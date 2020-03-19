package com.king.nativesharing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.content.FileProvider;
import android.util.Log;
import com.king.sdk.core.KsdkCoreActivityHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

public class NativeSharing
{
  private static final String TAG = "native-sharing";
  private static final String shareFolder = "native-sharing-data";
  
  public NativeSharing() {}
  
  private static File copyFile(String paramString1, String paramString2, String paramString3)
    throws IOException
  {
    Object localObject = new File(paramString3);
    if (!((File)localObject).exists()) {
      ((File)localObject).mkdirs();
    }
    localObject = new File(paramString1, paramString2);
    paramString1 = new File(paramString3, paramString2);
    if (!((File)localObject).getCanonicalPath().equals(paramString1.getCanonicalPath()))
    {
      paramString2 = new FileInputStream((File)localObject);
      paramString3 = new FileOutputStream(paramString1);
      localObject = new byte['Ѐ'];
      for (;;)
      {
        int i = paramString2.read((byte[])localObject);
        if (i == -1) {
          break;
        }
        paramString3.write((byte[])localObject, 0, i);
      }
      paramString2.close();
      paramString3.flush();
      paramString3.close();
    }
    return paramString1;
  }
  
  private static Uri getFileUri(String paramString, Context paramContext)
    throws IOException
  {
    Object localObject = new File(paramContext.getCacheDir(), "native-sharing-data");
    paramString = new File(paramString);
    paramString = copyFile(paramString.getParent(), paramString.getName(), ((File)localObject).toString());
    localObject = paramContext.getPackageName();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(".fileprovider");
    paramContext = FileProvider.getUriForFile(paramContext, localStringBuilder.toString(), paramString);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Shared file: ");
    ((StringBuilder)localObject).append(paramString.toString());
    ((StringBuilder)localObject).append(" => ");
    ((StringBuilder)localObject).append(paramContext.toString());
    Log.d("native-sharing", ((StringBuilder)localObject).toString());
    return paramContext;
  }
  
  public static void shareContent(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    Activity localActivity = KsdkCoreActivityHelper.getInstance().getActivity();
    Intent localIntent = new Intent("android.intent.action.SEND");
    if (paramString5 != null) {
      localIntent.putExtra("android.intent.extra.SUBJECT", paramString5);
    }
    if (paramString3 != null) {
      localIntent.putExtra("android.intent.extra.TEXT", paramString3);
    }
    if (paramString2 != null)
    {
      if (!new File(paramString2).canRead())
      {
        paramString1 = new StringBuilder();
        paramString1.append("input file not readable: ");
        paramString1.append(paramString2);
        Log.e("native-sharing", paramString1.toString());
        return;
      }
      try
      {
        paramString2 = getFileUri(paramString2, localActivity);
        paramString3 = localActivity.getPackageManager().getInstalledApplications(128).iterator();
        while (paramString3.hasNext()) {
          localActivity.grantUriPermission(((ApplicationInfo)paramString3.next()).packageName, paramString2, 3);
        }
        localIntent.putExtra("android.intent.extra.STREAM", paramString2);
        localIntent.addFlags(1);
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        paramString2 = new StringBuilder();
        paramString2.append("exception: ");
        paramString2.append(paramString1.getMessage());
        Log.e("native-sharing", paramString2.toString());
        return;
      }
    }
    localIntent.setType(paramString1);
    if (Build.VERSION.SDK_INT <= 19)
    {
      localActivity.startActivity(localIntent);
      return;
    }
    paramString1 = paramString4;
    if (paramString4 == null) {
      paramString1 = "";
    }
    localActivity.startActivity(Intent.createChooser(localIntent, paramString1));
  }
  
  public static void shareImage(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (paramString1 == null)
    {
      Log.e("native-sharing", "image file is null");
      return;
    }
    shareContent("image/*", paramString1, paramString2, paramString3, paramString4);
  }
  
  public static void shareText(String paramString1, String paramString2, String paramString3)
  {
    shareContent("text/plain", null, paramString1, paramString2, paramString3);
  }
}
