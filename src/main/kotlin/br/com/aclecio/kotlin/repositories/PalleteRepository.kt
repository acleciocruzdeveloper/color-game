package br.com.aclecio.kotlin.repositories

import br.com.aclecio.kotlin.entities.Pallete
import br.com.aclecio.kotlin.model.ResultDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface PalleteRepository : JpaRepository<Pallete, Int> {
    @Query(
        """
        select  p from Pallete p where p.status = br.com.aclecio.kotlin.entities.Palletestatus.OPEN and p.id=?1
    """
    )
    fun findOpenById(id: Int): Optional<Pallete>

    @Query("""
        select p.id from Pallete  p where p.status = br.com.aclecio.kotlin.entities.Palletestatus.OPEN order by created_at desc
    """)
    fun lastOpenedPallete(): Int?

    @Query(
        """
            select new br.com.aclecio.kotlin.model.ResultDTO(v.color, count(v)) from Pallete p inner join p.votes v 
            where p.status = br.com.aclecio.kotlin.entities.Palletestatus.CLOSE
            and p.id=?1 
            group by v.color
        """
    )
    fun result(palletId: Int): Optional<List<ResultDTO>>
}
