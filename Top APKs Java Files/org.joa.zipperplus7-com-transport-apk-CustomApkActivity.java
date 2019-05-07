package com.transport.apk;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.nostra13.universalimageloader.core.c.a;
import com.nostra13.universalimageloader.core.c.b;
import com.nostra13.universalimageloader.core.d;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.test.flashtest.util.CommonTask;
import org.test.flashtest.util.af;

public class CustomApkActivity
  extends AppCompatActivity
  implements View.OnClickListener
{
  final String a = "CustomApkActivity";
  protected d b = d.a();
  private c c;
  private ImageAdapter d;
  private com.nostra13.universalimageloader.core.c e;
  private BitmapDrawable f;
  private Toolbar g;
  private GridView h;
  private Button i;
  private Button j;
  private View k;
  
  public CustomApkActivity() {}
  
  private List<ApplicationInfo> a(List<ApplicationInfo> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return paramList;
    }
    ArrayList localArrayList = new ArrayList();
    int n = paramList.size();
    int m = 0;
    while (m < n)
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramList.get(m);
      if ((localApplicationInfo.flags & 0x1) == 0) {
        localArrayList.add(localApplicationInfo);
      }
      m += 1;
    }
    return localArrayList;
  }
  
  private void a()
  {
    this.g = ((Toolbar)findViewById(2131232338));
    this.g.inflateMenu(2131427373);
    setSupportActionBar(this.g);
    this.h = ((GridView)findViewById(2131230738));
    this.i = ((Button)findViewById(2131232164));
    this.j = ((Button)findViewById(2131230984));
    this.k = findViewById(2131231575);
    this.k.setVisibility(0);
    ((TextView)this.k.findViewById(2131231045)).setText(2131559341);
    this.i.setOnClickListener(this);
    this.j.setOnClickListener(this);
    this.h.setOnScrollListener(new PauseOnScrollListener(this.b, true, true));
    this.f = ((BitmapDrawable)getResources().getDrawable(2131165545));
  }
  
  private void a(String paramString1, String paramString2)
  {
    new org.test.flashtest.customview.roundcorner.a(this).setTitle(paramString1).setMessage(paramString2).setPositiveButton(2131559687, null).create().show();
  }
  
  private void b()
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "";
    if ((arrayOfString != null) && (arrayOfString.length > 0))
    {
      this.c = new c(arrayOfString[0]);
      this.c.startTask(new Void[] { (Void)null });
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    }
    do
    {
      do
      {
        return;
      } while (paramInt2 != -1);
      return;
    } while (paramInt2 != -1);
  }
  
  public void onBackPressed()
  {
    a.a.clear();
    setResult(0);
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    if (this.i == paramView)
    {
      paramView = new ArrayList();
      if (this.d != null)
      {
        m = 0;
        while (m < this.d.a.size())
        {
          localObject = (b)this.d.a.get(m);
          if ((((b)localObject).a) && (new File(((b)localObject).b.sourceDir).exists())) {
            paramView.add(((b)localObject).b.sourceDir);
          }
          m += 1;
        }
      }
      if (paramView.size() > 0)
      {
        localObject = new String[paramView.size()];
        paramView.toArray((Object[])localObject);
        localIntent = new Intent();
        localIntent.putExtra("extra_select_files", (String[])localObject);
        setResult(-1, localIntent);
        paramView.clear();
        finish();
      }
    }
    while (this.j != paramView)
    {
      int m;
      Object localObject;
      Intent localIntent;
      return;
      setResult(0);
      finish();
      return;
    }
    finish();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    af.a(this);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131362309);
    a();
    b();
    this.e = new c.a().a().c();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131427372, paramMenu);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.k.setVisibility(8);
    if (this.c != null) {
      this.c.a();
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    do
    {
      do
      {
        return true;
      } while (this.d == null);
      this.d.b();
      return true;
    } while (this.d == null);
    this.d.c();
    return true;
  }
  
  public class ImageAdapter
    extends BaseAdapter
    implements View.OnClickListener
  {
    public ArrayList<CustomApkActivity.b> a = new ArrayList();
    public boolean b = false;
    private LayoutInflater d = (LayoutInflater)CustomApkActivity.this.getSystemService("layout_inflater");
    private String e;
    private com.nostra13.universalimageloader.core.listener.a f = new CustomApkActivity.a(null);
    
    public ImageAdapter(String paramString)
    {
      this.e = paramString;
    }
    
    public void a()
    {
      this.a.clear();
      PackageManager localPackageManager = CustomApkActivity.this.getPackageManager();
      Object localObject = localPackageManager.getInstalledApplications(0);
      List localList = CustomApkActivity.a(CustomApkActivity.this, (List)localObject);
      int j = localList.size();
      int i = 0;
      for (;;)
      {
        if (i < j)
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
          CustomApkActivity.b localB = new CustomApkActivity.b(CustomApkActivity.this);
          localB.b = localApplicationInfo;
          try
          {
            PackageInfo localPackageInfo = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 0);
            StringBuilder localStringBuilder = new StringBuilder().append("version").append(" ");
            if (localPackageInfo.versionName == null) {}
            for (localObject = String.valueOf(localPackageInfo.versionCode);; localObject = localPackageInfo.versionName)
            {
              localB.e = ((String)localObject);
              localB.d = localPackageInfo.versionName;
              localB.f = localPackageInfo.versionCode;
              try
              {
                localB.c = localApplicationInfo.loadLabel(localPackageManager).toString();
                if (localApplicationInfo.sourceDir == null) {
                  break;
                }
                if (!localApplicationInfo.sourceDir.contains("/data/app-private")) {
                  break label253;
                }
                localB.g = true;
              }
              catch (Exception localException)
              {
                localException.printStackTrace();
              }
            }
            this.a.add(localB);
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            Log.e("CustomApkActivity", localNameNotFoundException.getLocalizedMessage(), localNameNotFoundException);
          }
          for (;;)
          {
            break;
            label253:
            File localFile = new File(localApplicationInfo.sourceDir);
            if (!localFile.exists()) {
              break;
            }
            localB.h = Uri.fromFile(localFile);
          }
        }
        CustomApkActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            CustomApkActivity.ImageAdapter.this.notifyDataSetChanged();
          }
        });
        return;
        i += 1;
      }
    }
    
    public void b()
    {
      Object localObject = this.a.iterator();
      int i = 0;
      while (((Iterator)localObject).hasNext())
      {
        ((CustomApkActivity.b)((Iterator)localObject).next()).a = true;
        i += 1;
      }
      localObject = String.format("%d items is selected", new Object[] { Integer.valueOf(i) });
      Toast.makeText(CustomApkActivity.this, (CharSequence)localObject, 0).show();
      notifyDataSetChanged();
    }
    
    public void c()
    {
      Object localObject = this.a.iterator();
      int i = 0;
      while (((Iterator)localObject).hasNext())
      {
        ((CustomApkActivity.b)((Iterator)localObject).next()).a = false;
        i += 1;
      }
      localObject = String.format("%d items is unselected", new Object[] { Integer.valueOf(i) });
      Toast.makeText(CustomApkActivity.this, (CharSequence)localObject, 0).show();
      notifyDataSetChanged();
    }
    
    public int getCount()
    {
      return this.a.size();
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
      if (paramView == null)
      {
        paramViewGroup = new CustomApkActivity.d(CustomApkActivity.this);
        paramView = this.d.inflate(2131362304, null);
        paramViewGroup.a = ((ImageView)paramView.findViewById(2131232317));
        paramViewGroup.b = ((CheckBox)paramView.findViewById(2131231505));
        paramViewGroup.c = ((TextView)paramView.findViewById(2131231713));
        paramView.setTag(paramViewGroup);
      }
      for (;;)
      {
        Object localObject = (CustomApkActivity.b)this.a.get(paramInt);
        paramViewGroup.b.setId(paramInt);
        paramViewGroup.a.setId(paramInt);
        paramViewGroup.a.setOnClickListener(this);
        paramViewGroup.a.setTag(2131232317, Integer.valueOf(paramInt));
        paramViewGroup.b.setChecked(((CustomApkActivity.b)localObject).a);
        paramViewGroup.b.setOnClickListener(this);
        paramViewGroup.a.setImageDrawable(CustomApkActivity.d(CustomApkActivity.this));
        if (((CustomApkActivity.b)localObject).h != null) {
          CustomApkActivity.this.b.a(((CustomApkActivity.b)localObject).b, CustomApkActivity.this.getPackageManager(), ((CustomApkActivity.b)localObject).h.toString(), paramViewGroup.a, CustomApkActivity.e(CustomApkActivity.this), paramInt, this.f);
        }
        localObject = ((CustomApkActivity.b)localObject).c;
        paramViewGroup.c.setText((CharSequence)localObject);
        paramViewGroup.c.setFocusable(true);
        paramViewGroup.c.setSelected(true);
        return paramView;
        paramViewGroup = (CustomApkActivity.d)paramView.getTag();
      }
    }
    
    public void onClick(View paramView)
    {
      if ((paramView instanceof CheckBox))
      {
        paramView = (CheckBox)paramView;
        i = paramView.getId();
        if (((CustomApkActivity.b)this.a.get(i)).a)
        {
          paramView.setChecked(false);
          ((CustomApkActivity.b)this.a.get(i)).a = false;
        }
      }
      while (!(paramView instanceof ImageView))
      {
        return;
        paramView.setChecked(true);
        ((CustomApkActivity.b)this.a.get(i)).a = true;
        return;
      }
      int i = ((ImageView)paramView).getId();
      paramView = (CustomApkActivity.b)this.a.get(i);
      CustomApkActivity.a(CustomApkActivity.this, paramView.c, paramView.b.sourceDir);
    }
  }
  
  private static class a
    extends com.nostra13.universalimageloader.core.listener.c
  {
    static final List<String> a = Collections.synchronizedList(new LinkedList());
    
    private a() {}
    
    public void a(String paramString, View paramView, Bitmap paramBitmap)
    {
      int i;
      if (paramBitmap != null)
      {
        paramView = (ImageView)paramView;
        if (a.contains(paramString)) {
          break label47;
        }
        i = 1;
        if (i == 0) {
          break label53;
        }
        b.a(paramView, 500);
      }
      for (;;)
      {
        a.add(paramString);
        return;
        label47:
        i = 0;
        break;
        label53:
        paramView.setImageBitmap(paramBitmap);
      }
    }
  }
  
  class b
  {
    boolean a;
    ApplicationInfo b;
    String c;
    String d;
    String e;
    int f;
    boolean g;
    Uri h;
    
    b() {}
  }
  
  class c
    extends CommonTask<Void, Void, Void>
  {
    private String b;
    
    public c(String paramString)
    {
      this.b = paramString;
    }
    
    protected Void a(Void... paramVarArgs)
    {
      CustomApkActivity.a(CustomApkActivity.this, new CustomApkActivity.ImageAdapter(CustomApkActivity.this, this.b));
      CustomApkActivity.b(CustomApkActivity.this).a();
      return null;
    }
    
    public void a()
    {
      if (CustomApkActivity.b(CustomApkActivity.this) != null) {
        CustomApkActivity.b(CustomApkActivity.this).b = true;
      }
      cancel(true);
    }
    
    protected void a(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      CustomApkActivity.a(CustomApkActivity.this).setVisibility(8);
      if (isCancelled()) {
        return;
      }
      CustomApkActivity.c(CustomApkActivity.this).setAdapter(CustomApkActivity.b(CustomApkActivity.this));
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      CustomApkActivity.a(CustomApkActivity.this).setVisibility(0);
    }
  }
  
  class d
  {
    ImageView a;
    CheckBox b;
    TextView c;
    
    d() {}
  }
}
