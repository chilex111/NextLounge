package merchant.com.our.nextlounge.model

import com.google.gson.annotations.Expose

class LoginModel {

    @Expose
    var details: Details? = null
    @Expose
     var error: String? = null
    @Expose
    var success: Success? = null

}
