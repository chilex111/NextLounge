package merchant.com.our.nextlounge.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Log
{
  @SerializedName("attempts")
  private Long mAttempts;
  @SerializedName("authentication")
  private Object mAuthentication;
  @SerializedName("channel")
  private Object mChannel;
  @SerializedName("errors")
  private Long mErrors;
  @SerializedName("history")
  private List<History> mHistory;
  @SerializedName("input")
  private List<Object> mInput;
  @SerializedName("mobile")
  private Boolean mMobile;
  @SerializedName("success")
  private Boolean mSuccess;
  @SerializedName("time_spent")
  private Long mTimeSpent;
  
  public Long getAttempts()
  {
    return this.mAttempts;
  }
  
  public Object getAuthentication()
  {
    return this.mAuthentication;
  }
  
  public Object getChannel()
  {
    return this.mChannel;
  }
  
  public Long getErrors()
  {
    return this.mErrors;
  }
  
  public List<History> getHistory()
  {
    return this.mHistory;
  }
  
  public List<Object> getInput()
  {
    return this.mInput;
  }
  
  public Boolean getMobile()
  {
    return this.mMobile;
  }
  
  public Boolean getSuccess()
  {
    return this.mSuccess;
  }
  
  public Long getTimeSpent()
  {
    return this.mTimeSpent;
  }
  
  public void setAttempts(Long paramLong)
  {
    this.mAttempts = paramLong;
  }
  
  public void setAuthentication(Object paramObject)
  {
    this.mAuthentication = paramObject;
  }
  
  public void setChannel(Object paramObject)
  {
    this.mChannel = paramObject;
  }
  
  public void setErrors(Long paramLong)
  {
    this.mErrors = paramLong;
  }
  
  public void setHistory(List<History> paramList)
  {
    this.mHistory = paramList;
  }
  
  public void setInput(List<Object> paramList)
  {
    this.mInput = paramList;
  }
  
  public void setMobile(Boolean paramBoolean)
  {
    this.mMobile = paramBoolean;
  }
  
  public void setSuccess(Boolean paramBoolean)
  {
    this.mSuccess = paramBoolean;
  }
  
  public void setTimeSpent(Long paramLong)
  {
    this.mTimeSpent = paramLong;
  }
}


/* Location:              /Users/ekeretepeter/Downloads/veritas/classes-dex2jar.jar!/veritas/exolve/com/veritas/models/Log.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */