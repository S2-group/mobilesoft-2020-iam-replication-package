package com.imobile3.passconnect.ui.activity;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SwitchCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.imobile3.passconnect.models.Pass;
import com.imobile3.passconnect.models.PassBarcode;
import com.imobile3.passconnect.models.PassBarcode.Format;
import com.imobile3.passconnect.models.PassField;
import com.imobile3.passconnect.models.PassStyle;
import com.imobile3.passconnect.ui.PMProgressDialog;
import com.imobile3.passconnect.ui.PassBarcodeToggler;
import com.imobile3.passconnect.utils.NotificationHelper;
import com.imobile3.passconnect.views.PassMarketURLSpan;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

public class WalletPassActivity
  extends BaseActivity
{
  private ProgressDialog c;
  private Pass d = null;
  private final BroadcastReceiver e = new WalletPassActivity.7(this);
  
  public WalletPassActivity() {}
  
  private void a(Pass paramPass)
  {
    this.d = paramPass;
    findViewById(2131624407).setBackgroundColor(this.d.p());
    Object localObject1 = (ImageView)findViewById(2131624397);
    Object localObject2 = this.d.d(this);
    ((ImageView)localObject1).setImageBitmap((Bitmap)localObject2);
    int i;
    int j;
    Object localObject3;
    if (localObject2 != null)
    {
      i = (int)(getResources().getDisplayMetrics().density * 160.0F);
      j = (int)(getResources().getDisplayMetrics().density * 50.0F);
      if (this.d.n().a().size() > 0) {
        i = getResources().getDisplayMetrics().widthPixels / 2;
      }
      localObject3 = ((ImageView)localObject1).getLayoutParams();
      float f = j / ((Bitmap)localObject2).getHeight();
      ((ViewGroup.LayoutParams)localObject3).width = Math.min(i, (int)(((Bitmap)localObject2).getWidth() * f));
      ((ImageView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject3);
    }
    localObject1 = (TextView)findViewById(2131624408);
    Object localObject4;
    if (!TextUtils.isEmpty(this.d.s()))
    {
      ((TextView)localObject1).setVisibility(0);
      ((TextView)localObject1).setText(this.d.s());
      ((TextView)localObject1).setTextColor(this.d.q());
      localObject1 = (LinearLayout)findViewById(2131624409);
      a(this.d.n().a(), (LinearLayout)localObject1, 2130903188, 3, true, false);
      localObject1 = (ViewStub)findViewById(2131624410);
      if (!this.d.a()) {
        break label1052;
      }
      if (localObject1 != null)
      {
        ((ViewStub)localObject1).setLayoutResource(2130903189);
        ((ViewStub)localObject1).inflate();
      }
      ((ImageView)findViewById(2131624425)).setImageBitmap(this.d.c(this));
      localObject1 = (LinearLayout)findViewById(2131624420);
      localObject2 = (LinearLayout)findViewById(2131624422);
      localObject3 = this.d.n().b();
      localObject4 = new ArrayList();
      if (((List)localObject3).size() > 1) {
        ((List)localObject4).add(((List)localObject3).get(1));
      }
      a((List)localObject3, (LinearLayout)localObject1, 2130903185, 1, false, false);
      a((List)localObject4, (LinearLayout)localObject2, 2130903185, 1, true, false);
      ((TextView)findViewById(2131624421)).setTextColor(this.d.q());
      localObject1 = (LinearLayout)findViewById(2131624424);
      a(this.d.n().c(), (LinearLayout)localObject1, 2130903188, 4, false, false);
      localObject1 = (LinearLayout)findViewById(2131624423);
      a(this.d.n().d(), (LinearLayout)localObject1, 2130903188, 4, false, false);
      label483:
      localObject1 = (ImageView)findViewById(2131624375);
      localObject2 = (TextView)findViewById(2131624426);
      if (this.d.o() == null) {
        break label1836;
      }
      if (!TextUtils.isEmpty(this.d.o().a())) {
        break label1800;
      }
      ((TextView)localObject2).setVisibility(8);
      label538:
      ((ImageView)localObject1).setOnClickListener(new PassBarcodeToggler((ImageView)localObject1, this.d.o()));
      label559:
      localObject2 = (TextView)findViewById(2131624411);
      localObject1 = Calendar.getInstance();
      ((Calendar)localObject1).setTime(this.d.i(this));
      localObject3 = Calendar.getInstance();
      ((Calendar)localObject3).set(11, 0);
      ((Calendar)localObject3).set(12, 0);
      ((Calendar)localObject3).set(13, 0);
      ((Calendar)localObject3).add(5, -1);
      if (!((Calendar)localObject1).before(localObject3)) {
        break label1853;
      }
      localObject1 = SimpleDateFormat.getDateInstance(3).format(((Calendar)localObject1).getTime());
      label648:
      ((TextView)localObject2).setText((CharSequence)localObject1);
      localObject2 = findViewById(2131624412);
      ((View)localObject2).setVisibility(8);
      if (this.d.j() != null)
      {
        localObject1 = this.d.j().optString("androidAppTypeIdentifier");
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          ((View)localObject2).setVisibility(0);
          j = 0;
          localObject2 = getPackageManager();
          localObject3 = ((PackageManager)localObject2).getInstalledApplications(0).iterator();
          do
          {
            i = j;
            if (!((Iterator)localObject3).hasNext()) {
              break;
            }
          } while (!((String)localObject1).equals(((ApplicationInfo)((Iterator)localObject3).next()).packageName));
          i = 1;
          localObject3 = (Button)findViewById(2131624413);
          if (i == 0) {
            break label2041;
          }
          ((Button)localObject3).setText(2131165543);
          ((Button)localObject3).setOnClickListener(new WalletPassActivity.2(this, (PackageManager)localObject2, (String)localObject1));
        }
      }
      label808:
      localObject1 = (SwitchCompat)findViewById(2131624414);
      ((SwitchCompat)localObject1).setChecked(paramPass.x());
      ((SwitchCompat)localObject1).setOnCheckedChangeListener(new WalletPassActivity.4(this));
      localObject1 = (SwitchCompat)findViewById(2131624415);
      ((SwitchCompat)localObject1).setChecked(paramPass.y());
      ((SwitchCompat)localObject1).setOnCheckedChangeListener(new WalletPassActivity.5(this));
      paramPass = new ArrayList();
      localObject1 = findViewById(2131624417);
      localObject2 = findViewById(2131624416);
      ((View)localObject1).setVisibility(8);
      ((View)localObject2).setVisibility(8);
      localObject3 = this.d.n().e().iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject3).hasNext()) {
        break label2079;
      }
      localObject4 = (PassField)((Iterator)localObject3).next();
      if ((((PassField)localObject4).b().equals("Redeem Your Pass")) && (((PassField)localObject4).d().contains("passmarket.com")))
      {
        String str = ((PassField)localObject4).d().replaceAll("^.*:\\s(.*?)$", "$1");
        if (!TextUtils.isEmpty(str))
        {
          ((View)localObject1).setVisibility(0);
          ((View)localObject2).setVisibility(0);
          findViewById(2131624418).setOnClickListener(new WalletPassActivity.6(this, str));
          continue;
          ((TextView)localObject1).setVisibility(8);
          break;
          label1052:
          if ((this.d.b()) || (this.d.e()))
          {
            if (localObject1 != null)
            {
              ((ViewStub)localObject1).setLayoutResource(2130903190);
              ((ViewStub)localObject1).inflate();
            }
            ((ImageView)findViewById(2131624115)).setImageBitmap(this.d.e(this));
            localObject1 = (LinearLayout)findViewById(2131624427);
            a(this.d.n().b(), (LinearLayout)localObject1, 2130903184, -1, -1, 1, false, false);
            localObject1 = new ArrayList();
            ((List)localObject1).addAll(this.d.n().c());
            ((List)localObject1).addAll(this.d.n().d());
            a((List)localObject1, (LinearLayout)findViewById(2131624428), 2130903188, 4, false, false);
            break label483;
          }
          if (this.d.c())
          {
            if (this.d.a(this))
            {
              if (localObject1 != null)
              {
                ((ViewStub)localObject1).setLayoutResource(2130903191);
                ((ViewStub)localObject1).inflate();
              }
              ((ImageView)findViewById(2131624429)).setImageBitmap(this.d.f(this));
              i = Color.rgb(184, 184, 184);
              a(this.d.n().b(), (LinearLayout)findViewById(2131624427), 2130903186, -1, i, 1, false, false);
              a(this.d.n().c(), (LinearLayout)findViewById(2131624424), 2130903188, -1, i, 4, false, false);
              a(this.d.n().d(), (LinearLayout)findViewById(2131624423), 2130903188, -1, i, 4, false, false);
              break label483;
            }
            if (localObject1 != null)
            {
              ((ViewStub)localObject1).setLayoutResource(2130903192);
              ((ViewStub)localObject1).inflate();
            }
            ((ImageView)findViewById(2131624115)).setImageBitmap(this.d.e(this));
            a(this.d.n().b(), (LinearLayout)findViewById(2131624427), 2130903184, -1, -1, 1, false, false);
            a(this.d.n().c(), (LinearLayout)findViewById(2131624424), 2130903188, 4, false, false);
            a(this.d.n().d(), (LinearLayout)findViewById(2131624423), 2130903188, 4, false, false);
            break label483;
          }
          if (!this.d.d()) {
            break label483;
          }
          if (localObject1 != null)
          {
            ((ViewStub)localObject1).setLayoutResource(2130903193);
            ((ViewStub)localObject1).inflate();
          }
          localObject1 = (ImageView)findViewById(2131624429);
          if (!this.d.b(this)) {
            ((ImageView)localObject1).setVisibility(8);
          }
          for (;;)
          {
            localObject1 = (LinearLayout)findViewById(2131624427);
            a(this.d.n().b(), (LinearLayout)localObject1, 2130903187, 1, false, false);
            localObject1 = (LinearLayout)findViewById(2131624424);
            localObject2 = (LinearLayout)findViewById(2131624423);
            if ((this.d.o() == null) || (this.d.o().b() == null) || (!this.d.o().b().c())) {
              break label1755;
            }
            localObject3 = new ArrayList();
            ((List)localObject3).addAll(this.d.n().c());
            ((List)localObject3).addAll(this.d.n().d());
            a((List)localObject3, (LinearLayout)localObject1, 2130903188, 4, false, false);
            ((LinearLayout)localObject2).setVisibility(8);
            break;
            ((ImageView)localObject1).setImageBitmap(this.d.f(this));
          }
          label1755:
          a(this.d.n().c(), (LinearLayout)localObject1, 2130903188, 4, false, false);
          a(this.d.n().d(), (LinearLayout)localObject2, 2130903188, 4, false, false);
          break label483;
          label1800:
          ((TextView)localObject2).setVisibility(0);
          ((TextView)localObject2).setText(this.d.o().a());
          ((TextView)localObject2).setTextColor(this.d.q());
          break label538;
          label1836:
          ((ImageView)localObject1).setVisibility(8);
          ((TextView)localObject2).setVisibility(8);
          break label559;
          label1853:
          localObject3 = Calendar.getInstance();
          ((Calendar)localObject3).set(11, 0);
          ((Calendar)localObject3).set(12, 0);
          ((Calendar)localObject3).set(13, 0);
          if (((Calendar)localObject1).before(localObject3))
          {
            localObject1 = getString(2131165559);
            break label648;
          }
          long l = (Calendar.getInstance().getTimeInMillis() - ((Calendar)localObject1).getTimeInMillis()) / 60000L;
          if (l >= 120L)
          {
            localObject1 = getString(2131165556, new Object[] { Long.valueOf(l / 60L) });
            break label648;
          }
          if (l >= 60L)
          {
            localObject1 = getString(2131165555, new Object[] { Long.valueOf(l / 60L) });
            break label648;
          }
          if (l > 10L)
          {
            localObject1 = getString(2131165558, new Object[] { Long.valueOf(l) });
            break label648;
          }
          localObject1 = getString(2131165557);
          break label648;
          label2041:
          ((Button)localObject3).setText(2131165544);
          ((Button)localObject3).setOnClickListener(new WalletPassActivity.3(this, (String)localObject1));
          break label808;
        }
      }
      paramPass.add(localObject4);
    }
    label2079:
    a(paramPass, (LinearLayout)findViewById(2131624419), 2130903183, getResources().getColor(2131558535), getResources().getColor(2131558536), -1, false, true);
  }
  
  private void a(List<PassField> paramList, LinearLayout paramLinearLayout, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
  {
    LayoutInflater localLayoutInflater = LayoutInflater.from(paramLinearLayout.getContext());
    paramLinearLayout.removeAllViews();
    int i = paramList.size();
    if (paramInt4 != -1) {}
    for (paramInt4 = Math.min(paramList.size(), paramInt4);; paramInt4 = i)
    {
      float f1 = getResources().getDisplayMetrics().scaledDensity;
      int k = (int)(6.0F * getResources().getDisplayMetrics().density);
      i = 0;
      if (i < paramInt4)
      {
        if (paramBoolean1) {}
        LinearLayout localLinearLayout;
        LinearLayout.LayoutParams localLayoutParams;
        Object localObject;
        TextView localTextView;
        for (PassField localPassField = (PassField)paramList.get(paramInt4 - 1 - i);; localPassField = (PassField)paramList.get(i))
        {
          localLinearLayout = (LinearLayout)localLayoutInflater.inflate(paramInt1, null);
          localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
          if ((paramLinearLayout.getOrientation() == 0) && (i < paramInt4 - 1)) {
            localLayoutParams.rightMargin = k;
          }
          localObject = (TextView)localLinearLayout.findViewById(2131624117);
          localTextView = (TextView)localLinearLayout.findViewById(2131624116);
          ((TextView)localObject).setTextColor(paramInt2);
          localTextView.setTextColor(paramInt3);
          if (paramLinearLayout.getOrientation() == 0)
          {
            float f2 = 30.0F / paramInt4 / localPassField.e().length();
            if (f2 < 1.0F) {
              localTextView.setTextSize(0, Math.max(f2 * localTextView.getTextSize(), 10.0F * f1));
            }
            localLayoutParams.width = 0;
            localLayoutParams.weight = ((float)Math.sqrt(localPassField.e().length() * localTextView.getTextSize()));
          }
          ((TextView)localObject).setText(localPassField.b());
          if (!paramBoolean2) {
            break label569;
          }
          Linkify.addLinks((TextView)localObject, 15);
          localObject = new SpannableString(localPassField.e());
          Linkify.addLinks((Spannable)localObject, 15);
          URLSpan[] arrayOfURLSpan = (URLSpan[])((SpannableString)localObject).getSpans(0, ((SpannableString)localObject).length(), URLSpan.class);
          int m = arrayOfURLSpan.length;
          int j = 0;
          while (j < m)
          {
            URLSpan localURLSpan = arrayOfURLSpan[j];
            String str = localURLSpan.getURL().toLowerCase(Locale.US);
            if ((str.contains("passmarket.com")) || (str.contains("bit.ly")) || (str.contains("psmkt.me")))
            {
              int n = ((SpannableString)localObject).getSpanStart(localURLSpan);
              int i1 = ((SpannableString)localObject).getSpanEnd(localURLSpan);
              int i2 = ((SpannableString)localObject).getSpanFlags(localURLSpan);
              ((SpannableString)localObject).removeSpan(localURLSpan);
              ((SpannableString)localObject).setSpan(new PassMarketURLSpan(localURLSpan.getURL()), n, i1, i2);
            }
            j += 1;
          }
        }
        localTextView.setText((CharSequence)localObject, TextView.BufferType.SPANNABLE);
        localTextView.setMovementMethod(LinkMovementMethod.getInstance());
        label509:
        switch (WalletPassActivity.8.a[localPassField.c().ordinal()])
        {
        }
        for (;;)
        {
          paramLinearLayout.addView(localLinearLayout, localLayoutParams);
          i += 1;
          break;
          label569:
          localTextView.setText(localPassField.e());
          break label509;
          localLinearLayout.setGravity(8388611);
          continue;
          localLinearLayout.setGravity(1);
          continue;
          localLinearLayout.setGravity(8388613);
          continue;
          if (paramBoolean1) {
            localLinearLayout.setGravity(8388613);
          } else {
            localLinearLayout.setGravity(8388611);
          }
        }
      }
      return;
    }
  }
  
  private void a(List<PassField> paramList, LinearLayout paramLinearLayout, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    a(paramList, paramLinearLayout, paramInt1, this.d.r(), this.d.q(), paramInt2, paramBoolean1, paramBoolean2);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    paramBundle = Pass.b(this, getIntent().getStringExtra("pass_serialnumber"), getIntent().getStringExtra("pass_type_identifier"));
    if (paramBundle == null)
    {
      finish();
      return;
    }
    setContentView(2130903182);
    a(1.0F);
    a(paramBundle);
    NotificationHelper.a(this, 2, paramBundle);
    LocalBroadcastManager.getInstance(this).registerReceiver(this.e, new IntentFilter("service_event"));
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131755018, paramMenu);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if ((this.c != null) && (this.c.isShowing()))
    {
      this.c.dismiss();
      this.c = null;
    }
    LocalBroadcastManager.getInstance(this).unregisterReceiver(this.e);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i == 2131624442)
    {
      this.c = PMProgressDialog.a(this);
      this.d.a(false, 0);
      return true;
    }
    if (i == 2131624436)
    {
      new AlertDialog.Builder(this).setTitle(2131165541).setMessage(2131165540).setPositiveButton(2131165539, new WalletPassActivity.1(this)).setNegativeButton(17039360, null).show();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
}
