package com.whatsnew

import com.whatsnew.form.AddItemViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(
            module {
                single { AddItemViewModel() }
            }
        )
    }
}