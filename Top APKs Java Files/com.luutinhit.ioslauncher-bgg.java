import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.luutinhit.launcher3.BubbleTextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;

public final class bgg
{
  private static final Object b = new Object();
  public final bgg.b a;
  private final bhc c = new bhc();
  private final bja d;
  private final Handler e;
  private final HashMap<String, Integer> f = new HashMap();
  private final HashMap<biz, Bitmap> g = new HashMap();
  private final Context h;
  private final PackageManager i;
  private final bit j;
  private final HashMap<bjp, bgg.a> k = new HashMap(50);
  private final int l;
  private final int m;
  private final int n;
  private final BitmapFactory.Options o;
  private String p;
  private Bitmap q;
  private Canvas r;
  private Paint s;
  
  public bgg(Context paramContext, bgk paramBgk)
  {
    this.h = paramContext;
    this.i = paramContext.getPackageManager();
    this.d = bja.a(this.h);
    this.j = bit.a(this.h);
    this.l = paramBgk.i;
    this.a = new bgg.b(paramContext, paramBgk.h);
    int i1 = paramBgk.h;
    this.e = new Handler(bgt.f());
    this.m = paramContext.getResources().getColor(2131099786);
    this.n = paramContext.getResources().getColor(2131099787);
    this.o = new BitmapFactory.Options();
    this.o.inPreferredConfig = Bitmap.Config.RGB_565;
    c();
  }
  
  private ContentValues a(Bitmap paramBitmap, String paramString, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("icon", bhr.b(paramBitmap));
    localContentValues.put("history", Long.valueOf(System.currentTimeMillis()));
    localContentValues.put("label", paramString);
    localContentValues.put("system_state", this.p);
    if (paramInt == 0)
    {
      localContentValues.put("icon_low_res", bhr.b(Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true)));
      return localContentValues;
    }
    try
    {
      if (this.q == null)
      {
        this.q = Bitmap.createBitmap(paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, Bitmap.Config.RGB_565);
        this.r = new Canvas(this.q);
        this.s = new Paint(3);
      }
      this.r.drawColor(paramInt);
      this.r.drawBitmap(paramBitmap, new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight()), new Rect(0, 0, this.q.getWidth(), this.q.getHeight()), this.s);
      localContentValues.put("icon_low_res", bhr.b(this.q));
      return localContentValues;
    }
    finally {}
  }
  
  private static Bitmap a(Cursor paramCursor, BitmapFactory.Options paramOptions)
  {
    paramCursor = paramCursor.getBlob(0);
    try
    {
      paramCursor = BitmapFactory.decodeByteArray(paramCursor, 0, paramCursor.length, paramOptions);
      return paramCursor;
    }
    catch (Exception paramCursor)
    {
      for (;;) {}
    }
    return null;
  }
  
  private Bitmap a(Bitmap paramBitmap)
  {
    int i1 = c(paramBitmap);
    if (i1 >= 0) {
      return a(paramBitmap, i1);
    }
    return b(paramBitmap);
  }
  
  private Bitmap a(Bitmap paramBitmap, int paramInt)
  {
    int i1 = paramBitmap.getWidth();
    int i2 = paramBitmap.getHeight();
    int i4 = paramInt * 2;
    int i3 = i1 - i4;
    i4 = i2 - i4;
    paramBitmap = Bitmap.createBitmap(paramBitmap, paramInt, paramInt, i3, i4);
    paramInt = (int)(this.h.getResources().getDimensionPixelSize(2131165405) * i3 / i1);
    Bitmap localBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Rect localRect = new Rect(0, 0, i3, i4);
    RectF localRectF = new RectF(localRect);
    Paint localPaint = new Paint();
    localPaint.setAntiAlias(true);
    localPaint.setColor(-1);
    localPaint.setStyle(Paint.Style.FILL);
    localCanvas.drawARGB(0, 0, 0, 0);
    float f1 = paramInt;
    localCanvas.drawRoundRect(localRectF, f1, f1, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    return Bitmap.createScaledBitmap(localBitmap, i1, i2, true);
  }
  
  private Bitmap a(bgg.a paramA, biz paramBiz)
  {
    if (paramA.a == null) {
      return a(paramBiz);
    }
    return paramA.a;
  }
  
  private Drawable a(Resources paramResources, int paramInt)
  {
    try
    {
      paramResources = paramResources.getDrawableForDensity(paramInt, this.l);
    }
    catch (Resources.NotFoundException paramResources)
    {
      for (;;) {}
    }
    paramResources = null;
    if (paramResources != null) {
      return paramResources;
    }
    return b();
  }
  
  private Drawable a(Object paramObject)
  {
    if (paramObject != null) {}
    for (;;)
    {
      try
      {
        if ((paramObject instanceof biq))
        {
          localObject1 = ((biq)paramObject).a().toString().toLowerCase();
          localObject2 = ((biq)paramObject).a().getPackageName().toLowerCase();
          localObject3 = ((biq)paramObject).c().toString().toLowerCase();
        }
        else
        {
          if (!(paramObject instanceof ApplicationInfo)) {
            break label1679;
          }
          localObject2 = ((ApplicationInfo)paramObject).packageName;
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append((String)localObject2);
          ((StringBuilder)localObject1).append("/");
          ((StringBuilder)localObject1).append(((ApplicationInfo)paramObject).className);
          localObject1 = ((StringBuilder)localObject1).toString();
          localObject3 = ((ApplicationInfo)paramObject).loadLabel(this.i).toString().toLowerCase();
        }
        if (localObject1 != null)
        {
          b(paramObject);
          if (b(paramObject)) {
            if ((!((String)localObject1).contains("dialer")) && (!((String)localObject1).contains("dialtacts")))
            {
              if ((!((String)localObject1).contains("gmail")) && ((((String)localObject1).contains("mms")) || (((String)localObject1).contains("messaging")) || (((String)localObject1).contains("conversations"))))
              {
                if (a((String)localObject2, 2131230967)) {
                  return b(2131230967);
                }
              }
              else if ((!((String)localObject1).contains("contacts")) && (!((String)localObject1).contains("socialphonebook")))
              {
                if ((!((String)localObject1).contains("google")) && (((String)localObject1).contains("settings")))
                {
                  if (a((String)localObject2, 2131231002)) {
                    return b(2131231002);
                  }
                }
                else if (((String)localObject1).contains("email"))
                {
                  if (a((String)localObject2, 2131230861)) {
                    return b(2131230861);
                  }
                }
                else if ((!((String)localObject1).contains("gallery")) && (!((String)localObject1).contains("album")))
                {
                  if (((String)localObject1).contains("vending"))
                  {
                    if (a((String)localObject2, 2131231006)) {
                      return b(2131231006);
                    }
                  }
                  else if ((((String)localObject1).contains("browser")) && (!((String)localObject1).contains("vending")) && (!((String)localObject1).contains("com.sonyericsson.video")) && (!((String)localObject1).contains("com.mobisystems.files")) && (!((String)localObject1).contains("com.dropbox.android")) && (!((String)localObject1).contains("com.miui.player")) && (!((String)localObject1).contains("com.lge.music")) && (!((String)localObject3).contains("music")) && (!((String)localObject3).contains("file")))
                  {
                    if (a((String)localObject2, 2131230998)) {
                      return b(2131230998);
                    }
                  }
                  else if ((!((String)localObject1).contains("video")) && (((String)localObject1).contains("health")))
                  {
                    if (a((String)localObject2, 2131230875)) {
                      return b(2131230875);
                    }
                  }
                  else if (((String)localObject1).contains("music"))
                  {
                    if (a((String)localObject2, 2131230968)) {
                      return b(2131230968);
                    }
                  }
                  else if (((String)localObject1).contains("calculator"))
                  {
                    if (a((String)localObject2, 2131230817)) {
                      return b(2131230817);
                    }
                  }
                  else if (((String)localObject1).contains("camera"))
                  {
                    if (a((String)localObject2, 2131230821)) {
                      return b(2131230821);
                    }
                  }
                  else if ((!((String)localObject1).contains("voice")) && ((((String)localObject1).contains("memo")) || (((String)localObject1).contains("note"))) && (!((String)localObject1).contains("com.sony.playmemories.mobile")))
                  {
                    if (a((String)localObject2, 2131230970)) {
                      return b(2131230970);
                    }
                  }
                  else if (((((String)localObject1).contains("voicenote")) || (((String)localObject1).contains("record"))) && (!((String)localObject1).contains("screenrecorder")))
                  {
                    if (a((String)localObject2, 2131231017)) {
                      return b(2131231017);
                    }
                  }
                  else if (((((String)localObject1).contains("clock")) || (((String)localObject1).contains("organizer"))) && (!((String)localObject1).contains("wearable")) && (!((String)localObject1).contains("smsorganizer")))
                  {
                    if (a((String)localObject2, 2131230825)) {
                      return b(2131230825);
                    }
                  }
                  else if (((String)localObject1).contains("calendar"))
                  {
                    if (a((String)localObject2, 2131230818)) {
                      return b(2131230818);
                    }
                  }
                  else if ((((String)localObject1).contains("compass")) && (a((String)localObject2, 2131230845))) {
                    return b(2131230845);
                  }
                }
                else if (a((String)localObject2, 2131230987)) {
                  return b(2131230987);
                }
              }
              else {
                return b(2131230846);
              }
            }
            else {
              return b(2131230819);
            }
          }
          if (((String)localObject1).contains("com.google.android.googlequicksearchbox"))
          {
            if (a((String)localObject2, 2131230870)) {
              return b(2131230870);
            }
          }
          else if (((String)localObject1).contains("com.android.chrome"))
          {
            if (a((String)localObject2, 2131230822)) {
              return b(2131230822);
            }
          }
          else if (((String)localObject1).contains("com.google.android.apps.translate"))
          {
            if (a((String)localObject2, 2131231011)) {
              return b(2131231011);
            }
          }
          else if (((String)localObject1).contains("com.google.android.play.games"))
          {
            if (a((String)localObject2, 2131230868)) {
              return b(2131230868);
            }
          }
          else if (((String)localObject1).contains("com.google.android.apps.photos"))
          {
            if (a((String)localObject2, 2131230872)) {
              return b(2131230872);
            }
          }
          else if (((String)localObject1).contains("com.google.android.apps.docs"))
          {
            if (a((String)localObject2, 2131230858)) {
              return b(2131230858);
            }
          }
          else if (((String)localObject1).contains("com.google.android.keep"))
          {
            if (a((String)localObject2, 2131230965)) {
              return b(2131230965);
            }
          }
          else if (((String)localObject1).contains("com.google.android.gm"))
          {
            if (a((String)localObject2, 2131230869)) {
              return b(2131230869);
            }
          }
          else if ((((String)localObject1).contains("com.google.android.youtube")) && (a((String)localObject2, 2131231031))) {
            return b(2131231031);
          }
          if (((String)localObject1).contains("com.sec.android.app.music"))
          {
            if (a((String)localObject2, 2131230968)) {
              return b(2131230968);
            }
          }
          else if (((String)localObject1).contains("com.sec.android.app.sbrowser"))
          {
            if (a((String)localObject2, 2131230998)) {
              return b(2131230998);
            }
          }
          else if (((String)localObject1).contains("com.samsung.android.app.notes"))
          {
            if (a((String)localObject2, 2131230970)) {
              return b(2131230970);
            }
          }
          else if (((String)localObject1).contains("com.sec.android.app.popupcalculator"))
          {
            if (a((String)localObject2, 2131230817)) {
              return b(2131230817);
            }
          }
          else if (((String)localObject1).contains("com.sec.android.app.voicenote"))
          {
            if (a((String)localObject2, 2131231017)) {
              return b(2131231017);
            }
          }
          else if ((((String)localObject1).contains("com.google.android.apps.maps")) && (a((String)localObject2, 2131230871))) {
            return b(2131230871);
          }
          if ((!((String)localObject3).equals("facebook")) && (!((String)localObject1).contains("com.facebook.katana")) && (!((String)localObject1).contains("com.facebook.lite")))
          {
            if ((!((String)localObject3).equals("whatsapp")) && (!((String)localObject1).contains("com.whatsapp")))
            {
              if ((!((String)localObject3).equals("instagram")) && (!((String)localObject1).contains("com.instagram.android")))
              {
                if ((!((String)localObject3).equals("twitter")) && (!((String)localObject1).contains("com.twitter.android")))
                {
                  if ((!((String)localObject3).equals("snapchat")) && (!((String)localObject1).contains("com.snapchat.android")))
                  {
                    if ((!((String)localObject3).equals("dropbox")) && (!((String)localObject1).contains("com.dropbox.android")))
                    {
                      if ((!((String)localObject3).equals("line")) && (!((String)localObject1).contains("jp.naver.line.android")))
                      {
                        if ((!((String)localObject3).equals("viber")) && (!((String)localObject1).contains("com.viber.voip")))
                        {
                          if ((!((String)localObject3).equals("flipboard")) && (!((String)localObject1).contains("flipboard.app")))
                          {
                            if ((!((String)localObject3).equals("spotify")) && (!((String)localObject1).contains("com.spotify.music")))
                            {
                              if ((((String)localObject3).equals("uber")) || (((String)localObject1).contains("com.ubercab"))) {
                                return b(2131231013);
                              }
                            }
                            else {
                              return b(2131231005);
                            }
                          }
                          else {
                            return b(2131230865);
                          }
                        }
                        else {
                          return b(2131231014);
                        }
                      }
                      else {
                        return b(2131230966);
                      }
                    }
                    else {
                      return b(2131230859);
                    }
                  }
                  else {
                    return b(2131231004);
                  }
                }
                else {
                  return b(2131231012);
                }
              }
              else {
                return b(2131230962);
              }
            }
            else {
              return b(2131231030);
            }
          }
          else
          {
            paramObject = b(2131230864);
            return paramObject;
          }
        }
      }
      catch (Throwable paramObject)
      {
        paramObject.getMessage();
      }
      return null;
      label1679:
      Object localObject1 = null;
      Object localObject2 = localObject1;
      Object localObject3 = localObject2;
    }
  }
  
  private bgg.a a(ComponentName paramComponentName, biq paramBiq, biz paramBiz, boolean paramBoolean1, boolean paramBoolean2)
  {
    bjp localBjp = new bjp(paramComponentName, paramBiz);
    bgg.a localA2 = (bgg.a)this.k.get(localBjp);
    bgg.a localA1;
    if (localA2 != null)
    {
      localA1 = localA2;
      if (localA2.d)
      {
        localA1 = localA2;
        if (paramBoolean2) {}
      }
    }
    else
    {
      localA2 = new bgg.a();
      this.k.put(localBjp, localA2);
      if (!a(localBjp, localA2, paramBoolean2))
      {
        if (paramBiq != null)
        {
          paramComponentName = a(paramBiq);
          if (paramComponentName == null) {}
        }
        for (paramComponentName = a(bhr.a(paramComponentName, paramBiq.b(), this.h));; paramComponentName = a(paramBiz))
        {
          localA2.a = paramComponentName;
          break label241;
          paramComponentName = paramBiq.a(this.l);
          break;
          if (paramBoolean1)
          {
            localA1 = e(paramComponentName.getPackageName(), paramBiz);
            if (localA1 != null)
            {
              new StringBuilder("using package default icon for ").append(paramComponentName.toShortString());
              localA2.a = localA1.a;
              localA2.b = localA1.b;
              localA2.c = localA1.c;
            }
          }
          if (localA2.a != null) {
            break label241;
          }
          new StringBuilder("using default icon for ").append(paramComponentName.toShortString());
        }
      }
      label241:
      localA1 = localA2;
      if (TextUtils.isEmpty(localA2.b))
      {
        localA1 = localA2;
        if (paramBiq != null)
        {
          localA2.b = paramBiq.c();
          localA2.c = this.d.a(localA2.b, paramBiz);
          localA1 = localA2;
        }
      }
    }
    return localA1;
  }
  
  private void a(ContentValues paramContentValues, ComponentName paramComponentName, PackageInfo paramPackageInfo, long paramLong)
  {
    paramContentValues.put("componentName", paramComponentName.flattenToString());
    paramContentValues.put("profileId", Long.valueOf(paramLong));
    paramContentValues.put("lastUpdated", Long.valueOf(paramPackageInfo.lastUpdateTime));
    paramContentValues.put("version", Integer.valueOf(paramPackageInfo.versionCode));
    this.a.a(paramContentValues);
  }
  
  private static boolean a(int paramInt)
  {
    Color.alpha(paramInt);
    return ((0xFF000000 & paramInt) != 0) && (Color.alpha(paramInt) > 200);
  }
  
  /* Error */
  private boolean a(bjp paramBjp, bgg.a paramA, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 7
    //   9: aload_0
    //   10: getfield 116	bgg:a	Lbgg$b;
    //   13: astore 8
    //   15: iload_3
    //   16: ifeq +250 -> 266
    //   19: ldc -53
    //   21: astore 4
    //   23: goto +3 -> 26
    //   26: aload_1
    //   27: getfield 704	bjp:a	Landroid/content/ComponentName;
    //   30: invokevirtual 666	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   33: astore 9
    //   35: aload_0
    //   36: getfield 96	bgg:d	Lbja;
    //   39: aload_1
    //   40: getfield 707	bjp:b	Lbiz;
    //   43: invokevirtual 710	bja:a	(Lbiz;)J
    //   46: invokestatic 713	java/lang/Long:toString	(J)Ljava/lang/String;
    //   49: astore 10
    //   51: aload 8
    //   53: iconst_2
    //   54: anewarray 357	java/lang/String
    //   57: dup
    //   58: iconst_0
    //   59: aload 4
    //   61: aastore
    //   62: dup
    //   63: iconst_1
    //   64: ldc -62
    //   66: aastore
    //   67: ldc_w 715
    //   70: iconst_2
    //   71: anewarray 357	java/lang/String
    //   74: dup
    //   75: iconst_0
    //   76: aload 9
    //   78: aastore
    //   79: dup
    //   80: iconst_1
    //   81: aload 10
    //   83: aastore
    //   84: invokevirtual 718	bgg$b:a	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   87: astore 4
    //   89: aload 4
    //   91: invokeinterface 722 1 0
    //   96: ifeq +104 -> 200
    //   99: aload 7
    //   101: astore 5
    //   103: iload_3
    //   104: ifeq +9 -> 113
    //   107: aload_0
    //   108: getfield 149	bgg:o	Landroid/graphics/BitmapFactory$Options;
    //   111: astore 5
    //   113: aload_2
    //   114: aload 4
    //   116: aload 5
    //   118: invokestatic 724	bgg:a	(Landroid/database/Cursor;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   121: putfield 328	bgg$a:a	Landroid/graphics/Bitmap;
    //   124: aload_2
    //   125: iload_3
    //   126: putfield 613	bgg$a:d	Z
    //   129: aload_2
    //   130: aload 4
    //   132: iconst_1
    //   133: invokeinterface 728 2 0
    //   138: putfield 646	bgg$a:b	Ljava/lang/CharSequence;
    //   141: aload_2
    //   142: getfield 646	bgg$a:b	Ljava/lang/CharSequence;
    //   145: ifnonnull +22 -> 167
    //   148: aload_2
    //   149: ldc_w 730
    //   152: putfield 646	bgg$a:b	Ljava/lang/CharSequence;
    //   155: ldc_w 730
    //   158: astore_1
    //   159: aload_2
    //   160: aload_1
    //   161: putfield 648	bgg$a:c	Ljava/lang/CharSequence;
    //   164: goto +22 -> 186
    //   167: aload_0
    //   168: getfield 96	bgg:d	Lbja;
    //   171: aload_2
    //   172: getfield 646	bgg$a:b	Ljava/lang/CharSequence;
    //   175: aload_1
    //   176: getfield 707	bjp:b	Lbiz;
    //   179: invokevirtual 658	bja:a	(Ljava/lang/CharSequence;Lbiz;)Ljava/lang/CharSequence;
    //   182: astore_1
    //   183: goto -24 -> 159
    //   186: aload 4
    //   188: ifnull +10 -> 198
    //   191: aload 4
    //   193: invokeinterface 733 1 0
    //   198: iconst_1
    //   199: ireturn
    //   200: aload 4
    //   202: ifnull +50 -> 252
    //   205: aload 4
    //   207: invokeinterface 733 1 0
    //   212: iconst_0
    //   213: ireturn
    //   214: astore_2
    //   215: aload 4
    //   217: astore_1
    //   218: goto +10 -> 228
    //   221: goto +19 -> 240
    //   224: astore_2
    //   225: aload 5
    //   227: astore_1
    //   228: aload_1
    //   229: ifnull +9 -> 238
    //   232: aload_1
    //   233: invokeinterface 733 1 0
    //   238: aload_2
    //   239: athrow
    //   240: aload 4
    //   242: ifnull +10 -> 252
    //   245: aload 4
    //   247: invokeinterface 733 1 0
    //   252: iconst_0
    //   253: ireturn
    //   254: astore_1
    //   255: aload 6
    //   257: astore 4
    //   259: goto -19 -> 240
    //   262: astore_1
    //   263: goto -42 -> 221
    //   266: ldc -90
    //   268: astore 4
    //   270: goto -244 -> 26
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	273	0	this	bgg
    //   0	273	1	paramBjp	bjp
    //   0	273	2	paramA	bgg.a
    //   0	273	3	paramBoolean	boolean
    //   21	248	4	localObject1	Object
    //   1	225	5	localObject2	Object
    //   4	252	6	localObject3	Object
    //   7	93	7	localObject4	Object
    //   13	39	8	localB	bgg.b
    //   33	44	9	str1	String
    //   49	33	10	str2	String
    // Exception table:
    //   from	to	target	type
    //   89	99	214	finally
    //   107	113	214	finally
    //   113	155	214	finally
    //   159	164	214	finally
    //   167	183	214	finally
    //   9	15	224	finally
    //   26	89	224	finally
    //   9	15	254	android/database/sqlite/SQLiteException
    //   26	89	254	android/database/sqlite/SQLiteException
    //   89	99	262	android/database/sqlite/SQLiteException
    //   107	113	262	android/database/sqlite/SQLiteException
    //   113	155	262	android/database/sqlite/SQLiteException
    //   159	164	262	android/database/sqlite/SQLiteException
    //   167	183	262	android/database/sqlite/SQLiteException
  }
  
  private boolean a(String paramString, int paramInt)
  {
    if (!this.f.values().contains(Integer.valueOf(paramInt)))
    {
      this.f.put(paramString, Integer.valueOf(paramInt));
      return true;
    }
    return false;
  }
  
  private Bitmap b(Bitmap paramBitmap)
  {
    int i1 = paramBitmap.getWidth();
    int i2 = paramBitmap.getHeight();
    int i3 = this.h.getResources().getDimensionPixelSize(2131165405);
    Paint localPaint = new Paint();
    localPaint.setAntiAlias(true);
    localPaint.setColor(-1);
    localPaint.setStyle(Paint.Style.FILL);
    RectF localRectF = new RectF(0.0F, 0.0F, i1, i2);
    Bitmap localBitmap = Bitmap.createBitmap(i1, i2, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    float f1 = i3;
    localCanvas.drawRoundRect(localRectF, f1, f1, localPaint);
    localCanvas.drawBitmap(Bitmap.createScaledBitmap(paramBitmap, i1 * 4 / 5, i2 * 4 / 5, true), i1 / 10, i2 / 10, new Paint(2));
    return Bitmap.createScaledBitmap(localBitmap, i1, i2, true);
  }
  
  private Drawable b()
  {
    Resources localResources = Resources.getSystem();
    int i1;
    if (bhr.c) {
      i1 = 17301651;
    } else {
      i1 = 17629184;
    }
    return a(localResources, i1);
  }
  
  private Drawable b(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return this.h.getDrawable(paramInt);
    }
    return this.h.getResources().getDrawable(paramInt);
  }
  
  private static boolean b(Object paramObject)
  {
    if ((paramObject instanceof biq)) {
      return (((biq)paramObject).d().flags & 0x81) != 0;
    }
    return ((paramObject instanceof ApplicationInfo)) && ((((ApplicationInfo)paramObject).flags & 0x81) != 0);
  }
  
  private int c(Bitmap paramBitmap)
  {
    if (paramBitmap != null)
    {
      int i5 = paramBitmap.getWidth();
      int i6 = paramBitmap.getHeight();
      if ((i5 > 0) && (i6 > 0))
      {
        if (a(paramBitmap.getPixel(0, 0)))
        {
          i1 = i5 - 1;
          if (a(paramBitmap.getPixel(i1, 0)))
          {
            i2 = i6 - 1;
            if ((a(paramBitmap.getPixel(i1, i2))) && (a(paramBitmap.getPixel(0, i2)))) {
              return 0;
            }
          }
        }
        int i3 = this.h.getResources().getDimensionPixelSize(2131165405);
        int i4 = Math.min(i3, i5 / 9);
        int i2 = i4 / 6;
        int i1 = i2;
        if (i2 <= 0) {
          i1 = 1;
        }
        i5 -= 1;
        i6 -= 1;
        i2 = 0;
        while (i2 <= i4)
        {
          double d1 = i3;
          double d2 = (Math.sqrt(2.0D) - 1.0D) / Math.sqrt(2.0D);
          Double.isNaN(d1);
          double d3 = i2;
          double d4 = Math.sqrt(2.0D);
          Double.isNaN(d3);
          int i7 = (int)(d1 * d2 + d3 / d4);
          int i8 = i5 / 2;
          int i9 = i5 - i7;
          int i10 = i6 / 2;
          int i11 = i6 - i7;
          if ((a(paramBitmap.getPixel(i7, i7))) && (a(paramBitmap.getPixel(i8, i2))) && (a(paramBitmap.getPixel(i9, i7))) && (a(paramBitmap.getPixel(i5 - i2, i10))) && (a(paramBitmap.getPixel(i9, i11))) && (a(paramBitmap.getPixel(i8, i6 - i2))) && (a(paramBitmap.getPixel(i7, i11))) && (a(paramBitmap.getPixel(i2, i10)))) {
            return i2;
          }
          i2 += i1;
        }
      }
    }
    return -1;
  }
  
  private static bjp c(String paramString, biz paramBiz)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(".");
    return new bjp(new ComponentName(paramString, localStringBuilder.toString()), paramBiz);
  }
  
  private void c()
  {
    this.p = Locale.getDefault().toString();
  }
  
  private void d(String paramString, biz paramBiz)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.k.keySet().iterator();
    while (localIterator.hasNext())
    {
      bjp localBjp = (bjp)localIterator.next();
      if ((localBjp.a.getPackageName().equals(paramString)) && (localBjp.b.equals(paramBiz))) {
        localHashSet.add(localBjp);
      }
    }
    paramString = localHashSet.iterator();
    while (paramString.hasNext())
    {
      paramBiz = (bjp)paramString.next();
      this.k.remove(paramBiz);
    }
  }
  
  private bgg.a e(String paramString, biz paramBiz)
  {
    bjp localBjp = c(paramString, paramBiz);
    bgg.a localA = (bgg.a)this.k.get(localBjp);
    int i2;
    if (localA != null)
    {
      localObject = localA;
      if (!localA.d) {}
    }
    else
    {
      localA = new bgg.a();
      i2 = 1;
      i1 = i2;
      if (a(localBjp, localA, false)) {}
    }
    try
    {
      if (!biz.a().equals(paramBiz)) {
        break label288;
      }
      i1 = 0;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        ApplicationInfo localApplicationInfo;
        continue;
        i1 = 8192;
      }
    }
    Object localObject = this.i.getPackageInfo(paramString, i1);
    localApplicationInfo = ((PackageInfo)localObject).applicationInfo;
    if (localApplicationInfo != null)
    {
      paramString = a(localObject);
      if (paramString != null) {}
      for (paramString = bhr.a(paramString, paramBiz, this.h);; paramString = bhr.a(localApplicationInfo.loadIcon(this.i), paramBiz, this.h))
      {
        localA.a = a(paramString);
        break;
      }
      localA.b = localApplicationInfo.loadLabel(this.i);
      localA.c = this.d.a(localA.b, paramBiz);
      localA.d = false;
      a(a(localA.a, localA.b.toString(), this.n), localBjp.a, (PackageInfo)localObject, this.d.a(paramBiz));
      i1 = i2;
    }
    else
    {
      throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
      i1 = 0;
    }
    localObject = localA;
    if (i1 != 0)
    {
      this.k.put(localBjp, localA);
      localObject = localA;
    }
    return localObject;
  }
  
  final ContentValues a(biq paramBiq, boolean paramBoolean)
  {
    Object localObject3 = new bjp(paramBiq.a(), paramBiq.b());
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (!paramBoolean)
    {
      localObject3 = (bgg.a)this.k.get(localObject3);
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        localObject1 = localObject2;
        if (!((bgg.a)localObject3).d) {
          if (((bgg.a)localObject3).a == null) {
            localObject1 = localObject2;
          } else {
            localObject1 = localObject3;
          }
        }
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject2 = new bgg.a();
      localObject1 = a(paramBiq);
      if (localObject1 != null) {}
      for (;;)
      {
        ((bgg.a)localObject2).a = a(bhr.a((Drawable)localObject1, paramBiq.b(), this.h));
        break;
        localObject1 = paramBiq.a(this.l);
      }
    }
    ((bgg.a)localObject2).b = paramBiq.c();
    ((bgg.a)localObject2).c = this.d.a(((bgg.a)localObject2).b, paramBiq.b());
    this.k.put(new bjp(paramBiq.a(), paramBiq.b()), localObject2);
    return a(((bgg.a)localObject2).a, ((bgg.a)localObject2).b.toString(), this.m);
  }
  
  public final Bitmap a(Intent paramIntent, biz paramBiz)
  {
    try
    {
      ComponentName localComponentName = paramIntent.getComponent();
      if (localComponentName == null)
      {
        paramIntent = a(paramBiz);
        return paramIntent;
      }
      paramIntent = a(localComponentName, this.j.a(paramIntent, paramBiz), paramBiz, true, false).a;
      return paramIntent;
    }
    finally {}
  }
  
  public final Bitmap a(biz paramBiz)
  {
    try
    {
      if (!this.g.containsKey(paramBiz)) {
        this.g.put(paramBiz, bhr.a(b(), paramBiz, this.h));
      }
      paramBiz = (Bitmap)this.g.get(paramBiz);
      return paramBiz;
    }
    finally {}
  }
  
  public final bgg.c a(final BubbleTextView paramBubbleTextView, final bgm paramBgm)
  {
    paramBubbleTextView = new Runnable()
    {
      public final void run()
      {
        if ((paramBgm instanceof bfg))
        {
          bgg.this.a((bfg)paramBgm, null, false);
        }
        else
        {
          Object localObject;
          if ((paramBgm instanceof bhl))
          {
            bhl localBhl = (bhl)paramBgm;
            bgg localBgg = bgg.this;
            if (localBhl.C != null) {
              localObject = localBhl.C;
            } else {
              localObject = localBhl.a;
            }
            localBgg.a(localBhl, (Intent)localObject, localBhl.x, false);
          }
          else if ((paramBgm instanceof bjk))
          {
            localObject = (bjk)paramBgm;
            bgg.this.a(((bjk)localObject).c, ((bjk)localObject).x, (bjk)localObject);
          }
        }
        bgg.a(bgg.this).execute(new Runnable()
        {
          public final void run()
          {
            bgg.1.this.b.a(bgg.1.this.a);
          }
        });
      }
    };
    this.e.post(paramBubbleTextView);
    return new bgg.c(paramBubbleTextView, this.e);
  }
  
  public final void a(ComponentName paramComponentName, Bitmap paramBitmap, String paramString, long paramLong, bgk paramBgk)
  {
    try
    {
      this.h.getPackageManager().getActivityIcon(paramComponentName);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    paramBitmap = a(Bitmap.createScaledBitmap(paramBitmap, paramBgk.h, paramBgk.h, true), paramString, 0);
    paramBitmap.put("componentName", paramComponentName.flattenToString());
    paramBitmap.put("profileId", Long.valueOf(paramLong));
    this.a.a(paramBitmap);
  }
  
  public final void a(ComponentName paramComponentName, biz paramBiz)
  {
    try
    {
      this.k.remove(new bjp(paramComponentName, paramBiz));
      if (paramComponentName != null) {
        this.f.remove(paramComponentName.getPackageName());
      }
      return;
    }
    finally {}
  }
  
  public final void a(bfg paramBfg)
  {
    try
    {
      bgg.a localA = a(paramBfg.d, null, paramBfg.x, false, paramBfg.c);
      if ((localA.a != null) && (!a(localA.a, paramBfg.x)))
      {
        paramBfg.u = bhr.a(localA.b);
        paramBfg.b = localA.a;
        paramBfg.v = localA.c;
        paramBfg.c = localA.d;
      }
      return;
    }
    finally
    {
      paramBfg = finally;
      throw paramBfg;
    }
  }
  
  public final void a(bfg paramBfg, biq paramBiq, boolean paramBoolean)
  {
    if (paramBiq == null) {}
    try
    {
      biz localBiz = paramBfg.x;
      break label21;
      localBiz = paramBiq.b();
      label21:
      paramBiq = a(paramBfg.d, paramBiq, localBiz, false, paramBoolean);
      paramBfg.u = bhr.a(paramBiq.b);
      paramBfg.b = a(paramBiq, localBiz);
      paramBfg.v = paramBiq.c;
      paramBfg.c = paramBiq.d;
      return;
    }
    finally
    {
      for (;;) {}
    }
    throw paramBfg;
  }
  
  public final void a(bhl paramBhl, ComponentName paramComponentName, biq paramBiq, biz paramBiz, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      paramComponentName = a(paramComponentName, paramBiq, paramBiz, paramBoolean1, paramBoolean2);
      paramBhl.f = a(paramComponentName, paramBiz);
      paramBhl.u = bhr.a(paramComponentName.b);
      paramBhl.c = a(paramComponentName.a, paramBiz);
      paramBhl.d = paramComponentName.d;
      return;
    }
    finally
    {
      paramBhl = finally;
      throw paramBhl;
    }
  }
  
  public final void a(bhl paramBhl, Intent paramIntent, biz paramBiz, boolean paramBoolean)
  {
    try
    {
      ComponentName localComponentName = paramIntent.getComponent();
      if (localComponentName == null)
      {
        paramBhl.f = a(paramBiz);
        paramBhl.u = "";
        paramBhl.c = true;
        paramBhl.d = false;
        return;
      }
      a(paramBhl, localComponentName, this.j.a(paramIntent, paramBiz), paramBiz, true, paramBoolean);
      return;
    }
    finally {}
  }
  
  final void a(biq paramBiq, PackageInfo paramPackageInfo, long paramLong)
  {
    a(a(paramBiq, false), paramBiq.a(), paramPackageInfo, paramLong);
  }
  
  public final void a(String paramString)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("history", Long.valueOf(System.currentTimeMillis()));
      this.a.a(localContentValues, "componentName = ?", new String[] { paramString });
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public final void a(String paramString, biz paramBiz)
  {
    for (;;)
    {
      PackageInfo localPackageInfo;
      long l1;
      try
      {
        b(paramString, paramBiz);
      }
      finally {}
      try
      {
        localPackageInfo = this.i.getPackageInfo(paramString, 8192);
        l1 = this.d.a(paramBiz);
        paramString = this.j.a(paramString, paramBiz).iterator();
        if (paramString.hasNext()) {
          a((biq)paramString.next(), localPackageInfo, l1);
        } else {
          return;
        }
      }
      catch (PackageManager.NameNotFoundException paramString) {}
    }
  }
  
  public final void a(String paramString, biz paramBiz, Bitmap paramBitmap, CharSequence paramCharSequence)
  {
    try
    {
      d(paramString, paramBiz);
      bjp localBjp = c(paramString, paramBiz);
      paramBiz = (bgg.a)this.k.get(localBjp);
      paramString = paramBiz;
      if (paramBiz == null)
      {
        paramString = new bgg.a();
        this.k.put(localBjp, paramString);
      }
      if (!TextUtils.isEmpty(paramCharSequence)) {
        paramString.b = paramCharSequence;
      }
      if (paramBitmap != null) {
        paramString.a = bhr.a(paramBitmap, this.h);
      }
      return;
    }
    finally {}
  }
  
  public final void a(String paramString, biz paramBiz, bjk paramBjk)
  {
    try
    {
      paramString = e(paramString, paramBiz);
      paramBjk.a = a(paramString, paramBiz);
      paramBjk.u = bhr.a(paramString.b);
      paramBjk.b = paramString.d;
      paramBjk.v = paramString.c;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  /* Error */
  public final void a(Set<String> paramSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 128	bgg:e	Landroid/os/Handler;
    //   4: getstatic 60	bgg:b	Ljava/lang/Object;
    //   7: invokevirtual 973	android/os/Handler:removeCallbacksAndMessages	(Ljava/lang/Object;)V
    //   10: aload_0
    //   11: invokespecial 160	bgg:c	()V
    //   14: aload_0
    //   15: getfield 96	bgg:d	Lbja;
    //   18: invokevirtual 976	bja:b	()Ljava/util/List;
    //   21: invokeinterface 952 1 0
    //   26: astore 13
    //   28: aload 13
    //   30: invokeinterface 828 1 0
    //   35: ifeq +799 -> 834
    //   38: aload 13
    //   40: invokeinterface 831 1 0
    //   45: checkcast 833	biz
    //   48: astore 21
    //   50: aload_0
    //   51: getfield 103	bgg:j	Lbit;
    //   54: aconst_null
    //   55: aload 21
    //   57: invokevirtual 949	bit:a	(Ljava/lang/String;Lbiz;)Ljava/util/List;
    //   60: astore 15
    //   62: aload 15
    //   64: ifnull +770 -> 834
    //   67: aload 15
    //   69: invokeinterface 978 1 0
    //   74: ifeq +4 -> 78
    //   77: return
    //   78: invokestatic 847	biz:a	()Lbiz;
    //   81: aload 21
    //   83: invokevirtual 834	biz:equals	(Ljava/lang/Object;)Z
    //   86: ifeq +9 -> 95
    //   89: aload_1
    //   90: astore 14
    //   92: goto +8 -> 100
    //   95: invokestatic 983	java/util/Collections:emptySet	()Ljava/util/Set;
    //   98: astore 14
    //   100: aload_0
    //   101: getfield 96	bgg:d	Lbja;
    //   104: aload 21
    //   106: invokevirtual 710	bja:a	(Lbiz;)J
    //   109: lstore 9
    //   111: aload_0
    //   112: getfield 81	bgg:h	Landroid/content/Context;
    //   115: invokevirtual 87	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   118: astore 16
    //   120: new 69	java/util/HashMap
    //   123: dup
    //   124: invokespecial 70	java/util/HashMap:<init>	()V
    //   127: astore 17
    //   129: aload 16
    //   131: sipush 8192
    //   134: invokevirtual 987	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   137: invokeinterface 952 1 0
    //   142: astore 16
    //   144: aload 16
    //   146: invokeinterface 828 1 0
    //   151: ifeq +31 -> 182
    //   154: aload 16
    //   156: invokeinterface 831 1 0
    //   161: checkcast 672	android/content/pm/PackageInfo
    //   164: astore 18
    //   166: aload 17
    //   168: aload 18
    //   170: getfield 988	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   173: aload 18
    //   175: invokevirtual 617	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   178: pop
    //   179: goto -35 -> 144
    //   182: new 69	java/util/HashMap
    //   185: dup
    //   186: invokespecial 70	java/util/HashMap:<init>	()V
    //   189: astore 18
    //   191: aload 15
    //   193: invokeinterface 952 1 0
    //   198: astore 15
    //   200: aload 15
    //   202: invokeinterface 828 1 0
    //   207: ifeq +31 -> 238
    //   210: aload 15
    //   212: invokeinterface 831 1 0
    //   217: checkcast 346	biq
    //   220: astore 16
    //   222: aload 18
    //   224: aload 16
    //   226: invokevirtual 349	biq:a	()Landroid/content/ComponentName;
    //   229: aload 16
    //   231: invokevirtual 617	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   234: pop
    //   235: goto -35 -> 200
    //   238: new 812	java/util/HashSet
    //   241: dup
    //   242: invokespecial 813	java/util/HashSet:<init>	()V
    //   245: astore 20
    //   247: new 990	java/util/Stack
    //   250: dup
    //   251: invokespecial 991	java/util/Stack:<init>	()V
    //   254: astore 19
    //   256: aload_0
    //   257: getfield 116	bgg:a	Lbgg$b;
    //   260: astore 15
    //   262: lload 9
    //   264: invokestatic 713	java/lang/Long:toString	(J)Ljava/lang/String;
    //   267: astore 16
    //   269: aload 15
    //   271: iconst_5
    //   272: anewarray 357	java/lang/String
    //   275: dup
    //   276: iconst_0
    //   277: ldc_w 993
    //   280: aastore
    //   281: dup
    //   282: iconst_1
    //   283: ldc_w 663
    //   286: aastore
    //   287: dup
    //   288: iconst_2
    //   289: ldc_w 670
    //   292: aastore
    //   293: dup
    //   294: iconst_3
    //   295: ldc_w 678
    //   298: aastore
    //   299: dup
    //   300: iconst_4
    //   301: ldc -57
    //   303: aastore
    //   304: ldc_w 995
    //   307: iconst_1
    //   308: anewarray 357	java/lang/String
    //   311: dup
    //   312: iconst_0
    //   313: aload 16
    //   315: aastore
    //   316: invokevirtual 718	bgg$b:a	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   319: astore 15
    //   321: aload 13
    //   323: astore 16
    //   325: lload 9
    //   327: lstore 11
    //   329: aload 15
    //   331: ldc_w 663
    //   334: invokeinterface 999 2 0
    //   339: istore_2
    //   340: aload 13
    //   342: astore 16
    //   344: lload 9
    //   346: lstore 11
    //   348: aload 15
    //   350: ldc_w 670
    //   353: invokeinterface 999 2 0
    //   358: istore 4
    //   360: aload 13
    //   362: astore 16
    //   364: lload 9
    //   366: lstore 11
    //   368: aload 15
    //   370: ldc_w 678
    //   373: invokeinterface 999 2 0
    //   378: istore_3
    //   379: aload 13
    //   381: astore 16
    //   383: lload 9
    //   385: lstore 11
    //   387: aload 15
    //   389: ldc_w 993
    //   392: invokeinterface 999 2 0
    //   397: istore 5
    //   399: aload 13
    //   401: astore 16
    //   403: lload 9
    //   405: lstore 11
    //   407: aload 15
    //   409: ldc -57
    //   411: invokeinterface 999 2 0
    //   416: istore 6
    //   418: aload 13
    //   420: astore 16
    //   422: lload 9
    //   424: lstore 11
    //   426: aload 15
    //   428: invokeinterface 722 1 0
    //   433: ifeq +235 -> 668
    //   436: aload 13
    //   438: astore 16
    //   440: lload 9
    //   442: lstore 11
    //   444: aload 15
    //   446: iload_2
    //   447: invokeinterface 728 2 0
    //   452: astore 22
    //   454: aload 22
    //   456: invokestatic 1003	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   459: astore 16
    //   461: aload 17
    //   463: aload 16
    //   465: invokevirtual 363	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   468: invokevirtual 610	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   471: checkcast 672	android/content/pm/PackageInfo
    //   474: astore 22
    //   476: aload 22
    //   478: ifnonnull +47 -> 525
    //   481: aload 14
    //   483: aload 16
    //   485: invokevirtual 363	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   488: invokeinterface 1004 2 0
    //   493: ifne +362 -> 855
    //   496: aload_0
    //   497: aload 16
    //   499: aload 21
    //   501: invokevirtual 1006	bgg:a	(Landroid/content/ComponentName;Lbiz;)V
    //   504: aload 20
    //   506: aload 15
    //   508: iload 5
    //   510: invokeinterface 1009 2 0
    //   515: invokestatic 686	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   518: invokevirtual 837	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   521: pop
    //   522: goto +333 -> 855
    //   525: aload 22
    //   527: getfield 857	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   530: getfield 772	android/content/pm/ApplicationInfo:flags	I
    //   533: ldc_w 1010
    //   536: iand
    //   537: ifne +128 -> 665
    //   540: aload 15
    //   542: iload 4
    //   544: invokeinterface 1014 2 0
    //   549: lstore 11
    //   551: aload 15
    //   553: iload_3
    //   554: invokeinterface 1009 2 0
    //   559: istore 7
    //   561: aload 18
    //   563: aload 16
    //   565: invokevirtual 841	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   568: checkcast 346	biq
    //   571: astore 23
    //   573: aload 22
    //   575: getfield 681	android/content/pm/PackageInfo:versionCode	I
    //   578: istore 8
    //   580: iload 7
    //   582: iload 8
    //   584: if_icmpne +274 -> 858
    //   587: lload 11
    //   589: aload 22
    //   591: getfield 676	android/content/pm/PackageInfo:lastUpdateTime	J
    //   594: lcmp
    //   595: ifne +25 -> 620
    //   598: aload_0
    //   599: getfield 201	bgg:p	Ljava/lang/String;
    //   602: aload 15
    //   604: iload 6
    //   606: invokeinterface 728 2 0
    //   611: invokestatic 1017	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   614: ifne +48 -> 662
    //   617: goto +3 -> 620
    //   620: aload 23
    //   622: ifnonnull +32 -> 654
    //   625: aload_0
    //   626: aload 16
    //   628: aload 21
    //   630: invokevirtual 1006	bgg:a	(Landroid/content/ComponentName;Lbiz;)V
    //   633: aload 20
    //   635: aload 15
    //   637: iload 5
    //   639: invokeinterface 1009 2 0
    //   644: invokestatic 686	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   647: invokevirtual 837	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   650: pop
    //   651: goto +11 -> 662
    //   654: aload 19
    //   656: aload 23
    //   658: invokevirtual 1018	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   661: pop
    //   662: goto -244 -> 418
    //   665: goto -247 -> 418
    //   668: aload 13
    //   670: astore 14
    //   672: lload 9
    //   674: lstore 11
    //   676: aload 15
    //   678: ifnull +70 -> 748
    //   681: goto +52 -> 733
    //   684: astore_1
    //   685: goto +18 -> 703
    //   688: aload 16
    //   690: astore 13
    //   692: lload 11
    //   694: lstore 9
    //   696: goto +24 -> 720
    //   699: astore_1
    //   700: aconst_null
    //   701: astore 15
    //   703: aload 15
    //   705: ifnull +10 -> 715
    //   708: aload 15
    //   710: invokeinterface 733 1 0
    //   715: aload_1
    //   716: athrow
    //   717: aconst_null
    //   718: astore 15
    //   720: aload 13
    //   722: astore 14
    //   724: lload 9
    //   726: lstore 11
    //   728: aload 15
    //   730: ifnull +18 -> 748
    //   733: aload 15
    //   735: invokeinterface 733 1 0
    //   740: lload 9
    //   742: lstore 11
    //   744: aload 13
    //   746: astore 14
    //   748: aload 20
    //   750: invokevirtual 1019	java/util/HashSet:isEmpty	()Z
    //   753: ifne +19 -> 772
    //   756: aload_0
    //   757: getfield 116	bgg:a	Lbgg$b;
    //   760: ldc_w 993
    //   763: aload 20
    //   765: invokestatic 1022	bhr:a	(Ljava/lang/String;Ljava/lang/Iterable;)Ljava/lang/String;
    //   768: aconst_null
    //   769: invokevirtual 1025	bgg$b:a	(Ljava/lang/String;[Ljava/lang/String;)V
    //   772: aload 18
    //   774: invokevirtual 1026	java/util/HashMap:isEmpty	()Z
    //   777: ifeq +11 -> 788
    //   780: aload 19
    //   782: invokevirtual 1027	java/util/Stack:isEmpty	()Z
    //   785: ifne +42 -> 827
    //   788: new 990	java/util/Stack
    //   791: dup
    //   792: invokespecial 991	java/util/Stack:<init>	()V
    //   795: astore 13
    //   797: aload 13
    //   799: aload 18
    //   801: invokevirtual 737	java/util/HashMap:values	()Ljava/util/Collection;
    //   804: invokevirtual 1031	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   807: pop
    //   808: new 19	bgg$d
    //   811: dup
    //   812: aload_0
    //   813: lload 11
    //   815: aload 17
    //   817: aload 13
    //   819: aload 19
    //   821: invokespecial 1034	bgg$d:<init>	(Lbgg;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;)V
    //   824: invokevirtual 1036	bgg$d:a	()V
    //   827: aload 14
    //   829: astore 13
    //   831: goto -803 -> 28
    //   834: return
    //   835: astore 14
    //   837: goto -120 -> 717
    //   840: astore 13
    //   842: goto -154 -> 688
    //   845: astore 14
    //   847: goto -151 -> 696
    //   850: astore 14
    //   852: goto -132 -> 720
    //   855: goto -437 -> 418
    //   858: goto -238 -> 620
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	861	0	this	bgg
    //   0	861	1	paramSet	Set<String>
    //   339	108	2	i1	int
    //   378	176	3	i2	int
    //   358	185	4	i3	int
    //   397	241	5	i4	int
    //   416	189	6	i5	int
    //   559	26	7	i6	int
    //   578	7	8	i7	int
    //   109	632	9	l1	long
    //   327	487	11	l2	long
    //   26	804	13	localObject1	Object
    //   840	1	13	localSQLiteException1	android.database.sqlite.SQLiteException
    //   90	738	14	localObject2	Object
    //   835	1	14	localSQLiteException2	android.database.sqlite.SQLiteException
    //   845	1	14	localSQLiteException3	android.database.sqlite.SQLiteException
    //   850	1	14	localSQLiteException4	android.database.sqlite.SQLiteException
    //   60	674	15	localObject3	Object
    //   118	571	16	localObject4	Object
    //   127	689	17	localHashMap	HashMap
    //   164	636	18	localObject5	Object
    //   254	566	19	localStack	Stack
    //   245	519	20	localHashSet	HashSet
    //   48	581	21	localBiz	biz
    //   452	138	22	localObject6	Object
    //   571	86	23	localBiq	biq
    // Exception table:
    //   from	to	target	type
    //   329	340	684	finally
    //   348	360	684	finally
    //   368	379	684	finally
    //   387	399	684	finally
    //   407	418	684	finally
    //   426	436	684	finally
    //   444	454	684	finally
    //   454	476	684	finally
    //   481	522	684	finally
    //   525	580	684	finally
    //   587	617	684	finally
    //   625	651	684	finally
    //   654	662	684	finally
    //   256	321	699	finally
    //   256	321	835	android/database/sqlite/SQLiteException
    //   329	340	840	android/database/sqlite/SQLiteException
    //   348	360	840	android/database/sqlite/SQLiteException
    //   368	379	840	android/database/sqlite/SQLiteException
    //   387	399	840	android/database/sqlite/SQLiteException
    //   407	418	840	android/database/sqlite/SQLiteException
    //   426	436	840	android/database/sqlite/SQLiteException
    //   444	454	840	android/database/sqlite/SQLiteException
    //   454	476	845	android/database/sqlite/SQLiteException
    //   481	522	845	android/database/sqlite/SQLiteException
    //   525	580	845	android/database/sqlite/SQLiteException
    //   587	617	850	android/database/sqlite/SQLiteException
    //   625	651	850	android/database/sqlite/SQLiteException
    //   654	662	850	android/database/sqlite/SQLiteException
  }
  
  public final boolean a(Bitmap paramBitmap, biz paramBiz)
  {
    return this.g.get(paramBiz) == paramBitmap;
  }
  
  public final void b(String paramString, biz paramBiz)
  {
    try
    {
      d(paramString, paramBiz);
      long l1 = this.d.a(paramBiz);
      paramBiz = this.a;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("/%");
      paramBiz.a("componentName LIKE ? AND profileId = ?", new String[] { localStringBuilder.toString(), Long.toString(l1) });
      this.f.remove(paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  static final class a
  {
    public Bitmap a;
    public CharSequence b = "";
    public CharSequence c = "";
    boolean d;
    
    a() {}
  }
  
  public static final class b
    extends bkf
  {
    private static final int b;
    
    static
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e0expr(TypeTransformer.java:441)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:710)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
    
    public b(Context paramContext, int paramInt)
    {
      super("app_icons.db", (b << 16) + paramInt, "icons");
    }
    
    public final void a(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS icons (_id INTEGER PRIMARY KEY AUTOINCREMENT, componentName TEXT NOT NULL, profileId INTEGER NOT NULL, lastUpdated INTEGER NOT NULL DEFAULT 0, version INTEGER NOT NULL DEFAULT 0, icon BLOB, icon_low_res BLOB, label TEXT, system_state TEXT, history INTEGER NOT NULL DEFAULT 0,UNIQUE (_id, componentName, profileId) );");
    }
  }
  
  public static final class c
  {
    private final Runnable a;
    private final Handler b;
    
    c(Runnable paramRunnable, Handler paramHandler)
    {
      this.a = paramRunnable;
      this.b = paramHandler;
    }
    
    public final void a()
    {
      this.b.removeCallbacks(this.a);
    }
  }
  
  final class d
    implements Runnable
  {
    private final long b;
    private final HashMap<String, PackageInfo> c;
    private final Stack<biq> d;
    private final Stack<biq> e;
    private final HashSet<String> f = new HashSet();
    
    d(HashMap<String, PackageInfo> paramHashMap, Stack<biq> paramStack1, Stack<biq> paramStack2)
    {
      this.b = ???;
      this.c = paramStack1;
      this.d = paramStack2;
      Object localObject;
      this.e = localObject;
    }
    
    final void a()
    {
      bgg.d(bgg.this).postAtTime(this, bgg.a(), SystemClock.uptimeMillis() + 1L);
    }
    
    public final void run()
    {
      Object localObject5;
      if (!this.e.isEmpty())
      {
        ??? = (biq)this.e.pop();
        Object localObject2 = ((biq)???).a().flattenToString();
        localObject5 = bgg.this.a((biq)???, true);
        bgg.b(bgg.this).a((ContentValues)localObject5, "componentName = ? AND profileId = ?", new String[] { localObject2, Long.toString(this.b) });
        this.f.add(((biq)???).a().getPackageName());
        if ((this.e.isEmpty()) && (!this.f.isEmpty()))
        {
          localObject2 = bgp.a().a;
          localObject5 = this.f;
          biz localBiz = bgg.c(bgg.this).a(this.b);
          bgt.b localB = ((bgt)localObject2).d();
          ArrayList localArrayList1 = new ArrayList();
          ArrayList localArrayList2 = new ArrayList();
          synchronized (bgt.p)
          {
            Iterator localIterator = bgt.q.iterator();
            while (localIterator.hasNext())
            {
              Object localObject6 = (bgm)localIterator.next();
              if (((localObject6 instanceof bhl)) && (localBiz.equals(((bgm)localObject6).x)) && (((bgm)localObject6).h == 0))
              {
                localObject6 = (bhl)localObject6;
                ComponentName localComponentName = ((bhl)localObject6).d();
                if ((localComponentName != null) && (((HashSet)localObject5).contains(localComponentName.getPackageName())))
                {
                  ((bhl)localObject6).b(((bgt)localObject2).v);
                  localArrayList2.add(localObject6);
                }
              }
            }
            ((bgt)localObject2).o.a((HashSet)localObject5, localBiz, localArrayList1);
            if (!localArrayList2.isEmpty()) {
              ((bgt)localObject2).c.a(new bgt.8((bgt)localObject2, localB, localArrayList2, localBiz));
            }
            if (!localArrayList1.isEmpty()) {
              ((bgt)localObject2).c.a(new bgt.9((bgt)localObject2, localB, localArrayList1));
            }
          }
        }
        a();
        return;
      }
      if (!this.d.isEmpty())
      {
        biq localBiq = (biq)this.d.pop();
        localObject5 = (PackageInfo)this.c.get(localBiq.a().getPackageName());
        if (localObject5 != null) {
          synchronized (bgg.this)
          {
            bgg.this.a(localBiq, (PackageInfo)localObject5, this.b);
          }
        }
        if (!this.d.isEmpty()) {
          a();
        }
      }
    }
  }
}
