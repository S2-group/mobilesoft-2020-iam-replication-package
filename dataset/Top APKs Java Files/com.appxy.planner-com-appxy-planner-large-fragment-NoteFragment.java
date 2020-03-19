package com.appxy.planner.large.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appxy.planner.MyApplication;
import com.appxy.planner.activity.SettingPrefrence;
import com.appxy.planner.dao.BitmapDao;
import com.appxy.planner.dao.Notesdao;
import com.appxy.planner.dao.Settingsdao;
import com.appxy.planner.db.PlannerDb;
import com.appxy.planner.helper.BitmapHelper;
import com.appxy.planner.helper.DateFormatHelper;
import com.appxy.planner.helper.DateTrans;
import com.appxy.planner.implement.FragmentInterface;
import com.appxy.planner.implement.ViewRefresh;
import com.appxy.planner.large.activity.NoteView;
import com.appxy.planner.large.adapter.NotesFragmentAdapter;
import com.appxy.planner.large.helper.SearchHelper;
import com.appxy.planner.table.DbManagerTaskResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;

public class NoteFragment
  extends Fragment
  implements View.OnClickListener, FragmentInterface, ViewRefresh
{
  private NotesFragmentAdapter adapter;
  private ImageView add_iv;
  private ArrayList<BitmapDao> bitmapDaos = new ArrayList();
  Comparator<String> comparator = new Comparator()
  {
    public int compare(String paramAnonymousString1, String paramAnonymousString2)
    {
      int i = Integer.parseInt(paramAnonymousString1.substring(0, 4));
      int j = Integer.parseInt(paramAnonymousString2.substring(0, 4));
      int k = Integer.parseInt(paramAnonymousString1.substring(5, 7));
      int m = Integer.parseInt(paramAnonymousString2.substring(5, 7));
      if (i == j) {
        if (k <= m) {}
      }
      while (i > j)
      {
        return 1;
        return -1;
      }
      return -1;
    }
  };
  private Activity context;
  private DateTrans dateTrans;
  private PlannerDb db;
  private Settingsdao doSetting;
  private RelativeLayout mianview_more_rl;
  private TextView mianview_tasks_tv;
  private TreeMap<String, ArrayList<Notesdao>> mnotedata = new TreeMap();
  private ArrayList<String> mnotegrouplist = new ArrayList();
  private ImageView no_iv;
  private ListView note_lv;
  private ArrayList<Notesdao> notesdaoslist = new ArrayList();
  private SearchHelper searchHelper;
  private Typeface typeface;
  private String userID;
  private ArrayList<String> uuids = new ArrayList();
  
  public NoteFragment() {}
  
  private void getdata()
  {
    this.notesdaoslist = this.db.getallnotes(this.userID);
    this.mnotedata.clear();
    new ArrayList();
    this.uuids.clear();
    Object localObject2 = this.notesdaoslist.iterator();
    if (((Iterator)localObject2).hasNext())
    {
      Notesdao localNotesdao = (Notesdao)((Iterator)localObject2).next();
      this.uuids.add(localNotesdao.getFirstuuid());
      if ((localNotesdao.getnContent() == null) || (localNotesdao.getnContent().length() == 0)) {
        localNotesdao.setnContent("Untitled");
      }
      String str = DateFormatHelper.getnotekey(localNotesdao.getnDate());
      if (!this.mnotedata.containsKey(str)) {}
      for (localObject1 = new ArrayList();; localObject1 = (ArrayList)this.mnotedata.get(str))
      {
        ((ArrayList)localObject1).add(localNotesdao);
        this.mnotedata.put(str, localObject1);
        break;
      }
    }
    this.mnotegrouplist.clear();
    Object localObject1 = this.mnotedata.entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (String)((Map.Entry)((Iterator)localObject1).next()).getKey();
      this.mnotegrouplist.add(localObject2);
    }
    if (this.mnotegrouplist.size() == 0) {
      this.no_iv.setVisibility(0);
    }
    for (;;)
    {
      Collections.sort(this.mnotegrouplist, this.comparator);
      return;
      this.no_iv.setVisibility(8);
    }
  }
  
  private void initdata()
  {
    getdata();
    setlistview();
    new Managertask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[] { "" });
  }
  
  private void initviews(View paramView)
  {
    this.add_iv = ((ImageView)paramView.findViewById(2131624888));
    this.note_lv = ((ListView)paramView.findViewById(2131624887));
    this.mianview_more_rl = ((RelativeLayout)this.context.findViewById(2131624098));
    this.mianview_tasks_tv = ((TextView)this.context.findViewById(2131624088));
    this.no_iv = ((ImageView)paramView.findViewById(2131624889));
    this.mianview_tasks_tv.setText("Notes");
    this.mianview_tasks_tv.setVisibility(0);
    this.typeface = Typeface.createFromAsset(this.context.getAssets(), "fonts/HELVETICANEUELTPRO-ROMAN.OTF");
    this.add_iv.setOnClickListener(this);
    this.mianview_more_rl.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        final PopupWindow localPopupWindow;
        LinearLayout localLinearLayout1;
        LinearLayout localLinearLayout2;
        if (MyApplication.shoufei == 1)
        {
          paramAnonymousView = NoteFragment.this.context.getLayoutInflater().inflate(2130903079, null);
          localPopupWindow = new PopupWindow(paramAnonymousView, -2, -2);
          localPopupWindow.setBackgroundDrawable(NoteFragment.this.getResources().getDrawable(2130838003));
          localPopupWindow.setOutsideTouchable(true);
          localPopupWindow.update();
          localPopupWindow.setTouchable(true);
          localPopupWindow.setFocusable(true);
          if (!localPopupWindow.isShowing()) {
            localPopupWindow.showAsDropDown(NoteFragment.this.mianview_more_rl);
          }
          Object localObject4 = (LinearLayout)paramAnonymousView.findViewById(2131624186);
          localLinearLayout1 = (LinearLayout)paramAnonymousView.findViewById(2131624184);
          localLinearLayout2 = (LinearLayout)paramAnonymousView.findViewById(2131624201);
          LinearLayout localLinearLayout3 = (LinearLayout)paramAnonymousView.findViewById(2131624198);
          Object localObject1 = (LinearLayout)paramAnonymousView.findViewById(2131624188);
          Object localObject2 = (LinearLayout)paramAnonymousView.findViewById(2131624190);
          Object localObject3 = (TextView)paramAnonymousView.findViewById(2131624189);
          TextView localTextView1 = (TextView)paramAnonymousView.findViewById(2131624187);
          TextView localTextView2 = (TextView)paramAnonymousView.findViewById(2131624185);
          TextView localTextView3 = (TextView)paramAnonymousView.findViewById(2131624191);
          ((LinearLayout)localObject4).setVisibility(8);
          localObject4 = (TextView)paramAnonymousView.findViewById(2131624200);
          TextView localTextView4 = (TextView)paramAnonymousView.findViewById(2131624202);
          ((TextView)localObject3).setTypeface(NoteFragment.this.typeface);
          localTextView1.setTypeface(NoteFragment.this.typeface);
          localTextView2.setTypeface(NoteFragment.this.typeface);
          localTextView3.setTypeface(NoteFragment.this.typeface);
          localTextView4.setTypeface(NoteFragment.this.typeface);
          ((TextView)localObject4).setTypeface(NoteFragment.this.typeface);
          localTextView1.setText("Calendars");
          localLinearLayout3.setVisibility(8);
          localObject3 = (RelativeLayout)paramAnonymousView.findViewById(2131624199);
          ((LinearLayout)localObject2).setVisibility(8);
          localLinearLayout3.setVisibility(8);
          ((LinearLayout)localObject1).setVisibility(8);
          ((LinearLayout)localObject2).setVisibility(8);
          if (MyApplication.shoufei != 1) {
            break label515;
          }
          localLinearLayout3 = (LinearLayout)paramAnonymousView.findViewById(2131624196);
          localObject1 = (TextView)paramAnonymousView.findViewById(2131624197);
          localObject2 = (LinearLayout)paramAnonymousView.findViewById(2131624192);
          localObject2 = (TextView)paramAnonymousView.findViewById(2131624193);
          localObject3 = (LinearLayout)paramAnonymousView.findViewById(2131624194);
          paramAnonymousView = (TextView)paramAnonymousView.findViewById(2131624195);
          ((TextView)localObject1).setTypeface(NoteFragment.this.typeface);
          ((TextView)localObject2).setTypeface(NoteFragment.this.typeface);
          paramAnonymousView.setTypeface(NoteFragment.this.typeface);
          localLinearLayout3.setVisibility(8);
        }
        for (;;)
        {
          localLinearLayout1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localPopupWindow.dismiss();
              NoteFragment.access$302(NoteFragment.this, new SearchHelper(NoteFragment.this.context, NoteFragment.this.mianview_more_rl, NoteFragment.this.db, NoteFragment.this.dateTrans, NoteFragment.this.doSetting, NoteFragment.this));
            }
          });
          localLinearLayout2.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localPopupWindow.dismiss();
              paramAnonymous2View = new Intent();
              paramAnonymous2View.setClass(NoteFragment.this.context, SettingPrefrence.class);
              NoteFragment.this.startActivity(paramAnonymous2View);
            }
          });
          return;
          paramAnonymousView = NoteFragment.this.context.getLayoutInflater().inflate(2130903080, null);
          break;
          label515:
          ((TextView)paramAnonymousView.findViewById(2131624204)).setTypeface(NoteFragment.this.typeface);
          localLinearLayout1.setVisibility(0);
          paramAnonymousView = (LinearLayout)paramAnonymousView.findViewById(2131624203);
          paramAnonymousView.setVisibility(8);
          paramAnonymousView.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              switch (MyApplication.banben)
              {
              default: 
                return;
              case 1: 
                Object localObject = NoteFragment.this.context.getPackageManager().getInstalledApplications(0);
                int j = ((List)localObject).size();
                paramAnonymous2View = null;
                int i = 0;
                while (i < j)
                {
                  if (((ApplicationInfo)((List)localObject).get(i)).packageName.equals("com.android.vending")) {
                    paramAnonymous2View = (ApplicationInfo)((List)localObject).get(i);
                  }
                  i += 1;
                }
                if (paramAnonymous2View != null)
                {
                  localObject = new Intent("android.intent.action.VIEW");
                  ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.plannerplus&hl=en"));
                  ((Intent)localObject).setPackage(paramAnonymous2View.packageName);
                  NoteFragment.this.startActivity((Intent)localObject);
                  return;
                }
                NoteFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.appxy.plannerplus")));
                return;
              case 2: 
                try
                {
                  paramAnonymous2View = new Intent("android.intent.action.VIEW");
                  paramAnonymous2View.setData(Uri.parse("amzn://apps/android?asin=B00KA9WHMU"));
                  NoteFragment.this.startActivity(paramAnonymous2View);
                  return;
                }
                catch (ActivityNotFoundException paramAnonymous2View)
                {
                  paramAnonymous2View = new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/Planner-Plus-Schedule-Personal-Organizer/dp/B00KA9WHMU/ref=zg_bs_2478859011_44"));
                  NoteFragment.this.startActivity(paramAnonymous2View);
                  return;
                }
              }
              try
              {
                paramAnonymous2View = new Intent("android.intent.action.VIEW");
                paramAnonymous2View.setData(Uri.parse("samsungapps://ProductDetail/com.appxy.plannerplus"));
                NoteFragment.this.startActivity(paramAnonymous2View);
                return;
              }
              catch (ActivityNotFoundException paramAnonymous2View)
              {
                paramAnonymous2View = new Intent("android.intent.action.VIEW", Uri.parse("http://apps.samsung.com/mars/topApps/topAppsDetail.as?productId=000000846370"));
                NoteFragment.this.startActivity(paramAnonymous2View);
              }
            }
          });
        }
      }
    });
  }
  
  private void setlistview()
  {
    this.adapter = new NotesFragmentAdapter(this.context, this.mnotegrouplist, this.mnotedata, this.doSetting, this.dateTrans, this.db, this);
    this.note_lv.setAdapter(this.adapter);
    this.note_lv.setDivider(null);
  }
  
  public void fragmentrefresh()
  {
    viewRefresh();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    }
    paramView = new Intent();
    Bundle localBundle = new Bundle();
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    localGregorianCalendar.set(MyApplication.whichyear, MyApplication.whichmonth - 1, MyApplication.whichday);
    localBundle.putLong("startdate", localGregorianCalendar.getTimeInMillis());
    localBundle.putInt("update", 0);
    paramView.putExtras(localBundle);
    paramView.setClass(this.context, NoteView.class);
    this.context.startActivity(paramView);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = getActivity();
    this.db = new PlannerDb(this.context);
    this.doSetting = ((Settingsdao)this.db.getAllsetting().get(0));
    paramBundle = this.context.getPackageName();
    paramBundle = this.context.getSharedPreferences(paramBundle + "_preferences", 0);
    this.dateTrans = new DateTrans(this.context);
    this.userID = paramBundle.getString("userID", "");
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130903198, paramViewGroup, false);
    initviews(paramLayoutInflater);
    initdata();
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onResume()
  {
    super.onResume();
    if (MyApplication.needupdate)
    {
      viewRefresh();
      MyApplication.needupdate = false;
    }
    if (this.searchHelper != null) {
      this.searchHelper.viewRefresh();
    }
  }
  
  public void viewRefresh()
  {
    getdata();
    new Managertask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[] { "" });
    if (this.adapter != null) {
      this.adapter.setdata(this.mnotegrouplist, this.mnotedata, this.doSetting);
    }
  }
  
  class Managertask
    extends AsyncTask<Object, BitmapDao, Object>
  {
    Managertask() {}
    
    protected DbManagerTaskResult doInBackground(Object... paramVarArgs)
    {
      int i = 0;
      if (i < NoteFragment.this.uuids.size())
      {
        String str = (String)NoteFragment.this.uuids.get(i);
        Object localObject;
        if ((str != null) && (!str.equals("")))
        {
          localObject = new File("/mnt/sdcard/PlannerPro/IMAGESFOLDER/" + str + ".jpg");
          paramVarArgs = null;
          if (!((File)localObject).exists()) {
            break label190;
          }
          localObject = BitmapFactory.decodeFile("/mnt/sdcard/PlannerPro/IMAGESFOLDER/" + str + ".jpg");
          paramVarArgs = (Object[])localObject;
          if (localObject != null) {
            paramVarArgs = BitmapHelper.setneedshowbitmap(NoteFragment.this.context, (Bitmap)localObject);
          }
        }
        for (;;)
        {
          if (paramVarArgs != null)
          {
            localObject = new BitmapDao();
            ((BitmapDao)localObject).setBitmap(paramVarArgs);
            ((BitmapDao)localObject).setUuid(str);
            NoteFragment.this.bitmapDaos.add(localObject);
            publishProgress(new BitmapDao[] { localObject });
          }
          i += 1;
          break;
          label190:
          if (new File("/mnt/sdcard/PlannerPro/UNSYNCFOLDER/" + str + ".jpg").exists())
          {
            localObject = BitmapFactory.decodeFile("/mnt/sdcard/PlannerPro/UNSYNCFOLDER/" + str + ".jpg");
            paramVarArgs = (Object[])localObject;
            if (localObject != null) {
              paramVarArgs = BitmapHelper.setneedshowbitmap(NoteFragment.this.context, (Bitmap)localObject);
            }
          }
        }
      }
      return null;
    }
    
    protected void onPostExecute(Object paramObject)
    {
      super.onPostExecute(paramObject);
    }
    
    protected void onProgressUpdate(BitmapDao... paramVarArgs)
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        BitmapDao localBitmapDao = paramVarArgs[i];
        NoteFragment.this.adapter.setbitmapdao(localBitmapDao);
        i += 1;
      }
    }
  }
}
