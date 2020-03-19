package com.android.inputmethod.latin.suggestions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.k;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout.LayoutParams;
import com.MainApp;
import com.android.inputmethod.latin.LatinIME;
import com.android.inputmethod.latin.utils.i;
import com.android.inputmethod.latin.utils.w;
import com.emoji.common.h;
import com.more.setting.MainActivity;
import com.umeng.analytics.MobclickAgent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class d
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  private LatinIME aIX;
  y.c aJy;
  RecyclerView aao;
  public int baI = 0;
  private View bbV;
  final List<ch.a> bjZ = new ArrayList();
  private final SharedPreferences bkA;
  private final BroadcastReceiver bkB;
  private b bkC;
  ch.a bkD;
  boolean bkE;
  private Locale bkF = Locale.ENGLISH;
  private boolean bkG;
  private long bkH;
  public String bkI;
  private final Map<String, String> bkJ;
  private final SparseArray<com.android.inputmethod.latin.suggestions.gifpredict.b> bkK;
  Comparator<GifPack> bkL = new Comparator() {};
  private int bkM;
  int bkN;
  private BroadcastReceiver bkq;
  private boolean bkr;
  final Map<String, ArrayList<GifPack>> bks = new ArrayMap();
  b bkt;
  int bku;
  private boolean bkv;
  private Set<String> bkw;
  int bkx;
  int bky;
  private int bkz;
  private Handler mHandler = new Handler()
  {
    public final void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default: 
      case 5500: 
      case 2200: 
      case 3300: 
        Object localObject2;
        Object localObject1;
        int i;
        Object localObject3;
        Object localObject4;
        label356:
        label626:
        label712:
        label714:
        do
        {
          for (;;)
          {
            return;
            d.this.hide();
            return;
            d.d(d.this);
            return;
            localObject2 = (GifPack)paramAnonymousMessage.obj;
            paramAnonymousMessage = d.this;
            localObject1 = d.e(d.this);
            if (localObject1 == null) {
              i = 0;
            }
            for (;;)
            {
              if (i == 0) {
                break label712;
              }
              if ((paramAnonymousMessage.aao != null) && (paramAnonymousMessage.aao.getAdapter() != paramAnonymousMessage.bkt) && (paramAnonymousMessage.bkt != null)) {
                paramAnonymousMessage.aao.setAdapter(paramAnonymousMessage.bkt);
              }
              if (localObject2 == null) {
                break;
              }
              if ((!paramAnonymousMessage.isShowing()) || (!TextUtils.equals(((GifPack)localObject2).text, paramAnonymousMessage.bkI))) {
                break label714;
              }
              paramAnonymousMessage.bku = 15000;
              return;
              localObject3 = ((View)localObject1).getContext();
              if (localObject3 == null)
              {
                i = 0;
              }
              else
              {
                localObject4 = paramAnonymousMessage.aao;
                if (localObject4 == null)
                {
                  i = 0;
                }
                else
                {
                  int j;
                  if ((((RecyclerView)localObject4).getLayoutParams() instanceof RelativeLayout.LayoutParams))
                  {
                    localObject4 = i.bnc;
                    ((View)localObject1).getLocationInWindow((int[])localObject4);
                    j = localObject4[1];
                    localObject4 = String.valueOf(cb.b.bCV);
                    i = -1;
                    switch (((String)localObject4).hashCode())
                    {
                    default: 
                      switch (i)
                      {
                      default: 
                        i = 45;
                      }
                      break;
                    }
                  }
                  for (;;)
                  {
                    paramAnonymousMessage.bkN = (j - h.o((Context)localObject3, i + 4) - paramAnonymousMessage.bky);
                    if (paramAnonymousMessage.mParent != null) {
                      break label626;
                    }
                    i = 0;
                    break;
                    if (!((String)localObject4).equals("com.tencent.mobileqq")) {
                      break label356;
                    }
                    i = 0;
                    break label356;
                    if (!((String)localObject4).equals("com.snapchat.android")) {
                      break label356;
                    }
                    i = 1;
                    break label356;
                    if (!((String)localObject4).equals("com.facebook.orca")) {
                      break label356;
                    }
                    i = 2;
                    break label356;
                    if (!((String)localObject4).equals("com.whatsapp")) {
                      break label356;
                    }
                    i = 3;
                    break label356;
                    if (!((String)localObject4).equals("com.instagram.android")) {
                      break label356;
                    }
                    i = 4;
                    break label356;
                    if (!((String)localObject4).equals("com.facebook.katana")) {
                      break label356;
                    }
                    i = 5;
                    break label356;
                    if (!((String)localObject4).equals("com.android.mms")) {
                      break label356;
                    }
                    i = 6;
                    break label356;
                    if (!((String)localObject4).equals("com.android.chrome")) {
                      break label356;
                    }
                    i = 7;
                    break label356;
                    if (!((String)localObject4).equals("com.bbm")) {
                      break label356;
                    }
                    i = 8;
                    break label356;
                    if (!((String)localObject4).equals("com.google.android.youtube")) {
                      break label356;
                    }
                    i = 9;
                    break label356;
                    if (!((String)localObject4).equals("jp.naver.line.android")) {
                      break label356;
                    }
                    i = 10;
                    break label356;
                    if (!((String)localObject4).equals("com.tencent.mm")) {
                      break label356;
                    }
                    i = 11;
                    break label356;
                    i = 82;
                    continue;
                    i = 71;
                    continue;
                    i = 55;
                  }
                  if (paramAnonymousMessage.mParent.getParent() == null)
                  {
                    i = ((View)localObject1).getWidth();
                    j = ((View)localObject1).getHeight();
                    if ((i == 0) || (j == 0))
                    {
                      i = 0;
                    }
                    else
                    {
                      localObject3 = ((View)localObject1).getRootView();
                      if (localObject3 == null)
                      {
                        i = 0;
                      }
                      else
                      {
                        localObject3 = (ViewGroup)((View)localObject3).findViewById(16908290);
                        if (localObject3 == null) {
                          i = 0;
                        } else {
                          ((ViewGroup)localObject3).addView(paramAnonymousMessage.mParent);
                        }
                      }
                    }
                  }
                  else
                  {
                    i = 1;
                  }
                }
              }
            }
          }
          localObject3 = ((GifPack)localObject2).packName;
          i = ((GifPack)localObject2).bkn;
          localObject2 = ((GifPack)localObject2).text;
          localObject4 = com.emoji.common.d.u(((View)localObject1).getContext(), (String)localObject3);
          localObject1 = d.n((Context)localObject4, h.h((Context)localObject4, "stickerface_" + i, "drawable"));
          i = h.h((Context)localObject4, "sticker_" + i, "drawable");
          localObject4 = d.n((Context)localObject4, i);
        } while (i == 0);
        ch.a localA = new ch.a();
        localA.setLocalDrawableId(i);
        localA.setLocalDrawableUri(((Uri)localObject4).toString());
        localA.setLocalThumbnailUri(((Uri)localObject1).toString());
        localA.setPackageName((String)localObject3);
        localA.setText((String)localObject2);
        if ((!paramAnonymousMessage.bkE) && (paramAnonymousMessage.aJy != null))
        {
          paramAnonymousMessage.aJy.onResume();
          paramAnonymousMessage.bkE = true;
        }
        paramAnonymousMessage.mParent.setVisibility(0);
        paramAnonymousMessage.mParent.setPadding(paramAnonymousMessage.bkx - paramAnonymousMessage.bky, paramAnonymousMessage.bkN, 0, 0);
        paramAnonymousMessage.bjZ.add(0, localA);
        paramAnonymousMessage.bkt.notifyDataSetChanged();
        return;
      }
      if (!d.f(d.this)) {
        d.b(d.this, d.g(d.this) - 1000);
      }
      if (d.g(d.this) <= 0)
      {
        d.b(d.this, 0);
        if (d.this.bkD == null)
        {
          d.this.hide();
          return;
        }
      }
      sendEmptyMessageDelayed(2425, 1000L);
    }
  };
  ViewGroup mParent = null;
  
  public d(LatinIME paramLatinIME)
  {
    this.aIX = paramLatinIME;
    this.bky = this.aIX.getResources().getDimensionPixelOffset(2131427557);
    this.bkz = h.o(this.aIX, 3);
    this.bkK = new SparseArray();
    Object localObject = (WindowManager)this.aIX.getSystemService("window");
    Point localPoint = new Point();
    ((WindowManager)localObject).getDefaultDisplay().getSize(localPoint);
    this.bkx = localPoint.x;
    this.bkA = PreferenceManager.getDefaultSharedPreferences(paramLatinIME);
    this.bkA.registerOnSharedPreferenceChangeListener(this);
    pV();
    this.bkq = new BroadcastReceiver()
    {
      public final void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        d.this.hide();
      }
    };
    LocalBroadcastManager.getInstance(this.aIX).registerReceiver(this.bkq, new IntentFilter("com.android.inputmethod.latin.suggestions.GifPredictView"));
    localObject = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    this.bkB = new BroadcastReceiver()
    {
      public final void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        d.this.pV();
      }
    };
    LocalBroadcastManager.getInstance(this.aIX).registerReceiver(this.bkB, (IntentFilter)localObject);
    p(paramLatinIME);
    this.mParent = ((ViewGroup)LayoutInflater.from(paramLatinIME).inflate(2130903164, null, false));
    this.mParent.setOnTouchListener(new View.OnTouchListener()
    {
      public final boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        d.this.hide();
        return false;
      }
    });
    this.aao = ((RecyclerView)this.mParent.findViewById(2131755365));
    al.a.a(this.aao, false, new Runnable()
    {
      public final void run()
      {
        d.a(d.this, d.a(d.this).getPaddingLeft());
        new StringBuilder("测量RecyclerView的高度----》").append(d.a(d.this).getHeight());
        d.b(d.this);
      }
    });
    this.bkt = new b(this, this.aIX, this.bjZ);
    paramLatinIME = com.emoji.common.c.g("CACHE_PREDICT_STICKER_TEXT_FOR_EMOJI", this.aIX);
    if ((paramLatinIME instanceof Map)) {}
    for (this.bkJ = ((Map)paramLatinIME);; this.bkJ = new HashMap())
    {
      new Thread()
      {
        public final void run()
        {
          LatinIME localLatinIME = d.c(d.this);
          if (localLatinIME == null) {
            return;
          }
          List localList = localLatinIME.getPackageManager().getInstalledPackages(0);
          d localD = d.this;
          int i = -1;
          Object localObject3;
          if (i < localList.size())
          {
            long l = 0L;
            if (i == -1) {
              ??? = localLatinIME.getPackageName();
            }
            for (;;)
            {
              if (h.a(localLatinIME, "enable_gif_" + (String)???, Boolean.valueOf(true)).booleanValue()) {
                localD.a(localLatinIME, (String)???, l);
              }
              do
              {
                i += 1;
                break;
                localObject3 = (PackageInfo)localList.get(i);
                ??? = ((PackageInfo)localObject3).packageName;
              } while ((TextUtils.isEmpty((CharSequence)???)) || (!cd.b.bH((String)???)));
              l = ((PackageInfo)localObject3).firstInstallTime;
            }
          }
          try
          {
            synchronized (localD.bks)
            {
              localObject3 = localD.bks.keySet().iterator();
              Object localObject4;
              do
              {
                if (!((Iterator)localObject3).hasNext()) {
                  break;
                }
                localObject4 = (String)((Iterator)localObject3).next();
                localObject4 = (ArrayList)localD.bks.get(localObject4);
              } while ((localObject4 == null) || (((ArrayList)localObject4).size() == 0));
              Collections.sort((List)localObject4, localD.bkL);
            }
            MainActivity.f(localLatinIME, localList);
          }
          catch (IllegalArgumentException localIllegalArgumentException) {}
          for (;;)
          {
            return;
          }
        }
      }.start();
      return;
    }
  }
  
  private static String a(LatinIME paramLatinIME, Locale paramLocale)
  {
    return paramLatinIME.getFilesDir().getPath() + "/trending/" + paramLocale.toString();
  }
  
  /* Error */
  public static void a(LatinIME paramLatinIME, Locale paramLocale, String paramString)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokestatic 326	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +4 -> 8
    //   7: return
    //   8: aconst_null
    //   9: astore 5
    //   11: aconst_null
    //   12: astore 4
    //   14: aload 5
    //   16: astore_3
    //   17: aload_0
    //   18: aload_1
    //   19: invokestatic 328	com/android/inputmethod/latin/suggestions/d:a	(Lcom/android/inputmethod/latin/LatinIME;Ljava/util/Locale;)Ljava/lang/String;
    //   22: astore_0
    //   23: aload 5
    //   25: astore_3
    //   26: new 303	java/io/File
    //   29: dup
    //   30: new 303	java/io/File
    //   33: dup
    //   34: aload_0
    //   35: invokespecial 329	java/io/File:<init>	(Ljava/lang/String;)V
    //   38: invokevirtual 332	java/io/File:getParent	()Ljava/lang/String;
    //   41: invokespecial 329	java/io/File:<init>	(Ljava/lang/String;)V
    //   44: astore_1
    //   45: aload 5
    //   47: astore_3
    //   48: aload_1
    //   49: invokevirtual 336	java/io/File:exists	()Z
    //   52: ifne +11 -> 63
    //   55: aload 5
    //   57: astore_3
    //   58: aload_1
    //   59: invokevirtual 339	java/io/File:mkdirs	()Z
    //   62: pop
    //   63: aload 5
    //   65: astore_3
    //   66: new 341	java/io/FileOutputStream
    //   69: dup
    //   70: aload_0
    //   71: invokespecial 342	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   74: astore_0
    //   75: aload_0
    //   76: aload_2
    //   77: invokevirtual 348	java/lang/String:getBytes	()[B
    //   80: invokevirtual 352	java/io/FileOutputStream:write	([B)V
    //   83: aload_0
    //   84: invokevirtual 355	java/io/FileOutputStream:close	()V
    //   87: return
    //   88: astore_0
    //   89: aload_0
    //   90: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   93: return
    //   94: astore_1
    //   95: aload 4
    //   97: astore_0
    //   98: aload_0
    //   99: astore_3
    //   100: aload_1
    //   101: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   104: aload_0
    //   105: astore_3
    //   106: aload_1
    //   107: invokevirtual 361	java/io/IOException:getMessage	()Ljava/lang/String;
    //   110: pop
    //   111: aload_0
    //   112: ifnull -105 -> 7
    //   115: aload_0
    //   116: invokevirtual 355	java/io/FileOutputStream:close	()V
    //   119: return
    //   120: astore_0
    //   121: aload_0
    //   122: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   125: return
    //   126: astore_0
    //   127: aload_3
    //   128: ifnull +7 -> 135
    //   131: aload_3
    //   132: invokevirtual 355	java/io/FileOutputStream:close	()V
    //   135: aload_0
    //   136: athrow
    //   137: astore_1
    //   138: aload_1
    //   139: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   142: goto -7 -> 135
    //   145: astore_1
    //   146: aload_0
    //   147: astore_3
    //   148: aload_1
    //   149: astore_0
    //   150: goto -23 -> 127
    //   153: astore_1
    //   154: goto -56 -> 98
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	paramLatinIME	LatinIME
    //   0	157	1	paramLocale	Locale
    //   0	157	2	paramString	String
    //   16	132	3	localObject1	Object
    //   12	84	4	localObject2	Object
    //   9	55	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   83	87	88	java/io/IOException
    //   17	23	94	java/io/IOException
    //   26	45	94	java/io/IOException
    //   48	55	94	java/io/IOException
    //   58	63	94	java/io/IOException
    //   66	75	94	java/io/IOException
    //   115	119	120	java/io/IOException
    //   17	23	126	finally
    //   26	45	126	finally
    //   48	55	126	finally
    //   58	63	126	finally
    //   66	75	126	finally
    //   100	104	126	finally
    //   106	111	126	finally
    //   131	135	137	java/io/IOException
    //   75	83	145	finally
    //   75	83	153	java/io/IOException
  }
  
  private boolean aR(String paramString)
  {
    try
    {
      if (this.bkw == null) {
        return false;
      }
      paramString = new JSONObject(paramString);
      JSONArray localJSONArray;
      int j;
      int i;
      if (paramString.has("results"))
      {
        localJSONArray = paramString.getJSONArray("results");
        j = localJSONArray.length();
        new StringBuilder("HotWordJson,本地可用热词---->").append(this.bkw.size()).append("，网络可用热词--->").append(j);
        if (j != 0)
        {
          localMap = this.bks;
          i = 0;
        }
      }
      for (;;)
      {
        if (i < j) {}
        try
        {
          String str = localJSONArray.getString(i);
          paramString = str;
          if (str.contains(" ")) {
            paramString = str.replace(" ", "+");
          }
          this.bkw.add(paramString);
          i += 1;
        }
        finally {}
      }
      new StringBuilder("HotWordJson,当前Set中有多少可用热词----》").append(this.bkw.size());
      return true;
    }
    catch (JSONException paramString)
    {
      Map localMap;
      paramString.printStackTrace();
    }
    return false;
  }
  
  private void d(String paramString, Context paramContext)
  {
    try
    {
      a(paramContext, paramString, paramContext.getPackageManager().getPackageInfo(paramString, 0).firstInstallTime);
      return;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      Log.e("GifPredictView", "addGifForMap: ", paramString);
    }
  }
  
  public static Uri n(Context paramContext, int paramInt)
  {
    return Uri.parse("android.resource://" + paramContext.getPackageName() + "/drawable/" + paramInt);
  }
  
  private void n(Context paramContext, String paramString)
  {
    for (;;)
    {
      int i;
      synchronized (this.bks)
      {
        Iterator localIterator = this.bks.entrySet().iterator();
        if (localIterator.hasNext())
        {
          Object localObject = (Map.Entry)localIterator.next();
          localObject = (ArrayList)this.bks.get(((Map.Entry)localObject).getKey());
          if (localObject == null) {
            continue;
          }
          i = ((ArrayList)localObject).size() - 1;
          if (i >= 0)
          {
            if (!TextUtils.equals(((GifPack)((ArrayList)localObject).get(i)).packName, paramString)) {
              break label164;
            }
            ((ArrayList)localObject).remove(i);
            break label164;
          }
          if (((ArrayList)localObject).size() != 0) {
            continue;
          }
          localIterator.remove();
        }
      }
      if (this.bks.size() == 0) {
        a(paramContext, paramContext.getPackageName(), 0L);
      }
      return;
      label164:
      i -= 1;
    }
  }
  
  private void pT()
  {
    Object localObject = new File(a(this.aIX, this.bkF));
    long l = ((File)localObject).lastModified();
    if ((System.currentTimeMillis() - l > 86400000L) || (((File)localObject).length() == 0L)) {
      try
      {
        if (this.bkG) {
          return;
        }
        this.bkG = true;
        localObject = new JSONObject();
        final Locale localLocale = this.bkF;
        final LatinIME localLatinIME = this.aIX;
        if (localLatinIME == null) {
          return;
        }
        ((JSONObject)localObject).put("lang", localLocale.toString());
        fv.c localC = (fv.c)fu.a.aeD().hW(com.emoji.network.d.ts());
        localC.content = ((JSONObject)localObject).toString();
        localC.fEi = com.emoji.network.c.bEx;
        localC.aeE().a(new fw.a()
        {
          public final void a(Exception paramAnonymousException, int paramAnonymousInt)
          {
            if (paramAnonymousException != null) {
              paramAnonymousException.getMessage();
            }
            d.h(d.this);
          }
        });
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
    }
    this.bkH = l;
    q(this.aIX);
  }
  
  private void pU()
  {
    if (this.bjZ.isEmpty()) {
      return;
    }
    this.bjZ.size();
    this.bjZ.clear();
    this.bkt.notifyDataSetChanged();
  }
  
  /* Error */
  private boolean q(LatinIME paramLatinIME)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: iconst_0
    //   5: ireturn
    //   6: new 677	java/io/FileInputStream
    //   9: dup
    //   10: aload_1
    //   11: aload_0
    //   12: getfield 124	com/android/inputmethod/latin/suggestions/d:bkF	Ljava/util/Locale;
    //   15: invokestatic 328	com/android/inputmethod/latin/suggestions/d:a	(Lcom/android/inputmethod/latin/LatinIME;Ljava/util/Locale;)Ljava/lang/String;
    //   18: invokespecial 678	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   21: astore_3
    //   22: aload_3
    //   23: astore_1
    //   24: aload_3
    //   25: invokestatic 683	com/emoji/common/d:h	(Ljava/io/InputStream;)Ljava/lang/String;
    //   28: astore 4
    //   30: aload_3
    //   31: astore_1
    //   32: new 296	java/lang/StringBuilder
    //   35: dup
    //   36: ldc_w 685
    //   39: invokespecial 446	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   42: aload 4
    //   44: invokevirtual 686	java/lang/String:length	()I
    //   47: invokevirtual 452	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   50: pop
    //   51: aload_3
    //   52: astore_1
    //   53: aload_0
    //   54: aload 4
    //   56: invokespecial 419	com/android/inputmethod/latin/suggestions/d:aR	(Ljava/lang/String;)Z
    //   59: istore_2
    //   60: aload_3
    //   61: invokevirtual 687	java/io/FileInputStream:close	()V
    //   64: iload_2
    //   65: ireturn
    //   66: astore_1
    //   67: aload_1
    //   68: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   71: iconst_0
    //   72: istore_2
    //   73: goto -9 -> 64
    //   76: astore 4
    //   78: aconst_null
    //   79: astore_3
    //   80: aload_3
    //   81: astore_1
    //   82: aload 4
    //   84: invokevirtual 688	java/io/FileNotFoundException:printStackTrace	()V
    //   87: aload_3
    //   88: ifnull +52 -> 140
    //   91: aload_3
    //   92: invokevirtual 687	java/io/FileInputStream:close	()V
    //   95: iconst_0
    //   96: istore_2
    //   97: goto -33 -> 64
    //   100: astore_1
    //   101: aload_1
    //   102: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   105: iconst_0
    //   106: istore_2
    //   107: goto -43 -> 64
    //   110: astore_3
    //   111: aconst_null
    //   112: astore_1
    //   113: aload_1
    //   114: ifnull +7 -> 121
    //   117: aload_1
    //   118: invokevirtual 687	java/io/FileInputStream:close	()V
    //   121: aload_3
    //   122: athrow
    //   123: astore_1
    //   124: aload_1
    //   125: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   128: goto -7 -> 121
    //   131: astore_3
    //   132: goto -19 -> 113
    //   135: astore 4
    //   137: goto -57 -> 80
    //   140: iconst_0
    //   141: istore_2
    //   142: goto -78 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	145	0	this	d
    //   0	145	1	paramLatinIME	LatinIME
    //   59	83	2	bool	boolean
    //   21	71	3	localFileInputStream	java.io.FileInputStream
    //   110	12	3	localObject1	Object
    //   131	1	3	localObject2	Object
    //   28	27	4	str	String
    //   76	7	4	localFileNotFoundException1	java.io.FileNotFoundException
    //   135	1	4	localFileNotFoundException2	java.io.FileNotFoundException
    // Exception table:
    //   from	to	target	type
    //   60	64	66	java/io/IOException
    //   6	22	76	java/io/FileNotFoundException
    //   91	95	100	java/io/IOException
    //   6	22	110	finally
    //   117	121	123	java/io/IOException
    //   24	30	131	finally
    //   32	51	131	finally
    //   53	60	131	finally
    //   82	87	131	finally
    //   24	30	135	java/io/FileNotFoundException
    //   32	51	135	java/io/FileNotFoundException
    //   53	60	135	java/io/FileNotFoundException
  }
  
  public final void a(final int paramInt, final List<ch.a> paramList, final boolean paramBoolean)
  {
    this.mHandler.post(new Runnable()
    {
      public final void run()
      {
        if (paramList != null) {
          synchronized (paramList)
          {
            d.a(d.this, paramInt, paramList, paramBoolean);
            return;
          }
        }
      }
    });
  }
  
  /* Error */
  final void a(Context paramContext, String paramString, long paramLong)
  {
    // Byte code:
    //   0: aload_1
    //   1: astore 9
    //   3: aload_2
    //   4: ifnull +10 -> 14
    //   7: aload_1
    //   8: aload_2
    //   9: invokestatic 706	com/emoji/common/d:u	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/Context;
    //   12: astore 9
    //   14: aload 9
    //   16: ifnonnull +4 -> 20
    //   19: return
    //   20: aload 9
    //   22: ldc_w 708
    //   25: ldc_w 710
    //   28: invokestatic 713	com/emoji/common/h:h	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)I
    //   31: istore 5
    //   33: iload 5
    //   35: ifeq -16 -> 19
    //   38: aconst_null
    //   39: astore 12
    //   41: aconst_null
    //   42: astore 11
    //   44: aload 9
    //   46: invokevirtual 714	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   49: iload 5
    //   51: invokevirtual 718	android/content/res/Resources:openRawResourceFd	(I)Landroid/content/res/AssetFileDescriptor;
    //   54: astore_1
    //   55: aload_1
    //   56: invokevirtual 724	android/content/res/AssetFileDescriptor:createInputStream	()Ljava/io/FileInputStream;
    //   59: astore 9
    //   61: new 726	java/io/InputStreamReader
    //   64: dup
    //   65: aload 9
    //   67: invokespecial 729	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   70: astore 10
    //   72: new 731	java/io/BufferedReader
    //   75: dup
    //   76: aload 10
    //   78: invokespecial 734	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   81: astore 12
    //   83: iconst_0
    //   84: istore 5
    //   86: aload 12
    //   88: invokevirtual 737	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   91: astore 11
    //   93: aload 11
    //   95: ifnull +277 -> 372
    //   98: aload 11
    //   100: invokestatic 326	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   103: ifne -17 -> 86
    //   106: aload 11
    //   108: invokevirtual 740	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   111: astore 14
    //   113: iconst_0
    //   114: istore 6
    //   116: aload 14
    //   118: bipush 44
    //   120: iload 6
    //   122: invokevirtual 744	java/lang/String:indexOf	(II)I
    //   125: istore 8
    //   127: iload 8
    //   129: iconst_m1
    //   130: if_icmpne +455 -> 585
    //   133: aload 14
    //   135: invokevirtual 686	java/lang/String:length	()I
    //   138: istore 7
    //   140: aload 14
    //   142: iload 6
    //   144: iload 7
    //   146: invokevirtual 748	java/lang/String:substring	(II)Ljava/lang/String;
    //   149: astore 11
    //   151: aload_0
    //   152: getfield 107	com/android/inputmethod/latin/suggestions/d:baI	I
    //   155: aload 11
    //   157: invokevirtual 686	java/lang/String:length	()I
    //   160: if_icmpge +12 -> 172
    //   163: aload_0
    //   164: aload 11
    //   166: invokevirtual 686	java/lang/String:length	()I
    //   169: putfield 107	com/android/inputmethod/latin/suggestions/d:baI	I
    //   172: aload 11
    //   174: ldc_w 460
    //   177: ldc_w 465
    //   180: invokevirtual 469	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   183: astore 16
    //   185: iload 7
    //   187: iconst_1
    //   188: iadd
    //   189: istore 6
    //   191: new 585	com/android/inputmethod/latin/suggestions/GifPack
    //   194: dup
    //   195: invokestatic 754	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   198: invokespecial 757	com/android/inputmethod/latin/suggestions/GifPack:<init>	(Landroid/os/Parcel;)V
    //   201: astore 17
    //   203: aload 17
    //   205: iload 5
    //   207: iconst_1
    //   208: iadd
    //   209: putfield 760	com/android/inputmethod/latin/suggestions/GifPack:bkn	I
    //   212: aload 17
    //   214: aload_2
    //   215: putfield 588	com/android/inputmethod/latin/suggestions/GifPack:packName	Ljava/lang/String;
    //   218: aload 17
    //   220: lload_3
    //   221: invokestatic 766	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   224: putfield 769	com/android/inputmethod/latin/suggestions/GifPack:firstInstallTime	Ljava/lang/Long;
    //   227: aload 17
    //   229: aload 16
    //   231: putfield 772	com/android/inputmethod/latin/suggestions/GifPack:text	Ljava/lang/String;
    //   234: aload_0
    //   235: getfield 103	com/android/inputmethod/latin/suggestions/d:bks	Ljava/util/Map;
    //   238: astore 15
    //   240: aload 15
    //   242: monitorenter
    //   243: aload_0
    //   244: getfield 103	com/android/inputmethod/latin/suggestions/d:bks	Ljava/util/Map;
    //   247: aload 16
    //   249: invokeinterface 581 2 0
    //   254: checkcast 109	java/util/ArrayList
    //   257: astore 13
    //   259: aload 13
    //   261: astore 11
    //   263: aload 13
    //   265: ifnonnull +26 -> 291
    //   268: new 109	java/util/ArrayList
    //   271: dup
    //   272: invokespecial 110	java/util/ArrayList:<init>	()V
    //   275: astore 11
    //   277: aload_0
    //   278: getfield 103	com/android/inputmethod/latin/suggestions/d:bks	Ljava/util/Map;
    //   281: aload 16
    //   283: aload 11
    //   285: invokeinterface 775 3 0
    //   290: pop
    //   291: aload 11
    //   293: aload 17
    //   295: invokevirtual 776	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   298: pop
    //   299: aload 15
    //   301: monitorexit
    //   302: iload 8
    //   304: iconst_m1
    //   305: if_icmpne -189 -> 116
    //   308: iload 5
    //   310: iconst_1
    //   311: iadd
    //   312: istore 5
    //   314: goto -228 -> 86
    //   317: astore_2
    //   318: aload 15
    //   320: monitorexit
    //   321: aload_2
    //   322: athrow
    //   323: astore_2
    //   324: aload_1
    //   325: astore_2
    //   326: aload 12
    //   328: astore_1
    //   329: aload 10
    //   331: ifnull +8 -> 339
    //   334: aload 10
    //   336: invokevirtual 777	java/io/InputStreamReader:close	()V
    //   339: aload 9
    //   341: ifnull +8 -> 349
    //   344: aload 9
    //   346: invokevirtual 687	java/io/FileInputStream:close	()V
    //   349: aload_1
    //   350: ifnull +7 -> 357
    //   353: aload_1
    //   354: invokevirtual 778	java/io/BufferedReader:close	()V
    //   357: aload_2
    //   358: ifnull -339 -> 19
    //   361: aload_2
    //   362: invokevirtual 779	android/content/res/AssetFileDescriptor:close	()V
    //   365: return
    //   366: astore_1
    //   367: aload_1
    //   368: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   371: return
    //   372: aload_1
    //   373: invokevirtual 779	android/content/res/AssetFileDescriptor:close	()V
    //   376: aload 10
    //   378: invokevirtual 777	java/io/InputStreamReader:close	()V
    //   381: aload 9
    //   383: ifnull +8 -> 391
    //   386: aload 9
    //   388: invokevirtual 687	java/io/FileInputStream:close	()V
    //   391: aload 12
    //   393: invokevirtual 778	java/io/BufferedReader:close	()V
    //   396: aload_1
    //   397: ifnull -378 -> 19
    //   400: aload_1
    //   401: invokevirtual 779	android/content/res/AssetFileDescriptor:close	()V
    //   404: return
    //   405: astore_1
    //   406: aload_1
    //   407: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   410: return
    //   411: astore_2
    //   412: aconst_null
    //   413: astore_1
    //   414: aconst_null
    //   415: astore 11
    //   417: aconst_null
    //   418: astore 10
    //   420: aconst_null
    //   421: astore 9
    //   423: aload 10
    //   425: ifnull +8 -> 433
    //   428: aload 10
    //   430: invokevirtual 777	java/io/InputStreamReader:close	()V
    //   433: aload 11
    //   435: ifnull +8 -> 443
    //   438: aload 11
    //   440: invokevirtual 687	java/io/FileInputStream:close	()V
    //   443: aload 9
    //   445: ifnull +8 -> 453
    //   448: aload 9
    //   450: invokevirtual 778	java/io/BufferedReader:close	()V
    //   453: aload_1
    //   454: ifnull +7 -> 461
    //   457: aload_1
    //   458: invokevirtual 779	android/content/res/AssetFileDescriptor:close	()V
    //   461: aload_2
    //   462: athrow
    //   463: astore_1
    //   464: aload_1
    //   465: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   468: goto -7 -> 461
    //   471: astore_2
    //   472: aconst_null
    //   473: astore 9
    //   475: aconst_null
    //   476: astore 11
    //   478: aconst_null
    //   479: astore 10
    //   481: goto -58 -> 423
    //   484: astore_2
    //   485: aconst_null
    //   486: astore 12
    //   488: aconst_null
    //   489: astore 10
    //   491: aload 9
    //   493: astore 11
    //   495: aload 12
    //   497: astore 9
    //   499: goto -76 -> 423
    //   502: astore_2
    //   503: aconst_null
    //   504: astore 12
    //   506: aload 9
    //   508: astore 11
    //   510: aload 12
    //   512: astore 9
    //   514: goto -91 -> 423
    //   517: astore_2
    //   518: aload 9
    //   520: astore 11
    //   522: aload 12
    //   524: astore 9
    //   526: goto -103 -> 423
    //   529: astore_1
    //   530: aconst_null
    //   531: astore_2
    //   532: aconst_null
    //   533: astore_1
    //   534: aload 11
    //   536: astore 10
    //   538: aload 12
    //   540: astore 9
    //   542: goto -213 -> 329
    //   545: astore_2
    //   546: aconst_null
    //   547: astore 13
    //   549: aload_1
    //   550: astore_2
    //   551: aload 11
    //   553: astore 10
    //   555: aload 12
    //   557: astore 9
    //   559: aload 13
    //   561: astore_1
    //   562: goto -233 -> 329
    //   565: astore_2
    //   566: aload_1
    //   567: astore_2
    //   568: aconst_null
    //   569: astore_1
    //   570: aload 11
    //   572: astore 10
    //   574: goto -245 -> 329
    //   577: astore_2
    //   578: aload_1
    //   579: astore_2
    //   580: aconst_null
    //   581: astore_1
    //   582: goto -253 -> 329
    //   585: iload 8
    //   587: istore 7
    //   589: goto -449 -> 140
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	592	0	this	d
    //   0	592	1	paramContext	Context
    //   0	592	2	paramString	String
    //   0	592	3	paramLong	long
    //   31	282	5	i	int
    //   114	76	6	j	int
    //   138	450	7	k	int
    //   125	461	8	m	int
    //   1	557	9	localObject1	Object
    //   70	503	10	localObject2	Object
    //   42	529	11	localObject3	Object
    //   39	517	12	localBufferedReader	java.io.BufferedReader
    //   257	303	13	localArrayList	ArrayList
    //   111	30	14	str1	String
    //   183	99	16	str2	String
    //   201	93	17	localGifPack	GifPack
    // Exception table:
    //   from	to	target	type
    //   243	259	317	finally
    //   268	291	317	finally
    //   291	302	317	finally
    //   318	321	317	finally
    //   86	93	323	java/lang/Exception
    //   98	113	323	java/lang/Exception
    //   116	127	323	java/lang/Exception
    //   133	140	323	java/lang/Exception
    //   140	172	323	java/lang/Exception
    //   172	185	323	java/lang/Exception
    //   191	243	323	java/lang/Exception
    //   321	323	323	java/lang/Exception
    //   372	376	323	java/lang/Exception
    //   334	339	366	java/io/IOException
    //   344	349	366	java/io/IOException
    //   353	357	366	java/io/IOException
    //   361	365	366	java/io/IOException
    //   376	381	405	java/io/IOException
    //   386	391	405	java/io/IOException
    //   391	396	405	java/io/IOException
    //   400	404	405	java/io/IOException
    //   44	55	411	finally
    //   428	433	463	java/io/IOException
    //   438	443	463	java/io/IOException
    //   448	453	463	java/io/IOException
    //   457	461	463	java/io/IOException
    //   55	61	471	finally
    //   61	72	484	finally
    //   72	83	502	finally
    //   86	93	517	finally
    //   98	113	517	finally
    //   116	127	517	finally
    //   133	140	517	finally
    //   140	172	517	finally
    //   172	185	517	finally
    //   191	243	517	finally
    //   321	323	517	finally
    //   372	376	517	finally
    //   44	55	529	java/lang/Exception
    //   55	61	545	java/lang/Exception
    //   61	72	565	java/lang/Exception
    //   72	83	577	java/lang/Exception
  }
  
  final void a(ch.a arg1)
  {
    if (??? == null) {}
    b localB;
    do
    {
      do
      {
        return;
      } while (???.getLocalDrawableId() == 0);
      localB = this.bkC;
      this.bkC = null;
    } while ((localB == null) || (TextUtils.isEmpty(localB.text)) || (TextUtils.isEmpty(localB.baO)));
    synchronized (this.bkJ)
    {
      this.bkJ.put(localB.text, localB.baO);
      return;
    }
  }
  
  public final boolean a(View paramView, String paramString1, String arg3)
  {
    if ((!this.bkG) && (System.currentTimeMillis() - this.bkH > 86400000L)) {
      pT();
    }
    this.bbV = paramView;
    this.bkC = null;
    if (TextUtils.isEmpty(paramString1)) {
      return false;
    }
    paramView = paramString1.trim();
    if (TextUtils.isEmpty(paramView)) {
      return false;
    }
    paramView = paramView.toLowerCase().replace(" ", "+");
    paramString1 = paramView;
    if (TextUtils.isEmpty(???)) {}
    synchronized (this.bkJ)
    {
      paramString1 = (String)this.bkJ.get(paramView);
      if (TextUtils.isEmpty(paramString1)) {
        break label838;
      }
      paramView = paramString1;
      paramString1 = paramView;
      if (TextUtils.equals(this.bkI, paramString1)) {
        return true;
      }
    }
    this.bkI = paramString1;
    label161:
    int i;
    for (;;)
    {
      synchronized (this.bks)
      {
        if (TextUtils.isEmpty(paramString1))
        {
          paramView = null;
          if ((!this.bkr) || (!this.bkw.contains(paramString1))) {
            break label844;
          }
          i = 1;
          label184:
          if ((paramView != null) || (i != 0)) {
            break;
          }
          this.mHandler.sendEmptyMessage(5500);
          return false;
        }
      }
      if (!this.bks.containsKey(paramString1))
      {
        paramView = null;
      }
      else
      {
        paramView = (List)this.bks.get(paramString1);
        if (paramView == null)
        {
          paramView = null;
        }
        else
        {
          if (!paramView.isEmpty()) {
            break label841;
          }
          paramView = null;
        }
      }
    }
    Locale localLocale = this.bkF;
    this.bkM = 0;
    this.mHandler.sendEmptyMessage(2200);
    Object localObject;
    if (paramView != null)
    {
      if (!paramView.isEmpty())
      {
        if (!TextUtils.isEmpty(???))
        {
          this.bkC = new b();
          this.bkC.text = ???.toLowerCase().replace(" ", "+");
          this.bkC.baO = paramString1;
        }
        if (this.bbV != null)
        {
          ??? = null;
          long l = 0L;
          paramView.size();
          localObject = paramView.iterator();
          paramView = ???;
          label381:
          if (((Iterator)localObject).hasNext())
          {
            ??? = (GifPack)((Iterator)localObject).next();
            Long localLong = ???.firstInstallTime;
            if (localLong.longValue() < l) {
              break label835;
            }
            l = localLong.longValue();
            ???.text = paramString1;
            paramView = ???;
            break label850;
          }
          if (paramView != null)
          {
            ??? = Message.obtain();
            ???.obj = paramView;
            ???.what = 3300;
            this.mHandler.sendMessage(???);
          }
        }
      }
      label465:
      if (i != 0)
      {
        MobclickAgent.onEvent(this.aIX, "hotwordgif_hit", localLocale.toString() + "#" + paramString1);
        paramView = this.aIX;
        if (paramView != null) {
          this.bkM = paramString1.hashCode();
        }
      }
    }
    for (;;)
    {
      synchronized (this.bkK)
      {
        if (this.bkK.indexOfKey(this.bkM) >= 0)
        {
          paramView = (com.android.inputmethod.latin.suggestions.gifpredict.b)this.bkK.get(this.bkM);
          paramString1 = paramView.aJw;
          if (paramString1 != null)
          {
            ??? = paramView.bka;
            if (??? != null) {
              new StringBuilder("状态:").append(paramView.mState).append(", mResponseCount=").append(paramView.bmt).append(", mSuccessCount=").append(paramView.bms);
            }
          }
        }
        switch (paramView.mState)
        {
        case 0: 
        case 1: 
          return true;
          this.mHandler.sendEmptyMessage(3300);
          break label465;
          if (at.c.aa(paramView)) {}
          break;
        }
      }
      paramView = new com.android.inputmethod.latin.suggestions.gifpredict.b(this, paramView, paramString1);
      this.bkK.put(this.bkM, paramView);
      continue;
      paramView.mState = 0;
      ??? = com.emoji.network.d.bK(paramView.bmn);
      localObject = MainApp.iW().getAsString(???);
      if (TextUtils.isEmpty((CharSequence)localObject))
      {
        paramString1.g(new a(???, paramView, paramView.bkU));
        MobclickAgent.onEvent(paramString1, "start_fast_gif_search", localLocale.toString() + "#" + paramView.bmn);
      }
      else
      {
        paramView.aV((String)localObject);
        continue;
        ???.a(paramView.bkU, paramView.bjZ, true);
        continue;
        label835:
        break label850;
        label838:
        break;
        label841:
        break label161;
        label844:
        i = 0;
        break label184;
        label850:
        break label381;
      }
    }
  }
  
  public final void c(Context paramContext, Intent paramIntent)
  {
    Object localObject = null;
    try
    {
      str = paramIntent.getDataString().substring(8);
      localObject = str;
    }
    catch (NullPointerException localNullPointerException)
    {
      do
      {
        String str;
        for (;;) {}
        if (TextUtils.equals(localNullPointerException, "android.intent.action.PACKAGE_REMOVED"))
        {
          n(paramContext, localObject);
          return;
        }
      } while (!TextUtils.equals(localNullPointerException, "keyboard.giflist.change"));
      boolean bool = paramIntent.getBooleanExtra("select", true);
      paramIntent = paramIntent.getStringExtra("packageName");
      if (!bool) {
        break label106;
      }
      d(paramIntent, paramContext);
      return;
      n(paramContext, paramIntent);
    }
    str = paramIntent.getAction();
    if (TextUtils.equals(str, "android.intent.action.PACKAGE_ADDED"))
    {
      d(localObject, paramContext);
      return;
    }
    label106:
  }
  
  public final void cO(int paramInt)
  {
    if (this.mHandler == null) {
      return;
    }
    this.mHandler.removeMessages(2425);
    this.bku = 15000;
    if (paramInt > 15000) {
      this.bku = paramInt;
    }
    this.mHandler.sendEmptyMessage(2425);
  }
  
  public final void cP(int paramInt)
  {
    synchronized (this.bkK)
    {
      if (this.bkK.indexOfKey(paramInt) >= 0)
      {
        ((com.android.inputmethod.latin.suggestions.gifpredict.b)this.bkK.get(paramInt)).recycle();
        this.bkK.remove(paramInt);
      }
      return;
    }
  }
  
  public final void hide()
  {
    this.bkE = false;
    this.bkD = null;
    this.bkM = 0;
    if (!isShowing()) {}
    while (this.aIX == null) {
      return;
    }
    if (this.aao != null) {
      this.aao.setAdapter(null);
    }
    pU();
    this.bku = 0;
    this.mParent.setVisibility(8);
    this.mHandler.removeMessages(2425);
  }
  
  public final boolean isShowing()
  {
    return this.mParent.getVisibility() == 0;
  }
  
  public final void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if (TextUtils.equals("show_popular_words_gifs_setting", paramString)) {
      pV();
    }
  }
  
  public final void p(LatinIME paramLatinIME)
  {
    if (this.bkw == null) {
      this.bkw = new HashSet();
    }
    this.bkw.clear();
    Object localObject = paramLatinIME.bdo;
    this.bkF = ((Locale)localObject);
    paramLatinIME = (String[])new w() {}.a(paramLatinIME.getResources(), (Locale)localObject);
    int j = paramLatinIME.length;
    int i = 0;
    while (i < j)
    {
      localObject = paramLatinIME[i].replace(" ", "+");
      this.bkw.add(localObject);
      i += 1;
    }
    pT();
  }
  
  public final void pV()
  {
    boolean bool2 = true;
    LatinIME localLatinIME = this.aIX;
    if (localLatinIME == null) {
      return;
    }
    String str = h.k(localLatinIME, "show_popular_words_gifs_setting", "1");
    int i = -1;
    boolean bool1;
    switch (str.hashCode())
    {
    default: 
      bool1 = bool2;
      switch (i)
      {
      default: 
        bool1 = bool2;
        if (at.c.aa(localLatinIME))
        {
          if (!at.c.ac(localLatinIME)) {
            break label163;
          }
          bool1 = bool2;
        }
        break;
      }
      break;
    }
    for (;;)
    {
      this.bkr = bool1;
      return;
      if (!str.equals("0")) {
        break;
      }
      i = 0;
      break;
      if (!str.equals("2")) {
        break;
      }
      i = 1;
      break;
      if (!str.equals("1")) {
        break;
      }
      i = 2;
      break;
      bool1 = false;
      continue;
      label163:
      bool1 = false;
    }
  }
  
  public final void recycle()
  {
    Object localObject1 = this.bkK;
    int i = 0;
    try
    {
      while (i < this.bkK.size())
      {
        ((com.android.inputmethod.latin.suggestions.gifpredict.b)this.bkK.valueAt(i)).recycle();
        i += 1;
      }
      this.bkK.clear();
      if (this.aJy != null)
      {
        this.aJy.recycle();
        this.aJy = null;
      }
      if (this.aIX != null)
      {
        com.emoji.common.c.a("CACHE_PREDICT_STICKER_TEXT_FOR_EMOJI", this.aIX, this.bkJ);
        this.bkA.unregisterOnSharedPreferenceChangeListener(this);
        LocalBroadcastManager.getInstance(this.aIX).unregisterReceiver(this.bkB);
        LocalBroadcastManager.getInstance(this.aIX).unregisterReceiver(this.bkq);
        if (this.aao != null)
        {
          this.aao.setAdapter(null);
          this.aao = null;
        }
        this.aIX = null;
        if (this.bkt != null)
        {
          localObject1 = this.bkt;
          if (((b)localObject1).mContext != null)
          {
            if (((b)localObject1).bkb != null)
            {
              LocalBroadcastManager.getInstance(((b)localObject1).mContext).unregisterReceiver(((b)localObject1).bkb);
              ((b)localObject1).bkb = null;
            }
            ((b)localObject1).mContext = null;
          }
          ((b)localObject1).bkc.clear();
          this.bkt = null;
        }
      }
      return;
    }
    finally {}
  }
  
  public static final class a
    implements Runnable
  {
    private String bkS;
    private fw.b bkT;
    private int bkU;
    
    public a(String paramString, fw.b paramB, int paramInt)
    {
      this.bkS = paramString;
      this.bkT = paramB;
      this.bkU = paramInt;
    }
    
    public final void run()
    {
      ((fv.a)((fv.a)((fv.a)fu.a.aeC().aB(this)).iy(this.bkU)).hW(this.bkS)).aeE().a(this.bkT);
    }
  }
  
  final class b
  {
    String baO;
    String text;
    
    b() {}
  }
}
