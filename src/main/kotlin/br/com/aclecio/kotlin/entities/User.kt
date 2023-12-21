package br.com.aclecio.kotlin.entities

import javax.persistence.*

@Entity
@Table(name = "color_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(unique = true)
    val email: String = "",
    var password: String = ""
) : SuperEntity()
