package com.p1.chompsms.g;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import com.p1.chompsms.activities.themesettings.CustomizeFontInfo;
import com.p1.chompsms.p;
import com.p1.chompsms.system.packagemgr.a;
import com.p1.chompsms.util.Util;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public final class h
{
  static int a(String paramString1, String paramString2)
    throws XmlPullParserException
  {
    try
    {
      int i = Color.parseColor(paramString2);
      return i;
    }
    catch (Exception localException)
    {
      throw new XmlPullParserException("Failed to parse color for '" + paramString1 + "' value '" + paramString2 + "'");
    }
  }
  
  static CustomizeFontInfo a(String paramString, HashMap<String, String> paramHashMap)
    throws XmlPullParserException
  {
    return new CustomizeFontInfo(new p(a(paramString, "package-name", paramHashMap), a(paramString, "font-name", paramHashMap), a(paramString, "friendly-package-name", paramHashMap)), b(paramString, "size", paramHashMap), b(paramString, "font-style", paramHashMap));
  }
  
  public static e a(Context paramContext, String paramString)
  {
    e localE1;
    try
    {
      if (e.a(paramContext, paramString))
      {
        if (paramString.equals("Default")) {
          return a(paramContext, "com.p1.chompsms", b(paramString));
        }
        if (paramString.equals("Dark Mode")) {
          return a(paramContext, "com.p1.chompsms", b(paramString));
        }
        e localE2 = a(paramContext, "com.p1.chompsms.themes", b(paramString));
        localE1 = localE2;
        if (localE2 == null) {
          return b(paramContext, paramString);
        }
      }
      else
      {
        paramContext = e.a(e.c(paramString), paramContext);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      Log.e("ChompSms", "Failed to load theme", paramContext);
      localE1 = null;
    }
    return localE1;
  }
  
  public static e a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Object localObject = paramContext.getPackageManager().getResourcesForApplication(paramString1);
      int i = ((Resources)localObject).getIdentifier(paramString1 + ":raw/" + paramString2, null, null);
      if (i != 0)
      {
        localObject = e.b(((Resources)localObject).openRawResource(i));
        ((e)localObject).f = paramString1;
        ((e)localObject).g = i;
        ((e)localObject).e(paramContext);
        return localObject;
      }
    }
    catch (Exception paramContext)
    {
      Log.w("ChompSms", "Error opening theme: " + paramString2 + " : " + paramContext.getMessage(), paramContext);
    }
    return null;
  }
  
  static String a(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(9);
    localStringBuilder.append("#");
    localStringBuilder.append(b(Color.alpha(paramInt)));
    localStringBuilder.append(b(Color.red(paramInt)));
    localStringBuilder.append(b(Color.green(paramInt)));
    localStringBuilder.append(b(Color.blue(paramInt)));
    return localStringBuilder.toString();
  }
  
  private static String a(String paramString1, String paramString2, HashMap<String, String> paramHashMap)
    throws XmlPullParserException
  {
    if (!paramHashMap.containsKey(paramString2)) {
      throw new XmlPullParserException(paramString1 + " missing attribute " + paramString2);
    }
    return (String)paramHashMap.get(paramString2);
  }
  
  public static ArrayList<String> a()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = a.a.a().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (str.startsWith("com.p1.chompsms.themes.")) {
        localArrayList.add(str);
      }
    }
    return localArrayList;
  }
  
  public static ArrayList<e> a(Context paramContext)
  {
    File[] arrayOfFile = new File(e.p).listFiles(new FilenameFilter()
    {
      public final boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        return paramAnonymousString.endsWith(".zip");
      }
    });
    ArrayList localArrayList = new ArrayList();
    int i;
    int j;
    if (arrayOfFile == null)
    {
      i = 0;
      j = 0;
      label40:
      if (j >= i) {}
    }
    else
    {
      for (;;)
      {
        try
        {
          localE = e.a(arrayOfFile[j], paramContext);
          if (!localE.b.equals("Default"))
          {
            boolean bool = localE.b.equals("Dark Mode");
            if (!bool) {
              continue;
            }
          }
        }
        catch (Exception localException)
        {
          e localE;
          Log.w("ChompSms", localException.getMessage() + " theme file " + arrayOfFile[j], localException);
          continue;
        }
        j += 1;
        break label40;
        i = arrayOfFile.length;
        break;
        localE.e(paramContext);
        localArrayList.add(localE);
      }
    }
    return localArrayList;
  }
  
  private static void a(Context paramContext, ArrayList<e> paramArrayList, String paramString, String[] paramArrayOfString)
  {
    int k = paramArrayOfString.length;
    int i = 0;
    if (i < k)
    {
      e localE = a(paramContext, paramString, paramArrayOfString[i]);
      if (localE != null)
      {
        Iterator localIterator = paramArrayList.iterator();
        do
        {
          if (!localIterator.hasNext()) {
            break;
          }
        } while (!((e)localIterator.next()).c.equals(localE.c));
      }
      for (int j = 1;; j = 0)
      {
        if (j == 0) {
          paramArrayList.add(localE);
        }
        i += 1;
        break;
      }
    }
  }
  
  static void a(String paramString, CustomizeFontInfo paramCustomizeFontInfo, XmlSerializer paramXmlSerializer)
    throws IOException
  {
    paramXmlSerializer.startTag(null, paramString);
    paramXmlSerializer.attribute(null, "package-name", paramCustomizeFontInfo.a.a);
    paramXmlSerializer.attribute(null, "font-name", paramCustomizeFontInfo.a.c);
    paramXmlSerializer.attribute(null, "size", Integer.toString(paramCustomizeFontInfo.b));
    paramXmlSerializer.attribute(null, "font-style", Integer.toString(paramCustomizeFontInfo.c));
    try
    {
      paramXmlSerializer.attribute(null, "friendly-package-name", paramCustomizeFontInfo.a.b);
      paramXmlSerializer.endTag(null, paramString);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;)
      {
        paramCustomizeFontInfo = paramCustomizeFontInfo.a.b;
      }
    }
  }
  
  private static void a(ArrayList<e> paramArrayList)
  {
    Iterator localIterator = a().iterator();
    label63:
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      f localF = f.a;
      e localE = localF.a(str);
      if (localE != null) {}
      for (;;)
      {
        if (localE == null) {
          break label63;
        }
        paramArrayList.add(localE);
        break;
        localE = localF.b(str);
      }
    }
  }
  
  public static boolean a(String paramString)
  {
    return (Util.a(e.r, paramString)) || (new File(e.c(paramString)).exists());
  }
  
  static int b(String paramString1, String paramString2)
    throws XmlPullParserException
  {
    try
    {
      int i = Integer.parseInt(paramString2);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new XmlPullParserException("Failed to parse int for '" + paramString1 + "' value '" + paramString2 + "'");
    }
  }
  
  private static int b(String paramString1, String paramString2, HashMap<String, String> paramHashMap)
    throws XmlPullParserException
  {
    paramHashMap = a(paramString1, paramString2, paramHashMap);
    try
    {
      int i = Integer.parseInt(paramHashMap);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new XmlPullParserException("Failed to parse int attribute for " + paramString1 + " attribute " + paramString2 + " value '" + paramHashMap + "'");
    }
  }
  
  public static e b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    Object localObject1 = paramContext.getInstalledApplications(0);
    String str1 = e.b(paramString);
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject1).next();
      if (localApplicationInfo.packageName.startsWith("com.p1.chompsms.themes.")) {
        try
        {
          Object localObject2 = paramContext.getResourcesForApplication(localApplicationInfo);
          if (Util.a(((Resources)localObject2).getAssets().list("themes"), str1))
          {
            String str2 = "themes/" + str1;
            localObject2 = e.b(((Resources)localObject2).getAssets().open(str2));
            ((e)localObject2).f = localApplicationInfo.packageName;
            ((e)localObject2).h = str2;
            return localObject2;
          }
        }
        catch (Exception localException)
        {
          Log.e("ChompSms", "Failed to load package " + paramString, localException);
        }
      }
    }
    return null;
  }
  
  private static String b(int paramInt)
  {
    String str2 = Integer.toHexString(paramInt);
    String str1 = str2;
    if (str2.length() == 1) {
      str1 = "0" + str2;
    }
    return str1;
  }
  
  private static String b(String paramString)
  {
    return paramString.toLowerCase().replace(' ', '_') + "_theme";
  }
  
  public static ArrayList<e> b(Context paramContext)
  {
    e localE2 = null;
    ArrayList localArrayList = new ArrayList();
    a(paramContext, localArrayList, paramContext.getPackageName(), new String[] { "default_theme", "dark_mode_theme" });
    a(paramContext, localArrayList, "com.p1.chompsms.themes", new String[] { "blue_sky_theme", "dark_night_theme", "winter_snow_theme", "sgs2_inspired_theme" });
    a(localArrayList);
    Collections.sort(localArrayList);
    Iterator localIterator = localArrayList.iterator();
    e localE1 = null;
    paramContext = localE2;
    if (localIterator.hasNext())
    {
      localE2 = (e)localIterator.next();
      if (localE2.b.equals("Default")) {
        localE1 = localE2;
      }
      if (!localE2.b.equals("Dark Mode")) {
        break label174;
      }
      paramContext = localE2;
    }
    label174:
    for (;;)
    {
      break;
      if (localE1 != null)
      {
        localArrayList.remove(localE1);
        localArrayList.add(0, localE1);
      }
      if (paramContext != null)
      {
        localArrayList.remove(paramContext);
        localArrayList.add(1, paramContext);
      }
      return localArrayList;
    }
  }
}
