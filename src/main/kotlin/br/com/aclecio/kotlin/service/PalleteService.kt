package br.com.aclecio.kotlin.service

import br.com.aclecio.kotlin.entities.Pallete
import br.com.aclecio.kotlin.entities.Palletestatus
import br.com.aclecio.kotlin.enumerates.ColorExceptionMessage
import br.com.aclecio.kotlin.exceptions.ColorException
import br.com.aclecio.kotlin.repositories.PalleteRepository
import org.springframework.stereotype.Service

@Service
class PalleteService(
    val palleteRepository: PalleteRepository
) {
    fun open() = palleteRepository.save(Pallete()).id

    fun close(id: Int) {
        val openedPallete = palleteRepository.findOpenById(id).orElseThrow {
            ColorException(ColorExceptionMessage.RESOURCE_NOT_FOUND)
        }
        openedPallete.status = Palletestatus.CLOSE
        palleteRepository.save(openedPallete)
    }

    fun lastPalleteId() = palleteRepository.lastOpenedPallete()
}
