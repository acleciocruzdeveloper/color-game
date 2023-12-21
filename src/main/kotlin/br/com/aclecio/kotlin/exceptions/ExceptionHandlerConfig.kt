package br.com.aclecio.kotlin.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlerConfig {
    @ExceptionHandler(ColorException::class)
    fun handlerException(ex: ColorException) = ResponseEntity.status(ex.colorMessage.status)
        .body(ResponseError(ex.colorMessage.message))
}

data class ResponseError(
    val message: String
)
