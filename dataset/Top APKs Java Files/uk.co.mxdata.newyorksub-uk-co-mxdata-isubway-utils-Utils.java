package uk.co.mxdata.isubway.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
import uk.co.mxdata.isubway.SubwayApplication;
import uk.co.mxdata.isubway.i;
import uk.co.mxdata.isubway.model.datamanager.Line;

public final class Utils
{
  public static Utils.TileResolution a;
  private static final char[] b = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  private static float c = -1.0F;
  
  public static float a(float paramFloat)
  {
    return (int)(SubwayApplication.b().getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static int a(int paramInt)
  {
    return (int)(SubwayApplication.b().getResources().getDisplayMetrics().density * paramInt);
  }
  
  /* Error */
  public static Bitmap a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 66	android/graphics/BitmapFactory$Options
    //   5: dup
    //   6: invokespecial 69	android/graphics/BitmapFactory$Options:<init>	()V
    //   9: astore_1
    //   10: aload_1
    //   11: iconst_1
    //   12: putfield 73	android/graphics/BitmapFactory$Options:inPurgeable	Z
    //   15: invokestatic 40	uk/co/mxdata/isubway/SubwayApplication:b	()Landroid/content/Context;
    //   18: invokevirtual 77	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   21: aload_0
    //   22: invokevirtual 83	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   25: astore_0
    //   26: aload_0
    //   27: aconst_null
    //   28: aload_1
    //   29: invokestatic 89	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   32: astore_1
    //   33: aload_1
    //   34: astore_2
    //   35: aload_0
    //   36: ifnull +9 -> 45
    //   39: aload_0
    //   40: invokevirtual 94	java/io/InputStream:close	()V
    //   43: aload_1
    //   44: astore_2
    //   45: aload_2
    //   46: areturn
    //   47: astore_0
    //   48: aconst_null
    //   49: astore_0
    //   50: aload_0
    //   51: ifnull -6 -> 45
    //   54: aload_0
    //   55: invokevirtual 94	java/io/InputStream:close	()V
    //   58: aconst_null
    //   59: areturn
    //   60: astore_0
    //   61: aconst_null
    //   62: areturn
    //   63: astore_1
    //   64: aconst_null
    //   65: astore_0
    //   66: aload_0
    //   67: ifnull +7 -> 74
    //   70: aload_0
    //   71: invokevirtual 94	java/io/InputStream:close	()V
    //   74: aload_1
    //   75: athrow
    //   76: astore_0
    //   77: aload_1
    //   78: areturn
    //   79: astore_0
    //   80: goto -6 -> 74
    //   83: astore_1
    //   84: goto -18 -> 66
    //   87: astore_1
    //   88: goto -38 -> 50
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	paramString	String
    //   9	35	1	localObject1	Object
    //   63	15	1	localBitmap	Bitmap
    //   83	1	1	localObject2	Object
    //   87	1	1	localIOException	java.io.IOException
    //   1	45	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   2	26	47	java/io/IOException
    //   54	58	60	java/lang/Exception
    //   2	26	63	finally
    //   39	43	76	java/lang/Exception
    //   70	74	79	java/lang/Exception
    //   26	33	83	finally
    //   26	33	87	java/io/IOException
  }
  
  public static BitmapDrawable a(Line paramLine)
  {
    if (paramLine == null) {
      return null;
    }
    paramLine = a("images/line_icons/Line-Icon_" + paramLine.i.toLowerCase() + "@2x.png");
    paramLine.setDensity(240);
    return new BitmapDrawable(SubwayApplication.a().getResources(), paramLine);
  }
  
  public static Drawable a(int paramInt1, int paramInt2)
  {
    LightingColorFilter localLightingColorFilter = new LightingColorFilter(SubwayApplication.a().getResources().getColor(paramInt2), SubwayApplication.a().getResources().getColor(paramInt2));
    Drawable localDrawable = SubwayApplication.a().getResources().getDrawable(paramInt1).mutate();
    localDrawable.setColorFilter(localLightingColorFilter);
    return localDrawable;
  }
  
  public static String a(InputStream paramInputStream)
  {
    localObject5 = null;
    try
    {
      InputStreamReader localInputStreamReader = new InputStreamReader(paramInputStream);
      BufferedReader localBufferedReader;
      if (str == null) {
        break label98;
      }
    }
    finally
    {
      for (;;)
      {
        Object localObject2;
        try
        {
          localBufferedReader = new BufferedReader(localInputStreamReader);
        }
        finally
        {
          String str = null;
          localObject5 = localObject1;
          localObject2 = localObject6;
        }
        try
        {
          str = localBufferedReader.readLine();
          localObject5 = new StringBuilder();
          if (str != null)
          {
            ((StringBuilder)localObject5).append(str);
            str = localBufferedReader.readLine();
          }
          else
          {
            str = ((StringBuilder)localObject5).toString();
            if (localBufferedReader != null) {
              localBufferedReader.close();
            }
            if (localInputStreamReader != null) {
              localInputStreamReader.close();
            }
            if (paramInputStream != null) {
              paramInputStream.close();
            }
            return str;
          }
        }
        finally
        {
          localObject5 = localObject2;
          localObject2 = localObject3;
          Object localObject4 = localObject6;
        }
      }
      localObject1 = finally;
      str = null;
    }
    str.close();
    label98:
    if (localObject5 != null) {
      ((InputStreamReader)localObject5).close();
    }
    if (paramInputStream != null) {
      paramInputStream.close();
    }
    throw localObject1;
  }
  
  public static Utils.TileResolution a(Resources paramResources, JSONObject paramJSONObject)
  {
    int i;
    if (a == null)
    {
      a = Utils.TileResolution.b;
      i = paramResources.getDisplayMetrics().densityDpi;
      if ((i != 120) || (!paramJSONObject.has("lowRes"))) {
        break label45;
      }
      a = Utils.TileResolution.a;
    }
    for (;;)
    {
      return a;
      label45:
      if ((i == 320) && (paramJSONObject.has("XhighRes"))) {
        a = Utils.TileResolution.c;
      } else if ((i > 320) && (paramJSONObject.has("XXhighRes"))) {
        a = Utils.TileResolution.d;
      }
    }
  }
  
  public static void a(Context paramContext)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramContext.getPackageName())));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (paramString != null) && (paramString.length() > 0))
    {
      if (paramString.startsWith("http")) {
        b(paramContext, paramString);
      }
    }
    else {
      return;
    }
    if (paramString.startsWith(paramContext.getString(i.market_url)))
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      try
      {
        paramContext.startActivity(paramString);
        return;
      }
      catch (ActivityNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
    }
    if (c(paramString))
    {
      paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
      try
      {
        paramContext.startActivity(paramString);
        return;
      }
      catch (ActivityNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
    }
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramContext.getString(i.market_url) + paramString));
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
  
  public static void a(ListView paramListView, boolean paramBoolean)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null) {
      return;
    }
    int j = paramListView.getPaddingTop() + paramListView.getPaddingBottom();
    Object localObject;
    int i;
    if ((localListAdapter.getCount() > 3) && (!paramBoolean))
    {
      localObject = localListAdapter.getView(0, null, paramListView);
      if ((localObject instanceof ViewGroup)) {
        ((View)localObject).setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
      }
      ((View)localObject).measure(0, 0);
      i = ((View)localObject).getMeasuredHeight() * localListAdapter.getCount() - 1 + j;
    }
    for (;;)
    {
      localObject = paramListView.getLayoutParams();
      j = paramListView.getDividerHeight();
      ((ViewGroup.LayoutParams)localObject).height = (i + (localListAdapter.getCount() - 1) * j);
      paramListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      paramListView.setVisibility(0);
      return;
      if (localListAdapter.getCount() > 0)
      {
        int k = 0;
        for (;;)
        {
          i = j;
          if (k >= localListAdapter.getCount()) {
            break;
          }
          localObject = localListAdapter.getView(k, null, paramListView);
          if ((localObject instanceof ViewGroup)) {
            ((View)localObject).setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
          }
          ((View)localObject).measure(0, 0);
          j += ((View)localObject).getMeasuredHeight();
          k += 1;
        }
      }
      i = j;
    }
  }
  
  public static void a(FileOutputStream paramFileOutputStream, String paramString)
  {
    try
    {
      paramFileOutputStream.write(paramString.getBytes());
      paramFileOutputStream.flush();
      return;
    }
    finally
    {
      paramFileOutputStream.close();
    }
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (Build.MANUFACTURER.equals("HTC"))
    {
      localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse("mailto:" + paramString1 + "?subject=" + Uri.encode(paramString2) + "&body=" + Uri.encode(paramString3)));
      try
      {
        paramContext.startActivity(localIntent);
        return true;
      }
      catch (Exception paramString1)
      {
        Toast.makeText(paramContext, paramContext.getString(i.about_no_email_clients), 1).show();
        return false;
      }
    }
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("message/rfc822");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { paramString1 });
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString2);
    localIntent.putExtra("android.intent.extra.TEXT", paramString3);
    try
    {
      paramContext.startActivity(Intent.createChooser(localIntent, paramContext.getString(i.app_name) + " " + paramContext.getString(i.about_chooser_support)));
      return true;
    }
    catch (Exception paramString1)
    {
      c.d("RATE", "No email clients, returning false.");
      Toast.makeText(paramContext, paramContext.getString(i.about_no_email_clients), 0).show();
    }
    return false;
  }
  
  public static boolean a(String paramString, int paramInt)
  {
    if (paramString.isEmpty()) {}
    int i;
    do
    {
      return false;
      i = 0;
      if (i >= paramString.length()) {
        break label62;
      }
      if ((i != 0) || (paramString.charAt(i) != '-')) {
        break;
      }
    } while (paramString.length() == 1);
    while (Character.digit(paramString.charAt(i), paramInt) >= 0)
    {
      i += 1;
      break;
    }
    return false;
    label62:
    return true;
  }
  
  public static Drawable b(int paramInt)
  {
    return a(paramInt, uk.co.mxdata.isubway.c.actionbar_icon_base_colour);
  }
  
  public static String b(String paramString)
  {
    paramString = paramString.toCharArray();
    if ((paramString[0] >= 'a') && (paramString[0] <= 'z')) {
      paramString[0] = ((char)(paramString[0] - ' '));
    }
    int i = 1;
    while (i < paramString.length)
    {
      if ((paramString[i] >= 'A') && (paramString[i] <= 'Z')) {
        paramString[i] = ((char)(paramString[i] + ' '));
      }
      i += 1;
    }
    return String.valueOf(paramString);
  }
  
  public static void b(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
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
  
  public static boolean b(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean c(String paramString)
  {
    Iterator localIterator = SubwayApplication.b().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.contentEquals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean d(String paramString)
  {
    PackageManager localPackageManager = SubwayApplication.b().getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 128);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static boolean e(String paramString)
  {
    return a(paramString, 10);
  }
}
