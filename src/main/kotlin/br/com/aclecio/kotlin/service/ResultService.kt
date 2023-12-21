package br.com.aclecio.kotlin.service

import br.com.aclecio.kotlin.repositories.PalleteRepository
import org.springframework.stereotype.Service

@Service
class ResultService(
    val palleteRepository: PalleteRepository
) {
    fun result(palletId: Int) = palleteRepository.result(palletId).orElse(listOf())
}
