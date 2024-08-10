package di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import viewmodel.HealthAppViewModel
import viewmodel.HomeViewModel

val appModule = module {
    single {
        SampleRepositoryImpl()
    }.bind<SampleRepository>()

    viewModel {
        HealthAppViewModel()
    }

    viewModel { params ->
        HomeViewModel(
            updateUserData = params.get()
        )
    }
}

