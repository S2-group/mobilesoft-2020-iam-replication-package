package com.nytimes.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.nytimes.android.activity.controller.sectionfront.SectionFrontModel.SavedSectionTab;
import com.nytimes.android.activity.widget.NYTWidgetModel;
import com.nytimes.android.persistence.SaveOrigin;
import com.nytimes.android.persistence.Section;
import com.nytimes.android.persistence.dao.SearchManager;
import com.nytimes.android.persistence.dao.SearchManager.RemoteSearchEngine;
import com.nytimes.android.util.n;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class c
{
  private static final c a = new c();
  private static final String b = c.class.getSimpleName();
  private static Context d = NYTApplication.c;
  private final Set<String> c = new HashSet();
  
  private c() {}
  
  public static c a()
  {
    try
    {
      c localC = a;
      return localC;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static c a(Context paramContext)
  {
    try
    {
      c localC = a;
      d = paramContext;
      paramContext = a;
      return paramContext;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  private void a(SharedPreferences paramSharedPreferences, Map<String, SaveOrigin> paramMap)
  {
    try
    {
      paramSharedPreferences = paramSharedPreferences.edit().clear();
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        paramSharedPreferences.putInt(str, ((SaveOrigin)paramMap.get(str)).getCode());
      }
      paramSharedPreferences.commit();
    }
    finally {}
  }
  
  private String aX()
  {
    return b().getString("subbannerCCSF", "");
  }
  
  private String aY()
  {
    return b().getString("subbannerCCAF", "");
  }
  
  private String aZ()
  {
    return b().getString("messageCC", "");
  }
  
  private Map<String, SaveOrigin> b(SharedPreferences paramSharedPreferences)
  {
    paramSharedPreferences = paramSharedPreferences.getAll();
    HashMap localHashMap = new HashMap(paramSharedPreferences.size());
    Iterator localIterator = paramSharedPreferences.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localHashMap.put(str, SaveOrigin.fromCode(((Integer)paramSharedPreferences.get(str)).intValue()));
    }
    return Collections.synchronizedMap(localHashMap);
  }
  
  private boolean ba()
  {
    return b().getBoolean("HAS_DISPLAYED_EDITION_REMINDER", false);
  }
  
  private void p(String paramString)
  {
    SharedPreferences.Editor localEditor = b().edit();
    if (paramString == null) {
      localEditor.remove("lastCC");
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putString("lastCC", paramString);
    }
  }
  
  public Set<String> A()
  {
    return a(a("legacySavedUrlSet"));
  }
  
  public Set<String> B()
  {
    return a(a("pendingUnsavedArticleURLSet"));
  }
  
  public List<NYTWidgetModel> C()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = x().iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(d(((Integer)localIterator.next()).intValue()));
    }
    return localArrayList;
  }
  
  @Deprecated
  public int D()
  {
    return b().getInt("mFontSelection", 2);
  }
  
  public float E()
  {
    return b().getFloat("fontSelection", 0.0F);
  }
  
  public Set<String> F()
  {
    return a(a("uptAdsQueue"));
  }
  
  public Set<String> G()
  {
    return a(a("uptEventsQueue"));
  }
  
  public void H()
  {
    b().edit().remove("commentsName").remove("commentsLocation").commit();
  }
  
  public String I()
  {
    return b().getString("commentsName", null);
  }
  
  public String J()
  {
    return b().getString("commentsLocation", null);
  }
  
  public long K()
  {
    return b().getLong("LAST_IMAGE_STORAGE_CLEANUP", -1L);
  }
  
  public boolean L()
  {
    return b().getBoolean("IS_LIVE_BLOG_ENABLED", false);
  }
  
  public boolean M()
  {
    return b().getBoolean("localyticsFileLogEnabled", false);
  }
  
  public boolean N()
  {
    return b().getBoolean("localyticsShowHUD", false);
  }
  
  public boolean O()
  {
    return b().getBoolean("PREF_SUPPORT_VOICEOVER", false);
  }
  
  public String P()
  {
    return a("com.nytimes.android_preferences").getString("usevoiceovercloud", "Always");
  }
  
  public boolean Q()
  {
    return b().getBoolean("SECTION_TOGGLED_TO_SECTION", true);
  }
  
  public List<String> R()
  {
    String str = b().getString("LAST_VISITED_BLOGS", null);
    if (str == null) {
      return new ArrayList();
    }
    return new ArrayList(Arrays.asList(str.split(",")));
  }
  
  public boolean S()
  {
    return b().getBoolean("IS_OFFLINE_VOICEOVER_ENABLED", false);
  }
  
  public Uri T()
  {
    String str = a("com.nytimes.android_preferences").getString("bnaRingtone", null);
    if (str == null) {
      return null;
    }
    return Uri.parse(str);
  }
  
  public String U()
  {
    return a("com.nytimes.android_preferences").getString("bnaVibrate", NYTApplication.c.getString(2131558785));
  }
  
  public boolean V()
  {
    return b().getBoolean("IS_UPDATE_ALL_SAVED", false);
  }
  
  public boolean W()
  {
    return a("com.nytimes.android_preferences").getBoolean("bnaPopup", false);
  }
  
  public int X()
  {
    return b().getInt("TABLET_TOP_AD_IMPRESSIONS_PER_SESSION", 5);
  }
  
  public int Y()
  {
    return b().getInt("TABLET_HALF_PAGE_AD_IMPRESSIONS_PER_SESSION", 5);
  }
  
  public int Z()
  {
    return b().getInt("TABLET_HALF_PAGE_AD_BLOCKS_LIMIT", 1);
  }
  
  public SharedPreferences a(String paramString)
  {
    return d.getSharedPreferences(paramString, 0);
  }
  
  public Set<String> a(SharedPreferences paramSharedPreferences)
  {
    return Collections.synchronizedSet(new HashSet(Arrays.asList((String[])paramSharedPreferences.getAll().keySet().toArray(new String[0]))));
  }
  
  public void a(float paramFloat)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putFloat("fontSelection", paramFloat);
    localEditor.commit();
  }
  
  public void a(int paramInt)
  {
    SharedPreferences.Editor localEditor = a("com.nytimes.android_preferences").edit();
    localEditor.putString("selectEdition", Integer.toString(paramInt));
    localEditor.commit();
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putInt("UpdateFrequencyTime", paramInt1 * 60 + paramInt2);
    localEditor.commit();
  }
  
  public void a(int paramInt, NYTWidgetModel paramNYTWidgetModel)
  {
    try
    {
      a("ny_times_widget").edit().putString("" + paramInt, paramNYTWidgetModel.toJsonString()).commit();
      return;
    }
    catch (JsonGenerationException localJsonGenerationException)
    {
      localJsonGenerationException.printStackTrace();
      Log.e(b, "could not serialize to json: " + localJsonGenerationException.getMessage() + " : for " + paramNYTWidgetModel.getFeedUri() + " | " + paramNYTWidgetModel.getPosition());
      throw new RuntimeException(localJsonGenerationException);
    }
  }
  
  public void a(long paramLong)
  {
    b().edit().putLong("savedLastUpdated", paramLong).commit();
  }
  
  public void a(SharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener)
  {
    b().registerOnSharedPreferenceChangeListener(paramOnSharedPreferenceChangeListener);
  }
  
  public void a(SharedPreferences paramSharedPreferences, Set<String> paramSet)
  {
    try
    {
      paramSharedPreferences = paramSharedPreferences.edit().clear();
      Iterator localIterator = paramSet.iterator();
      while (localIterator.hasNext()) {
        paramSharedPreferences.putInt((String)localIterator.next(), 0);
      }
      paramSharedPreferences.commit();
    }
    finally {}
  }
  
  public void a(SectionFrontModel.SavedSectionTab paramSavedSectionTab)
  {
    b().edit().putString("savedSectionActiveTab", paramSavedSectionTab.name()).commit();
  }
  
  public void a(Section paramSection)
  {
    b().edit().putString("CurrentSectionName", paramSection.getTitle()).putString("CurrentSectionFeedUri", paramSection.getFeedUri()).commit();
  }
  
  public void a(String paramString1, String paramString2)
  {
    b().edit().putString("commentsName", paramString1).putString("commentsLocation", paramString2).commit();
  }
  
  public void a(String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b().edit();
    paramString = String.format(paramString, new Object[0]);
    if (paramBoolean) {
      localEditor.putBoolean(paramString, true);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.remove(paramString);
    }
  }
  
  public void a(List<Section> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      c(((Section)paramList.next()).getFeedUri());
    }
  }
  
  public void a(Map<String, SaveOrigin> paramMap)
  {
    a(a("savedArticleURLSet"), paramMap);
  }
  
  public void a(Set<String> paramSet)
  {
    a(a("pendingSavedArticleURLSet"), paramSet);
  }
  
  public void a(boolean paramBoolean)
  {
    a("com.nytimes.android_preferences").edit().putBoolean("isEditionToggled", paramBoolean).commit();
  }
  
  public void a(boolean paramBoolean, int paramInt)
  {
    Object localObject = "";
    if (paramInt == 1)
    {
      localObject = NYTApplication.c;
      if (paramBoolean)
      {
        paramInt = 2131558447;
        localObject = ((NYTApplication)localObject).getString(paramInt);
      }
    }
    do
    {
      p((String)localObject);
      return;
      paramInt = 2131558560;
      break;
      if (paramInt == 2)
      {
        localObject = NYTApplication.c;
        if (paramBoolean) {}
        for (paramInt = 2131558448;; paramInt = 2131558561)
        {
          localObject = ((NYTApplication)localObject).getString(paramInt);
          break;
        }
      }
    } while (paramInt != -1);
    localObject = NYTApplication.c;
    if (paramBoolean) {}
    for (paramInt = 2131558449;; paramInt = 2131558563)
    {
      localObject = ((NYTApplication)localObject).getString(paramInt);
      break;
    }
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    String str;
    if (paramBoolean1) {
      str = NYTApplication.c.getString(2131558566);
    }
    for (;;)
    {
      p(str);
      return;
      if (paramBoolean2) {
        str = NYTApplication.c.getString(2131558568);
      } else {
        str = NYTApplication.c.getString(2131558567);
      }
    }
  }
  
  public boolean aA()
  {
    return a("com.nytimes.android_preferences").getBoolean("bnaSubscribed", false);
  }
  
  public boolean aB()
  {
    return a("com.nytimes.android_preferences").getBoolean("drnSubscribed", true);
  }
  
  public long aC()
  {
    return b().getLong("LAST_DRN_AUTO", 0L);
  }
  
  public long aD()
  {
    return b().getLong("LAST_DRN_MANUAL", 0L);
  }
  
  public void aE()
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("userWasPromptedToSubscribeToBNAsSinceLastUpdate", true);
    localEditor.commit();
  }
  
  public void aF()
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("userWasPromptedThatAppIsOnlyInEnglish", true);
    localEditor.commit();
  }
  
  public String aG()
  {
    return b().getString("deviceRegistrationId", null);
  }
  
  public String aH()
  {
    return b().getString("admRegistrationId", null);
  }
  
  public boolean aI()
  {
    return b().getBoolean("ADMavailable", false);
  }
  
  public boolean aJ()
  {
    return b().getBoolean("isRegisteredWithServer", false);
  }
  
  public void aK()
  {
    if (b().contains("favToggle")) {
      b().edit().remove("favToggle").commit();
    }
  }
  
  public boolean aL()
  {
    return Integer.valueOf(a("com.nytimes.android_preferences").getString("backgroundUpdateOptions", "1")).intValue() == 1;
  }
  
  public void aM()
  {
    if (b().contains("altContentFeedBaseUrl")) {
      b().edit().remove("altContentFeedBaseUrl").commit();
    }
  }
  
  public String aN()
  {
    return b().getString("altContentFeedBaseUrl", null);
  }
  
  public boolean aO()
  {
    return b().getBoolean("noGP", false);
  }
  
  public boolean aP()
  {
    return o("ssdeal");
  }
  
  public void aQ()
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("HAS_DISPLAYED_EDITION_REMINDER", true);
    localEditor.commit();
  }
  
  public boolean aR()
  {
    boolean bool = false;
    if (!ba())
    {
      aQ();
      bool = true;
    }
    return bool;
  }
  
  public boolean aS()
  {
    return b().getBoolean("HAS_SHOWN_EDITION_NOTIFICATION", false);
  }
  
  public void aT()
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("HAS_SHOWN_EDITION_NOTIFICATION", true);
    localEditor.commit();
  }
  
  public boolean aU()
  {
    return b().getBoolean("ppShow", false);
  }
  
  public Set<String> aV()
  {
    return b().getStringSet("ppExclude", this.c);
  }
  
  public int aW()
  {
    return b().getInt("ppRank", 10);
  }
  
  public boolean aa()
  {
    return b().getBoolean("showTapToLaunchInteractiveAsMainMedia", true);
  }
  
  public boolean ab()
  {
    return b().getBoolean("hideHalfPageAd", false);
  }
  
  public String ac()
  {
    return b().getString("subbannerName", "sub_banner");
  }
  
  public String ad()
  {
    return b().getString("subbannerUrl", "");
  }
  
  public void ae()
  {
    p(aX());
  }
  
  public void af()
  {
    p(aY());
  }
  
  public void ag()
  {
    p(NYTApplication.c.getString(2131558562));
  }
  
  public void ah()
  {
    p(NYTApplication.c.getString(2131558565));
  }
  
  public void ai()
  {
    p(aZ());
  }
  
  public String aj()
  {
    return b().getString("lastCC", "");
  }
  
  public long ak()
  {
    return b().getLong("APP_LAST_PAUSE_TIME", System.currentTimeMillis());
  }
  
  public int al()
  {
    return b().getInt("articleSwipeCount", 0);
  }
  
  public boolean am()
  {
    return a("com.nytimes.android_preferences").getBoolean("nightModeOn", false);
  }
  
  public boolean an()
  {
    return b().getBoolean("migratedEntitlements", false);
  }
  
  public void ao()
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("migratedEntitlements", true);
    localEditor.commit();
  }
  
  public boolean ap()
  {
    Object localObject;
    if (!b().contains("hasGCS"))
    {
      localObject = d.getPackageManager().getInstalledPackages(0).iterator();
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
      } while (!((PackageInfo)((Iterator)localObject).next()).packageName.equals("com.google.android.gsf"));
    }
    for (boolean bool = true;; bool = false)
    {
      localObject = b().edit();
      ((SharedPreferences.Editor)localObject).putBoolean("hasGCS", bool);
      ((SharedPreferences.Editor)localObject).commit();
      return b().getBoolean("hasGCS", false);
    }
  }
  
  public SearchManager.RemoteSearchEngine aq()
  {
    SearchManager.RemoteSearchEngine localRemoteSearchEngine = SearchManager.RemoteSearchEngine.CSE;
    String str = a("com.nytimes.android_preferences").getString("searchEngine", null);
    if (str == null) {}
    do
    {
      return localRemoteSearchEngine;
      if (str.equalsIgnoreCase(SearchManager.RemoteSearchEngine.CSE.name())) {
        localRemoteSearchEngine = SearchManager.RemoteSearchEngine.CSE;
      }
      if (str.equalsIgnoreCase(SearchManager.RemoteSearchEngine.ADD.name())) {
        localRemoteSearchEngine = SearchManager.RemoteSearchEngine.ADD;
      }
    } while (!str.equalsIgnoreCase(SearchManager.RemoteSearchEngine.OFF.name()));
    return SearchManager.RemoteSearchEngine.OFF;
  }
  
  public boolean ar()
  {
    return b().getBoolean("mandUpt", false);
  }
  
  public long as()
  {
    return b().getLong("DEVICE_CLOCK_OFFSET", 0L);
  }
  
  public boolean at()
  {
    return b().getBoolean("METRICS_HTTP_CALL", false);
  }
  
  public boolean au()
  {
    return b().getBoolean("METRICS_REFRESH", false);
  }
  
  public int av()
  {
    return b().getInt("refreshAlarmJitter", 0);
  }
  
  public String aw()
  {
    return b().getString("METRIICS_FILTER", "");
  }
  
  public float ax()
  {
    return b().getFloat("METRICS_SAMPLING_RATE", 0.0F);
  }
  
  public boolean ay()
  {
    return b().getBoolean("userWasPromptedToSubscribeToBNAsSinceLastUpdate", false);
  }
  
  public boolean az()
  {
    return b().getBoolean("userWasPromptedThatAppIsOnlyInEnglish", false);
  }
  
  public SharedPreferences b()
  {
    return a("NYTIMES_PREFS");
  }
  
  public void b(float paramFloat)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putFloat("METRICS_SAMPLING_RATE", paramFloat);
    localEditor.commit();
  }
  
  public void b(int paramInt)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putInt("UpdateFrequency", paramInt);
    localEditor.commit();
  }
  
  public void b(long paramLong)
  {
    b().edit().putLong("LAST_ICON_MODIFIED", paramLong).commit();
  }
  
  public void b(SharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener)
  {
    b().unregisterOnSharedPreferenceChangeListener(paramOnSharedPreferenceChangeListener);
  }
  
  public void b(String paramString)
  {
    b().edit().putString("CurrentArticleUrl", paramString).commit();
  }
  
  public void b(Set<String> paramSet)
  {
    a(a("legacySavedUrlSet"), paramSet);
  }
  
  public void b(boolean paramBoolean)
  {
    b().edit().putBoolean("ReadingListMigrated", paramBoolean).commit();
  }
  
  public SharedPreferences c()
  {
    return a("NYTIMES_BLOGCATS");
  }
  
  public void c(int paramInt)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putInt("UpdateWidgetFrequency", paramInt);
    localEditor.commit();
  }
  
  public void c(long paramLong)
  {
    b().edit().putLong("LAST_SUB_BAN_MODIFIED", paramLong).commit();
  }
  
  public void c(String paramString)
  {
    d().edit().remove(paramString).commit();
  }
  
  public void c(Set<String> paramSet)
  {
    a(a("pendingUnsavedArticleURLSet"), paramSet);
  }
  
  public void c(boolean paramBoolean)
  {
    b().edit().putBoolean("IS_LIVE_BLOG_ENABLED", paramBoolean).commit();
  }
  
  public SharedPreferences d()
  {
    return a("NYTIMES_FAVSECTIONS");
  }
  
  public NYTWidgetModel d(int paramInt)
  {
    Object localObject = a("ny_times_widget").getString("" + paramInt, null);
    if (localObject == null) {
      return null;
    }
    try
    {
      localObject = NYTWidgetModel.fromJsonString((String)localObject);
      return localObject;
    }
    catch (JsonParseException localJsonParseException)
    {
      localJsonParseException.printStackTrace();
      Log.e(b, "could not deserialize from json: " + localJsonParseException.getMessage() + " : for widget " + paramInt);
    }
    return null;
  }
  
  public void d(long paramLong)
  {
    b().edit().putLong("LAST_COMMENTS_STATUS_CHECKED", paramLong).commit();
  }
  
  public void d(String paramString)
  {
    d().edit().putInt(paramString, 0).commit();
  }
  
  public void d(Set<String> paramSet)
  {
    a(a("uptAdsQueue"), paramSet);
  }
  
  public void d(boolean paramBoolean)
  {
    b().edit().putBoolean("fileLogEnabled", paramBoolean).commit();
  }
  
  public List<String> e()
  {
    return Arrays.asList((String[])d().getAll().keySet().toArray(new String[0]));
  }
  
  public void e(int paramInt)
  {
    a("ny_times_widget").edit().remove(paramInt + "").commit();
  }
  
  public void e(long paramLong)
  {
    b().edit().putLong("LAST_IMAGE_STORAGE_CLEANUP", paramLong).commit();
  }
  
  public void e(String paramString)
  {
    Object localObject = R();
    ((List)localObject).remove(paramString);
    ((List)localObject).add(0, paramString);
    paramString = "";
    int i = 0;
    while ((i < 5) && (i < ((List)localObject).size()))
    {
      paramString = paramString + (String)((List)localObject).get(i) + ",";
      i += 1;
    }
    localObject = b().edit();
    ((SharedPreferences.Editor)localObject).putString("LAST_VISITED_BLOGS", paramString);
    ((SharedPreferences.Editor)localObject).commit();
  }
  
  public void e(Set<String> paramSet)
  {
    a(a("uptEventsQueue"), paramSet);
  }
  
  public void e(boolean paramBoolean)
  {
    b().edit().putBoolean("localyticsFileLogEnabled", paramBoolean).commit();
  }
  
  public SectionFrontModel.SavedSectionTab f()
  {
    String str = b().getString("savedSectionActiveTab", null);
    if (str == null) {
      return SectionFrontModel.SavedSectionTab.ARTICLES;
    }
    return SectionFrontModel.SavedSectionTab.valueOf(str.toUpperCase(Locale.US));
  }
  
  public void f(int paramInt)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putInt("TABLET_TOP_AD_IMPRESSIONS_PER_SESSION", paramInt);
    localEditor.commit();
  }
  
  public void f(long paramLong)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putLong("APP_LAST_PAUSE_TIME", paramLong);
    localEditor.commit();
  }
  
  public void f(String paramString)
  {
    SharedPreferences.Editor localEditor = b().edit();
    if (paramString == null) {
      localEditor.remove("subbannerName");
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putString("subbannerName", paramString);
    }
  }
  
  public void f(Set<String> paramSet)
  {
    b().edit().putStringSet("ppExclude", paramSet).commit();
  }
  
  public void f(boolean paramBoolean)
  {
    b().edit().putBoolean("localyticsShowHUD", paramBoolean).commit();
  }
  
  public int g()
  {
    return a("NYTIMES_FAVSECTIONS").getAll().size();
  }
  
  public void g(int paramInt)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putInt("TABLET_HALF_PAGE_AD_IMPRESSIONS_PER_SESSION", paramInt);
    localEditor.commit();
  }
  
  public void g(long paramLong)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putLong("DEVICE_CLOCK_OFFSET", paramLong);
    localEditor.commit();
  }
  
  public void g(String paramString)
  {
    SharedPreferences.Editor localEditor = b().edit();
    if (paramString == null) {
      localEditor.remove("subbannerUrl");
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putString("subbannerUrl", paramString);
    }
  }
  
  public void g(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("PREF_SUPPORT_VOICEOVER", paramBoolean);
    localEditor.commit();
  }
  
  public int h()
  {
    n localN = n.a();
    Locale localLocale = d.getResources().getConfiguration().locale;
    return Integer.valueOf(a("com.nytimes.android_preferences").getString("selectEdition", Integer.toString(localN.a(localLocale)))).intValue();
  }
  
  public void h(int paramInt)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putInt("TABLET_HALF_PAGE_AD_BLOCKS_LIMIT", paramInt);
    localEditor.commit();
  }
  
  public void h(long paramLong)
  {
    b().edit().putLong("LAST_DRN_AUTO", paramLong).commit();
  }
  
  public void h(String paramString)
  {
    SharedPreferences.Editor localEditor = b().edit();
    if (paramString == null) {
      localEditor.remove("subbannerCCSF");
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putString("subbannerCCSF", paramString);
    }
  }
  
  public void h(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("SECTION_TOGGLED_TO_SECTION", paramBoolean);
    localEditor.commit();
  }
  
  public void i(int paramInt)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putInt("articleSwipeCount", paramInt);
    localEditor.commit();
  }
  
  public void i(long paramLong)
  {
    b().edit().putLong("LAST_DRN_MANUAL", paramLong).commit();
  }
  
  public void i(String paramString)
  {
    SharedPreferences.Editor localEditor = b().edit();
    if (paramString == null) {
      localEditor.remove("subbannerCCAF");
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putString("subbannerCCAF", paramString);
    }
  }
  
  public void i(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("IS_OFFLINE_VOICEOVER_ENABLED", paramBoolean);
    localEditor.commit();
  }
  
  public boolean i()
  {
    return a("com.nytimes.android_preferences").getBoolean("isEditionToggled", false);
  }
  
  public int j()
  {
    p();
    Object localObject = d.getResources();
    localObject = localObject.getStringArray(2131099664)[localObject.getInteger(2131492868)];
    return b().getInt("UpdateWidgetFrequency", Integer.parseInt((String)localObject));
  }
  
  public void j(int paramInt)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putInt("refreshAlarmJitter", paramInt);
    localEditor.commit();
  }
  
  public void j(String paramString)
  {
    SharedPreferences.Editor localEditor = b().edit();
    if (paramString == null) {
      localEditor.remove("messageCC");
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putString("messageCC", paramString);
    }
  }
  
  public boolean j(boolean paramBoolean)
  {
    if (b().contains("HQ_VIDEO_PREFERRED")) {
      paramBoolean = b().getBoolean("HQ_VIDEO_PREFERRED", false);
    }
    return paramBoolean;
  }
  
  public void k(int paramInt)
  {
    b().edit().putInt("ppRank", paramInt).commit();
  }
  
  public void k(String paramString)
  {
    if (SearchManager.a(paramString))
    {
      SharedPreferences.Editor localEditor = a("com.nytimes.android_preferences").edit();
      localEditor.putString("searchEngine", paramString);
      localEditor.commit();
    }
  }
  
  public void k(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("HQ_VIDEO_PREFERRED", paramBoolean);
    localEditor.commit();
  }
  
  public int[] k()
  {
    int i = b().getInt("UpdateFrequencyTime", 360);
    return new int[] { i / 60, i % 60 };
  }
  
  public long l()
  {
    return b().getLong("savedLastUpdated", 0L);
  }
  
  public void l(String paramString)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putString("METRIICS_FILTER", paramString);
    localEditor.commit();
  }
  
  public void l(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = a("com.nytimes.android_preferences").edit();
    localEditor.putBoolean("bnaSubscribed", paramBoolean);
    localEditor.commit();
  }
  
  public long m()
  {
    return b().getLong("LAST_ICON_MODIFIED", -1L);
  }
  
  public void m(String paramString)
  {
    a("com.nytimes.android_preferences").edit().putString("backgroundUpdateOptions", paramString).commit();
  }
  
  public void m(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("IS_UPDATE_ALL_SAVED", paramBoolean);
    localEditor.commit();
  }
  
  public long n()
  {
    return b().getLong("LAST_SUB_BAN_MODIFIED", -1L);
  }
  
  public void n(String paramString)
  {
    b().edit().putString("altContentFeedBaseUrl", paramString).commit();
  }
  
  public void n(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("showTapToLaunchInteractiveAsMainMedia", paramBoolean);
    localEditor.commit();
  }
  
  public long o()
  {
    return b().getLong("LAST_COMMENTS_STATUS_CHECKED", -1L);
  }
  
  public void o(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("hideHalfPageAd", paramBoolean);
    localEditor.commit();
  }
  
  public boolean o(String paramString)
  {
    return b().getBoolean(String.format(paramString, new Object[0]), false);
  }
  
  public void p()
  {
    int i = b().getInt("UpdateFrequency", -2342342);
    if ((i != -2342342) && (i == 600000))
    {
      SharedPreferences.Editor localEditor = b().edit();
      localEditor.remove("UpdateFrequency");
      localEditor.commit();
    }
  }
  
  public void p(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 2131558450;; i = 2131558564)
    {
      p(NYTApplication.c.getString(i));
      return;
    }
  }
  
  public int q()
  {
    p();
    Object localObject = d.getResources();
    localObject = localObject.getStringArray(2131099662)[localObject.getInteger(2131492867)];
    return b().getInt("UpdateFrequency", Integer.parseInt((String)localObject));
  }
  
  public void q(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = a("com.nytimes.android_preferences").edit();
    localEditor.putBoolean("nightModeOn", paramBoolean);
    localEditor.commit();
  }
  
  public void r(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("mandUpt", paramBoolean);
    localEditor.commit();
  }
  
  public boolean r()
  {
    return b().getBoolean("ReadingListMigrated", false);
  }
  
  public String s()
  {
    return b().getString("CurrentArticleUrl", null);
  }
  
  public void s(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("METRICS_HTTP_CALL", paramBoolean);
    localEditor.commit();
  }
  
  public Section t()
  {
    Object localObject = b().getString("CurrentSectionFeedUri", Section.getDefaultUri());
    String str = b().getString("CurrentSectionName", "Top Stories");
    localObject = new Section((String)localObject);
    ((Section)localObject).setTitle(str);
    return localObject;
  }
  
  public void t(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b().edit();
    localEditor.putBoolean("METRICS_REFRESH", paramBoolean);
    localEditor.commit();
  }
  
  public Section u()
  {
    Object localObject = b().getString("DRNSectionFeedUri", Section.getDefaultUri());
    String str = b().getString("DRNSectionName", "Top Stories");
    localObject = new Section((String)localObject);
    ((Section)localObject).setTitle(str);
    return localObject;
  }
  
  public void u(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = a("com.nytimes.android_preferences").edit();
    localEditor.putBoolean("drnSubscribed", paramBoolean);
    localEditor.commit();
  }
  
  public void v(boolean paramBoolean)
  {
    b().edit().putBoolean("ADMavailable", paramBoolean).commit();
  }
  
  public boolean v()
  {
    return Integer.valueOf(a("com.nytimes.android_preferences").getString("imagePreDownloadOptions", "1")).intValue() == 1;
  }
  
  public void w(boolean paramBoolean)
  {
    b().edit().putBoolean("isRegisteredWithServer", paramBoolean).commit();
  }
  
  public boolean w()
  {
    int i = Integer.valueOf(a("com.nytimes.android_preferences").getString("imagePreDownloadOptions", "1")).intValue();
    return (i == 2) || (i == 1);
  }
  
  public List<Integer> x()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = a("ny_times_widget").getAll().keySet().iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(Integer.valueOf(Integer.parseInt((String)localIterator.next())));
    }
    return localArrayList;
  }
  
  public void x(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = b().edit();
    if (!paramBoolean) {
      localEditor.remove("noGP");
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putBoolean("noGP", paramBoolean);
    }
  }
  
  public Map<String, SaveOrigin> y()
  {
    return b(a("savedArticleURLSet"));
  }
  
  public void y(boolean paramBoolean)
  {
    b().edit().putBoolean("ppShow", paramBoolean).commit();
  }
  
  public Set<String> z()
  {
    return a(a("pendingSavedArticleURLSet"));
  }
}
