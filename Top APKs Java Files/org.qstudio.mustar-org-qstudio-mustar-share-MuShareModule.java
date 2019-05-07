package org.qstudio.mustar.share;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import com.crashlytics.android.Crashlytics;
import com.facebook.applinks.AppLinkData;
import com.facebook.applinks.AppLinkData.CompletionHandler;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.yandex.metrica.DeferredDeeplinkParametersListener;
import com.yandex.metrica.DeferredDeeplinkParametersListener.Error;
import com.yandex.metrica.YandexMetrica;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;
import org.qstudio.a.e.a;
import org.qstudio.mustar.MainApplication;
import org.qstudio.mustar.a.a;
import org.qstudio.mustar.a.b.a;
import org.qstudio.mustar.editor.a.k;
import rn.js.templates.RnModule;

public class MuShareModule
  extends RnModule
{
  private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
  private Future<Void> mCurrentSession;
  private long mLastProcessingProgressUpdateTime = 0L;
  
  public MuShareModule(ReactApplicationContext paramReactApplicationContext)
  {
    super(paramReactApplicationContext);
  }
  
  private boolean checkAppInstalled(String paramString)
  {
    paramString = getPackageByServiceTag(paramString);
    Iterator localIterator;
    switch (6.a[paramString.ordinal()])
    {
    case 2: 
    default: 
      localIterator = this.mReactContext.getPackageManager().getInstalledApplications(128).iterator();
    }
    while (localIterator.hasNext()) {
      if (TextUtils.equals(((ApplicationInfo)localIterator.next()).packageName, a.a(paramString)))
      {
        return true;
        paramString = new Intent("com.instagram.share.ADD_TO_STORY");
        paramString.setType("video/mp4");
        if (this.mReactContext.getPackageManager().resolveActivity(paramString, 0) != null) {}
        for (boolean bool = true;; bool = false) {
          return bool;
        }
      }
    }
    return false;
  }
  
  public static void copy(File paramFile1, File paramFile2)
  {
    paramFile1 = new FileInputStream(paramFile1);
    try
    {
      paramFile2 = new FileOutputStream(paramFile2);
      try
      {
        byte[] arrayOfByte = new byte['Ѐ'];
        for (;;)
        {
          int i = paramFile1.read(arrayOfByte);
          if (i <= 0) {
            break;
          }
          paramFile2.write(arrayOfByte, 0, i);
        }
        paramFile2 = finally;
      }
      finally {}
    }
    finally
    {
      paramFile1.close();
    }
    paramFile1.close();
  }
  
  private void generalShare(String paramString1, String paramString2, Promise paramPromise, Activity paramActivity, a paramA, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        localObject = MainApplication.a("Made_with_MuStar_app.mp4");
        if (((File)localObject).exists()) {
          ((File)localObject).delete();
        }
        ((File)localObject).createNewFile();
      }
      catch (IOException localIOException)
      {
        Object localObject;
        localIOException.printStackTrace();
        continue;
      }
      try
      {
        copy(new File(paramString1), (File)localObject);
        localObject = ((File)localObject).getAbsolutePath();
        paramString1 = (String)localObject;
        localObject = new Intent("android.intent.action.SEND");
        ((Intent)localObject).setType("video/mp4");
        ((Intent)localObject).setFlags(1);
        ((Intent)localObject).putExtra("android.intent.extra.STREAM", FileProvider.a(this.mReactContext, "org.qstudio.mustar.provider", new File(paramString1)));
        ((Intent)localObject).putExtra("android.intent.extra.TEXT", paramString2);
        if (a.a(paramA) != null)
        {
          ((Intent)localObject).setPackage(a.a(paramA));
          if ((paramBoolean) && (a.b(paramA) != null)) {
            ((Intent)localObject).setClassName(a.a(paramA), a.b(paramA));
          }
        }
      }
      catch (Exception localException)
      {
        try
        {
          paramActivity.startActivity((Intent)localObject);
          paramPromise.resolve(null);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          if ((!paramBoolean) || (a.b(paramA) == null)) {
            break label221;
          }
          generalShare(paramString1, paramString2, paramPromise, paramActivity, paramA, false);
          return;
          Crashlytics.logException(localActivityNotFoundException);
          paramPromise.reject(localActivityNotFoundException);
        }
        localException = localException;
        Crashlytics.logException(localException);
      }
    }
    label221:
  }
  
  private void getDeferredDeeplinkYandex(final a paramA)
  {
    YandexMetrica.requestDeferredDeeplinkParameters(new DeferredDeeplinkParametersListener()
    {
      public void onError(DeferredDeeplinkParametersListener.Error paramAnonymousError, String paramAnonymousString)
      {
        paramA.resolve(null);
      }
      
      public void onParametersLoaded(Map<String, String> paramAnonymousMap)
      {
        if (paramAnonymousMap != null)
        {
          Object localObject = "screen";
          if (paramAnonymousMap.containsKey("linktype")) {
            localObject = (String)paramAnonymousMap.get("linktype");
          }
          Uri.Builder localBuilder = new Uri.Builder();
          localBuilder.scheme("mustar").authority("mustar.me").appendPath("appcontent").appendPath((String)localObject);
          paramAnonymousMap = paramAnonymousMap.entrySet().iterator();
          while (paramAnonymousMap.hasNext())
          {
            localObject = (Map.Entry)paramAnonymousMap.next();
            localBuilder.appendQueryParameter((String)((Map.Entry)localObject).getKey(), (String)((Map.Entry)localObject).getValue());
          }
          paramA.resolve(localBuilder.build().toString());
          return;
        }
        paramA.resolve(null);
      }
    });
  }
  
  private a getPackageByServiceTag(String paramString)
  {
    a[] arrayOfA = a.values();
    int j = arrayOfA.length;
    int i = 0;
    while (i < j)
    {
      a localA = arrayOfA[i];
      if (TextUtils.equals(localA.name().toLowerCase(), paramString.toLowerCase())) {
        return localA;
      }
      i += 1;
    }
    return a.m;
  }
  
  private void shareToGallery(String paramString, Promise paramPromise)
  {
    for (;;)
    {
      try
      {
        File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Mustar_" + sDateFormat.format(new Date()) + ".mp4");
        if (!localFile.exists())
        {
          boolean bool = localFile.createNewFile();
          if (!bool) {
            break;
          }
        }
        try
        {
          copy(new File(paramString), localFile);
          if (Build.VERSION.SDK_INT >= 19)
          {
            paramString = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            paramString.setData(Uri.fromFile(localFile));
            this.mReactContext.sendBroadcast(paramString);
            paramPromise.resolve(null);
            return;
          }
        }
        catch (Exception paramString)
        {
          Crashlytics.logException(paramString);
          paramPromise.reject(paramString);
          return;
        }
        paramString = new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file://" + Environment.getExternalStorageDirectory()));
      }
      catch (IOException paramString)
      {
        Crashlytics.logException(paramString);
        paramPromise.reject(paramString);
        return;
      }
      this.mReactContext.sendBroadcast(paramString);
    }
    paramPromise.reject("can't create file", "can't create file");
  }
  
  private void shareToStories(Activity paramActivity, String paramString, Promise paramPromise)
  {
    Intent localIntent = new Intent("com.instagram.share.ADD_TO_STORY");
    localIntent.setType("video/mp4");
    localIntent.setData(FileProvider.a(paramActivity, "org.qstudio.mustar.provider", new File(paramString)));
    localIntent.setFlags(1);
    try
    {
      paramActivity.startActivity(localIntent);
      paramPromise.resolve(null);
      return;
    }
    catch (ActivityNotFoundException paramActivity)
    {
      Crashlytics.logException(paramActivity);
      paramPromise.reject(paramActivity);
    }
  }
  
  private void updateProcessingProgress(double paramDouble)
  {
    long l = System.currentTimeMillis();
    if (l - this.mLastProcessingProgressUpdateTime > 333L)
    {
      this.mLastProcessingProgressUpdateTime = l;
      dispatchEvent("shareProcessingProgress", rn.js.b.c().a("progress", paramDouble));
    }
  }
  
  @ReactMethod
  public void canShareWithService(String paramString, Promise paramPromise)
  {
    paramPromise.resolve(Boolean.valueOf(checkAppInstalled(paramString)));
  }
  
  @ReactMethod
  public void canShareWithServices(ReadableArray paramReadableArray, Promise paramPromise)
  {
    rn.js.b localB = rn.js.b.c();
    int i = 0;
    while (i < paramReadableArray.size())
    {
      localB.a(paramReadableArray.getString(i), checkAppInstalled(paramReadableArray.getString(i)));
      i += 1;
    }
    paramPromise.resolve(localB.e());
  }
  
  public Map<String, Object> getConstants()
  {
    HashMap localHashMap = new HashMap();
    try
    {
      localHashMap.put("build", this.mReactContext.getPackageManager().getPackageInfo(this.mReactContext.getPackageName(), 0).versionName);
      localHashMap.put("manufacturer", Build.MANUFACTURER);
      localHashMap.put("brand", Build.BRAND);
      localHashMap.put("model", Build.MODEL);
      localHashMap.put("androidVersion", Integer.toString(Build.VERSION.SDK_INT));
      return localHashMap;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Crashlytics.logException(localThrowable);
      }
    }
  }
  
  @ReactMethod
  public void getDeferredDeeplink(final Promise paramPromise)
  {
    paramPromise = new a(paramPromise);
    AppLinkData.fetchDeferredAppLinkData(getReactApplicationContext(), new AppLinkData.CompletionHandler()
    {
      public void onDeferredAppLinkDataFetched(AppLinkData paramAnonymousAppLinkData)
      {
        if ((paramAnonymousAppLinkData != null) && (paramAnonymousAppLinkData.getTargetUri() != null))
        {
          paramPromise.resolve(paramAnonymousAppLinkData.getTargetUri().toString());
          return;
        }
        MuShareModule.this.getDeferredDeeplinkYandex(paramPromise);
      }
    });
  }
  
  public String getName()
  {
    return "MuShare";
  }
  
  public boolean hasConstants()
  {
    return true;
  }
  
  @ReactMethod
  public void installApp(String paramString, Promise paramPromise)
  {
    Activity localActivity = this.mReactContext.getCurrentActivity();
    if (localActivity == null)
    {
      paramPromise.reject("no_activity", "No current activity");
      return;
    }
    paramString = getPackageByServiceTag(paramString);
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("market://details?id=" + a.a(paramString)));
    try
    {
      localActivity.startActivity(localIntent);
      paramPromise.resolve(null);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      Crashlytics.logException(paramString);
      paramPromise.reject(paramString);
    }
  }
  
  @ReactMethod
  public void processCancel()
  {
    if (this.mCurrentSession != null) {
      this.mCurrentSession.cancel(true);
    }
  }
  
  @ReactMethod
  public void processForBattleShare(ReadableMap paramReadableMap, int paramInt, final Promise paramPromise)
  {
    try
    {
      final File localFile = MainApplication.a("share_output", ".mp4");
      String str1 = null;
      if (paramReadableMap.hasKey("trackId")) {
        str1 = paramReadableMap.getString("trackId");
      }
      ReadableMap localReadableMap1 = paramReadableMap.getMap("video1");
      ReadableMap localReadableMap2 = paramReadableMap.getMap("video2");
      String str2 = localReadableMap1.getString("videoId");
      String str3 = localReadableMap1.getString("username");
      String str4 = localReadableMap2.getString("videoId");
      String str5 = localReadableMap2.getString("username");
      double d1 = localReadableMap1.getDouble("start");
      double d2 = localReadableMap1.getDouble("end");
      double d3 = localReadableMap2.getDouble("start");
      double d4 = localReadableMap2.getDouble("end");
      boolean bool = paramReadableMap.getBoolean("shouldAddHeader");
      paramReadableMap = org.qstudio.mustar.a.b.a().a(b.a.x);
      paramPromise = new a(paramPromise);
      paramReadableMap = new org.qstudio.a.b(localFile.getAbsolutePath(), paramInt, bool).a(str2, str3, d1, d2).b(str4, str5, d3, d4).b(paramReadableMap).a(str1).a();
      this.mCurrentSession = org.qstudio.a.e.a().a(paramReadableMap, this.mReactContext, new e.a()
      {
        public void a()
        {
          MuShareModule.access$202(MuShareModule.this, null);
          paramPromise.reject("Cancelled", "Cancelled");
        }
        
        public void a(double paramAnonymousDouble)
        {
          MuShareModule.this.updateProcessingProgress(paramAnonymousDouble);
        }
        
        public void a(Exception paramAnonymousException)
        {
          MuShareModule.access$202(MuShareModule.this, null);
          Crashlytics.logException(paramAnonymousException);
          paramPromise.reject(paramAnonymousException);
        }
        
        public void a(String paramAnonymousString)
        {
          MuShareModule.access$202(MuShareModule.this, null);
          if (localFile.length() > 0L)
          {
            paramPromise.resolve(paramAnonymousString);
            return;
          }
          paramPromise.reject("Null length file");
        }
      });
      return;
    }
    catch (IOException paramReadableMap)
    {
      Crashlytics.logException(paramReadableMap);
      paramPromise.reject(paramReadableMap);
    }
  }
  
  @ReactMethod
  public void processForInstagramShare(String paramString, int paramInt, Promise paramPromise)
  {
    resizeVideo(paramString, 1, 1, paramInt, paramPromise);
  }
  
  @ReactMethod
  public void processForShare(String paramString1, int paramInt, String paramString2, final Promise paramPromise)
  {
    try
    {
      final File localFile = MainApplication.a("share_output", ".mp4");
      paramPromise = new a(paramPromise);
      paramString1 = new org.qstudio.a.c(localFile.getAbsolutePath(), paramInt, paramString1).a(new k()).a(new org.qstudio.mustar.editor.a.b.e());
      if (!TextUtils.isEmpty(paramString2)) {
        paramString1.a(new org.qstudio.mustar.editor.a.b.b(paramString2));
      }
      this.mCurrentSession = org.qstudio.a.e.a().a(paramString1.a(), this.mReactContext, new e.a()
      {
        public void a()
        {
          MuShareModule.access$202(MuShareModule.this, null);
          paramPromise.reject("Cancelled", "Cancelled");
        }
        
        public void a(double paramAnonymousDouble)
        {
          MuShareModule.this.updateProcessingProgress(paramAnonymousDouble);
        }
        
        public void a(Exception paramAnonymousException)
        {
          MuShareModule.access$202(MuShareModule.this, null);
          Crashlytics.logException(paramAnonymousException);
          paramPromise.reject(paramAnonymousException);
        }
        
        public void a(String paramAnonymousString)
        {
          MuShareModule.access$202(MuShareModule.this, null);
          if (localFile.length() > 0L)
          {
            paramPromise.resolve(paramAnonymousString);
            return;
          }
          paramPromise.reject("Null length file");
        }
      });
      return;
    }
    catch (IOException paramString1)
    {
      Crashlytics.logException(paramString1);
      paramPromise.reject(paramString1);
    }
  }
  
  @ReactMethod
  public void resizeVideo(String paramString, int paramInt1, int paramInt2, int paramInt3, final Promise paramPromise)
  {
    try
    {
      final File localFile = MainApplication.a("share_output_resize", ".mp4");
      paramPromise = new a(paramPromise);
      k localK = new k();
      localK.a(new org.qstudio.mustar.editor.a.c(paramInt1 / paramInt2));
      paramString = new org.qstudio.a.c(localFile.getAbsolutePath(), new org.qstudio.a.b.b(paramInt3, paramInt1, paramInt2), paramString).a(localK);
      this.mCurrentSession = org.qstudio.a.e.a().a(paramString.a(), this.mReactContext, new e.a()
      {
        public void a()
        {
          MuShareModule.access$202(MuShareModule.this, null);
          paramPromise.reject("Cancelled", "Cancelled");
        }
        
        public void a(double paramAnonymousDouble)
        {
          MuShareModule.this.updateProcessingProgress(paramAnonymousDouble);
        }
        
        public void a(Exception paramAnonymousException)
        {
          MuShareModule.access$202(MuShareModule.this, null);
          Crashlytics.logException(paramAnonymousException);
          paramPromise.reject(paramAnonymousException);
        }
        
        public void a(String paramAnonymousString)
        {
          MuShareModule.access$202(MuShareModule.this, null);
          if (localFile.length() > 0L)
          {
            paramPromise.resolve(paramAnonymousString);
            return;
          }
          paramPromise.reject("Null length file");
        }
      });
      return;
    }
    catch (IOException paramString)
    {
      Crashlytics.logException(paramString);
      paramPromise.reject(paramString);
    }
  }
  
  @ReactMethod
  public void shareWithService(String paramString1, String paramString2, String paramString3, Promise paramPromise)
  {
    Activity localActivity = this.mReactContext.getCurrentActivity();
    if (localActivity == null)
    {
      paramPromise.reject("no_activity", "No current activity");
      return;
    }
    paramString1 = getPackageByServiceTag(paramString1);
    switch (6.a[paramString1.ordinal()])
    {
    default: 
      generalShare(paramString2, paramString3, paramPromise, localActivity, paramString1, true);
      return;
    case 1: 
      shareToGallery(paramString2, paramPromise);
      return;
    }
    shareToGallery(paramString2, paramPromise);
    localActivity.startActivity(this.mReactContext.getPackageManager().getLaunchIntentForPackage(a.a(paramString1)));
  }
  
  protected List<String> supportedEvents()
  {
    return Collections.singletonList("shareProcessingProgress");
  }
  
  private static enum a
  {
    private final String n;
    private final String o;
    
    private a(String paramString)
    {
      this(paramString, null);
    }
    
    private a(String paramString1, String paramString2)
    {
      this.n = paramString1;
      this.o = paramString2;
    }
  }
}
