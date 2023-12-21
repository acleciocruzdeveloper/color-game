package br.com.aclecio.kotlin.entities


import javax.persistence.*
import br.com.aclecio.kotlin.entities.Palletestatus.OPEN as OPEN

@Entity
@Table(name = "palletes")
data class Pallete(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Enumerated(EnumType.STRING)
    var status: Palletestatus = OPEN,
    @OneToMany
    @JoinColumn(name = "pallete_id")
    val votes: Collection<Vote> = listOf()
): SuperEntity()

enum class Palletestatus {
    OPEN, CLOSE
}
