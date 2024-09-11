package org.example.annotations

import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class User(val name: String)

interface UserRepository {
    fun findUser(name: String): User?
    fun addUsers(users: List<User>)
}

@Single
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

@Single
class UserService(private val userRepository: org.example.UserRepository) {
    class DefaultData {
        companion object {
            val DEFAULT_USER = org.example.User("たかし")
        }
    }

    fun getDefaultUser(): org.example.User {
        return userRepository.findUser(DefaultData.DEFAULT_USER.name) ?: error("Can't find default user")
    }
}

class AnnotationUserApplication : KoinComponent {
    private val userService : UserService by inject()

    fun sayHello() {
        val user = userService.getDefaultUser()
        val message = "Hello '${user.name}'!"
        println(message)
    }
}