package com.google.android.apps.enterprise.dmagent;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.apps.enterprise.dmagent.b.i;
import com.google.android.apps.enterprise.dmagent.b.l;
import com.google.common.base.g;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class t
{
  public t() {}
  
  static int a(Set<String> paramSet, com.google.android.libraries.b.a.a paramA, Context paramContext)
  {
    ComponentName localComponentName = DeviceAdminReceiver.a(paramContext);
    paramContext = com.google.android.gms.common.api.u.p(paramContext);
    paramSet = paramSet.iterator();
    int i = 0;
    if (paramSet.hasNext())
    {
      String str = (String)paramSet.next();
      if ((str == null) || (g.a(str.trim()))) {
        break label79;
      }
      paramContext.d(localComponentName, str);
      paramA.c(str);
      i += 1;
    }
    label79:
    for (;;)
    {
      break;
      return i;
    }
  }
  
  public static String a(Context paramContext, String paramString, MessageDigest paramMessageDigest)
  {
    paramContext = paramContext.getPackageManager();
    if (paramString != null) {
      try
      {
        if (!TextUtils.isEmpty(paramString.trim()))
        {
          paramContext = a(paramContext.getPackageInfo(paramString, 0).applicationInfo.publicSourceDir, paramMessageDigest);
          return paramContext;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        Log.e("DMAgent", "PackageManager cannot detect the packageName provided for finding its hash", paramContext);
      }
    }
    return null;
  }
  
  private static String a(InputStream paramInputStream, MessageDigest paramMessageDigest)
  {
    try
    {
      byte[] arrayOfByte = new byte['䀀'];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i < 0) {
          break;
        }
        paramMessageDigest.update(arrayOfByte, 0, i);
      }
      paramInputStream = "Exception thrown by getSha256Hash ".concat(paramInputStream);
    }
    catch (IOException paramInputStream)
    {
      paramInputStream = String.valueOf(Log.getStackTraceString(paramInputStream));
      if (paramInputStream.length() == 0) {}
    }
    for (;;)
    {
      Log.d("DMAgent", paramInputStream);
      return null;
      D.a();
      paramInputStream = D.a(paramMessageDigest.digest()).toUpperCase(Locale.US);
      return paramInputStream;
      paramInputStream = new String("Exception thrown by getSha256Hash ");
    }
  }
  
  public static String a(String paramString, MessageDigest paramMessageDigest)
  {
    if ((paramString != null) && (!TextUtils.isEmpty(paramString.trim())))
    {
      paramString = new File(paramString);
      if ((paramString.exists()) && (paramString.canRead())) {
        try
        {
          paramString = a(new FileInputStream(paramString), paramMessageDigest);
          return paramString;
        }
        catch (IOException paramString)
        {
          paramString = String.valueOf(Log.getStackTraceString(paramString));
          if (paramString.length() == 0) {}
        }
      }
    }
    for (paramString = "IOException thrown by getSha256Hash ".concat(paramString);; paramString = new String("IOException thrown by getSha256Hash "))
    {
      Log.d("DMAgent", paramString);
      return null;
    }
  }
  
  public static String a(List<PackageInfo> paramList, aA paramAA, Context paramContext, com.google.common.a.a.a paramA)
  {
    if (paramList == null) {
      return null;
    }
    Object localObject1;
    do
    {
      try
      {
        localObject1 = MessageDigest.getInstance("MD5");
        localObject2 = paramList.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
          ((MessageDigest)localObject1).update(localPackageInfo.packageName.getBytes());
          i = localPackageInfo.versionCode;
          ((MessageDigest)localObject1).update((11 + i).getBytes());
          ((MessageDigest)localObject1).update((byte)0);
        }
        localObject1 = a(((MessageDigest)localObject1).digest());
      }
      catch (NoSuchAlgorithmException paramList)
      {
        paramList = String.valueOf(paramList);
        Log.e("DMAgent", String.valueOf(paramList).length() + 44 + "Application data not added to server request" + paramList);
        return null;
      }
    } while (((String)localObject1).equals(paramAA.bc()));
    Object localObject2 = String.valueOf(paramAA.bc());
    int i = paramList.size();
    Log.d("DMAgent", String.valueOf(localObject2).length() + 91 + String.valueOf(localObject1).length() + "Adding Application data to server request. Old digest: " + (String)localObject2 + ". New: " + (String)localObject1 + ". Number of apps: " + i);
    DMProtoUtils.a(paramAA, paramA, paramList, paramContext);
    return localObject1;
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length);
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      int k = paramArrayOfByte[i] & 0xFF;
      localStringBuilder.append("0123456789abcdef".charAt(k >> 4));
      localStringBuilder.append("0123456789abcdef".charAt(k & 0xF));
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  static Set<String> a(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    paramContext = new HashSet();
    localObject = ((PackageManager)localObject).getInstalledPackages(8704).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = ((PackageInfo)((Iterator)localObject).next()).applicationInfo;
      if ((localApplicationInfo != null) && ((localApplicationInfo.flags & 0x81) > 0)) {
        paramContext.add(localApplicationInfo.packageName);
      }
    }
    return paramContext;
  }
  
  private static List<ResolveInfo> d(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    return paramContext.queryIntentActivities(localIntent, 8704);
  }
  
  public final List<PackageInfo> a(D paramD, aA paramAA, Context paramContext)
  {
    String str = paramAA.bd();
    Object localObject = com.google.android.gms.common.api.u.u(paramContext);
    paramD = com.google.android.gms.common.api.u.p(paramContext);
    if ((D.e()) && ((paramD.c()) || (paramD.b()))) {
      return ((l)localObject).a(4160);
    }
    if (str == null) {
      return null;
    }
    paramD = new HashSet(Arrays.asList(str.split(":")));
    paramAA = new LinkedList();
    if (!TextUtils.isEmpty(str))
    {
      paramContext = (PackageInfo[])((l)localObject).a(4160).toArray(new PackageInfo[0]);
      int k = paramContext.length;
      int i = 0;
      if (i < k)
      {
        str = paramContext[i];
        int m;
        int j;
        if (str.requestedPermissions != null)
        {
          localObject = str.requestedPermissions;
          m = localObject.length;
          j = 0;
        }
        for (;;)
        {
          if (j < m)
          {
            if (paramD.contains(localObject[j])) {
              paramAA.add(str);
            }
          }
          else
          {
            i += 1;
            break;
          }
          j += 1;
        }
      }
      Collections.sort(paramAA, new u());
    }
    return paramAA;
  }
  
  final int b(Set<String> paramSet, com.google.android.libraries.b.a.a paramA, Context paramContext)
  {
    int i = 0;
    paramContext = paramContext.getPackageManager();
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    localObject = paramContext.queryIntentActivities((Intent)localObject, 0);
    paramContext = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = ((ResolveInfo)((Iterator)localObject).next()).activityInfo.applicationInfo;
      if ((localApplicationInfo.flags & 0x81) > 0) {
        paramContext.add(localApplicationInfo.packageName);
      }
    }
    paramSet.removeAll(paramContext);
    paramSet = paramSet.iterator();
    if (paramSet.hasNext())
    {
      paramContext = (String)paramSet.next();
      if ((paramContext == null) || (g.a(paramContext.trim()))) {
        break label175;
      }
      paramA.b(paramContext);
      i += 1;
    }
    label175:
    for (;;)
    {
      break;
      return i;
    }
  }
  
  final Set<String> b(Context paramContext)
  {
    Object localObject = d(paramContext);
    paramContext = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = ((ResolveInfo)((Iterator)localObject).next()).activityInfo.applicationInfo;
      if ((localApplicationInfo.flags & 0x81) > 0) {
        paramContext.add(localApplicationInfo.packageName);
      }
    }
    return paramContext;
  }
  
  final int c(Context paramContext)
  {
    D.a();
    if (D.e())
    {
      PackageInstaller localPackageInstaller = paramContext.getPackageManager().getPackageInstaller();
      Object localObject2 = d(paramContext);
      Object localObject1 = new HashSet();
      localObject2 = ((List)localObject2).iterator();
      Object localObject3;
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = ((ResolveInfo)((Iterator)localObject2).next()).activityInfo.applicationInfo;
        if ((((ApplicationInfo)localObject3).flags & 0x81) == 0) {
          ((Set)localObject1).add(((ApplicationInfo)localObject3).packageName);
        }
      }
      localObject1 = ((Set)localObject1).iterator();
      int i = 0;
      for (;;)
      {
        j = i;
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        localObject2 = (String)((Iterator)localObject1).next();
        localObject3 = new Intent(paramContext, AppUpdateReceiver.class);
        ((Intent)localObject3).putExtra("com.google.android.apps.enterprise.dmagent.uninstalledPackageName", (String)localObject2);
        localPackageInstaller.uninstall((String)localObject2, PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject3, 0).getIntentSender());
        i += 1;
      }
    }
    int j = 0;
    return j;
  }
}
