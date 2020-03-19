package send.share.app.apk.com.apkshare;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity
  extends Activity
{
  public static String SelectedPaskageName = "";
  static MainActivity child;
  String AD_UNIT_ID = "ca-app-pub-3711656466702812/3202655684";
  ListView apkList;
  CheckBox chc;
  String filtertext = "";
  protected View lastview;
  PackageManager packageManager;
  protected int selectedposition = 0;
  private Timer timer;
  
  public MainActivity() {}
  
  private void Filter()
  {
    PackageInfo localPackageInfo;
    label145:
    do
    {
      try
      {
        this.packageManager = getPackageManager();
        localObject = this.packageManager.getInstalledPackages(4096);
        this.apkList = ((ListView)findViewById(2131492974));
        ArrayList localArrayList = new ArrayList();
        if ((!this.filtertext.equals("")) || (this.chc.isChecked())) {
          break label145;
        }
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          localPackageInfo = (PackageInfo)((Iterator)localObject).next();
          if (localPackageInfo.applicationInfo.sourceDir.startsWith("/data/app/")) {
            localArrayList.add(localPackageInfo);
          }
        }
        this.apkList.setAdapter(new ApkAdapter(this, localException, this.packageManager));
      }
      catch (Exception localException)
      {
        ShowMessage(localException.getMessage());
        return;
      }
      return;
      if ((!this.filtertext.equals("")) && (!this.chc.isChecked()))
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          localPackageInfo = (PackageInfo)((Iterator)localObject).next();
          if ((this.packageManager.getApplicationLabel(localPackageInfo.applicationInfo).toString().toLowerCase().contains(this.filtertext.toLowerCase())) && (localPackageInfo.applicationInfo.sourceDir.startsWith("/data/app/"))) {
            localException.add(localPackageInfo);
          }
        }
        this.apkList.setAdapter(new ApkAdapter(this, localException, this.packageManager));
        return;
      }
      if ((this.filtertext.equals("")) && (this.chc.isChecked()))
      {
        this.apkList.setAdapter(new ApkAdapter(this, (List)localObject, this.packageManager));
        return;
      }
    } while ((this.filtertext.equals("")) || (!this.chc.isChecked()));
    Object localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (this.packageManager.getApplicationLabel(localPackageInfo.applicationInfo).toString().toLowerCase().contains(this.filtertext.toLowerCase())) {
        localException.add(localPackageInfo);
      }
    }
    this.apkList.setAdapter(new ApkAdapter(this, localException, this.packageManager));
  }
  
  private void ShowMessage(String paramString)
  {
    Toast.makeText(getApplicationContext(), paramString, 1).show();
  }
  
  private void refresh()
  {
    try
    {
      this.packageManager = getPackageManager();
      List localList = this.packageManager.getInstalledPackages(4096);
      this.apkList = ((ListView)findViewById(2131492974));
      this.apkList.setAdapter(new ApkAdapter(this, localList, this.packageManager));
      return;
    }
    catch (Exception localException)
    {
      ShowMessage(localException.getMessage());
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903067);
    try
    {
      paramBundle = new AdView(this);
      paramBundle.setAdSize(AdSize.SMART_BANNER);
      paramBundle.setAdUnitId(this.AD_UNIT_ID);
      ((RelativeLayout)findViewById(2131492984)).addView(paramBundle);
      paramBundle.loadAd(new AdRequest.Builder().build());
      child = this;
      this.chc = ((CheckBox)findViewById(2131492970));
      this.chc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          MainActivity.this.Filter();
        }
      });
      Filter();
      this.apkList.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (MainActivity.this.lastview != null) {
            MainActivity.this.lastview.setBackgroundColor(-16777216);
          }
          MainActivity.this.selectedposition = paramAnonymousInt;
          MainActivity.this.lastview = paramAnonymousView;
          paramAnonymousView.setBackgroundColor(-16776961);
          paramAnonymousAdapterView = (PackageInfo)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt);
          MainActivity.SelectedPaskageName = paramAnonymousAdapterView.packageName;
          paramAnonymousAdapterView = paramAnonymousAdapterView.applicationInfo;
          try
          {
            paramAnonymousLong = new FileInputStream(paramAnonymousAdapterView.sourceDir).getChannel().size();
            ((TextView)MainActivity.this.findViewById(2131492969)).setText(MainActivity.this.getResources().getString(2131034165) + ":" + paramAnonymousLong / 1000L + "KB");
            return;
          }
          catch (FileNotFoundException paramAnonymousAdapterView)
          {
            paramAnonymousAdapterView.printStackTrace();
            return;
          }
          catch (IOException paramAnonymousAdapterView)
          {
            paramAnonymousAdapterView.printStackTrace();
          }
        }
      });
      ((ImageButton)findViewById(2131492983)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = (PackageInfo)MainActivity.this.apkList.getItemAtPosition(MainActivity.this.selectedposition);
          paramAnonymousView = new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramAnonymousView.packageName));
          MainActivity.this.startActivity(paramAnonymousView);
        }
      });
      ((EditText)findViewById(2131492972)).addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable)
        {
          MainActivity.access$102(MainActivity.this, new Timer());
          MainActivity.this.timer.schedule(new TimerTask()
          {
            public void run()
            {
              MainActivity.this.runOnUiThread(new Runnable()
              {
                public void run()
                {
                  MainActivity.this.Filter();
                }
              });
            }
          }, 500L);
        }
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          MainActivity.this.filtertext = paramAnonymousCharSequence.toString();
          if (MainActivity.this.timer != null) {
            MainActivity.this.timer.cancel();
          }
        }
      });
      ((Button)findViewById(2131492982)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            paramAnonymousView = new File(((PackageInfo)MainActivity.this.apkList.getItemAtPosition(MainActivity.this.selectedposition)).applicationInfo.sourceDir);
            MainActivity.this.sendFile(paramAnonymousView);
            return;
          }
          catch (Exception paramAnonymousView)
          {
            MainActivity.this.ShowMessage(paramAnonymousView.getMessage());
          }
        }
      });
      ((ImageButton)findViewById(2131492981)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            MainActivity.this.Filter();
            return;
          }
          catch (Exception paramAnonymousView)
          {
            MainActivity.this.ShowMessage(paramAnonymousView.getMessage());
          }
        }
      });
      return;
    }
    catch (Exception paramBundle)
    {
      for (;;)
      {
        ShowMessage(paramBundle.getMessage());
      }
    }
  }
  
  public void sendFile(File paramFile)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(paramFile));
    localIntent.setType("application/*");
    startActivity(Intent.createChooser(localIntent, ""));
  }
}
