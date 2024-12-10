package com.github.crisacm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform