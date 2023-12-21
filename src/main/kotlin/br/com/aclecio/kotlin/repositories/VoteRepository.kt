package br.com.aclecio.kotlin.repositories

import br.com.aclecio.kotlin.entities.Pallete
import br.com.aclecio.kotlin.entities.User
import br.com.aclecio.kotlin.entities.Vote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface VoteRepository : JpaRepository<Vote, Int> {

    @Query(
        """
        select v from Vote v
        where v.user = ?1
        and v.pallete = ?2
    """
    )
    fun findByUserAndPallete(user: User, pallete: Pallete): Optional<Vote>
}
