package com.iddiction.sdk.internal.utils.Tergeo;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Base64;
import com.iddiction.sdk.internal.utils.Tergeo;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class Alohomora
  implements Runnable
{
  private final WeakReference Alohomora;
  private final String Orchideous;
  private final boolean Tergeo;
  
  public Alohomora(Context paramContext, String paramString, boolean paramBoolean)
  {
    this.Alohomora = new WeakReference(paramContext);
    this.Orchideous = paramString;
    this.Tergeo = paramBoolean;
  }
  
  private static List Alohomora(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
    paramContext = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramContext.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
    }
    return paramContext;
  }
  
  private static JSONObject Alohomora(List paramList1, List paramList2, String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("hash", paramString);
    if (paramList1 != null)
    {
      paramString = new JSONArray();
      paramList1 = paramList1.iterator();
      while (paramList1.hasNext()) {
        paramString.put((String)paramList1.next());
      }
      localJSONObject.put("schemes", paramString);
    }
    paramList1 = new JSONArray();
    paramList2 = paramList2.iterator();
    while (paramList2.hasNext()) {
      paramList1.put((String)paramList2.next());
    }
    localJSONObject.put("processes", paramList1);
    return localJSONObject;
  }
  
  private static List Orchideous(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject1 = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    paramContext = new ArrayList(((List)localObject1).size());
    Object localObject2 = ((List)localObject1).iterator();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    for (int i = 0; ((Iterator)localObject2).hasNext(); i = localRunningAppProcessInfo.lru)
    {
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
      if (localRunningAppProcessInfo.lru <= i) {
        break label195;
      }
    }
    localObject1 = ((List)localObject1).iterator();
    label94:
    do
    {
      if (!((Iterator)localObject1).hasNext()) {
        break;
      }
      localObject2 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject1).next();
    } while (((ActivityManager.RunningAppProcessInfo)localObject2).pkgList.length == 0);
    for (;;)
    {
      label195:
      try
      {
        if ((localPackageManager.getPackageInfo(localObject2.pkgList[0], 1).applicationInfo.flags & 0x1) == 0) {
          break label203;
        }
        j = 1;
        if (j == 0)
        {
          paramContext.add(((ActivityManager.RunningAppProcessInfo)localObject2).processName);
          break label94;
        }
        if (((ActivityManager.RunningAppProcessInfo)localObject2).lru >= i) {
          break label94;
        }
        paramContext.add(((ActivityManager.RunningAppProcessInfo)localObject2).processName);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
      return paramContext;
      break;
      break label94;
      label203:
      int j = 0;
    }
  }
  
  public final void run()
  {
    com.iddiction.sdk.dependencies.Orchideous.Alohomora.Alohomora.Alohomora("Executing application scan.", new Object[0]);
    Object localObject3 = (Context)this.Alohomora.get();
    if (localObject3 == null) {
      return;
    }
    try
    {
      localObject1 = new JSONObject(new String(Tergeo.Alohomora(Base64.decode(this.Orchideous.getBytes("UTF-8"), 2), "SYoIngdJl2sYeIqrjK28WsuGIE43p116"), "UTF-8"));
      boolean bool = ((JSONObject)localObject1).optBoolean("scan");
      str = ((JSONObject)localObject1).optString("hash");
      if (!bool) {
        break label197;
      }
      localObject1 = Alohomora((Context)localObject3);
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Object localObject1;
        String str;
        com.iddiction.sdk.dependencies.Orchideous.Alohomora.Alohomora.Alohomora("Scanning failed, bad JSON: " + localJSONException.getMessage(), new Object[0]);
        return;
        Object localObject2 = null;
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      com.iddiction.sdk.dependencies.Orchideous.Alohomora.Alohomora.Alohomora("Scanning failed due to unsupported encoding.", new Object[0]);
    }
    localObject1 = new String(Base64.encode(Tergeo.Alohomora(Alohomora((List)localObject1, Orchideous((Context)localObject3), str).toString(), "SYoIngdJl2sYeIqrjK28WsuGIE43p116"), 2), "UTF-8");
    localObject3 = new HashMap();
    ((Map)localObject3).put("data", localObject1);
    if (this.Tergeo) {
      ((Map)localObject3).put("source", "silent_push");
    }
    com.iddiction.sdk.dependencies.Orchideous.Alohomora.Alohomora.Alohomora("Application scan completed. Logging to analytics.", new Object[0]);
    com.iddiction.sdk.internal.Alohomora.Alohomora.Alohomora("__/fingerprint", (Map)localObject3, true);
    return;
    label197:
  }
}
