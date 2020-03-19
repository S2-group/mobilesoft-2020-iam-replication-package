package com.mplus.lib;

import android.annotation.TargetApi;
import android.content.ClipboardManager;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Groups;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.RawContactsEntity;
import android.provider.Telephony.Mms;
import android.provider.Telephony.MmsSms;
import android.provider.Telephony.Sms;
import android.util.DisplayMetrics;
import android.widget.TextView;
import com.mplus.lib.ui.main.App;
import com.mplus.lib.util.ViewUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;

public final class bdd
  extends bem
{
  private static bdd a;
  private static final File b = bde.l;
  private static final bdf c = new bdf("Ťest Ďude", "0408975903", b, (byte)0);
  private static final bdf[] d = { c, new bdf("Johnny", "+61411903925", bde.m, 0), new bdf("Martina", "04119110903", bde.n, 0), new bdf("Chrissy", "0412980111", bde.o, 0), new bdf("Long long long very long winded name that goes on and on", "0412980112", bde.p, 0), new bdf("Email Dudette", "a@a.com", bde.p, 0) };
  private static final byte[] e = null;
  private static int f = 0;
  private static final byw[] g = { byz.a, byz.b, byz.c, byz.d, byz.e, byz.f, byz.g, byz.h };
  private static final bdg[] j = { new bdg("mdpi", 1.0F), new bdg("hdpi", 1.5F), new bdg("xhdpi", 2.0F), new bdg("xxhdpi", 3.0F) };
  private volatile Thread i;
  
  private bdd(Context paramContext)
  {
    super(paramContext);
  }
  
  public static void A()
  {
    ayo.b().a(true, 10, 1000, null);
  }
  
  public static void G()
  {
    Cursor localCursor = bbe.a().a(bbq.a, null, null, null, null);
    try
    {
      cow.a(localCursor, "sms");
      return;
    }
    finally
    {
      cqn.a(localCursor);
    }
  }
  
  /* Error */
  public static void H()
  {
    // Byte code:
    //   0: invokestatic 181	com/mplus/lib/bbe:a	()Lcom/mplus/lib/bbf;
    //   3: getstatic 207	com/mplus/lib/bbo:a	Landroid/net/Uri;
    //   6: aconst_null
    //   7: aconst_null
    //   8: aconst_null
    //   9: aconst_null
    //   10: invokevirtual 191	com/mplus/lib/bbf:a	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   13: astore_2
    //   14: aload_2
    //   15: ldc -47
    //   17: invokestatic 198	com/mplus/lib/cow:a	(Landroid/database/Cursor;Ljava/lang/String;)V
    //   20: aload_2
    //   21: invokeinterface 215 1 0
    //   26: ifeq +72 -> 98
    //   29: aload_2
    //   30: ldc -39
    //   32: invokeinterface 221 2 0
    //   37: istore_0
    //   38: getstatic 207	com/mplus/lib/bbo:a	Landroid/net/Uri;
    //   41: aload_2
    //   42: iload_0
    //   43: invokeinterface 225 2 0
    //   48: invokestatic 231	android/content/ContentUris:withAppendedId	(Landroid/net/Uri;J)Landroid/net/Uri;
    //   51: invokevirtual 237	android/net/Uri:buildUpon	()Landroid/net/Uri$Builder;
    //   54: ldc -17
    //   56: invokevirtual 245	android/net/Uri$Builder:appendPath	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   59: invokevirtual 249	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   62: astore_3
    //   63: invokestatic 181	com/mplus/lib/bbe:a	()Lcom/mplus/lib/bbf;
    //   66: aload_3
    //   67: aconst_null
    //   68: aconst_null
    //   69: aconst_null
    //   70: aconst_null
    //   71: invokevirtual 191	com/mplus/lib/bbf:a	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   74: astore_3
    //   75: aload_3
    //   76: ldc -5
    //   78: invokestatic 198	com/mplus/lib/cow:a	(Landroid/database/Cursor;Ljava/lang/String;)V
    //   81: aload_3
    //   82: invokeinterface 254 1 0
    //   87: aload_2
    //   88: invokeinterface 257 1 0
    //   93: istore_1
    //   94: iload_1
    //   95: ifne -66 -> 29
    //   98: aload_2
    //   99: invokestatic 203	com/mplus/lib/cqn:a	(Landroid/database/Cursor;)V
    //   102: return
    //   103: astore 4
    //   105: aload_3
    //   106: invokeinterface 254 1 0
    //   111: aload 4
    //   113: athrow
    //   114: astore_3
    //   115: aload_2
    //   116: invokestatic 203	com/mplus/lib/cqn:a	(Landroid/database/Cursor;)V
    //   119: aload_3
    //   120: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   37	6	0	k	int
    //   93	2	1	bool	boolean
    //   13	103	2	localCursor	Cursor
    //   62	44	3	localObject1	Object
    //   114	6	3	localObject2	Object
    //   103	9	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   75	81	103	finally
    //   14	29	114	finally
    //   29	75	114	finally
    //   81	94	114	finally
    //   105	114	114	finally
  }
  
  public static void J()
  {
    bdn.a().e();
    bdn.a().h();
    bdn.a().f();
  }
  
  public static void M()
  {
    cqf localCqf = new cqf();
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghij";
    arrayOfString[1] = "Àabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghij";
    int n = arrayOfString.length;
    int k = 0;
    while (k < n)
    {
      String str = arrayOfString[k];
      int m = 0;
      while (m < 10000)
      {
        bmn.a().a(str);
        m += 1;
      }
      auv.a("Txtr:app", "Time to remove diacritics string '%s...': %f ms (%d iterations)", new Object[] { str.substring(0, 10), Float.valueOf((float)localCqf.a() / 10000.0F), Integer.valueOf(10000) });
      k += 1;
    }
  }
  
  public static void N()
  {
    bbi localBbi = ayo.b().c;
    Object localObject = localBbi.a();
    ((bax)localObject).moveToFirst();
    String str = localObject.c().split(" ")[0];
    ((bax)localObject).close();
    localObject = new cqf();
    int k = 0;
    int n;
    for (int m = 0; k < 1000; m = n)
    {
      n = m;
      if (localBbi.b(str) != null) {
        n = m + 1;
      }
      k += 1;
    }
    auv.a("Txtr:app", "Time per lookup for recipient %s: %f ms (found %d of %d)", new Object[] { str, Float.valueOf((float)((cqf)localObject).a() / 1000.0F), Integer.valueOf(m), Integer.valueOf(1000) });
  }
  
  public static void O()
  {
    d(false);
    d(true);
  }
  
  public static void P()
  {
    cqf localCqf = new cqf();
    aya localAya = new aya("0408975903");
    String str = null;
    int k = 0;
    while (k < 10000)
    {
      localAya = new aya("0408975903");
      str = localAya.k();
      k += 1;
    }
    auv.a("Txtr:app", "Time per canonicalise %s to %s: %f ms (%d iterations)", new Object[] { localAya.d, str, Float.valueOf((float)localCqf.a() / 10000.0F), Integer.valueOf(10000) });
  }
  
  public static void V()
  {
    try
    {
      Object localObject = bde.e;
      localObject = (bhm)new bhe(cpe.c(bde.e)).a();
      ((bhm)localObject).b();
      ((bhm)localObject).a();
      ((bhm)localObject).c();
      ((bhm)localObject).e();
      try
      {
        localObject = bde.d;
        localObject = (bhk)new bhe(cpe.c(bde.d)).a();
        ((bhk)localObject).b();
        ((bhk)localObject).g();
        ((bhk)localObject).h();
        ((bhk)localObject).c();
        localObject = ((bgu)localObject).b;
        ((bgx)localObject).a.size();
        int k = 0;
        while (k < ((bgx)localObject).a.size())
        {
          bhf localBhf = ((bgx)localObject).a(k);
          localBhf.e();
          localBhf.j();
          cpe.a(new File(bde.b, "Part" + k), localBhf.b());
          k += 1;
        }
        return;
      }
      catch (Exception localException1) {}
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  public static void Y()
  {
    int n = 0;
    int m = 10485760;
    int i2;
    label111:
    for (;;)
    {
      i2 = n + (m - n) / 2;
      try
      {
        ayo.b().c.a(new ayb(new aya("0408975903")), bdh.a(i2), 0, System.currentTimeMillis(), System.currentTimeMillis(), false, null);
        i1 = 1;
      }
      catch (Exception localException)
      {
        int k;
        for (;;)
        {
          localException.getMessage();
          int i1 = 0;
          continue;
          k = (i2 - n) / 2 + n;
        }
        m = k;
      }
      if (i1 != 0)
      {
        k = (m - i2) / 2 + i2;
        if (i2 == k) {
          break;
        }
        if (i1 == 0) {
          break label111;
        }
        n = k;
      }
    }
  }
  
  private static long a(ayb paramAyb, aya paramAya, int paramInt, azb paramAzb, String paramString, boolean paramBoolean, long paramLong)
  {
    azs localAzs = new azs();
    localAzs.e = paramAzb;
    localAzs.d = paramString;
    paramAzb = new azt();
    paramAzb.a(localAzs);
    long l = System.currentTimeMillis();
    paramString = new azo();
    paramString.h = ayo.b().b(paramAyb);
    if (paramAya != null) {}
    for (paramAyb = paramAya.d;; paramAyb = null)
    {
      paramString.y = paramAyb;
      paramString.j = paramLong;
      paramString.m = true;
      paramString.g = 0;
      paramString.f = 1;
      paramString.u = paramAzb;
      paramString.e = l;
      ayo.b().b(paramString);
      return l;
    }
  }
  
  public static bdd a()
  {
    return a;
  }
  
  public static void a(int paramInt)
  {
    int k = 0;
    while (k < paramInt)
    {
      long l2 = System.currentTimeMillis();
      long l1 = l2;
      if (k % 3 != 0) {
        l1 = l2 - k * 60 * 1000L;
      }
      bbi localBbi = ayo.b().c;
      ayb localAyb = new ayb(new aya("0408975903"));
      StringBuilder localStringBuilder = new StringBuilder("Msg ");
      int m = f + 1;
      f = m;
      localBbi.a(localAyb, m + ", inserted at " + new Date(), 1, System.currentTimeMillis(), l1, false, null);
      k += 1;
    }
  }
  
  public static void a(int paramInt1, int paramInt2)
  {
    ayo.b().a(false, paramInt1, paramInt2, null);
  }
  
  public static void a(Context paramContext)
  {
    a = new bdd(paramContext);
  }
  
  private static void a(Uri paramUri, String paramString)
  {
    paramUri = bbe.a().a(paramUri, null, null, null, null);
    try
    {
      cow.a(paramUri, paramString);
      return;
    }
    finally
    {
      paramUri.close();
    }
  }
  
  private static void a(byw paramByw, List<bei> paramList)
  {
    if (paramByw == null) {}
    for (;;)
    {
      return;
      int k = 0;
      while (k < paramByw.d())
      {
        bei localBei = new bei();
        paramByw.a(k, localBei);
        paramList.add(localBei);
        k += 1;
      }
    }
  }
  
  public static void a(File paramFile, byte[] paramArrayOfByte)
  {
    if (!App.DEBUG_FAKE_MMSC) {}
    try
    {
      cpe.a(paramFile, paramArrayOfByte);
      return;
    }
    catch (IOException paramFile)
    {
      auv.b("Txtr:dbg", "Error writing debug file: %s", new Object[] { paramFile.getMessage() });
    }
  }
  
  private static void a(InputStream paramInputStream, int paramInt, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    azo localAzo = new azo();
    localAzo.h = new ayb(new aya("0408975903"));
    localAzo.j = System.currentTimeMillis();
    localAzo.m = false;
    localAzo.g = paramInt;
    localAzo.f = 1;
    azs localAzs = new azs();
    localAzs.e = new azd(cqg.a(paramInputStream, false));
    localAzs.c = paramString;
    localAzs.d = f(localAzs.c);
    localAzs.j();
    if (localAzs.g()) {}
    for (int k = 1;; k = 0)
    {
      localAzs.f = k;
      localAzo.u.a(localAzs);
      localAzo.r = 0;
      localAzo.n = bil.a(localAzo.h);
      ayo.b().e(localAzo);
      bnj.a().b(localAzo.e);
      bnj.a().b();
      if ((paramInt == 1) && (paramBoolean1)) {
        ayo.b().a(localAzo.e, bid.a(502, "Error!"));
      }
      if ((paramInt == 1) && (!paramBoolean1) && (paramBoolean2))
      {
        ayo.b().a(localAzo.e, 0, localAzo.n);
        ayo.b().x(localAzo.e);
      }
      return;
    }
  }
  
  private static void a(OutputStream paramOutputStream, boolean paramBoolean, int paramInt, String paramString)
  {
    bvn.a();
    String str = bvn.d(paramInt);
    paramOutputStream.write(("        <activity-alias a:enabled='" + Boolean.toString(paramBoolean) + "' a:icon='@drawable/" + str + "' a:label='@string/app_name' a:name='" + paramString + "' a:targetActivity='com.mplus.lib.ui.main.Main'>\n            <intent-filter>\n                <action a:name='android.intent.action.MAIN'/>\n                <category a:name='android.intent.category.LAUNCHER'/>\n" + "            </intent-filter>\n        </activity-alias>\n").getBytes());
  }
  
  private static void a(String paramString1, String paramString2, byte[] paramArrayOfByte, long paramLong, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    ContentProviderOperation.Builder localBuilder = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null);
    int k;
    if (paramBoolean)
    {
      k = 1;
      localArrayList.add(localBuilder.withValue("starred", Integer.valueOf(k)).build());
      localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data1", paramString1).build());
      if (paramArrayOfByte != null) {
        localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/photo").withValue("data15", paramArrayOfByte).build());
      }
      if (paramLong != 0L) {
        localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/group_membership").withValue("data1", Long.valueOf(paramLong)).build());
      }
      if (!paramString2.contains("@")) {
        break label259;
      }
      localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", paramString2).withValue("data2", Integer.valueOf(1)).build());
    }
    for (;;)
    {
      a(localArrayList);
      return;
      k = 0;
      break;
      label259:
      localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", paramString2).withValue("data2", Integer.valueOf(2)).build());
    }
  }
  
  private static void a(List<bei> paramList)
  {
    int[] arrayOfInt = new int[66];
    int[] tmp6_5 = arrayOfInt;
    tmp6_5[0] = 127877;
    int[] tmp12_6 = tmp6_5;
    tmp12_6[1] = 127938;
    int[] tmp18_12 = tmp12_6;
    tmp18_12[2] = 127939;
    int[] tmp24_18 = tmp18_12;
    tmp24_18[3] = 127940;
    int[] tmp30_24 = tmp24_18;
    tmp30_24[4] = 127943;
    int[] tmp36_30 = tmp30_24;
    tmp36_30[5] = 127946;
    int[] tmp42_36 = tmp36_30;
    tmp42_36[6] = 127947;
    int[] tmp49_42 = tmp42_36;
    tmp49_42[7] = 128066;
    int[] tmp56_49 = tmp49_42;
    tmp56_49[8] = 128067;
    int[] tmp63_56 = tmp56_49;
    tmp63_56[9] = 128070;
    int[] tmp70_63 = tmp63_56;
    tmp70_63[10] = 128071;
    int[] tmp77_70 = tmp70_63;
    tmp77_70[11] = 128072;
    int[] tmp84_77 = tmp77_70;
    tmp84_77[12] = 128073;
    int[] tmp91_84 = tmp84_77;
    tmp91_84[13] = 128074;
    int[] tmp98_91 = tmp91_84;
    tmp98_91[14] = 128075;
    int[] tmp105_98 = tmp98_91;
    tmp105_98[15] = 128076;
    int[] tmp112_105 = tmp105_98;
    tmp112_105[16] = 128077;
    int[] tmp119_112 = tmp112_105;
    tmp119_112[17] = 128078;
    int[] tmp126_119 = tmp119_112;
    tmp126_119[18] = 128079;
    int[] tmp133_126 = tmp126_119;
    tmp133_126[19] = 128080;
    int[] tmp140_133 = tmp133_126;
    tmp140_133[20] = 128102;
    int[] tmp147_140 = tmp140_133;
    tmp147_140[21] = 128103;
    int[] tmp154_147 = tmp147_140;
    tmp154_147[22] = 128104;
    int[] tmp161_154 = tmp154_147;
    tmp161_154[23] = 128105;
    int[] tmp168_161 = tmp161_154;
    tmp168_161[24] = 128110;
    int[] tmp175_168 = tmp168_161;
    tmp175_168[25] = 128112;
    int[] tmp182_175 = tmp175_168;
    tmp182_175[26] = 128113;
    int[] tmp189_182 = tmp182_175;
    tmp189_182[27] = 128114;
    int[] tmp196_189 = tmp189_182;
    tmp196_189[28] = 128115;
    int[] tmp203_196 = tmp196_189;
    tmp203_196[29] = 128116;
    int[] tmp210_203 = tmp203_196;
    tmp210_203[30] = 128117;
    int[] tmp217_210 = tmp210_203;
    tmp217_210[31] = 128118;
    int[] tmp224_217 = tmp217_210;
    tmp224_217[32] = 128119;
    int[] tmp231_224 = tmp224_217;
    tmp231_224[33] = 128120;
    int[] tmp238_231 = tmp231_224;
    tmp238_231[34] = 128124;
    int[] tmp245_238 = tmp238_231;
    tmp245_238[35] = 128129;
    int[] tmp252_245 = tmp245_238;
    tmp252_245[36] = 128130;
    int[] tmp259_252 = tmp252_245;
    tmp259_252[37] = 128131;
    int[] tmp266_259 = tmp259_252;
    tmp266_259[38] = 128133;
    int[] tmp273_266 = tmp266_259;
    tmp273_266[39] = 128134;
    int[] tmp280_273 = tmp273_266;
    tmp280_273[40] = 128135;
    int[] tmp287_280 = tmp280_273;
    tmp287_280[41] = 128170;
    int[] tmp294_287 = tmp287_280;
    tmp294_287[42] = 128373;
    int[] tmp301_294 = tmp294_287;
    tmp301_294[43] = 128400;
    int[] tmp308_301 = tmp301_294;
    tmp308_301[44] = 128405;
    int[] tmp315_308 = tmp308_301;
    tmp315_308[45] = 128406;
    int[] tmp322_315 = tmp315_308;
    tmp322_315[46] = 128581;
    int[] tmp329_322 = tmp322_315;
    tmp329_322[47] = 128582;
    int[] tmp336_329 = tmp329_322;
    tmp336_329[48] = 128583;
    int[] tmp343_336 = tmp336_329;
    tmp343_336[49] = 128587;
    int[] tmp350_343 = tmp343_336;
    tmp350_343[50] = 128588;
    int[] tmp357_350 = tmp350_343;
    tmp357_350[51] = 128589;
    int[] tmp364_357 = tmp357_350;
    tmp364_357[52] = 128590;
    int[] tmp371_364 = tmp364_357;
    tmp371_364[53] = 128591;
    int[] tmp378_371 = tmp371_364;
    tmp378_371[54] = 128675;
    int[] tmp385_378 = tmp378_371;
    tmp385_378[55] = 128692;
    int[] tmp392_385 = tmp385_378;
    tmp392_385[56] = 128693;
    int[] tmp399_392 = tmp392_385;
    tmp399_392[57] = 128694;
    int[] tmp406_399 = tmp399_392;
    tmp406_399[58] = 128704;
    int[] tmp413_406 = tmp406_399;
    tmp413_406[59] = 129304;
    int[] tmp420_413 = tmp413_406;
    tmp420_413[60] = '☝';
    int[] tmp427_420 = tmp420_413;
    tmp427_420[61] = '⛹';
    int[] tmp434_427 = tmp427_420;
    tmp434_427[62] = '✊';
    int[] tmp441_434 = tmp434_427;
    tmp441_434[63] = '✋';
    int[] tmp448_441 = tmp441_434;
    tmp448_441[64] = '✌';
    int[] tmp455_448 = tmp448_441;
    tmp455_448[65] = '✍';
    tmp455_448;
    int k = 0;
    while (k < arrayOfInt.length)
    {
      int m = 0;
      while (m < byv.a.length)
      {
        bei localBei = new bei();
        localBei.a = arrayOfInt[k];
        localBei.b = byv.a[m];
        paramList.add(localBei);
        m += 1;
      }
      k += 1;
    }
  }
  
  private void a(boolean paramBoolean1, boolean paramBoolean2, String... paramVarArgs)
  {
    int n = 0;
    byte[][] arrayOfByte = new byte[paramVarArgs.length][];
    int k = 0;
    String str;
    int m;
    while (k < paramVarArgs.length)
    {
      str = paramVarArgs[k];
      int i1 = str.length();
      byte[] arrayOfByte1 = new byte[i1 / 2];
      m = 0;
      while (m < i1)
      {
        arrayOfByte1[(m / 2)] = ((byte)(cow.a(str.charAt(m)) << 4 | cow.a(str.charAt(m + 1))));
        m += 2;
      }
      arrayOfByte[k] = arrayOfByte1;
      k += 1;
    }
    if ((App.isKitKat) && (bmn.a().b()))
    {
      paramVarArgs = new String[2];
      paramVarArgs[0] = "android.provider.Telephony.SMS_RECEIVED";
      paramVarArgs[1] = "android.provider.Telephony.SMS_DELIVER";
    }
    for (;;)
    {
      m = paramVarArgs.length;
      k = n;
      while (k < m)
      {
        str = paramVarArgs[k];
        bmn.a().a(new cpr(this.h, bmq.a).a(str).a("pdus", arrayOfByte).a("debug_class0", paramBoolean1).a("enable_duplicate_filtering", paramBoolean2).b);
        k += 1;
      }
      paramVarArgs = new String[1];
      paramVarArgs[0] = "android.provider.Telephony.SMS_RECEIVED";
    }
  }
  
  private static ContentProviderResult[] a(ArrayList<ContentProviderOperation> paramArrayList)
  {
    try
    {
      ContentProviderResult[] arrayOfContentProviderResult = bbe.a().a("com.android.contacts", paramArrayList);
      return arrayOfContentProviderResult;
    }
    catch (Exception localException)
    {
      throw new RuntimeException(localException);
    }
    finally
    {
      paramArrayList.clear();
    }
  }
  
  public static void aa()
  {
    bjt.a().R.a(Boolean.valueOf(true));
  }
  
  public static void ab()
  {
    cow.a(new File(bde.a, "msgOrderedById.txt"), b("select _id, text, ts, direction, failed, convo_id, null, kind, builtin_message_id, locked, null, originator, mms_unique_id, part_content_type, part_mms_state, queue_id, message_center_ts from messages order by convo_id, _id").getBytes());
    cow.a(new File(bde.a, "msgOrderedByTs.txt"), b("select _id, text, ts, direction, failed, convo_id, null, kind, builtin_message_id, locked, null, originator, mms_unique_id, part_content_type, part_mms_state, queue_id, message_center_ts from messages order by convo_id, ts").getBytes());
  }
  
  public static void ae()
  {
    Iterator localIterator;
    String str1;
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(new File(bde.a, "themes-base-extra.xml"));
      localFileOutputStream.write("    <!-- ============================================================================================================\n    Generated by generateThemeCode()\n    ============================================================================================================= -->\n".getBytes());
      localIterator = bvw.c.a().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (bvv)localIterator.next();
        str1 = bvv.b(((bvv)localObject).a);
        String str2 = bvv.b(((bvv)localObject).b);
        localObject = bvv.b(((bvv)localObject).a & 0xFFFFFF | 0x66000000);
        localFileOutputStream.write(("    <style name='Material_Primary_" + str1 + "' parent='AppThemeBaseMaterial'>\n        <item name='android:colorPrimary'>#" + str1 + "</item>\n        <item name='android:textColorPrimary'>#" + str2 + "</item>\n        <item name='android:textColorHighlight'>#" + (String)localObject + "</item>\n    </style>\n").getBytes());
      }
      localIterator = bvw.d.a().iterator();
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
    while (localIterator.hasNext())
    {
      str1 = bvv.b(((bvv)localIterator.next()).a);
      localIOException.write(("    <style name='Material_Accent_" + str1 + "'>\n        <item name='android:colorAccent'>#" + str1 + "</item>\n    </style>\n").getBytes());
    }
  }
  
  public static void ah()
  {
    Paint localPaint = new Paint();
    Bitmap localBitmap = coi.a(500, 100, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    for (float f1 = 0.0F; f1 < 500.0F; f1 += 1.0F)
    {
      localPaint.setColor(cos.a(-16615491, -1739917, f1 / 500.0F));
      localCanvas.drawRect(f1, 0.0F, 2.0F + f1, 100.0F, localPaint);
    }
    cow.a(localBitmap, new File(bde.a, "interpolate.png"));
  }
  
  private static ayb al()
  {
    ayb localAyb = new ayb();
    localAyb.e(new aya("0408975903"));
    localAyb.e(new aya("+61408975900"));
    localAyb.e(new aya("0408975901"));
    localAyb.e(new aya("0408975902"));
    return localAyb;
  }
  
  private static String b(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramString = new azm(ayo.b().a.a().a.rawQuery(paramString, null), null, null);
    try
    {
      if (paramString.moveToNext()) {
        localStringBuilder.append("convo_id=" + paramString.i() + ", _id=" + paramString.b() + ", ts=" + paramString.d()).append("\n");
      }
      return localObject.toString();
    }
    finally
    {
      paramString.close();
    }
  }
  
  public static void b(int paramInt)
  {
    aux.a(new cpr(null).a("voiceRegState", paramInt).b);
  }
  
  private static void b(long paramLong)
  {
    bip.a().a(ayo.b().v(paramLong));
  }
  
  public static void b(boolean paramBoolean)
  {
    try
    {
      ayo.b().a(bde.a, paramBoolean);
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  private static long c(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Groups.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).withValue("title", paramString).withValue("group_visible", Integer.valueOf(1)).withValue("group_is_read_only", Integer.valueOf(0)).build());
    a(localArrayList);
    return d(paramString);
  }
  
  public static void c()
  {
    try
    {
      Object localObject = new azs();
      ((azs)localObject).c = bde.l.getName();
      ((azs)localObject).e = new azd(cqg.b(new FileInputStream(bde.q)));
      ((azs)localObject).d = f(((azs)localObject).c);
      azt localAzt = new azt();
      localAzt.a((azs)localObject);
      long l = System.currentTimeMillis();
      localObject = new azo();
      ((azo)localObject).h = new ayb(new aya("0408975903"));
      ((azo)localObject).j = System.currentTimeMillis();
      ((azo)localObject).g = 1;
      ((azo)localObject).f = 1;
      ((azo)localObject).u = localAzt;
      ((azo)localObject).e = l;
      int k = 0;
      while (k <= 0)
      {
        ayo.b().c.a((azo)localObject);
        k += 1;
      }
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  public static void c(boolean paramBoolean)
  {
    if (paramBoolean) {
      throw new Error("Crash!");
    }
    bnx.a().a(new Exception("Caught Exception. Info: 1"));
  }
  
  private static long d(String paramString)
  {
    Cursor localCursor = bbe.a().a(ContactsContract.Groups.CONTENT_URI, null, "deleted= 0 and group_visible= 1", null, null);
    try
    {
      while (localCursor.moveToNext())
      {
        long l = localCursor.getLong(localCursor.getColumnIndex("_id"));
        boolean bool = localCursor.getString(localCursor.getColumnIndex("title")).equals(paramString);
        if (bool) {
          return l;
        }
      }
      return 0L;
    }
    finally
    {
      localCursor.close();
    }
  }
  
  public static void d()
  {
    a(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, "Phone");
    a(ContactsContract.Contacts.CONTENT_URI, "Contacts");
    a(ContactsContract.RawContacts.CONTENT_URI, "Raw");
    a(ContactsContract.RawContactsEntity.CONTENT_URI, "RawEnt");
    a(ContactsContract.Groups.CONTENT_SUMMARY_URI, "GroupSumm");
    a(ContactsContract.Groups.CONTENT_URI, "Groups");
    a(ContactsContract.Data.CONTENT_URI, "Data");
  }
  
  private static void d(boolean paramBoolean)
  {
    Object localObject = ayo.b().d;
    cqf localCqf = new cqf();
    aya localAya = new aya("0408975903");
    int k = 0;
    int n;
    for (int m = 0; k < 100; m = n)
    {
      ((bca)localObject).a(localAya, bjj.a().b().a, paramBoolean);
      n = m;
      if (localAya.g != null) {
        n = m + 1;
      }
      k += 1;
    }
    if (paramBoolean) {}
    for (localObject = "forced";; localObject = "non-forced")
    {
      auv.a("Txtr:app", "Time per %s refresh contact %s: %f ms (found %d of %d)", new Object[] { localObject, localAya.d, Float.valueOf((float)localCqf.a() / 100.0F), Integer.valueOf(m), Integer.valueOf(100) });
      return;
    }
  }
  
  private static bdc e(String paramString)
  {
    paramString = bbe.a().a(ContactsContract.Data.CONTENT_URI, new String[] { "raw_contact_id", "display_name" }, "data1= ? and mimetype = ?", new String[] { paramString, "vnd.android.cursor.item/phone_v2" }, null);
    try
    {
      if (paramString.moveToNext())
      {
        bdc localBdc = new bdc(paramString.getLong(0), paramString.getString(1));
        return localBdc;
      }
      return null;
    }
    finally
    {
      paramString.close();
    }
  }
  
  public static void e()
  {
    bdf[] arrayOfBdf = d;
    int m = arrayOfBdf.length;
    int k = 0;
    while (k < m)
    {
      bdc localBdc = e(arrayOfBdf[k].b);
      if (localBdc != null)
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(ContentProviderOperation.newDelete(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, localBdc.a)).build());
        a(localArrayList);
      }
      k += 1;
    }
  }
  
  private static String f(String paramString)
  {
    paramString = paramString.toLowerCase(Locale.US);
    if (paramString.endsWith(".gif")) {
      return "image/gif";
    }
    if (paramString.endsWith(".png")) {
      return "image/png";
    }
    if (paramString.endsWith(".jpg")) {
      return "image/jpeg";
    }
    if (paramString.endsWith(".3gpp")) {
      return "video/3gpp";
    }
    if (paramString.endsWith(".mov")) {
      return "video/mp4";
    }
    if (paramString.endsWith(".ogg")) {
      return "application/ogg";
    }
    if (paramString.endsWith(".vcf")) {
      return "text/x-vCard";
    }
    throw new RuntimeException("Can't handle format: " + paramString);
  }
  
  public static void g()
  {
    bdc localBdc = e("0408975903");
    if (localBdc != null)
    {
      ArrayList localArrayList = new ArrayList();
      int k = localBdc.b.length();
      String str = k.substring(k.length() - 1);
      localArrayList.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection("raw_contact_id= ? and mimetype= ?", new String[] { localBdc.a, "vnd.android.cursor.item/name" }).withValue("data1", localBdc.b + str).build());
      a(localArrayList);
    }
  }
  
  private void g(final String paramString)
  {
    App.getAppHandler().postDelayed(new Runnable()
    {
      public final void run()
      {
        bdd.a(bdd.this, false, new String[] { paramString });
      }
    }, 3000L);
  }
  
  public static void i()
  {
    axs.a().b();
  }
  
  public static void v()
  {
    try
    {
      cqg.a(new FileInputStream(bek.a().c()), new FileOutputStream(new File(bde.a, "msg.log")));
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  public static void x()
  {
    try
    {
      ayo.b().a(new File(bde.a, "db-contact-thumbs"));
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  public static void z() {}
  
  public final void B()
  {
    final Random localRandom = new Random();
    final byw[] arrayOfByw = bdn.a().g();
    final bei localBei = new bei();
    ayo.b().a(false, 1, 1000, new brp() {});
  }
  
  public final void C()
  {
    final cpp localCpp = new cpp();
    ayo.b().a(false, 1, 1000, new brp() {});
  }
  
  public final void D()
  {
    int k = 0;
    if (k < 100)
    {
      long l = System.currentTimeMillis() + k;
      String str1 = "Zomeone " + l;
      String str2 = l;
      byte[] arrayOfByte = e;
      if (k == 0) {}
      for (boolean bool = true;; bool = false)
      {
        a(str1, str2, arrayOfByte, 0L, bool);
        k += 1;
        break;
      }
    }
  }
  
  public final void E()
  {
    if (this.i == null)
    {
      this.i = new Thread(new Runnable()
      {
        public final void run()
        {
          while (bdd.b(bdd.a()) == Thread.currentThread())
          {
            bdd.a(bdd.this, "0408975903");
            try
            {
              Thread.sleep(500L);
            }
            catch (InterruptedException localInterruptedException) {}
          }
          bdd localBdd = bdd.this;
        }
      });
      this.i.start();
      brl.a(this.h, "Started consistency check", 0, brl.a).a();
      return;
    }
    this.i = null;
    brl.a(this.h, "Stopped consistency check", 0, brl.a).a();
  }
  
  public final void F()
  {
    long l = 0L;
    int k = 0;
    while (k < 5)
    {
      App.getAppHandler().postDelayed(new Runnable()
      {
        public final void run()
        {
          bmx.a().a(true);
        }
      }, l);
      l += 150L;
      k += 1;
    }
  }
  
  public final void I()
  {
    int[] arrayOfInt = new int[5];
    int[] tmp7_5 = arrayOfInt;
    tmp7_5[0] = 14;
    int[] tmp12_7 = tmp7_5;
    tmp12_7[1] = 16;
    int[] tmp17_12 = tmp12_7;
    tmp17_12[2] = 18;
    int[] tmp22_17 = tmp17_12;
    tmp22_17[3] = 20;
    int[] tmp27_22 = tmp22_17;
    tmp27_22[4] = 24;
    tmp27_22;
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "fonts/Roboto-Light.ttf";
    arrayOfString[1] = "fonts/Roboto-Regular.ttf";
    int n = arrayOfString.length;
    int k = 0;
    while (k < n)
    {
      Object localObject1 = arrayOfString[k];
      Object localObject2 = czx.a(this.h.getAssets(), (String)localObject1);
      if (localObject2 != null)
      {
        localObject1 = new Paint();
        ((Paint)localObject1).setTypeface((Typeface)localObject2);
        int i1 = arrayOfInt.length;
        int m = 0;
        while (m < i1)
        {
          int i2 = arrayOfInt[m];
          ((Paint)localObject1).setTextSize(cpl.a(i2));
          localObject2 = ((Paint)localObject1).getFontMetricsInt();
          i2 = ((Paint.FontMetricsInt)localObject2).bottom;
          i2 = ((Paint.FontMetricsInt)localObject2).top;
          m += 1;
        }
      }
      k += 1;
    }
  }
  
  public final void K()
  {
    cqf localCqf = new cqf();
    new bdy(ak());
    bei localBei = new bei();
    beo localBeo = bdn.b();
    int n = 0;
    int m = 0;
    int k = 0;
    while (n < 10)
    {
      int i1 = 0;
      m = 0;
      k = 0;
      if (i1 < bdy.a())
      {
        bdy.a(i1, localBei);
        if (localBeo.b(localBei)) {
          k += 1;
        }
        for (;;)
        {
          i1 += 1;
          break;
          m += 1;
        }
      }
      n += 1;
    }
    auv.a("Txtr:app", "Time for checking support of all emojis: %f ms (supported %d, unsupported %d)", new Object[] { Float.valueOf((float)localCqf.a() / 10.0F), Integer.valueOf(k), Integer.valueOf(m) });
  }
  
  public final void L()
  {
    cqf localCqf = new cqf();
    Bitmap localBitmap = coi.a(new File(bde.b, "imagefile").getAbsolutePath(), new coj());
    auv.a("Txtr:app", "%s: dimensions %dx%d", new Object[] { this, Integer.valueOf(localBitmap.getWidth()), Integer.valueOf(localBitmap.getHeight()) });
    auv.a("Txtr:app", "%s: took %dms", new Object[] { this, Long.valueOf(localCqf.a()) });
  }
  
  public final void Q()
  {
    ((ClipboardManager)this.h.getSystemService("clipboard")).setText(new String(new byte[] { -18, -112, -118, -18, -112, -124, -18, -124, -123, -18, -112, -119 }));
  }
  
  public final void R()
  {
    ((ClipboardManager)this.h.getSystemService("clipboard")).setText("bla" + cqh.a(11088) + cqh.a(65039) + cqh.a(9925) + cqh.a(65024) + cqh.a(126980) + cqh.a(65025));
  }
  
  public final void S()
  {
    ((ClipboardManager)this.h.getSystemService("clipboard")).setText("xx" + cqh.a(128118) + cqh.a(127995) + cqh.a(128102) + cqh.a(127996) + cqh.a(128103) + cqh.a(127997) + cqh.a(128105) + cqh.a(127998) + cqh.a(128104) + cqh.a(127999) + "1" + cqh.a(8419));
  }
  
  public final void T()
  {
    ((ClipboardManager)this.h.getSystemService("clipboard")).setText(new String(bde.r));
  }
  
  public final void U()
  {
    this.h.startActivity(new Intent("android.intent.action.SENDTO", Uri.parse("smsto:0408975903")).putExtra("sms_body", "Sent from external app").addFlags(268435456));
  }
  
  public final void W()
  {
    App.getAppHandler().postDelayed(new Runnable()
    {
      public final void run()
      {
        cow.a();
      }
    }, 5000L);
  }
  
  public final void X()
  {
    final azz localAzz = ayo.b().a.a();
    localAzz.a("drop table if exists test");
    localAzz.a("create table test ( _id integer primary key autoincrement, t blob not null)");
    Object localObject1 = new byte[100000];
    final ArrayList localArrayList = new ArrayList();
    int k = 0;
    while (k < 100)
    {
      localObject2 = new ContentValues();
      ((ContentValues)localObject2).put("t", (byte[])localObject1);
      localArrayList.add(Long.valueOf(localAzz.a("test", (ContentValues)localObject2, 2)));
      k += 1;
    }
    localObject1 = new cpp();
    Object localObject2 = new cpp();
    new Thread(new Runnable()
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 25	com/mplus/lib/bdd$7:a	Ljava/util/List;
        //   4: invokeinterface 41 1 0
        //   9: astore_1
        //   10: aload_1
        //   11: invokeinterface 47 1 0
        //   16: ifeq +122 -> 138
        //   19: aload_1
        //   20: invokeinterface 51 1 0
        //   25: checkcast 53	java/lang/Long
        //   28: astore_3
        //   29: aload_0
        //   30: getfield 27	com/mplus/lib/bdd$7:b	Lcom/mplus/lib/cpp;
        //   33: astore_2
        //   34: aload_2
        //   35: monitorenter
        //   36: aload_0
        //   37: getfield 29	com/mplus/lib/bdd$7:c	Lcom/mplus/lib/azz;
        //   40: getfield 58	com/mplus/lib/azz:a	Landroid/database/sqlite/SQLiteDatabase;
        //   43: invokevirtual 63	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
        //   46: aload_0
        //   47: getfield 29	com/mplus/lib/bdd$7:c	Lcom/mplus/lib/azz;
        //   50: ldc 65
        //   52: ldc 67
        //   54: iconst_1
        //   55: anewarray 69	java/lang/String
        //   58: dup
        //   59: iconst_0
        //   60: new 71	java/lang/StringBuilder
        //   63: dup
        //   64: invokespecial 72	java/lang/StringBuilder:<init>	()V
        //   67: aload_3
        //   68: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   71: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   74: aastore
        //   75: invokevirtual 83	com/mplus/lib/azz:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
        //   78: pop
        //   79: ldc2_w 84
        //   82: invokestatic 91	java/lang/Thread:sleep	(J)V
        //   85: aload_0
        //   86: getfield 29	com/mplus/lib/bdd$7:c	Lcom/mplus/lib/azz;
        //   89: getfield 58	com/mplus/lib/azz:a	Landroid/database/sqlite/SQLiteDatabase;
        //   92: invokevirtual 94	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
        //   95: aload_2
        //   96: monitorexit
        //   97: aload_0
        //   98: getfield 29	com/mplus/lib/bdd$7:c	Lcom/mplus/lib/azz;
        //   101: getfield 58	com/mplus/lib/azz:a	Landroid/database/sqlite/SQLiteDatabase;
        //   104: invokevirtual 97	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   107: ldc2_w 98
        //   110: invokestatic 91	java/lang/Thread:sleep	(J)V
        //   113: goto -103 -> 10
        //   116: astore_2
        //   117: goto -107 -> 10
        //   120: astore_1
        //   121: aload_2
        //   122: monitorexit
        //   123: aload_1
        //   124: athrow
        //   125: astore_1
        //   126: aload_0
        //   127: getfield 29	com/mplus/lib/bdd$7:c	Lcom/mplus/lib/azz;
        //   130: getfield 58	com/mplus/lib/azz:a	Landroid/database/sqlite/SQLiteDatabase;
        //   133: invokevirtual 97	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   136: aload_1
        //   137: athrow
        //   138: aload_0
        //   139: getfield 23	com/mplus/lib/bdd$7:d	Lcom/mplus/lib/bdd;
        //   142: astore_1
        //   143: aload_1
        //   144: monitorenter
        //   145: aload_0
        //   146: getfield 27	com/mplus/lib/bdd$7:b	Lcom/mplus/lib/cpp;
        //   149: iconst_1
        //   150: putfield 104	com/mplus/lib/cpp:a	I
        //   153: aload_1
        //   154: monitorexit
        //   155: return
        //   156: astore_2
        //   157: aload_1
        //   158: monitorexit
        //   159: aload_2
        //   160: athrow
        //   161: astore_3
        //   162: goto -77 -> 85
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	165	0	this	7
        //   9	11	1	localIterator	Iterator
        //   120	4	1	localObject1	Object
        //   125	12	1	localObject2	Object
        //   116	6	2	localInterruptedException1	InterruptedException
        //   156	4	2	localObject3	Object
        //   28	40	3	localLong	Long
        //   161	1	3	localInterruptedException2	InterruptedException
        // Exception table:
        //   from	to	target	type
        //   107	113	116	java/lang/InterruptedException
        //   36	79	120	finally
        //   79	85	120	finally
        //   85	97	120	finally
        //   29	36	125	finally
        //   121	125	125	finally
        //   145	155	156	finally
        //   79	85	161	java/lang/InterruptedException
      }
    }).start();
    new Thread(new Runnable()
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 24	com/mplus/lib/bdd$8:a	Lcom/mplus/lib/cpp;
        //   4: astore_1
        //   5: aload_1
        //   6: monitorenter
        //   7: aload_0
        //   8: getfield 26	com/mplus/lib/bdd$8:b	Lcom/mplus/lib/azz;
        //   11: ldc 36
        //   13: aconst_null
        //   14: invokevirtual 41	com/mplus/lib/azz:a	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //   17: astore_2
        //   18: ldc2_w 42
        //   21: invokestatic 49	java/lang/Thread:sleep	(J)V
        //   24: aload_2
        //   25: invokeinterface 55 1 0
        //   30: ifeq +32 -> 62
        //   33: aload_2
        //   34: iconst_0
        //   35: invokeinterface 59 2 0
        //   40: pop2
        //   41: goto -17 -> 24
        //   44: astore_3
        //   45: aload_2
        //   46: ifnull +9 -> 55
        //   49: aload_2
        //   50: invokeinterface 62 1 0
        //   55: aload_3
        //   56: athrow
        //   57: astore_2
        //   58: aload_1
        //   59: monitorexit
        //   60: aload_2
        //   61: athrow
        //   62: aload_2
        //   63: ifnull +9 -> 72
        //   66: aload_2
        //   67: invokeinterface 62 1 0
        //   72: aload_1
        //   73: monitorexit
        //   74: aload_0
        //   75: getfield 22	com/mplus/lib/bdd$8:d	Lcom/mplus/lib/bdd;
        //   78: astore_1
        //   79: aload_1
        //   80: monitorenter
        //   81: aload_0
        //   82: getfield 28	com/mplus/lib/bdd$8:c	Lcom/mplus/lib/cpp;
        //   85: getfield 67	com/mplus/lib/cpp:a	I
        //   88: iconst_1
        //   89: if_icmpne +6 -> 95
        //   92: aload_1
        //   93: monitorexit
        //   94: return
        //   95: aload_1
        //   96: monitorexit
        //   97: ldc2_w 42
        //   100: invokestatic 49	java/lang/Thread:sleep	(J)V
        //   103: goto -103 -> 0
        //   106: astore_1
        //   107: goto -107 -> 0
        //   110: astore_2
        //   111: aload_1
        //   112: monitorexit
        //   113: aload_2
        //   114: athrow
        //   115: astore_3
        //   116: goto -92 -> 24
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	119	0	this	8
        //   106	6	1	localInterruptedException1	InterruptedException
        //   17	33	2	localCursor	Cursor
        //   57	10	2	localObject2	Object
        //   110	4	2	localObject3	Object
        //   44	12	3	localObject4	Object
        //   115	1	3	localInterruptedException2	InterruptedException
        // Exception table:
        //   from	to	target	type
        //   24	41	44	finally
        //   7	18	57	finally
        //   18	24	57	finally
        //   49	55	57	finally
        //   55	57	57	finally
        //   66	72	57	finally
        //   72	74	57	finally
        //   97	103	106	java/lang/InterruptedException
        //   81	94	110	finally
        //   18	24	115	java/lang/InterruptedException
      }
    }).start();
  }
  
  public final void Z()
  {
    int k = 0;
    while (k < 20)
    {
      bxk localBxk = new bxk(this.h, Uri.fromFile(bde.q), null);
      localBxk.a(new cpk(100, 100));
      localBxk.a(1048576);
      k += 1;
    }
  }
  
  public final long a(ayb paramAyb, aya paramAya, String paramString, long paramLong)
  {
    return a(paramAyb, paramAya, 0, new azd(paramString.getBytes()), "text/plain", true, paramLong);
  }
  
  public final void a(File paramFile)
  {
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramFile);
      a(localFileInputStream, 0, paramFile.getName(), false, false);
      localFileInputStream.close();
      localFileInputStream = new FileInputStream(paramFile);
      a(localFileInputStream, 1, paramFile.getName(), false, false);
      localFileInputStream.close();
      localFileInputStream = new FileInputStream(paramFile);
      a(localFileInputStream, 1, paramFile.getName(), true, false);
      localFileInputStream.close();
      localFileInputStream = new FileInputStream(paramFile);
      a(localFileInputStream, 1, paramFile.getName(), false, true);
      localFileInputStream.close();
      return;
    }
    catch (IOException paramFile)
    {
      throw new RuntimeException(paramFile);
    }
  }
  
  @TargetApi(19)
  public final void a(boolean paramBoolean)
  {
    bbe.a().a(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, null);
    bbe.a().a(Telephony.Sms.CONTENT_URI, null);
    bbe.a().a(Telephony.Mms.CONTENT_URI, null);
    bbe.a().b(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, null);
    ayo.b().a(false);
    bjt.a().b();
    App.getAppHandler().post(new Runnable()
    {
      public final void run()
      {
        bvs.a().b();
      }
    });
    if (paramBoolean)
    {
      bnj.a().g();
      return;
    }
    bjt.a().s.a(Boolean.valueOf(true));
    bjt.a().t.a(Integer.valueOf(0));
  }
  
  public final void a(final boolean paramBoolean, int paramInt)
  {
    int k = 0;
    while (k < paramInt)
    {
      App.getApp().post(new Runnable()
      {
        public final void run()
        {
          bdd localBdd = bdd.this;
          boolean bool = paramBoolean;
          if (System.currentTimeMillis() % 2L == 0L) {}
          for (String str = "00200A81408079953000002111224150814415D4F29C0E9A36A721C5B43C7EBBC92076DA5D06";; str = "07911614910981F1440B911604985709F3000031403011645544A00500030602016031D98C56B3DD7039584C36A3D56C375C0E1693CD6835DB0D9783C564335ACD76C3E56031D98C56B3DD7039584C36A3D56C375C0E1693CD6835DB0D9783C564335ACD76C3E56031D98C56B3DD7039584C36A3D56C375C0E1693CD6835DB0D9783C564335ACD76C3E56031D98C56B3DD7039584C36A3D56C375C0E1693CD6835DB0D9783C564")
          {
            bdd.a(localBdd, bool, new String[] { str });
            return;
          }
        }
      }, k * 1000L);
      k += 1;
    }
  }
  
  public final void ac()
  {
    synchronized (new Object())
    {
      ArrayList localArrayList = new ArrayList();
      if (localArrayList.size() <= 10000)
      {
        localArrayList.size();
        Thread localThread = new Thread(new Runnable()
        {
          private Object c;
          private Object d;
          
          public final void run()
          {
            try
            {
              bmn.a().b();
              this.c = bbe.a().a(bbq.a, new String[] { "_id" }, "type in (1,4,2,6,5)", null, "_id desc limit 10");
              this.d = bdd.c(bdd.this).getPackageManager().getInstalledApplications(128);
              try
              {
                synchronized (localObject1)
                {
                  localObject1.wait();
                  return;
                }
              }
              catch (InterruptedException localInterruptedException)
              {
                for (;;) {}
              }
            }
            catch (Exception localException)
            {
              for (;;) {}
            }
          }
        });
        localThread.start();
        localArrayList.add(localThread);
      }
    }
  }
  
  public final void ad()
  {
    Resources localResources = this.h.getResources();
    DisplayMetrics localDisplayMetrics = localResources.getDisplayMetrics();
    float f1 = localDisplayMetrics.density;
    try
    {
      localDisplayMetrics.density = 2.0F;
      File localFile1 = new File(bde.a, "drawable-xxhdpi");
      File[] arrayOfFile = localFile1.listFiles();
      int i1 = arrayOfFile.length;
      int k = 0;
      while (k < i1)
      {
        File localFile2 = arrayOfFile[k];
        if ((localFile2.getName().endsWith("png")) && (localFile2.getName().contains("uncolored")))
        {
          BitmapDrawable localBitmapDrawable = new BitmapDrawable(localResources, coi.a(cpe.c(localFile2)));
          Bitmap localBitmap = coi.a(localBitmapDrawable.getIntrinsicWidth(), localBitmapDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
          int[] arrayOfInt = new int[60];
          int[] tmp142_140 = arrayOfInt;
          tmp142_140[0] = -16777216;
          int[] tmp148_142 = tmp142_140;
          tmp148_142[1] = -16742327;
          int[] tmp154_148 = tmp148_142;
          tmp154_148[2] = -16739862;
          int[] tmp160_154 = tmp154_148;
          tmp160_154[3] = -16738393;
          int[] tmp166_160 = tmp160_154;
          tmp166_160[4] = -16737844;
          int[] tmp172_166 = tmp166_160;
          tmp172_166[5] = -16611119;
          int[] tmp178_172 = tmp172_166;
          tmp178_172[6] = -15750300;
          int[] tmp185_178 = tmp178_172;
          tmp185_178[7] = -15444807;
          int[] tmp192_185 = tmp185_178;
          tmp192_185[8] = -14244198;
          int[] tmp199_192 = tmp192_185;
          tmp199_192[9] = -14235942;
          int[] tmp206_199 = tmp199_192;
          tmp206_199[10] = -14043402;
          int[] tmp213_206 = tmp206_199;
          tmp213_206[11] = -13421773;
          int[] tmp220_213 = tmp213_206;
          tmp220_213[12] = -13388314;
          int[] tmp227_220 = tmp220_213;
          tmp227_220[13] = -12627531;
          int[] tmp234_227 = tmp227_220;
          tmp234_227[14] = -12417548;
          int[] tmp241_234 = tmp234_227;
          tmp241_234[15] = -12409355;
          int[] tmp248_241 = tmp241_234;
          tmp248_241[16] = -11491560;
          int[] tmp255_248 = tmp248_241;
          tmp255_248[17] = -10720320;
          int[] tmp262_255 = tmp255_248;
          tmp262_255[18] = -10242926;
          int[] tmp269_262 = tmp262_255;
          tmp269_262[19] = -10132020;
          int[] tmp276_269 = tmp269_262;
          tmp276_269[20] = -10053376;
          int[] tmp283_276 = tmp276_269;
          tmp283_276[21] = -10044566;
          int[] tmp290_283 = tmp283_276;
          tmp290_283[22] = -9587783;
          int[] tmp297_290 = tmp290_283;
          tmp297_290[23] = -9529159;
          int[] tmp304_297 = tmp297_290;
          tmp304_297[24] = -9025355;
          int[] tmp311_304 = tmp304_297;
          tmp311_304[25] = -8875876;
          int[] tmp318_311 = tmp311_304;
          tmp318_311[26] = -8497214;
          int[] tmp325_318 = tmp318_311;
          tmp325_318[27] = -8231988;
          int[] tmp332_325 = tmp325_318;
          tmp332_325[28] = -7949765;
          int[] tmp339_332 = tmp332_325;
          tmp339_332[29] = -7713223;
          int[] tmp346_339 = tmp339_332;
          tmp346_339[30] = -7508381;
          int[] tmp353_346 = tmp346_339;
          tmp353_346[31] = -6736947;
          int[] tmp360_353 = tmp353_346;
          tmp360_353[32] = -6697984;
          int[] tmp367_360 = tmp360_353;
          tmp367_360[33] = -6501275;
          int[] tmp374_367 = tmp367_360;
          tmp374_367[34] = -6406216;
          int[] tmp381_374 = tmp374_367;
          tmp381_374[35] = -6122345;
          int[] tmp388_381 = tmp381_374;
          tmp388_381[36] = -5997438;
          int[] tmp395_388 = tmp388_381;
          tmp395_388[37] = -5817034;
          int[] tmp402_395 = tmp395_388;
          tmp402_395[38] = -5609780;
          int[] tmp409_402 = tmp402_395;
          tmp409_402[39] = -5552196;
          int[] tmp416_409 = tmp409_402;
          tmp416_409[40] = -4342339;
          int[] tmp423_416 = tmp416_409;
          tmp423_416[41] = -3842690;
          int[] tmp430_423 = tmp423_416;
          tmp430_423[42] = -3730176;
          int[] tmp437_430 = tmp430_423;
          tmp437_430[43] = -3662334;
          int[] tmp444_437 = tmp437_430;
          tmp444_437[44] = -3629780;
          int[] tmp451_444 = tmp444_437;
          tmp451_444[45] = -3557556;
          int[] tmp458_451 = tmp451_444;
          tmp458_451[46] = -3390976;
          int[] tmp465_458 = tmp458_451;
          tmp465_458[47] = -3377398;
          int[] tmp472_465 = tmp465_458;
          tmp472_465[48] = -3342336;
          int[] tmp479_472 = tmp472_465;
          tmp479_472[49] = -1499549;
          int[] tmp486_479 = tmp479_472;
          tmp486_479[50] = -1294214;
          int[] tmp493_486 = tmp486_479;
          tmp493_486[51] = -1092784;
          int[] tmp500_493 = tmp493_486;
          tmp500_493[52] = -1086464;
          int[] tmp507_500 = tmp500_493;
          tmp507_500[53] = -48059;
          int[] tmp514_507 = tmp507_500;
          tmp514_507[54] = -36797;
          int[] tmp521_514 = tmp514_507;
          tmp521_514[55] = '言';
          int[] tmp528_521 = tmp521_514;
          tmp528_521[56] = 'Ꜧ';
          int[] tmp535_528 = tmp528_521;
          tmp535_528[57] = '뤳';
          int[] tmp542_535 = tmp535_528;
          tmp542_535[58] = '쨨';
          int[] tmp549_542 = tmp542_535;
          tmp549_542[59] = -1;
          tmp549_542;
          int i2 = arrayOfInt.length;
          int m = 0;
          if (m < i2)
          {
            Integer localInteger = Integer.valueOf(arrayOfInt[m]);
            localBitmap.eraseColor(0);
            Canvas localCanvas = new Canvas(localBitmap);
            if (localInteger.intValue() == -16777216) {}
            for (int n = -16777195;; n = localInteger.intValue())
            {
              localBitmapDrawable.setBounds(0, 0, localBitmap.getWidth(), localBitmap.getHeight());
              bvs.a();
              localBitmapDrawable.setColorFilter(bvs.a(n));
              localBitmapDrawable.draw(localCanvas);
              cow.a(new File(localFile1, localFile2.getName().replace("uncolored", bvv.b(localInteger.intValue()))), cpl.b(localBitmap));
              m += 1;
              break;
            }
          }
        }
        k += 1;
      }
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
    finally
    {
      localDisplayMetrics.density = f1;
    }
  }
  
  public final void af()
  {
    Object localObject1;
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(new File(bde.a, "activity-aliases.xml"));
      a(localFileOutputStream, true, -15108398, "com.mplus.lib.ui.main.Main");
      bvn.a();
      localObject1 = bvn.b().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (bvv)((Iterator)localObject1).next();
        int k = ((bvv)localObject3).a;
        bvn.a();
        a(localFileOutputStream, false, k, bvn.a(((bvv)localObject3).a));
      }
      localIOException1.close();
    }
    catch (IOException localIOException1)
    {
      throw new RuntimeException(localIOException1);
    }
    Object localObject3 = this.h.getResources();
    DisplayMetrics localDisplayMetrics = ((Resources)localObject3).getDisplayMetrics();
    float f1 = localDisplayMetrics.density;
    try
    {
      localDisplayMetrics.density = 2.0F;
      localObject1 = new BitmapDrawable((Resources)localObject3, coi.a(cpe.c(new File(bde.a, "drawable-xxhdpi/icon_template_bubble.png"))));
      localObject3 = new BitmapDrawable((Resources)localObject3, coi.a(cpe.c(new File(bde.a, "drawable-xxhdpi/icon_template_shadow.png"))));
      Bitmap localBitmap = coi.a(((BitmapDrawable)localObject1).getIntrinsicWidth(), ((BitmapDrawable)localObject1).getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
      bvn.a();
      Iterator localIterator = bvn.b().iterator();
      while (localIterator.hasNext())
      {
        bvv localBvv = (bvv)localIterator.next();
        localBitmap.eraseColor(0);
        Object localObject4 = new Canvas(localBitmap);
        ((BitmapDrawable)localObject3).setBounds(0, 0, localBitmap.getWidth(), localBitmap.getHeight());
        ((BitmapDrawable)localObject3).draw((Canvas)localObject4);
        ((BitmapDrawable)localObject1).setBounds(0, 0, localBitmap.getWidth(), localBitmap.getHeight());
        ((BitmapDrawable)localObject1).setColorFilter(new PorterDuffColorFilter(localBvv.a, PorterDuff.Mode.MULTIPLY));
        ((BitmapDrawable)localObject1).draw((Canvas)localObject4);
        localObject4 = bde.a;
        StringBuilder localStringBuilder = new StringBuilder("drawable-xxhdpi/");
        bvn.a();
        cow.a(new File((File)localObject4, bvn.d(localBvv.a) + ".png"), cpl.b(localBitmap));
      }
    }
    catch (IOException localIOException2)
    {
      throw new RuntimeException(localIOException2);
    }
    finally
    {
      localDisplayMetrics.density = f1;
    }
  }
  
  public final void ag()
  {
    TextView localTextView = new TextView(this.h, null);
    localTextView.setText("💛");
    localTextView.setTextColor(-1);
    localTextView.setTextSize(cpl.a(16));
    cow.a(ViewUtil.n(localTextView), new File(bde.a, "heart.png"));
  }
  
  public final void ai()
  {
    bjt.a().t.a(Integer.valueOf(20));
    App.getAppHandler().postDelayed(new Runnable()
    {
      public final void run()
      {
        bjt.a().t.a(Integer.valueOf(0));
      }
    }, 10000L);
    ayk localAyk = ayo.b().c();
    while (localAyk.moveToNext())
    {
      final long l = localAyk.b();
      ayo.b().d(l, 10);
      App.getAppHandler().postDelayed(new Runnable()
      {
        public final void run()
        {
          ayo.b().d(l, 0);
        }
      }, 10000L);
    }
    localAyk.close();
  }
  
  public final void b()
  {
    a(false);
    Object localObject = new azo();
    ((azo)localObject).h = new ayb(new aya("0408975903"));
    ((azo)localObject).f = 0;
    ((azo)localObject).g = 0;
    ((azo)localObject).m = false;
    ((azo)localObject).i = "Hi!";
    ((azo)localObject).j = System.currentTimeMillis();
    ((azo)localObject).p = true;
    ayo.b().b((azo)localObject);
    localObject = al();
    azo localAzo = new azo();
    localAzo.h = ((ayb)localObject);
    localAzo.f = 0;
    localAzo.g = 0;
    localAzo.m = true;
    localAzo.j = System.currentTimeMillis();
    localAzo.y = "0408975900";
    localAzo.i = "Short again";
    localAzo.p = true;
    ayo.b().b(localAzo);
    localAzo = new azo();
    localAzo.h = ((ayb)localObject);
    localAzo.f = 0;
    localAzo.g = 0;
    localAzo.m = true;
    localAzo.j = System.currentTimeMillis();
    localAzo.i = "Short again no originator";
    localAzo.p = true;
    ayo.b().b(localAzo);
    localAzo = new azo();
    localAzo.h = ((ayb)localObject);
    localAzo.f = 0;
    localAzo.g = 1;
    localAzo.m = true;
    localAzo.j = System.currentTimeMillis();
    localAzo.i = "Short!";
    localAzo.p = true;
    ayo.b().b(localAzo);
    localAzo = new azo();
    localAzo.h = ((ayb)localObject);
    localAzo.f = 0;
    localAzo.g = 0;
    localAzo.y = "0408975903";
    localAzo.i = "loong long  long  long  long  long  long  long  long  long  long  long  long  long  long  long  long  long locked message";
    localAzo.m = true;
    localAzo.j = System.currentTimeMillis();
    localAzo.p = true;
    ayo.b().b(localAzo);
    localAzo = new azo();
    localAzo.h = ((ayb)localObject);
    localAzo.f = 0;
    localAzo.g = 1;
    localAzo.m = true;
    localAzo.j = System.currentTimeMillis();
    localAzo.i = "lksjdflkjsadfjskldj a@a.com ljklfj 0285690400 lkjsdf www.mplus.com kljd lkfj sdkljf kljsd lfjkl sdj kljflksjdflkjsadfjskldj ljklfj lkjsdf kljd lkfj sdkljf kljsd lfjkl sdj kljflksjdflkjsadfjskldj ljklfj lkjsdf kljd lkfj sdkljf kljsd lfjkl sdj kljflksjdflkjsadfjskldj ljklfj lkjsdf kljd lkfj sdkljf kljsd lfjkl sdj kljf";
    localAzo.p = false;
    ayo.b().b(localAzo);
    localObject = new ArrayList();
    ((ArrayList)localObject).add(ContentProviderOperation.newDelete(ContactsContract.Groups.CONTENT_URI).build());
    a((ArrayList)localObject);
    localObject = new ArrayList();
    ((ArrayList)localObject).add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI).build());
    a((ArrayList)localObject);
    f();
    a("Jack", "12345", e, 0L, false);
    a("Jill2", "556782", e, 0L, false);
    a("Jill3", "556783", e, 0L, false);
    long l = c("Test1");
    a("Jill4", "556784", e, l, false);
    a("Jill5", "556785", e, 0L, false);
    a("Jill6", "556786", e, 0L, false);
    a("Jill7", "556787", e, 0L, false);
    a("Jill8", "556788", e, 0L, false);
    a("Jill9", "556789", e, 0L, false);
    a("Jill0", "556780", e, 0L, false);
    l = c("Test2");
    a("Jill10", "55678123", e, l, false);
    a("Jill11", "55678124", e, l, false);
    a("Jill12", "55678125", e, l, false);
    a("Jill13", "55678126", e, l, true);
    a("Jill14", "55678127", e, l, false);
    a("Jill15", "55678128", e, l, false);
    a("Jill16", "55678129", e, l, false);
    a("Jill17", "556781212", e, l, false);
    a("Jill18", "556781211", e, l, false);
    a("Jill19", "556781213", e, l, false);
    a("Andrew", "9999", e, 0L, true);
    f();
  }
  
  public final void f()
  {
    int k = 0;
    bdf[] arrayOfBdf = d;
    int m = arrayOfBdf.length;
    while (k < m)
    {
      Object localObject = arrayOfBdf[k];
      try
      {
        String str1 = ((bdf)localObject).a;
        String str2 = ((bdf)localObject).b;
        if (((bdf)localObject).c == null) {}
        for (localObject = e;; localObject = cpe.c(((bdf)localObject).c))
        {
          a(str1, str2, (byte[])localObject, 0L, false);
          k += 1;
          break;
        }
        return;
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException(localIOException);
      }
    }
  }
  
  /* Error */
  public final void h()
  {
    // Byte code:
    //   0: new 1973	java/util/zip/ZipInputStream
    //   3: dup
    //   4: new 1135	java/io/FileInputStream
    //   7: dup
    //   8: getstatic 1975	com/mplus/lib/bde:j	Ljava/io/File;
    //   11: invokespecial 1139	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   14: invokespecial 1978	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore 6
    //   19: iconst_0
    //   20: istore_1
    //   21: aload 6
    //   23: astore 5
    //   25: aload 6
    //   27: invokevirtual 1982	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   30: astore 7
    //   32: aload 7
    //   34: ifnull +96 -> 130
    //   37: aload 6
    //   39: astore 5
    //   41: ldc_w 290
    //   44: ldc_w 1984
    //   47: iconst_1
    //   48: anewarray 294	java/lang/Object
    //   51: dup
    //   52: iconst_0
    //   53: aload 7
    //   55: invokevirtual 1987	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   58: aastore
    //   59: invokestatic 318	com/mplus/lib/auv:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   62: iload_1
    //   63: iconst_3
    //   64: irem
    //   65: ifne +49 -> 114
    //   68: iconst_0
    //   69: istore_2
    //   70: aload 6
    //   72: astore 5
    //   74: aload 7
    //   76: invokevirtual 1987	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   79: astore 7
    //   81: iload_1
    //   82: iconst_5
    //   83: irem
    //   84: ifne +35 -> 119
    //   87: iconst_1
    //   88: istore_3
    //   89: goto +105 -> 194
    //   92: aload 6
    //   94: astore 5
    //   96: aload 6
    //   98: iload_2
    //   99: aload 7
    //   101: iload_3
    //   102: iload 4
    //   104: invokestatic 1604	com/mplus/lib/bdd:a	(Ljava/io/InputStream;ILjava/lang/String;ZZ)V
    //   107: iload_1
    //   108: iconst_1
    //   109: iadd
    //   110: istore_1
    //   111: goto -90 -> 21
    //   114: iconst_1
    //   115: istore_2
    //   116: goto -46 -> 70
    //   119: iconst_0
    //   120: istore_3
    //   121: goto +73 -> 194
    //   124: iconst_0
    //   125: istore 4
    //   127: goto -35 -> 92
    //   130: aload 6
    //   132: invokestatic 1991	com/mplus/lib/cyt:a	(Ljava/io/InputStream;)V
    //   135: return
    //   136: astore 7
    //   138: aconst_null
    //   139: astore 5
    //   141: new 904	java/lang/RuntimeException
    //   144: dup
    //   145: aload 7
    //   147: invokespecial 907	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   150: athrow
    //   151: astore 7
    //   153: aload 5
    //   155: astore 6
    //   157: aload 7
    //   159: astore 5
    //   161: aload 6
    //   163: invokestatic 1991	com/mplus/lib/cyt:a	(Ljava/io/InputStream;)V
    //   166: aload 5
    //   168: athrow
    //   169: astore 5
    //   171: return
    //   172: astore 6
    //   174: goto -8 -> 166
    //   177: astore 5
    //   179: aconst_null
    //   180: astore 6
    //   182: goto -21 -> 161
    //   185: astore 7
    //   187: aload 6
    //   189: astore 5
    //   191: goto -50 -> 141
    //   194: iload_1
    //   195: iconst_2
    //   196: iadd
    //   197: iconst_3
    //   198: irem
    //   199: ifne -75 -> 124
    //   202: iconst_1
    //   203: istore 4
    //   205: goto -113 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	208	0	this	bdd
    //   20	177	1	k	int
    //   69	47	2	m	int
    //   88	33	3	bool1	boolean
    //   102	102	4	bool2	boolean
    //   23	144	5	localObject1	Object
    //   169	1	5	localException1	Exception
    //   177	1	5	localObject2	Object
    //   189	1	5	localObject3	Object
    //   17	145	6	localObject4	Object
    //   172	1	6	localException2	Exception
    //   180	8	6	localObject5	Object
    //   30	70	7	localObject6	Object
    //   136	10	7	localException3	Exception
    //   151	7	7	localObject7	Object
    //   185	1	7	localException4	Exception
    // Exception table:
    //   from	to	target	type
    //   0	19	136	java/lang/Exception
    //   25	32	151	finally
    //   41	62	151	finally
    //   74	81	151	finally
    //   96	107	151	finally
    //   141	151	151	finally
    //   130	135	169	java/lang/Exception
    //   161	166	172	java/lang/Exception
    //   0	19	177	finally
    //   25	32	185	java/lang/Exception
    //   41	62	185	java/lang/Exception
    //   74	81	185	java/lang/Exception
    //   96	107	185	java/lang/Exception
  }
  
  public final void j()
  {
    a(false, false, new String[] { "00200A81408079953000002111224150814415D4F29C0E9A36A721C5B43C7EBBC92076DA5D06" });
    a(false, true, new String[] { "00200A81408079953000002111224150814415D4F29C0E9A36A721C5B43C7EBBC92076DA5D06" });
    a(false, false, new String[] { "07912160130350F6040B919171940859F400005130916041028A09C834885D4F8FD169" });
    a(false, true, new String[] { "07912160130350F5040B919171940859F400005130916041148A09C834885D4F8FD169" });
  }
  
  public final void k()
  {
    g("00200A81408079953000002111224150814415D4F29C0E9A36A721C5B43C7EBBC92076DA5D06");
  }
  
  public final void l()
  {
    g("07911614910930F1040B911614002167F30008315032711201040CD83DDE1C0074006500730074");
  }
  
  public final void m()
  {
    final int k = 0;
    while (k < 3)
    {
      new Thread(new Runnable()
      {
        public final void run()
        {
          long l = k * 1000 + 5000;
          try
          {
            Thread.sleep(l);
            String str;
            switch (k % 3)
            {
            default: 
              str = "07911614910930F1040B911614002167F30008315032711201040CD83DDE1C0074006500730074";
            }
            for (;;)
            {
              bdd.a(bdd.this, false, new String[] { str });
              bdd.a(bdd.this, "0408975903");
              return;
              str = "07911614910981F1440B911604985709F3000031403011645544A00500030602016031D98C56B3DD7039584C36A3D56C375C0E1693CD6835DB0D9783C564335ACD76C3E56031D98C56B3DD7039584C36A3D56C375C0E1693CD6835DB0D9783C564335ACD76C3E56031D98C56B3DD7039584C36A3D56C375C0E1693CD6835DB0D9783C564335ACD76C3E56031D98C56B3DD7039584C36A3D56C375C0E1693CD6835DB0D9783C564";
              continue;
              str = "00200A81408079953000002111224150814415D4F29C0E9A36A721C5B43C7EBBC92076DA5D06";
            }
          }
          catch (InterruptedException localInterruptedException)
          {
            for (;;) {}
          }
        }
      }).start();
      k += 1;
    }
  }
  
  public final void n()
  {
    App.getAppHandler().postDelayed(new Runnable()
    {
      public final void run()
      {
        azs localAzs = new azs();
        localAzs.d = "text/plain";
        localAzs.e = new azd("MMS!".getBytes());
        azo localAzo = new azo();
        localAzo.h = new ayb(new aya("0408975903"));
        localAzo.i = "Test MMS";
        localAzo.u.a(localAzs);
        localAzo.u.a(localAzs);
        bhw.a().a(localAzo);
        bhw.a().a(localAzo);
      }
    }, 3000L);
  }
  
  public final void o()
  {
    App.getAppHandler().postDelayed(new Runnable()
    {
      public final void run()
      {
        Object localObject = bdd.this;
        localObject = bdd.aj();
        bdd localBdd = bdd.this;
        bdd.a(bdd.this.a((ayb)localObject, ((ayb)localObject).k(), "Howdy!", System.currentTimeMillis()));
      }
    }, 3000L);
  }
  
  public final void p()
  {
    App.getApp().post(new Runnable()
    {
      public final void run()
      {
        Object localObject = bdd.this;
        localObject = bdd.aj();
        File localFile = bde.q;
        bdd localBdd = bdd.this;
        localBdd = bdd.this;
        localBdd = bdd.this;
        bdd.a(bdd.a(bdd.aj(), ((ayb)localObject).j(), new aze(localFile), cpx.a(Uri.fromFile(localFile)), System.currentTimeMillis()));
      }
    }, 3000L);
  }
  
  public final void q()
  {
    ayb localAyb = al();
    int k = 10;
    while (k < 50)
    {
      localAyb.e(new aya("04089759" + k));
      k += 1;
    }
    b(a(localAyb, localAyb.k(), "a 1234567890 a b c d e f g h i j k l m n o p q r s t u v w x y z a b c d e f g h i j k l m n o p q r s t u v w x y z", System.currentTimeMillis()));
    b(a(localAyb, localAyb.j(), "Howdy!", System.currentTimeMillis()));
  }
  
  public final void r()
  {
    App.getAppHandler().postDelayed(new Runnable()
    {
      public final void run()
      {
        azs localAzs = new azs();
        localAzs.d = "text/html";
        localAzs.e = new azd("<html><<body><h1>HTML Attachment</h1><p>This is a test attachment</p><img src='https://www.google.com.au/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png'></body>".getBytes());
        azo localAzo = new azo();
        localAzo.h = new ayb(new aya("0408975903"));
        localAzo.u.a(localAzs);
        bhw.a().a(localAzo);
      }
    }, 3000L);
  }
  
  public final void s()
  {
    this.h.startService(new cpr(this.h, bmq.a).a("android.intent.action.RESPOND_VIA_MESSAGE").a(Uri.parse("sms:0408975903")).a("android.intent.extra.TEXT", "Call me later").b);
  }
  
  public final void t()
  {
    App.getAppHandler().postDelayed(new Runnable()
    {
      public final void run()
      {
        bdd.a(bdd.this, false, new String[] { "07911614910981F1440B911604985709F3000031403011645544A00500030602016031D98C56B3DD7039584C36A3D56C375C0E1693CD6835DB0D9783C564335ACD76C3E56031D98C56B3DD7039584C36A3D56C375C0E1693CD6835DB0D9783C564335ACD76C3E56031D98C56B3DD7039584C36A3D56C375C0E1693CD6835DB0D9783C564335ACD76C3E56031D98C56B3DD7039584C36A3D56C375C0E1693CD6835DB0D9783C564", "07911614910981F1440B911604985709F30000314030116455440F05000306020266B49AED86CBE101" });
      }
    }, 3000L);
  }
  
  public final void u()
  {
    try
    {
      Object localObject = cpe.c(bde.c);
      localObject = new cpr("android.provider.Telephony.WAP_PUSH_DELIVER").b("application/vnd.wap.mms-message").a("data", (Serializable)localObject).b;
      this.h.sendOrderedBroadcast((Intent)localObject, null);
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  public final void w()
  {
    try
    {
      cqg.a(new FileInputStream(this.h.getDatabasePath("messaging.db")), new FileOutputStream(new File(bde.a, "messaging.db")));
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  public final void y()
  {
    Object localObject = new ArrayList();
    int m = g.length;
    int k = 0;
    while (k < m)
    {
      a(g[k], (List)localObject);
      k += 1;
    }
    a((List)localObject);
    Collections.sort((List)localObject, new Comparator() {});
    StringBuilder localStringBuilder = new StringBuilder();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      bei localBei = (bei)((Iterator)localObject).next();
      if (localStringBuilder.length() != 0) {
        localStringBuilder.append(",\n");
      }
      localStringBuilder.append("0x").append(Integer.toHexString(localBei.a));
      localStringBuilder.append(", ");
      localStringBuilder.append("0x").append(Integer.toHexString(localBei.b));
    }
    try
    {
      cpe.a(bde.i, ("// Generated by DebugMenuDelegate class: do not modify manually! Search for this string instead to see how to re-create it\nprivate static final int[] ORDERED_POTENTIALS_LIST = new int[] {\n" + localStringBuilder.toString() + "\n};").getBytes());
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
}
