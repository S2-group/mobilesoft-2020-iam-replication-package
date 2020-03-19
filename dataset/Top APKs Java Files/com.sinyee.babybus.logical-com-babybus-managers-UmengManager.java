package com.babybus.managers;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.babybus.app.App;
import com.babybus.app.C.Path;
import com.babybus.umeng.UmengAnalytics;
import com.babybus.utils.DateUtil;
import com.babybus.utils.IntegerUtil;
import com.babybus.utils.KeyChainUtil;
import com.babybus.utils.SDCardUtil;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.meituan.robust.PatchProxyResult;
import java.io.File;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class UmengManager
{
  public static ChangeQuickRedirect changeQuickRedirect;
  private String curData;
  
  public UmengManager() {}
  
  public static UmengManager get()
  {
    try
    {
      Object localObject1 = changeQuickRedirect;
      localObject1 = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject1, true, "get()", new Class[0], UmengManager.class);
      if (((PatchProxyResult)localObject1).isSupported)
      {
        localObject1 = (UmengManager)((PatchProxyResult)localObject1).result;
        return localObject1;
      }
      localObject1 = UmengManagerHolder.INSTANCE;
      return localObject1;
    }
    finally {}
  }
  
  private void sendEvent4BabybusApkCount()
  {
    int i = 0;
    Object localObject1 = changeQuickRedirect;
    Object localObject2 = Void.TYPE;
    if (PatchProxy.proxy(new Object[0], this, (ChangeQuickRedirect)localObject1, false, "sendEvent4BabybusApkCount()", new Class[0], (Class)localObject2).isSupported) {
      return;
    }
    localObject1 = App.get().getPackageManager();
    if (localObject1 == null) {
      return;
    }
    localObject1 = ((PackageManager)localObject1).getInstalledPackages(0);
    int k;
    for (int j = 0; i < ((List)localObject1).size(); j = k)
    {
      k = j;
      if (((PackageInfo)((List)localObject1).get(i)).packageName.contains("com.sinyee.babybus"))
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
    localObject1 = UmengAnalytics.get();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(j);
    ((StringBuilder)localObject2).append("");
    ((UmengAnalytics)localObject1).sendEvent("2143F24D68D47C232CC89B67B610EA89", ((StringBuilder)localObject2).toString());
  }
  
  private void sendExternalStorage()
  {
    Object localObject1 = changeQuickRedirect;
    Object localObject2 = Void.TYPE;
    if (PatchProxy.proxy(new Object[0], this, (ChangeQuickRedirect)localObject1, false, "sendExternalStorage()", new Class[0], (Class)localObject2).isSupported) {
      return;
    }
    localObject1 = KeyChainUtil.get().getKeyChain("SEND_UM_4_EXTERNAL_STORAGE_CAPACITY");
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
    Object localObject = changeQuickRedirect;
    Class localClass = Void.TYPE;
    if (PatchProxy.proxy(new Object[0], this, (ChangeQuickRedirect)localObject, false, "sendInstalledBabybusApkCount()", new Class[0], localClass).isSupported) {
      return;
    }
    localObject = KeyChainUtil.get().getKeyChain("SEND_UM_4_INSTALLED_BABYBUS_APP");
    if (!TextUtils.equals(this.curData, (CharSequence)localObject))
    {
      sendEvent4BabybusApkCount();
      KeyChainUtil.get().setKeyChain("SEND_UM_4_INSTALLED_BABYBUS_APP", this.curData);
    }
  }
  
  private void sendUnInstallBabybusApkCount()
  {
    int j = 0;
    Object localObject1 = changeQuickRedirect;
    Object localObject2 = Void.TYPE;
    if (PatchProxy.proxy(new Object[0], this, (ChangeQuickRedirect)localObject1, false, "sendUnInstallBabybusApkCount()", new Class[0], (Class)localObject2).isSupported) {
      return;
    }
    localObject1 = KeyChainUtil.get().getKeyChain("SEND_UM_4_UNINSTALL_BABYBUS_APP");
    if (!TextUtils.equals(this.curData, (CharSequence)localObject1))
    {
      int i;
      try
      {
        localObject1 = new File(C.Path.APK_PATH);
        i = j;
        if (((File)localObject1).exists()) {
          i = ((File)localObject1).listFiles().length;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        i = j;
      }
      UmengAnalytics localUmengAnalytics = UmengAnalytics.get();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(i);
      ((StringBuilder)localObject2).append("");
      localUmengAnalytics.sendEvent("09E0A15C6F3FD85E509BE89FD1FF8A8E", ((StringBuilder)localObject2).toString());
      KeyChainUtil.get().setKeyChain("SEND_UM_4_UNINSTALL_BABYBUS_APP", this.curData);
    }
  }
  
  public void sendInfo()
  {
    ChangeQuickRedirect localChangeQuickRedirect = changeQuickRedirect;
    Class localClass = Void.TYPE;
    if (PatchProxy.proxy(new Object[0], this, localChangeQuickRedirect, false, "sendInfo()", new Class[0], localClass).isSupported) {
      return;
    }
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
    public static ChangeQuickRedirect changeQuickRedirect;
    
    private UmengManagerHolder() {}
  }
}
