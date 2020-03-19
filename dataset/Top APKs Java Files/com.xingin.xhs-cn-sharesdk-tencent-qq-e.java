package cn.sharesdk.tencent.qq;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.utils.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class e
  extends cn.sharesdk.framework.e
{
  private static final String[] b = { "get_user_info", "get_simple_userinfo", "get_user_profile", "get_app_friends", "add_share", "list_album", "upload_pic", "add_album", "set_user_face", "get_vip_info", "get_vip_rich_info", "get_intimate_friends_weibo", "match_nick_tips_weibo", "add_t", "add_pic_t" };
  private static e c;
  private String d;
  private String[] e;
  private String f;
  private String g;
  
  private e(Platform paramPlatform)
  {
    super(paramPlatform);
  }
  
  public static e a(Platform paramPlatform)
  {
    if (c == null) {
      c = new e(paramPlatform);
    }
    return c;
  }
  
  private void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, PlatformActionListener paramPlatformActionListener)
  {
    paramString6 = paramString5;
    if (paramString5 == null)
    {
      paramString6 = paramString5;
      if (paramString4 != null)
      {
        paramString6 = paramString5;
        if (new File(paramString4).exists()) {
          paramString6 = ((QQ)this.a).uploadImageToFileServer(paramString4);
        }
      }
    }
    for (;;)
    {
      try
      {
        paramString5 = new StringBuilder();
        paramString5.append("http://openmobile.qq.com/api/check?");
        paramString5.append("page=shareindex.html&");
        paramString5.append("style=9&");
        paramString5.append("action=shareToQQ&");
        paramString5.append("sdkv=2.2.1&");
        paramString5.append("sdkp=a&");
        paramString5.append("appId=").append(this.d).append("&");
        paramString4 = com.mob.tools.utils.c.a(this.a.getContext());
        paramString5.append("status_os=").append(com.mob.tools.utils.b.c(Build.VERSION.RELEASE, "utf-8")).append("&");
        paramString5.append("status_machine=").append(com.mob.tools.utils.b.c(Build.MODEL, "utf-8")).append("&");
        paramString5.append("status_version=").append(com.mob.tools.utils.b.c(String.valueOf(Build.VERSION.SDK_INT), "utf-8")).append("&");
        paramString4 = paramString4.j();
        if (!TextUtils.isEmpty(paramString4)) {
          paramString5.append("site=").append(com.mob.tools.utils.b.c(paramString4, "utf-8")).append("&");
        }
        if (!TextUtils.isEmpty(paramString1))
        {
          if (paramString1.length() > 40)
          {
            paramString1 = paramString1.substring(40) + "...";
            paramString4 = paramString1;
            if (paramString1.length() > 80) {
              paramString4 = paramString1.substring(80) + "...";
            }
            paramString5.append("title=").append(com.mob.tools.utils.b.c(paramString4, "utf-8")).append("&");
          }
        }
        else
        {
          if (!TextUtils.isEmpty(paramString3)) {
            paramString5.append("summary=").append(com.mob.tools.utils.b.c(paramString3, "utf-8")).append("&");
          }
          if (!TextUtils.isEmpty(paramString2)) {
            paramString5.append("targeturl=").append(com.mob.tools.utils.b.c(paramString2, "utf-8")).append("&");
          }
          if (!TextUtils.isEmpty(paramString6)) {
            paramString5.append("imageUrl=").append(com.mob.tools.utils.b.c(paramString6, "utf-8")).append("&");
          }
          paramString5.append("type=1");
          paramString1 = new k();
          paramString1.a(paramString5.toString());
          paramString1.a(paramPlatformActionListener);
          paramString1.b(this.d);
          paramString1.show(this.a.getContext(), null);
          return;
        }
      }
      catch (Throwable paramString1)
      {
        if (paramPlatformActionListener != null)
        {
          paramPlatformActionListener.onError(this.a, 9, paramString1);
          return;
        }
      }
    }
  }
  
  private String b()
  {
    int i = 0;
    if (this.e == null) {}
    StringBuilder localStringBuilder;
    for (String[] arrayOfString = b;; arrayOfString = this.e)
    {
      localStringBuilder = new StringBuilder();
      int k = arrayOfString.length;
      int j = 0;
      while (i < k)
      {
        String str = arrayOfString[i];
        if (j > 0) {
          localStringBuilder.append(',');
        }
        localStringBuilder.append(str);
        j += 1;
        i += 1;
      }
    }
    return localStringBuilder.toString();
  }
  
  private void b(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, PlatformActionListener paramPlatformActionListener)
  {
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty(paramString4)) {
          continue;
        }
        if (TextUtils.isEmpty(paramString5)) {
          break label330;
        }
      }
      catch (Throwable paramString1)
      {
        ArrayList localArrayList;
        if (paramPlatformActionListener == null) {
          break label329;
        }
        paramPlatformActionListener.onError(this.a, 9, paramString1);
        return;
        i = 1;
        if (i != 0) {
          break label336;
        }
        paramString1 = "/t/add_t";
        continue;
      }
      paramString6 = "https://graph.qq.com" + paramString1;
      localArrayList = new ArrayList();
      localArrayList.add(new com.mob.tools.b.i("oauth_consumer_key", this.d));
      localArrayList.add(new com.mob.tools.b.i("access_token", this.g));
      localArrayList.add(new com.mob.tools.b.i("openid", this.f));
      localArrayList.add(new com.mob.tools.b.i("format", "json"));
      localArrayList.add(new com.mob.tools.b.i("content", paramString3));
      if (i != 0)
      {
        paramString2 = paramString4;
        if (TextUtils.isEmpty(paramString4)) {
          paramString2 = com.mob.tools.utils.a.a(this.a.getContext(), paramString5);
        }
        paramString2 = new com.mob.tools.b.i("pic", paramString2);
        paramString1 = cn.sharesdk.framework.a.a.a().a(paramString6, localArrayList, paramString2, paramString1, c());
        if ((paramString1 == null) || (paramString1.length() <= 0) || (paramPlatformActionListener == null)) {
          break label329;
        }
        paramString2 = new com.mob.tools.utils.e().a(paramString1);
        if (((Integer)paramString2.get("ret")).intValue() != 0) {
          paramPlatformActionListener.onError(this.a, 9, new Exception(paramString1));
        }
      }
      else
      {
        paramString1 = cn.sharesdk.framework.a.a.a().b(paramString6, localArrayList, paramString1, c());
        continue;
      }
      paramPlatformActionListener.onComplete(this.a, 9, paramString2);
      return;
      label329:
      return;
      label330:
      int i = 0;
      continue;
      label336:
      paramString1 = "/t/add_pic_t";
    }
  }
  
  public void a(AuthorizeListener paramAuthorizeListener, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      b(paramAuthorizeListener);
      return;
    }
    a(new f(this, paramAuthorizeListener));
  }
  
  public void a(String paramString)
  {
    this.d = paramString;
  }
  
  public void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, boolean paramBoolean1, PlatformActionListener paramPlatformActionListener, boolean paramBoolean2)
  {
    if (paramBoolean2)
    {
      b(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramPlatformActionListener);
      return;
    }
    if ((paramBoolean1) && (a()))
    {
      String str = paramString4;
      if (!TextUtils.isEmpty(paramString4))
      {
        File localFile = new File(paramString4);
        str = paramString4;
        if (localFile.exists())
        {
          str = paramString4;
          if (paramString4.startsWith("/data/"))
          {
            str = new File(R.getCachePath(this.a.getContext(), "images"), System.currentTimeMillis() + localFile.getName()).getAbsolutePath();
            if (!R.copyFile(paramString4, str)) {
              break label267;
            }
          }
        }
      }
      for (;;)
      {
        paramString4 = new Intent();
        paramString4.putExtra("title", paramString1);
        paramString4.putExtra("titleUrl", paramString2);
        paramString4.putExtra("summary", paramString3);
        paramString4.putExtra("imagePath", str);
        paramString4.putExtra("imageUrl", paramString5);
        paramString4.putExtra("musicUrl", paramString6);
        paramString4.putExtra("appId", this.d);
        paramString1 = new i();
        paramString1.a(this.a, paramPlatformActionListener);
        paramString1.a(this.d);
        paramString1.show(this.a.getContext(), paramString4);
        return;
        label267:
        str = null;
      }
    }
    a(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramPlatformActionListener);
  }
  
  public void a(String[] paramArrayOfString)
  {
    this.e = paramArrayOfString;
  }
  
  public boolean a()
  {
    List localList = this.a.getContext().getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (localList != null)
    {
      int i = 0;
      while (i < localList.size())
      {
        localArrayList.add(((PackageInfo)localList.get(i)).packageName);
        i += 1;
      }
    }
    return (localArrayList.contains("com.tencent.mobileqq")) || (localArrayList.contains("com.tencent.mobileqqi")) || (localArrayList.contains("com.tencent.qqlite"));
  }
  
  public void b(String paramString)
  {
    this.f = paramString;
  }
  
  public HashMap<String, Object> c(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = new ArrayList();
    ((ArrayList)localObject1).add(new com.mob.tools.b.i("access_token", paramString));
    paramString = new ArrayList();
    paramString.add(new com.mob.tools.b.i("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
    paramString = cn.sharesdk.framework.a.a.a().a("https://graph.qq.com/oauth2.0/me", (ArrayList)localObject1, paramString, null, "/oauth2.0/me", c());
    localObject1 = paramString;
    if (paramString.startsWith("callback"))
    {
      for (localObject1 = paramString;; localObject1 = ((String)localObject1).substring(1))
      {
        paramString = (String)localObject1;
        if (((String)localObject1).startsWith("{")) {
          break;
        }
        paramString = (String)localObject1;
        if (((String)localObject1).length() <= 0) {
          break;
        }
      }
      for (;;)
      {
        localObject1 = paramString;
        if (paramString.endsWith("}")) {
          break;
        }
        localObject1 = paramString;
        if (paramString.length() <= 0) {
          break;
        }
        paramString = paramString.substring(0, paramString.length() - 1);
      }
    }
    paramString = localObject2;
    if (localObject1 != null)
    {
      paramString = localObject2;
      if (((String)localObject1).length() > 0) {
        paramString = new com.mob.tools.utils.e().a((String)localObject1);
      }
    }
    return paramString;
  }
  
  public void d(String paramString)
  {
    this.g = paramString;
  }
  
  public HashMap<String, Object> e(String paramString)
  {
    Object localObject1 = null;
    paramString = new ArrayList();
    paramString.add(new com.mob.tools.b.i("access_token", this.g));
    paramString.add(new com.mob.tools.b.i("oauth_consumer_key", this.d));
    paramString.add(new com.mob.tools.b.i("openid", this.f));
    Object localObject2 = new ArrayList();
    ((ArrayList)localObject2).add(new com.mob.tools.b.i("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
    localObject2 = cn.sharesdk.framework.a.a.a().a("https://graph.qq.com/user/get_simple_userinfo", paramString, (ArrayList)localObject2, null, "/user/get_simple_userinfo", c());
    paramString = localObject1;
    if (localObject2 != null)
    {
      paramString = localObject1;
      if (((String)localObject2).length() > 0) {
        paramString = new com.mob.tools.utils.e().a((String)localObject2);
      }
    }
    return paramString;
  }
  
  public String getAuthorizeUrl()
  {
    ShareSDK.logApiEvent("/oauth2.0/authorize", c());
    String str3 = b();
    String str1 = getRedirectUri();
    try
    {
      str1 = com.mob.tools.utils.b.c(str1, "utf-8");
      return "https://graph.qq.com/oauth2.0/m_authorize?response_type=token&client_id=" + this.d + "&redirect_uri=" + str1 + "&display=mobile&scope=" + str3;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        cn.sharesdk.framework.utils.d.a().w(localThrowable);
        String str2 = getRedirectUri();
      }
    }
  }
  
  public cn.sharesdk.framework.authorize.b getAuthorizeWebviewClient(cn.sharesdk.framework.authorize.g paramG)
  {
    return new c(paramG);
  }
  
  public String getRedirectUri()
  {
    return "auth://tauth.qq.com/";
  }
  
  public cn.sharesdk.framework.authorize.f getSSOProcessor(cn.sharesdk.framework.authorize.e paramE)
  {
    paramE = new g(paramE);
    paramE.a(5656);
    paramE.a(this.d, b());
    return paramE;
  }
}
