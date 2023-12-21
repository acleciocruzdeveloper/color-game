package br.com.aclecio.kotlin.exceptions

import br.com.aclecio.kotlin.enumerates.ColorExceptionMessage

class ColorException(
    val colorMessage: ColorExceptionMessage
) : RuntimeException(colorMessage.message)