package com.trendmicro.tmmssuite.cleantool;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.trendmicro.tmmssuite.core.sys.b;
import com.trendmicro.tmmssuite.core.sys.c;
import java.io.File;
import java.util.List;
import org.json.JSONObject;

public abstract class CleanToolModuleBase
  implements CleanToolModule
{
  private d mCTMgr = d.a();
  private a mCleanTask;
  private Context mCtx = (Context)b.a(com.trendmicro.tmmssuite.core.app.a.a);
  private Bundle mEnvBundle;
  private boolean mIsScanCancelled = false;
  private e mScanTask;
  
  public CleanToolModuleBase() {}
  
  public final void cancelScan()
  {
    this.mIsScanCancelled = true;
  }
  
  public final void clean(Bundle paramBundle)
  {
    doClean(paramBundle);
  }
  
  protected final boolean deleteCacheFile(String paramString)
  {
    return getCacheFile(paramString).delete();
  }
  
  protected final boolean deleteFile(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = new File(paramString);
      if ((paramString.exists()) && (paramString.isFile())) {
        paramString.delete();
      }
      if (paramString.exists()) {
        return false;
      }
    }
    return true;
  }
  
  protected abstract void doClean(Bundle paramBundle);
  
  protected abstract void doScan(Bundle paramBundle);
  
  protected final File getAssetFile(String paramString)
  {
    paramString = getAssetPath(paramString);
    if (paramString != null) {
      return new File(paramString);
    }
    return null;
  }
  
  /* Error */
  protected final String getAssetPath(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: aload_0
    //   10: getfield 52	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:mCTMgr	Lcom/trendmicro/tmmssuite/cleantool/d;
    //   13: aload_0
    //   14: invokevirtual 99	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:getId	()I
    //   17: invokevirtual 103	com/trendmicro/tmmssuite/cleantool/d:f	(I)Ljava/io/File;
    //   20: astore_2
    //   21: aload_2
    //   22: ifnull -15 -> 7
    //   25: new 67	java/io/File
    //   28: dup
    //   29: aload_2
    //   30: aload_1
    //   31: invokespecial 106	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   34: astore 4
    //   36: new 108	java/util/jar/JarFile
    //   39: dup
    //   40: aload_0
    //   41: getfield 52	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:mCTMgr	Lcom/trendmicro/tmmssuite/cleantool/d;
    //   44: aload_0
    //   45: invokevirtual 99	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:getId	()I
    //   48: invokevirtual 112	com/trendmicro/tmmssuite/cleantool/d:e	(I)Ljava/lang/String;
    //   51: iconst_1
    //   52: invokespecial 115	java/util/jar/JarFile:<init>	(Ljava/lang/String;Z)V
    //   55: astore_2
    //   56: aload_2
    //   57: new 117	java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial 118	java/lang/StringBuilder:<init>	()V
    //   64: ldc 120
    //   66: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: getstatic 128	java/io/File:separator	Ljava/lang/String;
    //   72: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: aload_1
    //   76: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: invokevirtual 136	java/util/jar/JarFile:getJarEntry	(Ljava/lang/String;)Ljava/util/jar/JarEntry;
    //   85: astore_1
    //   86: aload_1
    //   87: ifnonnull +10 -> 97
    //   90: ldc -118
    //   92: invokestatic 143	com/trendmicro/tmmssuite/core/sys/c:b	(Ljava/lang/String;)V
    //   95: aconst_null
    //   96: areturn
    //   97: aload_2
    //   98: aload_1
    //   99: invokevirtual 147	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   102: astore_3
    //   103: new 149	java/io/FileOutputStream
    //   106: dup
    //   107: aload 4
    //   109: invokespecial 152	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   112: astore_2
    //   113: aload_3
    //   114: ifnull +34 -> 148
    //   117: aload_2
    //   118: ifnull +30 -> 148
    //   121: aload_3
    //   122: aload_2
    //   123: invokestatic 157	com/trendmicro/tmmssuite/core/util/k:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   126: aload_3
    //   127: ifnull +7 -> 134
    //   130: aload_3
    //   131: invokevirtual 162	java/io/InputStream:close	()V
    //   134: aload_2
    //   135: ifnull +7 -> 142
    //   138: aload_2
    //   139: invokevirtual 165	java/io/OutputStream:close	()V
    //   142: aload 4
    //   144: invokevirtual 168	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   147: areturn
    //   148: aload_3
    //   149: ifnull +7 -> 156
    //   152: aload_3
    //   153: invokevirtual 162	java/io/InputStream:close	()V
    //   156: aload_2
    //   157: ifnull -150 -> 7
    //   160: aload_2
    //   161: invokevirtual 165	java/io/OutputStream:close	()V
    //   164: aconst_null
    //   165: areturn
    //   166: astore_1
    //   167: aconst_null
    //   168: areturn
    //   169: astore_1
    //   170: aconst_null
    //   171: astore_3
    //   172: aconst_null
    //   173: astore_2
    //   174: aload_3
    //   175: ifnull +7 -> 182
    //   178: aload_3
    //   179: invokevirtual 162	java/io/InputStream:close	()V
    //   182: aload_2
    //   183: ifnull +7 -> 190
    //   186: aload_2
    //   187: invokevirtual 165	java/io/OutputStream:close	()V
    //   190: aload_1
    //   191: athrow
    //   192: astore_1
    //   193: goto -37 -> 156
    //   196: astore_1
    //   197: goto -63 -> 134
    //   200: astore_1
    //   201: goto -59 -> 142
    //   204: astore_3
    //   205: goto -23 -> 182
    //   208: astore_2
    //   209: goto -19 -> 190
    //   212: astore_1
    //   213: aconst_null
    //   214: astore_2
    //   215: goto -41 -> 174
    //   218: astore_1
    //   219: goto -45 -> 174
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	222	0	this	CleanToolModuleBase
    //   0	222	1	paramString	String
    //   20	167	2	localObject1	Object
    //   208	1	2	localIOException1	java.io.IOException
    //   214	1	2	localObject2	Object
    //   102	77	3	localInputStream	java.io.InputStream
    //   204	1	3	localIOException2	java.io.IOException
    //   34	109	4	localFile	File
    // Exception table:
    //   from	to	target	type
    //   160	164	166	java/io/IOException
    //   97	103	169	finally
    //   152	156	192	java/io/IOException
    //   130	134	196	java/io/IOException
    //   138	142	200	java/io/IOException
    //   178	182	204	java/io/IOException
    //   186	190	208	java/io/IOException
    //   103	113	212	finally
    //   121	126	218	finally
  }
  
  protected final File getCacheFile(String paramString)
  {
    return new File(this.mCtx.getCacheDir(), getId() + "_" + paramString);
  }
  
  protected final String getCachePath(String paramString)
  {
    return getCacheFile(paramString).getAbsolutePath();
  }
  
  protected final Context getContext()
  {
    return this.mCtx;
  }
  
  protected final DevicePolicyManager getDevicePolicyManager()
  {
    return (DevicePolicyManager)this.mCtx.getSystemService("device_policy");
  }
  
  protected final Bundle getEnvBundle()
  {
    return this.mEnvBundle;
  }
  
  protected final List<PackageInfo> getInstalledPackages()
  {
    return getInstalledPackages(0);
  }
  
  protected final List<PackageInfo> getInstalledPackages(int paramInt)
  {
    return getContext().getPackageManager().getInstalledPackages(paramInt);
  }
  
  /* Error */
  protected final File getLibFile(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_1
    //   3: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: ifeq +5 -> 11
    //   9: aconst_null
    //   10: areturn
    //   11: aload_0
    //   12: getfield 52	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:mCTMgr	Lcom/trendmicro/tmmssuite/cleantool/d;
    //   15: aload_0
    //   16: invokevirtual 99	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:getId	()I
    //   19: invokevirtual 215	com/trendmicro/tmmssuite/cleantool/d:g	(I)Ljava/io/File;
    //   22: astore_2
    //   23: aload_2
    //   24: ifnonnull +5 -> 29
    //   27: aconst_null
    //   28: areturn
    //   29: new 67	java/io/File
    //   32: dup
    //   33: aload_2
    //   34: aload_1
    //   35: invokespecial 106	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   38: astore 5
    //   40: new 108	java/util/jar/JarFile
    //   43: dup
    //   44: aload_0
    //   45: getfield 52	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:mCTMgr	Lcom/trendmicro/tmmssuite/cleantool/d;
    //   48: aload_0
    //   49: invokevirtual 99	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:getId	()I
    //   52: invokevirtual 112	com/trendmicro/tmmssuite/cleantool/d:e	(I)Ljava/lang/String;
    //   55: iconst_1
    //   56: invokespecial 115	java/util/jar/JarFile:<init>	(Ljava/lang/String;Z)V
    //   59: astore 6
    //   61: ldc -39
    //   63: astore 4
    //   65: ldc -37
    //   67: invokestatic 224	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   70: astore 7
    //   72: aload 4
    //   74: astore_2
    //   75: aload 7
    //   77: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   80: ifne +30 -> 110
    //   83: aload 7
    //   85: ldc -30
    //   87: invokevirtual 232	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   90: ifeq +80 -> 170
    //   93: aload_0
    //   94: ldc -22
    //   96: invokevirtual 237	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:logi	(Ljava/lang/String;)V
    //   99: getstatic 243	android/os/Build$VERSION:SDK_INT	I
    //   102: bipush 9
    //   104: if_icmplt +58 -> 162
    //   107: ldc -30
    //   109: astore_2
    //   110: aload 6
    //   112: new 117	java/lang/StringBuilder
    //   115: dup
    //   116: invokespecial 118	java/lang/StringBuilder:<init>	()V
    //   119: ldc -11
    //   121: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: getstatic 128	java/io/File:separator	Ljava/lang/String;
    //   127: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: aload_2
    //   131: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: getstatic 128	java/io/File:separator	Ljava/lang/String;
    //   137: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload_1
    //   141: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: invokevirtual 136	java/util/jar/JarFile:getJarEntry	(Ljava/lang/String;)Ljava/util/jar/JarEntry;
    //   150: astore_1
    //   151: aload_1
    //   152: ifnonnull +71 -> 223
    //   155: ldc -9
    //   157: invokestatic 143	com/trendmicro/tmmssuite/core/sys/c:b	(Ljava/lang/String;)V
    //   160: aconst_null
    //   161: areturn
    //   162: aload_0
    //   163: ldc -7
    //   165: invokevirtual 252	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:logw	(Ljava/lang/String;)V
    //   168: aconst_null
    //   169: areturn
    //   170: aload 7
    //   172: ldc -2
    //   174: invokevirtual 232	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   177: ifeq +33 -> 210
    //   180: aload_0
    //   181: ldc_w 256
    //   184: invokevirtual 237	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:logi	(Ljava/lang/String;)V
    //   187: getstatic 243	android/os/Build$VERSION:SDK_INT	I
    //   190: bipush 9
    //   192: if_icmplt +9 -> 201
    //   195: ldc -2
    //   197: astore_2
    //   198: goto -88 -> 110
    //   201: aload_0
    //   202: ldc_w 258
    //   205: invokevirtual 252	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:logw	(Ljava/lang/String;)V
    //   208: aconst_null
    //   209: areturn
    //   210: aload_0
    //   211: ldc_w 260
    //   214: invokevirtual 237	com/trendmicro/tmmssuite/cleantool/CleanToolModuleBase:logi	(Ljava/lang/String;)V
    //   217: aload 4
    //   219: astore_2
    //   220: goto -110 -> 110
    //   223: aload 6
    //   225: aload_1
    //   226: invokevirtual 147	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   229: astore_1
    //   230: new 149	java/io/FileOutputStream
    //   233: dup
    //   234: aload 5
    //   236: invokespecial 152	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   239: astore_2
    //   240: aload_1
    //   241: ifnull +12 -> 253
    //   244: aload_2
    //   245: ifnull +8 -> 253
    //   248: aload_1
    //   249: aload_2
    //   250: invokestatic 157	com/trendmicro/tmmssuite/core/util/k:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   253: aload_1
    //   254: ifnull +7 -> 261
    //   257: aload_1
    //   258: invokevirtual 162	java/io/InputStream:close	()V
    //   261: aload_2
    //   262: ifnull +7 -> 269
    //   265: aload_2
    //   266: invokevirtual 165	java/io/OutputStream:close	()V
    //   269: aload 5
    //   271: areturn
    //   272: astore_1
    //   273: aload_1
    //   274: invokevirtual 263	java/io/IOException:printStackTrace	()V
    //   277: goto -16 -> 261
    //   280: astore_1
    //   281: aload_1
    //   282: invokevirtual 263	java/io/IOException:printStackTrace	()V
    //   285: goto -16 -> 269
    //   288: astore_1
    //   289: aconst_null
    //   290: astore_2
    //   291: aload_2
    //   292: ifnull +7 -> 299
    //   295: aload_2
    //   296: invokevirtual 162	java/io/InputStream:close	()V
    //   299: aload_3
    //   300: ifnull +7 -> 307
    //   303: aload_3
    //   304: invokevirtual 165	java/io/OutputStream:close	()V
    //   307: aload_1
    //   308: athrow
    //   309: astore_2
    //   310: aload_2
    //   311: invokevirtual 263	java/io/IOException:printStackTrace	()V
    //   314: goto -15 -> 299
    //   317: astore_2
    //   318: aload_2
    //   319: invokevirtual 263	java/io/IOException:printStackTrace	()V
    //   322: goto -15 -> 307
    //   325: astore 4
    //   327: aload_1
    //   328: astore_2
    //   329: aload 4
    //   331: astore_1
    //   332: goto -41 -> 291
    //   335: astore 4
    //   337: aload_2
    //   338: astore_3
    //   339: aload_1
    //   340: astore_2
    //   341: aload 4
    //   343: astore_1
    //   344: goto -53 -> 291
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	347	0	this	CleanToolModuleBase
    //   0	347	1	paramString	String
    //   22	274	2	localObject1	Object
    //   309	2	2	localIOException1	java.io.IOException
    //   317	2	2	localIOException2	java.io.IOException
    //   328	13	2	str1	String
    //   1	338	3	localObject2	Object
    //   63	155	4	str2	String
    //   325	5	4	localObject3	Object
    //   335	7	4	localObject4	Object
    //   38	232	5	localFile	File
    //   59	165	6	localJarFile	java.util.jar.JarFile
    //   70	101	7	str3	String
    // Exception table:
    //   from	to	target	type
    //   257	261	272	java/io/IOException
    //   265	269	280	java/io/IOException
    //   223	230	288	finally
    //   295	299	309	java/io/IOException
    //   303	307	317	java/io/IOException
    //   230	240	325	finally
    //   248	253	335	finally
  }
  
  protected final String getLibPath(String paramString)
  {
    paramString = getLibFile(paramString);
    if (paramString != null) {
      return paramString.getAbsolutePath();
    }
    return null;
  }
  
  public final String getName()
  {
    JSONObject localJSONObject = this.mCTMgr.b(getId());
    if (localJSONObject != null) {
      return localJSONObject.optString("name");
    }
    return "";
  }
  
  protected final PackageManager getPackageManager()
  {
    return this.mCtx.getPackageManager();
  }
  
  protected final boolean getRoot()
  {
    try
    {
      boolean bool = h.a();
      return bool;
    }
    catch (h.c localC)
    {
      throw new PermissionDeniedException();
    }
  }
  
  public final int getVersion()
  {
    JSONObject localJSONObject = this.mCTMgr.b(getId());
    if (localJSONObject != null) {
      return localJSONObject.optInt("version");
    }
    return 0;
  }
  
  protected final boolean isFilePathString(String paramString)
  {
    return (paramString != null) && (paramString.contains(File.separator));
  }
  
  protected final boolean isScanCancelled()
  {
    return this.mIsScanCancelled;
  }
  
  protected final void log(String paramString)
  {
    logd(paramString);
  }
  
  protected final void logd(String paramString)
  {
    c.c(paramString);
  }
  
  protected final void loge(String paramString)
  {
    c.b(paramString);
  }
  
  protected final void logi(String paramString)
  {
    c.a(paramString);
  }
  
  protected final void logv(String paramString)
  {
    c.d(paramString);
  }
  
  protected final void logw(String paramString)
  {
    c.e(paramString);
  }
  
  protected final void publishCleanProgress(Bundle paramBundle) {}
  
  protected final void publishScanProgress(Bundle paramBundle) {}
  
  protected final CommandsExecResult runCommands(String[] paramArrayOfString)
  {
    return runCommands(paramArrayOfString, true);
  }
  
  protected final CommandsExecResult runCommands(String[] paramArrayOfString, boolean paramBoolean)
  {
    paramArrayOfString = h.a(paramArrayOfString, paramBoolean);
    CommandsExecResult localCommandsExecResult = new CommandsExecResult();
    localCommandsExecResult.error = paramArrayOfString.b;
    localCommandsExecResult.exception = paramArrayOfString.c;
    localCommandsExecResult.output = paramArrayOfString.a;
    return localCommandsExecResult;
  }
  
  public final void scan(Bundle paramBundle)
  {
    this.mIsScanCancelled = false;
    doScan(paramBundle);
  }
  
  public final void setCleanTask(a paramA)
  {
    if (paramA == null) {
      throw new IllegalArgumentException("clean tool clean task reference must be set.");
    }
    this.mCleanTask = paramA;
  }
  
  public final void setEnv(Bundle paramBundle)
  {
    if (paramBundle == null) {
      throw new IllegalArgumentException("clean tool scan environment must be set.");
    }
    this.mEnvBundle = paramBundle;
  }
  
  public final void setScanTask(e paramE)
  {
    if (paramE == null) {
      throw new IllegalArgumentException("clean tool scan task reference must be set.");
    }
    this.mScanTask = paramE;
  }
  
  protected static final class CommandsExecResult
  {
    public String error;
    public Exception exception;
    public String output;
    
    protected CommandsExecResult() {}
  }
  
  protected static final class PermissionDeniedException
    extends h.c
  {
    private static final long serialVersionUID = 1L;
    
    protected PermissionDeniedException() {}
  }
}
