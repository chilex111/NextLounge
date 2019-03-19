package merchant.com.our.nextlounge.model

import com.google.gson.annotations.SerializedName

class Authorization {
    @SerializedName("authorization_code")
    var authorizationCode: String? = null
    @SerializedName("bank")
    var bank: String? = null
    @SerializedName("bin")
    var bin: String? = null
    @SerializedName("card_type")
    var cardType: String? = null
    @SerializedName("channel")
    var channel: String? = null
    @SerializedName("country_code")
    var countryCode: String? = null
    @SerializedName("exp_month")
    var expMonth: String? = null
    @SerializedName("exp_year")
    var expYear: String? = null
    @SerializedName("last4")
    var last4: String? = null
    @SerializedName("reusable")
    var reusable: Boolean? = null
    @SerializedName("signature")
    var signature: String? = null
}


/* Location:              /Users/ekeretepeter/Downloads/veritas/classes-dex2jar.jar!/veritas/exolve/com/veritas/models/Authorization.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */