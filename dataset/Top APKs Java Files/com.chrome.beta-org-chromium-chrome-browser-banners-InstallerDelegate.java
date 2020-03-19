package org.chromium.chrome.browser.banners;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import java.util.List;

public final class InstallerDelegate
  implements Runnable
{
  public final Handler mHandler;
  public boolean mIsRunning;
  public long mMsBetweenRuns;
  private long mMsMaximumWaitingTime;
  private final InstallerDelegate.Observer mObserver;
  private final PackageManager mPackageManager;
  private final String mPackageName;
  public long mTimestampStarted;
  
  public InstallerDelegate(Looper paramLooper, PackageManager paramPackageManager, InstallerDelegate.Observer paramObserver, String paramString)
  {
    this.mHandler = new Handler(paramLooper);
    this.mPackageManager = paramPackageManager;
    this.mObserver = paramObserver;
    this.mPackageName = paramString;
    this.mMsBetweenRuns = 1000L;
    this.mMsMaximumWaitingTime = 180000L;
  }
  
  public static boolean isInstalled(PackageManager paramPackageManager, String paramString)
  {
    boolean bool2 = false;
    paramPackageManager = paramPackageManager.getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < paramPackageManager.size())
      {
        if (TextUtils.equals(((PackageInfo)paramPackageManager.get(i)).packageName, paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public final void run()
  {
    boolean bool = isInstalled(this.mPackageManager, this.mPackageName);
    if (SystemClock.elapsedRealtime() - this.mTimestampStarted > this.mMsMaximumWaitingTime) {}
    for (int i = 1; (bool) || (!this.mIsRunning) || (i != 0); i = 0)
    {
      this.mObserver.onInstallFinished(this, bool);
      this.mIsRunning = false;
      return;
    }
    this.mHandler.postDelayed(this, this.mMsBetweenRuns);
  }
}
