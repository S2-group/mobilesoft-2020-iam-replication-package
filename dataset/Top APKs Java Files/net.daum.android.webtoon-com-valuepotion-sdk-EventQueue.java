package com.valuepotion.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.valuepotion.sdk.event.DataModel;
import com.valuepotion.sdk.event.EventModel;
import com.valuepotion.sdk.system.PreferenceHelper;
import com.valuepotion.sdk.system.SystemInfo;
import com.valuepotion.sdk.util.SdkUtils;
import com.valuepotion.sdk.util.StringUtils;
import com.valuepotion.sdk.util.VPLog;
import com.valuepotion.sdk.util.vphttpclient.IVPHttpClient.HttpClientAndResponse;
import com.valuepotion.sdk.util.vphttpclient.Request;
import com.valuepotion.sdk.util.vphttpclient.Response;
import com.valuepotion.sdk.util.vphttpclient.VPHttpClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class EventQueue
{
  private static final String TAG = "EventQueue";
  private static EventQueue instance;
  private boolean adClicked;
  private String apiEventUrl;
  private HashMap<String, Runnable> callbacksByEventName;
  private Timer dispatchTimer = new Timer();
  private SharedPreferences eventQueue;
  private int fetchMax;
  private ExecutorService pool = Executors.newSingleThreadExecutor();
  private final Semaphore queueSemaphore = new Semaphore(1, true);
  private int retryInterval;
  private int retryMax;
  
  private EventQueue()
  {
    initCallbacksByEventName();
  }
  
  private void dump()
  {
    this.pool.execute(new Dump(null));
  }
  
  public static EventQueue getInstance()
  {
    if (instance == null) {
      instance = new EventQueue();
    }
    return instance;
  }
  
  private int getIntValue(String paramString, int paramInt)
  {
    try
    {
      paramInt = this.eventQueue.getInt(paramString, paramInt);
      return paramInt;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  private int getNextRearIndex()
  {
    try
    {
      if (isEmpty())
      {
        putIntValue("front_index", 0);
        putIntValue("rear_index", 0);
      }
      else
      {
        putIntValue("rear_index", getIntValue("rear_index", -1) + 1);
      }
      int i = getIntValue("rear_index", -1);
      return i;
    }
    finally {}
  }
  
  private String getStringValue(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = this.eventQueue.getString(paramString1, paramString2);
      return paramString1;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  private void initCallbacksByEventName()
  {
    this.callbacksByEventName = new HashMap();
    this.callbacksByEventName.put("install", new Runnable()
    {
      public void run()
      {
        VPLog.v(EventQueue.TAG, "trackInstall is done. installed save");
        PreferenceHelper.saveInstalled();
      }
    });
    this.callbacksByEventName.put("install_referrer", new Runnable()
    {
      public void run()
      {
        VPLog.v(EventQueue.TAG, "trackInstallReferrer is done. installed save");
        PreferenceHelper.saveSentReferrer();
      }
    });
    this.callbacksByEventName.put("update", new Runnable()
    {
      public void run()
      {
        VPLog.v(EventQueue.TAG, "trackUpdate is done. installed save");
        PreferenceHelper.saveInstalled();
      }
    });
  }
  
  private void insertQueueElement(String paramString)
  {
    try
    {
      if (StringUtils.isNotEmpty(paramString))
      {
        int i = getNextRearIndex();
        String str = TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("insertQueueElement : ");
        localStringBuilder.append(paramString);
        VPLog.d(str, localStringBuilder.toString());
        putStringValue(String.valueOf(i), paramString);
      }
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  private String popFrontElement()
  {
    try
    {
      int i = getIntValue("front_index", -1);
      String str1 = getStringValue(String.valueOf(i), null);
      putIntValue("front_index", i + 1);
      String str2 = TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("popFrontElement : ");
      localStringBuilder.append(str1);
      VPLog.d(str2, localStringBuilder.toString());
      return str1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
  private void putIntValue(String paramString, int paramInt)
  {
    try
    {
      SharedPreferences.Editor localEditor = this.eventQueue.edit();
      localEditor.putInt(paramString, paramInt);
      localEditor.commit();
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
  private void putStringValue(String paramString1, String paramString2)
  {
    try
    {
      SharedPreferences.Editor localEditor = this.eventQueue.edit();
      localEditor.putString(paramString1, paramString2);
      localEditor.commit();
      return;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  private void trySleep(int paramInt)
  {
    long l = paramInt;
    try
    {
      Thread.sleep(l);
      return;
    }
    catch (InterruptedException localInterruptedException) {}
  }
  
  public void addEvent(DataModel paramDataModel, String paramString)
  {
    VPLog.d(TAG, paramString);
    this.pool.execute(new Add(paramDataModel));
    this.dispatchTimer.schedule(new TimerTask()
    {
      public void run()
      {
        EventQueue.this.dump();
      }
    }, 300L);
  }
  
  public void init(Context paramContext, ValuePotionCore paramValuePotionCore)
  {
    try
    {
      this.eventQueue = paramContext.getSharedPreferences("queue", 0);
      paramContext = paramValuePotionCore.getConfig();
      this.retryInterval = paramContext.getEventRetryInterval();
      this.retryMax = paramContext.getEventRetryMax();
      this.fetchMax = paramContext.getEventFetchMax();
      this.apiEventUrl = paramContext.getApiEventUrl();
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  protected boolean isEmpty()
  {
    try
    {
      int i = getIntValue("front_index", -1);
      int j = getIntValue("rear_index", -1);
      boolean bool;
      if ((i != -1) && (j != -1) && (i <= j)) {
        bool = false;
      } else {
        bool = true;
      }
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void setAdClicked()
  {
    this.adClicked = true;
  }
  
  public void setApiEventUrl(String paramString)
  {
    try
    {
      this.apiEventUrl = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  void trackInstalledApps(final Context paramContext)
  {
    if (StringUtils.isEmpty(this.apiEventUrl)) {
      return;
    }
    final SharedPreferences localSharedPreferences = PreferenceHelper.getSharedPreferences(paramContext);
    long l = localSharedPreferences.getLong("last_installed_checked", 0L);
    if ((System.currentTimeMillis() - l < 43200000L) && (!this.adClicked)) {
      return;
    }
    this.adClicked = false;
    SdkUtils.runDelayedOnWorkerThread(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          try
          {
            localObject2 = paramContext.getPackageManager().getInstalledApplications(128);
            Object localObject1 = new JSONArray();
            localObject2 = ((List)localObject2).iterator();
            boolean bool = ((Iterator)localObject2).hasNext();
            int j = 0;
            int i = 0;
            if (bool)
            {
              localObject3 = (ApplicationInfo)((Iterator)localObject2).next();
              if ((((ApplicationInfo)localObject3).flags & 0x81) != 0) {
                i = 1;
              }
              if (i != 0) {
                continue;
              }
              localObject3 = ((ApplicationInfo)localObject3).packageName;
              if (!StringUtils.isNotEmpty((String)localObject3)) {
                continue;
              }
              ((JSONArray)localObject1).put(localObject3);
              continue;
            }
            localSharedPreferences.edit().putLong("last_installed_checked", System.currentTimeMillis()).apply();
            localObject2 = new JSONObject();
            ((JSONObject)localObject2).put("installed_app", localObject1);
            localObject1 = new HashMap();
            ((HashMap)localObject1).put("body", ((JSONObject)localObject2).toString());
            localObject2 = new EventModel().toMap(null);
            ((HashMap)localObject2).put("os", SystemInfo.getInstance().getOsName());
            localObject2 = Request.generateParamString((Map)localObject2);
            localObject3 = new HashMap();
            ((HashMap)localObject3).put("Content-Type", "application/json; charset=utf-8");
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(EventQueue.this.apiEventUrl);
            localStringBuilder.append("/track_installed_app?");
            localStringBuilder.append((String)localObject2);
            localObject1 = VPHttpClient.Put(localStringBuilder.toString(), (Map)localObject1, (Map)localObject3, null, false, 5000);
            i = j;
            if (((IVPHttpClient.HttpClientAndResponse)localObject1).response != null)
            {
              i = j;
              if (200 <= ((IVPHttpClient.HttpClientAndResponse)localObject1).response.getCode())
              {
                i = j;
                if (((IVPHttpClient.HttpClientAndResponse)localObject1).response.getCode() < 400) {
                  i = 1;
                }
              }
            }
            localObject2 = EventQueue.TAG;
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("track installed_app is ");
            if (i != 0)
            {
              localObject1 = "success";
              ((StringBuilder)localObject3).append((String)localObject1);
              VPLog.d((String)localObject2, ((StringBuilder)localObject3).toString());
              return;
            }
          }
          catch (Throwable localThrowable)
          {
            Object localObject2 = EventQueue.TAG;
            Object localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("track installed_app is failed : ");
            ((StringBuilder)localObject3).append(localThrowable.getLocalizedMessage());
            VPLog.w((String)localObject2, ((StringBuilder)localObject3).toString());
            return;
          }
          String str = "failed";
        }
      }
    }, 1000L, 1);
  }
  
  private class Add
    implements Runnable
  {
    protected final DataModel event;
    
    public Add(DataModel paramDataModel)
    {
      this.event = paramDataModel;
    }
    
    private String getTrackingString()
      throws Exception
    {
      UserInfo localUserInfo = UserInfo.copy(ValuePotionCore.getUserInfo());
      return this.event.toJsonEvent(localUserInfo).toString();
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 17	com/valuepotion/sdk/EventQueue$Add:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   4: invokestatic 56	com/valuepotion/sdk/EventQueue:access$300	(Lcom/valuepotion/sdk/EventQueue;)Ljava/util/concurrent/Semaphore;
      //   7: invokevirtual 61	java/util/concurrent/Semaphore:acquire	()V
      //   10: aload_0
      //   11: getfield 17	com/valuepotion/sdk/EventQueue$Add:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   14: aload_0
      //   15: invokespecial 63	com/valuepotion/sdk/EventQueue$Add:getTrackingString	()Ljava/lang/String;
      //   18: invokestatic 67	com/valuepotion/sdk/EventQueue:access$900	(Lcom/valuepotion/sdk/EventQueue;Ljava/lang/String;)V
      //   21: goto +12 -> 33
      //   24: astore_1
      //   25: goto +19 -> 44
      //   28: astore_1
      //   29: aload_1
      //   30: invokestatic 73	com/valuepotion/sdk/VPExceptionHandler:report	(Ljava/lang/Throwable;)V
      //   33: aload_0
      //   34: getfield 17	com/valuepotion/sdk/EventQueue$Add:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   37: invokestatic 56	com/valuepotion/sdk/EventQueue:access$300	(Lcom/valuepotion/sdk/EventQueue;)Ljava/util/concurrent/Semaphore;
      //   40: invokevirtual 76	java/util/concurrent/Semaphore:release	()V
      //   43: return
      //   44: aload_0
      //   45: getfield 17	com/valuepotion/sdk/EventQueue$Add:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   48: invokestatic 56	com/valuepotion/sdk/EventQueue:access$300	(Lcom/valuepotion/sdk/EventQueue;)Ljava/util/concurrent/Semaphore;
      //   51: invokevirtual 76	java/util/concurrent/Semaphore:release	()V
      //   54: aload_1
      //   55: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	56	0	this	Add
      //   24	1	1	localObject	Object
      //   28	27	1	localException	Exception
      // Exception table:
      //   from	to	target	type
      //   0	21	24	finally
      //   29	33	24	finally
      //   0	21	28	java/lang/Exception
    }
  }
  
  private class Dump
    implements Runnable
  {
    private Dump() {}
    
    private ArrayList<JSONObject> convertEventsToJson(List<String> paramList)
    {
      ArrayList localArrayList = new ArrayList();
      paramList = paramList.iterator();
      for (;;)
      {
        String str;
        if (paramList.hasNext()) {
          str = (String)paramList.next();
        }
        try
        {
          localArrayList.add(new JSONObject(str));
        }
        catch (JSONException localJSONException) {}
        return localArrayList;
      }
    }
    
    private ArrayList<String> convertJsonToEvents(List<JSONObject> paramList)
    {
      ArrayList localArrayList = new ArrayList();
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        localArrayList.add(((JSONObject)paramList.next()).toString());
      }
      return localArrayList;
    }
    
    private boolean dumpChunk(List<String> paramList1, boolean paramBoolean, List<String> paramList2)
    {
      Object localObject1 = convertEventsToJson(paramList1);
      Object localObject2 = filterValuepotionTracking((ArrayList)localObject1);
      if (!((ArrayList)localObject2).isEmpty())
      {
        paramBoolean = sendTrackingRequest((ArrayList)localObject2, paramBoolean);
        String str = EventQueue.TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("dumping ");
        localStringBuilder.append(paramList1.size());
        localStringBuilder.append(" events : ");
        if (paramBoolean) {
          paramList1 = "succeeded";
        } else {
          paramList1 = "failed";
        }
        localStringBuilder.append(paramList1);
        VPLog.d(str, localStringBuilder.toString());
        if (paramBoolean)
        {
          executeCallbacks((ArrayList)localObject2);
          paramBoolean = true;
        }
        else
        {
          paramList2.addAll(convertJsonToEvents((List)localObject2));
        }
      }
      else
      {
        paramBoolean = false;
      }
      localObject1 = filterCustomTracking((ArrayList)localObject1);
      if (!((ArrayList)localObject1).isEmpty())
      {
        paramList1 = new ArrayList();
        localObject1 = ((ArrayList)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (JSONObject)((Iterator)localObject1).next();
          try
          {
            bool = sendCustomRequest(((JSONObject)localObject2).getString("customUrl"));
          }
          catch (JSONException localJSONException)
          {
            boolean bool;
            for (;;) {}
          }
          bool = false;
          if (!bool) {
            paramList1.add(localObject2);
          }
        }
        if (paramList1.isEmpty()) {
          return true;
        }
        paramList2.addAll(convertJsonToEvents(paramList1));
      }
      return paramBoolean;
    }
    
    private void executeCallbacks(ArrayList<JSONObject> paramArrayList)
    {
      if (EventQueue.this.callbacksByEventName == null) {
        return;
      }
      paramArrayList = paramArrayList.iterator();
      for (;;)
      {
        Object localObject;
        if (paramArrayList.hasNext()) {
          localObject = (JSONObject)paramArrayList.next();
        }
        try
        {
          localObject = ((JSONObject)localObject).optString("eventName");
          if ((localObject == null) || (!EventQueue.this.callbacksByEventName.containsKey(localObject))) {
            continue;
          }
          localObject = (Runnable)EventQueue.this.callbacksByEventName.get(localObject);
          try
          {
            ((Runnable)localObject).run();
          }
          catch (Exception localException1)
          {
            String str = EventQueue.TAG;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("failed to execute callback : ");
            localStringBuilder.append(localException1.getMessage());
            VPLog.d(str, localStringBuilder.toString());
            VPExceptionHandler.report(localException1);
          }
        }
        catch (Exception localException2) {}
        return;
      }
    }
    
    private ArrayList<String> extractEvents()
    {
      ArrayList localArrayList = new ArrayList();
      int i = 1;
      while ((i <= EventQueue.this.fetchMax) && (!EventQueue.this.isEmpty()))
      {
        localArrayList.add(EventQueue.this.popFrontElement());
        i += 1;
      }
      return localArrayList;
    }
    
    private ArrayList<JSONObject> filterCustomTracking(ArrayList<JSONObject> paramArrayList)
    {
      ArrayList localArrayList = new ArrayList();
      paramArrayList = paramArrayList.iterator();
      for (;;)
      {
        JSONObject localJSONObject;
        if (paramArrayList.hasNext()) {
          localJSONObject = (JSONObject)paramArrayList.next();
        }
        try
        {
          if (!StringUtils.isNotEmpty(localJSONObject.getString("customUrl"))) {
            continue;
          }
          localArrayList.add(localJSONObject);
        }
        catch (JSONException localJSONException) {}
        return localArrayList;
      }
    }
    
    private ArrayList<JSONObject> filterValuepotionTracking(ArrayList<JSONObject> paramArrayList)
    {
      ArrayList localArrayList = new ArrayList();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        JSONObject localJSONObject = (JSONObject)paramArrayList.next();
        try
        {
          if (!StringUtils.isEmpty(localJSONObject.getString("customUrl"))) {
            continue;
          }
          localArrayList.add(localJSONObject);
        }
        catch (JSONException localJSONException)
        {
          for (;;) {}
        }
        localArrayList.add(localJSONObject);
      }
      return localArrayList;
    }
    
    private void putBack(List<String> paramList)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        String str = (String)paramList.next();
        EventQueue.this.insertQueueElement(str);
      }
    }
    
    private boolean sendCustomRequest(String paramString)
    {
      boolean bool2 = false;
      try
      {
        paramString = VPHttpClient.Get(paramString, null, null, null);
        boolean bool1 = bool2;
        if (paramString.response != null)
        {
          bool1 = bool2;
          if (200 <= paramString.response.getCode())
          {
            int i = paramString.response.getCode();
            bool1 = bool2;
            if (i < 400) {
              bool1 = true;
            }
          }
        }
        return bool1;
      }
      catch (Exception paramString) {}
      return false;
    }
    
    private boolean sendTrackingRequest(ArrayList<JSONObject> paramArrayList, boolean paramBoolean)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("events", new JSONArray(paramArrayList).toString());
      boolean bool = false;
      try
      {
        paramArrayList = new StringBuilder();
        paramArrayList.append(EventQueue.this.apiEventUrl);
        paramArrayList.append("/track");
        paramArrayList = VPHttpClient.Post(paramArrayList.toString(), localHashMap, null, null, paramBoolean);
        paramBoolean = bool;
        if (paramArrayList.response != null)
        {
          paramBoolean = bool;
          if (200 <= paramArrayList.response.getCode())
          {
            int i = paramArrayList.response.getCode();
            paramBoolean = bool;
            if (i < 400) {
              paramBoolean = true;
            }
          }
        }
        return paramBoolean;
      }
      catch (Exception paramArrayList) {}
      return false;
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   4: invokestatic 242	com/valuepotion/sdk/EventQueue:access$300	(Lcom/valuepotion/sdk/EventQueue;)Ljava/util/concurrent/Semaphore;
      //   7: invokevirtual 247	java/util/concurrent/Semaphore:acquire	()V
      //   10: iconst_0
      //   11: istore_1
      //   12: aload_0
      //   13: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   16: invokevirtual 178	com/valuepotion/sdk/EventQueue:isEmpty	()Z
      //   19: ifne +228 -> 247
      //   22: aload_0
      //   23: invokespecial 249	com/valuepotion/sdk/EventQueue$Dump:extractEvents	()Ljava/util/ArrayList;
      //   26: astore_3
      //   27: new 28	java/util/ArrayList
      //   30: dup
      //   31: invokespecial 29	java/util/ArrayList:<init>	()V
      //   34: astore 4
      //   36: iload_1
      //   37: ifle +346 -> 383
      //   40: iconst_1
      //   41: istore_2
      //   42: goto +3 -> 45
      //   45: aload_0
      //   46: aload_3
      //   47: iload_2
      //   48: aload 4
      //   50: invokespecial 251	com/valuepotion/sdk/EventQueue$Dump:dumpChunk	(Ljava/util/List;ZLjava/util/List;)Z
      //   53: ifeq +22 -> 75
      //   56: aload_0
      //   57: aload 4
      //   59: invokespecial 253	com/valuepotion/sdk/EventQueue$Dump:putBack	(Ljava/util/List;)V
      //   62: aload_0
      //   63: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   66: sipush 300
      //   69: invokestatic 257	com/valuepotion/sdk/EventQueue:access$400	(Lcom/valuepotion/sdk/EventQueue;I)V
      //   72: goto -60 -> 12
      //   75: iload_1
      //   76: iconst_1
      //   77: iadd
      //   78: istore_1
      //   79: iload_1
      //   80: aload_0
      //   81: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   84: invokestatic 260	com/valuepotion/sdk/EventQueue:access$500	(Lcom/valuepotion/sdk/EventQueue;)I
      //   87: if_icmple +53 -> 140
      //   90: invokestatic 82	com/valuepotion/sdk/EventQueue:access$000	()Ljava/lang/String;
      //   93: astore_3
      //   94: new 84	java/lang/StringBuilder
      //   97: dup
      //   98: invokespecial 85	java/lang/StringBuilder:<init>	()V
      //   101: astore 4
      //   103: aload 4
      //   105: ldc_w 262
      //   108: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   111: pop
      //   112: aload 4
      //   114: iload_1
      //   115: invokevirtual 98	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   118: pop
      //   119: aload 4
      //   121: ldc_w 264
      //   124: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   127: pop
      //   128: aload_3
      //   129: aload 4
      //   131: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   134: invokestatic 111	com/valuepotion/sdk/util/VPLog:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   137: goto +110 -> 247
      //   140: aload_0
      //   141: aload 4
      //   143: invokespecial 253	com/valuepotion/sdk/EventQueue$Dump:putBack	(Ljava/util/List;)V
      //   146: invokestatic 82	com/valuepotion/sdk/EventQueue:access$000	()Ljava/lang/String;
      //   149: astore_3
      //   150: new 84	java/lang/StringBuilder
      //   153: dup
      //   154: invokespecial 85	java/lang/StringBuilder:<init>	()V
      //   157: astore 4
      //   159: aload 4
      //   161: ldc_w 266
      //   164: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   167: pop
      //   168: aload 4
      //   170: iload_1
      //   171: invokevirtual 98	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   174: pop
      //   175: aload 4
      //   177: ldc_w 268
      //   180: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   183: pop
      //   184: aload 4
      //   186: aload_0
      //   187: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   190: invokestatic 271	com/valuepotion/sdk/EventQueue:access$600	(Lcom/valuepotion/sdk/EventQueue;)I
      //   193: iload_1
      //   194: imul
      //   195: invokevirtual 98	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   198: pop
      //   199: aload_3
      //   200: aload 4
      //   202: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   205: invokestatic 111	com/valuepotion/sdk/util/VPLog:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   208: aload_0
      //   209: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   212: invokestatic 242	com/valuepotion/sdk/EventQueue:access$300	(Lcom/valuepotion/sdk/EventQueue;)Ljava/util/concurrent/Semaphore;
      //   215: invokevirtual 274	java/util/concurrent/Semaphore:release	()V
      //   218: aload_0
      //   219: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   222: aload_0
      //   223: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   226: invokestatic 271	com/valuepotion/sdk/EventQueue:access$600	(Lcom/valuepotion/sdk/EventQueue;)I
      //   229: iload_1
      //   230: imul
      //   231: invokestatic 257	com/valuepotion/sdk/EventQueue:access$400	(Lcom/valuepotion/sdk/EventQueue;I)V
      //   234: aload_0
      //   235: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   238: invokestatic 242	com/valuepotion/sdk/EventQueue:access$300	(Lcom/valuepotion/sdk/EventQueue;)Ljava/util/concurrent/Semaphore;
      //   241: invokevirtual 247	java/util/concurrent/Semaphore:acquire	()V
      //   244: goto -232 -> 12
      //   247: aload_0
      //   248: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   251: invokevirtual 178	com/valuepotion/sdk/EventQueue:isEmpty	()Z
      //   254: ifeq +65 -> 319
      //   257: aload_0
      //   258: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   261: ldc_w 276
      //   264: iconst_m1
      //   265: invokestatic 280	com/valuepotion/sdk/EventQueue:access$700	(Lcom/valuepotion/sdk/EventQueue;Ljava/lang/String;I)V
      //   268: aload_0
      //   269: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   272: astore_3
      //   273: goto +38 -> 311
      //   276: astore_3
      //   277: goto +53 -> 330
      //   280: astore_3
      //   281: aload_3
      //   282: invokestatic 170	com/valuepotion/sdk/VPExceptionHandler:report	(Ljava/lang/Throwable;)V
      //   285: aload_0
      //   286: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   289: invokevirtual 178	com/valuepotion/sdk/EventQueue:isEmpty	()Z
      //   292: ifeq +27 -> 319
      //   295: aload_0
      //   296: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   299: ldc_w 276
      //   302: iconst_m1
      //   303: invokestatic 280	com/valuepotion/sdk/EventQueue:access$700	(Lcom/valuepotion/sdk/EventQueue;Ljava/lang/String;I)V
      //   306: aload_0
      //   307: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   310: astore_3
      //   311: aload_3
      //   312: ldc_w 282
      //   315: iconst_m1
      //   316: invokestatic 280	com/valuepotion/sdk/EventQueue:access$700	(Lcom/valuepotion/sdk/EventQueue;Ljava/lang/String;I)V
      //   319: aload_0
      //   320: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   323: invokestatic 242	com/valuepotion/sdk/EventQueue:access$300	(Lcom/valuepotion/sdk/EventQueue;)Ljava/util/concurrent/Semaphore;
      //   326: invokevirtual 274	java/util/concurrent/Semaphore:release	()V
      //   329: return
      //   330: aload_0
      //   331: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   334: invokevirtual 178	com/valuepotion/sdk/EventQueue:isEmpty	()Z
      //   337: ifeq +25 -> 362
      //   340: aload_0
      //   341: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   344: ldc_w 276
      //   347: iconst_m1
      //   348: invokestatic 280	com/valuepotion/sdk/EventQueue:access$700	(Lcom/valuepotion/sdk/EventQueue;Ljava/lang/String;I)V
      //   351: aload_0
      //   352: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   355: ldc_w 282
      //   358: iconst_m1
      //   359: invokestatic 280	com/valuepotion/sdk/EventQueue:access$700	(Lcom/valuepotion/sdk/EventQueue;Ljava/lang/String;I)V
      //   362: aload_0
      //   363: getfield 15	com/valuepotion/sdk/EventQueue$Dump:this$0	Lcom/valuepotion/sdk/EventQueue;
      //   366: invokestatic 242	com/valuepotion/sdk/EventQueue:access$300	(Lcom/valuepotion/sdk/EventQueue;)Ljava/util/concurrent/Semaphore;
      //   369: invokevirtual 274	java/util/concurrent/Semaphore:release	()V
      //   372: aload_3
      //   373: athrow
      //   374: astore_3
      //   375: goto -56 -> 319
      //   378: astore 4
      //   380: goto -18 -> 362
      //   383: iconst_0
      //   384: istore_2
      //   385: goto -340 -> 45
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	388	0	this	Dump
      //   11	220	1	i	int
      //   41	344	2	bool	boolean
      //   26	247	3	localObject1	Object
      //   276	1	3	localObject2	Object
      //   280	2	3	localException1	Exception
      //   310	63	3	localEventQueue	EventQueue
      //   374	1	3	localException2	Exception
      //   34	167	4	localObject3	Object
      //   378	1	4	localException3	Exception
      // Exception table:
      //   from	to	target	type
      //   0	10	276	finally
      //   12	36	276	finally
      //   45	72	276	finally
      //   79	137	276	finally
      //   140	244	276	finally
      //   281	285	276	finally
      //   0	10	280	java/lang/Exception
      //   12	36	280	java/lang/Exception
      //   45	72	280	java/lang/Exception
      //   79	137	280	java/lang/Exception
      //   140	244	280	java/lang/Exception
      //   247	273	374	java/lang/Exception
      //   285	311	374	java/lang/Exception
      //   311	319	374	java/lang/Exception
      //   330	362	378	java/lang/Exception
    }
  }
}
