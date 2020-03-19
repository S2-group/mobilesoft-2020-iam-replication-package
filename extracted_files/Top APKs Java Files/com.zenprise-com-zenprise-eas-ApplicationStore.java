package com.zenprise.eas;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.citrix.work.ServerType;
import com.citrix.work.activities.BaseActivity;
import com.citrix.work.activities.PageSwitchingActivityCallback;
import com.citrix.work.log.Logger;
import com.sparus.npcommon.Serializer;
import com.sparus.npcommon.ServicesEnumeration;
import com.zenprise.Util;
import com.zenprise.communication.KernelService;
import com.zenprise.communication.Response;
import com.zenprise.communication.SHTP;
import com.zenprise.gui.ContainerList;
import com.zenprise.gui.ListSeparer;
import com.zenprise.gui.MDMUIManager;
import com.zenprise.xml.ParsingXML;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class ApplicationStore
  extends BaseActivity
  implements PageSwitchingActivityCallback
{
  public static final int DESCRIPTION_APP_STORE = 1;
  public static final String PREFS_PARAM = "MyPrefsFile";
  public static boolean landState = false;
  public static Activity me = null;
  private ActionBar actionbar;
  private ListSeparer adapter = null;
  private Map<String, String> appInfo = null;
  Map<String, Bitmap> bitmapsButtonApp = null;
  Map<Integer, Bitmap> bitmapsIconApp = null;
  private String descriptionDialogue = "";
  private Map<String, String> iconInfo = null;
  private ListView list = null;
  private ArrayList<Map> listAppInfo = null;
  private ArrayList<Map> listIconInfo = null;
  Logger logger = Logger.getLogger(getClass());
  private ArrayList<String> name = null;
  private String name_AppDialogue = "";
  private boolean orientation = false;
  private String packageNameDialogue = "";
  private Runnable refreshIconeDisplay = new Runnable()
  {
    public void run()
    {
      ApplicationStore.this.logger.d("refreshIconeDisplay ");
      int i;
      do
      {
        i = 0;
        Iterator localIterator = KernelService.providerImageApps.iterator();
        if (localIterator.hasNext())
        {
          ProviderImageApp localProviderImageApp = (ProviderImageApp)localIterator.next();
          String str = Environment.getDataDirectory().getAbsolutePath() + "/data/" + Util.getPackageName(KernelService.getContext()) + "/icone/" + localProviderImageApp.getId() + ".png";
          if (BitmapFactory.decodeFile(str) != null) {
            ApplicationStore.this.bitmapsIconApp.put(Integer.valueOf(localProviderImageApp.getId()), BitmapFactory.decodeFile(str));
          }
          for (;;)
          {
            ApplicationStore.this.runOnMainScreen(ApplicationStore.this.updateIcone);
            break;
            i = 1;
          }
        }
        SHTP.Sleep(1000L);
      } while (i != 0);
    }
  };
  private int screenWidth = 0;
  private Runnable upDateListIcone = new Runnable()
  {
    public void run()
    {
      ApplicationStore.this.logger.d("upDateListIcone ");
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator1 = KernelService.providerImageApps.iterator();
      Object localObject2;
      for (;;)
      {
        int i = 0;
        if (!localIterator1.hasNext()) {
          break;
        }
        ProviderImageApp localProviderImageApp = (ProviderImageApp)localIterator1.next();
        Iterator localIterator2 = ApplicationStore.this.listAppInfo.iterator();
        while (localIterator2.hasNext())
        {
          Map localMap = (Map)localIterator2.next();
          localObject2 = (String)localMap.get("versionCode");
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = (String)localMap.get("version");
          }
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = "X";
          }
          if ((((String)localMap.get("packageName")).equals(localProviderImageApp.getPackageName())) && (((String)localObject2).equals(localProviderImageApp.getVersion()))) {
            i = 1;
          }
        }
        if (i == 0)
        {
          localArrayList.add(localProviderImageApp);
          new File(Environment.getDataDirectory().getAbsolutePath() + "/data/" + Util.getPackageName(KernelService.getContext()) + "/icone/" + localProviderImageApp.getId() + ".png").delete();
        }
      }
      Object localObject1 = localArrayList.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ProviderImageApp)((Iterator)localObject1).next();
        KernelService.providerImageApps.remove(localObject2);
      }
    }
  };
  private Runnable updateIcone = new Runnable()
  {
    public void run()
    {
      ApplicationStore.access$002(ApplicationStore.this, new ListSeparer(ApplicationStore.me, ApplicationStore.this.vectorContainerList, ApplicationStore.this.bitmapsButtonApp, ApplicationStore.this.bitmapsIconApp));
      ApplicationStore.this.list.setAdapter(ApplicationStore.this.adapter);
    }
  };
  private ArrayList<String> value = null;
  private Vector<ContainerList> vectorContainerList = null;
  private String versionDialogue = "";
  
  public ApplicationStore()
  {
    super(2131493356);
  }
  
  private void buildList()
  {
    this.bitmapsIconApp = new HashMap();
    this.vectorContainerList = new Vector();
    this.appInfo = new HashMap();
    this.listAppInfo = new ArrayList();
    this.iconInfo = new HashMap();
    this.listIconInfo = new ArrayList();
    this.screenWidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
    int k = 0;
    int i = 0;
    int m = 0;
    Object localObject1 = new ContainerList(getString(2131493232), "", 2130903136);
    this.vectorContainerList.add(localObject1);
    localObject1 = this.name.iterator();
    int n;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (String)((Iterator)localObject1).next();
      n = m;
      if (((String)localObject2).equals("store"))
      {
        i = 0;
        if (m != 0) {
          this.listAppInfo.add(this.appInfo);
        }
        n = 1;
        this.appInfo = new HashMap();
        this.appInfo.put(localObject2, this.value.get(k));
      }
      if (((String)localObject2).equals("packageName")) {
        this.appInfo.put(localObject2, this.value.get(k));
      }
      if (((String)localObject2).equals("displayName")) {
        this.appInfo.put(localObject2, this.value.get(k));
      }
      if (((String)localObject2).equals("description")) {
        this.appInfo.put(localObject2, this.value.get(k));
      }
      if (((String)localObject2).equals("version")) {
        this.appInfo.put(localObject2, this.value.get(k));
      }
      if (((String)localObject2).equals("versionCode")) {
        this.appInfo.put(localObject2, this.value.get(k));
      }
      if (((String)localObject2).equals("size")) {
        this.appInfo.put(localObject2, this.value.get(k));
      }
      if (((String)localObject2).equals("modification")) {
        this.appInfo.put(localObject2, this.value.get(k));
      }
      if (((String)localObject2).equals("editor")) {
        this.appInfo.put(localObject2, this.value.get(k));
      }
      if (((String)localObject2).equals("url")) {
        this.appInfo.put(localObject2, this.value.get(k));
      }
      j = i;
      if (((String)localObject2).equals("icon"))
      {
        j = i + 1;
        this.appInfo.put((String)localObject2 + "" + j, this.value.get(k));
      }
      k += 1;
      m = n;
      i = j;
    }
    this.listAppInfo.add(this.appInfo);
    i = 0;
    while (i < this.listAppInfo.size())
    {
      j = 0 + 1;
      while (((Map)this.listAppInfo.get(i)).get("icon" + j) != null)
      {
        this.iconInfo.put("packageName", (String)((Map)this.listAppInfo.get(i)).get("packageName"));
        localObject2 = (String)((Map)this.listAppInfo.get(i)).get("versionCode");
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = "X";
        }
        this.iconInfo.put("version", localObject1);
        localObject1 = (String)((Map)this.listAppInfo.get(i)).get("icon" + j);
        localObject2 = ((String)localObject1).substring(((String)localObject1).indexOf("id!"));
        localObject2 = ((String)localObject2).substring("id!".length(), ((String)localObject2).lastIndexOf("!"));
        this.iconInfo.put("id", localObject2);
        localObject2 = ((String)localObject1).substring(((String)localObject1).indexOf("size!"), ((String)localObject1).indexOf("id!"));
        localObject2 = ((String)localObject2).substring("size!".length(), ((String)localObject2).lastIndexOf("!"));
        this.iconInfo.put("size", localObject2);
        localObject2 = ((String)localObject1).substring(((String)localObject1).indexOf("crc32!"), ((String)localObject1).indexOf("size!"));
        localObject2 = ((String)localObject2).substring("crc32!".length(), ((String)localObject2).lastIndexOf("!"));
        this.iconInfo.put("crc32", localObject2);
        localObject2 = ((String)localObject1).substring(((String)localObject1).indexOf("mime!"), ((String)localObject1).indexOf("crc32!"));
        localObject2 = ((String)localObject2).substring("mime!".length(), ((String)localObject2).lastIndexOf("!"));
        this.iconInfo.put("mime", localObject2);
        localObject1 = ((String)localObject1).substring(((String)localObject1).indexOf("lang!"), ((String)localObject1).indexOf("mime!"));
        localObject1 = ((String)localObject1).substring("lang!".length(), ((String)localObject1).lastIndexOf("!"));
        this.iconInfo.put("lang", localObject1);
        this.listIconInfo.add(this.iconInfo);
        this.iconInfo = new HashMap();
        j += 1;
      }
      i += 1;
    }
    localObject1 = null;
    Object localObject2 = null;
    Object localObject3 = new ArrayList();
    Object localObject9 = new ArrayList();
    Object localObject10 = this.listIconInfo.iterator();
    Object localObject8;
    Object localObject4;
    Object localObject6;
    Object localObject7;
    while (((Iterator)localObject10).hasNext())
    {
      localObject8 = (Map)((Iterator)localObject10).next();
      if (localObject1 == null)
      {
        ((ArrayList)localObject3).add(localObject8);
        localObject1 = (String)((Map)localObject8).get("packageName");
        localObject2 = (String)((Map)localObject8).get("version");
      }
      else if ((((Map)localObject8).get("packageName").equals(localObject1)) && (((Map)localObject8).get("version").equals(localObject2)))
      {
        ((ArrayList)localObject3).add(localObject8);
        localObject1 = (String)((Map)localObject8).get("packageName");
        localObject2 = (String)((Map)localObject8).get("version");
      }
      else
      {
        i = -1;
        localObject1 = null;
        localObject2 = null;
        j = 0;
        localObject3 = ((ArrayList)localObject3).iterator();
        for (;;)
        {
          m = i;
          localObject5 = localObject1;
          localObject4 = localObject2;
          k = j;
          if (((Iterator)localObject3).hasNext())
          {
            localObject6 = (Map)((Iterator)localObject3).next();
            if (((Map)localObject6).get("size").equals("48"))
            {
              m = Integer.parseInt((String)((Map)localObject6).get("id"));
              localObject5 = (String)((Map)localObject6).get("packageName");
              localObject4 = (String)((Map)localObject6).get("version");
              k = Integer.parseInt((String)((Map)localObject6).get("size"));
            }
          }
          else
          {
            localObject6 = new ArrayList();
            j = 0;
            ((ArrayList)localObject6).add(localObject8);
            localObject7 = (String)((Map)localObject8).get("packageName");
            localObject8 = (String)((Map)localObject8).get("version");
            if (!new File(Environment.getDataDirectory().getAbsolutePath() + "/data/" + Util.getPackageName(this) + "/icone/" + m + ".png").exists()) {
              break label1819;
            }
            n = 1;
            i = 0;
            localObject1 = KernelService.providerImageApps.iterator();
            while (((Iterator)localObject1).hasNext()) {
              if (((ProviderImageApp)((Iterator)localObject1).next()).getId() == m) {
                i = 1;
              }
            }
          }
          if (i == -1)
          {
            i = Integer.parseInt((String)((Map)localObject6).get("id"));
            localObject1 = (String)((Map)localObject6).get("packageName");
            localObject2 = (String)((Map)localObject6).get("version");
            j = Integer.parseInt((String)((Map)localObject6).get("size"));
          }
          else if ((i < 48) && (Integer.parseInt((String)((Map)localObject6).get("size")) > 48))
          {
            i = Integer.parseInt((String)((Map)localObject6).get("id"));
            localObject1 = (String)((Map)localObject6).get("packageName");
            localObject2 = (String)((Map)localObject6).get("version");
            j = Integer.parseInt((String)((Map)localObject6).get("size"));
          }
          else if (Math.abs(i - 48) < Math.abs(Integer.parseInt((String)((Map)localObject6).get("size")) - 48))
          {
            i = Integer.parseInt((String)((Map)localObject6).get("id"));
            localObject1 = (String)((Map)localObject6).get("packageName");
            localObject2 = (String)((Map)localObject6).get("version");
            j = Integer.parseInt((String)((Map)localObject6).get("size"));
          }
        }
        j = n;
        if (i == 0)
        {
          KernelService.providerImageApps.add(new ProviderImageApp(m, (String)localObject5, (String)localObject4, k, true));
          j = n;
        }
        label1819:
        localObject3 = localObject6;
        localObject1 = localObject7;
        localObject2 = localObject8;
        if (j == 0)
        {
          KernelService.providerImageApps.add(new ProviderImageApp(m, (String)localObject5, (String)localObject4, k, true));
          ((ArrayList)localObject9).add(Integer.valueOf(m));
          localObject1 = new ByteArrayOutputStream();
          Serializer.writeIntArray((ByteArrayOutputStream)localObject1, new int[] { m });
          new Response(KernelService.shtp, ServicesEnumeration.SIMPLE_REQUEST, 0, 2, ((ByteArrayOutputStream)localObject1).toByteArray(), true);
          localObject3 = localObject6;
          localObject1 = localObject7;
          localObject2 = localObject8;
        }
      }
    }
    i = -1;
    localObject1 = null;
    localObject2 = null;
    int j = 0;
    Object localObject5 = ((ArrayList)localObject3).iterator();
    for (;;)
    {
      m = i;
      localObject4 = localObject1;
      localObject3 = localObject2;
      k = j;
      if (((Iterator)localObject5).hasNext())
      {
        localObject6 = (Map)((Iterator)localObject5).next();
        if (((Map)localObject6).get("size").equals("48"))
        {
          m = Integer.parseInt((String)((Map)localObject6).get("id"));
          localObject4 = (String)((Map)localObject6).get("packageName");
          localObject3 = (String)((Map)localObject6).get("version");
          k = Integer.parseInt((String)((Map)localObject6).get("size"));
        }
      }
      else
      {
        j = 0;
        if (!new File(Environment.getDataDirectory().getAbsolutePath() + "/data/" + Util.getPackageName(this) + "/icone/" + m + ".png").exists()) {
          break label2455;
        }
        n = 1;
        i = 0;
        localObject1 = KernelService.providerImageApps.iterator();
        while (((Iterator)localObject1).hasNext()) {
          if (((ProviderImageApp)((Iterator)localObject1).next()).getId() == m) {
            i = 1;
          }
        }
      }
      if (i == -1)
      {
        i = Integer.parseInt((String)((Map)localObject6).get("id"));
        localObject1 = (String)((Map)localObject6).get("packageName");
        localObject2 = (String)((Map)localObject6).get("version");
        j = Integer.parseInt((String)((Map)localObject6).get("size"));
      }
      else if ((i < 48) && (Integer.parseInt((String)((Map)localObject6).get("size")) > 48))
      {
        i = Integer.parseInt((String)((Map)localObject6).get("id"));
        localObject1 = (String)((Map)localObject6).get("packageName");
        localObject2 = (String)((Map)localObject6).get("version");
        j = Integer.parseInt((String)((Map)localObject6).get("size"));
      }
      else if (Math.abs(i - 48) < Math.abs(Integer.parseInt((String)((Map)localObject6).get("size")) - 48))
      {
        i = Integer.parseInt((String)((Map)localObject6).get("id"));
        localObject1 = (String)((Map)localObject6).get("packageName");
        localObject2 = (String)((Map)localObject6).get("version");
        j = Integer.parseInt((String)((Map)localObject6).get("size"));
      }
    }
    j = n;
    if (i == 0)
    {
      KernelService.providerImageApps.add(new ProviderImageApp(m, (String)localObject4, (String)localObject3, k, true));
      j = n;
    }
    label2455:
    if ((m != -1) && (j == 0))
    {
      ((ArrayList)localObject9).add(Integer.valueOf(m));
      KernelService.providerImageApps.add(new ProviderImageApp(m, (String)localObject4, (String)localObject3, k, true));
      localObject1 = new ByteArrayOutputStream();
      Serializer.writeIntArray((ByteArrayOutputStream)localObject1, new int[] { m });
      new Response(KernelService.shtp, ServicesEnumeration.SIMPLE_REQUEST, 0, 2, ((ByteArrayOutputStream)localObject1).toByteArray(), true);
    }
    runOnMainScreen(this.upDateListIcone);
    localObject1 = ((ArrayList)localObject9).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Integer)((Iterator)localObject1).next();
      this.logger.d("" + localObject2);
    }
    if ((KernelService.connectionState == 0) && (m != -1) && (getIntent().getBooleanExtra("appStore", false)))
    {
      localObject1 = new Intent(this, KernelService.class);
      KernelService.appStoreLuncher = true;
      ((Intent)localObject1).putExtra("up", true);
      startService((Intent)localObject1);
      localObject7 = (ArrayList)getPackageManager().getInstalledPackages(0);
      k = ((ArrayList)localObject7).size();
      i = 0;
      localObject1 = null;
      if (i >= this.listAppInfo.size()) {
        break label3538;
      }
      localObject1 = (String)((Map)this.listAppInfo.get(i)).get("displayName");
      if (((Map)this.listAppInfo.get(i)).get("versionCode") != null) {
        break label3022;
      }
      localObject4 = "X";
      label2753:
      localObject5 = localObject1;
      if (localObject1 == null) {
        localObject5 = (String)((Map)this.listAppInfo.get(i)).get("packageName");
      }
      localObject3 = "Install";
      localObject6 = "";
      j = 0;
      label2796:
      if (j >= k) {
        break label3249;
      }
      localObject9 = (PackageInfo)((ArrayList)localObject7).get(j);
      localObject8 = ((PackageInfo)localObject9).applicationInfo;
      localObject1 = localObject3;
      localObject2 = localObject6;
      if (localObject8 != null)
      {
        localObject1 = localObject3;
        localObject2 = localObject6;
        if (!((ApplicationInfo)localObject8).sourceDir.startsWith("/system"))
        {
          localObject10 = String.valueOf(((PackageInfo)localObject9).versionCode);
          localObject8 = ((ApplicationInfo)localObject8).packageName;
          localObject1 = localObject3;
          localObject2 = localObject6;
          if (localObject10 != null)
          {
            localObject1 = localObject3;
            localObject2 = localObject6;
            if (((String)localObject8).equals((String)((Map)this.listAppInfo.get(i)).get("packageName")))
            {
              if (!((String)localObject10).equals(localObject4)) {
                break label3048;
              }
              localObject1 = "Installed";
              localObject2 = (String)((Map)this.listAppInfo.get(i)).get("version");
            }
          }
        }
      }
    }
    for (;;)
    {
      j += 1;
      localObject3 = localObject1;
      localObject6 = localObject2;
      break label2796;
      if (!getIntent().getBooleanExtra("appStore", false)) {
        break;
      }
      localObject1 = new ByteArrayOutputStream();
      new Response(KernelService.shtp, ServicesEnumeration.SIMPLE_REQUEST, 0, 4, ((ByteArrayOutputStream)localObject1).toByteArray(), true);
      break;
      label3022:
      localObject4 = (String)((Map)this.listAppInfo.get(i)).get("versionCode");
      break label2753;
      label3048:
      if (((String)localObject4).equals("X"))
      {
        localObject1 = "Installed";
        localObject2 = "";
      }
      else
      {
        localObject1 = localObject3;
        if (Integer.decode((String)localObject4).intValue() > Integer.decode((String)localObject10).intValue()) {
          localObject1 = "Updated";
        }
        localObject2 = (String)((Map)this.listAppInfo.get(i)).get("version");
        if (Integer.decode((String)localObject4).intValue() < Integer.decode((String)localObject10).intValue())
        {
          localObject1 = "Installed";
          localObject3 = String.valueOf(((PackageInfo)localObject9).versionName);
          localObject6 = new ArrayList();
          localObject2 = this.listAppInfo.iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject9 = (Map)((Iterator)localObject2).next();
            if (((Map)localObject9).get("packageName").equals(localObject8)) {
              ((ArrayList)localObject6).add(localObject9);
            }
          }
          localObject2 = localObject3;
          if (((ArrayList)localObject6).size() >= 2)
          {
            localObject1 = "Duplicate";
            localObject2 = localObject3;
          }
        }
      }
    }
    label3249:
    localObject1 = KernelService.appButtonPendingInstall.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (ButtonAppStoreState)((Iterator)localObject1).next();
      if ((((ButtonAppStoreState)localObject2).getPackageName().equals((String)((Map)this.listAppInfo.get(i)).get("packageName"))) && (((ButtonAppStoreState)localObject2).getVersion().equals(localObject4))) {
        if (((Map)this.listAppInfo.get(i)).get("store").equals("EXTERNAL")) {
          KernelService.appButtonPendingInstall.remove(localObject2);
        } else {
          localObject3 = "Pending";
        }
      }
    }
    localObject2 = (String)((Map)this.listAppInfo.get(i)).get("store");
    localObject1 = null;
    if (((String)localObject2).equals("EXTERNAL")) {
      if (!((String)((Map)this.listAppInfo.get(i)).get("url")).contains("market")) {
        break label3530;
      }
    }
    label3530:
    for (localObject1 = "google";; localObject1 = "amazon")
    {
      localObject1 = new ContainerList((String)localObject5, (String)localObject4, (String)localObject1, (String)((Map)this.listAppInfo.get(i)).get("packageName"), (String)localObject3, 2130837816, 2130903135, getResources(), this.screenWidth);
      ((ContainerList)localObject1).setVersionDisplay((String)localObject6);
      if (!((String)localObject3).equals("Duplicate")) {
        this.vectorContainerList.add(localObject1);
      }
      i += 1;
      break;
    }
    label3538:
    this.bitmapsButtonApp = new HashMap();
    this.bitmapsButtonApp.put("install", BitmapFactory.decodeResource(((ContainerList)localObject1).getRes(), 2130837656));
    this.bitmapsButtonApp.put("installed", BitmapFactory.decodeResource(((ContainerList)localObject1).getRes(), 2130837657));
    this.bitmapsButtonApp.put("pending", BitmapFactory.decodeResource(((ContainerList)localObject1).getRes(), 2130837658));
    this.adapter = new ListSeparer(this, this.vectorContainerList, this.bitmapsButtonApp, this.bitmapsIconApp);
    this.list.setAdapter(this.adapter);
    this.list.setBackgroundResource(2130837785);
    this.list.setCacheColorHint(0);
    customizeActionBar();
    localObject1 = new Thread(this.refreshIconeDisplay);
    if (!((Thread)localObject1).isAlive()) {
      ((Thread)localObject1).start();
    }
  }
  
  private void customizeActionBar()
  {
    this.actionbar = getSupportActionBar();
    Drawable localDrawable = getResources().getDrawable(2130837718);
    this.actionbar.setBackgroundDrawable(localDrawable);
    setBrandingImageBis();
  }
  
  private void runOnMainScreen(Runnable paramRunnable)
  {
    runOnUiThread(paramRunnable);
  }
  
  private void setBrandingImageBis()
  {
    ImageView localImageView = (ImageView)LayoutInflater.from(this).inflate(2130903079, null);
    ActionBar.LayoutParams localLayoutParams = new ActionBar.LayoutParams(-2, -2, 17);
    this.actionbar.setCustomView(localImageView, localLayoutParams);
    this.actionbar.setDisplayOptions(18);
  }
  
  public void clickButtonHandler(View paramView)
  {
    paramView = (RelativeLayout)paramView.getParent();
    Object localObject2 = (TextView)paramView.getChildAt(4);
    Object localObject1 = (TextView)paramView.getChildAt(5);
    Object localObject3 = (TextView)paramView.getChildAt(6);
    Object localObject4 = (Button)paramView.getChildAt(0);
    String str = (String)((Button)localObject4).getText();
    localObject1 = ((TextView)localObject1).getText().toString();
    localObject2 = ((TextView)localObject2).getText().toString();
    localObject3 = ((TextView)localObject3).getText().toString();
    if (!str.equals("Installed"))
    {
      ((Button)localObject4).setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeResource(getResources(), 2130837658)));
      ((Button)localObject4).setText(2131493234);
      ((Button)localObject4).setClickable(false);
      ((Button)localObject4).setEnabled(false);
      paramView.refreshDrawableState();
      KernelService.appButtonPendingInstall.add(new ButtonAppStoreState((Button)localObject4, (String)localObject1, (String)localObject2, str, (String)localObject3));
      paramView = this.vectorContainerList.iterator();
      while (paramView.hasNext())
      {
        localObject4 = (ContainerList)paramView.next();
        if ((((ContainerList)localObject4).getPackageName() != null) && (((ContainerList)localObject4).getPackageName().equals(localObject2))) {
          ((ContainerList)localObject4).setTitreButton("Pending");
        }
      }
      this.adapter = new ListSeparer(this, this.vectorContainerList, this.bitmapsButtonApp, this.bitmapsIconApp);
      this.list.setAdapter(this.adapter);
    }
    int i;
    try
    {
      if (((String)localObject1).equals("X"))
      {
        i = 0;
        if (i < this.listAppInfo.size())
        {
          if ((!((Map)this.listAppInfo.get(i)).get("store").equals("EXTERNAL")) || (!((Map)this.listAppInfo.get(i)).get("packageName").equals(localObject2))) {
            break label870;
          }
          if (((Map)this.listAppInfo.get(i)).get("versionCode") == null) {
            paramView = "X";
          }
          while (paramView.equals(localObject1))
          {
            paramView = (String)((Map)this.listAppInfo.get(i)).get("url");
            if ((paramView.contains("market")) && (((String)localObject3).equals("google")))
            {
              startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)((Map)this.listAppInfo.get(i)).get("url"))));
              break;
              paramView = (String)((Map)this.listAppInfo.get(i)).get("versionCode");
            }
            else if ((paramView.contains("amazon")) && (((String)localObject3).equals("amazon")))
            {
              startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)((Map)this.listAppInfo.get(i)).get("url"))));
            }
          }
        }
      }
    }
    catch (Exception paramView)
    {
      this.logger.d("error " + paramView);
      Toast.makeText(this, "error conection to the store", 1).show();
    }
    label870:
    label877:
    label882:
    for (;;)
    {
      return;
      i = 0;
      for (;;)
      {
        if (i >= this.listAppInfo.size()) {
          break label882;
        }
        if ((((Map)this.listAppInfo.get(i)).get("store").equals("INTERNAL")) && (((Map)this.listAppInfo.get(i)).get("packageName").equals(localObject2)))
        {
          if (((Map)this.listAppInfo.get(i)).get("versionCode") == null) {
            paramView = "X";
          }
          for (;;)
          {
            if (paramView.equals(localObject1))
            {
              paramView = new ByteArrayOutputStream();
              Serializer.writeString(paramView, (String)localObject2);
              Serializer.writeString(paramView, (String)localObject1);
              if (KernelService.connectionState == 0)
              {
                localObject3 = new Intent(this, KernelService.class);
                ((Intent)localObject3).putExtra("up", true);
                startService((Intent)localObject3);
              }
              new Response(KernelService.shtp, ServicesEnumeration.SIMPLE_REQUEST, 0, 3, paramView.toByteArray(), true);
              paramView = new Thread(new ConnectionForAppStoreInternal(this, (String)localObject2 + "_" + (String)localObject1));
              paramView.start();
              KernelService.listPocessusPendingInstall.put((String)localObject2 + "_" + (String)localObject1, paramView);
              break label877;
              paramView = (String)((Map)this.listAppInfo.get(i)).get("versionCode");
              continue;
              i += 1;
              break;
            }
          }
        }
        i += 1;
      }
    }
  }
  
  public void clickDescriptionHandler(View paramView)
  {
    paramView = (RelativeLayout)paramView.getParent();
    TextView localTextView1 = (TextView)paramView.getChildAt(4);
    TextView localTextView2 = (TextView)paramView.getChildAt(5);
    int i = 0;
    if (i < this.listAppInfo.size())
    {
      if (((Map)this.listAppInfo.get(i)).get("packageName").equals(localTextView1.getText().toString())) {
        if (((Map)this.listAppInfo.get(i)).get("versionCode") != null) {
          break label241;
        }
      }
      label241:
      for (paramView = "X";; paramView = (String)((Map)this.listAppInfo.get(i)).get("versionCode"))
      {
        if (paramView.equals(localTextView2.getText().toString()))
        {
          this.versionDialogue = paramView;
          this.packageNameDialogue = localTextView1.getText().toString();
          this.name_AppDialogue = ((String)((Map)this.listAppInfo.get(i)).get("displayName"));
          if (this.name_AppDialogue == null) {
            this.name_AppDialogue = ((String)((Map)this.listAppInfo.get(i)).get("packageName"));
          }
          this.descriptionDialogue = ((String)((Map)this.listAppInfo.get(i)).get("description"));
          if (this.descriptionDialogue == null) {
            this.descriptionDialogue = "No Description";
          }
          showDialog(1);
        }
        i += 1;
        break;
      }
    }
  }
  
  public int getProfileId()
  {
    return -1;
  }
  
  public ServerType getServerType()
  {
    return ServerType.MDM_TYPE;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2)
    {
      this.orientation = true;
      this.list.setBackgroundResource(2130837785);
    }
    if (paramConfiguration.orientation == 1)
    {
      this.orientation = true;
      this.list.setBackgroundResource(2130837785);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    me = this;
    KernelService.appStoreLuncher = false;
    if (getResources().getConfiguration().orientation == 2) {
      landState = true;
    }
    BufferedReader localBufferedReader;
    for (;;)
    {
      this.name = new ArrayList();
      this.value = new ArrayList();
      setContentView(2130903138);
      this.list = ((ListView)findViewById(2131165418));
      try
      {
        paramBundle = new StringBuffer(1000);
        localBufferedReader = new BufferedReader(new FileReader(Environment.getDataDirectory().getAbsolutePath() + "/data/" + Util.getPackageName(this) + "/apkPullList.xml"));
        char[] arrayOfChar = new char['Ѐ'];
        for (;;)
        {
          int i = localBufferedReader.read(arrayOfChar);
          if (i == -1) {
            break;
          }
          paramBundle.append(arrayOfChar, 0, i);
        }
        landState = false;
      }
      catch (Exception paramBundle)
      {
        this.logger.w("", paramBundle);
        new File(Environment.getDataDirectory().getAbsolutePath() + "/data/" + Util.getPackageName(this) + "/icone").delete();
        this.vectorContainerList = new Vector();
        KernelService.appButtonPendingInstall = new ArrayList();
        KernelService.providerImageApps = new ArrayList();
        paramBundle = new ContainerList(getString(2131493232), "", 2130903136);
        this.vectorContainerList.add(paramBundle);
        this.adapter = new ListSeparer(this, this.vectorContainerList);
        this.list.setAdapter(this.adapter);
        this.list.setBackgroundResource(2130837785);
        this.list.setCacheColorHint(0);
        customizeActionBar();
        return;
      }
    }
    localBufferedReader.close();
    paramBundle = new ParsingXML(paramBundle.toString());
    paramBundle.read(KernelService.getContext());
    this.name = paramBundle.getNom();
    this.value = paramBundle.getValeur();
    if (this.name.size() != 0)
    {
      buildList();
      return;
    }
    new File(Environment.getDataDirectory().getAbsolutePath() + "/data/" + Util.getPackageName(this) + "/icone").delete();
    this.vectorContainerList = new Vector();
    KernelService.appButtonPendingInstall = new ArrayList();
    KernelService.providerImageApps = new ArrayList();
    paramBundle = new ContainerList(getString(2131493232), "", 2130903136);
    this.vectorContainerList.add(paramBundle);
    this.adapter = new ListSeparer(this, this.vectorContainerList);
    this.list.setAdapter(this.adapter);
    this.list.setBackgroundResource(2130837785);
    this.list.setCacheColorHint(0);
    customizeActionBar();
  }
  
  protected Dialog onCreateDialog(int paramInt)
  {
    if (paramInt == 1)
    {
      Object localObject = KernelService.providerImageApps.iterator();
      while (((Iterator)localObject).hasNext())
      {
        ProviderImageApp localProviderImageApp = (ProviderImageApp)((Iterator)localObject).next();
        if ((localProviderImageApp.getPackageName().equals(this.packageNameDialogue)) && (localProviderImageApp.getVersion().equals(this.versionDialogue)))
        {
          localObject = BitmapFactory.decodeFile(Environment.getDataDirectory().getAbsolutePath() + "/data/" + Util.getPackageName(this) + "/icone/" + localProviderImageApp.getId() + ".png");
          if (localObject != null) {
            localObject = new BitmapDrawable((Bitmap)localObject);
          }
          for (localObject = new AlertDialog.Builder(this).setIcon((Drawable)localObject);; localObject = new AlertDialog.Builder(this).setIcon(2130837792))
          {
            ((AlertDialog.Builder)localObject).setTitle(this.name_AppDialogue);
            ((AlertDialog.Builder)localObject).setMessage(this.descriptionDialogue);
            ((AlertDialog.Builder)localObject).setPositiveButton(2131493256, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
              {
                ApplicationStore.this.removeDialog(1);
              }
            });
            ((AlertDialog.Builder)localObject).setCancelable(false);
            return ((AlertDialog.Builder)localObject).create();
          }
        }
      }
    }
    return null;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      paramKeyEvent = new Intent(this, MDMUIManager.class);
      paramKeyEvent.putExtra("homeScreen", true);
      startActivity(paramKeyEvent);
      finish();
      return true;
    }
    return false;
  }
  
  protected void onPause()
  {
    this.orientation = true;
    super.onPause();
  }
  
  protected void onStop()
  {
    super.onStop();
    if (!this.orientation)
    {
      finish();
      this.orientation = false;
    }
  }
  
  public void reloadUIFragment(int paramInt) {}
  
  public void switchPageLeft() {}
  
  public void switchPageRight() {}
}
