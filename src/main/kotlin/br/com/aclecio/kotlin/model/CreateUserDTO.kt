package br.com.aclecio.kotlin.model

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class CreateUserDTO(
    @field:NotBlank
    @field:Email
    val email: String,
    @field:NotBlank
    @field:Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$")
    var password: String
)
