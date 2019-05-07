package com.stone.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Xml;
import android.webkit.MimeTypeMap;
import com.debugTools.debugTool;
import com.gstar.ApplicationStone;
import com.gstar.android.CadFilesActivity;
import com.gstar.android.ImageShowActivity;
import com.gstar.model.FileModel;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Signature;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.util.EncodingUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

@SuppressLint({"DefaultLocale"})
public class FileUtils
{
  public static final String FILEDATE = "fileDate";
  public static final String FILENAME = "fileName";
  public static final String FILEPATH = "filePath";
  public static final String FILESIZE = "fileSize";
  public static final String FILETYPE = "fileType";
  private static final String TAG = FileUtils.class.getSimpleName();
  
  public FileUtils() {}
  
  public static void ApkInstall(Context paramContext, String paramString)
  {
    paramString = new File(paramString);
    if ((paramString.exists()) && (paramString.getName().toLowerCase(Locale.getDefault()).endsWith(".apk")))
    {
      Intent localIntent = new Intent();
      localIntent.addFlags(268435456);
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.fromFile(paramString), "application/vnd.android.package-archive");
      paramContext.startActivity(localIntent);
    }
  }
  
  public static void ApkUnInstall(Context paramContext, String paramString)
  {
    if (checkPackageNameExists(paramContext, paramString)) {
      paramContext.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString)));
    }
  }
  
  public static Boolean DeleteFileFromSD(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return Boolean.valueOf(true);
    }
    paramString = new File(paramString);
    boolean bool = false;
    if (isSDExist()) {
      bool = paramString.delete();
    }
    return Boolean.valueOf(bool);
  }
  
  public static String DoubleFormat(double paramDouble, int paramInt)
  {
    return new BigDecimal(Double.toString(paramDouble)).setScale(paramInt, 4).toPlainString();
  }
  
  public static boolean appendSystemFile(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.openFileOutput(paramString1, 32768);
      paramContext.write(paramString2.getBytes());
      paramContext.flush();
      paramContext.close();
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean checkAndMakeDir(String paramString)
  {
    paramString = new File(paramString);
    return (paramString.exists()) || (paramString.mkdirs());
  }
  
  public static String checkAppSignatures(String paramString)
  {
    try
    {
      Class localClass = Class.forName("android.content.pm.PackageParser");
      Object localObject = localClass.getConstructor(new Class[] { String.class }).newInstance(new Object[] { paramString });
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      localDisplayMetrics.setToDefaults();
      paramString = localClass.getDeclaredMethod("parsePackage", new Class[] { File.class, String.class, DisplayMetrics.class, Integer.TYPE }).invoke(localObject, new Object[] { new File(paramString), paramString, localDisplayMetrics, Integer.valueOf(64) });
      localClass.getDeclaredMethod("collectCertificates", new Class[] { paramString.getClass(), Integer.TYPE }).invoke(localObject, new Object[] { paramString, Integer.valueOf(64) });
      paramString = ((Signature[])(Signature[])paramString.getClass().getDeclaredField("mSignatures").get(paramString))[0].toString();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static boolean checkClassNameExists(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    try
    {
      Class.forName(paramString);
      return true;
    }
    catch (ClassNotFoundException paramString) {}
    return false;
  }
  
  public static Boolean checkFileExists(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return Boolean.valueOf(false);
    }
    paramString = new File(paramString);
    boolean bool = true;
    if (isSDExist()) {
      bool = paramString.exists();
    }
    return Boolean.valueOf(bool);
  }
  
  public static boolean checkPackageNameExists(Context paramContext, String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean checkServiceRunning(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(100).iterator();
    while (paramContext.hasNext()) {
      if (((ActivityManager.RunningServiceInfo)paramContext.next()).service.getClassName().equals(paramString)) {
        bool = true;
      }
    }
    return bool;
  }
  
  public static <T> List<T> combineArrayList(List<T> paramList1, List<T> paramList2)
  {
    paramList2 = paramList2.iterator();
    while (paramList2.hasNext()) {
      paramList1.add(paramList2.next());
    }
    return paramList1;
  }
  
  public static String compareFileName(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramString2 = StringUtils.getMD5String(paramString2);
      String[] arrayOfString = new File(paramString1).list();
      int i = 0;
      while (i < arrayOfString.length)
      {
        if (paramString2.endsWith(arrayOfString[i]))
        {
          paramString1 = paramString1 + arrayOfString[i];
          return paramString1;
        }
        i += 1;
      }
      return null;
    }
    catch (NullPointerException paramString1)
    {
      ToastUtils.toastLong(paramContext, "语音功能需要SD卡支持");
      paramString1.printStackTrace();
    }
  }
  
  public static boolean compareSize(String paramString)
  {
    String str = paramString.substring(paramString.length() - 2).trim();
    paramString = paramString.substring(0, paramString.length() - 2).trim();
    if ("GB".equalsIgnoreCase(str)) {}
    int i;
    do
    {
      return true;
      if (!"MB".equalsIgnoreCase(str)) {
        break;
      }
      i = Math.round(Float.parseFloat(paramString));
    } while ((paramString != null) && (i > 100));
    return false;
    return false;
  }
  
  public static void copyAssetsDir2Dir(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      String[] arrayOfString = paramContext.getResources().getAssets().list(paramString1);
      File localFile = new File(paramString2);
      if ((!localFile.exists()) && (!localFile.mkdirs())) {
        Log.e("--CopyAssets--", "cannot create directory.");
      }
      int i = 0;
      while (i < arrayOfString.length)
      {
        String str = arrayOfString[i];
        try
        {
          if ((str.compareTo("images") == 0) || (str.compareTo("sounds") == 0) || (str.compareTo("webkit") == 0)) {
            break label360;
          }
          if (!str.contains(".")) {
            if (paramString1.length() == 0) {
              copyAssetsDir2Dir(paramContext, str, paramString2 + str + "/");
            }
          }
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          localFileNotFoundException.printStackTrace();
          break label360;
          copyAssetsDir2Dir(paramContext, paramString1 + "/" + localFileNotFoundException, paramString2 + localFileNotFoundException + "/");
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
        Object localObject = new File(localFile, localIOException);
        if (!((File)localObject).exists())
        {
          if (paramString1.length() != 0) {}
          for (InputStream localInputStream = paramContext.getAssets().open(paramString1 + "/" + localIOException);; localInputStream = paramContext.getAssets().open(localInputStream))
          {
            localObject = new FileOutputStream((File)localObject);
            byte[] arrayOfByte = new byte['Ѐ'];
            for (;;)
            {
              int j = localInputStream.read(arrayOfByte);
              if (j <= 0) {
                break;
              }
              ((OutputStream)localObject).write(arrayOfByte, 0, j);
            }
          }
          localInputStream.close();
          ((OutputStream)localObject).close();
        }
        label360:
        i += 1;
      }
      return;
    }
    catch (IOException paramContext) {}
  }
  
  public static boolean copyAssetsFile2Dir(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramString2 = new File(paramString2, paramString1);
      if (paramString2.exists()) {
        return true;
      }
      paramContext = paramContext.getAssets().open(paramString1);
      paramString1 = new FileOutputStream(paramString2);
      paramString2 = new byte['Ѐ'];
      for (;;)
      {
        int i = paramContext.read(paramString2);
        if (i <= 0) {
          break;
        }
        paramString1.write(paramString2, 0, i);
      }
      paramContext.close();
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    paramString1.close();
    return true;
  }
  
  public static boolean copyFileTo(File paramFile1, File paramFile2)
  {
    if (!paramFile1.exists()) {}
    Object localObject;
    do
    {
      return false;
      localObject = paramFile2;
      if (!paramFile1.isDirectory()) {
        break;
      }
    } while (!paramFile2.isDirectory());
    String str = paramFile2.getPath() + "/" + paramFile1.getName();
    createDir(str);
    copyFilesTo(paramFile1.getPath(), str);
    if ((paramFile2.isDirectory()) && (paramFile2.exists()))
    {
      localObject = new File(paramFile2.getPath() + "/" + paramFile1.getName());
      try
      {
        do
        {
          paramFile1 = new FileInputStream(paramFile1);
          paramFile2 = new FileOutputStream((File)localObject);
          localObject = new byte['က'];
          for (;;)
          {
            int i = paramFile1.read((byte[])localObject);
            if (i == -1) {
              break;
            }
            paramFile2.write((byte[])localObject, 0, i);
          }
        } while (paramFile2.getParent() != null);
      }
      catch (Exception paramFile1)
      {
        paramFile1.printStackTrace();
        return false;
      }
    }
    return false;
    paramFile2.flush();
    paramFile2.close();
    paramFile1.close();
    return true;
  }
  
  public static boolean copyFileTo(String paramString1, String paramString2)
  {
    return copyFileTo(new File(paramString1), new File(paramString2));
  }
  
  public static boolean copyFilesTo(File paramFile1, File paramFile2)
  {
    if ((!paramFile1.exists()) || (!paramFile2.exists())) {}
    while ((!paramFile1.isDirectory()) || (!paramFile2.isDirectory())) {
      return false;
    }
    for (;;)
    {
      int i;
      try
      {
        paramFile1 = paramFile1.listFiles();
        i = 0;
        if (i < paramFile1.length)
        {
          File localFile;
          if (paramFile1[i].isFile())
          {
            localFile = new File(paramFile2.getPath() + "//" + paramFile1[i].getName());
            copyFileTo(paramFile1[i], localFile);
          }
          else if (paramFile1[i].isDirectory())
          {
            localFile = new File(paramFile2.getPath() + "//" + paramFile1[i].getName());
            copyFilesTo(paramFile1[i], localFile);
          }
        }
      }
      catch (Exception paramFile1)
      {
        paramFile1.printStackTrace();
        return false;
      }
      return true;
      i += 1;
    }
  }
  
  public static boolean copyFilesTo(String paramString1, String paramString2)
  {
    return copyFilesTo(new File(paramString1), new File(paramString2));
  }
  
  public static boolean copySystemDataFileTo(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      boolean bool = copyFileTo(new File(getSystemFilesPath(paramContext) + paramString1), new File(getSystemFilesPath(paramContext) + paramString2));
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean copySystemDataFilesTo(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      boolean bool = copyFilesTo(new File(getSystemFilesPath(paramContext) + paramString1), new File(getSystemFilesPath(paramContext) + paramString2));
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static File creatSystemDataDir(Context paramContext, String paramString)
  {
    paramContext = new File(getSystemFilesPath(paramContext) + paramString);
    paramContext.mkdir();
    return paramContext;
  }
  
  public static File creatSystemDataFile(Context paramContext, String paramString)
  {
    try
    {
      paramContext = new File(getSystemFilesPath(paramContext) + paramString);
      if (!paramContext.getParentFile().exists()) {
        paramContext.getParentFile().mkdirs();
      }
      if (!paramContext.exists()) {
        paramContext.createNewFile();
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static boolean createDir(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!TextUtils.isEmpty(paramString))
    {
      File localFile = new File(paramString);
      bool1 = bool2;
      if (!localFile.exists())
      {
        localFile.mkdir();
        bool1 = bool2;
        if (isFileExist(paramString)) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean createDirAndFile(String paramString)
  {
    boolean bool2 = false;
    try
    {
      File localFile = new File(paramString);
      boolean bool1 = bool2;
      if (!localFile.exists())
      {
        if (!localFile.getParentFile().exists()) {
          localFile.getParentFile().mkdirs();
        }
        localFile.createNewFile();
        boolean bool3 = isFileExist(paramString);
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static boolean deleteDir(File paramFile)
  {
    int i = 0;
    if ((paramFile == null) || (!paramFile.exists()) || (paramFile.isFile())) {
      return false;
    }
    File[] arrayOfFile = paramFile.listFiles();
    int j = arrayOfFile.length;
    if (i < j)
    {
      File localFile = arrayOfFile[i];
      if (localFile.isFile()) {
        localFile.delete();
      }
      for (;;)
      {
        i += 1;
        break;
        if (localFile.isDirectory()) {
          deleteDir(localFile);
        }
      }
    }
    paramFile.delete();
    return true;
  }
  
  public static boolean deleteDir(String paramString)
  {
    return deleteDir(new File(paramString));
  }
  
  public static boolean deleteDirOrFile(File paramFile)
  {
    if ((paramFile == null) || (!paramFile.exists())) {
      return false;
    }
    if (paramFile.isDirectory()) {
      return deleteDir(paramFile);
    }
    return deleteFile(paramFile);
  }
  
  public static boolean deleteDirOrFile(String paramString)
  {
    return deleteDirOrFile(new File(paramString));
  }
  
  public static boolean deleteFile(File paramFile)
  {
    if (paramFile.isDirectory()) {
      return false;
    }
    return paramFile.delete();
  }
  
  public static boolean deleteFile(String paramString)
  {
    return deleteFile(new File(paramString));
  }
  
  public static boolean deleteSystemDataDir(Context paramContext, String paramString)
  {
    return deleteDir(new File(getSystemFilesPath(paramContext) + paramString));
  }
  
  public static boolean deleteSystemDataFile(Context paramContext, String paramString)
  {
    return deleteFile(new File(getSystemFilesPath(paramContext) + paramString));
  }
  
  public static boolean fileRename(String paramString1, String paramString2)
  {
    paramString1 = new File(paramString1);
    if (!paramString1.exists()) {
      return false;
    }
    paramString1.renameTo(new File(paramString2));
    return true;
  }
  
  public static String formatFileSize(long paramLong)
  {
    DecimalFormat localDecimalFormat = new DecimalFormat("#.00");
    if (paramLong < 1024L) {
      return localDecimalFormat.format(paramLong) + "B";
    }
    if (paramLong < 1048576L) {
      return localDecimalFormat.format(paramLong / 1024.0D) + "KB";
    }
    if (paramLong < 1073741824L) {
      return localDecimalFormat.format(paramLong / 1048576.0D) + "MB";
    }
    return localDecimalFormat.format(paramLong / 1.073741824E9D) + "GB";
  }
  
  public static String getAbsoluteImagePath(Context paramContext, Uri paramUri)
  {
    paramContext = ((Activity)paramContext).managedQuery(paramUri, new String[] { "_data" }, null, null, null);
    int i = paramContext.getColumnIndexOrThrow("_data");
    paramContext.moveToFirst();
    paramUri = paramContext.getString(i);
    paramContext.close();
    return paramUri;
  }
  
  public static String getAttachmetName(Context paramContext, Uri paramUri)
  {
    if (paramUri != null)
    {
      String str = paramUri.toString();
      Object localObject3 = null;
      Object localObject2 = null;
      Object localObject1 = localObject3;
      if (str.lastIndexOf("/attachments") != -1)
      {
        paramUri = paramContext.getContentResolver().query(paramUri, new String[] { "_display_name" }, null, null, null);
        localObject1 = localObject3;
        if (paramUri != null)
        {
          int i = paramUri.getColumnIndex("_display_name");
          paramContext = localObject2;
          if (i != -1)
          {
            paramUri.moveToFirst();
            paramContext = paramUri.getString(i);
          }
          paramUri.close();
          localObject1 = paramContext;
        }
      }
      return localObject1;
    }
    return null;
  }
  
  public static List<Map<String, Object>> getAudiosFromFolder(Context paramContext, String paramString)
  {
    localArrayList = new ArrayList();
    String str1 = null;
    label375:
    do
    {
      try
      {
        localObject = paramContext.getContentResolver();
        paramContext = "_id!='' ";
        if (!TextUtils.isEmpty(paramString)) {
          paramContext = "_id!='' " + " and _data like '" + paramString + "%' ";
        }
        paramString = ((ContentResolver)localObject).query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[] { "_id", "title", "album", "artist", "_data", "duration", "_size" }, paramContext, null, "title_key");
        boolean bool = paramString.moveToFirst();
        if (bool) {
          paramContext = str1;
        }
      }
      catch (Exception paramContext)
      {
        Object localObject;
        int i;
        String str2;
        String str3;
        int j;
        long l;
        paramContext.printStackTrace();
        return localArrayList;
      }
      try
      {
        i = paramString.getInt(paramString.getColumnIndexOrThrow("_id"));
        str1 = paramString.getString(paramString.getColumnIndexOrThrow("title"));
        localObject = paramString.getString(paramString.getColumnIndexOrThrow("album"));
        str2 = paramString.getString(paramString.getColumnIndexOrThrow("artist"));
        str3 = paramString.getString(paramString.getColumnIndexOrThrow("_data"));
        j = paramString.getInt(paramString.getColumnIndexOrThrow("duration"));
        l = paramString.getLong(paramString.getColumnIndexOrThrow("_size"));
        if (str3.equals("")) {
          break label442;
        }
        paramContext = new HashMap();
        paramContext.put("audio_id", Integer.valueOf(i));
        paramContext.put("audio_tilte", str1);
        paramContext.put("audio_album", localObject);
        paramContext.put("audio_artist", str2);
        paramContext.put("audio_url", str3);
        paramContext.put("audio_duration", Integer.valueOf(j));
        paramContext.put("audio_size", Long.valueOf(l));
        localArrayList.add(paramContext);
      }
      catch (Exception paramContext)
      {
        break label431;
        break label375;
      }
    } while (paramString.moveToNext());
    Log.i(TAG, "getAudiosFromFolder 扫描结束!AudiosCount = " + localArrayList.size());
    if (paramString != null) {
      paramString.close();
    }
    return localArrayList;
  }
  
  public static String getAvailableFilesPathAndroidData(boolean paramBoolean)
  {
    if (!isSDExist())
    {
      if (paramBoolean) {
        return ApplicationStone.getInstance().getCacheDir().getPath() + "/";
      }
      return ApplicationStone.getInstance().getFilesDir().getPath() + "/";
    }
    if (paramBoolean) {
      return ApplicationStone.getInstance().getExternalCacheDir() + "/";
    }
    return ApplicationStone.getInstance().getExternalFilesDir("").getPath() + "/";
  }
  
  public static String getAvailableFilesPathSystem(Context paramContext)
  {
    if (!isSDExist()) {
      return getSystemFilesPath(paramContext);
    }
    return getSDCardFilesPath();
  }
  
  private static String getAvailableSize(Context paramContext, String paramString)
  {
    paramString = new StatFs(paramString);
    return Formatter.formatFileSize(paramContext, paramString.getBlockSize() * paramString.getAvailableBlocks());
  }
  
  public static List<String> getAvailableStorageList(Context paramContext)
  {
    Object localObject1 = null;
    StorageManager localStorageManager = (StorageManager)paramContext.getSystemService("storage");
    try
    {
      paramContext = localStorageManager.getClass().getMethod("getVolumePaths", new Class[0]);
      localObject1 = paramContext;
    }
    catch (NoSuchMethodException paramContext)
    {
      try
      {
        if (((Method)localObject1).invoke(localStorageManager, new Object[0]) == null) {
          break label78;
        }
        paramContext = (String[])((Method)localObject1).invoke(localStorageManager, new Object[0]);
        localObject1 = localArrayList;
        if (paramContext == null) {
          return localObject1;
        }
        localObject1 = localArrayList;
        if (paramContext.length <= 0) {
          return localObject1;
        }
        ArrayList localArrayList = new ArrayList();
        int j = paramContext.length;
        int i = 0;
        for (;;)
        {
          localObject1 = localArrayList;
          if (i >= j) {
            break;
          }
          localObject1 = paramContext[i];
          if (isFileExist((String)localObject1)) {
            localArrayList.add(localObject1);
          }
          i += 1;
        }
        paramContext = paramContext;
        paramContext.printStackTrace();
      }
      catch (IllegalArgumentException paramContext)
      {
        for (;;)
        {
          paramContext.printStackTrace();
          paramContext = localObject2;
        }
      }
      catch (IllegalAccessException paramContext)
      {
        for (;;)
        {
          paramContext.printStackTrace();
          paramContext = localObject2;
        }
      }
      catch (InvocationTargetException paramContext)
      {
        for (;;)
        {
          Object localObject2;
          paramContext.printStackTrace();
          paramContext = localObject2;
        }
      }
    }
    localObject2 = null;
    localArrayList = null;
    paramContext = localObject2;
    if (localObject1 != null) {
      paramContext = localObject2;
    }
    label78:
    return localObject1;
  }
  
  public static String getAvailableStorageRoot(Context paramContext)
  {
    String str = getAvailableFilesPathSystem(paramContext);
    List localList = getAvailableStorageList(paramContext);
    paramContext = str;
    if (localList != null)
    {
      paramContext = str;
      if (localList.size() > 0)
      {
        paramContext = (String)localList.get(0);
        str = (String)localList.get(localList.size() - 1);
        if (paramContext.trim().length() >= str.trim().length()) {
          break label117;
        }
      }
    }
    label117:
    for (str = new File(paramContext).getParentFile().getPath();; str = new File(str).getParentFile().getPath())
    {
      paramContext = str;
      if (str.indexOf("emulated") >= 0) {
        paramContext = new File(str).getParentFile().getPath();
      }
      return paramContext;
    }
  }
  
  public static List<Map<String, Object>> getContactsList(Context paramContext, String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    new HashMap();
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Cursor localCursor1 = localContentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    while (localCursor1.moveToNext())
    {
      paramString2 = localCursor1.getString(localCursor1.getColumnIndex("_id"));
      String str = localCursor1.getString(localCursor1.getColumnIndex("display_name"));
      paramContext = "";
      Cursor localCursor2 = localContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = " + paramString2, null, null);
      while (localCursor2.moveToNext())
      {
        paramString1 = localCursor2.getString(localCursor2.getColumnIndex("data1")).replace("-", "");
        if ((paramString1 != null) && (!paramString1.trim().equalsIgnoreCase(""))) {
          paramContext = paramString1;
        }
      }
      localCursor2.close();
      paramString1 = "";
      localCursor2 = localContentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, "contact_id=" + paramString2, null, null);
      while (localCursor2.moveToNext())
      {
        paramString2 = localCursor2.getString(localCursor2.getColumnIndex("data1"));
        if ((paramString2 != null) && (!paramString2.trim().equalsIgnoreCase(""))) {
          paramString1 = paramString2;
        }
      }
      localCursor2.close();
      paramString2 = new HashMap();
      paramString2.put("contactsNamePY", "");
      paramString2.put("contactsName", str);
      paramString2.put("contactsNumber", paramContext);
      paramString2.put("contactsEmail", paramString1);
      paramString2.put("contactsSelected", Boolean.valueOf(false));
      localArrayList.add(paramString2);
      Log.i(TAG, "contactsName = " + str + "contactsNumber = " + paramContext + "contactsEmail = " + paramString1);
    }
    localCursor1.close();
    return localArrayList;
  }
  
  public static String getDouble2Sting(Double paramDouble, int paramInt)
  {
    try
    {
      Object localObject = NumberFormat.getInstance();
      ((NumberFormat)localObject).setMaximumFractionDigits(paramInt);
      ((NumberFormat)localObject).setMinimumFractionDigits(paramInt);
      localObject = ((NumberFormat)localObject).format(paramDouble);
      return localObject;
    }
    catch (Exception localException) {}
    return paramDouble.toString();
  }
  
  public static double getDoublePoint(double paramDouble, int paramInt)
  {
    double d = paramInt;
    try
    {
      d = Math.pow(10.0D, d);
      return (int)(paramDouble * d) / d;
    }
    catch (Exception localException) {}
    return paramDouble;
  }
  
  public static double getDoubleRound(Double paramDouble, int paramInt)
  {
    if (paramDouble == null) {}
    for (;;)
    {
      try
      {
        localBigDecimal = new BigDecimal("0.0");
        return localBigDecimal.divide(new BigDecimal("1"), paramInt, 4).doubleValue();
      }
      catch (Exception localException) {}
      BigDecimal localBigDecimal = new BigDecimal(Double.toString(paramDouble.doubleValue()));
    }
    return paramDouble.doubleValue();
  }
  
  public static List<String> getEmojiFile(Context paramContext)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramContext.getResources().getAssets().open("emoji"), "UTF-8"));
      for (;;)
      {
        String str = localBufferedReader.readLine();
        paramContext = localArrayList;
        if (str == null) {
          break;
        }
        localArrayList.add(str);
      }
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      paramContext = null;
    }
  }
  
  public static String getExternalStorageDirectory()
  {
    Map localMap = System.getenv();
    String[] arrayOfString = new String[localMap.values().size()];
    localMap.values().toArray(arrayOfString);
    localMap = arrayOfString[(arrayOfString.length - 1)];
    if ((localMap.startsWith("/mnt/")) && (!Environment.getExternalStorageDirectory().getAbsolutePath().equals(localMap))) {
      return localMap;
    }
    return null;
  }
  
  public static int getFileCount(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      paramFile = paramFile.listFiles();
      int i = paramFile.length;
      int j = 0;
      for (;;)
      {
        k = i;
        if (j >= paramFile.length) {
          break;
        }
        k = i;
        if (paramFile[j].isDirectory()) {
          k = i + getFileCount(paramFile[j]) - 1;
        }
        j += 1;
        i = k;
      }
    }
    int k = 1;
    return k;
  }
  
  public static int getFileCount(String paramString)
  {
    return getFileCount(new File(paramString));
  }
  
  public static String getFileExtension(File paramFile)
  {
    if ((paramFile == null) || (paramFile.isDirectory())) {
      return "";
    }
    paramFile = paramFile.getName();
    if ((paramFile != null) && (paramFile.length() > 0))
    {
      int i = paramFile.lastIndexOf('.');
      if ((i > -1) && (i < paramFile.length() - 1)) {
        return paramFile.substring(i + 1);
      }
    }
    return "";
  }
  
  public static String getFileExtension(String paramString)
  {
    return getFileExtension(new File(paramString));
  }
  
  public static String getFileExtensionFromMimeType(String paramString)
  {
    MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
    if (localMimeTypeMap.hasMimeType(paramString)) {
      return localMimeTypeMap.getExtensionFromMimeType(paramString);
    }
    return "";
  }
  
  public static int getFileHashCode(File paramFile)
  {
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.isFile())) {
      return 0;
    }
    return paramFile.hashCode();
  }
  
  public static String getFileMD5(File paramFile)
  {
    if (!paramFile.isFile()) {
      return null;
    }
    byte[] arrayOfByte = new byte['Ѐ'];
    try
    {
      localMessageDigest = MessageDigest.getInstance("MD5");
      paramFile = new FileInputStream(paramFile);
      try
      {
        for (;;)
        {
          int i = paramFile.read(arrayOfByte, 0, 1024);
          if (i == -1) {
            break;
          }
          localMessageDigest.update(arrayOfByte, 0, i);
        }
        paramFile.printStackTrace();
      }
      catch (Exception paramFile) {}
    }
    catch (Exception paramFile)
    {
      MessageDigest localMessageDigest;
      for (;;) {}
    }
    return null;
    paramFile.close();
    return new BigInteger(1, localMessageDigest.digest()).toString(16);
  }
  
  public static String getFileMimeTypeFromExtension(String paramString)
  {
    Object localObject = paramString.replace(".", "");
    if ((((String)localObject).equalsIgnoreCase("docx")) || (((String)localObject).equalsIgnoreCase("wps"))) {
      paramString = "doc";
    }
    for (;;)
    {
      localObject = MimeTypeMap.getSingleton();
      if (!((MimeTypeMap)localObject).hasExtension(paramString)) {
        break;
      }
      return ((MimeTypeMap)localObject).getMimeTypeFromExtension(paramString);
      if (((String)localObject).equalsIgnoreCase("xlsx"))
      {
        paramString = "xls";
      }
      else
      {
        paramString = (String)localObject;
        if (((String)localObject).equalsIgnoreCase("pptx")) {
          paramString = "ppt";
        }
      }
    }
    if (paramString.equalsIgnoreCase("dwg")) {
      return "application/x-autocad";
    }
    if (paramString.equalsIgnoreCase("dxf")) {
      return "application/x-autocad";
    }
    if (paramString.equalsIgnoreCase("ocf")) {
      return "application/x-autocad";
    }
    return "*/*";
  }
  
  public static String getFileMimeTypeFromFile(File paramFile)
  {
    Object localObject = getFileExtension(paramFile).replace(".", "");
    if ((((String)localObject).equalsIgnoreCase("docx")) || (((String)localObject).equalsIgnoreCase("wps"))) {
      paramFile = "doc";
    }
    for (;;)
    {
      localObject = MimeTypeMap.getSingleton();
      if (!((MimeTypeMap)localObject).hasExtension(paramFile)) {
        break;
      }
      return ((MimeTypeMap)localObject).getMimeTypeFromExtension(paramFile);
      if (((String)localObject).equalsIgnoreCase("xlsx"))
      {
        paramFile = "xls";
      }
      else
      {
        paramFile = (File)localObject;
        if (((String)localObject).equalsIgnoreCase("pptx")) {
          paramFile = "ppt";
        }
      }
    }
    if (paramFile.equalsIgnoreCase("dwg")) {
      return "application/x-autocad";
    }
    if (paramFile.equalsIgnoreCase("dxf")) {
      return "application/x-autocad";
    }
    if (paramFile.equalsIgnoreCase("ocf")) {
      return "application/x-autocad";
    }
    return "*/*";
  }
  
  public static void getFileModelListBySearch(File paramFile, final List<FileModel> paramList, String paramString)
  {
    paramFile.listFiles(new FileFilter()
    {
      public boolean accept(File paramAnonymousFile)
      {
        if ((FileUtils.getFilePermission(paramAnonymousFile.getPath()) <= 0) || (paramAnonymousFile.getName().startsWith("."))) {}
        do
        {
          do
          {
            return false;
          } while ((paramAnonymousFile.isFile()) && ((!ApplicationStone.getInstance().isSupportFileType_All(paramAnonymousFile.getName())) || ("new.dwg".equalsIgnoreCase(paramAnonymousFile.getName()))));
          if (!paramAnonymousFile.isFile()) {
            break;
          }
        } while (!paramAnonymousFile.getName().toLowerCase().contains(this.val$strFileName.toLowerCase()));
        FileModel localFileModel = new FileModel();
        long l1 = paramAnonymousFile.lastModified();
        String str1 = DateUtils.getDateToString_YYYY_MM_DD_HH_MM_SS_EN(new Date(l1));
        long l2 = FileUtils.getFileSize(paramAnonymousFile, false);
        String str2 = FileUtils.formatFileSize(l2);
        String str3 = FileUtils.getFileExtension(paramAnonymousFile);
        localFileModel.setFileName(paramAnonymousFile.getName());
        localFileModel.setFilePath(paramAnonymousFile.getPath());
        localFileModel.setFileDate(l1);
        localFileModel.setFileDateShow(str1);
        localFileModel.setFileSize(l2);
        localFileModel.setFileSizeShow(str2);
        localFileModel.setFileType(str3);
        localFileModel.setFile(true);
        localFileModel.setDirectory(false);
        localFileModel.setFileSelected(false);
        paramList.add(localFileModel);
        return true;
        FileUtils.getFileModelListBySearch(paramAnonymousFile, paramList, this.val$strFileName);
        return true;
      }
    });
  }
  
  public static void getFileModelListChild(File paramFile, final List<FileModel> paramList, String paramString)
  {
    paramFile.listFiles(new FileFilter()
    {
      public boolean accept(File paramAnonymousFile)
      {
        if ((FileUtils.getFilePermission(paramAnonymousFile.getPath()) <= 0) || (paramAnonymousFile.getName().startsWith("."))) {}
        do
        {
          do
          {
            return false;
          } while ((paramAnonymousFile.isFile()) && ((!ApplicationStone.getInstance().isSupportFileType_All(paramAnonymousFile.getName())) || ("new.dwg".equalsIgnoreCase(paramAnonymousFile.getName()))));
          if (!paramAnonymousFile.isFile()) {
            break;
          }
        } while (!paramAnonymousFile.getName().toLowerCase().contains(this.val$strFileName.toLowerCase()));
        FileModel localFileModel = new FileModel();
        long l1 = paramAnonymousFile.lastModified();
        String str1 = DateUtils.getDateToString_YYYY_MM_DD_HH_MM_SS_EN(new Date(l1));
        long l2 = FileUtils.getFileSize(paramAnonymousFile, false);
        String str2 = FileUtils.formatFileSize(l2);
        String str3 = FileUtils.getFileExtension(paramAnonymousFile);
        localFileModel.setFileName(paramAnonymousFile.getName());
        localFileModel.setFilePath(paramAnonymousFile.getPath());
        localFileModel.setFileDate(l1);
        localFileModel.setFileDateShow(str1);
        localFileModel.setFileSize(l2);
        localFileModel.setFileSizeShow(str2);
        localFileModel.setFileType(str3);
        localFileModel.setFile(true);
        localFileModel.setDirectory(false);
        localFileModel.setFileSelected(false);
        paramList.add(localFileModel);
        return true;
        if (paramAnonymousFile.getName().toLowerCase().contains(this.val$strFileName.toLowerCase()))
        {
          localFileModel = new FileModel();
          l1 = paramAnonymousFile.lastModified();
          str1 = DateUtils.getDateToString_YYYY_MM_DD_HH_MM_SS_EN(new Date(l1));
          l2 = FileUtils.getFileSize(paramAnonymousFile, false);
          str2 = FileUtils.formatFileSize(l2);
          str3 = FileUtils.getFileExtension(paramAnonymousFile);
          localFileModel.setFileName(paramAnonymousFile.getName());
          localFileModel.setFilePath(paramAnonymousFile.getPath());
          localFileModel.setFileName(paramAnonymousFile.getName());
          localFileModel.setFilePath(paramAnonymousFile.getPath());
          localFileModel.setFileDate(l1);
          localFileModel.setFileDateShow(str1);
          localFileModel.setFileSize(l2);
          localFileModel.setFileSizeShow(str2);
          localFileModel.setFileType(str3);
          localFileModel.setFileType(str3);
          localFileModel.setFile(false);
          localFileModel.setDirectory(true);
          localFileModel.setFileSelected(false);
          paramList.add(localFileModel);
        }
        FileUtils.getFileModelListChild(paramAnonymousFile, paramList, this.val$strFileName);
        return true;
      }
    });
  }
  
  public static void getFileModelListCurrent(File paramFile, List<FileModel> paramList)
  {
    paramFile.listFiles(new FileFilter()
    {
      public boolean accept(File paramAnonymousFile)
      {
        if ((FileUtils.getFilePermission(paramAnonymousFile.getPath()) <= 0) || (paramAnonymousFile.getName().startsWith("."))) {}
        while ((paramAnonymousFile.isFile()) && ((!ApplicationStone.getInstance().isSupportFileType_All(paramAnonymousFile.getName())) || ("new.dwg".equalsIgnoreCase(paramAnonymousFile.getName())))) {
          return false;
        }
        FileModel localFileModel = new FileModel();
        long l1 = paramAnonymousFile.lastModified();
        String str1 = DateUtils.getDateToString_YYYY_MM_DD_HH_MM_SS_EN(new Date(l1));
        long l2 = FileUtils.getFileSize(paramAnonymousFile, false);
        String str2 = FileUtils.formatFileSize(l2);
        String str3 = FileUtils.getFileExtension(paramAnonymousFile);
        localFileModel.setFileName(paramAnonymousFile.getName());
        localFileModel.setFilePath(paramAnonymousFile.getPath());
        localFileModel.setFileDate(l1);
        localFileModel.setFileDateShow(str1);
        localFileModel.setFileSize(l2);
        localFileModel.setFileSizeShow(str2);
        localFileModel.setFileType(str3);
        localFileModel.setFile(paramAnonymousFile.isFile());
        localFileModel.setDirectory(paramAnonymousFile.isDirectory());
        localFileModel.setFileSelected(false);
        this.val$listFileModels.add(localFileModel);
        return true;
      }
    });
  }
  
  public static String getFileName(File paramFile)
  {
    if (paramFile == null) {
      return "";
    }
    return paramFile.getName();
  }
  
  public static String getFileName(String paramString)
  {
    return getFileName(new File(paramString));
  }
  
  public static String getFileNameNoExtension(File paramFile)
  {
    Object localObject;
    if (paramFile == null) {
      localObject = "";
    }
    String str;
    int i;
    do
    {
      do
      {
        do
        {
          do
          {
            return localObject;
            str = paramFile.getName();
            localObject = str;
          } while (!paramFile.isFile());
          localObject = str;
        } while (TextUtils.isEmpty(str));
        i = str.lastIndexOf('.');
        localObject = str;
      } while (i <= -1);
      localObject = str;
    } while (i >= str.length());
    return str.substring(0, i);
  }
  
  public static String getFileNameNoExtension(String paramString)
  {
    return getFileNameNoExtension(new File(paramString));
  }
  
  public static String getFilePathOfParent(File paramFile)
  {
    if (paramFile == null) {
      return "";
    }
    return paramFile.getParentFile().getPath();
  }
  
  public static String getFilePathOfParent(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      return getFilePathOfParent(new File(paramString));
    }
    return null;
  }
  
  public static int getFilePermission(File paramFile)
  {
    int i = -1;
    if (isFileExist(paramFile))
    {
      if ((!paramFile.canRead()) && (!paramFile.canWrite())) {}
      i = -1;
      if (paramFile.canRead())
      {
        if (!paramFile.canWrite()) {
          break label43;
        }
        i = 1;
      }
    }
    return i;
    label43:
    return 0;
  }
  
  public static int getFilePermission(String paramString)
  {
    return getFilePermission(new File(paramString));
  }
  
  public static boolean getFilePermissionExternalSD(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!TextUtils.isEmpty(paramString))
    {
      bool1 = bool2;
      if (getFilePermission(new File(paramString)) > 0)
      {
        bool1 = bool2;
        if (new File(paramString).isDirectory())
        {
          paramString = new File(paramString + "/.abcdefghijklmnopqrstuwxyz_123456789");
          deleteDirOrFile(paramString);
          paramString.mkdir();
          bool1 = bool2;
          if (isFileExist(paramString))
          {
            deleteDirOrFile(paramString);
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public static long getFileSize(File paramFile, boolean paramBoolean)
  {
    long l1 = 0L;
    long l2 = l1;
    for (;;)
    {
      long l3;
      int i;
      try
      {
        if (paramFile.isFile())
        {
          l2 = l1;
          l3 = l1;
          if (paramFile.exists())
          {
            l2 = l1;
            return paramFile.length();
          }
        }
        else
        {
          l3 = l1;
          if (paramBoolean)
          {
            l2 = l1;
            paramFile = paramFile.listFiles();
            l3 = l1;
            if (paramFile != null)
            {
              l2 = l1;
              l3 = l1;
              if (paramFile.length > 0)
              {
                l2 = l1;
                int j = paramFile.length;
                i = 0;
                l3 = l1;
                if (i < j)
                {
                  File localFile = paramFile[i];
                  l2 = l1;
                  if (localFile.isDirectory())
                  {
                    l2 = l1;
                    l1 += getFileSize(localFile, paramBoolean);
                  }
                  else
                  {
                    l2 = l1;
                    l3 = localFile.length();
                    l1 += l3;
                  }
                }
              }
            }
          }
        }
      }
      catch (Exception paramFile)
      {
        paramFile.printStackTrace();
        l3 = l2;
      }
      return l3;
      i += 1;
    }
  }
  
  public static long getFileSize(String paramString, boolean paramBoolean)
  {
    return getFileSize(new File(paramString), paramBoolean);
  }
  
  public static String getGamiFilePath(Context paramContext, Uri paramUri)
  {
    String str = getAttachmetName(paramContext, paramUri);
    str = getAvailableFilesPathSystem(paramContext) + "/" + str;
    try
    {
      paramContext = paramContext.getContentResolver().openInputStream(paramUri);
      if (paramContext == null)
      {
        Log.e("onCreate", "cannot access mail attachment");
        return str;
      }
      paramUri = new FileOutputStream(str);
      byte[] arrayOfByte = new byte['Ѐ'];
      while (paramContext.read(arrayOfByte) > 0) {
        paramUri.write(arrayOfByte);
      }
      return "";
    }
    catch (FileNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return "";
      paramUri.close();
      paramContext.close();
      return str;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static String getInOrOutSDAvailableSize(Context paramContext, String paramString)
  {
    return getAvailableSize(paramContext, paramString);
  }
  
  public static String getInOrOutSDTotalSize(Context paramContext, String paramString)
  {
    return getTotalSize(paramContext, paramString);
  }
  
  private static int getInt(String paramString)
  {
    int i = 0;
    try
    {
      paramString = Pattern.compile("^\\d+").matcher(paramString);
      if (paramString.find()) {
        i = Integer.valueOf(paramString.group()).intValue();
      }
      return i;
    }
    catch (NumberFormatException paramString)
    {
      paramString.printStackTrace();
    }
    return 0;
  }
  
  public static String getPackageNameClass(Context paramContext)
  {
    if ((paramContext == null) || ("".equals(paramContext))) {
      return "";
    }
    return paramContext.getPackageName();
  }
  
  public static String getPhoneCapacity()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    int i = localStatFs.getAvailableBlocks();
    int j = localStatFs.getBlockCount();
    int k = localStatFs.getBlockSize();
    return formatFileSize(i * k) + "/" + formatFileSize(j * k);
  }
  
  public static List<PhotoFileModel> getPhotoFilesList(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      long l1;
      try
      {
        l1 = System.currentTimeMillis();
        Object localObject = paramContext.getContentResolver();
        paramContext = "_id!=''  and _size >= 1 and _data is not null and _display_name is not null ";
        if (!TextUtils.isEmpty(paramString)) {
          paramContext = "_id!=''  and _size >= 1 and _data is not null and _display_name is not null " + " and _data like '" + paramString + "%' ";
        }
        paramString = ((ContentResolver)localObject).query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { "_id", "_display_name", "_data", "latitude", "longitude", "date_added", "date_modified" }, paramContext, null, "date_added desc ");
        if ((paramString == null) || (paramString.getCount() <= 0)) {
          break label416;
        }
        paramString.moveToPrevious();
        if (!paramString.moveToNext()) {
          break label416;
        }
        localObject = new PhotoFileModel();
        if (paramString.getString(0) != null)
        {
          paramContext = paramString.getString(0);
          ((PhotoFileModel)localObject).setImageId(paramContext);
          if (paramString.getString(1) == null) {
            break label470;
          }
          paramContext = paramString.getString(1);
          ((PhotoFileModel)localObject).setImageName(paramContext);
          if (paramString.getString(2) == null) {
            break label476;
          }
          paramContext = paramString.getString(2);
          ((PhotoFileModel)localObject).setImagePath(paramContext);
          if (paramString.getString(3) == null) {
            break label482;
          }
          paramContext = paramString.getString(3);
          ((PhotoFileModel)localObject).setImageLat(paramContext);
          if (paramString.getString(4) == null) {
            break label489;
          }
          paramContext = paramString.getString(4);
          ((PhotoFileModel)localObject).setImageLon(paramContext);
          if (paramString.getString(5) == null) {
            break label366;
          }
          paramContext = paramString.getString(5);
          ((PhotoFileModel)localObject).setImageAddDate(paramContext);
          if (paramString.getString(6) == null) {
            break label391;
          }
          paramContext = paramString.getString(6);
          ((PhotoFileModel)localObject).setImageEditDate(paramContext);
          ((PhotoFileModel)localObject).setImageSelected(false);
          localArrayList.add(localObject);
          continue;
        }
        paramContext = "";
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return localArrayList;
      }
      continue;
      label366:
      paramContext = "" + System.currentTimeMillis();
      continue;
      label391:
      paramContext = "" + System.currentTimeMillis();
      continue;
      label416:
      if (paramString != null) {
        paramString.close();
      }
      long l2 = System.currentTimeMillis();
      Log.d(TAG, "getPhotoFilesList useTime: " + (l2 - l1) + " ms");
      return localArrayList;
      label470:
      paramContext = "";
      continue;
      label476:
      paramContext = "";
      continue;
      label482:
      paramContext = "0";
      continue;
      label489:
      paramContext = "0";
    }
  }
  
  public static List<PhotoFolderModel> getPhotoFoldersList(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      long l1;
      try
      {
        l1 = System.currentTimeMillis();
        Object localObject = paramContext.getContentResolver();
        paramContext = "_id!=''  and _size >= 1 and _data is not null and bucket_display_name is not null ";
        if (!TextUtils.isEmpty(paramString)) {
          paramContext = "_id!=''  and _size >= 1 and _data is not null and bucket_display_name is not null " + " and _data like '" + paramString + "%' ";
        }
        paramContext = paramContext + ") group by (bucket_id";
        paramString = ((ContentResolver)localObject).query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { " distinct bucket_id", "bucket_display_name", "_data", " count(*) " }, paramContext, null, "date_added desc ");
        if ((paramString == null) || (paramString.getCount() <= 0)) {
          break label276;
        }
        paramString.moveToPrevious();
        if (!paramString.moveToNext()) {
          break label276;
        }
        localObject = new PhotoFolderModel();
        if (paramString.getString(1) != null)
        {
          paramContext = paramString.getString(1);
          ((PhotoFolderModel)localObject).setImageFolderName(paramContext);
          ((PhotoFolderModel)localObject).setImageFolderPath(new File(paramString.getString(2)).getParentFile().getPath());
          if (paramString.getString(2) == null) {
            break label330;
          }
          paramContext = paramString.getString(2);
          ((PhotoFolderModel)localObject).setImageFolderLogo(paramContext);
          ((PhotoFolderModel)localObject).setImageCount(paramString.getInt(3));
          localArrayList.add(localObject);
          continue;
        }
        paramContext = "";
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return localArrayList;
      }
      continue;
      label276:
      if (paramString != null) {
        paramString.close();
      }
      long l2 = System.currentTimeMillis();
      Log.d(TAG, "getPhotoFolderList useTime: " + (l2 - l1) + " ms");
      return localArrayList;
      label330:
      paramContext = "";
    }
  }
  
  public static String getRomAvailableSize(Context paramContext)
  {
    return getAvailableSize(paramContext, Environment.getDataDirectory().getPath());
  }
  
  public static String getRomTotalSize(Context paramContext)
  {
    return getTotalSize(paramContext, Environment.getDataDirectory().getPath());
  }
  
  public static String getSDCardCachePath()
  {
    if (!isSDExist()) {
      return null;
    }
    return Environment.getDownloadCacheDirectory().getPath() + "/";
  }
  
  public static String getSDCardCapacity()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    int i = localStatFs.getAvailableBlocks();
    int j = localStatFs.getBlockCount();
    int k = localStatFs.getBlockSize();
    return formatFileSize(i * k) + "/" + formatFileSize(j * k);
  }
  
  public static String getSDCardDownloadPath()
  {
    if (!isSDExist()) {
      return null;
    }
    return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/";
  }
  
  public static String getSDCardFilesPath()
  {
    if (!isSDExist()) {
      return null;
    }
    return Environment.getExternalStorageDirectory().getPath() + "/";
  }
  
  public static String getSign(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(64).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (localPackageInfo.packageName.equals(ApplicationStone.getInstance().getPackageName())) {
        return localPackageInfo.signatures[0].toString();
      }
    }
    return null;
  }
  
  public static int getSubStringCount(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.length() == 0) || (paramString2 == null) || (paramString2.length() == 0))
    {
      j = 0;
      return j;
    }
    int i = 0;
    int k;
    for (int j = 0;; j = k + paramString2.length())
    {
      k = paramString1.indexOf(paramString2, j);
      j = i;
      if (k < 0) {
        break;
      }
      i += 1;
    }
  }
  
  public static String getSystemCachePath(Context paramContext)
  {
    return paramContext.getCacheDir().getPath() + "/";
  }
  
  public static String getSystemFilesPath(Context paramContext)
  {
    return paramContext.getFilesDir().getPath() + "/";
  }
  
  private static String getTotalSize(Context paramContext, String paramString)
  {
    paramString = new StatFs(paramString);
    return Formatter.formatFileSize(paramContext, paramString.getBlockSize() * paramString.getBlockCount());
  }
  
  public static boolean isClassExists(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    try
    {
      Class.forName(paramString);
      return true;
    }
    catch (ClassNotFoundException paramString) {}
    return false;
  }
  
  public static boolean isCompareFiles(File paramFile1, File paramFile2)
  {
    return paramFile1.getPath().equalsIgnoreCase(paramFile2.getPath());
  }
  
  public static boolean isCompareFiles(String paramString1, String paramString2)
  {
    if (paramString1.equalsIgnoreCase(paramString2)) {
      return true;
    }
    return isCompareFiles(new File(paramString1), new File(paramString2));
  }
  
  public static boolean isFileExist(File paramFile)
  {
    try
    {
      boolean bool = paramFile.exists();
      return bool;
    }
    catch (Exception paramFile) {}
    return false;
  }
  
  public static boolean isFileExist(String paramString)
  {
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        boolean bool = new File(paramString).exists();
        return bool;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static boolean isJpgFile(String paramString)
  {
    if (paramString != null)
    {
      if ((paramString.endsWith(".jpg")) || (paramString.endsWith(".JPG"))) {}
      int i;
      do
      {
        return true;
        i = paramString.lastIndexOf('.');
      } while ((i != -1) && (paramString.substring(i, paramString.length()).equalsIgnoreCase(".jpg")));
    }
    return false;
  }
  
  public static boolean isPackageExists(Context paramContext, String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isSDExist()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean moveFileTo(File paramFile1, File paramFile2)
  {
    if (!paramFile1.exists()) {}
    while ((!paramFile2.exists()) || (!paramFile2.isDirectory())) {
      return false;
    }
    return paramFile1.renameTo(new File(paramFile2, paramFile1.getName()));
  }
  
  public static boolean moveFileTo(String paramString1, String paramString2)
  {
    return moveFileTo(new File(paramString1), new File(paramString2));
  }
  
  public static boolean moveFilesTo(File paramFile1, File paramFile2)
  {
    if ((!paramFile1.isDirectory()) || (!paramFile2.isDirectory())) {
      return false;
    }
    paramFile1 = paramFile1.listFiles();
    int i = 0;
    while (i < paramFile1.length)
    {
      moveFileTo(paramFile1[i], paramFile2);
      i += 1;
    }
    return true;
  }
  
  public static boolean moveFilesTo(String paramString1, String paramString2)
  {
    return moveFilesTo(new File(paramString1), new File(paramString2));
  }
  
  public static boolean moveSystemDataFileTo(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      boolean bool = moveFileTo(new File(getSystemFilesPath(paramContext) + paramString1), new File(getSystemFilesPath(paramContext) + paramString2));
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean moveSystemDataFilesTo(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      boolean bool = moveFilesTo(new File(getSystemFilesPath(paramContext) + paramString1), new File(getSystemFilesPath(paramContext) + paramString2));
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static void openFileByApp(Context paramContext, String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    Object localObject = getFileExtension(paramString);
    if (ApplicationStone.getInstance().isSupportFileType_Dwg((String)localObject))
    {
      localObject = new Intent(paramContext, CadFilesActivity.class);
      ((Intent)localObject).putExtra("fileName", paramString);
      ((Intent)localObject).putExtra("isOtherApp", paramBoolean1);
      ((Intent)localObject).putExtra("isNewFile", paramBoolean2);
      ((Activity)paramContext).startActivityForResult((Intent)localObject, paramInt);
      ((Activity)paramContext).overridePendingTransition(2130968578, 2130968579);
      return;
    }
    if (ApplicationStone.getInstance().isSupportFileType_Font((String)localObject))
    {
      ApplicationStone.getInstance().showToastPublic(paramContext.getResources().getString(2131296382));
      return;
    }
    if ((((String)localObject).equalsIgnoreCase("png")) || (((String)localObject).equalsIgnoreCase("jpg")) || (((String)localObject).equalsIgnoreCase("bmp")))
    {
      localObject = new ArrayList();
      ((ArrayList)localObject).add(paramString);
      Intent localIntent = new Intent(paramContext, ImageShowActivity.class);
      localIntent.putStringArrayListExtra("photo_list_all", (ArrayList)localObject);
      paramContext.startActivity(localIntent);
      ((Activity)paramContext).overridePendingTransition(2130968578, 2130968579);
      ApplicationStone.getInstance().setRecentFile(paramString);
      return;
    }
    if (((String)localObject).equalsIgnoreCase("pdf"))
    {
      openFileBySystemApp(paramContext, paramString);
      ApplicationStone.getInstance().setRecentFile(paramString);
      return;
    }
    openFileBySystemApp(paramContext, paramString);
  }
  
  public static void openFileBySystemApp(Context paramContext, File paramFile)
  {
    String str = getFileMimeTypeFromFile(paramFile);
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(paramFile), str);
    paramContext.startActivity(localIntent);
  }
  
  public static void openFileBySystemApp(Context paramContext, String paramString)
  {
    openFileBySystemApp(paramContext, new File(paramString));
  }
  
  public static List<String> readAssetsFile2List(Context paramContext, String paramString)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      paramString = new BufferedReader(new InputStreamReader(paramContext.getResources().getAssets().open(paramString), "UTF-8"));
      for (;;)
      {
        String str = paramString.readLine();
        paramContext = localArrayList;
        if (str == null) {
          break;
        }
        localArrayList.add(str);
      }
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      paramContext = null;
    }
  }
  
  public static String readAssetsFile2String1(Context paramContext, String paramString)
  {
    String str = "";
    Object localObject = str;
    try
    {
      paramString = paramContext.getAssets().open(paramString);
      paramContext = str;
      if (paramString != null)
      {
        localObject = str;
        paramContext = new DataInputStream(paramString);
        localObject = str;
        byte[] arrayOfByte = new byte[paramContext.available()];
        localObject = str;
        paramContext.read(arrayOfByte);
        localObject = str;
        paramContext = EncodingUtils.getString(arrayOfByte, "UTF-8");
        localObject = paramContext;
        paramString.close();
      }
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return localObject;
  }
  
  public static String readAssetsFile2String2(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    String str = "";
    Object localObject = str;
    try
    {
      paramString = paramContext.getAssets().open(paramString);
      localObject = str;
      if (paramString != null)
      {
        localObject = str;
        paramContext = new BufferedReader(new InputStreamReader(paramString));
        for (;;)
        {
          localObject = str;
          if (!paramContext.ready()) {
            break;
          }
          localObject = str;
          localStringBuilder.append(paramContext.readLine() + "\n");
        }
      }
      return localObject;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    localObject = str;
    paramContext = EncodingUtils.getString(localStringBuilder.toString().getBytes(), "UTF-8");
    localObject = paramContext;
    paramString.close();
    return paramContext;
  }
  
  public static String readAssetsFile2String3(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    String str = "";
    Object localObject = str;
    try
    {
      paramString = paramContext.getAssets().open(paramString);
      localObject = str;
      if (paramString != null)
      {
        localObject = str;
        paramContext = new BufferedReader(new InputStreamReader(paramString, "UTF-8"));
        for (;;)
        {
          localObject = str;
          if (!paramContext.ready()) {
            break;
          }
          localObject = str;
          localStringBuilder.append(paramContext.readLine() + "\n");
        }
      }
      return localObject;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    localObject = str;
    paramContext = localStringBuilder.toString();
    localObject = paramContext;
    paramString.close();
    return paramContext;
  }
  
  public static String readFileSdcardFile(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramString);
      str1 = str2;
      paramString = new byte[localFileInputStream.available()];
      str1 = str2;
      localFileInputStream.read(paramString);
      str1 = str2;
      paramString = EncodingUtils.getString(paramString, "UTF-8");
      str1 = paramString;
      localFileInputStream.close();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return str1;
  }
  
  public static String readFileValue(String paramString)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream;
    try
    {
      if (new File(paramString).exists())
      {
        localByteArrayOutputStream = new ByteArrayOutputStream(1024);
        paramString = new FileInputStream(paramString);
        byte[] arrayOfByte = new byte['Ѐ'];
        for (;;)
        {
          int i = paramString.read(arrayOfByte);
          if (i == -1) {
            break;
          }
          localByteArrayOutputStream.write(arrayOfByte, 0, i);
        }
      }
      return "";
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      Log.e(TAG, "readFileValue is Error! ErrorCode = " + paramString.getMessage());
    }
    localByteArrayOutputStream.close();
    paramString.close();
    paramString = new String(localByteArrayOutputStream.toByteArray());
    return paramString;
  }
  
  public static String readSystemFile(Context paramContext, String paramString)
  {
    ByteArrayOutputStream localByteArrayOutputStream;
    try
    {
      paramContext = paramContext.openFileInput(paramString);
      paramString = new byte['Ѐ'];
      localByteArrayOutputStream = new ByteArrayOutputStream();
      while (paramContext.read(paramString) != -1) {
        localByteArrayOutputStream.write(paramString, 0, paramString.length);
      }
      paramContext.close();
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return "";
    }
    localByteArrayOutputStream.close();
    paramContext = new String(localByteArrayOutputStream.toByteArray());
    return paramContext;
  }
  
  public static void readXMLFromPull(InputStream paramInputStream)
  {
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    for (;;)
    {
      try
      {
        localXmlPullParser.setInput(paramInputStream, "UTF-8");
        i = localXmlPullParser.getEventType();
      }
      catch (Exception paramInputStream)
      {
        paramInputStream.printStackTrace();
        return;
      }
      int i = localXmlPullParser.next();
      if (localXmlPullParser.getName().equalsIgnoreCase("person"))
      {
        new Integer(localXmlPullParser.getAttributeValue(null, "id")).intValue();
        localXmlPullParser.getAttributeValue(null, "sex");
        while (i == 1)
        {
          paramInputStream.close();
          return;
        }
        switch (i)
        {
        }
      }
    }
  }
  
  public static boolean renameFile(File paramFile, String paramString)
  {
    if ((!paramFile.exists()) || (paramFile.getParentFile() == null)) {
      return false;
    }
    return paramFile.renameTo(new File(paramFile.getParentFile(), paramString));
  }
  
  public static boolean renameFile(String paramString1, String paramString2)
  {
    return renameFile(new File(paramString1), paramString2);
  }
  
  public static boolean renameSystemDataFile(Context paramContext, String paramString1, String paramString2)
  {
    return new File(getSystemFilesPath(paramContext) + paramString1).renameTo(new File(getSystemFilesPath(paramContext) + paramString2));
  }
  
  public static void scanMediaFileDataBase(Context paramContext, String paramString)
  {
    if (new File(paramString).isFile()) {}
    for (String str = new File(paramString).getParentFile().getPath();; str = paramString)
    {
      MediaScannerConnection.scanFile(paramContext, new String[] { paramString, str }, null, null);
      return;
    }
  }
  
  public static void sortArrayList(List<?> paramList, String paramString, final boolean paramBoolean)
  {
    Collections.sort(paramList, new Comparator()
    {
      public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        if (TextUtils.isEmpty(this.val$sortKey)) {}
        do
        {
          do
          {
            return -1;
            if (!(paramAnonymousObject1 instanceof Map)) {
              break;
            }
            paramAnonymousObject1 = (Map)paramAnonymousObject1;
            paramAnonymousObject2 = (Map)paramAnonymousObject2;
          } while ((!paramAnonymousObject1.containsKey(this.val$sortKey)) || (!paramAnonymousObject2.containsKey(this.val$sortKey)));
          if (paramBoolean) {
            return paramAnonymousObject1.get(this.val$sortKey).toString().compareToIgnoreCase(paramAnonymousObject2.get(this.val$sortKey).toString());
          }
          return paramAnonymousObject2.get(this.val$sortKey).toString().compareToIgnoreCase(paramAnonymousObject1.get(this.val$sortKey).toString());
          if ((paramAnonymousObject1 instanceof String))
          {
            paramAnonymousObject1 = (String)paramAnonymousObject1;
            paramAnonymousObject2 = (String)paramAnonymousObject2;
            if (paramBoolean) {
              return paramAnonymousObject1.compareToIgnoreCase(paramAnonymousObject2);
            }
            return paramAnonymousObject2.compareToIgnoreCase(paramAnonymousObject1);
          }
        } while (!(paramAnonymousObject1 instanceof File));
        paramAnonymousObject1 = (File)paramAnonymousObject1;
        paramAnonymousObject2 = (File)paramAnonymousObject2;
        paramAnonymousObject1 = paramAnonymousObject1.getName();
        paramAnonymousObject2 = paramAnonymousObject2.getName();
        if (paramBoolean) {
          return paramAnonymousObject1.compareToIgnoreCase(paramAnonymousObject2);
        }
        return paramAnonymousObject2.compareToIgnoreCase(paramAnonymousObject1);
      }
    });
  }
  
  public static void sortFileModelList(List<FileModel> paramList, String paramString, final boolean paramBoolean)
  {
    Collections.sort(paramList, new Comparator()
    {
      public int compare(FileModel paramAnonymousFileModel1, FileModel paramAnonymousFileModel2)
      {
        if (TextUtils.isEmpty(this.val$sortKey)) {}
        do
        {
          return 0;
          if (this.val$sortKey.equalsIgnoreCase("fileName"))
          {
            paramAnonymousFileModel1 = paramAnonymousFileModel1.getFileName();
            paramAnonymousFileModel2 = paramAnonymousFileModel2.getFileName();
            if (paramBoolean) {
              return paramAnonymousFileModel1.compareToIgnoreCase(paramAnonymousFileModel2);
            }
            return paramAnonymousFileModel2.compareToIgnoreCase(paramAnonymousFileModel1);
          }
          if (this.val$sortKey.equalsIgnoreCase("fileType"))
          {
            paramAnonymousFileModel1 = paramAnonymousFileModel1.getFileType();
            paramAnonymousFileModel2 = paramAnonymousFileModel2.getFileType();
            if (paramBoolean) {
              return paramAnonymousFileModel1.compareToIgnoreCase(paramAnonymousFileModel2);
            }
            return paramAnonymousFileModel2.compareToIgnoreCase(paramAnonymousFileModel1);
          }
          if (this.val$sortKey.equalsIgnoreCase("fileDate"))
          {
            paramAnonymousFileModel1 = Long.valueOf(paramAnonymousFileModel1.getFileDate());
            paramAnonymousFileModel2 = Long.valueOf(paramAnonymousFileModel2.getFileDate());
            if (paramBoolean) {
              return paramAnonymousFileModel1.compareTo(paramAnonymousFileModel2);
            }
            return paramAnonymousFileModel2.compareTo(paramAnonymousFileModel1);
          }
        } while (!this.val$sortKey.equalsIgnoreCase("fileSize"));
        paramAnonymousFileModel1 = Long.valueOf(paramAnonymousFileModel1.getFileSize());
        paramAnonymousFileModel2 = Long.valueOf(paramAnonymousFileModel2.getFileSize());
        if (paramBoolean) {
          return paramAnonymousFileModel1.compareTo(paramAnonymousFileModel2);
        }
        return paramAnonymousFileModel2.compareTo(paramAnonymousFileModel1);
      }
    });
  }
  
  public static Map<Integer, String> sortMapByKey(Map<Integer, String> paramMap)
  {
    if ((paramMap == null) || (paramMap.isEmpty())) {
      return null;
    }
    TreeMap localTreeMap = new TreeMap(new Comparator()
    {
      public int compare(Integer paramAnonymousInteger1, Integer paramAnonymousInteger2)
      {
        try
        {
          i = FileUtils.getInt(String.valueOf(paramAnonymousInteger1));
          j = FileUtils.getInt(String.valueOf(paramAnonymousInteger2));
          return i - j;
        }
        catch (Exception paramAnonymousInteger1)
        {
          for (;;)
          {
            int i = 0;
            int j = 0;
          }
        }
      }
    });
    localTreeMap.putAll(paramMap);
    return localTreeMap;
  }
  
  public static Map<String, String> sortMapByValue(Map<String, String> paramMap)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      paramMap = new ArrayList(paramMap.entrySet());
      Collections.sort(paramMap, new Comparator()
      {
        public int compare(Map.Entry<String, String> paramAnonymousEntry1, Map.Entry<String, String> paramAnonymousEntry2)
        {
          try
          {
            i = FileUtils.getInt((String)paramAnonymousEntry1.getValue());
            j = FileUtils.getInt((String)paramAnonymousEntry2.getValue());
            return j - i;
          }
          catch (NumberFormatException paramAnonymousEntry1)
          {
            for (;;)
            {
              int i = 0;
              int j = 0;
            }
          }
        }
      });
      paramMap = paramMap.iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localLinkedHashMap.put(localEntry.getKey(), localEntry.getValue());
      }
    }
    return localLinkedHashMap;
  }
  
  public static void startAnotherApp(Context paramContext, String paramString1, String paramString2)
  {
    if (checkPackageNameExists(paramContext, paramString1))
    {
      if (TextUtils.isEmpty(paramString2))
      {
        paramString2 = paramContext.getPackageManager();
        new Intent();
        paramContext.startActivity(paramString2.getLaunchIntentForPackage(paramString1));
        return;
      }
      Intent localIntent = new Intent();
      localIntent.setComponent(new ComponentName(paramString1, paramString2));
      ((Activity)paramContext).startActivityForResult(localIntent, 1000);
      return;
    }
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString1)));
  }
  
  /* Error */
  public static boolean writeFileFromStream(String paramString, InputStream paramInputStream)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore_3
    //   8: aconst_null
    //   9: astore 7
    //   11: aconst_null
    //   12: astore 5
    //   14: new 477	java/io/FileOutputStream
    //   17: dup
    //   18: new 70	java/io/File
    //   21: dup
    //   22: aload_0
    //   23: invokespecial 73	java/io/File:<init>	(Ljava/lang/String;)V
    //   26: iconst_0
    //   27: invokespecial 1521	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   30: astore_0
    //   31: new 1523	java/io/BufferedOutputStream
    //   34: dup
    //   35: aload_0
    //   36: invokespecial 1526	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   39: astore_3
    //   40: sipush 4096
    //   43: newarray byte
    //   45: astore 4
    //   47: aload_1
    //   48: aload 4
    //   50: invokevirtual 486	java/io/InputStream:read	([B)I
    //   53: istore_2
    //   54: iload_2
    //   55: iconst_m1
    //   56: if_icmpeq +38 -> 94
    //   59: aload_3
    //   60: aload 4
    //   62: iconst_0
    //   63: iload_2
    //   64: invokevirtual 1527	java/io/BufferedOutputStream:write	([BII)V
    //   67: goto -20 -> 47
    //   70: astore 5
    //   72: aload_3
    //   73: astore_1
    //   74: aload_1
    //   75: astore_3
    //   76: aload_0
    //   77: astore 4
    //   79: aload 5
    //   81: invokevirtual 226	java/lang/Exception:printStackTrace	()V
    //   84: aload_1
    //   85: invokevirtual 1528	java/io/BufferedOutputStream:close	()V
    //   88: aload_0
    //   89: invokevirtual 519	java/io/FileOutputStream:close	()V
    //   92: iconst_0
    //   93: ireturn
    //   94: aload_3
    //   95: invokevirtual 1529	java/io/BufferedOutputStream:flush	()V
    //   98: aload_3
    //   99: invokevirtual 1528	java/io/BufferedOutputStream:close	()V
    //   102: aload_0
    //   103: invokevirtual 519	java/io/FileOutputStream:close	()V
    //   106: aload_3
    //   107: invokevirtual 1528	java/io/BufferedOutputStream:close	()V
    //   110: aload_0
    //   111: invokevirtual 519	java/io/FileOutputStream:close	()V
    //   114: iconst_1
    //   115: ireturn
    //   116: astore_0
    //   117: aload_0
    //   118: invokevirtual 226	java/lang/Exception:printStackTrace	()V
    //   121: iconst_1
    //   122: ireturn
    //   123: astore_0
    //   124: aload_0
    //   125: invokevirtual 226	java/lang/Exception:printStackTrace	()V
    //   128: iconst_0
    //   129: ireturn
    //   130: astore_0
    //   131: aload_3
    //   132: invokevirtual 1528	java/io/BufferedOutputStream:close	()V
    //   135: aload 4
    //   137: invokevirtual 519	java/io/FileOutputStream:close	()V
    //   140: aload_0
    //   141: athrow
    //   142: astore_1
    //   143: aload_1
    //   144: invokevirtual 226	java/lang/Exception:printStackTrace	()V
    //   147: goto -7 -> 140
    //   150: astore_1
    //   151: aload 7
    //   153: astore_3
    //   154: aload_0
    //   155: astore 4
    //   157: aload_1
    //   158: astore_0
    //   159: goto -28 -> 131
    //   162: astore_1
    //   163: aload_0
    //   164: astore 4
    //   166: aload_1
    //   167: astore_0
    //   168: goto -37 -> 131
    //   171: astore_0
    //   172: aload 5
    //   174: astore_1
    //   175: aload_0
    //   176: astore 5
    //   178: aload 6
    //   180: astore_0
    //   181: goto -107 -> 74
    //   184: astore_3
    //   185: aload 5
    //   187: astore_1
    //   188: aload_3
    //   189: astore 5
    //   191: goto -117 -> 74
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	194	0	paramString	String
    //   0	194	1	paramInputStream	InputStream
    //   53	11	2	i	int
    //   7	147	3	localObject1	Object
    //   184	5	3	localException1	Exception
    //   1	164	4	localObject2	Object
    //   12	1	5	localObject3	Object
    //   70	103	5	localException2	Exception
    //   176	14	5	localObject4	Object
    //   4	175	6	localObject5	Object
    //   9	143	7	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   40	47	70	java/lang/Exception
    //   47	54	70	java/lang/Exception
    //   59	67	70	java/lang/Exception
    //   94	106	70	java/lang/Exception
    //   106	114	116	java/lang/Exception
    //   84	92	123	java/lang/Exception
    //   14	31	130	finally
    //   79	84	130	finally
    //   131	140	142	java/lang/Exception
    //   31	40	150	finally
    //   40	47	162	finally
    //   47	54	162	finally
    //   59	67	162	finally
    //   94	106	162	finally
    //   14	31	171	java/lang/Exception
    //   31	40	184	java/lang/Exception
  }
  
  /* Error */
  public static boolean writeFileFromString(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: new 1532	java/io/FileWriter
    //   7: dup
    //   8: new 70	java/io/File
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 73	java/io/File:<init>	(Ljava/lang/String;)V
    //   16: invokespecial 1533	java/io/FileWriter:<init>	(Ljava/io/File;)V
    //   19: astore_0
    //   20: aload_0
    //   21: aload_1
    //   22: invokevirtual 1535	java/io/FileWriter:write	(Ljava/lang/String;)V
    //   25: aload_0
    //   26: invokevirtual 1536	java/io/FileWriter:close	()V
    //   29: aload_0
    //   30: invokevirtual 1536	java/io/FileWriter:close	()V
    //   33: iconst_1
    //   34: ireturn
    //   35: astore_0
    //   36: aload_0
    //   37: invokevirtual 226	java/lang/Exception:printStackTrace	()V
    //   40: iconst_1
    //   41: ireturn
    //   42: astore_1
    //   43: aload_3
    //   44: astore_0
    //   45: aload_0
    //   46: astore_2
    //   47: aload_1
    //   48: invokevirtual 226	java/lang/Exception:printStackTrace	()V
    //   51: aload_0
    //   52: invokevirtual 1536	java/io/FileWriter:close	()V
    //   55: iconst_0
    //   56: ireturn
    //   57: astore_0
    //   58: aload_0
    //   59: invokevirtual 226	java/lang/Exception:printStackTrace	()V
    //   62: iconst_0
    //   63: ireturn
    //   64: astore_0
    //   65: aload_2
    //   66: invokevirtual 1536	java/io/FileWriter:close	()V
    //   69: aload_0
    //   70: athrow
    //   71: astore_1
    //   72: aload_1
    //   73: invokevirtual 226	java/lang/Exception:printStackTrace	()V
    //   76: goto -7 -> 69
    //   79: astore_1
    //   80: aload_0
    //   81: astore_2
    //   82: aload_1
    //   83: astore_0
    //   84: goto -19 -> 65
    //   87: astore_1
    //   88: goto -43 -> 45
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	paramString1	String
    //   0	91	1	paramString2	String
    //   1	81	2	str	String
    //   3	41	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   29	33	35	java/lang/Exception
    //   4	20	42	java/lang/Exception
    //   51	55	57	java/lang/Exception
    //   4	20	64	finally
    //   47	51	64	finally
    //   65	69	71	java/lang/Exception
    //   20	29	79	finally
    //   20	29	87	java/lang/Exception
  }
  
  public static void writeFileSdcardFile(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new FileOutputStream(paramString1);
      paramString1.write(paramString2.getBytes());
      paramString1.close();
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static boolean writeSystemFile(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.openFileOutput(paramString1, 2);
      paramContext.write(paramString2.getBytes());
      paramContext.flush();
      paramContext.close();
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public String getInnerSDCardPath()
  {
    return Environment.getExternalStorageDirectory().getPath();
  }
  
  public List<String> getOuterSDCardPath()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      InputStreamReader localInputStreamReader = new InputStreamReader(Runtime.getRuntime().exec("mount").getInputStream());
      BufferedReader localBufferedReader = new BufferedReader(localInputStreamReader);
      for (;;)
      {
        String str = localBufferedReader.readLine();
        if (str == null) {
          break;
        }
        if (str.contains("extSdCard"))
        {
          str = str.split(" ")[1];
          if (new File(str).isDirectory()) {
            localArrayList.add(str);
          }
        }
      }
      localInputStreamReader.close();
      return localArrayList;
    }
    catch (Exception localException) {}
    return localArrayList;
  }
  
  public void getVideoFile(final List<Map<String, Object>> paramList, File paramFile)
  {
    paramFile.listFiles(new FileFilter()
    {
      public boolean accept(File paramAnonymousFile)
      {
        Object localObject = paramAnonymousFile.getName();
        int i = ((String)localObject).indexOf('.');
        if (i != -1)
        {
          localObject = ((String)localObject).substring(i);
          if ((((String)localObject).equalsIgnoreCase(".mp4")) || (((String)localObject).equalsIgnoreCase(".3gp")) || (((String)localObject).equalsIgnoreCase(".wmv")) || (((String)localObject).equalsIgnoreCase(".ts")) || (((String)localObject).equalsIgnoreCase(".rmvb")) || (((String)localObject).equalsIgnoreCase(".mov")) || (((String)localObject).equalsIgnoreCase(".m4v")) || (((String)localObject).equalsIgnoreCase(".avi")) || (((String)localObject).equalsIgnoreCase(".m3u8")) || (((String)localObject).equalsIgnoreCase(".3gpp")) || (((String)localObject).equalsIgnoreCase(".3gpp2")) || (((String)localObject).equalsIgnoreCase(".mkv")) || (((String)localObject).equalsIgnoreCase(".flv")) || (((String)localObject).equalsIgnoreCase(".divx")) || (((String)localObject).equalsIgnoreCase(".f4v")) || (((String)localObject).equalsIgnoreCase(".rm")) || (((String)localObject).equalsIgnoreCase(".asf")) || (((String)localObject).equalsIgnoreCase(".ram")) || (((String)localObject).equalsIgnoreCase(".mpg")) || (((String)localObject).equalsIgnoreCase(".v8")) || (((String)localObject).equalsIgnoreCase(".swf")) || (((String)localObject).equalsIgnoreCase(".m2v")) || (((String)localObject).equalsIgnoreCase(".asx")) || (((String)localObject).equalsIgnoreCase(".ra")) || (((String)localObject).equalsIgnoreCase(".ndivx")) || (((String)localObject).equalsIgnoreCase(".xvid")))
          {
            localObject = new HashMap();
            ((Map)localObject).put("name", paramAnonymousFile.getName());
            ((Map)localObject).put("path", paramAnonymousFile.getAbsolutePath());
            paramList.add(localObject);
            return true;
          }
        }
        else if (paramAnonymousFile.isDirectory())
        {
          FileUtils.this.getVideoFile(paramList, paramAnonymousFile);
        }
        return false;
      }
    });
  }
  
  public void writeXMLString()
  {
    XmlSerializer localXmlSerializer = Xml.newSerializer();
    StringWriter localStringWriter = new StringWriter();
    try
    {
      localXmlSerializer.setOutput(localStringWriter);
      localXmlSerializer.startDocument("UTF-8", null);
      localXmlSerializer.startTag("", "paralist");
      localXmlSerializer.startTag("", "para");
      localXmlSerializer.startTag("", "value");
      localXmlSerializer.text("Stonejxn");
      localXmlSerializer.endTag("", "value");
      localXmlSerializer.startTag("", "name");
      localXmlSerializer.text("account");
      localXmlSerializer.endTag("", "name");
      localXmlSerializer.endTag("", "para");
      localXmlSerializer.startTag("", "para");
      localXmlSerializer.startTag("", "value");
      localXmlSerializer.text("a123456789");
      localXmlSerializer.endTag("", "value");
      localXmlSerializer.startTag("", "name");
      localXmlSerializer.text("password");
      localXmlSerializer.endTag("", "name");
      localXmlSerializer.endTag("", "para");
      localXmlSerializer.endTag("", "paralist");
      localXmlSerializer.endDocument();
      debugTool.d("XML", localStringWriter.toString());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void writeXMLStringToXMLFile(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      writeSystemFile(paramContext, paramString1, paramString2);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static class ExtensionFileFilter
    implements FileFilter
  {
    private String[] extensions;
    private String isShow;
    private String strKeyword;
    
    public ExtensionFileFilter(String paramString1, String paramString2, String... paramVarArgs)
    {
      this.isShow = paramString1;
      this.extensions = paramVarArgs;
    }
    
    public boolean accept(File paramFile)
    {
      String str = paramFile.getName();
      if (paramFile.isDirectory()) {}
      for (;;)
      {
        return true;
        if ((this.extensions != null) && (this.extensions.length != 0))
        {
          Object localObject;
          if (this.isShow.equalsIgnoreCase("show"))
          {
            paramFile = this.extensions;
            j = paramFile.length;
            i = 0;
            for (;;)
            {
              if (i >= j) {
                break label128;
              }
              localObject = paramFile[i];
              if ((str.toLowerCase(Locale.getDefault()).endsWith(localObject.toLowerCase(Locale.getDefault()))) && ((TextUtils.isEmpty(this.strKeyword)) || (str.toLowerCase(Locale.getDefault()).contains(this.strKeyword.toLowerCase(Locale.getDefault()).trim())))) {
                break;
              }
              i += 1;
            }
            label128:
            return false;
          }
          paramFile = this.extensions;
          int j = paramFile.length;
          int i = 0;
          while (i < j)
          {
            localObject = paramFile[i];
            if (str.toLowerCase(Locale.getDefault()).endsWith(localObject.toLowerCase(Locale.getDefault()))) {
              if (!TextUtils.isEmpty(this.strKeyword))
              {
                if (str.toLowerCase(Locale.getDefault()).contains(this.strKeyword.toLowerCase(Locale.getDefault()).trim())) {
                  return false;
                }
              }
              else {
                return false;
              }
            }
            i += 1;
          }
        }
      }
    }
  }
  
  public static class FileComparator
    implements Comparator<Object>
  {
    private String sortKey;
    private boolean sortOrder;
    
    public FileComparator(String paramString, boolean paramBoolean)
    {
      this.sortKey = paramString;
      this.sortOrder = paramBoolean;
    }
    
    public int compare(Object paramObject1, Object paramObject2)
    {
      if (TextUtils.isEmpty(this.sortKey)) {}
      do
      {
        do
        {
          return -1;
          if (!(paramObject1 instanceof Map)) {
            break;
          }
          paramObject1 = (Map)paramObject1;
          paramObject2 = (Map)paramObject2;
        } while ((!paramObject1.containsKey(this.sortKey)) || (!paramObject2.containsKey(this.sortKey)));
        if (this.sortOrder) {
          return paramObject1.get(this.sortKey).toString().compareToIgnoreCase(paramObject2.get(this.sortKey).toString());
        }
        return paramObject2.get(this.sortKey).toString().compareToIgnoreCase(paramObject1.get(this.sortKey).toString());
        if ((paramObject1 instanceof String))
        {
          paramObject1 = (String)paramObject1;
          paramObject2 = (String)paramObject2;
          if (this.sortOrder) {
            return paramObject1.compareToIgnoreCase(paramObject2);
          }
          return paramObject2.compareToIgnoreCase(paramObject1);
        }
      } while (!(paramObject1 instanceof File));
      paramObject1 = (File)paramObject1;
      paramObject2 = (File)paramObject2;
      paramObject1 = paramObject1.getName();
      paramObject2 = paramObject2.getName();
      if (this.sortOrder) {
        return paramObject1.compareToIgnoreCase(paramObject2);
      }
      return paramObject2.compareToIgnoreCase(paramObject1);
    }
  }
  
  public static abstract interface OnFileListAllCallback
  {
    public abstract void SearchFileListAll(List<Map<String, Object>> paramList);
  }
  
  public static abstract interface OnFileListCurrentLevelCallback
  {
    public abstract void SearchFileListCurrentLevel(List<Map<String, Object>> paramList);
  }
}
