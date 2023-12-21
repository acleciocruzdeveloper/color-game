package br.com.aclecio.kotlin.model

import br.com.aclecio.kotlin.entities.User

data class UserDTO(
    val email: String
)

fun CreateUserDTO.toEntity() = User(
    email = this.email,
    password = this.password
)