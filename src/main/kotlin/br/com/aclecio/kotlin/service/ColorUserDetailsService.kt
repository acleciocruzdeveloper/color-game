package br.com.aclecio.kotlin.service

import br.com.aclecio.kotlin.exceptions.UserNameNotFoundException
import br.com.aclecio.kotlin.repositories.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class ColorUserDetailsService(
    val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        val user = userRepository.findByEmail(username).orElseThrow {
            UserNameNotFoundException("User not found!")
        }
        val builder = User.withUsername(username)
        builder.password(user.password)
        builder.authorities("USER")
        return builder.build()
    }

}
