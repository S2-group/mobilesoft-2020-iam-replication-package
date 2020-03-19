package com.opengarden.firechat;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.BackStackEntry;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.a.a.d;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.c;
import android.support.v4.view.ViewPager;
import android.support.v7.app.h;
import android.support.v7.app.h.a;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils.TruncateAt;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.contacts.list.ContactListFilterController;
import com.android.contacts.list.ContactListFilterController.ContactListFilterListener;
import com.android.contacts.list.ContactsIntentResolver;
import com.android.contacts.list.ContactsRequest;
import com.readystatesoftware.viewbadger.BadgeView;
import com.viewpagerindicator.CirclePageIndicator;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity
  extends c
  implements ContactListFilterController.ContactListFilterListener
{
  static Set B = Collections.newSetFromMap(new WeakHashMap());
  static final String m = MainActivity.class.getSimpleName();
  public FloatingActionButton A;
  gk C = new dh(this);
  private int D = -1;
  private boolean E = false;
  private boolean F = false;
  private String G = null;
  private long H;
  private long I;
  private float J;
  private float K;
  String n = "184095740544";
  com.google.android.gms.b.a o;
  String p;
  ImageView q = null;
  TabLayout r;
  public a s;
  public ViewPager t;
  ContactsRequest u;
  ContactsIntentResolver v = new ContactsIntentResolver(this);
  ContactListFilterController w;
  boolean x;
  final String y = "contacts-all";
  LinearLayout z;
  
  public MainActivity() {}
  
  private static long J()
  {
    return System.currentTimeMillis() / 1000L;
  }
  
  public static Intent a(String paramString)
  {
    Intent localIntent = new Intent(Application.b, MainActivity.class);
    localIntent.setAction(paramString);
    localIntent.setFlags(537001984);
    return localIntent;
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    paramContext.startActivity(SubActivity.a("com.opengarden.firechat.SHOW_PROFILE").putExtra("username", paramString1).putExtra("fullname", paramString2).putExtra("verified", paramBoolean));
  }
  
  static int b(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new RuntimeException("Could not get package name: " + paramContext);
    }
  }
  
  public static Intent b(String paramString)
  {
    Intent localIntent = new Intent(Application.b, SubActivity.class);
    localIntent.setAction(paramString);
    localIntent.setFlags(537001984);
    return localIntent;
  }
  
  public static void l() {}
  
  public static void m()
  {
    long l = FireChat.inboxStoreUnread(FireChat.inboxStoreEveryone());
    Iterator localIterator = B.iterator();
    if (localIterator.hasNext())
    {
      BadgeView localBadgeView = (BadgeView)((MainActivity)localIterator.next()).r.findViewById(2131624373);
      if (localBadgeView != null)
      {
        if (l <= 0L) {
          break label75;
        }
        localBadgeView.setText(String.valueOf(l));
        localBadgeView.a();
      }
      for (;;)
      {
        Application.a(l);
        break;
        label75:
        localBadgeView.b();
      }
    }
  }
  
  public static void n()
  {
    Iterator localIterator = B.iterator();
    while (localIterator.hasNext())
    {
      Fragment localFragment = ((MainActivity)localIterator.next()).getFragmentManager().findFragmentById(2131624091);
      if ((localFragment instanceof ChatroomsHomeFragment)) {
        ((ChatroomsHomeFragment)localFragment).b.notifyDataSetChanged();
      }
    }
  }
  
  void A()
  {
    SharedPreferences.Editor localEditor = gz.b().edit();
    localEditor.putBoolean("always_turn_on_bluetooth_on_app_foreground", true);
    localEditor.apply();
  }
  
  boolean B()
  {
    return (Application.b.j()) && (!w.b());
  }
  
  boolean C()
  {
    return (Application.b.l()) && (!Application.b.m());
  }
  
  boolean D()
  {
    if ((!IrfanCorrector.a) && (Application.b.e != null))
    {
      il localIl = Application.b.e;
      if (il.c()) {
        return true;
      }
    }
    return false;
  }
  
  boolean E()
  {
    int i = com.google.android.gms.common.c.a(this);
    if (i != 0)
    {
      if (com.google.android.gms.common.c.b(i)) {
        com.google.android.gms.common.c.a(i, this, 9000).show();
      }
      for (;;)
      {
        return false;
        Log.i(m, "GCM: This device is not supported.");
        finish();
      }
    }
    return true;
  }
  
  void F()
  {
    int j = 1;
    Object localObject1 = getFragmentManager().findFragmentById(2131624091);
    Object localObject2;
    if (localObject1 != null)
    {
      i = 1;
      if ((i & localObject1 instanceof MessageFragment) != 0)
      {
        localObject1 = ((MessageFragment)localObject1).b;
        if (localObject1 == null) {
          break label140;
        }
        localObject2 = FireChat.messageStoreLastObject((FireChat.MessageStore)localObject1);
        i = j;
        if (localObject2 != null)
        {
          i = j;
          if (FireChat.messageStoreLength((FireChat.MessageStore)localObject1) >= 5L) {
            if (FireChat.messageSecondsSince((FireChat.Message)localObject2) <= 21600.0D) {
              break label140;
            }
          }
        }
      }
    }
    label140:
    for (int i = j;; i = 0)
    {
      localObject1 = (TabSwitch)findViewById(2131624119);
      localObject2 = findViewById(2131624114);
      if ((localObject1 != null) && (localObject2 != null))
      {
        if ((((TabSwitch)localObject1).isChecked()) && (i != 0)) {
          ((View)localObject2).setVisibility(0);
        }
      }
      else
      {
        return;
        i = 0;
        break;
      }
      ((View)localObject2).setVisibility(8);
      return;
    }
  }
  
  void G()
  {
    new da(this).start();
  }
  
  void H()
  {
    if ((FireChat.userSignedIn()) || ((FireChat.userGetUsername() != null) && (av.a())))
    {
      ig.b();
      return;
    }
    startActivity(Application.a(this));
    finish();
  }
  
  public float I()
  {
    return registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("level", -1);
  }
  
  String a(Context paramContext)
  {
    SharedPreferences localSharedPreferences = gz.a();
    String str = localSharedPreferences.getString("registration_id", null);
    if (str == null)
    {
      Log.i(m, "GCM: Registration not found.");
      str = "";
    }
    while (localSharedPreferences.getInt("appVersion", Integer.MIN_VALUE) == b(paramContext)) {
      return str;
    }
    Log.i(m, "GCM: App version changed.");
    return "";
  }
  
  void a(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = gz.a();
    int i = b(paramContext);
    paramContext = "";
    try
    {
      localObject = gn.b(paramString.getBytes("UTF-8"));
      paramContext = (Context)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        localException.printStackTrace();
      }
    }
    Log.i(m, paramContext);
    localObject = localSharedPreferences.edit();
    ((SharedPreferences.Editor)localObject).putString("registration_id", paramString);
    ((SharedPreferences.Editor)localObject).putInt("appVersion", i);
    ((SharedPreferences.Editor)localObject).putString("user_id", paramContext);
    Application.a((SharedPreferences.Editor)localObject);
  }
  
  void a(Uri paramUri)
  {
    Fragment localFragment = p();
    if (localFragment != null)
    {
      if (!(localFragment instanceof MessageFragment)) {
        break label25;
      }
      ((MessageFragment)localFragment).a(paramUri);
    }
    label25:
    while (!(localFragment instanceof PrivateMessagesFragment)) {
      return;
    }
    ((PrivateMessagesFragment)localFragment).a(paramUri);
  }
  
  public void a(Boolean paramBoolean)
  {
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131624454);
    if (paramBoolean.booleanValue())
    {
      paramBoolean = (TextView)findViewById(2131624051);
      paramBoolean.setTextSize(15.0F);
      paramBoolean.setTypeface(null, 1);
      paramBoolean.setPadding(0, (int)(Application.j * 2.0F), 0, (int)(1.0F * Application.j));
      ((ViewGroup.MarginLayoutParams)paramBoolean.getLayoutParams()).setMargins(0, (int)(5.0F * Application.j), 0, (int)(Application.j * 2.0F));
      localLinearLayout.setOrientation(1);
      localLinearLayout.setGravity(17);
      localLinearLayout.setPadding((int)(Application.j * 4.0F), (int)(Application.j * 4.0F), 4, 4);
      return;
    }
    paramBoolean = (TextView)findViewById(2131624051);
    paramBoolean.setTextSize(19.0F);
    paramBoolean.setTypeface(null, 0);
    paramBoolean.setPadding(0, 0, 0, 0);
    ((ViewGroup.MarginLayoutParams)paramBoolean.getLayoutParams()).setMargins(0, 0, 0, 0);
    int i = 8;
    if ((g().a() & 0x4) != 0) {
      i = 48;
    }
    localLinearLayout.setOrientation(0);
    localLinearLayout.setGravity(8388611);
    localLinearLayout.setPadding((int)(i * Application.j), (int)(Application.j * 15.0F), 0, 0);
  }
  
  void a(String paramString, DialogInterface.OnClickListener paramOnClickListener)
  {
    h.a localA = new h.a(this);
    localA.b(paramString);
    localA.b(17039360, new cu(this));
    localA.a(2131690224, paramOnClickListener);
    localA.b().show();
  }
  
  public void a(String paramString1, String paramString2)
  {
    FireChat.signin(paramString1, paramString2, new MainActivity.3(this));
  }
  
  public int b(int paramInt)
  {
    Resources localResources = getResources();
    return (int)TypedValue.applyDimension(1, paramInt, localResources.getDisplayMetrics());
  }
  
  public void b(boolean paramBoolean)
  {
    Object localObject = findViewById(2131624119);
    if (paramBoolean) {}
    for (int i = 0;; i = 8)
    {
      ((View)localObject).setVisibility(i);
      localObject = (ViewGroup.MarginLayoutParams)this.z.getLayoutParams();
      if (!paramBoolean) {
        break;
      }
      i = b(51);
      ((ViewGroup.MarginLayoutParams)localObject).leftMargin = i;
      ((ViewGroup.MarginLayoutParams)localObject).rightMargin = i;
      return;
    }
    ((ViewGroup.MarginLayoutParams)localObject).leftMargin = 0;
    ((ViewGroup.MarginLayoutParams)localObject).rightMargin = 0;
  }
  
  public boolean b(String paramString1, String paramString2)
  {
    try
    {
      String str = URLDecoder.decode(paramString1, "UTF-8");
      paramString2 = Uri.parse(str);
      List localList = paramString2.getPathSegments();
      if (localList.size() > 0)
      {
        if (str.startsWith("content://com.android.contacts/data/"))
        {
          paramString1 = ig.b(paramString1);
          if (paramString1 == null) {
            return false;
          }
          c(paramString1[0], paramString1[1]);
          return true;
        }
        paramString1 = (String)localList.get(0);
        if (paramString1 != null) {
          if (paramString1.equals("signin"))
          {
            paramString1 = paramString2.getQueryParameter("username");
            paramString2 = paramString2.getQueryParameter("token");
            if ((paramString1 != null) && (paramString2 != null))
            {
              a(paramString1, paramString2);
              return true;
            }
          }
          else if (paramString1.equals("verify_email"))
          {
            paramString1 = (String)localList.get(1);
            if (paramString1 != null)
            {
              paramString2 = Application.e(this);
              paramString2.putExtra("token", paramString1);
              startActivity(paramString2);
            }
          }
          else
          {
            e(paramString1);
            return true;
          }
        }
      }
    }
    catch (UnsupportedEncodingException paramString1)
    {
      Log.e(m, "Error decoding chatroom link: ", paramString1);
    }
    return false;
  }
  
  public void c(int paramInt)
  {
    int i = 0;
    if (i < this.s.b())
    {
      if (i == paramInt) {
        this.s.e[i].setBackgroundResource(this.s.c[i]);
      }
      for (;;)
      {
        i += 1;
        break;
        this.s.e[i].setBackgroundResource(this.s.b[i]);
      }
    }
  }
  
  public void c(Intent paramIntent)
  {
    String str1 = paramIntent.getAction();
    if (str1 != null)
    {
      if (!str1.equals("com.opengarden.firechat.OPEN_CHATROOM")) {
        break label42;
      }
      str1 = paramIntent.getStringExtra("chatroom");
      if (str1 != null) {
        e(str1);
      }
    }
    for (;;)
    {
      setIntent(paramIntent);
      return;
      label42:
      if (str1.equals("com.opengarden.firechat.SHOW_PROFILE"))
      {
        str1 = paramIntent.getStringExtra("fullname");
        a(this, paramIntent.getStringExtra("username"), str1, paramIntent.getBooleanExtra("verified", false));
      }
      else if (str1.equals("com.opengarden.firechat.SHOW_USERS"))
      {
        new FollowFragment(paramIntent.getStringExtra("show"), paramIntent.getStringExtra("username"));
      }
      else if (str1.equals("com.opengarden.firechat.MESSAGE_NOTIFICATION"))
      {
        d(paramIntent);
        ge.a(this, 30000L);
        str1 = paramIntent.getStringExtra("chatroom");
        if (str1 != null)
        {
          gg.a(str1);
          this.D = 1;
          e(str1);
        }
        str1 = paramIntent.getStringExtra("username");
        String str2 = paramIntent.getStringExtra("fullname");
        if (str1 != null)
        {
          this.D = 1;
          this.E = true;
          c(str1, str2);
        }
      }
      else if (str1.equals("android.intent.action.VIEW"))
      {
        b(paramIntent.getDataString(), paramIntent.getType());
      }
    }
  }
  
  public void c(String paramString)
  {
    c(true);
    a(Boolean.valueOf(false));
    ((TextView)findViewById(2131624051)).setSingleLine(true);
    ((TextView)findViewById(2131624051)).setEllipsize(TextUtils.TruncateAt.END);
    ((TextView)findViewById(2131624051)).setText(paramString);
  }
  
  public void c(String paramString1, String paramString2)
  {
    Intent localIntent = b("open_private_chat");
    localIntent.putExtra("username", paramString1);
    localIntent.putExtra("fullname", paramString2);
    startActivity(localIntent);
  }
  
  public void c(boolean paramBoolean)
  {
    int i = 0;
    a(Boolean.valueOf(false));
    TextView localTextView = (TextView)findViewById(2131624051);
    if (paramBoolean) {}
    for (;;)
    {
      localTextView.setVisibility(i);
      return;
      i = 8;
    }
  }
  
  public void d(int paramInt)
  {
    BadgeView localBadgeView = (BadgeView)this.r.findViewById(2131624373);
    if (paramInt == 0)
    {
      localBadgeView.setAlpha(1.0F);
      return;
    }
    localBadgeView.setAlpha(0.44F);
  }
  
  void d(Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("username");
    String str2 = paramIntent.getStringExtra("chatroom");
    boolean bool1 = paramIntent.getBooleanExtra("is_nearby", false);
    boolean bool2 = paramIntent.getBooleanExtra("didBuzz", false);
    JSONObject localJSONObject;
    for (;;)
    {
      int i;
      try
      {
        localJSONObject = new JSONObject();
        localJSONObject.put("didBuzz", bool2);
        localJSONObject.put("app_visible", j.a());
        Time localTime = new Time();
        localTime.setToNow();
        localJSONObject.put("hour", localTime.hour);
        if (str1 != null)
        {
          i.a("private_message_notification/tapped", localJSONObject);
          return;
        }
        if (str2 == null) {
          break;
        }
        localJSONObject.put("chatroom", str2);
        bool1 = paramIntent.getBooleanExtra("is_following", false);
        i = paramIntent.getIntExtra("message_length", -10);
        localJSONObject.put("is_following", bool1);
        if (i == -1)
        {
          localJSONObject.put("is_image", true);
          i.a("public_message_notification/tapped", localJSONObject);
          return;
        }
      }
      catch (JSONException paramIntent)
      {
        paramIntent.printStackTrace();
        return;
      }
      localJSONObject.put("message_length", i);
    }
    if (bool1)
    {
      i.a("nearby_notification/tapped", localJSONObject);
      return;
    }
    i.a("unknown_notification/tapped", localJSONObject);
  }
  
  public void d(String paramString)
  {
    c(true);
    a(Boolean.valueOf(true));
    ((TextView)findViewById(2131624051)).setText(paramString);
  }
  
  public void d(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      g().a(false);
      return;
    }
    g().a(true);
  }
  
  public void e(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      this.A.b();
    }
    for (;;)
    {
      Fragment localFragment = this.s.d[paramInt];
      if (localFragment != null)
      {
        Application.a(localFragment.getClass().getSimpleName());
        localFragment.onResume();
      }
      return;
      this.A.setImageDrawable(getResources().getDrawable(2130837609));
      gk.a(this.C, 200L);
      continue;
      this.A.b();
      continue;
      this.A.setImageDrawable(getResources().getDrawable(2130837571));
      gk.a(this.C, 200L);
      localFragment = this.s.d[paramInt];
      if (localFragment != null)
      {
        ((ChatroomsHomeFragment)localFragment).b();
        continue;
        this.A.b();
      }
    }
  }
  
  public void e(String paramString)
  {
    Intent localIntent = b("open_chatroom");
    localIntent.putExtra("chatroom", paramString);
    startActivity(localIntent);
  }
  
  public boolean f(String paramString)
  {
    getPackageManager().getInstalledPackages(0);
    try
    {
      getPackageManager().getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  void o()
  {
    if (getCurrentFocus() == null) {
      return;
    }
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
  }
  
  public void onBackPressed()
  {
    Object localObject = getFragmentManager();
    boolean bool;
    if (((FragmentManager)localObject).getBackStackEntryCount() > 1)
    {
      ((FragmentManager)localObject).getBackStackEntryAt(((FragmentManager)localObject).getBackStackEntryCount() - 1);
      FragmentManager.BackStackEntry localBackStackEntry = ((FragmentManager)localObject).getBackStackEntryAt(((FragmentManager)localObject).getBackStackEntryCount() - 2);
      if ((localBackStackEntry.getName() != null) && (localBackStackEntry.getName().contains("root")))
      {
        bool = true;
        d(bool);
        Fragment localFragment = p();
        ((FragmentManager)localObject).popBackStackImmediate();
        if (((localFragment instanceof PrivateMessagesFragment)) && (!FireChat.messageStoreAllIncoming(((PrivateMessagesFragment)localFragment).b)) && (((FragmentManager)localObject).getBackStackEntryCount() > 1))
        {
          localFragment = p();
          if (((localFragment instanceof FollowFragment)) && (((FollowFragment)localFragment).d.equals("compose_search"))) {
            ((FragmentManager)localObject).popBackStackImmediate();
          }
        }
        localObject = p();
        if (localObject != null) {
          ((Fragment)localObject).setHasOptionsMenu(true);
        }
        int i = localBackStackEntry.getName().lastIndexOf(":");
        i = Integer.parseInt(localBackStackEntry.getName().substring(i + 1));
        this.t.setCurrentItem(i);
      }
    }
    for (;;)
    {
      o();
      return;
      bool = false;
      break;
      super.onBackPressed();
    }
  }
  
  public void onClick_sign_out(View paramView)
  {
    hd.a(this, new dc(this));
  }
  
  public void onContactListFilterChanged() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    int j = 0;
    super.onCreate(paramBundle);
    this.w = ContactListFilterController.createContactListFilterController(getApplicationContext());
    this.w.checkFilterValidity(false);
    this.w.addListener(this);
    if (paramBundle != null) {}
    int i;
    for (boolean bool = true;; bool = false)
    {
      this.x = bool;
      Log.d(m, this + " onCreate");
      setContentView(2130968773);
      H();
      if (x()) {
        w();
      }
      this.z = ((LinearLayout)findViewById(2131624454));
      a((Toolbar)findViewById(2131624110));
      this.A = ((FloatingActionButton)findViewById(2131624457));
      this.A.setOnClickListener(new de(this));
      this.u = new ContactsRequest();
      ((TabSwitch)findViewById(2131624119)).setOnCheckedChangeListener(new df(this));
      this.r = ((TabLayout)findViewById(2131624456));
      this.t = ((ViewPager)findViewById(2131624091));
      this.s = new a(getFragmentManager(), this);
      B.add(this);
      this.r.setTabMode(1);
      this.r.setTabGravity(0);
      this.t.setAdapter(this.s);
      this.r.setupWithViewPager(this.t);
      i = 0;
      while (i < this.r.getTabCount())
      {
        this.r.a(i).a(this.s.d(i));
        i += 1;
      }
    }
    this.t.a(new dg(this));
    this.r.requestFocus();
    Object localObject = getIntent();
    if (paramBundle != null)
    {
      d(paramBundle.getBoolean("hamburger"));
      this.D = paramBundle.getInt("selectedElement");
      this.G = paramBundle.getString("selectedChatroomName");
      if (paramBundle.containsKey("cameraImageUri")) {
        this.l = Uri.parse(paramBundle.getString("cameraImageUri"));
      }
      l();
      i = j;
      if (i != 0)
      {
        Application.a(InboxFragment.class.getSimpleName());
        this.t.setCurrentItem(Application.l);
        c(Application.l);
        this.D = 1;
        this.A.setImageDrawable(getResources().getDrawable(2130837609));
        this.A.a();
      }
      if (!E()) {
        break label860;
      }
      this.o = com.google.android.gms.b.a.a(this);
      this.p = a(this);
      if (!this.p.equals("")) {
        break label820;
      }
      G();
    }
    for (;;)
    {
      F();
      ge.a(this, 0L);
      paramBundle = new JSONObject();
      try
      {
        localObject = Arrays.asList(new String[] { "com.whatsapp", "com.facebook.orca", "jp.naver.line.android", "com.kakao.talk", "com.yahoo.mobile.client.android.im", "com.skype.raider", "com.bsb.hike", "kik.android", "com.tencent.mm", "com.viber.voip", "com.nimbuzz", "com.twitter.android", "com.snapchat.android", "com.dewmobile.kuaiya.play", "com.jott.android.jottmessenger", "com.lenovo.anyshare.gps", "com.ubercab", "com.ubercab.driver", "me.lyft.android", "com.grabtaxi.passenger", "com.grabtaxi.driver2" }).iterator();
        while (((Iterator)localObject).hasNext())
        {
          String str = (String)((Iterator)localObject).next();
          paramBundle.put(str, f(str));
        }
        if (localJSONException == null) {
          break label796;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        i.b("apps/present", paramBundle);
        return;
      }
      if ((localJSONException.getAction() != null) && (localJSONException.getAction().equals("android.intent.action.VIEW")))
      {
        i = j;
        if (b(localJSONException.getDataString(), localJSONException.getType())) {
          break;
        }
        i = 1;
        break;
      }
      label796:
      if ((localJSONException != null) && (localJSONException.getAction() != null)) {
        c(localJSONException);
      }
      i = 1;
      break;
      label820:
      Log.d(m, "GCM id:" + this.p);
      FireChat.userSetGcm(this.p);
      continue;
      label860:
      Log.i(m, "GCM: No valid Google Play Services APK found.");
    }
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    Log.d(m, this + " onNewIntent " + paramIntent);
    c(paramIntent);
    this.u = new ContactsRequest();
  }
  
  protected void onPause()
  {
    super.onPause();
    this.K = I();
    this.I = J();
    long l = this.I - this.H;
    float f1;
    float f2;
    JSONObject localJSONObject;
    if ((!this.F) && (this.K != 100.0F) && (l != 0L))
    {
      f1 = this.K - this.J;
      f2 = f1 / (float)l;
      localJSONObject = new JSONObject();
    }
    try
    {
      localJSONObject.put("battery_at_start", this.J);
      localJSONObject.put("battery_at_finish", this.K);
      localJSONObject.put("diff_in_battery", f1);
      localJSONObject.put("battery_per_second", f2);
      localJSONObject.put("time_in_app", l);
      i.b("battery_monitoring", localJSONObject);
      if (Application.b.e != null) {
        Application.b.e.b();
      }
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    E();
    new di(this).start();
    if (Application.b.e != null) {
      Application.b.e.a();
    }
    m();
    this.F = false;
    registerReceiver(new ct(this), new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    this.J = I();
    this.H = J();
    if (this.t != null) {
      e(this.t.getCurrentItem());
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    if ((g().a() & 0x4) == 0) {}
    for (boolean bool = true;; bool = false)
    {
      paramBundle.putBoolean("hamburger", bool);
      paramBundle.putInt("selectedElement", this.D);
      paramBundle.putString("selectedChatroomName", this.G);
      paramBundle.putInt("tabPosition", this.r.getSelectedTabPosition());
      if (this.l != null) {
        paramBundle.putString("cameraImageUri", this.l.toString());
      }
      super.onSaveInstanceState(paramBundle);
      return;
    }
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    Object localObject = gz.b();
    long l1 = J();
    long l2 = ((SharedPreferences)localObject).getLong("last_checked_wifi_bluetooth", 0L);
    SharedPreferences.Editor localEditor = ((SharedPreferences)localObject).edit();
    if (((SharedPreferences)localObject).getBoolean("always_turn_on_bluetooth_on_app_foreground", false)) {
      z();
    }
    boolean bool;
    if (l1 - l2 > 86400L)
    {
      paramBoolean = B();
      bool = C();
      if ((paramBoolean) || (bool))
      {
        localEditor.putLong("last_checked_wifi_bluetooth", J());
        Application.a(localEditor);
      }
      if ((!paramBoolean) || (!bool)) {
        break label247;
      }
      a(Application.b.getString(2131690221), new cv(this));
    }
    for (;;)
    {
      if ((J() - ((SharedPreferences)localObject).getLong("last_checked_irfan", 0L) > 86400L) && (D()))
      {
        localEditor.putLong("last_checked_irfan", J());
        Application.a(localEditor);
        localObject = new h.a(this);
        ((h.a)localObject).b(Application.b.getString(2131690110));
        ((h.a)localObject).b(17039360, new cy(this));
        ((h.a)localObject).a(17039370, new cz(this));
        ((h.a)localObject).b().show();
      }
      return;
      label247:
      if (paramBoolean) {
        a(Application.b.getString(2131690222), new cw(this));
      } else if (bool) {
        a(Application.b.getString(2131690223), new cx(this));
      }
    }
  }
  
  public Fragment p()
  {
    return getFragmentManager().findFragmentById(2131624091);
  }
  
  public void q()
  {
    startActivity(b("open_compose"));
  }
  
  public void r()
  {
    startActivity(b("open_settings"));
  }
  
  public void s()
  {
    startActivity(b("open_profile"));
  }
  
  public void shareGooglePlayLink(View paramView)
  {
    paramView = new Intent();
    paramView.setAction("android.intent.action.SEND");
    paramView.putExtra("android.intent.extra.TEXT", String.format(getResources().getText(2131690148).toString(), new Object[] { "http://firech.at/" }));
    paramView.setType("text/plain");
    startActivity(Intent.createChooser(paramView, getResources().getText(2131690161)));
  }
  
  public void t()
  {
    startActivity(b("open_firechat_profile"));
  }
  
  public void u()
  {
    startActivity(b("invite_contacts"));
  }
  
  public void v()
  {
    startActivity(b("discover"));
  }
  
  public void w()
  {
    Object localObject = getResources().getDisplayMetrics();
    int i = ((DisplayMetrics)localObject).widthPixels;
    int j = ((DisplayMetrics)localObject).heightPixels;
    localObject = new Dialog(this);
    ((Dialog)localObject).setCancelable(false);
    ((Dialog)localObject).setCanceledOnTouchOutside(false);
    ((Dialog)localObject).requestWindowFeature(1);
    ((Dialog)localObject).setContentView(2130968846);
    ((Dialog)localObject).getWindow().setBackgroundDrawable(new ColorDrawable(17170445));
    ((Dialog)localObject).getWindow().setLayout(i * 8 / 9, (int)(j * 7.5D) / 10);
    ViewPager localViewPager = (ViewPager)((Dialog)localObject).findViewById(2131624122);
    localViewPager.setAdapter(new ep("tutorial_slide_image", "tutorialSlideTitle", "tutorialSlideText", 4));
    ((CirclePageIndicator)((Dialog)localObject).findViewById(2131624190)).setViewPager(localViewPager);
    ((Button)((Dialog)localObject).findViewById(2131624602)).setOnClickListener(new dd(this, localViewPager, (Dialog)localObject));
    ((Dialog)localObject).show();
  }
  
  public boolean x()
  {
    return getIntent().getBooleanExtra("funnel", false);
  }
  
  void y()
  {
    ((WifiManager)getSystemService("wifi")).setWifiEnabled(true);
  }
  
  void z()
  {
    A();
    w.a().enable();
  }
  
  public class a
    extends d
  {
    public String[] a = { "Inbox", "Contacts", "Home", "More" };
    int[] b = { 2130837828, 2130837640, 2130837699, 2130837830 };
    int[] c = { 2130837829, 2130837641, 2130837700, 2130837831 };
    Fragment[] d = new Fragment[this.b.length];
    ImageView[] e = new ImageView[this.b.length];
    Context f;
    
    public a(FragmentManager paramFragmentManager, Context paramContext)
    {
      super();
      this.f = paramContext;
    }
    
    public Fragment a(int paramInt)
    {
      if (this.d[paramInt] != null) {
        return this.d[paramInt];
      }
      switch (paramInt)
      {
      default: 
        return null;
      case 0: 
        return new InboxFragment();
      case 1: 
        return new FollowFragment("my_contacts", "root");
      case 2: 
        return new ChatroomsHomeFragment();
      }
      return new MeFragment();
    }
    
    public Object a(ViewGroup paramViewGroup, int paramInt)
    {
      paramViewGroup = (Fragment)super.a(paramViewGroup, paramInt);
      this.d[paramInt] = paramViewGroup;
      return paramViewGroup;
    }
    
    public void a(boolean paramBoolean)
    {
      if (MainActivity.this.r != null)
      {
        if (paramBoolean)
        {
          MainActivity.this.r.setVisibility(0);
          MainActivity.this.g().c();
        }
      }
      else {
        return;
      }
      MainActivity.this.r.setVisibility(8);
      MainActivity.this.g().b();
    }
    
    public int b()
    {
      return this.b.length;
    }
    
    public CharSequence b(int paramInt)
    {
      return this.a[paramInt];
    }
    
    public void c()
    {
      if (this.d[2] != null) {
        ((ChatroomsHomeFragment)this.d[2]).a();
      }
    }
    
    public View d(int paramInt)
    {
      View localView = LayoutInflater.from(MainActivity.this).inflate(2130968840, null);
      Object localObject = localView.findViewById(2131624585);
      ImageView localImageView = (ImageView)localView.findViewById(2131624586);
      localImageView.setBackgroundResource(this.b[paramInt]);
      if (paramInt == 0)
      {
        localObject = new BadgeView(this.f, (View)localObject);
        ((BadgeView)localObject).setBadgePosition(6);
        ((BadgeView)localObject).setId(2131624373);
        ((BadgeView)localObject).setTextColor(this.f.getResources().getColor(2131296324));
        ((BadgeView)localObject).setBadgeBackgroundDrawableColor(this.f.getResources().getColor(2131296326));
        ((BadgeView)localObject).setBadgeMargin(0);
        ((BadgeView)localObject).setTextSize(1, 13.0F);
        localView.setSelected(true);
      }
      this.e[paramInt] = localImageView;
      return localView;
    }
  }
}
