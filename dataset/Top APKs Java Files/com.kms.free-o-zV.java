package o;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.util.Pair;
import com.google.android.apps.analytics.easytracking.GA;
import com.google.android.apps.analytics.easytracking.GA.AvScanType;
import com.google.android.apps.analytics.easytracking.GA.AvWhenVirusWasFound;
import com.kavsdk.antivirus.Scanner;
import com.kavsdk.antivirus.ScannerEventListener;
import com.kavsdk.antivirus.ScannerSuspiciousEventListener;
import com.kavsdk.antivirus.SuspiciousThreatType;
import com.kavsdk.antivirus.ThreatInfo;
import com.kavsdk.antivirus.ThreatType;
import com.kavsdk.antivirus.impl.ScannerImpl;
import com.kavsdk.antivirus.impl.ScannerRuntimeException;
import com.kavsdk.shared.SdkUtils;
import com.kms.antivirus.AntivirusEventType;
import com.kms.antivirus.AntivirusScanType;
import com.kms.antivirus.gui.AvScanResultActivity;
import com.kms.kmsshared.KMSApplication;
import com.kms.kmsshared.KMSLog;
import com.kms.kmsshared.Utils;
import com.kms.kmsshared.reports.Reports;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class zV
  implements ScannerEventListener, ScannerSuspiciousEventListener, Ao
{
  private static final byte[] 十八難 = { 36, 98, 70, -68, -11, 3, 64, -60, -1, -5, 70, -70, 3, 15, -12, 18, -1, -16, 12, -5, 2, 65, -62, -1, 12, 0, -25, 27, -50, 22, -1, -10, -3, 12, -5, 2, 79, -86, 16, 0, -2, 8, 70, -74, 3, -2, 1, 4, -4, 8, 72, -83, 13, 4, 70, -64, -14, 1, 81, -1, -16, 12, -12, 14, -10, -4, -11, 3, 64, -60, -1, -5, 70, -70, 3, 15, -12, 18, -1, -16, 12, -5, 2, 65, -58, -4, 9, -8, 17, 3, -12, 32, -18, -7, 6, 3, -16, 77, 4, -77, 72, 13, -4, -62, 57, 13, -4, -62, 57, 13, -4, -77, 75, -11, 3, 64, -60, -1, -5, 70, -70, 3, 15, -12, 18, -1, -16, 12, -5, 2, 65, -55, -2, -2, 8, 7, -16, 16, -18, 13, -11, 3, 64, -60, -1, -5, 70, -70, 3, 15, -12, 18, -1, -16, 12, -5, 2, 65, -65, 16, -1, -7, 11, -5, 3, -11, 3, 64, -60, -1, -5, 70, -70, 3, 15, -12, 18, -1, -16, 12, -5, 2, 65, -69, -4, 10, 12, -52, 36, -15, 3, -12, 43, -36, -9, 4, 16, -18, 18, -4, 4, -12, -43, 2, -5, 12, -4, -6, 17, 70, -82, 17, 3, -12, 1, 6, -4, 8, 46, 27, -68, -16, 18, -8, -5, 85, -83, 12, -3, 9, 70, -12, 14, 17, 3, -12, 31, -16, -18, 13, 65, -74, 17, 3, -12, 1, 10, -12, 71, 13, -4, -62, 57, 13, -4, -77, 72, 13, -4, -60, 55, 13, -4, -60, 58, -11, 3, 64, -60, -1, -5, 70, -70, 3, 15, -12, 18, -1, -16, 12, -5, 2, 65, -61, 12, -19, 8, 12, -4, 4, 2, -1, -11, 4, 3, -67, -5, 7, 0, 16, -7, -26, -7, -2, 8, 2, 69, -83, 6, 80, -70, 3, -14, 85, -83, 13, 0, -9, 84, -64, -14, 1, 5, 4, 7, 3, -18, 12, -5, 2, 79, -72, -4, 9, -8, -1, -5, -3, -9, -9, 7, 26, -34, 13, -7, 39, -39, -7, 10, 12, -18, 6, -2 };
  private static int 論脈有三部四經與切脈而知積聚 = 124;
  private boolean 一難;
  private int 七難;
  private final AtomicBoolean 三難 = new AtomicBoolean(false);
  private int 九難;
  private long 二難;
  private int 五難;
  private String 僅輸入原文;
  private int 八難;
  private int 六難;
  private int 十一難;
  private final CopyOnWriteArrayList<Ad> 十七難 = new CopyOnWriteArrayList();
  private boolean 十三難;
  private Set<String> 十二難 = new HashSet();
  private int 十五難;
  private final AtomicBoolean 十六難 = new AtomicBoolean(false);
  private Ap 十四難;
  private long 十難;
  private ApplicationInfo 四難;
  private long 論一脈十變;
  private PowerManager.WakeLock 論五臟病之內外證;
  private Scanner 論切脈而知死生存亡;
  private int 論別知臟腑之病;
  private int 論四時之脈以胃氣為本;
  private boolean 論太過不及等反常脈象;
  private List<String> 論實實虛虛之誤 = new ArrayList();
  private int 論寸口脈平而死者;
  private boolean 論寸口脈與經經脈榮衛度數;
  private long 論尺寸為脈之大要會;
  private final Set<String> 論損至脈之病證及其治法 = Collections.synchronizedSet(new HashSet());
  private int 論王脈;
  private int 論脈不滿五十動而一止;
  private int 論脈之陰陽虛實;
  private int 論脈有輕重;
  private long 論脈有陰陽之法;
  private String 論色脈皮膚之相應;
  private int 難經本義;
  private boolean 難經本義卷上;
  
  public zV() {}
  
  private ApplicationInfo 一難(String paramString)
  {
    try
    {
      paramString = 論寸口脈與經經脈榮衛度數(paramString);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  private static String[] 二難(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString);
    return (String[])localArrayList.toArray(new String[0]);
  }
  
  private void 五難()
  {
    PackageManager localPackageManager = KMSApplication.難經本義.getPackageManager();
    Iterator localIterator;
    if (!this.十二難.isEmpty())
    {
      localIterator = this.十二難.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        try
        {
          localPackageManager.getPackageInfo(str, 0);
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException1)
        {
          this.論脈之陰陽虛實 -= 1;
          this.六難 += 1;
        }
      }
      this.十二難.clear();
    }
    if (!this.論實實虛虛之誤.isEmpty())
    {
      localIterator = this.論實實虛虛之誤.iterator();
      while (localIterator.hasNext()) {
        try
        {
          if ((localPackageManager.getApplicationInfo((String)localIterator.next(), 0).flags & 0x200000) != 2097152) {
            localIterator.remove();
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException2) {}catch (NoSuchElementException localNoSuchElementException) {}catch (IllegalStateException localIllegalStateException) {}catch (UnsupportedOperationException localUnsupportedOperationException) {}
      }
      this.八難 = this.論實實虛虛之誤.size();
      this.論實實虛虛之誤.clear();
    }
  }
  
  private static List<String> 僅輸入原文(Set<String> paramSet)
  {
    Object localObject = new ArrayList(paramSet.size());
    PackageManager localPackageManager = KMSApplication.難經本義.getPackageManager();
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
    {
      String str = (String)paramSet.next();
      try
      {
        ((List)localObject).add(new Pair(str, Long.valueOf(localPackageManager.getPackageInfo(str, 0).firstInstallTime)));
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    }
    Collections.sort((List)localObject, new zY());
    paramSet = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramSet.add(((Pair)((Iterator)localObject).next()).first);
    }
    return Collections.unmodifiableList(paramSet);
  }
  
  private void 僅輸入原文(ThreatInfo paramThreatInfo, ThreatType paramThreatType)
  {
    String str1 = paramThreatInfo.getPackageName();
    Context localContext = KMSApplication.難經本義;
    if (((Integer)EV.僅輸入原文().二難(2)).intValue() == 3)
    {
      this.論脈不滿五十動而一止 = 3;
    }
    else
    {
      int i = 十八難[15];
      ActivityManager localActivityManager = (ActivityManager)localContext.getSystemService(難經本義(i, i | 0x24, 十八難['']));
      try
      {
        DP.難經本義(localActivityManager, str1);
      }
      catch (Exception localException)
      {
        i = -十八難[28];
        String str2 = 難經本義(i, i | 0x84, 十八難[13]);
        StringBuilder localStringBuilder = new StringBuilder();
        i = 十八難['Ú'];
        KMSLog.僅輸入原文(str2, 難經本義(i, i >>> 1, 十八難['ì']) + str1, localException);
      }
      PackageManager localPackageManager = localContext.getPackageManager();
      if (this.四難 == null) {
        try
        {
          localPackageManager.getApplicationInfo(str1, 0).loadLabel(localPackageManager).toString();
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
      } else {
        this.四難.loadLabel(localNameNotFoundException).toString();
      }
      if ((!Utils.論別知臟腑之病(str1)) || (!論尺寸為脈之大要會(str1)))
      {
        Intent localIntent = new Intent(localContext, Av.class);
        localIntent.addFlags(268435456);
        localIntent.putExtra(難經本義(十八難[38], -十八難['ě'], 十八難[29]), paramThreatInfo.getVirusName());
        localIntent.putExtra(難經本義(十八難[38], 論脈有三部四經與切脈而知積聚 & 0x1EF, 十八難[27]), paramThreatInfo.getFileFullPath());
        localIntent.putExtra(難經本義(十八難[38], 135, -十八難[26]), str1);
        localIntent.putExtra(難經本義(十八難[38], 160, 十八難[29]), paramThreatType);
        localIntent.putExtra(難經本義(十八難[38], 十八難[8], 十八難[29]), 2);
        localIntent.putExtra(難經本義(十八難[38], 260, 十八難['ì'] - 1), 1);
        localContext.startActivity(localIntent);
        二難();
        六難();
      }
    }
    switch (this.論脈不滿五十動而一止)
    {
    default: 
      
    case 1: 
    case 2: 
      this.十二難.add(str1);
      this.論脈之陰陽虛實 += 1;
      if (paramThreatInfo.isApplication())
      {
        this.十四難.難經本義(this);
        二難();
        六難();
        this.十四難.僅輸入原文(this);
        return;
      }
      break;
    case 3: 
      this.論脈之陰陽虛實 += 1;
      Reports.add(4, paramThreatInfo.getFileFullPath());
      return;
    case 4: 
      this.論實實虛虛之誤.add(str1);
    }
  }
  
  private void 六難()
  {
    try
    {
      for (;;)
      {
        boolean bool = 論尺寸為脈之大要會();
        if (!bool) {
          break;
        }
        try
        {
          wait();
        }
        catch (InterruptedException localInterruptedException) {}
      }
      return;
    }
    finally {}
  }
  
  private ApplicationInfo 論寸口脈與經經脈榮衛度數(String paramString)
  {
    return KMSApplication.難經本義.getPackageManager().getApplicationInfo(paramString, 0);
  }
  
  private boolean 論尺寸為脈之大要會(String paramString)
  {
    return (Utils.十難(paramString)) || (Ah.難經本義(paramString));
  }
  
  private ApplicationInfo 論脈之陰陽虛實()
  {
    try
    {
      ApplicationInfo localApplicationInfo = 論寸口脈與經經脈榮衛度數(KMSApplication.難經本義.getPackageName());
      return localApplicationInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new RuntimeException(難經本義(十八難['Ú'] - 1, 296, -十八難['Ŗ']));
    }
  }
  
  private void 論脈有輕重()
  {
    if (this.十六難.compareAndSet(false, true))
    {
      this.論五臟病之內外證 = mJ.難經本義(KMSApplication.難經本義, 難經本義(論脈有三部四經與切脈而知積聚 & 0xAA, 330, 十八難[15]));
      new Thread(new zW(this)).start();
    }
  }
  
  private static String 難經本義(int paramInt1, int paramInt2, int paramInt3)
  {
    int k = paramInt3 + 1;
    paramInt1 = 115 - paramInt1;
    byte[] arrayOfByte1 = 十八難;
    int j = 0;
    int m = 0;
    paramInt3 = paramInt2 + 4;
    byte[] arrayOfByte2 = new byte[k];
    paramInt2 = paramInt1;
    int i = paramInt3;
    if (arrayOfByte1 == null)
    {
      i = k;
      j = paramInt1;
      paramInt2 = paramInt3;
      paramInt1 = m;
      paramInt3 = j;
    }
    for (;;)
    {
      paramInt3 = i + -paramInt3 + 1;
      i = paramInt2;
      paramInt2 = paramInt3;
      j = paramInt1;
      paramInt1 = j + 1;
      arrayOfByte2[j] = ((byte)paramInt2);
      j = i + 1;
      if (paramInt1 == k) {
        return new String(arrayOfByte2, 0).intern();
      }
      paramInt3 = arrayOfByte1[j];
      i = paramInt2;
      paramInt2 = j;
    }
  }
  
  private static zR 難經本義(ThreatInfo paramThreatInfo, String paramString)
  {
    return new zR(paramThreatInfo.getVirusName(), paramThreatInfo.getObjectName(), paramThreatInfo.getFileFullPath(), true, paramString, paramThreatInfo.isDeviceAdminThreat(KMSApplication.十二難()), paramThreatInfo.isCloudCheckFailed());
  }
  
  private void 難經本義(int paramInt)
  {
    Iterator localIterator = this.十七難.iterator();
    while (localIterator.hasNext()) {
      ((Ad)localIterator.next()).難經本義(paramInt);
    }
  }
  
  private void 難經本義(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.論太過不及等反常脈象 = true;
    Object localObject = SdkUtils.getInstalledApplications(KMSApplication.難經本義, 0);
    if (localObject != null)
    {
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if ((((localApplicationInfo.flags & 0x1) != 0) && ((localApplicationInfo.flags & 0x80) == 0)) || (localApplicationInfo.uid == 0) || (localApplicationInfo.sourceDir.startsWith(難經本義(-十八難[3], 290, 十八難[94])))) {
          localIterator.remove();
        }
      }
      if (paramBoolean)
      {
        this.五難 = ((List)localObject).size();
        難經本義(this.五難);
      }
      難經本義((List)localObject, paramInt1, paramInt2);
    }
    else
    {
      if (paramBoolean)
      {
        this.五難 = 0;
        難經本義(this.五難);
      }
      難經本義(null, paramInt1, paramInt2);
    }
    if (paramBoolean)
    {
      五難();
      Reports.add(7, new Serializable[] { this.僅輸入原文, Integer.valueOf(this.論別知臟腑之病), Integer.valueOf(this.論脈有輕重), Integer.valueOf(this.論王脈), Integer.valueOf(this.七難), Integer.valueOf(this.六難), Integer.valueOf(this.論脈之陰陽虛實) });
    }
    localObject = EV.僅輸入原文();
    ((EJ)localObject).難經本義(10, Integer.valueOf(0));
    ((EJ)localObject).y_();
    Bi.一難();
    this.論太過不及等反常脈象 = false;
  }
  
  private void 難經本義(ThreatInfo paramThreatInfo, ThreatType paramThreatType)
  {
    int i = ((Integer)EV.僅輸入原文().二難(2)).intValue();
    if (i == 0)
    {
      this.論脈不滿五十動而一止 = 2;
    }
    else if (i == 2)
    {
      this.論脈不滿五十動而一止 = 1;
    }
    else if (i == 3)
    {
      this.論脈不滿五十動而一止 = 3;
    }
    else if ((i == 1) && (!this.十三難))
    {
      this.論脈不滿五十動而一止 = 1;
      Context localContext = KMSApplication.難經本義;
      Intent localIntent = new Intent(KMSApplication.難經本義, Av.class);
      localIntent.addFlags(268435456);
      localIntent.putExtra(難經本義(十八難[38], -十八難['ě'], 十八難[29]), paramThreatInfo.getVirusName());
      localIntent.putExtra(難經本義(十八難[38], 論脈有三部四經與切脈而知積聚 & 0x1EF, 十八難[27]), paramThreatInfo.getFileFullPath());
      localIntent.putExtra(難經本義(十八難[38], 160, 十八難[29]), paramThreatType);
      localIntent.putExtra(難經本義(十八難[38], 十八難[8], 十八難[29]), 1);
      localIntent.putExtra(難經本義(十八難[38], 260, 十八難['ì'] - 1), 1);
      localContext.startActivity(localIntent);
      二難();
      六難();
    }
    switch (this.論脈不滿五十動而一止)
    {
    default: 
      
    case 2: 
      if (zQ.難經本義(paramThreatInfo, paramThreatType))
      {
        this.七難 += 1;
        return;
      }
      break;
    case 1: 
      if (zQ.難經本義(paramThreatInfo, paramThreatType, true))
      {
        this.六難 += 1;
        return;
      }
      break;
    case 3: 
      this.論脈之陰陽虛實 += 1;
      Reports.add(4, paramThreatInfo.getFileFullPath());
    }
  }
  
  private void 難經本義(String paramString, int paramInt1, int paramInt2, String[] paramArrayOfString)
  {
    int i = -十八難[28];
    KMSLog.難經本義(難經本義(i, i | 0x84, 十八難[13]), String.format(難經本義(十八難[25], -十八難[51], -十八難[26]), new Object[] { paramString, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Arrays.toString(paramArrayOfString) }));
    if (this.論切脈而知死生存亡.isDirectory(paramString))
    {
      paramString = Utils.十一難(paramString);
      if (paramString != null) {
        this.論切脈而知死生存亡.scanFolder(paramString, paramInt1, paramInt2, this, paramArrayOfString, true);
      }
    }
    else
    {
      this.論切脈而知死生存亡.scanFile(paramString, paramInt1, paramInt2, this, true);
    }
    if ((this.論切脈而知死生存亡 instanceof ScannerImpl))
    {
      paramString = ((ScannerImpl)this.論切脈而知死生存亡).難經本義();
      if (paramString != null) {
        GA.一難(paramString);
      }
    }
  }
  
  private void 難經本義(List<ApplicationInfo> paramList, int paramInt1, int paramInt2)
  {
    if (paramList != null)
    {
      this.十五難 = (paramInt1 | 0x4000);
      this.論四時之脈以胃氣為本 = paramInt2;
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        this.四難 = ((ApplicationInfo)paramList.next());
        this.論切脈而知死生存亡.scanInstalledApplication(this.四難, paramInt2, this, this, true, paramInt1);
        if ((this.論切脈而知死生存亡 instanceof ScannerImpl))
        {
          String str = ((ScannerImpl)this.論切脈而知死生存亡).難經本義();
          if (str != null) {
            GA.一難(str);
          }
        }
        this.論寸口脈平而死者 += 1;
        if (this.論寸口脈與經經脈榮衛度數) {
          break;
        }
      }
      return;
    }
    onScanEvent(0, 0, null, ThreatType.None);
  }
  
  private static boolean 難經本義卷上(String paramString)
  {
    return !new File(paramString).isDirectory();
  }
  
  public int onScanEvent(int paramInt1, int paramInt2, ThreatInfo paramThreatInfo, ThreatType paramThreatType)
  {
    long l = SystemClock.elapsedRealtime();
    if (l - this.論脈有陰陽之法 >= 60000L)
    {
      this.論脈有陰陽之法 = l;
      int i = -十八難[28];
      KMSLog.僅輸入原文(難經本義(i, i | 0x84, 十八難[13]), 難經本義(論脈有三部四經與切脈而知積聚 & 0xB0, 197, 十八難['ì']) + String.valueOf(this.論脈有陰陽之法));
    }
    Object localObject1;
    Object localObject2;
    switch (paramInt1)
    {
    default: 
      break;
    case 0: 
      if (!this.論太過不及等反常脈象) {
        this.論寸口脈平而死者 += 1;
      }
      if (this.五難 != 0)
      {
        paramInt1 = this.論寸口脈平而死者 * 100 / this.五難;
        if (paramInt1 - this.九難 >= 1)
        {
          this.九難 = paramInt1;
          paramThreatInfo = this.十七難.iterator();
          while (paramThreatInfo.hasNext()) {
            ((Ad)paramThreatInfo.next()).難經本義卷上(this.論寸口脈平而死者);
          }
        }
      }
      break;
    case 10: 
      this.論王脈 += 1;
      if (DJ.難經本義(paramThreatInfo.getFileFullPath())) {
        Reports.add(0, paramThreatInfo.getObjectName());
      } else {
        Reports.add(0, paramThreatInfo.getFileFullPath());
      }
      break;
    case 3: 
      GA.難經本義(GA.AvWhenVirusWasFound.DuringScanning);
      String str = paramThreatInfo.getPackageName();
      localObject1 = paramThreatInfo;
      localObject2 = localObject1;
      if (DJ.難經本義(str))
      {
        str = Utils.論太過不及等反常脈象(paramThreatInfo.getFileFullPath());
        localObject2 = localObject1;
        if (!DJ.難經本義(str)) {
          localObject2 = 難經本義(paramThreatInfo, str);
        }
      }
      if (((ThreatInfo)localObject2).isApplication()) {
        僅輸入原文((ThreatInfo)localObject2, paramThreatType);
      } else {
        難經本義(paramThreatInfo, paramThreatType);
      }
      break;
    case 2: 
      switch (paramInt2)
      {
      default: 
        break;
      case 9: 
        Utils.五難(KMSApplication.十二難());
        wY.僅輸入原文().難經本義(AntivirusEventType.ScanFailedNotEnoughMemory.newEvent());
      case 2: 
      case 3: 
      case 4: 
      case 6: 
      case 7: 
      case 8: 
        this.論脈之陰陽虛實 += 1;
        break;
      case 0: 
      case 1: 
      case 5: 
        this.論別知臟腑之病 += 1;
      }
      l = SystemClock.elapsedRealtime();
      if (l - this.十難 >= 300L)
      {
        this.十難 = l;
        paramThreatInfo = this.十七難.iterator();
        while (paramThreatInfo.hasNext()) {
          ((Ad)paramThreatInfo.next()).一難(this.論別知臟腑之病);
        }
      }
      break;
    case 5: 
      l = SystemClock.elapsedRealtime();
      if (l - this.論一脈十變 >= 300L)
      {
        this.論一脈十變 = l;
        localObject1 = "";
        if (this.難經本義 == 8)
        {
          paramThreatType = this.四難.processName + 難經本義(-十八難[51], 十八難[29] - 1, 十八難[20]);
        }
        else
        {
          paramThreatType = (ThreatType)localObject1;
          if (paramThreatInfo.getFileFullPath() != null)
          {
            paramThreatType = (ThreatType)localObject1;
            if (!paramThreatInfo.getObjectName().equals(paramThreatInfo.getFileFullPath())) {
              paramThreatType = paramThreatInfo.getFileFullPath() + 難經本義(-十八難[3], 十八難[8], 十八難[25]);
            }
          }
        }
        localObject1 = this.十七難.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Ad)((Iterator)localObject1).next();
          this.論色脈皮膚之相應 = (paramThreatType + paramThreatInfo.getObjectName());
          ((Ad)localObject2).難經本義(this.論色脈皮膚之相應);
        }
      }
      break;
    case 1: 
      localObject2 = paramThreatInfo.getPackageName();
      localObject1 = localObject2;
      if (DJ.難經本義((String)localObject2)) {
        localObject1 = Utils.論太過不及等反常脈象(paramThreatInfo.getFileFullPath());
      }
      if (((!this.論太過不及等反常脈象) && (localObject1 == null)) || (!Utils.論別知臟腑之病((String)localObject1)) || (!論尺寸為脈之大要會((String)localObject1)))
      {
        this.論脈有輕重 += 1;
        Reports.addDetection(paramThreatType, paramThreatInfo.getFileFullPath(), paramThreatInfo.getVirusName());
        KMSApplication.論太過不及等反常脈象();
        Utils.論太過不及等反常脈象();
        localObject2 = this.十七難.iterator();
        while (((Iterator)localObject2).hasNext()) {
          ((Ad)((Iterator)localObject2).next()).難經本義(paramThreatInfo.getFileFullPath(), paramThreatInfo.getVirusName());
        }
        if (!((Boolean)EV.僅輸入原文().二難(1)).booleanValue()) {
          if ((this.論太過不及等反常脈象) || (localObject1 != null)) {
            僅輸入原文(難經本義(paramThreatInfo, (String)localObject1), paramThreatType);
          } else {
            難經本義(paramThreatInfo, paramThreatType);
          }
        }
      }
      break;
    }
    if (this.論寸口脈與經經脈榮衛度數) {
      return 2;
    }
    return 0;
  }
  
  public void onScanEvent(ThreatInfo paramThreatInfo, SuspiciousThreatType paramSuspiciousThreatType)
  {
    if ((paramThreatInfo.isApplication()) && (!Utils.論別知臟腑之病(paramThreatInfo.getPackageName()))) {
      this.論損至脈之病證及其治法.add(paramThreatInfo.getPackageName());
    }
  }
  
  public int 一難()
  {
    return this.論別知臟腑之病;
  }
  
  public void 三難()
  {
    try
    {
      if (this.三難.get())
      {
        notify();
        if (this.論切脈而知死生存亡.isPaused()) {
          this.論切脈而知死生存亡.resumeScan();
        }
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void 二難()
  {
    if (this.論切脈而知死生存亡 != null) {
      this.論切脈而知死生存亡.pauseScan();
    }
  }
  
  public void 僅輸入原文()
  {
    this.論寸口脈與經經脈榮衛度數 = true;
    if (this.論切脈而知死生存亡 != null) {
      this.論切脈而知死生存亡.stopScan();
    }
  }
  
  public long 四難()
  {
    return this.二難;
  }
  
  public int 論太過不及等反常脈象()
  {
    return this.論脈有輕重;
  }
  
  public int 論寸口脈與經經脈榮衛度數()
  {
    五難();
    return this.論脈有輕重 - this.六難 - this.七難 - this.論王脈 - this.八難;
  }
  
  public boolean 論尺寸為脈之大要會()
  {
    return (this.論切脈而知死生存亡 != null) && (this.論切脈而知死生存亡.isPaused());
  }
  
  public void 論脈有陰陽之法()
  {
    三難();
  }
  
  public final Thread 難經本義(Scanner paramScanner, int paramInt, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Ap paramAp)
  {
    int i = -十八難[28];
    String str = 難經本義(i, i | 0x84, 十八難[13]);
    i = 十八難[25];
    KMSLog.難經本義(str, String.format(難經本義(i, i | 0xE4, 十八難[91]), new Object[] { Integer.valueOf(paramInt), paramString, Boolean.valueOf(paramBoolean1), Boolean.valueOf(paramBoolean2) }));
    if (!this.三難.compareAndSet(false, true)) {
      return null;
    }
    this.論寸口脈與經經脈榮衛度數 = false;
    this.難經本義 = paramInt;
    this.僅輸入原文 = paramString;
    this.難經本義卷上 = paramBoolean1;
    this.一難 = paramBoolean2;
    this.論切脈而知死生存亡 = paramScanner;
    this.論別知臟腑之病 = 0;
    this.五難 = 0;
    this.論脈有輕重 = 0;
    this.六難 = 0;
    this.論脈之陰陽虛實 = 0;
    this.七難 = 0;
    this.二難 = 0L;
    this.論王脈 = 0;
    this.八難 = 0;
    this.論寸口脈平而死者 = 0;
    this.九難 = 0;
    this.十二難.clear();
    this.十三難 = false;
    this.論色脈皮膚之相應 = "";
    this.論尺寸為脈之大要會 = SystemClock.elapsedRealtime();
    this.十難 = this.論尺寸為脈之大要會;
    this.論一脈十變 = this.論尺寸為脈之大要會;
    this.十四難 = paramAp;
    if (!this.十六難.get()) {
      this.論損至脈之病證及其治法.clear();
    }
    paramScanner = new Thread(new 難經本義(paramBoolean3));
    paramScanner.start();
    paramString = this.十七難.iterator();
    while (paramString.hasNext()) {
      ((Ad)paramString.next()).難經本義();
    }
    return paramScanner;
  }
  
  public void 難經本義(int paramInt, boolean paramBoolean)
  {
    this.論脈不滿五十動而一止 = paramInt;
    this.十三難 = paramBoolean;
  }
  
  public void 難經本義(Ad paramAd)
  {
    this.十七難.addIfAbsent(paramAd);
    if (this.三難.get())
    {
      paramAd.僅輸入原文(this.論別知臟腑之病);
      paramAd.難經本義(this.論色脈皮膚之相應);
    }
  }
  
  public boolean 難經本義()
  {
    return this.三難.get();
  }
  
  public int 難經本義卷上()
  {
    return this.難經本義;
  }
  
  class 難經本義
    implements Runnable
  {
    private static int 一難 = 23;
    private static final byte[] 難經本義卷上 = { 49, -91, 81, 34, -33, 34, -24, -8, -2, -3, -7, -12, 2, 14, -12, -2, -6, 20, -20, 16, 2, -13, 15, -19, 13, -14, -2, 9, -8, 21, -4, 2, -13, 11, -2, -7, 0, 7, 1, 16, 2, -13, -17, 18, -2, 11, -1, -2, -6, -4, -10, -10, 6, 25, -35, 12, -8 };
    private final boolean 僅輸入原文;
    
    public 難經本義(boolean paramBoolean)
    {
      this.僅輸入原文 = paramBoolean;
    }
    
    private void 一難()
    {
      Reports.add(7, new Serializable[] { zV.一難(zV.this), Integer.valueOf(zV.七難(zV.this)), Integer.valueOf(zV.論脈有陰陽之法(zV.this)), Integer.valueOf(zV.八難(zV.this)), Integer.valueOf(zV.論寸口脈平而死者(zV.this)), Integer.valueOf(zV.九難(zV.this)), Integer.valueOf(zV.論別知臟腑之病(zV.this)) });
    }
    
    private void 僅輸入原文()
    {
      Iterator localIterator = zV.論王脈(zV.this).iterator();
      while (localIterator.hasNext())
      {
        Ad localAd = (Ad)localIterator.next();
        int i;
        if (this.僅輸入原文) {
          i = zV.論尺寸為脈之大要會(zV.this);
        } else {
          i = 0;
        }
        localAd.論寸口脈與經經脈榮衛度數(i);
      }
    }
    
    private void 僅輸入原文(Context paramContext)
    {
      if (zV.論尺寸為脈之大要會(zV.this) == 0)
      {
        if (難經本義卷上(paramContext))
        {
          Intent localIntent = new Intent(paramContext, AvScanResultActivity.class);
          localIntent.setFlags(268435456);
          localIntent.putExtra(難經本義(-難經本義卷上[54], 難經本義卷上[17], -難經本義卷上[7]).intern(), zV.僅輸入原文(zV.this));
          if (zV.論太過不及等反常脈象(zV.this)) {
            localIntent.putExtra(難經本義(難經本義卷上[37], 難經本義卷上[36], -難經本義卷上[6]).intern(), true);
          }
          paramContext.startActivity(localIntent);
          return;
        }
        paramContext = EV.七難();
        paramContext.難經本義(true);
        paramContext.難經本義(zV.僅輸入原文(zV.this));
        paramContext.僅輸入原文(zV.論太過不及等反常脈象(zV.this));
        paramContext.難經本義(Aa.三難());
        paramContext.難經本義卷上(zV.this.論太過不及等反常脈象());
        paramContext.僅輸入原文(zV.this.一難());
        paramContext.y_();
      }
    }
    
    private static String 難經本義(int paramInt1, int paramInt2, int paramInt3)
    {
      paramInt1 += 4;
      byte[] arrayOfByte1 = 難經本義卷上;
      paramInt3 += 75;
      paramInt2 = 29 - paramInt2;
      int i = 0;
      int k = 0;
      byte[] arrayOfByte2 = new byte[paramInt2];
      int j = paramInt2 - 1;
      paramInt2 = paramInt1;
      if (arrayOfByte1 == null)
      {
        paramInt3 = j;
        i = paramInt1;
        paramInt2 = paramInt1;
        paramInt1 = k;
      }
      for (;;)
      {
        paramInt2 += 1;
        paramInt3 += -i;
        i = paramInt1;
        arrayOfByte2[i] = ((byte)paramInt3);
        paramInt1 = i + 1;
        if (i == j) {
          return new String(arrayOfByte2, 0);
        }
        i = arrayOfByte1[paramInt2];
      }
    }
    
    private void 難經本義()
    {
      new Thread(new zZ(this)).start();
    }
    
    private void 難經本義(Context paramContext)
    {
      if (zV.僅輸入原文(zV.this) != AntivirusScanType.Quick.getScanFlag()) {
        難經本義();
      }
      EJ localEJ = EV.僅輸入原文();
      boolean bool1;
      boolean bool2;
      try
      {
        i = ((Integer)localEJ.二難(0)).intValue();
        bool1 = ((Boolean)localEJ.二難(1)).booleanValue();
        bool2 = ((Boolean)localEJ.二難(8)).booleanValue();
        localEJ.難經本義(0);
        localEJ.僅輸入原文(0);
      }
      finally
      {
        paramContext = finally;
        throw paramContext;
      }
      if (i == 1) {
        i = 896;
      } else {
        i = 514;
      }
      int j = i;
      if (!bool2) {
        j = i | 0x800;
      }
      if (bool1) {
        i = 5;
      } else {
        i = 2;
      }
      zV.難經本義(zV.this, 0);
      try
      {
        if (zV.僅輸入原文(zV.this) == 8)
        {
          zV.難經本義(zV.this, j, i, true);
        }
        else if (zV.僅輸入原文(zV.this) == 2)
        {
          zV.難經本義(zV.this, j, i, false);
          File localFile = new File(zV.難經本義卷上(zV.this).sourceDir);
          zV.難經本義(zV.this, zV.一難(zV.this), j, i, zV.難經本義(localFile.getParent()));
          一難();
        }
        else
        {
          if (zV.論寸口脈與經經脈榮衛度數(zV.this)) {
            zV.難經本義(zV.this, j, i, false);
          }
          if (zV.二難(zV.this)) {
            zV.難經本義(zV.this, zV.一難(zV.this), j, i, new String[] { Environment.getExternalStorageDirectory().toString() });
          } else {
            zV.難經本義(zV.this, zV.一難(zV.this), j, i, null);
          }
          一難();
        }
      }
      catch (ScannerRuntimeException localScannerRuntimeException)
      {
        zV.難經本義(zV.this, localScannerRuntimeException.getErrorCode());
        if (zV.論尺寸為脈之大要會(zV.this) == -4) {
          GA.一難(難經本義(難經本義卷上[36], 難經本義卷上[29], -難經本義卷上[9]).intern());
        } else {
          throw localScannerRuntimeException;
        }
      }
      zV.難經本義(zV.this, SystemClock.elapsedRealtime() - zV.三難(zV.this));
      難經本義(localEJ);
      僅輸入原文();
      難經本義卷上();
      if (!zV.論太過不及等反常脈象(zV.this)) {
        GA.難經本義(GA.AvScanType.getByScanMode(zV.僅輸入原文(zV.this)), zV.四難(zV.this), zV.論脈有陰陽之法(zV.this));
      }
      僅輸入原文(paramContext);
      paramContext = EV.七難();
      int i = zV.this.論寸口脈與經經脈榮衛度數();
      j = paramContext.三難();
      paramContext.論寸口脈與經經脈榮衛度數(zV.this.論太過不及等反常脈象());
      paramContext.一難(i);
      paramContext.y_();
      if (i != j) {
        kW.論尺寸為脈之大要會().二難();
      }
      if (!zV.五難(zV.this).isEmpty()) {
        zV.論脈有輕重(zV.this);
      }
    }
    
    private void 難經本義(EJ paramEJ)
    {
      paramEJ.難經本義(zV.七難(zV.this));
      paramEJ.僅輸入原文(zV.論脈有陰陽之法(zV.this));
      if (!zV.論太過不及等反常脈象(zV.this)) {
        paramEJ.僅輸入原文(System.currentTimeMillis());
      }
      paramEJ.y_();
    }
    
    private void 難經本義卷上()
    {
      if (!zV.論太過不及等反常脈象(zV.this)) {
        KMSApplication.僅輸入原文.難經本義(16);
      }
    }
    
    private boolean 難經本義卷上(Context paramContext)
    {
      String str = mI.難經本義(paramContext);
      return paramContext.getPackageName().equals(str);
    }
    
    public void run()
    {
      zV.難經本義(zV.this, false);
      Context localContext = KMSApplication.難經本義;
      PowerManager.WakeLock localWakeLock = mJ.難經本義(localContext, 難經本義(43, 難經本義卷上[43], 難經本義卷上[36]).intern());
      try
      {
        難經本義(localContext);
        return;
      }
      finally
      {
        mJ.難經本義(localWakeLock);
        zV.難經本義(zV.this, null);
        zV.難經本義(zV.this).set(false);
      }
    }
  }
}
