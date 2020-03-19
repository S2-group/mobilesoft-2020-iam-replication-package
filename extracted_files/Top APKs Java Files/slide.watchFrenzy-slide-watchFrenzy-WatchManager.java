package slide.watchFrenzy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageApi.SendMessageResult;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.wearable.intent.RemoteIntent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import slide.watchFrenzy.util.Base64;

public class WatchManager
{
  public static int MAX_BYTES = 100000;
  public static int SizePreview = 256;
  private static GoogleApiClient m_googleApiClient;
  private static int m_watchImportNo;
  
  public WatchManager() {}
  
  public static native void CPlusOnChangeWatch();
  
  public static void CPlusOnChangeWatchSafe()
  {
    if (SlideUtil.mGLView != null) {
      SlideUtil.mGLView.queueEvent(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  public static void CheckExtractAsset(String paramString)
  {
    if ((!SlideUtil.AssetExistsStorage(paramString)) && (SlideUtil.AssetExists(paramString))) {
      SlideUtil.ExtractAsset(paramString, Globals.MainFolder + "/" + paramString);
    }
  }
  
  public static void CheckImportAPK()
  {
    Object localObject;
    ApplicationInfo localApplicationInfo;
    try
    {
      if (!SlideUtil.HasInitialized()) {
        return;
      }
      localObject = SlideUtil.m_context.getPackageManager().getInstalledApplications(128);
      Hashtable localHashtable = new Hashtable();
      m_watchImportNo = 1;
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if ((localApplicationInfo.metaData != null) && (localApplicationInfo.metaData.containsKey("watchmaker.watch"))) {
          ExtractWatches(localApplicationInfo.processName, localHashtable);
        }
      }
      localObject = new File(Globals.WatchesFolder).listFiles();
    }
    catch (Exception localException)
    {
      Log.d("dd", "cp1 ex " + SlideUtil.Stack2String(localException));
      return;
    }
    if (localObject != null)
    {
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        localApplicationInfo = localObject[i];
        if ((localApplicationInfo.getName().startsWith("a_")) && (!localException.containsKey(localApplicationInfo.getName()))) {
          DeleteWatch(localApplicationInfo.getName().replace(".xml", ""));
        }
        i += 1;
      }
    }
  }
  
  public static native void CheckNeedWeather();
  
  public static void CheckNeedWeatherSafe()
  {
    if (SlideUtil.mGLView != null) {
      SlideUtil.mGLView.queueEvent(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  public static void CheckWatchPreview(String paramString)
  {
    if (SlideUtil.m_context == null) {}
    Object localObject;
    for (;;)
    {
      return;
      try
      {
        if (!SlideUtil.CheckFileExists(paramString)) {
          SlideUtil.CopyFile(paramString.replace("preview_small", "preview"), paramString);
        }
        localObject = new BitmapFactory.Options();
        ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
        BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject);
        float f1 = ((BitmapFactory.Options)localObject).outWidth;
        float f2 = ((BitmapFactory.Options)localObject).outHeight;
        if ((f1 != SizePreview) || (f2 != SizePreview))
        {
          ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
          for (((BitmapFactory.Options)localObject).inSampleSize = 1; (f1 / ((BitmapFactory.Options)localObject).inSampleSize > 640.0F) || (f2 / ((BitmapFactory.Options)localObject).inSampleSize > 640.0F); ((BitmapFactory.Options)localObject).inSampleSize *= 2) {}
          localObject = BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject);
        }
      }
      catch (Exception paramString)
      {
        Log.d("dd", "cp1 CheckWatchPreview " + SlideUtil.Stack2String(paramString));
        return;
      }
    }
    Bitmap localBitmap = Bitmap.createBitmap(SizePreview, SizePreview, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    localCanvas.setDrawFilter(new PaintFlagsDrawFilter(0, 2));
    int i = Math.max(((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
    localCanvas.drawBitmap((Bitmap)localObject, new Rect(0, 0, i, i), new RectF(0.0F, 0.0F, SizePreview, SizePreview), null);
    SlideUtil.SavePicture(localBitmap, paramString, Bitmap.CompressFormat.JPEG, 100);
    localBitmap.recycle();
    ((Bitmap)localObject).recycle();
  }
  
  public static void CopyOldMyPhoto()
  {
    SlideUtil.CopyFile(SlideUtil.GetPath("/BeautifulWatches/.photos/photo1.jpg"), Globals.ImagesFolder + "/.img1.jpg");
  }
  
  public static native void DeleteWatch(String paramString);
  
  public static void ExtractWatch(ZipFile paramZipFile, ZipEntry paramZipEntry, String paramString1, String paramString2)
    throws Exception
  {
    File localFile = new File(paramString1);
    paramZipFile = paramZipFile.getInputStream(paramZipEntry);
    try
    {
      SlideUtil.copy(paramZipFile, localFile);
      ImportWatch(paramString1, paramString2);
      paramZipFile.close();
      SlideUtil.DeleteFile(paramString1);
      return;
    }
    finally
    {
      paramZipFile.close();
    }
  }
  
  public static void ExtractWatches(String paramString, Hashtable<String, String> paramHashtable)
  {
    try
    {
      paramString = new ZipFile(SlideUtil.m_context.getPackageManager().getApplicationInfo(paramString, 0).sourceDir);
      Enumeration localEnumeration = paramString.entries();
      while (localEnumeration.hasMoreElements())
      {
        ZipEntry localZipEntry = (ZipEntry)localEnumeration.nextElement();
        String str = new File(localZipEntry.getName()).getName().toLowerCase();
        int i = str.indexOf(".watch");
        if (i != -1)
        {
          str = "a_" + str.substring(0, i);
          paramHashtable.put(str + ".xml", "");
          if (!new File(Globals.WatchesFolder + "/" + str + ".xml").exists())
          {
            ExtractWatch(paramString, localZipEntry, Globals.TempFolder + "/temp" + m_watchImportNo + ".watch", str);
            m_watchImportNo += 1;
          }
        }
      }
      return;
    }
    catch (Exception paramString)
    {
      Log.d("dd", "cp1 ex " + SlideUtil.Stack2String(paramString));
    }
  }
  
  public static String GetBGPath(String paramString)
  {
    int i = 1;
    while (new File(Globals.WallpaperBGFolder + "/bg" + i + paramString).exists()) {
      i += 1;
    }
    return Globals.WallpaperBGFolder + "/bg" + i + paramString;
  }
  
  public static String GetBestAssetPath(String paramString)
  {
    if (SlideUtil.AssetExists(paramString)) {
      return paramString;
    }
    if (SlideUtil.AssetExistsStorage(paramString)) {
      return Globals.MainFolder + "/" + paramString;
    }
    return "";
  }
  
  public static int GetImagePath(String paramString)
  {
    return GetImagePathExt2(paramString.substring(paramString.lastIndexOf(".")).toLowerCase());
  }
  
  public static String GetImagePathExt(String paramString)
  {
    return Globals.ImagesFolder + "/.img" + GetImagePathExt2(paramString) + paramString;
  }
  
  public static int GetImagePathExt2(String paramString)
  {
    int j = 1;
    i = 1;
    File[] arrayOfFile = new File(Globals.ImagesFolder).listFiles();
    k = j;
    if (arrayOfFile != null)
    {
      k = j;
      if (arrayOfFile.length >= 1)
      {
        j = 0;
        for (;;)
        {
          k = i;
          if (j >= arrayOfFile.length) {
            break;
          }
          try
          {
            String str = arrayOfFile[j].getName();
            k = i;
            if (str.startsWith(".img"))
            {
              k = i;
              if (str.endsWith(paramString)) {
                k = Math.max(i, Integer.parseInt(str.substring(4, str.length() - 4)) + 1);
              }
            }
          }
          catch (Exception localException)
          {
            for (;;)
            {
              k = i;
            }
          }
          j += 1;
          i = k;
        }
      }
    }
    SlideUtil.SetPrefBool("got_image_paths", false);
    return k;
  }
  
  public static String GetImagePaths()
  {
    int[] arrayOfInt = new int[4];
    File[] arrayOfFile = new File(Globals.ImagesFolder).listFiles();
    int i;
    if ((arrayOfFile != null) && (arrayOfFile.length >= 1))
    {
      Log.d("dd", "cp1 got " + arrayOfFile.length);
      i = 0;
    }
    for (;;)
    {
      if (i < arrayOfFile.length) {}
      try
      {
        String str = arrayOfFile[i].getName();
        if (!str.startsWith(".img")) {
          break label292;
        }
        if (str.endsWith(".jpg")) {
          arrayOfInt[0] = Math.max(arrayOfInt[0], Integer.parseInt(str.substring(4, str.length() - 4)));
        } else if (str.endsWith(".png")) {
          arrayOfInt[1] = Math.max(arrayOfInt[1], Integer.parseInt(str.substring(4, str.length() - 4)));
        } else if (str.endsWith(".pjpg")) {
          arrayOfInt[2] = Math.max(arrayOfInt[2], Integer.parseInt(str.substring(4, str.length() - 5)));
        } else if (str.endsWith(".ppng")) {
          arrayOfInt[3] = Math.max(arrayOfInt[3], Integer.parseInt(str.substring(4, str.length() - 5)));
        }
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      SlideUtil.SetPrefBool("got_image_paths", true);
      return arrayOfInt[0] + 1 + "`" + (arrayOfInt[1] + 1) + "`" + (arrayOfInt[2] + 1) + "`" + (arrayOfInt[3] + 1);
      label292:
      i += 1;
    }
  }
  
  public static String GetMyFonts(boolean paramBoolean)
  {
    Object localObject1;
    String str;
    label33:
    int i;
    label47:
    File localFile;
    if (paramBoolean)
    {
      localObject1 = Globals.FontsFolder;
      File[] arrayOfFile = new File((String)localObject1).listFiles();
      localObject1 = "";
      if (!paramBoolean) {
        break label134;
      }
      str = ".ttf";
      localObject2 = localObject1;
      if (arrayOfFile == null) {
        break label212;
      }
      int j = arrayOfFile.length;
      i = 0;
      localObject2 = localObject1;
      if (i >= j) {
        break label212;
      }
      localFile = arrayOfFile[i];
      localObject2 = localObject1;
      if (localFile.getName().toLowerCase().endsWith(str))
      {
        localObject2 = localObject1;
        if (paramBoolean) {
          break label164;
        }
        if (new File(localFile.getPath().replace(".fnt", ".png")).exists()) {
          break label142;
        }
      }
    }
    label134:
    label142:
    label164:
    for (Object localObject2 = localObject1;; localObject2 = (String)localObject2 + localFile.getName().substring(0, localFile.getName().length() - 4) + "|")
    {
      i += 1;
      localObject1 = localObject2;
      break label47;
      localObject1 = Globals.FontsBMFolder;
      break;
      str = ".fnt";
      break label33;
      localObject2 = (String)localObject1 + "bm:";
    }
    label212:
    return SlideUtil.TextRemoveChars((String)localObject2, 1);
  }
  
  public static String GetMyWatches()
  {
    String str = SlideUtil.GetPrefString("sort_order", "sort_newest");
    Object localObject2 = new File(Globals.WatchesFolder).listFiles();
    Object localObject1 = new ArrayList();
    if (localObject2 != null)
    {
      int j = localObject2.length;
      int i = 0;
      while (i < j)
      {
        File localFile = localObject2[i];
        if (localFile.getName().endsWith(".xml"))
        {
          MyWatch localMyWatch = new MyWatch();
          localMyWatch.Id = localFile.getName().substring(0, localFile.getName().indexOf(".xml"));
          localMyWatch.LastModified = Long.valueOf(localFile.lastModified());
          localMyWatch.Name = "";
          if (str.equals("sort_az")) {
            localMyWatch.Name = GetWatchName(localFile);
          }
          ((ArrayList)localObject1).add(localMyWatch);
        }
        i += 1;
      }
    }
    Collections.sort((List)localObject1, new Comparator()
    {
      public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        paramAnonymousObject1 = (MyWatch)paramAnonymousObject1;
        paramAnonymousObject2 = (MyWatch)paramAnonymousObject2;
        if (this.val$sortOrder.equals("sort_newest")) {
          return paramAnonymousObject2.LastModified.compareTo(paramAnonymousObject1.LastModified);
        }
        if (this.val$sortOrder.equals("sort_oldest")) {
          return paramAnonymousObject1.LastModified.compareTo(paramAnonymousObject2.LastModified);
        }
        return paramAnonymousObject1.Name.compareToIgnoreCase(paramAnonymousObject2.Name);
      }
    });
    str = "";
    localObject1 = ((ArrayList)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (MyWatch)((Iterator)localObject1).next();
      str = str + ((MyWatch)localObject2).Id + "|";
    }
    return SlideUtil.TextRemoveChars(str, 1);
  }
  
  public static String GetMyWatchesSearch()
  {
    File[] arrayOfFile = new File(Globals.WatchesFolder).listFiles();
    Object localObject1 = "";
    Object localObject2 = localObject1;
    if (arrayOfFile != null)
    {
      int j = arrayOfFile.length;
      int i = 0;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= j) {
          break;
        }
        Object localObject3 = arrayOfFile[i];
        localObject2 = localObject1;
        if (((File)localObject3).getName().endsWith(".xml"))
        {
          localObject2 = new MyWatch();
          ((MyWatch)localObject2).Id = ((File)localObject3).getName().substring(0, ((File)localObject3).getName().indexOf(".xml"));
          ((MyWatch)localObject2).Id = ((MyWatch)localObject2).Id.replace("`", "").replace("~", "");
          ((MyWatch)localObject2).LastModified = Long.valueOf(((File)localObject3).lastModified());
          localObject3 = GetWatchDetails((File)localObject3);
          localObject2 = (String)localObject1 + ((MyWatch)localObject2).Id + "`" + localObject3[0] + "`" + Long.toString(((MyWatch)localObject2).LastModified.longValue()) + "`" + localObject3[1] + "`" + localObject3[2] + "`" + localObject3[3] + "`~";
        }
        i += 1;
        localObject1 = localObject2;
      }
    }
    return SlideUtil.TextRemoveChars((String)localObject2, 1);
  }
  
  public static String GetNewWatchId()
  {
    int j = 1;
    i = 1;
    File[] arrayOfFile = new File(Globals.WatchesFolder).listFiles();
    k = j;
    if (arrayOfFile != null)
    {
      k = j;
      if (arrayOfFile.length >= 1)
      {
        j = 0;
        for (;;)
        {
          k = i;
          if (j >= arrayOfFile.length) {
            break;
          }
          try
          {
            String str = arrayOfFile[j].getName();
            k = i;
            if (str.startsWith("u"))
            {
              k = i;
              if (str.endsWith(".xml")) {
                k = Math.max(i, Integer.parseInt(str.substring(1, str.length() - 4)) + 1);
              }
            }
          }
          catch (Exception localException)
          {
            for (;;)
            {
              k = i;
            }
          }
          j += 1;
          i = k;
        }
      }
    }
    return "u" + k;
  }
  
  public static String GetRandomWatch()
  {
    File[] arrayOfFile = new File(Globals.WatchesFolder).listFiles();
    ArrayList localArrayList = new ArrayList();
    if (arrayOfFile != null)
    {
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        File localFile = arrayOfFile[i];
        if (localFile.getName().endsWith(".xml")) {
          localArrayList.add(localFile.getName().substring(0, localFile.getName().indexOf(".xml")));
        }
        i += 1;
      }
    }
    if (localArrayList.size() == 0) {
      return "w3";
    }
    return (String)localArrayList.get(new Random().nextInt(localArrayList.size()));
  }
  
  public static String GetSettings()
  {
    Object localObject2 = "";
    String[] arrayOfString = "device=moto360`gyro_strength_watch=100`pref_override_24=none`pref_extra_timezone_1=-8`pref_extra_timezone_2=0`pref_extra_timezone_3=9`pref_w_scale=c`pref_w_interval=180`pref_w_provider=yahoo`calendar_all_day=false`calendar_after_today=false`calendars_exclude=`pref_vibrate=`prop_cards_large=true`prop_cards_bg_opaque=true`prop_cards_bg_persist=false`prop_cards_show_unread_count=false`prop_cards_show_ambient=false`prop_cards_adapt_watch_size=unchanged`show_hotspots=false".split("`");
    int j = arrayOfString.length;
    int i = 0;
    if (i < j)
    {
      String str = arrayOfString[i];
      Object localObject1 = localObject2;
      if (str.length() >= 1)
      {
        localObject1 = str.split("=");
        str = localObject1[0];
        if (localObject1.length < 2) {
          break label157;
        }
        localObject1 = localObject1[1];
        label66:
        localObject2 = (String)localObject2 + str + "=";
        if ((!((String)localObject1).equals("true")) && (!((String)localObject1).equals("false"))) {
          break label163;
        }
      }
      label157:
      label163:
      for (localObject1 = (String)localObject2 + SlideUtil.GetPrefBool(str, ((String)localObject1).equals("true")) + "`";; localObject1 = (String)localObject2 + SlideUtil.GetPrefString(str, (String)localObject1) + "`")
      {
        i += 1;
        localObject2 = localObject1;
        break;
        localObject1 = "";
        break label66;
      }
    }
    return SlideUtil.TextRemoveChars((String)localObject2, 1);
  }
  
  /* Error */
  public static String[] GetWatchDetails(File paramFile)
  {
    // Byte code:
    //   0: iconst_4
    //   1: anewarray 197	java/lang/String
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 4
    //   9: aconst_null
    //   10: astore 5
    //   12: new 599	java/io/BufferedReader
    //   15: dup
    //   16: new 601	java/io/FileReader
    //   19: dup
    //   20: aload_0
    //   21: invokespecial 604	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   24: invokespecial 607	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   27: astore_0
    //   28: aload_0
    //   29: invokevirtual 610	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   32: astore 4
    //   34: iconst_4
    //   35: anewarray 197	java/lang/String
    //   38: astore 5
    //   40: aload 5
    //   42: iconst_0
    //   43: ldc_w 612
    //   46: aastore
    //   47: aload 5
    //   49: iconst_1
    //   50: ldc_w 614
    //   53: aastore
    //   54: aload 5
    //   56: iconst_2
    //   57: ldc_w 616
    //   60: aastore
    //   61: aload 5
    //   63: iconst_3
    //   64: ldc_w 618
    //   67: aastore
    //   68: iconst_0
    //   69: istore_1
    //   70: iload_1
    //   71: aload 5
    //   73: arraylength
    //   74: if_icmpge +107 -> 181
    //   77: new 81	java/lang/StringBuilder
    //   80: dup
    //   81: invokespecial 82	java/lang/StringBuilder:<init>	()V
    //   84: ldc_w 620
    //   87: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: aload 5
    //   92: iload_1
    //   93: aaload
    //   94: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: ldc_w 622
    //   100: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: astore 7
    //   108: aload 4
    //   110: aload 7
    //   112: invokevirtual 387	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   115: istore_2
    //   116: iload_2
    //   117: iconst_m1
    //   118: if_icmpeq +56 -> 174
    //   121: iload_2
    //   122: aload 7
    //   124: invokevirtual 440	java/lang/String:length	()I
    //   127: iadd
    //   128: istore_2
    //   129: aload 4
    //   131: ldc_w 624
    //   134: iload_2
    //   135: invokevirtual 627	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   138: istore_3
    //   139: iload_3
    //   140: iconst_m1
    //   141: if_icmpeq +33 -> 174
    //   144: aload 6
    //   146: iload_1
    //   147: aload 4
    //   149: iload_2
    //   150: iload_3
    //   151: invokevirtual 391	java/lang/String:substring	(II)Ljava/lang/String;
    //   154: invokestatic 630	slide/watchFrenzy/SlideUtil:unescapeXML	(Ljava/lang/String;)Ljava/lang/String;
    //   157: ldc_w 464
    //   160: ldc -49
    //   162: invokevirtual 211	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   165: ldc_w 545
    //   168: ldc -49
    //   170: invokevirtual 211	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   173: aastore
    //   174: iload_1
    //   175: iconst_1
    //   176: iadd
    //   177: istore_1
    //   178: goto -108 -> 70
    //   181: aload_0
    //   182: ifnull +67 -> 249
    //   185: aload_0
    //   186: invokevirtual 631	java/io/BufferedReader:close	()V
    //   189: aload 6
    //   191: areturn
    //   192: astore_0
    //   193: aload 6
    //   195: areturn
    //   196: astore_0
    //   197: aload 5
    //   199: astore_0
    //   200: aload_0
    //   201: ifnull -12 -> 189
    //   204: aload_0
    //   205: invokevirtual 631	java/io/BufferedReader:close	()V
    //   208: aload 6
    //   210: areturn
    //   211: astore_0
    //   212: aload 6
    //   214: areturn
    //   215: astore_0
    //   216: aload 4
    //   218: ifnull +8 -> 226
    //   221: aload 4
    //   223: invokevirtual 631	java/io/BufferedReader:close	()V
    //   226: aload_0
    //   227: athrow
    //   228: astore 4
    //   230: goto -4 -> 226
    //   233: astore 5
    //   235: aload_0
    //   236: astore 4
    //   238: aload 5
    //   240: astore_0
    //   241: goto -25 -> 216
    //   244: astore 4
    //   246: goto -46 -> 200
    //   249: aload 6
    //   251: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	252	0	paramFile	File
    //   69	109	1	i	int
    //   115	35	2	j	int
    //   138	13	3	k	int
    //   7	215	4	str1	String
    //   228	1	4	localException1	Exception
    //   236	1	4	localFile	File
    //   244	1	4	localException2	Exception
    //   10	188	5	arrayOfString1	String[]
    //   233	6	5	localObject	Object
    //   4	246	6	arrayOfString2	String[]
    //   106	17	7	str2	String
    // Exception table:
    //   from	to	target	type
    //   185	189	192	java/lang/Exception
    //   12	28	196	java/lang/Exception
    //   204	208	211	java/lang/Exception
    //   12	28	215	finally
    //   221	226	228	java/lang/Exception
    //   28	40	233	finally
    //   70	116	233	finally
    //   121	139	233	finally
    //   144	174	233	finally
    //   28	40	244	java/lang/Exception
    //   70	116	244	java/lang/Exception
    //   121	139	244	java/lang/Exception
    //   144	174	244	java/lang/Exception
  }
  
  /* Error */
  public static String GetWatchName(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: new 599	java/io/BufferedReader
    //   8: dup
    //   9: new 601	java/io/FileReader
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 604	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   17: invokespecial 607	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   20: astore_0
    //   21: aload_0
    //   22: invokevirtual 610	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   25: astore_3
    //   26: aload_3
    //   27: ldc_w 633
    //   30: invokevirtual 387	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   33: istore_1
    //   34: iload_1
    //   35: iconst_m1
    //   36: if_icmpeq +42 -> 78
    //   39: iload_1
    //   40: bipush 7
    //   42: iadd
    //   43: istore_1
    //   44: aload_3
    //   45: ldc_w 624
    //   48: iload_1
    //   49: invokevirtual 627	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   52: istore_2
    //   53: iload_2
    //   54: iconst_m1
    //   55: if_icmpeq +23 -> 78
    //   58: aload_3
    //   59: iload_1
    //   60: iload_2
    //   61: invokevirtual 391	java/lang/String:substring	(II)Ljava/lang/String;
    //   64: invokestatic 630	slide/watchFrenzy/SlideUtil:unescapeXML	(Ljava/lang/String;)Ljava/lang/String;
    //   67: astore_3
    //   68: aload_0
    //   69: ifnull +7 -> 76
    //   72: aload_0
    //   73: invokevirtual 631	java/io/BufferedReader:close	()V
    //   76: aload_3
    //   77: areturn
    //   78: aload_0
    //   79: ifnull +67 -> 146
    //   82: aload_0
    //   83: invokevirtual 631	java/io/BufferedReader:close	()V
    //   86: ldc_w 635
    //   89: areturn
    //   90: astore_0
    //   91: goto -5 -> 86
    //   94: astore_0
    //   95: aload 4
    //   97: astore_0
    //   98: aload_0
    //   99: ifnull -13 -> 86
    //   102: aload_0
    //   103: invokevirtual 631	java/io/BufferedReader:close	()V
    //   106: goto -20 -> 86
    //   109: astore_0
    //   110: goto -24 -> 86
    //   113: astore_0
    //   114: aload_3
    //   115: ifnull +7 -> 122
    //   118: aload_3
    //   119: invokevirtual 631	java/io/BufferedReader:close	()V
    //   122: aload_0
    //   123: athrow
    //   124: astore_0
    //   125: goto -49 -> 76
    //   128: astore_3
    //   129: goto -7 -> 122
    //   132: astore 4
    //   134: aload_0
    //   135: astore_3
    //   136: aload 4
    //   138: astore_0
    //   139: goto -25 -> 114
    //   142: astore_3
    //   143: goto -45 -> 98
    //   146: goto -60 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	149	0	paramFile	File
    //   33	27	1	i	int
    //   52	9	2	j	int
    //   1	118	3	str	String
    //   128	1	3	localException1	Exception
    //   135	1	3	localFile	File
    //   142	1	3	localException2	Exception
    //   3	93	4	localObject1	Object
    //   132	5	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   82	86	90	java/lang/Exception
    //   5	21	94	java/lang/Exception
    //   102	106	109	java/lang/Exception
    //   5	21	113	finally
    //   72	76	124	java/lang/Exception
    //   118	122	128	java/lang/Exception
    //   21	34	132	finally
    //   44	53	132	finally
    //   58	68	132	finally
    //   21	34	142	java/lang/Exception
    //   44	53	142	java/lang/Exception
    //   58	68	142	java/lang/Exception
  }
  
  public static native void ImportWatch(String paramString1, String paramString2);
  
  public static void ImportWatchSafe(String paramString1, final String paramString2)
  {
    if (SlideUtil.mGLView != null) {
      SlideUtil.mGLView.queueEvent(new Runnable()
      {
        public void run()
        {
          WatchManager.ImportWatch(this.val$filePath_, paramString2);
        }
      });
    }
  }
  
  public static void InitWearOS()
  {
    if (Globals.WearOS.equals("aw")) {
      MyGoogleApiClient();
    }
    do
    {
      return;
      if (Globals.WearOS.equals("tizen"))
      {
        SapManager.OnCreate();
        return;
      }
    } while (!Globals.WearOS.equals("tw"));
    TicwearManager.OnCreate();
  }
  
  public static void InstallCompanionAppAW2()
  {
    ((Activity)SlideUtil.m_context).runOnUiThread(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.parse("market://details?id=slide.watchFrenzy"));
        RemoteIntent.startRemoteActivity(SlideUtil.m_context, localIntent, new ResultReceiver(new Handler())
        {
          protected void onReceiveResult(int paramAnonymous2Int, Bundle paramAnonymous2Bundle)
          {
            Log.d("dd", "cp1 onReceiveResult: " + paramAnonymous2Int);
            Toast.makeText(SlideUtil.m_context, "Look at your watch to continue...", 1).show();
          }
        });
      }
    });
  }
  
  public static GoogleApiClient MyGoogleApiClient()
  {
    if ((Globals.IsLWP) || (Globals.IsLWPS)) {
      return m_googleApiClient;
    }
    if (m_googleApiClient == null)
    {
      GoogleApiClient.Builder localBuilder = new GoogleApiClient.Builder(SlideUtil.m_context).addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks()
      {
        public void onConnected(Bundle paramAnonymousBundle)
        {
          Log.d("dd", "cp1 MyGoogleApiClient onConnected");
          Globals.IsGoogleAPIClientConnected = true;
        }
        
        public void onConnectionSuspended(int paramAnonymousInt)
        {
          Log.d("dd", "cp1 MyGoogleApiClient onConnectionSuspended");
        }
      }).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener()
      {
        public void onConnectionFailed(ConnectionResult paramAnonymousConnectionResult)
        {
          Log.d("dd", "cp1 MyGoogleApiClient onConnectionFailed");
          if (paramAnonymousConnectionResult != null) {
            Log.d("dd", "cp1 " + paramAnonymousConnectionResult);
          }
        }
      });
      localBuilder.addApi(Wearable.API);
      m_googleApiClient = localBuilder.build();
    }
    if ((!m_googleApiClient.isConnecting()) && (!m_googleApiClient.isConnected()))
    {
      m_googleApiClient.connect();
      Log.d("dd", "cp1 MyGoogleApiClient connect");
    }
    return m_googleApiClient;
  }
  
  public static native void NewTexture(String paramString, int[] paramArrayOfInt, int paramInt1, int paramInt2);
  
  public static void OnReceived(String paramString1, Context paramContext, String paramString2)
  {
    if (paramString1.startsWith("request_weather"))
    {
      SlideUtil.QuickInitIfMissing(paramContext);
      RequestWeather(paramString1);
    }
    int i;
    int j;
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
              return;
              if (paramString1.equals("request_calendar"))
              {
                SlideUtil.QuickInitIfMissing(paramContext);
                CalendarManager.UpdateSendCalData(paramContext);
                return;
              }
              if (paramString1.equals("request_phone"))
              {
                SlideUtil.QuickInitIfMissing(paramContext);
                PhoneManager.SendPhoneData(paramContext);
                return;
              }
              if (paramString1.equals("request_location"))
              {
                SlideUtil.QuickInitIfMissing(paramContext);
                MyLocationManager.RequestLocation();
                return;
              }
              if (paramString1.equals("request_steps"))
              {
                SlideUtil.QuickInitIfMissing(paramContext);
                paramString1 = SlideUtil.GetPrefString("pref_steps_source", "phone_acc");
                if ((Globals.WearOS.equals("tizen")) && (paramString1.equals("shealth")))
                {
                  SHealthManager.GetStepCount(false);
                  return;
                }
                SensorsManager.RequestStepsSingle();
                return;
              }
              if (paramString1.startsWith("tap_action:"))
              {
                SlideUtil.QuickInitIfMissing(paramContext);
                TapActionMobile(paramString1.substring(11));
                return;
              }
              if (paramString1.startsWith("response_watch>"))
              {
                SlideUtil.QuickInitIfMissing(paramContext);
                ResponseWatchSafe(paramString1.replace("response_watch>", ""));
                return;
              }
              if (paramString1.startsWith("camera_"))
              {
                SlideUtil.QuickInitIfMissing(paramContext);
                paramString1 = paramString1.substring(7);
                if (paramString1.equals("start"))
                {
                  CameraManager.SetNeedsCameraWear(true);
                  return;
                }
                if (paramString1.equals("stop"))
                {
                  CameraManager.SetNeedsCameraWear(false);
                  return;
                }
                CameraManager.SendRequest(paramString1);
                return;
              }
              if (!paramString1.startsWith("download_file>")) {
                break;
              }
              SlideUtil.QuickInitIfMissing(paramContext);
              paramContext = paramString1.split(">");
            } while (paramContext.length < 3);
            paramString1 = paramContext[1];
            paramContext = paramContext[2];
            if (SlideUtil.CheckFileExists(Globals.MainFolder + "/" + paramContext))
            {
              SendData("download", paramContext, "");
              return;
            }
            SlideUtil.DownloadFile(paramString1, paramContext, true);
            return;
            if (!paramString1.startsWith("chunk~")) {
              break;
            }
            paramString1 = paramString1.split("~");
          } while (paramString1.length < 5);
          SendDataByMessage(paramString1[1], paramString1[2], paramString1[3], Integer.parseInt(paramString1[4]), paramString2);
          return;
        } while (!paramString1.startsWith("transaction:"));
        paramString1 = paramString1.split(":");
      } while (paramString1.length < 5);
      Integer.parseInt(paramString1[2]);
      ResponseWatchSafe("set_watch:" + paramString1[1] + ":mobile");
      i = Integer.parseInt(paramString1[2]);
      j = Integer.parseInt(paramString1[3]);
      SetMyProgressSafe(i / j, true);
    } while (i >= j);
    paramString1 = paramString1[4];
    CheckExtractAsset(paramString1);
    Log.d("dd", "cp1 transaction send " + paramString1);
    SendData("asset", paramString1, "", paramString2);
  }
  
  public static native void ParseWeatherC(String paramString);
  
  public static native void ParseWeatherFD(String paramString);
  
  public static native void RefreshSettingsQuick();
  
  public static void RequestWeather(String paramString)
  {
    SlideUtil.SetPrefBool("refresh_weather_now", true);
    if (SlideUtil.GetPrefString("weather_manual_location", "").length() == 0)
    {
      MyLocationManager.RequestLocation();
      new Thread()
      {
        public void run()
        {
          SlideUtil.Sleep(15000);
          WatchManager.WeatherRequestFromWatchSafe();
        }
      }.start();
      return;
    }
    WeatherRequestFromWatchSafe();
  }
  
  public static native void ResponseWatch(String paramString);
  
  public static void ResponseWatchSafe(String paramString)
  {
    if (SlideUtil.mGLView != null)
    {
      SlideUtil.mGLView.queueEvent(new Runnable()
      {
        public void run()
        {
          try
          {
            WatchManager.ResponseWatch(this.val$response_);
            return;
          }
          catch (Exception localException) {}
        }
      });
      return;
    }
    try
    {
      ResponseWatch(paramString);
      return;
    }
    catch (Exception paramString) {}catch (UnsatisfiedLinkError paramString) {}
  }
  
  public static void SendData(String paramString1, String paramString2, String paramString3)
  {
    SendData(paramString1, paramString2, paramString3, null);
  }
  
  public static void SendData(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if ((Globals.IsLWP) || (Globals.IsLWPS)) {}
    while (SlideUtil.m_context == null) {
      return;
    }
    if ((paramString2 != null) && (paramString2.length() >= 1) && (paramString2.startsWith("watches/w"))) {
      CheckExtractAsset(paramString2);
    }
    if (Globals.IsGooglePlay) {}
    for (String str = "data"; (SlideUtil.GetPrefString("pref_sync", str).equals("message")) || (Globals.WearOS.equals("tizen")); str = "message")
    {
      SendDataByMessage(paramString1, paramString2, paramString3, -1, paramString4);
      return;
    }
    SendDataByData(paramString1, paramString2, paramString3);
  }
  
  public static void SendDataByData(String paramString1, String paramString2, String paramString3)
  {
    if (Globals.WearOS.equals("tw")) {
      TicwearManager.SendDataByData(paramString1, paramString2, paramString3);
    }
    for (;;)
    {
      return;
      if (!MyGoogleApiClient().isConnected()) {
        continue;
      }
      PutDataMapRequest localPutDataMapRequest = PutDataMapRequest.create("/asset");
      DataMap localDataMap = localPutDataMapRequest.getDataMap();
      localDataMap.putString("assetName", paramString1);
      localDataMap.putString("filePath", paramString2);
      localDataMap.putString("extraText", paramString3);
      if (paramString1.startsWith("watch:"))
      {
        localDataMap.putString("settings", GetSettings());
        localDataMap.putString("weather_c", SlideUtil.GetPrefString("weather_c", ""));
        localDataMap.putString("weather_fd", SlideUtil.GetPrefString("weather_fd", ""));
        localDataMap.putString("cal_data", SlideUtil.GetPrefString("cal_data", ""));
        localDataMap.putString("phone_data", PhoneManager.GetPhoneData(SlideUtil.m_context));
      }
      localDataMap.putLong("time", new Date().getTime());
      if ((paramString2 != null) && (paramString2.length() >= 1))
      {
        paramString3 = new File(Globals.MainFolder + "/" + paramString2);
        paramString2 = new byte[(int)paramString3.length()];
      }
      try
      {
        paramString3 = new BufferedInputStream(new FileInputStream(paramString3));
        paramString3.read(paramString2, 0, paramString2.length);
        paramString3.close();
        localDataMap.putAsset("asset", Asset.createFromBytes(paramString2));
        Wearable.DataApi.putDataItem(MyGoogleApiClient(), localPutDataMapRequest.asPutDataRequest());
        localPutDataMapRequest.setUrgent();
        if (!paramString1.startsWith("watch")) {
          continue;
        }
        Wearable.NodeApi.getConnectedNodes(MyGoogleApiClient()).setResultCallback(new ResultCallback()
        {
          public void onResult(NodeApi.GetConnectedNodesResult paramAnonymousGetConnectedNodesResult)
          {
            WatchManager.ResponseWatchSafe("connected_nodes:" + paramAnonymousGetConnectedNodesResult.getNodes().size());
          }
        });
        return;
      }
      catch (FileNotFoundException paramString3)
      {
        for (;;)
        {
          Log.d("dd", "cp1 error1 " + paramString3);
        }
      }
      catch (IOException paramString3)
      {
        for (;;)
        {
          Log.d("dd", "cp1 error2 " + paramString3);
        }
      }
    }
  }
  
  public static void SendDataByMessage(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    paramString1 = "Data~" + paramString1 + "~" + paramString2 + "~" + paramString3 + "~";
    paramString3 = new File(Globals.MainFolder + "/" + paramString2);
    paramString2 = new byte[(int)paramString3.length()];
    for (;;)
    {
      try
      {
        paramString3 = new BufferedInputStream(new FileInputStream(paramString3));
        paramString3.read(paramString2, 0, paramString2.length);
        paramString3.close();
        paramString2 = Base64.encode(paramString2);
        if (paramInt == -1)
        {
          if (paramString2.length() > MAX_BYTES) {
            paramInt = 0;
          }
          if (paramInt == -1)
          {
            paramString1 = paramString1 + paramInt + "~";
            paramString1 = paramString1 + paramString2;
            SendMessage(paramString1, paramString4);
            return;
          }
          int j = paramInt * MAX_BYTES;
          int i = j + MAX_BYTES;
          if (i >= paramString2.length())
          {
            paramString1 = paramString1 + "-2~";
            paramInt = paramString2.length();
            paramString1 = paramString1 + paramString2.substring(j, paramInt);
            continue;
          }
          paramString1 = paramString1 + paramInt + "~";
          paramInt = i;
          continue;
        }
        paramInt += 1;
      }
      catch (FileNotFoundException paramString1)
      {
        paramString1.printStackTrace();
        return;
      }
      catch (IOException paramString1)
      {
        paramString1.printStackTrace();
        return;
      }
    }
  }
  
  public static void SendMessage(String paramString)
  {
    SendMessage(paramString, null);
  }
  
  public static void SendMessage(final String paramString1, String paramString2)
  {
    if (Globals.WearOS.equals("tizen")) {
      SapManager.SendMessage(paramString1);
    }
    do
    {
      return;
      if (Globals.WearOS.equals("tw"))
      {
        TicwearManager.SendMessage(paramString1);
        return;
      }
    } while ((Globals.IsLWP) || (Globals.IsLWPS) || (!MyGoogleApiClient().isConnected()));
    Wearable.NodeApi.getConnectedNodes(MyGoogleApiClient()).setResultCallback(new ResultCallback()
    {
      public void onResult(NodeApi.GetConnectedNodesResult paramAnonymousGetConnectedNodesResult)
      {
        int i = 0;
        if (i < paramAnonymousGetConnectedNodesResult.getNodes().size())
        {
          Node localNode = (Node)paramAnonymousGetConnectedNodesResult.getNodes().get(i);
          if ((this.val$nodeId_ != null) && (!this.val$nodeId_.equals(localNode.getId()))) {}
          for (;;)
          {
            i += 1;
            break;
            Wearable.MessageApi.sendMessage(WatchManager.MyGoogleApiClient(), localNode.getId(), paramString1, null).setResultCallback(new ResultCallback()
            {
              public void onResult(MessageApi.SendMessageResult paramAnonymous2SendMessageResult)
              {
                paramAnonymous2SendMessageResult.getStatus();
              }
            });
          }
        }
      }
    });
  }
  
  public static native void SetMyProgress(float paramFloat, boolean paramBoolean);
  
  public static void SetMyProgressSafe(float paramFloat, final boolean paramBoolean)
  {
    if (SlideUtil.mGLView != null) {
      SlideUtil.mGLView.queueEvent(new Runnable()
      {
        public void run()
        {
          WatchManager.SetMyProgress(this.val$progress_, paramBoolean);
        }
      });
    }
  }
  
  public static void SetWearOS(String paramString)
  {
    Globals.WearOS = paramString;
    SlideUtil.SetPrefString("WearOS", Globals.WearOS);
    Log.d("dd", "cp1 WearOS = " + Globals.WearOS);
    InitWearOS();
  }
  
  public static native void TagsChanged(String paramString);
  
  public static void TagsChangedSafe(String paramString)
  {
    if (SlideUtil.mGLView != null) {
      SlideUtil.mGLView.queueEvent(new Runnable()
      {
        public void run()
        {
          WatchManager.TagsChanged(this.val$tagsChanged_);
        }
      });
    }
  }
  
  public static void TapActionMobile(String paramString)
  {
    if (paramString.equals("m_update_weather")) {
      RequestWeather("request_weather_now");
    }
    do
    {
      return;
      if (paramString.startsWith("m_app:"))
      {
        paramString = paramString.substring(6).split("`");
        if (paramString.length >= 3)
        {
          SlideUtil.LaunchActivity(paramString[0], paramString[1], paramString[2]);
          return;
        }
        SlideUtil.LaunchActivity(paramString[0], paramString[1]);
        return;
      }
      if (paramString.startsWith("m_sc:"))
      {
        paramString = paramString.substring(5).split("`");
        SlideUtil.LaunchShortcut(paramString[0], paramString[1], paramString[2]);
        return;
      }
      if (paramString.startsWith("m_task:"))
      {
        TaskerManager.RunTask(paramString.substring(7));
        return;
      }
      if (paramString.equals("m_email"))
      {
        SlideUtil.LaunchActivity("Email", "com.google.android.gm,com.android.email");
        return;
      }
      if (paramString.equals("m_calendar"))
      {
        SlideUtil.LaunchActivity("Calendar", "com.google.android.calendar,com.android.calendar");
        return;
      }
      if (paramString.equals("m_android_wear"))
      {
        SlideUtil.LaunchActivity("Android Wear", "com.google.android.wearable.app");
        return;
      }
      if (paramString.startsWith("m_calendar_"))
      {
        SlideUtil.LaunchCalendarEvent(Integer.parseInt(paramString.replace("m_calendar_", "")));
        return;
      }
    } while (!paramString.equals("voice_search"));
    SlideUtil.LaunchActivity2("Voice Search", "android.intent.action.VOICE_ASSIST");
  }
  
  public static void TapActionMobileLooper(String paramString)
  {
    new Handler(Looper.getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        WatchManager.TapActionMobile(this.val$action_);
      }
    });
  }
  
  public static native int TouchEventStatic(int paramInt, float paramFloat1, float paramFloat2, boolean paramBoolean);
  
  public static native void WeatherRequestFromWatch();
  
  public static void WeatherRequestFromWatchSafe()
  {
    if (SlideUtil.mGLView != null)
    {
      SlideUtil.mGLView.queueEvent(new Runnable()
      {
        public void run() {}
      });
      return;
    }
    WeatherRequestFromWatch();
  }
}
