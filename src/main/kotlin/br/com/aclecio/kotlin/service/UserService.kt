package br.com.aclecio.kotlin.service

import br.com.aclecio.kotlin.enumerates.ColorExceptionMessage
import br.com.aclecio.kotlin.exceptions.ColorException
import br.com.aclecio.kotlin.model.CreateUserDTO
import br.com.aclecio.kotlin.model.UserDTO
import br.com.aclecio.kotlin.model.toEntity
import br.com.aclecio.kotlin.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {
    fun create(dto: CreateUserDTO): UserDTO {
        validateIfEmailAlreadyExists(dto)
        val user = dto.toEntity()

        user.password = passwordEncoder.encode(user.password)
        val savedUser = userRepository.save(user)
        return UserDTO(savedUser.email)
    }

    private fun validateIfEmailAlreadyExists(dto: CreateUserDTO) {
        if (userRepository.findByEmail(dto.email).isPresent)
            throw ColorException(ColorExceptionMessage.USER_ALREADY_EXISTS)
    }

}