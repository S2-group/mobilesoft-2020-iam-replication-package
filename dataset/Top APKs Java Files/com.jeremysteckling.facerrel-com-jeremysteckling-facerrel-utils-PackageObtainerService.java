package com.jeremysteckling.facerrel.utils;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class PackageObtainerService
  extends IntentService
{
  private static final String[] a = { "com.superevilmegacorp.game", "com.ea.games.simsfreeplay_na", "com.ea.game.pvzfree_row", "com.ea.game.maddenmobile15_row", "com.zynga.livepoker", "air.com.playtika.slotomania", "com.playtika.wsop.gp", "air.com.buffalo_studios.newflashbingo", "com.ea.game.tetris2011_na", "com.ea.tetrisblitz_na" };
  
  public PackageObtainerService()
  {
    super("PackageObtainerService");
  }
  
  private void a(List<String> paramList)
  {
    SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
    localEditor.remove("propTrackedApps");
    localEditor.commit();
    if ((paramList != null) && (paramList.size() > 0))
    {
      localEditor.putStringSet("propTrackedApps", new HashSet(paramList));
      localEditor.apply();
    }
  }
  
  private boolean a(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = a;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramString.equals(arrayOfString[i])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    boolean bool2 = false;
    Object localObject2 = getApplicationContext().getPackageManager().getInstalledApplications(128);
    paramIntent = "";
    Object localObject1 = "";
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = ((List)localObject2).iterator();
    boolean bool1 = false;
    if (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      Object localObject3;
      boolean bool4;
      boolean bool3;
      if (localApplicationInfo.packageName.contains("com.jeremysteckling.facerrel"))
      {
        localObject3 = paramIntent + localApplicationInfo.packageName + ", ";
        bool4 = bool1;
        bool3 = bool2;
        localObject2 = localObject1;
      }
      for (;;)
      {
        localObject1 = localObject2;
        paramIntent = (Intent)localObject3;
        bool2 = bool3;
        bool1 = bool4;
        if (!a(localApplicationInfo.packageName)) {
          break;
        }
        localArrayList.add(localApplicationInfo.packageName);
        localObject1 = localObject2;
        paramIntent = (Intent)localObject3;
        bool2 = bool3;
        bool1 = bool4;
        break;
        if (localApplicationInfo.packageName.contains("com.pujie.wristwear"))
        {
          bool4 = true;
          localObject2 = localObject1;
          localObject3 = paramIntent;
          bool3 = bool2;
        }
        else if (localApplicationInfo.packageName.contains("dd.watchmaster"))
        {
          bool3 = true;
          localObject2 = localObject1;
          localObject3 = paramIntent;
          bool4 = bool1;
        }
        else
        {
          localObject2 = localObject1;
          localObject3 = paramIntent;
          bool3 = bool2;
          bool4 = bool1;
          if (localApplicationInfo.packageName.contains("dd.watchdesigner"))
          {
            localObject2 = (String)localObject1 + localApplicationInfo.packageName + ", ";
            localObject3 = paramIntent;
            bool3 = bool2;
            bool4 = bool1;
          }
        }
      }
    }
    a.a(getApplicationContext()).b(paramIntent);
    a.a(getApplicationContext()).a(bool1);
    a.a(getApplicationContext()).b(bool2);
    a.a(getApplicationContext()).c((String)localObject1);
    a(localArrayList);
    if (localArrayList.size() > 0) {
      a.a(getApplicationContext()).a(new JSONArray(localArrayList));
    }
  }
}
