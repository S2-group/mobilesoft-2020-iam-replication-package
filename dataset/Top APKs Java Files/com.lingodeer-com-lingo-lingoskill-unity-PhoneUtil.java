package com.lingo.lingoskill.unity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Process;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.lingo.lingoskill.LingoSkillApplication;
import com.lingo.lingoskill.db.i;
import com.lingo.lingoskill.db.i.a;
import com.lingo.lingoskill.db.l;
import com.lingo.lingoskill.db.l.a;
import com.lingo.lingoskill.db.p;
import com.lingo.lingoskill.object.LanCustomInfoDao;
import com.lingo.lingoskill.object.LanCustomInfoDao.Properties;
import com.lingo.lingoskill.object.LanguageItem;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.TypeCastException;
import kotlin.a.g;
import kotlin.a.s;
import kotlin.h.f;
import org.greenrobot.greendao.d.j;

public final class PhoneUtil
{
  public static final PhoneUtil INSTANCE = new PhoneUtil();
  private static final int[] keyLanArray = { 0, 1, 2, 3, 4, 5, 6, 8, 7, 11, 12, 13, 14, 15, 16, 17 };
  
  private PhoneUtil() {}
  
  private final void clearLearnProgress(int paramInt)
  {
    Object localObject1 = l.b;
    localObject1 = l.a.a();
    Object localObject2 = ((l)localObject1).a.f().queryBuilder();
    org.greenrobot.greendao.e localE = LanCustomInfoDao.Properties.Lan;
    long l = paramInt;
    localObject2 = ((org.greenrobot.greendao.d.h)localObject2).a(localE.a(Long.valueOf(l)), new j[0]).b().b();
    while (((Cursor)localObject2).moveToNext()) {
      ((l)localObject1).a.f().deleteByKey(Long.valueOf(l));
    }
  }
  
  public final void addNewUnitToSRS(long paramLong)
  {
    Object localObject = i.b;
    if ((i.a.a().b(-1, (int)paramLong).isEmpty()) && ((kotlin.d.b.h.a(EnvHelper.INSTANCE.getFlashCardFocusUnit(), "-1") ^ true)))
    {
      localObject = EnvHelper.INSTANCE;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(EnvHelper.INSTANCE.getFlashCardFocusUnit());
      localStringBuilder.append(';');
      localStringBuilder.append(paramLong);
      ((EnvHelper)localObject).setFlashCardFocusUnit(localStringBuilder.toString());
    }
  }
  
  public final boolean checkIsCopy(String paramString, Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 11)
    {
      paramContext = paramContext.getSystemService("clipboard");
      if (paramContext != null)
      {
        paramContext = (android.text.ClipboardManager)paramContext;
        paramContext.setText((CharSequence)paramString);
        return kotlin.d.b.h.a(paramContext.getText().toString(), paramString);
      }
      throw new TypeCastException("null cannot be cast to non-null type android.text.ClipboardManager");
    }
    paramContext = paramContext.getSystemService("clipboard");
    if (paramContext != null)
    {
      ClipData localClipData = ((android.content.ClipboardManager)paramContext).getPrimaryClip();
      if (localClipData != null) {
        paramContext = localClipData.getItemAt(0);
      } else {
        paramContext = null;
      }
      if (paramContext != null)
      {
        paramContext = localClipData.getItemAt(0);
        kotlin.d.b.h.a(paramContext, "item");
        return kotlin.d.b.h.a(paramContext.getText(), paramString);
      }
      return false;
    }
    throw new TypeCastException("null cannot be cast to non-null type android.content.ClipboardManager");
  }
  
  public final boolean checkNetworkStatus(Context paramContext)
  {
    paramContext = paramContext.getSystemService("connectivity");
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext).getActiveNetworkInfo();
      return (paramContext != null) && (paramContext.isConnected());
    }
    throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
  }
  
  public final boolean checkSampleRate(int paramInt)
  {
    paramInt = AudioRecord.getMinBufferSize(paramInt, 16, 2);
    return (paramInt != -1) && (paramInt != -2) && (paramInt > 0);
  }
  
  public final boolean checkWifiConnected(Context paramContext)
  {
    paramContext = paramContext.getSystemService("connectivity");
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext).getActiveNetworkInfo();
      int i;
      if ((paramContext != null) && (paramContext.isConnectedOrConnecting())) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0)
      {
        if (paramContext == null) {
          kotlin.d.b.h.a();
        }
        return paramContext.getType() == 1;
      }
      return false;
    }
    throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
  }
  
  public final void clearLearnProgress()
  {
    clearLearnProgress(0);
    clearLearnProgress(1);
    clearLearnProgress(2);
    clearLearnProgress(3);
    clearLearnProgress(4);
    clearLearnProgress(5);
    clearLearnProgress(6);
    clearLearnProgress(8);
    clearLearnProgress(7);
    clearLearnProgress(11);
    clearLearnProgress(13);
    clearLearnProgress(12);
    clearLearnProgress(14);
    clearLearnProgress(15);
    clearLearnProgress(16);
    clearLearnProgress(17);
    clearLearnProgress(10);
  }
  
  public final void clearUserInfo(Env paramEnv)
  {
    paramEnv.accountType = "unlogin_user";
    paramEnv.uid = null;
    paramEnv.prevLoginAccount = paramEnv.loginAccount;
    paramEnv.prevAccountType = paramEnv.accountType;
    paramEnv.loginAccount = null;
    paramEnv.nickName = null;
    paramEnv.userPicName = null;
    paramEnv.progressSuccessSync = false;
    paramEnv.srsSuccessSync = false;
    paramEnv.fbToken = null;
    paramEnv.fbDbToken = null;
    paramEnv.preContinueDays = null;
    paramEnv.preLearnedTime = 0;
    paramEnv.preLearnedXp = 0;
    paramEnv.weekRank = 0;
    paramEnv.regin = null;
    paramEnv.age = -1;
    paramEnv.hasSyncSubInfo = false;
    paramEnv.buyCoffee = null;
    paramEnv.appVersion = null;
    paramEnv.hasGetIsOldUser = false;
    paramEnv.isOldUser = false;
    paramEnv.alarmDiscountTime = 0L;
    paramEnv.freeTimeRemove = 0L;
    paramEnv.freeTimeAdd = 0L;
    paramEnv.updateEntries(new String[] { "accountType", "uid", "prevLoginAccount", "prevAccountType", "loginAccount", "nickName", "userPicName", "progressSuccessSync", "srsSuccessSync", "fbToken", "fbDbToken", "preContinueDays", "preLearnedTime", "preLearnedXp", "weekRank", "regin", "age", "hasSyncSubInfo", "buyCoffee", "appVersion", "hasGetIsOldUser", "isOldUser", "alarmDiscountTime", "freeTimeRemove", "freeTimeAdd" });
  }
  
  public final void copy(String paramString, Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 11)
    {
      paramContext = paramContext.getSystemService("clipboard");
      if (paramContext != null)
      {
        ((android.text.ClipboardManager)paramContext).setText((CharSequence)paramString);
        return;
      }
      throw new TypeCastException("null cannot be cast to non-null type android.text.ClipboardManager");
    }
    paramContext = paramContext.getSystemService("clipboard");
    if (paramContext != null)
    {
      paramContext = (android.content.ClipboardManager)paramContext;
      paramString = (CharSequence)paramString;
      paramContext.setPrimaryClip(ClipData.newPlainText(paramString, paramString));
      return;
    }
    throw new TypeCastException("null cannot be cast to non-null type android.content.ClipboardManager");
  }
  
  public final String filterUnNumber(String paramString)
  {
    paramString = Pattern.compile("[^0-9.]").matcher((CharSequence)paramString).replaceAll("");
    kotlin.d.b.h.a(paramString, "m.replaceAll(\"\")");
    paramString = (CharSequence)paramString;
    int i = paramString.length() - 1;
    int j = 0;
    int k = 0;
    while (j <= i)
    {
      int m;
      if (k == 0) {
        m = j;
      } else {
        m = i;
      }
      if (paramString.charAt(m) <= ' ') {
        m = 1;
      } else {
        m = 0;
      }
      if (k == 0)
      {
        if (m == 0) {
          k = 1;
        } else {
          j += 1;
        }
      }
      else
      {
        if (m == 0) {
          break;
        }
        i -= 1;
      }
    }
    return paramString.subSequence(j, i + 1).toString();
  }
  
  public final Pair<String, Integer> getAppVersion()
  {
    try
    {
      localObject1 = LingoSkillApplication.c();
      kotlin.d.b.h.a(localObject1, "LingoSkillApplication.getContext()");
      localObject1 = ((Context)localObject1).getPackageManager();
      Object localObject2 = LingoSkillApplication.c();
      kotlin.d.b.h.a(localObject2, "LingoSkillApplication.getContext()");
      localPackageInfo = ((PackageManager)localObject1).getPackageInfo(((Context)localObject2).getPackageName(), 0);
      localObject2 = localPackageInfo.versionName;
      if (localObject2 == null) {
        break label98;
      }
      localObject1 = localObject2;
      if (!kotlin.d.b.h.a(localObject2, "")) {}
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject1;
        PackageInfo localPackageInfo;
        continue;
        label98:
        String str = "";
      }
    }
    localObject1 = new Pair(localObject1, Integer.valueOf(localPackageInfo.versionCode));
    return localObject1;
    return new Pair("", Integer.valueOf(-1));
  }
  
  public final int getAppVersionCode()
  {
    try
    {
      Object localObject = LingoSkillApplication.c();
      kotlin.d.b.h.a(localObject, "LingoSkillApplication.getContext()");
      localObject = ((Context)localObject).getPackageManager();
      Context localContext = LingoSkillApplication.c();
      kotlin.d.b.h.a(localContext, "LingoSkillApplication.getContext()");
      int i = ((PackageManager)localObject).getPackageInfo(localContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return -1;
  }
  
  public final String getAppVersionName()
  {
    try
    {
      Object localObject1 = LingoSkillApplication.c();
      kotlin.d.b.h.a(localObject1, "LingoSkillApplication.getContext()");
      localObject1 = ((Context)localObject1).getPackageManager();
      Object localObject2 = LingoSkillApplication.c();
      kotlin.d.b.h.a(localObject2, "LingoSkillApplication.getContext()");
      localObject2 = ((PackageManager)localObject1).getPackageInfo(((Context)localObject2).getPackageName(), 0).versionName;
      kotlin.d.b.h.a(localObject2, "pi.versionName");
      if (localObject2 != null) {
        localObject1 = localObject2;
      }
      return localObject2;
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        try
        {
          if (kotlin.d.b.h.a(localObject2, ""))
          {
            return "";
            localObject1 = "";
          }
          return localObject1;
        }
        catch (Exception localException2) {}
        localException1 = localException1;
      }
    }
  }
  
  public final int getAssertDbVersion(String paramString)
  {
    paramString = Pattern.compile(".+?_([0-9]+?)\\.db").matcher((CharSequence)paramString);
    if (paramString.matches()) {
      return Integer.parseInt(paramString.group(1));
    }
    return -1;
  }
  
  public final int getAssertZVersion(String paramString)
  {
    paramString = Pattern.compile(".+?_([0-9]+?)\\.z").matcher((CharSequence)paramString);
    if (paramString.matches()) {
      return Integer.parseInt(paramString.group(1));
    }
    return -1;
  }
  
  public final String getFirebaseKeyLanguageCode(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 20: 
      return "it";
    case 19: 
      return "pol";
    case 18: 
      return "idn";
    case 17: 
      return "ptup";
    case 16: 
      return "deocup";
    case 15: 
      return "frocup";
    case 14: 
      return "esocup";
    case 13: 
      return "krup";
    case 12: 
      return "jpup";
    case 11: 
      return "cnup";
    case 10: 
      return "ru";
    case 9: 
      return "tch";
    case 8: 
      return "pt";
    case 7: 
      return "vt";
    case 6: 
      return "deoc";
    case 5: 
      return "froc";
    case 4: 
      return "esoc";
    case 3: 
      return "en";
    case 2: 
      return "kr";
    case 1: 
      return "jp";
    }
    return "cn";
  }
  
  public final boolean getHasAck()
  {
    return (LingoSkillApplication.a().locateLanguage == 3) && (LingoSkillApplication.a().keyLanguage != 11) && (LingoSkillApplication.a().keyLanguage != 0) && (LingoSkillApplication.a().keyLanguage != 3) && (LingoSkillApplication.a().keyLanguage != 7) && (LingoSkillApplication.a().keyLanguage != 12) && (LingoSkillApplication.a().keyLanguage != 13);
  }
  
  public final boolean getHasAlphabet()
  {
    if ((LingoSkillApplication.a().keyLanguage != 0) && (LingoSkillApplication.a().keyLanguage != 1) && (LingoSkillApplication.a().keyLanguage != 2) && (LingoSkillApplication.a().keyLanguage != 7) && (LingoSkillApplication.a().keyLanguage != 11) && (LingoSkillApplication.a().keyLanguage != 12))
    {
      if (LingoSkillApplication.a().keyLanguage == 13) {
        return true;
      }
      return ((LingoSkillApplication.a().keyLanguage == 4) || (LingoSkillApplication.a().keyLanguage == 5) || (LingoSkillApplication.a().keyLanguage == 6) || (LingoSkillApplication.a().keyLanguage == 14) || (LingoSkillApplication.a().keyLanguage == 15) || (LingoSkillApplication.a().keyLanguage == 16)) && (LingoSkillApplication.a().locateLanguage == 3);
    }
    return true;
  }
  
  public final int[] getKeyLanArray()
  {
    return keyLanArray;
  }
  
  public final String getKeyLanguageCode(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 20: 
      return "it";
    case 19: 
      return "pol";
    case 18: 
      return "idn";
    case 17: 
      return "ptup";
    case 16: 
      return "deocup";
    case 15: 
      return "frocup";
    case 14: 
      return "esocup";
    case 13: 
      return "krup";
    case 12: 
      return "jpup";
    case 11: 
      return "cnup";
    case 10: 
      return "ru";
    case 9: 
      return "tch";
    case 8: 
      return "pt";
    case 7: 
      return "vt";
    case 6: 
      return "de";
    case 5: 
      return "fr";
    case 4: 
      return "es";
    case 3: 
      return "en";
    case 2: 
      return "kr";
    case 1: 
      return "jp";
    }
    return "cn";
  }
  
  public final String getKeyLanguageName(int paramInt)
  {
    com.lingo.lingoskill.base.c.e localE;
    switch (paramInt)
    {
    default: 
      return "";
    case 17: 
      localObject = new StringBuilder();
      localE = com.lingo.lingoskill.base.c.e.a;
      ((StringBuilder)localObject).append(com.lingo.lingoskill.base.c.e.b(2131756021));
      ((StringBuilder)localObject).append(" 2");
      return ((StringBuilder)localObject).toString();
    case 16: 
      localObject = new StringBuilder();
      localE = com.lingo.lingoskill.base.c.e.a;
      ((StringBuilder)localObject).append(com.lingo.lingoskill.base.c.e.b(2131755788));
      ((StringBuilder)localObject).append(" 2");
      return ((StringBuilder)localObject).toString();
    case 15: 
      localObject = new StringBuilder();
      localE = com.lingo.lingoskill.base.c.e.a;
      ((StringBuilder)localObject).append(com.lingo.lingoskill.base.c.e.b(2131755781));
      ((StringBuilder)localObject).append(" 2");
      return ((StringBuilder)localObject).toString();
    case 14: 
      localObject = new StringBuilder();
      localE = com.lingo.lingoskill.base.c.e.a;
      ((StringBuilder)localObject).append(com.lingo.lingoskill.base.c.e.b(2131756129));
      ((StringBuilder)localObject).append(" 2");
      return ((StringBuilder)localObject).toString();
    case 13: 
      localObject = new StringBuilder();
      localE = com.lingo.lingoskill.base.c.e.a;
      ((StringBuilder)localObject).append(com.lingo.lingoskill.base.c.e.b(2131755864));
      ((StringBuilder)localObject).append(" 2");
      return ((StringBuilder)localObject).toString();
    case 12: 
      localObject = new StringBuilder();
      localE = com.lingo.lingoskill.base.c.e.a;
      ((StringBuilder)localObject).append(com.lingo.lingoskill.base.c.e.b(2131755847));
      ((StringBuilder)localObject).append(" 2");
      return ((StringBuilder)localObject).toString();
    case 11: 
      localObject = new StringBuilder();
      localE = com.lingo.lingoskill.base.c.e.a;
      ((StringBuilder)localObject).append(com.lingo.lingoskill.base.c.e.b(2131755217));
      ((StringBuilder)localObject).append(" 2");
      return ((StringBuilder)localObject).toString();
    case 10: 
      localObject = com.lingo.lingoskill.base.c.e.a;
      return com.lingo.lingoskill.base.c.e.b(2131756071);
    case 9: 
      localObject = com.lingo.lingoskill.base.c.e.a;
      return com.lingo.lingoskill.base.c.e.b(2131755217);
    case 8: 
      localObject = com.lingo.lingoskill.base.c.e.a;
      return com.lingo.lingoskill.base.c.e.b(2131756021);
    case 7: 
      localObject = com.lingo.lingoskill.base.c.e.a;
      return com.lingo.lingoskill.base.c.e.b(2131756257);
    case 6: 
      localObject = com.lingo.lingoskill.base.c.e.a;
      return com.lingo.lingoskill.base.c.e.b(2131755788);
    case 5: 
      localObject = com.lingo.lingoskill.base.c.e.a;
      return com.lingo.lingoskill.base.c.e.b(2131755781);
    case 4: 
      localObject = com.lingo.lingoskill.base.c.e.a;
      return com.lingo.lingoskill.base.c.e.b(2131756129);
    case 3: 
      localObject = com.lingo.lingoskill.base.c.e.a;
      return com.lingo.lingoskill.base.c.e.b(2131755397);
    case 2: 
      localObject = com.lingo.lingoskill.base.c.e.a;
      return com.lingo.lingoskill.base.c.e.b(2131755864);
    case 1: 
      localObject = com.lingo.lingoskill.base.c.e.a;
      return com.lingo.lingoskill.base.c.e.b(2131755847);
    }
    Object localObject = com.lingo.lingoskill.base.c.e.a;
    return com.lingo.lingoskill.base.c.e.b(2131755217);
  }
  
  public final LanguageItem getLanguageItem(String paramString)
  {
    Object localObject2 = (CharSequence)paramString;
    if (!TextUtils.isEmpty((CharSequence)localObject2))
    {
      paramString = new f(":").a((CharSequence)localObject2);
      Object localObject1;
      int i;
      if (!paramString.isEmpty())
      {
        localObject1 = paramString.listIterator(paramString.size());
        while (((ListIterator)localObject1).hasPrevious())
        {
          if (((CharSequence)((ListIterator)localObject1).previous()).length() == 0) {
            i = 1;
          } else {
            i = 0;
          }
          if (i == 0)
          {
            paramString = g.a((Iterable)paramString, ((ListIterator)localObject1).nextIndex() + 1);
            break label118;
          }
        }
      }
      paramString = (List)s.a;
      label118:
      paramString = (Collection)paramString;
      if (paramString != null)
      {
        paramString = paramString.toArray(new String[0]);
        if (paramString != null)
        {
          if (paramString.length >= 2)
          {
            localObject1 = new LanguageItem();
            paramString = new f(":").a((CharSequence)localObject2);
            if (!paramString.isEmpty())
            {
              localObject2 = paramString.listIterator(paramString.size());
              while (((ListIterator)localObject2).hasPrevious())
              {
                if (((CharSequence)((ListIterator)localObject2).previous()).length() == 0) {
                  i = 1;
                } else {
                  i = 0;
                }
                if (i == 0)
                {
                  paramString = g.a((Iterable)paramString, ((ListIterator)localObject2).nextIndex() + 1);
                  break label267;
                }
              }
            }
            paramString = (List)s.a;
            label267:
            paramString = (Collection)paramString;
            if (paramString != null)
            {
              paramString = paramString.toArray(new String[0]);
              if (paramString != null)
              {
                localObject2 = (String[])paramString;
                paramString = localObject2[0];
                localObject2 = localObject2[1];
                switch (paramString.hashCode())
                {
                }
                for (;;)
                {
                  break;
                  if (paramString.equals("ptup"))
                  {
                    ((LanguageItem)localObject1).setKeyLanguage(17);
                    if (paramString.equals("krup"))
                    {
                      ((LanguageItem)localObject1).setKeyLanguage(13);
                      if (paramString.equals("jpup"))
                      {
                        ((LanguageItem)localObject1).setKeyLanguage(12);
                        if (paramString.equals("cnup"))
                        {
                          ((LanguageItem)localObject1).setKeyLanguage(11);
                          if (paramString.equals("pol"))
                          {
                            ((LanguageItem)localObject1).setKeyLanguage(19);
                            if (paramString.equals("idn"))
                            {
                              ((LanguageItem)localObject1).setKeyLanguage(18);
                              if (paramString.equals("zh"))
                              {
                                ((LanguageItem)localObject1).setKeyLanguage(9);
                                if (paramString.equals("vt"))
                                {
                                  ((LanguageItem)localObject1).setKeyLanguage(7);
                                  if (paramString.equals("ru"))
                                  {
                                    ((LanguageItem)localObject1).setKeyLanguage(10);
                                    if (paramString.equals("pt"))
                                    {
                                      ((LanguageItem)localObject1).setKeyLanguage(8);
                                      if (paramString.equals("kr"))
                                      {
                                        ((LanguageItem)localObject1).setKeyLanguage(2);
                                        if (paramString.equals("jp"))
                                        {
                                          ((LanguageItem)localObject1).setKeyLanguage(1);
                                          if (paramString.equals("it"))
                                          {
                                            ((LanguageItem)localObject1).setKeyLanguage(20);
                                            if (paramString.equals("fr"))
                                            {
                                              ((LanguageItem)localObject1).setKeyLanguage(5);
                                              if (paramString.equals("es"))
                                              {
                                                ((LanguageItem)localObject1).setKeyLanguage(4);
                                                if (paramString.equals("en"))
                                                {
                                                  ((LanguageItem)localObject1).setKeyLanguage(3);
                                                  if (paramString.equals("de"))
                                                  {
                                                    ((LanguageItem)localObject1).setKeyLanguage(6);
                                                    if (paramString.equals("cn"))
                                                    {
                                                      ((LanguageItem)localObject1).setKeyLanguage(0);
                                                      break label893;
                                                      if (paramString.equals("frocup"))
                                                      {
                                                        ((LanguageItem)localObject1).setKeyLanguage(15);
                                                        if (paramString.equals("esocup"))
                                                        {
                                                          ((LanguageItem)localObject1).setKeyLanguage(14);
                                                          if (paramString.equals("deocup"))
                                                          {
                                                            ((LanguageItem)localObject1).setKeyLanguage(16);
                                                            break label893;
                                                          }
                                                        }
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
                ((LanguageItem)localObject1).setKeyLanguage(0);
                label893:
                if (kotlin.d.b.h.a(localObject2, "cn"))
                {
                  ((LanguageItem)localObject1).setLocate(0);
                }
                else if (kotlin.d.b.h.a(localObject2, "jp"))
                {
                  ((LanguageItem)localObject1).setLocate(1);
                }
                else if (kotlin.d.b.h.a(localObject2, "kr"))
                {
                  ((LanguageItem)localObject1).setLocate(2);
                }
                else
                {
                  if (!kotlin.d.b.h.a(localObject2, "en"))
                  {
                    if (kotlin.d.b.h.a(localObject2, "es"))
                    {
                      ((LanguageItem)localObject1).setLocate(4);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(localObject2, "vt"))
                    {
                      ((LanguageItem)localObject1).setLocate(7);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(localObject2, "de"))
                    {
                      ((LanguageItem)localObject1).setLocate(6);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(localObject2, "pt"))
                    {
                      ((LanguageItem)localObject1).setLocate(8);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(localObject2, "zh"))
                    {
                      ((LanguageItem)localObject1).setLocate(9);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(localObject2, "ru"))
                    {
                      ((LanguageItem)localObject1).setLocate(10);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(paramString, "fr"))
                    {
                      ((LanguageItem)localObject1).setLocate(5);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(paramString, "cnup"))
                    {
                      ((LanguageItem)localObject1).setLocate(11);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(paramString, "jpup"))
                    {
                      ((LanguageItem)localObject1).setLocate(12);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(paramString, "krup"))
                    {
                      ((LanguageItem)localObject1).setLocate(13);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(paramString, "esocup"))
                    {
                      ((LanguageItem)localObject1).setLocate(14);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(paramString, "frocup"))
                    {
                      ((LanguageItem)localObject1).setLocate(15);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(paramString, "deocup"))
                    {
                      ((LanguageItem)localObject1).setLocate(16);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(paramString, "ptup"))
                    {
                      ((LanguageItem)localObject1).setLocate(17);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(paramString, "idn"))
                    {
                      ((LanguageItem)localObject1).setLocate(18);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(paramString, "pol"))
                    {
                      ((LanguageItem)localObject1).setLocate(19);
                      break label1293;
                    }
                    if (kotlin.d.b.h.a(paramString, "it"))
                    {
                      ((LanguageItem)localObject1).setLocate(20);
                      break label1293;
                    }
                  }
                  ((LanguageItem)localObject1).setLocate(3);
                }
                label1293:
                paramString = new StringBuilder();
                paramString.append(String.valueOf(((LanguageItem)localObject1).getKeyLanguage()));
                paramString.append(":");
                paramString.append(((LanguageItem)localObject1).getLocate());
                ((LanguageItem)localObject1).setId(paramString.toString());
                ((LanguageItem)localObject1).setName(INSTANCE.getKeyLanguageName(((LanguageItem)localObject1).getKeyLanguage()));
                return localObject1;
              }
              throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
          }
        }
        else {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
      }
      else
      {
        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
      }
    }
    return null;
  }
  
  public final String getLanguageProduct(String paramString)
  {
    if (paramString.startsWith("en")) {
      return "enoc";
    }
    if (paramString.startsWith("cn")) {
      return "cnoc";
    }
    if (paramString.startsWith("jp")) {
      return "jpoc";
    }
    if (paramString.startsWith("kr")) {
      return "kroc";
    }
    if (paramString.startsWith("es")) {
      return "esoc";
    }
    if (paramString.startsWith("fr")) {
      return "froc";
    }
    if (paramString.startsWith("vt")) {
      return "vtoc";
    }
    if (paramString.startsWith("de")) {
      return "deoc";
    }
    if (paramString.startsWith("pt")) {
      return "ptoc";
    }
    if (paramString.startsWith("tch")) {
      return "tchoc";
    }
    if (paramString.startsWith("ru")) {
      return "ruoc";
    }
    if ((!paramString.startsWith("basic_member")) && (!paramString.startsWith("s0")) && (!paramString.startsWith("s3"))) {
      return "";
    }
    return "basic_member";
  }
  
  public final List<Locale> getLocalesFromIso4217(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Locale[] arrayOfLocale = NumberFormat.getAvailableLocales();
    int j = arrayOfLocale.length;
    int i = 0;
    while (i < j)
    {
      Locale localLocale = arrayOfLocale[i];
      Object localObject = NumberFormat.getCurrencyInstance(localLocale);
      kotlin.d.b.h.a(localObject, "NumberFormat.getCurrencyInstance(locale)");
      localObject = ((NumberFormat)localObject).getCurrency();
      kotlin.d.b.h.a(localObject, "NumberFormat.getCurrencyInstance(locale).currency");
      if (kotlin.d.b.h.a(paramString, ((Currency)localObject).getCurrencyCode())) {
        localArrayList.add(localLocale);
      }
      i += 1;
    }
    return (List)localArrayList;
  }
  
  public final int getMinSupportedSampleRate()
  {
    int[] arrayOfInt = new int[19];
    int[] tmp6_5 = arrayOfInt;
    tmp6_5[0] = 'ὀ';
    int[] tmp12_6 = tmp6_5;
    tmp12_6[1] = '⬑';
    int[] tmp18_12 = tmp12_6;
    tmp18_12[2] = '㺀';
    int[] tmp24_18 = tmp18_12;
    tmp24_18[3] = '嘢';
    int[] tmp30_24 = tmp24_18;
    tmp30_24[4] = '紀';
    int[] tmp36_30 = tmp30_24;
    tmp36_30[5] = 37800;
    int[] tmp42_36 = tmp36_30;
    tmp42_36[6] = 44056;
    int[] tmp49_42 = tmp42_36;
    tmp49_42[7] = 44100;
    int[] tmp56_49 = tmp49_42;
    tmp56_49[8] = 47250;
    int[] tmp63_56 = tmp56_49;
    tmp63_56[9] = 'ዀ';
    int[] tmp70_63 = tmp63_56;
    tmp70_63[10] = 50000;
    int[] tmp77_70 = tmp70_63;
    tmp77_70[11] = 50400;
    int[] tmp84_77 = tmp77_70;
    tmp84_77[12] = 88200;
    int[] tmp91_84 = tmp84_77;
    tmp91_84[13] = 96000;
    int[] tmp98_91 = tmp91_84;
    tmp98_91[14] = 176400;
    int[] tmp105_98 = tmp98_91;
    tmp105_98[15] = 192000;
    int[] tmp112_105 = tmp105_98;
    tmp112_105[16] = 352800;
    int[] tmp119_112 = tmp112_105;
    tmp119_112[17] = 2822400;
    int[] tmp126_119 = tmp119_112;
    tmp126_119[18] = 5644800;
    tmp126_119;
    int i = 0;
    while (i < 19)
    {
      new StringBuilder("checking ").append(arrayOfInt[i]);
      int j = AudioRecord.getMinBufferSize(arrayOfInt[i], 16, 2);
      if ((j != -1) && (j != -2) && (j > 0)) {
        return arrayOfInt[i];
      }
      i += 1;
    }
    return -1;
  }
  
  public final String getOsVersion()
  {
    String str = Build.VERSION.RELEASE;
    kotlin.d.b.h.a(str, "Build.VERSION.RELEASE");
    return str;
  }
  
  public final Object getPrefereceValue(Context paramContext, String paramString, Object paramObject)
  {
    String str = paramObject.getClass().getSimpleName();
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (str != null) {
      switch (str.hashCode())
      {
      default: 
        break;
      case 1729365000: 
        if (str.equals("Boolean")) {
          return Boolean.valueOf(paramContext.getBoolean(paramString, ((Boolean)paramObject).booleanValue()));
        }
        break;
      case 67973692: 
        if (str.equals("Float")) {
          return Float.valueOf(paramContext.getFloat(paramString, ((Float)paramObject).floatValue()));
        }
        break;
      case 2374300: 
        if (str.equals("Long")) {
          return Long.valueOf(paramContext.getLong(paramString, ((Long)paramObject).longValue()));
        }
        break;
      case -672261858: 
        if (str.equals("Integer")) {
          return Integer.valueOf(paramContext.getInt(paramString, ((Integer)paramObject).intValue()));
        }
        break;
      case -1808118735: 
        if (str.equals("String")) {
          return paramContext.getString(paramString, (String)paramObject);
        }
        break;
      }
    }
    return null;
  }
  
  public final String getProcessName(Context paramContext)
  {
    paramContext = paramContext.getSystemService("activity");
    if (paramContext != null)
    {
      paramContext = ((ActivityManager)paramContext).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.pid == Process.myPid()) {
          return localRunningAppProcessInfo.processName;
        }
      }
      return null;
    }
    throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
  }
  
  public final String getProductId()
  {
    if (LingoSkillApplication.a().keyLanguage == 3) {
      return "enoc";
    }
    if (LingoSkillApplication.a().keyLanguage == 0) {
      return "cnoc";
    }
    if (LingoSkillApplication.a().keyLanguage == 1) {
      return "jpoc";
    }
    if (LingoSkillApplication.a().keyLanguage == 2) {
      return "kroc";
    }
    if (LingoSkillApplication.a().keyLanguage == 4) {
      return "esoc";
    }
    if (LingoSkillApplication.a().keyLanguage == 5) {
      return "froc";
    }
    if (LingoSkillApplication.a().keyLanguage == 7) {
      return "vtoc";
    }
    if (LingoSkillApplication.a().keyLanguage == 6) {
      return "deoc";
    }
    if (LingoSkillApplication.a().keyLanguage == 8) {
      return "ptoc";
    }
    if (LingoSkillApplication.a().keyLanguage == 9) {
      return "tchoc";
    }
    if (LingoSkillApplication.a().keyLanguage == 10) {
      return "ruoc";
    }
    return "";
  }
  
  public final long getSoundDuration(int paramInt, float paramFloat)
  {
    Object localObject1 = new StringBuilder("android.resource://com.lingodeer/");
    ((StringBuilder)localObject1).append(paramInt);
    localObject1 = Uri.parse(((StringBuilder)localObject1).toString());
    localObject1 = MediaPlayer.create(LingoSkillApplication.c(), (Uri)localObject1);
    if (localObject1 != null) {}
    try
    {
      paramInt = ((MediaPlayer)localObject1).getDuration();
      paramInt = (int)(paramInt / paramFloat);
    }
    finally
    {
      if (localObject1 != null) {
        ((MediaPlayer)localObject1).release();
      }
    }
    ((MediaPlayer)localObject1).release();
    return paramInt;
  }
  
  /* Error */
  public final long getSoundDuration(android.content.res.AssetFileDescriptor paramAssetFileDescriptor, float paramFloat)
  {
    // Byte code:
    //   0: new 852	android/media/MediaPlayer
    //   3: dup
    //   4: invokespecial 866	android/media/MediaPlayer:<init>	()V
    //   7: astore 4
    //   9: aload 4
    //   11: invokevirtual 869	android/media/MediaPlayer:prepare	()V
    //   14: aload 4
    //   16: aload_1
    //   17: invokevirtual 875	android/content/res/AssetFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   20: invokevirtual 879	android/media/MediaPlayer:setDataSource	(Ljava/io/FileDescriptor;)V
    //   23: aload 4
    //   25: invokevirtual 859	android/media/MediaPlayer:getDuration	()I
    //   28: istore_3
    //   29: iload_3
    //   30: i2f
    //   31: fload_2
    //   32: fdiv
    //   33: f2i
    //   34: istore_3
    //   35: aload 4
    //   37: invokevirtual 882	android/media/MediaPlayer:reset	()V
    //   40: aload 4
    //   42: invokevirtual 862	android/media/MediaPlayer:release	()V
    //   45: goto +24 -> 69
    //   48: astore_1
    //   49: goto +23 -> 72
    //   52: astore_1
    //   53: aload_1
    //   54: invokevirtual 885	java/io/IOException:printStackTrace	()V
    //   57: aload 4
    //   59: invokevirtual 882	android/media/MediaPlayer:reset	()V
    //   62: aload 4
    //   64: invokevirtual 862	android/media/MediaPlayer:release	()V
    //   67: iconst_0
    //   68: istore_3
    //   69: iload_3
    //   70: i2l
    //   71: lreturn
    //   72: aload 4
    //   74: invokevirtual 882	android/media/MediaPlayer:reset	()V
    //   77: aload 4
    //   79: invokevirtual 862	android/media/MediaPlayer:release	()V
    //   82: aload_1
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	PhoneUtil
    //   0	84	1	paramAssetFileDescriptor	android.content.res.AssetFileDescriptor
    //   0	84	2	paramFloat	float
    //   28	42	3	i	int
    //   7	71	4	localMediaPlayer	MediaPlayer
    // Exception table:
    //   from	to	target	type
    //   9	29	48	finally
    //   53	57	48	finally
    //   9	29	52	java/io/IOException
  }
  
  public final long getSoundDuration(String paramString, float paramFloat)
  {
    paramString = MediaPlayer.create(LingoSkillApplication.c(), Uri.fromFile(new File(paramString)));
    if (paramString != null) {}
    int i;
    try
    {
      i = paramString.getDuration();
      i = (int)(i / paramFloat);
    }
    finally
    {
      if (paramString != null) {
        paramString.release();
      }
    }
    paramString.release();
    return i;
  }
  
  public final void goAdActivity() {}
  
  public final boolean hasGoogleAccess()
  {
    return true;
  }
  
  public final void hideSoftInput(Activity paramActivity)
  {
    Object localObject = LingoSkillApplication.c().getSystemService("input_method");
    if (localObject != null)
    {
      localObject = (InputMethodManager)localObject;
      paramActivity = paramActivity.getCurrentFocus();
      if (paramActivity != null) {
        ((InputMethodManager)localObject).hideSoftInputFromWindow(paramActivity.getWindowToken(), 2);
      }
      return;
    }
    throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
  }
  
  public final boolean isAsianLan()
  {
    if ((LingoSkillApplication.a().keyLanguage != 0) && (LingoSkillApplication.a().keyLanguage != 11) && (LingoSkillApplication.a().keyLanguage != 1) && (LingoSkillApplication.a().keyLanguage != 2) && (LingoSkillApplication.a().keyLanguage != 11) && (LingoSkillApplication.a().keyLanguage != 13)) {
      return LingoSkillApplication.a().keyLanguage == 12;
    }
    return true;
  }
  
  public final boolean isConnectToInternet()
  {
    try
    {
      Object localObject = LingoSkillApplication.c().getSystemService("connectivity");
      if (localObject != null)
      {
        localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
        if ((localObject != null) && (((NetworkInfo)localObject).isConnected())) {
          return true;
        }
      }
      else
      {
        throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
      }
      return false;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public final boolean isDirectToLottery()
  {
    if (!com.lingo.lingoskill.db.h.a().c())
    {
      Object localObject1 = LingoSkillApplication.a();
      kotlin.d.b.h.a(localObject1, "getEnv()");
      if (!((Env)localObject1).isUnloginUser())
      {
        localObject1 = LingoSkillApplication.a().appVersion;
        localObject1 = LingoSkillApplication.a().appVersion;
        kotlin.d.b.h.a(localObject1, "getEnv().appVersion");
        localObject1 = (CharSequence)localObject1;
        localObject1 = new f(";").a((CharSequence)localObject1);
        Object localObject2;
        int i;
        if (!((List)localObject1).isEmpty())
        {
          localObject2 = ((List)localObject1).listIterator(((List)localObject1).size());
          while (((ListIterator)localObject2).hasPrevious())
          {
            if (((CharSequence)((ListIterator)localObject2).previous()).length() == 0) {
              i = 1;
            } else {
              i = 0;
            }
            if (i == 0)
            {
              localObject1 = g.a((Iterable)localObject1, ((ListIterator)localObject2).nextIndex() + 1);
              break label176;
            }
          }
        }
        localObject1 = (List)s.a;
        label176:
        localObject1 = (Collection)localObject1;
        if (localObject1 != null)
        {
          localObject1 = ((Collection)localObject1).toArray(new String[0]);
          if (localObject1 != null)
          {
            localObject2 = (String[])localObject1;
            if (localObject2.length == 0) {
              i = 1;
            } else {
              i = 0;
            }
            if ((i ^ 0x1) != 0)
            {
              int k = localObject2.length;
              i = 0;
              boolean bool1 = false;
              while (i < k)
              {
                localObject1 = (CharSequence)localObject2[i];
                localObject1 = new f("\\.").a((CharSequence)localObject1);
                Object localObject3;
                int j;
                if (!((List)localObject1).isEmpty())
                {
                  localObject3 = ((List)localObject1).listIterator(((List)localObject1).size());
                  while (((ListIterator)localObject3).hasPrevious())
                  {
                    if (((CharSequence)((ListIterator)localObject3).previous()).length() == 0) {
                      j = 1;
                    } else {
                      j = 0;
                    }
                    if (j == 0)
                    {
                      localObject1 = g.a((Iterable)localObject1, ((ListIterator)localObject3).nextIndex() + 1);
                      break label370;
                    }
                  }
                }
                localObject1 = (List)s.a;
                label370:
                localObject1 = (Collection)localObject1;
                if (localObject1 != null)
                {
                  localObject1 = ((Collection)localObject1).toArray(new String[0]);
                  if (localObject1 != null)
                  {
                    localObject1 = (String[])localObject1;
                    if (localObject1.length == 0) {
                      j = 1;
                    } else {
                      j = 0;
                    }
                    boolean bool2 = bool1;
                    if ((j ^ 0x1) != 0)
                    {
                      if (kotlin.d.b.h.a(Integer.valueOf(localObject1[0]).intValue(), 2) < 0) {}
                      do
                      {
                        bool2 = true;
                        break;
                        localObject3 = Integer.valueOf(localObject1[0]);
                        if (localObject3 == null)
                        {
                          bool2 = bool1;
                          break;
                        }
                        bool2 = bool1;
                        if (((Integer)localObject3).intValue() != 2) {
                          break;
                        }
                        bool2 = bool1;
                        if (localObject1.length <= 1) {
                          break;
                        }
                        bool2 = bool1;
                      } while (kotlin.d.b.h.a(Integer.valueOf(localObject1[1]).intValue(), 17) < 0);
                    }
                    i += 1;
                    bool1 = bool2;
                  }
                  else
                  {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                  }
                }
                else
                {
                  throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                }
              }
              return bool1;
            }
          }
          else
          {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
          }
        }
        else
        {
          throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
        }
      }
    }
    return false;
  }
  
  public final boolean isFreeLevel()
  {
    return (LingoSkillApplication.a().keyLanguage != 12) && (LingoSkillApplication.a().keyLanguage != 13) && (LingoSkillApplication.a().keyLanguage != 11) && (LingoSkillApplication.a().keyLanguage != 14) && (LingoSkillApplication.a().keyLanguage != 15) && (LingoSkillApplication.a().keyLanguage != 16) && (LingoSkillApplication.a().keyLanguage != 17);
  }
  
  public final boolean isSoftInstalled(String paramString)
  {
    Object localObject = LingoSkillApplication.c();
    kotlin.d.b.h.a(localObject, "LingoSkillApplication.getContext()");
    localObject = ((Context)localObject).getPackageManager();
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (localObject != null)
    {
      int j = ((Collection)localObject).size();
      while (i < j)
      {
        localArrayList.add(((PackageInfo)((List)localObject).get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public final boolean isUnloginUser(Env paramEnv)
  {
    return paramEnv.isUnloginUser();
  }
  
  public final void marketToAPP(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    StringBuilder localStringBuilder = new StringBuilder("market://details?id=");
    localStringBuilder.append(paramString);
    localIntent.setData(Uri.parse(localStringBuilder.toString()));
    if (localIntent.resolveActivity(paramContext.getPackageManager()) != null)
    {
      paramContext.startActivity(localIntent);
      return;
    }
    localStringBuilder = new StringBuilder("https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(paramString);
    localIntent.setData(Uri.parse(localStringBuilder.toString()));
    if (localIntent.resolveActivity(paramContext.getPackageManager()) != null) {
      paramContext.startActivity(localIntent);
    }
  }
  
  public final void marketToGooglePlay(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    if (localIntent.resolveActivity(paramContext.getPackageManager()) != null) {
      paramContext.startActivity(localIntent);
    }
  }
  
  public final String replaceBlank(String paramString)
  {
    String str = "";
    if (paramString != null)
    {
      str = Pattern.compile("\n").matcher((CharSequence)paramString).replaceAll("");
      kotlin.d.b.h.a(str, "m.replaceAll(\"\")");
    }
    return str;
  }
  
  public final void showSoftInput(EditText paramEditText)
  {
    paramEditText.setFocusable(true);
    paramEditText.setFocusableInTouchMode(true);
    paramEditText.requestFocus();
    Object localObject = LingoSkillApplication.c().getSystemService("input_method");
    if (localObject != null)
    {
      ((InputMethodManager)localObject).showSoftInput((View)paramEditText, 2);
      return;
    }
    throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
  }
  
  public final void sortMap(HashMap<Long, Integer> paramHashMap)
  {
    paramHashMap = (Map)paramHashMap;
    new TreeMap((Comparator)sortMap.1.INSTANCE).putAll(paramHashMap);
  }
  
  public final void switchInputMethod(Context paramContext)
  {
    paramContext = paramContext.getSystemService("input_method");
    if (paramContext != null)
    {
      ((InputMethodManager)paramContext).showInputMethodPicker();
      return;
    }
    throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
  }
  
  public final void switchLanguage(Env paramEnv)
  {
    Object localObject = com.lingo.lingoskill.base.c.e.a;
    localObject = com.lingo.lingoskill.base.c.e.b().getResources();
    kotlin.d.b.h.a(localObject, "resources");
    Configuration localConfiguration = ((Resources)localObject).getConfiguration();
    DisplayMetrics localDisplayMetrics = ((Resources)localObject).getDisplayMetrics();
    int i = paramEnv.locateLanguage;
    switch (i)
    {
    default: 
      switch (i)
      {
      default: 
        paramEnv = null;
        break;
      case 20: 
        paramEnv = new Locale("it");
        break;
      case 19: 
        paramEnv = new Locale("pl");
        break;
      case 18: 
        paramEnv = new Locale("in");
      }
      break;
    case 10: 
      paramEnv = new Locale("ru");
      break;
    case 9: 
      paramEnv = Locale.TRADITIONAL_CHINESE;
      break;
    case 8: 
      paramEnv = new Locale("pt");
      break;
    case 7: 
      paramEnv = new Locale("vi");
      break;
    case 6: 
      paramEnv = Locale.GERMANY;
      break;
    case 5: 
      paramEnv = Locale.FRANCE;
      break;
    case 4: 
      paramEnv = new Locale("es");
      break;
    case 3: 
      paramEnv = Locale.ENGLISH;
      break;
    case 2: 
      paramEnv = Locale.KOREA;
      break;
    case 1: 
      paramEnv = Locale.JAPAN;
      break;
    case 0: 
      paramEnv = Locale.SIMPLIFIED_CHINESE;
      break;
    case -1: 
      paramEnv = Locale.getDefault();
    }
    if (paramEnv != null)
    {
      localConfiguration.locale = paramEnv;
      ((Resources)localObject).updateConfiguration(localConfiguration, localDisplayMetrics);
      Locale.setDefault(paramEnv);
    }
  }
  
  public final Context wrapContext(Context paramContext)
  {
    Locale localLocale = Locale.ENGLISH;
    if (LingoSkillApplication.a() == null)
    {
      paramContext = ContextWrapper.wrap(paramContext, Locale.getDefault());
      kotlin.d.b.h.a(paramContext, "ContextWrapper.wrap(newBase, newLocale)");
      return (Context)paramContext;
    }
    int i = LingoSkillApplication.a().locateLanguage;
    switch (i)
    {
    default: 
      switch (i)
      {
      default: 
        break;
      case 20: 
        localLocale = new Locale("it");
        break;
      case 19: 
        localLocale = new Locale("pl");
        break;
      case 18: 
        localLocale = new Locale("in");
      }
      break;
    case 10: 
      localLocale = new Locale("ru");
      break;
    case 9: 
      localLocale = Locale.TRADITIONAL_CHINESE;
      break;
    case 8: 
      localLocale = new Locale("pt");
      break;
    case 7: 
      localLocale = new Locale("vi");
      break;
    case 6: 
      localLocale = Locale.GERMANY;
      break;
    case 5: 
      localLocale = Locale.FRANCE;
      break;
    case 4: 
      localLocale = new Locale("es");
      break;
    case 3: 
      localLocale = Locale.ENGLISH;
      break;
    case 2: 
      localLocale = Locale.KOREA;
      break;
    case 1: 
      localLocale = Locale.JAPAN;
      break;
    case 0: 
      localLocale = Locale.SIMPLIFIED_CHINESE;
      break;
    case -1: 
      if (LingoSkillApplication.a().deviceLanguage != null) {
        localLocale = new Locale(LingoSkillApplication.a().deviceLanguage);
      } else {
        localLocale = Locale.getDefault();
      }
      break;
    }
    paramContext = ContextWrapper.wrap(paramContext, localLocale);
    kotlin.d.b.h.a(paramContext, "ContextWrapper.wrap(newBase, newLocale)");
    return (Context)paramContext;
  }
}
