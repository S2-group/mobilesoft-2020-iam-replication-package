package cn.andoumiao2.messenger;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import java.util.List;

class cz
  extends Thread
{
  Handler a;
  
  public cz(AppFragment paramAppFragment, Handler paramHandler)
  {
    this.a = paramHandler;
  }
  
  public void run()
  {
    int i = 0;
    super.run();
    Looper.prepare();
    PackageManager localPackageManager = this.b.getActivity().getPackageManager();
    List localList = localPackageManager.getInstalledPackages(0);
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0) {
        AppFragment.a(this.b, localPackageManager, localPackageInfo);
      }
      i += 1;
    }
    AppFragment.a(this.b, AppFragment.d(this.b));
    AppFragment.a(this.b, AppFragment.f(this.b));
    this.a.sendEmptyMessage(2);
  }
}
