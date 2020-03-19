package com.nuance.dragon.toolkit.grammar.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.nuance.dragon.toolkit.file.FileManager;
import com.nuance.dragon.toolkit.util.Logger;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AppsManager
  extends ContentManager
{
  private final BroadcastReceiver a = new BroadcastReceiver()
  {
    public final void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      AppsManager.this.onContentUpdate();
    }
  };
  
  public AppsManager(String paramString, FileManager paramFileManager, Context paramContext)
  {
    this(paramString, paramFileManager, false, false, paramContext);
  }
  
  public AppsManager(String paramString, FileManager paramFileManager, boolean paramBoolean, Context paramContext)
  {
    this(paramString, paramFileManager, false, paramBoolean, paramContext);
  }
  
  public AppsManager(String paramString, FileManager paramFileManager, boolean paramBoolean1, boolean paramBoolean2, Context paramContext)
  {
    super(paramString, paramFileManager, paramBoolean2, paramContext);
    initialize(paramBoolean1);
  }
  
  protected ContentManager.PlatformFullIterator getPlatformFullIterator()
  {
    PackageManager localPackageManager = this._context.getPackageManager();
    final TreeSet localTreeSet = new TreeSet(new Comparator() {});
    try
    {
      List localList = localPackageManager.getInstalledApplications(0);
      if (localList != null)
      {
        i = 0;
        if (i < localList.size())
        {
          str1 = ((ApplicationInfo)localList.get(i)).packageName;
          if ((str1 != null) && (str1.length() != 0))
          {
            localObject2 = new Intent("android.intent.action.MAIN");
            ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
            ((Intent)localObject2).setPackage(str1);
          }
        }
      }
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        int i;
        try
        {
          Object localObject2 = localPackageManager.queryIntentActivities((Intent)localObject2, 0);
          int j = 0;
          if (j < ((List)localObject2).size())
          {
            String str2 = ((ResolveInfo)((List)localObject2).get(j)).loadLabel(localPackageManager).toString();
            if (str2.length() > 0)
            {
              new StringBuilder("Indexing application: ").append(str2);
              localTreeSet.add(str2);
            }
            j += 1;
            continue;
            localException1 = localException1;
            Logger.error(this, "Unable to fetch installed application list from device.", localException1);
            Object localObject1 = null;
          }
        }
        catch (Exception localException2)
        {
          String str1;
          Logger.error(this, "Unable to fetch application name in " + str1, localException2);
        }
        i += 1;
      }
    }
    new ContentManager.PlatformFullIterator()
    {
      private Iterator<String> c;
      
      public final int getSize()
      {
        if (localTreeSet != null) {
          return localTreeSet.size();
        }
        return 0;
      }
      
      public final boolean hasNext()
      {
        if (this.c != null) {
          return this.c.hasNext();
        }
        return false;
      }
      
      public final void remove() {}
    };
  }
  
  protected void stopWatchingForContentUpdates(Context paramContext)
  {
    paramContext.unregisterReceiver(this.a);
  }
  
  protected void watchForContentUpdates(Context paramContext)
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addDataScheme("package");
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
    paramContext.registerReceiver(this.a, localIntentFilter);
  }
}
