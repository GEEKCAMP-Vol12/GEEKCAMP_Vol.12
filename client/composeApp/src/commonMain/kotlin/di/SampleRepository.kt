package di

interface SampleRepository {
    fun getMessage(): String
}

class SampleRepositoryImpl: SampleRepository {
    override fun getMessage(): String {
        return "Hello from SampleRepository injected by Koin"
    }
}
