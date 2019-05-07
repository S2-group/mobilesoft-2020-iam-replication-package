package com.ihs.inputmethod.theme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LruCache;
import android.util.SparseArray;
import android.view.inputmethod.InputMethodSubtype;
import com.ihs.inputmethod.a.c;
import com.ihs.inputmethod.a.d;
import com.ihs.inputmethod.a.c.t;
import com.ihs.inputmethod.api.i.j;
import com.ihs.inputmethod.api.i.o;
import com.ihs.inputmethod.keyboard.internal.r;
import com.ihs.inputmethod.theme.bean.ResizeMode;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class d
{
  public static List<com.ihs.inputmethod.api.keyboard.a> a;
  public static List<com.ihs.inputmethod.api.keyboard.a> b;
  public static List<com.ihs.inputmethod.api.keyboard.a> c;
  public static List<com.ihs.inputmethod.api.keyboard.a> d;
  public static List<com.ihs.inputmethod.api.keyboard.a> e;
  public static boolean f;
  public static boolean g;
  static LruCache<String, com.ihs.inputmethod.api.keyboard.a> h = new LruCache(5);
  private static Context i;
  private static a j;
  private static List<String> k = new ArrayList();
  private static List<String> l = new ArrayList();
  private static List<String> m = new ArrayList();
  private static com.ihs.inputmethod.api.keyboard.a n;
  private static boolean o;
  private static com.ihs.inputmethod.api.keyboard.a p;
  private static int q;
  private static String r;
  private static final List<Integer> s;
  private static SparseArray<Integer> t;
  private static com.ihs.a.g.c u;
  
  static
  {
    a = new ArrayList();
    b = new ArrayList();
    c = new ArrayList();
    d = new ArrayList();
    e = new ArrayList();
    o = false;
    r = "en_US";
    s = new ArrayList();
    t = new SparseArray();
    t.append(1, Integer.valueOf(240));
    t.append(2, Integer.valueOf(320));
    t.append(3, Integer.valueOf(480));
    t.append(4, Integer.valueOf(640));
    b(com.ihs.inputmethod.api.i.g.b());
    u = new com.ihs.a.g.c()
    {
      public void a(String paramAnonymousString, com.ihs.a.h.b paramAnonymousB)
      {
        if (paramAnonymousString.equals("hs.commons.config.CONFIG_CHANGED")) {
          d.w();
        }
        do
        {
          do
          {
            return;
            if (!"NOTIFICATION_THEME_DOWNLOADED".equals(paramAnonymousString)) {
              break;
            }
            paramAnonymousString = paramAnonymousB.b("themeName");
            paramAnonymousB = paramAnonymousB.b("themePackageName");
          } while ((paramAnonymousB == null) || (paramAnonymousString == null));
          d.a(paramAnonymousB, paramAnonymousString, true);
          return;
        } while (!"hs.inputmethod.theme.api.RELOAD_THEME".equals(paramAnonymousString));
        d.x();
      }
    };
  }
  
  private static com.ihs.inputmethod.api.keyboard.a A(String paramString)
  {
    return o(paramString);
  }
  
  private static void A()
  {
    o.a(new Runnable()
    {
      public void run()
      {
        d.y();
        com.ihs.a.g.a.b("hs.inputmethod.theme.api.THEME_LIST_CHANGED");
      }
    });
  }
  
  private static void B()
  {
    o.a(new Runnable()
    {
      public void run()
      {
        d.y();
        b.a().b();
        com.ihs.a.g.a.b("hs.inputmethod.theme.api.THEME_LIST_CHANGED");
      }
    });
  }
  
  private static void C()
  {
    try
    {
      a(c);
      a(e);
      a(d);
      c.clear();
      e.clear();
      d.clear();
      H();
      I();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private static String D()
  {
    return com.ihs.app.b.a.a().getFilesDir().getAbsolutePath() + "/" + "Themes_config";
  }
  
  private static void E()
  {
    ArrayList localArrayList = new ArrayList();
    b(localArrayList);
    a(localArrayList);
    H();
    G();
    o.a(new Runnable()
    {
      public void run() {}
    });
    F();
    J();
  }
  
  private static void F()
  {
    v().a();
  }
  
  private static void G()
  {
    if (com.ihs.app.e.c.d())
    {
      K();
      L();
    }
  }
  
  private static void H()
  {
    List localList = com.ihs.a.b.b.d(new String[] { "Application", "ThemeContents", "offical_themes" });
    int i1 = 0;
    while (i1 < localList.size())
    {
      b((Map)localList.get(i1));
      i1 += 1;
    }
    if (a(com.ihs.inputmethod.api.h.i.a().c(1024), localList, "zipName")) {
      com.ihs.inputmethod.api.h.i.a().a(1024);
    }
  }
  
  private static void I()
  {
    try
    {
      if (g)
      {
        Object localObject1 = com.ihs.app.b.a.a().getPackageManager().getInstalledPackages(0);
        int i1 = 0;
        while (i1 < ((List)localObject1).size())
        {
          b(((PackageInfo)((List)localObject1).get(i1)).packageName, null, false);
          i1 += 1;
        }
        localObject1 = com.ihs.inputmethod.theme.download.c.a().b().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          com.ihs.inputmethod.theme.download.c.a localA = (com.ihs.inputmethod.theme.download.c.a)((Iterator)localObject1).next();
          b(localA.a, localA.b, false);
        }
      }
    }
    finally {}
  }
  
  private static void J()
  {
    if (n == null)
    {
      int i1 = h();
      if (r != null) {}
      for (String str = r;; str = "en_US")
      {
        n = new com.ihs.inputmethod.api.keyboard.a("custom_theme_common", i1, str);
        n.a(com.ihs.inputmethod.api.keyboard.a.a.a);
        return;
      }
    }
    com.ihs.a.h.d.a("HSKeyboardThemeManager", String.format("common theme: %s already loaded.", new Object[] { "custom_theme_common" }));
  }
  
  private static void K()
  {
    if (c.isEmpty()) {}
    for (;;)
    {
      return;
      Iterator localIterator = c.iterator();
      while (localIterator.hasNext()) {
        x(((com.ihs.inputmethod.api.keyboard.a)localIterator.next()).b);
      }
    }
  }
  
  private static void L()
  {
    x("custom_theme");
    x("custom_theme_common");
    j("custom_theme");
    j("custom_theme_common");
    b.a().c();
    M();
  }
  
  private static void M()
  {
    Iterator localIterator = b.iterator();
    while (localIterator.hasNext())
    {
      com.ihs.inputmethod.api.keyboard.a localA = (com.ihs.inputmethod.api.keyboard.a)localIterator.next();
      c.a().a(localA.b, null);
    }
  }
  
  public static Bitmap a(String paramString, Bitmap paramBitmap)
  {
    return a(r().b, paramString, paramBitmap);
  }
  
  public static Bitmap a(String paramString1, String paramString2, Bitmap paramBitmap)
  {
    Object localObject = paramBitmap;
    if (!TextUtils.isEmpty(paramString2))
    {
      paramString2 = a(paramString1, paramString2);
      localObject = paramBitmap;
      if (!TextUtils.isEmpty(paramString2)) {
        localObject = new File(paramString2);
      }
    }
    try
    {
      paramString1 = URLDecoder.decode(Uri.fromFile((File)localObject).toString(), "utf-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        try
        {
          int i1 = Integer.parseInt(paramString2.substring(paramString2.indexOf("@") + 1, paramString2.indexOf("@") + 2));
          paramString2 = new BitmapFactory.Options();
          paramString2.inDensity = ((Integer)t.get(i1)).intValue();
          paramString2.inTargetDensity = i.getResources().getDisplayMetrics().densityDpi;
          paramString2 = new com.b.a.b.c.a().a(paramString2).a();
          localObject = j.a().a(paramString1, paramString2);
          return localObject;
        }
        catch (Exception paramString1)
        {
          paramString1.printStackTrace();
        }
        localUnsupportedEncodingException = localUnsupportedEncodingException;
        paramString1 = Uri.fromFile((File)localObject).toString().replace("%40", "@");
        localUnsupportedEncodingException.printStackTrace();
      }
    }
    return paramBitmap;
  }
  
  public static Drawable a(Bitmap paramBitmap, ResizeMode paramResizeMode)
  {
    if (paramBitmap == null) {
      paramBitmap = null;
    }
    BitmapDrawable localBitmapDrawable;
    do
    {
      do
      {
        return paramBitmap;
        localBitmapDrawable = new BitmapDrawable(com.ihs.app.b.a.a().getResources(), paramBitmap);
        paramBitmap = localBitmapDrawable;
      } while (paramResizeMode == null);
      paramBitmap = localBitmapDrawable;
    } while (TextUtils.isEmpty(paramResizeMode.resize_mode));
    paramBitmap = paramResizeMode.resize_mode;
    int i1 = -1;
    switch (paramBitmap.hashCode())
    {
    }
    for (;;)
    {
      switch (i1)
      {
      default: 
        return localBitmapDrawable;
      }
      return t.a(localBitmapDrawable.getBitmap(), paramResizeMode);
      if (paramBitmap.equals("resizableFitXYUsingStretch"))
      {
        i1 = 0;
        continue;
        if (paramBitmap.equals("center")) {
          i1 = 1;
        }
      }
    }
  }
  
  public static Drawable a(Drawable paramDrawable)
  {
    Object localObject = com.ihs.app.b.a.a().getResources();
    StateListDrawable localStateListDrawable = new StateListDrawable();
    localObject = new LayerDrawable(new Drawable[] { paramDrawable, new ColorDrawable(((Resources)localObject).getColor(a.d.translucent_black)) });
    localStateListDrawable.addState(new int[] { 16842919 }, (Drawable)localObject);
    localStateListDrawable.addState(new int[] { 16842908 }, (Drawable)localObject);
    localStateListDrawable.addState(new int[] { 16842913 }, (Drawable)localObject);
    localStateListDrawable.addState(new int[0], paramDrawable);
    return localStateListDrawable;
  }
  
  public static Drawable a(Drawable paramDrawable, String paramString)
  {
    paramString = a(paramString, paramDrawable);
    try
    {
      paramString = t.a(paramString);
      return paramString;
    }
    catch (Resources.NotFoundException paramString) {}
    return paramDrawable;
  }
  
  public static Drawable a(String paramString, Drawable paramDrawable)
  {
    return a(r().b, paramString, paramDrawable);
  }
  
  public static Drawable a(String paramString1, String paramString2, Drawable paramDrawable)
  {
    paramString1 = a(a(paramString1, paramString2, null), r().b(paramString2));
    if (paramString1 == null) {
      return paramDrawable;
    }
    return paramString1;
  }
  
  public static String a(int paramInt)
  {
    List localList = e();
    if ((paramInt >= 0) && (paramInt < localList.size())) {
      return (String)localList.get(paramInt);
    }
    return null;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    String str1 = null;
    String str2 = n(paramString1);
    paramString1 = str1;
    if (q()) {
      paramString1 = a(str2, "sw600", paramString2);
    }
    if (p()) {
      paramString1 = a(str2, "sw600", paramString2);
    }
    str1 = paramString1;
    if (TextUtils.isEmpty(paramString1)) {
      str1 = a(str2, "default", paramString2);
    }
    return str1;
  }
  
  private static String a(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = new StringBuilder(paramString1).append("/").append(paramString2).append("/");
    int i1 = paramString1.length();
    paramString2 = s.iterator();
    while (paramString2.hasNext())
    {
      Object localObject = (Integer)paramString2.next();
      paramString1.replace(i1, paramString1.length(), paramString3);
      if (!paramString3.contains("@")) {
        paramString1.insert(paramString1.length() - 4, "@" + localObject + "x");
      }
      localObject = new File(paramString1.toString());
      if (((File)localObject).exists()) {
        paramString1 = ((File)localObject).getAbsolutePath();
      }
    }
    do
    {
      return paramString1;
      paramString1.replace(i1, paramString1.length(), paramString3);
      paramString2 = paramString1.toString();
      paramString1 = paramString2;
    } while (new File(paramString2).exists());
    return null;
  }
  
  /* Error */
  public static final Map<String, Object> a(Map paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 586	java/lang/Object:getClass	()Ljava/lang/Class;
    //   4: invokevirtual 591	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   7: checkcast 232	java/util/Map
    //   10: astore_1
    //   11: aload_1
    //   12: aload_0
    //   13: invokeinterface 594 2 0
    //   18: aload_1
    //   19: areturn
    //   20: astore_0
    //   21: aconst_null
    //   22: astore_1
    //   23: aload_0
    //   24: invokevirtual 595	java/lang/InstantiationException:printStackTrace	()V
    //   27: aload_1
    //   28: areturn
    //   29: astore_0
    //   30: aconst_null
    //   31: astore_1
    //   32: aload_0
    //   33: invokevirtual 596	java/lang/IllegalAccessException:printStackTrace	()V
    //   36: aload_1
    //   37: areturn
    //   38: astore_0
    //   39: goto -7 -> 32
    //   42: astore_0
    //   43: goto -20 -> 23
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	46	0	paramMap	Map
    //   10	27	1	localMap	Map
    // Exception table:
    //   from	to	target	type
    //   0	11	20	java/lang/InstantiationException
    //   0	11	29	java/lang/IllegalAccessException
    //   11	18	38	java/lang/IllegalAccessException
    //   11	18	42	java/lang/InstantiationException
  }
  
  public static void a()
  {
    i = com.ihs.app.b.a.a();
    f = i.getResources().getBoolean(a.c.config_custom_theme_enabled);
    r = com.ihs.inputmethod.language.g.a().l().getLocale();
    g = com.ihs.a.b.b.a(false, new String[] { "Application", "DownloadTheme", "DownloadThemeEnable" });
    if (g) {
      l = com.ihs.a.b.b.d(new String[] { "Application", "DownloadTheme", "DownloadThemePkNamePrefix" });
    }
    k = Arrays.asList(com.ihs.app.b.a.a().getResources().getStringArray(com.ihs.inputmethod.a.a.themes_buildin_names));
    E();
    if (j == null)
    {
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
      localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
      localIntentFilter.addDataScheme("package");
      j = new a(null);
      i.registerReceiver(j, localIntentFilter);
    }
    com.ihs.a.g.a.a("hs.commons.config.CONFIG_CHANGED", u);
    com.ihs.a.g.a.a("NOTIFICATION_THEME_DOWNLOADED", u);
    com.ihs.a.g.a.a("hs.inputmethod.theme.api.RELOAD_THEME", u);
  }
  
  public static void a(SharedPreferences paramSharedPreferences)
  {
    paramSharedPreferences.edit().remove("pref_keyboard_layout_20160417").apply();
  }
  
  public static void a(com.ihs.inputmethod.api.keyboard.a paramA)
  {
    if ((p == null) || ((paramA != null) && (!p.b.equals(paramA.b)))) {
      p = paramA;
    }
  }
  
  public static void a(r paramR) {}
  
  private static void a(File paramFile)
  {
    for (;;)
    {
      File localFile;
      try
      {
        ZipFile localZipFile = new ZipFile(paramFile);
        paramFile = new File(paramFile.getParentFile(), "Themes");
        paramFile.mkdir();
        Enumeration localEnumeration = localZipFile.entries();
        if (localEnumeration.hasMoreElements())
        {
          ZipEntry localZipEntry = (ZipEntry)localEnumeration.nextElement();
          localFile = new File(paramFile, localZipEntry.getName());
          localFile.getParentFile().mkdirs();
          if (!localZipEntry.isDirectory()) {
            a(localFile, localZipEntry, localZipFile);
          }
        }
        else
        {
          return;
        }
      }
      catch (IOException paramFile)
      {
        paramFile.printStackTrace();
      }
      localFile.mkdirs();
    }
  }
  
  private static void a(File paramFile, ZipEntry paramZipEntry, ZipFile paramZipFile)
  {
    try
    {
      paramZipEntry = new BufferedInputStream(paramZipFile.getInputStream(paramZipEntry), 1024);
      paramFile = new BufferedOutputStream(new FileOutputStream(paramFile), 1024);
      paramZipFile = new byte['Ѐ'];
      for (;;)
      {
        int i1 = paramZipEntry.read(paramZipFile);
        if (i1 <= 0) {
          break;
        }
        paramFile.write(paramZipFile, 0, i1);
      }
      paramZipEntry.close();
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
      return;
    }
    paramFile.close();
  }
  
  private static void a(ArrayList<String> paramArrayList)
  {
    int i1 = 0;
    while (i1 < paramArrayList.size())
    {
      e((String)paramArrayList.get(i1));
      i1 += 1;
    }
  }
  
  private static void a(List<com.ihs.inputmethod.api.keyboard.a> paramList)
  {
    a.removeAll(paramList);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      com.ihs.inputmethod.api.keyboard.a localA = (com.ihs.inputmethod.api.keyboard.a)paramList.next();
      m.remove(localA.b);
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    o = paramBoolean;
  }
  
  public static boolean a(InputMethodSubtype paramInputMethodSubtype)
  {
    com.ihs.a.h.d.a("reloadKeyboardThemeBySubType", "reloadKeyboardThemeBySubType " + paramInputMethodSubtype.getLocale().toString() + " to " + r);
    if (r.equals(paramInputMethodSubtype.getLocale())) {
      return false;
    }
    r = paramInputMethodSubtype.getLocale();
    return true;
  }
  
  private static boolean a(InputStream paramInputStream, String paramString)
  {
    paramString = new File(com.ihs.app.b.a.a().getFilesDir().getAbsolutePath(), paramString);
    FileOutputStream localFileOutputStream;
    try
    {
      localFileOutputStream = new FileOutputStream(paramString);
      byte[] arrayOfByte = new byte['ࠀ'];
      for (;;)
      {
        int i1 = paramInputStream.read(arrayOfByte);
        if (i1 <= 0) {
          break;
        }
        localFileOutputStream.write(arrayOfByte, 0, i1);
      }
      if (!paramString.exists()) {}
    }
    catch (IOException paramInputStream)
    {
      paramInputStream.printStackTrace();
    }
    for (;;)
    {
      a(paramString);
      paramString.delete();
      return true;
      paramInputStream.close();
      localFileOutputStream.close();
    }
  }
  
  public static boolean a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      int i1 = b.size() - 1;
      while (i1 >= 0)
      {
        com.ihs.inputmethod.api.keyboard.a localA = (com.ihs.inputmethod.api.keyboard.a)b.get(i1);
        if (paramString.equals(localA.b))
        {
          b.remove(i1);
          a.remove(localA);
          m.remove(paramString);
          return s(paramString);
        }
        i1 -= 1;
      }
    }
    return false;
  }
  
  public static boolean a(Set<String> paramSet, List<Map<String, Object>> paramList, String paramString)
  {
    int i2 = paramSet.size();
    int i1 = 0;
    boolean bool1 = false;
    boolean bool2 = bool1;
    Object localObject1;
    Object localObject2;
    if (i1 < paramList.size())
    {
      localObject1 = (Map)paramList.get(i1);
      localObject2 = ((Map)localObject1).get("showNewMark");
      if ((localObject2 == null) || (!(localObject2 instanceof Boolean))) {
        break label214;
      }
    }
    label214:
    for (bool2 = ((Boolean)localObject2).booleanValue();; bool2 = false)
    {
      if (!bool2)
      {
        i1 += 1;
        break;
      }
      localObject1 = (String)((Map)localObject1).get(paramString);
      if (i2 == 0)
      {
        com.ihs.a.h.d.a("cjx,localReadSize is 0,find new item name is:" + (String)localObject1);
        bool2 = true;
        return bool2;
      }
      localObject2 = paramSet.iterator();
      do
      {
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
      } while (!((String)localObject1).equals((String)((Iterator)localObject2).next()));
      for (bool2 = false;; bool2 = true)
      {
        bool1 = bool2;
        if (!bool2) {
          break;
        }
        com.ihs.a.h.d.a("cjx,find new item name is:" + (String)localObject1);
        return true;
      }
    }
  }
  
  public static Drawable b(String paramString1, String paramString2)
  {
    paramString1 = c(paramString1, paramString2);
    if (paramString1 != null) {
      return new BitmapDrawable(paramString1);
    }
    return null;
  }
  
  private static com.ihs.inputmethod.api.keyboard.a b(SharedPreferences paramSharedPreferences)
  {
    localObject = null;
    String str = paramSharedPreferences.getString("pref_keyboard_layout_20110916", null);
    if (str != null) {
      paramSharedPreferences.edit().remove("pref_keyboard_layout_20110916").apply();
    }
    try
    {
      int i1 = Integer.parseInt(str);
      paramSharedPreferences = Integer.valueOf(i1);
    }
    catch (NumberFormatException paramSharedPreferences)
    {
      for (;;)
      {
        paramSharedPreferences = localObject;
      }
    }
    if ((paramSharedPreferences != null) && (paramSharedPreferences.intValue() >= 0) && (paramSharedPreferences.intValue() < c.size())) {
      return (com.ihs.inputmethod.api.keyboard.a)c.get(paramSharedPreferences.intValue());
    }
    return o();
  }
  
  public static String b(String paramString)
  {
    return com.ihs.app.b.a.a().getFilesDir().getAbsolutePath() + "/" + "Themes" + "/" + paramString;
  }
  
  public static void b()
  {
    p.a();
  }
  
  private static void b(int paramInt)
  {
    int i1 = 0;
    int i3 = 0;
    if (s.size() == 0)
    {
      if (paramInt > ((Integer)t.valueAt(0)).intValue())
      {
        int i2 = 1;
        for (;;)
        {
          i1 = i3;
          if (i2 >= 4) {
            break;
          }
          i1 = i3;
          if (paramInt < ((Integer)t.valueAt(i2)).intValue()) {
            break;
          }
          i3 = i2;
          i2 += 1;
        }
      }
      paramInt = i1;
      while (paramInt < 4)
      {
        s.add(Integer.valueOf(t.keyAt(paramInt)));
        paramInt += 1;
      }
      paramInt = i1 - 1;
      while (paramInt >= 0)
      {
        s.add(Integer.valueOf(t.keyAt(paramInt)));
        paramInt -= 1;
      }
    }
  }
  
  public static void b(com.ihs.inputmethod.api.keyboard.a paramA)
  {
    b.remove(paramA);
    a.remove(paramA);
    m.remove(paramA.b);
    com.ihs.inputmethod.api.i.i.b(new File(b(paramA.b)));
    if (paramA.b.equals(r().b))
    {
      a(PreferenceManager.getDefaultSharedPreferences(com.ihs.app.b.a.a()));
      com.ihs.inputmethod.api.c.b.c();
    }
  }
  
  private static void b(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (!g) {
      return;
    }
    Iterator localIterator = l.iterator();
    Object localObject = paramString2;
    label19:
    com.ihs.inputmethod.api.keyboard.a localA;
    for (;;)
    {
      if (localIterator.hasNext()) {
        if (paramString1.startsWith((String)localIterator.next()))
        {
          paramString2 = (String)localObject;
          if (localObject == null)
          {
            paramString2 = v(paramString1);
            z(paramString1);
          }
          if (paramString2 == null) {
            break;
          }
          localObject = paramString2;
          if (m.contains(paramString2))
          {
            localObject = paramString2;
            if (!d.contains(paramString2))
            {
              localObject = e.iterator();
              do
              {
                if (!((Iterator)localObject).hasNext()) {
                  break;
                }
                localA = (com.ihs.inputmethod.api.keyboard.a)((Iterator)localObject).next();
              } while (!paramString2.equals(localA.b));
            }
          }
        }
      }
    }
    for (;;)
    {
      localObject = paramString2;
      if (localA == null) {
        break label19;
      }
      localObject = paramString2;
      if (localA.b == null) {
        break label19;
      }
      e.remove(localA);
      localA.a(com.ihs.inputmethod.api.keyboard.a.a.e);
      d.add(localA);
      localObject = paramString2;
      if (!paramBoolean) {
        break label19;
      }
      com.ihs.a.g.a.b("hs.inputmethod.theme.api.THEME_LIST_CHANGED");
      localObject = paramString2;
      break label19;
      break;
      localA = null;
    }
  }
  
  private static void b(ArrayList<String> paramArrayList)
  {
    File[] arrayOfFile = new File(g()).listFiles();
    Pattern localPattern = Pattern.compile("[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}", 2);
    if (arrayOfFile != null)
    {
      int i1 = 0;
      if (i1 < arrayOfFile.length)
      {
        Object localObject = arrayOfFile[i1];
        if (!((File)localObject).isDirectory()) {}
        for (;;)
        {
          i1 += 1;
          break;
          localObject = ((File)localObject).getName();
          if (localPattern.matcher((CharSequence)localObject).matches()) {
            paramArrayList.add(localObject);
          }
        }
      }
    }
  }
  
  private static void b(Map<String, Object> paramMap)
  {
    boolean bool = false;
    String str = (String)paramMap.get("zipName");
    if (!m.contains(str))
    {
      Object localObject = paramMap.get("showNewMark");
      if (localObject != null) {
        bool = ((Boolean)localObject).booleanValue();
      }
      if (k.contains(str))
      {
        i1 = h();
        if (r != null) {}
        for (localObject = r;; localObject = "en_US")
        {
          localObject = new com.ihs.inputmethod.api.keyboard.a(str, i1, (String)localObject);
          ((com.ihs.inputmethod.api.keyboard.a)localObject).a(com.ihs.inputmethod.api.keyboard.a.a.c);
          ((com.ihs.inputmethod.api.keyboard.a)localObject).a((String)paramMap.get("showName"), (String)paramMap.get("themeDesc"), (String)paramMap.get("themePkName"), (String)paramMap.get("bannerImgUrl"), (String)paramMap.get("mediumPreviewUrl"), (String)paramMap.get("largePreviewUrl"), bool);
          c.add(localObject);
          a.add(localObject);
          m.add(str);
          return;
        }
      }
      int i1 = h();
      if (r != null) {}
      for (localObject = r;; localObject = "en_US")
      {
        localObject = new com.ihs.inputmethod.api.keyboard.a(str, i1, (String)localObject);
        ((com.ihs.inputmethod.api.keyboard.a)localObject).a(com.ihs.inputmethod.api.keyboard.a.a.d);
        ((com.ihs.inputmethod.api.keyboard.a)localObject).a((String)paramMap.get("showName"), (String)paramMap.get("themeDesc"), (String)paramMap.get("themePkName"), (String)paramMap.get("bannerImgUrl"), (String)paramMap.get("mediumPreviewUrl"), (String)paramMap.get("largePreviewUrl"), bool);
        ((com.ihs.inputmethod.api.keyboard.a)localObject).a((String)paramMap.get("apkUrl"));
        e.add(localObject);
        a.add(localObject);
        m.add(str);
        return;
      }
    }
    com.ihs.a.h.d.a("HSKeyboardThemeManager", String.format("builtIn theme: %s already loaded.", new Object[] { str }));
  }
  
  public static Bitmap c(String paramString1, String paramString2)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    int i1 = com.ihs.app.b.a.a().getResources().getDisplayMetrics().densityDpi;
    if (Build.VERSION.SDK_INT >= 16) {}
    for (localOptions.inDensity = u();; localOptions.inDensity = u())
    {
      localOptions.inTargetDensity = i1;
      Object localObject = com.ihs.inputmethod.api.h.c.a(paramString1) + "/" + paramString2;
      com.b.a.b.c localC = new com.b.a.b.c.a().a(localOptions).a(true).a();
      Bitmap localBitmap = j.a().a(Uri.fromFile(new File((String)localObject)).toString().replace("%20", " "), localC);
      localObject = localBitmap;
      if (localBitmap == null)
      {
        localOptions.inDensity = 320;
        paramString1 = com.ihs.inputmethod.api.h.c.a(paramString1) + "/xhdpi/" + paramString2;
        localObject = j.a().a(Uri.fromFile(new File(paramString1)).toString().replace("%20", " "), localC);
      }
      return localObject;
    }
  }
  
  private static com.ihs.inputmethod.api.keyboard.a c(SharedPreferences paramSharedPreferences)
  {
    paramSharedPreferences.edit().remove("pref_keyboard_layout_20160417").apply();
    paramSharedPreferences = o();
    a(paramSharedPreferences);
    return paramSharedPreferences;
  }
  
  public static String c(String paramString)
  {
    return String.format("%s/%s", new Object[] { D(), paramString });
  }
  
  public static void c()
  {
    int i1 = h();
    if (r != null) {}
    for (String str = r;; str = "en_US")
    {
      n = new com.ihs.inputmethod.api.keyboard.a("custom_theme_common", i1, str);
      n.a();
      return;
    }
  }
  
  private static boolean c(com.ihs.inputmethod.api.keyboard.a paramA)
  {
    return (paramA != null) && (!e.contains(paramA));
  }
  
  public static Drawable d(String paramString)
  {
    Object localObject2 = "tabbar_" + paramString + "_selected";
    Object localObject3 = "tabbar_" + paramString + "_unselected";
    Object localObject1 = (String)localObject2 + ".png";
    paramString = (String)localObject3 + ".png";
    Drawable localDrawable1 = r().e((String)localObject2);
    localObject3 = r().e((String)localObject3);
    localObject2 = new StateListDrawable();
    Drawable localDrawable2 = a((String)localObject1, localDrawable1);
    ((StateListDrawable)localObject2).addState(new int[] { 16842908 }, localDrawable2);
    localDrawable2 = a((String)localObject1, localDrawable1);
    ((StateListDrawable)localObject2).addState(new int[] { 16842919 }, localDrawable2);
    localObject1 = a((String)localObject1, localDrawable1);
    ((StateListDrawable)localObject2).addState(new int[] { 16842913 }, (Drawable)localObject1);
    paramString = a(paramString, (Drawable)localObject3);
    ((StateListDrawable)localObject2).addState(new int[0], paramString);
    return localObject2;
  }
  
  public static String d()
  {
    com.ihs.inputmethod.api.keyboard.a localA2 = r();
    com.ihs.inputmethod.api.keyboard.a localA1 = localA2;
    if (localA2 == null) {
      localA1 = o();
    }
    if (localA1 == null) {
      return "";
    }
    return localA1.b;
  }
  
  public static List<String> e()
  {
    return m;
  }
  
  public static void e(String paramString)
  {
    if (!m.contains(paramString))
    {
      int i1 = h();
      if (r != null) {}
      for (Object localObject = r;; localObject = "en_US")
      {
        localObject = new com.ihs.inputmethod.api.keyboard.a(paramString, i1, (String)localObject);
        ((com.ihs.inputmethod.api.keyboard.a)localObject).a(com.ihs.inputmethod.api.keyboard.a.a.b);
        a.add(b.size(), localObject);
        m.add(b.size(), paramString);
        b.add(localObject);
        com.ihs.a.g.a.a("hs.inputmethod.theme.api.THEME_LIST_CHANGED");
        return;
      }
    }
    com.ihs.a.h.d.a("HSKeyboardThemeManager", String.format("custom theme: %s already loaded.", new Object[] { paramString }));
  }
  
  public static int f()
  {
    List localList = e();
    String str = d();
    int i1 = 0;
    while (i1 < localList.size())
    {
      if (((String)localList.get(i1)).equalsIgnoreCase(str)) {
        return i1;
      }
      i1 += 1;
    }
    return 0;
  }
  
  public static String f(String paramString)
  {
    return n(paramString) + File.separator + "default" + File.separator + "preview_large@2x.jpg";
  }
  
  public static String g()
  {
    return com.ihs.app.b.a.a().getFilesDir().getAbsolutePath() + "/" + "Themes";
  }
  
  public static String g(String paramString)
  {
    return n(paramString) + File.separator + "default" + File.separator + "custom_theme_share@2x.jpg";
  }
  
  public static int h()
  {
    return i.getResources().getIdentifier("KeyboardTheme.COMMON", "style", i.getPackageName());
  }
  
  public static Drawable h(String paramString)
  {
    try
    {
      BitmapDrawable localBitmapDrawable = new BitmapDrawable(BitmapFactory.decodeStream(i.getAssets().open("themes/previews/" + paramString + "/" + "preview_medium@2x.jpg")));
      localObject = localBitmapDrawable;
      if (localBitmapDrawable != null) {}
    }
    catch (IOException localIOException)
    {
      Drawable localDrawable;
      for (;;)
      {
        try
        {
          Object localObject = a(a(paramString, "preview_medium@2x.jpg", com.ihs.inputmethod.api.i.h.b()));
          return localObject;
        }
        catch (Resources.NotFoundException paramString) {}
        localIOException = localIOException;
        localDrawable = null;
      }
      return localDrawable;
    }
  }
  
  public static Drawable i(String paramString)
  {
    Object localObject1 = null;
    try
    {
      localObject2 = BitmapFactory.decodeStream(i.getAssets().open("themes/previews/" + paramString + "/" + "preview_medium@2x.jpg"));
      localObject1 = localObject2;
    }
    catch (IOException localIOException)
    {
      Object localObject2;
      for (;;) {}
    }
    localObject2 = localObject1;
    if (localObject1 == null) {}
    try
    {
      localObject2 = a(paramString, "preview_medium@2x.jpg", com.ihs.inputmethod.api.i.c.a());
      paramString = Bitmap.createBitmap(((Bitmap)localObject2).getWidth(), ((Bitmap)localObject2).getWidth() / 2, Bitmap.Config.ARGB_8888);
      new Canvas(paramString).drawBitmap((Bitmap)localObject2, 0.0F, 0.0F, new Paint());
      return new BitmapDrawable(paramString);
    }
    catch (Resources.NotFoundException paramString)
    {
      for (;;)
      {
        localObject2 = localObject1;
      }
    }
  }
  
  public static boolean i()
  {
    return (b.contains(r())) || (s());
  }
  
  public static String j()
  {
    switch (q)
    {
    default: 
      return "style_portrait";
    case 1: 
      return "style_portrait";
    }
    return "style_landscape";
  }
  
  public static boolean j(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    File localFile;
    do
    {
      return false;
      localFile = new File(com.ihs.app.b.a.a().getFilesDir().getAbsolutePath(), paramString + ".zip");
    } while (new File(g() + "/" + paramString).exists());
    Object localObject = com.ihs.app.b.a.a().getAssets();
    FileOutputStream localFileOutputStream;
    try
    {
      localObject = ((AssetManager)localObject).open(String.format("%s/%s.zip", new Object[] { "themes/skins", paramString }));
      localFileOutputStream = new FileOutputStream(localFile);
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i1 = ((InputStream)localObject).read(arrayOfByte);
        if (i1 <= 0) {
          break;
        }
        localFileOutputStream.write(arrayOfByte, 0, i1);
      }
      if (!localFile.exists()) {}
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    for (;;)
    {
      a(localFile);
      localFile.delete();
      if (com.ihs.a.h.d.a()) {
        k(paramString);
      }
      return true;
      localIOException.close();
      localFileOutputStream.close();
    }
  }
  
  public static b k()
  {
    Boolean localBoolean1 = Boolean.valueOf(com.ihs.app.b.a.a().getResources().getBoolean(a.c.isSW600DP));
    Boolean localBoolean2 = Boolean.valueOf(com.ihs.app.b.a.a().getResources().getBoolean(a.c.isSW768DP));
    int i1 = com.ihs.inputmethod.api.i.g.a(i);
    boolean bool;
    if ((i1 == 0) || (i1 == 2)) {
      bool = true;
    }
    while (Boolean.valueOf(bool).booleanValue()) {
      if (localBoolean1.booleanValue())
      {
        return b.d;
        bool = false;
      }
      else
      {
        if (localBoolean2.booleanValue()) {
          return b.f;
        }
        return b.b;
      }
    }
    if (localBoolean1.booleanValue()) {
      return b.e;
    }
    if (localBoolean2.booleanValue()) {
      return b.g;
    }
    return b.c;
  }
  
  public static void k(String paramString)
  {
    if (w(paramString)) {
      com.ihs.inputmethod.api.i.i.b(String.format("%s/%s", new Object[] { "themes/skins", paramString }), String.format("%s/%s", new Object[] { g(), paramString }));
    }
  }
  
  public static int l()
  {
    return 0;
  }
  
  public static boolean l(String paramString)
  {
    Iterator localIterator = b.iterator();
    while (localIterator.hasNext()) {
      if (((com.ihs.inputmethod.api.keyboard.a)localIterator.next()).b.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static String m()
  {
    return r;
  }
  
  public static void m(String paramString)
  {
    String str = v(paramString);
    if (str != null)
    {
      b(paramString, null, true);
      com.ihs.inputmethod.api.h.h.h(str);
    }
  }
  
  public static com.ihs.inputmethod.api.keyboard.a n()
  {
    return n;
  }
  
  public static String n(String paramString)
  {
    return g() + "/" + paramString;
  }
  
  public static com.ihs.inputmethod.api.keyboard.a o()
  {
    if (!c.isEmpty()) {
      return (com.ihs.inputmethod.api.keyboard.a)c.get(0);
    }
    return null;
  }
  
  static com.ihs.inputmethod.api.keyboard.a o(String paramString)
  {
    Object localObject = (com.ihs.inputmethod.api.keyboard.a)h.get(paramString);
    if (localObject != null) {
      return localObject;
    }
    localObject = a.iterator();
    while (((Iterator)localObject).hasNext())
    {
      com.ihs.inputmethod.api.keyboard.a localA = (com.ihs.inputmethod.api.keyboard.a)((Iterator)localObject).next();
      if (localA.b.equals(paramString))
      {
        h.put(paramString, localA);
        return localA;
      }
    }
    return null;
  }
  
  public static void p(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(com.ihs.app.b.a.a()).edit().putString("pref_keyboard_layout_20160417", paramString).apply();
  }
  
  public static boolean p()
  {
    return com.ihs.app.b.a.a().getResources().getBoolean(a.c.isSW768DP);
  }
  
  public static boolean q()
  {
    return com.ihs.app.b.a.a().getResources().getBoolean(a.c.isSW600DP);
  }
  
  public static com.ihs.inputmethod.api.keyboard.a r()
  {
    if (o) {
      return n();
    }
    return p;
  }
  
  public static boolean s()
  {
    return o;
  }
  
  private static boolean s(String paramString)
  {
    boolean bool = false;
    if (!TextUtils.isEmpty(paramString)) {
      bool = com.ihs.inputmethod.api.i.i.a(new String[] { b(paramString), c(paramString) });
    }
    return bool;
  }
  
  public static int t()
  {
    b localB = k();
    switch (5.a[localB.ordinal()])
    {
    case 1: 
    default: 
      return 3;
    case 2: 
    case 3: 
      return 4;
    }
    return 5;
  }
  
  private static void t(String paramString)
  {
    try
    {
      b(paramString, null, true);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public static int u()
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return 480;
    }
    return 320;
  }
  
  private static void u(String paramString)
  {
    label198:
    for (;;)
    {
      try
      {
        if ((g) && (paramString != null))
        {
          Object localObject = l.iterator();
          if ((((Iterator)localObject).hasNext()) && (paramString.startsWith((String)((Iterator)localObject).next())))
          {
            Iterator localIterator = d.iterator();
            if (!localIterator.hasNext()) {
              break label198;
            }
            localObject = (com.ihs.inputmethod.api.keyboard.a)localIterator.next();
            if (!paramString.equals(((com.ihs.inputmethod.api.keyboard.a)localObject).d())) {
              continue;
            }
            paramString = (String)localObject;
            if ((paramString != null) && (paramString.b != null))
            {
              localObject = com.ihs.inputmethod.theme.download.a.a(com.ihs.app.b.a.a(), com.ihs.inputmethod.theme.download.c.b(paramString.b));
              if (localObject == null) {
                continue;
              }
            }
          }
        }
        return;
        paramString.a(com.ihs.inputmethod.api.keyboard.a.a.d);
        d.remove(paramString);
        e.add(paramString);
        y(paramString.b);
        if (paramString.b.equals(r().b)) {
          a(PreferenceManager.getDefaultSharedPreferences(com.ihs.app.b.a.a()));
        }
        com.ihs.inputmethod.api.c.b.c();
        com.ihs.a.g.a.a("hs.inputmethod.theme.api.THEME_LIST_CHANGED");
        continue;
        paramString = null;
      }
      finally {}
    }
  }
  
  public static com.ihs.inputmethod.api.keyboard.a v()
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(com.ihs.app.b.a.a());
    String str = localSharedPreferences.getString("pref_keyboard_layout_20160417", null);
    com.ihs.inputmethod.api.keyboard.a localA;
    if ((str == null) || (str.isEmpty()))
    {
      localA = b(localSharedPreferences);
      if (c(localA))
      {
        localSharedPreferences.edit().putString("pref_keyboard_layout_20160417", localA.b).apply();
        a(localA);
        return localA;
      }
    }
    else
    {
      localA = A(str);
      if (c(localA))
      {
        localSharedPreferences.edit().putString("pref_keyboard_layout_20160417", localA.b).apply();
        a(localA);
        return localA;
      }
    }
    Log.w("HSKeyboardThemeManager", "Unknown keyboard theme in LXX preference: " + str);
    return c(localSharedPreferences);
  }
  
  private static String v(String paramString)
  {
    Object localObject = null;
    try
    {
      String[] arrayOfString = com.ihs.app.b.a.a().createPackageContext(paramString, 2).getAssets().list("theme");
      int i2 = arrayOfString.length;
      int i1 = 0;
      for (;;)
      {
        paramString = localObject;
        if (i1 < i2)
        {
          paramString = arrayOfString[i1];
          if (paramString.endsWith(".zip")) {
            paramString = paramString.replace(".zip", "");
          }
        }
        else
        {
          return paramString;
        }
        i1 += 1;
      }
      return null;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString = paramString;
      paramString.printStackTrace();
      return null;
    }
    catch (IOException paramString)
    {
      paramString = paramString;
      paramString.printStackTrace();
      return null;
    }
    finally {}
  }
  
  private static boolean w(String paramString)
  {
    try
    {
      int i1 = com.ihs.app.b.a.a().getAssets().list(String.format("%s/%s", new Object[] { "themes/skins", paramString })).length;
      return i1 > 0;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  private static void x(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    do
    {
      return;
      paramString = new File(g() + "/" + paramString);
    } while ((!paramString.exists()) || (!paramString.isDirectory()));
    com.ihs.inputmethod.api.i.i.a(paramString);
  }
  
  private static void y(String paramString)
  {
    com.ihs.inputmethod.api.i.i.a(new File(g() + File.separator + paramString));
  }
  
  private static String z(String paramString)
  {
    for (;;)
    {
      try
      {
        localContext = com.ihs.app.b.a.a().createPackageContext(paramString, 2);
        Log.d("HSKeyboardThemeManager", "get" + paramString + " pk name");
        localAssetManager = localContext.getAssets();
        paramString = localAssetManager.list("theme");
        int i2 = paramString.length;
        i1 = 0;
        if (i1 >= i2) {
          continue;
        }
        localContext = paramString[i1];
        if (!localContext.endsWith(".zip")) {
          continue;
        }
        if (TextUtils.isEmpty("theme")) {
          continue;
        }
        paramString = "theme" + "/" + localContext;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        AssetManager localAssetManager;
        int i1;
        paramString.printStackTrace();
        return null;
      }
      catch (IOException paramString)
      {
        Context localContext;
        paramString.printStackTrace();
        continue;
        paramString = localContext;
        continue;
      }
      a(localAssetManager.open(paramString), localContext);
      paramString = localContext.replace(".zip", "");
      return paramString;
      i1 += 1;
    }
  }
  
  private static class a
    extends BroadcastReceiver
  {
    private a() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getAction();
      paramIntent = paramIntent.getData().getEncodedSchemeSpecificPart();
      if ("android.intent.action.PACKAGE_ADDED".equals(paramContext)) {
        d.q(paramIntent);
      }
      while (!"android.intent.action.PACKAGE_REMOVED".equals(paramContext)) {
        return;
      }
      d.r(paramIntent);
    }
  }
  
  public static enum b
  {
    private b() {}
  }
}
