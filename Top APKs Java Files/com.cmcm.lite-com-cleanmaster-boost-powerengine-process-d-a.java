package com.cleanmaster.boost.powerengine.process.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.cleanmaster.activitymanagerhelper.RunningAppProcessInfo;
import com.cleanmaster.boost.powerengine.b.f;
import com.cleanmaster.boost.powerengine.b.g;
import com.cleanmaster.boost.powerengine.process.ProcessModel;
import com.cleanmaster.boost.powerengine.process.clond.ProcCloudDefine;
import com.cleanmaster.utilext.AppInfo;
import com.cleanmaster.utilext.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class a
  extends com.cleanmaster.boost.powerengine.c.a
{
  public int a = 2;
  public int b = 2;
  public b c = null;
  public b d = null;
  public Map<String, AppInfo> e = null;
  public final List<ProcessModel> f = new ArrayList();
  public HashMap<Long, HashSet<Long>> g = null;
  public int h = 48;
  public g i = null;
  public f j = null;
  public com.cleanmaster.boost.powerengine.b.b k = null;
  
  public a() {}
  
  private void a(List<String> paramList, List<RunningAppProcessInfo> paramList1)
  {
    if ((paramList == null) || (paramList1 == null) || (paramList1.size() <= 0)) {
      label17:
      return;
    } else {
      paramList1 = paramList1.iterator();
    }
    for (;;)
    {
      if (!paramList1.hasNext()) {
        break label17;
      }
      Object localObject = (RunningAppProcessInfo)paramList1.next();
      if ((localObject == null) || (((RunningAppProcessInfo)localObject).d == null) || (((RunningAppProcessInfo)localObject).d.length <= 0)) {
        break;
      }
      localObject = ((RunningAppProcessInfo)localObject).d;
      int n = localObject.length;
      int m = 0;
      while (m < n)
      {
        CharSequence localCharSequence = localObject[m];
        if ((!TextUtils.isEmpty(localCharSequence)) && (!paramList.contains(localCharSequence)))
        {
          paramList.add(localCharSequence);
          if (ProcCloudDefine.a) {
            Log.d("cm_power_cloud", "pkgscansetting, running_pkg:" + localCharSequence);
          }
        }
        m += 1;
      }
    }
  }
  
  private boolean a(Context paramContext, ProcessModel paramProcessModel, int paramInt1, PackageInfo paramPackageInfo, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
  {
    if ((paramContext == null) || (paramProcessModel == null) || (paramPackageInfo == null)) {
      return false;
    }
    paramProcessModel.a(paramPackageInfo.packageName);
    paramProcessModel.a(paramInt1);
    if (paramPackageInfo.applicationInfo != null)
    {
      if (paramBoolean1) {
        paramProcessModel.b(i.a(paramContext, paramPackageInfo.applicationInfo));
      }
      paramProcessModel.h(paramPackageInfo.applicationInfo.flags);
      paramProcessModel.b = paramInt2;
      paramProcessModel.a = paramBoolean2;
    }
    paramProcessModel.j(paramPackageInfo.versionCode);
    if (Build.VERSION.SDK_INT >= 9) {
      paramProcessModel.c(paramPackageInfo.lastUpdateTime);
    }
    return true;
  }
  
  private boolean a(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, b paramB)
  {
    boolean bool = false;
    if (paramB == null) {
      paramBoolean = true;
    }
    do
    {
      do
      {
        return paramBoolean;
        if (paramInt1 == 4)
        {
          if (!paramB.b) {
            return true;
          }
          return com.cleanmaster.boost.powerengine.process.a.a(paramString, paramB.g, paramInt1, paramInt2, false, paramBoolean);
        }
        paramBoolean = bool;
      } while (paramB.a);
      paramBoolean = bool;
    } while (paramInt1 != 2);
    return true;
  }
  
  public void a(Context paramContext, List<RunningAppProcessInfo> paramList, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.f.clear();
    if ((paramContext == null) || ((this.c == null) && (this.d == null))) {}
    boolean bool;
    PackageInfo localPackageInfo;
    int m;
    label532:
    label544:
    label556:
    label568:
    label580:
    label592:
    label604:
    label616:
    label628:
    label640:
    label652:
    label664:
    label685:
    int n;
    label696:
    int i1;
    for (;;)
    {
      return;
      Object localObject1 = paramContext.getPackageManager();
      if (localObject1 != null) {
        try
        {
          localObject1 = ((PackageManager)localObject1).getInstalledPackages(64);
          if ((localObject1 != null) && (((List)localObject1).size() > 0))
          {
            Object localObject2;
            if (ProcCloudDefine.a)
            {
              localObject2 = new StringBuilder().append("pkgscansetting, run_scan_usr_app:");
              if (this.c == null)
              {
                bool = false;
                localObject2 = ((StringBuilder)localObject2).append(bool).append(", run_scan_pre_app:");
                if (this.c != null) {
                  break label532;
                }
                bool = false;
                localObject2 = ((StringBuilder)localObject2).append(bool).append(", run_def_check_usr_app:");
                if (this.c != null) {
                  break label544;
                }
                bool = false;
                localObject2 = ((StringBuilder)localObject2).append(bool).append(", run_def_check_sys_app:");
                if (this.c != null) {
                  break label556;
                }
                bool = false;
                localObject2 = ((StringBuilder)localObject2).append(bool).append(", run_def_hide_usr_app:");
                if (this.c != null) {
                  break label568;
                }
                bool = false;
                localObject2 = ((StringBuilder)localObject2).append(bool).append(", run_def_hide_sys_app:");
                if (this.c != null) {
                  break label580;
                }
                bool = false;
                localObject2 = ((StringBuilder)localObject2).append(bool).append(", no_run_scan_usr_app:");
                if (this.d != null) {
                  break label592;
                }
                bool = false;
                localObject2 = ((StringBuilder)localObject2).append(bool).append(", no_run_scan_pre_app:");
                if (this.d != null) {
                  break label604;
                }
                bool = false;
                localObject2 = ((StringBuilder)localObject2).append(bool).append(", no_run_def_check_usr_app:");
                if (this.d != null) {
                  break label616;
                }
                bool = false;
                localObject2 = ((StringBuilder)localObject2).append(bool).append(", no_run_def_check_sys_app:");
                if (this.d != null) {
                  break label628;
                }
                bool = false;
                localObject2 = ((StringBuilder)localObject2).append(bool).append(", no_run_def_hide_usr_app:");
                if (this.d != null) {
                  break label640;
                }
                bool = false;
                localObject2 = ((StringBuilder)localObject2).append(bool).append(", no_run_def_hide_sys_app:");
                if (this.d != null) {
                  break label652;
                }
                bool = false;
                Log.d("cm_power_cloud", bool);
              }
            }
            else
            {
              localObject2 = new ArrayList();
              a((List)localObject2, paramList);
              Iterator localIterator = ((List)localObject1).iterator();
              while (localIterator.hasNext())
              {
                localPackageInfo = (PackageInfo)localIterator.next();
                if ((localPackageInfo != null) && (!TextUtils.isEmpty(localPackageInfo.packageName)))
                {
                  if (!((List)localObject2).contains(localPackageInfo.packageName)) {
                    break label664;
                  }
                  m = 1;
                  if ((!paramBoolean1) || (2 != m)) {
                    break label685;
                  }
                  if (ProcCloudDefine.a) {
                    Log.d("cm_power_cloud", "pkgscansetting, stopped_filter:" + localPackageInfo.packageName + ",pkg_status:" + m);
                  }
                }
              }
            }
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            str = null;
            continue;
            bool = this.c.a;
            continue;
            bool = this.c.b;
            continue;
            bool = this.c.c;
            continue;
            bool = this.c.d;
            continue;
            bool = this.c.e;
            continue;
            bool = this.c.f;
            continue;
            bool = this.d.a;
            continue;
            bool = this.d.b;
            continue;
            bool = this.d.c;
            continue;
            bool = this.d.d;
            continue;
            bool = this.d.e;
            continue;
            bool = this.d.f;
            continue;
            if (1 == i.a(localPackageInfo)) {
              m = 2;
            } else {
              m = 3;
            }
          }
          if (localPackageInfo.applicationInfo == null)
          {
            n = 0;
            bool = false;
            if (!i.a(localPackageInfo.applicationInfo)) {
              break label794;
            }
          }
          label794:
          for (i1 = 4;; i1 = 2)
          {
            if (4 == i1) {
              bool = i.a(paramContext, localPackageInfo);
            }
            if (!bool) {
              break label800;
            }
            if (!ProcCloudDefine.a) {
              break;
            }
            Log.d("cm_power_cloud", "pkgscansetting, sys_signature_filter:" + localPackageInfo.packageName + ",pkg_status:" + m);
            break;
            n = localPackageInfo.applicationInfo.flags;
            break label696;
          }
          label800:
          paramList = this.d;
          if (1 != m) {
            break label1004;
          }
        }
      }
    }
    paramList = this.c;
    label1004:
    for (String str = "no_running_filter:";; str = "running_filter:")
    {
      if (a(localPackageInfo.packageName, i1, n, bool, paramList))
      {
        if (!ProcCloudDefine.a) {
          break;
        }
        Log.d("cm_power_cloud", "pkgscansetting, " + str + localPackageInfo.packageName + ",pkg_status:" + m + ",type:" + i1);
        break;
      }
      paramList = new ProcessModel();
      if (!a(paramContext, paramList, m, localPackageInfo, paramBoolean2, i1, bool)) {
        break;
      }
      this.f.add(paramList);
      if (!ProcCloudDefine.a) {
        break;
      }
      Log.d("cm_power_cloud", "pkgscansetting, pkg_scan:" + paramList.l() + ",pkg_status:" + m + ",type:" + paramList.b);
      break;
    }
  }
}
