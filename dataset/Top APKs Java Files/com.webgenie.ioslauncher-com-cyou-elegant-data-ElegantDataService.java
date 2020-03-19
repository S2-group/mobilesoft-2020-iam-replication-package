package com.cyou.elegant.data;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import com.c.a.c.a;
import com.cyou.elegant.c;
import com.cyou.elegant.h;
import com.cyou.elegant.model.ThemeInfoModel;
import com.cyou.elegant.model.ThemeInfoModel.Preview;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ElegantDataService
  extends IntentService
{
  private Handler a = new Handler();
  
  public ElegantDataService()
  {
    super("ElegantDataService");
  }
  
  private ArrayList<ThemeInfoModel.Preview> a(File paramFile, ThemeInfoModel paramThemeInfoModel)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramThemeInfoModel == null) || (TextUtils.isEmpty(paramThemeInfoModel.u))) {
      return localArrayList;
    }
    Iterator localIterator = paramThemeInfoModel.l.iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (ThemeInfoModel.Preview)localIterator.next();
      Object localObject1 = new File(paramThemeInfoModel.u + File.separator + "CYOUThemePreviews");
      if (!((File)localObject1).exists()) {
        return localArrayList;
      }
      localObject2 = a.a(((ThemeInfoModel.Preview)localObject2).a);
      localObject1 = ((File)localObject1).listFiles(new f(this, (String)localObject2));
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
  
  private void a()
  {
    com.cyou.elegant.theme.a.b localB = new com.cyou.elegant.theme.a.b(this);
    try
    {
      List localList = getPackageManager().getInstalledApplications(128);
      str = c.a(this, ".ThemeResources");
      if (str == null)
      {
        this.a.post(new d(this));
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
      ArrayList localArrayList1 = b.a(this, null, null);
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
            localObject1 = localB.a(localArrayList1, (String)localObject1, str);
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
      b(localArrayList2);
    }
  }
  
  private void a(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    b(c.a(c.f(this)));
    b(c.a(Environment.getExternalStorageDirectory().getAbsolutePath()));
    a(localArrayList);
    a(localArrayList, paramString);
    paramString = c.a(this, true);
    ThemeInfoModel localThemeInfoModel;
    if (paramString != null)
    {
      Iterator localIterator = paramString.iterator();
      if (localIterator.hasNext())
      {
        localThemeInfoModel = (ThemeInfoModel)localIterator.next();
        if (!TextUtils.isEmpty(localThemeInfoModel.i)) {
          break label238;
        }
      }
    }
    label238:
    for (paramString = new File(localThemeInfoModel.i);; paramString = null)
    {
      Object localObject;
      if (paramString != null)
      {
        localObject = paramString;
        if (paramString.exists()) {}
      }
      else
      {
        localObject = c.a(this, localThemeInfoModel.r, localThemeInfoModel.i);
      }
      if ((localObject == null) || (!((File)localObject).exists()) || (localArrayList.contains(localThemeInfoModel))) {
        break;
      }
      localArrayList.add(localThemeInfoModel);
      break;
      paramString = b.a(this, null, null);
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
      Collections.sort(localArrayList, new g(this));
      b(localArrayList);
      return;
    }
  }
  
  private void a(ArrayList<ThemeInfoModel> paramArrayList)
  {
    Object localObject2 = File.separator + "clauncher.cyou.inc" + File.separator + "elegant" + File.separator + "elegant.db";
    Object localObject1 = new ArrayList();
    Object localObject3 = Environment.getExternalStorageDirectory().getAbsolutePath() + (String)localObject2;
    if (new File((String)localObject3).exists()) {
      ((ArrayList)localObject1).add(localObject3);
    }
    localObject2 = c.f(this) + (String)localObject2;
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
        b.a(paramArrayList, (String)localObject2);
        localObject2 = new File((String)localObject2);
        localObject3 = new File(((File)localObject2).getParentFile(), "elegant.db-journal");
        ((File)localObject2).delete();
        ((File)localObject3).delete();
      }
    }
  }
  
  private void a(ArrayList<ThemeInfoModel> paramArrayList, String paramString)
  {
    Object localObject2 = new ArrayList();
    Object localObject1 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "cyou_nad_picwall.db";
    if (new File((String)localObject1).exists()) {
      ((ArrayList)localObject2).add(localObject1);
    }
    localObject1 = c.f(this) + File.separator + "cyou_nad_picwall.db";
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
        b.b((ArrayList)localObject1, (String)localObject3);
        b.c(this, (String)localObject3);
        localObject3 = new File((String)localObject3);
        File localFile = new File(((File)localObject3).getParentFile(), "cyou_nad_picwall.db-journal");
        ((File)localObject3).delete();
        localFile.delete();
      }
    } while (((ArrayList)localObject1).isEmpty());
    a(paramArrayList, (ArrayList)localObject1, paramString);
  }
  
  private void a(ArrayList<ThemeInfoModel> paramArrayList1, ArrayList<ThemeInfoModel> paramArrayList2, String paramString)
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
        Object localObject1 = new File(paramString + File.separator + localThemeInfoModel.r);
        if (!((File)localObject1).exists()) {
          ((File)localObject1).mkdirs();
        }
        File localFile;
        if ((localThemeInfoModel == null) || (TextUtils.isEmpty(localThemeInfoModel.u))) {
          localFile = null;
        }
        for (;;)
        {
          if (localFile != null) {
            break label290;
          }
          localIterator.remove();
          break;
          paramArrayList2 = new File(localThemeInfoModel.u + File.separator + "CYOUThemeResources" + File.separator + localThemeInfoModel.r + File.separator + a.a(localThemeInfoModel.i) + ".act");
          if (paramArrayList2.exists())
          {
            if (!((File)localObject1).exists()) {
              ((File)localObject1).mkdirs();
            }
            localFile = new File((File)localObject1, a.a(localThemeInfoModel.i) + ".amr");
            if (!localFile.exists())
            {
              paramArrayList2.renameTo(localFile);
              continue;
            }
          }
          localFile = null;
        }
        label290:
        localThemeInfoModel.w = localFile.lastModified();
        if ((localThemeInfoModel == null) || (TextUtils.isEmpty(localThemeInfoModel.u))) {
          paramArrayList2 = null;
        }
        for (;;)
        {
          localObject1 = a((File)localObject1, localThemeInfoModel);
          if (!TextUtils.equals(localThemeInfoModel.t, "apkDownload")) {
            break label569;
          }
          if (paramArrayList2 != null) {
            break label511;
          }
          localIterator.remove();
          break;
          Object localObject2 = new File(localThemeInfoModel.u + File.separator + "CYOUPicResources");
          if (((File)localObject2).exists())
          {
            paramArrayList2 = a.a(localThemeInfoModel.c);
            localObject2 = ((File)localObject2).listFiles(new e(this, paramArrayList2));
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
        localThemeInfoModel.i = localFile.getAbsolutePath();
        localThemeInfoModel.c = paramArrayList2.getAbsolutePath();
        if (((ArrayList)localObject1).size() > 0) {
          localThemeInfoModel.l = ((List)localObject1);
        }
        localThemeInfoModel.v = 2;
        localThemeInfoModel.t = "DIY";
        for (;;)
        {
          paramArrayList1.add(localThemeInfoModel);
          break;
          label569:
          if (TextUtils.equals(localThemeInfoModel.t, "actDownload"))
          {
            localThemeInfoModel.v = 2;
            localThemeInfoModel.t = "AMR";
          }
        }
      }
    }
  }
  
  private static void b(String paramString)
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
    com.cyou.elegant.util.b.b(localFile1);
  }
  
  private void b(ArrayList<ThemeInfoModel> paramArrayList)
  {
    String str = com.cyou.elegant.util.d.d(this);
    ArrayList localArrayList = new ArrayList();
    paramArrayList = paramArrayList.iterator();
    if (paramArrayList.hasNext())
    {
      ThemeInfoModel localThemeInfoModel = (ThemeInfoModel)paramArrayList.next();
      ContentValues localContentValues = i.a(localThemeInfoModel, false);
      if ((str != null) && (str.contains(localThemeInfoModel.r))) {
        localContentValues.put("status", Integer.valueOf(5));
      }
      for (;;)
      {
        localArrayList.add(localContentValues);
        break;
        if (localThemeInfoModel.v == 5) {
          localContentValues.put("status", Integer.valueOf(2));
        }
      }
    }
    b.a(this, h.a().a(this, false, 291), (ContentValues[])localArrayList.toArray(new ContentValues[0]));
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    long l = getSharedPreferences("dataTrans", 0).getLong("time_data_trans", 0L);
    if ((l == 0L) || (l + 60000L < System.currentTimeMillis())) {}
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        paramIntent = c.a(this, ".ThemeResources");
        File localFile = new File(paramIntent, "theme.bp");
        a(paramIntent);
        a();
        c.a(b.a(this, null, null), localFile);
        paramIntent = getSharedPreferences("dataTrans", 0).edit();
        paramIntent.putLong("time_data_trans", System.currentTimeMillis());
        paramIntent.commit();
      }
      return;
    }
  }
}
