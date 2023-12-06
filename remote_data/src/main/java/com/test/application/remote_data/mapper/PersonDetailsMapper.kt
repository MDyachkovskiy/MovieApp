package com.test.application.remote_data.mapper

import com.test.application.core.domain.personDetail.BirthPlace
import com.test.application.core.domain.personDetail.Person
import com.test.application.core.domain.personDetail.PersonDetails
import com.test.application.core.domain.personDetail.Profession
import com.test.application.remote_data.dto.personDetailsDTO.BirthPlaceDTO
import com.test.application.remote_data.dto.personDetailsDTO.PersonDTO
import com.test.application.remote_data.dto.personDetailsDTO.PersonDetailsDTO
import com.test.application.remote_data.dto.personDetailsDTO.ProfessionDTO

fun PersonDetailsDTO.toDomain() : PersonDetails {
    return PersonDetails(
        person = this.person.map { personDTO -> personDTO.toDomain() },
        limit = this.limit,
        page = this.page,
        pages = this.pages,
        total = this.total
    )
}

fun PersonDTO.toDomain() : Person {
    return Person(
        age = this.age,
        birthPlace = this.birthPlace.map {birthPlaceDTO -> birthPlaceDTO.toDomain() },
        birthday = this.birthday,
        id = this.id,
        name = this.name,
        photo = this.photo,
        profession = this.profession.map {professionDTO -> professionDTO.toDomain() }
    )
}

fun BirthPlaceDTO.toDomain() : BirthPlace {
    return BirthPlace(
        value = this.value
    )
}

fun ProfessionDTO.toDomain() : Profession {
    return Profession(
        value = this.value
    )
}