package di

import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        SampleRepositoryImpl()
    }.bind<SampleRepository>()
}

