package nono.camera.h;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader.ForceLoadContentObserver;
import android.text.TextUtils;
import carbon.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import nono.camera.f.al;
import nono.camera.i.q;
import nono.camera.i.r;
import nono.camera.i.t;
import nono.camera.provider.a;

public final class u
  extends AsyncTaskLoader
{
  private Loader.ForceLoadContentObserver a = new Loader.ForceLoadContentObserver(this);
  private q b = null;
  private Object c;
  private List d;
  
  public u(Context paramContext, Object paramObject)
  {
    super(paramContext);
    this.c = paramObject;
    this.d = null;
  }
  
  private List a(Cursor paramCursor)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = b().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PackageInfo)localIterator.next();
      if ((!TextUtils.isEmpty(((PackageInfo)localObject).packageName)) && (((PackageInfo)localObject).packageName.startsWith("fonteee.typography.quotes.text.swag.stickers.")))
      {
        String str = ((PackageInfo)localObject).packageName.substring(45);
        boolean bool = a(paramCursor, str);
        String.format("pkg: %s, should show: %b", new Object[] { str, Boolean.valueOf(bool) });
        if (bool)
        {
          v localV = new v((byte)0);
          localV.a = str;
          localV.b = b.b(getContext(), ((PackageInfo)localObject).packageName, "package_title");
          localObject = b.c(getContext(), ((PackageInfo)localObject).packageName, "ic_launcher");
          if ((localObject instanceof BitmapDrawable))
          {
            localObject = ((BitmapDrawable)localObject).getBitmap();
            int i = getContext().getResources().getDimensionPixelSize(2131361993);
            localV.c = b.a(i, i, (Bitmap)localObject);
          }
          localArrayList.add(localV);
        }
      }
    }
    return localArrayList;
  }
  
  private q a()
  {
    q localQ = new q();
    for (;;)
    {
      Cursor localCursor;
      Object localObject1;
      Object localObject2;
      Object localObject3;
      try
      {
        localQ.a = getContext().getContentResolver().query(a.b, a.h, null, null, String.format("%s DESC, %s DESC", new Object[] { "type", "update_time" }));
        if (localQ.a != null)
        {
          localQ.a.getCount();
          localQ.a.registerContentObserver(this.a);
          localCursor = localQ.a;
          if (localCursor == null)
          {
            localArrayList = new ArrayList();
            localQ.b = localArrayList;
            return localQ;
          }
          ArrayList localArrayList = new ArrayList();
          localObject1 = a(localCursor).iterator();
          if (((Iterator)localObject1).hasNext())
          {
            localObject2 = (v)((Iterator)localObject1).next();
            localObject3 = new nono.camera.i.u();
            ((nono.camera.i.u)localObject3).b = ((v)localObject2).a;
            ((nono.camera.i.u)localObject3).a = ((v)localObject2).b;
            ((nono.camera.i.u)localObject3).c = false;
            ((nono.camera.i.u)localObject3).d = false;
            localArrayList.add(localObject3);
            localObject3 = new r();
            ((r)localObject3).b = ((v)localObject2).a;
            ((r)localObject3).a = ((v)localObject2).c;
            localArrayList.add(localObject3);
            continue;
          }
          localCursor.moveToFirst();
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        localQ.a = null;
        localQ.b = new ArrayList();
        return localQ;
      }
      while (!localCursor.isAfterLast())
      {
        localObject1 = localCursor.getString(1);
        localObject3 = localCursor.getString(2);
        localObject2 = b.u(localCursor.getString(4));
        if (localCursor.getInt(6) != 0)
        {
          localCursor.moveToNext();
        }
        else
        {
          boolean bool = a((String)localObject1);
          nono.camera.i.u localU = new nono.camera.i.u();
          localU.b = ((String)localObject1);
          localU.a = ((String)localObject3);
          localU.c = true;
          localU.d = bool;
          localException.add(localU);
          if (bool)
          {
            int i = 0;
            while (i < localObject2.length)
            {
              localObject3 = new t();
              ((t)localObject3).b = ((String)localObject1);
              ((t)localObject3).a = new String[4];
              int j = 0;
              while ((j < 4) && (i < localObject2.length))
              {
                ((t)localObject3).a[j] = localObject2[i];
                j += 1;
                i += 1;
              }
              localException.add(localObject3);
            }
          }
          localObject3 = new t();
          ((t)localObject3).b = ((String)localObject1);
          ((t)localObject3).a = ((String[])localObject2);
          localException.add(localObject3);
          localCursor.moveToNext();
          continue;
          localQ.b = new ArrayList();
          return localQ;
        }
      }
    }
  }
  
  private void a(q paramQ)
  {
    if (isReset()) {
      if ((paramQ != null) && (paramQ.a != null) && (!paramQ.a.isClosed())) {
        paramQ.a.close();
      }
    }
    q localQ;
    do
    {
      return;
      localQ = this.b;
      this.b = paramQ;
      if (isStarted()) {
        super.deliverResult(paramQ);
      }
    } while ((localQ == null) || (paramQ == null) || (localQ.a == null) || (localQ.a == paramQ.a) || (localQ.a.isClosed()));
    localQ.a.close();
  }
  
  private static boolean a(Cursor paramCursor, String paramString)
  {
    if (paramCursor == null) {}
    while (TextUtils.isEmpty(paramString)) {
      return false;
    }
    paramCursor.moveToFirst();
    label20:
    int i;
    if (!paramCursor.isAfterLast())
    {
      String str = paramCursor.getString(1);
      i = paramCursor.getInt(6);
      if (paramString.contentEquals(str)) {
        if (i != 0) {
          i = 1;
        }
      }
    }
    label62:
    for (int j = 1;; j = 0)
    {
      if (j != 0)
      {
        if (i == 0) {
          break;
        }
        return false;
        i = 0;
        break label62;
        paramCursor.moveToNext();
        break label20;
      }
      return true;
      i = 0;
    }
  }
  
  private boolean a(String paramString)
  {
    if ((this.d == null) || (this.d.size() == 0)) {}
    while (TextUtils.isEmpty(paramString)) {
      return false;
    }
    Iterator localIterator = this.d.iterator();
    boolean bool = false;
    if (localIterator.hasNext())
    {
      if (!((String)localIterator.next()).contentEquals(paramString)) {
        break label72;
      }
      bool = true;
    }
    label72:
    for (;;)
    {
      break;
      return bool;
    }
  }
  
  /* Error */
  private List b()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 108	nono/camera/h/u:getContext	()Landroid/content/Context;
    //   4: invokevirtual 274	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: astore_3
    //   8: aload_3
    //   9: iconst_0
    //   10: invokevirtual 280	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   13: astore_1
    //   14: aload_1
    //   15: areturn
    //   16: astore_1
    //   17: aload_1
    //   18: invokevirtual 218	java/lang/Exception:printStackTrace	()V
    //   21: new 34	java/util/ArrayList
    //   24: dup
    //   25: invokespecial 37	java/util/ArrayList:<init>	()V
    //   28: astore 4
    //   30: invokestatic 286	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   33: ldc_w 288
    //   36: invokevirtual 292	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   39: astore 5
    //   41: new 294	java/io/BufferedReader
    //   44: dup
    //   45: new 296	java/io/InputStreamReader
    //   48: dup
    //   49: aload 5
    //   51: invokevirtual 302	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   54: invokespecial 305	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   57: invokespecial 308	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   60: astore_2
    //   61: aload_2
    //   62: astore_1
    //   63: aload_2
    //   64: invokevirtual 312	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   67: astore 6
    //   69: aload 6
    //   71: ifnull +82 -> 153
    //   74: aload_2
    //   75: astore_1
    //   76: aload 6
    //   78: aload 6
    //   80: bipush 58
    //   82: invokevirtual 315	java/lang/String:indexOf	(I)I
    //   85: iconst_1
    //   86: iadd
    //   87: invokevirtual 80	java/lang/String:substring	(I)Ljava/lang/String;
    //   90: astore 6
    //   92: aload_2
    //   93: astore_1
    //   94: aload 6
    //   96: ldc 70
    //   98: invokevirtual 76	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   101: ifeq -40 -> 61
    //   104: aload_2
    //   105: astore_1
    //   106: aload 4
    //   108: aload_3
    //   109: aload 6
    //   111: iconst_0
    //   112: invokevirtual 319	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   115: invokeinterface 151 2 0
    //   120: pop
    //   121: goto -60 -> 61
    //   124: astore_3
    //   125: aload_2
    //   126: astore_1
    //   127: aload_3
    //   128: invokevirtual 218	java/lang/Exception:printStackTrace	()V
    //   131: aload 4
    //   133: astore_1
    //   134: aload_2
    //   135: ifnull -121 -> 14
    //   138: aload_2
    //   139: invokevirtual 320	java/io/BufferedReader:close	()V
    //   142: aload 4
    //   144: areturn
    //   145: astore_1
    //   146: aload_1
    //   147: invokevirtual 321	java/io/IOException:printStackTrace	()V
    //   150: aload 4
    //   152: areturn
    //   153: aload_2
    //   154: astore_1
    //   155: aload 5
    //   157: invokevirtual 324	java/lang/Process:waitFor	()I
    //   160: pop
    //   161: aload_2
    //   162: invokevirtual 320	java/io/BufferedReader:close	()V
    //   165: aload 4
    //   167: areturn
    //   168: astore_1
    //   169: aload_1
    //   170: invokevirtual 321	java/io/IOException:printStackTrace	()V
    //   173: aload 4
    //   175: areturn
    //   176: astore_2
    //   177: aconst_null
    //   178: astore_1
    //   179: aload_1
    //   180: ifnull +7 -> 187
    //   183: aload_1
    //   184: invokevirtual 320	java/io/BufferedReader:close	()V
    //   187: aload_2
    //   188: athrow
    //   189: astore_1
    //   190: aload_1
    //   191: invokevirtual 321	java/io/IOException:printStackTrace	()V
    //   194: goto -7 -> 187
    //   197: astore_2
    //   198: goto -19 -> 179
    //   201: astore_3
    //   202: aconst_null
    //   203: astore_2
    //   204: goto -79 -> 125
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	207	0	this	u
    //   13	2	1	localList	List
    //   16	2	1	localException1	Exception
    //   62	72	1	localObject1	Object
    //   145	2	1	localIOException1	java.io.IOException
    //   154	1	1	localObject2	Object
    //   168	2	1	localIOException2	java.io.IOException
    //   178	6	1	localObject3	Object
    //   189	2	1	localIOException3	java.io.IOException
    //   60	102	2	localBufferedReader	java.io.BufferedReader
    //   176	12	2	localObject4	Object
    //   197	1	2	localObject5	Object
    //   203	1	2	localObject6	Object
    //   7	102	3	localPackageManager	android.content.pm.PackageManager
    //   124	4	3	localException2	Exception
    //   201	1	3	localException3	Exception
    //   28	146	4	localArrayList	ArrayList
    //   39	117	5	localProcess	Process
    //   67	43	6	str	String
    // Exception table:
    //   from	to	target	type
    //   8	14	16	java/lang/Exception
    //   63	69	124	java/lang/Exception
    //   76	92	124	java/lang/Exception
    //   94	104	124	java/lang/Exception
    //   106	121	124	java/lang/Exception
    //   155	161	124	java/lang/Exception
    //   138	142	145	java/io/IOException
    //   161	165	168	java/io/IOException
    //   30	61	176	finally
    //   183	187	189	java/io/IOException
    //   63	69	197	finally
    //   76	92	197	finally
    //   94	104	197	finally
    //   106	121	197	finally
    //   127	131	197	finally
    //   155	161	197	finally
    //   30	61	201	java/lang/Exception
  }
  
  protected final void onForceLoad()
  {
    super.onForceLoad();
    if ((this.c instanceof al))
    {
      this.d = ((al)this.c).a();
      return;
    }
    this.d = null;
  }
  
  protected final void onReset()
  {
    super.onReset();
    onStopLoading();
    if ((this.b != null) && (this.b.a != null) && (!this.b.a.isClosed())) {
      this.b.a.close();
    }
    this.b = null;
  }
  
  protected final void onStartLoading()
  {
    if (this.b != null) {
      a(this.b);
    }
    if ((takeContentChanged()) || (this.b == null)) {
      forceLoad();
    }
  }
  
  protected final void onStopLoading()
  {
    cancelLoad();
  }
}
