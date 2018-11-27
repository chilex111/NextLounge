package merchant.com.our.nextlounge.model;

import com.google.gson.annotations.SerializedName;

public class PayStackModel
{
  @SerializedName("data")
  private PayStackData mData;
  @SerializedName("message")
  private String mMessage;
  @SerializedName("status")
  private Boolean mStatus;
  
  public PayStackData getData()
  {
    return this.mData;
  }
  
  public String getMessage()
  {
    return this.mMessage;
  }
  
  public Boolean getStatus()
  {
    return this.mStatus;
  }
  
  public void setData(PayStackData paramData)
  {
    this.mData = paramData;
  }
  
  public void setMessage(String paramString)
  {
    this.mMessage = paramString;
  }
  
  public void setStatus(Boolean paramBoolean)
  {
    this.mStatus = paramBoolean;
  }
}


/* Location:              /Users/ekeretepeter/Downloads/veritas/classes-dex2jar.jar!/veritas/exolve/com/veritas/models/PayStackModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */