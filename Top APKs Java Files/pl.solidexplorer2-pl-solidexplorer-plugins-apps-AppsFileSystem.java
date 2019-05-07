package pl.solidexplorer.plugins.apps;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import pl.solidexplorer.SEApp;
import pl.solidexplorer.common.Quota;
import pl.solidexplorer.common.exceptions.SEException;
import pl.solidexplorer.filesystem.FileMetadata;
import pl.solidexplorer.filesystem.FileSystem;
import pl.solidexplorer.filesystem.FileSystemDescriptor;
import pl.solidexplorer.filesystem.OpenCallback;
import pl.solidexplorer.filesystem.SEFile;
import pl.solidexplorer.filesystem.SEFile.LocationType;
import pl.solidexplorer.filesystem.SEFile.Type;
import pl.solidexplorer.filesystem.VirtualFile;
import pl.solidexplorer.filesystem.filters.FileFilter;
import pl.solidexplorer.filesystem.local.LocalFile;
import pl.solidexplorer.filesystem.local.LocalFileSystem;
import pl.solidexplorer.filesystem.search.Criteria;
import pl.solidexplorer.filesystem.search.ResultsChunk;
import pl.solidexplorer.panel.explorer.Explorer;
import pl.solidexplorer.util.ResUtils;
import pl.solidexplorer.util.SELog;
import pl.solidexplorer.util.Utils;

public class AppsFileSystem
  extends LocalFileSystem
{
  private BroadcastReceiver mBroadcastReceiver = new AppsFileSystem.1(this);
  private SEFile mRoot;
  private List<SEFile> mSystemApps = new ArrayList();
  private SEFile mSystemAppsDir;
  private List<SEFile> mUserApps = new ArrayList();
  private SEFile mUserAppsDir;
  
  public AppsFileSystem(String paramString, FileSystemDescriptor paramFileSystemDescriptor)
  {
    super(paramFileSystemDescriptor);
    this.mRoot = new SEFile().setId("/").setPath("/").setName(paramString).setDisplayName(ResUtils.getString(2131165510)).setType(SEFile.Type.DIRECTORY).setLocationType(SEFile.LocationType.VIRTUAL);
    this.mUserAppsDir = new SEFile().setParentAndName("/", ResUtils.getString(2131165894)).setParentId("/").setType(SEFile.Type.DIRECTORY).setLocationType(SEFile.LocationType.VIRTUAL);
    this.mUserAppsDir.setId("0");
    this.mSystemAppsDir = new SEFile().setParentAndName("/", ResUtils.getString(2131165816)).setParentId("/").setType(SEFile.Type.DIRECTORY).setLocationType(SEFile.LocationType.VIRTUAL);
    this.mSystemAppsDir.setId("1");
  }
  
  public boolean canCopy(FileSystem paramFileSystem, SEFile paramSEFile1, SEFile paramSEFile2)
  {
    return super.canCopy(paramFileSystem, ((VirtualFile)paramSEFile1).getRealFile(), paramSEFile2);
  }
  
  public boolean canMove(FileSystem paramFileSystem, SEFile paramSEFile1, SEFile paramSEFile2)
  {
    return super.canMove(paramFileSystem, ((VirtualFile)paramSEFile1).getRealFile(), paramSEFile2);
  }
  
  SEFile create(PackageManager paramPackageManager, PackageInfo paramPackageInfo, String paramString)
  {
    ApplicationInfo localApplicationInfo = paramPackageInfo.applicationInfo;
    VirtualFile localVirtualFile;
    if ((localApplicationInfo.flags & 0x1) != 0)
    {
      localVirtualFile = new VirtualFile(this.mSystemAppsDir.getPath(), localApplicationInfo.sourceDir);
      localVirtualFile.setParentId(this.mSystemAppsDir.getPath());
      this.mSystemApps.add(localVirtualFile);
    }
    for (;;)
    {
      paramPackageManager = localApplicationInfo.loadLabel(paramPackageManager).toString();
      localVirtualFile.putExtra("transfer_name", String.format("%s %s.apk", new Object[] { paramPackageManager, paramPackageInfo.versionName }));
      localVirtualFile.setId(localApplicationInfo.sourceDir).putExtra("package", localApplicationInfo.packageName).putExtra("details", String.format("%s\n%s: %s", new Object[] { localApplicationInfo.packageName, paramString, paramPackageInfo.versionName }));
      localVirtualFile.setDisplayName(paramPackageManager);
      return localVirtualFile;
      localVirtualFile = new VirtualFile(this.mUserAppsDir.getPath(), localApplicationInfo.sourceDir);
      localVirtualFile.setParentId(this.mUserAppsDir.getPath());
      this.mUserApps.add(localVirtualFile);
    }
  }
  
  protected void deleteImpl(SEFile paramSEFile)
    throws SEException
  {
    paramSEFile = paramSEFile.getExtra("package", null);
    if (Utils.isStringEmpty(paramSEFile)) {
      throw SEException.notAllowed();
    }
    Intent localIntent = new Intent("android.intent.action.DELETE").addFlags(268435456);
    localIntent.setData(Uri.parse("package:" + paramSEFile));
    SEApp.get().startActivity(localIntent);
  }
  
  public List<FileMetadata> extractMetadata(SEFile paramSEFile)
  {
    if ((paramSEFile instanceof VirtualFile)) {
      return super.extractMetadata(((VirtualFile)paramSEFile).getRealFile());
    }
    return null;
  }
  
  public SEFile getFileInstanceImpl(String paramString, SEFile paramSEFile)
    throws SEException
  {
    if (paramString.equals(this.mSystemAppsDir.getPath())) {
      return this.mSystemAppsDir;
    }
    if (paramString.equals(this.mUserAppsDir.getPath())) {
      return this.mUserAppsDir;
    }
    throw SEException.dirNotFound(paramString);
  }
  
  public SEFile.LocationType getLocationType()
  {
    return SEFile.LocationType.VIRTUAL;
  }
  
  public String getName()
  {
    return this.mRoot.getName();
  }
  
  public Intent getOpenIntent(SEFile paramSEFile)
  {
    Intent localIntent = new Intent(SEApp.get(), AppInfoViewer.class);
    localIntent.setData(((VirtualFile)paramSEFile).getRealFile().uri());
    return localIntent;
  }
  
  public Quota getQuotaImpl(String paramString)
    throws SEException
  {
    if ("/".equals(paramString)) {
      return getQuotaForPath("/data").merge(getQuotaForPath("/system"));
    }
    if (paramString.equals(this.mUserAppsDir.getPath())) {
      return getQuotaForPath("/data");
    }
    return getQuotaForPath("/system");
  }
  
  public List<SEFile> getRoots()
  {
    return Arrays.asList(new SEFile[] { this.mRoot });
  }
  
  public boolean isFeatureSupported(int paramInt)
  {
    return paramInt == 4;
  }
  
  public boolean isReadOnly(SEFile paramSEFile)
  {
    return true;
  }
  
  void listApps()
  {
    PackageManager localPackageManager = SEApp.get().getPackageManager();
    Object localObject = localPackageManager.getInstalledPackages(0);
    String str = ResUtils.getString(2131165911);
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      create(localPackageManager, (PackageInfo)((Iterator)localObject).next(), str);
    }
    this.mSystemAppsDir.putExtra("children_count", this.mSystemApps.size());
    this.mUserAppsDir.putExtra("children_count", this.mUserApps.size());
  }
  
  public List<SEFile> listImpl(SEFile paramSEFile, FileFilter paramFileFilter)
    throws SEException
  {
    if (paramSEFile.getPath().equals("/")) {
      return Arrays.asList(new SEFile[] { this.mUserAppsDir, this.mSystemAppsDir });
    }
    if (paramSEFile.getIdentity().indexOf('0') == 0) {
      return new ArrayList(this.mUserApps);
    }
    return new ArrayList(this.mSystemApps);
  }
  
  protected boolean mkdirImpl(SEFile paramSEFile)
    throws SEException
  {
    throw SEException.notSupported();
  }
  
  protected boolean mkfileImpl(SEFile paramSEFile)
    throws SEException
  {
    throw SEException.notSupported();
  }
  
  public void onAttach(Explorer paramExplorer)
  {
    super.onAttach(paramExplorer);
    paramExplorer = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    paramExplorer.addAction("android.intent.action.PACKAGE_REMOVED");
    paramExplorer.addDataScheme("package");
    SEApp.get().registerReceiver(this.mBroadcastReceiver, paramExplorer);
  }
  
  public void onDetach(Explorer paramExplorer)
  {
    super.onDetach(paramExplorer);
    try
    {
      SEApp.get().unregisterReceiver(this.mBroadcastReceiver);
      return;
    }
    catch (Throwable paramExplorer)
    {
      SELog.e(paramExplorer);
    }
  }
  
  public SEFile openFileSystemImpl(OpenCallback paramOpenCallback)
    throws SEException
  {
    listApps();
    return this.mRoot;
  }
  
  public InputStream readImpl(SEFile paramSEFile, long paramLong)
    throws SEException
  {
    Object localObject = paramSEFile;
    if ((paramSEFile instanceof VirtualFile)) {
      localObject = ((VirtualFile)paramSEFile).getRealFile();
    }
    return super.readImpl((SEFile)localObject, paramLong);
  }
  
  public ResultsChunk search(Criteria paramCriteria, ResultsChunk paramResultsChunk)
    throws SEException
  {
    return null;
  }
}
