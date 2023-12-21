package br.com.aclecio.kotlin.service

import br.com.aclecio.kotlin.entities.Pallete
import br.com.aclecio.kotlin.entities.User
import br.com.aclecio.kotlin.entities.Vote
import br.com.aclecio.kotlin.enumerates.ColorExceptionMessage.RESOURCE_NOT_FOUND
import br.com.aclecio.kotlin.enumerates.ColorExceptionMessage.USER_ALREADY_VOTED
import br.com.aclecio.kotlin.exceptions.ColorException
import br.com.aclecio.kotlin.model.VoteDTO
import br.com.aclecio.kotlin.repositories.PalleteRepository
import br.com.aclecio.kotlin.repositories.UserRepository
import br.com.aclecio.kotlin.repositories.VoteRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class VoteService(
    private val voteRepository: VoteRepository,
    private val palleteRepository: PalleteRepository,
    private val userRepository: UserRepository
) {
    fun vote(dto: VoteDTO) {
        val user = findUser()
        val pallete = palleteRepository.findOpenById(dto.palletId)
            .orElseThrow {
                ColorException(RESOURCE_NOT_FOUND)
            }
        voteRepository.save(
            Vote(
                pallete = pallete,
                color = dto.color,
                user = user
            )
        )
    }

    private fun validateIfAlreadyVoted(user: User, pallete: Pallete) {
        if (voteRepository.findByUserAndPallete(user, pallete).isPresent)
            throw ColorException(USER_ALREADY_VOTED)
    }

    private fun findUser(): User {
        val authentication = SecurityContextHolder.getContext().authentication
        val email = (authentication.principal as org.springframework.security.core.userdetails.User).username
        return userRepository.findByEmail(email).orElseThrow {
            ColorException(RESOURCE_NOT_FOUND)
        }
    }

}
