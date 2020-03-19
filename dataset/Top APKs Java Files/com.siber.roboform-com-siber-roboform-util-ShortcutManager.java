package com.siber.roboform.util;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutInfo.Builder;
import android.database.Cursor;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.siber.lib_util.FileUtils;
import com.siber.lib_util.Tracer;
import com.siber.roboform.StarterActivity;
import com.siber.roboform.filesystem.fileitem.FileItem;
import com.siber.roboform.filesystem.fileitem.FileType;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShortcutManager
{
  private static final String a = "ShortcutManager";
  private static ShortcutManager b;
  private ShortcutActions c;
  
  private ShortcutManager()
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      this.c = new Android_O_Actions(null);
      return;
    }
    this.c = new Android_Pre_O_Actions(null);
  }
  
  private int a(String paramString)
  {
    if (FileType.b.a(paramString)) {
      return 2131231166;
    }
    if (FileType.c.a(paramString)) {
      return 2131230867;
    }
    if (FileType.e.a(paramString)) {
      return 2131231153;
    }
    if (FileType.f.a(paramString)) {
      return 2131230921;
    }
    if (FileType.d.a(paramString)) {
      return 2131231206;
    }
    if (FileType.g.a(paramString)) {
      return 2131231166;
    }
    return 2131231152;
  }
  
  public static ShortcutManager a()
  {
    if (b == null) {
      b = new ShortcutManager();
    }
    return b;
  }
  
  public void a(FragmentActivity paramFragmentActivity, String paramString1, String paramString2)
  {
    b(FileItem.a(paramString1), paramFragmentActivity);
    a(FileItem.a(paramString2), paramFragmentActivity);
  }
  
  public void a(FileItem paramFileItem, FragmentActivity paramFragmentActivity)
  {
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("create shortcut for ");
    localStringBuilder.append(paramFileItem.toString());
    Tracer.b((String)localObject, localStringBuilder.toString());
    localObject = new Intent(paramFragmentActivity.getApplicationContext(), StarterActivity.class);
    ((Intent)localObject).setAction(paramFileItem.Path);
    ((Intent)localObject).addFlags(536870912);
    ((Intent)localObject).addFlags(67108864);
    ((Intent)localObject).putExtra("com.siber.roboform.starter_file_name_extra", paramFileItem.Path);
    this.c.a(paramFileItem, (Intent)localObject, paramFragmentActivity);
  }
  
  public boolean a(String paramString, FragmentActivity paramFragmentActivity)
  {
    return this.c.a(paramString, paramFragmentActivity);
  }
  
  public void b(FileItem paramFileItem, FragmentActivity paramFragmentActivity)
  {
    this.c.a(paramFileItem, paramFragmentActivity);
  }
  
  @SuppressLint({"NewApi"})
  private class Android_O_Actions
    implements ShortcutManager.ShortcutActions
  {
    private Android_O_Actions() {}
    
    public void a(FileItem paramFileItem, Intent paramIntent, FragmentActivity paramFragmentActivity)
    {
      try
      {
        String str = FileUtils.b(paramFileItem.Path);
        android.content.pm.ShortcutManager localShortcutManager = (android.content.pm.ShortcutManager)paramFragmentActivity.getSystemService(android.content.pm.ShortcutManager.class);
        if (localShortcutManager.isRequestPinShortcutSupported()) {
          localShortcutManager.requestPinShortcut(new ShortcutInfo.Builder(paramFragmentActivity, paramFileItem.Path).setIcon(Icon.createWithResource(paramFragmentActivity, ShortcutManager.a(ShortcutManager.this, str))).setShortLabel(paramFileItem.f()).setIntent(paramIntent).build(), null);
        }
        return;
      }
      catch (Exception paramFileItem) {}
    }
    
    public void a(FileItem paramFileItem, FragmentActivity paramFragmentActivity)
    {
      paramFragmentActivity = (android.content.pm.ShortcutManager)paramFragmentActivity.getSystemService(android.content.pm.ShortcutManager.class);
      try
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(paramFileItem.Path);
        paramFragmentActivity.disableShortcuts(localArrayList);
        return;
      }
      catch (Exception paramFileItem) {}
    }
    
    public boolean a(String paramString, FragmentActivity paramFragmentActivity)
    {
      paramFragmentActivity = (android.content.pm.ShortcutManager)paramFragmentActivity.getSystemService(android.content.pm.ShortcutManager.class);
      try
      {
        paramFragmentActivity = paramFragmentActivity.getPinnedShortcuts().iterator();
        while (paramFragmentActivity.hasNext())
        {
          boolean bool = TextUtils.equals(((ShortcutInfo)paramFragmentActivity.next()).getId(), paramString);
          if (bool) {
            return true;
          }
        }
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
      return false;
    }
  }
  
  private class Android_Pre_O_Actions
    implements ShortcutManager.ShortcutActions
  {
    private Android_Pre_O_Actions() {}
    
    private String a(Context paramContext, String paramString)
    {
      if (paramString == null) {
        return null;
      }
      paramContext = paramContext.getPackageManager().getInstalledPackages(8);
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
          if (arrayOfProviderInfo != null)
          {
            int j = arrayOfProviderInfo.length;
            int i = 0;
            while (i < j)
            {
              ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
              if ((localProviderInfo != null) && ((TextUtils.equals(paramString, localProviderInfo.readPermission)) || (TextUtils.equals(paramString, localProviderInfo.writePermission)))) {
                return localProviderInfo.authority;
              }
              i += 1;
            }
          }
        }
      }
      return null;
    }
    
    public void a(FileItem paramFileItem, Intent paramIntent, FragmentActivity paramFragmentActivity)
    {
      String str = FileUtils.b(paramFileItem.Path);
      Intent localIntent = new Intent();
      localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
      localIntent.putExtra("android.intent.extra.shortcut.NAME", paramFileItem.f());
      localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramFragmentActivity, ShortcutManager.a(ShortcutManager.this, str)));
      localIntent.putExtra("duplicate", false);
      localIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
      paramFragmentActivity.setResult(-1, localIntent);
      paramFragmentActivity.sendBroadcast(localIntent);
    }
    
    public void a(FileItem paramFileItem, FragmentActivity paramFragmentActivity)
    {
      Intent localIntent1 = new Intent();
      Intent localIntent2 = new Intent(paramFragmentActivity.getApplicationContext(), StarterActivity.class);
      localIntent2.setAction(paramFileItem.Path);
      localIntent2.addFlags(536870912);
      localIntent2.addFlags(67108864);
      localIntent2.putExtra("com.siber.roboform.starter_file_name_extra", paramFileItem.Path);
      localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
      localIntent1.putExtra("android.intent.extra.shortcut.NAME", paramFileItem.f());
      localIntent1.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
      localIntent1.putExtra("duplicate", false);
      paramFragmentActivity.setResult(-1, localIntent1);
      paramFragmentActivity.sendBroadcast(localIntent1);
    }
    
    public boolean a(String paramString, FragmentActivity paramFragmentActivity)
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("content://");
      ((StringBuilder)localObject).append(a(paramFragmentActivity, "com.android.launcher.permission.READ_SETTINGS"));
      ((StringBuilder)localObject).append("/favorites?notify=true");
      localObject = ((StringBuilder)localObject).toString();
      paramFragmentActivity = paramFragmentActivity.getContentResolver();
      try
      {
        paramFragmentActivity = paramFragmentActivity.query(Uri.parse((String)localObject), new String[] { "title", "intent" }, "title=?", new String[] { paramString }, null);
        if (paramFragmentActivity == null) {
          return false;
        }
        int i = paramFragmentActivity.getColumnIndex("Intent");
        if (paramFragmentActivity.getCount() > 0)
        {
          paramFragmentActivity.moveToFirst();
          while (!paramFragmentActivity.isAfterLast())
          {
            localObject = paramFragmentActivity.getString(i);
            try
            {
              localObject = Intent.parseUri((String)localObject, 0);
              if (((Intent)localObject).hasExtra("com.siber.roboform.starter_file_name_extra"))
              {
                boolean bool = ((Intent)localObject).getStringExtra("com.siber.roboform.starter_file_name_extra").equals(paramString);
                if (bool) {
                  return true;
                }
              }
            }
            catch (NullPointerException localNullPointerException)
            {
              Crashlytics.getInstance().core.logException(localNullPointerException);
            }
            catch (URISyntaxException localURISyntaxException)
            {
              Crashlytics.getInstance().core.logException(localURISyntaxException);
            }
            paramFragmentActivity.moveToNext();
          }
          paramFragmentActivity.close();
        }
        return false;
      }
      catch (Exception paramString)
      {
        Crashlytics.getInstance().core.logException(paramString);
      }
      return false;
    }
  }
  
  private static abstract interface ShortcutActions
  {
    public abstract void a(FileItem paramFileItem, Intent paramIntent, FragmentActivity paramFragmentActivity);
    
    public abstract void a(FileItem paramFileItem, FragmentActivity paramFragmentActivity);
    
    public abstract boolean a(String paramString, FragmentActivity paramFragmentActivity);
  }
}
