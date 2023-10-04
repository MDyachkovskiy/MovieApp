package com.example.kotlin_movieapp.model.datasource.domain.personDetail

data class Doc(
    val age: Int = 0,
    val birthPlace: List<BirthPlace> = listOf(),
    val birthday: String = "",
    val id: Int = 0,
    val name: String = "",
    val photo: String = "",
    val profession: List<Profession> = listOf()
)