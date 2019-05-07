package com.ivymobi.mediation.relaxmusic;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.client.AndroidSdk;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.ivymobi.mediation.relaxmusic.bean.CrossItem;
import com.ivymobi.mediation.relaxmusic.util.Utils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CrossActivity
  extends BaseActivity
{
  private ArrayList<CrossItem> crossItems = null;
  private RecyclerView recyclerView;
  
  public CrossActivity() {}
  
  private void getData()
  {
    this.crossItems = getCrossData(this, AndroidSdk.getExtraData(), "apps");
    MyAdapter localMyAdapter = new MyAdapter();
    LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(this);
    this.recyclerView.setLayoutManager(localLinearLayoutManager);
    this.recyclerView.setAdapter(localMyAdapter);
  }
  
  private boolean isAvilible(Context paramContext, String paramString)
    throws Exception
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private boolean isInstalledApp(String paramString)
  {
    try
    {
      paramString = getPackageManager().getLaunchIntentForPackage(paramString);
      if (paramString != null) {
        return true;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  private void openApp(String paramString)
    throws Exception
  {
    Intent localIntent = getPackageManager().getLaunchIntentForPackage(paramString);
    if (localIntent != null)
    {
      startActivity(localIntent);
      return;
    }
    Utils.openPlayStore(this, paramString);
  }
  
  public ArrayList<CrossItem> getCrossData(Context paramContext, String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramString1 != null) && (paramString2 != null))
    {
      if (paramContext == null) {
        return localArrayList;
      }
      try
      {
        paramContext = new JSONObject(paramString1).getJSONArray(paramString2);
        int i = 0;
        while (i < paramContext.length())
        {
          paramString1 = paramContext.getJSONObject(i);
          paramString2 = paramString1.optString("package");
          if ((paramString2 != null) && (!paramString2.isEmpty()) && (!paramString2.equals(getPackageName())))
          {
            CrossItem localCrossItem = new CrossItem();
            localCrossItem.setPkgName(paramString2);
            localCrossItem.setIconUrl(paramString1.optString("icon"));
            localCrossItem.setHeadUrl(paramString1.optString("url"));
            localCrossItem.setAction(paramString1.optString("action"));
            localCrossItem.setName(paramString1.optString("name"));
            localCrossItem.setDesc(paramString1.optString("desc"));
            localArrayList.add(localCrossItem);
          }
          i += 1;
        }
        return localArrayList;
      }
      catch (IndexOutOfBoundsException paramContext)
      {
        paramContext.printStackTrace();
        return localArrayList;
      }
      catch (NullPointerException paramContext)
      {
        paramContext.printStackTrace();
        return localArrayList;
      }
      catch (JSONException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return localArrayList;
  }
  
  public void onButtonBack(View paramView)
  {
    finish();
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361818);
    this.recyclerView = ((RecyclerView)findViewById(2131230865));
    getData();
  }
  
  class MyAdapter
    extends RecyclerView.Adapter<MyViewHolder>
  {
    MyAdapter() {}
    
    public int getItemCount()
    {
      if (CrossActivity.this.crossItems == null) {
        return 0;
      }
      return CrossActivity.this.crossItems.size();
    }
    
    public void onBindViewHolder(MyViewHolder paramMyViewHolder, int paramInt)
    {
      final CrossItem localCrossItem = (CrossItem)CrossActivity.this.crossItems.get(paramInt);
      Glide.with(paramMyViewHolder.cover.getContext()).load(Uri.parse(localCrossItem.getIconUrl())).into(paramMyViewHolder.cover);
      paramMyViewHolder.title.setText(localCrossItem.getName());
      paramMyViewHolder.subtitle.setText(localCrossItem.getDesc());
      paramMyViewHolder.action.setText(localCrossItem.getAction());
      final boolean bool = CrossActivity.this.isInstalledApp(localCrossItem.getPkgName());
      if (bool)
      {
        paramMyViewHolder.action.setText("OPEN");
        paramMyViewHolder.action.setSelected(true);
      }
      else
      {
        paramMyViewHolder.action.setText(localCrossItem.getAction());
        paramMyViewHolder.action.setSelected(false);
      }
      paramMyViewHolder.rootView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (bool) {
            try
            {
              CrossActivity.this.openApp(localCrossItem.getPkgName());
              return;
            }
            catch (Exception paramAnonymousView)
            {
              paramAnonymousView.printStackTrace();
              return;
            }
          }
          Utils.openPlayStore(paramAnonymousView.getContext(), localCrossItem.getPkgName());
        }
      });
    }
    
    public MyViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
      return new MyViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(2131361821, paramViewGroup, false));
    }
    
    class MyViewHolder
      extends RecyclerView.ViewHolder
    {
      public TextView action;
      public ImageView cover;
      public RelativeLayout rootView;
      public TextView subtitle;
      public TextView title;
      
      public MyViewHolder(View paramView)
      {
        super();
        this.rootView = ((RelativeLayout)paramView.findViewById(2131230870));
        this.cover = ((ImageView)paramView.findViewById(2131230770));
        this.title = ((TextView)paramView.findViewById(2131230924));
        this.subtitle = ((TextView)paramView.findViewById(2131230912));
        this.action = ((TextView)paramView.findViewById(2131230726));
      }
    }
  }
}
