package dev.dworks.apps.anexplorer.provider;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.SparseArray;
import com.jaredrummler.android.processes.ProcessManager;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import dev.dworks.apps.anexplorer.DocumentsApplication;
import dev.dworks.apps.anexplorer.cursor.MatrixCursor;
import dev.dworks.apps.anexplorer.cursor.MatrixCursor.RowBuilder;
import dev.dworks.apps.anexplorer.misc.FileUtils;
import dev.dworks.apps.anexplorer.misc.RootsCache;
import dev.dworks.apps.anexplorer.misc.StorageUtils;
import dev.dworks.apps.anexplorer.model.DocumentsContract;
import dev.dworks.apps.anexplorer.model.RootInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"DefaultLocale"})
public class AppsProvider
  extends DocumentsProvider
{
  private static final String[] DEFAULT_DOCUMENT_PROJECTION;
  private static final String[] DEFAULT_ROOT_PROJECTION = { "root_id", "flags", "icon", "title", "document_id", "available_bytes", "total_bytes" };
  private static SparseArray<String> processTypeCache;
  private ActivityManager activityManager;
  private PackageManager packageManager;
  
  static
  {
    DEFAULT_DOCUMENT_PROJECTION = new String[] { "document_id", "mime_type", "path", "_display_name", "summary", "last_modified", "flags", "_size" };
    SparseArray localSparseArray = new SparseArray();
    processTypeCache = localSparseArray;
    localSparseArray.put(300, "Service");
    processTypeCache.put(400, "Background");
    processTypeCache.put(100, "Foreground");
    processTypeCache.put(200, "Visible");
    processTypeCache.put(500, "Empty");
  }
  
  public AppsProvider() {}
  
  private static String getAppName(String paramString)
  {
    String str1 = paramString;
    str3 = str1;
    for (;;)
    {
      try
      {
        int i = paramString.lastIndexOf('.');
        if (i == -1) {
          continue;
        }
        str3 = str1;
        str1 = paramString.substring(i + 1);
        str2 = str1;
        str3 = str1;
        if (str1.equalsIgnoreCase("android"))
        {
          str3 = str1;
          i = paramString.substring(0, i).lastIndexOf('.');
          if (i == -1) {
            continue;
          }
          str3 = str1;
          str2 = paramString.substring(i + 1);
        }
      }
      catch (Exception paramString)
      {
        String str2 = str3;
        continue;
      }
      if (!TextUtils.isEmpty(str2)) {
        continue;
      }
      return str2;
      str1 = paramString;
      continue;
      str2 = paramString;
    }
    return Character.toUpperCase(str2.charAt(0)) + str2.substring(1);
  }
  
  private static String getAppVersion(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    return "-" + paramString;
  }
  
  private static String getDocIdForApp(String paramString1, String paramString2)
  {
    return paramString1 + ":" + paramString2;
  }
  
  public static String getPackageForDocId(String paramString)
  {
    return paramString.substring(paramString.indexOf(':', 1) + 1);
  }
  
  private static void includeAppFromPackage(MatrixCursor paramMatrixCursor, String paramString1, PackageInfo paramPackageInfo, String paramString2)
  {
    ApplicationInfo localApplicationInfo = paramPackageInfo.applicationInfo;
    if ((localApplicationInfo.flags != 0) && (((localApplicationInfo.flags & 0x80) != 0) || ((localApplicationInfo.flags & 0x1) == 0))) {}
    String str;
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        str = paramPackageInfo.packageName;
        if ((paramString2 == null) || (str.toLowerCase().contains(paramString2))) {
          break;
        }
      }
      return;
    }
    paramString2 = localApplicationInfo.sourceDir;
    long l1 = new File(localApplicationInfo.sourceDir).length();
    long l2 = paramPackageInfo.lastUpdateTime;
    paramMatrixCursor = paramMatrixCursor.newRow();
    paramMatrixCursor.add("document_id", getDocIdForApp(paramString1, str));
    paramMatrixCursor.add("_display_name", getAppName(str) + getAppVersion(paramPackageInfo.versionName));
    paramMatrixCursor.add("summary", str);
    paramMatrixCursor.add("_size", Long.valueOf(l1));
    paramMatrixCursor.add("mime_type", "application/vnd.android.package-archive");
    paramMatrixCursor.add("last_modified", Long.valueOf(l2));
    paramMatrixCursor.add("path", paramString2);
    paramMatrixCursor.add("flags", Integer.valueOf(131077));
  }
  
  private void includeAppFromProcess(MatrixCursor paramMatrixCursor, String paramString1, ActivityManager.RunningAppProcessInfo paramRunningAppProcessInfo, String paramString2)
  {
    Object localObject1;
    String str;
    if ((paramRunningAppProcessInfo.importance != 500) && (paramRunningAppProcessInfo.importance != 130))
    {
      localObject1 = paramRunningAppProcessInfo.processName;
      str = ((String)localObject1).substring(((String)localObject1).lastIndexOf(".") + 1, ((String)localObject1).length());
      localObject1 = "";
      localObject2 = null;
    }
    try
    {
      localApplicationInfo = this.packageManager.getPackageInfo(paramRunningAppProcessInfo.processName, 1).applicationInfo;
      localObject1 = str;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ApplicationInfo localApplicationInfo;
        Object localObject3 = localObject2;
      }
    }
    localObject2 = localObject1;
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = str;
    }
    if ((paramString2 != null) && (!((String)localObject2).toLowerCase().contains(paramString2))) {
      return;
    }
    if (localApplicationInfo != null) {}
    for (paramString2 = localApplicationInfo.sourceDir;; paramString2 = "")
    {
      localObject1 = (String)processTypeCache.get(paramRunningAppProcessInfo.importance);
      int i = paramRunningAppProcessInfo.pid;
      long l = this.activityManager.getProcessMemoryInfo(new int[] { i })[0].getTotalPss() * 1024;
      paramRunningAppProcessInfo = paramRunningAppProcessInfo.processName;
      paramMatrixCursor = paramMatrixCursor.newRow();
      paramMatrixCursor.add("document_id", getDocIdForApp(paramString1, paramRunningAppProcessInfo));
      paramMatrixCursor.add("_display_name", localObject2);
      paramMatrixCursor.add("summary", localObject1);
      paramMatrixCursor.add("_size", Long.valueOf(l));
      paramMatrixCursor.add("mime_type", "application/vnd.android.package-archive");
      paramMatrixCursor.add("path", paramString2);
      paramMatrixCursor.add("flags", Integer.valueOf(5));
      return;
    }
  }
  
  public static void notifyDocumentsChanged(Context paramContext, String paramString)
  {
    paramString = DocumentsContract.buildChildDocumentsUri("dev.dworks.apps.anexplorer.apps.documents", paramString);
    paramContext.getContentResolver().notifyChange(paramString, null, false);
  }
  
  public static void notifyRootsChanged(Context paramContext)
  {
    paramContext.getContentResolver().notifyChange(DocumentsContract.buildRootsUri("dev.dworks.apps.anexplorer.apps.documents"), null, false);
  }
  
  private static String[] resolveDocumentProjection(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null) {
      return paramArrayOfString;
    }
    return DEFAULT_DOCUMENT_PROJECTION;
  }
  
  public final void deleteDocument(String paramString)
    throws FileNotFoundException
  {
    String str1 = paramString.substring(0, paramString.indexOf(':', 1));
    String str2 = getPackageForDocId(paramString);
    l = Binder.clearCallingIdentity();
    for (;;)
    {
      try
      {
        boolean bool = "apps".equals(str1);
        if (!bool) {}
      }
      finally
      {
        Binder.restoreCallingIdentity(l);
      }
      try
      {
        paramString = Uri.fromParts("package", str2, null);
        if (paramString != null)
        {
          paramString = new Intent("android.intent.action.DELETE", paramString);
          paramString.setFlags(268435456);
          getContext().startActivity(paramString);
        }
      }
      catch (Exception paramString)
      {
        continue;
      }
      Binder.restoreCallingIdentity(l);
      return;
      this.activityManager.killBackgroundProcesses(getPackageForDocId(paramString));
    }
  }
  
  public final void moveDocument(String paramString1, String paramString2, boolean paramBoolean)
    throws FileNotFoundException
  {
    Object localObject2 = getPackageForDocId(paramString1);
    Object localObject1 = "";
    String str = "";
    paramString2 = str;
    paramString1 = (String)localObject1;
    try
    {
      PackageInfo localPackageInfo = this.packageManager.getPackageInfo((String)localObject2, 0);
      paramString2 = str;
      paramString1 = (String)localObject1;
      localObject2 = localPackageInfo.applicationInfo;
      paramString2 = str;
      paramString1 = (String)localObject1;
      localObject1 = ((ApplicationInfo)localObject2).sourceDir;
      paramString2 = str;
      paramString1 = (String)localObject1;
      if (((ApplicationInfo)localObject2).loadLabel(this.packageManager) == null) {
        break label252;
      }
      paramString2 = str;
      paramString1 = (String)localObject1;
      localObject2 = ((ApplicationInfo)localObject2).loadLabel(this.packageManager);
      paramString2 = str;
      paramString1 = (String)localObject1;
      localObject2 = (String)localObject2;
      paramString2 = (String)localObject2;
      paramString1 = (String)localObject1;
      localObject2 = (String)localObject2 + getAppVersion(localPackageInfo.versionName);
      paramString2 = (String)localObject2;
      paramString1 = (String)localObject1;
    }
    catch (Exception localException)
    {
      label252:
      label276:
      for (;;) {}
    }
    localObject1 = DocumentsApplication.getRootsCache(getContext()).getDefaultRoot();
    if (localObject1 != null) {}
    for (localObject1 = new File(((RootInfo)localObject1).path);; localObject1 = Environment.getExternalStorageDirectory())
    {
      paramString1 = new File(paramString1);
      localObject1 = new File((File)localObject1, "AppBackup");
      if (!((File)localObject1).exists()) {
        ((File)localObject1).mkdir();
      }
      if (FileUtils.moveFile(paramString1, (File)localObject1, paramString2)) {
        break label276;
      }
      throw new IllegalStateException("Failed to copy " + paramString1);
      paramString2 = str;
      paramString1 = (String)localObject1;
      localObject2 = ((ApplicationInfo)localObject2).packageName;
      break;
    }
    FileUtils.updateMedia(getContext(), FileUtils.makeFilePath(((File)localObject1).getPath(), paramString2 + "." + FileUtils.getExtFromFilename(paramString1.getPath())));
  }
  
  public boolean onCreate()
  {
    this.packageManager = getContext().getPackageManager();
    this.activityManager = ((ActivityManager)getContext().getSystemService("activity"));
    return true;
  }
  
  public final ParcelFileDescriptor openDocument$15ff3859(String paramString1, String paramString2)
    throws FileNotFoundException
  {
    Binder.restoreCallingIdentity(Binder.clearCallingIdentity());
    return null;
  }
  
  public final AssetFileDescriptor openDocumentThumbnail$2e6c2445(String paramString)
    throws FileNotFoundException
  {
    return new AssetFileDescriptor(openDocument$15ff3859(paramString, "r"), 0L, -1L);
  }
  
  public final Cursor queryChildDocuments$11aa9eef(String paramString, String[] paramArrayOfString)
    throws FileNotFoundException
  {
    MatrixCursor localMatrixCursor = new MatrixCursor(resolveDocumentProjection(paramArrayOfString), (byte)0);
    long l = Binder.clearCallingIdentity();
    try
    {
      if ("apps".equals(paramString))
      {
        paramArrayOfString = this.packageManager.getInstalledPackages(8192).iterator();
        while (paramArrayOfString.hasNext()) {
          includeAppFromPackage(localMatrixCursor, paramString, (PackageInfo)paramArrayOfString.next(), null);
        }
      }
      paramArrayOfString = getContext();
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
    int i;
    if (Build.VERSION.SDK_INT >= 22)
    {
      Object localObject = ProcessManager.getRunningAppProcesses();
      paramArrayOfString = new ArrayList();
      localObject = ((List)localObject).iterator();
      for (;;)
      {
        if (!((Iterator)localObject).hasNext()) {
          break label293;
        }
        AndroidAppProcess localAndroidAppProcess = (AndroidAppProcess)((Iterator)localObject).next();
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = new ActivityManager.RunningAppProcessInfo(localAndroidAppProcess.name, localAndroidAppProcess.pid, null);
        localRunningAppProcessInfo.uid = localAndroidAppProcess.uid;
        if (!localAndroidAppProcess.foreground) {
          break;
        }
        i = 100;
        localRunningAppProcessInfo.importance = i;
        paramArrayOfString.add(localRunningAppProcessInfo);
      }
    }
    label293:
    for (;;)
    {
      paramArrayOfString = paramArrayOfString.iterator();
      for (;;)
      {
        if (paramArrayOfString.hasNext())
        {
          includeAppFromProcess(localMatrixCursor, paramString, (ActivityManager.RunningAppProcessInfo)paramArrayOfString.next(), null);
          continue;
          paramArrayOfString = ((ActivityManager)paramArrayOfString.getSystemService("activity")).getRunningAppProcesses();
          break;
        }
      }
      Binder.restoreCallingIdentity(l);
      paramString = DocumentsContract.buildChildDocumentsUri("dev.dworks.apps.anexplorer.apps.documents", paramString);
      localMatrixCursor.setNotificationUri(getContext().getContentResolver(), paramString);
      notifyRootsChanged(getContext());
      return localMatrixCursor;
      i = 400;
      break;
    }
  }
  
  public final Cursor queryDocument(String paramString, String[] paramArrayOfString)
    throws FileNotFoundException
  {
    paramArrayOfString = new MatrixCursor(resolveDocumentProjection(paramArrayOfString), (byte)0);
    MatrixCursor.RowBuilder localRowBuilder = paramArrayOfString.newRow();
    localRowBuilder.add("document_id", paramString);
    localRowBuilder.add("mime_type", "vnd.android.document/directory");
    localRowBuilder.add("flags", Integer.valueOf(5));
    return paramArrayOfString;
  }
  
  public final Cursor queryRoots(String[] paramArrayOfString)
    throws FileNotFoundException
  {
    StorageUtils localStorageUtils = new StorageUtils(getContext());
    if (paramArrayOfString != null) {}
    for (;;)
    {
      paramArrayOfString = new MatrixCursor(paramArrayOfString, (byte)0);
      MatrixCursor.RowBuilder localRowBuilder = paramArrayOfString.newRow();
      localRowBuilder.add("root_id", "apps");
      localRowBuilder.add("flags", Integer.valueOf(655370));
      localRowBuilder.add("icon", Integer.valueOf(2130837697));
      localRowBuilder.add("title", getContext().getString(2131165363));
      localRowBuilder.add("document_id", "apps");
      localRowBuilder.add("available_bytes", Long.valueOf(localStorageUtils.getPartionSize(2, false)));
      localRowBuilder.add("total_bytes", Long.valueOf(localStorageUtils.getPartionSize(2, true)));
      localRowBuilder = paramArrayOfString.newRow();
      localRowBuilder.add("root_id", "process");
      localRowBuilder.add("flags", Integer.valueOf(655370));
      localRowBuilder.add("icon", Integer.valueOf(2130837708));
      localRowBuilder.add("title", getContext().getString(2131165373));
      localRowBuilder.add("document_id", "process");
      localRowBuilder.add("available_bytes", Long.valueOf(localStorageUtils.getPartionSize(4, false)));
      localRowBuilder.add("total_bytes", Long.valueOf(localStorageUtils.getPartionSize(4, true)));
      return paramArrayOfString;
      paramArrayOfString = DEFAULT_ROOT_PROJECTION;
    }
  }
  
  public final Cursor querySearchDocuments(String paramString1, String paramString2, String[] paramArrayOfString)
    throws FileNotFoundException
  {
    paramArrayOfString = new MatrixCursor(resolveDocumentProjection(paramArrayOfString), (byte)0);
    if ("apps".equals(paramString1))
    {
      localIterator = this.packageManager.getInstalledPackages(8192).iterator();
      while (localIterator.hasNext()) {
        includeAppFromPackage(paramArrayOfString, paramString1, (PackageInfo)localIterator.next(), paramString2.toLowerCase());
      }
    }
    Iterator localIterator = this.activityManager.getRunningAppProcesses().iterator();
    while (localIterator.hasNext()) {
      includeAppFromProcess(paramArrayOfString, paramString1, (ActivityManager.RunningAppProcessInfo)localIterator.next(), paramString2.toLowerCase());
    }
    paramString1 = DocumentsContract.buildChildDocumentsUri("dev.dworks.apps.anexplorer.apps.documents", paramString1);
    paramArrayOfString.setNotificationUri(getContext().getContentResolver(), paramString1);
    return paramArrayOfString;
  }
}
