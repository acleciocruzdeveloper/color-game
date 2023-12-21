package br.com.aclecio.kotlin.util

import org.springframework.security.core.userdetails.UserDetails
import br.com.aclecio.kotlin.service.ColorUserDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.lang.Exception
import java.util.*

@Component
class JWTUtil(
    val userDatailsService: ColorUserDetailsService
) {

    @Value("\${jwt.secret}")
    private lateinit var secret: String
    private val expiration: Long = 60000L

    fun generateToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS256, secret.toByteArray())
            .compact()
    }

    fun tokenValidation(token: String): Boolean {
        val clains = getClaimsToken(token)
        if (clains != null) {
            val username = clains.subject
            val expirationDate = clains.expiration
            val now = Date(System.currentTimeMillis())
            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return false
            }
        }
        return true
    }

    private fun getClaimsToken(token: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
        } catch (e: Exception) {
            null
        }
    }

    fun getUsername(token: String): String? {
        val claims = getClaimsToken(token)
        return claims?.subject
    }

    fun getUser(username: String): UserDetails? {
        return userDatailsService.loadUserByUsername(username)
    }
}
