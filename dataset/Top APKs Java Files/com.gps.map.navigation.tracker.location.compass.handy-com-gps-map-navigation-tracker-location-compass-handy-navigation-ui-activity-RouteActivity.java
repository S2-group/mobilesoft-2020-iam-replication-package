package com.gps.map.navigation.tracker.location.compass.handy.navigation.ui.activity;

import android.content.Context;
import android.content.Intent;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.abz;
import com.ado;
import com.ady;
import com.adz;
import com.aea;
import com.aea.d;
import com.aed;
import com.afi;
import com.afl;
import com.afm;
import com.afr;
import com.ajp;
import com.ajr;
import com.ajv;
import com.ajw;
import com.ajy;
import com.ajz;
import com.akd;
import com.akn;
import com.amr;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.a;
import com.google.gson.Gson;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.adapter.StepProgressAdapter;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.adapter.TransitStepProgressAdapter;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.DistanceBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.DurationBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.DistanceBeanX;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.DurationBeanX;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.EndLocationBeanX;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.PolylineBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.StartLocationBeanX;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean.DistanceBeanXX;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean.DurationBeanXX;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean.EndLocationBeanXX;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean.StartLocationBeanXX;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.PlaceBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.StepProgressBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.TransitStepProgressBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.TransitSubWayBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.TransitSubWayBean.ArrivalStopBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.TransitSubWayBean.DepartureStopBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.TransitSubWayBean.LineBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.TransitSubWayBean.LineBean.VehicleBean;
import com.gps.map.navigation.tracker.location.compass.handy.navigation.enity.VehicleBean;
import com.qu;
import com.rh;
import com.rh.a;
import com.rh.b;
import com.rh.c;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.c;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.d;
import com.umeng.analytics.MobclickAgent;
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
  extends ajp
  implements ActivityCompat.OnRequestPermissionsResultCallback, rh.b, rh.c
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
  LinearLayout mRadioGroup;
  @BindView
  RelativeLayout mRlSummary;
  @BindView
  LinearLayout mRlSummarySencond;
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
  TextView mSummaryDitance;
  @BindView
  TextView mSummaryTime;
  @BindView
  ImageView mSwap;
  @BindView
  ImageView mTransit;
  @BindView
  TextView mTvJump;
  @BindView
  ImageView mWalking;
  private String n;
  private StepProgressAdapter o;
  private TransitStepProgressAdapter p;
  private afl q;
  private afl r;
  private List s;
  private LinearLayoutManager t;
  private Context u;
  private akn v;
  private Intent w;
  private Intent x;
  private Call y;
  
  public RouteActivity() {}
  
  private String a()
  {
    if (this.j == null) {
      if ((this.mDes.getText().toString().trim().equals(getResources().getString(2131689583))) && (this.l != null)) {
        this.j = this.l;
      } else {
        return null;
      }
    }
    if (this.k == null) {
      if ((this.mSrc.getText().toString().trim().equals(getResources().getString(2131689583))) && (this.l != null)) {
        this.k = this.l;
      } else {
        return null;
      }
    }
    this.a.b();
    StringBuilder localStringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?&origin=");
    localStringBuilder.append(this.k.a);
    localStringBuilder.append(",");
    localStringBuilder.append(this.k.b);
    localStringBuilder.append("&destination=");
    localStringBuilder.append(this.j.a);
    localStringBuilder.append(",");
    localStringBuilder.append(this.j.b);
    localStringBuilder.append("&mode=");
    localStringBuilder.append(this.i);
    localStringBuilder.append("&key=AIzaSyD9cW_4fKYd3t7DrJQjB-v5Bai2Tv2Qdrc");
    this.h = localStringBuilder.toString();
    return this.h;
  }
  
  public static String a(String paramString)
  {
    return paramString.replace("<b>", "").replace("</b>", "");
  }
  
  private static boolean a(Context paramContext, String paramString)
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
  
  private void b(String paramString)
  {
    int i1 = paramString.hashCode();
    if (i1 != -1497957892)
    {
      if (i1 != -1067059757)
      {
        if (i1 != 1118815609)
        {
          if ((i1 == 1920367559) && (paramString.equals("driving")))
          {
            i1 = 0;
            break label96;
          }
        }
        else if (paramString.equals("walking"))
        {
          i1 = 2;
          break label96;
        }
      }
      else if (paramString.equals("transit"))
      {
        i1 = 1;
        break label96;
      }
    }
    else if (paramString.equals("bicycling"))
    {
      i1 = 3;
      break label96;
    }
    i1 = -1;
    switch (i1)
    {
    default: 
      return;
    case 3: 
      this.mBicying.setSelected(false);
      return;
    case 2: 
      label96:
      paramString = this.mWalking;
    }
    for (;;)
    {
      paramString.setSelected(false);
      return;
      paramString = this.mTransit;
      continue;
      paramString = this.mDriving;
    }
  }
  
  private void c(String paramString)
  {
    this.mSlideLayout.setEnabled(false);
    if (this.a != null)
    {
      if (paramString == null) {
        return;
      }
      this.mRlSummary.setVisibility(0);
      this.mSummary.setText(2131689602);
      this.mRlSummarySencond.setVisibility(8);
      ajz.a(this.mLoading);
      this.mSpace.setVisibility(0);
      if (this.s == null) {
        this.s = new ArrayList();
      } else {
        this.s.clear();
      }
      if (this.j == null) {
        return;
      }
      this.mSlideLayout.setPanelHeight(getResources().getDimensionPixelSize(2131165393));
      if (this.q != null) {
        this.q.a();
      }
      if (this.r != null) {
        this.r.a();
      }
      aea localAea = this.a;
      afm localAfm = new afm().a(this.k);
      localAfm.b = afi.a(2131231001);
      this.q = localAea.a(localAfm);
      localAea = this.a;
      localAfm = new afm().a(this.j);
      localAfm.b = afi.a(2131230993);
      this.r = localAea.a(localAfm);
      this.a.b(adz.a(this.j, 16.0F));
      if (paramString == null) {
        return;
      }
      this.y = new OkHttpClient().newCall(new Request.Builder().url(paramString).build());
      this.y.enqueue(new Callback()
      {
        public final void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException)
        {
          new StringBuilder("onFailure() e=").append(paramAnonymousIOException);
        }
        
        public final void onResponse(final Call paramAnonymousCall, final Response paramAnonymousResponse)
          throws IOException
        {
          Object localObject1 = paramAnonymousResponse.body().string();
          paramAnonymousCall = new ArrayList();
          if (paramAnonymousResponse.isSuccessful())
          {
            RouteActivity.a(RouteActivity.this);
            paramAnonymousResponse = (DistancesBean)new Gson().fromJson((String)localObject1, DistancesBean.class);
            if (paramAnonymousResponse.getStatus().equals("ZERO_RESULTS"))
            {
              RouteActivity.this.runOnUiThread(new Runnable()
              {
                public final void run()
                {
                  Toast.makeText(RouteActivity.this, 2131689616, 0).show();
                  RouteActivity.this.mLoading.clearAnimation();
                  RouteActivity.this.mLoading.setVisibility(8);
                  RouteActivity.this.mRlSummary.setVisibility(0);
                  RouteActivity.this.mRlSummarySencond.setVisibility(8);
                  RouteActivity.this.mSummary.setText(2131689648);
                  RouteActivity.this.mSlideLayout.setEnabled(false);
                  RouteActivity.this.mTvJump.setVisibility(0);
                }
              });
              return;
            }
            if ((!paramAnonymousResponse.getStatus().equals("OVER_QUERY_LIMIT")) && (!((String)localObject1).contains("error_message")))
            {
              localObject1 = paramAnonymousResponse.getRoutes();
              if (((List)localObject1).size() <= 0) {
                return;
              }
              int i = 0;
              while (i < ((List)localObject1).size())
              {
                List localList = ((DistancesBean.RoutesBean)paramAnonymousResponse.getRoutes().get(i)).getLegs();
                int j = 0;
                Object localObject2 = paramAnonymousResponse;
                paramAnonymousResponse = paramAnonymousCall;
                while (j < localList.size())
                {
                  int m = 0;
                  int k = i;
                  paramAnonymousCall = (Call)localObject2;
                  localObject2 = localObject1;
                  while (m < ((DistancesBean.RoutesBean.LegsBean)localList.get(j)).getSteps().size())
                  {
                    paramAnonymousResponse.addAll(ajv.a(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)((DistancesBean.RoutesBean.LegsBean)localList.get(j)).getSteps().get(m)).getPolyline().getPoints()));
                    Object localObject4 = (DistancesBean.RoutesBean.LegsBean.StepsBeanX)((DistancesBean.RoutesBean.LegsBean)localList.get(j)).getSteps().get(m);
                    if (Build.VERSION.SDK_INT >= 24) {
                      localObject1 = Html.fromHtml(RouteActivity.a(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getHtml_instructions()), 0);
                    } else {
                      localObject1 = Html.fromHtml(RouteActivity.a(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getHtml_instructions()));
                    }
                    Object localObject3 = null;
                    Object localObject5;
                    Object localObject6;
                    if (((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getTravel_mode().equals("WALKING"))
                    {
                      localObject3 = ((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getManeuver();
                      localObject5 = new StringBuilder();
                      ((StringBuilder)localObject5).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getStart_location().getLat());
                      ((StringBuilder)localObject5).append(",");
                      ((StringBuilder)localObject5).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getStart_location().getLng());
                      localObject5 = ((StringBuilder)localObject5).toString();
                      localObject6 = new StringBuilder();
                      ((StringBuilder)localObject6).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getEnd_location().getLat());
                      ((StringBuilder)localObject6).append(",");
                      ((StringBuilder)localObject6).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getEnd_location().getLng());
                      localObject3 = new StepProgressBean((CharSequence)localObject1, (String)localObject5, ((StringBuilder)localObject6).toString(), ((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getDistance().getText(), ((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getDuration().getText(), "WALK_ALL", (String)localObject3, null);
                    }
                    if ("transit".equals(RouteActivity.b(RouteActivity.this)))
                    {
                      localObject6 = new Gson();
                      Object localObject7 = ((Gson)localObject6).toJson(localObject4);
                      int n = ((String)localObject7).indexOf("travel_mode");
                      int i1 = n + 14;
                      localObject5 = ((String)localObject7).substring(i1, n + 18);
                      i = -1;
                      int i2 = ((String)localObject5).hashCode();
                      if (i2 != 2583339)
                      {
                        if ((i2 == 2656713) && (((String)localObject5).equals("WALK"))) {
                          i = 0;
                        }
                      }
                      else if (((String)localObject5).equals("TRAN")) {
                        i = 1;
                      }
                      String str;
                      Object localObject8;
                      Object localObject9;
                      switch (i)
                      {
                      default: 
                      case 1: 
                        for (;;)
                        {
                          localObject1 = paramAnonymousCall;
                          paramAnonymousCall = paramAnonymousResponse;
                          paramAnonymousResponse = (Response)localObject1;
                          break;
                          i = ((String)localObject7).indexOf("transit_details");
                          localObject5 = ((String)localObject7).substring(i1, n + 22);
                          localObject6 = (TransitSubWayBean)((Gson)localObject6).fromJson(((String)localObject7).substring(i + 17, n - 2), TransitSubWayBean.class);
                          localObject3 = new StringBuilder("departure stop");
                          ((StringBuilder)localObject3).append(((TransitSubWayBean)localObject6).getDeparture_stop().getName());
                          ((StringBuilder)localObject3).append("arrival stop");
                          ((StringBuilder)localObject3).append(((TransitSubWayBean)localObject6).getArrival_stop().getName());
                          ((StringBuilder)localObject3).append("stop nums");
                          ((StringBuilder)localObject3).append(((TransitSubWayBean)localObject6).getNum_stops());
                          ((StringBuilder)localObject3).append(RouteActivity.this.getString(2131689643));
                          ((StringBuilder)localObject3).append("lines");
                          ((StringBuilder)localObject3).append(((TransitSubWayBean)localObject6).getLine().getShort_name());
                          ((StringBuilder)localObject3).append("time");
                          ((StringBuilder)localObject3).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getDuration().getText());
                          localObject3 = ((StringBuilder)localObject3).toString();
                          localObject7 = ((TransitSubWayBean)localObject6).getLine().getVehicle().getIcon();
                          str = ((TransitSubWayBean)localObject6).getDeparture_stop().getName();
                          localObject8 = ((TransitSubWayBean)localObject6).getArrival_stop().getName();
                          localObject9 = ((TransitSubWayBean)localObject6).getLine().getShort_name();
                          StringBuilder localStringBuilder = new StringBuilder();
                          localStringBuilder.append(((TransitSubWayBean)localObject6).getNum_stops());
                          localStringBuilder.append("(");
                          localStringBuilder.append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getDuration().getText());
                          localStringBuilder.append(")");
                          localStringBuilder = new StringBuilder("<font color='#4e86f2'>");
                          localStringBuilder.append(((TransitSubWayBean)localObject6).getNum_stops());
                          localStringBuilder.append(" ");
                          localStringBuilder.append(RouteActivity.this.getString(2131689643));
                          localStringBuilder.append("</font><font color='#000000'> ( ");
                          localStringBuilder.append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getDuration().getText());
                          localStringBuilder.append(" ) </font>");
                          localObject4 = localStringBuilder.toString();
                          if (Build.VERSION.SDK_INT >= 24) {}
                          for (localObject3 = Html.fromHtml((String)localObject3, 0);; localObject3 = Html.fromHtml((String)localObject3)) {
                            break;
                          }
                          localObject1 = new TransitStepProgressBean((CharSequence)localObject1, str, (String)localObject8, (String)localObject4, (String)localObject4, (String)localObject5, (CharSequence)localObject3, (CharSequence)localObject9, (CharSequence)localObject7, 1, new VehicleBean(((TransitSubWayBean)localObject6).getLine().getVehicle().getType(), ((TransitSubWayBean)localObject6).getLine().getColor(), ((TransitSubWayBean)localObject6).getLine().getVehicle().getLocal_icon()), null);
                          RouteActivity.c(RouteActivity.this).add(localObject1);
                        }
                      }
                      if ((localObject4 != null) && (((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getSteps() != null))
                      {
                        if (((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getSteps().size() == 0) {
                          return;
                        }
                        localObject6 = new ArrayList();
                        i = 0;
                        while (i < ((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getSteps().size())
                        {
                          localObject7 = (DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getSteps().get(i);
                          localObject1 = new StringBuilder("walk distance");
                          ((StringBuilder)localObject1).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getDistance().getText());
                          ((StringBuilder)localObject1).append("(");
                          ((StringBuilder)localObject1).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getDuration().getText());
                          ((StringBuilder)localObject1).append(")");
                          localObject1 = ((StringBuilder)localObject1).toString();
                          if (Build.VERSION.SDK_INT >= 24)
                          {
                            Html.fromHtml((String)localObject1, 0);
                            if (((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getHtml_instructions() != null)
                            {
                              localObject1 = ((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getHtml_instructions();
                              localObject1 = Html.fromHtml(RouteActivity.a((String)localObject1), 0);
                            }
                          }
                          for (;;)
                          {
                            break label1336;
                            if (((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getHtml_instructions() != null)
                            {
                              localObject1 = ((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getHtml_instructions();
                              break;
                              if (((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getHtml_instructions() != null)
                              {
                                localObject1 = Html.fromHtml(RouteActivity.a(((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getHtml_instructions()));
                                continue;
                              }
                            }
                            localObject1 = "";
                          }
                          label1336:
                          str = ((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getManeuver();
                          localObject8 = new StringBuilder();
                          ((StringBuilder)localObject8).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getStart_location().getLat());
                          ((StringBuilder)localObject8).append(",");
                          ((StringBuilder)localObject8).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getStart_location().getLng());
                          localObject8 = ((StringBuilder)localObject8).toString();
                          localObject9 = new StringBuilder();
                          ((StringBuilder)localObject9).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getEnd_location().getLat());
                          ((StringBuilder)localObject9).append(",");
                          ((StringBuilder)localObject9).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getEnd_location().getLng());
                          ((ArrayList)localObject6).add(new StepProgressBean((CharSequence)localObject1, (String)localObject8, ((StringBuilder)localObject9).toString(), ((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getDistance().getText(), ((DistancesBean.RoutesBean.LegsBean.StepsBeanX.StepsBean)localObject7).getDuration().getText(), (String)localObject5, str, null));
                          i += 1;
                        }
                        localObject1 = paramAnonymousCall;
                        ((StepProgressBean)localObject3).setStepDetailUsuallyWalkMode((ArrayList)localObject6);
                        RouteActivity.c(RouteActivity.this).add(localObject3);
                        paramAnonymousCall = paramAnonymousResponse;
                        paramAnonymousResponse = (Response)localObject1;
                      }
                    }
                    else
                    {
                      localObject3 = paramAnonymousResponse;
                      if (((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getManeuver() == null) {}
                      for (paramAnonymousResponse = "straight";; paramAnonymousResponse = ((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getManeuver()) {
                        break;
                      }
                      localObject5 = new StringBuilder();
                      ((StringBuilder)localObject5).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getStart_location().getLat());
                      ((StringBuilder)localObject5).append(",");
                      ((StringBuilder)localObject5).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getStart_location().getLng());
                      localObject5 = ((StringBuilder)localObject5).toString();
                      localObject6 = new StringBuilder();
                      ((StringBuilder)localObject6).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getEnd_location().getLat());
                      ((StringBuilder)localObject6).append(",");
                      ((StringBuilder)localObject6).append(((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getEnd_location().getLng());
                      paramAnonymousResponse = new StepProgressBean((CharSequence)localObject1, (String)localObject5, ((StringBuilder)localObject6).toString(), ((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getDistance().getText(), ((DistancesBean.RoutesBean.LegsBean.StepsBeanX)localObject4).getDuration().getText(), RouteActivity.b(RouteActivity.this), paramAnonymousResponse, null);
                      RouteActivity.c(RouteActivity.this).add(paramAnonymousResponse);
                      paramAnonymousResponse = paramAnonymousCall;
                      paramAnonymousCall = (Call)localObject3;
                    }
                    m += 1;
                    localObject1 = paramAnonymousResponse;
                    paramAnonymousResponse = paramAnonymousCall;
                    paramAnonymousCall = (Call)localObject1;
                  }
                  j += 1;
                  localObject1 = localObject2;
                  localObject2 = paramAnonymousCall;
                  i = k;
                }
                i += 1;
                paramAnonymousCall = paramAnonymousResponse;
                paramAnonymousResponse = (Response)localObject2;
              }
              localObject1 = new afr();
              ((afr)localObject1).a(paramAnonymousCall);
              ((afr)localObject1).a = 18.0F;
              ((afr)localObject1).b = Color.parseColor("#4448a0");
              RouteActivity.this.runOnUiThread(new Runnable()
              {
                public final void run()
                {
                  if (RouteActivity.this.a == null) {
                    return;
                  }
                  RouteActivity.this.a.b();
                  RouteActivity.this.a.a(this.a);
                  RouteActivity.this.mTvJump.setVisibility(0);
                  if (paramAnonymousCall.size() > 0)
                  {
                    localObject1 = LatLngBounds.a();
                    int i = 0;
                    while (i < paramAnonymousCall.size())
                    {
                      ((LatLngBounds.a)localObject1).a((LatLng)paramAnonymousCall.get(i));
                      i += 1;
                    }
                    localObject1 = adz.a(((LatLngBounds.a)localObject1).a());
                    RouteActivity.this.a.b((ady)localObject1);
                  }
                  String str = ((DistancesBean.RoutesBean.LegsBean)((DistancesBean.RoutesBean)paramAnonymousResponse.getRoutes().get(0)).getLegs().get(0)).getDistance().getText();
                  Object localObject3 = ((DistancesBean.RoutesBean.LegsBean)((DistancesBean.RoutesBean)paramAnonymousResponse.getRoutes().get(0)).getLegs().get(0)).getDuration().getText();
                  if (((String)localObject3).contains("hours")) {
                    localObject1 = "hours ";
                  }
                  for (Object localObject2 = "hours\n";; localObject2 = "hour\n")
                  {
                    localObject1 = ((String)localObject3).replace((CharSequence)localObject1, (CharSequence)localObject2);
                    break;
                    localObject1 = localObject3;
                    if (!((String)localObject3).contains("hour")) {
                      break;
                    }
                    localObject1 = "hour ";
                  }
                  if (((String)localObject1).contains("days")) {
                    localObject2 = "days ";
                  }
                  for (localObject3 = "days\n";; localObject3 = "day\n")
                  {
                    localObject2 = ((String)localObject1).replace((CharSequence)localObject2, (CharSequence)localObject3);
                    break;
                    localObject2 = localObject1;
                    if (!((String)localObject1).contains("day")) {
                      break;
                    }
                    localObject2 = "day ";
                  }
                  Object localObject1 = RouteActivity.this;
                  localObject3 = RouteActivity.this.a;
                  afm localAfm = new afm().a(RouteActivity.d(RouteActivity.this));
                  localAfm.b = afi.a(2131231001);
                  RouteActivity.a((RouteActivity)localObject1, ((aea)localObject3).a(localAfm));
                  localObject1 = RouteActivity.this;
                  localObject3 = RouteActivity.this.a;
                  localAfm = new afm().a(RouteActivity.e(RouteActivity.this));
                  localAfm.b = afi.a(2131230993);
                  RouteActivity.b((RouteActivity)localObject1, ((aea)localObject3).a(localAfm));
                  if (RouteActivity.this.e != null) {
                    RouteActivity.this.e.a();
                  }
                  localObject1 = RouteActivity.this;
                  localObject3 = RouteActivity.this.a;
                  localAfm = new afm().a(RouteActivity.f(RouteActivity.this));
                  localAfm.b = afi.a(2131230892);
                  ((RouteActivity)localObject1).e = ((aea)localObject3).a(localAfm);
                  RouteActivity.this.mLoading.clearAnimation();
                  RouteActivity.this.mLoading.setVisibility(8);
                  RouteActivity.this.mRlSummary.setVisibility(8);
                  RouteActivity.this.mRlSummarySencond.setVisibility(0);
                  RouteActivity.this.mSlideLayout.setEnabled(true);
                  RouteActivity.this.mSummaryTime.setText((CharSequence)localObject2);
                  RouteActivity.this.mSummaryTime.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                  if (RouteActivity.this.mSummaryTime.getLineCount() == 1) {
                    localObject1 = RouteActivity.this.mSummaryTime;
                  }
                  for (float f = 17.0F;; f = 12.0F)
                  {
                    ((TextView)localObject1).setTextSize(f);
                    break;
                    localObject1 = RouteActivity.this.mSummaryTime;
                  }
                  RouteActivity.this.mSummaryDitance.setText(str);
                  RouteActivity.this.mSpace.setVisibility(8);
                  if (RouteActivity.this.mStepProgress.getLayoutManager() == null)
                  {
                    RouteActivity.a(RouteActivity.this, new LinearLayoutManager(RouteActivity.g(RouteActivity.this), 1, false));
                    RouteActivity.this.mStepProgress.setLayoutManager(RouteActivity.h(RouteActivity.this));
                  }
                  if (RouteActivity.b(RouteActivity.this).equals("transit"))
                  {
                    RouteActivity.c(RouteActivity.this).add(0, RouteActivity.this.mSrc.getText().toString().trim());
                    RouteActivity.c(RouteActivity.this).add(RouteActivity.this.mDes.getText().toString().trim());
                    RouteActivity.a(RouteActivity.this, new TransitStepProgressAdapter(RouteActivity.g(RouteActivity.this), RouteActivity.c(RouteActivity.this)));
                    localObject1 = RouteActivity.this.mStepProgress;
                  }
                  for (localObject2 = RouteActivity.i(RouteActivity.this);; localObject2 = RouteActivity.j(RouteActivity.this))
                  {
                    ((RecyclerView)localObject1).setAdapter((RecyclerView.Adapter)localObject2);
                    return;
                    RouteActivity.c(RouteActivity.this).add(0, RouteActivity.this.mSrc.getText().toString().trim());
                    RouteActivity.c(RouteActivity.this).add(RouteActivity.this.mDes.getText().toString().trim());
                    if (RouteActivity.j(RouteActivity.this) == null) {
                      RouteActivity.a(RouteActivity.this, new StepProgressAdapter(RouteActivity.g(RouteActivity.this), RouteActivity.c(RouteActivity.this)));
                    } else {
                      RouteActivity.j(RouteActivity.this).notifyDataSetChanged();
                    }
                    localObject1 = RouteActivity.this.mStepProgress;
                  }
                }
              });
              return;
            }
            RouteActivity.this.runOnUiThread(new Runnable()
            {
              public final void run()
              {
                RouteActivity.this.mLoading.clearAnimation();
                RouteActivity.this.mLoading.setVisibility(8);
                RouteActivity.this.mSummary.setText(2131689614);
                RouteActivity.this.mTvJump.setVisibility(0);
              }
            });
            return;
          }
          new StringBuilder("响应吗").append(paramAnonymousResponse.code());
        }
      });
    }
  }
  
  public final void a(int paramInt) {}
  
  public final void a(Bundle paramBundle)
  {
    if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
      return;
    }
    LocationManager localLocationManager = (LocationManager)getSystemService("location");
    afm localAfm = null;
    paramBundle = localAfm;
    if (localLocationManager != null)
    {
      paramBundle = this.i;
      int i1 = paramBundle.hashCode();
      if (i1 != -1497957892)
      {
        if (i1 != -1067059757)
        {
          if (i1 != 1118815609)
          {
            if ((i1 == 1920367559) && (paramBundle.equals("driving")))
            {
              i1 = 0;
              break label133;
            }
          }
          else if (paramBundle.equals("walking"))
          {
            i1 = 2;
            break label133;
          }
        }
        else if (paramBundle.equals("transit"))
        {
          i1 = 1;
          break label133;
        }
      }
      else if (paramBundle.equals("bicycling"))
      {
        i1 = 3;
        break label133;
      }
      i1 = -1;
      switch (i1)
      {
      default: 
        break;
      case 3: 
        paramBundle = this.mBicying;
        break;
      case 2: 
        paramBundle = this.mWalking;
        break;
      case 1: 
        paramBundle = this.mTransit;
        break;
      case 0: 
        label133:
        paramBundle = this.mDriving;
      }
      paramBundle.setSelected(true);
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
        c(this.h);
        getIntent().removeExtra("pass_url");
        paramBundle = localAfm;
      }
      else
      {
        paramBundle = localLocationManager.getLastKnownLocation("network");
        localLocationManager.requestLocationUpdates("network", 1000L, 10.0F, new LocationListener()
        {
          public final void onLocationChanged(Location paramAnonymousLocation)
          {
            if (RouteActivity.this.e != null) {
              RouteActivity.this.e.a();
            }
            paramAnonymousLocation = ajv.a(paramAnonymousLocation.getLatitude(), paramAnonymousLocation.getLongitude());
            RouteActivity.a(RouteActivity.this, new LatLng(paramAnonymousLocation[0], paramAnonymousLocation[1]));
            if (RouteActivity.this.e != null) {
              RouteActivity.this.e.a();
            }
            paramAnonymousLocation = RouteActivity.this;
            aea localAea = RouteActivity.this.a;
            afm localAfm = new afm().a(RouteActivity.f(RouteActivity.this));
            localAfm.b = afi.a(2131230892);
            paramAnonymousLocation.e = localAea.a(localAfm);
          }
          
          public final void onProviderDisabled(String paramAnonymousString) {}
          
          public final void onProviderEnabled(String paramAnonymousString) {}
          
          public final void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle) {}
        });
      }
    }
    if (paramBundle != null)
    {
      paramBundle = ajv.a(paramBundle.getLatitude(), paramBundle.getLongitude());
      this.l = new LatLng(paramBundle[0], paramBundle[1]);
      this.a.b(adz.a(this.l, 15.0F));
      if (this.e != null) {
        this.e.a();
      }
      paramBundle = this.a;
      localAfm = new afm().a(this.l);
      localAfm.b = afi.a(2131230892);
      this.e = paramBundle.a(localAfm);
    }
  }
  
  public final void a(aea paramAea)
  {
    super.a(paramAea);
    this.a.a(0, getResources().getDimensionPixelSize(2131165394), 0);
    this.a.a(new aea.d()
    {
      public final boolean a(afl paramAnonymousAfl)
      {
        paramAnonymousAfl.e();
        return true;
      }
    });
  }
  
  public final void a(qu paramQu)
  {
    StringBuilder localStringBuilder = new StringBuilder("Error");
    localStringBuilder.append(paramQu.b);
    Toast.makeText(this, localStringBuilder.toString(), 0).show();
  }
  
  protected final View d()
  {
    return b(2131492903);
  }
  
  public final void e()
  {
    if ((ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) && (this.a != null)) {
      this.a.c();
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramIntent == null) {
      return;
    }
    Object localObject1;
    if (paramInt1 == 123)
    {
      Object localObject2;
      if (paramInt2 == -1)
      {
        localObject1 = paramIntent.getStringExtra("place_bean_string");
        localObject2 = (PlaceBean)new Gson().fromJson((String)localObject1, PlaceBean.class);
        this.mSrc.setText(((PlaceBean)localObject2).getName());
        this.k = ((PlaceBean)localObject2).getLatLng();
        int i1;
        if ((!TextUtils.isEmpty(this.mSrc.getText().toString().trim())) && (!TextUtils.isEmpty(this.mDes.getText().toString().trim()))) {
          i1 = 0;
        } else {
          i1 = 1;
        }
        if (i1 == 0)
        {
          c(a());
        }
        else
        {
          this.a.b(adz.a(((PlaceBean)localObject2).getLatLng(), 15.0F));
          if (this.q == null)
          {
            localObject1 = this.a;
            localObject2 = new afm().a(((PlaceBean)localObject2).getLatLng());
            ((afm)localObject2).b = afi.a(2131231001);
            this.q = ((aea)localObject1).a((afm)localObject2);
          }
        }
      }
      else if (paramInt2 == 2)
      {
        localObject1 = ado.a(this, paramIntent);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(((Status)localObject1).toString());
        Toast.makeText(this, ((StringBuilder)localObject2).toString(), 0).show();
      }
    }
    if (paramInt1 == 321)
    {
      localObject1 = paramIntent.getStringExtra("place_bean_string");
      localObject1 = (PlaceBean)new Gson().fromJson((String)localObject1, PlaceBean.class);
      if (localObject1 == null)
      {
        Toast.makeText(this.u, "location latlng = null", 0).show();
        return;
      }
      if (paramInt2 == -1)
      {
        this.j = ((PlaceBean)localObject1).getLatLng();
        this.mDes.setText(((PlaceBean)localObject1).getName());
        c(a());
        return;
      }
      if (paramInt2 == 2)
      {
        paramIntent = ado.a(this, paramIntent);
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(paramIntent.toString());
        Toast.makeText(this, ((StringBuilder)localObject1).toString(), 0).show();
      }
    }
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
  
  protected void onCreate(Bundle arg1)
  {
    super.onCreate(???);
    ButterKnife.a(this);
    this.u = this;
    this.c = new rh.a(this).a(abz.a).a(abz.b).a(this).a(this).a();
    this.c.b();
    this.v = new akn(this);
    this.b = ((aed)getSupportFragmentManager().findFragmentById(2131361904));
    this.b.a(this);
    this.i = ajy.b(this.u, "map_mode", "driving");
    this.mSlideLayout.setPanelState(SlidingUpPanelLayout.d.b);
    this.mSlideLayout.setPanelHeight(0);
    SlidingUpPanelLayout localSlidingUpPanelLayout = this.mSlideLayout;
    SlidingUpPanelLayout.c local1 = new SlidingUpPanelLayout.c()
    {
      public final void a(SlidingUpPanelLayout.d paramAnonymousD1, SlidingUpPanelLayout.d paramAnonymousD2)
      {
        if (paramAnonymousD2 == SlidingUpPanelLayout.d.b) {
          akd.a(RouteActivity.this.mPanel, false);
        }
        if (paramAnonymousD1 == SlidingUpPanelLayout.d.b) {
          akd.a(RouteActivity.this.mPanel, true);
        }
      }
    };
    synchronized (localSlidingUpPanelLayout.a)
    {
      localSlidingUpPanelLayout.a.add(local1);
      return;
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    ajy.a(this.u, "map_mode", this.i);
  }
  
  protected void onPause()
  {
    MobclickAgent.onPause(this);
    super.onPause();
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
    new ajr().show(localFragmentTransaction, "dialogFragment");
  }
  
  @OnClick
  public void onViewClicked(View paramView)
  {
    Object localObject;
    double d1;
    double d2;
    switch (paramView.getId())
    {
    default: 
      
    case 2131362250: 
      b(this.i);
      this.i = "walking";
      this.mWalking.setSelected(true);
      c(a());
      return;
    case 2131362232: 
      if (!TextUtils.isEmpty(this.mSrc.getText()))
      {
        if (TextUtils.isEmpty(this.mDes.getText())) {
          return;
        }
        paramView = new StringBuilder("&origin=");
        paramView.append(this.mSrc.getText().toString().trim());
        paramView = paramView.toString();
        localObject = new StringBuilder("&destination=");
        ((StringBuilder)localObject).append(this.mDes.getText().toString().trim());
        localObject = ((StringBuilder)localObject).toString();
        if (this.mSrc.getText().toString().trim().equals(getString(2131689583))) {
          paramView = "";
        }
        if (this.mDes.getText().toString().trim().equals(getString(2131689583)))
        {
          localObject = new StringBuilder("&destination=");
          ((StringBuilder)localObject).append(this.l.a);
          ((StringBuilder)localObject).append(",");
          ((StringBuilder)localObject).append(this.l.b);
          localObject = ((StringBuilder)localObject).toString();
        }
        StringBuilder localStringBuilder = new StringBuilder("https://www.google.com/maps/dir/?api=1");
        localStringBuilder.append(paramView);
        localStringBuilder.append((String)localObject);
        localStringBuilder.append("&travelmode=");
        localStringBuilder.append(this.i);
        localStringBuilder.append("&key=AIzaSyD9cW_4fKYd3t7DrJQjB-v5Bai2Tv2Qdrc");
        paramView = localStringBuilder.toString();
        if (a(this, "com.google.android.apps.maps"))
        {
          this.y.cancel();
          paramView = new Intent("android.intent.action.VIEW", Uri.parse(paramView));
          paramView.setPackage("com.google.android.apps.maps");
          startActivity(paramView);
          return;
        }
        Toast.makeText(this, 2131689590, 1).show();
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.android.apps.maps")));
        return;
      }
      return;
    case 2131362207: 
      b(this.i);
      this.i = "transit";
      this.mTransit.setSelected(true);
      c(a());
      return;
    case 2131362189: 
      this.mSlideLayout.setEnabled(true);
      paramView = this.mSrc.getText().toString().trim();
      localObject = this.mDes.getText().toString().trim();
      this.mSrc.setText((CharSequence)localObject);
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
      localObject = this.mDes.getHint().toString().trim();
      if (((!TextUtils.isEmpty(this.mSrc.getText().toString().trim())) || (!paramView.equals(getResources().getString(2131689641)))) && ((!TextUtils.isEmpty(this.mDes.getText().toString().trim())) || (!((String)localObject).equals(getResources().getString(2131689510))))) {
        c(a());
      }
      return;
    case 2131362176: 
      if (this.l == null) {
        return;
      }
      if (this.x == null) {
        this.x = new Intent(this.u, SearchRouteTargetActivity.class);
      }
      d1 = this.l.a;
      d2 = this.l.b;
      this.x.putExtra("locate_latlng", new double[] { d1, d2 });
      startActivityForResult(this.x, 123);
      return;
    case 2131362156: 
      if (!ajw.a())
      {
        ajv.a(this, this.m, this.n, this.l);
        return;
      }
      break;
    case 2131361955: 
      if (this.l != null)
      {
        this.a.b(adz.a(this.l, 15.0F));
        return;
      }
      break;
    case 2131361888: 
      b(this.i);
      this.i = "driving";
      this.mDriving.setSelected(true);
      c(a());
      return;
    case 2131361876: 
      if (this.l == null) {
        return;
      }
      if (this.w == null) {
        this.w = new Intent(this.u, SearchRouteTargetActivity.class);
      }
      d1 = this.l.a;
      d2 = this.l.b;
      this.w.putExtra("locate_latlng", new double[] { d1, d2 });
      startActivityForResult(this.w, 321);
      return;
    case 2131361840: 
      b(this.i);
      this.i = "bicycling";
      this.mBicying.setSelected(true);
      c(a());
      return;
    case 2131361837: 
      onBackPressed();
    }
  }
}
