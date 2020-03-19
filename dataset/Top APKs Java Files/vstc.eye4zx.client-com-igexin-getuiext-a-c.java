package com.igexin.getuiext.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.igexin.getuiext.data.Consts;
import com.igexin.getuiext.service.GetuiExtService;
import com.igexin.getuiext.util.g;
import com.igexin.getuiext.util.h;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c
  implements a
{
  private final String a = "GetuiExt-BindCIDAction";
  
  public c() {}
  
  private static void a(long paramLong)
  {
    com.igexin.getuiext.data.a.b = paramLong;
    com.igexin.getuiext.b.c.d().a().b();
  }
  
  private void a(Context paramContext, long paramLong)
  {
    Object localObject1 = new ArrayList();
    a(paramContext, (List)localObject1);
    int j = ((List)localObject1).size();
    if (j <= 0) {}
    for (;;)
    {
      return;
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("action", "reportApps");
        localJSONObject.put("cid", Consts.CID);
        localJSONObject.put("app_id", Consts.APPID);
        localJSONObject.put("selfpkg", paramContext.getPackageName());
        paramContext = new JSONArray();
        int i = 0;
        while (i < j)
        {
          localObject2 = new JSONObject();
          localObject3 = (com.igexin.getuiext.data.a.c)((List)localObject1).get(i);
          ((JSONObject)localObject2).put("pkgname", ((com.igexin.getuiext.data.a.c)localObject3).b);
          ((JSONObject)localObject2).put("versionCode", String.valueOf(((com.igexin.getuiext.data.a.c)localObject3).d));
          ((JSONObject)localObject2).put("checksum", ((com.igexin.getuiext.data.a.c)localObject3).f);
          paramContext.put(localObject2);
          i += 1;
        }
        Object localObject2 = g.a(String.valueOf(System.currentTimeMillis()));
        localObject1 = new ByteArrayOutputStream();
        Object localObject3 = new GZIPOutputStream((OutputStream)localObject1);
        ((GZIPOutputStream)localObject3).write(paramContext.toString().getBytes());
        ((GZIPOutputStream)localObject3).close();
        i = ((ByteArrayOutputStream)localObject1).size();
        paramContext = new byte[i + 16];
        localObject3 = ((String)localObject2).substring(0, 8).getBytes();
        localObject2 = ((String)localObject2).substring(24, 32).getBytes();
        System.arraycopy(localObject3, 0, paramContext, 0, 8);
        System.arraycopy(((ByteArrayOutputStream)localObject1).toByteArray(), 0, paramContext, 8, i);
        System.arraycopy(localObject2, 0, paramContext, i + 8, 8);
        ((ByteArrayOutputStream)localObject1).close();
        localJSONObject.put("apps", new String(paramContext, "ISO-8859-1"));
        paramContext = com.igexin.getuiext.util.c.a("http://sdk.open.inc2.igexin.com/api.php", localJSONObject, Consts.DEFAULT_RETRY_TIMES);
        if ((paramContext != null) && ("1".equals(new JSONObject(paramContext).getString("result"))))
        {
          com.igexin.getuiext.data.a.a = paramLong;
          com.igexin.getuiext.b.c.d().a().a();
          return;
        }
      }
      catch (Exception paramContext) {}
    }
  }
  
  private void a(Context paramContext, List paramList)
  {
    d localD = new d(this);
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int j = localList.size();
    int i = 0;
    for (;;)
    {
      if (i < j) {}
      try
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
        if ((localApplicationInfo.flags & 0x1) <= 0)
        {
          com.igexin.getuiext.data.a.c localC = new com.igexin.getuiext.data.a.c();
          localC.b = localApplicationInfo.packageName;
          localC.d = localPackageInfo.versionCode;
          localC.f = h.b(paramContext, localApplicationInfo.packageName, localApplicationInfo.sourceDir);
          paramList.add(localC);
        }
        i += 1;
        continue;
        Collections.sort(paramList, localD);
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public void a(Context paramContext, Intent paramIntent)
  {
    paramIntent = paramIntent.getStringExtra("cid");
    Consts.CID = paramIntent;
    if (System.currentTimeMillis() - com.igexin.getuiext.data.a.b < 86400000L)
    {
      paramContext.stopService(new Intent(paramContext, GetuiExtService.class));
      return;
    }
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("action", "bindApp");
        localJSONObject.put("cid", paramIntent);
        localJSONObject.put("app_id", Consts.APPID);
        localJSONObject.put("pkgname", paramContext.getPackageName());
        localJSONObject.put("inc_version", Consts.VERSION);
        paramIntent = com.igexin.getuiext.util.c.a("http://sdk.open.inc2.igexin.com/api.php", localJSONObject, Consts.DEFAULT_RETRY_TIMES);
        if (paramIntent == null) {}
      }
      catch (JSONException paramIntent)
      {
        continue;
      }
      try
      {
        if ("1".equals(new JSONObject(paramIntent).getString("result")))
        {
          long l1 = System.currentTimeMillis();
          long l2 = com.igexin.getuiext.data.a.a;
          a(l1);
          if (l1 - l2 > 259200000L) {
            a(paramContext, l1);
          }
        }
      }
      catch (JSONException paramIntent) {}
    }
    paramContext.stopService(new Intent(paramContext, GetuiExtService.class));
  }
}
