package com.mycheva.app.profile.main.presentation

import com.mycheva.app.profile.main.domain.Division
import com.mycheva.app.profile.main.domain.User
import com.mycheva.app.profile.main.domain.UserData

data class UserUi(
    val id: Int,
    val name: String,
    val fullName: String,
    val profileUrl: String,
    val nim: String,
    val email: String,
    val major: String,
    val faculty: String,
    val division: Division
) {
    fun toDomain(): User {
        return User(
            id = id,
            name = name,
            profileUrl = profileUrl,
            data = UserData(
                nim = nim,
                email = email,
                major = major,
                faculty = faculty,
                division = division,
                fullName = fullName
            )
        )
    }

    companion object {
        fun fromDomain(user: User): UserUi {
            return UserUi(
                id = user.id,
                name = user.name,
                profileUrl = user.profileUrl,
                nim = user.data.nim,
                email = user.data.email,
                major = user.data.major,
                faculty = user.data.faculty,
                division = user.data.division,
                fullName = user.data.fullName
            )
        }
    }
}
