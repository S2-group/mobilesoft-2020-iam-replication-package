package com.babybus.umeng;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.babybus.app.App;
import com.babybus.app.C.Path;
import com.babybus.utils.DateUtil;
import com.babybus.utils.IntegerUtil;
import com.babybus.utils.KeyChainUtil;
import com.babybus.utils.SDCardUtil;
import java.io.File;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class UmengManager
{
  private String curData;
  
  public UmengManager() {}
  
  public static UmengManager get()
  {
    try
    {
      UmengManager localUmengManager = UmengManagerHolder.INSTANCE;
      return localUmengManager;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private void sendEvent4BabybusApkCount()
  {
    Object localObject = App.get().getPackageManager();
    if (localObject == null) {
      return;
    }
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    int k;
    for (int j = 0; i < ((List)localObject).size(); j = k)
    {
      k = j;
      if (((PackageInfo)((List)localObject).get(i)).packageName.contains("com.sinyee.babybus"))
      {
        k = j;
        if (!TextUtils.equals("com.sinyee.babybus.recommendapp", App.get().packName))
        {
          k = j;
          if (!TextUtils.equals("com.sinyee.babybus.chants", App.get().packName))
          {
            k = j;
            if (!TextUtils.equals("com.sinyee.babybus.bbtime.android", App.get().packName)) {
              k = j + 1;
            }
          }
        }
      }
      i += 1;
    }
    localObject = UmengAnalytics.get();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(j);
    localStringBuilder.append("");
    ((UmengAnalytics)localObject).sendEvent("2143F24D68D47C232CC89B67B610EA89", localStringBuilder.toString());
  }
  
  private void sendExternalStorage()
  {
    Object localObject1 = KeyChainUtil.get().getKeyChain("SEND_UM_4_EXTERNAL_STORAGE_CAPACITY");
    Object localObject2;
    if (!TextUtils.equals(DateUtil.getCurDate(), (CharSequence)localObject1))
    {
      localObject2 = SDCardUtil.getSDAvailableSize();
      if (TextUtils.isEmpty((CharSequence)localObject2)) {
        return;
      }
    }
    for (localObject1 = "";; localObject1 = "")
    {
      StringBuilder localStringBuilder;
      try
      {
        if (((String)localObject2).contains("GB"))
        {
          localObject2 = ((String)localObject2).split("\\u002E");
          if (localObject2.length == 1)
          {
            localObject2 = StringUtils.substringBefore(localObject2[0], "GB");
            localObject1 = localObject2;
          }
          else
          {
            if (localObject2.length != 2) {
              continue;
            }
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(IntegerUtil.str2Int(localObject2[0]) + 1);
            localStringBuilder.append("");
            localObject2 = localStringBuilder.toString();
            localObject1 = localObject2;
          }
        }
        else
        {
          localObject1 = "1";
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        UmengAnalytics localUmengAnalytics = UmengAnalytics.get();
        localStringBuilder = new StringBuilder();
        localStringBuilder.append((String)localObject1);
        localStringBuilder.append("G");
        localUmengAnalytics.sendEvent("1B22F1DEC575205A72158001D919A3FE", localStringBuilder.toString());
        KeyChainUtil.get().setKeyChain("SEND_UM_4_EXTERNAL_STORAGE_CAPACITY", this.curData);
      }
      return;
    }
  }
  
  private void sendInstalledBabybusApkCount()
  {
    String str = KeyChainUtil.get().getKeyChain("SEND_UM_4_INSTALLED_BABYBUS_APP");
    if (!TextUtils.equals(this.curData, str))
    {
      sendEvent4BabybusApkCount();
      KeyChainUtil.get().setKeyChain("SEND_UM_4_INSTALLED_BABYBUS_APP", this.curData);
    }
  }
  
  private void sendUnInstallBabybusApkCount()
  {
    Object localObject = KeyChainUtil.get().getKeyChain("SEND_UM_4_UNINSTALL_BABYBUS_APP");
    if (!TextUtils.equals(this.curData, (CharSequence)localObject))
    {
      int j = 0;
      int i;
      try
      {
        localObject = new File(C.Path.APK_PATH);
        i = j;
        if (((File)localObject).exists()) {
          i = ((File)localObject).listFiles().length;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        i = j;
      }
      UmengAnalytics localUmengAnalytics = UmengAnalytics.get();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(i);
      localStringBuilder.append("");
      localUmengAnalytics.sendEvent("09E0A15C6F3FD85E509BE89FD1FF8A8E", localStringBuilder.toString());
      KeyChainUtil.get().setKeyChain("SEND_UM_4_UNINSTALL_BABYBUS_APP", this.curData);
    }
  }
  
  public String getTimeString(long paramLong)
  {
    if (paramLong < 60000L) {
      return "1分钟";
    }
    if (paramLong >= 1500000L) {
      return "25分钟以上";
    }
    if ((paramLong >= 900000L) && (paramLong < 1200000L)) {
      return "15分钟-20分钟";
    }
    if ((paramLong >= 1200000L) && (paramLong < 1500000L)) {
      return "20分钟-25分钟";
    }
    paramLong /= 60000L;
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(paramLong);
    ((StringBuilder)localObject1).append("");
    localObject1 = ((StringBuilder)localObject1).toString();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramLong + 1L);
    ((StringBuilder)localObject2).append("");
    localObject2 = ((StringBuilder)localObject2).toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append("-");
    localStringBuilder.append((String)localObject2);
    localStringBuilder.append("分钟");
    return localStringBuilder.toString();
  }
  
  public void sendInfo()
  {
    if (!App.writeSDCard) {
      return;
    }
    this.curData = DateUtil.getCurDate();
    sendExternalStorage();
    sendInstalledBabybusApkCount();
    sendUnInstallBabybusApkCount();
  }
  
  private static class UmengManagerHolder
  {
    private static final UmengManager INSTANCE = new UmengManager();
    
    private UmengManagerHolder() {}
  }
}
