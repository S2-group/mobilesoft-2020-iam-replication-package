package org.holylobster.nuntius.activity;

import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import java.util.Collections;
import java.util.List;
import org.holylobster.nuntius.adapter.AppBlacklistAdapter;
import org.holylobster.nuntius.adapter.AppBlacklistAdapter.OnItemClickListener;
import org.holylobster.nuntius.data.BlacklistedApp;

public class AddApplicationBlacklist
  extends AppCompatActivity
{
  private AppBlacklistAdapter adapter;
  private BlacklistedApp blacklistedApp;
  private RecyclerView.LayoutManager layoutManager;
  private List<ApplicationInfo> packages;
  private PackageManager pm;
  private RecyclerView recyclerView;
  private Toolbar toolbar;
  
  public AddApplicationBlacklist() {}
  
  public void addToBlacklist(int paramInt)
  {
    this.blacklistedApp.add((ApplicationInfo)this.packages.get(paramInt));
    showInfo(paramInt);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427356);
    this.toolbar = ((Toolbar)findViewById(2131230856));
    setSupportActionBar(this.toolbar);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setTitle(getResources().getString(2131558435));
    this.pm = getPackageManager();
    this.recyclerView = ((RecyclerView)findViewById(2131230800));
    this.recyclerView.setHasFixedSize(true);
    this.layoutManager = new LinearLayoutManager(this);
    this.recyclerView.setLayoutManager(this.layoutManager);
    new GetAllInstalledApp().execute(new Void[0]);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public void onResume()
  {
    super.onResume();
    this.blacklistedApp = new BlacklistedApp(this);
  }
  
  public void showInfo(int paramInt)
  {
    SnackbarManager.show(Snackbar.with(getApplicationContext()).text(getString(2131558431, new Object[] { this.pm.getApplicationLabel((ApplicationInfo)this.packages.get(paramInt)) })), this);
  }
  
  class GetAllInstalledApp
    extends AsyncTask<Void, Integer, String>
  {
    GetAllInstalledApp() {}
    
    protected String doInBackground(Void... paramVarArgs)
    {
      AddApplicationBlacklist.access$002(AddApplicationBlacklist.this, AddApplicationBlacklist.this.pm.getInstalledApplications(128));
      Collections.sort(AddApplicationBlacklist.this.packages, new ApplicationInfo.DisplayNameComparator(AddApplicationBlacklist.this.pm));
      return "";
    }
    
    protected void onPostExecute(String paramString)
    {
      ((LinearLayout)AddApplicationBlacklist.this.findViewById(2131230757)).setVisibility(8);
      AddApplicationBlacklist.access$202(AddApplicationBlacklist.this, new AppBlacklistAdapter(AddApplicationBlacklist.this.getApplicationContext(), AddApplicationBlacklist.this.packages, false));
      AddApplicationBlacklist.this.recyclerView.setAdapter(AddApplicationBlacklist.this.adapter);
      AddApplicationBlacklist.this.adapter.SetOnItemClickListener(new AppBlacklistAdapter.OnItemClickListener()
      {
        public void onItemClick(View paramAnonymousView, int paramAnonymousInt)
        {
          AddApplicationBlacklist.this.addToBlacklist(paramAnonymousInt);
        }
      });
    }
  }
}
