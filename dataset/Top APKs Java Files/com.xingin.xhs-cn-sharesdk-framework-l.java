package cn.sharesdk.framework;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Handler;
import com.mob.tools.utils.R;
import dalvik.system.DexFile;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class l
{
  private static boolean a = true;
  private Context b;
  private String c;
  
  public l(Context paramContext, String paramString)
  {
    this.b = paramContext;
    this.c = paramString;
  }
  
  private ArrayList<Platform> a(PackageInfo paramPackageInfo)
  {
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
                localArrayList.add((Platform)((Constructor)localObject).newInstance(new Object[] { this.b }));
              }
            }
            catch (Throwable localThrowable1)
            {
              cn.sharesdk.framework.utils.d.a().w(localThrowable1);
            }
          }
        }
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          cn.sharesdk.framework.utils.d.a().w(localThrowable2);
        }
      }
      return localArrayList;
    }
    catch (Throwable paramPackageInfo)
    {
      cn.sharesdk.framework.utils.d.a().w(paramPackageInfo);
      return null;
    }
  }
  
  private PackageInfo e()
  {
    try
    {
      Object localObject = this.b.getPackageManager();
      String str = this.b.getPackageName();
      localObject = ((PackageManager)localObject).getInstalledPackages(8192).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        boolean bool = str.equals(localPackageInfo.packageName);
        if (bool) {
          return localPackageInfo;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      cn.sharesdk.framework.utils.d.a().w(localThrowable);
      return null;
    }
    return null;
  }
  
  private ArrayList<Platform> f()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    for (;;)
    {
      if (i < 37)
      {
        Object localObject = new String[] { "cn.sharesdk.douban.Douban", "cn.sharesdk.evernote.Evernote", "cn.sharesdk.facebook.Facebook", "cn.sharesdk.renren.Renren", "cn.sharesdk.sina.weibo.SinaWeibo", "cn.sharesdk.kaixin.KaiXin", "cn.sharesdk.linkedin.LinkedIn", "cn.sharesdk.system.email.Email", "cn.sharesdk.system.text.ShortMessage", "cn.sharesdk.tencent.qq.QQ", "cn.sharesdk.tencent.qzone.QZone", "cn.sharesdk.tencent.weibo.TencentWeibo", "cn.sharesdk.twitter.Twitter", "cn.sharesdk.wechat.friends.Wechat", "cn.sharesdk.wechat.moments.WechatMoments", "cn.sharesdk.wechat.favorite.WechatFavorite", "cn.sharesdk.youdao.YouDao", "cn.sharesdk.google.GooglePlus", "cn.sharesdk.foursquare.FourSquare", "cn.sharesdk.pinterest.Pinterest", "cn.sharesdk.flickr.Flickr", "cn.sharesdk.tumblr.Tumblr", "cn.sharesdk.dropbox.Dropbox", "cn.sharesdk.vkontakte.VKontakte", "cn.sharesdk.instagram.Instagram", "cn.sharesdk.yixin.friends.Yixin", "cn.sharesdk.yixin.moments.YixinMoments", "cn.sharesdk.mingdao.Mingdao", "cn.sharesdk.line.Line", "cn.sharesdk.kakao.story.KakaoStory", "cn.sharesdk.kakao.talk.KakaoTalk", "cn.sharesdk.system.bluetooth.Bluetooth", "cn.sharesdk.whatsapp.WhatsApp", "cn.sharesdk.pocket.Pocket", "cn.sharesdk.instapaper.Instapaper", "cn.sharesdk.facebookmessenger.FacebookMessenger", "cn.sharesdk.alipay.share.Alipay" }[i];
        try
        {
          localObject = Class.forName((String)localObject).getConstructor(new Class[] { Context.class });
          ((Constructor)localObject).setAccessible(true);
          localArrayList.add((Platform)((Constructor)localObject).newInstance(new Object[] { this.b }));
          i += 1;
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            cn.sharesdk.framework.utils.d.a().d(localThrowable);
          }
        }
      }
    }
    return localArrayList;
  }
  
  public String a(int paramInt, String paramString, HashMap<Integer, HashMap<String, Object>> paramHashMap)
  {
    paramHashMap = (HashMap)paramHashMap.get(Integer.valueOf(paramInt));
    if (paramHashMap == null) {
      return null;
    }
    paramString = paramHashMap.get(paramString);
    if (paramString == null) {
      return null;
    }
    return String.valueOf(paramString);
  }
  
  public String a(Bitmap paramBitmap)
  {
    return cn.sharesdk.framework.statistics.a.a(this.b, this.c).a(paramBitmap);
  }
  
  public String a(String paramString)
  {
    return cn.sharesdk.framework.statistics.a.a(this.b, this.c).a(paramString);
  }
  
  public String a(String paramString1, boolean paramBoolean, int paramInt, String paramString2)
  {
    return cn.sharesdk.framework.statistics.a.a(this.b, this.c).a(paramString1, paramInt, paramBoolean, paramString2);
  }
  
  public ArrayList<Platform> a()
  {
    if (a) {}
    for (Object localObject = f();; localObject = a((PackageInfo)localObject))
    {
      a((ArrayList)localObject);
      return localObject;
      localObject = e();
      if (localObject == null) {
        return null;
      }
    }
  }
  
  public void a(int paramInt1, int paramInt2, HashMap<Integer, HashMap<String, Object>> paramHashMap)
  {
    paramHashMap.put(Integer.valueOf(paramInt2), (HashMap)paramHashMap.get(Integer.valueOf(paramInt1)));
  }
  
  public void a(int paramInt, Platform paramPlatform)
  {
    cn.sharesdk.framework.statistics.b.d localD = new cn.sharesdk.framework.statistics.b.d();
    switch (paramInt)
    {
    }
    for (;;)
    {
      if (paramPlatform != null) {
        localD.b = paramPlatform.getPlatformId();
      }
      paramPlatform = cn.sharesdk.framework.statistics.d.a(this.b, this.c);
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
  
  public void a(Handler paramHandler, boolean paramBoolean, int paramInt)
  {
    cn.sharesdk.framework.statistics.d localD = cn.sharesdk.framework.statistics.d.a(this.b, this.c);
    if (localD != null)
    {
      localD.a(paramHandler);
      localD.a(paramBoolean);
      localD.a(paramInt);
      localD.startThread();
    }
  }
  
  public void a(n paramN) {}
  
  public void a(String paramString, int paramInt)
  {
    cn.sharesdk.framework.statistics.d localD = cn.sharesdk.framework.statistics.d.a(this.b, this.c);
    if (localD == null) {
      return;
    }
    cn.sharesdk.framework.statistics.b.a localA = new cn.sharesdk.framework.statistics.b.a();
    localA.b = paramString;
    localA.a = paramInt;
    localD.a(localA);
  }
  
  public void a(ArrayList<Platform> paramArrayList)
  {
    if (paramArrayList == null) {
      return;
    }
    Collections.sort(paramArrayList, new m(this));
  }
  
  public boolean a(HashMap<String, Object> paramHashMap, HashMap<Integer, HashMap<String, Object>> paramHashMap1)
  {
    if ((paramHashMap == null) || (paramHashMap.size() <= 0)) {
      return false;
    }
    paramHashMap = (ArrayList)paramHashMap.get("fakelist");
    if (paramHashMap == null) {
      return false;
    }
    paramHashMap = paramHashMap.iterator();
    for (;;)
    {
      if (paramHashMap.hasNext())
      {
        HashMap localHashMap = (HashMap)paramHashMap.next();
        if (localHashMap == null) {
          continue;
        }
        String str = String.valueOf(localHashMap.get("snsplat"));
        try
        {
          i = R.parseInt(str);
          if (i != -1) {
            paramHashMap1.put(Integer.valueOf(i), localHashMap);
          }
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            cn.sharesdk.framework.utils.d.a().w(localThrowable);
            int i = -1;
          }
        }
      }
    }
    return true;
  }
  
  public void b()
  {
    cn.sharesdk.framework.statistics.d localD = cn.sharesdk.framework.statistics.d.a(this.b, this.c);
    if (localD != null) {
      localD.stopThread();
    }
  }
  
  public String c()
  {
    return "2.7.0";
  }
  
  public int d()
  {
    return 58;
  }
}
