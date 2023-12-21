package br.com.aclecio.kotlin.model

import br.com.aclecio.kotlin.entities.Color
import javax.validation.constraints.NotNull

data class VoteDTO(
    @field:NotNull
    val palletId: Int,
    @field:NotNull
    val color: Color
)
