package com.desarrollodroide.repos.repositorios.swipelistview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView.MultiChoiceModeListener;
import com.fortysevendeg.android.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.android.swipelistview.SwipeListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwipeListViewExampleActivity
  extends FragmentActivity
{
  private static final int REQUEST_CODE_SETTINGS = 0;
  private PackageAdapter adapter;
  private List<PackageItem> data;
  private ProgressDialog progressDialog;
  private SwipeListView swipeListView;
  
  public SwipeListViewExampleActivity() {}
  
  private void reload()
  {
    SettingsManager localSettingsManager = SettingsManager.getInstance();
    this.swipeListView.setSwipeMode(localSettingsManager.getSwipeMode());
    this.swipeListView.setSwipeActionLeft(localSettingsManager.getSwipeActionLeft());
    this.swipeListView.setSwipeActionRight(localSettingsManager.getSwipeActionRight());
    this.swipeListView.setOffsetLeft(convertDpToPixel(localSettingsManager.getSwipeOffsetLeft()));
    this.swipeListView.setOffsetRight(convertDpToPixel(localSettingsManager.getSwipeOffsetRight()));
    this.swipeListView.setAnimationTime(localSettingsManager.getSwipeAnimationTime());
    this.swipeListView.setSwipeOpenOnLongPress(localSettingsManager.isSwipeOpenOnLongPress());
  }
  
  public int convertDpToPixel(float paramFloat)
  {
    return (int)(paramFloat * (getResources().getDisplayMetrics().densityDpi / 160.0F));
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default: 
      return;
    }
    reload();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903703);
    this.data = new ArrayList();
    this.adapter = new PackageAdapter(this, this.data);
    this.swipeListView = ((SwipeListView)findViewById(2131428483));
    this.swipeListView.setChoiceMode(3);
    if (Build.VERSION.SDK_INT >= 11) {
      this.swipeListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener()
      {
        public boolean onActionItemClicked(ActionMode paramAnonymousActionMode, MenuItem paramAnonymousMenuItem)
        {
          switch (paramAnonymousMenuItem.getItemId())
          {
          default: 
            return false;
          }
          SwipeListViewExampleActivity.this.swipeListView.dismissSelected();
          paramAnonymousActionMode.finish();
          return true;
        }
        
        public boolean onCreateActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
        {
          paramAnonymousActionMode.getMenuInflater().inflate(2131886115, paramAnonymousMenu);
          return true;
        }
        
        public void onDestroyActionMode(ActionMode paramAnonymousActionMode)
        {
          SwipeListViewExampleActivity.this.swipeListView.unselectedChoiceStates();
        }
        
        public void onItemCheckedStateChanged(ActionMode paramAnonymousActionMode, int paramAnonymousInt, long paramAnonymousLong, boolean paramAnonymousBoolean)
        {
          paramAnonymousActionMode.setTitle("Selected (" + SwipeListViewExampleActivity.this.swipeListView.getCountSelected() + ")");
        }
        
        public boolean onPrepareActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
        {
          return false;
        }
      });
    }
    this.swipeListView.setSwipeListViewListener(new BaseSwipeListViewListener()
    {
      public void onClickBackView(int paramAnonymousInt)
      {
        Log.d("swipe", String.format("onClickBackView %d", new Object[] { Integer.valueOf(paramAnonymousInt) }));
      }
      
      public void onClickFrontView(int paramAnonymousInt)
      {
        Log.d("swipe", String.format("onClickFrontView %d", new Object[] { Integer.valueOf(paramAnonymousInt) }));
      }
      
      public void onClosed(int paramAnonymousInt, boolean paramAnonymousBoolean) {}
      
      public void onDismiss(int[] paramAnonymousArrayOfInt)
      {
        int j = paramAnonymousArrayOfInt.length;
        int i = 0;
        for (;;)
        {
          if (i >= j)
          {
            SwipeListViewExampleActivity.this.adapter.notifyDataSetChanged();
            return;
          }
          int k = paramAnonymousArrayOfInt[i];
          SwipeListViewExampleActivity.this.data.remove(k);
          i += 1;
        }
      }
      
      public void onListChanged() {}
      
      public void onMove(int paramAnonymousInt, float paramAnonymousFloat) {}
      
      public void onOpened(int paramAnonymousInt, boolean paramAnonymousBoolean) {}
      
      public void onStartClose(int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        Log.d("swipe", String.format("onStartClose %d", new Object[] { Integer.valueOf(paramAnonymousInt) }));
      }
      
      public void onStartOpen(int paramAnonymousInt1, int paramAnonymousInt2, boolean paramAnonymousBoolean)
      {
        Log.d("swipe", String.format("onStartOpen %d - action %d", new Object[] { Integer.valueOf(paramAnonymousInt1), Integer.valueOf(paramAnonymousInt2) }));
      }
    });
    this.swipeListView.setAdapter(this.adapter);
    reload();
    new ListAppTask().execute(new Void[0]);
    this.progressDialog = new ProgressDialog(this);
    this.progressDialog.setMessage(getString(2131230750));
    this.progressDialog.setCancelable(false);
    this.progressDialog.show();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131886114, paramMenu);
    return true;
  }
  
  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    case 16908332: 
      finish();
      return false;
    }
    startActivityForResult(new Intent(this, SettingsActivity.class), 0);
    return false;
  }
  
  public class ListAppTask
    extends AsyncTask<Void, Void, List<PackageItem>>
  {
    public ListAppTask() {}
    
    protected List<PackageItem> doInBackground(Void... paramVarArgs)
    {
      Object localObject = SwipeListViewExampleActivity.this.getPackageManager();
      paramVarArgs = ((PackageManager)localObject).getInstalledApplications(0);
      Collections.sort(paramVarArgs, new ApplicationInfo.DisplayNameComparator((PackageManager)localObject));
      localObject = new ArrayList();
      int i = 0;
      for (;;)
      {
        if (i >= paramVarArgs.size()) {
          return localObject;
        }
        try
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)paramVarArgs.get(i);
          if ((localApplicationInfo.flags != 1) && (localApplicationInfo.enabled) && (localApplicationInfo.icon != 0))
          {
            PackageItem localPackageItem = new PackageItem();
            localPackageItem.setName(SwipeListViewExampleActivity.this.getPackageManager().getApplicationLabel(localApplicationInfo).toString());
            localPackageItem.setPackageName(localApplicationInfo.packageName);
            localPackageItem.setIcon(SwipeListViewExampleActivity.this.getPackageManager().getDrawable(localApplicationInfo.packageName, localApplicationInfo.icon, localApplicationInfo));
            ((List)localObject).add(localPackageItem);
          }
          i += 1;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }
    
    protected void onPostExecute(List<PackageItem> paramList)
    {
      SwipeListViewExampleActivity.this.data.clear();
      SwipeListViewExampleActivity.this.data.addAll(paramList);
      SwipeListViewExampleActivity.this.adapter.notifyDataSetChanged();
      if (SwipeListViewExampleActivity.this.progressDialog != null)
      {
        SwipeListViewExampleActivity.this.progressDialog.dismiss();
        SwipeListViewExampleActivity.this.progressDialog = null;
      }
      if (PreferencesManager.getInstance(SwipeListViewExampleActivity.this).getShowAbout()) {
        new AboutDialog().show(SwipeListViewExampleActivity.this.getSupportFragmentManager(), "dialog");
      }
    }
  }
}
