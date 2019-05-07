package services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import mtools.appupdate.Preference;
import mtools.appupdate.v2.SoftwareUpdateActivity;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NotificationService
  extends Service
{
  Bitmap B1;
  Bitmap B2;
  Bitmap B3;
  Bitmap B4;
  Bitmap B5;
  Bitmap B6;
  Bitmap B7;
  private String CHANNEL_NAME = "quantum4u";
  private int ID = 1;
  private String NOTIFICATION_CHANNEL_DISCRIPTION = "quantum4u_notification_channel";
  private int NOTIFICATION_ID = 1001;
  int TYPE_4 = 1201;
  private String app;
  private ArrayList<String> appArr;
  private String appName;
  private int appSize;
  private ArrayList<ApplicationInfo> apps;
  Bitmap b1;
  Bitmap b10;
  Bitmap b11;
  Bitmap b12;
  Bitmap b13;
  Bitmap b14;
  Bitmap b15;
  Bitmap b16;
  Bitmap b17;
  Bitmap b18;
  Bitmap b19;
  Bitmap b2;
  Bitmap b20;
  Bitmap b21;
  Bitmap b22;
  Bitmap b23;
  Bitmap b24;
  Bitmap b3;
  Bitmap b4;
  Bitmap b5;
  Bitmap b6;
  Bitmap b7;
  Bitmap b8;
  Bitmap b9;
  private String firstappName;
  private Drawable icon;
  private ArrayList<Drawable> iconArr;
  Intent intent1;
  Intent intent2;
  private Timer mTimer = null;
  private String packageName;
  private String package_name;
  private ArrayList<String> pkgArr;
  private String pkgName;
  private PackageManager pm;
  private Preference preference;
  private ServicesUtils servicesUtils;
  private ArrayList<String> updateAppsName;
  private ArrayList<Drawable> updateIcon;
  private ArrayList<String> updatePackageName;
  private ArrayList<String> updateVersion;
  private Drawable update_icon;
  private int valRadio;
  private ArrayList<String> verArr;
  private String verName;
  
  public NotificationService() {}
  
  private ArrayList<Drawable> getAppIcon(ArrayList<String> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    for (;;)
    {
      if (i >= paramArrayList.size()) {
        break label65;
      }
      Object localObject = null;
      try
      {
        Drawable localDrawable = getPackageManager().getApplicationIcon((String)paramArrayList.get(i));
        localObject = localDrawable;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          localNameNotFoundException.printStackTrace();
        }
      }
      localArrayList.add(localObject);
      i += 1;
    }
    label65:
    return localArrayList;
  }
  
  private String getAppName(ArrayList<String> paramArrayList)
  {
    if (paramArrayList.size() < 0)
    {
      System.out.println("checking pkgName " + (String)paramArrayList.get(0));
      PackageManager localPackageManager = getApplicationContext().getPackageManager();
      try
      {
        paramArrayList = localPackageManager.getApplicationInfo((String)paramArrayList.get(0), 0);
        if (paramArrayList != null)
        {
          paramArrayList = localPackageManager.getApplicationLabel(paramArrayList);
          return (String)paramArrayList;
        }
      }
      catch (PackageManager.NameNotFoundException paramArrayList)
      {
        for (;;)
        {
          paramArrayList = null;
          continue;
          paramArrayList = "(unknown)";
        }
      }
    }
    return "";
  }
  
  @NonNull
  private Bitmap getBitmapFromDrawable(@NonNull Drawable paramDrawable)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
    paramDrawable.draw(localCanvas);
    return localBitmap;
  }
  
  private ArrayList<ApplicationInfo> getInstalledApps()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = (ArrayList)this.pm.getInstalledPackages(0);
    int i = 0;
    while (i < localArrayList2.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localArrayList2.get(i);
      if (!isSystemPackage(localPackageInfo))
      {
        this.appName = localPackageInfo.applicationInfo.loadLabel(this.pm).toString();
        this.verName = localPackageInfo.versionName;
        this.pkgName = localPackageInfo.packageName;
        this.icon = localPackageInfo.applicationInfo.loadIcon(this.pm);
        this.appArr.add(this.appName);
        this.pkgArr.add(this.pkgName);
        this.verArr.add(this.verName);
        this.iconArr.add(this.icon);
        System.out.println("DeviceApp in NotificationActivity " + this.appName + "  " + this.verName + "  " + this.pkgName);
        localArrayList1.add(new ApplicationInfo());
      }
      i += 1;
    }
    return localArrayList1;
  }
  
  private boolean isSystemPackage(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  public void addNotification(String paramString, ArrayList<Drawable> paramArrayList, Context paramContext)
  {
    System.out.println("NotificationService.addNotification hello notification");
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    this.intent1 = new Intent(paramContext, SoftwareUpdateActivity.class);
    this.intent1.putExtra("_data", "Update Found");
    this.intent2 = new Intent(paramContext, NotificationActionReceiver.class);
    this.intent2.addCategory(getPackageName());
    this.intent2.setAction("btn_action");
    this.intent2.putExtra("TYPE_4", this.TYPE_4);
    this.intent2.addFlags(67108864);
    PendingIntent localPendingIntent1 = PendingIntent.getActivity(paramContext, this.TYPE_4, this.intent1, 134217728);
    PendingIntent localPendingIntent2 = PendingIntent.getActivity(paramContext, this.TYPE_4, this.intent2, 134217728);
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), 2130903174);
    Object localObject;
    Bitmap localBitmap1;
    Bitmap localBitmap2;
    Bitmap localBitmap3;
    Bitmap localBitmap4;
    Bitmap localBitmap5;
    Bitmap localBitmap6;
    Bitmap localBitmap7;
    Bitmap localBitmap8;
    Bitmap localBitmap9;
    Bitmap localBitmap10;
    Bitmap localBitmap11;
    Bitmap localBitmap12;
    Bitmap localBitmap13;
    Bitmap localBitmap14;
    Bitmap localBitmap15;
    Bitmap localBitmap16;
    Bitmap localBitmap17;
    Bitmap localBitmap18;
    Bitmap localBitmap19;
    Bitmap localBitmap20;
    Bitmap localBitmap21;
    Bitmap localBitmap22;
    Bitmap localBitmap23;
    if ((paramArrayList != null) && (paramArrayList.size() != 0))
    {
      if (Build.VERSION.SDK_INT < 26) {
        break label2025;
      }
      if (paramArrayList.size() > 7)
      {
        localObject = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
        localBitmap1 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
        localBitmap2 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
        localBitmap3 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
        localBitmap4 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
        localBitmap5 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
        localBitmap6 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
        localRemoteViews.setImageViewBitmap(2131690022, (Bitmap)localObject);
        localRemoteViews.setImageViewBitmap(2131690023, localBitmap1);
        localRemoteViews.setImageViewBitmap(2131690024, localBitmap2);
        localRemoteViews.setImageViewBitmap(2131690025, localBitmap3);
        localRemoteViews.setImageViewBitmap(2131690026, localBitmap4);
        localRemoteViews.setImageViewBitmap(2131690027, localBitmap5);
        localRemoteViews.setImageViewBitmap(2131690028, localBitmap6);
        localRemoteViews.setTextViewText(2131690029, "...");
      }
    }
    else
    {
      localRemoteViews.setTextViewText(2131690020, paramString);
      localObject = new RemoteViews(paramContext.getPackageName(), 2130903177);
      if ((paramArrayList != null) && (paramArrayList.size() != 0))
      {
        if (Build.VERSION.SDK_INT < 26) {
          break label11798;
        }
        if (paramArrayList.size() <= 24) {
          break label3037;
        }
        localBitmap1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
        localBitmap2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
        localBitmap3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
        localBitmap4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
        localBitmap5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
        localBitmap6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
        localBitmap7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
        localBitmap8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
        localBitmap9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
        localBitmap10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
        localBitmap11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
        localBitmap12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
        localBitmap13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
        localBitmap14 = getBitmapFromDrawable((Drawable)paramArrayList.get(13));
        localBitmap15 = getBitmapFromDrawable((Drawable)paramArrayList.get(14));
        localBitmap16 = getBitmapFromDrawable((Drawable)paramArrayList.get(15));
        localBitmap17 = getBitmapFromDrawable((Drawable)paramArrayList.get(16));
        localBitmap18 = getBitmapFromDrawable((Drawable)paramArrayList.get(17));
        localBitmap19 = getBitmapFromDrawable((Drawable)paramArrayList.get(18));
        localBitmap20 = getBitmapFromDrawable((Drawable)paramArrayList.get(19));
        localBitmap21 = getBitmapFromDrawable((Drawable)paramArrayList.get(20));
        localBitmap22 = getBitmapFromDrawable((Drawable)paramArrayList.get(21));
        paramArrayList = getBitmapFromDrawable((Drawable)paramArrayList.get(22));
        localBitmap23 = BitmapFactory.decodeResource(paramContext.getResources(), 2130837725);
        ((RemoteViews)localObject).setImageViewBitmap(2131690022, localBitmap1);
        ((RemoteViews)localObject).setImageViewBitmap(2131690023, localBitmap2);
        ((RemoteViews)localObject).setImageViewBitmap(2131690024, localBitmap3);
        ((RemoteViews)localObject).setImageViewBitmap(2131690025, localBitmap4);
        ((RemoteViews)localObject).setImageViewBitmap(2131690026, localBitmap5);
        ((RemoteViews)localObject).setImageViewBitmap(2131690027, localBitmap6);
        ((RemoteViews)localObject).setImageViewBitmap(2131690028, localBitmap7);
        ((RemoteViews)localObject).setImageViewBitmap(2131690033, localBitmap8);
        ((RemoteViews)localObject).setImageViewBitmap(2131690035, localBitmap9);
        ((RemoteViews)localObject).setImageViewBitmap(2131690036, localBitmap10);
        ((RemoteViews)localObject).setImageViewBitmap(2131690037, localBitmap11);
        ((RemoteViews)localObject).setImageViewBitmap(2131690038, localBitmap12);
        ((RemoteViews)localObject).setImageViewBitmap(2131690039, localBitmap13);
        ((RemoteViews)localObject).setImageViewBitmap(2131690040, localBitmap14);
        ((RemoteViews)localObject).setImageViewBitmap(2131690041, localBitmap15);
        ((RemoteViews)localObject).setImageViewBitmap(2131690042, localBitmap16);
        ((RemoteViews)localObject).setImageViewBitmap(2131690044, localBitmap17);
        ((RemoteViews)localObject).setImageViewBitmap(2131690045, localBitmap18);
        ((RemoteViews)localObject).setImageViewBitmap(2131690046, localBitmap19);
        ((RemoteViews)localObject).setImageViewBitmap(2131690047, localBitmap20);
        ((RemoteViews)localObject).setImageViewBitmap(2131690048, localBitmap21);
        ((RemoteViews)localObject).setImageViewBitmap(2131690049, localBitmap22);
        ((RemoteViews)localObject).setImageViewBitmap(2131690050, paramArrayList);
        ((RemoteViews)localObject).setImageViewBitmap(2131690051, localBitmap23);
      }
      ((RemoteViews)localObject).setTextViewText(2131690020, paramString);
      ((RemoteViews)localObject).setOnClickPendingIntent(2131690019, localPendingIntent2);
      if (Build.VERSION.SDK_INT >= 26)
      {
        paramArrayList = new NotificationChannel("12345", paramContext.getResources().getString(2131165234), 4);
        paramArrayList.setDescription("Update Software Notification");
        localNotificationManager.createNotificationChannel(paramArrayList);
      }
      paramString = new NotificationCompat.Builder(paramContext, "12345").setContentText(paramString).setCustomContentView(localRemoteViews).setCustomBigContentView((RemoteViews)localObject);
      if (Build.VERSION.SDK_INT < 21) {
        break label21054;
      }
      paramString.setSmallIcon(2130837772);
    }
    for (;;)
    {
      paramString = paramString.build();
      paramString.contentIntent = localPendingIntent1;
      paramString.flags |= 0x10;
      paramString.defaults |= 0x1;
      paramString.defaults |= 0x2;
      localNotificationManager.notify(this.TYPE_4, paramString);
      return;
      switch (paramArrayList.size())
      {
      default: 
        break;
      case 1: 
        this.B1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
        localRemoteViews.setImageViewBitmap(2131690022, this.B3);
        break;
      case 7: 
        this.B1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
        this.B2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
        this.B3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
        this.B4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
        this.B5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
        this.B6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
        this.B7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
        localRemoteViews.setImageViewBitmap(2131690022, this.B1);
        localRemoteViews.setImageViewBitmap(2131690023, this.B2);
        localRemoteViews.setImageViewBitmap(2131690024, this.B3);
        localRemoteViews.setImageViewBitmap(2131690025, this.B4);
        localRemoteViews.setImageViewBitmap(2131690026, this.B5);
        localRemoteViews.setImageViewBitmap(2131690027, this.B6);
        localRemoteViews.setImageViewBitmap(2131690028, this.B7);
        break;
      case 6: 
        this.B1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
        this.B2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
        this.B3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
        this.B4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
        this.B5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
        this.B6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
        localRemoteViews.setImageViewBitmap(2131690022, this.B1);
        localRemoteViews.setImageViewBitmap(2131690023, this.B2);
        localRemoteViews.setImageViewBitmap(2131690024, this.B3);
        localRemoteViews.setImageViewBitmap(2131690025, this.B4);
        localRemoteViews.setImageViewBitmap(2131690026, this.B5);
        localRemoteViews.setImageViewBitmap(2131690027, this.B6);
        break;
      case 5: 
        this.B1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
        this.B2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
        this.B3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
        this.B4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
        this.B5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
        localRemoteViews.setImageViewBitmap(2131690022, this.B1);
        localRemoteViews.setImageViewBitmap(2131690023, this.B2);
        localRemoteViews.setImageViewBitmap(2131690024, this.B3);
        localRemoteViews.setImageViewBitmap(2131690025, this.B4);
        localRemoteViews.setImageViewBitmap(2131690026, this.B5);
        break;
      case 4: 
        this.B1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
        this.B2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
        this.B3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
        this.B4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
        localRemoteViews.setImageViewBitmap(2131690022, this.B1);
        localRemoteViews.setImageViewBitmap(2131690023, this.B2);
        localRemoteViews.setImageViewBitmap(2131690024, this.B3);
        localRemoteViews.setImageViewBitmap(2131690025, this.B4);
        break;
      case 3: 
        this.B1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
        this.B2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
        this.B3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
        localRemoteViews.setImageViewBitmap(2131690022, this.B1);
        localRemoteViews.setImageViewBitmap(2131690023, this.B2);
        localRemoteViews.setImageViewBitmap(2131690024, this.B3);
        break;
      case 2: 
        this.B1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
        this.B2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
        localRemoteViews.setImageViewBitmap(2131690022, this.B1);
        localRemoteViews.setImageViewBitmap(2131690023, this.B2);
        break;
        label2025:
        if (paramArrayList.size() > 7)
        {
          localObject = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
          localBitmap1 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
          localBitmap2 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
          localBitmap3 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
          localBitmap4 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
          localBitmap5 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
          localBitmap6 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
          localRemoteViews.setImageViewBitmap(2131690022, (Bitmap)localObject);
          localRemoteViews.setImageViewBitmap(2131690023, localBitmap1);
          localRemoteViews.setImageViewBitmap(2131690024, localBitmap2);
          localRemoteViews.setImageViewBitmap(2131690025, localBitmap3);
          localRemoteViews.setImageViewBitmap(2131690026, localBitmap4);
          localRemoteViews.setImageViewBitmap(2131690027, localBitmap5);
          localRemoteViews.setImageViewBitmap(2131690028, localBitmap6);
          localRemoteViews.setTextViewText(2131690029, "...");
          break;
        }
        switch (paramArrayList.size())
        {
        default: 
          break;
        case 1: 
          this.B1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
          localRemoteViews.setImageViewBitmap(2131690022, this.B1);
          break;
        case 7: 
          this.B1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
          this.B2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
          this.B3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
          this.B4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
          this.B5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
          this.B6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
          this.B7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
          localRemoteViews.setImageViewBitmap(2131690022, this.B1);
          localRemoteViews.setImageViewBitmap(2131690023, this.B2);
          localRemoteViews.setImageViewBitmap(2131690024, this.B3);
          localRemoteViews.setImageViewBitmap(2131690025, this.B4);
          localRemoteViews.setImageViewBitmap(2131690026, this.B5);
          localRemoteViews.setImageViewBitmap(2131690027, this.B6);
          localRemoteViews.setImageViewBitmap(2131690028, this.B7);
          break;
        case 6: 
          this.B1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
          this.B2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
          this.B3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
          this.B4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
          this.B5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
          this.B6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
          localRemoteViews.setImageViewBitmap(2131690022, this.B1);
          localRemoteViews.setImageViewBitmap(2131690023, this.B2);
          localRemoteViews.setImageViewBitmap(2131690024, this.B3);
          localRemoteViews.setImageViewBitmap(2131690025, this.B4);
          localRemoteViews.setImageViewBitmap(2131690026, this.B5);
          localRemoteViews.setImageViewBitmap(2131690027, this.B6);
          break;
        case 5: 
          this.B1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
          this.B2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
          this.B3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
          this.B4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
          this.B5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
          localRemoteViews.setImageViewBitmap(2131690022, this.B1);
          localRemoteViews.setImageViewBitmap(2131690023, this.B2);
          localRemoteViews.setImageViewBitmap(2131690024, this.B3);
          localRemoteViews.setImageViewBitmap(2131690025, this.B4);
          localRemoteViews.setImageViewBitmap(2131690026, this.B5);
          break;
        case 4: 
          this.B1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
          this.B2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
          this.B3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
          this.B4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
          localRemoteViews.setImageViewBitmap(2131690022, this.B1);
          localRemoteViews.setImageViewBitmap(2131690023, this.B2);
          localRemoteViews.setImageViewBitmap(2131690024, this.B3);
          localRemoteViews.setImageViewBitmap(2131690025, this.B4);
          break;
        case 3: 
          this.B1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
          this.B2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
          this.B3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
          localRemoteViews.setImageViewBitmap(2131690022, this.B1);
          localRemoteViews.setImageViewBitmap(2131690023, this.B2);
          localRemoteViews.setImageViewBitmap(2131690024, this.B3);
          break;
        case 2: 
          this.B1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
          this.B2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
          localRemoteViews.setImageViewBitmap(2131690022, this.B1);
          localRemoteViews.setImageViewBitmap(2131690023, this.B2);
          break;
          label3037:
          switch (paramArrayList.size())
          {
          default: 
            break;
          case 1: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            break;
          case 24: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            this.b13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
            this.b14 = getBitmapFromDrawable((Drawable)paramArrayList.get(13));
            this.b15 = getBitmapFromDrawable((Drawable)paramArrayList.get(14));
            this.b16 = getBitmapFromDrawable((Drawable)paramArrayList.get(15));
            this.b17 = getBitmapFromDrawable((Drawable)paramArrayList.get(16));
            this.b18 = getBitmapFromDrawable((Drawable)paramArrayList.get(17));
            this.b19 = getBitmapFromDrawable((Drawable)paramArrayList.get(18));
            this.b20 = getBitmapFromDrawable((Drawable)paramArrayList.get(19));
            this.b21 = getBitmapFromDrawable((Drawable)paramArrayList.get(20));
            this.b22 = getBitmapFromDrawable((Drawable)paramArrayList.get(21));
            this.b23 = getBitmapFromDrawable((Drawable)paramArrayList.get(22));
            this.b24 = getBitmapFromDrawable((Drawable)paramArrayList.get(23));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
            ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
            ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
            ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
            ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
            ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
            ((RemoteViews)localObject).setImageViewBitmap(2131690046, this.b19);
            ((RemoteViews)localObject).setImageViewBitmap(2131690047, this.b20);
            ((RemoteViews)localObject).setImageViewBitmap(2131690048, this.b21);
            ((RemoteViews)localObject).setImageViewBitmap(2131690049, this.b22);
            ((RemoteViews)localObject).setImageViewBitmap(2131690050, this.b23);
            ((RemoteViews)localObject).setImageViewBitmap(2131690051, this.b24);
            break;
          case 23: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            this.b13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
            this.b14 = getBitmapFromDrawable((Drawable)paramArrayList.get(13));
            this.b15 = getBitmapFromDrawable((Drawable)paramArrayList.get(14));
            this.b16 = getBitmapFromDrawable((Drawable)paramArrayList.get(15));
            this.b17 = getBitmapFromDrawable((Drawable)paramArrayList.get(16));
            this.b18 = getBitmapFromDrawable((Drawable)paramArrayList.get(17));
            this.b19 = getBitmapFromDrawable((Drawable)paramArrayList.get(18));
            this.b20 = getBitmapFromDrawable((Drawable)paramArrayList.get(19));
            this.b21 = getBitmapFromDrawable((Drawable)paramArrayList.get(20));
            this.b22 = getBitmapFromDrawable((Drawable)paramArrayList.get(21));
            this.b23 = getBitmapFromDrawable((Drawable)paramArrayList.get(22));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
            ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
            ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
            ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
            ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
            ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
            ((RemoteViews)localObject).setImageViewBitmap(2131690046, this.b19);
            ((RemoteViews)localObject).setImageViewBitmap(2131690047, this.b20);
            ((RemoteViews)localObject).setImageViewBitmap(2131690048, this.b21);
            ((RemoteViews)localObject).setImageViewBitmap(2131690049, this.b22);
            ((RemoteViews)localObject).setImageViewBitmap(2131690050, this.b23);
            break;
          case 22: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            this.b13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
            this.b14 = getBitmapFromDrawable((Drawable)paramArrayList.get(13));
            this.b15 = getBitmapFromDrawable((Drawable)paramArrayList.get(14));
            this.b16 = getBitmapFromDrawable((Drawable)paramArrayList.get(15));
            this.b17 = getBitmapFromDrawable((Drawable)paramArrayList.get(16));
            this.b18 = getBitmapFromDrawable((Drawable)paramArrayList.get(17));
            this.b19 = getBitmapFromDrawable((Drawable)paramArrayList.get(18));
            this.b20 = getBitmapFromDrawable((Drawable)paramArrayList.get(19));
            this.b21 = getBitmapFromDrawable((Drawable)paramArrayList.get(20));
            this.b22 = getBitmapFromDrawable((Drawable)paramArrayList.get(21));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
            ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
            ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
            ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
            ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
            ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
            ((RemoteViews)localObject).setImageViewBitmap(2131690046, this.b19);
            ((RemoteViews)localObject).setImageViewBitmap(2131690047, this.b20);
            ((RemoteViews)localObject).setImageViewBitmap(2131690048, this.b21);
            ((RemoteViews)localObject).setImageViewBitmap(2131690049, this.b22);
            break;
          case 21: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            this.b13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
            this.b14 = getBitmapFromDrawable((Drawable)paramArrayList.get(13));
            this.b15 = getBitmapFromDrawable((Drawable)paramArrayList.get(14));
            this.b16 = getBitmapFromDrawable((Drawable)paramArrayList.get(15));
            this.b17 = getBitmapFromDrawable((Drawable)paramArrayList.get(16));
            this.b18 = getBitmapFromDrawable((Drawable)paramArrayList.get(17));
            this.b19 = getBitmapFromDrawable((Drawable)paramArrayList.get(18));
            this.b20 = getBitmapFromDrawable((Drawable)paramArrayList.get(19));
            this.b21 = getBitmapFromDrawable((Drawable)paramArrayList.get(20));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
            ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
            ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
            ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
            ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
            ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
            ((RemoteViews)localObject).setImageViewBitmap(2131690046, this.b19);
            ((RemoteViews)localObject).setImageViewBitmap(2131690047, this.b20);
            ((RemoteViews)localObject).setImageViewBitmap(2131690048, this.b21);
            break;
          case 20: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            this.b13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
            this.b14 = getBitmapFromDrawable((Drawable)paramArrayList.get(13));
            this.b15 = getBitmapFromDrawable((Drawable)paramArrayList.get(14));
            this.b16 = getBitmapFromDrawable((Drawable)paramArrayList.get(15));
            this.b17 = getBitmapFromDrawable((Drawable)paramArrayList.get(16));
            this.b18 = getBitmapFromDrawable((Drawable)paramArrayList.get(17));
            this.b19 = getBitmapFromDrawable((Drawable)paramArrayList.get(18));
            this.b20 = getBitmapFromDrawable((Drawable)paramArrayList.get(19));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
            ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
            ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
            ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
            ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
            ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
            ((RemoteViews)localObject).setImageViewBitmap(2131690046, this.b19);
            ((RemoteViews)localObject).setImageViewBitmap(2131690047, this.b20);
            break;
          case 19: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            this.b13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
            this.b14 = getBitmapFromDrawable((Drawable)paramArrayList.get(13));
            this.b15 = getBitmapFromDrawable((Drawable)paramArrayList.get(14));
            this.b16 = getBitmapFromDrawable((Drawable)paramArrayList.get(15));
            this.b17 = getBitmapFromDrawable((Drawable)paramArrayList.get(16));
            this.b18 = getBitmapFromDrawable((Drawable)paramArrayList.get(17));
            this.b19 = getBitmapFromDrawable((Drawable)paramArrayList.get(18));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
            ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
            ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
            ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
            ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
            ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
            ((RemoteViews)localObject).setImageViewBitmap(2131690046, this.b19);
            break;
          case 18: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            this.b13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
            this.b14 = getBitmapFromDrawable((Drawable)paramArrayList.get(13));
            this.b15 = getBitmapFromDrawable((Drawable)paramArrayList.get(14));
            this.b16 = getBitmapFromDrawable((Drawable)paramArrayList.get(15));
            this.b17 = getBitmapFromDrawable((Drawable)paramArrayList.get(16));
            this.b18 = getBitmapFromDrawable((Drawable)paramArrayList.get(17));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
            ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
            ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
            ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
            ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
            ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
            break;
          case 17: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            this.b13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
            this.b14 = getBitmapFromDrawable((Drawable)paramArrayList.get(13));
            this.b15 = getBitmapFromDrawable((Drawable)paramArrayList.get(14));
            this.b16 = getBitmapFromDrawable((Drawable)paramArrayList.get(15));
            this.b17 = getBitmapFromDrawable((Drawable)paramArrayList.get(16));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
            ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
            ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
            ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
            ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
            break;
          case 16: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            this.b13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
            this.b14 = getBitmapFromDrawable((Drawable)paramArrayList.get(13));
            this.b15 = getBitmapFromDrawable((Drawable)paramArrayList.get(14));
            this.b16 = getBitmapFromDrawable((Drawable)paramArrayList.get(15));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
            ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
            ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
            ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
            break;
          case 15: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            this.b13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
            this.b14 = getBitmapFromDrawable((Drawable)paramArrayList.get(13));
            this.b15 = getBitmapFromDrawable((Drawable)paramArrayList.get(14));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
            ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
            ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
            break;
          case 14: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            this.b13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
            this.b14 = getBitmapFromDrawable((Drawable)paramArrayList.get(13));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
            ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
            break;
          case 13: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            this.b13 = getBitmapFromDrawable((Drawable)paramArrayList.get(12));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
            break;
          case 12: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            this.b12 = getBitmapFromDrawable((Drawable)paramArrayList.get(11));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
            break;
          case 11: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            this.b11 = getBitmapFromDrawable((Drawable)paramArrayList.get(10));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
            break;
          case 10: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            this.b10 = getBitmapFromDrawable((Drawable)paramArrayList.get(9));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
            break;
          case 9: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            this.b9 = getBitmapFromDrawable((Drawable)paramArrayList.get(8));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
            break;
          case 8: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            this.b8 = getBitmapFromDrawable((Drawable)paramArrayList.get(7));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
            break;
          case 7: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            this.b7 = getBitmapFromDrawable((Drawable)paramArrayList.get(6));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
            break;
          case 6: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            this.b6 = getBitmapFromDrawable((Drawable)paramArrayList.get(5));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
            break;
          case 5: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            this.b5 = getBitmapFromDrawable((Drawable)paramArrayList.get(4));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
            break;
          case 4: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            this.b4 = getBitmapFromDrawable((Drawable)paramArrayList.get(3));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
            break;
          case 3: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            this.b3 = getBitmapFromDrawable((Drawable)paramArrayList.get(2));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
            break;
          case 2: 
            this.b1 = getBitmapFromDrawable((Drawable)paramArrayList.get(0));
            this.b2 = getBitmapFromDrawable((Drawable)paramArrayList.get(1));
            ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
            ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
            break;
            label11798:
            if (paramArrayList.size() > 24)
            {
              localBitmap1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              localBitmap2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              localBitmap3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              localBitmap4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              localBitmap5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              localBitmap6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              localBitmap7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              localBitmap8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              localBitmap9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              localBitmap10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              localBitmap11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              localBitmap12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              localBitmap13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              localBitmap14 = ((BitmapDrawable)paramArrayList.get(13)).getBitmap();
              localBitmap15 = ((BitmapDrawable)paramArrayList.get(14)).getBitmap();
              localBitmap16 = ((BitmapDrawable)paramArrayList.get(15)).getBitmap();
              localBitmap17 = ((BitmapDrawable)paramArrayList.get(16)).getBitmap();
              localBitmap18 = ((BitmapDrawable)paramArrayList.get(17)).getBitmap();
              localBitmap19 = ((BitmapDrawable)paramArrayList.get(18)).getBitmap();
              localBitmap20 = ((BitmapDrawable)paramArrayList.get(19)).getBitmap();
              localBitmap21 = ((BitmapDrawable)paramArrayList.get(20)).getBitmap();
              localBitmap22 = ((BitmapDrawable)paramArrayList.get(21)).getBitmap();
              paramArrayList = ((BitmapDrawable)paramArrayList.get(22)).getBitmap();
              localBitmap23 = BitmapFactory.decodeResource(paramContext.getResources(), 2130837725);
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, localBitmap1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, localBitmap2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, localBitmap3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, localBitmap4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, localBitmap5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, localBitmap6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, localBitmap7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, localBitmap8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, localBitmap9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, localBitmap10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, localBitmap11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, localBitmap12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, localBitmap13);
              ((RemoteViews)localObject).setImageViewBitmap(2131690040, localBitmap14);
              ((RemoteViews)localObject).setImageViewBitmap(2131690041, localBitmap15);
              ((RemoteViews)localObject).setImageViewBitmap(2131690042, localBitmap16);
              ((RemoteViews)localObject).setImageViewBitmap(2131690044, localBitmap17);
              ((RemoteViews)localObject).setImageViewBitmap(2131690045, localBitmap18);
              ((RemoteViews)localObject).setImageViewBitmap(2131690046, localBitmap19);
              ((RemoteViews)localObject).setImageViewBitmap(2131690047, localBitmap20);
              ((RemoteViews)localObject).setImageViewBitmap(2131690048, localBitmap21);
              ((RemoteViews)localObject).setImageViewBitmap(2131690049, localBitmap22);
              ((RemoteViews)localObject).setImageViewBitmap(2131690050, paramArrayList);
              ((RemoteViews)localObject).setImageViewBitmap(2131690051, localBitmap23);
              break;
            }
            switch (paramArrayList.size())
            {
            default: 
              break;
            case 1: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              System.out.println("NotificationService.addNotification 1");
              break;
            case 24: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              this.b13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              this.b14 = ((BitmapDrawable)paramArrayList.get(13)).getBitmap();
              this.b15 = ((BitmapDrawable)paramArrayList.get(14)).getBitmap();
              this.b16 = ((BitmapDrawable)paramArrayList.get(15)).getBitmap();
              this.b17 = ((BitmapDrawable)paramArrayList.get(16)).getBitmap();
              this.b18 = ((BitmapDrawable)paramArrayList.get(17)).getBitmap();
              this.b19 = ((BitmapDrawable)paramArrayList.get(18)).getBitmap();
              this.b20 = ((BitmapDrawable)paramArrayList.get(19)).getBitmap();
              this.b21 = ((BitmapDrawable)paramArrayList.get(20)).getBitmap();
              this.b22 = ((BitmapDrawable)paramArrayList.get(21)).getBitmap();
              this.b23 = ((BitmapDrawable)paramArrayList.get(22)).getBitmap();
              this.b24 = ((BitmapDrawable)paramArrayList.get(23)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
              ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
              ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
              ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
              ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
              ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
              ((RemoteViews)localObject).setImageViewBitmap(2131690046, this.b19);
              ((RemoteViews)localObject).setImageViewBitmap(2131690047, this.b20);
              ((RemoteViews)localObject).setImageViewBitmap(2131690048, this.b21);
              ((RemoteViews)localObject).setImageViewBitmap(2131690049, this.b22);
              ((RemoteViews)localObject).setImageViewBitmap(2131690050, this.b23);
              ((RemoteViews)localObject).setImageViewBitmap(2131690051, this.b24);
              System.out.println("NotificationService.addNotification 24");
              break;
            case 23: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              this.b13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              this.b14 = ((BitmapDrawable)paramArrayList.get(13)).getBitmap();
              this.b15 = ((BitmapDrawable)paramArrayList.get(14)).getBitmap();
              this.b16 = ((BitmapDrawable)paramArrayList.get(15)).getBitmap();
              this.b17 = ((BitmapDrawable)paramArrayList.get(16)).getBitmap();
              this.b18 = ((BitmapDrawable)paramArrayList.get(17)).getBitmap();
              this.b19 = ((BitmapDrawable)paramArrayList.get(18)).getBitmap();
              this.b20 = ((BitmapDrawable)paramArrayList.get(19)).getBitmap();
              this.b21 = ((BitmapDrawable)paramArrayList.get(20)).getBitmap();
              this.b22 = ((BitmapDrawable)paramArrayList.get(21)).getBitmap();
              this.b23 = ((BitmapDrawable)paramArrayList.get(22)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
              ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
              ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
              ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
              ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
              ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
              ((RemoteViews)localObject).setImageViewBitmap(2131690046, this.b19);
              ((RemoteViews)localObject).setImageViewBitmap(2131690047, this.b20);
              ((RemoteViews)localObject).setImageViewBitmap(2131690048, this.b21);
              ((RemoteViews)localObject).setImageViewBitmap(2131690049, this.b22);
              ((RemoteViews)localObject).setImageViewBitmap(2131690050, this.b23);
              System.out.println("NotificationService.addNotification 23");
              break;
            case 22: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              this.b13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              this.b14 = ((BitmapDrawable)paramArrayList.get(13)).getBitmap();
              this.b15 = ((BitmapDrawable)paramArrayList.get(14)).getBitmap();
              this.b16 = ((BitmapDrawable)paramArrayList.get(15)).getBitmap();
              this.b17 = ((BitmapDrawable)paramArrayList.get(16)).getBitmap();
              this.b18 = ((BitmapDrawable)paramArrayList.get(17)).getBitmap();
              this.b19 = ((BitmapDrawable)paramArrayList.get(18)).getBitmap();
              this.b20 = ((BitmapDrawable)paramArrayList.get(19)).getBitmap();
              this.b21 = ((BitmapDrawable)paramArrayList.get(20)).getBitmap();
              this.b22 = ((BitmapDrawable)paramArrayList.get(21)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
              ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
              ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
              ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
              ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
              ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
              ((RemoteViews)localObject).setImageViewBitmap(2131690046, this.b19);
              ((RemoteViews)localObject).setImageViewBitmap(2131690047, this.b20);
              ((RemoteViews)localObject).setImageViewBitmap(2131690048, this.b21);
              ((RemoteViews)localObject).setImageViewBitmap(2131690049, this.b22);
              System.out.println("NotificationService.addNotification 22");
              break;
            case 21: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              this.b13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              this.b14 = ((BitmapDrawable)paramArrayList.get(13)).getBitmap();
              this.b15 = ((BitmapDrawable)paramArrayList.get(14)).getBitmap();
              this.b16 = ((BitmapDrawable)paramArrayList.get(15)).getBitmap();
              this.b17 = ((BitmapDrawable)paramArrayList.get(16)).getBitmap();
              this.b18 = ((BitmapDrawable)paramArrayList.get(17)).getBitmap();
              this.b19 = ((BitmapDrawable)paramArrayList.get(18)).getBitmap();
              this.b20 = ((BitmapDrawable)paramArrayList.get(19)).getBitmap();
              this.b21 = ((BitmapDrawable)paramArrayList.get(20)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
              ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
              ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
              ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
              ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
              ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
              ((RemoteViews)localObject).setImageViewBitmap(2131690046, this.b19);
              ((RemoteViews)localObject).setImageViewBitmap(2131690047, this.b20);
              ((RemoteViews)localObject).setImageViewBitmap(2131690048, this.b21);
              System.out.println("NotificationService.addNotification 21");
              break;
            case 20: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              this.b13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              this.b14 = ((BitmapDrawable)paramArrayList.get(13)).getBitmap();
              this.b15 = ((BitmapDrawable)paramArrayList.get(14)).getBitmap();
              this.b16 = ((BitmapDrawable)paramArrayList.get(15)).getBitmap();
              this.b17 = ((BitmapDrawable)paramArrayList.get(16)).getBitmap();
              this.b18 = ((BitmapDrawable)paramArrayList.get(17)).getBitmap();
              this.b19 = ((BitmapDrawable)paramArrayList.get(18)).getBitmap();
              this.b20 = ((BitmapDrawable)paramArrayList.get(19)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
              ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
              ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
              ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
              ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
              ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
              ((RemoteViews)localObject).setImageViewBitmap(2131690046, this.b19);
              ((RemoteViews)localObject).setImageViewBitmap(2131690047, this.b20);
              System.out.println("NotificationService.addNotification 20");
              break;
            case 19: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              this.b13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              this.b14 = ((BitmapDrawable)paramArrayList.get(13)).getBitmap();
              this.b15 = ((BitmapDrawable)paramArrayList.get(14)).getBitmap();
              this.b16 = ((BitmapDrawable)paramArrayList.get(15)).getBitmap();
              this.b17 = ((BitmapDrawable)paramArrayList.get(16)).getBitmap();
              this.b18 = ((BitmapDrawable)paramArrayList.get(17)).getBitmap();
              this.b19 = ((BitmapDrawable)paramArrayList.get(18)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
              ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
              ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
              ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
              ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
              ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
              ((RemoteViews)localObject).setImageViewBitmap(2131690046, this.b19);
              System.out.println("NotificationService.addNotification 19");
              break;
            case 18: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              this.b13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              this.b14 = ((BitmapDrawable)paramArrayList.get(13)).getBitmap();
              this.b15 = ((BitmapDrawable)paramArrayList.get(14)).getBitmap();
              this.b16 = ((BitmapDrawable)paramArrayList.get(15)).getBitmap();
              this.b17 = ((BitmapDrawable)paramArrayList.get(16)).getBitmap();
              this.b18 = ((BitmapDrawable)paramArrayList.get(17)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
              ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
              ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
              ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
              ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
              ((RemoteViews)localObject).setImageViewBitmap(2131690045, this.b18);
              System.out.println("NotificationService.addNotification 18");
              break;
            case 17: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              this.b13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              this.b14 = ((BitmapDrawable)paramArrayList.get(13)).getBitmap();
              this.b15 = ((BitmapDrawable)paramArrayList.get(14)).getBitmap();
              this.b16 = ((BitmapDrawable)paramArrayList.get(15)).getBitmap();
              this.b17 = ((BitmapDrawable)paramArrayList.get(16)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
              ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
              ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
              ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
              ((RemoteViews)localObject).setImageViewBitmap(2131690044, this.b17);
              System.out.println("NotificationService.addNotification 17");
              break;
            case 16: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              this.b13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              this.b14 = ((BitmapDrawable)paramArrayList.get(13)).getBitmap();
              this.b15 = ((BitmapDrawable)paramArrayList.get(14)).getBitmap();
              this.b16 = ((BitmapDrawable)paramArrayList.get(15)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
              ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
              ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
              ((RemoteViews)localObject).setImageViewBitmap(2131690042, this.b16);
              System.out.println("NotificationService.addNotification 16");
              break;
            case 15: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              this.b13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              this.b14 = ((BitmapDrawable)paramArrayList.get(13)).getBitmap();
              this.b15 = ((BitmapDrawable)paramArrayList.get(14)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
              ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
              ((RemoteViews)localObject).setImageViewBitmap(2131690041, this.b15);
              System.out.println("NotificationService.addNotification 15");
              break;
            case 14: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              this.b13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              this.b14 = ((BitmapDrawable)paramArrayList.get(13)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
              ((RemoteViews)localObject).setImageViewBitmap(2131690040, this.b14);
              System.out.println("NotificationService.addNotification 14");
              break;
            case 13: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              this.b13 = ((BitmapDrawable)paramArrayList.get(12)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              ((RemoteViews)localObject).setImageViewBitmap(2131690039, this.b13);
              System.out.println("NotificationService.addNotification 13");
              break;
            case 12: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              this.b12 = ((BitmapDrawable)paramArrayList.get(11)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              ((RemoteViews)localObject).setImageViewBitmap(2131690038, this.b12);
              System.out.println("NotificationService.addNotification 12");
              break;
            case 11: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              this.b11 = ((BitmapDrawable)paramArrayList.get(10)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              ((RemoteViews)localObject).setImageViewBitmap(2131690037, this.b11);
              System.out.println("NotificationService.addNotification 11");
              break;
            case 10: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              this.b10 = ((BitmapDrawable)paramArrayList.get(9)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              ((RemoteViews)localObject).setImageViewBitmap(2131690036, this.b10);
              System.out.println("NotificationService.addNotification 10");
              break;
            case 9: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              this.b9 = ((BitmapDrawable)paramArrayList.get(8)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              ((RemoteViews)localObject).setImageViewBitmap(2131690035, this.b9);
              System.out.println("NotificationService.addNotification 9");
              break;
            case 8: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              this.b8 = ((BitmapDrawable)paramArrayList.get(7)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              ((RemoteViews)localObject).setImageViewBitmap(2131690033, this.b8);
              System.out.println("NotificationService.addNotification 8");
              break;
            case 7: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              this.b7 = ((BitmapDrawable)paramArrayList.get(6)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              ((RemoteViews)localObject).setImageViewBitmap(2131690028, this.b7);
              System.out.println("NotificationService.addNotification 7");
              break;
            case 6: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              this.b6 = ((BitmapDrawable)paramArrayList.get(5)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              ((RemoteViews)localObject).setImageViewBitmap(2131690027, this.b6);
              System.out.println("NotificationService.addNotification 6");
              break;
            case 5: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              this.b5 = ((BitmapDrawable)paramArrayList.get(4)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              ((RemoteViews)localObject).setImageViewBitmap(2131690026, this.b5);
              System.out.println("NotificationService.addNotification 5");
              break;
            case 4: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              this.b4 = ((BitmapDrawable)paramArrayList.get(3)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              ((RemoteViews)localObject).setImageViewBitmap(2131690025, this.b4);
              System.out.println("NotificationService.addNotification 4");
              break;
            case 3: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              this.b3 = ((BitmapDrawable)paramArrayList.get(2)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              ((RemoteViews)localObject).setImageViewBitmap(2131690024, this.b3);
              System.out.println("NotificationService.addNotification 3");
              break;
            case 2: 
              this.b1 = ((BitmapDrawable)paramArrayList.get(0)).getBitmap();
              this.b2 = ((BitmapDrawable)paramArrayList.get(1)).getBitmap();
              ((RemoteViews)localObject).setImageViewBitmap(2131690022, this.b1);
              ((RemoteViews)localObject).setImageViewBitmap(2131690023, this.b2);
              System.out.println("NotificationService.addNotification 2");
              break;
              label21054:
              paramString.setSmallIcon(2130837600);
            }
            break;
          }
          break;
        }
        break;
      }
    }
  }
  
  @Nullable
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    if (Build.VERSION.SDK_INT >= 26) {
      super.startForeground(this.ID, new Notification());
    }
    this.pm = getPackageManager();
    this.preference = new Preference(this);
    this.servicesUtils = new ServicesUtils(this);
    this.mTimer = new Timer();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    System.out.println("inside onstartcommand");
    if (this.mTimer != null) {
      this.mTimer.scheduleAtFixedRate(new TimeForNotificaton(null), 1800000L, 21600000L);
    }
    return 1;
  }
  
  private class GetVersionCode
    extends AsyncTask<Void, String, String>
  {
    private GetVersionCode() {}
    
    protected String doInBackground(Void... paramVarArgs)
    {
      try
      {
        paramVarArgs = Jsoup.connect("https://play.google.com/store/apps/details?id=" + NotificationService.this.package_name).timeout(30000).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").referrer("http://www.google.com").get().select("div:containsOwn(Current Version)").next().text();
        return paramVarArgs;
      }
      catch (Exception paramVarArgs) {}
      return null;
    }
    
    protected void onPostExecute(String paramString)
    {
      super.onPostExecute(paramString);
    }
  }
  
  private class NewUpdateVersion
    extends AsyncTask<Void, ArrayList<String>, ArrayList<String>>
  {
    private ArrayList<String> checkList;
    private String newVersion;
    private ArrayList<String> variesList;
    
    private NewUpdateVersion() {}
    
    protected ArrayList<String> doInBackground(Void... paramVarArgs)
    {
      this.checkList = new ArrayList();
      this.variesList = new ArrayList();
      int i = 0;
      for (;;)
      {
        if (i < NotificationService.this.pkgArr.size()) {
          for (;;)
          {
            try
            {
              NotificationService.access$202(NotificationService.this, (String)NotificationService.this.pkgArr.get(i));
              NotificationService.access$302(NotificationService.this, (String)NotificationService.this.pkgArr.get(i));
              NotificationService.access$402(NotificationService.this, (Drawable)NotificationService.this.iconArr.get(i));
              NotificationService.access$602(NotificationService.this, (String)NotificationService.this.appArr.get(i));
              if (NotificationService.this.package_name == null) {
                break;
              }
              this.newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + NotificationService.this.package_name).timeout(30000).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").referrer("http://www.google.com").get().select("div:containsOwn(Current Version)").next().text();
              if (((String)NotificationService.this.verArr.get(i)).equals(this.newVersion))
              {
                System.out.println("equals");
              }
              else if (this.newVersion.equalsIgnoreCase("Varies with device"))
              {
                this.variesList.add(NotificationService.this.package_name);
                System.out.println("NewUpdateFoundAsyncTask.doInBackground " + this.newVersion);
              }
            }
            catch (Exception paramVarArgs)
            {
              System.out.println("here is exception asynctask  " + paramVarArgs);
            }
            this.checkList.add(NotificationService.this.package_name);
          }
        }
        return this.checkList;
        i += 1;
      }
    }
    
    protected void onPostExecute(ArrayList<String> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      System.out.println("here is the appPkgName " + paramArrayList.size() + " checkList " + this.checkList.size() + " variesList " + this.variesList.size());
      NotificationService.access$902(NotificationService.this, new ArrayList());
      NotificationService.this.preference.setUpdateApps(this.checkList);
      NotificationService.this.preference.setVariesApps(this.variesList);
      int i = 0;
      while (i < this.checkList.size())
      {
        NotificationService.this.updatePackageName.add(this.checkList.get(i));
        i += 1;
      }
      i = 0;
      while (i < this.variesList.size())
      {
        NotificationService.this.updatePackageName.add(this.variesList.get(i));
        i += 1;
      }
      long l1;
      long l2;
      if (NotificationService.this.servicesUtils.checkConnection())
      {
        if ((NotificationService.this.updatePackageName != null) && (NotificationService.this.updatePackageName.size() > 0))
        {
          NotificationService.access$1202(NotificationService.this, NotificationService.this.getAppName(NotificationService.this.updatePackageName));
          NotificationService.access$1402(NotificationService.this, NotificationService.this.updatePackageName.size() - 1);
        }
        l1 = NotificationService.this.preference.getLastNotificationTime();
        l2 = System.currentTimeMillis() - l1;
        NotificationService.access$1502(NotificationService.this, NotificationService.this.preference.getRadioSelected());
        System.out.println("NotificationService M value " + NotificationService.this.valRadio);
        if ((NotificationService.this.valRadio == 0) && ((l2 >= NotificationService.this.preference.getServiceTime()) && ((l1 == 0L) || (NotificationService.this.firstappName == null) || (NotificationService.this.appSize == 0) || (NotificationService.this.appSize <= 0)))) {}
      }
      try
      {
        NotificationService.this.addNotification(NotificationService.this.firstappName + "+" + NotificationService.this.appSize + " " + NotificationService.this.getString(2131165267), NotificationService.this.getAppIcon(NotificationService.this.updatePackageName), NotificationService.this.getApplicationContext());
        NotificationService.this.preference.setLastNotificationTime(System.currentTimeMillis());
        System.out.println("NotificationService Meenu its working if . " + NotificationService.this.preference.getServiceTime() + "radio " + NotificationService.this.valRadio);
        return;
        System.out.println("NotificationService Meenu its working else.");
        return;
        if (NotificationService.this.valRadio == 1)
        {
          if (l2 >= NotificationService.this.preference.getServiceTime())
          {
            if ((l1 != 0L) && (NotificationService.this.firstappName != null) && (NotificationService.this.appSize != 0) && (NotificationService.this.appSize > 0)) {
              System.out.println("NotificationService NewUpdateVersion.onPostExecute ");
            }
            try
            {
              NotificationService.this.addNotification(NotificationService.this.firstappName + "+" + NotificationService.this.appSize + " " + NotificationService.this.getString(2131165267), NotificationService.this.getAppIcon(NotificationService.this.updatePackageName), NotificationService.this.getApplicationContext());
              NotificationService.this.preference.setLastNotificationTime(System.currentTimeMillis());
              System.out.println("NotificationService Meenu its working if . " + NotificationService.this.preference.getServiceTime() + "radio " + NotificationService.this.valRadio);
              return;
            }
            catch (Exception paramArrayList)
            {
              for (;;)
              {
                System.out.println("NotificationService Meenu its working " + paramArrayList.getMessage());
              }
            }
          }
          System.out.println("NotificationService Meenu its working else.");
          return;
        }
        if ((NotificationService.this.valRadio != 2) || ((l2 < NotificationService.this.preference.getServiceTime()) || ((l1 != 0L) && (NotificationService.this.firstappName != null) && (NotificationService.this.appSize != 0) && (NotificationService.this.appSize > 0)))) {}
        try
        {
          NotificationService.this.addNotification(NotificationService.this.firstappName + "+" + NotificationService.this.appSize + " " + NotificationService.this.getString(2131165267), NotificationService.this.getAppIcon(NotificationService.this.updatePackageName), NotificationService.this.getApplicationContext());
          NotificationService.this.preference.setLastNotificationTime(System.currentTimeMillis());
          System.out.println("NotificationService Meenu its working if . " + NotificationService.this.preference.getServiceTime() + "radio " + NotificationService.this.valRadio);
          return;
          System.out.println("NotificationService Meenu its working else.");
          return;
          if ((NotificationService.this.valRadio != 3) || ((l2 < NotificationService.this.preference.getServiceTime()) || ((l1 != 0L) && (NotificationService.this.firstappName != null) && (NotificationService.this.appSize != 0) && (NotificationService.this.appSize > 0)))) {}
          try
          {
            NotificationService.this.addNotification(NotificationService.this.firstappName + "+" + NotificationService.this.appSize + " " + NotificationService.this.getString(2131165267), NotificationService.this.getAppIcon(NotificationService.this.updatePackageName), NotificationService.this.getApplicationContext());
            NotificationService.this.preference.setLastNotificationTime(System.currentTimeMillis());
            System.out.println("NotificationService Meenu its working if . " + NotificationService.this.preference.getServiceTime() + "radio " + NotificationService.this.valRadio);
            return;
            System.out.println("NotificationService Meenu its working else.");
            return;
            if ((NotificationService.this.valRadio != 4) || ((l2 < NotificationService.this.preference.getServiceTime()) || (l1 == 0L) || ((NotificationService.this.firstappName != null) && (NotificationService.this.appSize != 0) && (NotificationService.this.appSize > 0)))) {}
            try
            {
              NotificationService.this.addNotification(NotificationService.this.firstappName + "+" + NotificationService.this.appSize + " " + NotificationService.this.getString(2131165267), NotificationService.this.getAppIcon(NotificationService.this.updatePackageName), NotificationService.this.getApplicationContext());
              NotificationService.this.preference.setLastNotificationTime(System.currentTimeMillis());
              System.out.println("NotificationService Meenu its working if . " + NotificationService.this.preference.getServiceTime() + "radio " + NotificationService.this.valRadio);
              return;
              System.out.println("NotificationService Meenu its working else.");
              return;
            }
            catch (Exception paramArrayList)
            {
              for (;;) {}
            }
          }
          catch (Exception paramArrayList)
          {
            for (;;) {}
          }
        }
        catch (Exception paramArrayList)
        {
          for (;;) {}
        }
      }
      catch (Exception paramArrayList)
      {
        for (;;) {}
      }
    }
  }
  
  private class TimeForNotificaton
    extends TimerTask
  {
    private Handler mTimerHandler = new Handler();
    
    private TimeForNotificaton() {}
    
    public void run()
    {
      this.mTimerHandler.post(new Runnable()
      {
        public void run()
        {
          NotificationService.access$702(NotificationService.this, new ArrayList());
          NotificationService.access$102(NotificationService.this, new ArrayList());
          NotificationService.access$802(NotificationService.this, new ArrayList());
          NotificationService.access$502(NotificationService.this, new ArrayList());
          NotificationService.access$1702(NotificationService.this, new ArrayList());
          NotificationService.access$1802(NotificationService.this, new ArrayList());
          NotificationService.access$1902(NotificationService.this, new ArrayList());
          NotificationService.access$2002(NotificationService.this, NotificationService.this.getInstalledApps());
          System.out.println("NotificationService size" + NotificationService.this.apps.size());
          try
          {
            new NotificationService.NewUpdateVersion(NotificationService.this, null).execute(new Void[0]);
            return;
          }
          catch (Exception localException)
          {
            System.out.println("NotificationService Exception " + localException.getMessage());
          }
        }
      });
    }
  }
}
