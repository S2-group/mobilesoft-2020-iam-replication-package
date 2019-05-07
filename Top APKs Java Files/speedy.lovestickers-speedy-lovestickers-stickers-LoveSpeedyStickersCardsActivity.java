package speedy.lovestickers.stickers;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import com.b.a.b.c.a;
import com.b.a.b.d;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.nhaarman.listviewanimations.appearance.ViewAnimator;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.Iterator;
import java.util.List;
import speedy.lovestickers.b.a;
import speedy.lovestickers.util.e;

public class LoveSpeedyStickersCardsActivity
  extends AppCompatActivity
  implements AdapterView.OnItemClickListener, a
{
  String[] a;
  private int[] c;
  private c d;
  private Toolbar e;
  private SharedPreferences f;
  private String g = "com.topapps.photo.blur";
  private String h = "com.mantra.pipcamera.effect";
  private String i;
  private String j;
  private String k;
  private String l;
  private e m;
  private boolean n;
  private d o;
  private com.b.a.b.c p;
  private AdView q;
  
  static
  {
    if (!LoveSpeedyStickersCardsActivity.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      b = bool;
      return;
    }
  }
  
  public LoveSpeedyStickersCardsActivity() {}
  
  private void a(int paramInt)
  {
    Intent localIntent = new Intent(this, LoveSpeedyStickersShareStickerFragmentActivity.class);
    localIntent.putExtra("category", this.a[paramInt]);
    localIntent.putExtra("number", this.c[paramInt]);
    startActivity(localIntent);
  }
  
  private void a(final String paramString1, int paramInt, String paramString2)
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setContentView(2130968609);
    ImageView localImageView = (ImageView)localDialog.findViewById(2131689608);
    if (this.n) {
      this.o.a(paramString2, localImageView, this.p);
    }
    for (;;)
    {
      localDialog.show();
      ((ImageView)localDialog.findViewById(2131689610)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          LoveSpeedyStickersCardsActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString1)));
          localDialog.dismiss();
        }
      });
      ((ImageView)localDialog.findViewById(2131689611)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localDialog.dismiss();
        }
      });
      return;
      paramString2 = "drawable://" + paramInt;
      this.o.a(paramString2, localImageView, this.p);
    }
  }
  
  private boolean a(String paramString)
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private boolean c()
  {
    return ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo() != null;
  }
  
  private void d()
  {
    SharedPreferences.Editor localEditor;
    if (a(this.g))
    {
      localEditor = this.f.edit();
      localEditor.putBoolean("FIRST_APP_INSTALLED", true);
      localEditor.commit();
    }
    if (a(this.h))
    {
      localEditor = this.f.edit();
      localEditor.putBoolean("SEC_APP_INSTALLED", true);
      localEditor.commit();
    }
  }
  
  private void e()
  {
    this.e = ((Toolbar)findViewById(2131689592));
    setSupportActionBar(this.e);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
  }
  
  public void a()
  {
    new ParseQuery("MarshmallowStudio").getFirstInBackground(new GetCallback()
    {
      public void done(ParseObject paramAnonymousParseObject, ParseException paramAnonymousParseException)
      {
        if (paramAnonymousParseObject == null) {}
        do
        {
          return;
          LoveSpeedyStickersCardsActivity.a(LoveSpeedyStickersCardsActivity.this, (String)paramAnonymousParseObject.get("app_Name"));
          LoveSpeedyStickersCardsActivity.b(LoveSpeedyStickersCardsActivity.this, (String)paramAnonymousParseObject.get("package_Name"));
          LoveSpeedyStickersCardsActivity.c(LoveSpeedyStickersCardsActivity.this, (String)paramAnonymousParseObject.get("app_Name1"));
          LoveSpeedyStickersCardsActivity.d(LoveSpeedyStickersCardsActivity.this, (String)paramAnonymousParseObject.get("package_Name1"));
          paramAnonymousParseException = (ParseFile)paramAnonymousParseObject.get("image");
          LoveSpeedyStickersCardsActivity.e(LoveSpeedyStickersCardsActivity.this, paramAnonymousParseException.getUrl());
          paramAnonymousParseObject = (ParseFile)paramAnonymousParseObject.get("image1");
          LoveSpeedyStickersCardsActivity.f(LoveSpeedyStickersCardsActivity.this, paramAnonymousParseObject.getUrl());
          LoveSpeedyStickersCardsActivity.b(LoveSpeedyStickersCardsActivity.this).a(LoveSpeedyStickersCardsActivity.a(LoveSpeedyStickersCardsActivity.this));
          LoveSpeedyStickersCardsActivity.b(LoveSpeedyStickersCardsActivity.this).b(LoveSpeedyStickersCardsActivity.c(LoveSpeedyStickersCardsActivity.this));
        } while ((LoveSpeedyStickersCardsActivity.d(LoveSpeedyStickersCardsActivity.this) == null) || (LoveSpeedyStickersCardsActivity.e(LoveSpeedyStickersCardsActivity.this) == null) || (LoveSpeedyStickersCardsActivity.f(LoveSpeedyStickersCardsActivity.this) == null) || (LoveSpeedyStickersCardsActivity.g(LoveSpeedyStickersCardsActivity.this) == null) || (LoveSpeedyStickersCardsActivity.a(LoveSpeedyStickersCardsActivity.this) == null) || (LoveSpeedyStickersCardsActivity.c(LoveSpeedyStickersCardsActivity.this) == null));
        LoveSpeedyStickersCardsActivity.a(LoveSpeedyStickersCardsActivity.this, true);
        LoveSpeedyStickersCardsActivity.h(LoveSpeedyStickersCardsActivity.this);
      }
    });
  }
  
  public void b()
  {
    d();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968603);
    overridePendingTransition(2131034125, 2131034122);
    this.m = e.a();
    this.m.a(this.g);
    this.m.b(this.h);
    speedy.lovestickers.b.b.a(this);
    this.f = PreferenceManager.getDefaultSharedPreferences(this);
    this.a = getResources().getStringArray(2131558402);
    this.c = getResources().getIntArray(2131558406);
    paramBundle = (ListView)findViewById(2131689598);
    this.d = new c(this, this.c);
    SwingBottomInAnimationAdapter localSwingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(this.d);
    localSwingBottomInAnimationAdapter.setAbsListView(paramBundle);
    if ((!b) && (localSwingBottomInAnimationAdapter.getViewAnimator() == null)) {
      throw new AssertionError();
    }
    localSwingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(300);
    paramBundle.setAdapter(localSwingBottomInAnimationAdapter);
    paramBundle.setOnItemClickListener(this);
    int i1 = 0;
    while (i1 < this.c.length)
    {
      speedy.lovestickers.util.b.a("stickers" + i1);
      this.d.add(Integer.valueOf(i1));
      i1 += 1;
    }
    e();
    if ((!this.f.getBoolean("FIRST_APP_INSTALLED", false)) || (!this.f.getBoolean("SEC_APP_INSTALLED", false))) {}
    try
    {
      if (c()) {
        a();
      }
      d();
      this.q = ((AdView)findViewById(2131689595));
      paramBundle = new AdRequest.Builder().build();
      this.q.loadAd(paramBundle);
      this.o = d.a();
      this.p = new c.a().b(2130903041).c(2130903041).d(2130903041).a(true).b(true).a(Bitmap.Config.RGB_565).c();
      return;
    }
    catch (Exception paramBundle)
    {
      for (;;)
      {
        this.n = false;
      }
    }
  }
  
  public void onDestroy()
  {
    if (this.q != null) {
      this.q.destroy();
    }
    super.onDestroy();
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      a(paramInt);
      return;
    case 1: 
      a(paramInt);
      return;
    case 2: 
      if (this.f.getBoolean("FIRST_APP_INSTALLED", false))
      {
        a(paramInt);
        return;
      }
      if (this.n)
      {
        a(this.i, 0, this.k);
        return;
      }
      a("http://bit.ly/1VHUmOC", 2130903041, null);
      return;
    }
    if (this.f.getBoolean("SEC_APP_INSTALLED", false))
    {
      a(paramInt);
      return;
    }
    if (this.n)
    {
      a(this.j, 0, this.l);
      return;
    }
    a("http://bit.ly/1UduRDS", 2130903042, null);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    }
    finish();
    return true;
  }
  
  public void onPause()
  {
    if (this.q != null) {
      this.q.pause();
    }
    overridePendingTransition(2131034124, 2131034123);
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.q != null) {
      this.q.resume();
    }
  }
}
