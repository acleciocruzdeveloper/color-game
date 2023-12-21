package br.com.aclecio.kotlin.model

import br.com.aclecio.kotlin.entities.Color

data class ResultDTO(
    val color: Color,
    val count: Long
)
