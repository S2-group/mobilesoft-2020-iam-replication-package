package com.newbay.syncdrive.android.model.nab.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import com.synchronoss.android.util.Log;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DBMappingFinder
{
  private static final String TAG = DBMappingFinder.class.getSimpleName();
  private Context context;
  private Log log;
  
  @Inject
  public DBMappingFinder(Context paramContext, Log paramLog)
  {
    this.context = paramContext;
    this.log = paramLog;
  }
  
  private String getManufacturer()
  {
    String str2 = Build.MANUFACTURER.replace(" ", "").toLowerCase();
    String str1 = str2;
    if (str2.equalsIgnoreCase("unknown")) {
      str1 = htc_manufacturer_workaround();
    }
    return str1;
  }
  
  private String getModel()
  {
    return Build.DEVICE.replace(" ", "").toLowerCase();
  }
  
  private String htc_manufacturer_workaround()
  {
    Iterator localIterator = this.context.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.toLowerCase().startsWith("com.android.providers.htc")) {
        return "htc";
      }
    }
    this.log.a(TAG, "Dbmapping workaround for HTC device: ", new Object[0]);
    return "";
  }
  
  public InputStreamReader getDefaultDBMappingFile()
  {
    Object localObject2 = getModel();
    Object localObject1 = getManufacturer();
    localObject2 = (String)localObject1 + "_" + (String)localObject2 + ".xml";
    this.log.a(TAG, "Dbmapping for this device: %s", new Object[] { localObject2 });
    try
    {
      localObject2 = new InputStreamReader(this.context.getAssets().open((String)localObject2), "UTF-8");
      return localObject2;
    }
    catch (IOException localIOException2)
    {
      localObject1 = (String)localObject1 + "_default_dbmapping.xml";
      try
      {
        localObject1 = new InputStreamReader(this.context.getAssets().open((String)localObject1), "UTF-8");
        return localObject1;
      }
      catch (IOException localIOException1)
      {
        throw new IOException("DbMapping file not found");
      }
    }
  }
}
