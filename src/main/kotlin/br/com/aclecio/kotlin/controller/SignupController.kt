package br.com.aclecio.kotlin.controller

import br.com.aclecio.kotlin.model.CreateUserDTO
import br.com.aclecio.kotlin.model.UserDTO
import br.com.aclecio.kotlin.service.UserService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/signup")
class SignupController(
    val userService: UserService
) {

    @PostMapping
    fun signup(@RequestBody @Valid user: CreateUserDTO): ResponseEntity<UserDTO> {
        val userCreeated = userService.create(user)
        return ResponseEntity.created(URI("")).body(userCreeated)
    }
}