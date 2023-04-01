package com.example.bmi_mui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class BmiViewModel() : ViewModel() {
    var _count: MutableLiveData<Double> = MutableLiveData()
    var BMI: LiveData<Double> = _count
    var healthStatus: MutableLiveData<String> = MutableLiveData()
    var baseObservable: Observable = Observable()
    var errormessage: MutableLiveData<String> = MutableLiveData()

    fun validate() {
        if (baseObservable.height.isBlank() && baseObservable.weight.isBlank()) {
            errormessage.value = "please enter the height & weight "

        } else if (baseObservable.height.isBlank()) {
            errormessage.value = "Please enter the height "
        } else if (baseObservable.weight.isBlank()) {
            errormessage.value = "Please enter the weight"
        } else {

            val Height_in_metre = baseObservable.height.toFloat() / 100
            val total = baseObservable.weight.toFloat() / (Height_in_metre * Height_in_metre)
            val x = (total * 100).roundToInt() / 100.0

            if (x < 18.5) {
                //var a =  "$x \n You are under weight "
                healthStatus.value = "You are under weight "
                _count.value = x
            } else if (x >= 18.5 && x < 24.9) {
                healthStatus.value = "You are healthy "
                _count.value = x

            } else if (x >= 24.9 && x < 30) {
                healthStatus.value = "You are Over weight "
                _count.value = x
            } else {
                // _count.value.toString().plus("You are suffering Obesity ")
                healthStatus.value = "You are suffering Obesity "
                _count.value = x


            }

        }

    }

}

