package merchant.com.our.nextlounge.model;

import com.google.gson.annotations.SerializedName;

public class Authorization
{
  @SerializedName("authorization_code")
  private String mAuthorizationCode;
  @SerializedName("bank")
  private String mBank;
  @SerializedName("bin")
  private String mBin;
  @SerializedName("card_type")
  private String mCardType;
  @SerializedName("channel")
  private String mChannel;
  @SerializedName("country_code")
  private String mCountryCode;
  @SerializedName("exp_month")
  private String mExpMonth;
  @SerializedName("exp_year")
  private String mExpYear;
  @SerializedName("last4")
  private String mLast4;
  @SerializedName("reusable")
  private Boolean mReusable;
  @SerializedName("signature")
  private String mSignature;
  
  public String getAuthorizationCode()
  {
    return this.mAuthorizationCode;
  }
  
  public String getBank()
  {
    return this.mBank;
  }
  
  public String getBin()
  {
    return this.mBin;
  }
  
  public String getCardType()
  {
    return this.mCardType;
  }
  
  public String getChannel()
  {
    return this.mChannel;
  }
  
  public String getCountryCode()
  {
    return this.mCountryCode;
  }
  
  public String getExpMonth()
  {
    return this.mExpMonth;
  }
  
  public String getExpYear()
  {
    return this.mExpYear;
  }
  
  public String getLast4()
  {
    return this.mLast4;
  }
  
  public Boolean getReusable()
  {
    return this.mReusable;
  }
  
  public String getSignature()
  {
    return this.mSignature;
  }
  
  public void setAuthorizationCode(String paramString)
  {
    this.mAuthorizationCode = paramString;
  }
  
  public void setBank(String paramString)
  {
    this.mBank = paramString;
  }
  
  public void setBin(String paramString)
  {
    this.mBin = paramString;
  }
  
  public void setCardType(String paramString)
  {
    this.mCardType = paramString;
  }
  
  public void setChannel(String paramString)
  {
    this.mChannel = paramString;
  }
  
  public void setCountryCode(String paramString)
  {
    this.mCountryCode = paramString;
  }
  
  public void setExpMonth(String paramString)
  {
    this.mExpMonth = paramString;
  }
  
  public void setExpYear(String paramString)
  {
    this.mExpYear = paramString;
  }
  
  public void setLast4(String paramString)
  {
    this.mLast4 = paramString;
  }
  
  public void setReusable(Boolean paramBoolean)
  {
    this.mReusable = paramBoolean;
  }
  
  public void setSignature(String paramString)
  {
    this.mSignature = paramString;
  }
}


/* Location:              /Users/ekeretepeter/Downloads/veritas/classes-dex2jar.jar!/veritas/exolve/com/veritas/models/Authorization.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */