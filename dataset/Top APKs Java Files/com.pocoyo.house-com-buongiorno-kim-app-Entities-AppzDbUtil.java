package com.buongiorno.kim.app.Entities;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import com.buongiorno.kim.app.Db.DBAdapter;
import com.buongiorno.kim.app.apptracking.TrackerEvents;
import com.buongiorno.kim.app.util.JsonProp;
import com.buongiorno.kim.app.util.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppzDbUtil
{
  private static String CLASSNAME = "AppzDbUtil";
  private static ArrayList<Appz> appz_list = null;
  
  public AppzDbUtil() {}
  
  public static boolean check_embeddedappz_db(Context paramContext)
  {
    paramContext = Utils.getEmbeddAppzs(paramContext);
    int i = 0;
    int j = 0;
    Cursor localCursor = DBAdapter.getAppz();
    if (localCursor != null)
    {
      Iterator localIterator = paramContext.iterator();
      i = j;
      if (localIterator.hasNext())
      {
        Appz localAppz1 = (Appz)localIterator.next();
        localCursor.moveToFirst();
        for (j = i;; j = i)
        {
          i = j;
          if (localCursor.isAfterLast()) {
            break;
          }
          Appz localAppz2 = new Appz(localCursor);
          JsonProp.logv(CLASSNAME, "check_embeddedappz_db(): #1=[" + localAppz2.getPackagename() + "], #2=[" + localAppz1.getPackagename() + "]");
          i = j;
          if (localAppz2.getPackagename().equals(localAppz1.getPackagename())) {
            i = j + 1;
          }
          localCursor.moveToNext();
        }
      }
      localCursor.close();
    }
    if (i >= paramContext.size()) {}
    for (boolean bool = true;; bool = false)
    {
      JsonProp.logi(CLASSNAME, "check_embeddedappz_db(): ret=" + bool + ", counter=" + i);
      return bool;
    }
  }
  
  public static List<Appz> engage_all()
  {
    appz_list = new ArrayList();
    Cursor localCursor = DBAdapter.getAppz();
    if (localCursor != null)
    {
      localCursor.moveToFirst();
      while (!localCursor.isAfterLast())
      {
        Appz localAppz = new Appz(localCursor);
        localAppz.setEngaged(true);
        if (localAppz.hasImageUrl()) {
          appz_list.add(localAppz);
        }
        localCursor.moveToNext();
      }
      localCursor.close();
    }
    return appz_list;
  }
  
  public static List<Appz> getAppzList()
  {
    return appz_list;
  }
  
  public static boolean has_appz_not_engaged()
  {
    boolean bool2 = false;
    Cursor localCursor = DBAdapter.getAppz();
    ArrayList localArrayList = new ArrayList();
    if (localCursor != null)
    {
      localCursor.moveToFirst();
      if (!localCursor.isAfterLast())
      {
        Appz localAppz = new Appz(localCursor);
        bool2 = localAppz.hasImageUrl();
        if (!localAppz.isEngaged()) {}
        for (boolean bool1 = true;; bool1 = false)
        {
          if ((bool1 & bool2)) {
            localArrayList.add(localAppz);
          }
          localCursor.moveToNext();
          break;
        }
      }
      localCursor.close();
      if (localArrayList.size() > 0) {
        bool2 = true;
      }
    }
    else
    {
      return bool2;
    }
    return false;
  }
  
  public static List<Appz> load_appz_from_db(boolean paramBoolean)
  {
    if ((appz_list != null) && (!paramBoolean)) {
      return appz_list;
    }
    appz_list = new ArrayList();
    Cursor localCursor = DBAdapter.getAppzEngaged();
    if (localCursor != null)
    {
      JsonProp.logv(CLASSNAME, "load_appz_from_db: data size: " + localCursor.getCount());
      localCursor.moveToFirst();
      if (!localCursor.isAfterLast())
      {
        Appz localAppz = new Appz(localCursor);
        if (localAppz.isSelfieWow()) {
          if (Utils.hasCamera()) {
            appz_list.add(localAppz);
          }
        }
        for (;;)
        {
          localCursor.moveToNext();
          break;
          if (localAppz.hasImageUrl()) {
            appz_list.add(localAppz);
          } else {
            JsonProp.logw(CLASSNAME, "load_appz_from_db: not added to the list because has not imageurl");
          }
        }
      }
      localCursor.close();
    }
    return appz_list;
  }
  
  public static List<String> querySystemInstalledAppzList(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = paramContext.getPackageManager();
    localArrayList.add("EmbeddedApp.Camera");
    localArrayList.add("EmbeddedApp.Paint");
    localArrayList.add("EmbeddedApp.Stickers");
    localArrayList.add("EmbeddedApp.Abc");
    localObject = ((PackageManager)localObject).getInstalledApplications(128).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if (localApplicationInfo.packageName.startsWith(paramContext.getResources().getString(2131165600))) {
        localArrayList.add(localApplicationInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public static void remove_from_db_if_no_more_installed(Context paramContext)
  {
    appz_list = new ArrayList();
    Cursor localCursor = DBAdapter.getAppzEngaged();
    if (localCursor != null)
    {
      localCursor.moveToFirst();
      if (!localCursor.isAfterLast())
      {
        Appz localAppz = new Appz(localCursor);
        if (!localAppz.isEmbedded())
        {
          if (!localAppz.isInstalled(paramContext)) {
            break label83;
          }
          if (localAppz.hasImageUrl()) {
            appz_list.add(localAppz);
          }
        }
        for (;;)
        {
          localCursor.moveToNext();
          break;
          label83:
          localAppz.removeFromDb();
          List localList = querySystemInstalledAppzList(paramContext);
          localList.remove(localAppz.getPackagename());
          JsonProp.logi(CLASSNAME, "remove_from_db_if_no_more_installed: " + localAppz.getPackagename());
          TrackerEvents.trackAppDeleted(paramContext.getApplicationContext(), localAppz.getCategoryId(), localAppz.getPackagename(), localAppz.getTitle(), localList);
        }
      }
      localCursor.close();
    }
  }
  
  public static void reset_playtime() {}
}
