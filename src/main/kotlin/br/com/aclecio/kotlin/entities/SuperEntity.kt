package br.com.aclecio.kotlin.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.MappedSuperclass

@MappedSuperclass
sealed class SuperEntity {
    @CreatedDate
    open var created_at: LocalDateTime = LocalDateTime.now();
    @LastModifiedDate
    open var updated_at: LocalDateTime = LocalDateTime.now();
}