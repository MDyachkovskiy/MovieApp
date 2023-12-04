package com.test.application.person_details

import android.os.Bundle
import android.view.View
import coil.load
import com.test.application.core.domain.personDetail.Person
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.KEY_BUNDLE_PERSON
import com.test.application.core.utils.convert
import com.test.application.core.utils.replaceFragment
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.person_details.databinding.FragmentPersonBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.koin.androidx.viewmodel.ext.android.viewModel


class PersonDetailsFragment :
    BaseFragmentWithAppState<AppState, Person, FragmentPersonBinding>(
    FragmentPersonBinding::inflate
) {

    private lateinit var personBundle : Person

    private val viewModel: PersonDetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personBundle = arguments?.getParcelable(KEY_BUNDLE_PERSON)?: Person()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        requestPersonDetail(personBundle.id)
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

    override fun setupData(data: Person) {
        displayPersonImage(data)
        initPersonTextData(data)
        initBirthPlaceMap(data)
    }

    private fun initBirthPlaceMap(data: Person) {
        childFragmentManager.replaceFragment(
           R.id.place_of_birth_map_container,
            MapsFragment(data.birthPlace[0].value) )
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