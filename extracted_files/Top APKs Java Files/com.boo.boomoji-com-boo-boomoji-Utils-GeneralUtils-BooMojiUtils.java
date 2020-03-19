package com.boo.boomoji.Utils.GeneralUtils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import com.boo.boomoji.Controller.BooMojiApplication;
import com.boo.boomoji.Controller.LocalDataController.Constant;
import com.boo.boomoji.Management.DipperHelp.DipperStatisticsHelper;
import com.boo.boomoji.Management.FabricManage.FabricManagement;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BooMojiUtils
{
  private static final int PERMISSON_REQUESTCODE = 0;
  private static final String TAG = "BooMojiUtils";
  
  public BooMojiUtils() {}
  
  public static void checkPermissions(Activity paramActivity, String... paramVarArgs)
  {
    paramVarArgs = findDeniedPermissions(paramVarArgs, paramActivity);
    if ((paramVarArgs != null) && (paramVarArgs.size() > 0)) {
      ActivityCompat.requestPermissions(paramActivity, (String[])paramVarArgs.toArray(new String[paramVarArgs.size()]), 0);
    }
  }
  
  public static void copyFile(Context paramContext, String paramString1, String paramString2)
  {
    long l = System.currentTimeMillis();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Environment.getExternalStorageDirectory());
    localStringBuilder.append(paramContext.getResources().getString(2131624110));
    localStringBuilder.append(l);
    localStringBuilder.append(paramString2);
    paramString2 = localStringBuilder.toString();
    DevUtil.copyfile(new File(paramString1), new File(paramString2), Boolean.valueOf(true));
    DevUtil.scanFile(paramContext, paramString2);
    DevUtil.showShortInfo(paramContext, paramContext.getResources().getString(2131624184));
  }
  
  public static void emailUs(Context paramContext)
  {
    Object localObject5 = Build.MODEL;
    Object localObject4 = Build.VERSION.RELEASE;
    int i = NetworkUtils.getNetworkType(paramContext);
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("message/rfc822");
    String str = paramContext.getResources().getString(2131624101);
    Object localObject1 = paramContext.getResources().getString(2131624397);
    try
    {
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append((String)localObject5);
      ((StringBuilder)localObject2).append(" - ");
      ((StringBuilder)localObject2).append(paramContext.getResources().getString(2131624110));
      ((StringBuilder)localObject2).append(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName);
      localObject2 = ((StringBuilder)localObject2).toString();
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException1)
    {
      localNameNotFoundException1.printStackTrace();
    }
    Object localObject3 = paramContext.getResources().getString(2131624098);
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append((String)localObject3);
    localStringBuilder2.append("\nDevice:");
    localStringBuilder2.append((String)localObject5);
    localObject3 = localStringBuilder2.toString();
    localObject5 = new StringBuilder();
    ((StringBuilder)localObject5).append((String)localObject3);
    ((StringBuilder)localObject5).append("\nAndroid:");
    ((StringBuilder)localObject5).append((String)localObject4);
    localObject3 = ((StringBuilder)localObject5).toString();
    localObject4 = new StringBuilder();
    ((StringBuilder)localObject4).append((String)localObject3);
    ((StringBuilder)localObject4).append("\nLocale:");
    ((StringBuilder)localObject4).append("");
    localObject3 = ((StringBuilder)localObject4).toString();
    try
    {
      localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append((String)localObject3);
      ((StringBuilder)localObject4).append("\nVersion:");
      ((StringBuilder)localObject4).append(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName);
      localObject4 = ((StringBuilder)localObject4).toString();
      localObject3 = localObject4;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException2)
    {
      localNameNotFoundException2.printStackTrace();
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append((String)localObject3);
    localStringBuilder1.append("\nNetwork:");
    localStringBuilder1.append(i);
    localObject3 = localStringBuilder1.toString();
    localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append((String)localObject3);
    localStringBuilder1.append("\nCurrent Power");
    localStringBuilder1.append("");
    localObject3 = localStringBuilder1.toString();
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { str });
    localIntent.putExtra("android.intent.extra.SUBJECT", (String)localObject1);
    localIntent.putExtra("android.intent.extra.TEXT", (String)localObject3);
    localIntent.setFlags(268435456);
    paramContext.startActivity(Intent.createChooser(localIntent, "Welcome to Mail"));
  }
  
  public static List<String> findDeniedPermissions(String[] paramArrayOfString, Activity paramActivity)
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      if ((ContextCompat.checkSelfPermission(paramActivity, str) != 0) || (ActivityCompat.shouldShowRequestPermissionRationale(paramActivity, str))) {
        localArrayList.add(str);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static String getNetworkState(Context paramContext)
  {
    Object localObject1 = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (localObject1 == null) {
      return "";
    }
    paramContext = ((ConnectivityManager)localObject1).getActiveNetworkInfo();
    if ((paramContext != null) && (paramContext.isAvailable()))
    {
      Object localObject2 = ((ConnectivityManager)localObject1).getNetworkInfo(1);
      if (localObject2 != null)
      {
        localObject2 = ((NetworkInfo)localObject2).getState();
        if ((localObject2 != null) && ((localObject2 == NetworkInfo.State.CONNECTED) || (localObject2 == NetworkInfo.State.CONNECTING))) {
          return "WIFI";
        }
      }
      localObject2 = ((ConnectivityManager)localObject1).getNetworkInfo(0);
      if (localObject2 != null)
      {
        localObject1 = ((NetworkInfo)localObject2).getState();
        localObject2 = ((NetworkInfo)localObject2).getSubtypeName();
        if ((localObject1 != null) && ((localObject1 == NetworkInfo.State.CONNECTED) || (localObject1 == NetworkInfo.State.CONNECTING)))
        {
          switch (paramContext.getSubtype())
          {
          default: 
            if ((((String)localObject2).equalsIgnoreCase("TD-SCDMA")) || (((String)localObject2).equalsIgnoreCase("WCDMA"))) {
              break label237;
            }
            if (!((String)localObject2).equalsIgnoreCase("CDMA2000")) {
              break;
            }
            break;
          case 13: 
            return "4G";
          case 3: 
          case 5: 
          case 6: 
          case 8: 
          case 9: 
          case 10: 
          case 12: 
          case 14: 
          case 15: 
            return "3G";
          case 1: 
          case 2: 
          case 4: 
          case 7: 
          case 11: 
            return "2G";
          }
          return "WIFI";
          label237:
          return "3G";
        }
      }
      return "";
    }
    return "";
  }
  
  public static boolean isBackground(Context paramContext)
  {
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (localRunningAppProcessInfo.processName.equals(paramContext.getPackageName()))
      {
        if (localRunningAppProcessInfo.importance == 400)
        {
          Log.i("后台", localRunningAppProcessInfo.processName);
          return true;
        }
        Log.i("前台", localRunningAppProcessInfo.processName);
        return false;
      }
    }
    return false;
  }
  
  public static void loadCircleImage(SimpleDraweeView paramSimpleDraweeView, String paramString)
  {
    paramString = Uri.parse(paramString);
    paramSimpleDraweeView.setController(((PipelineDraweeControllerBuilder)Fresco.newDraweeControllerBuilder().setUri(paramString).setAutoPlayAnimations(true)).build());
  }
  
  public static void loadRefreshLoading(Context paramContext, SimpleDraweeView paramSimpleDraweeView, int paramInt)
  {
    paramContext = paramContext.getPackageName();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("res://");
    localStringBuilder.append(paramContext);
    localStringBuilder.append("/");
    localStringBuilder.append(paramInt);
    paramContext = ImageRequestBuilder.newBuilderWithSource(Uri.parse(localStringBuilder.toString())).build();
    paramSimpleDraweeView.setController(((PipelineDraweeControllerBuilder)((PipelineDraweeControllerBuilder)Fresco.newDraweeControllerBuilder().setImageRequest(paramContext)).setAutoPlayAnimations(true)).build());
  }
  
  public static List<String> scanLocalInstallAppList(PackageManager paramPackageManager)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    try
    {
      List localList = paramPackageManager.getInstalledPackages(0);
      while (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        String str = localPackageInfo.packageName;
        if (localPackageInfo.applicationInfo.loadIcon(paramPackageManager) != null) {
          localArrayList.add(str);
        }
        i += 1;
      }
    }
    catch (Exception paramPackageManager)
    {
      for (;;) {}
    }
    Log.e(TAG, "===============获取应用包信息失败");
    return localArrayList;
  }
  
  public static void shareFile(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (paramString3.equals(Constant.APP)) {
      FabricManagement.getInstance(paramContext).addFlurryEvent("share_file_from_app", "share_file_from_app", DevUtil.getFileName(paramString2));
    } else if (paramString3.equals(Constant.KEYBOARD)) {
      FabricManagement.getInstance(paramContext).addFlurryEvent("share_file_from_keyboard", "share_file_from_keyboard", DevUtil.getFileName(paramString2));
    }
    paramString3 = new Intent("android.intent.action.SEND");
    if (Build.VERSION.SDK_INT <= 23) {
      paramString2 = Uri.fromFile(new File(paramString2));
    } else {
      paramString2 = FileProvider.getUriForFile(BooMojiApplication.mContext, ProviderUtil.getFileProviderName(paramContext), new File(paramString2));
    }
    paramString3.putExtra("android.intent.extra.STREAM", paramString2);
    paramString3.setType(paramString1);
    paramString3.addFlags(268435456);
    paramContext.startActivity(Intent.createChooser(paramString3, ""));
  }
  
  public static void shareFileAndMsg(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    if (Build.VERSION.SDK_INT <= 23) {
      paramString2 = Uri.fromFile(new File(paramString2));
    } else {
      paramString2 = FileProvider.getUriForFile(BooMojiApplication.mContext, ProviderUtil.getFileProviderName(paramContext), new File(paramString2));
    }
    localIntent.putExtra("android.intent.extra.STREAM", paramString2);
    localIntent.putExtra("android.intent.extra.TEXT", paramString3);
    localIntent.setType(paramString1);
    localIntent.addFlags(268435456);
    paramContext.startActivity(Intent.createChooser(localIntent, ""));
  }
  
  public static void shareLink(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    DipperStatisticsHelper.eventInviteFreinds(paramString5);
    paramString5 = new Intent("android.intent.action.SEND");
    paramString5.setType(paramString1);
    paramString1 = new StringBuilder();
    paramString1.append(paramString4);
    paramString1.append(paramString3);
    paramString5.putExtra("android.intent.extra.TEXT", paramString1.toString());
    paramString5.setFlags(268435456);
    paramContext.startActivity(Intent.createChooser(paramString5, paramString2));
  }
  
  private static void showShareIntent(Context paramContext, String paramString, File paramFile)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    if (Build.VERSION.SDK_INT <= 23) {
      paramFile = Uri.fromFile(paramFile);
    } else {
      paramFile = FileProvider.getUriForFile(BooMojiApplication.mContext, ProviderUtil.getFileProviderName(paramContext), paramFile);
    }
    String str = TAG;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("uri:");
    localStringBuilder.append(paramFile);
    Log.e(str, localStringBuilder.toString());
    localIntent.putExtra("android.intent.extra.STREAM", paramFile);
    localIntent.setType(paramString);
    localIntent.addFlags(268435456);
    paramContext.startActivity(Intent.createChooser(localIntent, ""));
  }
}
