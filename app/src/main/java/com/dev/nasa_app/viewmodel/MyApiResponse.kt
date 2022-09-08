package com.dev.nasa_app.viewmodel


class MyApiResponse(
    val code: Int = 490,
    val requestName: String = "",
    val message: String = "Error response $code",
    val responseObj: Any? = null
){

}

class MyError(){
    val code: Int = 400
    var message:String = ""
    var error:String = ""


    fun getMessagebyCode(code: Int = 300):String{

        if (code == 400) {
            message = error
        } else if (code in 400..499) {
            message = "Error creating connection please try again"
        } else if (code in 500..599) {
            message = "We are having an issue accessing our servers please try again later"
        }else if (code == 300) {
            message = "Please check your internet connection and try again"
        } else {
            message = "Unexpected error occurred"
        }
        return message!!
    }

}
