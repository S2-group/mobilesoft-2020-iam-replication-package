package com.keyboard.common.artemojimodule.data;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.keyboard.common.artemojimodule.R.array;
import com.keyboard.common.utilsmodule.AccessResUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArtEmojiManager
{
  private static final int MSG_NOTIFY_BASE = 9999;
  public static final int MSG_NOTIFY_SCAN_ART_EMOJI_DONE = 10002;
  public static final int MSG_NOTIFY_SCAN_ART_EMOJI_PKG = 10001;
  public static final int MSG_NOTIFY_SCAN_ART_EMOJI_START = 10000;
  private static final int MSG_SCAN_ART_EMOJI = 100;
  private static final String TAG = ArtEmojiManager.class.getSimpleName();
  private static final int TASK_CANCELLED = 1;
  private static final int TASK_RUNNING = 0;
  public static final int TYPE_ALL = 3;
  public static final int TYPE_EXTERNAL = 2;
  public static final int TYPE_INTERNAL = 1;
  private static ArtEmojiManager sInstance = null;
  private ArtEmojiComparator mComparator = null;
  private Context mContext = null;
  private Message mCurrentTask = null;
  private AtomicBoolean mIsQuit = null;
  private ArrayList<Handler> mListener = null;
  private String mRecentFileName;
  private Handler mWorkHandler = null;
  private HandlerThread mWorkThread = null;
  
  private ArtEmojiManager(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mComparator = new ArtEmojiComparator();
    this.mListener = new ArrayList();
    initWorkThread();
  }
  
  private void cancelCurrentTask()
  {
    if (this.mCurrentTask != null) {
      synchronized (this.mCurrentTask)
      {
        this.mCurrentTask.arg2 = 1;
        return;
      }
    }
  }
  
  /* Error */
  private String getArtEmojiData(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aload_1
    //   7: invokevirtual 112	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   10: astore 7
    //   12: aload 7
    //   14: ifnonnull +5 -> 19
    //   17: aconst_null
    //   18: areturn
    //   19: aload 6
    //   21: astore_1
    //   22: aload 7
    //   24: aload_2
    //   25: invokevirtual 118	android/content/pm/PackageManager:getResourcesForApplication	(Ljava/lang/String;)Landroid/content/res/Resources;
    //   28: invokevirtual 124	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   31: aload_3
    //   32: invokevirtual 130	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   35: astore_2
    //   36: aload_2
    //   37: astore_1
    //   38: aload_2
    //   39: astore 5
    //   41: aload_2
    //   42: invokevirtual 136	java/io/InputStream:available	()I
    //   45: istore 4
    //   47: aload_2
    //   48: astore_1
    //   49: aload_2
    //   50: astore 5
    //   52: iload 4
    //   54: newarray byte
    //   56: astore_3
    //   57: aload_2
    //   58: astore_1
    //   59: aload_2
    //   60: astore 5
    //   62: aload_2
    //   63: aload_3
    //   64: iconst_0
    //   65: iload 4
    //   67: invokevirtual 140	java/io/InputStream:read	([BII)I
    //   70: pop
    //   71: aload_2
    //   72: astore_1
    //   73: aload_2
    //   74: astore 5
    //   76: new 142	java/lang/String
    //   79: dup
    //   80: aload_3
    //   81: ldc -112
    //   83: invokespecial 147	java/lang/String:<init>	([BLjava/lang/String;)V
    //   86: astore_3
    //   87: aload_2
    //   88: ifnull +7 -> 95
    //   91: aload_2
    //   92: invokevirtual 150	java/io/InputStream:close	()V
    //   95: aload_3
    //   96: areturn
    //   97: astore_2
    //   98: aload_1
    //   99: astore 5
    //   101: aload_2
    //   102: invokevirtual 153	java/lang/Exception:printStackTrace	()V
    //   105: aload_1
    //   106: ifnull +7 -> 113
    //   109: aload_1
    //   110: invokevirtual 150	java/io/InputStream:close	()V
    //   113: aconst_null
    //   114: areturn
    //   115: astore_1
    //   116: aload 5
    //   118: ifnull +8 -> 126
    //   121: aload 5
    //   123: invokevirtual 150	java/io/InputStream:close	()V
    //   126: aload_1
    //   127: athrow
    //   128: astore_1
    //   129: goto -34 -> 95
    //   132: astore_1
    //   133: goto -20 -> 113
    //   136: astore_2
    //   137: goto -11 -> 126
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	this	ArtEmojiManager
    //   0	140	1	paramContext	Context
    //   0	140	2	paramString1	String
    //   0	140	3	paramString2	String
    //   45	21	4	i	int
    //   1	121	5	localObject1	Object
    //   4	16	6	localObject2	Object
    //   10	13	7	localPackageManager	PackageManager
    // Exception table:
    //   from	to	target	type
    //   22	36	97	java/lang/Exception
    //   41	47	97	java/lang/Exception
    //   52	57	97	java/lang/Exception
    //   62	71	97	java/lang/Exception
    //   76	87	97	java/lang/Exception
    //   22	36	115	finally
    //   41	47	115	finally
    //   52	57	115	finally
    //   62	71	115	finally
    //   76	87	115	finally
    //   101	105	115	finally
    //   91	95	128	java/lang/Exception
    //   109	113	132	java/lang/Exception
    //   121	126	136	java/lang/Exception
  }
  
  public static ArtEmojiManager getInstance(Context paramContext)
  {
    if (sInstance == null) {}
    try
    {
      if (sInstance == null) {
        sInstance = new ArtEmojiManager(paramContext);
      }
      return sInstance;
    }
    finally {}
  }
  
  private void initWorkThread()
  {
    if (this.mWorkThread != null)
    {
      Log.w(TAG, "work thread is alreay running, ignore it !!");
      return;
    }
    this.mIsQuit = new AtomicBoolean(false);
    this.mWorkThread = new HandlerThread(TAG + hashCode());
    this.mWorkThread.start();
    this.mWorkHandler = new Handler(this.mWorkThread.getLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default: 
          return false;
        }
        ArtEmojiManager.this.onWorkLoadArtEmoji(paramAnonymousMessage);
        return true;
      }
    });
  }
  
  private boolean isTaskCancelled(Message paramMessage)
  {
    try
    {
      return 1 == paramMessage.arg2;
    }
    finally {}
  }
  
  private void notifyListener(int paramInt1, int paramInt2, int paramInt3, Object paramObject)
  {
    Iterator localIterator = this.mListener.iterator();
    while (localIterator.hasNext())
    {
      Handler localHandler = (Handler)localIterator.next();
      localHandler.sendMessage(localHandler.obtainMessage(paramInt1, paramInt2, paramInt3, paramObject));
    }
  }
  
  private void onWorkLoadArtEmoji(Message paramMessage)
  {
    notifyListener(10000, 0, 0, null);
    int i = paramMessage.arg1;
    if ((i & 0x1) != 0) {
      scanInternalPkg(paramMessage);
    }
    if ((i & 0x2) != 0) {
      scanExternalPkg(paramMessage);
    }
    notifyListener(10002, 0, 0, null);
  }
  
  private void scanExternalPkg(Message paramMessage)
  {
    List localList = this.mContext.getPackageManager().getInstalledApplications(8192);
    Iterator localIterator = localList.iterator();
    for (;;)
    {
      String str1;
      ArtEmojiPkg localArtEmojiPkg;
      if (localIterator.hasNext())
      {
        str1 = ((ApplicationInfo)localIterator.next()).packageName;
        localArtEmojiPkg = new ArtEmojiPkg();
      }
      try
      {
        if (str1.startsWith("com.keyboard.artemoji"))
        {
          localArtEmojiPkg.mPkgName = str1;
          String str2 = AccessResUtils.getString(this.mContext, "art_name", str1);
          if (str2 != null)
          {
            localArtEmojiPkg.mName = str2;
            str1 = getArtEmojiData(this.mContext, str1, "art_emoji.json");
            if (str1 != null)
            {
              localArtEmojiPkg.mArtEmojiList = JsonDecoder.decodeArtEmojiList(str1);
              if (localArtEmojiPkg.mArtEmojiList != null) {
                Collections.sort(localArtEmojiPkg.mArtEmojiList, this.mComparator);
              }
              notifyListener(10001, 0, 0, localArtEmojiPkg);
            }
          }
        }
        else if (shouldInterruptCurrentTask(paramMessage))
        {
          localList.clear();
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void scanInternalPkg(Message paramMessage)
  {
    TypedArray localTypedArray = this.mContext.getResources().obtainTypedArray(R.array.art_emoji_internal_list);
    int j = localTypedArray.length();
    int i;
    if (j % 4 == 0) {
      i = 0;
    }
    for (;;)
    {
      ArtEmojiPkg localArtEmojiPkg;
      if (i < j)
      {
        localArtEmojiPkg = new ArtEmojiPkg();
        if (i != 0) {
          break label172;
        }
      }
      for (;;)
      {
        try
        {
          this.mRecentFileName = localTypedArray.getString(i + 3);
          str = JsonDecoder.getRecentFileData(this.mContext.getApplicationContext().getFilesDir() + "/" + this.mRecentFileName);
          localArtEmojiPkg.mPkgName = localTypedArray.getString(i);
          localArtEmojiPkg.mName = localTypedArray.getString(i + 1);
          localArtEmojiPkg.mRank = localTypedArray.getIndex(i + 2);
          localArtEmojiPkg.mArtEmojiList = JsonDecoder.decodeArtEmojiList(str);
          notifyListener(10001, 0, 0, localArtEmojiPkg);
          if (!shouldInterruptCurrentTask(paramMessage)) {
            continue;
          }
          localTypedArray.recycle();
          return;
        }
        catch (Exception localException)
        {
          String str;
          label172:
          localException.printStackTrace();
          i += 4;
        }
        localArtEmojiPkg.mPkgName = localTypedArray.getString(i);
        localArtEmojiPkg.mName = localTypedArray.getString(i + 1);
        localArtEmojiPkg.mRank = localTypedArray.getIndex(i + 2);
        str = localTypedArray.getString(i + 3);
        localArtEmojiPkg.mArtEmojiList = JsonDecoder.decodeArtEmojiFromAssets(this.mContext, str);
        if (localArtEmojiPkg.mArtEmojiList != null) {
          Collections.sort(localArtEmojiPkg.mArtEmojiList, this.mComparator);
        }
      }
    }
  }
  
  private boolean shouldInterruptCurrentTask(Message paramMessage)
  {
    return (true == this.mIsQuit.get()) || (isTaskCancelled(paramMessage));
  }
  
  public void addListener(Handler paramHandler)
  {
    if (!this.mListener.contains(paramHandler)) {
      this.mListener.add(paramHandler);
    }
  }
  
  public void cancelTask()
  {
    if (this.mCurrentTask != null)
    {
      if (this.mWorkHandler != null) {
        this.mWorkHandler.removeMessages(this.mCurrentTask.what);
      }
      cancelCurrentTask();
      this.mCurrentTask = null;
    }
  }
  
  public void destroy()
  {
    try
    {
      cancelTask();
      this.mIsQuit.compareAndSet(false, true);
      this.mWorkThread.quit();
      this.mListener.clear();
      this.mWorkThread = null;
      this.mWorkHandler = null;
      sInstance = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public String getRecentFileName()
  {
    return this.mRecentFileName;
  }
  
  public void postScanArtEmojiPkgTask()
  {
    postScanArtEmojiPkgTask(3);
  }
  
  public void postScanArtEmojiPkgTask(int paramInt)
  {
    cancelCurrentTask();
    this.mCurrentTask = this.mWorkHandler.obtainMessage(100, 0, 0);
    this.mCurrentTask.arg1 = paramInt;
    this.mWorkHandler.sendMessage(this.mCurrentTask);
  }
  
  public void removelistener(Handler paramHandler)
  {
    this.mListener.remove(paramHandler);
  }
}
