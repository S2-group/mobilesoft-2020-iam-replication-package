package com.clearchannel.iheartradio.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import com.clearchannel.iheartradio.ApplicationManager;
import com.clearchannel.iheartradio.UserDataManager;
import com.clearchannel.iheartradio.commons.R.string;
import com.clearchannel.iheartradio.utils.lang.StringUtils;
import com.iheartradio.functional.Maybe;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUtils
{
  private static final String[] emailFailurePatterns = { ".*\\.@.*", ".*\\.\\..*" };
  private static final Pattern emailPattern = Pattern.compile("^[-a-zA-Z0-9!#$%&'*+/=?^_`{|}~][-\\.a-zA-Z0-9!#$%&'*+/=?^_`{|}~]*@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+$");
  private static final String emailPatternText = "^[-a-zA-Z0-9!#$%&'*+/=?^_`{|}~][-\\.a-zA-Z0-9!#$%&'*+/=?^_`{|}~]*@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+$";
  
  public ValidUtils() {}
  
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
  
  public static boolean activityIsUsable(Activity paramActivity)
  {
    return (paramActivity != null) && (!paramActivity.isFinishing());
  }
  
  public static boolean activityIsUsable(Maybe<? extends Activity> paramMaybe)
  {
    return (paramMaybe.isDefined()) && (!((Activity)paramMaybe.get()).isFinishing());
  }
  
  public static CheckResult checkEmail(Resources paramResources, String paramString)
  {
    return checkEmail(paramResources, paramString, R.string.error_empty_fields_params);
  }
  
  public static CheckResult checkEmail(Resources paramResources, String paramString, int paramInt)
  {
    boolean bool = true;
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
          str2 = String.format(paramResources.getString(R.string.error_empty_field_format), new Object[] { paramResources.getString(R.string.error_invalid_email) });
          i = R.string.dialog_name_error_invalid_email;
        }
      }
      if (str2 != null) {
        break label106;
      }
    }
    for (;;)
    {
      return new CheckResult(i, str2, bool);
      paramInt = R.string.dialog_name_error_empty_field;
      break;
      label106:
      bool = false;
    }
  }
  
  public static CheckResult checkEmailConfirmation(Resources paramResources, String paramString1, String paramString2)
  {
    boolean bool = false;
    String str1 = null;
    if ((StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2))) {
      str1 = paramResources.getString(R.string.error_empty_fields_params);
    }
    if (str1 == null) {}
    for (int i = 0;; i = R.string.dialog_name_error_empty_field)
    {
      int j = i;
      String str2 = str1;
      if (str1 == null)
      {
        j = i;
        str2 = str1;
        if (!paramString1.equals(paramString2))
        {
          str2 = paramResources.getString(R.string.email_not_match);
          j = R.string.dialog_email_confirmation_title;
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
    for (paramInt = 0;; paramInt = R.string.dialog_name_error_empty_field)
    {
      if (str == null) {
        bool = true;
      }
      return new CheckResult(paramInt, str, bool);
    }
  }
  
  public static CheckResult checkFacebookEmail(Resources paramResources, String paramString)
  {
    if (!ApplicationManager.instance().user().loggedInWithFacebook()) {
      return new CheckResult();
    }
    return checkSocialAccountEmail(String.format(paramResources.getString(R.string.dialog_account_emails_dont_match), new Object[] { paramResources.getString(R.string.facebook) }), paramString);
  }
  
  public static CheckResult checkGoogleEmail(Resources paramResources, String paramString)
  {
    if (!ApplicationManager.instance().user().loggedInWithGooglePlus()) {
      return new CheckResult();
    }
    return checkSocialAccountEmail(String.format(paramResources.getString(R.string.dialog_account_emails_dont_match), new Object[] { paramResources.getString(R.string.google_plus) }), paramString);
  }
  
  public static CheckResult checkIfMatch(Resources paramResources, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool = false;
    String str1 = null;
    if ((StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2))) {
      str1 = paramResources.getString(paramInt3);
    }
    if (str1 == null) {}
    for (paramInt3 = 0;; paramInt3 = R.string.dialog_name_error_empty_field)
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
    boolean bool = false;
    Object localObject2 = null;
    if (StringUtils.isEmpty(paramString)) {
      localObject2 = paramResources.getString(R.string.error_password_empty);
    }
    if (localObject2 == null) {}
    for (int j = 0;; j = R.string.dialog_name_error_empty_field)
    {
      int i = j;
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        i = j;
        localObject1 = localObject2;
        if (!isPasswordValidLength(paramString))
        {
          localObject1 = paramResources.getString(R.string.error_password_does_not_conform);
          i = R.string.dialog_name_error_password_does_not_conform;
        }
      }
      j = i;
      localObject2 = localObject1;
      if (localObject1 == null)
      {
        j = i;
        localObject2 = localObject1;
        if (!isPasswordValidChars(paramString))
        {
          localObject2 = paramResources.getString(R.string.dialog_name_error_password_contain_special_character);
          j = R.string.dialog_name_error_invalid_password;
        }
      }
      if (localObject2 == null) {
        bool = true;
      }
      return new CheckResult(j, (String)localObject2, bool);
    }
  }
  
  public static CheckResult checkSocialAccountEmail(String paramString1, String paramString2)
  {
    String str = null;
    int i = 0;
    if (!emailSameAsCurrent(paramString2))
    {
      i = R.string.login;
      str = paramString1;
    }
    if (str == null) {}
    for (boolean bool = true;; bool = false) {
      return new CheckResult(i, str, bool);
    }
  }
  
  public static String checkTextFieldOnEmpty(Resources paramResources, String paramString, int paramInt)
  {
    String str = null;
    if (StringUtils.isEmpty(paramString)) {
      str = getErrorMessage(paramResources, R.string.error_empty_field_format, paramInt);
    }
    return str;
  }
  
  public static CheckResult checkZipCode(Resources paramResources, String paramString)
  {
    boolean bool = false;
    String str1 = null;
    if (StringUtils.isEmpty(paramString)) {
      str1 = paramResources.getString(R.string.error_zipcode_empty);
    }
    if (str1 == null) {}
    for (int i = 0;; i = R.string.dialog_name_error_empty_field)
    {
      int j = i;
      String str2 = str1;
      if (str1 == null)
      {
        j = i;
        str2 = str1;
        if (!isZipCodeValid(paramString))
        {
          str2 = paramResources.getString(R.string.error_zipcode_lenght);
          j = R.string.zipcode;
        }
      }
      if (str2 == null) {
        bool = true;
      }
      return new CheckResult(j, str2, bool);
    }
  }
  
  public static boolean emailOk(CharSequence paramCharSequence)
  {
    if (!emailPattern.matcher(paramCharSequence).find()) {
      return false;
    }
    String[] arrayOfString = emailFailurePatterns;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label46;
      }
      if (Pattern.matches(arrayOfString[i], paramCharSequence)) {
        break;
      }
      i += 1;
    }
    label46:
    return true;
  }
  
  public static boolean emailSameAsCurrent(String paramString)
  {
    UserDataManager localUserDataManager = ApplicationManager.instance().user();
    return (!localUserDataManager.isLoggedIn()) || (equalEmails(localUserDataManager.getEmail(), paramString));
  }
  
  public static boolean equalEmails(String paramString1, String paramString2)
  {
    return (!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2)) && (paramString1.equalsIgnoreCase(paramString2));
  }
  
  public static String getErrorMessage(Resources paramResources, int paramInt1, int paramInt2)
  {
    return String.format(paramResources.getString(paramInt1), new Object[] { paramResources.getString(paramInt2) });
  }
  
  public static boolean isBirthYearValid(String paramString)
  {
    try
    {
      int i = Calendar.getInstance().get(1);
      int j = Integer.parseInt(paramString);
      return i - j >= 14;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static boolean isPasswordValidChars(String paramString)
  {
    paramString = paramString.toCharArray();
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      int k = paramString[i];
      if ((k < 33) || (k > 126)) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static boolean isPasswordValidLength(String paramString)
  {
    return (paramString.length() >= 4) && (paramString.length() <= 33);
  }
  
  public static boolean isZipCodeValid(String paramString)
  {
    return (paramString != null) && (paramString.length() == 5);
  }
}
