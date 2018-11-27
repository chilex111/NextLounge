package merchant.com.our.nextlounge.model;

import com.google.gson.annotations.SerializedName;

public class Customer
{
  @SerializedName("customer_code")
  private String mCustomerCode;
  @SerializedName("email")
  private String mEmail;
  @SerializedName("first_name")
  private String mFirstName;
  @SerializedName("id")
  private Long mId;
  @SerializedName("last_name")
  private String mLastName;
  
  public String getCustomerCode()
  {
    return this.mCustomerCode;
  }
  
  public String getEmail()
  {
    return this.mEmail;
  }
  
  public String getFirstName()
  {
    return this.mFirstName;
  }
  
  public Long getId()
  {
    return this.mId;
  }
  
  public String getLastName()
  {
    return this.mLastName;
  }
  
  public void setCustomerCode(String paramString)
  {
    this.mCustomerCode = paramString;
  }
  
  public void setEmail(String paramString)
  {
    this.mEmail = paramString;
  }
  
  public void setFirstName(String paramString)
  {
    this.mFirstName = paramString;
  }
  
  public void setId(Long paramLong)
  {
    this.mId = paramLong;
  }
  
  public void setLastName(String paramString)
  {
    this.mLastName = paramString;
  }
}


/* Location:              /Users/ekeretepeter/Downloads/veritas/classes-dex2jar.jar!/veritas/exolve/com/veritas/models/Customer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */