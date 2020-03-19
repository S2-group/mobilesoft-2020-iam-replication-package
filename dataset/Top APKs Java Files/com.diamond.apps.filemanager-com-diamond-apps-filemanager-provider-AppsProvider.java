package com.diamond.apps.filemanager.provider;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Point;
import android.os.Binder;
import android.os.CancellationSignal;
import android.os.Debug.MemoryInfo;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.SparseArray;
import com.diamond.apps.filemanager.DocumentsApplication;
import com.diamond.apps.filemanager.cursor.MatrixCursor;
import com.diamond.apps.filemanager.cursor.MatrixCursor.RowBuilder;
import com.diamond.apps.filemanager.misc.FileUtils;
import com.diamond.apps.filemanager.misc.PackageManagerUtils;
import com.diamond.apps.filemanager.misc.StorageUtils;
import com.diamond.apps.filemanager.misc.Utils;
import com.diamond.apps.filemanager.model.DocumentsContract;
import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"DefaultLocale"})
public class AppsProvider
  extends DocumentsProvider
{
  public static final String AUTHORITY = "com.diamond.apps.filemanager.apps.documents";
  private static final String[] DEFAULT_DOCUMENT_PROJECTION;
  private static final String[] DEFAULT_ROOT_PROJECTION = { "root_id", "flags", "icon", "title", "document_id", "available_bytes", "capacity_bytes" };
  public static final String ROOT_ID_PROCESS = "process:";
  public static final String ROOT_ID_SYSTEM_APP = "system_apps:";
  public static final String ROOT_ID_USER_APP = "user_apps:";
  private static SparseArray<String> processTypeCache;
  private ActivityManager activityManager;
  private PackageManager packageManager;
  
  static
  {
    DEFAULT_DOCUMENT_PROJECTION = new String[] { "document_id", "mime_type", "path", "_display_name", "summary", "last_modified", "flags", "_size" };
    processTypeCache = new SparseArray();
    processTypeCache.put(300, "Service");
    processTypeCache.put(400, "Background");
    processTypeCache.put(100, "Foreground");
    processTypeCache.put(200, "Visible");
    processTypeCache.put(500, "Empty");
  }
  
  public AppsProvider() {}
  
  private static String capitalize(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Character.toUpperCase(paramString.charAt(0)));
    localStringBuilder.append(paramString.substring(1));
    return localStringBuilder.toString();
  }
  
  private static String getAppName(String paramString)
  {
    for (;;)
    {
      Object localObject;
      try
      {
        i = paramString.lastIndexOf('.');
        if (i != -1) {
          str = paramString.substring(i + 1);
        } else {
          str = paramString;
        }
        localObject = str;
      }
      catch (Exception localException)
      {
        int i;
        String str;
        localObject = paramString;
        continue;
      }
      try
      {
        if (str.equalsIgnoreCase("android"))
        {
          i = paramString.substring(0, i).lastIndexOf('.');
          localObject = paramString;
          if (i != -1) {
            localObject = paramString.substring(i + 1);
          }
        }
      }
      catch (Exception paramString)
      {
        localObject = localException;
      }
    }
    return capitalize((String)localObject);
  }
  
  private static String getAppVersion(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("-");
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }
  
  public static String getDocIdForApp(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append(paramString2);
    return localStringBuilder.toString();
  }
  
  public static String getPackageForDocId(String paramString)
  {
    return paramString.substring(paramString.indexOf(':', 1) + 1);
  }
  
  private long getProcessSize(int paramInt)
  {
    return this.activityManager.getProcessMemoryInfo(new int[] { paramInt })[0].getTotalPss() * 1024;
  }
  
  public static List<ActivityManager.RunningAppProcessInfo> getRunningAppProcessInfo(Context paramContext)
  {
    Object localObject = (ActivityManager)paramContext.getSystemService("activity");
    ArrayList localArrayList = new ArrayList();
    paramContext = "";
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    int i;
    if (Utils.hasNougat())
    {
      localObject = ((ActivityManager)localObject).getRunningServices(1000).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)((Iterator)localObject).next();
        localRunningAppProcessInfo = new ActivityManager.RunningAppProcessInfo(localRunningServiceInfo.process, localRunningServiceInfo.pid, null);
        localRunningAppProcessInfo.uid = localRunningServiceInfo.uid;
        if (localRunningServiceInfo.foreground) {
          i = 100;
        } else {
          i = 400;
        }
        localRunningAppProcessInfo.importance = i;
        if (!paramContext.equals(localRunningServiceInfo.process))
        {
          paramContext = localRunningServiceInfo.process;
          localArrayList.add(localRunningAppProcessInfo);
        }
      }
      return localArrayList;
    }
    if (Utils.hasLollipopMR1())
    {
      paramContext = AndroidProcesses.getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        localObject = (AndroidAppProcess)paramContext.next();
        localRunningAppProcessInfo = new ActivityManager.RunningAppProcessInfo(((AndroidAppProcess)localObject).name, ((AndroidAppProcess)localObject).pid, null);
        localRunningAppProcessInfo.uid = ((AndroidAppProcess)localObject).uid;
        if (((AndroidAppProcess)localObject).foreground) {
          i = 100;
        } else {
          i = 400;
        }
        localRunningAppProcessInfo.importance = i;
        localArrayList.add(localRunningAppProcessInfo);
      }
      return localArrayList;
    }
    return ((ActivityManager)localObject).getRunningAppProcesses();
  }
  
  private void includeAppFromPackage(MatrixCursor paramMatrixCursor, String paramString1, PackageInfo paramPackageInfo, boolean paramBoolean, String paramString2)
  {
    ApplicationInfo localApplicationInfo = paramPackageInfo.applicationInfo;
    if (paramBoolean == isSystemApp(localApplicationInfo))
    {
      String str = paramPackageInfo.packageName;
      if ((paramString2 != null) && (!str.toLowerCase().contains(paramString2))) {
        return;
      }
      paramString2 = localApplicationInfo.sourceDir;
      int i = 133;
      if (DocumentsApplication.isTelevision()) {
        i = 149;
      }
      long l1 = new File(localApplicationInfo.sourceDir).length();
      long l2 = paramPackageInfo.lastUpdateTime;
      paramMatrixCursor = paramMatrixCursor.newRow();
      paramMatrixCursor.add("document_id", getDocIdForApp(paramString1, str));
      paramString1 = new StringBuilder();
      paramString1.append(getAppName(str));
      paramString1.append(getAppVersion(paramPackageInfo.versionName));
      paramMatrixCursor.add("_display_name", paramString1.toString());
      paramMatrixCursor.add("summary", str);
      paramMatrixCursor.add("_size", Long.valueOf(l1));
      paramMatrixCursor.add("mime_type", "application/vnd.android.package-archive");
      paramMatrixCursor.add("last_modified", Long.valueOf(l2));
      paramMatrixCursor.add("path", paramString2);
      paramMatrixCursor.add("flags", Integer.valueOf(i));
    }
  }
  
  private void includeAppFromProcess(MatrixCursor paramMatrixCursor, String paramString1, ActivityManager.RunningAppProcessInfo paramRunningAppProcessInfo, String paramString2)
  {
    Object localObject2;
    if ((paramRunningAppProcessInfo.importance != 500) && (paramRunningAppProcessInfo.importance != 230))
    {
      localObject1 = paramRunningAppProcessInfo.processName;
      localObject2 = ((String)localObject1).substring(((String)localObject1).lastIndexOf(".") + 1, ((String)localObject1).length());
      localObject1 = "";
    }
    try
    {
      localApplicationInfo = this.packageManager.getPackageInfo(paramRunningAppProcessInfo.processName, 1).applicationInfo;
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      ApplicationInfo localApplicationInfo;
      int i;
      long l;
      for (;;) {}
    }
    localApplicationInfo = null;
    if (!TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = localObject1;
    }
    if ((paramString2 != null) && (!((String)localObject2).toLowerCase().contains(paramString2))) {
      return;
    }
    if (localApplicationInfo != null) {
      paramString2 = localApplicationInfo.sourceDir;
    } else {
      paramString2 = "";
    }
    i = 5;
    if (DocumentsApplication.isTelevision()) {
      i = 21;
    }
    Object localObject1 = (String)processTypeCache.get(paramRunningAppProcessInfo.importance);
    l = getProcessSize(paramRunningAppProcessInfo.pid);
    paramRunningAppProcessInfo = paramRunningAppProcessInfo.processName;
    paramMatrixCursor = paramMatrixCursor.newRow();
    paramMatrixCursor.add("document_id", getDocIdForApp(paramString1, paramRunningAppProcessInfo));
    paramMatrixCursor.add("_display_name", localObject2);
    paramMatrixCursor.add("summary", localObject1);
    paramMatrixCursor.add("_size", Long.valueOf(l));
    paramMatrixCursor.add("mime_type", "application/vnd.android.package-archive");
    paramMatrixCursor.add("path", paramString2);
    paramMatrixCursor.add("flags", Integer.valueOf(i));
  }
  
  private void includeAppFromProcess(MatrixCursor paramMatrixCursor, String paramString1, AndroidAppProcess paramAndroidAppProcess, String paramString2)
  {
    Object localObject1 = paramAndroidAppProcess.name;
    String str = paramAndroidAppProcess.getPackageName();
    Object localObject2 = ((String)localObject1).substring(((String)localObject1).lastIndexOf(".") + 1, ((String)localObject1).length());
    localObject1 = "";
    try
    {
      localApplicationInfo = this.packageManager.getPackageInfo(str, 1).applicationInfo;
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      ApplicationInfo localApplicationInfo;
      int i;
      int j;
      long l;
      for (;;) {}
    }
    localApplicationInfo = null;
    if (!TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = localObject1;
    }
    if ((paramString2 != null) && (!((String)localObject2).toLowerCase().contains(paramString2))) {
      return;
    }
    if (localApplicationInfo != null) {
      paramString2 = localApplicationInfo.sourceDir;
    } else {
      paramString2 = "";
    }
    i = 5;
    if (DocumentsApplication.isTelevision()) {
      i = 21;
    }
    if (paramAndroidAppProcess.foreground) {
      j = 100;
    } else {
      j = 400;
    }
    localObject1 = (String)processTypeCache.get(j);
    l = getProcessSize(paramAndroidAppProcess.pid);
    paramMatrixCursor = paramMatrixCursor.newRow();
    paramMatrixCursor.add("document_id", getDocIdForApp(paramString1, str));
    paramMatrixCursor.add("_display_name", localObject2);
    paramMatrixCursor.add("summary", localObject1);
    paramMatrixCursor.add("_size", Long.valueOf(l));
    paramMatrixCursor.add("mime_type", "application/vnd.android.package-archive");
    paramMatrixCursor.add("path", paramString2);
    paramMatrixCursor.add("flags", Integer.valueOf(i));
  }
  
  private void includeAppFromService(MatrixCursor paramMatrixCursor, String paramString1, ActivityManager.RunningServiceInfo paramRunningServiceInfo, String paramString2)
  {
    Object localObject1 = paramRunningServiceInfo.process;
    String str = paramRunningServiceInfo.process;
    Object localObject2 = ((String)localObject1).substring(((String)localObject1).lastIndexOf(".") + 1, ((String)localObject1).length());
    localObject1 = "";
    try
    {
      localApplicationInfo = this.packageManager.getPackageInfo(str, 1).applicationInfo;
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      ApplicationInfo localApplicationInfo;
      int i;
      int j;
      long l;
      for (;;) {}
    }
    localApplicationInfo = null;
    if (!TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = localObject1;
    }
    if ((paramString2 != null) && (!((String)localObject2).toLowerCase().contains(paramString2))) {
      return;
    }
    if (localApplicationInfo != null) {
      paramString2 = localApplicationInfo.sourceDir;
    } else {
      paramString2 = "";
    }
    i = 5;
    if (DocumentsApplication.isTelevision()) {
      i = 21;
    }
    if (paramRunningServiceInfo.foreground) {
      j = 100;
    } else {
      j = 400;
    }
    localObject1 = (String)processTypeCache.get(j);
    l = getProcessSize(paramRunningServiceInfo.pid);
    paramMatrixCursor = paramMatrixCursor.newRow();
    paramMatrixCursor.add("document_id", getDocIdForApp(paramString1, str));
    paramMatrixCursor.add("_display_name", localObject2);
    paramMatrixCursor.add("summary", localObject1);
    paramMatrixCursor.add("_size", Long.valueOf(l));
    paramMatrixCursor.add("mime_type", "application/vnd.android.package-archive");
    paramMatrixCursor.add("path", paramString2);
    paramMatrixCursor.add("flags", Integer.valueOf(i));
  }
  
  private void includeDefaultDocument(MatrixCursor paramMatrixCursor, String paramString)
  {
    paramMatrixCursor = paramMatrixCursor.newRow();
    paramMatrixCursor.add("document_id", paramString);
    paramMatrixCursor.add("mime_type", "vnd.android.document/directory");
    paramMatrixCursor.add("flags", Integer.valueOf(5));
  }
  
  private static boolean isAppUseful(ApplicationInfo paramApplicationInfo)
  {
    int i = paramApplicationInfo.flags;
    boolean bool = true;
    if (i != 0)
    {
      if ((paramApplicationInfo.flags & 0x80) != 0) {
        return bool;
      }
      if ((paramApplicationInfo.flags & 0x1) == 0) {
        return true;
      }
    }
    bool = false;
    return bool;
  }
  
  private static boolean isSystemApp(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags != 0) && ((paramApplicationInfo.flags & 0x81) > 0);
  }
  
  public static void notifyDocumentsChanged(Context paramContext, String paramString)
  {
    paramString = DocumentsContract.buildChildDocumentsUri("com.diamond.apps.filemanager.apps.documents", paramString);
    paramContext.getContentResolver().notifyChange(paramString, null, false);
  }
  
  private void notifyDocumentsChanged(String paramString)
  {
    paramString = DocumentsContract.buildChildDocumentsUri("com.diamond.apps.filemanager.apps.documents", getParentRootIdForDocId(paramString));
    getContext().getContentResolver().notifyChange(paramString, null, false);
  }
  
  public static void notifyRootsChanged(Context paramContext)
  {
    paramContext.getContentResolver().notifyChange(DocumentsContract.buildRootsUri("com.diamond.apps.filemanager.apps.documents"), null, false);
  }
  
  private static String[] resolveDocumentProjection(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null) {
      return paramArrayOfString;
    }
    return DEFAULT_DOCUMENT_PROJECTION;
  }
  
  private static String[] resolveRootProjection(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null) {
      return paramArrayOfString;
    }
    return DEFAULT_ROOT_PROJECTION;
  }
  
  public String copyDocument(String paramString1, String paramString2)
    throws FileNotFoundException
  {
    paramString1 = getPackageForDocId(paramString1);
    for (;;)
    {
      try
      {
        localObject1 = this.packageManager.getPackageInfo(paramString1, 0);
        paramString1 = ((PackageInfo)localObject1).applicationInfo;
        paramString2 = paramString1.sourceDir;
      }
      catch (Exception paramString1)
      {
        Object localObject1;
        Object localObject2;
        Object localObject3;
        StringBuilder localStringBuilder;
        continue;
      }
      try
      {
        if (paramString1.loadLabel(this.packageManager) != null) {
          paramString1 = paramString1.loadLabel(this.packageManager);
        } else {
          paramString1 = paramString1.packageName;
        }
        paramString1 = (String)paramString1;
      }
      catch (Exception paramString1)
      {
        continue;
      }
      try
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(paramString1);
        ((StringBuilder)localObject2).append(getAppVersion(((PackageInfo)localObject1).versionName));
        localObject1 = ((StringBuilder)localObject2).toString();
        paramString1 = (String)localObject1;
      }
      catch (Exception localException) {}
    }
    break label107;
    paramString2 = "";
    paramString1 = "";
    label107:
    localObject1 = new File(paramString2);
    localObject3 = Utils.getAppsBackupFile(getContext());
    if (!((File)localObject3).exists()) {
      ((File)localObject3).mkdir();
    }
    if (!FileUtils.moveDocument((File)localObject1, (File)localObject3, paramString1))
    {
      paramString1 = new StringBuilder();
      paramString1.append("Failed to copy ");
      paramString1.append(localObject1);
      throw new IllegalStateException(paramString1.toString());
    }
    localObject2 = getContext();
    localObject3 = ((File)localObject3).getPath();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append(".");
    localStringBuilder.append(FileUtils.getExtFromFilename(((File)localObject1).getPath()));
    FileUtils.updateMediaStore((Context)localObject2, FileUtils.makeFilePath((String)localObject3, localStringBuilder.toString()));
    return paramString2;
  }
  
  public void deleteDocument(String paramString)
    throws FileNotFoundException
  {
    String str = getPackageForDocId(paramString);
    long l = Binder.clearCallingIdentity();
    try
    {
      if (paramString.startsWith("user_apps:")) {
        PackageManagerUtils.uninstallApp(getContext(), str);
      } else if (paramString.startsWith("process:")) {
        this.activityManager.killBackgroundProcesses(getPackageForDocId(paramString));
      }
      notifyDocumentsChanged(paramString);
      return;
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
  }
  
  public boolean onCreate()
  {
    this.packageManager = getContext().getPackageManager();
    this.activityManager = ((ActivityManager)getContext().getSystemService("activity"));
    return true;
  }
  
  public ParcelFileDescriptor openDocument(String paramString1, String paramString2, CancellationSignal paramCancellationSignal)
    throws FileNotFoundException
  {
    Binder.restoreCallingIdentity(Binder.clearCallingIdentity());
    return null;
  }
  
  public AssetFileDescriptor openDocumentThumbnail(String paramString, Point paramPoint, CancellationSignal paramCancellationSignal)
    throws FileNotFoundException
  {
    return new AssetFileDescriptor(openDocument(paramString, "r", paramCancellationSignal), 0L, -1L);
  }
  
  public Cursor queryChildDocuments(String paramString1, String[] paramArrayOfString, String paramString2)
    throws FileNotFoundException
  {
    paramArrayOfString = new DocumentCursor(resolveDocumentProjection(paramArrayOfString), paramString1);
    long l = Binder.clearCallingIdentity();
    try
    {
      if (paramString1.startsWith("user_apps:"))
      {
        paramString2 = this.packageManager.getInstalledPackages(8192).iterator();
        while (paramString2.hasNext()) {
          includeAppFromPackage(paramArrayOfString, paramString1, (PackageInfo)paramString2.next(), false, null);
        }
      }
      if (paramString1.startsWith("system_apps:"))
      {
        paramString2 = this.packageManager.getInstalledPackages(8192).iterator();
        while (paramString2.hasNext()) {
          includeAppFromPackage(paramArrayOfString, paramString1, (PackageInfo)paramString2.next(), true, null);
        }
      }
      if (paramString1.startsWith("process:"))
      {
        if (Utils.hasNougat())
        {
          paramString2 = this.activityManager.getRunningServices(1000).iterator();
          while (paramString2.hasNext()) {
            includeAppFromService(paramArrayOfString, paramString1, (ActivityManager.RunningServiceInfo)paramString2.next(), null);
          }
        }
        if (Utils.hasLollipopMR1())
        {
          paramString2 = AndroidProcesses.getRunningAppProcesses().iterator();
          while (paramString2.hasNext()) {
            includeAppFromProcess(paramArrayOfString, paramString1, (AndroidAppProcess)paramString2.next(), null);
          }
        }
        paramString2 = this.activityManager.getRunningAppProcesses().iterator();
        while (paramString2.hasNext()) {
          includeAppFromProcess(paramArrayOfString, paramString1, (ActivityManager.RunningAppProcessInfo)paramString2.next(), null);
        }
      }
      return paramArrayOfString;
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
  }
  
  public Cursor queryDocument(String paramString, String[] paramArrayOfString)
    throws FileNotFoundException
  {
    paramArrayOfString = new MatrixCursor(resolveDocumentProjection(paramArrayOfString));
    includeDefaultDocument(paramArrayOfString, paramString);
    return paramArrayOfString;
  }
  
  public Cursor queryRoots(String[] paramArrayOfString)
    throws FileNotFoundException
  {
    StorageUtils localStorageUtils = new StorageUtils(getContext());
    paramArrayOfString = new MatrixCursor(resolveRootProjection(paramArrayOfString));
    MatrixCursor.RowBuilder localRowBuilder = paramArrayOfString.newRow();
    localRowBuilder.add("root_id", "user_apps:");
    localRowBuilder.add("flags", Integer.valueOf(134348810));
    localRowBuilder.add("icon", Integer.valueOf(2131230924));
    localRowBuilder.add("title", getContext().getString(2131558592));
    localRowBuilder.add("document_id", "user_apps:");
    localRowBuilder.add("available_bytes", Long.valueOf(localStorageUtils.getPartionSize(2, false)));
    localRowBuilder.add("capacity_bytes", Long.valueOf(localStorageUtils.getPartionSize(2, true)));
    localRowBuilder = paramArrayOfString.newRow();
    localRowBuilder.add("root_id", "system_apps:");
    localRowBuilder.add("flags", Integer.valueOf(134348810));
    localRowBuilder.add("icon", Integer.valueOf(2131230924));
    localRowBuilder.add("title", getContext().getString(2131558611));
    localRowBuilder.add("document_id", "system_apps:");
    localRowBuilder.add("available_bytes", Long.valueOf(localStorageUtils.getPartionSize(2, false)));
    localRowBuilder.add("capacity_bytes", Long.valueOf(localStorageUtils.getPartionSize(2, true)));
    localRowBuilder = paramArrayOfString.newRow();
    localRowBuilder.add("root_id", "process:");
    localRowBuilder.add("flags", Integer.valueOf(134348810));
    localRowBuilder.add("icon", Integer.valueOf(2131230940));
    localRowBuilder.add("title", getContext().getString(2131558608));
    localRowBuilder.add("document_id", "process:");
    localRowBuilder.add("available_bytes", Long.valueOf(localStorageUtils.getPartionSize(4, false)));
    localRowBuilder.add("capacity_bytes", Long.valueOf(localStorageUtils.getPartionSize(4, true)));
    return paramArrayOfString;
  }
  
  public Cursor querySearchDocuments(String paramString1, String paramString2, String[] paramArrayOfString)
    throws FileNotFoundException
  {
    paramArrayOfString = new MatrixCursor(resolveDocumentProjection(paramArrayOfString));
    Iterator localIterator;
    if (paramString1.startsWith("user_apps:"))
    {
      localIterator = this.packageManager.getInstalledPackages(8192).iterator();
      while (localIterator.hasNext()) {
        includeAppFromPackage(paramArrayOfString, paramString1, (PackageInfo)localIterator.next(), false, paramString2.toLowerCase());
      }
    }
    if (paramString1.startsWith("system_apps:"))
    {
      localIterator = this.packageManager.getInstalledPackages(8192).iterator();
      while (localIterator.hasNext()) {
        includeAppFromPackage(paramArrayOfString, paramString1, (PackageInfo)localIterator.next(), true, paramString2.toLowerCase());
      }
    }
    if (paramString1.startsWith("process:"))
    {
      localIterator = this.activityManager.getRunningAppProcesses().iterator();
      while (localIterator.hasNext()) {
        includeAppFromProcess(paramArrayOfString, paramString1, (ActivityManager.RunningAppProcessInfo)localIterator.next(), paramString2.toLowerCase());
      }
    }
    return paramArrayOfString;
  }
  
  private class DocumentCursor
    extends MatrixCursor
  {
    public DocumentCursor(String[] paramArrayOfString, String paramString)
    {
      super();
      paramArrayOfString = DocumentsContract.buildChildDocumentsUri("com.diamond.apps.filemanager.apps.documents", paramString);
      setNotificationUri(AppsProvider.this.getContext().getContentResolver(), paramArrayOfString);
    }
    
    public void close()
    {
      super.close();
    }
  }
}
