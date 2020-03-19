package com.radpad.RadPad.ui;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.c;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.android.gms.maps.v;
import com.radpad.RadPad.RadPadApplication;
import com.radpad.RadPad.api.DrivetimeData;
import com.radpad.RadPad.api.GeoService;
import com.radpad.RadPad.api.RouteReceivedListener;
import com.radpad.RadPad.api.SuggestionListener;
import com.radpad.RadPad.api.google.Bounds;
import com.radpad.RadPad.api.google.GeoDetailResponse;
import com.radpad.RadPad.api.google.Geopoint;
import com.radpad.RadPad.api.google.Polyline;
import com.radpad.RadPad.api.google.Route;
import com.radpad.RadPad.api.google.Suggestion;
import com.radpad.RadPad.k.s;
import com.radpad.RadPad.k.t;
import com.radpad.RadPad.k.z;
import com.radpad.RadPad.model.Listing;
import com.radpad.RadPad.whoareyou.b.m;
import com.radpad.RadPad.widget.DurationBoard;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RouteActivity
  extends com.radpad.RadPad.app.a
  implements View.OnClickListener, AdapterView.OnItemClickListener, RouteReceivedListener, SuggestionListener
{
  private com.google.android.gms.maps.model.g A;
  private DurationBoard B;
  private final View.OnFocusChangeListener C = new h(this);
  private boolean D = false;
  private LatLngBounds E;
  private com.google.android.gms.maps.a F;
  private final GeoService a = RadPadApplication.a().g();
  private final m b = RadPadApplication.a().a();
  private final com.radpad.RadPad.i.b c = RadPadApplication.a().l();
  private c d;
  private SupportMapFragment e;
  private Listing f;
  private LatLng g;
  private LatLng h;
  private boolean i = true;
  private boolean j = true;
  private int k = 0;
  private com.google.android.gms.maps.model.f l;
  private View m;
  private AutoCompleteTextView n;
  private final TextWatcher o = new f(this);
  private a p;
  private ListView q;
  private ImageView r;
  private ImageView s;
  private ImageView t;
  private FloatingActionButton u;
  private final com.google.android.gms.maps.k v = new g(this);
  private FloatingActionButton w;
  private View x;
  private View y;
  private String z;
  
  public RouteActivity() {}
  
  public static void a(Context paramContext, Listing paramListing)
  {
    Intent localIntent = new Intent(paramContext, RouteActivity.class);
    localIntent.putExtra("intent_extra_listing", paramListing);
    localIntent.addFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  private void a(LatLng paramLatLng)
  {
    if (paramLatLng != null)
    {
      if ((paramLatLng.equals(this.g)) && (this.F != null) && (this.d != null)) {
        this.d.b(this.F);
      }
    }
    else {
      return;
    }
    this.g = paramLatLng;
    this.a.getDurations(paramLatLng, this.h);
  }
  
  private void a(LatLng paramLatLng, Location paramLocation)
  {
    if ((paramLatLng != null) && (paramLocation != null) && (Math.abs(paramLatLng.a - paramLocation.getLatitude()) < 1.0E-5D) && (Math.abs(paramLatLng.b - paramLocation.getLongitude()) < 1.0E-5D))
    {
      this.c.a(new LatLng(paramLocation.getLatitude(), paramLocation.getLongitude()));
      this.u.setImageResource(2130838231);
      return;
    }
    this.u.setImageResource(2130838230);
  }
  
  private void b()
  {
    if (this.l == null)
    {
      MarkerOptions localMarkerOptions = new MarkerOptions().a(this.g).a(com.google.android.gms.maps.model.b.a(2130837869));
      this.l = this.d.a(localMarkerOptions);
      return;
    }
    this.l.a(this.g);
  }
  
  private void b(LatLng paramLatLng)
  {
    if (paramLatLng != null) {
      this.d.a(com.google.android.gms.maps.b.a(paramLatLng, this.d.b().b), 2000, null);
    }
  }
  
  private void c()
  {
    this.e = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(2131624626));
    this.d = this.e.b();
    this.B = ((DurationBoard)findViewById(2131624874));
    this.n = ((AutoCompleteTextView)findViewById(2131624879));
    this.q = ((ListView)findViewById(2131624876));
    this.r = ((ImageView)findViewById(2131624880));
    this.t = ((ImageView)findViewById(2131624881));
    this.t.setVisibility(8);
    this.s = ((ImageView)findViewById(2131624878));
    this.x = findViewById(2131624875);
    this.w = ((FloatingActionButton)findViewById(2131624873));
    this.u = ((FloatingActionButton)findViewById(2131624872));
    this.y = findViewById(2131624877);
  }
  
  private void d()
  {
    String str = this.c.g();
    Object localObject = str;
    if (TextUtils.isEmpty(str)) {
      localObject = getString(2131165807);
    }
    this.z = ((String)localObject);
    this.n.setText((CharSequence)localObject);
    if (z.a(this, this.m, new String[] { "android.permission.ACCESS_FINE_LOCATION" })) {
      this.d.a(true);
    }
    this.d.e().a(false);
    this.d.e().b(false);
    this.d.e().f(false);
    this.d.a(new i(this));
    this.d.a(0, 0, 0, getResources().getDimensionPixelSize(2131296547));
    if (this.D) {
      this.d.a(com.radpad.RadPad.k.k.a(this.h, this));
    }
    for (;;)
    {
      this.d.a(com.google.android.gms.maps.b.a(this.h, 14.0F));
      this.d.a(this.v);
      this.p = new a(this, 2130903153, this.a);
      this.n.setAdapter(this.p);
      this.n.addTextChangedListener(this.o);
      this.n.setOnFocusChangeListener(this.C);
      this.q.setAdapter(this.p);
      this.q.setOnItemClickListener(this);
      i();
      this.x.setOnClickListener(this);
      this.u.setOnClickListener(this);
      this.w.setOnClickListener(this);
      this.s.setOnClickListener(this);
      this.r.setOnClickListener(this);
      this.t.setOnClickListener(this);
      localObject = new PolylineOptions();
      ((PolylineOptions)localObject).a(t.e(2131558706));
      this.A = this.d.a((PolylineOptions)localObject);
      this.A.a(16.0F);
      localObject = this.c.f();
      e();
      a((LatLng)localObject);
      this.p.a();
      this.B.setOnClickListener(this);
      return;
      localObject = new MarkerOptions().a(this.h).a(com.radpad.RadPad.k.k.a(this, this.f));
      this.d.a((MarkerOptions)localObject);
    }
  }
  
  private void e()
  {
    com.radpad.RadPad.k.ad.a(this);
    this.n.clearFocus();
    this.C.onFocusChange(this.n, false);
  }
  
  private void i()
  {
    this.q.getViewTreeObserver().addOnGlobalLayoutListener(new j(this));
  }
  
  private void j()
  {
    Intent localIntent = getIntent();
    localIntent.putStringArrayListExtra("TIME_VALUES", this.B.getValues());
    setResult(-1, localIntent);
    finish();
  }
  
  private void k()
  {
    StreetViewActivity.a(this, this.h, true);
  }
  
  private void l()
  {
    this.n.setText("");
  }
  
  private void m()
  {
    double d1 = this.h.a;
    double d2 = this.h.b;
    if (o().contains("com.google.android.apps.maps"))
    {
      Uri localUri = Uri.parse(String.format("google.streetview:cbll=%1$s,%2$s&cbp=1,99.56,,1,-5.27&mz=21", new Object[] { Double.valueOf(d1), Double.valueOf(d2) }));
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(localUri);
      startActivity(localIntent);
      return;
    }
    n();
  }
  
  private void n()
  {
    new AlertDialog.Builder(this).setMessage(getString(2131165841)).setPositiveButton(2131165689, new l(this)).setNegativeButton(17039360, new k(this)).show();
  }
  
  private List<String> o()
  {
    Object localObject = getPackageManager().getInstalledApplications(128);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
    }
    return localArrayList;
  }
  
  private void p()
  {
    CameraPosition localCameraPosition = this.d.b();
    Location localLocation;
    if (z.a(this, this.m, new String[] { "android.permission.ACCESS_FINE_LOCATION" }))
    {
      this.d.a(true);
      localLocation = this.d.d();
    }
    for (;;)
    {
      a(localCameraPosition.a, localLocation);
      return;
      LatLng localLatLng = this.c.m();
      localLocation = new Location("");
      localLocation.setLatitude(localLatLng.a);
      localLocation.setLongitude(localLatLng.b);
    }
  }
  
  private void q()
  {
    if (this.d != null) {
      if (z.a(this, this.m, new String[] { "android.permission.ACCESS_FINE_LOCATION" }))
      {
        this.d.a(true);
        Object localObject = this.d.d();
        if (localObject != null)
        {
          this.c.a(new LatLng(((Location)localObject).getLatitude(), ((Location)localObject).getLongitude()));
          localObject = new LatLng(((Location)localObject).getLatitude(), ((Location)localObject).getLongitude());
          this.n.setText(getString(2131165807));
          if (!this.d.f().a().e.a((LatLng)localObject)) {
            b((LatLng)localObject);
          }
          a((LatLng)localObject);
        }
      }
    }
  }
  
  protected void a()
  {
    this.d.a(true);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    }
    do
    {
      do
      {
        return;
      } while (paramInt2 != -1);
      if ((paramIntent.hasExtra("intent_extra_destination")) && (this.h == null)) {
        this.h = ((LatLng)paramIntent.getParcelableExtra("intent_extra_destination"));
      }
    } while (paramIntent.getBooleanExtra("intent_extra_street_view_allow", true));
    m();
  }
  
  public void onBackPressed()
  {
    if (this.q.getVisibility() == 0)
    {
      e();
      return;
    }
    j();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131624874: 
    case 2131624876: 
    case 2131624877: 
    case 2131624879: 
    default: 
      return;
    case 2131624878: 
      onBackPressed();
      return;
    case 2131624880: 
      k();
      return;
    case 2131624881: 
      l();
      return;
    case 2131624875: 
      this.C.onFocusChange(this.n, false);
      String str = this.c.g();
      paramView = str;
      if (str == null) {
        paramView = getString(2131165807);
      }
      this.n.setText(t.e(paramView));
      return;
    case 2131624872: 
      q();
      return;
    }
    com.radpad.RadPad.k.k.a(this, this.g, this.h);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    f();
    setContentView(2130903204);
    this.m = findViewById(2131624871);
    this.f = ((Listing)getIntent().getParcelableExtra("intent_extra_listing"));
    if (this.f != null) {
      this.h = this.f.getCoordinates();
    }
    if ((this.f != null) && (this.h != null))
    {
      this.D = this.f.getIsApproximateAddress().booleanValue();
      c();
      d();
      return;
    }
    finish();
  }
  
  public void onGeoDetailsReceived(GeoDetailResponse paramGeoDetailResponse)
  {
    DrivetimeData localDrivetimeData = paramGeoDetailResponse.getDrivetimeData();
    this.b.a(localDrivetimeData);
    this.n.setText(s.a(new String[] { localDrivetimeData.getCity(), localDrivetimeData.getState() }));
    a(paramGeoDetailResponse.getLocation());
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    e();
    this.n.clearFocus();
    this.q.setVisibility(8);
    this.x.setVisibility(8);
    paramAdapterView = this.p.a(paramInt);
    this.z = paramAdapterView.getDescription();
    paramAdapterView = paramAdapterView.getReference();
    if (paramAdapterView != null) {
      this.a.getPlaceDetails(paramAdapterView);
    }
    for (;;)
    {
      this.n.setText(t.e(this.z));
      return;
      if (z.a(this, this.m, new String[] { "android.permission.ACCESS_FINE_LOCATION" }))
      {
        this.d.a(true);
        paramAdapterView = this.d.d();
        if (paramAdapterView != null)
        {
          paramAdapterView = new LatLng(paramAdapterView.getLatitude(), paramAdapterView.getLongitude());
          this.c.a(paramAdapterView);
          paramView = new DrivetimeData();
          paramView.setLatitude(paramAdapterView.a);
          paramView.setLongitude(paramAdapterView.b);
          this.b.a(paramView);
          a(paramAdapterView);
        }
      }
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    this.a.setDurationListener(null);
    this.a.setRouteReceivedListener(null);
    this.a.setSuggestionListener(null);
  }
  
  protected void onResume()
  {
    super.onResume();
    this.a.setDurationListener(this.B);
    this.a.setRouteReceivedListener(this);
    this.a.setSuggestionListener(this);
  }
  
  public void onRouteReceived(Route paramRoute)
  {
    List localList;
    if (paramRoute != null)
    {
      localList = t.d(paramRoute.getOverviewPolyline().getPoints());
      paramRoute = paramRoute.getBounds();
      paramRoute = new LatLngBounds(paramRoute.getSouthwest().toLatLng(), paramRoute.getNortheast().toLatLng());
      int i1 = this.e.getView().getMeasuredHeight();
      int i2 = this.e.getView().getMeasuredWidth();
      if ((i1 == 0) || (i2 == 0))
      {
        this.E = paramRoute;
        paramRoute = localList;
      }
    }
    for (;;)
    {
      this.A.a(paramRoute);
      b();
      return;
      this.F = com.google.android.gms.maps.b.a(paramRoute, 150);
      this.d.b(this.F);
      paramRoute = localList;
      continue;
      paramRoute = new ArrayList();
    }
  }
  
  public void onSuggestionListReceived(List<Suggestion> paramList) {}
}
