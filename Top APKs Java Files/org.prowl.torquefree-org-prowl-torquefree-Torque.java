package org.prowl.torquefree;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.google.android.gms.ads.AdView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.Vector;
import o.ċ;
import o.ĩ;
import o.ɽ;
import o.ʃ;
import o.ʌ;
import o.Ξ;
import o.ς;
import o.ϛ;
import o.ч;
import o.э;
import o.ѓ;
import o.װ;
import o.ٲ;
import o.ە;
import o.ᐸ;
import o.ᒉ;
import o.ᒦ;
import o.ᒨ;
import o.ᒫ;
import o.ᓚ;
import o.ᖟ;
import o.ᴧ;
import o.ᴳ;
import o.ᴼ;
import o.ᵣ;
import o.ᵣ.if;
import o.ᵦ;
import o.ἵ;
import o.〵;
import o.יִ;
import o.ﺬ;
import o.ﺮ;
import o.ﻣ;
import o.Ｌ;
import o.ｃ;
import o.ｧ;
import o.ﾕ;
import org.prowl.torquefree.pid.PID;
import org.prowl.torquefree.productpage.ProductPage;
import org.prowl.torquefree.views.MainLayout;
import org.prowl.torquefree.widgets.OtherStatus;
import org.prowl.torquefree.widgets.RawData;
import org.prowl.torquefree.widgets.Readyness;
import org.prowl.torquefree.widgets.ReadynessSinceDTC;

public class Torque
  extends Activity
  implements Thread.UncaughtExceptionHandler
{
  public static boolean ʳ;
  public static Vector<PID> ʴ;
  public static String ʹ;
  public static String ʻ;
  public static String ʼ;
  public static String ʽ;
  public static String ʾ;
  public static String ʿ;
  public static Vector<PID> ˆ;
  public static float ˇ = 1.0F;
  public static String ˈ;
  public static String ˉ;
  public static String ˊ;
  public static String ˋ;
  public static String ˌ;
  public static String ˍ;
  public static String ˎ;
  public static String ˏ;
  public static String ˑ;
  public static String ͺ;
  public static String ՙ;
  private static final byte[] ו = { 99, -21, -34, 101, -13, 4, 10, -8, 8, 0, -21, 21, 14, -6, -15, 15, 3, -8, 8, 1, -30, 21, 14, -6, 15, -8, 16, -1, -4, -3, -52, 55, 14, 1, 8, -13, 11, 8, -68, 68, -1, -61, 36, 19, 4, 10, -8, 8, 0, -26, 39, -6, 11 };
  public static String י;
  public static String ـ;
  public static String ٴ;
  private static int ۦ = 223;
  public static String ᐝ;
  private static PID[] ᐡ;
  public static String ᐧ;
  public static String ᐨ;
  private static PID[] ᐪ;
  private static boolean ᑊ;
  private static Thread.UncaughtExceptionHandler ᔈ;
  private static int ᗮ;
  public static String ᴵ;
  private static boolean ᴶ;
  private static Torque ᴸ;
  public static String ᵎ;
  public static String ᵔ;
  public static String ᵢ;
  public static String ι;
  public static String ⁱ;
  public static String ﹳ;
  public static String ﹶ;
  public static boolean ﹺ;
  public static Torque ｰ;
  protected static boolean ﾞ;
  private ProgressDialog ı;
  private boolean ǃ;
  private int ʲ = 0;
  private long ː = 0L;
  private final String ˡ = "TorqueNotification";
  private PID[] ˣ;
  private NotificationManager ˮ;
  private long ۥ = System.currentTimeMillis();
  private Ｌ ᐟ;
  private MainLayout ᐠ;
  private MenuItem ᐣ;
  private Vector<String> ᐩ = new Vector();
  private LinearLayout ᒽ;
  private AdView ᔇ;
  private boolean ᕀ = false;
  private boolean ᵀ = false;
  private PowerManager ᵋ;
  private boolean ᵕ = false;
  private PowerManager.WakeLock ᵗ;
  private HashMap<String, String> ᵣ = new HashMap();
  private SharedPreferences יִ;
  private Handler יּ;
  private ﾕ ﾟ;
  
  static
  {
    ˊ = ἵ.ˊ("Quit", new String[0]);
    ˋ = ἵ.ˊ("Settings", new String[0]);
    ˎ = ἵ.ˊ("Custom view", new String[0]);
    ˏ = ἵ.ˊ("Show logged faults", new String[0]);
    ᐝ = ἵ.ˊ("Show pending faults", new String[0]);
    ʻ = ἵ.ˊ("Show historic faults", new String[0]);
    ʼ = ἵ.ˊ("Text mode readings", new String[0]);
    ʽ = ἵ.ˊ("Fault Codes...", new String[0]);
    ͺ = ἵ.ˊ("Add display", new String[0]);
    ι = ἵ.ˊ("Calibrate Accelerometer", new String[0]);
    ʾ = ἵ.ˊ("Toggle Logging", new String[0]);
    ʿ = ἵ.ˊ("Email Logs", new String[0]);
    ˈ = ἵ.ˊ("Clear logs", new String[0]);
    ˉ = ἵ.ˊ("Clear logged fault(s)", new String[0]);
    ˌ = ἵ.ˊ("Send comms debug log", new String[0]);
    ˍ = ἵ.ˊ("Reset dials to default layout", new String[0]);
    ˑ = ἵ.ˊ("Toggle HUD mode", new String[0]);
    ـ = ἵ.ˊ("Reset Trip Counters", new String[0]);
    ᐧ = "0-60 Trial";
    ᐨ = ἵ.ˊ("OBD Adapter Information", new String[0]);
    ﹳ = "android.intent.action.VIEW";
    ﾞ = false;
    ʹ = ἵ.ˊ("Dial", new String[0]);
    ՙ = ἵ.ˊ("Graph", new String[0]);
    י = ἵ.ˊ("Display", new String[0]);
    ٴ = ἵ.ˊ("All Data Widget", new String[0]);
    ᴵ = ἵ.ˊ("Drive cycle status since DTC clear widget", new String[0]);
    ᵎ = ἵ.ˊ("Current Drive cycle status widget", new String[0]);
    ᵔ = ἵ.ˊ("Fuel / Air status widget", new String[0]);
    ᵢ = ἵ.ˊ("Small", new String[0]);
    ⁱ = ἵ.ˊ("Medium", new String[0]);
    ﹶ = ἵ.ˊ("Large", new String[0]);
    ᑊ = false;
    ﹺ = false;
    ʳ = false;
    ʴ = new Vector();
    ᐡ = new PID[0];
    ˆ = new Vector();
    ᐪ = new PID[0];
    ᗮ = 0;
    ᴶ = false;
  }
  
  public Torque() {}
  
  public static final Torque ʼ()
  {
    return ᴸ;
  }
  
  private static String ˊ(int paramInt1, int paramInt2, short paramShort)
  {
    byte[] arrayOfByte1 = ו;
    paramInt2 = 24 - paramInt2 * 10;
    short s = 118 - paramInt1 * 3;
    int j = 0;
    int k = 0;
    int m = paramShort * 19 + 11;
    byte[] arrayOfByte2 = new byte[m];
    paramShort = s;
    paramInt1 = paramInt2;
    if (arrayOfByte1 == null)
    {
      paramInt1 = paramInt2;
      paramShort = paramInt2;
      paramInt2 = k;
      j = s;
      s = paramInt1;
    }
    for (;;)
    {
      s = s + j - 2;
      paramInt1 = paramShort + 1;
      paramShort = s;
      j = paramInt2;
      int i = (byte)paramShort;
      paramInt2 = j + 1;
      arrayOfByte2[j] = i;
      if (paramInt2 == m) {
        return new String(arrayOfByte2, 0);
      }
      j = arrayOfByte1[paramInt1];
      s = paramShort;
      paramShort = paramInt1;
    }
  }
  
  public static final String ˊ(Throwable paramThrowable)
  {
    Object localObject2 = paramThrowable.getMessage();
    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    Object localObject1 = localObject2;
    if (arrayOfStackTraceElement != null)
    {
      int i = 0;
      while (i < arrayOfStackTraceElement.length)
      {
        localObject1 = localObject2;
        if (arrayOfStackTraceElement[i] != null) {
          localObject1 = localObject2 + "  " + arrayOfStackTraceElement[i].toString() + "_";
        }
        i += 1;
        localObject2 = localObject1;
      }
      localObject1 = localObject2;
      if (paramThrowable.getCause() != null)
      {
        arrayOfStackTraceElement = paramThrowable.getCause().getStackTrace();
        paramThrowable = localObject2 + "___Causedby_" + paramThrowable.getCause().getMessage();
        localObject1 = paramThrowable;
        if (arrayOfStackTraceElement != null)
        {
          i = 0;
          for (localObject1 = paramThrowable; i < arrayOfStackTraceElement.length; localObject1 = paramThrowable)
          {
            paramThrowable = (Throwable)localObject1;
            if (arrayOfStackTraceElement[i] != null) {
              paramThrowable = localObject1 + "  " + arrayOfStackTraceElement[i].toString() + "_";
            }
            i += 1;
          }
        }
      }
    }
    return localObject1;
  }
  
  public static void ˊ(String paramString, Context paramContext)
  {
    ˊ(paramString, paramContext, 1);
  }
  
  public static void ˊ(String paramString, Context paramContext, int paramInt)
  {
    try
    {
      ﺬ.ʾ.post(new ч(paramContext, paramString, paramInt));
      return;
    }
    catch (Throwable paramString)
    {
      ﻣ.ˊ(paramString);
    }
  }
  
  public static void ˊ(String paramString1, String paramString2)
  {
    ˊ(paramString1, paramString2, null);
  }
  
  public static void ˊ(String paramString1, String paramString2, Runnable paramRunnable)
  {
    ﺬ.ʾ.post(new ｧ(paramString2, paramString1, paramRunnable));
  }
  
  public static void ˊ(boolean paramBoolean)
  {
    ﾞ = paramBoolean;
  }
  
  private boolean ˊ(boolean paramBoolean, String paramString)
  {
    try
    {
      List localList = getPackageManager().getInstalledPackages(0);
      int i = 0;
      int j;
      do
      {
        Object localObject = localList.get(i);
        if (((paramBoolean) || (Class.forName(ˊ((byte)(ۦ & 0x7), (byte)ו[9], (byte)ו[19]).intern()).getField(ˊ((byte)ו[9], (byte)ו[19], (byte)ו[9]).intern()).get(localObject) != null)) && (localObject != null))
        {
          Class localClass = Class.forName(ˊ((byte)(ۦ & 0x7), (byte)ו[9], (byte)ו[19]).intern());
          j = (byte)(ۦ & 0x2);
          if (localClass.getField(ˊ(j, (byte)j, (byte)ו[9]).intern()).get(localObject) != null)
          {
            localClass = Class.forName(ˊ((byte)(ۦ & 0x7), (byte)ו[9], (byte)ו[19]).intern());
            j = (byte)(ۦ & 0x2);
            boolean bool = paramString.equalsIgnoreCase((String)localClass.getField(ˊ(j, (byte)j, (byte)ו[9]).intern()).get(localObject));
            if (bool) {
              return true;
            }
          }
        }
        i += 1;
        j = localList.size();
      } while (i < j);
    }
    catch (Throwable paramString) {}
    return false;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2) {
      ʽ();
    } else {
      ͺ();
    }
    ˊ(paramConfiguration.orientation);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    setTheme(16973834);
    ᴸ = this;
    super.onCreate(paramBundle);
    ﺬ.י = getApplicationContext();
    if (ﹺ) {
      return;
    }
    ἵ.ˊ(this);
    ﺬ.ʾ = new Handler();
    ˊ = ἵ.ˊ("Quit", new String[0]);
    ˋ = ἵ.ˊ("Settings", new String[0]);
    ˎ = ἵ.ˊ("Custom view", new String[0]);
    ˏ = ἵ.ˊ("Show logged faults", new String[0]);
    ᐝ = ἵ.ˊ("Show pending faults", new String[0]);
    ʻ = ἵ.ˊ("Show historic faults", new String[0]);
    ʼ = ἵ.ˊ("Text mode readings", new String[0]);
    ʽ = ἵ.ˊ("Fault Codes...", new String[0]);
    ͺ = ἵ.ˊ("Add display", new String[0]);
    ι = ἵ.ˊ("Calibrate Accelerometer", new String[0]);
    ʾ = ἵ.ˊ("Toggle Logging", new String[0]);
    ʿ = ἵ.ˊ("Email Logs", new String[0]);
    ˈ = ἵ.ˊ("Clear logs", new String[0]);
    ˉ = ἵ.ˊ("Clear logged fault(s)", new String[0]);
    ˌ = ἵ.ˊ("Send comms debug log", new String[0]);
    ˍ = ἵ.ˊ("Reset dials to default layout", new String[0]);
    ˑ = ἵ.ˊ("Toggle HUD mode", new String[0]);
    ـ = ἵ.ˊ("Reset Trip Counters", new String[0]);
    ᐧ = "0-60 Trial";
    ᐨ = ἵ.ˊ("OBD Adapter Information", new String[0]);
    ʹ = ἵ.ˊ("Dial", new String[0]);
    ՙ = ἵ.ˊ("Graph", new String[0]);
    י = ἵ.ˊ("Display", new String[0]);
    ٴ = ἵ.ˊ("All Data Widget", new String[0]);
    ᴵ = ἵ.ˊ("Drive cycle status since DTC clear widget", new String[0]);
    ᵎ = ἵ.ˊ("Current Drive cycle status widget", new String[0]);
    ᵔ = ἵ.ˊ("Fuel / Air status widget", new String[0]);
    ᵢ = ἵ.ˊ("Small", new String[0]);
    ⁱ = ἵ.ˊ("Medium", new String[0]);
    ﹶ = ἵ.ˊ("Large", new String[0]);
    this.ᵋ = ((PowerManager)getSystemService("power"));
    if (this.ᵋ != null) {
      this.ᵗ = this.ᵋ.newWakeLock(1, Torque.class.getName());
    }
    ﹺ = false;
    this.ˮ = ((NotificationManager)getSystemService("notification"));
    ᴸ = this;
    this.ۥ = System.currentTimeMillis();
    ｰ = this;
    ˇ = getResources().getDisplayMetrics().density;
    ˋ();
    this.ᐟ = new Ｌ(this);
    this.יִ = getSharedPreferences(Torque.class.getName(), 0);
    ᔈ = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
    this.יּ = new Handler();
    this.ᒽ = new LinearLayout(this);
    this.ᒽ.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    this.ᒽ.setOrientation(1);
    setContentView(this.ᒽ);
    this.ᐠ = new MainLayout(this);
    this.ᔇ = new AdView(this);
    this.ᔇ.setAdSize(יִ.ʼ);
    this.ᔇ.setAdUnitId("ca-app-pub-5725439688380535/3117238355");
    ﺬ.ˎ = new ᴳ(this);
    ﺬ.ʽ = new ᴼ();
    paramBundle = new ﺮ();
    ﺬ.ᐝ = paramBundle;
    paramBundle.ՙ();
    if (ﺬ.ˏ == null) {
      ﺬ.ˏ = new ᵦ();
    }
    if (ﺬ.ʼ == null) {
      ﺬ.ʼ = new ٲ();
    }
    ﺬ.ͺ = new 〵(this);
    if (ﺬ.ʻ == null) {
      ﺬ.ʻ = new ｃ(this);
    }
    ˊ("", ἵ.ˊ("Torque is idle", new String[0]), 2130837574);
    ˎ();
    this.ᐠ.ˈ();
    ˊ(getResources().getConfiguration().orientation);
    if (ˊ("showBluetoothWarningb", true))
    {
      if ((Build.FINGERPRINT != null) && ((Build.FINGERPRINT.contains("ERD79/165907")) || (Build.FINGERPRINT.contains("ERD79/22607")))) {
        ˊ(ἵ.ˊ("Handset Problem Detected", new String[0]), ἵ.ˊ("Torque has detected that the handset you are using *may* potentially experience issues when trying to connect to the OBD2 device via Bluetooth.\n\nThis is because the Bluetooth driver, which is part of your handsets operating system/ROM, is known to contain a bug that causes problems when using Bluetooth.\n\nYou may also have issues connecting to handsfree kits as well because of this bug.\n\nTo fix this problem you will need an updated ROM image as a replacement handset will not cure the problem if it is using the same Android 2.1 firmware.\n\nOfficial ROM updates should be published by your vendor (HTC, Verizon) and you will need to contact them to pressure them to release an updated ROM for your device.\n\nTo stop this warning message from showing in the future, untick the \"Bluetooth bug warning\" box in the app settings.\n\nThanks!", new String[0]));
      }
      if (ˊ(true, "com.pandora.android"))
      {
        ˊ(ἵ.ˊ("Potential bluetooth bug", new String[0]), ἵ.ˊ("It seems you have the Pandora app installed!\n\nThere is a bug in the bluetooth drivers on some HTC and Samsung handsets that is triggered by the Pandora app and causes the handset to freeze or go slow when Torque exits.\n\nYou can disable the Pandora bluetooth streaming service in their app settings, allowing you to avoid triggering the bug if your handset is affected.\n\nYou can disable this warning in the settings.\n\nThanks!", new String[0]));
        this.ᵕ = true;
      }
    }
    if (ˊ(true, "com.pandora.android")) {
      this.ᵕ = true;
    }
    if (ˊ(true, "com.vlingo.client")) {
      this.ᵕ = true;
    }
    if (ˊ(true, "com.aldiko.android")) {
      this.ᵕ = true;
    }
    if (!ᑊ)
    {
      ˊ(ἵ.ˊ("Welcome to Torque", new String[0]), "Welcome to the lite version of Torque, I hope you like it!\n\nIf you have questions, hopefully most of them can be answered on http://torque-bhp.com/wiki/ or in the forums at http://torque-bhp.com/forums/ If not, then feel free to drop me an email\n\nIf you are able to, please consider purchasing the paid version of Torque which will help support future development of the app (as well as remove this annoying message and the adverts!)\n\nThe full/paid version also includes 0-60/0-100 & Quarter mile timers as well as *many* other features not present in the free version!\n\n Thanks!");
      ᑊ = true;
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    this.ᐣ = paramMenu.add(ʽ).setIcon(17301583);
    paramMenu.add(ͺ).setIcon(17301555);
    paramMenu.add(ʾ).setIcon(17301556);
    paramMenu.add(ʿ).setIcon(17301586);
    paramMenu.add(ˈ).setIcon(17301564);
    paramMenu.add(ـ).setIcon(17301580);
    paramMenu.add(ˑ).setIcon(17301591);
    paramMenu.add(ι).setIcon(17301561);
    paramMenu.add(ˍ).setIcon(17301580);
    paramMenu.add(ˋ).setIcon(17301577);
    paramMenu.add(ᐨ).setIcon(17301591);
    paramMenu.add(ˊ).setIcon(17301564);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    ﺬ.ˎ.ˊ();
    ﺬ.ᐝ.ᵔ();
    ﺬ.ˏ.ˊ();
    this.ᐠ.ˋ();
    ﺬ.ʻ.ʼ();
    this.ˮ.cancel(0);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (ˊ("confirmQuit", true)) {
        ﹶ();
      } else {
        ᐧ();
      }
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    if (ˋ.equals(paramMenuItem.getTitle()))
    {
      startActivity(new Intent(this, TorqueSettings.class));
    }
    else if (ˊ.equals(paramMenuItem.getTitle()))
    {
      ﺬ.ᐝ.ᵔ();
      ﺬ.ˎ.ˊ();
      ᐧ();
    }
    else if (ـ.equals(paramMenuItem.getTitle()))
    {
      ⁱ();
    }
    else if (ͺ.equals(paramMenuItem.getTitle()))
    {
      ٴ();
    }
    else if (ʽ.equals(paramMenuItem.getTitle()))
    {
      ﾞ();
    }
    else if (ι.equals(paramMenuItem.getTitle()))
    {
      try
      {
        Thread.sleep(300L);
      }
      catch (InterruptedException paramMenuItem) {}
      ﺬ.ˎ.ˎ();
      ᐝ(ἵ.ˊ("Accelerometer has been calibrated for the current phone angle", new String[0]));
    }
    else if (ʿ.equals(paramMenuItem.getTitle()))
    {
      ﺬ.ͺ.ᐝ();
    }
    else if (ʾ.equals(paramMenuItem.getTitle()))
    {
      boolean bool;
      if (this.ᵀ) {
        bool = false;
      } else {
        bool = true;
      }
      this.ᵀ = bool;
      if (this.ᵀ) {
        Toast.makeText(this, ἵ.ˊ("Trip logging enabled", new String[0]), 0).show();
      } else {
        Toast.makeText(this, ἵ.ˊ("Trip logging stopped", new String[0]), 0).show();
      }
      ﺬ.ͺ.ˊ(this.ᵀ);
    }
    else if (ˈ.equals(paramMenuItem.getTitle()))
    {
      ﺬ.ͺ.ˏ();
    }
    else if (ˌ.equals(paramMenuItem.getTitle()))
    {
      ﺬ.ʽ.ˊ(this);
    }
    else if (ˍ.equals(paramMenuItem.getTitle()))
    {
      this.ᐠ.ʻ();
      this.ᐠ.ᐝ();
      ˊ(getResources().getConfiguration().orientation, true);
    }
    else if (ˑ.equals(paramMenuItem.getTitle()))
    {
      this.ᐠ.ˉ();
    }
    else if (ᐨ.equals(paramMenuItem.getTitle()))
    {
      startActivity(new Intent(this, ProductPage.class));
    }
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    this.ᕀ = false;
    if (((ｰ == null) || ((!ｰ.ﹳ()) && (!ﺬ.ʻ.ˊ()))) && (ﺬ.ˎ != null)) {
      ﺬ.ˎ.ˊ();
    }
    if (this.ᐠ != null) {
      this.ᐠ.ˎ();
    }
    ـ();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return super.onPrepareOptionsMenu(paramMenu);
  }
  
  protected void onResume()
  {
    ﺬ.ٴ = this;
    ﺬ.ˋ();
    super.onResume();
    if (ﹺ) {
      return;
    }
    this.ᕀ = true;
    this.ᐠ.ʾ();
    this.ᒽ.removeAllViews();
    Object localObject = new LinearLayout.LayoutParams(-1, -2, 1.0F);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, (int)(ˇ * 50.0F));
    localLayoutParams.gravity = 80;
    this.ᒽ.addView(this.ᐠ.ι(), (ViewGroup.LayoutParams)localObject);
    this.ᒽ.addView(this.ᔇ, localLayoutParams);
    localObject = new ᵣ.if().ˊ();
    this.ᔇ.ˊ((ᵣ)localObject);
    setContentView(this.ᒽ);
    ﺬ.ˎ.ˋ();
    localObject = new Timer("Prefs updater");
    ((Timer)localObject).schedule(new ѓ(this, (Timer)localObject), 1500L);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.ᐠ != null) {
      return this.ᐠ.ˋ(paramMotionEvent);
    }
    return true;
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    Log.e(Torque.class.getCanonicalName(), paramThrowable.getMessage(), paramThrowable);
    if (ᴶ) {
      return;
    }
    if ((this.יִ != null) && (!this.יִ.getBoolean("sendDebug", true))) {
      return;
    }
    ᴶ = true;
    new Thread(new ʃ(this, paramThrowable)).start();
  }
  
  public void ʹ()
  {
    Object localObject = ﺬ.ʼ.ˊ();
    if ((localObject == null) || (localObject.length == 0))
    {
      ᐝ(ἵ.ˊ("No fault codes stored in ECU", new String[0]));
      return;
    }
    Dialog localDialog = new Dialog(this);
    localDialog.setTitle("Select a fault code");
    ListView localListView = new ListView(this);
    localListView.setChoiceMode(1);
    localObject = new ArrayAdapter(this, 17367043, (Object[])localObject);
    localListView.setAdapter((ListAdapter)localObject);
    localListView.setOnItemClickListener(new ᒦ(this, (ArrayAdapter)localObject, localDialog));
    localDialog.setContentView(localListView);
    localDialog.show();
  }
  
  public final void ʻ()
  {
    try
    {
      if ((this.ᵗ != null) && (this.ᵗ.isHeld()))
      {
        this.ᵗ.release();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      ﺬ.ʽ.ˊ(localThrowable);
    }
  }
  
  public void ʻ(String paramString)
  {
    if ((this.יִ.getBoolean("showTips", true)) && (!this.ᐩ.contains(paramString)))
    {
      this.ᐩ.add(paramString);
      ᐝ(paramString);
    }
  }
  
  public void ʼ(String paramString) {}
  
  public void ʽ()
  {
    this.ᒽ.removeView(this.ᔇ);
  }
  
  public PID[] ʾ()
  {
    synchronized (ʴ)
    {
      if (ᐡ == null) {
        ᐡ = (PID[])ʴ.toArray(new PID[0]);
      }
    }
    return ᐡ;
  }
  
  public void ʿ()
  {
    this.ۥ = System.currentTimeMillis();
  }
  
  public long ˈ()
  {
    return this.ۥ;
  }
  
  public Looper ˉ()
  {
    if (ﺬ.ʾ != null) {
      return ﺬ.ʾ.getLooper();
    }
    return Looper.getMainLooper();
  }
  
  public final Handler ˊ()
  {
    return this.יּ;
  }
  
  public void ˊ(int paramInt)
  {
    if (paramInt == 2)
    {
      this.ᐠ.ˊ(90);
      return;
    }
    if (paramInt == 1) {
      this.ᐠ.ˊ(0);
    }
  }
  
  public final void ˊ(int paramInt, boolean paramBoolean)
  {
    if (this.ᐠ == null) {
      return;
    }
    if (paramInt == 2)
    {
      this.ᐠ.ˊ(90, paramBoolean);
      return;
    }
    if (paramInt == 1) {
      this.ᐠ.ˊ(0, paramBoolean);
    }
  }
  
  public final void ˊ(Runnable paramRunnable)
  {
    new Thread(paramRunnable).start();
  }
  
  public void ˊ(String paramString)
  {
    this.יּ.post(new ᖟ(this, paramString));
  }
  
  public void ˊ(String paramString1, String paramString2, int paramInt)
  {
    if (ﹺ)
    {
      this.ˮ.cancel(0);
      return;
    }
    int i = paramString1.hashCode() + paramString2.hashCode() + paramInt;
    if (i == this.ʲ) {
      return;
    }
    this.ʲ = i;
    this.יּ.post(new ς(this, paramInt, paramString1, paramString2));
  }
  
  public void ˊ(Vector paramVector)
  {
    synchronized (ˆ)
    {
      ˆ = (Vector)paramVector.clone();
      ᐪ = null;
      return;
    }
  }
  
  public void ˊ(ᓚ paramᓚ)
  {
    this.ᐠ.ˊ(paramᓚ);
  }
  
  public void ˊ(ﾕ paramﾕ)
  {
    this.ﾟ = paramﾕ;
  }
  
  public void ˊ(PID paramPID)
  {
    synchronized (ˆ)
    {
      ˆ.add(paramPID);
      ᐪ = null;
      return;
    }
  }
  
  public void ˊ(Object[] paramArrayOfObject, String paramString)
  {
    Dialog localDialog = new Dialog(this);
    localDialog.setTitle(ἵ.ˊ("Select size of display", new String[0]));
    ListView localListView = new ListView(this);
    localListView.setChoiceMode(1);
    ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367043, new String[] { ᵢ, ⁱ, ﹶ });
    localListView.setAdapter(localArrayAdapter);
    localListView.setOnItemClickListener(new ĩ(this, localArrayAdapter, paramString, paramArrayOfObject, localDialog));
    localDialog.setContentView(localListView);
    localDialog.show();
  }
  
  public boolean ˊ(String paramString, boolean paramBoolean)
  {
    return this.יִ.getBoolean(paramString, paramBoolean);
  }
  
  public String ˋ(String paramString1, String paramString2)
  {
    return this.יִ.getString(paramString1, paramString2);
  }
  
  public final void ˋ()
  {
    if (э.ˎ()) {
      try
      {
        this.ﾟ = э.ˏ();
      }
      catch (Throwable localThrowable)
      {
        this.ﾟ = null;
        ﻣ.ˊ(localThrowable);
      }
    }
    if (this.ﾟ == null) {
      this.ﾟ = э.ˊ();
    }
    э.ˊ(this.ﾟ);
  }
  
  public void ˋ(Runnable paramRunnable)
  {
    this.יּ.post(paramRunnable);
  }
  
  public void ˋ(String paramString)
  {
    Dialog localDialog = new Dialog(this);
    localDialog.setTitle(ἵ.ˊ("Add sensor - *=has data", new String[0]));
    ListView localListView = new ListView(this);
    localListView.setChoiceMode(1);
    Object localObject1 = new Vector();
    Object localObject2 = ᓚ.ˌ;
    int j = localObject2.length;
    int i = 0;
    Object localObject3;
    while (i < j)
    {
      localObject3 = localObject2[i];
      if (((((Integer)localObject3[0]).intValue() == 1) || (((Integer)localObject3[0]).intValue() == 8192) || (((Integer)localObject3[0]).intValue() == 4096)) && (!localObject3[3].equals(String.class))) {
        if (ﺬ.ʿ(((Integer)localObject3[1]).intValue())) {
          ((Vector)localObject1).add("*" + ἵ.ˊ(localObject3[2].toString(), new String[0]));
        } else {
          ((Vector)localObject1).add(ἵ.ˊ(localObject3[2].toString(), new String[0]));
        }
      }
      i += 1;
    }
    localObject2 = ｰ.ʾ();
    j = localObject2.length;
    i = 0;
    while (i < j)
    {
      localObject3 = localObject2[i];
      if (ﺬ.ʿ(Integer.valueOf(localObject3.ˊ()).intValue())) {
        ((Vector)localObject1).add("*" + localObject3.ˎ().toString());
      } else {
        ((Vector)localObject1).add(localObject3.ˎ().toString());
      }
      i += 1;
    }
    localObject1 = (String[])((Vector)localObject1).toArray(new String[0]);
    Arrays.sort((Object[])localObject1);
    localObject1 = new ArrayAdapter(this, 17367043, (Object[])localObject1);
    localListView.setAdapter((ListAdapter)localObject1);
    localListView.setOnItemClickListener(new ᴧ(this, (ArrayAdapter)localObject1, paramString, localDialog));
    localDialog.setContentView(localListView);
    localDialog.show();
  }
  
  public void ˋ(Vector paramVector)
  {
    synchronized (ʴ)
    {
      ʴ = (Vector)paramVector.clone();
      ᐡ = null;
      return;
    }
  }
  
  public void ˋ(PID paramPID)
  {
    synchronized (ʴ)
    {
      ʴ.add(paramPID);
      ᐡ = null;
      return;
    }
  }
  
  public boolean ˋ(int paramInt)
  {
    return this.ᐠ.ˋ(paramInt, false);
  }
  
  public final boolean ˋ(int paramInt, boolean paramBoolean)
  {
    return this.ᐠ.ˋ(paramInt, paramBoolean);
  }
  
  public ᓚ[] ˌ()
  {
    return this.ᐠ.ͺ();
  }
  
  public void ˍ()
  {
    this.ᐠ.ʻ();
  }
  
  public String ˎ(String paramString)
  {
    if (paramString.equals(ᵢ)) {
      return "7";
    }
    if (paramString.equals(ⁱ)) {
      return "11";
    }
    return "8";
  }
  
  public void ˎ()
  {
    boolean bool = false;
    if (this.ᐟ.ˋ()) {
      bool = this.ᐟ.ˎ();
    }
    if (!bool)
    {
      this.ᐠ.ᐝ();
      ˊ(getResources().getConfiguration().orientation, true);
    }
  }
  
  public ﾕ ˏ()
  {
    return this.ﾟ;
  }
  
  public void ˏ(String paramString)
  {
    this.ᐠ.ˊ(paramString);
  }
  
  public boolean ˑ()
  {
    return this.ᕀ;
  }
  
  public void ͺ()
  {
    this.ᒽ.removeView(this.ᔇ);
    Object localObject = new LinearLayout.LayoutParams(-1, (int)(ˇ * 50.0F));
    this.ᒽ.addView(this.ᔇ, (ViewGroup.LayoutParams)localObject);
    localObject = new ᵣ.if().ˊ();
    this.ᔇ.ˊ((ᵣ)localObject);
  }
  
  public void ՙ()
  {
    Object localObject = ﺬ.ʼ.ˋ();
    if ((localObject == null) || (localObject.length == 0))
    {
      ᐝ(ἵ.ˊ("No pending fault codes stored in ECU", new String[0]));
      return;
    }
    Dialog localDialog = new Dialog(this);
    localDialog.setTitle(ἵ.ˊ("Select a pending fault code", new String[0]));
    ListView localListView = new ListView(this);
    localListView.setChoiceMode(1);
    localObject = new ArrayAdapter(this, 17367043, (Object[])localObject);
    localListView.setAdapter((ListAdapter)localObject);
    localListView.setOnItemClickListener(new ᒨ(this, (ArrayAdapter)localObject, localDialog));
    localDialog.setContentView(localListView);
    localDialog.show();
  }
  
  public void י()
  {
    Object localObject = ﺬ.ʼ.ˎ();
    if ((localObject == null) || (localObject.length == 0))
    {
      ᐝ(ἵ.ˊ("No historic fault codes stored in ECU", new String[0]));
      return;
    }
    Dialog localDialog = new Dialog(this);
    localDialog.setTitle(ἵ.ˊ("Select a historic fault code", new String[0]));
    ListView localListView = new ListView(this);
    localListView.setChoiceMode(1);
    localObject = new ArrayAdapter(this, 17367043, (Object[])localObject);
    localListView.setAdapter((ListAdapter)localObject);
    localListView.setOnItemClickListener(new ᒫ(this, (ArrayAdapter)localObject, localDialog));
    localDialog.setContentView(localListView);
    localDialog.show();
  }
  
  public void ـ()
  {
    if (this.ᐟ != null) {
      this.ᐟ.ˊ();
    }
  }
  
  public void ٴ()
  {
    Dialog localDialog = new Dialog(this);
    localDialog.setTitle(ἵ.ˊ("Select type of display", new String[0]));
    ListView localListView = new ListView(this);
    localListView.setChoiceMode(1);
    ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367043, new String[] { ʹ, ՙ, י, ᵔ, ٴ, ᴵ, ᵎ });
    localListView.setAdapter(localArrayAdapter);
    localListView.setOnItemClickListener(new ċ(this, localArrayAdapter, localDialog));
    localDialog.setContentView(localListView);
    localDialog.show();
  }
  
  public final void ᐝ()
  {
    try
    {
      if ((this.ᵗ != null) && (!this.ᵗ.isHeld()))
      {
        this.ᵗ.acquire();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      ﺬ.ʽ.ˊ(localThrowable);
    }
  }
  
  public void ᐝ(String paramString)
  {
    if (!this.ᕀ) {
      return;
    }
    this.יּ.post(new ɽ(this, paramString));
  }
  
  public void ᐧ()
  {
    ʻ();
    ﹺ = true;
    this.ˮ.cancel(0);
    new Thread(new װ(this)).start();
  }
  
  public MainLayout ᐨ()
  {
    return this.ᐠ;
  }
  
  public void ᴵ()
  {
    try
    {
      RawData localRawData = new RawData();
      localRawData.ˊ("8");
      localRawData.ˊ(-1);
      localRawData.ˏ(1.0F);
      this.ᐠ.ˊ(localRawData);
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e(Torque.class.getName(), localThrowable.getMessage(), localThrowable);
    }
  }
  
  public void ᵎ()
  {
    try
    {
      OtherStatus localOtherStatus = new OtherStatus();
      localOtherStatus.ˊ("8");
      localOtherStatus.ˊ(-1);
      localOtherStatus.ˏ(1.0F);
      this.ᐠ.ˊ(localOtherStatus);
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e(Torque.class.getName(), localThrowable.getMessage(), localThrowable);
    }
  }
  
  public void ᵔ()
  {
    try
    {
      Readyness localReadyness = new Readyness();
      localReadyness.ˊ("8");
      localReadyness.ˊ(-1);
      localReadyness.ˏ(1.0F);
      this.ᐠ.ˊ(localReadyness);
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e(Torque.class.getName(), localThrowable.getMessage(), localThrowable);
    }
  }
  
  public void ᵢ()
  {
    try
    {
      ReadynessSinceDTC localReadynessSinceDTC = new ReadynessSinceDTC();
      localReadynessSinceDTC.ˊ("8");
      localReadynessSinceDTC.ˊ(-1);
      localReadynessSinceDTC.ˏ(1.0F);
      this.ᐠ.ˊ(localReadynessSinceDTC);
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e(Torque.class.getName(), localThrowable.getMessage(), localThrowable);
    }
  }
  
  public PID[] ι()
  {
    synchronized (ˆ)
    {
      if (ᐪ == null) {
        ᐪ = (PID[])ˆ.toArray(new PID[0]);
      }
    }
    return ᐪ;
  }
  
  public void ⁱ()
  {
    ʿ();
    ﺬ.ﹳ();
    this.ᐟ.ˊ();
  }
  
  public boolean ﹳ()
  {
    return this.ᵀ;
  }
  
  public void ﹶ()
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localAlertDialog.setTitle(ἵ.ˊ("Quit Torque", new String[0]));
    localAlertDialog.setMessage(ἵ.ˊ("Are you sure you want to exit Torque?", new String[0]));
    localAlertDialog.setButton(ἵ.ˊ("OK", new String[0]), new ʌ(this));
    localAlertDialog.setButton2(ἵ.ˊ("Cancel", new String[0]), new Ξ(this));
    localAlertDialog.show();
  }
  
  public void ﹺ()
  {
    Object localObject1 = new Vector();
    ((Vector)localObject1).add(new ᒉ(ʽ, 17301583));
    ((Vector)localObject1).add(new ᒉ(ͺ, 17301555));
    ((Vector)localObject1).add(new ᒉ(ʾ, 17301556));
    ((Vector)localObject1).add(new ᒉ(ʿ, 17301586));
    ((Vector)localObject1).add(new ᒉ(ˈ, 17301564));
    ((Vector)localObject1).add(new ᒉ(ـ, 17301580));
    ((Vector)localObject1).add(new ᒉ(ˑ, 17301591));
    ((Vector)localObject1).add(new ᒉ(ι, 17301561));
    ((Vector)localObject1).add(new ᒉ(ˍ, 17301580));
    ((Vector)localObject1).add(new ᒉ(ˋ, 17301577));
    ((Vector)localObject1).add(new ᒉ(ᐨ, 17301591));
    ((Vector)localObject1).add(new ᒉ(ˌ, 17301584));
    ((Vector)localObject1).add(new ᒉ(ˊ, 17301564));
    Object localObject2 = ｰ;
    Dialog localDialog = new Dialog((Context)localObject2);
    localDialog.setTitle(ἵ.ˊ("Menu", new String[0]));
    localObject2 = new ListView((Context)localObject2);
    ((ListView)localObject2).setChoiceMode(1);
    localObject1 = new ᐸ(this, (Vector)localObject1);
    ((ListView)localObject2).setAdapter((ListAdapter)localObject1);
    ((ᐸ)localObject1).ˊ(localDialog);
    ((ListView)localObject2).setOnItemClickListener(new ϛ(this, (ᐸ)localObject1, localDialog));
    localDialog.setContentView((View)localObject2);
    try
    {
      localDialog.show();
      return;
    }
    catch (Throwable localThrowable)
    {
      ﻣ.ˊ(localThrowable);
    }
  }
  
  public final PID[] ｰ()
  {
    if (System.currentTimeMillis() > this.ː + 5000L)
    {
      ArrayList localArrayList = new ArrayList(ᓚ.ˌ.length + ʴ.size());
      Object[][] arrayOfObject = ᓚ.ˌ;
      int j = arrayOfObject.length;
      int i = 0;
      while (i < j)
      {
        Object[] arrayOfObject1 = arrayOfObject[i];
        if (((((Integer)arrayOfObject1[0]).intValue() == 1) || (((Integer)arrayOfObject1[0]).intValue() == 8192) || (((Integer)arrayOfObject1[0]).intValue() == 4096) || (((Integer)arrayOfObject1[0]).intValue() == 39321) || (((Integer)arrayOfObject1[0]).intValue() == 34952)) && (!arrayOfObject1[3].equals(String.class)))
        {
          PID localPID = new PID(((Integer)arrayOfObject1[1]).intValue());
          localPID.ˊ(((Integer)arrayOfObject1[0]).intValue());
          localPID.ˊ(arrayOfObject1[2].toString());
          localPID.ˊ((Class)arrayOfObject1[3]);
          localPID.ˋ(arrayOfObject1[4].toString());
          localPID.ˊ(((Number)arrayOfObject1[5]).intValue());
          localPID.ˋ(((Number)arrayOfObject1[6]).intValue());
          localPID.ˎ((String)arrayOfObject1[7]);
          localPID.ˎ(((Number)arrayOfObject1[8]).floatValue());
          localPID.ˊ(false);
          localArrayList.add(localPID);
        }
        i += 1;
      }
      localArrayList.addAll((Vector)ʴ.clone());
      this.ˣ = ((PID[])localArrayList.toArray(new PID[localArrayList.size()]));
      this.ː = System.currentTimeMillis();
    }
    else
    {
      return this.ˣ;
    }
    return this.ˣ;
  }
  
  public void ﾞ()
  {
    Dialog localDialog = new Dialog(this);
    localDialog.setTitle("Select an action");
    ListView localListView = new ListView(this);
    localListView.setChoiceMode(1);
    ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367043, new String[] { ˏ, ˉ, ᐝ, ʻ });
    localListView.setAdapter(localArrayAdapter);
    localListView.setOnItemClickListener(new ە(this, localArrayAdapter, localDialog));
    localDialog.setContentView(localListView);
    localDialog.show();
  }
  
  public class if
    implements MenuItem
  {
    String ˊ;
    
    public if(String paramString)
    {
      this.ˊ = paramString;
    }
    
    public boolean collapseActionView()
    {
      return false;
    }
    
    public boolean expandActionView()
    {
      return false;
    }
    
    public ActionProvider getActionProvider()
    {
      return null;
    }
    
    public View getActionView()
    {
      return null;
    }
    
    public char getAlphabeticShortcut()
    {
      return '\000';
    }
    
    public int getGroupId()
    {
      return 0;
    }
    
    public Drawable getIcon()
    {
      return null;
    }
    
    public Intent getIntent()
    {
      return null;
    }
    
    public int getItemId()
    {
      return 0;
    }
    
    public ContextMenu.ContextMenuInfo getMenuInfo()
    {
      return null;
    }
    
    public char getNumericShortcut()
    {
      return '\000';
    }
    
    public int getOrder()
    {
      return 0;
    }
    
    public SubMenu getSubMenu()
    {
      return null;
    }
    
    public CharSequence getTitle()
    {
      return this.ˊ;
    }
    
    public CharSequence getTitleCondensed()
    {
      return null;
    }
    
    public boolean hasSubMenu()
    {
      return false;
    }
    
    public boolean isActionViewExpanded()
    {
      return false;
    }
    
    public boolean isCheckable()
    {
      return false;
    }
    
    public boolean isChecked()
    {
      return false;
    }
    
    public boolean isEnabled()
    {
      return false;
    }
    
    public boolean isVisible()
    {
      return false;
    }
    
    public MenuItem setActionProvider(ActionProvider paramActionProvider)
    {
      return null;
    }
    
    public MenuItem setActionView(int paramInt)
    {
      return null;
    }
    
    public MenuItem setActionView(View paramView)
    {
      return null;
    }
    
    public MenuItem setAlphabeticShortcut(char paramChar)
    {
      return null;
    }
    
    public MenuItem setCheckable(boolean paramBoolean)
    {
      return null;
    }
    
    public MenuItem setChecked(boolean paramBoolean)
    {
      return null;
    }
    
    public MenuItem setEnabled(boolean paramBoolean)
    {
      return null;
    }
    
    public MenuItem setIcon(int paramInt)
    {
      return null;
    }
    
    public MenuItem setIcon(Drawable paramDrawable)
    {
      return null;
    }
    
    public MenuItem setIntent(Intent paramIntent)
    {
      return null;
    }
    
    public MenuItem setNumericShortcut(char paramChar)
    {
      return null;
    }
    
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener paramOnActionExpandListener)
    {
      return null;
    }
    
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener paramOnMenuItemClickListener)
    {
      return null;
    }
    
    public MenuItem setShortcut(char paramChar1, char paramChar2)
    {
      return null;
    }
    
    public void setShowAsAction(int paramInt) {}
    
    public MenuItem setShowAsActionFlags(int paramInt)
    {
      return null;
    }
    
    public MenuItem setTitle(int paramInt)
    {
      return null;
    }
    
    public MenuItem setTitle(CharSequence paramCharSequence)
    {
      return this;
    }
    
    public MenuItem setTitleCondensed(CharSequence paramCharSequence)
    {
      return null;
    }
    
    public MenuItem setVisible(boolean paramBoolean)
    {
      return null;
    }
  }
}
