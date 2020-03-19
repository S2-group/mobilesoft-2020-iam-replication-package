package kr.co.sbs.videoplayer.tmoney;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import kr.co.sbs.videoplayer.base.AVAbsPage;
import kr.co.sbs.videoplayer.main.AVMainPage;
import kr.co.sbs.videoplayer.network.ApiController;
import kr.co.sbs.videoplayer.network.cacheable.IApiCallListener;
import kr.co.sbs.videoplayer.network.datatype.common.RBARequest;
import kr.co.sbs.videoplayer.network.datatype.common.RBARequest.Builder;
import kr.co.sbs.videoplayer.network.datatype.common.RBARequest.Listener;
import kr.co.sbs.videoplayer.push.b;
import kr.co.sbs.videoplayer.util.c;
import org.json.JSONArray;
import org.json.JSONObject;

public final class f
{
  private static f c = new f();
  public Context a;
  public SparseArrayCompat<b> b = new SparseArrayCompat();
  private boolean d;
  private long e = -1L;
  private long f = -1L;
  private long g = -1L;
  private long h = -1L;
  private String i;
  private String j;
  private String k;
  private boolean l;
  private d m = new d();
  private AVAbsPage n;
  private IApiCallListener o;
  
  public f() {}
  
  public static AlertDialog a(AVAbsPage paramAVAbsPage)
  {
    try
    {
      paramAVAbsPage = kr.co.sbs.videoplayer.util.a.a(paramAVAbsPage, paramAVAbsPage.getString(2131296800), paramAVAbsPage.getString(2131296633)).setPositiveButton(2131296805, new DialogInterface.OnClickListener()
      {
        public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      }).show();
      return paramAVAbsPage;
    }
    catch (Exception paramAVAbsPage)
    {
      kr.co.sbs.library.common.a.a.a(paramAVAbsPage);
    }
    return null;
  }
  
  private static String a(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext.getSimState() != 5)
        {
          kr.co.sbs.library.common.a.a.a(new Object[] { "-- SIM이 사용 불가능!" });
          return null;
        }
        String str1 = paramContext.getSimOperator();
        localObject = paramContext.getNetworkOperator();
        String str2 = paramContext.getNetworkOperatorName();
        kr.co.sbs.library.common.a.a.b(new Object[] { "++ simOperator: [%s]", str1 });
        kr.co.sbs.library.common.a.a.b(new Object[] { "++ networkOperator: [%s]", localObject });
        kr.co.sbs.library.common.a.a.b(new Object[] { "++ networkOperatorName: [%s]", str2 });
        if ((!str1.equals("45005")) && (!str1.equals("45006")) && (!str1.equals("45008")))
        {
          kr.co.sbs.library.common.a.a.a(new Object[] { "-- 3사 통신사 아님!" });
          return null;
        }
        if (TextUtils.isEmpty(str1)) {
          return null;
        }
        localObject = paramContext.getLine1Number();
        kr.co.sbs.library.common.a.a.b(new Object[] { "++ phoneNumber: [%s]", localObject });
        if (!str1.equals("45006"))
        {
          paramContext = (Context)localObject;
          if (!str1.equals("45008")) {}
        }
        else
        {
          paramContext = (Context)localObject;
          if (!TextUtils.isEmpty((CharSequence)localObject))
          {
            paramContext = (Context)localObject;
            if (((String)localObject).startsWith("+821")) {
              paramContext = ((String)localObject).replace("+821", "01");
            }
          }
        }
        if (TextUtils.isEmpty(paramContext))
        {
          kr.co.sbs.library.common.a.a.a(new Object[] { "-- 전화번호 없음" });
          localObject = paramContext;
          kr.co.sbs.library.common.a.a.b(new Object[] { "++ pn: [%s]", localObject });
          return localObject;
        }
      }
      catch (Exception paramContext)
      {
        kr.co.sbs.library.common.a.a.a(paramContext);
        return null;
      }
      Object localObject = paramContext;
      if (!c.j(paramContext)) {
        localObject = c.h(paramContext);
      }
    }
  }
  
  private f a(String paramString1, String paramString2)
  {
    try
    {
      if (!c.n(paramString1))
      {
        kr.co.sbs.library.common.a.a.a(new Object[] { "-- URL 잘못됨!" });
        return f.g;
      }
      long l1 = System.currentTimeMillis();
      if (this.m == null) {
        this.m = new d();
      }
      this.m.a = l1;
      ApiController localApiController = ApiController.c();
      if (paramString1.equals("https://mktp.t-money.co.kr/mktp/api/member/memberInfo.api"))
      {
        kr.co.sbs.videoplayer.network.cacheable.a localA = localApiController.a(1, paramString1, this.m);
        paramString2 = new TMParamMember(paramString2);
        localA.a("data", new Gson().toJson(paramString2));
        localA.setShouldCache(false);
        localA.setTag(paramString1);
        localApiController.a(paramString1);
        localApiController.a(localA);
        return f.a;
      }
      if (paramString1.startsWith("https://m.sbs.co.kr/common/ajax/tmoney_cttime_check.do?uuid="))
      {
        paramString2 = localApiController.a(0, paramString1 + paramString2, this.m);
        paramString2.setShouldCache(false);
        paramString2.setTag(paramString1);
        localApiController.a(paramString1);
        localApiController.a(paramString2);
        return f.b;
      }
      paramString1.startsWith("https://m.sbs.co.kr/common/tmoney_to_sbs.do");
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        kr.co.sbs.library.common.a.a.a(paramString1);
      }
    }
    return f.i;
  }
  
  public static f a()
  {
    try
    {
      if (c == null) {
        c = new f();
      }
      f localF = c;
      return localF;
    }
    finally {}
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, kr.co.sbs.videoplayer.push.b.a paramA)
  {
    String str = null;
    try
    {
      if (!TextUtils.isEmpty(paramString1)) {
        str = "/" + paramString1;
      }
      if (TextUtils.isEmpty(str))
      {
        paramA.a("popup/error", new Object[0]);
        return;
      }
      paramString1 = str;
      if (!TextUtils.isEmpty(paramString2)) {
        paramString1 = str + "?type=" + paramString2;
      }
      if (TextUtils.isEmpty(paramString1))
      {
        paramA.a("popup/error", new Object[0]);
        return;
      }
    }
    catch (Exception paramContext)
    {
      kr.co.sbs.library.common.a.a.a(paramContext);
      paramA.a("popup/error", new Object[0]);
      return;
    }
    paramString1 = c.a(c.b, paramString1);
    paramString2 = c.a(c.b, null);
    if (kr.co.sbs.videoplayer.a.a.a.K) {
      kr.co.sbs.library.common.a.a.b(new Object[] { "++ url: [%s]", paramString1 });
    }
    new RBARequest.Builder().setMethod(0).setNeedToCheckModifiedFromNetwork(false).setRevalidation(false).setShouldCache(false).setTag(paramString2).setURL(paramString1).setListener(new RBARequest.Listener()
    {
      public final void onErrorResponse(RBARequest paramAnonymousRBARequest, VolleyError paramAnonymousVolleyError)
      {
        if (kr.co.sbs.videoplayer.a.a.a.K)
        {
          kr.co.sbs.library.common.a.a.b(new Object[] { "-- request: [%s]", paramAnonymousRBARequest });
          kr.co.sbs.library.common.a.a.b(new Object[] { "-- error: [%s]", paramAnonymousVolleyError });
        }
        if (this.a != null) {
          this.a.a("popup/error", new Object[] { paramAnonymousRBARequest, paramAnonymousVolleyError });
        }
      }
      
      public final void onResponse(RBARequest paramAnonymousRBARequest, int paramAnonymousInt, byte[] paramAnonymousArrayOfByte)
      {
        if (kr.co.sbs.videoplayer.a.a.a.K)
        {
          kr.co.sbs.library.common.a.a.b(new Object[] { "-- request: [%s]", paramAnonymousRBARequest });
          kr.co.sbs.library.common.a.a.b(new Object[] { "-- resCode: [%s]", Integer.valueOf(paramAnonymousInt) });
          kr.co.sbs.library.common.a.a.b(new Object[] { "-- res: [%s]", paramAnonymousArrayOfByte });
        }
        if (this.a != null) {
          this.a.a("popup/ok", new Object[] { paramAnonymousRBARequest, Integer.valueOf(paramAnonymousInt), paramAnonymousArrayOfByte });
        }
      }
    }).request(paramContext, ApiController.c());
  }
  
  public static void a(final Context paramContext, kr.co.sbs.videoplayer.push.b.a paramA)
  {
    b.a(paramContext, new kr.co.sbs.videoplayer.push.b.a()
    {
      public final void a(String paramAnonymousString, Object... paramAnonymousVarArgs)
      {
        try
        {
          if ((!"adid".equalsIgnoreCase(b.a(paramAnonymousString))) || (paramAnonymousVarArgs == null) || (paramAnonymousVarArgs[0] == null) || (!(paramAnonymousVarArgs[0] instanceof com.google.android.gms.a.a.a.a)))
          {
            if (this.a != null) {
              this.a.a("auth/error", new Object[0]);
            }
          }
          else if (!"ok".equalsIgnoreCase(b.b(paramAnonymousString)))
          {
            if (this.a == null) {
              return;
            }
            this.a.a("auth/error", new Object[0]);
          }
        }
        catch (Exception paramAnonymousString)
        {
          kr.co.sbs.library.common.a.a.a(paramAnonymousString);
          if (this.a != null)
          {
            this.a.a("auth/error", new Object[0]);
            return;
            paramAnonymousString = (com.google.android.gms.a.a.a.a)paramAnonymousVarArgs[0];
            paramAnonymousVarArgs = paramAnonymousString.a;
            boolean bool = paramAnonymousString.b;
            if (kr.co.sbs.videoplayer.a.a.a.K)
            {
              kr.co.sbs.library.common.a.a.a(new Object[] { "-- 구글 광고 정보!" });
              kr.co.sbs.library.common.a.a.b(new Object[] { "++ info.id: [%s]", paramAnonymousVarArgs });
              kr.co.sbs.library.common.a.a.b(new Object[] { "++ info.limitAdTrackingEnabled: [%s]", Boolean.valueOf(bool) });
            }
            if (!TextUtils.isEmpty(paramAnonymousVarArgs))
            {
              paramAnonymousString = f.c.a(f.c.a, "/" + paramAnonymousVarArgs);
              paramAnonymousVarArgs = f.c.a(f.c.a, null);
              if (kr.co.sbs.videoplayer.a.a.a.K) {
                kr.co.sbs.library.common.a.a.b(new Object[] { "++ url: [%s]", paramAnonymousString });
              }
              ApiController.c().a(paramAnonymousString);
              new RBARequest.Builder().setMethod(0).setNeedToCheckModifiedFromNetwork(false).setRevalidation(false).setShouldCache(false).setTag(paramAnonymousVarArgs).setURL(paramAnonymousString).setListener(new RBARequest.Listener()
              {
                public final void onErrorResponse(RBARequest paramAnonymous2RBARequest, VolleyError paramAnonymous2VolleyError)
                {
                  if (kr.co.sbs.videoplayer.a.a.a.K)
                  {
                    kr.co.sbs.library.common.a.a.b(new Object[] { "-- request: [%s]", paramAnonymous2RBARequest });
                    kr.co.sbs.library.common.a.a.b(new Object[] { "-- error: [%s]", paramAnonymous2VolleyError });
                  }
                  if (f.1.this.a != null) {
                    f.1.this.a.a("auth/error", new Object[] { paramAnonymous2RBARequest, paramAnonymous2VolleyError });
                  }
                }
                
                public final void onResponse(RBARequest paramAnonymous2RBARequest, int paramAnonymous2Int, byte[] paramAnonymous2ArrayOfByte)
                {
                  if (kr.co.sbs.videoplayer.a.a.a.K)
                  {
                    kr.co.sbs.library.common.a.a.b(new Object[] { "-- request: [%s]", paramAnonymous2RBARequest });
                    kr.co.sbs.library.common.a.a.b(new Object[] { "-- resCode: [%s]", Integer.valueOf(paramAnonymous2Int) });
                    kr.co.sbs.library.common.a.a.b(new Object[] { "-- res: [%s]", paramAnonymous2ArrayOfByte });
                  }
                  if (f.1.this.a != null) {
                    f.1.this.a.a("auth/ok", new Object[] { paramAnonymous2RBARequest, Integer.valueOf(paramAnonymous2Int), paramAnonymous2ArrayOfByte });
                  }
                }
              }).request(paramContext, ApiController.c());
            }
          }
        }
      }
    });
  }
  
  private f b(Context paramContext, IApiCallListener paramIApiCallListener)
  {
    for (;;)
    {
      int i2;
      try
      {
        Context localContext = paramContext.getApplicationContext();
        if (paramIApiCallListener != null) {
          this.o = paramIApiCallListener;
        }
        paramIApiCallListener = e.a;
        e localE1 = e.b;
        e localE2 = e.c;
        List localList = localContext.getPackageManager().getInstalledPackages(128);
        if (localList == null) {
          break label327;
        }
        int i3 = localList.size();
        i1 = 0;
        if (i1 >= i3) {
          break label327;
        }
        Object localObject = (PackageInfo)localList.get(i1);
        if (localObject == null) {
          break label320;
        }
        localObject = ((PackageInfo)localObject).packageName;
        if (TextUtils.isEmpty((CharSequence)localObject)) {
          break label320;
        }
        i2 = 0;
        if (i2 >= 3) {
          break label320;
        }
        if (!((String)localObject).equals(new e[] { paramIApiCallListener, localE1, localE2 }[i2].d)) {
          break label311;
        }
        kr.co.sbs.library.common.a.a.b(new Object[] { "++ name: [%s]", localObject });
        i1 = 1;
        if (i1 == 0)
        {
          kr.co.sbs.library.common.a.a.a(new Object[] { "-- 티머니 앱 안깔려있음!" });
          return f.d;
        }
        if (g.b(localContext))
        {
          ApiController.c().b(paramContext);
          paramContext = g.a(localContext);
          this.k = "";
          this.i = "";
          this.j = "";
          this.h = -1L;
          this.g = -1L;
          this.e = -1L;
          this.f = -1L;
          this.d = false;
          return a("https://m.sbs.co.kr/common/ajax/tmoney_cttime_check.do?uuid=", paramContext);
        }
        paramContext = f.f;
        paramIApiCallListener = a(localContext);
        if (!TextUtils.isEmpty(paramIApiCallListener))
        {
          ApiController.c().b(localContext);
          paramContext = a("https://mktp.t-money.co.kr/mktp/api/member/memberInfo.api", paramIApiCallListener);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        kr.co.sbs.library.common.a.a.a(paramContext);
        paramContext = f.i;
      }
      return paramContext;
      label311:
      i2 += 1;
      continue;
      label320:
      i1 += 1;
      continue;
      label327:
      int i1 = 0;
    }
  }
  
  @Deprecated
  private void e()
  {
    this.k = "";
    this.i = "";
    this.j = "";
    this.h = 0L;
    this.g = 0L;
    this.e = 0L;
    this.f = 0L;
    this.l = false;
  }
  
  public final f a(Context paramContext, IApiCallListener paramIApiCallListener)
  {
    f localF = f.i;
    try
    {
      Object localObject = paramContext.getApplicationContext();
      if (paramIApiCallListener != null) {
        this.o = paramIApiCallListener;
      }
      localObject = g.a((Context)localObject);
      if (TextUtils.isEmpty((CharSequence)localObject)) {
        return b(paramContext, paramIApiCallListener);
      }
      paramContext = a("https://m.sbs.co.kr/common/tmoney_to_sbs.do", (String)localObject);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      kr.co.sbs.library.common.a.a.a(paramContext);
    }
    return localF;
  }
  
  public final void a(TMTypeCtTime paramTMTypeCtTime)
  {
    if (paramTMTypeCtTime == null)
    {
      if (kr.co.sbs.videoplayer.a.a.a.K) {
        kr.co.sbs.library.common.a.a.a(new Object[] { "-- 데이터 없음!" });
      }
      e();
      return;
    }
    if (kr.co.sbs.videoplayer.a.a.a.K) {
      kr.co.sbs.library.common.a.a.b(new Object[] { "++ data: [%s]", paramTMTypeCtTime });
    }
    if ((paramTMTypeCtTime.response == null) || (TextUtils.isEmpty(paramTMTypeCtTime.response.code)))
    {
      if (kr.co.sbs.videoplayer.a.a.a.K) {
        kr.co.sbs.library.common.a.a.a(new Object[] { "-- 응답코드를 알 수 없음!" });
      }
      e();
      return;
    }
    if (("404".equalsIgnoreCase(paramTMTypeCtTime.response.code)) || (!"200".equalsIgnoreCase(paramTMTypeCtTime.response.code)))
    {
      if (kr.co.sbs.videoplayer.a.a.a.K) {
        kr.co.sbs.library.common.a.a.a(new Object[] { "-- 응답코드가 정상이 아님!" });
      }
      e();
      return;
    }
    if ((TextUtils.isEmpty(paramTMTypeCtTime.freezone_time)) || (TextUtils.isEmpty(paramTMTypeCtTime.ct_time)) || (TextUtils.isEmpty(paramTMTypeCtTime.server_time)))
    {
      if (kr.co.sbs.videoplayer.a.a.a.K) {
        kr.co.sbs.library.common.a.a.a(new Object[] { "-- 정보가 이상함!" });
      }
      e();
      return;
    }
    String str1 = paramTMTypeCtTime.server_time;
    String str2 = paramTMTypeCtTime.ct_time;
    this.j = str1;
    this.i = str2;
    long l1 = g.a(str1);
    long l2 = g.a(str2);
    this.f = l1;
    this.e = l2;
    this.h = (l1 - System.currentTimeMillis());
    l1 -= l2;
    paramTMTypeCtTime = paramTMTypeCtTime.freezone_time;
    this.k = paramTMTypeCtTime;
    kr.co.sbs.library.common.a.a.b(new Object[] { "++ mFreeZoneTime: [%s]", this.k });
    if (!c.j(paramTMTypeCtTime))
    {
      e();
      return;
    }
    l2 = Long.parseLong(paramTMTypeCtTime) * 1000L;
    this.g = l2;
    kr.co.sbs.library.common.a.a.b(new Object[] { "++ remainTime: [%s]", Long.valueOf(l2 - (l1 - l1 % 1000L)) });
  }
  
  public final void a(b paramB)
  {
    if (this.b == null) {
      this.b = new SparseArrayCompat();
    }
    int i2 = this.b.size();
    int i1 = 0;
    while (i1 < i2)
    {
      b localB = (b)this.b.get(i1);
      if ((localB != null) && (localB.equals(paramB))) {
        return;
      }
      i1 += 1;
    }
    this.b.put(i2, paramB);
  }
  
  public final long b()
  {
    try
    {
      long l2 = this.h;
      long l3 = this.f;
      long l4 = this.e;
      l1 = this.g;
      long l5 = System.currentTimeMillis();
      if ((l3 <= 0L) || (l4 <= 0L) || (l1 <= 0L))
      {
        kr.co.sbs.library.common.a.a.a(new Object[] { "-- 시간 정보 없음!" });
        return -1L;
      }
      l2 = l2 + l5 - l4;
      if (l2 <= l1)
      {
        l2 = l1 - l2;
        l1 = l2;
        if (!kr.co.sbs.videoplayer.a.a.a.K) {
          return l1;
        }
        kr.co.sbs.library.common.a.a.b(new Object[] { "++ remainTime: [%s]", Long.valueOf(l2) });
        return l2;
      }
    }
    catch (Exception localException)
    {
      kr.co.sbs.library.common.a.a.a(localException);
      return -1L;
    }
    kr.co.sbs.library.common.a.a.a(new Object[] { "-- 시간 초과!" });
    long l1 = -1L;
    return l1;
  }
  
  public final void b(b paramB)
  {
    if (this.b == null) {}
    for (;;)
    {
      return;
      int i2 = this.b.size();
      int i1 = 0;
      while (i1 < i2)
      {
        b localB = (b)this.b.get(i1);
        if ((localB != null) && (localB.equals(paramB)))
        {
          this.b.remove(i1);
          return;
        }
        i1 += 1;
      }
    }
  }
  
  public final boolean c()
  {
    try
    {
      if (b() >= 0L) {
        return true;
      }
      kr.co.sbs.library.common.a.a.a(new Object[] { "-- 시간 초과!" });
      return false;
    }
    catch (Exception localException)
    {
      kr.co.sbs.library.common.a.a.a(localException);
      kr.co.sbs.library.common.a.a.a(new Object[] { "-- 불확실한 이유로 프리존 사용 못함!" });
    }
    return false;
  }
  
  public final boolean d()
  {
    if (this.b == null) {
      return false;
    }
    if (this.e > 0L) {}
    for (int i1 = 1; i1 == 0; i1 = 0)
    {
      kr.co.sbs.library.common.a.a.a(new Object[] { "-- 시간 정보 없음!" });
      return false;
    }
    if (c())
    {
      kr.co.sbs.library.common.a.a.a(new Object[] { "-- 프리존 사용 중!" });
      return false;
    }
    if (this.d)
    {
      kr.co.sbs.library.common.a.a.a(new Object[] { "-- 프리존 사용 할 수 없어 갱신 이미 했음!" });
      return false;
    }
    int i2 = this.b.size();
    i1 = 0;
    while (i1 < i2)
    {
      b localB = (b)this.b.get(i1);
      if (localB != null)
      {
        localB.r();
        localB.q();
      }
      i1 += 1;
    }
    this.d = true;
    return true;
  }
  
  public static final class a
  {
    public static TMTypeCtTime a(String paramString)
    {
      Object localObject4 = null;
      TMTypeCtTime localTMTypeCtTime = new TMTypeCtTime();
      if (TextUtils.isEmpty(paramString)) {
        return localTMTypeCtTime;
      }
      try
      {
        paramString = new JSONObject(paramString);
        if (paramString == null) {
          return localTMTypeCtTime;
        }
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          kr.co.sbs.library.common.a.a.a(paramString);
          paramString = null;
        }
      }
      try
      {
        if (paramString.has("response"))
        {
          localObject1 = paramString.getString("response");
          if (TextUtils.isEmpty((CharSequence)localObject1)) {}
        }
      }
      catch (Exception localException6)
      {
        try
        {
          localObject1 = new JSONObject((String)localObject1);
          if (localObject1 != null)
          {
            localTMTypeCtTime.response = new TMCtTimeResponse();
            if (!((JSONObject)localObject1).has("code")) {}
          }
        }
        catch (Exception localException6)
        {
          try
          {
            Object localObject1;
            localTMTypeCtTime.response.code = ((JSONObject)localObject1).getString("code");
          }
          catch (Exception localException6)
          {
            try
            {
              if (paramString.has("boarding")) {
                localTMTypeCtTime.boarding = paramString.getString("boarding");
              }
            }
            catch (Exception localException6)
            {
              try
              {
                if (paramString.has("freezone_time")) {
                  localTMTypeCtTime.freezone_time = paramString.getString("freezone_time");
                }
              }
              catch (Exception localException6)
              {
                try
                {
                  if (paramString.has("server_time")) {
                    localTMTypeCtTime.server_time = paramString.getString("server_time");
                  }
                }
                catch (Exception localException6)
                {
                  try
                  {
                    for (;;)
                    {
                      if (paramString.has("ct_time")) {
                        localTMTypeCtTime.ct_time = paramString.getString("ct_time");
                      }
                      return localTMTypeCtTime;
                      localException1 = localException1;
                      kr.co.sbs.library.common.a.a.a(localException1);
                      Object localObject2 = null;
                      continue;
                      localException2 = localException2;
                      kr.co.sbs.library.common.a.a.a(localException2);
                      Object localObject3 = localObject4;
                      continue;
                      localException3 = localException3;
                      kr.co.sbs.library.common.a.a.a(localException3);
                      continue;
                      localException4 = localException4;
                      kr.co.sbs.library.common.a.a.a(localException4);
                      continue;
                      localException5 = localException5;
                      kr.co.sbs.library.common.a.a.a(localException5);
                      continue;
                      localException6 = localException6;
                      kr.co.sbs.library.common.a.a.a(localException6);
                    }
                  }
                  catch (Exception paramString)
                  {
                    for (;;)
                    {
                      kr.co.sbs.library.common.a.a.a(paramString);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    
    public static f.g b(String paramString)
    {
      Object localObject9 = null;
      Object localObject7 = null;
      f.g localG = new f.g();
      try
      {
        paramString = new JSONObject(paramString);
        if (paramString == null) {
          return null;
        }
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          kr.co.sbs.library.common.a.a.a(paramString);
          paramString = null;
        }
      }
      if (paramString.has("text")) {}
      for (;;)
      {
        String str4;
        label83:
        label157:
        label183:
        label209:
        Object localObject8;
        String str5;
        try
        {
          str3 = paramString.getString("text");
        }
        catch (Exception localException1)
        {
          int i;
          label271:
          str3 = null;
          label408:
          kr.co.sbs.library.common.a.a.a(localException1);
          continue;
        }
        for (;;)
        {
          try
          {
            localG.a = str3;
          }
          catch (Exception localException4)
          {
            break label408;
            Object localObject4 = null;
            break label209;
            Object localObject2 = null;
            break label183;
            paramString = null;
            break label157;
            paramString = null;
            localObject8 = null;
            str5 = null;
            Object localObject6 = localObject7;
            localObject7 = paramString;
            break label271;
            paramString = null;
            break;
          }
          try
          {
            if (!paramString.has("data")) {
              continue;
            }
            paramString = paramString.getJSONArray("data");
            str4 = paramString;
          }
          catch (Exception paramString)
          {
            kr.co.sbs.library.common.a.a.a(paramString);
            str4 = null;
            break label83;
          }
        }
        if (str4 != null)
        {
          if (kr.co.sbs.videoplayer.a.a.a.K) {
            kr.co.sbs.library.common.a.a.b(new Object[] { "++ ja: [%s]", str4 });
          }
          i = str4.length();
          localG.b = new HashMap();
          if (i > 0)
          {
            try
            {
              paramString = str4.getString(0);
            }
            catch (Exception localException2)
            {
              String str1;
              String str2;
              Object localObject5;
              paramString = null;
              kr.co.sbs.library.common.a.a.a(localException2);
              continue;
            }
            for (;;)
            {
              try
              {
                localG.b.put(Integer.valueOf(0), paramString);
                if (i <= 1) {
                  break label499;
                }
              }
              catch (Exception localException3)
              {
                Object localObject1;
                break label429;
              }
              try
              {
                str1 = str4.getString(1);
              }
              catch (Exception localException5)
              {
                localObject1 = null;
                kr.co.sbs.library.common.a.a.a(localException5);
                break;
              }
            }
            for (;;)
            {
              try
              {
                localG.b.put(Integer.valueOf(1), str1);
                if (i <= 2) {
                  break label494;
                }
              }
              catch (Exception localException6)
              {
                Object localObject3;
                break label439;
              }
              try
              {
                str2 = str4.getString(2);
              }
              catch (Exception localException7)
              {
                localObject3 = null;
                kr.co.sbs.library.common.a.a.a(localException7);
                break;
              }
            }
            for (;;)
            {
              try
              {
                localG.b.put(Integer.valueOf(2), str2);
                localObject5 = localObject7;
                localObject7 = str2;
                localObject8 = str1;
                str5 = paramString;
                if (i > 3) {
                  localObject5 = localObject9;
                }
              }
              catch (Exception localException8)
              {
                break label450;
              }
              try
              {
                str4 = str4.getString(3);
                localObject5 = str4;
                localG.b.put(Integer.valueOf(3), str4);
                str5 = paramString;
                localObject8 = str1;
                localObject7 = str2;
                localObject5 = str4;
              }
              catch (Exception localException9)
              {
                kr.co.sbs.library.common.a.a.a(localException9);
                localObject7 = localObject3;
                localObject8 = localObject1;
                str5 = paramString;
                break;
              }
            }
            if (kr.co.sbs.videoplayer.a.a.a.K) {
              kr.co.sbs.library.common.a.a.b(new Object[] { "++ text: [%s]", str3 });
            }
            if (kr.co.sbs.videoplayer.a.a.a.K) {
              kr.co.sbs.library.common.a.a.b(new Object[] { "++ type: [%s]", str5 });
            }
            if (kr.co.sbs.videoplayer.a.a.a.K) {
              kr.co.sbs.library.common.a.a.b(new Object[] { "++ pushId: [%s]", localObject8 });
            }
            if (kr.co.sbs.videoplayer.a.a.a.K) {
              kr.co.sbs.library.common.a.a.b(new Object[] { "++ pushType: [%s]", localObject7 });
            }
            if (kr.co.sbs.videoplayer.a.a.a.K) {
              kr.co.sbs.library.common.a.a.b(new Object[] { "++ ctTime: [%s]", localObject5 });
            }
            return localG;
          }
        }
        label429:
        label439:
        label450:
        label494:
        label499:
        String str3 = null;
      }
    }
  }
  
  public static abstract interface b
  {
    public abstract boolean q();
    
    public abstract void r();
  }
  
  public static enum c
  {
    private String c;
    
    private c(String paramString)
    {
      this.c = paramString;
    }
    
    public static String a(c paramC, String paramString)
    {
      if (paramC == null) {
        return null;
      }
      paramC = new StringBuilder(paramC.c);
      if (!TextUtils.isEmpty(paramString)) {
        paramC.append(paramString);
      }
      return paramC.toString();
    }
  }
  
  @Deprecated
  public final class d
    implements IApiCallListener
  {
    long a;
    
    public d() {}
    
    public final void onFailed(String paramString, VolleyError paramVolleyError)
    {
      kr.co.sbs.library.common.a.a.b(new Object[] { "++ url: [%s]", paramString });
      kr.co.sbs.library.common.a.a.b(new Object[] { "++ error: [%s]", paramVolleyError });
      if (f.a(f.this) != null) {
        f.a(f.this).onFailed(paramString, paramVolleyError);
      }
    }
    
    public final void onSucceded(String paramString, Object paramObject)
    {
      Object localObject;
      try
      {
        kr.co.sbs.library.common.a.a.b(new Object[] { "++ url: [%s]", paramString });
        if (paramObject == null)
        {
          kr.co.sbs.library.common.a.a.a(new Object[] { "-- 데이터 없음!" });
          return;
        }
        paramObject = ApiController.a(paramObject);
        if (!paramString.startsWith("https://mktp.t-money.co.kr/mktp/api/member/memberInfo.api")) {
          break label322;
        }
        localObject = (TMTypeUuid)ApiController.a(TMTypeUuid.class).fromJson(paramObject, TMTypeUuid.class);
        if (localObject == null)
        {
          kr.co.sbs.library.common.a.a.a(new Object[] { "-- 데이터 없음!" });
          if (f.a(f.this) == null) {
            return;
          }
          f.a(f.this).onSucceded(paramString, null);
          return;
        }
      }
      catch (Exception paramString)
      {
        kr.co.sbs.library.common.a.a.a(paramString);
        return;
      }
      kr.co.sbs.library.common.a.a.b(new Object[] { "++ data: [%s]", localObject });
      paramObject = (AVMainPage)f.d(f.this);
      String str1 = ((TMTypeUuid)localObject).RESULT_CODE;
      if ((TextUtils.isEmpty(str1)) || (!str1.equals("0000")))
      {
        kr.co.sbs.library.common.a.a.a(new Object[] { "-- uuid를 얻지 못함!: [%s]", str1 });
        g.a(paramObject, "");
        if (f.a(f.this) != null) {
          f.a(f.this).onSucceded(paramString, null);
        }
      }
      else if ((((TMTypeUuid)localObject).RESULT_DATA == null) || (TextUtils.isEmpty(((TMTypeUuid)localObject).RESULT_DATA.mbr_mng_no)))
      {
        kr.co.sbs.library.common.a.a.a(new Object[] { "-- uuid가 없음!" });
        g.a(paramObject, "");
        if (f.a(f.this) != null) {
          f.a(f.this).onSucceded(paramString, null);
        }
      }
      else
      {
        localObject = ((TMTypeUuid)localObject).RESULT_DATA.mbr_mng_no;
        g.a(paramObject, (String)localObject);
        if (f.a(f.this) != null)
        {
          f.a(f.this).onSucceded(paramString, localObject);
          return;
          label322:
          if (paramString.startsWith("https://m.sbs.co.kr/common/ajax/tmoney_cttime_check.do?uuid="))
          {
            localObject = (TMTypeCtTime)ApiController.a(TMTypeCtTime.class).fromJson(paramObject, TMTypeCtTime.class);
            if (localObject == null)
            {
              kr.co.sbs.library.common.a.a.a(new Object[] { "-- 데이터 없음!" });
              if (f.a(f.this) != null) {
                f.a(f.this).onSucceded(paramString, null);
              }
            }
            else
            {
              kr.co.sbs.library.common.a.a.b(new Object[] { "++ data: [%s]", localObject });
              if ((((TMTypeCtTime)localObject).response == null) || (TextUtils.isEmpty(((TMTypeCtTime)localObject).response.code)))
              {
                kr.co.sbs.library.common.a.a.a(new Object[] { "-- 응답코드를 알 수 없음!" });
                if (f.a(f.this) != null) {
                  f.a(f.this).onSucceded(paramString, null);
                }
              }
              else if (((TMTypeCtTime)localObject).response.code.equals("404"))
              {
                kr.co.sbs.library.common.a.a.a(new Object[] { "-- uuid가 없거나 프리존 유효시간 초과(CT 시간없음)!" });
                if (f.a(f.this) != null) {
                  f.a(f.this).onSucceded(paramString, null);
                }
              }
              else if (!((TMTypeCtTime)localObject).response.code.equals("200"))
              {
                kr.co.sbs.library.common.a.a.a(new Object[] { "-- 응답코드가 정상이 아님!" });
                if (f.a(f.this) != null) {
                  f.a(f.this).onSucceded(paramString, null);
                }
              }
              else if ((TextUtils.isEmpty(((TMTypeCtTime)localObject).freezone_time)) || (TextUtils.isEmpty(((TMTypeCtTime)localObject).ct_time)) || (TextUtils.isEmpty(((TMTypeCtTime)localObject).server_time)))
              {
                kr.co.sbs.library.common.a.a.a(new Object[] { "-- 정보가 이상함!" });
                if (f.a(f.this) != null) {
                  f.a(f.this).onSucceded(paramString, null);
                }
              }
              else
              {
                System.currentTimeMillis();
                str1 = ((TMTypeCtTime)localObject).server_time;
                String str2 = ((TMTypeCtTime)localObject).ct_time;
                f.a(f.this, str1);
                f.b(f.this, str2);
                long l1 = g.a(str1);
                long l2 = g.a(str2);
                f.a(f.this, l1);
                f.b(f.this, l2);
                f.c(f.this, l1 - System.currentTimeMillis());
                l1 -= l2;
                str1 = ((TMTypeCtTime)localObject).freezone_time;
                f.c(f.this, str1);
                kr.co.sbs.library.common.a.a.b(new Object[] { "++ mFreeZoneTime: [%s]", f.b(f.this) });
                if (!c.j(str1))
                {
                  f.c(f.this);
                  if (f.a(f.this) != null) {
                    f.a(f.this).onSucceded(paramObject, null);
                  }
                }
                else
                {
                  l2 = Long.parseLong(str1) * 1000L;
                  f.d(f.this, l2);
                  kr.co.sbs.library.common.a.a.b(new Object[] { "++ remainTime: [%s]", Long.valueOf(l2 - (l1 - l1 % 1000L)) });
                  if (f.a(f.this) != null) {
                    f.a(f.this).onSucceded(paramString, localObject);
                  }
                }
              }
            }
          }
          else if (paramString.startsWith("https://m.sbs.co.kr/common/tmoney_to_sbs.do"))
          {
            paramObject = (TMCtTimeResponse)ApiController.a(TMCtTimeResponse.class).fromJson(paramObject, TMCtTimeResponse.class);
            if (paramObject == null)
            {
              kr.co.sbs.library.common.a.a.a(new Object[] { "-- 데이터 없음!" });
              if (f.a(f.this) != null) {
                f.a(f.this).onSucceded(paramString, null);
              }
            }
            else
            {
              kr.co.sbs.library.common.a.a.b(new Object[] { "++ data: [%s]", paramObject });
              paramObject = paramObject.code;
              if ((TextUtils.isEmpty(paramObject)) || (!paramObject.equals("200")))
              {
                kr.co.sbs.library.common.a.a.a(new Object[] { "-- 정상 처리 안됨!" });
                if (f.a(f.this) != null) {
                  f.a(f.this).onSucceded(paramString, null);
                }
              }
              else
              {
                paramObject = Uri.parse(paramString).getQueryParameter("mbr_id");
                if (TextUtils.isEmpty(paramObject))
                {
                  kr.co.sbs.library.common.a.a.a(new Object[] { "-- URL에 UUID 없음!" });
                  if (f.a(f.this) != null) {
                    f.a(f.this).onSucceded(paramString, null);
                  }
                }
                else if (f.a(f.this) != null)
                {
                  f.a(f.this).onSucceded(paramString, paramObject);
                }
              }
            }
          }
        }
      }
    }
  }
  
  private static enum e
  {
    String d;
    
    private e(String paramString)
    {
      this.d = paramString;
    }
  }
  
  public static enum f
  {
    private f() {}
  }
  
  public static final class g
  {
    public String a;
    public HashMap<Integer, String> b;
    
    public g() {}
  }
}
