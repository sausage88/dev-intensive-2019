package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils.parseFullName
import java.util.*

data class User (
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false
) {
    private constructor(builder: Builder) : this(
        builder.id!!,
        builder.firstName,
        builder.lastName,
        builder.avatar
        )

    data class Builder(
        var id: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var avatar: String? = null
    ) {
        private var lastId =  -1

        fun id() {
            lastId++
            this.id = lastId.toString()
        }

        fun firstName(firstName: String?) {
            this.firstName = firstName
        }

        fun lastName(lastName: String?) {
            this.lastName = firstName
        }

        fun avatar(avatar: String?) {
            this.firstName = firstName
        }

        fun build() = User(this)
    }


    companion object Factory {

        private var id = -1
        fun makeUser(fullName: String): User {
            id++

            val (firstName, lastName) = parseFullName(fullName)

            return User(
                id.toString(),
                firstName = firstName,
                lastName = lastName,
                avatar = null
            )
        }
    }
}