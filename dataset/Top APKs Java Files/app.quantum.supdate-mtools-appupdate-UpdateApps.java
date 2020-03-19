package mtools.appupdate;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import java.io.PrintStream;
import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class UpdateApps
  extends Activity
{
  String app;
  String appName;
  ArrayList<ApplicationInfo> installList;
  ArrayList<String> pkgArr;
  String pkgName;
  private PackageManager pm;
  ArrayList<String> verArr;
  String verName;
  
  public UpdateApps() {}
  
  public ArrayList<ApplicationInfo> getInstalledApps()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = (ArrayList)this.pm.getInstalledPackages(0);
    this.verArr = new ArrayList();
    this.pkgArr = new ArrayList();
    int i = 0;
    while (i < localArrayList2.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localArrayList2.get(i);
      if (!isSystemPackage(localPackageInfo))
      {
        this.appName = localPackageInfo.applicationInfo.loadLabel(this.pm).toString();
        this.verName = localPackageInfo.versionName;
        this.pkgName = localPackageInfo.packageName;
        this.pkgArr.add(this.pkgName);
        this.verArr.add(this.verName);
        System.out.println("DeviceApp in NotificationActivity " + this.appName + "  " + this.verName + "  " + this.pkgName);
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
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.pm = getPackageManager();
    this.installList = getInstalledApps();
    System.out.println("UpdateNotification " + this.installList.size());
    new NewUpdateFoundAsyncTask(this.pkgArr).execute(new Void[0]);
  }
  
  private class NewUpdateFoundAsyncTask
    extends AsyncTask<Void, ArrayList<String>, ArrayList<String>>
  {
    private ArrayList<String> checkList;
    private ArrayList<String> list;
    private String newVersion;
    private String pkg;
    
    public NewUpdateFoundAsyncTask()
    {
      Object localObject;
      this.list = localObject;
      this.checkList = new ArrayList();
    }
    
    protected ArrayList<String> doInBackground(Void... paramVarArgs)
    {
      int i = 0;
      for (;;)
      {
        if (i < this.list.size())
        {
          this.pkg = ((String)this.list.get(i));
          try
          {
            this.newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + this.pkg).timeout(30000).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").referrer("http://www.google.com").get().select("div:containsOwn(Current Version)").next().text();
            if (((String)UpdateApps.this.verArr.get(i)).equals(this.newVersion)) {
              System.out.println("equals");
            } else {
              this.checkList.add(this.newVersion);
            }
          }
          catch (Exception paramVarArgs)
          {
            System.out.println("here is exception asynctask  " + paramVarArgs);
          }
        }
        return this.checkList;
        i += 1;
      }
    }
    
    protected void onPostExecute(ArrayList<String> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      System.out.println("here is on post execute  " + paramArrayList.size());
    }
  }
}
