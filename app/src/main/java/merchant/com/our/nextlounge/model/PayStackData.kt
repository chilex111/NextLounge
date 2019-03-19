package merchant.com.our.nextlounge.model

import com.google.gson.annotations.SerializedName

class PayStackData {
    @SerializedName("amount")
    var amount: Long? = null
    @SerializedName("authorization")
    var authorization: Authorization? = null
    @SerializedName("channel")
    var channel: String? = null
    @SerializedName("currency")
    var currency: String? = null
    @SerializedName("customer")
    var customer: Customer? = null
    @SerializedName("domain")
    var domain: String? = null
    @SerializedName("fees")
    var fees: Any? = null
    @SerializedName("gateway_response")
    var gatewayResponse: String? = null
    @SerializedName("ip_address")
    var ipAddress: String? = null
    @SerializedName("log")
    var log: Log? = null
    @SerializedName("message")
    var message: Any? = null
    @SerializedName("metadata")
    var metadata: Long? = null
    @SerializedName("plan")
    var plan: String? = null
    @SerializedName("reference")
    var reference: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("transaction_date")
    var transactionDate: String? = null
}


/* Location:              /Users/ekeretepeter/Downloads/veritas/classes-dex2jar.jar!/veritas/exolve/com/veritas/models/PayStackData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */