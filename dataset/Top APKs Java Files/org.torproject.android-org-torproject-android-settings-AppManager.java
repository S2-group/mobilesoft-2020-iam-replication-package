package org.torproject.android.settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.torproject.android.TorConstants;
import org.torproject.android.service.TorService;

public class AppManager
  extends Activity
  implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, TorConstants
{
  private ListView listApps;
  ArrayList<TorifiedApp> mApps = null;
  SharedPreferences mPrefs = null;
  
  public AppManager() {}
  
  public static ArrayList<TorifiedApp> getApps(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    Object localObject1 = new StringTokenizer(paramSharedPreferences.getString("PrefTord", ""), "|");
    paramSharedPreferences = new String[((StringTokenizer)localObject1).countTokens()];
    int i = 0;
    while (((StringTokenizer)localObject1).hasMoreTokens())
    {
      paramSharedPreferences[i] = ((StringTokenizer)localObject1).nextToken();
      i += 1;
    }
    Arrays.sort(paramSharedPreferences);
    paramContext = paramContext.getPackageManager();
    localObject1 = paramContext.getInstalledApplications(0).iterator();
    ArrayList localArrayList = new ArrayList();
    i = 0;
    ApplicationInfo localApplicationInfo;
    TorifiedApp localTorifiedApp;
    for (;;)
    {
      if (((Iterator)localObject1).hasNext())
      {
        localApplicationInfo = (ApplicationInfo)((Iterator)localObject1).next();
        localTorifiedApp = new TorifiedApp();
        try
        {
          Object localObject2 = paramContext.getPackageInfo(localApplicationInfo.packageName, 4096);
          if ((localObject2 != null) && (((PackageInfo)localObject2).requestedPermissions != null))
          {
            localObject2 = ((PackageInfo)localObject2).requestedPermissions;
            int k = localObject2.length;
            int j = 0;
            while (j < k)
            {
              if (localObject2[j].equals("android.permission.INTERNET")) {
                localTorifiedApp.setUsesInternet(true);
              }
              j += 1;
            }
          }
          try
          {
            localTorifiedApp.setName(paramContext.getApplicationLabel(localApplicationInfo).toString());
            if (Arrays.binarySearch(paramSharedPreferences, localTorifiedApp.getUsername()) >= 0)
            {
              localTorifiedApp.setTorified(true);
              i += 1;
            }
          }
          catch (Exception localException2)
          {
            for (;;)
            {
              localTorifiedApp.setName(localApplicationInfo.packageName);
              continue;
              localTorifiedApp.setTorified(false);
            }
          }
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
          if ((localApplicationInfo.flags & 0x1) == 1) {
            localTorifiedApp.setUsesInternet(true);
          }
          localArrayList.add(localTorifiedApp);
          localTorifiedApp.setEnabled(localApplicationInfo.enabled);
          localTorifiedApp.setUid(localApplicationInfo.uid);
          localTorifiedApp.setUsername(paramContext.getNameForUid(localTorifiedApp.getUid()));
          localTorifiedApp.setProcname(localApplicationInfo.processName);
        }
      }
    }
    Collections.sort(localArrayList);
    return localArrayList;
  }
  
  private void loadApps(final SharedPreferences paramSharedPreferences)
  {
    this.mApps = getApps(getApplicationContext(), paramSharedPreferences);
    paramSharedPreferences = getLayoutInflater();
    paramSharedPreferences = new ArrayAdapter(this, 2130903065, 2131165249, this.mApps)
    {
      public View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
      {
        TorifiedApp localTorifiedApp;
        if (paramAnonymousView == null)
        {
          paramAnonymousView = paramSharedPreferences.inflate(2130903065, paramAnonymousViewGroup, false);
          paramAnonymousViewGroup = new AppManager.ListEntry(null);
          AppManager.ListEntry.access$102(paramAnonymousViewGroup, (ImageView)paramAnonymousView.findViewById(2131165248));
          AppManager.ListEntry.access$202(paramAnonymousViewGroup, (CheckBox)paramAnonymousView.findViewById(2131165250));
          AppManager.ListEntry.access$302(paramAnonymousViewGroup, (TextView)paramAnonymousView.findViewById(2131165249));
          AppManager.ListEntry.access$300(paramAnonymousViewGroup).setOnClickListener(AppManager.this);
          AppManager.ListEntry.access$300(paramAnonymousViewGroup).setOnClickListener(AppManager.this);
          paramAnonymousView.setTag(paramAnonymousViewGroup);
          AppManager.ListEntry.access$200(paramAnonymousViewGroup).setOnCheckedChangeListener(AppManager.this);
          localTorifiedApp = (TorifiedApp)AppManager.this.mApps.get(paramAnonymousInt);
          if (localTorifiedApp.getIcon() == null) {
            break label207;
          }
          AppManager.ListEntry.access$100(paramAnonymousViewGroup).setImageDrawable(localTorifiedApp.getIcon());
        }
        for (;;)
        {
          AppManager.ListEntry.access$300(paramAnonymousViewGroup).setText(localTorifiedApp.getName());
          CheckBox localCheckBox = AppManager.ListEntry.access$200(paramAnonymousViewGroup);
          localCheckBox.setTag(localTorifiedApp);
          localCheckBox.setChecked(localTorifiedApp.isTorified());
          AppManager.ListEntry.access$300(paramAnonymousViewGroup).setTag(localCheckBox);
          AppManager.ListEntry.access$100(paramAnonymousViewGroup).setTag(localCheckBox);
          return paramAnonymousView;
          paramAnonymousViewGroup = (AppManager.ListEntry)paramAnonymousView.getTag();
          break;
          label207:
          AppManager.ListEntry.access$100(paramAnonymousViewGroup).setVisibility(8);
        }
      }
    };
    this.listApps.setAdapter(paramSharedPreferences);
  }
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    paramCompoundButton = (TorifiedApp)paramCompoundButton.getTag();
    if (paramCompoundButton != null) {
      paramCompoundButton.setTorified(paramBoolean);
    }
    saveAppSettings(this);
  }
  
  public void onClick(View paramView)
  {
    paramView = (CheckBox)paramView.getTag();
    TorifiedApp localTorifiedApp = (TorifiedApp)paramView.getTag();
    if (localTorifiedApp != null) {
      if (localTorifiedApp.isTorified()) {
        break label48;
      }
    }
    label48:
    for (boolean bool = true;; bool = false)
    {
      localTorifiedApp.setTorified(bool);
      paramView.setChecked(localTorifiedApp.isTorified());
      saveAppSettings(this);
      return;
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903064);
  }
  
  protected void onResume()
  {
    super.onResume();
    this.listApps = ((ListView)findViewById(2131165247));
    ((Button)findViewById(2131165246)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AppManager.this.finish();
      }
    });
    this.mPrefs = TorService.getSharedPrefs(getApplicationContext());
    loadApps(this.mPrefs);
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void saveAppSettings(Context paramContext)
  {
    paramContext = new StringBuilder();
    Object localObject = this.mApps.iterator();
    while (((Iterator)localObject).hasNext())
    {
      TorifiedApp localTorifiedApp = (TorifiedApp)((Iterator)localObject).next();
      if (localTorifiedApp.isTorified())
      {
        paramContext.append(localTorifiedApp.getUsername());
        paramContext.append("|");
      }
    }
    localObject = this.mPrefs.edit();
    ((SharedPreferences.Editor)localObject).putString("PrefTord", paramContext.toString());
    ((SharedPreferences.Editor)localObject).commit();
  }
  
  private static class ListEntry
  {
    private CheckBox box;
    private ImageView icon;
    private TextView text;
    
    private ListEntry() {}
  }
}
