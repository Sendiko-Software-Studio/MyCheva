package com.mycheva.app.attendance.presentation

import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FlashlightOn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.common.util.concurrent.ListenableFuture
import com.mycheva.app.R
import com.mycheva.app.attendance.domain.BarcodeAnalyzer
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.theme.poppinsFamily
import kotlinx.coroutines.delay
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class,
    ExperimentalSharedTransitionApi::class
)
@Composable
fun SharedTransitionScope.AttendanceScreen(
    state: AttendanceState,
    onEvent: (AttendanceEvent) -> Unit,
    onNavigateBack: () -> Unit,
    animatedContentScope: AnimatedContentScope
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var preview by remember { mutableStateOf<Preview?>(null) }

    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    LaunchedEffect(
        key1 = cameraPermissionState.hasPermission,
        block = {
            if (!cameraPermissionState.hasPermission)
                cameraPermissionState.launchPermissionRequest()
        }
    )

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.asString(context).isNotEmpty()) {
            delay(2000)
            onEvent(AttendanceEvent.OnClearState)
        }
    }

    LaunchedEffect(key1 = state.isRequestSuccess) {
        if (state.isRequestSuccess) {
            delay(2000)
            onNavigateBack()
        }
    }

    NotificationBox(
        message = state.notificationMessage.asString(context),
        isLoading = state.isLoading,
        isErrorNotification = state.isRequestFailed
    ) {
        Scaffold(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "presensi"),
                    animatedVisibilityScope = animatedContentScope
                ),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Absensi",
                            fontFamily = poppinsFamily,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.Black,
                        navigationIconContentColor = Color.Black
                    )
                )
            },
            containerColor = Color.Transparent
        ) { it ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding()),
            ) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { androidViewContext ->
                        PreviewView(androidViewContext).apply {
                            this.scaleType = PreviewView.ScaleType.FILL_CENTER
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                            )
                            implementationMode =
                                PreviewView.ImplementationMode.COMPATIBLE
                        }
                    },
                    update = { previewView ->
                        val cameraSelector: CameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()
                        val cameraExecutor: ExecutorService =
                            Executors.newSingleThreadExecutor()
                        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
                            ProcessCameraProvider.getInstance(context)

                        cameraProviderFuture.addListener(
                            {
                                preview = Preview.Builder().build().also {
                                    it.surfaceProvider = previewView.surfaceProvider
                                }
                                val cameraProvider: ProcessCameraProvider =
                                    cameraProviderFuture.get()
                                val barcodeAnalyser = BarcodeAnalyzer { barcodes ->
                                    barcodes.forEach { barcode ->
                                        barcode.rawValue?.let { barcodeValue ->
                                            if (state.eventId.isBlank())
                                                onEvent(AttendanceEvent.OnEventIdRead(state.token, barcodeValue, state.userId))
                                        }
                                    }
                                }
                                val imageAnalysis: ImageAnalysis =
                                    ImageAnalysis.Builder()
                                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                        .build()
                                        .also {
                                            it.setAnalyzer(
                                                cameraExecutor,
                                                barcodeAnalyser
                                            )
                                        }

                                try {
                                    cameraProvider.unbindAll()
                                    cameraProvider.bindToLifecycle(
                                        lifecycleOwner,
                                        cameraSelector,
                                        preview,
                                        imageAnalysis
                                    )
                                } catch (e: Exception) {
                                    Log.d("TAG", "CameraPreview: ${e.localizedMessage}")
                                }
                            }, ContextCompat.getMainExecutor(context)
                        )
                    }
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Silakan scan kode QR untuk absensi",
                        fontFamily = poppinsFamily,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(R.drawable.code_scan_square),
                        contentDescription = "QRCODE SCAN",
                        modifier = Modifier.size(256.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.FlashlightOn,
                            contentDescription = "FlashLight",
                            modifier = Modifier.size(128.dp)
                        )
                    }
                }
            }
        }
    }
}