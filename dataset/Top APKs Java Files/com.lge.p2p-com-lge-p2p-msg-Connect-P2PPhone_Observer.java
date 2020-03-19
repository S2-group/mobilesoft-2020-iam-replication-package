package com.lge.p2p.msg.Connect;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.MmsSms;
import android.text.TextUtils;
import android.util.Log;
import com.lge.qpair.api.r1.IPeerContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class P2PPhone_Observer
  extends Service
{
  private static final char[] i = { 45, 46, 44, 40, 41, 32, 47, 92, 42, 35, 43 };
  private static HashMap j = new HashMap(i.length);
  Context a;
  private int b = 0;
  private int c = 0;
  private int d = -1;
  private int e = 19;
  private IPeerContext f;
  private boolean g = false;
  private ArrayList h = null;
  private ServiceConnection k = new c(this);
  private final ContentObserver l = new d(this, new Handler());
  
  public P2PPhone_Observer() {}
  
  private String a(int paramInt)
  {
    new StringBuilder().append("msg_id = ").append(paramInt).toString();
    Cursor localCursor = SqliteWrapper.query(getApplicationContext(), getApplicationContext().getContentResolver(), Uri.parse("content://mms/" + paramInt + "/addr"), null, null, null, null);
    Object localObject1;
    if ((localCursor == null) || (localCursor.getCount() < 1))
    {
      if ((localCursor != null) && (!localCursor.isClosed())) {
        localCursor.close();
      }
      localObject1 = com.lge.p2p.msg.a.a.d(this.a);
      if (com.lge.p2p.msg.a.a.h()) {
        Log.d("P2PPhone_Observer", "[search_address]0 number is =" + (String)localObject1);
      }
    }
    while (com.lge.p2p.msg.a.a.h())
    {
      Log.d("P2PPhone_Observer", "[search_address]5 number is =" + (String)localObject1);
      return localObject1;
      Log.d("P2PPhone_Observer", "[search_address]0 number is =...");
      continue;
      paramInt = 0;
    }
    label304:
    label376:
    label446:
    label528:
    Object localObject4;
    for (Object localObject5 = null;; localObject5 = localObject4)
    {
      Object localObject2 = localObject5;
      localObject1 = localObject5;
      try
      {
        if (paramInt < localCursor.getCount())
        {
          if (paramInt != 0) {
            break label528;
          }
          localObject1 = localObject5;
          if (!localCursor.moveToFirst()) {
            break label528;
          }
          localObject1 = localObject5;
          localObject5 = localCursor.getString(localCursor.getColumnIndex("address"));
          localObject1 = localObject5;
          localObject2 = localObject5;
          if (!a((String)localObject5)) {
            break label687;
          }
          localObject1 = localObject5;
          if (!com.lge.p2p.msg.a.a.h()) {
            break label446;
          }
          localObject1 = localObject5;
          Log.d("P2PPhone_Observer", "[search_address]1 number is =" + (String)localObject5);
        }
        for (localObject2 = localObject5;; localObject2 = localObject5)
        {
          localObject1 = localObject2;
          if (!TextUtils.isEmpty(localObject2))
          {
            localObject5 = localObject2;
            localObject1 = localObject2;
            if (!"Unknown".equalsIgnoreCase(localObject2)) {}
          }
          else
          {
            localObject1 = localObject2;
            localObject5 = com.lge.p2p.msg.a.a.d(this.a);
            localObject1 = localObject5;
            if (!com.lge.p2p.msg.a.a.h()) {
              break label641;
            }
            localObject1 = localObject5;
            Log.d("P2PPhone_Observer", "[search_address]3 number is =" + (String)localObject5);
          }
          localCursor.close();
          if (!TextUtils.isEmpty((CharSequence)localObject5))
          {
            localObject1 = localObject5;
            if (!"Unknown".equalsIgnoreCase((String)localObject5)) {
              break;
            }
          }
          localObject1 = com.lge.p2p.msg.a.a.d(this.a);
          if (!com.lge.p2p.msg.a.a.h()) {
            break label655;
          }
          Log.d("P2PPhone_Observer", "[search_address]4 number is =" + (String)localObject1);
          break;
          localObject1 = localObject5;
          Log.d("P2PPhone_Observer", "[search_address]1 number is = ...");
        }
        throw localObject3;
      }
      finally
      {
        localCursor.close();
        if ((TextUtils.isEmpty((CharSequence)localObject1)) || ("Unknown".equalsIgnoreCase((String)localObject1)))
        {
          localObject1 = com.lge.p2p.msg.a.a.d(this.a);
          if (!com.lge.p2p.msg.a.a.h()) {
            break label666;
          }
          Log.d("P2PPhone_Observer", "[search_address]4 number is =" + (String)localObject1);
        }
      }
      for (;;)
      {
        localObject1 = localObject5;
        localObject4 = localObject5;
        if (!localCursor.moveToNext()) {
          break label687;
        }
        localObject1 = localObject5;
        localObject5 = localCursor.getString(localCursor.getColumnIndex("address"));
        localObject1 = localObject5;
        localObject4 = localObject5;
        if (!a((String)localObject5)) {
          break label687;
        }
        localObject1 = localObject5;
        if (com.lge.p2p.msg.a.a.h())
        {
          localObject1 = localObject5;
          Log.d("P2PPhone_Observer", "[search_address]2 number is =" + (String)localObject5);
          localObject4 = localObject5;
          break label304;
        }
        localObject1 = localObject5;
        Log.d("P2PPhone_Observer", "[search_address]2 number is =...");
        localObject4 = localObject5;
        break label304;
        label641:
        localObject1 = localObject5;
        Log.d("P2PPhone_Observer", "[search_address]3 number is =...");
        break label376;
        label655:
        Log.d("P2PPhone_Observer", "[search_address]4 number is =...");
        break;
        label666:
        Log.d("P2PPhone_Observer", "[search_address]4 number is =...");
      }
      Log.d("P2PPhone_Observer", "[search_address]5 number is =...");
      return localObject1;
      label687:
      paramInt += 1;
    }
  }
  
  /* Error */
  private void a()
  {
    // Byte code:
    //   0: ldc -106
    //   2: ldc -44
    //   4: invokestatic 157	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   7: pop
    //   8: aload_0
    //   9: invokevirtual 104	com/lge/p2p/msg/Connect/P2PPhone_Observer:getApplicationContext	()Landroid/content/Context;
    //   12: aload_0
    //   13: invokevirtual 104	com/lge/p2p/msg/Connect/P2PPhone_Observer:getApplicationContext	()Landroid/content/Context;
    //   16: invokevirtual 110	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   19: getstatic 218	android/provider/Telephony$Mms$Inbox:CONTENT_URI	Landroid/net/Uri;
    //   22: aconst_null
    //   23: ldc -36
    //   25: aconst_null
    //   26: ldc -34
    //   28: invokestatic 126	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   31: astore_1
    //   32: ldc -106
    //   34: ldc -32
    //   36: invokestatic 157	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   39: pop
    //   40: ldc -106
    //   42: new 86	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 87	java/lang/StringBuilder:<init>	()V
    //   49: ldc -30
    //   51: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: aload_1
    //   55: invokeinterface 132 1 0
    //   60: invokevirtual 96	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   63: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: invokestatic 157	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   69: pop
    //   70: aload_1
    //   71: ifnull +13 -> 84
    //   74: aload_1
    //   75: invokeinterface 132 1 0
    //   80: iconst_1
    //   81: if_icmpge +33 -> 114
    //   84: aload_1
    //   85: ifnull +18 -> 103
    //   88: aload_1
    //   89: invokeinterface 136 1 0
    //   94: ifne +9 -> 103
    //   97: aload_1
    //   98: invokeinterface 139 1 0
    //   103: aload_0
    //   104: iconst_0
    //   105: putfield 55	com/lge/p2p/msg/Connect/P2PPhone_Observer:c	I
    //   108: aload_0
    //   109: iconst_0
    //   110: putfield 53	com/lge/p2p/msg/Connect/P2PPhone_Observer:b	I
    //   113: return
    //   114: ldc -106
    //   116: ldc -28
    //   118: invokestatic 157	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   121: pop
    //   122: aload_0
    //   123: invokespecial 230	com/lge/p2p/msg/Connect/P2PPhone_Observer:b	()Z
    //   126: ifeq +36 -> 162
    //   129: aload_0
    //   130: aload_1
    //   131: invokespecial 233	com/lge/p2p/msg/Connect/P2PPhone_Observer:b	(Landroid/database/Cursor;)V
    //   134: aload_1
    //   135: ifnull +18 -> 153
    //   138: aload_1
    //   139: invokeinterface 136 1 0
    //   144: ifne +9 -> 153
    //   147: aload_1
    //   148: invokeinterface 139 1 0
    //   153: ldc -21
    //   155: ldc -19
    //   157: invokestatic 157	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   160: pop
    //   161: return
    //   162: aload_0
    //   163: aload_1
    //   164: invokespecial 239	com/lge/p2p/msg/Connect/P2PPhone_Observer:a	(Landroid/database/Cursor;)V
    //   167: goto -33 -> 134
    //   170: astore_2
    //   171: aload_1
    //   172: ifnull +18 -> 190
    //   175: aload_1
    //   176: invokeinterface 136 1 0
    //   181: ifne +9 -> 190
    //   184: aload_1
    //   185: invokeinterface 139 1 0
    //   190: aload_2
    //   191: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	192	0	this	P2PPhone_Observer
    //   31	154	1	localCursor	Cursor
    //   170	21	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   122	134	170	finally
    //   162	167	170	finally
  }
  
  private void a(int paramInt, Cursor paramCursor)
  {
    if (this.h != null) {
      this.h = null;
    }
    this.h = new ArrayList();
    int m = 0;
    if (m < paramInt)
    {
      Log.d("P2PPhone_Observer", "setMsgId_list()- i" + m);
      if (m == 0)
      {
        paramCursor.moveToFirst();
        this.h.add(Integer.valueOf(paramCursor.getInt(0)));
      }
      for (;;)
      {
        m += 1;
        break;
        paramCursor.moveToNext();
        this.h.add(Integer.valueOf(paramCursor.getInt(0)));
      }
    }
  }
  
  private void a(Cursor paramCursor)
  {
    this.c = paramCursor.getCount();
    Log.d("P2PPhone_Observer", "[deliver_mms_kikat]registerDBChangeObserver6 before_count= " + this.b);
    Log.d("P2PPhone_Observer", "[deliver_mms_kikat]registerDBChangeObserver6 count= " + this.c);
    if (this.b < this.c)
    {
      int n = 0;
      int i1 = 0;
      if (n < this.c - this.b)
      {
        int m;
        if ((n == 0) && (paramCursor.moveToFirst()) && (!b(paramCursor.getInt(0)))) {
          m = paramCursor.getInt(0);
        }
        for (;;)
        {
          n += 1;
          i1 = m;
          break;
          m = i1;
          if (paramCursor.moveToNext())
          {
            m = i1;
            if (!b(paramCursor.getInt(0))) {
              m = paramCursor.getInt(0);
            }
          }
        }
      }
      if (!com.lge.p2p.msg.a.a.e(this.a))
      {
        if (this.f == null) {
          bindService(new Intent("com.lge.qpair.api.r1.QPairService"), this.k, 0);
        }
        String str = a(i1);
        new l(getApplicationContext()).a(str).b(d(str)).d(String.valueOf(System.currentTimeMillis())).c(Integer.toString(i1)).a(com.lge.p2p.msg.g.a.a.a(paramCursor, getApplicationContext(), str)).a().a(this.f);
        Log.d("P2PPhone_Observer", "[deliver_mms_kikat]Send to Tablet");
      }
    }
    for (;;)
    {
      this.b = this.c;
      return;
      f();
      a(this.c, paramCursor);
    }
  }
  
  private void a(boolean paramBoolean)
  {
    this.g = paramBoolean;
  }
  
  private boolean a(String paramString)
  {
    return b(paramString) != null;
  }
  
  private String b(String paramString)
  {
    if (Telephony.Mms.isEmailAddress(paramString)) {}
    do
    {
      return null;
      paramString = c(paramString);
    } while ((paramString == null) || (paramString.length() <= 0));
    return paramString;
  }
  
  private void b(Cursor paramCursor)
  {
    this.c = paramCursor.getCount();
    Log.d("P2PPhone_Observer", "[deliver_mms_new]registerDBChangeObserver6 before_count= " + this.b);
    Log.d("P2PPhone_Observer", "[deliver_mms_new]registerDBChangeObserver6 count= " + this.c);
    if (this.b < this.c)
    {
      int m = 0;
      if (m < this.c - this.b)
      {
        Object localObject1;
        if (m == 0)
        {
          paramCursor.moveToFirst();
          c(paramCursor);
          if ((!com.lge.p2p.msg.a.a.e(this.a)) && (this.d == -1))
          {
            if (this.f == null) {
              bindService(new Intent("com.lge.qpair.api.r1.QPairService"), this.k, 0);
            }
            localObject2 = paramCursor.getString(paramCursor.getColumnIndex("address"));
            if (com.lge.p2p.msg.a.a.h()) {
              Log.d("P2PPhone_Observer", "[deliver_mms_new]wrong number0=" + (String)localObject2);
            }
            if (!TextUtils.isEmpty((CharSequence)localObject2))
            {
              localObject1 = localObject2;
              if (!"Unknown".equalsIgnoreCase((String)localObject2)) {}
            }
            else
            {
              Log.d("P2PPhone_Observer", "[deliver_mms_new]wrong number1=" + (String)localObject2);
              localObject1 = com.lge.p2p.msg.a.a.d(this.a);
            }
            localObject2 = localObject1;
            if (!a((String)localObject1))
            {
              if (!com.lge.p2p.msg.a.a.h()) {
                break label395;
              }
              Log.d("P2PPhone_Observer", "[deliver_mms_new]wrong number2=" + (String)localObject1);
              label303:
              localObject2 = "";
            }
            new l(getApplicationContext()).a((String)localObject2).b(d((String)localObject2)).d(String.valueOf(System.currentTimeMillis())).c(Integer.toString(paramCursor.getInt(0))).a(com.lge.p2p.msg.g.a.a.a(paramCursor, getApplicationContext(), (String)localObject2)).a().a(this.f);
            Log.d("P2PPhone_Observer", "[deliver_mms_new]1 Send to Tablet");
          }
        }
        label395:
        do
        {
          m += 1;
          break;
          Log.d("P2PPhone_Observer", "[deliver_mms_new]wrong number2=...");
          break label303;
          paramCursor.moveToNext();
          c(paramCursor);
        } while ((com.lge.p2p.msg.a.a.e(this.a)) || (this.d != -1));
        Object localObject2 = paramCursor.getString(paramCursor.getColumnIndex("address"));
        if (com.lge.p2p.msg.a.a.h())
        {
          Log.d("P2PPhone_Observer", "[deliver_mms_new]wrong number0.5=" + (String)localObject2);
          label486:
          if (!TextUtils.isEmpty((CharSequence)localObject2))
          {
            localObject1 = localObject2;
            if (!"Unknown".equalsIgnoreCase((String)localObject2)) {}
          }
          else
          {
            Log.d("P2PPhone_Observer", "[deliver_mms_new]wrong number3=" + (String)localObject2);
            localObject1 = com.lge.p2p.msg.a.a.d(this.a);
          }
          localObject2 = localObject1;
          if (!a((String)localObject1))
          {
            if (!com.lge.p2p.msg.a.a.h()) {
              break label685;
            }
            Log.d("P2PPhone_Observer", "[deliver_mms_new]wrong number4=" + (String)localObject1);
          }
        }
        for (;;)
        {
          localObject2 = "";
          new l(getApplicationContext()).a((String)localObject2).b(d((String)localObject2)).d(String.valueOf(System.currentTimeMillis())).c(Integer.toString(paramCursor.getInt(0))).a(com.lge.p2p.msg.g.a.a.a(paramCursor, getApplicationContext(), (String)localObject2)).a().a(this.f);
          Log.d("P2PPhone_Observer", "[deliver_mms_new]2 Send to Tablet");
          break;
          Log.d("P2PPhone_Observer", "[deliver_mms_new]wrong number0.5=...");
          break label486;
          label685:
          Log.d("P2PPhone_Observer", "[deliver_mms_new]wrong number4=...");
        }
      }
    }
    this.b = this.c;
  }
  
  private boolean b()
  {
    return this.g;
  }
  
  private boolean b(int paramInt)
  {
    return (this.h != null) && (this.h.contains(Integer.valueOf(paramInt)));
  }
  
  private static String c(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int n = paramString.length();
    int m = 0;
    if (m < n)
    {
      char c1 = paramString.charAt(m);
      if ((c1 == '+') && (localStringBuilder.length() == 0)) {
        localStringBuilder.append(c1);
      }
      label94:
      do
      {
        for (;;)
        {
          m += 1;
          break;
          if ((c1 == '#') || (c1 == '*'))
          {
            localStringBuilder.append(c1);
          }
          else
          {
            if (!Character.isDigit(c1)) {
              break label94;
            }
            localStringBuilder.append(c1);
          }
        }
      } while (j.get(Character.valueOf(c1)) != null);
      return null;
    }
    return localStringBuilder.toString();
  }
  
  private void c(Cursor paramCursor)
  {
    if ((b()) && ((paramCursor.getInt(paramCursor.getColumnIndex("save_call_type")) == 1) || (paramCursor.getInt(paramCursor.getColumnIndex("save_call_type")) == 2)))
    {
      this.d = paramCursor.getInt(paramCursor.getColumnIndex("save_call_type"));
      return;
    }
    this.d = -1;
  }
  
  private boolean c()
  {
    PackageManager localPackageManager = getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if ("com.lge.message".equals(localApplicationInfo.packageName)) {
        return true;
      }
      if (("com.android.mms".equals(localApplicationInfo.packageName)) && (d())) {
        return true;
      }
      Log.d("P2PPhone_Observer", "Installed package :" + localApplicationInfo.packageName);
      Log.d("P2PPhone_Observer", "Launch Activity :" + localPackageManager.getLaunchIntentForPackage(localApplicationInfo.packageName));
    }
    return false;
  }
  
  private String d(String paramString)
  {
    if (Telephony.Mms.isPhoneNumber(paramString)) {}
    for (Object localObject = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(paramString));; localObject = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Email.CONTENT_LOOKUP_URI, Uri.encode(paramString)))
    {
      localObject = getContentResolver().query((Uri)localObject, null, null, null, null);
      if (localObject != null) {
        break;
      }
      do
      {
        return paramString;
      } while (!Telephony.Mms.isEmailAddress(paramString));
    }
    try
    {
      if (((Cursor)localObject).moveToFirst()) {
        paramString = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("display_name"));
      }
      return paramString;
    }
    finally
    {
      ((Cursor)localObject).close();
    }
  }
  
  private boolean d()
  {
    boolean bool = false;
    try
    {
      int m = getApplicationContext().getPackageManager().getPackageInfo("com.android.mms", 128).versionCode;
      if ((30500000 > m) || (30500000 == m)) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return false;
  }
  
  private boolean e()
  {
    String str1 = Build.MODEL;
    String str2 = com.lge.p2p.msg.a.a.e();
    String str3 = com.lge.p2p.msg.a.a.e();
    if (str1.contains("LG"))
    {
      if (((str3.equals("VZW")) && (str2.equals("US"))) || ((str3.equals("CRK")) && (str2.equals("US"))) || ((str3.equals("USC")) && (str2.equals("US"))) || ((str3.equals("SPR")) && (str2.equals("US"))) || ((str3.equals("BM")) && (str2.equals("US"))) || ((str3.equals("ACG")) && (str2.equals("US"))) || ((str3.equals("LRA")) && (str2.equals("US")))) {}
      while ((c()) || (str1.contains("D82"))) {
        return false;
      }
      try
      {
        SqliteWrapper.query(getApplicationContext(), getApplicationContext().getContentResolver(), Telephony.Mms.Inbox.CONTENT_URI, null, "m_type = 132", null, "insert_time DESC");
        bool = true;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          boolean bool = false;
        }
      }
      return bool;
    }
    Log.e("P2PPhone_Observer", "NOT LG phone");
    return false;
  }
  
  private void f()
  {
    if (this.h != null) {
      this.h = null;
    }
  }
  
  private void g()
  {
    if (e()) {
      a(true);
    }
    try
    {
      for (;;)
      {
        if (b()) {
          localObject1 = SqliteWrapper.query(getApplicationContext(), getApplicationContext().getContentResolver(), Telephony.Mms.Inbox.CONTENT_URI, null, "m_type = 132", null, "insert_time DESC");
        }
        try
        {
          label44:
          Log.d("P2PPhone_Observer", "registerDBChangeObserver3 - ok");
          Log.d("P2PPhone_Observer", "registerDBChangeObserver3-1 count= " + ((Cursor)localObject1).getCount());
          if ((localObject1 == null) || (((Cursor)localObject1).getCount() < 1))
          {
            if ((localObject1 != null) && (!((Cursor)localObject1).isClosed())) {
              ((Cursor)localObject1).close();
            }
            if ((localObject1 != null) && (!((Cursor)localObject1).isClosed())) {
              ((Cursor)localObject1).close();
            }
          }
          for (;;)
          {
            return;
            a(false);
            break;
            localObject1 = SqliteWrapper.query(getApplicationContext(), getApplicationContext().getContentResolver(), Telephony.Mms.Inbox.CONTENT_URI, null, "m_type = 132", null, "date DESC");
            break label44;
            this.b = ((Cursor)localObject1).getCount();
            Log.d("P2PPhone_Observer", "registerDBChangeObserver before count=" + this.b);
            try
            {
              if (!b()) {
                a(this.b, (Cursor)localObject1);
              }
              Log.d("P2PPhone_Observer", "registerDBChangeObserver6 before_count= " + this.b);
              Log.d("P2PPhone_Observer", "registerDBChangeObserver6 count= " + this.c);
              if ((localObject1 != null) && (!((Cursor)localObject1).isClosed())) {
                ((Cursor)localObject1).close();
              }
              return;
            }
            finally
            {
              if ((localObject1 != null) && (!((Cursor)localObject1).isClosed())) {
                ((Cursor)localObject1).close();
              }
            }
          }
          if (localObject4 == null) {
            break label370;
          }
        }
        finally
        {
          localObject4 = localObject1;
          localObject1 = localObject5;
        }
      }
    }
    finally
    {
      for (;;)
      {
        Object localObject1;
        label370:
        Object localObject4 = null;
      }
    }
    if (!localObject4.isClosed()) {
      localObject4.close();
    }
    throw ((Throwable)localObject1);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    bindService(new Intent("com.lge.qpair.api.r1.QPairService"), this.k, 0);
    g();
    this.a = this;
    this.a.getContentResolver().registerContentObserver(Telephony.MmsSms.CONTENT_URI, true, this.l);
    Log.d("ihy_test", "------- TestService : onCreate-------");
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    if (!b()) {
      f();
    }
    if (this.f != null)
    {
      unbindService(this.k);
      this.f = null;
    }
    Log.d("ihy_test", "------- TestService : onDestroy-------");
    this.a.getContentResolver().unregisterContentObserver(this.l);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}
