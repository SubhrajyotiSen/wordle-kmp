package me.subhrajyoti.wordle

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform