package com.example.faxtest;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.amazon.cognito.CognitoSyncClientManager;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.mobileconnectors.cognito.Dataset;
import com.amazonaws.mobileconnectors.cognito.Dataset.SyncCallback;
import com.amazonaws.mobileconnectors.cognito.Record;
import com.amazonaws.mobileconnectors.cognito.SyncConflict;
import com.amazonaws.mobileconnectors.cognito.exceptions.DataStorageException;
import com.appxy.tools.Utils;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnSwipeListener;
import com.example.faxtest.adapter.clientAdapter;
import com.example.faxtest.adapter.faxInfoAdapter;
import com.example.faxtest.db.DBHelper;
import com.example.faxtest.view.SlidingDraw;
import com.example.faxtest.view.SlidingDraw.OnDrawerCloseListener;
import com.example.faxtest.view.SlidingDraw.OnDrawerOpenListener;
import com.example.faxtest.view.SlidingDraw.OnDrawerScrollListener;
import com.phaxio.Fax;
import com.phaxio.exception.PhaxioException;
import com.phaxio.status.FaxStatus;
import de.gaffga.utils.IabHelper;
import de.gaffga.utils.IabHelper.OnConsumeFinishedListener;
import de.gaffga.utils.IabHelper.OnIabSetupFinishedListener;
import de.gaffga.utils.IabHelper.QueryInventoryFinishedListener;
import de.gaffga.utils.IabResult;
import de.gaffga.utils.Inventory;
import de.gaffga.utils.Purchase;
import de.gaffga.utils.SkuDetails;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity
  extends FragmentActivity
  implements Animation.AnimationListener, SlidingDraw.OnDrawerScrollListener, View.OnClickListener
{
  private static final String PUBKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp75p/9VTBPmZbOqQ+u1+zyRHGsoJdXjPBE9x5rkL4xYjNb6rb3kiR8rYbd7eFTLx46hNGRaMn9E+gFJuE0hUpERELUHi9PWurTmjiiELIKWvsBjG6l+CgYS/C0fqHXFOBf56IipP6QJGnjwum/UsBmSKPD+jllwSBSwRjiNSEQFPF5sacZJLPHBMD/HQsMsrkGygWcAvA6k5aEwyGWDSsXvFfIjOD+pqTqUK9MAPwftJZi4koJJbugqR/25/DqCAQT4c6nVIB/zgQ8a4BiPjZ9HfvfmH2m0IOXpGKvWev0Fnrqa6AocnVauaUITf2XWP7a0UnXgXpd4qQ61HiUDVOwIDAQAB";
  protected static final String[] SKU = { "credits.lvl1.basic", "credits.lvl2", "credits.lvl3", "credits.lvl4", "credits.lvl1" };
  ImageView begin;
  IabHelper buyHelper;
  boolean caninit = true;
  Dialog clientdialog;
  CognitoSyncClientManager cognitoSyncClientManager = new CognitoSyncClientManager();
  TextView contactus;
  Context context;
  TextView credit;
  long credits = 0L;
  TextView credittext;
  private Dataset dataset;
  private SQLiteDatabase db;
  SlidingDraw draft;
  faxInfoAdapter draftad;
  LinearLayout draftcontent;
  TextView draftitem;
  Cursor draftlists;
  SwipeMenuListView draftlistview;
  faxInfoAdapter faildad;
  LinearLayout faildcontent;
  TextView failditem;
  Cursor faildlists;
  SwipeMenuListView faildlistview;
  SlidingDraw failed;
  int faileduuid = 0;
  @SuppressLint({"HandlerLeak"})
  Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 2) {
        MainActivity.this.initlist();
      }
      for (;;)
      {
        super.handleMessage(paramAnonymousMessage);
        return;
        if (paramAnonymousMessage.what == 1)
        {
          new Thread(MainActivity.this.run).start();
        }
        else
        {
          if (paramAnonymousMessage.what == 3)
          {
            if (MainActivity.this.sendlists.getCount() > 1)
            {
              MainActivity.this.senditem.setText(MainActivity.this.sendlists.getCount() + " " + MainActivity.this.getString(2131165231));
              label125:
              if (MainActivity.this.faildlists.getCount() <= 1) {
                break label608;
              }
              MainActivity.this.failditem.setText(MainActivity.this.faildlists.getCount() + " " + MainActivity.this.getString(2131165231));
              label193:
              if (MainActivity.this.draftlists.getCount() <= 1) {
                break label663;
              }
              MainActivity.this.draftitem.setText(MainActivity.this.draftlists.getCount() + " " + MainActivity.this.getString(2131165231));
              label261:
              if (MainActivity.this.sentlists.getCount() <= 1) {
                break label718;
              }
              MainActivity.this.sentitem.setText(MainActivity.this.sentlists.getCount() + " " + MainActivity.this.getString(2131165231));
            }
            for (;;)
            {
              MainActivity.this.sendlistview.setAdapter(MainActivity.this.sendad);
              MainActivity.this.faildlistview.setAdapter(MainActivity.this.faildad);
              MainActivity.this.draftlistview.setAdapter(MainActivity.this.draftad);
              MainActivity.this.sentlistview.setAdapter(MainActivity.this.sentad);
              MainActivity.this.setItemDelete(MainActivity.this.faildlistview);
              MainActivity.this.setItemDelete(MainActivity.this.draftlistview);
              MainActivity.this.setItemDelete(MainActivity.this.sentlistview);
              MainActivity.this.nosending.setVisibility(8);
              if (((MainActivity.this.sendlists == null) || (MainActivity.this.sendlists.getCount() == 0)) && (!MainActivity.this.send.isOpened())) {
                MainActivity.this.nosending.setVisibility(0);
              }
              if ((MainActivity.this.sendlists == null) || (MainActivity.this.sendlists.getCount() <= 0)) {
                break;
              }
              MainActivity.this.isClose = false;
              new Thread(MainActivity.this.run).start();
              break;
              MainActivity.this.senditem.setText(MainActivity.this.sendlists.getCount() + " " + MainActivity.this.getString(2131165230));
              break label125;
              label608:
              MainActivity.this.failditem.setText(MainActivity.this.faildlists.getCount() + " " + MainActivity.this.getString(2131165230));
              break label193;
              label663:
              MainActivity.this.draftitem.setText(MainActivity.this.draftlists.getCount() + " " + MainActivity.this.getString(2131165230));
              break label261;
              label718:
              MainActivity.this.sentitem.setText(MainActivity.this.sentlists.getCount() + " " + MainActivity.this.getString(2131165230));
            }
          }
          if (paramAnonymousMessage.what == 4)
          {
            MainActivity.this.credit.setText(MainActivity.this.credits);
            MainActivity.this.credittext.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                paramAnonymous2View = new Intent(MainActivity.this.context, Activity_IapCredits.class);
                MainActivity.this.startActivity(paramAnonymous2View);
              }
            });
            MainActivity.this.credit.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                paramAnonymous2View = new Intent(MainActivity.this.context, Activity_IapCredits.class);
                MainActivity.this.startActivity(paramAnonymous2View);
              }
            });
            if (MainActivity.this.getSharedPreferences("TinyFax", 0).getString("account", "").equals("")) {
              MainActivity.this.selectdialog();
            }
            if (MainActivity.this.caninit)
            {
              Runnable local3 = new Runnable()
              {
                public void run()
                {
                  MainActivity.this.initcredits();
                  Log.e("twice", "twice");
                }
              };
              MainActivity.this.handler.postDelayed(local3, 2000L);
            }
          }
          else if (paramAnonymousMessage.what == 11)
          {
            MainActivity.this.rate.setVisibility(0);
          }
          else if (paramAnonymousMessage.what == 12)
          {
            MainActivity.this.oops.setVisibility(0);
          }
        }
      }
    }
  };
  String hascover = "0";
  private DBHelper help;
  boolean iap_is_ok = false;
  Animation inalphani;
  boolean isClose = false;
  MyApplication m;
  Map<String, String> map = null;
  int model = 0;
  Animation movein;
  Animation moveout;
  LinearLayout nosending;
  RelativeLayout oops;
  ImageView oopsdelete;
  Animation outalphani;
  String path = null;
  ImageView[] point;
  private Purchase purchase;
  RelativeLayout rate;
  ImageView ratedelete;
  TextView rateus;
  Runnable run = new Runnable()
  {
    public void run()
    {
      int i;
      int j;
      if (!MainActivity.this.isClose)
      {
        i = 0;
        j = 0;
      }
      for (int m = 0;; m = k)
      {
        try
        {
          MainActivity.this.sendlists.moveToFirst();
          i = m;
          j = m;
          boolean bool = MainActivity.this.sendlists.isAfterLast();
          if (!bool) {
            break label75;
          }
          i = m;
        }
        catch (PhaxioException localPhaxioException)
        {
          for (;;)
          {
            Long localLong;
            Fax localFax;
            int n;
            int i1;
            localPhaxioException.printStackTrace();
            Log.e("erro", localPhaxioException.toString());
            continue;
            int k = m;
            i = m;
            j = m;
            if (localFax.getStatus().equals(FaxStatus.FAILURE))
            {
              i = m;
              j = m;
              MainActivity.this.faileduuid = MainActivity.this.sendlists.getInt(0);
              i = m;
              j = m;
              MainActivity.this.hascover = MainActivity.this.sendlists.getString(5);
              i = m;
              j = m;
              MainActivity.this.map = new HashMap();
              i = m;
              j = m;
              MainActivity.this.map.put("sendername", MainActivity.this.sendlists.getString(13));
              i = m;
              j = m;
              MainActivity.this.map.put("senderphone", MainActivity.this.sendlists.getString(14));
              i = m;
              j = m;
              MainActivity.this.map.put("senderemail", MainActivity.this.sendlists.getString(15));
              i = m;
              j = m;
              MainActivity.this.map.put("subjectname", MainActivity.this.sendlists.getString(8));
              i = m;
              j = m;
              MainActivity.this.map.put("subjectcommit", MainActivity.this.sendlists.getString(7));
              i = m;
              j = m;
              MainActivity.this.map.put("number", MainActivity.this.sendlists.getString(10));
              i = m;
              j = m;
              MainActivity.this.map.put("numtitle", MainActivity.this.sendlists.getString(11));
              i = m;
              j = m;
              MainActivity.this.map.put("subject", MainActivity.this.sendlists.getString(4));
              i = m;
              j = m;
              MainActivity.this.path = MainActivity.this.sendlists.getString(9);
              i = m;
              j = m;
              if (MainActivity.this.updateDb(localPhaxioException, 2))
              {
                i = m;
                j = m;
                String str = MainActivity.this.getstring();
                i = m;
                j = m;
                Utils.savechage(MainActivity.this.context, str + "Add", MainActivity.this.sendlists.getString(3));
                i = m;
                j = m;
                MainActivity.this.dataset.put(str + "Add", MainActivity.this.sendlists.getString(3));
                i = m;
                j = m;
                MainActivity.this.dataset.synchronize(MainActivity.this.sync);
              }
              m = 1;
              n = 1;
              k = 1;
              i = m;
              j = n;
              MainActivity.this.initcredits();
              i = m;
              j = n;
              MainActivity.this.handler.sendEmptyMessage(12);
            }
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            label75:
            localException.printStackTrace();
            Log.e("erro", localException.toString());
            i = j;
          }
          MainActivity.this.handler.sendEmptyMessageDelayed(1, 30000L);
        }
        if (i == 0) {
          break label913;
        }
        MainActivity.this.handler.sendEmptyMessage(2);
        return;
        i = m;
        j = m;
        localLong = Long.valueOf(MainActivity.this.sendlists.getLong(12));
        i = m;
        j = m;
        localFax = Fax.status(MainActivity.this.sendlists.getString(12));
        i = m;
        j = m;
        if (!localFax.getStatus().equals(FaxStatus.SUCCESS))
        {
          i = m;
          j = m;
          if (!localFax.getStatus().equals(FaxStatus.PARTIAL_SUCCESS)) {
            break;
          }
        }
        n = 1;
        i1 = 1;
        m = 1;
        i = n;
        j = i1;
        MainActivity.this.updateDb(localLong, 4);
        k = m;
        i = n;
        j = i1;
        if (MainActivity.this.getSharedPreferences("TinyFax", 0).getBoolean("canrate", true))
        {
          i = n;
          j = i1;
          MainActivity.this.handler.sendEmptyMessage(11);
          k = m;
        }
        i = k;
        j = k;
        MainActivity.this.sendlists.moveToNext();
      }
      label913:
    }
  };
  int selectnum = -1;
  SlidingDraw send;
  faxInfoAdapter sendad;
  LinearLayout sendcontent;
  TextView senditem;
  Cursor sendlists;
  SwipeMenuListView sendlistview;
  SlidingDraw sent;
  faxInfoAdapter sentad;
  LinearLayout sentcontent;
  TextView sentitem;
  Cursor sentlists;
  SwipeMenuListView sentlistview;
  ImageButton setting;
  Animation startani;
  ImageButton startim;
  Dataset.SyncCallback sync = new Dataset.SyncCallback()
  {
    public boolean onConflict(Dataset paramAnonymousDataset, List<SyncConflict> paramAnonymousList)
    {
      return false;
    }
    
    public boolean onDatasetDeleted(Dataset paramAnonymousDataset, String paramAnonymousString)
    {
      return false;
    }
    
    public boolean onDatasetsMerged(Dataset paramAnonymousDataset, List<String> paramAnonymousList)
    {
      return false;
    }
    
    public void onFailure(DataStorageException paramAnonymousDataStorageException) {}
    
    public void onSuccess(Dataset paramAnonymousDataset, List<Record> paramAnonymousList)
    {
      long l = 0L;
      Log.e("p", 0L);
      paramAnonymousDataset = MainActivity.this.dataset.getAllRecords().iterator();
      for (;;)
      {
        if (!paramAnonymousDataset.hasNext())
        {
          if (l != MainActivity.this.credits) {
            MainActivity.this.sync();
          }
          return;
        }
        paramAnonymousList = (Record)paramAnonymousDataset.next();
        float f = Float.parseFloat(paramAnonymousList.getValue());
        l = ((float)l + f);
        Log.e("value", paramAnonymousList.getKey() + "adsfa" + f);
      }
    }
  };
  int time = 0;
  TextView tryagain;
  int visiti = 0;
  
  public MainActivity() {}
  
  private int dp2px(int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, getResources().getDisplayMetrics());
  }
  
  private void update()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    for (;;)
    {
      if (i >= SKU.length)
      {
        this.buyHelper.queryInventoryAsync(true, localArrayList, new IabHelper.QueryInventoryFinishedListener()
        {
          public void onQueryInventoryFinished(IabResult paramAnonymousIabResult, Inventory paramAnonymousInventory)
          {
            int i;
            if (paramAnonymousIabResult.isSuccess())
            {
              Log.e("fasdfasdfadsfasdfasd", paramAnonymousInventory.getAllSkus().toString());
              paramAnonymousIabResult = paramAnonymousInventory.getSkuDetails("credits.lvl1.basic");
              Log.e("json", paramAnonymousIabResult.getmJson());
              Log.e("info", paramAnonymousIabResult.getPrice() + "contains" + paramAnonymousIabResult.getDescription() + "\n" + paramAnonymousIabResult.getSku() + "\n" + paramAnonymousIabResult.getTitle() + "\n" + paramAnonymousIabResult.getType());
              paramAnonymousIabResult = "" + paramAnonymousIabResult.getPrice();
              SkuDetails localSkuDetails = paramAnonymousInventory.getSkuDetails("credits.lvl2");
              Log.e("info", localSkuDetails.getPrice() + "contains" + localSkuDetails.getDescription() + "\n" + localSkuDetails.getSku() + "\n" + localSkuDetails.getTitle() + "\n" + localSkuDetails.getType());
              paramAnonymousIabResult = paramAnonymousIabResult + localSkuDetails.getPrice();
              localSkuDetails = paramAnonymousInventory.getSkuDetails("credits.lvl3");
              Log.e("info", localSkuDetails.getPrice() + "contains" + localSkuDetails.getDescription() + "\n" + localSkuDetails.getSku() + "\n" + localSkuDetails.getTitle() + "\n" + localSkuDetails.getType());
              paramAnonymousIabResult = paramAnonymousIabResult + localSkuDetails.getPrice();
              localSkuDetails = paramAnonymousInventory.getSkuDetails("credits.lvl4");
              Log.e("info", localSkuDetails.getPrice() + "contains" + localSkuDetails.getDescription() + "\n" + localSkuDetails.getSku() + "\n" + localSkuDetails.getTitle() + "\n" + localSkuDetails.getType());
              new StringBuilder(String.valueOf(paramAnonymousIabResult)).append(localSkuDetails.getPrice()).toString();
              paramAnonymousIabResult = paramAnonymousInventory.getSkuDetails("credits.lvl1");
              Log.e("info", paramAnonymousIabResult.getPrice() + "contains" + paramAnonymousIabResult.getDescription() + "\n" + paramAnonymousIabResult.getSku() + "\n" + paramAnonymousIabResult.getTitle() + "\n" + paramAnonymousIabResult.getType());
              i = 0;
            }
            for (;;)
            {
              if (i >= 4) {
                return;
              }
              MainActivity.this.purchase = paramAnonymousInventory.getPurchase(MainActivity.SKU[i]);
              if (MainActivity.this.purchase != null) {
                MainActivity.this.consume(MainActivity.this.purchase, i, true);
              }
              i += 1;
            }
          }
        });
        return;
      }
      localArrayList.add(SKU[i]);
      i += 1;
    }
  }
  
  public void consume(Purchase paramPurchase, final int paramInt, final boolean paramBoolean)
  {
    this.buyHelper.consumeAsync(paramPurchase, new IabHelper.OnConsumeFinishedListener()
    {
      public void onConsumeFinished(Purchase paramAnonymousPurchase, IabResult paramAnonymousIabResult)
      {
        if (paramAnonymousIabResult.isSuccess()) {}
        try
        {
          if (paramBoolean)
          {
            paramAnonymousPurchase = paramAnonymousPurchase.getOrderId();
            if (paramInt == 0)
            {
              Utils.savechage(MainActivity.this.context, paramAnonymousPurchase + "ADD", "50");
              MainActivity.this.dataset.put(paramAnonymousPurchase + "ADD", "50");
            }
            for (;;)
            {
              MainActivity.this.dataset.synchronize(MainActivity.this.sync);
              return;
              if (paramInt == 1)
              {
                Utils.savechage(MainActivity.this.context, paramAnonymousPurchase + "ADD", "250");
                MainActivity.this.dataset.put(paramAnonymousPurchase + "ADD", "250");
              }
              else if (paramInt == 2)
              {
                Utils.savechage(MainActivity.this.context, paramAnonymousPurchase + "ADD", "600");
                MainActivity.this.dataset.put(paramAnonymousPurchase + "ADD", "600");
              }
              else if (paramInt == 3)
              {
                Utils.savechage(MainActivity.this.context, paramAnonymousPurchase + "ADD", "2000");
                MainActivity.this.dataset.put(paramAnonymousPurchase + "ADD", "2000");
              }
            }
          }
          return;
        }
        catch (Exception paramAnonymousPurchase) {}
      }
    });
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public String getstring()
  {
    Timestamp localTimestamp = new Timestamp(System.currentTimeMillis());
    return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(localTimestamp);
  }
  
  public void init()
  {
    initlayout();
    this.credittext = ((TextView)findViewById(2131361816));
    this.senditem = ((TextView)findViewById(2131361821));
    this.failditem = ((TextView)findViewById(2131361830));
    this.draftitem = ((TextView)findViewById(2131361837));
    this.sentitem = ((TextView)findViewById(2131361844));
    this.sendcontent = ((LinearLayout)findViewById(2131361818));
    this.draftcontent = ((LinearLayout)findViewById(2131361838));
    this.faildcontent = ((LinearLayout)findViewById(2131361831));
    this.sentcontent = ((LinearLayout)findViewById(2131361845));
    this.nosending = ((LinearLayout)findViewById(2131361823));
    this.sendlistview = ((SwipeMenuListView)findViewById(2131361825));
    this.draftlistview = ((SwipeMenuListView)findViewById(2131361839));
    this.faildlistview = ((SwipeMenuListView)findViewById(2131361832));
    this.sentlistview = ((SwipeMenuListView)findViewById(2131361846));
    this.startani = AnimationUtils.loadAnimation(this, 2130968585);
    this.moveout = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 0.5F);
    this.moveout.setDuration(500L);
    this.movein = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.5F, 1, 0.0F);
    this.movein.setDuration(500L);
    this.inalphani = new AlphaAnimation(0.0F, 1.0F);
    this.inalphani.setDuration(500L);
    this.inalphani.setFillAfter(true);
    this.outalphani = new AlphaAnimation(1.0F, 0.0F);
    this.outalphani.setDuration(500L);
    this.outalphani.setFillAfter(true);
    this.movein.setAnimationListener(this);
    this.moveout.setAnimationListener(this);
    this.inalphani.setAnimationListener(this);
    this.outalphani.setAnimationListener(this);
    this.sendcontent.setClickable(true);
    this.credit = ((TextView)findViewById(2131361814));
    this.setting = ((ImageButton)findViewById(2131361815));
    this.startim = ((ImageButton)findViewById(2131361847));
    this.begin = ((ImageView)findViewById(2131361848));
    this.setting.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(MainActivity.this.context, Activity_Setting.class);
        MainActivity.this.startActivity(paramAnonymousView);
      }
    });
    this.startim.setOnClickListener(this);
    this.send = ((SlidingDraw)findViewById(2131361817));
    this.failed = ((SlidingDraw)findViewById(2131361826));
    this.draft = ((SlidingDraw)findViewById(2131361833));
    this.sent = ((SlidingDraw)findViewById(2131361840));
    this.send.setflag(true);
    this.send.open();
    this.send.close();
    this.sendlistview.setEnabled(false);
    this.startani.setAnimationListener(this);
    this.begin.startAnimation(this.startani);
    this.send.setOnDrawerScrollListener(this);
    this.send.setOnDrawerCloseListener(new SlidingDraw.OnDrawerCloseListener()
    {
      public void onDrawerClosed()
      {
        MainActivity.this.sendlistview.setEnabled(false);
        if (MainActivity.this.failed.isOpened())
        {
          MainActivity.this.failed.close();
          if (!MainActivity.this.draft.isOpened()) {
            break label196;
          }
          MainActivity.this.draft.close();
          label57:
          if (!MainActivity.this.sent.isOpened()) {
            break label231;
          }
          MainActivity.this.sent.close();
        }
        for (;;)
        {
          MainActivity.this.setting.setVisibility(0);
          MainActivity.this.credit.setVisibility(0);
          MainActivity.this.credittext.setVisibility(0);
          MainActivity.this.startim.setVisibility(0);
          if ((MainActivity.this.sendlists == null) || (MainActivity.this.sendlists.getCount() == 0)) {
            MainActivity.this.nosending.setVisibility(0);
          }
          return;
          if (MainActivity.this.failed.getVisibility() != 8) {
            break;
          }
          MainActivity.this.failed.startAnimation(MainActivity.this.movein);
          break;
          label196:
          if (MainActivity.this.draft.getVisibility() != 8) {
            break label57;
          }
          MainActivity.this.draft.startAnimation(MainActivity.this.movein);
          break label57;
          label231:
          if (MainActivity.this.sent.getVisibility() == 8) {
            MainActivity.this.sent.startAnimation(MainActivity.this.movein);
          }
        }
      }
    });
    this.send.setOnDrawerOpenListener(new SlidingDraw.OnDrawerOpenListener()
    {
      public void onDrawerOpened()
      {
        MainActivity.this.setting.setVisibility(4);
        MainActivity.this.credit.setVisibility(4);
        MainActivity.this.credittext.setVisibility(4);
        MainActivity.this.startim.setVisibility(4);
        MainActivity.this.sendlistview.setEnabled(true);
        if (MainActivity.this.failed.isOpened())
        {
          MainActivity.this.failed.close();
          if (!MainActivity.this.draft.isOpened()) {
            break label170;
          }
          MainActivity.this.draft.close();
          label101:
          if (!MainActivity.this.sent.isOpened()) {
            break label203;
          }
          MainActivity.this.sent.close();
        }
        for (;;)
        {
          MainActivity.this.nosending.setVisibility(8);
          return;
          if (MainActivity.this.failed.getVisibility() != 0) {
            break;
          }
          MainActivity.this.failed.startAnimation(MainActivity.this.moveout);
          break;
          label170:
          if (MainActivity.this.draft.getVisibility() != 0) {
            break label101;
          }
          MainActivity.this.draft.startAnimation(MainActivity.this.moveout);
          break label101;
          label203:
          if (MainActivity.this.sent.getVisibility() == 0) {
            MainActivity.this.sent.startAnimation(MainActivity.this.moveout);
          }
        }
      }
    });
    this.failed.setOnDrawerCloseListener(new SlidingDraw.OnDrawerCloseListener()
    {
      public void onDrawerClosed()
      {
        MainActivity.this.startim.setVisibility(0);
        MainActivity.this.setting.setVisibility(0);
        MainActivity.this.credit.setVisibility(0);
        MainActivity.this.credittext.setVisibility(0);
        if (MainActivity.this.send.isOpened()) {
          MainActivity.this.send.close();
        }
        if (MainActivity.this.draft.isOpened())
        {
          MainActivity.this.draft.close();
          if (!MainActivity.this.sent.isOpened()) {
            break label173;
          }
          MainActivity.this.sent.close();
        }
        for (;;)
        {
          MainActivity.this.faildlistview.setVisibility(8);
          MainActivity.this.faildcontent.setVisibility(8);
          return;
          if (MainActivity.this.draft.getVisibility() != 8) {
            break;
          }
          MainActivity.this.draft.startAnimation(MainActivity.this.movein);
          break;
          label173:
          if (MainActivity.this.sent.getVisibility() == 8) {
            MainActivity.this.sent.startAnimation(MainActivity.this.movein);
          }
        }
      }
    });
    this.failed.setOnDrawerOpenListener(new SlidingDraw.OnDrawerOpenListener()
    {
      public void onDrawerOpened()
      {
        MainActivity.this.faildcontent.setVisibility(0);
        MainActivity.this.faildlistview.setVisibility(0);
        MainActivity.this.startim.setVisibility(4);
        MainActivity.this.setting.setVisibility(4);
        MainActivity.this.credit.setVisibility(4);
        MainActivity.this.credittext.setVisibility(4);
        if (!MainActivity.this.send.isOpened()) {
          MainActivity.this.send.open();
        }
        if (MainActivity.this.draft.isOpened()) {
          MainActivity.this.draft.close();
        }
        do
        {
          while (MainActivity.this.sent.isOpened())
          {
            MainActivity.this.sent.close();
            return;
            if (MainActivity.this.draft.getVisibility() == 0) {
              MainActivity.this.draft.startAnimation(MainActivity.this.moveout);
            }
          }
        } while (MainActivity.this.sent.getVisibility() != 0);
        MainActivity.this.sent.startAnimation(MainActivity.this.moveout);
      }
    });
    this.failed.setOnDrawerScrollListener(new SlidingDraw.OnDrawerScrollListener()
    {
      public void onScrollEnded() {}
      
      public void onScrollStarted()
      {
        MainActivity.this.faildcontent.setVisibility(0);
        MainActivity.this.faildlistview.setVisibility(0);
      }
    });
    this.draft.setOnDrawerCloseListener(new SlidingDraw.OnDrawerCloseListener()
    {
      public void onDrawerClosed()
      {
        MainActivity.this.startim.setVisibility(0);
        MainActivity.this.setting.setVisibility(0);
        MainActivity.this.credit.setVisibility(0);
        MainActivity.this.credittext.setVisibility(0);
        if (MainActivity.this.send.isOpened()) {
          MainActivity.this.send.close();
        }
        if (MainActivity.this.failed.isOpened()) {
          MainActivity.this.failed.close();
        }
        if (MainActivity.this.sent.isOpened()) {
          MainActivity.this.sent.close();
        }
        for (;;)
        {
          MainActivity.this.draftcontent.setVisibility(8);
          MainActivity.this.draftlistview.setVisibility(8);
          return;
          if (MainActivity.this.sent.getVisibility() == 8) {
            MainActivity.this.sent.startAnimation(MainActivity.this.movein);
          }
        }
      }
    });
    this.draft.setOnDrawerOpenListener(new SlidingDraw.OnDrawerOpenListener()
    {
      public void onDrawerOpened()
      {
        MainActivity.this.draftcontent.setVisibility(0);
        MainActivity.this.draftlistview.setVisibility(0);
        MainActivity.this.startim.setVisibility(4);
        MainActivity.this.setting.setVisibility(4);
        MainActivity.this.credit.setVisibility(4);
        MainActivity.this.credittext.setVisibility(4);
        if (!MainActivity.this.send.isOpened()) {
          MainActivity.this.send.open();
        }
        if (!MainActivity.this.failed.isOpened()) {
          MainActivity.this.failed.open();
        }
        if (MainActivity.this.sent.isOpened()) {
          MainActivity.this.sent.close();
        }
        while (MainActivity.this.sent.getVisibility() != 0) {
          return;
        }
        MainActivity.this.sent.startAnimation(MainActivity.this.moveout);
      }
    });
    this.draft.setOnDrawerScrollListener(new SlidingDraw.OnDrawerScrollListener()
    {
      public void onScrollEnded() {}
      
      public void onScrollStarted()
      {
        MainActivity.this.draftcontent.setVisibility(0);
        MainActivity.this.draftlistview.setVisibility(0);
      }
    });
    this.sent.setOnDrawerCloseListener(new SlidingDraw.OnDrawerCloseListener()
    {
      public void onDrawerClosed()
      {
        if (MainActivity.this.send.isOpened()) {
          MainActivity.this.send.close();
        }
        if (MainActivity.this.failed.isOpened()) {
          MainActivity.this.failed.close();
        }
        if (MainActivity.this.draft.isOpened()) {
          MainActivity.this.draft.close();
        }
        MainActivity.this.startim.setVisibility(0);
        MainActivity.this.setting.setVisibility(0);
        MainActivity.this.credit.setVisibility(0);
        MainActivity.this.credittext.setVisibility(0);
      }
    });
    this.sent.setOnDrawerOpenListener(new SlidingDraw.OnDrawerOpenListener()
    {
      public void onDrawerOpened()
      {
        MainActivity.this.startim.setVisibility(4);
        MainActivity.this.setting.setVisibility(4);
        MainActivity.this.credit.setVisibility(4);
        MainActivity.this.credittext.setVisibility(4);
        if (!MainActivity.this.send.isOpened()) {
          MainActivity.this.send.open();
        }
        if (!MainActivity.this.failed.isOpened()) {
          MainActivity.this.failed.open();
        }
        if (!MainActivity.this.draft.isOpened()) {
          MainActivity.this.draft.open();
        }
        MainActivity.this.sent.setVisibility(0);
        MainActivity.this.sent.setVisibility(0);
      }
    });
    this.sent.setOnDrawerScrollListener(new SlidingDraw.OnDrawerScrollListener()
    {
      public void onScrollEnded() {}
      
      public void onScrollStarted()
      {
        MainActivity.this.sentcontent.setVisibility(0);
        MainActivity.this.sentlistview.setVisibility(0);
      }
    });
  }
  
  public void initcredits()
  {
    for (;;)
    {
      try
      {
        SQLiteDatabase localSQLiteDatabase = new DBHelper(this).getWritableDatabase();
        Cursor localCursor = localSQLiteDatabase.query("Sender", null, null, null, null, null, null);
        long l = 0L;
        if (!localCursor.moveToNext())
        {
          this.credits = l;
          localCursor.close();
          localSQLiteDatabase.close();
          Log.e("credits", this.credits);
          runOnUiThread(new Runnable()
          {
            public void run()
            {
              MainActivity.this.credit.setText(MainActivity.this.credits);
            }
          });
          this.cognitoSyncClientManager.init(this);
          this.dataset = this.cognitoSyncClientManager.getInstance().openOrCreateDataset("DataSet_CreditsHistory");
          this.dataset.synchronize(this.sync);
          l = this.credits;
          if ((l == 0L) && (!getSharedPreferences("TinyFax", 0).getString("account", "").equals("")))
          {
            this.caninit = true;
            if (this.caninit)
            {
              this.time += 1;
              if ((l != 0L) || (this.time >= 3)) {
                break label443;
              }
              this.caninit = true;
            }
            Log.e("", this.caninit + "asdf" + this.time);
            if ((this.credits == 0L) || (!getSharedPreferences("TinyFax", 0).getString("account", "").equals(""))) {
              break;
            }
            this.handler.sendEmptyMessageDelayed(4, 2000L);
          }
        }
        else
        {
          if ((!getSharedPreferences("TinyFax", 0).getString("account", "").equals(localCursor.getString(3))) && (!localCursor.getString(3).equals(""))) {
            continue;
          }
          Log.e("syncinfo", localCursor.getString(1) + "adsfa" + localCursor.getString(2));
          l += Long.parseLong(localCursor.getString(2));
          Log.e("creditsp", l);
          continue;
        }
        this.caninit = false;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      continue;
      label443:
      this.caninit = false;
    }
    this.handler.sendEmptyMessage(4);
  }
  
  public void initlayout()
  {
    this.rate = ((RelativeLayout)findViewById(2131361851));
    this.oops = ((RelativeLayout)findViewById(2131361856));
    this.rate.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView) {}
    });
    this.oops.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView) {}
    });
    this.ratedelete = ((ImageView)findViewById(2131361852));
    this.oopsdelete = ((ImageView)findViewById(2131361857));
    this.rateus = ((TextView)findViewById(2131361855));
    this.contactus = ((TextView)findViewById(2131361861));
    this.tryagain = ((TextView)findViewById(2131361860));
    this.ratedelete.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.this.rate.setVisibility(8);
      }
    });
    this.oopsdelete.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.this.oops.setVisibility(8);
      }
    });
    this.contactus.setOnClickListener(new View.OnClickListener()
    {
      @SuppressLint({"DefaultLocale"})
      public void onClick(View paramAnonymousView)
      {
        Object localObject = new Intent("android.intent.action.SEND");
        MainActivity.this.oops.setVisibility(8);
        ((Intent)localObject).setType("plain/text");
        paramAnonymousView = new ArrayList();
        localObject = MainActivity.this.getPackageManager().queryIntentActivities((Intent)localObject, 0);
        if (!((List)localObject).isEmpty()) {
          localObject = ((List)localObject).iterator();
        }
        for (;;)
        {
          if (!((Iterator)localObject).hasNext())
          {
            if (paramAnonymousView.size() <= 0) {
              break;
            }
            localObject = Intent.createChooser((Intent)paramAnonymousView.remove(0), MainActivity.this.getString(2131165259));
            ((Intent)localObject).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymousView.toArray(new Parcelable[0]));
            MainActivity.this.startActivityForResult((Intent)localObject, 3);
            return;
          }
          ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
          Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
          localIntent.setType("plain/text");
          localIntent.putExtra("android.intent.extra.SUBJECT", "TinyFax");
          if ((localResolveInfo.activityInfo.packageName.toLowerCase().contains("mail")) || (localResolveInfo.activityInfo.name.toLowerCase().contains("mail")) || (localResolveInfo.activityInfo.packageName.toLowerCase().contains("inbox")) || (localResolveInfo.activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (localResolveInfo.activityInfo.name.toLowerCase().contains("blue")))
          {
            localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "tinyfax.a@appxy.com" });
            localIntent.putExtra("android.intent.extra.SUBJECT", "TinyFax Feedback");
            localIntent.putExtra("android.intent.extra.TEXT", "IdentityId :" + MainActivity.this.context.getSharedPreferences("TinyFax", 0).getString("IdentityId", "") + "\n");
            localIntent.setPackage(localResolveInfo.activityInfo.packageName);
            paramAnonymousView.add(localIntent);
          }
        }
        Toast.makeText(MainActivity.this.context, "Can't find mail application", 0).show();
      }
    });
    this.rateus.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.this.rate.setVisibility(8);
        MainActivity.this.getSharedPreferences("TinyFax", 0).edit().putBoolean("canrate", false).commit();
        Object localObject = MainActivity.this.getPackageManager().getInstalledApplications(0);
        int j = ((List)localObject).size();
        paramAnonymousView = null;
        int i = 0;
        for (;;)
        {
          if (i >= j)
          {
            if (paramAnonymousView == null) {
              break;
            }
            localObject = new Intent("android.intent.action.VIEW");
            ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.tinyfax&hl=en"));
            ((Intent)localObject).setPackage(paramAnonymousView.packageName);
            MainActivity.this.startActivity((Intent)localObject);
            return;
          }
          if (((ApplicationInfo)((List)localObject).get(i)).packageName.equals("com.android.vending")) {
            paramAnonymousView = (ApplicationInfo)((List)localObject).get(i);
          }
          i += 1;
        }
        MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyfax&hl=en")));
      }
    });
    this.tryagain.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.this.oops.setVisibility(8);
        MainActivity.this.m.setPhotopath(MainActivity.this.path);
        MainActivity.this.m.setMap(MainActivity.this.map);
        MainActivity.this.m.setState(2);
        MainActivity.this.m.setIsfromlist(true);
        paramAnonymousView = new Intent();
        paramAnonymousView.setClass(MainActivity.this.context, Activity_SendlookPdf.class);
        if (MainActivity.this.hascover.endsWith("0")) {
          paramAnonymousView.putExtra("iscoveropen", false);
        }
        for (;;)
        {
          MainActivity.this.startActivity(paramAnonymousView);
          return;
          paramAnonymousView.putExtra("iscoveropen", true);
        }
      }
    });
  }
  
  public void initlist()
  {
    this.help = new DBHelper(this.context);
    this.db = this.help.getWritableDatabase();
    this.sendlists = this.db.query("Faxinfo", null, "status=?", new String[] { "1" }, null, null, null);
    this.faildlists = this.db.query("Faxinfo", null, "status=?", new String[] { "2" }, null, null, null);
    this.draftlists = this.db.query("Faxinfo", null, "status=?", new String[] { "3" }, null, null, null);
    this.sentlists = this.db.query("Faxinfo", null, "status=?", new String[] { "4" }, null, null, null);
    this.sendad = new faxInfoAdapter(this, this.sendlists, true);
    this.faildad = new faxInfoAdapter(this, this.faildlists, false);
    this.draftad = new faxInfoAdapter(this, this.draftlists, false);
    this.sentad = new faxInfoAdapter(this, this.sentlists, false);
    this.handler.sendEmptyMessage(3);
  }
  
  public void onAnimationEnd(Animation paramAnimation)
  {
    if (paramAnimation.equals(this.moveout))
    {
      if (!this.failed.isOpened()) {
        this.failed.setVisibility(8);
      }
      if (!this.draft.isOpened()) {
        this.draft.setVisibility(8);
      }
      if (!this.sent.isOpened()) {
        this.sent.setVisibility(8);
      }
    }
    if (paramAnimation.equals(this.startani))
    {
      this.send.setVisibility(0);
      this.failed.setVisibility(0);
      this.draft.setVisibility(0);
      this.sent.setVisibility(0);
      this.startim.setVisibility(0);
      this.credit.setVisibility(0);
      this.credittext.setVisibility(0);
      this.setting.setVisibility(0);
      this.begin.setVisibility(8);
    }
  }
  
  public void onAnimationRepeat(Animation paramAnimation) {}
  
  public void onAnimationStart(Animation paramAnimation)
  {
    if (paramAnimation.equals(this.movein))
    {
      this.failed.setVisibility(0);
      this.draft.setVisibility(0);
      this.sent.setVisibility(0);
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    }
    paramView = new Intent();
    paramView.setClass(this, GetPicActivity.class);
    startActivity(paramView);
  }
  
  @SuppressLint({"InlinedApi"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903042);
    if (Build.VERSION.SDK_INT >= 19)
    {
      getWindow().addFlags(67108864);
      getWindow().addFlags(134217728);
    }
    this.context = this;
    this.m = ((MyApplication)getApplication());
    Utils.changeFont(this.context, (ViewGroup)getWindow().getDecorView());
    init();
    this.point = new ImageView[3];
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.cognitoSyncClientManager.clear();
    this.m.clearMemCache();
    this.sendlists.close();
    this.faildlists.close();
    this.draftlists.close();
    this.sentlists.close();
    this.db.close();
    this.sendlists = null;
    this.faildlists = null;
    this.draftlists = null;
    this.sentlists = null;
    this.db = null;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      finish();
      System.exit(0);
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    super.onPause();
    this.handler.removeMessages(1);
    this.isClose = true;
  }
  
  protected void onResume()
  {
    super.onResume();
    this.m.setIsfromlist(false);
    new Thread(new Runnable()
    {
      public void run()
      {
        MainActivity.this.initlist();
        MainActivity.this.initcredits();
      }
    }).start();
    Log.e("onResume", "onResume");
    try
    {
      this.buyHelper = new IabHelper(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp75p/9VTBPmZbOqQ+u1+zyRHGsoJdXjPBE9x5rkL4xYjNb6rb3kiR8rYbd7eFTLx46hNGRaMn9E+gFJuE0hUpERELUHi9PWurTmjiiELIKWvsBjG6l+CgYS/C0fqHXFOBf56IipP6QJGnjwum/UsBmSKPD+jllwSBSwRjiNSEQFPF5sacZJLPHBMD/HQsMsrkGygWcAvA6k5aEwyGWDSsXvFfIjOD+pqTqUK9MAPwftJZi4koJJbugqR/25/DqCAQT4c6nVIB/zgQ8a4BiPjZ9HfvfmH2m0IOXpGKvWev0Fnrqa6AocnVauaUITf2XWP7a0UnXgXpd4qQ61HiUDVOwIDAQAB");
      this.buyHelper.startSetup(new IabHelper.OnIabSetupFinishedListener()
      {
        public void onIabSetupFinished(IabResult paramAnonymousIabResult)
        {
          if (!paramAnonymousIabResult.isSuccess()) {
            Log.e("ERRO1", "!result.isSuccess()");
          }
          while (MainActivity.this.buyHelper == null) {
            return;
          }
          MainActivity.this.iap_is_ok = true;
          MainActivity.this.update();
        }
      });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void onScrollEnded() {}
  
  public void onScrollStarted() {}
  
  public void selectdialog()
  {
    final Account[] arrayOfAccount = AccountManager.get(this).getAccountsByType("com.google");
    if (arrayOfAccount.length == 0) {}
    do
    {
      do
      {
        return;
        if (arrayOfAccount.length == 1)
        {
          getSharedPreferences("TinyFax", 0).edit().putString("account", arrayOfAccount[0].name).commit();
          this.cognitoSyncClientManager.ini();
          new Thread(new Runnable()
          {
            public void run()
            {
              MainActivity.this.initcredits();
            }
          }).start();
          return;
        }
      } while (this.credits == 0L);
      View localView = View.inflate(this, 2130903057, null);
      ListView localListView = (ListView)localView.findViewById(2131361923);
      final clientAdapter localClientAdapter = new clientAdapter(this, arrayOfAccount);
      localListView.setAdapter(localClientAdapter);
      localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          MainActivity.this.selectnum = paramAnonymousInt;
          Log.e("account", arrayOfAccount[paramAnonymousInt].name);
          localClientAdapter.set(paramAnonymousInt);
          localClientAdapter.notifyDataSetChanged();
        }
      });
      if (this.clientdialog == null) {
        this.clientdialog = new AlertDialog.Builder(this).setView(localView).setCancelable(true).setPositiveButton(2131165262, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            if (MainActivity.this.selectnum != -1) {
              MainActivity.this.getSharedPreferences("TinyFax", 0).edit().putString("account", arrayOfAccount[MainActivity.this.selectnum].name).commit();
            }
            if (!MainActivity.this.getSharedPreferences("TinyFax", 0).getString("account", "").equals(""))
            {
              MainActivity.this.getSharedPreferences("TinyFax", 0).edit().putLong("credits", 0L).commit();
              MainActivity.this.cognitoSyncClientManager.ini();
              new Thread(new Runnable()
              {
                public void run()
                {
                  MainActivity.this.initcredits();
                }
              }).start();
            }
            MainActivity.this.clientdialog.dismiss();
          }
        }).create();
      }
      this.clientdialog.setCancelable(false);
    } while ((this.clientdialog == null) || (this.clientdialog.isShowing()));
    this.clientdialog.show();
  }
  
  public void setItemDelete(SwipeMenuListView paramSwipeMenuListView)
  {
    paramSwipeMenuListView.setMenuCreator(new SwipeMenuCreator()
    {
      public void create(SwipeMenu paramAnonymousSwipeMenu)
      {
        SwipeMenuItem localSwipeMenuItem = new SwipeMenuItem(MainActivity.this.getApplicationContext());
        localSwipeMenuItem.setBackground(new ColorDrawable(Color.rgb(249, 63, 37)));
        localSwipeMenuItem.setWidth(MainActivity.this.dp2px(60));
        localSwipeMenuItem.setIcon(2130837684);
        paramAnonymousSwipeMenu.addMenuItem(localSwipeMenuItem);
      }
    });
    paramSwipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
    {
      public void onMenuItemClick(int paramAnonymousInt1, SwipeMenu paramAnonymousSwipeMenu, int paramAnonymousInt2)
      {
        switch (paramAnonymousInt2)
        {
        }
        do
        {
          return;
          if (MainActivity.this.sent.isOpened())
          {
            MainActivity.this.sentlists.moveToPosition(paramAnonymousInt1);
            paramAnonymousSwipeMenu = new File(MainActivity.this.sentlists.getString(MainActivity.this.sentlists.getColumnIndex("path")));
            if (paramAnonymousSwipeMenu.exists()) {
              paramAnonymousSwipeMenu.delete();
            }
            MainActivity.this.db.delete("Faxinfo", "uuid=?", new String[] { MainActivity.this.sentlists.getString(MainActivity.this.sentlists.getColumnIndex("uuid")) });
            MainActivity.this.sentlists = MainActivity.this.db.query("Faxinfo", null, "status=?", new String[] { "4" }, null, null, null);
            MainActivity.this.sentad = new faxInfoAdapter(MainActivity.this, MainActivity.this.sentlists, false);
            MainActivity.this.sentlistview.setAdapter(MainActivity.this.sentad);
            if (MainActivity.this.sentlists.getCount() > 1)
            {
              MainActivity.this.sentitem.setText(MainActivity.this.sentlists.getCount() + " " + MainActivity.this.getString(2131165231));
              return;
            }
            MainActivity.this.sentitem.setText(MainActivity.this.sentlists.getCount() + " " + MainActivity.this.getString(2131165230));
            return;
          }
          if (MainActivity.this.draft.isOpened())
          {
            MainActivity.this.draftlists.moveToPosition(paramAnonymousInt1);
            paramAnonymousSwipeMenu = new File(MainActivity.this.draftlists.getString(MainActivity.this.draftlists.getColumnIndex("path")));
            if (paramAnonymousSwipeMenu.exists()) {
              paramAnonymousSwipeMenu.delete();
            }
            MainActivity.this.db.delete("Faxinfo", "uuid=?", new String[] { MainActivity.this.draftlists.getString(MainActivity.this.draftlists.getColumnIndex("uuid")) });
            MainActivity.this.draftlists = MainActivity.this.db.query("Faxinfo", null, "status=?", new String[] { "3" }, null, null, null);
            MainActivity.this.draftad = new faxInfoAdapter(MainActivity.this, MainActivity.this.draftlists, false);
            MainActivity.this.draftlistview.setAdapter(MainActivity.this.draftad);
            if (MainActivity.this.draftlists.getCount() > 1)
            {
              MainActivity.this.draftitem.setText(MainActivity.this.draftlists.getCount() + " " + MainActivity.this.getString(2131165231));
              return;
            }
            MainActivity.this.draftitem.setText(MainActivity.this.draftlists.getCount() + " " + MainActivity.this.getString(2131165230));
            return;
          }
          if (MainActivity.this.failed.isOpened())
          {
            MainActivity.this.faildlists.moveToPosition(paramAnonymousInt1);
            paramAnonymousSwipeMenu = new File(MainActivity.this.faildlists.getString(MainActivity.this.faildlists.getColumnIndex("path")));
            if (paramAnonymousSwipeMenu.exists()) {
              paramAnonymousSwipeMenu.delete();
            }
            MainActivity.this.db.delete("Faxinfo", "uuid=?", new String[] { MainActivity.this.faildlists.getString(MainActivity.this.faildlists.getColumnIndex("uuid")) });
            MainActivity.this.faildlists = MainActivity.this.db.query("Faxinfo", null, "status=?", new String[] { "2" }, null, null, null);
            MainActivity.this.faildad = new faxInfoAdapter(MainActivity.this, MainActivity.this.faildlists, false);
            MainActivity.this.faildlistview.setAdapter(MainActivity.this.faildad);
            if (MainActivity.this.faildlists.getCount() > 1)
            {
              MainActivity.this.failditem.setText(MainActivity.this.faildlists.getCount() + " " + MainActivity.this.getString(2131165231));
              return;
            }
            MainActivity.this.failditem.setText(MainActivity.this.faildlists.getCount() + " " + MainActivity.this.getString(2131165230));
            return;
          }
        } while (!MainActivity.this.send.isOpened());
        MainActivity.this.sendlists.moveToPosition(paramAnonymousInt1);
        MainActivity.this.db.delete("Faxinfo", "uuid=?", new String[] { MainActivity.this.sendlists.getString(MainActivity.this.sendlists.getColumnIndex("uuid")) });
        MainActivity.this.sendlists = MainActivity.this.db.query("Faxinfo", null, "status=?", new String[] { "1" }, null, null, null);
        MainActivity.this.sendad = new faxInfoAdapter(MainActivity.this, MainActivity.this.sendlists, true);
        MainActivity.this.sendlistview.setAdapter(MainActivity.this.sendad);
        if (MainActivity.this.sendlists.getCount() > 1)
        {
          MainActivity.this.senditem.setText(MainActivity.this.sendlists.getCount() + " " + MainActivity.this.getString(2131165231));
          return;
        }
        MainActivity.this.senditem.setText(MainActivity.this.sendlists.getCount() + " " + MainActivity.this.getString(2131165230));
      }
    });
    paramSwipeMenuListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener()
    {
      public void onSwipeEnd(int paramAnonymousInt) {}
      
      public void onSwipeStart(int paramAnonymousInt) {}
    });
    paramSwipeMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        MainActivity.this.m.setSent(false);
        if (MainActivity.this.sent.isOpened())
        {
          MainActivity.this.sentlists.moveToPosition(paramAnonymousInt);
          MainActivity.this.m.setState(4);
          MainActivity.this.m.setIsfromlist(true);
          MainActivity.this.m.setSent(true);
          MainActivity.this.m.setPdf(true);
          MainActivity.this.m.setFaxuuid(MainActivity.this.sentlists.getInt(0));
          MainActivity.this.m.setPhotopath(MainActivity.this.sentlists.getString(9));
          paramAnonymousAdapterView = new Intent();
          paramAnonymousAdapterView.setClass(MainActivity.this.context, Activity_Detail.class);
          MainActivity.this.startActivity(paramAnonymousAdapterView);
        }
        do
        {
          return;
          if (MainActivity.this.draft.isOpened())
          {
            MainActivity.this.draftlists.moveToPosition(paramAnonymousInt);
            MainActivity.this.m.setState(3);
            MainActivity.this.m.setIsfromlist(true);
            MainActivity.this.m.setPdf(true);
            MainActivity.this.m.setSent(false);
            MainActivity.this.m.setFaxuuid(MainActivity.this.draftlists.getInt(0));
            MainActivity.this.m.setPhotopath(MainActivity.this.draftlists.getString(9));
            paramAnonymousAdapterView = new Intent();
            paramAnonymousAdapterView.setClass(MainActivity.this.context, Activity_Detail.class);
            MainActivity.this.startActivity(paramAnonymousAdapterView);
            return;
          }
        } while (!MainActivity.this.failed.isOpened());
        MainActivity.this.faildlists.moveToPosition(paramAnonymousInt);
        MainActivity.this.m.setState(2);
        MainActivity.this.m.setIsfromlist(true);
        MainActivity.this.m.setPdf(true);
        MainActivity.this.m.setSent(false);
        MainActivity.this.m.setFaxuuid(MainActivity.this.faildlists.getInt(0));
        MainActivity.this.m.setPhotopath(MainActivity.this.faildlists.getString(9));
        paramAnonymousAdapterView = new Intent();
        paramAnonymousAdapterView.setClass(MainActivity.this.context, Activity_Detail.class);
        MainActivity.this.startActivity(paramAnonymousAdapterView);
      }
    });
  }
  
  public void sync()
  {
    SQLiteDatabase localSQLiteDatabase = new DBHelper(this.context).getWritableDatabase();
    Object localObject1 = this.dataset.getAllRecords().iterator();
    Object localObject2;
    if (!((Iterator)localObject1).hasNext())
    {
      localObject2 = localSQLiteDatabase.query("Sender", null, null, null, null, null, null);
      localObject1 = Long.valueOf(0L);
    }
    for (;;)
    {
      if (!((Cursor)localObject2).moveToNext())
      {
        this.credits = ((Long)localObject1).longValue();
        this.dataset.synchronize(this.sync);
        runOnUiThread(new Runnable()
        {
          public void run()
          {
            MainActivity.this.credit.setText(MainActivity.this.credits);
          }
        });
        ((Cursor)localObject2).close();
        localSQLiteDatabase.close();
        return;
        localObject2 = (Record)((Iterator)localObject1).next();
        Log.e("value", ((Record)localObject2).getKey() + "adsfa" + ((Record)localObject2).getValue());
        localSQLiteDatabase.delete("Sender", "name=?", new String[] { ((Record)localObject2).getKey() });
        localSQLiteDatabase.execSQL("INSERT INTO Sender VALUES(null,?,?,?)", new Object[] { ((Record)localObject2).getKey(), ((Record)localObject2).getValue(), getSharedPreferences("TinyFax", 0).getString("account", "") });
        break;
      }
      if ((getSharedPreferences("TinyFax", 0).getString("account", "").equals(((Cursor)localObject2).getString(3))) || (((Cursor)localObject2).getString(3).equals("")))
      {
        Log.e("syncinfo", ((Cursor)localObject2).getString(1) + "adsfa" + ((Cursor)localObject2).getString(2));
        try
        {
          if (Float.parseFloat(((Cursor)localObject2).getString(2)) != 0.0F) {
            this.dataset.put(((Cursor)localObject2).getString(1), ((Cursor)localObject2).getString(2));
          }
          long l1 = ((Long)localObject1).longValue();
          long l2 = Long.parseLong(((Cursor)localObject2).getString(2));
          localObject1 = Long.valueOf(l1 + l2);
        }
        catch (Exception localException) {}
      }
    }
  }
  
  public boolean updateDb(Long paramLong, int paramInt)
  {
    boolean bool = true;
    Cursor localCursor = this.db.query("Faxinfo", null, "uuid=?", new String[] { paramLong }, null, null, null);
    if (localCursor.moveToNext()) {
      if (localCursor.getInt(localCursor.getColumnIndex("status")) != 2) {
        break label162;
      }
    }
    label162:
    for (bool = false;; bool = true)
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("status", Integer.valueOf(paramInt));
      if (paramInt == 2) {
        localContentValues.put("faxid", getString(2131165304));
      }
      this.db.update("Faxinfo", localContentValues, "faxid=?", new String[] { paramLong });
      localCursor.close();
      return bool;
    }
  }
}
