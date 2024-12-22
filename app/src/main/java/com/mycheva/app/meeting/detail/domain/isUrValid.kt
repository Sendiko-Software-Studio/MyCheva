package com.mycheva.app.meeting.detail.domain

import java.net.URI
import java.net.URISyntaxException

fun isValidUrl(url: String): Boolean {
    return try {
        URI(url).let {
            it.scheme != null && it.host != null
        }
    } catch (e: URISyntaxException) {
        false
    }
}