package com.android.inputmethod.keyboard;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;
import ce.e;
import cl.c;
import cl.c.b;
import com.MainApp;
import com.android.inputmethod.latin.LatinIME;
import com.android.inputmethod.latin.utils.v;
import com.more.setting.CustomThemeActivity;
import com.more.setting.MainActivity;
import com.more.setting.db.CustomTheme;
import com.more.setting.db.a;
import com.more.setting.fragments.template.g.a;
import com.more.setting.fragments.template.g.b;
import com.more.setting.fragments.template.k;
import com.more.setting.fragments.template.l;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class KbQuickSettingPlusView
  extends h
  implements View.OnClickListener, f.d, g.b
{
  private LatinIME aIX;
  private KeyboardSwitcher aJH;
  private int aJN;
  private g.a aKe;
  private List<k> aKf;
  private f aKg;
  private PackageManReceiver aKh;
  private a aKi;
  private List<k> aKj;
  RecyclerView aao;
  private View mView;
  
  public KbQuickSettingPlusView(LatinIME paramLatinIME, int paramInt, KeyboardSwitcher paramKeyboardSwitcher)
  {
    super(paramLatinIME, paramKeyboardSwitcher);
    this.aIX = paramLatinIME;
    this.aJN = paramInt;
    this.aJH = paramKeyboardSwitcher;
    this.aKh = new PackageManReceiver(this.aIX);
    this.aKi = new a(this.aIX);
    this.aKj = new ArrayList();
    new l(this);
    LayoutInflater.from(this.aIX).inflate(2130903205, this);
    this.mView = this;
    this.mView.setOnClickListener(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView) {}
    });
    this.aao = ((RecyclerView)findViewById(2131755717));
    setContentViewPaddingTop(this.aao);
    this.aao.setLayoutManager(new GridLayoutManager(this.aIX, getResources().getInteger(2131492875)));
    this.aKf = new ArrayList();
    this.aKg = new f(this.aIX, this.aKf, this.aJN);
    this.aao.setAdapter(this.aKg);
    setAdapterForTheme(this.aKg);
    getPlus();
    this.aKg.aJP = this;
    paramKeyboardSwitcher = (FontTextView)findViewById(2131755676);
    paramInt = com.emoji.common.h.d(this.aIX, "KB_FONT_SIZE_SELECT", 2);
    paramKeyboardSwitcher.setTextSize(com.android.inputmethod.latin.settings.KbAdjustActivity.bhw[paramInt]);
    paramKeyboardSwitcher.setOnClickListener(this);
    if (this.aJN == 0) {
      paramKeyboardSwitcher.setVisibility(4);
    }
    for (;;)
    {
      a(paramLatinIME.bdq, true);
      return;
      paramKeyboardSwitcher.setVisibility(0);
      Object localObject = (FrameLayout.LayoutParams)paramKeyboardSwitcher.getLayoutParams();
      ((FrameLayout.LayoutParams)localObject).height = v.U(this.aIX);
      paramKeyboardSwitcher.setLayoutParams((ViewGroup.LayoutParams)localObject);
      localObject = this.aIX.bdq;
      if (localObject != null) {
        paramKeyboardSwitcher.setTextColor(((cl.b)localObject).bW("quick_setting_btn_color"));
      }
    }
  }
  
  private void getPlus()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        Object localObject = KbQuickSettingPlusView.a(KbQuickSettingPlusView.this).getPackageManager().getInstalledPackages(0);
        KbQuickSettingPlusView.b(KbQuickSettingPlusView.this).addAll(KbQuickSettingPlusView.this.a(KbQuickSettingPlusView.a(KbQuickSettingPlusView.this), com.emoji.common.d.u(KbQuickSettingPlusView.a(KbQuickSettingPlusView.this), KbQuickSettingPlusView.a(KbQuickSettingPlusView.this).getPackageName())));
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          String str = ((PackageInfo)((Iterator)localObject).next()).packageName;
          if (KbQuickSettingPlusView.c(KbQuickSettingPlusView.this) == 0)
          {
            if (cd.b.bF(str)) {
              KbQuickSettingPlusView.b(KbQuickSettingPlusView.this).addAll(KbQuickSettingPlusView.this.a(KbQuickSettingPlusView.a(KbQuickSettingPlusView.this), com.emoji.common.d.u(KbQuickSettingPlusView.a(KbQuickSettingPlusView.this), str)));
            }
          }
          else if (cd.b.bG(str)) {
            KbQuickSettingPlusView.b(KbQuickSettingPlusView.this).addAll(KbQuickSettingPlusView.this.a(KbQuickSettingPlusView.a(KbQuickSettingPlusView.this), com.emoji.common.d.u(KbQuickSettingPlusView.a(KbQuickSettingPlusView.this), str)));
          }
        }
        KbQuickSettingPlusView.a(KbQuickSettingPlusView.this, KbQuickSettingPlusView.b(KbQuickSettingPlusView.this));
        if (KbQuickSettingPlusView.c(KbQuickSettingPlusView.this) == 0) {
          KbQuickSettingPlusView.d(KbQuickSettingPlusView.this);
        }
        for (;;)
        {
          KbQuickSettingPlusView.h(KbQuickSettingPlusView.this).post(new Runnable()
          {
            public final void run()
            {
              KbQuickSettingPlusView.f(KbQuickSettingPlusView.this).notifyDataSetChanged();
              if (KbQuickSettingPlusView.c(KbQuickSettingPlusView.this) == 0)
              {
                KbQuickSettingPlusView.g(KbQuickSettingPlusView.this).hS("theme");
                return;
              }
              KbQuickSettingPlusView.g(KbQuickSettingPlusView.this).hS("emojitype");
            }
          });
          return;
          KbQuickSettingPlusView.e(KbQuickSettingPlusView.this);
        }
      }
    }).start();
  }
  
  private void kM()
  {
    Intent localIntent = new Intent("ACTION_QUICK_SETTING_THEME");
    this.aIX.sendBroadcast(localIntent);
  }
  
  public final LinkedList<k> a(Context paramContext1, Context paramContext2)
  {
    LinkedList localLinkedList = new LinkedList();
    for (;;)
    {
      k localK;
      try
      {
        PackageInfo localPackageInfo = paramContext2.getPackageManager().getPackageInfo(paramContext2.getPackageName(), 0);
        if (this.aJN == 0)
        {
          localObject = "buildin_themes";
          int i = paramContext2.getResources().getIdentifier((String)localObject, "array", paramContext2.getPackageName());
          if (i == 0) {
            break label272;
          }
          localObject = paramContext2.getResources().getStringArray(i);
          String str1 = getCurrentSettingName();
          int j = localObject.length;
          i = 0;
          if (i >= j) {
            break;
          }
          String str2 = localObject[i];
          localK = new k();
          localK.init(paramContext2, str2, 4);
          localK.verRes = c.aN(paramContext1).I(paramContext2, localK.packName);
          if (paramContext1 == paramContext2) {
            break label288;
          }
          localK.firstInstallTime = Long.valueOf(localPackageInfo.firstInstallTime);
          localK.isDef = TextUtils.equals(localK.settingName, getDefaultValue());
          if (!TextUtils.equals(str1, localK.settingName)) {
            break label302;
          }
          localK.select = true;
          if ((localK.verRes != null) && ((localK.verRes.bLk == -1) || (localK.verRes.bLk == -3) || (localK.verRes.bLk == -2))) {
            com.emoji.common.h.j(getContext(), "KEY_THEME", getDefaultValue());
          }
          localLinkedList.add(localK);
          i += 1;
          continue;
        }
        localObject = "buildin_emoji_types";
      }
      catch (PackageManager.NameNotFoundException paramContext1)
      {
        paramContext1.printStackTrace();
        return localLinkedList;
      }
      continue;
      label272:
      Object localObject = new String[1];
      localObject[0] = "";
      continue;
      label288:
      localK.firstInstallTime = Long.valueOf(Long.MAX_VALUE);
      continue;
      label302:
      localK.select = false;
    }
    return localLinkedList;
  }
  
  public final void a(k paramK, int paramInt)
  {
    LatinIME localLatinIME = this.aIX;
    if (localLatinIME == null) {}
    do
    {
      do
      {
        return;
      } while (TextUtils.isEmpty(paramK.displayName));
      if (TextUtils.equals(paramK.displayName, this.aIX.getString(2131362133)))
      {
        MobclickAgent.onEvent(localLatinIME, "quick_theme_entrance_item_click", "customize");
        paramK = new Intent(localLatinIME, CustomThemeActivity.class);
        paramK.putExtra("EXTRA_PACKAGE", cl.b.tj());
        paramK.setAction(MainActivity.acW());
        paramK.setFlags(337641472);
        try
        {
          localLatinIME.startActivity(paramK);
          return;
        }
        catch (NullPointerException paramK)
        {
          paramK.printStackTrace();
          return;
        }
      }
      if (TextUtils.equals(paramK.displayName, this.aIX.getString(2131362179)))
      {
        MobclickAgent.onEvent(localLatinIME, "quick_theme_entrance_item_click", "more");
        paramK = new Intent(localLatinIME, MainActivity.class);
        paramK.setFlags(337641472);
        try
        {
          localLatinIME.startActivity(paramK);
          return;
        }
        catch (NullPointerException paramK)
        {
          paramK.printStackTrace();
          return;
        }
      }
      if (!TextUtils.equals(paramK.displayName, this.aIX.getString(2131362181))) {
        break;
      }
      MobclickAgent.onEvent(localLatinIME, "quick_theme_entrance_item_click", "myTheme");
      com.emoji.common.h.b(localLatinIME, "KEY_CUSTOM_THEME_SELECT_ID", paramK.id);
      if (MainApp.axG.c(Long.valueOf(paramK.id)).size() > 0) {
        com.emoji.common.h.j(localLatinIME, "KEY_TYPE_FACE", ((CustomTheme)MainApp.axG.c(Long.valueOf(paramK.id)).get(0)).fuq);
      }
      com.emoji.common.h.j(localLatinIME, "KEY_THEME", paramK.packName + ":customtheme_");
      com.emoji.common.h.j(localLatinIME, "KEY_KB_BG", paramK.settingName);
      ce.d.aC(localLatinIME);
      kM();
    } while (this.aJH == null);
    this.aJH.mn();
    return;
    if (TextUtils.equals(paramK.displayName, this.aIX.getString(2131362178)))
    {
      MobclickAgent.onEvent(localLatinIME, "quick_emoji_item_click", "more");
      paramK = new Intent(localLatinIME, MainActivity.class);
      paramK.putExtra("EXTRA_PACKAGE", e.uj());
      paramK.setAction(MainActivity.acW());
      paramK.setFlags(337641472);
      try
      {
        localLatinIME.startActivity(paramK);
        return;
      }
      catch (NullPointerException paramK)
      {
        paramK.printStackTrace();
        return;
      }
    }
    if (this.aJN == 1) {
      MobclickAgent.onEvent(localLatinIME, "quick_emoji_item_click", paramK.displayName);
    }
    int i;
    Object localObject;
    for (;;)
    {
      if (paramK.installed) {
        if (this.aJN == 0)
        {
          com.emoji.common.h.j(localLatinIME, "KEY_THEME", paramK.settingName);
          com.emoji.common.h.j(localLatinIME, "KEY_KB_BG", "");
          ce.d.aC(localLatinIME);
          kM();
          if (this.aJH != null) {
            this.aJH.mn();
          }
          Toast.makeText(localLatinIME, this.aIX.getString(2131362274, new Object[] { paramK.displayName }), 0).show();
          return;
          MobclickAgent.onEvent(localLatinIME, "quick_theme_entrance_item_click", paramK.displayName);
        }
        else
        {
          i = 0;
          if (i >= this.aKf.size()) {
            break label751;
          }
          localObject = (k)this.aKf.get(i);
          if (((k)localObject).select) {
            ((k)localObject).select = false;
          }
        }
      }
    }
    for (;;)
    {
      this.aKg.notifyItemChanged(i);
      paramK.select = true;
      this.aKg.notifyItemChanged(paramInt);
      com.emoji.common.h.j(localLatinIME, "KEY_EMOJISTYLE", paramK.settingName);
      localLatinIME.sendBroadcast(new Intent("ACTION_QUICK_SETTING_EMOJI"));
      if (this.aJH != null) {
        this.aJH.mn();
      }
      Toast.makeText(localLatinIME, this.aIX.getString(2131362273, new Object[] { paramK.displayName }), 0).show();
      return;
      i += 1;
      break;
      localObject = paramK.isLocked(localLatinIME);
      if (localObject != null)
      {
        localLatinIME.a((Dialog)localObject);
        return;
      }
      paramK = paramK.getInstallIntent(localLatinIME, paramK.packName);
      if (paramK != null) {
        try
        {
          localLatinIME.startActivity(paramK);
          return;
        }
        catch (ActivityNotFoundException paramK)
        {
          paramK.printStackTrace();
          return;
        }
      }
      Toast.makeText(localLatinIME, 2131362182, 0).show();
      return;
      label751:
      i = 0;
    }
  }
  
  protected final String getCurrentSettingName()
  {
    Context localContext = getContext();
    if (localContext == null) {
      return null;
    }
    if (this.aJN == 1) {}
    for (String str = "KEY_EMOJISTYLE";; str = "KEY_THEME") {
      return com.emoji.common.h.k(localContext, str, getDefaultValue());
    }
  }
  
  public final String getDefaultValue()
  {
    if (this.aJN == 0)
    {
      Object localObject = getContext();
      if (localObject == null) {
        localObject = "";
      }
      String str1;
      String str2;
      do
      {
        return localObject;
        str1 = ((Context)localObject).getPackageName();
        cd.b.tm();
        str2 = cd.b.tq();
        localObject = str1;
      } while (TextUtils.isEmpty(str2));
      return str1 + ":" + str2;
    }
    return com.emoji.setting.b.aS(getContext());
  }
  
  public final int getSettingType()
  {
    return this.aJN;
  }
  
  protected final void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (this.aJN == 1) {
      this.aJH.ad(false);
    }
  }
  
  public final void onClick(View paramView)
  {
    if (paramView.getId() == 2131755676) {
      this.aJH.mn();
    }
  }
  
  protected final void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.aKi != null)
    {
      LocalBroadcastManager.getInstance(this.aIX).unregisterReceiver(this.aKi);
      this.aKi = null;
    }
    if (this.aKh != null)
    {
      this.aIX.unregisterReceiver(this.aKh);
      this.aKh = null;
    }
    if (this.aJN == 1) {
      this.aJH.ad(true);
    }
    if (this.aao != null)
    {
      this.aao.setAdapter(null);
      this.aao = null;
    }
  }
  
  public final void p(List<k> paramList)
  {
    Log.i("ddd", "getRecommendListSuccess: ");
    paramList = paramList.iterator();
    k localK;
    while (paramList.hasNext())
    {
      localK = (k)paramList.next();
      if (localK.prompt)
      {
        this.aKj.add(localK);
        Iterator localIterator = this.aKf.iterator();
        do
        {
          if (!localIterator.hasNext()) {
            break;
          }
        } while (!TextUtils.equals(((k)localIterator.next()).packName, localK.packName));
      }
    }
    for (int i = 1;; i = 0)
    {
      int j = i;
      if (localK.isAd())
      {
        j = i;
        if (com.emoji.common.d.t(this.aIX, localK.packName)) {
          j = 1;
        }
      }
      if (j != 0) {
        break;
      }
      localK.installed = false;
      localK.pluginType = 4;
      this.aKf.add(this.aKf.size(), localK);
      this.aKg.notifyItemChanged(this.aKf.size() - 1);
      break;
      return;
    }
  }
  
  public final void setPresenter(g.a paramA)
  {
    this.aKe = paramA;
  }
  
  public class PackageManReceiver
    extends BroadcastReceiver
  {
    public PackageManReceiver(Context paramContext)
    {
      this$1 = new IntentFilter();
      KbQuickSettingPlusView.this.addAction("android.intent.action.PACKAGE_ADDED");
      KbQuickSettingPlusView.this.addAction("android.intent.action.PACKAGE_REMOVED");
      KbQuickSettingPlusView.this.addDataScheme("package");
      paramContext.registerReceiver(this, KbQuickSettingPlusView.this);
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      try
      {
        paramContext = paramIntent.getDataString().substring(8);
        if (TextUtils.isEmpty(paramContext)) {}
        while ((!cd.b.bF(paramContext)) && (!cd.b.bG(paramContext))) {
          return;
        }
        new StringBuilder("items size:").append(KbQuickSettingPlusView.b(KbQuickSettingPlusView.this).size());
        Object localObject;
        if (TextUtils.equals(paramIntent.getAction(), "android.intent.action.PACKAGE_ADDED"))
        {
          paramIntent = KbQuickSettingPlusView.i(KbQuickSettingPlusView.this).iterator();
          while (paramIntent.hasNext()) {
            if (TextUtils.equals(((k)paramIntent.next()).packName, paramContext))
            {
              paramIntent = KbQuickSettingPlusView.b(KbQuickSettingPlusView.this).iterator();
              while (paramIntent.hasNext())
              {
                localObject = (k)paramIntent.next();
                if (TextUtils.equals(((k)localObject).packName, paramContext)) {
                  KbQuickSettingPlusView.b(KbQuickSettingPlusView.this).remove(localObject);
                }
              }
            }
          }
          KbQuickSettingPlusView.b(KbQuickSettingPlusView.this).addAll(KbQuickSettingPlusView.this.a(KbQuickSettingPlusView.a(KbQuickSettingPlusView.this), com.emoji.common.d.u(KbQuickSettingPlusView.a(KbQuickSettingPlusView.this), paramContext)));
          break label325;
        }
        for (;;)
        {
          KbQuickSettingPlusView.a(KbQuickSettingPlusView.this, KbQuickSettingPlusView.b(KbQuickSettingPlusView.this));
          new StringBuilder("items size:").append(KbQuickSettingPlusView.b(KbQuickSettingPlusView.this).size());
          KbQuickSettingPlusView.f(KbQuickSettingPlusView.this).notifyDataSetChanged();
          return;
          localObject = KbQuickSettingPlusView.b(KbQuickSettingPlusView.this).iterator();
          if (((Iterator)localObject).hasNext())
          {
            paramIntent = (k)((Iterator)localObject).next();
            if (!TextUtils.equals(paramIntent.packName, paramContext)) {
              break;
            }
            KbQuickSettingPlusView.b(KbQuickSettingPlusView.this).remove(paramIntent);
            localObject = KbQuickSettingPlusView.i(KbQuickSettingPlusView.this).iterator();
            label325:
            if (((Iterator)localObject).hasNext())
            {
              k localK = (k)((Iterator)localObject).next();
              if (!TextUtils.equals(paramIntent.packName, paramContext)) {
                break;
              }
              localK.installed = false;
              KbQuickSettingPlusView.b(KbQuickSettingPlusView.this).add(localK);
            }
          }
        }
        return;
      }
      catch (Exception paramContext) {}
    }
  }
  
  private final class a
    extends BroadcastReceiver
  {
    public a(Context paramContext)
    {
      this$1 = new IntentFilter();
      KbQuickSettingPlusView.this.addAction("ACTION_REMOVE_ADS");
      LocalBroadcastManager.getInstance(paramContext).registerReceiver(this, KbQuickSettingPlusView.this);
    }
    
    public final void onReceive(Context paramContext, Intent paramIntent)
    {
      KbQuickSettingPlusView.f(KbQuickSettingPlusView.this).notifyDataSetChanged();
    }
  }
}
