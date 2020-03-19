package org.codein.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.b;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import org.codein.app.task.SendAppTask;
import org.test.flashtest.customview.CircleButton;
import org.test.flashtest.util.CommonTask;
import org.test.flashtest.util.ac;
import org.test.flashtest.util.ad;
import org.test.flashtest.util.af;
import org.test.flashtest.util.j;
import org.test.flashtest.util.y;

public class HiddenAppFindActivity
  extends BaseApplicationActivity
  implements SwipeRefreshLayout.b, View.OnClickListener
{
  static Method a;
  private int A = 0;
  private f B;
  private Vector<Integer> C;
  private int D = org.test.flashtest.a.b.a;
  private int E = -7114533;
  private String F = "";
  private boolean G = false;
  private boolean H = false;
  private boolean I = false;
  private boolean J = false;
  private boolean K = false;
  private int L = 0;
  private int M = 0;
  CompoundButton.OnCheckedChangeListener b = new CompoundButton.OnCheckedChangeListener()
  {
    public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
    {
      ((HiddenAppFindActivity.a)HiddenAppFindActivity.k(HiddenAppFindActivity.this).getItemAtPosition(((Integer)paramAnonymousCompoundButton.getTag()).intValue())).m = paramAnonymousBoolean;
      int i = HiddenAppFindActivity.this.d.b();
      if (i > 0) {
        if (HiddenAppFindActivity.this.e == null) {
          HiddenAppFindActivity.this.d();
        }
      }
      for (;;)
      {
        HiddenAppFindActivity.this.a(i);
        HiddenAppFindActivity.this.d.notifyDataSetChanged();
        return;
        if (HiddenAppFindActivity.this.e != null) {
          HiddenAppFindActivity.this.e();
        }
      }
    }
  };
  Handler c = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      boolean bool = true;
      Object localObject1;
      Object localObject2;
      switch (paramAnonymousMessage.what)
      {
      default: 
      case 201: 
      case 202: 
      case 203: 
      case 2: 
        do
        {
          do
          {
            return;
          } while (HiddenAppFindActivity.l(HiddenAppFindActivity.this) == null);
          HiddenAppFindActivity.l(HiddenAppFindActivity.this).setMessage(HiddenAppFindActivity.this.getString(2131559011, new Object[] { paramAnonymousMessage.obj }));
          HiddenAppFindActivity.l(HiddenAppFindActivity.this).setProgress(HiddenAppFindActivity.l(HiddenAppFindActivity.this).getProgress() + 1);
          return;
          if ((paramAnonymousMessage.arg1 == 0) && (HiddenAppFindActivity.l(HiddenAppFindActivity.this) != null))
          {
            HiddenAppFindActivity.l(HiddenAppFindActivity.this).dismiss();
            HiddenAppFindActivity.a(HiddenAppFindActivity.this, null);
          }
          c.a(HiddenAppFindActivity.this, HiddenAppFindActivity.this.getString(2131558834, new Object[] { paramAnonymousMessage.obj }));
          return;
          localObject1 = (List)paramAnonymousMessage.obj;
          if (HiddenAppFindActivity.l(HiddenAppFindActivity.this) != null)
          {
            localObject2 = HiddenAppFindActivity.l(HiddenAppFindActivity.this);
            if (paramAnonymousMessage.arg2 > 0)
            {
              localObject1 = HiddenAppFindActivity.this.getString(2131559008, new Object[] { Integer.valueOf(paramAnonymousMessage.arg1), Integer.valueOf(paramAnonymousMessage.arg2) });
              ((ProgressDialog)localObject2).setMessage((CharSequence)localObject1);
              HiddenAppFindActivity.l(HiddenAppFindActivity.this).setProgress(HiddenAppFindActivity.l(HiddenAppFindActivity.this).getMax());
              HiddenAppFindActivity.l(HiddenAppFindActivity.this).dismiss();
              HiddenAppFindActivity.a(HiddenAppFindActivity.this, null);
            }
          }
          else
          {
            localObject1 = HiddenAppFindActivity.this;
            if (paramAnonymousMessage.arg2 <= 0) {
              break label414;
            }
          }
          for (paramAnonymousMessage = HiddenAppFindActivity.this.getString(2131559010, new Object[] { Integer.valueOf(paramAnonymousMessage.arg1), c.a(HiddenAppFindActivity.this, "app_export_dir", "/sdcard/backups/"), Integer.valueOf(paramAnonymousMessage.arg2) });; paramAnonymousMessage = HiddenAppFindActivity.this.getString(2131559009, new Object[] { Integer.valueOf(paramAnonymousMessage.arg1), c.a(HiddenAppFindActivity.this, "app_export_dir", "/sdcard/backups/") }))
          {
            c.a((Context)localObject1, paramAnonymousMessage);
            HiddenAppFindActivity.this.a(false);
            return;
            localObject1 = HiddenAppFindActivity.this.getString(2131559007, new Object[] { Integer.valueOf(paramAnonymousMessage.arg1) });
            break;
          }
        } while (HiddenAppFindActivity.l(HiddenAppFindActivity.this) == null);
        HiddenAppFindActivity.l(HiddenAppFindActivity.this).dismiss();
        HiddenAppFindActivity.a(HiddenAppFindActivity.this, null);
        return;
      case 5: 
        c.a(HiddenAppFindActivity.this, (String)paramAnonymousMessage.obj);
        return;
      case 3: 
        label414:
        sendEmptyMessage(2);
        localObject1 = (String)paramAnonymousMessage.obj;
        localObject2 = HiddenAppFindActivity.this;
        if (paramAnonymousMessage.arg2 == 1) {}
        for (;;)
        {
          c.a((String)localObject1, "Android Applications - ", (Activity)localObject2, bool);
          return;
          bool = false;
        }
      }
      sendEmptyMessage(2);
      c.a(this, HiddenAppFindActivity.this, (String)paramAnonymousMessage.obj, paramAnonymousMessage.arg1, "android_applications");
    }
  };
  private SwipeRefreshLayout f;
  private ListView g;
  private Button h;
  private Button i;
  private Button j;
  private ProgressDialog k;
  private Toolbar l;
  private CircleButton m;
  private ImageButton o;
  private ViewGroup p;
  private ImageView q;
  private EditText r;
  private ImageView s;
  private ImageButton t;
  private ImageButton u;
  private ImageButton v;
  private e w;
  private d x;
  private SendAppTask y;
  private boolean z = false;
  
  static
  {
    try
    {
      a = PackageManager.class.getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
      return;
    }
    catch (Exception localException)
    {
      Log.e(HiddenAppFindActivity.class.getName(), localException.getLocalizedMessage(), localException);
    }
  }
  
  public HiddenAppFindActivity() {}
  
  private String a(ApplicationInfo paramApplicationInfo)
  {
    localStringBuilder = new StringBuilder();
    try
    {
      localPackageManager = getPackageManager();
      paramApplicationInfo = localPackageManager.getPackageArchiveInfo(paramApplicationInfo.publicSourceDir, 20480).requestedPermissions;
      if (paramApplicationInfo == null) {
        break label120;
      }
      n = 0;
    }
    catch (Exception paramApplicationInfo)
    {
      PackageManager localPackageManager;
      int i1;
      if (!org.test.flashtest.a.d.a().af) {
        break label120;
      }
      paramApplicationInfo.printStackTrace();
      return localStringBuilder.toString();
    }
    catch (NoSuchFieldError paramApplicationInfo)
    {
      for (;;)
      {
        if (org.test.flashtest.a.d.a().af) {
          paramApplicationInfo.printStackTrace();
        }
      }
    }
    catch (NoSuchMethodError paramApplicationInfo)
    {
      for (;;)
      {
        if (org.test.flashtest.a.d.a().af) {
          paramApplicationInfo.printStackTrace();
        }
      }
    }
    catch (OutOfMemoryError paramApplicationInfo)
    {
      for (;;)
      {
        int n;
        if (org.test.flashtest.a.d.a().af) {
          paramApplicationInfo.printStackTrace();
        }
        j.b();
        continue;
        n += 1;
      }
    }
    i1 = paramApplicationInfo.length;
    if (n < i1) {
      try
      {
        localStringBuilder.append((String)localPackageManager.getPermissionInfo(paramApplicationInfo[n], 0).loadLabel(localPackageManager));
        if (n < paramApplicationInfo.length - 1) {
          localStringBuilder.append("\n");
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
          localStringBuilder.append(paramApplicationInfo[n]);
        }
      }
    }
  }
  
  static String a(String paramString)
  {
    String str;
    if (paramString == null) {
      str = null;
    }
    int n;
    do
    {
      return str;
      n = paramString.lastIndexOf('/');
      str = paramString;
    } while (n == -1);
    return paramString.substring(n + 1);
  }
  
  private static List<a> a(ListView paramListView)
  {
    int i1 = paramListView.getCount();
    ArrayList localArrayList = new ArrayList();
    int n = 0;
    while (n < i1)
    {
      a localA = (a)paramListView.getItemAtPosition(n);
      if (localA.m) {
        localArrayList.add(localA);
      }
      n += 1;
    }
    return localArrayList;
  }
  
  private void a(Context paramContext, File paramFile, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramBoolean) {}
    StringBuilder localStringBuilder;
    for (String str = paramContext.getString(2131559793);; str = paramContext.getString(2131559792))
    {
      localStringBuilder = new StringBuilder(paramContext.getString(2131559595) + "\n");
      if ((paramFile != null) && (paramFile.isFile()))
      {
        localStringBuilder.append(paramFile.getName() + "\n");
        localArrayList.add(paramFile);
      }
      if (localArrayList.size() != 0) {
        break;
      }
      Toast.makeText(paramContext, paramContext.getString(2131559577), 0).show();
      return;
    }
    new org.test.flashtest.browser.dialog.d(paramContext, localArrayList, paramBoolean).a(str, localStringBuilder.toString());
  }
  
  private void a(EditText paramEditText)
  {
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
  }
  
  static void a(File paramFile1, File paramFile2)
  {
    paramFile1 = new BufferedInputStream(new FileInputStream(paramFile1), 32768);
    paramFile2 = new BufferedOutputStream(new FileOutputStream(paramFile2), 32768);
    byte[] arrayOfByte = new byte['က'];
    for (;;)
    {
      int n = paramFile1.read(arrayOfByte);
      if (n == -1) {
        break;
      }
      paramFile2.write(arrayOfByte, 0, n);
    }
    paramFile1.close();
    paramFile2.close();
  }
  
  private void a(String paramString, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        if ((this.B != null) && (!this.B.b().equals(paramString))) {
          this.z = false;
        }
        if (!this.z)
        {
          if (this.B != null) {
            this.B.a();
          }
          this.F = paramString;
          if (paramString.length() <= 0) {
            break;
          }
          this.C.clear();
          this.d.notifyDataSetChanged();
          this.B = new f(paramString);
          this.B.startTask(new String[] { paramString });
          return;
        }
        if (!paramBoolean) {
          break label207;
        }
        if (this.A + 1 >= this.C.size())
        {
          this.A = 0;
          if ((this.C.size() <= 0) || (this.C.size() <= this.A)) {
            break;
          }
          this.d.notifyDataSetChanged();
          this.g.setSelection(((Integer)this.C.get(this.A)).intValue());
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        this.z = false;
        return;
      }
      this.A += 1;
      continue;
      label207:
      if (this.A - 1 <= 0) {
        this.A = (this.C.size() - 1);
      } else {
        this.A -= 1;
      }
    }
  }
  
  private void b(String paramString)
  {
    try
    {
      if (this.B != null) {
        this.B.a();
      }
      this.z = false;
      this.F = paramString;
      this.C.clear();
      this.d.notifyDataSetChanged();
      if (paramString.length() > 0)
      {
        this.B = new f(paramString);
        this.B.startTask(new String[] { paramString });
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private void f()
  {
    this.l = ((Toolbar)findViewById(2131232338));
    setSupportActionBar(this.l);
    x();
    this.f = ((SwipeRefreshLayout)findViewById(2131232016));
    this.g = ((ListView)findViewById(2131230812));
    this.h = ((Button)findViewById(2131230903));
    this.h.setOnClickListener(this);
    this.i = ((Button)findViewById(2131230909));
    this.i.setOnClickListener(this);
    this.j = ((Button)findViewById(2131230900));
    this.j.setOnClickListener(this);
    this.o = ((ImageButton)findViewById(2131232134));
    this.o.setOnClickListener(this);
    this.p = ((ViewGroup)findViewById(2131232132));
    this.q = ((ImageView)findViewById(2131232129));
    this.r = ((EditText)findViewById(2131231228));
    this.s = ((ImageView)findViewById(2131231315));
    this.s.setOnClickListener(this);
    this.t = ((ImageButton)findViewById(2131230894));
    this.t.setOnClickListener(this);
    this.u = ((ImageButton)findViewById(2131230891));
    this.u.setOnClickListener(this);
    this.v = ((ImageButton)findViewById(2131230893));
    this.v.setOnClickListener(this);
    this.m = ((CircleButton)findViewById(2131231263));
    this.m.setOnClickListener(this);
    this.f.setOnRefreshListener(this);
    g();
    h();
  }
  
  private void g()
  {
    this.g.setFastScrollEnabled(true);
    this.g.setDrawSelectorOnTop(true);
    ad.a(this.g, this);
    registerForContextMenu(this.g);
    this.g.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        boolean bool = false;
        paramAnonymousAdapterView = (HiddenAppFindActivity.a)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt);
        if (paramAnonymousAdapterView != null)
        {
          if (HiddenAppFindActivity.this.e != null)
          {
            if (!paramAnonymousAdapterView.m) {
              bool = true;
            }
            paramAnonymousAdapterView.m = bool;
            HiddenAppFindActivity.this.d.notifyDataSetChanged();
            paramAnonymousInt = HiddenAppFindActivity.this.d.b();
            HiddenAppFindActivity.this.a(paramAnonymousInt);
          }
        }
        else {
          return;
        }
        HiddenAppFindActivity.this.a(paramAnonymousAdapterView, 0);
      }
    });
    this.g.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        try
        {
          int j = paramAnonymousMotionEvent.getAction();
          int i = (int)paramAnonymousMotionEvent.getX();
          int k = (int)paramAnonymousMotionEvent.getY();
          switch (j & 0xFF)
          {
          case 0: 
          case 1: 
          case 6: 
            paramAnonymousMotionEvent = new Rect();
            paramAnonymousView.getDrawingRect(paramAnonymousMotionEvent);
            j = paramAnonymousMotionEvent.width();
            if (i > j - 100) {
              return true;
            }
            break;
          }
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
          return false;
        }
        return false;
      }
    });
    this.d = new BaseApplicationActivity.a(this, 2131361833)
    {
      void a()
      {
        int i = 0;
        while (i < getCount())
        {
          ((HiddenAppFindActivity.a)getItem(i)).m = false;
          i += 1;
        }
        notifyDataSetChanged();
      }
      
      int b()
      {
        int i = 0;
        int k;
        for (int j = 0; i < getCount(); j = k)
        {
          k = j;
          if (((HiddenAppFindActivity.a)getItem(i)).m) {
            k = j + 1;
          }
          i += 1;
        }
        return j;
      }
      
      public View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
      {
        if (paramAnonymousView == null)
        {
          paramAnonymousView = HiddenAppFindActivity.this.getLayoutInflater().inflate(2131361833, paramAnonymousViewGroup, false);
          paramAnonymousViewGroup = new HiddenAppFindActivity.b(HiddenAppFindActivity.this);
          paramAnonymousViewGroup.a = ((TextView)paramAnonymousView.findViewById(2131230820));
          paramAnonymousViewGroup.c = ((TextView)paramAnonymousView.findViewById(2131230826));
          paramAnonymousViewGroup.b = ((TextView)paramAnonymousView.findViewById(2131230823));
          paramAnonymousViewGroup.d = ((TextView)paramAnonymousView.findViewById(2131230824));
          paramAnonymousViewGroup.e = ((ImageView)paramAnonymousView.findViewById(2131231466));
          paramAnonymousViewGroup.f = ((CheckBox)paramAnonymousView.findViewById(2131231015));
          paramAnonymousViewGroup.g = ((ImageView)paramAnonymousView.findViewById(2131231467));
          paramAnonymousView.setTag(paramAnonymousViewGroup);
        }
        while (paramAnonymousInt >= getCount())
        {
          return paramAnonymousView;
          paramAnonymousViewGroup = (HiddenAppFindActivity.b)paramAnonymousView.getTag();
        }
        HiddenAppFindActivity.a localA = (HiddenAppFindActivity.a)getItem(paramAnonymousInt);
        Object localObject;
        int i;
        if (localA.b != null)
        {
          localObject = localA.b.toString();
          if (!HiddenAppFindActivity.a(HiddenAppFindActivity.this).contains(Integer.valueOf(paramAnonymousInt))) {
            break label578;
          }
          paramAnonymousView.setBackgroundColor(HiddenAppFindActivity.b(HiddenAppFindActivity.this));
          if (!y.b(HiddenAppFindActivity.c(HiddenAppFindActivity.this))) {
            break label603;
          }
          i = ((String)localObject).toLowerCase().indexOf(HiddenAppFindActivity.c(HiddenAppFindActivity.this));
          label236:
          if (i < 0) {
            break label609;
          }
          localObject = new SpannableStringBuilder((CharSequence)localObject);
          ((SpannableStringBuilder)localObject).setSpan(new ForegroundColorSpan(HiddenAppFindActivity.d(HiddenAppFindActivity.this)), i, HiddenAppFindActivity.c(HiddenAppFindActivity.this).length() + i, 33);
          paramAnonymousViewGroup.a.setText((CharSequence)localObject);
          label297:
          if (!HiddenAppFindActivity.e(HiddenAppFindActivity.this)) {
            break label657;
          }
          switch (localA.j)
          {
          default: 
            paramAnonymousViewGroup.a.setTextColor(-16777216);
            label349:
            paramAnonymousViewGroup.c.setText(localA.c);
            if (HiddenAppFindActivity.f(HiddenAppFindActivity.this))
            {
              paramAnonymousViewGroup.b.setVisibility(0);
              if (localA.f != null)
              {
                paramAnonymousViewGroup.b.setText(localA.f);
                label399:
                if (!HiddenAppFindActivity.g(HiddenAppFindActivity.this)) {
                  break label706;
                }
                paramAnonymousViewGroup.d.setVisibility(0);
                if (localA.a.sourceDir == null) {
                  break label693;
                }
                localObject = new File(localA.a.sourceDir);
                paramAnonymousViewGroup.d.setText(DateUtils.formatDateTime(HiddenAppFindActivity.this, ((File)localObject).lastModified(), 21));
                label466:
                if (!HiddenAppFindActivity.h(HiddenAppFindActivity.this)) {
                  break label765;
                }
                paramAnonymousViewGroup.e.setVisibility(0);
                if (localA.e == null) {
                  break label718;
                }
                paramAnonymousViewGroup.e.setImageDrawable(localA.e);
                label504:
                paramAnonymousViewGroup.f.setTag(Integer.valueOf(paramAnonymousInt));
                paramAnonymousViewGroup.f.setChecked(localA.m);
                paramAnonymousViewGroup.f.setOnCheckedChangeListener(HiddenAppFindActivity.this.b);
                paramAnonymousViewGroup = paramAnonymousView.findViewById(2131231467);
                if (!localA.l) {
                  break label777;
                }
              }
            }
            break;
          }
        }
        label578:
        label603:
        label609:
        label657:
        label693:
        label706:
        label718:
        label765:
        label777:
        for (paramAnonymousInt = 0;; paramAnonymousInt = 8)
        {
          paramAnonymousViewGroup.setVisibility(paramAnonymousInt);
          return paramAnonymousView;
          localObject = localA.a.packageName;
          break;
          if (localA.m)
          {
            paramAnonymousView.setBackgroundColor(-2134061876);
            i = -1;
            break label236;
          }
          paramAnonymousView.setBackgroundColor(0);
          i = -1;
          break label236;
          paramAnonymousViewGroup.a.setText((CharSequence)localObject);
          break label297;
          paramAnonymousViewGroup.a.setTextColor(-13653810);
          break label349;
          paramAnonymousViewGroup.a.setTextColor(-16729344);
          break label349;
          paramAnonymousViewGroup.a.setTextColor(-949315);
          break label349;
          paramAnonymousViewGroup.a.setTextColor(-1);
          break label349;
          paramAnonymousViewGroup.b.setText(2131558688);
          break label399;
          paramAnonymousViewGroup.b.setVisibility(8);
          break label399;
          paramAnonymousViewGroup.d.setText(2131560329);
          break label466;
          paramAnonymousViewGroup.d.setVisibility(8);
          break label466;
          try
          {
            paramAnonymousViewGroup.e.setImageDrawable(HiddenAppFindActivity.this.getPackageManager().getDefaultActivityIcon());
          }
          catch (Exception localException)
          {
            paramAnonymousViewGroup.e.setImageDrawable(null);
            Log.e(HiddenAppFindActivity.class.getName(), localException.getLocalizedMessage());
          }
          break label504;
          paramAnonymousViewGroup.e.setVisibility(8);
          break label504;
        }
      }
    };
    this.g.setAdapter(this.d);
  }
  
  private void h()
  {
    this.r.setImeOptions(3);
    this.r.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 3)
        {
          HiddenAppFindActivity.i(HiddenAppFindActivity.this).performClick();
          return true;
        }
        return false;
      }
    });
    this.r.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        paramAnonymousEditable = paramAnonymousEditable.toString();
        if (!HiddenAppFindActivity.c(HiddenAppFindActivity.this).equals(paramAnonymousEditable)) {
          HiddenAppFindActivity.a(HiddenAppFindActivity.this, paramAnonymousEditable);
        }
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
  }
  
  private static boolean i()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }
  
  private void j()
  {
    Object localObject = a(this.g);
    if ((localObject == null) || (((List)localObject).size() == 0))
    {
      c.a(this, 2131559654);
      return;
    }
    localObject = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        int j = this.a.size();
        int i = 0;
        paramAnonymousInt = 0;
        if (i < j)
        {
          paramAnonymousDialogInterface = ((HiddenAppFindActivity.a)this.a.get(i)).a;
          paramAnonymousDialogInterface = new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramAnonymousDialogInterface.packageName));
          if (paramAnonymousInt != 0) {
            break label148;
          }
          if (HiddenAppFindActivity.this.getPackageManager().queryIntentActivities(paramAnonymousDialogInterface, 0).size() > 0) {
            paramAnonymousInt = 1;
          }
        }
        label148:
        for (;;)
        {
          if (paramAnonymousInt != 0) {
            HiddenAppFindActivity.this.startActivity(paramAnonymousDialogInterface);
          }
          i += 1;
          break;
          paramAnonymousInt = 0;
          continue;
          if (paramAnonymousInt == 0)
          {
            c.a(HiddenAppFindActivity.this, 2131560322);
            Log.d(ApplicationManager.class.getName(), "No activity found to handle the uninstall request.");
          }
          return;
        }
      }
    };
    new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131560356).setMessage(2131560323).setPositiveButton(2131559687, (DialogInterface.OnClickListener)localObject).setNegativeButton(2131558641, null).create().show();
  }
  
  private void k()
  {
    Object localObject = a(this.g);
    if ((localObject == null) || (((List)localObject).size() == 0))
    {
      c.a(this, 2131559654);
      return;
    }
    final boolean[] arrayOfBoolean = new boolean[4];
    boolean[] tmp34_33 = arrayOfBoolean;
    tmp34_33[0] = 1;
    boolean[] tmp40_34 = tmp34_33;
    tmp40_34[1] = 1;
    boolean[] tmp46_40 = tmp40_34;
    tmp46_40[2] = 1;
    boolean[] tmp52_46 = tmp46_40;
    tmp52_46[3] = 1;
    tmp52_46;
    DialogInterface.OnMultiChoiceClickListener local3 = new DialogInterface.OnMultiChoiceClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        arrayOfBoolean[paramAnonymousInt] = paramAnonymousBoolean;
      }
    };
    localObject = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface = HiddenAppFindActivity.this;
            boolean[] arrayOfBoolean = HiddenAppFindActivity.4.this.a;
            List localList = HiddenAppFindActivity.4.this.b;
            if (paramAnonymous2Int == 0) {}
            for (boolean bool = true;; bool = false)
            {
              paramAnonymous2DialogInterface.a(arrayOfBoolean, localList, bool);
              return;
            }
          }
        };
        new org.test.flashtest.customview.roundcorner.a(HiddenAppFindActivity.this).setTitle(2131558516).setItems(new String[] { HiddenAppFindActivity.this.getString(2131558832), HiddenAppFindActivity.this.getString(2131560058) }, paramAnonymousDialogInterface).create().show();
      }
    };
    new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131559264).setMultiChoiceItems(new CharSequence[] { getString(2131560349), getString(2131560213), getString(2131559766), getString(2131559356) }, arrayOfBoolean, local3).setPositiveButton(2131559687, (DialogInterface.OnClickListener)localObject).setNegativeButton(2131558641, null).create().show();
  }
  
  private void l()
  {
    e();
    if (this.k != null)
    {
      this.k.dismiss();
      this.k = null;
    }
    if (this.w != null) {
      this.w.a();
    }
    if (this.x != null) {
      this.x.a();
    }
    if (this.B != null) {
      this.B.a();
    }
    this.C.clear();
    this.z = false;
    if (this.d != null)
    {
      BaseApplicationActivity.a localA = this.d;
      localA.clear();
      localA.notifyDataSetChanged();
    }
    this.w = new e();
    this.w.startTask(new Void[] { (Void)null });
  }
  
  String a(boolean[] paramArrayOfBoolean, List<a> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i1 = paramList.size();
    int n = 0;
    if (n < i1)
    {
      a localA = (a)paramList.get(n);
      if (n > 0) {
        localStringBuilder.append('\n');
      }
      if (localA.b == null) {}
      for (Object localObject = localA.a.packageName;; localObject = localA.b)
      {
        localStringBuilder.append((CharSequence)localObject);
        if (paramArrayOfBoolean[0] != 0) {
          localStringBuilder.append(", " + localA.c);
        }
        if (paramArrayOfBoolean[1] != 0) {
          localStringBuilder.append(", SDK " + c.a(this, localA.a));
        }
        if (paramArrayOfBoolean[2] != 0) {
          localStringBuilder.append(", " + localA.a.packageName);
        }
        if (paramArrayOfBoolean[3] != 0) {
          localStringBuilder.append(", http://market.android.com/search?q=pname:" + localA.a.packageName);
        }
        n += 1;
        break;
      }
    }
    if (localStringBuilder.length() > 0) {
      return localStringBuilder.toString();
    }
    return null;
  }
  
  public void a()
  {
    l();
  }
  
  protected void a(Menu paramMenu)
  {
    paramMenu.add(0, 14, 0, 2131560049);
    paramMenu.add(0, 15, 0, 2131558931);
    paramMenu.add(0, 1, 0, 2131560321);
  }
  
  public void a(EditText paramEditText, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramEditText.requestFocus();
    }
    ((InputMethodManager)getSystemService("input_method")).showSoftInput(paramEditText, 1);
  }
  
  public void a(File paramFile, String paramString)
  {
    if ((paramFile.exists()) && (paramFile.canRead()) && (!paramFile.getAbsolutePath().contains("/data/app-private")))
    {
      if (this.y != null) {
        this.y.a();
      }
      File localFile = new File(org.test.flashtest.pref.b.b);
      if (!localFile.exists()) {
        localFile.mkdirs();
      }
      this.y = new SendAppTask(this, paramFile, new File(localFile, paramString + ".apk"), new org.test.flashtest.sdcardcleaner.b.c()
      {
        public void a(File paramAnonymousFile)
        {
          if (HiddenAppFindActivity.this.isFinishing()) {
            return;
          }
          HiddenAppFindActivity.a(HiddenAppFindActivity.this, HiddenAppFindActivity.this, paramAnonymousFile, true);
        }
      });
      this.y.startTask(new Void[] { (Void)null });
      return;
    }
    Toast.makeText(this, getString(2131559540), 1).show();
  }
  
  void a(final List<a> paramList, final int paramInt)
  {
    if ((paramList == null) || (paramList.isEmpty()))
    {
      c.a(this, 2131559654);
      return;
    }
    if (this.k == null) {
      this.k = new ProgressDialog(this);
    }
    this.k.setMessage(getResources().getString(2131560125));
    this.k.setIndeterminate(false);
    this.k.setProgressStyle(1);
    this.k.setMax(paramList.size());
    this.k.show();
    new Thread(new Runnable()
    {
      public void run()
      {
        Object localObject1 = new File(c.a(HiddenAppFindActivity.this, "app_export_dir", "/sdcard/backups/"));
        if ((!((File)localObject1).exists()) && (!((File)localObject1).mkdirs()))
        {
          HiddenAppFindActivity.this.c.sendMessage(Message.obtain(HiddenAppFindActivity.this.c, 202, 0, 0, HiddenAppFindActivity.this.getString(2131558967, new Object[] { ((File)localObject1).getAbsolutePath() })));
          return;
        }
        File localFile1 = new File((File)localObject1, "system/");
        if ((!localFile1.exists()) && (!localFile1.mkdirs()))
        {
          HiddenAppFindActivity.this.c.sendMessage(Message.obtain(HiddenAppFindActivity.this.c, 202, 0, 0, HiddenAppFindActivity.this.getString(2131558967, new Object[] { localFile1.getAbsolutePath() })));
          return;
        }
        File localFile2 = new File((File)localObject1, "user/");
        if ((!localFile2.exists()) && (!localFile2.mkdirs()))
        {
          HiddenAppFindActivity.this.c.sendMessage(Message.obtain(HiddenAppFindActivity.this.c, 202, 0, 0, HiddenAppFindActivity.this.getString(2131558967, new Object[] { localFile2.getAbsolutePath() })));
          return;
        }
        int i = 0;
        int j = 0;
        int i1 = paramList.size();
        int k = 0;
        ApplicationInfo localApplicationInfo;
        File localFile3;
        int m;
        if (k < i1)
        {
          localApplicationInfo = ((HiddenAppFindActivity.a)paramList.get(k)).a;
          localObject1 = localApplicationInfo.sourceDir;
          if (localObject1 == null) {
            break label895;
          }
          localFile3 = new File((String)localObject1);
          if ((((String)localObject1).contains("/data/app-private")) || (!localFile3.canRead()))
          {
            m = i + 1;
            i = j;
            j = m;
          }
        }
        for (;;)
        {
          m = k + 1;
          k = j;
          j = i;
          i = k;
          k = m;
          break;
          if ((HiddenAppFindActivity.a((String)localObject1) != null) && (localApplicationInfo.packageName != null))
          {
            if (((HiddenAppFindActivity.a)paramList.get(k)).b != null)
            {
              localObject1 = ((HiddenAppFindActivity.a)paramList.get(k)).b.toString();
              label405:
              m = paramInt;
              if (!TextUtils.isEmpty((CharSequence)localObject1)) {
                break label557;
              }
              m = 0;
              localObject1 = null;
              switch (m)
              {
              default: 
                label425:
                localObject1 = localApplicationInfo.packageName;
              }
            }
            label455:
            label557:
            label892:
            for (;;)
            {
              String str = (String)localObject1 + ".apk";
              if ((localApplicationInfo.flags & 0x1) != 0) {}
              Object localObject2;
              for (localObject1 = localFile1;; localObject2 = localFile2)
              {
                localObject1 = new File((File)localObject1, str);
                HiddenAppFindActivity.this.c.sendMessage(Message.obtain(HiddenAppFindActivity.this.c, 201, str));
                try
                {
                  HiddenAppFindActivity.a(localFile3, (File)localObject1);
                  m = j + 1;
                  j = i;
                  i = m;
                }
                catch (Exception localException)
                {
                  int n;
                  Log.e(ApplicationManager.class.getName(), localException.getLocalizedMessage(), localException);
                  HiddenAppFindActivity.this.c.sendMessage(Message.obtain(HiddenAppFindActivity.this.c, 202, 1, 0, localException.getLocalizedMessage()));
                  m = i;
                  i = j;
                  j = m;
                }
                localObject1 = "";
                break label405;
                localObject1 = new StringBuilder((String)localObject1);
                break label425;
                m = 0;
                if (m < ((StringBuilder)localObject1).length())
                {
                  n = 0;
                  for (;;)
                  {
                    if (n < "/:*?<>|".length())
                    {
                      if (((StringBuilder)localObject1).charAt(m) == "/:*?<>|".charAt(n)) {
                        ((StringBuilder)localObject1).setCharAt(m, '_');
                      }
                    }
                    else
                    {
                      m += 1;
                      break;
                    }
                    n += 1;
                  }
                }
                localObject1 = ((StringBuilder)localObject1).toString();
                break label455;
                m = 0;
                if (m < ((StringBuilder)localObject1).length())
                {
                  n = 0;
                  for (;;)
                  {
                    if (n < "/:*?<>|".length())
                    {
                      if (((StringBuilder)localObject1).charAt(m) == "/:*?<>|".charAt(n)) {
                        ((StringBuilder)localObject1).setCharAt(m, '_');
                      }
                    }
                    else
                    {
                      m += 1;
                      break;
                    }
                    n += 1;
                  }
                }
                localObject1 = ((StringBuilder)localObject1).toString();
                if (TextUtils.isEmpty(((HiddenAppFindActivity.a)paramList.get(k)).d)) {
                  break label892;
                }
                localObject1 = (String)localObject1 + " v" + ((HiddenAppFindActivity.a)paramList.get(k)).d;
                break label455;
                break;
                HiddenAppFindActivity.this.c.sendMessage(Message.obtain(HiddenAppFindActivity.this.c, 203, j, i, paramList));
                return;
              }
            }
          }
          label895:
          m = i;
          i = j;
          j = m;
        }
      }
    }).start();
  }
  
  void a(final a paramA, int paramInt)
  {
    Object localObject1 = paramA.a.packageName;
    Object localObject2;
    label332:
    Object localObject3;
    switch (paramInt)
    {
    default: 
    case 0: 
    case 1: 
    case 2: 
      do
      {
        do
        {
          return;
          paramA = new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              paramAnonymousDialogInterface.dismiss();
              HiddenAppFindActivity.this.a(paramA, paramAnonymousInt + 1);
            }
          };
          new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131558516).setItems(new CharSequence[] { getString(2131559354), getString(2131559944), getString(2131560020), getString(2131558933), getString(2131560016), getString(2131559393) }, paramA).create().show();
          return;
          paramA = new Intent("android.intent.action.VIEW");
          paramA.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
          paramA.putExtra("com.android.settings.ApplicationPkgName", (String)localObject1);
          paramA.putExtra("pkg", (String)localObject1);
          if (getPackageManager().queryIntentActivities(paramA, 0).size() > 0)
          {
            startActivity(paramA);
            return;
          }
          paramA = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", (String)localObject1, null));
          if (getPackageManager().queryIntentActivities(paramA, 0).size() > 0)
          {
            startActivity(paramA);
            return;
          }
          Log.d(ApplicationManager.class.getName(), "Failed to resolve activity for InstalledAppDetails");
          return;
        } while (((String)localObject1).equals(getPackageName()));
        paramA = new Intent("android.intent.action.MAIN");
        paramA.addCategory("android.intent.category.LAUNCHER");
        localObject2 = getPackageManager().queryIntentActivities(paramA, 0);
      } while (localObject2 == null);
      int n = ((List)localObject2).size();
      paramInt = 0;
      if (paramInt < n)
      {
        localObject3 = (ResolveInfo)((List)localObject2).get(paramInt);
        if (((String)localObject1).equals(((ResolveInfo)localObject3).activityInfo.packageName))
        {
          paramA.setClassName(((ResolveInfo)localObject3).activityInfo.packageName, ((ResolveInfo)localObject3).activityInfo.name);
          paramA.addFlags(268435456).addFlags(67108864);
        }
      }
      break;
    }
    for (;;)
    {
      try
      {
        startActivity(paramA);
        paramInt = 1;
        if (paramInt != 0) {
          break label499;
        }
      }
      catch (Exception paramA)
      {
        try
        {
          paramA = getPackageManager().getLaunchIntentForPackage((String)localObject1);
          paramA.setFlags(268435456);
          startActivity(paramA);
          paramInt = 1;
          if (paramInt != 0) {
            break;
          }
          c.a(this, 2131559945);
          return;
        }
        catch (Exception paramA)
        {
          paramA.printStackTrace();
        }
        paramA = paramA;
        Log.e(ApplicationManager.class.getName(), "Cannot start activity: " + (String)localObject1, paramA);
        paramInt = 0;
        continue;
      }
      paramInt += 1;
      break label332;
      label499:
      continue;
      af.b(this, (String)localObject1);
      return;
      ApplicationInfo localApplicationInfo = paramA.a;
      String str;
      ArrayList localArrayList1;
      ArrayList localArrayList2;
      if (localApplicationInfo.sourceDir != null)
      {
        localObject1 = new File(localApplicationInfo.sourceDir);
        localObject2 = DateUtils.formatDateTime(this, ((File)localObject1).lastModified(), 21);
        localObject1 = Formatter.formatFileSize(this, ((File)localObject1).length());
        str = a(localApplicationInfo);
        localArrayList1 = new ArrayList();
        localArrayList2 = new ArrayList();
        localArrayList1.add(getString(2131559766));
        localArrayList2.add(localApplicationInfo.packageName);
        localArrayList1.add(getString(2131560349));
        if (paramA.d != null) {
          break label1163;
        }
        localObject3 = "";
        label635:
        localArrayList2.add(localObject3);
        localArrayList1.add(getString(2131560350));
        localArrayList2.add(String.valueOf(paramA.k));
        localArrayList1.add(getString(2131560213));
        localArrayList2.add(c.a(this, localApplicationInfo));
        localArrayList1.add(getString(2131560318));
        localArrayList2.add(String.valueOf(localApplicationInfo.uid));
        localArrayList1.add(getString(2131559068));
        localArrayList2.add(String.valueOf(localObject1));
        localArrayList1.add(getString(2131559903));
        localArrayList2.add(localApplicationInfo.publicSourceDir);
        localArrayList1.add(getString(2131560122));
        localArrayList2.add(localApplicationInfo.sourceDir);
        localArrayList1.add(getString(2131558863));
        localArrayList2.add(localApplicationInfo.dataDir);
        localArrayList1.add(getString(2131559275));
        localArrayList2.add(localObject2);
        localArrayList1.add(getString(2131559892));
        localArrayList2.add(localApplicationInfo.processName);
        localArrayList1.add(getString(2131558531));
        if (localApplicationInfo.className != null) {
          break label1175;
        }
        localObject1 = "";
        label887:
        localArrayList2.add(localObject1);
        localArrayList1.add(getString(2131560214));
        localArrayList2.add(localApplicationInfo.taskAffinity);
        localArrayList1.add(getString(2131559745));
        if (str != null) {
          break label1185;
        }
        localObject1 = "";
        label941:
        localArrayList2.add(localObject1);
        localArrayList1.add(getString(2131559085));
        localArrayList2.add(String.valueOf(localApplicationInfo.flags));
        localArrayList1.add(getString(2131558955));
        localArrayList2.add(String.valueOf(localApplicationInfo.enabled));
        localArrayList1.add(getString(2131559355));
        if (localApplicationInfo.manageSpaceActivityName != null) {
          break label1192;
        }
      }
      label1163:
      label1175:
      label1185:
      label1192:
      for (localObject1 = "";; localObject1 = localApplicationInfo.manageSpaceActivityName)
      {
        localArrayList2.add(localObject1);
        localObject2 = new File(localApplicationInfo.publicSourceDir);
        if (!((File)localObject2).exists()) {
          break label1379;
        }
        localObject3 = new HashSet();
        localObject1 = new ArrayList();
        af.a((File)localObject2, (HashSet)localObject3, (ArrayList)localObject1);
        localObject2 = new StringBuilder();
        localObject3 = ((HashSet)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          if (((StringBuilder)localObject2).length() > 0) {
            ((StringBuilder)localObject2).append(", ");
          }
          ((StringBuilder)localObject2).append((String)((Iterator)localObject3).next());
        }
        localObject2 = getString(2131560329);
        localObject1 = localObject2;
        break;
        localObject3 = (String)paramA.d;
        break label635;
        localObject1 = localApplicationInfo.className;
        break label887;
        localObject1 = str;
        break label941;
      }
      localObject3 = new StringBuilder();
      paramInt = 0;
      while (paramInt < ((ArrayList)localObject1).size())
      {
        ((StringBuilder)localObject3).append((String)((ArrayList)localObject1).get(paramInt));
        if (paramInt < ((ArrayList)localObject1).size() - 1) {
          ((StringBuilder)localObject3).append("\n");
        }
        paramInt += 1;
      }
      if (((StringBuilder)localObject2).length() > 0)
      {
        localObject2 = "(" + ((StringBuilder)localObject2).toString() + ")";
        localObject1 = localObject2;
        if (((StringBuilder)localObject3).length() > 0)
        {
          localObject1 = (String)localObject2 + "\n";
          localObject1 = (String)localObject1 + ((StringBuilder)localObject3).toString();
        }
        localArrayList1.add("Native libs");
        localArrayList2.add(localObject1);
      }
      label1379:
      localObject1 = new a();
      if (paramA.b == null) {}
      for (paramA = localApplicationInfo.packageName;; paramA = (String)paramA.b)
      {
        ((a)localObject1).a(this, paramA, localArrayList1, localArrayList2);
        return;
      }
      paramA = new Intent();
      paramA.setAction("android.intent.action.WEB_SEARCH");
      paramA.putExtra("query", (String)localObject1);
      try
      {
        startActivity(paramA);
        return;
      }
      catch (ActivityNotFoundException paramA)
      {
        ac.a(2131559569);
        paramA.printStackTrace();
        return;
      }
      localObject2 = getString(2131559596);
      if (paramA.b == null) {}
      for (localObject1 = paramA.a.packageName;; localObject1 = (String)paramA.b)
      {
        localObject1 = String.format((String)localObject2, new Object[] { localObject1 });
        new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131560058).setMessage((CharSequence)localObject1).setPositiveButton(2131559687, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface = new File(paramA.a.sourceDir);
            HiddenAppFindActivity.this.a(paramAnonymousDialogInterface, paramA.a.packageName);
          }
        }).setNegativeButton(2131558641, null).create().show();
        return;
      }
      paramInt = 0;
    }
  }
  
  void a(boolean paramBoolean)
  {
    int i1 = this.g.getCount();
    int n = 0;
    while (n < i1)
    {
      ((a)this.g.getItemAtPosition(n)).m = paramBoolean;
      n += 1;
    }
    this.d.notifyDataSetChanged();
    n = this.d.b();
    if (paramBoolean) {
      d();
    }
    for (;;)
    {
      a(n);
      return;
      e();
    }
  }
  
  void a(final boolean[] paramArrayOfBoolean, final List<a> paramList, final boolean paramBoolean)
  {
    if (this.k == null) {
      this.k = new ProgressDialog(this);
    }
    this.k.setMessage(getResources().getText(2131559341));
    this.k.setIndeterminate(true);
    this.k.show();
    new Thread(new Runnable()
    {
      public void run()
      {
        final String str = HiddenAppFindActivity.this.a(paramArrayOfBoolean, paramList);
        if (HiddenAppFindActivity.this.isFinishing()) {}
        while (!paramBoolean) {
          return;
        }
        HiddenAppFindActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (!HiddenAppFindActivity.this.isFinishing()) {
              c.a.a.a.a.a.a(str, HiddenAppFindActivity.this);
            }
          }
        });
        HiddenAppFindActivity.this.c.sendEmptyMessage(2);
        HiddenAppFindActivity.this.c.sendMessage(HiddenAppFindActivity.this.c.obtainMessage(5, HiddenAppFindActivity.this.getString(2131558831)));
      }
    }).start();
  }
  
  protected boolean a(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    case 14: 
      a(true);
      return true;
    case 15: 
      a(false);
      return true;
    }
    j();
    return true;
  }
  
  void b()
  {
    final List localList = a(this.g);
    if ((localList == null) || (localList.size() == 0))
    {
      c.a(this, 2131559654);
      return;
    }
    if (!i())
    {
      c.a(this, 2131558986);
      return;
    }
    final SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    int n = localSharedPreferences.getInt("export_type", 0);
    Object localObject = (ViewGroup)LayoutInflater.from(this).inflate(2131361831, null);
    org.test.flashtest.customview.roundcorner.a localA = new org.test.flashtest.customview.roundcorner.a(this);
    localA.setTitle(2131560356);
    localA.setMessage(getString(2131560358, new Object[] { c.a(this, "app_export_dir", "/sdcard/backups/") }));
    localA.setView((View)localObject);
    final String[] arrayOfString = getResources().getStringArray(2130837508);
    final TextView localTextView = (TextView)((ViewGroup)localObject).findViewById(2131232176);
    localObject = (Spinner)((ViewGroup)localObject).findViewById(2131232175);
    localTextView.setText(arrayOfString[0]);
    ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367048, arrayOfString);
    localArrayAdapter.setDropDownViewResource(17367049);
    ((Spinner)localObject).setAdapter(localArrayAdapter);
    ((Spinner)localObject).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if ((paramAnonymousInt >= 0) && (paramAnonymousInt < arrayOfString.length))
        {
          localObject = HiddenAppFindActivity.this.getPackageManager();
          paramAnonymousAdapterView = "org.joa.zipperplus7";
        }
        try
        {
          paramAnonymousView = HiddenAppFindActivity.this.getPackageName();
          paramAnonymousAdapterView = paramAnonymousView;
          localObject = ((PackageManager)localObject).getPackageInfo(paramAnonymousView, 0).versionName;
          paramAnonymousAdapterView = paramAnonymousView;
        }
        catch (PackageManager.NameNotFoundException paramAnonymousView)
        {
          for (;;)
          {
            paramAnonymousView.printStackTrace();
            localObject = "1.0";
            continue;
            paramAnonymousView = HiddenAppFindActivity.this.getString(2131558532);
            continue;
            paramAnonymousView = HiddenAppFindActivity.this.getString(2131558532) + " v" + (String)localObject;
          }
        }
        paramAnonymousView = paramAnonymousAdapterView;
        switch (paramAnonymousInt)
        {
        default: 
          paramAnonymousView = paramAnonymousAdapterView;
        case 0: 
          paramAnonymousAdapterView = "ex) " + paramAnonymousView + ".apk";
          localTextView.setText(paramAnonymousAdapterView);
          return;
        }
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    ((Spinner)localObject).setSelection(n);
    localA.setPositiveButton(2131559687, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousInt = this.a.getSelectedItemPosition();
        if (-1 != paramAnonymousInt)
        {
          paramAnonymousDialogInterface = localSharedPreferences.edit();
          paramAnonymousDialogInterface.putInt("export_type", paramAnonymousInt);
          paramAnonymousDialogInterface.commit();
        }
        switch (paramAnonymousInt)
        {
        default: 
          return;
        }
        HiddenAppFindActivity.this.a(localList, paramAnonymousInt);
      }
    });
    localA.setNegativeButton(2131558641, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localA.create().show();
  }
  
  @Deprecated
  void c()
  {
    View localView = findViewById(2131230817);
    if (localView.getVisibility() != 8)
    {
      localView.setVisibility(8);
      localView.startAnimation(AnimationUtils.loadAnimation(this, 2130771997));
    }
  }
  
  public void onBackPressed()
  {
    try
    {
      if (this.p.getVisibility() == 0)
      {
        this.v.performClick();
        return;
      }
      if (!e())
      {
        super.onBackPressed();
        return;
      }
    }
    catch (Exception localException)
    {
      if (org.test.flashtest.a.d.a().af) {
        localException.printStackTrace();
      }
      finish();
    }
  }
  
  public void onClick(View paramView)
  {
    if (this.h == paramView) {
      b();
    }
    do
    {
      return;
      if (this.i == paramView)
      {
        a(true);
        return;
      }
      if (this.j == paramView)
      {
        a(false);
        return;
      }
      if (this.o == paramView)
      {
        this.p.setVisibility(0);
        a(this.r, true);
        return;
      }
      if (this.v == paramView)
      {
        this.p.setVisibility(8);
        a(this.r);
        this.z = false;
        if (this.B != null) {
          this.B.a();
        }
        this.C.clear();
        this.F = "";
        this.r.setText("");
        this.d.notifyDataSetChanged();
        return;
      }
      if (this.t == paramView)
      {
        a(this.r.getEditableText().toString(), true);
        return;
      }
      if (this.u == paramView)
      {
        a(this.r.getEditableText().toString(), false);
        return;
      }
      if (this.s == paramView)
      {
        this.z = false;
        if (this.B != null) {
          this.B.a();
        }
        this.r.setText("");
        return;
      }
    } while (this.m != paramView);
    ad.a(this);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    af.a(this);
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    int n = ((AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo()).position;
    a localA;
    if ((n >= 0) && (n < this.g.getCount())) {
      localA = (a)this.g.getItemAtPosition(n);
    }
    switch (paramMenuItem.getItemId())
    {
    case 4: 
    case 5: 
    case 6: 
    case 8: 
    case 9: 
    default: 
      return false;
    case 10: 
      a(localA, 1);
      return true;
    case 2: 
      a(localA, 2);
      return true;
    case 3: 
      a(localA, 3);
      return true;
    case 7: 
      a(localA, 4);
      return true;
    case 11: 
      a(localA, 5);
      return true;
    case 12: 
      a(localA, 6);
      return true;
    }
    localA.m = true;
    d();
    this.d.notifyDataSetChanged();
    return true;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131362008);
    this.C = new Vector();
    f();
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    paramContextMenu.setHeaderTitle(2131558516);
    paramContextMenu.add(0, 13, 0, 2131559385);
    paramContextMenu.add(0, 10, 0, 2131559354);
    paramContextMenu.add(0, 2, 0, 2131559944);
    paramContextMenu.add(0, 3, 0, 2131560020);
    paramContextMenu.add(0, 7, 0, 2131558933);
    paramContextMenu.add(0, 11, 0, 2131560016);
    paramContextMenu.add(0, 12, 0, 2131559393);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    paramMenu.add(0, 103, 0, 2131559918).setIcon(17301581);
    return true;
  }
  
  protected void onDestroy()
  {
    if (this.k != null)
    {
      this.k.dismiss();
      this.k = null;
    }
    if (this.w != null) {
      this.w.a();
    }
    if (this.x != null) {
      this.x.a();
    }
    if (this.B != null) {
      this.B.a();
    }
    this.C.clear();
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 102)
    {
      k();
      return true;
    }
    if (paramMenuItem.getItemId() == 1)
    {
      j();
      return true;
    }
    if (paramMenuItem.getItemId() == 103)
    {
      this.L = 0;
      this.M = 0;
      l();
      return true;
    }
    return false;
  }
  
  protected void onPause()
  {
    int n = 0;
    super.onPause();
    View localView;
    if (this.g != null)
    {
      this.L = this.g.getFirstVisiblePosition();
      localView = this.g.getChildAt(0);
      if (localView != null) {
        break label43;
      }
    }
    for (;;)
    {
      this.M = n;
      return;
      label43:
      n = localView.getTop();
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    this.H = c.a(this, "show_backup_state");
    this.I = c.a(this, "show_size");
    this.J = c.a(this, "show_date");
    this.K = c.a(this, "show_icon");
  }
  
  protected void onStart()
  {
    super.onStart();
    if ((!this.G) && (!org.test.flashtest.pref.a.b(this, "pref_hidden_app_find_explain_nomore_see", false)))
    {
      final boolean[] arrayOfBoolean = new boolean[1];
      org.test.flashtest.browser.dialog.c.a(this, getString(2131559674), getString(2131559187), getString(2131559652), arrayOfBoolean, true, new org.test.flashtest.browser.b.a()
      {
        public void a(Boolean paramAnonymousBoolean)
        {
          if ((paramAnonymousBoolean != null) && (paramAnonymousBoolean.booleanValue()) && (arrayOfBoolean[0] != 0)) {
            org.test.flashtest.pref.a.a(HiddenAppFindActivity.this, "pref_hidden_app_find_explain_nomore_see", true);
          }
          HiddenAppFindActivity.j(HiddenAppFindActivity.this);
        }
      });
      this.G = true;
      return;
    }
    l();
  }
  
  protected void onStop()
  {
    if (this.y != null)
    {
      this.y.a();
      this.y = null;
    }
    super.onStop();
  }
  
  private static final class a
  {
    ApplicationInfo a;
    CharSequence b;
    CharSequence c;
    CharSequence d;
    Drawable e;
    String f;
    long g;
    long h;
    long i;
    int j;
    int k;
    boolean l;
    boolean m;
    
    a() {}
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof a)) {
        return false;
      }
      paramObject = (a)paramObject;
      return this.a.packageName.equals(paramObject.a.packageName);
    }
  }
  
  class b
  {
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    ImageView e;
    CheckBox f;
    ImageView g;
    
    b() {}
  }
  
  private static final class c
    extends IPackageStatsObserver.Stub
  {
    private CountDownLatch a;
    private WeakReference<Activity> b;
    private WeakReference<ArrayAdapter<HiddenAppFindActivity.a>> c;
    
    c(CountDownLatch paramCountDownLatch, Activity paramActivity, ArrayAdapter<HiddenAppFindActivity.a> paramArrayAdapter)
    {
      this.a = paramCountDownLatch;
      this.b = new WeakReference(paramActivity);
      this.c = new WeakReference(paramArrayAdapter);
    }
    
    private boolean a()
    {
      if ((this.b == null) || (this.b.get() == null) || (((Activity)this.b.get()).isFinishing())) {
        return true;
      }
      return (this.c == null) || (this.c.get() == null);
    }
    
    void a(String paramString, PackageManager paramPackageManager)
    {
      if (HiddenAppFindActivity.a != null) {}
      try
      {
        HiddenAppFindActivity.a.invoke(paramPackageManager, new Object[] { paramString, this });
        return;
      }
      catch (Exception paramString)
      {
        Log.e(HiddenAppFindActivity.class.getName(), paramString.getLocalizedMessage(), paramString);
      }
    }
    
    public void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean)
    {
      for (;;)
      {
        try
        {
          paramBoolean = a();
          if (paramBoolean) {
            return;
          }
          int i = 0;
          if (i < ((ArrayAdapter)this.c.get()).getCount())
          {
            localA = (HiddenAppFindActivity.a)((ArrayAdapter)this.c.get()).getItem(i);
            if ((!paramPackageStats.packageName.equals(localA.a.packageName)) || (localA != null)) {}
            synchronized ((ArrayAdapter)this.c.get())
            {
              if (a())
              {
                return;
                i += 1;
                continue;
              }
              localA.f = "";
              if (paramPackageStats.codeSize > 0L)
              {
                localA.f += Formatter.formatFileSize((Context)this.b.get(), paramPackageStats.codeSize);
                if (TextUtils.isEmpty(localA.f)) {
                  localA.f = ((Activity)this.b.get()).getString(2131560329);
                }
                if (paramPackageStats.dataSize > 0L) {
                  localA.f = (localA.f + ",d:" + Formatter.formatFileSize((Context)this.b.get(), paramPackageStats.dataSize));
                }
                if (paramPackageStats.cacheSize > 0L) {
                  localA.f = (localA.f + "(c:" + Formatter.formatFileSize((Context)this.b.get(), paramPackageStats.cacheSize) + ")");
                }
                localA.g = paramPackageStats.codeSize;
                localA.h = paramPackageStats.dataSize;
                localA.i = paramPackageStats.cacheSize;
                return;
              }
              localA.f = ((Activity)this.b.get()).getString(2131560329);
            }
          }
          HiddenAppFindActivity.a localA = null;
        }
        finally
        {
          this.a.countDown();
        }
      }
    }
  }
  
  private static final class d
    extends CommonTask<Void, Void, Void>
  {
    AtomicBoolean a = new AtomicBoolean(false);
    private Activity b;
    private ArrayAdapter<HiddenAppFindActivity.a> c;
    
    d(Activity paramActivity, ArrayAdapter<HiddenAppFindActivity.a> paramArrayAdapter)
    {
      this.b = paramActivity;
      this.c = paramArrayAdapter;
    }
    
    private boolean b()
    {
      return (this.a.get()) || (isCancelled());
    }
    
    protected Void a(Void... paramVarArgs)
    {
      paramVarArgs = this.b.getPackageManager();
      int n = this.c.getCount();
      int i = n / 32;
      if (i * 32 < n) {
        i += 1;
      }
      for (;;)
      {
        int j = 0;
        for (;;)
        {
          int k;
          CountDownLatch localCountDownLatch;
          HiddenAppFindActivity.c localC;
          int m;
          if (j < i)
          {
            if ((j + 1) * 32 <= n) {
              break label112;
            }
            k = n - j * 32;
            localCountDownLatch = new CountDownLatch(k);
            localC = new HiddenAppFindActivity.c(localCountDownLatch, this.b, this.c);
            m = 0;
          }
          for (;;)
          {
            if (m >= k) {
              break label157;
            }
            if (b())
            {
              return null;
              label112:
              k = 32;
              break;
            }
            localC.a(((HiddenAppFindActivity.a)this.c.getItem(j * 32 + m)).a.packageName, paramVarArgs);
            m += 1;
          }
          try
          {
            label157:
            localCountDownLatch.await();
            if (j == i - 1) {}
            publishProgress(new Void[] { (Void)null });
          }
          catch (InterruptedException localInterruptedException)
          {
            for (;;)
            {
              Log.e(HiddenAppFindActivity.class.getName(), localInterruptedException.getLocalizedMessage(), localInterruptedException);
            }
          }
          j += 1;
        }
      }
    }
    
    public void a()
    {
      if (!this.a.get())
      {
        this.a.set(true);
        cancel(false);
      }
    }
    
    protected void a(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      if (b())
      {
        this.c = null;
        return;
      }
      this.c.notifyDataSetChanged();
      this.c = null;
    }
    
    protected void b(Void... paramVarArgs)
    {
      super.onProgressUpdate(paramVarArgs);
      if (b()) {
        return;
      }
      this.c.notifyDataSetChanged();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      if (b()) {}
    }
  }
  
  class e
    extends CommonTask<Void, Void, Void>
  {
    ProgressDialog a;
    ArrayList<HiddenAppFindActivity.a> b = new ArrayList();
    TreeSet<String> c = new TreeSet();
    ArrayList<PackageInfo> d = new ArrayList();
    boolean e = false;
    
    e() {}
    
    private boolean a(String paramString, PackageInfo paramPackageInfo)
    {
      boolean bool = false;
      if (!this.c.contains(paramString))
      {
        this.c.add(paramString);
        this.d.add(paramPackageInfo);
        bool = true;
      }
      return bool;
    }
    
    private void b()
    {
      BaseApplicationActivity.a localA = HiddenAppFindActivity.this.d;
      localA.setNotifyOnChange(false);
      localA.clear();
      for (;;)
      {
        synchronized (HiddenAppFindActivity.this)
        {
          if (Build.VERSION.SDK_INT >= 11)
          {
            localA.addAll(this.b);
            HiddenAppFindActivity.this.c();
            localA.notifyDataSetChanged();
            if (HiddenAppFindActivity.k(HiddenAppFindActivity.this).getCount() == 0) {
              c.a(HiddenAppFindActivity.this, 2131559655);
            }
            if (HiddenAppFindActivity.k(HiddenAppFindActivity.this) != null)
            {
              HiddenAppFindActivity.k(HiddenAppFindActivity.this).postDelayed(new Runnable()
              {
                public void run()
                {
                  if (HiddenAppFindActivity.e.a(HiddenAppFindActivity.e.this)) {
                    return;
                  }
                  ListView localListView = HiddenAppFindActivity.k(HiddenAppFindActivity.this);
                  if (HiddenAppFindActivity.o(HiddenAppFindActivity.this) < 0) {}
                  for (int i = 0;; i = HiddenAppFindActivity.o(HiddenAppFindActivity.this))
                  {
                    localListView.setSelectionFromTop(i, HiddenAppFindActivity.p(HiddenAppFindActivity.this));
                    return;
                  }
                }
              }, 200L);
              ??? = HiddenAppFindActivity.k(HiddenAppFindActivity.this);
              if (HiddenAppFindActivity.o(HiddenAppFindActivity.this) >= 0) {
                break label181;
              }
              i = 0;
              ((ListView)???).setSelectionFromTop(i, HiddenAppFindActivity.p(HiddenAppFindActivity.this));
            }
          }
          else
          {
            Iterator localIterator = this.b.iterator();
            if (!localIterator.hasNext()) {
              continue;
            }
            localA.add((HiddenAppFindActivity.a)localIterator.next());
          }
        }
        label181:
        int i = HiddenAppFindActivity.o(HiddenAppFindActivity.this);
      }
    }
    
    private boolean c()
    {
      if (HiddenAppFindActivity.this.isFinishing()) {}
      while ((this.e) || (isCancelled())) {
        return true;
      }
      return false;
    }
    
    /* Error */
    protected Void a(Void... paramVarArgs)
    {
      // Byte code:
      //   0: aload_0
      //   1: invokespecial 60	org/codein/app/HiddenAppFindActivity$e:c	()Z
      //   4: ifeq +5 -> 9
      //   7: aconst_null
      //   8: areturn
      //   9: aload_0
      //   10: getfield 29	org/codein/app/HiddenAppFindActivity$e:f	Lorg/codein/app/HiddenAppFindActivity;
      //   13: invokevirtual 158	org/codein/app/HiddenAppFindActivity:getResources	()Landroid/content/res/Resources;
      //   16: ldc -97
      //   18: invokevirtual 165	android/content/res/Resources:getString	(I)Ljava/lang/String;
      //   21: astore 4
      //   23: aload_0
      //   24: getfield 29	org/codein/app/HiddenAppFindActivity$e:f	Lorg/codein/app/HiddenAppFindActivity;
      //   27: invokevirtual 169	org/codein/app/HiddenAppFindActivity:getPackageManager	()Landroid/content/pm/PackageManager;
      //   30: astore 5
      //   32: aload 5
      //   34: iconst_1
      //   35: invokevirtual 175	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
      //   38: astore_1
      //   39: aload_1
      //   40: ifnull +290 -> 330
      //   43: aload_1
      //   44: invokeinterface 180 1 0
      //   49: ifle +281 -> 330
      //   52: aload_1
      //   53: invokeinterface 181 1 0
      //   58: astore_1
      //   59: aload_1
      //   60: invokeinterface 132 1 0
      //   65: ifeq +265 -> 330
      //   68: aload_1
      //   69: invokeinterface 136 1 0
      //   74: checkcast 183	android/content/pm/PackageInfo
      //   77: astore 6
      //   79: aload_0
      //   80: invokespecial 60	org/codein/app/HiddenAppFindActivity$e:c	()Z
      //   83: ifne -76 -> 7
      //   86: aload 6
      //   88: getfield 187	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
      //   91: getfield 192	android/content/pm/ApplicationInfo:flags	I
      //   94: iconst_1
      //   95: iand
      //   96: ifne -37 -> 59
      //   99: aload 6
      //   101: getfield 187	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
      //   104: getfield 196	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   107: astore 7
      //   109: aload 6
      //   111: getfield 200	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
      //   114: ifnull +204 -> 318
      //   117: iconst_0
      //   118: istore_2
      //   119: iload_2
      //   120: aload 6
      //   122: getfield 200	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
      //   125: arraylength
      //   126: if_icmpge +58 -> 184
      //   129: aload 6
      //   131: getfield 200	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
      //   134: iload_2
      //   135: aaload
      //   136: astore 8
      //   138: new 202	android/content/ComponentName
      //   141: dup
      //   142: aload 8
      //   144: getfield 205	android/content/pm/ActivityInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
      //   147: getfield 196	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   150: aload 8
      //   152: getfield 208	android/content/pm/ActivityInfo:name	Ljava/lang/String;
      //   155: invokespecial 211	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
      //   158: astore 8
      //   160: aload 5
      //   162: aload 8
      //   164: invokevirtual 215	android/content/pm/PackageManager:getComponentEnabledSetting	(Landroid/content/ComponentName;)I
      //   167: iconst_2
      //   168: if_icmpne +79 -> 247
      //   171: aload_0
      //   172: aload 7
      //   174: aload 6
      //   176: invokespecial 217	org/codein/app/HiddenAppFindActivity$e:a	(Ljava/lang/String;Landroid/content/pm/PackageInfo;)Z
      //   179: istore_3
      //   180: iload_3
      //   181: ifeq +66 -> 247
      //   184: aload 5
      //   186: aload 7
      //   188: invokevirtual 221	android/content/pm/PackageManager:getApplicationEnabledSetting	(Ljava/lang/String;)I
      //   191: iconst_2
      //   192: if_icmpne +12 -> 204
      //   195: aload_0
      //   196: aload 7
      //   198: aload 6
      //   200: invokespecial 217	org/codein/app/HiddenAppFindActivity$e:a	(Ljava/lang/String;Landroid/content/pm/PackageInfo;)Z
      //   203: pop
      //   204: aload 5
      //   206: aload 7
      //   208: invokevirtual 225	android/content/pm/PackageManager:getLaunchIntentForPackage	(Ljava/lang/String;)Landroid/content/Intent;
      //   211: astore 8
      //   213: aload 8
      //   215: ifnonnull +56 -> 271
      //   218: aload_0
      //   219: aload 7
      //   221: aload 6
      //   223: invokespecial 217	org/codein/app/HiddenAppFindActivity$e:a	(Ljava/lang/String;Landroid/content/pm/PackageInfo;)Z
      //   226: pop
      //   227: goto -168 -> 59
      //   230: astore_1
      //   231: aload_1
      //   232: invokevirtual 228	java/lang/Exception:printStackTrace	()V
      //   235: aconst_null
      //   236: astore_1
      //   237: goto -198 -> 39
      //   240: astore 8
      //   242: aload 8
      //   244: invokevirtual 228	java/lang/Exception:printStackTrace	()V
      //   247: aload_0
      //   248: invokespecial 60	org/codein/app/HiddenAppFindActivity$e:c	()Z
      //   251: ifne -244 -> 7
      //   254: iload_2
      //   255: iconst_1
      //   256: iadd
      //   257: istore_2
      //   258: goto -139 -> 119
      //   261: astore 8
      //   263: aload 8
      //   265: invokevirtual 228	java/lang/Exception:printStackTrace	()V
      //   268: goto -64 -> 204
      //   271: aload 8
      //   273: invokevirtual 234	android/content/Intent:getCategories	()Ljava/util/Set;
      //   276: ifnull +30 -> 306
      //   279: aload 8
      //   281: invokevirtual 234	android/content/Intent:getCategories	()Ljava/util/Set;
      //   284: ldc -20
      //   286: invokeinterface 239 2 0
      //   291: ifne -232 -> 59
      //   294: aload_0
      //   295: getfield 42	org/codein/app/HiddenAppFindActivity$e:c	Ljava/util/TreeSet;
      //   298: aload 7
      //   300: invokevirtual 52	java/util/TreeSet:contains	(Ljava/lang/Object;)Z
      //   303: ifne -244 -> 59
      //   306: aload_0
      //   307: aload 7
      //   309: aload 6
      //   311: invokespecial 217	org/codein/app/HiddenAppFindActivity$e:a	(Ljava/lang/String;Landroid/content/pm/PackageInfo;)Z
      //   314: pop
      //   315: goto -256 -> 59
      //   318: aload_0
      //   319: aload 7
      //   321: aload 6
      //   323: invokespecial 217	org/codein/app/HiddenAppFindActivity$e:a	(Ljava/lang/String;Landroid/content/pm/PackageInfo;)Z
      //   326: pop
      //   327: goto -268 -> 59
      //   330: aload_0
      //   331: invokespecial 60	org/codein/app/HiddenAppFindActivity$e:c	()Z
      //   334: ifne -327 -> 7
      //   337: aload_0
      //   338: getfield 44	org/codein/app/HiddenAppFindActivity$e:d	Ljava/util/ArrayList;
      //   341: invokevirtual 240	java/util/ArrayList:size	()I
      //   344: ifle -337 -> 7
      //   347: aload_0
      //   348: getfield 44	org/codein/app/HiddenAppFindActivity$e:d	Ljava/util/ArrayList;
      //   351: invokevirtual 127	java/util/ArrayList:iterator	()Ljava/util/Iterator;
      //   354: astore 6
      //   356: aload 6
      //   358: invokeinterface 132 1 0
      //   363: ifeq -356 -> 7
      //   366: aload 6
      //   368: invokeinterface 136 1 0
      //   373: checkcast 183	android/content/pm/PackageInfo
      //   376: getfield 187	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
      //   379: astore 8
      //   381: aload 8
      //   383: ifnull -27 -> 356
      //   386: aload_0
      //   387: invokespecial 60	org/codein/app/HiddenAppFindActivity$e:c	()Z
      //   390: ifne -383 -> 7
      //   393: new 138	org/codein/app/HiddenAppFindActivity$a
      //   396: dup
      //   397: invokespecial 241	org/codein/app/HiddenAppFindActivity$a:<init>	()V
      //   400: astore 7
      //   402: aload 7
      //   404: aload 8
      //   406: putfield 243	org/codein/app/HiddenAppFindActivity$a:a	Landroid/content/pm/ApplicationInfo;
      //   409: aload 5
      //   411: aload 8
      //   413: getfield 196	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   416: iconst_0
      //   417: invokevirtual 247	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   420: astore 9
      //   422: new 249	java/lang/StringBuilder
      //   425: dup
      //   426: invokespecial 250	java/lang/StringBuilder:<init>	()V
      //   429: aload 4
      //   431: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   434: ldc_w 256
      //   437: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   440: astore 10
      //   442: aload 9
      //   444: getfield 259	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
      //   447: ifnonnull +118 -> 565
      //   450: aload 9
      //   452: getfield 262	android/content/pm/PackageInfo:versionCode	I
      //   455: invokestatic 267	java/lang/String:valueOf	(I)Ljava/lang/String;
      //   458: astore_1
      //   459: aload 7
      //   461: aload 10
      //   463: aload_1
      //   464: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   467: invokevirtual 271	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   470: putfield 274	org/codein/app/HiddenAppFindActivity$a:c	Ljava/lang/CharSequence;
      //   473: aload 7
      //   475: aload 9
      //   477: getfield 259	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
      //   480: putfield 276	org/codein/app/HiddenAppFindActivity$a:d	Ljava/lang/CharSequence;
      //   483: aload 7
      //   485: aload 9
      //   487: getfield 262	android/content/pm/PackageInfo:versionCode	I
      //   490: putfield 278	org/codein/app/HiddenAppFindActivity$a:k	I
      //   493: aload 7
      //   495: aload 8
      //   497: aload 5
      //   499: invokevirtual 282	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
      //   502: putfield 284	org/codein/app/HiddenAppFindActivity$a:b	Ljava/lang/CharSequence;
      //   505: aload 8
      //   507: getfield 287	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
      //   510: ifnull +23 -> 533
      //   513: aload 8
      //   515: getfield 287	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
      //   518: ldc_w 289
      //   521: invokevirtual 292	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   524: ifeq +9 -> 533
      //   527: aload 7
      //   529: iconst_1
      //   530: putfield 295	org/codein/app/HiddenAppFindActivity$a:l	Z
      //   533: aload 8
      //   535: aload 5
      //   537: invokevirtual 299	android/content/pm/ApplicationInfo:loadIcon	(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;
      //   540: astore_1
      //   541: aload 7
      //   543: ifnull +9 -> 552
      //   546: aload 7
      //   548: aload_1
      //   549: putfield 302	org/codein/app/HiddenAppFindActivity$a:e	Landroid/graphics/drawable/Drawable;
      //   552: aload_0
      //   553: getfield 37	org/codein/app/HiddenAppFindActivity$e:b	Ljava/util/ArrayList;
      //   556: aload 7
      //   558: invokevirtual 56	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   561: pop
      //   562: goto -206 -> 356
      //   565: aload 9
      //   567: getfield 259	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
      //   570: astore_1
      //   571: goto -112 -> 459
      //   574: astore_1
      //   575: aload_1
      //   576: invokevirtual 303	java/lang/OutOfMemoryError:printStackTrace	()V
      //   579: goto -27 -> 552
      //   582: astore_1
      //   583: ldc 7
      //   585: invokevirtual 308	java/lang/Class:getName	()Ljava/lang/String;
      //   588: aload_1
      //   589: invokevirtual 311	android/content/pm/PackageManager$NameNotFoundException:getLocalizedMessage	()Ljava/lang/String;
      //   592: aload_1
      //   593: invokestatic 316	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   596: pop
      //   597: goto -45 -> 552
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	600	0	this	e
      //   0	600	1	paramVarArgs	Void[]
      //   118	140	2	i	int
      //   179	2	3	bool	boolean
      //   21	409	4	str	String
      //   30	506	5	localPackageManager	PackageManager
      //   77	290	6	localObject1	Object
      //   107	450	7	localObject2	Object
      //   136	78	8	localObject3	Object
      //   240	3	8	localException1	Exception
      //   261	19	8	localException2	Exception
      //   379	155	8	localApplicationInfo	ApplicationInfo
      //   420	146	9	localPackageInfo	PackageInfo
      //   440	22	10	localStringBuilder	StringBuilder
      // Exception table:
      //   from	to	target	type
      //   32	39	230	java/lang/Exception
      //   160	180	240	java/lang/Exception
      //   184	204	261	java/lang/Exception
      //   533	541	574	java/lang/OutOfMemoryError
      //   546	552	574	java/lang/OutOfMemoryError
      //   409	459	582	android/content/pm/PackageManager$NameNotFoundException
      //   459	533	582	android/content/pm/PackageManager$NameNotFoundException
      //   533	541	582	android/content/pm/PackageManager$NameNotFoundException
      //   546	552	582	android/content/pm/PackageManager$NameNotFoundException
      //   565	571	582	android/content/pm/PackageManager$NameNotFoundException
      //   575	579	582	android/content/pm/PackageManager$NameNotFoundException
    }
    
    public void a()
    {
      if (!this.e)
      {
        this.e = true;
        cancel(false);
      }
    }
    
    protected void a(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      if (c()) {
        return;
      }
      try
      {
        if (this.a.isShowing()) {
          this.a.dismiss();
        }
        if (HiddenAppFindActivity.m(HiddenAppFindActivity.this).b()) {
          HiddenAppFindActivity.m(HiddenAppFindActivity.this).setRefreshing(false);
        }
        b();
        HiddenAppFindActivity.a(HiddenAppFindActivity.this, new HiddenAppFindActivity.d(HiddenAppFindActivity.this, HiddenAppFindActivity.this.d));
        HiddenAppFindActivity.n(HiddenAppFindActivity.this).startTask(new Void[] { (Void)null });
      }
      catch (Exception paramVoid)
      {
        for (;;)
        {
          paramVoid.printStackTrace();
        }
      }
      this.c.clear();
      this.d.clear();
      this.b.clear();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      if (c()) {
        return;
      }
      try
      {
        this.a = ProgressDialog.show(HiddenAppFindActivity.this, "", HiddenAppFindActivity.this.getString(2131559625));
        this.a.setCancelable(true);
        this.a.setCanceledOnTouchOutside(false);
        this.a.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymousDialogInterface)
          {
            if (HiddenAppFindActivity.this.isFinishing()) {}
            do
            {
              return;
              HiddenAppFindActivity.e.this.a();
            } while (!HiddenAppFindActivity.m(HiddenAppFindActivity.this).b());
            HiddenAppFindActivity.m(HiddenAppFindActivity.this).setRefreshing(false);
          }
        });
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  class f
    extends CommonTask<String, Void, Boolean>
  {
    private final boolean b = false;
    private boolean c = false;
    private String d = "";
    private Vector<Integer> e = new Vector();
    
    public f(String paramString)
    {
      this.d = paramString;
    }
    
    private boolean c()
    {
      return (this.c) || (isCancelled());
    }
    
    protected Boolean a(String... paramVarArgs)
    {
      if (isCancelled()) {
        return Boolean.valueOf(true);
      }
      for (;;)
      {
        try
        {
          String str = this.d.toLowerCase();
          if (str.length() > 0)
          {
            BaseApplicationActivity.a localA = HiddenAppFindActivity.this.d;
            if (localA != null)
            {
              i = 0;
              if (i < localA.getCount())
              {
                paramVarArgs = (HiddenAppFindActivity.a)localA.getItem(i);
                if (paramVarArgs.b == null) {
                  continue;
                }
                paramVarArgs = paramVarArgs.b.toString();
                if (paramVarArgs.trim().toLowerCase().contains(str)) {
                  this.e.add(Integer.valueOf(i));
                }
                boolean bool = c();
                if (!bool) {
                  continue;
                }
              }
            }
          }
        }
        catch (Exception paramVarArgs)
        {
          int i;
          paramVarArgs.printStackTrace();
          continue;
        }
        return Boolean.valueOf(true);
        paramVarArgs = paramVarArgs.a.packageName;
        continue;
        i += 1;
      }
    }
    
    public void a()
    {
      this.c = true;
      this.d = "";
      if (!isCancelled()) {
        cancel(false);
      }
    }
    
    protected void a(Boolean paramBoolean)
    {
      try
      {
        boolean bool = c();
        if (bool) {
          return;
        }
        if (this.e.size() > 0)
        {
          HiddenAppFindActivity.a(HiddenAppFindActivity.this).clear();
          HiddenAppFindActivity.a(HiddenAppFindActivity.this).addAll(this.e);
          HiddenAppFindActivity.a(HiddenAppFindActivity.this, 0);
          HiddenAppFindActivity.this.d.notifyDataSetChanged();
          HiddenAppFindActivity.k(HiddenAppFindActivity.this).setSelection(((Integer)HiddenAppFindActivity.a(HiddenAppFindActivity.this).get(HiddenAppFindActivity.q(HiddenAppFindActivity.this))).intValue());
        }
        this.e.clear();
        HiddenAppFindActivity.a(HiddenAppFindActivity.this, true);
        return;
      }
      finally
      {
        this.e.clear();
      }
    }
    
    public String b()
    {
      return this.d;
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
      a(Boolean.valueOf(false));
    }
    
    protected void onPreExecute()
    {
      this.c = false;
    }
  }
}
