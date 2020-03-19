package mbinc12.mb32b.utils;

import alc;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.MailTo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Media;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.a;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.WrapperListAdapter;
import apj;
import apm;
import apn;
import app;
import apq;
import apt;
import apv;
import aqc;
import aqf;
import aqq;
import aqs;
import aqx;
import aqy;
import arc;
import ari;
import arn;
import arx;
import arz;
import asa;
import asb;
import asc;
import bl;
import com.flurry.android.FlurryAgent;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import java.util.TimeZone;
import java.util.UUID;
import ku;
import kv;
import lf;
import mbinc12.mb32b.LoginBridge;
import mbinc12.mb32b.MainPage;
import mbinc12.mb32b.MyApplication;
import mbinc12.mb32b.classes.MyFocusableWebView;
import mbinc12.mb32b.fragments.SongFragment;
import mbinc12.mb32b.services.WindowPlayerService;
import mbinc12.mb32b.services.WindowPlayerService.b;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tk;
import tk.1;
import wo;
import ws.a;
import wt;
import xa;
import xh;

public final class MixerBoxUtils
{
  static int a = 0;
  static Bundle b;
  static apn c = null;
  static String d = null;
  static String e = null;
  static apt f = null;
  static apq g = null;
  static apv h = null;
  static ListPopupWindow i = null;
  static int j = 0;
  static PorterDuffColorFilter k = null;
  public static String l = "";
  static Toast m = null;
  static Toast n = null;
  
  public static int a(Context paramContext, int paramInt)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramInt + 0.5F);
  }
  
  public static AlertDialog a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    return a(paramContext, paramString1, paramString2, paramBoolean, true, null);
  }
  
  public static AlertDialog a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, Runnable paramRunnable)
  {
    if (((Activity)paramContext).isFinishing())
    {
      paramString1 = null;
      return paramString1;
    }
    if (paramBoolean1) {}
    for (paramContext = new AlertDialog.Builder(paramContext).setTitle(paramString1).setMessage(paramString2).setPositiveButton(paramContext.getResources().getString(2131230851), new DialogInterface.OnClickListener()
        {
          public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            if (this.a != null) {
              this.a.run();
            }
          }
        }).create();; paramContext = new AlertDialog.Builder(paramContext).setTitle(paramString1).setMessage(paramString2).create())
    {
      paramString1 = paramContext;
      if (!paramBoolean2) {
        break;
      }
      paramContext.show();
      return paramContext;
    }
  }
  
  private static Intent a(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager.getPackageInfo("com.facebook.katana", 0);
      paramPackageManager = Uri.parse("fb://facewebmodal/f?href=" + paramString);
      return new Intent("android.intent.action.VIEW", paramPackageManager);
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      for (;;)
      {
        paramPackageManager = Uri.parse(paramString);
      }
    }
  }
  
  public static SpannableString a(CharSequence paramCharSequence, aqq paramAqq, Context paramContext)
  {
    SpannableString localSpannableString = new SpannableString(paramCharSequence);
    localSpannableString.setSpan(new StyleSpan(1), 0, paramCharSequence.length(), 33);
    localSpannableString.setSpan(new a(paramAqq), 0, paramCharSequence.length(), 33);
    localSpannableString.setSpan(new NoUnderlineSpan(), 0, paramCharSequence.length(), 33);
    localSpannableString.setSpan(new ForegroundColorSpan(paramContext.getResources().getColor(2131558410)), 0, paramCharSequence.length(), 33);
    return localSpannableString;
  }
  
  public static apm a(JSONObject paramJSONObject)
  {
    for (;;)
    {
      try
      {
        if (!paramJSONObject.isNull("ref"))
        {
          str2 = "0";
          str1 = "";
          str3 = "";
          str4 = "";
          if (!paramJSONObject.isNull("size")) {
            str2 = paramJSONObject.getString("size");
          }
          if (!paramJSONObject.isNull("owner")) {
            str1 = paramJSONObject.getString("owner");
          }
          if (!paramJSONObject.isNull("ownerName")) {
            str1 = paramJSONObject.getString("ownerName");
          }
          if (!paramJSONObject.isNull("thumbnail")) {
            str3 = paramJSONObject.getString("thumbnail");
          }
          if (!paramJSONObject.isNull("ownerId")) {
            str4 = paramJSONObject.getString("ownerId");
          }
          if (paramJSONObject.isNull("isAlbum")) {
            break label330;
          }
          localBoolean = Boolean.valueOf(paramJSONObject.getBoolean("isAlbum"));
          return new apm(paramJSONObject.getString("ref"), paramJSONObject.getString("title"), str4, str1, str2, "0", str3, localBoolean.booleanValue());
        }
        String str1 = "0";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        if (!paramJSONObject.isNull("itemCount")) {
          str1 = paramJSONObject.getString("itemCount");
        }
        if (!paramJSONObject.isNull("ownerName")) {
          str2 = paramJSONObject.getString("ownerName");
        }
        if (!paramJSONObject.isNull("cover")) {
          str3 = paramJSONObject.getString("cover");
        }
        if (!paramJSONObject.isNull("owner")) {
          str4 = paramJSONObject.getString("owner");
        }
        if (!paramJSONObject.isNull("isAlbum"))
        {
          localBoolean = Boolean.valueOf(paramJSONObject.getBoolean("isAlbum"));
          paramJSONObject = new apm(paramJSONObject.getString("_id"), paramJSONObject.getString("name"), str4, str2, str1, "0", str3, localBoolean.booleanValue());
          return paramJSONObject;
        }
      }
      catch (JSONException paramJSONObject)
      {
        return null;
      }
      Boolean localBoolean = Boolean.valueOf(false);
      continue;
      label330:
      localBoolean = Boolean.valueOf(false);
    }
  }
  
  /* Error */
  public static apn a(JSONObject paramJSONObject, int paramInt)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: ldc 123
    //   4: astore 5
    //   6: ldc 123
    //   8: astore 8
    //   10: ldc_w 280
    //   13: astore 9
    //   15: ldc 123
    //   17: astore 15
    //   19: ldc 123
    //   21: astore 11
    //   23: aload 9
    //   25: astore 7
    //   27: aload 15
    //   29: astore 13
    //   31: aload 8
    //   33: astore 12
    //   35: aload 5
    //   37: astore 10
    //   39: aload 11
    //   41: astore 14
    //   43: aload_0
    //   44: ldc_w 325
    //   47: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   50: ifne +619 -> 669
    //   53: aload 9
    //   55: astore 7
    //   57: aload 15
    //   59: astore 13
    //   61: aload 8
    //   63: astore 12
    //   65: aload 5
    //   67: astore 10
    //   69: aload 11
    //   71: astore 14
    //   73: aload_0
    //   74: ldc_w 325
    //   77: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   80: ldc_w 327
    //   83: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   86: ifeq +583 -> 669
    //   89: aload 5
    //   91: astore 4
    //   93: aload 9
    //   95: astore 7
    //   97: aload 15
    //   99: astore 13
    //   101: aload 8
    //   103: astore 12
    //   105: aload 5
    //   107: astore 10
    //   109: aload 11
    //   111: astore 14
    //   113: aload_0
    //   114: ldc_w 320
    //   117: invokevirtual 336	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   120: ifeq +66 -> 186
    //   123: aload 5
    //   125: astore 4
    //   127: aload 9
    //   129: astore 7
    //   131: aload 15
    //   133: astore 13
    //   135: aload 8
    //   137: astore 12
    //   139: aload 5
    //   141: astore 10
    //   143: aload 11
    //   145: astore 14
    //   147: aload_0
    //   148: ldc_w 320
    //   151: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   154: ifne +32 -> 186
    //   157: aload 9
    //   159: astore 7
    //   161: aload 15
    //   163: astore 13
    //   165: aload 8
    //   167: astore 12
    //   169: aload 5
    //   171: astore 10
    //   173: aload 11
    //   175: astore 14
    //   177: aload_0
    //   178: ldc_w 320
    //   181: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   184: astore 4
    //   186: aload 11
    //   188: astore 6
    //   190: aload 9
    //   192: astore 7
    //   194: aload 15
    //   196: astore 13
    //   198: aload 8
    //   200: astore 12
    //   202: aload 4
    //   204: astore 10
    //   206: aload 11
    //   208: astore 14
    //   210: aload_0
    //   211: ldc_w 291
    //   214: invokevirtual 336	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   217: ifeq +66 -> 283
    //   220: aload 11
    //   222: astore 6
    //   224: aload 9
    //   226: astore 7
    //   228: aload 15
    //   230: astore 13
    //   232: aload 8
    //   234: astore 12
    //   236: aload 4
    //   238: astore 10
    //   240: aload 11
    //   242: astore 14
    //   244: aload_0
    //   245: ldc_w 291
    //   248: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   251: ifne +32 -> 283
    //   254: aload 9
    //   256: astore 7
    //   258: aload 15
    //   260: astore 13
    //   262: aload 8
    //   264: astore 12
    //   266: aload 4
    //   268: astore 10
    //   270: aload 11
    //   272: astore 14
    //   274: aload_0
    //   275: ldc_w 291
    //   278: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   281: astore 6
    //   283: aload 8
    //   285: astore 5
    //   287: aload 9
    //   289: astore 7
    //   291: aload 15
    //   293: astore 13
    //   295: aload 8
    //   297: astore 12
    //   299: aload 4
    //   301: astore 10
    //   303: aload 6
    //   305: astore 14
    //   307: aload_0
    //   308: ldc_w 308
    //   311: invokevirtual 336	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   314: ifeq +66 -> 380
    //   317: aload 8
    //   319: astore 5
    //   321: aload 9
    //   323: astore 7
    //   325: aload 15
    //   327: astore 13
    //   329: aload 8
    //   331: astore 12
    //   333: aload 4
    //   335: astore 10
    //   337: aload 6
    //   339: astore 14
    //   341: aload_0
    //   342: ldc_w 308
    //   345: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   348: ifne +32 -> 380
    //   351: aload 9
    //   353: astore 7
    //   355: aload 15
    //   357: astore 13
    //   359: aload 8
    //   361: astore 12
    //   363: aload 4
    //   365: astore 10
    //   367: aload 6
    //   369: astore 14
    //   371: aload_0
    //   372: ldc_w 308
    //   375: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   378: astore 5
    //   380: aload 9
    //   382: astore 8
    //   384: aload 9
    //   386: astore 7
    //   388: aload 15
    //   390: astore 13
    //   392: aload 5
    //   394: astore 12
    //   396: aload 4
    //   398: astore 10
    //   400: aload 6
    //   402: astore 14
    //   404: aload_0
    //   405: ldc_w 338
    //   408: invokevirtual 336	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   411: ifeq +66 -> 477
    //   414: aload 9
    //   416: astore 8
    //   418: aload 9
    //   420: astore 7
    //   422: aload 15
    //   424: astore 13
    //   426: aload 5
    //   428: astore 12
    //   430: aload 4
    //   432: astore 10
    //   434: aload 6
    //   436: astore 14
    //   438: aload_0
    //   439: ldc_w 338
    //   442: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   445: ifne +32 -> 477
    //   448: aload 9
    //   450: astore 7
    //   452: aload 15
    //   454: astore 13
    //   456: aload 5
    //   458: astore 12
    //   460: aload 4
    //   462: astore 10
    //   464: aload 6
    //   466: astore 14
    //   468: aload_0
    //   469: ldc_w 338
    //   472: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   475: astore 8
    //   477: aload 8
    //   479: astore 7
    //   481: aload 15
    //   483: astore 13
    //   485: aload 5
    //   487: astore 12
    //   489: aload 4
    //   491: astore 10
    //   493: aload 6
    //   495: astore 14
    //   497: aload 8
    //   499: astore 18
    //   501: aload 15
    //   503: astore 17
    //   505: aload 5
    //   507: astore 20
    //   509: aload 4
    //   511: astore 19
    //   513: aload 6
    //   515: astore 16
    //   517: aload_0
    //   518: ldc_w 340
    //   521: invokevirtual 336	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   524: ifeq +878 -> 1402
    //   527: aload 8
    //   529: astore 7
    //   531: aload 15
    //   533: astore 13
    //   535: aload 5
    //   537: astore 12
    //   539: aload 4
    //   541: astore 10
    //   543: aload 6
    //   545: astore 14
    //   547: aload 8
    //   549: astore 18
    //   551: aload 15
    //   553: astore 17
    //   555: aload 5
    //   557: astore 20
    //   559: aload 4
    //   561: astore 19
    //   563: aload 6
    //   565: astore 16
    //   567: aload_0
    //   568: ldc_w 340
    //   571: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   574: ifne +828 -> 1402
    //   577: aload 8
    //   579: astore 7
    //   581: aload 15
    //   583: astore 13
    //   585: aload 5
    //   587: astore 12
    //   589: aload 4
    //   591: astore 10
    //   593: aload 6
    //   595: astore 14
    //   597: aload_0
    //   598: ldc_w 340
    //   601: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   604: astore 9
    //   606: aconst_null
    //   607: astore 10
    //   609: aload 5
    //   611: astore 7
    //   613: aload 10
    //   615: astore 5
    //   617: aload_0
    //   618: ldc_w 325
    //   621: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   624: istore_3
    //   625: iload_3
    //   626: ifeq +638 -> 1264
    //   629: iconst_1
    //   630: istore_2
    //   631: aload 4
    //   633: astore_0
    //   634: aload 5
    //   636: astore 4
    //   638: aload 7
    //   640: astore 5
    //   642: new 342	apn
    //   645: dup
    //   646: aload_0
    //   647: aload 5
    //   649: aload 8
    //   651: aload 9
    //   653: iload_1
    //   654: iload_2
    //   655: aload 6
    //   657: invokespecial 345	apn:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;)V
    //   660: astore_0
    //   661: aload_0
    //   662: aload 4
    //   664: putfield 347	apn:h	Ljava/lang/String;
    //   667: aload_0
    //   668: areturn
    //   669: aload 5
    //   671: astore 4
    //   673: aload 9
    //   675: astore 7
    //   677: aload 15
    //   679: astore 13
    //   681: aload 8
    //   683: astore 12
    //   685: aload 5
    //   687: astore 10
    //   689: aload 11
    //   691: astore 14
    //   693: aload_0
    //   694: ldc_w 320
    //   697: invokevirtual 336	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   700: ifeq +66 -> 766
    //   703: aload 5
    //   705: astore 4
    //   707: aload 9
    //   709: astore 7
    //   711: aload 15
    //   713: astore 13
    //   715: aload 8
    //   717: astore 12
    //   719: aload 5
    //   721: astore 10
    //   723: aload 11
    //   725: astore 14
    //   727: aload_0
    //   728: ldc_w 320
    //   731: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   734: ifne +32 -> 766
    //   737: aload 9
    //   739: astore 7
    //   741: aload 15
    //   743: astore 13
    //   745: aload 8
    //   747: astore 12
    //   749: aload 5
    //   751: astore 10
    //   753: aload 11
    //   755: astore 14
    //   757: aload_0
    //   758: ldc_w 320
    //   761: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   764: astore 4
    //   766: aload 8
    //   768: astore 5
    //   770: aload 9
    //   772: astore 7
    //   774: aload 15
    //   776: astore 13
    //   778: aload 8
    //   780: astore 12
    //   782: aload 4
    //   784: astore 10
    //   786: aload 11
    //   788: astore 14
    //   790: aload_0
    //   791: ldc_w 349
    //   794: invokevirtual 336	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   797: ifeq +66 -> 863
    //   800: aload 8
    //   802: astore 5
    //   804: aload 9
    //   806: astore 7
    //   808: aload 15
    //   810: astore 13
    //   812: aload 8
    //   814: astore 12
    //   816: aload 4
    //   818: astore 10
    //   820: aload 11
    //   822: astore 14
    //   824: aload_0
    //   825: ldc_w 349
    //   828: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   831: ifne +32 -> 863
    //   834: aload 9
    //   836: astore 7
    //   838: aload 15
    //   840: astore 13
    //   842: aload 8
    //   844: astore 12
    //   846: aload 4
    //   848: astore 10
    //   850: aload 11
    //   852: astore 14
    //   854: aload_0
    //   855: ldc_w 349
    //   858: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   861: astore 5
    //   863: aload 9
    //   865: astore 8
    //   867: aload 9
    //   869: astore 7
    //   871: aload 15
    //   873: astore 13
    //   875: aload 5
    //   877: astore 12
    //   879: aload 4
    //   881: astore 10
    //   883: aload 11
    //   885: astore 14
    //   887: aload_0
    //   888: ldc_w 351
    //   891: invokevirtual 336	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   894: ifeq +66 -> 960
    //   897: aload 9
    //   899: astore 8
    //   901: aload 9
    //   903: astore 7
    //   905: aload 15
    //   907: astore 13
    //   909: aload 5
    //   911: astore 12
    //   913: aload 4
    //   915: astore 10
    //   917: aload 11
    //   919: astore 14
    //   921: aload_0
    //   922: ldc_w 351
    //   925: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   928: ifne +32 -> 960
    //   931: aload 9
    //   933: astore 7
    //   935: aload 15
    //   937: astore 13
    //   939: aload 5
    //   941: astore 12
    //   943: aload 4
    //   945: astore 10
    //   947: aload 11
    //   949: astore 14
    //   951: aload_0
    //   952: ldc_w 351
    //   955: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   958: astore 8
    //   960: aload 15
    //   962: astore 9
    //   964: aload 8
    //   966: astore 7
    //   968: aload 15
    //   970: astore 13
    //   972: aload 5
    //   974: astore 12
    //   976: aload 4
    //   978: astore 10
    //   980: aload 11
    //   982: astore 14
    //   984: aload_0
    //   985: ldc_w 352
    //   988: invokevirtual 336	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   991: ifeq +66 -> 1057
    //   994: aload 15
    //   996: astore 9
    //   998: aload 8
    //   1000: astore 7
    //   1002: aload 15
    //   1004: astore 13
    //   1006: aload 5
    //   1008: astore 12
    //   1010: aload 4
    //   1012: astore 10
    //   1014: aload 11
    //   1016: astore 14
    //   1018: aload_0
    //   1019: ldc_w 352
    //   1022: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1025: ifne +32 -> 1057
    //   1028: aload 8
    //   1030: astore 7
    //   1032: aload 15
    //   1034: astore 13
    //   1036: aload 5
    //   1038: astore 12
    //   1040: aload 4
    //   1042: astore 10
    //   1044: aload 11
    //   1046: astore 14
    //   1048: aload_0
    //   1049: ldc_w 352
    //   1052: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1055: astore 9
    //   1057: aload 8
    //   1059: astore 7
    //   1061: aload 9
    //   1063: astore 13
    //   1065: aload 5
    //   1067: astore 12
    //   1069: aload 4
    //   1071: astore 10
    //   1073: aload 11
    //   1075: astore 14
    //   1077: aload 8
    //   1079: astore 18
    //   1081: aload 9
    //   1083: astore 17
    //   1085: aload 5
    //   1087: astore 20
    //   1089: aload 4
    //   1091: astore 19
    //   1093: aload 11
    //   1095: astore 16
    //   1097: aload_0
    //   1098: ldc_w 354
    //   1101: invokevirtual 336	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   1104: ifeq +298 -> 1402
    //   1107: aload 8
    //   1109: astore 7
    //   1111: aload 9
    //   1113: astore 13
    //   1115: aload 5
    //   1117: astore 12
    //   1119: aload 4
    //   1121: astore 10
    //   1123: aload 11
    //   1125: astore 14
    //   1127: aload 8
    //   1129: astore 18
    //   1131: aload 9
    //   1133: astore 17
    //   1135: aload 5
    //   1137: astore 20
    //   1139: aload 4
    //   1141: astore 19
    //   1143: aload 11
    //   1145: astore 16
    //   1147: aload_0
    //   1148: ldc_w 354
    //   1151: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1154: ifne +248 -> 1402
    //   1157: aload 8
    //   1159: astore 7
    //   1161: aload 9
    //   1163: astore 13
    //   1165: aload 5
    //   1167: astore 12
    //   1169: aload 4
    //   1171: astore 10
    //   1173: aload 11
    //   1175: astore 14
    //   1177: aload 8
    //   1179: astore 18
    //   1181: aload 9
    //   1183: astore 17
    //   1185: aload 5
    //   1187: astore 20
    //   1189: aload 4
    //   1191: astore 19
    //   1193: aload 11
    //   1195: astore 16
    //   1197: aload_0
    //   1198: ldc_w 354
    //   1201: invokevirtual 358	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   1204: invokevirtual 361	org/json/JSONArray:length	()I
    //   1207: ifle +195 -> 1402
    //   1210: aload 8
    //   1212: astore 7
    //   1214: aload 9
    //   1216: astore 13
    //   1218: aload 5
    //   1220: astore 12
    //   1222: aload 4
    //   1224: astore 10
    //   1226: aload 11
    //   1228: astore 14
    //   1230: aload_0
    //   1231: ldc_w 354
    //   1234: invokevirtual 358	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   1237: iconst_0
    //   1238: invokevirtual 365	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   1241: ldc_w 322
    //   1244: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1247: astore 6
    //   1249: aload 5
    //   1251: astore 7
    //   1253: aload 6
    //   1255: astore 5
    //   1257: aload 11
    //   1259: astore 6
    //   1261: goto -644 -> 617
    //   1264: aload_0
    //   1265: ldc_w 325
    //   1268: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1271: ldc_w 367
    //   1274: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1277: ifeq +23 -> 1300
    //   1280: iconst_1
    //   1281: istore_2
    //   1282: aload 5
    //   1284: astore 10
    //   1286: aload 4
    //   1288: astore_0
    //   1289: aload 7
    //   1291: astore 5
    //   1293: aload 10
    //   1295: astore 4
    //   1297: goto -655 -> 642
    //   1300: aload_0
    //   1301: ldc_w 325
    //   1304: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1307: ldc_w 327
    //   1310: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1313: istore_3
    //   1314: iload_3
    //   1315: ifeq +5 -> 1320
    //   1318: iconst_3
    //   1319: istore_2
    //   1320: aload 5
    //   1322: astore 10
    //   1324: aload 4
    //   1326: astore_0
    //   1327: aload 7
    //   1329: astore 5
    //   1331: aload 10
    //   1333: astore 4
    //   1335: goto -693 -> 642
    //   1338: astore 8
    //   1340: aconst_null
    //   1341: astore_0
    //   1342: aload 12
    //   1344: astore 5
    //   1346: aload 10
    //   1348: astore 4
    //   1350: aload 14
    //   1352: astore 6
    //   1354: aload 13
    //   1356: astore 9
    //   1358: aload 8
    //   1360: invokevirtual 370	org/json/JSONException:printStackTrace	()V
    //   1363: iconst_1
    //   1364: istore_2
    //   1365: aload_0
    //   1366: astore 10
    //   1368: aload 4
    //   1370: astore_0
    //   1371: aload 7
    //   1373: astore 8
    //   1375: aload 10
    //   1377: astore 4
    //   1379: goto -737 -> 642
    //   1382: astore 10
    //   1384: aload 5
    //   1386: astore_0
    //   1387: aload 7
    //   1389: astore 5
    //   1391: aload 8
    //   1393: astore 7
    //   1395: aload 10
    //   1397: astore 8
    //   1399: goto -41 -> 1358
    //   1402: aconst_null
    //   1403: astore 5
    //   1405: aload 20
    //   1407: astore 7
    //   1409: aload 19
    //   1411: astore 4
    //   1413: aload 18
    //   1415: astore 8
    //   1417: aload 17
    //   1419: astore 9
    //   1421: aload 16
    //   1423: astore 6
    //   1425: goto -808 -> 617
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1428	0	paramJSONObject	JSONObject
    //   0	1428	1	paramInt	int
    //   1	1364	2	i1	int
    //   624	691	3	bool	boolean
    //   91	1321	4	localObject1	Object
    //   4	1400	5	localObject2	Object
    //   188	1236	6	localObject3	Object
    //   25	1383	7	localObject4	Object
    //   8	1203	8	localObject5	Object
    //   1338	21	8	localJSONException1	JSONException
    //   1373	43	8	localObject6	Object
    //   13	1407	9	localObject7	Object
    //   37	1339	10	localObject8	Object
    //   1382	14	10	localJSONException2	JSONException
    //   21	1237	11	str1	String
    //   33	1310	12	localObject9	Object
    //   29	1326	13	localObject10	Object
    //   41	1310	14	localObject11	Object
    //   17	1016	15	str2	String
    //   515	907	16	localObject12	Object
    //   503	915	17	localObject13	Object
    //   499	915	18	localObject14	Object
    //   511	899	19	localObject15	Object
    //   507	899	20	localObject16	Object
    // Exception table:
    //   from	to	target	type
    //   43	53	1338	org/json/JSONException
    //   73	89	1338	org/json/JSONException
    //   113	123	1338	org/json/JSONException
    //   147	157	1338	org/json/JSONException
    //   177	186	1338	org/json/JSONException
    //   210	220	1338	org/json/JSONException
    //   244	254	1338	org/json/JSONException
    //   274	283	1338	org/json/JSONException
    //   307	317	1338	org/json/JSONException
    //   341	351	1338	org/json/JSONException
    //   371	380	1338	org/json/JSONException
    //   404	414	1338	org/json/JSONException
    //   438	448	1338	org/json/JSONException
    //   468	477	1338	org/json/JSONException
    //   517	527	1338	org/json/JSONException
    //   567	577	1338	org/json/JSONException
    //   597	606	1338	org/json/JSONException
    //   693	703	1338	org/json/JSONException
    //   727	737	1338	org/json/JSONException
    //   757	766	1338	org/json/JSONException
    //   790	800	1338	org/json/JSONException
    //   824	834	1338	org/json/JSONException
    //   854	863	1338	org/json/JSONException
    //   887	897	1338	org/json/JSONException
    //   921	931	1338	org/json/JSONException
    //   951	960	1338	org/json/JSONException
    //   984	994	1338	org/json/JSONException
    //   1018	1028	1338	org/json/JSONException
    //   1048	1057	1338	org/json/JSONException
    //   1097	1107	1338	org/json/JSONException
    //   1147	1157	1338	org/json/JSONException
    //   1197	1210	1338	org/json/JSONException
    //   1230	1249	1338	org/json/JSONException
    //   617	625	1382	org/json/JSONException
    //   1264	1280	1382	org/json/JSONException
    //   1300	1314	1382	org/json/JSONException
  }
  
  public static String a(String paramString)
  {
    int i1;
    for (;;)
    {
      try
      {
        i1 = Integer.valueOf(paramString).intValue();
        if (i1 < 3600) {
          break label145;
        }
        if (i1 % 3600 / 60 < 10)
        {
          paramString = i1 / 3600 + ":0" + i1 % 3600 / 60;
          if (i1 % 60 >= 10) {
            break;
          }
          return paramString + ":0" + i1 % 60;
        }
      }
      catch (Exception paramString)
      {
        return "0:00";
      }
      paramString = i1 / 3600 + ":" + i1 % 3600 / 60;
      continue;
      label145:
      if (i1 / 60 < 10) {
        paramString = "0" + i1 / 60;
      } else {
        paramString = i1 / 60;
      }
    }
    return paramString + ":" + i1 % 60;
  }
  
  public static ArrayList<apn> a(String paramString, SongFragment paramSongFragment)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString == null) {
      return localArrayList;
    }
    for (;;)
    {
      int i1;
      try
      {
        paramString = new JSONObject(paramString);
        if (paramString.isNull("getVector")) {
          break;
        }
        paramString = paramString.getJSONObject("getVector");
        if (paramString.isNull("items")) {
          break;
        }
        JSONArray localJSONArray = paramString.getJSONArray("items");
        i1 = localJSONArray.length() - 1;
        if (i1 >= 0)
        {
          if ((!localJSONArray.isNull(i1)) && (!localJSONArray.getJSONObject(i1).isNull("type")) && (localJSONArray.getJSONObject(i1).getString("type").equals("music"))) {
            localArrayList.add(a(localJSONArray.getJSONObject(i1), i1));
          }
        }
        else
        {
          if ((paramString.isNull("subsCnt")) || (paramSongFragment == null)) {
            break;
          }
          paramSongFragment.F.f = paramString.getString("subsCnt");
          return localArrayList;
        }
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
        return localArrayList;
      }
      i1 -= 1;
    }
  }
  
  public static void a()
  {
    if ((f == null) || (g == null)) {
      return;
    }
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("sItem", c.a());
      if (d != null) {
        localJSONObject.put("pId", d);
      }
      if (e != null) {
        localJSONObject.put("pName", e);
      }
      localJSONObject.put("type", "continue_play");
    }
    catch (JSONException localJSONException)
    {
      apq localApq;
      for (;;) {}
    }
    localApq = g;
    localApq.a = localJSONObject;
    localApq.c();
  }
  
  public static void a(int paramInt)
  {
    j = paramInt;
  }
  
  public static void a(final Context paramContext)
  {
    arx.a(arx.b(paramContext), new apj(paramContext)
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
        if (!arx.a(paramContext))
        {
          paramAnonymousArrayOfXh = new Intent();
          paramAnonymousArrayOfByte = new Bundle();
          paramAnonymousArrayOfByte.putBoolean("isLogIn", true);
          paramAnonymousArrayOfXh.putExtras(paramAnonymousArrayOfByte);
          paramAnonymousArrayOfXh.setClass(paramContext, LoginBridge.class);
          paramContext.startActivity(paramAnonymousArrayOfXh);
          ((Activity)paramContext).finish();
          return;
        }
        if (MixerBoxUtils.a > 0)
        {
          MixerBoxUtils.a = 0;
          MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131230836) + " " + paramContext.getResources().getString(2131231011), 1, new boolean[0]);
          return;
        }
        MixerBoxUtils.a += 1;
        MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131230965), 1, new boolean[0]);
        MixerBoxUtils.a(paramContext);
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        if ((paramContext instanceof MainPage)) {
          MixerBoxUtils.a("action:logged_in", null);
        }
        paramAnonymousArrayOfXh = new JSONObject();
        try
        {
          paramAnonymousArrayOfByte = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject("getMyInfo");
          paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
          if (!paramAnonymousArrayOfByte.isNull("fb"))
          {
            paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
            localObject = paramAnonymousArrayOfByte.getJSONObject("fb");
            paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
            if (!((JSONObject)localObject).isNull("id"))
            {
              paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
              asa.a(paramContext, ((JSONObject)localObject).getString("id"));
            }
          }
          Object localObject = "";
          String str = "";
          paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
          if (!paramAnonymousArrayOfByte.isNull("_id"))
          {
            paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
            localObject = paramAnonymousArrayOfByte.getString("_id");
          }
          paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
          if (!paramAnonymousArrayOfByte.isNull("name"))
          {
            paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
            str = paramAnonymousArrayOfByte.getString("name");
          }
          paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
          asa.a(paramContext, (String)localObject, str);
          paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
        }
        catch (JSONException paramAnonymousArrayOfByte)
        {
          for (;;) {}
        }
        asa.w(paramContext, paramAnonymousArrayOfXh.toString());
        paramAnonymousArrayOfXh = new Intent();
        if (MixerBoxUtils.b == null) {
          MixerBoxUtils.b = new Bundle();
        }
        MixerBoxUtils.b.putBoolean("isLogIn", true);
        paramAnonymousArrayOfXh.putExtras(MixerBoxUtils.b);
        paramAnonymousArrayOfXh.setClass(paramContext, LoginBridge.class);
        paramContext.startActivity(paramAnonymousArrayOfXh);
        ((Activity)paramContext).finish();
      }
    });
  }
  
  public static void a(Context paramContext, int paramInt1, ImageView paramImageView, int paramInt2)
  {
    paramInt2 = b(paramInt2);
    try
    {
      wo.a(paramContext).a(paramInt1).b(paramInt2).a(paramImageView, null);
      return;
    }
    catch (Exception localException)
    {
      wo.a(paramContext).a(2130837585).a(2130837585).b(2130837585).a(paramImageView, null);
    }
  }
  
  public static void a(Context paramContext, int paramInt1, apn paramApn, String paramString, int paramInt2)
  {
    Object localObject2;
    if (((paramString == null) || (paramString.length() == 0)) && (((Fragment)((MainPage)paramContext).m.lastElement() instanceof SongFragment)))
    {
      localObject2 = ((SongFragment)((MainPage)paramContext).m.lastElement()).F;
      localObject1 = localObject2;
      if (localObject2 != null) {
        paramString = ((apm)localObject2).a;
      }
    }
    for (Object localObject1 = localObject2;; localObject1 = null)
    {
      switch (paramInt1)
      {
      }
      for (;;)
      {
        return;
        localObject1 = asa.z(paramContext);
        localObject2 = asa.y(paramContext);
        if (((MainPage)paramContext).l((String)localObject1))
        {
          if (((MainPage)paramContext).am)
          {
            if ((paramApn != null) && (paramString != null) && (paramString.equals("PLAYLISTHISTORYID")))
            {
              a(paramContext, (String)localObject1, new apn("", paramApn.b, paramApn.c, paramApn.d, paramApn.e, paramApn.f, paramApn.g), true, (String)localObject2);
              ((MainPage)paramContext).b((String)localObject1, paramApn.d);
              return;
            }
            a(paramContext, (String)localObject1, paramApn, true, (String)localObject2);
            if ((paramApn != null) && (paramApn.d != null)) {
              ((MainPage)paramContext).b((String)localObject1, paramApn.d);
            }
          }
          else
          {
            ((MainPage)paramContext).b("local", paramApn.d);
            a("action:music_add_local", null);
            ((MainPage)paramContext).an.a((String)localObject1, paramApn.b, paramApn.c, paramApn.d, paramApn.f, paramApn.g);
            a(paramContext, paramContext.getResources().getString(2131230922), 1, new boolean[0]);
            ((MainPage)paramContext).s();
          }
        }
        else
        {
          aqs.a(paramContext);
          return;
          localObject1 = paramApn;
          if (((MainPage)paramContext).am)
          {
            localObject1 = paramApn;
            if (paramApn != null)
            {
              localObject1 = paramApn;
              if (paramString != null)
              {
                localObject1 = paramApn;
                if (paramString.equals("PLAYLISTHISTORYID")) {
                  localObject1 = new apn("", paramApn.b, paramApn.c, paramApn.d, paramApn.e, paramApn.f, paramApn.g);
                }
              }
            }
          }
          new aqs(paramContext, (apn)localObject1).show().getWindow().setSoftInputMode(16);
          return;
          localObject1 = new HashMap();
          ((Map)localObject1).put("f", paramApn.d);
          ((Map)localObject1).put("id", paramApn.a);
          ((Map)localObject1).put("playlist", paramString);
          ((Map)localObject1).put("t", "yt");
          a("action:music_share", (Map)localObject1);
          if (paramString.equals("PLAYLISTHISTORYID")) {
            paramString = "http://www.mixerbox.com/music/0/" + paramApn.d;
          }
          for (;;)
          {
            localObject1 = paramContext.getResources().getString(2131230985) + paramContext.getResources().getString(2131230986);
            localObject2 = paramContext.getResources().getString(2131230987);
            Intent localIntent = new Intent("android.intent.action.SEND");
            localIntent.setType("text/plain");
            localIntent.putExtra("android.intent.extra.SUBJECT", (String)localObject1 + paramApn.b + (String)localObject2);
            localIntent.putExtra("android.intent.extra.TEXT", (String)localObject1 + paramApn.b + (String)localObject2 + paramString);
            paramContext.startActivity(Intent.createChooser(localIntent, null));
            return;
            if ((!((MainPage)paramContext).am) && (((MainPage)paramContext).i(((SongFragment)((MainPage)paramContext).m.lastElement()).F.a))) {
              paramString = "http://www.mixerbox.com/music/0/" + paramApn.d;
            } else {
              paramString = "http://www.mixerbox.com/music/" + paramString + "/" + paramApn.a;
            }
          }
          paramString = (String)localObject1;
          if (localObject1 == null)
          {
            paramString = (String)localObject1;
            if ((((MainPage)paramContext).m.lastElement() instanceof SongFragment)) {
              paramString = ((SongFragment)((MainPage)paramContext).m.lastElement()).F;
            }
          }
          new aqy(paramContext, 2, paramString, paramApn, paramInt2).show();
          return;
          f(paramContext, "FastBrowseByMenu");
          ((MainPage)paramContext).i();
          if ((((MainPage)paramContext).m.lastElement() instanceof SongFragment))
          {
            localObject1 = (SongFragment)((MainPage)paramContext).m.lastElement();
            paramApn = ((SongFragment)localObject1).b;
            paramString = ((SongFragment)localObject1).b.getAdapter();
            if ((((MainPage)paramContext).aJ != null) && (((MainPage)paramContext).aJ.b.av) && (asa.ar(paramContext))) {
              ((MainPage)paramContext).aG = true;
            }
            paramContext = ((SongFragment)localObject1).F;
            if ((j < paramString.getCount()) && (j >= 0))
            {
              paramApn.performItemClick(paramString.getView(j, null, null), j, paramString.getItemId(j));
              return;
              if ((((MainPage)paramContext).m.lastElement() instanceof SongFragment))
              {
                ((SongFragment)((MainPage)paramContext).m.lastElement()).d();
                return;
                if ((((MainPage)paramContext).m.lastElement() instanceof SongFragment))
                {
                  paramContext = (SongFragment)((MainPage)paramContext).m.lastElement();
                  paramContext.b("browse");
                  ((MainPage)paramContext.getActivity()).i();
                  paramApn = paramContext.b.getAdapter();
                  if (asa.ap(paramContext.getActivity())) {
                    ((MainPage)paramContext.getActivity()).aF = true;
                  }
                  if ((paramApn instanceof WrapperListAdapter)) {}
                  for (paramInt1 = paramContext.b.getHeaderViewsCount(); (paramContext.b != null) && (paramApn != null) && (paramApn.getCount() > 0); paramInt1 = 0)
                  {
                    paramContext.b.performItemClick(paramApn.getView(paramInt1, null, null), paramInt1, paramApn.getItemId(paramInt1));
                    return;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public static void a(Context paramContext, Bundle paramBundle)
  {
    if (paramBundle == null) {}
    for (b = new Bundle();; b = paramBundle)
    {
      a(paramContext);
      return;
    }
  }
  
  public static void a(final Context paramContext, View paramView, final apn paramApn, final String paramString, final int paramInt)
  {
    d();
    i = new ListPopupWindow(paramContext);
    ArrayList localArrayList = new ArrayList();
    SimpleAdapter localSimpleAdapter = new SimpleAdapter(paramContext, localArrayList, 2130903162, new String[] { "title", "icon" }, new int[] { 2131624421, 2131624420 });
    if (paramInt >= 0) {
      if (asa.z(paramContext).length() > 0)
      {
        localObject = asa.z(paramContext);
        str = asa.y(paramContext);
        if (((MainPage)paramContext).i((String)localObject)) {
          a(localArrayList, String.format(paramContext.getResources().getString(2131230911), new Object[] { str }), 0, 0);
        }
      }
      else
      {
        a(localArrayList, paramContext.getResources().getString(2131230912), 2130837764, 1);
        if ((((MainPage)paramContext).m.lastElement() instanceof SongFragment)) {
          a(localArrayList, paramContext.getResources().getString(2131230877), 2130838000, 18);
        }
        if ((((MainPage)paramContext).ao != 8) && ((((MainPage)paramContext).m.lastElement() instanceof SongFragment)) && (paramApn.f != 3) && (!paramString.equals("PLAYLISTHISTORYID"))) {
          a(localArrayList, paramContext.getResources().getString(2131230984), 2130837984, 2);
        }
        if (((((MainPage)paramContext).i(paramString)) && (((MainPage)paramContext).ao != 8) && (((MainPage)paramContext).ao != 9)) || (paramString.equals("PLAYLISTHISTORYID"))) {
          a(localArrayList, paramContext.getResources().getString(2131230857), 2130837785, 3);
        }
      }
    }
    while (!(((MainPage)paramContext).m.lastElement() instanceof SongFragment)) {
      for (;;)
      {
        String str;
        i.i = paramView;
        i.a(localSimpleAdapter);
        i.f();
        i.d = a(paramContext, 200);
        i.j = new AdapterView.OnItemClickListener()
        {
          public final void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            try
            {
              if ((this.a != null) && (this.a.getCount() > paramAnonymousInt))
              {
                paramAnonymousInt = Integer.parseInt(((HashMap)this.a.getItem(paramAnonymousInt)).get("id").toString());
                MixerBoxUtils.a(paramContext, paramAnonymousInt, paramApn, paramString, paramInt);
                MixerBoxUtils.i.c();
              }
              return;
            }
            catch (Exception paramAnonymousAdapterView) {}
          }
        };
        i.b();
        return;
        asa.b(paramContext, "", "");
      }
    }
    Object localObject = (SongFragment)((MainPage)paramContext).m.lastElement();
    boolean bool = SongFragment.a(paramString);
    for (;;)
    {
      try
      {
        i1 = Integer.parseInt(((SongFragment)localObject).F.e);
        if (i1 > 0) {
          continue;
        }
        i1 = 1;
      }
      catch (Exception localException)
      {
        int i1 = 1;
        continue;
      }
      if (i1 == 0) {
        a(localArrayList, paramContext.getResources().getString(2131230877), 2130838000, 19);
      }
      if ((bool) || (i1 != 0)) {
        break;
      }
      a(localArrayList, paramContext.getResources().getString(2131230984), 2130837984, 10);
      break;
      i1 = 0;
    }
  }
  
  public static void a(final Context paramContext, final arn paramArn, final String paramString, List<Map<String, String>> paramList)
  {
    arx.a(arx.a(paramString, paramList), new apj(paramContext)
    {
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        int i = 0;
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        paramArn.a = new ArrayList();
        if (paramAnonymousArrayOfByte != null) {
          try
          {
            paramAnonymousArrayOfXh = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject(paramString).getJSONArray("items");
            paramAnonymousInt = i;
            while (paramAnonymousInt < paramAnonymousArrayOfXh.length())
            {
              paramAnonymousArrayOfByte = paramAnonymousArrayOfXh.getJSONObject(paramAnonymousInt);
              MixerBoxUtils.a(paramArn.a, paramAnonymousArrayOfByte, paramContext, paramAnonymousInt, paramAnonymousArrayOfXh.length() - paramAnonymousInt - 1);
              paramAnonymousInt += 1;
            }
            paramArn.a();
          }
          catch (JSONException paramAnonymousArrayOfXh)
          {
            paramAnonymousArrayOfXh.printStackTrace();
          }
        }
        for (;;)
        {
          paramArn.j = true;
          return;
          MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231010), 1, new boolean[0]);
        }
      }
    });
  }
  
  public static void a(final Context paramContext, final String paramString)
  {
    arx.a(arx.i(paramString), new apj(paramContext)
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
        MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231011), 1, new boolean[0]);
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131230944), 1, new boolean[0]);
        MixerBoxUtils.a("action:playlist_create", null);
        paramAnonymousArrayOfXh = "";
        if (paramAnonymousArrayOfByte != null) {
          paramAnonymousArrayOfXh = new String(paramAnonymousArrayOfByte);
        }
        paramAnonymousArrayOfByte = "";
        try
        {
          paramAnonymousArrayOfXh = new JSONObject(paramAnonymousArrayOfXh).getString("mChangePlaylist");
          ((MainPage)paramContext).e(paramAnonymousArrayOfXh);
          ((MainPage)paramContext).E.clear();
          paramAnonymousArrayOfByte = new apm(paramAnonymousArrayOfXh, paramString, "", "", "0", "0", "", false);
          ((MainPage)paramContext).M.add(paramAnonymousArrayOfByte);
          SongFragment localSongFragment = ((MainPage)paramContext).b(paramAnonymousArrayOfXh, false);
          localSongFragment.F = paramAnonymousArrayOfByte;
          MixerBoxUtils.a(paramContext, paramAnonymousArrayOfXh, -1, localSongFragment, 0);
          MixerBoxUtils.a(paramContext, false);
          if (((MainPage)paramContext).t.d != null) {
            ((MainPage)paramContext).t.d.post(new Runnable()
            {
              public final void run()
              {
                ((MainPage)MixerBoxUtils.8.this.a).t.d.setRefreshing(false);
              }
            });
          }
          asc.a(paramContext, "createPlaylist");
          return;
        }
        catch (JSONException paramAnonymousArrayOfXh)
        {
          for (;;)
          {
            paramAnonymousArrayOfXh.printStackTrace();
            paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
          }
        }
      }
    });
  }
  
  public static void a(final Context paramContext, String paramString, final int paramInt1, final SongFragment paramSongFragment, final int paramInt2)
  {
    arx.a(arx.g(paramString), new apj(paramContext)
    {
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        MixerBoxUtils.b localB = new MixerBoxUtils.b(paramContext, this.b, this.c, paramInt1, paramSongFragment, paramInt2);
        paramAnonymousArrayOfXh = "";
        if (paramAnonymousArrayOfByte != null) {
          paramAnonymousArrayOfXh = new String(paramAnonymousArrayOfByte);
        }
        localB.execute(new String[] { paramAnonymousArrayOfXh });
      }
    });
  }
  
  public static void a(Context paramContext, String paramString, int paramInt, boolean... paramVarArgs)
  {
    if (m != null) {
      m.cancel();
    }
    Toast localToast = Toast.makeText(paramContext, paramString, paramInt);
    m = localToast;
    localToast.setGravity(16, 0, 0);
    if ((paramVarArgs.length > 0) && (paramVarArgs[0] == 0)) {
      m = Toast.makeText(paramContext, paramString, paramInt);
    }
    m.show();
  }
  
  public static void a(Context paramContext, String paramString, ImageView paramImageView, int paramInt)
  {
    paramInt = b(paramInt);
    if (paramString != null) {}
    try
    {
      if (paramString.equals(""))
      {
        wo.a(paramContext).a(paramInt).a(2130837590).b(paramInt).a(paramImageView, null);
        return;
      }
      wo.a(paramContext).a(paramString).a(2130837590).b(paramInt).a(paramImageView, null);
      return;
    }
    catch (Exception paramString)
    {
      wo.a(paramContext).a(2130837585).a(2130837585).b(2130837585).a(paramImageView, null);
    }
  }
  
  public static void a(Context paramContext, String paramString, ImageView paramImageView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt4 = b(paramInt4);
    Object localObject = paramString;
    if (paramString != null) {
      localObject = paramString;
    }
    try
    {
      if (paramString.equals("")) {
        localObject = null;
      }
      paramString = new tk();
      paramString.e = ColorStateList.valueOf(paramInt2);
      paramString.d = TypedValue.applyDimension(1, paramInt3, paramString.a);
      float f1 = TypedValue.applyDimension(1, paramInt1, paramString.a);
      paramString.b[0] = f1;
      paramString.b[1] = f1;
      paramString.b[2] = f1;
      paramString.b[3] = f1;
      paramString.c = false;
      paramString = new tk.1(paramString);
      localObject = wo.a(paramContext).a((String)localObject).b(paramInt4);
      localA = ((wt)localObject).b;
      if (localA.g) {
        throw new IllegalStateException("Center crop can not be used after calling centerInside");
      }
    }
    catch (Exception paramString)
    {
      wo.a(paramContext).a(2130837585).b(2130837585).a(paramImageView, null);
      return;
    }
    localA.f = true;
    ((wt)localObject).c = true;
    ws.a localA = ((wt)localObject).b;
    if (paramString.a() == null) {
      throw new IllegalArgumentException("Transformation key must not be null.");
    }
    if (localA.m == null) {
      localA.m = new ArrayList(2);
    }
    localA.m.add(paramString);
    ((wt)localObject).a(paramImageView, null);
  }
  
  public static void a(final Context paramContext, final String paramString, final apn paramApn)
  {
    arx.a(arx.i(paramString), new apj(paramContext)
    {
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        MixerBoxUtils.a("action:playlist_create", null);
        paramAnonymousArrayOfXh = "";
        if (paramAnonymousArrayOfByte != null) {
          paramAnonymousArrayOfXh = new String(paramAnonymousArrayOfByte);
        }
        try
        {
          paramAnonymousArrayOfXh = new JSONObject(paramAnonymousArrayOfXh).getString("mChangePlaylist");
          ((MainPage)paramContext).e(paramAnonymousArrayOfXh);
          ((MainPage)paramContext).b(paramAnonymousArrayOfXh, paramApn.d);
          MixerBoxUtils.a(paramContext, paramAnonymousArrayOfXh, paramApn, true, paramString);
          asc.a(paramContext, "createPlaylist");
          return;
        }
        catch (JSONException paramAnonymousArrayOfXh)
        {
          for (;;)
          {
            paramAnonymousArrayOfXh.printStackTrace();
            paramAnonymousArrayOfXh = "";
          }
        }
      }
    });
  }
  
  public static void a(final Context paramContext, final String paramString, final apn paramApn, int paramInt)
  {
    arx.a(arx.a(paramString, paramApn, paramInt), new apj(paramContext)
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
        MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231011), 1, new boolean[0]);
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        boolean bool2 = true;
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        if (paramAnonymousArrayOfByte != null) {}
        for (;;)
        {
          try
          {
            paramAnonymousArrayOfXh = new JSONObject(new String(paramAnonymousArrayOfByte));
            boolean bool1 = bool2;
            if (!paramAnonymousArrayOfXh.isNull("mChangePlaylist"))
            {
              bool1 = bool2;
              if (!paramAnonymousArrayOfXh.getJSONObject("mChangePlaylist").isNull("status")) {
                bool1 = paramAnonymousArrayOfXh.getJSONObject("mChangePlaylist").getBoolean("status");
              }
            }
            if (!bool1) {
              continue;
            }
            if (((((MainPage)paramContext).m.lastElement() instanceof SongFragment)) && (((SongFragment)((MainPage)paramContext).m.lastElement()).F.a.equals(paramString))) {
              ((SongFragment)((MainPage)paramContext).m.lastElement()).a();
            }
            MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131230923), 1, new boolean[0]);
            paramAnonymousArrayOfXh = new HashMap();
            paramAnonymousArrayOfXh.put("f", paramApn.d);
            paramAnonymousArrayOfXh.put("id", paramApn.a);
            paramAnonymousArrayOfXh.put("playlist", paramString);
            paramAnonymousArrayOfXh.put("t", "yt");
            MixerBoxUtils.a("action:music_del", paramAnonymousArrayOfXh);
          }
          catch (JSONException paramAnonymousArrayOfXh)
          {
            paramAnonymousArrayOfXh.printStackTrace();
            continue;
          }
          MixerBoxUtils.a(paramContext, false);
          return;
          MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231011), 1, new boolean[0]);
        }
      }
    });
  }
  
  public static void a(final Context paramContext, final String paramString1, final apn paramApn, final boolean paramBoolean, final String paramString2)
  {
    arx.a(arx.a(paramString1, paramApn), new apj(paramContext)
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
        if (paramAnonymousArrayOfByte != null) {
          new String(paramAnonymousArrayOfByte);
        }
        if (paramBoolean) {
          MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231011), 1, new boolean[0]);
        }
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        if (paramString2.length() > 0) {
          asa.b(paramContext, paramString1, paramString2);
        }
        if (paramBoolean)
        {
          MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131230922), 1, new boolean[0]);
          paramAnonymousArrayOfXh = new HashMap();
          paramAnonymousArrayOfXh.put("f", paramApn.d);
          paramAnonymousArrayOfXh.put("playlist", paramString1);
          paramAnonymousArrayOfXh.put("t", "yt");
          MixerBoxUtils.a("action:music_add", paramAnonymousArrayOfXh);
          MixerBoxUtils.a(paramContext, false);
        }
      }
    });
  }
  
  public static void a(final Context paramContext, final String paramString, final aqx paramAqx)
  {
    arx.a(arx.h(paramString), new apj(paramContext)
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
        if (paramAqx != null)
        {
          paramAqx.b();
          return;
        }
        MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231011), 1, new boolean[0]);
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        paramAnonymousArrayOfXh = new HashMap();
        paramAnonymousArrayOfXh.put("id", paramString);
        MixerBoxUtils.a("action:playlist_deletel", paramAnonymousArrayOfXh);
        if (paramAqx != null)
        {
          paramAqx.a();
          return;
        }
        MixerBoxUtils.a(paramContext, true);
        MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131230945), 1, new boolean[0]);
      }
    });
  }
  
  public static void a(final Context paramContext, String paramString, final arn paramArn)
  {
    arx.a(arx.a("", paramString, 0), new apj(paramContext)
    {
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        int i = 0;
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        paramArn.a = new ArrayList();
        if (paramAnonymousArrayOfByte != null) {}
        for (;;)
        {
          try
          {
            paramAnonymousArrayOfXh = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject("searchVideo").getJSONArray("items");
            paramAnonymousInt = i;
            if (paramAnonymousInt < paramAnonymousArrayOfXh.length())
            {
              paramAnonymousArrayOfByte = paramAnonymousArrayOfXh.getJSONObject(paramAnonymousInt);
              MixerBoxUtils.a(paramArn.a, paramAnonymousArrayOfByte, paramContext, paramAnonymousInt, paramAnonymousArrayOfXh.length() - paramAnonymousInt - 1);
              paramAnonymousInt += 1;
              continue;
            }
            if ((paramAnonymousArrayOfXh.length() >= 2) && (!paramAnonymousArrayOfXh.getJSONObject(1).isNull("isArtist")) && (paramAnonymousArrayOfXh.getJSONObject(1).getBoolean("isArtist")) && (!paramAnonymousArrayOfXh.getJSONObject(1).isNull("title")))
            {
              paramAnonymousArrayOfXh = paramAnonymousArrayOfXh.getJSONObject(1).getString("title");
              if ((paramContext instanceof MainPage)) {
                ((MainPage)paramContext).a(paramAnonymousArrayOfXh, "searchArtist");
              }
            }
          }
          catch (JSONException paramAnonymousArrayOfXh)
          {
            paramAnonymousArrayOfXh.printStackTrace();
            continue;
          }
          paramArn.a();
          return;
          MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231010), 1, new boolean[0]);
        }
      }
    });
  }
  
  public static void a(final Context paramContext, String paramString1, final arn paramArn, String paramString2)
  {
    arx.a(arx.a(paramString2, paramString1, 0), new apj(paramContext)
    {
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        int i = 0;
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        paramArn.a = new ArrayList();
        if (paramAnonymousArrayOfByte != null) {
          try
          {
            paramAnonymousArrayOfXh = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject("searchVideo").getJSONArray("items");
            paramAnonymousInt = i;
            while (paramAnonymousInt < paramAnonymousArrayOfXh.length())
            {
              paramAnonymousArrayOfByte = paramAnonymousArrayOfXh.getJSONObject(paramAnonymousInt);
              MixerBoxUtils.a(paramArn.a, paramAnonymousArrayOfByte, paramContext, paramAnonymousInt, paramAnonymousArrayOfXh.length() - paramAnonymousInt - 1);
              paramAnonymousInt += 1;
            }
            paramArn.a();
          }
          catch (JSONException paramAnonymousArrayOfXh)
          {
            paramAnonymousArrayOfXh.printStackTrace();
          }
        }
        for (;;)
        {
          paramArn.j = true;
          return;
          MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231010), 1, new boolean[0]);
        }
      }
    });
  }
  
  public static void a(final Context paramContext, String paramString1, final arn paramArn, final String paramString2, final String paramString3)
  {
    arx.a(arx.a(paramString1, 0, paramString2), new apj(paramContext)
    {
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        int i = 0;
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        paramArn.a = new ArrayList();
        if (paramAnonymousArrayOfByte != null) {
          try
          {
            paramAnonymousArrayOfXh = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject("getVector").getJSONArray("items");
            paramAnonymousInt = i;
            if (!paramAnonymousArrayOfXh.getJSONObject(0).isNull("type"))
            {
              paramAnonymousInt = i;
              if (!paramAnonymousArrayOfXh.getJSONObject(0).getString("type").equals("divider"))
              {
                paramAnonymousInt = i;
                if (!paramString2.equals("dj"))
                {
                  paramArn.a.add(new apt(new JSONObject("{\"type\":\"divider\",\"title\":\"" + paramString3 + "\"}"), paramContext));
                  paramAnonymousInt = i;
                }
              }
            }
            while (paramAnonymousInt < paramAnonymousArrayOfXh.length())
            {
              paramAnonymousArrayOfByte = paramAnonymousArrayOfXh.getJSONObject(paramAnonymousInt);
              MixerBoxUtils.a(paramArn.a, paramAnonymousArrayOfByte, paramContext, paramAnonymousInt, paramAnonymousArrayOfXh.length() - paramAnonymousInt - 1);
              paramAnonymousInt += 1;
            }
            paramArn.a();
          }
          catch (JSONException paramAnonymousArrayOfXh)
          {
            paramAnonymousArrayOfXh.printStackTrace();
          }
        }
        for (;;)
        {
          paramArn.j = true;
          return;
          MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231010), 1, new boolean[0]);
        }
      }
    });
  }
  
  public static void a(final Context paramContext, final String paramString1, final String paramString2)
  {
    arx.a(arx.b(paramString1, paramString2), new apj(paramContext)
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
        MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231011), 1, new boolean[0]);
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131230946), 1, new boolean[0]);
        paramAnonymousArrayOfXh = new HashMap();
        paramAnonymousArrayOfXh.put("id", paramString1);
        MixerBoxUtils.a("action:playlist_change_title", paramAnonymousArrayOfXh);
        if ((((MainPage)paramContext).m.lastElement() instanceof SongFragment))
        {
          ((SongFragment)((MainPage)paramContext).m.lastElement()).F.a.equals(paramString1);
          ((SongFragment)((MainPage)paramContext).m.lastElement()).F.b = paramString2;
        }
        MixerBoxUtils.a(paramContext, false);
      }
    });
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    String str = asa.h(paramContext);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("ytId", paramString3);
      if (!paramString1.equals("")) {
        localJSONObject.put("musicId", paramString1);
      }
      localJSONObject.put("playlistId", paramString2);
      if (!str.equals("")) {
        localJSONObject.put("userId", str);
      }
      localJSONObject.put("isForeground", paramBoolean);
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    a(paramContext, "Listening", localJSONObject);
    arx.a(arx.a(paramString1, paramString2, paramString3, str), new AsyncHttpResponseHandler()
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        if (paramAnonymousArrayOfByte != null) {
          new StringBuilder("Cohort + ").append(paramAnonymousThrowable.toString()).append(" / ").append(new String(paramAnonymousArrayOfByte));
        }
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        if (paramAnonymousArrayOfByte != null) {
          new StringBuilder("Cohort +  / ").append(new String(paramAnonymousArrayOfByte));
        }
      }
    });
  }
  
  public static void a(Context paramContext, String paramString, JSONObject paramJSONObject)
  {
    Object localObject2 = "";
    boolean bool2 = false;
    boolean bool1 = bool2;
    Object localObject1 = localObject2;
    if (paramContext != null)
    {
      bool1 = bool2;
      localObject1 = localObject2;
      if ((paramContext instanceof MainPage))
      {
        bool1 = bool2;
        localObject1 = localObject2;
        if (((MainPage)paramContext).aB != null)
        {
          localObject1 = ((MainPage)paramContext).aB;
          bool1 = ((MainPage)paramContext).aD;
        }
      }
    }
    try
    {
      paramContext = new ArrayList();
      localObject2 = paramJSONObject.keys();
      while (((Iterator)localObject2).hasNext())
      {
        String str = (String)((Iterator)localObject2).next();
        if (!paramJSONObject.isNull(str)) {
          paramContext.add(new bl(str, paramJSONObject.getString(str)));
        }
      }
      a(paramString, paramContext, (String)localObject1, bool1);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void a(final Context paramContext, String paramString1, boolean paramBoolean, final String paramString2, final int paramInt)
  {
    MyFocusableWebView localMyFocusableWebView = new MyFocusableWebView(paramContext);
    localMyFocusableWebView.getSettings().setJavaScriptEnabled(true);
    localMyFocusableWebView.getSettings().setCacheMode(2);
    localMyFocusableWebView.getSettings().setDomStorageEnabled(true);
    localMyFocusableWebView.setWebChromeClient(new WebChromeClient());
    localMyFocusableWebView.setWebViewClient(new WebViewClient()
    {
      public final void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
        if (!this.a) {}
        try
        {
          if (((MyFocusableWebView)paramAnonymousWebView).a != null)
          {
            ((MyFocusableWebView)paramAnonymousWebView).a.show();
            asa.g(paramContext, paramString2);
            asa.k(paramContext, paramInt);
          }
          return;
        }
        catch (Exception paramAnonymousWebView) {}
      }
      
      public final boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (paramAnonymousString.contains("fb://"))
        {
          MixerBoxUtils.g(paramContext);
          return true;
        }
        if (paramAnonymousString.contains("://play.google.com/store/apps/details?id=")) {
          try
          {
            paramAnonymousWebView = paramAnonymousString.split("id=")[1];
            Intent localIntent = new Intent("android.intent.action.VIEW");
            localIntent.setData(Uri.parse("market://details?id=" + paramAnonymousWebView));
            paramContext.startActivity(localIntent);
            return true;
          }
          catch (Exception paramAnonymousWebView)
          {
            for (;;)
            {
              paramAnonymousWebView = new Intent("android.intent.action.VIEW");
              paramAnonymousWebView.setData(Uri.parse(paramAnonymousString));
              paramContext.startActivity(paramAnonymousWebView);
            }
          }
        }
        if (paramAnonymousString.contains("market://details?id=")) {
          try
          {
            paramAnonymousWebView = new Intent("android.intent.action.VIEW");
            paramAnonymousWebView.setData(Uri.parse(paramAnonymousString));
            paramContext.startActivity(paramAnonymousWebView);
            return true;
          }
          catch (Exception paramAnonymousWebView)
          {
            for (;;)
            {
              paramAnonymousWebView = paramAnonymousString.split("id=")[1];
              paramAnonymousString = new Intent("android.intent.action.VIEW");
              paramAnonymousString.setData(Uri.parse("http://play.google.com/store/apps/details?id=" + paramAnonymousWebView));
              paramContext.startActivity(paramAnonymousString);
            }
          }
        }
        if (paramAnonymousString.contains("mbappaction://")) {
          MixerBoxUtils.e(paramContext, paramAnonymousString);
        }
        try
        {
          if (((MyFocusableWebView)paramAnonymousWebView).a != null) {
            ((MyFocusableWebView)paramAnonymousWebView).a.dismiss();
          }
          return true;
          if (paramAnonymousString.startsWith("mailto:"))
          {
            paramAnonymousWebView = MailTo.parse(paramAnonymousString);
            paramAnonymousString = new Intent("android.intent.action.SEND");
            paramAnonymousString.putExtra("android.intent.extra.EMAIL", new String[] { paramAnonymousWebView.getTo() });
            paramAnonymousString.setType("message/rfc822");
            paramContext.startActivity(paramAnonymousString);
            return true;
          }
          return super.shouldOverrideUrlLoading(paramAnonymousWebView, paramAnonymousString);
        }
        catch (Exception paramAnonymousWebView)
        {
          for (;;) {}
        }
      }
    });
    int i1 = a(paramContext, 40);
    RelativeLayout localRelativeLayout = new RelativeLayout(paramContext);
    localRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    Object localObject1 = new RelativeLayout.LayoutParams(-1, -1);
    ((RelativeLayout.LayoutParams)localObject1).setMargins(0, i1, 0, 0);
    localMyFocusableWebView.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    localObject1 = new RelativeLayout(paramContext);
    ((RelativeLayout)localObject1).setBackgroundColor(paramContext.getResources().getColor(2131558412));
    Object localObject2 = new RelativeLayout.LayoutParams(-1, i1);
    ((RelativeLayout.LayoutParams)localObject2).addRule(10);
    ((RelativeLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    localObject2 = new ImageView(paramContext);
    ((ImageView)localObject2).setId(2131623954);
    a(paramContext, 2130837780, (ImageView)localObject2, 8);
    Object localObject3 = new RelativeLayout.LayoutParams(i1, i1);
    ((RelativeLayout.LayoutParams)localObject3).setMargins(10, 10, 10, 10);
    ((RelativeLayout.LayoutParams)localObject3).addRule(11);
    ((ImageView)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
    localObject3 = new TextView(paramContext);
    ((TextView)localObject3).setText(paramContext.getResources().getString(2131230918));
    ((TextView)localObject3).setTextSize(18.0F);
    ((TextView)localObject3).setGravity(16);
    ((TextView)localObject3).setTextColor(-1);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, i1);
    localLayoutParams.setMargins(20, 0, 0, 0);
    localLayoutParams.addRule(0, 2131623954);
    ((TextView)localObject3).setLayoutParams(localLayoutParams);
    ((RelativeLayout)localObject1).addView((View)localObject2);
    ((RelativeLayout)localObject1).addView((View)localObject3);
    localRelativeLayout.addView(localMyFocusableWebView);
    localRelativeLayout.addView((View)localObject1);
    localObject1 = new Dialog(paramContext);
    ((Dialog)localObject1).requestWindowFeature(1);
    ((Dialog)localObject1).setContentView(localRelativeLayout);
    ((Dialog)localObject1).getWindow().setSoftInputMode(16);
    ((Dialog)localObject1).getWindow().setSoftInputMode(2);
    ((Dialog)localObject1).getWindow().setBackgroundDrawable(new ColorDrawable(0));
    localMyFocusableWebView.a = ((Dialog)localObject1);
    if (paramBoolean)
    {
      ((Dialog)localObject1).show();
      asa.g(paramContext, paramString2);
      asa.k(paramContext, paramInt);
    }
    ((ImageView)localObject2).setOnClickListener(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this.a.dismiss();
      }
    });
    localMyFocusableWebView.loadUrl(paramString1);
  }
  
  public static void a(Context paramContext, String paramString, boolean paramBoolean, SongFragment paramSongFragment)
  {
    ((MainPage)paramContext).E = new ArrayList();
    if ((((MainPage)paramContext).an == null) || (!((MainPage)paramContext).an.a())) {
      return;
    }
    Cursor localCursor = ((MainPage)paramContext).an.a(paramString);
    int i2 = localCursor.getCount();
    localCursor.moveToLast();
    int i1 = 0;
    while (i1 < i2)
    {
      ((MainPage)paramContext).E.add(new apn(localCursor.getString(0), localCursor.getString(2), localCursor.getString(3), localCursor.getString(4), i1, localCursor.getInt(6), localCursor.getString(7)));
      localCursor.moveToPrevious();
      i1 += 1;
    }
    localCursor.close();
    if (paramSongFragment != null) {
      paramSongFragment.a(((MainPage)paramContext).E, paramString);
    }
    if ((((MainPage)paramContext).aJ != null) && (((MainPage)paramContext).aJ.b.a())) {}
    for (i1 = 1;; i1 = 0)
    {
      boolean bool = paramBoolean;
      if (i1 == 0)
      {
        bool = paramBoolean;
        if (MyApplication.c.endsWith("C")) {
          bool = true;
        }
      }
      if ((!bool) || (((MainPage)paramContext).E.size() == 0)) {
        break;
      }
      ((MainPage)paramContext).F = new ArrayList(((MainPage)paramContext).E);
      ((MainPage)paramContext).I = ((apn)((MainPage)paramContext).F.get(0));
      if ((paramSongFragment != null) && ((((MainPage)paramContext).m.lastElement() instanceof SongFragment))) {
        ((MainPage)paramContext).J = ((SongFragment)((MainPage)paramContext).m.lastElement()).F;
      }
      for (;;)
      {
        ((MainPage)paramContext).G = 0;
        ((MainPage)paramContext).a(true, 2, false);
        return;
        if (((MainPage)paramContext).M.size() > 0)
        {
          i1 = 0;
          while (i1 < ((MainPage)paramContext).M.size())
          {
            if (paramString.equals(((apm)((MainPage)paramContext).M.get(i1)).a)) {
              ((MainPage)paramContext).J = ((apm)((MainPage)paramContext).M.get(i1));
            }
            i1 += 1;
          }
        }
        else
        {
          ((MainPage)paramContext).J = null;
        }
      }
    }
  }
  
  private static void a(Context paramContext, List<aqc> paramList, boolean paramBoolean)
  {
    int i1 = e(paramContext);
    if ((i1 <= 0) || (!paramBoolean)) {}
    try
    {
      String str = paramContext.getResources().getString(2131230924);
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("type", "divider");
      localJSONObject.put("title", str);
      a(paramList, localJSONObject, paramContext, 0, 0);
      return;
    }
    catch (Exception paramContext) {}
    paramList.add(((MainPage)paramContext).b(new apm("PLAYLISTMP3ID", paramContext.getResources().getString(2131230924), "", "", String.valueOf(i1), "0", "", false)));
    return;
  }
  
  public static void a(final Context paramContext, final boolean paramBoolean)
  {
    arx.a(arx.c(paramContext), new apj(paramContext)
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
        if (paramAnonymousArrayOfByte != null) {
          new String(paramAnonymousArrayOfByte);
        }
      }
      
      /* Error */
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        // Byte code:
        //   0: aload_0
        //   1: iload_1
        //   2: aload_2
        //   3: aload_3
        //   4: invokespecial 39	apj:onSuccess	(I[Lxh;[B)V
        //   7: aload_0
        //   8: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   11: ifnull +7 -> 18
        //   14: aload_3
        //   15: ifnonnull +4 -> 19
        //   18: return
        //   19: aload_0
        //   20: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   23: invokestatic 45	asa:r	(Landroid/content/Context;)Z
        //   26: ifne +36 -> 62
        //   29: aload_0
        //   30: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   33: invokestatic 48	mbinc12/mb32b/utils/MixerBoxUtils:c	(Landroid/content/Context;)V
        //   36: aload_0
        //   37: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   40: invokestatic 50	mbinc12/mb32b/utils/MixerBoxUtils:b	(Landroid/content/Context;)V
        //   43: aload_0
        //   44: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   47: iconst_1
        //   48: invokestatic 52	asa:b	(Landroid/content/Context;Z)V
        //   51: aload_0
        //   52: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   55: checkcast 54	mbinc12/mb32b/MainPage
        //   58: invokevirtual 58	mbinc12/mb32b/MainPage:t	()V
        //   61: return
        //   62: aload_0
        //   63: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   66: checkcast 54	mbinc12/mb32b/MainPage
        //   69: getfield 62	mbinc12/mb32b/MainPage:C	Ljava/util/List;
        //   72: ifnull +174 -> 246
        //   75: aload_0
        //   76: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   79: checkcast 54	mbinc12/mb32b/MainPage
        //   82: getfield 62	mbinc12/mb32b/MainPage:C	Ljava/util/List;
        //   85: invokeinterface 67 1 0
        //   90: new 69	org/json/JSONObject
        //   93: dup
        //   94: new 28	java/lang/String
        //   97: dup
        //   98: aload_3
        //   99: invokespecial 31	java/lang/String:<init>	([B)V
        //   102: invokespecial 72	org/json/JSONObject:<init>	(Ljava/lang/String;)V
        //   105: astore_2
        //   106: aload_0
        //   107: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   110: checkcast 54	mbinc12/mb32b/MainPage
        //   113: new 74	java/util/ArrayList
        //   116: dup
        //   117: invokespecial 76	java/util/ArrayList:<init>	()V
        //   120: putfield 80	mbinc12/mb32b/MainPage:M	Ljava/util/ArrayList;
        //   123: aload_0
        //   124: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   127: checkcast 54	mbinc12/mb32b/MainPage
        //   130: new 74	java/util/ArrayList
        //   133: dup
        //   134: invokespecial 76	java/util/ArrayList:<init>	()V
        //   137: putfield 83	mbinc12/mb32b/MainPage:L	Ljava/util/ArrayList;
        //   140: aload_2
        //   141: ldc 85
        //   143: invokevirtual 89	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   146: astore_2
        //   147: aload_2
        //   148: ldc 91
        //   150: invokevirtual 95	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
        //   153: astore_3
        //   154: aload_0
        //   155: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   158: checkcast 54	mbinc12/mb32b/MainPage
        //   161: aload_3
        //   162: invokestatic 98	mbinc12/mb32b/MainPage:a	(Lorg/json/JSONArray;)Ljava/util/ArrayList;
        //   165: putfield 80	mbinc12/mb32b/MainPage:M	Ljava/util/ArrayList;
        //   168: aload_2
        //   169: ldc 100
        //   171: invokevirtual 95	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
        //   174: astore_3
        //   175: aload_0
        //   176: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   179: checkcast 54	mbinc12/mb32b/MainPage
        //   182: aload_3
        //   183: invokestatic 98	mbinc12/mb32b/MainPage:a	(Lorg/json/JSONArray;)Ljava/util/ArrayList;
        //   186: putfield 83	mbinc12/mb32b/MainPage:L	Ljava/util/ArrayList;
        //   189: aload_2
        //   190: ldc 102
        //   192: invokevirtual 95	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
        //   195: astore_2
        //   196: iconst_0
        //   197: istore_1
        //   198: iload_1
        //   199: aload_2
        //   200: invokevirtual 108	org/json/JSONArray:length	()I
        //   203: if_icmpge +63 -> 266
        //   206: aload_2
        //   207: iload_1
        //   208: invokevirtual 111	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
        //   211: astore_3
        //   212: aload_0
        //   213: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   216: checkcast 54	mbinc12/mb32b/MainPage
        //   219: getfield 62	mbinc12/mb32b/MainPage:C	Ljava/util/List;
        //   222: aload_3
        //   223: aload_0
        //   224: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   227: iload_1
        //   228: aload_2
        //   229: invokevirtual 108	org/json/JSONArray:length	()I
        //   232: iload_1
        //   233: isub
        //   234: iconst_1
        //   235: isub
        //   236: invokestatic 114	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/util/List;Lorg/json/JSONObject;Landroid/content/Context;II)V
        //   239: iload_1
        //   240: iconst_1
        //   241: iadd
        //   242: istore_1
        //   243: goto -45 -> 198
        //   246: aload_0
        //   247: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   250: checkcast 54	mbinc12/mb32b/MainPage
        //   253: new 74	java/util/ArrayList
        //   256: dup
        //   257: invokespecial 76	java/util/ArrayList:<init>	()V
        //   260: putfield 62	mbinc12/mb32b/MainPage:C	Ljava/util/List;
        //   263: goto -173 -> 90
        //   266: aload_0
        //   267: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   270: aload_0
        //   271: getfield 18	mbinc12/mb32b/utils/MixerBoxUtils$11:b	Z
        //   274: invokestatic 115	mbinc12/mb32b/utils/MixerBoxUtils:b	(Landroid/content/Context;Z)V
        //   277: aload_0
        //   278: getfield 16	mbinc12/mb32b/utils/MixerBoxUtils$11:a	Landroid/content/Context;
        //   281: checkcast 54	mbinc12/mb32b/MainPage
        //   284: invokevirtual 118	mbinc12/mb32b/MainPage:G	()V
        //   287: goto -236 -> 51
        //   290: astore_2
        //   291: aload_2
        //   292: invokevirtual 121	org/json/JSONException:printStackTrace	()V
        //   295: goto -244 -> 51
        //   298: astore_3
        //   299: goto -110 -> 189
        //   302: astore_3
        //   303: goto -135 -> 168
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	306	0	this	11
        //   0	306	1	paramAnonymousInt	int
        //   0	306	2	paramAnonymousArrayOfXh	xh[]
        //   0	306	3	paramAnonymousArrayOfByte	byte[]
        // Exception table:
        //   from	to	target	type
        //   90	147	290	org/json/JSONException
        //   147	168	290	org/json/JSONException
        //   168	189	290	org/json/JSONException
        //   189	196	290	org/json/JSONException
        //   198	239	290	org/json/JSONException
        //   266	287	290	org/json/JSONException
        //   168	189	298	java/lang/Exception
        //   147	168	302	java/lang/Exception
      }
    });
  }
  
  /* Error */
  public static void a(Context paramContext, boolean paramBoolean, SongFragment paramSongFragment, String paramString, Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: checkcast 483	mbinc12/mb32b/MainPage
    //   4: new 393	java/util/ArrayList
    //   7: dup
    //   8: invokespecial 394	java/util/ArrayList:<init>	()V
    //   11: putfield 1109	mbinc12/mb32b/MainPage:E	Ljava/util/ArrayList;
    //   14: aload_0
    //   15: invokevirtual 1196	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   18: aload 4
    //   20: iconst_5
    //   21: anewarray 329	java/lang/String
    //   24: dup
    //   25: iconst_0
    //   26: ldc_w 1198
    //   29: aastore
    //   30: dup
    //   31: iconst_1
    //   32: ldc_w 308
    //   35: aastore
    //   36: dup
    //   37: iconst_2
    //   38: ldc_w 338
    //   41: aastore
    //   42: dup
    //   43: iconst_3
    //   44: ldc_w 1200
    //   47: aastore
    //   48: dup
    //   49: iconst_4
    //   50: ldc_w 354
    //   53: aastore
    //   54: aload_3
    //   55: aconst_null
    //   56: aconst_null
    //   57: invokevirtual 1206	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   60: astore_3
    //   61: aload_3
    //   62: invokeinterface 1117 1 0
    //   67: istore 6
    //   69: iload 6
    //   71: ifle +129 -> 200
    //   74: aload_3
    //   75: invokeinterface 1209 1 0
    //   80: pop
    //   81: iconst_0
    //   82: istore 5
    //   84: iload 5
    //   86: iload 6
    //   88: if_icmpge +112 -> 200
    //   91: aload_0
    //   92: checkcast 483	mbinc12/mb32b/MainPage
    //   95: getfield 1109	mbinc12/mb32b/MainPage:E	Ljava/util/ArrayList;
    //   98: new 342	apn
    //   101: dup
    //   102: aload_3
    //   103: iconst_0
    //   104: invokeinterface 1121 2 0
    //   109: new 207	java/lang/StringBuilder
    //   112: dup
    //   113: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   116: aload_3
    //   117: iconst_4
    //   118: invokeinterface 1121 2 0
    //   123: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: ldc_w 1211
    //   129: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload_3
    //   133: iconst_1
    //   134: invokeinterface 1121 2 0
    //   139: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: aload_3
    //   146: iconst_2
    //   147: invokeinterface 1121 2 0
    //   152: invokestatic 377	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   155: invokevirtual 380	java/lang/Integer:intValue	()I
    //   158: sipush 1000
    //   161: idiv
    //   162: invokestatic 1181	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   165: aload_3
    //   166: iconst_3
    //   167: invokeinterface 1121 2 0
    //   172: iload 5
    //   174: iconst_2
    //   175: ldc 123
    //   177: invokespecial 345	apn:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;)V
    //   180: invokevirtual 414	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   183: pop
    //   184: aload_3
    //   185: invokeinterface 1214 1 0
    //   190: pop
    //   191: iload 5
    //   193: iconst_1
    //   194: iadd
    //   195: istore 5
    //   197: goto -113 -> 84
    //   200: aload_3
    //   201: ifnull +9 -> 210
    //   204: aload_3
    //   205: invokeinterface 1130 1 0
    //   210: aload_2
    //   211: ifnull +15 -> 226
    //   214: aload_2
    //   215: aload_0
    //   216: checkcast 483	mbinc12/mb32b/MainPage
    //   219: getfield 1109	mbinc12/mb32b/MainPage:E	Ljava/util/ArrayList;
    //   222: aconst_null
    //   223: invokevirtual 1133	mbinc12/mb32b/fragments/SongFragment:a	(Ljava/util/ArrayList;Ljava/lang/String;)V
    //   226: iload_1
    //   227: ifeq +16 -> 243
    //   230: aload_0
    //   231: checkcast 483	mbinc12/mb32b/MainPage
    //   234: getfield 1109	mbinc12/mb32b/MainPage:E	Ljava/util/ArrayList;
    //   237: invokevirtual 1144	java/util/ArrayList:size	()I
    //   240: ifne +35 -> 275
    //   243: return
    //   244: astore_3
    //   245: aconst_null
    //   246: astore_3
    //   247: aload_3
    //   248: ifnull -38 -> 210
    //   251: aload_3
    //   252: invokeinterface 1130 1 0
    //   257: goto -47 -> 210
    //   260: astore_0
    //   261: aconst_null
    //   262: astore_3
    //   263: aload_3
    //   264: ifnull +9 -> 273
    //   267: aload_3
    //   268: invokeinterface 1130 1 0
    //   273: aload_0
    //   274: athrow
    //   275: aload_0
    //   276: checkcast 483	mbinc12/mb32b/MainPage
    //   279: new 393	java/util/ArrayList
    //   282: dup
    //   283: aload_0
    //   284: checkcast 483	mbinc12/mb32b/MainPage
    //   287: getfield 1109	mbinc12/mb32b/MainPage:E	Ljava/util/ArrayList;
    //   290: invokespecial 1147	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   293: putfield 1149	mbinc12/mb32b/MainPage:F	Ljava/util/ArrayList;
    //   296: aload_0
    //   297: checkcast 483	mbinc12/mb32b/MainPage
    //   300: aload_0
    //   301: checkcast 483	mbinc12/mb32b/MainPage
    //   304: getfield 1149	mbinc12/mb32b/MainPage:F	Ljava/util/ArrayList;
    //   307: iconst_0
    //   308: invokevirtual 1153	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   311: checkcast 342	apn
    //   314: putfield 1155	mbinc12/mb32b/MainPage:I	Lapn;
    //   317: aload_2
    //   318: ifnull +45 -> 363
    //   321: aload_0
    //   322: checkcast 483	mbinc12/mb32b/MainPage
    //   325: aload_0
    //   326: checkcast 483	mbinc12/mb32b/MainPage
    //   329: getfield 486	mbinc12/mb32b/MainPage:m	Ljava/util/Stack;
    //   332: invokevirtual 492	java/util/Stack:lastElement	()Ljava/lang/Object;
    //   335: checkcast 418	mbinc12/mb32b/fragments/SongFragment
    //   338: getfield 421	mbinc12/mb32b/fragments/SongFragment:F	Lapm;
    //   341: putfield 1158	mbinc12/mb32b/MainPage:J	Lapm;
    //   344: aload_0
    //   345: checkcast 483	mbinc12/mb32b/MainPage
    //   348: iconst_0
    //   349: putfield 1161	mbinc12/mb32b/MainPage:G	I
    //   352: aload_0
    //   353: checkcast 483	mbinc12/mb32b/MainPage
    //   356: iconst_1
    //   357: iconst_2
    //   358: iconst_0
    //   359: invokevirtual 1164	mbinc12/mb32b/MainPage:a	(ZIZ)V
    //   362: return
    //   363: aload_0
    //   364: checkcast 483	mbinc12/mb32b/MainPage
    //   367: aconst_null
    //   368: putfield 1158	mbinc12/mb32b/MainPage:J	Lapm;
    //   371: goto -27 -> 344
    //   374: astore_0
    //   375: goto -112 -> 263
    //   378: astore 4
    //   380: goto -133 -> 247
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	383	0	paramContext	Context
    //   0	383	1	paramBoolean	boolean
    //   0	383	2	paramSongFragment	SongFragment
    //   0	383	3	paramString	String
    //   0	383	4	paramUri	Uri
    //   82	114	5	i1	int
    //   67	22	6	i2	int
    // Exception table:
    //   from	to	target	type
    //   14	61	244	java/lang/Exception
    //   14	61	260	finally
    //   61	69	374	finally
    //   74	81	374	finally
    //   91	191	374	finally
    //   61	69	378	java/lang/Exception
    //   74	81	378	java/lang/Exception
    //   91	191	378	java/lang/Exception
  }
  
  public static void a(ListView paramListView)
  {
    if (paramListView == null) {
      return;
    }
    int i1 = paramListView.getFirstVisiblePosition();
    label10:
    Object localObject;
    if (i1 <= paramListView.getLastVisiblePosition())
    {
      if (!(paramListView.getItemAtPosition(i1) instanceof aqf)) {
        break label74;
      }
      if (!((aqf)paramListView.getItemAtPosition(i1)).h)
      {
        localObject = paramListView.getChildAt(i1 - paramListView.getFirstVisiblePosition());
        paramListView.getAdapter().getView(i1, (View)localObject, paramListView);
      }
    }
    for (;;)
    {
      i1 += 1;
      break label10;
      break;
      label74:
      if ((paramListView.getItemAtPosition(i1) instanceof app))
      {
        localObject = (app)paramListView.getItemAtPosition(i1);
        if ((((app)localObject).b != null) && (((app)localObject).b.getAdapter() != null)) {
          ((app)localObject).b.getAdapter().notifyDataSetChanged();
        }
      }
    }
  }
  
  public static void a(TextView paramTextView)
  {
    MovementMethod localMovementMethod = paramTextView.getMovementMethod();
    if (((localMovementMethod == null) || (!(localMovementMethod instanceof LinkMovementMethod))) && (paramTextView.getLinksClickable())) {
      paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
  }
  
  public static void a(apn paramApn)
  {
    c = paramApn;
  }
  
  public static void a(String paramString1, String paramString2)
  {
    d = paramString1;
    e = paramString2;
  }
  
  private static void a(String paramString1, List<bl<String, String>> paramList, String paramString2, boolean paramBoolean)
  {
    int i2 = 0;
    try
    {
      if (paramList.size() <= 0) {
        a("trace:" + paramString1, null);
      }
      for (;;)
      {
        try
        {
          paramString2 = arx.a(paramString1, paramString2, paramBoolean);
          i1 = 0;
          if (i1 < paramList.size())
          {
            localObject3 = (bl)paramList.get(i1);
            localObject1 = ((bl)localObject3).a.toString();
          }
        }
        catch (Exception paramString2)
        {
          int i1;
          Object localObject3;
          Object localObject1;
          Object localObject2;
          continue;
        }
        try
        {
          localObject2 = URLEncoder.encode((String)localObject1, "utf-8");
          localObject1 = localObject2;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException1)
        {
          continue;
        }
        localObject2 = ((bl)localObject3).b.toString();
        try
        {
          localObject3 = URLEncoder.encode((String)localObject2, "utf-8");
          localObject2 = localObject3;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException2)
        {
          continue;
        }
        paramString2 = paramString2 + "&" + (String)localObject1 + "=" + (String)localObject2;
        i1 += 1;
        continue;
        localObject1 = new HashMap();
        i1 = 0;
        if (i1 < paramList.size())
        {
          localObject2 = (bl)paramList.get(i1);
          ((Map)localObject1).put(((bl)localObject2).a.toString(), ((bl)localObject2).b.toString());
          i1 += 1;
        }
        else
        {
          a("trace:" + paramString1, (Map)localObject1);
        }
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
      arx.a(paramString2, null);
      try
      {
        paramString1 = new lf(paramString1);
        i1 = i2;
        while (i1 < paramList.size())
        {
          paramString2 = (bl)paramList.get(i1);
          paramString1.a(paramString2.a.toString(), paramString2.b.toString());
          i1 += 1;
        }
        kv.c().a(paramString1);
        return;
      }
      catch (Exception paramString1) {}
    }
  }
  
  public static void a(String paramString, Map<String, String> paramMap)
  {
    if (paramMap != null)
    {
      FlurryAgent.logEvent(paramString, paramMap);
      return;
    }
    FlurryAgent.logEvent(paramString);
  }
  
  private static void a(List<HashMap<String, Object>> paramList, String paramString, int paramInt1, int paramInt2)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("title", paramString);
    localHashMap.put("icon", Integer.valueOf(paramInt1));
    localHashMap.put("id", Integer.valueOf(paramInt2));
    paramList.add(localHashMap);
  }
  
  /* Error */
  public static void a(List<aqc> paramList, JSONObject paramJSONObject, Context paramContext, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 1323
    //   4: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   7: ifne +56 -> 63
    //   10: aload_1
    //   11: ldc_w 1323
    //   14: invokevirtual 400	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   17: ldc_w 1325
    //   20: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   23: ifne +40 -> 63
    //   26: aload_1
    //   27: ldc_w 1323
    //   30: invokevirtual 400	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   33: ldc_w 1325
    //   36: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   39: astore 6
    //   41: aload 6
    //   43: ldc_w 1327
    //   46: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   49: ifeq +25 -> 74
    //   52: aload_1
    //   53: ldc_w 407
    //   56: ldc_w 1327
    //   59: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   62: pop
    //   63: aload_1
    //   64: ldc_w 407
    //   67: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   70: ifeq +131 -> 201
    //   73: return
    //   74: aload 6
    //   76: ldc_w 1329
    //   79: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   82: ifeq +17 -> 99
    //   85: aload_1
    //   86: ldc_w 407
    //   89: ldc_w 1331
    //   92: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   95: pop
    //   96: goto -33 -> 63
    //   99: aload 6
    //   101: ldc_w 1333
    //   104: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   107: ifeq +17 -> 124
    //   110: aload_1
    //   111: ldc_w 407
    //   114: ldc_w 1335
    //   117: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   120: pop
    //   121: goto -58 -> 63
    //   124: aload 6
    //   126: ldc_w 1337
    //   129: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   132: ifeq +17 -> 149
    //   135: aload_1
    //   136: ldc_w 407
    //   139: ldc_w 1339
    //   142: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   145: pop
    //   146: goto -83 -> 63
    //   149: aload 6
    //   151: ldc_w 1341
    //   154: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   157: ifeq +17 -> 174
    //   160: aload_1
    //   161: ldc_w 407
    //   164: ldc_w 1341
    //   167: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   170: pop
    //   171: goto -108 -> 63
    //   174: aload 6
    //   176: ldc_w 1343
    //   179: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   182: ifne -109 -> 73
    //   185: aload 6
    //   187: ldc_w 1345
    //   190: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   193: istore 5
    //   195: iload 5
    //   197: ifeq -134 -> 63
    //   200: return
    //   201: aload_1
    //   202: ldc_w 407
    //   205: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   208: astore 6
    //   210: aload 6
    //   212: ldc_w 1174
    //   215: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   218: ifeq +41 -> 259
    //   221: aload_0
    //   222: new 1347	apt
    //   225: dup
    //   226: aload_1
    //   227: aload_2
    //   228: invokespecial 1350	apt:<init>	(Lorg/json/JSONObject;Landroid/content/Context;)V
    //   231: invokeinterface 879 2 0
    //   236: pop
    //   237: return
    //   238: astore_1
    //   239: aload_1
    //   240: invokevirtual 1351	java/lang/Exception:toString	()Ljava/lang/String;
    //   243: pop
    //   244: aload_0
    //   245: new 1353	apu
    //   248: dup
    //   249: invokespecial 1354	apu:<init>	()V
    //   252: invokeinterface 879 2 0
    //   257: pop
    //   258: return
    //   259: aload 6
    //   261: ldc_w 1356
    //   264: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   267: ifeq +19 -> 286
    //   270: aload_0
    //   271: new 1358	apz
    //   274: dup
    //   275: aload_2
    //   276: invokespecial 1359	apz:<init>	(Landroid/content/Context;)V
    //   279: invokeinterface 879 2 0
    //   284: pop
    //   285: return
    //   286: aload 6
    //   288: ldc_w 1361
    //   291: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   294: ifeq +20 -> 314
    //   297: aload_0
    //   298: new 1363	aps
    //   301: dup
    //   302: aload_2
    //   303: aload_1
    //   304: invokespecial 1366	aps:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   307: invokeinterface 879 2 0
    //   312: pop
    //   313: return
    //   314: aload 6
    //   316: ldc_w 1368
    //   319: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   322: ifeq +20 -> 342
    //   325: aload_0
    //   326: new 1370	aqa
    //   329: dup
    //   330: aload_2
    //   331: aload_1
    //   332: invokespecial 1371	aqa:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   335: invokeinterface 879 2 0
    //   340: pop
    //   341: return
    //   342: aload 6
    //   344: ldc_w 409
    //   347: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   350: ifeq +23 -> 373
    //   353: aload_0
    //   354: new 1373	aqb
    //   357: dup
    //   358: aload_2
    //   359: aload_1
    //   360: iload_3
    //   361: iload 4
    //   363: invokespecial 1376	aqb:<init>	(Landroid/content/Context;Lorg/json/JSONObject;II)V
    //   366: invokeinterface 879 2 0
    //   371: pop
    //   372: return
    //   373: aload 6
    //   375: ldc_w 583
    //   378: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   381: ifeq +20 -> 401
    //   384: aload_0
    //   385: new 1226	aqf
    //   388: dup
    //   389: aload_2
    //   390: aload_1
    //   391: invokespecial 1377	aqf:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   394: invokeinterface 879 2 0
    //   399: pop
    //   400: return
    //   401: aload 6
    //   403: ldc_w 1379
    //   406: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   409: ifeq +19 -> 428
    //   412: aload_0
    //   413: new 1381	aqr
    //   416: dup
    //   417: aload_1
    //   418: invokespecial 1384	aqr:<init>	(Lorg/json/JSONObject;)V
    //   421: invokeinterface 879 2 0
    //   426: pop
    //   427: return
    //   428: aload 6
    //   430: ldc_w 1386
    //   433: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   436: ifeq +20 -> 456
    //   439: aload_0
    //   440: new 1388	apw
    //   443: dup
    //   444: aload_2
    //   445: aload_1
    //   446: invokespecial 1389	apw:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   449: invokeinterface 879 2 0
    //   454: pop
    //   455: return
    //   456: aload 6
    //   458: ldc_w 1331
    //   461: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   464: ifeq +20 -> 484
    //   467: aload_0
    //   468: new 1391	apr
    //   471: dup
    //   472: aload_2
    //   473: aload_1
    //   474: invokespecial 1392	apr:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   477: invokeinterface 879 2 0
    //   482: pop
    //   483: return
    //   484: aload 6
    //   486: ldc_w 1394
    //   489: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   492: ifeq +20 -> 512
    //   495: aload_0
    //   496: new 1396	aqh
    //   499: dup
    //   500: aload_2
    //   501: aload_1
    //   502: invokespecial 1397	aqh:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   505: invokeinterface 879 2 0
    //   510: pop
    //   511: return
    //   512: aload 6
    //   514: ldc_w 1399
    //   517: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   520: ifeq +20 -> 540
    //   523: aload_0
    //   524: new 1401	aqg
    //   527: dup
    //   528: aload_2
    //   529: aload_1
    //   530: invokespecial 1402	aqg:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   533: invokeinterface 879 2 0
    //   538: pop
    //   539: return
    //   540: aload 6
    //   542: ldc_w 1327
    //   545: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   548: ifeq +20 -> 568
    //   551: aload_0
    //   552: new 1404	aqm
    //   555: dup
    //   556: aload_2
    //   557: aload_1
    //   558: invokespecial 1405	aqm:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   561: invokeinterface 879 2 0
    //   566: pop
    //   567: return
    //   568: aload 6
    //   570: ldc_w 1341
    //   573: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   576: ifeq +20 -> 596
    //   579: aload_0
    //   580: new 1407	aqk
    //   583: dup
    //   584: aload_1
    //   585: aload_2
    //   586: invokespecial 1408	aqk:<init>	(Lorg/json/JSONObject;Landroid/content/Context;)V
    //   589: invokeinterface 879 2 0
    //   594: pop
    //   595: return
    //   596: aload 6
    //   598: ldc_w 1335
    //   601: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   604: ifeq +21 -> 625
    //   607: aload_0
    //   608: new 1410	aqn
    //   611: dup
    //   612: aload_1
    //   613: aload_2
    //   614: iload_3
    //   615: invokespecial 1413	aqn:<init>	(Lorg/json/JSONObject;Landroid/content/Context;I)V
    //   618: invokeinterface 879 2 0
    //   623: pop
    //   624: return
    //   625: aload 6
    //   627: ldc_w 1339
    //   630: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   633: ifeq +20 -> 653
    //   636: aload_0
    //   637: new 1415	aql
    //   640: dup
    //   641: aload_2
    //   642: aload_1
    //   643: invokespecial 1416	aql:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   646: invokeinterface 879 2 0
    //   651: pop
    //   652: return
    //   653: aload 6
    //   655: ldc_w 1418
    //   658: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   661: ifeq +20 -> 681
    //   664: aload_0
    //   665: new 1234	app
    //   668: dup
    //   669: aload_2
    //   670: aload_1
    //   671: invokespecial 1419	app:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   674: invokeinterface 879 2 0
    //   679: pop
    //   680: return
    //   681: aload 6
    //   683: ldc_w 1421
    //   686: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   689: ifeq +20 -> 709
    //   692: aload_0
    //   693: new 1423	apo
    //   696: dup
    //   697: aload_2
    //   698: aload_1
    //   699: invokespecial 1424	apo:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   702: invokeinterface 879 2 0
    //   707: pop
    //   708: return
    //   709: aload 6
    //   711: ldc_w 1426
    //   714: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   717: ifeq +58 -> 775
    //   720: aload_1
    //   721: ldc_w 580
    //   724: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   727: ifne +33 -> 760
    //   730: aload_1
    //   731: ldc_w 580
    //   734: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   737: invokestatic 1429	asc:a	(Ljava/lang/String;)Z
    //   740: ifeq +20 -> 760
    //   743: aload_0
    //   744: new 1431	apx
    //   747: dup
    //   748: aload_2
    //   749: aload_1
    //   750: invokespecial 1432	apx:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   753: invokeinterface 879 2 0
    //   758: pop
    //   759: return
    //   760: aload_0
    //   761: new 1353	apu
    //   764: dup
    //   765: invokespecial 1354	apu:<init>	()V
    //   768: invokeinterface 879 2 0
    //   773: pop
    //   774: return
    //   775: aload 6
    //   777: ldc_w 1434
    //   780: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   783: ifeq +20 -> 803
    //   786: aload_0
    //   787: new 1436	aqi
    //   790: dup
    //   791: aload_2
    //   792: aload_1
    //   793: invokespecial 1437	aqi:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   796: invokeinterface 879 2 0
    //   801: pop
    //   802: return
    //   803: aload 6
    //   805: ldc_w 1439
    //   808: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   811: ifeq +126 -> 937
    //   814: aload_2
    //   815: invokestatic 1441	asc:f	(Landroid/content/Context;)Z
    //   818: ifeq +18 -> 836
    //   821: aload_0
    //   822: new 1353	apu
    //   825: dup
    //   826: invokespecial 1354	apu:<init>	()V
    //   829: invokeinterface 879 2 0
    //   834: pop
    //   835: return
    //   836: aload_1
    //   837: ldc_w 1443
    //   840: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   843: ifne +36 -> 879
    //   846: aload_1
    //   847: ldc_w 1443
    //   850: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   853: ldc_w 1445
    //   856: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   859: ifeq +20 -> 879
    //   862: aload_0
    //   863: new 1447	aqd
    //   866: dup
    //   867: aload_2
    //   868: aload_1
    //   869: invokespecial 1448	aqd:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   872: invokeinterface 879 2 0
    //   877: pop
    //   878: return
    //   879: aload_1
    //   880: ldc_w 1443
    //   883: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   886: ifne +36 -> 922
    //   889: aload_1
    //   890: ldc_w 1443
    //   893: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   896: ldc_w 1450
    //   899: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   902: ifeq +20 -> 922
    //   905: aload_0
    //   906: new 1452	aqe
    //   909: dup
    //   910: aload_2
    //   911: aload_1
    //   912: invokespecial 1453	aqe:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   915: invokeinterface 879 2 0
    //   920: pop
    //   921: return
    //   922: aload_0
    //   923: new 1353	apu
    //   926: dup
    //   927: invokespecial 1354	apu:<init>	()V
    //   930: invokeinterface 879 2 0
    //   935: pop
    //   936: return
    //   937: aload 6
    //   939: ldc_w 1455
    //   942: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   945: ifeq +20 -> 965
    //   948: aload_0
    //   949: new 1457	aqj
    //   952: dup
    //   953: aload_2
    //   954: aload_1
    //   955: invokespecial 1458	aqj:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   958: invokeinterface 879 2 0
    //   963: pop
    //   964: return
    //   965: aload 6
    //   967: ldc_w 531
    //   970: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   973: ifeq +801 -> 1774
    //   976: aload_1
    //   977: ldc_w 580
    //   980: invokevirtual 278	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   983: ifne +791 -> 1774
    //   986: aload_1
    //   987: ldc_w 580
    //   990: invokevirtual 285	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   993: astore_1
    //   994: aload_1
    //   995: ldc_w 1460
    //   998: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1001: ifeq +60 -> 1061
    //   1004: aload_2
    //   1005: checkcast 483	mbinc12/mb32b/MainPage
    //   1008: getfield 510	mbinc12/mb32b/MainPage:am	Z
    //   1011: ifne +50 -> 1061
    //   1014: aload_0
    //   1015: aload_2
    //   1016: checkcast 483	mbinc12/mb32b/MainPage
    //   1019: new 306	apm
    //   1022: dup
    //   1023: ldc_w 1462
    //   1026: aload_2
    //   1027: invokevirtual 137	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   1030: ldc_w 1463
    //   1033: invokevirtual 178	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   1036: ldc 123
    //   1038: ldc 123
    //   1040: ldc_w 280
    //   1043: ldc 123
    //   1045: ldc 123
    //   1047: iconst_0
    //   1048: invokespecial 314	apm:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    //   1051: invokevirtual 1184	mbinc12/mb32b/MainPage:b	(Lapm;)Laqc;
    //   1054: invokeinterface 879 2 0
    //   1059: pop
    //   1060: return
    //   1061: aload_1
    //   1062: ldc_w 1465
    //   1065: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1068: ifeq +128 -> 1196
    //   1071: aload_2
    //   1072: checkcast 483	mbinc12/mb32b/MainPage
    //   1075: getfield 540	mbinc12/mb32b/MainPage:an	Larz;
    //   1078: ifnull -1005 -> 73
    //   1081: aload_2
    //   1082: checkcast 483	mbinc12/mb32b/MainPage
    //   1085: getfield 540	mbinc12/mb32b/MainPage:an	Larz;
    //   1088: invokevirtual 1111	arz:a	()Z
    //   1091: ifeq -1018 -> 73
    //   1094: new 207	java/lang/StringBuilder
    //   1097: dup
    //   1098: ldc_w 1467
    //   1101: invokespecial 212	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1104: aload_2
    //   1105: checkcast 483	mbinc12/mb32b/MainPage
    //   1108: getfield 540	mbinc12/mb32b/MainPage:an	Larz;
    //   1111: invokevirtual 1469	arz:d	()Ljava/lang/String;
    //   1114: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1117: ldc_w 1471
    //   1120: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1123: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1126: astore_1
    //   1127: aload_0
    //   1128: aload_2
    //   1129: checkcast 483	mbinc12/mb32b/MainPage
    //   1132: new 306	apm
    //   1135: dup
    //   1136: ldc_w 512
    //   1139: aload_2
    //   1140: invokevirtual 137	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   1143: ldc_w 1472
    //   1146: invokevirtual 178	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   1149: ldc 123
    //   1151: ldc 123
    //   1153: new 207	java/lang/StringBuilder
    //   1156: dup
    //   1157: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   1160: aload_2
    //   1161: checkcast 483	mbinc12/mb32b/MainPage
    //   1164: getfield 540	mbinc12/mb32b/MainPage:an	Larz;
    //   1167: ldc_w 512
    //   1170: invokevirtual 1474	arz:b	(Ljava/lang/String;)I
    //   1173: invokevirtual 384	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1176: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1179: ldc 123
    //   1181: aload_1
    //   1182: iconst_0
    //   1183: invokespecial 314	apm:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    //   1186: invokevirtual 1184	mbinc12/mb32b/MainPage:b	(Lapm;)Laqc;
    //   1189: invokeinterface 879 2 0
    //   1194: pop
    //   1195: return
    //   1196: aload_1
    //   1197: ldc_w 1476
    //   1200: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1203: ifeq +355 -> 1558
    //   1206: aload_2
    //   1207: checkcast 483	mbinc12/mb32b/MainPage
    //   1210: getfield 1478	mbinc12/mb32b/MainPage:C	Ljava/util/List;
    //   1213: astore 6
    //   1215: getstatic 105	mbinc12/mb32b/utils/MixerBoxUtils:c	Lapn;
    //   1218: ifnonnull +129 -> 1347
    //   1221: aload_2
    //   1222: checkcast 483	mbinc12/mb32b/MainPage
    //   1225: getfield 540	mbinc12/mb32b/MainPage:an	Larz;
    //   1228: astore_1
    //   1229: aload_1
    //   1230: getfield 1481	arz:a	Landroid/database/sqlite/SQLiteDatabase;
    //   1233: invokevirtual 1486	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   1236: ifeq +580 -> 1816
    //   1239: aload_1
    //   1240: getfield 1481	arz:a	Landroid/database/sqlite/SQLiteDatabase;
    //   1243: ldc_w 1488
    //   1246: aconst_null
    //   1247: invokevirtual 1492	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   1250: astore 7
    //   1252: aload 7
    //   1254: invokeinterface 1117 1 0
    //   1259: ifle +289 -> 1548
    //   1262: aload 7
    //   1264: invokeinterface 1120 1 0
    //   1269: pop
    //   1270: new 342	apn
    //   1273: dup
    //   1274: aload 7
    //   1276: iconst_1
    //   1277: invokeinterface 1121 2 0
    //   1282: aload 7
    //   1284: iconst_2
    //   1285: invokeinterface 1121 2 0
    //   1290: aload 7
    //   1292: iconst_3
    //   1293: invokeinterface 1121 2 0
    //   1298: aload 7
    //   1300: iconst_4
    //   1301: invokeinterface 1121 2 0
    //   1306: aload 7
    //   1308: iconst_5
    //   1309: invokeinterface 1124 2 0
    //   1314: aload 7
    //   1316: bipush 6
    //   1318: invokeinterface 1124 2 0
    //   1323: aload 7
    //   1325: bipush 7
    //   1327: invokeinterface 1121 2 0
    //   1332: invokespecial 345	apn:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;)V
    //   1335: astore_1
    //   1336: aload 7
    //   1338: invokeinterface 1130 1 0
    //   1343: aload_1
    //   1344: putstatic 105	mbinc12/mb32b/utils/MixerBoxUtils:c	Lapn;
    //   1347: getstatic 107	mbinc12/mb32b/utils/MixerBoxUtils:d	Ljava/lang/String;
    //   1350: ifnonnull +10 -> 1360
    //   1353: aload_2
    //   1354: invokestatic 1495	asa:bh	(Landroid/content/Context;)Ljava/lang/String;
    //   1357: putstatic 107	mbinc12/mb32b/utils/MixerBoxUtils:d	Ljava/lang/String;
    //   1360: getstatic 109	mbinc12/mb32b/utils/MixerBoxUtils:e	Ljava/lang/String;
    //   1363: ifnonnull +10 -> 1373
    //   1366: aload_2
    //   1367: invokestatic 1498	asa:bi	(Landroid/content/Context;)Ljava/lang/String;
    //   1370: putstatic 109	mbinc12/mb32b/utils/MixerBoxUtils:e	Ljava/lang/String;
    //   1373: aload_2
    //   1374: invokevirtual 137	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   1377: ldc_w 1499
    //   1380: invokevirtual 178	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   1383: astore 7
    //   1385: new 274	org/json/JSONObject
    //   1388: dup
    //   1389: invokespecial 426	org/json/JSONObject:<init>	()V
    //   1392: astore_1
    //   1393: aload_1
    //   1394: ldc_w 407
    //   1397: ldc_w 1174
    //   1400: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1403: pop
    //   1404: aload_1
    //   1405: ldc_w 308
    //   1408: aload 7
    //   1410: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1413: pop
    //   1414: aload 6
    //   1416: aload_1
    //   1417: aload_2
    //   1418: iconst_0
    //   1419: iconst_0
    //   1420: invokestatic 1177	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/util/List;Lorg/json/JSONObject;Landroid/content/Context;II)V
    //   1423: aload 6
    //   1425: aload 6
    //   1427: invokeinterface 1268 1 0
    //   1432: iconst_1
    //   1433: isub
    //   1434: invokeinterface 1274 2 0
    //   1439: checkcast 1347	apt
    //   1442: putstatic 111	mbinc12/mb32b/utils/MixerBoxUtils:f	Lapt;
    //   1445: new 274	org/json/JSONObject
    //   1448: dup
    //   1449: invokespecial 426	org/json/JSONObject:<init>	()V
    //   1452: astore_1
    //   1453: getstatic 105	mbinc12/mb32b/utils/MixerBoxUtils:c	Lapn;
    //   1456: ifnull +17 -> 1473
    //   1459: aload_1
    //   1460: ldc_w 428
    //   1463: getstatic 105	mbinc12/mb32b/utils/MixerBoxUtils:c	Lapn;
    //   1466: invokevirtual 431	apn:a	()Lorg/json/JSONObject;
    //   1469: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1472: pop
    //   1473: getstatic 107	mbinc12/mb32b/utils/MixerBoxUtils:d	Ljava/lang/String;
    //   1476: ifnull +14 -> 1490
    //   1479: aload_1
    //   1480: ldc_w 437
    //   1483: getstatic 107	mbinc12/mb32b/utils/MixerBoxUtils:d	Ljava/lang/String;
    //   1486: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1489: pop
    //   1490: getstatic 109	mbinc12/mb32b/utils/MixerBoxUtils:e	Ljava/lang/String;
    //   1493: ifnull +14 -> 1507
    //   1496: aload_1
    //   1497: ldc_w 439
    //   1500: getstatic 109	mbinc12/mb32b/utils/MixerBoxUtils:e	Ljava/lang/String;
    //   1503: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1506: pop
    //   1507: aload_1
    //   1508: ldc_w 407
    //   1511: ldc_w 441
    //   1514: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1517: pop
    //   1518: new 443	apq
    //   1521: dup
    //   1522: aload_2
    //   1523: aload_1
    //   1524: invokespecial 1500	apq:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   1527: astore_1
    //   1528: aload_1
    //   1529: putstatic 113	mbinc12/mb32b/utils/MixerBoxUtils:g	Lapq;
    //   1532: aload_1
    //   1533: invokevirtual 448	apq:c	()V
    //   1536: aload 6
    //   1538: getstatic 113	mbinc12/mb32b/utils/MixerBoxUtils:g	Lapq;
    //   1541: invokeinterface 879 2 0
    //   1546: pop
    //   1547: return
    //   1548: aload 7
    //   1550: invokeinterface 1130 1 0
    //   1555: goto +261 -> 1816
    //   1558: aload_1
    //   1559: ldc_w 1502
    //   1562: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1565: ifeq +16 -> 1581
    //   1568: aload_2
    //   1569: aload_2
    //   1570: checkcast 483	mbinc12/mb32b/MainPage
    //   1573: getfield 1478	mbinc12/mb32b/MainPage:C	Ljava/util/List;
    //   1576: iconst_1
    //   1577: invokestatic 1504	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/util/List;Z)V
    //   1580: return
    //   1581: aload_1
    //   1582: ldc_w 1506
    //   1585: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1588: ifeq +16 -> 1604
    //   1591: aload_2
    //   1592: aload_2
    //   1593: checkcast 483	mbinc12/mb32b/MainPage
    //   1596: getfield 1478	mbinc12/mb32b/MainPage:C	Ljava/util/List;
    //   1599: iconst_0
    //   1600: invokestatic 1504	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/util/List;Z)V
    //   1603: return
    //   1604: aload_1
    //   1605: ldc_w 1508
    //   1608: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1611: ifeq +57 -> 1668
    //   1614: aload_2
    //   1615: checkcast 483	mbinc12/mb32b/MainPage
    //   1618: getfield 1478	mbinc12/mb32b/MainPage:C	Ljava/util/List;
    //   1621: astore_1
    //   1622: aload_2
    //   1623: invokestatic 1510	asa:an	(Landroid/content/Context;)Z
    //   1626: ifne -1553 -> 73
    //   1629: new 274	org/json/JSONObject
    //   1632: dup
    //   1633: invokespecial 426	org/json/JSONObject:<init>	()V
    //   1636: astore 6
    //   1638: aload 6
    //   1640: ldc_w 407
    //   1643: ldc_w 1508
    //   1646: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1649: pop
    //   1650: aload_1
    //   1651: new 1512	apy
    //   1654: dup
    //   1655: aload_2
    //   1656: aload 6
    //   1658: invokespecial 1513	apy:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   1661: invokeinterface 879 2 0
    //   1666: pop
    //   1667: return
    //   1668: aload_1
    //   1669: ldc_w 1515
    //   1672: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1675: ifeq +84 -> 1759
    //   1678: new 274	org/json/JSONObject
    //   1681: dup
    //   1682: invokespecial 426	org/json/JSONObject:<init>	()V
    //   1685: astore_1
    //   1686: aload_1
    //   1687: ldc_w 1517
    //   1690: aload_2
    //   1691: invokevirtual 137	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   1694: ldc_w 1518
    //   1697: invokevirtual 178	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   1700: invokevirtual 435	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1703: pop
    //   1704: getstatic 115	mbinc12/mb32b/utils/MixerBoxUtils:h	Lapv;
    //   1707: ifnonnull +15 -> 1722
    //   1710: new 1520	apv
    //   1713: dup
    //   1714: aload_2
    //   1715: aload_1
    //   1716: invokespecial 1521	apv:<init>	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   1719: putstatic 115	mbinc12/mb32b/utils/MixerBoxUtils:h	Lapv;
    //   1722: getstatic 115	mbinc12/mb32b/utils/MixerBoxUtils:h	Lapv;
    //   1725: astore_1
    //   1726: aload_2
    //   1727: checkcast 483	mbinc12/mb32b/MainPage
    //   1730: getfield 1524	mbinc12/mb32b/MainPage:L	Ljava/util/ArrayList;
    //   1733: invokevirtual 1144	java/util/ArrayList:size	()I
    //   1736: ifne +85 -> 1821
    //   1739: iconst_1
    //   1740: istore 5
    //   1742: aload_1
    //   1743: iload 5
    //   1745: putfield 1526	apv:a	Z
    //   1748: aload_0
    //   1749: getstatic 115	mbinc12/mb32b/utils/MixerBoxUtils:h	Lapv;
    //   1752: invokeinterface 879 2 0
    //   1757: pop
    //   1758: return
    //   1759: aload_0
    //   1760: new 1353	apu
    //   1763: dup
    //   1764: invokespecial 1354	apu:<init>	()V
    //   1767: invokeinterface 879 2 0
    //   1772: pop
    //   1773: return
    //   1774: aload_0
    //   1775: new 1353	apu
    //   1778: dup
    //   1779: invokespecial 1354	apu:<init>	()V
    //   1782: invokeinterface 879 2 0
    //   1787: pop
    //   1788: return
    //   1789: astore 6
    //   1791: goto -87 -> 1704
    //   1794: astore 7
    //   1796: goto -146 -> 1650
    //   1799: astore 7
    //   1801: goto -283 -> 1518
    //   1804: astore 7
    //   1806: goto -392 -> 1414
    //   1809: astore_0
    //   1810: return
    //   1811: astore 6
    //   1813: goto -1750 -> 63
    //   1816: aconst_null
    //   1817: astore_1
    //   1818: goto -475 -> 1343
    //   1821: iconst_0
    //   1822: istore 5
    //   1824: goto -82 -> 1742
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1827	0	paramList	List<aqc>
    //   0	1827	1	paramJSONObject	JSONObject
    //   0	1827	2	paramContext	Context
    //   0	1827	3	paramInt1	int
    //   0	1827	4	paramInt2	int
    //   193	1630	5	bool	boolean
    //   39	1618	6	localObject1	Object
    //   1789	1	6	localJSONException1	JSONException
    //   1811	1	6	localJSONException2	JSONException
    //   1250	299	7	localObject2	Object
    //   1794	1	7	localJSONException3	JSONException
    //   1799	1	7	localJSONException4	JSONException
    //   1804	1	7	localJSONException5	JSONException
    // Exception table:
    //   from	to	target	type
    //   210	237	238	java/lang/Exception
    //   259	285	238	java/lang/Exception
    //   286	313	238	java/lang/Exception
    //   314	341	238	java/lang/Exception
    //   342	372	238	java/lang/Exception
    //   373	400	238	java/lang/Exception
    //   401	427	238	java/lang/Exception
    //   428	455	238	java/lang/Exception
    //   456	483	238	java/lang/Exception
    //   484	511	238	java/lang/Exception
    //   512	539	238	java/lang/Exception
    //   540	567	238	java/lang/Exception
    //   568	595	238	java/lang/Exception
    //   596	624	238	java/lang/Exception
    //   625	652	238	java/lang/Exception
    //   653	680	238	java/lang/Exception
    //   681	708	238	java/lang/Exception
    //   709	759	238	java/lang/Exception
    //   760	774	238	java/lang/Exception
    //   775	802	238	java/lang/Exception
    //   803	835	238	java/lang/Exception
    //   836	878	238	java/lang/Exception
    //   879	921	238	java/lang/Exception
    //   922	936	238	java/lang/Exception
    //   937	964	238	java/lang/Exception
    //   965	1060	238	java/lang/Exception
    //   1061	1195	238	java/lang/Exception
    //   1196	1343	238	java/lang/Exception
    //   1343	1347	238	java/lang/Exception
    //   1347	1360	238	java/lang/Exception
    //   1360	1373	238	java/lang/Exception
    //   1373	1393	238	java/lang/Exception
    //   1393	1414	238	java/lang/Exception
    //   1414	1453	238	java/lang/Exception
    //   1453	1473	238	java/lang/Exception
    //   1473	1490	238	java/lang/Exception
    //   1490	1507	238	java/lang/Exception
    //   1507	1518	238	java/lang/Exception
    //   1518	1547	238	java/lang/Exception
    //   1548	1555	238	java/lang/Exception
    //   1558	1580	238	java/lang/Exception
    //   1581	1603	238	java/lang/Exception
    //   1604	1638	238	java/lang/Exception
    //   1638	1650	238	java/lang/Exception
    //   1650	1667	238	java/lang/Exception
    //   1668	1686	238	java/lang/Exception
    //   1686	1704	238	java/lang/Exception
    //   1704	1722	238	java/lang/Exception
    //   1722	1739	238	java/lang/Exception
    //   1742	1758	238	java/lang/Exception
    //   1759	1773	238	java/lang/Exception
    //   1774	1788	238	java/lang/Exception
    //   1686	1704	1789	org/json/JSONException
    //   1638	1650	1794	org/json/JSONException
    //   1453	1473	1799	org/json/JSONException
    //   1473	1490	1799	org/json/JSONException
    //   1490	1507	1799	org/json/JSONException
    //   1507	1518	1799	org/json/JSONException
    //   1393	1414	1804	org/json/JSONException
    //   201	210	1809	org/json/JSONException
    //   0	63	1811	org/json/JSONException
    //   74	96	1811	org/json/JSONException
    //   99	121	1811	org/json/JSONException
    //   124	146	1811	org/json/JSONException
    //   149	171	1811	org/json/JSONException
    //   174	195	1811	org/json/JSONException
  }
  
  public static void a(boolean paramBoolean)
  {
    if (h != null) {
      h.a = paramBoolean;
    }
  }
  
  public static boolean a(Context paramContext, JSONArray paramJSONArray)
  {
    boolean bool2 = true;
    if (paramJSONArray != null) {
      try
      {
        if (paramJSONArray.length() != 0)
        {
          paramContext = paramContext.getPackageManager().getInstalledApplications(128);
          int i2 = 0;
          bool1 = bool2;
          if (i2 >= paramJSONArray.length()) {
            break label117;
          }
          Iterator localIterator = paramContext.iterator();
          int i1 = 0;
          label52:
          if (localIterator.hasNext())
          {
            bool1 = ((ApplicationInfo)localIterator.next()).packageName.equals(paramJSONArray.getString(i2));
            if (!bool1) {
              break label111;
            }
            i1 = 1;
          }
          for (;;)
          {
            break label52;
            if (i1 == 0) {
              return false;
            }
            i2 += 1;
            break;
          }
        }
      }
      catch (Exception paramContext)
      {
        return false;
      }
    }
    label111:
    boolean bool1 = false;
    label117:
    return bool1;
  }
  
  public static boolean a(apm paramApm)
  {
    if (paramApm == null) {}
    while ((!paramApm.a.equals("PLAYLISTMP3ID")) && (!paramApm.a.equals("PLAYLISTMP3ALBUMID")) && (!paramApm.a.equals("PLAYLISTMP3ARTISTID")) && (!paramApm.a.equals("PLAYLISTMP3PLAYLISTID"))) {
      return false;
    }
    return true;
  }
  
  private static int b(int paramInt)
  {
    if (paramInt == 1) {}
    do
    {
      do
      {
        return 2130837586;
      } while ((paramInt == 2) || (paramInt == 3));
      if (paramInt == 4) {
        return 2130837587;
      }
    } while ((paramInt == 5) || (paramInt == 6));
    if (paramInt == 7) {
      return 2130837585;
    }
    return 2130837585;
  }
  
  public static void b()
  {
    if ((f == null) || (g == null)) {
      return;
    }
    if (c != null) {}
    for (int i1 = 1; i1 == 0; i1 = 0)
    {
      f.b = false;
      g.b = false;
      return;
    }
    f.b = true;
    g.b = true;
  }
  
  public static void b(final Context paramContext)
  {
    if ((((MainPage)paramContext).am) && (((MainPage)paramContext).an != null) && (((MainPage)paramContext).an.a()))
    {
      Cursor localCursor = ((MainPage)paramContext).an.a.rawQuery("SELECT * FROM tablePlaylist WHERE TYPE=2", null);
      final int i1 = localCursor.getCount();
      localCursor.moveToFirst();
      i1 -= 1;
      while (i1 >= 0)
      {
        final String str = localCursor.getString(1);
        arx.a(arx.j(str), new apj(paramContext)
        {
          public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
          {
            super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
            if (i1 <= 1) {
              MixerBoxUtils.a(paramContext, false);
            }
          }
          
          public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
          {
            super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
            paramAnonymousArrayOfXh = new HashMap();
            paramAnonymousArrayOfXh.put("id", str);
            paramAnonymousArrayOfXh.put("sub", "1");
            MixerBoxUtils.a("action:subscribe", paramAnonymousArrayOfXh);
            if (i1 <= 1) {
              MixerBoxUtils.a(paramContext, false);
            }
          }
        });
        localCursor.moveToNext();
        i1 -= 1;
      }
      localCursor.close();
      a(paramContext, false);
      return;
    }
    asa.b(paramContext, false);
  }
  
  public static void b(final Context paramContext, final String paramString)
  {
    arx.a(arx.j(paramString), new apj(paramContext)
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
        MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231011), 1, new boolean[0]);
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        paramAnonymousArrayOfXh = new HashMap();
        paramAnonymousArrayOfXh.put("id", paramString);
        paramAnonymousArrayOfXh.put("sub", "1");
        MixerBoxUtils.a("action:subscribe", paramAnonymousArrayOfXh);
        if (this.b)
        {
          ((MainPage)paramContext).k.setVisibility(0);
          MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131230881), 1, new boolean[0]);
          MixerBoxUtils.a(paramContext, false);
          asc.a(paramContext, "subPlaylist");
        }
      }
    });
  }
  
  public static void b(final Context paramContext, String paramString, final arn paramArn)
  {
    arx.a(arx.f(paramString), new apj(paramContext)
    {
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        int i = 0;
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        paramArn.a = new ArrayList();
        if (paramAnonymousArrayOfByte != null) {
          try
          {
            paramAnonymousArrayOfXh = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject("subsInfoV2").getJSONArray("items");
            paramAnonymousInt = i;
            while (paramAnonymousInt < paramAnonymousArrayOfXh.length())
            {
              paramAnonymousArrayOfByte = paramAnonymousArrayOfXh.getJSONObject(paramAnonymousInt);
              MixerBoxUtils.a(paramArn.a, paramAnonymousArrayOfByte, paramContext, paramAnonymousInt, paramAnonymousArrayOfXh.length() - paramAnonymousInt - 1);
              paramAnonymousInt += 1;
            }
            paramArn.a();
          }
          catch (JSONException paramAnonymousArrayOfXh)
          {
            paramAnonymousArrayOfXh.printStackTrace();
          }
        }
        for (;;)
        {
          return;
          MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231010), 1, new boolean[0]);
        }
      }
    });
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
    Object localObject = paramContext.getPackageManager().queryIntentActivities(paramString1, 0).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      if (localResolveInfo.activityInfo.applicationInfo.packageName.equals("com.android.vending"))
      {
        localObject = localResolveInfo.activityInfo;
        localObject = new ComponentName(((ActivityInfo)localObject).applicationInfo.packageName, ((ActivityInfo)localObject).name);
        paramString1.setFlags(270532608);
        paramString1.setComponent((ComponentName)localObject);
        paramContext.startActivity(paramString1);
      }
    }
    for (int i1 = 1;; i1 = 0)
    {
      if (i1 == 0) {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramString2)));
      }
      return;
    }
  }
  
  public static void b(Context paramContext, JSONArray paramJSONArray)
  {
    int i1 = 0;
    if (paramJSONArray == null) {
      return;
    }
    boolean bool;
    label48:
    ArrayList localArrayList;
    if ((paramContext != null) && ((paramContext instanceof MainPage)) && (((MainPage)paramContext).aB != null))
    {
      String str1 = ((MainPage)paramContext).aB;
      bool = ((MainPage)paramContext).aD;
      paramContext = str1;
      if (i1 >= paramJSONArray.length()) {
        break label188;
      }
      try
      {
        str1 = paramJSONArray.getJSONObject(i1).getString("id");
        localArrayList = new ArrayList();
        if (!paramJSONArray.getJSONObject(i1).isNull("arguments"))
        {
          JSONObject localJSONObject = paramJSONArray.getJSONObject(i1).getJSONObject("arguments");
          Iterator localIterator = localJSONObject.keys();
          while (localIterator.hasNext())
          {
            String str2 = (String)localIterator.next();
            if (!localJSONObject.isNull(str2))
            {
              localArrayList.add(new bl(str2, localJSONObject.getString(str2)));
              continue;
              i1 += 1;
            }
          }
        }
      }
      catch (Exception localException) {}
    }
    for (;;)
    {
      break label48;
      paramContext = asa.ai(paramContext);
      bool = false;
      break label48;
      label188:
      break;
      a(localException, localArrayList, paramContext, bool);
    }
  }
  
  public static void b(String paramString)
  {
    try
    {
      if (alc.c()) {
        ku.a(paramString);
      }
      return;
    }
    catch (Exception paramString) {}
  }
  
  public static AlertDialog c(Context paramContext, String paramString1, String paramString2)
  {
    return a(paramContext, paramString1, paramString2, true, false, null);
  }
  
  public static void c(final Context paramContext)
  {
    if ((((MainPage)paramContext).am) && (((MainPage)paramContext).an != null) && (((MainPage)paramContext).an.a()))
    {
      Cursor localCursor = ((MainPage)paramContext).an.b();
      int i1 = localCursor.getCount();
      localCursor.moveToFirst();
      i1 -= 1;
      while (i1 >= 0)
      {
        final int i2 = Integer.valueOf(i1).intValue();
        final String str = localCursor.getString(0);
        arx.a(arx.i(localCursor.getString(2)), new apj(paramContext)
        {
          public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
          {
            if (paramAnonymousArrayOfByte != null) {
              new String(paramAnonymousArrayOfByte);
            }
          }
          
          public final void onSuccess(final int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
          {
            super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
            paramAnonymousArrayOfXh = "";
            try
            {
              paramAnonymousArrayOfByte = new JSONObject(new String(paramAnonymousArrayOfByte)).getString("mChangePlaylist");
              paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
              localObject = ((MainPage)paramContext).an.b(str, true);
              paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
              if (((String)localObject).length() > 0)
              {
                paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
                ((MainPage)paramContext).an.b((String)localObject, str, paramAnonymousArrayOfByte);
              }
              paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
            }
            catch (JSONException paramAnonymousArrayOfByte)
            {
              Object localObject;
              for (;;)
              {
                int i;
                paramAnonymousArrayOfByte.printStackTrace();
                continue;
                paramAnonymousArrayOfByte.put("music[" + paramAnonymousInt + "][t]", "yt");
                paramAnonymousArrayOfByte.put("music[" + paramAnonymousInt + "][f]", ((Cursor)localObject).getString(4));
              }
              label326:
              ((Cursor)localObject).close();
              paramAnonymousInt = Integer.valueOf(i2).intValue();
              arx.a(arx.p(paramAnonymousArrayOfXh), paramAnonymousArrayOfByte, new AsyncHttpResponseHandler()
              {
                public final void onFailure(int paramAnonymous2Int, xh[] paramAnonymous2ArrayOfXh, byte[] paramAnonymous2ArrayOfByte, Throwable paramAnonymous2Throwable)
                {
                  if (paramAnonymous2ArrayOfByte != null) {
                    new StringBuilder("FAILURE ").append(paramAnonymous2Throwable.toString()).append(" / ").append(new String(paramAnonymous2ArrayOfByte));
                  }
                  if (paramAnonymousInt <= 2) {
                    MixerBoxUtils.a(MixerBoxUtils.19.this.a, false);
                  }
                }
                
                public final void onSuccess(int paramAnonymous2Int, xh[] paramAnonymous2ArrayOfXh, byte[] paramAnonymous2ArrayOfByte)
                {
                  if (paramAnonymousInt <= 2) {
                    MixerBoxUtils.a(MixerBoxUtils.19.this.a, false);
                  }
                }
              });
              return;
            }
            if ((((MainPage)paramContext).an != null) && (((MainPage)paramContext).an.a()))
            {
              paramAnonymousArrayOfByte = new RequestParams();
              localObject = ((MainPage)paramContext).an.a(str);
              i = ((Cursor)localObject).getCount();
              ((Cursor)localObject).moveToFirst();
              paramAnonymousInt = 0;
              for (;;)
              {
                if (paramAnonymousInt >= i) {
                  break label326;
                }
                if (((Cursor)localObject).getInt(6) != 3) {
                  break;
                }
                apn localApn = new apn("", ((Cursor)localObject).getString(2), ((Cursor)localObject).getString(3), ((Cursor)localObject).getString(4), paramAnonymousInt, 3, ((Cursor)localObject).getString(7));
                MixerBoxUtils.a(paramContext, paramAnonymousArrayOfXh, localApn, false, "");
                ((Cursor)localObject).moveToNext();
                paramAnonymousInt += 1;
              }
            }
            asa.b(paramContext, false);
          }
        });
        localCursor.moveToNext();
        i1 -= 1;
      }
      localCursor.close();
      a(paramContext, false);
      return;
    }
    asa.b(paramContext, false);
  }
  
  public static void c(final Context paramContext, final String paramString)
  {
    arx.a(arx.k(paramString), new apj(paramContext)
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
        MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231011), 1, new boolean[0]);
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        paramAnonymousArrayOfXh = new HashMap();
        paramAnonymousArrayOfXh.put("id", paramString);
        paramAnonymousArrayOfXh.put("sub", "0");
        MixerBoxUtils.a("action:subscribe", paramAnonymousArrayOfXh);
        MixerBoxUtils.a(paramContext, paramContext.getResources().getString(2131231016), 1, new boolean[0]);
        ((MainPage)paramContext).k(paramString);
        ((MainPage)paramContext).s();
      }
    });
  }
  
  public static boolean c()
  {
    return (i != null) && (i.m.isShowing());
  }
  
  public static void d()
  {
    if ((i != null) && (i.m.isShowing())) {
      i.c();
    }
  }
  
  public static void d(final Context paramContext)
  {
    arx.a(arx.f(paramContext), new apj(paramContext)
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
        ((MainPage)paramContext).w.d.post(new Runnable()
        {
          public final void run()
          {
            ((MainPage)MixerBoxUtils.20.this.a).w.d.setRefreshing(false);
          }
        });
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        int i = 0;
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        if (paramAnonymousArrayOfByte == null)
        {
          ((MainPage)paramContext).A = new ArrayList();
          ((MainPage)paramContext).w.a();
          ((MainPage)paramContext).w.d.post(new Runnable()
          {
            public final void run()
            {
              ((MainPage)MixerBoxUtils.20.this.a).w.d.setRefreshing(false);
            }
          });
          return;
        }
        for (;;)
        {
          try
          {
            paramAnonymousArrayOfXh = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject("radioPage").getJSONArray("items");
            ((MainPage)paramContext).A = new ArrayList();
            paramAnonymousInt = i;
            JSONObject localJSONObject;
            if (paramAnonymousInt < paramAnonymousArrayOfXh.length())
            {
              if (!paramAnonymousArrayOfXh.isNull(paramAnonymousInt))
              {
                localJSONObject = paramAnonymousArrayOfXh.getJSONObject(paramAnonymousInt);
                MixerBoxUtils.a(((MainPage)paramContext).A, localJSONObject, paramContext, paramAnonymousInt, paramAnonymousArrayOfXh.length() - paramAnonymousInt - 1);
              }
            }
            else
            {
              if (!((MainPage)paramContext).am)
              {
                localJSONObject = new JSONObject(new String(paramAnonymousArrayOfByte));
                localJSONObject.put("type", "divider");
                localJSONObject.put("title", paramContext.getResources().getString(2131230884));
                MixerBoxUtils.a(((MainPage)paramContext).A, localJSONObject, paramContext, paramAnonymousArrayOfXh.length(), 0);
                paramAnonymousArrayOfByte = new JSONObject(new String(paramAnonymousArrayOfByte));
                paramAnonymousArrayOfByte.put("type", "radio_login");
                MixerBoxUtils.a(((MainPage)paramContext).A, paramAnonymousArrayOfByte, paramContext, paramAnonymousArrayOfXh.length(), 0);
              }
              ((MainPage)paramContext).w.a();
              ((MainPage)paramContext).w.d.post(new Runnable()
              {
                public final void run()
                {
                  ((MainPage)MixerBoxUtils.20.this.a).w.d.setRefreshing(false);
                }
              });
              return;
            }
          }
          catch (Exception paramAnonymousArrayOfXh)
          {
            paramAnonymousArrayOfXh.printStackTrace();
            return;
          }
          paramAnonymousInt += 1;
        }
      }
    });
  }
  
  public static void d(Context paramContext, String paramString)
  {
    try
    {
      String str = asa.v(paramContext);
      if ((paramString != null) && (!paramString.equals(str)) && (!paramString.equals("")))
      {
        arx.a(arx.r(paramString), null);
        asa.i(paramContext, paramString);
        asa.f(paramContext, j(paramContext));
        f(paramContext, "Register");
      }
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static int e(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[0], "is_music != 0", null, null);
      int i1 = paramContext.getCount();
      paramContext.close();
      return i1;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static String e()
  {
    long l1 = Calendar.getInstance().getTimeInMillis();
    String str = UUID.randomUUID().toString().replaceAll(" ", "");
    return str + "-" + String.valueOf(l1);
  }
  
  public static void e(Context paramContext, String paramString)
  {
    Object localObject;
    try
    {
      paramString = paramString.replace("mbappaction://", "");
      localObject = paramString.split("/")[0];
      if (!((String)localObject).equals("opentab")) {
        break label140;
      }
      paramString = paramString.split("/")[1];
      if (paramString.equals("playlist"))
      {
        ((MainPage)paramContext).u();
        return;
      }
      if (paramString.equals("search"))
      {
        ((MainPage)paramContext).w();
        return;
      }
    }
    catch (Exception paramString)
    {
      a(paramContext, paramContext.getResources().getString(2131231011), 0, new boolean[0]);
      return;
    }
    if (paramString.equals("dj"))
    {
      ((MainPage)paramContext).x();
      return;
    }
    if (paramString.equals("setting"))
    {
      ((MainPage)paramContext).y();
      return;
    }
    ((MainPage)paramContext).v();
    return;
    label140:
    if (((String)localObject).equals("playlist"))
    {
      paramString = paramString.split("/")[1];
      if (paramString.length() > 0) {
        ((MainPage)paramContext).a(paramString, false);
      }
    }
    else
    {
      if (((String)localObject).equals("openpage"))
      {
        paramString = paramString.split("/")[1];
        if (paramString.equals("coin"))
        {
          ((MainPage)paramContext).y();
          asc.a(paramContext, ((MainPage)paramContext).b(true));
          asc.g(paramContext);
          return;
        }
        if (paramString.equals("theme"))
        {
          ((MainPage)paramContext).y();
          ((MainPage)paramContext).z();
          return;
        }
        a(paramContext, paramContext.getResources().getString(2131230811), 0, new boolean[0]);
        return;
      }
      if (((String)localObject).equals("action"))
      {
        localObject = paramString.split("/")[1];
        boolean bool = ((String)localObject).equals("mutualpromo");
        if (bool)
        {
          try
          {
            localObject = new JSONObject(asc.c(paramContext)).getJSONObject("mutualPromo");
            ((JSONObject)localObject).getString("link");
            paramString = ((JSONObject)localObject).getJSONObject("text").getString("subject");
            localObject = ((JSONObject)localObject).getJSONObject("text").getString("message");
            if (k(paramContext))
            {
              paramString = new Intent("android.intent.action.SEND");
              paramString.setClassName("jp.naver.line.android", "jp.naver.line.android.activity.selectchat.SelectChatActivity");
              paramString.setType("text/plain");
              paramString.putExtra("android.intent.extra.TEXT", (String)localObject);
              paramContext.startActivity(paramString);
              return;
            }
          }
          catch (Exception paramString)
          {
            a(paramContext, paramContext.getResources().getString(2131231011), 0, new boolean[0]);
            return;
          }
          Intent localIntent = new Intent("android.intent.action.SEND");
          localIntent.setType("text/plain");
          localIntent.putExtra("android.intent.extra.SUBJECT", paramString);
          localIntent.putExtra("android.intent.extra.TEXT", (String)localObject);
          paramContext.startActivity(Intent.createChooser(localIntent, null));
          return;
        }
        if (((String)localObject).equals("redeem"))
        {
          paramString = paramString.split("/")[2];
          if (paramString.length() > 0) {
            asc.b(paramContext, paramString);
          }
        }
        else
        {
          if (((String)localObject).equals("web"))
          {
            paramString = paramString.replace("action/web/", "");
            localObject = new Intent("android.intent.action.VIEW");
            ((Intent)localObject).setData(Uri.parse(paramString));
            paramContext.startActivity((Intent)localObject);
            return;
          }
          a(paramContext, paramContext.getResources().getString(2131230811), 0, new boolean[0]);
        }
      }
      else
      {
        a(paramContext, paramContext.getResources().getString(2131230811), 0, new boolean[0]);
      }
    }
  }
  
  public static String f(Context paramContext)
  {
    if (l.equals("")) {}
    try
    {
      String str = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkCountryIso();
      if (str != null)
      {
        paramContext = str;
        if (str.length() != 0) {}
      }
      else
      {
        paramContext = Locale.getDefault().getCountry();
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = Locale.getDefault().getCountry();
      }
    }
    l = paramContext.toLowerCase(Locale.getDefault());
    return l;
  }
  
  public static void f(Context paramContext, String paramString)
  {
    boolean bool;
    if ((paramContext != null) && ((paramContext instanceof MainPage)) && (((MainPage)paramContext).aB != null))
    {
      String str = ((MainPage)paramContext).aB;
      bool = ((MainPage)paramContext).aD;
      paramContext = str;
    }
    for (;;)
    {
      a(paramString, new ArrayList(), paramContext, bool);
      return;
      paramContext = asa.ai(paramContext);
      bool = false;
    }
  }
  
  public static void g(Context paramContext)
  {
    try
    {
      paramContext.startActivity(a(paramContext.getPackageManager(), "https://www.facebook.com/MixerBox"));
      return;
    }
    catch (Exception localException)
    {
      a(paramContext, paramContext.getResources().getString(2131231010), 0, new boolean[0]);
    }
  }
  
  public static void g(Context paramContext, String paramString)
  {
    long l1 = Calendar.getInstance().getTimeInMillis();
    long l2 = asa.as(paramContext);
    Object localObject = Calendar.getInstance(TimeZone.getTimeZone("GMT+0"));
    ((Calendar)localObject).setTimeInMillis(l1);
    Calendar localCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+0"));
    localCalendar.setTimeInMillis(l2);
    if (!(((Calendar)localObject).get(2) + "/" + ((Calendar)localObject).get(5)).equals(localCalendar.get(2) + "/" + localCalendar.get(5)))
    {
      l2 = asa.u(paramContext);
      localObject = String.valueOf((int)((Calendar.getInstance().getTimeInMillis() - l2) / 86400000L));
      if (l2 == 0L) {
        localObject = "OLD";
      }
      arx.a(arx.a(paramString, (String)localObject, asa.l(paramContext)), null);
      asa.e(paramContext, l1);
    }
  }
  
  public static void h(Context paramContext)
  {
    int i2 = paramContext.getResources().getColor(2131558412);
    int i1 = 0;
    if (i1 < asb.a.length) {
      if (!asa.W(paramContext).equals(asb.a[i1])) {}
    }
    for (i1 = asb.b[i1];; i1 = i2)
    {
      k = new PorterDuffColorFilter(i1, PorterDuff.Mode.MULTIPLY);
      return;
      i1 += 1;
      break;
    }
  }
  
  public static void h(Context paramContext, String paramString)
  {
    if (n != null) {
      n.cancel();
    }
    paramContext = Toast.makeText(paramContext, paramString, 0);
    n = paramContext;
    paramContext.show();
  }
  
  public static PorterDuffColorFilter i(Context paramContext)
  {
    if (k == null) {
      h(paramContext);
    }
    return k;
  }
  
  private static int j(Context paramContext)
  {
    try
    {
      int i1 = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new RuntimeException("Could not get package name: " + paramContext);
    }
  }
  
  private static boolean k(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals("jp.naver.line.android")) {
        return true;
      }
    }
    return false;
  }
  
  static class NoUnderlineSpan
    extends UnderlineSpan
  {
    public NoUnderlineSpan() {}
    
    public void updateDrawState(TextPaint paramTextPaint)
    {
      paramTextPaint.setUnderlineText(false);
    }
  }
  
  static final class a
    extends ClickableSpan
  {
    private aqq a;
    
    public a(aqq paramAqq)
    {
      this.a = paramAqq;
    }
    
    public final void onClick(View paramView)
    {
      this.a.onClick(paramView);
    }
  }
  
  static final class b
    extends AsyncTask<String, Integer, Void>
  {
    Context a;
    boolean b;
    int c;
    int d;
    boolean e;
    SongFragment f;
    int g;
    ArrayList<aqc> h;
    
    public b(Context paramContext, boolean paramBoolean, int paramInt1, int paramInt2, SongFragment paramSongFragment, int paramInt3)
    {
      this.a = paramContext;
      this.b = paramBoolean;
      this.c = paramInt1;
      this.d = paramInt2;
      this.e = false;
      this.f = paramSongFragment;
      this.g = paramInt3;
    }
    
    private Void a(String... paramVarArgs)
    {
      if (paramVarArgs[0] == null)
      {
        this.e = true;
        return null;
      }
      new ArrayList();
      ArrayList localArrayList = new ArrayList(MixerBoxUtils.a(paramVarArgs[0], this.f));
      this.h = new ArrayList();
      try
      {
        paramVarArgs = new JSONObject(paramVarArgs[0]).getJSONObject("getVector").getJSONArray("items");
        int i = paramVarArgs.length() - 1;
        while (i >= 0)
        {
          MixerBoxUtils.a(this.h, paramVarArgs.getJSONObject(i), this.a, i, paramVarArgs.length() - i - 1);
          i -= 1;
        }
        return null;
      }
      catch (Exception paramVarArgs)
      {
        ((MainPage)this.a).E = new ArrayList(localArrayList);
        this.e = false;
      }
    }
    
    protected final void onPreExecute()
    {
      super.onPreExecute();
    }
  }
}
