package com.cyou.elegant.data;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import com.cyou.elegant.model.ThemeInfoModel;
import com.cyou.elegant.model.ThemeInfoModel.Preview;
import com.cyou.elegant.ʽ;
import com.cyou.elegant.ˉ;
import com.ˆ.ʻ.ʽ.ʻ;
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
  
  private ArrayList<ThemeInfoModel.Preview> ʻ(File paramFile, ThemeInfoModel paramThemeInfoModel)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramThemeInfoModel == null) || (TextUtils.isEmpty(paramThemeInfoModel.ﹳ))) {
      return localArrayList;
    }
    Iterator localIterator = paramThemeInfoModel.ˏ.iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (ThemeInfoModel.Preview)localIterator.next();
      Object localObject1 = new File(paramThemeInfoModel.ﹳ + File.separator + "CYOUThemePreviews");
      if (!((File)localObject1).exists()) {
        return localArrayList;
      }
      localObject2 = ʻ.ʻ(((ThemeInfoModel.Preview)localObject2).ʻ);
      localObject1 = ((File)localObject1).listFiles(new ˆ(this, (String)localObject2));
      if ((localObject1 == null) || (localObject1.length == 0)) {
        return localArrayList;
      }
      if (!paramFile.exists()) {
        paramFile.mkdirs();
      }
      localObject2 = new File(paramFile, (String)localObject2 + ".jpg");
      if (!((File)localObject2).exists())
      {
        localObject1[0].renameTo((File)localObject2);
        localArrayList.add(new ThemeInfoModel.Preview(((File)localObject2).getAbsolutePath()));
      }
    }
    return localArrayList;
  }
  
  private void ʻ()
  {
    com.cyou.elegant.theme.ʻ.ʼ localʼ = new com.cyou.elegant.theme.ʻ.ʼ(this);
    String str;
    Object localObject1;
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    Iterator localIterator;
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
      for (;;)
      {
        localObject1 = new ArrayList();
      }
      localArrayList1 = ʼ.ʻ(this, null, null);
      localArrayList2 = new ArrayList();
      localIterator = ((List)localObject1).iterator();
    }
    label210:
    label213:
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject3 = (ApplicationInfo)localIterator.next();
        localObject1 = ((ApplicationInfo)localObject3).packageName;
        localObject3 = ((ApplicationInfo)localObject3).metaData;
        if ((localObject3 == null) || (TextUtils.isEmpty(((Bundle)localObject3).getString("com.cyou.cma.clauncher.theme.id")))) {
          break label210;
        }
      }
      for (int i = 1;; i = 0)
      {
        if ((!((String)localObject1).startsWith("com.cyou.cma.clauncher.theme.v")) && (!((String)localObject1).startsWith("com.theme.launcher.android.")) && (i == 0)) {
          break label213;
        }
        try
        {
          localObject1 = localʼ.ʻ(localArrayList1, (String)localObject1, str);
          if ((localObject1 == null) || (localArrayList2.contains(localObject1))) {
            break;
          }
          localArrayList2.add(localObject1);
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            Object localObject2 = null;
          }
        }
        ʼ(localArrayList2);
        return;
      }
    }
  }
  
  private void ʻ(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    ʼ(ʽ.ʻ(ʽ.ˈ(this)));
    ʼ(ʽ.ʻ(Environment.getExternalStorageDirectory().getAbsolutePath()));
    ʻ(localArrayList);
    ʻ(localArrayList, paramString);
    paramString = ʽ.ʻ(this, true);
    ThemeInfoModel localThemeInfoModel;
    if (paramString != null)
    {
      Iterator localIterator = paramString.iterator();
      if (localIterator.hasNext())
      {
        localThemeInfoModel = (ThemeInfoModel)localIterator.next();
        if (!TextUtils.isEmpty(localThemeInfoModel.ˊ)) {
          break label238;
        }
      }
    }
    label238:
    for (paramString = new File(localThemeInfoModel.ˊ);; paramString = null)
    {
      Object localObject;
      if (paramString != null)
      {
        localObject = paramString;
        if (paramString.exists()) {}
      }
      else
      {
        localObject = ʽ.ʻ(this, localThemeInfoModel.ᴵ, localThemeInfoModel.ˊ);
      }
      if ((localObject == null) || (!((File)localObject).exists()) || (localArrayList.contains(localThemeInfoModel))) {
        break;
      }
      localArrayList.add(localThemeInfoModel);
      break;
      paramString = ʼ.ʻ(this, null, null);
      if ((paramString != null) && (!paramString.isEmpty()))
      {
        paramString = paramString.iterator();
        while (paramString.hasNext())
        {
          localObject = (ThemeInfoModel)paramString.next();
          if (localArrayList.contains(localObject)) {
            localArrayList.remove(localObject);
          }
        }
      }
      Collections.sort(localArrayList, new ˈ(this));
      ʼ(localArrayList);
      return;
    }
  }
  
  private void ʻ(ArrayList<ThemeInfoModel> paramArrayList)
  {
    Object localObject2 = File.separator + "clauncher.cyou.inc" + File.separator + "elegant" + File.separator + "elegant.db";
    Object localObject1 = new ArrayList();
    Object localObject3 = Environment.getExternalStorageDirectory().getAbsolutePath() + (String)localObject2;
    if (new File((String)localObject3).exists()) {
      ((ArrayList)localObject1).add(localObject3);
    }
    localObject2 = ʽ.ˈ(this) + (String)localObject2;
    if (new File((String)localObject2).exists()) {
      ((ArrayList)localObject1).add(localObject2);
    }
    if (((ArrayList)localObject1).isEmpty()) {}
    for (;;)
    {
      return;
      localObject1 = ((ArrayList)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (String)((Iterator)localObject1).next();
        ʼ.ʻ(paramArrayList, (String)localObject2);
        localObject2 = new File((String)localObject2);
        localObject3 = new File(((File)localObject2).getParentFile(), "elegant.db-journal");
        ((File)localObject2).delete();
        ((File)localObject3).delete();
      }
    }
  }
  
  private void ʻ(ArrayList<ThemeInfoModel> paramArrayList, String paramString)
  {
    Object localObject2 = new ArrayList();
    Object localObject1 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "cyou_nad_picwall.db";
    if (new File((String)localObject1).exists()) {
      ((ArrayList)localObject2).add(localObject1);
    }
    localObject1 = ʽ.ˈ(this) + File.separator + "cyou_nad_picwall.db";
    if (new File((String)localObject1).exists()) {
      ((ArrayList)localObject2).add(localObject1);
    }
    if (((ArrayList)localObject2).isEmpty()) {}
    do
    {
      return;
      localObject1 = new ArrayList();
      localObject2 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Object localObject3 = (String)((Iterator)localObject2).next();
        ʼ.ʼ((ArrayList)localObject1, (String)localObject3);
        ʼ.ʾ(this, (String)localObject3);
        localObject3 = new File((String)localObject3);
        File localFile = new File(((File)localObject3).getParentFile(), "cyou_nad_picwall.db-journal");
        ((File)localObject3).delete();
        localFile.delete();
      }
    } while (((ArrayList)localObject1).isEmpty());
    ʻ(paramArrayList, (ArrayList)localObject1, paramString);
  }
  
  private void ʻ(ArrayList<ThemeInfoModel> paramArrayList1, ArrayList<ThemeInfoModel> paramArrayList2, String paramString)
  {
    Iterator localIterator = paramArrayList2.iterator();
    while (localIterator.hasNext())
    {
      ThemeInfoModel localThemeInfoModel = (ThemeInfoModel)localIterator.next();
      if (paramArrayList1.contains(localThemeInfoModel))
      {
        localIterator.remove();
      }
      else
      {
        Object localObject1 = new File(paramString + File.separator + localThemeInfoModel.ᴵ);
        if (!((File)localObject1).exists()) {
          ((File)localObject1).mkdirs();
        }
        File localFile;
        if ((localThemeInfoModel == null) || (TextUtils.isEmpty(localThemeInfoModel.ﹳ))) {
          localFile = null;
        }
        for (;;)
        {
          if (localFile != null) {
            break label290;
          }
          localIterator.remove();
          break;
          paramArrayList2 = new File(localThemeInfoModel.ﹳ + File.separator + "CYOUThemeResources" + File.separator + localThemeInfoModel.ᴵ + File.separator + ʻ.ʻ(localThemeInfoModel.ˊ) + ".act");
          if (paramArrayList2.exists())
          {
            if (!((File)localObject1).exists()) {
              ((File)localObject1).mkdirs();
            }
            localFile = new File((File)localObject1, ʻ.ʻ(localThemeInfoModel.ˊ) + ".amr");
            if (!localFile.exists())
            {
              paramArrayList2.renameTo(localFile);
              continue;
            }
          }
          localFile = null;
        }
        label290:
        localThemeInfoModel.ﾞﾞ = localFile.lastModified();
        if ((localThemeInfoModel == null) || (TextUtils.isEmpty(localThemeInfoModel.ﹳ))) {
          paramArrayList2 = null;
        }
        for (;;)
        {
          localObject1 = ʻ((File)localObject1, localThemeInfoModel);
          if (!TextUtils.equals(localThemeInfoModel.ⁱ, "apkDownload")) {
            break label569;
          }
          if (paramArrayList2 != null) {
            break label511;
          }
          localIterator.remove();
          break;
          Object localObject2 = new File(localThemeInfoModel.ﹳ + File.separator + "CYOUPicResources");
          if (((File)localObject2).exists())
          {
            paramArrayList2 = ʻ.ʻ(localThemeInfoModel.ʽ);
            localObject2 = ((File)localObject2).listFiles(new ʿ(this, paramArrayList2));
            if ((localObject2 == null) || (localObject2.length == 0))
            {
              paramArrayList2 = null;
              continue;
            }
            if (!((File)localObject1).exists()) {
              ((File)localObject1).mkdirs();
            }
            paramArrayList2 = new File((File)localObject1, paramArrayList2 + ".jpg");
            if (!paramArrayList2.exists())
            {
              localObject2[0].renameTo(paramArrayList2);
              continue;
            }
          }
          paramArrayList2 = null;
        }
        label511:
        localThemeInfoModel.ˊ = localFile.getAbsolutePath();
        localThemeInfoModel.ʽ = paramArrayList2.getAbsolutePath();
        if (((ArrayList)localObject1).size() > 0) {
          localThemeInfoModel.ˏ = ((List)localObject1);
        }
        localThemeInfoModel.ﹶ = 2;
        localThemeInfoModel.ⁱ = "DIY";
        for (;;)
        {
          paramArrayList1.add(localThemeInfoModel);
          break;
          label569:
          if (TextUtils.equals(localThemeInfoModel.ⁱ, "actDownload"))
          {
            localThemeInfoModel.ﹶ = 2;
            localThemeInfoModel.ⁱ = "AMR";
          }
        }
      }
    }
  }
  
  private static void ʼ(String paramString)
  {
    File localFile1 = new File(paramString, "ThemeResources");
    if ((!localFile1.exists()) || (localFile1.isFile())) {}
    File[] arrayOfFile;
    do
    {
      return;
      paramString = new File(paramString, ".ThemeResources");
      if (paramString.isFile()) {
        paramString.delete();
      }
      if (!paramString.exists()) {
        paramString.mkdirs();
      }
      arrayOfFile = localFile1.listFiles();
    } while ((arrayOfFile == null) || (arrayOfFile.length == 0));
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      File localFile2 = arrayOfFile[i];
      localFile2.renameTo(new File(paramString, localFile2.getName()));
      i += 1;
    }
    com.cyou.elegant.util.ʼ.ʼ(localFile1);
  }
  
  private void ʼ(ArrayList<ThemeInfoModel> paramArrayList)
  {
    String str = com.cyou.elegant.util.ˆ.ˆ(this);
    ArrayList localArrayList = new ArrayList();
    paramArrayList = paramArrayList.iterator();
    if (paramArrayList.hasNext())
    {
      ThemeInfoModel localThemeInfoModel = (ThemeInfoModel)paramArrayList.next();
      ContentValues localContentValues = ˊ.ʻ(localThemeInfoModel, false);
      if ((str != null) && (str.contains(localThemeInfoModel.ᴵ))) {
        localContentValues.put("status", Integer.valueOf(5));
      }
      for (;;)
      {
        localArrayList.add(localContentValues);
        break;
        if (localThemeInfoModel.ﹶ == 5) {
          localContentValues.put("status", Integer.valueOf(2));
        }
      }
    }
    ʼ.ʻ(this, ˉ.ʻ().ʻ(this, false, 291), (ContentValues[])localArrayList.toArray(new ContentValues[0]));
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    long l = getSharedPreferences("dataTrans", 0).getLong("time_data_trans", 0L);
    if ((l == 0L) || (l + 60000L < System.currentTimeMillis())) {}
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        paramIntent = ʽ.ʻ(this, ".ThemeResources");
        File localFile = new File(paramIntent, "theme.bp");
        ʻ(paramIntent);
        ʻ();
        ʽ.ʻ(ʼ.ʻ(this, null, null), localFile);
        paramIntent = getSharedPreferences("dataTrans", 0).edit();
        paramIntent.putLong("time_data_trans", System.currentTimeMillis());
        paramIntent.commit();
      }
      return;
    }
  }
}
