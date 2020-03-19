package com.android.inputmethod.latin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Region;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.InputMethodService.Insets;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.UserDictionary.Words;
import android.support.v4.view.ViewPager;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.SuggestionSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.PrintWriterPrinter;
import android.util.Printer;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.android.inputmethod.keyboard.ArtView;
import com.android.inputmethod.keyboard.ClipBoardView;
import com.android.inputmethod.keyboard.EditPanelView;
import com.android.inputmethod.keyboard.EmojiContainerView;
import com.android.inputmethod.keyboard.EmojiView;
import com.android.inputmethod.keyboard.MainKeyboardView;
import com.android.inputmethod.keyboard.ProximityInfo;
import com.android.inputmethod.keyboard.StickerView;
import com.android.inputmethod.keyboard.StickerView.a;
import com.android.inputmethod.keyboard.TopMenuPopupViewPager;
import com.android.inputmethod.keyboard.h.1;
import com.android.inputmethod.keyboard.internal.af;
import com.android.inputmethod.keyboard.internal.w.b;
import com.android.inputmethod.latin.d.ab;
import com.android.inputmethod.latin.d.ad;
import com.android.inputmethod.latin.d.ad.a;
import com.android.inputmethod.latin.d.ae;
import com.android.inputmethod.latin.d.ai;
import com.android.inputmethod.latin.d.aj;
import com.android.inputmethod.latin.d.i;
import com.android.inputmethod.latin.kkuirearch.utils.d.1;
import com.android.inputmethod.latin.kkuirearch.utils.g.1;
import com.android.inputmethod.latin.kkuirearch.views.TopSuggestionSettingView;
import com.android.inputmethod.latin.personalization.DictionaryDecayBroadcastReciever;
import com.android.inputmethod.latin.settings.SettingsActivity;
import com.android.inputmethod.latin.suggestions.SuggestionStripView;
import com.android.inputmethod.latin.suggestions.SuggestionStripView.a;
import com.android.service.UmengPushHandleService;
import com.facebook.ads.NativeAd;
import com.facebook.appevents.AppEventsLogger;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mavl.dc.DCAgent;
import com.mavl.dc.k;
import com.myandroid.promotion.entity.PromotionHashInfo;
import com.myandroid.promotion.entity.ShuffleIconInfo;
import com.myandroid.widget.pageindicator.ExpandedTabPageIndicator;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import emoji.keyboard.emoticonkeyboard.lockscreen.LockScreenService;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.Header;

public class LatinIME
  extends InputMethodService
  implements ClipboardManager.OnPrimaryClipChangedListener, com.android.inputmethod.keyboard.e, ad.a, SuggestionStripView.a, v.b
{
  public static final String CLIP_LIST_FILE = "clip_text_list_file";
  public static final int CLIP_TEXT_MAX_NUM = 12;
  private static boolean DEBUG = false;
  private static final long DELAY_ONE_HOUR = 3600000L;
  private static final long DELAY_TEN_MIN = 600000L;
  private static final int DELETE_ACCELERATE_AT = 20;
  private static final int EXTENDED_TOUCHABLE_REGION_HEIGHT = 100;
  private static final int GET_SUGGESTED_WORDS_TIMEOUT = 200;
  private static final int GO_TO_SEARCH = 1;
  private static final String KK_ART_PACKAGE_NAME_PREFIX = "com.emojifamily.emoji.keyboard.art.";
  private static final int NOT_A_CURSOR_POSITION = -1;
  private static final int PENDING_IMS_CALLBACK_DURATION = 800;
  private static final int PERIOD_FOR_AUDIO_AND_HAPTIC_FEEDBACK_IN_KEY_REPEAT = 2;
  private static final String PREF_VERSION_TRACK_TIME = "version_track_time";
  private static final int QUICK_PRESS = 200;
  private static final String SCHEME_PACKAGE = "package";
  private static final int SPACE_STATE_DOUBLE = 1;
  private static final int SPACE_STATE_NONE = 0;
  private static final int SPACE_STATE_PHANTOM = 4;
  private static final int SPACE_STATE_SWAP_PUNCTUATION = 2;
  private static final int SPACE_STATE_WEAK = 3;
  private static final int SWITCH_TO_TOP_PANEL = 0;
  private static final String TAG = LatinIME.class.getSimpleName();
  private static final boolean TRACE = false;
  private static final int VOICE = 2;
  public static ArrayList<c> mClipArray = new ArrayList();
  private emoji.keyboard.emoticonkeyboard.b.b mAppWorkAroundsUtils = new emoji.keyboard.emoticonkeyboard.b.b();
  private CompletionInfo[] mApplicationSpecifiedCompletions;
  private Runnable mArtPackageScanner = new Runnable()
  {
    public final void run()
    {
      PackageManager localPackageManager = LatinIME.this.getPackageManager();
      try
      {
        Object localObject3 = localPackageManager.getInstalledPackages(0);
        Object localObject1 = PreferenceManager.getDefaultSharedPreferences(LatinIME.this).getString("pref_art_pacakge_collection", "");
        if (((String)localObject1).isEmpty())
        {
          localObject1 = new LinkedHashMap();
          localObject3 = ((List)localObject3).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject3).next();
            if (localPackageInfo.packageName.contains("com.emojifamily.emoji.keyboard.art."))
            {
              com.android.inputmethod.latin.kkuirearch.extras.a localA = new com.android.inputmethod.latin.kkuirearch.extras.a();
              localA.a = localPackageInfo.packageName;
              localA.b = localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo).toString();
              ((LinkedHashMap)localObject1).put(localA.a, localA);
              Log.d(LatinIME.TAG, "Find Emoji Art package " + localA.a);
              continue;
              PreferenceManager.getDefaultSharedPreferences(LatinIME.this).edit().putBoolean("pref_art_package_scanned", true).apply();
            }
          }
        }
      }
      catch (NullPointerException localNullPointerException)
      {
        localNullPointerException.printStackTrace();
      }
      for (;;)
      {
        Object localObject2 = new Intent("emoji.keyboard.emoticonkeyboard.KAKA_ART_PACKAGE_CHANGED_INTENT");
        LatinIME.this.sendBroadcast((Intent)localObject2);
        return;
        localObject2 = (LinkedHashMap)new com.google.a.f().a((String)localObject2, new com.google.a.c.a() {}.getType());
        break;
        localObject2 = new com.google.a.f().a(localObject2);
        PreferenceManager.getDefaultSharedPreferences(LatinIME.this).edit().putString("pref_art_pacakge_collection", (String)localObject2).commit();
      }
    }
  };
  a mArtViewChangedReceiver = null;
  private boolean mBoostPersonalizationDictionaryForDebug = false;
  private b mClipChangedListener = null;
  private final s mConnection = new s(this);
  private EditorInfo mCurrentEditorInfo;
  private final TreeSet<Long> mCurrentlyPressedHardwareKeys = new TreeSet();
  private int mDeleteCount;
  private BroadcastReceiver mDictionaryPackInstallReceiver = new DictionaryPackInstallBroadcastReceiver(this);
  private int mDisplayOrientation;
  private String mEnteredText;
  private emoji.keyboard.emoticonkeyboard.c.c mEventInterpreter = new emoji.keyboard.emoticonkeyboard.c.c(this);
  private boolean mExpectingUpdateSelection;
  private View mExtractArea;
  public final g mHandler = new g(this);
  private d mInputUpdater;
  private boolean mIsAutoCorrectionIndicatorOn;
  private final boolean mIsHardwareAcceleratedDrawingEnabled = emoji.keyboard.emoticonkeyboard.b.h.a(this);
  private boolean mIsMainDictionaryAvailable;
  private boolean mIsNeededHackingForSM2k = false;
  private boolean mIsSelectionEnabled = false;
  private boolean mIsUserDictionaryAvailable;
  private View mKeyPreviewBackingView;
  final com.android.inputmethod.keyboard.h mKeyboardSwitcher = com.android.inputmethod.keyboard.h.a();
  private p mLastComposedWord = p.i;
  private long mLastKeyTime;
  private int mLastSelectionEnd = -1;
  private int mLastSelectionStart = -1;
  private AlertDialog mOptionsDialog;
  private com.android.inputmethod.latin.personalization.c mPersonalizationDictionary;
  private com.android.inputmethod.latin.personalization.e mPersonalizationPredictionDictionary;
  private final com.android.inputmethod.latin.d.v mRecapitalizeStatus = new com.android.inputmethod.latin.d.v();
  private BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public final void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      boolean bool = false;
      paramAnonymousContext = paramAnonymousIntent.getAction();
      if (paramAnonymousContext.equals("android.net.conn.CONNECTIVITY_CHANGE"))
      {
        paramAnonymousContext = LatinIME.this.mSubtypeSwitcher;
        if (!paramAnonymousIntent.getBooleanExtra("noConnectivity", false)) {
          bool = true;
        }
        paramAnonymousContext.g = bool;
        paramAnonymousContext = com.android.inputmethod.keyboard.h.a();
        if (paramAnonymousContext.c != null) {
          paramAnonymousContext.c.a(paramAnonymousContext.a.d());
        }
      }
      while (!paramAnonymousContext.equals("android.media.RINGER_MODE_CHANGED")) {
        return;
      }
      paramAnonymousContext = c.a();
      paramAnonymousContext.b = paramAnonymousContext.c();
    }
  };
  private t mRichImm;
  private final com.android.inputmethod.latin.settings.c mSettings = com.android.inputmethod.latin.settings.c.a();
  private int mSpaceState;
  private int mSubTip = -1;
  private final e mSubtypeState = new e();
  private final u mSubtypeSwitcher = u.a();
  private v mSuggest;
  private w mSuggestedWords = w.a;
  private SuggestionStripView mSuggestionStripView;
  private ImageView mTopMenuBtnRedPoint;
  private ImageButton mTopMenuButton;
  private final f mTopMenuButtonClickListener = new f((byte)0);
  private View mTopMenuSuggestionContainerView;
  private View mTopSuggestionSettingView;
  private boolean mUseOnlyPersonalizationDictionaryForDebug = false;
  private z mUserDictionary;
  private com.android.inputmethod.latin.personalization.f mUserHistoryDictionary;
  private ImageButton mVoiceInputButton;
  private ImageView mVoiceRedPoint;
  private final aa mWordComposer = new aa();
  
  static
  {
    com.android.inputmethod.latin.d.q.a();
  }
  
  public LatinIME()
  {
    Log.i(TAG, "Hardware accelerated drawing: " + this.mIsHardwareAcceleratedDrawingEnabled);
  }
  
  private String addToUserHistoryDictionary(String paramString)
  {
    int j = 2;
    boolean bool = true;
    if (TextUtils.isEmpty(paramString)) {
      paramString = null;
    }
    Object localObject;
    com.android.inputmethod.latin.personalization.f localF;
    String str;
    label151:
    label157:
    label161:
    do
    {
      do
      {
        return paramString;
        v localV = this.mSuggest;
        if (localV == null) {
          return null;
        }
        localObject = this.mSettings.g;
        if (!((com.android.inputmethod.latin.settings.e)localObject).F) {
          return null;
        }
        localF = this.mUserHistoryDictionary;
        if (localF == null) {
          return null;
        }
        str = this.mConnection.a(((com.android.inputmethod.latin.settings.e)localObject).f, 2);
        localObject = this.mWordComposer;
        if ((((aa)localObject).j == 7) || (((aa)localObject).j == 5))
        {
          i = 1;
          if ((i == 0) || (this.mWordComposer.g())) {
            break label151;
          }
        }
        for (localObject = paramString.toLowerCase(this.mSubtypeSwitcher.e());; localObject = paramString)
        {
          i = com.android.inputmethod.latin.d.e.a(localV.c, paramString);
          if (i != 0) {
            break label157;
          }
          return null;
          i = 0;
          break;
        }
        if (i <= 0) {
          break;
        }
        paramString = str;
      } while (((String)localObject).length() >= 48);
      if (str == null) {
        break;
      }
      paramString = str;
    } while (str.length() >= 48);
    int i = j;
    if (com.android.inputmethod.latin.personalization.a.h)
    {
      i = j;
      if (!bool) {
        i = -1;
      }
    }
    if (!localF.m) {
      Log.w(l.b, "addWordDynamically is called for non-updatable dictionary: " + localF.l);
    }
    for (;;)
    {
      paramString = str;
      if (((String)localObject).equals(str)) {
        break;
      }
      paramString = str;
      if (str == null) {
        break;
      }
      if (localF.m) {
        break label337;
      }
      Log.w(l.b, "addBigramDynamically is called for non-updatable dictionary: " + localF.l);
      return str;
      bool = false;
      break label161;
      l.c(localF.l).a(new l.10(localF, (String)localObject, i));
    }
    label337:
    l.c(localF.l).a(new l.11(localF, str, (String)localObject, i, bool));
    return str;
  }
  
  private static boolean canBeFollowedByDoubleSpacePeriod(int paramInt)
  {
    return (Character.isLetterOrDigit(paramInt)) || (paramInt == 39) || (paramInt == 34) || (paramInt == 41) || (paramInt == 93) || (paramInt == 125) || (paramInt == 62) || (paramInt == 43) || (Character.getType(paramInt) == 28);
  }
  
  private void clearSuggestionStrip()
  {
    setSuggestedWords(w.a, false);
    setAutoCorrectionIndicator(false);
  }
  
  private void commitChosenWord(String paramString1, int paramInt, String paramString2)
  {
    Object localObject = this.mSuggestedWords;
    this.mConnection.a(emoji.keyboard.emoticonkeyboard.b.l.a(this, paramString1, (w)localObject, this.mIsMainDictionaryAvailable), 1);
    String str = addToUserHistoryDictionary(paramString1);
    localObject = this.mWordComposer;
    int[] arrayOfInt = ((aa)localObject).a;
    ((aa)localObject).a = new int[48];
    paramString1 = new p(arrayOfInt, ((aa)localObject).b, ((aa)localObject).c.toString(), paramString1, paramString2, str, ((aa)localObject).j);
    ((aa)localObject).b.a();
    if ((paramInt != 2) && (paramInt != 1)) {
      paramString1.h = false;
    }
    ((aa)localObject).h = 0;
    ((aa)localObject).i = 0;
    ((aa)localObject).f = false;
    ((aa)localObject).c.setLength(0);
    ((aa)localObject).l = 0;
    ((aa)localObject).k = 0;
    ((aa)localObject).n = false;
    ((aa)localObject).j = 0;
    ((aa)localObject).b();
    ((aa)localObject).d = null;
    ((aa)localObject).m = 0;
    ((aa)localObject).e = false;
    ((aa)localObject).g = null;
    this.mLastComposedWord = paramString1;
  }
  
  private void commitCurrentAutoCorrection(String paramString)
  {
    if (this.mHandler.hasMessages(2)) {
      updateSuggestionStrip();
    }
    Object localObject = this.mWordComposer.d;
    String str = this.mWordComposer.c.toString();
    if (localObject != null) {}
    while (localObject != null) {
      if (TextUtils.isEmpty(str))
      {
        throw new RuntimeException("We have an auto-correction but the typed word is empty? Impossible! I must commit suicide.");
        localObject = str;
      }
      else
      {
        if (this.mSettings.g.N) {
          com.android.inputmethod.latin.d.s.a(str, (String)localObject, paramString, this.mWordComposer);
        }
        this.mExpectingUpdateSelection = true;
        commitChosenWord((String)localObject, 2, paramString);
        if (!str.equals(localObject))
        {
          paramString = this.mConnection;
          localObject = new CorrectionInfo(this.mLastSelectionEnd - str.length(), str, (CharSequence)localObject);
          if (paramString.f != null) {
            paramString.f.commitCorrection((CorrectionInfo)localObject);
          }
        }
      }
    }
  }
  
  private void commitTyped(String paramString)
  {
    if (!this.mWordComposer.c()) {}
    String str;
    do
    {
      return;
      str = this.mWordComposer.c.toString();
    } while (str.length() <= 0);
    commitChosenWord(str, 0, paramString);
  }
  
  private void exitFromSelectionMode()
  {
    if (this.mIsSelectionEnabled)
    {
      this.mConnection.a(new KeyEvent(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, 59, 0, 0, -1, 0, 2));
      com.android.inputmethod.keyboard.h localH = this.mKeyboardSwitcher;
      if (localH.i != null) {
        localH.i.setSelectionMode(false);
      }
      this.mIsSelectionEnabled = false;
    }
  }
  
  private int getActualCapsMode()
  {
    com.android.inputmethod.keyboard.d localD = this.mKeyboardSwitcher.c();
    if (localD != null) {}
    switch (localD.a.f)
    {
    default: 
      i = 0;
    }
    while (i != 5)
    {
      return i;
      i = 3;
      continue;
      i = 1;
      continue;
      i = 5;
    }
    int i = getCurrentAutoCapsState();
    if ((i & 0x1000) != 0) {
      return 7;
    }
    if (i != 0) {
      return 5;
    }
    return 0;
  }
  
  private int getAdjustedBackingViewHeight()
  {
    int i = this.mKeyPreviewBackingView.getHeight();
    if (i > 0) {
      return i;
    }
    Object localObject = this.mKeyboardSwitcher.z();
    if (localObject == null) {
      return 0;
    }
    i = ((View)localObject).getHeight();
    int j = this.mSuggestionStripView.getHeight();
    int k = getResources().getDisplayMetrics().heightPixels;
    localObject = new Rect();
    this.mKeyPreviewBackingView.getWindowVisibleDisplayFrame((Rect)localObject);
    j = k - ((Rect)localObject).top - j - i;
    localObject = this.mKeyPreviewBackingView.getLayoutParams();
    com.android.inputmethod.latin.suggestions.b localB = this.mSuggestionStripView.e;
    i = localB.a();
    if (i <= j) {}
    for (;;)
    {
      ((ViewGroup.LayoutParams)localObject).height = i;
      this.mKeyPreviewBackingView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      return ((ViewGroup.LayoutParams)localObject).height;
      localB.f = ((j - localB.h) / localB.e);
      i = localB.a();
    }
  }
  
  private w getOlderSuggestions(String paramString)
  {
    w localW2 = this.mSuggestedWords;
    w localW1 = localW2;
    if (localW2 == this.mSettings.g.e) {
      localW1 = w.a;
    }
    if (paramString == null) {
      return localW1;
    }
    return new w(w.a(paramString, localW1), false, false, false, true, false);
  }
  
  private void getSuggestedWords(int paramInt, v.a paramA)
  {
    Object localObject3 = this.mKeyboardSwitcher.c();
    v localV = this.mSuggest;
    if ((localObject3 == null) || (localV == null))
    {
      paramA.a(w.a);
      return;
    }
    Object localObject2 = this.mSettings.g;
    int[] arrayOfInt = ((com.android.inputmethod.latin.settings.e)localObject2).J;
    Object localObject4;
    if (((com.android.inputmethod.latin.settings.e)localObject2).i)
    {
      localObject1 = this.mConnection;
      localObject4 = ((com.android.inputmethod.latin.settings.e)localObject2).f;
      if (this.mWordComposer.c())
      {
        i = 2;
        localObject1 = ((s)localObject1).a((String)localObject4, i);
      }
    }
    ProximityInfo localProximityInfo;
    boolean bool2;
    for (;;)
    {
      localObject4 = this.mWordComposer;
      localProximityInfo = ((com.android.inputmethod.keyboard.d)localObject3).q;
      bool1 = ((com.android.inputmethod.latin.settings.e)localObject2).r;
      bool2 = ((com.android.inputmethod.latin.settings.e)localObject2).F;
      if (!((aa)localObject4).f) {
        break label502;
      }
      localObject2 = new com.android.inputmethod.latin.d.f(v.h);
      localObject3 = localV.c.keySet().iterator();
      while (((Iterator)localObject3).hasNext())
      {
        str1 = (String)((Iterator)localObject3).next();
        ((com.android.inputmethod.latin.d.f)localObject2).addAll(((h)localV.c.get(str1)).a((aa)localObject4, (String)localObject1, localProximityInfo, bool1, arrayOfInt, paramInt));
      }
      i = 1;
      break;
      if (p.i == this.mLastComposedWord) {
        localObject1 = null;
      } else {
        localObject1 = this.mLastComposedWord.c;
      }
    }
    Object localObject1 = ((com.android.inputmethod.latin.d.f)localObject2).iterator();
    while (((Iterator)localObject1).hasNext()) {
      localObject3 = ((w.a)((Iterator)localObject1).next()).e.g;
    }
    localObject1 = com.android.inputmethod.latin.d.h.a((Collection)localObject2);
    int i = ((ArrayList)localObject1).size();
    if ((((aa)localObject4).j == 5) || (((aa)localObject4).j == 1)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      bool2 = ((aa)localObject4).f();
      if ((!bool1) && (!bool2)) {
        break;
      }
      paramInt = 0;
      while (paramInt < i)
      {
        ((ArrayList)localObject1).set(paramInt, v.a((w.a)((ArrayList)localObject1).get(paramInt), localV.g, bool2, bool1, 0));
        paramInt += 1;
      }
    }
    if ((((ArrayList)localObject1).size() > 1) && (TextUtils.equals(((w.a)((ArrayList)localObject1).get(0)).a, ((aa)localObject4).g))) {
      ((ArrayList)localObject1).add(1, (w.a)((ArrayList)localObject1).remove(0));
    }
    w.a.a((ArrayList)localObject1);
    paramInt = ((ArrayList)localObject1).size() - 1;
    while (paramInt >= 0)
    {
      if (((w.a)((ArrayList)localObject1).get(paramInt)).b < -2000000000) {
        ((ArrayList)localObject1).remove(paramInt);
      }
      paramInt -= 1;
    }
    paramA.a(new w((ArrayList)localObject1, true, false, false, false, false));
    return;
    label502:
    int j = ((aa)localObject4).k;
    com.android.inputmethod.latin.d.f localF = new com.android.inputmethod.latin.d.f(v.h);
    String str1 = ((aa)localObject4).c.toString();
    if (j > 0) {}
    for (localObject2 = str1.substring(0, str1.length() - j); j > 0; localObject2 = str1)
    {
      localObject5 = new aa((aa)localObject4);
      paramInt = j - 1;
      for (;;)
      {
        localObject3 = localObject5;
        if (paramInt < 0) {
          break;
        }
        ((aa)localObject5).e();
        paramInt -= 1;
      }
    }
    localObject3 = localObject4;
    Object localObject5 = localV.c.keySet().iterator();
    while (((Iterator)localObject5).hasNext())
    {
      String str2 = (String)((Iterator)localObject5).next();
      localF.addAll(((h)localV.c.get(str2)).a((aa)localObject3, (String)localObject1, localProximityInfo, bool1, arrayOfInt));
    }
    if (localF.isEmpty())
    {
      localObject1 = null;
      if (((localObject1 == null) || (((String)localObject1).equals(localObject2))) && ((((String)localObject2).length() <= 1) || (com.android.inputmethod.latin.d.e.a(localV, (String)localObject2, ((aa)localObject4).n)))) {
        break label935;
      }
      paramInt = 1;
      label728:
      if ((bool2) && (paramInt != 0) && (((aa)localObject4).c()) && (!localF.isEmpty()))
      {
        if (((aa)localObject4).i <= 0) {
          break label940;
        }
        i = 1;
        label763:
        if ((i == 0) && (!((aa)localObject4).g()) && (!((aa)localObject4).e) && (localV.a()) && (7 != ((w.a)localF.first()).c)) {
          break label945;
        }
      }
    }
    int k;
    label935:
    label940:
    label945:
    for (bool1 = false;; bool1 = com.android.inputmethod.latin.d.e.a((w.a)localF.first(), (String)localObject2, localV.f))
    {
      localObject1 = com.android.inputmethod.latin.d.h.a(localF);
      k = ((ArrayList)localObject1).size();
      bool2 = ((aa)localObject4).n;
      bool3 = ((aa)localObject4).f();
      if ((!bool2) && (!bool3) && (j == 0)) {
        break label968;
      }
      i = 0;
      while (i < k)
      {
        ((ArrayList)localObject1).set(i, v.a((w.a)((ArrayList)localObject1).get(i), localV.g, bool3, bool2, j));
        i += 1;
      }
      if (3 != ((w.a)localF.first()).c)
      {
        localObject1 = null;
        break;
      }
      localObject1 = ((w.a)localF.first()).a;
      break;
      paramInt = 0;
      break label728;
      i = 0;
      break label763;
    }
    label968:
    i = 0;
    while (i < k)
    {
      localObject2 = (w.a)((ArrayList)localObject1).get(i);
      ((w.a)localObject2).a.toString();
      localObject2 = ((w.a)localObject2).e.g;
      i += 1;
    }
    if (!TextUtils.isEmpty(str1)) {
      ((ArrayList)localObject1).add(0, new w.a(str1, Integer.MAX_VALUE, 0, h.c, -1, -1));
    }
    w.a.a((ArrayList)localObject1);
    if ((v.b) && (!((ArrayList)localObject1).isEmpty()))
    {
      localObject1 = v.a(str1, (ArrayList)localObject1);
      if (paramInt != 0) {
        break label1118;
      }
      bool2 = true;
      label1080:
      if (((aa)localObject4).c()) {
        break label1124;
      }
    }
    label1118:
    label1124:
    for (boolean bool3 = true;; bool3 = false)
    {
      paramA.a(new w((ArrayList)localObject1, bool2, bool1, false, false, bool3));
      return;
      break;
      bool2 = false;
      break label1080;
    }
  }
  
  private void getSuggestedWordsOrOlderSuggestionsAsync(int paramInt, final v.a paramA)
  {
    this.mInputUpdater.a(paramInt, new v.a()
    {
      public final void a(w paramAnonymousW)
      {
        paramA.a(LatinIME.this.maybeRetrieveOlderSuggestions(LatinIME.this.mWordComposer.c.toString(), paramAnonymousW));
      }
    });
  }
  
  private CharSequence getTextWithUnderline(String paramString)
  {
    Object localObject = paramString;
    if (this.mIsAutoCorrectionIndicatorOn) {
      localObject = emoji.keyboard.emoticonkeyboard.b.l.a(this, paramString);
    }
    return localObject;
  }
  
  private void handleBackspace(int paramInt)
  {
    int j = 2;
    this.mDeleteCount += 1;
    this.mExpectingUpdateSelection = true;
    Object localObject1 = this.mHandler;
    ((g)localObject1).removeMessages(0);
    ((g)localObject1).sendMessageDelayed(((g)localObject1).obtainMessage(0), ((g)localObject1).b);
    if (this.mWordComposer.d()) {
      resetEntireInputState(this.mLastSelectionStart);
    }
    if (this.mWordComposer.c())
    {
      if (this.mWordComposer.f)
      {
        localObject1 = this.mWordComposer.c.toString();
        this.mWordComposer.a();
        this.mWordComposer.g = ((String)localObject1);
      }
      for (;;)
      {
        this.mConnection.a(getTextWithUnderline(this.mWordComposer.c.toString()));
        this.mHandler.b();
        if (!this.mWordComposer.c()) {
          this.mKeyboardSwitcher.e();
        }
        return;
        this.mWordComposer.e();
      }
    }
    localObject1 = this.mSettings.g;
    Object localObject2 = this.mLastComposedWord;
    if ((((p)localObject2).h) && (!TextUtils.isEmpty(((p)localObject2).c)) && (!TextUtils.equals(((p)localObject2).b, ((p)localObject2).c))) {}
    for (int i = 1; i != 0; i = 0)
    {
      boolean bool = ((com.android.inputmethod.latin.settings.e)localObject1).N;
      revertCommit();
      return;
    }
    Object localObject3;
    if (this.mEnteredText != null)
    {
      localObject2 = this.mConnection;
      localObject3 = this.mEnteredText;
      if (TextUtils.equals((CharSequence)localObject3, ((s)localObject2).a(((CharSequence)localObject3).length())))
      {
        this.mConnection.a(this.mEnteredText.length(), 0);
        this.mEnteredText = null;
        return;
      }
    }
    if (1 == paramInt)
    {
      this.mHandler.d = 0L;
      localObject2 = this.mConnection;
      if (!TextUtils.equals(". ", ((s)localObject2).a(2)))
      {
        Log.d(s.a, "Tried to revert double-space combo but we didn't find \". \" just before the cursor.");
        paramInt = 0;
        label348:
        if (paramInt != 0) {
          break label474;
        }
        label352:
        if (this.mLastSelectionStart == this.mLastSelectionEnd) {
          break label588;
        }
        paramInt = Math.abs(this.mLastSelectionEnd - this.mLastSelectionStart);
        this.mConnection.b(this.mLastSelectionEnd, this.mLastSelectionEnd);
        if (this.mLastSelectionEnd <= this.mLastSelectionStart) {
          break label576;
        }
        this.mConnection.a(paramInt, 0);
        label411:
        this.mLastSelectionEnd = this.mLastSelectionStart;
      }
    }
    label474:
    label576:
    label588:
    label644:
    label667:
    label677:
    do
    {
      if ((((com.android.inputmethod.latin.settings.e)localObject1).a(this.mDisplayOrientation)) && (((com.android.inputmethod.latin.settings.e)localObject1).i)) {
        restartSuggestionsOnWordBeforeCursorIfAtEndOfWord();
      }
      this.mKeyboardSwitcher.e();
      exitFromSelectionMode();
      return;
      ((s)localObject2).a(2, 0);
      ((s)localObject2).a(" ", 1);
      paramInt = 1;
      break label348;
      break;
      if (2 != paramInt) {
        break label352;
      }
      localObject2 = this.mConnection;
      localObject3 = ((s)localObject2).a(2);
      if ((TextUtils.isEmpty((CharSequence)localObject3)) || (' ' != ((CharSequence)localObject3).charAt(1))) {
        Log.d(s.a, "Tried to revert a swap of punctuation but we didn't find a space just before the cursor.");
      }
      for (paramInt = 0; paramInt != 0; paramInt = 1)
      {
        return;
        ((s)localObject2).a(2, 0);
        ((s)localObject2).a(" " + ((CharSequence)localObject3).subSequence(0, 1), 1);
      }
      break label352;
      this.mConnection.a(0, paramInt);
      break label411;
      if (-1 == this.mLastSelectionEnd) {
        Log.e(TAG, "Backspace when we don't know the selection position");
      }
      paramInt = this.mConnection.e();
      if (paramInt == -1)
      {
        this.mDeleteCount -= 1;
        this.mExpectingUpdateSelection = false;
        return;
      }
      if (!Character.isSupplementaryCodePoint(paramInt)) {
        break label720;
      }
      paramInt = 2;
      if (!this.mAppWorkAroundsUtils.a())
      {
        if (((com.android.inputmethod.latin.settings.e)localObject1).A.e != 0) {
          break label725;
        }
        i = 1;
        if (i == 0) {
          break label730;
        }
      }
      sendDownUpKeyEvent(67);
      if (this.mDeleteCount <= 20) {
        break label740;
      }
      paramInt = this.mConnection.e();
    } while (paramInt == -1);
    if (Character.isSupplementaryCodePoint(paramInt)) {}
    for (paramInt = j;; paramInt = 1)
    {
      this.mConnection.a(paramInt, 0);
      break;
      label720:
      paramInt = 1;
      break label644;
      label725:
      i = 0;
      break label667;
      label730:
      this.mConnection.a(paramInt, 0);
      break label677;
      label740:
      break;
    }
  }
  
  private void handleCharacter(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool3 = true;
    int i = 0;
    boolean bool1 = this.mWordComposer.c();
    com.android.inputmethod.latin.settings.e localE = this.mSettings.g;
    if ((4 == paramInt4) && (!localE.d(paramInt1)))
    {
      if (bool1) {
        throw new RuntimeException("Should not be composing here");
      }
      promotePhantomSpace();
    }
    if (this.mWordComposer.d())
    {
      resetEntireInputState(this.mLastSelectionStart);
      bool1 = false;
    }
    boolean bool2 = bool1;
    if (!bool1)
    {
      bool2 = bool1;
      if (localE.e(paramInt1))
      {
        bool2 = bool1;
        if (localE.a(this.mDisplayOrientation)) {
          if (this.mConnection.a(localE))
          {
            bool2 = bool1;
            if (localE.i) {}
          }
          else
          {
            if ((39 == paramInt1) || (45 == paramInt1)) {
              break label314;
            }
            bool1 = true;
            resetComposingState(false);
            bool2 = bool1;
          }
        }
      }
    }
    label178:
    Object localObject;
    if (bool2)
    {
      if (paramInt2 >= 0)
      {
        paramInt4 = 1;
        if (paramInt4 == 0) {
          break label326;
        }
        paramInt4 = i;
        if (paramInt3 >= 0) {
          paramInt4 = 1;
        }
        if (paramInt4 == 0) {
          break label326;
        }
        localObject = this.mKeyboardSwitcher.c.getKeyDetector();
        i = ((com.android.inputmethod.keyboard.c)localObject).c + paramInt2;
        paramInt4 = ((com.android.inputmethod.keyboard.c)localObject).d + paramInt3;
      }
      for (;;)
      {
        this.mWordComposer.a(paramInt1, i, paramInt4);
        if (this.mWordComposer.l == 1) {
          this.mWordComposer.j = getActualCapsMode();
        }
        this.mConnection.a(getTextWithUnderline(this.mWordComposer.c.toString()));
        this.mHandler.b();
        if (localE.N)
        {
          char c = (char)paramInt1;
          aj.a().a(c, paramInt2, paramInt3);
        }
        return;
        label314:
        bool1 = false;
        break;
        paramInt4 = 0;
        break label178;
        label326:
        paramInt4 = paramInt3;
        i = paramInt2;
      }
    }
    if (-2 == paramInt2) {}
    for (bool1 = bool3;; bool1 = false)
    {
      bool1 = maybeStripSpace(paramInt1, paramInt4, bool1);
      sendKeyCodePoint(paramInt1);
      if (bool1)
      {
        swapSwapperAndSpace();
        this.mSpaceState = 3;
      }
      if (this.mSuggestionStripView == null) {
        break;
      }
      localObject = this.mSuggestionStripView;
      if (!((SuggestionStripView)localObject).a()) {
        break;
      }
      ((SuggestionStripView)localObject).b();
      break;
    }
  }
  
  private void handleClose()
  {
    commitTyped("");
    requestHideSelf(0);
    MainKeyboardView localMainKeyboardView = this.mKeyboardSwitcher.c;
    if (localMainKeyboardView != null) {
      localMainKeyboardView.i();
    }
  }
  
  private void handleCopyPastKeys(int paramInt)
  {
    Object localObject = (ClipboardManager)getSystemService("clipboard");
    CharSequence localCharSequence = this.mConnection.d();
    switch (paramInt)
    {
    default: 
    case -23: 
    case -25: 
      do
      {
        do
        {
          return;
        } while (localCharSequence == null);
        ((ClipboardManager)localObject).setPrimaryClip(ClipData.newPlainText("simple text", localCharSequence));
        Toast.makeText(getApplicationContext(), 2131296740, 0).show();
        this.mKeyboardSwitcher.A();
        exitFromSelectionMode();
        this.mConnection.b(this.mLastSelectionStart, this.mLastSelectionStart);
        return;
      } while (localCharSequence == null);
      ((ClipboardManager)localObject).setPrimaryClip(ClipData.newPlainText("simple text", localCharSequence));
      sendDownUpKeyEvent(67);
      Toast.makeText(getApplicationContext(), 2131296740, 0).show();
      this.mKeyboardSwitcher.A();
    }
    for (;;)
    {
      exitFromSelectionMode();
      return;
      localObject = ((ClipboardManager)localObject).getPrimaryClip();
      if (localObject == null) {
        break;
      }
      localObject = ((ClipData)localObject).getItemAt(0).getText();
      if (localObject != null) {
        this.mConnection.a((CharSequence)localObject, 1);
      }
    }
  }
  
  private void handleLanguageSwitchKey()
  {
    IBinder localIBinder = getWindow().getWindow().getAttributes().token;
    if (this.mSettings.g.n)
    {
      this.mRichImm.a(localIBinder, false);
      return;
    }
    e localE = this.mSubtypeState;
    t localT = this.mRichImm;
    InputMethodSubtype localInputMethodSubtype1 = localT.b().getCurrentInputMethodSubtype();
    InputMethodSubtype localInputMethodSubtype2 = localE.a;
    boolean bool = localE.b;
    if (bool)
    {
      localE.a = localInputMethodSubtype1;
      localE.b = false;
    }
    if ((bool) && (localT.a(localInputMethodSubtype2)) && (!localInputMethodSubtype1.equals(localInputMethodSubtype2)))
    {
      localT.a(localIBinder, localInputMethodSubtype2);
      return;
    }
    localT.a(localIBinder, true);
  }
  
  private void handleMovementKeys(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case -18: 
      sendDownUpKeyEvent(22);
      return;
    case -17: 
      sendDownUpKeyEvent(21);
      return;
    case -19: 
      sendDownUpKeyEventWithMetaState(122, 4096);
      return;
    case -20: 
      sendDownUpKeyEventWithMetaState(123, 4096);
      return;
    case -15: 
      sendDownUpKeyEvent(19);
      return;
    }
    sendDownUpKeyEvent(20);
  }
  
  private boolean handleNonSpecialCharacter(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mSpaceState = 0;
    Object localObject = this.mSettings.g;
    boolean bool;
    if ((((com.android.inputmethod.latin.settings.e)localObject).c(paramInt1)) || (Character.getType(paramInt1) == 28))
    {
      bool = handleSeparator(paramInt1, paramInt2, paramInt3, paramInt4);
      this.mExpectingUpdateSelection = true;
      return bool;
    }
    if (4 == paramInt4)
    {
      if ((((com.android.inputmethod.latin.settings.e)localObject).N) && (this.mWordComposer.c()) && (this.mWordComposer.f)) {
        com.android.inputmethod.latin.d.s.a("", this.mWordComposer.c.toString(), " ", this.mWordComposer);
      }
      if (this.mWordComposer.d()) {
        resetEntireInputState(this.mLastSelectionStart);
      }
    }
    else
    {
      label126:
      localObject = this.mKeyboardSwitcher.c();
      if (localObject != null)
      {
        if (!((com.android.inputmethod.keyboard.d)localObject).r) {
          break label229;
        }
        if ((((com.android.inputmethod.keyboard.d)localObject).a.f != 0) && (((com.android.inputmethod.keyboard.d)localObject).a.f != 2)) {
          break label223;
        }
        i = 1;
        label174:
        if ((i == 0) && (!Character.isLetter(paramInt1))) {
          break label229;
        }
      }
    }
    label223:
    label229:
    for (int i = 1;; i = 0)
    {
      if (i == 0)
      {
        paramInt3 = -1;
        paramInt2 = -1;
      }
      handleCharacter(paramInt1, paramInt2, paramInt3, paramInt4);
      bool = false;
      break;
      commitTyped("");
      break label126;
      i = 0;
      break label174;
    }
  }
  
  private void handleRecapitalize()
  {
    if (this.mLastSelectionStart == this.mLastSelectionEnd) {}
    do
    {
      return;
      if ((this.mRecapitalizeStatus.k) && (this.mRecapitalizeStatus.a(this.mLastSelectionStart, this.mLastSelectionEnd))) {
        break;
      }
      localObject1 = this.mConnection.d();
    } while (TextUtils.isEmpty((CharSequence)localObject1));
    Object localObject2 = this.mSettings.g;
    this.mRecapitalizeStatus.a(this.mLastSelectionStart, this.mLastSelectionEnd, ((CharSequence)localObject1).toString(), ((com.android.inputmethod.latin.settings.e)localObject2).z, ((com.android.inputmethod.latin.settings.e)localObject2).f);
    Object localObject1 = this.mRecapitalizeStatus;
    int k = ((com.android.inputmethod.latin.d.v)localObject1).c.length();
    for (int i = 0; (i < k) && (Character.isWhitespace(((com.android.inputmethod.latin.d.v)localObject1).c.codePointAt(i))); i = ((com.android.inputmethod.latin.d.v)localObject1).c.offsetByCodePoints(i, 1)) {}
    for (int j = k; (j > 0) && (Character.isWhitespace(((com.android.inputmethod.latin.d.v)localObject1).c.codePointBefore(j))); j = ((com.android.inputmethod.latin.d.v)localObject1).c.offsetByCodePoints(j, -1)) {}
    if (((i != 0) || (k != j)) && (i < j))
    {
      ((com.android.inputmethod.latin.d.v)localObject1).e = (((com.android.inputmethod.latin.d.v)localObject1).b + j);
      k = ((com.android.inputmethod.latin.d.v)localObject1).b + i;
      ((com.android.inputmethod.latin.d.v)localObject1).d = k;
      ((com.android.inputmethod.latin.d.v)localObject1).b = k;
      localObject2 = ((com.android.inputmethod.latin.d.v)localObject1).c.substring(i, j);
      ((com.android.inputmethod.latin.d.v)localObject1).c = ((String)localObject2);
      ((com.android.inputmethod.latin.d.v)localObject1).j = ((String)localObject2);
    }
    if (!this.mRecapitalizeStatus.a(this.mLastSelectionStart, this.mLastSelectionEnd))
    {
      this.mLastSelectionStart = this.mRecapitalizeStatus.d;
      this.mLastSelectionEnd = this.mRecapitalizeStatus.e;
      this.mConnection.b(this.mLastSelectionStart, this.mLastSelectionEnd);
    }
    localObject1 = this.mRecapitalizeStatus;
    localObject2 = ((com.android.inputmethod.latin.d.v)localObject1).j;
    i = 0;
    ((com.android.inputmethod.latin.d.v)localObject1).f = ((((com.android.inputmethod.latin.d.v)localObject1).f + 1) % com.android.inputmethod.latin.d.v.a.length);
    if ((com.android.inputmethod.latin.d.v.a[localObject1.f] == 0) && (((com.android.inputmethod.latin.d.v)localObject1).g)) {
      ((com.android.inputmethod.latin.d.v)localObject1).f = ((((com.android.inputmethod.latin.d.v)localObject1).f + 1) % com.android.inputmethod.latin.d.v.a.length);
    }
    j = i + 1;
    switch (com.android.inputmethod.latin.d.v.a[localObject1.f])
    {
    default: 
      ((com.android.inputmethod.latin.d.v)localObject1).j = ((com.android.inputmethod.latin.d.v)localObject1).c;
    }
    for (;;)
    {
      if (((com.android.inputmethod.latin.d.v)localObject1).j.equals(localObject2))
      {
        i = j;
        if (j < com.android.inputmethod.latin.d.v.a.length + 1) {
          break;
        }
      }
      ((com.android.inputmethod.latin.d.v)localObject1).e = (((com.android.inputmethod.latin.d.v)localObject1).d + ((com.android.inputmethod.latin.d.v)localObject1).j.length());
      i = this.mLastSelectionEnd;
      j = this.mLastSelectionStart;
      this.mConnection.b(this.mLastSelectionEnd, this.mLastSelectionEnd);
      this.mConnection.a(i - j, 0);
      this.mConnection.a(this.mRecapitalizeStatus.j, 0);
      this.mLastSelectionStart = this.mRecapitalizeStatus.d;
      this.mLastSelectionEnd = this.mRecapitalizeStatus.e;
      this.mConnection.b(this.mLastSelectionStart, this.mLastSelectionEnd);
      this.mKeyboardSwitcher.e();
      return;
      ((com.android.inputmethod.latin.d.v)localObject1).j = ((com.android.inputmethod.latin.d.v)localObject1).c;
      continue;
      ((com.android.inputmethod.latin.d.v)localObject1).j = ((com.android.inputmethod.latin.d.v)localObject1).c.toLowerCase(((com.android.inputmethod.latin.d.v)localObject1).h);
      continue;
      ((com.android.inputmethod.latin.d.v)localObject1).j = ab.a(((com.android.inputmethod.latin.d.v)localObject1).c, ((com.android.inputmethod.latin.d.v)localObject1).i, ((com.android.inputmethod.latin.d.v)localObject1).h);
      continue;
      ((com.android.inputmethod.latin.d.v)localObject1).j = ((com.android.inputmethod.latin.d.v)localObject1).c.toUpperCase(((com.android.inputmethod.latin.d.v)localObject1).h);
    }
  }
  
  private boolean handleSeparator(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool2 = false;
    com.android.inputmethod.latin.settings.e localE = this.mSettings.g;
    int i;
    String str;
    label85:
    boolean bool1;
    if ((32 == paramInt1) && (!localE.i) && (this.mWordComposer.c()))
    {
      i = 1;
      if (this.mWordComposer.d()) {
        resetEntireInputState(this.mLastSelectionStart);
      }
      if (!this.mWordComposer.c()) {
        break label238;
      }
      if (!localE.F) {
        break label230;
      }
      if (i == 0) {
        break label221;
      }
      str = "";
      commitCurrentAutoCorrection(str);
      bool1 = true;
      label94:
      if (-2 == paramInt2) {
        bool2 = true;
      }
      bool2 = maybeStripSpace(paramInt1, paramInt4, bool2);
      if ((4 == paramInt4) && (localE.f(paramInt1))) {
        promotePhantomSpace();
      }
      if (i == 0) {
        sendKeyCodePoint(paramInt1);
      }
      if (32 != paramInt1) {
        break label259;
      }
      if (localE.a(this.mDisplayOrientation))
      {
        if (!maybeDoubleSpacePeriod()) {
          break label244;
        }
        this.mSpaceState = 1;
      }
    }
    for (;;)
    {
      this.mHandler.d = SystemClock.uptimeMillis();
      this.mHandler.b();
      if (localE.N) {
        com.android.inputmethod.latin.d.s.a((char)paramInt1, paramInt2, paramInt3);
      }
      this.mKeyboardSwitcher.e();
      return bool1;
      i = 0;
      break;
      label221:
      str = ab.a(paramInt1);
      break label85;
      label230:
      commitTyped(ab.a(paramInt1));
      label238:
      bool1 = false;
      break label94;
      label244:
      if (!isShowingPunctuationList()) {
        this.mSpaceState = 3;
      }
    }
    label259:
    if (bool2) {
      swapSwapperAndSpace();
    }
    for (this.mSpaceState = 2;; this.mSpaceState = 4) {
      do
      {
        setPunctuationSuggestions();
        break;
      } while ((4 != paramInt4) || (!localE.g(paramInt1)));
    }
  }
  
  private void handleSwitchLastMethod()
  {
    IBinder localIBinder = getWindow().getWindow().getAttributes().token;
    this.mRichImm.a.b.switchToLastInputMethod(localIBinder);
  }
  
  private void hapticAndAudioFeedback(int paramInt1, int paramInt2)
  {
    int i = 1;
    MainKeyboardView localMainKeyboardView = this.mKeyboardSwitcher.c;
    boolean bool;
    if (localMainKeyboardView != null) {
      if (localMainKeyboardView.f())
      {
        bool = true;
        if (!bool) {
          break label41;
        }
      }
    }
    label41:
    label94:
    label97:
    for (;;)
    {
      return;
      bool = com.android.inputmethod.keyboard.l.a();
      break;
      if (paramInt2 > 0) {
        if (paramInt1 == -5) {
          if (this.mConnection.b <= 0) {
            break label94;
          }
        }
      }
      for (;;)
      {
        if (i == 0) {
          break label97;
        }
        if (paramInt2 % 2 == 0) {
          break;
        }
        c localC = c.a();
        if (paramInt2 == 0) {
          localC.a(localMainKeyboardView);
        }
        localC.a(paramInt1);
        return;
        i = 0;
      }
    }
  }
  
  private void initPersonalizationDebugSettings(com.android.inputmethod.latin.settings.e paramE)
  {
    if (this.mUseOnlyPersonalizationDictionaryForDebug != paramE.I)
    {
      initSuggest();
      this.mUseOnlyPersonalizationDictionaryForDebug = paramE.I;
    }
    if (this.mBoostPersonalizationDictionaryForDebug != paramE.H)
    {
      this.mBoostPersonalizationDictionaryForDebug = paramE.H;
      if (this.mBoostPersonalizationDictionaryForDebug) {
        ai.a();
      }
    }
    else
    {
      return;
    }
    ai.b();
  }
  
  private void initSuggest()
  {
    Object localObject2 = this.mSubtypeSwitcher.e();
    Object localObject3 = ((Locale)localObject2).toString();
    Object localObject1 = localObject3;
    if (TextUtils.isEmpty((CharSequence)localObject3))
    {
      Log.e(TAG, "System is reporting no current subtype.");
      localObject2 = getResources().getConfiguration().locale;
      localObject1 = ((Locale)localObject2).toString();
    }
    localObject3 = new v(this, (Locale)localObject2, this);
    com.android.inputmethod.latin.settings.e localE = this.mSettings.g;
    if (localE.F) {
      ((v)localObject3).f = localE.E;
    }
    this.mIsMainDictionaryAvailable = j.b(this, (Locale)localObject2);
    this.mUserDictionary = new z(this, (String)localObject1);
    localObject2 = this.mUserDictionary.i.getContentResolver().acquireContentProviderClient(UserDictionary.Words.CONTENT_URI);
    boolean bool;
    if (localObject2 != null)
    {
      ((ContentProviderClient)localObject2).release();
      bool = true;
      this.mIsUserDictionaryAvailable = bool;
      ((v)localObject3).a("user", this.mUserDictionary);
      localObject2 = PreferenceManager.getDefaultSharedPreferences(this);
      this.mUserHistoryDictionary = com.android.inputmethod.latin.personalization.d.a(this, (String)localObject1, (SharedPreferences)localObject2);
      ((v)localObject3).a("history", this.mUserHistoryDictionary);
      this.mPersonalizationDictionary = com.android.inputmethod.latin.personalization.d.a(this, (String)localObject1);
      ((v)localObject3).a("personalization", this.mPersonalizationDictionary);
      this.mPersonalizationPredictionDictionary = com.android.inputmethod.latin.personalization.d.b(this, (String)localObject1, (SharedPreferences)localObject2);
      ((v)localObject3).a("personalization_prediction_in_java", this.mPersonalizationPredictionDictionary);
      localObject2 = this.mSuggest;
      if (localObject2 == null) {
        break label266;
      }
    }
    label266:
    for (localObject1 = ((v)localObject2).d;; localObject1 = null)
    {
      resetContactsDictionary((g)localObject1);
      this.mSuggest = ((v)localObject3);
      if (localObject2 != null) {
        ((v)localObject2).b();
      }
      return;
      bool = false;
      break;
    }
  }
  
  private boolean isResumableWord(String paramString, com.android.inputmethod.latin.settings.e paramE)
  {
    boolean bool2 = false;
    int i = paramString.codePointAt(0);
    boolean bool1 = bool2;
    if (paramE.e(i))
    {
      bool1 = bool2;
      if (39 != i)
      {
        bool1 = bool2;
        if (45 != i) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  private boolean isShowingOptionDialog()
  {
    return (this.mOptionsDialog != null) && (this.mOptionsDialog.isShowing());
  }
  
  private boolean isSuggestionsStripVisible()
  {
    com.android.inputmethod.latin.settings.e localE = this.mSettings.g;
    if (this.mSuggestionStripView == null) {}
    do
    {
      return false;
      if (this.mSuggestionStripView.a()) {
        return true;
      }
    } while ((localE == null) || (!localE.b(this.mDisplayOrientation)));
    if (localE.A.c) {
      return true;
    }
    return localE.a(this.mDisplayOrientation);
  }
  
  private void launchSettings()
  {
    handleClose();
    launchSubActivity(SettingsActivity.class);
  }
  
  private void launchSubActivity(Class<? extends Activity> paramClass)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, paramClass);
    localIntent.setFlags(337641472);
    startActivity(localIntent);
  }
  
  private boolean maybeDoubleSpacePeriod()
  {
    Object localObject1 = this.mSettings.g;
    if (!((com.android.inputmethod.latin.settings.e)localObject1).F) {
      return false;
    }
    if (!((com.android.inputmethod.latin.settings.e)localObject1).q) {
      return false;
    }
    Object localObject2 = this.mHandler;
    if (SystemClock.uptimeMillis() - ((g)localObject2).d < ((g)localObject2).c) {}
    for (int i = 1; i == 0; i = 0) {
      return false;
    }
    localObject2 = this.mConnection.a(4);
    if (localObject2 == null) {
      return false;
    }
    i = ((CharSequence)localObject2).length();
    if (i < 3) {
      return false;
    }
    if (((CharSequence)localObject2).charAt(i - 1) != ' ') {
      return false;
    }
    if (((CharSequence)localObject2).charAt(i - 2) != ' ') {
      return false;
    }
    if (Character.isSurrogatePair(((CharSequence)localObject2).charAt(0), ((CharSequence)localObject2).charAt(1))) {}
    for (i = Character.codePointAt((CharSequence)localObject2, 0); canBeFollowedByDoubleSpacePeriod(i); i = ((CharSequence)localObject2).charAt(i - 3))
    {
      this.mHandler.d = 0L;
      this.mConnection.a(2, 0);
      localObject1 = new String(new int[] { ((com.android.inputmethod.latin.settings.e)localObject1).g, 32 }, 0, 2);
      this.mConnection.a((CharSequence)localObject1, 1);
      this.mKeyboardSwitcher.e();
      return true;
    }
    return false;
  }
  
  private w maybeRetrieveOlderSuggestions(String paramString, w paramW)
  {
    if ((paramW.g.size() > 1) || (paramString.length() <= 1) || (paramW.b) || (this.mSuggestionStripView == null) || (this.mSuggestionStripView.a())) {
      return paramW;
    }
    return getOlderSuggestions(paramString);
  }
  
  private boolean maybeStripSpace(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if ((10 == paramInt1) && (2 == paramInt2)) {
      this.mConnection.g();
    }
    com.android.inputmethod.latin.settings.e localE;
    do
    {
      do
      {
        return false;
      } while (((3 != paramInt2) && (2 != paramInt2)) || (!paramBoolean));
      localE = this.mSettings.g;
    } while (localE.f(paramInt1));
    if (localE.g(paramInt1)) {
      return true;
    }
    this.mConnection.g();
    return false;
  }
  
  private void onFinishInputInternal()
  {
    super.onFinishInput();
    MainKeyboardView localMainKeyboardView = this.mKeyboardSwitcher.c;
    if (localMainKeyboardView != null) {
      localMainKeyboardView.i();
    }
  }
  
  private void onFinishInputViewInternal(boolean paramBoolean)
  {
    super.onFinishInputView(paramBoolean);
    this.mKeyboardSwitcher.h = false;
    Object localObject = this.mKeyboardSwitcher;
    if (((com.android.inputmethod.keyboard.h)localObject).c != null)
    {
      ((com.android.inputmethod.keyboard.h)localObject).c.h();
      ((com.android.inputmethod.keyboard.h)localObject).c.b();
    }
    if (((com.android.inputmethod.keyboard.h)localObject).d != null)
    {
      localObject = ((com.android.inputmethod.keyboard.h)localObject).d.c;
      if (((EmojiView)localObject).h != null)
      {
        ((EmojiView)localObject).h.unregisterView();
        ((EmojiView)localObject).h.destroy();
        ((EmojiView)localObject).h = null;
      }
    }
    this.mHandler.removeMessages(2);
    if (this.mWordComposer.c()) {
      this.mConnection.c();
    }
    resetComposingState(true);
  }
  
  private void onSettingsKeyPressed()
  {
    if (isShowingOptionDialog()) {
      return;
    }
    showSubtypeSelectorAndSettings();
  }
  
  private void onStartInputInternal(EditorInfo paramEditorInfo, boolean paramBoolean)
  {
    super.onStartInput(paramEditorInfo, paramBoolean);
  }
  
  private void onStartInputViewInternal(EditorInfo paramEditorInfo, boolean paramBoolean)
  {
    boolean bool2 = true;
    super.onStartInputView(paramEditorInfo, paramBoolean);
    this.mRichImm.c();
    com.android.inputmethod.keyboard.h localH = this.mKeyboardSwitcher;
    MainKeyboardView localMainKeyboardView = localH.c;
    Object localObject1 = this.mSettings.g;
    Object localObject3;
    if (paramEditorInfo == null)
    {
      Log.e(TAG, "Null EditorInfo in onStartInputView()");
      if (q.a) {
        throw new NullPointerException("Null EditorInfo in onStartInputView()");
      }
    }
    else if (DEBUG)
    {
      Log.d(TAG, "onStartInputView: editorInfo:" + String.format("inputType=0x%08x imeOptions=0x%08x", new Object[] { Integer.valueOf(paramEditorInfo.inputType), Integer.valueOf(paramEditorInfo.imeOptions) }));
      localObject2 = TAG;
      localObject3 = new StringBuilder("All caps = ");
      if ((paramEditorInfo.inputType & 0x1000) == 0) {
        break label408;
      }
      bool1 = true;
      localObject3 = ((StringBuilder)localObject3).append(bool1).append(", sentence caps = ");
      if ((paramEditorInfo.inputType & 0x4000) == 0) {
        break label414;
      }
      bool1 = true;
      label188:
      localObject3 = ((StringBuilder)localObject3).append(bool1).append(", word caps = ");
      if ((paramEditorInfo.inputType & 0x2000) == 0) {
        break label420;
      }
    }
    label408:
    label414:
    label420:
    for (boolean bool1 = true;; bool1 = false)
    {
      Log.d((String)localObject2, bool1);
      if (n.a(null, "nm", paramEditorInfo))
      {
        Log.w(TAG, "Deprecated private IME option specified: " + paramEditorInfo.privateImeOptions);
        Log.w(TAG, "Use " + getPackageName() + ".noMicrophoneKey instead");
      }
      if (n.a(getPackageName(), "forceAscii", paramEditorInfo))
      {
        Log.w(TAG, "Deprecated private IME option specified: " + paramEditorInfo.privateImeOptions);
        Log.w(TAG, "Use EditorInfo.IME_FLAG_FORCE_ASCII flag instead");
      }
      localObject2 = ad.a(paramEditorInfo.packageName);
      this.mAppWorkAroundsUtils.a((PackageInfo)localObject2);
      if (localObject2 == null) {
        new ad(this, this).execute(new String[] { paramEditorInfo.packageName });
      }
      if (localMainKeyboardView != null) {
        break label426;
      }
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label188;
    }
    label426:
    Object localObject2 = emoji.keyboard.emoticonkeyboard.a.b.a();
    if ((((emoji.keyboard.emoticonkeyboard.a.b)localObject2).b()) && (((emoji.keyboard.emoticonkeyboard.a.b)localObject2).a(paramEditorInfo))) {
      ((emoji.keyboard.emoticonkeyboard.a.b)localObject2).a(localMainKeyboardView, ((emoji.keyboard.emoticonkeyboard.a.b)localObject2).a.getText(2131296482));
    }
    localObject2 = ((com.android.inputmethod.latin.settings.e)localObject1).A;
    int i;
    if (paramEditorInfo.inputType == ((n)localObject2).e)
    {
      i = 1;
      if (i != 0) {
        break label885;
      }
      i = 1;
      label493:
      if ((paramBoolean) && (i == 0)) {
        break label890;
      }
      bool1 = true;
      label504:
      if (bool1) {
        this.mSubtypeSwitcher.b();
      }
      updateFullscreenMode();
      this.mApplicationSpecifiedCompletions = null;
      this.mEnteredText = null;
      resetComposingState(true);
      this.mDeleteCount = 0;
      this.mSpaceState = 0;
      this.mRecapitalizeStatus.k = false;
      this.mCurrentlyPressedHardwareKeys.clear();
      localObject2 = this.mSubtypeSwitcher.e();
      localObject3 = this.mSuggest;
      if ((localObject3 != null) && (localObject2 != null) && (!((Locale)localObject2).equals(((v)localObject3).g))) {
        initSuggest();
      }
      if (this.mSuggestionStripView != null) {
        setPunctuationSuggestions();
      }
      this.mSuggestedWords = w.a;
      if (this.mConnection.a(paramEditorInfo.initialSelStart, false)) {
        break label896;
      }
      this.mHandler.a(bool1, 5);
      i = 0;
      label647:
      if (!bool1) {
        break label913;
      }
      localMainKeyboardView.i();
      loadSettings();
      localObject2 = this.mSettings.g;
      if ((localObject3 != null) && (((com.android.inputmethod.latin.settings.e)localObject2).F)) {
        ((v)localObject3).f = ((com.android.inputmethod.latin.settings.e)localObject2).E;
      }
      localH.a(paramEditorInfo, (com.android.inputmethod.latin.settings.e)localObject2);
      localObject1 = localObject2;
      if (i == 0)
      {
        localH.b();
        localObject1 = localObject2;
      }
      setSuggestionStripShownInternal(isSuggestionsStripVisible(), false);
      this.mKeyboardSwitcher.d();
      this.mLastSelectionStart = paramEditorInfo.initialSelStart;
      this.mLastSelectionEnd = paramEditorInfo.initialSelEnd;
      tryFixLyingCursorPosition();
      this.mHandler.removeMessages(2);
      this.mHandler.d = 0L;
      localMainKeyboardView.setMainDictionaryAvailability(this.mIsMainDictionaryAvailable);
      localMainKeyboardView.a(((com.android.inputmethod.latin.settings.e)localObject1).m, ((com.android.inputmethod.latin.settings.e)localObject1).D);
      localMainKeyboardView.setSlidingKeyInputPreviewEnabled(((com.android.inputmethod.latin.settings.e)localObject1).w);
      bool1 = ((com.android.inputmethod.latin.settings.e)localObject1).t;
      paramBoolean = ((com.android.inputmethod.latin.settings.e)localObject1).u;
      boolean bool3 = ((com.android.inputmethod.latin.settings.e)localObject1).v;
      com.android.inputmethod.keyboard.l.b(bool1);
      if ((!bool1) || (!paramBoolean)) {
        break label1014;
      }
      paramBoolean = true;
      label840:
      if ((!bool1) || (!bool3)) {
        break label1019;
      }
    }
    label885:
    label890:
    label896:
    label913:
    label1014:
    label1019:
    for (bool1 = bool2;; bool1 = false)
    {
      localMainKeyboardView.k.b = bool1;
      localMainKeyboardView.l.b = paramBoolean;
      initPersonalizationDebugSettings((com.android.inputmethod.latin.settings.e)localObject1);
      return;
      i = 0;
      break;
      i = 0;
      break label493;
      bool1 = false;
      break label504;
      if (bool1) {
        this.mHandler.c();
      }
      i = 1;
      break label647;
      if (paramBoolean) {}
      try
      {
        paramBoolean = this.mSubtypeSwitcher.f().getExtraValueOf("KeyboardLayoutSet").equals("emoji");
        if ((!paramBoolean) && (!localH.w()))
        {
          localObject2 = localH.g;
          if (!((com.android.inputmethod.keyboard.internal.w)localObject2).e)
          {
            ((com.android.inputmethod.keyboard.internal.w)localObject2).k = ((com.android.inputmethod.keyboard.internal.w)localObject2).i;
            ((com.android.inputmethod.keyboard.internal.w)localObject2).c();
            if (((com.android.inputmethod.keyboard.internal.w)localObject2).j) {
              ((com.android.inputmethod.keyboard.internal.w)localObject2).a(true);
            }
            ((com.android.inputmethod.keyboard.internal.w)localObject2).j = false;
          }
          localH.e();
        }
      }
      catch (NullPointerException localNullPointerException)
      {
        for (;;)
        {
          paramBoolean = false;
        }
      }
      paramBoolean = false;
      break label840;
    }
  }
  
  private void performEditorAction(int paramInt)
  {
    s localS = this.mConnection;
    localS.f = localS.e.getCurrentInputConnection();
    if (localS.f != null) {
      localS.f.performEditorAction(paramInt);
    }
  }
  
  private void resetComposingState(boolean paramBoolean)
  {
    this.mWordComposer.a();
    if (paramBoolean) {
      this.mLastComposedWord = p.i;
    }
  }
  
  private void resetContactsDictionary(g paramG)
  {
    v localV = this.mSuggest;
    int i;
    if ((localV != null) && (this.mSettings.g.p))
    {
      i = 1;
      if (i != 0) {
        break label61;
      }
      if (paramG != null) {
        paramG.d();
      }
      paramG = null;
    }
    for (;;)
    {
      if (localV != null)
      {
        localV.d = paramG;
        localV.a("contacts", paramG);
      }
      return;
      i = 0;
      break;
      label61:
      Locale localLocale = this.mSubtypeSwitcher.e();
      if (paramG != null)
      {
        if (!paramG.a.equals(localLocale))
        {
          paramG.d();
          paramG = new g(this, localLocale);
        }
        else
        {
          paramG.a(this);
        }
      }
      else {
        paramG = new g(this, localLocale);
      }
    }
  }
  
  private void resetEntireInputState(int paramInt)
  {
    boolean bool = this.mWordComposer.c();
    resetComposingState(true);
    com.android.inputmethod.latin.settings.e localE = this.mSettings.g;
    if (localE.s) {
      clearSuggestionStrip();
    }
    for (;;)
    {
      this.mConnection.a(paramInt, bool);
      return;
      setSuggestedWords(localE.e, false);
    }
  }
  
  private void restartSuggestionsOnWordBeforeCursor(String paramString)
  {
    this.mWordComposer.a(paramString, this.mKeyboardSwitcher.c());
    int i = paramString.length();
    this.mConnection.a(i, 0);
    this.mConnection.a(paramString);
    this.mHandler.b();
  }
  
  private void restartSuggestionsOnWordBeforeCursorIfAtEndOfWord()
  {
    Object localObject3 = null;
    Object localObject1 = this.mConnection;
    com.android.inputmethod.latin.settings.e localE = this.mSettings.g;
    Object localObject2 = ((s)localObject1).f();
    if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (!localE.c(((CharSequence)localObject2).charAt(0)))) {
      localObject2 = localObject3;
    }
    for (;;)
    {
      if (localObject2 != null) {
        restartSuggestionsOnWordBeforeCursor(((CharSequence)localObject2).toString());
      }
      return;
      localObject1 = ((s)localObject1).a(localE.f);
      if (localObject1 == null) {
        localObject1 = null;
      }
      while ((!TextUtils.isEmpty((CharSequence)localObject1)) && ('\'' == ((CharSequence)localObject1).charAt(0)))
      {
        localObject1 = ((CharSequence)localObject1).subSequence(1, ((CharSequence)localObject1).length());
        continue;
        localObject1 = ((ae)localObject1).e;
      }
      localObject2 = localObject3;
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        int i = Character.codePointBefore((CharSequence)localObject1, ((CharSequence)localObject1).length());
        localObject2 = localObject3;
        if (Character.isDefined(i))
        {
          localObject2 = localObject3;
          if (!localE.c(i))
          {
            char c = ((CharSequence)localObject1).charAt(0);
            if (((CharSequence)localObject1).length() == 1)
            {
              localObject2 = localObject3;
              if (!Character.isLetter(c)) {}
            }
            else
            {
              localObject2 = localObject3;
              if (Character.isLetter(c)) {
                localObject2 = localObject1;
              }
            }
          }
        }
      }
    }
  }
  
  private void restartSuggestionsOnWordTouchedByCursor()
  {
    if (this.mAppWorkAroundsUtils.a) {}
    label10:
    int i3;
    ArrayList localArrayList;
    final String str;
    do
    {
      do
      {
        do
        {
          do
          {
            break label10;
            break label10;
            do
            {
              return;
            } while ((!isSuggestionsStripVisible()) || (!this.mSettings.g.i) || (this.mLastSelectionStart != this.mLastSelectionEnd) || (this.mLastSelectionStart < 0));
            if (this.mKeyboardSwitcher.x())
            {
              this.mWordComposer.a();
              this.mConnection.c();
              return;
            }
            localObject1 = this.mSettings.g;
          } while (!this.mConnection.a((com.android.inputmethod.latin.settings.e)localObject1));
          localObject2 = this.mConnection.a(((com.android.inputmethod.latin.settings.e)localObject1).f);
        } while ((localObject2 == null) || (((ae)localObject2).e.length() <= 0));
        i3 = ((ae)localObject2).d - ((ae)localObject2).b;
      } while (i3 > this.mLastSelectionStart);
      localArrayList = new ArrayList();
      str = ((ae)localObject2).e.toString();
    } while (!isResumableWord(str, (com.android.inputmethod.latin.settings.e)localObject1));
    int m;
    if ((!(((ae)localObject2).a instanceof Spanned)) || (!(((ae)localObject2).e instanceof Spanned)))
    {
      localObject1 = new SuggestionSpan[0];
      m = localObject1.length;
      j = 0;
      i = 0;
    }
    int k;
    for (;;)
    {
      if (j >= m) {
        break label554;
      }
      Object localObject3 = localObject1[j].getSuggestions();
      int n = localObject3.length;
      k = 0;
      for (;;)
      {
        if (k < n)
        {
          CharSequence localCharSequence = localObject3[k];
          i += 1;
          if (!TextUtils.equals(localCharSequence, str)) {
            localArrayList.add(new w.a(localCharSequence, 18 - i, 9, h.f, -1, -1));
          }
          k += 1;
          continue;
          localObject3 = (Spanned)((ae)localObject2).a;
          localObject1 = (SuggestionSpan[])((Spanned)localObject3).getSpans(((ae)localObject2).b - 1, ((ae)localObject2).c + 1, SuggestionSpan.class);
          i = 0;
          for (j = 0; i < localObject1.length; j = n)
          {
            localCharSequence = localObject1[i];
            n = j;
            if (localCharSequence != null)
            {
              m = ((Spanned)localObject3).getSpanStart(localCharSequence);
              k = ((Spanned)localObject3).getSpanEnd(localCharSequence);
              n = i + 1;
              while (n < localObject1.length)
              {
                int i2 = k;
                int i1 = m;
                if (localCharSequence.equals(localObject1[n]))
                {
                  i1 = Math.min(m, ((Spanned)localObject3).getSpanStart(localObject1[n]));
                  i2 = Math.max(k, ((Spanned)localObject3).getSpanEnd(localObject1[n]));
                  localObject1[n] = null;
                }
                n += 1;
                k = i2;
                m = i1;
              }
              n = j;
              if (m == ((ae)localObject2).b)
              {
                n = j;
                if (k == ((ae)localObject2).c)
                {
                  localObject1[j] = localObject1[i];
                  n = j + 1;
                }
              }
            }
            i += 1;
          }
          if (j == i) {
            break;
          }
          localObject1 = (SuggestionSpan[])Arrays.copyOfRange((Object[])localObject1, 0, j);
          break;
        }
      }
      j += 1;
    }
    label554:
    this.mWordComposer.a(str, this.mKeyboardSwitcher.c());
    this.mWordComposer.m = str.codePointCount(0, i3);
    Object localObject1 = this.mConnection;
    int i = this.mLastSelectionStart - i3;
    int j = this.mLastSelectionEnd + (((ae)localObject2).c - ((ae)localObject2).d);
    Object localObject2 = ((s)localObject1).a(j - i + 1024);
    ((s)localObject1).c.setLength(0);
    if (!TextUtils.isEmpty((CharSequence)localObject2))
    {
      k = Math.max(((CharSequence)localObject2).length() - (j - i), 0);
      ((s)localObject1).d.append(((CharSequence)localObject2).subSequence(k, ((CharSequence)localObject2).length()));
      ((s)localObject1).c.append(((CharSequence)localObject2).subSequence(0, k));
    }
    if (((s)localObject1).f != null) {
      ((s)localObject1).f.setComposingRegion(i, j);
    }
    if (localArrayList.isEmpty())
    {
      this.mInputUpdater.a(0, new v.a()
      {
        public final void a(w paramAnonymousW)
        {
          if (paramAnonymousW.g.size() > 1)
          {
            ArrayList localArrayList = new ArrayList();
            int i = 0;
            while (i < paramAnonymousW.g.size())
            {
              w.a localA = (w.a)paramAnonymousW.g.get(i);
              if (localA.c != 0) {
                localArrayList.add(localA);
              }
              i += 1;
            }
            paramAnonymousW = new w(localArrayList, true, false, paramAnonymousW.d, paramAnonymousW.e, paramAnonymousW.f);
          }
          for (;;)
          {
            LatinIME.this.unsetIsAutoCorrectionIndicatorOnAndCallShowSuggestionStrip(paramAnonymousW, str);
            return;
          }
        }
      });
      return;
    }
    unsetIsAutoCorrectionIndicatorOnAndCallShowSuggestionStrip(new w(localArrayList, true, false, false, false, false), str);
  }
  
  private void retryResetCaches(boolean paramBoolean, int paramInt)
  {
    if ((!this.mConnection.a(this.mLastSelectionStart, false)) && (paramInt > 0)) {
      this.mHandler.a(paramBoolean, paramInt - 1);
    }
    do
    {
      return;
      tryFixLyingCursorPosition();
      this.mKeyboardSwitcher.a(getCurrentInputEditorInfo(), this.mSettings.g);
    } while (!paramBoolean);
    this.mHandler.c();
  }
  
  private void revertCommit()
  {
    String str1 = this.mLastComposedWord.e;
    String str2 = this.mLastComposedWord.b;
    String str3 = this.mLastComposedWord.c;
    int i = str3.length();
    int j = this.mLastComposedWord.d.length() + i;
    Object localObject;
    if (DEBUG)
    {
      if (this.mWordComposer.c()) {
        throw new RuntimeException("revertCommit, but we are composing a word");
      }
      localObject = this.mConnection.a(j).subSequence(0, i);
      if (!TextUtils.equals(str3, (CharSequence)localObject)) {
        throw new RuntimeException("revertCommit check failed: we thought we were reverting \"" + str3 + "\", but before the cursor we found \"" + localObject + "\"");
      }
    }
    this.mConnection.a(j, 0);
    if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str3)))
    {
      localObject = this.mUserHistoryDictionary;
      if (!((l)localObject).m) {
        Log.w(l.b, "removeBigramDynamically is called for non-updatable dictionary: " + ((l)localObject).l);
      }
    }
    else
    {
      str1 = str2 + this.mLastComposedWord.d;
      if (!this.mSettings.g.i) {
        break label322;
      }
      this.mConnection.a(str1, 1);
    }
    for (;;)
    {
      if (this.mSettings.g.N) {
        com.android.inputmethod.latin.d.s.a(this.mLastComposedWord.d, -1, -1);
      }
      this.mLastComposedWord = p.i;
      this.mHandler.b();
      return;
      l.c(((l)localObject).l).a(new l.12((l)localObject, str1, str3));
      break;
      label322:
      this.mWordComposer.a(str1, this.mKeyboardSwitcher.c());
      this.mConnection.a(str1);
    }
  }
  
  private void searchSuggestion(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.putExtra("query", paramString);
    localIntent.setFlags(268435456);
    localIntent.setClassName(getPackageName(), "emoji.keyboard.searchbox.SearchActivity");
    startActivity(localIntent);
    emoji.keyboard.emoticonkeyboard.extras.d.c(this, "Top_Panel_Switch_Search");
  }
  
  private void sendDownUpKeyEvent(int paramInt)
  {
    long l = SystemClock.uptimeMillis();
    this.mConnection.a(new KeyEvent(l, l, 0, paramInt, 0, 0, -1, 0, 6));
    this.mConnection.a(new KeyEvent(SystemClock.uptimeMillis(), l, 1, paramInt, 0, 0, -1, 0, 6));
  }
  
  private void sendDownUpKeyEventWithMetaState(int paramInt1, int paramInt2)
  {
    long l = SystemClock.uptimeMillis();
    this.mConnection.a(new KeyEvent(l, l, 0, paramInt1, 0, paramInt2, -1, 0, 2));
    this.mConnection.a(new KeyEvent(SystemClock.uptimeMillis(), l, 1, paramInt1, 0, paramInt2, -1, 0, 2));
  }
  
  private void sendKeyCodePoint(int paramInt)
  {
    if ((paramInt >= 48) && (paramInt <= 57))
    {
      sendDownUpKeyEvent(paramInt - 48 + 7);
      return;
    }
    if ((10 == paramInt) && (this.mAppWorkAroundsUtils.a()))
    {
      sendDownUpKeyEvent(66);
      return;
    }
    this.mConnection.a(ab.a(paramInt), 1);
  }
  
  private void setAutoCorrection(w paramW, String paramString)
  {
    if (paramW.g.isEmpty()) {
      return;
    }
    if (paramW.c) {
      paramString = paramW.a(1);
    }
    this.mWordComposer.d = paramString;
  }
  
  private void setAutoCorrectionIndicator(boolean paramBoolean)
  {
    if ((this.mIsAutoCorrectionIndicatorOn != paramBoolean) && (this.mWordComposer.c()))
    {
      this.mIsAutoCorrectionIndicatorOn = paramBoolean;
      CharSequence localCharSequence = getTextWithUnderline(this.mWordComposer.c.toString());
      this.mConnection.a(localCharSequence);
    }
  }
  
  private void setPunctuationSuggestions()
  {
    com.android.inputmethod.latin.settings.e localE = this.mSettings.g;
    if (localE.s) {
      clearSuggestionStrip();
    }
    for (;;)
    {
      setAutoCorrectionIndicator(false);
      setSuggestionStripShown(isSuggestionsStripVisible());
      return;
      setSuggestedWords(localE.e, false);
    }
  }
  
  private void setSuggestedWords(w paramW, boolean paramBoolean)
  {
    this.mSuggestedWords = paramW;
    String str;
    if (this.mSuggestionStripView != null)
    {
      boolean bool = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("top_bar_mic", true);
      if ((!paramW.g.isEmpty()) || (!com.android.inputmethod.latin.settings.c.a().g.a(this.mCurrentEditorInfo)) || (!bool)) {
        break label183;
      }
      this.mVoiceInputButton.setVisibility(0);
      this.mVoiceInputButton.setTag(Integer.valueOf(2));
      this.mVoiceInputButton.setImageResource(2130838057);
      this.mVoiceRedPoint.setVisibility(8);
      str = "";
      if (!paramW.g.isEmpty()) {
        break label195;
      }
      this.mTopSuggestionSettingView.setVisibility(0);
      this.mSuggestionStripView.setVisibility(4);
    }
    for (;;)
    {
      updateRedPoint("IS_TOPMENU_CLICKED_ONCE");
      this.mTopMenuButtonClickListener.a = str;
      this.mSuggestionStripView.setSuggestions(paramW);
      paramW = this.mKeyboardSwitcher;
      if (paramW.h != paramBoolean)
      {
        paramW.h = paramBoolean;
        if (paramW.c != null) {
          paramW.c.b(paramBoolean);
        }
      }
      return;
      label183:
      this.mVoiceInputButton.setVisibility(8);
      break;
      label195:
      this.mTopSuggestionSettingView.setVisibility(8);
      this.mSuggestionStripView.setVisibility(0);
      if (!com.myandroid.promotion.b.b.b(this))
      {
        updateRedPoint("IS_VOICE_SEARCH_CLICKED_ONCE");
        this.mVoiceInputButton.setImageResource(2130838089);
        this.mVoiceInputButton.setVisibility(0);
        this.mVoiceInputButton.setTag(Integer.valueOf(1));
        str = paramW.a(0);
      }
    }
  }
  
  private void setSuggestionStripShown(boolean paramBoolean)
  {
    setSuggestionStripShownInternal(paramBoolean, true);
  }
  
  private void setSuggestionStripShownInternal(boolean paramBoolean1, boolean paramBoolean2)
  {
    int n = 1;
    int m = 0;
    int k = 0;
    label80:
    int j;
    if ((onEvaluateInputViewShown()) && (this.mTopMenuSuggestionContainerView != null))
    {
      localObject = this.mKeyboardSwitcher;
      if ((((com.android.inputmethod.keyboard.h)localObject).c == null) || (!((com.android.inputmethod.keyboard.h)localObject).c.isShown())) {
        break label128;
      }
      i = 1;
      if ((i == 0) && (!((com.android.inputmethod.keyboard.h)localObject).w()) && (!((com.android.inputmethod.keyboard.h)localObject).x()) && (!((com.android.inputmethod.keyboard.h)localObject).y())) {
        break label133;
      }
      i = 1;
      if (!paramBoolean1) {
        break label138;
      }
      j = n;
      if (paramBoolean2)
      {
        if (i == 0) {
          break label138;
        }
        j = n;
      }
      label100:
      if (!isFullscreenMode()) {
        break label150;
      }
      localObject = this.mTopMenuSuggestionContainerView;
      if (j == 0) {
        break label144;
      }
    }
    label128:
    label133:
    label138:
    label144:
    for (int i = k;; i = 8)
    {
      ((View)localObject).setVisibility(i);
      return;
      i = 0;
      break;
      i = 0;
      break label80;
      j = 0;
      break label100;
    }
    label150:
    Object localObject = this.mTopMenuSuggestionContainerView;
    if (j != 0) {}
    for (i = m;; i = 4)
    {
      ((View)localObject).setVisibility(i);
      return;
    }
  }
  
  private void showGesturePreviewAndSuggestionStrip(w paramW, boolean paramBoolean)
  {
    showSuggestionStrip(paramW);
    MainKeyboardView localMainKeyboardView = this.mKeyboardSwitcher.c;
    localMainKeyboardView.a(paramW);
    if (paramBoolean) {
      localMainKeyboardView.e();
    }
  }
  
  private void showSubtypeSelectorAndSettings()
  {
    String str1 = getString(2131296356);
    String str2 = getString(2131296394);
    String str3 = getString(com.android.inputmethod.latin.d.b.a(this, SettingsActivity.class));
    DialogInterface.OnClickListener local6 = new DialogInterface.OnClickListener()
    {
      public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 0: 
          paramAnonymousDialogInterface = com.android.inputmethod.latin.d.p.a(LatinIME.this.mRichImm.b.getId());
          LatinIME.this.startActivity(paramAnonymousDialogInterface);
          return;
        }
        LatinIME.this.launchSettings();
      }
    };
    showOptionDialog(new AlertDialog.Builder(this).setItems(new CharSequence[] { str2, str3 }, local6).setTitle(str1).create());
  }
  
  private void showSuggestionStrip(w paramW)
  {
    if (paramW.g.isEmpty())
    {
      clearSuggestionStrip();
      return;
    }
    showSuggestionStripWithTypedWord(paramW, paramW.a(0));
  }
  
  private void showSuggestionStripWithTypedWord(w paramW, String paramString)
  {
    if (paramW.g.isEmpty())
    {
      emoji.keyboard.emoticonkeyboard.a.b.a().a(null, null);
      clearSuggestionStrip();
      return;
    }
    setAutoCorrection(paramW, paramString);
    boolean bool = paramW.c;
    setSuggestedWords(paramW, bool);
    setAutoCorrectionIndicator(bool);
    setSuggestionStripShown(isSuggestionsStripVisible());
    emoji.keyboard.emoticonkeyboard.a.b.a().a(paramW, paramString);
  }
  
  private String specificTldProcessingOnTextInput(String paramString)
  {
    if ((paramString.length() <= 1) || (paramString.charAt(0) != '.') || (!Character.isLetter(paramString.charAt(1)))) {}
    CharSequence localCharSequence;
    do
    {
      return paramString;
      this.mSpaceState = 0;
      localCharSequence = this.mConnection.a(1);
    } while ((localCharSequence == null) || (localCharSequence.length() != 1) || (localCharSequence.charAt(0) != '.'));
    return paramString.substring(1);
  }
  
  private void swapSwapperAndSpace()
  {
    Object localObject = this.mConnection.a(2);
    if ((localObject != null) && (((CharSequence)localObject).length() == 2) && (((CharSequence)localObject).charAt(0) == ' '))
    {
      this.mConnection.a(2, 0);
      localObject = ((CharSequence)localObject).charAt(1) + " ";
      this.mConnection.a((CharSequence)localObject, 1);
      this.mKeyboardSwitcher.e();
    }
  }
  
  private void tryFixLyingCursorPosition()
  {
    CharSequence localCharSequence = this.mConnection.a(1024);
    if (localCharSequence == null)
    {
      this.mLastSelectionEnd = -1;
      this.mLastSelectionStart = -1;
    }
    do
    {
      int i;
      do
      {
        return;
        i = localCharSequence.length();
      } while ((i <= this.mLastSelectionStart) && ((i >= 1024) || (this.mLastSelectionStart >= 1024)));
      this.mLastSelectionStart = i;
    } while (this.mLastSelectionStart <= this.mLastSelectionEnd);
    this.mLastSelectionEnd = this.mLastSelectionStart;
  }
  
  private void tryToShowAppPromotion()
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    if ((localSharedPreferences.getBoolean("pref_app_promotion_turn_on", false)) && (!localSharedPreferences.getBoolean("pref_app_promotion_show_in_app", false))) {
      localSharedPreferences.edit().putBoolean("pref_app_promotion_show_in_app", com.myandroid.promotion.b.b.a(this, true)).apply();
    }
  }
  
  private void updateRedPoint(String paramString)
  {
    boolean bool = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(paramString, false);
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return;
        if (paramString.equals("IS_TOPMENU_CLICKED_ONCE"))
        {
          i = 0;
          continue;
          if (paramString.equals("IS_VOICE_SEARCH_CLICKED_ONCE")) {
            i = 1;
          }
        }
        break;
      }
    }
    if (bool)
    {
      this.mTopMenuBtnRedPoint.setVisibility(8);
      return;
    }
    this.mTopMenuBtnRedPoint.setVisibility(0);
    return;
    if (bool)
    {
      this.mVoiceRedPoint.setVisibility(8);
      return;
    }
    this.mVoiceRedPoint.setVisibility(0);
  }
  
  private void updateSuggestionStrip()
  {
    this.mHandler.removeMessages(2);
    Object localObject = this.mSettings.g;
    if ((this.mSuggest == null) || (!((com.android.inputmethod.latin.settings.e)localObject).a(this.mDisplayOrientation))) {
      if (this.mWordComposer.c()) {
        Log.w(TAG, "Called updateSuggestionsOrPredictions but suggestions were not requested!");
      }
    }
    do
    {
      return;
      if ((!this.mWordComposer.c()) && (!((com.android.inputmethod.latin.settings.e)localObject).s))
      {
        setPunctuationSuggestions();
        return;
      }
      localObject = new com.android.inputmethod.latin.d.d();
      getSuggestedWordsOrOlderSuggestionsAsync(0, new v.a()
      {
        public final void a(w paramAnonymousW)
        {
          this.a.a(paramAnonymousW);
        }
      });
      localObject = (w)((com.android.inputmethod.latin.d.d)localObject).a(null, 200L);
    } while (localObject == null);
    showSuggestionStrip((w)localObject);
  }
  
  public void addWordToUserDictionary(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    int i = this.mLastComposedWord.f;
    if ((5 == i) || (7 == i)) {}
    for (i = 1;; i = 0)
    {
      String str = paramString;
      if (i != 0) {
        str = paramString.toLowerCase(this.mSubtypeSwitcher.e());
      }
      this.mUserDictionary.f(str);
      return;
    }
  }
  
  public void debugDumpStateAndCrashWithException(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(this.mAppWorkAroundsUtils.toString());
    localStringBuilder.append("\nAttributes : ").append(this.mSettings.g.A).append("\nContext : ").append(paramString);
    throw new RuntimeException(localStringBuilder.toString());
  }
  
  public void destroyViews()
  {
    this.mSuggestionStripView = null;
    this.mKeyPreviewBackingView = null;
    this.mTopSuggestionSettingView = null;
    this.mTopMenuSuggestionContainerView = null;
    this.mVoiceInputButton = null;
  }
  
  protected void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramFileDescriptor = new PrintWriterPrinter(paramPrintWriter);
    paramFileDescriptor.println("LatinIME state :");
    paramPrintWriter = this.mKeyboardSwitcher.c();
    if (paramPrintWriter != null) {}
    for (int i = paramPrintWriter.a.e;; i = -1)
    {
      paramFileDescriptor.println("  Keyboard mode = " + i);
      paramPrintWriter = this.mSettings.g;
      paramFileDescriptor.println("  mIsSuggestionsSuggestionsRequested = " + paramPrintWriter.a(this.mDisplayOrientation));
      paramFileDescriptor.println("  mCorrectionEnabled=" + paramPrintWriter.F);
      paramFileDescriptor.println("  isComposingWord=" + this.mWordComposer.c());
      paramFileDescriptor.println("  mSoundOn=" + paramPrintWriter.l);
      paramFileDescriptor.println("  mVibrateOn=" + paramPrintWriter.k);
      paramFileDescriptor.println("  mKeyPreviewPopupOn=" + paramPrintWriter.m);
      paramFileDescriptor.println("  inputAttributes=" + paramPrintWriter.A);
      return;
    }
  }
  
  public void getClipArrayFromFile()
  {
    com.google.a.f localF = new com.google.a.f();
    try
    {
      FileInputStream localFileInputStream = openFileInput("clip_text_list_file");
      StringBuffer localStringBuffer = new StringBuffer("");
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i = localFileInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localStringBuffer.append(new String(arrayOfByte, 0, i));
      }
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      return;
      mClipArray = (ArrayList)localFileNotFoundException.a(localStringBuffer.toString(), new com.google.a.c.a() {}.getType());
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  public int getCurrentAutoCapsState()
  {
    int i1 = 0;
    com.android.inputmethod.latin.settings.e localE = this.mSettings.g;
    if (!localE.j) {}
    do
    {
      return 0;
      localObject = getCurrentInputEditorInfo();
    } while (localObject == null);
    int i2 = ((EditorInfo)localObject).inputType;
    Object localObject = this.mConnection;
    if (4 == this.mSpaceState) {}
    for (int n = 1;; n = 0)
    {
      ((s)localObject).f = ((s)localObject).e.getCurrentInputConnection();
      if (((s)localObject).f == null) {
        break;
      }
      if (TextUtils.isEmpty(((s)localObject).d)) {
        break label114;
      }
      if (n == 0) {
        break label107;
      }
      return i2 & 0x3000;
    }
    label107:
    return i2 & 0x1000;
    label114:
    if ((TextUtils.isEmpty(((s)localObject).c)) && (((s)localObject).b != 0))
    {
      CharSequence localCharSequence = ((s)localObject).a(1024);
      if (!TextUtils.isEmpty(localCharSequence)) {
        ((s)localObject).c.append(localCharSequence);
      }
    }
    localObject = ((s)localObject).c;
    if ((i2 & 0x6000) == 0) {
      return i2 & 0x1000;
    }
    int m;
    int i;
    int k;
    if (n != 0)
    {
      m = ((CharSequence)localObject).length() + 1;
      i = 32;
      if (n == 0) {
        break label684;
      }
      k = m - 1;
    }
    for (;;)
    {
      int j = i;
      if (k > 0)
      {
        i = ((CharSequence)localObject).charAt(k - 1);
        if (!Character.isSpaceChar(i))
        {
          j = i;
          if (i != 9) {}
        }
        else
        {
          k -= 1;
          continue;
          k = ((CharSequence)localObject).length();
          for (;;)
          {
            m = k;
            if (k <= 0) {
              break;
            }
            i = ((CharSequence)localObject).charAt(k - 1);
            if ((i != 34) && (i != 39))
            {
              m = k;
              if (Character.getType(i) != 21) {
                break;
              }
            }
            k -= 1;
          }
        }
      }
      if ((k <= 0) || (Character.isWhitespace(j))) {
        return i2 & 0x7000;
      }
      if (m == k) {
        return i2 & 0x1000;
      }
      if ((i2 & 0x4000) == 0) {
        return i2 & 0x3000;
      }
      m = k;
      if (Locale.ENGLISH.getLanguage().equals(localE.z.getLanguage())) {
        for (;;)
        {
          m = k;
          if (k <= 0) {
            break;
          }
          i = ((CharSequence)localObject).charAt(k - 1);
          if ((i != 34) && (i != 39))
          {
            m = k;
            if (Character.getType(i) != 22) {
              break;
            }
          }
          k -= 1;
        }
      }
      if (m <= 0) {
        return i2 & 0x1000;
      }
      m -= 1;
      k = ((CharSequence)localObject).charAt(m);
      if ((k == 63) || (k == 33)) {
        return i2 & 0x5000;
      }
      if ((localE.g != k) || (m <= 0)) {
        return i2 & 0x3000;
      }
      n = i2 & 0x7000;
      i2 &= 0x3000;
      k = i1;
      while (m > 0)
      {
        m -= 1;
        i = ((CharSequence)localObject).charAt(m);
        switch (k)
        {
        default: 
          break;
        case 0: 
          if (Character.isLetter(i))
          {
            k = 1;
          }
          else
          {
            if (Character.isWhitespace(i)) {
              return i2;
            }
            return n;
          }
          break;
        case 1: 
          if (Character.isLetter(i)) {
            k = 1;
          } else if (localE.g == i) {
            k = 2;
          } else {
            return n;
          }
          break;
        case 2: 
          if (Character.isLetter(i)) {
            k = 3;
          } else {
            return n;
          }
          break;
        case 3: 
          if (Character.isLetter(i)) {
            k = 3;
          } else if (localE.g == i) {
            k = 2;
          } else {
            return i2;
          }
          break;
        }
      }
      if ((k == 0) || (3 == k)) {
        return i2;
      }
      return n;
      label684:
      k = m;
    }
  }
  
  public String getCurrentPackage()
  {
    if (this.mCurrentEditorInfo == null) {
      return null;
    }
    return this.mCurrentEditorInfo.packageName;
  }
  
  public int getCurrentRecapitalizeState()
  {
    if ((!this.mRecapitalizeStatus.k) || (!this.mRecapitalizeStatus.a(this.mLastSelectionStart, this.mLastSelectionEnd))) {
      return -1;
    }
    com.android.inputmethod.latin.d.v localV = this.mRecapitalizeStatus;
    return com.android.inputmethod.latin.d.v.a[localV.f];
  }
  
  String getFirstSuggestedWord()
  {
    if (this.mSuggestedWords.g.size() > 0) {
      return this.mSuggestedWords.a(0);
    }
    return null;
  }
  
  public s getRichInputConnection()
  {
    return this.mConnection;
  }
  
  boolean hasMainDictionary()
  {
    return this.mSuggest.a();
  }
  
  public void hideWindow()
  {
    this.mKeyboardSwitcher.h = false;
    if (emoji.keyboard.emoticonkeyboard.a.b.a().b.isEnabled())
    {
      emoji.keyboard.emoticonkeyboard.a.c localC = emoji.keyboard.emoticonkeyboard.a.c.a();
      if (localC.c != null)
      {
        localC.a(localC.c.getContext().getString(2131296278));
        localC.g = -1;
      }
    }
    if ((this.mOptionsDialog != null) && (this.mOptionsDialog.isShowing()))
    {
      this.mOptionsDialog.dismiss();
      this.mOptionsDialog = null;
    }
    super.hideWindow();
  }
  
  boolean isCurrentlyWaitingForMainDictionary()
  {
    return this.mSuggest.e;
  }
  
  boolean isShowingPunctuationList()
  {
    if (this.mSuggestedWords == null) {}
    while (this.mSettings.g.e != this.mSuggestedWords) {
      return false;
    }
    return true;
  }
  
  public void launchKeyboardedDialogActivity(Class<? extends Activity> paramClass)
  {
    commitTyped("");
    launchSubActivity(paramClass);
  }
  
  void loadKeyboard()
  {
    g localG = this.mHandler;
    localG.sendMessage(localG.obtainMessage(5));
    loadSettings();
    if (this.mKeyboardSwitcher.c != null) {
      this.mKeyboardSwitcher.a(getCurrentInputEditorInfo(), this.mSettings.g);
    }
  }
  
  void loadSettings()
  {
    Object localObject = this.mSubtypeSwitcher.e();
    n localN = new n(getCurrentInputEditorInfo(), isFullscreenMode());
    this.mSettings.a((Locale)localObject, localN);
    localObject = c.a();
    ((c)localObject).a = this.mSettings.g;
    ((c)localObject).b = ((c)localObject).c();
    if (!this.mHandler.hasMessages(5)) {
      if (this.mSuggest != null) {
        break label82;
      }
    }
    label82:
    for (localObject = null;; localObject = this.mSuggest.d)
    {
      resetContactsDictionary((g)localObject);
      return;
    }
  }
  
  public void onCancelBatchInput()
  {
    d localD = this.mInputUpdater;
    synchronized (localD.c)
    {
      localD.d = false;
      localD.b.mHandler.a(w.a, true);
      return;
    }
  }
  
  public void onCancelInput() {}
  
  public void onCodeInput(int paramInt1, int paramInt2, int paramInt3)
  {
    long l = SystemClock.uptimeMillis();
    if ((paramInt1 != -5) || (l > this.mLastKeyTime + 200L)) {
      this.mDeleteCount = 0;
    }
    this.mLastKeyTime = l;
    this.mConnection.a();
    com.android.inputmethod.keyboard.h localH = this.mKeyboardSwitcher;
    int i = this.mSpaceState;
    if (!this.mWordComposer.c()) {
      this.mIsAutoCorrectionIndicatorOn = false;
    }
    if (paramInt1 != 32) {
      this.mHandler.d = 0L;
    }
    boolean bool2 = false;
    boolean bool1 = bool2;
    switch (paramInt1)
    {
    case -13: 
    case -4: 
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    default: 
      bool1 = handleNonSpecialCharacter(paramInt1, paramInt2, paramInt3, i);
      exitFromSelectionMode();
    }
    for (;;)
    {
      localH.a(paramInt1);
      if ((!bool1) && (paramInt1 != -1) && (paramInt1 != -2) && (paramInt1 != -3)) {
        this.mLastComposedWord.h = false;
      }
      if (-5 != paramInt1) {
        this.mEnteredText = null;
      }
      this.mConnection.b();
      return;
      this.mSpaceState = 0;
      handleBackspace(i);
      bool1 = bool2;
      continue;
      Object localObject1 = localH.c();
      bool1 = bool2;
      if (localObject1 != null)
      {
        bool1 = bool2;
        if (com.android.inputmethod.keyboard.f.a(((com.android.inputmethod.keyboard.d)localObject1).a.f))
        {
          handleRecapitalize();
          bool1 = bool2;
          continue;
          this.mKeyboardSwitcher.D();
          bool1 = bool2;
          continue;
          localObject1 = this.mSubtypeSwitcher;
          bool1 = bool2;
          if (((u)localObject1).d != null)
          {
            Object localObject2 = ((u)localObject1).d.getId();
            InputMethodSubtype localInputMethodSubtype = ((u)localObject1).e;
            IBinder localIBinder = getWindow().getWindow().getAttributes().token;
            bool1 = bool2;
            if (localIBinder != null)
            {
              new u.1((u)localObject1, ((u)localObject1).b.b(), localIBinder, (String)localObject2, localInputMethodSubtype).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
              bool1 = bool2;
              continue;
              performEditorAction(5);
              bool1 = bool2;
              continue;
              performEditorAction(7);
              bool1 = bool2;
              continue;
              handleLanguageSwitchKey();
              bool1 = bool2;
              continue;
              localObject1 = getCurrentInputEditorInfo();
              int j = com.android.inputmethod.latin.d.o.a((EditorInfo)localObject1);
              if (256 == j)
              {
                performEditorAction(((EditorInfo)localObject1).actionId);
                bool1 = false;
              }
              for (;;)
              {
                exitFromSelectionMode();
                break;
                if (1 != j)
                {
                  performEditorAction(j);
                  bool1 = false;
                }
                else
                {
                  bool1 = handleNonSpecialCharacter(10, paramInt2, paramInt3, i);
                }
              }
              bool1 = handleNonSpecialCharacter(10, paramInt2, paramInt3, i);
              continue;
              handleSwitchLastMethod();
              bool1 = bool2;
              continue;
              handleMovementKeys(paramInt1);
              bool1 = bool2;
              continue;
              this.mConnection.a(new KeyEvent(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 59, 0, 0, -1, 0, 2));
              this.mIsSelectionEnabled = true;
              bool1 = bool2;
              continue;
              exitFromSelectionMode();
              this.mConnection.b(this.mLastSelectionStart, this.mLastSelectionStart);
              bool1 = bool2;
              continue;
              handleCopyPastKeys(paramInt1);
              bool1 = bool2;
              continue;
              paramInt2 = 0;
              localObject1 = getCurrentInputConnection();
              bool1 = bool2;
              if (localObject1 != null)
              {
                localObject2 = ((InputConnection)localObject1).getTextBeforeCursor(1024, 0);
                if (localObject2 != null) {
                  paramInt2 = ((CharSequence)localObject2).length() + 0;
                }
                localObject1 = ((InputConnection)localObject1).getTextAfterCursor(1024, 0);
                paramInt3 = paramInt2;
                if (localObject1 != null) {
                  paramInt3 = paramInt2 + ((CharSequence)localObject1).length();
                }
                paramInt2 = Math.abs(this.mLastSelectionEnd - this.mLastSelectionStart);
                this.mConnection.b(0, paramInt3 + paramInt2);
                bool1 = bool2;
                continue;
                paramInt2 = 0;
                localObject1 = getCurrentInputConnection();
                bool1 = bool2;
                if (localObject1 != null)
                {
                  localObject2 = ((InputConnection)localObject1).getTextBeforeCursor(1024, 0);
                  if (localObject2 != null) {
                    paramInt2 = ((CharSequence)localObject2).length() + 0;
                  }
                  localObject1 = ((InputConnection)localObject1).getTextAfterCursor(1024, 0);
                  paramInt3 = paramInt2;
                  if (localObject1 != null) {
                    paramInt3 = paramInt2 + ((CharSequence)localObject1).length();
                  }
                  paramInt2 = Math.abs(this.mLastSelectionEnd - this.mLastSelectionStart);
                  this.mConnection.b(0, paramInt3 + paramInt2);
                  sendDownUpKeyEvent(67);
                  exitFromSelectionMode();
                  bool1 = bool2;
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void onComputeInsets(InputMethodService.Insets paramInsets)
  {
    super.onComputeInsets(paramInsets);
    View localView = this.mKeyboardSwitcher.z();
    if ((localView == null) || (this.mSuggestionStripView == null)) {
      return;
    }
    int i = getAdjustedBackingViewHeight();
    int j;
    label67:
    int m;
    label82:
    int k;
    label144:
    com.android.inputmethod.keyboard.h localH;
    boolean bool;
    label161:
    label168:
    int n;
    if (this.mKeyPreviewBackingView.getVisibility() == 8)
    {
      j = 1;
      if (j != 0) {
        i = 0;
      }
      if (!isFullscreenMode()) {
        break label285;
      }
      j = this.mExtractArea.getHeight();
      if (this.mTopMenuSuggestionContainerView.getVisibility() != 8) {
        break label290;
      }
      m = 0;
      k = i + j + m;
      if (!localView.isShown()) {
        break label367;
      }
      if ((!this.mKeyboardSwitcher.w()) && (!this.mKeyboardSwitcher.x()) && (!this.mKeyboardSwitcher.y()) && (this.mTopMenuSuggestionContainerView.getVisibility() != 0)) {
        break label361;
      }
      i = k - m;
      localH = this.mKeyboardSwitcher;
      if (!localH.w()) {
        break label302;
      }
      bool = false;
      if (!bool) {
        break label315;
      }
      j = 0;
      n = localView.getWidth();
      m = k + localView.getHeight() + 100;
      paramInsets.touchableInsets = 3;
      if (!this.mIsNeededHackingForSM2k) {
        break label354;
      }
      if (getResources().getConfiguration().orientation != 2) {
        break label320;
      }
      k = 2560;
      m = 1151;
      j = 239;
      label228:
      paramInsets.touchableRegion.set(0, j, k, m);
    }
    for (;;)
    {
      j = i;
      if (this.mIsNeededHackingForSM2k) {
        if (getResources().getConfiguration().orientation != 2) {
          break label345;
        }
      }
      label285:
      label290:
      label302:
      label315:
      label320:
      label345:
      for (j = 273;; j = i + 360)
      {
        paramInsets.contentTopInsets = j;
        paramInsets.visibleTopInsets = j;
        return;
        j = 0;
        break;
        j = 0;
        break label67;
        m = this.mTopMenuSuggestionContainerView.getHeight();
        break label82;
        bool = localH.c.f();
        break label161;
        j = i;
        break label168;
        k = n + 360;
        m += 474;
        j += 284;
        break label228;
      }
      label354:
      k = n;
      break label228;
      label361:
      i = k;
      break label144;
      label367:
      i = k;
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (this.mDisplayOrientation != paramConfiguration.orientation)
    {
      this.mDisplayOrientation = paramConfiguration.orientation;
      Object localObject = this.mHandler;
      ((g)localObject).removeMessages(1);
      ((g)localObject).d();
      ((g)localObject).h = true;
      localObject = (LatinIME)((com.android.inputmethod.latin.d.aa)localObject).n.get();
      if (((LatinIME)localObject).isInputViewShown()) {
        ((LatinIME)localObject).mKeyboardSwitcher.b();
      }
      this.mConnection.a();
      commitTyped("");
      this.mConnection.c();
      this.mConnection.b();
      if (isShowingOptionDialog()) {
        this.mOptionsDialog.dismiss();
      }
      this.mKeyboardSwitcher.C();
      this.mKeyboardSwitcher.B();
    }
    super.onConfigurationChanged(paramConfiguration);
  }
  
  public void onCreate()
  {
    com.android.inputmethod.latin.settings.c.a(this);
    t.a(this);
    this.mRichImm = t.a();
    u.a(this);
    com.android.inputmethod.keyboard.h.a(this);
    emoji.keyboard.emoticonkeyboard.a.b.a(this);
    com.android.inputmethod.latin.kkuirearch.utils.f.c = this;
    super.onCreate();
    this.mIsNeededHackingForSM2k = emoji.keyboard.emoticonkeyboard.extras.d.a(this);
    Object localObject = this.mHandler;
    Resources localResources = ((LatinIME)((com.android.inputmethod.latin.d.aa)localObject).n.get()).getResources();
    ((g)localObject).f = this;
    ((g)localObject).a = localResources.getInteger(2131427361);
    ((g)localObject).b = localResources.getInteger(2131427360);
    ((g)localObject).c = localResources.getInteger(2131427362);
    ((g)localObject).e = new AsyncHttpClient();
    ((g)localObject).g = DCAgent.a(this);
    ((g)localObject).a();
    DEBUG = q.a;
    loadSettings();
    initSuggest();
    this.mDisplayOrientation = getResources().getConfiguration().orientation;
    localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.net.conn.CONNECTIVITY_CHANGE");
    ((IntentFilter)localObject).addAction("android.media.RINGER_MODE_CHANGED");
    registerReceiver(this.mReceiver, (IntentFilter)localObject);
    localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_ADDED");
    ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_REMOVED");
    ((IntentFilter)localObject).addDataScheme("package");
    registerReceiver(this.mDictionaryPackInstallReceiver, (IntentFilter)localObject);
    localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("com.kitkatandroid.keyboard.dictionarypack.aosp.newdict");
    registerReceiver(this.mDictionaryPackInstallReceiver, (IntentFilter)localObject);
    DictionaryDecayBroadcastReciever.a(this);
    if (this.mArtViewChangedReceiver == null)
    {
      this.mArtViewChangedReceiver = new a();
      localObject = new IntentFilter("emoji.keyboard.emoticonkeyboard.KAKA_ART_PACKAGE_CHANGED_INTENT");
      ((IntentFilter)localObject).addAction("emoji.keyboard.emoticonkeyboard.ACTION_REFRESH_ONLINE_ART");
      ((IntentFilter)localObject).addAction("action_sticker_changed");
      registerReceiver(this.mArtViewChangedReceiver, (IntentFilter)localObject);
    }
    this.mInputUpdater = new d(this, (byte)0);
    ((ClipboardManager)getSystemService("clipboard")).addPrimaryClipChangedListener(this);
    getClipArrayFromFile();
    OnlineConfigAgent.getInstance().updateOnlineConfig(this);
    this.mHandler.b(0L);
    if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_art_package_scanned", false)) {
      new Thread(this.mArtPackageScanner).start();
    }
    this.mHandler.c(0L);
    localObject = PushAgent.getInstance(this);
    ((PushAgent)localObject).enable();
    ((PushAgent)localObject).setPushIntentServiceClass(UmengPushHandleService.class);
    localObject = this.mHandler;
    if (!((g)localObject).hasMessages(13)) {
      ((g)localObject).sendEmptyMessageDelayed(13, 3600000L);
    }
    this.mHandler.a(0L);
    g.b(this.mHandler);
    g.c(this.mHandler);
    com.android.inputmethod.latin.kkuirearch.utils.d.a(this).a();
    g.d(this.mHandler);
  }
  
  public View onCreateInputView()
  {
    return this.mKeyboardSwitcher.a(null, this.mIsHardwareAcceleratedDrawingEnabled);
  }
  
  public void onCurrentInputMethodSubtypeChanged(InputMethodSubtype paramInputMethodSubtype)
  {
    this.mSubtypeSwitcher.a(paramInputMethodSubtype);
    loadKeyboard();
  }
  
  public boolean onCustomRequest(int paramInt)
  {
    if (isShowingOptionDialog()) {}
    do
    {
      return false;
      switch (paramInt)
      {
      default: 
        return false;
      }
    } while (!this.mRichImm.b(true));
    this.mRichImm.b().showInputMethodPicker();
    return true;
  }
  
  public void onDestroy()
  {
    Object localObject = this.mSuggest;
    if (localObject != null)
    {
      ((v)localObject).b();
      this.mSuggest = null;
    }
    localObject = this.mSettings;
    ((com.android.inputmethod.latin.settings.c)localObject).f.unregisterOnSharedPreferenceChangeListener((SharedPreferences.OnSharedPreferenceChangeListener)localObject);
    unregisterReceiver(this.mReceiver);
    unregisterReceiver(this.mDictionaryPackInstallReceiver);
    if (this.mArtViewChangedReceiver != null) {
      unregisterReceiver(this.mArtViewChangedReceiver);
    }
    if (this.mInputUpdater != null)
    {
      d.a(this.mInputUpdater);
      this.mInputUpdater = null;
    }
    this.mIsNeededHackingForSM2k = false;
    super.onDestroy();
  }
  
  public void onDisplayCompletions(CompletionInfo[] paramArrayOfCompletionInfo)
  {
    if (DEBUG)
    {
      Log.i(TAG, "Received completions:");
      if (paramArrayOfCompletionInfo != null)
      {
        int i = 0;
        while (i < paramArrayOfCompletionInfo.length)
        {
          Log.i(TAG, "  #" + i + ": " + paramArrayOfCompletionInfo[i]);
          i += 1;
        }
      }
    }
    if (!this.mSettings.g.A.c) {
      return;
    }
    if (paramArrayOfCompletionInfo == null)
    {
      clearSuggestionStrip();
      return;
    }
    this.mApplicationSpecifiedCompletions = i.a(paramArrayOfCompletionInfo);
    setSuggestedWords(new w(w.a(paramArrayOfCompletionInfo), false, false, false, false, false), false);
    setAutoCorrectionIndicator(false);
    setSuggestionStripShown(true);
  }
  
  public void onEndBatchInput(o paramO)
  {
    d localD = this.mInputUpdater;
    synchronized (localD.c)
    {
      localD.a(paramO, new LatinIME.d.2(localD));
      return;
    }
  }
  
  public void onEndBatchInputAsyncInternal(w paramW)
  {
    if (paramW.g.isEmpty()) {}
    for (Object localObject = null; TextUtils.isEmpty((CharSequence)localObject); localObject = paramW.a(0)) {
      return;
    }
    this.mConnection.a();
    if (4 == this.mSpaceState) {
      promotePhantomSpace();
    }
    if (this.mSettings.g.x)
    {
      int j = ((String)localObject).lastIndexOf(' ') + 1;
      if (j != 0)
      {
        this.mConnection.a(((String)localObject).substring(0, j), 1);
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        while (i < paramW.g.size())
        {
          w.a localA = (w.a)paramW.g.get(i);
          int k = localA.a.lastIndexOf(' ');
          localArrayList.add(new w.a(localA.a.substring(k + 1), localA.b, localA.c, localA.e, -1, -1));
          i += 1;
        }
        showSuggestionStrip(new w(localArrayList, paramW.b, paramW.c, paramW.d, paramW.e, paramW.f));
      }
      paramW = ((String)localObject).substring(j);
      this.mWordComposer.a(paramW);
      this.mConnection.a(paramW);
    }
    for (;;)
    {
      this.mExpectingUpdateSelection = true;
      this.mConnection.b();
      this.mSpaceState = 4;
      this.mKeyboardSwitcher.e();
      return;
      this.mWordComposer.a((String)localObject);
      this.mConnection.a((CharSequence)localObject);
    }
  }
  
  public boolean onEvaluateFullscreenMode()
  {
    boolean bool2 = false;
    boolean bool3 = com.android.inputmethod.latin.settings.c.f(getResources());
    boolean bool1 = bool2;
    if (super.onEvaluateFullscreenMode())
    {
      bool1 = bool2;
      if (bool3)
      {
        EditorInfo localEditorInfo = getCurrentInputEditorInfo();
        if (localEditorInfo != null)
        {
          bool1 = bool2;
          if ((localEditorInfo.imeOptions & 0x10000000) != 0) {}
        }
        else
        {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public void onExtractedCursorMovement(int paramInt1, int paramInt2)
  {
    if (this.mSettings.g.a(this.mDisplayOrientation)) {
      return;
    }
    super.onExtractedCursorMovement(paramInt1, paramInt2);
  }
  
  public void onExtractedTextClicked()
  {
    if (this.mSettings.g.a(this.mDisplayOrientation)) {
      return;
    }
    super.onExtractedTextClicked();
  }
  
  public void onFinishInput()
  {
    g localG = this.mHandler;
    if (localG.hasMessages(1))
    {
      localG.l = true;
      return;
    }
    LatinIME localLatinIME = (LatinIME)localG.n.get();
    localG.a(localLatinIME, null, false);
    localLatinIME.onFinishInputInternal();
  }
  
  public void onFinishInputView(boolean paramBoolean)
  {
    g localG = this.mHandler;
    if (localG.hasMessages(1)) {
      localG.k = true;
    }
    for (;;)
    {
      this.mCurrentEditorInfo = null;
      MobclickAgent.onPause(this);
      AppEventsLogger.deactivateApp(this);
      return;
      ((LatinIME)localG.n.get()).onFinishInputViewInternal(paramBoolean);
      localG.m = null;
    }
  }
  
  public void onFinishSlidingInput()
  {
    com.android.inputmethod.keyboard.internal.w localW = this.mKeyboardSwitcher.g;
    switch (localW.d)
    {
    default: 
      return;
    case 3: 
      localW.a();
      return;
    case 4: 
      localW.b();
      return;
    }
    localW.c();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && ((this.mKeyboardSwitcher.C()) || (this.mKeyboardSwitcher.B()))) {
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    long l = paramKeyEvent.getDeviceId() << paramKeyEvent.getKeyCode() + 32;
    if (this.mCurrentlyPressedHardwareKeys.remove(Long.valueOf(l))) {
      return true;
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  public void onPressKey(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int j = 1;
    com.android.inputmethod.keyboard.h localH = this.mKeyboardSwitcher;
    com.android.inputmethod.keyboard.internal.w localW = localH.g;
    int i = localH.f.getCurrentAutoCapsState();
    if (paramInt1 != -1) {
      localW.a.u();
    }
    if (paramInt1 == -1) {
      if (-1 == localW.l)
      {
        if (!localW.e) {
          break label231;
        }
        localW.n = localW.a.v();
        if (!localW.n) {
          localW.a.t();
        }
        if (!localW.n) {
          break label136;
        }
        if ((localW.h.e()) || (localW.m)) {
          localW.a(true);
        }
      }
    }
    label136:
    label231:
    label382:
    label386:
    for (;;)
    {
      hapticAndAudioFeedback(paramInt1, paramInt2);
      return;
      if (localW.h.b())
      {
        localW.a(3);
        localW.b.a();
      }
      else if (localW.h.d())
      {
        localW.a(1);
        localW.b.a();
      }
      else if (localW.h.a())
      {
        localW.b.g();
      }
      else
      {
        localW.a(1);
        localW.b.a();
        continue;
        localW.b();
        localW.d = 4;
        localW.b.a();
        continue;
        if (paramInt1 != -2) {
          if (paramInt1 == -3)
          {
            localW.a();
            localW.c.a();
            localW.d = 3;
          }
          else
          {
            localW.b.c();
            localW.c.c();
            if ((!paramBoolean) && (localW.e) && (i != 4096))
            {
              i = j;
              if (!localW.h.d()) {
                if ((!localW.h.e()) || (!localW.b.e())) {
                  break label382;
                }
              }
              for (i = j;; i = 0)
              {
                if (i == 0) {
                  break label386;
                }
                localW.a.f();
                break;
              }
            }
          }
        }
      }
    }
  }
  
  public void onPrimaryClipChanged()
  {
    for (;;)
    {
      try
      {
        Object localObject = ((ClipboardManager)getSystemService("clipboard")).getPrimaryClip();
        if (localObject == null) {
          break;
        }
        localObject = ((ClipData)localObject).getItemAt(0).getText();
        if (localObject == null) {
          break;
        }
        if (mClipArray.isEmpty())
        {
          localObject = new c(((CharSequence)localObject).toString());
          mClipArray.add(0, localObject);
          saveClipArrayToFile();
          if (this.mClipChangedListener == null) {
            break;
          }
          this.mClipChangedListener.a();
          return;
        }
        c localC = (c)mClipArray.get(0);
        if (!TextUtils.equals(((CharSequence)localObject).toString(), localC.a))
        {
          localObject = new c(((CharSequence)localObject).toString());
          mClipArray.add(0, localObject);
        }
        int i = mClipArray.size();
        if (i > 12)
        {
          i -= 1;
          if (i >= 0) {
            if (!((c)mClipArray.get(i)).b) {
              mClipArray.remove(i);
            } else {
              i -= 1;
            }
          }
        }
      }
      catch (Exception localException)
      {
        return;
      }
    }
  }
  
  public void onReleaseKey(int paramInt, boolean paramBoolean)
  {
    boolean bool = true;
    Object localObject1 = this.mKeyboardSwitcher.g;
    if (paramInt == -1) {
      if (-1 != ((com.android.inputmethod.keyboard.internal.w)localObject1).l)
      {
        ((com.android.inputmethod.keyboard.internal.w)localObject1).b(((com.android.inputmethod.keyboard.internal.w)localObject1).l);
        ((com.android.inputmethod.keyboard.internal.w)localObject1).b.b();
        label44:
        if (emoji.keyboard.emoticonkeyboard.a.b.a().b()) {
          switch (paramInt)
          {
          }
        }
      }
    }
    do
    {
      do
      {
        return;
        if (((com.android.inputmethod.keyboard.internal.w)localObject1).e)
        {
          bool = ((com.android.inputmethod.keyboard.internal.w)localObject1).h.b();
          ((com.android.inputmethod.keyboard.internal.w)localObject1).m = false;
          if (((com.android.inputmethod.keyboard.internal.w)localObject1).n)
          {
            ((com.android.inputmethod.keyboard.internal.w)localObject1).n = false;
            break;
          }
          if (((com.android.inputmethod.keyboard.internal.w)localObject1).b.f())
          {
            if (((com.android.inputmethod.keyboard.internal.w)localObject1).h.c()) {
              ((com.android.inputmethod.keyboard.internal.w)localObject1).a(true);
            }
            for (;;)
            {
              ((com.android.inputmethod.keyboard.internal.w)localObject1).b.b();
              ((com.android.inputmethod.keyboard.internal.w)localObject1).a.s();
              break;
              ((com.android.inputmethod.keyboard.internal.w)localObject1).a(0);
            }
          }
          if ((((com.android.inputmethod.keyboard.internal.w)localObject1).h.c()) && (paramBoolean))
          {
            ((com.android.inputmethod.keyboard.internal.w)localObject1).a(true);
            break;
          }
          if ((((com.android.inputmethod.keyboard.internal.w)localObject1).h.e()) && (paramBoolean))
          {
            ((com.android.inputmethod.keyboard.internal.w)localObject1).d = 5;
            break;
          }
          if ((bool) && (!((com.android.inputmethod.keyboard.internal.w)localObject1).h.c()) && ((((com.android.inputmethod.keyboard.internal.w)localObject1).b.d()) || (((com.android.inputmethod.keyboard.internal.w)localObject1).b.h())) && (!paramBoolean)) {
            break;
          }
          if ((bool) && (!((com.android.inputmethod.keyboard.internal.w)localObject1).b.i()) && (!paramBoolean))
          {
            ((com.android.inputmethod.keyboard.internal.w)localObject1).a(false);
            break;
          }
          if ((((com.android.inputmethod.keyboard.internal.w)localObject1).h.a()) && (((com.android.inputmethod.keyboard.internal.w)localObject1).b.h()) && (!paramBoolean))
          {
            ((com.android.inputmethod.keyboard.internal.w)localObject1).a(0);
            ((com.android.inputmethod.keyboard.internal.w)localObject1).m = true;
            break;
          }
          if (((com.android.inputmethod.keyboard.internal.w)localObject1).h.a == 2) {}
          for (int i = 1; (i != 0) && (((com.android.inputmethod.keyboard.internal.w)localObject1).b.d()) && (!paramBoolean); i = 0)
          {
            ((com.android.inputmethod.keyboard.internal.w)localObject1).a(0);
            ((com.android.inputmethod.keyboard.internal.w)localObject1).m = true;
            break;
          }
        }
        if (!((com.android.inputmethod.keyboard.internal.w)localObject1).b.f()) {
          break;
        }
        ((com.android.inputmethod.keyboard.internal.w)localObject1).b();
        break;
        if (paramInt == -2)
        {
          if (!((com.android.inputmethod.keyboard.internal.w)localObject1).h.b()) {}
          for (paramBoolean = bool;; paramBoolean = false)
          {
            ((com.android.inputmethod.keyboard.internal.w)localObject1).a(paramBoolean);
            break;
          }
        }
        if (paramInt != -3) {
          break label44;
        }
        if (((com.android.inputmethod.keyboard.internal.w)localObject1).c.f()) {
          ((com.android.inputmethod.keyboard.internal.w)localObject1).a();
        }
        for (;;)
        {
          ((com.android.inputmethod.keyboard.internal.w)localObject1).c.b();
          break;
          if (!paramBoolean) {
            ((com.android.inputmethod.keyboard.internal.w)localObject1).k = false;
          }
        }
        localObject2 = emoji.keyboard.emoticonkeyboard.a.c.a();
      } while (((emoji.keyboard.emoticonkeyboard.a.c)localObject2).c == null);
      paramInt = ((emoji.keyboard.emoticonkeyboard.a.c)localObject2).c.getKeyboard().a.f;
      localObject1 = ((emoji.keyboard.emoticonkeyboard.a.c)localObject2).c.getContext();
      switch (paramInt)
      {
      case 5: 
      default: 
        localObject1 = ((Context)localObject1).getText(2131296472);
      }
      for (;;)
      {
        emoji.keyboard.emoticonkeyboard.a.b.a().a(((emoji.keyboard.emoticonkeyboard.a.c)localObject2).c, (CharSequence)localObject1);
        return;
        localObject1 = ((Context)localObject1).getText(2131296471);
        continue;
        localObject1 = ((Context)localObject1).getText(2131296473);
      }
      localObject1 = emoji.keyboard.emoticonkeyboard.a.c.a();
    } while (((emoji.keyboard.emoticonkeyboard.a.c)localObject1).c == null);
    Object localObject2 = ((emoji.keyboard.emoticonkeyboard.a.c)localObject1).c.getKeyboard();
    Context localContext = ((emoji.keyboard.emoticonkeyboard.a.c)localObject1).c.getContext();
    switch (((com.android.inputmethod.keyboard.d)localObject2).a.f)
    {
    default: 
      paramInt = -1;
    }
    while (paramInt >= 0)
    {
      localObject2 = localContext.getString(paramInt);
      emoji.keyboard.emoticonkeyboard.a.b.a().a(((emoji.keyboard.emoticonkeyboard.a.c)localObject1).c, (CharSequence)localObject2);
      return;
      paramInt = 2131296462;
      continue;
      paramInt = 2131296465;
      continue;
      paramInt = 2131296463;
      continue;
      paramInt = 2131296464;
    }
  }
  
  public void onStartBatchInput()
  {
    d localD = this.mInputUpdater;
    for (;;)
    {
      int i;
      synchronized (localD.c)
      {
        localD.a.removeMessages(1);
        localD.d = true;
        localD.b.mHandler.a(w.a, false);
        this.mHandler.removeMessages(2);
        this.mConnection.a();
        ??? = this.mSettings.g;
        if (this.mWordComposer.c())
        {
          if ((((com.android.inputmethod.latin.settings.e)???).N) && (this.mWordComposer.f)) {
            com.android.inputmethod.latin.d.s.a("", this.mWordComposer.c.toString(), " ", this.mWordComposer);
          }
          i = this.mWordComposer.l;
          if (this.mWordComposer.d())
          {
            resetEntireInputState(this.mLastSelectionStart);
            this.mExpectingUpdateSelection = true;
          }
        }
        else
        {
          i = this.mConnection.e();
          if ((Character.isLetterOrDigit(i)) || (((com.android.inputmethod.latin.settings.e)???).g(i))) {
            this.mSpaceState = 4;
          }
          this.mConnection.b();
          this.mWordComposer.j = getActualCapsMode();
          return;
        }
      }
      if (i <= 1) {
        commitCurrentAutoCorrection("");
      } else {
        commitTyped("");
      }
    }
  }
  
  public void onStartInput(EditorInfo paramEditorInfo, boolean paramBoolean)
  {
    g localG = this.mHandler;
    if (localG.hasMessages(1))
    {
      localG.j = true;
      return;
    }
    if ((localG.h) && (paramBoolean))
    {
      localG.h = false;
      localG.i = true;
    }
    LatinIME localLatinIME = (LatinIME)localG.n.get();
    localG.a(localLatinIME, paramEditorInfo, paramBoolean);
    localLatinIME.onStartInputInternal(paramEditorInfo, paramBoolean);
  }
  
  public void onStartInputView(EditorInfo paramEditorInfo, boolean paramBoolean)
  {
    Object localObject1 = this.mKeyboardSwitcher;
    label100:
    Object localObject2;
    int i;
    if (((com.android.inputmethod.keyboard.h)localObject1).n)
    {
      ((com.android.inputmethod.keyboard.h)localObject1).f.destroyViews();
      if (Build.VERSION.SDK_INT >= 16)
      {
        if (((com.android.inputmethod.keyboard.h)localObject1).k != null) {
          ((com.android.inputmethod.keyboard.h)localObject1).k.setBackground(null);
        }
        if (((com.android.inputmethod.keyboard.h)localObject1).e != null)
        {
          ((com.android.inputmethod.keyboard.h)localObject1).e.setBackground(null);
          ((com.android.inputmethod.keyboard.h)localObject1).e.d();
        }
        if (((com.android.inputmethod.keyboard.h)localObject1).m != null)
        {
          ((com.android.inputmethod.keyboard.h)localObject1).m.setBackground(null);
          ((com.android.inputmethod.keyboard.h)localObject1).m.a.clear();
        }
        if (((com.android.inputmethod.keyboard.h)localObject1).l != null)
        {
          localObject2 = ((com.android.inputmethod.keyboard.h)localObject1).l;
          PreferenceManager.getDefaultSharedPreferences(((TopMenuPopupViewPager)localObject2).b).unregisterOnSharedPreferenceChangeListener((SharedPreferences.OnSharedPreferenceChangeListener)localObject2);
        }
        ((com.android.inputmethod.keyboard.h)localObject1).c.setBackgroundDrawable(null);
        ((com.android.inputmethod.keyboard.h)localObject1).c.c();
        ((com.android.inputmethod.keyboard.h)localObject1).b.setBackgroundDrawable(null);
        ((com.android.inputmethod.keyboard.h)localObject1).d.setBackgroundDrawable(null);
        ((com.android.inputmethod.keyboard.h)localObject1).i.setBackgroundDrawable(null);
        ((com.android.inputmethod.keyboard.h)localObject1).i.a.clear();
        ((com.android.inputmethod.keyboard.h)localObject1).j.setBackgroundDrawable(null);
        localObject2 = ((com.android.inputmethod.keyboard.h)localObject1).j;
        ((ClipBoardView)localObject2).b.clear();
        ((ClipBoardView)localObject2).a.clear();
        System.gc();
        localObject2 = ((com.android.inputmethod.keyboard.h)localObject1).a(PreferenceManager.getDefaultSharedPreferences(((com.android.inputmethod.keyboard.h)localObject1).f), false);
        ((com.android.inputmethod.keyboard.h)localObject1).f.mHandler.post(new h.1((com.android.inputmethod.keyboard.h)localObject1, (View)localObject2));
        ((com.android.inputmethod.keyboard.h)localObject1).n = false;
      }
    }
    else
    {
      localObject1 = this.mHandler;
      if (!((g)localObject1).hasMessages(1)) {
        break label569;
      }
      localObject2 = ((g)localObject1).m;
      if ((paramEditorInfo != null) || (localObject2 != null)) {
        break label506;
      }
      i = 1;
      label298:
      if (i == 0) {
        break label569;
      }
      ((g)localObject1).d();
    }
    for (;;)
    {
      this.mCurrentEditorInfo = paramEditorInfo;
      tryToShowAppPromotion();
      MobclickAgent.onResume(this);
      AppEventsLogger.activateApp(this);
      PushAgent.getInstance(this).onAppStart();
      paramEditorInfo = PreferenceManager.getDefaultSharedPreferences(this);
      long l = paramEditorInfo.getLong("version_track_time", 0L);
      if (System.currentTimeMillis() - l >= 86400000L)
      {
        emoji.keyboard.emoticonkeyboard.extras.d.f(this);
        paramEditorInfo.edit().putLong("version_track_time", System.currentTimeMillis()).apply();
      }
      if (this.mSubTip != -1) {}
      switch (this.mSubTip)
      {
      case 12: 
      case 14: 
      default: 
        return;
        if (((com.android.inputmethod.keyboard.h)localObject1).k != null) {
          ((com.android.inputmethod.keyboard.h)localObject1).k.setBackgroundDrawable(null);
        }
        if (((com.android.inputmethod.keyboard.h)localObject1).e != null)
        {
          ((com.android.inputmethod.keyboard.h)localObject1).e.setBackgroundDrawable(null);
          ((com.android.inputmethod.keyboard.h)localObject1).e.d();
        }
        if (((com.android.inputmethod.keyboard.h)localObject1).m == null) {
          break label100;
        }
        ((com.android.inputmethod.keyboard.h)localObject1).m.setBackgroundDrawable(null);
        ((com.android.inputmethod.keyboard.h)localObject1).m.a.clear();
        break label100;
        label506:
        if ((paramEditorInfo == null) || (localObject2 == null))
        {
          i = 0;
          break label298;
        }
        if ((paramEditorInfo.inputType == ((EditorInfo)localObject2).inputType) && (paramEditorInfo.imeOptions == ((EditorInfo)localObject2).imeOptions) && (TextUtils.equals(paramEditorInfo.privateImeOptions, ((EditorInfo)localObject2).privateImeOptions)))
        {
          i = 1;
          break label298;
        }
        i = 0;
        break label298;
        label569:
        if (((g)localObject1).i)
        {
          ((g)localObject1).i = false;
          ((g)localObject1).d();
          ((g)localObject1).sendMessageDelayed(((g)localObject1).obtainMessage(1), 800L);
        }
        localObject2 = (LatinIME)((com.android.inputmethod.latin.d.aa)localObject1).n.get();
        ((g)localObject1).a((LatinIME)localObject2, paramEditorInfo, paramBoolean);
        ((LatinIME)localObject2).onStartInputViewInternal(paramEditorInfo, paramBoolean);
        ((g)localObject1).m = paramEditorInfo;
      }
    }
    this.mKeyboardSwitcher.p();
    return;
    paramEditorInfo = this.mKeyboardSwitcher;
    paramEditorInfo.l();
    paramEditorInfo.d.e();
    return;
    this.mKeyboardSwitcher.n();
  }
  
  public void onTargetPackageInfoKnown(PackageInfo paramPackageInfo)
  {
    this.mAppWorkAroundsUtils.a(paramPackageInfo);
  }
  
  public void onTextInput(String paramString)
  {
    this.mConnection.a();
    if (this.mWordComposer.c()) {
      commitCurrentAutoCorrection(paramString);
    }
    for (;;)
    {
      this.mHandler.b();
      paramString = specificTldProcessingOnTextInput(paramString);
      if (4 == this.mSpaceState) {
        promotePhantomSpace();
      }
      this.mConnection.a(paramString, 1);
      this.mConnection.b();
      this.mSpaceState = 0;
      this.mKeyboardSwitcher.e();
      this.mKeyboardSwitcher.a(-4);
      this.mEnteredText = paramString;
      return;
      resetComposingState(true);
    }
  }
  
  public void onUpdateBatchInput(o paramO)
  {
    Object localObject;
    int i;
    if (this.mSettings.g.x)
    {
      localObject = this.mSuggestedWords;
      if (((w)localObject).g.size() <= 0) {
        break label200;
      }
      localObject = (w.a)((w)localObject).g.get(0);
      if ((1 != ((w.a)localObject).c) || (-1 == ((w.a)localObject).f)) {
        break label195;
      }
      i = 1;
      if (i == 0) {
        break label200;
      }
    }
    for (;;)
    {
      if ((localObject != null) && (((w.a)localObject).e.a((w.a)localObject)))
      {
        String[] arrayOfString = ((w.a)localObject).a.split(" ", 2);
        i = ((w.a)localObject).f;
        paramO.b.f(i);
        paramO.c.f(i);
        paramO.d.f(i);
        paramO.e.f(i);
        promotePhantomSpace();
        this.mConnection.a(arrayOfString[0], 0);
        this.mSpaceState = 4;
        this.mKeyboardSwitcher.e();
        this.mWordComposer.j = getActualCapsMode();
      }
      localObject = this.mInputUpdater;
      if (!((d)localObject).a.hasMessages(1)) {
        ((d)localObject).a.obtainMessage(1, paramO).sendToTarget();
      }
      return;
      label195:
      i = 0;
      break;
      label200:
      localObject = null;
    }
  }
  
  public void onUpdateMainDictionaryAvailability(boolean paramBoolean)
  {
    this.mIsMainDictionaryAvailable = paramBoolean;
    MainKeyboardView localMainKeyboardView = this.mKeyboardSwitcher.c;
    if (localMainKeyboardView != null) {
      localMainKeyboardView.setMainDictionaryAvailability(paramBoolean);
    }
  }
  
  public void onUpdateSelection(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    super.onUpdateSelection(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
    if (DEBUG) {
      Log.i(TAG, "onUpdateSelection: oss=" + paramInt1 + ", ose=" + paramInt2 + ", lss=" + this.mLastSelectionStart + ", lse=" + this.mLastSelectionEnd + ", nss=" + paramInt3 + ", nse=" + paramInt4 + ", cs=" + paramInt5 + ", ce=" + paramInt6);
    }
    int i;
    label157:
    s localS;
    if ((this.mLastSelectionStart != paramInt3) || (this.mLastSelectionEnd != paramInt4))
    {
      i = 1;
      if ((paramInt5 != -1) || (paramInt6 != -1)) {
        break label322;
      }
      paramInt6 = 1;
      if ((isInputViewShown()) && (!this.mExpectingUpdateSelection))
      {
        localS = this.mConnection;
        if (paramInt3 != localS.b) {
          break label328;
        }
        paramInt5 = 1;
        label189:
        if (paramInt5 == 0)
        {
          this.mSpaceState = 0;
          if ((i == 0) && (this.mWordComposer.c()) && (paramInt6 == 0)) {
            break label363;
          }
          paramInt5 = 1;
          label222:
          if ((paramInt1 == paramInt2) && (paramInt3 == paramInt4)) {
            break label369;
          }
          paramInt2 = 1;
          label235:
          if ((paramInt5 == 0) || ((paramInt2 == 0) && (this.mWordComposer.b(paramInt3 - paramInt1)))) {
            break label374;
          }
          resetEntireInputState(paramInt3);
        }
      }
    }
    for (;;)
    {
      if (isSuggestionsStripVisible()) {
        this.mHandler.c();
      }
      this.mRecapitalizeStatus.k = false;
      this.mKeyboardSwitcher.e();
      this.mExpectingUpdateSelection = false;
      this.mLastSelectionStart = paramInt3;
      this.mLastSelectionEnd = paramInt4;
      this.mSubtypeState.b = true;
      return;
      i = 0;
      break;
      label322:
      paramInt6 = 0;
      break label157;
      label328:
      if ((paramInt1 != localS.b) && ((localS.b - paramInt3) * (paramInt3 - paramInt1) >= 0))
      {
        paramInt5 = 1;
        break label189;
      }
      paramInt5 = 0;
      break label189;
      label363:
      paramInt5 = 0;
      break label222;
      label369:
      paramInt2 = 0;
      break label235;
      label374:
      this.mConnection.a(paramInt3, false);
    }
  }
  
  public void onWindowHidden()
  {
    super.onWindowHidden();
    MainKeyboardView localMainKeyboardView = this.mKeyboardSwitcher.c;
    if (localMainKeyboardView != null) {
      localMainKeyboardView.i();
    }
    this.mKeyboardSwitcher.C();
  }
  
  public void pickSuggestionManually(int paramInt, w.a paramA)
  {
    int i = 1;
    Object localObject1 = paramA.a;
    if ((((String)localObject1).length() == 1) && (isShowingPunctuationList()))
    {
      onCodeInput(((String)localObject1).charAt(0), -2, -2);
      return;
    }
    this.mConnection.a();
    Object localObject2 = this.mSettings.g;
    if ((4 == this.mSpaceState) && (((String)localObject1).length() > 0) && (!this.mWordComposer.f))
    {
      int j = Character.codePointAt((CharSequence)localObject1, 0);
      if ((!((com.android.inputmethod.latin.settings.e)localObject2).c(j)) || (((com.android.inputmethod.latin.settings.e)localObject2).f(j))) {
        promotePhantomSpace();
      }
    }
    if ((((com.android.inputmethod.latin.settings.e)localObject2).A.c) && (this.mApplicationSpecifiedCompletions != null) && (paramInt >= 0) && (paramInt < this.mApplicationSpecifiedCompletions.length))
    {
      this.mSuggestedWords = w.a;
      if (this.mSuggestionStripView != null) {
        this.mSuggestionStripView.b();
      }
      this.mKeyboardSwitcher.e();
      resetComposingState(true);
      localObject2 = this.mApplicationSpecifiedCompletions[paramInt];
      localObject3 = this.mConnection;
      localObject1 = ((CompletionInfo)localObject2).getText();
      paramA = (w.a)localObject1;
      if (localObject1 == null) {
        paramA = "";
      }
      ((s)localObject3).c.append(paramA);
      paramInt = ((s)localObject3).b;
      ((s)localObject3).b = (paramA.length() - ((s)localObject3).d.length() + paramInt);
      ((s)localObject3).d.setLength(0);
      if (((s)localObject3).f != null) {
        ((s)localObject3).f.commitCompletion((CompletionInfo)localObject2);
      }
      this.mConnection.b();
      return;
    }
    this.mExpectingUpdateSelection = true;
    commitChosenWord((String)localObject1, 1, "");
    this.mConnection.b();
    this.mLastComposedWord.h = false;
    this.mSpaceState = 4;
    this.mKeyboardSwitcher.e();
    Object localObject3 = this.mSuggest;
    if (((paramA.c == 0) || (10 == paramA.c)) && (localObject3 != null) && (!com.android.inputmethod.latin.d.e.a((v)localObject3, (String)localObject1, true))) {}
    for (paramInt = i;; paramInt = 0)
    {
      if (((com.android.inputmethod.latin.settings.e)localObject2).N) {
        com.android.inputmethod.latin.d.s.a(32, -1, -1);
      }
      if ((paramInt == 0) || (!this.mIsUserDictionaryAvailable)) {
        break;
      }
      paramA = this.mSuggestionStripView;
      localObject2 = ((com.android.inputmethod.latin.settings.e)localObject2).h;
      paramA.b();
      localObject3 = paramA.e;
      ViewGroup localViewGroup = paramA.b;
      paramInt = paramA.getWidth() - ((com.android.inputmethod.latin.suggestions.b)localObject3).b - ((com.android.inputmethod.latin.suggestions.b)localObject3).a * 2;
      TextView localTextView = ((com.android.inputmethod.latin.suggestions.b)localObject3).y;
      localTextView.setTextColor(((com.android.inputmethod.latin.suggestions.b)localObject3).p);
      i = (int)(paramInt * ((com.android.inputmethod.latin.suggestions.b)localObject3).t);
      Object localObject4 = com.android.inputmethod.latin.suggestions.b.b((CharSequence)localObject1, i, localTextView.getPaint());
      float f = localTextView.getTextScaleX();
      localTextView.setTag(localObject1);
      localTextView.setText((CharSequence)localObject4);
      localTextView.setTextScaleX(f);
      localViewGroup.addView(localTextView);
      com.android.inputmethod.latin.suggestions.b.a(localTextView, ((com.android.inputmethod.latin.suggestions.b)localObject3).t, -1);
      localViewGroup.addView((View)((com.android.inputmethod.latin.suggestions.b)localObject3).m.get(0));
      localObject1 = ((com.android.inputmethod.latin.suggestions.b)localObject3).z;
      ((TextView)localObject1).setTextColor(((com.android.inputmethod.latin.suggestions.b)localObject3).q);
      ((TextView)localObject1).setText("←");
      localViewGroup.addView((View)localObject1);
      localObject4 = ((com.android.inputmethod.latin.suggestions.b)localObject3).A;
      ((TextView)localObject4).setGravity(19);
      ((TextView)localObject4).setTextColor(((com.android.inputmethod.latin.suggestions.b)localObject3).q);
      f = com.android.inputmethod.latin.suggestions.b.a((CharSequence)localObject2, paramInt - i - ((TextView)localObject1).getWidth(), ((TextView)localObject4).getPaint());
      ((TextView)localObject4).setText((CharSequence)localObject2);
      ((TextView)localObject4).setTextScaleX(f);
      localViewGroup.addView((View)localObject4);
      com.android.inputmethod.latin.suggestions.b.a((View)localObject4, 1.0F - ((com.android.inputmethod.latin.suggestions.b)localObject3).t, -1);
      localTextView.setOnClickListener(paramA);
      ((TextView)localObject1).setOnClickListener(paramA);
      ((TextView)localObject4).setOnClickListener(paramA);
      return;
    }
    this.mHandler.b();
  }
  
  public void promotePhantomSpace()
  {
    com.android.inputmethod.latin.settings.e localE = this.mSettings.g;
    if ((localE.A.d) && (localE.i) && (!ab.a(this.mConnection.c))) {
      sendKeyCodePoint(32);
    }
  }
  
  void replaceMainDictionaryForTest(Locale paramLocale)
  {
    this.mSuggest.a(this, paramLocale, null);
  }
  
  void resetSuggestMainDict()
  {
    Locale localLocale = this.mSubtypeSwitcher.e();
    this.mSuggest.a(this, localLocale, this);
    this.mIsMainDictionaryAvailable = j.b(this, localLocale);
  }
  
  public void saveClipArrayToFile()
  {
    String str = new com.google.a.f().a(mClipArray);
    try
    {
      FileOutputStream localFileOutputStream = openFileOutput("clip_text_list_file", 0);
      localFileOutputStream.write(str.getBytes());
      localFileOutputStream.close();
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  public void setCandidatesView(View paramView) {}
  
  public void setClipChangedListener(b paramB)
  {
    this.mClipChangedListener = paramB;
  }
  
  public void setInputView(View paramView)
  {
    super.setInputView(paramView);
    this.mExtractArea = getWindow().getWindow().getDecorView().findViewById(16908316);
    this.mKeyPreviewBackingView = paramView.findViewById(2131755638);
    this.mSuggestionStripView = ((SuggestionStripView)paramView.findViewById(2131755647));
    if (this.mSuggestionStripView != null)
    {
      SuggestionStripView localSuggestionStripView = this.mSuggestionStripView;
      localSuggestionStripView.d = this;
      localSuggestionStripView.c = ((MainKeyboardView)paramView.findViewById(2131755648));
    }
    if (q.b) {
      this.mKeyPreviewBackingView.setBackgroundColor(285147136);
    }
    this.mTopMenuSuggestionContainerView = paramView.findViewById(2131755640);
    this.mVoiceInputButton = ((ImageButton)paramView.findViewById(2131755645));
    this.mVoiceInputButton.setOnClickListener(this.mTopMenuButtonClickListener);
    if (!com.android.inputmethod.latin.settings.c.a().g.a(this.mCurrentEditorInfo)) {
      this.mVoiceInputButton.setVisibility(8);
    }
    this.mVoiceInputButton.setTag(Integer.valueOf(2));
    this.mVoiceRedPoint = ((ImageView)paramView.findViewById(2131755646));
    this.mTopSuggestionSettingView = paramView.findViewById(2131755908);
    this.mTopMenuButton = ((ImageButton)paramView.findViewById(2131755642));
    this.mTopMenuButton.setOnClickListener(this.mTopMenuButtonClickListener);
    this.mTopMenuButton.setTag(Integer.valueOf(0));
    this.mTopMenuBtnRedPoint = ((ImageView)paramView.findViewById(2131755643));
  }
  
  public void setSubTip(int paramInt)
  {
    this.mSubTip = paramInt;
  }
  
  public void showOptionDialog(AlertDialog paramAlertDialog)
  {
    IBinder localIBinder = this.mKeyboardSwitcher.c.getWindowToken();
    if (localIBinder == null) {
      return;
    }
    paramAlertDialog.setCancelable(true);
    paramAlertDialog.setCanceledOnTouchOutside(true);
    Window localWindow = paramAlertDialog.getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    localLayoutParams.token = localIBinder;
    localLayoutParams.type = 1003;
    localWindow.setAttributes(localLayoutParams);
    localWindow.addFlags(131072);
    this.mOptionsDialog = paramAlertDialog;
    paramAlertDialog.show();
  }
  
  public void toDismissTopMenuPopup()
  {
    this.mKeyboardSwitcher.C();
  }
  
  public void unsetIsAutoCorrectionIndicatorOnAndCallShowSuggestionStrip(w paramW, String paramString)
  {
    this.mIsAutoCorrectionIndicatorOn = false;
    g localG = this.mHandler;
    localG.removeMessages(3);
    localG.obtainMessage(3, 0, 1, new Pair(paramW, paramString)).sendToTarget();
  }
  
  public void updateFullscreenMode()
  {
    super.updateFullscreenMode();
    if (this.mKeyPreviewBackingView == null) {
      return;
    }
    View localView = this.mKeyPreviewBackingView;
    if (isFullscreenMode()) {}
    for (int i = 8;; i = 0)
    {
      localView.setVisibility(i);
      return;
    }
  }
  
  public final class a
    extends BroadcastReceiver
  {
    public a() {}
    
    public final void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getAction();
      if (paramContext.equals("emoji.keyboard.emoticonkeyboard.KAKA_ART_PACKAGE_CHANGED_INTENT")) {
        if (LatinIME.this.mKeyboardSwitcher != null)
        {
          paramContext = LatinIME.this.mKeyboardSwitcher;
          if (paramContext.d != null)
          {
            paramContext = paramContext.d;
            if (paramContext.f != null) {
              paramContext.f.a();
            }
          }
        }
      }
      do
      {
        do
        {
          do
          {
            return;
            if (paramContext.equals("emoji.keyboard.emoticonkeyboard.ACTION_REFRESH_ONLINE_ART"))
            {
              LatinIME.g.e(LatinIME.this.mHandler);
              return;
            }
          } while ((!paramContext.equals("action_sticker_changed")) || (LatinIME.this.mKeyboardSwitcher == null));
          paramContext = LatinIME.this.mKeyboardSwitcher;
        } while (paramContext.d == null);
        paramContext = paramContext.d;
      } while (paramContext.d == null);
      paramContext = paramContext.d;
      paramContext.a();
      paramContext.c = new StickerView.a(paramContext, paramContext.e);
      paramContext.a.setAdapter(paramContext.c);
      paramContext.b.notifyDataSetChanged();
    }
  }
  
  public static abstract interface b
  {
    public abstract void a();
  }
  
  public final class c
  {
    public String a;
    public boolean b;
    
    public c(String paramString)
    {
      this.a = paramString;
      this.b = false;
    }
  }
  
  private static final class d
    implements Handler.Callback
  {
    final Handler a;
    final LatinIME b;
    final Object c = new Object();
    boolean d;
    
    private d(LatinIME paramLatinIME)
    {
      HandlerThread localHandlerThread = new HandlerThread(d.class.getSimpleName());
      localHandlerThread.start();
      this.a = new Handler(localHandlerThread.getLooper(), this);
      this.b = paramLatinIME;
    }
    
    public final void a(int paramInt, v.a paramA)
    {
      this.a.obtainMessage(2, paramInt, 0, paramA).sendToTarget();
    }
    
    final void a(o paramO, v.a paramA)
    {
      aa localAa = this.b.mWordComposer;
      o localO = localAa.b;
      localO.b.a(paramO.b);
      localO.c.a(paramO.c);
      localO.d.a(paramO.d);
      localO.e.a(paramO.e);
      localAa.f = true;
      this.b.getSuggestedWordsOrOlderSuggestionsAsync(0, new LatinIME.d.3(this, paramA));
    }
    
    public final boolean handleMessage(Message arg1)
    {
      switch (???.what)
      {
      }
      for (;;)
      {
        return true;
        o localO1 = (o)???.obj;
        synchronized (this.c)
        {
          if (this.d) {}
        }
        a(localO2, new LatinIME.d.1(this));
        continue;
        this.b.getSuggestedWords(???.arg1, (v.a)???.obj);
      }
    }
  }
  
  static final class e
  {
    InputMethodSubtype a;
    boolean b;
    
    e() {}
  }
  
  private final class f
    implements View.OnClickListener
  {
    String a;
    
    private f() {}
    
    public final void onClick(View paramView)
    {
      paramView = paramView.getTag();
      if (paramView == null) {
        return;
      }
      switch (((Integer)paramView).intValue())
      {
      default: 
        return;
      case 0: 
        LatinIME.this.mTopMenuBtnRedPoint.setVisibility(8);
        if (!LatinIME.this.mKeyboardSwitcher.l.a.isShowing()) {
          LatinIME.this.mKeyboardSwitcher.D();
        }
        for (;;)
        {
          PreferenceManager.getDefaultSharedPreferences(LatinIME.this).edit().putBoolean("IS_TOPMENU_CLICKED_ONCE", true).apply();
          return;
          LatinIME.this.mKeyboardSwitcher.C();
        }
      case 1: 
        LatinIME.this.mVoiceRedPoint.setVisibility(8);
        PreferenceManager.getDefaultSharedPreferences(LatinIME.this).edit().putBoolean("IS_VOICE_SEARCH_CLICKED_ONCE", true).apply();
        LatinIME.this.searchSuggestion(this.a);
        return;
      }
      LatinIME.this.onPressKey(-7, 0, false);
      LatinIME.this.onCodeInput(-7, -1, -1);
      LatinIME.this.onReleaseKey(-7, false);
    }
  }
  
  public static final class g
    extends com.android.inputmethod.latin.d.aa<LatinIME>
  {
    int a;
    int b;
    long c;
    long d;
    AsyncHttpClient e;
    Context f;
    DCAgent g;
    boolean h;
    boolean i;
    boolean j;
    boolean k;
    boolean l;
    EditorInfo m;
    
    public g(LatinIME paramLatinIME)
    {
      super();
    }
    
    private void d(long paramLong)
    {
      if (!hasMessages(15)) {
        sendEmptyMessageDelayed(15, paramLong);
      }
    }
    
    private void e(long paramLong)
    {
      if (!hasMessages(16)) {
        sendEmptyMessageDelayed(16, paramLong);
      }
    }
    
    private void f(long paramLong)
    {
      if (!hasMessages(17)) {
        sendEmptyMessageDelayed(17, paramLong);
      }
    }
    
    final void a()
    {
      final com.android.inputmethod.keyboard.h localH = ((LatinIME)this.n.get()).mKeyboardSwitcher;
      if (com.myandroid.promotion.b.b.a((Context)this.n.get()))
      {
        RequestParams localRequestParams = new RequestParams();
        this.e.get(this.f.getString(2131297197), localRequestParams, new AsyncHttpResponseHandler()
        {
          public final void onFailure(int paramAnonymousInt, Header[] paramAnonymousArrayOfHeader, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
          {
            Log.e(LatinIME.TAG, "query online art info failure");
            localH.E();
          }
          
          public final void onSuccess(int paramAnonymousInt, Header[] paramAnonymousArrayOfHeader, byte[] paramAnonymousArrayOfByte)
          {
            paramAnonymousArrayOfHeader = new String(paramAnonymousArrayOfByte);
            PreferenceManager.getDefaultSharedPreferences((Context)LatinIME.g.this.n.get()).edit().putString("pref_online_art_info_cache_artview", paramAnonymousArrayOfHeader).commit();
            localH.E();
          }
        });
        return;
      }
      localH.E();
    }
    
    public final void a(long paramLong)
    {
      if (!hasMessages(14)) {
        sendEmptyMessageDelayed(14, paramLong);
      }
    }
    
    final void a(LatinIME paramLatinIME, EditorInfo paramEditorInfo, boolean paramBoolean)
    {
      if (this.k) {
        paramLatinIME.onFinishInputViewInternal(this.l);
      }
      if (this.l) {
        paramLatinIME.onFinishInputInternal();
      }
      if (this.j) {
        paramLatinIME.onStartInputInternal(paramEditorInfo, paramBoolean);
      }
      d();
    }
    
    public final void a(w paramW, boolean paramBoolean)
    {
      removeMessages(3);
      if (paramBoolean) {}
      for (int n = 1;; n = 2)
      {
        obtainMessage(3, n, 0, paramW).sendToTarget();
        return;
      }
    }
    
    public final void a(boolean paramBoolean, int paramInt)
    {
      removeMessages(7);
      if (paramBoolean) {}
      for (int n = 1;; n = 0)
      {
        sendMessage(obtainMessage(7, n, paramInt, null));
        return;
      }
    }
    
    public final void b()
    {
      sendMessageDelayed(obtainMessage(2), this.a);
    }
    
    public final void b(long paramLong)
    {
      if (!hasMessages(8)) {
        sendEmptyMessageDelayed(8, paramLong);
      }
    }
    
    public final void c()
    {
      removeMessages(4);
      sendMessageDelayed(obtainMessage(4), this.a);
    }
    
    public final void c(long paramLong)
    {
      if (!hasMessages(9)) {
        sendEmptyMessageDelayed(9, paramLong);
      }
    }
    
    final void d()
    {
      this.k = false;
      this.l = false;
      this.j = false;
    }
    
    public final void handleMessage(final Message paramMessage)
    {
      int n = 0;
      boolean bool2 = true;
      boolean bool1 = true;
      Object localObject1 = (LatinIME)this.n.get();
      Object localObject2 = ((LatinIME)localObject1).mKeyboardSwitcher;
      switch (paramMessage.what)
      {
      case 1: 
      case 12: 
      case 13: 
      default: 
        return;
      case 2: 
        ((LatinIME)localObject1).updateSuggestionStrip();
        return;
      case 0: 
        ((com.android.inputmethod.keyboard.h)localObject2).e();
        return;
      case 3: 
        if (paramMessage.arg1 == 0)
        {
          if (paramMessage.arg2 == 1)
          {
            paramMessage = (Pair)paramMessage.obj;
            ((LatinIME)localObject1).showSuggestionStripWithTypedWord((w)paramMessage.first, (String)paramMessage.second);
            return;
          }
          ((LatinIME)localObject1).showSuggestionStrip((w)paramMessage.obj);
          return;
        }
        localObject2 = (w)paramMessage.obj;
        if (paramMessage.arg1 == 1) {}
        for (;;)
        {
          ((LatinIME)localObject1).showGesturePreviewAndSuggestionStrip((w)localObject2, bool1);
          return;
          bool1 = false;
        }
      case 4: 
        ((LatinIME)localObject1).restartSuggestionsOnWordTouchedByCursor();
        return;
      case 5: 
        ((LatinIME)localObject1).initSuggest();
        b();
        return;
      case 6: 
        ((LatinIME)localObject1).onEndBatchInputAsyncInternal((w)paramMessage.obj);
        return;
      case 7: 
        if (paramMessage.arg1 == 1) {}
        for (bool1 = bool2;; bool1 = false)
        {
          ((LatinIME)localObject1).retryResetCaches(bool1, paramMessage.arg2);
          return;
        }
      case 8: 
        paramMessage = PreferenceManager.getDefaultSharedPreferences((Context)this.n.get());
        if (com.myandroid.promotion.b.b.a((Context)this.n.get()))
        {
          localObject1 = new RequestParams();
          ((RequestParams)localObject1).put("key", emoji.keyboard.searchbox.ui.trending.a.a());
          this.e.get(this.f.getString(2131297027), (RequestParams)localObject1, new AsyncHttpResponseHandler()
          {
            public final void onFailure(int paramAnonymousInt, Header[] paramAnonymousArrayOfHeader, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
            {
              Log.d(LatinIME.TAG, "query hash info failure");
            }
            
            public final void onSuccess(int paramAnonymousInt, Header[] paramAnonymousArrayOfHeader, byte[] paramAnonymousArrayOfByte)
            {
              paramAnonymousArrayOfHeader = new String(paramAnonymousArrayOfByte);
              paramAnonymousArrayOfByte = new com.google.a.f();
              try
              {
                paramAnonymousArrayOfHeader = (PromotionHashInfo)paramAnonymousArrayOfByte.a(paramAnonymousArrayOfHeader, new LatinIME.g.3.1(this).getType());
                if (paramAnonymousArrayOfHeader != null)
                {
                  LatinIME.g.a(paramMessage, paramAnonymousArrayOfHeader);
                  LatinIME.g.b(paramMessage, paramAnonymousArrayOfHeader);
                  LatinIME.g.c(paramMessage, paramAnonymousArrayOfHeader);
                  LatinIME.g.a(LatinIME.g.this, paramMessage, paramAnonymousArrayOfHeader);
                  LatinIME.g.b(LatinIME.g.this, paramMessage, paramAnonymousArrayOfHeader);
                  LatinIME.g.c(LatinIME.g.this, paramMessage, paramAnonymousArrayOfHeader);
                  LatinIME.g.a(LatinIME.g.this, paramAnonymousArrayOfHeader);
                  LatinIME.g.d(LatinIME.g.this, paramMessage, paramAnonymousArrayOfHeader);
                  LatinIME.g.e(LatinIME.g.this, paramMessage, paramAnonymousArrayOfHeader);
                }
                return;
              }
              catch (Exception paramAnonymousArrayOfHeader)
              {
                for (;;)
                {
                  paramAnonymousArrayOfHeader = null;
                }
              }
            }
          });
        }
        emoji.keyboard.searchbox.d.v.a((Context)this.n.get());
        localObject2 = (Context)this.n.get();
        String str1;
        String str2;
        if (!"emoji.keyboard.emoticonkeyboard.premium".equals(((Context)localObject2).getPackageName()))
        {
          localObject1 = OnlineConfigAgent.getInstance();
          ((OnlineConfigAgent)localObject1).updateOnlineConfig((Context)localObject2);
          paramMessage = ((OnlineConfigAgent)localObject1).getConfigParams((Context)localObject2, ((Context)localObject2).getString(2131297083));
          str1 = ((OnlineConfigAgent)localObject1).getConfigParams((Context)localObject2, ((Context)localObject2).getString(2131297084));
          str2 = ((OnlineConfigAgent)localObject1).getConfigParams((Context)localObject2, ((Context)localObject2).getString(2131297085));
        }
        try
        {
          i1 = Integer.decode(paramMessage).intValue();
          paramMessage = null;
        }
        catch (NumberFormatException paramMessage)
        {
          for (;;)
          {
            int i1;
            label488:
            paramMessage.printStackTrace();
            continue;
            n = paramMessage.versionCode;
          }
        }
      }
      try
      {
        localObject1 = ((Context)localObject2).getPackageManager().getPackageInfo(((Context)localObject2).getPackageName(), 16384);
        paramMessage = (Message)localObject1;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        break label488;
      }
      if (paramMessage == null)
      {
        if (i1 > n) {
          com.myandroid.umeng.a.a((Context)localObject2, str1, str2);
        }
        paramMessage = com.mavl.b.a.a.a().a.a("emoji_recent_promote_apps", "configns:firebase");
        if (!TextUtils.isEmpty(paramMessage)) {
          PreferenceManager.getDefaultSharedPreferences((Context)this.n.get()).edit().putString("pref_emoji_recent_tab_app_list", paramMessage).apply();
        }
        b(86400000L);
        return;
      }
      if (com.myandroid.promotion.b.b.a(com.android.inputmethod.latin.kkuirearch.utils.g.b))
      {
        paramMessage = new RequestParams();
        com.android.inputmethod.latin.kkuirearch.utils.g.a.get("http://www.phoneonlineupdate.com:7080/top_menu_promotion/promotions.php", paramMessage, new g.1());
      }
      c(14400000L);
      return;
      if (!emoji.keyboard.emoticonkeyboard.theme.b.a((Context)this.n.get())) {}
      for (paramMessage = ((LatinIME)this.n.get()).getPackageName() + "." + PreferenceManager.getDefaultSharedPreferences((Context)this.n.get()).getString("keyboard_theme_name", "Default");; paramMessage = emoji.keyboard.emoticonkeyboard.theme.b.b((Context)this.n.get()))
      {
        emoji.keyboard.emoticonkeyboard.extras.d.c((Context)this.n.get(), "CurrentTheme：" + paramMessage);
        if (hasMessages(10)) {
          break;
        }
        sendEmptyMessageDelayed(10, 86400000L);
        return;
      }
      paramMessage = "";
      if (PreferenceManager.getDefaultSharedPreferences((Context)this.n.get()).getString("kbd_emoji_style", "2").equals("2")) {
        paramMessage = "" + "Native";
      }
      for (;;)
      {
        emoji.keyboard.emoticonkeyboard.extras.d.c((Context)this.n.get(), "CurrentEmojiStyle：" + paramMessage);
        if (hasMessages(11)) {
          break;
        }
        sendEmptyMessageDelayed(11, 86400000L);
        return;
        if (PreferenceManager.getDefaultSharedPreferences((Context)this.n.get()).getString("kbd_emoji_style", "2").equals("0")) {
          paramMessage = "" + "Android";
        } else if (PreferenceManager.getDefaultSharedPreferences((Context)this.n.get()).getString("kbd_emoji_style", "2").equals("1")) {
          paramMessage = "" + "Colorful";
        } else if (PreferenceManager.getDefaultSharedPreferences((Context)this.n.get()).getString("kbd_emoji_style", "2").equals("3")) {
          paramMessage = "" + "Twitter";
        } else if (PreferenceManager.getDefaultSharedPreferences((Context)this.n.get()).getString("kbd_emoji_style", "2").equals("3")) {
          paramMessage = "" + "Puppy";
        }
      }
      LockScreenService.a((Context)this.n.get());
      a(86400000L);
      return;
      if (com.mavl.b.a.a.a().a.b("bool_udc_enable", "configns:firebase")) {
        paramMessage = this.g;
      }
      try
      {
        paramMessage.a.a();
        postDelayed(new Runnable()
        {
          public final void run()
          {
            DCAgent localDCAgent = LatinIME.g.a(LatinIME.g.this);
            try
            {
              k localK = localDCAgent.a.b();
              localDCAgent.b.add(localK);
              localDCAgent.a("http://mz.cocamobile.com:7080/keyinfo/base_key.php", localDCAgent.b);
              return;
            }
            catch (Exception localException)
            {
              Log.e("DCAgent", localException.toString());
            }
          }
        }, 5000L);
        d(86400000L);
        return;
      }
      catch (Exception paramMessage)
      {
        for (;;)
        {
          Log.e("DCAgent", paramMessage.toString());
        }
      }
      com.mavl.b.a.a.a().b();
      e(3600000L);
      return;
      paramMessage = com.android.inputmethod.latin.kkuirearch.utils.d.a((Context)this.n.get());
      localObject1 = new AsyncHttpClient();
      ((AsyncHttpClient)localObject1).setTimeout(7000);
      ((AsyncHttpClient)localObject1).get("http://www.phoneonlineupdate.com:7080/online_collection/collection.php", new d.1(paramMessage));
      f(86400000L);
    }
  }
}
