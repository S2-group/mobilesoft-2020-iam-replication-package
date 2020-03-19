package gogolook.callgogolook2.developmode;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Looper;
import android.util.Base64;
import com.gogolook.whoscallsdk.core.b;
import com.gogolook.whoscallsdk.core.d;
import com.gogolook.whoscallsdk.core.e;
import gogolook.callgogolook2.MyApplication;
import gogolook.callgogolook2.provider.a.ac;
import gogolook.callgogolook2.util.OJni;
import gogolook.callgogolook2.util.ab;
import gogolook.callgogolook2.util.ag;
import gogolook.callgogolook2.util.ai;
import gogolook.callgogolook2.util.p;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LogManager
{
  public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
  
  public LogManager() {}
  
  private static long a(File paramFile, PrintStream paramPrintStream)
  {
    for (;;)
    {
      int i;
      long l2;
      try
      {
        if (paramFile.exists())
        {
          paramPrintStream.println(paramFile.getPath());
          File[] arrayOfFile = paramFile.listFiles();
          i = 0;
          long l1 = 0L;
          l2 = l1;
          if (i >= arrayOfFile.length) {
            break label139;
          }
          if (arrayOfFile[i].isDirectory())
          {
            l1 += a(arrayOfFile[i], paramPrintStream);
          }
          else
          {
            paramPrintStream.println(arrayOfFile[i].length() / 1000L + "KB " + paramFile.getPath() + "/" + arrayOfFile[i].getName());
            l2 = arrayOfFile[i].length();
            l1 += l2;
          }
        }
      }
      catch (Exception paramFile)
      {
        paramFile.printStackTrace();
        l2 = 0L;
      }
      label139:
      return l2;
      i += 1;
    }
  }
  
  private static File a(Context paramContext, String paramString)
    throws IOException, ParseException
  {
    File localFile1 = new File(ab.h(paramContext) + "/user_report/");
    if (localFile1.exists()) {
      a(localFile1);
    }
    localFile1.mkdir();
    File localFile2 = new File(localFile1 + paramString);
    File localFile3 = new File(localFile1 + "/" + ab.l() + ".db");
    File localFile4 = new File(localFile1 + "/" + ab.l() + "_sdk.db");
    File localFile5 = new File(localFile1 + "/" + ab.l() + ".txt");
    File localFile6 = new File(localFile1 + "/" + ab.l() + "_apps.txt");
    File localFile7 = new File(localFile1 + "/" + ab.l() + "_disk.txt");
    a(paramContext.getDatabasePath("whoscall.db"), localFile3);
    Object localObject1 = b.a();
    Object localObject2;
    Object localObject3;
    Object localObject4;
    StringBuilder localStringBuilder;
    if (((b)localObject1).a != null)
    {
      localObject2 = ((b)localObject1).a;
      e.a(((b)localObject1).a);
      a(((Context)localObject2).getDatabasePath(e.a()), localFile4);
      localObject3 = p.d().getAll();
      localFile5.createNewFile();
      localObject1 = new FileOutputStream(localFile5);
      localObject2 = new PrintStream((OutputStream)localObject1, false, "UTF-8");
      localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("CountryCode : " + ai.a());
      ((PrintStream)localObject2).println(((StringBuilder)localObject4).toString());
      localObject3 = ((Map)localObject3).entrySet().iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (Map.Entry)((Iterator)localObject3).next();
        localStringBuilder = new StringBuilder();
        localStringBuilder.append((String)((Map.Entry)localObject4).getKey());
        localStringBuilder.append(" : ");
        localStringBuilder.append(((Map.Entry)localObject4).getValue());
        ((PrintStream)localObject2).println(localStringBuilder.toString());
      }
    }
    throw new d("whoscallSDK context = null, please init it first");
    ((PrintStream)localObject2).close();
    ((FileOutputStream)localObject1).close();
    localFile6.createNewFile();
    try
    {
      localObject1 = new FileOutputStream(localFile6);
      localObject2 = new PrintStream((OutputStream)localObject1, false, "UTF-8");
      localObject3 = paramContext.getPackageManager();
      new Intent("android.intent.action.MAIN", null).addCategory("android.intent.category.LAUNCHER");
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext())
      {
        localObject4 = (PackageInfo)paramContext.next();
        if ((((PackageInfo)localObject4).applicationInfo.flags & 0x1) == 0)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(((PackageInfo)localObject4).applicationInfo.loadLabel((PackageManager)localObject3) + " (" + ((PackageInfo)localObject4).packageName + ")");
          ((PrintStream)localObject2).println(localStringBuilder.toString());
          continue;
          localFile7.createNewFile();
        }
      }
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    try
    {
      paramContext = new FileOutputStream(localFile7);
      localObject1 = new PrintStream(paramContext, false, "UTF-8");
      long l = a(new File(MyApplication.a().getApplicationInfo().dataDir), (PrintStream)localObject1);
      ((PrintStream)localObject1).println(l / 1000L + "KB total");
      ((PrintStream)localObject1).println("========================");
      l = a(MyApplication.a().getExternalCacheDir().getParentFile(), (PrintStream)localObject1);
      ((PrintStream)localObject1).println(l / 1000L + "KB total");
      ((PrintStream)localObject1).println("========================");
      l = a(new File(gogolook.callgogolook2.card.c.a()), (PrintStream)localObject1);
      ((PrintStream)localObject1).println(l / 1000L + "KB total");
      ((PrintStream)localObject1).println("========================");
      ((PrintStream)localObject1).close();
      paramContext.close();
      paramContext = new ArrayList();
      paramContext.add(localFile3);
      paramContext.add(localFile4);
      paramContext.add(localFile5);
      paramContext.add(localFile6);
      paramContext.add(localFile7);
      a(localFile1, paramString, paramContext);
      return localFile2;
      ((PrintStream)localObject2).close();
      ((FileOutputStream)localObject1).close();
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static String a(Context paramContext, File[] paramArrayOfFile)
    throws IOException, ParseException
  {
    File localFile1 = a(paramContext, "/user_report.rpt");
    File localFile2 = new File(ab.h(paramContext) + "/user_report/");
    Object localObject = new byte[(int)localFile1.length()];
    BufferedInputStream localBufferedInputStream = new BufferedInputStream(new FileInputStream(localFile1));
    localBufferedInputStream.read((byte[])localObject, 0, localObject.length);
    localBufferedInputStream.close();
    paramContext = ag.a((byte[])localObject, ag.b(OJni.getEncryptKey(paramContext)).getBytes());
    localFile1.delete();
    localFile1.createNewFile();
    localObject = new BufferedOutputStream(new FileOutputStream(localFile1.getAbsolutePath()));
    ((BufferedOutputStream)localObject).write(paramContext);
    ((BufferedOutputStream)localObject).close();
    int i = 0;
    while (i <= 0)
    {
      paramContext = new File(localFile2 + "/" + paramArrayOfFile[0].getName());
      a(paramArrayOfFile[0], paramContext);
      paramArrayOfFile[0] = paramContext;
      i += 1;
    }
    paramContext = new ArrayList();
    paramContext.add(localFile1);
    i = 0;
    while (i <= 0)
    {
      paramContext.add(paramArrayOfFile[0]);
      i += 1;
    }
    a(localFile2, "/whoscall_report.zip", paramContext);
    return new File(localFile2 + "//whoscall_report.zip").getAbsolutePath();
  }
  
  private static void a(File paramFile)
  {
    if (paramFile.exists())
    {
      String[] arrayOfString = paramFile.list();
      int i = 0;
      if (i < arrayOfString.length)
      {
        File localFile = new File(paramFile, arrayOfString[i]);
        if (localFile.isDirectory()) {
          a(localFile);
        }
        for (;;)
        {
          i += 1;
          break;
          localFile.delete();
        }
      }
      paramFile.delete();
    }
  }
  
  private static void a(File paramFile1, File paramFile2)
    throws IOException
  {
    paramFile1 = new FileInputStream(paramFile1);
    paramFile2 = new FileOutputStream(paramFile2);
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramFile1.read(arrayOfByte);
      if (i <= 0) {
        break;
      }
      paramFile2.write(arrayOfByte, 0, i);
    }
    paramFile1.close();
    paramFile2.close();
  }
  
  private static void a(File paramFile1, File paramFile2, ZipOutputStream paramZipOutputStream)
    throws FileNotFoundException, IOException
  {
    FileInputStream localFileInputStream = new FileInputStream(paramFile2);
    paramFile1 = paramFile2.getCanonicalPath().substring(paramFile1.getCanonicalPath().length() + 1, paramFile2.getCanonicalPath().length());
    System.out.println("Writing '" + paramFile1 + "' to zip file");
    paramZipOutputStream.putNextEntry(new ZipEntry(paramFile1));
    paramFile1 = new byte['Ѐ'];
    for (;;)
    {
      int i = localFileInputStream.read(paramFile1);
      if (i < 0) {
        break;
      }
      paramZipOutputStream.write(paramFile1, 0, i);
    }
    paramZipOutputStream.closeEntry();
    localFileInputStream.close();
  }
  
  public static void a(File paramFile, String paramString, List<File> paramList)
  {
    try
    {
      paramString = new FileOutputStream(paramFile.getAbsolutePath() + "/" + paramString);
      ZipOutputStream localZipOutputStream = new ZipOutputStream(paramString);
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        File localFile = (File)paramList.next();
        if (!localFile.isDirectory()) {
          a(paramFile, localFile, localZipOutputStream);
        }
      }
      return;
    }
    catch (FileNotFoundException paramFile)
    {
      paramFile.printStackTrace();
      return;
      localZipOutputStream.close();
      paramString.close();
      return;
    }
    catch (IOException paramFile)
    {
      paramFile.printStackTrace();
    }
  }
  
  public static void a(String paramString)
  {
    if (paramString != null)
    {
      a(paramString, Calendar.getInstance().getTime());
      a(paramString, 2, Calendar.getInstance().getTimeInMillis());
    }
  }
  
  private static void a(String paramString, int paramInt, long paramLong)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("_log", paramString);
      localContentValues.put("_ctime", Long.valueOf(paramLong));
      localContentValues.put("_type", Integer.valueOf(paramInt));
      if (Looper.myLooper() == Looper.getMainLooper())
      {
        new LogManager.2(MyApplication.a().getContentResolver()).a(a.ac.a, localContentValues);
        return;
      }
      MyApplication.a().getContentResolver().insert(a.ac.a, localContentValues);
      b();
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private static void a(String paramString, Date paramDate)
  {
    if (f.f().e())
    {
      paramString = "# " + a.format(paramDate) + "\n" + paramString;
      j.f().a(paramString);
    }
  }
  
  private static void b()
  {
    int i = p.b("pref_current_log_size", 0);
    if (i < 1000) {
      p.a("pref_current_log_size", i + 1);
    }
    label186:
    for (;;)
    {
      return;
      ContentResolver localContentResolver = MyApplication.a().getContentResolver();
      Cursor localCursor = localContentResolver.query(a.ac.a, new String[] { "_ctime" }, null, null, "_ctime DESC LIMIT 700");
      long l;
      if (localCursor != null) {
        if (localCursor.moveToLast())
        {
          l = localCursor.getLong(localCursor.getColumnIndex("_ctime"));
          localCursor.close();
        }
      }
      for (;;)
      {
        if (l == -1L) {
          break label186;
        }
        p.a("pref_current_log_size", 700);
        if (Looper.myLooper() == Looper.getMainLooper())
        {
          new LogManager.1(localContentResolver).a(a.ac.a, "_ctime < ?", new String[] { String.valueOf(l) });
          return;
        }
        localContentResolver.delete(a.ac.a, "_ctime < ?", new String[] { String.valueOf(l) });
        return;
        l = -1L;
        break;
        l = -1L;
      }
    }
  }
  
  public static void b(String paramString)
  {
    if (paramString != null) {
      a(paramString, Calendar.getInstance().getTime());
    }
  }
  
  public static void c(String paramString)
  {
    if (paramString != null) {}
    try
    {
      String str = Base64.encodeToString(paramString.getBytes("UTF-8"), 0);
      paramString = str;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    a(paramString, 1, Calendar.getInstance().getTimeInMillis());
  }
  
  public static void d(String paramString)
  {
    if (paramString != null) {
      a(paramString, 2, Calendar.getInstance().getTimeInMillis());
    }
  }
  
  public static void putEventDiaperLog(String paramString)
  {
    if ((paramString != null) && (!paramString.equals("response data = "))) {
      a(paramString, 0, Calendar.getInstance().getTimeInMillis());
    }
  }
  
  public static void trackGgaLog(com.gogolook.whoscallsdk.core.b.f paramF) {}
}
