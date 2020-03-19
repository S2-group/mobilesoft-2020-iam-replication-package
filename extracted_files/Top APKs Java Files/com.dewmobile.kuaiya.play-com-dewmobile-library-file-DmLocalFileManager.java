package com.dewmobile.library.file;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import com.dewmobile.library.j.m;
import com.dewmobile.library.j.q;
import com.dewmobile.library.logging.DmLog;
import com.dewmobile.library.m.o;
import com.dewmobile.library.m.r;
import com.dewmobile.library.m.t;
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DmLocalFileManager
{
  public static List<String> a;
  public static String[] b = { "video", "apk", "doc", "ebook", "zip", "omni_video", "zapya_ting" };
  public static List<FileItem> c = new ArrayList();
  public static List<FileItem> d = new ArrayList();
  public static HashMap<String, String> e = new HashMap();
  private static final String f = DmLocalFileManager.class.getSimpleName();
  private static String g = "scanresult3.cache";
  private static ArrayList<String> h;
  
  public DmLocalFileManager() {}
  
  public static LocalFileResult a(Context paramContext, DmFileCategory paramDmFileCategory)
  {
    if (paramDmFileCategory == null) {}
    do
    {
      return null;
      if (paramDmFileCategory.f()) {
        return f(paramContext, paramDmFileCategory);
      }
      if (paramDmFileCategory.d()) {
        return d(paramContext, paramDmFileCategory);
      }
      if (paramDmFileCategory.n())
      {
        if (com.dewmobile.library.g.b.a().F() == 0) {
          return i(paramContext, paramDmFileCategory);
        }
        return h(paramContext, paramDmFileCategory);
      }
      if (paramDmFileCategory.m()) {
        return i(paramContext, paramDmFileCategory);
      }
      if (paramDmFileCategory.g()) {
        return a(paramDmFileCategory, paramContext);
      }
      if (paramDmFileCategory.e()) {
        return b(paramContext, paramDmFileCategory);
      }
      if (paramDmFileCategory.h()) {
        return b(paramContext);
      }
    } while (!paramDmFileCategory.b());
    return e(paramContext, paramDmFileCategory);
  }
  
  public static LocalFileResult a(DmFileCategory paramDmFileCategory, Context paramContext)
  {
    String str = paramDmFileCategory.d;
    a();
    LocalFileResult localLocalFileResult = new LocalFileResult();
    ArrayList localArrayList;
    if (!"...".equals(str))
    {
      localArrayList = new ArrayList();
      Object localObject = com.dewmobile.transfer.api.a.a(str);
      int i;
      if ((localObject != null) && (((File)localObject).exists()))
      {
        localObject = ((File)localObject).listFiles();
        if (localObject != null)
        {
          b();
          boolean bool = com.dewmobile.library.g.b.a().s();
          int j = localObject.length;
          i = 0;
          while (i < j)
          {
            File localFile = localObject[i];
            if (((bool) || (!localFile.isHidden())) && (!a(localFile))) {
              localArrayList.add(localFile);
            }
            i += 1;
          }
        }
        if ((com.dewmobile.library.f.a.a().e().equals(str)) && (paramDmFileCategory.i()))
        {
          i = 1;
          if (i == 0) {
            break label187;
          }
          a(16, localArrayList);
        }
      }
      for (;;)
      {
        if (!localArrayList.isEmpty()) {
          break label277;
        }
        return null;
        i = 0;
        break;
        label187:
        a(paramDmFileCategory, localArrayList);
        continue;
        if (!str.equals(com.dewmobile.library.f.a.a().e()))
        {
          do
          {
            str = str.substring(0, str.lastIndexOf(File.separatorChar));
            DmLog.d(f, "search parent folder: " + str);
            localObject = com.dewmobile.transfer.api.a.a(str);
          } while (localObject == null);
          localArrayList.add(0, localObject);
        }
      }
    }
    label277:
    for (localLocalFileResult.a = a(localArrayList, paramDmFileCategory, false, paramContext);; localLocalFileResult.a = a(a(), paramDmFileCategory, paramContext))
    {
      localLocalFileResult.b = null;
      return localLocalFileResult;
    }
  }
  
  private static FileCategorySorter a(Context paramContext, ArrayList<FileItem> paramArrayList, DmFileCategory paramDmFileCategory, Cursor paramCursor)
  {
    return a(paramContext, paramArrayList, paramDmFileCategory, paramCursor, 1, false);
  }
  
  private static FileCategorySorter a(Context paramContext, ArrayList<FileItem> paramArrayList, DmFileCategory paramDmFileCategory, Cursor paramCursor, int paramInt, boolean paramBoolean)
  {
    int i10 = paramCursor.getColumnIndex("_id");
    int i11 = paramCursor.getColumnIndex("_display_name");
    int i12 = paramCursor.getColumnIndex("_data");
    int i5 = -1;
    int i6 = -1;
    int i7 = -1;
    int i8 = -1;
    int i9 = -1;
    int i4 = -1;
    int i2 = -1;
    int n = -1;
    int k = -1;
    int i;
    int j;
    int m;
    int i1;
    int i3;
    FileCategorySorter localFileCategorySorter;
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    boolean bool;
    List localList;
    label223:
    String str4;
    File localFile;
    FileItem localFileItem;
    String str2;
    String str1;
    long l2;
    if (paramDmFileCategory.d())
    {
      i = paramCursor.getColumnIndex("album");
      j = paramCursor.getColumnIndex("album_id");
      m = paramCursor.getColumnIndex("artist");
      i1 = paramCursor.getColumnIndex("duration");
      i3 = paramCursor.getColumnIndex("title");
      if ((Build.VERSION.SDK_INT < 16) || ((!paramDmFileCategory.e()) && (!paramDmFileCategory.c()))) {
        break label1091;
      }
      i6 = paramCursor.getColumnIndex("width");
      i5 = paramCursor.getColumnIndex("height");
      localFileCategorySorter = null;
      if (!paramBoolean) {
        localFileCategorySorter = j(paramContext, paramDmFileCategory);
      }
      localArrayList1 = new ArrayList();
      localArrayList2 = new ArrayList();
      bool = com.dewmobile.library.g.b.a().r();
      localList = null;
      if (!paramBoolean) {
        localList = j.a(paramContext).a();
      }
      if (paramCursor.moveToNext())
      {
        i7 = paramCursor.getInt(i10);
        str4 = paramCursor.getString(i12);
      }
    }
    else
    {
      label429:
      label498:
      label589:
      label708:
      do
      {
        for (;;)
        {
          try
          {
            localFile = com.dewmobile.transfer.api.a.a(str4);
            long l1 = localFile.length();
            if (0L == l1) {
              break label223;
            }
            String str5 = paramCursor.getString(i11);
            localFileItem = new FileItem(paramDmFileCategory);
            try
            {
              if (!paramDmFileCategory.d()) {
                continue;
              }
              String str3 = paramCursor.getString(i);
              str2 = paramCursor.getString(m);
              str1 = paramCursor.getString(i3);
              l2 = paramCursor.getLong(j);
              long l3 = paramCursor.getLong(i1);
              if (str3 == null) {
                continue;
              }
              paramContext = str3;
              if (i(str3))
              {
                paramContext = str3;
                if (!h(str3)) {
                  paramContext = j(str3);
                }
              }
              localFileItem.n = paramContext;
              localFileItem.p = l2;
              if (str2 == null) {
                break label1100;
              }
              paramContext = str2;
              if (i(str2))
              {
                paramContext = str2;
                if (!h(str2)) {
                  paramContext = j(str2);
                }
              }
              localFileItem.o = paramContext;
              localFileItem.q = l3;
              paramContext = str1;
              if (i(str1))
              {
                paramContext = str1;
                if (!h(str1)) {
                  paramContext = j(str1);
                }
              }
              localFileItem.r = paramContext;
              if (localFileItem.o.contains("??")) {
                localFileItem.o = "<unknown>";
              }
              if ((paramDmFileCategory.c()) && (i6 != -1) && (i5 != -1))
              {
                localFileItem.k = paramCursor.getInt(i6);
                localFileItem.l = paramCursor.getInt(i5);
                if ((str4.endsWith(".gif")) || (str4.endsWith(".GIF"))) {
                  localFileItem.m = true;
                }
              }
              paramContext = String.valueOf(i7);
              if (str5 == null) {
                continue;
              }
              localFileItem.e = str5;
              localFileItem.h = l1;
              localFileItem.i = localFile.lastModified();
              localFileItem.f = paramContext;
              localFileItem.g = paramContext;
              localFileItem.z = str4;
              if ((localList != null) && (localList.size() > 0) && (localList.contains(localFileItem.z)))
              {
                if (!bool) {
                  break label223;
                }
                localFileItem.C = true;
              }
              if (localFileCategorySorter != null)
              {
                if (!paramDmFileCategory.d()) {
                  continue;
                }
                if (!localArrayList2.contains(localFileItem.o.toLowerCase())) {
                  localArrayList2.add(localFileItem.o.toLowerCase());
                }
              }
              paramArrayList.add(localFileItem);
            }
            catch (Exception paramContext)
            {
              DmLog.e(f, "exception:", paramContext);
            }
          }
          catch (Exception paramContext) {}
          i3 = i9;
          i1 = i8;
          m = i7;
          j = i6;
          i = i5;
          if (!paramDmFileCategory.e()) {
            break;
          }
          i4 = paramCursor.getColumnIndex("album");
          i2 = paramCursor.getColumnIndex("artist");
          n = paramCursor.getColumnIndex("duration");
          k = paramCursor.getColumnIndex("title");
          i3 = i9;
          i1 = i8;
          m = i7;
          j = i6;
          i = i5;
          break;
          paramContext = "";
        }
      } while (!paramDmFileCategory.e());
      paramContext = paramCursor.getString(i4);
      str1 = paramCursor.getString(i2);
      l2 = paramCursor.getLong(n);
      str2 = paramCursor.getString(k);
      if (paramContext == null) {
        break label1107;
      }
      label883:
      localFileItem.n = paramContext;
      localFileItem.p = 0L;
      if (str1 == null) {
        break label1114;
      }
    }
    label1091:
    label1100:
    label1107:
    label1114:
    for (paramContext = str1;; paramContext = "")
    {
      localFileItem.o = paramContext;
      localFileItem.q = l2;
      localFileItem.r = str2;
      break label498;
      localFileItem.e = localFileItem.r;
      break label589;
      a(localFileCategorySorter, localFile, localFileItem, localArrayList1);
      break label708;
      if ((paramDmFileCategory.d()) && (paramArrayList.size() > 0) && (!paramBoolean))
      {
        if (paramInt == 0) {
          a(paramArrayList, localArrayList2);
        }
        for (;;)
        {
          i = 0;
          while (i < paramArrayList.size())
          {
            paramContext = (FileItem)paramArrayList.get(i);
            if (paramInt == 0) {
              paramContext.s = paramContext.o;
            }
            a(localFileCategorySorter, null, paramContext, localArrayList1);
            i += 1;
          }
          b(paramArrayList);
        }
      }
      if (localFileCategorySorter != null)
      {
        localFileCategorySorter.d();
        if ((localArrayList1.size() != 0) && (paramDmFileCategory.n()))
        {
          paramArrayList.removeAll(localArrayList1);
          paramArrayList.addAll(localArrayList1);
        }
      }
      return localFileCategorySorter;
      break label223;
      i5 = -1;
      i6 = -1;
      break;
      paramContext = "";
      break label429;
      paramContext = "";
      break label883;
    }
  }
  
  public static FileItem a(PackageInfo paramPackageInfo, DmFileCategory paramDmFileCategory, PackageManager paramPackageManager)
  {
    try
    {
      paramDmFileCategory = new FileItem(paramDmFileCategory);
      paramPackageManager = paramPackageInfo.applicationInfo.loadLabel(paramPackageManager).toString();
      if (paramPackageInfo != null)
      {
        paramDmFileCategory.t = paramPackageInfo.versionCode;
        paramDmFileCategory.v = paramPackageInfo.versionName;
        paramDmFileCategory.u = paramPackageInfo.packageName;
      }
      paramDmFileCategory.e = (paramPackageManager + ".apk");
      paramDmFileCategory.s = r.b(paramPackageManager);
      paramDmFileCategory.r = paramPackageManager;
      paramDmFileCategory.z = paramPackageInfo.applicationInfo.sourceDir;
      paramDmFileCategory.f = paramPackageInfo.packageName;
      paramDmFileCategory.g = paramPackageInfo.packageName;
      return paramDmFileCategory;
    }
    catch (Exception paramPackageInfo)
    {
      DmLog.e(f, "exception:" + paramPackageInfo);
    }
    return null;
  }
  
  public static FileItem a(File paramFile, DmFileCategory paramDmFileCategory)
  {
    FileItem localFileItem = new FileItem(paramDmFileCategory);
    localFileItem.e = paramFile.getName();
    localFileItem.x = paramFile.isDirectory();
    String str = paramFile.getPath();
    long l = paramFile.lastModified();
    if (!localFileItem.x) {
      localFileItem.h = paramFile.length();
    }
    localFileItem.i = l;
    localFileItem.z = str;
    localFileItem.b = paramDmFileCategory.b;
    try
    {
      paramFile = URLEncoder.encode(str, "UTF-8");
      localFileItem.f = paramFile;
      localFileItem.g = paramFile;
      return localFileItem;
    }
    catch (Exception paramFile)
    {
      DmLog.e(f, "parseFile exception:" + paramFile);
      return null;
    }
    catch (OutOfMemoryError paramFile)
    {
      for (;;) {}
    }
  }
  
  private static String a(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramInt - 1)
    {
      localStringBuilder.append("?,");
      i += 1;
    }
    localStringBuilder.append("?");
    return localStringBuilder.toString();
  }
  
  public static String a(ContentResolver paramContentResolver, Uri paramUri)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramUri != null)
    {
      localObject1 = localObject2;
      if (paramUri.getScheme() != null)
      {
        if (!paramUri.getScheme().equals("file")) {
          break label37;
        }
        localObject1 = paramUri.getPath();
      }
    }
    label37:
    do
    {
      do
      {
        return localObject1;
        localObject1 = localObject2;
      } while (!paramUri.getScheme().equals("content"));
      localObject1 = localObject2;
    } while (paramUri.toString().startsWith("content://mms"));
    return b(paramContentResolver, paramUri);
  }
  
  private static String a(DmFileCategory paramDmFileCategory)
  {
    return a(paramDmFileCategory, 0);
  }
  
  private static String a(DmFileCategory paramDmFileCategory, int paramInt)
  {
    if (paramDmFileCategory.m()) {
      paramDmFileCategory = "date_added DESC";
    }
    do
    {
      return paramDmFileCategory;
      if (paramDmFileCategory.n()) {
        return "date_added DESC";
      }
      paramDmFileCategory = "artist ASC ";
    } while (paramInt != 1);
    return "title_key ASC";
  }
  
  public static ArrayList<FileItem> a(List<String> paramList, DmFileCategory paramDmFileCategory, Context paramContext)
  {
    paramContext = new ArrayList();
    int j = paramList.size();
    int i = 0;
    if (i < j)
    {
      FileItem localFileItem = a(com.dewmobile.transfer.api.a.a((String)paramList.get(i)), paramDmFileCategory);
      if (localFileItem == null) {}
      for (;;)
      {
        i += 1;
        break;
        localFileItem.t = (i + 11);
        paramContext.add(localFileItem);
      }
    }
    return paramContext;
  }
  
  private static ArrayList<FileItem> a(List<File> paramList, DmFileCategory paramDmFileCategory, boolean paramBoolean, Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    boolean bool = com.dewmobile.library.g.b.a().r();
    List localList = j.a(paramContext).a();
    int i;
    int j;
    label63:
    FileItem localFileItem;
    if ((com.dewmobile.library.f.a.a().e().equals(paramDmFileCategory.d)) && (paramDmFileCategory.i()))
    {
      i = 1;
      int k = paramList.size();
      j = 0;
      if (j >= k) {
        break label191;
      }
      paramContext = (File)paramList.get(j);
      localFileItem = a(paramContext, paramDmFileCategory);
      if (localFileItem != null) {
        break label109;
      }
    }
    label109:
    do
    {
      j += 1;
      break label63;
      i = 0;
      break;
      if (!localList.contains(localFileItem.z)) {
        break label135;
      }
    } while (!bool);
    localFileItem.C = true;
    label135:
    if ((j == 0) && (!"/".equalsIgnoreCase(paramDmFileCategory.d)) && (i == 0) && (paramBoolean)) {}
    for (paramContext = "..";; paramContext = paramContext.getName())
    {
      localFileItem.e = paramContext;
      localArrayList.add(localFileItem);
      break;
    }
    label191:
    return localArrayList;
  }
  
  private static HashMap<String, String> a(ContentResolver paramContentResolver)
  {
    Cursor localCursor = paramContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[] { "contact_id", "data1" }, null, null, null);
    if (localCursor == null) {
      return null;
    }
    int i = localCursor.getColumnIndex("contact_id");
    int j = localCursor.getColumnIndex("data1");
    HashMap localHashMap = new HashMap();
    if (localCursor.moveToNext())
    {
      String str = localCursor.getString(i);
      paramContentResolver = (String)localHashMap.get(str);
      if (paramContentResolver == null) {}
      for (paramContentResolver = localCursor.getString(j);; paramContentResolver = paramContentResolver + " ; " + localCursor.getString(j))
      {
        localHashMap.put(str, paramContentResolver);
        break;
      }
    }
    localCursor.close();
    return localHashMap;
  }
  
  public static List<String> a()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = com.dewmobile.transfer.storage.c.a().f().iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((com.dewmobile.transfer.storage.d)localIterator.next()).a);
    }
    a = localArrayList;
    return localArrayList;
  }
  
  /* Error */
  public static List<FileGroupItem> a(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 584	com/dewmobile/transfer/storage/c:a	()Lcom/dewmobile/transfer/storage/c;
    //   3: invokevirtual 613	com/dewmobile/transfer/storage/c:e	()Ljava/util/List;
    //   6: astore_3
    //   7: invokestatic 107	com/dewmobile/library/g/b:a	()Lcom/dewmobile/library/g/b;
    //   10: invokevirtual 289	com/dewmobile/library/g/b:r	()Z
    //   13: istore_2
    //   14: aload_0
    //   15: invokestatic 294	com/dewmobile/library/file/j:a	(Landroid/content/Context;)Lcom/dewmobile/library/file/j;
    //   18: invokevirtual 295	com/dewmobile/library/file/j:a	()Ljava/util/List;
    //   21: astore 8
    //   23: aload_3
    //   24: invokeinterface 590 1 0
    //   29: astore 9
    //   31: aconst_null
    //   32: astore_3
    //   33: aload 9
    //   35: invokeinterface 595 1 0
    //   40: ifeq +437 -> 477
    //   43: aload 9
    //   45: invokeinterface 599 1 0
    //   50: checkcast 601	com/dewmobile/transfer/storage/d
    //   53: astore 4
    //   55: aload_0
    //   56: invokevirtual 619	android/content/Context:getCacheDir	()Ljava/io/File;
    //   59: new 211	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   66: aload 4
    //   68: getfield 603	com/dewmobile/transfer/storage/d:a	Ljava/lang/String;
    //   71: ldc_w 543
    //   74: ldc_w 621
    //   77: invokevirtual 625	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   80: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: getstatic 53	com/dewmobile/library/file/DmLocalFileManager:g	Ljava/lang/String;
    //   86: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: invokestatic 628	com/dewmobile/transfer/api/a:a	(Ljava/io/File;Ljava/lang/String;)Ljava/io/File;
    //   95: astore 4
    //   97: aload 4
    //   99: invokevirtual 159	java/io/File:exists	()Z
    //   102: ifeq -69 -> 33
    //   105: new 630	java/io/ObjectInputStream
    //   108: dup
    //   109: new 632	java/io/FileInputStream
    //   112: dup
    //   113: aload 4
    //   115: invokespecial 635	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   118: invokespecial 638	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   121: astore 4
    //   123: aload 4
    //   125: astore 5
    //   127: aload 4
    //   129: invokevirtual 641	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   132: checkcast 73	java/util/ArrayList
    //   135: astore 7
    //   137: aload 7
    //   139: ifnull +245 -> 384
    //   142: aload 4
    //   144: astore 5
    //   146: new 73	java/util/ArrayList
    //   149: dup
    //   150: invokespecial 76	java/util/ArrayList:<init>	()V
    //   153: astore 6
    //   155: iconst_0
    //   156: istore_1
    //   157: aload 4
    //   159: astore 5
    //   161: iload_1
    //   162: aload 7
    //   164: invokeinterface 384 1 0
    //   169: if_icmpge +412 -> 581
    //   172: aload 4
    //   174: astore 5
    //   176: aload 6
    //   178: new 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   181: dup
    //   182: aload 7
    //   184: iload_1
    //   185: invokeinterface 538 2 0
    //   190: checkcast 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   193: getfield 642	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:a	Ljava/lang/String;
    //   196: invokespecial 645	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:<init>	(Ljava/lang/String;)V
    //   199: invokeinterface 179 2 0
    //   204: pop
    //   205: aload 4
    //   207: astore 5
    //   209: aload 7
    //   211: iload_1
    //   212: invokeinterface 538 2 0
    //   217: checkcast 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   220: getfield 647	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:b	Ljava/util/ArrayList;
    //   223: invokevirtual 648	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   226: astore_3
    //   227: aload 4
    //   229: astore 5
    //   231: aload_3
    //   232: invokeinterface 595 1 0
    //   237: ifeq +140 -> 377
    //   240: aload 4
    //   242: astore 5
    //   244: aload_3
    //   245: invokeinterface 599 1 0
    //   250: checkcast 311	com/dewmobile/library/file/FileItem
    //   253: astore 10
    //   255: aload 8
    //   257: ifnull +50 -> 307
    //   260: aload 4
    //   262: astore 5
    //   264: aload 8
    //   266: invokeinterface 384 1 0
    //   271: ifle +36 -> 307
    //   274: aload 4
    //   276: astore 5
    //   278: aload 8
    //   280: aload 10
    //   282: getfield 381	com/dewmobile/library/file/FileItem:z	Ljava/lang/String;
    //   285: invokeinterface 386 2 0
    //   290: ifeq +17 -> 307
    //   293: iload_2
    //   294: ifeq -67 -> 227
    //   297: aload 4
    //   299: astore 5
    //   301: aload 10
    //   303: iconst_1
    //   304: putfield 388	com/dewmobile/library/file/FileItem:C	Z
    //   307: aload 4
    //   309: astore 5
    //   311: aload 6
    //   313: iload_1
    //   314: invokeinterface 538 2 0
    //   319: checkcast 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   322: getfield 647	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:b	Ljava/util/ArrayList;
    //   325: aload 10
    //   327: invokevirtual 393	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   330: pop
    //   331: goto -104 -> 227
    //   334: astore_3
    //   335: aload 6
    //   337: astore_3
    //   338: ldc_w 650
    //   341: ldc_w 652
    //   344: invokestatic 654	com/dewmobile/library/logging/DmLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   347: aload_3
    //   348: astore 5
    //   350: aload 4
    //   352: ifnull +11 -> 363
    //   355: aload 4
    //   357: invokevirtual 655	java/io/ObjectInputStream:close	()V
    //   360: aload_3
    //   361: astore 5
    //   363: aload 5
    //   365: astore_3
    //   366: goto -333 -> 33
    //   369: astore 5
    //   371: aconst_null
    //   372: astore 7
    //   374: goto -237 -> 137
    //   377: iload_1
    //   378: iconst_1
    //   379: iadd
    //   380: istore_1
    //   381: goto -224 -> 157
    //   384: aload_3
    //   385: astore 5
    //   387: aload 4
    //   389: ifnull -26 -> 363
    //   392: aload 4
    //   394: invokevirtual 655	java/io/ObjectInputStream:close	()V
    //   397: aload_3
    //   398: astore 5
    //   400: goto -37 -> 363
    //   403: astore 4
    //   405: aload_3
    //   406: astore 5
    //   408: goto -45 -> 363
    //   411: astore_3
    //   412: aload 6
    //   414: astore 5
    //   416: aload_3
    //   417: astore 6
    //   419: aload 5
    //   421: astore_3
    //   422: aload 4
    //   424: astore 5
    //   426: ldc_w 650
    //   429: ldc_w 657
    //   432: aload 6
    //   434: invokestatic 398	com/dewmobile/library/logging/DmLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   437: aload_3
    //   438: astore 5
    //   440: aload 4
    //   442: ifnull -79 -> 363
    //   445: aload 4
    //   447: invokevirtual 655	java/io/ObjectInputStream:close	()V
    //   450: aload_3
    //   451: astore 5
    //   453: goto -90 -> 363
    //   456: astore 4
    //   458: aload_3
    //   459: astore 5
    //   461: goto -98 -> 363
    //   464: astore_0
    //   465: aload 5
    //   467: ifnull +8 -> 475
    //   470: aload 5
    //   472: invokevirtual 655	java/io/ObjectInputStream:close	()V
    //   475: aload_0
    //   476: athrow
    //   477: aload_3
    //   478: astore_0
    //   479: aload_3
    //   480: ifnonnull +58 -> 538
    //   483: new 73	java/util/ArrayList
    //   486: dup
    //   487: invokespecial 76	java/util/ArrayList:<init>	()V
    //   490: astore_3
    //   491: iconst_0
    //   492: istore_1
    //   493: aload_3
    //   494: astore_0
    //   495: iload_1
    //   496: iconst_5
    //   497: if_icmpge +41 -> 538
    //   500: aload_3
    //   501: new 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   504: dup
    //   505: getstatic 71	com/dewmobile/library/file/DmLocalFileManager:b	[Ljava/lang/String;
    //   508: iload_1
    //   509: aaload
    //   510: invokespecial 645	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:<init>	(Ljava/lang/String;)V
    //   513: invokeinterface 179 2 0
    //   518: pop
    //   519: iload_1
    //   520: iconst_1
    //   521: iadd
    //   522: istore_1
    //   523: goto -30 -> 493
    //   526: astore 4
    //   528: aload_3
    //   529: astore 5
    //   531: goto -168 -> 363
    //   534: astore_3
    //   535: goto -60 -> 475
    //   538: aload_0
    //   539: areturn
    //   540: astore_0
    //   541: aload 4
    //   543: astore 5
    //   545: goto -80 -> 465
    //   548: astore_0
    //   549: aconst_null
    //   550: astore 5
    //   552: goto -87 -> 465
    //   555: astore 6
    //   557: aconst_null
    //   558: astore 4
    //   560: goto -138 -> 422
    //   563: astore 6
    //   565: goto -143 -> 422
    //   568: astore 4
    //   570: aconst_null
    //   571: astore 4
    //   573: goto -235 -> 338
    //   576: astore 5
    //   578: goto -240 -> 338
    //   581: aload 6
    //   583: astore_3
    //   584: goto -200 -> 384
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	587	0	paramContext	Context
    //   156	367	1	i	int
    //   13	281	2	bool	boolean
    //   6	239	3	localObject1	Object
    //   334	1	3	localEOFException1	java.io.EOFException
    //   337	69	3	localObject2	Object
    //   411	6	3	localException1	Exception
    //   421	108	3	localObject3	Object
    //   534	1	3	localIOException1	java.io.IOException
    //   583	1	3	localObject4	Object
    //   53	340	4	localObject5	Object
    //   403	43	4	localIOException2	java.io.IOException
    //   456	1	4	localIOException3	java.io.IOException
    //   526	16	4	localIOException4	java.io.IOException
    //   558	1	4	localObject6	Object
    //   568	1	4	localEOFException2	java.io.EOFException
    //   571	1	4	localObject7	Object
    //   125	239	5	localObject8	Object
    //   369	1	5	localException2	Exception
    //   385	166	5	localObject9	Object
    //   576	1	5	localEOFException3	java.io.EOFException
    //   153	280	6	localObject10	Object
    //   555	1	6	localException3	Exception
    //   563	19	6	localException4	Exception
    //   135	238	7	localArrayList	ArrayList
    //   21	258	8	localList	List
    //   29	15	9	localIterator	Iterator
    //   253	73	10	localFileItem	FileItem
    // Exception table:
    //   from	to	target	type
    //   161	172	334	java/io/EOFException
    //   176	205	334	java/io/EOFException
    //   209	227	334	java/io/EOFException
    //   231	240	334	java/io/EOFException
    //   244	255	334	java/io/EOFException
    //   264	274	334	java/io/EOFException
    //   278	293	334	java/io/EOFException
    //   301	307	334	java/io/EOFException
    //   311	331	334	java/io/EOFException
    //   127	137	369	java/lang/Exception
    //   392	397	403	java/io/IOException
    //   161	172	411	java/lang/Exception
    //   176	205	411	java/lang/Exception
    //   209	227	411	java/lang/Exception
    //   231	240	411	java/lang/Exception
    //   244	255	411	java/lang/Exception
    //   264	274	411	java/lang/Exception
    //   278	293	411	java/lang/Exception
    //   301	307	411	java/lang/Exception
    //   311	331	411	java/lang/Exception
    //   445	450	456	java/io/IOException
    //   127	137	464	finally
    //   146	155	464	finally
    //   161	172	464	finally
    //   176	205	464	finally
    //   209	227	464	finally
    //   231	240	464	finally
    //   244	255	464	finally
    //   264	274	464	finally
    //   278	293	464	finally
    //   301	307	464	finally
    //   311	331	464	finally
    //   426	437	464	finally
    //   355	360	526	java/io/IOException
    //   470	475	534	java/io/IOException
    //   338	347	540	finally
    //   105	123	548	finally
    //   105	123	555	java/lang/Exception
    //   146	155	563	java/lang/Exception
    //   105	123	568	java/io/EOFException
    //   127	137	576	java/io/EOFException
    //   146	155	576	java/io/EOFException
  }
  
  private static List<FileItem> a(DmFileCategory paramDmFileCategory, List<FileItem> paramList, PackageManager paramPackageManager, HashSet<String> paramHashSet, Set<String> paramSet)
  {
    Collections.sort(paramList, new a(true, paramHashSet, paramSet));
    return paramList;
  }
  
  public static List<FileItem> a(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = b(paramString).iterator();
    while (paramString.hasNext())
    {
      FileItem localFileItem = a((File)paramString.next(), new DmFileCategory(7, 0));
      if (localFileItem != null) {
        localArrayList.add(localFileItem);
      }
    }
    return localArrayList;
  }
  
  private static List<FileItem> a(ArrayList<FileItem> paramArrayList, Context paramContext)
  {
    boolean bool = com.dewmobile.library.g.b.a().r();
    paramContext = j.a(paramContext).a();
    ArrayList localArrayList = new ArrayList();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      FileItem localFileItem = (FileItem)paramArrayList.next();
      if (com.dewmobile.transfer.api.a.a(localFileItem.z).exists()) {
        if ((paramContext != null) && (paramContext.size() > 0) && (paramContext.contains(localFileItem.z)))
        {
          if (bool)
          {
            localFileItem.C = true;
            localArrayList.add(localFileItem);
          }
        }
        else {
          localArrayList.add(localFileItem);
        }
      }
    }
    return localArrayList;
  }
  
  private static void a(int paramInt, List<File> paramList)
  {
    Object localObject;
    if ((paramInt & 0xC) == 4) {
      if ((paramInt & 0x10) == 16) {
        localObject = new g(false);
      }
    }
    for (;;)
    {
      Collections.sort(paramList, (Comparator)localObject);
      return;
      localObject = new g(true);
      continue;
      if ((paramInt & 0xC) == 8)
      {
        if ((paramInt & 0x10) == 16) {
          localObject = new i(false);
        } else {
          localObject = new i(true);
        }
      }
      else if ((paramInt & 0x10) == 16) {
        localObject = new h(false);
      } else {
        localObject = new h(true);
      }
    }
  }
  
  private static void a(Context paramContext, DmFileCategory paramDmFileCategory, ArrayList<FileItem> paramArrayList)
  {
    paramContext = com.dewmobile.library.file.b.a.a(paramContext.getApplicationContext());
    if (!paramContext.a(paramDmFileCategory))
    {
      paramContext.b(paramDmFileCategory);
      paramDmFileCategory = new ArrayList();
      paramDmFileCategory.addAll(paramArrayList);
      paramContext.a(paramDmFileCategory);
    }
  }
  
  private static void a(DmFileCategory paramDmFileCategory, List<File> paramList)
  {
    int i = paramDmFileCategory.c;
    if ((i & 0xC) == 4) {
      if ((i & 0x10) == 16) {
        paramDmFileCategory = new g(false);
      }
    }
    for (;;)
    {
      Collections.sort(paramList, paramDmFileCategory);
      return;
      paramDmFileCategory = new g(true);
      continue;
      if ((i & 0xC) == 8)
      {
        if ((i & 0x10) == 16) {
          paramDmFileCategory = new i(false);
        } else {
          paramDmFileCategory = new i(true);
        }
      }
      else if ((i & 0x10) == 16) {
        paramDmFileCategory = new h(false);
      } else {
        paramDmFileCategory = new h(true);
      }
    }
  }
  
  private static void a(FileCategorySorter paramFileCategorySorter, File paramFile, FileItem paramFileItem, List<FileItem> paramList)
  {
    int i;
    if ((paramFileItem.c()) || (paramFileItem.e()) || (paramFileItem.t()))
    {
      i = paramFileCategorySorter.a(Long.valueOf(paramFile.lastModified()));
      if (i >= 0) {
        paramFileCategorySorter.a(i, paramFileItem);
      }
    }
    do
    {
      do
      {
        return;
      } while ((!paramFileItem.d()) && (!paramFileItem.b()));
      i = paramFileCategorySorter.a(paramFileItem.s);
    } while (i < 0);
    paramFileCategorySorter.a(i, paramFileItem);
  }
  
  private static void a(ArrayList<FileItem> paramArrayList)
  {
    com.dewmobile.library.file.b.b localB = com.dewmobile.library.file.b.b.a();
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      FileItem localFileItem = (FileItem)localIterator.next();
      if (TextUtils.isEmpty(localFileItem.e))
      {
        localFileItem.s = "#";
      }
      else
      {
        String str = k(((String)localB.a(localFileItem.e).values().iterator().next()).trim().toUpperCase());
        if (!TextUtils.isEmpty(str))
        {
          char c1 = str.charAt(0);
          if ((c1 >= 'A') && (c1 <= 'Z')) {
            localFileItem.s = String.valueOf(c1);
          } else {
            localFileItem.s = "#";
          }
        }
        else
        {
          localFileItem.s = "#";
        }
      }
    }
    Collections.sort(paramArrayList, new Comparator()
    {
      public int a(FileItem paramAnonymousFileItem1, FileItem paramAnonymousFileItem2)
      {
        return paramAnonymousFileItem1.s.compareTo(paramAnonymousFileItem2.s);
      }
    });
  }
  
  public static void a(ArrayList<FileItem> paramArrayList, ArrayList<String> paramArrayList1)
  {
    int k = 0;
    if ((paramArrayList == null) || (paramArrayList.size() == 0)) {
      return;
    }
    int i = 0;
    FileItem localFileItem;
    String str;
    while (i < paramArrayList.size())
    {
      localFileItem = (FileItem)paramArrayList.get(i);
      if ((localFileItem.o.equalsIgnoreCase("<unknown>")) || (localFileItem.o.equalsIgnoreCase("<Undefined>")))
      {
        str = o.a(localFileItem.e, false, false);
        if ((str != null) && (paramArrayList1.contains(str.toLowerCase()))) {
          localFileItem.o = str;
        }
      }
      i += 1;
    }
    i = 0;
    int j;
    for (;;)
    {
      j = k;
      if (i >= paramArrayList.size()) {
        break;
      }
      localFileItem = (FileItem)paramArrayList.get(i);
      if ((localFileItem.o.equalsIgnoreCase("<unknown>")) || (localFileItem.o.equalsIgnoreCase("<Undefined>")))
      {
        str = o.a(localFileItem.e, true, false);
        if ((str != null) && (paramArrayList1.contains(str.toLowerCase()))) {
          localFileItem.o = str;
        }
      }
      i += 1;
    }
    while (j < paramArrayList.size())
    {
      localFileItem = (FileItem)paramArrayList.get(j);
      if ((localFileItem.o.equalsIgnoreCase("<unknown>")) || (localFileItem.o.equalsIgnoreCase("<Undefined>")))
      {
        str = o.a(localFileItem.e, true, true);
        if ((str != null) && (paramArrayList1.contains(str.toLowerCase()))) {
          localFileItem.o = str;
        }
      }
      j += 1;
    }
    Collections.sort(paramArrayList, new Comparator()
    {
      public int a(FileItem paramAnonymousFileItem1, FileItem paramAnonymousFileItem2)
      {
        if (this.a) {
          return Collator.getInstance(Locale.CHINA).compare(paramAnonymousFileItem1.o, paramAnonymousFileItem2.o);
        }
        return paramAnonymousFileItem1.o.compareTo(paramAnonymousFileItem2.o);
      }
    });
  }
  
  public static void a(List<FileGroupItem> paramList, File paramFile, int paramInt, DmFileCategory paramDmFileCategory)
  {
    paramFile = paramFile.listFiles();
    if (paramFile == null) {}
    for (;;)
    {
      return;
      int i = paramFile.length;
      paramInt = 0;
      while (paramInt < i)
      {
        Object localObject1 = paramFile[paramInt];
        if (localObject1.isDirectory())
        {
          Object localObject2 = com.dewmobile.transfer.api.a.a(localObject1.getPath() + "/MP4/vfs.db");
          if (((File)localObject2).exists())
          {
            localObject2 = a((File)localObject2, paramDmFileCategory);
            ((FileItem)localObject2).e = localObject1.getName();
            ((FileItem)localObject2).b = 0;
            ((FileGroupItem)paramList.get(0)).b.add(localObject2);
          }
        }
        paramInt += 1;
      }
    }
  }
  
  private static boolean a(File paramFile)
  {
    paramFile = paramFile.getAbsolutePath();
    return h.contains(paramFile);
  }
  
  public static boolean a(HashMap<String, JSONObject> paramHashMap, ApplicationInfo paramApplicationInfo, FileItem paramFileItem)
  {
    if ((paramHashMap == null) || (paramHashMap.size() < 1)) {
      return false;
    }
    try
    {
      if (paramHashMap.keySet().contains(paramApplicationInfo.packageName))
      {
        paramHashMap = (JSONObject)paramHashMap.get(paramApplicationInfo.packageName);
        paramApplicationInfo = com.dewmobile.transfer.utils.f.a(paramApplicationInfo.sourceDir);
        if (paramHashMap.getString("md5").equals(paramApplicationInfo))
        {
          paramFileItem.A = (paramHashMap.optDouble("price") + "");
          return true;
        }
        return false;
      }
    }
    catch (JSONException paramHashMap)
    {
      paramHashMap.printStackTrace();
    }
    return false;
  }
  
  private static boolean a(List<FileItem> paramList1, List<FileItem> paramList2, ApplicationInfo paramApplicationInfo, FileItem paramFileItem)
  {
    if (paramList1 != null)
    {
      paramList1 = paramList1.iterator();
      while (paramList1.hasNext())
      {
        FileItem localFileItem = (FileItem)paramList1.next();
        if (localFileItem.y.F.equals(paramApplicationInfo.packageName))
        {
          if (!localFileItem.y.h())
          {
            localFileItem.y.N = paramFileItem.z;
            localFileItem.y.M = paramFileItem.t;
          }
          paramFileItem.y = localFileItem.y;
          paramList2.add(paramFileItem);
          return true;
        }
      }
    }
    return false;
  }
  
  private static long b(File paramFile)
  {
    int k = 0;
    int i = 0;
    if (paramFile.isDirectory())
    {
      String[] arrayOfString = paramFile.list();
      int m = arrayOfString.length;
      int j = 0;
      for (;;)
      {
        k = i;
        if (j >= m) {
          break;
        }
        String str = arrayOfString[j];
        File localFile = com.dewmobile.transfer.api.a.a(paramFile, str);
        k = i;
        if (localFile.isFile())
        {
          k = i;
          if (g(str))
          {
            long l = i;
            k = (int)(localFile.length() + l);
          }
        }
        j += 1;
        i = k;
      }
    }
    return k;
  }
  
  /* Error */
  public static LocalFileResult b(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 107	com/dewmobile/library/g/b:a	()Lcom/dewmobile/library/g/b;
    //   3: ldc_w 868
    //   6: iconst_0
    //   7: invokevirtual 871	com/dewmobile/library/g/b:a	(Ljava/lang/String;Z)Z
    //   10: istore_2
    //   11: iload_2
    //   12: ifne +977 -> 989
    //   15: aload_0
    //   16: ldc_w 873
    //   19: invokestatic 878	com/dewmobile/library/m/k:a	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   22: ifnull +63 -> 85
    //   25: iconst_1
    //   26: istore_2
    //   27: iload_2
    //   28: ifeq +956 -> 984
    //   31: bipush 6
    //   33: istore_1
    //   34: invokestatic 584	com/dewmobile/transfer/storage/c:a	()Lcom/dewmobile/transfer/storage/c;
    //   37: invokevirtual 613	com/dewmobile/transfer/storage/c:e	()Ljava/util/List;
    //   40: astore_3
    //   41: new 73	java/util/ArrayList
    //   44: dup
    //   45: iload_1
    //   46: invokespecial 881	java/util/ArrayList:<init>	(I)V
    //   49: astore 6
    //   51: iconst_0
    //   52: istore_1
    //   53: iload_1
    //   54: iconst_5
    //   55: if_icmpge +35 -> 90
    //   58: aload 6
    //   60: new 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   63: dup
    //   64: getstatic 71	com/dewmobile/library/file/DmLocalFileManager:b	[Ljava/lang/String;
    //   67: iload_1
    //   68: aaload
    //   69: invokespecial 645	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:<init>	(Ljava/lang/String;)V
    //   72: invokeinterface 179 2 0
    //   77: pop
    //   78: iload_1
    //   79: iconst_1
    //   80: iadd
    //   81: istore_1
    //   82: goto -29 -> 53
    //   85: iconst_0
    //   86: istore_2
    //   87: goto -60 -> 27
    //   90: new 90	com/dewmobile/library/file/DmFileCategory
    //   93: dup
    //   94: iconst_5
    //   95: iconst_0
    //   96: invokespecial 677	com/dewmobile/library/file/DmFileCategory:<init>	(II)V
    //   99: astore 7
    //   101: aload_3
    //   102: invokeinterface 590 1 0
    //   107: astore 8
    //   109: aload 8
    //   111: invokeinterface 595 1 0
    //   116: ifeq +670 -> 786
    //   119: aload 8
    //   121: invokeinterface 599 1 0
    //   126: checkcast 601	com/dewmobile/transfer/storage/d
    //   129: astore 9
    //   131: aload 9
    //   133: getfield 603	com/dewmobile/transfer/storage/d:a	Ljava/lang/String;
    //   136: invokestatic 154	com/dewmobile/transfer/api/a:a	(Ljava/lang/String;)Ljava/io/File;
    //   139: astore_3
    //   140: new 73	java/util/ArrayList
    //   143: dup
    //   144: invokespecial 76	java/util/ArrayList:<init>	()V
    //   147: astore 5
    //   149: iconst_0
    //   150: istore_1
    //   151: iload_1
    //   152: iconst_5
    //   153: if_icmpge +30 -> 183
    //   156: aload 5
    //   158: new 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   161: dup
    //   162: getstatic 71	com/dewmobile/library/file/DmLocalFileManager:b	[Ljava/lang/String;
    //   165: iload_1
    //   166: aaload
    //   167: invokespecial 645	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:<init>	(Ljava/lang/String;)V
    //   170: invokeinterface 179 2 0
    //   175: pop
    //   176: iload_1
    //   177: iconst_1
    //   178: iadd
    //   179: istore_1
    //   180: goto -29 -> 151
    //   183: aload 5
    //   185: aload_3
    //   186: iconst_0
    //   187: aload 7
    //   189: invokestatic 883	com/dewmobile/library/file/DmLocalFileManager:b	(Ljava/util/List;Ljava/io/File;ILcom/dewmobile/library/file/DmFileCategory;)V
    //   192: new 211	java/lang/StringBuilder
    //   195: dup
    //   196: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   199: aload 9
    //   201: getfield 603	com/dewmobile/transfer/storage/d:a	Ljava/lang/String;
    //   204: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: ldc_w 885
    //   210: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   216: astore_3
    //   217: new 211	java/lang/StringBuilder
    //   220: dup
    //   221: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   224: aload_3
    //   225: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: ldc_w 887
    //   231: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   237: invokestatic 154	com/dewmobile/transfer/api/a:a	(Ljava/lang/String;)Ljava/io/File;
    //   240: astore 4
    //   242: aload 4
    //   244: invokevirtual 159	java/io/File:exists	()Z
    //   247: ifeq +13 -> 260
    //   250: aload 5
    //   252: aload 4
    //   254: iconst_0
    //   255: aload 7
    //   257: invokestatic 889	com/dewmobile/library/file/DmLocalFileManager:c	(Ljava/util/List;Ljava/io/File;ILcom/dewmobile/library/file/DmFileCategory;)V
    //   260: new 211	java/lang/StringBuilder
    //   263: dup
    //   264: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   267: aload_3
    //   268: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: ldc_w 891
    //   274: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   280: invokestatic 154	com/dewmobile/transfer/api/a:a	(Ljava/lang/String;)Ljava/io/File;
    //   283: astore 4
    //   285: aload 4
    //   287: invokevirtual 159	java/io/File:exists	()Z
    //   290: ifeq +13 -> 303
    //   293: aload 5
    //   295: aload 4
    //   297: iconst_0
    //   298: aload 7
    //   300: invokestatic 889	com/dewmobile/library/file/DmLocalFileManager:c	(Ljava/util/List;Ljava/io/File;ILcom/dewmobile/library/file/DmFileCategory;)V
    //   303: new 211	java/lang/StringBuilder
    //   306: dup
    //   307: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   310: aload_3
    //   311: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   314: ldc_w 893
    //   317: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   323: invokestatic 154	com/dewmobile/transfer/api/a:a	(Ljava/lang/String;)Ljava/io/File;
    //   326: astore 4
    //   328: aload 4
    //   330: invokevirtual 159	java/io/File:exists	()Z
    //   333: ifeq +13 -> 346
    //   336: aload 5
    //   338: aload 4
    //   340: iconst_0
    //   341: aload 7
    //   343: invokestatic 895	com/dewmobile/library/file/DmLocalFileManager:a	(Ljava/util/List;Ljava/io/File;ILcom/dewmobile/library/file/DmFileCategory;)V
    //   346: new 211	java/lang/StringBuilder
    //   349: dup
    //   350: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   353: aload_3
    //   354: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: ldc_w 897
    //   360: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   363: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   366: invokestatic 154	com/dewmobile/transfer/api/a:a	(Ljava/lang/String;)Ljava/io/File;
    //   369: astore_3
    //   370: aload_3
    //   371: invokevirtual 159	java/io/File:exists	()Z
    //   374: ifeq +12 -> 386
    //   377: aload 5
    //   379: aload_3
    //   380: iconst_0
    //   381: aload 7
    //   383: invokestatic 889	com/dewmobile/library/file/DmLocalFileManager:c	(Ljava/util/List;Ljava/io/File;ILcom/dewmobile/library/file/DmFileCategory;)V
    //   386: iload_2
    //   387: ifeq +72 -> 459
    //   390: ldc_w 899
    //   393: astore 4
    //   395: new 211	java/lang/StringBuilder
    //   398: dup
    //   399: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   402: aload 9
    //   404: getfield 603	com/dewmobile/transfer/storage/d:a	Ljava/lang/String;
    //   407: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: ldc_w 899
    //   413: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   416: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   419: invokestatic 154	com/dewmobile/transfer/api/a:a	(Ljava/lang/String;)Ljava/io/File;
    //   422: astore_3
    //   423: aload_3
    //   424: invokevirtual 159	java/io/File:exists	()Z
    //   427: ifeq +124 -> 551
    //   430: aload 5
    //   432: new 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   435: dup
    //   436: getstatic 71	com/dewmobile/library/file/DmLocalFileManager:b	[Ljava/lang/String;
    //   439: iconst_5
    //   440: aaload
    //   441: invokespecial 645	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:<init>	(Ljava/lang/String;)V
    //   444: invokeinterface 179 2 0
    //   449: pop
    //   450: aload 5
    //   452: aload_3
    //   453: iconst_0
    //   454: aload 7
    //   456: invokestatic 901	com/dewmobile/library/file/DmLocalFileManager:e	(Ljava/util/List;Ljava/io/File;ILcom/dewmobile/library/file/DmFileCategory;)V
    //   459: iconst_0
    //   460: istore_1
    //   461: iload_1
    //   462: aload 5
    //   464: invokeinterface 384 1 0
    //   469: if_icmpge +182 -> 651
    //   472: iload_1
    //   473: aload 6
    //   475: invokeinterface 384 1 0
    //   480: if_icmplt +32 -> 512
    //   483: aload 6
    //   485: new 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   488: dup
    //   489: aload 5
    //   491: iload_1
    //   492: invokeinterface 538 2 0
    //   497: checkcast 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   500: getfield 642	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:a	Ljava/lang/String;
    //   503: invokespecial 645	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:<init>	(Ljava/lang/String;)V
    //   506: invokeinterface 179 2 0
    //   511: pop
    //   512: aload 6
    //   514: iload_1
    //   515: invokeinterface 538 2 0
    //   520: checkcast 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   523: getfield 647	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:b	Ljava/util/ArrayList;
    //   526: aload 5
    //   528: iload_1
    //   529: invokeinterface 538 2 0
    //   534: checkcast 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   537: getfield 647	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:b	Ljava/util/ArrayList;
    //   540: invokevirtual 427	java/util/ArrayList:addAll	(Ljava/util/Collection;)Z
    //   543: pop
    //   544: iload_1
    //   545: iconst_1
    //   546: iadd
    //   547: istore_1
    //   548: goto -87 -> 461
    //   551: aload 4
    //   553: astore_3
    //   554: getstatic 276	android/os/Build$VERSION:SDK_INT	I
    //   557: bipush 19
    //   559: if_icmplt +27 -> 586
    //   562: aload 4
    //   564: astore_3
    //   565: aload 9
    //   567: getfield 603	com/dewmobile/transfer/storage/d:a	Ljava/lang/String;
    //   570: invokevirtual 391	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   573: ldc_w 903
    //   576: invokevirtual 346	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   579: ifeq +7 -> 586
    //   582: ldc_w 905
    //   585: astore_3
    //   586: new 211	java/lang/StringBuilder
    //   589: dup
    //   590: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   593: aload 9
    //   595: getfield 603	com/dewmobile/transfer/storage/d:a	Ljava/lang/String;
    //   598: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   601: aload_3
    //   602: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   605: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   608: invokestatic 154	com/dewmobile/transfer/api/a:a	(Ljava/lang/String;)Ljava/io/File;
    //   611: astore_3
    //   612: aload_3
    //   613: invokevirtual 159	java/io/File:exists	()Z
    //   616: ifeq -157 -> 459
    //   619: aload 5
    //   621: new 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   624: dup
    //   625: getstatic 71	com/dewmobile/library/file/DmLocalFileManager:b	[Ljava/lang/String;
    //   628: iconst_5
    //   629: aaload
    //   630: invokespecial 645	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:<init>	(Ljava/lang/String;)V
    //   633: invokeinterface 179 2 0
    //   638: pop
    //   639: aload 5
    //   641: aload_3
    //   642: iconst_0
    //   643: aload 7
    //   645: invokestatic 901	com/dewmobile/library/file/DmLocalFileManager:e	(Ljava/util/List;Ljava/io/File;ILcom/dewmobile/library/file/DmFileCategory;)V
    //   648: goto -189 -> 459
    //   651: aconst_null
    //   652: astore_3
    //   653: new 907	java/io/ObjectOutputStream
    //   656: dup
    //   657: aload_0
    //   658: invokevirtual 619	android/content/Context:getCacheDir	()Ljava/io/File;
    //   661: new 211	java/lang/StringBuilder
    //   664: dup
    //   665: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   668: aload 9
    //   670: getfield 603	com/dewmobile/transfer/storage/d:a	Ljava/lang/String;
    //   673: ldc_w 543
    //   676: ldc_w 621
    //   679: invokevirtual 625	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   682: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   685: aload 7
    //   687: invokevirtual 474	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   690: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   693: invokestatic 628	com/dewmobile/transfer/api/a:a	(Ljava/io/File;Ljava/lang/String;)Ljava/io/File;
    //   696: invokestatic 912	com/dewmobile/transfer/api/d:a	(Ljava/io/File;)Ljava/io/OutputStream;
    //   699: invokespecial 915	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   702: astore 4
    //   704: aload 4
    //   706: astore_3
    //   707: aload 4
    //   709: aload 5
    //   711: invokevirtual 919	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   714: aload 4
    //   716: astore_3
    //   717: aload 4
    //   719: invokevirtual 920	java/io/ObjectOutputStream:close	()V
    //   722: aload 4
    //   724: ifnull -615 -> 109
    //   727: aload 4
    //   729: invokevirtual 920	java/io/ObjectOutputStream:close	()V
    //   732: goto -623 -> 109
    //   735: astore_3
    //   736: goto -627 -> 109
    //   739: astore 5
    //   741: aconst_null
    //   742: astore 4
    //   744: aload 4
    //   746: astore_3
    //   747: ldc_w 650
    //   750: ldc_w 922
    //   753: aload 5
    //   755: invokestatic 398	com/dewmobile/library/logging/DmLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   758: aload 4
    //   760: ifnull -651 -> 109
    //   763: aload 4
    //   765: invokevirtual 920	java/io/ObjectOutputStream:close	()V
    //   768: goto -659 -> 109
    //   771: astore_3
    //   772: goto -663 -> 109
    //   775: astore_0
    //   776: aload_3
    //   777: ifnull +7 -> 784
    //   780: aload_3
    //   781: invokevirtual 920	java/io/ObjectOutputStream:close	()V
    //   784: aload_0
    //   785: athrow
    //   786: new 924	android/content/Intent
    //   789: dup
    //   790: ldc_w 926
    //   793: invokespecial 927	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   796: astore_3
    //   797: invokestatic 931	com/dewmobile/library/d/b:a	()Landroid/content/Context;
    //   800: invokestatic 937	android/support/v4/content/LocalBroadcastManager:getInstance	(Landroid/content/Context;)Landroid/support/v4/content/LocalBroadcastManager;
    //   803: aload_3
    //   804: invokevirtual 941	android/support/v4/content/LocalBroadcastManager:sendBroadcast	(Landroid/content/Intent;)Z
    //   807: pop
    //   808: new 21	com/dewmobile/library/file/DmLocalFileManager$LocalFileResult
    //   811: dup
    //   812: invokespecial 143	com/dewmobile/library/file/DmLocalFileManager$LocalFileResult:<init>	()V
    //   815: astore_3
    //   816: aload_3
    //   817: aload 6
    //   819: putfield 942	com/dewmobile/library/file/DmLocalFileManager$LocalFileResult:c	Ljava/util/List;
    //   822: aload_3
    //   823: aload 6
    //   825: iconst_1
    //   826: invokeinterface 538 2 0
    //   831: checkcast 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   834: getfield 647	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:b	Ljava/util/ArrayList;
    //   837: putfield 234	com/dewmobile/library/file/DmLocalFileManager$LocalFileResult:a	Ljava/util/ArrayList;
    //   840: aload_0
    //   841: invokestatic 702	com/dewmobile/library/file/b/a:a	(Landroid/content/Context;)Lcom/dewmobile/library/file/b/a;
    //   844: astore 4
    //   846: aload 4
    //   848: aload 7
    //   850: invokevirtual 705	com/dewmobile/library/file/b/a:a	(Lcom/dewmobile/library/file/DmFileCategory;)Z
    //   853: ifne +87 -> 940
    //   856: aload 4
    //   858: aload 7
    //   860: invokevirtual 707	com/dewmobile/library/file/b/a:b	(Lcom/dewmobile/library/file/DmFileCategory;)V
    //   863: aload 6
    //   865: invokeinterface 590 1 0
    //   870: astore 5
    //   872: aload 5
    //   874: invokeinterface 595 1 0
    //   879: ifeq +56 -> 935
    //   882: aload 5
    //   884: invokeinterface 599 1 0
    //   889: checkcast 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   892: astore 7
    //   894: aload 7
    //   896: getfield 647	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:b	Ljava/util/ArrayList;
    //   899: invokevirtual 404	java/util/ArrayList:size	()I
    //   902: ifle -30 -> 872
    //   905: new 73	java/util/ArrayList
    //   908: dup
    //   909: invokespecial 76	java/util/ArrayList:<init>	()V
    //   912: astore 8
    //   914: aload 8
    //   916: aload 7
    //   918: getfield 647	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:b	Ljava/util/ArrayList;
    //   921: invokevirtual 427	java/util/ArrayList:addAll	(Ljava/util/Collection;)Z
    //   924: pop
    //   925: aload 4
    //   927: aload 8
    //   929: invokevirtual 710	com/dewmobile/library/file/b/a:a	(Ljava/util/List;)V
    //   932: goto -60 -> 872
    //   935: aload 4
    //   937: invokevirtual 944	com/dewmobile/library/file/b/a:c	()V
    //   940: aload_0
    //   941: iconst_4
    //   942: invokestatic 949	com/dewmobile/library/backend/g:a	(Landroid/content/Context;I)Z
    //   945: ifeq +24 -> 969
    //   948: invokestatic 952	com/dewmobile/library/backend/g:a	()Lcom/dewmobile/library/backend/g;
    //   951: aload 6
    //   953: iconst_3
    //   954: invokeinterface 538 2 0
    //   959: checkcast 18	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem
    //   962: getfield 647	com/dewmobile/library/file/DmLocalFileManager$FileGroupItem:b	Ljava/util/ArrayList;
    //   965: iconst_4
    //   966: invokevirtual 955	com/dewmobile/library/backend/g:a	(Ljava/util/List;I)V
    //   969: aload_3
    //   970: areturn
    //   971: astore_3
    //   972: goto -188 -> 784
    //   975: astore_0
    //   976: goto -200 -> 776
    //   979: astore 5
    //   981: goto -237 -> 744
    //   984: iconst_5
    //   985: istore_1
    //   986: goto -952 -> 34
    //   989: goto -962 -> 27
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	992	0	paramContext	Context
    //   33	953	1	i	int
    //   10	377	2	bool	boolean
    //   40	677	3	localObject1	Object
    //   735	1	3	localIOException1	java.io.IOException
    //   746	1	3	localObject2	Object
    //   771	10	3	localIOException2	java.io.IOException
    //   796	174	3	localObject3	Object
    //   971	1	3	localIOException3	java.io.IOException
    //   240	696	4	localObject4	Object
    //   147	563	5	localArrayList1	ArrayList
    //   739	15	5	localException1	Exception
    //   870	13	5	localIterator	Iterator
    //   979	1	5	localException2	Exception
    //   49	903	6	localArrayList2	ArrayList
    //   99	818	7	localObject5	Object
    //   107	821	8	localObject6	Object
    //   129	540	9	localD	com.dewmobile.transfer.storage.d
    // Exception table:
    //   from	to	target	type
    //   727	732	735	java/io/IOException
    //   653	704	739	java/lang/Exception
    //   763	768	771	java/io/IOException
    //   653	704	775	finally
    //   780	784	971	java/io/IOException
    //   707	714	975	finally
    //   717	722	975	finally
    //   747	758	975	finally
    //   707	714	979	java/lang/Exception
    //   717	722	979	java/lang/Exception
  }
  
  public static LocalFileResult b(Context paramContext, DmFileCategory paramDmFileCategory)
  {
    Object localObject1 = b(paramDmFileCategory.c);
    Object localObject3 = paramContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[] { "_id", "_display_name", "date_modified", "_size", "title", "_data", "date_added", "album", "artist", "duration" }, "_size > ?", new String[] { "102400" }, (String)localObject1);
    localObject1 = a(paramContext);
    Object localObject4 = new ArrayList();
    if ((localObject1 != null) && (((List)localObject1).size() > 0))
    {
      b(paramDmFileCategory, (List)localObject1);
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (FileGroupItem)((Iterator)localObject1).next();
        if (("video".equals(((FileGroupItem)localObject2).a)) || ("omni_video".equals(((FileGroupItem)localObject2).a))) {
          ((ArrayList)localObject4).addAll(((FileGroupItem)localObject2).b);
        }
      }
    }
    if ((localObject3 == null) && (((ArrayList)localObject4).size() == 0))
    {
      DmLog.e(f, "No VIDEO found in data base!");
      return null;
    }
    Object localObject2 = new ArrayList();
    if (localObject3 == null) {}
    for (localObject1 = j(paramContext, paramDmFileCategory);; localObject1 = a(paramContext, (ArrayList)localObject2, paramDmFileCategory, (Cursor)localObject3))
    {
      c((ArrayList)localObject2);
      Object localObject5 = new LinkedHashSet();
      ((HashSet)localObject5).addAll((Collection)localObject2);
      if (((ArrayList)localObject4).size() > 0) {
        ((HashSet)localObject5).addAll(a((ArrayList)localObject4, paramContext));
      }
      ((Cursor)localObject3).close();
      localObject3 = new LocalFileResult();
      ((LocalFileResult)localObject3).a = new ArrayList((Collection)localObject5);
      localObject4 = new Comparator()
      {
        public int a(FileItem paramAnonymousFileItem1, FileItem paramAnonymousFileItem2)
        {
          if (paramAnonymousFileItem1.i > paramAnonymousFileItem2.i) {
            return -1;
          }
          if (paramAnonymousFileItem1.i == paramAnonymousFileItem2.i) {
            return 0;
          }
          return 1;
        }
      };
      Collections.sort(((LocalFileResult)localObject3).a, (Comparator)localObject4);
      ((FileCategorySorter)localObject1).e();
      localObject4 = ((LocalFileResult)localObject3).a.iterator();
      while (((Iterator)localObject4).hasNext())
      {
        localObject5 = (FileItem)((Iterator)localObject4).next();
        a((FileCategorySorter)localObject1, com.dewmobile.transfer.api.a.a(((FileItem)localObject5).z), (FileItem)localObject5, null);
      }
    }
    ((FileCategorySorter)localObject1).d();
    ((LocalFileResult)localObject3).b = ((FileCategorySorter)localObject1);
    a(paramContext, paramDmFileCategory, ((LocalFileResult)localObject3).a);
    if (com.dewmobile.library.backend.g.a(paramContext, 3)) {
      com.dewmobile.library.backend.g.a().a((List)localObject2, 3);
    }
    return localObject3;
  }
  
  private static FileItem b(Context paramContext, ArrayList<FileItem> paramArrayList, DmFileCategory paramDmFileCategory, Cursor paramCursor)
  {
    int i = paramCursor.getColumnIndex("date_modified");
    int j = paramCursor.getColumnIndex("_id");
    int k = paramCursor.getColumnIndex("_display_name");
    int m = paramCursor.getColumnIndex("_data");
    FileItem localFileItem = null;
    boolean bool = com.dewmobile.library.g.b.a().r();
    List localList = j.a(paramContext).a();
    paramContext = localFileItem;
    for (;;)
    {
      if (paramCursor.moveToNext())
      {
        int n = paramCursor.getInt(j);
        String str1 = paramCursor.getString(m);
        long l1 = com.dewmobile.transfer.api.a.a(str1).length();
        if (0L == l1) {
          continue;
        }
        String str2 = paramCursor.getString(k);
        long l2 = paramCursor.getLong(i);
        localFileItem = new FileItem(paramDmFileCategory);
        try
        {
          String str3 = String.valueOf(n);
          localFileItem.e = str2;
          localFileItem.h = l1;
          localFileItem.i = l2;
          localFileItem.f = str3;
          localFileItem.g = str3;
          localFileItem.z = str1;
          if (localList.contains(localFileItem.z))
          {
            if (bool) {
              localFileItem.C = true;
            }
          }
          else
          {
            paramArrayList.add(localFileItem);
            if (paramContext == null) {
              paramContext = localFileItem;
            }
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            DmLog.e(f, "exception:", localException);
          }
        }
      }
    }
    return paramContext;
  }
  
  private static String b(int paramInt)
  {
    String str;
    if ((paramInt & 0xC) == 4) {
      str = "date_modified";
    }
    while ((paramInt & 0x10) == 16)
    {
      return str + " DESC";
      if ((paramInt & 0xC) == 8) {
        str = "_size";
      } else {
        str = "_display_name COLLATE LOCALIZED ";
      }
    }
    return str + " ASC";
  }
  
  public static String b(ContentResolver paramContentResolver, Uri paramUri)
  {
    Object localObject = null;
    paramUri = paramContentResolver.query(paramUri, new String[] { "_data" }, null, null, null);
    paramContentResolver = localObject;
    if (paramUri != null) {}
    try
    {
      int i = paramUri.getColumnIndexOrThrow("_data");
      paramUri.moveToFirst();
      paramContentResolver = paramUri.getString(i);
      paramUri.close();
      return paramContentResolver;
    }
    catch (Exception paramContentResolver)
    {
      paramContentResolver = paramContentResolver;
      paramUri.close();
      return null;
    }
    finally
    {
      paramContentResolver = finally;
      paramUri.close();
      throw paramContentResolver;
    }
  }
  
  public static List<File> b(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    File localFile = com.dewmobile.transfer.api.a.a(paramString);
    if ((paramString != null) && (localFile.exists()))
    {
      paramString = localFile.listFiles(new FilenameFilter()
      {
        public boolean accept(File paramAnonymousFile, String paramAnonymousString)
        {
          paramAnonymousFile = paramAnonymousString.toLowerCase();
          return (paramAnonymousFile.endsWith(".jpg")) || (paramAnonymousFile.endsWith(".jpeg")) || (paramAnonymousFile.endsWith(".png")) || (paramAnonymousFile.endsWith(".gif")) || (paramAnonymousFile.endsWith(".bmp"));
        }
      });
      if (paramString != null)
      {
        int j = paramString.length;
        int i = 0;
        while (i < j)
        {
          localFile = paramString[i];
          if ((!localFile.isHidden()) && (localFile.isFile()) && (localFile.length() > 0L)) {
            localArrayList.add(localFile);
          }
          i += 1;
        }
      }
    }
    return localArrayList;
  }
  
  private static void b()
  {
    if (h == null)
    {
      h = new ArrayList(3);
      String str1 = com.dewmobile.library.f.a.a().j();
      String str2 = com.dewmobile.library.f.a.a().l();
      String str3 = com.dewmobile.library.f.a.a().t();
      h.add(str1);
      h.add(str2);
      h.add(str3);
    }
  }
  
  private static void b(DmFileCategory paramDmFileCategory, List<FileGroupItem> paramList)
  {
    com.dewmobile.library.d.b.a();
    File localFile1 = com.dewmobile.transfer.api.a.a(Environment.getExternalStorageDirectory() + "/Android/data/com.smile.gifmaker/cache/.cache");
    File localFile2 = com.dewmobile.transfer.api.a.a(Environment.getExternalStorageDirectory() + "/Ingkee/shortVideo");
    if (localFile1.exists()) {
      d(paramList, localFile1, 0, paramDmFileCategory);
    }
    if (localFile2.exists()) {
      d(paramList, localFile2, 0, paramDmFileCategory);
    }
  }
  
  private static void b(ArrayList<FileItem> paramArrayList)
  {
    com.dewmobile.library.file.b.b localB = com.dewmobile.library.file.b.b.a();
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      FileItem localFileItem = (FileItem)localIterator.next();
      if (TextUtils.isEmpty(localFileItem.e))
      {
        localFileItem.s = "[";
      }
      else
      {
        String str = k(((String)localB.a(localFileItem.e).values().iterator().next()).trim().toUpperCase());
        if (!TextUtils.isEmpty(str))
        {
          char c1 = str.charAt(0);
          if ((c1 >= 'A') && (c1 <= 'Z')) {
            localFileItem.s = String.valueOf(c1);
          } else {
            localFileItem.s = "[";
          }
        }
        else
        {
          localFileItem.s = "[";
        }
      }
    }
    Collections.sort(paramArrayList, new Comparator()
    {
      public int a(FileItem paramAnonymousFileItem1, FileItem paramAnonymousFileItem2)
      {
        return paramAnonymousFileItem1.s.compareTo(paramAnonymousFileItem2.s);
      }
    });
  }
  
  public static void b(List<FileGroupItem> paramList, File paramFile, int paramInt, DmFileCategory paramDmFileCategory)
  {
    Object localObject;
    int j;
    int i;
    do
    {
      try
      {
        String[] arrayOfString = paramFile.list();
        if ((arrayOfString == null) || (arrayOfString.length == 0)) {
          return;
        }
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        for (;;)
        {
          DmLog.e("xh", "OutOfMemoryError:" + localOutOfMemoryError);
          localObject = null;
        }
        j = localObject.length;
        i = 0;
      }
    } while (i >= j);
    String str = localObject[i];
    if (str.startsWith(".")) {}
    for (;;)
    {
      i += 1;
      break;
      File localFile = com.dewmobile.transfer.api.a.a(paramFile, str);
      if ((localFile.isFile()) && (localFile.length() > 1024L))
      {
        str = str.toLowerCase();
        FileItem localFileItem = a(localFile, paramDmFileCategory);
        if (localFileItem != null) {
          if (g(str))
          {
            localFileItem.b = 0;
            ((FileGroupItem)paramList.get(0)).b.add(localFileItem);
          }
          else if (c(str))
          {
            localFileItem.b = 1;
            localFileItem.e = localFile.getName();
            ((FileGroupItem)paramList.get(1)).b.add(localFileItem);
          }
          else if (e(str))
          {
            localFileItem.b = 2;
            ((FileGroupItem)paramList.get(2)).b.add(localFileItem);
          }
          else if (f(str))
          {
            localFileItem.b = 3;
            ((FileGroupItem)paramList.get(3)).b.add(localFileItem);
          }
          else if (d(str))
          {
            localFileItem.b = 4;
            ((FileGroupItem)paramList.get(4)).b.add(localFileItem);
          }
          else if ((localFileItem.e != null) && (localFileItem.e.contains(".")) && (!m(localFileItem.e)) && (!com.dewmobile.library.file.b.a.a(com.dewmobile.library.d.b.a).a(paramDmFileCategory)))
          {
            com.dewmobile.library.file.b.a.a(com.dewmobile.library.d.b.a).a(localFileItem);
          }
        }
      }
      else if ((localFile.isDirectory()) && (paramInt < 10) && (!c(localFile)))
      {
        b(paramList, localFile, paramInt + 1, paramDmFileCategory);
      }
    }
  }
  
  public static LocalFileResult c(Context paramContext, DmFileCategory paramDmFileCategory)
  {
    Object localObject2 = d.a();
    String str = a(paramDmFileCategory);
    Object localObject1;
    if (Build.VERSION.SDK_INT >= 16)
    {
      localObject1 = new String[9];
      localObject1[0] = "_id";
      localObject1[1] = "_display_name";
      localObject1[2] = "date_modified";
      localObject1[3] = "_size";
      localObject1[4] = "title";
      localObject1[5] = "_data";
      localObject1[6] = "date_added";
      localObject1[7] = "width";
      localObject1[8] = "height";
    }
    Object localObject3;
    while (paramDmFileCategory.n())
    {
      localObject3 = new String[localObject2.length + 1];
      localObject3[0] = "10240";
      int i = 1;
      for (;;)
      {
        if (i < localObject3.length)
        {
          localObject3[i] = localObject2[(i - 1)];
          i += 1;
          continue;
          localObject1 = new String[7];
          localObject1[0] = "_id";
          localObject1[1] = "_display_name";
          localObject1[2] = "date_modified";
          localObject1[3] = "_size";
          localObject1[4] = "title";
          localObject1[5] = "_data";
          localObject1[6] = "date_added";
          break;
        }
      }
      localObject1 = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[])localObject1, "_size > ? AND ", (String[])localObject3, str);
      if (localObject1 != null) {
        break label272;
      }
      DmLog.e(f, "No IMAGE found in data base!");
      paramDmFileCategory = null;
    }
    label272:
    do
    {
      return paramDmFileCategory;
      localObject3 = "bucket_id IN (" + a(localObject2.length) + ")";
      localObject1 = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[])localObject1, (String)localObject3, (String[])localObject2, str);
      break;
      localObject2 = new ArrayList();
      paramDmFileCategory = a(paramContext, (ArrayList)localObject2, paramDmFileCategory, (Cursor)localObject1);
      ((Cursor)localObject1).close();
      localObject1 = new LocalFileResult();
      ((LocalFileResult)localObject1).a = ((ArrayList)localObject2);
      ((LocalFileResult)localObject1).b = paramDmFileCategory;
      paramDmFileCategory = (DmFileCategory)localObject1;
    } while (!com.dewmobile.library.backend.g.a(paramContext, 1));
    com.dewmobile.library.backend.g.a().a((List)localObject2, 1);
    return localObject1;
  }
  
  private static void c(ArrayList<FileItem> paramArrayList)
  {
    if ((paramArrayList == null) || (paramArrayList.size() == 0)) {}
    for (;;)
    {
      return;
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        FileItem localFileItem = (FileItem)paramArrayList.next();
        if ((!t.a(localFileItem.z)) && (localFileItem.z.contains("/sdcard/Android/data/com.smile.gifmaker/"))) {
          paramArrayList.remove();
        }
      }
    }
  }
  
  public static void c(List<FileGroupItem> paramList, File paramFile, int paramInt, DmFileCategory paramDmFileCategory)
  {
    String[] arrayOfString = paramFile.list();
    if ((arrayOfString == null) || (arrayOfString.length == 0)) {
      return;
    }
    int i = arrayOfString.length;
    paramInt = 0;
    label25:
    Object localObject;
    if (paramInt < i)
    {
      localObject = arrayOfString[paramInt];
      if (!((String)localObject).startsWith(".")) {
        break label55;
      }
    }
    for (;;)
    {
      paramInt += 1;
      break label25;
      break;
      label55:
      localObject = com.dewmobile.transfer.api.a.a(paramFile, (String)localObject);
      if ((((File)localObject).isFile()) && (((File)localObject).length() > 1024L))
      {
        localObject = a((File)localObject, paramDmFileCategory);
        ((FileItem)localObject).b = 0;
        ((FileGroupItem)paramList.get(0)).b.add(localObject);
      }
    }
  }
  
  private static boolean c(File paramFile)
  {
    paramFile = paramFile.getPath().toLowerCase();
    return ((paramFile.contains("cache")) && (!paramFile.contains("video"))) || (paramFile.contains("image")) || (paramFile.contains("/android/data")) || (paramFile.contains("gameloft")) || (paramFile.contains("img")) || (paramFile.contains("org.vv.menu.fish")) || (paramFile.contains("error")) || (paramFile.endsWith("log")) || (paramFile.endsWith("avatar")) || (paramFile.contains("avater")) || (paramFile.contains("sina/weibo")) || (paramFile.contains("/tencent/micromsg")) || (paramFile.contains("/tencent/com/tencent")) || (paramFile.contains("/tencent/mobileqq")) || (paramFile.contains("/tencentnews/data")) || (paramFile.contains("/sina/news")) || (paramFile.contains("/baidu_music/lyric")) || (paramFile.contains("/meilishuo")) || (paramFile.contains("/cctvnews")) || ((paramFile.contains("tmp")) && (paramFile.contains("pic"))) || (paramFile.contains("/tuniu/tuniupic")) || ((paramFile.contains("/baiduwenku/download/")) && (!paramFile.contains("original"))) || (paramFile.contains("com.sohu.sohuvideo/home")) || (paramFile.contains("com.sohu.sohuvideo/firstpage")) || (paramFile.contains("youku/offlinedata")) || (l(paramFile));
  }
  
  public static boolean c(String paramString)
  {
    return paramString.endsWith(".apk");
  }
  
  public static LocalFileResult d(Context paramContext, DmFileCategory paramDmFileCategory)
  {
    int i = com.dewmobile.library.g.b.a().a("audio_sort", 0);
    Object localObject = a(paramDmFileCategory, i);
    localObject = paramContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[] { "_id", "_display_name", "date_modified", "_size", "title", "_data", "album", "album_id", "artist", "duration" }, "_size > ?", new String[] { "102400" }, (String)localObject);
    if (localObject == null)
    {
      DmLog.e(f, "No AUDIO found in data base!");
      paramDmFileCategory = null;
    }
    ArrayList localArrayList;
    do
    {
      return paramDmFileCategory;
      localArrayList = new ArrayList();
      FileCategorySorter localFileCategorySorter = a(paramContext, localArrayList, paramDmFileCategory, (Cursor)localObject, i, false);
      ((Cursor)localObject).close();
      localObject = new LocalFileResult();
      ((LocalFileResult)localObject).a = localArrayList;
      ((LocalFileResult)localObject).b = localFileCategorySorter;
      a(paramContext, paramDmFileCategory, localArrayList);
      paramDmFileCategory = (DmFileCategory)localObject;
    } while (!com.dewmobile.library.backend.g.a(paramContext, 2));
    com.dewmobile.library.backend.g.a().a(localArrayList, 2);
    return localObject;
  }
  
  public static void d(List<FileGroupItem> paramList, File paramFile, int paramInt, DmFileCategory paramDmFileCategory)
  {
    String[] arrayOfString = paramFile.list();
    if ((arrayOfString == null) || (arrayOfString.length == 0)) {
      return;
    }
    int i = arrayOfString.length;
    paramInt = 0;
    label25:
    Object localObject;
    if (paramInt < i)
    {
      localObject = arrayOfString[paramInt];
      if ((!((String)localObject).startsWith(".")) && (((String)localObject).endsWith(".mp4"))) {
        break label66;
      }
    }
    for (;;)
    {
      paramInt += 1;
      break label25;
      break;
      label66:
      localObject = com.dewmobile.transfer.api.a.a(paramFile, (String)localObject);
      if ((((File)localObject).isFile()) && (((File)localObject).length() > 1024L))
      {
        localObject = a((File)localObject, paramDmFileCategory);
        ((FileItem)localObject).f = null;
        ((FileItem)localObject).b = 0;
        ((FileGroupItem)paramList.get(0)).b.add(localObject);
      }
    }
  }
  
  public static boolean d(String paramString)
  {
    return (paramString.endsWith(".zip")) || (paramString.endsWith(".tar")) || (paramString.endsWith(".rar")) || (paramString.endsWith(".iso")) || (paramString.endsWith(".7z"));
  }
  
  public static LocalFileResult e(Context paramContext, DmFileCategory paramDmFileCategory)
  {
    paramContext = paramContext.getContentResolver();
    Object localObject = a(paramContext);
    if (localObject == null) {}
    Cursor localCursor;
    do
    {
      return null;
      localCursor = paramContext.query(ContactsContract.Contacts.CONTENT_URI, new String[] { "_id", "display_name", "has_phone_number" }, null, null, null);
    } while (localCursor == null);
    int i = localCursor.getColumnIndex("has_phone_number");
    int j = localCursor.getColumnIndex("display_name");
    int k = localCursor.getColumnIndex("_id");
    paramContext = new ArrayList();
    while (localCursor.moveToNext()) {
      if (localCursor.getInt(i) == 1)
      {
        String str = localCursor.getString(k);
        if (((HashMap)localObject).get(str) != null)
        {
          FileItem localFileItem = new FileItem(paramDmFileCategory);
          localFileItem.f = str;
          localFileItem.e = localCursor.getString(j);
          localFileItem.r = ((String)((HashMap)localObject).get(str));
          localFileItem.z = localFileItem.f;
          localFileItem.h = -1L;
          paramContext.add(localFileItem);
        }
      }
    }
    localCursor.close();
    a(paramContext);
    paramDmFileCategory = new ContactSorter();
    i = 0;
    while (i < paramContext.size())
    {
      a(paramDmFileCategory, null, (FileItem)paramContext.get(i), null);
      i += 1;
    }
    localObject = new LocalFileResult();
    ((LocalFileResult)localObject).a = paramContext;
    ((LocalFileResult)localObject).b = paramDmFileCategory;
    paramDmFileCategory.d();
    return localObject;
  }
  
  public static void e(List<FileGroupItem> paramList, File paramFile, int paramInt, DmFileCategory paramDmFileCategory)
  {
    String[] arrayOfString = paramFile.list();
    if ((arrayOfString == null) || (arrayOfString.length == 0)) {
      return;
    }
    paramDmFileCategory.b = 0;
    int m = arrayOfString.length;
    int i = 0;
    label31:
    Object localObject1;
    File localFile;
    if (i < m)
    {
      localObject1 = arrayOfString[i];
      localFile = com.dewmobile.transfer.api.a.a(paramFile, (String)localObject1);
      if (localFile.isDirectory())
      {
        localObject1 = localFile.list();
        if (localObject1 != null)
        {
          int k = 1;
          int n = localObject1.length;
          int j = 0;
          paramInt = k;
          Object localObject2;
          if (j < n)
          {
            localObject2 = localObject1[j];
            if (((String)localObject2).endsWith("dm")) {
              paramInt = 0;
            }
          }
          else
          {
            label114:
            if (paramInt == 0) {
              break label242;
            }
            j = localObject1.length;
            paramInt = 0;
            label125:
            if (paramInt >= j) {
              break label242;
            }
            localObject2 = com.dewmobile.transfer.api.a.a(localFile, localObject1[paramInt]);
            if (!((File)localObject2).isDirectory()) {
              break label198;
            }
          }
          for (;;)
          {
            paramInt += 1;
            break label125;
            if ("list.txt".equals(localObject2))
            {
              paramInt = 0;
              break label114;
            }
            if (".notmerge".equals(localObject2))
            {
              paramInt = 0;
              break label114;
            }
            j += 1;
            break;
            label198:
            localObject2 = a((File)localObject2, paramDmFileCategory);
            ((FileItem)localObject2).b = 5;
            ((FileGroupItem)paramList.get(5)).b.add(localObject2);
          }
        }
      }
      else
      {
        if (g((String)localObject1)) {
          break label251;
        }
      }
    }
    for (;;)
    {
      label242:
      i += 1;
      break label31;
      break;
      label251:
      localObject1 = a(localFile, paramDmFileCategory);
      if (localFile.isDirectory()) {
        ((FileItem)localObject1).h = b(localFile);
      }
      ((FileItem)localObject1).b = 5;
      ((FileGroupItem)paramList.get(5)).b.add(localObject1);
    }
  }
  
  public static boolean e(String paramString)
  {
    return (paramString.endsWith(".doc")) || (paramString.endsWith(".xlsx")) || (paramString.endsWith(".ppt")) || (paramString.endsWith(".wps")) || (paramString.endsWith(".docx")) || (paramString.endsWith(".xls"));
  }
  
  public static LocalFileResult f(Context paramContext, DmFileCategory paramDmFileCategory)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    List localList1 = localPackageManager.getInstalledPackages(128);
    if (localList1 == null)
    {
      paramDmFileCategory = null;
      return paramDmFileCategory;
    }
    c.clear();
    HashSet localHashSet1 = new HashSet();
    ArrayList localArrayList1 = new ArrayList();
    String str = paramContext.getPackageName();
    Object localObject1 = null;
    boolean bool = com.dewmobile.library.g.b.a().r();
    List localList2 = j.a(paramContext).a();
    Object localObject2 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    if (com.dewmobile.library.d.a.b) {
      localObject2 = com.dewmobile.library.j.f.e().d();
    }
    for (;;)
    {
      Object localObject3 = com.dewmobile.library.d.b.a.getSharedPreferences("z_wblist", 0);
      SharedPreferences localSharedPreferences = com.dewmobile.library.d.b.a.getSharedPreferences("linked_active", 0);
      Object localObject6 = localSharedPreferences.getString("array", null);
      HashMap localHashMap1 = new HashMap();
      int i;
      if (!TextUtils.isEmpty((CharSequence)localObject6)) {
        try
        {
          localObject6 = new JSONArray((String)localObject6);
          i = 0;
          while (i < ((JSONArray)localObject6).length())
          {
            localObject7 = ((JSONArray)localObject6).getJSONObject(i);
            if (!TextUtils.isEmpty(((JSONObject)localObject7).optString("pkg"))) {
              localHashMap1.put(((JSONObject)localObject7).optString("pkg"), localObject7);
            }
            i += 1;
          }
          localObject8 = ((SharedPreferences)localObject3).getString("blackarray", "");
        }
        catch (JSONException localJSONException2)
        {
          localJSONException2.printStackTrace();
        }
      }
      Object localObject8;
      localObject3 = ((SharedPreferences)localObject3).getString("whiarray", "");
      Object localObject7 = new HashSet();
      HashSet localHashSet2 = new HashSet();
      if (!TextUtils.isEmpty((CharSequence)localObject8)) {
        try
        {
          localObject8 = new JSONArray((String)localObject8);
          i = 0;
          while (i < ((JSONArray)localObject8).length())
          {
            ((HashSet)localObject7).add(((JSONArray)localObject8).getString(i));
            i += 1;
          }
          if (TextUtils.isEmpty((CharSequence)localObject3)) {
            break label387;
          }
        }
        catch (Exception localException2) {}
      }
      try
      {
        localObject3 = new JSONArray((String)localObject3);
        i = 0;
        while (i < ((JSONArray)localObject3).length())
        {
          localHashSet2.add(((JSONArray)localObject3).getString(i));
          i += 1;
        }
        localArrayList3 = new ArrayList();
      }
      catch (Exception localException1) {}
      label387:
      ArrayList localArrayList3;
      List localList3 = com.dewmobile.library.j.f.f().d();
      Object localObject4 = m.b("dm_money_main_data", null);
      HashMap localHashMap2 = new HashMap();
      int j = com.dewmobile.library.g.b.a().a("dm_money_open", 0);
      Object localObject9;
      if (localObject4 != null) {
        try
        {
          localObject4 = new JSONObject((String)localObject4).optJSONArray("materials");
          i = 0;
          while (i < ((JSONArray)localObject4).length())
          {
            localObject9 = ((JSONArray)localObject4).getJSONObject(i);
            localHashMap2.put(((JSONObject)localObject9).getString("pkg"), localObject9);
            i += 1;
          }
          i = 0;
        }
        catch (JSONException localJSONException1)
        {
          localJSONException1.printStackTrace();
        }
      }
      Object localObject5;
      File localFile;
      for (;;)
      {
        if (i < localList1.size())
        {
          localObject5 = (PackageInfo)localList1.get(i);
          localObject9 = ((PackageInfo)localObject5).applicationInfo;
          if ((localObject9 != null) && (((ApplicationInfo)localObject9).sourceDir != null) && (((((ApplicationInfo)localObject9).flags & 0x1) == 0) || ((((ApplicationInfo)localObject9).flags & 0x80) != 0)))
          {
            if (((ApplicationInfo)localObject9).packageName.equals(str))
            {
              localObject1 = localObject5;
              i += 1;
              continue;
            }
            localFile = com.dewmobile.transfer.api.a.a(((ApplicationInfo)localObject9).sourceDir);
            if ((localFile.canRead()) && (!localHashSet1.contains(((ApplicationInfo)localObject9).packageName)) && (!((ApplicationInfo)localObject9).packageName.equals("com.ud.intellegentschoolsystem")) && (!((ApplicationInfo)localObject9).packageName.equals("com.up.textcasterpro")) && (!((HashSet)localObject7).contains(((ApplicationInfo)localObject9).packageName)))
            {
              localObject5 = a((PackageInfo)localObject5, paramDmFileCategory, localPackageManager);
              if (localObject5 != null)
              {
                if (localList3.contains(localObject5)) {
                  ((FileItem)localObject5).y = ((FileItem)localList3.get(localList3.indexOf(localObject5))).y;
                }
                if ((localHashMap1.size() > 0) && (localHashMap1.get(((FileItem)localObject5).u) != null) && (!DateUtils.isToday(localSharedPreferences.getLong(((FileItem)localObject5).u, 0L)))) {
                  ((FileItem)localObject5).M = true;
                }
              }
            }
          }
        }
      }
      try
      {
        ((FileItem)localObject5).N = ((JSONObject)localHashMap1.get(((FileItem)localObject5).u)).optString("scm");
        ((FileItem)localObject5).h = localFile.length();
        ((FileItem)localObject5).E = Formatter.formatFileSize(com.dewmobile.library.d.b.a(), ((FileItem)localObject5).h);
        ((FileItem)localObject5).i = localFile.lastModified();
        ((FileItem)localObject5).b = 0;
        if (localList2.contains(((FileItem)localObject5).f))
        {
          DmLog.d("yy", "app hideItems title:" + ((FileItem)localObject5).e);
          if (bool) {
            ((FileItem)localObject5).C = true;
          }
        }
        else
        {
          if ((j != 1) || (!a(localHashMap2, (ApplicationInfo)localObject9, (FileItem)localObject5))) {
            break label946;
          }
          localArrayList2.add(localObject5);
          c.add(localObject5);
        }
        for (;;)
        {
          localHashSet1.add(((ApplicationInfo)localObject9).packageName);
          break;
          label946:
          if (!a((List)localObject2, localArrayList3, (ApplicationInfo)localObject9, (FileItem)localObject5)) {
            localArrayList1.add(localObject5);
          }
        }
        localObject5 = new GameSorter();
        a(paramDmFileCategory, localArrayList1, localPackageManager, localHashSet2, localHashMap1.keySet());
        a(paramDmFileCategory, localArrayList3, localPackageManager, localHashSet2, localHashMap1.keySet());
        localArrayList1.addAll(0, localArrayList3);
        localArrayList1.addAll(0, localArrayList2);
        localObject2 = new BigDecimal(0);
        if (j == 1)
        {
          i = 0;
          while (i < localArrayList2.size())
          {
            localObject2 = ((BigDecimal)localObject2).add(new BigDecimal(((FileItem)localArrayList2.get(i)).A));
            i += 1;
          }
          com.dewmobile.library.g.b.a().b("dm_money_total", ((BigDecimal)localObject2).toString());
        }
        if ((localObject1 != null) && (((PackageInfo)localObject1).applicationInfo.sourceDir != null))
        {
          localObject2 = a((PackageInfo)localObject1, paramDmFileCategory, localPackageManager);
          localObject1 = com.dewmobile.transfer.api.a.a(((PackageInfo)localObject1).applicationInfo.sourceDir);
          ((FileItem)localObject2).h = ((File)localObject1).length();
          ((FileItem)localObject2).i = ((File)localObject1).lastModified();
          localArrayList1.add(0, localObject2);
          localObject1 = localObject2;
          localObject2 = new FileGroup();
          ((FileGroup)localObject2).d = ((FileItem)localArrayList1.get(0));
          ((FileGroup)localObject2).f = "local_app";
          ((FileGroup)localObject2).e = localArrayList1.size();
          ((FileCategorySorter)localObject5).a(localObject2);
          localObject2 = new LocalFileResult();
          ((LocalFileResult)localObject2).a = localArrayList1;
          ((LocalFileResult)localObject2).b = ((FileCategorySorter)localObject5);
          com.dewmobile.library.h.c.c().b(localArrayList1);
          localObject5 = new ArrayList();
          ((ArrayList)localObject5).addAll(com.dewmobile.library.j.f.f().e());
          ((ArrayList)localObject5).addAll(localArrayList1);
          a(paramContext, paramDmFileCategory, (ArrayList)localObject5);
          com.dewmobile.library.file.b.a.a(paramContext.getApplicationContext()).d();
          if (localObject1 != null) {
            ((ArrayList)localObject5).remove(localObject1);
          }
          com.dewmobile.library.backend.h.a(paramContext).a((List)localObject5);
          paramDmFileCategory = (DmFileCategory)localObject2;
          if (!com.dewmobile.library.backend.g.a(paramContext, 0)) {
            break;
          }
          com.dewmobile.library.backend.g.a().a((List)localObject5, 1);
          return localObject2;
        }
      }
      catch (Exception localException3)
      {
        for (;;)
        {
          continue;
          localObject1 = null;
        }
      }
    }
  }
  
  public static boolean f(String paramString)
  {
    return (paramString.endsWith(".umd")) || (paramString.endsWith(".pdf")) || (paramString.endsWith(".ebk")) || (paramString.endsWith(".chm")) || ((paramString.endsWith(".txt")) && (!paramString.contains("log")));
  }
  
  public static com.dewmobile.library.file.a.f g(Context paramContext, DmFileCategory paramDmFileCategory)
  {
    com.dewmobile.library.file.a.b localB = null;
    if (paramDmFileCategory.g()) {
      localB = new com.dewmobile.library.file.a.b(paramDmFileCategory);
    }
    do
    {
      return localB;
      if ((paramDmFileCategory.c()) || (paramDmFileCategory.d()) || (paramDmFileCategory.b())) {
        return new com.dewmobile.library.file.a.c(paramContext, paramDmFileCategory);
      }
      if (paramDmFileCategory.e()) {
        return new com.dewmobile.library.file.a.g(paramContext, paramDmFileCategory);
      }
    } while (!paramDmFileCategory.f());
    return new com.dewmobile.library.file.a.a(paramContext, paramDmFileCategory);
  }
  
  public static boolean g(String paramString)
  {
    return (paramString.endsWith(".rmvb")) || (paramString.endsWith(".rm")) || (paramString.endsWith(".flv")) || (paramString.endsWith(".mp4"));
  }
  
  public static LocalFileResult h(Context paramContext, DmFileCategory paramDmFileCategory)
  {
    LocalFileResult localLocalFileResult = new LocalFileResult();
    Object localObject1 = paramContext.getContentResolver();
    Object localObject3 = d.a();
    Object localObject2 = new ArrayList();
    int j = localObject3.length;
    int i = 0;
    while (i < j)
    {
      ((List)localObject2).add(localObject3[i]);
      i += 1;
    }
    localObject3 = ((ContentResolver)localObject1).query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { "bucket_display_name", "bucket_id", "_data", "COUNT(bucket_id)" }, "_size > ? ) GROUP BY (bucket_id", new String[] { "10240" }, null);
    localObject1 = new GallerySorter();
    if (localObject3 == null) {
      return null;
    }
    Object localObject4 = new ArrayList();
    i = ((Cursor)localObject3).getColumnIndex("bucket_display_name");
    j = ((Cursor)localObject3).getColumnIndex("_data");
    int k = ((Cursor)localObject3).getColumnIndex("bucket_id");
    int m = ((Cursor)localObject3).getColumnIndex("COUNT(bucket_id)");
    while (((Cursor)localObject3).moveToNext())
    {
      localObject5 = new FileGroup();
      ((FileGroup)localObject5).f = ((Cursor)localObject3).getString(i);
      ((FileGroup)localObject5).b = ((Cursor)localObject3).getString(j);
      ((FileGroup)localObject5).j = ((Cursor)localObject3).getString(k);
      ((FileGroup)localObject5).e = ((Cursor)localObject3).getInt(m);
      ((FileGroup)localObject5).k = ((FileGroup)localObject5).e;
      if (((FileGroup)localObject5).e != 0) {
        if (((FileGroup)localObject5).b.contains("/zapya/photo")) {
          ((List)localObject4).add(localObject5);
        } else if (!((List)localObject2).contains(((FileGroup)localObject5).j)) {
          ((FileCategorySorter)localObject1).a(localObject5);
        } else {
          DmLog.d(f, "exclude:" + ((FileGroup)localObject5).j);
        }
      }
    }
    ((Cursor)localObject3).close();
    ((FileCategorySorter)localObject1).a(new Comparator()
    {
      public int a(FileGroup paramAnonymousFileGroup1, FileGroup paramAnonymousFileGroup2)
      {
        return (int)(paramAnonymousFileGroup2.k - paramAnonymousFileGroup1.k);
      }
    });
    if (((List)localObject4).size() > 0)
    {
      localObject2 = ((List)localObject4).iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((FileCategorySorter)localObject1).a((FileGroup)((Iterator)localObject2).next(), 0);
      }
    }
    localObject2 = new ArrayList();
    localObject3 = c(paramContext, new DmFileCategory(4, 0));
    if ((((LocalFileResult)localObject3).a != null) && (((LocalFileResult)localObject3).a.size() > 0))
    {
      localObject4 = new FileGroup();
      ((FileGroup)localObject4).e = ((LocalFileResult)localObject3).a.size();
      ((FileGroup)localObject4).d = ((FileItem)((LocalFileResult)localObject3).a.get(0));
      ((FileGroup)localObject4).b = "/zapya_camera";
      ((FileGroup)localObject4).f = "/zapya_camera";
      ((FileCategorySorter)localObject1).a(localObject4, 0);
      ((ArrayList)localObject2).addAll(((LocalFileResult)localObject3).a);
    }
    k.a(paramContext, paramDmFileCategory, (FileCategorySorter)localObject1);
    k.a(paramContext, (FileCategorySorter)localObject1);
    localObject3 = a(paramDmFileCategory);
    localObject4 = new ArrayList();
    i = ((ArrayList)localObject2).size();
    Object localObject5 = ((FileCategorySorter)localObject1).h().iterator();
    while (((Iterator)localObject5).hasNext())
    {
      FileGroup localFileGroup = (FileGroup)((Iterator)localObject5).next();
      if (TextUtils.isEmpty(localFileGroup.j))
      {
        k.a(paramContext, paramDmFileCategory, localFileGroup.b, (FileCategorySorter)localObject1, (List)localObject2);
        i = ((ArrayList)localObject2).size();
      }
      else
      {
        Object localObject6 = "_size > ? AND bucket_id=" + localFileGroup.j;
        localObject6 = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { "_id", "_display_name", "date_modified", "_size", "title", "_data", "date_added" }, (String)localObject6, new String[] { "10240" }, (String)localObject3);
        if ((localObject6 == null) || (((Cursor)localObject6).getCount() == 0))
        {
          if (localObject6 != null) {
            ((Cursor)localObject6).close();
          }
          ((List)localObject4).add(localFileGroup);
        }
        else
        {
          localFileGroup.d = b(paramContext, (ArrayList)localObject2, paramDmFileCategory, (Cursor)localObject6);
          localFileGroup.e = (((ArrayList)localObject2).size() - i);
          i = ((ArrayList)localObject2).size();
          if (localFileGroup.e == 0) {
            ((List)localObject4).add(localFileGroup);
          }
          ((Cursor)localObject6).close();
        }
      }
    }
    ((FileCategorySorter)localObject1).a((Collection)localObject4);
    ((FileCategorySorter)localObject1).d();
    localLocalFileResult.a = ((ArrayList)localObject2);
    localLocalFileResult.b = ((FileCategorySorter)localObject1);
    return localLocalFileResult;
  }
  
  public static boolean h(String paramString)
  {
    return Charset.forName("GB2312").newEncoder().canEncode(paramString);
  }
  
  private static LocalFileResult i(Context paramContext, DmFileCategory paramDmFileCategory)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      localObject = new String[9];
      localObject[0] = "_id";
      localObject[1] = "_display_name";
      localObject[2] = "date_modified";
      localObject[3] = "_size";
      localObject[4] = "title";
      localObject[5] = "_data";
      localObject[6] = "date_added";
      localObject[7] = "width";
      localObject[8] = "height";
    }
    Cursor localCursor;
    for (;;)
    {
      localCursor = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[])localObject, "_size > 10240", null, "date_modified DESC");
      if (localCursor != null) {
        break;
      }
      DmLog.e(f, "No IMAGE found in data base!");
      return null;
      localObject = new String[7];
      localObject[0] = "_id";
      localObject[1] = "_display_name";
      localObject[2] = "date_modified";
      localObject[3] = "_size";
      localObject[4] = "title";
      localObject[5] = "_data";
      localObject[6] = "date_added";
    }
    Object localObject = new ArrayList();
    paramContext = a(paramContext, (ArrayList)localObject, paramDmFileCategory, localCursor);
    localCursor.close();
    paramDmFileCategory = new LocalFileResult();
    paramDmFileCategory.a = ((ArrayList)localObject);
    paramDmFileCategory.b = paramContext;
    return paramDmFileCategory;
  }
  
  public static boolean i(String paramString)
  {
    return Charset.forName("ISO-8859-1").newEncoder().canEncode(paramString);
  }
  
  private static FileCategorySorter j(Context paramContext, DmFileCategory paramDmFileCategory)
  {
    paramContext = null;
    if (paramDmFileCategory.c()) {
      paramContext = new CameraSorter();
    }
    do
    {
      return paramContext;
      if (paramDmFileCategory.n()) {
        return new GallerySorter();
      }
      if (paramDmFileCategory.d()) {
        return new AudioSorter();
      }
    } while (!paramDmFileCategory.e());
    return new VideoSorter();
  }
  
  public static String j(String paramString)
  {
    try
    {
      String str = new String(paramString.getBytes("ISO-8859-1"), "gb2312");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return paramString;
  }
  
  private static String k(String paramString)
  {
    return paramString.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&amp;*（）——+|{}《》【】‘；：”“’。，、？|-]", "");
  }
  
  private static boolean l(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!TextUtils.isEmpty(paramString))
    {
      bool1 = bool2;
      if (paramString.contains("/omnivideo/download"))
      {
        bool1 = bool2;
        if (!paramString.substring(0, paramString.lastIndexOf("/")).endsWith("/omnivideo/download")) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  private static boolean m(String paramString)
  {
    return (paramString.endsWith(".jpg")) || (paramString.endsWith(".png")) || (paramString.endsWith(".JPG")) || (paramString.endsWith("PNG")) || (paramString.endsWith(".dm")) || (paramString.endsWith(".JPEG")) || (paramString.endsWith(".jpeg"));
  }
  
  public static class FileGroupItem
    implements Serializable
  {
    private static final long serialVersionUID = -6414183457095551068L;
    public String a;
    public ArrayList<FileItem> b = new ArrayList();
    
    public FileGroupItem() {}
    
    public FileGroupItem(String paramString)
    {
      this.a = paramString;
    }
    
    public String toString()
    {
      return this.a;
    }
  }
  
  public static class LocalFileResult
    implements Serializable
  {
    private static final long serialVersionUID = -5484444038985673282L;
    public ArrayList<FileItem> a;
    public FileCategorySorter b;
    public List<DmLocalFileManager.FileGroupItem> c;
    
    public LocalFileResult() {}
  }
}
