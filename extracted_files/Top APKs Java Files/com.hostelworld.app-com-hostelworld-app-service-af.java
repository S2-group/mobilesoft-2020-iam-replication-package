package com.hostelworld.app.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v4.content.b;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import com.hostelworld.app.Application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class af
{
  public static int a(Context paramContext, String paramString)
  {
    int i = b(String.format("icn_facilities_%s", new Object[] { paramString.toLowerCase(Locale.ENGLISH) }), paramContext);
    if (i > 0) {
      return i;
    }
    return 2131231263;
  }
  
  public static CharSequence a(Date paramDate1, Date paramDate2, int paramInt)
  {
    paramDate1 = m.a(Application.a(), paramDate1);
    paramDate2 = m.a(Application.a(), paramDate2);
    Locale localLocale = Locale.getDefault();
    return new aq().a(String.format(localLocale, "%s - %s     ", new Object[] { paramDate1, paramDate2 })).a(new ImageSpan(Application.a(), 2131231200, 1)).a(" ").a().a(new RelativeSizeSpan(1.2F)).a(String.format(localLocale, " %d", new Object[] { Integer.valueOf(paramInt) })).a().b();
  }
  
  public static String a(Resources paramResources, String paramString)
  {
    try
    {
      paramResources = paramResources.getAssets().open(paramString);
      String str = a(paramResources);
      paramResources.close();
      return str;
    }
    catch (IOException paramResources)
    {
      for (;;) {}
    }
    paramResources = new StringBuilder();
    paramResources.append("Unable to load asset ");
    paramResources.append(paramString);
    Log.e("ResourceService", paramResources.toString());
    return "";
  }
  
  private static String a(InputStream paramInputStream)
    throws IOException
  {
    paramInputStream = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      String str = paramInputStream.readLine();
      if (str == null) {
        break;
      }
      localStringBuilder.append(str);
      localStringBuilder.append("\n");
    }
    paramInputStream.close();
    return localStringBuilder.toString();
  }
  
  public static String a(String paramString, Context paramContext)
  {
    try
    {
      paramString = paramContext.getString(paramContext.getResources().getIdentifier(paramString, "string", paramContext.getApplicationInfo().packageName));
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    Log.e("ResourceService", "Resource not found in the strings.xml file");
    return "";
  }
  
  @SuppressLint({"NewApi"})
  public static void a(View paramView, int paramInt)
  {
    int i = paramView.getPaddingLeft();
    int j = paramView.getPaddingRight();
    int k = paramView.getPaddingTop();
    int m = paramView.getPaddingBottom();
    paramView.setBackground(b.a(paramView.getContext(), paramInt));
    paramView.setPadding(i, k, j, m);
  }
  
  public static int b(String paramString, Context paramContext)
  {
    try
    {
      int i = paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getApplicationInfo().packageName);
      return i;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    Log.e("ResourceService", "File not found in the drawable folders");
    return 0;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
}
