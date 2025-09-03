package com.mycheva.app.core.ui.utils

import com.mycheva.app.R
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.ui.utils.UiText.*

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Remote.REQUEST_TIMEOUT -> StringResource(
            R.string.the_request_timed_out
        )

        DataError.Remote.TOO_MANY_REQUESTS -> StringResource(
            R.string.youve_hit_your_rate_limit
        )

        DataError.Remote.NO_INTERNET -> StringResource(
            R.string.no_internet
        )

        DataError.Remote.SERIALIZATION -> StringResource(
            R.string.error_serialization
        )

        DataError.Remote.UNKNOWN -> StringResource(
            R.string.unknown_error
        )

        DataError.Local.UNKNOWN -> StringResource(
            R.string.unknown_error
        )

        DataError.Remote.SERVER -> StringResource(
            R.string.server_error
        )

        DataError.Remote.NOT_FOUND -> StringResource(
            R.string.not_found
        )

        DataError.Remote.BAD_REQUEST -> StringResource(
            R.string.bad_request
        )
        DataError.Remote.UNAUTHORIZED -> StringResource(
            R.string.unauthorized
        )

        DataError.Local.DISK_FULL -> StringResource(
            R.string.error_disk_full
        )
    }
}