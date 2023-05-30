package com.example.myap.di

import com.example.myap.data.utils.AndroidResourceProvider
import com.example.myap.data.utils.ResourceProvider
import com.example.myap.presentation.presenter.DetailViewModel
import com.example.myap.presentation.presenter.HomeViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        DetailViewModel(get())
    }
    factory<ResourceProvider> {
        AndroidResourceProvider(get())
    }
}
