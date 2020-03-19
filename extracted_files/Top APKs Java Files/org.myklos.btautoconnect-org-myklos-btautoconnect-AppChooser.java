package org.myklos.btautoconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class AppChooser
  extends Activity
  implements AdapterView.OnItemClickListener
{
  public static final String APP_PACKAGE_NAME = "package_name";
  private static final boolean INCLUDE_SYSTEM_APPS = true;
  private AppListAdapter mAdapter;
  private List<App> mApps;
  private ListView mAppsList;
  
  public AppChooser() {}
  
  private List<App> loadInstalledApps(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = getPackageManager();
    List localList = localPackageManager.getInstalledPackages(0);
    int i = 0;
    if (i >= localList.size())
    {
      Collections.sort(localArrayList, new CustomComparator());
      return localArrayList;
    }
    Object localObject1 = (PackageInfo)localList.get(i);
    Object localObject2 = ((PackageInfo)localObject1).applicationInfo;
    if ((!paramBoolean) && ((((ApplicationInfo)localObject2).flags & 0x1) == 1)) {}
    Intent localIntent;
    do
    {
      i += 1;
      break;
      localIntent = localPackageManager.getLaunchIntentForPackage(((PackageInfo)localObject1).packageName);
    } while (localIntent == null);
    localObject2 = new App();
    ((App)localObject2).setTitle(((PackageInfo)localObject1).applicationInfo.loadLabel(localPackageManager).toString());
    ((App)localObject2).setPackageName(((PackageInfo)localObject1).packageName);
    ((App)localObject2).setVersionName(((PackageInfo)localObject1).versionName);
    ((App)localObject2).setVersionCode(((PackageInfo)localObject1).versionCode);
    ((App)localObject2).setActivity(localIntent);
    localObject1 = ((PackageInfo)localObject1).applicationInfo.loadDescription(localPackageManager);
    if (localObject1 != null) {}
    for (localObject1 = ((CharSequence)localObject1).toString();; localObject1 = "")
    {
      ((App)localObject2).setDescription((String)localObject1);
      localArrayList.add(localObject2);
      break;
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903040);
    this.mAppsList = ((ListView)findViewById(2131099657));
    this.mAppsList.setOnItemClickListener(this);
    this.mApps = loadInstalledApps(true);
    this.mAdapter = new AppListAdapter(this, 2130903041, this.mApps);
    this.mAppsList.setAdapter(this.mAdapter);
    new LoadIconsTask(null).execute((App[])this.mApps.toArray(new App[0]));
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = ((App)paramAdapterView.getItemAtPosition(paramInt)).getPackageName();
    paramView = new Intent();
    paramView.putExtra("package_name", paramAdapterView);
    setResult(-1, paramView);
    finish();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    }
    finish();
    return true;
  }
  
  public class App
  {
    private Intent activity;
    private String description;
    private String packageName;
    private String title;
    private int versionCode;
    private String versionName;
    
    public App() {}
    
    public Intent getActivity()
    {
      return this.activity;
    }
    
    public String getDescription()
    {
      return this.description;
    }
    
    public String getPackageName()
    {
      return this.packageName;
    }
    
    public String getTitle()
    {
      return this.title;
    }
    
    public int getVersionCode()
    {
      return this.versionCode;
    }
    
    public String getVersionName()
    {
      return this.versionName;
    }
    
    public void setActivity(Intent paramIntent)
    {
      this.activity = paramIntent;
    }
    
    public void setDescription(String paramString)
    {
      this.description = paramString;
    }
    
    public void setPackageName(String paramString)
    {
      this.packageName = paramString;
    }
    
    public void setTitle(String paramString)
    {
      this.title = paramString;
    }
    
    public void setVersionCode(int paramInt)
    {
      this.versionCode = paramInt;
    }
    
    public void setVersionName(String paramString)
    {
      this.versionName = paramString;
    }
  }
  
  public class AppListAdapter
    extends ArrayAdapter<AppChooser.App>
  {
    private List<AppChooser.App> list;
    private Map<String, Drawable> mIcons;
    
    public AppListAdapter(int paramInt, List<AppChooser.App> paramList)
    {
      super(paramList, localList);
      this.list = localList;
    }
    
    public Map<String, Drawable> getIcons()
    {
      return this.mIcons;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = ((LayoutInflater)AppChooser.this.getSystemService("layout_inflater")).inflate(2130903041, null);
        paramViewGroup = new AppViewHolder();
        paramViewGroup.mTitle = ((TextView)paramView.findViewById(2131099659));
        paramViewGroup.mIcon = ((ImageView)paramView.findViewById(2131099658));
        paramView.setTag(paramViewGroup);
      }
      AppChooser.App localApp;
      for (;;)
      {
        localApp = (AppChooser.App)AppChooser.this.mApps.get(paramInt);
        paramViewGroup.setTitle(localApp.getTitle());
        if ((this.mIcons != null) && (this.mIcons.get(localApp.getPackageName()) != null)) {
          break;
        }
        paramViewGroup.setIcon(null);
        return paramView;
        paramViewGroup = (AppViewHolder)paramView.getTag();
      }
      paramViewGroup.setIcon((Drawable)this.mIcons.get(localApp.getPackageName()));
      return paramView;
    }
    
    public void setIcons(Map<String, Drawable> paramMap)
    {
      this.mIcons = paramMap;
    }
    
    public class AppViewHolder
    {
      private ImageView mIcon;
      private TextView mTitle;
      
      public AppViewHolder() {}
      
      public void setIcon(Drawable paramDrawable)
      {
        this.mIcon.setImageDrawable(paramDrawable);
      }
      
      public void setTitle(String paramString)
      {
        this.mTitle.setText(paramString);
      }
    }
  }
  
  public class CustomComparator
    implements Comparator<AppChooser.App>
  {
    public CustomComparator() {}
    
    public int compare(AppChooser.App paramApp1, AppChooser.App paramApp2)
    {
      return paramApp1.getTitle().compareToIgnoreCase(paramApp2.getTitle());
    }
  }
  
  private class LoadIconsTask
    extends AsyncTask<AppChooser.App, Void, Void>
  {
    private LoadIconsTask() {}
    
    /* Error */
    protected Void doInBackground(AppChooser.App... paramVarArgs)
    {
      // Byte code:
      //   0: new 34	java/util/HashMap
      //   3: dup
      //   4: invokespecial 35	java/util/HashMap:<init>	()V
      //   7: astore 6
      //   9: aload_0
      //   10: getfield 14	org/myklos/btautoconnect/AppChooser$LoadIconsTask:this$0	Lorg/myklos/btautoconnect/AppChooser;
      //   13: invokevirtual 39	org/myklos/btautoconnect/AppChooser:getApplicationContext	()Landroid/content/Context;
      //   16: invokevirtual 45	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   19: astore 7
      //   21: aload_1
      //   22: arraylength
      //   23: istore_3
      //   24: iconst_0
      //   25: istore_2
      //   26: iload_2
      //   27: iload_3
      //   28: if_icmplt +17 -> 45
      //   31: aload_0
      //   32: getfield 14	org/myklos/btautoconnect/AppChooser$LoadIconsTask:this$0	Lorg/myklos/btautoconnect/AppChooser;
      //   35: invokestatic 49	org/myklos/btautoconnect/AppChooser:access$1	(Lorg/myklos/btautoconnect/AppChooser;)Lorg/myklos/btautoconnect/AppChooser$AppListAdapter;
      //   38: aload 6
      //   40: invokevirtual 55	org/myklos/btautoconnect/AppChooser$AppListAdapter:setIcons	(Ljava/util/Map;)V
      //   43: aconst_null
      //   44: areturn
      //   45: aload_1
      //   46: iload_2
      //   47: aaload
      //   48: astore 8
      //   50: aconst_null
      //   51: astore 4
      //   53: aload 7
      //   55: aload 8
      //   57: invokevirtual 61	org/myklos/btautoconnect/AppChooser$App:getActivity	()Landroid/content/Intent;
      //   60: invokevirtual 67	android/content/pm/PackageManager:getActivityIcon	(Landroid/content/Intent;)Landroid/graphics/drawable/Drawable;
      //   63: astore 5
      //   65: aload 5
      //   67: astore 4
      //   69: aload 5
      //   71: checkcast 69	android/graphics/drawable/BitmapDrawable
      //   74: invokevirtual 73	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
      //   77: sipush 256
      //   80: sipush 256
      //   83: iconst_0
      //   84: invokestatic 79	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
      //   87: astore 9
      //   89: aload 5
      //   91: astore 4
      //   93: new 69	android/graphics/drawable/BitmapDrawable
      //   96: dup
      //   97: aload_0
      //   98: getfield 14	org/myklos/btautoconnect/AppChooser$LoadIconsTask:this$0	Lorg/myklos/btautoconnect/AppChooser;
      //   101: invokevirtual 83	org/myklos/btautoconnect/AppChooser:getResources	()Landroid/content/res/Resources;
      //   104: aload 9
      //   106: invokespecial 86	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
      //   109: astore 5
      //   111: aload 5
      //   113: astore 4
      //   115: aload 6
      //   117: aload 8
      //   119: invokevirtual 90	org/myklos/btautoconnect/AppChooser$App:getPackageName	()Ljava/lang/String;
      //   122: aload 4
      //   124: invokeinterface 96 3 0
      //   129: pop
      //   130: goto +30 -> 160
      //   133: astore 5
      //   135: aload 5
      //   137: invokevirtual 99	java/lang/Exception:printStackTrace	()V
      //   140: goto -25 -> 115
      //   143: astore 4
      //   145: aload 4
      //   147: invokevirtual 99	java/lang/Exception:printStackTrace	()V
      //   150: goto +10 -> 160
      //   153: astore 4
      //   155: aload 4
      //   157: invokevirtual 100	java/lang/Error:printStackTrace	()V
      //   160: iload_2
      //   161: iconst_1
      //   162: iadd
      //   163: istore_2
      //   164: goto -138 -> 26
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	167	0	this	LoadIconsTask
      //   0	167	1	paramVarArgs	AppChooser.App[]
      //   25	139	2	i	int
      //   23	6	3	j	int
      //   51	72	4	localObject1	Object
      //   143	3	4	localException1	Exception
      //   153	3	4	localError	Error
      //   63	49	5	localObject2	Object
      //   133	3	5	localException2	Exception
      //   7	109	6	localHashMap	java.util.HashMap
      //   19	35	7	localPackageManager	PackageManager
      //   48	70	8	localApp	AppChooser.App
      //   87	18	9	localBitmap	android.graphics.Bitmap
      // Exception table:
      //   from	to	target	type
      //   53	65	133	java/lang/Exception
      //   69	89	133	java/lang/Exception
      //   93	111	133	java/lang/Exception
      //   115	130	143	java/lang/Exception
      //   135	140	143	java/lang/Exception
      //   53	65	153	java/lang/Error
      //   69	89	153	java/lang/Error
      //   93	111	153	java/lang/Error
      //   115	130	153	java/lang/Error
      //   135	140	153	java/lang/Error
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      AppChooser.this.mAdapter.notifyDataSetChanged();
    }
  }
}
