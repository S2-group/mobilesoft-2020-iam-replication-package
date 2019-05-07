package com.stereomatch.utilitygenericrecorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import com.stereomatch.utilitygeneral3.q;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UtilityChooseAppActivity
  extends Activity
  implements AdapterView.OnItemClickListener
{
  private static int c = 0;
  private static boolean d = true;
  private ListView a = null;
  private List<cj> b = null;
  
  public UtilityChooseAppActivity() {}
  
  private List<cj> a(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = getPackageManager();
    int i = 0;
    List localList = localPackageManager.getInstalledPackages(0);
    while (i < localList.size())
    {
      Object localObject1 = (PackageInfo)localList.get(i);
      Object localObject2 = ((PackageInfo)localObject1).applicationInfo;
      if ((paramBoolean) || ((((ApplicationInfo)localObject2).flags & 0x1) != 1))
      {
        localObject2 = new cj();
        ((cj)localObject2).a(((PackageInfo)localObject1).applicationInfo.loadLabel(localPackageManager).toString());
        ((cj)localObject2).b(((PackageInfo)localObject1).packageName);
        ((cj)localObject2).c(((PackageInfo)localObject1).versionName);
        ((cj)localObject2).a(((PackageInfo)localObject1).versionCode);
        localObject1 = ((PackageInfo)localObject1).applicationInfo.loadDescription(localPackageManager);
        if (localObject1 != null) {
          localObject1 = ((CharSequence)localObject1).toString();
        } else {
          localObject1 = "";
        }
        ((cj)localObject2).d((String)localObject1);
        localArrayList.add(localObject2);
      }
      i += 1;
    }
    Collections.sort(localArrayList, new Comparator()
    {
      public int a(cj paramAnonymousCj1, cj paramAnonymousCj2)
      {
        return paramAnonymousCj1.a().compareToIgnoreCase(paramAnonymousCj2.a());
      }
    });
    return localArrayList;
  }
  
  public static void a(Context paramContext, int paramInt, boolean paramBoolean)
  {
    c = paramInt;
    d = paramBoolean;
    Intent localIntent = new Intent(paramContext, UtilityChooseAppActivity.class);
    localIntent.setFlags(536870912);
    paramContext.startActivity(localIntent);
  }
  
  private void a(String paramString1, String paramString2)
  {
    ce.a(this, paramString1, c);
    ce.b(this, paramString2, c);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (cf.b(this)) {
      setRequestedOrientation(0);
    } else {
      setRequestedOrientation(1);
    }
    setContentView(ab.d.activity_utility_choose_app);
    this.a = ((ListView)findViewById(ab.c.appslist));
    this.a.setOnItemClickListener(this);
    this.b = a(d);
    paramBundle = new ck(getApplicationContext());
    paramBundle.a(this.b);
    this.a.setAdapter(paramBundle);
    q.b(this);
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (cj)paramAdapterView.getItemAtPosition(paramInt);
    if (paramAdapterView == null) {
      return;
    }
    a(paramAdapterView.a(), paramAdapterView.b());
    paramView = new StringBuilder();
    paramView.append("Button now linked to:\n\n");
    paramView.append(paramAdapterView.a());
    paramView.append("\n\nPress Back key to return to app.");
    Toast.makeText(this, paramView.toString(), 0).show();
  }
  
  public void onStart()
  {
    super.onStart();
    q.c(this);
  }
  
  public void onStop()
  {
    super.onStop();
    q.d(this);
  }
}
