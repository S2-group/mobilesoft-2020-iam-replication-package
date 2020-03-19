package fi.polar.polarflow.activity.main.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.j;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import fi.polar.polarflow.data.EntityManager;
import fi.polar.polarflow.data.User;
import fi.polar.polarflow.data.smartnotifications.SmartNotificationApp;
import fi.polar.polarflow.data.smartnotifications.SmartNotificationAppList;
import fi.polar.polarflow.util.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlockAppsActivity
  extends fi.polar.polarflow.activity.a
{
  private static a p;
  private static j q;
  private ListView n;
  private List<SmartNotificationApp> o;
  private BroadcastReceiver r = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ((paramAnonymousIntent.getAction().equals("fi.polar.polarflow.activity.main.settings.INTENT_BLOCK_ALL_CHANGED")) && (paramAnonymousIntent.hasExtra("fi.polar.polarflow.activity.main.settings.EXTRA_BLOCK_ALL_CHECKED")))
      {
        boolean bool = paramAnonymousIntent.getBooleanExtra("fi.polar.polarflow.activity.main.settings.EXTRA_BLOCK_ALL_CHECKED", false);
        paramAnonymousContext = new StringBuilder();
        paramAnonymousContext.append("Block all checked changed: ");
        paramAnonymousContext.append(bool);
        i.c("BlockAppsActivity", paramAnonymousContext.toString());
        ((CheckBox)BlockAppsActivity.this.findViewById(2131296411)).setChecked(bool);
      }
    }
  };
  
  public BlockAppsActivity() {}
  
  private void d()
  {
    Iterator localIterator = this.o.iterator();
    while (localIterator.hasNext()) {
      if (!((SmartNotificationApp)localIterator.next()).isBlocked()) {
        return;
      }
    }
    ((CheckBox)findViewById(2131296411)).setChecked(true);
  }
  
  public void c()
  {
    p = new a(this, 2131427394, this.o);
    this.n.setAdapter(p);
    this.n.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (CheckBox)paramAnonymousView.findViewById(2131296414);
        paramAnonymousView = (SmartNotificationApp)BlockAppsActivity.a(BlockAppsActivity.this).get(paramAnonymousInt);
        boolean bool = paramAnonymousView.isBlocked() ^ true;
        paramAnonymousView.setIsBlocked(bool, true);
        paramAnonymousAdapterView.setChecked(bool);
      }
    });
  }
  
  public void onBlockAllClicked(View paramView)
  {
    boolean bool = ((CheckBox)paramView).isChecked();
    int i = 0;
    while (i < p.getCount())
    {
      ((SmartNotificationApp)p.getItem(i)).setIsBlocked(bool, false);
      i += 1;
    }
    EntityManager.getCurrentUser().getSmartNotificationAppList().setIsBlockedForAll(bool);
    p.notifyDataSetChanged();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427393);
    q = j.a(this);
    paramBundle = new IntentFilter();
    paramBundle.addAction("fi.polar.polarflow.activity.main.settings.INTENT_BLOCK_ALL_CHANGED");
    q.a(this.r, paramBundle);
    this.o = EntityManager.getCurrentUser().getSmartNotificationAppList().getSmartNotificationAppList();
    paramBundle = getPackageManager().getInstalledApplications(128);
    Object localObject1 = new ArrayList(this.o);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Installed app count: ");
    ((StringBuilder)localObject2).append(paramBundle.size());
    i.a("BlockAppsActivity", ((StringBuilder)localObject2).toString());
    localObject1 = ((List)localObject1).iterator();
    for (;;)
    {
      boolean bool = ((Iterator)localObject1).hasNext();
      int j = 0;
      if (!bool) {
        break;
      }
      localObject2 = (SmartNotificationApp)((Iterator)localObject1).next();
      Object localObject3 = paramBundle.iterator();
      do
      {
        i = j;
        if (!((Iterator)localObject3).hasNext()) {
          break;
        }
      } while (!((ApplicationInfo)((Iterator)localObject3).next()).packageName.equals(((SmartNotificationApp)localObject2).getPackageName()));
      int i = 1;
      if (i == 0)
      {
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append(((SmartNotificationApp)localObject2).getPackageName());
        ((StringBuilder)localObject3).append(" is not installed --> remove");
        i.c("BlockAppsActivity", ((StringBuilder)localObject3).toString());
        this.o.remove(localObject2);
        ((SmartNotificationApp)localObject2).delete();
      }
    }
    paramBundle = new StringBuilder();
    paramBundle.append("App count in block list: ");
    paramBundle.append(this.o.size());
    i.a("BlockAppsActivity", paramBundle.toString());
    if (this.o.size() > 0)
    {
      paramBundle = (TextView)findViewById(2131296596);
      localObject1 = findViewById(2131296594);
      localObject2 = findViewById(2131296597);
      ((View)localObject1).setVisibility(0);
      ((View)localObject2).setVisibility(0);
      paramBundle.setVisibility(0);
      paramBundle.setText(getString(2131625203));
      this.n = ((ListView)findViewById(2131296598));
      this.n.setVisibility(0);
      d();
      c();
      return;
    }
    paramBundle = (TextView)findViewById(2131296595);
    paramBundle.setText(getString(2131625205));
    paramBundle.setVisibility(0);
  }
  
  public void onDestroy()
  {
    q.a(this.r);
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() != 16908332) {
      return super.onOptionsItemSelected(paramMenuItem);
    }
    onBackPressed();
    return true;
  }
  
  protected boolean shouldShowToolBar()
  {
    return true;
  }
}
