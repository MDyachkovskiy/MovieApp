package com.example.kotlin_movieapp.view.personDetails

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.databinding.FragmentPersonBinding
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.Person
import com.example.kotlin_movieapp.model.datasource.remote.personDetailsResponse.PersonDTO
import com.example.kotlin_movieapp.utils.KEY_BUNDLE_PERSON
import com.example.kotlin_movieapp.utils.convert
import com.example.kotlin_movieapp.utils.replaceFragment
import com.example.kotlin_movieapp.view.base.BaseFragment
import com.example.kotlin_movieapp.view.map.MapsFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.koin.androidx.viewmodel.ext.android.viewModel


class PersonDetailsFragment : BaseFragment<AppState, PersonDTO, FragmentPersonBinding>(
    FragmentPersonBinding::inflate
) {

    private lateinit var personBundle : Person

    private val viewModel: PersonDetailsViewModel by viewModel()

    companion object {
        fun newInstance(bundle: Bundle) : PersonDetailsFragment {
            val fragment = PersonDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personBundle = arguments?.getParcelable(KEY_BUNDLE_PERSON)?: Person()

        requestPersonDetail(personBundle.id)

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun requestPersonDetail(personId: Int?) {
        viewModel.getPersonDetailsFromRemoteSource(personId)
    }

    private fun displayPerson(personDTO : PersonDTO) {

        with(binding) {

            view?.let {
                Glide.with(it).load(personDTO.photo).into(personPhoto) }

            personName.text = personDTO.name ?: ""

            personProfession.text = personDTO.profession?.convert {
                    profession -> profession.value}

            personAge.text = personDTO.age?.toString()

            personDateOfBirth.text = convertDate(personDTO.birthday)

            val birthLocation = personDTO.birthPlace?.convert{
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

    override fun setupData(data: PersonDTO) {
        displayPerson(data)
    }
}