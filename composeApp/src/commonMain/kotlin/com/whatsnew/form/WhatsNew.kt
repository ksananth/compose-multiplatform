package com.whatsnew.form

import kotlin.random.Random

data class WhatsNew(
    val id: Int,
    val name: String,
    val zeplinSectionUrlAndroid: String,
    val zeplinSectionUrlIos: String,
    val title: String,
    val description: String,
    val languages: List<Language>,
    val brands: List<Brand>,
    val platforms: List<Platform>
) {
    companion object {
        fun empty() = WhatsNew(
            Random.nextInt(),
            "",
            "",
            "",
            "",
            "",
            Language.entries,
            Brand.entries,
            Platform.entries
        )
    }
}

enum class Brand {
    FR, KN, HB
}

enum class Platform { IOS, ANDROID }

enum class Language { EN, FR, NL, DE }