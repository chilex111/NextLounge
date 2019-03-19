package merchant.com.our.nextlounge.model

import com.google.gson.annotations.SerializedName

class Customer {
    @SerializedName("customer_code")
    var customerCode: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("first_name")
    var firstName: String? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("last_name")
    var lastName: String? = null
}


/* Location:              /Users/ekeretepeter/Downloads/veritas/classes-dex2jar.jar!/veritas/exolve/com/veritas/models/Customer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */