package di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import viewmodel.HealthAppViewModel

val appModule = module {
    single {
        SampleRepositoryImpl()
    }.bind<SampleRepository>()

    viewModel {
        HealthAppViewModel()
    }
}

