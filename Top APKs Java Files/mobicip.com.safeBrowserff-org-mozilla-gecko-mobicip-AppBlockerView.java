package org.mozilla.gecko.mobicip;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import mobicip.com.safeBrowser.BlockedAppDB;
import mobicip.com.safeBrowser.MobicipBrowserApplication;

public class AppBlockerView
  extends ListActivity
{
  public static int ALLOW_APP_INSTALL = 3;
  public static int ALLOW_APP_UNINSTALL = 1;
  public static int BLOCK_APP_UNINSTALL = 2;
  public static int DISABLE_PIN = 4;
  private static final int MENU_ALLOW_APP = 3;
  private static final int MENU_BLOCK_APP = 2;
  private static final int VERIFY_PWD_REQ = 1;
  private static final String expAppList = "com.android.launcher,com.mobicip.safeBrowser,mobicip.com.safeBrowser,com.example.android.softkeyboard,com.android.systemui";
  private AppAdapter appAdapter;
  List<ApplicationInfo> appList;
  private TextView empty;
  PackageManager pm;
  
  public AppBlockerView() {}
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Context localContext = getApplicationContext();
    if (paramInt2 != -1) {
      finish();
    }
    Bundle localBundle;
    do
    {
      return;
      localBundle = paramIntent.getExtras();
    } while ((paramInt1 != 1) || (localBundle == null) || (paramIntent.getExtras().getInt("status") == 1));
    Toast.makeText(localContext, "Login Failure", 0).show();
    finish();
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    AdapterView.AdapterContextMenuInfo localAdapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo();
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onContextItemSelected(paramMenuItem);
    case 2: 
      ((AppView)localAdapterContextMenuInfo.targetView).setIsBlocked(1);
      return true;
    }
    ((AppView)localAdapterContextMenuInfo.targetView).setIsBlocked(0);
    return true;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903070);
    this.empty = ((TextView)findViewById(2131165302));
    paramBundle = getListView();
    paramBundle.setChoiceMode(1);
    paramBundle.setItemsCanFocus(false);
    paramBundle.setEmptyView(this.empty);
    registerForContextMenu(getListView());
    this.pm = getPackageManager();
    this.appAdapter = null;
    this.appList = this.pm.getInstalledApplications(128);
    startActivityForResult(new Intent(this, LoginViewCompareWithDB.class), 1);
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
    paramView = (AdapterView.AdapterContextMenuInfo)paramContextMenuInfo;
    paramContextMenu.setHeaderTitle(((AppView)paramView.targetView).sAppName);
    if (((AppView)paramView.targetView).getIsBlocked() == 1)
    {
      paramContextMenu.add(0, 3, 0, 2131427624);
      return;
    }
    paramContextMenu.add(0, 2, 0, 2131427623);
  }
  
  protected void onDestroy()
  {
    MobicipBrowserApplication.me.storeAppList();
    super.onDestroy();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.appList = this.pm.getInstalledApplications(128);
    if (this.appList.size() == 0) {
      this.empty.setText("no Apps to display");
    }
    while (this.appAdapter != null) {
      return;
    }
    List localList = MobicipBrowserApplication.me.getAppList();
    Iterator localIterator = this.appList.iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      BlockedAppDB localBlockedAppDB = new BlockedAppDB(localApplicationInfo.loadLabel(this.pm).toString(), localApplicationInfo.packageName, 0);
      if (!"com.android.launcher,com.mobicip.safeBrowser,mobicip.com.safeBrowser,com.example.android.softkeyboard,com.android.systemui".contains(localApplicationInfo.packageName))
      {
        int i = localList.indexOf(localBlockedAppDB);
        localBlockedAppDB.setIcon(new ScaleDrawable(localApplicationInfo.loadIcon(this.pm), 0, 30.0F, 30.0F));
        if (i == -1) {
          localList.add(localBlockedAppDB);
        }
      }
    }
    this.appAdapter = new AppAdapter(this, localList);
    setListAdapter(this.appAdapter);
  }
  
  class AppAdapter
    extends BaseAdapter
  {
    private final List<BlockedAppDB> appList;
    private final Context mContext;
    
    public AppAdapter(List<BlockedAppDB> paramList)
    {
      this.mContext = paramList;
      Object localObject;
      this.appList = localObject;
    }
    
    public int getCount()
    {
      return this.appList.size();
    }
    
    public Object getItem(int paramInt)
    {
      return this.appList.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramView = (BlockedAppDB)this.appList.get(paramInt);
      return new AppBlockerView.AppView(AppBlockerView.this, this.mContext, paramView);
    }
  }
  
  class AppView
    extends RelativeLayout
  {
    private TextView appName;
    private TextView blocked;
    private BlockedAppDB dbObj;
    private ImageView icon;
    private int isBlocked;
    private RelativeLayout layout;
    private Context mContext;
    private TextView packageName;
    public String sAppName;
    
    public AppView(Context paramContext)
    {
      super();
      this.isBlocked = 0;
    }
    
    public AppView(Context paramContext, BlockedAppDB paramBlockedAppDB)
    {
      super();
      this.mContext = paramContext;
      this.dbObj = paramBlockedAppDB;
      this.isBlocked = paramBlockedAppDB.getBlocked();
      LayoutInflater.from(paramContext).inflate(2130903046, this, true);
      this.appName = ((TextView)findViewById(2131165206));
      this.packageName = ((TextView)findViewById(2131165213));
      this.blocked = ((TextView)findViewById(2131165214));
      this.layout = ((RelativeLayout)findViewById(2131165340));
      this.icon = ((ImageView)findViewById(2131165212));
      this.appName.setText(paramBlockedAppDB.getName());
      this.sAppName = paramBlockedAppDB.getName();
      this.packageName.setText(paramBlockedAppDB.getPkg());
      this.appName.setTextColor(-1);
      this.blocked.setTextColor(-16776961);
      this.blocked.setText("Blocked ");
      if (this.isBlocked == 0) {
        this.blocked.setVisibility(4);
      }
      if (paramBlockedAppDB.getIcon() == null)
      {
        this.icon.setVisibility(4);
        return;
      }
      this.icon.setImageDrawable(paramBlockedAppDB.getIcon());
    }
    
    public BlockedAppDB getDbObj()
    {
      return this.dbObj;
    }
    
    public int getIsBlocked()
    {
      return this.isBlocked;
    }
    
    public void setDate(long paramLong)
    {
      this.packageName.setText(new Date(paramLong).toString());
    }
    
    public void setDbObj(BlockedAppDB paramBlockedAppDB)
    {
      this.dbObj = paramBlockedAppDB;
      this.appName.setText(paramBlockedAppDB.getName());
      this.packageName.setText(paramBlockedAppDB.getPkg());
    }
    
    public void setIsBlocked(int paramInt)
    {
      this.isBlocked = paramInt;
      if (this.isBlocked == 1) {
        this.blocked.setVisibility(0);
      }
      for (;;)
      {
        this.dbObj.setBlocked(this.isBlocked);
        return;
        this.blocked.setVisibility(4);
      }
    }
    
    public void setUrl(String paramString)
    {
      this.appName.setText(paramString);
    }
    
    class AppOnClickListener
      implements View.OnClickListener
    {
      AppOnClickListener() {}
      
      public void onClick(View paramView) {}
    }
  }
}
