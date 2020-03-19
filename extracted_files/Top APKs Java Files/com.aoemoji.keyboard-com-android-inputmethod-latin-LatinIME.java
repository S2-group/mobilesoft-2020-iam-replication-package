package com.android.inputmethod.latin;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.InputMethodService.Insets;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.PrintWriterPrinter;
import android.util.Printer;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputBinding;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import cl.b.d;
import com.MainApp;
import com.android.inputmethod.keyboard.FontTextView;
import com.android.inputmethod.keyboard.KeyboardSwitcher;
import com.android.inputmethod.keyboard.KeyboardSwitcher.RemoveAdsReceiver;
import com.android.inputmethod.keyboard.MainKeyboardView;
import com.android.inputmethod.keyboard.internal.PreviewPlacerView;
import com.android.inputmethod.keyboard.internal.ac;
import com.android.inputmethod.keyboard.internal.d.a;
import com.android.inputmethod.keyboard.internal.u.b;
import com.android.inputmethod.keyboard.k;
import com.android.inputmethod.keyboard.kbquicksetting.ClipItemData;
import com.android.inputmethod.keyboard.m;
import com.android.inputmethod.keyboard.s;
import com.android.inputmethod.keyboard.translate.EditTextCursorWatcher;
import com.android.inputmethod.latin.personalization.DictionaryDecayBroadcastReciever;
import com.android.inputmethod.latin.settings.SettingsActivity;
import com.android.inputmethod.latin.suggestions.MoreSuggestionsView;
import com.android.inputmethod.latin.suggestions.SuggestionStripView;
import com.android.inputmethod.latin.suggestions.SuggestionStripView.a;
import com.android.inputmethod.latin.utils.ab.a;
import com.android.inputmethod.latin.utils.ag;
import com.android.inputmethod.latin.utils.ah;
import com.android.inputmethod.latin.utils.y;
import com.android.inputmethod.latin.utils.z;
import com.android.inputmethodcommon.InputMethodSettingsFragment;
import com.emoji.network.beans.f.b;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kb.anims.DrawingView;
import com.more.setting.PreviewSkinActivity;
import com.more.setting.RateUsActivity;
import com.more.setting.db.CustomTheme;
import com.more.setting.db.CustomWallpaper;
import com.more.setting.db.PlusInfo;
import com.more.setting.db.PlusInfoDao.Properties;
import com.more.setting.fragments.font.a.a;
import com.more.setting.nightmode.NightModeFragment;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;

@SuppressLint({"NewApi"})
public class LatinIME
  extends InputMethodService
  implements ClipboardManager.OnPrimaryClipChangedListener, SharedPreferences.OnSharedPreferenceChangeListener, View.OnClickListener, View.OnLongClickListener, b.d, m, SuggestionStripView.a, ab.a, w.c
{
  private static boolean DEBUG;
  private static final String TAG = LatinIME.class.getSimpleName();
  private static boolean bds = true;
  public final KeyboardSwitcher aJH = KeyboardSwitcher.lA();
  private ClipboardManager aJk;
  public final v aMQ = v.pb();
  public SuggestionStripView aMo;
  com.android.inputmethod.keyboard.a aMw;
  private x aRT = x.beZ;
  private View bcA;
  public View bcB;
  public ViewGroup bcC;
  w bcD;
  private CompletionInfo[] bcE;
  private ao.b bcF = new ao.b();
  public u bcG;
  private final c bcH = new c();
  private ap.c bcI = new ap.c(this);
  boolean bcJ;
  private aa bcK;
  private com.android.inputmethod.latin.personalization.f bcL;
  private com.android.inputmethod.latin.personalization.e bcM;
  private com.android.inputmethod.latin.personalization.c bcN;
  private boolean bcO;
  private q bcP = q.bci;
  private final ab bcQ = new ab();
  public final t bcR = new t(this);
  private final com.android.inputmethod.latin.utils.t bcS = new com.android.inputmethod.latin.utils.t();
  private int bcT = -1;
  private int bcU = -1;
  private boolean bcV;
  private int bcW;
  private long bcX;
  private final TreeSet<Long> bcY = new TreeSet();
  private boolean bcZ = false;
  private final LinkedList<String> bcj = new LinkedList();
  public e bck;
  private boolean bcl = true;
  private FirebaseAnalytics bcm;
  public a bcn;
  private int bco;
  private final StringBuilder bcp = new StringBuilder();
  public final com.android.inputmethod.latin.settings.e bcq = com.android.inputmethod.latin.settings.e.pH();
  private boolean bcr = false;
  private boolean bcs = false;
  int bct = -1;
  int bcu = -1;
  private ClipItemData bcv;
  public long bcw;
  final SparseArray<String> bcx = new SparseArray();
  private BroadcastReceiver bcy = new BroadcastReceiver()
  {
    public final void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      LatinIME.this.I(LatinIME.this);
    }
  };
  private as.a bcz;
  private BroadcastReceiver bdA = new BroadcastReceiver()
  {
    public final void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      boolean bool = false;
      paramAnonymousContext = paramAnonymousIntent.getAction();
      if (paramAnonymousContext.equals("android.net.conn.CONNECTIVITY_CHANGE"))
      {
        paramAnonymousContext = LatinIME.n(LatinIME.this);
        if (!paramAnonymousIntent.getBooleanExtra("noConnectivity", false)) {
          bool = true;
        }
        paramAnonymousContext.beD = bool;
      }
      while (!paramAnonymousContext.equals("android.media.RINGER_MODE_CHANGED")) {
        return;
      }
      paramAnonymousContext = c.nW();
      paramAnonymousContext.bak = paramAnonymousContext.nX();
    }
  };
  private boolean bda = false;
  private int bdb;
  private int bdc;
  private String bdd;
  private boolean bde;
  private AlertDialog bdf;
  private final boolean bdg = ao.f.b(this);
  public final d bdh = new d(this);
  private b bdi;
  PackageManReceiver bdj;
  RateUsNotificationCancelReceiver bdk;
  GifListChangeLocalReceiver bdl;
  CustomThemeUpdateReceiver bdm;
  public ar.a bdn;
  public Locale bdo = Locale.ROOT;
  private long bdp;
  public cl.b bdq;
  public com.android.inputmethod.latin.suggestions.d bdr;
  private long bdt = 0L;
  private int bdu = 0;
  private long bdv = Long.MAX_VALUE;
  public int bdw = -1;
  private int bdx;
  boolean bdy = false;
  private boolean bdz = false;
  final Rect mTempRect = new Rect();
  
  static
  {
    com.android.inputmethod.latin.utils.p.qk();
  }
  
  public LatinIME()
  {
    Log.i(TAG, "Hardware accelerated drawing: " + this.bdg);
  }
  
  public static NotificationCompat.Builder a(Context paramContext, String paramString1, String paramString2, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2)
  {
    paramString1 = new NotificationCompat.Builder(paramContext).setContentTitle(paramString1).setContentText(paramString2).setAutoCancel(true).setSmallIcon(2130837854).setDefaults(-1).setWhen(System.currentTimeMillis());
    if (paramPendingIntent1 != null) {
      paramString1.setContentIntent(paramPendingIntent1);
    }
    if (paramPendingIntent2 != null) {
      paramString1.setDeleteIntent(paramPendingIntent2);
    }
    if (Build.VERSION.SDK_INT >= 21) {
      paramString1.setLargeIcon(((BitmapDrawable)ContextCompat.getDrawable(paramContext, 2130837854)).getBitmap()).setColor(ContextCompat.getColor(paramContext, 2131689639)).setSmallIcon(2130837895);
    }
    return paramString1;
  }
  
  private void a(AlertDialog paramAlertDialog)
  {
    paramAlertDialog.setCancelable(true);
    paramAlertDialog.setCanceledOnTouchOutside(true);
    if (a(paramAlertDialog)) {
      this.bdf = paramAlertDialog;
    }
  }
  
  private void a(g paramG)
  {
    w localW = this.bcD;
    int i;
    if ((localW != null) && (this.bcq.baj.biR))
    {
      i = 1;
      if (i != 0) {
        break label61;
      }
      if (paramG != null) {
        paramG.close();
      }
      paramG = null;
    }
    for (;;)
    {
      if (localW != null)
      {
        localW.beQ = paramG;
        localW.a("contacts", paramG);
      }
      return;
      i = 0;
      break;
      label61:
      Locale localLocale = this.aMQ.pe();
      if (paramG != null)
      {
        if (!paramG.mLocale.equals(localLocale))
        {
          paramG.close();
          paramG = new g(this, localLocale);
        }
        else
        {
          paramG.H(this);
        }
      }
      else {
        paramG = new g(this, localLocale);
      }
    }
  }
  
  private void a(final w.b paramB)
  {
    final CharSequence localCharSequence = oI();
    this.bdi.a(localCharSequence, new w.b()
    {
      public final void d(x paramAnonymousX)
      {
        paramB.d(LatinIME.a(LatinIME.this, LatinIME.m(LatinIME.this).bft.toString(), paramAnonymousX));
        LatinIME.this.a(localCharSequence, paramAnonymousX, true);
      }
    });
  }
  
  private CharSequence aB(String paramString)
  {
    if ((!this.bcr) || (!oB())) {
      return paramString;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramString.length())
    {
      localStringBuilder.append(cj(paramString.charAt(i)));
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private CharSequence aC(String paramString)
  {
    Object localObject = paramString;
    if (this.bde) {
      localObject = ao.i.k(this, paramString);
    }
    return localObject;
  }
  
  private x aD(String paramString)
  {
    x localX2 = this.aRT;
    x localX1 = localX2;
    if (localX2 == this.bcq.baj.biG) {
      localX1 = x.beZ;
    }
    if (paramString == null) {
      return localX1;
    }
    return new x(x.a(paramString, localX1), false, false, false, true, false);
  }
  
  private void aE(String paramString)
  {
    if (this.bdh.hasMessages(2)) {
      oH();
    }
    Object localObject = this.bcQ.bfu;
    String str = this.bcQ.bft.toString();
    if (localObject != null) {}
    while (localObject != null) {
      if (TextUtils.isEmpty(str))
      {
        throw new RuntimeException("We have an auto-correction but the typed word is empty? Impossible! I must commit suicide.");
        localObject = str;
      }
      else
      {
        if (this.bcq.baj.bjj) {
          com.android.inputmethod.latin.utils.q.a(str, (String)localObject, paramString, this.bcQ);
        }
        this.bcV = true;
        b((String)localObject, 2, paramString);
        if (!str.equals(localObject))
        {
          paramString = this.bcR;
          localObject = new CorrectionInfo(this.bcU - str.length(), str, (CharSequence)localObject);
          if (paramString.bep != null) {
            paramString.bep.commitCorrection((CorrectionInfo)localObject);
          }
        }
      }
    }
  }
  
  private void av(boolean paramBoolean)
  {
    this.bcQ.reset();
    if (paramBoolean) {
      this.bcP = q.bci;
    }
  }
  
  private void aw(boolean paramBoolean)
  {
    if ((this.bde != paramBoolean) && (this.bcQ.pj()))
    {
      this.bde = paramBoolean;
      CharSequence localCharSequence = aC(oC());
      this.bcR.h(localCharSequence);
    }
  }
  
  private void ax(String paramString)
  {
    if (!this.bcQ.pj()) {}
    String str;
    do
    {
      return;
      str = this.bcQ.bft.toString();
    } while (str.length() <= 0);
    b(str, 0, paramString);
  }
  
  private void b(x paramX, String paramString)
  {
    if (paramX.bff.isEmpty())
    {
      an.b.kr().a(null, null);
      oG();
      return;
    }
    if (!paramX.bff.isEmpty()) {
      if (!paramX.bfb) {
        break label86;
      }
    }
    label86:
    for (String str = paramX.cm(1);; str = paramString)
    {
      this.bcQ.bfu = str;
      boolean bool = paramX.bfb;
      a(paramX, bool);
      aw(bool);
      oE();
      an.b.kr().a(paramX, paramString);
      return;
    }
  }
  
  private void b(String paramString1, int paramInt, String paramString2)
  {
    Object localObject1 = this.aRT;
    Object localObject2 = new com.emoji.network.beans.f();
    Object localObject3 = new f.b();
    if ((this.bcQ.pj()) && (this.bcQ.bfw))
    {
      ((f.b)localObject3).setFrom("滑行");
      ((f.b)localObject3).setIndex(this.bdx);
      this.bdx = 0;
      ((com.emoji.network.beans.f)localObject2).setE("输入");
      ((com.emoji.network.beans.f)localObject2).setF("上屏");
      ((com.emoji.network.beans.f)localObject2).setG("文本");
      ((f.b)localObject3).setWhat(paramString1);
      ((com.emoji.network.beans.f)localObject2).setH((f.b)localObject3);
      a((com.emoji.network.beans.f)localObject2);
      this.bcR.a(ao.i.a(this, aB(paramString1).toString(), (x)localObject1, this.bcJ), 1);
      if (!TextUtils.isEmpty(paramString1)) {
        break label500;
      }
      localObject1 = null;
    }
    Object localObject4;
    for (;;)
    {
      localObject2 = this.bcQ;
      localObject3 = ((ab)localObject2).bcc;
      ((ab)localObject2).bcc = new int[48];
      paramString1 = new q((int[])localObject3, ((ab)localObject2).bch, ((ab)localObject2).bft.toString(), paramString1, paramString2, (String)localObject1, ((ab)localObject2).bcg);
      ((ab)localObject2).bch.reset();
      if ((paramInt != 2) && (paramInt != 1)) {
        paramString1.mActive = false;
      }
      ((ab)localObject2).bfy = 0;
      ((ab)localObject2).bfz = 0;
      ((ab)localObject2).bfw = false;
      ((ab)localObject2).bft.setLength(0);
      ((ab)localObject2).bfB = 0;
      ((ab)localObject2).bfA = 0;
      ((ab)localObject2).bfD = false;
      ((ab)localObject2).bcg = 0;
      ((ab)localObject2).pi();
      ((ab)localObject2).bfu = null;
      ((ab)localObject2).bfC = 0;
      ((ab)localObject2).bfv = false;
      ((ab)localObject2).bfx = null;
      this.bcP = paramString1;
      localObject1 = oI();
      paramString1 = (String)localObject1;
      if (!TextUtils.equals(paramString2, "")) {
        paramString1 = localObject1 + paramString2;
      }
      a(paramString1, null, false);
      return;
      switch (paramInt)
      {
      }
      for (;;)
      {
        localObject4 = new com.emoji.network.beans.f();
        ((com.emoji.network.beans.f)localObject4).setE("输入");
        ((com.emoji.network.beans.f)localObject4).setF("点击");
        ((com.emoji.network.beans.f)localObject4).setG("按键");
        f.b localB = new f.b();
        localB.setSl(this.bcj);
        ((com.emoji.network.beans.f)localObject4).setH(localB);
        a((com.emoji.network.beans.f)localObject4);
        this.bcj.clear();
        break;
        ((f.b)localObject3).setFrom("用户输入");
        continue;
        ((f.b)localObject3).setFrom("点击候选");
        ((f.b)localObject3).setIndex(this.bdx);
        continue;
        ((f.b)localObject3).setFrom("自动校正");
      }
      label500:
      localObject3 = this.bcD;
      if (localObject3 == null)
      {
        localObject1 = null;
      }
      else
      {
        localObject1 = this.bcq.baj;
        if (!((com.android.inputmethod.latin.settings.f)localObject1).bjg)
        {
          localObject1 = null;
        }
        else
        {
          localObject4 = this.bcL;
          if (localObject4 != null) {
            break label557;
          }
          localObject1 = null;
        }
      }
    }
    label557:
    localObject2 = this.bcR.f(((com.android.inputmethod.latin.settings.f)localObject1).biH, 2);
    localObject1 = this.bcQ;
    int i;
    if ((((ab)localObject1).bcg == 7) || (((ab)localObject1).bcg == 5))
    {
      i = 1;
      label600:
      if ((i == 0) || (this.bcQ.pn())) {
        break label656;
      }
    }
    label656:
    for (localObject1 = paramString1.toLowerCase(this.aMQ.pe());; localObject1 = paramString1)
    {
      i = com.android.inputmethod.latin.utils.d.a(((w)localObject3).beN, paramString1);
      if (i != 0) {
        break label662;
      }
      localObject1 = null;
      break;
      i = 0;
      break label600;
    }
    label662:
    if (i > 0) {}
    for (boolean bool = true;; bool = false)
    {
      ((com.android.inputmethod.latin.personalization.f)localObject4).b((String)localObject2, (String)localObject1, bool);
      localObject1 = localObject2;
      break;
    }
  }
  
  private void c(x paramX)
  {
    if (paramX.bff.isEmpty())
    {
      oG();
      return;
    }
    b(paramX, paramX.cm(0));
  }
  
  private void cf(int paramInt)
  {
    boolean bool = this.bcQ.pj();
    av(true);
    com.android.inputmethod.latin.settings.f localF = this.bcq.baj;
    if (localF != null)
    {
      if (!localF.biU) {
        break label47;
      }
      oG();
    }
    for (;;)
    {
      this.bcR.i(paramInt, bool);
      return;
      label47:
      a(localF.biG, false);
    }
  }
  
  private void cg(int paramInt)
  {
    t localT = this.bcR;
    localT.bep = localT.beo.getCurrentInputConnection();
    if (localT.bep != null) {
      localT.bep.performEditorAction(paramInt);
    }
  }
  
  private void ch(int paramInt)
  {
    long l = SystemClock.uptimeMillis();
    this.bcR.b(new KeyEvent(l, l, 0, paramInt, 0, 0, -1, 0, 6));
    this.bcR.b(new KeyEvent(SystemClock.uptimeMillis(), l, 1, paramInt, 0, 0, -1, 0, 6));
  }
  
  private void ci(int paramInt)
  {
    if ((paramInt >= 48) && (paramInt <= 57))
    {
      ch(paramInt - 48 + 7);
      return;
    }
    if ((10 == paramInt) && (this.bcF.kw()))
    {
      ch(66);
      return;
    }
    String str = cj(paramInt);
    this.bcR.a(str, 1);
  }
  
  private String cj(int paramInt)
  {
    int m = 0;
    int i = 0;
    String str1 = z.dg(paramInt);
    if ((!this.bcr) || (!oB())) {
      return str1;
    }
    if ((this.bcx.size() == 0) || (this.bcu != this.bct))
    {
      this.bcx.clear();
      this.bcu = this.bct;
      boolean bool = cb.c.bDe.contains(Integer.valueOf(this.bct));
      String str2 = cb.c.bDf[this.bct];
      String str3 = str2.toUpperCase(Locale.ENGLISH);
      if (!bool)
      {
        this.bcR.b(str2, true);
        this.bcR.b(str3, false);
      }
      int k = 97;
      int j = 65;
      SparseArray localSparseArray;
      if (!bool)
      {
        int n = str2.length() / 26;
        while (i < str2.length())
        {
          localSparseArray = this.bcx;
          m = (char)(k + 1);
          localSparseArray.append(k, str2.substring(i, i + n));
          localSparseArray = this.bcx;
          k = (char)(j + 1);
          localSparseArray.append(j, str3.substring(i, i + n));
          i += n;
          j = k;
          k = m;
        }
      }
      if (str2.codePointCount(0, str2.length()) == 26)
      {
        i = m;
        while (i < str2.length())
        {
          localSparseArray = this.bcx;
          m = (char)(k + 1);
          localSparseArray.append(k, String.valueOf(Character.toChars(str2.codePointAt(i))));
          localSparseArray = this.bcx;
          k = (char)(j + 1);
          localSparseArray.append(j, String.valueOf(Character.toChars(str3.codePointAt(i))));
          i = str2.offsetByCodePoints(i, 1);
          j = k;
          k = m;
        }
      }
    }
    return (String)this.bcx.get(paramInt, str1);
  }
  
  private boolean e(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if ((10 == paramInt1) && (2 == paramInt2)) {
      this.bcR.oW();
    }
    com.android.inputmethod.latin.settings.f localF;
    do
    {
      do
      {
        return false;
      } while (((3 != paramInt2) && (2 != paramInt2)) || (!paramBoolean));
      localF = this.bcq.baj;
    } while (localF.cM(paramInt1));
    if (localF.cN(paramInt1)) {
      return true;
    }
    this.bcR.oW();
    return false;
  }
  
  private boolean g(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.bco = 0;
    Object localObject1 = this.bcq.baj;
    Object localObject2;
    int i;
    if ((((com.android.inputmethod.latin.settings.f)localObject1).cJ(paramInt1)) || (Character.getType(paramInt1) == 28))
    {
      bool2 = false;
      localObject2 = this.bcq.baj;
      label121:
      label130:
      label139:
      Object localObject3;
      if ((32 == paramInt1) && (!((com.android.inputmethod.latin.settings.f)localObject2).biK) && (this.bcQ.pj()))
      {
        i = 1;
        if (this.bcQ.pk()) {
          cf(this.bcT);
        }
        bool1 = bool2;
        if (this.bcQ.pj())
        {
          if (!((com.android.inputmethod.latin.settings.f)localObject2).bjg) {
            break label548;
          }
          if (i == 0) {
            break label539;
          }
          localObject1 = "";
          aE((String)localObject1);
          bool1 = true;
        }
        if (-2 != paramInt2) {
          break label583;
        }
        bool2 = true;
        bool2 = e(paramInt1, paramInt4, bool2);
        if ((4 == paramInt4) && (((com.android.inputmethod.latin.settings.f)localObject2).cM(paramInt1))) {
          oM();
        }
        if (i == 0) {
          ci(paramInt1);
        }
        if (32 != paramInt1) {
          break label638;
        }
        if (((com.android.inputmethod.latin.settings.f)localObject2).cH(this.bdb))
        {
          localObject1 = this.bcq.baj;
          if (!((com.android.inputmethod.latin.settings.f)localObject1).biS) {
            break label617;
          }
          localObject3 = this.bdh;
          if (SystemClock.uptimeMillis() - ((d)localObject3).bdW >= ((d)localObject3).bdV) {
            break label589;
          }
          paramInt4 = 1;
          label241:
          if (paramInt4 == 0) {
            break label617;
          }
          localObject3 = this.bcR.ck(4);
          if (localObject3 == null) {
            break label617;
          }
          paramInt4 = ((CharSequence)localObject3).length();
          if ((paramInt4 < 3) || (((CharSequence)localObject3).charAt(paramInt4 - 1) != ' ') || (((CharSequence)localObject3).charAt(paramInt4 - 2) != ' ')) {
            break label617;
          }
          if (!Character.isSurrogatePair(((CharSequence)localObject3).charAt(0), ((CharSequence)localObject3).charAt(1))) {
            break label595;
          }
          paramInt4 = Character.codePointAt((CharSequence)localObject3, 0);
          label338:
          if ((!Character.isLetterOrDigit(paramInt4)) && (paramInt4 != 39) && (paramInt4 != 34) && (paramInt4 != 41) && (paramInt4 != 93) && (paramInt4 != 125) && (paramInt4 != 62) && (paramInt4 != 43) && (Character.getType(paramInt4) != 28)) {
            break label611;
          }
          paramInt4 = 1;
          label408:
          if (paramInt4 == 0) {
            break label617;
          }
          this.bdh.bdW = 0L;
          this.bcR.cl(2);
          localObject1 = new String(new int[] { ((com.android.inputmethod.latin.settings.f)localObject1).biI, 32 }, 0, 2);
          this.bcR.a((CharSequence)localObject1, 1);
          this.aJH.lE();
          paramInt4 = 1;
          label476:
          if (paramInt4 == 0) {
            break label623;
          }
          this.bco = 1;
        }
      }
      for (;;)
      {
        this.bdh.bdW = SystemClock.uptimeMillis();
        this.bdh.oP();
        if (((com.android.inputmethod.latin.settings.f)localObject2).bjj) {
          com.android.inputmethod.latin.utils.q.s((char)paramInt1, paramInt2, paramInt3);
        }
        this.aJH.lE();
        this.bcV = true;
        return bool1;
        i = 0;
        break;
        label539:
        localObject1 = z.dg(paramInt1);
        break label121;
        label548:
        ax(z.dg(paramInt1));
        bool1 = bool2;
        if (((com.android.inputmethod.latin.settings.f)localObject2).cH(this.bdb)) {
          break label130;
        }
        oG();
        bool1 = bool2;
        break label130;
        label583:
        bool2 = false;
        break label139;
        label589:
        paramInt4 = 0;
        break label241;
        label595:
        paramInt4 = ((CharSequence)localObject3).charAt(paramInt4 - 3);
        break label338;
        label611:
        paramInt4 = 0;
        break label408;
        label617:
        paramInt4 = 0;
        break label476;
        label623:
        if (!oD()) {
          this.bco = 3;
        }
      }
      label638:
      if (bool2) {
        oz();
      }
      for (this.bco = 2;; this.bco = 4) {
        do
        {
          oL();
          break;
        } while ((4 != paramInt4) || (!((com.android.inputmethod.latin.settings.f)localObject2).cN(paramInt1)));
      }
    }
    if (4 == paramInt4)
    {
      if ((((com.android.inputmethod.latin.settings.f)localObject1).bjj) && (this.bcQ.pj()) && (this.bcQ.bfw)) {
        com.android.inputmethod.latin.utils.q.a("", this.bcQ.bft.toString(), " ", this.bcQ);
      }
      if (this.bcQ.pk()) {
        cf(this.bcT);
      }
    }
    else
    {
      localObject1 = this.aJH.getKeyboard();
      if (localObject1 == null) {
        break label896;
      }
      if (!((com.android.inputmethod.keyboard.l)localObject1).aLK) {
        break label890;
      }
      if ((((com.android.inputmethod.keyboard.l)localObject1).aLt.aLN != 0) && (((com.android.inputmethod.keyboard.l)localObject1).aLt.aLN != 2)) {
        break label884;
      }
      i = 1;
      label805:
      if ((i == 0) && (!Character.isLetter(paramInt1))) {
        break label890;
      }
      i = 1;
      label820:
      if (i == 0) {
        break label896;
      }
    }
    for (;;)
    {
      bool1 = this.bcQ.pj();
      localObject1 = this.bcq.baj;
      if ((4 != paramInt4) || (((com.android.inputmethod.latin.settings.f)localObject1).cK(paramInt1))) {
        break label907;
      }
      if (!bool1) {
        break label903;
      }
      throw new RuntimeException("Should not be composing here");
      ax("");
      break;
      label884:
      i = 0;
      break label805;
      label890:
      i = 0;
      break label820;
      label896:
      paramInt2 = -1;
      paramInt3 = -1;
    }
    label903:
    oM();
    label907:
    if (this.bcQ.pk())
    {
      cf(this.bcT);
      bool1 = false;
    }
    boolean bool2 = bool1;
    if (!bool1)
    {
      bool2 = bool1;
      if (((com.android.inputmethod.latin.settings.f)localObject1).cL(paramInt1))
      {
        bool2 = bool1;
        if (((com.android.inputmethod.latin.settings.f)localObject1).cH(this.bdb)) {
          if (this.bcR.a((com.android.inputmethod.latin.settings.f)localObject1))
          {
            bool2 = bool1;
            if (((com.android.inputmethod.latin.settings.f)localObject1).biK) {}
          }
          else
          {
            if ((39 == paramInt1) || (45 == paramInt1)) {
              break label1182;
            }
            bool1 = true;
            label1005:
            av(false);
            bool2 = bool1;
          }
        }
      }
    }
    if (bool2)
    {
      if (paramInt2 >= 0)
      {
        paramInt4 = 1;
        label1026:
        if (paramInt4 == 0) {
          break label1200;
        }
        if (paramInt3 < 0) {
          break label1194;
        }
        paramInt4 = 1;
        label1038:
        if (paramInt4 == 0) {
          break label1200;
        }
        localObject2 = this.aJH.aMW.getKeyDetector();
        i = ((k)localObject2).aLr + paramInt2;
        paramInt4 = ((k)localObject2).aLs + paramInt3;
      }
      for (;;)
      {
        this.bcQ.r(paramInt1, i, paramInt4);
        if (this.bcQ.bfB == 1) {
          this.bcQ.bcg = oy();
        }
        this.bcR.h(aC(oC()));
        this.bcp.delete(0, this.bcp.length());
        this.bcp.appendCodePoint(paramInt1);
        this.bdh.oP();
        if (((com.android.inputmethod.latin.settings.f)localObject1).bjj)
        {
          char c = (char)paramInt1;
          ah.qv().b(c, paramInt2, paramInt3);
        }
        bool1 = false;
        break;
        label1182:
        bool1 = false;
        break label1005;
        paramInt4 = 0;
        break label1026;
        label1194:
        paramInt4 = 0;
        break label1038;
        label1200:
        paramInt4 = paramInt3;
        i = paramInt2;
      }
    }
    if (-2 == paramInt2) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      bool1 = e(paramInt1, paramInt4, bool1);
      ci(paramInt1);
      if (bool1)
      {
        oz();
        this.bco = 3;
      }
      if (this.aMo == null) {
        break;
      }
      localObject2 = this.aMo;
      if (!((SuggestionStripView)localObject2).qd()) {
        break;
      }
      ((SuggestionStripView)localObject2).e(x.beZ);
      break;
    }
  }
  
  private boolean oA()
  {
    return (this.bdf != null) && (this.bdf.isShowing());
  }
  
  private boolean oB()
  {
    return (this.bct >= 0) && (this.bct < cb.c.bDf.length);
  }
  
  private String oC()
  {
    String str = this.bcQ.bft.toString();
    if (this.bct < 0) {
      return str;
    }
    return aB(str).toString();
  }
  
  private boolean oD()
  {
    if (this.aRT == null) {}
    while (this.bcq.baj.biG != this.aRT) {
      return false;
    }
    return true;
  }
  
  private boolean oE()
  {
    com.android.inputmethod.latin.settings.f localF = this.bcq.baj;
    if (this.aMo == null) {}
    do
    {
      return false;
      if (this.aMo.qd()) {
        return true;
      }
    } while ((localF == null) || (!localF.cI(this.bdb)));
    if (localF.bjb.bbP) {
      return true;
    }
    return localF.cH(this.bdb);
  }
  
  private void oH()
  {
    this.bdh.removeMessages(2);
    Object localObject = this.bcq.baj;
    if ((this.bcD == null) || (!((com.android.inputmethod.latin.settings.f)localObject).cH(this.bdb)))
    {
      oG();
      if (this.bcQ.pj()) {
        Log.w(TAG, "Called updateSuggestionsOrPredictions but suggestions were not requested!");
      }
    }
    do
    {
      return;
      if ((!this.bcQ.pj()) && (!((com.android.inputmethod.latin.settings.f)localObject).biU))
      {
        oL();
        return;
      }
      localObject = new com.android.inputmethod.latin.utils.c();
      a(new w.b()
      {
        public final void d(x paramAnonymousX)
        {
          this.bba.set(paramAnonymousX);
        }
      });
      localObject = (x)((com.android.inputmethod.latin.utils.c)localObject).a(null, 200L);
    } while (localObject == null);
    c((x)localObject);
  }
  
  private CharSequence oI()
  {
    int j = 0;
    Object localObject = this.bcD;
    if (localObject == null) {
      return null;
    }
    int i;
    if (((w)localObject).beT == null)
    {
      i = 0;
      localObject = this.bdr;
      if (localObject != null) {
        j = ((com.android.inputmethod.latin.suggestions.d)localObject).baI;
      }
      i = Math.max(i, j);
      if (i <= 0) {
        break label72;
      }
    }
    label72:
    for (localObject = this.bcR.ck(i / 2 * 3);; localObject = null)
    {
      return localObject;
      i = ((w)localObject).beT.baI;
      break;
    }
  }
  
  private void oL()
  {
    com.android.inputmethod.latin.settings.f localF = this.bcq.baj;
    if (localF.biU) {
      oG();
    }
    for (;;)
    {
      aw(false);
      oE();
      return;
      a(localF.biG, false);
    }
  }
  
  private void oM()
  {
    com.android.inputmethod.latin.settings.f localF = this.bcq.baj;
    if ((localF.bjb.bbQ) && (localF.biK) && (!z.i(this.bcR.bej))) {
      ci(32);
    }
  }
  
  private void or()
  {
    InputMethodSubtype localInputMethodSubtype = this.bcG.a(null);
    if (localInputMethodSubtype != null)
    {
      com.emoji.network.beans.f localF = new com.emoji.network.beans.f();
      localF.setE("状态");
      localF.setF("输入法");
      localF.setG("变化");
      localF.setH(new f.b("输入法语言", this.bdo.toString(), localInputMethodSubtype.getLocale()));
      a(localF);
      this.bdo = com.android.inputmethod.dictionarypack.b.V(localInputMethodSubtype.getLocale());
      com.emoji.network.d.e(this.bdo);
    }
  }
  
  private void os()
  {
    Object localObject1 = this.aMQ.pe();
    Object localObject2 = new o(getCurrentInputEditorInfo(), isFullscreenMode());
    this.bcq.a((Locale)localObject1, (o)localObject2);
    localObject1 = c.nW();
    ((c)localObject1).baj = this.bcq.baj;
    ((c)localObject1).bak = ((c)localObject1).nX();
    localObject2 = com.emoji.common.h.k(MainApp.axF, "KEY_SOUND", "");
    com.b localB = com.b.jg();
    int i;
    if (((String)localObject2).isEmpty())
    {
      i = 0;
      ((c)localObject1).bam = i;
      if (!this.bdh.hasMessages(5)) {
        if (this.bcD != null) {
          break label135;
        }
      }
    }
    label135:
    for (localObject1 = null;; localObject1 = this.bcD.beQ)
    {
      a((g)localObject1);
      return;
      i = ((Integer)localB.axT.get(localObject2)).intValue();
      break;
    }
  }
  
  private void ot()
  {
    Object localObject2 = this.aMQ.pe();
    Object localObject3 = ((Locale)localObject2).toString();
    Object localObject1 = localObject3;
    if (TextUtils.isEmpty((CharSequence)localObject3))
    {
      Log.e(TAG, "System is reporting no current subtype.");
      localObject2 = getResources().getConfiguration().locale;
      localObject1 = ((Locale)localObject2).toString();
    }
    localObject3 = new w(this, (Locale)localObject2, this);
    com.android.inputmethod.latin.settings.f localF = this.bcq.baj;
    if (localF.bjg) {
      ((w)localObject3).beS = localF.beS;
    }
    this.bcJ = j.c(this, (Locale)localObject2);
    this.bcK = new aa(this, (String)localObject1);
    this.bcO = this.bcK.isEnabled();
    ((w)localObject3).a("user", this.bcK);
    localObject2 = PreferenceManager.getDefaultSharedPreferences(this);
    this.bcL = com.android.inputmethod.latin.personalization.d.a(this, (String)localObject1, (SharedPreferences)localObject2);
    ((w)localObject3).a("history", this.bcL);
    this.bcN = com.android.inputmethod.latin.personalization.d.l(this, (String)localObject1);
    ((w)localObject3).a("personalization", this.bcN);
    this.bcM = com.android.inputmethod.latin.personalization.d.b(this, (String)localObject1, (SharedPreferences)localObject2);
    ((w)localObject3).a("personalization_prediction_in_java", this.bcM);
    localObject2 = this.bcD;
    if (localObject2 != null) {}
    for (localObject1 = ((w)localObject2).beQ;; localObject1 = null)
    {
      a((g)localObject1);
      this.bcD = ((w)localObject3);
      if (localObject2 != null) {
        ((w)localObject2).close();
      }
      return;
    }
  }
  
  private void ov()
  {
    CharSequence localCharSequence = this.bcR.ck(1024);
    if (localCharSequence == null)
    {
      this.bcU = -1;
      this.bcT = -1;
    }
    do
    {
      int i;
      do
      {
        return;
        i = localCharSequence.length();
      } while ((i <= this.bcT) && ((i >= 1024) || (this.bcT >= 1024)));
      this.bcT = i;
    } while (this.bcT <= this.bcU);
    this.bcU = this.bcT;
  }
  
  private int oy()
  {
    int i = this.aJH.lV();
    if (i != 5) {
      return i;
    }
    i = ow();
    if ((i & 0x1000) != 0) {
      return 7;
    }
    if (i != 0) {
      return 5;
    }
    return 0;
  }
  
  private void oz()
  {
    Object localObject = this.bcR.ck(2);
    if ((localObject != null) && (((CharSequence)localObject).length() == 2) && (((CharSequence)localObject).charAt(0) == ' '))
    {
      this.bcR.cl(2);
      localObject = ((CharSequence)localObject).charAt(1) + " ";
      this.bcR.a((CharSequence)localObject, 1);
      this.aJH.lE();
    }
  }
  
  public static boolean q(long paramLong)
  {
    return System.currentTimeMillis() - paramLong > 86400000L;
  }
  
  public final void I(Context paramContext)
  {
    if (this.aJH == null) {}
    MainKeyboardView localMainKeyboardView;
    InputView localInputView;
    do
    {
      do
      {
        return;
        localMainKeyboardView = this.aJH.aMW;
      } while (localMainKeyboardView == null);
      localInputView = this.aJH.aMS;
    } while (localInputView == null);
    if (com.emoji.common.h.a(paramContext, "night_mode_key_switcher", Boolean.valueOf(false)).booleanValue())
    {
      int i = NightModeFragment.H(paramContext, 0);
      localMainKeyboardView.setNightModeColor(i);
      localInputView.setNightModeColor(i);
      return;
    }
    localMainKeyboardView.aOz.setNightModeColor(0);
    localInputView.setNightModeColor(0);
  }
  
  public final void X(String paramString)
  {
    this.bcR.beginBatchEdit();
    String str;
    if (this.bcQ.pj())
    {
      aE(paramString);
      if (!this.aJH.lR()) {
        this.bdh.oP();
      }
      str = paramString;
      if (paramString.length() > 1)
      {
        str = paramString;
        if (paramString.charAt(0) == '.')
        {
          if (Character.isLetter(paramString.charAt(1))) {
            break label157;
          }
          str = paramString;
        }
      }
    }
    for (;;)
    {
      if (4 == this.bco) {
        oM();
      }
      this.bcR.a(str, 1);
      this.bcR.endBatchEdit();
      this.bco = 0;
      this.aJH.lE();
      this.aJH.bI(-4);
      this.bdd = str;
      this.bdt += 1L;
      a(oI(), null, false);
      return;
      av(true);
      break;
      label157:
      this.bco = 0;
      CharSequence localCharSequence = this.bcR.ck(1);
      str = paramString;
      if (localCharSequence != null)
      {
        str = paramString;
        if (localCharSequence.length() == 1)
        {
          str = paramString;
          if (localCharSequence.charAt(0) == '.') {
            str = paramString.substring(1);
          }
        }
      }
    }
  }
  
  public final com.android.inputmethod.latin.suggestions.gifpredict.a a(LatinIME paramLatinIME, ch.a paramA, int paramInt, com.android.inputmethod.latin.suggestions.gifpredict.b paramB, String paramString)
  {
    ar.a localA = this.bdn;
    if (localA != null) {
      return localA.a(paramLatinIME, paramA, paramInt, paramB, paramString);
    }
    return null;
  }
  
  public final void a(int paramInt, x.a paramA)
  {
    int i = 1;
    this.bdx = paramInt;
    Object localObject1 = paramA.bfh;
    if ((((String)localObject1).length() == 1) && (oD())) {
      i(((String)localObject1).charAt(0), -2, -2);
    }
    Object localObject2;
    do
    {
      return;
      localObject2 = this.bcQ.bft.toString();
      this.bcR.beginBatchEdit();
      Object localObject3 = this.bcq.baj;
      if ((4 == this.bco) && (((String)localObject1).length() > 0) && (!this.bcQ.bfw))
      {
        int j = Character.codePointAt((CharSequence)localObject1, 0);
        if ((!((com.android.inputmethod.latin.settings.f)localObject3).cJ(j)) || (((com.android.inputmethod.latin.settings.f)localObject3).cM(j))) {
          oM();
        }
      }
      if ((((com.android.inputmethod.latin.settings.f)localObject3).bjb.bbP) && (this.bcE != null) && (paramInt >= 0) && (paramInt < this.bcE.length))
      {
        this.aRT = x.beZ;
        if (this.aMo != null) {
          this.aMo.e(x.beZ);
        }
        this.aJH.lE();
        av(true);
        localObject2 = this.bcE[paramInt];
        localObject3 = this.bcR;
        localObject1 = ((CompletionInfo)localObject2).getText();
        paramA = (x.a)localObject1;
        if (localObject1 == null) {
          paramA = "";
        }
        ((t)localObject3).bej.append(paramA);
        paramInt = ((t)localObject3).bei;
        ((t)localObject3).bei = (paramA.length() - ((t)localObject3).bek.length() + paramInt);
        ((t)localObject3).bek.setLength(0);
        if (((t)localObject3).bep != null) {
          ((t)localObject3).bep.commitCompletion((CompletionInfo)localObject2);
        }
        this.bcR.endBatchEdit();
        return;
      }
      this.bcV = true;
      b((String)localObject1, 1, "");
      this.bcR.endBatchEdit();
      this.bcP.mActive = false;
      this.bco = 4;
      this.aJH.lE();
      Object localObject4 = this.bcD;
      if (((paramA.bfj == 0) || (10 == paramA.bfj)) && (localObject4 != null) && (!com.android.inputmethod.latin.utils.d.a((w)localObject4, (String)localObject1, true))) {}
      for (paramInt = i;; paramInt = 0)
      {
        if (((com.android.inputmethod.latin.settings.f)localObject3).bjj) {
          com.android.inputmethod.latin.utils.q.s(32, -1, -1);
        }
        if ((paramInt == 0) || (!this.bcO)) {
          break;
        }
        paramA = this.aMo;
        localObject2 = ((com.android.inputmethod.latin.settings.f)localObject3).biJ;
        paramA.blJ.removeAllViews();
        paramA.blM.mO();
        paramA.aRT = x.beZ;
        localObject3 = paramA.blP;
        localObject4 = paramA.blJ;
        paramInt = paramA.getWidth() - ((com.android.inputmethod.latin.suggestions.f)localObject3).mDividerWidth - ((com.android.inputmethod.latin.suggestions.f)localObject3).bld * 2;
        TextView localTextView = ((com.android.inputmethod.latin.suggestions.f)localObject3).blC;
        localTextView.setTextColor(((com.android.inputmethod.latin.suggestions.f)localObject3).blq);
        i = (int)(paramInt * ((com.android.inputmethod.latin.suggestions.f)localObject3).blu);
        Object localObject5 = com.android.inputmethod.latin.suggestions.f.b((CharSequence)localObject1, i, localTextView.getPaint());
        float f = localTextView.getTextScaleX();
        localTextView.setTag(localObject1);
        localTextView.setText((CharSequence)localObject5);
        localTextView.setTextScaleX(f);
        ((ViewGroup)localObject4).addView(localTextView);
        com.android.inputmethod.latin.suggestions.f.a(localTextView, ((com.android.inputmethod.latin.suggestions.f)localObject3).blu, -1);
        localObject1 = (View)((com.android.inputmethod.latin.suggestions.f)localObject3).bln.get(0);
        ((ViewGroup)localObject4).addView((View)localObject1);
        ((LinearLayout.LayoutParams)((View)localObject1).getLayoutParams()).gravity = 17;
        localObject1 = ((com.android.inputmethod.latin.suggestions.f)localObject3).blD;
        ((TextView)localObject1).setTextColor(((com.android.inputmethod.latin.suggestions.f)localObject3).blr);
        ((TextView)localObject1).setText("←");
        ((ViewGroup)localObject4).addView((View)localObject1);
        localObject5 = ((com.android.inputmethod.latin.suggestions.f)localObject3).blE;
        ((TextView)localObject5).setGravity(19);
        ((TextView)localObject5).setTextColor(((com.android.inputmethod.latin.suggestions.f)localObject3).blr);
        f = com.android.inputmethod.latin.suggestions.f.a((CharSequence)localObject2, paramInt - i - ((TextView)localObject1).getWidth(), ((TextView)localObject5).getPaint());
        ((TextView)localObject5).setText((CharSequence)localObject2);
        ((TextView)localObject5).setTextScaleX(f);
        ((ViewGroup)localObject4).addView((View)localObject5);
        com.android.inputmethod.latin.suggestions.f.a((View)localObject5, 1.0F - ((com.android.inputmethod.latin.suggestions.f)localObject3).blu, -1);
        localTextView.setOnClickListener(paramA);
        ((TextView)localObject1).setOnClickListener(paramA);
        ((TextView)localObject5).setOnClickListener(paramA);
        return;
      }
      if (!paramA.bfg) {
        break;
      }
      localObject1 = this.aJH;
      paramA = paramA.bfh;
      if (((KeyboardSwitcher)localObject1).aNj)
      {
        localObject3 = ce.d.uf();
        if (localObject3 != null) {
          ((ce.d)localObject3).n(((KeyboardSwitcher)localObject1).aIX, paramA, null);
        }
      }
    } while (TextUtils.isEmpty((CharSequence)localObject2));
    this.bdh.oP();
  }
  
  public final void a(PackageInfo paramPackageInfo)
  {
    this.bcF.b(paramPackageInfo);
  }
  
  public final void a(p paramP)
  {
    Object localObject;
    int i;
    if (this.bcq.baj.biZ)
    {
      localObject = this.aRT;
      if (((x)localObject).bff.size() <= 0) {
        break label200;
      }
      localObject = (x.a)((x)localObject).bff.get(0);
      if ((1 != ((x.a)localObject).bfj) || (-1 == ((x.a)localObject).bfm)) {
        break label195;
      }
      i = 1;
      if (i == 0) {
        break label200;
      }
    }
    for (;;)
    {
      if ((localObject != null) && (((x.a)localObject).bfl.shouldAutoCommit((x.a)localObject)))
      {
        String[] arrayOfString = ((x.a)localObject).bfh.split(" ", 2);
        i = ((x.a)localObject).bfm;
        paramP.aSf.de(i);
        paramP.aSg.de(i);
        paramP.bbT.de(i);
        paramP.bbU.de(i);
        oM();
        this.bcR.a(arrayOfString[0], 0);
        this.bco = 4;
        this.aJH.lE();
        this.bcQ.bcg = oy();
      }
      localObject = this.bdi;
      if (!((b)localObject).mHandler.hasMessages(1)) {
        ((b)localObject).mHandler.obtainMessage(1, paramP).sendToTarget();
      }
      return;
      label195:
      i = 0;
      break;
      label200:
      localObject = null;
    }
  }
  
  public final void a(x paramX, boolean paramBoolean)
  {
    this.aRT = paramX;
    if (this.aMo != null)
    {
      this.aMo.setSuggestions(paramX);
      paramX = this.aJH;
      if (paramX.aMZ != paramBoolean)
      {
        paramX.aMZ = paramBoolean;
        if (paramX.aMW != null) {
          paramX.aMW.ah(paramBoolean);
        }
      }
    }
  }
  
  public final void a(com.emoji.network.beans.f paramF)
  {
    if (!this.bcl) {
      return;
    }
    MainApp.axF.a(paramF, false);
  }
  
  protected final void a(CharSequence paramCharSequence, x paramX, boolean paramBoolean)
  {
    if (!this.bcs) {}
    int i;
    label21:
    Object localObject;
    label54:
    com.android.inputmethod.latin.suggestions.d localD;
    boolean bool;
    do
    {
      do
      {
        do
        {
          return;
          if (this.aJH.aMn == null) {
            break;
          }
          i = 1;
        } while (i != 0);
        localObject = this.aJH;
        if (((KeyboardSwitcher)localObject).aMD == null) {
          break label352;
        }
        if (((KeyboardSwitcher)localObject).aMD.getVisibility() != 0) {
          break label346;
        }
        i = 1;
      } while (i != 0);
      localD = this.bdr;
      bool = TextUtils.isEmpty(paramCharSequence);
    } while (localD == null);
    if ((paramX != null) && (paramX.bff.size() > 0)) {
      if ((!bool) && (paramX.cn(0).bfj == 1))
      {
        localObject = new StringBuilder().append(paramCharSequence);
        if (paramCharSequence.charAt(paramCharSequence.length() - 1) == ' ')
        {
          paramCharSequence = "";
          label143:
          paramCharSequence = paramCharSequence + paramX.cm(0);
          label161:
          paramBoolean = false;
        }
      }
    }
    for (;;)
    {
      String str2;
      if ((paramX != null) && (paramX.bff.size() > 2))
      {
        localObject = paramX.cn(2);
        if (((x.a)localObject).bfg)
        {
          localObject = ((x.a)localObject).bfh;
          str2 = paramX.cn(0).bfh;
        }
      }
      for (paramX = (x)localObject;; paramX = null)
      {
        if ((!TextUtils.isEmpty(this.bcR.oV())) || ((paramBoolean) && (!this.bcQ.pj()))) {
          break label426;
        }
        i = 0;
        label243:
        if (i >= 2) {
          break;
        }
        label272:
        label275:
        String str1;
        switch (i)
        {
        default: 
          localObject = null;
          if ((this.aMo != null) && (!TextUtils.isEmpty((CharSequence)localObject)))
          {
            if (i != 1) {
              break label414;
            }
            if (str2 == null) {
              break label408;
            }
            str1 = str2.toString();
          }
          break;
        }
        while (!localD.a(this.aMo, ((CharSequence)localObject).toString(), str1))
        {
          i += 1;
          break label243;
          i = 0;
          break label21;
          label346:
          i = 0;
          break label54;
          label352:
          i = 0;
          break label54;
          paramCharSequence = " ";
          break label143;
          if (!bool) {
            break label161;
          }
          paramCharSequence = paramX.cm(0);
          break label161;
          localObject = paramCharSequence;
          break label275;
          if (!TextUtils.equals(this.bcQ.bft.toString(), paramCharSequence)) {
            break label272;
          }
          localObject = paramX;
          break label275;
          label408:
          str1 = null;
          continue;
          label414:
          str1 = null;
        }
        str2 = null;
      }
      label426:
      break;
    }
  }
  
  public final boolean a(Dialog paramDialog)
  {
    if (paramDialog == null) {}
    Window localWindow;
    WindowManager.LayoutParams localLayoutParams;
    do
    {
      do
      {
        do
        {
          return false;
        } while (this.bcB == null);
        localWindow = paramDialog.getWindow();
      } while (localWindow == null);
      localLayoutParams = localWindow.getAttributes();
    } while (localLayoutParams == null);
    localLayoutParams.token = this.bcB.getWindowToken();
    localLayoutParams.type = 1003;
    localWindow.setAttributes(localLayoutParams);
    localWindow.addFlags(131072);
    paramDialog.show();
    return true;
  }
  
  public final boolean a(EditorInfo paramEditorInfo, String paramString)
  {
    if (paramEditorInfo == null) {}
    for (;;)
    {
      return false;
      if ((getCurrentInputConnection() != null) && (e(paramEditorInfo)))
      {
        paramEditorInfo = am.a.a(paramEditorInfo);
        int j = paramEditorInfo.length;
        int i = 0;
        while (i < j)
        {
          if (ClipDescription.compareMimeTypes(paramString, paramEditorInfo[i])) {
            return true;
          }
          i += 1;
        }
      }
    }
  }
  
  public final boolean a(cl.b paramB)
  {
    return false;
  }
  
  public final InputMethodSubtype aA(String paramString)
  {
    Object localObject2 = this.bcG.ax(true);
    Object localObject1 = null;
    Iterator localIterator = ((List)localObject2).iterator();
    if (localIterator.hasNext())
    {
      localObject2 = (InputMethodSubtype)localIterator.next();
      if (TextUtils.equals(paramString, ((InputMethodSubtype)localObject2).getLocale())) {
        return localObject2;
      }
      if (!((InputMethodSubtype)localObject2).getLocale().startsWith(paramString)) {
        break label71;
      }
      localObject1 = localObject2;
    }
    label71:
    for (;;)
    {
      break;
      return localObject1;
    }
  }
  
  public final void aF(String paramString)
  {
    if (this.bcR == null) {
      return;
    }
    X(paramString);
  }
  
  public final void ad(int paramInt1, int paramInt2)
  {
    int i = 1;
    MainKeyboardView localMainKeyboardView = this.aJH.aMW;
    boolean bool;
    if (localMainKeyboardView != null) {
      if (localMainKeyboardView.mB())
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
      bool = s.mS();
      break;
      if (paramInt2 > 0) {
        if (paramInt1 == -5) {
          if (this.bcR.bei <= 0) {
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
        c localC = c.nW();
        if (paramInt2 == 0) {
          localC.aU(localMainKeyboardView);
        }
        localC.cc(paramInt1);
        return;
        i = 0;
      }
    }
  }
  
  public final void au(boolean paramBoolean)
  {
    this.bcJ = paramBoolean;
    MainKeyboardView localMainKeyboardView = this.aJH.aMW;
    if (localMainKeyboardView != null) {
      localMainKeyboardView.setMainDictionaryAvailability(paramBoolean);
    }
  }
  
  public final void ay(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    int i = this.bcP.bcg;
    if ((5 == i) || (7 == i)) {}
    for (i = 1;; i = 0)
    {
      String str = paramString;
      if (i != 0) {
        str = paramString.toLowerCase(this.aMQ.pe());
      }
      if (this.bcK != null) {
        this.bcK.ay(str);
      }
      Log.i(TAG, "addWordToUserDictionary: ");
      this.aJH.af(true);
      return;
    }
  }
  
  public final void az(String paramString)
  {
    paramString = paramString.replace("-", "_");
    IBinder localIBinder;
    if (aA(paramString) != null)
    {
      localIBinder = getWindowToken();
      if (localIBinder != null) {}
    }
    else
    {
      return;
    }
    this.bcG.a(localIBinder, aA(paramString));
  }
  
  public final void b(p paramP)
  {
    b localB = this.bdi;
    Object localObject = localB.mLock;
    if (localObject != null) {
      try
      {
        localB.a(paramP, new LatinIME.b.2(localB));
        return;
      }
      finally {}
    }
  }
  
  public final void b(x paramX)
  {
    if (paramX.bff.isEmpty()) {}
    for (Object localObject = null; TextUtils.isEmpty((CharSequence)localObject); localObject = paramX.cm(0)) {
      return;
    }
    this.bcR.beginBatchEdit();
    if (4 == this.bco) {
      oM();
    }
    if (this.bcq.baj.biZ)
    {
      int j = ((String)localObject).lastIndexOf(' ') + 1;
      if (j != 0)
      {
        this.bcR.a(((String)localObject).substring(0, j), 1);
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        while (i < paramX.bff.size())
        {
          x.a localA = (x.a)paramX.bff.get(i);
          int k = localA.bfh.lastIndexOf(' ');
          localArrayList.add(new x.a(localA.bfh.substring(k + 1), localA.bfi, localA.bfj, localA.bfl, -1, -1));
          i += 1;
        }
        c(new x(localArrayList, paramX.bfa, paramX.bfb, paramX.bfc, paramX.bfd, paramX.bfe));
      }
      paramX = ((String)localObject).substring(j);
      this.bcQ.aJ(paramX);
      this.bcR.h(aB(paramX));
    }
    for (;;)
    {
      this.bcV = true;
      this.bcR.endBatchEdit();
      this.bco = 4;
      this.aJH.lE();
      a(oI(), null, false);
      return;
      this.bcQ.aJ((String)localObject);
      this.bcR.h((CharSequence)localObject);
    }
  }
  
  public final void c(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int j = 1;
    this.aJH.aNq = paramInt1;
    Object localObject = this.aJH;
    LatinIME localLatinIME = ((KeyboardSwitcher)localObject).aIX;
    int i;
    if (localLatinIME != null)
    {
      localObject = ((KeyboardSwitcher)localObject).aMX;
      i = localLatinIME.ow();
      if (paramInt1 != -1) {
        ((com.android.inputmethod.keyboard.internal.u)localObject).aVB.lP();
      }
      if (paramInt1 != -1) {
        break label270;
      }
      if (-1 == ((com.android.inputmethod.keyboard.internal.u)localObject).aVL)
      {
        if (!((com.android.inputmethod.keyboard.internal.u)localObject).aVF) {
          break label248;
        }
        ((com.android.inputmethod.keyboard.internal.u)localObject).aVN = ((com.android.inputmethod.keyboard.internal.u)localObject).aVB.lQ();
        if (!((com.android.inputmethod.keyboard.internal.u)localObject).aVN) {
          ((com.android.inputmethod.keyboard.internal.u)localObject).aVB.lO();
        }
        if (!((com.android.inputmethod.keyboard.internal.u)localObject).aVN) {
          break label153;
        }
        if ((((com.android.inputmethod.keyboard.internal.u)localObject).aVH.nn()) || (((com.android.inputmethod.keyboard.internal.u)localObject).aVM)) {
          ((com.android.inputmethod.keyboard.internal.u)localObject).am(true);
        }
      }
    }
    label153:
    label248:
    label270:
    label399:
    label403:
    for (;;)
    {
      ad(paramInt1, paramInt2);
      return;
      if (((com.android.inputmethod.keyboard.internal.u)localObject).aVH.nk())
      {
        ((com.android.inputmethod.keyboard.internal.u)localObject).bW(3);
        ((com.android.inputmethod.keyboard.internal.u)localObject).aVC.nA();
      }
      else if (((com.android.inputmethod.keyboard.internal.u)localObject).aVH.nm())
      {
        ((com.android.inputmethod.keyboard.internal.u)localObject).bW(1);
        ((com.android.inputmethod.keyboard.internal.u)localObject).aVC.nA();
      }
      else if (((com.android.inputmethod.keyboard.internal.u)localObject).aVH.nj())
      {
        ((com.android.inputmethod.keyboard.internal.u)localObject).aVC.nI();
      }
      else
      {
        ((com.android.inputmethod.keyboard.internal.u)localObject).bW(1);
        ((com.android.inputmethod.keyboard.internal.u)localObject).aVC.nA();
        continue;
        ((com.android.inputmethod.keyboard.internal.u)localObject).ny();
        ((com.android.inputmethod.keyboard.internal.u)localObject).aVE = 4;
        ((com.android.inputmethod.keyboard.internal.u)localObject).aVC.nA();
        continue;
        if (paramInt1 != -2) {
          if (paramInt1 == -3)
          {
            ((com.android.inputmethod.keyboard.internal.u)localObject).nx();
            ((com.android.inputmethod.keyboard.internal.u)localObject).aVD.nA();
            ((com.android.inputmethod.keyboard.internal.u)localObject).aVE = 3;
          }
          else
          {
            ((com.android.inputmethod.keyboard.internal.u)localObject).aVC.nB();
            ((com.android.inputmethod.keyboard.internal.u)localObject).aVD.nB();
            if ((!paramBoolean) && (((com.android.inputmethod.keyboard.internal.u)localObject).aVF) && (i != 4096))
            {
              i = j;
              if (!((com.android.inputmethod.keyboard.internal.u)localObject).aVH.nm()) {
                if ((!((com.android.inputmethod.keyboard.internal.u)localObject).aVH.nn()) || (!((com.android.inputmethod.keyboard.internal.u)localObject).aVC.nD())) {
                  break label399;
                }
              }
              for (i = j;; i = 0)
              {
                if (i == 0) {
                  break label403;
                }
                ((com.android.inputmethod.keyboard.internal.u)localObject).aVB.lF();
                break;
              }
            }
          }
        }
      }
    }
  }
  
  public final void c(x paramX, String paramString)
  {
    this.bde = false;
    d localD = this.bdh;
    localD.removeMessages(3);
    localD.obtainMessage(3, 0, 1, new Pair(paramX, paramString)).sendToTarget();
  }
  
  protected void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramFileDescriptor = new PrintWriterPrinter(paramPrintWriter);
    paramFileDescriptor.println("LatinIME state :");
    paramPrintWriter = this.aJH.getKeyboard();
    if (paramPrintWriter != null) {}
    for (int i = paramPrintWriter.aLt.LF;; i = -1)
    {
      paramFileDescriptor.println("  Keyboard mode = " + i);
      paramPrintWriter = this.bcq.baj;
      paramFileDescriptor.println("  mIsSuggestionsSuggestionsRequested = " + paramPrintWriter.cH(this.bdb));
      paramFileDescriptor.println("  mCorrectionEnabled=" + paramPrintWriter.bjg);
      paramFileDescriptor.println("  isComposingWord=" + this.bcQ.pj());
      paramFileDescriptor.println("  mSoundOn=" + paramPrintWriter.bak);
      paramFileDescriptor.println("  mVibrateOn=" + paramPrintWriter.biM);
      paramFileDescriptor.println("  mKeyPreviewPopupOn=" + paramPrintWriter.biN);
      paramFileDescriptor.println("  inputAttributes=" + paramPrintWriter.bjb);
      return;
    }
  }
  
  public final boolean e(EditorInfo paramEditorInfo)
  {
    if (paramEditorInfo == null) {
      return false;
    }
    paramEditorInfo = paramEditorInfo.packageName;
    if (paramEditorInfo == null) {
      return false;
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return true;
    }
    Object localObject = getCurrentInputBinding();
    if (localObject == null)
    {
      Log.e(TAG, "inputBinding should not be null here. You are likely to be hitting b.android.com/225029");
      return false;
    }
    int i = ((InputBinding)localObject).getUid();
    if (Build.VERSION.SDK_INT >= 19)
    {
      localObject = (AppOpsManager)getSystemService("appops");
      try
      {
        ((AppOpsManager)localObject).checkPackage(i, paramEditorInfo);
        return true;
      }
      catch (Exception paramEditorInfo)
      {
        return false;
      }
    }
    localObject = getPackageManager().getPackagesForUid(i);
    if (localObject == null) {
      return false;
    }
    int j = localObject.length;
    i = 0;
    while (i < j)
    {
      if (paramEditorInfo.equals(localObject[i])) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public final void g(Runnable paramRunnable)
  {
    ar.a localA = this.bdn;
    if (localA != null)
    {
      ExecutorService localExecutorService = localA.pp();
      try
      {
        if (localA.bfL) {
          return;
        }
        if (localExecutorService != null) {
          localExecutorService.submit(paramRunnable);
        }
        return;
      }
      finally {}
    }
  }
  
  public InputConnection getCurrentInputConnection()
  {
    Object localObject = this.aJH;
    if (localObject != null)
    {
      if (((KeyboardSwitcher)localObject).aMD != null) {
        localObject = ((KeyboardSwitcher)localObject).aMD.getInputConnection();
      }
      while (localObject != null)
      {
        return localObject;
        localObject = ((KeyboardSwitcher)localObject).aMn;
        if (localObject != null) {
          localObject = ((com.android.inputmethod.keyboard.u)localObject).nf();
        } else {
          localObject = null;
        }
      }
    }
    return super.getCurrentInputConnection();
  }
  
  public EditorInfo getCurrentInputEditorInfo()
  {
    Object localObject = this.aJH;
    if (localObject != null)
    {
      if (((KeyboardSwitcher)localObject).aMD != null) {
        localObject = ((KeyboardSwitcher)localObject).aMD.getEditorInfo();
      }
      while (localObject != null)
      {
        return localObject;
        localObject = ((KeyboardSwitcher)localObject).aMn;
        if (localObject != null) {
          localObject = ((com.android.inputmethod.keyboard.u)localObject).ng();
        } else {
          localObject = null;
        }
      }
    }
    return super.getCurrentInputEditorInfo();
  }
  
  public final IBinder getWindowToken()
  {
    Object localObject = getWindow();
    if (localObject == null) {}
    do
    {
      do
      {
        return null;
        localObject = ((Dialog)localObject).getWindow();
      } while (localObject == null);
      localObject = ((Window)localObject).getAttributes();
    } while (localObject == null);
    return ((WindowManager.LayoutParams)localObject).token;
  }
  
  public final void h(int paramInt, boolean paramBoolean)
  {
    boolean bool = true;
    Object localObject1 = this.aJH.aMX;
    if (paramInt == -1) {
      if (-1 != ((com.android.inputmethod.keyboard.internal.u)localObject1).aVL)
      {
        ((com.android.inputmethod.keyboard.internal.u)localObject1).bX(((com.android.inputmethod.keyboard.internal.u)localObject1).aVL);
        ((com.android.inputmethod.keyboard.internal.u)localObject1).aVC.onRelease();
        label44:
        if (an.b.kr().isTouchExplorationEnabled()) {
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
        if (((com.android.inputmethod.keyboard.internal.u)localObject1).aVF)
        {
          bool = ((com.android.inputmethod.keyboard.internal.u)localObject1).aVH.nk();
          ((com.android.inputmethod.keyboard.internal.u)localObject1).aVM = false;
          if (((com.android.inputmethod.keyboard.internal.u)localObject1).aVN)
          {
            ((com.android.inputmethod.keyboard.internal.u)localObject1).aVN = false;
            break;
          }
          if (((com.android.inputmethod.keyboard.internal.u)localObject1).aVC.nE())
          {
            if (((com.android.inputmethod.keyboard.internal.u)localObject1).aVH.nl()) {
              ((com.android.inputmethod.keyboard.internal.u)localObject1).am(true);
            }
            for (;;)
            {
              ((com.android.inputmethod.keyboard.internal.u)localObject1).aVC.onRelease();
              ((com.android.inputmethod.keyboard.internal.u)localObject1).aVB.lN();
              break;
              ((com.android.inputmethod.keyboard.internal.u)localObject1).bW(0);
            }
          }
          if ((((com.android.inputmethod.keyboard.internal.u)localObject1).aVH.nl()) && (paramBoolean))
          {
            ((com.android.inputmethod.keyboard.internal.u)localObject1).am(true);
            break;
          }
          if ((((com.android.inputmethod.keyboard.internal.u)localObject1).aVH.nn()) && (paramBoolean))
          {
            ((com.android.inputmethod.keyboard.internal.u)localObject1).aVE = 5;
            break;
          }
          if ((bool) && (!((com.android.inputmethod.keyboard.internal.u)localObject1).aVH.nl()) && ((((com.android.inputmethod.keyboard.internal.u)localObject1).aVC.nC()) || (((com.android.inputmethod.keyboard.internal.u)localObject1).aVC.nJ())) && (!paramBoolean)) {
            break;
          }
          if ((bool) && (!((com.android.inputmethod.keyboard.internal.u)localObject1).aVC.nK()) && (!paramBoolean))
          {
            ((com.android.inputmethod.keyboard.internal.u)localObject1).am(false);
            break;
          }
          if ((((com.android.inputmethod.keyboard.internal.u)localObject1).aVH.nj()) && (((com.android.inputmethod.keyboard.internal.u)localObject1).aVC.nJ()) && (!paramBoolean))
          {
            ((com.android.inputmethod.keyboard.internal.u)localObject1).bW(0);
            ((com.android.inputmethod.keyboard.internal.u)localObject1).aVM = true;
            break;
          }
          if (((com.android.inputmethod.keyboard.internal.u)localObject1).aVH.mState == 2) {}
          for (int i = 1; (i != 0) && (((com.android.inputmethod.keyboard.internal.u)localObject1).aVC.nC()) && (!paramBoolean); i = 0)
          {
            ((com.android.inputmethod.keyboard.internal.u)localObject1).bW(0);
            ((com.android.inputmethod.keyboard.internal.u)localObject1).aVM = true;
            break;
          }
        }
        if (!((com.android.inputmethod.keyboard.internal.u)localObject1).aVC.nE()) {
          break;
        }
        ((com.android.inputmethod.keyboard.internal.u)localObject1).ny();
        break;
        if (paramInt == -2)
        {
          if (!((com.android.inputmethod.keyboard.internal.u)localObject1).aVH.nk()) {}
          for (paramBoolean = bool;; paramBoolean = false)
          {
            ((com.android.inputmethod.keyboard.internal.u)localObject1).am(paramBoolean);
            break;
          }
        }
        if (paramInt != -3) {
          break label44;
        }
        if (((com.android.inputmethod.keyboard.internal.u)localObject1).aVD.nE()) {
          ((com.android.inputmethod.keyboard.internal.u)localObject1).nx();
        }
        for (;;)
        {
          ((com.android.inputmethod.keyboard.internal.u)localObject1).aVD.onRelease();
          break;
          if (!paramBoolean) {
            ((com.android.inputmethod.keyboard.internal.u)localObject1).aVK = false;
          }
        }
        localObject2 = an.c.ks();
      } while (((an.c)localObject2).aHN == null);
      paramInt = ((an.c)localObject2).aHN.getKeyboard().aLt.aLN;
      localObject1 = ((an.c)localObject2).aHN.getContext();
      switch (paramInt)
      {
      case 5: 
      default: 
        localObject1 = ((Context)localObject1).getText(2131362006);
      }
      for (;;)
      {
        an.b.kr().b(((an.c)localObject2).aHN, (CharSequence)localObject1);
        return;
        localObject1 = ((Context)localObject1).getText(2131362005);
        continue;
        localObject1 = ((Context)localObject1).getText(2131362007);
      }
      localObject1 = an.c.ks();
    } while (((an.c)localObject1).aHN == null);
    Object localObject2 = ((an.c)localObject1).aHN.getKeyboard();
    Context localContext = ((an.c)localObject1).aHN.getContext();
    switch (((com.android.inputmethod.keyboard.l)localObject2).aLt.aLN)
    {
    default: 
      paramInt = -1;
    }
    while (paramInt >= 0)
    {
      localObject2 = localContext.getString(paramInt);
      an.b.kr().b(((an.c)localObject1).aHN, (CharSequence)localObject2);
      return;
      paramInt = 2131361996;
      continue;
      paramInt = 2131361999;
      continue;
      paramInt = 2131361997;
      continue;
      paramInt = 2131361998;
    }
  }
  
  public void hideWindow()
  {
    this.aJH.aMZ = false;
    if (an.b.kr().Gq.isEnabled())
    {
      an.c localC = an.c.ks();
      if (localC.aHN != null)
      {
        localC.R(localC.aHN.getContext().getString(2131361816));
        localC.aHR = -1;
      }
    }
    if ((this.bdf != null) && (this.bdf.isShowing()))
    {
      this.bdf.dismiss();
      this.bdf = null;
    }
    super.hideWindow();
  }
  
  public final void i(int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.bdr == null) {
      this.bdr = new com.android.inputmethod.latin.suggestions.d(this);
    }
    this.bdr.hide();
    this.aJH.aNq = paramInt1;
    long l = SystemClock.uptimeMillis();
    if ((paramInt1 != -5) || (l > this.bcX + 200L)) {
      this.bcW = 0;
    }
    this.bcX = l;
    this.bcR.beginBatchEdit();
    KeyboardSwitcher localKeyboardSwitcher = this.aJH;
    int i = this.bco;
    if (!this.bcQ.pj()) {
      this.bde = false;
    }
    if (paramInt1 != 32) {
      this.bdh.bdW = 0L;
    }
    Object localObject1 = null;
    boolean bool;
    switch (paramInt1)
    {
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
      bool = g(paramInt1, paramInt2, paramInt3, i);
    }
    for (;;)
    {
      if (this.bcQ.pj()) {
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          this.bcj.add(z.dg(paramInt1));
        }
      }
      label481:
      Object localObject3;
      label992:
      label1039:
      label1043:
      label1239:
      label1341:
      label1343:
      label1431:
      label1441:
      label1483:
      label1488:
      label1497:
      label1809:
      label2231:
      while (bool) {
        for (;;)
        {
          localKeyboardSwitcher.bI(paramInt1);
          if ((!bool) && (paramInt1 != -1) && (paramInt1 != -2) && (paramInt1 != -3)) {
            this.bcP.mActive = false;
          }
          if (-5 != paramInt1) {
            this.bdd = null;
          }
          this.bcR.endBatchEdit();
          this.bdt += 1L;
          cb.b.bCX.ap(this);
          do
          {
            return;
            this.bco = 0;
            if (this.bdr != null)
            {
              localObject1 = this.bdr;
              ((com.android.inputmethod.latin.suggestions.d)localObject1).bkI = null;
              ((com.android.inputmethod.latin.suggestions.d)localObject1).hide();
            }
            this.bcW += 1;
            this.bcV = true;
            localObject1 = this.bdh;
            ((d)localObject1).removeMessages(0);
            ((d)localObject1).sendMessageDelayed(((d)localObject1).obtainMessage(0), ((d)localObject1).bdU);
            if (this.bcQ.pk()) {
              cf(this.bcT);
            }
            if (this.bcQ.pj()) {
              if (this.bcQ.bfw)
              {
                localObject1 = this.bcQ.bft.toString();
                this.bcQ.reset();
                this.bcQ.bfx = ((String)localObject1);
                this.bcR.h(aC(oC()));
                this.bdh.oP();
                if (!this.bcQ.pj()) {
                  this.aJH.lE();
                }
              }
            }
            Object localObject4;
            for (;;)
            {
              localObject1 = "删除";
              bool = false;
              break;
              this.bcQ.pl();
              break label481;
              localObject1 = this.bcq.baj;
              localObject2 = this.bcP;
              if ((((q)localObject2).mActive) && (!TextUtils.isEmpty(((q)localObject2).bcd)) && (!TextUtils.equals(((q)localObject2).aHJ, ((q)localObject2).bcd))) {
                paramInt2 = 1;
              }
              while (paramInt2 != 0)
              {
                bool = ((com.android.inputmethod.latin.settings.f)localObject1).bjj;
                localObject1 = this.bcP.bcf;
                localObject2 = this.bcP.aHJ;
                localObject3 = this.bcP.bcd;
                paramInt2 = aB((String)localObject3).toString().length();
                paramInt3 = this.bcP.bce.length() + paramInt2;
                if (DEBUG)
                {
                  if (this.bcQ.pj())
                  {
                    throw new RuntimeException("revertCommit, but we are composing a word");
                    paramInt2 = 0;
                    continue;
                  }
                  localObject4 = this.bcR.ck(paramInt3).subSequence(0, paramInt2);
                  if (!TextUtils.equals((CharSequence)localObject3, (CharSequence)localObject4)) {
                    throw new RuntimeException("revertCommit check failed: we thought we were reverting \"" + (String)localObject3 + "\", but before the cursor we found \"" + localObject4 + "\"");
                  }
                }
                this.bcR.cl(paramInt3);
                if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (!TextUtils.isEmpty((CharSequence)localObject3)) && (this.bcL != null)) {
                  this.bcL.p((String)localObject1, (String)localObject3);
                }
                localObject1 = (String)localObject2 + this.bcP.bce;
                if (this.bcq.baj.biK) {
                  this.bcR.a(aB((String)localObject1), 1);
                }
                for (;;)
                {
                  if (this.bcq.baj.bjj) {
                    com.android.inputmethod.latin.utils.q.c(this.bcP.bce, -1, -1);
                  }
                  this.bcP = q.bci;
                  this.bdh.oP();
                  break;
                  this.bcQ.a((CharSequence)localObject1, this.aJH.getKeyboard());
                  this.bcR.h(aB((String)localObject1));
                }
              }
              if (this.bdd == null) {
                break label992;
              }
              localObject2 = this.bcR;
              localObject3 = this.bdd;
              if (!TextUtils.equals((CharSequence)localObject3, ((t)localObject2).ck(((CharSequence)localObject3).length()))) {
                break label992;
              }
              this.bcR.cl(this.bdd.length());
              this.bdd = null;
            }
            if (1 == i)
            {
              this.bdh.bdW = 0L;
              localObject2 = this.bcR;
              if (!TextUtils.equals(". ", ((t)localObject2).ck(2)))
              {
                Log.d(t.TAG, "Tried to revert double-space combo but we didn't find \". \" just before the cursor.");
                paramInt2 = 0;
                if (paramInt2 != 0) {
                  break label1239;
                }
                if (this.bcT == this.bcU) {
                  break label1343;
                }
                paramInt2 = this.bcU;
                paramInt3 = this.bcT;
                this.bcR.setSelection(this.bcU, this.bcU);
                this.bcU = this.bcT;
                this.bcR.cl(paramInt2 - paramInt3);
              }
            }
            do
            {
              if ((((com.android.inputmethod.latin.settings.f)localObject1).cH(this.bdb)) && (((com.android.inputmethod.latin.settings.f)localObject1).biK))
              {
                localObject1 = this.bcR.b(this.bcq.baj);
                if (localObject1 != null)
                {
                  localObject1 = ((CharSequence)localObject1).toString();
                  this.bcQ.a((CharSequence)localObject1, this.aJH.getKeyboard());
                  paramInt2 = ((String)localObject1).length();
                  this.bcR.cl(paramInt2);
                  this.bcR.h(aB((String)localObject1));
                  this.bdh.oP();
                }
              }
              this.aJH.lE();
              if (this.bcQ.pj()) {
                break;
              }
              oG();
              break;
              ((t)localObject2).cl(2);
              ((t)localObject2).a(" ", 1);
              paramInt2 = 1;
              break label1039;
              break;
              if (2 != i) {
                break label1043;
              }
              localObject2 = this.bcR;
              localObject3 = ((t)localObject2).ck(2);
              if ((TextUtils.isEmpty((CharSequence)localObject3)) || (' ' != ((CharSequence)localObject3).charAt(1))) {
                Log.d(t.TAG, "Tried to revert a swap of punctuation but we didn't find a space just before the cursor.");
              }
              for (paramInt2 = 0;; paramInt2 = 1)
              {
                if (paramInt2 != 0) {
                  break label1341;
                }
                break;
                ((t)localObject2).cl(2);
                ((t)localObject2).a(" " + ((CharSequence)localObject3).subSequence(0, 1), 1);
              }
              break;
              if (-1 == this.bcU) {
                Log.e(TAG, "Backspace when we don't know the selection position");
              }
              if (this.bcR.oT() == -1)
              {
                this.bcW -= 1;
                this.bcV = false;
                ch(67);
                break;
              }
              paramInt3 = this.bcR.oU();
              if (paramInt3 == 0) {
                break;
              }
              if (!this.bcF.kw())
              {
                if (((com.android.inputmethod.latin.settings.f)localObject1).bjb.bbR != 0) {
                  break label1483;
                }
                paramInt2 = 1;
                if (paramInt2 == 0) {
                  break label1488;
                }
              }
              ch(67);
              if (this.bcW <= 20) {
                break label1497;
              }
              paramInt2 = this.bcR.oT();
            } while (paramInt2 == -1);
            if (Character.isSupplementaryCodePoint(paramInt2)) {}
            for (paramInt2 = 2;; paramInt2 = 1)
            {
              this.bcR.cl(paramInt2);
              break;
              paramInt2 = 0;
              break label1431;
              this.bcR.cl(paramInt3);
              break label1441;
              break;
            }
            localObject1 = localKeyboardSwitcher.getKeyboard();
            if ((localObject1 == null) || (!com.android.inputmethod.keyboard.n.bE(((com.android.inputmethod.keyboard.l)localObject1).aLt.aLN))) {
              break label2519;
            }
            if (this.bcT != this.bcU) {
              if ((!this.bcS.mIsActive) || (!this.bcS.an(this.bcT, this.bcU)))
              {
                localObject1 = this.bcR;
                if (((t)localObject1).bep != null) {
                  break label1809;
                }
              }
            }
            for (localObject1 = null;; localObject1 = ((t)localObject1).bep.getSelectedText(0))
            {
              if (!TextUtils.isEmpty((CharSequence)localObject1))
              {
                localObject2 = this.bcq.baj;
                this.bcS.a(this.bcT, this.bcU, ((CharSequence)localObject1).toString(), ((com.android.inputmethod.latin.settings.f)localObject2).mLocale, ((com.android.inputmethod.latin.settings.f)localObject2).biH);
                this.bcS.qn();
                if (!this.bcS.an(this.bcT, this.bcU))
                {
                  this.bcT = this.bcS.bnr;
                  this.bcU = this.bcS.bns;
                  this.bcR.setSelection(this.bcT, this.bcU);
                }
                this.bcS.qm();
                paramInt2 = this.bcU;
                paramInt3 = this.bcT;
                this.bcR.setSelection(this.bcU, this.bcU);
                this.bcR.cl(paramInt2 - paramInt3);
                this.bcR.a(this.bcS.bnw, 0);
                this.bcT = this.bcS.bnr;
                this.bcU = this.bcS.bns;
                this.bcR.setSelection(this.bcT, this.bcU);
                this.aJH.lE();
              }
              localObject1 = "上档";
              bool = false;
              break;
            }
            localObject1 = "锁定大写";
            bool = false;
            break;
            localObject1 = "切换字母或符号";
            bool = false;
            break;
            localObject1 = "设置";
            Object localObject5;
            if (!oA())
            {
              localObject2 = getString(2131361876);
              localObject3 = getString(2131361921);
              localObject4 = getString(com.android.inputmethod.latin.utils.b.c(this, SettingsActivity.class));
              localObject5 = new DialogInterface.OnClickListener()
              {
                public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                {
                  paramAnonymousDialogInterface.dismiss();
                  switch (paramAnonymousInt)
                  {
                  default: 
                    return;
                  case 0: 
                    paramAnonymousDialogInterface = com.android.inputmethod.latin.utils.o.bd(LatinIME.k(LatinIME.this).bev.getId());
                    LatinIME.this.startActivity(paramAnonymousDialogInterface);
                    return;
                  }
                  LatinIME.o(LatinIME.this);
                }
              };
              a(new AlertDialog.Builder(this).setItems(new CharSequence[] { localObject3, localObject4 }, (DialogInterface.OnClickListener)localObject5).setTitle((CharSequence)localObject2).create());
            }
            bool = false;
            break;
            localObject1 = "shortcut";
            this.aMQ.d(this);
            bool = false;
            break;
            localObject1 = "next";
            cg(5);
            bool = false;
            break;
            localObject1 = "previous";
            cg(7);
            bool = false;
            break;
            localObject1 = "语言选择";
            localObject2 = getWindowToken();
            if (localObject2 != null)
            {
              if (this.bcq.baj.biP)
              {
                this.bcG.switchToNextInputMethod((IBinder)localObject2, false);
                bool = false;
                break;
              }
              localObject3 = this.bcH;
              localObject4 = this.bcG;
              localObject5 = ((u)localObject4).oZ().getCurrentInputMethodSubtype();
              InputMethodSubtype localInputMethodSubtype = ((c)localObject3).bdR;
              bool = ((c)localObject3).bdS;
              if (bool)
              {
                ((c)localObject3).bdR = ((InputMethodSubtype)localObject5);
                ((c)localObject3).bdS = false;
              }
              if ((bool) && (((u)localObject4).b(localInputMethodSubtype)) && (!((InputMethodSubtype)localObject5).equals(localInputMethodSubtype)))
              {
                ((u)localObject4).a((IBinder)localObject2, localInputMethodSubtype);
                bool = false;
                break;
              }
              ((u)localObject4).switchToNextInputMethod((IBinder)localObject2, true);
            }
            bool = false;
            break;
            localObject1 = "表情键盘";
            bool = false;
            break;
            localObject1 = "回车";
            localObject2 = getCurrentInputEditorInfo();
            if (!TextUtils.equals(((EditorInfo)localObject2).fieldName, "ime_translate_view")) {
              break label2231;
            }
            localObject1 = this.aJH;
            if (((KeyboardSwitcher)localObject1).aMD != null) {
              ((KeyboardSwitcher)localObject1).aMD.ar(true);
            }
            oG();
          } while (this.bcQ == null);
          this.bcQ.reset();
          return;
          if (TextUtils.equals(((EditorInfo)localObject2).fieldName, "ime_search_gif"))
          {
            localObject1 = this.aJH;
            if (((KeyboardSwitcher)localObject1).aMn != null) {
              ((KeyboardSwitcher)localObject1).Y(((KeyboardSwitcher)localObject1).aMn.getText());
            }
            oG();
            return;
          }
          int j = com.android.inputmethod.latin.utils.n.f((EditorInfo)localObject2);
          if (256 == j)
          {
            cg(((EditorInfo)localObject2).actionId);
            bool = false;
            break;
          }
          if (1 != j)
          {
            cg(j);
            bool = false;
            break;
          }
          bool = g(10, paramInt2, paramInt3, i);
          break;
          localObject1 = "shift_enter";
          bool = g(10, paramInt2, paramInt3, i);
          break;
          this.bcj.add(localObject1);
        }
      }
      Object localObject2 = this.aJH.aMW;
      if ((localObject2 != null) && (((MainKeyboardView)localObject2).getUserActionRepeatKeyCode() != paramInt1))
      {
        localObject2 = new com.emoji.network.beans.f();
        localObject3 = new f.b();
        ((com.emoji.network.beans.f)localObject2).setE("输入");
        ((com.emoji.network.beans.f)localObject2).setF("上屏");
        if (!TextUtils.isEmpty((CharSequence)localObject1)) {
          break label2501;
        }
        ((com.emoji.network.beans.f)localObject2).setG("文本");
        ((f.b)localObject3).setWhat(z.dg(paramInt1));
      }
      for (;;)
      {
        ((f.b)localObject3).setFrom("按键");
        ((com.emoji.network.beans.f)localObject2).setH((f.b)localObject3);
        a((com.emoji.network.beans.f)localObject2);
        if (this.bcj.size() <= 0) {
          break;
        }
        this.bcj.clear();
        break;
        label2501:
        ((com.emoji.network.beans.f)localObject2).setG("功能");
        ((f.b)localObject3).setWhat((String)localObject1);
      }
      label2519:
      localObject1 = "上档";
      bool = false;
    }
  }
  
  public final void i(Class<? extends Activity> paramClass)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, paramClass);
    localIntent.setFlags(337641472);
    startActivity(localIntent);
  }
  
  public final void lj()
  {
    b localB = this.bdi;
    Object localObject1 = localB.mLock;
    if (localObject1 != null) {}
    for (;;)
    {
      int i;
      try
      {
        localB.mHandler.removeMessages(1);
        localB.bdN = true;
        localB.aIJ.bdh.b(x.beZ, false);
        this.bdh.removeMessages(2);
        this.bcR.beginBatchEdit();
        localObject1 = this.bcq.baj;
        if (this.bcQ.pj())
        {
          if ((((com.android.inputmethod.latin.settings.f)localObject1).bjj) && (this.bcQ.bfw)) {
            com.android.inputmethod.latin.utils.q.a("", this.bcQ.bft.toString(), " ", this.bcQ);
          }
          i = this.bcQ.bfB;
          if (this.bcQ.pk())
          {
            cf(this.bcT);
            this.bcV = true;
          }
        }
        else
        {
          i = this.bcR.oT();
          if ((Character.isLetterOrDigit(i)) || (((com.android.inputmethod.latin.settings.f)localObject1).cN(i))) {
            this.bco = 4;
          }
          this.bcR.endBatchEdit();
          this.bcQ.bcg = oy();
          return;
        }
      }
      finally {}
      if (i <= 1) {
        aE("");
      } else {
        ax("");
      }
    }
  }
  
  public final void lk()
  {
    if (this.bdi != null)
    {
      b localB = this.bdi;
      Object localObject1 = localB.mLock;
      if (localObject1 != null) {
        try
        {
          localB.bdN = false;
          localB.aIJ.bdh.b(x.beZ, true);
          return;
        }
        finally {}
      }
    }
  }
  
  public final void ll() {}
  
  public final void lm()
  {
    com.android.inputmethod.keyboard.internal.u localU = this.aJH.aMX;
    switch (localU.aVE)
    {
    default: 
      return;
    case 3: 
      localU.nx();
      return;
    case 4: 
      localU.ny();
      return;
    }
    localU.lF();
  }
  
  public final boolean ln()
  {
    if (oA()) {
      return false;
    }
    Object localObject = this.bcG.J(this);
    CharSequence[] arrayOfCharSequence = new CharSequence[((List)localObject).size()];
    int i = 0;
    int j = 0;
    while (i < ((List)localObject).size())
    {
      arrayOfCharSequence[i] = ((InputMethodSubtype)((List)localObject).get(i)).getDisplayName(getApplicationContext(), getPackageName(), getApplicationInfo());
      if (((InputMethodSubtype)((List)localObject).get(i)).equals(this.bcG.oZ().getCurrentInputMethodSubtype())) {
        j = i;
      }
      i += 1;
    }
    String str = getString(2131362123);
    SpannableString localSpannableString = new SpannableString(str);
    if (Build.VERSION.SDK_INT >= 21) {
      localSpannableString.setSpan(new RelativeSizeSpan(1.3F), 0, str.length(), 0);
    }
    localObject = new AlertDialog.Builder(this).setSingleChoiceItems(arrayOfCharSequence, j, new DialogInterface.OnClickListener()
    {
      public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = LatinIME.this.getWindowToken();
        if (paramAnonymousDialogInterface == null) {}
        do
        {
          return;
          LatinIME.k(LatinIME.this).a(paramAnonymousDialogInterface, (InputMethodSubtype)this.bdM.get(paramAnonymousInt));
        } while (LatinIME.l(LatinIME.this) == null);
        LatinIME.l(LatinIME.this).dismiss();
      }
    }).setTitle(localSpannableString).setPositiveButton(2131361921, new DialogInterface.OnClickListener()
    {
      public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = com.android.inputmethod.latin.utils.o.bd(LatinIME.k(LatinIME.this).bev.getId());
        LatinIME.this.startActivity(paramAnonymousDialogInterface);
      }
    }).create();
    if (Build.VERSION.SDK_INT >= 21) {
      ((AlertDialog)localObject).setOnShowListener(new DialogInterface.OnShowListener()
      {
        public final void onShow(DialogInterface paramAnonymousDialogInterface)
        {
          paramAnonymousDialogInterface = ((AlertDialog)paramAnonymousDialogInterface).getButton(-1);
          paramAnonymousDialogInterface.setTextSize(0, paramAnonymousDialogInterface.getTextSize() * 1.3F);
          paramAnonymousDialogInterface.invalidate();
        }
      });
    }
    a((AlertDialog)localObject);
    return true;
  }
  
  public final boolean oF()
  {
    if (this.aMo != null)
    {
      SuggestionStripView localSuggestionStripView = this.aMo;
      if ((localSuggestionStripView.getVisibility() != 0) || (localSuggestionStripView.aRT == null) || (localSuggestionStripView.aRT.bff.isEmpty())) {}
      for (int i = 1; (i == 0) || (this.aMo.qd()); i = 0) {
        return true;
      }
    }
    return false;
  }
  
  public final void oG()
  {
    a(x.beZ, false);
    aw(false);
  }
  
  public final void oJ()
  {
    KeyboardSwitcher localKeyboardSwitcher = this.aJH;
    if (!oF()) {}
    for (boolean bool = true;; bool = false)
    {
      localKeyboardSwitcher.af(bool);
      return;
    }
  }
  
  public final void oK()
  {
    Log.i(TAG, "onSuggestionClear: ");
    this.aJH.af(true);
    this.aJH.ae(true);
  }
  
  public final void oN()
  {
    os();
    if (this.aJH.aMW != null)
    {
      KeyboardSwitcher localKeyboardSwitcher = this.aJH;
      EditorInfo localEditorInfo = getCurrentInputEditorInfo();
      com.android.inputmethod.latin.settings.f localF = this.bcq.baj;
      localKeyboardSwitcher.d(localEditorInfo);
      localKeyboardSwitcher = this.aJH;
      if (localKeyboardSwitcher.aMW != null) {
        localKeyboardSwitcher.aMW.aPp = true;
      }
      localKeyboardSwitcher.lZ();
      localKeyboardSwitcher.a(localKeyboardSwitcher.aMY.bH(localKeyboardSwitcher.aNe), false);
      localKeyboardSwitcher.ma();
      this.bdy = true;
    }
  }
  
  public final void oO()
  {
    this.bcR.oS();
    this.bcQ.reset();
  }
  
  public void onClick(View paramView)
  {
    paramView.getId();
  }
  
  public void onComputeInsets(InputMethodService.Insets paramInsets)
  {
    super.onComputeInsets(paramInsets);
    ViewFlipper localViewFlipper = this.aJH.aMP;
    if ((localViewFlipper == null) || (this.aMo == null) || (this.bcB == null)) {
      return;
    }
    Object localObject = this.aJH.aMP;
    int i;
    if (localObject == null)
    {
      i = 0;
      if (this.bcB == null) {
        break label581;
      }
      this.bcB.getLocationInWindow(com.android.inputmethod.latin.utils.i.bnc);
    }
    label88:
    label95:
    label111:
    label125:
    label192:
    label228:
    label262:
    label279:
    label295:
    label482:
    label515:
    label522:
    label528:
    label539:
    label544:
    label549:
    label562:
    label567:
    label572:
    label578:
    label581:
    for (int j = com.android.inputmethod.latin.utils.i.bnc[1];; j = 0)
    {
      int k;
      boolean bool1;
      boolean bool2;
      if (this.bcB.getVisibility() == 8)
      {
        k = 1;
        if (k == 0) {
          break label515;
        }
        j = 0;
        if (!isFullscreenMode()) {
          break label522;
        }
        k = this.bcA.getHeight();
        if (this.aMo.getVisibility() != 8) {
          break label528;
        }
        i = 0;
        k = j + k + i;
        int m = this.aJH.lU();
        localObject = this.aJH;
        if ((((KeyboardSwitcher)localObject).aMJ == null) || (((KeyboardSwitcher)localObject).aMJ.getChildCount() <= 0)) {
          break label539;
        }
        localObject = ((KeyboardSwitcher)localObject).aMJ.getChildAt(0);
        if (((View)localObject).getVisibility() != 0) {
          break label539;
        }
        j = ((View)localObject).getHeight();
        j = k + m - j;
        if (!localViewFlipper.isShown()) {
          break label578;
        }
        j -= i;
        if (this.bdr == null) {
          break label572;
        }
        bool1 = this.bdr.isShowing();
        if (this.bcz == null) {
          break label567;
        }
        localObject = this.bcz;
        if ((((as.a)localObject).bmh == null) || (((as.a)localObject).bmh.getVisibility() != 0)) {
          break label544;
        }
        i = 1;
        localObject = this.aJH;
        if (!((KeyboardSwitcher)localObject).lR()) {
          break label549;
        }
        bool2 = false;
        if ((!bool2) && (!bool1) && (i == 0)) {
          break label562;
        }
        i = 0;
        m = getResources().getDisplayMetrics().widthPixels;
        int n = localViewFlipper.getHeight();
        int i1 = this.aJH.lU();
        paramInsets.touchableInsets = 3;
        paramInsets.touchableRegion.set(0, i, m, n + k + 100 + i1);
      }
      for (;;)
      {
        paramInsets.contentTopInsets = j;
        paramInsets.visibleTopInsets = j;
        return;
        com.android.inputmethod.latin.suggestions.f localF;
        if (this.bdw <= 0)
        {
          i = ((View)localObject).getHeight();
          j = this.aMo.getHeight();
          k = getResources().getDisplayMetrics().heightPixels;
          this.bcB.getWindowVisibleDisplayFrame(this.mTempRect);
          j = k - this.mTempRect.top - j - i;
          localObject = this.bcB.getLayoutParams();
          localF = this.aMo.blP;
          i = localF.pZ();
          if (i > j) {
            break label482;
          }
        }
        for (;;)
        {
          ((ViewGroup.LayoutParams)localObject).height = i;
          this.bcB.setLayoutParams((ViewGroup.LayoutParams)localObject);
          this.bdw = ((ViewGroup.LayoutParams)localObject).height;
          i = this.bdw;
          break;
          localF.blh = ((j - localF.blj) / localF.blg);
          i = localF.pZ();
        }
        k = 0;
        break label88;
        j = i + j;
        break label95;
        k = 0;
        break label111;
        i = this.aMo.getHeight();
        break label125;
        j = 0;
        break label192;
        i = 0;
        break label262;
        bool2 = ((KeyboardSwitcher)localObject).aMW.mB();
        break label279;
        i = j;
        break label295;
        i = 0;
        break label262;
        bool1 = false;
        break label228;
      }
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (this.bdb != paramConfiguration.orientation)
    {
      this.bdb = paramConfiguration.orientation;
      Object localObject = this.bdh;
      ((d)localObject).removeMessages(1);
      ((d)localObject).oR();
      ((d)localObject).bdX = true;
      localObject = (LatinIME)((y)localObject).bnD.get();
      if (((LatinIME)localObject).isInputViewShown()) {
        ((LatinIME)localObject).aJH.lD();
      }
      this.bcR.beginBatchEdit();
      ax("");
      this.bcR.oS();
      this.bcR.endBatchEdit();
      if (oA()) {
        this.bdf.dismiss();
      }
      if (this.aJH != null)
      {
        localObject = this.aJH;
        ((KeyboardSwitcher)localObject).aNg = -1;
        ((KeyboardSwitcher)localObject).aMy = false;
        ((KeyboardSwitcher)localObject).mn();
        ((KeyboardSwitcher)localObject).mp();
        ((KeyboardSwitcher)localObject).mk();
        ((KeyboardSwitcher)localObject).ml();
        ((KeyboardSwitcher)localObject).lx();
      }
      if (this.bcz != null)
      {
        this.bcz.recycle();
        this.bcz = null;
      }
    }
    super.onConfigurationChanged(paramConfiguration);
  }
  
  public void onCreate()
  {
    this.bcm = FirebaseAnalytics.getInstance(this);
    this.bdn = new ar.a();
    Object localObject = new com.emoji.network.beans.f();
    ((com.emoji.network.beans.f)localObject).setE("启动");
    ((com.emoji.network.beans.f)localObject).setF("输入法");
    a((com.emoji.network.beans.f)localObject);
    PushAgent.getInstance(this).onAppStart();
    com.android.inputmethod.latin.settings.e.init(this);
    com.emoji.network.a.z(this, "ime");
    PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    u.init(this);
    this.bcG = u.oX();
    v.init(this);
    KeyboardSwitcher.b(this);
    c.init(this);
    an.b.a(this);
    super.onCreate();
    localObject = this.bdh;
    Log.d(TAG, "onCreate: ");
    Resources localResources = ((LatinIME)((y)localObject).bnD.get()).getResources();
    ((d)localObject).bdT = localResources.getInteger(2131492887);
    ((d)localObject).bdU = localResources.getInteger(2131492886);
    ((d)localObject).bdV = localResources.getInteger(2131492888);
    DEBUG = r.bed;
    os();
    ot();
    this.bdb = getResources().getConfiguration().orientation;
    localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.net.conn.CONNECTIVITY_CHANGE");
    ((IntentFilter)localObject).addAction("android.media.RINGER_MODE_CHANGED");
    registerReceiver(this.bdA, (IntentFilter)localObject);
    DictionaryDecayBroadcastReciever.L(this);
    this.bdi = new b(this, (byte)0);
    this.bdj = new PackageManReceiver(this);
    registerReceiver(this.bcy, new IntentFilter("com.setting.nightmode.ACTION_NIGHT_MODE"));
    this.bdl = new GifListChangeLocalReceiver(this, (byte)0);
    this.bdm = new CustomThemeUpdateReceiver(this);
    this.bdk = new RateUsNotificationCancelReceiver(this);
    if (bds) {
      bds = com.emoji.common.h.a(this, "KEY_FIRST_START_INPUTVIEW", Boolean.valueOf(true)).booleanValue();
    }
    this.bdu = 0;
    this.bdt = 0L;
    this.bct = com.emoji.common.h.d(this, "KEY_COOL_FONT_SELECT", -1);
    this.aJk = ((ClipboardManager)getSystemService("clipboard"));
    if (this.aJk != null) {
      this.aJk.addPrimaryClipChangedListener(this);
    }
    or();
  }
  
  public View onCreateInputView()
  {
    t.c(this);
    KeyboardSwitcher localKeyboardSwitcher = this.aJH;
    boolean bool = this.bdg;
    if (localKeyboardSwitcher.aMW != null) {
      localKeyboardSwitcher.aMW.closing();
    }
    localKeyboardSwitcher.a(localKeyboardSwitcher.aIX, localKeyboardSwitcher.aNa);
    localKeyboardSwitcher.aMH = new KeyboardSwitcher.RemoveAdsReceiver(localKeyboardSwitcher, localKeyboardSwitcher.aIX);
    localKeyboardSwitcher.aMT = ((ViewGroup)LayoutInflater.from(localKeyboardSwitcher.aNb).inflate(2130903171, null));
    localKeyboardSwitcher.aMS = ((InputView)localKeyboardSwitcher.aMT.findViewById(2131755603));
    localKeyboardSwitcher.aMt = ((FontTextView)localKeyboardSwitcher.aMS.findViewById(2131755612));
    localKeyboardSwitcher.aMt.setOnClickListener(localKeyboardSwitcher);
    localKeyboardSwitcher.aMJ = ((FrameLayout)localKeyboardSwitcher.aMS.findViewById(2131755605));
    localKeyboardSwitcher.aMu = ((FontTextView)localKeyboardSwitcher.aMS.findViewById(2131755615));
    localKeyboardSwitcher.aMu.setOnClickListener(localKeyboardSwitcher);
    localKeyboardSwitcher.aMv = ((FontTextView)localKeyboardSwitcher.aMS.findViewById(2131755613));
    localKeyboardSwitcher.aMv.setOnClickListener(localKeyboardSwitcher);
    int i = com.emoji.common.h.d(localKeyboardSwitcher.aIX, "KB_FONT_SIZE_SELECT", 2);
    localKeyboardSwitcher.aMu.setTextSize(com.android.inputmethod.latin.settings.KbAdjustActivity.bhw[i]);
    localKeyboardSwitcher.aMt.setTextSize(com.android.inputmethod.latin.settings.KbAdjustActivity.bhw[i]);
    localKeyboardSwitcher.aMU = ((ViewGroup)localKeyboardSwitcher.aMS.findViewById(2131755609));
    localKeyboardSwitcher.aMV = ((ViewGroup)localKeyboardSwitcher.aMS.findViewById(2131755606));
    localKeyboardSwitcher.aMP = ((ViewFlipper)localKeyboardSwitcher.aMS.findViewById(2131755608));
    localKeyboardSwitcher.aNf = ((ImageView)localKeyboardSwitcher.aMT.findViewById(2131755607));
    i = localKeyboardSwitcher.lC();
    Object localObject = localKeyboardSwitcher.aMV.getLayoutParams();
    ((ViewGroup.LayoutParams)localObject).height = i;
    localKeyboardSwitcher.aMV.setLayoutParams((ViewGroup.LayoutParams)localObject);
    localKeyboardSwitcher.aMW = ((MainKeyboardView)localKeyboardSwitcher.aMS.findViewById(2131755616));
    localKeyboardSwitcher.aMW.setHardwareAcceleratedDrawingEnabled(bool);
    localKeyboardSwitcher.aMW.setKeyboardActionListener(localKeyboardSwitcher.aIX);
    localKeyboardSwitcher.aMW.setUIListener(localKeyboardSwitcher);
    localObject = an.c.ks();
    MainKeyboardView localMainKeyboardView = localKeyboardSwitcher.aMW;
    if (localMainKeyboardView != null)
    {
      ((an.c)localObject).aHN = localMainKeyboardView;
      ViewCompat.setAccessibilityDelegate(localMainKeyboardView, (AccessibilityDelegateCompat)localObject);
      if (((an.c)localObject).aHO != null) {
        ((an.c)localObject).aHO.a(localMainKeyboardView);
      }
    }
    localKeyboardSwitcher.ma();
    localKeyboardSwitcher.lW();
    localKeyboardSwitcher.aNi = ((DrawingView)localKeyboardSwitcher.aMS.findViewById(2131755617));
    localKeyboardSwitcher.aMm = ((DrawingView)localKeyboardSwitcher.aMS.findViewById(2131755610));
    localKeyboardSwitcher.aMo = ((SuggestionStripView)localKeyboardSwitcher.aMS.findViewById(2131755611));
    localKeyboardSwitcher.aMo.qc();
    localKeyboardSwitcher.b(KeyboardSwitcher.lB());
    if (localKeyboardSwitcher.aMI == 0L) {
      localKeyboardSwitcher.aMI = com.emoji.common.h.c(localKeyboardSwitcher.aIX, "AD_KEY_KEYBOARD_ICON_AD_LAST_SHOWTIME", 0L);
    }
    this.bcC = ((ViewGroup)localKeyboardSwitcher.aMT);
    I(this);
    return this.bcC;
  }
  
  public void onCurrentInputMethodSubtypeChanged(InputMethodSubtype paramInputMethodSubtype)
  {
    or();
    this.aMQ.c(paramInputMethodSubtype);
    if (this.bdr != null) {
      this.bdr.p(this);
    }
    Object localObject = this.bdh;
    ((d)localObject).sendMessage(((d)localObject).obtainMessage(5));
    os();
    if (this.aJH.aMW != null)
    {
      localObject = this.aJH;
      EditorInfo localEditorInfo = getCurrentInputEditorInfo();
      com.android.inputmethod.latin.settings.f localF = this.bcq.baj;
      ((KeyboardSwitcher)localObject).d(localEditorInfo);
    }
    InputMethodSettingsFragment.p(this, paramInputMethodSubtype.getLocale());
    if (this.aJH != null) {
      this.aJH.aNq = -10;
    }
  }
  
  public void onDestroy()
  {
    this.aMo = null;
    this.bcM = null;
    this.bcN = null;
    this.bcL = null;
    this.bcK = null;
    this.bcI = null;
    com.android.inputmethod.latin.personalization.d.clean();
    ??? = new com.emoji.network.beans.f();
    ((com.emoji.network.beans.f)???).setE("关闭");
    ((com.emoji.network.beans.f)???).setF("输入法");
    a((com.emoji.network.beans.f)???);
    if (this.aJk != null)
    {
      this.aJk.removePrimaryClipChangedListener(this);
      this.aJk = null;
    }
    ??? = this.bcD;
    if (??? != null)
    {
      ((w)???).close();
      this.bcD = null;
    }
    ??? = this.bcq;
    ((com.android.inputmethod.latin.settings.e)???).aMR.unregisterOnSharedPreferenceChangeListener((SharedPreferences.OnSharedPreferenceChangeListener)???);
    unregisterReceiver(this.bdA);
    if (this.bdi != null)
    {
      b.a(this.bdi);
      this.bdi = null;
    }
    if (this.bdq != null)
    {
      cb.c.bDd = null;
      this.bdq.recycle();
      this.bdq = null;
    }
    PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    ??? = this.aJH;
    ((KeyboardSwitcher)???).lX();
    ((KeyboardSwitcher)???).ma();
    ((KeyboardSwitcher)???).lW();
    ((KeyboardSwitcher)???).aIX = null;
    if (((KeyboardSwitcher)???).aMW != null) {
      ((KeyboardSwitcher)???).aMW.setKeyboardActionListener(m.aLL);
    }
    if (this.bdj != null)
    {
      unregisterReceiver(this.bdj);
      this.bdj = null;
    }
    if (this.bcy != null)
    {
      unregisterReceiver(this.bcy);
      this.bcy = null;
    }
    if (this.bdl != null)
    {
      LocalBroadcastManager.getInstance(this).unregisterReceiver(this.bdl);
      this.bdl = null;
    }
    if (this.bdm != null)
    {
      unregisterReceiver(this.bdm);
      this.bdm = null;
    }
    if (this.bdk != null)
    {
      unregisterReceiver(this.bdk);
      this.bdk = null;
    }
    if (this.bdr != null)
    {
      this.bdr.recycle();
      this.bdr = null;
    }
    if (this.bcz != null)
    {
      this.bdh.removeMessages(1011);
      this.bcz.recycle();
      this.bcz = null;
    }
    ce.d.aI(this);
    if (this.bdn != null)
    {
      synchronized (this.bdn)
      {
        if (((ar.a)???).bfM != null)
        {
          ((ar.a)???).bfL = true;
          ((ar.a)???).bfM.shutdown();
          ((ar.a)???).bfM = null;
        }
        Iterator localIterator = ((ar.a)???).bfK.values().iterator();
        if (localIterator.hasNext()) {
          ((com.android.inputmethod.latin.suggestions.gifpredict.a)localIterator.next()).bmm = null;
        }
      }
      ((ar.a)???).bfK.clear();
      this.bdn = null;
    }
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
    if (!this.bcq.baj.bjb.bbP) {
      return;
    }
    if (paramArrayOfCompletionInfo == null)
    {
      oG();
      return;
    }
    this.bcE = com.android.inputmethod.latin.utils.h.b(paramArrayOfCompletionInfo);
    a(new x(x.a(paramArrayOfCompletionInfo), false, false, false, false, false), false);
    aw(false);
  }
  
  public boolean onEvaluateFullscreenMode()
  {
    if (this.aJH.aMx) {}
    EditorInfo localEditorInfo;
    do
    {
      boolean bool;
      do
      {
        return false;
        bool = com.android.inputmethod.latin.settings.e.k(getResources());
      } while ((!super.onEvaluateFullscreenMode()) || (!bool));
      localEditorInfo = getCurrentInputEditorInfo();
    } while ((localEditorInfo != null) && ((localEditorInfo.imeOptions & 0x10000000) != 0));
    return true;
  }
  
  public void onExtractedCursorMovement(int paramInt1, int paramInt2)
  {
    if (this.bcq.baj.cH(this.bdb)) {
      return;
    }
    super.onExtractedCursorMovement(paramInt1, paramInt2);
  }
  
  public void onExtractedTextClicked()
  {
    if (this.bcq.baj.cH(this.bdb)) {
      return;
    }
    super.onExtractedTextClicked();
  }
  
  public void onFinishInput()
  {
    d localD = this.bdh;
    if (localD.hasMessages(1)) {
      localD.beb = true;
    }
    for (;;)
    {
      if (this.bdt > 0L)
      {
        this.bdt = 0L;
        this.bdu += 1;
      }
      this.bcl = true;
      this.bcj.clear();
      return;
      LatinIME localLatinIME = (LatinIME)localD.bnD.get();
      localD.b(localLatinIME, null, false);
      h(localLatinIME);
    }
  }
  
  public void onFinishInputView(boolean paramBoolean)
  {
    cb.b.bCU = cb.b.bCT;
    if (this.bdr != null) {
      this.bdr.hide();
    }
    com.emoji.network.a.z(this, "ime");
    Object localObject = this.bdh;
    if (((d)localObject).hasMessages(1)) {
      ((d)localObject).bea = true;
    }
    for (;;)
    {
      if (!paramBoolean)
      {
        if (this.bcz != null)
        {
          this.bdh.removeMessages(1011);
          this.bcz.hide();
        }
        localObject = new com.emoji.network.beans.f();
        ((com.emoji.network.beans.f)localObject).setE("状态");
        ((com.emoji.network.beans.f)localObject).setF("输入法");
        ((com.emoji.network.beans.f)localObject).setG("收起");
        a((com.emoji.network.beans.f)localObject);
      }
      MobclickAgent.onPause(this);
      return;
      a((LatinIME)((y)localObject).bnD.get(), paramBoolean);
      ((d)localObject).bec = null;
    }
  }
  
  public void onInitializeInterface()
  {
    super.onInitializeInterface();
    if (this.bdc != this.bdb)
    {
      this.bdc = this.bdb;
      if (this.bdq != null) {
        this.bdq.recycle();
      }
      this.bdq = new cl.b(this, this);
      cb.c.bDd = this.bdq;
      this.bdq.uN();
      this.bdq.a(this);
      this.bdq.a(this.aJH);
    }
    KeyboardSwitcher localKeyboardSwitcher = this.aJH;
    ce.d.a(localKeyboardSwitcher.aIX.bdq, null);
    localKeyboardSwitcher.aNj = true;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    long l = paramKeyEvent.getDeviceId() << paramKeyEvent.getKeyCode() + 32;
    if (this.bcY.remove(Long.valueOf(l))) {
      return true;
    }
    switch (paramInt)
    {
    }
    for (;;)
    {
      return super.onKeyUp(paramInt, paramKeyEvent);
      this.aJH.aNq = 4;
      this.bdz = true;
    }
  }
  
  public boolean onLongClick(View paramView)
  {
    paramView.getId();
    return true;
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    if (!MainApp.axO) {
      aw.i.ag(this).bsc.rf();
    }
    if (this.aJH != null) {
      this.aJH.onLowMemory();
    }
  }
  
  public void onPrimaryClipChanged()
  {
    if (this.aJk == null) {}
    Object localObject;
    do
    {
      do
      {
        return;
        localObject = this.aJk.getPrimaryClip();
      } while ((localObject == null) || (((ClipData)localObject).getItemCount() == 0));
      localObject = ((ClipData)localObject).getItemAt(0).getText();
    } while (TextUtils.isEmpty((CharSequence)localObject));
    long l = System.currentTimeMillis();
    if (l - this.bdp < 200L)
    {
      this.bdp = l;
      return;
    }
    this.bdp = l;
    if (this.bcv == null) {
      this.bcv = new ClipItemData();
    }
    this.bcv.text = ((CharSequence)localObject).toString();
    this.bcv.time = System.currentTimeMillis();
    this.bcv.aXN = false;
    ClipItemData localClipItemData = new ClipItemData();
    localClipItemData.text = ((CharSequence)localObject).toString();
    localClipItemData.time = System.currentTimeMillis();
    com.more.setting.db.a.dI(this).a(localClipItemData);
  }
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    int j = -1;
    if (TextUtils.isEmpty(paramString)) {}
    label72:
    label74:
    label133:
    LatinIME localLatinIME;
    do
    {
      do
      {
        do
        {
          return;
          switch (paramString.hashCode())
          {
          default: 
            i = -1;
            switch (i)
            {
            default: 
              if (TextUtils.equals(paramString, com.android.inputmethod.latin.settings.a.e(ou())))
              {
                com.android.inputmethod.keyboard.o.ls();
                oN();
              }
              break;
            }
            break;
          }
        } while (this.aJH == null);
        paramSharedPreferences = this.aJH;
      } while (TextUtils.isEmpty(paramString));
      localLatinIME = paramSharedPreferences.aIX;
    } while (localLatinIME == null);
    switch (paramString.hashCode())
    {
    default: 
      i = j;
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return;
      case 0: 
        ce.d.a(localLatinIME.bdq, null);
        return;
        if (!paramString.equals("night_mode_key_switcher")) {
          break label72;
        }
        i = 0;
        break label74;
        if (!paramString.equals("night_mode_key_effect_degree")) {
          break label72;
        }
        i = 1;
        break label74;
        if (!paramString.equals("KEY_COOL_FONT_SELECT")) {
          break label72;
        }
        i = 2;
        break label74;
        if (!paramString.equals("KEY_THEME")) {
          break label72;
        }
        i = 3;
        break label74;
        if (!paramString.equals("KEY_KB_BG")) {
          break label72;
        }
        i = 4;
        break label74;
        if (!paramString.equals("KEY_TYPE_FACE")) {
          break label72;
        }
        i = 5;
        break label74;
        I(this);
        break label133;
        this.bct = com.emoji.common.h.d(this, "KEY_COOL_FONT_SELECT", -1);
        break label133;
        com.kb.anims.a.eLV = true;
        com.kb.anims.a.eLW = true;
        if (this.bdq == null) {
          break label133;
        }
        this.bdq.uN();
        if (!isInputViewShown()) {
          break label133;
        }
        oN();
        break label133;
        if (this.bdq == null) {
          break label133;
        }
        this.bdq.uN();
        oN();
        break label133;
        if (this.bdq == null) {
          break label133;
        }
        this.bdq.uO();
        break label133;
        i = j;
        if (paramString.equals("KEY_EMOJISTYLE"))
        {
          i = 0;
          continue;
          i = j;
          if (paramString.equals("KEY_CUSTOMIZE_KB_SIZE_RESET_SIGNAL"))
          {
            i = 1;
            continue;
            i = j;
            if (paramString.equals("KB_FONT_SIZE_SELECT"))
            {
              i = 2;
              continue;
              i = j;
              if (paramString.equals("AD_KEY_KEYBOARD_ICON_AD_LAST_SHOWTIME"))
              {
                i = 3;
                continue;
                i = j;
                if (paramString.equals("KEY_SMART_EMOJI")) {
                  i = 4;
                }
              }
            }
          }
        }
        break;
      }
    }
    com.emoji.setting.b.h(localLatinIME, 1.0F);
    paramSharedPreferences.lz();
    return;
    int i = com.emoji.common.h.d(localLatinIME, "KB_FONT_SIZE_SELECT", 2);
    paramSharedPreferences.aMu.setTextSize(com.android.inputmethod.latin.settings.KbAdjustActivity.bhw[i]);
    paramSharedPreferences.aMt.setTextSize(com.android.inputmethod.latin.settings.KbAdjustActivity.bhw[i]);
    paramSharedPreferences.lz();
    paramSharedPreferences.ae(false);
    paramSharedPreferences.ad(false);
    return;
    paramSharedPreferences.aMI = com.emoji.common.h.c(localLatinIME, "AD_KEY_KEYBOARD_ICON_AD_LAST_SHOWTIME", 0L);
    return;
    paramSharedPreferences.G(localLatinIME);
  }
  
  public void onStartInput(EditorInfo paramEditorInfo, boolean paramBoolean)
  {
    cb.b.bCX.reset();
    d localD = this.bdh;
    if (localD.hasMessages(1))
    {
      localD.bdZ = true;
      return;
    }
    if ((localD.bdX) && (paramBoolean))
    {
      localD.bdX = false;
      localD.bdY = true;
    }
    LatinIME localLatinIME = (LatinIME)localD.bnD.get();
    localD.b(localLatinIME, paramEditorInfo, paramBoolean);
    a(localLatinIME, paramEditorInfo, paramBoolean);
  }
  
  public void onStartInputView(final EditorInfo paramEditorInfo, boolean paramBoolean)
  {
    MobclickAgent.onResume(this);
    d localD;
    int i;
    if (paramBoolean)
    {
      if (this.bcn != null) {
        this.bcn.onExtractedTextClicked();
      }
      this.bdw = -1;
      com.android.umpush.a.Z(this);
      localD = this.bdh;
      if (!localD.hasMessages(1)) {
        break label392;
      }
      localObject1 = localD.bec;
      if ((paramEditorInfo != null) || (localObject1 != null)) {
        break label326;
      }
      i = 1;
      label67:
      if (i == 0) {
        break label392;
      }
      localD.oR();
      if (this.bcv != null) {
        break label2750;
      }
    }
    for (Object localObject1 = null;; localObject1 = null)
    {
      label326:
      label392:
      long l1;
      label590:
      label1091:
      label1181:
      label1210:
      label1239:
      label1499:
      label1507:
      label1519:
      label1680:
      label1752:
      label1873:
      label1886:
      label1995:
      label2084:
      label2287:
      label2302:
      label2308:
      label2314:
      label2320:
      label2326:
      label2332:
      label2351:
      label2371:
      label2377:
      label2383:
      label2389:
      label2750:
      do
      {
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          if (this.bcz == null) {
            this.bcz = new as.a(this);
          }
          if ((this.bcv != null) && ((paramEditorInfo.imeOptions & 0xFF) != 5)) {
            this.bcv.aXN = true;
          }
          this.bcw = 10000L;
          this.bdh.sendEmptyMessage(1011);
          this.bcz.b(paramEditorInfo, (String)localObject1);
        }
        return;
        if (bds)
        {
          av.a.ad(this);
          bds = false;
          com.emoji.common.h.c(this, "KEY_FIRST_START_INPUTVIEW", false);
          localObject1 = new com.emoji.network.beans.f();
          ((com.emoji.network.beans.f)localObject1).setE("事件");
          ((com.emoji.network.beans.f)localObject1).setF("输入法");
          ((com.emoji.network.beans.f)localObject1).setG("首次展开键盘");
          localObject2 = new f.b();
          ((f.b)localObject2).setWhat(paramEditorInfo.packageName);
          ((com.emoji.network.beans.f)localObject1).setH((f.b)localObject2);
          MainApp.axF.a((com.emoji.network.beans.f)localObject1, true);
        }
        localObject1 = new com.emoji.network.beans.f();
        ((com.emoji.network.beans.f)localObject1).setE("状态");
        ((com.emoji.network.beans.f)localObject1).setF("输入法");
        ((com.emoji.network.beans.f)localObject1).setG("展开");
        Object localObject2 = new f.b();
        ((f.b)localObject2).setWhat(paramEditorInfo.packageName);
        ((com.emoji.network.beans.f)localObject1).setH((f.b)localObject2);
        a((com.emoji.network.beans.f)localObject1);
        break;
        if ((paramEditorInfo == null) || (localObject1 == null))
        {
          i = 0;
          break label67;
        }
        if ((paramEditorInfo.inputType == ((EditorInfo)localObject1).inputType) && (paramEditorInfo.imeOptions == ((EditorInfo)localObject1).imeOptions) && (TextUtils.equals(paramEditorInfo.privateImeOptions, ((EditorInfo)localObject1).privateImeOptions)))
        {
          i = 1;
          break label67;
        }
        i = 0;
        break label67;
        if (localD.bdY)
        {
          localD.bdY = false;
          localD.oR();
          localD.sendMessageDelayed(localD.obtainMessage(1), 800L);
        }
        LatinIME localLatinIME = (LatinIME)localD.bnD.get();
        localD.b(localLatinIME, paramEditorInfo, paramBoolean);
        localLatinIME.onStartInputView(paramEditorInfo, paramBoolean);
        l1 = System.currentTimeMillis();
        long l2 = l1 - MainApp.axC;
        if (l2 > 0L)
        {
          l2 /= 86400000L;
          if (localLatinIME.bdv != l2)
          {
            localLatinIME.bdv = l2;
            localLatinIME.bcm.setUserProperty(cb.a.bCR, String.valueOf(l2));
          }
        }
        if (TextUtils.equals(paramEditorInfo.fieldName, "ime_translate_view"))
        {
          localLatinIME.bcr = false;
          localLatinIME.bcs = false;
          switch (paramEditorInfo.inputType & 0xFF0)
          {
          default: 
            localLatinIME.bcl = true;
            if ((!TextUtils.equals(paramEditorInfo.fieldName, "ime_search_gif")) && (!TextUtils.equals(paramEditorInfo.fieldName, "ime_translate_view"))) {
              switch (MainApp.axH)
              {
              }
            }
            break;
          }
        }
        KeyboardSwitcher localKeyboardSwitcher;
        Object localObject3;
        for (;;)
        {
          if ((paramEditorInfo.packageName != null) && (paramEditorInfo.packageName.contains("contacts")) && ((paramEditorInfo.inputType & 0xFF0) == 96))
          {
            l2 = com.emoji.common.h.c(MainApp.axF, "KEY_CONTACTS_SHOW_TIME", 0L);
            if (((l2 == 0L) || (l1 - l2 > 86400000L)) && (!fj.a.dH(localLatinIME)))
            {
              new Handler().postDelayed(new Runnable()
              {
                public final void run()
                {
                  if (LatinIME.j(LatinIME.this) == null) {
                    return;
                  }
                  fj.a.a(MainApp.axF, LatinIME.j(LatinIME.this).getWindowToken(), paramEditorInfo.packageName);
                }
              }, 500L);
              com.emoji.common.h.b(MainApp.axF, "KEY_CONTACTS_SHOW_TIME", l1);
            }
          }
          localLatinIME.bcG.pa();
          localKeyboardSwitcher = localLatinIME.aJH;
          localObject3 = localKeyboardSwitcher.aMW;
          localObject1 = localLatinIME.bcq.baj;
          if (paramEditorInfo != null) {
            break label1091;
          }
          Log.e(TAG, "Null EditorInfo in onStartInputView()");
          if (!r.bed) {
            break label2287;
          }
          throw new NullPointerException("Null EditorInfo in onStartInputView()");
          switch (paramEditorInfo.inputType & 0xF)
          {
          default: 
            if (((paramEditorInfo.imeOptions & 0xFF) == 3) || ((paramEditorInfo.imeOptions & 0xFF) == 2))
            {
              localLatinIME.bcr = false;
              localLatinIME.bcs = false;
            }
            break;
          case 2: 
          case 3: 
          case 4: 
            localLatinIME.bcr = false;
            localLatinIME.bcs = false;
            break;
            i = paramEditorInfo.inputType & 0xFF0;
            switch (i)
            {
            default: 
              localLatinIME.bcr = true;
              if (i == 96) {
                localLatinIME.bcs = false;
              }
              break;
            case 16: 
            case 32: 
            case 112: 
            case 128: 
            case 144: 
            case 160: 
            case 176: 
            case 224: 
              localLatinIME.bcr = false;
              localLatinIME.bcs = false;
              break;
              localLatinIME.bcs = true;
              break;
              localLatinIME.bcl = false;
              break label590;
              paramEditorInfo.fieldName = "ime_customize_kb_height";
              continue;
              paramEditorInfo.fieldName = "ime_customize_suggestion_font";
              continue;
              paramEditorInfo.fieldName = "preview_custom_kb";
              continue;
              paramEditorInfo.fieldName = "preview_kb";
            }
            break;
          }
        }
        Object localObject4;
        boolean bool1;
        boolean bool2;
        if (DEBUG)
        {
          Log.d(TAG, "onStartInputView: editorInfo:" + String.format("inputType=0x%08x imeOptions=0x%08x", new Object[] { Integer.valueOf(paramEditorInfo.inputType), Integer.valueOf(paramEditorInfo.imeOptions) }));
          localObject2 = TAG;
          localObject4 = new StringBuilder("All caps = ");
          if ((paramEditorInfo.inputType & 0x1000) != 0)
          {
            bool1 = true;
            localObject4 = ((StringBuilder)localObject4).append(bool1).append(", sentence caps = ");
            if ((paramEditorInfo.inputType & 0x4000) == 0) {
              break label2302;
            }
            bool1 = true;
            localObject4 = ((StringBuilder)localObject4).append(bool1).append(", word caps = ");
            if ((paramEditorInfo.inputType & 0x2000) == 0) {
              break label2308;
            }
            bool1 = true;
            Log.d((String)localObject2, bool1);
          }
        }
        else
        {
          if (o.a(null, "nm", paramEditorInfo))
          {
            Log.w(TAG, "Deprecated private IME option specified: " + paramEditorInfo.privateImeOptions);
            Log.w(TAG, "Use " + localLatinIME.getPackageName() + ".noMicrophoneKey instead");
          }
          if (o.a(localLatinIME.getPackageName(), "forceAscii", paramEditorInfo))
          {
            Log.w(TAG, "Deprecated private IME option specified: " + paramEditorInfo.privateImeOptions);
            Log.w(TAG, "Use EditorInfo.IME_FLAG_FORCE_ASCII flag instead");
          }
          localObject2 = com.android.inputmethod.latin.utils.ab.bp(paramEditorInfo.packageName);
          localLatinIME.bcF.b((PackageInfo)localObject2);
          if (localObject2 == null) {
            new com.android.inputmethod.latin.utils.ab(localLatinIME, localLatinIME).executeOnExecutor(MainApp.iY(), new String[] { paramEditorInfo.packageName });
          }
          if (localObject3 != null)
          {
            localObject2 = an.b.kr();
            if ((((an.b)localObject2).isTouchExplorationEnabled()) && (((an.b)localObject2).b(paramEditorInfo))) {
              ((an.b)localObject2).b((View)localObject3, ((an.b)localObject2).mContext.getText(2131362016));
            }
            localObject2 = ((com.android.inputmethod.latin.settings.f)localObject1).bjb;
            if (paramEditorInfo.inputType != ((o)localObject2).bbR) {
              break label2314;
            }
            i = 1;
            if (i != 0) {
              break label2320;
            }
            i = 1;
            if ((paramBoolean) && (i == 0)) {
              break label2326;
            }
            bool1 = true;
            if (bool1) {
              localLatinIME.aMQ.pc();
            }
            localLatinIME.updateFullscreenMode();
            localLatinIME.bcE = null;
            localLatinIME.bdd = null;
            localLatinIME.av(true);
            localLatinIME.bcW = 0;
            localLatinIME.bco = 0;
            localLatinIME.bcS.mIsActive = false;
            localLatinIME.bcY.clear();
            localObject2 = localLatinIME.aMQ.pe();
            localObject4 = localLatinIME.bcD;
            if ((localObject4 != null) && (localObject2 != null) && (!((Locale)localObject2).equals(((w)localObject4).mLocale))) {
              localLatinIME.ot();
            }
            if (localLatinIME.aMo != null) {
              localLatinIME.oL();
            }
            localLatinIME.aRT = x.beZ;
            if (localLatinIME.bcR.i(paramEditorInfo.initialSelStart, false)) {
              break label2332;
            }
            localLatinIME.bdh.b(bool1, 5);
            i = 0;
            if (!bool1) {
              break label2351;
            }
            ((MainKeyboardView)localObject3).closing();
            localLatinIME.os();
            localObject2 = localLatinIME.bcq.baj;
            if ((localObject4 != null) && (((com.android.inputmethod.latin.settings.f)localObject2).bjg)) {
              ((w)localObject4).beS = ((com.android.inputmethod.latin.settings.f)localObject2).beS;
            }
            localKeyboardSwitcher.d(paramEditorInfo);
            localObject1 = localObject2;
            if (i == 0)
            {
              localKeyboardSwitcher.lD();
              localObject1 = localObject2;
            }
            localLatinIME.oE();
            localLatinIME.bcT = paramEditorInfo.initialSelStart;
            localLatinIME.bcU = paramEditorInfo.initialSelEnd;
            localLatinIME.ov();
            localLatinIME.bdh.removeMessages(2);
            localLatinIME.bdh.bdW = 0L;
            ((MainKeyboardView)localObject3).setMainDictionaryAvailability(localLatinIME.bcJ);
            ((MainKeyboardView)localObject3).a(((com.android.inputmethod.latin.settings.f)localObject1).biN, ((com.android.inputmethod.latin.settings.f)localObject1).bje);
            ((MainKeyboardView)localObject3).setSlidingKeyInputPreviewEnabled(((com.android.inputmethod.latin.settings.f)localObject1).biY);
            bool2 = ((com.android.inputmethod.latin.settings.f)localObject1).biV;
            bool1 = ((com.android.inputmethod.latin.settings.f)localObject1).biW;
            boolean bool3 = ((com.android.inputmethod.latin.settings.f)localObject1).biX;
            s.aj(bool2);
            if ((!bool2) || (!bool1)) {
              break label2371;
            }
            bool1 = true;
            if ((!bool2) || (!bool3)) {
              break label2377;
            }
            bool2 = true;
            ((MainKeyboardView)localObject3).aOB.aRO = bool2;
            localObject2 = ((MainKeyboardView)localObject3).aOB;
            localObject4 = KeyboardSwitcher.b(null);
            d.a.a(((com.android.inputmethod.keyboard.internal.d)localObject2).aRP).setTypeface((Typeface)localObject4);
            ((MainKeyboardView)localObject3).aOC.aRO = bool1;
            if (localLatinIME.bcZ != ((com.android.inputmethod.latin.settings.f)localObject1).bcZ)
            {
              localLatinIME.ot();
              localLatinIME.bcZ = ((com.android.inputmethod.latin.settings.f)localObject1).bcZ;
            }
            if (localLatinIME.bda != ((com.android.inputmethod.latin.settings.f)localObject1).bda)
            {
              localLatinIME.bda = ((com.android.inputmethod.latin.settings.f)localObject1).bda;
              if (!localLatinIME.bda) {
                break label2383;
              }
              ag.qr();
            }
            if (paramEditorInfo != null) {
              cb.b.bCV = paramEditorInfo.packageName;
            }
            cb.b.bCW = paramEditorInfo.inputType;
            cb.b.bCU = paramEditorInfo;
            if (PreviewSkinActivity.fty >= 0) {
              localLatinIME.aJH.ac(PreviewSkinActivity.fty, -1);
            }
            if (!TextUtils.isEmpty(paramEditorInfo.packageName))
            {
              MobclickAgent.onEvent(localLatinIME, "alpha_kb_raise_app", paramEditorInfo.packageName);
              if (localLatinIME.getResources().getConfiguration().orientation != 1) {
                break label2389;
              }
              MobclickAgent.onEvent(localLatinIME, "alpha_kb_raise_app_portrait", paramEditorInfo.packageName);
            }
            bool2 = false;
            bool1 = bool2;
            switch (cb.d.d(localLatinIME, true))
            {
            default: 
              bool1 = bool2;
            }
          }
        }
        for (;;)
        {
          localObject1 = localLatinIME.bcG.a(null);
          if (localObject1 != null) {
            localKeyboardSwitcher.a(localLatinIME, paramEditorInfo, (InputMethodSubtype)localObject1, paramBoolean, bool1);
          }
          if ((MainApp.axH == 2) || (MainApp.axH == 1))
          {
            localLatinIME.aMw = new com.android.inputmethod.keyboard.a(localLatinIME);
            localLatinIME.bcC.addView(localLatinIME.aMw);
          }
          MainApp.y(localLatinIME);
          if (MainApp.jd())
          {
            l1 = com.emoji.common.h.c(localLatinIME, "KEY_RATE_PLUS_SHOW_TIME", 0L);
            if ((l1 == 0L) || (q(l1)))
            {
              com.emoji.common.h.b(localLatinIME, "KEY_RATE_PLUS_SHOW_TIME", System.currentTimeMillis());
              new Thread(new Runnable()
              {
                public final void run()
                {
                  ArrayList localArrayList1 = new ArrayList();
                  ArrayList localArrayList2 = new ArrayList();
                  ArrayList localArrayList3 = new ArrayList();
                  ArrayList localArrayList4 = new ArrayList();
                  new StringBuilder("plusInfo size: ").append(gy.f.a(MainApp.axG.fuL.fuU).ahb().list().size());
                  Iterator localIterator = LatinIME.this.getPackageManager().getInstalledPackages(0).iterator();
                  PackageInfo localPackageInfo;
                  String str2;
                  Object localObject1;
                  label212:
                  Context localContext;
                  for (;;)
                  {
                    if (localIterator.hasNext())
                    {
                      localPackageInfo = (PackageInfo)localIterator.next();
                      str2 = localPackageInfo.packageName;
                      int i = cd.b.bI(str2);
                      if (i != -1)
                      {
                        localObject1 = gy.f.a(MainApp.axG.fuL.fuU).a(PlusInfoDao.Properties.fvg.aJ(str2), new gy.h[0]).ahb().list();
                        if (((List)localObject1).size() > 0) {
                          localObject1 = (PlusInfo)((List)localObject1).get(0);
                        }
                        switch (i)
                        {
                        default: 
                          if (((PlusInfo)localObject1).fuZ == 0) {
                            if (((PlusInfo)localObject1).fva)
                            {
                              localArrayList1.add(localObject1);
                              continue;
                              localContext = com.emoji.common.d.u(LatinIME.this, str2);
                              localObject1 = null;
                            }
                          }
                          break;
                        }
                      }
                    }
                  }
                  for (;;)
                  {
                    try
                    {
                      String str1 = com.emoji.common.h.x(localContext, "displayName");
                      localObject1 = str1;
                      localObject1 = new PlusInfo(null, (String)localObject1, 0.0F, 0, false, str2, 0);
                    }
                    catch (Exception localException1)
                    {
                      break;
                    }
                    catch (IllegalAccessError localIllegalAccessError) {}
                    try
                    {
                      Object localObject3 = localContext.getPackageManager();
                      localObject3 = (String)((PackageManager)localObject3).getApplicationLabel(((PackageManager)localObject3).getPackageInfo(localContext.getPackageName(), 0).applicationInfo);
                      localObject2 = localObject3;
                    }
                    catch (Exception localException2) {}
                    ((PlusInfo)localObject2).fva = TextUtils.equals(localPackageInfo.packageName, this.bdK);
                    break label212;
                    ((PlusInfo)localObject2).fva = TextUtils.equals(localPackageInfo.packageName, this.bdL);
                    break label212;
                    ((PlusInfo)localObject2).fva = com.emoji.common.h.a(LatinIME.this, "enable_gif_" + localPackageInfo.packageName, Boolean.valueOf(true)).booleanValue();
                    break label212;
                    localArrayList2.add(localObject2);
                    break;
                    if (((PlusInfo)localObject2).fuZ != 1) {
                      break;
                    }
                    if (((PlusInfo)localObject2).fva)
                    {
                      localArrayList3.add(localObject2);
                      break;
                    }
                    localArrayList4.add(localObject2);
                    break;
                    new StringBuilder("A size :").append(localArrayList1.size());
                    new StringBuilder("B size :").append(localArrayList2.size());
                    new StringBuilder("C size :").append(localArrayList3.size());
                    new StringBuilder("D size :").append(localArrayList4.size());
                    Object localObject2 = new Random();
                    if (localArrayList1.size() > 0) {
                      localObject2 = (PlusInfo)localArrayList1.get(((Random)localObject2).nextInt(localArrayList1.size()));
                    }
                    for (;;)
                    {
                      if (localObject2 != null) {
                        LatinIME.a(LatinIME.this, (PlusInfo)localObject2);
                      }
                      return;
                      if (localArrayList2.size() > 0) {
                        localObject2 = (PlusInfo)localArrayList2.get(((Random)localObject2).nextInt(localArrayList2.size()));
                      } else if (localArrayList3.size() > 0) {
                        localObject2 = (PlusInfo)localArrayList3.get(((Random)localObject2).nextInt(localArrayList3.size()));
                      } else if (localArrayList4.size() > 0) {
                        localObject2 = (PlusInfo)localArrayList4.get(((Random)localObject2).nextInt(localArrayList4.size()));
                      } else {
                        localObject2 = null;
                      }
                    }
                  }
                }
              }).start();
            }
          }
          localD.bec = paramEditorInfo;
          break;
          bool1 = false;
          break label1181;
          bool1 = false;
          break label1210;
          bool1 = false;
          break label1239;
          i = 0;
          break label1499;
          i = 0;
          break label1507;
          bool1 = false;
          break label1519;
          if (bool1) {
            localLatinIME.bdh.oQ();
          }
          i = 1;
          break label1680;
          if (paramBoolean)
          {
            localKeyboardSwitcher.aMX.nz();
            localKeyboardSwitcher.lE();
          }
          break label1752;
          bool1 = false;
          break label1873;
          bool2 = false;
          break label1886;
          ag.qs();
          break label1995;
          MobclickAgent.onEvent(localLatinIME, "alpha_kb_raise_app_landscape", paramEditorInfo.packageName);
          break label2084;
          float f = com.emoji.common.h.b(localLatinIME, "KEY_RATE_APP_RATING", 0.0F);
          MobclickAgent.onEvent(localLatinIME, "app_rate_notification_times");
          com.emoji.common.h.c(localLatinIME, "KEY_RATE_APP_IS_SHOW_AND_CANCEL", false);
          localObject1 = new Intent(MainApp.axF, RateUsActivity.class);
          ((Intent)localObject1).putExtra("rate_type", 1);
          localObject3 = PendingIntent.getActivity(localLatinIME, 1, (Intent)localObject1, 134217728);
          localObject4 = new Intent();
          ((Intent)localObject4).setAction("rate_app_notification_cancel_action");
          localObject2 = (NotificationManager)localLatinIME.getSystemService("notification");
          if (f == 0.0F) {
            localObject1 = localLatinIME.getString(2131362209, new Object[] { "😘", localLatinIME.getString(2131362298), "😝" });
          }
          for (;;)
          {
            localObject1 = new NotificationCompat.Builder(localLatinIME).setContentTitle(localLatinIME.getString(2131362298)).setContentText((CharSequence)localObject1).setContentIntent((PendingIntent)localObject3).setDeleteIntent(PendingIntent.getBroadcast(localLatinIME, 0, (Intent)localObject4, 0)).setAutoCancel(true).setSmallIcon(2130837854).setWhen(System.currentTimeMillis());
            if (Build.VERSION.SDK_INT >= 21) {
              ((NotificationCompat.Builder)localObject1).setLargeIcon(((BitmapDrawable)ContextCompat.getDrawable(localLatinIME, 2130837854)).getBitmap()).setColor(ContextCompat.getColor(localLatinIME, 2131689639)).setSmallIcon(2130837895);
            }
            ((NotificationManager)localObject2).notify(6, ((NotificationCompat.Builder)localObject1).build());
            com.emoji.common.h.b(localLatinIME, "KEY_RATE_APP_SHOW_TIME", System.currentTimeMillis());
            bool1 = bool2;
            break;
            if (f >= 4.0F) {
              localObject1 = localLatinIME.getString(2131362256, new Object[] { "😘", "😝" });
            } else {
              localObject1 = localLatinIME.getString(2131362255, new Object[] { "😝", "✍" });
            }
          }
          bool1 = true;
        }
        localObject1 = this.bcv.text;
        l1 = this.bcv.time;
        paramBoolean = this.bcv.aXN;
      } while ((System.currentTimeMillis() - l1 <= 60000L) && (!paramBoolean));
    }
  }
  
  public void onTrimMemory(int paramInt)
  {
    super.onTrimMemory(paramInt);
    aw.i.ag(this).onTrimMemory(paramInt);
    if ((paramInt == 60) && (this.aJH != null)) {
      this.aJH.onLowMemory();
    }
  }
  
  public void onUpdateSelection(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    super.onUpdateSelection(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
    if (DEBUG) {
      Log.i(TAG, "onUpdateSelection: oss=" + paramInt1 + ", ose=" + paramInt2 + ", lss=" + this.bcT + ", lse=" + this.bcU + ", nss=" + paramInt3 + ", nse=" + paramInt4 + ", cs=" + paramInt5 + ", ce=" + paramInt6);
    }
    if (this.aJH.aMq != null) {}
    for (int i = 1; i != 0; i = 0) {
      return;
    }
    if (this.bck != null) {
      this.bck.kF();
    }
    Object localObject = this.aJH;
    if ((((KeyboardSwitcher)localObject).aMD != null) && (((KeyboardSwitcher)localObject).aMD.nQ()))
    {
      localObject = ((KeyboardSwitcher)localObject).aMD;
      if (((com.android.inputmethod.keyboard.translate.a)localObject).aZm != null)
      {
        localObject = ((com.android.inputmethod.keyboard.translate.a)localObject).aZm;
        if (((EditTextCursorWatcher)localObject).aZa != paramInt1)
        {
          i = 0;
          label222:
          if (i == 0) {
            break label515;
          }
          if ((this.bcT == paramInt3) && (this.bcU == paramInt4)) {
            break label517;
          }
          i = 1;
          label247:
          if ((paramInt5 != -1) || (paramInt6 != -1)) {
            break label523;
          }
          paramInt6 = 1;
          label262:
          if ((isInputViewShown()) && (!this.bcV))
          {
            localObject = this.bcR;
            if (paramInt3 != ((t)localObject).bei) {
              break label529;
            }
            paramInt5 = 1;
            label294:
            if (paramInt5 == 0)
            {
              this.bco = 0;
              if ((i == 0) && (this.bcQ.pj()) && (paramInt6 == 0)) {
                break label564;
              }
              paramInt5 = 1;
              label327:
              if ((paramInt1 == paramInt2) && (paramInt3 == paramInt4)) {
                break label570;
              }
              paramInt2 = 1;
              label340:
              if ((paramInt5 == 0) || ((paramInt2 == 0) && (this.bcQ.cq(paramInt3 - paramInt1)))) {
                break label575;
              }
              cf(paramInt3);
            }
          }
        }
      }
    }
    for (;;)
    {
      if (oE()) {
        this.bdh.oQ();
      }
      this.bcS.mIsActive = false;
      this.aJH.lE();
      this.bcV = false;
      this.bcT = paramInt3;
      this.bcU = paramInt4;
      this.bcH.bdS = true;
      return;
      if (((EditTextCursorWatcher)localObject).aZb != paramInt2)
      {
        i = 0;
        break label222;
      }
      if (((EditTextCursorWatcher)localObject).aZc != paramInt3)
      {
        i = 0;
        break label222;
      }
      if (((EditTextCursorWatcher)localObject).aZd != paramInt4)
      {
        i = 0;
        break label222;
      }
      if (((EditTextCursorWatcher)localObject).aZe != paramInt5)
      {
        i = 0;
        break label222;
      }
      if (((EditTextCursorWatcher)localObject).aZf != paramInt6)
      {
        i = 0;
        break label222;
      }
      i = 1;
      break label222;
      i = 1;
      break label222;
      i = 1;
      break label222;
      label515:
      break;
      label517:
      i = 0;
      break label247;
      label523:
      paramInt6 = 0;
      break label262;
      label529:
      if ((paramInt1 != ((t)localObject).bei) && ((((t)localObject).bei - paramInt3) * (paramInt3 - paramInt1) >= 0))
      {
        paramInt5 = 1;
        break label294;
      }
      paramInt5 = 0;
      break label294;
      label564:
      paramInt5 = 0;
      break label327;
      label570:
      paramInt2 = 0;
      break label340;
      label575:
      this.bcR.i(paramInt3, false);
    }
  }
  
  public void onViewClicked(boolean paramBoolean)
  {
    super.onViewClicked(paramBoolean);
    if (this.bcn != null) {
      this.bcn.onExtractedTextClicked();
    }
  }
  
  public void onWindowHidden()
  {
    super.onWindowHidden();
    MainKeyboardView localMainKeyboardView = this.aJH.aMW;
    if (localMainKeyboardView != null) {
      localMainKeyboardView.closing();
    }
  }
  
  public final InputMethodSubtype ou()
  {
    if (this.bcG == null) {
      return null;
    }
    return this.bcG.a(null);
  }
  
  public final int ow()
  {
    int i1 = 0;
    com.android.inputmethod.latin.settings.f localF = this.bcq.baj;
    if (!localF.biL) {}
    do
    {
      return 0;
      localObject = getCurrentInputEditorInfo();
    } while (localObject == null);
    int i2 = ((EditorInfo)localObject).inputType;
    Object localObject = this.bcR;
    if (4 == this.bco) {}
    for (int n = 1;; n = 0)
    {
      ((t)localObject).bep = ((t)localObject).beo.getCurrentInputConnection();
      if (((t)localObject).bep == null) {
        break;
      }
      if (TextUtils.isEmpty(((t)localObject).bek)) {
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
    if ((TextUtils.isEmpty(((t)localObject).bej)) && (((t)localObject).bei != 0))
    {
      CharSequence localCharSequence = ((t)localObject).ck(1024);
      if (!TextUtils.isEmpty(localCharSequence)) {
        ((t)localObject).bej.append(localCharSequence);
      }
    }
    localObject = ((t)localObject).bej;
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
      if (Locale.ENGLISH.getLanguage().equals(localF.mLocale.getLanguage())) {
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
      if ((localF.biI != k) || (m <= 0)) {
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
          } else if (localF.biI == i) {
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
          } else if (localF.biI == i) {
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
  
  public final int ox()
  {
    if ((!this.bcS.mIsActive) || (!this.bcS.an(this.bcT, this.bcU))) {
      return -1;
    }
    com.android.inputmethod.latin.utils.t localT = this.bcS;
    return com.android.inputmethod.latin.utils.t.bno[localT.bnt];
  }
  
  public void setInputView(View paramView)
  {
    super.setInputView(paramView);
    this.bcA = getWindow().getWindow().getDecorView().findViewById(16908316);
    this.bcB = paramView.findViewById(2131755604);
    this.aMo = ((SuggestionStripView)paramView.findViewById(2131755611));
    if (this.aMo != null)
    {
      SuggestionStripView localSuggestionStripView = this.aMo;
      localSuggestionStripView.blO = this;
      localSuggestionStripView.blK = ((MainKeyboardView)paramView.findViewById(2131755616));
      this.bdq.a(this.aMo);
    }
    oG();
    if (this.bdr != null)
    {
      this.bdr.recycle();
      this.bdr = null;
    }
    if (this.bcz != null)
    {
      this.bdh.removeMessages(1011);
      this.bcz.recycle();
      this.bcz = null;
    }
    if (r.bee) {
      this.bcB.setBackgroundColor(285147136);
    }
  }
  
  public void updateFullscreenMode()
  {
    super.updateFullscreenMode();
    if (this.bcB == null) {
      return;
    }
    Object localObject = this.bcB;
    if (isFullscreenMode()) {}
    for (int i = 8;; i = 0)
    {
      ((View)localObject).setVisibility(i);
      if (this.aJH == null) {
        break;
      }
      localObject = this.aJH;
      isFullscreenMode();
      if (((KeyboardSwitcher)localObject).aMn != null) {
        ((KeyboardSwitcher)localObject).aMn.nh();
      }
      ((KeyboardSwitcher)localObject).ly();
      return;
    }
  }
  
  public class CustomThemeUpdateReceiver
    extends BroadcastReceiver
  {
    public CustomThemeUpdateReceiver(Context paramContext)
    {
      this$1 = new IntentFilter();
      LatinIME.this.addAction("com.setting.custom_theme_action");
      LatinIME.this.addAction("ACTION_CUSTOM_THEME_KEY_OPACITY");
      LatinIME.this.addAction("ACTION_CUSTOM_THEME_KEY_COLOR");
      LatinIME.this.addAction("ACTION_CUSTOM_THEME_KEY_SHAPE");
      LatinIME.this.addAction("ACTION_CUSTOM_THEME_KEY_STYLE");
      LatinIME.this.addAction("ACTION_CUSTOM_THEME_FONT");
      LatinIME.this.addAction("ACTION_CUSTOM_THEME_FONT_COLOR");
      LatinIME.this.addAction("ACTION_CUSTOM_BG_KEY_OPACITY");
      LatinIME.this.addAction("ACTION_CUSTOM_THEME_KB_BG");
      LatinIME.this.addAction("ACTION_CUSTOM_THEME_SWIPE_COLOR");
      LatinIME.this.addAction("ACTION_CUSTOM_THEME_SWIPE_OPACITY");
      LatinIME.this.addAction("ACTION_CUSTOM_THEME_SWIPE_SIZE");
      paramContext.registerReceiver(this, LatinIME.this);
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((paramIntent == null) || (TextUtils.isEmpty(paramIntent.getAction()))) {}
      label132:
      int i;
      label134:
      label198:
      do
      {
        return;
        localObject1 = paramIntent.getAction();
        switch (((String)localObject1).hashCode())
        {
        default: 
          i = -1;
          switch (i)
          {
          default: 
            paramContext = null;
          }
          break;
        }
      } while (paramContext == null);
      Object localObject1 = LatinIME.this.bdq;
      Object localObject2;
      if (localObject1 != null) {
        localObject2 = paramIntent.getAction();
      }
      switch (((String)localObject2).hashCode())
      {
      default: 
        label252:
        i = -1;
        switch (i)
        {
        default: 
          label254:
          label276:
          if (LatinIME.c(LatinIME.this) != null)
          {
            localObject1 = LatinIME.c(LatinIME.this);
            localObject2 = paramIntent.getAction();
            if (((KeyboardSwitcher)localObject1).aMW != null) {
              ((KeyboardSwitcher)localObject1).aMW.a((String)localObject2, paramContext);
            }
            if (localObject2 != null) {
              switch (((String)localObject2).hashCode())
              {
              default: 
                label356:
                i = -1;
                label358:
                switch (i)
                {
                }
                break;
              }
            }
          }
          break;
        }
        break;
      }
      for (;;)
      {
        localObject1 = LatinIME.j(LatinIME.this);
        if (localObject1 == null) {
          break;
        }
        ((SuggestionStripView)localObject1).a(paramIntent.getAction(), paramContext);
        return;
        if (!((String)localObject1).equals("com.setting.custom_theme_action")) {
          break label132;
        }
        i = 0;
        break label134;
        if (!((String)localObject1).equals("ACTION_CUSTOM_THEME_FONT_COLOR")) {
          break label132;
        }
        i = 1;
        break label134;
        if (!((String)localObject1).equals("ACTION_CUSTOM_THEME_SWIPE_COLOR")) {
          break label132;
        }
        i = 2;
        break label134;
        if (!((String)localObject1).equals("ACTION_CUSTOM_THEME_SWIPE_OPACITY")) {
          break label132;
        }
        i = 3;
        break label134;
        if (!((String)localObject1).equals("ACTION_CUSTOM_THEME_SWIPE_SIZE")) {
          break label132;
        }
        i = 4;
        break label134;
        if (!((String)localObject1).equals("ACTION_CUSTOM_THEME_KEY_COLOR")) {
          break label132;
        }
        i = 5;
        break label134;
        if (!((String)localObject1).equals("ACTION_CUSTOM_THEME_KEY_STYLE")) {
          break label132;
        }
        i = 6;
        break label134;
        if (!((String)localObject1).equals("ACTION_CUSTOM_THEME_KEY_SHAPE")) {
          break label132;
        }
        i = 7;
        break label134;
        if (!((String)localObject1).equals("ACTION_CUSTOM_THEME_KEY_OPACITY")) {
          break label132;
        }
        i = 8;
        break label134;
        if (!((String)localObject1).equals("ACTION_CUSTOM_THEME_FONT")) {
          break label132;
        }
        i = 9;
        break label134;
        if (!((String)localObject1).equals("ACTION_CUSTOM_THEME_KB_BG")) {
          break label132;
        }
        i = 10;
        break label134;
        if (!((String)localObject1).equals("ACTION_CUSTOM_BG_KEY_OPACITY")) {
          break label132;
        }
        i = 11;
        break label134;
        LatinIME.a(LatinIME.this, paramContext, "preview_kb_" + System.currentTimeMillis());
        paramContext = null;
        break label198;
        paramContext = (CustomTheme)paramIntent.getParcelableExtra("KEY_CUSTOM_THEME");
        new StringBuilder("ACTION_CUSTOM_THEME_KEY_OPACITY onReceive").append(paramContext.fui);
        break label198;
        paramContext = (CustomWallpaper)paramIntent.getParcelableExtra("KEY_CUSTOM_BG");
        break label198;
        if (!((String)localObject2).equals("ACTION_CUSTOM_THEME_FONT_COLOR")) {
          break label252;
        }
        i = 0;
        break label254;
        if (!((String)localObject2).equals("ACTION_CUSTOM_THEME_KB_BG")) {
          break label252;
        }
        i = 1;
        break label254;
        ((cl.b)localObject1).dS(paramContext.uG());
        break label276;
        i = paramContext.uK();
        localObject2 = new cl.b.a((cl.b)localObject1, 1, "kb_bg");
        if (!(((cl.b)localObject1).bKZ.get(localObject2) instanceof ColorDrawable)) {
          break label276;
        }
        ((cl.b)localObject1).bKW = i;
        break label276;
        if (!((String)localObject2).equals("ACTION_CUSTOM_THEME_FONT_COLOR")) {
          break label356;
        }
        i = 0;
        break label358;
        if (!((String)localObject2).equals("ACTION_CUSTOM_THEME_KB_BG")) {
          break label356;
        }
        i = 1;
        break label358;
        if (((KeyboardSwitcher)localObject1).aMY != null) {
          com.android.inputmethod.keyboard.o.lt();
        }
        ((KeyboardSwitcher)localObject1).ma();
        continue;
        if (((((KeyboardSwitcher)localObject1).aNl instanceof ColorDrawable)) && (((ColorDrawable)((KeyboardSwitcher)localObject1).aNl).getColor() != paramContext.uK()))
        {
          if (((KeyboardSwitcher)localObject1).aMM != null) {
            ((KeyboardSwitcher)localObject1).aMM.cancel();
          }
          ((KeyboardSwitcher)localObject1).aMM = com.emoji.common.h.a(localObject1, "keyboardBgColor", new int[] { ((ColorDrawable)((KeyboardSwitcher)localObject1).aNl).getColor(), paramContext.uK() });
          ((KeyboardSwitcher)localObject1).aMM.start();
        }
      }
    }
  }
  
  public class GifListChangeLocalReceiver
    extends BroadcastReceiver
  {
    private GifListChangeLocalReceiver(Context paramContext)
    {
      this$1 = new IntentFilter();
      LatinIME.this.addAction("keyboard.giflist.change");
      LatinIME.this.addAction("ACTION_DICT_DOWNLOADED_AND_DECOMPRESSED");
      LocalBroadcastManager.getInstance(paramContext).registerReceiver(this, LatinIME.this);
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (TextUtils.isEmpty(paramIntent.getAction())) {}
      label10:
      do
      {
        return;
        localObject = paramIntent.getAction();
        int i = -1;
        switch (((String)localObject).hashCode())
        {
        }
        for (;;)
        {
          switch (i)
          {
          default: 
            return;
          case 0: 
            localObject = LatinIME.i(LatinIME.this);
            if (localObject == null) {
              break label10;
            }
            ((com.android.inputmethod.latin.suggestions.d)localObject).c(paramContext, paramIntent);
            return;
            if (((String)localObject).equals("keyboard.giflist.change"))
            {
              i = 0;
              continue;
              if (((String)localObject).equals("ACTION_DICT_DOWNLOADED_AND_DECOMPRESSED")) {
                i = 1;
              }
            }
            break;
          }
        }
        paramIntent = paramIntent.getStringExtra("EXTRA_LOCALE");
      } while (TextUtils.isEmpty(paramIntent));
      Object localObject = LatinIME.this;
      Locale localLocale = ((LatinIME)localObject).aMQ.pe();
      w localW = ((LatinIME)localObject).bcD;
      if (localW != null)
      {
        localW.a((Context)localObject, localLocale, (w.c)localObject);
        ((LatinIME)localObject).bcJ = j.c((Context)localObject, localLocale);
      }
      new StringBuilder("发送统计事件----》dict_list_download_success ").append(paramIntent.toLowerCase());
      MobclickAgent.onEvent(paramContext, "dict_list_download_success", paramIntent.toLowerCase());
    }
  }
  
  public class PackageManReceiver
    extends BroadcastReceiver
  {
    public PackageManReceiver(Context paramContext)
    {
      this$1 = new IntentFilter();
      LatinIME.this.addAction("android.intent.action.PACKAGE_ADDED");
      LatinIME.this.addAction("android.intent.action.PACKAGE_REMOVED");
      LatinIME.this.addDataScheme("package");
      paramContext.registerReceiver(this, LatinIME.this);
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((!paramIntent.getAction().equals("android.intent.action.PACKAGE_ADDED")) && (!paramIntent.getAction().equals("android.intent.action.PACKAGE_REMOVED"))) {
        return;
      }
      String str = paramIntent.getDataString().substring(8);
      com.android.inputmethod.latin.suggestions.d localD = LatinIME.i(LatinIME.this);
      if (localD != null) {
        localD.c(paramContext, paramIntent);
      }
      if ((cl.b.bT(str)) && (LatinIME.this.bdq != null))
      {
        paramIntent = LatinIME.this.bdq;
        paramIntent.bKz = null;
        paramIntent.bV(paramIntent.bKK);
      }
      com.emoji.common.h.c(paramContext, "local_push_loop_count", 0);
    }
  }
  
  public class RateUsNotificationCancelReceiver
    extends BroadcastReceiver
  {
    public RateUsNotificationCancelReceiver(Context paramContext)
    {
      this$1 = new IntentFilter();
      LatinIME.this.addAction("rate_app_notification_cancel_action");
      LatinIME.this.addAction("rate_plus_notification_cancel_action");
      paramContext.registerReceiver(this, LatinIME.this);
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction() == "rate_app_notification_cancel_action") {
        com.emoji.common.h.c(paramContext, "KEY_RATE_APP_IS_SHOW_AND_CANCEL", true);
      }
      while (paramIntent.getAction() != "rate_plus_notification_cancel_action") {
        return;
      }
      paramContext = (PlusInfo)paramIntent.getParcelableExtra("plus_info");
      paramIntent = Long.valueOf(paramIntent.getLongExtra("id", 0L));
      new StringBuilder("plusName:").append(paramContext.fuX);
      if (paramContext.fvb >= 2) {
        paramContext.fuZ = 2;
      }
      if (paramIntent.longValue() == 0L) {}
      for (paramContext.aXM = null;; paramContext.aXM = paramIntent)
      {
        MainApp.axG.a(paramContext);
        return;
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void onExtractedTextClicked();
  }
  
  private static final class b
    implements Handler.Callback
  {
    final LatinIME aIJ;
    boolean bdN;
    final Handler mHandler;
    final Object mLock = new Object();
    
    private b(LatinIME paramLatinIME)
    {
      HandlerThread localHandlerThread = new HandlerThread(b.class.getSimpleName());
      localHandlerThread.start();
      this.mHandler = new Handler(localHandlerThread.getLooper(), this);
      this.aIJ = paramLatinIME;
    }
    
    final void a(p paramP, final w.b paramB)
    {
      ab localAb = LatinIME.m(this.aIJ);
      p localP = localAb.bch;
      localP.aSf.a(paramP.aSf);
      localP.aSg.a(paramP.aSg);
      localP.bbT.a(paramP.bbT);
      localP.bbU.a(paramP.bbU);
      localAb.bfw = true;
      LatinIME.a(this.aIJ, new w.b()
      {
        public final void d(x paramAnonymousX)
        {
          int i = paramAnonymousX.bff.size();
          if (i <= 1) {
            if (i != 0) {
              break label50;
            }
          }
          label50:
          for (String str = null;; str = paramAnonymousX.cm(0))
          {
            paramB.d(LatinIME.a(LatinIME.this, str));
            paramB.d(paramAnonymousX);
            return;
          }
        }
      });
    }
    
    public final void a(CharSequence paramCharSequence, w.b paramB)
    {
      a localA = new a();
      localA.bdQ = paramB;
      localA.bdP = paramCharSequence;
      this.mHandler.obtainMessage(2, 0, 0, localA).sendToTarget();
    }
    
    public final boolean handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      }
      for (;;)
      {
        return true;
        p localP1 = (p)paramMessage.obj;
        paramMessage = this.mLock;
        if (paramMessage != null)
        {
          try
          {
            if (!this.bdN) {
              continue;
            }
          }
          finally {}
          a(localP2, new w.b()
          {
            public final void d(x paramAnonymousX)
            {
              LatinIME.this.bdh.b(paramAnonymousX, false);
            }
          });
          continue;
          a localA = (a)paramMessage.obj;
          LatinIME.a(this.aIJ, paramMessage.arg1, localA.bdP, localA.bdQ);
        }
      }
    }
    
    final class a
    {
      CharSequence bdP;
      w.b bdQ;
      
      a() {}
    }
  }
  
  static final class c
  {
    InputMethodSubtype bdR;
    boolean bdS;
    
    c() {}
  }
  
  public static final class d
    extends y<LatinIME>
  {
    int bdT;
    int bdU;
    long bdV;
    long bdW;
    boolean bdX;
    boolean bdY;
    boolean bdZ;
    boolean bea;
    boolean beb;
    EditorInfo bec;
    
    public d(LatinIME paramLatinIME)
    {
      super();
    }
    
    final void b(LatinIME paramLatinIME, EditorInfo paramEditorInfo, boolean paramBoolean)
    {
      if (this.bea) {
        LatinIME.a(paramLatinIME, this.beb);
      }
      if (this.beb) {
        LatinIME.h(paramLatinIME);
      }
      if (this.bdZ) {
        LatinIME.a(paramLatinIME, paramEditorInfo, paramBoolean);
      }
      oR();
    }
    
    public final void b(x paramX, boolean paramBoolean)
    {
      removeMessages(3);
      if (paramBoolean) {}
      for (int i = 1;; i = 2)
      {
        obtainMessage(3, i, 0, paramX).sendToTarget();
        return;
      }
    }
    
    public final void b(boolean paramBoolean, int paramInt)
    {
      removeMessages(7);
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        sendMessage(obtainMessage(7, i, paramInt, null));
        return;
      }
    }
    
    public final void handleMessage(Message paramMessage)
    {
      boolean bool2 = true;
      boolean bool1 = true;
      LatinIME localLatinIME = (LatinIME)this.bnD.get();
      Object localObject = LatinIME.c(localLatinIME);
      switch (paramMessage.what)
      {
      }
      do
      {
        return;
        LatinIME.d(localLatinIME);
        return;
        ((KeyboardSwitcher)localObject).lE();
        return;
        if (paramMessage.arg1 == 0)
        {
          if (paramMessage.arg2 == 1)
          {
            paramMessage = (Pair)paramMessage.obj;
            LatinIME.a(localLatinIME, (x)paramMessage.first, (String)paramMessage.second);
            return;
          }
          LatinIME.a(localLatinIME, (x)paramMessage.obj);
          return;
        }
        localObject = (x)paramMessage.obj;
        if (paramMessage.arg1 == 1) {}
        for (;;)
        {
          LatinIME.a(localLatinIME, (x)localObject, bool1);
          return;
          bool1 = false;
        }
        LatinIME.e(localLatinIME);
        return;
        LatinIME.f(localLatinIME);
        oP();
        return;
        localLatinIME.b((x)paramMessage.obj);
        return;
        if (paramMessage.arg1 == 1) {}
        for (bool1 = bool2;; bool1 = false)
        {
          LatinIME.a(localLatinIME, bool1, paramMessage.arg2);
          return;
        }
        localLatinIME.bcw -= 1000L;
        if (localLatinIME.bcw >= 0L) {
          break;
        }
      } while (LatinIME.g(localLatinIME) == null);
      LatinIME.g(localLatinIME).hide();
      removeMessages(1011);
      return;
      sendEmptyMessageDelayed(1011, 1000L);
    }
    
    public final void oP()
    {
      sendMessageDelayed(obtainMessage(2), this.bdT);
    }
    
    public final void oQ()
    {
      removeMessages(4);
      sendMessageDelayed(obtainMessage(4), this.bdT);
    }
    
    final void oR()
    {
      this.bea = false;
      this.beb = false;
      this.bdZ = false;
    }
  }
  
  public static abstract interface e
  {
    public abstract void kF();
  }
}
