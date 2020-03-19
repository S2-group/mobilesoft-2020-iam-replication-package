package com.jb.gosms.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.jb.gosms.util.av;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserFonts
{
  private static String I = null;
  private static UserFonts Z = null;
  private Looper B = null;
  boolean Code = true;
  ArrayList V = new ArrayList();
  
  private UserFonts() {}
  
  public static UserFonts Code()
  {
    if (Z == null) {
      Z = new UserFonts();
    }
    return Z;
  }
  
  private void Code(Context paramContext)
  {
    paramContext = new n(paramContext);
    try
    {
      SQLiteDatabase localSQLiteDatabase = paramContext.getWritableDatabase();
      av.Code(localSQLiteDatabase, "UserFontsTable", null, null);
      int j = this.V.size();
      int i = 0;
      while (i < j)
      {
        av.Code(localSQLiteDatabase, "UserFontsTable", null, (ContentValues)this.V.get(i));
        i += 1;
      }
      paramContext.close();
    }
    catch (SQLiteException localSQLiteException)
    {
      paramContext.close();
      return;
    }
  }
  
  private boolean Code(Context paramContext, a paramA)
  {
    paramContext = paramContext.getPackageManager();
    Object localObject1 = paramContext.getInstalledPackages(4096);
    this.V.clear();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("com.lenovomobile.deskclock");
    localArrayList.add("com.android.systemui");
    localArrayList.add("com.monotype.android.font.shaonv");
    localArrayList.add("cn.com.fetion");
    localObject1 = ((List)localObject1).iterator();
    int i = 0;
    Object localObject2;
    Object localObject3;
    int j;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      if (localObject2 != null)
      {
        if (!this.Code) {
          return false;
        }
        localObject3 = paramA.V.obtainMessage(0);
        paramA.Z = ((PackageInfo)localObject2).packageName;
        ((Message)localObject3).obj = paramA;
        ((Message)localObject3).arg1 = 1;
        ((Message)localObject3).sendToTarget();
        if (!localArrayList.contains(((PackageInfo)localObject2).packageName)) {
          j = i;
        }
      }
    }
    for (;;)
    {
      int k;
      try
      {
        localObject3 = paramContext.getResourcesForApplication(((PackageInfo)localObject2).packageName).getAssets().list("fonts");
        j = i;
        if (localObject3 != null)
        {
          int m = 0;
          k = 0;
          j = i;
          if (k < localObject3.length)
          {
            j = i;
            if (localObject3[k].indexOf(".ttf") <= 0) {
              break label459;
            }
            j = i;
            String str1 = ((PackageInfo)localObject2).packageName;
            j = i;
            String str2 = ((PackageInfo)localObject2).applicationInfo.loadLabel(paramContext).toString();
            j = i;
            String str3 = localObject3[k].substring(0, localObject3[k].indexOf(".ttf"));
            j = i;
            String str4 = "fonts/" + localObject3[k];
            j = i;
            ContentValues localContentValues = new ContentValues();
            j = i;
            localContentValues.put("pkgName", str1);
            j = i;
            localContentValues.put("appName", str2);
            j = i;
            localContentValues.put("FontName", str3);
            j = i;
            localContentValues.put("path", str4);
            j = i;
            this.V.add(localContentValues);
            m = 1;
            break label459;
          }
          j = i;
          if (m != 0)
          {
            i += 1;
            j = i;
            localObject2 = paramA.V.obtainMessage(0);
            j = i;
            paramA.I = i;
            j = i;
            ((Message)localObject2).obj = paramA;
            j = i;
            ((Message)localObject2).arg1 = 2;
            j = i;
            ((Message)localObject2).sendToTarget();
            j = i;
          }
        }
        i = j;
      }
      catch (Exception localException)
      {
        i = j;
      }
      break;
      return true;
      label459:
      k += 1;
    }
  }
  
  protected class WorkerHandler
    extends Handler
  {
    public WorkerHandler(Looper paramLooper, Context paramContext)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      paramMessage = (UserFonts.a)paramMessage.obj;
      Object localObject = paramMessage.Code;
      if (UserFonts.Code(UserFonts.this, (Context)localObject, paramMessage)) {
        UserFonts.Code(UserFonts.this, (Context)localObject);
      }
      localObject = paramMessage.V.obtainMessage(0);
      ((Message)localObject).obj = paramMessage;
      ((Message)localObject).arg1 = 3;
      ((Message)localObject).sendToTarget();
    }
  }
  
  public static final class a
  {
    public Context Code;
    public int I;
    public Handler V;
    public String Z;
    
    public a() {}
  }
}
