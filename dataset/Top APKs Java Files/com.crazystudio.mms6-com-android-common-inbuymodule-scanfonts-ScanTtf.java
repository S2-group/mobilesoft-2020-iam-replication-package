package com.android.common.inbuymodule.scanfonts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ScanTtf
{
  private static final String FLIPFONT = "com.monotype.android.font";
  public static final String SCAN_TTF_RESULT = "scan_ttf_fonts";
  public static final String SCAN_TTF_RESULT_FLIPFONT = "scan_ttf_fonts_flipfont";
  private static ScanTtf mInstance = null;
  Context mContext = null;
  List<String> mDownloardApp = new ArrayList();
  boolean mExit = false;
  Handler mHandler = new Handler();
  public ArrayList<TtfFontInfo> mListFont = new ArrayList();
  OnScanListener mScanUpdateListener = null;
  
  public ScanTtf(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public static List<String> getAllApps(Context paramContext, OnUpdateListener paramOnUpdateListener)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      Object localObject = localPackageInfo.packageName;
      paramContext = (Context)localObject;
      if (TextUtils.isEmpty((CharSequence)localObject)) {
        paramContext = localPackageInfo.applicationInfo.packageName;
      }
      localObject = paramContext;
      if (TextUtils.isEmpty(paramContext)) {
        localObject = localPackageInfo.applicationInfo.processName;
      }
      if (TextUtils.isEmpty((CharSequence)localObject)) {}
      for (;;)
      {
        i += 1;
        break;
        if (localObject != null)
        {
          localArrayList.add(localObject);
          if (paramOnUpdateListener != null) {
            paramOnUpdateListener.onUpdate(localArrayList);
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static List<String> getDownloadApps(Context paramContext, OnUpdateListener paramOnUpdateListener)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      int j = localPackageInfo.applicationInfo.flags;
      paramContext = localPackageInfo.applicationInfo;
      Object localObject;
      if ((j & 0x1) <= 0)
      {
        localObject = localPackageInfo.packageName;
        paramContext = (Context)localObject;
        if (TextUtils.isEmpty((CharSequence)localObject)) {
          paramContext = localPackageInfo.applicationInfo.packageName;
        }
        localObject = paramContext;
        if (TextUtils.isEmpty(paramContext)) {
          localObject = localPackageInfo.applicationInfo.processName;
        }
        if (!TextUtils.isEmpty((CharSequence)localObject)) {
          break label128;
        }
      }
      for (;;)
      {
        i += 1;
        break;
        label128:
        if (localObject != null)
        {
          localArrayList.add(localObject);
          if (paramOnUpdateListener != null) {
            paramOnUpdateListener.onUpdate(localArrayList);
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static ScanTtf getInstance(Context paramContext)
  {
    if (mInstance == null) {
      mInstance = new ScanTtf(paramContext);
    }
    return mInstance;
  }
  
  private String getOutput()
  {
    String str = "follow font info:";
    int i = 0;
    while (i < this.mListFont.size())
    {
      Log.e("Font", "pkg:" + ((TtfFontInfo)this.mListFont.get(i)).pkg + "file:" + ((TtfFontInfo)this.mListFont.get(i)).file);
      str = str + "pkg:" + ((TtfFontInfo)this.mListFont.get(i)).pkg + "file:" + ((TtfFontInfo)this.mListFont.get(i)).file + "\n";
      i += 1;
    }
    return str;
  }
  
  public static List<String> getSystemApps(Context paramContext, OnUpdateListener paramOnUpdateListener)
  {
    localArrayList = new ArrayList();
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageManager();
    localObject = localPackageManager.queryIntentActivities((Intent)localObject, 0);
    int i = 0;
    try
    {
      while (i < ((List)localObject).size())
      {
        int j = paramContext.getApplicationInfo(((ResolveInfo)((List)localObject).get(i)).activityInfo.packageName, 0).flags;
        int k = ((ResolveInfo)((List)localObject).get(i)).activityInfo.flags;
        if ((j & 0x1) != 0)
        {
          localArrayList.add(((ResolveInfo)((List)localObject).get(i)).activityInfo.packageName);
          if (paramOnUpdateListener != null) {
            paramOnUpdateListener.onUpdate(localArrayList);
          }
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private Typeface getTtfFont(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = Typeface.createFromAsset(paramContext.getResourcesForApplication(paramString1).getAssets(), paramString2);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private Typeface getTtfFont(Resources paramResources, String paramString)
  {
    try
    {
      paramResources = Typeface.createFromAsset(paramResources.getAssets(), paramString);
      return paramResources;
    }
    catch (Exception paramResources)
    {
      paramResources.printStackTrace();
    }
    return null;
  }
  
  private boolean needScan(String paramString)
  {
    if ((paramString.equalsIgnoreCase("images")) || (paramString.length() <= 0) || ((paramString.equalsIgnoreCase("kioskmode") | paramString.equalsIgnoreCase("sounds") | paramString.equalsIgnoreCase("armeabi") | paramString.equalsIgnoreCase("x86") | paramString.equalsIgnoreCase("mips"))) || (paramString.equalsIgnoreCase("webkit"))) {}
    while ((!paramString.equalsIgnoreCase("font")) && (!paramString.equalsIgnoreCase("fonts"))) {
      return false;
    }
    return true;
  }
  
  private void saveTtfResult(Context paramContext, String paramString)
  {
    paramString = "";
    int i = 0;
    while (i < this.mListFont.size())
    {
      paramString = paramString + "" + ((TtfFontInfo)this.mListFont.get(i)).pkg + ":" + ((TtfFontInfo)this.mListFont.get(i)).file + "\n";
      i += 1;
    }
    writeData(paramContext, paramString, "scan_ttf_fonts");
  }
  
  private void scanAssets(Resources paramResources, String paramString1, String paramString2, String paramString3)
  {
    for (;;)
    {
      String str;
      try
      {
        String[] arrayOfString = paramResources.getAssets().list(paramString1);
        if (paramString1.length() == 0) {
          Log.e("TAG", "size:" + arrayOfString.length + "pkg:" + paramString3);
        }
        int i = 0;
        if (i < arrayOfString.length)
        {
          str = arrayOfString[i];
          if ((str.contains(".")) || (!needScan(str))) {
            break label212;
          }
          if (paramString1.length() == 0)
          {
            scanAssets(paramResources, str, paramString2 + str + "/", paramString3);
            i += 1;
          }
        }
        else
        {
          return;
        }
      }
      catch (IOException paramResources) {}
      scanAssets(paramResources, paramString1 + "/" + str, paramString2 + "/" + str + "/", paramString3);
      continue;
      label212:
      if (str.endsWith(".ttf")) {
        this.mListFont.add(new TtfFontInfo(paramString3, paramString2 + str));
      }
    }
  }
  
  private void scanTtfFonts(Context paramContext, final List<String> paramList)
  {
    paramContext = paramContext.getPackageManager();
    final int i = 0;
    while (i < paramList.size())
    {
      try
      {
        if (((String)paramList.get(i)).equalsIgnoreCase("com.monotype.android.font") != true)
        {
          scanAssets(paramContext.getResourcesForApplication((String)paramList.get(i)), "", "", (String)paramList.get(i));
          if (this.mExit) {
            return;
          }
          this.mHandler.post(new Runnable()
          {
            public void run()
            {
              if (ScanTtf.this.mScanUpdateListener != null) {
                ScanTtf.this.mScanUpdateListener.onUpdate(ScanTtf.this.mListFont, (String)paramList.get(i));
              }
            }
          });
        }
      }
      catch (Exception localException) {}
      i += 1;
    }
  }
  
  private static void writeData(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.openFileOutput(paramString2, 0);
      paramContext.write(paramString1.getBytes());
      paramContext.close();
      return;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public void cancel()
  {
    this.mExit = true;
  }
  
  public List<TtfFontInfo> getFonts()
  {
    return this.mListFont;
  }
  
  public void scanFlipFont(final List<String> paramList)
  {
    PackageManager localPackageManager = this.mContext.getPackageManager();
    final int i = 0;
    while (i < paramList.size())
    {
      if (((String)paramList.get(i)).contains("com.monotype.android.font")) {}
      try
      {
        scanAssets(localPackageManager.getResourcesForApplication((String)paramList.get(i)), "", "", (String)paramList.get(i));
        if (this.mExit) {
          return;
        }
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            if (ScanTtf.this.mScanUpdateListener != null) {
              ScanTtf.this.mScanUpdateListener.onUpdate(ScanTtf.this.mListFont, (String)paramList.get(i));
            }
          }
        });
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
      i += 1;
    }
    saveTtfResult(this.mContext, "scan_ttf_fonts_flipfont");
  }
  
  public void setUpdateListener(OnScanListener paramOnScanListener)
  {
    this.mScanUpdateListener = paramOnScanListener;
  }
  
  public void startScan(final String paramString)
  {
    this.mExit = false;
    new Thread(new Runnable()
    {
      public void run()
      {
        ScanTtf.this.mListFont.clear();
        List localList = ScanTtf.getAllApps(ScanTtf.this.mContext, null);
        if ((paramString != null) && (paramString.equalsIgnoreCase("simple"))) {
          ScanTtf.this.scanFlipFont(localList);
        }
        for (;;)
        {
          ScanTtf.this.saveTtfResult(ScanTtf.this.mContext, "scan_ttf_fonts");
          if (ScanTtf.this.mScanUpdateListener != null) {
            ScanTtf.this.mScanUpdateListener.onUpdate(null, "-1");
          }
          return;
          ScanTtf.this.scanFlipFont(localList);
          ScanTtf.this.scanTtfFonts(ScanTtf.this.mContext, localList);
        }
      }
    }).start();
  }
  
  public static abstract interface OnScanListener
  {
    public abstract void onUpdate(List<TtfFontInfo> paramList, String paramString);
  }
  
  public static abstract interface OnUpdateListener
  {
    public abstract void onUpdate(List<String> paramList);
  }
}
