package com.hideitpro.util.monitor;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.d;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.Iterator;
import java.util.List;

public class ActivityExplainFilesDeleted
  extends d
{
  public ActivityExplainFilesDeleted() {}
  
  String getDebugString()
  {
    StringBuilder localStringBuilder = new StringBuilder("\n\n\n\nKeep the data below intact");
    localStringBuilder.append("Phone details:\n");
    localStringBuilder.append("Manufacturer - ").append(Build.MANUFACTURER).append("\n");
    localStringBuilder.append("Brand - ").append(Build.BRAND).append("\n");
    localStringBuilder.append("Device - ").append(Build.DEVICE).append("\n");
    localStringBuilder.append("Model - ").append(Build.MODEL).append("\n");
    localStringBuilder.append("Android - ").append(Build.VERSION.CODENAME).append(" (").append(Build.VERSION.SDK_INT).append(")\n");
    localStringBuilder.append("Board - ").append(Build.BOARD).append("\n");
    localStringBuilder.append("\nInstalled apps:\n");
    Object localObject = getPackageManager().getInstalledPackages(128);
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        localStringBuilder.append(((PackageInfo)((Iterator)localObject).next()).packageName);
        localStringBuilder.append("\n");
      }
    }
    return localStringBuilder.toString();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427369);
    findViewById(2131296360).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent("android.intent.action.SEND");
        paramAnonymousView.setType("plain/text");
        paramAnonymousView.putExtra("android.intent.extra.EMAIL", new String[] { "premium@hideitpro.com" });
        paramAnonymousView.putExtra("android.intent.extra.SUBJECT", "Hide it Pro [Files Missing]");
        paramAnonymousView.putExtra("android.intent.extra.TEXT", ActivityExplainFilesDeleted.this.getDebugString());
        paramAnonymousView.setFlags(268435456);
        ActivityExplainFilesDeleted.this.startActivity(Intent.createChooser(paramAnonymousView, ActivityExplainFilesDeleted.this.getString(2131689608)));
      }
    });
  }
}
