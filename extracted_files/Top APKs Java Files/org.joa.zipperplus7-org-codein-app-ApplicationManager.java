package org.codein.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
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
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v4.view.ag;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.b;
import android.support.v7.app.ActionBar;
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
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
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
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.c.a;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import org.codein.app.task.SendAppTask;
import org.test.flashtest.ImageViewerApp;
import org.test.flashtest.customview.CircleButton;
import org.test.flashtest.pref.CommonPreferenceActivity;
import org.test.flashtest.pref.control.ColorTitlePreferenceCategory;
import org.test.flashtest.util.CommonTask;
import org.test.flashtest.util.ac;
import org.test.flashtest.util.ad;
import org.test.flashtest.util.af;
import org.test.flashtest.util.j;
import org.test.flashtest.util.k;
import org.test.flashtest.util.l;
import org.test.flashtest.util.u;
import org.test.flashtest.util.w;
import org.test.flashtest.util.y;

public final class ApplicationManager
  extends BaseApplicationActivity
  implements SwipeRefreshLayout.b, View.OnClickListener
{
  private static boolean Q;
  static Method a;
  private a A;
  private g B;
  private h C;
  private d D;
  private SendAppTask E;
  private boolean F = false;
  private int G = 0;
  private i H;
  private Vector<Integer> I = new Vector();
  private int J = org.test.flashtest.a.b.a;
  private int K = -7114533;
  private String L = "";
  private int M = -1;
  private boolean N = false;
  private boolean O = false;
  private boolean P = false;
  private int R = 0;
  private int S = 0;
  private org.test.flashtest.tutorial.d T;
  private boolean U = false;
  Handler b = new Handler()
  {
    public void handleMessage(Message arg1)
    {
      boolean bool = false;
      Object localObject3;
      switch (???.what)
      {
      default: 
      case 1: 
      case 201: 
      case 202: 
      case 203: 
      case 2: 
      case 204: 
      case 205: 
      case 207: 
      case 206: 
      case 5: 
      case 208: 
        do
        {
          int j;
          int i;
          Object localObject4;
          do
          {
            do
            {
              for (;;)
              {
                return;
                BaseApplicationActivity.a localA = ApplicationManager.this.d;
                localA.setNotifyOnChange(false);
                localA.clear();
                synchronized (ApplicationManager.a(ApplicationManager.this))
                {
                  localObject3 = ApplicationManager.a(ApplicationManager.this).a;
                  j = ((ArrayList)localObject3).size();
                  i = 0;
                  while (i < j)
                  {
                    localA.add(((ArrayList)localObject3).get(i));
                    i += 1;
                  }
                  localA.notifyDataSetChanged();
                  ApplicationManager.b(ApplicationManager.this).setSelection(0);
                  sendEmptyMessage(2);
                  if (ApplicationManager.b(ApplicationManager.this).getCount() == 0)
                  {
                    c.a(ApplicationManager.this, 2131559655);
                    return;
                  }
                }
              }
            } while (ApplicationManager.c(ApplicationManager.this) == null);
            ApplicationManager.c(ApplicationManager.this).setMessage(ApplicationManager.this.getString(2131559011, new Object[] { ???.obj }));
            ApplicationManager.c(ApplicationManager.this).setProgress(ApplicationManager.c(ApplicationManager.this).getProgress() + 1);
            return;
            if ((???.arg1 == 0) && (ApplicationManager.c(ApplicationManager.this) != null))
            {
              ApplicationManager.c(ApplicationManager.this).dismiss();
              ApplicationManager.a(ApplicationManager.this, null);
            }
            c.a(ApplicationManager.this, ApplicationManager.this.getString(2131558834, new Object[] { ???.obj }));
            return;
            localObject3 = (List)???.obj;
            if (ApplicationManager.c(ApplicationManager.this) != null)
            {
              localObject4 = ApplicationManager.c(ApplicationManager.this);
              if (???.arg2 > 0)
              {
                ??? = ApplicationManager.this.getString(2131559008, new Object[] { Integer.valueOf(???.arg1), Integer.valueOf(???.arg2) });
                ((ProgressDialog)localObject4).setMessage((CharSequence)???);
                ApplicationManager.c(ApplicationManager.this).setProgress(ApplicationManager.c(ApplicationManager.this).getMax());
                ApplicationManager.c(ApplicationManager.this).dismiss();
                ApplicationManager.a(ApplicationManager.this, null);
              }
            }
            else
            {
              ??? = ApplicationManager.this;
              if (???.arg2 <= 0) {
                break label663;
              }
            }
            for (??? = ApplicationManager.this.getString(2131559010, new Object[] { Integer.valueOf(???.arg1), c.a(ApplicationManager.this, "app_export_dir", "/sdcard/backups/"), Integer.valueOf(???.arg2) });; ??? = ApplicationManager.this.getString(2131559009, new Object[] { Integer.valueOf(???.arg1), c.a(ApplicationManager.this, "app_export_dir", "/sdcard/backups/") }))
            {
              c.a((Context)???, ???);
              ApplicationManager.this.a(false);
              if (!ApplicationManager.d(ApplicationManager.this)) {
                break;
              }
              if (ApplicationManager.e(ApplicationManager.this) != null) {
                ApplicationManager.e(ApplicationManager.this).a = true;
              }
              ApplicationManager.a(ApplicationManager.this, new ApplicationManager.d(ApplicationManager.this, (List)localObject3, ApplicationManager.a(ApplicationManager.this), ApplicationManager.this.b)).start();
              return;
              ??? = ApplicationManager.this.getString(2131559007, new Object[] { Integer.valueOf(???.arg1) });
              break label445;
            }
          } while (ApplicationManager.c(ApplicationManager.this) == null);
          ApplicationManager.c(ApplicationManager.this).dismiss();
          ApplicationManager.a(ApplicationManager.this, null);
          return;
          localObject3 = ApplicationManager.this.d;
          if (???.arg1 == 1)
          {
            ((ArrayAdapter)localObject3).setNotifyOnChange(false);
            ((ArrayAdapter)localObject3).clear();
          }
          for (;;)
          {
            synchronized (ApplicationManager.a(ApplicationManager.this))
            {
              localObject4 = ApplicationManager.a(ApplicationManager.this).a;
              j = ((ArrayList)localObject4).size();
              i = 0;
              if (i < j)
              {
                ((ArrayAdapter)localObject3).add(((ArrayList)localObject4).get(i));
                i += 1;
                continue;
              }
              ((ArrayAdapter)localObject3).notifyDataSetChanged();
              if ((???.arg1 != 1) || (ApplicationManager.b(ApplicationManager.this) == null)) {
                break;
              }
              ApplicationManager.b(ApplicationManager.this).postDelayed(new Runnable()
              {
                public void run()
                {
                  if (ApplicationManager.this.isFinishing()) {
                    return;
                  }
                  ListView localListView = ApplicationManager.b(ApplicationManager.this);
                  if (ApplicationManager.f(ApplicationManager.this) < 0) {}
                  for (int i = 0;; i = ApplicationManager.f(ApplicationManager.this))
                  {
                    localListView.setSelectionFromTop(i, ApplicationManager.g(ApplicationManager.this));
                    return;
                  }
                }
              }, 200L);
              ??? = ApplicationManager.b(ApplicationManager.this);
              if (ApplicationManager.f(ApplicationManager.this) < 0)
              {
                i = 0;
                ???.setSelectionFromTop(i, ApplicationManager.g(ApplicationManager.this));
                return;
              }
            }
            i = ApplicationManager.f(ApplicationManager.this);
          }
          ApplicationManager.this.d.notifyDataSetChanged();
          return;
          c.a(ApplicationManager.this, (String)???.obj);
          return;
          if (ApplicationManager.h(ApplicationManager.this) != null) {
            ApplicationManager.h(ApplicationManager.this).a = true;
          }
          if (ApplicationManager.i(ApplicationManager.this) != null) {
            ApplicationManager.i(ApplicationManager.this).a = true;
          }
          if (ApplicationManager.e(ApplicationManager.this) != null) {
            ApplicationManager.e(ApplicationManager.this).a = true;
          }
          if (ApplicationManager.j(ApplicationManager.this).b()) {
            ApplicationManager.j(ApplicationManager.this).setRefreshing(false);
          }
          ApplicationManager.a(ApplicationManager.this).a((ArrayList)???.obj);
          ApplicationManager.a(ApplicationManager.this).a(c.a(ApplicationManager.this, "sort_order_type", 0), c.a(ApplicationManager.this, "sort_direction", 1));
          ApplicationManager.this.b.sendEmptyMessage(1);
          if (ApplicationManager.k(ApplicationManager.this)) {
            ApplicationManager.a(ApplicationManager.this, new ApplicationManager.g(ApplicationManager.this, ApplicationManager.a(ApplicationManager.this), ApplicationManager.this.b)).start();
          }
          ApplicationManager.a(ApplicationManager.this, new ApplicationManager.h(ApplicationManager.this, ApplicationManager.a(ApplicationManager.this), ApplicationManager.this.b)).start();
        } while (!ApplicationManager.d(ApplicationManager.this));
        ApplicationManager.a(ApplicationManager.this, new ApplicationManager.d(ApplicationManager.this, null, ApplicationManager.a(ApplicationManager.this), ApplicationManager.this.b)).start();
        return;
      case 3: 
        label445:
        label663:
        sendEmptyMessage(2);
        ??? = (String)???.obj;
        localObject3 = ApplicationManager.this;
        if (???.arg2 == 1) {
          bool = true;
        }
        c.a((String)???, "Android Applications - ", (Activity)localObject3, bool);
        return;
      }
      sendEmptyMessage(2);
      c.a(this, ApplicationManager.this, (String)???.obj, ???.arg1, "android_applications");
    }
  };
  CompoundButton.OnCheckedChangeListener c = new CompoundButton.OnCheckedChangeListener()
  {
    public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
    {
      ((ApplicationManager.b)ApplicationManager.b(ApplicationManager.this).getItemAtPosition(((Integer)paramAnonymousCompoundButton.getTag()).intValue())).m = paramAnonymousBoolean;
      int i = ApplicationManager.this.d.b();
      if (i > 0) {
        if (ApplicationManager.this.e == null) {
          ApplicationManager.this.d();
        }
      }
      for (;;)
      {
        ApplicationManager.this.a(i);
        ApplicationManager.this.d.notifyDataSetChanged();
        return;
        if (ApplicationManager.this.e != null) {
          ApplicationManager.this.e();
        }
      }
    }
  };
  private Button f;
  private Button g;
  private Button h;
  private Button i;
  private Toolbar j;
  private CircleButton k;
  private ImageButton l;
  private ViewGroup m;
  private ImageView o;
  private EditText p;
  private ImageView q;
  private ImageButton r;
  private ImageButton s;
  private ImageButton t;
  private SwipeRefreshLayout u;
  private ListView v;
  private ViewGroup w;
  private ProgressDialog x;
  private volatile boolean y;
  private String z;
  
  static
  {
    try
    {
      a = PackageManager.class.getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
      Q = false;
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e(ApplicationManager.class.getName(), localException.getLocalizedMessage(), localException);
      }
    }
  }
  
  public ApplicationManager() {}
  
  private String a(ApplicationInfo paramApplicationInfo)
  {
    localStringBuilder = new StringBuilder();
    try
    {
      localPackageManager = getPackageManager();
      paramApplicationInfo = localPackageManager.getPackageArchiveInfo(paramApplicationInfo.publicSourceDir, 20480).requestedPermissions;
      if (paramApplicationInfo == null) {
        break label121;
      }
      n = 0;
    }
    catch (Exception paramApplicationInfo)
    {
      PackageManager localPackageManager;
      int i1;
      if (!org.test.flashtest.a.d.a().af) {
        break label121;
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
  
  private static List<b> a(ListView paramListView)
  {
    int i1 = paramListView.getCount();
    ArrayList localArrayList = new ArrayList();
    int n = 0;
    while (n < i1)
    {
      b localB = (b)paramListView.getItemAtPosition(n);
      if (localB.m) {
        localArrayList.add(localB);
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
    paramFile2 = new BufferedOutputStream(k.b(ImageViewerApp.k, paramFile2), 32768);
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
        if ((this.H != null) && (!this.H.b().equals(paramString))) {
          this.F = false;
        }
        if (!this.F)
        {
          if (this.H != null) {
            this.H.a();
          }
          this.L = paramString;
          if (paramString.length() <= 0) {
            break;
          }
          this.I.clear();
          this.d.notifyDataSetChanged();
          this.H = new i(paramString);
          this.H.startTask(new Void[0]);
          return;
        }
        if (!paramBoolean) {
          break label203;
        }
        if (this.G + 1 >= this.I.size())
        {
          this.G = 0;
          if ((this.I.size() <= 0) || (this.I.size() <= this.G)) {
            break;
          }
          this.d.notifyDataSetChanged();
          this.v.setSelection(((Integer)this.I.get(this.G)).intValue());
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        this.F = false;
        return;
      }
      this.G += 1;
      continue;
      label203:
      if (this.G - 1 <= 0) {
        this.G = (this.I.size() - 1);
      } else {
        this.G -= 1;
      }
    }
  }
  
  private void b(String paramString)
  {
    try
    {
      if (this.H != null) {
        this.H.a();
      }
      this.F = false;
      this.L = paramString;
      this.I.clear();
      this.d.notifyDataSetChanged();
      if (paramString.length() > 0)
      {
        this.H = new i(paramString);
        this.H.startTask(new Void[0]);
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
    try
    {
      e();
      if (this.H != null) {
        this.H.a();
      }
      this.I.clear();
      this.F = false;
      if (this.x == null) {
        this.x = new ProgressDialog(this);
      }
      this.x.setMessage(getResources().getText(2131559341));
      this.x.setIndeterminate(true);
      this.x.show();
      new Thread(new Runnable()
      {
        public void run()
        {
          if (ApplicationManager.this.isFinishing()) {}
          ArrayList localArrayList;
          for (;;)
          {
            return;
            localArrayList = new ArrayList();
            try
            {
              PackageManager localPackageManager = ApplicationManager.this.getPackageManager();
              Object localObject = localPackageManager.getInstalledApplications(0);
              List localList = ApplicationManager.this.a((List)localObject);
              int j = localList.size();
              int i = 0;
              if (i < j)
              {
                ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
                ApplicationManager.b localB = new ApplicationManager.b();
                localB.a = localApplicationInfo;
                for (;;)
                {
                  try
                  {
                    localPackageInfo = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 0);
                    StringBuilder localStringBuilder = new StringBuilder().append(ApplicationManager.s(ApplicationManager.this)).append(" ");
                    if (localPackageInfo.versionName != null) {
                      continue;
                    }
                    localObject = String.valueOf(localPackageInfo.versionCode);
                    localB.c = ((String)localObject);
                    localB.d = localPackageInfo.versionName;
                    localB.k = localPackageInfo.versionCode;
                    if ((localApplicationInfo.sourceDir != null) && (localApplicationInfo.sourceDir.contains("/data/app-private"))) {
                      localB.l = true;
                    }
                  }
                  catch (PackageManager.NameNotFoundException localNameNotFoundException)
                  {
                    PackageInfo localPackageInfo;
                    Log.e(ApplicationManager.class.getName(), localNameNotFoundException.getLocalizedMessage(), localNameNotFoundException);
                    continue;
                  }
                  localArrayList.add(localB);
                  i += 1;
                  break;
                  localObject = localPackageInfo.versionName;
                }
              }
              if (ApplicationManager.this.isFinishing()) {}
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
              return;
            }
          }
          ApplicationManager.this.b.sendMessage(ApplicationManager.this.b.obtainMessage(208, localArrayList));
        }
      }, "MainUpdater").start();
      return;
    }
    finally {}
  }
  
  private static boolean g()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }
  
  private void h()
  {
    Object localObject = a(this.v);
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
          paramAnonymousDialogInterface = ((ApplicationManager.b)this.a.get(i)).a;
          paramAnonymousDialogInterface = new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramAnonymousDialogInterface.packageName));
          if (paramAnonymousInt != 0) {
            break label148;
          }
          if (ApplicationManager.this.getPackageManager().queryIntentActivities(paramAnonymousDialogInterface, 0).size() > 0) {
            paramAnonymousInt = 1;
          }
        }
        label148:
        for (;;)
        {
          if (paramAnonymousInt != 0) {
            ApplicationManager.this.startActivity(paramAnonymousDialogInterface);
          }
          i += 1;
          break;
          paramAnonymousInt = 0;
          continue;
          if (paramAnonymousInt == 0)
          {
            c.a(ApplicationManager.this, 2131560322);
            Log.d(ApplicationManager.class.getName(), "No activity found to handle the uninstall request.");
          }
          return;
        }
      }
    };
    new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131560356).setMessage(2131560323).setPositiveButton(2131559687, (DialogInterface.OnClickListener)localObject).setNegativeButton(2131558641, null).create().show();
  }
  
  private void i()
  {
    Object localObject = a(this.v);
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
    DialogInterface.OnMultiChoiceClickListener local6 = new DialogInterface.OnMultiChoiceClickListener()
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
            paramAnonymous2DialogInterface = ApplicationManager.this;
            boolean[] arrayOfBoolean = ApplicationManager.7.this.a;
            List localList = ApplicationManager.7.this.b;
            if (paramAnonymous2Int == 0) {}
            for (boolean bool = true;; bool = false)
            {
              paramAnonymous2DialogInterface.a(arrayOfBoolean, localList, bool);
              return;
            }
          }
        };
        new org.test.flashtest.customview.roundcorner.a(ApplicationManager.this).setTitle(2131558516).setItems(new String[] { ApplicationManager.this.getString(2131558832), ApplicationManager.this.getString(2131560058) }, paramAnonymousDialogInterface).create().show();
      }
    };
    new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131559264).setMultiChoiceItems(new CharSequence[] { getString(2131560349), getString(2131560213), getString(2131559766), getString(2131559356) }, arrayOfBoolean, local6).setPositiveButton(2131559687, (DialogInterface.OnClickListener)localObject).setNegativeButton(2131558641, null).create().show();
  }
  
  private void j()
  {
    Intent localIntent = new Intent(this, AppSettings.class);
    localIntent.putExtra("filter_app_type", c.a(this, "filter_app_type", 1));
    localIntent.putExtra("app_export_dir", c.a(this, "app_export_dir", "/sdcard/backups/"));
    localIntent.putExtra("sort_order_type", c.a(this, "sort_order_type", 0));
    localIntent.putExtra("sort_direction", c.a(this, "sort_direction", 1));
    localIntent.putExtra("show_size", c.a(this, "show_size"));
    localIntent.putExtra("show_date", c.a(this, "show_date"));
    localIntent.putExtra("show_icon", c.a(this, "show_icon"));
    localIntent.putExtra("show_backup_state", c.a(this, "show_backup_state"));
    localIntent.putExtra("default_tap_action", c.a(this, "default_tap_action", 0));
    startActivityForResult(localIntent, 1);
  }
  
  private void k()
  {
    Intent localIntent = new Intent(this, RestoreAppActivity.class);
    Object localObject = new File(c.a(this, "app_export_dir", "/sdcard/backups/"));
    if (1 == this.M) {}
    for (localObject = new File((File)localObject, "user/").getAbsolutePath();; localObject = new File((File)localObject, "system/").getAbsolutePath())
    {
      localIntent.putExtra("restore_path", (String)localObject);
      localIntent.putExtra("archive_path", new File(c.a(this, "app_export_dir", "/sdcard/backups/"), "archived/").getAbsolutePath());
      startActivityForResult(localIntent, 2);
      this.t.performClick();
      return;
    }
  }
  
  private void l()
  {
    Intent localIntent = c.a(getPackageManager(), "com.android.settings.UsageStats");
    if (localIntent != null) {}
    try
    {
      startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      do
      {
        localException.printStackTrace();
      } while (!y.b(localException.getMessage()));
      Toast.makeText(this, localException.getMessage(), 0).show();
    }
  }
  
  private void m()
  {
    Intent localIntent = c.a(getPackageManager(), "com.android.settings.fuelgauge.PowerUsageSummary");
    if (localIntent != null) {}
    try
    {
      startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      do
      {
        localException.printStackTrace();
      } while (!y.b(localException.getMessage()));
      Toast.makeText(this, localException.getMessage(), 0).show();
    }
  }
  
  private void n()
  {
    if (this.x != null)
    {
      this.x.dismiss();
      this.x = null;
    }
    if (this.d != null)
    {
      this.d.clear();
      this.d.notifyDataSetChanged();
    }
    this.A.a();
    this.R = 0;
    this.S = 0;
    f();
  }
  
  private void o()
  {
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131230783);
    int n = u.a().a(this);
    if ((n == 0) || (n == -1) || (!af.c()))
    {
      localLinearLayout.setVisibility(8);
      return;
    }
    localLinearLayout.setVisibility(0);
    ag.c((ImageView)findViewById(2131232180), 1.0F);
    try
    {
      AdView localAdView = new AdView(this);
      localAdView.setAdSize(com.google.android.gms.ads.d.a);
      localAdView.setAdUnitId("ca-app-pub-6383453963907648/1443996856");
      localAdView.setAdListener(new com.google.android.gms.ads.a()
      {
        public void onAdClosed()
        {
          super.onAdClosed();
        }
        
        public void onAdFailedToLoad(int paramAnonymousInt)
        {
          super.onAdFailedToLoad(paramAnonymousInt);
          try
          {
            ag.c((ImageView)ApplicationManager.this.findViewById(2131232180), 1.0F);
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
        
        public void onAdLeftApplication()
        {
          super.onAdLeftApplication();
        }
        
        public void onAdLoaded()
        {
          super.onAdLoaded();
          try
          {
            ag.c((ImageView)ApplicationManager.this.findViewById(2131232180), 0.0F);
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
        
        public void onAdOpened()
        {
          super.onAdOpened();
        }
      });
      localLinearLayout.addView(localAdView);
      localAdView.a(new c.a().a());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  String a(boolean[] paramArrayOfBoolean, List<b> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i1 = paramList.size();
    int n = 0;
    if (n < i1)
    {
      b localB = (b)paramList.get(n);
      if (n > 0) {
        localStringBuilder.append('\n');
      }
      if (localB.b == null) {}
      for (Object localObject = localB.a.packageName;; localObject = localB.b)
      {
        localStringBuilder.append((CharSequence)localObject);
        if (paramArrayOfBoolean[0] != 0) {
          localStringBuilder.append(", " + localB.c);
        }
        if (paramArrayOfBoolean[1] != 0) {
          localStringBuilder.append(", SDK " + c.a(this, localB.a));
        }
        if (paramArrayOfBoolean[2] != 0) {
          localStringBuilder.append(", " + localB.a.packageName);
        }
        if (paramArrayOfBoolean[3] != 0) {
          localStringBuilder.append(", http://market.android.com/search?q=pname:" + localB.a.packageName);
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
  
  List<ApplicationInfo> a(List<ApplicationInfo> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {}
    ApplicationInfo localApplicationInfo;
    do
    {
      return paramList;
      n = c.a(this, "filter_app_type", 1);
      this.M = n;
      if (n == 0)
      {
        localArrayList = new ArrayList();
        i1 = paramList.size();
        n = 0;
        while (n < i1)
        {
          localApplicationInfo = (ApplicationInfo)paramList.get(n);
          if ((localApplicationInfo.flags & 0x1) != 0) {
            localArrayList.add(localApplicationInfo);
          }
          n += 1;
        }
        return localArrayList;
      }
    } while (n != 1);
    ArrayList localArrayList = new ArrayList();
    int i1 = paramList.size();
    int n = 0;
    while (n < i1)
    {
      localApplicationInfo = (ApplicationInfo)paramList.get(n);
      if ((localApplicationInfo.flags & 0x1) == 0) {
        localArrayList.add(localApplicationInfo);
      }
      n += 1;
    }
    return localArrayList;
  }
  
  public void a()
  {
    n();
  }
  
  protected void a(Menu paramMenu)
  {
    paramMenu.add(0, 14, 0, 2131560049);
    paramMenu.add(0, 15, 0, 2131558931);
    paramMenu.add(0, 16, 0, 2131559005);
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
      if (this.E != null) {
        this.E.a();
      }
      File localFile = new File(org.test.flashtest.pref.b.b);
      if (!localFile.exists()) {
        localFile.mkdirs();
      }
      this.E = new SendAppTask(this, paramFile, new File(localFile, paramString + ".apk"), new org.test.flashtest.sdcardcleaner.b.c()
      {
        public void a(File paramAnonymousFile)
        {
          if (ApplicationManager.this.isFinishing()) {
            return;
          }
          ApplicationManager.a(ApplicationManager.this, ApplicationManager.this, paramAnonymousFile, true);
        }
      });
      this.E.startTask(new Void[] { (Void)null });
      return;
    }
    Toast.makeText(this, getString(2131559540), 1).show();
  }
  
  void a(final List<b> paramList, final int paramInt)
  {
    if ((paramList == null) || (paramList.isEmpty()))
    {
      c.a(this, 2131559654);
      return;
    }
    if (this.x == null) {
      this.x = new ProgressDialog(this);
    }
    this.x.setMessage(getResources().getString(2131560125));
    this.x.setIndeterminate(false);
    this.x.setProgressStyle(1);
    this.x.setMax(paramList.size());
    this.x.show();
    new Thread(new Runnable()
    {
      public void run()
      {
        Object localObject1 = new File(c.a(ApplicationManager.this, "app_export_dir", "/sdcard/backups/"));
        if ((!((File)localObject1).exists()) && (!k.a(ApplicationManager.this, ((File)localObject1).getParentFile(), ((File)localObject1).getName())))
        {
          ApplicationManager.this.b.sendMessage(Message.obtain(ApplicationManager.this.b, 202, 0, 0, ApplicationManager.this.getString(2131558967, new Object[] { ((File)localObject1).getAbsolutePath() })));
          return;
        }
        File localFile1 = new File((File)localObject1, "system/");
        if ((!localFile1.exists()) && (!k.a(ApplicationManager.this, localFile1.getParentFile(), localFile1.getName())))
        {
          ApplicationManager.this.b.sendMessage(Message.obtain(ApplicationManager.this.b, 202, 0, 0, ApplicationManager.this.getString(2131558967, new Object[] { localFile1.getAbsolutePath() })));
          return;
        }
        File localFile2 = new File((File)localObject1, "user/");
        if ((!localFile2.exists()) && (!k.a(ApplicationManager.this, localFile2.getParentFile(), localFile2.getName())))
        {
          ApplicationManager.this.b.sendMessage(Message.obtain(ApplicationManager.this.b, 202, 0, 0, ApplicationManager.this.getString(2131558967, new Object[] { localFile2.getAbsolutePath() })));
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
          localApplicationInfo = ((ApplicationManager.b)paramList.get(k)).a;
          localObject1 = localApplicationInfo.sourceDir;
          if (localObject1 == null) {
            break label931;
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
          if ((ApplicationManager.a((String)localObject1) != null) && (localApplicationInfo.packageName != null))
          {
            if (((ApplicationManager.b)paramList.get(k)).b != null)
            {
              localObject1 = ((ApplicationManager.b)paramList.get(k)).b.toString();
              label441:
              m = paramInt;
              if (!TextUtils.isEmpty((CharSequence)localObject1)) {
                break label593;
              }
              m = 0;
              localObject1 = null;
              switch (m)
              {
              default: 
                label461:
                localObject1 = localApplicationInfo.packageName;
              }
            }
            label491:
            label593:
            label928:
            for (;;)
            {
              String str = (String)localObject1 + ".apk";
              if ((localApplicationInfo.flags & 0x1) != 0) {}
              Object localObject2;
              for (localObject1 = localFile1;; localObject2 = localFile2)
              {
                localObject1 = new File((File)localObject1, str);
                ApplicationManager.this.b.sendMessage(Message.obtain(ApplicationManager.this.b, 201, str));
                try
                {
                  ApplicationManager.a(localFile3, (File)localObject1);
                  m = j + 1;
                  j = i;
                  i = m;
                }
                catch (Exception localException)
                {
                  int n;
                  Log.e(ApplicationManager.class.getName(), localException.getLocalizedMessage(), localException);
                  ApplicationManager.this.b.sendMessage(Message.obtain(ApplicationManager.this.b, 202, 1, 0, localException.getLocalizedMessage()));
                  m = i;
                  i = j;
                  j = m;
                }
                localObject1 = "";
                break label441;
                localObject1 = new StringBuilder((String)localObject1);
                break label461;
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
                break label491;
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
                if (TextUtils.isEmpty(((ApplicationManager.b)paramList.get(k)).d)) {
                  break label928;
                }
                localObject1 = (String)localObject1 + " v" + ((ApplicationManager.b)paramList.get(k)).d;
                break label491;
                break;
                ApplicationManager.this.b.sendMessage(Message.obtain(ApplicationManager.this.b, 203, j, i, paramList));
                return;
              }
            }
          }
          label931:
          m = i;
          i = j;
          j = m;
        }
      }
    }).start();
  }
  
  void a(final b paramB, int paramInt)
  {
    Object localObject1 = paramB.a.packageName;
    Object localObject2;
    label331:
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
          paramB = new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              paramAnonymousDialogInterface.dismiss();
              ApplicationManager.this.a(paramB, paramAnonymousInt + 1);
            }
          };
          new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131558516).setItems(new CharSequence[] { getString(2131559354), getString(2131559944), getString(2131560020), getString(2131558933), getString(2131560016), getString(2131559393) }, paramB).create().show();
          return;
          paramB = new Intent("android.intent.action.VIEW");
          paramB.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
          paramB.putExtra("com.android.settings.ApplicationPkgName", (String)localObject1);
          paramB.putExtra("pkg", (String)localObject1);
          if (getPackageManager().queryIntentActivities(paramB, 0).size() > 0)
          {
            startActivity(paramB);
            return;
          }
          paramB = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", (String)localObject1, null));
          if (getPackageManager().queryIntentActivities(paramB, 0).size() > 0)
          {
            startActivity(paramB);
            return;
          }
          Log.d(ApplicationManager.class.getName(), "Failed to resolve activity for InstalledAppDetails");
          return;
        } while (((String)localObject1).equals(getPackageName()));
        paramB = new Intent("android.intent.action.MAIN");
        paramB.addCategory("android.intent.category.LAUNCHER");
        localObject2 = getPackageManager().queryIntentActivities(paramB, 0);
      } while (localObject2 == null);
      int n = ((List)localObject2).size();
      paramInt = 0;
      if (paramInt < n)
      {
        localObject3 = (ResolveInfo)((List)localObject2).get(paramInt);
        if (((String)localObject1).equals(((ResolveInfo)localObject3).activityInfo.packageName))
        {
          paramB.setClassName(((ResolveInfo)localObject3).activityInfo.packageName, ((ResolveInfo)localObject3).activityInfo.name);
          paramB.addFlags(268435456).addFlags(67108864);
        }
      }
      break;
    }
    for (;;)
    {
      try
      {
        startActivity(paramB);
        paramInt = 1;
        if (paramInt != 0) {
          break label497;
        }
      }
      catch (Exception paramB)
      {
        try
        {
          paramB = getPackageManager().getLaunchIntentForPackage((String)localObject1);
          paramB.setFlags(268435456);
          startActivity(paramB);
          paramInt = 1;
          if (paramInt != 0) {
            break;
          }
          c.a(this, 2131559945);
          return;
        }
        catch (Exception paramB)
        {
          paramB.printStackTrace();
        }
        paramB = paramB;
        Log.e(ApplicationManager.class.getName(), "Cannot start activity: " + (String)localObject1, paramB);
        paramInt = 0;
        continue;
      }
      paramInt += 1;
      break label331;
      label497:
      continue;
      af.b(this, (String)localObject1);
      return;
      ApplicationInfo localApplicationInfo = paramB.a;
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
        if (paramB.d != null) {
          break label1161;
        }
        localObject3 = "";
        label633:
        localArrayList2.add(localObject3);
        localArrayList1.add(getString(2131560350));
        localArrayList2.add(String.valueOf(paramB.k));
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
          break label1173;
        }
        localObject1 = "";
        label885:
        localArrayList2.add(localObject1);
        localArrayList1.add(getString(2131560214));
        localArrayList2.add(localApplicationInfo.taskAffinity);
        localArrayList1.add(getString(2131559745));
        if (str != null) {
          break label1183;
        }
        localObject1 = "";
        label939:
        localArrayList2.add(localObject1);
        localArrayList1.add(getString(2131559085));
        localArrayList2.add(String.valueOf(localApplicationInfo.flags));
        localArrayList1.add(getString(2131558955));
        localArrayList2.add(String.valueOf(localApplicationInfo.enabled));
        localArrayList1.add(getString(2131559355));
        if (localApplicationInfo.manageSpaceActivityName != null) {
          break label1190;
        }
      }
      label1161:
      label1173:
      label1183:
      label1190:
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
        localObject3 = (String)paramB.d;
        break label633;
        localObject1 = localApplicationInfo.className;
        break label885;
        localObject1 = str;
        break label939;
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
      if (paramB.b == null) {}
      for (paramB = localApplicationInfo.packageName;; paramB = (String)paramB.b)
      {
        ((a)localObject1).a(this, paramB, localArrayList1, localArrayList2);
        return;
      }
      paramB = new Intent();
      paramB.setAction("android.intent.action.WEB_SEARCH");
      paramB.putExtra("query", (String)localObject1);
      try
      {
        startActivity(paramB);
        return;
      }
      catch (ActivityNotFoundException paramB)
      {
        ac.a(2131559569);
        paramB.printStackTrace();
        return;
      }
      localObject2 = getString(2131559596);
      if (paramB.b == null) {}
      for (localObject1 = paramB.a.packageName;; localObject1 = (String)paramB.b)
      {
        localObject1 = String.format((String)localObject2, new Object[] { localObject1 });
        new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131560058).setMessage((CharSequence)localObject1).setPositiveButton(2131559687, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface = new File(paramB.a.sourceDir);
            ApplicationManager.this.a(paramAnonymousDialogInterface, paramB.a.packageName);
          }
        }).setNegativeButton(2131558641, null).create().show();
        return;
      }
      paramInt = 0;
    }
  }
  
  void a(boolean paramBoolean)
  {
    int i1 = this.v.getCount();
    int n = 0;
    while (n < i1)
    {
      ((b)this.v.getItemAtPosition(n)).m = paramBoolean;
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
  
  void a(final boolean[] paramArrayOfBoolean, final List<b> paramList, final boolean paramBoolean)
  {
    if (this.x == null) {
      this.x = new ProgressDialog(this);
    }
    this.x.setMessage(getResources().getText(2131559341));
    this.x.setIndeterminate(true);
    this.x.show();
    new Thread(new Runnable()
    {
      public void run()
      {
        final String str = ApplicationManager.this.a(paramArrayOfBoolean, paramList);
        if (ApplicationManager.this.isFinishing()) {}
        while (!paramBoolean) {
          return;
        }
        ApplicationManager.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (!ApplicationManager.this.isFinishing()) {
              c.a.a.a.a.a.a(str, ApplicationManager.this);
            }
          }
        });
        ApplicationManager.this.b.sendEmptyMessage(2);
        ApplicationManager.this.b.sendMessage(ApplicationManager.this.b.obtainMessage(5, ApplicationManager.this.getString(2131558831)));
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
    case 16: 
      b();
      return true;
    }
    h();
    return true;
  }
  
  void b()
  {
    final List localList = a(this.v);
    if ((localList == null) || (localList.size() == 0))
    {
      c.a(this, 2131559654);
      return;
    }
    if (!g())
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
          localObject = ApplicationManager.this.getPackageManager();
          paramAnonymousAdapterView = "org.joa.zipperplus7";
        }
        try
        {
          paramAnonymousView = ApplicationManager.this.getPackageName();
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
            paramAnonymousView = ApplicationManager.this.getString(2131558532);
            continue;
            paramAnonymousView = ApplicationManager.this.getString(2131558532) + " v" + (String)localObject;
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
        ApplicationManager.this.a(localList, paramAnonymousInt);
      }
    });
    localA.setNegativeButton(2131558641, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localA.create().show();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 1) && (paramIntent != null))
    {
      this.t.performClick();
      c.b(paramIntent, this, "app_export_dir");
      c.a(paramIntent, this, "filter_app_type", 1);
      c.a(paramIntent, this, "sort_order_type", 0);
      c.a(paramIntent, this, "sort_direction", 1);
      c.a(paramIntent, this, "default_tap_action", 0);
      c.a(paramIntent, this, "show_size");
      c.a(paramIntent, this, "show_date");
      c.a(paramIntent, this, "show_icon");
      c.a(paramIntent, this, "show_backup_state");
    }
  }
  
  public void onBackPressed()
  {
    try
    {
      if (this.m.getVisibility() == 0)
      {
        this.t.performClick();
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
    if (this.f == paramView) {
      b();
    }
    do
    {
      return;
      if (this.g == paramView)
      {
        a(true);
        return;
      }
      if (this.h == paramView)
      {
        a(false);
        return;
      }
      if (this.i == paramView)
      {
        k();
        return;
      }
      if (this.l == paramView)
      {
        this.m.setVisibility(0);
        a(this.p, true);
        return;
      }
      if (this.t == paramView)
      {
        this.m.setVisibility(8);
        a(this.p);
        this.F = false;
        if (this.H != null) {
          this.H.a();
        }
        this.I.clear();
        this.L = "";
        this.p.setText("");
        this.d.notifyDataSetChanged();
        return;
      }
      if (this.r == paramView)
      {
        a(this.p.getEditableText().toString(), true);
        return;
      }
      if (this.s == paramView)
      {
        a(this.p.getEditableText().toString(), false);
        return;
      }
      if (this.q == paramView)
      {
        this.F = false;
        if (this.H != null) {
          this.H.a();
        }
        this.p.setText("");
        return;
      }
    } while (this.k != paramView);
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
    b localB;
    if ((n >= 0) && (n < this.v.getCount())) {
      localB = (b)this.v.getItemAtPosition(n);
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
      a(localB, 1);
      return true;
    case 2: 
      a(localB, 2);
      return true;
    case 3: 
      a(localB, 3);
      return true;
    case 7: 
      a(localB, 4);
      return true;
    case 11: 
      a(localB, 5);
      return true;
    case 12: 
      a(localB, 6);
      return true;
    }
    localB.m = true;
    d();
    this.d.notifyDataSetChanged();
    return true;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.z = getResources().getString(2131560349);
    this.A = new a();
    setContentView(2131361834);
    this.j = ((Toolbar)findViewById(2131232338));
    setSupportActionBar(this.j);
    getSupportActionBar().hide();
    x();
    this.f = ((Button)findViewById(2131230903));
    this.f.setOnClickListener(this);
    this.g = ((Button)findViewById(2131230909));
    this.g.setOnClickListener(this);
    this.h = ((Button)findViewById(2131230900));
    this.h.setOnClickListener(this);
    this.i = ((Button)findViewById(2131230849));
    this.i.setOnClickListener(this);
    this.w = ((ViewGroup)findViewById(2131230757));
    this.w.setVisibility(0);
    this.l = ((ImageButton)findViewById(2131232134));
    this.l.setOnClickListener(this);
    this.m = ((ViewGroup)findViewById(2131232132));
    this.o = ((ImageView)findViewById(2131232129));
    this.p = ((EditText)findViewById(2131231228));
    this.q = ((ImageView)findViewById(2131231315));
    this.q.setOnClickListener(this);
    this.r = ((ImageButton)findViewById(2131230894));
    this.r.setOnClickListener(this);
    this.s = ((ImageButton)findViewById(2131230891));
    this.s.setOnClickListener(this);
    this.t = ((ImageButton)findViewById(2131230893));
    this.t.setOnClickListener(this);
    this.u = ((SwipeRefreshLayout)findViewById(2131232016));
    this.v = ((ListView)findViewById(2131230812));
    this.v.setFastScrollEnabled(true);
    this.v.setDrawSelectorOnTop(true);
    registerForContextMenu(this.v);
    this.k = ((CircleButton)findViewById(2131231263));
    this.k.setOnClickListener(this);
    this.v.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        boolean bool = false;
        paramAnonymousAdapterView = (ApplicationManager.b)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt);
        if (paramAnonymousAdapterView != null)
        {
          if (ApplicationManager.this.e != null)
          {
            if (!paramAnonymousAdapterView.m) {
              bool = true;
            }
            paramAnonymousAdapterView.m = bool;
            ApplicationManager.this.d.notifyDataSetChanged();
            paramAnonymousInt = ApplicationManager.this.d.b();
            ApplicationManager.this.a(paramAnonymousInt);
          }
        }
        else {
          return;
        }
        paramAnonymousInt = c.a(ApplicationManager.this, "default_tap_action", 0);
        ApplicationManager.this.a(paramAnonymousAdapterView, paramAnonymousInt);
      }
    });
    this.v.setOnTouchListener(new View.OnTouchListener()
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
          ((ApplicationManager.b)getItem(i)).m = false;
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
          if (((ApplicationManager.b)getItem(i)).m) {
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
          paramAnonymousView = ApplicationManager.this.getLayoutInflater().inflate(2131361833, paramAnonymousViewGroup, false);
          paramAnonymousViewGroup = new ApplicationManager.c(ApplicationManager.this);
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
          paramAnonymousViewGroup = (ApplicationManager.c)paramAnonymousView.getTag();
        }
        ApplicationManager.b localB = (ApplicationManager.b)getItem(paramAnonymousInt);
        Object localObject;
        int i;
        if (localB.b != null)
        {
          localObject = localB.b.toString();
          if (!ApplicationManager.l(ApplicationManager.this).contains(Integer.valueOf(paramAnonymousInt))) {
            break label572;
          }
          paramAnonymousView.setBackgroundColor(ApplicationManager.m(ApplicationManager.this));
          if (!y.b(ApplicationManager.n(ApplicationManager.this))) {
            break label598;
          }
          i = ((String)localObject).toLowerCase().indexOf(ApplicationManager.n(ApplicationManager.this));
          label236:
          if (i < 0) {
            break label604;
          }
          localObject = new SpannableStringBuilder((CharSequence)localObject);
          ((SpannableStringBuilder)localObject).setSpan(new ForegroundColorSpan(ApplicationManager.o(ApplicationManager.this)), i, ApplicationManager.n(ApplicationManager.this).length() + i, 33);
          paramAnonymousViewGroup.a.setText((CharSequence)localObject);
          label297:
          if (!ApplicationManager.d(ApplicationManager.this)) {
            break label655;
          }
          switch (localB.j)
          {
          default: 
            paramAnonymousViewGroup.a.setTextColor(-16777216);
            label349:
            paramAnonymousViewGroup.c.setText(localB.c);
            if (ApplicationManager.k(ApplicationManager.this))
            {
              paramAnonymousViewGroup.b.setVisibility(0);
              if (localB.f != null)
              {
                paramAnonymousViewGroup.b.setText(localB.f);
                label399:
                if (!ApplicationManager.p(ApplicationManager.this)) {
                  break label704;
                }
                paramAnonymousViewGroup.d.setVisibility(0);
                if (localB.a.sourceDir == null) {
                  break label691;
                }
                localObject = new File(localB.a.sourceDir);
                paramAnonymousViewGroup.d.setText(DateUtils.formatDateTime(ApplicationManager.this, ((File)localObject).lastModified(), 21));
                label466:
                if (!ApplicationManager.c()) {
                  break label763;
                }
                paramAnonymousViewGroup.e.setVisibility(0);
                if (localB.e == null) {
                  break label716;
                }
                paramAnonymousViewGroup.e.setImageDrawable(localB.e);
                label500:
                paramAnonymousViewGroup.f.setTag(Integer.valueOf(paramAnonymousInt));
                paramAnonymousViewGroup.f.setChecked(localB.m);
                paramAnonymousViewGroup.f.setOnCheckedChangeListener(ApplicationManager.this.c);
                paramAnonymousViewGroup = paramAnonymousViewGroup.g;
                if (!localB.l) {
                  break label775;
                }
              }
            }
            break;
          }
        }
        label572:
        label598:
        label604:
        label655:
        label691:
        label704:
        label716:
        label763:
        label775:
        for (paramAnonymousInt = 0;; paramAnonymousInt = 8)
        {
          paramAnonymousViewGroup.setVisibility(paramAnonymousInt);
          return paramAnonymousView;
          localObject = localB.a.packageName;
          break;
          if (localB.m)
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
            paramAnonymousViewGroup.e.setImageDrawable(ApplicationManager.this.getPackageManager().getDefaultActivityIcon());
          }
          catch (Exception localException)
          {
            paramAnonymousViewGroup.e.setImageDrawable(null);
            Log.e(ApplicationManager.class.getName(), localException.getLocalizedMessage());
          }
          break label500;
          paramAnonymousViewGroup.e.setVisibility(8);
          break label500;
        }
      }
    };
    this.v.setAdapter(this.d);
    this.u.setOnRefreshListener(this);
    this.p.setImeOptions(3);
    this.p.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 3)
        {
          ApplicationManager.q(ApplicationManager.this).performClick();
          return true;
        }
        return false;
      }
    });
    this.p.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        paramAnonymousEditable = paramAnonymousEditable.toString();
        if (!ApplicationManager.n(ApplicationManager.this).equals(paramAnonymousEditable)) {
          ApplicationManager.a(ApplicationManager.this, paramAnonymousEditable);
        }
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
    try
    {
      o();
      return;
    }
    catch (Throwable paramBundle)
    {
      paramBundle.printStackTrace();
    }
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
    if (c.a(getPackageManager(), "com.android.settings.UsageStats") != null) {
      paramMenu.add(0, 106, 0, 2131560338).setIcon(17301578);
    }
    if (c.a(getPackageManager(), "com.android.settings.fuelgauge.PowerUsageSummary") != null) {
      paramMenu.add(0, 112, 0, 2131558558).setIcon(17301569);
    }
    paramMenu.add(0, 103, 0, 2131559918).setIcon(17301581);
    paramMenu.add(0, 101, 0, 2131559924).setIcon(17301580);
    paramMenu.add(0, 113, 0, 2131559072).setIcon(17301583);
    paramMenu.add(0, 110, 0, 2131559874).setIcon(17301577);
    return true;
  }
  
  protected void onDestroy()
  {
    if (this.x != null)
    {
      this.x.dismiss();
      this.x = null;
    }
    this.d.clear();
    this.A.a();
    if (this.H != null) {
      this.H.a();
    }
    this.I.clear();
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    case 110: 
      j();
      return true;
    case 101: 
      k();
      return true;
    case 106: 
      l();
      return true;
    case 102: 
      i();
      return true;
    case 1: 
      h();
      return true;
    case 103: 
      n();
      return true;
    case 112: 
      m();
      return true;
    }
    startActivity(new Intent(this, HiddenAppFindActivity.class));
    return true;
  }
  
  protected void onPause()
  {
    int n = 0;
    super.onPause();
    this.y = true;
    this.b.removeMessages(4);
    this.b.removeMessages(3);
    View localView;
    if (this.v != null)
    {
      this.R = this.v.getFirstVisiblePosition();
      localView = this.v.getChildAt(0);
      if (localView != null) {
        break label64;
      }
    }
    for (;;)
    {
      this.S = n;
      return;
      label64:
      n = localView.getTop();
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    this.y = false;
    this.i.setText(2131559176);
    this.N = c.a(this, "show_backup_state");
    this.O = c.a(this, "show_size");
    this.P = c.a(this, "show_date");
    Q = c.a(this, "show_icon");
  }
  
  protected void onStart()
  {
    super.onStart();
    f();
  }
  
  protected void onStop()
  {
    if (this.B != null)
    {
      this.B.a = true;
      this.B = null;
    }
    if (this.C != null)
    {
      this.C.a = true;
      this.C = null;
    }
    if (this.D != null)
    {
      this.D.a = true;
      this.D = null;
    }
    if (this.E != null)
    {
      this.E.a();
      this.E = null;
    }
    ((NotificationManager)getSystemService("notification")).cancel(2);
    super.onStop();
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (!paramBoolean) {}
    for (;;)
    {
      return;
      try
      {
        if ((!isFinishing()) && (!this.U))
        {
          this.U = true;
          View localView = getWindow().getDecorView().getRootView();
          if ((localView != null) && (org.test.flashtest.tutorial.d.a(this, 9)))
          {
            if (this.T == null)
            {
              this.T = new org.test.flashtest.tutorial.d(this, 9);
              this.T.a(new org.test.flashtest.tutorial.b()
              {
                public void a()
                {
                  ApplicationManager.r(ApplicationManager.this).b();
                  ApplicationManager.a(ApplicationManager.this, null);
                }
              });
            }
            if (!this.T.c())
            {
              this.T.a(localView);
              return;
            }
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public static final class AppSettings
    extends CommonPreferenceActivity
  {
    public AppSettings() {}
    
    void a()
    {
      findPreference("app_export_dir").setSummary(getIntent().getStringExtra("app_export_dir"));
    }
    
    void a(String paramString)
    {
      boolean bool = getIntent().getBooleanExtra(paramString, true);
      ((CheckBoxPreference)findPreference(paramString)).setChecked(bool);
    }
    
    void b()
    {
      int j = getIntent().getIntExtra("filter_app_type", 1);
      int i = 2131558527;
      if (j == 0) {
        i = 2131560197;
      }
      for (;;)
      {
        findPreference("filter_app_type").setSummary(i);
        return;
        if (j == 1) {
          i = 2131560345;
        }
      }
    }
    
    void c()
    {
      int i = getIntent().getIntExtra("default_tap_action", 0);
      Object localObject = null;
      switch (i)
      {
      }
      for (;;)
      {
        findPreference("default_tap_action").setSummary((CharSequence)localObject);
        return;
        localObject = getString(2131559354);
        continue;
        localObject = getString(2131559944);
        continue;
        localObject = getString(2131560020);
        continue;
        localObject = getString(2131558933);
        continue;
        localObject = getString(2131560096);
      }
    }
    
    void d()
    {
      int i = getIntent().getIntExtra("sort_order_type", 0);
      Object localObject = null;
      switch (i)
      {
      }
      for (;;)
      {
        findPreference("sort_order_type").setSummary((CharSequence)localObject);
        return;
        localObject = getString(2131559641);
        continue;
        localObject = getString(2131558673);
        continue;
        localObject = getString(2131558864);
        continue;
        localObject = getString(2131558602);
        continue;
        localObject = getString(2131560317);
        continue;
        localObject = getString(2131559275);
        continue;
        localObject = getString(2131558552);
      }
    }
    
    void e()
    {
      if (getIntent().getIntExtra("sort_direction", 1) == 1) {}
      for (String str = getString(2131558545);; str = getString(2131558930))
      {
        findPreference("sort_direction").setSummary(str);
        return;
      }
    }
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setTitle(getString(2131559867));
      setPreferenceScreen(getPreferenceManager().createPreferenceScreen(this));
      paramBundle = new ColorTitlePreferenceCategory(this);
      paramBundle.setTitle(2131559874);
      getPreferenceScreen().addPreference(paramBundle);
      Object localObject = new Preference(this);
      ((Preference)localObject).setKey("app_export_dir");
      ((Preference)localObject).setTitle(2131559006);
      paramBundle.addPreference((Preference)localObject);
      localObject = new Preference(this);
      ((Preference)localObject).setKey("filter_app_type");
      ((Preference)localObject).setTitle(2131559071);
      paramBundle.addPreference((Preference)localObject);
      localObject = new CheckBoxPreference(this);
      ((CheckBoxPreference)localObject).setKey("show_size");
      ((CheckBoxPreference)localObject).setTitle(2131560082);
      ((CheckBoxPreference)localObject).setSummary(2131560083);
      paramBundle.addPreference((Preference)localObject);
      localObject = new CheckBoxPreference(this);
      ((CheckBoxPreference)localObject).setKey("show_date");
      ((CheckBoxPreference)localObject).setTitle(2131560078);
      ((CheckBoxPreference)localObject).setSummary(2131560079);
      paramBundle.addPreference((Preference)localObject);
      localObject = new CheckBoxPreference(this);
      ((CheckBoxPreference)localObject).setKey("show_icon");
      ((CheckBoxPreference)localObject).setTitle(2131560080);
      ((CheckBoxPreference)localObject).setSummary(2131560081);
      paramBundle.addPreference((Preference)localObject);
      localObject = new CheckBoxPreference(this);
      ((CheckBoxPreference)localObject).setKey("show_backup_state");
      ((CheckBoxPreference)localObject).setTitle(2131560084);
      ((CheckBoxPreference)localObject).setSummary(2131560085);
      paramBundle.addPreference((Preference)localObject);
      localObject = new Preference(this);
      ((Preference)localObject).setKey("default_tap_action");
      ((Preference)localObject).setTitle(2131558909);
      paramBundle.addPreference((Preference)localObject);
      paramBundle = new ColorTitlePreferenceCategory(this);
      paramBundle.setTitle(2131560114);
      getPreferenceScreen().addPreference(paramBundle);
      localObject = new Preference(this);
      ((Preference)localObject).setKey("sort_order_type");
      ((Preference)localObject).setTitle(2131560120);
      paramBundle.addPreference((Preference)localObject);
      localObject = new Preference(this);
      ((Preference)localObject).setKey("sort_direction");
      ((Preference)localObject).setTitle(2131560119);
      paramBundle.addPreference((Preference)localObject);
      a();
      b();
      c();
      d();
      e();
      a("show_size");
      a("show_date");
      a("show_icon");
      a("show_backup_state");
      setResult(-1, getIntent());
    }
    
    public boolean onPreferenceTreeClick(final PreferenceScreen paramPreferenceScreen, Preference paramPreference)
    {
      int i = 0;
      paramPreferenceScreen = getIntent();
      Object localObject1;
      Object localObject2;
      Object localObject3;
      Object localObject4;
      Object localObject5;
      Object localObject6;
      if ("app_export_dir".equals(paramPreference.getKey()))
      {
        paramPreference = new LinearLayout(this);
        paramPreference.setOrientation(1);
        i = (int)w.a(this, 10.0F);
        int j = (int)w.a(this, 5.0F);
        localObject1 = new LinearLayout.LayoutParams(-1, -2);
        ((LinearLayout.LayoutParams)localObject1).setMargins(i, 0, i, 0);
        localObject2 = new LinearLayout.LayoutParams(-1, -2);
        ((LinearLayout.LayoutParams)localObject2).setMargins(i, j, i, 0);
        localObject3 = new EditText(this);
        ((EditText)localObject3).setText(paramPreferenceScreen.getStringExtra("app_export_dir"));
        i = 0;
        if (i < org.test.flashtest.a.d.al.size())
        {
          localObject4 = (File)org.test.flashtest.a.d.al.get(i);
          if (l.b((File)localObject4, Environment.getExternalStorageDirectory()))
          {
            localObject5 = new TextView(this);
            ((TextView)localObject5).setText(getString(2131559278));
            ((TextView)localObject5).setTextSize(2, 11.0F);
            ((TextView)localObject5).setTypeface(null, 1);
            paramPreference.addView((View)localObject5, (ViewGroup.LayoutParams)localObject2);
            ad.a((View)localObject5, this);
            localObject6 = new TextView(this);
            ((TextView)localObject6).setText(((File)localObject4).getAbsolutePath());
            ((TextView)localObject6).setTextSize(2, 11.0F);
            paramPreference.addView((View)localObject6, (ViewGroup.LayoutParams)localObject1);
            ad.a((View)localObject6, this);
            ((TextView)localObject5).setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView)
              {
                this.a.setText(this.b.getText().toString() + "/backups");
              }
            });
            ((TextView)localObject6).setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView)
              {
                this.a.setText(this.b.getText().toString() + "/backups");
              }
            });
          }
          for (;;)
          {
            i += 1;
            break;
            if (Build.VERSION.SDK_INT != 19)
            {
              localObject5 = new TextView(this);
              ((TextView)localObject5).setText(getString(2131559012));
              ((TextView)localObject5).setTextSize(2, 11.0F);
              ((TextView)localObject5).setTypeface(null, 1);
              paramPreference.addView((View)localObject5, (ViewGroup.LayoutParams)localObject1);
              ad.a((View)localObject5, this);
              localObject6 = new TextView(this);
              ((TextView)localObject6).setText(((File)localObject4).getAbsolutePath());
              ((TextView)localObject6).setTextSize(2, 11.0F);
              paramPreference.addView((View)localObject6, (ViewGroup.LayoutParams)localObject1);
              ad.a((View)localObject6, this);
              ((TextView)localObject5).setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  this.a.setText(this.b.getText().toString() + "/backups");
                }
              });
              ((TextView)localObject6).setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  this.a.setText(this.b.getText().toString() + "/backups");
                }
              });
            }
          }
        }
        paramPreference.addView((View)localObject3, new LinearLayout.LayoutParams(-1, -2));
        paramPreferenceScreen = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            Object localObject2 = this.a.getText().toString();
            Object localObject1 = localObject2;
            if (localObject2 != null)
            {
              localObject2 = ((String)localObject2).trim();
              localObject1 = localObject2;
              if (((String)localObject2).length() == 0) {
                localObject1 = null;
              }
            }
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = "/sdcard/backups/";
            }
            paramPreferenceScreen.putExtra("app_export_dir", (String)localObject2);
            paramAnonymousDialogInterface.dismiss();
            ApplicationManager.AppSettings.this.a();
          }
        };
        new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131559006).setPositiveButton(2131559687, paramPreferenceScreen).setNegativeButton(2131558641, null).setView(paramPreference).create().show();
        return true;
      }
      if ("filter_app_type".equals(paramPreference.getKey()))
      {
        paramPreference = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, final int paramAnonymousInt)
          {
            if (paramAnonymousInt == 0)
            {
              new org.test.flashtest.customview.roundcorner.a(ApplicationManager.AppSettings.this).setTitle(ApplicationManager.AppSettings.this.getString(2131560356)).setMessage(ApplicationManager.AppSettings.this.getString(2131560357)).setCancelable(false).setPositiveButton(2131559687, new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  paramAnonymous2DialogInterface.dismiss();
                  ApplicationManager.AppSettings.6.this.a.putExtra("filter_app_type", paramAnonymousInt);
                  ApplicationManager.AppSettings.this.b();
                }
              }).setNegativeButton(2131558641, new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  paramAnonymous2DialogInterface.dismiss();
                }
              }).create().show();
              paramAnonymousDialogInterface.dismiss();
              return;
            }
            paramAnonymousDialogInterface.dismiss();
            paramPreferenceScreen.putExtra("filter_app_type", paramAnonymousInt);
            ApplicationManager.AppSettings.this.b();
          }
        };
        localObject1 = new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131559071).setPositiveButton(2131558669, null);
        localObject2 = getText(2131560197);
        localObject3 = getText(2131560345);
        i = paramPreferenceScreen.getIntExtra("filter_app_type", 1);
        ((AlertDialog.Builder)localObject1).setSingleChoiceItems(new CharSequence[] { localObject2, localObject3 }, i, paramPreference).create().show();
        return true;
      }
      if ("show_size".equals(paramPreference.getKey()))
      {
        paramPreferenceScreen.putExtra("show_size", ((CheckBoxPreference)findPreference("show_size")).isChecked());
        return true;
      }
      if ("show_date".equals(paramPreference.getKey()))
      {
        paramPreferenceScreen.putExtra("show_date", ((CheckBoxPreference)findPreference("show_date")).isChecked());
        return true;
      }
      if ("show_icon".equals(paramPreference.getKey()))
      {
        paramPreferenceScreen.putExtra("show_icon", ((CheckBoxPreference)findPreference("show_icon")).isChecked());
        return true;
      }
      if ("show_backup_state".equals(paramPreference.getKey()))
      {
        paramPreferenceScreen.putExtra("show_backup_state", ((CheckBoxPreference)findPreference("show_backup_state")).isChecked());
        return true;
      }
      if ("default_tap_action".equals(paramPreference.getKey()))
      {
        paramPreference = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramPreferenceScreen.putExtra("default_tap_action", paramAnonymousInt);
            paramAnonymousDialogInterface.dismiss();
            ApplicationManager.AppSettings.this.c();
          }
        };
        localObject1 = new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131558909).setPositiveButton(2131558669, null);
        localObject2 = getString(2131560096);
        localObject3 = getString(2131559354);
        localObject4 = getString(2131559944);
        localObject5 = getString(2131560020);
        localObject6 = getString(2131558933);
        i = paramPreferenceScreen.getIntExtra("default_tap_action", 0);
        ((AlertDialog.Builder)localObject1).setSingleChoiceItems(new String[] { localObject2, localObject3, localObject4, localObject5, localObject6 }, i, paramPreference).create().show();
        return true;
      }
      if ("sort_order_type".equals(paramPreference.getKey()))
      {
        paramPreference = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramPreferenceScreen.putExtra("sort_order_type", paramAnonymousInt);
            paramAnonymousDialogInterface.dismiss();
            ApplicationManager.AppSettings.this.d();
          }
        };
        localObject1 = new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131560120).setPositiveButton(2131558669, null);
        localObject2 = getString(2131559641);
        localObject3 = getString(2131558673);
        localObject4 = getString(2131558864);
        localObject5 = getString(2131558602);
        localObject6 = getString(2131560317);
        String str1 = getString(2131559275);
        String str2 = getString(2131558552);
        i = paramPreferenceScreen.getIntExtra("sort_order_type", 0);
        ((AlertDialog.Builder)localObject1).setSingleChoiceItems(new String[] { localObject2, localObject3, localObject4, localObject5, localObject6, str1, str2 }, i, paramPreference).create().show();
        return true;
      }
      if ("sort_direction".equals(paramPreference.getKey()))
      {
        paramPreference = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            Intent localIntent = paramPreferenceScreen;
            if (paramAnonymousInt == 0) {}
            for (paramAnonymousInt = 1;; paramAnonymousInt = -1)
            {
              localIntent.putExtra("sort_direction", paramAnonymousInt);
              paramAnonymousDialogInterface.dismiss();
              ApplicationManager.AppSettings.this.e();
              return;
            }
          }
        };
        localObject1 = new org.test.flashtest.customview.roundcorner.a(this).setTitle(2131560119).setPositiveButton(2131558669, null);
        localObject2 = getString(2131558545);
        localObject3 = getString(2131558930);
        if (paramPreferenceScreen.getIntExtra("sort_direction", 1) == 1) {}
        for (;;)
        {
          ((AlertDialog.Builder)localObject1).setSingleChoiceItems(new String[] { localObject2, localObject3 }, i, paramPreference).create().show();
          return true;
          i = 1;
        }
      }
      return false;
    }
  }
  
  private static final class a
  {
    ArrayList<ApplicationManager.b> a = new ArrayList();
    HashMap<String, ApplicationManager.b> b = new HashMap();
    
    a() {}
    
    void a()
    {
      try
      {
        this.a.clear();
        this.b.clear();
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
    
    void a(int paramInt1, final int paramInt2)
    {
      switch (paramInt1)
      {
      }
      for (;;)
      {
        return;
        try
        {
          Collections.sort(this.a, new ApplicationManager.e(paramInt2));
          continue;
        }
        finally {}
        Collections.sort(this.a, new ApplicationManager.j(paramInt1, paramInt2));
        continue;
        Collections.sort(this.a, new Comparator()
        {
          public int a(ApplicationManager.b paramAnonymousB1, ApplicationManager.b paramAnonymousB2)
          {
            long l2 = 0L;
            if (paramAnonymousB1.a.sourceDir != null) {}
            for (long l1 = new File(paramAnonymousB1.a.sourceDir).lastModified();; l1 = 0L)
            {
              if (paramAnonymousB2.a.sourceDir != null) {
                l2 = new File(paramAnonymousB2.a.sourceDir).lastModified();
              }
              int i;
              if (l1 == l2) {
                i = 0;
              }
              for (;;)
              {
                return i * paramInt2;
                if (l1 < l2) {
                  i = -1;
                } else {
                  i = 1;
                }
              }
            }
          }
        });
        continue;
        Collections.sort(this.a, new Comparator()
        {
          public int a(ApplicationManager.b paramAnonymousB1, ApplicationManager.b paramAnonymousB2)
          {
            return (paramAnonymousB1.j - paramAnonymousB2.j) * paramInt2;
          }
        });
      }
    }
    
    void a(ArrayList<ApplicationManager.b> paramArrayList)
    {
      for (;;)
      {
        int i;
        try
        {
          this.a.retainAll(paramArrayList);
          int j = paramArrayList.size();
          i = 0;
          if (i < j)
          {
            Object localObject = (ApplicationManager.b)paramArrayList.get(i);
            ApplicationManager.b localB = (ApplicationManager.b)this.b.get(((ApplicationManager.b)localObject).a.packageName);
            if (localB == null)
            {
              this.b.put(((ApplicationManager.b)localObject).a.packageName, localObject);
              if (!this.a.contains(localObject)) {
                this.a.add(localObject);
              }
            }
            else
            {
              localB.a = ((ApplicationManager.b)localObject).a;
              localB.c = ((ApplicationManager.b)localObject).c;
              localB.l = ((ApplicationManager.b)localObject).l;
              localB.m = ((ApplicationManager.b)localObject).m;
              localB.k = ((ApplicationManager.b)localObject).k;
              localObject = localB;
              continue;
            }
          }
          else
          {
            return;
          }
        }
        finally {}
        i += 1;
      }
    }
    
    ArrayList<ApplicationManager.b> b()
    {
      try
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(this.a);
        return localArrayList;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
  }
  
  private static final class b
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
    
    b() {}
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof b)) {
        return false;
      }
      paramObject = (b)paramObject;
      return this.a.packageName.equals(paramObject.a.packageName);
    }
  }
  
  class c
  {
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    ImageView e;
    CheckBox f;
    ImageView g;
    
    c() {}
  }
  
  private static final class d
    extends Thread
  {
    volatile boolean a;
    private Activity b;
    private List<ApplicationManager.b> c;
    private ApplicationManager.a d;
    private Handler e;
    
    d(Activity paramActivity, List<ApplicationManager.b> paramList, ApplicationManager.a paramA, Handler paramHandler)
    {
      super();
      this.b = paramActivity;
      this.c = paramList;
      this.d = paramA;
      this.e = paramHandler;
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 28	org/codein/app/ApplicationManager$d:c	Ljava/util/List;
      //   4: ifnonnull +14 -> 18
      //   7: aload_0
      //   8: aload_0
      //   9: getfield 30	org/codein/app/ApplicationManager$d:d	Lorg/codein/app/ApplicationManager$a;
      //   12: invokevirtual 48	org/codein/app/ApplicationManager$a:b	()Ljava/util/ArrayList;
      //   15: putfield 28	org/codein/app/ApplicationManager$d:c	Ljava/util/List;
      //   18: aload_0
      //   19: getfield 28	org/codein/app/ApplicationManager$d:c	Ljava/util/List;
      //   22: ifnull +15 -> 37
      //   25: aload_0
      //   26: getfield 28	org/codein/app/ApplicationManager$d:c	Ljava/util/List;
      //   29: invokeinterface 54 1 0
      //   34: ifne +4 -> 38
      //   37: return
      //   38: new 56	java/io/File
      //   41: dup
      //   42: aload_0
      //   43: getfield 26	org/codein/app/ApplicationManager$d:b	Landroid/app/Activity;
      //   46: ldc 58
      //   48: ldc 60
      //   50: invokestatic 65	org/codein/app/c:a	(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   53: invokespecial 66	java/io/File:<init>	(Ljava/lang/String;)V
      //   56: astore 5
      //   58: aload 5
      //   60: invokevirtual 70	java/io/File:exists	()Z
      //   63: ifeq +501 -> 564
      //   66: new 56	java/io/File
      //   69: dup
      //   70: aload 5
      //   72: ldc 72
      //   74: invokespecial 75	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   77: astore 4
      //   79: aload 4
      //   81: astore_3
      //   82: aload 4
      //   84: invokevirtual 70	java/io/File:exists	()Z
      //   87: ifne +5 -> 92
      //   90: aconst_null
      //   91: astore_3
      //   92: new 56	java/io/File
      //   95: dup
      //   96: aload 5
      //   98: ldc 77
      //   100: invokespecial 75	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   103: astore 5
      //   105: aload 5
      //   107: invokevirtual 70	java/io/File:exists	()Z
      //   110: ifne +445 -> 555
      //   113: aconst_null
      //   114: astore 5
      //   116: aload_3
      //   117: astore 4
      //   119: aload 5
      //   121: astore_3
      //   122: aload_0
      //   123: getfield 26	org/codein/app/ApplicationManager$d:b	Landroid/app/Activity;
      //   126: invokevirtual 83	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
      //   129: astore 6
      //   131: aload_0
      //   132: getfield 28	org/codein/app/ApplicationManager$d:c	Ljava/util/List;
      //   135: invokeinterface 54 1 0
      //   140: istore_2
      //   141: iconst_0
      //   142: istore_1
      //   143: iload_1
      //   144: iload_2
      //   145: if_icmpge +328 -> 473
      //   148: aload_0
      //   149: getfield 85	org/codein/app/ApplicationManager$d:a	Z
      //   152: ifne -115 -> 37
      //   155: aload_0
      //   156: getfield 28	org/codein/app/ApplicationManager$d:c	Ljava/util/List;
      //   159: iload_1
      //   160: invokeinterface 89 2 0
      //   165: checkcast 91	org/codein/app/ApplicationManager$b
      //   168: getfield 94	org/codein/app/ApplicationManager$b:a	Landroid/content/pm/ApplicationInfo;
      //   171: astore 8
      //   173: aload_0
      //   174: getfield 30	org/codein/app/ApplicationManager$d:d	Lorg/codein/app/ApplicationManager$a;
      //   177: getfield 97	org/codein/app/ApplicationManager$a:b	Ljava/util/HashMap;
      //   180: aload 8
      //   182: getfield 103	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   185: invokevirtual 108	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   188: checkcast 91	org/codein/app/ApplicationManager$b
      //   191: astore 7
      //   193: aload 7
      //   195: ifnull +141 -> 336
      //   198: aload 8
      //   200: getfield 112	android/content/pm/ApplicationInfo:flags	I
      //   203: iconst_1
      //   204: iand
      //   205: ifeq +344 -> 549
      //   208: aload 4
      //   210: astore 5
      //   212: aload 5
      //   214: ifnull +232 -> 446
      //   217: aload 8
      //   219: getfield 115	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
      //   222: astore 9
      //   224: aload 9
      //   226: ifnull +220 -> 446
      //   229: aload 9
      //   231: invokestatic 118	org/codein/app/ApplicationManager:a	(Ljava/lang/String;)Ljava/lang/String;
      //   234: ifnull +212 -> 446
      //   237: aload 8
      //   239: getfield 103	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   242: ifnull +204 -> 446
      //   245: new 56	java/io/File
      //   248: dup
      //   249: aload 5
      //   251: new 120	java/lang/StringBuilder
      //   254: dup
      //   255: invokespecial 122	java/lang/StringBuilder:<init>	()V
      //   258: aload 8
      //   260: getfield 103	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   263: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   266: ldc -128
      //   268: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   271: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   274: invokespecial 75	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   277: astore 5
      //   279: aload 5
      //   281: invokevirtual 70	java/io/File:exists	()Z
      //   284: ifeq +162 -> 446
      //   287: aload 6
      //   289: aload 5
      //   291: invokevirtual 135	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   294: iconst_0
      //   295: invokevirtual 141	android/content/pm/PackageManager:getPackageArchiveInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   298: astore 5
      //   300: aload 5
      //   302: ifnull +144 -> 446
      //   305: aload_0
      //   306: getfield 30	org/codein/app/ApplicationManager$d:d	Lorg/codein/app/ApplicationManager$a;
      //   309: astore 8
      //   311: aload 8
      //   313: monitorenter
      //   314: aload 5
      //   316: getfield 146	android/content/pm/PackageInfo:versionCode	I
      //   319: aload 7
      //   321: getfield 149	org/codein/app/ApplicationManager$b:k	I
      //   324: if_icmpge +85 -> 409
      //   327: aload 7
      //   329: iconst_1
      //   330: putfield 152	org/codein/app/ApplicationManager$b:j	I
      //   333: aload 8
      //   335: monitorexit
      //   336: iload_1
      //   337: iconst_1
      //   338: iadd
      //   339: istore_1
      //   340: goto -197 -> 143
      //   343: astore 5
      //   345: invokestatic 157	org/test/flashtest/a/d:a	()Lorg/test/flashtest/a/d;
      //   348: getfield 160	org/test/flashtest/a/d:af	Z
      //   351: ifeq +8 -> 359
      //   354: aload 5
      //   356: invokevirtual 163	java/lang/Exception:printStackTrace	()V
      //   359: aconst_null
      //   360: astore 5
      //   362: goto -62 -> 300
      //   365: astore 5
      //   367: invokestatic 157	org/test/flashtest/a/d:a	()Lorg/test/flashtest/a/d;
      //   370: getfield 160	org/test/flashtest/a/d:af	Z
      //   373: ifeq +8 -> 381
      //   376: aload 5
      //   378: invokevirtual 164	java/lang/NoSuchMethodError:printStackTrace	()V
      //   381: aconst_null
      //   382: astore 5
      //   384: goto -84 -> 300
      //   387: astore 5
      //   389: invokestatic 157	org/test/flashtest/a/d:a	()Lorg/test/flashtest/a/d;
      //   392: getfield 160	org/test/flashtest/a/d:af	Z
      //   395: ifeq +8 -> 403
      //   398: aload 5
      //   400: invokevirtual 165	java/lang/NoSuchFieldError:printStackTrace	()V
      //   403: aconst_null
      //   404: astore 5
      //   406: goto -106 -> 300
      //   409: aload 5
      //   411: getfield 146	android/content/pm/PackageInfo:versionCode	I
      //   414: aload 7
      //   416: getfield 149	org/codein/app/ApplicationManager$b:k	I
      //   419: if_icmpne +18 -> 437
      //   422: aload 7
      //   424: iconst_2
      //   425: putfield 152	org/codein/app/ApplicationManager$b:j	I
      //   428: goto -95 -> 333
      //   431: astore_3
      //   432: aload 8
      //   434: monitorexit
      //   435: aload_3
      //   436: athrow
      //   437: aload 7
      //   439: iconst_3
      //   440: putfield 152	org/codein/app/ApplicationManager$b:j	I
      //   443: goto -110 -> 333
      //   446: aload_0
      //   447: getfield 30	org/codein/app/ApplicationManager$d:d	Lorg/codein/app/ApplicationManager$a;
      //   450: astore 5
      //   452: aload 5
      //   454: monitorenter
      //   455: aload 7
      //   457: iconst_0
      //   458: putfield 152	org/codein/app/ApplicationManager$b:j	I
      //   461: aload 5
      //   463: monitorexit
      //   464: goto -128 -> 336
      //   467: astore_3
      //   468: aload 5
      //   470: monitorexit
      //   471: aload_3
      //   472: athrow
      //   473: aload_0
      //   474: getfield 26	org/codein/app/ApplicationManager$d:b	Landroid/app/Activity;
      //   477: ldc -89
      //   479: iconst_0
      //   480: invokestatic 170	org/codein/app/c:a	(Landroid/app/Activity;Ljava/lang/String;I)I
      //   483: bipush 6
      //   485: if_icmpne +43 -> 528
      //   488: aload_0
      //   489: getfield 30	org/codein/app/ApplicationManager$d:d	Lorg/codein/app/ApplicationManager$a;
      //   492: bipush 6
      //   494: aload_0
      //   495: getfield 26	org/codein/app/ApplicationManager$d:b	Landroid/app/Activity;
      //   498: ldc -84
      //   500: iconst_1
      //   501: invokestatic 170	org/codein/app/c:a	(Landroid/app/Activity;Ljava/lang/String;I)I
      //   504: invokevirtual 175	org/codein/app/ApplicationManager$a:a	(II)V
      //   507: aload_0
      //   508: getfield 32	org/codein/app/ApplicationManager$d:e	Landroid/os/Handler;
      //   511: aload_0
      //   512: getfield 32	org/codein/app/ApplicationManager$d:e	Landroid/os/Handler;
      //   515: sipush 207
      //   518: iconst_1
      //   519: iconst_0
      //   520: invokevirtual 181	android/os/Handler:obtainMessage	(III)Landroid/os/Message;
      //   523: invokevirtual 185	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   526: pop
      //   527: return
      //   528: aload_0
      //   529: getfield 32	org/codein/app/ApplicationManager$d:e	Landroid/os/Handler;
      //   532: aload_0
      //   533: getfield 32	org/codein/app/ApplicationManager$d:e	Landroid/os/Handler;
      //   536: sipush 207
      //   539: iconst_0
      //   540: iconst_0
      //   541: invokevirtual 181	android/os/Handler:obtainMessage	(III)Landroid/os/Message;
      //   544: invokevirtual 185	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   547: pop
      //   548: return
      //   549: aload_3
      //   550: astore 5
      //   552: goto -340 -> 212
      //   555: aload_3
      //   556: astore 4
      //   558: aload 5
      //   560: astore_3
      //   561: goto -439 -> 122
      //   564: aconst_null
      //   565: astore_3
      //   566: aconst_null
      //   567: astore 4
      //   569: goto -447 -> 122
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	572	0	this	d
      //   142	198	1	i	int
      //   140	6	2	j	int
      //   81	41	3	localObject1	Object
      //   431	5	3	localObject2	Object
      //   467	89	3	localObject3	Object
      //   560	6	3	localObject4	Object
      //   77	491	4	localObject5	Object
      //   56	259	5	localObject6	Object
      //   343	12	5	localException	Exception
      //   360	1	5	localObject7	Object
      //   365	12	5	localNoSuchMethodError	NoSuchMethodError
      //   382	1	5	localObject8	Object
      //   387	12	5	localNoSuchFieldError	NoSuchFieldError
      //   129	159	6	localPackageManager	PackageManager
      //   191	265	7	localB	ApplicationManager.b
      //   222	8	9	str	String
      // Exception table:
      //   from	to	target	type
      //   287	300	343	java/lang/Exception
      //   287	300	365	java/lang/NoSuchMethodError
      //   287	300	387	java/lang/NoSuchFieldError
      //   314	333	431	finally
      //   333	336	431	finally
      //   409	428	431	finally
      //   432	435	431	finally
      //   437	443	431	finally
      //   455	464	467	finally
      //   468	471	467	finally
    }
  }
  
  private static final class e
    implements Comparator<ApplicationManager.b>
  {
    Collator a = Collator.getInstance();
    int b;
    
    e(int paramInt)
    {
      this.b = paramInt;
    }
    
    public int a(ApplicationManager.b paramB1, ApplicationManager.b paramB2)
    {
      if (paramB1.b == null)
      {
        paramB1 = paramB1.a.packageName;
        if (paramB2.b != null) {
          break label58;
        }
      }
      label58:
      for (paramB2 = paramB2.a.packageName;; paramB2 = paramB2.b.toString())
      {
        return this.a.compare(paramB1, paramB2) * this.b;
        paramB1 = paramB1.b.toString();
        break;
      }
    }
  }
  
  private static final class f
    extends IPackageStatsObserver.Stub
  {
    private CountDownLatch a;
    private Activity b;
    private ApplicationManager.a c;
    
    f(CountDownLatch paramCountDownLatch, Activity paramActivity, ApplicationManager.a paramA)
    {
      this.a = paramCountDownLatch;
      this.b = paramActivity;
      this.c = paramA;
    }
    
    void a(String paramString, PackageManager paramPackageManager)
    {
      if (ApplicationManager.a != null) {}
      try
      {
        ApplicationManager.a.invoke(paramPackageManager, new Object[] { paramString, this });
        return;
      }
      catch (Exception paramString)
      {
        Log.e(ApplicationManager.class.getName(), paramString.getLocalizedMessage(), paramString);
      }
    }
    
    /* Error */
    public void onGetStatsCompleted(android.content.pm.PackageStats paramPackageStats, boolean paramBoolean)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 24	org/codein/app/ApplicationManager$f:c	Lorg/codein/app/ApplicationManager$a;
      //   4: getfield 61	org/codein/app/ApplicationManager$a:b	Ljava/util/HashMap;
      //   7: aload_1
      //   8: getfield 67	android/content/pm/PackageStats:packageName	Ljava/lang/String;
      //   11: invokevirtual 73	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   14: checkcast 75	org/codein/app/ApplicationManager$b
      //   17: astore 4
      //   19: aload 4
      //   21: ifnull +224 -> 245
      //   24: aload_0
      //   25: getfield 24	org/codein/app/ApplicationManager$f:c	Lorg/codein/app/ApplicationManager$a;
      //   28: astore_3
      //   29: aload_3
      //   30: monitorenter
      //   31: aload 4
      //   33: ldc 77
      //   35: putfield 79	org/codein/app/ApplicationManager$b:f	Ljava/lang/String;
      //   38: aload_1
      //   39: getfield 83	android/content/pm/PackageStats:codeSize	J
      //   42: lconst_0
      //   43: lcmp
      //   44: ifle +209 -> 253
      //   47: aload 4
      //   49: new 85	java/lang/StringBuilder
      //   52: dup
      //   53: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   56: aload 4
      //   58: getfield 79	org/codein/app/ApplicationManager$b:f	Ljava/lang/String;
      //   61: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   64: aload_0
      //   65: getfield 22	org/codein/app/ApplicationManager$f:b	Landroid/app/Activity;
      //   68: aload_1
      //   69: getfield 83	android/content/pm/PackageStats:codeSize	J
      //   72: invokestatic 96	android/text/format/Formatter:formatFileSize	(Landroid/content/Context;J)Ljava/lang/String;
      //   75: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   78: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   81: putfield 79	org/codein/app/ApplicationManager$b:f	Ljava/lang/String;
      //   84: aload 4
      //   86: getfield 79	org/codein/app/ApplicationManager$b:f	Ljava/lang/String;
      //   89: invokestatic 105	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   92: ifeq +17 -> 109
      //   95: aload 4
      //   97: aload_0
      //   98: getfield 22	org/codein/app/ApplicationManager$f:b	Landroid/app/Activity;
      //   101: ldc 106
      //   103: invokevirtual 112	android/app/Activity:getString	(I)Ljava/lang/String;
      //   106: putfield 79	org/codein/app/ApplicationManager$b:f	Ljava/lang/String;
      //   109: aload_1
      //   110: getfield 115	android/content/pm/PackageStats:dataSize	J
      //   113: lconst_0
      //   114: lcmp
      //   115: ifle +45 -> 160
      //   118: aload 4
      //   120: new 85	java/lang/StringBuilder
      //   123: dup
      //   124: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   127: aload 4
      //   129: getfield 79	org/codein/app/ApplicationManager$b:f	Ljava/lang/String;
      //   132: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   135: ldc 117
      //   137: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   140: aload_0
      //   141: getfield 22	org/codein/app/ApplicationManager$f:b	Landroid/app/Activity;
      //   144: aload_1
      //   145: getfield 115	android/content/pm/PackageStats:dataSize	J
      //   148: invokestatic 96	android/text/format/Formatter:formatFileSize	(Landroid/content/Context;J)Ljava/lang/String;
      //   151: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   154: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   157: putfield 79	org/codein/app/ApplicationManager$b:f	Ljava/lang/String;
      //   160: aload_1
      //   161: getfield 120	android/content/pm/PackageStats:cacheSize	J
      //   164: lconst_0
      //   165: lcmp
      //   166: ifle +50 -> 216
      //   169: aload 4
      //   171: new 85	java/lang/StringBuilder
      //   174: dup
      //   175: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   178: aload 4
      //   180: getfield 79	org/codein/app/ApplicationManager$b:f	Ljava/lang/String;
      //   183: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   186: ldc 122
      //   188: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   191: aload_0
      //   192: getfield 22	org/codein/app/ApplicationManager$f:b	Landroid/app/Activity;
      //   195: aload_1
      //   196: getfield 120	android/content/pm/PackageStats:cacheSize	J
      //   199: invokestatic 96	android/text/format/Formatter:formatFileSize	(Landroid/content/Context;J)Ljava/lang/String;
      //   202: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   205: ldc 124
      //   207: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   210: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   213: putfield 79	org/codein/app/ApplicationManager$b:f	Ljava/lang/String;
      //   216: aload 4
      //   218: aload_1
      //   219: getfield 83	android/content/pm/PackageStats:codeSize	J
      //   222: putfield 127	org/codein/app/ApplicationManager$b:g	J
      //   225: aload 4
      //   227: aload_1
      //   228: getfield 115	android/content/pm/PackageStats:dataSize	J
      //   231: putfield 130	org/codein/app/ApplicationManager$b:h	J
      //   234: aload 4
      //   236: aload_1
      //   237: getfield 120	android/content/pm/PackageStats:cacheSize	J
      //   240: putfield 133	org/codein/app/ApplicationManager$b:i	J
      //   243: aload_3
      //   244: monitorexit
      //   245: aload_0
      //   246: getfield 20	org/codein/app/ApplicationManager$f:a	Ljava/util/concurrent/CountDownLatch;
      //   249: invokevirtual 138	java/util/concurrent/CountDownLatch:countDown	()V
      //   252: return
      //   253: aload 4
      //   255: aload_0
      //   256: getfield 22	org/codein/app/ApplicationManager$f:b	Landroid/app/Activity;
      //   259: ldc 106
      //   261: invokevirtual 112	android/app/Activity:getString	(I)Ljava/lang/String;
      //   264: putfield 79	org/codein/app/ApplicationManager$b:f	Ljava/lang/String;
      //   267: goto -158 -> 109
      //   270: astore_1
      //   271: aload_3
      //   272: monitorexit
      //   273: aload_1
      //   274: athrow
      //   275: astore_1
      //   276: aload_0
      //   277: getfield 20	org/codein/app/ApplicationManager$f:a	Ljava/util/concurrent/CountDownLatch;
      //   280: invokevirtual 138	java/util/concurrent/CountDownLatch:countDown	()V
      //   283: aload_1
      //   284: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	285	0	this	f
      //   0	285	1	paramPackageStats	android.content.pm.PackageStats
      //   0	285	2	paramBoolean	boolean
      //   17	237	4	localB	ApplicationManager.b
      // Exception table:
      //   from	to	target	type
      //   31	109	270	finally
      //   109	160	270	finally
      //   160	216	270	finally
      //   216	245	270	finally
      //   253	267	270	finally
      //   271	273	270	finally
      //   24	31	275	finally
      //   273	275	275	finally
    }
  }
  
  private static final class g
    extends Thread
  {
    volatile boolean a;
    private Activity b;
    private ApplicationManager.a c;
    private Handler d;
    
    g(Activity paramActivity, ApplicationManager.a paramA, Handler paramHandler)
    {
      super();
      this.b = paramActivity;
      this.c = paramA;
      this.d = paramHandler;
    }
    
    public void run()
    {
      PackageManager localPackageManager = this.b.getPackageManager();
      ArrayList localArrayList = this.c.b();
      int n = localArrayList.size();
      int i = n / 32;
      if (i * 32 < n) {
        i += 1;
      }
      for (;;)
      {
        int j = 0;
        int k;
        CountDownLatch localCountDownLatch;
        ApplicationManager.f localF;
        int m;
        if (j < i)
        {
          if ((j + 1) * 32 <= n) {
            break label116;
          }
          k = n - j * 32;
          localCountDownLatch = new CountDownLatch(k);
          localF = new ApplicationManager.f(localCountDownLatch, this.b, this.c);
          m = 0;
        }
        for (;;)
        {
          if (m >= k) {
            break label159;
          }
          if (this.a)
          {
            return;
            label116:
            k = 32;
            break;
          }
          localF.a(((ApplicationManager.b)localArrayList.get(j * 32 + m)).a.packageName, localPackageManager);
          m += 1;
        }
        for (;;)
        {
          try
          {
            label159:
            localCountDownLatch.await();
            if (j != i - 1) {
              break label266;
            }
            k = c.a(this.b, "sort_order_type", 0);
            if ((k != 1) && (k != 2) && (k != 3) && (k != 4)) {
              break label266;
            }
            this.c.a(k, c.a(this.b, "sort_direction", 1));
            this.d.sendMessage(this.d.obtainMessage(204, 1, 0));
            return;
          }
          catch (InterruptedException localInterruptedException)
          {
            Log.e(ApplicationManager.class.getName(), localInterruptedException.getLocalizedMessage(), localInterruptedException);
            j += 1;
          }
          break;
          label266:
          this.d.sendMessage(this.d.obtainMessage(204, 0, 0));
        }
      }
    }
  }
  
  private static final class h
    extends Thread
  {
    volatile boolean a;
    private Activity b;
    private ApplicationManager.a c;
    private Handler d;
    
    h(Activity paramActivity, ApplicationManager.a paramA, Handler paramHandler)
    {
      super();
      this.b = paramActivity;
      this.c = paramA;
      this.d = paramHandler;
    }
    
    public void run()
    {
      int j = 0;
      PackageManager localPackageManager1 = this.b.getPackageManager();
      ArrayList localArrayList = this.c.b();
      int k = localArrayList.size();
      int i = 0;
      for (;;)
      {
        if (i < k) {
          if (!this.a) {}
        }
        Object localObject1;
        label279:
        for (;;)
        {
          return;
          ??? = ((ApplicationManager.b)localArrayList.get(i)).a;
          localObject1 = ((ApplicationInfo)???).loadLabel(localPackageManager1);
          ApplicationManager.b localB = (ApplicationManager.b)this.c.b.get(((ApplicationInfo)???).packageName);
          if (localB == null) {
            break label332;
          }
          synchronized (this.c)
          {
            localB.b = ((CharSequence)localObject1);
          }
          if (c.a(this.b, "sort_order_type", 0) == 0)
          {
            this.c.a(0, c.a(this.b, "sort_direction", 1));
            this.d.sendMessage(this.d.obtainMessage(205, 1, 0));
          }
          for (;;)
          {
            if (!ApplicationManager.c()) {
              break label279;
            }
            k = localArrayList.size();
            i = j;
            for (;;)
            {
              if (i >= k) {
                break label320;
              }
              if (this.a) {
                break;
              }
              localObject1 = ((ApplicationManager.b)localArrayList.get(i)).a;
              try
              {
                ??? = ((ApplicationInfo)localObject1).loadIcon(localPackageManager2);
                localB = (ApplicationManager.b)this.c.b.get(((ApplicationInfo)localObject1).packageName);
                if (localB != null) {
                  localB.e = ((Drawable)???);
                }
              }
              catch (OutOfMemoryError localOutOfMemoryError)
              {
                for (;;)
                {
                  Log.e(ApplicationManager.class.getName(), "OOM when loading icon: " + ((ApplicationInfo)localObject1).packageName, localOutOfMemoryError);
                }
              }
              i += 1;
            }
            this.d.sendMessage(this.d.obtainMessage(205, 0, 0));
          }
        }
        label320:
        this.d.sendEmptyMessage(206);
        return;
        label332:
        i += 1;
      }
    }
  }
  
  class i
    extends CommonTask<Void, Void, Boolean>
  {
    private final boolean b = false;
    private boolean c = false;
    private String d = "";
    private Vector<Integer> e = new Vector();
    
    public i(String paramString)
    {
      this.d = paramString;
    }
    
    private boolean c()
    {
      return (this.c) || (isCancelled());
    }
    
    protected Boolean a(Void... paramVarArgs)
    {
      if (isCancelled()) {
        return Boolean.valueOf(true);
      }
      for (;;)
      {
        try
        {
          String str = this.d.toLowerCase();
          if ((str.length() > 0) && (ApplicationManager.this.d != null))
          {
            i = 0;
            if (i < ApplicationManager.this.d.getCount())
            {
              paramVarArgs = (ApplicationManager.b)ApplicationManager.this.d.getItem(i);
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
        bool = ApplicationManager.this.isFinishing();
        if (bool) {
          return;
        }
        if (this.e.size() > 0)
        {
          ApplicationManager.l(ApplicationManager.this).clear();
          ApplicationManager.l(ApplicationManager.this).addAll(this.e);
          ApplicationManager.a(ApplicationManager.this, 0);
          ApplicationManager.this.d.notifyDataSetChanged();
          ApplicationManager.b(ApplicationManager.this).setSelection(((Integer)ApplicationManager.l(ApplicationManager.this).get(ApplicationManager.t(ApplicationManager.this))).intValue());
        }
        this.e.clear();
        ApplicationManager.a(ApplicationManager.this, true);
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
  
  private static final class j
    implements Comparator<ApplicationManager.b>
  {
    int a;
    int b;
    
    j(int paramInt1, int paramInt2)
    {
      this.a = paramInt1;
      this.b = paramInt2;
    }
    
    public int a(ApplicationManager.b paramB1, ApplicationManager.b paramB2)
    {
      int j = 0;
      int k = 0;
      int m = 0;
      int i = 0;
      switch (this.a)
      {
      default: 
        return 0;
      case 1: 
        if (paramB1.g == paramB2.g) {}
        for (;;)
        {
          return i * this.b;
          if (paramB1.g < paramB2.g) {
            i = -1;
          } else {
            i = 1;
          }
        }
      case 2: 
        if (paramB1.h == paramB2.h) {
          i = j;
        }
        for (;;)
        {
          return i * this.b;
          if (paramB1.h < paramB2.h) {
            i = -1;
          } else {
            i = 1;
          }
        }
      case 3: 
        if (paramB1.i == paramB2.i) {
          i = k;
        }
        for (;;)
        {
          return i * this.b;
          if (paramB1.i < paramB2.i) {
            i = -1;
          } else {
            i = 1;
          }
        }
      }
      long l1 = paramB1.g + paramB1.h + paramB1.i;
      long l2 = paramB2.g + paramB2.h + paramB2.i;
      if (l1 == l2) {
        i = m;
      }
      for (;;)
      {
        return i * this.b;
        if (l1 < l2) {
          i = -1;
        } else {
          i = 1;
        }
      }
    }
  }
}
