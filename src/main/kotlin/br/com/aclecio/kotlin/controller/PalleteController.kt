package br.com.aclecio.kotlin.controller

import br.com.aclecio.kotlin.service.PalleteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/palletes")
class PalleteController(
    val palleteService: PalleteService
) {
    @GetMapping
    fun available(): ResponseEntity<Int> {
        return ResponseEntity.ok(palleteService.lastPalleteId())
    }
}