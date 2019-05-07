package messenger.messenger.messanger.messenger.b;

import android.annotation.SuppressLint;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.o;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.d.b.a;
import android.text.TextUtils;
import android.util.Log;
import app.common.d.d;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import messenger.messenger.messanger.messenger.e.a;
import messenger.messenger.messanger.messenger.model.AppData;
import messenger.messenger.messanger.messenger.model.AppLaunchCountModel;
import messenger.messenger.messanger.messenger.model.StatType;
import messenger.messenger.messanger.messenger.model.UsageData;
import messenger.messenger.messanger.messenger.model.UsageDataSet;
import messenger.messenger.messanger.messenger.model.UsageStatPayload;
import org.json.JSONException;
import org.json.JSONObject;

public class b
  extends app.common.c.a
{
  public static final String b = "b";
  private static final Comparator<AppLaunchCountModel> c = -..Lambda.b.20ItrLzLw0a2eX7WHrEeW8rmS-M.INSTANCE;
  private static final Comparator<AppLaunchCountModel> d = -..Lambda.b.chdWqvP5cfDTrnE2gIlOERSTQJI.INSTANCE;
  private o<List<AppLaunchCountModel>> e;
  private o<Integer> f;
  private o<UsageDataSet> g;
  private o<String> h;
  private o<String> i;
  private UsageStatPayload j;
  private Map<String, UsageDataSet> k = new HashMap();
  private AtomicBoolean l = new AtomicBoolean(false);
  private ArrayList<AppLaunchCountModel> m = new ArrayList();
  private JSONObject n;
  
  public b(UsageStatPayload paramUsageStatPayload)
  {
    this.j = paramUsageStatPayload;
    this.e = new o();
    this.a = new o();
    this.f = new o();
    this.g = new o();
    this.h = new o();
    this.h.a(new -..Lambda.b.0PoznCbXtMM8SQfuD8BSk4n0uEo(this));
    this.i = new o();
    this.i.a(new -..Lambda.b.wgaKuGw-5UeY42bQj2yd-b7vyQs(this));
    g();
  }
  
  private Bitmap a(Drawable paramDrawable)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
    paramDrawable.draw(localCanvas);
    return localBitmap;
  }
  
  private void a(Map<String, UsageDataSet> paramMap, List<UsageStats> paramList, @StatType String paramString, UsageDataSet paramUsageDataSet)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      UsageStats localUsageStats = (UsageStats)localIterator.next();
      if (localUsageStats == null)
      {
        paramList = new StringBuilder();
        paramList.append("statType is null ");
        paramList.append(paramString);
        d.a("UsageStatsRepository", paramList.toString());
      }
      else
      {
        Object localObject = (UsageDataSet)paramMap.get(localUsageStats.getPackageName());
        paramList = (List<UsageStats>)localObject;
        if (localObject == null) {
          paramList = new UsageDataSet();
        }
        int i1 = -1;
        int i2 = paramString.hashCode();
        if (i2 != -1738378111)
        {
          if (i2 != -1681232246)
          {
            if (i2 != 64808441)
            {
              if ((i2 == 1954618349) && (paramString.equals("MONTHLY"))) {
                i1 = 2;
              }
            }
            else if (paramString.equals("DAILY")) {
              i1 = 0;
            }
          }
          else if (paramString.equals("YEARLY")) {
            i1 = 3;
          }
        }
        else if (paramString.equals("WEEKLY")) {
          i1 = 1;
        }
        switch (i1)
        {
        default: 
          break;
        case 3: 
          paramList.yearly = new UsageData(localUsageStats.getTotalTimeInForeground());
          localObject = paramUsageDataSet.yearly;
          ((UsageData)localObject).totalTimeInForeground += paramList.yearly.totalTimeInForeground;
          break;
        case 2: 
          paramList.monthly = new UsageData(localUsageStats.getTotalTimeInForeground());
          localObject = paramUsageDataSet.monthly;
          ((UsageData)localObject).totalTimeInForeground += paramList.monthly.totalTimeInForeground;
          break;
        case 1: 
          paramList.weekly = new UsageData(localUsageStats.getTotalTimeInForeground());
          localObject = paramUsageDataSet.weekly;
          ((UsageData)localObject).totalTimeInForeground += paramList.weekly.totalTimeInForeground;
          break;
        case 0: 
          paramList.daily = new UsageData(localUsageStats.getTotalTimeInForeground());
          localObject = paramUsageDataSet.daily;
          ((UsageData)localObject).totalTimeInForeground += paramList.daily.totalTimeInForeground;
        }
        paramMap.put(localUsageStats.getPackageName(), paramList);
      }
    }
  }
  
  private void g()
  {
    io.reactivex.e.a(new -..Lambda.b.Pu-XhCvmAofz4rMSiECgtWEn3ss(this)).a(new -..Lambda.b.Tepm6F7F0f9XsWEQOA0C7bW5bEk(this)).b(io.reactivex.g.a.b()).a(io.reactivex.a.b.a.a()).a(new -..Lambda.b.nhcs-j_-qc6vxYebeu2H6zCrhxo(this), -..Lambda.nuhfFEC7uEvYsR3qpsPwn8hlhHg.INSTANCE);
  }
  
  private void h()
  {
    if (app.common.d.h.a(this.m)) {
      return;
    }
    Iterator localIterator = this.m.iterator();
    while (localIterator.hasNext())
    {
      AppLaunchCountModel localAppLaunchCountModel = (AppLaunchCountModel)localIterator.next();
      localAppLaunchCountModel.usageData = ((UsageDataSet)this.k.get(localAppLaunchCountModel.packageName));
    }
  }
  
  private boolean i()
  {
    if (!app.common.d.h.a(this.j.context)) {
      return false;
    }
    if (this.l.get()) {
      return true;
    }
    Object localObject1 = (UsageStatsManager)this.j.getContext().getSystemService("usagestats");
    Object localObject2 = Calendar.getInstance();
    ((Calendar)localObject2).add(6, -5);
    if (localObject1 == null) {
      return true;
    }
    List localList1 = ((UsageStatsManager)localObject1).queryUsageStats(0, ((Calendar)localObject2).getTimeInMillis(), System.currentTimeMillis());
    List localList2 = ((UsageStatsManager)localObject1).queryUsageStats(1, ((Calendar)localObject2).getTimeInMillis(), System.currentTimeMillis());
    List localList3 = ((UsageStatsManager)localObject1).queryUsageStats(2, ((Calendar)localObject2).getTimeInMillis(), System.currentTimeMillis());
    localObject1 = ((UsageStatsManager)localObject1).queryUsageStats(3, ((Calendar)localObject2).getTimeInMillis(), System.currentTimeMillis());
    localObject2 = new UsageDataSet();
    a(this.k, localList1, "DAILY", (UsageDataSet)localObject2);
    a(this.k, localList2, "WEEKLY", (UsageDataSet)localObject2);
    a(this.k, localList3, "MONTHLY", (UsageDataSet)localObject2);
    a(this.k, (List)localObject1, "YEARLY", (UsageDataSet)localObject2);
    this.l = new AtomicBoolean(true);
    this.g.a(localObject2);
    return true;
  }
  
  private List<AppLaunchCountModel> j()
  {
    if (!app.common.d.h.a(this.m)) {
      return this.m;
    }
    Object localObject1 = new HashMap();
    Object localObject2 = new com.google.gson.b.a() {}.b();
    try
    {
      localObject2 = (HashMap)app.common.d.c.a(app.common.d.h.c("applist.json"), (Type)localObject2);
      localObject1 = localObject2;
    }
    catch (Exception localException1)
    {
      localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("Error reading JSON ");
      ((StringBuilder)localObject4).append(localException1);
      d.b("UsageStatsRepository", ((StringBuilder)localObject4).toString());
    }
    l();
    Object localObject3 = localObject1;
    if (localObject1 == null) {
      localObject3 = new HashMap();
    }
    PackageManager localPackageManager = this.j.getContext().getPackageManager();
    int i1 = 0;
    Object localObject4 = localPackageManager.getInstalledApplications(0);
    localObject1 = localObject4;
    if (localObject4 == null) {
      localObject1 = new ArrayList();
    }
    messenger.messenger.messanger.messenger.c.c.a();
    this.m = new ArrayList(((List)localObject1).size());
    int i2 = 0;
    while (i1 < ((List)localObject1).size())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((List)localObject1).get(i1);
      if (localApplicationInfo != null)
      {
        localObject4 = localApplicationInfo.packageName;
        if (((HashMap)localObject3).containsKey(localObject4))
        {
          AppLaunchCountModel localAppLaunchCountModel = new AppLaunchCountModel();
          int i3 = messenger.messenger.messanger.messenger.c.c.a((String)localObject4);
          i2 += i3;
          localAppLaunchCountModel.setLaunchCount(i3);
          messenger.messenger.messanger.messenger.c.c.a(i3);
          localAppLaunchCountModel.setAppName(localApplicationInfo.loadLabel(localPackageManager).toString());
          localAppLaunchCountModel.setPackageName((String)localObject4);
          try
          {
            if ((((HashMap)localObject3).get(localObject4) != null) && (!TextUtils.isEmpty(((AppData)((HashMap)localObject3).get(localObject4)).color))) {
              localAppLaunchCountModel.colorCode = Color.parseColor(((AppData)((HashMap)localObject3).get(localObject4)).color);
            } else {
              localAppLaunchCountModel.colorCode = c(localAppLaunchCountModel.packageName);
            }
          }
          catch (Exception localException2)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Error while parsing color for ");
            localStringBuilder.append((String)localObject4);
            d.a("UsageStatsRepository", localStringBuilder.toString(), localException2);
          }
          if (this.l.get()) {
            localAppLaunchCountModel.usageData = ((UsageDataSet)this.k.get(localAppLaunchCountModel.packageName));
          }
          this.m.add(localAppLaunchCountModel);
          k();
        }
      }
      i1 += 1;
    }
    this.f.a(Integer.valueOf(i2));
    app.common.d.e.b(this.j.getContext(), "appColors", this.n.toString());
    return new ArrayList(this.m);
  }
  
  private String k()
  {
    Object localObject2 = (String)this.i.b();
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "launch_count";
    }
    int i1 = -1;
    if ((((String)localObject1).hashCode() == -1992012396) && (((String)localObject1).equals("duration"))) {
      i1 = 0;
    }
    if (i1 != 0)
    {
      Collections.sort(this.m, c);
      return localObject1;
    }
    Object localObject3 = (String)this.h.b();
    localObject2 = localObject3;
    if (localObject3 == null) {
      localObject2 = "DAILY";
    }
    localObject3 = (UsageDataSet)this.g.b();
    if (localObject3 != null)
    {
      ((UsageDataSet)localObject3).setStatType((String)localObject2);
      this.g.a(localObject3);
    }
    localObject3 = this.m.iterator();
    while (((Iterator)localObject3).hasNext())
    {
      AppLaunchCountModel localAppLaunchCountModel = (AppLaunchCountModel)((Iterator)localObject3).next();
      if (localAppLaunchCountModel.usageData != null) {
        localAppLaunchCountModel.usageData.setStatType((String)localObject2);
      }
    }
    Collections.sort(this.m, d);
    return localObject1;
  }
  
  private void l()
  {
    if (this.n != null) {
      return;
    }
    try
    {
      this.n = new JSONObject(app.common.d.e.b(app.common.d.b.a().b(), "appColors"));
      return;
    }
    catch (NullPointerException|JSONException localNullPointerException)
    {
      Log.e(getClass().getSimpleName(), "", localNullPointerException);
      this.n = new JSONObject();
    }
  }
  
  @SuppressLint({"CheckResult"})
  public void a(int paramInt)
  {
    if (a()) {
      return;
    }
    io.reactivex.e.a(new -..Lambda.b.qrerIRKPjL69B3TEa_tVwS-9HDY(this)).b(io.reactivex.g.a.b()).a(io.reactivex.a.b.a.a()).a(new -..Lambda.XrKOMhu4YvAwn_110w9YoakQcwE(this), -..Lambda.nuhfFEC7uEvYsR3qpsPwn8hlhHg.INSTANCE);
  }
  
  public void a(@StatType String paramString)
  {
    this.h.a(paramString);
  }
  
  @SuppressLint({"CheckResult"})
  public void a(List<AppLaunchCountModel> paramList)
  {
    io.reactivex.e.a(paramList).a(500L, TimeUnit.MILLISECONDS).b(io.reactivex.g.a.b()).a(io.reactivex.a.b.a.a()).a(new -..Lambda.b.gmKKeo4TIjrzQRvFU9ZXwHAIEOY(this, paramList), -..Lambda.nuhfFEC7uEvYsR3qpsPwn8hlhHg.INSTANCE);
  }
  
  public void b(String paramString)
  {
    if (paramString.equals(this.i.b())) {
      return;
    }
    this.i.a(paramString);
  }
  
  public int c(String paramString)
    throws JSONException
  {
    if (this.n.has(paramString)) {
      return this.n.getInt(paramString);
    }
    Object localObject = com.notification.core.a.h.b(app.common.d.b.a().b(), paramString);
    if ((localObject instanceof BitmapDrawable)) {
      localObject = ((BitmapDrawable)localObject).getBitmap();
    } else {
      localObject = a((Drawable)localObject);
    }
    int i1 = android.support.v7.d.b.a((Bitmap)localObject).a().a(e.a.black);
    this.n.put(paramString, i1);
    return i1;
  }
  
  public LiveData<List<AppLaunchCountModel>> c()
  {
    return this.e;
  }
  
  public o<Integer> d()
  {
    return this.f;
  }
  
  public o<UsageDataSet> e()
  {
    return this.g;
  }
  
  @StatType
  public String f()
  {
    return (String)this.h.b();
  }
}
