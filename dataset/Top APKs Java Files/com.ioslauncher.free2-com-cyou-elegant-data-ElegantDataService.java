package com.cyou.elegant.data;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.TextUtils;
import com.cyou.elegant.model.ThemeInfoModel;
import com.cyou.elegant.ʽ;
import com.cyou.elegant.ˉ;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ElegantDataService
  extends IntentService
{
  private Handler ʻ = new Handler();
  
  public ElegantDataService()
  {
    super("ElegantDataService");
  }
  
  private void ʻ()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = ʽ.ʻ(this, true);
    ThemeInfoModel localThemeInfoModel;
    if (localObject1 != null)
    {
      Iterator localIterator = ((ArrayList)localObject1).iterator();
      if (localIterator.hasNext())
      {
        localThemeInfoModel = (ThemeInfoModel)localIterator.next();
        if (!TextUtils.isEmpty(localThemeInfoModel.ˊ)) {
          break label205;
        }
      }
    }
    label205:
    for (localObject1 = new File(localThemeInfoModel.ˊ);; localObject1 = null)
    {
      Object localObject2;
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        if (((File)localObject1).exists()) {}
      }
      else
      {
        localObject2 = ʽ.ʻ(this, localThemeInfoModel.ᴵ, localThemeInfoModel.ˊ);
      }
      if ((localObject2 == null) || (!((File)localObject2).exists()) || (localArrayList.contains(localThemeInfoModel))) {
        break;
      }
      localArrayList.add(localThemeInfoModel);
      break;
      localObject1 = ʼ.ʻ(this, null, null);
      if ((localObject1 != null) && (!((ArrayList)localObject1).isEmpty()))
      {
        localObject1 = ((ArrayList)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (ThemeInfoModel)((Iterator)localObject1).next();
          if (localArrayList.contains(localObject2)) {
            localArrayList.remove(localObject2);
          }
        }
      }
      Collections.sort(localArrayList, new ʿ(this));
      ʻ(localArrayList);
      return;
    }
  }
  
  private void ʻ(ArrayList<ThemeInfoModel> paramArrayList)
  {
    String str = com.cyou.elegant.util.ʿ.ʾ(this);
    ArrayList localArrayList = new ArrayList();
    paramArrayList = paramArrayList.iterator();
    if (paramArrayList.hasNext())
    {
      ThemeInfoModel localThemeInfoModel = (ThemeInfoModel)paramArrayList.next();
      ContentValues localContentValues = ˈ.ʻ(localThemeInfoModel, false);
      if ((str != null) && (str.contains(localThemeInfoModel.ᴵ))) {
        localContentValues.put("status", Integer.valueOf(5));
      }
      for (;;)
      {
        localArrayList.add(localContentValues);
        break;
        if (localThemeInfoModel.ⁱ == 5) {
          localContentValues.put("status", Integer.valueOf(2));
        }
      }
    }
    ʼ.ʻ(this, ˉ.ʻ().ʻ(this, false, 291), (ContentValues[])localArrayList.toArray(new ContentValues[0]));
  }
  
  private void ʼ()
  {
    com.cyou.elegant.theme.ʻ.ʼ localʼ = new com.cyou.elegant.theme.ʻ.ʼ(this);
    try
    {
      List localList = getPackageManager().getInstalledApplications(128);
      str = ʽ.ʻ(this, ".ThemeResources");
      if (str == null)
      {
        this.ʻ.post(new ʾ(this));
        return;
      }
    }
    catch (Exception localException1)
    {
      String str;
      Object localObject1;
      for (;;)
      {
        localObject1 = new ArrayList();
      }
      ArrayList localArrayList1 = ʼ.ʻ(this, null, null);
      ArrayList localArrayList2 = new ArrayList();
      Iterator localIterator = ((List)localObject1).iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          localObject1 = ((ApplicationInfo)localIterator.next()).packageName;
          if ((!((String)localObject1).startsWith("com.cyou.cma.clauncher.theme.v")) && (!((String)localObject1).startsWith("com.theme.launcher.android."))) {
            continue;
          }
          try
          {
            localObject1 = localʼ.ʻ(localArrayList1, (String)localObject1, str);
            if ((localObject1 != null) && (!localArrayList2.contains(localObject1))) {
              localArrayList2.add(localObject1);
            }
          }
          catch (Exception localException2)
          {
            for (;;)
            {
              Object localObject2 = null;
            }
          }
        }
      }
      ʻ(localArrayList2);
    }
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    int i = 0;
    for (;;)
    {
      try
      {
        if (!paramIntent.getBooleanExtra("force", false))
        {
          long l = getSharedPreferences("dataTrans", 0).getLong("time_data_trans", 0L);
          if (l != 0L) {
            if (l + 15000L >= System.currentTimeMillis()) {
              break label124;
            }
          }
        }
        else
        {
          paramIntent = new File(ʽ.ʻ(this, ".ThemeResources"), "theme.bp");
          ʻ();
          ʼ();
          ʽ.ʻ(ʼ.ʻ(this, null, null), paramIntent);
          paramIntent = getSharedPreferences("dataTrans", 0).edit();
          paramIntent.putLong("time_data_trans", System.currentTimeMillis());
          paramIntent.commit();
          return;
        }
      }
      catch (Exception paramIntent)
      {
        paramIntent.printStackTrace();
        return;
      }
      i = 1;
      label124:
      if (i == 0) {}
    }
  }
}
