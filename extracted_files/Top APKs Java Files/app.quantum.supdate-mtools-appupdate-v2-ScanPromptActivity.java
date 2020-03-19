package mtools.appupdate.v2;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.adshandler.AHandler;
import java.io.PrintStream;
import java.util.ArrayList;
import mtools.appupdate.Preference;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ScanPromptActivity
  extends AppCompatActivity
{
  ImageView appIcon;
  TextView appName;
  private String appname;
  private ArrayList<ApplicationInfo> installList;
  private NewUpdateFoundAsyncTask newUpdateFoundAsyncTask;
  private PackageManager packageManager;
  private ArrayList<String> pkgArr;
  private String pkgName;
  private Preference preference;
  TextView totalSize;
  TextView updateSize;
  private ArrayList<String> verArr;
  private String verName;
  
  public ScanPromptActivity() {}
  
  public ArrayList<ApplicationInfo> getInstalledApps()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = (ArrayList)this.packageManager.getInstalledPackages(0);
    this.verArr = new ArrayList();
    this.pkgArr = new ArrayList();
    int i = 0;
    while (i < localArrayList2.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localArrayList2.get(i);
      if (!isSystemPackage(localPackageInfo))
      {
        this.verName = localPackageInfo.versionName;
        this.pkgName = localPackageInfo.packageName;
        this.pkgArr.add(this.pkgName);
        this.verArr.add(this.verName);
        localArrayList1.add(new ApplicationInfo());
      }
      i += 1;
    }
    return localArrayList1;
  }
  
  public boolean isSystemPackage(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  public void onBackPressed()
  {
    System.out.println("checking logs 122 1 " + this.newUpdateFoundAsyncTask.getStatus());
    if ((this.newUpdateFoundAsyncTask != null) && (this.newUpdateFoundAsyncTask.getStatus() == AsyncTask.Status.RUNNING)) {
      this.newUpdateFoundAsyncTask.cancel(true);
    }
    this.preference.setScanPromp(Boolean.valueOf(true));
    finish();
    super.onBackPressed();
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903210);
    this.appIcon = ((ImageView)findViewById(2131690111));
    this.appName = ((TextView)findViewById(2131689633));
    this.updateSize = ((TextView)findViewById(2131690112));
    this.totalSize = ((TextView)findViewById(2131690113));
    this.packageManager = getPackageManager();
    this.preference = new Preference(this);
    this.installList = getInstalledApps();
    this.newUpdateFoundAsyncTask = new NewUpdateFoundAsyncTask(this.pkgArr);
    this.newUpdateFoundAsyncTask.execute(new Void[0]);
    ((LinearLayout)findViewById(2131690116)).addView(new AHandler().showNativeMedium(this));
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if ((this.newUpdateFoundAsyncTask != null) && (this.newUpdateFoundAsyncTask.getStatus() != AsyncTask.Status.FINISHED)) {
      this.newUpdateFoundAsyncTask.cancel(true);
    }
    System.out.println("ScanPromptActivity.onStop ");
  }
  
  protected void onResume()
  {
    super.onResume();
    System.out.println("checking logs 122 " + this.newUpdateFoundAsyncTask.isCancelled());
  }
  
  private class NewUpdateFoundAsyncTask
    extends AsyncTask<Void, Integer, ArrayList<String>>
  {
    private ArrayList<String> checkList;
    private ArrayList<String> list;
    private String newVersion;
    private String pkg;
    private ArrayList<String> variesList;
    
    public NewUpdateFoundAsyncTask()
    {
      Object localObject;
      this.list = localObject;
      this.checkList = new ArrayList();
      this.variesList = new ArrayList();
    }
    
    protected ArrayList<String> doInBackground(Void... paramVarArgs)
    {
      int i = 0;
      for (;;)
      {
        if (i < this.list.size()) {
          this.pkg = ((String)this.list.get(i));
        }
        try
        {
          this.newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + this.pkg).timeout(30000).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").referrer("http://www.google.com").get().select("div:containsOwn(Current Version)").next().text();
          if (((String)ScanPromptActivity.this.verArr.get(i)).equals(this.newVersion))
          {
            System.out.println("equals");
            System.out.println("NewUpdateFoundAsyncTask.doInBackground equals " + this.newVersion);
          }
          for (;;)
          {
            publishProgress(new Integer[] { Integer.valueOf(i), Integer.valueOf(this.list.size()) });
            if (!isCancelled()) {
              break label302;
            }
            System.out.println("ScanPromptActivity NewUpdateFoundAsyncTask.doInBackground");
            return this.checkList;
            if (!this.newVersion.equalsIgnoreCase("Varies with device")) {
              break;
            }
            this.variesList.add(this.pkg);
            System.out.println("NewUpdateFoundAsyncTask.doInBackground " + this.newVersion);
          }
        }
        catch (Exception paramVarArgs)
        {
          for (;;)
          {
            System.out.println("here is exception asynctask  " + paramVarArgs);
            continue;
            this.checkList.add(this.pkg);
          }
          label302:
          i += 1;
        }
      }
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
      System.out.println("ScanPromptActivity NewUpdateFoundAsyncTask.onCancelled");
      ScanPromptActivity.this.preference.setScanPromp(Boolean.valueOf(true));
      ScanPromptActivity.this.finish();
    }
    
    protected void onPostExecute(ArrayList<String> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      System.out.println("NewUpdateFoundAsyncTask.onPostExecute checkList " + this.checkList.size() + " variesList " + this.variesList.size() + " " + paramArrayList.size());
      ScanPromptActivity.this.preference.setUpdateApps(this.checkList);
      ScanPromptActivity.this.preference.setVariesApps(this.variesList);
      ScanPromptActivity.this.preference.setScanPromp(Boolean.valueOf(true));
      ScanPromptActivity.this.finish();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
    
    protected void onProgressUpdate(Integer... paramVarArgs)
    {
      ScanPromptActivity.this.updateSize.setText("" + paramVarArgs[0]);
      ScanPromptActivity.this.totalSize.setText("/" + paramVarArgs[1]);
      try
      {
        paramVarArgs = String.valueOf(ScanPromptActivity.this.packageManager.getApplicationLabel(ScanPromptActivity.this.packageManager.getApplicationInfo(this.pkg, 128)));
        Drawable localDrawable = ScanPromptActivity.this.packageManager.getApplicationIcon(this.pkg);
        ScanPromptActivity.this.appName.setText("Checking updates for " + paramVarArgs);
        ScanPromptActivity.this.appIcon.setImageDrawable(localDrawable);
        return;
      }
      catch (PackageManager.NameNotFoundException paramVarArgs)
      {
        System.out.println("hello meenu " + paramVarArgs.getMessage());
        paramVarArgs.printStackTrace();
      }
    }
  }
}
