package com.mindefy.phoneaddiction.mobilepe.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mindefy.phoneaddiction.mobilepe.Util.UsageStat;
import com.mindefy.phoneaddiction.mobilepe.Util.Util;
import com.mindefy.phoneaddiction.mobilepe.pojo.AllAppInfoPojo;
import com.mindefy.phoneaddiction.mobilepe.pojo.AppInfo;
import com.rvalerio.fgchecker.AppChecker;
import com.rvalerio.fgchecker.AppChecker.Listener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FloatingViewService
  extends Service
{
  private View mFloatingView;
  private WindowManager mWindowManager;
  String previousPackage = null;
  long usageTime;
  
  public FloatingViewService() {}
  
  public String convertToMinute(long paramLong)
  {
    return String.format("%02d:%02d:%02d", new Object[] { Long.valueOf(TimeUnit.MILLISECONDS.toHours(paramLong)), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(paramLong) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(paramLong))), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(paramLong) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(paramLong))) });
  }
  
  public Long getAppInfo(String paramString)
    throws PackageManager.NameNotFoundException
  {
    Iterator localIterator = UsageStat.getUsageStatsList(getApplicationContext()).getAppInfoList().iterator();
    long l = 0L;
    while (localIterator.hasNext())
    {
      AppInfo localAppInfo = (AppInfo)localIterator.next();
      if (localAppInfo.getPname().equalsIgnoreCase(paramString)) {
        l = localAppInfo.getTotalAppForgroundTime();
      }
    }
    return Long.valueOf(l);
  }
  
  public Map<String, AppInfo> getAppInfo()
    throws PackageManager.NameNotFoundException
  {
    PackageManager localPackageManager = getPackageManager();
    Object localObject = localPackageManager.getInstalledPackages(0);
    HashMap localHashMap = new HashMap();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      AppInfo localAppInfo = new AppInfo();
      if (((localPackageInfo.applicationInfo.flags & 0x80) != 0) || ((localPackageInfo.applicationInfo.flags & 0x1) == 0))
      {
        String str = localPackageInfo.packageName;
        localAppInfo.setAppRunTime(Util.convertMilliToTime(0L));
        localAppInfo.setTotalAppForgroundTime(0L);
        Drawable localDrawable = localPackageInfo.applicationInfo.loadIcon(localPackageManager);
        localAppInfo.setPname(str);
        localAppInfo.setAppname((String)localPackageInfo.applicationInfo.loadLabel(localPackageManager));
        localAppInfo.setIcon(localDrawable);
        localHashMap.put(str, localAppInfo);
      }
    }
    return localHashMap;
  }
  
  public String getLauncherPackage()
  {
    PackageManager localPackageManager = getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    return localPackageManager.resolveActivity(localIntent, 65536).activityInfo.packageName;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  @RequiresApi(api=23)
  public void onCreate()
  {
    super.onCreate();
    this.mFloatingView = LayoutInflater.from(this).inflate(2131427401, null);
    final WindowManager.LayoutParams localLayoutParams;
    if (Build.VERSION.SDK_INT >= 26) {
      localLayoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 8, -3);
    } else {
      localLayoutParams = new WindowManager.LayoutParams(-2, -2, 2002, 8, -3);
    }
    localLayoutParams.gravity = 8388659;
    localLayoutParams.x = 0;
    localLayoutParams.y = 130;
    this.mWindowManager = ((WindowManager)getSystemService("window"));
    if (this.mWindowManager != null) {
      if (Build.VERSION.SDK_INT >= 23)
      {
        if (Settings.canDrawOverlays(getApplicationContext())) {
          this.mWindowManager.addView(this.mFloatingView, localLayoutParams);
        }
      }
      else {
        this.mWindowManager.addView(this.mFloatingView, localLayoutParams);
      }
    }
    final Gson localGson = new Gson();
    final SharedPreferences localSharedPreferences = getSharedPreferences("mobilePePreference", 0);
    final TextView localTextView = (TextView)this.mFloatingView.findViewById(2131296635);
    Object localObject = (TextView)this.mFloatingView.findViewById(2131296305);
    localObject = (GradientDrawable)localTextView.getBackground();
    final String str = localGson.toJson(new ArrayList());
    final HashMap localHashMap = new HashMap();
    new AppChecker().whenOther(new AppChecker.Listener()
    {
      public void onForeground(String paramAnonymousString)
      {
        try
        {
          localHashMap.clear();
          Object localObject2 = (ArrayList)localGson.fromJson(localSharedPreferences.getString("appInfoListJson", str), new TypeToken() {}.getType());
          Object localObject1 = (ArrayList)localGson.fromJson(localSharedPreferences.getString("disabledAppsJson", str), new TypeToken() {}.getType());
          localObject2 = ((ArrayList)localObject2).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            String str = (String)((Iterator)localObject2).next();
            localHashMap.put(str, str);
          }
          localObject1 = ((ArrayList)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            localHashMap.put(localObject2, localObject2);
          }
          if (!localHashMap.containsKey(paramAnonymousString))
          {
            if (!paramAnonymousString.equalsIgnoreCase(FloatingViewService.this.previousPackage))
            {
              FloatingViewService.this.previousPackage = paramAnonymousString;
              long l1 = Util.getAppUsageTimeLimit(FloatingViewService.this.getApplicationContext(), paramAnonymousString);
              long l2 = 30L * l1 / 100L;
              if ((!paramAnonymousString.equalsIgnoreCase(Util.getLauncherPackage(FloatingViewService.this.getApplicationContext()))) && (!paramAnonymousString.equalsIgnoreCase("com.android.systemui")) && (!paramAnonymousString.equalsIgnoreCase("com.google.android.packageinstaller")))
              {
                FloatingViewService.this.usageTime = FloatingViewService.this.getAppInfo(paramAnonymousString).longValue();
                localObject1 = FloatingViewService.this.mFloatingView;
                int i = 0;
                ((View)localObject1).setVisibility(0);
                if ((FloatingViewService.this.usageTime >= l1 - l2) && (FloatingViewService.this.usageTime < l1))
                {
                  this.val$timerBg.setColor(ContextCompat.getColor(FloatingViewService.this.getApplicationContext(), 2131099697));
                }
                else if (FloatingViewService.this.usageTime >= l1)
                {
                  if (this.val$preReachAppOpenRate.containsKey(paramAnonymousString)) {
                    i = ((Integer)this.val$preReachAppOpenRate.get(paramAnonymousString)).intValue();
                  }
                  this.val$preReachAppOpenRate.put(paramAnonymousString, Integer.valueOf(i + 1));
                  if (i % 4 == 0) {
                    FloatingViewService.this.openSystemDialog(FloatingViewService.this.getApplicationContext());
                  }
                  this.val$timerBg.setColor(ContextCompat.getColor(FloatingViewService.this.getApplicationContext(), 2131099711));
                }
                else
                {
                  if (this.val$preReachAppOpenRate.containsKey(paramAnonymousString)) {
                    this.val$preReachAppOpenRate.remove(paramAnonymousString);
                  }
                  this.val$timerBg.setColor(ContextCompat.getColor(FloatingViewService.this.getApplicationContext(), 2131099692));
                }
              }
              else
              {
                FloatingViewService.this.mFloatingView.setVisibility(8);
              }
            }
            localTextView.setText(FloatingViewService.this.convertToMinute(FloatingViewService.this.usageTime));
            FloatingViewService.this.usageTime += 1000L;
            return;
          }
          FloatingViewService.this.mFloatingView.setVisibility(8);
          return;
        }
        catch (PackageManager.NameNotFoundException paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
      }
    }).timeout(1000).start(this);
    this.mFloatingView.findViewById(2131296352).setOnTouchListener(new View.OnTouchListener()
    {
      private float initialTouchX;
      private float initialTouchY;
      private int initialX;
      private int initialY;
      
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        default: 
          return false;
        case 2: 
          localLayoutParams.x = (this.initialX + (int)(paramAnonymousMotionEvent.getRawX() - this.initialTouchX));
          localLayoutParams.y = (this.initialY + (int)(paramAnonymousMotionEvent.getRawY() - this.initialTouchY));
          FloatingViewService.this.mWindowManager.updateViewLayout(FloatingViewService.this.mFloatingView, localLayoutParams);
          return true;
        case 1: 
          paramAnonymousMotionEvent.getRawX();
          float f = this.initialTouchX;
          paramAnonymousMotionEvent.getRawY();
          f = this.initialTouchY;
          return true;
        }
        this.initialX = localLayoutParams.x;
        this.initialY = localLayoutParams.y;
        this.initialTouchX = paramAnonymousMotionEvent.getRawX();
        this.initialTouchY = paramAnonymousMotionEvent.getRawY();
        return true;
      }
    });
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    if (this.mFloatingView != null) {
      this.mWindowManager.removeView(this.mFloatingView);
    }
  }
  
  public void openSystemDialog(Context paramContext)
  {
    final WindowManager localWindowManager = (WindowManager)paramContext.getApplicationContext().getSystemService("window");
    WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
    localLayoutParams.gravity = 17;
    localLayoutParams.type = 2003;
    localLayoutParams.width = -2;
    localLayoutParams.height = -2;
    localLayoutParams.alpha = 1.0F;
    localLayoutParams.packageName = paramContext.getPackageName();
    localLayoutParams.buttonBrightness = 1.0F;
    localLayoutParams.windowAnimations = 16973826;
    final View localView = View.inflate(paramContext.getApplicationContext(), 2131427434, null);
    Button localButton1 = (Button)localView.findViewById(2131296328);
    Button localButton2 = (Button)localView.findViewById(2131296326);
    TextView localTextView1 = (TextView)localView.findViewById(2131296612);
    TextView localTextView2 = (TextView)localView.findViewById(2131296305);
    TextView localTextView3 = (TextView)localView.findViewById(2131296562);
    paramContext = Arrays.asList(paramContext.getResources().getStringArray(2130903045));
    paramContext = (String)paramContext.get(new Random().nextInt(paramContext.size()));
    localTextView1.setText("Usage Time Alert");
    localTextView2.setText(paramContext);
    localTextView3.setText("Time to Take a break!");
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (localWindowManager != null) {
          localWindowManager.removeView(localView);
        }
      }
    });
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (localWindowManager != null) {
          localWindowManager.removeView(localView);
        }
      }
    });
    if (localWindowManager != null) {
      localWindowManager.addView(localView, localLayoutParams);
    }
  }
}
