package com.mrnumber.blocker.tasks;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.mrnumber.blocker.MrNumberPrefs;
import com.mrnumber.blocker.api.ApiDispatch;
import com.mrnumber.blocker.api.PutProfileListCommand;
import com.mrnumber.blocker.json.ProfileListJson;
import java.util.Iterator;
import java.util.List;

public class ProfileListTask
  extends SafeAsyncTask<Void, Void, Void>
{
  private Context ctx;
  
  public ProfileListTask(Context paramContext)
  {
    this.ctx = paramContext;
  }
  
  protected Void safelyDoInBackground(Void... paramVarArgs)
  {
    Object localObject = this.ctx.getPackageManager();
    paramVarArgs = ProfileListJson.makeSafely();
    Iterator localIterator = ((PackageManager)localObject).getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      paramVarArgs.addApplication(localApplicationInfo.loadLabel((PackageManager)localObject), localApplicationInfo.packageName, localApplicationInfo.loadDescription((PackageManager)localObject));
    }
    localObject = new ApiDispatch(this.ctx);
    try
    {
      ((ApiDispatch)localObject).execute(new PutProfileListCommand(paramVarArgs), false, false);
      MrNumberPrefs.setProfileListSent(true);
      return null;
    }
    catch (Throwable paramVarArgs)
    {
      for (;;) {}
    }
  }
}
