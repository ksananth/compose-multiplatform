package com.whatsnew.form

import kotlin.random.Random

data class WhatsNew(
    val id: Int,
    val name: String,
    val storyTitle: String,
    val storyBadge: String,
    val pages: List<Page>,
    val languages: List<Language>,
    val brands: List<Brand>,
    val platforms: List<Platform>
) {
    companion object {
        fun empty() = WhatsNew(
            Random.nextInt(),
            name = "",
            storyTitle = "",
            storyBadge = "",
            pages = listOf(Page(
                title = "",
                description = "",
                zeplinSectionUrlAndroid = "",
                zeplinSectionUrlIos = ""
            )),
            languages = Language.entries,
            brands = Brand.entries,
            platforms = Platform.entries
        )
    }
}

data class Page(
    val title: String,
    val description: String,
    val zeplinSectionUrlAndroid: String,
    val zeplinSectionUrlIos: String,
)

enum class Brand {
    FR, KN, HB
}

enum class Platform { IOS, ANDROID }

enum class Language { EN, FR, NL, DE }