package tv.picpac;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera.Size;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.renderscript.Allocation;
import android.renderscript.Allocation.MipmapControl;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.b.b.b.c;
import com.b.b.b.h;
import com.b.b.i;
import com.google.api.client.util.IOUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ffmpeg.android.e;
import tv.picpac.view.ToastCustomed;

@SuppressLint({"SimpleDateFormat"})
public class UtilsPicPac
{
  public static final String TAG = "UtilsPicPac";
  public static SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd_HHmmss");
  public static SimpleDateFormat dateformatSimple = new SimpleDateFormat("yyyy.MM.dd");
  public static Comparator<File> fileSorterNameAscending = new UtilsPicPac.7();
  public static Comparator<File> fileSorterNameDescending = new UtilsPicPac.8();
  public static Comparator<File> fileSorterNewestFirst;
  public static Comparator<File> fileSorterOldestFirst;
  public static FilenameFilter filterFolder;
  public static FileFilter filterFolderSimple;
  public static FilenameFilter filterImage = new UtilsPicPac.1();
  public static FilenameFilter filterImageJpgOrPng = new UtilsPicPac.2();
  
  static
  {
    filterFolder = new UtilsPicPac.3();
    filterFolderSimple = new UtilsPicPac.4();
    fileSorterOldestFirst = new UtilsPicPac.5();
    fileSorterNewestFirst = new UtilsPicPac.6();
  }
  
  public UtilsPicPac() {}
  
  public static File[] add2FileArray(File[] paramArrayOfFile, File paramFile)
  {
    int i = 0;
    File[] arrayOfFile = new File[paramArrayOfFile.length + 1];
    arrayOfFile[0] = paramFile;
    while (i < paramArrayOfFile.length)
    {
      arrayOfFile[(i + 1)] = paramArrayOfFile[i];
      i += 1;
    }
    return arrayOfFile;
  }
  
  public static Bitmap addPaddingToImage(Global paramGlobal, File paramFile, int paramInt1, int paramInt2, int paramInt3)
  {
    paramFile = scaleImageToBitmap(paramFile, (int)(paramInt1 * paramInt2 * 1.5D));
    if ((paramGlobal.isBestBit()) && (paramGlobal.usingAnimationInSlideshow)) {}
    for (int i = paramFile.getWidth() * paramInt2 / paramFile.getHeight();; i = paramInt1)
    {
      int j = paramInt2;
      paramGlobal = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(paramGlobal);
      Paint localPaint = new Paint();
      localPaint.setAntiAlias(true);
      if (paramInt1 == paramInt2) {
        localCanvas.drawColor(Color.argb(255, 15, 15, 15));
      }
      if ((paramInt3 == 0) || (paramInt3 == 1))
      {
        paramInt2 = (int)(1.0D * paramFile.getWidth() / paramFile.getHeight() * j);
        if (paramInt2 > i)
        {
          paramInt1 = (int)(1.0D * paramFile.getHeight() / paramFile.getWidth() * i);
          paramInt2 = i;
        }
      }
      for (;;)
      {
        localCanvas.drawBitmap(paramFile, null, new Rect((i - paramInt2) / 2, (j - paramInt1) / 2, (i + paramInt2) / 2, (j + paramInt1) / 2), localPaint);
        paramFile.recycle();
        return paramGlobal;
        paramInt1 = j;
        continue;
        paramInt2 = (int)(1.0D * paramFile.getWidth() / paramFile.getHeight() * j);
        if (paramInt2 > i)
        {
          paramInt1 = j;
        }
        else
        {
          paramInt1 = (int)(1.0D * paramFile.getHeight() / paramFile.getWidth() * i);
          paramInt2 = i;
        }
      }
    }
  }
  
  public static void addPaddingToImageFile(Global paramGlobal, File paramFile1, int paramInt1, int paramInt2, File paramFile2, int paramInt3)
  {
    BitmapFactory.Options localOptions = getImageProperties(paramFile1);
    double d = 1.0D * localOptions.outWidth / localOptions.outHeight;
    if ((localOptions.outWidth == paramInt1) && (localOptions.outHeight == paramInt2)) {
      return;
    }
    paramGlobal = addPaddingToImage(paramGlobal, paramFile1, paramInt1, paramInt2, paramInt3);
    try
    {
      saveBitmapToFile(paramGlobal, paramFile2);
      return;
    }
    catch (FileNotFoundException paramGlobal)
    {
      paramGlobal.printStackTrace();
      return;
    }
    catch (IOException paramGlobal)
    {
      paramGlobal.printStackTrace();
    }
  }
  
  public static boolean applicationInit(ActivityIAPBase paramActivityIAPBase)
  {
    File localFile2;
    if (paramActivityIAPBase.Global().tempFolder == null)
    {
      if (paramActivityIAPBase.Global().isBestBit()) {
        paramActivityIAPBase.Global().tempFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), paramActivityIAPBase.getResources().getString(2131165593) + "/" + ".projects");
      }
    }
    else
    {
      File localFile1 = new File(paramActivityIAPBase.Global().tempFolder.getParentFile(), ".projects");
      localFile2 = new File(paramActivityIAPBase.Global().tempFolder.getParentFile(), "my_projects");
      if (!localFile1.exists()) {
        break label519;
      }
      if (localFile2.exists()) {
        break label508;
      }
      if (paramActivityIAPBase.Global().tempFolder.renameTo(localFile2))
      {
        paramActivityIAPBase.Global().tempFolder = localFile2;
        createNoMedia(paramActivityIAPBase.Global().tempFolder);
      }
    }
    for (;;)
    {
      if (!paramActivityIAPBase.Global().tempFolder.exists())
      {
        paramActivityIAPBase.Global().tempFolder.mkdirs();
        createNoMedia(paramActivityIAPBase.Global().tempFolder);
      }
      if (paramActivityIAPBase.Global().tempSelectedFolder == null) {
        paramActivityIAPBase.Global().tempSelectedFolder = new File(paramActivityIAPBase.Global().tempFolder, "selected");
      }
      if (!paramActivityIAPBase.Global().tempSelectedFolder.exists())
      {
        paramActivityIAPBase.Global().tempSelectedFolder.mkdirs();
        createNoMedia(paramActivityIAPBase.Global().tempSelectedFolder);
      }
      if ((paramActivityIAPBase.Global().tempLibraryFolder != null) && (!paramActivityIAPBase.Global().tempLibraryFolder.exists()))
      {
        paramActivityIAPBase.Global().tempLibraryFolder.mkdirs();
        createNoMedia(paramActivityIAPBase.Global().tempLibraryFolder);
      }
      if ((!paramActivityIAPBase.Global().tempFolder.exists()) || (!paramActivityIAPBase.Global().tempSelectedFolder.exists())) {
        break label530;
      }
      return true;
      if (paramActivityIAPBase.Global().isSnapComic())
      {
        paramActivityIAPBase.Global().tempFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), paramActivityIAPBase.getResources().getString(2131165595) + "/" + ".projects");
        paramActivityIAPBase.Global().tempLibraryFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), paramActivityIAPBase.getResources().getString(2131165595) + "/" + ".library");
        break;
      }
      paramActivityIAPBase.Global().tempFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), paramActivityIAPBase.getResources().getString(2131165592) + "/" + ".projects");
      break;
      label508:
      paramActivityIAPBase.Global().tempFolder = localFile2;
      continue;
      label519:
      paramActivityIAPBase.Global().tempFolder = localFile2;
    }
    label530:
    return false;
  }
  
  @SuppressLint({"NewApi"})
  public static Bitmap blurBitmap(Bitmap paramBitmap, int paramInt, Context paramContext)
  {
    Object localObject1;
    Object localObject2;
    if (Build.VERSION.SDK_INT > 16)
    {
      localObject1 = paramBitmap.copy(paramBitmap.getConfig(), true);
      paramContext = RenderScript.create(paramContext);
      paramBitmap = Allocation.createFromBitmap(paramContext, paramBitmap, Allocation.MipmapControl.MIPMAP_NONE, 1);
      localObject2 = Allocation.createTyped(paramContext, paramBitmap.getType());
      paramContext = ScriptIntrinsicBlur.create(paramContext, Element.U8_4(paramContext));
      paramContext.setRadius(paramInt);
      paramContext.setInput(paramBitmap);
      paramContext.forEach((Allocation)localObject2);
      ((Allocation)localObject2).copyTo((Bitmap)localObject1);
      return localObject1;
    }
    int i12;
    int i13;
    int i11;
    int i14;
    int i;
    int i15;
    int[] arrayOfInt1;
    int[] arrayOfInt2;
    int j;
    int[][] arrayOfInt;
    int i6;
    int i7;
    int i8;
    int i9;
    int[] arrayOfInt4;
    int i5;
    int i4;
    int i3;
    int i1;
    int m;
    label463:
    int i2;
    int i18;
    int i17;
    int i10;
    try
    {
      paramBitmap = paramBitmap.copy(paramBitmap.getConfig(), true);
      if (paramInt < 1) {
        return null;
      }
      i12 = paramBitmap.getWidth();
      i13 = paramBitmap.getHeight();
      paramContext = new int[i12 * i13];
      Log.e("pix", i12 + " " + i13 + " " + paramContext.length);
      paramBitmap.getPixels(paramContext, 0, i12, 0, 0, i12, i13);
      i11 = i12 - 1;
      i14 = i13 - 1;
      i = i12 * i13;
      i15 = paramInt + paramInt + 1;
      localObject1 = new int[i];
      localObject2 = new int[i];
      arrayOfInt1 = new int[i];
      arrayOfInt2 = new int[Math.max(i12, i13)];
      i = i15 + 1 >> 1;
      j = i * i;
      arrayOfInt3 = new int[j * 256];
      i = 0;
      while (i < j * 256)
      {
        arrayOfInt3[i] = (i / j);
        i += 1;
      }
      arrayOfInt = (int[][])Array.newInstance(Integer.TYPE, new int[] { i15, 3 });
      i16 = paramInt + 1;
      i6 = 0;
      i = 0;
      i7 = 0;
    }
    catch (OutOfMemoryError paramBitmap)
    {
      int[] arrayOfInt3;
      int i16;
      label672:
      return null;
    }
    if (i8 <= paramInt)
    {
      i9 = paramContext[(Math.min(i11, Math.max(i8, 0)) + i)];
      arrayOfInt4 = arrayOfInt[(i8 + paramInt)];
      arrayOfInt4[0] = ((0xFF0000 & i9) >> 16);
      arrayOfInt4[1] = ((0xFF00 & i9) >> 8);
      arrayOfInt4[2] = (i9 & 0xFF);
      i9 = i16 - Math.abs(i8);
      i5 += arrayOfInt4[0] * i9;
      i4 += arrayOfInt4[1] * i9;
      i3 += i9 * arrayOfInt4[2];
      if (i8 > 0)
      {
        i1 += arrayOfInt4[0];
        m += arrayOfInt4[1];
        j += arrayOfInt4[2];
        break label833;
        if (i2 >= i12) {
          break label1097;
        }
        localObject1[i] = arrayOfInt3[i5];
        localObject2[i] = arrayOfInt3[i8];
        arrayOfInt1[i] = arrayOfInt3[i9];
        arrayOfInt4 = arrayOfInt[((i4 - paramInt + i15) % i15)];
        i18 = arrayOfInt4[0];
        i17 = arrayOfInt4[1];
        i10 = arrayOfInt4[2];
        if (i7 != 0) {
          break label897;
        }
        arrayOfInt2[i2] = Math.min(i2 + paramInt + 1, i11);
        break label897;
        if (i7 > paramInt) {
          break label1253;
        }
        i8 = Math.max(0, i6) + i;
        arrayOfInt4 = arrayOfInt[(i7 + paramInt)];
        arrayOfInt4[0] = localObject1[i8];
        arrayOfInt4[1] = localObject2[i8];
        arrayOfInt4[2] = arrayOfInt1[i8];
        i9 = i16 - Math.abs(i7);
        i10 = localObject1[i8];
        i11 = localObject2[i8];
        i17 = arrayOfInt1[i8];
        if (i7 <= 0) {
          break label1223;
        }
        i1 += arrayOfInt4[0];
        m += arrayOfInt4[1];
        j += arrayOfInt4[2];
        break label1162;
        if (i3 >= i13) {
          break label1501;
        }
        paramContext[i1] = (0xFF000000 & paramContext[i1] | arrayOfInt3[i5] << 16 | arrayOfInt3[i6] << 8 | arrayOfInt3[i8]);
        arrayOfInt4 = arrayOfInt[((i7 - paramInt + i15) % i15)];
        i11 = arrayOfInt4[0];
        i10 = arrayOfInt4[1];
        i9 = arrayOfInt4[2];
        if (i != 0) {
          break label1313;
        }
        arrayOfInt2[i3] = (Math.min(i3 + i16, i14) * i12);
        break label1313;
      }
    }
    label833:
    label897:
    label1097:
    label1113:
    label1162:
    label1223:
    label1253:
    label1313:
    label1501:
    label1506:
    for (;;)
    {
      paramBitmap.setPixels(paramContext, 0, i12, 0, 0, i12, i13);
      return paramBitmap;
      int k;
      int n;
      for (;;)
      {
        if (i7 >= i13) {
          break label1113;
        }
        j = 0;
        i8 = -paramInt;
        i1 = 0;
        k = 0;
        n = 0;
        i2 = 0;
        i3 = 0;
        i4 = 0;
        i5 = 0;
        m = 0;
        break;
        for (;;)
        {
          i8 += 1;
          break;
          i2 += arrayOfInt4[0];
          n += arrayOfInt4[1];
          k += arrayOfInt4[2];
        }
        i8 = i4;
        i9 = i3;
        i10 = 0;
        i4 = paramInt;
        i3 = i2;
        i2 = i10;
        break label463;
        int i19 = paramContext[(arrayOfInt2[i2] + i6)];
        arrayOfInt4[0] = ((0xFF0000 & i19) >> 16);
        arrayOfInt4[1] = ((0xFF00 & i19) >> 8);
        arrayOfInt4[2] = (i19 & 0xFF);
        i1 += arrayOfInt4[0];
        m += arrayOfInt4[1];
        j += arrayOfInt4[2];
        i5 = i5 - i3 + i1;
        i8 = i8 - n + m;
        i9 = i9 - k + j;
        i4 = (i4 + 1) % i15;
        arrayOfInt4 = arrayOfInt[(i4 % i15)];
        i3 = i3 - i18 + arrayOfInt4[0];
        n = n - i17 + arrayOfInt4[1];
        k = k - i10 + arrayOfInt4[2];
        i1 -= arrayOfInt4[0];
        m -= arrayOfInt4[1];
        j -= arrayOfInt4[2];
        i += 1;
        i2 += 1;
        break label463;
        i6 += i12;
        i7 += 1;
      }
      i = 0;
      for (;;)
      {
        if (i >= i12) {
          break label1506;
        }
        j = 0;
        i6 = -paramInt * i12;
        i7 = -paramInt;
        i1 = 0;
        k = 0;
        n = 0;
        i2 = 0;
        i3 = 0;
        i4 = 0;
        i5 = 0;
        m = 0;
        break;
        for (;;)
        {
          i8 = i6;
          if (i7 < i14) {
            i8 = i6 + i12;
          }
          i7 += 1;
          i3 = i17 * i9 + i3;
          i4 = i11 * i9 + i4;
          i5 = i10 * i9 + i5;
          i6 = i8;
          break;
          i2 += arrayOfInt4[0];
          n += arrayOfInt4[1];
          k += arrayOfInt4[2];
        }
        i6 = i4;
        i11 = 0;
        i8 = i3;
        i3 = i;
        i4 = k;
        i9 = n;
        i10 = i2;
        i7 = paramInt;
        k = m;
        n = i1;
        m = i4;
        i2 = i9;
        i4 = i10;
        i1 = i3;
        i3 = i11;
        break label672;
        i17 = arrayOfInt2[i3] + i;
        arrayOfInt4[0] = localObject1[i17];
        arrayOfInt4[1] = localObject2[i17];
        arrayOfInt4[2] = arrayOfInt1[i17];
        n += arrayOfInt4[0];
        k += arrayOfInt4[1];
        j += arrayOfInt4[2];
        i5 = i5 - i4 + n;
        i6 = i6 - i2 + k;
        i8 = i8 - m + j;
        i7 = (i7 + 1) % i15;
        arrayOfInt4 = arrayOfInt[i7];
        i4 = i4 - i11 + arrayOfInt4[0];
        i2 = i2 - i10 + arrayOfInt4[1];
        m = m - i9 + arrayOfInt4[2];
        n -= arrayOfInt4[0];
        k -= arrayOfInt4[1];
        j -= arrayOfInt4[2];
        i1 += i12;
        i3 += 1;
        break label672;
        i += 1;
      }
    }
  }
  
  public static void cleanRubbishImagesInProject(File paramFile)
  {
    File[] arrayOfFile = paramFile.listFiles(filterImage);
    Object localObject;
    ArrayList localArrayList;
    try
    {
      localObject = new Scanner(new File(paramFile, "order.txt"));
      localArrayList = new ArrayList();
      while (((Scanner)localObject).hasNextLine())
      {
        localArrayList.add(new File(paramFile, ((Scanner)localObject).nextLine()));
        continue;
        return;
      }
    }
    catch (FileNotFoundException paramFile)
    {
      paramFile.printStackTrace();
    }
    ((Scanner)localObject).close();
    int k = arrayOfFile.length;
    int i = 0;
    label85:
    if (i < k)
    {
      paramFile = arrayOfFile[i];
      localObject = localArrayList.iterator();
      File localFile;
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        localFile = (File)((Iterator)localObject).next();
      } while (!paramFile.getName().equals(localFile.getName()));
    }
    for (int j = 1;; j = 0)
    {
      if (j == 0) {
        paramFile.delete();
      }
      i += 1;
      break label85;
      break;
    }
  }
  
  public static Bitmap collageTwoImage(File paramFile1, File paramFile2)
  {
    paramFile1 = scaleImageToBitmap(paramFile1, 1800000);
    paramFile2 = scaleImageToBitmap(paramFile2, 1800000);
    if (paramFile1.getHeight() < paramFile2.getHeight()) {}
    for (int i = paramFile1.getHeight();; i = paramFile2.getHeight())
    {
      int j = (int)(paramFile1.getWidth() * 1.0D * i / paramFile1.getHeight());
      int k = (int)(paramFile2.getWidth() * 1.0D * i / paramFile2.getHeight()) + j;
      Bitmap localBitmap = Bitmap.createBitmap(k, i, Bitmap.Config.RGB_565);
      Canvas localCanvas = new Canvas(localBitmap);
      localCanvas.drawBitmap(paramFile1, null, new Rect(0, 0, j, i), null);
      localCanvas.drawBitmap(paramFile2, null, new Rect(j + 1, 0, k, i), null);
      paramFile1.recycle();
      paramFile2.recycle();
      return localBitmap;
    }
  }
  
  public static String combineStrings(String paramString, String... paramVarArgs)
  {
    int j = paramVarArgs.length;
    if (j == 0) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramVarArgs[0]);
    int i = 1;
    while (i < j)
    {
      localStringBuilder.append(paramString).append(paramVarArgs[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  /* Error */
  public static File copyAssetToFile(Context paramContext, String paramString1, File paramFile, String paramString2)
  {
    // Byte code:
    //   0: new 89	java/io/File
    //   3: dup
    //   4: aload_2
    //   5: aload_3
    //   6: invokespecial 239	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   9: astore_2
    //   10: aload_2
    //   11: invokevirtual 248	java/io/File:exists	()Z
    //   14: ifeq +5 -> 19
    //   17: aload_2
    //   18: areturn
    //   19: aload_0
    //   20: invokevirtual 461	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   23: astore_0
    //   24: aload_0
    //   25: aload_1
    //   26: invokevirtual 467	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   29: astore_0
    //   30: sipush 1024
    //   33: newarray byte
    //   35: astore_1
    //   36: new 469	java/io/FileOutputStream
    //   39: dup
    //   40: aload_2
    //   41: invokespecial 470	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   44: astore_3
    //   45: aload_0
    //   46: aload_1
    //   47: invokevirtual 476	java/io/InputStream:read	([B)I
    //   50: istore 4
    //   52: iload 4
    //   54: ifle +21 -> 75
    //   57: aload_3
    //   58: aload_1
    //   59: iconst_0
    //   60: iload 4
    //   62: invokevirtual 480	java/io/FileOutputStream:write	([BII)V
    //   65: goto -20 -> 45
    //   68: astore_0
    //   69: aload_0
    //   70: invokevirtual 189	java/io/IOException:printStackTrace	()V
    //   73: aconst_null
    //   74: areturn
    //   75: aload_0
    //   76: invokevirtual 481	java/io/InputStream:close	()V
    //   79: aload_3
    //   80: invokevirtual 482	java/io/FileOutputStream:close	()V
    //   83: aload_2
    //   84: areturn
    //   85: astore_0
    //   86: aload_0
    //   87: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	paramContext	Context
    //   0	88	1	paramString1	String
    //   0	88	2	paramFile	File
    //   0	88	3	paramString2	String
    //   50	11	4	i	int
    // Exception table:
    //   from	to	target	type
    //   24	45	68	java/io/IOException
    //   45	52	68	java/io/IOException
    //   57	65	68	java/io/IOException
    //   75	83	68	java/io/IOException
    //   24	45	85	finally
    //   45	52	85	finally
    //   57	65	85	finally
    //   69	73	85	finally
    //   75	83	85	finally
  }
  
  /* Error */
  public static File copyFile(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: ldc_w 485
    //   5: new 212	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 213	java/lang/StringBuilder:<init>	()V
    //   12: ldc_w 487
    //   15: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: aload_0
    //   19: invokevirtual 490	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   22: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: ldc_w 492
    //   28: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload_1
    //   32: invokevirtual 490	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   35: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 236	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: invokestatic 495	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   44: pop
    //   45: aload_1
    //   46: invokevirtual 248	java/io/File:exists	()Z
    //   49: ifne +8 -> 57
    //   52: aload_1
    //   53: invokevirtual 498	java/io/File:createNewFile	()Z
    //   56: pop
    //   57: new 500	java/io/FileInputStream
    //   60: dup
    //   61: aload_0
    //   62: invokespecial 501	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   65: invokevirtual 505	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   68: astore_2
    //   69: new 469	java/io/FileOutputStream
    //   72: dup
    //   73: aload_1
    //   74: invokespecial 470	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   77: invokevirtual 506	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   80: astore_0
    //   81: aload_0
    //   82: aload_2
    //   83: lconst_0
    //   84: aload_2
    //   85: invokevirtual 512	java/nio/channels/FileChannel:size	()J
    //   88: invokevirtual 516	java/nio/channels/FileChannel:transferFrom	(Ljava/nio/channels/ReadableByteChannel;JJ)J
    //   91: pop2
    //   92: aload_2
    //   93: ifnull +7 -> 100
    //   96: aload_2
    //   97: invokevirtual 517	java/nio/channels/FileChannel:close	()V
    //   100: aload_0
    //   101: ifnull +7 -> 108
    //   104: aload_0
    //   105: invokevirtual 517	java/nio/channels/FileChannel:close	()V
    //   108: aload_1
    //   109: areturn
    //   110: astore_0
    //   111: aconst_null
    //   112: astore_1
    //   113: aload_3
    //   114: astore_2
    //   115: aload_2
    //   116: ifnull +7 -> 123
    //   119: aload_2
    //   120: invokevirtual 517	java/nio/channels/FileChannel:close	()V
    //   123: aload_1
    //   124: ifnull +7 -> 131
    //   127: aload_1
    //   128: invokevirtual 517	java/nio/channels/FileChannel:close	()V
    //   131: aload_0
    //   132: athrow
    //   133: astore_0
    //   134: aconst_null
    //   135: astore_1
    //   136: goto -21 -> 115
    //   139: astore_3
    //   140: aload_0
    //   141: astore_1
    //   142: aload_3
    //   143: astore_0
    //   144: goto -29 -> 115
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	147	0	paramFile1	File
    //   0	147	1	paramFile2	File
    //   68	52	2	localObject1	Object
    //   1	113	3	localObject2	Object
    //   139	4	3	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   57	69	110	finally
    //   69	81	133	finally
    //   81	92	139	finally
  }
  
  public static String copyFolder2Folder(File paramFile1, File paramFile2, int paramInt)
  {
    String str = String.format("%04d", new Object[] { Integer.valueOf(paramInt) });
    paramFile1 = paramFile1.listFiles(filterImage);
    int i = paramFile1.length;
    paramInt = 0;
    for (;;)
    {
      if (paramInt < i)
      {
        File localFile = paramFile1[paramInt];
        try
        {
          copyFile(localFile, new File(paramFile2, str + localFile.getName()));
          paramInt += 1;
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            localIOException.printStackTrace();
          }
        }
      }
    }
    return str;
  }
  
  public static File copyResourceToFile(Context paramContext, File paramFile, String paramString1, int paramInt, String paramString2)
  {
    paramFile = new File(paramFile, paramString2);
    if (paramFile.exists()) {
      return paramFile;
    }
    paramString2 = paramContext.getResources();
    if (paramString1 != null) {
      paramContext = paramString2.openRawResource(paramString2.getIdentifier(paramString1, "raw", paramContext.getPackageName()));
    }
    for (;;)
    {
      paramString1 = new byte['Ѐ'];
      try
      {
        paramString2 = new FileOutputStream(paramFile);
        for (;;)
        {
          paramInt = paramContext.read(paramString1);
          if (paramInt <= 0) {
            break;
          }
          paramString2.write(paramString1, 0, paramInt);
        }
      }
      catch (IOException paramContext)
      {
        paramContext = paramContext;
        paramContext.printStackTrace();
        return null;
        paramContext = paramString2.openRawResource(paramInt);
        continue;
        paramContext.close();
        paramString2.close();
        return paramFile;
      }
      finally {}
    }
  }
  
  public static int countSubString(String paramString1, String paramString2)
  {
    int j = 0;
    int i = 0;
    while (i != -1)
    {
      int k = paramString1.indexOf(paramString2, i);
      i = k;
      if (k != -1)
      {
        j += 1;
        i = k + paramString2.length();
      }
    }
    return j;
  }
  
  public static void createNoMedia(File paramFile)
  {
    File localFile = new File(paramFile, ".nomedia");
    if (!localFile.exists()) {}
    try
    {
      localFile.createNewFile();
      new File(paramFile, "nomedia").createNewFile();
      return;
    }
    catch (IOException paramFile)
    {
      paramFile.printStackTrace();
    }
  }
  
  public static Bitmap createViewSnapshot(View paramView)
  {
    paramView.layout(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
    Bitmap localBitmap = Bitmap.createBitmap(paramView.getWidth(), paramView.getHeight(), Bitmap.Config.ARGB_8888);
    paramView.draw(new Canvas(localBitmap));
    return localBitmap;
  }
  
  public static Bundle decodeUrl(String paramString)
  {
    Bundle localBundle = new Bundle();
    if (paramString != null)
    {
      paramString = paramString.split("&");
      int j = paramString.length;
      int i = 0;
      if (i < j)
      {
        String[] arrayOfString = paramString[i].split("=");
        if (arrayOfString.length == 2) {
          localBundle.putString(URLDecoder.decode(arrayOfString[0]), URLDecoder.decode(arrayOfString[1]));
        }
        for (;;)
        {
          i += 1;
          break;
          localBundle.putString(URLDecoder.decode(arrayOfString[0]), " ");
        }
      }
    }
    return localBundle;
  }
  
  public static boolean deleteDirectory(File paramFile)
  {
    if (paramFile.exists())
    {
      File[] arrayOfFile = paramFile.listFiles();
      if (arrayOfFile != null)
      {
        int i = 0;
        if (i < arrayOfFile.length)
        {
          if (arrayOfFile[i].isDirectory()) {
            deleteDirectory(arrayOfFile[i]);
          }
          for (;;)
          {
            i += 1;
            break;
            arrayOfFile[i].delete();
          }
        }
      }
    }
    return paramFile.delete();
  }
  
  public static File downloadUrl(String paramString, File paramFile)
  {
    try
    {
      paramString = new DefaultHttpClient().execute(new HttpGet(paramString)).getEntity();
      if (paramString != null)
      {
        paramString = paramString.getContent();
        FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
        IOUtils.copy(paramString, localFileOutputStream);
        localFileOutputStream.close();
      }
      return paramFile;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static File downloadUsingManager(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    paramString1 = new DownloadManager.Request(Uri.parse(paramString1));
    paramString1.setDescription("Downloading " + paramString3);
    paramString1.setTitle(paramString3);
    paramString1.allowScanningByMediaScanner();
    paramString1.setNotificationVisibility(1);
    paramString1.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, paramString2);
    DownloadManager localDownloadManager = (DownloadManager)paramActivity.getSystemService("download");
    new Thread(new UtilsPicPac.9(localDownloadManager.enqueue(paramString1), localDownloadManager, paramActivity, (ProgressBar)paramActivity.findViewById(2131624223), (TextView)paramActivity.findViewById(2131624222), paramString3)).start();
    Log.i("downloadUsingManager", paramString2 + " downloaded");
    return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), paramString2);
  }
  
  public static int dpToPx(Resources paramResources, int paramInt)
  {
    paramResources = paramResources.getDisplayMetrics();
    float f = paramInt;
    return Math.round(paramResources.xdpi / 160.0F * f);
  }
  
  public static File duplicateImageInProject(File paramFile1, File paramFile2)
  {
    String str = paramFile1.getName();
    Object localObject = str;
    if (!getFrameTransitionTag(str).isEmpty()) {
      localObject = str.replace(getFrameTransitionTag(str), "");
    }
    int i = ((String)localObject).indexOf(".jpg");
    if (i < 0) {
      return null;
    }
    str = ((String)localObject).substring(0, i);
    for (localObject = new File(paramFile2, str + "+.jpg"); ((File)localObject).exists(); localObject = new File(paramFile2, str + "+.jpg")) {
      str = str + "+";
    }
    try
    {
      copyFile(paramFile1, (File)localObject);
      return localObject;
    }
    catch (IOException paramFile1)
    {
      paramFile1.printStackTrace();
    }
    return null;
  }
  
  public static double easeInOutCubic(double paramDouble1, double paramDouble2, int paramInt1, int paramInt2)
  {
    paramDouble1 /= paramDouble2 / 2.0D;
    if (paramDouble1 < 1.0D) {
      return paramDouble1 * (paramDouble1 * paramDouble1) * paramInt2 / 2.0D + paramInt1;
    }
    paramDouble1 -= 2.0D;
    return (paramDouble1 * (paramDouble1 * paramDouble1) + 2.0D) * paramInt2 / 2.0D + paramInt1;
  }
  
  public static double easeInOutExpo(double paramDouble1, double paramDouble2, int paramInt1, int paramInt2)
  {
    paramDouble1 /= paramDouble2 / 2.0D;
    if (paramDouble1 < 1.0D) {
      return Math.pow(2.0D, (paramDouble1 - 1.0D) * 10.0D) * paramInt2 / 2.0D + paramInt1;
    }
    return (-Math.pow(2.0D, (paramDouble1 - 1.0D) * -10.0D) + 2.0D) * paramInt2 / 2.0D + paramInt1;
  }
  
  public static double easeInOutQuart(double paramDouble1, double paramDouble2, int paramInt1, int paramInt2)
  {
    paramDouble1 /= paramDouble2 / 2.0D;
    if (paramDouble1 < 1.0D) {
      return paramDouble1 * (paramDouble1 * paramDouble1 * paramDouble1) * paramInt2 / 2.0D + paramInt1;
    }
    paramDouble1 -= 2.0D;
    return -(paramDouble1 * (paramDouble1 * paramDouble1 * paramDouble1) - 2.0D) * paramInt2 / 2.0D + paramInt1;
  }
  
  public static double easeInOutQuint(double paramDouble1, double paramDouble2, int paramInt1, int paramInt2)
  {
    paramDouble1 /= paramDouble2 / 2.0D;
    if (paramDouble1 < 1.0D) {
      return paramDouble1 * (paramDouble1 * paramDouble1 * paramDouble1 * paramDouble1) * paramInt2 / 2.0D + paramInt1;
    }
    paramDouble1 -= 2.0D;
    return (paramDouble1 * (paramDouble1 * paramDouble1 * paramDouble1 * paramDouble1) + 2.0D) * paramInt2 / 2.0D + paramInt1;
  }
  
  public static double easeLinear(double paramDouble1, double paramDouble2, int paramInt1, int paramInt2)
  {
    return paramInt2 * paramDouble1 / paramDouble2 + paramInt1;
  }
  
  public static String encodeUrl(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramBundle.keySet().iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (i != 0) {
        i = 0;
      }
      for (;;)
      {
        localStringBuilder.append(URLEncoder.encode(str) + "=" + URLEncoder.encode(paramBundle.getString(str)));
        break;
        localStringBuilder.append("&");
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String encondeUrlString(String paramString)
  {
    try
    {
      paramString = new URL(paramString);
      paramString = new URI(paramString.getProtocol(), paramString.getUserInfo(), paramString.getHost(), paramString.getPort(), paramString.getPath(), paramString.getQuery(), paramString.getRef()).toURL().toString();
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    catch (URISyntaxException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public static ArrayList<File> fileArray2FileList(File[] paramArrayOfFile)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramArrayOfFile.length)
    {
      localArrayList.add(paramArrayOfFile[i]);
      i += 1;
    }
    return localArrayList;
  }
  
  public static File[] fileList2FileArray(ArrayList<File> paramArrayList)
  {
    File[] arrayOfFile = new File[paramArrayList.size()];
    int i = 0;
    while (i < paramArrayList.size())
    {
      arrayOfFile[i] = ((File)paramArrayList.get(i));
      i += 1;
    }
    return arrayOfFile;
  }
  
  public static Handler flashView(View paramView)
  {
    Handler localHandler = new Handler();
    new UtilsPicPac.10(paramView, localHandler).run();
    return localHandler;
  }
  
  public static void followOnTwitter(Activity paramActivity)
  {
    try
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("twitter://user?screen_name=picpactv")));
      return;
    }
    catch (Exception localException)
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://twitter.com/#!/picpactv")));
    }
  }
  
  public static boolean getAlreadyRated(Activity paramActivity)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramActivity).getBoolean("ALREADY_RATED", false);
  }
  
  public static long getAppFirstInstallTime(Context paramContext)
  {
    try
    {
      if (Build.VERSION.SDK_INT > 8) {
        return paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).firstInstallTime;
      }
      long l = new File(paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0).sourceDir).lastModified();
      return l;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 0L;
  }
  
  public static long getAppLastUpdateTime(Context paramContext)
  {
    try
    {
      if (Build.VERSION.SDK_INT > 8) {
        return paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).lastUpdateTime;
      }
      long l = new File(paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0).sourceDir).lastModified();
      return l;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 0L;
  }
  
  public static Bitmap getBitmapFromAsset(Context paramContext, String paramString)
  {
    paramContext = paramContext.getAssets();
    try
    {
      localInputStream = paramContext.open(paramString);
      if (localInputStream == null) {}
    }
    catch (IOException paramString)
    {
      try
      {
        paramContext = BitmapFactory.decodeStream(localInputStream);
      }
      catch (IOException paramString)
      {
        for (;;)
        {
          InputStream localInputStream;
          paramContext = null;
        }
      }
      try
      {
        localInputStream.close();
        return paramContext;
      }
      catch (IOException paramString)
      {
        break label27;
      }
      paramString = paramString;
      localInputStream = null;
      paramContext = null;
    }
    try
    {
      label27:
      localInputStream.close();
      paramString.printStackTrace();
      return paramContext;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
  }
  
  public static Bitmap getBitmapFromLibrary(Global paramGlobal, String paramString)
  {
    if (paramGlobal.snapLibrary == null) {
      paramGlobal.snapLibrary = new HashMap();
    }
    if (paramGlobal.snapLibrary.containsKey(paramString)) {
      return (Bitmap)paramGlobal.snapLibrary.get(paramString);
    }
    Bitmap localBitmap = scaleImageToBitmap(new File(paramGlobal.tempLibraryFolder, paramString), 1800000);
    paramGlobal.snapLibrary.put(paramString, localBitmap);
    return localBitmap;
  }
  
  public static String getCPUInfo()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("abi: ").append(Build.CPU_ABI).append("\n");
    if (new File("/proc/cpuinfo").exists()) {
      try
      {
        BufferedReader localBufferedReader = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
        for (;;)
        {
          String str = localBufferedReader.readLine();
          if (str == null) {
            break;
          }
          localStringBuffer.append(str + "\n");
        }
        return localStringBuffer.toString();
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
    for (;;)
    {
      if (localIOException != null) {
        localIOException.close();
      }
    }
  }
  
  public static long getDaysSinceInstall(Context paramContext)
  {
    return (new Date().getTime() - getAppFirstInstallTime(paramContext)) / 86400000L;
  }
  
  public static String getDirectedBy(Activity paramActivity)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramActivity).getString("Directed By", null);
  }
  
  public static String getFacebookPageURL(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      if (paramContext.getPackageInfo("com.facebook.katana", 0).versionCode >= 3002850) {
        return "fb://facewebmodal/f?href=https://www.facebook.com/picpactv";
      }
      return "fb://page/220137834859926";
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "https://www.facebook.com/picpactv";
  }
  
  public static long getFolderSize(File paramFile)
  {
    paramFile = paramFile.listFiles();
    int j = paramFile.length;
    long l = 0L;
    int i = 0;
    if (i < j)
    {
      File localFile = paramFile[i];
      if (localFile.isFile()) {}
      for (l += localFile.length();; l += getFolderSize(localFile))
      {
        i += 1;
        break;
      }
    }
    return l;
  }
  
  public static int getFrameTransitionMinProgress()
  {
    return 80;
  }
  
  public static String getFrameTransitionTag(String paramString)
  {
    if (paramString.contains("^")) {
      return paramString.substring(paramString.indexOf("^"), paramString.lastIndexOf("^") + 1);
    }
    return "";
  }
  
  public static File getImageFileFromLibrary(Global paramGlobal, String paramString)
  {
    return new File(paramGlobal.tempLibraryFolder, paramString);
  }
  
  public static BitmapFactory.Options getImageProperties(File paramFile)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramFile.getAbsolutePath(), localOptions);
    return localOptions;
  }
  
  public static int getNumberOfVideosCreated(Activity paramActivity)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramActivity).getInt("NUMBER_OF_VIDEOS_CREATED", 0);
  }
  
  public static boolean getPreferenceHasBestBitStylePack2(Global paramGlobal)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramGlobal).getBoolean(paramGlobal.PURCHASE_BESTBIT_STYLE_PACK_2, false);
  }
  
  public static boolean getPreferenceHasPro(Global paramGlobal)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramGlobal).getBoolean(paramGlobal.PURCHASE_PRO, false);
  }
  
  public static String getPreferenceLogoPath(Activity paramActivity)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramActivity).getString("PREFERENCE_LOGO_PATH", null);
  }
  
  public static boolean getPreferenceReceiveNotifiactions(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("receive_notification", true);
  }
  
  public static boolean getPreferenceRemoveEndingLogo(Activity paramActivity)
  {
    String str = PreferenceManager.getDefaultSharedPreferences(paramActivity).getString(paramActivity.getResources().getString(2131165341), null);
    return (str != null) && (str.equals(paramActivity.getResources().getString(2131165340)));
  }
  
  public static boolean getPreferenceShowDemoVideo(Activity paramActivity)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramActivity).getBoolean("show_demo_video", true);
  }
  
  public static String getPrefrenceDefaultCommunity(Activity paramActivity)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramActivity).getString("community_default", "Tutorial");
  }
  
  public static String getPrefrenceVersionName(Activity paramActivity)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramActivity).getString("version-name", null);
  }
  
  public static int getProgressFromVideoSpeedProgress(String paramString)
  {
    if (paramString == null) {}
    do
    {
      do
      {
        return 50;
        if (paramString.equals("0.25x")) {
          return 10;
        }
        if (paramString.equals("0.5x")) {
          return 30;
        }
      } while (paramString.equals("1x"));
      if (paramString.equals("2x")) {
        return 70;
      }
    } while (!paramString.equals("4x"));
    return 100;
  }
  
  public static String getProjectTitle(Global paramGlobal)
  {
    Object localObject = null;
    File localFile = new File(paramGlobal.tempProjectFolder, "title.txt");
    paramGlobal = localObject;
    if (localFile.exists()) {}
    try
    {
      paramGlobal = new Scanner(localFile).nextLine();
      return paramGlobal;
    }
    catch (FileNotFoundException paramGlobal)
    {
      paramGlobal.printStackTrace();
      return null;
    }
    catch (NoSuchElementException paramGlobal) {}
    return null;
  }
  
  public static boolean getPromoOrNot(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("PROMO_CODE_PRO_test1234", false);
  }
  
  public static String getRandomString(int paramInt)
  {
    char[] arrayOfChar = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    StringBuilder localStringBuilder = new StringBuilder();
    Random localRandom = new Random();
    int i = 0;
    while (i < paramInt)
    {
      localStringBuilder.append(arrayOfChar[localRandom.nextInt(arrayOfChar.length)]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  @SuppressLint({"NewApi"})
  public static Long getStorageAvailable()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    if (Build.VERSION.SDK_INT >= 18) {
      l = localStatFs.getAvailableBlocksLong();
    }
    for (long l = localStatFs.getBlockSizeLong() * l;; l = localStatFs.getBlockSize() * l)
    {
      return Long.valueOf(l / 1048576L);
      l = localStatFs.getAvailableBlocks();
    }
  }
  
  public static boolean getUseAddingForFree(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("ADDING_FOR_FREE", false);
  }
  
  public static boolean getUseWatermark(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("USE_WATERMARK", true);
  }
  
  public static void getVideoThumbnailAtLast(ActivityIAPBase paramActivityIAPBase, File paramFile1, File paramFile2)
  {
    try
    {
      paramActivityIAPBase = new org.ffmpeg.android.b(paramActivityIAPBase, paramActivityIAPBase.Global().tempProjectFolder);
      e localE = new e();
      localE.k = paramFile1.getAbsolutePath();
      paramActivityIAPBase.a(localE);
      double d = Double.parseDouble(localE.n);
      localE.m = ("" + d);
      localE.n = "1";
      paramActivityIAPBase.a(localE, null, new ShellOutputParser(), null, paramFile2);
      return;
    }
    catch (IOException paramActivityIAPBase)
    {
      paramActivityIAPBase.printStackTrace();
      return;
    }
    catch (InterruptedException paramActivityIAPBase)
    {
      paramActivityIAPBase.printStackTrace();
      return;
    }
    catch (Exception paramActivityIAPBase)
    {
      paramActivityIAPBase.printStackTrace();
    }
  }
  
  public static void getVideoThumbnailAtTime(ActivityIAPBase paramActivityIAPBase, File paramFile1, int paramInt, File paramFile2)
  {
    try
    {
      paramActivityIAPBase = new org.ffmpeg.android.b(paramActivityIAPBase, paramActivityIAPBase.Global().tempProjectFolder);
      e localE = new e();
      localE.k = paramFile1.getAbsolutePath();
      localE.m = milliseconds2SecondString(paramInt);
      localE.n = "1";
      paramActivityIAPBase.a(localE, null, new ShellOutputParser(), null, paramFile2);
      return;
    }
    catch (IOException paramActivityIAPBase)
    {
      paramActivityIAPBase.printStackTrace();
      return;
    }
    catch (InterruptedException paramActivityIAPBase)
    {
      paramActivityIAPBase.printStackTrace();
    }
  }
  
  public static void incrementNumberOfVideosCreated(Activity paramActivity)
  {
    paramActivity = PreferenceManager.getDefaultSharedPreferences(paramActivity);
    int i = paramActivity.getInt("NUMBER_OF_VIDEOS_CREATED", 0);
    paramActivity = paramActivity.edit();
    paramActivity.putInt("NUMBER_OF_VIDEOS_CREATED", i + 1);
    paramActivity.apply();
  }
  
  public static boolean isAppInstalled(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static boolean isAppturboUnlockable(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appturboCA2015")) || (localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appoftheday2015"))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isComicAppReady(Activity paramActivity)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramActivity).getBoolean("COMIC_APP_READY", false);
  }
  
  public static boolean isMomentsAppReady(Activity paramActivity)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramActivity).getBoolean("BESTBIT_APP_READY", false);
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo() != null;
  }
  
  public static void likeOnFacebook(Activity paramActivity)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(getFacebookPageURL(paramActivity)));
    paramActivity.startActivity(localIntent);
  }
  
  public static String mapChangedVideoSpeedProgressToString(int paramInt)
  {
    if (paramInt < 20) {
      return "0.25x";
    }
    if (paramInt < 40) {
      return "0.5x";
    }
    if (paramInt < 60) {
      return "1x";
    }
    if (paramInt < 80) {
      return "2x";
    }
    return "4x";
  }
  
  public static String mapDurationToFrameRate(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = paramString1.substring(0, paramString1.indexOf(" "));
    if (paramString1.contains("1/")) {
      return paramString1.replace("1/", "");
    }
    if (paramString1.equals("1")) {
      return "1/1";
    }
    return "1/" + paramString1;
  }
  
  public static String mapExtractSpeedProgressToFramerate(int paramInt, String paramString1, String paramString2)
  {
    if (paramInt < 5)
    {
      paramString1 = "10 " + paramString2;
      Global.framerate_extract = "0.1";
      return paramString1;
    }
    if (paramInt < 10)
    {
      paramString1 = "5 " + paramString2;
      Global.framerate_extract = "0.2";
      return paramString1;
    }
    if (paramInt < 15)
    {
      paramString1 = "3 " + paramString2;
      Global.framerate_extract = "0.33";
      return paramString1;
    }
    if (paramInt < 20)
    {
      paramString1 = "2 " + paramString2;
      Global.framerate_extract = "0.5";
      return paramString1;
    }
    if (paramInt < 30)
    {
      paramString1 = "1 " + paramString1;
      Global.framerate_extract = "1";
      return paramString1;
    }
    if (paramInt < 40)
    {
      paramString1 = "1/2 " + paramString1;
      Global.framerate_extract = "2";
      return paramString1;
    }
    if (paramInt < 50)
    {
      paramString1 = "1/3 " + paramString1;
      Global.framerate_extract = "3";
      return paramString1;
    }
    if (paramInt < 60)
    {
      paramString1 = "1/4 " + paramString1;
      Global.framerate_extract = "4";
      return paramString1;
    }
    if (paramInt < 70)
    {
      paramString1 = "1/5 " + paramString1;
      Global.framerate_extract = "5";
      return paramString1;
    }
    if (paramInt < 80)
    {
      paramString1 = "1/8 " + paramString1;
      Global.framerate_extract = "8";
      return paramString1;
    }
    if (paramInt < 90)
    {
      paramString1 = "1/10 " + paramString1;
      Global.framerate_extract = "10";
      return paramString1;
    }
    if (paramInt < 95)
    {
      paramString1 = "1/15 " + paramString1;
      Global.framerate_extract = "15";
      return paramString1;
    }
    if (paramInt < 100)
    {
      paramString1 = "1/20 " + paramString1;
      Global.framerate_extract = "20";
      return paramString1;
    }
    paramString1 = "1/25 " + paramString1;
    Global.framerate_extract = "25";
    return paramString1;
  }
  
  public static int mapFrameRateToDuration(String paramString)
  {
    if (paramString.contains("1/")) {
      return Integer.parseInt(paramString.replace("1/", ""));
    }
    return 1;
  }
  
  public static RectF mapPictureToPreview(Camera.Size paramSize1, Camera.Size paramSize2, Camera.Size paramSize3)
  {
    RectF localRectF = new RectF(0.0F, 0.0F, paramSize2.width, paramSize2.height);
    paramSize1 = new RectF(0.0F, 0.0F, paramSize1.width, paramSize1.height);
    paramSize3 = new RectF(0.0F, 0.0F, paramSize3.width, paramSize3.height);
    paramSize2 = new RectF(0.0F, 0.0F, paramSize2.width, paramSize2.height);
    Matrix localMatrix = new Matrix();
    localMatrix.setRectToRect(localRectF, paramSize3, Matrix.ScaleToFit.CENTER);
    localMatrix.mapRect(paramSize2);
    localMatrix.setRectToRect(paramSize1, paramSize3, Matrix.ScaleToFit.CENTER);
    localMatrix.invert(localMatrix);
    localMatrix.mapRect(paramSize2);
    return paramSize2;
  }
  
  public static String mapVideoSpeedProgressToDuration(Global paramGlobal, int paramInt, boolean paramBoolean, String paramString1, String paramString2)
  {
    Global localGlobal;
    if (!paramGlobal.isBestBit()) {
      if (paramInt < 5)
      {
        paramGlobal = "10 " + paramString2;
        if (!paramBoolean) {
          break label522;
        }
        localGlobal = paramGlobal;
      }
    }
    label522:
    do
    {
      return localGlobal;
      if (paramInt < 10)
      {
        paramGlobal = "5 " + paramString2;
        break;
      }
      if (paramInt < 20)
      {
        paramGlobal = "4 " + paramString2;
        break;
      }
      if (paramInt < 30)
      {
        paramGlobal = "3 " + paramString2;
        break;
      }
      if (paramInt < 40)
      {
        paramGlobal = "2 " + paramString2;
        break;
      }
      if (paramInt < 50)
      {
        paramGlobal = "1 " + paramString1;
        break;
      }
      if (paramInt < 60)
      {
        paramGlobal = "1/2 " + paramString1;
        break;
      }
      if (paramInt < 65)
      {
        paramGlobal = "1/4 " + paramString1;
        break;
      }
      if (paramInt < 68)
      {
        paramGlobal = "1/5 " + paramString1;
        break;
      }
      if (paramInt < 70)
      {
        paramGlobal = "1/6 " + paramString1;
        break;
      }
      if (paramInt < 75)
      {
        paramGlobal = "1/8 " + paramString1;
        break;
      }
      if (paramInt < 80)
      {
        paramGlobal = "1/10 " + paramString1;
        break;
      }
      if (paramInt < 85)
      {
        paramGlobal = "1/12 " + paramString1;
        break;
      }
      if (paramInt < 90)
      {
        paramGlobal = "1/15 " + paramString1;
        break;
      }
      if (paramInt < 95)
      {
        paramGlobal = "1/18 " + paramString1;
        break;
      }
      if (paramInt < 100)
      {
        paramGlobal = "1/20 " + paramString1;
        break;
      }
      paramGlobal = "1/25 " + paramString1;
      break;
      if (paramInt < 40) {}
      for (;;)
      {
        return paramGlobal.replace(paramString2, "").trim();
        paramString2 = paramString1;
      }
      if (paramInt >= 5) {
        break label600;
      }
      paramGlobal = "20 " + paramString2;
      localGlobal = paramGlobal;
    } while (paramBoolean);
    if (paramInt >= 20) {}
    for (;;)
    {
      return paramGlobal.replace(paramString2, "").trim();
      label600:
      if (paramInt < 10)
      {
        paramGlobal = "15 " + paramString2;
        break;
      }
      if (paramInt < 20)
      {
        paramGlobal = "10 " + paramString2;
        break;
      }
      if (paramInt < 30)
      {
        paramGlobal = "8 " + paramString2;
        break;
      }
      if (paramInt < 40)
      {
        paramGlobal = "6 " + paramString2;
        break;
      }
      if (paramInt < 50)
      {
        paramGlobal = "5 " + paramString2;
        break;
      }
      if (paramInt < 60)
      {
        paramGlobal = "4 " + paramString2;
        break;
      }
      if (paramInt < 70)
      {
        paramGlobal = "3 " + paramString2;
        break;
      }
      if (paramInt < 80)
      {
        paramGlobal = "2 " + paramString2;
        break;
      }
      if (paramInt < 90)
      {
        paramGlobal = "1 " + paramString1;
        break;
      }
      if (paramInt < 95)
      {
        paramGlobal = "1/5 " + paramString1;
        break;
      }
      if (paramInt < 100)
      {
        paramGlobal = "1/10 " + paramString1;
        break;
      }
      paramGlobal = "1/25 " + paramString1;
      break;
      paramString2 = paramString1;
    }
  }
  
  public static String milliseconds2SecondString(int paramInt)
  {
    return String.format("%d.%d", new Object[] { Integer.valueOf(paramInt / 1000), Integer.valueOf(paramInt % 1000) });
  }
  
  public static String milliseconds2TimeString(int paramInt)
  {
    return String.format("%02d:%02d.%d", new Object[] { Integer.valueOf(paramInt / 1000 / 60), Integer.valueOf(paramInt / 1000 % 60), Integer.valueOf(paramInt % 1000 / 100) });
  }
  
  public static double parseFraction(String paramString)
  {
    if (paramString.contains("/"))
    {
      paramString = paramString.split("/");
      return Double.parseDouble(paramString[0]) / Double.parseDouble(paramString[1]);
    }
    return Double.parseDouble(paramString);
  }
  
  public static Bundle parseUrl(String paramString)
  {
    paramString = paramString.replace("fbconnect", "http");
    try
    {
      paramString = new URL(paramString);
      Bundle localBundle = decodeUrl(paramString.getQuery());
      localBundle.putAll(decodeUrl(paramString.getRef()));
      return localBundle;
    }
    catch (MalformedURLException paramString) {}
    return new Bundle();
  }
  
  public static int pxToDp(Resources paramResources, int paramInt)
  {
    paramResources = paramResources.getDisplayMetrics();
    return Math.round(paramInt / (paramResources.xdpi / 160.0F));
  }
  
  public static Bitmap rotateBitmap(Bitmap paramBitmap, int paramInt)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(paramInt);
    return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, false);
  }
  
  public static void saveBitmapToFile(Bitmap paramBitmap, File paramFile)
  {
    paramFile = new FileOutputStream(paramFile);
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 90, paramFile);
    paramFile.flush();
    paramFile.close();
    paramBitmap.recycle();
  }
  
  public static void saveBitmapToFileNoRecycle(Bitmap paramBitmap, File paramFile)
  {
    paramFile = new FileOutputStream(paramFile);
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 90, paramFile);
    paramFile.flush();
    paramFile.close();
  }
  
  public static void saveBitmapToPNG(Bitmap paramBitmap, File paramFile)
  {
    paramFile = new FileOutputStream(paramFile);
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 0, paramFile);
    paramFile.flush();
    paramFile.close();
  }
  
  public static void saveImageToGallery(Context paramContext, File paramFile)
  {
    Object localObject = new File(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), paramContext.getResources().getString(2131165592)), dateformat.format(new Date()) + ".jpg");
    try
    {
      copyFile(paramFile, (File)localObject);
      paramFile = ((File)localObject).toString();
      localObject = new UtilsPicPac.11();
      MediaScannerConnection.scanFile(paramContext, new String[] { paramFile }, null, (MediaScannerConnection.OnScanCompletedListener)localObject);
      ToastCustomed.makeText(paramContext, paramContext.getResources().getString(2131165451), 1).show();
      return;
    }
    catch (IOException paramFile)
    {
      paramFile.printStackTrace();
      ToastCustomed.makeText(paramContext, paramContext.getResources().getString(2131165446), 1).show();
    }
  }
  
  public static void saveProjectTitle(Global paramGlobal, String paramString)
  {
    try
    {
      paramGlobal = new PrintWriter(new File(paramGlobal.tempProjectFolder, "title.txt"));
      paramGlobal.println(paramString);
      paramGlobal.close();
      return;
    }
    catch (FileNotFoundException paramGlobal)
    {
      paramGlobal.printStackTrace();
    }
  }
  
  public static void scaleImageFile(File paramFile1, File paramFile2)
  {
    try
    {
      saveBitmapToFile(scaleImageToBitmap(paramFile1, 1800000), paramFile2);
      return;
    }
    catch (IOException paramFile1)
    {
      paramFile1.printStackTrace();
    }
  }
  
  public static Bitmap scaleImageToBitmap(File paramFile, int paramInt)
  {
    Object localObject = new BitmapFactory.Options();
    ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramFile.getAbsolutePath(), (BitmapFactory.Options)localObject);
    int i = 1;
    while (((BitmapFactory.Options)localObject).outWidth * ((BitmapFactory.Options)localObject).outHeight * (1.0D / Math.pow(i, 2.0D)) > paramInt) {
      i += 1;
    }
    if (i > 1)
    {
      localObject = new BitmapFactory.Options();
      ((BitmapFactory.Options)localObject).inSampleSize = (i - 1);
      paramFile = BitmapFactory.decodeFile(paramFile.getAbsolutePath(), (BitmapFactory.Options)localObject);
      i = paramFile.getHeight();
      int j = paramFile.getWidth();
      double d = Math.sqrt(paramInt / (j / i));
      localObject = Bitmap.createScaledBitmap(paramFile, (int)(d / i * j), (int)d, true);
      paramFile.recycle();
      System.gc();
      return localObject;
    }
    return BitmapFactory.decodeFile(paramFile.getAbsolutePath());
  }
  
  public static void setAlreadyRated(Activity paramActivity)
  {
    paramActivity = PreferenceManager.getDefaultSharedPreferences(paramActivity).edit();
    paramActivity.putBoolean("ALREADY_RATED", true);
    paramActivity.apply();
  }
  
  public static void setDirectedBy(Activity paramActivity, String paramString)
  {
    setPreference(paramActivity, "Directed By", paramString);
  }
  
  public static void setPreference(Context paramContext, String paramString, Object paramObject)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    if ((paramObject instanceof Boolean)) {
      paramContext.putBoolean(paramString, ((Boolean)paramObject).booleanValue());
    }
    if ((paramObject instanceof Float)) {
      paramContext.putFloat(paramString, ((Float)paramObject).floatValue());
    }
    if ((paramObject instanceof Integer)) {
      paramContext.putInt(paramString, ((Integer)paramObject).intValue());
    }
    if ((paramObject instanceof Long)) {
      paramContext.putLong(paramString, ((Long)paramObject).longValue());
    }
    if ((paramObject instanceof String)) {
      paramContext.putString(paramString, (String)paramObject);
    }
    if (paramObject == null) {
      paramContext.putString(paramString, null);
    }
    paramContext.apply();
  }
  
  public static void setUSeWatermark(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("USE_WATERMARK", paramBoolean);
    paramContext.apply();
  }
  
  public static void setUseAddingForFree(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("ADDING_FOR_FREE", paramBoolean);
    paramContext.apply();
  }
  
  public static void testPromoCode(ActivityIAPBase paramActivityIAPBase, String paramString)
  {
    Resources localResources = paramActivityIAPBase.getResources();
    ((c)i.a(paramActivityIAPBase).b("http://picpac.parseapp.com/redeem?code=" + paramString + "&appname=" + paramActivityIAPBase.getString(2131165592))).a().a(new UtilsPicPac.14(paramActivityIAPBase, localResources));
  }
  
  public static void updatePaste(ArrayList<File> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2)
  {
    if (paramInt1 > paramInt2)
    {
      paramArrayList.add(paramInt2, paramArrayList.get(paramInt1));
      paramArrayList.remove(paramInt1 + 1);
      paramArrayList1.add(paramInt2, paramArrayList1.get(paramInt1));
      paramArrayList1.remove(paramInt1 + 1);
    }
    while (paramInt1 >= paramInt2) {
      return;
    }
    paramArrayList.add(paramInt2, paramArrayList.get(paramInt1));
    paramArrayList.remove(paramInt1);
    paramArrayList1.add(paramInt2, paramArrayList1.get(paramInt1));
    paramArrayList1.remove(paramInt1);
  }
  
  public static void usePromoCode(ActivityIAPBase paramActivityIAPBase)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivityIAPBase);
    localBuilder.setTitle(paramActivityIAPBase.getResources().getString(2131165484));
    localBuilder.setMessage(paramActivityIAPBase.getResources().getString(2131165487));
    EditText localEditText = new EditText(paramActivityIAPBase);
    localEditText.setInputType(1);
    localEditText.setMaxLines(1);
    localEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(15) });
    localBuilder.setView(localEditText);
    localBuilder.setPositiveButton(2131165398, new UtilsPicPac.12(localEditText, paramActivityIAPBase));
    localBuilder.setNegativeButton(2131165280, new UtilsPicPac.13());
    localBuilder.show();
  }
}
