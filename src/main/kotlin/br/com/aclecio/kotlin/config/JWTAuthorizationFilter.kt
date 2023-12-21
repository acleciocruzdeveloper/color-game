package br.com.aclecio.kotlin.config

import br.com.aclecio.kotlin.exceptions.UserNameNotFoundException
import br.com.aclecio.kotlin.util.JWTUtil

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager, private val jwtUtil: JWTUtil) :
    BasicAuthenticationFilter(authenticationManager) {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        authorizationHeader?.let {
            if (it.startsWith("Bearer")) {
                val auth = getAuthentication(it)
                SecurityContextHolder.getContext().authentication = auth
            }
        }
        chain.doFilter(request, response)
    }

    private fun getAuthentication(authorizationHeader: String?): UsernamePasswordAuthenticationToken {
        val token = authorizationHeader?.substring(7) ?: ""
        if (jwtUtil.tokenValidation(token)) {
            val username = jwtUtil.getUsername(token)
            username?.let {
                val user = jwtUtil.getUser(it)
                return UsernamePasswordAuthenticationToken(user, null, user?.authorities)
            }
        }
        throw UserNameNotFoundException("Auth invalid!")
    }

}
