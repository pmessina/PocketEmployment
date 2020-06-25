package com.jobdetailsmanager.pocketemployment.recruiters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.jobdetailsmanager.pocketemployment.database.Recruiter
import com.jobdetailsmanager.pocketemployment.database.RecruiterCompany
import kotlinx.coroutines.runBlocking

class RecruiterCallsViewModel(private val recruiterCallsRepository: RecruiterCallsRepository) : ViewModel() {

    fun insertPhoneNumber(phoneNumber: String) {
        recruiterCallsRepository.addRecruiter(phoneNumber)

        //Toast.makeText(app, "$phoneNumber is shown in the View Model", Toast.LENGTH_SHORT).show()
    }

    fun getRecruiters(): LiveData<List<Recruiter>> {
        return recruiterCallsRepository.getRecruiters()
    }

    fun getRecruiterCompanies(): LiveData<List<RecruiterCompany>> {
        return recruiterCallsRepository.getRecruiterCompanies()
    }

    private fun getRecruitersValue(owner: LifecycleOwner): List<Recruiter> {
        var recruiters = emptyList<Recruiter>()
        recruiterCallsRepository.getRecruiters().observe(owner, Observer { i ->
            recruiters = i
        })
        return recruiters
    }

    fun getRecruiterCompaniesValue(): List<RecruiterCompany> {
        return recruiterCallsRepository.getRecruiterCompanies().value!!
    }


    fun getRecruitersByRecruitingCompany(owner: LifecycleOwner): HashMap<RecruiterCompany, List<Recruiter>> {

        val map = HashMap<RecruiterCompany, List<Recruiter>>()

        val recruiters = getRecruitersValue(owner)
        //val recruiterCompany = getRecruiterCompaniesValue()

        val groupedRecruiters = recruiters.groupBy { it.recruiterCompanyId }

//        recruiterCompany.value?.map{
//            it
//        }


        return map

    }

    fun addRecruiterCompanies(){
        runBlocking{
            recruiterCallsRepository.addRecruiterCompanies()
        }
    }
}