package org.example

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.example.annotations.AnnotationUserApplication
import org.koin.ksp.generated.*

data class User(val name: String)

interface UserRepository {
    fun findUser(name: String): User?
    fun addUsers(users: List<User>)
}

class UserRepositoryImpl : UserRepository {
    private val _users = arrayListOf<User>()

    init {
        val user = listOf(User("たかし"))
        addUsers(user)
    }

    override fun findUser(name: String): User? {
        return _users.firstOrNull() { it.name == name }
    }

    override fun addUsers(users: List<User>) {
        _users.addAll(users)
    }
}

class UserService(private val userRepository: UserRepository) {
    class DefaultData {
        companion object {
            val DEFAULT_USER = User("たかし")
        }
    }

    fun getDefaultUser(): User {
        return userRepository.findUser(DefaultData.DEFAULT_USER.name) ?: error("Can't find default user")
    }
}

class UserApplication : KoinComponent {
    private val userService : UserService by inject()

    fun sayHello() {
        val user = userService.getDefaultUser()
        val message = "Hello '${user.name}'!"
        println(message)
    }
}

val appModule = module {
    single<UserRepository> { UserRepositoryImpl() }

    single { UserService(get()) }
}

fun main() {
    startKoin {
        modules(appModule)
        modules(AppModule().module)
    }

    print("module DI: ")
    UserApplication().sayHello()

    print("annotation DI: ")
    AnnotationUserApplication().sayHello()
}