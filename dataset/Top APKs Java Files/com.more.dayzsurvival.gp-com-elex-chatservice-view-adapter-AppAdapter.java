package com.elex.chatservice.view.adapter;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import com.elex.chatservice.model.ApplicationItem;
import com.elex.chatservice.model.ChannelListItem;
import com.elex.chatservice.model.ChatChannel;
import com.elex.chatservice.model.MailManager;
import com.elex.chatservice.view.ChannelListActivity;
import com.elex.chatservice.view.ChannelListFragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppAdapter
  extends AbstractMailListAdapter
{
  public ArrayList<ChannelListItem> allAppInfo = new ArrayList();
  private ChatChannel parentChannel;
  
  public AppAdapter(ChannelListActivity paramChannelListActivity, ChannelListFragment paramChannelListFragment, ChatChannel paramChatChannel)
  {
    super(paramChannelListActivity, paramChannelListFragment);
    this.parentChannel = paramChatChannel;
    reloadData();
  }
  
  public void destroy()
  {
    this.allAppInfo.clear();
    this.allAppInfo = null;
    super.destroy();
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = super.getView(paramInt, paramView, paramViewGroup);
    paramViewGroup = (CategoryViewHolder)paramView.getTag();
    ApplicationItem localApplicationItem = (ApplicationItem)getItem(paramInt);
    ApplicationInfo localApplicationInfo = localApplicationItem.appInfo;
    int i = MailManager.getColorByChannelId(this.parentChannel.channelID);
    paramViewGroup.setContent(this.context, localApplicationItem, localApplicationItem.showUreadAsText, localApplicationInfo.loadIcon(this.context.getPackageManager()), (String)localApplicationInfo.loadLabel(this.context.getPackageManager()), localApplicationItem.summary, localApplicationItem.time, this.fragment.isInEditMode(), paramInt, i, localApplicationItem.failTime);
    refreshMenu();
    return paramView;
  }
  
  public boolean hasMoreData()
  {
    return this.allAppInfo.size() > 0;
  }
  
  public void loadMoreData()
  {
    int i = 9;
    for (;;)
    {
      try
      {
        if (this.allAppInfo.size() > 9)
        {
          break label81;
          if (j < i)
          {
            this.list.add(this.allAppInfo.remove(0));
            j += 1;
            continue;
          }
        }
        else
        {
          i = this.allAppInfo.size();
          break label81;
        }
        this.fragment.refreshScrollLoadEnabled();
        this.fragment.onLoadMoreComplete();
        return;
      }
      finally {}
      label81:
      int j = 0;
    }
  }
  
  public void reloadData()
  {
    this.list.clear();
    Iterator localIterator = this.context.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      ApplicationItem localApplicationItem = new ApplicationItem((ApplicationInfo)localIterator.next());
      this.allAppInfo.add(localApplicationItem);
    } while (this.allAppInfo.size() <= 35);
    loadMoreData();
    refreshOrder();
  }
}
