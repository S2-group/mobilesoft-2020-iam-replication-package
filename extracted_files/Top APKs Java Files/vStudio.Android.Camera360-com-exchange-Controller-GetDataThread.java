package com.exchange.Controller;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.exchange.Model.AdvertiserConfig;
import com.exchange.Public.DeviceManager;
import com.exchange.Public.ExchangeConstants;
import com.exchange.Public.R;
import java.util.List;
import java.util.Set;

public class GetDataThread
  extends Thread
{
  final Handler getDataHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      GetDataThread.this.mDataReceiverListener.dataReceived(paramAnonymousMessage.what);
    }
  };
  Context mContext;
  public ExchangeDataRequestListener mDataReceiverListener;
  
  public GetDataThread(Context paramContext, ExchangeDataRequestListener paramExchangeDataRequestListener)
  {
    this.mContext = paramContext;
    this.mDataReceiverListener = paramExchangeDataRequestListener;
    init(paramContext);
  }
  
  public static void init(Context paramContext)
  {
    R.init(paramContext);
    ExchangeConstants.appIcon = new int[] { R.drawable("exchange_zhanwei"), R.drawable("exchange_zhanwei"), R.drawable("exchange_zhanwei"), R.drawable("exchange_zhanwei"), R.drawable("exchange_zhanwei"), R.drawable("exchange_zhanwei"), R.drawable("exchange_zhanwei"), R.drawable("exchange_zhanwei") };
  }
  
  public void run()
  {
    synchronized (ExchangeConstants.getDataMutex)
    {
      try
      {
        ExchangeDataService.context = this.mContext;
        if (!ExchangeDataService.hasData())
        {
          if (ExchangeConstants.isTestMode) {
            break label46;
          }
          ExchangeDataService.request();
        }
        if (ExchangeDataService.hasData()) {
          break label78;
        }
        this.getDataHandler.sendEmptyMessage(0);
      }
      catch (Exception localException)
      {
        for (;;)
        {
          label46:
          localException.printStackTrace();
        }
      }
      return;
      ExchangeDataService.mAdvertisers = ExchangeDataService.getExampleAds(0).subList(0, ExchangeConstants.REQUEST_NUMBER);
    }
    label78:
    if (ExchangeDataService.mAdvertisers.size() < ExchangeConstants.CURTAIN_PEARL_COUNT_VERTICAL) {
      ExchangeConstants.CURTAIN_PEARL_COUNT_VERTICAL = ExchangeDataService.mAdvertisers.size();
    }
    if (ExchangeDataService.mAdvertisers.size() < ExchangeConstants.CURTAIN_LIST_COUNT_VERTICAL) {
      ExchangeConstants.CURTAIN_LIST_COUNT_VERTICAL = ExchangeDataService.mAdvertisers.size();
    }
    if (ExchangeDataService.mAdvertisers.size() < ExchangeConstants.DRAWER_LIST_COUNT_VERTICAL) {
      ExchangeConstants.DRAWER_LIST_COUNT_VERTICAL = ExchangeDataService.mAdvertisers.size();
    }
    if (ExchangeDataService.mAdvertisers.size() < ExchangeConstants.CONTAINER_LIST_COUNT) {
      ExchangeConstants.CONTAINER_LIST_COUNT = ExchangeDataService.mAdvertisers.size();
    }
    DeviceManager.getInstalledPackages(this.mContext);
    int i;
    if (DeviceManager.installedApps != null) {
      i = 0;
    }
    for (;;)
    {
      if (i >= ExchangeDataService.mAdvertisers.size())
      {
        this.getDataHandler.sendEmptyMessage(1);
        break;
      }
      AdvertiserConfig localAdvertiserConfig = (AdvertiserConfig)ExchangeDataService.mAdvertisers.get(i);
      if (DeviceManager.installedApps.contains(localAdvertiserConfig.packageName)) {
        localAdvertiserConfig.installed = true;
      }
      i += 1;
    }
  }
}
