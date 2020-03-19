package cn.sharesdk.framework;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import cn.sharesdk.framework.utils.e;
import dalvik.system.DexFile;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class h
{
  private static boolean a = true;
  private ArrayList<Platform> b;
  private HashMap<String, HashMap<String, String>> c;
  private HashMap<Integer, HashMap<String, Object>> d;
  private ArrayList<Class<?>> e;
  
  public h() {}
  
  private ArrayList<Platform> a(Context paramContext, PackageInfo paramPackageInfo)
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
              if ((localObject != null) && (Platform.class.isAssignableFrom((Class)localObject)))
              {
                this.e.add(localObject);
                localObject = ((Class)localObject).getConstructor(new Class[] { Context.class });
                ((Constructor)localObject).setAccessible(true);
                localArrayList.add((Platform)((Constructor)localObject).newInstance(new Object[] { paramContext }));
              }
            }
            catch (Throwable localThrowable1) {}
          }
        }
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          e.c(localThrowable2);
        }
      }
      return localArrayList;
    }
    catch (Throwable paramContext)
    {
      e.c(paramContext);
      return null;
    }
  }
  
  private ArrayList<Platform> a(ArrayList<Platform> paramArrayList)
  {
    if (paramArrayList == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    int j;
    int k;
    int i;
    label37:
    int m;
    if (paramArrayList.size() > 0)
    {
      j = Integer.MAX_VALUE;
      k = Integer.MIN_VALUE;
      i = 0;
      int n = paramArrayList.size();
      if (i < n)
      {
        m = ((Platform)paramArrayList.get(i)).getSortId();
        if (m >= j) {
          break label108;
        }
        k = m;
        j = i;
      }
    }
    for (;;)
    {
      i += 1;
      m = k;
      k = j;
      j = m;
      break label37;
      if (k < 0) {
        break;
      }
      localArrayList.add(paramArrayList.remove(k));
      break;
      return localArrayList;
      label108:
      m = j;
      j = k;
      k = m;
    }
  }
  
  private PackageInfo b(Context paramContext)
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
      return null;
    }
    return null;
  }
  
  private void b(Context paramContext, String paramString)
  {
    try
    {
      Object localObject = XmlPullParserFactory.newInstance();
      ((XmlPullParserFactory)localObject).setNamespaceAware(true);
      XmlPullParser localXmlPullParser = ((XmlPullParserFactory)localObject).newPullParser();
      localInputStream = paramContext.getAssets().open("ShareSDK.conf");
      localXmlPullParser.setInput(localInputStream, "utf-8");
      for (int i = localXmlPullParser.getEventType();; i = localXmlPullParser.next())
      {
        if (i == 1) {
          break label167;
        }
        if ((i == 2) && (paramString.equals(localXmlPullParser.getName())))
        {
          int j = localXmlPullParser.getAttributeCount();
          localObject = new HashMap();
          i = 0;
          for (;;)
          {
            paramContext = (Context)localObject;
            if (i >= j) {
              break;
            }
            ((HashMap)localObject).put(localXmlPullParser.getAttributeName(i), localXmlPullParser.getAttributeValue(i));
            i += 1;
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        InputStream localInputStream;
        paramContext.printStackTrace();
        paramContext = null;
        continue;
        label167:
        paramContext = null;
      }
    }
    localInputStream.close();
    this.c.put(paramString, paramContext);
  }
  
  private ArrayList<Platform> c(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.e.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Class)localIterator.next();
      try
      {
        localObject = ((Class)localObject).getConstructor(new Class[] { Context.class });
        ((Constructor)localObject).setAccessible(true);
        localArrayList.add((Platform)((Constructor)localObject).newInstance(new Object[] { paramContext }));
      }
      catch (Throwable localThrowable) {}
    }
    return localArrayList;
  }
  
  private ArrayList<Platform> d(Context paramContext)
  {
    String[] arrayOfString = new String[27];
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
    arrayOfString[18] = "cn.sharesdk.youdao.YouDao";
    arrayOfString[19] = "cn.sharesdk.google.GooglePlus";
    arrayOfString[20] = "cn.sharesdk.foursquare.FourSquare";
    arrayOfString[21] = "cn.sharesdk.pinterest.Pinterest";
    arrayOfString[22] = "cn.sharesdk.flickr.Flickr";
    arrayOfString[23] = "cn.sharesdk.tumblr.Tumblr";
    arrayOfString[24] = "cn.sharesdk.dropbox.Dropbox";
    arrayOfString[25] = "cn.sharesdk.vkontakte.VKontakte";
    arrayOfString[26] = "cn.sharesdk.instagram.Instagram";
    ArrayList localArrayList = new ArrayList();
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      Object localObject;
      if (i < j) {
        localObject = arrayOfString[i];
      }
      try
      {
        localObject = Class.forName((String)localObject);
        this.e.add(localObject);
        localObject = ((Class)localObject).getConstructor(new Class[] { Context.class });
        ((Constructor)localObject).setAccessible(true);
        localArrayList.add((Platform)((Constructor)localObject).newInstance(new Object[] { paramContext }));
        i += 1;
        continue;
        return localArrayList;
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
  }
  
  public String a(Context paramContext, int paramInt, String paramString)
  {
    if (this.d == null) {
      this.d = new HashMap();
    }
    paramContext = (HashMap)this.d.get(Integer.valueOf(paramInt));
    if (paramContext == null) {
      return null;
    }
    return String.valueOf(paramContext.get(paramString));
  }
  
  public String a(Context paramContext, String paramString)
  {
    return a(paramContext, "ShareSDK", paramString);
  }
  
  public String a(Context paramContext, String paramString1, String paramString2)
  {
    if (this.c == null) {
      this.c = new HashMap();
    }
    if ((HashMap)this.c.get(paramString1) == null) {
      b(paramContext, paramString1);
    }
    paramContext = (HashMap)this.c.get(paramString1);
    if (paramContext == null) {
      return null;
    }
    return (String)paramContext.get(paramString2);
  }
  
  public ArrayList<Platform> a(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext();
    if (this.b != null) {
      return this.b;
    }
    if ((this.e != null) && (this.e.size() > 0)) {}
    PackageInfo localPackageInfo;
    for (paramContext = c(paramContext);; paramContext = a(paramContext, localPackageInfo))
    {
      this.b = a(paramContext);
      return this.b;
      localPackageInfo = b(paramContext);
      if (localPackageInfo == null) {
        return null;
      }
      this.e = new ArrayList();
    }
  }
  
  public void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (this.c == null) {
      this.c = new HashMap();
    }
    if ((HashMap)this.c.get(paramString1) == null) {
      b(paramContext, paramString1);
    }
    HashMap localHashMap = (HashMap)this.c.get(paramString1);
    paramContext = localHashMap;
    if (localHashMap == null)
    {
      paramContext = new HashMap();
      this.c.put(paramString1, paramContext);
    }
    paramContext.put(paramString2, paramString3);
  }
  
  public boolean a()
  {
    return (this.d != null) && (this.d.size() > 0);
  }
  
  public boolean a(HashMap<String, Object> paramHashMap)
  {
    if (this.d == null) {
      this.d = new HashMap();
    }
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
            this.d.put(Integer.valueOf(i), localHashMap);
          }
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            e.c(localThrowable);
            int i = -1;
          }
        }
      }
    }
    return true;
  }
}
