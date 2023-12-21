package br.com.aclecio.kotlin.schedules

import br.com.aclecio.kotlin.service.PalleteService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class PalleteSchedule (
    val palleteService: PalleteService
){
    @Scheduled(fixedDelayString = "\${schedule-time}")
    fun palleteTask(){
        palleteService.lastPalleteId()?.let { palleteService.close(it) }
        palleteService.open()
    }
}