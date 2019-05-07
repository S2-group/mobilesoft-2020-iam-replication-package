package com.maxmpz.audioplayer.preference;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;
import com.maxmpz.audioplayer.Application;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class ThemePreference
  extends DialogPreference
{
  public ThemePreference(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ThemePreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public static void 𐀀(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    if (new GregorianCalendar().after(new GregorianCalendar(2014, 1, 15))) {}
    for (String str = "com.maxmpz.audioplayer.skin";; str = "poweramp skin OR com.maxmpz.audioplayer.skin")
    {
      localIntent.setData(Uri.parse("market://search?q=" + Uri.encode(str)));
      try
      {
        paramContext.startActivity(localIntent);
        return;
      }
      catch (Exception localException)
      {
        Log.e("ThemePreference", "", localException);
        Toast.makeText(paramContext, "Failed to open Google Play app", 1).show();
      }
    }
  }
  
  private void 𐀀(Resources paramResources, ArrayList paramArrayList, String paramString1, File paramFile, String paramString2, String paramString3)
  {
    Object localObject6 = null;
    Object localObject5 = null;
    localObject1 = null;
    TypedArray localTypedArray = null;
    localObject4 = null;
    localObject3 = localObject1;
    Object localObject2 = localObject6;
    for (;;)
    {
      try
      {
        i = paramResources.getIdentifier("poweramp_skins", "array", paramString3);
        localObject3 = localObject1;
        localObject2 = localObject6;
        Log.w("ThemePreference", "skinsId=0x" + Integer.toHexString(i) + " pak=" + paramString3);
        if (i == 0)
        {
          localObject3 = localObject1;
          localObject2 = localObject6;
          Log.e("ThemePreference", "Can't find poweramp_skins array in skin=" + paramString3 + "/" + paramFile + ". Skin requires rebuild/update");
          localObject3 = localObject1;
          localObject2 = localObject6;
          paramResources = new 𐀀();
          localObject3 = localObject1;
          localObject2 = localObject6;
          paramResources.𐐁 = 0;
          localObject3 = localObject1;
          localObject2 = localObject6;
          paramResources.𐐂 = getContext().getString(2131231622);
          localObject3 = localObject1;
          localObject2 = localObject6;
          paramResources.𐀀 = paramFile.getAbsolutePath();
          localObject3 = localObject1;
          localObject2 = localObject6;
          paramResources.𐰄 = paramString2;
          localObject3 = localObject1;
          localObject2 = localObject6;
          paramResources.llll = true;
          localObject3 = localObject1;
          localObject2 = localObject6;
          paramArrayList.add(paramResources);
          if (0 != 0) {
            throw new NullPointerException();
          }
          if (0 != 0) {
            throw new NullPointerException();
          }
          if (0 != 0)
          {
            localObject1 = localTypedArray;
            ((TypedArray)localObject1).recycle();
          }
          return;
        }
        localObject3 = localObject1;
        localObject2 = localObject6;
        localTypedArray = paramResources.obtainTypedArray(i);
        if (localTypedArray == null) {
          continue;
        }
        localObject3 = localObject1;
        localObject2 = localTypedArray;
        if (localTypedArray.length() <= 0) {
          continue;
        }
        localObject3 = localObject1;
        localObject2 = localTypedArray;
        localObject1 = paramResources.obtainTypedArray(paramResources.getIdentifier("poweramp_skin_names", "array", paramString3));
        if (localObject1 != null)
        {
          localObject3 = localObject1;
          localObject2 = localTypedArray;
          if (((TypedArray)localObject1).length() >= localTypedArray.length()) {}
        }
        else
        {
          localObject3 = localObject1;
          localObject2 = localTypedArray;
          Log.e("ThemePreference", "No or invalid names array for skins in " + paramString1);
          if (localTypedArray != null) {
            localTypedArray.recycle();
          }
          if (0 != 0) {
            throw new NullPointerException();
          }
          if (localObject1 == null) {
            continue;
          }
          continue;
        }
        localObject3 = localObject1;
        localObject2 = localTypedArray;
      }
      finally
      {
        paramArrayList = (ArrayList)localObject4;
        localObject1 = localObject3;
        continue;
        int i = 0;
        continue;
      }
      try
      {
        paramString3 = paramResources.obtainTypedArray(paramResources.getIdentifier("poweramp_skin_authors", "array", paramString3));
        localObject2 = paramString3;
      }
      catch (Exception paramString3)
      {
        localObject3 = localObject1;
        localObject2 = localTypedArray;
        Log.e("ThemePreference", "", paramString3);
        localObject2 = null;
        continue;
        localObject3 = paramString3;
        ((𐀀)localObject4).ll1l = "...";
        continue;
      }
      if (localObject2 != null)
      {
        localObject3 = localObject2;
        paramString3 = (String)localObject2;
      }
      try
      {
        if (((TypedArray)localObject2).length() >= localTypedArray.length()) {
          continue;
        }
        localObject3 = localObject2;
        Log.e("ThemePreference", "No or invalid authors array for skins in " + paramString1);
        paramString3 = null;
      }
      finally
      {
        int j;
        paramArrayList = (ArrayList)localObject3;
        localObject2 = localTypedArray;
      }
      localObject3 = paramString3;
      localObject4 = paramString3;
      localObject2 = localObject1;
      if (i < localTypedArray.length())
      {
        localObject3 = paramString3;
        j = localTypedArray.getResourceId(i, 0);
        localObject3 = paramString3;
        localObject4 = paramResources.getResourceEntryName(j);
        if ((j != 0) && (localObject4 != null))
        {
          localObject3 = paramString3;
          localObject2 = ((TypedArray)localObject1).getString(i);
          localObject3 = paramString3;
          if (!paramFile.isFile()) {
            continue;
          }
          localObject3 = paramString3;
          if (!paramFile.canRead()) {
            continue;
          }
          localObject3 = paramString3;
          Log.w("ThemePreference", "found skin theme=" + (String)localObject4 + " name=" + (String)localObject2 + " id=0x" + Integer.toHexString(j) + " app=" + paramString1);
          localObject3 = paramString3;
          localObject4 = new 𐀀();
          localObject3 = paramString3;
          ((𐀀)localObject4).𐐁 = j;
          localObject3 = paramString3;
          ((𐀀)localObject4).𐐂 = ((String)localObject2);
          localObject3 = paramString3;
          ((𐀀)localObject4).𐀀 = paramFile.getAbsolutePath();
          localObject3 = paramString3;
          ((𐀀)localObject4).𐰄 = paramString2;
          if (paramString3 != null)
          {
            localObject3 = paramString3;
            ((𐀀)localObject4).ll1l = paramString3.getString(i);
            localObject3 = paramString3;
            paramArrayList.add(localObject4);
          }
        }
        else
        {
          i += 1;
          continue;
        }
        if (localObject2 != null) {
          ((TypedArray)localObject2).recycle();
        }
        if (paramArrayList != null) {
          paramArrayList.recycle();
        }
        if (localObject1 != null) {
          ((TypedArray)localObject1).recycle();
        }
        throw paramResources;
        localObject3 = paramString3;
        Log.e("ThemePreference", "file is not readable=" + paramFile);
        continue;
        localObject3 = localObject1;
        localObject2 = localTypedArray;
        Log.e("ThemePreference", "Got empty skins array for " + paramString1);
        localObject4 = null;
        localObject2 = localObject5;
      }
      else
      {
        if (localTypedArray != null) {
          localTypedArray.recycle();
        }
        if (localObject4 != null) {
          ((TypedArray)localObject4).recycle();
        }
        if (localObject2 != null) {
          localObject1 = localObject2;
        }
      }
    }
  }
  
  private ArrayList 𐐁()
  {
    localArrayList = new ArrayList(10);
    try
    {
      PackageManager localPackageManager = getContext().getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        Bundle localBundle = localApplicationInfo.metaData;
        if (localBundle != null) {
          if (!localBundle.containsKey("com.maxmpz.PowerAMPSkins"))
          {
            boolean bool = localBundle.containsKey("com.maxmpz.PowerampSkins");
            if (!bool) {}
          }
          else
          {
            try
            {
              𐀀(localPackageManager.getResourcesForApplication(localApplicationInfo), localArrayList, localApplicationInfo.packageName, new File(localApplicationInfo.publicSourceDir), localApplicationInfo.loadLabel(localPackageManager).toString(), localApplicationInfo.packageName);
            }
            catch (Exception localException2)
            {
              Log.e("ThemePreference", "Failed to get resources from app=" + localApplicationInfo.packageName, localException2);
            }
          }
        }
      }
      return localArrayList;
    }
    catch (Exception localException1)
    {
      Log.e("ThemePreference", "Failed to load skins from apks", localException1);
    }
  }
  
  /* Error */
  private ArrayList 𐐂()
  {
    // Byte code:
    //   0: new 170	java/util/ArrayList
    //   3: dup
    //   4: bipush 10
    //   6: invokespecial 233	java/util/ArrayList:<init>	(I)V
    //   9: astore 4
    //   11: getstatic 304	com/maxmpz/audioplayer/Application:G	Ljava/io/File;
    //   14: astore 6
    //   16: aload 6
    //   18: ifnull +233 -> 251
    //   21: aload 6
    //   23: invokevirtual 212	java/io/File:canRead	()Z
    //   26: ifeq +225 -> 251
    //   29: aload 6
    //   31: invokevirtual 307	java/io/File:isDirectory	()Z
    //   34: ifeq +217 -> 251
    //   37: aload 6
    //   39: new 6	com/maxmpz/audioplayer/preference/ThemePreference$1
    //   42: dup
    //   43: aload_0
    //   44: invokespecial 310	com/maxmpz/audioplayer/preference/ThemePreference$1:<init>	(Lcom/maxmpz/audioplayer/preference/ThemePreference;)V
    //   47: invokevirtual 314	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
    //   50: astore 5
    //   52: aload 5
    //   54: ifnull +405 -> 459
    //   57: aload 5
    //   59: arraylength
    //   60: istore_2
    //   61: iconst_0
    //   62: istore_1
    //   63: iload_1
    //   64: iload_2
    //   65: if_icmpge +186 -> 251
    //   68: aload 5
    //   70: iload_1
    //   71: aaload
    //   72: astore 7
    //   74: aload 7
    //   76: invokevirtual 212	java/io/File:canRead	()Z
    //   79: ifeq +315 -> 394
    //   82: aload 7
    //   84: invokevirtual 209	java/io/File:isFile	()Z
    //   87: ifeq +307 -> 394
    //   90: ldc_w 316
    //   93: iconst_0
    //   94: anewarray 318	java/lang/Class
    //   97: invokevirtual 322	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   100: iconst_0
    //   101: anewarray 291	java/lang/Object
    //   104: invokevirtual 328	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   107: checkcast 316	android/content/res/AssetManager
    //   110: astore 6
    //   112: aload 6
    //   114: invokevirtual 332	java/lang/Object:getClass	()Ljava/lang/Class;
    //   117: ldc_w 334
    //   120: iconst_1
    //   121: anewarray 318	java/lang/Class
    //   124: dup
    //   125: iconst_0
    //   126: ldc_w 336
    //   129: aastore
    //   130: invokevirtual 340	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   133: aload 6
    //   135: iconst_1
    //   136: anewarray 291	java/lang/Object
    //   139: dup
    //   140: iconst_0
    //   141: aload 7
    //   143: invokevirtual 343	java/io/File:getPath	()Ljava/lang/String;
    //   146: aastore
    //   147: invokevirtual 349	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   150: checkcast 116	java/lang/Integer
    //   153: invokevirtual 352	java/lang/Integer:intValue	()I
    //   156: istore_3
    //   157: iload_3
    //   158: ifeq +231 -> 389
    //   161: aload_0
    //   162: invokevirtual 146	com/maxmpz/audioplayer/preference/ThemePreference:getContext	()Landroid/content/Context;
    //   165: invokevirtual 356	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   168: astore 8
    //   170: new 108	android/content/res/Resources
    //   173: dup
    //   174: aload 6
    //   176: aload 8
    //   178: invokevirtual 360	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   181: aload 8
    //   183: invokevirtual 364	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   186: invokespecial 367	android/content/res/Resources:<init>	(Landroid/content/res/AssetManager;Landroid/util/DisplayMetrics;Landroid/content/res/Configuration;)V
    //   189: astore 9
    //   191: aload 6
    //   193: iload_3
    //   194: ldc_w 369
    //   197: invokevirtual 373	android/content/res/AssetManager:openXmlResourceParser	(ILjava/lang/String;)Landroid/content/res/XmlResourceParser;
    //   200: astore 8
    //   202: aload 8
    //   204: ifnull +185 -> 389
    //   207: aload 8
    //   209: invokeinterface 377 1 0
    //   214: istore_3
    //   215: iload_3
    //   216: iconst_2
    //   217: if_icmpeq +8 -> 225
    //   220: iload_3
    //   221: iconst_1
    //   222: if_icmpne -15 -> 207
    //   225: iload_3
    //   226: iconst_2
    //   227: if_icmpeq +27 -> 254
    //   230: ldc 82
    //   232: ldc_w 379
    //   235: invokestatic 137	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   238: pop
    //   239: aload 8
    //   241: invokeinterface 382 1 0
    //   246: aload 6
    //   248: invokevirtual 383	android/content/res/AssetManager:close	()V
    //   251: aload 4
    //   253: areturn
    //   254: aload 8
    //   256: invokeinterface 386 1 0
    //   261: ldc_w 388
    //   264: invokevirtual 391	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   267: ifne +43 -> 310
    //   270: ldc 82
    //   272: ldc_w 393
    //   275: invokestatic 137	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   278: pop
    //   279: aload 8
    //   281: invokeinterface 382 1 0
    //   286: aload 6
    //   288: invokevirtual 383	android/content/res/AssetManager:close	()V
    //   291: aload 4
    //   293: areturn
    //   294: astore 5
    //   296: ldc 82
    //   298: ldc_w 395
    //   301: aload 5
    //   303: invokestatic 90	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   306: pop
    //   307: aload 4
    //   309: areturn
    //   310: aload 8
    //   312: aconst_null
    //   313: ldc_w 397
    //   316: invokeinterface 401 3 0
    //   321: astore 10
    //   323: aload 10
    //   325: ifnull +11 -> 336
    //   328: aload 10
    //   330: invokevirtual 402	java/lang/String:length	()I
    //   333: ifne +27 -> 360
    //   336: ldc 82
    //   338: ldc_w 404
    //   341: invokestatic 137	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   344: pop
    //   345: aload 8
    //   347: invokeinterface 382 1 0
    //   352: aload 6
    //   354: invokevirtual 383	android/content/res/AssetManager:close	()V
    //   357: aload 4
    //   359: areturn
    //   360: aload_0
    //   361: aload 9
    //   363: aload 4
    //   365: aload 7
    //   367: invokevirtual 343	java/io/File:getPath	()Ljava/lang/String;
    //   370: aload 7
    //   372: aload 7
    //   374: invokevirtual 405	java/io/File:getName	()Ljava/lang/String;
    //   377: aload 10
    //   379: invokespecial 294	com/maxmpz/audioplayer/preference/ThemePreference:𐀀	(Landroid/content/res/Resources;Ljava/util/ArrayList;Ljava/lang/String;Ljava/io/File;Ljava/lang/String;Ljava/lang/String;)V
    //   382: aload 8
    //   384: invokeinterface 382 1 0
    //   389: aload 6
    //   391: invokevirtual 383	android/content/res/AssetManager:close	()V
    //   394: iload_1
    //   395: iconst_1
    //   396: iadd
    //   397: istore_1
    //   398: goto -335 -> 63
    //   401: astore 9
    //   403: aload 8
    //   405: invokeinterface 382 1 0
    //   410: aload 9
    //   412: athrow
    //   413: astore 8
    //   415: ldc 82
    //   417: new 47	java/lang/StringBuilder
    //   420: dup
    //   421: ldc_w 407
    //   424: invokespecial 50	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   427: aload 7
    //   429: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   432: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   435: aload 8
    //   437: invokestatic 90	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   440: pop
    //   441: aload 6
    //   443: invokevirtual 383	android/content/res/AssetManager:close	()V
    //   446: goto -52 -> 394
    //   449: astore 5
    //   451: aload 6
    //   453: invokevirtual 383	android/content/res/AssetManager:close	()V
    //   456: aload 5
    //   458: athrow
    //   459: ldc 82
    //   461: new 47	java/lang/StringBuilder
    //   464: dup
    //   465: ldc_w 409
    //   468: invokespecial 50	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   471: aload 6
    //   473: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   476: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   479: invokestatic 137	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   482: pop
    //   483: aload 4
    //   485: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	486	0	this	ThemePreference
    //   62	336	1	i	int
    //   60	6	2	j	int
    //   156	72	3	k	int
    //   9	475	4	localArrayList	ArrayList
    //   50	19	5	arrayOfFile	File[]
    //   294	8	5	localException1	Exception
    //   449	8	5	localObject1	Object
    //   14	458	6	localObject2	Object
    //   72	356	7	localFile	File
    //   168	236	8	localObject3	Object
    //   413	23	8	localException2	Exception
    //   189	173	9	localResources	Resources
    //   401	10	9	localObject4	Object
    //   321	57	10	str	String
    // Exception table:
    //   from	to	target	type
    //   11	16	294	java/lang/Exception
    //   21	52	294	java/lang/Exception
    //   57	61	294	java/lang/Exception
    //   74	112	294	java/lang/Exception
    //   246	251	294	java/lang/Exception
    //   286	291	294	java/lang/Exception
    //   352	357	294	java/lang/Exception
    //   389	394	294	java/lang/Exception
    //   441	446	294	java/lang/Exception
    //   451	459	294	java/lang/Exception
    //   459	483	294	java/lang/Exception
    //   207	215	401	finally
    //   230	239	401	finally
    //   254	279	401	finally
    //   310	323	401	finally
    //   328	336	401	finally
    //   336	345	401	finally
    //   360	382	401	finally
    //   112	157	413	java/lang/Exception
    //   161	202	413	java/lang/Exception
    //   239	246	413	java/lang/Exception
    //   279	286	413	java/lang/Exception
    //   345	352	413	java/lang/Exception
    //   382	389	413	java/lang/Exception
    //   403	413	413	java/lang/Exception
    //   112	157	449	finally
    //   161	202	449	finally
    //   239	246	449	finally
    //   279	286	449	finally
    //   345	352	449	finally
    //   382	389	449	finally
    //   403	413	449	finally
    //   415	441	449	finally
  }
  
  protected void onPrepareDialogBuilder(AlertDialog.Builder paramBuilder)
  {
    super.onPrepareDialogBuilder(paramBuilder);
    int n = TypedPrefs.theme_id;
    String str = TypedPrefs.theme_path;
    int j = 0;
    int k = 0;
    Object localObject1 = getContext().getResources().getStringArray(2131361792);
    Object localObject2 = getContext().getResources().obtainTypedArray(2131361800);
    final int[] arrayOfInt = new int[((TypedArray)localObject2).length()];
    final int i = 0;
    while (i < ((TypedArray)localObject2).length())
    {
      arrayOfInt[i] = ((TypedArray)localObject2).getResourceId(i, 0);
      i += 1;
    }
    ((TypedArray)localObject2).recycle();
    localObject2 = new ArrayList(10);
    final ArrayList localArrayList1 = new ArrayList(10);
    final ArrayList localArrayList2 = new ArrayList(10);
    int m = 0;
    i = k;
    k = m;
    while (k < localObject1.length)
    {
      localArrayList1.add(localObject1[k]);
      localArrayList2.add(getContext().getString(2131230985));
      if (n == arrayOfInt[k]) {
        j = i;
      }
      ((ArrayList)localObject2).add(Boolean.TRUE);
      i += 1;
      k += 1;
    }
    final ArrayList localArrayList3 = 𐐁();
    Object localObject3 = localArrayList3.iterator();
    if (((Iterator)localObject3).hasNext())
    {
      localObject1 = (𐀀)((Iterator)localObject3).next();
      localArrayList1.add(((𐀀)localObject1).𐐂);
      if (!((𐀀)localObject1).llll)
      {
        localArrayList2.add(((𐀀)localObject1).ll1l + " | " + ((𐀀)localObject1).𐰄);
        label297:
        k = j;
        if (n == ((𐀀)localObject1).𐐁)
        {
          k = j;
          if (TextUtils.equals(str, ((𐀀)localObject1).𐀀)) {
            k = i;
          }
        }
        if (!((𐀀)localObject1).llll) {
          break label374;
        }
      }
      label374:
      for (localObject1 = Boolean.FALSE;; localObject1 = Boolean.TRUE)
      {
        ((ArrayList)localObject2).add(localObject1);
        i += 1;
        j = k;
        break;
        localArrayList2.add(((𐀀)localObject1).𐰄);
        break label297;
      }
    }
    localObject3 = 𐐂();
    Iterator localIterator = ((ArrayList)localObject3).iterator();
    if (localIterator.hasNext())
    {
      localObject1 = (𐀀)localIterator.next();
      localArrayList1.add(((𐀀)localObject1).𐐂);
      if (!((𐀀)localObject1).llll)
      {
        localArrayList2.add(((𐀀)localObject1).ll1l + " | " + ((𐀀)localObject1).𐰄);
        label474:
        k = j;
        if (n == ((𐀀)localObject1).𐐁)
        {
          k = j;
          if (TextUtils.equals(str, ((𐀀)localObject1).𐀀)) {
            k = i;
          }
        }
        if (!((𐀀)localObject1).llll) {
          break label551;
        }
      }
      label551:
      for (localObject1 = Boolean.FALSE;; localObject1 = Boolean.TRUE)
      {
        ((ArrayList)localObject2).add(localObject1);
        i += 1;
        j = k;
        break;
        localArrayList2.add(((𐀀)localObject1).𐰄);
        break label474;
      }
    }
    i = 0;
    if (i < arrayOfInt.length) {
      if (n != arrayOfInt[i]) {}
    }
    for (;;)
    {
      paramBuilder.setSingleChoiceItems(new ArrayAdapter(getContext().getApplicationContext(), localArrayList1, i, localArrayList2)
      {
        public final View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
        {
          boolean bool = false;
          paramAnonymousView = super.getView(paramAnonymousInt, paramAnonymousView, paramAnonymousViewGroup);
          paramAnonymousViewGroup = (CheckedTextView)((ViewGroup)paramAnonymousView).getChildAt(0);
          if (paramAnonymousInt == i) {
            bool = true;
          }
          paramAnonymousViewGroup.setChecked(bool);
          TextView localTextView = (TextView)paramAnonymousView.findViewById(16908309);
          localTextView.setText((CharSequence)localArrayList2.get(paramAnonymousInt));
          bool = ((Boolean)this.𐐂.get(paramAnonymousInt)).booleanValue();
          paramAnonymousView.setEnabled(bool);
          paramAnonymousViewGroup.setEnabled(bool);
          localTextView.setEnabled(bool);
          return paramAnonymousView;
        }
      }, i, new DialogInterface.OnClickListener()
      {
        public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          Object localObject2 = null;
          int j;
          int i;
          Object localObject1;
          if ((paramAnonymousInt >= 0) && (paramAnonymousInt < localArrayList1.size()))
          {
            j = 0;
            if (paramAnonymousInt >= arrayOfInt.length) {
              break label76;
            }
            i = arrayOfInt[paramAnonymousInt];
            localObject1 = localObject2;
          }
          for (;;)
          {
            if (i != 0)
            {
              TypedPrefs.theme_path = (String)localObject1;
              TypedPrefs.theme_id = i;
              Application.getInstance().l1li();
            }
            ThemePreference.this.onClick(paramAnonymousDialogInterface, -1);
            paramAnonymousDialogInterface.dismiss();
            return;
            label76:
            if ((paramAnonymousInt >= arrayOfInt.length) && (paramAnonymousInt < arrayOfInt.length + localArrayList3.size()))
            {
              localObject1 = (ThemePreference.𐀀)localArrayList3.get(paramAnonymousInt - arrayOfInt.length);
              i = ((ThemePreference.𐀀)localObject1).𐐁;
              localObject1 = ((ThemePreference.𐀀)localObject1).𐀀;
            }
            else
            {
              localObject1 = localObject2;
              i = j;
              if (paramAnonymousInt >= arrayOfInt.length + localArrayList3.size())
              {
                localObject1 = localObject2;
                i = j;
                if (paramAnonymousInt < arrayOfInt.length + localArrayList3.size() + this.𐰄.size())
                {
                  localObject1 = (ThemePreference.𐀀)this.𐰄.get(paramAnonymousInt - arrayOfInt.length - localArrayList3.size());
                  i = ((ThemePreference.𐀀)localObject1).𐐁;
                  localObject1 = ((ThemePreference.𐀀)localObject1).𐀀;
                }
              }
            }
          }
        }
      });
      paramBuilder.setPositiveButton(null, null);
      paramBuilder.setNegativeButton(null, null);
      paramBuilder.setNeutralButton(2131231522, new DialogInterface.OnClickListener()
      {
        public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ThemePreference.𐀀(ThemePreference.this.getContext());
        }
      });
      return;
      i += 1;
      break;
      i = j;
    }
  }
  
  public final void 𐀀()
  {
    showDialog(null);
  }
  
  static final class 𐀀
  {
    String ll1l;
    boolean llll;
    String 𐀀;
    int 𐐁;
    String 𐐂;
    String 𐰄;
    
    𐀀() {}
  }
}
