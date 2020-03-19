package imoblife.startupmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import base.android.view.RevealLayout;
import base.util.LogUtil;
import base.util.ViewUtil;
import base.util.ui.titlebar.BaseTitlebarActivity;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.Builder;
import com.gc.materialdesign.views.ButtonFloat;
import imoblife.toolbox.full.clean.StatusbarUtil;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class StartupAddActivity
  extends BaseTitlebarActivity
  implements View.OnClickListener
{
  private static final String KEY_CONTENT = "CustomFragment:Content";
  public static final int REQUEST_CODE_CHOICE_APP = 200;
  private static final String TAG = StartupAddActivity.class.getSimpleName();
  public static List<Item> system = new ArrayList();
  public static List<Item> user = new ArrayList();
  private final String[] FORM = { "KEY1", "KEY2" };
  private final int[] TO = { R.id.linkIcon, R.id.linkName };
  private LinearLayout base_titlebar_ll;
  List<String> bootItem = new ArrayList();
  Comparator<AppInfo> comparator = new Comparator()
  {
    public int compare(AppInfo paramAnonymousAppInfo1, AppInfo paramAnonymousAppInfo2)
    {
      if ((paramAnonymousAppInfo1.isSelect) && (paramAnonymousAppInfo2.isSelect)) {}
      do
      {
        return Collator.getInstance().compare(paramAnonymousAppInfo1.appLabel, paramAnonymousAppInfo2.appLabel);
        if (paramAnonymousAppInfo1.isSelect) {
          return -1;
        }
      } while (!paramAnonymousAppInfo2.isSelect);
      return 1;
    }
  };
  private Handler handler;
  ListItem item;
  private LayoutInflater layoutInflater;
  private ListView listView;
  private CustomizeItem listViewItem;
  private ButtonFloat mButtonFloat;
  private ImageView mCancelImageView;
  private String mContent = "";
  private View mMaskView;
  private int mOldItem = 0;
  private RevealLayout mRevealLayout;
  private TextView mSystemTextView;
  private TextView mUserTextView;
  private TextView path_tv;
  PackageManager pm;
  MaterialDialog progressDialog;
  private ArrayList<AppInfo> systemAppList;
  private ArrayList<AppInfo> userAppList;
  ManagerUtil util;
  
  public StartupAddActivity() {}
  
  private void showChoiceView()
  {
    this.mMaskView.setVisibility(0);
    this.mRevealLayout.setVisibility(0);
    this.mRevealLayout.show(this.mRevealLayout.getWidth(), this.mRevealLayout.getHeight());
    this.mUserTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        StartupAddActivity.this.mRevealLayout.hide();
        StartupAddActivity.this.mMaskView.setVisibility(8);
        StartupAddActivity.this.newUse();
        StartupAddActivity.this.showList(StartupAddActivity.this.userAppList, R.string.customize_user);
      }
    });
    this.mSystemTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        StartupAddActivity.this.mRevealLayout.hide();
        StartupAddActivity.this.mMaskView.setVisibility(8);
        StartupAddActivity.this.newUse();
        StartupAddActivity.this.showList(StartupAddActivity.this.systemAppList, R.string.system_apps);
      }
    });
    this.mCancelImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        StartupAddActivity.this.mRevealLayout.hide();
        StartupAddActivity.this.mMaskView.setVisibility(8);
      }
    });
  }
  
  public void SystemData()
  {
    this.util = new ManagerUtil();
    this.item = new ListItem();
    this.item = this.util.getBroadcast(this);
    system = this.util.getSystemList(this.item, system, this);
    user = this.util.getUserList(this.item, user, this);
  }
  
  public void creatView()
  {
    setContentView(R.layout.zcustomize);
    inintView();
    StatusbarUtil.setStatusbarLeftText(findViewById(R.id.statusbar_ll), getString(R.string.toolbox_add_content));
  }
  
  public void deleteCustomizeList(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = getSQLiteDatabase();
    localSQLiteDatabase.execSQL("DELETE FROM LIST WHERE packageName='" + paramString + "'");
    localSQLiteDatabase.close();
  }
  
  protected Activity getActivity()
  {
    return this;
  }
  
  public void getAppInfo(AppInfo paramAppInfo)
  {
    String[] arrayOfString = new String[4];
    for (;;)
    {
      try
      {
        localObject = getPackageManager().getPackageInfo(paramAppInfo.appPackageName, 0);
        arrayOfString[0] = (getString(R.string.za_applicationName) + " " + paramAppInfo.appLabel);
        arrayOfString[0] = (arrayOfString[0] + "\n\n" + getString(R.string.pkgname) + " " + ((PackageInfo)localObject).applicationInfo.processName);
        arrayOfString[1] = (getString(R.string.za_sourceDirectory) + " " + ((PackageInfo)localObject).applicationInfo.sourceDir);
        arrayOfString[2] = (getString(R.string.za_versionCode) + " " + ((PackageInfo)localObject).versionCode);
        String str = ((PackageInfo)localObject).versionName;
        if (str == null) {
          continue;
        }
        arrayOfString[2] = (arrayOfString[2] + "\n" + getString(R.string.za_versionName) + " " + str);
        localObject = (String)((PackageInfo)localObject).applicationInfo.loadDescription(getPackageManager());
        if (localObject == null) {
          continue;
        }
        arrayOfString[3] = (getString(R.string.za_applicationDescription) + " " + (String)localObject);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Object localObject;
        continue;
      }
      localObject = new MaterialDialog.Builder(this);
      ((MaterialDialog.Builder)localObject).title(paramAppInfo.appLabel);
      ((MaterialDialog.Builder)localObject).content(arrayOfString[0] + "\n\n" + arrayOfString[1] + "\n\n" + arrayOfString[2] + "\n\n" + arrayOfString[3]);
      ((MaterialDialog.Builder)localObject).negativeText(getString(R.string.disableall_cancel));
      ((MaterialDialog.Builder)localObject).build().show();
      return;
      arrayOfString[2] = (arrayOfString[2] + "\n" + getString(R.string.za_versionName) + " " + getString(R.string.unknown));
      continue;
      arrayOfString[3] = (getString(R.string.za_applicationDescription) + " " + getString(R.string.unknown) + "\n");
    }
  }
  
  public List<AppInfo> getCustomizeList()
  {
    this.bootItem.clear();
    ArrayList localArrayList = new ArrayList();
    SQLiteDatabase localSQLiteDatabase = getSQLiteDatabase();
    Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT packageName,name FROM LIST", null);
    localCursor.moveToFirst();
    localSQLiteDatabase.close();
    for (;;)
    {
      if ((localCursor.getCount() > 0) && (!localCursor.isAfterLast())) {
        try
        {
          this.bootItem.add(localCursor.getString(0));
          localArrayList.add(new AppInfo(this.pm.getApplicationInfo(localCursor.getString(0), 0), this.pm, true));
          localCursor.moveToNext();
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            LogUtil.w(TAG, localNameNotFoundException);
            getSQLiteDatabase().delete("LIST", " packageName=?", new String[] { localCursor.getString(0) });
          }
        }
      }
    }
    localCursor.close();
    return localArrayList;
  }
  
  public SQLiteDatabase getSQLiteDatabase()
  {
    SQLiteDatabase localSQLiteDatabase = openOrCreateDatabase("CUSTOMIZEDATA", 0, null);
    localSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS LIST(packageName TEXT,name TEXT)");
    return localSQLiteDatabase;
  }
  
  protected int getTitleStringId()
  {
    return R.string.toolbox_tool_add;
  }
  
  public String getTrackModule()
  {
    return getClass().getSimpleName();
  }
  
  public void iniAppList()
  {
    if (this.progressDialog != null) {}
    try
    {
      this.progressDialog.dismiss();
      this.progressDialog = new MaterialDialog.Builder(getActivity()).progressIndeterminateStyle(false).progress(true, 0).build();
      this.progressDialog.setContent(getString(R.string.za_wait));
      new Thread()
      {
        public void run()
        {
          StartupAddActivity.access$302(StartupAddActivity.this, new ArrayList());
          StartupAddActivity.access$402(StartupAddActivity.this, new ArrayList());
          Object localObject = StartupAddActivity.this.pm.getInstalledPackages(0).iterator();
          for (;;)
          {
            PackageInfo localPackageInfo;
            if (((Iterator)localObject).hasNext())
            {
              localPackageInfo = (PackageInfo)((Iterator)localObject).next();
              if (StartupAddActivity.this.bootItem.contains(localPackageInfo.packageName)) {}
            }
            else
            {
              for (;;)
              {
                try
                {
                  if (StartupAddActivity.this.pm.getLaunchIntentForPackage(localPackageInfo.packageName) == null) {
                    break;
                  }
                  if (StartupAddActivity.this.isSystemApp(localPackageInfo.applicationInfo))
                  {
                    StartupAddActivity.this.systemAppList.add(new AppInfo(localPackageInfo.applicationInfo, StartupAddActivity.this.pm, false));
                    break;
                  }
                  StartupAddActivity.this.userAppList.add(new AppInfo(localPackageInfo.applicationInfo, StartupAddActivity.this.pm, false));
                }
                catch (Exception localException2) {}
                try
                {
                  Collections.sort(StartupAddActivity.this.userAppList, StartupAddActivity.this.comparator);
                  Collections.sort(StartupAddActivity.this.systemAppList, StartupAddActivity.this.comparator);
                  localObject = new Message();
                  ((Message)localObject).what = 0;
                  StartupAddActivity.this.handler.sendMessage((Message)localObject);
                  return;
                }
                catch (Exception localException1)
                {
                  localException1.printStackTrace();
                }
              }
            }
          }
        }
      }.start();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public void iniList()
  {
    this.listViewItem = new CustomizeItem(this, getCustomizeList());
    Collections.sort(this.listViewItem.scr, this.comparator);
    this.listView.setAdapter(this.listViewItem);
    this.listView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if (paramAnonymousInt1 < StartupAddActivity.this.mOldItem)
        {
          if (!StartupAddActivity.this.mButtonFloat.isShow()) {
            StartupAddActivity.this.mButtonFloat.show();
          }
          StartupAddActivity.access$702(StartupAddActivity.this, paramAnonymousInt1);
        }
        while (paramAnonymousInt1 <= StartupAddActivity.this.mOldItem) {
          return;
        }
        if (StartupAddActivity.this.mButtonFloat.isShow()) {
          StartupAddActivity.this.mButtonFloat.hide();
        }
        StartupAddActivity.access$702(StartupAddActivity.this, paramAnonymousInt1);
      }
      
      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        }
        do
        {
          return;
        } while ((paramAnonymousAbsListView.getLastVisiblePosition() != paramAnonymousAbsListView.getCount() - 1) || (StartupAddActivity.this.mButtonFloat.isShow()));
        StartupAddActivity.this.mButtonFloat.show();
      }
    });
  }
  
  public void inintView()
  {
    this.pm = getPackageManager();
    this.layoutInflater = LayoutInflater.from(this);
    this.listView = ((ListView)findViewById(R.id.zcustomize_list));
    View localView = new View(this);
    localView.setLayoutParams(new AbsListView.LayoutParams(-1, ViewUtil.dip2px(this, 80.0F)));
    this.listView.addFooterView(localView);
    this.mMaskView = findViewById(R.id.view_mask);
    this.mRevealLayout = ((RevealLayout)findViewById(R.id.reveal_layout));
    this.mRevealLayout.setContentShown(false);
    this.mRevealLayout.setVisibility(4);
    this.mUserTextView = ((TextView)findViewById(R.id.tv_user));
    this.mSystemTextView = ((TextView)findViewById(R.id.tv_system));
    this.mCancelImageView = ((ImageView)findViewById(R.id.iv_cancel));
    this.mButtonFloat = ((ButtonFloat)findViewById(R.id.buttonFloat));
    this.mButtonFloat.setOnClickListener(this);
    iniList();
    this.handler = new Handler()
    {
      /* Error */
      public void handleMessage(Message paramAnonymousMessage)
      {
        // Byte code:
        //   0: aload_1
        //   1: getfield 28	android/os/Message:what	I
        //   4: istore_2
        //   5: iload_2
        //   6: tableswitch	default:+22->28, 0:+23->29, 1:+47->53
        //   28: return
        //   29: aload_0
        //   30: getfield 15	imoblife/startupmanager/StartupAddActivity$1:this$0	Limoblife/startupmanager/StartupAddActivity;
        //   33: getfield 32	imoblife/startupmanager/StartupAddActivity:progressDialog	Lcom/afollestad/materialdialogs/MaterialDialog;
        //   36: invokevirtual 37	com/afollestad/materialdialogs/MaterialDialog:dismiss	()V
        //   39: aload_0
        //   40: getfield 15	imoblife/startupmanager/StartupAddActivity$1:this$0	Limoblife/startupmanager/StartupAddActivity;
        //   43: invokestatic 40	imoblife/startupmanager/StartupAddActivity:access$000	(Limoblife/startupmanager/StartupAddActivity;)V
        //   46: return
        //   47: astore_1
        //   48: aload_1
        //   49: invokevirtual 43	java/lang/Exception:printStackTrace	()V
        //   52: return
        //   53: aload_0
        //   54: getfield 15	imoblife/startupmanager/StartupAddActivity$1:this$0	Limoblife/startupmanager/StartupAddActivity;
        //   57: invokevirtual 46	imoblife/startupmanager/StartupAddActivity:iniList	()V
        //   60: return
        //   61: astore_1
        //   62: goto -23 -> 39
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	65	0	this	1
        //   0	65	1	paramAnonymousMessage	Message
        //   4	2	2	i	int
        // Exception table:
        //   from	to	target	type
        //   0	5	47	java/lang/Exception
        //   39	46	47	java/lang/Exception
        //   53	60	47	java/lang/Exception
        //   29	39	61	java/lang/Exception
      }
    };
  }
  
  public boolean isSystemApp(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) == 1;
  }
  
  public void newUse()
  {
    SystemData();
    int i = 0;
    int j;
    while (i < user.size())
    {
      j = 0;
      while (j < this.userAppList.size())
      {
        if (((Item)user.get(i)).applicationInfo.packageName.equals(((AppInfo)this.userAppList.get(j)).appPackageName)) {
          this.userAppList.remove(j);
        }
        j += 1;
      }
      i += 1;
    }
    i = 0;
    while (i < system.size())
    {
      j = 0;
      while (j < this.systemAppList.size())
      {
        if (((Item)system.get(i)).applicationInfo.packageName.equals(((AppInfo)this.systemAppList.get(j)).appPackageName)) {
          this.systemAppList.remove(j);
        }
        j += 1;
      }
      i += 1;
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 200)
    {
      paramIntent = new Message();
      paramIntent.what = 1;
      this.handler.sendMessage(paramIntent);
    }
  }
  
  public void onClick(View paramView)
  {
    if (paramView == this.base_titlebar_ll) {
      finish();
    }
    while (paramView.getId() != R.id.buttonFloat) {
      return;
    }
    iniAppList();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getString(R.string.startup_customize));
    if ((paramBundle != null) && (paramBundle.containsKey("CustomFragment:Content"))) {
      this.mContent = paramBundle.getString("CustomFragment:Content");
    }
    creatView();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putString("CustomFragment:Content", this.mContent);
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  public void showList(ArrayList<AppInfo> paramArrayList, int paramInt)
  {
    Intent localIntent = new Intent(this, ChoiceAppStartupActivity.class);
    localIntent.putExtra("activity_title", getString(paramInt));
    localIntent.putParcelableArrayListExtra("choice_data", paramArrayList);
    startActivityForResult(localIntent, 200);
  }
  
  class CustomizeItem
    extends BaseAdapter
  {
    public View[] myView;
    private List<AppInfo> scr;
    
    public CustomizeItem(List<AppInfo> paramList)
    {
      Object localObject;
      this.scr = localObject;
      this.myView = new View[this.scr.size()];
    }
    
    public void delectItem()
    {
      int i = 0;
      for (;;)
      {
        if ((i >= this.myView.length) || (this.myView[i] == null)) {
          return;
        }
        Object localObject = (StartupAddActivity.CustomizeItem.Element)this.myView[i].getTag();
        if (((AppInfo)this.scr.get(i)).isSelect)
        {
          StartupAddActivity.this.deleteCustomizeList(((AppInfo)this.scr.get(i)).appPackageName);
          localObject = new Message();
          ((Message)localObject).what = 1;
          StartupAddActivity.this.handler.sendMessage((Message)localObject);
        }
        i += 1;
      }
    }
    
    public int getCount()
    {
      return this.myView.length;
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
      this.myView[paramInt] = StartupAddActivity.this.layoutInflater.inflate(R.layout.startup_customize, null);
      paramView = new StartupAddActivity.CustomizeItem.Element(this);
      paramView.imageView = ((ImageView)this.myView[paramInt].findViewById(R.id.icon));
      paramView.textView = ((TextView)this.myView[paramInt].findViewById(R.id.text));
      StartupAddActivity.this.loadImage(paramView.imageView, ((AppInfo)this.scr.get(paramInt)).iconUri, null);
      paramView.textView.setText(((AppInfo)this.scr.get(paramInt)).appLabel);
      paramView.textView.setSingleLine();
      paramView.checkBox = ((ImageView)this.myView[paramInt].findViewById(R.id.check));
      paramView.checkBox.setOnClickListener(new StartupAddActivity.CustomizeItem.1(this, paramInt));
      this.myView[paramInt].setTag(paramView);
      this.myView[paramInt].setOnLongClickListener(new StartupAddActivity.CustomizeItem.2(this, paramInt));
      return this.myView[paramInt];
    }
    
    public void remove(int paramInt)
    {
      this.scr.remove(paramInt);
      notifyDataSetChanged();
    }
  }
}
