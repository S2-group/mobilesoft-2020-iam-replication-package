package com.samsung.accessory.goproviders.sagallery.notification;

import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import com.samsung.accessory.goproviders.R.drawable;
import com.samsung.accessory.goproviders.R.string;
import com.samsung.accessory.goproviders.sagallery.utils.SAGalleryAppFeatures;
import java.util.List;

public class SAGalleryNotificationController
{
  private static int NOTIFICATION_ID_ONGOING = 1000;
  private static int NOTIFICATION_IN_COMPLETE = 1001;
  private static final String TAG = "SAGalleryNotificationController";
  private static int mCompletedFileCnt = 0;
  private static int mCurrentFileCnt;
  private static int mFailedFileCnt = 0;
  private static int mMaxFileCnt = 0;
  private Notification.Builder mBuilder = null;
  private String mConnectedDeviceName = null;
  Context mContext;
  private int mCurrentProgress = 0;
  private boolean mIndeterminate = true;
  private String mLastFilePathName = null;
  private int mMaxProgress = 0;
  private NotificationManager mNotificationManager = null;
  private String sendMobile;
  
  static
  {
    mCurrentFileCnt = 0;
  }
  
  public SAGalleryNotificationController(Context paramContext)
  {
    this.mContext = paramContext;
    this.mConnectedDeviceName = SAGalleryAppFeatures.getConntectedDeviceName(this.mContext);
    this.sendMobile = (this.mConnectedDeviceName + " : " + this.mContext.getString(R.string.transfer_to_mobile));
    if (this.mNotificationManager == null) {
      this.mNotificationManager = ((NotificationManager)this.mContext.getSystemService("notification"));
    }
    if (this.mBuilder == null)
    {
      this.mBuilder = new Notification.Builder(paramContext);
      this.mBuilder.setSmallIcon(R.drawable.indicator_gallery);
      this.mBuilder.setContentTitle(this.sendMobile);
      this.mBuilder.setAutoCancel(true);
      this.mBuilder.setOngoing(true);
    }
  }
  
  /* Error */
  public static Uri getMediaUri(Context paramContext, Uri paramUri, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_0
    //   7: invokevirtual 138	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   10: astore_0
    //   11: aload 4
    //   13: astore_3
    //   14: aload_0
    //   15: aload_1
    //   16: iconst_2
    //   17: anewarray 140	java/lang/String
    //   20: dup
    //   21: iconst_0
    //   22: ldc -114
    //   24: aastore
    //   25: dup
    //   26: iconst_1
    //   27: ldc -112
    //   29: aastore
    //   30: ldc -110
    //   32: iconst_1
    //   33: anewarray 140	java/lang/String
    //   36: dup
    //   37: iconst_0
    //   38: aload_2
    //   39: aastore
    //   40: aconst_null
    //   41: invokevirtual 152	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   44: astore 6
    //   46: aload 5
    //   48: astore_0
    //   49: aload 6
    //   51: ifnull +76 -> 127
    //   54: aload 5
    //   56: astore_0
    //   57: aload 6
    //   59: invokeinterface 158 1 0
    //   64: ifle +63 -> 127
    //   67: aload 5
    //   69: astore_0
    //   70: aload 6
    //   72: invokeinterface 162 1 0
    //   77: ifeq +50 -> 127
    //   80: aload 5
    //   82: astore_0
    //   83: aload 6
    //   85: aload 6
    //   87: ldc -112
    //   89: invokeinterface 166 2 0
    //   94: invokeinterface 167 2 0
    //   99: aload_2
    //   100: invokevirtual 171	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   103: ifeq +24 -> 127
    //   106: aload_1
    //   107: aload 6
    //   109: aload 6
    //   111: ldc -114
    //   113: invokeinterface 166 2 0
    //   118: invokeinterface 175 2 0
    //   123: invokestatic 181	android/content/ContentUris:withAppendedId	(Landroid/net/Uri;J)Landroid/net/Uri;
    //   126: astore_0
    //   127: aload 6
    //   129: ifnull +16 -> 145
    //   132: iconst_0
    //   133: ifeq +56 -> 189
    //   136: aload_0
    //   137: astore_3
    //   138: aload 6
    //   140: invokeinterface 184 1 0
    //   145: aload_0
    //   146: areturn
    //   147: astore_1
    //   148: aload_0
    //   149: astore_3
    //   150: new 186	java/lang/NullPointerException
    //   153: dup
    //   154: invokespecial 187	java/lang/NullPointerException:<init>	()V
    //   157: athrow
    //   158: astore_0
    //   159: ldc 12
    //   161: new 72	java/lang/StringBuilder
    //   164: dup
    //   165: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   168: ldc -67
    //   170: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: aload_0
    //   174: invokevirtual 190	java/lang/SecurityException:toString	()Ljava/lang/String;
    //   177: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   183: invokestatic 196	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   186: pop
    //   187: aload_3
    //   188: areturn
    //   189: aload_0
    //   190: astore_3
    //   191: aload 6
    //   193: invokeinterface 184 1 0
    //   198: aload_0
    //   199: areturn
    //   200: astore_1
    //   201: aload_1
    //   202: athrow
    //   203: astore_0
    //   204: aload 6
    //   206: ifnull +17 -> 223
    //   209: aload_1
    //   210: ifnull +30 -> 240
    //   213: aload 4
    //   215: astore_3
    //   216: aload 6
    //   218: invokeinterface 184 1 0
    //   223: aload 4
    //   225: astore_3
    //   226: aload_0
    //   227: athrow
    //   228: astore_2
    //   229: aload 4
    //   231: astore_3
    //   232: aload_1
    //   233: aload_2
    //   234: invokevirtual 200	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   237: goto -14 -> 223
    //   240: aload 4
    //   242: astore_3
    //   243: aload 6
    //   245: invokeinterface 184 1 0
    //   250: goto -27 -> 223
    //   253: astore_0
    //   254: aconst_null
    //   255: astore_1
    //   256: goto -52 -> 204
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	259	0	paramContext	Context
    //   0	259	1	paramUri	Uri
    //   0	259	2	paramString	String
    //   13	230	3	localObject1	Object
    //   1	240	4	localObject2	Object
    //   4	77	5	localObject3	Object
    //   44	200	6	localCursor	android.database.Cursor
    // Exception table:
    //   from	to	target	type
    //   138	145	147	java/lang/Throwable
    //   14	46	158	java/lang/SecurityException
    //   138	145	158	java/lang/SecurityException
    //   150	158	158	java/lang/SecurityException
    //   191	198	158	java/lang/SecurityException
    //   216	223	158	java/lang/SecurityException
    //   226	228	158	java/lang/SecurityException
    //   232	237	158	java/lang/SecurityException
    //   243	250	158	java/lang/SecurityException
    //   57	67	200	java/lang/Throwable
    //   70	80	200	java/lang/Throwable
    //   83	127	200	java/lang/Throwable
    //   201	203	203	finally
    //   216	223	228	java/lang/Throwable
    //   57	67	253	finally
    //   70	80	253	finally
    //   83	127	253	finally
  }
  
  private void setContent(int paramInt)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    StringBuilder localStringBuilder2 = new StringBuilder();
    if (this.mMaxProgress > 0)
    {
      int i = this.mMaxProgress / 100;
      localStringBuilder2.append(String.format("%d", new Object[] { Integer.valueOf(mCurrentFileCnt) })).append("/").append(String.format("%d", new Object[] { Integer.valueOf(i) }));
      localStringBuilder1.append(String.format("%d", new Object[] { Integer.valueOf(paramInt * 100 / this.mMaxProgress) })).append("%");
    }
    setContent(localStringBuilder1.toString(), localStringBuilder2.toString());
  }
  
  private void setContent(String paramString1, String paramString2)
  {
    try
    {
      this.mBuilder.setContentText(paramString1);
      this.mBuilder.setContentInfo(paramString2);
      return;
    }
    catch (NullPointerException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  private void setFileCount(int paramInt)
  {
    mMaxFileCnt = paramInt;
    this.mMaxProgress = (paramInt * 100);
    this.mCurrentProgress = 0;
    mCompletedFileCnt = 0;
    mCurrentFileCnt = 0;
    mFailedFileCnt = 0;
    this.mIndeterminate = true;
  }
  
  private void setNotification(int paramInt)
  {
    Log.e("SAGalleryNotificationController", "setNotification() mCompletedFileCnt = " + mCompletedFileCnt + " mFailedFileCnt = " + mFailedFileCnt + " mCurrentFileCnt = " + mCurrentFileCnt + "mMaxFileCnt = " + mMaxFileCnt);
    Object localObject1 = null;
    String str;
    label118:
    Intent localIntent;
    Object localObject2;
    switch (paramInt)
    {
    default: 
      if (mCompletedFileCnt == 0)
      {
        str = this.mContext.getString(R.string.n_failed, new Object[] { Integer.valueOf(mMaxFileCnt) });
        localIntent = new Intent();
        localObject2 = this.mContext.getPackageManager();
        if (localObject2 != null)
        {
          localObject2 = ((PackageManager)localObject2).getInstalledPackages(128);
          if (localObject2 != null)
          {
            paramInt = 0;
            label158:
            if (paramInt < ((List)localObject2).size())
            {
              PackageInfo localPackageInfo = (PackageInfo)((List)localObject2).get(paramInt);
              if ((localPackageInfo == null) || (!"com.sec.android.gallery3d".equals(localPackageInfo.packageName))) {
                break label468;
              }
              localIntent.setClassName("com.sec.android.gallery3d", "com.sec.android.gallery3d.app.Gallery");
              Log.v("SAGalleryNotificationController", "set Samsung Gallery");
            }
          }
        }
        localIntent.setAction("android.intent.action.VIEW");
        localIntent.setFlags(67108864);
        if (!SAGalleryAppFeatures.isSamsungDevice()) {
          break label499;
        }
        if (this.mLastFilePathName == null) {
          break label487;
        }
        localObject2 = getMediaUri(this.mContext, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.mLastFilePathName);
        if (localObject2 == null) {
          break label475;
        }
        localIntent.setDataAndType((Uri)localObject2, "image/*");
      }
      break;
    }
    for (;;)
    {
      this.mLastFilePathName = null;
      localObject2 = new Notification.Builder(this.mContext);
      ((Notification.Builder)localObject2).setContentTitle((CharSequence)localObject1).setContentText(str).setSmallIcon(R.drawable.indicator_gallery).setOngoing(false).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this.mContext, 0, localIntent, 268435456));
      if (this.mNotificationManager != null) {
        this.mNotificationManager.notify(NOTIFICATION_IN_COMPLETE, ((Notification.Builder)localObject2).build());
      }
      return;
      localObject1 = this.sendMobile;
      break;
      if (mCompletedFileCnt == mMaxFileCnt)
      {
        if (mCompletedFileCnt == 1)
        {
          str = this.mContext.getString(R.string.one_photo_transferred);
          break label118;
        }
        str = this.mContext.getString(R.string.successful_photos_transferred, new Object[] { Integer.valueOf(mCompletedFileCnt) });
        break label118;
      }
      str = this.mContext.getString(R.string.successful_fail_photos_transferred, new Object[] { Integer.valueOf(mCompletedFileCnt), Integer.valueOf(mMaxFileCnt) });
      break label118;
      label468:
      paramInt += 1;
      break label158;
      label475:
      localIntent.setType("image/*");
      continue;
      label487:
      localIntent.setType("image/*");
      continue;
      label499:
      if (this.mLastFilePathName != null) {
        localIntent.setDataAndType(Uri.parse("file://" + this.mLastFilePathName), "image/*");
      } else {
        localIntent.setType("image/*");
      }
    }
  }
  
  private void setProgress(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    try
    {
      this.mBuilder.setProgress(paramInt1, paramInt2, paramBoolean);
      if (paramInt1 > 0) {
        setContent(paramInt2);
      }
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      localNullPointerException.printStackTrace();
    }
  }
  
  private void updateOngoingNotification()
  {
    Log.v("SAGalleryNotificationController", "updateOngoingNotification");
    try
    {
      this.mNotificationManager.notify(NOTIFICATION_ID_ONGOING, this.mBuilder.build());
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      localNullPointerException.printStackTrace();
    }
  }
  
  public boolean isTransferDone()
  {
    return mMaxFileCnt == mCurrentFileCnt;
  }
  
  public void onCancel()
  {
    Log.v("SAGalleryNotificationController", "onCancel()");
    if (this.mNotificationManager != null)
    {
      this.mNotificationManager.cancel(NOTIFICATION_ID_ONGOING);
      if (!this.mIndeterminate) {
        setNotification(2);
      }
    }
  }
  
  public void onInit()
  {
    Log.v("SAGalleryNotificationController", "onInit()");
    if (this.mNotificationManager != null) {
      this.mNotificationManager.cancel(NOTIFICATION_ID_ONGOING);
    }
    this.mBuilder.setSmallIcon(R.drawable.indicator_gallery);
    this.mMaxProgress = 0;
    this.mCurrentProgress = 0;
    mMaxFileCnt = 0;
    mCompletedFileCnt = 0;
    mCurrentFileCnt = 0;
    mFailedFileCnt = 0;
    this.mIndeterminate = true;
  }
  
  public void onOneFileCompleted(String paramString)
  {
    Log.v("SAGalleryNotificationController", "onOneFileCompleted()");
    mCompletedFileCnt += 1;
    mCurrentFileCnt += 1;
    this.mLastFilePathName = paramString;
    updateProgress(0);
  }
  
  public void onOneFileFailed()
  {
    Log.v("SAGalleryNotificationController", "onOneFileFailed()");
    mFailedFileCnt += 1;
    mCurrentFileCnt += 1;
  }
  
  public void transferDone(int paramInt)
  {
    this.sendMobile = (this.mConnectedDeviceName + " : " + this.mContext.getString(R.string.transfer_to_mobile));
    setContent(this.mMaxProgress);
    setNotification(paramInt);
    this.mIndeterminate = true;
    this.mBuilder.setWhen(System.currentTimeMillis());
    this.mBuilder.setContentTitle(this.sendMobile);
    try
    {
      this.mNotificationManager.cancel(NOTIFICATION_ID_ONGOING);
      Log.v("SAGalleryNotificationController", "onCompleted() Cancel noti");
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      localNullPointerException.printStackTrace();
    }
  }
  
  public void transferStart(int paramInt)
  {
    Log.v("SAGalleryNotificationController", "transferStart");
    this.mConnectedDeviceName = SAGalleryAppFeatures.getConntectedDeviceName(this.mContext);
    this.sendMobile = this.mContext.getResources().getString(R.string.receiving_from, new Object[] { this.mConnectedDeviceName });
    try
    {
      this.mNotificationManager.cancel(NOTIFICATION_ID_ONGOING);
      this.mNotificationManager.cancel(NOTIFICATION_IN_COMPLETE);
      setFileCount(paramInt);
      setProgress(this.mMaxProgress, 0, true);
      this.mBuilder.setWhen(System.currentTimeMillis());
      this.mBuilder.setContentTitle(this.sendMobile);
      this.mBuilder.setSmallIcon(R.drawable.stat_sys_download_anim);
      updateOngoingNotification();
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;)
      {
        localNullPointerException.printStackTrace();
      }
    }
  }
  
  public void updateMaxFileCount(int paramInt)
  {
    mMaxFileCnt += paramInt;
    this.mMaxProgress = (mMaxFileCnt * 100);
    setProgress(this.mMaxProgress, this.mCurrentProgress, false);
    this.mBuilder.setWhen(System.currentTimeMillis());
    updateOngoingNotification();
  }
  
  public void updateProgress(int paramInt)
  {
    int i = mCurrentFileCnt * 100;
    this.mCurrentProgress = (i + paramInt);
    Log.v("SAGalleryNotificationController", "max[" + this.mMaxProgress + "], current[" + mCurrentFileCnt + "], addProgress[" + paramInt + "], total[" + this.mCurrentProgress + "]");
    this.mIndeterminate = false;
    if (i < this.mMaxProgress) {
      setProgress(this.mMaxProgress, this.mCurrentProgress, false);
    }
    for (;;)
    {
      updateOngoingNotification();
      return;
      if (i > this.mMaxProgress)
      {
        Log.d("SAGalleryNotificationController", "updateProgress: This should not be happened.");
        this.mMaxProgress += 100;
        setProgress(this.mMaxProgress, this.mCurrentProgress, false);
      }
    }
  }
}
