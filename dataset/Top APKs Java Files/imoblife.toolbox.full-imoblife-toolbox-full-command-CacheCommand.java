package imoblife.toolbox.full.command;

import android.content.Context;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.StatFs;
import base.util.LogUtil;
import base.util.MathUtil;
import base.util.PackageUtil;
import base.util.os.StatFsUtil;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CacheCommand
  extends ExaminableCommand
{
  private static final String ATTR_PACKAGE_STATS = "PackageStats";
  public static final String TAG = CacheCommand.class.getSimpleName();
  private CountDownLatch countSignal;
  private int countTotal;
  private long delta;
  private long total_app;
  private long total_size;
  
  public CacheCommand(Context paramContext)
  {
    super(paramContext);
  }
  
  private long getEnvironmentSize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l;
  }
  
  public long cleanCache(Context paramContext)
  {
    CountDownLatch localCountDownLatch = new CountDownLatch(1);
    this.delta = StatFsUtil.getFreeRom();
    try
    {
      paramContext = paramContext.getPackageManager();
      Class localClass = Long.TYPE;
      paramContext.getClass().getMethod("freeStorageAndNotify", new Class[] { localClass, IPackageDataObserver.class }).invoke(paramContext, new Object[] { Long.valueOf(getEnvironmentSize() - 1L), new CacheCommand.1(this, localCountDownLatch) });
      localCountDownLatch.await();
      this.delta = Math.abs(StatFsUtil.getFreeRom() - this.delta);
      return MathUtil.clamp(0L, this.delta, StatFsUtil.getFreeRom());
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        LogUtil.w(TAG, paramContext);
      }
    }
  }
  
  public void examine()
  {
    for (;;)
    {
      try
      {
        localObject1 = getContext().getPackageManager();
        Object localObject4 = ((PackageManager)localObject1).getInstalledPackages(0);
        localObject3 = new CacheCommand.PackageStatsObserver(this);
        this.countTotal = ((List)localObject4).size();
        this.countSignal = new CountDownLatch(this.countTotal);
        this.total_size = 0L;
        this.total_app = 0L;
        localObject4 = ((List)localObject4).iterator();
        if (((Iterator)localObject4).hasNext())
        {
          localPackageInfo = (PackageInfo)((Iterator)localObject4).next();
          if (!isCanceled()) {}
        }
        else
        {
          this.countSignal.await(60L, TimeUnit.SECONDS);
          localObject1 = ((CacheCommand.PackageStatsObserver)localObject3).sort().getResult();
          if ((localObject1 != null) && (((List)localObject1).size() > 0))
          {
            localObject3 = new ExaminableCommand.Progress(this, this);
            ((ExaminableCommand.Progress)localObject3).setMsg(getContext().getString(2131165695));
            ((ExaminableCommand.Progress)localObject3).setArg1((int)(this.countTotal - this.countSignal.getCount()));
            ((ExaminableCommand.Progress)localObject3).setArg2(this.countTotal);
            ((ExaminableCommand.Progress)localObject3).setObj(localObject1);
            if ((!isCanceled()) && (getListener() != null)) {
              getListener().onExamining((ExaminableCommand.Progress)localObject3);
            }
          }
          return;
        }
      }
      catch (Exception localException)
      {
        Object localObject1;
        Object localObject3;
        PackageInfo localPackageInfo;
        Method localMethod;
        LogUtil.w(TAG, localException);
        return;
      }
      finally
      {
        if (isCanceled()) {
          continue;
        }
        onExamined(getContext(), this.total_app, this.total_size);
      }
      localMethod = localObject1.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
      localMethod.setAccessible(true);
      localMethod.invoke(localObject1, new Object[] { localPackageInfo.packageName, localObject3 });
    }
  }
  
  public void execute(List... paramVarArgs)
  {
    if (paramVarArgs.length <= 0)
    {
      long l = cleanCache(getContext());
      onExecuted(getContext(), -1L, l);
    }
    for (;;)
    {
      return;
      int i = 0;
      while (i < paramVarArgs[0].size())
      {
        PackageUtil.showAppDetails(getContext(), (String)paramVarArgs[0].get(i));
        i += 1;
      }
    }
  }
}
