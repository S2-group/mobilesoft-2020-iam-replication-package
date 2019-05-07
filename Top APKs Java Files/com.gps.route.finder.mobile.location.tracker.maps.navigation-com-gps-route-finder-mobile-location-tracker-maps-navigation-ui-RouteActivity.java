package com.gps.route.finder.mobile.location.tracker.maps.navigation.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.avz;
import com.axo;
import com.aza;
import com.azb;
import com.azc;
import com.azc.b;
import com.azg;
import com.azy;
import com.bab;
import com.bac;
import com.baf;
import com.bag;
import com.brl;
import com.brn;
import com.bro;
import com.brr;
import com.brs;
import com.brt;
import com.bru;
import com.bse;
import com.buj;
import com.bvn;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.a;
import com.google.gson.Gson;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.adapter.StepProgressAdapter;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.adapter.TransitStepProgressAdapter;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.DistanceBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.DurationBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.DistanceBeanX;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.DurationBeanX;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.EndLocationBeanX;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.PolylineBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.StartLocationBeanX;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean.DistanceBeanXX;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean.DurationBeanXX;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean.EndLocationBeanXX;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean.StartLocationBeanXX;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.PlaceBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.StepProgressBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.TransitStepProgressBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.TransitSubWayBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.TransitSubWayBean.ArrivalStopBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.TransitSubWayBean.DepartureStopBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.TransitSubWayBean.LineBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.TransitSubWayBean.LineBean.VehicleBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.enity.VehicleBean;
import com.gps.route.finder.mobile.location.tracker.maps.navigation.view.CustomTextView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.c;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.d;
import com.umeng.analytics.MobclickAgent;
import com.yj;
import com.yx;
import com.yx.a;
import com.yx.b;
import com.yx.c;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RouteActivity
  extends brl
  implements ActivityCompat.OnRequestPermissionsResultCallback, yx.b, yx.c
{
  private String h;
  private String i;
  private LatLng j;
  private LatLng k;
  private LatLng l;
  private String m;
  @BindView
  ImageView mBack;
  @BindView
  ImageView mBicying;
  @BindView
  TextView mDes;
  @BindView
  ImageView mDriving;
  @BindView
  ImageView mIvJump;
  @BindView
  ImageView mIvRouteMapType;
  @BindView
  ImageView mLoading;
  @BindView
  ImageView mLocate;
  @BindView
  RelativeLayout mPanel;
  @BindView
  RelativeLayout mPanelContent;
  @BindView
  RelativeLayout mPanelDown;
  @BindView
  RelativeLayout mPanelNoShadow;
  @BindView
  LinearLayout mRadioGroup;
  @BindView
  ImageView mRightIcon;
  @BindView
  RelativeLayout mRlSummary;
  @BindView
  ImageView mShadowDown;
  @BindView
  SlidingUpPanelLayout mSlideLayout;
  @BindView
  Space mSpace;
  @BindView
  ImageView mSplitRouteListBelowHeader;
  @BindView
  TextView mSrc;
  @BindView
  RecyclerView mStepProgress;
  @BindView
  TextView mSummary;
  @BindView
  ImageView mSwap;
  @BindView
  CustomTextView mTitle;
  @BindView
  ImageView mTransit;
  @BindView
  ImageView mWalking;
  private String n;
  private StepProgressAdapter o;
  private TransitStepProgressAdapter p;
  private bab q;
  private bab r;
  private List s;
  private LinearLayoutManager t;
  private Context u;
  private bse v;
  private Intent w;
  private Intent x;
  private Call y;
  
  public RouteActivity() {}
  
  private void a(String paramString)
  {
    int i1 = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i1)
      {
      default: 
        return;
        if (paramString.equals("driving"))
        {
          i1 = 0;
          continue;
          if (paramString.equals("transit"))
          {
            i1 = 1;
            continue;
            if (paramString.equals("walking"))
            {
              i1 = 2;
              continue;
              if (paramString.equals("bicycling")) {
                i1 = 3;
              }
            }
          }
        }
        break;
      }
    }
    this.mDriving.setSelected(false);
    return;
    this.mTransit.setSelected(false);
    return;
    this.mWalking.setSelected(false);
    return;
    this.mBicying.setSelected(false);
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i1 = 0;
      while (i1 < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i1)).packageName);
        i1 += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  private String b()
  {
    Object localObject2 = null;
    Object localObject1;
    if (this.j == null)
    {
      localObject1 = localObject2;
      if (this.mDes.getText().toString().trim().equals(getResources().getString(2131755150)))
      {
        localObject1 = localObject2;
        if (this.l != null) {
          this.j = this.l;
        }
      }
    }
    else if (this.k == null)
    {
      localObject1 = localObject2;
      if (this.mSrc.getText().toString().trim().equals(getResources().getString(2131755150)))
      {
        localObject1 = localObject2;
        if (this.l != null) {
          this.k = this.l;
        }
      }
    }
    else
    {
      this.a.a();
      this.h = ("https://maps.googleapis.com/maps/api/directions/json?&origin=" + this.k.a + "," + this.k.b + "&destination=" + this.j.a + "," + this.j.b + "&mode=" + this.i + "&key=AIzaSyD6GoKGIpcMw_BB2mUifK29kQxlTOm5o1Q");
      localObject1 = this.h;
    }
    return localObject1;
  }
  
  private void b(String paramString)
  {
    if ((this.a == null) || (paramString == null)) {}
    label353:
    for (;;)
    {
      return;
      this.mSummary.setText(2131755167);
      this.mSummary.setTypeface(brs.a(this));
      this.mLoading.setVisibility(0);
      Object localObject = new RotateAnimation(0.0F, 360.0F, 1, 0.5F, 1, 0.5F);
      ((Animation)localObject).setInterpolator(new LinearInterpolator());
      ((Animation)localObject).setDuration(800L);
      ((Animation)localObject).setRepeatCount(-1);
      this.mLoading.startAnimation((Animation)localObject);
      this.mSpace.setVisibility(0);
      if (this.s == null) {
        this.s = new ArrayList();
      }
      for (;;)
      {
        if (this.j == null) {
          break label353;
        }
        this.mSlideLayout.setPanelHeight(getResources().getDimensionPixelSize(2131165427));
        if (this.q != null) {
          this.q.a();
        }
        if (this.r != null) {
          this.r.a();
        }
        localObject = this.a;
        bac localBac = new bac().a(this.k);
        localBac.b = azy.a(2131230952);
        this.q = ((azc)localObject).a(localBac);
        localObject = this.a;
        localBac = new bac().a(this.j);
        localBac.b = azy.a(2131230951);
        this.r = ((azc)localObject).a(localBac);
        this.a.a(azb.a(this.j, 16.0F));
        if (paramString == null) {
          break;
        }
        this.y = new OkHttpClient().newCall(new Request.Builder().url(paramString).build());
        this.y.enqueue(new Callback()
        {
          public final void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException)
          {
            new StringBuilder("onFailure() e=").append(paramAnonymousIOException);
          }
          
          public final void onResponse(final Call paramAnonymousCall, Response paramAnonymousResponse)
            throws IOException
          {
            paramAnonymousCall = paramAnonymousResponse.body().string();
            final ArrayList localArrayList = new ArrayList();
            final DistancesBean localDistancesBean;
            if (paramAnonymousResponse.isSuccessful())
            {
              localDistancesBean = (DistancesBean)new Gson().fromJson(paramAnonymousCall, DistancesBean.class);
              if (!localDistancesBean.getStatus().equals("ZERO_RESULTS")) {
                break label71;
              }
              RouteActivity.this.runOnUiThread(new Runnable()
              {
                public final void run()
                {
                  Toast.makeText(RouteActivity.this, 2131755179, 0).show();
                  RouteActivity.this.mLoading.clearAnimation();
                  RouteActivity.this.mLoading.setVisibility(8);
                  RouteActivity.this.mSummary.setText(2131755205);
                  RouteActivity.this.mSummary.setTypeface(brs.b(RouteActivity.a(RouteActivity.this)));
                  RouteActivity.this.mIvJump.setVisibility(0);
                }
              });
            }
            label71:
            do
            {
              return;
              if ((localDistancesBean.getStatus().equals("OVER_QUERY_LIMIT")) || (paramAnonymousCall.contains("error_message")))
              {
                RouteActivity.this.runOnUiThread(new Runnable()
                {
                  public final void run()
                  {
                    RouteActivity.this.mLoading.clearAnimation();
                    RouteActivity.this.mLoading.setVisibility(8);
                    RouteActivity.this.mSummary.setText(2131755178);
                    RouteActivity.this.mSummary.setTypeface(brs.b(RouteActivity.a(RouteActivity.this)));
                    RouteActivity.this.mIvJump.setVisibility(0);
                  }
                });
                return;
              }
            } while (localDistancesBean.getRoutes().size() <= 0);
            int j = 0;
            for (;;)
            {
              if (j >= localDistancesBean.getRoutes().size()) {
                break label1338;
              }
              int k = 0;
              for (;;)
              {
                if (k >= ((DistancesBean.RoutesBean)localDistancesBean.getRoutes().get(j)).getLegs().size()) {
                  break label1329;
                }
                int m = 0;
                label174:
                if (m < ((DistancesBean.RoutesBean.LegsBean)((DistancesBean.RoutesBean)localDistancesBean.getRoutes().get(j)).getLegs().get(k)).getSteps().size())
                {
                  localArrayList.addAll(brn.a(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)((DistancesBean.RoutesBean.LegsBean)((DistancesBean.RoutesBean)localDistancesBean.getRoutes().get(j)).getLegs().get(k)).getSteps().get(m)).getPolyline().getPoints()));
                  paramAnonymousResponse = (DistancesBean.RoutesBean.LegsBean.StepsBeanX)((DistancesBean.RoutesBean.LegsBean)((DistancesBean.RoutesBean)localDistancesBean.getRoutes().get(j)).getLegs().get(k)).getSteps().get(m);
                  label332:
                  Object localObject;
                  String str2;
                  int n;
                  String str1;
                  int i;
                  if (Build.VERSION.SDK_INT >= 24)
                  {
                    paramAnonymousCall = Html.fromHtml(paramAnonymousResponse.getHtml_instructions(), 0);
                    if (!"transit".equals(RouteActivity.b(RouteActivity.this))) {
                      break label1203;
                    }
                    localObject = new Gson();
                    str2 = ((Gson)localObject).toJson(paramAnonymousResponse);
                    n = str2.indexOf("travel_mode");
                    str1 = str2.substring(n + 14, n + 18);
                    i = -1;
                    switch (str1.hashCode())
                    {
                    default: 
                      label424:
                      switch (i)
                      {
                      }
                      break;
                    }
                  }
                  for (;;)
                  {
                    m += 1;
                    break label174;
                    paramAnonymousCall = Html.fromHtml(paramAnonymousResponse.getHtml_instructions());
                    break label332;
                    if (!str1.equals("WALK")) {
                      break label424;
                    }
                    i = 0;
                    break label424;
                    if (!str1.equals("TRAN")) {
                      break label424;
                    }
                    i = 1;
                    break label424;
                    if ((paramAnonymousResponse == null) || (paramAnonymousResponse.getSteps() == null) || (paramAnonymousResponse.getSteps().size() == 0)) {
                      break;
                    }
                    i = 0;
                    if (i < paramAnonymousResponse.getSteps().size())
                    {
                      localObject = (DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)paramAnonymousResponse.getSteps().get(i);
                      paramAnonymousCall = "walk distance" + ((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject).getDistance().getText() + "(" + ((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject).getDuration().getText() + ")";
                      if (Build.VERSION.SDK_INT >= 24)
                      {
                        Html.fromHtml(paramAnonymousCall, 0);
                        if (((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject).getHtml_instructions() != null) {
                          paramAnonymousCall = Html.fromHtml(((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject).getHtml_instructions(), 0);
                        }
                      }
                      for (;;)
                      {
                        paramAnonymousCall = new StepProgressBean(paramAnonymousCall, ((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject).getStart_location().getLat() + "," + ((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject).getStart_location().getLng(), ((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject).getEnd_location().getLat() + "," + ((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject).getEnd_location().getLng(), ((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject).getDistance().getText(), ((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject).getDuration().getText(), str1);
                        RouteActivity.c(RouteActivity.this).add(paramAnonymousCall);
                        i += 1;
                        break;
                        if (paramAnonymousResponse.getHtml_instructions() != null)
                        {
                          paramAnonymousCall = Html.fromHtml(paramAnonymousResponse.getHtml_instructions(), 0);
                        }
                        else
                        {
                          paramAnonymousCall = "";
                          continue;
                          Html.fromHtml(paramAnonymousCall);
                          if (((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject).getHtml_instructions() != null) {
                            paramAnonymousCall = Html.fromHtml(((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject).getHtml_instructions());
                          } else {
                            paramAnonymousCall = "";
                          }
                        }
                      }
                      i = str2.indexOf("transit_details");
                      str1 = str2.substring(n + 14, n + 22);
                      localObject = (TransitSubWayBean)((Gson)localObject).fromJson(str2.substring(i + 17, n - 2), TransitSubWayBean.class);
                      String str7 = "departure stop" + ((TransitSubWayBean)localObject).getDeparture_stop().getName() + "arrival stop" + ((TransitSubWayBean)localObject).getArrival_stop().getName() + "stop nums" + ((TransitSubWayBean)localObject).getNum_stops() + RouteActivity.this.getString(2131755197) + "lines" + ((TransitSubWayBean)localObject).getLine().getShort_name() + "time" + paramAnonymousResponse.getDuration().getText();
                      str2 = ((TransitSubWayBean)localObject).getLine().getVehicle().getIcon();
                      String str3 = ((TransitSubWayBean)localObject).getDeparture_stop().getName();
                      String str4 = ((TransitSubWayBean)localObject).getArrival_stop().getName();
                      String str5 = ((TransitSubWayBean)localObject).getLine().getShort_name();
                      new StringBuilder().append(((TransitSubWayBean)localObject).getNum_stops()).append("(").append(paramAnonymousResponse.getDuration().getText()).append(")");
                      String str6 = "<font color='#4e86f2'>" + ((TransitSubWayBean)localObject).getNum_stops() + RouteActivity.this.getString(2131755197) + "</font><font color='#6c6c6c'>(" + paramAnonymousResponse.getDuration().getText() + ")</font>";
                      if (Build.VERSION.SDK_INT >= 24) {}
                      for (paramAnonymousResponse = Html.fromHtml(str7, 0);; paramAnonymousResponse = Html.fromHtml(str7))
                      {
                        paramAnonymousCall = new TransitStepProgressBean(paramAnonymousCall, str3, str4, str6, str6, str1, paramAnonymousResponse, str5, str2, 1, new VehicleBean(((TransitSubWayBean)localObject).getLine().getVehicle().getType(), ((TransitSubWayBean)localObject).getLine().getColor(), ((TransitSubWayBean)localObject).getLine().getVehicle().getLocal_icon()));
                        RouteActivity.c(RouteActivity.this).add(paramAnonymousCall);
                        break;
                      }
                      label1203:
                      paramAnonymousCall = new StepProgressBean(paramAnonymousCall, paramAnonymousResponse.getStart_location().getLat() + "," + paramAnonymousResponse.getStart_location().getLng(), paramAnonymousResponse.getEnd_location().getLat() + "," + paramAnonymousResponse.getEnd_location().getLng(), paramAnonymousResponse.getDistance().getText(), paramAnonymousResponse.getDuration().getText(), RouteActivity.b(RouteActivity.this));
                      RouteActivity.c(RouteActivity.this).add(paramAnonymousCall);
                    }
                  }
                }
                k += 1;
              }
              label1329:
              j += 1;
            }
            label1338:
            paramAnonymousCall = new baf();
            paramAnonymousCall.a(localArrayList);
            paramAnonymousCall.a = 18.0F;
            paramAnonymousCall.b = Color.parseColor("#67f0f9");
            RouteActivity.this.runOnUiThread(new Runnable()
            {
              public final void run()
              {
                if (RouteActivity.this.a == null) {
                  return;
                }
                RouteActivity.this.a.a();
                RouteActivity.this.a.a(paramAnonymousCall);
                RouteActivity.this.mIvJump.setVisibility(0);
                bvn.a(RouteActivity.this);
                if (localArrayList.size() > 0)
                {
                  localObject = LatLngBounds.a();
                  int i = 0;
                  while (i < localArrayList.size())
                  {
                    ((LatLngBounds.a)localObject).a((LatLng)localArrayList.get(i));
                    i += 1;
                  }
                  localObject = azb.a(((LatLngBounds.a)localObject).a());
                  RouteActivity.this.a.a((aza)localObject);
                }
                Object localObject = ((DistancesBean.RoutesBean.LegsBean)((DistancesBean.RoutesBean)localDistancesBean.getRoutes().get(0)).getLegs().get(0)).getDistance().getText();
                String str = ((DistancesBean.RoutesBean.LegsBean)((DistancesBean.RoutesBean)localDistancesBean.getRoutes().get(0)).getLegs().get(0)).getDuration().getText();
                RouteActivity localRouteActivity = RouteActivity.this;
                azc localAzc = RouteActivity.this.a;
                bac localBac = new bac().a(RouteActivity.d(RouteActivity.this));
                localBac.b = azy.a(2131230952);
                RouteActivity.a(localRouteActivity, localAzc.a(localBac));
                localRouteActivity = RouteActivity.this;
                localAzc = RouteActivity.this.a;
                localBac = new bac().a(RouteActivity.e(RouteActivity.this));
                localBac.b = azy.a(2131230951);
                RouteActivity.b(localRouteActivity, localAzc.a(localBac));
                if (RouteActivity.this.e != null) {
                  RouteActivity.this.e.a();
                }
                localRouteActivity = RouteActivity.this;
                localAzc = RouteActivity.this.a;
                localBac = new bac().a(RouteActivity.f(RouteActivity.this));
                localBac.b = azy.a(2131230950);
                localRouteActivity.e = localAzc.a(localBac);
                RouteActivity.this.mLoading.clearAnimation();
                RouteActivity.this.mLoading.setVisibility(8);
                RouteActivity.this.mSummary.setText(str + "( " + (String)localObject + " )");
                RouteActivity.this.mSummary.setTypeface(brs.b(RouteActivity.a(RouteActivity.this)));
                RouteActivity.this.mSpace.setVisibility(8);
                if (RouteActivity.this.mStepProgress.getLayoutManager() == null)
                {
                  RouteActivity.a(RouteActivity.this, new LinearLayoutManager(RouteActivity.this, 1, false));
                  RouteActivity.this.mStepProgress.setLayoutManager(RouteActivity.g(RouteActivity.this));
                }
                if (RouteActivity.b(RouteActivity.this).equals("transit"))
                {
                  RouteActivity.c(RouteActivity.this).add(0, RouteActivity.this.mSrc.getText().toString().trim());
                  RouteActivity.c(RouteActivity.this).add(RouteActivity.this.mDes.getText().toString().trim());
                  if (RouteActivity.h(RouteActivity.this) == null) {
                    RouteActivity.a(RouteActivity.this, new TransitStepProgressAdapter(RouteActivity.this, RouteActivity.c(RouteActivity.this)));
                  }
                  for (;;)
                  {
                    RouteActivity.this.mStepProgress.setAdapter(RouteActivity.h(RouteActivity.this));
                    return;
                    RouteActivity.h(RouteActivity.this).notifyDataSetChanged();
                  }
                }
                RouteActivity.c(RouteActivity.this).add(0, RouteActivity.this.mSrc.getText().toString().trim());
                RouteActivity.c(RouteActivity.this).add(RouteActivity.this.mDes.getText().toString().trim());
                if (RouteActivity.i(RouteActivity.this) == null) {
                  RouteActivity.a(RouteActivity.this, new StepProgressAdapter(RouteActivity.this, RouteActivity.c(RouteActivity.this)));
                }
                for (;;)
                {
                  RouteActivity.this.mStepProgress.setAdapter(RouteActivity.i(RouteActivity.this));
                  return;
                  RouteActivity.i(RouteActivity.this).notifyDataSetChanged();
                }
              }
            });
          }
        });
        return;
        this.s.clear();
        if (this.o != null) {
          this.o.notifyDataSetChanged();
        }
        if (this.p != null) {
          this.p.notifyDataSetChanged();
        }
      }
    }
  }
  
  public final void a()
  {
    if ((ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) && (this.a != null)) {
      this.a.b();
    }
  }
  
  public final void a(int paramInt) {}
  
  public final void a(Bundle paramBundle)
  {
    if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
      return;
    }
    Object localObject = (LocationManager)getSystemService("location");
    int i1;
    if (localObject != null)
    {
      paramBundle = this.i;
      i1 = -1;
      switch (paramBundle.hashCode())
      {
      default: 
        switch (i1)
        {
        default: 
          label80:
          label112:
          if (getIntent().hasExtra("pass_url"))
          {
            this.h = getIntent().getStringExtra("pass_url");
            if (getIntent().hasExtra("pass_name"))
            {
              paramBundle = getIntent().getStringExtra("pass_name");
              this.mDes.setText(paramBundle);
            }
            if (getIntent().hasExtra("pass_latlng")) {
              this.j = ((LatLng)getIntent().getParcelableExtra("pass_latlng"));
            }
            if (getIntent().hasExtra("locate_latlng"))
            {
              this.l = ((LatLng)getIntent().getParcelableExtra("locate_latlng"));
              this.k = this.l;
            }
            b(this.h);
            getIntent().removeExtra("pass_url");
            paramBundle = null;
          }
          break;
        }
        break;
      }
    }
    while (paramBundle != null)
    {
      paramBundle = brn.a(paramBundle.getLatitude(), paramBundle.getLongitude());
      this.l = new LatLng(paramBundle[0], paramBundle[1]);
      this.a.a(azb.a(this.l, 15.0F));
      if (this.e != null) {
        this.e.a();
      }
      paramBundle = this.a;
      localObject = new bac().a(this.l);
      ((bac)localObject).b = azy.a(2131230950);
      this.e = paramBundle.a((bac)localObject);
      return;
      if (!paramBundle.equals("driving")) {
        break label80;
      }
      i1 = 0;
      break label80;
      if (!paramBundle.equals("transit")) {
        break label80;
      }
      i1 = 1;
      break label80;
      if (!paramBundle.equals("walking")) {
        break label80;
      }
      i1 = 2;
      break label80;
      if (!paramBundle.equals("bicycling")) {
        break label80;
      }
      i1 = 3;
      break label80;
      this.mDriving.setSelected(true);
      break label112;
      this.mTransit.setSelected(true);
      break label112;
      this.mWalking.setSelected(true);
      break label112;
      this.mBicying.setSelected(true);
      break label112;
      paramBundle = ((LocationManager)localObject).getLastKnownLocation("network");
      ((LocationManager)localObject).requestLocationUpdates("network", 1000L, 10.0F, new LocationListener()
      {
        public final void onLocationChanged(Location paramAnonymousLocation)
        {
          if (RouteActivity.this.e != null) {
            RouteActivity.this.e.a();
          }
          paramAnonymousLocation = brn.a(paramAnonymousLocation.getLatitude(), paramAnonymousLocation.getLongitude());
          RouteActivity.a(RouteActivity.this, new LatLng(paramAnonymousLocation[0], paramAnonymousLocation[1]));
          if (RouteActivity.this.e != null) {
            RouteActivity.this.e.a();
          }
          paramAnonymousLocation = RouteActivity.this;
          azc localAzc = RouteActivity.this.a;
          bac localBac = new bac().a(RouteActivity.f(RouteActivity.this));
          localBac.b = azy.a(2131230950);
          paramAnonymousLocation.e = localAzc.a(localBac);
        }
        
        public final void onProviderDisabled(String paramAnonymousString) {}
        
        public final void onProviderEnabled(String paramAnonymousString) {}
        
        public final void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle) {}
      });
      continue;
      paramBundle = null;
    }
  }
  
  public final void a(azc paramAzc)
  {
    super.a(paramAzc);
    paramAzc = this.a;
    int i1 = getResources().getDimensionPixelSize(2131165428);
    try
    {
      paramAzc.a.b(i1);
      this.a.a(new azc.b()
      {
        public final boolean a(bab paramAnonymousBab)
        {
          paramAnonymousBab.e();
          return true;
        }
      });
      return;
    }
    catch (RemoteException paramAzc)
    {
      throw new bag(paramAzc);
    }
  }
  
  public final void a(yj paramYj)
  {
    Toast.makeText(this, "Error" + paramYj.b, 0).show();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramIntent == null) {}
    label195:
    label201:
    label274:
    label313:
    label315:
    do
    {
      for (;;)
      {
        return;
        Object localObject1;
        Object localObject2;
        int i1;
        if (paramInt1 == 123)
        {
          if (paramInt2 != -1) {
            break label274;
          }
          localObject1 = paramIntent.getStringExtra("place_bean_string");
          localObject2 = (PlaceBean)new Gson().fromJson((String)localObject1, PlaceBean.class);
          this.mSrc.setText(((PlaceBean)localObject2).getName());
          this.k = ((PlaceBean)localObject2).getLatLng();
          if ((!TextUtils.isEmpty(this.mSrc.getText().toString().trim())) && (!TextUtils.isEmpty(this.mDes.getText().toString().trim()))) {
            break label195;
          }
          i1 = 1;
          if (i1 != 0) {
            break label201;
          }
          b(b());
        }
        for (;;)
        {
          if (paramInt1 != 321) {
            break label313;
          }
          localObject1 = paramIntent.getStringExtra("place_bean_string");
          localObject1 = (PlaceBean)new Gson().fromJson((String)localObject1, PlaceBean.class);
          if (paramInt2 != -1) {
            break label315;
          }
          this.j = ((PlaceBean)localObject1).getLatLng();
          this.mDes.setText(((PlaceBean)localObject1).getName());
          b(b());
          return;
          i1 = 0;
          break;
          this.a.a(azb.a(((PlaceBean)localObject2).getLatLng(), 15.0F));
          if (this.q == null)
          {
            localObject1 = this.a;
            localObject2 = new bac().a(((PlaceBean)localObject2).getLatLng());
            ((bac)localObject2).b = azy.a(2131230952);
            this.q = ((azc)localObject1).a((bac)localObject2);
            continue;
            if (paramInt2 == 2)
            {
              localObject1 = axo.a(this, paramIntent);
              Toast.makeText(this, ((Status)localObject1).toString(), 0).show();
            }
          }
        }
      }
    } while (paramInt2 != 2);
    paramIntent = axo.a(this, paramIntent);
    Toast.makeText(this, paramIntent.toString(), 0).show();
  }
  
  public void onBackPressed()
  {
    if (this.mSlideLayout.getPanelState().equals(SlidingUpPanelLayout.d.a))
    {
      this.mSlideLayout.setPanelState(SlidingUpPanelLayout.d.b);
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131558430);
    ButterKnife.a(this);
    bro.a(this, getString(2131755180));
    this.u = this;
    this.c = new yx.a(this).a(avz.a).a(avz.b).a(this).a(this).b();
    this.c.e();
    this.v = new bse(this);
    this.b = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(2131361941));
    this.b.a(this);
    this.i = brr.a(this, "map_mode", "driving");
    this.mSlideLayout.setPanelState(SlidingUpPanelLayout.d.b);
    this.mSlideLayout.setPanelHeight(0);
    this.mSlideLayout.a(new SlidingUpPanelLayout.c()
    {
      public final void a(SlidingUpPanelLayout.d paramAnonymousD1, SlidingUpPanelLayout.d paramAnonymousD2)
      {
        if (paramAnonymousD2 == SlidingUpPanelLayout.d.b) {
          brt.a(RouteActivity.this.mPanel, false);
        }
        if (paramAnonymousD1 == SlidingUpPanelLayout.d.b) {
          brt.a(RouteActivity.this.mPanel, true);
        }
      }
    });
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    String str = this.i;
    SharedPreferences.Editor localEditor = getSharedPreferences("map_prefs", 0).edit();
    localEditor.putString("map_mode", str);
    localEditor.commit();
  }
  
  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }
  
  public void onPointerCaptureChanged(boolean paramBoolean) {}
  
  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }
  
  @OnClick
  public void onViewClicked()
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    Fragment localFragment = getSupportFragmentManager().findFragmentByTag("dialogFragment");
    if (localFragment != null) {
      localFragmentTransaction.remove(localFragment);
    }
    new bru().show(localFragmentTransaction, "dialogFragment");
  }
  
  @OnClick
  public void onViewClicked(View paramView)
  {
    switch (paramView.getId())
    {
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return;
                paramView = this.mSrc.getText().toString().trim();
                str = this.mDes.getText().toString().trim();
                this.mSrc.setText(str);
                this.mDes.setText(paramView);
                paramView = this.j;
                this.j = this.k;
                this.k = paramView;
                if (this.q != null) {
                  this.q.a();
                }
                if (this.r != null) {
                  this.r.a();
                }
                paramView = this.mSrc.getHint().toString().trim();
                str = this.mDes.getHint().toString().trim();
              } while (((TextUtils.isEmpty(this.mSrc.getText().toString().trim())) && (paramView.equals(getResources().getString(2131755195)))) || ((TextUtils.isEmpty(this.mDes.getText().toString().trim())) && (str.equals(getResources().getString(2131755084)))));
              b(b());
              return;
            } while (this.l == null);
            this.a.a(azb.a(this.l, 15.0F));
            return;
          } while (bro.a());
          brn.a(this, this.m, this.n, this.l);
          return;
          a(this.i);
          this.i = "driving";
          this.mDriving.setSelected(true);
          b(b());
          return;
          a(this.i);
          this.i = "transit";
          this.mTransit.setSelected(true);
          b(b());
          return;
          a(this.i);
          this.i = "walking";
          this.mWalking.setSelected(true);
          b(b());
          return;
          a(this.i);
          this.i = "bicycling";
          this.mBicying.setSelected(true);
          b(b());
          return;
        } while (this.l == null);
        if (this.w == null) {
          this.w = new Intent(this.u, SearchResultActivity.class);
        }
        d1 = this.l.a;
        d2 = this.l.b;
        this.w.putExtra("locate_latlng", new double[] { d1, d2 });
        startActivityForResult(this.w, 321);
        return;
      } while (this.l == null);
      if (this.x == null) {
        this.x = new Intent(this.u, SearchResultActivity.class);
      }
      double d1 = this.l.a;
      double d2 = this.l.b;
      this.x.putExtra("locate_latlng", new double[] { d1, d2 });
      startActivityForResult(this.x, 123);
      return;
    } while ((TextUtils.isEmpty(this.mSrc.getText())) || (TextUtils.isEmpty(this.mDes.getText())));
    paramView = "&origin=" + this.mSrc.getText().toString().trim();
    String str = "&destination=" + this.mDes.getText().toString().trim();
    if (this.mSrc.getText().toString().trim().equals(getString(2131755150))) {
      paramView = "";
    }
    if (this.mDes.getText().toString().trim().equals(getString(2131755150))) {
      str = "&destination=" + this.l.a + "," + this.l.b;
    }
    paramView = "https://www.google.com/maps/dir/?api=1" + paramView + str + "&travelmode=" + this.i + "&key=AIzaSyD6GoKGIpcMw_BB2mUifK29kQxlTOm5o1Q";
    if (a(this, "com.google.android.apps.maps"))
    {
      this.y.cancel();
      paramView = new Intent("android.intent.action.VIEW", Uri.parse(paramView));
      paramView.setPackage("com.google.android.apps.maps");
      startActivity(paramView);
      return;
    }
    buj.a(this, "com.google.android.apps.maps");
    Toast.makeText(this, 2131755154, 1).show();
  }
}
