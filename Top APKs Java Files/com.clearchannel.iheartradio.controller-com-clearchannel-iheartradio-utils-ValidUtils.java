package com.clearchannel.iheartradio.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import com.clearchannel.iheartradio.ApplicationManager;
import com.clearchannel.iheartradio.UserDataManager;
import com.clearchannel.iheartradio.utils.lang.StringUtils;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUtils
{
  public static final String AMZOM_MP3 = "com.amazon.mp3";
  private static final String[] emailFailurePatterns = { ".*\\.@.*", ".*\\.\\..*" };
  private static Pattern emailPattern = Pattern.compile("^[-a-zA-Z0-9!#$%&'*+/=?^_`{|}~][-\\.a-zA-Z0-9!#$%&'*+/=?^_`{|}~]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+$");
  private static final String emailPatternText = "^[-a-zA-Z0-9!#$%&'*+/=?^_`{|}~][-\\.a-zA-Z0-9!#$%&'*+/=?^_`{|}~]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+$";
  
  public ValidUtils() {}
  
  public static boolean AmazonMP3Installed(Context paramContext)
  {
    return PakcageInstalled(paramContext, "com.amazon.mp3");
  }
  
  public static boolean PakcageInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    boolean bool2 = false;
    int i = 0;
    boolean bool1 = bool2;
    if (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if (localPackageInfo.versionName == null) {}
      while (!localPackageInfo.packageName.startsWith(paramString))
      {
        i += 1;
        break;
      }
      bool1 = true;
    }
    return bool1;
  }
  
  public static CheckResult checkEmail(Resources paramResources, String paramString)
  {
    return checkEmail(paramResources, paramString, 2131296336);
  }
  
  public static CheckResult checkEmail(Resources paramResources, String paramString, int paramInt)
  {
    String str1 = null;
    if (StringUtils.isEmpty(paramString)) {
      str1 = paramResources.getString(paramInt);
    }
    int i;
    String str2;
    if (str1 == null)
    {
      paramInt = 0;
      i = paramInt;
      str2 = str1;
      if (str1 == null)
      {
        i = paramInt;
        str2 = str1;
        if (!emailOk(paramString))
        {
          str2 = String.format(paramResources.getString(2131296332), new Object[] { paramResources.getString(2131296335) });
          i = 2131296320;
        }
      }
      if (str2 != null) {
        break label102;
      }
    }
    label102:
    for (boolean bool = true;; bool = false)
    {
      return new CheckResult(i, str2, bool);
      paramInt = 2131296319;
      break;
    }
  }
  
  public static CheckResult checkEmailConfirmation(Resources paramResources, String paramString1, String paramString2)
  {
    boolean bool = false;
    String str1 = null;
    if ((StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2))) {
      str1 = paramResources.getString(2131296336);
    }
    if (str1 == null) {}
    for (int i = 0;; i = 2131296319)
    {
      int j = i;
      String str2 = str1;
      if (str1 == null)
      {
        j = i;
        str2 = str1;
        if (!paramString1.equals(paramString2))
        {
          str2 = paramResources.getString(2131296829);
          j = 2131296831;
        }
      }
      if (str2 == null) {
        bool = true;
      }
      return new CheckResult(j, str2, bool);
    }
  }
  
  public static CheckResult checkEmptyField(Resources paramResources, String paramString, int paramInt)
  {
    boolean bool = false;
    String str = null;
    if (StringUtils.isEmpty(paramString)) {
      str = paramResources.getString(paramInt);
    }
    if (str == null) {}
    for (paramInt = 0;; paramInt = 2131296319)
    {
      if (str == null) {
        bool = true;
      }
      return new CheckResult(paramInt, str, bool);
    }
  }
  
  public static CheckResult checkFacebookEmail(Resources paramResources, String paramString)
  {
    boolean bool = false;
    Object localObject2 = null;
    Object localObject3 = ApplicationManager.instance().user();
    if (0 == 0) {}
    for (int i = 0;; i = 2131296319)
    {
      int j = i;
      Object localObject1 = localObject2;
      if (((UserDataManager)localObject3).hasFacebookLogin())
      {
        localObject3 = ((UserDataManager)localObject3).getEmail();
        j = i;
        localObject1 = localObject2;
        if (0 == 0)
        {
          j = i;
          localObject1 = localObject2;
          if (!paramString.equals(localObject3))
          {
            localObject1 = paramResources.getString(2131296890);
            j = 2131296825;
          }
        }
      }
      if (localObject1 == null) {
        bool = true;
      }
      return new CheckResult(j, (String)localObject1, bool);
    }
  }
  
  public static CheckResult checkIfMatch(Resources paramResources, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool = false;
    String str1 = null;
    if ((StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2))) {
      str1 = paramResources.getString(paramInt3);
    }
    if (str1 == null) {}
    for (paramInt3 = 0;; paramInt3 = 2131296319)
    {
      int i = paramInt3;
      String str2 = str1;
      if (str1 == null)
      {
        i = paramInt3;
        str2 = str1;
        if (!paramString1.equals(paramString2))
        {
          str2 = paramResources.getString(paramInt2);
          i = paramInt1;
        }
      }
      if (str2 == null) {
        bool = true;
      }
      return new CheckResult(i, str2, bool);
    }
  }
  
  public static CheckResult checkPassword(Resources paramResources, String paramString)
  {
    Object localObject2 = null;
    if (StringUtils.isEmpty(paramString)) {
      localObject2 = paramResources.getString(2131296339);
    }
    if (localObject2 == null) {}
    int k;
    for (int j = 0;; j = 2131296319)
    {
      int i = j;
      Object localObject1 = localObject2;
      if (localObject2 == null) {
        if (paramString.length() >= 4)
        {
          i = j;
          localObject1 = localObject2;
          if (paramString.length() <= 33) {}
        }
        else
        {
          localObject1 = paramResources.getString(2131296340);
          i = 2131296324;
        }
      }
      k = i;
      localObject2 = localObject1;
      if (localObject1 != null) {
        break;
      }
      char[] arrayOfChar = paramString.toCharArray();
      int m = arrayOfChar.length;
      j = 0;
      for (;;)
      {
        k = i;
        localObject2 = localObject1;
        if (j >= m) {
          break;
        }
        int n = arrayOfChar[j];
        if (n >= 97)
        {
          k = i;
          paramString = (String)localObject1;
          if (n <= 122) {}
        }
        else if (n >= 65)
        {
          k = i;
          paramString = (String)localObject1;
          if (n <= 90) {}
        }
        else if (n >= 48)
        {
          k = i;
          paramString = (String)localObject1;
          if (n <= 57) {}
        }
        else
        {
          paramString = paramResources.getString(2131296325);
          k = 2131296321;
        }
        j += 1;
        i = k;
        localObject1 = paramString;
      }
    }
    if (localObject2 == null) {}
    for (boolean bool = true;; bool = false) {
      return new CheckResult(k, (String)localObject2, bool);
    }
  }
  
  public static String checkTextFieldOnEmpty(Resources paramResources, String paramString, int paramInt)
  {
    String str = null;
    if (StringUtils.isEmpty(paramString)) {
      str = getErrorMessage(paramResources, 2131296332, paramInt);
    }
    return str;
  }
  
  public static CheckResult checkYearBirth(Resources paramResources, String paramString)
  {
    boolean bool = false;
    String str1 = null;
    if (StringUtils.isEmpty(paramString)) {
      str1 = paramResources.getString(2131296906);
    }
    if (str1 == null) {}
    for (int i = 0;; i = 2131296319)
    {
      int j = i;
      String str2 = str1;
      if (str1 == null)
      {
        j = i;
        str2 = str1;
        if (Calendar.getInstance().get(1) - Integer.parseInt(paramString) < 14)
        {
          str2 = paramResources.getString(2131296908);
          j = 2131296909;
        }
      }
      if (str2 == null) {
        bool = true;
      }
      return new CheckResult(j, str2, bool);
    }
  }
  
  public static CheckResult checkZipCode(Resources paramResources, String paramString)
  {
    boolean bool = false;
    String str1 = null;
    if (StringUtils.isEmpty(paramString)) {
      str1 = paramResources.getString(2131296907);
    }
    if (str1 == null) {}
    for (int i = 0;; i = 2131296319)
    {
      int j = i;
      String str2 = str1;
      if (str1 == null)
      {
        j = i;
        str2 = str1;
        if (paramString.length() != 5)
        {
          str2 = paramResources.getString(2131296925);
          j = 2131296917;
        }
      }
      if (str2 == null) {
        bool = true;
      }
      return new CheckResult(j, str2, bool);
    }
  }
  
  public static boolean emailOk(String paramString)
  {
    if (!emailPattern.matcher(paramString).find()) {
      return false;
    }
    String[] arrayOfString = emailFailurePatterns;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (Pattern.matches(arrayOfString[i], paramString)) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static String getErrorMessage(Resources paramResources, int paramInt1, int paramInt2)
  {
    return String.format(paramResources.getString(paramInt1), new Object[] { paramResources.getString(paramInt2) });
  }
}
