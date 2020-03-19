package com.veryfit2hr.second.common.presenter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import com.veryfit.multi.nativedatabase.FunctionInfos;
import com.veryfit.multi.nativedatabase.NoticeOnOff;
import com.veryfit.multi.nativeprotocol.ProtocolUtils;
import com.veryfit.multi.util.DebugLog;
import com.veryfit2hr.second.common.beans.AppInfo;
import com.veryfit2hr.second.common.utils.AppSharedPreferencesUtils;
import com.veryfit2hr.second.common.utils.Constant;
import com.veryfit2hr.second.common.utils.FileUtil;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AppNotifedPersenter
  implements Serializable
{
  public static final String APP_INFO_PATH = Constant.APP_ROOT_PATH + "/appInfo.dat";
  public static final boolean DEBUG = false;
  private static AppNotifedPersenter appMode = new AppNotifedPersenter();
  private List<AppInfo> appInfoList = new ArrayList();
  public Map<String, PackageInfo> packageInfoMap = new HashMap();
  
  private AppNotifedPersenter() {}
  
  private Bitmap drawable2Bitmap(Drawable paramDrawable)
  {
    if ((paramDrawable instanceof BitmapDrawable)) {
      return ((BitmapDrawable)paramDrawable).getBitmap();
    }
    if ((paramDrawable instanceof NinePatchDrawable))
    {
      int i = paramDrawable.getIntrinsicWidth();
      int j = paramDrawable.getIntrinsicHeight();
      if (paramDrawable.getOpacity() != -1) {}
      for (Object localObject = Bitmap.Config.ARGB_8888;; localObject = Bitmap.Config.RGB_565)
      {
        localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
        Canvas localCanvas = new Canvas((Bitmap)localObject);
        paramDrawable.setBounds(0, 0, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
        paramDrawable.draw(localCanvas);
        return localObject;
      }
    }
    return null;
  }
  
  public static AppNotifedPersenter getInstance()
  {
    return appMode;
  }
  
  private void queryAppInfo(Context paramContext)
  {
    readDataFromFile();
    paramContext = paramContext.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = paramContext.getInstalledPackages(0);
    DebugLog.d("............................packages:" + ((List)localObject1).size());
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
      String str1 = ((PackageInfo)localObject2).packageName;
      String str2 = (String)((PackageInfo)localObject2).applicationInfo.loadLabel(paramContext);
      if ((!str2.startsWith("com.")) && (!str2.startsWith("org.")))
      {
        this.packageInfoMap.put(str1, localObject2);
        localObject2 = new AppInfo();
        ((AppInfo)localObject2).setAppLabel(str2);
        ((AppInfo)localObject2).setPkgName(str1);
        ((AppInfo)localObject2).bitmaps = new byte[2];
        ((AppInfo)localObject2).isChecked = false;
        if (!localArrayList.contains(localObject2)) {
          localArrayList.add(localObject2);
        }
      }
    }
    if (this.appInfoList.isEmpty())
    {
      this.appInfoList.addAll(localArrayList);
      return;
    }
    this.appInfoList.retainAll(localArrayList);
    localArrayList.removeAll(this.appInfoList);
    this.appInfoList.addAll(localArrayList);
  }
  
  private void setBitmap(IQueryAppInfo paramIQueryAppInfo)
  {
    new AppNotifedPersenter.2(this, paramIQueryAppInfo).execute(new String[] { "" });
  }
  
  public List<AppInfo> getAppInfoList()
  {
    return this.appInfoList;
  }
  
  public List<AppInfo> getAppInfoListOrRead()
  {
    if (this.appInfoList.isEmpty()) {
      readDataFromFile();
    }
    return this.appInfoList;
  }
  
  public Bitmap getIcon(String paramString, PackageManager paramPackageManager)
  {
    return drawable2Bitmap(((PackageInfo)this.packageInfoMap.get(paramString)).applicationInfo.loadIcon(paramPackageManager));
  }
  
  public Drawable getIconDrawble(String paramString, PackageManager paramPackageManager)
  {
    try
    {
      paramString = paramPackageManager.getPackageInfo(paramString, 0).applicationInfo.loadIcon(paramPackageManager);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public boolean isAppNotifed()
  {
    this.appInfoList.clear();
    Object localObject = new File(APP_INFO_PATH);
    if (!((File)localObject).exists()) {}
    do
    {
      while (!((Iterator)localObject).hasNext())
      {
        return false;
        localObject = (List)FileUtil.readObjectFromFile(((File)localObject).getAbsolutePath());
        this.appInfoList.addAll((Collection)localObject);
        DebugLog.d(localObject.toString());
        localObject = this.appInfoList.iterator();
      }
    } while (!((AppInfo)((Iterator)localObject).next()).isChecked);
    return true;
  }
  
  public boolean isOpenNotifed()
  {
    Object localObject = ProtocolUtils.getInstance().getNotice();
    boolean bool1 = false;
    if (!AppSharedPreferencesUtils.getInstance().getToggleSwitchState()) {
      return false;
    }
    if (localObject == null) {
      return false;
    }
    if ((((NoticeOnOff)localObject).getCalendaronOff()) || (((NoticeOnOff)localObject).getEmailonOff()) || (((NoticeOnOff)localObject).getMsgonOff()) || (((NoticeOnOff)localObject).getFacebookonOff()) || (((NoticeOnOff)localObject).getWxonOff()) || (((NoticeOnOff)localObject).getQQonOff()) || (((NoticeOnOff)localObject).getTwitteronOff()) || (((NoticeOnOff)localObject).getWhatsapponOff()) || (((NoticeOnOff)localObject).getLinkedinonOff()) || (((NoticeOnOff)localObject).getInstagramonOff()) || (((NoticeOnOff)localObject).getMessengeronOff()) || (((NoticeOnOff)localObject).skype) || (((NoticeOnOff)localObject).lineOnOff) || (((NoticeOnOff)localObject).viberOnOff) || (((NoticeOnOff)localObject).kakaoTalkOnOff) || (((NoticeOnOff)localObject).vKontakteOnOff)) {
      bool1 = true;
    }
    localObject = ProtocolUtils.getInstance().getFunctionInfosByDb();
    boolean bool2 = bool1;
    if (localObject != null)
    {
      bool2 = bool1;
      if (((FunctionInfos)localObject).allAppNotice)
      {
        bool2 = bool1;
        if (!bool1) {
          bool2 = isAppNotifed();
        }
      }
    }
    DebugLog.d(".........isOpenNotifed:" + bool2);
    return bool2;
  }
  
  public void queryAppInfo(Context paramContext, IQueryAppInfo paramIQueryAppInfo)
  {
    if (paramContext == null) {}
    do
    {
      FunctionInfos localFunctionInfos;
      do
      {
        return;
        localFunctionInfos = ProtocolUtils.getInstance().getFunctionInfosByDb();
      } while ((localFunctionInfos != null) && (!localFunctionInfos.allAppNotice));
      if (getAppInfoList().isEmpty()) {
        break;
      }
    } while (paramIQueryAppInfo == null);
    paramIQueryAppInfo.queryAppInfoSuccess();
    return;
    new AppNotifedPersenter.1(this, paramContext, paramIQueryAppInfo).execute(new String[] { "" });
  }
  
  public void readDataFromFile()
  {
    this.appInfoList.clear();
    Object localObject = new File(APP_INFO_PATH);
    if (!((File)localObject).exists()) {
      return;
    }
    localObject = (List)FileUtil.readObjectFromFile(((File)localObject).getAbsolutePath());
    DebugLog.d(localObject.toString());
    this.appInfoList.addAll((Collection)localObject);
  }
  
  public void saveData()
  {
    FunctionInfos localFunctionInfos = ProtocolUtils.getInstance().getFunctionInfosByDb();
    if ((localFunctionInfos != null) && (localFunctionInfos.allAppNotice)) {
      FileUtil.writeObjectToFile(APP_INFO_PATH, this.appInfoList);
    }
  }
  
  public void setAppInfoList(List<AppInfo> paramList)
  {
    this.appInfoList = paramList;
  }
  
  public static abstract interface IQueryAppInfo
  {
    public abstract void queryAppInfoSuccess();
    
    public abstract void setBitmapSuccess();
  }
}
