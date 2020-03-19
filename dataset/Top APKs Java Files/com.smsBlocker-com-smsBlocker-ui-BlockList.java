package com.smsBlocker.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockList
  extends Activity
{
  List a;
  List b;
  String[][] c;
  private ListView d;
  private ArrayList e = new ArrayList();
  private ArrayList f = new ArrayList();
  private int g = 0;
  private TextView h;
  
  public BlockList() {}
  
  private static String a(Context paramContext, String paramString)
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
  
  public final void a(String paramString)
  {
    int j = 0;
    int k;
    int i;
    try
    {
      localObject = fileList();
      k = localObject.length;
      i = 0;
    }
    catch (IOException paramString)
    {
      Object localObject;
      label32:
      return;
    }
    if (i != 0)
    {
      localObject = openFileOutput("blocklist.txt", 32768);
      localObject = new OutputStreamWriter((OutputStream)localObject);
      ((OutputStreamWriter)localObject).write(paramString);
      ((OutputStreamWriter)localObject).flush();
      ((OutputStreamWriter)localObject).close();
      return;
    }
    label107:
    for (;;)
    {
      if (localObject[i].equals("blocklist.txt"))
      {
        i = 1;
        break;
        localObject = openFileOutput("blocklist.txt", 0);
        break label32;
      }
      for (;;)
      {
        if (i < k) {
          break label107;
        }
        i = j;
        break;
        i += 1;
      }
    }
  }
  
  /* Error */
  protected void onActivityResult(int paramInt1, int paramInt2, android.content.Intent paramIntent)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aload_0
    //   4: iload_1
    //   5: iload_2
    //   6: aload_3
    //   7: invokespecial 115	android/app/Activity:onActivityResult	(IILandroid/content/Intent;)V
    //   10: iload_2
    //   11: iconst_m1
    //   12: if_icmpne +457 -> 469
    //   15: iload_1
    //   16: tableswitch	default:+20->36, 1001:+21->37
    //   36: return
    //   37: aload 6
    //   39: astore 4
    //   41: aload_3
    //   42: invokevirtual 121	android/content/Intent:getData	()Landroid/net/Uri;
    //   45: astore_3
    //   46: aload 6
    //   48: astore 4
    //   50: ldc 123
    //   52: new 52	java/lang/StringBuilder
    //   55: dup
    //   56: ldc 125
    //   58: invokespecial 127	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   61: aload_3
    //   62: invokevirtual 130	android/net/Uri:toString	()Ljava/lang/String;
    //   65: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: invokestatic 136	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   74: pop
    //   75: aload 6
    //   77: astore 4
    //   79: aload_3
    //   80: invokevirtual 139	android/net/Uri:getLastPathSegment	()Ljava/lang/String;
    //   83: astore 7
    //   85: ldc -115
    //   87: astore 5
    //   89: aload 6
    //   91: astore 4
    //   93: aload_0
    //   94: invokevirtual 145	com/smsBlocker/ui/BlockList:getContentResolver	()Landroid/content/ContentResolver;
    //   97: astore 8
    //   99: aload 6
    //   101: astore 4
    //   103: aload 8
    //   105: getstatic 151	android/provider/ContactsContract$Contacts:CONTENT_URI	Landroid/net/Uri;
    //   108: aconst_null
    //   109: ldc -103
    //   111: iconst_1
    //   112: anewarray 105	java/lang/String
    //   115: dup
    //   116: iconst_0
    //   117: aload 7
    //   119: aastore
    //   120: aconst_null
    //   121: invokevirtual 159	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   124: astore_3
    //   125: ldc -115
    //   127: astore 6
    //   129: aload_3
    //   130: astore 4
    //   132: aload_3
    //   133: invokeinterface 165 1 0
    //   138: ifne +129 -> 267
    //   141: aload_3
    //   142: astore 4
    //   144: aload 6
    //   146: ldc -115
    //   148: invokevirtual 109	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   151: ifne +261 -> 412
    //   154: aload_3
    //   155: astore 4
    //   157: aload 5
    //   159: ldc -115
    //   161: invokevirtual 109	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   164: ifne +248 -> 412
    //   167: aload_3
    //   168: astore 4
    //   170: new 167	android/app/AlertDialog$Builder
    //   173: dup
    //   174: aload_0
    //   175: invokespecial 170	android/app/AlertDialog$Builder:<init>	(Landroid/content/Context;)V
    //   178: astore 7
    //   180: aload_3
    //   181: astore 4
    //   183: aload 7
    //   185: new 52	java/lang/StringBuilder
    //   188: dup
    //   189: ldc -84
    //   191: invokespecial 127	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   194: aload 6
    //   196: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: ldc -82
    //   201: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   207: invokevirtual 178	android/app/AlertDialog$Builder:setMessage	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    //   210: iconst_0
    //   211: invokevirtual 182	android/app/AlertDialog$Builder:setCancelable	(Z)Landroid/app/AlertDialog$Builder;
    //   214: ldc -72
    //   216: new 186	com/smsBlocker/ui/j
    //   219: dup
    //   220: aload_0
    //   221: aload 6
    //   223: aload 5
    //   225: invokespecial 189	com/smsBlocker/ui/j:<init>	(Lcom/smsBlocker/ui/BlockList;Ljava/lang/String;Ljava/lang/String;)V
    //   228: invokevirtual 193	android/app/AlertDialog$Builder:setPositiveButton	(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
    //   231: ldc -61
    //   233: new 197	com/smsBlocker/ui/k
    //   236: dup
    //   237: aload_0
    //   238: invokespecial 200	com/smsBlocker/ui/k:<init>	(Lcom/smsBlocker/ui/BlockList;)V
    //   241: invokevirtual 203	android/app/AlertDialog$Builder:setNegativeButton	(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
    //   244: pop
    //   245: aload_3
    //   246: astore 4
    //   248: aload 7
    //   250: invokevirtual 207	android/app/AlertDialog$Builder:create	()Landroid/app/AlertDialog;
    //   253: invokevirtual 212	android/app/AlertDialog:show	()V
    //   256: aload_3
    //   257: ifnull -221 -> 36
    //   260: aload_3
    //   261: invokeinterface 213 1 0
    //   266: return
    //   267: aload_3
    //   268: astore 4
    //   270: aload_3
    //   271: aload_3
    //   272: ldc -41
    //   274: invokeinterface 219 2 0
    //   279: invokeinterface 223 2 0
    //   284: astore 6
    //   286: aload_3
    //   287: astore 4
    //   289: aload 8
    //   291: getstatic 226	android/provider/ContactsContract$CommonDataKinds$Phone:CONTENT_URI	Landroid/net/Uri;
    //   294: aconst_null
    //   295: new 52	java/lang/StringBuilder
    //   298: dup
    //   299: ldc -28
    //   301: invokespecial 127	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   304: aload 7
    //   306: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   312: aconst_null
    //   313: aconst_null
    //   314: invokevirtual 159	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   317: astore 9
    //   319: aload_3
    //   320: astore 4
    //   322: aload 9
    //   324: invokeinterface 165 1 0
    //   329: ifne +16 -> 345
    //   332: aload_3
    //   333: astore 4
    //   335: aload 9
    //   337: invokeinterface 213 1 0
    //   342: goto -213 -> 129
    //   345: aload_3
    //   346: astore 4
    //   348: aload 9
    //   350: aload 9
    //   352: ldc -26
    //   354: invokeinterface 219 2 0
    //   359: invokeinterface 234 2 0
    //   364: tableswitch	default:+133->497, 1:+-45->319, 2:+24->388
    //   388: aload_3
    //   389: astore 4
    //   391: aload 9
    //   393: aload 9
    //   395: ldc -20
    //   397: invokeinterface 219 2 0
    //   402: invokeinterface 223 2 0
    //   407: astore 5
    //   409: goto -90 -> 319
    //   412: aload_3
    //   413: astore 4
    //   415: aload_0
    //   416: invokevirtual 240	com/smsBlocker/ui/BlockList:getApplicationContext	()Landroid/content/Context;
    //   419: ldc -14
    //   421: iconst_0
    //   422: invokestatic 248	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   425: invokevirtual 249	android/widget/Toast:show	()V
    //   428: goto -172 -> 256
    //   431: astore 4
    //   433: ldc 123
    //   435: ldc -5
    //   437: aload 4
    //   439: invokestatic 254	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   442: pop
    //   443: aload_3
    //   444: ifnull -408 -> 36
    //   447: aload_3
    //   448: invokeinterface 213 1 0
    //   453: return
    //   454: astore_3
    //   455: aload 4
    //   457: ifnull +10 -> 467
    //   460: aload 4
    //   462: invokeinterface 213 1 0
    //   467: aload_3
    //   468: athrow
    //   469: ldc 123
    //   471: ldc_w 256
    //   474: invokestatic 259	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   477: pop
    //   478: return
    //   479: astore 5
    //   481: aload_3
    //   482: astore 4
    //   484: aload 5
    //   486: astore_3
    //   487: goto -32 -> 455
    //   490: astore 4
    //   492: aconst_null
    //   493: astore_3
    //   494: goto -61 -> 433
    //   497: goto -178 -> 319
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	500	0	this	BlockList
    //   0	500	1	paramInt1	int
    //   0	500	2	paramInt2	int
    //   0	500	3	paramIntent	android.content.Intent
    //   39	375	4	localObject1	Object
    //   431	30	4	localException1	Exception
    //   482	1	4	localIntent	android.content.Intent
    //   490	1	4	localException2	Exception
    //   87	321	5	str1	String
    //   479	6	5	localObject2	Object
    //   1	284	6	str2	String
    //   83	222	7	localObject3	Object
    //   97	193	8	localContentResolver	android.content.ContentResolver
    //   317	77	9	localCursor	android.database.Cursor
    // Exception table:
    //   from	to	target	type
    //   132	141	431	java/lang/Exception
    //   144	154	431	java/lang/Exception
    //   157	167	431	java/lang/Exception
    //   170	180	431	java/lang/Exception
    //   183	245	431	java/lang/Exception
    //   248	256	431	java/lang/Exception
    //   270	286	431	java/lang/Exception
    //   289	319	431	java/lang/Exception
    //   322	332	431	java/lang/Exception
    //   335	342	431	java/lang/Exception
    //   348	388	431	java/lang/Exception
    //   391	409	431	java/lang/Exception
    //   415	428	431	java/lang/Exception
    //   41	46	454	finally
    //   50	75	454	finally
    //   79	85	454	finally
    //   93	99	454	finally
    //   103	125	454	finally
    //   132	141	454	finally
    //   144	154	454	finally
    //   157	167	454	finally
    //   170	180	454	finally
    //   183	245	454	finally
    //   248	256	454	finally
    //   270	286	454	finally
    //   289	319	454	finally
    //   322	332	454	finally
    //   335	342	454	finally
    //   348	388	454	finally
    //   391	409	454	finally
    //   415	428	454	finally
    //   433	443	479	finally
    //   41	46	490	java/lang/Exception
    //   50	75	490	java/lang/Exception
    //   79	85	490	java/lang/Exception
    //   93	99	490	java/lang/Exception
    //   103	125	490	java/lang/Exception
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = true;
    switch (paramMenuItem.getItemId())
    {
    default: 
      bool = super.onContextItemSelected(paramMenuItem);
    }
    return bool;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903049);
    this.d = ((ListView)findViewById(2131361801));
    this.h = ((TextView)findViewById(2131361802));
    this.h.setText("Block list contains list of SMS senders whose all SMS are to be blocked. Add a sender in this list from 'Options' key.");
    this.d.setEmptyView(findViewById(2131361802));
    this.d.setCacheColorHint(0);
    this.a = new ArrayList();
    this.b = new ArrayList();
    paramBundle = a(this, "blocklist.txt");
    if (paramBundle.equals("")) {
      return;
    }
    paramBundle = paramBundle.split(";");
    this.c = ((String[][])Array.newInstance(String.class, new int[] { paramBundle.length, 3 }));
    int i = 0;
    if (i >= paramBundle.length)
    {
      Arrays.sort(this.c, new o(this));
      this.d.setAdapter(new h(this, this, this.a, this.b));
      setTitle("Block list");
      return;
    }
    String[] arrayOfString = paramBundle[i].split(",");
    if (arrayOfString[0].equals(arrayOfString[1]))
    {
      this.a.add(arrayOfString[0]);
      this.b.add("");
      this.c[i][0] = arrayOfString[0];
      this.c[i][1] = "";
      this.c[i][2] = i;
    }
    for (;;)
    {
      i += 1;
      break;
      this.a.add(arrayOfString[0]);
      this.b.add(arrayOfString[1]);
      this.c[i][0] = arrayOfString[0];
      this.c[i][1] = arrayOfString[1];
      this.c[i][2] = i;
    }
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
    getMenuInflater().inflate(2131296257, paramContextMenu);
    paramContextMenu.setHeaderTitle("Add from");
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
      a(this, "trialflag.txt");
      paramMenuItem = getPackageManager().getInstalledApplications(128);
      int i = 0;
      if (i >= paramMenuItem.size())
      {
        i = 0;
        label67:
        if ((i == 0) && (this.a.size() >= 50)) {
          break label182;
        }
        paramMenuItem = new AlertDialog.Builder(this);
        paramMenuItem.setTitle("Add from");
        p localP = new p(this);
        paramMenuItem.setItems(new CharSequence[] { "Inbox", "Phonebook", "Manual entry" }, localP);
        paramMenuItem.create().show();
      }
      for (;;)
      {
        return true;
        if (((ApplicationInfo)paramMenuItem.get(i)).packageName.equals("com.smsBlockerUnlocker"))
        {
          i = 1;
          break label67;
        }
        i += 1;
        break;
        label182:
        new AlertDialog.Builder(this).setTitle("Alert").setMessage("Perchase premium version to add unlimited entirs. You can add only 50 entries in trial version.").setPositiveButton(2131230723, new l(this)).create().show();
      }
    }
    new AlertDialog.Builder(this).setTitle("Help").setMessage("Block list contains list of SMS senders whose all SMS are to be blocked.").setPositiveButton(2131230723, new m(this)).create().show();
    return true;
  }
  
  protected void onResume()
  {
    super.onResume();
    this.a.clear();
    this.b.clear();
    Object localObject = a(this, "blocklist.txt");
    if (((String)localObject).equals("")) {
      return;
    }
    localObject = ((String)localObject).split(";");
    this.c = null;
    this.c = ((String[][])Array.newInstance(String.class, new int[] { localObject.length, 3 }));
    int i = 0;
    if (i >= localObject.length)
    {
      Arrays.sort(this.c, new q(this));
      this.d.setAdapter(new h(this, this, this.a, this.b));
      return;
    }
    String[] arrayOfString = localObject[i].split(",");
    if (arrayOfString[0].equals(arrayOfString[1]))
    {
      this.a.add(arrayOfString[0]);
      this.b.add("");
      this.c[i][0] = arrayOfString[0];
      this.c[i][1] = "";
      this.c[i][2] = i;
    }
    for (;;)
    {
      i += 1;
      break;
      this.a.add(arrayOfString[0]);
      this.b.add(arrayOfString[1]);
      this.c[i][0] = arrayOfString[0];
      this.c[i][1] = arrayOfString[1];
      this.c[i][2] = i;
    }
  }
}
