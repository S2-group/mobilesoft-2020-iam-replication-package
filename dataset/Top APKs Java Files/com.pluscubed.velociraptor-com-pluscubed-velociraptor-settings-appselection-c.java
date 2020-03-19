package com.pluscubed.velociraptor.settings.appselection;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import rx.Observable;
import rx.Single;
import rx.Single.OnSubscribe;
import rx.SingleSubscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class c
{
  public static Single<List<a>> a(Context paramContext)
  {
    Single.fromCallable(new Callable()
    {
      public List<a> a()
        throws Exception
      {
        return c.c(this.a);
      }
    }).subscribeOn(Schedulers.io()).flatMapObservable(new Func1()
    {
      public Observable<a> a(List<a> paramAnonymousList)
      {
        return Observable.from(paramAnonymousList);
      }
    }).toSortedList().toSingle();
  }
  
  public static Single<List<a>> b(Context paramContext)
  {
    Single.create(new Single.OnSubscribe()
    {
      public void a(SingleSubscriber<? super List<ApplicationInfo>> paramAnonymousSingleSubscriber)
      {
        paramAnonymousSingleSubscriber.onSuccess(this.a.getPackageManager().getInstalledApplications(0));
      }
    }).subscribeOn(Schedulers.io()).flatMapObservable(new Func1()
    {
      public Observable<ApplicationInfo> a(List<ApplicationInfo> paramAnonymousList)
      {
        return Observable.from(paramAnonymousList);
      }
    }).map(new Func1()
    {
      public a a(ApplicationInfo paramAnonymousApplicationInfo)
      {
        a localA = new a();
        localA.a = paramAnonymousApplicationInfo.packageName;
        localA.b = paramAnonymousApplicationInfo.loadLabel(this.a.getPackageManager()).toString();
        return localA;
      }
    }).filter(new Func1()
    {
      public Boolean a(a paramAnonymousA)
      {
        if (!paramAnonymousA.a.equals("com.pluscubed.velociraptor")) {}
        for (boolean bool = true;; bool = false) {
          return Boolean.valueOf(bool);
        }
      }
    }).toSortedList().toSingle();
  }
  
  private static List<a> d(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = new Intent("android.intent.action.VIEW", Uri.parse("geo:37.421999,-122.084056"));
    Object localObject2 = paramContext.getPackageManager();
    if (Build.VERSION.SDK_INT >= 23) {}
    for (localObject1 = ((PackageManager)localObject2).queryIntentActivities((Intent)localObject1, 131072);; localObject1 = ((PackageManager)localObject2).queryIntentActivities((Intent)localObject1, 65536))
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ResolveInfo)((Iterator)localObject1).next();
        a localA = new a();
        localA.a = ((ResolveInfo)localObject2).activityInfo.packageName;
        localA.b = ((ResolveInfo)localObject2).loadLabel(paramContext.getPackageManager()).toString();
        localArrayList.add(localA);
      }
    }
    return localArrayList;
  }
}
