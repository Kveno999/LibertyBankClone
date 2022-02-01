package com.example.libertybank.classes

data class User(
    val name : String = "",
    val surname : String = "",
    var card : String = "",
    var profileCompleted : Int = 0,
    var mobileNumber : Long = 0
)
