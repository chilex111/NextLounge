package merchant.com.our.nextlounge.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Details {

    @SerializedName("created_at")
    var createdAt: String? = null
    @Expose
    var email: String? = null
    @SerializedName("email_verified_at")
    var emailVerifiedAt: Any? = null
    @Expose
    var id: Long? = null
    @Expose
    var name: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null

}
