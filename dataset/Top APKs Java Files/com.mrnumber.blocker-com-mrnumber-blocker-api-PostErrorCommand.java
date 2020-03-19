package com.mrnumber.blocker.api;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import com.mrnumber.blocker.MrNumberPrefs;
import com.mrnumber.blocker.util.NavigationLogger;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;

public class PostErrorCommand
  extends PostStringApiCommand<String>
{
  static final String TAB = "  ";
  
  public PostErrorCommand(String paramString, AuthToken paramAuthToken)
  {
    super(ApiCommand.makeApiUrl(MrNumberPrefs.getServer(), "/api/v3/action/error"), "text/plain", paramString, paramAuthToken);
  }
  
  private static CharSequence appLogString(CharSequence paramCharSequence, String paramString1, String paramString2)
  {
    StringBuffer localStringBuffer = new StringBuffer("  ");
    localStringBuffer.append(paramCharSequence);
    localStringBuffer.append(": ");
    localStringBuffer.append(paramString1);
    localStringBuffer.append("/");
    localStringBuffer.append(paramString2);
    localStringBuffer.append("\n");
    return localStringBuffer;
  }
  
  public static StringBuffer appendDeviceInfo(Context paramContext, StringBuffer paramStringBuffer)
  {
    Object localObject1;
    Object localObject2;
    for (;;)
    {
      try
      {
        paramStringBuffer.append("OS INFO\n");
        appendProperty(paramStringBuffer, "BOARD", Build.BOARD);
        appendProperty(paramStringBuffer, "BRAND", Build.BRAND);
        appendProperty(paramStringBuffer, "DEVICE", Build.DEVICE);
        appendProperty(paramStringBuffer, "FINGERPRINT", Build.FINGERPRINT);
        appendProperty(paramStringBuffer, "ID", Build.ID);
        appendProperty(paramStringBuffer, "MANUFACTURER", Build.MANUFACTURER);
        appendProperty(paramStringBuffer, "MODEL", Build.MODEL);
        appendProperty(paramStringBuffer, "PRODUCT", Build.PRODUCT);
        appendProperty(paramStringBuffer, "TAGS", Build.TAGS);
        appendProperty(paramStringBuffer, "VERSION.CODENAME", Build.VERSION.CODENAME);
        appendProperty(paramStringBuffer, "VERSION.RELEASE", Build.VERSION.RELEASE);
        localObject1 = (ActivityManager)paramContext.getSystemService("activity");
        appendProperty(paramStringBuffer, "memoryClass", Integer.toString(((ActivityManager)localObject1).getMemoryClass()));
        localObject2 = new ActivityManager.MemoryInfo();
        ((ActivityManager)localObject1).getMemoryInfo((ActivityManager.MemoryInfo)localObject2);
        long l1 = ((ActivityManager.MemoryInfo)localObject2).availMem;
        boolean bool = ((ActivityManager.MemoryInfo)localObject2).lowMemory;
        long l2 = ((ActivityManager.MemoryInfo)localObject2).threshold;
        appendProperty(paramStringBuffer, "availMem", Long.toString(l1));
        appendProperty(paramStringBuffer, "lowMem", Boolean.toString(bool));
        appendProperty(paramStringBuffer, "threshold", Long.toString(l2));
        localObject1 = Environment.getExternalStorageState();
        bool = "mounted".equals(localObject1);
        appendProperty(paramStringBuffer, "extStorageState", (String)localObject1);
        appendProperty(paramStringBuffer, "extIsMounted", Boolean.toString(bool));
        if (!MrNumberPrefs.getDebugLogging()) {
          break label445;
        }
        localObject1 = paramContext.getPackageManager();
        Log.i("mrn/debug", "-- Installed Packages --\n");
        localObject2 = ((PackageManager)localObject1).getInstalledPackages(0).iterator();
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        paramContext = (PackageInfo)((Iterator)localObject2).next();
        String str1 = paramContext.packageName;
        String str2 = paramContext.versionName;
        if (paramContext.applicationInfo == null)
        {
          paramContext = "<no app info>";
          Log.i("mrn/debug", appLogString(paramContext, str1, str2).toString());
        }
        else
        {
          paramContext = paramContext.applicationInfo.loadLabel((PackageManager)localObject1);
        }
      }
      catch (Throwable paramContext)
      {
        appendThrowable(paramStringBuffer, "", paramContext);
        return paramStringBuffer;
      }
    }
    Log.i("mrn/debug", "-- Broadcast Receivers --\n");
    paramContext = ((PackageManager)localObject1).queryBroadcastReceivers(new Intent("android.provider.Telephony.SMS_RECEIVED"), 0).iterator();
    while (paramContext.hasNext())
    {
      localObject2 = (ResolveInfo)paramContext.next();
      int i = ((ResolveInfo)localObject2).preferredOrder;
      int j = ((ResolveInfo)localObject2).priority;
      Log.i("mrn/debug", receiverLogString(((ResolveInfo)localObject2).loadLabel((PackageManager)localObject1), j, i).toString());
    }
    label445:
    appendProperty(paramStringBuffer, "navigationSteps", NavigationLogger.getNavigationInformation());
    return paramStringBuffer;
  }
  
  private static void appendProperty(StringBuffer paramStringBuffer, String paramString1, String paramString2)
  {
    paramStringBuffer.append("  ");
    paramStringBuffer.append(paramString1);
    paramStringBuffer.append(": ");
    paramStringBuffer.append(paramString2);
    paramStringBuffer.append("\n");
  }
  
  private static CharSequence appendThrowable(StringBuffer paramStringBuffer, String paramString, Throwable paramThrowable)
  {
    paramStringBuffer.append(paramString);
    paramStringBuffer.append(paramThrowable.toString());
    paramStringBuffer.append(" ");
    paramStringBuffer.append(paramThrowable.getMessage());
    paramStringBuffer.append("\n");
    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    int j = arrayOfStackTraceElement.length;
    int i = 0;
    while (i < j)
    {
      StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i];
      paramStringBuffer.append(paramString);
      paramStringBuffer.append("  ");
      paramStringBuffer.append(localStackTraceElement.toString());
      paramStringBuffer.append("\n");
      i += 1;
    }
    if (paramThrowable.getCause() != null)
    {
      paramStringBuffer.append("Caused by ");
      appendThrowable(paramStringBuffer, paramString + "  ", paramThrowable.getCause());
    }
    return paramStringBuffer;
  }
  
  public static PostErrorCommand make(Context paramContext, CharSequence paramCharSequence)
  {
    paramCharSequence = new StringBuffer(paramCharSequence);
    appendDeviceInfo(paramContext, paramCharSequence);
    try
    {
      paramContext = MessageDigest.getInstance("MD5");
      paramContext.update("12345".getBytes());
      String str = paramCharSequence.toString();
      paramContext.update(str.getBytes());
      paramContext = new PostErrorCommand(str, new AuthToken()
      {
        public String getHeader()
        {
          return this.val$digest;
        }
      });
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      appendThrowable(paramCharSequence, "", paramContext);
    }
    new PostErrorCommand(paramCharSequence.toString(), new AuthToken()
    {
      public String getHeader()
      {
        return "FAILED";
      }
    });
  }
  
  public static CharSequence makeStacktrace(Throwable paramThrowable)
  {
    return appendThrowable(new StringBuffer(), "", paramThrowable);
  }
  
  private static CharSequence receiverLogString(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer("  ");
    localStringBuffer.append(paramCharSequence);
    localStringBuffer.append(": proiority=");
    localStringBuffer.append(paramInt1);
    localStringBuffer.append(", preferredOrder=");
    localStringBuffer.append(paramInt2);
    localStringBuffer.append("\n");
    return localStringBuffer;
  }
  
  public ResponseHandler<String> getResponseHandler()
  {
    return new BasicResponseHandler();
  }
}
