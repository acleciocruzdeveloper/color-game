package br.com.aclecio.kotlin.config

import org.springframework.security.core.userdetails.User
import br.com.aclecio.kotlin.exceptions.UserNameNotFoundException
import br.com.aclecio.kotlin.model.Credentials
import br.com.aclecio.kotlin.util.JWTUtil
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val jwtUtil: JWTUtil
) : UsernamePasswordAuthenticationFilter(authenticationManager) {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication? {
        try {
            val credentials = jacksonObjectMapper().readValue(request?.inputStream, Credentials::class.java)
            val token = UsernamePasswordAuthenticationToken(credentials.email, credentials.password)
            return authenticationManager.authenticate(token)
        } catch (e: Exception) {
            throw UserNameNotFoundException("")
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val username = (authResult?.principal as User).username
        val token = jwtUtil.generateToken(username)
        response?.addHeader("Authorization", "Bearer $token")
    }
}