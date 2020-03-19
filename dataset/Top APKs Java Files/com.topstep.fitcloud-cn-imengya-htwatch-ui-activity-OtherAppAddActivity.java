package cn.imengya.htwatch.ui.activity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.imengya.basic.utils.FastViewHolder;
import cn.imengya.htwatch.ancs.NoticeStatus;
import cn.imengya.htwatch.db.dao.NoticeStatusDao;
import com.htsmart.wristband.app.ui.base.AppToolbarActivity;
import java.util.ArrayList;
import java.util.List;

public class OtherAppAddActivity
  extends AppToolbarActivity
{
  private ListViewAdapter mAdapter;
  private List<PackageInfo> mApps = new ArrayList();
  private ListView mListView;
  
  public OtherAppAddActivity() {}
  
  private void initView()
  {
    this.mListView = ((ListView)findViewById(2131296596));
    this.mAdapter = new ListViewAdapter();
    this.mListView.setAdapter(this.mAdapter);
    updateDatas();
    this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = new NoticeStatus();
        paramAnonymousAdapterView.setId(((PackageInfo)OtherAppAddActivity.this.mApps.get(paramAnonymousInt)).packageName);
        paramAnonymousAdapterView.setOpen(true);
        NoticeStatusDao.getInstance().createOrUpdate(paramAnonymousAdapterView);
        OtherAppAddActivity.this.setResult(-1);
        OtherAppAddActivity.this.finish();
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
      if ((!NoticeStatus.ignoreApp(localPackageInfo.packageName)) && (!NoticeStatusDao.getInstance().isOpen(localPackageInfo.packageName))) {
        this.mApps.add(localPackageInfo);
      }
      i += 1;
    }
    this.mAdapter.notifyDataSetChanged();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427473);
    initView();
  }
  
  public int toolbarTitleRes()
  {
    return 2131755971;
  }
  
  public class ListViewAdapter
    extends BaseAdapter
  {
    public ListViewAdapter() {}
    
    public int getCount()
    {
      return OtherAppAddActivity.this.mApps.size();
    }
    
    public Object getItem(int paramInt)
    {
      return Integer.valueOf(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      View localView = paramView;
      if (paramView == null) {
        localView = OtherAppAddActivity.this.getLayoutInflater().inflate(2131427507, paramViewGroup, false);
      }
      paramView = (ImageView)FastViewHolder.get(localView, 2131296520);
      paramViewGroup = (TextView)FastViewHolder.get(localView, 2131296653);
      PackageInfo localPackageInfo = (PackageInfo)OtherAppAddActivity.this.mApps.get(paramInt);
      paramView.setImageDrawable(localPackageInfo.applicationInfo.loadIcon(OtherAppAddActivity.this.getPackageManager()));
      paramViewGroup.setText(localPackageInfo.applicationInfo.loadLabel(OtherAppAddActivity.this.getPackageManager()));
      return localView;
    }
  }
}
