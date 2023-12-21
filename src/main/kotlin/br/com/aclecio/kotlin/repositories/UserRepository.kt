package br.com.aclecio.kotlin.repositories

import br.com.aclecio.kotlin.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID>{

    @Query("""
        select u from User u where u.email=?1
    """)
    fun findByEmail(email: String): Optional<User>
}
