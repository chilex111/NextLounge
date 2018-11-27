package merchant.com.our.nextlounge.model;

import com.google.gson.annotations.SerializedName;

public class History
{
  @SerializedName("message")
  private String mMessage;
  @SerializedName("time")
  private Long mTime;
  @SerializedName("type")
  private String mType;
  
  public String getMessage()
  {
    return this.mMessage;
  }
  
  public Long getTime()
  {
    return this.mTime;
  }
  
  public String getType()
  {
    return this.mType;
  }
  
  public void setMessage(String paramString)
  {
    this.mMessage = paramString;
  }
  
  public void setTime(Long paramLong)
  {
    this.mTime = paramLong;
  }
  
  public void setType(String paramString)
  {
    this.mType = paramString;
  }
}


/* Location:              /Users/ekeretepeter/Downloads/veritas/classes-dex2jar.jar!/veritas/exolve/com/veritas/models/History.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */