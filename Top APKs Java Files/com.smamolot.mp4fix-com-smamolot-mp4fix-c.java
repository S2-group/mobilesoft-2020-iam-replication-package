package com.smamolot.mp4fix;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import java.util.Iterator;
import java.util.List;

public class c
  extends AsyncTask
{
  private final Context a;
  private final a b;
  private volatile boolean c;
  private volatile boolean d;
  private volatile boolean e;
  
  public c(Context paramContext, a paramA)
  {
    this.a = paramContext;
    this.b = paramA;
  }
  
  protected Void a(Void... paramVarArgs)
  {
    Object localObject1 = this.a.getPackageManager().getInstalledPackages(0);
    paramVarArgs = new String[7];
    paramVarArgs[0] = "com.iwobanas.screenrecorder";
    paramVarArgs[1] = "com.screen.videorecorder";
    paramVarArgs[2] = "com.jostltd.scrpro";
    paramVarArgs[3] = "com.htsoft.screenrecorder";
    paramVarArgs[4] = "com.eradicube.scrpro";
    paramVarArgs[5] = "cni.video.camera";
    paramVarArgs[6] = "com.screenrecorder.plus";
    String[] arrayOfString1 = new String[11];
    arrayOfString1[0] = "eu.chainfire.supersu";
    arrayOfString1[1] = "com.noshufou.android.su";
    arrayOfString1[2] = "com.thirdparty.superuser";
    arrayOfString1[3] = "com.koushikdutta.superuser";
    arrayOfString1[4] = "com.zachspong.temprootremovejb";
    arrayOfString1[5] = "com.ramdroid.appquarantine";
    arrayOfString1[6] = "com.kinguser.kinguser";
    arrayOfString1[7] = "com.mgyun.shua.su";
    arrayOfString1[8] = "com.lbe.security.miui";
    arrayOfString1[9] = "com.dianxinos.superuser";
    arrayOfString1[10] = "com.baidu.easyroot";
    String[] arrayOfString2 = new String[2];
    arrayOfString2[0] = "luckypatch";
    arrayOfString2[1] = "lackypatch";
    localObject1 = ((List)localObject1).iterator();
    label194:
    label243:
    label333:
    label340:
    label352:
    while (((Iterator)localObject1).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject1).next();
      int j;
      int i;
      Object localObject2;
      if (!this.c)
      {
        j = paramVarArgs.length;
        i = 0;
        if (i < j)
        {
          localObject2 = paramVarArgs[i];
          if ((localPackageInfo.packageName == null) || (!localPackageInfo.packageName.contains((CharSequence)localObject2))) {
            break label333;
          }
          this.c = true;
        }
      }
      if (!this.d)
      {
        j = arrayOfString1.length;
        i = 0;
        if (i < j)
        {
          localObject2 = arrayOfString1[i];
          if ((localPackageInfo.packageName == null) || (!localPackageInfo.packageName.contains((CharSequence)localObject2))) {
            break label340;
          }
          this.d = true;
        }
      }
      if (!this.e)
      {
        j = arrayOfString2.length;
        i = 0;
        for (;;)
        {
          if (i >= j) {
            break label352;
          }
          localObject2 = arrayOfString2[i];
          if ((localPackageInfo.packageName != null) && (localPackageInfo.packageName.contains((CharSequence)localObject2)))
          {
            this.e = true;
            break;
            i += 1;
            break label194;
            i += 1;
            break label243;
          }
          i += 1;
        }
      }
    }
    if ((!this.d) && (this.a.getPackageManager().hasSystemFeature("com.cyanogenmod.android"))) {
      this.d = true;
    }
    return null;
  }
  
  protected void a(Void paramVoid)
  {
    this.b.a(this.c);
    this.b.b(this.d);
    this.b.c(this.e);
  }
}
