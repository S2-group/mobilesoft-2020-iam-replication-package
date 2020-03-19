package com.kebikids.moreapps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.kebikids.common.G;
import com.kebikids.common.SoundManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"HandlerLeak", "HandlerLeak", "HandlerLeak", "HandlerLeak", "HandlerLeak"})
public class MoreAppsPage
  extends Activity
{
  private AppList_GridAdapter adapter;
  private boolean allBtLock = false;
  private GridView appList;
  public AdapterView.OnItemClickListener appListItemClick = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      MoreAppsPage.this.allBtLock = true;
      SoundManager.soundPlay(2);
      paramAnonymousAdapterView = MoreAppsPage.this.checkAppInstalled(paramAnonymousInt);
      if (paramAnonymousAdapterView != null)
      {
        MoreAppsPage.this.mActivity.startActivity(paramAnonymousAdapterView);
        MoreAppsPage.this.finish();
        return;
      }
      MoreAppsPage.this.finish();
    }
  };
  private RelativeLayout layout;
  private final Activity mActivity = this;
  private MoreAppsData moreAppsData = new MoreAppsData();
  Handler moreAppsInfoLoadFinished = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      G.showProgressDialog(MoreAppsPage.this.mActivity, "불러오는 중...");
      new Thread(new Runnable()
      {
        /* Error */
        public void run()
        {
          // Byte code:
          //   0: aconst_null
          //   1: astore 9
          //   3: aconst_null
          //   4: astore 8
          //   6: aconst_null
          //   7: astore 11
          //   9: aconst_null
          //   10: astore 12
          //   12: iconst_0
          //   13: istore_3
          //   14: iconst_0
          //   15: istore_2
          //   16: iconst_0
          //   17: istore 4
          //   19: iconst_0
          //   20: istore 5
          //   22: iconst_0
          //   23: istore_1
          //   24: aload_0
          //   25: getfield 17	com/kebikids/moreapps/MoreAppsPage$1$1:this$1	Lcom/kebikids/moreapps/MoreAppsPage$1;
          //   28: invokestatic 28	com/kebikids/moreapps/MoreAppsPage$1:access$0	(Lcom/kebikids/moreapps/MoreAppsPage$1;)Lcom/kebikids/moreapps/MoreAppsPage;
          //   31: invokestatic 33	com/kebikids/moreapps/MoreAppsPage:access$0	(Lcom/kebikids/moreapps/MoreAppsPage;)Landroid/app/Activity;
          //   34: ldc 35
          //   36: invokevirtual 41	android/app/Activity:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
          //   39: astore 7
          //   41: aload 7
          //   43: astore 8
          //   45: aload 7
          //   47: astore 9
          //   49: new 43	java/io/ObjectInputStream
          //   52: dup
          //   53: aload 7
          //   55: invokespecial 46	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
          //   58: astore 10
          //   60: iload 4
          //   62: istore_2
          //   63: iload 5
          //   65: istore_3
          //   66: aload_0
          //   67: getfield 17	com/kebikids/moreapps/MoreAppsPage$1$1:this$1	Lcom/kebikids/moreapps/MoreAppsPage$1;
          //   70: invokestatic 28	com/kebikids/moreapps/MoreAppsPage$1:access$0	(Lcom/kebikids/moreapps/MoreAppsPage$1;)Lcom/kebikids/moreapps/MoreAppsPage;
          //   73: aload 10
          //   75: invokevirtual 50	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
          //   78: checkcast 52	com/kebikids/moreapps/MoreAppsData
          //   81: invokestatic 56	com/kebikids/moreapps/MoreAppsPage:access$2	(Lcom/kebikids/moreapps/MoreAppsPage;Lcom/kebikids/moreapps/MoreAppsData;)V
          //   84: iload 4
          //   86: istore_2
          //   87: iload 5
          //   89: istore_3
          //   90: aload_0
          //   91: getfield 17	com/kebikids/moreapps/MoreAppsPage$1$1:this$1	Lcom/kebikids/moreapps/MoreAppsPage$1;
          //   94: invokestatic 28	com/kebikids/moreapps/MoreAppsPage$1:access$0	(Lcom/kebikids/moreapps/MoreAppsPage$1;)Lcom/kebikids/moreapps/MoreAppsPage;
          //   97: invokestatic 60	com/kebikids/moreapps/MoreAppsPage:access$3	(Lcom/kebikids/moreapps/MoreAppsPage;)Lcom/kebikids/moreapps/MoreAppsData;
          //   100: new 62	java/util/ArrayList
          //   103: dup
          //   104: invokespecial 63	java/util/ArrayList:<init>	()V
          //   107: putfield 67	com/kebikids/moreapps/MoreAppsData:isSetup	Ljava/util/ArrayList;
          //   110: iconst_0
          //   111: istore 4
          //   113: iload_1
          //   114: istore_2
          //   115: iload_1
          //   116: istore_3
          //   117: aload_0
          //   118: getfield 17	com/kebikids/moreapps/MoreAppsPage$1$1:this$1	Lcom/kebikids/moreapps/MoreAppsPage$1;
          //   121: invokestatic 28	com/kebikids/moreapps/MoreAppsPage$1:access$0	(Lcom/kebikids/moreapps/MoreAppsPage$1;)Lcom/kebikids/moreapps/MoreAppsPage;
          //   124: invokestatic 60	com/kebikids/moreapps/MoreAppsPage:access$3	(Lcom/kebikids/moreapps/MoreAppsPage;)Lcom/kebikids/moreapps/MoreAppsData;
          //   127: getfield 70	com/kebikids/moreapps/MoreAppsData:appLinks	Ljava/util/ArrayList;
          //   130: invokevirtual 74	java/util/ArrayList:size	()I
          //   133: istore 5
          //   135: iload 4
          //   137: iload 5
          //   139: if_icmplt +39 -> 178
          //   142: aload_0
          //   143: getfield 17	com/kebikids/moreapps/MoreAppsPage$1$1:this$1	Lcom/kebikids/moreapps/MoreAppsPage$1;
          //   146: invokestatic 28	com/kebikids/moreapps/MoreAppsPage$1:access$0	(Lcom/kebikids/moreapps/MoreAppsPage$1;)Lcom/kebikids/moreapps/MoreAppsPage;
          //   149: invokestatic 78	com/kebikids/moreapps/MoreAppsPage:access$1	(Lcom/kebikids/moreapps/MoreAppsPage;)Landroid/os/Handler;
          //   152: iload_1
          //   153: invokevirtual 84	android/os/Handler:sendEmptyMessage	(I)Z
          //   156: pop
          //   157: aload 10
          //   159: ifnull +8 -> 167
          //   162: aload 10
          //   164: invokevirtual 87	java/io/ObjectInputStream:close	()V
          //   167: aload 7
          //   169: ifnull +209 -> 378
          //   172: aload 7
          //   174: invokevirtual 90	java/io/FileInputStream:close	()V
          //   177: return
          //   178: iload_1
          //   179: istore_2
          //   180: iload_1
          //   181: istore_3
          //   182: aload_0
          //   183: getfield 17	com/kebikids/moreapps/MoreAppsPage$1$1:this$1	Lcom/kebikids/moreapps/MoreAppsPage$1;
          //   186: invokestatic 28	com/kebikids/moreapps/MoreAppsPage$1:access$0	(Lcom/kebikids/moreapps/MoreAppsPage$1;)Lcom/kebikids/moreapps/MoreAppsPage;
          //   189: aload_0
          //   190: getfield 17	com/kebikids/moreapps/MoreAppsPage$1$1:this$1	Lcom/kebikids/moreapps/MoreAppsPage$1;
          //   193: invokestatic 28	com/kebikids/moreapps/MoreAppsPage$1:access$0	(Lcom/kebikids/moreapps/MoreAppsPage$1;)Lcom/kebikids/moreapps/MoreAppsPage;
          //   196: invokestatic 60	com/kebikids/moreapps/MoreAppsPage:access$3	(Lcom/kebikids/moreapps/MoreAppsPage;)Lcom/kebikids/moreapps/MoreAppsData;
          //   199: getfield 70	com/kebikids/moreapps/MoreAppsData:appLinks	Ljava/util/ArrayList;
          //   202: iload 4
          //   204: invokevirtual 94	java/util/ArrayList:get	(I)Ljava/lang/Object;
          //   207: checkcast 96	java/lang/String
          //   210: invokestatic 100	com/kebikids/moreapps/MoreAppsPage:access$4	(Lcom/kebikids/moreapps/MoreAppsPage;Ljava/lang/String;)Z
          //   213: istore 6
          //   215: iload_1
          //   216: istore_2
          //   217: iload_1
          //   218: istore_3
          //   219: aload_0
          //   220: getfield 17	com/kebikids/moreapps/MoreAppsPage$1$1:this$1	Lcom/kebikids/moreapps/MoreAppsPage$1;
          //   223: invokestatic 28	com/kebikids/moreapps/MoreAppsPage$1:access$0	(Lcom/kebikids/moreapps/MoreAppsPage$1;)Lcom/kebikids/moreapps/MoreAppsPage;
          //   226: invokestatic 60	com/kebikids/moreapps/MoreAppsPage:access$3	(Lcom/kebikids/moreapps/MoreAppsPage;)Lcom/kebikids/moreapps/MoreAppsData;
          //   229: getfield 67	com/kebikids/moreapps/MoreAppsData:isSetup	Ljava/util/ArrayList;
          //   232: iload 6
          //   234: invokestatic 106	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
          //   237: invokevirtual 110	java/util/ArrayList:add	(Ljava/lang/Object;)Z
          //   240: pop
          //   241: iload_1
          //   242: istore_2
          //   243: iload 6
          //   245: ifeq +7 -> 252
          //   248: iload_1
          //   249: iconst_1
          //   250: iadd
          //   251: istore_2
          //   252: iload 4
          //   254: iconst_1
          //   255: iadd
          //   256: istore 4
          //   258: iload_2
          //   259: istore_1
          //   260: goto -147 -> 113
          //   263: astore 7
          //   265: aload 12
          //   267: astore 9
          //   269: aload 8
          //   271: astore 7
          //   273: aload_0
          //   274: getfield 17	com/kebikids/moreapps/MoreAppsPage$1$1:this$1	Lcom/kebikids/moreapps/MoreAppsPage$1;
          //   277: invokestatic 28	com/kebikids/moreapps/MoreAppsPage$1:access$0	(Lcom/kebikids/moreapps/MoreAppsPage$1;)Lcom/kebikids/moreapps/MoreAppsPage;
          //   280: invokestatic 78	com/kebikids/moreapps/MoreAppsPage:access$1	(Lcom/kebikids/moreapps/MoreAppsPage;)Landroid/os/Handler;
          //   283: iload_3
          //   284: invokevirtual 84	android/os/Handler:sendEmptyMessage	(I)Z
          //   287: pop
          //   288: aload 9
          //   290: ifnull +8 -> 298
          //   293: aload 9
          //   295: invokevirtual 87	java/io/ObjectInputStream:close	()V
          //   298: aload 7
          //   300: ifnull -123 -> 177
          //   303: aload 7
          //   305: invokevirtual 90	java/io/FileInputStream:close	()V
          //   308: return
          //   309: astore 7
          //   311: aload 7
          //   313: invokevirtual 113	java/lang/Exception:printStackTrace	()V
          //   316: return
          //   317: astore 7
          //   319: aload 11
          //   321: astore 8
          //   323: aload_0
          //   324: getfield 17	com/kebikids/moreapps/MoreAppsPage$1$1:this$1	Lcom/kebikids/moreapps/MoreAppsPage$1;
          //   327: invokestatic 28	com/kebikids/moreapps/MoreAppsPage$1:access$0	(Lcom/kebikids/moreapps/MoreAppsPage$1;)Lcom/kebikids/moreapps/MoreAppsPage;
          //   330: invokestatic 78	com/kebikids/moreapps/MoreAppsPage:access$1	(Lcom/kebikids/moreapps/MoreAppsPage;)Landroid/os/Handler;
          //   333: iload_2
          //   334: invokevirtual 84	android/os/Handler:sendEmptyMessage	(I)Z
          //   337: pop
          //   338: aload 8
          //   340: ifnull +8 -> 348
          //   343: aload 8
          //   345: invokevirtual 87	java/io/ObjectInputStream:close	()V
          //   348: aload 9
          //   350: ifnull +8 -> 358
          //   353: aload 9
          //   355: invokevirtual 90	java/io/FileInputStream:close	()V
          //   358: aload 7
          //   360: athrow
          //   361: astore 8
          //   363: aload 8
          //   365: invokevirtual 113	java/lang/Exception:printStackTrace	()V
          //   368: goto -10 -> 358
          //   371: astore 7
          //   373: aload 7
          //   375: invokevirtual 113	java/lang/Exception:printStackTrace	()V
          //   378: return
          //   379: astore 11
          //   381: aload 10
          //   383: astore 8
          //   385: aload 7
          //   387: astore 9
          //   389: aload 11
          //   391: astore 7
          //   393: goto -70 -> 323
          //   396: astore 8
          //   398: aload 10
          //   400: astore 9
          //   402: goto -129 -> 273
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	405	0	this	1
          //   23	237	1	i	int
          //   15	319	2	j	int
          //   13	271	3	k	int
          //   17	240	4	m	int
          //   20	120	5	n	int
          //   213	31	6	bool	boolean
          //   39	134	7	localFileInputStream	java.io.FileInputStream
          //   263	1	7	localException1	Exception
          //   271	33	7	localObject1	Object
          //   309	3	7	localException2	Exception
          //   317	42	7	localObject2	Object
          //   371	15	7	localException3	Exception
          //   391	1	7	localObject3	Object
          //   4	340	8	localObject4	Object
          //   361	3	8	localException4	Exception
          //   383	1	8	localObject5	Object
          //   396	1	8	localException5	Exception
          //   1	400	9	localObject6	Object
          //   58	341	10	localObjectInputStream	java.io.ObjectInputStream
          //   7	313	11	localObject7	Object
          //   379	11	11	localObject8	Object
          //   10	256	12	localObject9	Object
          // Exception table:
          //   from	to	target	type
          //   24	41	263	java/lang/Exception
          //   49	60	263	java/lang/Exception
          //   293	298	309	java/lang/Exception
          //   303	308	309	java/lang/Exception
          //   24	41	317	finally
          //   49	60	317	finally
          //   343	348	361	java/lang/Exception
          //   353	358	361	java/lang/Exception
          //   162	167	371	java/lang/Exception
          //   172	177	371	java/lang/Exception
          //   66	84	379	finally
          //   90	110	379	finally
          //   117	135	379	finally
          //   182	215	379	finally
          //   219	241	379	finally
          //   66	84	396	java/lang/Exception
          //   90	110	396	java/lang/Exception
          //   117	135	396	java/lang/Exception
          //   182	215	396	java/lang/Exception
          //   219	241	396	java/lang/Exception
        }
      }).start();
      super.handleMessage(paramAnonymousMessage);
    }
  };
  private Handler moreAppsInfoProcessFinished = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      MoreAppsPage.this.adapter = new MoreAppsPage.AppList_GridAdapter(MoreAppsPage.this, MoreAppsPage.this.mActivity, MoreAppsPage.this.moreAppsData);
      MoreAppsPage.this.appList.setAdapter(MoreAppsPage.this.adapter);
      if ((MoreAppsPage.this.moreAppsData.appLinks.size() != 0) && (MoreAppsPage.this.moreAppsData.appLinks.size() != paramAnonymousMessage.what))
      {
        Object localObject = new ImageView(MoreAppsPage.this.mActivity);
        ((ImageView)localObject).setBackgroundDrawable(G.getDrawableFromAssets("MoreApps/TopMsg.png", true));
        MoreAppsPage.this.params = new RelativeLayout.LayoutParams(G.cWH(440, 604), G.cWH(42, 60));
        MoreAppsPage.this.params.setMargins(G.cX(305, 518), G.cY(0, 73), 0, 0);
        ((ImageView)localObject).setLayoutParams(MoreAppsPage.this.params);
        MoreAppsPage.this.layout.addView((View)localObject);
        localObject = new TextView(MoreAppsPage.this.mActivity);
        MoreAppsPage.this.params = new RelativeLayout.LayoutParams(G.cWH(50, 60), G.cWH(50, 60));
        MoreAppsPage.this.params.setMargins(G.cX(562, 882), G.cY(5, 71), 0, 0);
        ((TextView)localObject).setLayoutParams(MoreAppsPage.this.params);
        ((TextView)localObject).setTextColor(Color.rgb(254, 227, 50));
        ((TextView)localObject).setTextSize(0, G.cWH(30, 46));
        ((TextView)localObject).setGravity(21);
        MoreAppsPage.this.layout.addView((View)localObject);
        ((TextView)localObject).setText(MoreAppsPage.this.moreAppsData.appLinks.size() - paramAnonymousMessage.what);
      }
      G.dismissProgressDialog();
    }
  };
  private RelativeLayout.LayoutParams params;
  
  public MoreAppsPage() {}
  
  private Bitmap byteToBitmap(byte[] paramArrayOfByte)
  {
    return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  private void cellBtClick(View paramView)
  {
    SoundManager.soundPlay(2);
    this.allBtLock = true;
    paramView = checkAppInstalled(((Integer)paramView.getTag()).intValue());
    if (paramView != null)
    {
      this.mActivity.startActivity(paramView);
      finish();
      return;
    }
    finish();
  }
  
  private Intent checkAppInstalled(int paramInt)
  {
    PackageManager localPackageManager = this.mActivity.getPackageManager();
    Object localObject = localPackageManager.getInstalledApplications(128);
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse((String)this.moreAppsData.links.get(paramInt)));
    String str = (String)this.moreAppsData.appLinks.get(paramInt);
    localObject = ((List)localObject).iterator();
    ApplicationInfo localApplicationInfo;
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        return localIntent;
      }
      localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
    } while (!str.equals(localApplicationInfo.packageName));
    if (str.equals(this.mActivity.getPackageName())) {
      return null;
    }
    return new Intent(localPackageManager.getLaunchIntentForPackage(localApplicationInfo.packageName));
  }
  
  private boolean checkAppIsInstalled(String paramString)
  {
    Iterator localIterator = this.mActivity.getPackageManager().getInstalledApplications(128).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!paramString.equals(((ApplicationInfo)localIterator.next()).packageName));
    return true;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().addFlags(128);
  }
  
  protected void onDestroy()
  {
    G.recursiveRecycle(getWindow().getDecorView());
    if (this.adapter != null) {
      this.adapter.recycle();
    }
    System.gc();
    super.onDestroy();
  }
  
  protected void onResume()
  {
    this.allBtLock = false;
    super.onResume();
  }
  
  protected void onStart()
  {
    this.layout = new RelativeLayout(this.mActivity);
    this.params = new RelativeLayout.LayoutParams(-2, -2);
    this.layout.setLayoutParams(this.params);
    setContentView(this.layout);
    this.layout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MoreAppsPage.this.finish();
        MoreAppsPage.this.overridePendingTransition(0, 0);
      }
    });
    Object localObject = new ImageView(this.mActivity);
    ((ImageView)localObject).setBackgroundDrawable(G.getDrawableFromAssets("MoreApps/Background.png", true));
    this.params = new RelativeLayout.LayoutParams(G.cWH(800, 1192), G.cWH(480, 618));
    this.params.setMargins(G.cX(0, 44), G.cY(0, 51), 0, 0);
    ((ImageView)localObject).setLayoutParams(this.params);
    this.layout.addView((View)localObject);
    localObject = new ImageButton(this.mActivity);
    ((ImageButton)localObject).setBackgroundDrawable(G.getDrawableFromAssets("MoreApps/CloseBt_Normal.png", true));
    this.params = new RelativeLayout.LayoutParams(G.cWH(60, 84), G.cWH(60, 84));
    this.params.setMargins(G.cX(740, 1126), G.cY(0, 65), 0, 0);
    ((ImageButton)localObject).setLayoutParams(this.params);
    this.layout.addView((View)localObject);
    ((ImageButton)localObject).setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if (paramAnonymousMotionEvent.getAction() == 0) {
          paramAnonymousView.setBackgroundDrawable(G.getDrawableFromAssets("MoreApps/CloseBt_Down.png", true));
        }
        for (;;)
        {
          return false;
          if (paramAnonymousMotionEvent.getAction() == 1) {
            paramAnonymousView.setBackgroundDrawable(G.getDrawableFromAssets("MoreApps/CloseBt_Normal.png", true));
          }
        }
      }
    });
    ((ImageButton)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (G.doubleClickDefense(paramAnonymousView, 500))
        {
          SoundManager.soundPlay(1);
          MoreAppsPage.this.finish();
          MoreAppsPage.this.overridePendingTransition(0, 0);
        }
      }
    });
    this.appList = new GridView(this.mActivity);
    this.appList.setSelector(new ColorDrawable(0));
    if (G.isBigResolution) {
      this.appList.setNumColumns(3);
    }
    for (;;)
    {
      this.appList.setVerticalScrollBarEnabled(false);
      this.appList.setScrollbarFadingEnabled(false);
      this.params = new RelativeLayout.LayoutParams(G.cWH(800, 1152), G.cWH(420, 504));
      this.params.setMargins(G.cX(0, 64), G.cY(60, 144), 0, 0);
      this.appList.setLayoutParams(this.params);
      this.layout.addView(this.appList);
      new MoreAppsDataDownloader(this.mActivity, this.moreAppsInfoLoadFinished, null).dataDownload(true, 0);
      super.onStart();
      return;
      this.appList.setNumColumns(2);
    }
  }
  
  class AppList_CellWrapper
  {
    MoreAppsPage.CellView base;
    private Button cellBt = null;
    private ImageView setupMark = null;
    private ImageView thumImg = null;
    private TextView titleTxt = null;
    
    AppList_CellWrapper(MoreAppsPage.CellView paramCellView)
    {
      this.base = paramCellView;
    }
    
    public Button getCellBt()
    {
      if (this.cellBt == null) {
        this.cellBt = this.base.cellBt;
      }
      return this.cellBt;
    }
    
    public ImageView getSetupMark()
    {
      if (this.setupMark == null) {
        this.setupMark = this.base.setupMark;
      }
      return this.setupMark;
    }
    
    public ImageView getThumImg()
    {
      if (this.thumImg == null) {
        this.thumImg = this.base.thumImg;
      }
      return this.thumImg;
    }
    
    public TextView getTitleTxt()
    {
      if (this.titleTxt == null) {
        this.titleTxt = this.base.titleTxt;
      }
      return this.titleTxt;
    }
  }
  
  class AppList_GridAdapter
    extends BaseAdapter
  {
    private MoreAppsData datas;
    private Activity mActivity;
    private List<WeakReference<View>> mRecycleList = new ArrayList();
    
    public AppList_GridAdapter(Context paramContext, MoreAppsData paramMoreAppsData)
    {
      this.mActivity = ((Activity)paramContext);
      this.datas = paramMoreAppsData;
    }
    
    public int getCount()
    {
      return this.datas.titles.size();
    }
    
    public Object getItem(int paramInt)
    {
      return null;
    }
    
    public long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramView = (MoreAppsPage.CellView)paramView;
      Object localObject;
      if (paramView == null)
      {
        paramView = new MoreAppsPage.CellView(MoreAppsPage.this, this.mActivity);
        paramViewGroup = new MoreAppsPage.AppList_CellWrapper(MoreAppsPage.this, paramView);
        paramView.setTag(paramViewGroup);
        localObject = paramViewGroup.getCellBt();
        ((Button)localObject).setTag(Integer.valueOf(paramInt));
        ((Button)localObject).setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            return false;
          }
        });
        ((Button)localObject).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            MoreAppsPage.CellView localCellView;
            if (!MoreAppsPage.this.allBtLock)
            {
              localCellView = (MoreAppsPage.CellView)MoreAppsPage.this.appList.getChildAt(((Integer)paramAnonymousView.getTag()).intValue());
              if (localCellView != null)
              {
                if (!((Boolean)MoreAppsPage.this.moreAppsData.isSetup.get(((Integer)paramAnonymousView.getTag()).intValue())).booleanValue()) {
                  break label104;
                }
                localCellView.setupMark.setBackgroundDrawable(G.getDrawableFromAssets("MoreApps/RunBt_Normal.png", false));
              }
            }
            for (;;)
            {
              MoreAppsPage.this.cellBtClick(paramAnonymousView);
              return;
              label104:
              localCellView.setupMark.setBackgroundDrawable(G.getDrawableFromAssets("MoreApps/InstallBt_Normal.png", false));
            }
          }
        });
        localObject = paramViewGroup.getThumImg();
        byte[] arrayOfByte = (byte[])MoreAppsPage.this.moreAppsData.thumBytes.get(paramInt);
        if (arrayOfByte == null) {
          break label225;
        }
        ((ImageView)localObject).setBackgroundDrawable(new BitmapDrawable(MoreAppsPage.this.byteToBitmap(arrayOfByte)));
        label135:
        paramViewGroup.getTitleTxt().setText((CharSequence)this.datas.titles.get(paramInt));
        paramViewGroup = paramViewGroup.getSetupMark();
        if (!((Boolean)MoreAppsPage.this.moreAppsData.isSetup.get(paramInt)).booleanValue()) {
          break label234;
        }
        paramViewGroup.setBackgroundDrawable(G.getDrawableFromAssets("MoreApps/RunBt_Normal.png", false));
      }
      for (;;)
      {
        this.mRecycleList.add(new WeakReference(paramView));
        return paramView;
        paramViewGroup = (MoreAppsPage.AppList_CellWrapper)paramView.getTag();
        break;
        label225:
        ((ImageView)localObject).setBackgroundColor(0);
        break label135;
        label234:
        paramViewGroup.setBackgroundDrawable(G.getDrawableFromAssets("MoreApps/InstallBt_Normal.png", false));
      }
    }
    
    public void recycle()
    {
      Iterator localIterator = this.mRecycleList.iterator();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return;
        }
        G.recursiveRecycle((View)((WeakReference)localIterator.next()).get());
      }
    }
  }
  
  class CellView
    extends RelativeLayout
  {
    public Button cellBt;
    public ImageView setupMark;
    public ImageView thumImg;
    public TextView titleTxt;
    
    public CellView(Context paramContext)
    {
      super();
      this.cellBt = new Button(paramContext);
      this.cellBt.setBackgroundColor(0);
      MoreAppsPage.this.params = new RelativeLayout.LayoutParams(G.cWH(400, 426), G.cWH(170, 157));
      this.cellBt.setLayoutParams(MoreAppsPage.this.params);
      addView(this.cellBt);
      this.thumImg = new ImageView(paramContext);
      MoreAppsPage.this.params = new RelativeLayout.LayoutParams(G.cWH(135, 135), G.cWH(135, 135));
      MoreAppsPage.this.params.setMargins(G.cWH(30, 30), G.cWH(20, 20), 0, 0);
      this.thumImg.setLayoutParams(MoreAppsPage.this.params);
      addView(this.thumImg);
      this.titleTxt = new TextView(paramContext);
      MoreAppsPage.this.params = new RelativeLayout.LayoutParams(G.cWH(205, 205), G.cWH(36, 36));
      MoreAppsPage.this.params.setMargins(G.cWH(188, 188), G.cWH(38, 38), 0, 0);
      this.titleTxt.setTextColor(-1);
      this.titleTxt.setTextSize(0, G.cWH(24, 24));
      this.titleTxt.setLayoutParams(MoreAppsPage.this.params);
      addView(this.titleTxt);
      this.setupMark = new ImageView(paramContext);
      MoreAppsPage.this.params = new RelativeLayout.LayoutParams(G.cWH(144, 144), G.cWH(50, 50));
      MoreAppsPage.this.params.setMargins(G.cWH(185, 185), G.cWH(85, 85), 0, 0);
      this.setupMark.setLayoutParams(MoreAppsPage.this.params);
      addView(this.setupMark);
    }
  }
}
