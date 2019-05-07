package ws.coverme.im.ui.applockmanager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import ws.coverme.im.ui.adapter.AppLockedManagerAdapter;
import ws.coverme.im.ui.view.BaseActivity;
import ws.coverme.im.ui.view.StretchListView;

public class AppLockedManagerActivity
  extends BaseActivity
  implements View.OnClickListener, AdapterView.OnItemClickListener
{
  private AppLockedManagerAdapter adapter;
  private ArrayList<HashMap<String, Object>> appsdata;
  private StretchListView listView;
  private AppLockDataHandler lockhandler;
  
  public AppLockedManagerActivity() {}
  
  private void gotoSetActivity(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, AppLockSettingActivity.class);
    localIntent.putExtra("packageName", paramString);
    localIntent.addFlags(67108864);
    startActivity(localIntent);
  }
  
  private void initData()
  {
    this.appsdata = new ArrayList();
    this.lockhandler = AppLockDataHandler.getInstance(this);
    getListData();
    this.adapter = new AppLockedManagerAdapter(this, this.appsdata);
    this.listView.setAdapter(this.adapter);
    this.listView.setDivider(getResources().getDrawable(2130838812));
  }
  
  private void initListener()
  {
    this.listView.setOnItemClickListener(this);
  }
  
  private void initView()
  {
    this.listView = ((StretchListView)findViewById(2131234949));
  }
  
  /* Error */
  public void getListData()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 54	ws/coverme/im/ui/applockmanager/AppLockedManagerActivity:appsdata	Ljava/util/ArrayList;
    //   4: invokevirtual 111	java/util/ArrayList:clear	()V
    //   7: aload_0
    //   8: invokevirtual 115	ws/coverme/im/ui/applockmanager/AppLockedManagerActivity:getPackageName	()Ljava/lang/String;
    //   11: astore 5
    //   13: invokestatic 120	ws/coverme/im/model/KexinData:getInstance	()Lws/coverme/im/model/KexinData;
    //   16: invokevirtual 124	ws/coverme/im/model/KexinData:getCurrentAuthorityId	()I
    //   19: istore_1
    //   20: aload_0
    //   21: getfield 62	ws/coverme/im/ui/applockmanager/AppLockedManagerActivity:lockhandler	Lws/coverme/im/ui/applockmanager/AppLockDataHandler;
    //   24: iload_1
    //   25: invokevirtual 128	ws/coverme/im/ui/applockmanager/AppLockDataHandler:getAppList	(I)Ljava/util/ArrayList;
    //   28: astore 6
    //   30: aload_0
    //   31: invokestatic 132	ws/coverme/im/ui/applockmanager/AppLockDataHandler:getRoot	(Landroid/content/Context;)I
    //   34: istore 4
    //   36: aload_0
    //   37: invokevirtual 136	ws/coverme/im/ui/applockmanager/AppLockedManagerActivity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   40: astore 7
    //   42: aload 7
    //   44: iconst_0
    //   45: invokevirtual 142	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   48: invokeinterface 148 1 0
    //   53: astore 8
    //   55: aload 8
    //   57: invokeinterface 154 1 0
    //   62: ifeq +260 -> 322
    //   65: aload 8
    //   67: invokeinterface 158 1 0
    //   72: checkcast 160	android/content/pm/PackageInfo
    //   75: astore 9
    //   77: new 162	java/util/HashMap
    //   80: dup
    //   81: invokespecial 163	java/util/HashMap:<init>	()V
    //   84: astore 10
    //   86: aload 9
    //   88: getfield 167	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   91: getfield 172	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   94: astore 11
    //   96: aload 11
    //   98: aload 5
    //   100: invokevirtual 178	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   103: iconst_1
    //   104: if_icmpeq -49 -> 55
    //   107: aload 11
    //   109: invokestatic 182	ws/coverme/im/ui/applockmanager/AppLockDataHandler:isWhitePack	(Ljava/lang/String;)Z
    //   112: iconst_1
    //   113: if_icmpeq -58 -> 55
    //   116: iconst_0
    //   117: istore_2
    //   118: iload_2
    //   119: istore_1
    //   120: aload 6
    //   122: ifnull +18 -> 140
    //   125: iload_2
    //   126: istore_1
    //   127: aload 6
    //   129: aload 11
    //   131: invokevirtual 185	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   134: iconst_1
    //   135: if_icmpne +5 -> 140
    //   138: iconst_1
    //   139: istore_1
    //   140: iconst_0
    //   141: istore_3
    //   142: iload_3
    //   143: istore_2
    //   144: iload 4
    //   146: iconst_1
    //   147: if_icmpne +18 -> 165
    //   150: iload_3
    //   151: istore_2
    //   152: aload 7
    //   154: aload 11
    //   156: invokevirtual 189	android/content/pm/PackageManager:getApplicationEnabledSetting	(Ljava/lang/String;)I
    //   159: iconst_2
    //   160: if_icmpne +5 -> 165
    //   163: iconst_1
    //   164: istore_2
    //   165: aload 7
    //   167: aload 11
    //   169: iconst_0
    //   170: invokevirtual 193	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   173: astore 12
    //   175: new 26	android/content/Intent
    //   178: dup
    //   179: ldc -61
    //   181: aconst_null
    //   182: invokespecial 198	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   185: astore 13
    //   187: aload 13
    //   189: ldc -56
    //   191: invokevirtual 204	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   194: pop
    //   195: aload 13
    //   197: aload 12
    //   199: getfield 205	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   202: invokevirtual 208	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   205: pop
    //   206: aload 7
    //   208: aload 13
    //   210: iconst_0
    //   211: invokevirtual 212	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   214: astore 12
    //   216: aload 12
    //   218: ifnull +15 -> 233
    //   221: aload 12
    //   223: invokeinterface 215 1 0
    //   228: istore_3
    //   229: iload_3
    //   230: ifne +11 -> 241
    //   233: iload_1
    //   234: ifne +7 -> 241
    //   237: iload_2
    //   238: ifeq -183 -> 55
    //   241: aload 10
    //   243: ldc -39
    //   245: aload 9
    //   247: getfield 167	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   250: aload 7
    //   252: invokevirtual 221	android/content/pm/ApplicationInfo:loadIcon	(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;
    //   255: invokevirtual 225	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   258: pop
    //   259: aload 10
    //   261: ldc -29
    //   263: aload 9
    //   265: getfield 167	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   268: aload 7
    //   270: invokevirtual 231	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   273: invokevirtual 225	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   276: pop
    //   277: aload 10
    //   279: ldc -23
    //   281: aload 11
    //   283: invokevirtual 225	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   286: pop
    //   287: iload_1
    //   288: iconst_1
    //   289: if_icmpne -234 -> 55
    //   292: aload 10
    //   294: ldc -21
    //   296: iconst_1
    //   297: invokestatic 241	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   300: invokevirtual 225	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   303: pop
    //   304: aload_0
    //   305: getfield 54	ws/coverme/im/ui/applockmanager/AppLockedManagerActivity:appsdata	Ljava/util/ArrayList;
    //   308: aload 10
    //   310: invokevirtual 244	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   313: pop
    //   314: goto -259 -> 55
    //   317: astore 9
    //   319: goto -264 -> 55
    //   322: return
    //   323: astore 12
    //   325: goto -66 -> 259
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	328	0	this	AppLockedManagerActivity
    //   19	271	1	i	int
    //   117	121	2	j	int
    //   141	89	3	k	int
    //   34	114	4	m	int
    //   11	88	5	str1	String
    //   28	100	6	localArrayList	ArrayList
    //   40	229	7	localPackageManager	android.content.pm.PackageManager
    //   53	13	8	localIterator	java.util.Iterator
    //   75	189	9	localPackageInfo	android.content.pm.PackageInfo
    //   317	1	9	localException1	Exception
    //   84	225	10	localHashMap	HashMap
    //   94	188	11	str2	String
    //   173	49	12	localObject	Object
    //   323	1	12	localException2	Exception
    //   185	24	13	localIntent	Intent
    // Exception table:
    //   from	to	target	type
    //   165	216	317	java/lang/Exception
    //   221	229	317	java/lang/Exception
    //   241	259	323	java/lang/Exception
  }
  
  public void onBackPressed()
  {
    finish();
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    }
    onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903525);
    initView();
    initData();
    initListener();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    gotoSetActivity((String)((HashMap)this.appsdata.get(paramInt)).get("ItempackageName"));
  }
  
  protected void onResume()
  {
    super.onResume();
  }
}
