package com.test.application.core.domain.personDetail

data class Person(
    val age: Int = 0,
    val birthPlace: List<BirthPlace> = listOf(),
    val birthday: String = "",
    val id: Int = 0,
    val name: String = "",
    val photo: String = "",
    val profession: List<Profession> = listOf()
)