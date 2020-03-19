package com.labs.merlinbirdid.packs;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.labs.merlinbirdid.orm.dao.PackDao;
import com.labs.merlinbirdid.orm.model.PackModel;
import com.labs.merlinbirdid.service.packs.PackInstallStateManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PackDataProvider
{
  public static final int PKG_STATUS_DOWNLOADING = 2;
  public static final int PKG_STATUS_INSTALLED = 1;
  public static final int PKG_STATUS_INSTALLING = 3;
  public static final int PKG_STATUS_OTHER = 4;
  public static final int PKG_STATUS_UPDATE_AVAILABLE = 5;
  private static final String TAG = "PackDataProvider";
  
  public PackDataProvider() {}
  
  public static String getBaseDownloadUrl(Context paramContext)
  {
    return paramContext.getString(2131165338);
  }
  
  public static String getZipFilename(Pack paramPack)
  {
    return paramPack.getId() + ".zip";
  }
  
  private boolean isPackageBeingDownloaded(Pack paramPack, Context paramContext)
  {
    return getDownloadId(paramPack, paramContext) != -1L;
  }
  
  private boolean isPackageBeingInstalled(Pack paramPack)
  {
    return PackInstallStateManager.getInstance().isPackBeingInstalled(paramPack);
  }
  
  public long getDownloadId(Pack paramPack, Context paramContext)
  {
    Log.d("PackDataProvider", "getDownloadId() pkg = " + paramPack);
    Object localObject = new DownloadManager.Query();
    ((DownloadManager.Query)localObject).setFilterByStatus(7);
    paramContext = ((DownloadManager)paramContext.getSystemService("download")).query((DownloadManager.Query)localObject);
    try
    {
      while (paramContext.moveToNext())
      {
        localObject = paramContext.getString(paramContext.getColumnIndex("uri"));
        Log.d("PackDataProvider", "getDownloadId() - uri = " + (String)localObject);
        if ((localObject != null) && (((String)localObject).endsWith(getZipFilename(paramPack))))
        {
          long l = paramContext.getLong(paramContext.getColumnIndex("_id"));
          Log.d("PackDataProvider", "getDownloadId() - id = " + l);
          return l;
        }
      }
      return -1L;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  public List<String> getInstalledPackages()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = new PackDao().getInstalledPackages();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localArrayList.add(((PackModel)((Iterator)localObject).next()).getPkgId());
      }
    }
    return localArrayList;
  }
  
  public int getPackageStatus(Pack paramPack, Context paramContext)
  {
    if (paramPack.isInstalled())
    {
      if (paramPack.isUpdate()) {
        return 5;
      }
      return 1;
    }
    if (isPackageBeingInstalled(paramPack)) {
      return 3;
    }
    if (isPackageBeingDownloaded(paramPack, paramContext)) {
      return 2;
    }
    return 4;
  }
}
