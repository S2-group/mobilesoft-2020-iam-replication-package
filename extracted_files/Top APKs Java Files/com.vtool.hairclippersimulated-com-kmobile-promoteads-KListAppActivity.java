package com.kmobile.promoteads;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;

public class KListAppActivity
  extends Activity
{
  private static List<ApplicationInfo> packages;
  private KListViewAdapter adapter;
  private ArrayList<KAppObject> arrayListAll;
  private Button btExit;
  private Button btTryAgain;
  private ListView listView;
  private PackageManager pm;
  private ProgressBar prgressLoading;
  
  public KListAppActivity() {}
  
  public boolean isInstall(String paramString, Context paramContext)
  {
    int i = 0;
    while (i < packages.size())
    {
      if (((ApplicationInfo)packages.get(i)).packageName.contains(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.pm = getPackageManager();
    packages = this.pm.getInstalledApplications(128);
    requestWindowFeature(1);
    setContentView(R.layout.k_activity_promote);
    this.prgressLoading = ((ProgressBar)findViewById(R.id.progressBar));
    this.btTryAgain = ((Button)findViewById(R.id.buttonTryAgain));
    this.btExit = ((Button)findViewById(R.id.imageViewExit));
    this.btTryAgain.setVisibility(4);
    this.btTryAgain.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        KListAppActivity.this.btTryAgain.setVisibility(4);
        new KListAppActivity.ShowListAppAsy(KListAppActivity.this, null).execute(new Void[0]);
      }
    });
    this.btExit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        KListAppActivity.this.finish();
      }
    });
    this.arrayListAll = new ArrayList();
    new ShowListAppAsy(null).execute(new Void[0]);
  }
  
  public ArrayList<KAppObject> removeAppInstalled(ArrayList<KAppObject> paramArrayList, Context paramContext)
  {
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      int m = i;
      int k = j;
      if (isInstall(((KAppObject)paramArrayList.get(i)).getIdApp(), paramContext))
      {
        paramArrayList.remove(i);
        m = i - 1;
        k = j - 1;
      }
      i = m + 1;
      j = k;
    }
    return paramArrayList;
  }
  
  private class ShowListAppAsy
    extends AsyncTask<Void, Void, Void>
  {
    private ShowListAppAsy() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = (ArrayList)KUtil.readObj(KListAppActivity.this, "save.dat");
      if (paramVarArgs != null)
      {
        KListAppActivity.access$302(KListAppActivity.this, KListAppActivity.this.removeAppInstalled(paramVarArgs, KListAppActivity.this));
        KUtil.writeObj(KListAppActivity.this, KListAppActivity.this.arrayListAll, "save.dat");
        if (KListAppActivity.this.arrayListAll.size() > 1)
        {
          int i = KUtil.getRandomNumber(1, KListAppActivity.this.arrayListAll.size() - 1);
          KListAppActivity.this.arrayListAll.add(0, KListAppActivity.this.arrayListAll.get(i));
          KListAppActivity.this.arrayListAll.remove(i + 1);
        }
      }
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      try
      {
        if (KListAppActivity.this.prgressLoading.isShown()) {
          KListAppActivity.this.prgressLoading.setVisibility(4);
        }
        if ((KListAppActivity.this.arrayListAll == null) || (KListAppActivity.this.arrayListAll.size() < 1))
        {
          KListAppActivity.this.btTryAgain.setVisibility(0);
          return;
        }
        KListAppActivity.access$402(KListAppActivity.this, (ListView)KListAppActivity.this.findViewById(R.id.listView1));
        KListAppActivity.access$502(KListAppActivity.this, new KListViewAdapter(KListAppActivity.this, KListAppActivity.this.arrayListAll));
        KListAppActivity.this.listView.setAdapter(KListAppActivity.this.adapter);
        return;
      }
      catch (Exception paramVoid)
      {
        paramVoid.printStackTrace();
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      if (!KListAppActivity.this.prgressLoading.isShown()) {
        KListAppActivity.this.prgressLoading.setVisibility(0);
      }
    }
  }
}
