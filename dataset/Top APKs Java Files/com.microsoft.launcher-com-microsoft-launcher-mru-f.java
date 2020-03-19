package com.microsoft.launcher.mru;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.microsoft.launcher.Launcher;
import com.microsoft.launcher.mru.identity.i;
import com.microsoft.launcher.mru.model.DocMetadata;
import com.microsoft.launcher.setting.am;
import com.microsoft.launcher.utils.v;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class f
{
  private static Dictionary<String, String> a = new Hashtable();
  private static Dictionary<String, String> b = new Hashtable();
  private static int c = 9;
  private static int d = 1000;
  
  static
  {
    a.put("OneNote", "com.microsoft.office.onenote");
    a.put("Word", "com.microsoft.office.word");
    a.put("PowerPoint", "com.microsoft.office.powerpoint");
    a.put("Excel", "com.microsoft.office.excel");
    b.put("com.microsoft.office.onenote", "onenote:");
    b.put("com.microsoft.office.word", "ms-word:");
    b.put("com.microsoft.office.powerpoint", "ms-powerpoint:");
    b.put("com.microsoft.office.excel", "ms-excel:");
  }
  
  public static int a(Context paramContext, String paramString)
  {
    paramString = "doc_" + paramString.replaceAll("\\.", "_");
    return paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName());
  }
  
  public static String a(DocMetadata paramDocMetadata)
  {
    return (String)a.get(paramDocMetadata.Application);
  }
  
  private static String a(String paramString)
  {
    int i = paramString.lastIndexOf(".");
    if (i > 0) {
      return paramString.substring(i + 1);
    }
    return "";
  }
  
  public static void a(Activity paramActivity, Context paramContext)
  {
    if (!paramActivity.isFinishing()) {
      if (Build.VERSION.SDK_INT < 21) {
        break label105;
      }
    }
    label105:
    for (int i = 2131493080;; i = 2131493081)
    {
      paramActivity = new AlertDialog.Builder(paramContext, i);
      paramActivity.setTitle(paramContext.getResources().getString(2131230986));
      paramActivity.setMessage(paramContext.getResources().getString(2131230984));
      paramActivity.setPositiveButton(paramContext.getResources().getString(2131230985), null);
      paramActivity.create().show();
      if (com.microsoft.launcher.mru.identity.f.a().a.a()) {
        com.microsoft.launcher.mru.identity.f.a().a.c((Activity)paramContext, null);
      }
      return;
    }
  }
  
  public static void a(Context paramContext, DocMetadata paramDocMetadata)
  {
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(paramContext.getResources().getString(2131230990), paramDocMetadata.DocumentUrl));
    Toast.makeText(paramContext, paramContext.getResources().getString(2131230991), 0).show();
    v.a("document copy link", "document source", paramDocMetadata.Provider, 1.0F);
  }
  
  public static void a(Context paramContext, DocMetadata paramDocMetadata, Activity paramActivity)
  {
    String str1;
    String str2;
    if (paramDocMetadata.isLocalFile())
    {
      str1 = "file://" + paramDocMetadata.DocumentUrl;
      str2 = a(paramDocMetadata);
      if (!c(paramContext, str2)) {
        break label112;
      }
      paramActivity = new Intent("android.intent.action.VIEW");
      paramActivity.setData(Uri.parse(str1));
      if (!str2.equalsIgnoreCase("com.microsoft.office.onenote")) {
        break label102;
      }
      a(paramContext, null, paramActivity);
    }
    for (;;)
    {
      v.a("document open", "document source", paramDocMetadata.Provider, 1.0F);
      return;
      str1 = b(paramDocMetadata);
      break;
      label102:
      a(paramContext, str2, paramActivity);
      continue;
      label112:
      if (paramDocMetadata.isLocalFile())
      {
        paramActivity = new Intent("android.intent.action.VIEW");
        String str3 = a(paramDocMetadata.DocumentUrl);
        String str4 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str3);
        if ((!str3.equalsIgnoreCase("")) && (str4 != null))
        {
          if (paramDocMetadata.isLocalFile()) {
            paramActivity.setDataAndType(Uri.fromFile(new File(paramDocMetadata.DocumentUrl)), str4);
          }
          for (;;)
          {
            if (paramContext.getPackageManager().queryIntentActivities(paramActivity, 0).size() <= 0) {
              break label233;
            }
            paramContext.startActivity(paramActivity);
            break;
            paramActivity.setDataAndType(Uri.parse(paramDocMetadata.DocumentUrl), str4);
          }
          label233:
          b(str1, str2, paramContext, paramDocMetadata);
        }
        else
        {
          b(str1, str2, paramContext, paramDocMetadata);
        }
      }
      else
      {
        com.microsoft.launcher.mru.a.c.a().a(paramDocMetadata, paramActivity, new g(paramActivity, str1, str2, paramContext, paramDocMetadata));
      }
    }
  }
  
  public static void a(Context paramContext, String paramString, Intent paramIntent)
  {
    Object localObject = paramContext.getPackageManager();
    if (paramString != null)
    {
      localObject = ((PackageManager)localObject).getLaunchIntentForPackage(paramString);
      paramString = (String)localObject;
      if (localObject != null) {}
    }
    else
    {
      paramString = new Intent();
    }
    paramString.setAction("android.intent.action.VIEW");
    paramString.setData(paramIntent.getData());
    paramString.setFlags(paramIntent.getFlags());
    try
    {
      paramContext.startActivity(paramString);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean a(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null) {
        return paramContext.isAvailable();
      }
    }
    return false;
  }
  
  public static boolean a(Launcher paramLauncher, Context paramContext, DocMetadata paramDocMetadata)
  {
    boolean bool = false;
    if ((com.microsoft.launcher.mru.identity.f.a().b.a()) && (com.microsoft.launcher.mru.identity.f.a().a.a()))
    {
      AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext).create();
      localAlertDialog.show();
      localAlertDialog.getWindow().setContentView(2130968768);
      Object localObject = localAlertDialog.getWindow();
      int i = (int)paramContext.getResources().getDimension(2131296628);
      ((Window)localObject).getDecorView().setPadding(i, 0, i, 0);
      localObject = (TextView)localAlertDialog.getWindow().findViewById(2131755858);
      TextView localTextView = (TextView)localAlertDialog.getWindow().findViewById(2131755861);
      ((TextView)localObject).setText(com.microsoft.launcher.mru.identity.f.a().b.d().a);
      localTextView.setText(com.microsoft.launcher.mru.identity.f.a().a.d().a);
      localAlertDialog.getWindow().findViewById(2131755856).setOnClickListener(new j(localAlertDialog, paramLauncher, paramContext, paramDocMetadata));
      localAlertDialog.getWindow().findViewById(2131755859).setOnClickListener(new k(localAlertDialog, paramLauncher, paramContext, paramDocMetadata));
      bool = true;
    }
    do
    {
      return bool;
      if (com.microsoft.launcher.mru.identity.f.a().b.a())
      {
        b(paramLauncher, paramContext, true, paramDocMetadata);
        return true;
      }
    } while (!com.microsoft.launcher.mru.identity.f.a().a.a());
    b(paramLauncher, paramContext, false, paramDocMetadata);
    return true;
  }
  
  public static String b(DocMetadata paramDocMetadata)
  {
    String str = a(paramDocMetadata);
    str = (String)b.get(str);
    if ("onenote:".equalsIgnoreCase(str)) {
      return str + paramDocMetadata.DocumentUrl;
    }
    return str + "ofv" + "|u|" + paramDocMetadata.DocumentUrl;
  }
  
  public static void b(Context paramContext, DocMetadata paramDocMetadata)
  {
    if (paramDocMetadata.isLocalFile())
    {
      String str = "file://" + paramDocMetadata.DocumentUrl;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.SEND");
      localIntent.putExtra("android.intent.extra.STREAM", Uri.parse(str));
      str = a(paramDocMetadata.DocumentUrl);
      localIntent.setType(MimeTypeMap.getSingleton().getMimeTypeFromExtension(str));
      paramContext.startActivity(localIntent);
    }
    for (;;)
    {
      v.a("document share", "document source", paramDocMetadata.Provider, 1.0F);
      return;
      c(paramContext, paramDocMetadata);
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    for (;;)
    {
      try
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString + "&referrer=mslauncher"));
        Object localObject = paramContext.getPackageManager().queryIntentActivities(localIntent, 0).iterator();
        if (((Iterator)localObject).hasNext())
        {
          ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
          if (!localResolveInfo.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
            continue;
          }
          localObject = localResolveInfo.activityInfo;
          localObject = new ComponentName(((ActivityInfo)localObject).applicationInfo.packageName, ((ActivityInfo)localObject).name);
          localIntent.setFlags(268468224);
          localIntent.setComponent((ComponentName)localObject);
          paramContext.startActivity(localIntent);
          i = 1;
          if (i == 0) {
            am.a(paramString);
          }
          return;
        }
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramString)));
        return;
      }
      int i = 0;
    }
  }
  
  private static void b(Launcher paramLauncher, Context paramContext, boolean paramBoolean, DocMetadata paramDocMetadata)
  {
    Object localObject = LayoutInflater.from(paramContext).inflate(2130968805, null);
    PopupWindow localPopupWindow = new PopupWindow((View)localObject, -1, -2);
    localPopupWindow.setFocusable(true);
    localPopupWindow.showAtLocation((View)localObject, 17, 0, 0);
    TextView localTextView = (TextView)((View)localObject).findViewById(2131756041);
    ImageView localImageView = (ImageView)((View)localObject).findViewById(2131756040);
    ProgressBar localProgressBar = (ProgressBar)((View)localObject).findViewById(2131756043);
    localObject = (TextView)((View)localObject).findViewById(2131756042);
    localProgressBar.setProgress(0);
    ((TextView)localObject).setText("0%");
    localTextView.setText(paramDocMetadata.FileName);
    localImageView.setImageResource(a(paramContext, a(paramDocMetadata)));
    new l(localProgressBar, paramLauncher, (TextView)localObject).start();
    com.microsoft.launcher.mru.a.c.a().a(paramLauncher, paramDocMetadata.DocumentUrl, paramBoolean, new o(paramLauncher, localProgressBar, (TextView)localObject, paramDocMetadata, localPopupWindow, paramContext));
    v.a("document upload", "document source", paramDocMetadata.Provider, 1.0F);
  }
  
  private static void b(String paramString1, String paramString2, Context paramContext, DocMetadata paramDocMetadata)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString1));
    bj.a().a(paramString2, localIntent);
    Toast.makeText(paramContext, paramDocMetadata.Application + paramContext.getResources().getString(2131230989), 1).show();
    b(paramContext, paramString2);
  }
  
  public static Date c(DocMetadata paramDocMetadata)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'");
    try
    {
      paramDocMetadata = localSimpleDateFormat.parse(paramDocMetadata.Timestamp);
      return paramDocMetadata;
    }
    catch (ParseException paramDocMetadata)
    {
      paramDocMetadata.printStackTrace();
    }
    return null;
  }
  
  public static void c(Context paramContext, DocMetadata paramDocMetadata)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramDocMetadata.DocumentUrl);
    paramContext.startActivity(Intent.createChooser(localIntent, paramContext.getResources().getString(2131230994)));
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equalsIgnoreCase(paramString)) {
        return true;
      }
    }
    return false;
  }
}
