package com.neulion.univision.e;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.Html;
import android.text.TextUtils;
import com.neulion.univision.application.UnivisionApplication;
import com.neulion.univision.bean.MediaItem;
import com.neulion.univision.bean.Tracking;
import com.neulion.univision.bean.UNTrackerKeys;
import com.neulion.univision.ui.a.c;
import com.neulion.univision.ui.a.t;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class r
{
  private static UNTrackerKeys a;
  
  public static float a()
  {
    float f2 = 0.0F;
    try
    {
      Object localObject = UnivisionApplication.r().getPackageManager();
      float f1 = f2;
      if (localObject != null)
      {
        localObject = ((PackageManager)localObject).getPackageInfo(UnivisionApplication.r().getPackageName(), 0);
        f1 = f2;
        if (localObject != null) {
          f1 = Float.parseFloat(((PackageInfo)localObject).versionName);
        }
      }
      return f1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0.0F;
  }
  
  public static int a(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return -1;
  }
  
  public static String a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = new Locale(m.a());
      if (paramString.indexOf("T") > 0) {}
      for (paramContext = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sszzz", paramContext);; paramContext = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", paramContext))
      {
        paramContext = paramContext.parse(paramString);
        paramString = new SimpleDateFormat(" " + t.b("MediaDateFormat"));
        paramString.setTimeZone(TimeZone.getDefault());
        return t.b("Date") + ": " + paramString.format(paramContext);
      }
      return "";
    }
    catch (Exception paramContext) {}
  }
  
  public static String a(Object paramObject, String paramString)
  {
    paramObject = b(paramObject, paramString);
    if ((paramObject instanceof String)) {
      return (String)paramObject;
    }
    if ((paramObject instanceof Boolean)) {
      return String.valueOf(paramObject);
    }
    if ((paramObject instanceof Integer)) {
      return String.valueOf(paramObject);
    }
    if ((paramObject instanceof Long)) {
      return String.valueOf(paramObject);
    }
    if ((paramObject instanceof Float)) {
      return String.valueOf(paramObject);
    }
    if ((paramObject instanceof Double)) {
      return String.valueOf(paramObject);
    }
    return "";
  }
  
  public static String a(String paramString)
  {
    try
    {
      paramString = new Date(Long.valueOf(paramString).longValue());
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
      localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
      paramString = localSimpleDateFormat.format(paramString);
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static void a(MediaItem paramMediaItem, Map<String, String> paramMap)
  {
    if ((paramMediaItem == null) || (paramMediaItem.getTracking() == null)) {
      return;
    }
    for (;;)
    {
      try
      {
        Tracking localTracking = paramMediaItem.getTracking();
        if (localTracking.getPrimary_tag() != null)
        {
          str = localTracking.getPrimary_tag();
          paramMap.put("channel", str);
          if (localTracking.getContent_type() == null) {
            break label551;
          }
          str = localTracking.getContent_type();
          paramMap.put("subchannel", str);
          if (localTracking.getShort_title() == null) {
            break label557;
          }
          str = localTracking.getShort_title();
          paramMap.put("section", str);
          if (localTracking.getUci_division() == null) {
            break label563;
          }
          str = localTracking.getUci_division();
          paramMap.put("uci_division", str);
          if (localTracking.getPrimary_tag() == null) {
            break label569;
          }
          str = localTracking.getPrimary_tag();
          paramMap.put("primary_tag", str);
          if (localTracking.getTags() == null) {
            break label575;
          }
          str = localTracking.getTagsString();
          paramMap.put("tags", str);
          if (localTracking.getParent_topic() == null) {
            break label581;
          }
          str = localTracking.getParent_topic();
          paramMap.put("parent_topic", str);
          if (localTracking.getTopics() == null) {
            break label587;
          }
          str = localTracking.getTopicsString();
          paramMap.put("topics", str);
          if (localTracking.getContent_id() == null) {
            break label593;
          }
          str = localTracking.getContent_id();
          paramMap.put("content_id", str);
          if (localTracking.getContent_type() == null) {
            break label599;
          }
          str = localTracking.getContent_type();
          paramMap.put("content_type", str);
          if (localTracking.getContent_created_date() == null) {
            break label605;
          }
          str = a(localTracking.getContent_created_date());
          paramMap.put("content_created_date", str);
          if (localTracking.getContent_modified_date() == null) {
            break label611;
          }
          str = a(localTracking.getContent_modified_date());
          paramMap.put("content_modified_date", str);
          if (localTracking.getContent_creator() == null) {
            break label617;
          }
          str = localTracking.getContent_creator();
          paramMap.put("content_creator", str);
          if (localTracking.getContent_author() == null) {
            break label623;
          }
          str = localTracking.getContent_author();
          paramMap.put("content_author", str);
          if (localTracking.getContent_priority() == null) {
            break label629;
          }
          str = localTracking.getContent_priority();
          paramMap.put("content_priority", str);
          if (localTracking.getShort_title() == null) {
            break label635;
          }
          str = localTracking.getShort_title();
          paramMap.put("title", str);
          if (localTracking.getTitle_en() == null) {
            break label641;
          }
          str = localTracking.getTitle_en();
          paramMap.put("title_en", str);
          if (localTracking.getShort_title() == null) {
            break label647;
          }
          str = localTracking.getShort_title();
          paramMap.put("short_title", str);
          if (localTracking.getShort_title_en() == null) {
            break label653;
          }
          str = localTracking.getShort_title_en();
          paramMap.put("short_title_en", str);
          if (localTracking.getVideo_type() == null) {
            break label659;
          }
          str = localTracking.getVideo_type();
          paramMap.put("video_type", str);
          if (localTracking.getSource() == null) {
            break label665;
          }
          str = localTracking.getSource();
          paramMap.put("source", str);
          if (!TextUtils.isEmpty(paramMediaItem.getProviderId()))
          {
            paramMap.put("providerId", paramMediaItem.getProviderId());
            return;
          }
          if (TextUtils.isEmpty(paramMediaItem.getMcpProviderId())) {
            break;
          }
          paramMap.put("providerId", paramMediaItem.getMcpProviderId());
          return;
        }
      }
      catch (Exception paramMediaItem)
      {
        return;
      }
      String str = "";
      continue;
      label551:
      str = "";
      continue;
      label557:
      str = "";
      continue;
      label563:
      str = "";
      continue;
      label569:
      str = "";
      continue;
      label575:
      str = "";
      continue;
      label581:
      str = "";
      continue;
      label587:
      str = "";
      continue;
      label593:
      str = "";
      continue;
      label599:
      str = "";
      continue;
      label605:
      str = "";
      continue;
      label611:
      str = "";
      continue;
      label617:
      str = "";
      continue;
      label623:
      str = "";
      continue;
      label629:
      str = "";
      continue;
      label635:
      str = "";
      continue;
      label641:
      str = "";
      continue;
      label647:
      str = "";
      continue;
      label653:
      str = "";
      continue;
      label659:
      str = "";
      continue;
      label665:
      str = "";
    }
  }
  
  public static void a(UNTrackerKeys paramUNTrackerKeys)
  {
    a = paramUNTrackerKeys;
  }
  
  public static Object b(Object paramObject, String paramString)
  {
    Object localObject;
    if (paramObject == null) {
      localObject = null;
    }
    String[] arrayOfString;
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return localObject;
              localObject = paramObject;
            } while (paramString == null);
            localObject = paramObject;
          } while ("".equals(paramString));
          arrayOfString = paramString.split("\\.");
          localObject = paramObject;
        } while (!(paramObject instanceof HashMap));
        paramObject = ((HashMap)paramObject).get(arrayOfString[0]);
        localObject = paramObject;
      } while (paramObject == null);
      localObject = paramObject;
    } while (arrayOfString.length <= 1);
    return b(paramObject, paramString.substring(paramString.indexOf(".") + 1));
  }
  
  public static String b()
  {
    return "653x365";
  }
  
  public static String b(String paramString)
  {
    Object localObject = null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localObject = localMessageDigest;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        int i;
        localNoSuchAlgorithmException.printStackTrace();
      }
    }
    ((MessageDigest)localObject).update(paramString.getBytes());
    paramString = ((MessageDigest)localObject).digest();
    localObject = new StringBuffer();
    i = 0;
    while (i < paramString.length)
    {
      int j = paramString[i] & 0xFF;
      if (j < 16) {
        ((StringBuffer)localObject).append(0);
      }
      ((StringBuffer)localObject).append(Integer.toHexString(j));
      i += 1;
    }
    return ((StringBuffer)localObject).toString();
  }
  
  public static boolean b(Context paramContext)
  {
    Object localObject = (ActivityManager)paramContext.getSystemService("activity");
    if (localObject == null) {}
    do
    {
      return false;
      localObject = ((ActivityManager)localObject).getRunningAppProcesses();
    } while (localObject == null);
    paramContext = paramContext.getPackageName();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      if (localRunningAppProcessInfo.processName.equals(paramContext)) {
        if (localRunningAppProcessInfo.importance != 100) {
          break;
        }
      }
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static int c()
  {
    int i = 0;
    try
    {
      c.a();
      boolean bool = "true".equalsIgnoreCase(c.b("nl.uv.freewheel.polling", "concurrent"));
      if (bool) {
        i = 1;
      }
      return i;
    }
    catch (Exception localException) {}
    return 0;
  }
  
  public static String c(Context paramContext)
  {
    try
    {
      paramContext = new Date();
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a EEE EEEE HH", Locale.ENGLISH);
      localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
      paramContext = localSimpleDateFormat.format(paramContext);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static boolean c(String paramString)
  {
    return Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}").matcher(paramString).matches();
  }
  
  public static UNTrackerKeys d()
  {
    return a;
  }
  
  public static boolean d(String paramString)
  {
    return Pattern.compile("[A-Z0-9a-z._]{4,32}").matcher(paramString).matches();
  }
  
  public static boolean e(String paramString)
  {
    return Pattern.compile("(?=^.{6,24}$)(?=(?:.*?\\d){1})[0-9a-zA-Z]*$").matcher(paramString).matches();
  }
  
  public static boolean f(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    Iterator localIterator = UnivisionApplication.r().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.indexOf(paramString) > -1) {
        return true;
      }
    }
    return false;
  }
  
  public static String g(String paramString)
  {
    try
    {
      paramString = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", 2).matcher(paramString).replaceAll("");
      paramString = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>", 2).matcher(paramString).replaceAll("");
      paramString = Html.fromHtml(Pattern.compile("<[^>]+>", 2).matcher(paramString).replaceAll("").trim()).toString().replaceAll("#n", "\n");
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
}
