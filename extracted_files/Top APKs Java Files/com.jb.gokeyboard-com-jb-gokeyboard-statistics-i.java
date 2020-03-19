package com.jb.gokeyboard.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.gau.go.gostaticsdk.StatisticsManager;
import com.gau.go.gostaticsdk.utiltool.UtilTool;
import com.jb.gokeyboard.GoKeyboardApplication;
import com.jb.gokeyboard.common.util.v;
import com.jb.gokeyboard.frame.b;
import com.jb.gokeyboard.keyboardmanage.datamanage.c;
import com.jb.gokeyboard.keyboardmanage.datamanage.d;
import com.jb.gokeyboard.l.j;
import com.jb.gokeyboard.preferences.KeyboardSettingSetMenuOpActivity;
import com.jb.gokeyboard.preferences.view.l;
import com.jb.gokeyboard.ui.frame.h;
import com.jb.gokeyboard.ui.p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class i
{
  public static String a(long paramLong, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Context localContext = GoKeyboardApplication.b();
    localStringBuilder.append(24);
    localStringBuilder.append("||");
    localStringBuilder.append(UtilTool.getBeiJinTime(paramLong));
    localStringBuilder.append("||");
    localStringBuilder.append(v.c(localContext));
    localStringBuilder.append("||");
    localStringBuilder.append(StatisticsManager.getGOID(localContext));
    localStringBuilder.append("||");
    localStringBuilder.append(com.jb.gokeyboard.common.util.k.c(localContext));
    localStringBuilder.append("||");
    localStringBuilder.append(v.d(localContext));
    localStringBuilder.append("||");
    localStringBuilder.append(v.a(localContext));
    localStringBuilder.append("||");
    localStringBuilder.append(v.b(localContext));
    localStringBuilder.append("||");
    localStringBuilder.append(paramInt);
    localStringBuilder.append("||");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("||");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("||");
    localStringBuilder.append(paramString3);
    localStringBuilder.append("||");
    return localStringBuilder.toString();
  }
  
  public static String a(long paramLong, String paramString1, String paramString2)
  {
    return a(paramLong, 150, paramString1, paramString2, "-1");
  }
  
  private static String a(long paramLong, String paramString1, String paramString2, String paramString3)
  {
    return a(paramLong, 150, paramString1, paramString2, paramString3);
  }
  
  public static String a(long paramLong, String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str = "1";; str = "0") {
      return a(paramLong, 150, paramString, str, "-1");
    }
  }
  
  public static String a(String paramString, Context paramContext)
  {
    if ("切换键盘布局".equals(paramString)) {
      paramString = "101";
    }
    do
    {
      return paramString;
      if ("弧形菜单设置".equals(paramString)) {
        return "102";
      }
      if ("勾选输入语言".equals(paramString)) {
        return "103";
      }
      if ("输入设置".equals(paramString)) {
        return "104";
      }
      if ("数字".equals(paramString)) {
        return "105";
      }
      if ("符号".equals(paramString)) {
        return "106";
      }
      paramContext = c.a(paramContext).b(paramString);
      paramString = paramContext;
    } while (paramContext != null);
    return "-1";
  }
  
  public static String a(String paramString, Context paramContext, int paramInt, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3)
  {
    String str = PreferenceManager.getDefaultSharedPreferences(paramContext).getString(paramString, "");
    paramString = str;
    if ("".equals(str))
    {
      paramString = str;
      if (paramInt >= 0)
      {
        paramString = str;
        if (paramArrayOfString1 != null)
        {
          paramString = str;
          if (paramInt < paramArrayOfString1.length) {
            paramString = paramArrayOfString1[paramInt];
          }
        }
      }
    }
    paramInt = 0;
    while (paramInt < paramArrayOfString2.length)
    {
      if (TextUtils.equals(paramString, paramArrayOfString2[paramInt])) {
        return paramArrayOfString2[paramInt];
      }
      paramInt += 1;
    }
    paramArrayOfString1 = new ArrayList();
    if (paramString.contains("|"))
    {
      paramInt = paramString.indexOf("|");
      paramArrayOfString1.add(paramString.substring(0, paramInt));
      paramArrayOfString1.add(paramString.substring(paramInt + 1));
      paramInt = 0;
    }
    for (;;)
    {
      if (paramInt >= paramArrayOfString1.size()) {
        break label204;
      }
      int i = 0;
      for (;;)
      {
        if (i >= paramArrayOfString3.length) {
          break label197;
        }
        if (TextUtils.equals((CharSequence)paramArrayOfString1.get(paramInt), paramArrayOfString3[i]))
        {
          return paramArrayOfString3[i];
          paramArrayOfString1.add(paramString);
          break;
        }
        i += 1;
      }
      label197:
      paramInt += 1;
    }
    label204:
    return paramContext.getResources().getString(2131165361);
  }
  
  public static void a()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    long l = System.currentTimeMillis();
    Context localContext = GoKeyboardApplication.b();
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(localContext);
    Resources localResources = localContext.getResources();
    Object localObject3 = b.a();
    if (localSharedPreferences.getInt("gosearch", 0) == 0)
    {
      localObject1 = "0";
      localStringBuilder.append(a(l, "using_search", (String)localObject1));
      localStringBuilder.append("\r\n");
      localObject1 = localResources.getString(2131165350);
      if (!TextUtils.equals(localSharedPreferences.getString("TypeFont", (String)localObject1), (CharSequence)localObject1)) {
        break label214;
      }
    }
    label214:
    for (Object localObject1 = "0";; localObject1 = "1")
    {
      localStringBuilder.append(a(l, "default_font", (String)localObject1));
      localStringBuilder.append("\r\n");
      if (!l.q(localContext)) {
        break label258;
      }
      localObject1 = j.a(localContext, "theme_phone");
      ((ArrayList)localObject1).addAll(j.a(localContext, "theme_pad"));
      localObject2 = new StringBuilder();
      i = 0;
      while (i < ((ArrayList)localObject1).size())
      {
        ((StringBuilder)localObject2).append((String)((ArrayList)localObject1).get(i)).append(",");
        i += 1;
      }
      localObject1 = "1";
      break;
    }
    localObject1 = ((StringBuilder)localObject2).toString();
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      localStringBuilder.append(a(l, "local_themes_new", (String)localObject1));
      localStringBuilder.append("\r\n");
    }
    label258:
    Object localObject2 = localSharedPreferences.getString("SkinPackName", "default");
    localObject1 = localObject2;
    if ("com.jb.gokeyboard:default".equals(localObject2)) {
      localObject1 = "default";
    }
    localStringBuilder.append(a(l, "using_themes", (String)localObject1));
    localStringBuilder.append("\r\n");
    localStringBuilder.append(a(l, "lang_set", ((b)localObject3).p()));
    localStringBuilder.append("\r\n");
    int i = d.a(((b)localObject3).s());
    localObject2 = ((b)localObject3).r();
    if (i == 8192)
    {
      localObject1 = "1";
      localStringBuilder.append(a(l, "default_set", (String)localObject2, (String)localObject1));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s001", localSharedPreferences.getBoolean("KeySound", localResources.getBoolean(2131689473))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s002", localSharedPreferences.getBoolean("KeyVibration", localResources.getBoolean(2131689474))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s003", localSharedPreferences.getBoolean("ArrowKey", localResources.getBoolean(2131689504))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s004", localSharedPreferences.getBoolean("DoubleEngine", localResources.getBoolean(2131689476))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s005", localSharedPreferences.getBoolean("ShowSuggest", localResources.getBoolean(2131689480))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s006", localSharedPreferences.getBoolean("Autospace", localResources.getBoolean(2131689482))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s007", localSharedPreferences.getBoolean("AutoCommit", localResources.getBoolean(2131689507))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s008", localSharedPreferences.getBoolean("AutoCaps", localResources.getBoolean(2131689477))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s009", localSharedPreferences.getBoolean("SwipInput", localResources.getBoolean(2131689512))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s010", localSharedPreferences.getBoolean("StartFantasyText", localResources.getBoolean(2131689509))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s016", localSharedPreferences.getBoolean("ShowCompletetrack", localResources.getBoolean(2131689513))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s017", localSharedPreferences.getBoolean("RememberDic", localResources.getBoolean(2131689506))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s018", localSharedPreferences.getBoolean("RTL", localResources.getBoolean(2131689500))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s011", localSharedPreferences.getBoolean("Martian", localResources.getBoolean(2131689486))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s013", localSharedPreferences.getBoolean("fuzzypinyin_enable", localResources.getBoolean(2131689487))));
      localStringBuilder.append("\r\n");
      i = p.a(localContext, "Association", 2131623954, 2131165332);
      localObject1 = "102";
      if (i != 0) {
        break label1186;
      }
      localObject1 = "101";
      label999:
      localStringBuilder.append(a(l, "s019", (String)localObject1));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s020", localSharedPreferences.getBoolean("SpaceSelectAssociation", localResources.getBoolean(2131689501))));
      localStringBuilder.append("\r\n");
      if (p.a(localContext, "KEY_L5_SimpleTraditional", 2131623955, 2131165333) != 0) {
        break label1210;
      }
    }
    label1186:
    label1210:
    for (localObject1 = "101";; localObject1 = "102")
    {
      localStringBuilder.append(a(l, "s022", (String)localObject1));
      localStringBuilder.append("\r\n");
      localObject1 = l.e(localContext);
      localObject2 = localResources.getStringArray(2131623971);
      localObject3 = new StringBuilder();
      i = 0;
      while ((i < localObject1.length) && (i < localObject2.length))
      {
        if (localObject1[i] != 0)
        {
          ((StringBuilder)localObject3).append(localObject2[i]);
          ((StringBuilder)localObject3).append(",");
        }
        i += 1;
      }
      localObject1 = "0";
      break;
      if (i == 2)
      {
        localObject1 = "103";
        break label999;
      }
      if (i != 1) {
        break label999;
      }
      localObject1 = "102";
      break label999;
    }
    if (((StringBuilder)localObject3).length() > 0) {
      ((StringBuilder)localObject3).deleteCharAt(((StringBuilder)localObject3).length() - 1);
    }
    localStringBuilder.append(a(l, "s024", ((StringBuilder)localObject3).toString()));
    localStringBuilder.append("\r\n");
    localObject1 = l.c(localContext).split(",");
    localObject2 = new String[5];
    localObject2[0] = "101";
    localObject2[1] = "102";
    localObject2[2] = "103";
    localObject2[3] = "104";
    localObject2[4] = "105";
    localObject3 = new StringBuilder();
    i = 0;
    while ((i < localObject1.length) && (i < localObject2.length))
    {
      if ("1".equals(localObject1[i]))
      {
        ((StringBuilder)localObject3).append(localObject2[i]);
        ((StringBuilder)localObject3).append(",");
      }
      i += 1;
    }
    if (((StringBuilder)localObject3).length() > 0) {
      ((StringBuilder)localObject3).deleteCharAt(((StringBuilder)localObject3).length() - 1);
    }
    localStringBuilder.append(a(l, "s025", ((StringBuilder)localObject3).toString()));
    localStringBuilder.append("\r\n");
    localStringBuilder.append(a(l, "s027", localSharedPreferences.getBoolean("AssociateWithSymbol", localResources.getBoolean(2131689484))));
    localStringBuilder.append("\r\n");
    localStringBuilder.append(a(l, "s028", localSharedPreferences.getBoolean("DoubleEngine", localResources.getBoolean(2131689476))));
    localStringBuilder.append("\r\n");
    i = p.a(localContext, "Laught", 2131623958, 2131165336);
    localObject1 = "103";
    label1576:
    label1769:
    label1891:
    label1935:
    label1979:
    label2023:
    label2067:
    int j;
    if (i == 0)
    {
      localObject1 = "101";
      localStringBuilder.append(a(l, "s029", (String)localObject1));
      localStringBuilder.append("\r\n");
      i = p.a(localContext, "LaughtSuggestion", 2131623966, 2131165343);
      localObject1 = "102";
      if (i != 0) {
        break label2782;
      }
      localObject1 = "101";
      localStringBuilder.append(a(l, "s030", (String)localObject1));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s031_01", localSharedPreferences.getBoolean("PortraitFullScreen", localResources.getBoolean(2131689510))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s031_02", localSharedPreferences.getBoolean("LandFullScreen", localResources.getBoolean(2131689511))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s033", localSharedPreferences.getBoolean("PreviewKey", localResources.getBoolean(2131689505))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s034", localSharedPreferences.getBoolean("EnableSwipe", true)));
      localStringBuilder.append("\r\n");
      if (p.a(localContext, "SwitchLauout", 2131623959, 2131165338) != 0) {
        break label2818;
      }
      localObject1 = "101";
      localStringBuilder.append(a(l, "s035", (String)localObject1));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s014", localSharedPreferences.getBoolean("NotifyIcon", localResources.getBoolean(2131689503))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s036", localSharedPreferences.getBoolean("RingInput", localResources.getBoolean(2131689508))));
      localStringBuilder.append("\r\n");
      if (p.a(localContext, "Split0", 2131623965, 2131165342) != 0) {
        break label2825;
      }
      localObject1 = "101";
      localStringBuilder.append(a(l, "s037", (String)localObject1));
      localStringBuilder.append("\r\n");
      if (p.a(localContext, "CandidateSpreadOut2", 2131623961, 2131165349) != 0) {
        break label2832;
      }
      localObject1 = "101";
      localStringBuilder.append(a(l, "s038", (String)localObject1));
      localStringBuilder.append("\r\n");
      if (p.a(localContext, "ComposingLocation", 2131623960, 2131165339) != 0) {
        break label2839;
      }
      localObject1 = "101";
      localStringBuilder.append(a(l, "s039", (String)localObject1));
      localStringBuilder.append("\r\n");
      if (p.a(localContext, "ShowVoiceInput", 2131623956, 2131165334) != 0) {
        break label2846;
      }
      localObject1 = "101";
      localStringBuilder.append(a(l, "s040", (String)localObject1));
      localStringBuilder.append("\r\n");
      if (p.a(localContext, "SectorLeftOrRight", 2131623967, 2131165344) != 0) {
        break label2853;
      }
      localObject1 = "101";
      localStringBuilder.append(a(l, "s044", (String)localObject1));
      localStringBuilder.append("\r\n");
      a(localStringBuilder, l);
      localStringBuilder.append(a(l, "s015", localSharedPreferences.getBoolean("ImportContacts", false)));
      localStringBuilder.append("\r\n");
      i = 0;
      j = p.a(localContext, "KeyboardLayoutMode", 2131623964, 2131165340);
      localObject1 = "101";
      if (j != 0) {
        break label2860;
      }
      i = 0;
      localObject1 = "101";
      label2159:
      localStringBuilder.append(a(l, "s050", (String)localObject1));
      localStringBuilder.append("\r\n");
      if (i != 0)
      {
        i = p.a(localContext, "PadPortraitScreen", 2131623963, 2131165351);
        localObject1 = "101";
        if (i != 0) {
          break label2874;
        }
        localObject1 = "101";
        label2213:
        localStringBuilder.append(a(l, "s051", (String)localObject1));
        localStringBuilder.append("\r\n");
        i = p.a(localContext, "PadLandScreen", 2131623962, 2131165352);
        localObject1 = "101";
        if (i != 0) {
          break label2910;
        }
        localObject1 = "101";
        label2263:
        localStringBuilder.append(a(l, "s052", (String)localObject1));
        localStringBuilder.append("\r\n");
      }
      localStringBuilder.append(a(l, "s054", localSharedPreferences.getBoolean("T9Hide", localResources.getBoolean(2131689481))));
      localStringBuilder.append("\r\n");
      localObject2 = localSharedPreferences.getString("KeySoundType", localResources.getString(2131165345));
      if (!TextUtils.isEmpty((CharSequence)localObject2))
      {
        localObject1 = localObject2;
        if (((String)localObject2).indexOf(",") != -1) {
          localObject1 = localObject2.split(",")[0];
        }
        localStringBuilder.append(a(l, "s055", (String)localObject1));
        localStringBuilder.append("\r\n");
      }
      localStringBuilder.append(a(l, "s_height_01", String.valueOf(localSharedPreferences.getFloat("PortraitKeyboardheightPercent", localResources.getInteger(2131296337)))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s_height_02", String.valueOf(localSharedPreferences.getFloat("LandKeyboardheightPercent", localResources.getInteger(2131296337)))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s_font_01", String.valueOf(localSharedPreferences.getInt("FrontSizePercent ", 4))));
      localStringBuilder.append("\r\n");
      i = localSharedPreferences.getInt("first_lang_pack_count", 0);
      if (i > 0)
      {
        localStringBuilder.append(a(l, "first_lang_pack", String.valueOf(i)));
        localStringBuilder.append("\r\n");
      }
      localObject1 = localSharedPreferences.getString("Keyboardfilename2", "");
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        localStringBuilder.append(a(l, "non_lang_pack", String.valueOf(((String)localObject1).split(",").length)));
        localStringBuilder.append("\r\n");
      }
      if (!l.D(localContext)) {
        break label2946;
      }
      localStringBuilder.append(a(l, "s60", localSharedPreferences.getBoolean("EmojiPrediction", localResources.getBoolean(2131689478))));
      localStringBuilder.append("\r\n");
      localStringBuilder.append(a(l, "s62", localSharedPreferences.getBoolean("NextPrediction", localResources.getBoolean(2131689479))));
      localStringBuilder.append("\r\n");
    }
    for (;;)
    {
      localObject1 = localStringBuilder.toString();
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        if (k.a) {
          h.a("Statistic", "uploadAllSettingsStatistic data = " + (String)localObject1);
        }
        e.b((String)localObject1);
      }
      return;
      if (i == 1)
      {
        localObject1 = "102";
        break;
      }
      if (i != 2) {
        break;
      }
      localObject1 = "103";
      break;
      label2782:
      if (i == 1)
      {
        localObject1 = "102";
        break label1576;
      }
      if (i == 2)
      {
        localObject1 = "103";
        break label1576;
      }
      if (i != 3) {
        break label1576;
      }
      localObject1 = "104";
      break label1576;
      label2818:
      localObject1 = "102";
      break label1769;
      label2825:
      localObject1 = "102";
      break label1891;
      label2832:
      localObject1 = "102";
      break label1935;
      label2839:
      localObject1 = "102";
      break label1979;
      label2846:
      localObject1 = "102";
      break label2023;
      label2853:
      localObject1 = "102";
      break label2067;
      label2860:
      if (j != 1) {
        break label2159;
      }
      i = 1;
      localObject1 = "102";
      break label2159;
      label2874:
      if (i == 1)
      {
        localObject1 = "102";
        break label2213;
      }
      if (i == 2)
      {
        localObject1 = "103";
        break label2213;
      }
      if (i != 3) {
        break label2213;
      }
      localObject1 = "104";
      break label2213;
      label2910:
      if (i == 1)
      {
        localObject1 = "102";
        break label2263;
      }
      if (i == 2)
      {
        localObject1 = "103";
        break label2263;
      }
      if (i != 3) {
        break label2263;
      }
      localObject1 = "104";
      break label2263;
      label2946:
      localStringBuilder.append(a(l, "s026", localSharedPreferences.getBoolean("KeyCorrection", localResources.getBoolean(2131689483))));
      localStringBuilder.append("\r\n");
    }
  }
  
  public static void a(StringBuilder paramStringBuilder, long paramLong)
  {
    Context localContext = GoKeyboardApplication.b();
    String[] arrayOfString1 = KeyboardSettingSetMenuOpActivity.a(c.a(localContext).b(localContext), localContext);
    String[] arrayOfString2 = localContext.getString(2131165335).split(",");
    String[] arrayOfString3 = localContext.getResources().getStringArray(2131623957);
    paramStringBuilder.append(a(paramLong, "s045", a(a("MenuOp1", localContext, 0, arrayOfString2, arrayOfString3, arrayOfString1), localContext)));
    paramStringBuilder.append("\r\n");
    paramStringBuilder.append(a(paramLong, "s046", a(a("MenuOp2", localContext, 1, arrayOfString2, arrayOfString3, arrayOfString1), localContext)));
    paramStringBuilder.append("\r\n");
    paramStringBuilder.append(a(paramLong, "s047", a(a("MenuOp3", localContext, 2, arrayOfString2, arrayOfString3, arrayOfString1), localContext)));
    paramStringBuilder.append("\r\n");
    paramStringBuilder.append(a(paramLong, "s048", a(a("MenuOp4", localContext, 3, arrayOfString2, arrayOfString3, arrayOfString1), localContext)));
    paramStringBuilder.append("\r\n");
    paramStringBuilder.append(a(paramLong, "s049", a(a("MenuOp5", localContext, 4, arrayOfString2, arrayOfString3, arrayOfString1), localContext)));
    paramStringBuilder.append("\r\n");
  }
  
  public static void b()
  {
    Object localObject = GoKeyboardApplication.b().getPackageManager().getInstalledPackages(0);
    if ((localObject == null) || (((List)localObject).size() <= 0)) {}
    label105:
    label170:
    do
    {
      return;
      StringBuilder localStringBuilder = new StringBuilder();
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (localPackageInfo != null)
        {
          ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
          if (localApplicationInfo != null)
          {
            localStringBuilder.append(localApplicationInfo.packageName).append(",");
            if ((localApplicationInfo.flags & 0x1) != 0)
            {
              localStringBuilder.append(1);
              localStringBuilder.append(",").append(localPackageInfo.versionName).append(",").append(localPackageInfo.versionCode).append(",");
              if (!localApplicationInfo.enabled) {
                break label170;
              }
              localStringBuilder.append(0);
            }
            for (;;)
            {
              localStringBuilder.append("#");
              break;
              localStringBuilder.append(0);
              break label105;
              localStringBuilder.append(1);
            }
          }
        }
      }
      if (localObject != null) {
        ((List)localObject).clear();
      }
      if (localStringBuilder.length() > 0) {
        localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
      }
      localObject = a(System.currentTimeMillis(), 227, "app_list", localStringBuilder.toString(), "-1");
    } while (TextUtils.isEmpty((CharSequence)localObject));
    if (k.a) {
      h.a("Statistic", "uploadAllInstalledAppsWithPhone data: " + (String)localObject);
    }
    e.b((String)localObject);
  }
}
