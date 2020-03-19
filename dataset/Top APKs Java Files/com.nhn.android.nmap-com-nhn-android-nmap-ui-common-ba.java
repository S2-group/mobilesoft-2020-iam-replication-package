package com.nhn.android.nmap.ui.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.nhn.android.c.k;
import com.nhn.android.c.r;
import com.nhn.android.g.u;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.nmap.data.fo;
import com.nhn.android.nmap.data.fs;
import com.nhn.android.nmap.data.jh;
import com.nhn.android.nmap.data.jr;
import com.nhn.android.nmap.data.js;
import com.nhn.android.nmap.data.ju;
import com.nhn.android.nmap.data.kh;
import com.nhn.android.nmap.data.ki;
import com.nhn.android.nmap.model.IndoorInfo;
import com.nhn.android.nmap.model.SubwayStationDetailInfo;
import com.nhn.android.nmap.model.UIModel.SearchResultItemModel;
import com.nhn.android.nmap.model.bn;
import com.nhn.android.nmap.model.bo;
import com.nhn.android.nmap.model.bs;
import com.nhn.android.nmap.model.bx;
import com.nhn.android.nmap.model.eq;
import com.nhn.android.nmap.model.eu;
import com.nhn.android.nmap.model.gf;
import com.nhn.android.nmap.model.hb;
import com.nhn.android.nmap.model.hq;
import com.nhn.android.nmap.ui.a.ak;
import com.nhn.android.nmap.ui.a.cb;
import com.nhn.android.nmap.ui.pages.BasicPage;
import com.nhn.android.util.a;
import com.nhn.android.util.v;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class ba
{
  public static double a(double paramDouble, int paramInt)
  {
    if (paramDouble == 0.0D) {
      return 0.0D;
    }
    if (paramInt == 0) {
      return Math.round(paramDouble);
    }
    double d = Math.pow(10.0D, paramInt);
    return Math.round((int)d * paramDouble) / d;
  }
  
  public static float a(String paramString, float paramFloat)
  {
    return a(paramString, paramFloat, null);
  }
  
  public static float a(String paramString, float paramFloat, Typeface paramTypeface)
  {
    Paint localPaint = new Paint();
    if (paramTypeface != null) {
      localPaint.setTypeface(paramTypeface);
    }
    localPaint.setTextSize(paramFloat);
    return localPaint.measureText(paramString);
  }
  
  public static int a()
  {
    if (!MapUIData.s) {
      return 2130840739;
    }
    return 2130840746;
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static int a(Context paramContext, View paramView)
  {
    int i = paramContext.getResources().getDisplayMetrics().heightPixels;
    paramContext = new Rect();
    paramView.getWindowVisibleDisplayFrame(paramContext);
    return i - paramContext.top;
  }
  
  public static int a(int... paramVarArgs)
  {
    int j = Integer.MIN_VALUE;
    int i = 0;
    while (i < paramVarArgs.length)
    {
      int k = j;
      if (j < paramVarArgs[i]) {
        k = paramVarArgs[i];
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  public static Bitmap a(View paramView)
    throws Exception
  {
    paramView = paramView.getRootView();
    paramView.setDrawingCacheEnabled(true);
    try
    {
      paramView = paramView.getDrawingCache();
      return paramView;
    }
    catch (Exception paramView)
    {
      throw paramView;
    }
  }
  
  public static Pair<String, String> a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if ((!MapUIData.s) && (!TextUtils.isEmpty(paramString2))) {
      return new Pair(paramString2, paramString3);
    }
    return new Pair(paramString1, paramString4);
  }
  
  public static ab a(Intent paramIntent)
  {
    ab localAb = (ab)paramIntent.getSerializableExtra("_pageFrom");
    paramIntent = localAb;
    if (localAb == null) {
      paramIntent = ab.a;
    }
    return paramIntent;
  }
  
  public static ad a(Intent paramIntent, ad paramAd)
  {
    paramIntent = (ad)paramIntent.getSerializableExtra("mapDirect");
    if (paramIntent == null) {
      return paramAd;
    }
    return paramIntent;
  }
  
  private static Runnable a(final Activity paramActivity, int paramInt, final ki paramKi)
  {
    new Runnable()
    {
      public void run()
      {
        kh localKh = new kh();
        localKh.a(this.a, paramKi);
        ak.a().a(paramActivity, cb.a().b(), localKh);
      }
    };
  }
  
  public static String a(double paramDouble)
  {
    return a(paramDouble, "0.##");
  }
  
  public static String a(double paramDouble, String paramString)
  {
    if (paramDouble < 0.0D) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramString = new DecimalFormat(paramString);
    if (paramDouble >= 1000.0D)
    {
      localStringBuilder.append(paramString.format(paramDouble / 1000.0D));
      localStringBuilder.append("km");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(paramString.format(paramDouble));
      localStringBuilder.append("m");
    }
  }
  
  public static String a(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramInt / 60;
    int j = paramInt - i * 60;
    if (paramInt <= 0) {
      localStringBuilder.append("시간정보 없음");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      if (i == 0)
      {
        localStringBuilder.append("약 ").append(j).append("분");
      }
      else
      {
        localStringBuilder.append("약 ").append(i).append("시간");
        if (j > 0)
        {
          localStringBuilder.append(" ");
          localStringBuilder.append(j).append("분");
        }
      }
    }
  }
  
  public static String a(int paramInt, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = v.a(paramInt);
    if (paramInt <= 0) {
      localStringBuilder.append("요금정보 없음");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      if (paramBoolean) {
        localStringBuilder.append("약 ");
      }
      localStringBuilder.append(str).append("원");
    }
  }
  
  public static String a(Context paramContext, long paramLong)
  {
    if (DateFormat.is24HourFormat(paramContext)) {}
    for (paramContext = "HH:mm";; paramContext = "aa hh:mm") {
      return new SimpleDateFormat(paramContext).format(new Date(paramLong));
    }
  }
  
  public static String a(bs paramBs, int paramInt)
  {
    if (paramBs != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      switch (paramInt)
      {
      default: 
        localStringBuilder.append(paramBs.a());
      }
      for (;;)
      {
        return localStringBuilder.toString();
        if (!TextUtils.isEmpty(paramBs.a)) {
          localStringBuilder.append(paramBs.a);
        }
      }
    }
    return "";
  }
  
  public static String a(Object paramObject)
  {
    jr localJr = null;
    Object localObject;
    int i;
    if ((paramObject instanceof UIModel.SearchResultItemModel))
    {
      localObject = (UIModel.SearchResultItemModel)paramObject;
      if (((UIModel.SearchResultItemModel)localObject).b == 2)
      {
        i = 1;
        if (i == 0) {
          break label121;
        }
        if (((UIModel.SearchResultItemModel)localObject).g == null) {
          break label113;
        }
        paramObject = ((UIModel.SearchResultItemModel)localObject).g;
        label40:
        localJr = new jr();
        localJr.a(((UIModel.SearchResultItemModel)localObject).m, ((UIModel.SearchResultItemModel)localObject).l);
        localJr.a(9);
        localJr.b(paramObject);
        localObject = fo.a(localJr, true);
      }
    }
    for (;;)
    {
      paramObject = localObject;
      if (localObject != null)
      {
        paramObject = localObject;
        if (((String)localObject).contains("http://map.naver.com/")) {
          paramObject = ((String)localObject).replace("http://map.naver.com/", "navermaps://");
        }
      }
      return paramObject;
      i = 0;
      break;
      label113:
      paramObject = ((UIModel.SearchResultItemModel)localObject).f;
      break label40;
      label121:
      i = ((UIModel.SearchResultItemModel)localObject).a;
      paramObject = new jr();
      paramObject.a(((UIModel.SearchResultItemModel)localObject).m, ((UIModel.SearchResultItemModel)localObject).l);
      paramObject.c(Integer.valueOf(i).toString());
      paramObject.a(11);
      paramObject.a(js.c);
      paramObject.b(((UIModel.SearchResultItemModel)localObject).f);
      localObject = fo.a(paramObject);
      continue;
      if ((paramObject instanceof eq))
      {
        paramObject = (eq)paramObject;
        localObject = localJr;
        if (paramObject != null)
        {
          new gf().b = cb.a(paramObject.d, paramObject.a, paramObject.e);
          localObject = localJr;
          if (paramObject != null)
          {
            localObject = new jh();
            ((jh)localObject).a(paramObject.b);
            localObject = fo.a((jh)localObject);
          }
        }
      }
      else if ((paramObject instanceof SubwayStationDetailInfo))
      {
        paramObject = (SubwayStationDetailInfo)paramObject;
        localObject = localJr;
        if (paramObject != null)
        {
          localObject = new gf();
          ((gf)localObject).b = paramObject.e;
          ((gf)localObject).c = paramObject.q;
          ((gf)localObject).d = paramObject.m;
          localObject = new kh();
          ((kh)localObject).a(paramObject.a.c, ki.c);
          localObject = fo.a((kh)localObject);
        }
      }
      else
      {
        localObject = localJr;
        if ((paramObject instanceof eu))
        {
          paramObject = (eu)paramObject;
          localObject = localJr;
          if (paramObject != null)
          {
            new gf().b = cb.a(paramObject.a, paramObject.c);
            localObject = new kh();
            ((kh)localObject).a(paramObject.b, ki.b);
            localObject = fo.a((kh)localObject);
          }
        }
      }
    }
  }
  
  public static String a(String paramString)
  {
    int i = 0;
    try
    {
      int j = Integer.parseInt(paramString);
      i = j;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return v.a(i);
  }
  
  public static String a(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (paramString2 != null)
    {
      str = paramString1;
      if (paramString2.length() > 0)
      {
        paramString1 = new StringBuilder(paramString1);
        paramString1.append(" ");
        paramString1.append(paramString2);
        str = paramString1.toString();
      }
    }
    return str;
  }
  
  public static <T> List<T> a(List<T> paramList)
  {
    Object localObject = paramList;
    if (paramList == null) {
      localObject = Collections.emptyList();
    }
    return localObject;
  }
  
  public static void a(Activity paramActivity)
  {
    a(paramActivity, 500);
  }
  
  public static void a(Activity paramActivity, int paramInt)
  {
    if ((paramActivity instanceof NMapActivity))
    {
      paramActivity = (NMapActivity)paramActivity;
      paramActivity.a(false);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          ba.this.a(true);
        }
      }, paramInt);
    }
    while (!(paramActivity instanceof BasicPage)) {
      return;
    }
    paramActivity = (BasicPage)paramActivity;
    paramActivity.c(false);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        ba.this.c(true);
      }
    }, paramInt);
  }
  
  private static void a(final Activity paramActivity, SubwayStationDetailInfo paramSubwayStationDetailInfo)
  {
    if (paramSubwayStationDetailInfo != null)
    {
      gf localGf = new gf();
      localGf.b = paramSubwayStationDetailInfo.e;
      localGf.h = new NGeoPoint(paramSubwayStationDetailInfo.b, paramSubwayStationDetailInfo.c);
      localGf.c = paramSubwayStationDetailInfo.q;
      localGf.d = paramSubwayStationDetailInfo.m;
      paramSubwayStationDetailInfo = new Runnable()
      {
        public void run()
        {
          kh localKh = new kh();
          localKh.a(ba.this.a.c, ki.c);
          ak.a().a(paramActivity, cb.a().b(), localKh);
        }
      };
      cb localCb = cb.a();
      localCb.a(paramSubwayStationDetailInfo);
      localCb.a(paramActivity, localGf, true);
    }
  }
  
  private static void a(final Activity paramActivity, UIModel.SearchResultItemModel paramSearchResultItemModel, boolean paramBoolean)
  {
    gf localGf = new gf();
    int i;
    Object localObject;
    if (paramSearchResultItemModel.b == 2)
    {
      i = 1;
      if (i == 0) {
        break label110;
      }
      if (paramSearchResultItemModel.g == null) {
        break label101;
      }
      localObject = paramSearchResultItemModel.g;
      label36:
      localGf.b = ((String)localObject);
      localGf.h = new NGeoPoint(paramSearchResultItemModel.l, paramSearchResultItemModel.m);
      paramSearchResultItemModel = new Runnable()
      {
        public void run()
        {
          jr localJr = new jr();
          localJr.a(ba.this.m, ba.this.l);
          localJr.a(9);
          localJr.b(this.b);
          ak.a().a(paramActivity, cb.a().b(), localJr, true);
        }
      };
    }
    for (;;)
    {
      localObject = cb.a();
      ((cb)localObject).a(paramSearchResultItemModel);
      ((cb)localObject).a(paramActivity, localGf, paramBoolean);
      return;
      i = 0;
      break;
      label101:
      localObject = paramSearchResultItemModel.f;
      break label36;
      label110:
      localGf.b = paramSearchResultItemModel.f;
      localGf.c = paramSearchResultItemModel.e();
      localGf.d = paramSearchResultItemModel.g;
      localGf.h = new NGeoPoint(paramSearchResultItemModel.l, paramSearchResultItemModel.m);
      switch (paramSearchResultItemModel.h())
      {
      default: 
        paramSearchResultItemModel = new Runnable()
        {
          public void run()
          {
            int i = ba.this.a;
            jr localJr = new jr();
            localJr.a(ba.this.m, ba.this.l);
            localJr.c(Integer.valueOf(i).toString());
            localJr.a(11);
            localJr.a(js.c);
            localJr.b(ba.this.f);
            if ((ba.this.w != null) && (ba.this.A == true))
            {
              IndoorInfo localIndoorInfo = ba.this.w;
              localJr.a(localIndoorInfo.a, localIndoorInfo.b, localIndoorInfo.a());
            }
            ak.a().a(paramActivity, cb.a().b(), localJr);
          }
        };
        break;
      case 2: 
        paramSearchResultItemModel = a(paramActivity, paramSearchResultItemModel.g(), ki.c);
        break;
      case 1: 
        localObject = new StringBuilder(paramSearchResultItemModel.f);
        ((StringBuilder)localObject).append("(버스정류장");
        String str = paramSearchResultItemModel.i();
        if (!TextUtils.isEmpty(str))
        {
          ((StringBuilder)localObject).append(", ");
          ((StringBuilder)localObject).append(str);
        }
        ((StringBuilder)localObject).append(")");
        localGf.b = ((StringBuilder)localObject).toString();
        localGf.d = null;
        paramSearchResultItemModel = a(paramActivity, paramSearchResultItemModel.g(), ki.b);
      }
    }
  }
  
  private static void a(final Activity paramActivity, eq paramEq)
  {
    if (paramEq != null)
    {
      gf localGf = new gf();
      localGf.b = cb.a(paramEq.d, paramEq.a, paramEq.e);
      paramEq = new Runnable()
      {
        public void run()
        {
          if (ba.this != null)
          {
            jh localJh = new jh();
            localJh.a(ba.this.b);
            ak.a().a(paramActivity, cb.a().b(), localJh);
          }
        }
      };
      cb localCb = cb.a();
      localCb.a(paramEq);
      localCb.a(paramActivity, localGf);
    }
  }
  
  private static void a(final Activity paramActivity, eu paramEu)
  {
    if (paramEu != null)
    {
      gf localGf = new gf();
      localGf.b = cb.a(paramEu.a, paramEu.c);
      paramEu = new Runnable()
      {
        public void run()
        {
          kh localKh = new kh();
          localKh.a(ba.this.b, ki.b);
          ak.a().a(paramActivity, cb.a().b(), localKh);
        }
      };
      cb localCb = cb.a();
      localCb.a(paramEu);
      localCb.a(paramActivity, localGf);
    }
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      StringBuilder localStringBuilder = new StringBuilder("http://m.search.naver.com/search.naver?where=m&query=");
      localStringBuilder.append(paramString);
      localStringBuilder.append("&sm=mob_map");
      localStringBuilder.append("&ie=utf8&qdt=1");
      paramString = localStringBuilder.toString();
      localStringBuilder.setLength(0);
      localStringBuilder.append(paramString);
      localStringBuilder.append("&target=new&version=6");
      paramString = new Intent();
      paramString.putExtra("url", localStringBuilder.toString());
      paramString.putExtra("inappbrowser", true);
      aw.a().a(paramActivity, aa.X, paramString, 0);
    }
  }
  
  public static void a(AlertDialog.Builder paramBuilder)
  {
    try
    {
      paramBuilder.show();
      return;
    }
    catch (Exception paramBuilder)
    {
      paramBuilder.printStackTrace();
    }
  }
  
  public static void a(AlertDialog paramAlertDialog, Context paramContext)
  {
    try
    {
      paramAlertDialog.show();
      a((Activity)paramContext);
      return;
    }
    catch (Exception paramAlertDialog)
    {
      for (;;)
      {
        paramAlertDialog.printStackTrace();
      }
    }
  }
  
  public static void a(Context paramContext)
  {
    String str1 = paramContext.getResources().getString(2131231630);
    String str2 = paramContext.getResources().getString(2131231627);
    String str3 = paramContext.getResources().getString(2131231628);
    a(new AlertDialog.Builder(paramContext).setIcon(17301543).setTitle(str1).setMessage(str3).setNeutralButton(str2, null).create(), paramContext);
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(2131231525);
    localBuilder.setMessage(paramInt);
    localBuilder.setIcon(17301659);
    localBuilder.setPositiveButton(2131231702, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        ba.this.startActivity(paramAnonymousDialogInterface);
      }
    });
    localBuilder.setNegativeButton(2131231435, null);
    a(localBuilder.create(), paramContext);
  }
  
  public static void a(Context paramContext, DialogInterface.OnClickListener paramOnClickListener)
  {
    String str1 = paramContext.getResources().getString(2131231630);
    String str2 = paramContext.getResources().getString(2131231627);
    String str3 = paramContext.getResources().getString(2131231629);
    a(new AlertDialog.Builder(paramContext).setIcon(17301543).setTitle(str1).setMessage(str3).setNeutralButton(str2, paramOnClickListener).create(), paramContext);
  }
  
  public static void a(Context paramContext, DialogInterface.OnClickListener paramOnClickListener, com.nhn.android.nmap.data.ab paramAb)
  {
    String str = paramContext.getResources().getString(2131231627);
    paramAb = x.a(paramAb);
    a(new AlertDialog.Builder(paramContext).setIcon(17301543).setTitle(null).setMessage(paramAb).setNeutralButton(str, paramOnClickListener).create(), paramContext);
  }
  
  public static void a(Context paramContext, Object paramObject, String paramString)
  {
    a(paramContext, paramObject, paramString, null);
  }
  
  public static void a(final Context paramContext, final Object paramObject, String paramString1, String paramString2)
  {
    SpannableStringBuilder localSpannableStringBuilder1 = new SpannableStringBuilder("전화걸기");
    SpannableStringBuilder localSpannableStringBuilder2 = new SpannableStringBuilder("연락처 저장");
    SpannableStringBuilder localSpannableStringBuilder3 = new SpannableStringBuilder("'네이버 주소록' 앱에 저장");
    localSpannableStringBuilder3.setSpan(new ForegroundColorSpan(Color.parseColor("#0097af")), 0, 9, 33);
    bd localBd = new bd(paramContext, 0);
    if (!a.a(localBd)) {
      return;
    }
    localBd.setTitle("연락처");
    localBd.a(paramString1);
    paramContext = new DialogInterface.OnClickListener()
    {
      private void a(String paramAnonymousString)
      {
        if (TextUtils.isEmpty(paramAnonymousString)) {
          return;
        }
        try
        {
          paramContext.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + paramAnonymousString)));
          fs.a("phn.call");
          return;
        }
        catch (ActivityNotFoundException paramAnonymousString)
        {
          for (;;)
          {
            ba.f(paramContext);
          }
        }
      }
      
      private void a(String paramAnonymousString, Object paramAnonymousObject)
      {
        ba.b(paramAnonymousString, paramAnonymousObject, (Activity)paramContext);
      }
      
      private void b(String paramAnonymousString, Object paramAnonymousObject)
      {
        ba.a(paramAnonymousString, paramAnonymousObject, (Activity)paramContext);
        fs.a("phn.save");
      }
      
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        String str = (String)((bd)paramAnonymousDialogInterface).d();
        switch (paramAnonymousInt)
        {
        }
        for (;;)
        {
          paramAnonymousDialogInterface.dismiss();
          return;
          if (!TextUtils.isEmpty(str))
          {
            ba.b(str, ba.this);
            a(str);
            continue;
            a(str, paramObject);
            continue;
            b(str, paramObject);
          }
        }
      }
    };
    localBd.a(new be()
    {
      public void a() {}
    });
    localBd.a(new SpannableStringBuilder[] { localSpannableStringBuilder1, localSpannableStringBuilder3, localSpannableStringBuilder2 }, paramContext);
    localBd.show();
  }
  
  public static void a(View paramView, boolean paramBoolean)
  {
    if (paramView == null) {
      return;
    }
    if (paramBoolean)
    {
      paramView.setVisibility(0);
      return;
    }
    paramView.setVisibility(8);
  }
  
  public static void a(final ju paramJu, Activity paramActivity)
  {
    gf localGf = new gf();
    localGf.b = paramJu.a;
    paramJu = new Runnable()
    {
      public void run()
      {
        ak.a().a(ba.this, cb.a().b(), paramJu);
      }
    };
    cb localCb = cb.a();
    localCb.a(paramJu);
    localCb.a(paramActivity, localGf);
  }
  
  public static void a(Object paramObject, Activity paramActivity)
  {
    a(paramObject, paramActivity, false);
  }
  
  public static void a(Object paramObject, Activity paramActivity, boolean paramBoolean)
  {
    if ((paramObject instanceof UIModel.SearchResultItemModel)) {
      a(paramActivity, (UIModel.SearchResultItemModel)paramObject, paramBoolean);
    }
    do
    {
      return;
      if ((paramObject instanceof eq))
      {
        a(paramActivity, (eq)paramObject);
        return;
      }
      if ((paramObject instanceof SubwayStationDetailInfo))
      {
        a(paramActivity, (SubwayStationDetailInfo)paramObject);
        return;
      }
    } while (!(paramObject instanceof eu));
    a(paramActivity, (eu)paramObject);
  }
  
  private static void a(Object paramObject, Intent paramIntent, Activity paramActivity)
  {
    if (a(paramActivity, "com.nhn.android.addressbookbackup", "2.2.1"))
    {
      paramObject = a(paramObject);
      if (paramObject != null)
      {
        paramIntent.putExtra("scrap-link", paramObject);
        paramIntent.putExtra("scrap-package", r.b());
        paramActivity.startActivity(paramIntent);
      }
      return;
    }
    paramObject = new AlertDialog.Builder(paramActivity);
    paramObject.setTitle("네이버 주소록앱 설치");
    paramObject.setPositiveButton("설치", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent("android.intent.action.VIEW", Uri.parse("http://market.android.com/details?id=com.nhn.android.addressbookbackup"));
        paramAnonymousDialogInterface.setFlags(268435456);
        ba.this.startActivity(paramAnonymousDialogInterface);
      }
    });
    paramObject.setNegativeButton("취소", null);
    paramObject.setMessage("최신 네이버 주소록앱을 설치해야 합니다. 설치하시겠습니까?");
    paramObject.show();
  }
  
  public static void a(String paramString, Object paramObject, Activity paramActivity)
  {
    int j = 0;
    if (!TextUtils.isEmpty(paramString))
    {
      if (!(paramObject instanceof bx)) {
        break label99;
      }
      paramObject = (bx)paramObject;
      localBn = bn.m();
      localArrayList = new ArrayList();
      localArrayList.add(new bo(0, paramObject.d));
      localArrayList.add(new bo(1, paramString));
      localArrayList.add(new bo(2, paramObject.g));
      paramActivity.startActivity(localBn.a(localArrayList));
    }
    label99:
    do
    {
      return;
      if ((paramObject instanceof UIModel.SearchResultItemModel))
      {
        paramObject = (UIModel.SearchResultItemModel)paramObject;
        localBn = bn.m();
        localArrayList = new ArrayList();
        localArrayList.add(new bo(0, paramObject.f));
        localArrayList.add(new bo(1, paramString));
        localArrayList.add(new bo(2, paramObject.g));
        paramActivity.startActivity(localBn.a(localArrayList));
        return;
      }
      if ((paramObject instanceof SubwayStationDetailInfo))
      {
        paramObject = (SubwayStationDetailInfo)paramObject;
        localBn = bn.m();
        localArrayList = new ArrayList();
        localArrayList.add(new bo(0, paramObject.e));
        paramString = e(paramString);
        if (paramString != null)
        {
          int i;
          if (paramString.size() > 3) {
            i = 3;
          }
          while (j < i)
          {
            localArrayList.add(new bo(1, (String)paramString.get(j)));
            j += 1;
            continue;
            i = paramString.size();
          }
        }
        localArrayList.add(new bo(2, paramObject.m));
        paramActivity.startActivity(localBn.a(localArrayList));
        return;
      }
      if ((paramObject instanceof hq))
      {
        paramObject = (hq)paramObject;
        localBn = bn.m();
        localArrayList = new ArrayList();
        localArrayList.add(new bo(0, paramObject.d));
        localArrayList.add(new bo(1, paramString));
        localArrayList.add(new bo(2, paramObject.j));
        paramActivity.startActivity(localBn.a(localArrayList));
        return;
      }
    } while (!(paramObject instanceof String));
    bn localBn = bn.m();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new bo(0, (String)paramObject));
    localArrayList.add(new bo(1, paramString));
    paramActivity.startActivity(localBn.a(localArrayList));
  }
  
  public static void a(ArrayList<ImageView> paramArrayList, float paramFloat, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 0;
    if (i < paramArrayList.size())
    {
      if (paramFloat >= 0.75D) {
        ((ImageView)paramArrayList.get(i)).setImageResource(paramInt1);
      }
      for (;;)
      {
        paramFloat -= 1.0F;
        i += 1;
        break;
        if (paramFloat >= 0.25D) {
          ((ImageView)paramArrayList.get(i)).setImageResource(paramInt2);
        } else {
          ((ImageView)paramArrayList.get(i)).setImageResource(paramInt3);
        }
      }
    }
  }
  
  private static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo.packageName.equals(paramString1) == true) && ((paramString2 == null) || (localPackageInfo.versionName.compareTo(paramString2) >= 0))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    if ((paramString1 != null) && (paramString2 != null) && (!paramString1.equalsIgnoreCase(paramString2)) && (paramInt == 0))
    {
      e(paramContext);
      return false;
    }
    return true;
  }
  
  public static boolean a(View paramView, CharSequence paramCharSequence)
  {
    if (paramView == null) {
      return false;
    }
    if (TextUtils.isEmpty(paramCharSequence))
    {
      paramView.setVisibility(8);
      return false;
    }
    paramView.setVisibility(0);
    return true;
  }
  
  public static boolean a(TextView paramTextView, CharSequence paramCharSequence)
  {
    if (TextUtils.isEmpty(paramCharSequence))
    {
      paramTextView.setVisibility(8);
      return false;
    }
    paramTextView.setVisibility(0);
    paramTextView.setText(paramCharSequence);
    return true;
  }
  
  public static int b(double paramDouble)
  {
    return (int)(10.0D * paramDouble + 0.5D) + 340000000;
  }
  
  public static int b(Activity paramActivity)
  {
    Rect localRect = new Rect();
    paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    int j = localRect.top;
    int i = j;
    if (r.an)
    {
      i = j;
      if (j < 0) {
        i = 0;
      }
    }
    return i;
  }
  
  public static aa b(Intent paramIntent)
  {
    aa localAa = (aa)paramIntent.getSerializableExtra("_returnInvoke");
    paramIntent = localAa;
    if (localAa == null) {
      paramIntent = aa.a;
    }
    return paramIntent;
  }
  
  public static String b(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = v.a(paramInt);
    if (paramInt <= 0) {
      localStringBuilder.append("요금 미제공");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(str).append("원");
    }
  }
  
  public static String b(bs paramBs, int paramInt)
  {
    if (paramInt < 8) {
      return paramBs.b();
    }
    return paramBs.a();
  }
  
  public static String b(String paramString)
  {
    if (paramString.length() < 12) {
      return "-";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString.substring(4, 6));
    localStringBuilder.append("/");
    localStringBuilder.append(paramString.substring(6, 8));
    localStringBuilder.append(" ");
    localStringBuilder.append(paramString.substring(8, 10));
    localStringBuilder.append(":");
    localStringBuilder.append(paramString.substring(10, 12));
    return localStringBuilder.toString();
  }
  
  public static void b(Activity paramActivity, String paramString)
  {
    String str1;
    if (!TextUtils.isEmpty(paramString)) {
      str1 = null;
    }
    try
    {
      paramString = Uri.encode(paramString, ":/.?=&#");
      str1 = paramString;
      Uri localUri = Uri.parse(paramString);
      str1 = paramString;
      String str2 = localUri.getAuthority();
      str1 = paramString;
      paramString = localUri.buildUpon().encodedAuthority(str2).build().toString();
      str1 = paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    if (str1 != null)
    {
      paramString = new Intent();
      paramString.putExtra("url", str1);
      paramString.putExtra("inappbrowser", true);
      aw.a().a(paramActivity, aa.X, paramString, 0);
    }
  }
  
  public static void b(Context paramContext)
  {
    String str1 = paramContext.getResources().getString(2131231630);
    String str2 = paramContext.getResources().getString(2131231627);
    String str3 = paramContext.getResources().getString(2131231629);
    a(new AlertDialog.Builder(paramContext).setIcon(17301543).setTitle(str1).setMessage(str3).setNeutralButton(str2, null).create(), paramContext);
  }
  
  public static void b(Context paramContext, int paramInt)
  {
    Toast.makeText(paramContext, paramContext.getString(paramInt), 0).show();
  }
  
  public static void b(Context paramContext, DialogInterface.OnClickListener paramOnClickListener)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(null);
    localBuilder.setIcon(17301543);
    localBuilder.setMessage(paramContext.getResources().getString(2131231453)).setCancelable(false).setPositiveButton(2131231751, paramOnClickListener).setNegativeButton(2131231439, paramOnClickListener);
    a(localBuilder);
  }
  
  public static void b(View paramView, boolean paramBoolean)
  {
    if (paramView != null) {
      paramView.setEnabled(paramBoolean);
    }
  }
  
  public static void b(Object paramObject, final Activity paramActivity)
  {
    if ((paramObject instanceof UIModel.SearchResultItemModel))
    {
      Object localObject = (UIModel.SearchResultItemModel)paramObject;
      paramObject = new gf();
      paramObject.b = ((UIModel.SearchResultItemModel)localObject).f;
      paramObject.d = ((UIModel.SearchResultItemModel)localObject).g;
      localObject = new Runnable()
      {
        public void run()
        {
          jr localJr = new jr();
          localJr.a(ba.this.m, ba.this.l);
          localJr.a(ba.this.n);
          localJr.b(ba.this.f);
          if (ba.this.w != null)
          {
            localJr.g = ba.this.w.a;
            localJr.h = ba.this.w.b;
            localJr.i = ba.this.w.a();
          }
          ak.a().b(paramActivity, cb.a().b(), localJr);
        }
      };
      cb localCb = cb.a();
      localCb.a((Runnable)localObject);
      localCb.a(paramActivity, paramObject);
    }
  }
  
  public static void b(String paramString, Object paramObject, Activity paramActivity)
  {
    int i = 3;
    Intent localIntent;
    if ((paramString != null) && (!TextUtils.isEmpty(paramString)))
    {
      if (!(paramObject instanceof bx)) {
        break label104;
      }
      localObject = (bx)paramObject;
      localIntent = new Intent("android.intent.action.VIEW", Uri.parse("navercontacts://insert?version=1"));
      localIntent.putExtra("name", ((bx)localObject).d);
      localIntent.putExtra("phone", paramString);
      localIntent.putExtra("postal", ((bx)localObject).g);
      localIntent.putExtra("scrap-title", ((bx)localObject).d);
      a(paramObject, localIntent, paramActivity);
    }
    label104:
    do
    {
      do
      {
        do
        {
          return;
          if ((paramObject instanceof UIModel.SearchResultItemModel))
          {
            localObject = (UIModel.SearchResultItemModel)paramObject;
            localIntent = new Intent("android.intent.action.VIEW", Uri.parse("navercontacts://insert?version=1"));
            localIntent.putExtra("name", ((UIModel.SearchResultItemModel)localObject).f);
            localIntent.putExtra("phone", paramString);
            localIntent.putExtra("postal", ((UIModel.SearchResultItemModel)localObject).g);
            localIntent.putExtra("scrap-title", ((UIModel.SearchResultItemModel)localObject).f);
            a(paramObject, localIntent, paramActivity);
            return;
          }
          if (!(paramObject instanceof SubwayStationDetailInfo)) {
            break;
          }
          localObject = (SubwayStationDetailInfo)paramObject;
        } while ((localObject == null) || (TextUtils.isEmpty(((SubwayStationDetailInfo)localObject).q)));
        localIntent = new Intent("android.intent.action.VIEW", Uri.parse("navercontacts://insert?version=1"));
        localIntent.putExtra("name", ((SubwayStationDetailInfo)localObject).e);
        ArrayList localArrayList = e(paramString);
        if (localArrayList != null)
        {
          if (localArrayList.size() > 3) {}
          for (;;)
          {
            int j = 0;
            while (j < i)
            {
              localIntent.putExtra("phone", paramString);
              j += 1;
            }
            i = localArrayList.size();
          }
        }
        localIntent.putExtra("postal", ((SubwayStationDetailInfo)localObject).m);
        localIntent.putExtra("scrap-title", ((SubwayStationDetailInfo)localObject).e);
        a(paramObject, localIntent, paramActivity);
        return;
        if (!(paramObject instanceof hq)) {
          break;
        }
        localObject = (hq)paramObject;
      } while ((localObject == null) || (TextUtils.isEmpty(((hq)localObject).k)));
      localIntent = new Intent("android.intent.action.VIEW", Uri.parse("navercontacts://insert?version=1"));
      localIntent.putExtra("name", ((hq)localObject).d);
      localIntent.putExtra("phone", paramString);
      localIntent.putExtra("postal", ((hq)localObject).j);
      localIntent.putExtra("scrap-title", ((hq)localObject).d);
      a(paramObject, localIntent, paramActivity);
      return;
    } while (!(paramObject instanceof String));
    Object localObject = new Intent("android.intent.action.VIEW", Uri.parse("navercontacts://insert?version=1"));
    ((Intent)localObject).putExtra("name", (String)paramObject);
    ((Intent)localObject).putExtra("phone", paramString);
    a(paramObject, (Intent)localObject, paramActivity);
  }
  
  public static int c(double paramDouble)
  {
    return (int)(10.0D * paramDouble + 0.5D) + 130000000;
  }
  
  public static String c(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = v.a(paramInt);
    if (paramInt <= 0) {
      localStringBuilder.append("-");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(str).append("원");
    }
  }
  
  public static void c(Context paramContext)
  {
    String str1 = paramContext.getResources().getString(2131231440);
    String str2 = paramContext.getResources().getString(2131231454);
    a(new AlertDialog.Builder(paramContext).setIcon(17301543).setTitle(null).setMessage(str2).setNeutralButton(str1, null).create(), paramContext);
  }
  
  public static void c(Context paramContext, DialogInterface.OnClickListener paramOnClickListener)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(null);
    localBuilder.setMessage(paramContext.getResources().getString(2131230932)).setPositiveButton(2131231474, paramOnClickListener).setNegativeButton(2131231435, paramOnClickListener);
    a(localBuilder);
  }
  
  public static void c(View paramView, boolean paramBoolean)
  {
    if (paramView != null)
    {
      if (paramBoolean) {
        paramView.setVisibility(0);
      }
    }
    else {
      return;
    }
    paramView.setVisibility(4);
  }
  
  private static void c(String paramString1, String paramString2)
  {
    k localK = new k(1, null, "BizCacher");
    localK.c(u.a().f());
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("mainNumber", paramString1);
      localJSONObject.put("callSource", "MMAP_APP");
      if (!TextUtils.isEmpty(paramString2)) {
        localJSONObject.put("keyword", paramString2);
      }
      Log.d("BizCatcher", localJSONObject.toString());
      localK.a("http://api.biz-catcher.naver.com/api/v1/callInfos", localJSONObject.toString(), "application/json");
      return;
    }
    catch (JSONException paramString1)
    {
      Log.e("BizCatcher", "BizCacher JSON error", paramString1);
    }
  }
  
  public static boolean c(String paramString)
  {
    return true;
  }
  
  public static String d(int paramInt)
  {
    if (paramInt < 50) {
      return null;
    }
    String str;
    if (paramInt < 100) {
      str = "50m 정도";
    }
    for (;;)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("내 위치와 ");
      localStringBuilder.append(str);
      localStringBuilder.append(" 차이가 날 수 있습니다.");
      return localStringBuilder.toString();
      if (paramInt < 200) {
        str = "100m 정도";
      } else if (paramInt < 500) {
        str = "300m 정도";
      } else if (paramInt < 1000) {
        str = "500m 정도";
      } else {
        str = "1km 이상";
      }
    }
  }
  
  public static String d(String paramString)
  {
    if (TextUtils.isEmpty(paramString) == true) {}
    do
    {
      return null;
      paramString = paramString.split(">");
    } while (paramString.length == 0);
    return paramString[(paramString.length - 1)].trim();
  }
  
  public static void d(Context paramContext)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(null);
    localBuilder.setIcon(17301543);
    localBuilder.setMessage(paramContext.getResources().getString(2131231453)).setPositiveButton(2131231440, null);
    a(localBuilder);
  }
  
  public static int e(int paramInt)
  {
    return 340000000 + paramInt;
  }
  
  private static ArrayList<String> e(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramString.indexOf(" / ");
    int i = 0;
    while (j > -1)
    {
      String str = paramString.substring(i, j);
      if (str != null) {
        localArrayList.add(str);
      }
      i = j + 3;
      j = paramString.indexOf(" / ", i);
    }
    paramString = paramString.substring(i);
    if (paramString != null) {
      localArrayList.add(paramString);
    }
    return localArrayList;
  }
  
  public static void e(Context paramContext)
  {
    String str = paramContext.getResources().getString(2131231627);
    a(new AlertDialog.Builder(paramContext).setIcon(17301543).setMessage("해당 정보는 구버전과 호환성 문제로 상세 내용을 표시할 수 없습니다.\n삭제 후 원하시는 정보를 다시 저장(즐겨찾기)하시기 바랍니다.\n이용에 불편을 드려 죄송합니다.").setNeutralButton(str, null).create(), paramContext);
  }
  
  public static int f(int paramInt)
  {
    return 130000000 + paramInt;
  }
  
  public static void f(Context paramContext)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle("3G 미 지원 알림");
    localBuilder.setIcon(17301659);
    localBuilder.setMessage(paramContext.getResources().getString(2131231749));
    localBuilder.setPositiveButton("확인", null);
    localBuilder.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        return paramAnonymousInt == 84;
      }
    });
    a(localBuilder);
  }
  
  public static int g(int paramInt)
  {
    return (int)((paramInt - 340000000) / 10.0D + 0.5D);
  }
  
  public static void g(Context paramContext)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(2131231525);
    localBuilder.setIcon(17301659);
    localBuilder.setMessage(2131231523);
    localBuilder.setPositiveButton(2131231702, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        ba.this.startActivity(paramAnonymousDialogInterface);
      }
    });
    localBuilder.setNegativeButton(2131231435, null);
    a(localBuilder.create(), paramContext);
  }
  
  public static int h(int paramInt)
  {
    return (int)((paramInt - 130000000) / 10.0D + 0.5D);
  }
  
  public static int h(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }
  
  public static int i(Context paramContext)
  {
    int i = 0;
    if ((paramContext instanceof Activity)) {
      i = b((Activity)paramContext);
    }
    return paramContext.getResources().getDisplayMetrics().heightPixels - i;
  }
  
  public static int j(Context paramContext)
  {
    Display localDisplay = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    int j = paramContext.getResources().getConfiguration().orientation;
    int i = j;
    if (j == 0)
    {
      if (localDisplay.getWidth() == localDisplay.getHeight()) {
        i = 3;
      }
    }
    else {
      return i;
    }
    if (localDisplay.getWidth() < localDisplay.getHeight()) {
      return 1;
    }
    return 2;
  }
  
  public static boolean k(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    View localView = ((Activity)paramContext).getWindow().getDecorView();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    int i = localView.getMeasuredWidth();
    int j = localView.getMeasuredHeight();
    if ((j == 0) && (i == 0)) {
      return false;
    }
    int k = localDisplayMetrics.heightPixels;
    return localDisplayMetrics.widthPixels * k != i * j;
  }
}
