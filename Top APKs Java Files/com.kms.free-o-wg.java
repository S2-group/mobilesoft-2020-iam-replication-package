package o;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Process;
import android.text.TextUtils;
import com.kaspersky.components.accessibility.AccessibilityHandlerType;
import com.kavsdk.antivirus.Antivirus;
import com.kavsdk.antivirus.AntivirusInstance;
import com.kavsdk.antivirus.Scanner;
import com.kavsdk.antivirus.ScannerEventListener;
import com.kavsdk.antivirus.ThreatInfo;
import com.kavsdk.antivirus.ThreatType;
import com.kavsdk.sdkstatus.ComponentStatus;
import com.kavsdk.shared.FileFormatRecognizer;
import com.kavsdk.taskreputation.OverlapActivityListener;
import com.kavsdk.taskreputation.Verdict;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public final class wg
  implements ScannerEventListener
{
  private static int 五難 = 237;
  private static final byte[] 論脈有陰陽之法 = { 24, -14, 15, 36, -13, 10, -14, 3, 6, 5, 54, -69, 14, -15, 0, 11, -5, 7, -12, 69, -39, 2, 18, -6, 2, -26, 30, -2, 0, -2, -14, 0, -12, 12, 14, -15, 0, 11, -5, 7, -12, -15, 0, 1, -1, -3, -13, 10, -14, 3, 6, 5, 54, -66, 11, -13, 5, 4, -10, 0, 10, -6, 1, 64, -25, 2, -15, -11, 11, 19, -18, 8, -8, -20, 3, 69, -80, 15, -2, -8, 10, -6, 2, 23, -19, -12, 8, 69, -73, -10, 83, -78, -1, -5, 84, -83, 3, 11, 2, -6, 3, -3, 4, 1, -13, 10, -14, 3, 6, 5, 42, -45, 2, -15, 21, -22, 2, 18, -6, 2, 6, -20, -1, 19, -19, 1 };
  private static final String 難經本義 = wg.class.getSimpleName();
  private final String 一難;
  private Activity 三難;
  private 難經本義 二難;
  private final WeakReference<Context> 僅輸入原文;
  private kb 四難 = new wi(this);
  private wf.難經本義 論太過不及等反常脈象 = new wh(this);
  private Set<String> 論寸口脈與經經脈榮衛度數;
  private boolean 論尺寸為脈之大要會;
  private final WeakHashMap<Activity, WeakReference<OverlapActivityListener>> 難經本義卷上;
  
  public wg(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext();
    this.一難 = paramContext.getPackageName();
    this.僅輸入原文 = new WeakReference(paramContext);
    this.難經本義卷上 = new WeakHashMap();
    難經本義();
  }
  
  private Context 僅輸入原文()
  {
    return (Context)this.僅輸入原文.get();
  }
  
  private Verdict 僅輸入原文(String paramString)
  {
    if (this.一難.equals(paramString)) {
      return Verdict.Trusted;
    }
    if (this.論寸口脈與經經脈榮衛度數.contains(paramString)) {
      return Verdict.System;
    }
    paramString = 難經本義卷上(paramString);
    if (paramString.isEmpty()) {
      return Verdict.Unknown;
    }
    this.二難 = new 難經本義(null);
    AntivirusInstance.getInstance().createScanner().scanFile(paramString, 1026, 2, this, true);
    if (難經本義.難經本義(this.二難)) {
      return Verdict.Untrusted;
    }
    if (難經本義.僅輸入原文(this.二難)) {
      return Verdict.Trusted;
    }
    return Verdict.Unknown;
  }
  
  private void 僅輸入原文(Activity paramActivity)
  {
    Object localObject = 僅輸入原文();
    if (localObject != null) {
      try
      {
        if ((Build.VERSION.SDK_INT <= 19) && (((Context)localObject).checkCallingOrSelfPermission(難經本義(58, 論脈有陰陽之法[43], 論脈有陰陽之法[5])) != 0))
        {
          tw.一難().四難().僅輸入原文(ComponentStatus.InsufficientPermission);
          return;
        }
        localObject = mI.難經本義卷上((Context)localObject);
        if ((localObject != null) && (!this.一難.equals(((mI.難經本義)localObject).難經本義)))
        {
          paramActivity = (OverlapActivityListener)((WeakReference)this.難經本義卷上.get(paramActivity)).get();
          if (paramActivity != null) {
            paramActivity.onOverlapActivity(((mI.難經本義)localObject).難經本義, ((mI.難經本義)localObject).僅輸入原文);
          }
        }
        tw.一難().四難().僅輸入原文(ComponentStatus.OK);
        return;
      }
      catch (SecurityException paramActivity)
      {
        tw.一難().四難().僅輸入原文(ComponentStatus.InsufficientPermission);
      }
    }
  }
  
  @TargetApi(21)
  public static boolean 僅輸入原文(Context paramContext)
  {
    if (Build.VERSION.SDK_INT > 19) {
      return ((AppOpsManager)paramContext.getSystemService(難經本義(論脈有陰陽之法[63] - 1, 論脈有陰陽之法[43], 五難 & 0x30))).checkOp(難經本義(論脈有陰陽之法[14], 論脈有陰陽之法[43], 論脈有陰陽之法[2]), Process.myUid(), paramContext.getPackageName()) == 0;
    }
    return false;
  }
  
  private static String 難經本義(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt2 * 13 + 84;
    byte[] arrayOfByte1 = 論脈有陰陽之法;
    paramInt2 = 104 - paramInt1;
    int j = 0;
    int m = 0;
    paramInt1 = 38 - paramInt3;
    byte[] arrayOfByte2 = new byte[paramInt1];
    int k = paramInt1 - 1;
    paramInt1 = paramInt2;
    paramInt3 = i;
    if (arrayOfByte1 == null)
    {
      paramInt3 = k;
      paramInt1 = paramInt2;
      paramInt2 = m;
    }
    for (;;)
    {
      paramInt3 = i + -paramInt3;
      paramInt1 += 1;
      j = paramInt2;
      arrayOfByte2[j] = ((byte)paramInt3);
      if (j == k) {
        return new String(arrayOfByte2, 0).intern();
      }
      paramInt2 = j + 1;
      j = arrayOfByte1[paramInt1];
      i = paramInt3;
      paramInt3 = j;
    }
  }
  
  private void 難經本義()
  {
    this.論寸口脈與經經脈榮衛度數 = new HashSet();
    Object localObject = 僅輸入原文();
    if (localObject != null)
    {
      localObject = ((Context)localObject).getPackageManager().getInstalledPackages(0).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) != 0) {
          this.論寸口脈與經經脈榮衛度數.add(localPackageInfo.packageName);
        }
      }
    }
  }
  
  public static void 難經本義(Context paramContext)
  {
    if (Build.VERSION.SDK_INT > 19)
    {
      Intent localIntent = new Intent(難經本義(五難 & 0x176, 論脈有陰陽之法[43], 論脈有陰陽之法[14]));
      try
      {
        paramContext.startActivity(localIntent);
        return;
      }
      catch (ActivityNotFoundException paramContext) {}
    }
  }
  
  private String 難經本義卷上(String paramString)
  {
    Object localObject = 僅輸入原文();
    if (localObject != null)
    {
      localObject = ((Context)localObject).getPackageManager();
      try
      {
        paramString = ((PackageManager)localObject).getPackageInfo(paramString, 0);
        if (paramString != null)
        {
          paramString = paramString.applicationInfo.sourceDir;
          return paramString;
        }
      }
      catch (PackageManager.NameNotFoundException paramString) {}
    }
    return "";
  }
  
  public int onScanEvent(int paramInt1, int paramInt2, ThreatInfo paramThreatInfo, ThreatType paramThreatType)
  {
    if (paramInt1 == 1)
    {
      難經本義.難經本義(this.二難, true);
    }
    else if ((paramInt1 == 2) && ((paramThreatInfo instanceof tg)))
    {
      paramThreatInfo = (tg)paramThreatInfo;
      if ((paramThreatInfo.難經本義()) && (FileFormatRecognizer.isApk(paramThreatInfo.getObjectName()))) {
        難經本義.僅輸入原文(this.二難, true);
      }
    }
    return 0;
  }
  
  public Verdict 難經本義(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException(難經本義(論脈有陰陽之法[26] + 1, 論脈有陰陽之法[14], 論脈有陰陽之法[8]));
    }
    return 僅輸入原文(paramString.toLowerCase());
  }
  
  public void 難經本義(Activity paramActivity)
  {
    if (this.難經本義卷上.containsKey(paramActivity))
    {
      wf.難經本義(paramActivity);
      this.難經本義卷上.remove(paramActivity);
      if (this.難經本義卷上.isEmpty()) {
        tw.一難().四難().僅輸入原文(ComponentStatus.Off);
      }
    }
  }
  
  public boolean 難經本義(Activity paramActivity, OverlapActivityListener paramOverlapActivityListener)
  {
    if (!this.難經本義卷上.containsKey(paramActivity)) {
      try
      {
        wf.難經本義(paramActivity, this.論太過不及等反常脈象);
        if (!this.論尺寸為脈之大要會)
        {
          mI.僅輸入原文((Context)this.僅輸入原文.get());
          kc.難經本義((Context)this.僅輸入原文.get()).難經本義(AccessibilityHandlerType.Task_Reputation, this.四難);
          this.論尺寸為脈之大要會 = true;
        }
        this.難經本義卷上.put(paramActivity, new WeakReference(paramOverlapActivityListener));
        tw.一難().四難().僅輸入原文(ComponentStatus.OK);
        return true;
      }
      catch (NoSuchMethodException paramActivity) {}
    }
    return false;
  }
  
  static class 難經本義
  {
    private boolean 僅輸入原文;
    private boolean 難經本義;
    
    private 難經本義() {}
  }
}
