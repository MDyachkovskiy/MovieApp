package com.test.application.remote_data.dto.personDetailsDTO
data class PersonDTO(
    val age: Int = 0,
    val birthPlace: List<BirthPlaceDTO> = listOf(),
    val birthday: String = "",
    val id: Int = 0,
    val name: String = "",
    val photo: String = "",
    val profession: List<ProfessionDTO> = listOf()
)