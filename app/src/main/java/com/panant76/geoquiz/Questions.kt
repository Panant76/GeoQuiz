package com.panant76.geoquiz

import androidx.annotation.StringRes

data class Questions (@StringRes val textResId:Int, val answer: Boolean, var usrAnswer:Boolean?){

}