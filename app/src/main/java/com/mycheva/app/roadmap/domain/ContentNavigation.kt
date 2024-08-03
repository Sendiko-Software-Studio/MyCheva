package com.mycheva.app.roadmap.domain

import com.mycheva.app.roadmap.data.Content


fun Content.next(): Content = when (this) {
    Content.WebDevelopment -> Content.UIUX
    Content.UIUX -> Content.Startup
    Content.Startup -> Content.WebDevelopment
    else -> Content.WebDevelopment
}

fun Content.previous(): Content = when (this) {
    Content.WebDevelopment -> Content.Startup
    Content.UIUX -> Content.WebDevelopment
    Content.Startup -> Content.UIUX
    else -> Content.WebDevelopment
}