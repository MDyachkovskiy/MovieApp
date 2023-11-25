package com.example.kotlin_movieapp.view.personDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.databinding.FragmentPersonBinding
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.domain.movieDetail.Person
import com.test.application.core.domain.personDetail.PersonDetailsResponse
import com.test.application.core.utils.KEY_BUNDLE_PERSON
import com.example.kotlin_movieapp.utils.convert
import com.example.kotlin_movieapp.utils.replaceFragment
import com.example.kotlin_movieapp.view.map.MapsFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.koin.androidx.viewmodel.ext.android.viewModel


class PersonDetailsFragmentWithAppState : com.test.application.core.view.BaseFragmentWithAppState<AppState, PersonDetailsResponse, FragmentPersonBinding>(
    FragmentPersonBinding::inflate
) {

    private lateinit var personBundle : Person

    private val viewModel: PersonDetailsViewModel by viewModel()

    companion object {
        fun newInstance(bundle: Bundle) : PersonDetailsFragmentWithAppState {
            val fragment = PersonDetailsFragmentWithAppState()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personBundle = arguments?.getParcelable(KEY_BUNDLE_PERSON)?: Person()

        requestPersonDetail(personBundle.id)

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            if (it is AppState.Success<*>) {
                Log.d("@@@", "fragment received data class: ${it.data?.javaClass}")
            }
            renderData(it)
        }
    }

    private fun requestPersonDetail(personId: Int?) {
        viewModel.getPersonDetailsFromRemoteSource(personId)
    }

    private fun displayPerson(person : PersonDetailsResponse) {

        with(binding) {

            val personDTO = person.person[0]

            view?.let {
                Glide.with(it).load(personDTO.photo).into(personPhoto) }

            personName.text = personDTO.name

            personProfession.text = personDTO.profession.convert {
                    profession -> profession.value}

            personAge.text = personDTO.age.toString()

            personDateOfBirth.text = convertDate(personDTO.birthday)

            val birthLocation = personDTO.birthPlace.convert{
                    birthPlace ->  birthPlace.value }
            personPlaceOfBirth.text = birthLocation

            childFragmentManager.replaceFragment(
                R.id.placeOfBirthMapContainer,
                MapsFragment(birthLocation) )
        }
    }

    private fun convertDate (date : String?) : String {
        return if (date != null) {

            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val dateTime = LocalDateTime.parse(date, formatter)
            val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            dateTime.format(outputFormatter)

        } else ""
    }

    override fun setupData(data: PersonDetailsResponse) {
        displayPerson(data)
    }
}