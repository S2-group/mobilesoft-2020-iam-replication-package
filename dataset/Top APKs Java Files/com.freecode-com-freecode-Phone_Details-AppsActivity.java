package com.freecode.Phone_Details;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.pddstudio.highlightjs.HighlightJsView;
import com.pddstudio.highlightjs.models.Language;
import com.pddstudio.highlightjs.models.Theme;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppsActivity
  extends AppCompatActivity
{
  Boolean restoredValue;
  
  public AppsActivity() {}
  
  private String readTextFromResource(int paramInt)
  {
    InputStream localInputStream = getResources().openRawResource(paramInt);
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      for (paramInt = localInputStream.read(); paramInt != -1; paramInt = localInputStream.read()) {
        localByteArrayOutputStream.write(paramInt);
      }
      localInputStream.close();
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
    return localByteArrayOutputStream.toString();
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361821);
    this.restoredValue = Boolean.valueOf(getSharedPreferences("CODE_CLICKED", 0).getBoolean("boolean", false));
    if (this.restoredValue.booleanValue())
    {
      setContentView(2131361908);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      getSupportActionBar().setTitle("Apps List");
      paramBundle = (HighlightJsView)findViewById(2131231025);
      paramBundle.setZoomSupportEnabled(true);
      paramBundle.setTheme(Theme.ANDROID_STUDIO);
      paramBundle.setHighlightLanguage(Language.JAVA);
      try
      {
        paramBundle.setSource(new URL("http://18.222.123.163/freecode/applist.html"));
      }
      catch (Exception localException)
      {
        try
        {
          for (;;)
          {
            paramBundle.reload();
            ((AdView)findViewById(2131230752)).loadAd(new AdRequest.Builder().addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").build());
            return;
            localException = localException;
            localException.printStackTrace();
          }
        }
        catch (Exception paramBundle)
        {
          for (;;)
          {
            paramBundle.printStackTrace();
          }
        }
      }
    }
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setTitle("Apps List");
    paramBundle = new ArrayList();
    Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      paramBundle.add(((ApplicationInfo)localIterator.next()).packageName);
    }
    ((ListView)findViewById(2131231104)).setAdapter(new ArrayAdapter(this, 17367043, paramBundle));
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332) {
      finish();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
}
