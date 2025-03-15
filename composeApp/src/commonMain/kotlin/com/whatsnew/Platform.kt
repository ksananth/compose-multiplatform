package com.whatsnew

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform