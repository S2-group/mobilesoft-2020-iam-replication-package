package cn.ninebot.libraries.h;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.ClipboardManager;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import cn.ninebot.libraries.R.color;
import cn.ninebot.libraries.R.drawable;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class q
{
  public static float a(Context paramContext, float paramFloat)
  {
    if (paramContext == null) {
      return paramFloat;
    }
    return paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F;
  }
  
  public static int a(int paramInt, float paramFloat)
  {
    float f;
    if (paramFloat < 0.0F)
    {
      f = 0.0F;
    }
    else
    {
      f = paramFloat;
      if (paramFloat > 1.0F) {
        f = 1.0F;
      }
    }
    int i = Color.red(paramInt);
    int j = Color.green(paramInt);
    paramInt = Color.blue(paramInt);
    return Color.argb((int)(f * 255.0F), i, j, paramInt);
  }
  
  public static int a(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public static String a(float paramFloat)
  {
    return new DecimalFormat("######0.0").format(paramFloat);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setText(paramString);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, TextView paramTextView)
  {
    paramContext = new ForegroundColorSpan(paramContext.getResources().getColor(R.color.color_key_color));
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramString2);
    int i = paramString2.indexOf(paramString1.toLowerCase());
    int j = paramString2.indexOf(paramString1.toUpperCase());
    if (i != -1) {
      localSpannableStringBuilder.setSpan(paramContext, i, paramString1.length() + i, 33);
    }
    for (;;)
    {
      paramTextView.setText(localSpannableStringBuilder);
      return;
      if (j == -1) {
        break;
      }
      localSpannableStringBuilder.setSpan(paramContext, j, paramString1.length() + j, 33);
    }
    paramTextView.setText(paramString2);
  }
  
  public static void a(InputMethodManager paramInputMethodManager, Activity paramActivity)
  {
    if ((paramInputMethodManager != null) && (paramInputMethodManager.isActive()) && (paramActivity.getCurrentFocus() != null)) {
      paramInputMethodManager.hideSoftInputFromWindow(paramActivity.getCurrentFocus().getWindowToken(), 0);
    }
  }
  
  public static boolean a(String paramString)
  {
    return (paramString == null) || (TextUtils.isEmpty(paramString.trim())) || (TextUtils.equals(paramString.trim(), ""));
  }
  
  public static float b(Context paramContext, float paramFloat)
  {
    return paramFloat * paramContext.getResources().getDisplayMetrics().scaledDensity;
  }
  
  public static int b(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static String b(String paramString)
  {
    int j = paramString.lastIndexOf("@");
    String str = "";
    int i = 0;
    while (i < j - 2)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("*");
      str = localStringBuilder.toString();
      i += 1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString.substring(0, 1));
    localStringBuilder.append(str);
    localStringBuilder.append(paramString.substring(j - 1, paramString.length()));
    return localStringBuilder.toString();
  }
  
  public static final boolean b(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static int c(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals(paramString)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static boolean c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Pattern.compile("[0-9]*").matcher(paramString).matches();
  }
  
  public static String d(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static boolean d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Pattern.compile("[a-fA-F0-9]*").matcher(paramString).matches();
  }
  
  public static boolean e(Context paramContext)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(100).iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)paramContext.next();
      if ((localRunningTaskInfo.topActivity.getPackageName().equals("cn.ninebot.ninebot")) && (localRunningTaskInfo.baseActivity.getPackageName().equals("cn.ninebot.ninebot"))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean e(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Pattern.compile("[a-zA-Z0-9]*").matcher(paramString).matches();
  }
  
  public static int f(String paramString)
  {
    if ((!paramString.isEmpty()) && (paramString.contains("="))) {
      return Integer.parseInt(paramString.substring(paramString.indexOf("=") + 1, paramString.indexOf("&")));
    }
    return 0;
  }
  
  public static int g(String paramString)
  {
    if ((!paramString.isEmpty()) && (paramString.contains("="))) {
      return Integer.parseInt(paramString.substring(paramString.lastIndexOf("=") + 1, paramString.length()));
    }
    return 0;
  }
  
  public static String h(String paramString)
  {
    double d = j(paramString);
    if (d == 0.0D) {
      return String.valueOf(0);
    }
    return new DecimalFormat("######0.0").format(d / 1024.0D / 1024.0D);
  }
  
  public static int i(String paramString)
  {
    if (paramString == null) {
      return 0;
    }
    if (paramString.equals("CN")) {
      return R.drawable.cn;
    }
    if (paramString.equals("AC")) {
      return R.drawable.ac;
    }
    if (paramString.equals("AD")) {
      return R.drawable.ad;
    }
    if (paramString.equals("AE")) {
      return R.drawable.ae;
    }
    if (paramString.equals("AF")) {
      return R.drawable.af;
    }
    if (paramString.equals("AG")) {
      return R.drawable.ag;
    }
    if (paramString.equals("AI")) {
      return R.drawable.ai;
    }
    if (paramString.equals("AL")) {
      return R.drawable.al;
    }
    if (paramString.equals("AM")) {
      return R.drawable.am;
    }
    if (paramString.equals("AN")) {
      return R.drawable.an;
    }
    if (paramString.equals("AO")) {
      return R.drawable.ao;
    }
    if (paramString.equals("AQ")) {
      return R.drawable.aq;
    }
    if (paramString.equals("AR")) {
      return R.drawable.ar;
    }
    if (paramString.equals("AS")) {
      return R.drawable.as;
    }
    if (paramString.equals("AT")) {
      return R.drawable.at;
    }
    if (paramString.equals("AU")) {
      return R.drawable.au;
    }
    if (paramString.equals("AW")) {
      return R.drawable.aw;
    }
    if (paramString.equals("AX")) {
      return R.drawable.ax;
    }
    if (paramString.equals("AZ")) {
      return R.drawable.az;
    }
    if (paramString.equals("BA")) {
      return R.drawable.ba;
    }
    if (paramString.equals("BB")) {
      return R.drawable.bb;
    }
    if (paramString.equals("BD")) {
      return R.drawable.bd;
    }
    if (paramString.equals("BE")) {
      return R.drawable.be;
    }
    if (paramString.equals("BF")) {
      return R.drawable.bf;
    }
    if (paramString.equals("BG")) {
      return R.drawable.bg;
    }
    if (paramString.equals("BH")) {
      return R.drawable.bh;
    }
    if (paramString.equals("BI")) {
      return R.drawable.bi;
    }
    if (paramString.equals("BJ")) {
      return R.drawable.bj;
    }
    if (paramString.equals("BM")) {
      return R.drawable.bm;
    }
    if (paramString.equals("BN")) {
      return R.drawable.bn;
    }
    if (paramString.equals("BO")) {
      return R.drawable.bo;
    }
    if (paramString.equals("BR")) {
      return R.drawable.br;
    }
    if (paramString.equals("BS")) {
      return R.drawable.bs;
    }
    if (paramString.equals("BT")) {
      return R.drawable.bt;
    }
    if (paramString.equals("BV")) {
      return R.drawable.bv;
    }
    if (paramString.equals("BW")) {
      return R.drawable.bw;
    }
    if (paramString.equals("BY")) {
      return R.drawable.by;
    }
    if (paramString.equals("BZ")) {
      return R.drawable.bz;
    }
    if (paramString.equals("CA")) {
      return R.drawable.ca;
    }
    if (paramString.equals("CC")) {
      return R.drawable.cc;
    }
    if (paramString.equals("CD")) {
      return R.drawable.cd;
    }
    if (paramString.equals("CF")) {
      return R.drawable.cf;
    }
    if (paramString.equals("CG")) {
      return R.drawable.cg;
    }
    if (paramString.equals("CH")) {
      return R.drawable.ch;
    }
    if (paramString.equals("CI")) {
      return R.drawable.ci;
    }
    if (paramString.equals("CK")) {
      return R.drawable.ck;
    }
    if (paramString.equals("CL")) {
      return R.drawable.cl;
    }
    if (paramString.equals("CM")) {
      return R.drawable.cm;
    }
    if (paramString.equals("CO")) {
      return R.drawable.co;
    }
    if (paramString.equals("CR")) {
      return R.drawable.cr;
    }
    if (paramString.equals("CU")) {
      return R.drawable.cu;
    }
    if (paramString.equals("CV")) {
      return R.drawable.cv;
    }
    if (paramString.equals("CX")) {
      return R.drawable.cx;
    }
    if (paramString.equals("CY")) {
      return R.drawable.cy;
    }
    if (paramString.equals("CZ")) {
      return R.drawable.cz;
    }
    if (paramString.equals("DE")) {
      return R.drawable.de;
    }
    if (paramString.equals("DJ")) {
      return R.drawable.dj;
    }
    if (paramString.equals("DK")) {
      return R.drawable.dk;
    }
    if (paramString.equals("DM")) {
      return R.drawable.dm;
    }
    if (paramString.equals("DO")) {
      return R.drawable.do_;
    }
    if (paramString.equals("DZ")) {
      return R.drawable.dz;
    }
    if (paramString.equals("EC")) {
      return R.drawable.ec;
    }
    if (paramString.equals("EE")) {
      return R.drawable.ee;
    }
    if (paramString.equals("EG")) {
      return R.drawable.eg;
    }
    if (paramString.equals("EH")) {
      return R.drawable.eh;
    }
    if (paramString.equals("ER")) {
      return R.drawable.er;
    }
    if (paramString.equals("ES")) {
      return R.drawable.es;
    }
    if (paramString.equals("ET")) {
      return R.drawable.et;
    }
    if (paramString.equals("FI")) {
      return R.drawable.fi;
    }
    if (paramString.equals("FJ")) {
      return R.drawable.fj;
    }
    if (paramString.equals("FK")) {
      return R.drawable.fk;
    }
    if (paramString.equals("FM")) {
      return R.drawable.fm;
    }
    if (paramString.equals("FO")) {
      return R.drawable.fo;
    }
    if (paramString.equals("FR")) {
      return R.drawable.fr;
    }
    if (paramString.equals("FX")) {
      return R.drawable.fx;
    }
    if (paramString.equals("GA")) {
      return R.drawable.ga;
    }
    if (paramString.equals("GB")) {
      return R.drawable.gb;
    }
    if (paramString.equals("GD")) {
      return R.drawable.gd;
    }
    if (paramString.equals("GE")) {
      return R.drawable.ge;
    }
    if (paramString.equals("GF")) {
      return R.drawable.gf;
    }
    if (paramString.equals("GG")) {
      return R.drawable.gg;
    }
    if (paramString.equals("GH")) {
      return R.drawable.gh;
    }
    if (paramString.equals("GI")) {
      return R.drawable.gi;
    }
    if (paramString.equals("GL")) {
      return R.drawable.gl;
    }
    if (paramString.equals("GM")) {
      return R.drawable.gm;
    }
    if (paramString.equals("GN")) {
      return R.drawable.gn;
    }
    if (paramString.equals("GP")) {
      return R.drawable.gp;
    }
    if (paramString.equals("GQ")) {
      return R.drawable.gq;
    }
    if (paramString.equals("GR")) {
      return R.drawable.gr;
    }
    if (paramString.equals("GS")) {
      return R.drawable.gs;
    }
    if (paramString.equals("GT")) {
      return R.drawable.gt;
    }
    if (paramString.equals("GU")) {
      return R.drawable.gu;
    }
    if (paramString.equals("GW")) {
      return R.drawable.gw;
    }
    if (paramString.equals("GY")) {
      return R.drawable.gy;
    }
    if (paramString.equals("HK")) {
      return R.drawable.hk;
    }
    if (paramString.equals("HM")) {
      return R.drawable.hm;
    }
    if (paramString.equals("HN")) {
      return R.drawable.hn;
    }
    if (paramString.equals("HR")) {
      return R.drawable.hr;
    }
    if (paramString.equals("HT")) {
      return R.drawable.ht;
    }
    if (paramString.equals("HU")) {
      return R.drawable.hu;
    }
    if (paramString.equals("ID")) {
      return R.drawable.id;
    }
    if (paramString.equals("IE")) {
      return R.drawable.ie;
    }
    if (paramString.equals("IL")) {
      return R.drawable.il;
    }
    if (paramString.equals("IM")) {
      return R.drawable.im;
    }
    if (paramString.equals("IN")) {
      return R.drawable.in;
    }
    if (paramString.equals("IO")) {
      return R.drawable.io;
    }
    if (paramString.equals("IQ")) {
      return R.drawable.iq;
    }
    if (paramString.equals("IR")) {
      return R.drawable.ir;
    }
    if (paramString.equals("IS")) {
      return R.drawable.is;
    }
    if (paramString.equals("IT")) {
      return R.drawable.it;
    }
    if (paramString.equals("JE")) {
      return R.drawable.je;
    }
    if (paramString.equals("JM")) {
      return R.drawable.jm;
    }
    if (paramString.equals("JO")) {
      return R.drawable.jo;
    }
    if (paramString.equals("JP")) {
      return R.drawable.jp;
    }
    if (paramString.equals("KE")) {
      return R.drawable.ke;
    }
    if (paramString.equals("KG")) {
      return R.drawable.kg;
    }
    if (paramString.equals("KH")) {
      return R.drawable.kh;
    }
    if (paramString.equals("KI")) {
      return R.drawable.ki;
    }
    if (paramString.equals("KM")) {
      return R.drawable.km;
    }
    if (paramString.equals("KN")) {
      return R.drawable.kn;
    }
    if (paramString.equals("KP")) {
      return R.drawable.kp;
    }
    if (paramString.equals("KR")) {
      return R.drawable.kr;
    }
    if (paramString.equals("KW")) {
      return R.drawable.kw;
    }
    if (paramString.equals("KY")) {
      return R.drawable.ky;
    }
    if (paramString.equals("KZ")) {
      return R.drawable.kz;
    }
    if (paramString.equals("LA")) {
      return R.drawable.la;
    }
    if (paramString.equals("LB")) {
      return R.drawable.lb;
    }
    if (paramString.equals("LC")) {
      return R.drawable.lc;
    }
    if (paramString.equals("LI")) {
      return R.drawable.li;
    }
    if (paramString.equals("LK")) {
      return R.drawable.lk;
    }
    if (paramString.equals("LR")) {
      return R.drawable.lr;
    }
    if (paramString.equals("LS")) {
      return R.drawable.ls;
    }
    if (paramString.equals("LT")) {
      return R.drawable.lt;
    }
    if (paramString.equals("LU")) {
      return R.drawable.lu;
    }
    if (paramString.equals("LV")) {
      return R.drawable.lv;
    }
    if (paramString.equals("LY")) {
      return R.drawable.ly;
    }
    if (paramString.equals("MA")) {
      return R.drawable.ma;
    }
    if (paramString.equals("MC")) {
      return R.drawable.mc;
    }
    if (paramString.equals("MD")) {
      return R.drawable.md;
    }
    if (paramString.equals("ME")) {
      return R.drawable.me;
    }
    if (paramString.equals("MF")) {
      return R.drawable.mf;
    }
    if (paramString.equals("MG")) {
      return R.drawable.mg;
    }
    if (paramString.equals("MH")) {
      return R.drawable.mh;
    }
    if (paramString.equals("MK")) {
      return R.drawable.mk;
    }
    if (paramString.equals("ML")) {
      return R.drawable.ml;
    }
    if (paramString.equals("MM")) {
      return R.drawable.mm;
    }
    if (paramString.equals("MN")) {
      return R.drawable.mn;
    }
    if (paramString.equals("MO")) {
      return R.drawable.mo;
    }
    if (paramString.equals("MP")) {
      return R.drawable.mp;
    }
    if (paramString.equals("MQ")) {
      return R.drawable.mq;
    }
    if (paramString.equals("MR")) {
      return R.drawable.mr;
    }
    if (paramString.equals("MS")) {
      return R.drawable.ms;
    }
    if (paramString.equals("MT")) {
      return R.drawable.mt;
    }
    if (paramString.equals("MU")) {
      return R.drawable.mu;
    }
    if (paramString.equals("MV")) {
      return R.drawable.mv;
    }
    if (paramString.equals("MW")) {
      return R.drawable.mw;
    }
    if (paramString.equals("MX")) {
      return R.drawable.mx;
    }
    if (paramString.equals("MY")) {
      return R.drawable.my;
    }
    if (paramString.equals("MZ")) {
      return R.drawable.mz;
    }
    if (paramString.equals("NA")) {
      return R.drawable.na;
    }
    if (paramString.equals("NC")) {
      return R.drawable.nc;
    }
    if (paramString.equals("NE")) {
      return R.drawable.ne;
    }
    if (paramString.equals("NF")) {
      return R.drawable.nf;
    }
    if (paramString.equals("NG")) {
      return R.drawable.ng;
    }
    if (paramString.equals("NI")) {
      return R.drawable.ni;
    }
    if (paramString.equals("NL")) {
      return R.drawable.nl;
    }
    if (paramString.equals("NO")) {
      return R.drawable.no;
    }
    if (paramString.equals("NP")) {
      return R.drawable.np;
    }
    if (paramString.equals("NR")) {
      return R.drawable.nr;
    }
    if (paramString.equals("NU")) {
      return R.drawable.nu;
    }
    if (paramString.equals("NZ")) {
      return R.drawable.nz;
    }
    if (paramString.equals("OM")) {
      return R.drawable.om;
    }
    if (paramString.equals("PA")) {
      return R.drawable.pa;
    }
    if (paramString.equals("PE")) {
      return R.drawable.pe;
    }
    if (paramString.equals("PF")) {
      return R.drawable.pf;
    }
    if (paramString.equals("PG")) {
      return R.drawable.pg;
    }
    if (paramString.equals("PH")) {
      return R.drawable.ph;
    }
    if (paramString.equals("PK")) {
      return R.drawable.pk;
    }
    if (paramString.equals("PL")) {
      return R.drawable.pl;
    }
    if (paramString.equals("PM")) {
      return R.drawable.pm;
    }
    if (paramString.equals("PN")) {
      return R.drawable.pn;
    }
    if (paramString.equals("PR")) {
      return R.drawable.pr;
    }
    if (paramString.equals("PS")) {
      return R.drawable.ps;
    }
    if (paramString.equals("PT")) {
      return R.drawable.pt;
    }
    if (paramString.equals("PW")) {
      return R.drawable.pw;
    }
    if (paramString.equals("PY")) {
      return R.drawable.py;
    }
    if (paramString.equals("QA")) {
      return R.drawable.qa;
    }
    if (paramString.equals("RE")) {
      return R.drawable.re;
    }
    if (paramString.equals("RO")) {
      return R.drawable.ro;
    }
    if (paramString.equals("RS")) {
      return R.drawable.rs;
    }
    if (paramString.equals("RU")) {
      return R.drawable.ru;
    }
    if (paramString.equals("RW")) {
      return R.drawable.rw;
    }
    if (paramString.equals("SA")) {
      return R.drawable.sa;
    }
    if (paramString.equals("SB")) {
      return R.drawable.sb;
    }
    if (paramString.equals("SC")) {
      return R.drawable.sc;
    }
    if (paramString.equals("SD")) {
      return R.drawable.sd;
    }
    if (paramString.equals("SE")) {
      return R.drawable.se;
    }
    if (paramString.equals("SG")) {
      return R.drawable.sg;
    }
    if (paramString.equals("SH")) {
      return R.drawable.sh;
    }
    if (paramString.equals("SI")) {
      return R.drawable.si;
    }
    if (paramString.equals("SJ")) {
      return R.drawable.sj;
    }
    if (paramString.equals("SK")) {
      return R.drawable.sk;
    }
    if (paramString.equals("SL")) {
      return R.drawable.sl;
    }
    if (paramString.equals("SM")) {
      return R.drawable.sm;
    }
    if (paramString.equals("SN")) {
      return R.drawable.sn;
    }
    if (paramString.equals("SO")) {
      return R.drawable.so;
    }
    if (paramString.equals("SR")) {
      return R.drawable.sr;
    }
    if (paramString.equals("ST")) {
      return R.drawable.st;
    }
    if (paramString.equals("SV")) {
      return R.drawable.sv;
    }
    if (paramString.equals("SY")) {
      return R.drawable.sy;
    }
    if (paramString.equals("SZ")) {
      return R.drawable.sz;
    }
    if (paramString.equals("TC")) {
      return R.drawable.tc;
    }
    if (paramString.equals("TD")) {
      return R.drawable.td;
    }
    if (paramString.equals("TF")) {
      return R.drawable.tf;
    }
    if (paramString.equals("TG")) {
      return R.drawable.tg;
    }
    if (paramString.equals("TH")) {
      return R.drawable.th;
    }
    if (paramString.equals("TJ")) {
      return R.drawable.tj;
    }
    if (paramString.equals("TK")) {
      return R.drawable.tk;
    }
    if (paramString.equals("TL")) {
      return R.drawable.tl;
    }
    if (paramString.equals("TM")) {
      return R.drawable.tm;
    }
    if (paramString.equals("TN")) {
      return R.drawable.tn;
    }
    if (paramString.equals("TO")) {
      return R.drawable.to;
    }
    if (paramString.equals("TR")) {
      return R.drawable.tr;
    }
    if (paramString.equals("TT")) {
      return R.drawable.tt;
    }
    if (paramString.equals("TV")) {
      return R.drawable.tv;
    }
    if (paramString.equals("TW")) {
      return 0;
    }
    if (paramString.equals("TZ")) {
      return R.drawable.tz;
    }
    if (paramString.equals("UA")) {
      return R.drawable.ua;
    }
    if (paramString.equals("UG")) {
      return R.drawable.ug;
    }
    if (paramString.equals("UM")) {
      return R.drawable.um;
    }
    if (paramString.equals("US")) {
      return R.drawable.us;
    }
    if (paramString.equals("UY")) {
      return R.drawable.uy;
    }
    if (paramString.equals("UZ")) {
      return R.drawable.uz;
    }
    if (paramString.equals("VA")) {
      return R.drawable.va;
    }
    if (paramString.equals("VC")) {
      return R.drawable.vc;
    }
    if (paramString.equals("VE")) {
      return R.drawable.ve;
    }
    if (paramString.equals("VG")) {
      return R.drawable.vg;
    }
    if (paramString.equals("VI")) {
      return R.drawable.vi;
    }
    if (paramString.equals("VN")) {
      return R.drawable.vn;
    }
    if (paramString.equals("VU")) {
      return R.drawable.vu;
    }
    if (paramString.equals("WF")) {
      return R.drawable.wf;
    }
    if (paramString.equals("WS")) {
      return R.drawable.ws;
    }
    if (paramString.equals("XK")) {
      return R.drawable.xk;
    }
    if (paramString.equals("YE")) {
      return R.drawable.ye;
    }
    if (paramString.equals("YT")) {
      return R.drawable.yt;
    }
    if (paramString.equals("YU")) {
      return R.drawable.yu;
    }
    if (paramString.equals("ZA")) {
      return R.drawable.za;
    }
    if (paramString.equals("ZM")) {
      return R.drawable.zm;
    }
    if (paramString.equals("ZW")) {
      return R.drawable.zw;
    }
    paramString.equals("");
    return 0;
  }
  
  private static long j(String paramString)
  {
    if (c(paramString)) {
      return Long.parseLong(paramString);
    }
    return 0L;
  }
}
