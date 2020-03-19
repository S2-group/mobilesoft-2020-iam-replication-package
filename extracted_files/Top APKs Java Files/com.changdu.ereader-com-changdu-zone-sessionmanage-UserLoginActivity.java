package com.changdu.zone.sessionmanage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.changdu.BaseActivity;
import com.changdu.BaseActivity.a;
import com.changdu.common.Wait;
import com.changdu.common.bk;
import com.changdu.common.view.NavigationBar;
import com.changdu.realvoice.receiver.RequestPlayStateReceiver;
import com.changdu.skin.SkinManager;
import com.changdu.u.a.g;
import com.changdu.util.z;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserLoginActivity
  extends BaseActivity
  implements a
{
  private static final int D = 1000;
  public static final String a = "is_from_person";
  public static final String b = "GetResult";
  public static final String c = "GetPassword";
  public static final String d = "operateStoreName";
  public static final int e = 0;
  public static final int f = 1;
  public static final int g = -1;
  public static final int h = 1;
  public static final int i = 59768;
  public static final int[] n = { 1, 2, 3, 901, 902, 905, 903 };
  public static final int[] o = { 1, 2, 3, 4, 5, 6, 7 };
  private FrameLayout A;
  private RelativeLayout B;
  private View.OnClickListener C = new s(this);
  private Handler E = new t(this);
  private TextView.OnEditorActionListener F = new u(this);
  private View.OnClickListener G = new v(this);
  private View.OnClickListener H = new w(this);
  private View.OnClickListener I = new k(this);
  private int J;
  private Map<String, String> K;
  NavigationBar j;
  com.changdu.share.k k;
  ab l;
  com.changdu.share.b m = new l(this);
  private Context p;
  private TextView q;
  private TextView r;
  private TextView s;
  private AutoCompleteTextView t;
  private EditText u;
  private TextView v;
  private TextView w;
  private Button x;
  private boolean y = false;
  private boolean z = false;
  
  public UserLoginActivity() {}
  
  public static int a(int paramInt)
  {
    int i2 = n.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (n[i1] == paramInt) {
        return o[i1];
      }
      i1 += 1;
    }
    return 0;
  }
  
  private void a()
  {
    this.j = ((NavigationBar)findViewById(2131231830));
    this.j.setTitle(getString(2131559538));
    this.j.setUpLeftListener(this.C);
    if (getResources().getBoolean(2130968595))
    {
      this.j.setUpRightView(null, getString(2131559900), getResources().getDrawable(2131165497), 2131034189, new r(this));
      this.j.setUpRightViewBg(SkinManager.getInstance().getDrawable("btn_topbar_edge_selector"));
      this.j.setUpRightViewTextColor(SkinManager.getInstance().getColorStateList("btn_topbar_text_selector"));
    }
  }
  
  private void a(int paramInt, com.changdu.share.b paramB)
  {
    this.k.b(this, paramInt, paramB);
  }
  
  private void a(Map<String, String> paramMap)
  {
    String str2 = (String)paramMap.get("openid");
    String str1 = (String)paramMap.get("accessToken");
    if (com.changdu.changdulib.e.k.a(str1)) {
      paramMap = (String)paramMap.get("access_token");
    } else {
      paramMap = str1;
    }
    new com.changdu.zone.sessionmanage.a.j(this, true, 3, str2, paramMap, true, false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
  }
  
  private boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    int i1 = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null) {
      while (i1 < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i1)).packageName);
        i1 += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  private boolean a(String paramString)
  {
    return (paramString.contains("@")) && (paramString.contains("."));
  }
  
  private void b(int paramInt, Map<String, String> paramMap)
  {
    this.J = paramInt;
    this.K = paramMap;
    if (paramInt != 903)
    {
      switch (paramInt)
      {
      default: 
        break;
      case 3: 
        a(paramMap);
        return;
      case 2: 
        c(paramMap);
        return;
      case 1: 
        b(paramMap);
        return;
      }
    }
    else
    {
      String str1 = (String)paramMap.get("access_token");
      String str2 = (String)paramMap.get("access_token_secret");
      try
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(str1);
        localStringBuilder.append("|");
        localStringBuilder.append(str2);
        paramMap.put("access_token", com.changdu.changdulib.e.m.a(localStringBuilder.toString(), "UTF-8"));
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        localUnsupportedEncodingException.printStackTrace();
      }
    }
    a(paramInt, paramMap);
  }
  
  private void b(ab paramAb)
  {
    new m(this, this, new Intent(), paramAb.m(), paramAb.e(), paramAb.g(), paramAb.x(), paramAb.w(), paramAb.v(), paramAb.y(), false, paramAb).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
  }
  
  private void b(Map<String, String> paramMap)
  {
    String str2 = (String)paramMap.get("uid");
    String str1 = (String)paramMap.get("accessToken");
    if (com.changdu.changdulib.e.k.a(str1)) {
      paramMap = (String)paramMap.get("access_token");
    } else {
      paramMap = str1;
    }
    new com.changdu.zone.sessionmanage.a.j(this, true, 1, str2, paramMap, true, false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
  }
  
  private boolean b()
  {
    Intent localIntent = getIntent();
    boolean bool = false;
    if (localIntent != null) {
      bool = localIntent.getBooleanExtra("is_from_person", false);
    }
    return bool;
  }
  
  private void c()
  {
    Object localObject = new LinearLayout(this);
    ((LinearLayout)localObject).setOrientation(1);
    ((LinearLayout)localObject).setGravity(17);
    ((LinearLayout)localObject).setPadding(z.a(5.0F), z.a(10.0F), z.a(5.0F), z.a(10.0F));
    EditText localEditText = new EditText(this);
    localEditText.setBackgroundResource(2131166665);
    localEditText.setText("");
    localEditText.setInputType(32);
    localEditText.setMaxLines(1);
    localEditText.setTextColor(getResources().getColor(2131034237));
    localEditText.setTextSize(18.0F);
    localEditText.setGravity(17);
    localEditText.requestFocus();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams.setMargins(10, 20, 10, 20);
    localEditText.setLayoutParams(localLayoutParams);
    ((LinearLayout)localObject).addView(localEditText);
    localObject = new g(this, 2131560429, (View)localObject, 2131558561, 2131558691);
    ((g)localObject).show();
    ((g)localObject).a(new n(this, localEditText, (g)localObject));
    ((g)localObject).setCanceledOnTouchOutside(true);
    z.b(localEditText, 0L);
  }
  
  private void c(Map<String, String> paramMap)
  {
    String str2 = (String)paramMap.get("uid");
    String str1 = (String)paramMap.get("accessToken");
    if (com.changdu.changdulib.e.k.a(str1)) {
      paramMap = (String)paramMap.get("access_token");
    } else {
      paramMap = str1;
    }
    new com.changdu.zone.sessionmanage.a.j(this, true, a(2), str2, paramMap, true, false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
  }
  
  public void a(int paramInt, Map<String, String> paramMap)
  {
    String str2 = (String)paramMap.get("uid");
    String str1 = (String)paramMap.get("accessToken");
    if (com.changdu.changdulib.e.k.a(str1)) {
      paramMap = (String)paramMap.get("access_token");
    } else {
      paramMap = str1;
    }
    new com.changdu.zone.sessionmanage.a.j(this, true, a(paramInt), str2, paramMap, true, false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
  }
  
  public void a(ab paramAb)
  {
    if (this.J != 0) {
      if ((this.K != null) && (paramAb != null))
      {
        int i1 = this.J;
        if (i1 != 905)
        {
          switch (i1)
          {
          default: 
            switch (i1)
            {
            default: 
              break;
            case 903: 
              paramAb.e((String)this.K.get("iconurl"));
              paramAb.b((String)this.K.get("username"));
              break;
            case 901: 
            case 902: 
              paramAb.e((String)this.K.get("iconurl"));
              paramAb.b((String)this.K.get("name"));
            }
            break;
          case 3: 
            String str2 = (String)this.K.get("iconurl");
            String str1 = str2;
            if (com.changdu.changdulib.e.k.a(str2)) {
              str1 = (String)this.K.get("profile_image_url");
            }
            paramAb.e(str1);
            str2 = (String)this.K.get("name");
            str1 = str2;
            if (com.changdu.changdulib.e.k.a(str2)) {
              str1 = (String)this.K.get("screen_name");
            }
            paramAb.b(str1);
            paramAb.k((String)this.K.get("city"));
            paramAb.l((String)this.K.get("province"));
            paramAb.m((String)this.K.get("conntry"));
            break;
          case 2: 
            paramAb.e((String)this.K.get("iconurl"));
            paramAb.b((String)this.K.get("name"));
            paramAb.k((String)this.K.get("location"));
            break;
          case 1: 
            paramAb.e((String)this.K.get("iconurl"));
            paramAb.b((String)this.K.get("name"));
            paramAb.k((String)this.K.get("city"));
            paramAb.l((String)this.K.get("province"));
            break;
          }
        }
        else
        {
          paramAb.e((String)this.K.get("iconurl"));
          paramAb.b((String)this.K.get("name"));
        }
      }
      else
      {
        bk.b(getString(2131559541));
        return;
      }
    }
    this.J = 0;
    this.K = null;
    b(paramAb);
  }
  
  protected boolean finishSpecify()
  {
    z.a(this.u);
    if (this.E != null) {
      this.E.sendEmptyMessageDelayed(1000, 150L);
    }
    return true;
  }
  
  public BaseActivity.a getActivityType()
  {
    return BaseActivity.a.G;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (this.k != null) {
      this.k.a(paramInt1, paramInt2, paramIntent);
    }
    if (paramInt1 == 1)
    {
      if (paramInt2 == -1)
      {
        if (this.t != null) {
          this.t.setText(paramIntent.getStringExtra("GetResult"));
        }
        if (this.u != null) {
          this.u.setText(paramIntent.getStringExtra("GetPassword"));
        }
        if (this.x != null) {
          this.y = true;
        }
      }
      if (paramInt2 == 21842)
      {
        setResult(0, paramIntent);
        finish();
      }
    }
    Wait.b();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.l = ae.c(this);
    int i2 = 0;
    this.y = false;
    setContentView(2131362151);
    this.k = com.changdu.share.q.a(this);
    this.z = b();
    this.p = getBaseContext();
    paramBundle = new ArrayAdapter(this, 17367050, ae.a(this.p));
    a();
    this.t = ((AutoCompleteTextView)findViewById(2131231535));
    this.t.setAdapter(paramBundle);
    this.t.setThreshold(0);
    this.t.setOnEditorActionListener(this.F);
    this.u = ((EditText)findViewById(2131231534));
    this.v = ((TextView)findViewById(2131232158));
    this.w = ((TextView)findViewById(2131230809));
    boolean bool = getResources().getBoolean(2130968599);
    paramBundle = this.w;
    int i1;
    if (bool) {
      i1 = 0;
    } else {
      i1 = 8;
    }
    paramBundle.setVisibility(i1);
    this.w.setOnClickListener(new j(this));
    this.v.setOnClickListener(new p(this));
    this.x = ((Button)findViewById(2131231738));
    this.x.setOnClickListener(this.H);
    this.B = ((RelativeLayout)findViewById(2131232546));
    this.A = ((FrameLayout)findViewById(2131232545));
    this.k.a(this.A, new q(this));
    bool = com.changdu.share.q.a();
    paramBundle = this.B;
    if (bool) {
      i1 = 0;
    } else {
      i1 = 8;
    }
    paramBundle.setVisibility(i1);
    paramBundle = this.A;
    if (bool) {
      i1 = i2;
    } else {
      i1 = 8;
    }
    paramBundle.setVisibility(i1);
    z.c(this);
  }
  
  protected void onDestroy()
  {
    ab localAb = ae.c(this);
    if ((this.l != null) && (localAb != null) && (localAb.l() != this.l.l()))
    {
      RequestPlayStateReceiver.a(this, "");
      com.changdu.util.b.a();
    }
    super.onDestroy();
  }
  
  public boolean onFlingExitExcute()
  {
    z.a(this.u);
    return super.onFlingExitExcute();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  protected void onResume()
  {
    if (this.y)
    {
      this.y = false;
      this.x.performClick();
    }
    super.onResume();
  }
}
