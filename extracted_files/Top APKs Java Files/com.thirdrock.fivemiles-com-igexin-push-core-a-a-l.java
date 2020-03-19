package com.igexin.push.core.a.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.igexin.push.core.a.e;
import com.igexin.push.core.b;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.f;
import com.igexin.push.core.g;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class l
  implements a
{
  private static final String b = com.igexin.push.core.a.n;
  private static final String c = com.igexin.push.core.a.p;
  private static final String d = com.igexin.push.core.a.o;
  private PackageManager a = null;
  
  public l() {}
  
  private String a(String paramString)
  {
    try
    {
      Object localObject1 = g.g.getPackageManager().getInstalledPackages(4);
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
          if (paramString.equals(((PackageInfo)localObject2).packageName))
          {
            localObject2 = ((PackageInfo)localObject2).services;
            int j = localObject2.length;
            int i = 0;
            while (i < j)
            {
              Object localObject3 = localObject2[i];
              if ((b.equals(localObject3.name)) || (d.equals(localObject3.name)) || (c.equals(localObject3.name)))
              {
                paramString = localObject3.name;
                return paramString;
              }
              i += 1;
            }
          }
        }
      }
      return null;
    }
    catch (Exception paramString)
    {
      com.igexin.a.a.c.a.b(paramString.toString());
    }
  }
  
  private List a(int paramInt, String paramString)
  {
    Object localObject1 = null;
    localObject3 = null;
    File localFile = new File("/sdcard/libs");
    if (!localFile.exists()) {}
    String[] arrayOfString;
    int i;
    do
    {
      do
      {
        return localObject3;
        arrayOfString = localFile.list();
      } while (arrayOfString == null);
      i = 0;
      localObject3 = localObject1;
    } while (i >= arrayOfString.length);
    localObject3 = localObject1;
    String str;
    if (arrayOfString[i].indexOf(".db") > 0)
    {
      localObject3 = localObject1;
      if (!arrayOfString[i].equals("app.db"))
      {
        localObject3 = localObject1;
        if (!arrayOfString[i].equals("imsi.db"))
        {
          localObject3 = localObject1;
          if (!arrayOfString[i].equals("com.igexin.sdk.deviceId.db"))
          {
            str = arrayOfString[i].substring(0, arrayOfString[i].length() - 3);
            localObject4 = localObject1;
          }
        }
      }
    }
    try
    {
      localObject5 = new File(localFile + "/" + arrayOfString[i]);
      localObject4 = localObject1;
      localObject3 = new byte['Ѐ'];
      localObject4 = localObject1;
      try
      {
        localObject5 = new FileInputStream((File)localObject5);
        localObject4 = localObject1;
        localObject6 = new ByteArrayOutputStream();
        for (;;)
        {
          localObject4 = localObject1;
          int j = ((InputStream)localObject5).read((byte[])localObject3);
          if (j == -1) {
            break;
          }
          localObject4 = localObject1;
          ((ByteArrayOutputStream)localObject6).write((byte[])localObject3, 0, j);
        }
        localObject4 = localObject3;
      }
      catch (Exception localException1)
      {
        localObject3 = localObject4;
      }
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        Object localObject5;
        Object localObject6;
        com.igexin.a.a.c.a.b(localException2.toString());
        localObject3 = localObject4;
      }
    }
    com.igexin.a.a.c.a.b(localException1.toString());
    for (;;)
    {
      i += 1;
      Object localObject2 = localObject3;
      break;
      localObject4 = localObject2;
      localObject5 = ((ByteArrayOutputStream)localObject6).toByteArray();
      localObject4 = localObject2;
      if (g.u == null)
      {
        localObject3 = "cantgetimei";
        label305:
        localObject4 = localObject2;
        localObject5 = new String(com.igexin.a.a.a.a.a((byte[])localObject5, com.igexin.a.b.a.a((String)localObject3))).split("\\|");
        localObject4 = localObject2;
        if (localObject5[0].startsWith("v"))
        {
          localObject4 = localObject2;
          if (localObject5[0].indexOf("null") < 0) {
            break label454;
          }
          localObject4 = localObject2;
          localObject5[0] = localObject5[0].substring(7);
        }
      }
      for (;;)
      {
        localObject4 = localObject2;
        localObject6 = com.igexin.a.b.a.a(localObject5[0]);
        if (paramInt != 0) {
          break label474;
        }
        localObject4 = localObject2;
        localObject3 = localObject2;
        if (!paramString.equals(localObject6)) {
          break;
        }
        localObject4 = localObject2;
        localObject3 = new ArrayList();
        try
        {
          ((List)localObject3).add(str);
          return localObject3;
        }
        catch (Exception localException3) {}
        localObject4 = localObject2;
        localObject3 = g.u;
        break label305;
        label454:
        localObject4 = localObject2;
        localObject5[0] = localObject5[0].substring(20);
      }
      label474:
      localObject4 = localObject2;
      localObject3 = localObject2;
      if (localObject5.length > 1)
      {
        localObject4 = localObject2;
        localObject3 = localObject2;
        if (paramString.equals(localObject5[1]))
        {
          localObject3 = localObject2;
          if (localObject2 == null)
          {
            localObject4 = localObject2;
            localObject3 = new ArrayList();
          }
          localObject4 = localObject3;
          ((List)localObject3).add(str);
        }
      }
    }
  }
  
  private void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(g.g.getPackageName());
    localStringBuilder.append("#");
    localStringBuilder.append(paramString4);
    localStringBuilder.append("#");
    localStringBuilder.append(paramString5);
    localStringBuilder.append("#");
    localStringBuilder.append("-1");
    b("30025", localStringBuilder.toString(), paramString1, paramString2, paramString3);
    com.igexin.a.a.c.a.b("feedback actionId=30025 result=" + localStringBuilder.toString());
  }
  
  private void a(String paramString, boolean paramBoolean, PushTaskBean paramPushTaskBean, BaseAction paramBaseAction)
  {
    String str1;
    String str2;
    String str3;
    for (;;)
    {
      String str4;
      try
      {
        str4 = a(paramString);
        str1 = paramPushTaskBean.getMessageId();
        str2 = paramPushTaskBean.getTaskId();
        str3 = ((com.igexin.push.core.bean.m)paramBaseAction).a();
        b(paramString);
        if (str4 == null) {
          break;
        }
        paramBaseAction = new HashMap();
        paramBaseAction.put("messageId", str1);
        paramBaseAction.put("taskId", str2);
        paramBaseAction.put("id", str3);
        paramBaseAction.put("pkgName", paramString);
        paramPushTaskBean = new StringBuffer();
        paramPushTaskBean.append(g.g.getPackageName());
        paramPushTaskBean.append("#");
        paramPushTaskBean.append(d(paramString));
        paramPushTaskBean.append("#");
        paramPushTaskBean.append(paramString);
        paramPushTaskBean.append("/");
        if (!str4.equals(b)) {
          break label388;
        }
        paramPushTaskBean.append(b);
        paramPushTaskBean.append("#");
        if (!a(paramString, b)) {
          continue;
        }
        paramPushTaskBean.append("0");
      }
      catch (Exception paramString)
      {
        try
        {
          Intent localIntent = new Intent();
          localIntent.setClassName(paramString, str4);
          localIntent.putExtra("action", "com.igexin.action.initialize.slave");
          localIntent.putExtra("op_app", g.e);
          localIntent.putExtra("isSlave", true);
          g.g.startService(localIntent);
          paramBaseAction.put("serviceName", b);
          a(paramBaseAction);
          paramPushTaskBean.append("1");
        }
        catch (Exception paramString)
        {
          com.igexin.a.a.c.a.b(paramString.toString());
          a(paramPushTaskBean, str1, str2, str3);
          return;
        }
        paramString = paramString;
        com.igexin.a.a.c.a.b(paramString.toString());
        return;
      }
      b("30025", paramPushTaskBean.toString(), str1, str2, str3);
      com.igexin.a.a.c.a.b("feedback actionId=30025 result=" + paramPushTaskBean.toString());
      return;
      if ((paramBoolean != true) || (!b(paramString, str4)))
      {
        a(paramPushTaskBean, str1, str2, str3);
        return;
        label388:
        if (str4.equals(d))
        {
          paramPushTaskBean.append(d);
          paramPushTaskBean.append("#");
          if (a(paramString, d))
          {
            paramPushTaskBean.append("0");
          }
          else
          {
            if (!b(paramString, str4))
            {
              a(paramPushTaskBean, str1, str2, str3);
              return;
            }
            paramBaseAction.put("serviceName", d);
            a(paramBaseAction);
            paramPushTaskBean.append("1");
          }
        }
        else if (str4.equals(c))
        {
          paramPushTaskBean.append(c);
          paramPushTaskBean.append("#");
          if (a(paramString, c))
          {
            paramPushTaskBean.append("0");
          }
          else
          {
            if (!b(paramString, str4))
            {
              a(paramPushTaskBean, str1, str2, str3);
              return;
            }
            paramBaseAction.put("serviceName", c);
            a(paramBaseAction);
            paramPushTaskBean.append("1");
          }
        }
      }
    }
    if (((com.igexin.push.core.bean.m)paramBaseAction).d() != null) {}
    for (paramString = ((com.igexin.push.core.bean.m)paramBaseAction).d();; paramString = "")
    {
      if (((com.igexin.push.core.bean.m)paramBaseAction).c() != null) {}
      for (paramPushTaskBean = ((com.igexin.push.core.bean.m)paramBaseAction).c();; paramPushTaskBean = "")
      {
        a(str1, str2, str3, paramString, paramPushTaskBean);
        return;
      }
    }
  }
  
  private void a(StringBuffer paramStringBuffer, String paramString1, String paramString2, String paramString3)
  {
    paramStringBuffer.append("-1");
    b("30025", paramStringBuffer.toString(), paramString1, paramString2, paramString3);
    com.igexin.a.a.c.a.b("feedback actionId=30025 result=" + paramStringBuffer.toString());
  }
  
  private void a(Map paramMap)
  {
    f.a().a(new m(this, 180000L, paramMap));
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    List localList = ((ActivityManager)g.g.getSystemService("activity")).getRunningServices(2000);
    if (localList.size() <= 0) {}
    for (;;)
    {
      return false;
      int i = 0;
      while (i < localList.size())
      {
        if ((((ActivityManager.RunningServiceInfo)localList.get(i)).service.getClassName().equals(paramString2)) && (((ActivityManager.RunningServiceInfo)localList.get(i)).service.getPackageName().equals(paramString1))) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  private void b(String paramString)
  {
    if (c(paramString)) {}
    try
    {
      paramString = g.g.getContentResolver().query(Uri.parse("content://downloads." + paramString + "/download"), null, null, null, null);
      if (paramString != null) {
        paramString.close();
      }
      return;
    }
    catch (Exception paramString)
    {
      com.igexin.a.a.c.a.b(paramString.toString());
    }
  }
  
  private void b(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    PushTaskBean localPushTaskBean = new PushTaskBean();
    localPushTaskBean.setAppid(g.a);
    localPushTaskBean.setMessageId(paramString3);
    localPushTaskBean.setTaskId(paramString4);
    localPushTaskBean.setId(paramString5);
    localPushTaskBean.setAppKey(g.b);
    e.a().a(localPushTaskBean, paramString1, paramString2);
  }
  
  private boolean b(String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setClassName(paramString1, paramString2);
      g.g.startService(localIntent);
      return true;
    }
    catch (Exception paramString1)
    {
      com.igexin.a.a.c.a.b(paramString1.toString());
    }
    return false;
  }
  
  private boolean c(String paramString)
  {
    try
    {
      this.a = g.g.getPackageManager();
      Object localObject1 = this.a.getPackageInfo(paramString, 8);
      if (localObject1 == null) {
        return false;
      }
      localObject1 = ((PackageInfo)localObject1).providers;
      if ((localObject1 != null) && (localObject1.length != 0))
      {
        int j = localObject1.length;
        int i = 0;
        while (i < j)
        {
          Object localObject2 = localObject1[i];
          if (localObject2.name.equals("com.igexin.download.DownloadProvider"))
          {
            boolean bool = localObject2.authority.equals("downloads." + paramString);
            if (bool) {
              return true;
            }
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Exception paramString) {}
  }
  
  private String d(String paramString)
  {
    try
    {
      this.a = g.g.getPackageManager();
      paramString = this.a.getApplicationInfo(paramString, 128).metaData;
      if (paramString != null)
      {
        Iterator localIterator = paramString.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          if (str.equals("PUSH_APPID"))
          {
            paramString = paramString.get(str).toString();
            return paramString;
          }
        }
      }
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public b a(PushTaskBean paramPushTaskBean, BaseAction paramBaseAction)
  {
    return b.a;
  }
  
  public BaseAction a(JSONObject paramJSONObject)
  {
    com.igexin.push.core.bean.m localM;
    for (;;)
    {
      try
      {
        if ((com.igexin.push.config.l.r == true) && (paramJSONObject.has("do")) && (paramJSONObject.has("actionid")) && (paramJSONObject.has("type")) && ((paramJSONObject.has("pkgname")) || (paramJSONObject.has("appid")) || (paramJSONObject.has("cid"))))
        {
          localM = new com.igexin.push.core.bean.m();
          localM.setType("wakeupsdk");
          localM.setActionId(paramJSONObject.getString("actionid"));
          localM.setDoActionId(paramJSONObject.getString("do"));
          if (paramJSONObject.has("pkgname"))
          {
            localM.b(paramJSONObject.getString("pkgname"));
            if (paramJSONObject.has("is_forcestart")) {
              localM.a(paramJSONObject.getBoolean("is_forcestart"));
            }
            if (!paramJSONObject.has("id")) {
              break;
            }
            localM.a(paramJSONObject.getString("id"));
            return localM;
          }
          if (paramJSONObject.has("cid"))
          {
            localM.d(paramJSONObject.getString("cid"));
            continue;
          }
        }
        else
        {
          return null;
        }
      }
      catch (JSONException paramJSONObject)
      {
        com.igexin.a.a.c.a.b(paramJSONObject.toString());
      }
      if (paramJSONObject.has("appid")) {
        localM.c(paramJSONObject.getString("appid"));
      }
    }
    return localM;
  }
  
  public boolean b(PushTaskBean paramPushTaskBean, BaseAction paramBaseAction)
  {
    Object localObject2;
    Object localObject1;
    Object localObject3;
    int i;
    if ((paramPushTaskBean != null) && (paramBaseAction != null))
    {
      localObject2 = (com.igexin.push.core.bean.m)paramBaseAction;
      localObject1 = ((com.igexin.push.core.bean.m)localObject2).c();
      if ((localObject1 != null) || (((com.igexin.push.core.bean.m)localObject2).e() == null)) {
        break label316;
      }
      localObject3 = a(0, ((com.igexin.push.core.bean.m)localObject2).e());
      if ((localObject3 == null) || (((List)localObject3).size() != 1)) {
        break label206;
      }
      localObject1 = (String)((List)localObject3).get(0);
      i = 1;
    }
    for (;;)
    {
      if (localObject1 != null) {
        a((String)localObject1, ((com.igexin.push.core.bean.m)localObject2).b(), paramPushTaskBean, paramBaseAction);
      }
      label95:
      label139:
      label206:
      label297:
      label305:
      label313:
      for (;;)
      {
        String str1;
        String str2;
        if (i == 0)
        {
          localObject3 = paramPushTaskBean.getMessageId();
          str1 = paramPushTaskBean.getTaskId();
          str2 = ((com.igexin.push.core.bean.m)paramBaseAction).a();
          if (((com.igexin.push.core.bean.m)paramBaseAction).d() == null) {
            break label297;
          }
          localObject1 = ((com.igexin.push.core.bean.m)paramBaseAction).d();
          if (((com.igexin.push.core.bean.m)paramBaseAction).c() == null) {
            break label305;
          }
        }
        for (localObject2 = ((com.igexin.push.core.bean.m)paramBaseAction).c();; localObject2 = "")
        {
          a((String)localObject3, str1, str2, (String)localObject1, (String)localObject2);
          if (!paramBaseAction.getDoActionId().equals("")) {
            e.a().a(paramPushTaskBean.getTaskId(), paramPushTaskBean.getMessageId(), paramBaseAction.getDoActionId());
          }
          return true;
          i = 0;
          break;
          if (((com.igexin.push.core.bean.m)localObject2).d() == null) {
            break label313;
          }
          localObject1 = a(1, ((com.igexin.push.core.bean.m)localObject2).d());
          if ((localObject1 != null) && (((List)localObject1).size() > 0))
          {
            localObject1 = ((List)localObject1).iterator();
            while (((Iterator)localObject1).hasNext()) {
              a((String)((Iterator)localObject1).next(), ((com.igexin.push.core.bean.m)localObject2).b(), paramPushTaskBean, paramBaseAction);
            }
            break label95;
          }
          i = 0;
          break label95;
          localObject1 = "";
          break label139;
        }
      }
      label316:
      i = 1;
    }
  }
}
