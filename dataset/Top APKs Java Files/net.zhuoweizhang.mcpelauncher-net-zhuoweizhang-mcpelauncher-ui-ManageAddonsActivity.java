package net.zhuoweizhang.mcpelauncher.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.zhuoweizhang.mcpelauncher.AddonManager;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.layout;
import net.zhuoweizhang.mcpelauncher.R.menu;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.Utils;

public class ManageAddonsActivity
  extends ListActivity
{
  private static final int DIALOG_MANAGE_PATCH = 1;
  private static final int DIALOG_MANAGE_PATCH_CURRENTLY_DISABLED = 2;
  private static final int DIALOG_MANAGE_PATCH_CURRENTLY_ENABLED = 3;
  private static String disabledString = " (disabled)";
  private static String enabledString = "";
  private List<AddonListItem> addons;
  protected CompoundButton master = null;
  private AddonListItem selectedAddonItem;
  
  public ManageAddonsActivity() {}
  
  private void afterAddonToggle(AddonListItem paramAddonListItem)
  {
    setAddonListModified();
  }
  
  private void findAddons()
  {
    PackageManager localPackageManager = getPackageManager();
    Object localObject = localPackageManager.getInstalledApplications(128);
    ArrayList localArrayList = new ArrayList();
    AddonManager localAddonManager = AddonManager.getAddonManager(this);
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if ((localApplicationInfo.metaData != null) && (localApplicationInfo.metaData.getString("net.zhuoweizhang.mcpelauncher.api.targetmcpeversion") != null))
      {
        AddonListItem localAddonListItem = new AddonListItem(localApplicationInfo, localAddonManager.isEnabled(localApplicationInfo.packageName));
        localAddonListItem.displayName = (localPackageManager.getApplicationLabel(localApplicationInfo).toString() + " " + localAddonListItem.displayName);
        localArrayList.add(localAddonListItem);
      }
    }
    receiveAddons(localArrayList);
  }
  
  private void openManageAddonWindow(AddonListItem paramAddonListItem)
  {
    this.selectedAddonItem = paramAddonListItem;
    if (paramAddonListItem.enabled) {}
    for (int i = 3;; i = 2)
    {
      showDialog(i);
      return;
    }
  }
  
  private void receiveAddons(List<AddonListItem> paramList)
  {
    this.addons = paramList;
    ArrayList localArrayList = new ArrayList(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((AddonListItem)localIterator.next()).appInfo.packageName);
    }
    AddonManager.getAddonManager(this).removeDeadEntries(localArrayList);
    paramList = new ArrayAdapter(this, R.layout.patch_list_item, paramList);
    paramList.sort(new AddonListComparator(null));
    setListAdapter(paramList);
  }
  
  protected AlertDialog createManageAddonDialog(int paramInt)
  {
    if (paramInt == -1)
    {
      localObject = new CharSequence[1];
      localObject[0] = getResources().getText(R.string.manage_patches_delete);
      new AlertDialog.Builder(this).setTitle("Addon name goes here").setItems((CharSequence[])localObject, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (paramAnonymousInt == 0) {}
          while (paramAnonymousInt != 1) {
            try
            {
              ManageAddonsActivity.this.deleteAddon(ManageAddonsActivity.this.selectedAddonItem);
              ManageAddonsActivity.this.findAddons();
              return;
            }
            catch (Exception paramAnonymousDialogInterface)
            {
              paramAnonymousDialogInterface.printStackTrace();
              return;
            }
          }
          ManageAddonsActivity.this.toggleAddon(ManageAddonsActivity.this.selectedAddonItem);
          ManageAddonsActivity.this.findAddons();
        }
      }).create();
    }
    CharSequence[] arrayOfCharSequence = new CharSequence[2];
    arrayOfCharSequence[0] = getResources().getText(R.string.manage_patches_delete);
    if (paramInt == 0) {}
    for (Object localObject = getResources().getText(R.string.manage_patches_enable);; localObject = getResources().getText(R.string.manage_patches_disable))
    {
      arrayOfCharSequence[1] = localObject;
      localObject = arrayOfCharSequence;
      break;
    }
  }
  
  public void deleteAddon(AddonListItem paramAddonListItem)
    throws Exception
  {
    startActivityForResult(new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramAddonListItem.appInfo.packageName)), 123);
    setAddonListModified();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 123) {
      findAddons();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    Utils.setLanguageOverride();
    super.onCreate(paramBundle);
    setResult(0);
    paramBundle = getListView();
    paramBundle.setTextFilterEnabled(true);
    paramBundle.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        ManageAddonsActivity.this.openManageAddonWindow((ManageAddonsActivity.AddonListItem)ManageAddonsActivity.this.addons.get(paramAnonymousInt));
      }
    });
  }
  
  public Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return super.onCreateDialog(paramInt);
    case 1: 
      return createManageAddonDialog(-1);
    case 3: 
      return createManageAddonDialog(1);
    }
    return createManageAddonDialog(0);
  }
  
  @TargetApi(14)
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      getMenuInflater().inflate(R.menu.ab_master, paramMenu);
      this.master = ((CompoundButton)paramMenu.findItem(R.id.ab_switch_container).getActionView().findViewById(R.id.ab_switch));
      if (this.master != null)
      {
        this.master.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
          public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
          {
            if (paramAnonymousBoolean) {
              ManageAddonsActivity.this.findAddons();
            }
            for (;;)
            {
              paramAnonymousCompoundButton = Utils.getPrefs(0).edit();
              paramAnonymousCompoundButton.putBoolean("zz_load_native_addons", paramAnonymousBoolean);
              paramAnonymousCompoundButton.apply();
              ManageAddonsActivity.this.refreshABToggle();
              return;
              ((ArrayAdapter)ManageAddonsActivity.this.getListAdapter()).clear();
            }
          }
        });
        refreshABToggle();
      }
      for (;;)
      {
        return true;
        System.err.println("WTF?");
      }
    }
    return false;
  }
  
  protected void onPause()
  {
    super.onPause();
    refreshABToggle();
  }
  
  public void onPrepareDialog(int paramInt, Dialog paramDialog)
  {
    switch (paramInt)
    {
    default: 
      super.onPrepareDialog(paramInt, paramDialog);
      return;
    }
    ((AlertDialog)paramDialog).setTitle(this.selectedAddonItem.toString());
  }
  
  protected void onResume()
  {
    super.onResume();
    refreshABToggle();
  }
  
  protected void onStart()
  {
    super.onStart();
    findAddons();
    refreshABToggle();
  }
  
  protected void refreshABToggle()
  {
    if ((Build.VERSION.SDK_INT >= 11) && (this.master != null)) {
      this.master.setChecked(Utils.getPrefs(0).getBoolean("zz_load_native_addons", false));
    }
  }
  
  protected void setAddonListModified()
  {
    setResult(-1);
  }
  
  public void toggleAddon(AddonListItem paramAddonListItem)
  {
    boolean bool2 = true;
    AddonManager localAddonManager = AddonManager.getAddonManager(this);
    String str = paramAddonListItem.appInfo.packageName;
    if (!paramAddonListItem.enabled)
    {
      bool1 = true;
      localAddonManager.setEnabled(str, bool1);
      if (paramAddonListItem.enabled) {
        break label59;
      }
    }
    label59:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      paramAddonListItem.enabled = bool1;
      afterAddonToggle(paramAddonListItem);
      return;
      bool1 = false;
      break;
    }
  }
  
  @SuppressLint({"DefaultLocale"})
  private final class AddonListComparator
    implements Comparator<ManageAddonsActivity.AddonListItem>
  {
    private AddonListComparator() {}
    
    public int compare(ManageAddonsActivity.AddonListItem paramAddonListItem1, ManageAddonsActivity.AddonListItem paramAddonListItem2)
    {
      return paramAddonListItem1.displayName.toLowerCase().compareTo(paramAddonListItem2.displayName.toLowerCase());
    }
    
    public boolean equals(ManageAddonsActivity.AddonListItem paramAddonListItem1, ManageAddonsActivity.AddonListItem paramAddonListItem2)
    {
      return paramAddonListItem1.displayName.toLowerCase().equals(paramAddonListItem2.displayName.toLowerCase());
    }
  }
  
  private final class AddonListItem
  {
    public final ApplicationInfo appInfo;
    public String displayName;
    public boolean enabled = true;
    
    public AddonListItem(ApplicationInfo paramApplicationInfo, boolean paramBoolean)
    {
      this.appInfo = paramApplicationInfo;
      this.displayName = paramApplicationInfo.packageName;
      this.enabled = paramBoolean;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder().append(this.displayName);
      if (this.enabled) {}
      for (String str = ManageAddonsActivity.enabledString;; str = ManageAddonsActivity.disabledString) {
        return str;
      }
    }
  }
}
