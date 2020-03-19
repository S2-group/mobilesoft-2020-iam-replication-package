package com.babybus.plugin.googlead.logic;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.babybus.app.App;
import com.babybus.managers.TokenManager;
import com.babybus.plugin.googlead.logic.bo.BBGADStartupBo;
import com.babybus.plugin.googlead.logic.bo.BBGADWelcomeReBo;
import com.babybus.umeng.BBUmengAnalytics;
import com.babybus.utils.ADUtil;
import com.babybus.utils.LogUtil;
import com.babybus.utils.SpUtil;
import com.babybus.utils.UIUtil;
import com.babybus.utils.UrlUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class BBGADSystem
{
  private static final BBGADSystem INSTANCE = new BBGADSystem();
  public static String mJsonFileUrl;
  public static String mResourceUrl = "";
  private String mAppKey;
  private String mChannel;
  private String mDefaultState;
  public List<String> mInstalled;
  private int mLanguageInt;
  private int mPlatform;
  private BBGADStartupBo mStartupBo;
  public String mUserAge;
  private BBGADWelcomeReBo mWelcomeReBo;
  public String mZipUrl;
  
  public BBGADSystem() {}
  
  public static BBGADSystem get()
  {
    return INSTANCE;
  }
  
  private String getEnData4List(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("list", paramString);
      paramString = TokenManager.get().AESEnData(localJSONObject.toString());
      return paramString;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  private List<String> getInstalled()
  {
    List localList = App.get().getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    int j = localList.size();
    int i = 0;
    while (i < j)
    {
      String str = ((PackageInfo)localList.get(i)).packageName;
      if (str.contains("com.sinyee.babybus")) {
        localArrayList.add(str);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  private int getLanguageInt()
  {
    int j = UIUtil.getLanguageInt();
    int i;
    if (j == 1) {
      i = 100;
    }
    do
    {
      return i;
      i = j;
    } while (j <= 11);
    return 3;
  }
  
  private String getResourceUrl()
  {
    Object localObject;
    if (App.get().debug) {
      localObject = "http://beta-pic.baby-bus.com/";
    }
    String str;
    do
    {
      return localObject;
      str = SpUtil.getString("resource_url", "");
      localObject = str;
    } while (!TextUtils.isEmpty(str));
    return UrlUtil.getUrl4ResourceUrl();
  }
  
  private String getUrl4Json()
  {
    return UrlUtil.getUrl4BabybusAd() + "api.php/v4/get_promote";
  }
  
  private String getUrlBabybusAdList()
  {
    return UrlUtil.getUrl4BabybusAd() + "/api.php/v4/get_promote_list";
  }
  
  private void initStartUp()
  {
    if (this.mStartupBo == null) {
      this.mStartupBo = new BBGADStartupBo();
    }
  }
  
  private void initWelcomeRe()
  {
    if (this.mWelcomeReBo == null) {
      this.mWelcomeReBo = new BBGADWelcomeReBo();
    }
  }
  
  private void requestAdList(int paramInt)
  {
    sendUmeng4GetJson(paramInt);
    switch (paramInt)
    {
    case 2: 
    case 3: 
    default: 
      return;
    case 1: 
      initStartUp();
      this.mStartupBo.handlerJsonFile();
      return;
    }
    initWelcomeRe();
    this.mWelcomeReBo.handlerJsonFile();
  }
  
  private void sendUmeng4GetJson(int paramInt)
  {
    String str3 = "";
    String str1 = str3;
    switch (paramInt)
    {
    default: 
      str1 = str3;
    }
    for (;;)
    {
      try
      {
        if (TextUtils.isEmpty(str1)) {
          break label66;
        }
        BBUmengAnalytics.get().sendEvent(str1, "");
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      str1 = "57EEB48C370010F7EDAADEDB526F61AB";
      continue;
      label66:
      return;
      String str2 = "E0908C9F1C9631DEE8A8176AEDF3E411";
    }
  }
  
  private String setZipUrl()
  {
    Object localObject;
    if (App.get().debug) {
      localObject = "http://beta.admin.baby-bus.com";
    }
    String str;
    do
    {
      return localObject;
      str = SpUtil.getString("zip_url", "");
      localObject = str;
    } while (!TextUtils.isEmpty(str));
    return UrlUtil.getUrl4DefaultZip();
  }
  
  public String getADData(Integer paramInteger)
  {
    switch (paramInteger.intValue())
    {
    case 2: 
    case 3: 
    default: 
      return "";
    case 1: 
      initStartUp();
      return this.mStartupBo.getData();
    }
    initWelcomeRe();
    return this.mWelcomeReBo.getData();
  }
  
  public String getEnDate(int paramInt)
  {
    String str = this.mAppKey + "|" + this.mPlatform + "|" + this.mChannel + "|" + this.mLanguageInt + "|" + paramInt + "|" + this.mUserAge;
    LogUtil.e("广告：" + paramInt + "请求参数：" + str);
    return getEnData4List(str);
  }
  
  public String getJsonZipName(int paramInt)
  {
    return this.mPlatform + "_" + App.get().channel + "_" + this.mLanguageInt + "_" + paramInt + "_" + this.mUserAge;
  }
  
  public void requestToken()
  {
    if (!TokenManager.get().getIsRequestToken()) {
      TokenManager.get().requestPubKey4Server();
    }
  }
  
  public void sendUmeng4GetJsonFail(String paramString)
  {
    ADUtil.sendUmeng4RequestJsonFauilt(paramString);
  }
  
  public void sendUmeng4GetJsonSuccess(int paramInt)
  {
    String str3 = "";
    String str1 = str3;
    switch (paramInt)
    {
    default: 
      str1 = str3;
    }
    for (;;)
    {
      try
      {
        if (TextUtils.isEmpty(str1)) {
          break label65;
        }
        BBUmengAnalytics.get().sendEvent(str1);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      str1 = "123FA7E343350A100845DDDE40D51916";
      continue;
      label65:
      return;
      String str2 = "DBE736C7A414DC8C788D10A40D29C059";
    }
  }
  
  public void startUp()
  {
    this.mAppKey = App.get().getPackageName();
    this.mChannel = App.get().channel;
    this.mUserAge = "0";
    this.mPlatform = Integer.parseInt("2");
    this.mLanguageInt = getLanguageInt();
    this.mInstalled = getInstalled();
    this.mZipUrl = setZipUrl();
    mResourceUrl = getResourceUrl();
    mJsonFileUrl = getUrl4Json();
    this.mDefaultState = ADUtil.getDefaultState();
    if ("1".equals(SpUtil.getString("startup_state", this.mDefaultState))) {
      requestAdList(1);
    }
    if (!"0".equals(SpUtil.getString("welcome_re_state", this.mDefaultState))) {
      requestAdList(4);
    }
  }
  
  public class MediaType
  {
    public static final String ALL_AGE = "2";
    public static final String INDEPENDENT = "1";
    public static final String SPECIFIED_AGE = "3";
    
    public MediaType() {}
  }
  
  public class OpenType
  {
    public static final String AUDIO = "4";
    public static final String DOWNLOAD = "1";
    public static final String NOTHING = "0";
    public static final String SOURCECODE = "5";
    public static final String URL = "2";
    public static final String VIDEO = "3";
    
    public OpenType() {}
  }
  
  public class SaveData
  {
    public static final String SELFAD = "G_selfad";
    
    public SaveData() {}
  }
}
