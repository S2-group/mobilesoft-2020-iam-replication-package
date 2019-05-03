package cn.imengya.htwatch.ui.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.imengya.htwatch.ancs.NoticeStatus;
import cn.imengya.htwatch.db.dao.NoticeStatusDao;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.daimajia.swipe.util.Attributes.Mode;
import com.htsmart.wristband.app.ui.base.AppToolbarActivity;
import java.util.ArrayList;
import java.util.List;

public class OtherAppListActivity
  extends AppToolbarActivity
{
  private ListViewAdapter mAdapter;
  private List<PackageInfo> mApps = new ArrayList();
  private ListView mListView;
  
  public OtherAppListActivity() {}
  
  private void initView()
  {
    this.mListView = ((ListView)findViewById(2131296596));
    this.mAdapter = new ListViewAdapter();
    this.mListView.setAdapter(this.mAdapter);
    this.mAdapter.setMode(Attributes.Mode.Single);
    updateDatas();
    findViewById(2131296304).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        OtherAppListActivity.this.startActivityForResult(new Intent(OtherAppListActivity.this, OtherAppAddActivity.class), 1);
      }
    });
  }
  
  private void updateDatas()
  {
    this.mApps.clear();
    Object localObject = getPackageManager();
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    while (i < ((List)localObject).size())
    {
      PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
      if ((!NoticeStatus.ignoreApp(localPackageInfo.packageName)) && (NoticeStatusDao.getInstance().isOpen(localPackageInfo.packageName))) {
        this.mApps.add(localPackageInfo);
      }
      i += 1;
    }
    this.mAdapter.notifyDataSetChanged();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 1) && (paramInt2 == -1)) {
      updateDatas();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427376);
    initView();
  }
  
  public int toolbarTitleRes()
  {
    return 2131755527;
  }
  
  public class ListViewAdapter
    extends BaseSwipeAdapter
  {
    public ListViewAdapter() {}
    
    public void fillValues(final int paramInt, View paramView)
    {
      ImageView localImageView = (ImageView)paramView.findViewById(2131296520);
      TextView localTextView = (TextView)paramView.findViewById(2131296653);
      paramView.findViewById(2131296424).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = (PackageInfo)OtherAppListActivity.this.mApps.get(paramInt);
          OtherAppListActivity.this.mApps.remove(paramAnonymousView);
          NoticeStatusDao.getInstance().deleteById(paramAnonymousView.packageName);
          OtherAppListActivity.ListViewAdapter.this.notifyDataSetChanged();
          OtherAppListActivity.ListViewAdapter.this.closeAllItems();
        }
      });
      paramView = (PackageInfo)OtherAppListActivity.this.mApps.get(paramInt);
      localImageView.setImageDrawable(paramView.applicationInfo.loadIcon(OtherAppListActivity.this.getPackageManager()));
      localTextView.setText(paramView.applicationInfo.loadLabel(OtherAppListActivity.this.getPackageManager()));
    }
    
    public View generateView(int paramInt, ViewGroup paramViewGroup)
    {
      return OtherAppListActivity.this.getLayoutInflater().inflate(2131427508, paramViewGroup, false);
    }
    
    public int getCount()
    {
      return OtherAppListActivity.this.mApps.size();
    }
    
    public Object getItem(int paramInt)
    {
      return Integer.valueOf(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public int getSwipeLayoutResourceId(int paramInt)
    {
      return 2131296920;
    }
  }
}
