package flar2.homebutton.adapters;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ResolveInfo.DisplayNameComparator;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.NfcAdapter;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v4.a.a;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import flar2.homebutton.ButtonPrefActivity.a;
import flar2.homebutton.MainActivity;
import flar2.homebutton.MainActivity.NestedFragment;
import flar2.homebutton.MainApp;
import flar2.homebutton.utils.g;
import flar2.homebutton.utils.i;
import flar2.homebutton.utils.j.a;
import flar2.homebutton.utils.n;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class AppPickerPref
  extends DialogPreference
  implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener
{
  public static MainActivity.NestedFragment a;
  public static ButtonPrefActivity.a b;
  private static LruCache<String, BitmapDrawable> s = new LruCache(Math.min((int)Runtime.getRuntime().maxMemory() / 6, 2097152))
  {
    protected int a(String paramAnonymousString, BitmapDrawable paramAnonymousBitmapDrawable)
    {
      return paramAnonymousBitmapDrawable.getBitmap().getByteCount();
    }
  };
  private g c;
  private i d;
  private Context e;
  private ListView f;
  private ProgressBar g;
  private AsyncTask<Void, Void, ArrayList<c>> h;
  private String i;
  private int j;
  private PackageManager k;
  private Resources l;
  private int m;
  private Spinner n;
  private ImageButton o;
  private b p;
  private int q;
  private String r;
  
  public AppPickerPref(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.e = paramContext;
    this.l = this.e.getResources();
    this.i = ((String)getSummary());
    this.r = getKey();
    this.j = ((int)TypedValue.applyDimension(1, 32.0F, this.l.getDisplayMetrics()));
    this.q = ((int)TypedValue.applyDimension(1, 52.0F, this.l.getDisplayMetrics()));
    this.k = this.e.getPackageManager();
    this.m = 0;
    this.p = new b();
    this.d = new i(paramContext);
    setDialogLayoutResource(2131492903);
    setPositiveButtonText(null);
    setDialogTitle(null);
  }
  
  public AppPickerPref(Context paramContext, AttributeSet paramAttributeSet, String paramString)
  {
    super(paramContext, paramAttributeSet);
    this.e = paramContext;
    this.l = this.e.getResources();
    this.i = ((String)getSummary());
    this.r = paramString;
    this.j = ((int)TypedValue.applyDimension(1, 32.0F, this.l.getDisplayMetrics()));
    this.q = ((int)TypedValue.applyDimension(1, 52.0F, this.l.getDisplayMetrics()));
    this.k = this.e.getPackageManager();
    this.m = 0;
    this.p = new b();
    this.d = new i(paramContext);
    setDialogLayoutResource(2131492903);
    setPositiveButtonText(null);
    setNegativeButtonText(null);
    setDialogTitle(null);
  }
  
  private void a(String paramString)
  {
    persistString(paramString);
    this.p = b(paramString);
    setSummary(this.p.a);
    if (this.o != null) {
      this.o.setImageDrawable(this.p.b);
    }
  }
  
  private static Bitmap b(Drawable paramDrawable)
  {
    if (paramDrawable == null) {
      return null;
    }
    if ((paramDrawable instanceof BitmapDrawable)) {
      return ((BitmapDrawable)paramDrawable).getBitmap();
    }
    int i1 = paramDrawable.getIntrinsicWidth();
    int i2 = 1;
    if (i1 <= 0) {
      i1 = 1;
    }
    int i3 = paramDrawable.getIntrinsicHeight();
    if (i3 > 0) {
      i2 = i3;
    }
    Bitmap localBitmap = Bitmap.createBitmap(i1, i2, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
    paramDrawable.draw(localCanvas);
    return localBitmap;
  }
  
  private b b(String paramString)
  {
    b localB = new b();
    if (paramString == null) {
      return localB;
    }
    for (;;)
    {
      try
      {
        paramString = Intent.parseUri(paramString, 0);
        if (paramString.getStringExtra("iconResName") != null)
        {
          i1 = this.l.getIdentifier(paramString.getStringExtra("iconResName"), "drawable", this.e.getPackageName());
          int i2 = paramString.getIntExtra("mode", 1);
          if (i2 == 1)
          {
            paramString = paramString.getComponent();
            paramString = this.k.getActivityInfo(paramString, 0);
            localB.a = paramString.loadLabel(this.k).toString();
            if (i1 != 0)
            {
              paramString = a.a(this.e, i1);
              localB.b = paramString;
              return localB;
            }
            paramString = paramString.loadIcon(this.k);
            continue;
          }
          if (i2 == 2)
          {
            localB.a = paramString.getStringExtra("prefLabel");
            if (i1 != 0)
            {
              paramString = a.a(this.e, i1);
              continue;
            }
            if (paramString.hasExtra("icon"))
            {
              paramString = paramString.getStringExtra("icon");
              if (paramString != null)
              {
                paramString = new FileInputStream(new File(paramString));
                localB.b = new BitmapDrawable(this.l, BitmapFactory.decodeStream(paramString));
                paramString.close();
                return localB;
              }
            }
          }
          else if (i2 == 0)
          {
            localB.a = paramString.getStringExtra("prefLabel");
            if (i1 != 0)
            {
              paramString = a.a(this.e, i1);
              continue;
            }
          }
          return localB;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return localB;
      }
      int i1 = 0;
    }
  }
  
  private void b()
  {
    this.h = new AsyncTask()
    {
      protected ArrayList<c> a(Void... paramAnonymousVarArgs)
      {
        ArrayList localArrayList = new ArrayList();
        Object localObject1 = new ArrayList();
        if (AppPickerPref.b(AppPickerPref.this) == 0)
        {
          localArrayList.add(new AppPickerPref.af(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.n(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.q(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.g(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.ak(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.ab(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.ar(AppPickerPref.this));
          if (!AppPickerPref.e(AppPickerPref.this).contains("edge")) {
            localArrayList.add(new AppPickerPref.y(AppPickerPref.this));
          }
          localArrayList.add(new AppPickerPref.am(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.bf(AppPickerPref.this));
          if (Build.VERSION.SDK_INT >= 21)
          {
            localArrayList.add(new AppPickerPref.ai(AppPickerPref.this));
            localArrayList.add(new AppPickerPref.an(AppPickerPref.this));
          }
          if (Build.VERSION.SDK_INT >= 24)
          {
            if (AppPickerPref.e(AppPickerPref.this).contains("edge")) {
              break label297;
            }
            paramAnonymousVarArgs = new AppPickerPref.ay(AppPickerPref.this);
          }
          else
          {
            paramAnonymousVarArgs = new AppPickerPref.az(AppPickerPref.this);
          }
          localArrayList.add(paramAnonymousVarArgs);
          label297:
          localArrayList.add(new AppPickerPref.ba(AppPickerPref.this));
          if (MainApp.a()) {}
          for (paramAnonymousVarArgs = new AppPickerPref.as(AppPickerPref.this);; paramAnonymousVarArgs = new AppPickerPref.at(AppPickerPref.this))
          {
            localArrayList.add(paramAnonymousVarArgs);
            break;
          }
          if (MainApp.a()) {
            localArrayList.add(new AppPickerPref.u(AppPickerPref.this));
          }
          for (paramAnonymousVarArgs = new AppPickerPref.l(AppPickerPref.this);; paramAnonymousVarArgs = new AppPickerPref.m(AppPickerPref.this))
          {
            localArrayList.add(paramAnonymousVarArgs);
            break;
            localArrayList.add(new AppPickerPref.v(AppPickerPref.this));
          }
          if (Build.VERSION.SDK_INT >= 19) {
            localArrayList.add(new AppPickerPref.r(AppPickerPref.this));
          }
          localArrayList.add(new AppPickerPref.ac(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.ax(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.aw(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.bi(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.ad(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.bh(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.bg(AppPickerPref.this));
          if (Build.VERSION.SDK_INT >= 19)
          {
            localArrayList.add(new AppPickerPref.aa(AppPickerPref.this, 2131820838, 2131230904, 88));
            localArrayList.add(new AppPickerPref.aa(AppPickerPref.this, 2131820836, 2131230895, 87));
            localArrayList.add(new AppPickerPref.aa(AppPickerPref.this, 2131820837, 2131230902, 85));
          }
          if ((AppPickerPref.f(AppPickerPref.this).c("pref_root").booleanValue()) || (Build.VERSION.SDK_INT >= 21))
          {
            localArrayList.add(new AppPickerPref.ap(AppPickerPref.this));
            localArrayList.add(new AppPickerPref.ao(AppPickerPref.this));
            localArrayList.add(new AppPickerPref.k(AppPickerPref.this));
            localArrayList.add(new AppPickerPref.ah(AppPickerPref.this));
          }
          localArrayList.add(new AppPickerPref.p(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.x(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.aj(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.ag(AppPickerPref.this));
          if (Build.VERSION.SDK_INT >= 21) {
            localArrayList.add(new AppPickerPref.j(AppPickerPref.this));
          }
          localArrayList.add(new AppPickerPref.i(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.h(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.e(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.f(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.be(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.bc(AppPickerPref.this));
          if (NfcAdapter.getDefaultAdapter(AppPickerPref.this.getContext()) != null) {
            localArrayList.add(new AppPickerPref.ae(AppPickerPref.this));
          }
          if (AppPickerPref.f(AppPickerPref.this).c("pref_root").booleanValue()) {
            localArrayList.add(new AppPickerPref.bb(AppPickerPref.this));
          }
          if (Build.VERSION.SDK_INT < 26) {
            localArrayList.add(new AppPickerPref.bd(AppPickerPref.this));
          }
          localArrayList.add(new AppPickerPref.al(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.t(AppPickerPref.this));
          localArrayList.add(new AppPickerPref.z(AppPickerPref.this));
          if (MainApp.a()) {}
          for (paramAnonymousVarArgs = new AppPickerPref.bj(AppPickerPref.this);; paramAnonymousVarArgs = new AppPickerPref.bk(AppPickerPref.this))
          {
            localArrayList.add(paramAnonymousVarArgs);
            break;
          }
          localArrayList.add(new AppPickerPref.w(AppPickerPref.this));
          if ((!AppPickerPref.e(AppPickerPref.this).contains("edge")) && (!MainActivity.a(AppPickerPref.this.getContext()))) {
            localArrayList.add(new AppPickerPref.aq(AppPickerPref.this));
          }
          if (!MainActivity.a(AppPickerPref.this.getContext())) {
            localArrayList.add(new AppPickerPref.s(AppPickerPref.this));
          }
          if ((!AppPickerPref.e(AppPickerPref.this).contains("edge")) && (!MainActivity.a(AppPickerPref.this.getContext()))) {
            localArrayList.add(new AppPickerPref.d(AppPickerPref.this));
          }
          if ((Build.VERSION.SDK_INT >= 21) && (!MainActivity.a(AppPickerPref.this.getContext())))
          {
            localArrayList.add(new AppPickerPref.o(AppPickerPref.this));
            return localArrayList;
          }
        }
        else
        {
          Object localObject3 = AppPickerPref.g(AppPickerPref.this).getInstalledApplications(128);
          Object localObject2 = new Intent();
          new Intent();
          if (AppPickerPref.b(AppPickerPref.this) == 1)
          {
            if ((Build.VERSION.SDK_INT >= 21) && (MainActivity.a(AppPickerPref.this.getContext())))
            {
              ((Intent)localObject2).setAction("android.intent.action.MAIN");
              paramAnonymousVarArgs = "android.intent.category.LEANBACK_LAUNCHER";
            }
            else
            {
              ((Intent)localObject2).setAction("android.intent.action.MAIN");
              paramAnonymousVarArgs = "android.intent.category.LAUNCHER";
            }
            ((Intent)localObject2).addCategory(paramAnonymousVarArgs);
          }
          else if (AppPickerPref.b(AppPickerPref.this) == 2)
          {
            ((Intent)localObject2).setAction("android.intent.action.CREATE_SHORTCUT");
          }
          paramAnonymousVarArgs = ((List)localObject3).iterator();
          while (paramAnonymousVarArgs.hasNext())
          {
            localObject3 = (ApplicationInfo)paramAnonymousVarArgs.next();
            if (isCancelled()) {
              break;
            }
            ((Intent)localObject2).setPackage(((ApplicationInfo)localObject3).packageName);
            localObject3 = AppPickerPref.g(AppPickerPref.this).queryIntentActivities((Intent)localObject2, 0).iterator();
            while (((Iterator)localObject3).hasNext()) {
              ((List)localObject1).add((ResolveInfo)((Iterator)localObject3).next());
            }
          }
          Collections.sort((List)localObject1, new ResolveInfo.DisplayNameComparator(AppPickerPref.g(AppPickerPref.this)));
          localObject1 = ((List)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            paramAnonymousVarArgs = (ResolveInfo)((Iterator)localObject1).next();
            if (isCancelled()) {
              return localArrayList;
            }
            localObject2 = paramAnonymousVarArgs.loadLabel(AppPickerPref.g(AppPickerPref.this)).toString();
            if (AppPickerPref.b(AppPickerPref.this) == 2) {
              paramAnonymousVarArgs = new AppPickerPref.av(AppPickerPref.this, (String)localObject2, paramAnonymousVarArgs);
            } else {
              paramAnonymousVarArgs = new AppPickerPref.c(AppPickerPref.this, (String)localObject2, paramAnonymousVarArgs);
            }
            localArrayList.add(paramAnonymousVarArgs);
          }
        }
        return localArrayList;
      }
      
      protected void a(ArrayList<c> paramAnonymousArrayList)
      {
        AppPickerPref.d(AppPickerPref.this).setAdapter(new d(AppPickerPref.h(AppPickerPref.this), paramAnonymousArrayList));
        ((d)AppPickerPref.d(AppPickerPref.this).getAdapter()).notifyDataSetChanged();
        if (AppPickerPref.b(AppPickerPref.this) != 0)
        {
          AppPickerPref.c(AppPickerPref.this).setVisibility(8);
          AppPickerPref.d(AppPickerPref.this).setVisibility(0);
        }
      }
      
      protected void onPreExecute()
      {
        super.onPreExecute();
        if (AppPickerPref.b(AppPickerPref.this) != 0)
        {
          AppPickerPref.c(AppPickerPref.this).setVisibility(0);
          AppPickerPref.d(AppPickerPref.this).setVisibility(4);
          return;
        }
        AppPickerPref.c(AppPickerPref.this).setVisibility(8);
      }
    }.execute(new Void[0]);
  }
  
  private String c(String paramString)
  {
    i1 = -1;
    try
    {
      switch (paramString.hashCode())
      {
      case 1671580406: 
        if (!paramString.equals("pref_mute_single_tap")) {
          break label1504;
        }
        i1 = 18;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        label1444:
        continue;
        switch (i1)
        {
        }
      }
    }
    if (paramString.equals("pref_voldown_single_tap"))
    {
      i1 = 9;
      break label1504;
      if (paramString.equals("pref_mute_double_tap"))
      {
        i1 = 19;
        break label1504;
        if (paramString.equals("pref_voldown_double_tap"))
        {
          i1 = 10;
          break label1504;
          if (paramString.equals("pref_headsethook_long_press"))
          {
            i1 = 26;
            break label1504;
            if (paramString.equals("pref_bixby_long_press"))
            {
              i1 = 23;
              break label1504;
              if (paramString.equals("pref_back_single_tap"))
              {
                i1 = 3;
                break label1504;
                if (paramString.equals("pref_back_double_tap"))
                {
                  i1 = 4;
                  break label1504;
                  if (paramString.equals("pref_volup_single_tap"))
                  {
                    i1 = 12;
                    break label1504;
                    if (paramString.equals("pref_mute_long_press"))
                    {
                      i1 = 20;
                      break label1504;
                      if (paramString.equals("pref_volup_double_tap"))
                      {
                        i1 = 13;
                        break label1504;
                        if (paramString.equals("pref_voldown_long_press"))
                        {
                          i1 = 11;
                          break label1504;
                          if (paramString.equals("pref_recents_single_tap"))
                          {
                            i1 = 6;
                            break label1504;
                            if (paramString.equals("pref_cam_single_tap"))
                            {
                              i1 = 15;
                              break label1504;
                              if (paramString.equals("pref_recents_double_tap"))
                              {
                                i1 = 7;
                                break label1504;
                                if (paramString.equals("pref_cam_double_tap"))
                                {
                                  i1 = 16;
                                  break label1504;
                                  if (paramString.equals("pref_back_long_press"))
                                  {
                                    i1 = 5;
                                    break label1504;
                                    if (paramString.equals("pref_volup_long_press"))
                                    {
                                      i1 = 14;
                                      break label1504;
                                      if (paramString.equals("pref_home_single_tap"))
                                      {
                                        i1 = 0;
                                        break label1504;
                                        if (paramString.equals("pref_home_double_tap"))
                                        {
                                          i1 = 1;
                                          break label1504;
                                          if (paramString.equals("pref_recents_long_press"))
                                          {
                                            i1 = 8;
                                            break label1504;
                                            if (paramString.equals("pref_cam_long_press"))
                                            {
                                              i1 = 17;
                                              break label1504;
                                              if (paramString.equals("pref_headsethook_single_tap"))
                                              {
                                                i1 = 24;
                                                break label1504;
                                                if (paramString.equals("pref_headsethook_double_tap"))
                                                {
                                                  i1 = 25;
                                                  break label1504;
                                                  if (paramString.equals("pref_bixby_single_tap"))
                                                  {
                                                    i1 = 21;
                                                    break label1504;
                                                    if (paramString.equals("pref_home_long_press"))
                                                    {
                                                      i1 = 2;
                                                      break label1504;
                                                      if (paramString.equals("pref_bixby_double_tap"))
                                                      {
                                                        i1 = 22;
                                                        break label1504;
                                                        break label1444;
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.z);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.A);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.y);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.w);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.x);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.v);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.t);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.u);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.s);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.q);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.r);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.p);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.k);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.l);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.j);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.n);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.o);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.m);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.h);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.i);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.g);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.e);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.f);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.d);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.b);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.c);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(j.a.a);
                                                        return ((StringBuilder)localObject).toString();
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(paramString);
                                                        localObject = ((StringBuilder)localObject).toString();
                                                        return localObject;
                                                        localObject = new StringBuilder();
                                                        ((StringBuilder)localObject).append("buttonmapper.");
                                                        ((StringBuilder)localObject).append(paramString);
                                                        return ((StringBuilder)localObject).toString();
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  protected void onBindDialogView(View paramView)
  {
    super.onBindDialogView(paramView);
    this.f = ((ListView)paramView.findViewById(2131362033));
    this.f.setOnItemClickListener(this);
    this.g = ((ProgressBar)paramView.findViewById(2131362171));
    this.n = ((Spinner)paramView.findViewById(2131362118));
    ArrayAdapter localArrayAdapter = new ArrayAdapter(getContext(), 2131493014, 2131361852, new ArrayList(Arrays.asList(new String[] { this.e.getString(2131820657), this.e.getString(2131820658), this.e.getString(2131820662) })));
    if (this.d.c("pref_contrast").booleanValue()) {}
    for (int i1 = 2131493013;; i1 = 2131493012)
    {
      localArrayAdapter.setDropDownViewResource(i1);
      break;
    }
    this.n.setAdapter(localArrayAdapter);
    this.n.setOnItemSelectedListener(this);
    this.m = this.n.getSelectedItemPosition();
    if (!this.d.c("pref_spinner_hint").booleanValue()) {
      paramView.findViewById(2131362224).setVisibility(0);
    }
    b();
  }
  
  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    LinearLayout localLinearLayout = (LinearLayout)paramView.findViewById(16908312);
    if (Build.VERSION.SDK_INT >= 26)
    {
      localLinearLayout.removeAllViews();
      this.o = new ImageButton(this.e);
    }
    for (LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(this.q, this.q);; localLayoutParams = new LinearLayout.LayoutParams(this.q, this.q))
    {
      localLayoutParams.gravity = 17;
      this.o.setLayoutParams(localLayoutParams);
      this.o.setScaleType(ImageView.ScaleType.CENTER_CROP);
      this.o.setImageDrawable(this.p.b);
      this.o.setFocusable(false);
      this.o.setBackgroundColor(0);
      this.o.setEnabled(false);
      localLinearLayout.addView(this.o);
      localLinearLayout.setVisibility(0);
      break;
      this.o = new ImageButton(this.e);
    }
    this.c = ((g)paramView.getContext());
  }
  
  public void onDismiss(DialogInterface paramDialogInterface)
  {
    if ((this.h != null) && (this.h.getStatus() == AsyncTask.Status.RUNNING)) {
      this.h.cancel(true);
    }
    this.h = null;
  }
  
  protected Object onGetDefaultValue(TypedArray paramTypedArray, int paramInt)
  {
    return paramTypedArray.getString(paramInt);
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (c)paramAdapterView.getItemAtPosition(paramInt);
    if ((this.m != 1) && (this.m != 0))
    {
      if (this.m != 2) {
        break label898;
      }
      paramView = (av)paramAdapterView;
      if (paramView.a() == null) {
        a(null);
      }
    }
    for (;;)
    {
      getDialog().dismiss();
      return;
      label360:
      label379:
      label898:
      try
      {
        paramView.a(new Object()
        {
          public void a(AppPickerPref.av paramAnonymousAv)
          {
            AppPickerPref.a(AppPickerPref.this, paramAnonymousAv.c());
            if (AppPickerPref.a != null) {
              AppPickerPref.a.onSharedPreferenceChanged(AppPickerPref.this.getSharedPreferences(), AppPickerPref.this.getKey());
            }
            if (AppPickerPref.b != null) {
              AppPickerPref.b.onSharedPreferenceChanged(AppPickerPref.this.getSharedPreferences(), AppPickerPref.this.getKey());
            }
            AppPickerPref.this.getDialog().dismiss();
          }
        });
        if (a != null) {
          a.a((av)paramAdapterView);
        }
        if (b != null)
        {
          b.a((av)paramAdapterView);
          return;
          c localC = (c)paramAdapterView;
          try
          {
            if (localC.d().getAction().equals("homebutton.intent.action.TASKER"))
            {
              ((ClipboardManager)getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", c(this.r)));
              Toast.makeText(getContext(), 2131820715, 0).show();
            }
            if (localC.d().getAction().equals("homebutton.intent.action.CUSTOM_KEY"))
            {
              String str = this.d.b(this.r);
              if (MainApp.a())
              {
                if (this.d.c("pref_root").booleanValue()) {
                  paramView = this.c;
                }
                for (paramAdapterView = this.r;; paramAdapterView = this.r)
                {
                  paramView.b(paramAdapterView, str);
                  break label379;
                  paramAdapterView = n.d(getContext());
                  if (paramAdapterView == null) {
                    break label360;
                  }
                  paramView = new StringBuilder();
                  paramView.append(paramAdapterView.toString());
                  paramView.append(" adfddd5Tg");
                  if (n.c(paramView.toString()).contains("Connection Failed")) {
                    break;
                  }
                  this.d.a("keycode_server_disabled", false);
                  paramView = this.c;
                }
                this.d.a("keycode_server_disabled", true);
                this.d.a("pref_simulate_keycodes", false);
                MainActivity.g(getContext());
                getDialog().dismiss();
                return;
                this.d.a("pref_simulate_keycodes", false);
                getDialog().dismiss();
                return;
              }
            }
            if (((localC.d().getAction().equals("homebutton.intent.action.ACTION_IN_APP_SEARCH")) || (localC.d().getAction().equals("homebutton.intent.action.GOOGLE_NOW")) || (localC.d().getAction().equals("homebutton.intent.action.HEADSETHOOK")) || (localC.d().getAction().equals("homebutton.intent.action.ACTION_SELECT"))) && (!this.d.c("pref_root").booleanValue()) && (!n.c(getContext())))
            {
              this.c.n();
              getDialog().dismiss();
              return;
            }
            if (localC.d().getAction().equals("homebutton.intent.action.CUSTOM_INTENT"))
            {
              paramAdapterView = this.d.b(this.r);
              if (MainApp.a()) {
                this.c.a(this.r, paramAdapterView);
              }
            }
            if (localC.d().getAction().equals("homebutton.intent.action.ACTION_SHELL_CMD"))
            {
              paramAdapterView = this.d.b(this.r);
              if (MainApp.a()) {
                this.c.c(this.r, paramAdapterView);
              }
            }
            if (localC.d().getAction().equals("homebutton.intent.action.ACTION_LINK"))
            {
              paramAdapterView = this.d.b(this.r);
              this.c.d(this.r, paramAdapterView);
            }
            if (localC.d().getAction().equals("homebutton.intent.action.SPLITSCREEN")) {
              this.c.o();
            }
            if (((localC.d().getAction().equals("homebutton.intent.action.ACTION_IMMERSIVE")) || (localC.d().getAction().equals("homebutton.intent.action.ACTION_MOBILE")) || (localC.d().getAction().equals("homebutton.intent.action.ACTION_INVERT")) || (localC.d().getAction().equals("homebutton.intent.action.ACTION_NFC"))) && (!this.d.c("pref_root").booleanValue()) && (a.a(getContext(), "android.permission.WRITE_SECURE_SETTINGS") != 0))
            {
              this.c.l();
              getDialog().dismiss();
              return;
            }
            if (localC.d().getAction().equals("homebutton.intent.action.GET_PRO"))
            {
              this.c.k();
              getDialog().dismiss();
              return;
            }
            if (localC.d().getAction().equals("homebutton.intent.action.NOROOT"))
            {
              this.c.q();
              getDialog().dismiss();
              return;
            }
            if (localC.d().getAction().equals("homebutton.intent.action.PRENOUGAT"))
            {
              this.c.p();
              getDialog().dismiss();
              return;
            }
            a(localC.c());
            getDialog().dismiss();
            return;
          }
          catch (NullPointerException paramAdapterView)
          {
            paramAdapterView.printStackTrace();
          }
        }
        else {}
      }
      catch (NullPointerException paramAdapterView) {}
    }
  }
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if ((paramInt > 0) && (!this.d.c("pref_spinner_hint").booleanValue())) {
      this.d.a("pref_spinner_hint", true);
    }
    for (;;)
    {
      try
      {
        paramView = (ImageView)paramView.findViewById(2131361853);
        switch (paramInt)
        {
        case 2: 
          paramAdapterView = a.a(getContext(), 2131230920);
          paramView.setImageDrawable(paramAdapterView);
        }
      }
      catch (NullPointerException paramAdapterView)
      {
        try
        {
          this.m = paramInt;
          try
          {
            b();
            return;
          }
          catch (NullPointerException paramAdapterView)
          {
            return;
          }
          paramAdapterView = paramAdapterView;
          continue;
        }
        catch (NullPointerException paramAdapterView)
        {
          continue;
        }
      }
      paramAdapterView = a.a(getContext(), 2131230842);
      continue;
      paramAdapterView = a.a(getContext(), 2131230839);
    }
    for (;;) {}
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
  
  protected void onSetInitialValue(boolean paramBoolean, Object paramObject)
  {
    if (paramBoolean) {
      this.p = b(getPersistedString(null));
    }
    for (paramObject = this.p.a;; paramObject = this.i)
    {
      setSummary(paramObject);
      return;
      a(null);
    }
  }
  
  protected void showDialog(Bundle paramBundle)
  {
    super.showDialog(paramBundle);
    int i1;
    if (Build.VERSION.SDK_INT < 21)
    {
      i1 = getDialog().getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
      paramBundle = getDialog().findViewById(i1);
      i1 = getDialog().getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
      TextView localTextView = (TextView)getDialog().findViewById(i1);
      if (this.d.c("pref_contrast").booleanValue())
      {
        paramBundle.setBackgroundColor(a.c(this.e, 2131099679));
        paramBundle = this.e;
        i1 = 2131099831;
        i1 = a.c(paramBundle, i1);
        label118:
        localTextView.setTextColor(i1);
      }
      else
      {
        i1 = this.d.d("pref_color");
        if (i1 != 2) {
          switch (i1)
          {
          }
        }
        for (i1 = a.c(this.e, 2131099694);; i1 = a.c(this.e, 2131099695))
        {
          paramBundle.setBackgroundColor(i1);
          i1 = a.c(this.e, 2131099694);
          break label118;
          paramBundle.setBackgroundColor(a.c(this.e, 2131099699));
          paramBundle = this.e;
          i1 = 2131099805;
          break;
          paramBundle.setBackgroundColor(a.c(this.e, 2131099698));
          paramBundle = this.e;
          i1 = 2131099804;
          break;
          paramBundle.setBackgroundColor(a.c(this.e, 2131099679));
          paramBundle = this.e;
          i1 = 2131099674;
          break;
        }
      }
    }
    try
    {
      i1 = getContext().getResources().getDisplayMetrics().widthPixels * 80 / 100;
      if ((getContext().getResources().getConfiguration().orientation == 2) || (getContext().getResources().getBoolean(2131034116))) {
        i1 = getContext().getResources().getDisplayMetrics().widthPixels * 48 / 100;
      }
      int i2 = getContext().getResources().getDisplayMetrics().heightPixels * 94 / 100;
      getDialog().getWindow().setLayout(i1, i2);
      return;
    }
    catch (Exception paramBundle) {}
  }
  
  abstract class a
    extends AppPickerPref.c
  {
    a()
    {
      super(null);
      this.e = new Intent();
      this.e.putExtra("mode", 0);
    }
    
    public Drawable f_()
    {
      return this.c;
    }
  }
  
  class aa
    extends AppPickerPref.a
  {
    aa(int paramInt1, int paramInt2, int paramInt3)
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(paramInt1);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), paramInt2)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.MEDIA_CONTROL");
      this.e.putExtra("mediaControlKeycode", paramInt3);
      this.e.putExtra("keepScreenOff", true);
      this.e.putExtra("iconResName", AppPickerPref.j(AppPickerPref.this).getResourceEntryName(paramInt2));
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class ab
    extends AppPickerPref.a
  {
    ab()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820600);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230891)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.MENU");
      this.e.putExtra("iconResName", "ic_menu");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class ac
    extends AppPickerPref.a
  {
    ac()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820602);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230894)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.MUTE");
      this.e.putExtra("iconResName", "ic_mute");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class ad
    extends AppPickerPref.a
  {
    ad()
    {
      super();
      this.b = AppPickerPref.h(AppPickerPref.this).getString(2131820601);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230892)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.MIC_MUTE");
      this.e.putExtra("iconResName", "ic_mic_mute");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class ae
    extends AppPickerPref.a
  {
    ae()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820603);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230896)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_NFC");
      this.e.putExtra("iconResName", "ic_nfc");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class af
    extends AppPickerPref.a
  {
    af()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820659);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230897)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.NO_ACTION");
      this.e.putExtra("iconResName", "ic_no_action");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class ag
    extends AppPickerPref.a
  {
    ag()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820604);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230898)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.NOTIFICATIONS");
      this.e.putExtra("iconResName", "ic_notifications");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class ah
    extends AppPickerPref.a
  {
    ah()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820606);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230900)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_PASTE");
      this.e.putExtra("iconResName", "ic_paste");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class ai
    extends AppPickerPref.a
  {
    ai()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820607);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230903)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.POWER_DIALOG");
      this.e.putExtra("iconResName", "ic_power");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class aj
    extends AppPickerPref.a
  {
    aj()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820608);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230917)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.QUICK_SETTINGS");
      this.e.putExtra("iconResName", "ic_settings");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class ak
    extends AppPickerPref.a
  {
    ak()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820609);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230908)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.RECENTS");
      this.e.putExtra("iconResName", "ic_recent_apps");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class al
    extends AppPickerPref.a
  {
    al()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820612);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230911)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ROTATE");
      this.e.putExtra("iconResName", "ic_rotation");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class am
    extends AppPickerPref.a
  {
    am()
    {
      super();
      int i;
      if ((AppPickerPref.f(AppPickerPref.this).c("pref_root").booleanValue()) && (AppPickerPref.e(AppPickerPref.this).contains("vol")) && (AppPickerPref.f(AppPickerPref.this).c("pref_screenoff").booleanValue()))
      {
        localObject = AppPickerPref.j(AppPickerPref.this);
        i = 2131820614;
      }
      else
      {
        localObject = AppPickerPref.j(AppPickerPref.this);
        i = 2131820613;
      }
      this.b = ((Resources)localObject).getString(i);
      Object localObject = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230888)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), (Bitmap)localObject);
      this.e.setAction("homebutton.intent.action.SCREEN_OFF");
      this.e.putExtra("iconResName", "ic_lock");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class an
    extends AppPickerPref.a
  {
    an()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820615);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230912)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.SCREENSHOT");
      this.e.putExtra("iconResName", "ic_screenshot");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class ao
    extends AppPickerPref.a
  {
    ao()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820616);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230913)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_SCROLL_DOWN");
      this.e.putExtra("iconResName", "ic_scroll_down");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class ap
    extends AppPickerPref.a
  {
    ap()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820617);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230914)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_SCROLL_UP");
      this.e.putExtra("iconResName", "ic_scroll_up");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class aq
    extends AppPickerPref.a
  {
    aq()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820618);
      try
      {
        Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230915)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
        this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
        this.e.setAction("homebutton.intent.action.SEARCH");
        this.e.putExtra("iconResName", "ic_search");
        this.e.putExtra("prefLabel", this.b);
        return;
      }
      catch (NullPointerException this$1)
      {
        for (;;) {}
      }
    }
  }
  
  class ar
    extends AppPickerPref.a
  {
    ar()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820619);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230916)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_SELECT");
      this.e.putExtra("iconResName", "ic_select");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class as
    extends AppPickerPref.a
  {
    as()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820621);
      Object localObject = AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230919));
      if (AppPickerPref.f(AppPickerPref.this).c("pref_root").booleanValue())
      {
        this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820620);
        localObject = AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230910));
      }
      localObject = Bitmap.createScaledBitmap((Bitmap)localObject, AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), (Bitmap)localObject);
      this.e.setAction("homebutton.intent.action.ACTION_SHELL_CMD");
      if (AppPickerPref.f(AppPickerPref.this).c("pref_root").booleanValue()) {
        localObject = this.e;
      }
      for (this$1 = "ic_root";; this$1 = "ic_shell")
      {
        ((Intent)localObject).putExtra("iconResName", AppPickerPref.this);
        break;
        localObject = this.e;
      }
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class at
    extends AppPickerPref.a
  {
    at()
    {
      super();
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(AppPickerPref.j(AppPickerPref.this).getString(2131820621));
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(AppPickerPref.j(AppPickerPref.this).getString(2131820890));
      this.b = ((StringBuilder)localObject).toString();
      localObject = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230919)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      if (AppPickerPref.f(AppPickerPref.this).c("pref_root").booleanValue())
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(AppPickerPref.j(AppPickerPref.this).getString(2131820620));
        ((StringBuilder)localObject).append(" ");
        ((StringBuilder)localObject).append(AppPickerPref.j(AppPickerPref.this).getString(2131820890));
        this.b = ((StringBuilder)localObject).toString();
        localObject = AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230910));
      }
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), (Bitmap)localObject);
      this.e.setAction("homebutton.intent.action.GET_PRO");
      if (AppPickerPref.f(AppPickerPref.this).c("pref_root").booleanValue()) {
        localObject = this.e;
      }
      for (this$1 = "ic_root";; this$1 = "ic_shell")
      {
        ((Intent)localObject).putExtra("iconResName", AppPickerPref.this);
        break;
        localObject = this.e;
      }
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  public class av
    extends AppPickerPref.c
  {
    private Intent g;
    private AppPickerPref.3 h;
    
    public av(String paramString, ResolveInfo paramResolveInfo)
    {
      super(null);
      this.b = paramString;
      this.d = paramResolveInfo;
      if (this.d != null)
      {
        this.g = new Intent("android.intent.action.CREATE_SHORTCUT");
        this$1 = new ComponentName(this.d.activityInfo.packageName, this.d.activityInfo.name);
        this.g.setComponent(AppPickerPref.this);
        this.g.putExtra("HomeButton", true);
      }
    }
    
    public Intent a()
    {
      return this.g;
    }
    
    public void a(Intent paramIntent, String paramString, Bitmap paramBitmap)
    {
      if (paramIntent == null)
      {
        Toast.makeText(AppPickerPref.h(AppPickerPref.this), 2131820661, 1).show();
        return;
      }
      this.e = paramIntent;
      this.e.putExtra("mode", 2);
      if (paramString != null)
      {
        paramIntent = new StringBuilder();
        paramIntent.append(this.b);
        paramIntent.append(": ");
        paramIntent.append(paramString);
        this.b = paramIntent.toString();
        this.e.putExtra("label", paramString);
      }
      for (;;)
      {
        this.e.putExtra("prefLabel", this.b);
        break;
        this.e.putExtra("label", this.b);
      }
      if (paramBitmap != null) {
        this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), paramBitmap);
      }
      if (this.c != null) {
        try
        {
          paramIntent = AppPickerPref.h(AppPickerPref.this);
          paramString = new StringBuilder();
          paramString.append(paramIntent.getFilesDir());
          paramString.append("/app_picker");
          paramIntent = paramString.toString();
          paramString = new StringBuilder();
          paramString.append(paramIntent);
          paramString.append("/");
          paramString.append(UUID.randomUUID().toString());
          paramString = paramString.toString();
          paramIntent = new File(paramIntent);
          paramIntent.mkdirs();
          paramIntent.setReadable(true, false);
          paramIntent.setExecutable(true, false);
          paramString = new File(paramString);
          FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
          if (paramBitmap == null) {
            paramBitmap = this.c.getBitmap();
          }
          boolean bool;
          for (paramIntent = Bitmap.CompressFormat.PNG;; paramIntent = Bitmap.CompressFormat.PNG)
          {
            bool = paramBitmap.compress(paramIntent, 100, localFileOutputStream);
            break;
          }
          if (bool)
          {
            this.e.putExtra("icon", paramString.getAbsolutePath());
            paramString.setReadable(true, false);
          }
          localFileOutputStream.close();
        }
        catch (IOException paramIntent)
        {
          paramIntent.printStackTrace();
        }
        catch (FileNotFoundException paramIntent)
        {
          paramIntent.printStackTrace();
        }
      }
      if (this.h != null) {
        this.h.a(this);
      }
    }
    
    void a(AppPickerPref.3 param3)
    {
      this.h = param3;
    }
    
    public void b()
    {
      Toast.makeText(AppPickerPref.h(AppPickerPref.this), 2131820660, 0).show();
    }
    
    protected String f()
    {
      return this.g.toUri(0);
    }
  }
  
  class aw
    extends AppPickerPref.a
  {
    aw()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820630);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230929)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_VIBMODE");
      this.e.putExtra("iconResName", "ic_vibration");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class ax
    extends AppPickerPref.a
  {
    ax()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820622);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230921)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.SOUNDMODE");
      this.e.putExtra("iconResName", "ic_silent");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class ay
    extends AppPickerPref.a
  {
    ay()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820623);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230922)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.SPLITSCREEN");
      this.e.putExtra("iconResName", "ic_splitscreen");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class az
    extends AppPickerPref.a
  {
    az()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820623);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230922)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.PRENOUGAT");
      this.e.putExtra("iconResName", "ic_splitscreen");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class b
  {
    String a = AppPickerPref.a(AppPickerPref.this);
    Drawable b;
    
    b() {}
  }
  
  class ba
    extends AppPickerPref.a
  {
    ba()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820624);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230924)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.TASKER");
      this.e.putExtra("iconResName", "ic_tasker");
      this.e.putExtra("prefLabel", AppPickerPref.b(AppPickerPref.this, AppPickerPref.e(AppPickerPref.this)));
    }
  }
  
  class bb
    extends AppPickerPref.a
  {
    bb()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820581);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230841)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_AIRPLANE");
      this.e.putExtra("iconResName", "ic_airplane");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class bc
    extends AppPickerPref.a
  {
    bc()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820625);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230893)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_MOBILE");
      this.e.putExtra("iconResName", "ic_mobile");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class bd
    extends AppPickerPref.a
  {
    bd()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820626);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230877)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_HOTSPOT");
      this.e.putExtra("iconResName", "ic_hotspot");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class be
    extends AppPickerPref.a
  {
    be()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820628);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230933)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.WIFI");
      this.e.putExtra("iconResName", "ic_wifi");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class bf
    extends AppPickerPref.a
  {
    bf()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820627);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230871)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.TOGGLE_FLASHLIGHT");
      this.e.putExtra("keepScreenOff", true);
      this.e.putExtra("iconResName", "ic_flashlight");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class bg
    extends AppPickerPref.a
  {
    bg()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820991);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230930)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.VOL_DOWN");
      this.e.putExtra("iconResName", "ic_voldown");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class bh
    extends AppPickerPref.a
  {
    bh()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820993);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230932)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.VOL_UP");
      this.e.putExtra("iconResName", "ic_volup");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class bi
    extends AppPickerPref.a
  {
    bi()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820631);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230931)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.VOLUME_MUTE");
      this.e.putExtra("iconResName", "ic_volume_mute");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class bj
    extends AppPickerPref.a
  {
    bj()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131821006);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230935)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_ZELLO_PTT");
      this.e.putExtra("iconResName", "ic_zello");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class bk
    extends AppPickerPref.a
  {
    bk()
    {
      super();
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(AppPickerPref.j(AppPickerPref.this).getString(2131821006));
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(AppPickerPref.j(AppPickerPref.this).getString(2131820890));
      this.b = ((StringBuilder)localObject).toString();
      localObject = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230935)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), (Bitmap)localObject);
      this.e.setAction("homebutton.intent.action.GET_PRO");
      this.e.putExtra("iconResName", "ic_zello");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  public class c
  {
    String b;
    BitmapDrawable c;
    ResolveInfo d;
    Intent e;
    
    private c() {}
    
    c(String paramString, ResolveInfo paramResolveInfo)
    {
      this.b = paramString;
      this.d = paramResolveInfo;
      if (this.d != null)
      {
        if ((Build.VERSION.SDK_INT >= 21) && (MainActivity.a(AppPickerPref.this.getContext())))
        {
          this.e = new Intent("android.intent.action.MAIN");
          this.e.addCategory("android.intent.category.LEANBACK_LAUNCHER");
          this$1 = new ComponentName(this.d.activityInfo.packageName, this.d.activityInfo.name);
        }
        else
        {
          this.e = new Intent("android.intent.action.MAIN");
          this.e.addCategory("android.intent.category.LAUNCHER");
          this$1 = new ComponentName(this.d.activityInfo.packageName, this.d.activityInfo.name);
        }
        this.e.setComponent(AppPickerPref.this);
        this.e.putExtra("mode", 1);
      }
    }
    
    public String c()
    {
      if (this.e == null) {
        return null;
      }
      return this.e.toUri(0);
    }
    
    public Intent d()
    {
      return this.e;
    }
    
    public String e()
    {
      return this.b;
    }
    
    protected String f()
    {
      return c();
    }
    
    public Drawable f_()
    {
      if (this.d == null) {
        return null;
      }
      if (this.c == null)
      {
        String str = f();
        this.c = ((BitmapDrawable)AppPickerPref.a().get(str));
        if (this.c == null)
        {
          Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(this.d.loadIcon(AppPickerPref.g(AppPickerPref.this))), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
          this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
          AppPickerPref.a().put(str, this.c);
        }
      }
      return this.c;
    }
    
    public Drawable g()
    {
      return null;
    }
  }
  
  class d
    extends AppPickerPref.a
  {
    d()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820583);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230843)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ASSIST");
      this.e.putExtra("iconResName", "ic_assist");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class e
    extends AppPickerPref.a
  {
    e()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820584);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230844)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_AUTOBRIGHTNESS_TOGGLE");
      this.e.putExtra("iconResName", "ic_autobrightness");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class f
    extends AppPickerPref.a
  {
    f()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820586);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230836)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.BT_TOGGLE");
      this.e.putExtra("iconResName", "ic_action_bluetooth");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class g
    extends AppPickerPref.a
  {
    g()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820585);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230845)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.BACK");
      this.e.putExtra("iconResName", "ic_back");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class h
    extends AppPickerPref.a
  {
    h()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820682);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230850)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.BRIGHT_DOWN");
      this.e.putExtra("iconResName", "ic_brightness_down");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class i
    extends AppPickerPref.a
  {
    i()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820683);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230849)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.BRIGHT_UP");
      this.e.putExtra("iconResName", "ic_brightness");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class j
    extends AppPickerPref.a
  {
    public j()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820587);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230855)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_CLEAR_NOTIFS");
      this.e.putExtra("iconResName", "ic_clear");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class k
    extends AppPickerPref.a
  {
    k()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820588);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230860)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_COPY");
      this.e.putExtra("iconResName", "ic_copy");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class l
    extends AppPickerPref.a
  {
    l()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820589);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230868)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.CUSTOM_KEY");
      this.e.putExtra("iconResName", "ic_edit");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class m
    extends AppPickerPref.a
  {
    m()
    {
      super();
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(AppPickerPref.j(AppPickerPref.this).getString(2131820589));
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(AppPickerPref.j(AppPickerPref.this).getString(2131820890));
      this.b = ((StringBuilder)localObject).toString();
      localObject = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230868)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), (Bitmap)localObject);
      this.e.setAction("homebutton.intent.action.GET_PRO");
      this.e.putExtra("iconResName", "ic_edit");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class n
    extends AppPickerPref.a
  {
    n()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820590);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230861)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.DEFAULT");
      this.e.putExtra("iconResName", "ic_default");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class o
    extends AppPickerPref.a
  {
    o()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820605);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230899)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.GOOGLE_NOW");
      this.e.putExtra("iconResName", "ic_now");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class p
    extends AppPickerPref.a
  {
    p()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820758);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230874)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.HEADSETHOOK");
      this.e.putExtra("iconResName", "ic_headset");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class q
    extends AppPickerPref.a
  {
    q()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820591);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230876)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.HOME");
      this.e.putExtra("iconResName", "ic_home");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class r
    extends AppPickerPref.a
  {
    r()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820592);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230878)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_IMMERSIVE");
      this.e.putExtra("iconResName", "ic_immersive");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class s
    extends AppPickerPref.a
  {
    s()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820593);
      try
      {
        Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230915)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
        this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
        this.e.setAction("homebutton.intent.action.ACTION_IN_APP_SEARCH");
        this.e.putExtra("iconResName", "ic_search");
        this.e.putExtra("prefLabel", this.b);
        return;
      }
      catch (NullPointerException this$1)
      {
        for (;;) {}
      }
    }
  }
  
  class t
    extends AppPickerPref.a
  {
    t()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820596);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230883)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.INPUT_METHOD");
      this.e.putExtra("iconResName", "ic_keyboard");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class u
    extends AppPickerPref.a
  {
    u()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820594);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230879)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.CUSTOM_INTENT");
      this.e.putExtra("iconResName", "ic_intent");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class v
    extends AppPickerPref.a
  {
    v()
    {
      super();
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(AppPickerPref.j(AppPickerPref.this).getString(2131820594));
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(AppPickerPref.j(AppPickerPref.this).getString(2131820890));
      this.b = ((StringBuilder)localObject).toString();
      localObject = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230879)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), (Bitmap)localObject);
      this.e.setAction("homebutton.intent.action.GET_PRO");
      this.e.putExtra("iconResName", "ic_intent");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class w
    extends AppPickerPref.a
  {
    w()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820595);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230881)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_INVERT");
      this.e.putExtra("iconResName", "ic_invert_colors");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class x
    extends AppPickerPref.a
  {
    x()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820597);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230928)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.KILL");
      this.e.putExtra("iconResName", "ic_uninstall");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class y
    extends AppPickerPref.a
  {
    y()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820598);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230885)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.LASTAPP");
      this.e.putExtra("iconResName", "ic_lastapp");
      this.e.putExtra("prefLabel", this.b);
    }
  }
  
  class z
    extends AppPickerPref.a
  {
    z()
    {
      super();
      this.b = AppPickerPref.j(AppPickerPref.this).getString(2131820599);
      Bitmap localBitmap = Bitmap.createScaledBitmap(AppPickerPref.a(a.a(AppPickerPref.h(AppPickerPref.this), 2131230887)), AppPickerPref.i(AppPickerPref.this), AppPickerPref.i(AppPickerPref.this), false);
      this.c = new BitmapDrawable(AppPickerPref.j(AppPickerPref.this), localBitmap);
      this.e.setAction("homebutton.intent.action.ACTION_LINK");
      this.e.putExtra("iconResName", "ic_link");
      this.e.putExtra("prefLabel", this.b);
    }
  }
}
