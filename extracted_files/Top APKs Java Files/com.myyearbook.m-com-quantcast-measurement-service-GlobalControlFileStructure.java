package com.quantcast.measurement.service;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.quantcast.settings.GlobalControl;
import com.quantcast.settings.GlobalControlAmbassador;
import com.quantcast.settings.GlobalControlDAO;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class GlobalControlFileStructure
  implements GlobalControlAmbassador, GlobalControlDAO
{
  private static final String BASE_DIR;
  private static final String OPT_OUT_FILE_NAME = GlobalControl.class.getName() + ".blockEventCollection";
  private static final String PRESENCE_ANNOUNCEMENT_FILE_NAME;
  private static final QuantcastLog.Tag TAG = new QuantcastLog.Tag(GlobalControlFileStructure.class);
  private final Context context;
  
  static
  {
    BASE_DIR = GlobalControlFileStructure.class.getName();
    PRESENCE_ANNOUNCEMENT_FILE_NAME = GlobalControlFileStructure.class.getName() + ".present";
  }
  
  public GlobalControlFileStructure(Context paramContext)
  {
    this.context = paramContext.getApplicationContext();
  }
  
  private static boolean fileExists(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = getBaseDir(paramContext);
    if (testValidDirExists(paramContext)) {
      bool = new File(paramContext, paramString).exists();
    }
    return bool;
  }
  
  private static File getBaseDir(Context paramContext)
  {
    return paramContext.getDir(BASE_DIR, 3);
  }
  
  private static boolean presenceAnnounced(Context paramContext)
  {
    return fileExists(paramContext, PRESENCE_ANNOUNCEMENT_FILE_NAME);
  }
  
  private static boolean testDirIsValid(File paramFile)
  {
    boolean bool = true;
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.isDirectory()) || (!paramFile.canRead()) || (!paramFile.canWrite()))
    {
      bool = false;
      if (paramFile != null) {
        QuantcastLog.e(TAG, "The directory (" + paramFile.getAbsolutePath() + ") cannot be accessed appropriately.");
      }
    }
    else
    {
      return bool;
    }
    QuantcastLog.e(TAG, "A null directory has could not be tested.");
    return false;
  }
  
  private static boolean testValidDirExists(File paramFile)
  {
    if ((paramFile == null) || (!paramFile.exists())) {
      return false;
    }
    return testDirIsValid(paramFile);
  }
  
  public void announcePresence()
  {
    File localFile = getBaseDir(this.context);
    if (testDirIsValid(localFile)) {
      localFile = new File(localFile, PRESENCE_ANNOUNCEMENT_FILE_NAME);
    }
    try
    {
      localFile.createNewFile();
      return;
    }
    catch (IOException localIOException)
    {
      QuantcastLog.e(TAG, "Unable to create presence file (" + localFile.getAbsolutePath() + ".");
    }
  }
  
  public GlobalControl get(Context paramContext)
  {
    return new GlobalControl(fileExists(paramContext, OPT_OUT_FILE_NAME));
  }
  
  public Context getForeignContext()
  {
    Iterator localIterator = this.context.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PackageInfo)localIterator.next();
      if (!((PackageInfo)localObject).packageName.equals(this.context.getPackageName())) {
        try
        {
          localObject = this.context.createPackageContext(((PackageInfo)localObject).packageName, 0);
          boolean bool = presenceAnnounced((Context)localObject);
          if (bool) {
            return localObject;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          QuantcastLog.w(TAG, "Unable to create context from package name.", localNameNotFoundException);
        }
      }
    }
    return null;
  }
  
  public Queue<Context> getForeignContexts()
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = this.context.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PackageInfo)localIterator.next();
      if (!((PackageInfo)localObject).packageName.equals(this.context.getPackageName())) {
        try
        {
          localObject = this.context.createPackageContext(((PackageInfo)localObject).packageName, 0);
          if (presenceAnnounced((Context)localObject)) {
            localLinkedList.add(localObject);
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          QuantcastLog.w(TAG, "Unable to create context from package name.", localNameNotFoundException);
        }
      }
    }
    return localLinkedList;
  }
  
  public GlobalControl getLocal()
  {
    return get(this.context);
  }
  
  public boolean presenceAnnounced()
  {
    return presenceAnnounced(this.context);
  }
  
  public void save(Context paramContext, GlobalControl paramGlobalControl)
  {
    paramContext = getBaseDir(paramContext);
    if (testDirIsValid(paramContext))
    {
      paramContext = new File(paramContext, OPT_OUT_FILE_NAME);
      if (!paramGlobalControl.blockingEventCollection) {
        paramContext.delete();
      }
    }
    else
    {
      return;
    }
    try
    {
      paramContext.createNewFile();
      return;
    }
    catch (IOException paramGlobalControl)
    {
      QuantcastLog.e(TAG, "Unable to create opt-out file (" + paramContext.getAbsolutePath() + ".");
    }
  }
  
  public void saveLocal(GlobalControl paramGlobalControl)
  {
    save(this.context, paramGlobalControl);
  }
}
