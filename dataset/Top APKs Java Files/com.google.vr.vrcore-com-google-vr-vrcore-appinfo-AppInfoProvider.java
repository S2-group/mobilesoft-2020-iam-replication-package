package com.google.vr.vrcore.appinfo;

import aho;
import ahv;
import akd;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.UriMatcher;
import android.content.pm.ActivityInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import apt;
import cso;
import dgh;
import dgi;
import dgn;
import dgo;
import dji;
import ezw;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import tw;

public class AppInfoProvider
  extends ContentProvider
{
  private static final String[] b = { "com.google.vr.vrcore", "com.google.android.vr.home" };
  public ahv a;
  private UriMatcher c;
  private PackageManager d;
  private akd e;
  private ezw f;
  private LauncherApps g;
  private Context h;
  private boolean i;
  private final Object j = new Object();
  private boolean k;
  private final AtomicLong l = new AtomicLong();
  private SharedPreferences m;
  private SharedPreferences n;
  private final BroadcastReceiver o = new dgi(this);
  
  public AppInfoProvider() {}
  
  public AppInfoProvider(ahv paramAhv, akd paramAkd, ezw paramEzw, LauncherApps paramLauncherApps)
  {
    this.a = paramAhv;
    this.e = paramAkd;
    this.f = paramEzw;
    this.g = paramLauncherApps;
  }
  
  private final int a(List paramList)
  {
    paramList = TextUtils.join(",", paramList);
    this.m.edit().putString("pinned_apps", paramList).apply();
    return 0;
  }
  
  private static void a(SharedPreferences.Editor paramEditor, Map paramMap, ComponentName paramComponentName)
  {
    paramComponentName = paramComponentName.flattenToString();
    paramMap = paramMap.get(paramComponentName);
    if (paramMap != null) {
      paramEditor.putLong(paramComponentName, ((Long)paramMap).longValue());
    }
  }
  
  private final boolean a()
  {
    for (;;)
    {
      long l2;
      int i2;
      synchronized (this.j)
      {
        if (this.k) {
          return true;
        }
        if (!tw.b(this.h)) {
          return false;
        }
        if (this.n == null) {
          this.n = this.h.getSharedPreferences("RecencyPrefs", 0);
        }
        if (this.m == null) {
          this.m = this.h.getSharedPreferences("PinningPrefs", 0);
        }
        Object localObject4 = this.n.getAll();
        Object localObject1 = this.l;
        l1 = 0L;
        Object localObject3 = ((Map)localObject4).values().iterator();
        Object localObject6;
        if (((Iterator)localObject3).hasNext())
        {
          localObject6 = ((Iterator)localObject3).next();
          if (localObject6 == null) {
            continue;
          }
          long l3 = ((Long)localObject6).longValue();
          l2 = l1;
          if (l3 > l1) {
            l2 = l3;
          }
        }
        else
        {
          ((AtomicLong)localObject1).set(l1);
          localObject6 = this.n.edit().clear();
          Object localObject8 = this.a.a(0);
          localObject1 = new ArrayList();
          if (this.i) {
            localObject1 = this.g.getActivityList(null, Process.myUserHandle());
          }
          if ((!((List)localObject8).isEmpty()) || (!((List)localObject1).isEmpty()))
          {
            Object localObject7 = new HashSet(((Map)localObject4).size());
            Object localObject9 = ((Map)localObject4).keySet().iterator();
            Object localObject10;
            if (((Iterator)localObject9).hasNext())
            {
              localObject3 = (String)((Iterator)localObject9).next();
              localObject10 = ComponentName.unflattenFromString((String)localObject3);
              if (localObject10 == null)
              {
                localObject3 = String.valueOf(localObject3);
                if (((String)localObject3).length() != 0) {
                  localObject3 = "Removing previously stored pref key - did not resolve to a valid component: ".concat((String)localObject3);
                } else {
                  localObject3 = new String("Removing previously stored pref key - did not resolve to a valid component: ");
                }
                Log.d("AppInfoProvider", (String)localObject3);
                continue;
              }
              ((HashSet)localObject7).add(((ComponentName)localObject10).getPackageName());
              continue;
            }
            localObject3 = new HashMap();
            localObject8 = ((List)localObject8).iterator();
            if (((Iterator)localObject8).hasNext())
            {
              localObject9 = (ResolveInfo)((Iterator)localObject8).next();
              localObject10 = ((ResolveInfo)localObject9).activityInfo.packageName;
              ComponentName localComponentName = new ComponentName((String)localObject10, ((ResolveInfo)localObject9).activityInfo.name);
              if (((HashSet)localObject7).contains(localObject10))
              {
                a((SharedPreferences.Editor)localObject6, (Map)localObject4, localComponentName);
                continue;
              }
              if ((!((HashMap)localObject3).containsKey(localObject10)) || (((ResolveInfo)localObject9).isDefault)) {
                ((HashMap)localObject3).put(localObject10, new ArrayList());
              }
              ((ArrayList)((HashMap)localObject3).get(localObject10)).add(localComponentName.flattenToString());
              continue;
            }
            localObject1 = ((List)localObject1).iterator();
            if (((Iterator)localObject1).hasNext())
            {
              localObject8 = ((LauncherActivityInfo)((Iterator)localObject1).next()).getComponentName();
              localObject9 = ((ComponentName)localObject8).getPackageName();
              if (((HashSet)localObject7).contains(localObject9))
              {
                a((SharedPreferences.Editor)localObject6, (Map)localObject4, (ComponentName)localObject8);
                continue;
              }
              if (!((HashMap)localObject3).containsKey(localObject9)) {
                ((HashMap)localObject3).put(localObject9, new ArrayList());
              }
              ((ArrayList)((HashMap)localObject3).get(localObject9)).add(((ComponentName)localObject8).flattenToString());
              continue;
            }
            localObject7 = this.d.getInstalledPackages(0);
            localObject1 = new ArrayList(((HashMap)localObject3).size());
            localObject7 = ((List)localObject7).iterator();
            if (((Iterator)localObject7).hasNext())
            {
              localObject8 = (PackageInfo)((Iterator)localObject7).next();
              if (!((HashMap)localObject3).containsKey(((PackageInfo)localObject8).packageName)) {
                break label1117;
              }
              ((List)localObject1).add(localObject8);
              break label1117;
            }
            Collections.sort((List)localObject1, dgh.a);
            localObject1 = (ArrayList)localObject1;
            int i3 = ((ArrayList)localObject1).size();
            i1 = 0;
            if (i1 < i3)
            {
              localObject7 = ((ArrayList)localObject1).get(i1);
              i2 = i1 + 1;
              localObject7 = (List)((HashMap)localObject3).get(((PackageInfo)localObject7).packageName);
              i1 = i2;
              if (localObject7 == null) {
                continue;
              }
              localObject7 = ((List)localObject7).iterator();
              if (!((Iterator)localObject7).hasNext()) {
                break label1120;
              }
              ((SharedPreferences.Editor)localObject6).putLong((String)((Iterator)localObject7).next(), this.l.incrementAndGet());
              continue;
            }
            if (((Map)localObject4).isEmpty())
            {
              localObject1 = aho.a(this.d, apt.c());
              if (localObject1 == null) {
                localObject1 = new String[0];
              } else {
                localObject1 = ((aho)localObject1).a("partner_initial_recent_apps");
              }
              i1 = localObject1.length - 1;
              if (i1 >= 0)
              {
                localObject7 = localObject1[i1];
                localObject4 = ComponentName.unflattenFromString((String)localObject7);
                localObject3 = localObject4;
                if (localObject4 == null) {
                  localObject3 = this.a.a((String)localObject7);
                }
                if (localObject3 == null)
                {
                  localObject3 = String.valueOf(localObject7);
                  if (((String)localObject3).length() != 0) {
                    localObject3 = "Not storing info for app - couldn't find main VR activity: ".concat((String)localObject3);
                  } else {
                    localObject3 = new String("Not storing info for app - couldn't find main VR activity: ");
                  }
                  Log.d("AppInfoProvider", (String)localObject3);
                  break label1125;
                }
                if (!this.a.a((ComponentName)localObject3))
                {
                  localObject3 = String.valueOf(localObject7);
                  if (((String)localObject3).length() != 0) {
                    localObject3 = "Not storing info for app - can't be launched in VR: ".concat((String)localObject3);
                  } else {
                    localObject3 = new String("Not storing info for app - can't be launched in VR: ");
                  }
                  Log.d("AppInfoProvider", (String)localObject3);
                  break label1125;
                }
                ((SharedPreferences.Editor)localObject6).putLong((String)localObject7, this.l.incrementAndGet());
                break label1125;
              }
            }
          }
          ((SharedPreferences.Editor)localObject6).apply();
          localObject1 = new IntentFilter("android.intent.action.PACKAGE_ADDED");
          ((IntentFilter)localObject1).addDataScheme("package");
          this.h.registerReceiver(this.o, (IntentFilter)localObject1);
          this.k = true;
          return true;
        }
      }
      long l1 = l2;
      continue;
      label1117:
      continue;
      label1120:
      int i1 = i2;
      continue;
      label1125:
      i1 -= 1;
    }
  }
  
  private final void b()
  {
    String str = getCallingPackage();
    if (this.h.getPackageName().equals(str)) {
      return;
    }
    Object localObject = this.e;
    this.h.getPackageManager();
    if (!((akd)localObject).a(str))
    {
      str = String.valueOf(str);
      if (str.length() != 0) {
        str = "Calling package is not signed for this content provider: ".concat(str);
      } else {
        str = new String("Calling package is not signed for this content provider: ");
      }
      throw new SecurityException(str);
    }
    localObject = b;
    int i2 = localObject.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (localObject[i1].equals(str)) {
        return;
      }
      i1 += 1;
    }
    str = String.valueOf(str);
    if (str.length() != 0) {
      str = "Calling package is not whitelisted for this content provider: ".concat(str);
    } else {
      str = new String("Calling package is not whitelisted for this content provider: ");
    }
    throw new SecurityException(str);
  }
  
  private final boolean b(ComponentName paramComponentName)
  {
    return (this.a.a(paramComponentName)) || (this.i);
  }
  
  private final String[] c()
  {
    String str = this.m.getString("pinned_apps", null);
    if ((str != null) && (!str.isEmpty())) {
      return str.split(",");
    }
    return new String[0];
  }
  
  public final int a(ComponentName paramComponentName)
  {
    if (paramComponentName == null)
    {
      Log.w("AppInfoProvider", "Component is null");
      return 1;
    }
    if (!b(paramComponentName))
    {
      paramComponentName = String.valueOf(paramComponentName.flattenToString());
      if (paramComponentName.length() != 0) {
        paramComponentName = "Not storing info for apps that can't be launched in VR: ".concat(paramComponentName);
      } else {
        paramComponentName = new String("Not storing info for apps that can't be launched in VR: ");
      }
      Log.d("AppInfoProvider", paramComponentName);
      return 1;
    }
    this.n.edit().putLong(paramComponentName.flattenToString(), this.l.incrementAndGet()).apply();
    return 0;
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    b();
    if (!a()) {
      return 1;
    }
    if (this.c.match(paramUri) != 100)
    {
      paramUri = String.valueOf(paramUri);
      paramString = new StringBuilder(String.valueOf(paramUri).length() + 38);
      paramString.append("Received delete with unsupported uri: ");
      paramString.append(paramUri);
      Log.w("AppInfoProvider", paramString.toString());
      return 1;
    }
    this.n.edit().remove(paramString).apply();
    paramUri = c();
    if (paramUri.length == 0) {
      return 0;
    }
    paramArrayOfString = new ArrayList(paramUri.length);
    int i2 = paramUri.length;
    int i1 = 0;
    while (i1 < i2)
    {
      Object localObject = paramUri[i1];
      if (!localObject.equals(paramString)) {
        paramArrayOfString.add(localObject);
      }
      i1 += 1;
    }
    a(paramArrayOfString);
    return 0;
  }
  
  public String getType(Uri paramUri)
  {
    b();
    return null;
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    b();
    return null;
  }
  
  public boolean onCreate()
  {
    dji.c("VrCoreMain");
    this.h = getContext();
    this.d = this.h.getPackageManager();
    if (this.a == null) {
      this.a = new ahv(this.d);
    }
    if (this.e == null) {
      this.e = akd.a(this.h);
    }
    if (this.f == null) {
      this.f = new ezw(this.h);
    }
    this.i = this.f.C();
    if (this.g == null) {
      this.g = ((LauncherApps)this.h.getSystemService("launcherapps"));
    }
    this.c = new UriMatcher(-1);
    this.c.addURI("com.google.vr.vrcore.appinfo", "app_info", 100);
    this.c.addURI("com.google.vr.vrcore.appinfo", "recency", 200);
    this.c.addURI("com.google.vr.vrcore.appinfo", "pinned_apps", 300);
    a();
    return true;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    b();
    if (!a()) {
      return null;
    }
    if (this.c.match(paramUri) != 100)
    {
      paramUri = String.valueOf(paramUri);
      paramArrayOfString1 = new StringBuilder(String.valueOf(paramUri).length() + 37);
      paramArrayOfString1.append("Received query with unsupported uri: ");
      paramArrayOfString1.append(paramUri);
      Log.w("AppInfoProvider", paramArrayOfString1.toString());
      return null;
    }
    paramArrayOfString1 = this.n.getAll();
    paramUri = new dgo[paramArrayOfString1.size()];
    paramArrayOfString1 = paramArrayOfString1.entrySet().iterator();
    int i1 = 0;
    while (paramArrayOfString1.hasNext())
    {
      paramString1 = (Map.Entry)paramArrayOfString1.next();
      paramUri[i1] = ((dgo)dgo.c().e((String)paramString1.getKey()).f(((Long)paramString1.getValue()).longValue()).build());
      i1 += 1;
    }
    paramUri = (dgn)dgn.d().d(Arrays.asList(paramUri)).e(Arrays.asList(c())).build();
    paramArrayOfString1 = new MatrixCursor(new String[] { "proto" });
    paramArrayOfString1.addRow(new Object[] { paramUri.toByteArray() });
    return paramArrayOfString1;
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    b();
    if (!a()) {
      return 1;
    }
    int i1 = this.c.match(paramUri);
    if (i1 != 200)
    {
      if (i1 != 300)
      {
        paramUri = String.valueOf(paramUri);
        paramContentValues = new StringBuilder(String.valueOf(paramUri).length() + 38);
        paramContentValues.append("Received update with unsupported uri: ");
        paramContentValues.append(paramUri);
        Log.w("AppInfoProvider", paramContentValues.toString());
        return 1;
      }
      if (paramContentValues == null)
      {
        Log.w("AppInfoProvider", "Received null as the list of pinned apps. Pass an empty string to clear.");
        return 1;
      }
      paramUri = paramContentValues.getAsString("pinned_apps");
      if (paramUri == null)
      {
        Log.w("AppInfoProvider", "Received null as the list of pinned apps. Pass an empty string to clear.");
        return 1;
      }
      paramUri = paramUri.split(",");
      paramContentValues = new ArrayList(paramUri.length);
      int i2 = paramUri.length;
      i1 = 0;
      while (i1 < i2)
      {
        paramString = paramUri[i1];
        if (b(ComponentName.unflattenFromString(paramString))) {
          paramContentValues.add(paramString);
        }
        i1 += 1;
      }
      return a(paramContentValues);
    }
    return a(ComponentName.unflattenFromString(paramString));
  }
}
