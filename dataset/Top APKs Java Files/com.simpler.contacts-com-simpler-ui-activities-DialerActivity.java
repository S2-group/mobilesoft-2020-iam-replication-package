package com.simpler.ui.activities;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract.Contacts;
import android.provider.Settings.System;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.algolia.search.Hit;
import com.algolia.search.Index;
import com.algolia.search.IndexListener;
import com.algolia.search.SearchQuery;
import com.algolia.search.SearchResult;
import com.simpler.data.contact.AlgoContact;
import com.simpler.data.contact.ContactPhone;
import com.simpler.logic.ConfigurationLogic;
import com.simpler.logic.ConfigurationLogic.OnConfigurationFileSetListener;
import com.simpler.logic.ContactsLogic;
import com.simpler.logic.IndexLogic;
import com.simpler.logic.LogicManager;
import com.simpler.logic.LoginLogic;
import com.simpler.logic.MergeLogic;
import com.simpler.logic.PackageLogic;
import com.simpler.logic.RateLogic;
import com.simpler.logic.SettingsLogic;
import com.simpler.logic.TasksLogic;
import com.simpler.logic.UpgradeLogic;
import com.simpler.logic.UploadLogic;
import com.simpler.ui.adapters.AlgoListAdapter;
import com.simpler.ui.fragments.home.CallLogFragmentOld;
import com.simpler.ui.views.DialpadView;
import com.simpler.ui.views.DialpadView.OnDialpadListener;
import com.simpler.utils.AnalyticsUtils;
import com.simpler.utils.AnalyticsUtils.AnalyticsDialType;
import com.simpler.utils.DialogUtils;
import com.simpler.utils.FilesUtils;
import com.simpler.utils.Logger;
import com.simpler.utils.PermissionUtils;
import com.simpler.utils.StringsUtils;
import com.simpler.utils.ThemeUtils;
import com.simpler.utils.UiUtils;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DialerActivity
  extends BaseActivity
  implements View.OnClickListener, AdapterView.OnItemClickListener, IndexListener<AlgoContact>, ConfigurationLogic.OnConfigurationFileSetListener, DialpadView.OnDialpadListener
{
  private final String a = "tel";
  private final String b = "DIALER_CALLLOG_FRAGMENT_TAG";
  private final String c = "HIDE_DIALPAD";
  private final int d = 16;
  private final float e = 0.54F;
  private FrameLayout f;
  private DialpadView g;
  private FloatingActionButton h;
  private Index<AlgoContact> i;
  private ListView j;
  private ArrayList<AlgoContact> k;
  private AlgoListAdapter l;
  private CardView m;
  private EditText n;
  private boolean o;
  private boolean p;
  private boolean q = false;
  private boolean r = false;
  private View s;
  private View t;
  private int u;
  
  public DialerActivity() {}
  
  private void A()
  {
    this.f.setVisibility(0);
    this.j.setVisibility(8);
  }
  
  private void B()
  {
    FilesUtils.saveToPreferences("missed_call_notification_click", false);
    C();
    new Handler().postDelayed(new ah(this), 'ú');
    finish();
  }
  
  private void C()
  {
    Object localObject1 = new Intent();
    ((Intent)localObject1).setAction("android.intent.action.VIEW");
    ((Intent)localObject1).setType("vnd.android.cursor.dir/calls");
    PackageManager localPackageManager = getPackageManager();
    localObject1 = localPackageManager.queryIntentActivities((Intent)localObject1, 65536).iterator();
    for (;;)
    {
      Object localObject2;
      Object localObject3;
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ResolveInfo)((Iterator)localObject1).next();
        localObject3 = ((ResolveInfo)localObject2).activityInfo.packageName;
      }
      try
      {
        if ((localPackageManager.getApplicationInfo((String)localObject3, 0).flags & 0x1) != 0)
        {
          localObject2 = ((ResolveInfo)localObject2).activityInfo;
          localObject2 = new ComponentName(((ActivityInfo)localObject2).applicationInfo.packageName, ((ActivityInfo)localObject2).name);
          localObject3 = new Intent("android.intent.action.MAIN");
          ((Intent)localObject3).setFlags(1082195968);
          ((Intent)localObject3).setComponent((ComponentName)localObject2);
          startActivity((Intent)localObject3);
          Logger.w("Simpler", "-- open native app!");
          return;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
  }
  
  private void D()
  {
    if (!FilesUtils.getBooleanFromPreferences("search_dups_on_startup", true)) {}
    MergeLogic localMergeLogic;
    do
    {
      do
      {
        return;
      } while ((!PermissionUtils.hasContactsPermissions(this)) || (!PackageLogic.getInstance().shouldRunFindDuplicatesBackgroundTask(this)));
      localMergeLogic = MergeLogic.getInstance();
    } while ((localMergeLogic.isMergeActivityVisible()) || (localMergeLogic.isFindingDuplicates()));
    new Handler().postDelayed(new ai(this), TimeUnit.SECONDS.toMillis(5L));
  }
  
  private void a()
  {
    this.m = ((CardView)findViewById(2131558819));
    this.n = ((EditText)findViewById(2131558790));
    this.m.setVisibility(0);
    ImageView localImageView = (ImageView)findViewById(2131558791);
    localImageView.setColorFilter(Color.parseColor("#909090"), PorterDuff.Mode.SRC_IN);
    localImageView.setAlpha(0.0F);
    localImageView.setOnClickListener(new y(this));
    clearSearchBoxFocus();
    this.n.setOnFocusChangeListener(new aj(this));
    int i1 = getResources().getInteger(17694720);
    this.n.addTextChangedListener(new ak(this, localImageView, i1));
    i1 = FilesUtils.getIntFromPreferences("search_bar_width_2", -1);
    if (i1 > 0) {
      this.m.getLayoutParams().width = i1;
    }
    this.t = findViewById(2131558653);
    this.t.setVisibility(0);
    this.t.setOnClickListener(new al(this));
    this.s = findViewById(2131558888);
    this.s.setOnClickListener(new am(this));
  }
  
  private void a(int paramInt)
  {
    f();
    if ((paramInt == 680) || (this.g == null))
    {
      z();
      return;
    }
    this.g.init();
  }
  
  /* Error */
  private void a(Intent paramIntent)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: ldc 95
    //   5: iconst_0
    //   6: invokestatic 232	com/simpler/utils/FilesUtils:getBooleanFromPreferences	(Ljava/lang/String;Z)Z
    //   9: istore_2
    //   10: iload_2
    //   11: ifeq +380 -> 391
    //   14: ldc_w 401
    //   17: iconst_3
    //   18: anewarray 403	java/lang/Object
    //   21: dup
    //   22: iconst_0
    //   23: ldc_w 405
    //   26: aastore
    //   27: dup
    //   28: iconst_1
    //   29: ldc_w 407
    //   32: aastore
    //   33: dup
    //   34: iconst_2
    //   35: iconst_3
    //   36: invokestatic 413	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   39: aastore
    //   40: invokestatic 419	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   43: astore 5
    //   45: aload_0
    //   46: invokevirtual 423	com/simpler/ui/activities/DialerActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   49: getstatic 429	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   52: iconst_1
    //   53: anewarray 415	java/lang/String
    //   56: dup
    //   57: iconst_0
    //   58: ldc_w 431
    //   61: aastore
    //   62: aload 5
    //   64: aconst_null
    //   65: aconst_null
    //   66: invokevirtual 437	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   69: astore 5
    //   71: aload 5
    //   73: astore 4
    //   75: aload 5
    //   77: invokeinterface 443 1 0
    //   82: ifne +304 -> 386
    //   85: aload 5
    //   87: astore 4
    //   89: ldc 95
    //   91: iconst_0
    //   92: invokestatic 101	com/simpler/utils/FilesUtils:saveToPreferences	(Ljava/lang/String;Z)V
    //   95: iconst_0
    //   96: istore_3
    //   97: iload_3
    //   98: istore_2
    //   99: aload 5
    //   101: ifnull +12 -> 113
    //   104: aload 5
    //   106: invokeinterface 446 1 0
    //   111: iload_3
    //   112: istore_2
    //   113: aload_1
    //   114: invokevirtual 450	android/content/Intent:getAction	()Ljava/lang/String;
    //   117: astore 5
    //   119: aload_1
    //   120: invokevirtual 453	android/content/Intent:getType	()Ljava/lang/String;
    //   123: astore 4
    //   125: ldc_w 455
    //   128: aload 5
    //   130: invokevirtual 459	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   133: ifne +13 -> 146
    //   136: ldc 126
    //   138: aload 5
    //   140: invokevirtual 459	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   143: ifeq +209 -> 352
    //   146: aload_0
    //   147: getfield 389	com/simpler/ui/activities/DialerActivity:g	Lcom/simpler/ui/views/DialpadView;
    //   150: ifnonnull +7 -> 157
    //   153: aload_0
    //   154: invokespecial 461	com/simpler/ui/activities/DialerActivity:d	()V
    //   157: aload_1
    //   158: invokevirtual 465	android/content/Intent:getData	()Landroid/net/Uri;
    //   161: astore_1
    //   162: aload_1
    //   163: ifnull +174 -> 337
    //   166: ldc 58
    //   168: aload_1
    //   169: invokevirtual 470	android/net/Uri:getScheme	()Ljava/lang/String;
    //   172: invokevirtual 459	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   175: ifeq +63 -> 238
    //   178: aload_1
    //   179: invokevirtual 473	android/net/Uri:getSchemeSpecificPart	()Ljava/lang/String;
    //   182: astore_1
    //   183: aload_0
    //   184: getfield 389	com/simpler/ui/activities/DialerActivity:g	Lcom/simpler/ui/views/DialpadView;
    //   187: aload_1
    //   188: invokevirtual 476	com/simpler/ui/views/DialpadView:setPhoneNumber	(Ljava/lang/String;)V
    //   191: return
    //   192: astore 6
    //   194: aconst_null
    //   195: astore 5
    //   197: aload 5
    //   199: astore 4
    //   201: aload 6
    //   203: invokevirtual 477	java/lang/Exception:printStackTrace	()V
    //   206: aload 5
    //   208: ifnull +173 -> 381
    //   211: aload 5
    //   213: invokeinterface 446 1 0
    //   218: iconst_1
    //   219: istore_2
    //   220: goto -107 -> 113
    //   223: astore_1
    //   224: aload 4
    //   226: ifnull +10 -> 236
    //   229: aload 4
    //   231: invokeinterface 446 1 0
    //   236: aload_1
    //   237: athrow
    //   238: ldc_w 479
    //   241: aload 4
    //   243: invokevirtual 459	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   246: ifne +14 -> 260
    //   249: ldc_w 481
    //   252: aload 4
    //   254: invokevirtual 459	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   257: ifeq -66 -> 191
    //   260: aload_0
    //   261: invokevirtual 423	com/simpler/ui/activities/DialerActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   264: aload_1
    //   265: iconst_1
    //   266: anewarray 415	java/lang/String
    //   269: dup
    //   270: iconst_0
    //   271: ldc_w 483
    //   274: aastore
    //   275: aconst_null
    //   276: aconst_null
    //   277: aconst_null
    //   278: invokevirtual 437	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   281: astore_1
    //   282: aload_1
    //   283: ifnull -92 -> 191
    //   286: aload_1
    //   287: invokeinterface 486 1 0
    //   292: ifeq +21 -> 313
    //   295: aload_1
    //   296: iconst_0
    //   297: invokeinterface 490 2 0
    //   302: astore 4
    //   304: aload_0
    //   305: getfield 389	com/simpler/ui/activities/DialerActivity:g	Lcom/simpler/ui/views/DialpadView;
    //   308: aload 4
    //   310: invokevirtual 476	com/simpler/ui/views/DialpadView:setPhoneNumber	(Ljava/lang/String;)V
    //   313: aload_1
    //   314: invokeinterface 446 1 0
    //   319: return
    //   320: astore_1
    //   321: aload_1
    //   322: invokevirtual 477	java/lang/Exception:printStackTrace	()V
    //   325: return
    //   326: astore 4
    //   328: aload_1
    //   329: invokeinterface 446 1 0
    //   334: aload 4
    //   336: athrow
    //   337: ldc -124
    //   339: aload 4
    //   341: invokevirtual 459	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   344: ifeq -153 -> 191
    //   347: aload_0
    //   348: invokespecial 492	com/simpler/ui/activities/DialerActivity:B	()V
    //   351: return
    //   352: iload_2
    //   353: ifne +14 -> 367
    //   356: ldc_w 494
    //   359: aload 5
    //   361: invokevirtual 459	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   364: ifeq -173 -> 191
    //   367: aload_0
    //   368: invokespecial 492	com/simpler/ui/activities/DialerActivity:B	()V
    //   371: return
    //   372: astore_1
    //   373: goto -149 -> 224
    //   376: astore 6
    //   378: goto -181 -> 197
    //   381: iconst_1
    //   382: istore_2
    //   383: goto -270 -> 113
    //   386: iload_2
    //   387: istore_3
    //   388: goto -291 -> 97
    //   391: goto -278 -> 113
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	394	0	this	DialerActivity
    //   0	394	1	paramIntent	Intent
    //   9	378	2	bool1	boolean
    //   96	292	3	bool2	boolean
    //   1	308	4	localObject1	Object
    //   326	14	4	localObject2	Object
    //   43	317	5	localObject3	Object
    //   192	10	6	localException1	Exception
    //   376	1	6	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   14	71	192	java/lang/Exception
    //   14	71	223	finally
    //   125	146	320	java/lang/Exception
    //   146	157	320	java/lang/Exception
    //   157	162	320	java/lang/Exception
    //   166	191	320	java/lang/Exception
    //   238	260	320	java/lang/Exception
    //   260	282	320	java/lang/Exception
    //   313	319	320	java/lang/Exception
    //   328	337	320	java/lang/Exception
    //   337	351	320	java/lang/Exception
    //   356	367	320	java/lang/Exception
    //   367	371	320	java/lang/Exception
    //   286	313	326	finally
    //   75	85	372	finally
    //   89	95	372	finally
    //   201	206	372	finally
    //   75	85	376	java/lang/Exception
    //   89	95	376	java/lang/Exception
  }
  
  private void a(Index<AlgoContact> paramIndex, Hit<AlgoContact> paramHit, AlgoContact paramAlgoContact)
  {
    Object localObject2 = StringsUtils.getHighlightedDisplayName(paramIndex, paramHit);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = StringsUtils.getHighlightedPhone(paramIndex, paramHit);
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = StringsUtils.getHighlightedT9(paramIndex, paramHit);
    }
    a(paramAlgoContact, (String)localObject2, StringsUtils.getHighlightedSubtitle(paramIndex, paramHit));
  }
  
  private void a(Index<AlgoContact> paramIndex, SearchResult<AlgoContact> paramSearchResult)
  {
    b(paramSearchResult.nbTotalHits);
    this.j.setVisibility(0);
    this.f.setVisibility(8);
    this.k.clear();
    paramSearchResult = paramSearchResult.hits.iterator();
    while (paramSearchResult.hasNext())
    {
      Hit localHit = (Hit)paramSearchResult.next();
      AlgoContact localAlgoContact = (AlgoContact)localHit.userData;
      a(paramIndex, localHit, localAlgoContact);
      this.k.add(localAlgoContact);
    }
    if (this.l == null)
    {
      this.l = new AlgoListAdapter(this, this.k, AlgoListAdapter.MODE_DIALER);
      paramIndex = new SwipeActionAdapter(this.l);
      paramIndex.setListView(this.j);
      this.j.setAdapter(paramIndex);
      paramIndex.addBackground(-1, 2130903205);
      paramIndex.addBackground(1, 2130903206);
      paramIndex.setNormalSwipeFraction(0.3F);
      paramIndex.setFarSwipeFraction(1.0F);
      paramIndex.setSwipeActionListener(new ae(this, paramIndex));
      j();
      return;
    }
    this.l.notifyDataSetChanged();
  }
  
  private void a(AlgoContact paramAlgoContact)
  {
    Intent localIntent = new Intent(this, ContactDetailsActivity.class);
    paramAlgoContact = Uri.encode(String.valueOf(paramAlgoContact.getId()));
    localIntent.setData(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, paramAlgoContact));
    localIntent.putExtra("launched_from_simpler", true);
    try
    {
      startActivity(localIntent);
      overridePendingTransition(2130968587, 2130968603);
      LogicManager.getInstance().getRateLogic().increaseUserActions();
      this.u = 204;
      return;
    }
    catch (Exception paramAlgoContact)
    {
      for (;;)
      {
        Logger.d("Simpler", paramAlgoContact.getLocalizedMessage());
      }
    }
  }
  
  private void a(AlgoContact paramAlgoContact, String paramString1, String paramString2)
  {
    paramAlgoContact.setHighlightedTitle(paramString1);
    paramAlgoContact.setHighlightedSubtitle(paramString2);
  }
  
  private void a(String paramString)
  {
    if (this.i != null)
    {
      paramString = new SearchQuery(paramString);
      paramString.setMaxHitsToRetrieve(50);
      ArrayList localArrayList = new ArrayList();
      localArrayList.add("search_tag_all_contacts");
      paramString.addORTagsFilter(localArrayList);
      this.i.asyncSearch(paramString);
    }
  }
  
  private void a(boolean paramBoolean)
  {
    int i1 = 0;
    if (this.q) {
      return;
    }
    this.q = true;
    this.g.setVisibility(0);
    Animation localAnimation;
    if (paramBoolean) {
      localAnimation = AnimationUtils.loadAnimation(this, 2130968598);
    }
    for (;;)
    {
      localAnimation.setAnimationListener(new ab(this, i1));
      this.g.startAnimation(localAnimation);
      return;
      localAnimation = AnimationUtils.loadAnimation(this, 2130968597);
      i1 = 8;
    }
  }
  
  private void b()
  {
    this.t.setVisibility(8);
    this.s.setVisibility(0);
    hideDialpad();
    this.h.setVisibility(8);
    this.u = 200;
  }
  
  private void b(int paramInt)
  {
    String str = String.format(getString(2131100021), new Object[] { Integer.valueOf(paramInt) });
    if (paramInt == 1) {
      str = String.format(getString(2131100020), new Object[] { Integer.valueOf(paramInt) });
    }
    c(str);
  }
  
  private void b(AlgoContact paramAlgoContact)
  {
    long l1 = paramAlgoContact.getLocalContactId();
    if (l1 == 0L) {
      return;
    }
    paramAlgoContact = LogicManager.getInstance().getContactsLogic().getDialingPhoneNumber(this, l1);
    if ((paramAlgoContact == null) || (paramAlgoContact.isEmpty())) {
      Toast.makeText(this, getString(2131099954), 0).show();
    }
    for (;;)
    {
      this.u = 205;
      return;
      if (paramAlgoContact.size() == 1)
      {
        b(((ContactPhone)paramAlgoContact.get(0)).getNumber());
      }
      else
      {
        af localAf = new af(this);
        ContactsLogic.getInstance().handleMultiplePhonesClick(this, l1, paramAlgoContact, true, localAf);
      }
    }
  }
  
  private void b(String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      p();
      Intent localIntent = new Intent("android.intent.action.SENDTO");
      localIntent.addFlags(536870912);
      localIntent.setData(Uri.parse("sms:" + paramString));
      if (localIntent.resolveActivity(getPackageManager()) != null)
      {
        startActivity(localIntent);
        LogicManager.getInstance().getRateLogic().increaseUserActions();
      }
    }
  }
  
  private void c()
  {
    if (FilesUtils.getBooleanFromPreferences("is_first_run", true))
    {
      LogicManager.getInstance().getSettingsLogic().setDialerSecondaryT9LanguageOnFirstRun();
      if (this.g != null) {
        this.g.init();
      }
      FilesUtils.saveToPreferences("is_first_run", false);
    }
  }
  
  private void c(String paramString)
  {
    ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null) {
      localActionBar.setSubtitle(paramString);
    }
  }
  
  private void d()
  {
    getSupportFragmentManager().beginTransaction().add(2131558556, new CallLogFragmentOld(), "DIALER_CALLLOG_FRAGMENT_TAG").commit();
    c(getString(2131100000));
    i();
    q();
    h();
    setDialpadVisible(true);
    g();
    this.o = LogicManager.getInstance().getSettingsLogic().getVibrateOnKeypress();
    try
    {
      ViewConfiguration localViewConfiguration = ViewConfiguration.get(this);
      Field localField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
      if (localField != null)
      {
        localField.setAccessible(true);
        localField.setBoolean(localViewConfiguration, false);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void e()
  {
    if (isSearchBoxFocused()) {
      clearSearchBoxFocus();
    }
    if (this.g == null)
    {
      z();
      return;
    }
    this.g.hideDigitsEditTextButtons();
    this.g.clearDigitsEditText();
    c(getString(2131100000));
    CallLogFragmentOld localCallLogFragmentOld = (CallLogFragmentOld)getSupportFragmentManager().findFragmentByTag("DIALER_CALLLOG_FRAGMENT_TAG");
    if (localCallLogFragmentOld != null) {
      localCallLogFragmentOld.scrollToTop();
    }
    this.g.setVisibility(0);
    setDialpadVisible(true);
  }
  
  private void f()
  {
    LogicManager.getInstance().getRateLogic().checkShowRateDialog(this);
  }
  
  private void g()
  {
    this.i = IndexLogic.getInstance().createIndex(this, this);
  }
  
  private void h()
  {
    this.h.getViewTreeObserver().addOnPreDrawListener(new ao(this));
  }
  
  private void i()
  {
    this.f = ((FrameLayout)findViewById(2131558556));
    this.j = ((ListView)findViewById(2131558551));
    this.g = ((DialpadView)findViewById(2131558557));
    this.h = ((FloatingActionButton)findViewById(2131558558));
    this.g.setOnDialpadListener(this);
    this.h.setOnClickListener(this);
    this.j.setOnItemClickListener(this);
    this.j.setBackgroundResource(ThemeUtils.getScreenBackgroundColor());
  }
  
  private void j()
  {
    this.j.setOnScrollListener(new ap(this));
  }
  
  private void k()
  {
    SettingsLogic.getInstance().showColorsDialog(this, new z(this));
  }
  
  private void l()
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
      localIntent.putExtra("finishActivityOnSaveCompleted", true);
      startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      localActivityNotFoundException.printStackTrace();
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setTitle(getString(2131099832));
      localBuilder.setMessage(getString(2131100168));
      localBuilder.setPositiveButton(2131099961, null);
      localBuilder.show();
    }
  }
  
  private void m()
  {
    if (n())
    {
      startActivity(getPackageManager().getLaunchIntentForPackage("com.simpler.contacts"));
      AnalyticsUtils.switchToSimplerContacts();
      return;
    }
    Intent localIntent = new Intent(this, DownloadSimplerContactsActivity.class);
    localIntent.putExtra("app_promote_type", DownloadSimplerContactsActivity.AppDownloadType.CONTACTS);
    startActivity(localIntent);
  }
  
  private boolean n()
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals("com.simpler.contacts")) {
        return true;
      }
    }
    return false;
  }
  
  private void o()
  {
    startActivityForResult(new Intent(this, SettingsActivity.class), 679);
  }
  
  private void p()
  {
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.j.getWindowToken(), 0);
  }
  
  private void q()
  {
    Display localDisplay = getWindowManager().getDefaultDisplay();
    Point localPoint = new Point();
    localDisplay.getSize(localPoint);
    int i1 = (int)(localPoint.y * 0.54F);
    FilesUtils.saveToPreferences("dailpad_height", i1);
    this.g.getLayoutParams().height = i1;
  }
  
  private boolean r()
  {
    boolean bool = false;
    try
    {
      String str = s().getVoiceMailNumber();
      if (str != null) {
        bool = true;
      }
      return bool;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
    }
    return false;
  }
  
  private TelephonyManager s()
  {
    return (TelephonyManager)getSystemService("phone");
  }
  
  private void t()
  {
    if (isDialpadVisible())
    {
      u();
      return;
    }
    showDialpad();
  }
  
  private void u()
  {
    v();
    if (this.g.isDigitsEmpty())
    {
      String str = w();
      if (str != null) {
        this.g.setPhoneNumber(str);
      }
      return;
    }
    dialPhoneNumber(this.g.getPhoneNumber());
    AnalyticsUtils.dial(AnalyticsUtils.AnalyticsDialType.DIALPAD_DIGITS);
  }
  
  private void v()
  {
    if (this.o) {
      ((Vibrator)getSystemService("vibrator")).vibrate(16L);
    }
  }
  
  private String w()
  {
    if (!PermissionUtils.hasPhonePermissions(this)) {
      return null;
    }
    Cursor localCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, new String[] { "number", "type" }, "type = ?", new String[] { String.valueOf(2) }, "date DESC");
    if (localCursor == null) {
      return null;
    }
    int i1 = localCursor.getColumnIndex("number");
    if (localCursor.getCount() > 0)
    {
      localCursor.moveToNext();
      String str = localCursor.getString(i1);
      localCursor.close();
      return str;
    }
    localCursor.close();
    return null;
  }
  
  private void x()
  {
    this.h.setImageResource(2130837715);
  }
  
  private void y()
  {
    if (this.h == null)
    {
      z();
      return;
    }
    this.h.setImageResource(2130837727);
  }
  
  private void z()
  {
    runOnUiThread(new ad(this, this));
  }
  
  public void batchSearchResults(Index<AlgoContact> paramIndex, List<SearchResult<AlgoContact>> paramList, List<SearchQuery> paramList1) {}
  
  public void clearSearchBoxFocus()
  {
    this.n.clearFocus();
  }
  
  public void dialPhoneNumber(String paramString)
  {
    if (!PermissionUtils.hasPhonePermissions(this)) {}
    Intent localIntent;
    do
    {
      do
      {
        return;
      } while (this.r);
      localIntent = new Intent("android.intent.action.CALL");
      localIntent.setData(Uri.parse("tel:" + Uri.encode(paramString)));
    } while (localIntent.resolveActivity(getPackageManager()) == null);
    this.r = true;
    startActivity(localIntent);
    this.g.postDelayed(new ac(this), 2500L);
  }
  
  public void hideDialpad()
  {
    if (!this.q)
    {
      if (isDialpadVisible()) {
        a(false);
      }
      setDialpadVisible(false);
    }
  }
  
  public boolean isDialpadVisible()
  {
    return this.p;
  }
  
  public boolean isSearchBoxFocused()
  {
    return this.n.isFocused();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default: 
    case 679: 
      do
      {
        return;
        a(paramInt2);
      } while (paramInt2 != -1);
      UploadLogic.getInstance().checkAndRunInitialBackup(this);
      LoginLogic.getInstance().checkSendAppDetails(this);
      return;
    case 989: 
      if (paramInt2 == -1)
      {
        TasksLogic.getInstance().handleContactsDataBaseChange();
        return;
      }
      onBackPressed();
      return;
    }
    TasksLogic.getInstance().handleContactsDataBaseChange();
  }
  
  public void onAddPhoneNumberClick(String paramString)
  {
    String str1 = getString(2131099714);
    String str2 = getString(2131099784);
    String str3 = getString(2131099716);
    paramString = new aa(this, paramString);
    paramString = DialogUtils.createTraditionalListDialog(this, str1, new String[] { str2, str3 }, paramString);
    paramString.setCanceledOnTouchOutside(true);
    paramString.show();
  }
  
  public void onBackPressed()
  {
    if (isSearchBoxFocused()) {
      clearSearchBoxFocus();
    }
    do
    {
      return;
      if ((this.j == null) || (this.j.getVisibility() != 0)) {
        break;
      }
      this.g.clearDigitsEditText();
    } while (isDialpadVisible());
    showDialpad();
    return;
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    }
    t();
  }
  
  public void onConfigurationFileSet()
  {
    ConfigurationLogic.getInstance().checkAndShowUpdateDialog(this);
    UploadLogic.getInstance().checkAndRunInitialBackup(this);
    LoginLogic.getInstance().checkSendAppDetails(this);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903072);
    setSupportActionBar((Toolbar)findViewById(2131558541));
    setActivityColors();
    paramBundle = getSupportActionBar();
    if (paramBundle != null)
    {
      paramBundle.setDisplayShowTitleEnabled(false);
      paramBundle.setDisplayShowHomeEnabled(false);
    }
    a();
    d();
    c();
    a(getIntent());
    if (getIntent().getBooleanExtra("HIDE_DIALPAD", false))
    {
      this.g.setVisibility(8);
      setDialpadVisible(false);
      getIntent().putExtra("HIDE_DIALPAD", false);
    }
    paramBundle = ConfigurationLogic.getInstance();
    paramBundle.requestConfigurationFile();
    paramBundle.setListener(this);
    this.k = new ArrayList();
    D();
    if (!PermissionUtils.hasPhonePermissions(this))
    {
      paramBundle = new Intent(this, PermissionsActivity.class);
      paramBundle.putExtra("permission_code_key", 202);
      startActivityForResult(paramBundle, 989);
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131623942, paramMenu);
    ConfigurationLogic localConfigurationLogic = ConfigurationLogic.getInstance();
    if ((!localConfigurationLogic.isUpgradeButtonVisible()) || (localConfigurationLogic.isDiffValueValid()))
    {
      paramMenu.findItem(2131558979).setVisible(false);
      paramMenu.findItem(2131558979).setEnabled(false);
    }
    if (FilesUtils.getIntFromPreferences("search_bar_width_2", -1) < 0) {
      this.m.getViewTreeObserver().addOnGlobalLayoutListener(new aq(this));
    }
    return true;
  }
  
  public void onDialpadQueryTextChange(String paramString)
  {
    a(paramString);
  }
  
  public void onDismissDialerClick()
  {
    hideDialpad();
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramLong = ((AlgoContact)this.l.getItem(paramInt)).getLocalContactId();
    if (paramLong == 0L) {
      return;
    }
    paramAdapterView = LogicManager.getInstance().getContactsLogic().getDialingPhoneNumber(this, paramLong);
    if ((paramAdapterView == null) || (paramAdapterView.isEmpty())) {
      Toast.makeText(this, getString(2131099954), 0).show();
    }
    for (;;)
    {
      this.u = 202;
      return;
      if (paramAdapterView.size() == 1)
      {
        dialPhoneNumber(((ContactPhone)paramAdapterView.get(0)).getNumber());
        AnalyticsUtils.dial(AnalyticsUtils.AnalyticsDialType.T9_RESULTS);
      }
      else
      {
        paramView = new ag(this);
        ContactsLogic.getInstance().handleMultiplePhonesClick(this, paramLong, paramAdapterView, true, paramView);
      }
    }
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    a(paramIntent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    case 2131558978: 
      o();
      return true;
    case 2131558977: 
      m();
      return true;
    case 2131558989: 
      l();
      return true;
    case 2131558990: 
      k();
      return true;
    }
    paramMenuItem = new Intent(this, UpgradeLogic.getInstance().getUpgradeActivityClass());
    paramMenuItem.putExtra("go_pro_action", "Overflow menu click");
    startActivity(paramMenuItem);
    overridePendingTransition(2130968590, 2130968603);
    return true;
  }
  
  protected void onResume()
  {
    super.onResume();
    if (FilesUtils.getBooleanFromPreferences("recreate_option_menu", false))
    {
      invalidateOptionsMenu();
      FilesUtils.saveToPreferences("recreate_option_menu", false);
    }
    if (FilesUtils.getBooleanFromPreferences("reopen_theme_dialog", false))
    {
      k();
      FilesUtils.saveToPreferences("reopen_theme_dialog", false);
    }
  }
  
  public void onSearchBoxLostFocus()
  {
    this.s.setVisibility(8);
    this.t.setVisibility(0);
    this.n.getText().clear();
    p();
    new Handler().postDelayed(new an(this), 200L);
    AnalyticsUtils.searchEvent(this.u);
  }
  
  public void onVoiceMailClick()
  {
    int i1 = 0;
    if (r())
    {
      dialPhoneNumber(s().getVoiceMailNumber());
      return;
    }
    if (Settings.System.getInt(getContentResolver(), "airplane_mode_on", 0) != 0) {
      i1 = 1;
    }
    if (i1 != 0)
    {
      UiUtils.makeToast(getString(2131100302));
      return;
    }
    UiUtils.makeToast(getString(2131100303));
  }
  
  public void publishChangesResult(Index<AlgoContact> paramIndex, String paramString, boolean paramBoolean) {}
  
  public void searchResult(Index<AlgoContact> paramIndex, SearchResult<AlgoContact> paramSearchResult, SearchQuery paramSearchQuery)
  {
    if (!paramSearchQuery.getQueryString().isEmpty())
    {
      a(paramIndex, paramSearchResult);
      return;
    }
    A();
  }
  
  public void setDialpadVisible(boolean paramBoolean)
  {
    this.p = paramBoolean;
    if (this.p)
    {
      x();
      return;
    }
    y();
  }
  
  public void showDialpad()
  {
    if (!this.q)
    {
      a(true);
      setDialpadVisible(true);
    }
  }
}
