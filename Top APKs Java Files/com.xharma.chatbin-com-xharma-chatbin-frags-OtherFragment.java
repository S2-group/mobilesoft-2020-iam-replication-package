package com.xharma.chatbin.frags;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.xharma.chatbin.activity.AdminSettingsActivity;
import com.xharma.chatbin.activity.OtherChatActivity;
import com.xharma.chatbin.adapter.ChatFragListAdapter;
import com.xharma.chatbin.common.CommonMethods;
import com.xharma.chatbin.common.PrefManager;
import com.xharma.chatbin.common.SQLiteHelper;
import com.xharma.chatbin.entity.ChatEntity;
import com.xharma.chatbin.service.NotificationService;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OtherFragment
  extends Fragment
  implements SwipeRefreshLayout.OnRefreshListener
{
  private ChatFragListAdapter adapter;
  private Map<String, Drawable> appMap;
  String blank = "Click on floating icon (+) to add app";
  Boolean blankFlag;
  private ChatEntity chatEntity;
  private CommonMethods commonMethods;
  private String from = null;
  private ListView list;
  private ArrayList<String> modelList;
  private HashMap<String, byte[]> picMap;
  private PrefManager prefManager;
  private SQLiteHelper sqLiteHelper;
  SwipeRefreshLayout swipeLayout;
  private TextView tempText;
  
  public OtherFragment() {}
  
  private void alertClear()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity(), 2131689644);
    localBuilder.setTitle("Do you want to clear all messages?");
    localBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        OtherFragment.this.sqLiteHelper.clearOAllRecords();
        OtherFragment.this.refreshList("clear");
      }
    });
    localBuilder.show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setHasOptionsMenu(true);
    setRetainInstance(true);
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    super.onCreateOptionsMenu(paramMenu, paramMenuInflater);
    getActivity().getMenuInflater().inflate(2131492865, paramMenu);
    getActivity().getMenuInflater().inflate(2131492867, paramMenu);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427403, paramViewGroup, false);
    this.modelList = new ArrayList();
    this.blankFlag = Boolean.valueOf(false);
    this.sqLiteHelper = new SQLiteHelper(getActivity());
    this.commonMethods = new CommonMethods();
    this.chatEntity = new ChatEntity();
    this.prefManager = new PrefManager(getActivity());
    this.tempText = ((TextView)paramLayoutInflater.findViewById(2131296553));
    this.picMap = new HashMap();
    this.list = ((ListView)paramLayoutInflater.findViewById(2131296441));
    this.swipeLayout = ((SwipeRefreshLayout)paramLayoutInflater.findViewById(2131296548));
    this.swipeLayout.setOnRefreshListener(this);
    this.swipeLayout.setColorScheme(new int[] { 17170459, 17170452, 17170456, 17170454 });
    this.appMap = new HashMap();
    paramViewGroup = getActivity().getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramViewGroup.size())
    {
      paramBundle = (PackageInfo)paramViewGroup.get(i);
      if ((0x1 & paramBundle.applicationInfo.flags) == 0)
      {
        String str = paramBundle.applicationInfo.packageName;
        this.appMap.put(str, paramBundle.applicationInfo.loadIcon(getActivity().getPackageManager()));
      }
      i += 1;
    }
    this.modelList = this.sqLiteHelper.getOSenderDetails();
    this.picMap = this.sqLiteHelper.getOPicMap();
    if ((this.modelList != null) && (this.modelList.size() != 0))
    {
      this.tempText.setVisibility(8);
      this.blankFlag = Boolean.valueOf(false);
    }
    else
    {
      this.tempText.setText(this.blank);
      this.blankFlag = Boolean.valueOf(true);
    }
    this.adapter = new ChatFragListAdapter(getActivity(), this.modelList, this.picMap, this.appMap);
    this.list.setAdapter(this.adapter);
    this.list.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (!OtherFragment.this.blankFlag.booleanValue())
        {
          paramAnonymousView = new Intent(OtherFragment.this.getActivity(), OtherChatActivity.class);
          if (((String)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt) != null) && (!"".equals((String)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt))))
          {
            paramAnonymousView.putExtra("senderName", (String)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt));
            paramAnonymousView.putExtra("groupFlag", OtherFragment.this.sqLiteHelper.getGroupFlag((String)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt)));
          }
          OtherFragment.this.startActivity(paramAnonymousView);
          return;
        }
        Toast.makeText(OtherFragment.this.getActivity(), "Chat history not available", 0).show();
      }
    });
    return paramLayoutInflater;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i != 2131296287)
    {
      switch (i)
      {
      default: 
        return super.onOptionsItemSelected(paramMenuItem);
      case 2131296301: 
        startActivity(new Intent(getActivity(), AdminSettingsActivity.class));
        return true;
      }
      try
      {
        refreshList("refresh");
        return true;
      }
      catch (Exception paramMenuItem)
      {
        Calendar localCalendar = Calendar.getInstance();
        Object localObject = System.out;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Current time => ");
        localStringBuilder.append(localCalendar.getTime());
        ((PrintStream)localObject).println(localStringBuilder.toString());
        localObject = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        this.sqLiteHelper.insertExceptionRecord(paramMenuItem.toString(), ((SimpleDateFormat)localObject).format(localCalendar.getTime()));
        return true;
      }
    }
    alertClear();
    return true;
  }
  
  public void onRefresh()
  {
    Log.d("Swipe", "Refreshing Number");
    refreshList("refresh");
  }
  
  public void refreshList(final String paramString)
  {
    this.swipeLayout.setRefreshing(true);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        OtherFragment.this.refreshMainMethod(paramString);
      }
    }, 1000L);
  }
  
  public void refreshMainMethod(String paramString)
  {
    Object localObject = this.sqLiteHelper.getOSenderDetails();
    this.picMap = this.sqLiteHelper.getOPicMap();
    if ((localObject != null) && (((ArrayList)localObject).size() != 0))
    {
      this.tempText.setVisibility(8);
      this.blankFlag = Boolean.valueOf(false);
    }
    else
    {
      this.tempText.setText(this.blank);
      this.blankFlag = Boolean.valueOf(true);
    }
    this.adapter = new ChatFragListAdapter(getActivity(), (ArrayList)localObject, this.picMap, this.appMap);
    this.list.setAdapter(this.adapter);
    this.swipeLayout.setRefreshing(false);
    localObject = new Intent(getActivity(), NotificationService.class);
    getActivity().startService((Intent)localObject);
    if ("main".equals(paramString)) {
      return;
    }
    if ((!"clear".equals(paramString)) && (!"noti".equals(paramString)))
    {
      Toast.makeText(getActivity(), "List has been updated successfully", 0).show();
      return;
    }
    if (!"noti".equals(paramString)) {
      Toast.makeText(getActivity(), "Chat messages deleted successfully", 0).show();
    }
  }
}
