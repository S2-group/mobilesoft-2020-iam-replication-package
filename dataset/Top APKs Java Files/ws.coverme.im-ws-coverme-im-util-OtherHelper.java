package ws.coverme.im.util;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import ws.coverme.im.JucoreAdp.Types.DataStructs.AppVersion;
import ws.coverme.im.clouddll.dbmanager.DataBaseTransferManager;
import ws.coverme.im.dll.AlbumTableOperation;
import ws.coverme.im.dll.LoginFailedLogTableOperation;
import ws.coverme.im.dll.LoginSuccessLogTableOperation;
import ws.coverme.im.dll.SettingTableOperation;
import ws.coverme.im.dll.SharedPreferencesManager;
import ws.coverme.im.model.FileLogger;
import ws.coverme.im.model.KexinData;
import ws.coverme.im.model.albums.Album;
import ws.coverme.im.model.constant.AppConstants;
import ws.coverme.im.model.hide_app.HideAppUtil;
import ws.coverme.im.model.location.MyLocation;
import ws.coverme.im.model.login_logs.LoginFailedLog;
import ws.coverme.im.model.login_logs.LoginSuccessLog;
import ws.coverme.im.model.settings.Security;
import ws.coverme.im.model.user.User;
import ws.coverme.im.service.BCMsg;
import ws.coverme.im.service.NetWorkManager;
import ws.coverme.im.ui.KexinApp;
import ws.coverme.im.ui.chat.async.FileUtils;
import ws.coverme.im.ui.others.advancedversion.util.PremiumUtil;
import ws.coverme.im.ui.view.MyDialog;

public class OtherHelper
{
  public OtherHelper() {}
  
  public static void CheckToDelLogFile()
  {
    FileLogger.deleteSDcardExpiredLog(AppConstants.FIRST_LEVEL_LOG, "yyyy-MM-dd", 7);
    FileLogger.deleteSDcardExpiredLog(AppConstants.FIRST_LEVEL_LOG, "yyyy-MM-dd HHmmss", 7);
  }
  
  public static void backupDb(Context paramContext, boolean paramBoolean)
  {
    Object localObject = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    if (paramBoolean) {}
    for (localObject = ((SimpleDateFormat)localObject).format(new Date()) + ".s";; localObject = ((SimpleDateFormat)localObject).format(new Date()) + "-T.s")
    {
      localObject = AppConstants.SECOND_LEVEL_BACKUP_KXDATA + (String)localObject;
      makeDir(AppConstants.SECOND_LEVEL_BACKUP_KXDATA);
      FileLogger.printStack();
      new DBBackupTask(paramContext).execute(new String[] { "backupDatabase", localObject });
      return;
    }
  }
  
  public static void backupDbAperiodically(Context paramContext)
  {
    backupDb(paramContext, true);
  }
  
  public static void backupDbPeriodically(Context paramContext)
  {
    if (!checkTodayDbBackupExitence()) {
      backupDb(paramContext, true);
    }
  }
  
  public static void backupMigrateLog(Context paramContext)
  {
    paramContext = SettingTableOperation.getStringSetting(SharedPreferencesManager.DATA_MIGRATE, paramContext);
    FileUtils.writeMigrate(AppConstants.FIRST_LEVEL_MIGRATE, "migrate.log", paramContext);
  }
  
  public static void backupMsgCloudDb(Context paramContext, boolean paramBoolean)
  {
    Object localObject = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    if (paramBoolean) {}
    for (localObject = ((SimpleDateFormat)localObject).format(new Date()) + ".clouds";; localObject = ((SimpleDateFormat)localObject).format(new Date()) + "-T.clouds")
    {
      localObject = AppConstants.SECOND_LEVEL_BACKUP_KXDATA + (String)localObject;
      makeDir(AppConstants.SECOND_LEVEL_BACKUP_KXDATA);
      FileLogger.printStack();
      new DBCloudBackupTask(paramContext).execute(new String[] { "backupDatabase", localObject });
      return;
    }
  }
  
  public static boolean checkAppExistence(String paramString1, String paramString2, Context paramContext)
  {
    try
    {
      localObject = paramContext.getPackageManager().getPackageInfo(paramString1, 0);
      if (localObject == null) {
        return false;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Object localObject;
      do
      {
        for (;;)
        {
          localObject = null;
          localNameNotFoundException.printStackTrace();
        }
        if (StrUtil.isNull(paramString2) == true) {
          return true;
        }
        localObject = new Intent();
        ((Intent)localObject).setClassName(paramString1, paramString2);
      } while (paramContext.getPackageManager().resolveActivity((Intent)localObject, 0) == null);
    }
    return true;
  }
  
  public static boolean checkCoreConfig()
  {
    Object localObject = KexinData.getInstance().getContext().getDir("config", 0);
    localObject = ((File)localObject).getAbsolutePath() + "/coreconfigex.bin";
    String str = AppConstants.FIRST_LEVEL_BACKUP + "config.back";
    File localFile1 = new File((String)localObject);
    File localFile2 = new File(str);
    makeDir(AppConstants.FIRST_LEVEL_BACKUP);
    if (!chekCoreConfigAvailable((String)localObject))
    {
      CMTracer.i("checkCoreConfig", "cofig corrupt!");
      if (!chekCoreConfigAvailable(str))
      {
        CMTracer.i("checkCoreConfig", "backup corrupt! restore from db");
        restoreCoreConfigFromDB(str);
      }
      if (chekCoreConfigAvailable(str)) {
        CMTracer.i("checkCoreConfig", "backup ok try to restore");
      }
    }
    else
    {
      try
      {
        copyFile(localFile2, localFile1);
        return true;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          localIOException.printStackTrace();
        }
      }
    }
    CMTracer.e("checkCoreConfig", "backup still corrupt, try to register");
    return false;
  }
  
  public static boolean checkGps(Context paramContext)
  {
    paramContext = (LocationManager)paramContext.getSystemService("location");
    return (paramContext.isProviderEnabled("gps")) && (paramContext.isProviderEnabled("network"));
  }
  
  public static void checkHasDefaultAlbum(Context paramContext)
  {
    boolean bool = AlbumTableOperation.checkHasDefaultAlbum(paramContext, 1);
    int i = KexinData.getInstance().getCurrentAuthorityId();
    Object localObject;
    if (!bool)
    {
      localObject = new Album();
      ((Album)localObject).albumName = "Default Album";
      ((Album)localObject).albumType = 1;
      ((Album)localObject).authorityId = i;
      ((Album)localObject).defaultFlag = 1;
      AlbumTableOperation.saveAlbum((Album)localObject, paramContext);
      localObject = AppConstants.THIRD_LEVEL_IMAGES_HIDDEN_THUMBNAILS;
      if (!new File((String)localObject).exists()) {
        new File((String)localObject).mkdirs();
      }
    }
    if (!AlbumTableOperation.checkHasDefaultAlbum(paramContext, 4))
    {
      localObject = new Album();
      ((Album)localObject).albumName = "Default Video Album";
      ((Album)localObject).albumType = 4;
      ((Album)localObject).authorityId = i;
      ((Album)localObject).defaultFlag = 1;
      AlbumTableOperation.saveAlbum((Album)localObject, paramContext);
      paramContext = AppConstants.THIRD_LEVEL_VIDEO_HIDDEN_THUMBNAILS;
      localObject = AppConstants.SECOND_LEVEL_VIDEO_TEMP;
      if (!new File(paramContext).exists()) {
        new File(paramContext).mkdirs();
      }
      if (!new File((String)localObject).exists()) {
        new File((String)localObject).mkdirs();
      }
    }
    DataBaseTransferManager.initDefaultRecord(i);
    DataBaseTransferManager.initDefautDoc(i);
  }
  
  public static boolean checkNetworkStatus(Context paramContext)
  {
    return checkNetworkStatus(paramContext, false);
  }
  
  public static boolean checkNetworkStatus(Context paramContext, boolean paramBoolean)
  {
    if (KexinApp.isAppVersionTooLow) {
      return false;
    }
    int j = 0;
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    int i;
    if (paramBoolean == true)
    {
      if (localNetworkInfo == null) {
        break label155;
      }
      CMTracer.d("checkNetworkStatus", String.format("activeNetInfo type(%d) typeName(%s) isConnected(%b) subTypeName(%s)", new Object[] { Integer.valueOf(localNetworkInfo.getType()), localNetworkInfo.getTypeName(), Boolean.valueOf(localNetworkInfo.isConnected()), localNetworkInfo.getSubtypeName() }));
      i = j;
      if (localNetworkInfo.isConnected())
      {
        if ((localNetworkInfo.getType() != 0) && (localNetworkInfo.getType() != 6)) {
          break label139;
        }
        i = 16;
      }
    }
    for (;;)
    {
      NetWorkManager.checkNetworkChanged(i);
      if ((localNetworkInfo == null) || (!localNetworkInfo.isAvailable())) {
        break;
      }
      return true;
      label139:
      i = j;
      if (localNetworkInfo.getType() == 1)
      {
        i = 1;
        continue;
        label155:
        CMTracer.d("checkNetworkStatus", String.format("Network become not reachable", new Object[0]));
        i = 0;
        BCMsg.send(BCMsg.ACTION_CONNECT_STATE_NOREACH, paramContext);
      }
    }
  }
  
  public static boolean checkNetworkStatusForInitAndGiveMsg(Context paramContext)
  {
    if (!checkNetworkStatus(paramContext))
    {
      MyDialog localMyDialog = new MyDialog(paramContext);
      localMyDialog.setTitle(paramContext.getResources().getString(2131165905));
      localMyDialog.setMessage(paramContext.getResources().getString(2131165906));
      localMyDialog.setSinglePositiveButton(paramContext.getResources().getString(2131165364), new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView) {}
      });
      localMyDialog.show();
      return false;
    }
    return true;
  }
  
  public static void checkToDelPeriodicDbBackup()
  {
    FileLogger.deleteExpiredBackupFile(AppConstants.SECOND_LEVEL_BACKUP_KXDATA, "yyyy-MM-dd HH-mm-ss", 7);
  }
  
  public static boolean checkTodayDbBackupExitence()
  {
    return FileLogger.deleteExpiredBackupFile(AppConstants.SECOND_LEVEL_BACKUP_KXDATA, "yyyy-MM-dd HH-mm-ss", 7);
  }
  
  /* Error */
  public static boolean chekCoreConfigAvailable(String paramString)
  {
    // Byte code:
    //   0: bipush 20
    //   2: newarray byte
    //   4: astore 5
    //   6: iconst_0
    //   7: istore_1
    //   8: new 421	java/io/FileInputStream
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 422	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   16: astore_0
    //   17: aload_0
    //   18: aload 5
    //   20: invokevirtual 426	java/io/FileInputStream:read	([B)I
    //   23: istore_2
    //   24: iload_2
    //   25: istore_1
    //   26: iload_1
    //   27: bipush 20
    //   29: if_icmpeq +34 -> 63
    //   32: aload_0
    //   33: invokevirtual 429	java/io/FileInputStream:close	()V
    //   36: iconst_0
    //   37: ireturn
    //   38: astore_0
    //   39: aload_0
    //   40: invokevirtual 430	java/io/FileNotFoundException:printStackTrace	()V
    //   43: iconst_0
    //   44: ireturn
    //   45: astore 6
    //   47: aload 6
    //   49: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   52: goto -26 -> 26
    //   55: astore_0
    //   56: aload_0
    //   57: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   60: goto -24 -> 36
    //   63: lconst_0
    //   64: lstore_3
    //   65: bipush 7
    //   67: istore_1
    //   68: iload_1
    //   69: iflt +25 -> 94
    //   72: lload_3
    //   73: bipush 8
    //   75: lshl
    //   76: aload 5
    //   78: iload_1
    //   79: baload
    //   80: sipush 255
    //   83: iand
    //   84: i2l
    //   85: lor
    //   86: lstore_3
    //   87: iload_1
    //   88: iconst_1
    //   89: isub
    //   90: istore_1
    //   91: goto -23 -> 68
    //   94: iconst_0
    //   95: istore_1
    //   96: bipush 19
    //   98: istore_2
    //   99: iload_2
    //   100: bipush 16
    //   102: if_icmplt +24 -> 126
    //   105: iload_1
    //   106: bipush 8
    //   108: ishl
    //   109: aload 5
    //   111: iload_2
    //   112: baload
    //   113: sipush 255
    //   116: iand
    //   117: ior
    //   118: istore_1
    //   119: iload_2
    //   120: iconst_1
    //   121: isub
    //   122: istore_2
    //   123: goto -24 -> 99
    //   126: iload_1
    //   127: ifle +9 -> 136
    //   130: iload_1
    //   131: bipush 20
    //   133: if_icmple +17 -> 150
    //   136: aload_0
    //   137: invokevirtual 429	java/io/FileInputStream:close	()V
    //   140: iconst_0
    //   141: ireturn
    //   142: astore_0
    //   143: aload_0
    //   144: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   147: goto -7 -> 140
    //   150: iload_1
    //   151: newarray byte
    //   153: astore 5
    //   155: aload_0
    //   156: aload 5
    //   158: invokevirtual 426	java/io/FileInputStream:read	([B)I
    //   161: istore_2
    //   162: aload_0
    //   163: invokevirtual 429	java/io/FileInputStream:close	()V
    //   166: iload_2
    //   167: iload_1
    //   168: if_icmpeq +12 -> 180
    //   171: iconst_0
    //   172: ireturn
    //   173: astore_0
    //   174: aload_0
    //   175: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   178: iconst_0
    //   179: ireturn
    //   180: new 88	java/lang/String
    //   183: dup
    //   184: aload 5
    //   186: invokespecial 433	java/lang/String:<init>	([B)V
    //   189: astore_0
    //   190: aload_0
    //   191: lload_3
    //   192: invokestatic 436	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   195: invokevirtual 440	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   198: ifne +8 -> 206
    //   201: iconst_0
    //   202: ireturn
    //   203: astore_0
    //   204: iconst_0
    //   205: ireturn
    //   206: iconst_1
    //   207: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	208	0	paramString	String
    //   7	162	1	i	int
    //   23	146	2	j	int
    //   64	128	3	l	long
    //   4	181	5	arrayOfByte	byte[]
    //   45	3	6	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   8	17	38	java/io/FileNotFoundException
    //   17	24	45	java/io/IOException
    //   32	36	55	java/io/IOException
    //   136	140	142	java/io/IOException
    //   155	166	173	java/io/IOException
    //   180	190	203	java/lang/Exception
  }
  
  public static void clearCoverMe2CmnBackup()
  {
    clearDir(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_BCL);
    clearDir(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_BTL);
    clearDir(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_MSG);
  }
  
  public static boolean clearDir(String paramString)
  {
    boolean bool = false;
    for (;;)
    {
      try
      {
        paramString = new File(paramString).listFiles();
        if (paramString != null) {
          continue;
        }
        return true;
      }
      catch (Exception paramString)
      {
        Object localObject;
        paramString.printStackTrace();
        continue;
        int i = 0;
        continue;
      }
      if (i >= paramString.length) {
        continue;
      }
      localObject = paramString[i];
      if (localObject.isFile()) {
        localObject.delete();
      }
      i += 1;
    }
    bool = true;
    return bool;
  }
  
  public static void clearDirRecursively(File paramFile)
  {
    if (paramFile.isFile()) {
      paramFile.delete();
    }
    while (!paramFile.isDirectory()) {
      return;
    }
    File[] arrayOfFile = paramFile.listFiles();
    if ((arrayOfFile == null) || (arrayOfFile.length == 0))
    {
      CMTracer.i("clearDirRecursively", paramFile.getAbsolutePath() + " is null or len is zero ");
      paramFile.delete();
      return;
    }
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      clearDirRecursively(arrayOfFile[i]);
      i += 1;
    }
    paramFile.delete();
  }
  
  public static void clearDirRecursivelyKeepFolder(File paramFile)
  {
    if (paramFile.isFile())
    {
      paramFile.delete();
      CMTracer.i("clearDirRecursivelyKeepFolder", "delelte file " + paramFile.getAbsolutePath());
    }
    while (!paramFile.isDirectory()) {
      return;
    }
    File[] arrayOfFile = paramFile.listFiles();
    if ((arrayOfFile == null) || (arrayOfFile.length == 0))
    {
      CMTracer.i("clearDirRecursivelyKeepFolder", paramFile.getAbsolutePath() + " is null or len is zero ");
      paramFile.delete();
      return;
    }
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      clearDirRecursivelyKeepFolder(arrayOfFile[i]);
      i += 1;
    }
    CMTracer.i("clearDirRecursivelyKeepFolder", "not delelte folder " + paramFile.getAbsolutePath());
  }
  
  public static void copyDBToLogFolder(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getDatabasePath(paramString2);
    paramString1 = new File(AppConstants.FIRST_LEVEL_LOG + paramString1);
    try
    {
      copyFile(paramContext, paramString1);
      return;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean copyFile(File paramFile1, File paramFile2)
    throws IOException
  {
    if ((paramFile1.isDirectory()) || (paramFile2.isDirectory())) {}
    while (paramFile1.getAbsolutePath().equals(paramFile2.getAbsolutePath())) {
      return false;
    }
    paramFile1 = new FileInputStream(paramFile1);
    paramFile2 = new FileOutputStream(paramFile2);
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramFile1.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      paramFile2.write(arrayOfByte, 0, i);
    }
    paramFile2.flush();
    paramFile2.close();
    paramFile1.close();
    return true;
  }
  
  public static boolean copyFile(File paramFile1, File paramFile2, CopyFileCallback paramCopyFileCallback)
  {
    bool2 = false;
    if ((paramFile1.isDirectory()) || (paramFile2.isDirectory()))
    {
      if (paramCopyFileCallback != null) {
        paramCopyFileCallback.over(1);
      }
      return false;
    }
    Object localObject3 = null;
    Object localObject2 = null;
    for (;;)
    {
      try
      {
        localObject1 = new FileInputStream(paramFile1);
      }
      catch (Exception paramFile2)
      {
        label157:
        paramFile2 = localObject3;
      }
      for (;;)
      {
        try
        {
          paramFile2 = new FileOutputStream(paramFile2);
          localObject2 = localObject1;
          localObject1 = paramFile2;
          paramFile2 = localObject2;
          bool1 = bool2;
          if (!bool2)
          {
            long l = paramFile1.length() / 1024L;
            paramFile1 = new byte['Ѐ'];
            try
            {
              for (;;)
              {
                i = paramFile2.read(paramFile1);
                bool1 = bool2;
                if (i == -1) {
                  break;
                }
                ((FileOutputStream)localObject1).write(paramFile1, 0, i);
                if (paramCopyFileCallback != null) {
                  paramCopyFileCallback.updateProgress(l, 1);
                }
              }
              bool2 = bool1;
            }
            catch (Exception paramFile1)
            {
              bool1 = true;
            }
          }
          if (localObject1 == null) {}
        }
        catch (Exception paramFile2)
        {
          paramFile2 = (File)localObject1;
          continue;
        }
        try
        {
          ((FileOutputStream)localObject1).flush();
          ((FileOutputStream)localObject1).close();
          bool2 = bool1;
        }
        catch (Exception paramFile1)
        {
          bool2 = true;
          break label157;
        }
      }
      bool1 = bool2;
      if (localObject1 != null) {}
      try
      {
        paramFile2.close();
        bool1 = bool2;
      }
      catch (Exception paramFile1)
      {
        bool1 = true;
        continue;
        i = 0;
        continue;
      }
      if (paramCopyFileCallback != null)
      {
        if (!bool1) {
          break label223;
        }
        i = 1;
        paramCopyFileCallback.over(i);
      }
      return bool1;
      bool2 = true;
      localObject1 = localObject2;
    }
  }
  
  public static void copySharedPreference(Context paramContext)
  {
    paramContext = new File(paramContext.getFileStreamPath("kexin.xml").getAbsolutePath().replace("/files/", "/shared_prefs/"));
    File localFile = new File(AppConstants.FIRST_LEVEL_LOG + "setting.txt");
    if (!paramContext.exists()) {
      return;
    }
    try
    {
      copyFile(paramContext, localFile);
      return;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static String createDataMigrateTime(Context paramContext)
  {
    return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
  }
  
  public static boolean delDir(File paramFile)
  {
    if (paramFile.isDirectory()) {
      return paramFile.delete();
    }
    return false;
  }
  
  public static boolean delFile(File paramFile)
  {
    if (paramFile.isDirectory()) {
      return false;
    }
    return paramFile.delete();
  }
  
  public static void deleteDBFromLogFolder(String paramString)
  {
    paramString = new File(AppConstants.FIRST_LEVEL_LOG + paramString);
    if (paramString.exists()) {
      paramString.delete();
    }
  }
  
  public static void deleteInstalledAppInfo()
  {
    File localFile = new File(AppConstants.FIRST_LEVEL_LOG + "ainfo.txt");
    if (localFile.exists()) {
      localFile.delete();
    }
  }
  
  public static void deleteSavedCoreConfig()
  {
    File localFile = new File(AppConstants.FIRST_LEVEL_BACKUP + "config.back");
    if (localFile.exists()) {
      localFile.delete();
    }
  }
  
  public static void deleteSharedPreference()
  {
    File localFile = new File(AppConstants.FIRST_LEVEL_LOG + "setting.txt");
    if (localFile.exists()) {
      localFile.delete();
    }
  }
  
  public static void displayMemory(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    paramContext.getMemoryInfo(localMemoryInfo);
    CMTracer.d("displayMemory", "availMem:" + (localMemoryInfo.availMem >> 10) + "k");
    CMTracer.d("displayMemory", "low mem threshold:" + (localMemoryInfo.threshold >> 10) + "k");
  }
  
  public static void emigrateCloundDb(Context paramContext)
  {
    Object localObject1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    File localFile1 = paramContext.getDatabasePath("BCL.db");
    File localFile2 = paramContext.getDatabasePath("BTL_2015.db");
    File localFile3 = paramContext.getDatabasePath("MSGCloud.db");
    paramContext = new File("/data/data/" + paramContext.getApplicationContext().getPackageName() + "/databases");
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    Object localObject2 = ((SimpleDateFormat)localObject1).format(new Date()) + ".s";
    Utils.checkDirs(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_BCL);
    Utils.checkDirs(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_BTL);
    Utils.checkDirs(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_MSG);
    paramContext = new File(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_BCL + "BCL.db" + (String)localObject2);
    localObject1 = new File(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_BTL + "BTL_2015.db" + (String)localObject2);
    localObject2 = new File(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_MSG + "MSGCloud.db" + (String)localObject2);
    try
    {
      paramContext.createNewFile();
      ((File)localObject1).createNewFile();
      ((File)localObject2).createNewFile();
      fileCopy(localFile1, paramContext);
      fileCopy(localFile2, (File)localObject1);
      fileCopy(localFile3, (File)localObject2);
      CMTracer.d("backup", "success");
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      CMTracer.d("backup", "fail");
    }
  }
  
  public static void emigrateDb(Context paramContext)
  {
    Object localObject = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    localObject = ((SimpleDateFormat)localObject).format(new Date()) + ".s";
    localObject = AppConstants.SECOND_LEVEL_BACKUP_KXDATA + (String)localObject;
    paramContext = paramContext.getDatabasePath("kexin.db");
    localObject = new File((String)localObject);
    makeDir(AppConstants.SECOND_LEVEL_BACKUP_KXDATA);
    try
    {
      copyFile(paramContext, (File)localObject);
      CMTracer.d("DataMigrate", "emigrateDb success");
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      CMTracer.d("DataMigrate", "emigrateDb failed!" + paramContext.getMessage());
    }
  }
  
  public static void emigrateSharedPreference(Context paramContext)
  {
    Object localObject = paramContext.getFileStreamPath("kexin.xml").getAbsolutePath().replace("/files/", "/shared_prefs/");
    paramContext = AppConstants.FIRST_LEVEL_BACKUP + "kexin.back";
    localObject = new File((String)localObject);
    paramContext = new File(paramContext);
    makeDir(AppConstants.FIRST_LEVEL_BACKUP);
    if (!((File)localObject).exists()) {
      return;
    }
    if (paramContext.exists()) {
      paramContext.delete();
    }
    try
    {
      copyFile((File)localObject, paramContext);
      return;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      CMTracer.i("DataMigrate", "emigrateSharedPreference failed!" + paramContext.getMessage());
    }
  }
  
  private static void fileCopy(File paramFile1, File paramFile2)
    throws IOException
  {
    paramFile1 = new FileInputStream(paramFile1).getChannel();
    paramFile2 = new FileOutputStream(paramFile2).getChannel();
    try
    {
      paramFile1.transferTo(0L, paramFile1.size(), paramFile2);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return;
    }
    finally
    {
      if (paramFile1 != null) {
        paramFile1.close();
      }
      if (paramFile2 != null) {
        paramFile2.close();
      }
    }
  }
  
  public static AppVersion getAppVersion(Context paramContext)
  {
    Object localObject2 = null;
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject1 = null;
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionName;
      localObject1 = paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    paramContext = localObject2;
    if (localObject1 != null)
    {
      localObject1 = ((String)localObject1).split("\\.");
      paramContext = localObject2;
      if (localObject1 != null)
      {
        paramContext = localObject2;
        if (localObject1.length == 3)
        {
          paramContext = new AppVersion();
          paramContext.majorVer = Integer.parseInt(localObject1[0]);
          paramContext.middleVer = Integer.parseInt(localObject1[1]);
          paramContext.minorVer = Integer.parseInt(localObject1[2]);
        }
      }
    }
    return paramContext;
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String getBackUpCloudDbFile(String paramString)
  {
    Object localObject1 = new File(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_BCL);
    Object localObject2;
    if ("BCL".equals(paramString))
    {
      paramString = null;
      localObject2 = paramString;
      if (((File)localObject1).exists())
      {
        localObject2 = paramString;
        if (((File)localObject1).isDirectory()) {
          localObject2 = ((File)localObject1).list(new FilenameFilter()
          {
            public boolean accept(File paramAnonymousFile, String paramAnonymousString)
            {
              return paramAnonymousString.endsWith(".s");
            }
          });
        }
      }
      if ((localObject2 != null) && (localObject2.length != 0)) {
        break label114;
      }
      localObject1 = null;
    }
    label114:
    int i;
    do
    {
      return localObject1;
      if ("BTL".equals(paramString))
      {
        localObject1 = new File(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_BTL);
        break;
      }
      if (!"MSG".equals(paramString)) {
        break;
      }
      localObject1 = new File(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_MSG);
      break;
      paramString = null;
      i = 0;
      localObject1 = paramString;
    } while (i >= localObject2.length);
    if (i == 0) {
      localObject1 = localObject2[0];
    }
    for (;;)
    {
      i += 1;
      paramString = (String)localObject1;
      break;
      localObject1 = paramString;
      if (paramString.compareTo(localObject2[i]) < 0) {
        localObject1 = localObject2[i];
      }
    }
  }
  
  public static String getBackUpDbFile()
  {
    Object localObject2 = new File(AppConstants.SECOND_LEVEL_BACKUP_KXDATA);
    Object localObject1 = null;
    Object localObject3 = localObject1;
    if (((File)localObject2).exists())
    {
      localObject3 = localObject1;
      if (((File)localObject2).isDirectory()) {
        localObject3 = ((File)localObject2).list(new FilenameFilter()
        {
          public boolean accept(File paramAnonymousFile, String paramAnonymousString)
          {
            return paramAnonymousString.endsWith(".s");
          }
        });
      }
    }
    if ((localObject3 == null) || (localObject3.length == 0)) {
      localObject2 = null;
    }
    int i;
    do
    {
      return localObject2;
      localObject1 = null;
      i = 0;
      localObject2 = localObject1;
    } while (i >= localObject3.length);
    if (i == 0) {
      localObject2 = localObject3[0];
    }
    for (;;)
    {
      i += 1;
      localObject1 = localObject2;
      break;
      localObject2 = localObject1;
      if (localObject1.compareTo(localObject3[i]) < 0) {
        localObject2 = localObject3[i];
      }
    }
  }
  
  public static String[] getBackupDbFiles()
  {
    File localFile = new File(AppConstants.SECOND_LEVEL_BACKUP_KXDATA);
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (localFile.exists())
    {
      localObject1 = localObject2;
      if (localFile.isDirectory()) {
        localObject1 = localFile.list(new FilenameFilter()
        {
          public boolean accept(File paramAnonymousFile, String paramAnonymousString)
          {
            return paramAnonymousString.endsWith(".s");
          }
        });
      }
    }
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (localObject1.length != 0) {}
    }
    else
    {
      localObject2 = null;
    }
    return localObject2;
  }
  
  public static Bitmap getBitmapFromUrl(String paramString)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject1 = localObject3;
    Object localObject2 = localObject4;
    try
    {
      InputStream localInputStream = new URL(paramString).openConnection().getInputStream();
      localObject1 = localObject3;
      localObject2 = localObject4;
      BufferedInputStream localBufferedInputStream = new BufferedInputStream(localInputStream);
      localObject1 = localObject3;
      localObject2 = localObject4;
      paramString = new BitmapFactory.Options();
      localObject1 = localObject3;
      localObject2 = localObject4;
      paramString.inPreferredConfig = Bitmap.Config.RGB_565;
      localObject1 = localObject3;
      localObject2 = localObject4;
      paramString.inPurgeable = true;
      localObject1 = localObject3;
      localObject2 = localObject4;
      paramString.inInputShareable = true;
      localObject1 = localObject3;
      localObject2 = localObject4;
      paramString = BitmapFactory.decodeStream(localBufferedInputStream, null, paramString);
      localObject1 = paramString;
      localObject2 = paramString;
      localBufferedInputStream.close();
      localObject1 = paramString;
      localObject2 = paramString;
      localInputStream.close();
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      paramString.printStackTrace();
      return localObject1;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return localObject2;
  }
  
  public static String getChannel(Context paramContext)
  {
    String str = "";
    try
    {
      Object localObject = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.get("UMENG_CHANNEL");
      paramContext = str;
      if (localObject != null) {
        paramContext = localObject.toString();
      }
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static long getCloudDbSize(Context paramContext)
  {
    File localFile1 = paramContext.getDatabasePath("BCL.db");
    File localFile2 = paramContext.getDatabasePath("BTL_2015.db");
    paramContext = paramContext.getDatabasePath("MSGCloud.db");
    long l2 = 0L;
    if (localFile1 != null) {
      l2 = 0L + localFile1.length();
    }
    long l1 = l2;
    if (localFile2 != null) {
      l1 = l2 + localFile2.length();
    }
    l2 = l1;
    if (paramContext != null) {
      l2 = l1 + paramContext.length();
    }
    return l2;
  }
  
  public static String getCountry(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.getCountry();
  }
  
  public static int getCurServerConnectState()
  {
    if (!KexinData.getInstance().hasNetWork) {
      return 4;
    }
    if (KexinData.getInstance().isOnline) {
      return 1;
    }
    if (RegisterUtil.isRegistered()) {
      return 3;
    }
    return 2;
  }
  
  private static MyLocation getCurrentLocation(Context paramContext)
  {
    Object localObject2 = null;
    LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
    Object localObject1 = localObject2;
    for (;;)
    {
      try
      {
        if (!localLocationManager.isProviderEnabled("gps")) {
          continue;
        }
        localObject1 = localObject2;
        paramContext = localLocationManager.getLastKnownLocation("gps");
        localObject1 = paramContext;
        if (paramContext == null)
        {
          localObject1 = paramContext;
          localLocationManager.requestLocationUpdates("network", 100L, 0.0F, new MyLocationListener());
          localObject1 = paramContext;
          paramContext = localLocationManager.getLastKnownLocation("network");
          localObject1 = paramContext;
        }
      }
      catch (Throwable paramContext)
      {
        continue;
      }
      if (localObject1 == null) {
        continue;
      }
      return new MyLocation(((Location)localObject1).getLongitude(), ((Location)localObject1).getLatitude());
      localObject1 = localObject2;
      paramContext = localLocationManager.getLastKnownLocation("network");
    }
    return new MyLocation();
  }
  
  public static long getDurationFromStr(String paramString)
  {
    if (paramString == null) {
      return 0L;
    }
    paramString = paramString.split(":");
    int m = 0;
    int i = 0;
    int j = 0;
    int k;
    if (paramString.length == 3)
    {
      k = Integer.parseInt(paramString[0]);
      i = Integer.parseInt(paramString[1]);
      j = Integer.parseInt(paramString[2]);
    }
    for (;;)
    {
      return k * 3600 + i * 60 + j;
      k = m;
      if (paramString.length == 2)
      {
        i = Integer.parseInt(paramString[0]);
        j = Integer.parseInt(paramString[1]);
        k = m;
      }
    }
  }
  
  public static int getFileCountInDir(File paramFile, String paramString)
  {
    int k = 0;
    int i = 0;
    if (paramFile.isDirectory())
    {
      paramFile = paramFile.listFiles(new FilenameFilter()
      {
        public boolean accept(File paramAnonymousFile, String paramAnonymousString)
        {
          if (new File(paramAnonymousFile.getAbsolutePath() + "/" + paramAnonymousString).isDirectory()) {
            return true;
          }
          return paramAnonymousString.contains(this.val$nameFilter);
        }
      });
      if ((paramFile == null) || (paramFile.length == 0)) {
        return 0;
      }
      int m = paramFile.length;
      int j = 0;
      k = i;
      if (j < m)
      {
        File localFile = paramFile[j];
        if (localFile.isDirectory()) {
          i += getFileCountInDir(localFile, paramString);
        }
        for (;;)
        {
          j += 1;
          break;
          i += 1;
        }
      }
    }
    return k;
  }
  
  private void getFiles(ArrayList<File> paramArrayList, String paramString)
  {
    paramString = new File(paramString).listFiles();
    int i = 0;
    while (i < paramString.length)
    {
      Object localObject = paramString[i];
      if (localObject.isFile()) {
        paramArrayList.add(localObject);
      }
      i += 1;
    }
  }
  
  /* Error */
  public static String getHttpDataByUrl(String paramString)
  {
    // Byte code:
    //   0: new 849	org/apache/http/impl/client/DefaultHttpClient
    //   3: dup
    //   4: invokespecial 850	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   7: astore 11
    //   9: new 852	org/apache/http/client/methods/HttpGet
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 853	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   17: astore 12
    //   19: new 855	org/apache/http/params/BasicHttpParams
    //   22: dup
    //   23: invokespecial 856	org/apache/http/params/BasicHttpParams:<init>	()V
    //   26: astore_0
    //   27: aload_0
    //   28: sipush 15000
    //   31: invokestatic 862	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   34: aload 12
    //   36: aload_0
    //   37: invokevirtual 866	org/apache/http/client/methods/HttpGet:setParams	(Lorg/apache/http/params/HttpParams;)V
    //   40: aconst_null
    //   41: astore 10
    //   43: aconst_null
    //   44: astore 9
    //   46: aconst_null
    //   47: astore_0
    //   48: aconst_null
    //   49: astore 8
    //   51: aconst_null
    //   52: astore 6
    //   54: aconst_null
    //   55: astore 7
    //   57: aload_0
    //   58: astore 5
    //   60: aload 10
    //   62: astore_2
    //   63: aload 6
    //   65: astore_3
    //   66: aload 9
    //   68: astore 4
    //   70: aload 11
    //   72: aload 12
    //   74: invokeinterface 871 2 0
    //   79: astore 12
    //   81: aload_0
    //   82: astore 5
    //   84: aload 10
    //   86: astore_2
    //   87: aload 6
    //   89: astore_3
    //   90: aload 9
    //   92: astore 4
    //   94: aload 12
    //   96: invokeinterface 877 1 0
    //   101: invokeinterface 882 1 0
    //   106: sipush 200
    //   109: if_icmpne +191 -> 300
    //   112: aload_0
    //   113: astore 5
    //   115: aload 10
    //   117: astore_2
    //   118: aload 6
    //   120: astore_3
    //   121: aload 9
    //   123: astore 4
    //   125: aload 12
    //   127: invokeinterface 886 1 0
    //   132: invokeinterface 891 1 0
    //   137: astore_0
    //   138: aload_0
    //   139: astore 5
    //   141: aload_0
    //   142: astore_2
    //   143: aload 6
    //   145: astore_3
    //   146: aload_0
    //   147: astore 4
    //   149: new 893	org/apache/commons/io/output/ByteArrayOutputStream
    //   152: dup
    //   153: sipush 512
    //   156: invokespecial 895	org/apache/commons/io/output/ByteArrayOutputStream:<init>	(I)V
    //   159: astore 6
    //   161: sipush 512
    //   164: newarray byte
    //   166: astore_2
    //   167: aload_0
    //   168: aload_2
    //   169: invokevirtual 896	java/io/InputStream:read	([B)I
    //   172: istore_1
    //   173: iload_1
    //   174: iconst_m1
    //   175: if_icmpeq +64 -> 239
    //   178: aload 6
    //   180: aload_2
    //   181: iconst_0
    //   182: iload_1
    //   183: invokevirtual 897	org/apache/commons/io/output/ByteArrayOutputStream:write	([BII)V
    //   186: goto -19 -> 167
    //   189: astore 5
    //   191: aload 6
    //   193: astore 4
    //   195: aload_0
    //   196: astore_2
    //   197: aload 4
    //   199: astore_3
    //   200: aload 5
    //   202: invokevirtual 898	org/apache/http/client/ClientProtocolException:printStackTrace	()V
    //   205: aload 4
    //   207: ifnull +8 -> 215
    //   210: aload 4
    //   212: invokevirtual 899	org/apache/commons/io/output/ByteArrayOutputStream:close	()V
    //   215: aload_0
    //   216: ifnull +7 -> 223
    //   219: aload_0
    //   220: invokevirtual 743	java/io/InputStream:close	()V
    //   223: aload 11
    //   225: invokeinterface 903 1 0
    //   230: invokeinterface 908 1 0
    //   235: ldc_w 672
    //   238: areturn
    //   239: new 88	java/lang/String
    //   242: dup
    //   243: aload 6
    //   245: invokevirtual 912	org/apache/commons/io/output/ByteArrayOutputStream:toByteArray	()[B
    //   248: invokespecial 433	java/lang/String:<init>	([B)V
    //   251: astore_2
    //   252: aload 6
    //   254: ifnull +8 -> 262
    //   257: aload 6
    //   259: invokevirtual 899	org/apache/commons/io/output/ByteArrayOutputStream:close	()V
    //   262: aload_0
    //   263: ifnull +7 -> 270
    //   266: aload_0
    //   267: invokevirtual 743	java/io/InputStream:close	()V
    //   270: aload 11
    //   272: invokeinterface 903 1 0
    //   277: invokeinterface 908 1 0
    //   282: aload_2
    //   283: areturn
    //   284: astore_3
    //   285: aload_3
    //   286: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   289: goto -27 -> 262
    //   292: astore_0
    //   293: aload_0
    //   294: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   297: goto -27 -> 270
    //   300: iconst_0
    //   301: ifeq +11 -> 312
    //   304: new 914	java/lang/NullPointerException
    //   307: dup
    //   308: invokespecial 915	java/lang/NullPointerException:<init>	()V
    //   311: athrow
    //   312: iconst_0
    //   313: ifeq +11 -> 324
    //   316: new 914	java/lang/NullPointerException
    //   319: dup
    //   320: invokespecial 915	java/lang/NullPointerException:<init>	()V
    //   323: athrow
    //   324: aload 11
    //   326: invokeinterface 903 1 0
    //   331: invokeinterface 908 1 0
    //   336: goto -101 -> 235
    //   339: astore_0
    //   340: aload_0
    //   341: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   344: goto -32 -> 312
    //   347: astore_0
    //   348: aload_0
    //   349: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   352: goto -28 -> 324
    //   355: astore_2
    //   356: aload_2
    //   357: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   360: goto -145 -> 215
    //   363: astore_0
    //   364: aload_0
    //   365: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   368: goto -145 -> 223
    //   371: astore_2
    //   372: aload 8
    //   374: astore 4
    //   376: aload 5
    //   378: astore_0
    //   379: aload_2
    //   380: astore 5
    //   382: aload_0
    //   383: astore_2
    //   384: aload 4
    //   386: astore_3
    //   387: aload 5
    //   389: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   392: aload 4
    //   394: ifnull +8 -> 402
    //   397: aload 4
    //   399: invokevirtual 899	org/apache/commons/io/output/ByteArrayOutputStream:close	()V
    //   402: aload_0
    //   403: ifnull +7 -> 410
    //   406: aload_0
    //   407: invokevirtual 743	java/io/InputStream:close	()V
    //   410: aload 11
    //   412: invokeinterface 903 1 0
    //   417: invokeinterface 908 1 0
    //   422: goto -187 -> 235
    //   425: astore_2
    //   426: aload_2
    //   427: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   430: goto -28 -> 402
    //   433: astore_0
    //   434: aload_0
    //   435: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   438: goto -28 -> 410
    //   441: astore_0
    //   442: aload_3
    //   443: ifnull +7 -> 450
    //   446: aload_3
    //   447: invokevirtual 899	org/apache/commons/io/output/ByteArrayOutputStream:close	()V
    //   450: aload_2
    //   451: ifnull +7 -> 458
    //   454: aload_2
    //   455: invokevirtual 743	java/io/InputStream:close	()V
    //   458: aload 11
    //   460: invokeinterface 903 1 0
    //   465: invokeinterface 908 1 0
    //   470: aload_0
    //   471: athrow
    //   472: astore_3
    //   473: aload_3
    //   474: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   477: goto -27 -> 450
    //   480: astore_2
    //   481: aload_2
    //   482: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   485: goto -27 -> 458
    //   488: astore 4
    //   490: aload 6
    //   492: astore_3
    //   493: aload_0
    //   494: astore_2
    //   495: aload 4
    //   497: astore_0
    //   498: goto -56 -> 442
    //   501: astore 5
    //   503: aload 6
    //   505: astore 4
    //   507: goto -125 -> 382
    //   510: astore 5
    //   512: aload 4
    //   514: astore_0
    //   515: aload 7
    //   517: astore 4
    //   519: goto -324 -> 195
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	522	0	paramString	String
    //   172	11	1	i	int
    //   62	221	2	localObject1	Object
    //   355	2	2	localIOException1	IOException
    //   371	9	2	localIOException2	IOException
    //   383	1	2	str1	String
    //   425	30	2	localIOException3	IOException
    //   480	2	2	localIOException4	IOException
    //   494	1	2	str2	String
    //   65	135	3	localObject2	Object
    //   284	2	3	localIOException5	IOException
    //   386	61	3	localObject3	Object
    //   472	2	3	localIOException6	IOException
    //   492	1	3	localByteArrayOutputStream1	org.apache.commons.io.output.ByteArrayOutputStream
    //   68	330	4	localObject4	Object
    //   488	8	4	localObject5	Object
    //   505	13	4	localObject6	Object
    //   58	82	5	str3	String
    //   189	188	5	localClientProtocolException1	org.apache.http.client.ClientProtocolException
    //   380	8	5	localObject7	Object
    //   501	1	5	localIOException7	IOException
    //   510	1	5	localClientProtocolException2	org.apache.http.client.ClientProtocolException
    //   52	452	6	localByteArrayOutputStream2	org.apache.commons.io.output.ByteArrayOutputStream
    //   55	461	7	localObject8	Object
    //   49	324	8	localObject9	Object
    //   44	78	9	localObject10	Object
    //   41	75	10	localObject11	Object
    //   7	452	11	localDefaultHttpClient	org.apache.http.impl.client.DefaultHttpClient
    //   17	109	12	localObject12	Object
    // Exception table:
    //   from	to	target	type
    //   161	167	189	org/apache/http/client/ClientProtocolException
    //   167	173	189	org/apache/http/client/ClientProtocolException
    //   178	186	189	org/apache/http/client/ClientProtocolException
    //   239	252	189	org/apache/http/client/ClientProtocolException
    //   257	262	284	java/io/IOException
    //   266	270	292	java/io/IOException
    //   304	312	339	java/io/IOException
    //   316	324	347	java/io/IOException
    //   210	215	355	java/io/IOException
    //   219	223	363	java/io/IOException
    //   70	81	371	java/io/IOException
    //   94	112	371	java/io/IOException
    //   125	138	371	java/io/IOException
    //   149	161	371	java/io/IOException
    //   397	402	425	java/io/IOException
    //   406	410	433	java/io/IOException
    //   70	81	441	finally
    //   94	112	441	finally
    //   125	138	441	finally
    //   149	161	441	finally
    //   200	205	441	finally
    //   387	392	441	finally
    //   446	450	472	java/io/IOException
    //   454	458	480	java/io/IOException
    //   161	167	488	finally
    //   167	173	488	finally
    //   178	186	488	finally
    //   239	252	488	finally
    //   161	167	501	java/io/IOException
    //   167	173	501	java/io/IOException
    //   178	186	501	java/io/IOException
    //   239	252	501	java/io/IOException
    //   70	81	510	org/apache/http/client/ClientProtocolException
    //   94	112	510	org/apache/http/client/ClientProtocolException
    //   125	138	510	org/apache/http/client/ClientProtocolException
    //   149	161	510	org/apache/http/client/ClientProtocolException
  }
  
  /* Error */
  public static void getInstalledApp(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 146	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_3
    //   5: aload_3
    //   6: iconst_0
    //   7: invokevirtual 920	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   10: astore 4
    //   12: aconst_null
    //   13: astore_2
    //   14: iconst_0
    //   15: istore_1
    //   16: iload_1
    //   17: aload 4
    //   19: invokeinterface 924 1 0
    //   24: if_icmpge +142 -> 166
    //   27: aload 4
    //   29: iload_1
    //   30: invokeinterface 927 2 0
    //   35: checkcast 644	android/content/pm/PackageInfo
    //   38: astore 5
    //   40: aload_2
    //   41: astore_0
    //   42: new 53	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   49: aload_2
    //   50: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload 5
    //   55: getfield 930	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   58: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   64: astore_2
    //   65: aload_2
    //   66: astore_0
    //   67: new 53	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   74: aload_2
    //   75: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: ldc_w 932
    //   81: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: astore_2
    //   88: aload_2
    //   89: astore_0
    //   90: aload_3
    //   91: aload 5
    //   93: getfield 936	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   96: invokevirtual 940	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   99: astore 5
    //   101: aload_2
    //   102: astore_0
    //   103: new 53	java/lang/StringBuilder
    //   106: dup
    //   107: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   110: aload_2
    //   111: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: aload 5
    //   116: invokeinterface 943 1 0
    //   121: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: astore_2
    //   128: aload_2
    //   129: astore_0
    //   130: new 53	java/lang/StringBuilder
    //   133: dup
    //   134: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   137: aload_2
    //   138: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: ldc_w 945
    //   144: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   150: astore_2
    //   151: aload_2
    //   152: astore_0
    //   153: iload_1
    //   154: iconst_1
    //   155: iadd
    //   156: istore_1
    //   157: aload_0
    //   158: astore_2
    //   159: goto -143 -> 16
    //   162: astore_2
    //   163: goto -10 -> 153
    //   166: aconst_null
    //   167: astore_0
    //   168: aconst_null
    //   169: astore 4
    //   171: new 492	java/io/FileOutputStream
    //   174: dup
    //   175: new 53	java/lang/StringBuilder
    //   178: dup
    //   179: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   182: getstatic 32	ws/coverme/im/model/constant/AppConstants:FIRST_LEVEL_LOG	Ljava/lang/String;
    //   185: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: ldc_w 541
    //   191: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: invokespecial 946	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   200: astore_3
    //   201: aload_3
    //   202: aload_2
    //   203: invokevirtual 949	java/lang/String:getBytes	()[B
    //   206: invokevirtual 951	java/io/FileOutputStream:write	([B)V
    //   209: aload_3
    //   210: ifnull +7 -> 217
    //   213: aload_3
    //   214: invokevirtual 501	java/io/FileOutputStream:close	()V
    //   217: return
    //   218: astore_0
    //   219: aload_0
    //   220: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   223: return
    //   224: astore_3
    //   225: aload 4
    //   227: astore_2
    //   228: aload_2
    //   229: astore_0
    //   230: aload_3
    //   231: invokevirtual 464	java/lang/Exception:printStackTrace	()V
    //   234: aload_2
    //   235: ifnull -18 -> 217
    //   238: aload_2
    //   239: invokevirtual 501	java/io/FileOutputStream:close	()V
    //   242: return
    //   243: astore_0
    //   244: aload_0
    //   245: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   248: return
    //   249: astore_2
    //   250: aload_0
    //   251: ifnull +7 -> 258
    //   254: aload_0
    //   255: invokevirtual 501	java/io/FileOutputStream:close	()V
    //   258: aload_2
    //   259: athrow
    //   260: astore_0
    //   261: aload_0
    //   262: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   265: goto -7 -> 258
    //   268: astore_2
    //   269: aload_3
    //   270: astore_0
    //   271: goto -21 -> 250
    //   274: astore_0
    //   275: aload_3
    //   276: astore_2
    //   277: aload_0
    //   278: astore_3
    //   279: goto -51 -> 228
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	282	0	paramContext	Context
    //   15	142	1	i	int
    //   13	146	2	localObject1	Object
    //   162	41	2	localException1	Exception
    //   227	12	2	localList1	java.util.List
    //   249	10	2	localObject2	Object
    //   268	1	2	localObject3	Object
    //   276	1	2	localException2	Exception
    //   4	210	3	localObject4	Object
    //   224	52	3	localException3	Exception
    //   278	1	3	localContext	Context
    //   10	216	4	localList2	java.util.List
    //   38	77	5	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   42	65	162	java/lang/Exception
    //   67	88	162	java/lang/Exception
    //   90	101	162	java/lang/Exception
    //   103	128	162	java/lang/Exception
    //   130	151	162	java/lang/Exception
    //   213	217	218	java/io/IOException
    //   171	201	224	java/lang/Exception
    //   238	242	243	java/io/IOException
    //   171	201	249	finally
    //   230	234	249	finally
    //   254	258	260	java/io/IOException
    //   201	209	268	finally
    //   201	209	274	java/lang/Exception
  }
  
  public static long getKexinDbSize(Context paramContext)
  {
    return paramContext.getDatabasePath("kexin.db").length();
  }
  
  public static String getLocale(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration().locale.getCountry();
    System.out.println("Country = " + paramContext);
    return paramContext;
  }
  
  public static String getRandomString(int paramInt)
  {
    int j = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length();
    Random localRandom = new Random();
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramInt)
    {
      localStringBuffer.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(localRandom.nextInt(j)));
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static long getSPSize(Context paramContext)
  {
    return new File(paramContext.getFileStreamPath("kexin.xml").getAbsolutePath().replace("/files/", "/shared_prefs/")).length();
  }
  
  public static long getSdCardFreeSpace(Context paramContext)
  {
    long l = 0L;
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      paramContext = new StatFs(Environment.getExternalStorageDirectory().getPath());
      l = paramContext.getBlockSize();
      l = paramContext.getAvailableBlocks() * l;
    }
    return l;
  }
  
  public static int getSdCardState(int paramInt, Context paramContext)
  {
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      paramContext = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l3 = paramContext.getBlockCount();
      long l1 = paramContext.getBlockSize();
      long l2 = paramContext.getAvailableBlocks();
      l3 = l3 * l1 / 1024L;
      l1 = l2 * l1 / 1024L;
      CMTracer.d("SDCard state", "SDCard 总容量:" + l3 + " KB" + " 剩余:" + l1 + " KB");
      if (l1 < paramInt) {
        return 3;
      }
      return 1;
    }
    return 2;
  }
  
  public static int getVersionUpdateType(Context paramContext, AppVersion paramAppVersion)
  {
    paramContext = getAppVersion(paramContext);
    if (paramContext == null) {}
    do
    {
      do
      {
        return 0;
        if (paramAppVersion.majorVer > paramContext.majorVer) {
          return 1;
        }
      } while (paramAppVersion.majorVer < paramContext.majorVer);
      if ((paramAppVersion.majorVer == paramContext.majorVer) && (paramAppVersion.middleVer > paramContext.middleVer)) {
        return 1;
      }
    } while (((paramAppVersion.majorVer == paramContext.majorVer) && (paramAppVersion.middleVer < paramContext.middleVer)) || (paramAppVersion.minorVer <= paramContext.minorVer));
    return 2;
  }
  
  public static int getlenth(EditText paramEditText)
  {
    int i = 9000;
    j = i;
    try
    {
      paramEditText = paramEditText.getFilters();
      j = i;
      int i1 = paramEditText.length;
      int k = 0;
      for (;;)
      {
        j = i;
        if (k >= i1) {
          break;
        }
        Object localObject1 = paramEditText[k];
        j = i;
        Object localObject2 = localObject1.getClass();
        int n = i;
        j = i;
        if (((Class)localObject2).getName().equals("android.text.InputFilter$LengthFilter"))
        {
          j = i;
          localObject2 = ((Class)localObject2).getDeclaredFields();
          j = i;
          int i2 = localObject2.length;
          int m = 0;
          for (;;)
          {
            n = i;
            if (m >= i2) {
              break;
            }
            Object localObject3 = localObject2[m];
            n = i;
            j = i;
            if (localObject3.getName().equals("mMax"))
            {
              j = i;
              localObject3.setAccessible(true);
              j = i;
              n = ((Integer)localObject3.get(localObject1)).intValue();
            }
            m += 1;
            i = n;
          }
        }
        k += 1;
        i = n;
      }
      return j;
    }
    catch (Exception paramEditText)
    {
      paramEditText.printStackTrace();
    }
  }
  
  public static boolean happenOnceEveryXHours(Context paramContext, String paramString, int paramInt)
  {
    return happenOnceEveryXMillis(paramContext, paramString, paramInt * 60 * 60 * 1000);
  }
  
  public static boolean happenOnceEveryXMillis(Context paramContext, String paramString, int paramInt)
  {
    long l1 = SharedPreferencesManager.getSharedLongPreferences(paramString, paramContext);
    long l2 = System.currentTimeMillis();
    if (l2 - l1 >= paramInt)
    {
      SharedPreferencesManager.setSharedLongPreferences(paramString, l2, paramContext);
      return true;
    }
    return false;
  }
  
  public static boolean hasGoogleAccount(Context paramContext)
  {
    paramContext = AccountManager.get(paramContext).getAccountsByType("com.google");
    return (paramContext != null) && (paramContext.length != 0);
  }
  
  public static void immigrateCloudBackup(Context paramContext)
  {
    File localFile1 = paramContext.getDatabasePath("BCL.db");
    File localFile2 = paramContext.getDatabasePath("BTL_2015.db");
    File localFile3 = paramContext.getDatabasePath("MSGCloud.db");
    CMTracer.i("immigrateCloudBackup", "bcldbFile:" + localFile1 + ",btldbFile:" + localFile2 + ",msgClouddbFile:" + localFile3);
    paramContext = "/data/data/" + paramContext.getApplicationContext().getPackageName() + "/databases";
    Object localObject1 = new File(paramContext);
    if (!((File)localObject1).exists()) {
      ((File)localObject1).mkdirs();
    }
    CMTracer.i("immigrateCloudBackup", "dbPath:" + paramContext);
    localObject1 = getBackUpCloudDbFile("BCL");
    Object localObject2 = getBackUpCloudDbFile("BTL");
    paramContext = getBackUpCloudDbFile("MSG");
    localObject1 = new File(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_BCL + (String)localObject1);
    localObject2 = new File(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_BTL + (String)localObject2);
    paramContext = new File(AppConstants.SECOND_LEVEL_BACKUP_CLOUD_MSG + paramContext);
    CMTracer.i("immigrateCloudBackup", "bclBackup:" + localObject1 + ",btlBackup:" + localObject2 + ",msgBackup:" + paramContext);
    try
    {
      fileCopy((File)localObject1, localFile1);
      fileCopy((File)localObject2, localFile2);
      fileCopy(paramContext, localFile3);
      CMTracer.d("immigrateCloudBackup", "success");
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      CMTracer.d("immigrateCloudBackup", "fail");
    }
  }
  
  public static void immigrateSharedPreference(Context paramContext)
  {
    Object localObject = AppConstants.FIRST_LEVEL_BACKUP + "kexin.back";
    paramContext = paramContext.getFileStreamPath("kexin.xml").getAbsolutePath().replace("/files/", "/shared_prefs/");
    localObject = new File((String)localObject);
    paramContext = new File(paramContext);
    if (!((File)localObject).exists()) {
      return;
    }
    if (paramContext.exists()) {
      paramContext.delete();
    }
    try
    {
      copyFile((File)localObject, paramContext);
      return;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      CMTracer.i("DataMigrate", "immigrateSharedPreference failed!" + paramContext.getMessage());
    }
  }
  
  public static boolean initHideMode(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1;
    if (PremiumUtil.isTrial())
    {
      bool1 = bool2;
      if (!PremiumUtil.trialEnds()) {}
    }
    else
    {
      Security localSecurity = KexinData.getInstance().getSeurity();
      bool1 = bool2;
      if (localSecurity.hidemode)
      {
        localSecurity.hidemode = false;
        HideAppUtil localHideAppUtil = new HideAppUtil();
        localHideAppUtil.Show(paramContext);
        localHideAppUtil.clearLaunchKey(paramContext);
        Security.setHideMode(localSecurity.hidemode, paramContext);
        KexinData.getInstance().setSeurity(localSecurity);
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static String iosVideoName2Android(String paramString)
  {
    if (paramString == null) {
      return paramString;
    }
    return paramString.replace(".mov", ".dat").replace(".m4v", ".dat").replace(".original", ".dat");
  }
  
  public static String iosVideoName2Mp4(String paramString)
  {
    if (paramString == null) {
      return paramString;
    }
    return paramString.replace(".mov", ".mp4").replace(".m4v", ".mp4").replace(".original", ".mp4");
  }
  
  public static boolean isIosVideoName(String paramString)
  {
    if (paramString == null) {}
    while ((!paramString.endsWith(".mov")) && (!paramString.endsWith(".m4v"))) {
      return false;
    }
    return true;
  }
  
  public static boolean isPadDevice(Activity paramActivity)
  {
    boolean bool = false;
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    if (Math.sqrt(Math.pow(localDisplayMetrics.widthPixels, 2.0D) + Math.pow(localDisplayMetrics.heightPixels, 2.0D)) / (localDisplayMetrics.densityDpi * localDisplayMetrics.density) > 6.0D) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isSimpleChineseLanguage(Context paramContext)
  {
    return "CN".equalsIgnoreCase(paramContext.getResources().getConfiguration().locale.getCountry());
  }
  
  public static boolean isSuitableDisplay(Activity paramActivity)
  {
    boolean bool = true;
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    double d = Math.sqrt(Math.pow(localDisplayMetrics.widthPixels, 2.0D) + Math.pow(localDisplayMetrics.heightPixels, 2.0D)) / (localDisplayMetrics.densityDpi * localDisplayMetrics.density);
    if ((localDisplayMetrics.widthPixels < 240) || (localDisplayMetrics.heightPixels < 320) || (d >= 7.0D)) {
      bool = false;
    }
    return bool;
  }
  
  public static boolean isSupportedDevice()
  {
    int i = 0;
    int j = 0;
    int k = 0;
    Object localObject = new File("/proc/cpuinfo");
    BufferedReader localBufferedReader;
    String str;
    if (((File)localObject).exists()) {
      try
      {
        localObject = new FileReader((File)localObject);
        localBufferedReader = new BufferedReader((Reader)localObject);
        KexinData.cpuInfo = "";
        for (;;)
        {
          str = localBufferedReader.readLine();
          if (str == null) {
            break label471;
          }
          if (!str.contains("Processor")) {
            break;
          }
          KexinData.cpuInfo += str.replace("Processor", "");
          if ((str.contains("architecture")) && ((str.contains("7")) || (str.contains("8"))))
          {
            j = 1;
            CMTracer.d("isArmV7CPU", "confirmed as armV7");
          }
        }
        j = k;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        i = 0;
      }
    }
    for (;;)
    {
      if (Build.VERSION.SDK_INT >= 9) {
        j = 1;
      }
      if ((i == 0) || (j == 0)) {
        break label556;
      }
      return true;
      if (str.contains("architecture"))
      {
        KexinData.cpuInfo += str.replace("architecture", "");
        CMTracer.d("isArmV7CPU", str);
        break;
      }
      if (str.contains("CPU variant"))
      {
        KexinData.cpuInfo += str.replace("CPU variant", "");
        break;
      }
      if (str.contains("CPU part"))
      {
        KexinData.cpuInfo += str.replace("CPU part", "");
        break;
      }
      if (str.contains("CPU revision"))
      {
        KexinData.cpuInfo += str.replace("CPU revision", "");
        break;
      }
      if (str.contains("Hardware"))
      {
        KexinData.cpuInfo += str.replace("Hardware", "");
        break;
      }
      if (!str.contains("Revision")) {
        break;
      }
      KexinData.cpuInfo += str.replace("Revision", "");
      break;
      label471:
      KexinData.cpuInfo = KexinData.cpuInfo.replaceAll("CPU", "");
      KexinData.cpuInfo = KexinData.cpuInfo.replaceAll(" ", "");
      KexinData.cpuInfo = KexinData.cpuInfo.replaceAll("\t", "");
      KexinData.cpuInfo = KexinData.cpuInfo.replaceAll(":", "");
      if (localIOException != null) {
        localIOException.close();
      }
      i = j;
      if (localBufferedReader != null)
      {
        localBufferedReader.close();
        i = j;
      }
    }
    label556:
    return false;
  }
  
  public static boolean isUpdateKexin(Context paramContext)
  {
    boolean bool = true;
    if (checkAppExistence("ws.coverme.im", null, paramContext)) {
      bool = false;
    }
    while (SharedPreferencesManager.getSharedIntPreferences("CoverMeVaultTransferFormalVersion", paramContext) != 0) {
      return bool;
    }
    return false;
  }
  
  public static boolean makeDir(String paramString)
  {
    paramString = new File(paramString);
    if (!paramString.isDirectory()) {
      return paramString.mkdirs();
    }
    return true;
  }
  
  public static boolean moveFile(File paramFile1, File paramFile2)
    throws IOException
  {
    if (!copyFile(paramFile1, paramFile2)) {
      return false;
    }
    delFile(paramFile1);
    return true;
  }
  
  public static boolean needDisplayNavigation(Context paramContext)
  {
    if (SharedPreferencesManager.getSharedBooleanPreferencesDefaultFalse("HadDisplayedNavigation", paramContext)) {
      return false;
    }
    SharedPreferencesManager.setSharedBooleanPreferences("HadDisplayedNavigation", true, paramContext);
    return true;
  }
  
  public static void restoreCoreConfigFromDB(String paramString)
  {
    Object localObject = SettingTableOperation.getStringSetting("coreconfig", KexinData.getInstance().getContext());
    if (StrUtil.isNull((String)localObject)) {
      return;
    }
    try
    {
      localObject = Base64.decode((String)localObject, 8);
      File localFile = new File(paramString);
      if (localFile.exists()) {
        localFile.delete();
      }
      try
      {
        paramString = new FileOutputStream(paramString);
        paramString.write((byte[])localObject, 0, localObject.length);
        paramString.close();
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      return;
    }
    catch (Exception paramString) {}
  }
  
  public static void restoreDb(Context paramContext, String paramString)
  {
    if (paramString != null) {
      new DBBackupTask(paramContext).execute(new String[] { "restroeDatabase", AppConstants.SECOND_LEVEL_BACKUP_KXDATA + paramString });
    }
  }
  
  public static void saveCoreConfig()
  {
    Object localObject1 = KexinData.getInstance().getContext().getDir("config", 0);
    Object localObject2 = ((File)localObject1).getAbsolutePath() + "/coreconfigex.bin";
    localObject1 = AppConstants.FIRST_LEVEL_BACKUP + "config.back";
    localObject2 = new File((String)localObject2);
    localObject1 = new File((String)localObject1);
    makeDir(AppConstants.FIRST_LEVEL_BACKUP);
    if ((((File)localObject1).exists()) && (((File)localObject1).length() != 0L)) {}
    while ((!((File)localObject2).exists()) || (((File)localObject2).length() == 0L)) {
      return;
    }
    if (((File)localObject1).exists()) {
      ((File)localObject1).delete();
    }
    try
    {
      copyFile((File)localObject2, (File)localObject1);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  public static void saveCoreConfigAnyWay()
  {
    Object localObject1 = KexinData.getInstance().getContext().getDir("config", 0);
    localObject1 = ((File)localObject1).getAbsolutePath() + "/coreconfigex.bin";
    Object localObject2 = AppConstants.FIRST_LEVEL_BACKUP + "config.back";
    File localFile = new File((String)localObject1);
    localObject2 = new File((String)localObject2);
    makeDir(AppConstants.FIRST_LEVEL_BACKUP);
    if (chekCoreConfigAvailable((String)localObject1))
    {
      CMTracer.i("checkCoreConfig", "config correct, saveCoreConfigAnyWay");
      saveCoreConfigToDB((String)localObject1);
      if (((File)localObject2).exists()) {
        ((File)localObject2).delete();
      }
    }
    try
    {
      copyFile(localFile, (File)localObject2);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  public static void saveCoreConfigToDB(String paramString)
  {
    paramString = new File(paramString);
    long l = paramString.length();
    if (l <= 0L) {}
    label54:
    do
    {
      return;
      try
      {
        localFileInputStream = new FileInputStream(paramString);
        paramString = new byte[(int)l];
        i = 0;
      }
      catch (FileNotFoundException paramString)
      {
        FileInputStream localFileInputStream;
        int i;
        int j;
        paramString.printStackTrace();
        return;
      }
      try
      {
        j = localFileInputStream.read(paramString);
        i = j;
        localFileInputStream.close();
        i = j;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        break label54;
      }
    } while (i != l);
    SettingTableOperation.saveStringSetting("coreconfig", Base64.encodeToString(paramString, 8), KexinData.getInstance().getContext());
  }
  
  public static void saveLoginLog(boolean paramBoolean, String paramString, Context paramContext)
  {
    if (paramBoolean)
    {
      paramString = new LoginSuccessLog();
      paramString.loginTime = DateUtil.getStringDate();
      paramString.messageSendNum = 0;
      paramString.photoSendNum = 0;
      paramString.phoneCallTime = 0.0D;
      paramString.userFaceImage = null;
      paramString.isMainPassword = KexinData.getInstance().getUserInfo().isMainPassword;
      paramString.authorityId = KexinData.getInstance().getCurrentAuthorityId();
      paramString.location = getCurrentLocation(paramContext);
      paramString.id = LoginSuccessLogTableOperation.saveLog(paramString, paramContext);
      KexinData.getInstance().setLoginSuccessLog(paramString);
      return;
    }
    LoginFailedLog localLoginFailedLog = new LoginFailedLog();
    localLoginFailedLog.loginTime = DateUtil.getStringDate();
    localLoginFailedLog.failedPassword = paramString;
    localLoginFailedLog.userFaceImage = null;
    localLoginFailedLog.location = null;
    LoginFailedLogTableOperation.saveLog(localLoginFailedLog, paramContext);
  }
  
  public static boolean sdCardState(Context paramContext, boolean paramBoolean)
  {
    return sdCardState(paramContext, paramBoolean, 15360);
  }
  
  public static boolean sdCardState(Context paramContext, boolean paramBoolean, int paramInt)
  {
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      long l2 = 0L;
      long l1 = l2;
      try
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        l1 = l2;
        long l5 = localStatFs.getBlockCount();
        l1 = l2;
        long l3 = localStatFs.getBlockSize();
        l1 = l2;
        long l4 = localStatFs.getAvailableBlocks();
        l1 = l2;
        l5 = l5 * l3 / 1024L;
        l1 = l2;
        l2 = l4 * l3 / 1024L;
        l1 = l2;
        System.out.println("SDCard 总容量:" + l5 + " KB" + " 剩余:" + l2 + " KB");
        l1 = l2;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          CMTracer.e("sdCardState", "Get SD space failed, " + localException.getMessage());
        }
      }
      if (l1 < paramInt)
      {
        if (paramBoolean)
        {
          paramContext = new MyDialog(paramContext);
          paramContext.setTitle(2131165362);
          paramContext.setMessage(2131165329);
          paramContext.setSinglePositiveButton(2131165364, null);
          paramContext.show();
        }
        return false;
      }
      return true;
    }
    if (paramBoolean)
    {
      paramContext = new MyDialog(paramContext);
      paramContext.setTitle(2131165362);
      paramContext.setMessage(2131165328);
      paramContext.setSinglePositiveButton(2131165364, null);
      paramContext.show();
    }
    return false;
  }
  
  public static void setNeedOnline(boolean paramBoolean)
  {
    KexinData.getInstance().needOnline = paramBoolean;
    CMTracer.i("OtherHelper", "set needOnline = " + paramBoolean);
  }
  
  public static void setSilentUpdateCheck(boolean paramBoolean)
  {
    KexinData.getInstance().silentUpdateCheck = paramBoolean;
    CMTracer.i("OtherHelper", "setSilentUpdateCheck = " + paramBoolean);
  }
  
  public static void showFileLostDialog(Context paramContext)
  {
    if (!SharedPreferencesManager.getSharedBooleanPreferencesDefaultFalse(SharedPreferencesManager.FILE_LOSE_Tip, paramContext))
    {
      MyDialog localMyDialog = new MyDialog(paramContext);
      localMyDialog.setTitle(2131165397);
      localMyDialog.setMessage(2131166415);
      localMyDialog.setSinglePositiveButton(2131165364, null);
      localMyDialog.show();
      SharedPreferencesManager.setSharedBooleanPreferences(SharedPreferencesManager.FILE_LOSE_Tip, true, paramContext);
    }
  }
  
  public static void showProgressDialog(String paramString, ProgressDialog paramProgressDialog, Context paramContext)
  {
    paramProgressDialog.setMessage(paramString);
    paramProgressDialog.setIndeterminate(true);
    paramProgressDialog.setCancelable(true);
    paramProgressDialog.show();
  }
  
  public static void showToast(Context paramContext, int paramInt1, int paramInt2)
  {
    Toast.makeText(paramContext, paramInt1, paramInt2).show();
  }
  
  public static boolean silentUpdateCheck(Context paramContext)
  {
    return happenOnceEveryXHours(paramContext, SharedPreferencesManager.SILENT_UPDATE_CHECK, 24);
  }
  
  public static void startApp(Context paramContext, String paramString)
  {
    paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
  }
  
  public static void startDisconnectCounter()
  {
    KexinData.disconnectCounter = 1;
    CMTracer.i("OtherHelper", "startDisconnectCounter");
  }
  
  public static void stopDisconnectCounter()
  {
    KexinData.disconnectCounter = 0;
    CMTracer.i("OtherHelper", "stopDisconnectCounter");
  }
  
  public static int totalBlockCount(ArrayList<String> paramArrayList, int paramInt)
  {
    int i = 0;
    if ((paramArrayList == null) || (paramInt < 1)) {
      return 0;
    }
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      String str = (String)paramArrayList.next();
      File localFile = new File(str);
      if ((str != null) && (localFile.exists())) {
        i = (int)(i + (localFile.length() + paramInt - 1L) / paramInt);
      }
    }
    return i;
  }
  
  public static boolean willOnline(Context paramContext)
  {
    return happenOnceEveryXHours(paramContext, SharedPreferencesManager.NEED_ONLINE, 48);
  }
  
  public static boolean willPopDialog(Context paramContext)
  {
    long l = SharedPreferencesManager.getSharedLongPreferences("ShowDialogTime", paramContext);
    if (l == 0L) {
      SharedPreferencesManager.setSharedLongPreferences("ShowDialogTime", System.currentTimeMillis(), paramContext);
    }
    while ((System.currentTimeMillis() - l) / 1000L / 60L / 60L < 24L) {
      return false;
    }
    SharedPreferencesManager.setSharedLongPreferences("ShowDialogTime", System.currentTimeMillis(), paramContext);
    return true;
  }
  
  public Bitmap getBitmap(String paramString1, String paramString2)
  {
    return BitmapFactory.decodeFile(paramString1 + paramString2);
  }
  
  public void savaCmnHttpBitmap(String paramString, Bitmap paramBitmap)
    throws IOException
  {
    if (paramBitmap == null) {
      return;
    }
    makeDir(AppConstants.FIRST_LEVEL_HTTPIMAGES);
    paramString = new FileOutputStream(new File(AppConstants.FIRST_LEVEL_HTTPIMAGES + paramString));
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, paramString);
    paramString.flush();
    paramString.close();
  }
  
  public class APP_UPDATE_TYPE
  {
    public static final int UPDATE_FORCE = 1;
    public static final int UPDATE_NONE = 0;
    public static final int UPDATE_RECOMMEND = 2;
    
    public APP_UPDATE_TYPE() {}
  }
  
  public static abstract interface CopyFileCallback
  {
    public static final int FAIL = 1;
    public static final int SUCCESS = 0;
    
    public abstract void over(int paramInt);
    
    public abstract void updateProgress(long paramLong1, long paramLong2);
  }
}
