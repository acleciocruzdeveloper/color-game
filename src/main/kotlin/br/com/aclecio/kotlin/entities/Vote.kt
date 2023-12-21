package br.com.aclecio.kotlin.entities

import javax.persistence.*

@Entity
@Table(
    name = "votes", uniqueConstraints = [
        UniqueConstraint(columnNames = ["pallete_id", "user_id"])
    ]
)
data class Vote(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @ManyToOne
    @JoinColumn(name = "pallete_id")
    val pallete: Pallete,
    @Enumerated(EnumType.STRING)
    val color: Color,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
) : SuperEntity()

enum class Color(hex: String) {
    RED("#FF0000"),
    CYAN("#00FFFF"),
    BLUE("#0000FF"),
    PURPLE("#800080"),
    YELLOW("#FFFF00"),
    GREEN("#00FF00"),
    ORANGE("#00FF00"),
    PINK("#FFC0CB");
}