package br.com.aclecio.kotlin.enumerates

import org.springframework.http.HttpStatus

enum class ColorExceptionMessage(val status: HttpStatus, val message: String) {
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found"),
    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "User already exists"),
    USER_ALREADY_VOTED(HttpStatus.BAD_REQUEST, "User already voted");
}