package com.jobdetailsmanager.pocketemployment.interviews

sealed class InterviewsState(){
    object NoInterviews : InterviewsState()
    object MultipleInterviews: InterviewsState()

}