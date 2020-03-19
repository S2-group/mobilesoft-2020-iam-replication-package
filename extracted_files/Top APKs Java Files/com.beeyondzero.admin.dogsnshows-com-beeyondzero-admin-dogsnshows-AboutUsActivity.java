package com.beeyondzero.admin.dogsnshows;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Iterator;
import java.util.List;

public class AboutUsActivity
  extends AppCompatActivity
{
  public static Toolbar toolbar;
  private ImageView mImageLocation;
  private TextView mTextAddress;
  private TextView mTextCall;
  private TextView mTextEmail;
  private TextView mTextWeb;
  
  public AboutUsActivity() {}
  
  public boolean isPackageExisted(String paramString)
  {
    boolean bool2 = false;
    Iterator localIterator = getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!localIterator.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)localIterator.next()).packageName.equals(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968603);
    new FontChangeCrawler(getAssets(), "CaviarDreams.ttf").replaceFonts((ViewGroup)findViewById(16908290));
    this.mImageLocation = ((ImageView)findViewById(2131689598));
    this.mTextAddress = ((TextView)findViewById(2131689599));
    this.mTextEmail = ((TextView)findViewById(2131689600));
    this.mTextCall = ((TextView)findViewById(2131689601));
    this.mTextWeb = ((TextView)findViewById(2131689602));
    toolbar = (Toolbar)findViewById(2131689595);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    this.mImageLocation.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AboutUsActivity.this.openMap();
      }
    });
    this.mTextAddress.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AboutUsActivity.this.openMap();
      }
    });
    this.mTextEmail.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "info@beeyondzero.com", null));
        AboutUsActivity.this.startActivity(Intent.createChooser(paramAnonymousView, "Send email..."));
      }
    });
    this.mTextCall.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent("android.intent.action.DIAL", Uri.fromParts("tel", "+919791301264", null));
        AboutUsActivity.this.startActivity(paramAnonymousView);
      }
    });
    this.mTextWeb.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse("http://www.beeyondzero.com/"));
        AboutUsActivity.this.startActivity(paramAnonymousView);
      }
    });
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332)
    {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void openMap()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://maps.google.com/maps?q=loc:11.0481706,76.932307"));
    localIntent.addFlags(268435456);
    if (isPackageExisted("com.google.android.apps.maps"))
    {
      localIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
      localIntent.setPackage("com.google.android.apps.maps");
    }
    startActivity(localIntent);
  }
}
