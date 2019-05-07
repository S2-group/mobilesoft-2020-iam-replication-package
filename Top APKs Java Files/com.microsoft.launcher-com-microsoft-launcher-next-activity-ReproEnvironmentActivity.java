package com.microsoft.launcher.next.activity;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.microsoft.launcher.next.model.dumpdata.CallLogInfo;
import com.microsoft.launcher.next.model.dumpdata.ContactInfo;
import com.microsoft.launcher.next.model.dumpdata.InstalledAppInfo;
import com.microsoft.launcher.next.model.dumpdata.SMSLogInfo;
import com.microsoft.launcher.utils.b;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReproEnvironmentActivity
  extends Activity
{
  public static boolean a = false;
  public static String b;
  private static String c = "%s %s on %s_Android%s";
  private static String[] d = { "ganglin@microsoft.com", "jiaxh@microsoft.com", "fexu@microsoft.com" };
  private static String e = "Choose an Email Client";
  private Button f;
  private Button g;
  private Handler h = new Handler();
  private String i = "DumpData.txt";
  private EditText j;
  private EditText k;
  private EditText l;
  private Button m;
  
  public ReproEnvironmentActivity() {}
  
  private List<ResolveInfo> a(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setPackage(paramString);
    return paramContext.getPackageManager().queryIntentActivities(localIntent, 0);
  }
  
  private ArrayList<ContactInfo> d()
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      try
      {
        localContentResolver = getContentResolver();
        localCursor = localContentResolver.query(ContactsContract.Contacts.CONTENT_URI, new String[] { "display_name", "_id" }, null, null, null);
        boolean bool = localCursor.moveToNext();
        if (!bool) {}
      }
      catch (Exception localException1)
      {
        ContentResolver localContentResolver;
        Cursor localCursor;
        ContactInfo localContactInfo;
        StringBuilder localStringBuilder;
        Object localObject1;
        Object localObject2;
        String str;
        localException1.printStackTrace();
        return localArrayList;
      }
      try
      {
        localContactInfo = new ContactInfo();
        localStringBuilder = new StringBuilder();
        localObject1 = localCursor.getString(localCursor.getColumnIndex("display_name"));
        localStringBuilder.append("name=" + (String)localObject1 + ";");
        localContactInfo.name = ((String)localObject1);
        localObject1 = localCursor.getString(localCursor.getColumnIndex("_id"));
        localContactInfo.id = ((String)localObject1);
        localObject2 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        str = "contact_id = " + (String)localObject1;
        localObject2 = localContentResolver.query((Uri)localObject2, new String[] { "data1" }, str, null, null);
        if (((Cursor)localObject2).moveToNext())
        {
          str = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("data1"));
          localStringBuilder.append("Phone=" + str + ";");
          localContactInfo.numberList.add(str);
          continue;
        }
        ((Cursor)localObject2).close();
        localObject2 = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        localObject1 = "contact_id = " + (String)localObject1;
        localObject1 = localContentResolver.query((Uri)localObject2, new String[] { "data1" }, (String)localObject1, null, null);
        if (((Cursor)localObject1).moveToNext())
        {
          localObject2 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("data1"));
          localStringBuilder.append("Email=" + (String)localObject2 + ";");
          localContactInfo.emailList.add(localObject2);
          continue;
        }
        ((Cursor)localObject1).close();
        localArrayList.add(localContactInfo);
      }
      catch (Exception localException2) {}
      localCursor.close();
      return localArrayList;
    }
  }
  
  private ArrayList<CallLogInfo> e()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = getContentResolver();
    Object localObject2 = Calendar.getInstance();
    ((Calendar)localObject2).add(5, -30);
    long l1 = ((Calendar)localObject2).getTimeInMillis();
    for (;;)
    {
      try
      {
        localObject2 = CallLog.Calls.CONTENT_URI;
        str1 = "date>" + l1;
        localObject1 = ((ContentResolver)localObject1).query((Uri)localObject2, new String[] { "number", "duration", "date", "type" }, str1, null, "date DESC");
        n = ((Cursor)localObject1).getColumnIndex("number");
        i1 = ((Cursor)localObject1).getColumnIndex("type");
        i2 = ((Cursor)localObject1).getColumnIndex("date");
        i3 = ((Cursor)localObject1).getColumnIndex("duration");
        boolean bool = ((Cursor)localObject1).moveToNext();
        if (!bool) {}
      }
      catch (Exception localException1)
      {
        String str1;
        int n;
        int i1;
        int i2;
        int i3;
        String str2;
        String str3;
        String str4;
        localException1.printStackTrace();
        return localArrayList;
      }
      try
      {
        localObject2 = new CallLogInfo();
        str1 = ((Cursor)localObject1).getString(n);
        str2 = ((Cursor)localObject1).getString(i1);
        str3 = ((Cursor)localObject1).getString(i2);
        new Date(Long.valueOf(str3).longValue());
        str4 = ((Cursor)localObject1).getString(i3);
        ((CallLogInfo)localObject2).number = str1;
        ((CallLogInfo)localObject2).type = str2;
        ((CallLogInfo)localObject2).date = str3;
        ((CallLogInfo)localObject2).duration = str4;
        localArrayList.add(localObject2);
        switch (Integer.parseInt(str2))
        {
        }
      }
      catch (Exception localException2) {}
      ((Cursor)localObject1).close();
      return localArrayList;
      continue;
      continue;
    }
  }
  
  private ArrayList<SMSLogInfo> f()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = getContentResolver();
    Object localObject2 = Calendar.getInstance();
    ((Calendar)localObject2).add(5, -30);
    long l1 = ((Calendar)localObject2).getTimeInMillis();
    try
    {
      localObject2 = Uri.parse("content://sms/");
      String str1 = "date>" + l1;
      localObject1 = ((ContentResolver)localObject1).query((Uri)localObject2, new String[] { "address", "date", "body" }, str1, null, "date DESC");
      int n = ((Cursor)localObject1).getColumnIndex("address");
      int i1 = ((Cursor)localObject1).getColumnIndex("date");
      int i2 = ((Cursor)localObject1).getColumnIndex("body");
      while (((Cursor)localObject1).moveToNext())
      {
        localObject2 = new SMSLogInfo();
        str1 = ((Cursor)localObject1).getString(n);
        l1 = ((Cursor)localObject1).getLong(i1);
        String str2 = ((Cursor)localObject1).getString(i2);
        ((SMSLogInfo)localObject2).number = str1;
        ((SMSLogInfo)localObject2).date = l1;
        ((SMSLogInfo)localObject2).length = str2.length();
        localArrayList.add(localObject2);
      }
      ((Cursor)localObject1).close();
      return localArrayList;
    }
    catch (Exception localException) {}
    return localArrayList;
  }
  
  private ArrayList<InstalledAppInfo> g()
  {
    ArrayList localArrayList1 = (ArrayList)getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    int n = 0;
    for (;;)
    {
      if (n < localArrayList1.size()) {
        try
        {
          PackageInfo localPackageInfo = (PackageInfo)localArrayList1.get(n);
          InstalledAppInfo localInstalledAppInfo = new InstalledAppInfo();
          localInstalledAppInfo.appName = localPackageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
          localInstalledAppInfo.packageName = localPackageInfo.packageName;
          localInstalledAppInfo.versionName = localPackageInfo.versionName;
          localInstalledAppInfo.versionCode = localPackageInfo.versionCode;
          if (a(this, localInstalledAppInfo.packageName).size() > 0) {
            localArrayList2.add(localInstalledAppInfo);
          } else {
            localArrayList3.add(localInstalledAppInfo);
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
      localArrayList2.addAll(localArrayList3);
      return localArrayList2;
      n += 1;
    }
  }
  
  int a(EditText paramEditText, int paramInt)
  {
    try
    {
      int n = Integer.parseInt(paramEditText.getText().toString());
      if ((n < 0) || (n > 1000))
      {
        paramEditText.setText("not in valid range, set to 1000.");
        return paramInt;
      }
      return n;
    }
    catch (Exception localException)
    {
      paramEditText.setText("not in valid range, set to 1000.");
    }
    return paramInt;
  }
  
  public void a(String paramString1, String paramString2, Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    int n = localArrayList.size();
    localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
    localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", n).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data1", paramString1).build());
    localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", n).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", paramString2).withValue("data2", Integer.valueOf(2)).build());
    try
    {
      paramContext.getContentResolver().applyBatch("com.android.contacts", localArrayList);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    overridePendingTransition(0, 2131034122);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968617);
    paramBundle = (RelativeLayout)findViewById(2131755198);
    ((TextView)findViewById(2131755200)).setText(2131230860);
    paramBundle.setOnClickListener(new af(this));
    this.f = ((Button)findViewById(2131755265));
    Object localObject = this.f;
    if (a) {}
    for (paramBundle = "stop CPU profiling";; paramBundle = "start CPU profiling")
    {
      ((Button)localObject).setText(paramBundle);
      this.f.setOnClickListener(new ag(this));
      paramBundle = (CheckBox)findViewById(2131755266);
      localObject = (CheckBox)findViewById(2131755267);
      ((CheckBox)findViewById(2131755268)).setVisibility(8);
      CheckBox localCheckBox1 = (CheckBox)findViewById(2131755269);
      CheckBox localCheckBox2 = (CheckBox)findViewById(2131755270);
      paramBundle.setChecked(b.c("dump_data_checkbox_applist", true));
      ((CheckBox)localObject).setChecked(b.c("dump_data_checkbox_contacts", true));
      localCheckBox1.setChecked(b.c("dump_data_checkbox_calllog", true));
      localCheckBox2.setChecked(b.c("dump_data_checkbox_smslog", true));
      ah localAh = new ah(this, paramBundle, (CheckBox)localObject, localCheckBox1, localCheckBox2);
      paramBundle.setOnClickListener(localAh);
      ((CheckBox)localObject).setOnClickListener(localAh);
      localCheckBox1.setOnClickListener(localAh);
      localCheckBox2.setOnClickListener(localAh);
      this.g = ((Button)findViewById(2131755271));
      this.g.setOnClickListener(new ai(this, paramBundle, (CheckBox)localObject, localCheckBox1, localCheckBox2));
      this.j = ((EditText)findViewById(2131755272));
      this.k = ((EditText)findViewById(2131755273));
      this.l = ((EditText)findViewById(2131755274));
      this.m = ((Button)findViewById(2131755275));
      this.m.setOnClickListener(new al(this));
      return;
    }
  }
}
