package mtools.appupdate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.adshandler.AHandler;
import java.io.File;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import mtools.appupdate.v2.ScanPromptActivity;
import mtools.appupdate.v2.SoftwareUpdateActivity;

public class UpdateForDownLoadedApp
  extends AppCompatActivity
{
  private AHandler aHandler;
  private DownloadedAppListViewAdapter appListViewAdapter;
  private String appName;
  private List<ApplicationInfo> applist = null;
  private List<AppDetail> cloneAppList;
  private ArrayList<String> data;
  ImageView dateImg;
  Boolean dateOrder = Boolean.valueOf(false);
  RelativeLayout dateOrderRL;
  TextView dateTv;
  private List<AppDetail> getSystemAppsFilter = new ArrayList();
  private List<AppDetail> installedAppChecked;
  private List<AppDetail> installedApps = new ArrayList();
  private List<AppDetail> installedAppsOrgis = new ArrayList();
  private ListView listView;
  ImageView nameImg;
  Boolean nameOrder = Boolean.valueOf(false);
  RelativeLayout nameOrderRL;
  TextView nameTv;
  private PackageManager packageManager = null;
  private ArrayList<String> pkgArr;
  private String pkgName;
  private Preference preference;
  private ProgressDialog progress = null;
  private EditText searchApp;
  ImageView sizeImg;
  Boolean sizeOrder = Boolean.valueOf(false);
  RelativeLayout sizeOrderRL;
  TextView sizeTv;
  private List<AppDetail> systemApps = new ArrayList();
  private Toolbar toolbar;
  private ArrayList<String> updatecheck = new ArrayList();
  private String value = "";
  private ArrayList<String> verArr;
  private String verName;
  
  public UpdateForDownLoadedApp() {}
  
  private void arrageOrder()
  {
    this.dateTv = ((TextView)findViewById(2131689896));
    this.nameTv = ((TextView)findViewById(2131689890));
    this.sizeTv = ((TextView)findViewById(2131689893));
    this.dateImg = ((ImageView)findViewById(2131689897));
    this.sizeImg = ((ImageView)findViewById(2131689894));
    this.nameImg = ((ImageView)findViewById(2131689891));
    this.dateOrderRL = ((RelativeLayout)findViewById(2131689895));
    this.sizeOrderRL = ((RelativeLayout)findViewById(2131689892));
    this.nameOrderRL = ((RelativeLayout)findViewById(2131689889));
    this.nameOrderRL.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UpdateForDownLoadedApp.this.aHandler.showFullAds(UpdateForDownLoadedApp.this, false);
        UpdateForDownLoadedApp.this.sizeImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837762));
        UpdateForDownLoadedApp.this.dateImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837762));
        UpdateForDownLoadedApp.this.nameTv.setTextColor(Color.parseColor("#17d7ef"));
        UpdateForDownLoadedApp.this.sizeTv.setTextColor(Color.parseColor("#ffffff"));
        UpdateForDownLoadedApp.this.dateTv.setTextColor(Color.parseColor("#ffffff"));
        if (UpdateForDownLoadedApp.this.nameOrder.booleanValue()) {}
        try
        {
          UpdateForDownLoadedApp.this.nameImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837708));
          if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Downloaded Apps"))
          {
            Collections.sort(UpdateForDownLoadedApp.this.installedApps, new Comparator()
            {
              public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
              {
                return paramAnonymous2AppDetail1.getAppName().toString().compareToIgnoreCase(paramAnonymous2AppDetail2.getAppName().toString());
              }
            });
            UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedApps);
          }
          for (;;)
          {
            UpdateForDownLoadedApp.this.nameOrder = Boolean.valueOf(false);
            return;
            if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("System Apps"))
            {
              Collections.sort(UpdateForDownLoadedApp.this.systemApps, new Comparator()
              {
                public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                {
                  return paramAnonymous2AppDetail1.getAppName().toString().compareToIgnoreCase(paramAnonymous2AppDetail2.getAppName().toString());
                }
              });
              UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.systemApps);
            }
            else if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Update Found"))
            {
              Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
              {
                public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                {
                  return paramAnonymous2AppDetail1.getAppName().toString().compareToIgnoreCase(paramAnonymous2AppDetail2.getAppName().toString());
                }
              });
              UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedAppChecked);
            }
            else if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("_key_notification"))
            {
              Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
              {
                public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                {
                  return paramAnonymous2AppDetail1.getAppName().toString().compareToIgnoreCase(paramAnonymous2AppDetail2.getAppName().toString());
                }
              });
              UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedAppChecked);
            }
          }
        }
        catch (Exception paramAnonymousView)
        {
          try
          {
            UpdateForDownLoadedApp.this.nameImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837707));
            if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Downloaded Apps"))
            {
              Collections.sort(UpdateForDownLoadedApp.this.installedApps, new Comparator()
              {
                public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                {
                  return paramAnonymous2AppDetail2.getAppName().toString().compareToIgnoreCase(paramAnonymous2AppDetail1.getAppName().toString());
                }
              });
              UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedApps);
            }
            for (;;)
            {
              UpdateForDownLoadedApp.this.nameOrder = Boolean.valueOf(true);
              return;
              if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("System Apps"))
              {
                Collections.sort(UpdateForDownLoadedApp.this.systemApps, new Comparator()
                {
                  public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                  {
                    return paramAnonymous2AppDetail2.getAppName().toString().compareToIgnoreCase(paramAnonymous2AppDetail1.getAppName().toString());
                  }
                });
                UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.systemApps);
              }
              else if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Update Found"))
              {
                Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
                {
                  public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                  {
                    return paramAnonymous2AppDetail2.getAppName().toString().compareToIgnoreCase(paramAnonymous2AppDetail1.getAppName().toString());
                  }
                });
                UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedAppChecked);
              }
              else if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("_key_notification"))
              {
                Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
                {
                  public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                  {
                    return paramAnonymous2AppDetail2.getAppName().toString().compareToIgnoreCase(paramAnonymous2AppDetail1.getAppName().toString());
                  }
                });
                UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedAppChecked);
              }
            }
            paramAnonymousView = paramAnonymousView;
          }
          catch (Exception paramAnonymousView)
          {
            for (;;) {}
          }
        }
      }
    });
    this.sizeOrderRL.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UpdateForDownLoadedApp.this.aHandler.showFullAds(UpdateForDownLoadedApp.this, false);
        UpdateForDownLoadedApp.this.nameImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837762));
        UpdateForDownLoadedApp.this.dateImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837762));
        UpdateForDownLoadedApp.this.sizeTv.setTextColor(Color.parseColor("#17d7ef"));
        UpdateForDownLoadedApp.this.nameTv.setTextColor(Color.parseColor("#ffffff"));
        UpdateForDownLoadedApp.this.dateTv.setTextColor(Color.parseColor("#ffffff"));
        if (UpdateForDownLoadedApp.this.sizeOrder.booleanValue()) {}
        try
        {
          UpdateForDownLoadedApp.this.sizeImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837708));
          if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Downloaded Apps"))
          {
            Collections.sort(UpdateForDownLoadedApp.this.installedApps, new Comparator()
            {
              public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
              {
                return Long.valueOf(paramAnonymous2AppDetail1.getApklength()).compareTo(Long.valueOf(paramAnonymous2AppDetail2.getApklength()));
              }
            });
            UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedApps);
          }
          for (;;)
          {
            UpdateForDownLoadedApp.this.sizeOrder = Boolean.valueOf(false);
            return;
            if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("System Apps"))
            {
              Collections.sort(UpdateForDownLoadedApp.this.systemApps, new Comparator()
              {
                public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                {
                  return Long.valueOf(paramAnonymous2AppDetail1.getApklength()).compareTo(Long.valueOf(paramAnonymous2AppDetail2.getApklength()));
                }
              });
              UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.systemApps);
            }
            else if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Update Found"))
            {
              Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
              {
                public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                {
                  return Long.valueOf(paramAnonymous2AppDetail1.getApklength()).compareTo(Long.valueOf(paramAnonymous2AppDetail2.getApklength()));
                }
              });
              UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedAppChecked);
            }
            else if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("_key_notification"))
            {
              Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
              {
                public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                {
                  return Long.valueOf(paramAnonymous2AppDetail1.getApklength()).compareTo(Long.valueOf(paramAnonymous2AppDetail2.getApklength()));
                }
              });
              UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedAppChecked);
            }
          }
        }
        catch (Exception paramAnonymousView)
        {
          try
          {
            UpdateForDownLoadedApp.this.sizeImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837707));
            if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Downloaded Apps"))
            {
              Collections.sort(UpdateForDownLoadedApp.this.installedApps, new Comparator()
              {
                public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                {
                  return Long.valueOf(paramAnonymous2AppDetail2.getApklength()).compareTo(Long.valueOf(paramAnonymous2AppDetail1.getApklength()));
                }
              });
              UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedApps);
            }
            for (;;)
            {
              UpdateForDownLoadedApp.this.sizeOrder = Boolean.valueOf(true);
              return;
              if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("System Apps"))
              {
                Collections.sort(UpdateForDownLoadedApp.this.systemApps, new Comparator()
                {
                  public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                  {
                    return Long.valueOf(paramAnonymous2AppDetail2.getApklength()).compareTo(Long.valueOf(paramAnonymous2AppDetail1.getApklength()));
                  }
                });
                UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.systemApps);
              }
              else if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Update Found"))
              {
                Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
                {
                  public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                  {
                    return Long.valueOf(paramAnonymous2AppDetail2.getApklength()).compareTo(Long.valueOf(paramAnonymous2AppDetail1.getApklength()));
                  }
                });
                UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedAppChecked);
              }
              else if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("_key_notification"))
              {
                Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
                {
                  public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                  {
                    return Long.valueOf(paramAnonymous2AppDetail2.getApklength()).compareTo(Long.valueOf(paramAnonymous2AppDetail1.getApklength()));
                  }
                });
                UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedAppChecked);
              }
            }
            paramAnonymousView = paramAnonymousView;
          }
          catch (Exception paramAnonymousView)
          {
            for (;;) {}
          }
        }
      }
    });
    this.dateOrderRL.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UpdateForDownLoadedApp.this.aHandler.showFullAds(UpdateForDownLoadedApp.this, false);
        UpdateForDownLoadedApp.this.nameImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837762));
        UpdateForDownLoadedApp.this.sizeImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837762));
        UpdateForDownLoadedApp.this.dateTv.setTextColor(Color.parseColor("#17d7ef"));
        UpdateForDownLoadedApp.this.nameTv.setTextColor(Color.parseColor("#ffffff"));
        UpdateForDownLoadedApp.this.sizeTv.setTextColor(Color.parseColor("#ffffff"));
        if (UpdateForDownLoadedApp.this.dateOrder.booleanValue()) {}
        try
        {
          UpdateForDownLoadedApp.this.dateImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837708));
          if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Downloaded Apps"))
          {
            Collections.sort(UpdateForDownLoadedApp.this.installedApps, new Comparator()
            {
              public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
              {
                return Long.toString(paramAnonymous2AppDetail1.actualdate).compareTo(Long.toString(paramAnonymous2AppDetail2.actualdate));
              }
            });
            UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedApps);
          }
          for (;;)
          {
            UpdateForDownLoadedApp.this.dateOrder = Boolean.valueOf(false);
            return;
            if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("System Apps"))
            {
              Collections.sort(UpdateForDownLoadedApp.this.systemApps, new Comparator()
              {
                public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                {
                  return Long.toString(paramAnonymous2AppDetail1.actualdate).compareTo(Long.toString(paramAnonymous2AppDetail2.actualdate));
                }
              });
              UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.systemApps);
            }
            else if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Update Found"))
            {
              System.out.println("inside date asc");
              Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
              {
                public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                {
                  return Long.toString(paramAnonymous2AppDetail1.actualdate).compareTo(Long.toString(paramAnonymous2AppDetail2.actualdate));
                }
              });
              UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedAppChecked);
            }
            else if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("_key_notification"))
            {
              System.out.println("inside date asc");
              Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
              {
                public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                {
                  return Long.toString(paramAnonymous2AppDetail1.actualdate).compareTo(Long.toString(paramAnonymous2AppDetail2.actualdate));
                }
              });
              UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedAppChecked);
            }
          }
        }
        catch (Exception paramAnonymousView)
        {
          try
          {
            UpdateForDownLoadedApp.this.dateImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837707));
            if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Downloaded Apps"))
            {
              Collections.sort(UpdateForDownLoadedApp.this.installedApps, new Comparator()
              {
                public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                {
                  return Long.toString(paramAnonymous2AppDetail2.actualdate).compareTo(Long.toString(paramAnonymous2AppDetail1.actualdate));
                }
              });
              UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedApps);
            }
            for (;;)
            {
              UpdateForDownLoadedApp.this.dateOrder = Boolean.valueOf(true);
              return;
              if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("System Apps"))
              {
                Collections.sort(UpdateForDownLoadedApp.this.systemApps, new Comparator()
                {
                  public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                  {
                    return Long.toString(paramAnonymous2AppDetail2.actualdate).compareTo(Long.toString(paramAnonymous2AppDetail1.actualdate));
                  }
                });
                UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.systemApps);
              }
              else if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Update Found"))
              {
                Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
                {
                  public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                  {
                    return Long.toString(paramAnonymous2AppDetail2.actualdate).compareTo(Long.toString(paramAnonymous2AppDetail1.actualdate));
                  }
                });
                UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedAppChecked);
              }
              else if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("_key_notification"))
              {
                Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
                {
                  public int compare(AppDetail paramAnonymous2AppDetail1, AppDetail paramAnonymous2AppDetail2)
                  {
                    return Long.toString(paramAnonymous2AppDetail2.actualdate).compareTo(Long.toString(paramAnonymous2AppDetail1.actualdate));
                  }
                });
                UpdateForDownLoadedApp.this.appListViewAdapter.setListMenu(UpdateForDownLoadedApp.this.installedAppChecked);
              }
            }
            paramAnonymousView = paramAnonymousView;
          }
          catch (Exception paramAnonymousView)
          {
            for (;;) {}
          }
        }
      }
    });
  }
  
  public static String getFileSize(long paramLong)
  {
    if (paramLong <= 0L) {
      return "0";
    }
    int i = (int)(Math.log10(paramLong) / Math.log10(1024.0D));
    return new DecimalFormat("#,##0.#").format(paramLong / Math.pow(1024.0D, i)) + " " + new String[] { "B", "KB", "MB", "GB", "TB" }[i];
  }
  
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
        this.appName = localPackageInfo.applicationInfo.loadLabel(this.packageManager).toString();
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
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    System.out.println("here inside update downloaded app onactivity");
    if (paramInt1 == 74) {}
    try
    {
      boolean bool = paramIntent.getExtras().getBoolean("data");
      System.out.println("here is the boolean check " + bool);
      return;
    }
    catch (Exception paramIntent) {}
  }
  
  public void onBackPressed()
  {
    if ((this.searchApp.getText().toString().length() <= 0) || (this.value.equalsIgnoreCase("Downloaded Apps"))) {}
    for (;;)
    {
      System.out.println("here is backpressed 11234");
      super.onBackPressed();
      return;
      if ((this.value.equalsIgnoreCase("System Apps")) || (!this.value.equalsIgnoreCase("Update Found"))) {}
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903226);
    this.toolbar = ((Toolbar)findViewById(2131689886));
    setSupportActionBar(this.toolbar);
    this.toolbar.setTitleTextColor(-1);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    this.preference = new Preference(this);
    this.aHandler = new AHandler();
    this.searchApp = ((EditText)findViewById(2131689887));
    this.listView = ((ListView)findViewById(2131689888));
    this.packageManager = getPackageManager();
    this.value = getIntent().getStringExtra("_data");
    System.out.println("here is the value " + this.value);
    if (this.value.equalsIgnoreCase("Downloaded Apps"))
    {
      getSupportActionBar().setTitle(getApplicationContext().getResources().getString(2131165246));
      new LoadApplications(null).execute(new Void[0]);
    }
    for (;;)
    {
      this.searchApp.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable)
        {
          if (UpdateForDownLoadedApp.this.searchApp.getText().toString().length() == 0)
          {
            if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Downloaded Apps"))
            {
              UpdateForDownLoadedApp.this.installedApps.clear();
              new UpdateForDownLoadedApp.LoadApplications(UpdateForDownLoadedApp.this, null).execute(new Void[0]);
            }
            do
            {
              do
              {
                return;
                if (UpdateForDownLoadedApp.this.value.equalsIgnoreCase("System Apps"))
                {
                  UpdateForDownLoadedApp.this.systemApps.clear();
                  new UpdateForDownLoadedApp.LoadSystemApplications(UpdateForDownLoadedApp.this, null).execute(new Void[0]);
                  return;
                }
              } while (!UpdateForDownLoadedApp.this.value.equalsIgnoreCase("Update Found"));
              UpdateForDownLoadedApp.access$702(UpdateForDownLoadedApp.this, new ArrayList());
              UpdateForDownLoadedApp.access$802(UpdateForDownLoadedApp.this, UpdateForDownLoadedApp.this.preference.getUpdateApps());
            } while ((UpdateForDownLoadedApp.this.data == null) || (UpdateForDownLoadedApp.this.data.size() <= 0));
            System.out.println("UpdateForDownLoadedApp.onCreate " + UpdateForDownLoadedApp.this.data.size());
            UpdateForDownLoadedApp.this.preference.setScanPromp(Boolean.valueOf(false));
            new UpdateForDownLoadedApp.LoadCheckedApplications(UpdateForDownLoadedApp.this, null).execute(new Void[0]);
            return;
          }
          try
          {
            UpdateForDownLoadedApp.this.appListViewAdapter.getFilter().filter(paramAnonymousEditable.toString());
            return;
          }
          catch (Exception paramAnonymousEditable) {}
        }
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      });
      getWindow().setSoftInputMode(3);
      getWindow().setSoftInputMode(32);
      arrageOrder();
      return;
      if (this.value.equalsIgnoreCase("System Apps"))
      {
        getSupportActionBar().setTitle(getApplicationContext().getResources().getString(2131165308));
        new LoadSystemApplications(null).execute(new Void[0]);
      }
      else if (this.value.equalsIgnoreCase("_key_notification"))
      {
        this.aHandler.v2CallOnBGLaunch(this);
        this.installedAppChecked = new ArrayList();
        System.out.println("here is one _key_notification");
        this.data = new ArrayList();
        this.data = ((ArrayList)getIntent().getSerializableExtra("_key_notification"));
        getSupportActionBar().setTitle(getApplicationContext().getResources().getString(2131165269) + "(" + this.data.size() + ")");
        this.toolbar.setTitleTextColor(-1);
        new LoadCheckedApplications(null).execute(new Void[0]);
      }
    }
  }
  
  protected void onDestroy()
  {
    if (this.progress != null)
    {
      System.out.println("UpdateForDownLoadedApp.onDestroy progress.dismiss");
      this.progress.dismiss();
    }
    super.onDestroy();
    System.out.println("UpdateForDownLoadedApp.onDestroy ");
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
  
  protected void onResume()
  {
    super.onResume();
    this.installedAppChecked = new ArrayList();
    if (this.value.equalsIgnoreCase("Update Found"))
    {
      this.toolbar.setVisibility(8);
      getSupportActionBar().setTitle(getApplicationContext().getResources().getString(2131165269));
      this.toolbar.setTitleTextColor(-1);
      System.out.println("here is one two Update Found");
      this.data = this.preference.getUpdateApps();
      if ((this.data == null) || (this.data.size() <= 0)) {
        break label202;
      }
      System.out.println("UpdateForDownLoadedApp.onCreate " + this.data.size());
      this.preference.setScanPromp(Boolean.valueOf(true));
      Intent localIntent = new Intent(this, SoftwareUpdateActivity.class);
      localIntent.putExtra("_data", "Update Found");
      startActivity(localIntent);
    }
    for (;;)
    {
      if (this.preference.getScanPromp().booleanValue())
      {
        finish();
        this.preference.setScanPromp(Boolean.valueOf(false));
      }
      return;
      label202:
      if (!this.preference.getScanPromp().booleanValue()) {
        startActivity(new Intent(this, ScanPromptActivity.class));
      }
    }
  }
  
  private class LoadApplications
    extends AsyncTask<Void, Void, Void>
  {
    private LoadApplications() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = UpdateForDownLoadedApp.this.packageManager.getInstalledApplications(0).iterator();
      while (paramVarArgs.hasNext())
      {
        Object localObject1 = (ApplicationInfo)paramVarArgs.next();
        if (((((ApplicationInfo)localObject1).flags & 0x80) == 0) && ((((ApplicationInfo)localObject1).flags & 0x1) == 0))
        {
          AppDetail localAppDetail = new AppDetail();
          try
          {
            localAppDetail.setImage(UpdateForDownLoadedApp.this.getPackageManager().getApplicationIcon(((ApplicationInfo)localObject1).packageName));
            localAppDetail.setAppName(UpdateForDownLoadedApp.this.getPackageManager().getApplicationLabel((ApplicationInfo)localObject1));
            PackageManager localPackageManager = UpdateForDownLoadedApp.this.getPackageManager();
            Object localObject2 = localPackageManager.getApplicationInfo(((ApplicationInfo)localObject1).packageName, 0);
            System.out.println("date is application " + ((ApplicationInfo)localObject1).packageName);
            System.out.println("date is package name " + localObject2);
            localObject2 = ((ApplicationInfo)localObject2).sourceDir;
            System.out.println("date string directory " + (String)localObject2);
            localObject2 = new SimpleDateFormat("dd/MM/yyyy");
            long l = new File(localPackageManager.getApplicationInfo(((ApplicationInfo)localObject1).packageName, 0).publicSourceDir).length();
            System.out.println("Size of APP " + l);
            localAppDetail.setApklength(l);
            localAppDetail.setAppSize(UpdateForDownLoadedApp.getFileSize(l));
            localAppDetail.setPkgName(((ApplicationInfo)localObject1).packageName);
            System.out.println("package name is here " + ((ApplicationInfo)localObject1).packageName);
            localObject1 = UpdateForDownLoadedApp.this.getPackageManager().getPackageInfo(((ApplicationInfo)localObject1).packageName, 0);
            localAppDetail.actualdate = ((PackageInfo)localObject1).firstInstallTime;
            localAppDetail.setInstallDate(((SimpleDateFormat)localObject2).format(new Date(localAppDetail.actualdate)));
            localObject1 = ((PackageInfo)localObject1).versionName;
            System.out.println("this is version name " + (String)localObject1);
            localAppDetail.setVersion((String)localObject1);
            UpdateForDownLoadedApp.this.installedApps.add(localAppDetail);
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            localNameNotFoundException.printStackTrace();
          }
        }
      }
      return null;
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      System.out.println("checking onPost 01");
      UpdateForDownLoadedApp.this.progress.dismiss();
      Collections.sort(UpdateForDownLoadedApp.this.installedApps, new Comparator()
      {
        public int compare(AppDetail paramAnonymousAppDetail1, AppDetail paramAnonymousAppDetail2)
        {
          return paramAnonymousAppDetail1.getAppName().toString().compareToIgnoreCase(paramAnonymousAppDetail2.getAppName().toString());
        }
      });
      UpdateForDownLoadedApp.access$1002(UpdateForDownLoadedApp.this, new DownloadedAppListViewAdapter(UpdateForDownLoadedApp.this, UpdateForDownLoadedApp.this.installedApps, UpdateForDownLoadedApp.this.value));
      System.out.println("checking onPost 03");
      UpdateForDownLoadedApp.this.listView.setAdapter(UpdateForDownLoadedApp.this.appListViewAdapter);
    }
    
    protected void onPreExecute()
    {
      UpdateForDownLoadedApp.access$1102(UpdateForDownLoadedApp.this, ProgressDialog.show(UpdateForDownLoadedApp.this, null, "Please Wait..."));
      super.onPreExecute();
    }
  }
  
  private class LoadCheckedApplications
    extends AsyncTask<Void, Void, Void>
  {
    private LoadCheckedApplications() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      int i = 0;
      for (;;)
      {
        if (i < UpdateForDownLoadedApp.this.data.size())
        {
          paramVarArgs = new AppDetail();
          try
          {
            Object localObject1 = UpdateForDownLoadedApp.this.packageManager;
            Object localObject2 = UpdateForDownLoadedApp.this.packageManager;
            String str = (String)UpdateForDownLoadedApp.this.data.get(i);
            paramVarArgs.setImage(((PackageManager)localObject1).getApplicationIcon(((PackageManager)localObject2).getApplicationInfo(str, 128)));
            localObject1 = UpdateForDownLoadedApp.this.packageManager;
            localObject2 = UpdateForDownLoadedApp.this.packageManager;
            str = (String)UpdateForDownLoadedApp.this.data.get(i);
            localObject1 = (String)((PackageManager)localObject1).getApplicationLabel(((PackageManager)localObject2).getApplicationInfo(str, 128));
            System.out.println("data is App Name " + (String)localObject1);
            paramVarArgs.setAppName((CharSequence)localObject1);
            localObject1 = UpdateForDownLoadedApp.this.packageManager.getApplicationInfo((String)UpdateForDownLoadedApp.this.data.get(i), 0);
            System.out.println("date is application " + (String)UpdateForDownLoadedApp.this.data.get(i));
            System.out.println("date is package name " + localObject1);
            localObject1 = ((ApplicationInfo)localObject1).sourceDir;
            System.out.println("date string directory " + (String)localObject1);
            localObject1 = new SimpleDateFormat("dd/MM/yyyy");
            long l = new File(UpdateForDownLoadedApp.this.packageManager.getApplicationInfo((String)UpdateForDownLoadedApp.this.data.get(i), 0).publicSourceDir).length();
            System.out.println("Size of APP " + l);
            paramVarArgs.setApklength(l);
            paramVarArgs.setAppSize(UpdateForDownLoadedApp.getFileSize(l));
            paramVarArgs.setPkgName((String)UpdateForDownLoadedApp.this.data.get(i));
            System.out.println("package name is here " + (String)UpdateForDownLoadedApp.this.data.get(i));
            UpdateForDownLoadedApp.this.updatecheck.add(UpdateForDownLoadedApp.this.data.get(i));
            localObject2 = UpdateForDownLoadedApp.this.getPackageManager().getPackageInfo((String)UpdateForDownLoadedApp.this.data.get(i), 0);
            paramVarArgs.actualdate = ((PackageInfo)localObject2).firstInstallTime;
            paramVarArgs.setInstallDate(((SimpleDateFormat)localObject1).format(new Date(paramVarArgs.actualdate)));
            localObject1 = ((PackageInfo)localObject2).versionName;
            System.out.println("this is version name " + (String)localObject1);
            paramVarArgs.setVersion((String)localObject1);
            UpdateForDownLoadedApp.this.installedAppChecked.add(paramVarArgs);
            i += 1;
          }
          catch (PackageManager.NameNotFoundException paramVarArgs)
          {
            for (;;)
            {
              paramVarArgs.printStackTrace();
            }
          }
        }
      }
      return null;
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      Collections.sort(UpdateForDownLoadedApp.this.installedAppChecked, new Comparator()
      {
        public int compare(AppDetail paramAnonymousAppDetail1, AppDetail paramAnonymousAppDetail2)
        {
          return paramAnonymousAppDetail1.getAppName().toString().compareToIgnoreCase(paramAnonymousAppDetail2.getAppName().toString());
        }
      });
      UpdateForDownLoadedApp.this.nameImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837708));
      UpdateForDownLoadedApp.this.sizeImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837762));
      UpdateForDownLoadedApp.this.dateImg.setBackground(ContextCompat.getDrawable(UpdateForDownLoadedApp.this, 2130837762));
      UpdateForDownLoadedApp.this.nameTv.setTextColor(Color.parseColor("#17d7ef"));
      UpdateForDownLoadedApp.this.sizeTv.setTextColor(Color.parseColor("#ffffff"));
      UpdateForDownLoadedApp.this.dateTv.setTextColor(Color.parseColor("#ffffff"));
      UpdateForDownLoadedApp.access$1002(UpdateForDownLoadedApp.this, new DownloadedAppListViewAdapter(UpdateForDownLoadedApp.this, UpdateForDownLoadedApp.this.installedAppChecked, UpdateForDownLoadedApp.this.value));
      UpdateForDownLoadedApp.this.listView.setAdapter(UpdateForDownLoadedApp.this.appListViewAdapter);
      UpdateForDownLoadedApp.this.appListViewAdapter.notifyDataSetChanged();
      UpdateForDownLoadedApp.this.progress.dismiss();
    }
    
    protected void onPreExecute()
    {
      UpdateForDownLoadedApp.access$1102(UpdateForDownLoadedApp.this, ProgressDialog.show(UpdateForDownLoadedApp.this, null, "Please Wait..."));
      super.onPreExecute();
    }
    
    protected void onProgressUpdate(Void... paramVarArgs)
    {
      super.onProgressUpdate(paramVarArgs);
    }
  }
  
  private class LoadSystemApplications
    extends AsyncTask<Void, Void, Void>
  {
    private LoadSystemApplications() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = UpdateForDownLoadedApp.this.packageManager.getInstalledApplications(0);
      int i = 0;
      paramVarArgs = paramVarArgs.iterator();
      while (paramVarArgs.hasNext())
      {
        Object localObject1 = (ApplicationInfo)paramVarArgs.next();
        if ((((ApplicationInfo)localObject1).flags & 0x80) != 0)
        {
          AppDetail localAppDetail = new AppDetail();
          try
          {
            localAppDetail.setImage(UpdateForDownLoadedApp.this.getPackageManager().getApplicationIcon(((ApplicationInfo)localObject1).packageName));
            localAppDetail.setAppName(UpdateForDownLoadedApp.this.getPackageManager().getApplicationLabel((ApplicationInfo)localObject1));
            localAppDetail.setPkgName(((ApplicationInfo)localObject1).packageName);
            System.out.println("package name is here bds " + ((ApplicationInfo)localObject1).packageName);
            PackageManager localPackageManager = UpdateForDownLoadedApp.this.getPackageManager();
            Object localObject2 = localPackageManager.getApplicationInfo(((ApplicationInfo)localObject1).packageName, 0);
            System.out.println("date is application " + localObject2);
            localObject2 = ((ApplicationInfo)localObject2).sourceDir;
            System.out.println("date string directory " + (String)localObject2);
            localObject2 = new SimpleDateFormat("dd/MM/yyyy");
            long l = new File(localPackageManager.getApplicationInfo(((ApplicationInfo)localObject1).packageName, 0).publicSourceDir).length();
            localAppDetail.setApklength(l);
            localAppDetail.setAppSize(UpdateForDownLoadedApp.getFileSize(l));
            localObject1 = UpdateForDownLoadedApp.this.getPackageManager().getPackageInfo(((ApplicationInfo)localObject1).packageName, 0);
            localAppDetail.actualdate = ((PackageInfo)localObject1).firstInstallTime;
            localAppDetail.setInstallDate(((SimpleDateFormat)localObject2).format(new Date(localAppDetail.actualdate)));
            localObject1 = ((PackageInfo)localObject1).versionName;
            UpdateForDownLoadedApp.this.systemApps.add(localAppDetail);
            UpdateForDownLoadedApp.this.getSystemAppsFilter.add(localAppDetail);
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            localNameNotFoundException.printStackTrace();
          }
        }
        else if ((((ApplicationInfo)localObject1).flags & 0x1) == 0)
        {
          i += 1;
        }
      }
      return null;
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      Collections.sort(UpdateForDownLoadedApp.this.systemApps, new Comparator()
      {
        public int compare(AppDetail paramAnonymousAppDetail1, AppDetail paramAnonymousAppDetail2)
        {
          return paramAnonymousAppDetail1.getAppName().toString().compareToIgnoreCase(paramAnonymousAppDetail2.getAppName().toString());
        }
      });
      UpdateForDownLoadedApp.access$1002(UpdateForDownLoadedApp.this, new DownloadedAppListViewAdapter(UpdateForDownLoadedApp.this, UpdateForDownLoadedApp.this.systemApps, UpdateForDownLoadedApp.this.value));
      UpdateForDownLoadedApp.this.listView.setAdapter(UpdateForDownLoadedApp.this.appListViewAdapter);
      UpdateForDownLoadedApp.this.progress.dismiss();
      super.onPostExecute(paramVoid);
    }
    
    protected void onPreExecute()
    {
      UpdateForDownLoadedApp.access$1102(UpdateForDownLoadedApp.this, ProgressDialog.show(UpdateForDownLoadedApp.this, null, "Please Wait..."));
      super.onPreExecute();
    }
    
    protected void onProgressUpdate(Void... paramVarArgs)
    {
      super.onProgressUpdate(paramVarArgs);
    }
  }
}
