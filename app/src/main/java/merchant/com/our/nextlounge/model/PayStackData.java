package merchant.com.our.nextlounge.model;

import com.google.gson.annotations.SerializedName;

public class PayStackData
{
  @SerializedName("amount")
  private Long mAmount;
  @SerializedName("authorization")
  private Authorization mAuthorization;
  @SerializedName("channel")
  private String mChannel;
  @SerializedName("currency")
  private String mCurrency;
  @SerializedName("customer")
  private Customer mCustomer;
  @SerializedName("domain")
  private String mDomain;
  @SerializedName("fees")
  private Object mFees;
  @SerializedName("gateway_response")
  private String mGatewayResponse;
  @SerializedName("ip_address")
  private String mIpAddress;
  @SerializedName("log")
  private Log mLog;
  @SerializedName("message")
  private Object mMessage;
  @SerializedName("metadata")
  private Long mMetadata;
  @SerializedName("plan")
  private String mPlan;
  @SerializedName("reference")
  private String mReference;
  @SerializedName("status")
  private String mStatus;
  @SerializedName("transaction_date")
  private String mTransactionDate;
  
  public Long getAmount()
  {
    return this.mAmount;
  }
  
  public Authorization getAuthorization()
  {
    return this.mAuthorization;
  }
  
  public String getChannel()
  {
    return this.mChannel;
  }
  
  public String getCurrency()
  {
    return this.mCurrency;
  }
  
  public Customer getCustomer()
  {
    return this.mCustomer;
  }
  
  public String getDomain()
  {
    return this.mDomain;
  }
  
  public Object getFees()
  {
    return this.mFees;
  }
  
  public String getGatewayResponse()
  {
    return this.mGatewayResponse;
  }
  
  public String getIpAddress()
  {
    return this.mIpAddress;
  }
  
  public Log getLog()
  {
    return this.mLog;
  }
  
  public Object getMessage()
  {
    return this.mMessage;
  }
  
  public Long getMetadata()
  {
    return this.mMetadata;
  }
  
  public String getPlan()
  {
    return this.mPlan;
  }
  
  public String getReference()
  {
    return this.mReference;
  }
  
  public String getStatus()
  {
    return this.mStatus;
  }
  
  public String getTransactionDate()
  {
    return this.mTransactionDate;
  }
  
  public void setAmount(Long paramLong)
  {
    this.mAmount = paramLong;
  }
  
  public void setAuthorization(Authorization paramAuthorization)
  {
    this.mAuthorization = paramAuthorization;
  }
  
  public void setChannel(String paramString)
  {
    this.mChannel = paramString;
  }
  
  public void setCurrency(String paramString)
  {
    this.mCurrency = paramString;
  }
  
  public void setCustomer(Customer paramCustomer)
  {
    this.mCustomer = paramCustomer;
  }
  
  public void setDomain(String paramString)
  {
    this.mDomain = paramString;
  }
  
  public void setFees(Object paramObject)
  {
    this.mFees = paramObject;
  }
  
  public void setGatewayResponse(String paramString)
  {
    this.mGatewayResponse = paramString;
  }
  
  public void setIpAddress(String paramString)
  {
    this.mIpAddress = paramString;
  }
  
  public void setLog(Log paramLog)
  {
    this.mLog = paramLog;
  }
  
  public void setMessage(Object paramObject)
  {
    this.mMessage = paramObject;
  }
  
  public void setMetadata(Long paramLong)
  {
    this.mMetadata = paramLong;
  }
  
  public void setPlan(String paramString)
  {
    this.mPlan = paramString;
  }
  
  public void setReference(String paramString)
  {
    this.mReference = paramString;
  }
  
  public void setStatus(String paramString)
  {
    this.mStatus = paramString;
  }
  
  public void setTransactionDate(String paramString)
  {
    this.mTransactionDate = paramString;
  }
}


/* Location:              /Users/ekeretepeter/Downloads/veritas/classes-dex2jar.jar!/veritas/exolve/com/veritas/models/PayStackData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */