package com.esurance.claims;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.esurance.common.AppSession;
import com.esurance.common.TaskActivity;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class VRAPdfActivity
  extends TaskActivity
{
  private final String VRAPDF = VRAPdfActivity.class.getSimpleName();
  private AppSession appSession;
  private Context context;
  File filePath;
  Intent intent;
  Uri pdfUri;
  TaskActivity taskactivity;
  
  public VRAPdfActivity() {}
  
  private void initShareIntent(String paramString)
  {
    int j = 0;
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("image/jpeg");
    Object localObject = getPackageManager().queryIntentActivities(localIntent, 0);
    ResolveInfo localResolveInfo;
    if (!((List)localObject).isEmpty())
    {
      localObject = ((List)localObject).iterator();
      i = j;
      if (((Iterator)localObject).hasNext())
      {
        localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
        if ((!paramString.contains("mail")) || ((!localResolveInfo.activityInfo.packageName.toLowerCase(Locale.US).contains(paramString)) && (!localResolveInfo.activityInfo.name.toLowerCase(Locale.US).contains(paramString)) && (!localResolveInfo.activityInfo.name.toLowerCase(Locale.US).contains("gm")))) {
          break label182;
        }
        localIntent.putExtra("android.intent.extra.STREAM", this.pdfUri);
        localIntent.setPackage(localResolveInfo.activityInfo.packageName);
      }
    }
    for (int i = 1;; i = 1)
    {
      if (i != 0) {
        break label255;
      }
      return;
      label182:
      if ((!localResolveInfo.activityInfo.packageName.toLowerCase(Locale.US).contains(paramString)) && (!localResolveInfo.activityInfo.name.toLowerCase(Locale.US).contains(paramString))) {
        break;
      }
      localIntent.putExtra("android.intent.extra.STREAM", this.pdfUri);
      localIntent.setPackage(localResolveInfo.activityInfo.packageName);
    }
    label255:
    startActivity(Intent.createChooser(localIntent, "Select"));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.intent = new Intent("android.intent.action.SEND");
    this.mToolbar.getMenu().clear();
    this.mToolbar.inflateMenu(2131689473);
    this.mToolbar.setLogo(null);
    this.mToolbar.setTitle(2131166790);
    this.context = getApplicationContext();
    this.appSession = ((AppSession)getApplicationContext());
    Log.e("Bandera", "1");
    paramBundle = getPackageManager();
    List localList = paramBundle.getInstalledPackages(0);
    Log.e("Bandera", "2");
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      try
      {
        paramBundle.getApplicationInfo(localPackageInfo.packageName, 0);
        if (localPackageInfo.packageName.contains("dropbox")) {
          this.mToolbar.getMenu().findItem(2131625356).setVisible(true);
        }
        if (localPackageInfo.packageName.contains("evernote")) {
          this.mToolbar.getMenu().findItem(2131625355).setVisible(true);
        }
        Log.e("Nombres", localPackageInfo.packageName);
      }
      catch (Exception localException)
      {
        Log.e("Error", "Nombres " + localException.getMessage());
      }
    }
    Log.e("Bandera", localList.size() + "");
    this.mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
    {
      /* Error */
      public boolean onMenuItemClick(MenuItem paramAnonymousMenuItem)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   4: new 29	java/io/File
        //   7: dup
        //   8: invokestatic 35	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
        //   11: invokevirtual 38	java/io/File:getAbsoluteFile	()Ljava/io/File;
        //   14: ldc 40
        //   16: invokespecial 43	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
        //   19: putfield 47	com/esurance/claims/VRAPdfActivity:filePath	Ljava/io/File;
        //   22: aload_0
        //   23: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   26: new 49	java/lang/StringBuilder
        //   29: dup
        //   30: invokespecial 50	java/lang/StringBuilder:<init>	()V
        //   33: ldc 52
        //   35: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   38: aload_0
        //   39: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   42: getfield 47	com/esurance/claims/VRAPdfActivity:filePath	Ljava/io/File;
        //   45: invokevirtual 60	java/io/File:getAbsolutePath	()Ljava/lang/String;
        //   48: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   51: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   54: invokestatic 69	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
        //   57: putfield 73	com/esurance/claims/VRAPdfActivity:pdfUri	Landroid/net/Uri;
        //   60: aconst_null
        //   61: astore_3
        //   62: new 75	java/io/FileInputStream
        //   65: dup
        //   66: aload_0
        //   67: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   70: getfield 47	com/esurance/claims/VRAPdfActivity:filePath	Ljava/io/File;
        //   73: invokevirtual 60	java/io/File:getAbsolutePath	()Ljava/lang/String;
        //   76: invokespecial 78	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
        //   79: astore 4
        //   81: aload 4
        //   83: astore_3
        //   84: sipush 8192
        //   87: newarray byte
        //   89: astore 5
        //   91: new 80	java/io/ByteArrayOutputStream
        //   94: dup
        //   95: invokespecial 81	java/io/ByteArrayOutputStream:<init>	()V
        //   98: astore 4
        //   100: new 83	android/util/Base64OutputStream
        //   103: dup
        //   104: aload 4
        //   106: iconst_0
        //   107: invokespecial 86	android/util/Base64OutputStream:<init>	(Ljava/io/OutputStream;I)V
        //   110: astore 6
        //   112: aload_3
        //   113: aload 5
        //   115: invokevirtual 92	java/io/InputStream:read	([B)I
        //   118: istore_2
        //   119: iload_2
        //   120: iconst_m1
        //   121: if_icmpeq +87 -> 208
        //   124: aload 6
        //   126: aload 5
        //   128: iconst_0
        //   129: iload_2
        //   130: invokevirtual 96	android/util/Base64OutputStream:write	([BII)V
        //   133: goto -21 -> 112
        //   136: astore_3
        //   137: aload_3
        //   138: invokevirtual 99	java/io/IOException:printStackTrace	()V
        //   141: aload_1
        //   142: invokeinterface 105 1 0
        //   147: tableswitch	default:+49->196, 2131625348:+69->216, 2131625349:+99->246, 2131625350:+49->196, 2131625351:+49->196, 2131625352:+49->196, 2131625353:+299->446, 2131625354:+235->382, 2131625355:+211->358, 2131625356:+223->370
        //   196: iconst_0
        //   197: ireturn
        //   198: astore 4
        //   200: aload 4
        //   202: invokevirtual 106	java/io/FileNotFoundException:printStackTrace	()V
        //   205: goto -121 -> 84
        //   208: aload 6
        //   210: invokevirtual 109	android/util/Base64OutputStream:close	()V
        //   213: goto -72 -> 141
        //   216: aload_0
        //   217: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   220: ldc 110
        //   222: invokevirtual 114	com/esurance/claims/VRAPdfActivity:getString	(I)Ljava/lang/String;
        //   225: aload_0
        //   226: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   229: invokestatic 118	com/esurance/claims/VRAPdfActivity:access$000	(Lcom/esurance/claims/VRAPdfActivity;)Landroid/content/Context;
        //   232: invokestatic 124	com/esurance/common/Tracking:LogButtonTracking	(Ljava/lang/String;Landroid/content/Context;)V
        //   235: aload_0
        //   236: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   239: ldc 126
        //   241: invokestatic 130	com/esurance/claims/VRAPdfActivity:access$100	(Lcom/esurance/claims/VRAPdfActivity;Ljava/lang/String;)V
        //   244: iconst_1
        //   245: ireturn
        //   246: aload_0
        //   247: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   250: ldc -125
        //   252: invokevirtual 114	com/esurance/claims/VRAPdfActivity:getString	(I)Ljava/lang/String;
        //   255: aload_0
        //   256: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   259: invokestatic 118	com/esurance/claims/VRAPdfActivity:access$000	(Lcom/esurance/claims/VRAPdfActivity;)Landroid/content/Context;
        //   262: invokestatic 124	com/esurance/common/Tracking:LogButtonTracking	(Ljava/lang/String;Landroid/content/Context;)V
        //   265: new 133	android/content/Intent
        //   268: dup
        //   269: aload_0
        //   270: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   273: invokestatic 118	com/esurance/claims/VRAPdfActivity:access$000	(Lcom/esurance/claims/VRAPdfActivity;)Landroid/content/Context;
        //   276: ldc -121
        //   278: invokespecial 138	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
        //   281: astore_1
        //   282: aload_1
        //   283: ldc -117
        //   285: invokevirtual 143	android/content/Intent:setFlags	(I)Landroid/content/Intent;
        //   288: pop
        //   289: aload_1
        //   290: ldc -111
        //   292: invokestatic 69	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
        //   295: ldc -109
        //   297: invokevirtual 151	android/content/Intent:setDataAndType	(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;
        //   300: pop
        //   301: aload_1
        //   302: getstatic 155	com/esurance/idcards/PrintDialogActivity:TITLE_DIALOG	Ljava/lang/String;
        //   305: aload_0
        //   306: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   309: invokestatic 118	com/esurance/claims/VRAPdfActivity:access$000	(Lcom/esurance/claims/VRAPdfActivity;)Landroid/content/Context;
        //   312: ldc -100
        //   314: invokevirtual 159	android/content/Context:getString	(I)Ljava/lang/String;
        //   317: invokevirtual 163	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   320: pop
        //   321: aload_1
        //   322: getstatic 166	com/esurance/idcards/PrintDialogActivity:DESCRIPTION_DIALOG	Ljava/lang/String;
        //   325: ldc -88
        //   327: invokevirtual 163	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   330: pop
        //   331: aload_1
        //   332: getstatic 171	com/esurance/idcards/PrintDialogActivity:FILE_STRING	Ljava/lang/String;
        //   335: aload 4
        //   337: invokevirtual 172	java/io/ByteArrayOutputStream:toString	()Ljava/lang/String;
        //   340: invokevirtual 163	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   343: pop
        //   344: aload_0
        //   345: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   348: invokestatic 118	com/esurance/claims/VRAPdfActivity:access$000	(Lcom/esurance/claims/VRAPdfActivity;)Landroid/content/Context;
        //   351: aload_1
        //   352: invokevirtual 176	android/content/Context:startActivity	(Landroid/content/Intent;)V
        //   355: goto -111 -> 244
        //   358: aload_0
        //   359: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   362: ldc -78
        //   364: invokestatic 130	com/esurance/claims/VRAPdfActivity:access$100	(Lcom/esurance/claims/VRAPdfActivity;Ljava/lang/String;)V
        //   367: goto -123 -> 244
        //   370: aload_0
        //   371: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   374: ldc -76
        //   376: invokestatic 130	com/esurance/claims/VRAPdfActivity:access$100	(Lcom/esurance/claims/VRAPdfActivity;Ljava/lang/String;)V
        //   379: goto -135 -> 244
        //   382: aload_0
        //   383: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   386: ldc -75
        //   388: invokevirtual 114	com/esurance/claims/VRAPdfActivity:getString	(I)Ljava/lang/String;
        //   391: aload_0
        //   392: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   395: invokestatic 118	com/esurance/claims/VRAPdfActivity:access$000	(Lcom/esurance/claims/VRAPdfActivity;)Landroid/content/Context;
        //   398: invokestatic 124	com/esurance/common/Tracking:LogButtonTracking	(Ljava/lang/String;Landroid/content/Context;)V
        //   401: aload_0
        //   402: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   405: invokestatic 185	com/esurance/claims/VRAPdfActivity:access$200	(Lcom/esurance/claims/VRAPdfActivity;)Lcom/esurance/common/AppSession;
        //   408: invokevirtual 190	com/esurance/common/AppSession:logout	()V
        //   411: new 133	android/content/Intent
        //   414: dup
        //   415: aload_0
        //   416: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   419: invokevirtual 194	com/esurance/claims/VRAPdfActivity:getApplication	()Landroid/app/Application;
        //   422: ldc -60
        //   424: invokespecial 138	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
        //   427: astore_1
        //   428: aload_1
        //   429: ldc -59
        //   431: invokevirtual 143	android/content/Intent:setFlags	(I)Landroid/content/Intent;
        //   434: pop
        //   435: aload_0
        //   436: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   439: aload_1
        //   440: invokevirtual 198	com/esurance/claims/VRAPdfActivity:startActivity	(Landroid/content/Intent;)V
        //   443: goto -199 -> 244
        //   446: aload_0
        //   447: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   450: ldc -57
        //   452: invokevirtual 114	com/esurance/claims/VRAPdfActivity:getString	(I)Ljava/lang/String;
        //   455: aload_0
        //   456: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   459: invokestatic 118	com/esurance/claims/VRAPdfActivity:access$000	(Lcom/esurance/claims/VRAPdfActivity;)Landroid/content/Context;
        //   462: invokestatic 124	com/esurance/common/Tracking:LogButtonTracking	(Ljava/lang/String;Landroid/content/Context;)V
        //   465: aload_0
        //   466: getfield 17	com/esurance/claims/VRAPdfActivity$1:this$0	Lcom/esurance/claims/VRAPdfActivity;
        //   469: new 201	com/esurance/navigation/fragments/SettingsFragment
        //   472: dup
        //   473: invokespecial 202	com/esurance/navigation/fragments/SettingsFragment:<init>	()V
        //   476: invokevirtual 206	com/esurance/claims/VRAPdfActivity:addFragment	(Lcom/esurance/common/BaseFragment;)V
        //   479: goto -235 -> 244
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	482	0	this	1
        //   0	482	1	paramAnonymousMenuItem	MenuItem
        //   118	12	2	i	int
        //   61	52	3	localObject1	Object
        //   136	2	3	localIOException	java.io.IOException
        //   79	26	4	localObject2	Object
        //   198	138	4	localFileNotFoundException	java.io.FileNotFoundException
        //   89	38	5	arrayOfByte	byte[]
        //   110	99	6	localBase64OutputStream	android.util.Base64OutputStream
        // Exception table:
        //   from	to	target	type
        //   112	119	136	java/io/IOException
        //   124	133	136	java/io/IOException
        //   208	213	136	java/io/IOException
        //   62	81	198	java/io/FileNotFoundException
      }
    });
    setPageName(this.VRAPDF);
    addFragment(new VRAPdfFragment());
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
}
