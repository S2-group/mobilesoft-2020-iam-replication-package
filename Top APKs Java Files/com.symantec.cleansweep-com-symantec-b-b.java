package com.symantec.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class b
  extends a
{
  private final Context b;
  private final Deque<String> c;
  
  b(Context paramContext, Deque<String> paramDeque, j paramJ)
  {
    super(paramJ);
    this.b = paramContext;
    this.c = paramDeque;
  }
  
  static boolean a(String paramString)
  {
    if ((paramString.startsWith("apk:")) && (!"apk:".equals(paramString.trim()))) {}
    while ((paramString.startsWith("apkr:")) && (!"apkr:".equals(paramString.trim()))) {
      return true;
    }
    return false;
  }
  
  private void b(String paramString)
    throws c
  {
    Object localObject = this.b.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getApplicationInfo(paramString.substring("apk:".length()), 0);
      this.a.a(((ApplicationInfo)localObject).sourceDir);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      this.a.b(paramString);
    }
  }
  
  private void c(String paramString)
    throws c
  {
    try
    {
      Pattern localPattern = Pattern.compile(paramString.substring("apkr:".length()));
      Iterator localIterator = this.b.getPackageManager().getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localPattern.matcher(localApplicationInfo.packageName).matches()) {
          this.a.a(localApplicationInfo.sourceDir);
        }
      }
      return;
    }
    catch (PatternSyntaxException localPatternSyntaxException)
    {
      this.a.b(paramString);
    }
  }
  
  protected void a()
    throws c
  {
    while (!this.c.isEmpty())
    {
      String str = (String)this.c.poll();
      if (str.startsWith("apk:")) {
        b(str);
      } else {
        c(str);
      }
    }
  }
}
