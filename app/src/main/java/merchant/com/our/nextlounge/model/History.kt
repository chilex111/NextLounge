package merchant.com.our.nextlounge.model

import com.google.gson.annotations.SerializedName

class History {
    @SerializedName("message")
    var message: String? = null
    @SerializedName("time")
    var time: Long? = null
    @SerializedName("type")
    var type: String? = null
}


/* Location:              /Users/ekeretepeter/Downloads/veritas/classes-dex2jar.jar!/veritas/exolve/com/veritas/models/History.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */