package net.geekstools.floatshort.PRO.Category;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.geekstools.floatshort.PRO.Category.nav.AppSavedListAdapter;
import net.geekstools.floatshort.PRO.Category.nav.AppSelectionListAdapter;
import net.geekstools.floatshort.PRO.Util.Functions.FunctionsClass;
import net.geekstools.floatshort.PRO.Util.Functions.PublicVariable;
import net.geekstools.floatshort.PRO.Util.NavAdapter.NavDrawerItem;
import net.geekstools.imageview.customshapes.ShapesImage;

public class AppSelectionList
  extends Activity
  implements View.OnClickListener
{
  Drawable AppIcon;
  String AppName = "Application";
  String PackageName;
  Activity activity;
  AppSavedListAdapter advanceSavedListAdapter;
  RecyclerView.Adapter appSelectionListAdapter;
  List<ApplicationInfo> applicationInfoList;
  RelativeLayout confirmLayout;
  Context context;
  TextView counterView;
  TextView desc;
  FunctionsClass functionsClass;
  LinearLayout indexView;
  ListPopupWindow listPopupWindow;
  ImageView loadIcon;
  RecyclerView loadView;
  RelativeLayout loadingSplash;
  Map<String, Integer> mapIndex;
  ArrayList<NavDrawerItem> navDrawerItems;
  ArrayList<NavDrawerItem> navDrawerItemsSaved;
  ScrollView nestedScrollView;
  ShapesImage one;
  RelativeLayout popupAnchorView;
  LinearLayoutManager recyclerViewLayoutManager;
  boolean resetAdapter = false;
  TextView splitHint;
  ShapesImage tempIcon;
  ShapesImage two;
  RelativeLayout wholeAuto;
  
  public AppSelectionList() {}
  
  public void loadDataOff()
  {
    new LoadApplicationsOffLimited(null).execute(new Void[0]);
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    try
    {
      this.functionsClass.overrideBackPressToClass(CategoryHandler.class, this);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void onClick(View paramView)
  {
    if ((paramView instanceof TextView))
    {
      paramView = (TextView)paramView;
      this.nestedScrollView.smoothScrollTo(0, (int)this.loadView.getChildAt(((Integer)this.mapIndex.get(paramView.getText().toString())).intValue()).getY());
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427356);
    this.functionsClass = new FunctionsClass(getApplicationContext(), this);
    this.context = getApplicationContext();
    this.activity = this;
    this.listPopupWindow = new ListPopupWindow(this.activity);
    this.desc = ((TextView)findViewById(2131296381));
    this.counterView = ((TextView)findViewById(2131296366));
    this.loadIcon = ((ImageView)findViewById(2131296479));
    this.nestedScrollView = ((ScrollView)findViewById(2131296495));
    this.loadView = ((RecyclerView)findViewById(2131296476));
    this.popupAnchorView = ((RelativeLayout)findViewById(2131296528));
    this.indexView = ((LinearLayout)findViewById(2131296583));
    this.wholeAuto = ((RelativeLayout)findViewById(2131296672));
    this.loadingSplash = ((RelativeLayout)findViewById(2131296482));
    this.tempIcon = ((ShapesImage)findViewById(2131296625));
    this.tempIcon.bringToFront();
    this.one = this.functionsClass.initShapesImage(this, 2131296518);
    this.two = this.functionsClass.initShapesImage(this, 2131296660);
    this.splitHint = ((TextView)findViewById(2131296593));
    this.confirmLayout = ((RelativeLayout)findViewById(2131296360));
    this.confirmLayout.bringToFront();
    if (this.functionsClass.appThemeTransparent() == true) {
      this.functionsClass.setThemeColor(this.wholeAuto, true, PublicVariable.categoryName, "");
    } else {
      this.functionsClass.setThemeColor(this.wholeAuto, false, PublicVariable.categoryName, "");
    }
    this.recyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext(), 1, false);
    this.loadView.setLayoutManager(this.recyclerViewLayoutManager);
    this.navDrawerItems = new ArrayList();
    this.navDrawerItemsSaved = new ArrayList();
    this.mapIndex = new LinkedHashMap();
    paramBundle = Typeface.createFromAsset(getAssets(), "upcil.ttf");
    this.desc.setTypeface(paramBundle);
    this.desc.setText(PublicVariable.categoryName);
    this.counterView.setTypeface(paramBundle);
    this.counterView.bringToFront();
    paramBundle = (LayerDrawable)getDrawable(2131230933);
    ((BitmapDrawable)paramBundle.findDrawableByLayerId(2131296443)).setTint(PublicVariable.primaryColor);
    this.loadIcon.setImageDrawable(paramBundle);
    ((ProgressBar)findViewById(2131296480)).getIndeterminateDrawable().setColorFilter(getResources().getColor(2131099703), PorterDuff.Mode.MULTIPLY);
    paramBundle = new IntentFilter();
    paramBundle.addAction(this.context.getString(2131624039));
    paramBundle.addAction(this.context.getString(2131624168));
    paramBundle.addAction(this.context.getString(2131624169));
    paramBundle.addAction(this.context.getString(2131624016));
    paramBundle.addAction(this.context.getString(2131624189));
    BroadcastReceiver local1 = new BroadcastReceiver()
    {
      /* Error */
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        // Byte code:
        //   0: aload_2
        //   1: invokevirtual 33	android/content/Intent:getAction	()Ljava/lang/String;
        //   4: aload_1
        //   5: ldc 34
        //   7: invokevirtual 40	android/content/Context:getString	(I)Ljava/lang/String;
        //   10: invokevirtual 46	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   13: ifeq +30 -> 43
        //   16: aload_0
        //   17: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   20: getfield 50	net/geekstools/floatshort/PRO/Category/AppSelectionList:counterView	Landroid/widget/TextView;
        //   23: aload_0
        //   24: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   27: getfield 54	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   30: getstatic 60	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   33: invokevirtual 66	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:countLine	(Ljava/lang/String;)I
        //   36: invokestatic 69	java/lang/String:valueOf	(I)Ljava/lang/String;
        //   39: invokevirtual 75	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
        //   42: return
        //   43: aload_2
        //   44: invokevirtual 33	android/content/Intent:getAction	()Ljava/lang/String;
        //   47: aload_1
        //   48: ldc 76
        //   50: invokevirtual 40	android/content/Context:getString	(I)Ljava/lang/String;
        //   53: invokevirtual 46	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   56: ifeq +450 -> 506
        //   59: aload_0
        //   60: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   63: getstatic 60	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   66: invokevirtual 80	net/geekstools/floatshort/PRO/Category/AppSelectionList:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
        //   69: invokevirtual 86	java/io/File:exists	()Z
        //   72: ifeq +789 -> 861
        //   75: aload_0
        //   76: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   79: getfield 54	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   82: getstatic 60	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   85: invokevirtual 66	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:countLine	(Ljava/lang/String;)I
        //   88: ifle +773 -> 861
        //   91: aload_0
        //   92: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   95: getfield 90	net/geekstools/floatshort/PRO/Category/AppSelectionList:navDrawerItemsSaved	Ljava/util/ArrayList;
        //   98: invokevirtual 95	java/util/ArrayList:clear	()V
        //   101: aload_0
        //   102: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   105: getfield 54	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   108: getstatic 60	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   111: invokevirtual 99	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readFileLine	(Ljava/lang/String;)[Ljava/lang/String;
        //   114: astore_2
        //   115: aload_2
        //   116: arraylength
        //   117: istore 4
        //   119: iconst_0
        //   120: istore_3
        //   121: iload_3
        //   122: iload 4
        //   124: if_icmpge +59 -> 183
        //   127: aload_2
        //   128: iload_3
        //   129: aaload
        //   130: astore 5
        //   132: aload_0
        //   133: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   136: getfield 90	net/geekstools/floatshort/PRO/Category/AppSelectionList:navDrawerItemsSaved	Ljava/util/ArrayList;
        //   139: new 101	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem
        //   142: dup
        //   143: aload_0
        //   144: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   147: getfield 54	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   150: aload 5
        //   152: invokevirtual 105	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appName	(Ljava/lang/String;)Ljava/lang/String;
        //   155: aload 5
        //   157: aload_0
        //   158: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   161: getfield 54	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   164: aload 5
        //   166: invokevirtual 109	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
        //   169: invokespecial 112	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem:<init>	(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)V
        //   172: invokevirtual 115	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   175: pop
        //   176: iload_3
        //   177: iconst_1
        //   178: iadd
        //   179: istore_3
        //   180: goto -59 -> 121
        //   183: aload_0
        //   184: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   187: new 117	net/geekstools/floatshort/PRO/Category/nav/AppSavedListAdapter
        //   190: dup
        //   191: aload_0
        //   192: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   195: getfield 121	net/geekstools/floatshort/PRO/Category/AppSelectionList:activity	Landroid/app/Activity;
        //   198: aload_1
        //   199: aload_0
        //   200: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   203: getfield 90	net/geekstools/floatshort/PRO/Category/AppSelectionList:navDrawerItemsSaved	Ljava/util/ArrayList;
        //   206: iconst_1
        //   207: invokespecial 124	net/geekstools/floatshort/PRO/Category/nav/AppSavedListAdapter:<init>	(Landroid/app/Activity;Landroid/content/Context;Ljava/util/ArrayList;I)V
        //   210: putfield 128	net/geekstools/floatshort/PRO/Category/AppSelectionList:advanceSavedListAdapter	Lnet/geekstools/floatshort/PRO/Category/nav/AppSavedListAdapter;
        //   213: aload_0
        //   214: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   217: new 130	android/widget/ListPopupWindow
        //   220: dup
        //   221: aload_0
        //   222: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   225: getfield 121	net/geekstools/floatshort/PRO/Category/AppSelectionList:activity	Landroid/app/Activity;
        //   228: invokespecial 133	android/widget/ListPopupWindow:<init>	(Landroid/content/Context;)V
        //   231: putfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   234: aload_0
        //   235: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   238: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   241: aload_0
        //   242: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   245: getfield 128	net/geekstools/floatshort/PRO/Category/AppSelectionList:advanceSavedListAdapter	Lnet/geekstools/floatshort/PRO/Category/nav/AppSavedListAdapter;
        //   248: invokevirtual 141	android/widget/ListPopupWindow:setAdapter	(Landroid/widget/ListAdapter;)V
        //   251: aload_0
        //   252: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   255: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   258: aload_0
        //   259: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   262: getfield 145	net/geekstools/floatshort/PRO/Category/AppSelectionList:popupAnchorView	Landroid/widget/RelativeLayout;
        //   265: invokevirtual 149	android/widget/ListPopupWindow:setAnchorView	(Landroid/view/View;)V
        //   268: aload_0
        //   269: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   272: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   275: bipush -2
        //   277: invokevirtual 153	android/widget/ListPopupWindow:setWidth	(I)V
        //   280: aload_0
        //   281: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   284: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   287: bipush -2
        //   289: invokevirtual 156	android/widget/ListPopupWindow:setHeight	(I)V
        //   292: aload_0
        //   293: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   296: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   299: iconst_1
        //   300: invokevirtual 160	android/widget/ListPopupWindow:setModal	(Z)V
        //   303: aload_0
        //   304: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   307: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   310: aconst_null
        //   311: invokevirtual 164	android/widget/ListPopupWindow:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
        //   314: aload_0
        //   315: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   318: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   321: invokevirtual 167	android/widget/ListPopupWindow:show	()V
        //   324: aload_0
        //   325: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   328: getfield 54	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   331: invokevirtual 171	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:returnAPI	()I
        //   334: bipush 23
        //   336: if_icmpge +99 -> 435
        //   339: aload_0
        //   340: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   343: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   346: invokevirtual 174	android/widget/ListPopupWindow:isShowing	()Z
        //   349: ifeq +86 -> 435
        //   352: new 176	android/os/Handler
        //   355: dup
        //   356: invokespecial 177	android/os/Handler:<init>	()V
        //   359: astore_1
        //   360: new 11	net/geekstools/floatshort/PRO/Category/AppSelectionList$1$1
        //   363: dup
        //   364: aload_0
        //   365: invokespecial 180	net/geekstools/floatshort/PRO/Category/AppSelectionList$1$1:<init>	(Lnet/geekstools/floatshort/PRO/Category/AppSelectionList$1;)V
        //   368: astore_2
        //   369: goto +57 -> 426
        //   372: astore_1
        //   373: goto +81 -> 454
        //   376: astore_1
        //   377: aload_1
        //   378: invokevirtual 183	java/lang/Exception:printStackTrace	()V
        //   381: aload_0
        //   382: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   385: getfield 54	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   388: invokevirtual 171	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:returnAPI	()I
        //   391: bipush 23
        //   393: if_icmpge +42 -> 435
        //   396: aload_0
        //   397: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   400: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   403: invokevirtual 174	android/widget/ListPopupWindow:isShowing	()Z
        //   406: ifeq +29 -> 435
        //   409: new 176	android/os/Handler
        //   412: dup
        //   413: invokespecial 177	android/os/Handler:<init>	()V
        //   416: astore_1
        //   417: new 11	net/geekstools/floatshort/PRO/Category/AppSelectionList$1$1
        //   420: dup
        //   421: aload_0
        //   422: invokespecial 180	net/geekstools/floatshort/PRO/Category/AppSelectionList$1$1:<init>	(Lnet/geekstools/floatshort/PRO/Category/AppSelectionList$1;)V
        //   425: astore_2
        //   426: aload_1
        //   427: aload_2
        //   428: ldc2_w 184
        //   431: invokevirtual 189	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
        //   434: pop
        //   435: aload_0
        //   436: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   439: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   442: new 13	net/geekstools/floatshort/PRO/Category/AppSelectionList$1$2
        //   445: dup
        //   446: aload_0
        //   447: invokespecial 190	net/geekstools/floatshort/PRO/Category/AppSelectionList$1$2:<init>	(Lnet/geekstools/floatshort/PRO/Category/AppSelectionList$1;)V
        //   450: invokevirtual 194	android/widget/ListPopupWindow:setOnDismissListener	(Landroid/widget/PopupWindow$OnDismissListener;)V
        //   453: return
        //   454: aload_0
        //   455: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   458: getfield 54	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   461: invokevirtual 171	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:returnAPI	()I
        //   464: bipush 23
        //   466: if_icmpge +38 -> 504
        //   469: aload_0
        //   470: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   473: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   476: invokevirtual 174	android/widget/ListPopupWindow:isShowing	()Z
        //   479: ifeq +25 -> 504
        //   482: new 176	android/os/Handler
        //   485: dup
        //   486: invokespecial 177	android/os/Handler:<init>	()V
        //   489: new 11	net/geekstools/floatshort/PRO/Category/AppSelectionList$1$1
        //   492: dup
        //   493: aload_0
        //   494: invokespecial 180	net/geekstools/floatshort/PRO/Category/AppSelectionList$1$1:<init>	(Lnet/geekstools/floatshort/PRO/Category/AppSelectionList$1;)V
        //   497: ldc2_w 184
        //   500: invokevirtual 189	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
        //   503: pop
        //   504: aload_1
        //   505: athrow
        //   506: aload_2
        //   507: invokevirtual 33	android/content/Intent:getAction	()Ljava/lang/String;
        //   510: aload_0
        //   511: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   514: ldc -61
        //   516: invokevirtual 196	net/geekstools/floatshort/PRO/Category/AppSelectionList:getString	(I)Ljava/lang/String;
        //   519: invokevirtual 46	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   522: ifeq +20 -> 542
        //   525: aload_0
        //   526: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   529: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   532: invokevirtual 199	android/widget/ListPopupWindow:dismiss	()V
        //   535: return
        //   536: astore_1
        //   537: aload_1
        //   538: invokevirtual 183	java/lang/Exception:printStackTrace	()V
        //   541: return
        //   542: aload_2
        //   543: invokevirtual 33	android/content/Intent:getAction	()Ljava/lang/String;
        //   546: aload_0
        //   547: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   550: ldc -56
        //   552: invokevirtual 196	net/geekstools/floatshort/PRO/Category/AppSelectionList:getString	(I)Ljava/lang/String;
        //   555: invokevirtual 46	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   558: ifeq +52 -> 610
        //   561: aload_0
        //   562: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   565: iconst_1
        //   566: putfield 204	net/geekstools/floatshort/PRO/Category/AppSelectionList:resetAdapter	Z
        //   569: aload_0
        //   570: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   573: invokevirtual 207	net/geekstools/floatshort/PRO/Category/AppSelectionList:loadDataOff	()V
        //   576: aload_0
        //   577: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   580: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   583: invokevirtual 199	android/widget/ListPopupWindow:dismiss	()V
        //   586: aload_0
        //   587: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   590: new 29	android/content/Intent
        //   593: dup
        //   594: aload_0
        //   595: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   598: ldc -48
        //   600: invokevirtual 196	net/geekstools/floatshort/PRO/Category/AppSelectionList:getString	(I)Ljava/lang/String;
        //   603: invokespecial 211	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   606: invokevirtual 215	net/geekstools/floatshort/PRO/Category/AppSelectionList:sendBroadcast	(Landroid/content/Intent;)V
        //   609: return
        //   610: aload_2
        //   611: invokevirtual 33	android/content/Intent:getAction	()Ljava/lang/String;
        //   614: aload_0
        //   615: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   618: ldc -40
        //   620: invokevirtual 196	net/geekstools/floatshort/PRO/Category/AppSelectionList:getString	(I)Ljava/lang/String;
        //   623: invokevirtual 46	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   626: ifeq +235 -> 861
        //   629: aload_0
        //   630: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   633: getfield 137	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   636: invokevirtual 199	android/widget/ListPopupWindow:dismiss	()V
        //   639: aload_0
        //   640: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   643: astore_1
        //   644: new 218	java/lang/StringBuilder
        //   647: dup
        //   648: invokespecial 219	java/lang/StringBuilder:<init>	()V
        //   651: astore_2
        //   652: aload_2
        //   653: getstatic 60	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   656: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   659: pop
        //   660: aload_2
        //   661: ldc -31
        //   663: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   666: pop
        //   667: aload_1
        //   668: aload_2
        //   669: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   672: invokevirtual 80	net/geekstools/floatshort/PRO/Category/AppSelectionList:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
        //   675: invokevirtual 86	java/io/File:exists	()Z
        //   678: ifeq +72 -> 750
        //   681: aload_0
        //   682: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   685: getfield 232	net/geekstools/floatshort/PRO/Category/AppSelectionList:one	Lnet/geekstools/imageview/customshapes/ShapesImage;
        //   688: astore_1
        //   689: aload_0
        //   690: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   693: getfield 54	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   696: astore_2
        //   697: aload_0
        //   698: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   701: getfield 54	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   704: astore 5
        //   706: new 218	java/lang/StringBuilder
        //   709: dup
        //   710: invokespecial 219	java/lang/StringBuilder:<init>	()V
        //   713: astore 6
        //   715: aload 6
        //   717: getstatic 60	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   720: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   723: pop
        //   724: aload 6
        //   726: ldc -31
        //   728: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   731: pop
        //   732: aload_1
        //   733: aload_2
        //   734: aload 5
        //   736: aload 6
        //   738: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   741: invokevirtual 235	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readFile	(Ljava/lang/String;)Ljava/lang/String;
        //   744: invokevirtual 109	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
        //   747: invokevirtual 240	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
        //   750: aload_0
        //   751: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   754: astore_1
        //   755: new 218	java/lang/StringBuilder
        //   758: dup
        //   759: invokespecial 219	java/lang/StringBuilder:<init>	()V
        //   762: astore_2
        //   763: aload_2
        //   764: getstatic 60	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   767: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   770: pop
        //   771: aload_2
        //   772: ldc -14
        //   774: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   777: pop
        //   778: aload_1
        //   779: aload_2
        //   780: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   783: invokevirtual 80	net/geekstools/floatshort/PRO/Category/AppSelectionList:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
        //   786: invokevirtual 86	java/io/File:exists	()Z
        //   789: ifeq +72 -> 861
        //   792: aload_0
        //   793: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   796: getfield 245	net/geekstools/floatshort/PRO/Category/AppSelectionList:two	Lnet/geekstools/imageview/customshapes/ShapesImage;
        //   799: astore_1
        //   800: aload_0
        //   801: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   804: getfield 54	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   807: astore_2
        //   808: aload_0
        //   809: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$1:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   812: getfield 54	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   815: astore 5
        //   817: new 218	java/lang/StringBuilder
        //   820: dup
        //   821: invokespecial 219	java/lang/StringBuilder:<init>	()V
        //   824: astore 6
        //   826: aload 6
        //   828: getstatic 60	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   831: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   834: pop
        //   835: aload 6
        //   837: ldc -14
        //   839: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   842: pop
        //   843: aload_1
        //   844: aload_2
        //   845: aload 5
        //   847: aload 6
        //   849: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   852: invokevirtual 235	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readFile	(Ljava/lang/String;)Ljava/lang/String;
        //   855: invokevirtual 109	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
        //   858: invokevirtual 240	net/geekstools/imageview/customshapes/ShapesImage:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
        //   861: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	862	0	this	1
        //   0	862	1	paramAnonymousContext	Context
        //   0	862	2	paramAnonymousIntent	Intent
        //   120	60	3	i	int
        //   117	8	4	j	int
        //   130	716	5	localObject	Object
        //   713	135	6	localStringBuilder	StringBuilder
        // Exception table:
        //   from	to	target	type
        //   314	324	372	finally
        //   377	381	372	finally
        //   314	324	376	java/lang/Exception
        //   525	535	536	java/lang/Exception
      }
    };
    this.context.registerReceiver(local1, paramBundle);
    loadDataOff();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onPause()
  {
    super.onPause();
    finish();
  }
  
  public void onResume()
  {
    super.onResume();
  }
  
  public void onStart()
  {
    super.onStart();
    if (this.functionsClass.returnAPI() < 24)
    {
      this.one.setVisibility(4);
      this.two.setVisibility(4);
      this.splitHint.setVisibility(4);
      int i = (int)TypedValue.applyDimension(1, 5.0F, getResources().getDisplayMetrics());
      this.loadView.setPaddingRelative(this.loadView.getPaddingStart(), i, this.loadView.getPaddingEnd(), this.loadView.getPaddingBottom());
      return;
    }
    this.splitHint.setTextColor(PublicVariable.primaryColorOpposite);
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(PublicVariable.categoryName);
    ((StringBuilder)localObject).append(".SplitOne");
    FunctionsClass localFunctionsClass1;
    FunctionsClass localFunctionsClass2;
    StringBuilder localStringBuilder;
    if (getFileStreamPath(((StringBuilder)localObject).toString()).exists())
    {
      localObject = this.one;
      localFunctionsClass1 = this.functionsClass;
      localFunctionsClass2 = this.functionsClass;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(PublicVariable.categoryName);
      localStringBuilder.append(".SplitOne");
      ((ShapesImage)localObject).setImageDrawable(localFunctionsClass1.shapedAppIcon(localFunctionsClass2.readFile(localStringBuilder.toString())));
    }
    else
    {
      localObject = getDrawable(2131230809);
      ((Drawable)localObject).setTint(this.functionsClass.setColorAlpha(PublicVariable.primaryColorOpposite, 175.0F));
      this.one.setImageDrawable((Drawable)localObject);
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(PublicVariable.categoryName);
    ((StringBuilder)localObject).append(".SplitTwo");
    if (getFileStreamPath(((StringBuilder)localObject).toString()).exists())
    {
      localObject = this.two;
      localFunctionsClass1 = this.functionsClass;
      localFunctionsClass2 = this.functionsClass;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(PublicVariable.categoryName);
      localStringBuilder.append(".SplitTwo");
      ((ShapesImage)localObject).setImageDrawable(localFunctionsClass1.shapedAppIcon(localFunctionsClass2.readFile(localStringBuilder.toString())));
    }
    else
    {
      localObject = getDrawable(2131230809);
      ((Drawable)localObject).setTint(this.functionsClass.setColorAlpha(PublicVariable.primaryColorOpposite, 175.0F));
      this.two.setImageDrawable((Drawable)localObject);
    }
    this.one.setOnClickListener(new View.OnClickListener()
    {
      /* Error */
      public void onClick(View paramAnonymousView)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   4: getstatic 32	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   7: invokevirtual 36	net/geekstools/floatshort/PRO/Category/AppSelectionList:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
        //   10: invokevirtual 42	java/io/File:exists	()Z
        //   13: ifeq +364 -> 377
        //   16: aload_0
        //   17: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   20: getfield 46	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   23: getstatic 32	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   26: invokevirtual 52	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:countLine	(Ljava/lang/String;)I
        //   29: ifle +348 -> 377
        //   32: aload_0
        //   33: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   36: getfield 56	net/geekstools/floatshort/PRO/Category/AppSelectionList:navDrawerItemsSaved	Ljava/util/ArrayList;
        //   39: invokevirtual 61	java/util/ArrayList:clear	()V
        //   42: aload_0
        //   43: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   46: getfield 46	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   49: getstatic 32	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   52: invokevirtual 65	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readFileLine	(Ljava/lang/String;)[Ljava/lang/String;
        //   55: astore_1
        //   56: aload_1
        //   57: arraylength
        //   58: istore_3
        //   59: iconst_0
        //   60: istore_2
        //   61: iload_2
        //   62: iload_3
        //   63: if_icmpge +59 -> 122
        //   66: aload_1
        //   67: iload_2
        //   68: aaload
        //   69: astore 4
        //   71: aload_0
        //   72: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   75: getfield 56	net/geekstools/floatshort/PRO/Category/AppSelectionList:navDrawerItemsSaved	Ljava/util/ArrayList;
        //   78: new 67	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem
        //   81: dup
        //   82: aload_0
        //   83: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   86: getfield 46	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   89: aload 4
        //   91: invokevirtual 71	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appName	(Ljava/lang/String;)Ljava/lang/String;
        //   94: aload 4
        //   96: aload_0
        //   97: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   100: getfield 46	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   103: aload 4
        //   105: invokevirtual 75	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
        //   108: invokespecial 78	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem:<init>	(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)V
        //   111: invokevirtual 82	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   114: pop
        //   115: iload_2
        //   116: iconst_1
        //   117: iadd
        //   118: istore_2
        //   119: goto -58 -> 61
        //   122: aload_0
        //   123: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   126: new 84	net/geekstools/floatshort/PRO/Category/nav/AppSavedListAdapter
        //   129: dup
        //   130: aload_0
        //   131: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   134: getfield 88	net/geekstools/floatshort/PRO/Category/AppSelectionList:activity	Landroid/app/Activity;
        //   137: aload_0
        //   138: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   141: getfield 92	net/geekstools/floatshort/PRO/Category/AppSelectionList:context	Landroid/content/Context;
        //   144: aload_0
        //   145: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   148: getfield 56	net/geekstools/floatshort/PRO/Category/AppSelectionList:navDrawerItemsSaved	Ljava/util/ArrayList;
        //   151: iconst_1
        //   152: invokespecial 95	net/geekstools/floatshort/PRO/Category/nav/AppSavedListAdapter:<init>	(Landroid/app/Activity;Landroid/content/Context;Ljava/util/ArrayList;I)V
        //   155: putfield 99	net/geekstools/floatshort/PRO/Category/AppSelectionList:advanceSavedListAdapter	Lnet/geekstools/floatshort/PRO/Category/nav/AppSavedListAdapter;
        //   158: aload_0
        //   159: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   162: new 101	android/widget/ListPopupWindow
        //   165: dup
        //   166: aload_0
        //   167: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   170: getfield 88	net/geekstools/floatshort/PRO/Category/AppSelectionList:activity	Landroid/app/Activity;
        //   173: invokespecial 104	android/widget/ListPopupWindow:<init>	(Landroid/content/Context;)V
        //   176: putfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   179: aload_0
        //   180: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   183: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   186: aload_0
        //   187: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   190: getfield 99	net/geekstools/floatshort/PRO/Category/AppSelectionList:advanceSavedListAdapter	Lnet/geekstools/floatshort/PRO/Category/nav/AppSavedListAdapter;
        //   193: invokevirtual 112	android/widget/ListPopupWindow:setAdapter	(Landroid/widget/ListAdapter;)V
        //   196: aload_0
        //   197: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   200: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   203: aload_0
        //   204: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   207: getfield 116	net/geekstools/floatshort/PRO/Category/AppSelectionList:popupAnchorView	Landroid/widget/RelativeLayout;
        //   210: invokevirtual 119	android/widget/ListPopupWindow:setAnchorView	(Landroid/view/View;)V
        //   213: aload_0
        //   214: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   217: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   220: bipush -2
        //   222: invokevirtual 123	android/widget/ListPopupWindow:setWidth	(I)V
        //   225: aload_0
        //   226: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   229: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   232: bipush -2
        //   234: invokevirtual 126	android/widget/ListPopupWindow:setHeight	(I)V
        //   237: aload_0
        //   238: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   241: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   244: iconst_1
        //   245: invokevirtual 130	android/widget/ListPopupWindow:setModal	(Z)V
        //   248: aload_0
        //   249: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   252: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   255: aconst_null
        //   256: invokevirtual 134	android/widget/ListPopupWindow:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
        //   259: aload_0
        //   260: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   263: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   266: invokevirtual 137	android/widget/ListPopupWindow:show	()V
        //   269: aload_0
        //   270: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   273: astore_1
        //   274: new 139	android/content/Intent
        //   277: dup
        //   278: aload_0
        //   279: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   282: ldc -116
        //   284: invokevirtual 144	net/geekstools/floatshort/PRO/Category/AppSelectionList:getString	(I)Ljava/lang/String;
        //   287: invokespecial 147	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   290: astore 4
        //   292: goto +35 -> 327
        //   295: astore_1
        //   296: goto +56 -> 352
        //   299: astore_1
        //   300: aload_1
        //   301: invokevirtual 150	java/lang/Exception:printStackTrace	()V
        //   304: aload_0
        //   305: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   308: astore_1
        //   309: new 139	android/content/Intent
        //   312: dup
        //   313: aload_0
        //   314: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   317: ldc -116
        //   319: invokevirtual 144	net/geekstools/floatshort/PRO/Category/AppSelectionList:getString	(I)Ljava/lang/String;
        //   322: invokespecial 147	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   325: astore 4
        //   327: aload_1
        //   328: aload 4
        //   330: invokevirtual 154	net/geekstools/floatshort/PRO/Category/AppSelectionList:sendBroadcast	(Landroid/content/Intent;)V
        //   333: aload_0
        //   334: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   337: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   340: new 13	net/geekstools/floatshort/PRO/Category/AppSelectionList$2$1
        //   343: dup
        //   344: aload_0
        //   345: invokespecial 157	net/geekstools/floatshort/PRO/Category/AppSelectionList$2$1:<init>	(Lnet/geekstools/floatshort/PRO/Category/AppSelectionList$2;)V
        //   348: invokevirtual 161	android/widget/ListPopupWindow:setOnDismissListener	(Landroid/widget/PopupWindow$OnDismissListener;)V
        //   351: return
        //   352: aload_0
        //   353: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   356: new 139	android/content/Intent
        //   359: dup
        //   360: aload_0
        //   361: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$2:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   364: ldc -116
        //   366: invokevirtual 144	net/geekstools/floatshort/PRO/Category/AppSelectionList:getString	(I)Ljava/lang/String;
        //   369: invokespecial 147	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   372: invokevirtual 154	net/geekstools/floatshort/PRO/Category/AppSelectionList:sendBroadcast	(Landroid/content/Intent;)V
        //   375: aload_1
        //   376: athrow
        //   377: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	378	0	this	2
        //   0	378	1	paramAnonymousView	View
        //   60	59	2	i	int
        //   58	6	3	j	int
        //   69	260	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   259	269	295	finally
        //   300	304	295	finally
        //   259	269	299	java/lang/Exception
      }
    });
    this.two.setOnClickListener(new View.OnClickListener()
    {
      /* Error */
      public void onClick(View paramAnonymousView)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   4: getstatic 32	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   7: invokevirtual 36	net/geekstools/floatshort/PRO/Category/AppSelectionList:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
        //   10: invokevirtual 42	java/io/File:exists	()Z
        //   13: ifeq +364 -> 377
        //   16: aload_0
        //   17: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   20: getfield 46	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   23: getstatic 32	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   26: invokevirtual 52	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:countLine	(Ljava/lang/String;)I
        //   29: ifle +348 -> 377
        //   32: aload_0
        //   33: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   36: getfield 56	net/geekstools/floatshort/PRO/Category/AppSelectionList:navDrawerItemsSaved	Ljava/util/ArrayList;
        //   39: invokevirtual 61	java/util/ArrayList:clear	()V
        //   42: aload_0
        //   43: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   46: getfield 46	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   49: getstatic 32	net/geekstools/floatshort/PRO/Util/Functions/PublicVariable:categoryName	Ljava/lang/String;
        //   52: invokevirtual 65	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:readFileLine	(Ljava/lang/String;)[Ljava/lang/String;
        //   55: astore_1
        //   56: aload_1
        //   57: arraylength
        //   58: istore_3
        //   59: iconst_0
        //   60: istore_2
        //   61: iload_2
        //   62: iload_3
        //   63: if_icmpge +59 -> 122
        //   66: aload_1
        //   67: iload_2
        //   68: aaload
        //   69: astore 4
        //   71: aload_0
        //   72: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   75: getfield 56	net/geekstools/floatshort/PRO/Category/AppSelectionList:navDrawerItemsSaved	Ljava/util/ArrayList;
        //   78: new 67	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem
        //   81: dup
        //   82: aload_0
        //   83: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   86: getfield 46	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   89: aload 4
        //   91: invokevirtual 71	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:appName	(Ljava/lang/String;)Ljava/lang/String;
        //   94: aload 4
        //   96: aload_0
        //   97: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   100: getfield 46	net/geekstools/floatshort/PRO/Category/AppSelectionList:functionsClass	Lnet/geekstools/floatshort/PRO/Util/Functions/FunctionsClass;
        //   103: aload 4
        //   105: invokevirtual 75	net/geekstools/floatshort/PRO/Util/Functions/FunctionsClass:shapedAppIcon	(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
        //   108: invokespecial 78	net/geekstools/floatshort/PRO/Util/NavAdapter/NavDrawerItem:<init>	(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)V
        //   111: invokevirtual 82	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   114: pop
        //   115: iload_2
        //   116: iconst_1
        //   117: iadd
        //   118: istore_2
        //   119: goto -58 -> 61
        //   122: aload_0
        //   123: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   126: new 84	net/geekstools/floatshort/PRO/Category/nav/AppSavedListAdapter
        //   129: dup
        //   130: aload_0
        //   131: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   134: getfield 88	net/geekstools/floatshort/PRO/Category/AppSelectionList:activity	Landroid/app/Activity;
        //   137: aload_0
        //   138: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   141: getfield 92	net/geekstools/floatshort/PRO/Category/AppSelectionList:context	Landroid/content/Context;
        //   144: aload_0
        //   145: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   148: getfield 56	net/geekstools/floatshort/PRO/Category/AppSelectionList:navDrawerItemsSaved	Ljava/util/ArrayList;
        //   151: iconst_2
        //   152: invokespecial 95	net/geekstools/floatshort/PRO/Category/nav/AppSavedListAdapter:<init>	(Landroid/app/Activity;Landroid/content/Context;Ljava/util/ArrayList;I)V
        //   155: putfield 99	net/geekstools/floatshort/PRO/Category/AppSelectionList:advanceSavedListAdapter	Lnet/geekstools/floatshort/PRO/Category/nav/AppSavedListAdapter;
        //   158: aload_0
        //   159: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   162: new 101	android/widget/ListPopupWindow
        //   165: dup
        //   166: aload_0
        //   167: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   170: getfield 88	net/geekstools/floatshort/PRO/Category/AppSelectionList:activity	Landroid/app/Activity;
        //   173: invokespecial 104	android/widget/ListPopupWindow:<init>	(Landroid/content/Context;)V
        //   176: putfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   179: aload_0
        //   180: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   183: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   186: aload_0
        //   187: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   190: getfield 99	net/geekstools/floatshort/PRO/Category/AppSelectionList:advanceSavedListAdapter	Lnet/geekstools/floatshort/PRO/Category/nav/AppSavedListAdapter;
        //   193: invokevirtual 112	android/widget/ListPopupWindow:setAdapter	(Landroid/widget/ListAdapter;)V
        //   196: aload_0
        //   197: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   200: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   203: aload_0
        //   204: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   207: getfield 116	net/geekstools/floatshort/PRO/Category/AppSelectionList:popupAnchorView	Landroid/widget/RelativeLayout;
        //   210: invokevirtual 119	android/widget/ListPopupWindow:setAnchorView	(Landroid/view/View;)V
        //   213: aload_0
        //   214: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   217: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   220: bipush -2
        //   222: invokevirtual 123	android/widget/ListPopupWindow:setWidth	(I)V
        //   225: aload_0
        //   226: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   229: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   232: bipush -2
        //   234: invokevirtual 126	android/widget/ListPopupWindow:setHeight	(I)V
        //   237: aload_0
        //   238: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   241: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   244: iconst_1
        //   245: invokevirtual 130	android/widget/ListPopupWindow:setModal	(Z)V
        //   248: aload_0
        //   249: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   252: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   255: aconst_null
        //   256: invokevirtual 134	android/widget/ListPopupWindow:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
        //   259: aload_0
        //   260: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   263: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   266: invokevirtual 137	android/widget/ListPopupWindow:show	()V
        //   269: aload_0
        //   270: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   273: astore_1
        //   274: new 139	android/content/Intent
        //   277: dup
        //   278: aload_0
        //   279: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   282: ldc -116
        //   284: invokevirtual 144	net/geekstools/floatshort/PRO/Category/AppSelectionList:getString	(I)Ljava/lang/String;
        //   287: invokespecial 147	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   290: astore 4
        //   292: goto +35 -> 327
        //   295: astore_1
        //   296: goto +56 -> 352
        //   299: astore_1
        //   300: aload_1
        //   301: invokevirtual 150	java/lang/Exception:printStackTrace	()V
        //   304: aload_0
        //   305: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   308: astore_1
        //   309: new 139	android/content/Intent
        //   312: dup
        //   313: aload_0
        //   314: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   317: ldc -116
        //   319: invokevirtual 144	net/geekstools/floatshort/PRO/Category/AppSelectionList:getString	(I)Ljava/lang/String;
        //   322: invokespecial 147	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   325: astore 4
        //   327: aload_1
        //   328: aload 4
        //   330: invokevirtual 154	net/geekstools/floatshort/PRO/Category/AppSelectionList:sendBroadcast	(Landroid/content/Intent;)V
        //   333: aload_0
        //   334: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   337: getfield 108	net/geekstools/floatshort/PRO/Category/AppSelectionList:listPopupWindow	Landroid/widget/ListPopupWindow;
        //   340: new 13	net/geekstools/floatshort/PRO/Category/AppSelectionList$3$1
        //   343: dup
        //   344: aload_0
        //   345: invokespecial 157	net/geekstools/floatshort/PRO/Category/AppSelectionList$3$1:<init>	(Lnet/geekstools/floatshort/PRO/Category/AppSelectionList$3;)V
        //   348: invokevirtual 161	android/widget/ListPopupWindow:setOnDismissListener	(Landroid/widget/PopupWindow$OnDismissListener;)V
        //   351: return
        //   352: aload_0
        //   353: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   356: new 139	android/content/Intent
        //   359: dup
        //   360: aload_0
        //   361: getfield 19	net/geekstools/floatshort/PRO/Category/AppSelectionList$3:this$0	Lnet/geekstools/floatshort/PRO/Category/AppSelectionList;
        //   364: ldc -116
        //   366: invokevirtual 144	net/geekstools/floatshort/PRO/Category/AppSelectionList:getString	(I)Ljava/lang/String;
        //   369: invokespecial 147	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   372: invokevirtual 154	net/geekstools/floatshort/PRO/Category/AppSelectionList:sendBroadcast	(Landroid/content/Intent;)V
        //   375: aload_1
        //   376: athrow
        //   377: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	378	0	this	3
        //   0	378	1	paramAnonymousView	View
        //   60	59	2	i	int
        //   58	6	3	j	int
        //   69	260	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   259	269	295	finally
        //   300	304	295	finally
        //   259	269	299	java/lang/Exception
      }
    });
  }
  
  public void onStop()
  {
    super.onStop();
  }
  
  private class LoadApplicationsIndex
    extends AsyncTask<Void, Void, Void>
  {
    private LoadApplicationsIndex() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      int i = 0;
      while (i < AppSelectionList.this.navDrawerItems.size())
      {
        try
        {
          paramVarArgs = ((NavDrawerItem)AppSelectionList.this.navDrawerItems.get(i)).getDesc().substring(0, 1).toUpperCase();
          if (AppSelectionList.this.mapIndex.get(paramVarArgs) == null) {
            AppSelectionList.this.mapIndex.put(paramVarArgs, Integer.valueOf(i));
          }
        }
        catch (Exception paramVarArgs)
        {
          paramVarArgs.printStackTrace();
        }
        i += 1;
      }
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      paramVoid = (LayerDrawable)AppSelectionList.this.getResources().getDrawable(2131230887);
      ((GradientDrawable)paramVoid.findDrawableByLayerId(2131296312)).setColor(0);
      Iterator localIterator = new ArrayList(AppSelectionList.this.mapIndex.keySet()).iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        TextView localTextView = (TextView)AppSelectionList.this.getLayoutInflater().inflate(2131427467, null);
        localTextView.setBackground(paramVoid);
        localTextView.setText(str.toUpperCase());
        localTextView.setTextColor(PublicVariable.colorLightDarkOpposite);
        localTextView.setOnClickListener(AppSelectionList.this);
        AppSelectionList.this.indexView.addView(localTextView);
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      AppSelectionList.this.indexView.removeAllViews();
    }
  }
  
  private class LoadApplicationsOffLimited
    extends AsyncTask<Void, Void, Void>
  {
    private LoadApplicationsOffLimited() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      for (;;)
      {
        int i;
        try
        {
          paramVarArgs = AppSelectionList.this;
          PackageManager localPackageManager = AppSelectionList.this.getApplicationContext().getPackageManager();
          i = 0;
          paramVarArgs.applicationInfoList = localPackageManager.getInstalledApplications(0);
          Collections.sort(AppSelectionList.this.applicationInfoList, new ApplicationInfo.DisplayNameComparator(AppSelectionList.this.getPackageManager()));
          if (i < AppSelectionList.this.applicationInfoList.size())
          {
            paramVarArgs = AppSelectionList.this.getApplicationContext().getPackageManager().getLaunchIntentForPackage(((ApplicationInfo)AppSelectionList.this.applicationInfoList.get(i)).packageName);
            if (paramVarArgs == null) {
              break label286;
            }
            try
            {
              AppSelectionList.this.PackageName = ((ApplicationInfo)AppSelectionList.this.applicationInfoList.get(i)).packageName;
              AppSelectionList.this.AppName = AppSelectionList.this.functionsClass.appName(AppSelectionList.this.PackageName);
              AppSelectionList.this.AppIcon = AppSelectionList.this.functionsClass.shapedAppIcon(AppSelectionList.this.PackageName);
              AppSelectionList.this.navDrawerItems.add(new NavDrawerItem(AppSelectionList.this.AppName, AppSelectionList.this.PackageName, AppSelectionList.this.AppIcon));
            }
            catch (Exception paramVarArgs)
            {
              paramVarArgs.printStackTrace();
            }
          }
          AppSelectionList.this.appSelectionListAdapter = new AppSelectionListAdapter(AppSelectionList.this.activity, AppSelectionList.this.context, AppSelectionList.this.navDrawerItems);
        }
        catch (Exception paramVarArgs)
        {
          paramVarArgs.printStackTrace();
          cancel(true);
          AppSelectionList.this.finish();
        }
        return null;
        label286:
        i += 1;
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      AppSelectionList.this.loadView.setAdapter(AppSelectionList.this.appSelectionListAdapter);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          Animation localAnimation = AnimationUtils.loadAnimation(AppSelectionList.this.context, 17432577);
          AppSelectionList.this.loadingSplash.setVisibility(4);
          if (!AppSelectionList.this.resetAdapter) {
            AppSelectionList.this.loadingSplash.startAnimation(localAnimation);
          }
          AppSelectionList.this.context.sendBroadcast(new Intent(AppSelectionList.this.context.getString(2131624226)));
          localAnimation = AnimationUtils.loadAnimation(AppSelectionList.this.context, 17432576);
          AppSelectionList.this.counterView.startAnimation(localAnimation);
          localAnimation.setAnimationListener(new Animation.AnimationListener()
          {
            public void onAnimationEnd(Animation paramAnonymous2Animation)
            {
              AppSelectionList.this.counterView.setVisibility(0);
            }
            
            public void onAnimationRepeat(Animation paramAnonymous2Animation) {}
            
            public void onAnimationStart(Animation paramAnonymous2Animation)
            {
              AppSelectionList.this.counterView.setText(String.valueOf(AppSelectionList.this.functionsClass.countLine(PublicVariable.categoryName)));
            }
          });
          AppSelectionList.this.resetAdapter = false;
        }
      }, 100L);
      new AppSelectionList.LoadApplicationsIndex(AppSelectionList.this, null).execute(new Void[0]);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      AppSelectionList.this.indexView.removeAllViews();
    }
  }
}
