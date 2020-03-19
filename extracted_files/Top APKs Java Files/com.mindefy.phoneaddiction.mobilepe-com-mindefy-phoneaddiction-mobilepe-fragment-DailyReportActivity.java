package com.mindefy.phoneaddiction.mobilepe.fragment;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mindefy.phoneaddiction.mobilepe.Util.UsageStat;
import com.mindefy.phoneaddiction.mobilepe.Util.Util;
import com.mindefy.phoneaddiction.mobilepe.activity.MainActivity;
import com.mindefy.phoneaddiction.mobilepe.adapter.TopUsedAppAdapter;
import com.mindefy.phoneaddiction.mobilepe.pojo.AllAppInfoPojo;
import com.mindefy.phoneaddiction.mobilepe.pojo.AppInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DailyReportActivity
  extends AppCompatActivity
{
  private final int PERMISSION_ALL = 101;
  File imagePath;
  RecyclerView recyclerView;
  TextView screenOnTime;
  TextView totalUnlockCountTV;
  
  public DailyReportActivity() {}
  
  private String SaveImage(Bitmap paramBitmap)
  {
    String str2 = Environment.getExternalStorageDirectory().getAbsolutePath();
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(str2);
    ((StringBuilder)localObject1).append("/YourHour");
    Object localObject2 = new File(((StringBuilder)localObject1).toString());
    ((File)localObject2).mkdirs();
    localObject1 = new Date();
    String str3 = new SimpleDateFormat("ddMMyyyyhhmmss", Locale.getDefault()).format((Date)localObject1);
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("YourHour-");
    ((StringBuilder)localObject1).append(str3);
    ((StringBuilder)localObject1).append(".jpg");
    String str1 = ((StringBuilder)localObject1).toString();
    localObject1 = new File((File)localObject2, str1);
    int i = 1;
    while (((File)localObject1).exists())
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("YourHour-");
      ((StringBuilder)localObject1).append(str3);
      ((StringBuilder)localObject1).append("(");
      ((StringBuilder)localObject1).append(i);
      ((StringBuilder)localObject1).append(").jpg");
      str1 = ((StringBuilder)localObject1).toString();
      localObject1 = new File((File)localObject2, str1);
      i += 1;
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(str2);
    ((StringBuilder)localObject2).append("/YourHour/");
    ((StringBuilder)localObject2).append(str1);
    str1 = ((StringBuilder)localObject2).toString();
    try
    {
      localObject1 = new FileOutputStream((File)localObject1);
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 90, (OutputStream)localObject1);
      ((FileOutputStream)localObject1).flush();
      ((FileOutputStream)localObject1).close();
      return str1;
    }
    catch (Exception paramBitmap)
    {
      paramBitmap.printStackTrace();
    }
    return str1;
  }
  
  private String getBitmapFromView(RelativeLayout paramRelativeLayout)
  {
    Bitmap localBitmap = Bitmap.createScaledBitmap(Bitmap.createBitmap(paramRelativeLayout.getWidth(), paramRelativeLayout.getHeight(), Bitmap.Config.ARGB_8888), paramRelativeLayout.getWidth(), paramRelativeLayout.getHeight(), false);
    Canvas localCanvas = new Canvas(localBitmap);
    paramRelativeLayout.layout(paramRelativeLayout.getLeft(), paramRelativeLayout.getTop(), paramRelativeLayout.getRight(), paramRelativeLayout.getBottom());
    paramRelativeLayout.draw(localCanvas);
    return SaveImage(localBitmap);
  }
  
  private void initRecyclerView(List<AppInfo> paramList)
  {
    this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    this.recyclerView.setAdapter(new TopUsedAppAdapter(paramList));
  }
  
  private void shareImage()
  {
    Uri localUri = Uri.parse(new File(getBitmapFromView((RelativeLayout)findViewById(2131296522))).toString());
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    localIntent.setType("image/*");
    localIntent.setFlags(1);
    localIntent.putExtra("android.intent.extra.TEXT", "\n\nPhone Usage Tracker - No More Addiction, Download YourHour App Today - \nhttps://goo.gl/gatTqP");
    localIntent.putExtra("android.intent.extra.STREAM", localUri);
    startActivityForResult(Intent.createChooser(localIntent, "Shared via YourHour App..."), 1);
  }
  
  public void counterAnimation(int paramInt)
  {
    ValueAnimator localValueAnimator = new ValueAnimator();
    localValueAnimator.setObjectValues(new Object[] { Integer.valueOf(0), Integer.valueOf(paramInt) });
    localValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        DailyReportActivity.this.totalUnlockCountTV.setText(String.valueOf(paramAnonymousValueAnimator.getAnimatedValue()));
      }
    });
    localValueAnimator.setDuration(1000L);
    localValueAnimator.start();
  }
  
  public List<AppInfo> getAppInfo(Context paramContext)
    throws PackageManager.NameNotFoundException
  {
    paramContext = paramContext.getPackageManager();
    Object localObject1 = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = UsageStat.getUsageStatsList(getApplicationContext()).getAppInfoList();
    HashMap localHashMap = new HashMap();
    localObject2 = ((List)localObject2).iterator();
    AppInfo localAppInfo;
    while (((Iterator)localObject2).hasNext())
    {
      localAppInfo = (AppInfo)((Iterator)localObject2).next();
      localHashMap.put(localAppInfo.getPname(), localAppInfo);
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      localAppInfo = new AppInfo();
      String str = ((PackageInfo)localObject2).packageName;
      if ((!str.equalsIgnoreCase(Util.getLauncherPackage(getApplicationContext()))) && (!str.equalsIgnoreCase("com.android.systemui")) && (!str.equalsIgnoreCase("android")) && (!str.equalsIgnoreCase("com.google.android.packageinstaller")) && (localHashMap.containsKey(str)))
      {
        long l = ((AppInfo)localHashMap.get(str)).getTotalAppForgroundTime();
        int i = ((AppInfo)localHashMap.get(str)).getAppLaunchCount();
        localAppInfo.setAppRunTime(Util.convertMilliToTime(l));
        localAppInfo.setTotalAppForgroundTime(l);
        Drawable localDrawable = ((PackageInfo)localObject2).applicationInfo.loadIcon(paramContext);
        localAppInfo.setPname(str);
        localAppInfo.setAppname((String)((PackageInfo)localObject2).applicationInfo.loadLabel(paramContext));
        localAppInfo.setIcon(localDrawable);
        localAppInfo.setAppLaunchCount(i);
        localArrayList.add(localAppInfo);
      }
    }
    return localArrayList;
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
    finish();
    overridePendingTransition(2130771983, 2130771984);
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427382);
    long l = getIntent().getLongExtra("totalUsageTime", 0L);
    this.recyclerView = ((RecyclerView)findViewById(2131296520));
    paramBundle = new StringBuilder();
    paramBundle.append(Environment.getExternalStorageDirectory());
    paramBundle.append("/screenshot.png");
    this.imagePath = new File(paramBundle.toString());
    this.totalUnlockCountTV = ((TextView)findViewById(2131296363));
    this.screenOnTime = ((TextView)findViewById(2131296622));
    int i = Util.getUnlockCount(getApplicationContext(), Util.getCurrentDate());
    int j = (int)UsageStat.getHour(l);
    int k = (int)UsageStat.getMinute(l);
    paramBundle = this.screenOnTime;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(j);
    localStringBuilder.append("h ");
    localStringBuilder.append(k);
    localStringBuilder.append("m");
    paramBundle.setText(localStringBuilder.toString());
    counterAnimation(i);
    ((Button)findViewById(2131296561)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new String[1];
        paramAnonymousView[0] = "android.permission.WRITE_EXTERNAL_STORAGE";
        if (!Util.hasPermissions(DailyReportActivity.this.getApplicationContext(), paramAnonymousView))
        {
          ActivityCompat.requestPermissions(DailyReportActivity.this, paramAnonymousView, 101);
          return;
        }
        DailyReportActivity.this.shareImage();
      }
    });
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    if (paramInt != 101) {
      return;
    }
    if ((paramArrayOfInt.length > 0) && (paramArrayOfInt[0] == 0)) {
      shareImage();
    }
  }
  
  public void onStart()
  {
    super.onStart();
    List localList2;
    try
    {
      List localList1 = getAppInfo(getApplicationContext());
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
      localList2 = null;
    }
    if (localList2 != null)
    {
      Collections.sort(localList2, new Comparator()
      {
        public int compare(AppInfo paramAnonymousAppInfo1, AppInfo paramAnonymousAppInfo2)
        {
          return Long.compare(paramAnonymousAppInfo2.getTotalAppForgroundTime(), paramAnonymousAppInfo1.getTotalAppForgroundTime());
        }
      });
      initRecyclerView(localList2);
    }
  }
}
