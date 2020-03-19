package com.lionmobi.powerclean.locker.a;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.flurry.android.FlurryAgent;
import com.lionmobi.powerclean.locker.service.AppLockService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class a
  extends BaseAdapter
{
  final PackageManager a;
  final Context b;
  final Set c;
  List d;
  public boolean e = false;
  Handler f;
  private final LayoutInflater g;
  private final SharedPreferences.Editor h;
  private int i = -1;
  private boolean j;
  private b k;
  private boolean l;
  
  public a(Context paramContext, int paramInt)
  {
    this.b = paramContext;
    this.g = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.a = paramContext.getPackageManager();
    this.c = new HashSet();
    this.d = new ArrayList();
    this.h = com.lionmobi.powerclean.locker.c.a.appsPrefs(paramContext).edit();
    this.i = paramInt;
  }
  
  private void a(boolean paramBoolean)
  {
    if (this.l != paramBoolean)
    {
      this.l = paramBoolean;
      if (this.k != null) {
        this.k.onDirtyStateChanged(paramBoolean);
      }
    }
  }
  
  public final boolean HasAppsLocked()
  {
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext())
    {
      d localD = (d)localIterator.next();
      if ((localD.isApp()) && (localD.c)) {
        return true;
      }
    }
    return false;
  }
  
  /* Error */
  final void a(java.util.Collection paramCollection)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: bipush 71
    //   4: anewarray 127	java/lang/String
    //   7: dup
    //   8: iconst_0
    //   9: ldc -127
    //   11: aastore
    //   12: dup
    //   13: iconst_1
    //   14: ldc -125
    //   16: aastore
    //   17: dup
    //   18: iconst_2
    //   19: ldc -123
    //   21: aastore
    //   22: dup
    //   23: iconst_3
    //   24: ldc -121
    //   26: aastore
    //   27: dup
    //   28: iconst_4
    //   29: ldc -119
    //   31: aastore
    //   32: dup
    //   33: iconst_5
    //   34: ldc -117
    //   36: aastore
    //   37: dup
    //   38: bipush 6
    //   40: ldc -115
    //   42: aastore
    //   43: dup
    //   44: bipush 7
    //   46: ldc -113
    //   48: aastore
    //   49: dup
    //   50: bipush 8
    //   52: ldc -111
    //   54: aastore
    //   55: dup
    //   56: bipush 9
    //   58: ldc -109
    //   60: aastore
    //   61: dup
    //   62: bipush 10
    //   64: ldc -107
    //   66: aastore
    //   67: dup
    //   68: bipush 11
    //   70: ldc -105
    //   72: aastore
    //   73: dup
    //   74: bipush 12
    //   76: ldc -103
    //   78: aastore
    //   79: dup
    //   80: bipush 13
    //   82: ldc -101
    //   84: aastore
    //   85: dup
    //   86: bipush 14
    //   88: ldc -99
    //   90: aastore
    //   91: dup
    //   92: bipush 15
    //   94: ldc -97
    //   96: aastore
    //   97: dup
    //   98: bipush 16
    //   100: ldc -95
    //   102: aastore
    //   103: dup
    //   104: bipush 17
    //   106: ldc -93
    //   108: aastore
    //   109: dup
    //   110: bipush 18
    //   112: ldc -91
    //   114: aastore
    //   115: dup
    //   116: bipush 19
    //   118: ldc -89
    //   120: aastore
    //   121: dup
    //   122: bipush 20
    //   124: ldc -87
    //   126: aastore
    //   127: dup
    //   128: bipush 21
    //   130: ldc -85
    //   132: aastore
    //   133: dup
    //   134: bipush 22
    //   136: ldc -83
    //   138: aastore
    //   139: dup
    //   140: bipush 23
    //   142: ldc -81
    //   144: aastore
    //   145: dup
    //   146: bipush 24
    //   148: ldc -79
    //   150: aastore
    //   151: dup
    //   152: bipush 25
    //   154: ldc -77
    //   156: aastore
    //   157: dup
    //   158: bipush 26
    //   160: ldc -75
    //   162: aastore
    //   163: dup
    //   164: bipush 27
    //   166: ldc -73
    //   168: aastore
    //   169: dup
    //   170: bipush 28
    //   172: ldc -71
    //   174: aastore
    //   175: dup
    //   176: bipush 29
    //   178: ldc -69
    //   180: aastore
    //   181: dup
    //   182: bipush 30
    //   184: ldc -67
    //   186: aastore
    //   187: dup
    //   188: bipush 31
    //   190: ldc -65
    //   192: aastore
    //   193: dup
    //   194: bipush 32
    //   196: ldc -63
    //   198: aastore
    //   199: dup
    //   200: bipush 33
    //   202: ldc -61
    //   204: aastore
    //   205: dup
    //   206: bipush 34
    //   208: ldc -59
    //   210: aastore
    //   211: dup
    //   212: bipush 35
    //   214: ldc -57
    //   216: aastore
    //   217: dup
    //   218: bipush 36
    //   220: ldc -55
    //   222: aastore
    //   223: dup
    //   224: bipush 37
    //   226: ldc -53
    //   228: aastore
    //   229: dup
    //   230: bipush 38
    //   232: ldc -51
    //   234: aastore
    //   235: dup
    //   236: bipush 39
    //   238: ldc -49
    //   240: aastore
    //   241: dup
    //   242: bipush 40
    //   244: ldc -47
    //   246: aastore
    //   247: dup
    //   248: bipush 41
    //   250: ldc -45
    //   252: aastore
    //   253: dup
    //   254: bipush 42
    //   256: ldc -43
    //   258: aastore
    //   259: dup
    //   260: bipush 43
    //   262: ldc -41
    //   264: aastore
    //   265: dup
    //   266: bipush 44
    //   268: ldc -39
    //   270: aastore
    //   271: dup
    //   272: bipush 45
    //   274: ldc -37
    //   276: aastore
    //   277: dup
    //   278: bipush 46
    //   280: ldc -35
    //   282: aastore
    //   283: dup
    //   284: bipush 47
    //   286: ldc -33
    //   288: aastore
    //   289: dup
    //   290: bipush 48
    //   292: ldc -31
    //   294: aastore
    //   295: dup
    //   296: bipush 49
    //   298: ldc -29
    //   300: aastore
    //   301: dup
    //   302: bipush 50
    //   304: ldc -27
    //   306: aastore
    //   307: dup
    //   308: bipush 51
    //   310: ldc -25
    //   312: aastore
    //   313: dup
    //   314: bipush 52
    //   316: ldc -23
    //   318: aastore
    //   319: dup
    //   320: bipush 53
    //   322: ldc -21
    //   324: aastore
    //   325: dup
    //   326: bipush 54
    //   328: ldc -19
    //   330: aastore
    //   331: dup
    //   332: bipush 55
    //   334: ldc -17
    //   336: aastore
    //   337: dup
    //   338: bipush 56
    //   340: ldc -15
    //   342: aastore
    //   343: dup
    //   344: bipush 57
    //   346: ldc -13
    //   348: aastore
    //   349: dup
    //   350: bipush 58
    //   352: ldc -11
    //   354: aastore
    //   355: dup
    //   356: bipush 59
    //   358: ldc -9
    //   360: aastore
    //   361: dup
    //   362: bipush 60
    //   364: ldc -7
    //   366: aastore
    //   367: dup
    //   368: bipush 61
    //   370: ldc -5
    //   372: aastore
    //   373: dup
    //   374: bipush 62
    //   376: ldc -3
    //   378: aastore
    //   379: dup
    //   380: bipush 63
    //   382: ldc -1
    //   384: aastore
    //   385: dup
    //   386: bipush 64
    //   388: ldc_w 257
    //   391: aastore
    //   392: dup
    //   393: bipush 65
    //   395: ldc_w 259
    //   398: aastore
    //   399: dup
    //   400: bipush 66
    //   402: ldc_w 261
    //   405: aastore
    //   406: dup
    //   407: bipush 67
    //   409: ldc_w 263
    //   412: aastore
    //   413: dup
    //   414: bipush 68
    //   416: ldc_w 265
    //   419: aastore
    //   420: dup
    //   421: bipush 69
    //   423: ldc_w 267
    //   426: aastore
    //   427: dup
    //   428: bipush 70
    //   430: ldc_w 269
    //   433: aastore
    //   434: invokestatic 275	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   437: astore 6
    //   439: iconst_1
    //   440: anewarray 127	java/lang/String
    //   443: dup
    //   444: iconst_0
    //   445: ldc_w 277
    //   448: aastore
    //   449: invokestatic 275	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   452: astore 7
    //   454: aload_0
    //   455: getfield 41	com/lionmobi/powerclean/locker/a/a:b	Landroid/content/Context;
    //   458: invokevirtual 57	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   461: astore 8
    //   463: aload 8
    //   465: iconst_0
    //   466: invokevirtual 283	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   469: invokeinterface 106 1 0
    //   474: astore 9
    //   476: iconst_0
    //   477: istore 4
    //   479: aload 9
    //   481: invokeinterface 111 1 0
    //   486: ifeq +304 -> 790
    //   489: aload 9
    //   491: invokeinterface 115 1 0
    //   496: checkcast 285	android/content/pm/ApplicationInfo
    //   499: astore 10
    //   501: ldc_w 287
    //   504: aload 10
    //   506: getfield 291	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   509: invokevirtual 295	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   512: ifeq +220 -> 732
    //   515: aload_1
    //   516: new 117	com/lionmobi/powerclean/locker/a/d
    //   519: dup
    //   520: aload_0
    //   521: getfield 41	com/lionmobi/powerclean/locker/a/a:b	Landroid/content/Context;
    //   524: ldc_w 296
    //   527: invokevirtual 300	android/content/Context:getString	(I)Ljava/lang/String;
    //   530: aload 10
    //   532: iconst_4
    //   533: invokespecial 303	com/lionmobi/powerclean/locker/a/d:<init>	(Ljava/lang/String;Landroid/content/pm/PackageItemInfo;I)V
    //   536: invokeinterface 308 2 0
    //   541: pop
    //   542: iconst_1
    //   543: istore_2
    //   544: aload 6
    //   546: aload 10
    //   548: getfield 291	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   551: invokeinterface 311 2 0
    //   556: istore 5
    //   558: iload 5
    //   560: ifeq +35 -> 595
    //   563: aload_1
    //   564: new 117	com/lionmobi/powerclean/locker/a/d
    //   567: dup
    //   568: aload 10
    //   570: aload 8
    //   572: invokevirtual 315	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   575: invokeinterface 321 1 0
    //   580: aload 10
    //   582: bipush 6
    //   584: invokespecial 303	com/lionmobi/powerclean/locker/a/d:<init>	(Ljava/lang/String;Landroid/content/pm/PackageItemInfo;I)V
    //   587: invokeinterface 308 2 0
    //   592: pop
    //   593: iconst_1
    //   594: istore_3
    //   595: aload 7
    //   597: aload 10
    //   599: getfield 291	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   602: invokeinterface 311 2 0
    //   607: istore 5
    //   609: iload 5
    //   611: ifeq +190 -> 801
    //   614: aload_1
    //   615: new 117	com/lionmobi/powerclean/locker/a/d
    //   618: dup
    //   619: aload 10
    //   621: aload 8
    //   623: invokevirtual 315	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   626: invokeinterface 321 1 0
    //   631: aload 10
    //   633: iconst_4
    //   634: invokespecial 303	com/lionmobi/powerclean/locker/a/d:<init>	(Ljava/lang/String;Landroid/content/pm/PackageItemInfo;I)V
    //   637: invokeinterface 308 2 0
    //   642: pop
    //   643: iconst_1
    //   644: istore_2
    //   645: aload_1
    //   646: new 117	com/lionmobi/powerclean/locker/a/d
    //   649: dup
    //   650: aload_0
    //   651: getfield 41	com/lionmobi/powerclean/locker/a/a:b	Landroid/content/Context;
    //   654: ldc_w 322
    //   657: invokevirtual 300	android/content/Context:getString	(I)Ljava/lang/String;
    //   660: iconst_3
    //   661: invokespecial 325	com/lionmobi/powerclean/locker/a/d:<init>	(Ljava/lang/String;I)V
    //   664: invokeinterface 308 2 0
    //   669: pop
    //   670: iload_3
    //   671: ifeq +29 -> 700
    //   674: aload_1
    //   675: new 117	com/lionmobi/powerclean/locker/a/d
    //   678: dup
    //   679: aload_0
    //   680: getfield 41	com/lionmobi/powerclean/locker/a/a:b	Landroid/content/Context;
    //   683: ldc_w 326
    //   686: invokevirtual 300	android/content/Context:getString	(I)Ljava/lang/String;
    //   689: bipush 7
    //   691: invokespecial 325	com/lionmobi/powerclean/locker/a/d:<init>	(Ljava/lang/String;I)V
    //   694: invokeinterface 308 2 0
    //   699: pop
    //   700: iload_2
    //   701: ifeq +103 -> 804
    //   704: aload_1
    //   705: new 117	com/lionmobi/powerclean/locker/a/d
    //   708: dup
    //   709: aload_0
    //   710: getfield 41	com/lionmobi/powerclean/locker/a/a:b	Landroid/content/Context;
    //   713: ldc_w 327
    //   716: invokevirtual 300	android/content/Context:getString	(I)Ljava/lang/String;
    //   719: iconst_5
    //   720: invokespecial 325	com/lionmobi/powerclean/locker/a/d:<init>	(Ljava/lang/String;I)V
    //   723: invokeinterface 308 2 0
    //   728: pop
    //   729: goto +75 -> 804
    //   732: iload 4
    //   734: istore_2
    //   735: ldc_w 329
    //   738: aload 10
    //   740: getfield 291	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   743: invokevirtual 295	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   746: ifeq -202 -> 544
    //   749: aload_1
    //   750: new 117	com/lionmobi/powerclean/locker/a/d
    //   753: dup
    //   754: aload_0
    //   755: getfield 41	com/lionmobi/powerclean/locker/a/a:b	Landroid/content/Context;
    //   758: ldc_w 330
    //   761: invokevirtual 300	android/content/Context:getString	(I)Ljava/lang/String;
    //   764: aload 10
    //   766: bipush 6
    //   768: invokespecial 303	com/lionmobi/powerclean/locker/a/d:<init>	(Ljava/lang/String;Landroid/content/pm/PackageItemInfo;I)V
    //   771: invokeinterface 308 2 0
    //   776: pop
    //   777: iconst_1
    //   778: istore_3
    //   779: iload 4
    //   781: istore_2
    //   782: goto -238 -> 544
    //   785: astore_1
    //   786: aload_1
    //   787: invokevirtual 333	java/lang/Exception:printStackTrace	()V
    //   790: return
    //   791: astore 10
    //   793: goto -150 -> 643
    //   796: astore 11
    //   798: goto -205 -> 593
    //   801: goto -156 -> 645
    //   804: iload_2
    //   805: istore 4
    //   807: goto -328 -> 479
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	810	0	this	a
    //   0	810	1	paramCollection	java.util.Collection
    //   543	262	2	m	int
    //   1	778	3	n	int
    //   477	329	4	i1	int
    //   556	54	5	bool	boolean
    //   437	108	6	localList1	List
    //   452	144	7	localList2	List
    //   461	161	8	localPackageManager	PackageManager
    //   474	16	9	localIterator	Iterator
    //   499	266	10	localApplicationInfo	android.content.pm.ApplicationInfo
    //   791	1	10	localException1	Exception
    //   796	1	11	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   454	476	785	java/lang/Exception
    //   479	542	785	java/lang/Exception
    //   544	558	785	java/lang/Exception
    //   595	609	785	java/lang/Exception
    //   645	670	785	java/lang/Exception
    //   674	700	785	java/lang/Exception
    //   704	729	785	java/lang/Exception
    //   735	777	785	java/lang/Exception
    //   614	643	791	java/lang/Exception
    //   563	593	796	java/lang/Exception
  }
  
  public final int getCount()
  {
    return this.d.size();
  }
  
  public final ArrayList getImportanceApp()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    a(localArrayList1);
    int m = 0;
    while (m < localArrayList1.size())
    {
      String str = ((d)localArrayList1.get(m)).b;
      if ((!str.equals("")) && (!str.equals("com.android.systemui"))) {
        localArrayList2.add(localArrayList1.get(m));
      }
      m += 1;
    }
    Collections.sort(localArrayList2);
    return localArrayList2;
  }
  
  public final Object getItem(int paramInt)
  {
    return this.d.get(paramInt);
  }
  
  public final long getItemId(int paramInt)
  {
    return 0L;
  }
  
  public final int getItemViewType(int paramInt)
  {
    if (((d)this.d.get(paramInt)).isApp()) {
      return 0;
    }
    return 1;
  }
  
  public final View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (((d)this.d.get(paramInt)).isApp())
    {
      localObject1 = (d)this.d.get(paramInt);
      if (paramView == null)
      {
        paramView = this.g.inflate(2130903103, paramViewGroup, false);
        paramViewGroup = new c();
        paramViewGroup.a = ((TextView)paramView.findViewById(2131427831));
        paramViewGroup.b = ((ImageView)paramView.findViewById(2131427830));
        paramViewGroup.c = ((CheckBox)paramView.findViewById(2131427832));
        paramView.setTag(paramViewGroup);
      }
      while (paramInt == this.i)
      {
        paramViewGroup.c.setVisibility(8);
        paramView.findViewById(2131427833).setVisibility(0);
        paramViewGroup.a.setText(((d)localObject1).getLabel(this.a));
        localObject1 = ((d)localObject1).getIcon(this.a);
        if (localObject1 != null) {
          break label221;
        }
        paramViewGroup.b.setVisibility(8);
        return paramView;
        paramViewGroup = (c)paramView.getTag();
      }
      localObject2 = paramViewGroup.c;
      if (((d)localObject1).c) {}
      for (boolean bool = true;; bool = false)
      {
        ((CheckBox)localObject2).setChecked(bool);
        break;
      }
      label221:
      paramViewGroup = paramViewGroup.b;
      if (Build.VERSION.SDK_INT < 16)
      {
        paramViewGroup.setBackgroundDrawable((Drawable)localObject1);
        return paramView;
      }
      paramViewGroup.setBackground((Drawable)localObject1);
      return paramView;
    }
    Object localObject2 = (d)this.d.get(paramInt);
    Object localObject1 = paramView;
    if (paramView == null) {
      localObject1 = this.g.inflate(2130903104, paramViewGroup, false);
    }
    ((TextView)((View)localObject1).findViewById(2131427831)).setText(((d)localObject2).a);
    return localObject1;
  }
  
  public final int getViewTypeCount()
  {
    return 2;
  }
  
  public final void loaddatas()
  {
    this.f = new Handler()
    {
      public final void handleMessage(Message paramAnonymousMessage)
      {
        super.handleMessage(paramAnonymousMessage);
        switch (paramAnonymousMessage.what)
        {
        }
        do
        {
          return;
          a.this.sort();
        } while (a.a(a.this) == null);
        a.b(a.this);
        a.a(a.this).onLoadComplete();
      }
    };
    new Thread(new Runnable()
    {
      public final void run()
      {
        a localA = a.this;
        Object localObject1;
        Object localObject2;
        try
        {
          localA.a(localA.c);
          localObject1 = new Intent("android.intent.action.MAIN");
          ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
          localObject1 = localA.a.queryIntentActivities((Intent)localObject1, 0).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (ResolveInfo)((Iterator)localObject1).next();
            if (!localA.b.getPackageName().equals(((ResolveInfo)localObject2).activityInfo.packageName))
            {
              localObject2 = new d(((ResolveInfo)localObject2).loadLabel(localA.a).toString(), ((ResolveInfo)localObject2).activityInfo, 1);
              localA.c.add(localObject2);
              continue;
              a.this.f.sendEmptyMessage(0);
            }
          }
        }
        catch (Exception localException) {}
        for (;;)
        {
          return;
          localObject1 = com.lionmobi.powerclean.locker.c.a.getLockedApps(localException.b);
          if (((Set)localObject1).size() > 0) {
            FlurryAgent.logEvent("ApplockHasLocked");
          }
          localObject2 = localException.c.iterator();
          while (((Iterator)localObject2).hasNext())
          {
            d localD = (d)((Iterator)localObject2).next();
            localD.c = ((Set)localObject1).contains(localD.b);
          }
          localException.d = new ArrayList(localException.c);
        }
      }
    }).start();
  }
  
  public final void setOnEventListener(b paramB)
  {
    this.k = paramB;
  }
  
  public final void sort()
  {
    Collections.sort(this.d);
    notifyDataSetChanged();
    a(false);
  }
  
  public final void toggle(d paramD)
  {
    if (paramD.isApp())
    {
      boolean bool;
      int m;
      label33:
      String str;
      if (!paramD.c)
      {
        bool = true;
        paramD.c = bool;
        bool = paramD.c;
        paramD = paramD.b;
        m = 0;
        if (m > 0) {
          break label93;
        }
        str = new String[] { paramD }[0];
        if (!bool) {
          break label78;
        }
        this.h.putBoolean(str, true);
      }
      for (;;)
      {
        m += 1;
        break label33;
        bool = false;
        break;
        label78:
        this.h.remove(str);
      }
      label93:
      com.lionmobi.powerclean.locker.c.a.apply(this.h);
      if (HasAppsLocked()) {
        AppLockService.forceRestart(this.b);
      }
    }
    else
    {
      a(true);
      if (!HasAppsLocked()) {
        break label153;
      }
      if (!AppLockService.a) {
        AppLockService.start(this.b.getApplicationContext());
      }
    }
    label153:
    while (!AppLockService.a)
    {
      return;
      AppLockService.stop(this.b);
      break;
    }
    AppLockService.stop(this.b.getApplicationContext());
  }
}
