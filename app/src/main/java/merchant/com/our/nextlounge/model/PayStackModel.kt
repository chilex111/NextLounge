package merchant.com.our.nextlounge.model

import com.google.gson.annotations.SerializedName

class PayStackModel {
    @SerializedName("data")
    var data: PayStackData? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: Boolean? = null
}


/* Location:              /Users/ekeretepeter/Downloads/veritas/classes-dex2jar.jar!/veritas/exolve/com/veritas/models/PayStackModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */