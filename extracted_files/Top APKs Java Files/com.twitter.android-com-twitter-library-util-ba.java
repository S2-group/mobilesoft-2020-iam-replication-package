package com.twitter.library.util;

import alt;
import alx;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ResolveInfo.DisplayNameComparator;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.preference.ListPreference;
import android.provider.Settings.Secure;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Pair;
import android.webkit.WebView;
import avt;
import awd;
import com.twitter.internal.network.HttpOperation.RequestMethod;
import com.twitter.library.network.x;
import com.twitter.model.account.OAuthToken;
import com.twitter.model.core.Tweet;
import com.twitter.model.moments.y;
import com.twitter.util.ay;
import com.twitter.util.be;
import com.twitter.util.collection.MutableList;
import com.twitter.util.collection.n;
import com.twitter.util.collection.r;
import com.twitter.util.d;
import com.twitter.util.t;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public abstract class ba
{
  public static final Random a = new Random();
  private static final Pattern b = Pattern.compile("%(\\d+\\$|<?)([^a-zA-z%]*)([[a-zA-Z%]&&[^tT]]|[tT][a-zA-Z])");
  
  public static float a(int paramInt1, int paramInt2, float paramFloat)
  {
    float f = paramFloat;
    if (paramInt1 > 0)
    {
      f = paramFloat;
      if (paramInt2 > 0) {
        f = paramInt1 / paramInt2;
      }
    }
    return f;
  }
  
  public static Spannable a(String paramString, @ColorInt int paramInt)
  {
    SpannableString localSpannableString = new SpannableString(paramString);
    localSpannableString.setSpan(new ForegroundColorSpan(paramInt), 0, paramString.length(), 33);
    return localSpannableString;
  }
  
  @Deprecated
  public static Spanned a(Object[] paramArrayOfObject, String paramString, char paramChar)
  {
    return a(paramArrayOfObject, paramString, String.valueOf(paramChar));
  }
  
  public static Spanned a(Object[] paramArrayOfObject, String paramString1, String paramString2)
  {
    int j = 0;
    int k = paramString1.indexOf(paramString2);
    if (k == -1) {
      return new SpannableString(paramString1);
    }
    int i = paramString1.indexOf(paramString2, k + 1);
    if (i == -1) {
      return new SpannableString(paramString1);
    }
    int i4 = paramArrayOfObject.length;
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
    int n = 0;
    int i1 = 0;
    localSpannableStringBuilder.append(paramString1.substring(n, k));
    localSpannableStringBuilder.append(paramString1.substring(paramString2.length() + k, i));
    localSpannableStringBuilder.setSpan(paramArrayOfObject[i1], k - j, i - j - paramString2.length(), 33);
    i1 += 1;
    int i2 = paramString2.length() + i;
    if (i1 >= i4) {}
    for (;;)
    {
      localSpannableStringBuilder.append(paramString1.substring(i2));
      return localSpannableStringBuilder;
      int i3 = paramString1.indexOf(paramString2, i2);
      k = j;
      int m = i;
      if (i3 != -1)
      {
        m = paramString1.indexOf(paramString2, i3 + 1);
        k = j + paramString2.length() * 2;
      }
      if (i3 != -1)
      {
        j = k;
        n = i2;
        i = m;
        k = i3;
        if (m != -1) {
          break;
        }
      }
    }
  }
  
  public static SpannedString a(CharSequence paramCharSequence, Object... paramVarArgs)
  {
    return a(Locale.getDefault(), paramCharSequence, paramVarArgs);
  }
  
  public static SpannedString a(Locale paramLocale, CharSequence paramCharSequence, Object... paramVarArgs)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramCharSequence);
    int i = -1;
    int j = 0;
    if (j < localSpannableStringBuilder.length())
    {
      localObject2 = b.matcher(localSpannableStringBuilder);
      if (((Matcher)localObject2).find(j)) {}
    }
    else
    {
      return new SpannedString(localSpannableStringBuilder);
    }
    int m = ((Matcher)localObject2).start();
    int n = ((Matcher)localObject2).end();
    Object localObject1 = ((Matcher)localObject2).group(1);
    paramCharSequence = ((Matcher)localObject2).group(2);
    Object localObject2 = ((Matcher)localObject2).group(3);
    if (((String)localObject2).equals("%")) {
      paramCharSequence = "%";
    }
    for (;;)
    {
      localSpannableStringBuilder.replace(m, n, paramCharSequence);
      j = paramCharSequence.length() + m;
      break;
      if (((String)localObject2).equals("n"))
      {
        paramCharSequence = "\n";
      }
      else
      {
        if (((String)localObject1).isEmpty())
        {
          j = i + 1;
          i = j;
        }
        for (;;)
        {
          localObject1 = paramVarArgs[j];
          if ((!((String)localObject2).equals("s")) || (!(localObject1 instanceof Spanned))) {
            break label244;
          }
          paramCharSequence = (Spanned)localObject1;
          break;
          if (((String)localObject1).equals("<"))
          {
            int k = i;
            j = i;
            i = k;
          }
          else
          {
            j = Integer.parseInt(((String)localObject1).substring(0, ((String)localObject1).length() - 1));
            j -= 1;
          }
        }
        label244:
        paramCharSequence = String.format(paramLocale, "%" + paramCharSequence + (String)localObject2, new Object[] { localObject1 });
      }
    }
  }
  
  public static com.twitter.ui.view.a a(Context paramContext, @StringRes int paramInt1, @ColorRes int paramInt2)
  {
    return new bb(paramContext.getResources().getColor(paramInt2), paramContext, paramInt1);
  }
  
  public static com.twitter.ui.view.a a(Context paramContext, @StringRes int paramInt1, @ColorRes int paramInt2, Class paramClass)
  {
    return new bc(paramContext.getResources().getColor(paramInt2), paramContext, paramClass, paramInt1);
  }
  
  public static CharSequence a(Resources paramResources, int paramInt, Object... paramVarArgs)
  {
    paramResources = paramResources.getText(paramInt);
    if (!(paramResources instanceof Spanned)) {
      return String.format(paramResources.toString(), paramVarArgs);
    }
    return Html.fromHtml(String.format(a((Spanned)paramResources), paramVarArgs));
  }
  
  public static String a(Spanned paramSpanned)
  {
    int i = 0;
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramSpanned);
    paramSpanned = (StyleSpan[])paramSpanned.getSpans(0, paramSpanned.length(), StyleSpan.class);
    int j = paramSpanned.length;
    if (i < j)
    {
      Object localObject = paramSpanned[i];
      int k = localObject.getStyle();
      if ((k & 0x3) == 3)
      {
        localSpannableStringBuilder.insert(localSpannableStringBuilder.getSpanStart(localObject), "<b><i>");
        localSpannableStringBuilder.insert(localSpannableStringBuilder.getSpanEnd(localObject), "</i></b>");
      }
      for (;;)
      {
        i += 1;
        break;
        if ((k & 0x1) == 1)
        {
          localSpannableStringBuilder.insert(localSpannableStringBuilder.getSpanStart(localObject), "<b>");
          localSpannableStringBuilder.insert(localSpannableStringBuilder.getSpanEnd(localObject), "</b>");
        }
        else if ((k & 0x2) == 2)
        {
          localSpannableStringBuilder.insert(localSpannableStringBuilder.getSpanStart(localObject), "<i>");
          localSpannableStringBuilder.insert(localSpannableStringBuilder.getSpanEnd(localObject), "</i>");
        }
      }
    }
    return localSpannableStringBuilder.toString();
  }
  
  public static String a(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.startsWith("@")) {
        str = paramString.substring(1);
      }
    }
    return str;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    return t.b(paramString2 + "d6PaPHJeSpyHXeVyWT6ePCcSMSrnD83MnfMgWhtczxpnSMSF7CQcBSQqtBNh6Jym" + paramString1 + "Activation");
  }
  
  public static String a(List<? extends NameValuePair> paramList)
  {
    if (paramList != null) {
      return URLEncodedUtils.format(paramList, "UTF-8").replace("*", "%2A");
    }
    return null;
  }
  
  public static String a(Map<String, ?> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramMap != null)
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Object localObject = (Map.Entry)paramMap.next();
        String str = (String)((Map.Entry)localObject).getKey();
        localObject = ((Map.Entry)localObject).getValue();
        if ((str != null) && (localObject != null))
        {
          str = str.replace(',', '_').replace('=', '_');
          localObject = localObject.toString().replace(',', '_').replace('=', '_');
          localStringBuilder.append(str).append("=").append((String)localObject).append(",");
        }
      }
    }
    if (localStringBuilder.length() > 0) {
      return localStringBuilder.deleteCharAt(localStringBuilder.length() - 1).toString();
    }
    return null;
  }
  
  public static String a(boolean... paramVarArgs)
  {
    return String.valueOf(b(paramVarArgs));
  }
  
  private static Map<String, String> a(OAuthToken paramOAuthToken, String paramString)
  {
    paramString = be.a(paramString);
    r localR = r.b();
    if (paramOAuthToken != null) {
      localR.b("Authorization", new x(paramOAuthToken).a(HttpOperation.RequestMethod.a, paramString, null, 0L));
    }
    return (Map)localR.q();
  }
  
  private static void a(Context paramContext, Intent paramIntent, @StringRes int paramInt)
  {
    List localList = MutableList.a();
    a(paramContext, localList, paramIntent, null);
    if (!localList.isEmpty()) {
      paramIntent = Intent.createChooser((Intent)localList.remove(localList.size() - 1), paramContext.getString(paramInt)).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])localList.toArray(new Parcelable[localList.size()]));
    }
    try
    {
      paramContext.startActivity(paramIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext) {}
  }
  
  public static void a(Context paramContext, Uri paramUri)
  {
    a(paramContext, new Intent("android.intent.action.VIEW", paramUri), awd.intent_chooser_title);
  }
  
  public static void a(Context paramContext, Tweet paramTweet, boolean paramBoolean)
  {
    a(paramContext, new com.twitter.model.core.ao(paramTweet), paramBoolean);
  }
  
  public static void a(Context paramContext, com.twitter.model.core.ao paramAo, boolean paramBoolean)
  {
    String str = paramContext.getString(awd.tweet_share_link, new Object[] { paramAo.d, Long.valueOf(paramAo.e) });
    Object localObject = paramContext.getString(awd.app_download_url);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramContext.getString(awd.tweet_date_format), Locale.getDefault());
    ao localAo = new ao();
    Iterator localIterator = ao.b.iterator();
    int i;
    while (localIterator.hasNext())
    {
      i = ((Integer)localIterator.next()).intValue();
      localAo.a(i, paramContext.getString(awd.tweets_share_subject_long_format, new Object[] { paramAo.c, paramAo.d }), paramContext.getString(awd.tweets_share_long_format, new Object[] { paramAo.c, paramAo.d, localSimpleDateFormat.format(Long.valueOf(paramAo.i)), paramAo.f, ao.a(str, i), ao.a((String)localObject, 13) }));
    }
    localObject = ao.a.iterator();
    while (((Iterator)localObject).hasNext())
    {
      i = ((Integer)((Iterator)localObject).next()).intValue();
      localAo.a(i, paramContext.getString(awd.tweets_share_short_format, new Object[] { paramAo.d, ao.a(str, i) }));
    }
    localAo.a(paramContext, paramAo, paramBoolean);
  }
  
  public static void a(Context paramContext, y paramY, boolean paramBoolean)
  {
    ao localAo = new ao();
    Iterator localIterator = ao.b.iterator();
    while (localIterator.hasNext()) {
      localAo.a(((Integer)localIterator.next()).intValue(), paramY.c, paramY.n);
    }
    localIterator = ao.a.iterator();
    while (localIterator.hasNext()) {
      localAo.a(((Integer)localIterator.next()).intValue(), paramY.n);
    }
    localAo.a(paramContext, null, paramBoolean);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    d.a(paramContext, paramContext.getString(awd.app_name), paramString);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    int i;
    if ((!ay.a(paramString1)) && (paramString1.charAt(0) == '#')) {
      i = 1;
    }
    for (;;)
    {
      String str = paramString1;
      if (i != 0) {
        str = paramString1.substring(1);
      }
      try
      {
        paramString1 = URLEncoder.encode(str, "utf-8").replaceAll("\\+", "%20");
        str = paramContext.getString(awd.app_download_url);
        if (i != 0) {}
        ao localAo;
        for (paramString1 = paramContext.getString(awd.search_hashtag_share_link, new Object[] { paramString1 });; paramString1 = paramContext.getString(awd.search_share_link, new Object[] { paramString1 }))
        {
          localAo = new ao();
          Iterator localIterator = ao.b.iterator();
          while (localIterator.hasNext())
          {
            i = ((Integer)localIterator.next()).intValue();
            localAo.a(i, paramContext.getString(awd.search_share_subject_long_format, new Object[] { paramString2 }), paramContext.getString(awd.search_share_long_format, new Object[] { paramString2, ao.a(paramString1, i), ao.a(str, 13) }));
          }
          i = 0;
          break;
        }
        paramString2 = ao.a.iterator();
        while (paramString2.hasNext())
        {
          i = ((Integer)paramString2.next()).intValue();
          localAo.a(i, paramContext.getString(awd.search_share_short_format, new Object[] { ao.a(paramString1, i) }));
        }
        localAo.a(paramContext, null, true);
        return;
      }
      catch (UnsupportedEncodingException paramContext) {}
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    String str1 = paramContext.getString(awd.user_share_link, new Object[] { paramString2 });
    String str2 = paramContext.getString(awd.app_download_url);
    ao localAo = new ao();
    Iterator localIterator = ao.b.iterator();
    int i;
    while (localIterator.hasNext())
    {
      i = ((Integer)localIterator.next()).intValue();
      localAo.a(i, paramContext.getString(awd.user_share_subject_long_format, new Object[] { paramString1, paramString2 }), paramContext.getString(awd.user_share_long_format, new Object[] { paramString1, paramString2, paramString3, ao.a(str1, i), ao.a(str2, 13) }));
    }
    paramString3 = ao.a.iterator();
    while (paramString3.hasNext())
    {
      i = ((Integer)paramString3.next()).intValue();
      localAo.a(i, paramContext.getString(awd.user_share_short_format, new Object[] { paramString1, paramString2, ao.a(str1, i) }));
    }
    localAo.a(paramContext, null, true);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    paramString3 = paramContext.getString(awd.list_share_link, new Object[] { paramString2, paramString3 });
    String str = paramContext.getString(awd.app_download_url);
    ao localAo = new ao();
    Iterator localIterator = ao.b.iterator();
    int i;
    while (localIterator.hasNext())
    {
      i = ((Integer)localIterator.next()).intValue();
      localAo.a(i, paramContext.getString(awd.list_share_subject_long_format, new Object[] { paramString1, paramString2 }), paramContext.getString(awd.list_share_long_format, new Object[] { paramString4, paramString1, paramString5, ao.a(paramString3, i), ao.a(str, 13) }));
    }
    paramString1 = ao.a.iterator();
    while (paramString1.hasNext())
    {
      i = ((Integer)paramString1.next()).intValue();
      localAo.a(i, paramContext.getString(awd.list_share_short_format, new Object[] { paramString4, paramString2, ao.a(paramString3, i) }));
    }
    localAo.a(paramContext, null, true);
  }
  
  public static void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    a(paramContext, paramString, paramBoolean, null);
  }
  
  public static void a(Context paramContext, String paramString, boolean paramBoolean, IntentSender paramIntentSender)
  {
    ao localAo = new ao();
    localAo.a(1, " ", "\n" + paramString);
    localAo.a(1, "\n" + paramString);
    if (paramBoolean) {
      localAo.a(true);
    }
    localAo.a(paramContext, null, true, paramIntentSender);
  }
  
  static void a(Context paramContext, List<Intent> paramList, Intent paramIntent, Set<ComponentName> paramSet)
  {
    Object localObject2 = paramContext.getPackageManager();
    Object localObject1 = ((PackageManager)localObject2).queryIntentActivities(paramIntent, 0);
    if (localObject1 != null)
    {
      Collections.sort((List)localObject1, new ResolveInfo.DisplayNameComparator((PackageManager)localObject2));
      paramContext = paramContext.getPackageName();
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ResolveInfo)((Iterator)localObject1).next();
        String str = ((ResolveInfo)localObject2).activityInfo.packageName;
        ComponentName localComponentName = new ComponentName(str, ((ResolveInfo)localObject2).activityInfo.name);
        if ((!str.equals(paramContext)) && ((paramSet == null) || (paramSet.add(localComponentName)))) {
          paramList.add(new Intent(paramIntent).setPackage(str).setComponent(new ComponentName(str, ((ResolveInfo)localObject2).activityInfo.name)));
        }
      }
    }
  }
  
  @Deprecated
  public static void a(ListPreference paramListPreference, String paramString)
  {
    CharSequence[] arrayOfCharSequence = paramListPreference.getEntries();
    int i = paramListPreference.findIndexOfValue(paramString);
    if (i != -1) {
      paramListPreference.setSummary(arrayOfCharSequence[i]);
    }
  }
  
  public static void a(Editable paramEditable, Object paramObject, String paramString, boolean paramBoolean)
  {
    int i = paramEditable.getSpanStart(paramObject);
    int j = paramEditable.getSpanEnd(paramObject);
    if ((i < 0) || (j < i)) {}
    do
    {
      return;
      paramEditable.removeSpan(paramObject);
      paramEditable.replace(i, j, paramString);
    } while ((!paramBoolean) || (paramString.isEmpty()));
    paramEditable.setSpan(paramObject, i, paramString.length() + i, 33);
  }
  
  public static void a(WebView paramWebView, String paramString, OAuthToken paramOAuthToken)
  {
    a(paramWebView, paramString, null, paramOAuthToken);
  }
  
  public static void a(WebView paramWebView, String paramString, Map<String, String> paramMap)
  {
    paramWebView.loadUrl(paramString, paramMap);
  }
  
  public static void a(WebView paramWebView, String paramString, Map<String, String> paramMap, OAuthToken paramOAuthToken)
  {
    paramOAuthToken = a(paramOAuthToken, paramString);
    if (paramMap != null) {
      paramOAuthToken.putAll(paramMap);
    }
    a(paramWebView, paramString, paramOAuthToken);
  }
  
  public static void a(WebView paramWebView, String paramString, byte[] paramArrayOfByte)
  {
    paramWebView.postUrl(paramString, paramArrayOfByte);
  }
  
  public static boolean a()
  {
    return a(Build.VERSION.SDK_INT);
  }
  
  public static boolean a(int paramInt)
  {
    return paramInt >= 16;
  }
  
  public static boolean a(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = AccountManager.get(paramContext).getAccountsByType("com.google");
    int j = paramContext.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramContext[i].name.endsWith("@twitter.com")) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean a(Context paramContext, boolean paramBoolean, String paramString)
  {
    return (!paramBoolean) || (a.a() > 0) || (paramString.endsWith("@twitter.com"));
  }
  
  public static long b(boolean... paramVarArgs)
  {
    long l1 = 0L;
    int i = 0;
    while (i < paramVarArgs.length)
    {
      long l2 = l1;
      if (paramVarArgs[i] != 0) {
        l2 = l1 + (1 << i);
      }
      i += 1;
      l1 = l2;
    }
    return l1;
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    String str = paramContext.getString(awd.app_download_url);
    ao localAo = new ao();
    Iterator localIterator = ao.b.iterator();
    int i;
    while (localIterator.hasNext())
    {
      i = ((Integer)localIterator.next()).intValue();
      localAo.a(i, paramContext.getString(awd.timeline_share_subject_long_format, new Object[] { paramString1, paramString2 }), paramContext.getString(awd.timeline_share_long_format, new Object[] { paramString3, paramString1, paramString2, paramString4, ao.a(paramString5, i), ao.a(str, 13) }));
    }
    paramString1 = ao.a.iterator();
    while (paramString1.hasNext())
    {
      i = ((Integer)paramString1.next()).intValue();
      localAo.a(i, paramContext.getString(awd.timeline_share_short_format, new Object[] { paramString3, paramString2, ao.a(paramString5, i) }));
    }
    localAo.a(paramContext, null, true);
  }
  
  public static boolean b(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1;
    if (!alt.m().l()) {
      bool1 = true;
    }
    String str;
    do
    {
      do
      {
        return bool1;
        str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
        bool1 = bool2;
      } while (str == null);
      str = t.a(str);
      bool1 = bool2;
    } while (str == null);
    return Arrays.asList(paramContext.getResources().getStringArray(avt.whitelisted_device_ids)).contains(str);
  }
  
  public static boolean b(String paramString)
  {
    return (paramString != null) && (paramString.startsWith("video/mp4")) && (paramString.contains("avc1.42E0"));
  }
  
  public static String c(String paramString)
  {
    while (paramString.matches("(?i).*twitter.*")) {
      paramString = paramString.replaceAll("(?i)twitter", "");
    }
    return paramString;
  }
  
  public static List<Pair<String, String>> c(Context paramContext)
  {
    int i = 0;
    n localN = n.b();
    Object localObject = n.b();
    paramContext = paramContext.getPackageManager();
    Iterator localIterator = paramContext.getInstalledApplications(0).iterator();
    ApplicationInfo localApplicationInfo;
    String str;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localApplicationInfo = (ApplicationInfo)localIterator.next();
      str = localApplicationInfo.packageName;
    } while (paramContext.getLaunchIntentForPackage(str) == null);
    label243:
    for (;;)
    {
      try
      {
        l = paramContext.getPackageInfo(str, 0).firstInstallTime;
        if (l != 0L) {
          break label243;
        }
        l = new File(localApplicationInfo.sourceDir).lastModified();
        if (l <= 0L) {
          break;
        }
        localN.c(str);
        ((n)localObject).c(String.valueOf(l));
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        long l = 0L;
        continue;
      }
      paramContext = (List)localN.q();
      localObject = (List)((n)localObject).q();
      int j = paramContext.size();
      localN = n.b();
      while (i < j)
      {
        int k = Math.min(j, i + 100);
        localN.c(new Pair(ay.a(",", paramContext.subList(i, k)), ay.a(",", ((List)localObject).subList(i, k))));
        i += k;
      }
      return (List)localN.q();
    }
  }
  
  public static boolean d(String paramString)
  {
    Object localObject1 = alx.c("vine_video_hosts");
    if ((paramString != null) && (!((List)localObject1).isEmpty()))
    {
      paramString = Uri.parse(paramString).getHost();
      if ((paramString != null) && (!paramString.isEmpty()))
      {
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = ((Iterator)localObject1).next();
          if (((localObject2 instanceof String)) && (paramString.equalsIgnoreCase((String)localObject2))) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
