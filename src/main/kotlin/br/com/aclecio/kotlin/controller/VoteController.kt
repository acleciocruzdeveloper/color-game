package br.com.aclecio.kotlin.controller

import br.com.aclecio.kotlin.model.VoteDTO
import br.com.aclecio.kotlin.service.VoteService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/votes")
class VoteController (
    private val voteService: VoteService
){

    @PostMapping
    fun vote(@RequestBody @Valid dto: VoteDTO): ResponseEntity<VoteDTO>{
        voteService.vote(dto)
        return ResponseEntity.ok(dto)
    }
}