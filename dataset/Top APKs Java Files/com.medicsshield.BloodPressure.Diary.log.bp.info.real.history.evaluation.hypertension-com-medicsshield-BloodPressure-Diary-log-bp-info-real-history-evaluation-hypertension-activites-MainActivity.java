package com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.activites;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.l;
import com.android.volley.p.a;
import com.android.volley.p.b;
import com.android.volley.t;
import com.android.volley.toolbox.n;
import com.android.volley.u;
import com.bumptech.glide.b;
import com.bumptech.glide.e;
import com.bumptech.glide.h;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.c.a;
import com.google.android.gms.ads.d;
import com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.fragment.DataFragment;
import com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.fragment.HistoryFragment;
import com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.utils.c;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MainActivity
  extends AppCompatActivity
  implements View.OnClickListener, c
{
  public static ArrayList<com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a> p;
  static String q = "http://hhhhhhhh.com/hxx.xml";
  public static Animation s;
  private HistoryFragment A;
  private FragmentTransaction B;
  private String C;
  com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.utils.a n;
  boolean o;
  public ArrayList<String> r = new ArrayList();
  private Context t;
  private LinearLayout u;
  private LinearLayout v;
  private RelativeLayout w;
  private RelativeLayout x;
  private FragmentManager y;
  private DataFragment z;
  
  public MainActivity() {}
  
  private boolean a(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  private static String b(String paramString, Element paramElement)
  {
    return paramElement.getElementsByTagName(paramString).item(0).getChildNodes().item(0).getNodeValue();
  }
  
  private void b()
  {
    this.u = ((LinearLayout)findViewById(2131230831));
    this.v = ((LinearLayout)findViewById(2131230832));
    this.w = ((RelativeLayout)findViewById(2131230782));
    this.x = ((RelativeLayout)findViewById(2131230819));
    this.z = new DataFragment();
    this.A = new HistoryFragment();
    this.y = getFragmentManager();
    this.z.setUserName(this.C);
    this.A.setUserName(this.C);
    this.r = getInstalledApps();
  }
  
  private void c()
  {
    this.u.setOnClickListener(this);
    this.v.setOnClickListener(this);
    this.B = this.y.beginTransaction();
    this.B.add(2131230782, this.z, "DataFragment");
    this.B.add(2131230819, this.A, "HistoryFragment").commit();
  }
  
  private void d()
  {
    try
    {
      ((LinearLayout)findViewById(2131230752)).setVisibility(0);
      ((RelativeLayout)findViewById(2131230755)).getLayoutParams().height = SplashScreen.dpToPx(5);
      AdView localAdView = new AdView(this);
      localAdView.setAdSize(d.a);
      localAdView.setAdUnitId(getResources().getString(2131492865));
      ((FrameLayout)findViewById(2131230754)).addView(localAdView);
      localAdView.loadAd(new c.a().build());
      Log.e("adview", localAdView.getAdUnitId());
      return;
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("MainActivity Ad Display Error");
      localStringBuilder.append(localException.getMessage());
      Log.e("Info", localStringBuilder.toString());
    }
  }
  
  public void exit()
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setContentView(2131361839);
    int i = getResources().getDisplayMetrics().widthPixels;
    int j = getResources().getDisplayMetrics().heightPixels;
    i -= i / 10;
    j -= j / 24;
    ((LinearLayout)localDialog.findViewById(2131230803)).getLayoutParams().height = ((int)(j / 1.3D));
    Object localObject1 = (LinearLayout)localDialog.findViewById(2131230840);
    ((LinearLayout)localObject1).getLayoutParams().width = i;
    localObject1 = ((LinearLayout)localObject1).getLayoutParams();
    int k = j / 10;
    int m = j / 12;
    ((ViewGroup.LayoutParams)localObject1).height = (k + (int)(m * 4.5D));
    localObject1 = (LinearLayout)localDialog.findViewById(2131230837);
    ((LinearLayout)localObject1).getLayoutParams().width = i;
    ((LinearLayout)localObject1).getLayoutParams().height = (m * 6);
    localObject1 = (RelativeLayout)localDialog.findViewById(2131230874);
    ((RelativeLayout)localObject1).getLayoutParams().width = i;
    localObject1 = ((RelativeLayout)localObject1).getLayoutParams();
    double d = j / 15;
    ((ViewGroup.LayoutParams)localObject1).height = ((int)(1.8D * d));
    localObject1 = (RelativeLayout)localDialog.findViewById(2131230875);
    ((RelativeLayout)localObject1).getLayoutParams().width = i;
    ((RelativeLayout)localObject1).getLayoutParams().height = ((int)(d * 1.5D));
    localObject1 = localDialog.findViewById(2131230962);
    ((View)localObject1).getLayoutParams().width = i;
    ((View)localObject1).getLayoutParams().height = (j / 11);
    localObject1 = (ImageView)localDialog.findViewById(2131230811);
    Object localObject2 = ((ImageView)localObject1).getLayoutParams();
    j = i / 3 - 20;
    ((ViewGroup.LayoutParams)localObject2).width = j;
    ((ImageView)localObject1).getLayoutParams().height = j;
    localObject2 = (ImageView)localDialog.findViewById(2131230901);
    ((ImageView)localObject2).getLayoutParams().width = j;
    ((ImageView)localObject2).getLayoutParams().height = j;
    ImageView localImageView1 = (ImageView)localDialog.findViewById(2131230934);
    localImageView1.getLayoutParams().width = j;
    localImageView1.getLayoutParams().height = j;
    ImageView localImageView2 = (ImageView)localDialog.findViewById(2131230815);
    localImageView2.getLayoutParams().width = j;
    localImageView2.getLayoutParams().height = j;
    Button localButton = (Button)localDialog.findViewById(2131230801);
    Object localObject3 = localButton.getLayoutParams();
    j = i / 4;
    ((ViewGroup.LayoutParams)localObject3).width = j;
    localObject3 = localButton.getLayoutParams();
    i /= 6;
    ((ViewGroup.LayoutParams)localObject3).height = i;
    localObject3 = (Button)localDialog.findViewById(2131230766);
    ((Button)localObject3).getLayoutParams().width = j;
    ((Button)localObject3).getLayoutParams().height = i;
    ((ImageView)localObject1).startAnimation(s);
    ((ImageView)localObject2).startAnimation(s);
    localImageView1.startAnimation(s);
    localImageView2.startAnimation(s);
    TextView localTextView1 = (TextView)localDialog.findViewById(2131230812);
    TextView localTextView2 = (TextView)localDialog.findViewById(2131230902);
    TextView localTextView3 = (TextView)localDialog.findViewById(2131230935);
    TextView localTextView4 = (TextView)localDialog.findViewById(2131230816);
    if ((p != null) && (p.size() >= 4))
    {
      localTextView1.setText(((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)p.get(0)).getAppName());
      localTextView2.setText(((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)p.get(1)).getAppName());
      localTextView3.setText(((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)p.get(2)).getAppName());
      localTextView4.setText(((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)p.get(3)).getAppName());
    }
    if ((p != null) && (p.size() >= 4))
    {
      e.with(getApplicationContext()).load(((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)p.get(0)).getImgUrl().toString()).placeholder(2131165290).error(2131165290).into((ImageView)localObject1);
      e.with(getApplicationContext()).load(((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)p.get(1)).getImgUrl().toString()).placeholder(2131165290).error(2131165290).into((ImageView)localObject2);
      e.with(getApplicationContext()).load(((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)p.get(2)).getImgUrl().toString()).placeholder(2131165290).error(2131165290).into(localImageView1);
      e.with(getApplicationContext()).load(((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)p.get(3)).getImgUrl().toString()).placeholder(2131165290).error(2131165290).into(localImageView2);
      ((ImageView)localObject1).startAnimation(s);
      ((ImageView)localObject2).startAnimation(s);
      localImageView1.startAnimation(s);
      localImageView2.startAnimation(s);
    }
    if ((this.o) && (p != null) && (p.size() >= 4))
    {
      ((ImageView)localObject1).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((MainActivity.p != null) && (MainActivity.p.size() >= 1)) {
            MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)MainActivity.p.get(0)).getAppUrl())));
          }
        }
      });
      ((ImageView)localObject2).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((MainActivity.p != null) && (MainActivity.p.size() >= 2)) {
            MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)MainActivity.p.get(1)).getAppUrl())));
          }
        }
      });
      localImageView1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((MainActivity.p != null) && (MainActivity.p.size() >= 3)) {
            MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)MainActivity.p.get(2)).getAppUrl())));
          }
        }
      });
      localImageView2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((MainActivity.p != null) && (MainActivity.p.size() >= 4)) {
            MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)MainActivity.p.get(3)).getAppUrl())));
          }
        }
      });
    }
    ((Button)localObject3).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
        MainActivity.this.finish();
      }
    });
    localDialog.setCancelable(false);
    localDialog.show();
  }
  
  public ArrayList<String> getInstalledApps()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = getPackageManager();
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    while (i < ((List)localObject).size())
    {
      PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
      if (!a(localPackageInfo)) {
        localArrayList.add(localPackageInfo.packageName);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public ArrayList<com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a> loadUndowloadedApp(ArrayList<com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramArrayList.size() > 0)
    {
      int i = 0;
      while (i < paramArrayList.size())
      {
        String str = ((com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a)paramArrayList.get(i)).getAppUrl().split("=")[1];
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("URl==>");
        localStringBuilder.append(str);
        Log.e("info ", localStringBuilder.toString());
        if (!this.r.contains(str)) {
          localArrayList.add(paramArrayList.get(i));
        }
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public void onBackPressed()
  {
    if (this.o)
    {
      exit();
      return;
    }
    finish();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131230832: 
      this.w.setVisibility(8);
      this.x.setVisibility(0);
      return;
    }
    this.w.setVisibility(0);
    this.x.setVisibility(8);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361823);
    this.t = this;
    this.C = getIntent().getExtras().getString("name");
    this.n = new com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.utils.a(this);
    this.o = this.n.isConnectingToInternet();
    s = new ScaleAnimation(1.0F, 0.9F, 1.0F, 0.9F, 1, 0.5F, 1, 0.5F);
    s.setDuration(300L);
    s.setInterpolator(new LinearInterpolator());
    s.setRepeatCount(-1);
    s.setRepeatMode(2);
    if (this.o)
    {
      p = new ArrayList();
      p.clear();
      xmlParsingUsingVolley(q);
      d();
    }
    b();
    c();
  }
  
  public void onItemAdded()
  {
    this.A.updateList();
  }
  
  public void xmlParsingUsingVolley(String paramString)
  {
    paramString = new n(0, paramString, new p.b()new p.a
    {
      public void onResponse(String paramAnonymousString)
      {
        try
        {
          Object localObject1 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
          Object localObject2 = new InputSource();
          ((InputSource)localObject2).setCharacterStream(new StringReader(paramAnonymousString));
          paramAnonymousString = ((DocumentBuilder)localObject1).parse((InputSource)localObject2).getElementsByTagName("app");
          localObject1 = new ArrayList();
          ((ArrayList)localObject1).clear();
          int i = 0;
          while (i < paramAnonymousString.getLength())
          {
            localObject2 = (Element)paramAnonymousString.item(i);
            com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a localA = new com.medicsshield.BloodPressure.Diary.log.bp.info.real.history.evaluation.hypertension.model.a();
            localA.setAppName(MainActivity.a("name", (Element)localObject2));
            localA.setAppUrl(MainActivity.a("url", (Element)localObject2));
            localA.setImgUrl(MainActivity.a("image", (Element)localObject2));
            ((ArrayList)localObject1).add(localA);
            i += 1;
          }
          paramAnonymousString = MainActivity.this.loadUndowloadedApp((ArrayList)localObject1);
          MainActivity.p.clear();
          if (paramAnonymousString.size() >= 4)
          {
            MainActivity.p.addAll(paramAnonymousString);
            return;
          }
        }
        catch (Exception paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
      }
    }, new p.a()
    {
      public void onErrorResponse(u paramAnonymousU)
      {
        if (((paramAnonymousU instanceof t)) || ((paramAnonymousU instanceof l))) {
          Log.e(" Internet problem ", " Loading....................");
        }
      }
    });
    com.android.volley.toolbox.o.newRequestQueue(this.t).add(paramString);
  }
}
