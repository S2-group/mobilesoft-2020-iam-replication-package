import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mbinc12.mb32b.MainPage;
import mbinc12.mb32b.utils.MixerBoxUtils;
import mbinc12.mb32b.virtualcurrency.VirtualCurrencyInitService;
import org.json.JSONArray;
import org.json.JSONObject;

public final class asc
{
  public static ash a;
  private static asj b = null;
  private static Context c;
  private static JSONObject d = new JSONObject();
  private static boolean e = false;
  private static JSONObject f = new JSONObject();
  private static ash.e g = new ash.e()
  {
    public final void a(asi paramAnonymousAsi, asj paramAnonymousAsj)
    {
      if (asc.a == null) {}
      Object localObject1;
      Object localObject2;
      String str;
      do
      {
        do
        {
          do
          {
            return;
            while (paramAnonymousAsi.b()) {}
            paramAnonymousAsi = asc.b();
            localObject1 = paramAnonymousAsi.keys();
            while (((Iterator)localObject1).hasNext()) {
              if (!paramAnonymousAsj.b((String)((Iterator)localObject1).next())) {
                ((Iterator)localObject1).remove();
              }
            }
            asc.b(paramAnonymousAsi);
            asc.a(paramAnonymousAsj);
            localObject2 = paramAnonymousAsi.keys();
          } while (!((Iterator)localObject2).hasNext());
          str = (String)((Iterator)localObject2).next();
          localObject1 = (ask)paramAnonymousAsj.b.get(str);
        } while (localObject1 == null);
        asc.g();
        localObject2 = ((ask)localObject1).b;
      } while ((asc.h() == null) || (!(asc.h() instanceof MainPage)));
      final long l = ((MainPage)asc.h()).c((String)localObject2);
      if (l != -1L)
      {
        paramAnonymousAsj = ((MainPage)asc.h()).a(l);
        if (paramAnonymousAsj.getCount() <= 0) {
          break label316;
        }
        paramAnonymousAsj.moveToFirst();
      }
      label316:
      for (paramAnonymousAsi = paramAnonymousAsj.getString(6);; paramAnonymousAsi = "")
      {
        paramAnonymousAsj.close();
        while (l != -1L)
        {
          asc.a.a((ask)localObject1, new ash.a()
          {
            public final void a(ask paramAnonymous2Ask, asi paramAnonymous2Asi)
            {
              if (paramAnonymous2Asi.a()) {
                asc.a(asc.h(), l, paramAnonymous2Ask, this.b);
              }
            }
          });
          return;
          try
          {
            paramAnonymousAsi = paramAnonymousAsi.getJSONObject(str).getJSONObject("content").getString("amount");
            if ((paramAnonymousAsi != null) && (paramAnonymousAsj.b(str)))
            {
              localObject2 = paramAnonymousAsj.a(str).e;
              paramAnonymousAsj = paramAnonymousAsj.a(str).d;
              l = ((MainPage)asc.h()).a((ask)localObject1, (String)localObject2, paramAnonymousAsj, paramAnonymousAsi);
            }
          }
          catch (Exception paramAnonymousAsi)
          {
            for (;;)
            {
              paramAnonymousAsi = null;
            }
            l = -1L;
          }
        }
        break;
      }
    }
  };
  
  public static long a(arz paramArz, ask paramAsk, String paramString1, String paramString2, String paramString3)
  {
    if (paramArz == null) {}
    for (;;)
    {
      return -1L;
      try
      {
        String str1 = paramAsk.d;
        long l = paramAsk.e;
        String str2 = paramAsk.b;
        String str3 = paramAsk.i;
        paramAsk = paramAsk.j;
        if (paramArz.a.isOpen())
        {
          ContentValues localContentValues = new ContentValues();
          localContentValues.put("NAME", paramString1);
          localContentValues.put("PRODUCT_ID", str1);
          localContentValues.put("TIMESTAMP", String.valueOf(l));
          localContentValues.put("PRICE", paramString2);
          localContentValues.put("TYPE", "coin");
          localContentValues.put("AMOUNT", paramString3);
          localContentValues.put("ORDER_ID", str2);
          localContentValues.put("ORIGINAL_JSON", str3);
          localContentValues.put("SIGNATURE", paramAsk);
          localContentValues.put("PROCESS_SUCCESS", Integer.valueOf(0));
          l = paramArz.a.insert("tableIapReceipt", null, localContentValues);
          return l;
        }
      }
      catch (Exception paramArz) {}
    }
    return -1L;
  }
  
  public static void a(final Context paramContext, ark paramArk)
  {
    arx.a(arx.d(), new AsyncHttpResponseHandler()
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        paramAnonymousThrowable.toString();
        asc.h(paramContext);
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        if (paramAnonymousArrayOfByte != null) {}
        for (;;)
        {
          try
          {
            paramAnonymousArrayOfXh = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject("getVirtualCurrencyUserPage").getJSONArray("items");
            this.a.a = new ArrayList();
            paramAnonymousInt = 0;
            if (paramAnonymousInt < paramAnonymousArrayOfXh.length())
            {
              paramAnonymousArrayOfByte = paramAnonymousArrayOfXh.getJSONObject(paramAnonymousInt);
              MixerBoxUtils.a(this.a.a, paramAnonymousArrayOfByte, paramContext, paramAnonymousInt, paramAnonymousArrayOfXh.length() - paramAnonymousInt - 1);
              paramAnonymousInt += 1;
              continue;
            }
          }
          catch (Exception paramAnonymousArrayOfXh)
          {
            int i;
            continue;
          }
          try
          {
            paramAnonymousArrayOfByte = ((MainPage)paramContext).an.e();
            i = paramAnonymousArrayOfByte.getCount();
            paramAnonymousArrayOfByte.close();
            if (i <= 0) {
              continue;
            }
            i = 1;
            if (i != 0)
            {
              paramAnonymousArrayOfByte = new JSONObject();
              paramAnonymousArrayOfByte.put("type", "simple_text");
              paramAnonymousArrayOfByte.put("mainText", paramContext.getResources().getString(2131230897));
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("type", "app_action");
              localJSONObject.put("action", "show_receipt");
              paramAnonymousArrayOfByte.put("mainLink", localJSONObject);
              MixerBoxUtils.a(this.a.a, paramAnonymousArrayOfByte, paramContext, paramAnonymousInt, paramAnonymousArrayOfXh.length() - paramAnonymousInt - 1);
            }
          }
          catch (Exception paramAnonymousArrayOfXh)
          {
            continue;
          }
          this.a.a();
          asc.h(paramContext);
          return;
          i = 0;
        }
      }
    });
  }
  
  public static void a(Context paramContext, String paramString)
  {
    int k = asd.e(paramContext, paramString) + 1;
    Object localObject = paramContext.getSharedPreferences("VIRTUAL_CURRENCY_SETTING", 0).edit();
    ((SharedPreferences.Editor)localObject).putInt("OVERALLVALUE_" + paramString, k);
    ((SharedPreferences.Editor)localObject).commit();
    long l = paramContext.getSharedPreferences("VIRTUAL_CURRENCY_SETTING", 0).getLong("TIME_" + paramString, -1L);
    int i;
    if (l < 0L)
    {
      i = 1;
      if (i == 0) {
        break label362;
      }
      i = 1;
      label105:
      localObject = paramContext.getSharedPreferences("VIRTUAL_CURRENCY_SETTING", 0).edit();
      ((SharedPreferences.Editor)localObject).putInt("DAILYVALUE_" + paramString, i);
      ((SharedPreferences.Editor)localObject).commit();
      l = Calendar.getInstance().getTimeInMillis();
      localObject = paramContext.getSharedPreferences("VIRTUAL_CURRENCY_SETTING", 0).edit();
      ((SharedPreferences.Editor)localObject).putLong("TIME_" + paramString, l);
      ((SharedPreferences.Editor)localObject).commit();
    }
    for (;;)
    {
      try
      {
        if (!d.isNull(paramString))
        {
          localObject = d.getJSONObject(paramString);
          Iterator localIterator = ((JSONObject)localObject).keys();
          if (localIterator.hasNext())
          {
            String str = (String)localIterator.next();
            if (!a(paramContext, ((JSONObject)localObject).getJSONObject(str), paramString)) {
              continue;
            }
            if (!((JSONObject)localObject).getJSONObject(str).isNull("repeat")) {
              break label373;
            }
            j = k;
            a(paramContext, paramString, str, j);
            continue;
          }
        }
        return;
      }
      catch (Exception paramContext) {}
      localObject = new SimpleDateFormat("dd/MM/yyyy");
      if (!((SimpleDateFormat)localObject).format(new Date(l)).equals(((SimpleDateFormat)localObject).format(new Date(Calendar.getInstance().getTimeInMillis()))))
      {
        i = 1;
        break;
      }
      i = 0;
      break;
      label362:
      i = asd.d(paramContext, paramString) + 1;
      break label105;
      label373:
      int j = i;
    }
  }
  
  public static void a(Context paramContext, final String paramString1, final String paramString2)
  {
    arx.a(arx.c(paramString1, paramString2), new AsyncHttpResponseHandler()
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        paramAnonymousThrowable.toString();
        asc.h(this.a);
      }
      
      /* Error */
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        // Byte code:
        //   0: aload_3
        //   1: ifnull +215 -> 216
        //   4: new 44	org/json/JSONObject
        //   7: dup
        //   8: new 46	java/lang/String
        //   11: dup
        //   12: aload_3
        //   13: invokespecial 49	java/lang/String:<init>	([B)V
        //   16: invokespecial 52	org/json/JSONObject:<init>	(Ljava/lang/String;)V
        //   19: ldc 54
        //   21: invokevirtual 58	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   24: astore_2
        //   25: new 60	android/app/AlertDialog$Builder
        //   28: dup
        //   29: aload_0
        //   30: getfield 18	asc$3:a	Landroid/content/Context;
        //   33: invokespecial 62	android/app/AlertDialog$Builder:<init>	(Landroid/content/Context;)V
        //   36: astore_3
        //   37: aload_0
        //   38: getfield 18	asc$3:a	Landroid/content/Context;
        //   41: invokestatic 68	android/view/LayoutInflater:from	(Landroid/content/Context;)Landroid/view/LayoutInflater;
        //   44: ldc 69
        //   46: aconst_null
        //   47: invokevirtual 73	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;)Landroid/view/View;
        //   50: astore 5
        //   52: aload 5
        //   54: ldc 74
        //   56: invokevirtual 80	android/view/View:findViewById	(I)Landroid/view/View;
        //   59: checkcast 82	android/widget/TextView
        //   62: aload_2
        //   63: ldc 84
        //   65: invokevirtual 88	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   68: invokevirtual 92	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
        //   71: aload_3
        //   72: aload 5
        //   74: invokevirtual 96	android/app/AlertDialog$Builder:setView	(Landroid/view/View;)Landroid/app/AlertDialog$Builder;
        //   77: pop
        //   78: aload_3
        //   79: aload_2
        //   80: ldc 98
        //   82: invokevirtual 88	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   85: invokevirtual 102	android/app/AlertDialog$Builder:setTitle	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
        //   88: pop
        //   89: aload_3
        //   90: aload_0
        //   91: getfield 18	asc$3:a	Landroid/content/Context;
        //   94: invokevirtual 108	android/content/Context:getResources	()Landroid/content/res/Resources;
        //   97: ldc 109
        //   99: invokevirtual 114	android/content/res/Resources:getString	(I)Ljava/lang/String;
        //   102: new 11	asc$3$1
        //   105: dup
        //   106: aload_0
        //   107: invokespecial 117	asc$3$1:<init>	(Lasc$3;)V
        //   110: invokevirtual 121	android/app/AlertDialog$Builder:setPositiveButton	(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
        //   113: pop
        //   114: aload_3
        //   115: invokevirtual 125	android/app/AlertDialog$Builder:create	()Landroid/app/AlertDialog;
        //   118: invokevirtual 130	android/app/AlertDialog:show	()V
        //   121: aload_2
        //   122: ldc -124
        //   124: invokevirtual 136	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
        //   127: istore 4
        //   129: iload 4
        //   131: ifeq +29 -> 160
        //   134: aload_0
        //   135: getfield 20	asc$3:b	Ljava/lang/String;
        //   138: ldc -118
        //   140: invokevirtual 142	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   143: ifeq +17 -> 160
        //   146: aload_0
        //   147: getfield 18	asc$3:a	Landroid/content/Context;
        //   150: checkcast 144	mbinc12/mb32b/MainPage
        //   153: aload_0
        //   154: getfield 22	asc$3:c	Ljava/lang/String;
        //   157: invokevirtual 147	mbinc12/mb32b/MainPage:g	(Ljava/lang/String;)V
        //   160: new 149	java/util/HashMap
        //   163: dup
        //   164: invokespecial 150	java/util/HashMap:<init>	()V
        //   167: astore_2
        //   168: iload 4
        //   170: ifeq +54 -> 224
        //   173: aload_2
        //   174: ldc -124
        //   176: ldc -104
        //   178: invokeinterface 158 3 0
        //   183: pop
        //   184: aload_2
        //   185: ldc -96
        //   187: aload_0
        //   188: getfield 20	asc$3:b	Ljava/lang/String;
        //   191: invokeinterface 158 3 0
        //   196: pop
        //   197: aload_2
        //   198: ldc -94
        //   200: aload_0
        //   201: getfield 22	asc$3:c	Ljava/lang/String;
        //   204: invokeinterface 158 3 0
        //   209: pop
        //   210: ldc -92
        //   212: aload_2
        //   213: invokestatic 169	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/lang/String;Ljava/util/Map;)V
        //   216: aload_0
        //   217: getfield 18	asc$3:a	Landroid/content/Context;
        //   220: invokestatic 38	asc:h	(Landroid/content/Context;)V
        //   223: return
        //   224: aload_2
        //   225: ldc -124
        //   227: ldc -85
        //   229: invokeinterface 158 3 0
        //   234: pop
        //   235: goto -51 -> 184
        //   238: astore_2
        //   239: goto -23 -> 216
        //   242: astore_2
        //   243: goto -83 -> 160
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	246	0	this	3
        //   0	246	1	paramAnonymousInt	int
        //   0	246	2	paramAnonymousArrayOfXh	xh[]
        //   0	246	3	paramAnonymousArrayOfByte	byte[]
        //   127	42	4	bool	boolean
        //   50	23	5	localView	View
        // Exception table:
        //   from	to	target	type
        //   4	129	238	java/lang/Exception
        //   160	168	238	java/lang/Exception
        //   173	184	238	java/lang/Exception
        //   184	216	238	java/lang/Exception
        //   224	235	238	java/lang/Exception
        //   134	160	242	java/lang/Exception
      }
    });
  }
  
  private static void a(Context paramContext, final String paramString1, final String paramString2, int paramInt)
  {
    arx.a(arx.b(paramString1, paramString2, paramInt), new AsyncHttpResponseHandler()
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        paramAnonymousThrowable.toString();
        asc.h(this.a);
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        if (paramAnonymousArrayOfByte != null) {}
        try
        {
          paramAnonymousArrayOfXh = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject("triggerAchievement");
          if ((!paramAnonymousArrayOfXh.isNull("reward")) && (!paramAnonymousArrayOfXh.isNull("success")) && (!paramAnonymousArrayOfXh.isNull("title")) && (paramAnonymousArrayOfXh.getBoolean("success")))
          {
            paramAnonymousArrayOfByte = paramAnonymousArrayOfXh.getJSONObject("reward");
            if ((!paramAnonymousArrayOfByte.isNull("type")) && (!paramAnonymousArrayOfByte.isNull("amount"))) {
              asc.a(this.a, paramAnonymousArrayOfByte.getString("type"), paramAnonymousArrayOfByte.getInt("amount"), paramAnonymousArrayOfXh.getString("title"));
            }
          }
          paramAnonymousArrayOfXh = new HashMap();
          paramAnonymousArrayOfXh.put("action", paramString1);
          paramAnonymousArrayOfXh.put("achievement", paramString2);
          MixerBoxUtils.a("action:vc_trigger_achievement", paramAnonymousArrayOfXh);
        }
        catch (Exception paramAnonymousArrayOfXh)
        {
          for (;;) {}
        }
        asc.h(this.a);
      }
    });
  }
  
  /* Error */
  public static void a(Context paramContext, String paramString1, final String paramString2, final String paramString3, final String paramString4)
  {
    // Byte code:
    //   0: getstatic 334	asc:a	Lash;
    //   3: astore 6
    //   5: aload_0
    //   6: checkcast 336	android/app/Activity
    //   9: astore 7
    //   11: new 20	asc$6
    //   14: dup
    //   15: aload_0
    //   16: aload_2
    //   17: aload_3
    //   18: aload 4
    //   20: invokespecial 339	asc$6:<init>	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   23: astore_2
    //   24: aload 6
    //   26: invokevirtual 343	ash:a	()V
    //   29: aload 6
    //   31: ldc_w 345
    //   34: invokevirtual 347	ash:a	(Ljava/lang/String;)V
    //   37: aload 6
    //   39: ldc_w 345
    //   42: invokevirtual 349	ash:b	(Ljava/lang/String;)V
    //   45: ldc_w 351
    //   48: ldc_w 353
    //   51: invokevirtual 270	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   54: ifeq +39 -> 93
    //   57: aload 6
    //   59: getfield 354	ash:e	Z
    //   62: ifne +31 -> 93
    //   65: new 356	asi
    //   68: dup
    //   69: sipush 64527
    //   72: ldc_w 358
    //   75: invokespecial 361	asi:<init>	(ILjava/lang/String;)V
    //   78: astore_1
    //   79: aload 6
    //   81: invokevirtual 363	ash:b	()V
    //   84: aload_2
    //   85: aload_1
    //   86: aconst_null
    //   87: invokeinterface 368 3 0
    //   92: return
    //   93: new 180	java/lang/StringBuilder
    //   96: dup
    //   97: ldc_w 370
    //   100: invokespecial 185	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   103: aload_1
    //   104: invokevirtual 189	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: ldc_w 372
    //   110: invokevirtual 189	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: ldc_w 351
    //   116: invokevirtual 189	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: aload 6
    //   122: getfield 375	ash:i	Lcom/android/vending/billing/IInAppBillingService;
    //   125: iconst_3
    //   126: aload 6
    //   128: getfield 378	ash:h	Landroid/content/Context;
    //   131: invokevirtual 381	android/content/Context:getPackageName	()Ljava/lang/String;
    //   134: aload_1
    //   135: ldc_w 351
    //   138: ldc_w 383
    //   141: invokeinterface 389 6 0
    //   146: astore_3
    //   147: aload_3
    //   148: invokestatic 392	ash:a	(Landroid/os/Bundle;)I
    //   151: istore 5
    //   153: iload 5
    //   155: ifeq +107 -> 262
    //   158: new 180	java/lang/StringBuilder
    //   161: dup
    //   162: ldc_w 394
    //   165: invokespecial 185	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   168: iload 5
    //   170: invokestatic 397	ash:a	(I)Ljava/lang/String;
    //   173: invokevirtual 189	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: pop
    //   177: aload 6
    //   179: invokevirtual 363	ash:b	()V
    //   182: aload_2
    //   183: new 356	asi
    //   186: dup
    //   187: iload 5
    //   189: ldc_w 399
    //   192: invokespecial 361	asi:<init>	(ILjava/lang/String;)V
    //   195: aconst_null
    //   196: invokeinterface 368 3 0
    //   201: return
    //   202: astore_1
    //   203: aload_1
    //   204: invokevirtual 402	android/content/IntentSender$SendIntentException:printStackTrace	()V
    //   207: aload 6
    //   209: invokevirtual 363	ash:b	()V
    //   212: aload_2
    //   213: new 356	asi
    //   216: dup
    //   217: sipush 64532
    //   220: ldc_w 404
    //   223: invokespecial 361	asi:<init>	(ILjava/lang/String;)V
    //   226: aconst_null
    //   227: invokeinterface 368 3 0
    //   232: return
    //   233: astore_1
    //   234: aload_1
    //   235: invokevirtual 405	java/lang/Exception:toString	()Ljava/lang/String;
    //   238: pop
    //   239: aload_0
    //   240: ifnull -148 -> 92
    //   243: aload_0
    //   244: aload_0
    //   245: invokevirtual 409	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   248: ldc_w 410
    //   251: invokevirtual 415	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   254: iconst_0
    //   255: iconst_0
    //   256: newarray boolean
    //   258: invokestatic 420	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;I[Z)V
    //   261: return
    //   262: aload_3
    //   263: ldc_w 422
    //   266: invokevirtual 428	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   269: checkcast 430	android/app/PendingIntent
    //   272: astore_3
    //   273: new 180	java/lang/StringBuilder
    //   276: dup
    //   277: ldc_w 432
    //   280: invokespecial 185	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   283: aload_1
    //   284: invokevirtual 189	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: ldc_w 434
    //   290: invokevirtual 189	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: pop
    //   294: aload 6
    //   296: sipush 10001
    //   299: putfield 438	ash:k	I
    //   302: aload 6
    //   304: aload_2
    //   305: putfield 442	ash:n	Lash$c;
    //   308: aload 6
    //   310: ldc_w 351
    //   313: putfield 445	ash:l	Ljava/lang/String;
    //   316: aload 7
    //   318: aload_3
    //   319: invokevirtual 449	android/app/PendingIntent:getIntentSender	()Landroid/content/IntentSender;
    //   322: sipush 10001
    //   325: new 451	android/content/Intent
    //   328: dup
    //   329: invokespecial 452	android/content/Intent:<init>	()V
    //   332: iconst_0
    //   333: invokestatic 134	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   336: invokevirtual 456	java/lang/Integer:intValue	()I
    //   339: iconst_0
    //   340: invokestatic 134	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   343: invokevirtual 456	java/lang/Integer:intValue	()I
    //   346: iconst_0
    //   347: invokestatic 134	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   350: invokevirtual 456	java/lang/Integer:intValue	()I
    //   353: invokevirtual 460	android/app/Activity:startIntentSenderForResult	(Landroid/content/IntentSender;ILandroid/content/Intent;III)V
    //   356: return
    //   357: astore_1
    //   358: aload_1
    //   359: invokevirtual 461	android/os/RemoteException:printStackTrace	()V
    //   362: aload 6
    //   364: invokevirtual 363	ash:b	()V
    //   367: aload_2
    //   368: new 356	asi
    //   371: dup
    //   372: sipush 64535
    //   375: ldc_w 463
    //   378: invokespecial 361	asi:<init>	(ILjava/lang/String;)V
    //   381: aconst_null
    //   382: invokeinterface 368 3 0
    //   387: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	388	0	paramContext	Context
    //   0	388	1	paramString1	String
    //   0	388	2	paramString2	String
    //   0	388	3	paramString3	String
    //   0	388	4	paramString4	String
    //   151	37	5	i	int
    //   3	360	6	localAsh	ash
    //   9	308	7	localActivity	android.app.Activity
    // Exception table:
    //   from	to	target	type
    //   93	153	202	android/content/IntentSender$SendIntentException
    //   158	201	202	android/content/IntentSender$SendIntentException
    //   262	356	202	android/content/IntentSender$SendIntentException
    //   0	92	233	java/lang/Exception
    //   93	153	233	java/lang/Exception
    //   158	201	233	java/lang/Exception
    //   203	232	233	java/lang/Exception
    //   262	356	233	java/lang/Exception
    //   358	387	233	java/lang/Exception
    //   93	153	357	android/os/RemoteException
    //   158	201	357	android/os/RemoteException
    //   262	356	357	android/os/RemoteException
  }
  
  private static void a(Cursor paramCursor)
  {
    if (paramCursor == null) {
      return;
    }
    int j = paramCursor.getCount();
    paramCursor.moveToFirst();
    int i = 0;
    for (;;)
    {
      long l;
      String str;
      if (i < j)
      {
        l = paramCursor.getLong(0);
        str = paramCursor.getString(6);
      }
      try
      {
        ask localAsk = new ask("ITEM_TYPE_INAPP", paramCursor.getString(8), paramCursor.getString(9));
        b(c, l, localAsk, str);
        paramCursor.moveToNext();
        i += 1;
        continue;
        paramCursor.close();
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public static void a(asj paramAsj)
  {
    b = paramAsj;
  }
  
  public static void a(JSONObject paramJSONObject)
  {
    try
    {
      d = paramJSONObject;
      return;
    }
    finally
    {
      paramJSONObject = finally;
      throw paramJSONObject;
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    e = paramBoolean;
  }
  
  public static boolean a()
  {
    return e;
  }
  
  public static boolean a(int paramInt1, int paramInt2, Intent paramIntent)
  {
    return (a != null) && (a.a(paramInt1, paramInt2, paramIntent));
  }
  
  public static boolean a(Context paramContext)
  {
    return paramContext.getSharedPreferences("VIRTUAL_CURRENCY_SETTING", 0).getBoolean("enabled", true);
  }
  
  private static boolean a(Context paramContext, JSONObject paramJSONObject, String paramString)
  {
    for (;;)
    {
      try
      {
        if (!paramJSONObject.isNull("valueLowBound"))
        {
          i = paramJSONObject.getInt("valueLowBound");
          if (!paramJSONObject.isNull("repeat"))
          {
            paramJSONObject = paramJSONObject.getJSONObject("repeat");
            if ((paramJSONObject.isNull("type")) || (paramJSONObject.isNull("interval")) || (!paramJSONObject.getString("type").equals("calendarDay")) || (paramJSONObject.getInt("interval") != 1)) {
              break;
            }
            if (asd.d(paramContext, paramString) < i) {
              break label154;
            }
            if (paramJSONObject.isNull("nextTimestamp")) {
              break;
            }
            long l = paramJSONObject.getLong("nextTimestamp");
            if (Calendar.getInstance().getTimeInMillis() / 1000L <= l) {
              break label152;
            }
            return true;
          }
          int j = asd.e(paramContext, paramString);
          if (j >= i) {
            break;
          }
          return false;
        }
      }
      catch (Exception paramContext)
      {
        return true;
      }
      int i = 0;
    }
    return true;
    label152:
    return false;
    label154:
    return false;
  }
  
  public static boolean a(String paramString)
  {
    return (b != null) && (b.b(paramString));
  }
  
  public static int b(Context paramContext)
  {
    return paramContext.getSharedPreferences("VIRTUAL_CURRENCY_SETTING", 0).getInt("deposit", 0);
  }
  
  public static String b(String paramString)
  {
    if ((b != null) && (b.b(paramString))) {
      return b.a(paramString).e;
    }
    return "";
  }
  
  public static JSONObject b()
  {
    return f;
  }
  
  private static void b(Context paramContext, final long paramLong, ask paramAsk, String paramString)
  {
    RequestParams localRequestParams = new RequestParams();
    localRequestParams.put("data", paramAsk.i);
    localRequestParams.put("signature", paramAsk.j);
    localRequestParams.put("content[type]", "coin");
    localRequestParams.put("content[amount]", paramString);
    arx.a(arx.e(), localRequestParams, new AsyncHttpResponseHandler()
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        ((MainPage)this.a).a(paramLong, false);
        asc.h(this.a);
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        if (paramAnonymousArrayOfByte != null) {}
        for (;;)
        {
          try
          {
            paramAnonymousArrayOfByte = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject("submitPurchase");
            if ((paramAnonymousArrayOfByte.isNull("status")) || (paramAnonymousArrayOfByte.getInt("status") == 1)) {
              continue;
            }
            ((MainPage)this.a).a(paramLong, true);
            if ((!paramAnonymousArrayOfByte.isNull("title")) && (!paramAnonymousArrayOfByte.isNull("message")))
            {
              paramAnonymousArrayOfXh = paramAnonymousArrayOfByte.getString("title");
              paramAnonymousArrayOfByte = paramAnonymousArrayOfByte.getString("message");
              AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.a);
              View localView = LayoutInflater.from(this.a).inflate(2130903105, null);
              ((TextView)localView.findViewById(2131624163)).setText(paramAnonymousArrayOfByte);
              localBuilder.setView(localView);
              localBuilder.setTitle(paramAnonymousArrayOfXh);
              localBuilder.setPositiveButton(this.a.getResources().getString(2131230851), new DialogInterface.OnClickListener()
              {
                public final void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  paramAnonymous2DialogInterface.dismiss();
                }
              });
              localBuilder.create().show();
            }
          }
          catch (Exception paramAnonymousArrayOfXh)
          {
            ((MainPage)this.a).a(paramLong, false);
            continue;
          }
          asc.h(this.a);
          return;
          ((MainPage)this.a).a(paramLong, false);
        }
      }
    });
  }
  
  public static void b(Context paramContext, ark paramArk)
  {
    JSONArray localJSONArray = ((MainPage)paramContext).o();
    paramArk.a = new ArrayList();
    int i = 0;
    for (;;)
    {
      if (i < localJSONArray.length()) {}
      try
      {
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        MixerBoxUtils.a(paramArk.a, localJSONObject, paramContext, i, localJSONArray.length() - i - 1);
        i += 1;
        continue;
        paramArk.a();
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    arx.a(arx.s(paramString), new AsyncHttpResponseHandler()
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        MixerBoxUtils.a(this.a, this.a.getResources().getString(2131231011), 0, new boolean[0]);
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        if (paramAnonymousArrayOfByte != null) {}
        try
        {
          paramAnonymousArrayOfByte = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject("redeem");
          boolean bool = paramAnonymousArrayOfByte.getBoolean("success");
          if (bool)
          {
            paramAnonymousArrayOfXh = this.a.getResources().getString(2131230963);
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.a);
            View localView = LayoutInflater.from(this.a).inflate(2130903105, null);
            ((TextView)localView.findViewById(2131624163)).setText(paramAnonymousArrayOfByte.getString("message"));
            localBuilder.setView(localView);
            localBuilder.setTitle(paramAnonymousArrayOfXh);
            localBuilder.setPositiveButton(this.a.getResources().getString(2131230851), new DialogInterface.OnClickListener()
            {
              public final void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                paramAnonymous2DialogInterface.dismiss();
              }
            });
            localBuilder.create().show();
            paramAnonymousArrayOfXh = new HashMap();
            if (!bool) {
              break label201;
            }
            paramAnonymousArrayOfXh.put("success", "true");
          }
          for (;;)
          {
            MixerBoxUtils.a("action:vc_redeem", paramAnonymousArrayOfXh);
            asc.h(this.a);
            return;
            paramAnonymousArrayOfXh = this.a.getResources().getString(2131230961);
            break;
            label201:
            paramAnonymousArrayOfXh.put("success", "false");
          }
          return;
        }
        catch (Exception paramAnonymousArrayOfXh) {}
      }
    });
  }
  
  public static void b(JSONObject paramJSONObject)
  {
    f = paramJSONObject;
  }
  
  public static String c(Context paramContext)
  {
    return paramContext.getSharedPreferences("VIRTUAL_CURRENCY_SETTING", 0).getString("promotion", "{}");
  }
  
  public static String c(String paramString)
  {
    if ("com.loopj.android.http".equals("c")) {
      return "";
    }
    if ((b != null) && (b.b(paramString))) {
      return b.a(paramString).d;
    }
    return "";
  }
  
  public static void c()
  {
    if ((c != null) && ((c instanceof MainPage))) {
      a(((MainPage)c).m());
    }
  }
  
  public static String d(Context paramContext)
  {
    return paramContext.getSharedPreferences("VIRTUAL_CURRENCY_SETTING", 0).getString("expenditure", "{}");
  }
  
  private static String d(String paramString)
  {
    try
    {
      paramString = ase.a(paramString.getBytes());
      return new String(paramString);
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static void d()
  {
    if (a != null)
    {
      ash localAsh = a;
      localAsh.c = false;
      if ((localAsh.j != null) && (localAsh.h != null) && (localAsh.i != null)) {
        localAsh.h.unbindService(localAsh.j);
      }
      localAsh.d = true;
      localAsh.h = null;
      localAsh.j = null;
      localAsh.i = null;
      localAsh.n = null;
    }
    a = null;
  }
  
  public static String e(Context paramContext)
  {
    return paramContext.getSharedPreferences("VIRTUAL_CURRENCY_SETTING", 0).getString("boughtexpenditure", "{}");
  }
  
  private static String e(String paramString)
  {
    try
    {
      paramString = ase.a(paramString.getBytes());
      return new String(paramString);
    }
    catch (Exception paramString) {}
    return "";
  }
  
  private static String f(String paramString)
  {
    try
    {
      paramString = ase.a(paramString.getBytes());
      return new String(paramString);
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static boolean f(Context paramContext)
  {
    return paramContext.getSharedPreferences("VIRTUAL_CURRENCY_SETTING", 0).getBoolean("hasboughtremovead", false);
  }
  
  private static String g(String paramString)
  {
    try
    {
      paramString = ase.a(paramString.getBytes());
      return new String(paramString);
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static void g(Context paramContext)
  {
    try
    {
      if (!d.isNull("installApp"))
      {
        JSONObject localJSONObject = d.getJSONObject("installApp");
        Iterator localIterator1 = localJSONObject.keys();
        for (;;)
        {
          if (!localIterator1.hasNext()) {
            break label120;
          }
          String str1 = (String)localIterator1.next();
          String str2 = localJSONObject.getJSONObject(str1).getString("ref");
          Iterator localIterator2 = paramContext.getPackageManager().getInstalledApplications(128).iterator();
          if (localIterator2.hasNext())
          {
            if (!((ApplicationInfo)localIterator2.next()).packageName.equals(str2)) {
              break;
            }
            a(paramContext, "installApp", str1, 1);
          }
        }
      }
      label120:
      return;
    }
    catch (Exception localException)
    {
      try
      {
        if (!d.isNull("launch")) {
          a(paramContext, "launch");
        }
        return;
      }
      catch (Exception paramContext) {}
    }
  }
  
  public static void h(Context paramContext)
  {
    paramContext.startService(new Intent(paramContext, VirtualCurrencyInitService.class));
  }
  
  public static void i(Context paramContext)
  {
    c = paramContext;
    paramContext = d(aru.k()) + e(aru.l()) + f(aru.m()) + g(aru.n());
    paramContext = new ash(c, paramContext);
    a = paramContext;
    ash.d local5 = new ash.d()
    {
      public final void a(asi paramAnonymousAsi)
      {
        if (!paramAnonymousAsi.a()) {}
        while (asc.a == null) {
          return;
        }
        paramAnonymousAsi = new ArrayList();
        Object localObject = asc.b().keys();
        while (((Iterator)localObject).hasNext()) {
          paramAnonymousAsi.add((String)((Iterator)localObject).next());
        }
        localObject = asc.a;
        ash.e localE = asc.e();
        Handler localHandler = new Handler();
        ((ash)localObject).a();
        ((ash)localObject).a("queryInventory");
        ((ash)localObject).b("refresh inventory");
        new Thread(new ash.2((ash)localObject, paramAnonymousAsi, localE, localHandler)).start();
        asc.f();
      }
    };
    paramContext.a();
    if (paramContext.c) {
      throw new IllegalStateException("IAB helper is already set up.");
    }
    paramContext.j = new ash.1(paramContext, local5);
    Intent localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
    localIntent.setPackage("com.android.vending");
    List localList = paramContext.h.getPackageManager().queryIntentServices(localIntent, 0);
    if ((localList != null) && (!localList.isEmpty()))
    {
      paramContext.h.bindService(localIntent, paramContext.j, 1);
      return;
    }
    local5.a(new asi(3, "Billing service unavailable on device."));
  }
}
