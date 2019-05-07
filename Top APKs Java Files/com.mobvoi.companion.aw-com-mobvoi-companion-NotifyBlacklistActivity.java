package com.mobvoi.companion;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import mms.enm;
import mms.eob;
import mms.exw;
import mms.eyf;

public class NotifyBlacklistActivity
  extends enm
{
  private List<eob> mApps;
  private List<eob> mBlackApps;
  private TextView mLoadingTv;
  private RecyclerView mRecyclerView;
  
  public NotifyBlacklistActivity() {}
  
  private void initData()
  {
    this.mApps = new ArrayList();
    this.mBlackApps = new ArrayList();
    new UpdateAppInfoTask().execute(new Void[0]);
  }
  
  private void initView()
  {
    setTitle(R.string.notification_blacklist_title);
    this.mLoadingTv = ((TextView)findViewById(R.id.loading));
    this.mRecyclerView = ((RecyclerView)findViewById(R.id.list));
    this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
  }
  
  private void sortAppList(List<eob> paramList)
  {
    Collections.sort(paramList, new Comparator()
    {
      public int compare(eob paramAnonymousEob1, eob paramAnonymousEob2)
      {
        return eyf.a(paramAnonymousEob1.a).compareTo(eyf.a(paramAnonymousEob2.a));
      }
    });
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_blacklist_dialog);
    initView();
    initData();
  }
  
  class BlacklistAdapter
    extends RecyclerView.Adapter<NotifyBlacklistActivity.ViewHolder>
  {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_SECTION = 1;
    private final Context mContext;
    
    public BlacklistAdapter(Context paramContext)
    {
      this.mContext = paramContext;
    }
    
    private boolean isBlackSection(int paramInt)
    {
      return paramInt < NotifyBlacklistActivity.this.mBlackApps.size();
    }
    
    private void onAddBlackApps(eob paramEob)
    {
      NotifyBlacklistActivity.this.mBlackApps.add(paramEob);
      NotifyBlacklistActivity.this.mApps.remove(paramEob);
      NotificationDataSetting.addAppToBlackList(NotifyBlacklistActivity.this.getApplicationContext(), paramEob.b);
      notifyDataSetChanged();
    }
    
    private void onRemoveBlackApps(eob paramEob)
    {
      NotifyBlacklistActivity.this.mBlackApps.remove(paramEob);
      NotifyBlacklistActivity.this.mApps.add(paramEob);
      NotificationDataSetting.removeAppFromBlackList(NotifyBlacklistActivity.this.getApplicationContext(), paramEob.b);
      notifyDataSetChanged();
    }
    
    public int getItemCount()
    {
      return NotifyBlacklistActivity.this.mApps.size() + NotifyBlacklistActivity.this.mBlackApps.size();
    }
    
    public int getItemViewType(int paramInt)
    {
      if ((paramInt != 0) && (paramInt != NotifyBlacklistActivity.this.mBlackApps.size())) {
        return 0;
      }
      return 1;
    }
    
    public void onBindViewHolder(NotifyBlacklistActivity.ViewHolder paramViewHolder, int paramInt)
    {
      final boolean bool = isBlackSection(paramInt);
      if (bool) {}
      for (Object localObject = NotifyBlacklistActivity.this.mBlackApps.get(paramInt);; localObject = NotifyBlacklistActivity.this.mApps.get(paramInt - NotifyBlacklistActivity.this.mBlackApps.size()))
      {
        localObject = (eob)localObject;
        break;
      }
      if (getItemViewType(paramInt) == 1)
      {
        paramViewHolder.mName.setText(((eob)localObject).a);
        return;
      }
      paramViewHolder.mIcon.setImageDrawable(((eob)localObject).e);
      paramViewHolder.mName.setText(((eob)localObject).a);
      if ((paramInt == NotifyBlacklistActivity.this.mBlackApps.size() - 1) || (paramInt == NotifyBlacklistActivity.this.mBlackApps.size() - 1 + NotifyBlacklistActivity.this.mApps.size())) {
        paramViewHolder.mDivider.setVisibility(8);
      }
      ImageButton localImageButton = paramViewHolder.mAction;
      if (bool) {
        paramInt = R.drawable.ic_appsdown_selector;
      } else {
        paramInt = R.drawable.ic_appsup_selector;
      }
      localImageButton.setBackgroundResource(paramInt);
      paramViewHolder.mAction.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!bool)
          {
            NotifyBlacklistActivity.BlacklistAdapter.this.onAddBlackApps(this.val$info);
            return;
          }
          NotifyBlacklistActivity.BlacklistAdapter.this.onRemoveBlackApps(this.val$info);
        }
      });
      paramViewHolder.mContainer.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!bool)
          {
            NotifyBlacklistActivity.BlacklistAdapter.this.onAddBlackApps(this.val$info);
            return;
          }
          NotifyBlacklistActivity.BlacklistAdapter.this.onRemoveBlackApps(this.val$info);
        }
      });
    }
    
    public NotifyBlacklistActivity.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
      if (paramInt == 1) {
        paramViewGroup = LayoutInflater.from(this.mContext).inflate(R.layout.list_section, paramViewGroup, false);
      } else {
        paramViewGroup = LayoutInflater.from(this.mContext).inflate(R.layout.blacklist_item, paramViewGroup, false);
      }
      return new NotifyBlacklistActivity.ViewHolder(paramViewGroup);
    }
  }
  
  class UpdateAppInfoTask
    extends AsyncTask<Void, Integer, Void>
  {
    UpdateAppInfoTask() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = NotificationDataSetting.getBlacklistPackageName(NotifyBlacklistActivity.this.getApplicationContext());
      List localList = NotifyBlacklistActivity.this.getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        eob localEob = new eob();
        localEob.a = localPackageInfo.applicationInfo.loadLabel(NotifyBlacklistActivity.this.getPackageManager()).toString();
        localEob.b = localPackageInfo.packageName;
        localEob.c = localPackageInfo.versionName;
        localEob.d = localPackageInfo.versionCode;
        localEob.e = localPackageInfo.applicationInfo.loadIcon(NotifyBlacklistActivity.this.getPackageManager());
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
        {
          if (paramVarArgs.contains(localEob.b)) {
            NotifyBlacklistActivity.this.mBlackApps.add(localEob);
          } else {
            NotifyBlacklistActivity.this.mApps.add(localEob);
          }
        }
        else if (paramVarArgs.contains(localEob.b)) {
          NotifyBlacklistActivity.this.mBlackApps.add(localEob);
        } else if ((exw.a(NotifyBlacklistActivity.this)) && (localPackageInfo.applicationInfo.icon != 0) && (!TextUtils.isEmpty(localPackageInfo.packageName)) && (!localPackageInfo.packageName.contains("com.google.android.webview")) && ((localPackageInfo.packageName.contains("com.google.android")) || (localPackageInfo.packageName.contains("email")))) {
          NotifyBlacklistActivity.this.mApps.add(localEob);
        }
        i += 1;
      }
      NotifyBlacklistActivity.this.sortAppList(NotifyBlacklistActivity.this.mBlackApps);
      NotifyBlacklistActivity.this.sortAppList(NotifyBlacklistActivity.this.mApps);
      paramVarArgs = new eob();
      paramVarArgs.a = NotifyBlacklistActivity.this.getString(R.string.app_list);
      paramVarArgs.b = paramVarArgs.a;
      if (NotifyBlacklistActivity.this.mApps.size() > 0) {
        NotifyBlacklistActivity.this.mApps.add(0, paramVarArgs);
      } else {
        NotifyBlacklistActivity.this.mApps.add(paramVarArgs);
      }
      paramVarArgs = new eob();
      paramVarArgs.a = NotifyBlacklistActivity.this.getString(R.string.blocked_apps);
      paramVarArgs.b = paramVarArgs.a;
      if (NotifyBlacklistActivity.this.mBlackApps.size() > 0) {
        NotifyBlacklistActivity.this.mBlackApps.add(0, paramVarArgs);
      } else {
        NotifyBlacklistActivity.this.mBlackApps.add(paramVarArgs);
      }
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      NotifyBlacklistActivity.this.mRecyclerView.setVisibility(0);
      NotifyBlacklistActivity.this.mLoadingTv.setVisibility(8);
      NotifyBlacklistActivity.this.mRecyclerView.setAdapter(new NotifyBlacklistActivity.BlacklistAdapter(NotifyBlacklistActivity.this, NotifyBlacklistActivity.this.getApplicationContext()));
    }
    
    protected void onPreExecute()
    {
      NotifyBlacklistActivity.this.mRecyclerView.setVisibility(8);
      NotifyBlacklistActivity.this.mLoadingTv.setVisibility(0);
      NotifyBlacklistActivity.this.mLoadingTv.setText(NotifyBlacklistActivity.this.getResources().getString(R.string.app_list_string));
    }
    
    protected void onProgressUpdate(Integer... paramVarArgs) {}
  }
  
  static class ViewHolder
    extends RecyclerView.ViewHolder
  {
    ImageButton mAction;
    View mContainer;
    View mDivider;
    ImageView mIcon;
    TextView mName;
    
    public ViewHolder(View paramView)
    {
      super();
      this.mContainer = paramView;
      this.mIcon = ((ImageView)paramView.findViewById(R.id.icon));
      this.mName = ((TextView)paramView.findViewById(R.id.name));
      this.mAction = ((ImageButton)paramView.findViewById(R.id.action));
      this.mDivider = paramView.findViewById(R.id.divider);
    }
  }
}
