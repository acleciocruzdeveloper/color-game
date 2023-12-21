package br.com.aclecio.kotlin.controller

import br.com.aclecio.kotlin.model.ResultDTO
import br.com.aclecio.kotlin.service.ResultService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/results")
class ResultController (
    val resultService: ResultService
){
    @GetMapping("/{palletId}")
    fun result(@PathVariable("palletId") palletId: Int) = resultService.result(palletId)
}