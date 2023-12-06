package com.test.application.person_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import coil.load
import com.test.application.core.domain.personDetail.Person
import com.test.application.core.domain.personDetail.PersonDetails
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.KEY_BUNDLE_PERSON
import com.test.application.core.utils.convert
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.person_details.databinding.FragmentPersonBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class PersonDetailsFragment :
    BaseFragmentWithAppState<AppState, PersonDetails, FragmentPersonBinding>(
    FragmentPersonBinding::inflate
) {

    private val personId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_PERSON) ?:
        throw IllegalArgumentException(getString(R.string.incorrect_person_id))
    }

    private val viewModel: PersonDetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        requestPersonDetail(personId)
    }

    private fun requestPersonDetail(personId: Int) {
        viewModel.getPersonDetailsFromRemoteSource(personId)
    }

    private fun convertDate (date : String?) : String {
        return if (date != null) {
            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val dateTime = LocalDateTime.parse(date, formatter)
            val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            dateTime.format(outputFormatter)

        } else ""
    }

    override fun setupData(data: PersonDetails) {
        val person = data.person.firstOrNull()
        if (person != null) {
            displayPersonImage(person)
            initPersonTextData(person)
            initBirthPlaceMap(person)
        } else {
            showErrorToast(getString(R.string.no_data_for_actor_error))
        }
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun initBirthPlaceMap(data: Person) {
        if (data.birthPlace.isNotEmpty()) {
            val birthPlaceValue = data.birthPlace.first().value
            val mapsFragment = MapsFragment(birthPlaceValue)
            childFragmentManager.beginTransaction()
                .replace(R.id.place_of_birth_map_container, mapsFragment)
                .commit()
        }
        showErrorToast(getString(R.string.no_data_for_birth_place))
    }

    private fun initPersonTextData(person: Person) {
        with(binding) {
            tvPersonName.text = person.name

            tvPersonProfession.text = person.profession.convert {
                    profession -> profession.value}

            tvPersonAge.text = person.age.toString()

            tvPersonDateOfBirth.text = convertDate(person.birthday)

            val birthLocation = person.birthPlace.convert{
                    birthPlace ->  birthPlace.value }
            tvPersonPlaceOfBirth.text = birthLocation
        }
    }

    private fun displayPersonImage(person: Person) {
        binding.personPhoto.load(person.photo){
            crossfade(true)
            placeholder(com.test.application.core.R.drawable.person_placeholder)
        }
    }
}