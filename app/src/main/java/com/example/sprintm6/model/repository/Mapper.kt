package com.example.sprintm6.model.repository

import com.example.sprintm6.model.Phone
import com.example.sprintm6.model.remote.clases.DetailsPhoneApiClass
import com.example.sprintm6.model.remote.clases.PhoneApiClass
fun fromInternetToCoursesEntity(phoneList: List<PhoneApiClass>, detailsPhoneList: List<DetailsPhoneApiClass>): List<Phone> {

    return phoneList.mapIndexed { index, phoneApi ->
        val detailsPhoneApi = detailsPhoneList[index]

        Phone(
            id = phoneApi.id,
            phoneName = phoneApi.name,
            phonePrice = phoneApi.price,
            phoneImage = phoneApi.image,
            phoneDescription = detailsPhoneApi.description,
            phoneLastPrice = detailsPhoneApi.lastPrice,
            phoneCredit = detailsPhoneApi.credit,
        )
    }
}




