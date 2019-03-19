package merchant.com.our.nextlounge.model

import com.google.gson.annotations.SerializedName

class Log {
    @SerializedName("attempts")
    var attempts: Long? = null
    @SerializedName("authentication")
    var authentication: Any? = null
    @SerializedName("channel")
    var channel: Any? = null
    @SerializedName("errors")
    var errors: Long? = null
    @SerializedName("history")
    var history: List<History>? = null
    @SerializedName("input")
    var input: List<Any>? = null
    @SerializedName("mobile")
    var mobile: Boolean? = null
    @SerializedName("success")
    var success: Boolean? = null
    @SerializedName("time_spent")
    var timeSpent: Long? = null
}


/* Location:              /Users/ekeretepeter/Downloads/veritas/classes-dex2jar.jar!/veritas/exolve/com/veritas/models/Log.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */