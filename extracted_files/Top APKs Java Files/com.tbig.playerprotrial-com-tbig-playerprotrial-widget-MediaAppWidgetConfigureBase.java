package com.tbig.playerprotrial.widget;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.view.Window;
import android.widget.Button;
import com.tbig.playerprotrial.ch;
import com.tbig.playerprotrial.dh;
import com.tbig.playerprotrial.settings.ColorPickerPreference;
import com.tbig.playerprotrial.settings.WidgetBackgroundAlphaPreference;
import com.tbig.playerprotrial.settings.WidgetPreviewPreference;
import java.util.ArrayList;
import java.util.List;

public abstract class MediaAppWidgetConfigureBase
  extends PreferenceActivity
{
  private int a;
  private dh b;
  private WidgetPreviewPreference c;
  private CheckBoxPreference d;
  private CheckBoxPreference e;
  private CheckBoxPreference f;
  private CheckBoxPreference g;
  private CheckBoxPreference h;
  private CheckBoxPreference i;
  private CheckBoxPreference j;
  private ListPreference k;
  private WidgetBackgroundAlphaPreference l;
  private int m;
  private int n;
  private BroadcastReceiver o = new d(this);
  private ServiceConnection p = new e(this);
  
  public MediaAppWidgetConfigureBase() {}
  
  /* Error */
  private void a(List paramList1, List paramList2, List paramList3)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 58	com/tbig/playerprotrial/widget/MediaAppWidgetConfigureBase:getResources	()Landroid/content/res/Resources;
    //   4: astore 6
    //   6: aload 6
    //   8: ldc 59
    //   10: invokevirtual 65	android/content/res/Resources:getTextArray	(I)[Ljava/lang/CharSequence;
    //   13: astore 7
    //   15: iconst_0
    //   16: istore 4
    //   18: iload 4
    //   20: aload 7
    //   22: arraylength
    //   23: if_icmpge +35 -> 58
    //   26: aload_1
    //   27: aload 7
    //   29: iload 4
    //   31: aaload
    //   32: invokeinterface 71 2 0
    //   37: pop
    //   38: aload_3
    //   39: iconst_0
    //   40: invokestatic 77	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   43: invokeinterface 71 2 0
    //   48: pop
    //   49: iload 4
    //   51: iconst_1
    //   52: iadd
    //   53: istore 4
    //   55: goto -37 -> 18
    //   58: aload 6
    //   60: ldc 78
    //   62: invokevirtual 65	android/content/res/Resources:getTextArray	(I)[Ljava/lang/CharSequence;
    //   65: astore 6
    //   67: iconst_0
    //   68: istore 4
    //   70: iload 4
    //   72: aload 6
    //   74: arraylength
    //   75: if_icmpge +24 -> 99
    //   78: aload_2
    //   79: aload 6
    //   81: iload 4
    //   83: aaload
    //   84: invokeinterface 71 2 0
    //   89: pop
    //   90: iload 4
    //   92: iconst_1
    //   93: iadd
    //   94: istore 4
    //   96: goto -26 -> 70
    //   99: aload_0
    //   100: invokevirtual 82	com/tbig/playerprotrial/widget/MediaAppWidgetConfigureBase:getPackageManager	()Landroid/content/pm/PackageManager;
    //   103: astore 9
    //   105: aload 9
    //   107: iconst_0
    //   108: invokevirtual 88	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   111: astore 10
    //   113: aload_0
    //   114: invokevirtual 91	com/tbig/playerprotrial/widget/MediaAppWidgetConfigureBase:b	()Ljava/lang/String;
    //   117: astore 11
    //   119: iconst_0
    //   120: istore 5
    //   122: iload 5
    //   124: aload 10
    //   126: invokeinterface 95 1 0
    //   131: if_icmpge +200 -> 331
    //   134: aload 10
    //   136: iload 5
    //   138: invokeinterface 99 2 0
    //   143: checkcast 101	android/content/pm/ApplicationInfo
    //   146: astore 8
    //   148: aload 8
    //   150: getfield 105	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   153: ldc 107
    //   155: invokevirtual 113	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   158: ifne +16 -> 174
    //   161: aload 8
    //   163: getfield 105	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   166: ldc 115
    //   168: invokevirtual 113	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   171: ifeq +121 -> 292
    //   174: aload 9
    //   176: aload 8
    //   178: getfield 105	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   181: invokevirtual 119	android/content/pm/PackageManager:getResourcesForApplication	(Ljava/lang/String;)Landroid/content/res/Resources;
    //   184: astore 12
    //   186: aload 12
    //   188: aload 11
    //   190: ldc 121
    //   192: aload 8
    //   194: getfield 105	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   197: invokevirtual 125	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   200: ifeq +145 -> 345
    //   203: aload 12
    //   205: aload 8
    //   207: getfield 128	android/content/pm/ApplicationInfo:labelRes	I
    //   210: invokevirtual 132	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   213: astore 7
    //   215: aload 8
    //   217: getfield 105	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   220: astore 6
    //   222: aload 12
    //   224: ldc -122
    //   226: ldc -120
    //   228: aload 8
    //   230: getfield 105	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   233: invokevirtual 125	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   236: istore 4
    //   238: iload 4
    //   240: ifeq +61 -> 301
    //   243: aload 12
    //   245: iload 4
    //   247: invokevirtual 140	android/content/res/Resources:getInteger	(I)I
    //   250: istore 4
    //   252: aload 7
    //   254: ifnull +38 -> 292
    //   257: aload 6
    //   259: ifnull +33 -> 292
    //   262: aload_1
    //   263: aload 7
    //   265: invokeinterface 71 2 0
    //   270: pop
    //   271: aload_2
    //   272: aload 6
    //   274: invokeinterface 71 2 0
    //   279: pop
    //   280: aload_3
    //   281: iload 4
    //   283: invokestatic 77	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   286: invokeinterface 71 2 0
    //   291: pop
    //   292: iload 5
    //   294: iconst_1
    //   295: iadd
    //   296: istore 5
    //   298: goto -176 -> 122
    //   301: iconst_0
    //   302: istore 4
    //   304: goto -52 -> 252
    //   307: astore 8
    //   309: aconst_null
    //   310: astore 6
    //   312: aconst_null
    //   313: astore 7
    //   315: ldc -114
    //   317: ldc -112
    //   319: aload 8
    //   321: invokestatic 149	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   324: pop
    //   325: iconst_0
    //   326: istore 4
    //   328: goto -76 -> 252
    //   331: return
    //   332: astore 8
    //   334: aconst_null
    //   335: astore 6
    //   337: goto -22 -> 315
    //   340: astore 8
    //   342: goto -27 -> 315
    //   345: iconst_0
    //   346: istore 4
    //   348: aconst_null
    //   349: astore 6
    //   351: aconst_null
    //   352: astore 7
    //   354: goto -102 -> 252
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	357	0	this	MediaAppWidgetConfigureBase
    //   0	357	1	paramList1	List
    //   0	357	2	paramList2	List
    //   0	357	3	paramList3	List
    //   16	331	4	i1	int
    //   120	177	5	i2	int
    //   4	346	6	localObject1	Object
    //   13	340	7	localObject2	Object
    //   146	83	8	localApplicationInfo	android.content.pm.ApplicationInfo
    //   307	13	8	localException1	Exception
    //   332	1	8	localException2	Exception
    //   340	1	8	localException3	Exception
    //   103	72	9	localPackageManager	android.content.pm.PackageManager
    //   111	24	10	localList	List
    //   117	72	11	str	String
    //   184	60	12	localResources	Resources
    // Exception table:
    //   from	to	target	type
    //   174	215	307	java/lang/Exception
    //   215	222	332	java/lang/Exception
    //   222	238	340	java/lang/Exception
    //   243	252	340	java/lang/Exception
  }
  
  protected abstract void a();
  
  protected abstract void a(int paramInt);
  
  protected abstract String b();
  
  protected void onCreate(Bundle paramBundle)
  {
    getWindow().setFormat(1);
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    setContentView(2130903058);
    a();
    paramBundle = getIntent().getExtras();
    if (paramBundle != null) {
      this.a = paramBundle.getInt("appWidgetId", 0);
    }
    if (this.a == 0) {
      finish();
    }
    paramBundle = new Intent();
    paramBundle.putExtra("appWidgetId", this.a);
    setResult(-1, paramBundle);
    this.b = ch.a(this, this.p);
    this.c = ((WidgetPreviewPreference)findPreference("appwidget_preview"));
    this.l = ((WidgetBackgroundAlphaPreference)findPreference("appwidget_bg_alpha"));
    if (this.l != null)
    {
      this.c.a(this.l.a());
      this.l.setOnPreferenceChangeListener(new a(this));
    }
    this.e = ((CheckBoxPreference)findPreference("appwidget_display_shuffle_repeat"));
    label254:
    label352:
    label392:
    label446:
    label486:
    ColorPickerPreference localColorPickerPreference;
    if (this.e != null)
    {
      if (this.e.isChecked())
      {
        this.c.a(true);
        this.e.setOnPreferenceChangeListener(new g(this));
      }
    }
    else
    {
      this.d = ((CheckBoxPreference)findPreference("appwidget_display_ratings"));
      if (this.d != null)
      {
        if (!this.d.isChecked()) {
          break label826;
        }
        this.c.b(false);
        this.d.setOnPreferenceChangeListener(new h(this));
      }
      this.f = ((CheckBoxPreference)findPreference("appwidget_display_artwork"));
      this.g = ((CheckBoxPreference)findPreference("appwidget_tap_artwork"));
      this.i = ((CheckBoxPreference)findPreference("appwidget_prefer_artist_art"));
      if (this.f != null)
      {
        if (!this.f.isChecked()) {
          break label837;
        }
        this.c.c(false);
        this.g.setEnabled(true);
        this.i.setEnabled(true);
        this.f.setOnPreferenceChangeListener(new i(this));
      }
      if (this.g != null)
      {
        if (!this.g.isChecked()) {
          break label864;
        }
        this.c.d(true);
        this.g.setOnPreferenceChangeListener(new j(this));
      }
      this.h = ((CheckBoxPreference)findPreference("appwidget_tap_title"));
      if (this.h != null)
      {
        if (!this.h.isChecked()) {
          break label875;
        }
        this.c.e(true);
        this.h.setOnPreferenceChangeListener(new k(this));
      }
      if (this.i != null)
      {
        if (!this.i.isChecked()) {
          break label886;
        }
        this.c.f(true);
        this.i.setOnPreferenceChangeListener(new l(this));
      }
      localColorPickerPreference = (ColorPickerPreference)findPreference("appwidget_color");
      Object localObject = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      ArrayList localArrayList1 = new ArrayList();
      a((List)localObject, localArrayList2, localArrayList1);
      paramBundle = new CharSequence[((List)localObject).size()];
      ((List)localObject).toArray(paramBundle);
      localObject = new CharSequence[localArrayList2.size()];
      localArrayList2.toArray((Object[])localObject);
      this.k = ((ListPreference)findPreference("appwidget_skin"));
      this.k.setEntries(paramBundle);
      this.k.setEntryValues((CharSequence[])localObject);
      paramBundle = this.k.getValue();
      int i2 = localArrayList2.indexOf(paramBundle);
      int i1 = i2;
      if (i2 == -1)
      {
        this.k.setValue("ppo");
        paramBundle = "ppo";
        i1 = 0;
      }
      this.n = ((Integer)localArrayList1.get(i1)).intValue();
      this.c.a(paramBundle);
      this.k.setOnPreferenceChangeListener(new m(this, localColorPickerPreference, localArrayList1));
      if (!"custom".equals(paramBundle)) {
        break label897;
      }
      localColorPickerPreference.setEnabled(true);
      this.m = localColorPickerPreference.c();
      this.c.b(this.m);
    }
    for (;;)
    {
      localColorPickerPreference.setOnPreferenceChangeListener(new n(this));
      this.j = ((CheckBoxPreference)findPreference("appwidget_xlarge_art"));
      if (this.j != null) {
        this.j.setOnPreferenceChangeListener(new b(this));
      }
      ((Button)findViewById(2131427389)).setOnClickListener(new c(this));
      return;
      this.c.a(false);
      break;
      label826:
      this.c.b(true);
      break label254;
      label837:
      this.c.c(true);
      this.g.setEnabled(false);
      this.i.setEnabled(false);
      break label352;
      label864:
      this.c.d(false);
      break label392;
      label875:
      this.c.e(false);
      break label446;
      label886:
      this.c.f(false);
      break label486;
      label897:
      localColorPickerPreference.setEnabled(false);
      this.m = 0;
    }
  }
  
  protected Dialog onCreateDialog(int paramInt)
  {
    Resources localResources = getResources();
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    switch (paramInt)
    {
    default: 
      return null;
    }
    localBuilder.setMessage(localResources.getString(2131361820)).setTitle(localResources.getString(2131361818)).setCancelable(true).setPositiveButton(localResources.getString(2131361897), new f(this));
    return localBuilder.create();
  }
  
  public void onDestroy()
  {
    if (this.b != null) {
      ch.a(this.b);
    }
    super.onDestroy();
  }
  
  public void onPause()
  {
    boolean bool2 = true;
    unregisterReceiver(this.o);
    com.tbig.playerprotrial.settings.m localM;
    if (isFinishing())
    {
      localM = com.tbig.playerprotrial.settings.m.a(this);
      if (this.e != null) {
        localM.b(this.e.isChecked(), this.a);
      }
      if (this.d != null)
      {
        if (this.d.isChecked()) {
          break label259;
        }
        bool1 = true;
        localM.c(bool1, this.a);
      }
      if (this.f != null) {
        if (this.f.isChecked()) {
          break label264;
        }
      }
    }
    label259:
    label264:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      localM.d(bool1, this.a);
      if (this.g != null) {
        localM.e(this.g.isChecked(), this.a);
      }
      if (this.h != null) {
        localM.f(this.h.isChecked(), this.a);
      }
      if (this.i != null) {
        localM.g(this.i.isChecked(), this.a);
      }
      if (this.j != null) {
        localM.a(this.j.isChecked(), this.a);
      }
      localM.a(this.k.getValue(), this.a);
      localM.a(this.n, this.a);
      localM.b(this.m, this.a);
      localM.c(this.l.a(), this.a);
      localM.s();
      a(this.a);
      super.onPause();
      return;
      bool1 = false;
      break;
    }
  }
  
  public void onResume()
  {
    super.onResume();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.tbig.playerprotrial.metachanged");
    localIntentFilter.addAction("com.tbig.playerprotrial.queuechanged");
    registerReceiver(this.o, localIntentFilter);
    this.o.onReceive(null, null);
  }
}
