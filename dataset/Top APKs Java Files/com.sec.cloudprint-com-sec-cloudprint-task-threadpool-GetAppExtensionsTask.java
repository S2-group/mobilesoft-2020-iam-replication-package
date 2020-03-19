package com.sec.cloudprint.task.threadpool;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.sec.cloudprint.application.SharedAppClass;
import com.sec.cloudprint.thread.ThreadPool.Task;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class GetAppExtensionsTask
  extends ThreadPool.Task
{
  public static final String METADATA = "com.sec.print.mobileprint.plugin.metadata";
  private static final String METADATA_KEY_ACTIVITY = "activity";
  private static final String METADATA_KEY_APP = "app";
  private static final String METADATA_KEY_COUNTRY = "country";
  private static final String METADATA_KEY_ERROR = "error";
  private static final String METADATA_KEY_ICON = "icon";
  private static final String METADATA_KEY_NAME = "name";
  private static final String METADATA_KEY_SUPPORT = "support";
  private final ArrayList<String> mApplication = new ArrayList();
  private final ArrayList<String> mLocale = new ArrayList();
  
  public GetAppExtensionsTask(String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    int k;
    int i;
    if ((paramArrayOfString1 != null) && (paramArrayOfString1.length > 0))
    {
      k = paramArrayOfString1.length;
      i = 0;
      if (i < k) {}
    }
    else if ((paramArrayOfString2 != null) && (paramArrayOfString2.length > 0))
    {
      k = paramArrayOfString2.length;
      i = j;
    }
    for (;;)
    {
      if (i >= k)
      {
        return;
        String str = paramArrayOfString1[i];
        this.mLocale.add(str.toLowerCase());
        i += 1;
        break;
      }
      paramArrayOfString1 = paramArrayOfString2[i];
      this.mApplication.add(paramArrayOfString1.toLowerCase());
      i += 1;
    }
  }
  
  private static boolean checkApplication(ArrayList<String> paramArrayList, String paramString)
  {
    paramArrayList = paramArrayList.iterator();
    do
    {
      if (!paramArrayList.hasNext()) {
        return false;
      }
    } while (!paramString.contains((String)paramArrayList.next()));
    return true;
  }
  
  private static boolean checkLocale(ArrayList<String> paramArrayList, String paramString)
  {
    paramArrayList = paramArrayList.iterator();
    do
    {
      if (!paramArrayList.hasNext()) {
        return false;
      }
    } while (!paramString.contains((String)paramArrayList.next()));
    return true;
  }
  
  private static ArrayList<Extension> parseApplication(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
    throws Exception
  {
    XmlResourceParser localXmlResourceParser = paramApplicationInfo.loadXmlMetaData(paramPackageManager, "com.sec.print.mobileprint.plugin.metadata");
    Object localObject1;
    if (localXmlResourceParser == null)
    {
      localObject1 = null;
      return localObject1;
    }
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      Resources localResources;
      Object localObject2;
      Object localObject3;
      int i;
      Object localObject4;
      try
      {
        localResources = paramPackageManager.getResourcesForApplication(paramApplicationInfo.packageName);
        localObject2 = null;
        localObject3 = null;
        localXmlResourceParser.next();
        i = localXmlResourceParser.getEventType();
        localObject1 = localArrayList;
        if (i == 1) {
          break;
        }
        localObject1 = localObject3;
        localObject4 = localObject2;
        switch (i)
        {
        default: 
          localObject4 = localObject2;
          localObject1 = localObject3;
        case 0: 
        case 1: 
          i = localXmlResourceParser.next();
          localObject3 = localObject1;
          localObject2 = localObject4;
          break;
        case 2: 
          localObject1 = localXmlResourceParser.getName();
        }
      }
      catch (PackageManager.NameNotFoundException paramPackageManager)
      {
        Log.e("SCP", String.format("[%s] Exception message : %s", new Object[] { GetAppExtensionsTask.class.getSimpleName(), paramPackageManager.getMessage() }));
        return null;
      }
      if (((String)localObject1).equals("app"))
      {
        localObject4 = new Extension();
        ((Extension)localObject4).mPackage = paramApplicationInfo.packageName;
        localObject1 = localObject3;
      }
      else if (((String)localObject1).equals("name"))
      {
        localObject1 = "name";
        localObject4 = localObject2;
      }
      else if (((String)localObject1).equals("icon"))
      {
        localObject1 = "icon";
        localObject4 = localObject2;
      }
      else if (((String)localObject1).equals("activity"))
      {
        localObject1 = "activity";
        localObject4 = localObject2;
      }
      else if (((String)localObject1).equals("country"))
      {
        localObject1 = "country";
        localObject4 = localObject2;
      }
      else if (((String)localObject1).equals("support"))
      {
        localObject1 = "support";
        localObject4 = localObject2;
      }
      else
      {
        localObject1 = "error";
        localObject4 = localObject2;
        continue;
        localObject1 = localObject3;
        localObject4 = localObject2;
        if (localXmlResourceParser.getName().equals("app"))
        {
          localArrayList.add(localObject2);
          localObject1 = localObject3;
          localObject4 = localObject2;
          continue;
          if (localObject3.equals("name"))
          {
            localObject2.mName = localXmlResourceParser.getText();
            localObject1 = localObject3;
            localObject4 = localObject2;
          }
          else if (localObject3.equals("icon"))
          {
            i = localResources.getIdentifier(localXmlResourceParser.getText(), "drawable", paramApplicationInfo.packageName);
            if (i != 0)
            {
              localObject1 = localResources.getDrawable(i);
              if (localObject1 != null)
              {
                localObject2.mIcon = ((Drawable)localObject1);
                localObject1 = localObject3;
                localObject4 = localObject2;
              }
              else
              {
                localObject2.mIcon = paramApplicationInfo.loadIcon(paramPackageManager);
                localObject1 = localObject3;
                localObject4 = localObject2;
              }
            }
            else
            {
              localObject2.mIcon = paramApplicationInfo.loadIcon(paramPackageManager);
              localObject1 = localObject3;
              localObject4 = localObject2;
            }
          }
          else if (localObject3.equals("activity"))
          {
            localObject2.mActivity = localXmlResourceParser.getText();
            localObject1 = localObject3;
            localObject4 = localObject2;
          }
          else if (localObject3.equals("country"))
          {
            localObject2.mCountry = localXmlResourceParser.getText();
            localObject1 = localObject3;
            localObject4 = localObject2;
          }
          else
          {
            localObject1 = localObject3;
            localObject4 = localObject2;
            if (localObject3.equals("support"))
            {
              localObject2.mSupport = localXmlResourceParser.getText();
              localObject1 = localObject3;
              localObject4 = localObject2;
            }
          }
        }
      }
    }
  }
  
  protected Object doTask()
  {
    Result localResult = new Result();
    PackageManager localPackageManager = SharedAppClass.getInstance().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {}
      Object localObject;
      do
      {
        return localResult;
        localObject = (ApplicationInfo)localIterator.next();
      } while ((isCancelled()) || (isTerminated()));
      try
      {
        localObject = parseApplication(localPackageManager, (ApplicationInfo)localObject);
        if (localObject == null) {
          continue;
        }
        localObject = ((ArrayList)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          Extension localExtension = (Extension)((Iterator)localObject).next();
          if ((checkLocale(this.mLocale, localExtension.mCountry.toLowerCase())) && (checkApplication(this.mApplication, localExtension.mSupport.toLowerCase()))) {
            localResult.mExtensions.add(localExtension);
          }
        }
      }
      catch (Exception localException)
      {
        Log.e("SCP", String.format("[%s] Exception message : %s", new Object[] { GetAppExtensionsTask.class.getSimpleName(), localException.getMessage() }));
      }
    }
  }
  
  public Integer getId()
  {
    return Integer.valueOf(12);
  }
  
  public String getName()
  {
    return "GET_APP_EXTENSIONS";
  }
  
  public static abstract interface Application
  {
    public static final String MPA = "MPA";
    public static final String SCP = "SCP";
  }
  
  public static final class Extension
  {
    public String mActivity = null;
    public String mCountry = null;
    public Drawable mIcon = null;
    public String mName = null;
    public String mPackage = null;
    public String mSupport = null;
    
    public Extension() {}
  }
  
  public static abstract interface Locale
  {
    public static final String ALL = "ALL";
  }
  
  public static final class Result
  {
    public final List<GetAppExtensionsTask.Extension> mExtensions = new ArrayList();
    
    public Result() {}
  }
}
