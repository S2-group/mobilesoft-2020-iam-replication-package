package com.smsBlocker.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrustedList
  extends Activity
{
  List a;
  List b;
  String[][] c;
  private ListView d;
  private TextView e;
  private ArrayList f = new ArrayList();
  private ArrayList g = new ArrayList();
  private int h = 0;
  
  public TrustedList() {}
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.fileList();
    int j = paramContext.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (paramContext[i].equals(paramString)) {
        return true;
      }
      i += 1;
    }
  }
  
  private static String b(Context paramContext, String paramString)
  {
    paramString = new File(paramContext.getFilesDir().getAbsolutePath(), paramString);
    paramContext = new StringBuilder();
    for (;;)
    {
      try
      {
        paramString = new BufferedReader(new FileReader(paramString));
        str = paramString.readLine();
        if (str != null) {
          continue;
        }
      }
      catch (IOException paramString)
      {
        String str;
        continue;
      }
      return paramContext.toString();
      paramContext.append(str);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903049);
    this.d = ((ListView)findViewById(2131361801));
    this.e = ((TextView)findViewById(2131361802));
    this.e.setText("Allow list contains list of SMS senders whose all SMS are to be allowed.\nNote that all phonebook contacts are by default included in Allow list. Add a sender in this list from 'Options' key.");
    this.d.setEmptyView(findViewById(2131361802));
    this.d.setCacheColorHint(0);
    this.a = new ArrayList();
    this.b = new ArrayList();
    paramBundle = b(this, "trustedlist.txt");
    if (paramBundle.equals("")) {
      return;
    }
    paramBundle = paramBundle.split(";");
    this.c = ((String[][])Array.newInstance(String.class, new int[] { paramBundle.length, 3 }));
    int i = 0;
    for (;;)
    {
      if (i >= paramBundle.length)
      {
        Arrays.sort(this.c, new du(this));
        this.d.setAdapter(new dm(this, this, this.a, this.b));
        return;
      }
      String[] arrayOfString = paramBundle[i].split(",");
      this.a.add(arrayOfString[0]);
      this.b.add(arrayOfString[1]);
      this.c[i][0] = arrayOfString[0];
      this.c[i][1] = arrayOfString[1];
      this.c[i][2] = i;
      i += 1;
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131296256, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    case 2131361845: 
      b(this, "trialflag.txt");
      paramMenuItem = getPackageManager().getInstalledApplications(128);
      int i = 0;
      if (i >= paramMenuItem.size())
      {
        i = 0;
        label66:
        if ((i == 0) && (this.a.size() >= 50)) {
          break label172;
        }
        paramMenuItem = new AlertDialog.Builder(this);
        paramMenuItem.setTitle("Add from");
        do localDo = new do(this);
        paramMenuItem.setItems(new CharSequence[] { "Inbox", "Manual entry" }, localDo);
        paramMenuItem.create().show();
      }
      for (;;)
      {
        return true;
        if (((ApplicationInfo)paramMenuItem.get(i)).packageName.equals("com.smsBlockerUnlocker"))
        {
          i = 1;
          break label66;
        }
        i += 1;
        break;
        label172:
        new AlertDialog.Builder(this).setTitle("Alert").setMessage("Perchase premium version to add unlimited entirs. You can add only 50 entries in trial version.").setPositiveButton(2131230723, new ds(this)).create().show();
      }
    }
    new AlertDialog.Builder(this).setTitle("Help").setMessage("Allow list contains list of SMS senders whose all SMS are to be allowed.\nNote that all phonebook contacts are by default included in Allow list.").setPositiveButton(2131230723, new dr(this)).create().show();
    return true;
  }
  
  protected void onResume()
  {
    super.onResume();
    this.a.clear();
    this.b.clear();
    Object localObject = b(this, "trustedlist.txt");
    if (((String)localObject).equals("")) {
      return;
    }
    localObject = ((String)localObject).split(";");
    this.c = null;
    this.c = ((String[][])Array.newInstance(String.class, new int[] { localObject.length, 3 }));
    int i = 0;
    for (;;)
    {
      if (i >= localObject.length)
      {
        Arrays.sort(this.c, new dq(this));
        this.d.setAdapter(new dm(this, this, this.a, this.b));
        return;
      }
      String[] arrayOfString = localObject[i].split(",");
      this.a.add(arrayOfString[0]);
      this.b.add(arrayOfString[1]);
      this.c[i][0] = arrayOfString[0];
      this.c[i][1] = arrayOfString[1];
      this.c[i][2] = i;
      i += 1;
    }
  }
}
