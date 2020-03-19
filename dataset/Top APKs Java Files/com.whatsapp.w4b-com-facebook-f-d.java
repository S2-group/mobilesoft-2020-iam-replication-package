package com.facebook.f;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Parcelable;
import com.whatsapp.core.i;
import com.whatsapp.fieldstats.u;
import com.whatsapp.phoneid.a;
import com.whatsapp.util.Log;
import com.whatsapp.util.ck;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class d
{
  private final Context a;
  private final i b;
  private final u c;
  private final a d;
  private final android.arch.lifecycle.b e;
  
  public d(Context paramContext, i paramI, u paramU, a paramA)
  {
    this.a = ((Context)ck.a(paramContext)).getApplicationContext();
    this.b = paramI;
    this.c = paramU;
    this.d = ((a)ck.a(paramA));
    this.e = null;
  }
  
  @SuppressLint({"PackageManagerGetSignatures"})
  private List<String> a()
  {
    Object localObject = this.a.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    String str = this.a.getPackageName();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo1 = (PackageInfo)((Iterator)localObject).next();
      if (!localPackageInfo1.packageName.equals(str)) {
        try
        {
          PackageInfo localPackageInfo2 = this.a.getPackageManager().getPackageInfo(localPackageInfo1.packageName, 64);
          if (b.a(a.a.a.a.d.a(localPackageInfo2))) {
            localArrayList.add(localPackageInfo2.packageName);
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          StringBuilder localStringBuilder = new StringBuilder("could not find package; packageName=");
          localStringBuilder.append(localPackageInfo1.packageName);
          Log.e(localStringBuilder.toString(), localNameNotFoundException);
        }
      }
    }
    return localArrayList;
  }
  
  public final void a(long paramLong)
  {
    Object localObject1 = a();
    Object localObject2 = new StringBuilder("found ");
    ((StringBuilder)localObject2).append(((List)localObject1).size());
    ((StringBuilder)localObject2).append(" trusted packages: ");
    ((StringBuilder)localObject2).append(localObject1);
    Log.d(((StringBuilder)localObject2).toString());
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (String)((Iterator)localObject1).next();
      localObject2 = new Intent();
      ((Intent)localObject2).setAction("com.facebook.GET_PHONE_ID");
      ((Intent)localObject2).setPackage((String)localObject3);
      localObject3 = PendingIntent.getActivity(this.a, 0, new Intent(), 134217728);
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("auth", (Parcelable)localObject3);
      this.a.sendOrderedBroadcast((Intent)localObject2, null, new e(this.b, this.c, this.d, this.e, paramLong), null, 1, null, localBundle);
    }
  }
}
