package cn.sharesdk.framework;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import cn.sharesdk.framework.b.b;
import cn.sharesdk.framework.b.b.a;
import cn.sharesdk.framework.b.b.d;
import cn.sharesdk.framework.utils.e;
import dalvik.system.DexFile;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class k
{
  private static boolean a = true;
  
  public k() {}
  
  private ArrayList<Platform> a(PackageInfo paramPackageInfo, Context paramContext)
  {
    if (a) {
      return d(paramContext);
    }
    localArrayList = new ArrayList();
    try
    {
      Object localObject = new DexFile(paramPackageInfo.applicationInfo.sourceDir);
      paramPackageInfo = ((DexFile)localObject).entries();
      try
      {
        ((DexFile)localObject).close();
        while ((paramPackageInfo != null) && (paramPackageInfo.hasMoreElements()))
        {
          localObject = (String)paramPackageInfo.nextElement();
          if ((((String)localObject).startsWith("cn.sharesdk")) && (!((String)localObject).contains("$"))) {
            try
            {
              localObject = Class.forName((String)localObject);
              if ((localObject != null) && (Platform.class.isAssignableFrom((Class)localObject)) && (!CustomPlatform.class.isAssignableFrom((Class)localObject)))
              {
                localObject = ((Class)localObject).getConstructor(new Class[] { Context.class });
                ((Constructor)localObject).setAccessible(true);
                localArrayList.add((Platform)((Constructor)localObject).newInstance(new Object[] { paramContext }));
              }
            }
            catch (Throwable localThrowable1)
            {
              e.b(localThrowable1);
            }
          }
        }
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          e.b(localThrowable2);
        }
      }
      return localArrayList;
    }
    catch (Throwable paramPackageInfo)
    {
      e.b(paramPackageInfo);
      return null;
    }
  }
  
  private PackageInfo c(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getPackageManager();
      paramContext = paramContext.getPackageName();
      localObject = ((PackageManager)localObject).getInstalledPackages(8192).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        boolean bool = paramContext.equals(localPackageInfo.packageName);
        if (bool) {
          return localPackageInfo;
        }
      }
    }
    catch (Throwable paramContext)
    {
      e.b(paramContext);
      return null;
    }
    return null;
  }
  
  private ArrayList<Platform> d(Context paramContext)
  {
    String[] arrayOfString = new String[31];
    arrayOfString[0] = "cn.sharesdk.douban.Douban";
    arrayOfString[1] = "cn.sharesdk.evernote.Evernote";
    arrayOfString[2] = "cn.sharesdk.facebook.Facebook";
    arrayOfString[3] = "cn.sharesdk.netease.microblog.NetEaseMicroBlog";
    arrayOfString[4] = "cn.sharesdk.renren.Renren";
    arrayOfString[5] = "cn.sharesdk.sina.weibo.SinaWeibo";
    arrayOfString[6] = "cn.sharesdk.sohu.microblog.SohuMicroBlog";
    arrayOfString[7] = "cn.sharesdk.sohu.suishenkan.SohuSuishenkan";
    arrayOfString[8] = "cn.sharesdk.kaixin.KaiXin";
    arrayOfString[9] = "cn.sharesdk.linkedin.LinkedIn";
    arrayOfString[10] = "cn.sharesdk.system.email.Email";
    arrayOfString[11] = "cn.sharesdk.system.text.ShortMessage";
    arrayOfString[12] = "cn.sharesdk.tencent.qq.QQ";
    arrayOfString[13] = "cn.sharesdk.tencent.qzone.QZone";
    arrayOfString[14] = "cn.sharesdk.tencent.weibo.TencentWeibo";
    arrayOfString[15] = "cn.sharesdk.twitter.Twitter";
    arrayOfString[16] = "cn.sharesdk.wechat.friends.Wechat";
    arrayOfString[17] = "cn.sharesdk.wechat.moments.WechatMoments";
    arrayOfString[18] = "cn.sharesdk.wechat.favorite.WechatFavorite";
    arrayOfString[19] = "cn.sharesdk.youdao.YouDao";
    arrayOfString[20] = "cn.sharesdk.google.GooglePlus";
    arrayOfString[21] = "cn.sharesdk.foursquare.FourSquare";
    arrayOfString[22] = "cn.sharesdk.pinterest.Pinterest";
    arrayOfString[23] = "cn.sharesdk.flickr.Flickr";
    arrayOfString[24] = "cn.sharesdk.tumblr.Tumblr";
    arrayOfString[25] = "cn.sharesdk.dropbox.Dropbox";
    arrayOfString[26] = "cn.sharesdk.vkontakte.VKontakte";
    arrayOfString[27] = "cn.sharesdk.instagram.Instagram";
    arrayOfString[28] = "cn.sharesdk.yixin.friends.Yixin";
    arrayOfString[29] = "cn.sharesdk.yixin.moments.YixinMoments";
    arrayOfString[30] = "cn.sharesdk.mingdao.Mingdao";
    ArrayList localArrayList = new ArrayList();
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        Object localObject = arrayOfString[i];
        try
        {
          localObject = Class.forName((String)localObject).getConstructor(new Class[] { Context.class });
          ((Constructor)localObject).setAccessible(true);
          localArrayList.add((Platform)((Constructor)localObject).newInstance(new Object[] { paramContext }));
          i += 1;
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            e.b(localThrowable);
          }
        }
      }
    }
    return localArrayList;
  }
  
  public String a()
  {
    return "2.3.6";
  }
  
  public String a(int paramInt, String paramString, HashMap<Integer, HashMap<String, Object>> paramHashMap)
  {
    paramHashMap = (HashMap)paramHashMap.get(Integer.valueOf(paramInt));
    if (paramHashMap == null) {
      return null;
    }
    paramString = paramHashMap.get(paramString);
    if (paramString == null) {}
    for (paramString = null;; paramString = String.valueOf(paramString)) {
      return paramString;
    }
  }
  
  public ArrayList<Platform> a(Context paramContext)
  {
    PackageInfo localPackageInfo = c(paramContext);
    if (localPackageInfo == null) {
      return null;
    }
    paramContext = a(localPackageInfo, paramContext);
    a(paramContext);
    return paramContext;
  }
  
  public void a(int paramInt, Platform paramPlatform)
  {
    d localD = new d();
    switch (paramInt)
    {
    }
    for (;;)
    {
      if (paramPlatform != null) {
        localD.b = paramPlatform.getPlatformId();
      }
      paramPlatform = b.a(null);
      if (paramPlatform != null) {
        paramPlatform.a(localD);
      }
      return;
      localD.a = "SHARESDK_ENTER_SHAREMENU";
      continue;
      localD.a = "SHARESDK_CANCEL_SHAREMENU";
      continue;
      localD.a = "SHARESDK_EDIT_SHARE";
      continue;
      localD.a = "SHARESDK_FAILED_SHARE";
      continue;
      localD.a = "SHARESDK_CANCEL_SHARE";
    }
  }
  
  public void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = b.a(paramContext);
    paramContext.a(paramString);
    paramContext.a(paramBoolean);
  }
  
  public void a(m paramM) {}
  
  public void a(String paramString, int paramInt)
  {
    b localB = b.a(null);
    if (localB == null) {
      return;
    }
    a localA = new a();
    localA.b = paramString;
    localA.a = paramInt;
    localB.a(localA);
  }
  
  public void a(ArrayList<Platform> paramArrayList)
  {
    if (paramArrayList == null) {
      return;
    }
    Collections.sort(paramArrayList, new l(this));
  }
  
  public boolean a(HashMap<String, Object> paramHashMap, HashMap<Integer, HashMap<String, Object>> paramHashMap1)
  {
    if ((paramHashMap == null) || (paramHashMap.size() <= 0)) {
      return false;
    }
    paramHashMap = ((ArrayList)paramHashMap.get("fakelist")).iterator();
    for (;;)
    {
      if (paramHashMap.hasNext())
      {
        HashMap localHashMap = (HashMap)paramHashMap.next();
        String str = String.valueOf(localHashMap.get("snsplat"));
        try
        {
          i = Integer.parseInt(str);
          if (i != -1) {
            paramHashMap1.put(Integer.valueOf(i), localHashMap);
          }
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            e.b(localThrowable);
            int i = -1;
          }
        }
      }
    }
    return true;
  }
  
  public int b()
  {
    return 31;
  }
  
  public void b(Context paramContext)
  {
    b.a(paramContext).a();
  }
}
