package com.mobileiron.common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;
import com.mobileiron.common.ab;
import com.mobileiron.common.ae;
import com.mobileiron.common.d;
import com.mobileiron.common.u;
import com.mobileiron.common.v;
import com.mobileiron.common.x;
import com.mobileiron.compliance.utils.b;
import com.mobileiron.config.ConfigMarshaller;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.ByteArrayBuffer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public final class aa
{
  public static v a(Integer paramInteger, boolean paramBoolean)
  {
    try
    {
      ByteArrayBuffer localByteArrayBuffer = a(paramInteger.intValue());
      paramInteger = a(localByteArrayBuffer, paramInteger.intValue());
      if (paramBoolean) {
        a(localByteArrayBuffer, paramInteger);
      }
      return paramInteger;
    }
    catch (Exception paramInteger)
    {
      paramInteger.printStackTrace();
      ae.b("MIAppsUtil", paramInteger.toString());
    }
    return null;
  }
  
  public static v a(ByteArrayBuffer paramByteArrayBuffer, int paramInt)
  {
    int j = 0;
    Object localObject = DocumentBuilderFactory.newInstance();
    v localV = new v();
    localV.b(paramInt);
    for (;;)
    {
      int i;
      try
      {
        paramByteArrayBuffer = ((DocumentBuilderFactory)localObject).newDocumentBuilder().parse(new ByteArrayInputStream(paramByteArrayBuffer.toByteArray())).getDocumentElement();
        if (paramByteArrayBuffer == null) {
          return null;
        }
        paramByteArrayBuffer = paramByteArrayBuffer.getElementsByTagName("key");
        i = 0;
        paramInt = j;
        if (i < paramByteArrayBuffer.getLength())
        {
          localObject = paramByteArrayBuffer.item(i);
          if (((Node)localObject).getNodeType() == 1)
          {
            localObject = (Element)localObject;
            String str = ((Element)localObject).getFirstChild().getNodeValue();
            if ((paramInt == 0) && (str.equalsIgnoreCase("Response"))) {
              paramInt = 1;
            } else if ((paramInt == 1) && (str.equalsIgnoreCase("AppDetail"))) {
              paramInt = 2;
            } else if (paramInt == 2) {
              a((Element)localObject, localV);
            }
          }
        }
      }
      catch (ParserConfigurationException paramByteArrayBuffer)
      {
        ae.b("MIAppsUtil", "Exception caught: " + paramByteArrayBuffer);
        return localV;
      }
      catch (SAXException paramByteArrayBuffer)
      {
        continue;
      }
      catch (IOException paramByteArrayBuffer)
      {
        continue;
      }
      i += 1;
    }
  }
  
  /* Error */
  @android.annotation.SuppressLint({"DefaultLocale"})
  public static File a(int paramInt, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: invokestatic 153	com/mobileiron/common/d:b	()Lcom/mobileiron/common/d;
    //   6: invokevirtual 157	com/mobileiron/common/d:c	()Landroid/content/Context;
    //   9: invokevirtual 163	android/content/Context:getFilesDir	()Ljava/io/File;
    //   12: invokevirtual 168	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   15: astore_2
    //   16: new 132	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   23: aload_2
    //   24: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc -82
    //   29: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: iload_0
    //   33: invokestatic 178	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   36: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: invokevirtual 142	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   42: astore_3
    //   43: new 165	java/io/File
    //   46: dup
    //   47: aload_2
    //   48: iload_0
    //   49: invokestatic 178	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   52: invokespecial 180	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   55: astore_2
    //   56: aload_2
    //   57: invokevirtual 184	java/io/File:exists	()Z
    //   60: ifne +17 -> 77
    //   63: aload_2
    //   64: invokevirtual 187	java/io/File:mkdirs	()Z
    //   67: ifne +10 -> 77
    //   70: ldc 29
    //   72: ldc -67
    //   74: invokestatic 39	com/mobileiron/common/ae:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   77: aload_1
    //   78: astore_2
    //   79: aload_1
    //   80: ldc -65
    //   82: invokevirtual 194	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   85: ifeq +30 -> 115
    //   88: aload_1
    //   89: iconst_0
    //   90: aload_1
    //   91: ldc -60
    //   93: invokevirtual 200	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   96: iconst_4
    //   97: iadd
    //   98: invokevirtual 204	java/lang/String:substring	(II)Ljava/lang/String;
    //   101: astore_1
    //   102: aload_1
    //   103: aload_1
    //   104: bipush 47
    //   106: invokevirtual 208	java/lang/String:lastIndexOf	(I)I
    //   109: iconst_1
    //   110: iadd
    //   111: invokevirtual 210	java/lang/String:substring	(I)Ljava/lang/String;
    //   114: astore_2
    //   115: new 165	java/io/File
    //   118: dup
    //   119: aload_3
    //   120: aload_2
    //   121: invokespecial 180	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   124: astore 6
    //   126: aload 6
    //   128: invokevirtual 184	java/io/File:exists	()Z
    //   131: ifeq +16 -> 147
    //   134: aload 6
    //   136: invokevirtual 214	java/io/File:length	()J
    //   139: lconst_0
    //   140: lcmp
    //   141: ifle +6 -> 147
    //   144: aload 6
    //   146: areturn
    //   147: ldc -40
    //   149: iconst_3
    //   150: anewarray 4	java/lang/Object
    //   153: dup
    //   154: iconst_0
    //   155: iconst_3
    //   156: invokestatic 219	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   159: aastore
    //   160: dup
    //   161: iconst_1
    //   162: iload_0
    //   163: invokestatic 219	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   166: aastore
    //   167: dup
    //   168: iconst_2
    //   169: aload_2
    //   170: aastore
    //   171: invokestatic 223	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   174: ldc -31
    //   176: invokestatic 228	com/mobileiron/common/utils/aa:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   179: astore_2
    //   180: aload_2
    //   181: iconst_1
    //   182: invokestatic 233	com/mobileiron/config/ConfigMarshaller:c	()Lcom/mobileiron/config/ConfigMarshaller;
    //   185: invokevirtual 237	com/mobileiron/config/ConfigMarshaller:f	()Lcom/mobileiron/common/x;
    //   188: invokevirtual 242	com/mobileiron/common/x:H	()Z
    //   191: invokestatic 247	com/mobileiron/common/utils/m:a	(Ljava/lang/String;ZZ)Ljava/io/InputStream;
    //   194: astore_1
    //   195: aload_1
    //   196: ifnonnull +34 -> 230
    //   199: ldc 29
    //   201: new 132	java/lang/StringBuilder
    //   204: dup
    //   205: ldc -7
    //   207: invokespecial 137	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   210: aload_2
    //   211: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: invokevirtual 142	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   217: invokestatic 39	com/mobileiron/common/ae:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   220: aload_1
    //   221: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   224: aconst_null
    //   225: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   228: aconst_null
    //   229: areturn
    //   230: new 256	java/io/FileOutputStream
    //   233: dup
    //   234: aload 6
    //   236: invokespecial 259	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   239: astore 4
    //   241: aload 4
    //   243: astore_3
    //   244: aload_1
    //   245: astore_2
    //   246: aload_1
    //   247: aload 4
    //   249: invokestatic 264	com/mobileiron/common/utils/r:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   252: aload_1
    //   253: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   256: aload 4
    //   258: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   261: aload 6
    //   263: areturn
    //   264: astore 5
    //   266: aconst_null
    //   267: astore 4
    //   269: aconst_null
    //   270: astore_1
    //   271: aload 4
    //   273: astore_3
    //   274: aload_1
    //   275: astore_2
    //   276: ldc 29
    //   278: aload 5
    //   280: invokevirtual 265	java/io/FileNotFoundException:toString	()Ljava/lang/String;
    //   283: invokestatic 267	com/mobileiron/common/ae:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   286: aload_1
    //   287: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   290: aload 4
    //   292: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   295: aconst_null
    //   296: areturn
    //   297: astore 5
    //   299: aconst_null
    //   300: astore 4
    //   302: aconst_null
    //   303: astore_1
    //   304: aload 4
    //   306: astore_3
    //   307: aload_1
    //   308: astore_2
    //   309: ldc 29
    //   311: aload 5
    //   313: invokevirtual 268	java/io/IOException:toString	()Ljava/lang/String;
    //   316: invokestatic 267	com/mobileiron/common/ae:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   319: aload_1
    //   320: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   323: aload 4
    //   325: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   328: aconst_null
    //   329: areturn
    //   330: astore_3
    //   331: aconst_null
    //   332: astore_1
    //   333: aload 5
    //   335: astore 4
    //   337: aload_1
    //   338: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   341: aload 4
    //   343: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   346: aload_3
    //   347: athrow
    //   348: astore_3
    //   349: aload 5
    //   351: astore 4
    //   353: goto -16 -> 337
    //   356: astore_1
    //   357: aload_3
    //   358: astore 4
    //   360: aload_1
    //   361: astore_3
    //   362: aload_2
    //   363: astore_1
    //   364: goto -27 -> 337
    //   367: astore 5
    //   369: aconst_null
    //   370: astore 4
    //   372: goto -68 -> 304
    //   375: astore 5
    //   377: goto -73 -> 304
    //   380: astore 5
    //   382: aconst_null
    //   383: astore 4
    //   385: goto -114 -> 271
    //   388: astore 5
    //   390: goto -119 -> 271
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	393	0	paramInt	int
    //   0	393	1	paramString	String
    //   15	348	2	localObject1	Object
    //   42	265	3	localObject2	Object
    //   330	17	3	localObject3	Object
    //   348	10	3	localObject4	Object
    //   361	1	3	str	String
    //   239	145	4	localObject5	Object
    //   1	1	5	localObject6	Object
    //   264	15	5	localFileNotFoundException1	java.io.FileNotFoundException
    //   297	53	5	localIOException1	IOException
    //   367	1	5	localIOException2	IOException
    //   375	1	5	localIOException3	IOException
    //   380	1	5	localFileNotFoundException2	java.io.FileNotFoundException
    //   388	1	5	localFileNotFoundException3	java.io.FileNotFoundException
    //   124	138	6	localFile	File
    // Exception table:
    //   from	to	target	type
    //   147	195	264	java/io/FileNotFoundException
    //   147	195	297	java/io/IOException
    //   147	195	330	finally
    //   199	220	348	finally
    //   230	241	348	finally
    //   246	252	356	finally
    //   276	286	356	finally
    //   309	319	356	finally
    //   199	220	367	java/io/IOException
    //   230	241	367	java/io/IOException
    //   246	252	375	java/io/IOException
    //   199	220	380	java/io/FileNotFoundException
    //   230	241	380	java/io/FileNotFoundException
    //   246	252	388	java/io/FileNotFoundException
  }
  
  public static File a(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramString != null)
    {
      if ((!paramBoolean) || (b(paramContext)))
      {
        if (paramBoolean)
        {
          paramContext = paramContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
          paramString = Uri.parse(paramString).getLastPathSegment();
          if (paramString == null) {
            break label88;
          }
        }
        label88:
        for (paramContext = new File(paramContext, paramString);; paramContext = null)
        {
          if (paramContext == null) {
            break label93;
          }
          ae.d("MIAppsUtil", "Local apk file: " + paramContext.getAbsolutePath());
          return paramContext;
          paramContext = paramContext.getFilesDir();
          break;
        }
        label93:
        ae.d("MIAppsUtil", "Invalid installation link. Returns null.");
        return paramContext;
      }
      ae.a("MIAppsUtil", "Installation folder does not exist. Returns null.");
      return null;
    }
    ae.b("MIAppsUtil", "Install link was not provided. Returns null.");
    return null;
  }
  
  /* Error */
  public static String a(Context paramContext, String paramString, u paramU)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: iconst_0
    //   4: istore_3
    //   5: aload_0
    //   6: invokestatic 273	com/mobileiron/common/utils/aa:b	(Landroid/content/Context;)Z
    //   9: ifne +13 -> 22
    //   12: ldc 29
    //   14: ldc_w 308
    //   17: invokestatic 267	com/mobileiron/common/ae:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   20: aconst_null
    //   21: areturn
    //   22: aload_0
    //   23: aload_1
    //   24: iconst_0
    //   25: invokestatic 310	com/mobileiron/common/utils/aa:a	(Landroid/content/Context;Ljava/lang/String;Z)Ljava/io/File;
    //   28: astore 11
    //   30: aload 11
    //   32: ifnonnull +13 -> 45
    //   35: ldc 29
    //   37: ldc_w 312
    //   40: invokestatic 267	com/mobileiron/common/ae:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   43: aconst_null
    //   44: areturn
    //   45: ldc 29
    //   47: new 132	java/lang/StringBuilder
    //   50: dup
    //   51: ldc_w 314
    //   54: invokespecial 137	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   57: aload 11
    //   59: invokevirtual 168	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   62: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 142	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: invokestatic 299	com/mobileiron/common/ae:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   71: new 165	java/io/File
    //   74: dup
    //   75: aload_0
    //   76: getstatic 279	android/os/Environment:DIRECTORY_DOWNLOADS	Ljava/lang/String;
    //   79: invokevirtual 283	android/content/Context:getExternalFilesDir	(Ljava/lang/String;)Ljava/io/File;
    //   82: aload 11
    //   84: invokevirtual 317	java/io/File:getName	()Ljava/lang/String;
    //   87: invokespecial 294	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   90: astore 12
    //   92: aload 12
    //   94: invokevirtual 168	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   97: astore 13
    //   99: ldc 29
    //   101: new 132	java/lang/StringBuilder
    //   104: dup
    //   105: ldc_w 319
    //   108: invokespecial 137	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   111: aload 13
    //   113: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 142	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: invokestatic 299	com/mobileiron/common/ae:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   122: new 321	java/io/FileInputStream
    //   125: dup
    //   126: aload 11
    //   128: invokespecial 322	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   131: astore_1
    //   132: new 256	java/io/FileOutputStream
    //   135: dup
    //   136: aload 12
    //   138: invokespecial 259	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   141: astore_0
    //   142: aload_0
    //   143: astore 10
    //   145: aload_1
    //   146: astore 9
    //   148: aload_1
    //   149: aload_0
    //   150: invokestatic 264	com/mobileiron/common/utils/r:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   153: aload_0
    //   154: astore 10
    //   156: aload_1
    //   157: astore 9
    //   159: aload 12
    //   161: invokevirtual 214	java/io/File:length	()J
    //   164: lstore 5
    //   166: aload_0
    //   167: astore 10
    //   169: aload_1
    //   170: astore 9
    //   172: aload 11
    //   174: invokevirtual 214	java/io/File:length	()J
    //   177: lstore 7
    //   179: lload 5
    //   181: lload 7
    //   183: lcmp
    //   184: ifne +5 -> 189
    //   187: iconst_1
    //   188: istore_3
    //   189: aload_1
    //   190: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   193: aload_0
    //   194: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   197: iload_3
    //   198: ifne +84 -> 282
    //   201: ldc 29
    //   203: new 132	java/lang/StringBuilder
    //   206: dup
    //   207: ldc_w 324
    //   210: invokespecial 137	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   213: aload 13
    //   215: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: invokevirtual 142	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   221: invokestatic 267	com/mobileiron/common/ae:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   224: aload 12
    //   226: invokestatic 327	com/mobileiron/common/utils/aa:a	(Ljava/io/File;)Z
    //   229: pop
    //   230: aconst_null
    //   231: areturn
    //   232: astore 11
    //   234: aconst_null
    //   235: astore_0
    //   236: aconst_null
    //   237: astore_1
    //   238: aload_0
    //   239: astore 10
    //   241: aload_1
    //   242: astore 9
    //   244: ldc 29
    //   246: aload 11
    //   248: invokestatic 330	com/mobileiron/common/ae:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   251: aload_1
    //   252: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   255: aload_0
    //   256: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   259: iload 4
    //   261: istore_3
    //   262: goto -65 -> 197
    //   265: astore_0
    //   266: aconst_null
    //   267: astore 10
    //   269: aconst_null
    //   270: astore_1
    //   271: aload_1
    //   272: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   275: aload 10
    //   277: invokestatic 254	com/mobileiron/common/utils/ad:a	(Ljava/io/Closeable;)V
    //   280: aload_0
    //   281: athrow
    //   282: aload_2
    //   283: ifnull +8 -> 291
    //   286: aload_2
    //   287: iconst_1
    //   288: invokevirtual 336	com/mobileiron/common/u:i	(Z)V
    //   291: ldc 29
    //   293: ldc_w 338
    //   296: invokestatic 299	com/mobileiron/common/ae:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   299: aload 13
    //   301: areturn
    //   302: astore_0
    //   303: aconst_null
    //   304: astore 10
    //   306: goto -35 -> 271
    //   309: astore_0
    //   310: aload 9
    //   312: astore_1
    //   313: goto -42 -> 271
    //   316: astore 11
    //   318: aconst_null
    //   319: astore_0
    //   320: goto -82 -> 238
    //   323: astore 11
    //   325: goto -87 -> 238
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	328	0	paramContext	Context
    //   0	328	1	paramString	String
    //   0	328	2	paramU	u
    //   4	258	3	i	int
    //   1	259	4	j	int
    //   164	16	5	l1	long
    //   177	5	7	l2	long
    //   146	165	9	str1	String
    //   143	162	10	localContext	Context
    //   28	145	11	localFile1	File
    //   232	15	11	localIOException1	IOException
    //   316	1	11	localIOException2	IOException
    //   323	1	11	localIOException3	IOException
    //   90	135	12	localFile2	File
    //   97	203	13	str2	String
    // Exception table:
    //   from	to	target	type
    //   122	132	232	java/io/IOException
    //   122	132	265	finally
    //   132	142	302	finally
    //   148	153	309	finally
    //   159	166	309	finally
    //   172	179	309	finally
    //   244	251	309	finally
    //   132	142	316	java/io/IOException
    //   148	153	323	java/io/IOException
    //   159	166	323	java/io/IOException
    //   172	179	323	java/io/IOException
  }
  
  public static String a(String paramString, Context paramContext)
  {
    Object localObject = null;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 1);
      paramString = localObject;
      if (paramContext != null) {
        paramString = paramContext.versionName;
      }
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  private static String a(String paramString1, String paramString2)
  {
    x localX = ConfigMarshaller.c().f();
    String str = localX.b("easi");
    return localX.b("easr").replace("$VARFOB$", str).replace("$VARARGS$", paramString1).replace("$VARFILE$", paramString2);
  }
  
  public static List a(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(8192);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo.applicationInfo.flags & 0x800000) != 0) {
        paramContext.add(localPackageInfo);
      }
    }
    return paramContext;
  }
  
  public static List a(PackageManager paramPackageManager)
  {
    int i = 0;
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      Object localObject = localArrayList;
      if (i < 10) {}
      try
      {
        localObject = paramPackageManager.getInstalledPackages(0);
        return localObject;
      }
      catch (Exception localException)
      {
        ae.b("MIAppsUtil", "Exception on calling getInstalledPackages() - retrying... " + localException);
        i += 1;
      }
    }
  }
  
  public static Vector a(ByteArrayBuffer paramByteArrayBuffer)
  {
    Vector localVector = new Vector();
    if (paramByteArrayBuffer == null) {
      return new Vector(localVector);
    }
    Object localObject = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    paramByteArrayBuffer = new ByteArrayInputStream(paramByteArrayBuffer.toByteArray());
    localObject = ((DocumentBuilder)localObject).parse(paramByteArrayBuffer).getDocumentElement().getElementsByTagName("array");
    if (((NodeList)localObject).getLength() == 0)
    {
      ae.d("MIAppsUtil", "No apps");
      ad.a(paramByteArrayBuffer);
      return new Vector(localVector);
    }
    localObject = ((Element)((NodeList)localObject).item(0)).getChildNodes();
    int i = 0;
    for (;;)
    {
      if (i < ((NodeList)localObject).getLength())
      {
        u localU = new u();
        if (((NodeList)localObject).item(i).getNodeType() == 1) {}
        try
        {
          a(((NodeList)localObject).item(i), localU);
          localVector.add(localU);
          i += 1;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            String str = localException.getMessage();
            if (str != null) {
              ae.b("MIAppsUtil", "Failed to parse one app" + str);
            }
          }
        }
      }
    }
    ad.a(paramByteArrayBuffer);
    return new Vector(localVector);
  }
  
  public static ByteArrayBuffer a()
  {
    String str = a("1", "easa.html");
    long l1 = System.currentTimeMillis();
    ae.c("MIAppsUtil", "APP: getting app list - " + str);
    localObject2 = m.a(str, true, ConfigMarshaller.c().f().H());
    long l2 = System.currentTimeMillis();
    ae.c("MIAppsUtil", "APP: getting app list took " + (l2 - l1) + " ms");
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(1024);
    if (localObject2 == null) {
      ae.b("MIAppsUtil", "APP: Failed to get applist:" + str);
    }
    do
    {
      return localByteArrayBuffer;
      try
      {
        r.a((InputStream)localObject2, localByteArrayBuffer);
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          ae.b("MIAppsUtil", "APP: " + localIOException.toString());
          ad.a((Closeable)localObject2);
        }
      }
      finally
      {
        ad.a((Closeable)localObject2);
      }
    } while ((!ae.c()) || (localByteArrayBuffer.length() <= 0));
    try
    {
      localObject2 = new String(localByteArrayBuffer.toByteArray(), "UTF-8");
      ae.c("MIAppsUtil", "APP: URL: " + str + " \n Response: " + (String)localObject2);
      return localByteArrayBuffer;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      ae.b("MIAppsUtil", "App exception: " + localUnsupportedEncodingException.toString());
      localUnsupportedEncodingException.printStackTrace();
      return localByteArrayBuffer;
    }
  }
  
  public static ByteArrayBuffer a(int paramInt)
  {
    String str = a("2/" + paramInt, "easa.html");
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(1024);
    localObject2 = m.a(str, true, ConfigMarshaller.c().f().H());
    if (localObject2 == null) {
      ae.b("MIAppsUtil", "Failed to get app details:" + str);
    }
    do
    {
      return localByteArrayBuffer;
      try
      {
        r.a((InputStream)localObject2, localByteArrayBuffer);
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          ae.b("MIAppsUtil", localIOException.toString());
          ad.a((Closeable)localObject2);
        }
      }
      finally
      {
        ad.a((Closeable)localObject2);
      }
    } while ((!ae.c()) || (localByteArrayBuffer.length() <= 0));
    localObject2 = new String(localByteArrayBuffer.toByteArray());
    ae.e("MIAppsUtil", "URL: " + str + " \n Response: " + (String)localObject2);
    return localByteArrayBuffer;
  }
  
  public static void a(Context paramContext, String paramString)
  {
    a(new File(paramContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), paramString));
    a(new File(paramContext.getFilesDir(), paramString));
  }
  
  public static void a(PackageInfo paramPackageInfo, u paramU)
  {
    PackageManager localPackageManager = d.b().c().getPackageManager();
    paramU.c(paramPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
    paramU.a(paramPackageInfo.packageName);
    paramU.e(paramPackageInfo.versionName);
  }
  
  public static void a(ImageView paramImageView, u paramU)
  {
    s.a().a(paramImageView, paramU);
  }
  
  /* Error */
  public static void a(ByteArrayBuffer paramByteArrayBuffer, v paramV)
  {
    // Byte code:
    //   0: invokestatic 52	javax/xml/parsers/DocumentBuilderFactory:newInstance	()Ljavax/xml/parsers/DocumentBuilderFactory;
    //   3: astore_3
    //   4: aload_3
    //   5: invokevirtual 64	javax/xml/parsers/DocumentBuilderFactory:newDocumentBuilder	()Ljavax/xml/parsers/DocumentBuilder;
    //   8: new 66	java/io/ByteArrayInputStream
    //   11: dup
    //   12: aload_0
    //   13: invokevirtual 72	org/apache/http/util/ByteArrayBuffer:toByteArray	()[B
    //   16: invokespecial 75	java/io/ByteArrayInputStream:<init>	([B)V
    //   19: invokevirtual 81	javax/xml/parsers/DocumentBuilder:parse	(Ljava/io/InputStream;)Lorg/w3c/dom/Document;
    //   22: invokeinterface 87 1 0
    //   27: ldc_w 423
    //   30: invokeinterface 95 2 0
    //   35: iconst_0
    //   36: invokeinterface 104 2 0
    //   41: checkcast 91	org/w3c/dom/Element
    //   44: invokeinterface 429 1 0
    //   49: astore_0
    //   50: iconst_0
    //   51: istore_2
    //   52: iload_2
    //   53: aload_0
    //   54: invokeinterface 100 1 0
    //   59: if_icmpge +96 -> 155
    //   62: new 526	com/mobileiron/common/ab
    //   65: dup
    //   66: invokespecial 527	com/mobileiron/common/ab:<init>	()V
    //   69: astore_3
    //   70: aload_0
    //   71: iload_2
    //   72: invokeinterface 104 2 0
    //   77: invokeinterface 110 1 0
    //   82: iconst_1
    //   83: if_icmpne +43 -> 126
    //   86: aload_0
    //   87: iload_2
    //   88: invokeinterface 104 2 0
    //   93: aload_3
    //   94: invokestatic 530	com/mobileiron/common/utils/aa:a	(Lorg/w3c/dom/Node;Lcom/mobileiron/common/ab;)V
    //   97: aload_1
    //   98: invokevirtual 534	com/mobileiron/common/v:s	()Ljava/util/List;
    //   101: ifnonnull +14 -> 115
    //   104: aload_1
    //   105: new 380	java/util/ArrayList
    //   108: dup
    //   109: invokespecial 381	java/util/ArrayList:<init>	()V
    //   112: invokevirtual 537	com/mobileiron/common/v:a	(Ljava/util/List;)V
    //   115: aload_1
    //   116: invokevirtual 534	com/mobileiron/common/v:s	()Ljava/util/List;
    //   119: aload_3
    //   120: invokeinterface 411 2 0
    //   125: pop
    //   126: iload_2
    //   127: iconst_1
    //   128: iadd
    //   129: istore_2
    //   130: goto -78 -> 52
    //   133: astore_0
    //   134: ldc 29
    //   136: new 132	java/lang/StringBuilder
    //   139: dup
    //   140: ldc -122
    //   142: invokespecial 137	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   145: aload_0
    //   146: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   149: invokevirtual 142	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   152: invokestatic 39	com/mobileiron/common/ae:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   155: return
    //   156: astore_0
    //   157: goto -23 -> 134
    //   160: astore_0
    //   161: goto -27 -> 134
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	164	0	paramByteArrayBuffer	ByteArrayBuffer
    //   0	164	1	paramV	v
    //   51	79	2	i	int
    //   3	117	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   4	50	133	javax/xml/parsers/ParserConfigurationException
    //   52	115	133	javax/xml/parsers/ParserConfigurationException
    //   115	126	133	javax/xml/parsers/ParserConfigurationException
    //   4	50	156	org/xml/sax/SAXException
    //   52	115	156	org/xml/sax/SAXException
    //   115	126	156	org/xml/sax/SAXException
    //   4	50	160	java/io/IOException
    //   52	115	160	java/io/IOException
    //   115	126	160	java/io/IOException
  }
  
  private static void a(Element paramElement, v paramV)
  {
    String str1 = null;
    String str2 = paramElement.getTextContent();
    paramElement = paramElement.getNextSibling();
    if (paramElement != null) {
      if (paramElement.getNodeType() == 1)
      {
        paramElement = (Element)paramElement;
        str1 = paramElement.getTextContent();
      }
    }
    for (paramElement = paramElement.getNodeName();; paramElement = null)
    {
      if (str2.equalsIgnoreCase("Name")) {
        paramV.c(str1);
      }
      do
      {
        return;
        paramElement = paramElement.getNextSibling();
        break;
        if (str2.equalsIgnoreCase("CatalogId"))
        {
          paramV.b(Integer.parseInt(str1));
          return;
        }
        if (str2.equalsIgnoreCase("Category"))
        {
          paramV.d(str1);
          return;
        }
        if (str2.equalsIgnoreCase("FileSize"))
        {
          paramV.a(Long.parseLong(str1));
          return;
        }
        if (str2.equalsIgnoreCase("Description"))
        {
          paramV.h(str1);
          return;
        }
        if (str2.equalsIgnoreCase("InHouse"))
        {
          paramV.d(a(paramElement));
          return;
        }
        if (str2.equalsIgnoreCase("isMIZoneApp"))
        {
          paramV.e(a(paramElement));
          return;
        }
        if (str2.equalsIgnoreCase("Installable"))
        {
          paramV.c(a(paramElement));
          return;
        }
        if (str2.equalsIgnoreCase("Installed"))
        {
          paramV.b(a(paramElement));
          return;
        }
        if (str2.equalsIgnoreCase("InstallLink"))
        {
          paramV.g(str1);
          return;
        }
        if (str2.equalsIgnoreCase("PackageName"))
        {
          paramV.a(str1);
          return;
        }
        if (str2.equalsIgnoreCase("Update"))
        {
          paramV.a(a(paramElement));
          return;
        }
        if (str2.equalsIgnoreCase("Version"))
        {
          paramV.e(str1);
          return;
        }
      } while (!str2.equalsIgnoreCase("IconLink"));
      paramV.b(str1);
      return;
    }
  }
  
  private static void a(Node paramNode, ab paramAb)
  {
    paramNode = (Element)paramNode;
    int i;
    int j;
    if (paramNode.getNodeName() != null)
    {
      NodeList localNodeList = paramNode.getChildNodes();
      paramNode = null;
      i = 0;
      while (i < localNodeList.getLength())
      {
        localObject = localNodeList.item(i);
        j = i + 1;
        i = j;
        if (((Node)localObject).getNodeType() == 1) {
          if (paramNode == null)
          {
            paramNode = ((Element)localObject).getFirstChild().getNodeValue();
            i = j;
          }
          else
          {
            localObject = ((Element)localObject).getFirstChild();
            if (localObject == null) {
              break label205;
            }
          }
        }
      }
    }
    label205:
    for (Object localObject = ((Node)localObject).getNodeValue();; localObject = null)
    {
      if (paramNode.equalsIgnoreCase("height")) {
        paramAb.c(Integer.parseInt((String)localObject));
      }
      for (;;)
      {
        paramNode = null;
        i = j;
        break;
        if (paramNode.equalsIgnoreCase("width")) {
          paramAb.b(Integer.parseInt((String)localObject));
        } else if (paramNode.equalsIgnoreCase("usage")) {
          paramAb.a(Integer.parseInt((String)localObject));
        } else if (paramNode.equalsIgnoreCase("link")) {
          paramAb.a((String)localObject);
        }
      }
      return;
    }
  }
  
  private static void a(Node paramNode, u paramU)
  {
    paramNode = (Element)paramNode;
    if (paramNode.getNodeName() != null)
    {
      NodeList localNodeList = paramNode.getChildNodes();
      paramNode = null;
      int i = 0;
      while (i < localNodeList.getLength())
      {
        Object localObject = localNodeList.item(i);
        int j = i + 1;
        i = j;
        if (((Node)localObject).getNodeType() == 1) {
          if (paramNode == null)
          {
            paramNode = ((Element)localObject).getFirstChild().getNodeValue();
            i = j;
          }
          else
          {
            localObject = (Element)localObject;
            String str = ((Element)localObject).getTextContent();
            if (paramNode.equalsIgnoreCase("Name")) {
              paramU.c(str);
            }
            for (;;)
            {
              paramNode = null;
              i = j;
              break;
              if (paramNode.equalsIgnoreCase("CatalogId")) {
                paramU.b(Integer.parseInt(str));
              } else if (paramNode.equalsIgnoreCase("Category")) {
                paramU.d(str);
              } else if (paramNode.equalsIgnoreCase("Featured")) {
                paramU.f(a(((Element)localObject).getNodeName()));
              } else if (paramNode.equalsIgnoreCase("IconLink")) {
                paramU.b(str);
              } else if (paramNode.equalsIgnoreCase("InHouse")) {
                paramU.d(a(((Element)localObject).getNodeName()));
              } else if (paramNode.equalsIgnoreCase("isMIZoneApp"))
              {
                if (a(((Element)localObject).getNodeName()))
                {
                  paramU.e(true);
                  if (b.m() < 70) {
                    paramU.h(true);
                  }
                }
              }
              else if (paramNode.equalsIgnoreCase("Installable")) {
                paramU.c(a(((Element)localObject).getNodeName()));
              } else if (paramNode.equalsIgnoreCase("Installed")) {
                paramU.b(a(((Element)localObject).getNodeName()));
              } else if (paramNode.equalsIgnoreCase("InstallLink")) {
                paramU.g(str);
              } else if (paramNode.equalsIgnoreCase("VersionCode")) {
                paramU.a(Integer.parseInt(str));
              } else if (paramNode.equalsIgnoreCase("PackageName")) {
                paramU.a(str);
              } else if (paramNode.equalsIgnoreCase("Update")) {
                paramU.a(a(((Element)localObject).getNodeName()));
              } else if (paramNode.equalsIgnoreCase("Version")) {
                paramU.e(str);
              } else if (paramNode.equalsIgnoreCase("silentInstall"))
              {
                if (a(((Element)localObject).getNodeName()))
                {
                  paramU.g(b.c());
                  if (b.m() < 70) {
                    paramU.h(true);
                  }
                }
              }
              else if (paramNode.equalsIgnoreCase("isMandatory")) {
                paramU.h(a(((Element)localObject).getNodeName()));
              } else if (paramNode.equalsIgnoreCase("FileSize")) {
                paramU.a(Long.parseLong(str));
              } else if (paramNode.equalsIgnoreCase("vpnUuid")) {
                paramU.f(str);
              } else if (paramNode.equalsIgnoreCase("isVersionRequired")) {
                paramU.j(a(((Element)localObject).getNodeName()));
              }
            }
          }
        }
      }
    }
  }
  
  public static boolean a(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists()))
    {
      ae.d("MIAppsUtil", "Delete apk file " + paramFile.getAbsolutePath());
      if (!paramFile.delete()) {
        ae.b("MIAppsUtil", "Failed to delete apk file");
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  private static boolean a(String paramString)
  {
    return (paramString.equalsIgnoreCase("true")) || (paramString.equalsIgnoreCase("1"));
  }
  
  public static ByteArrayBuffer b()
  {
    String str = a("16", "easa.html");
    long l1 = System.currentTimeMillis();
    ae.c("MIAppsUtil", "APP: getting secure app list - " + str);
    Object localObject1 = m.a(str, ConfigMarshaller.c().f().H());
    if ((localObject1 != null) && (((HttpResponse)localObject1).getStatusLine().getStatusCode() == 401)) {
      localObject1 = null;
    }
    ByteArrayBuffer localByteArrayBuffer;
    do
    {
      do
      {
        return localObject1;
        localObject1 = m.a((HttpResponse)localObject1);
        long l2 = System.currentTimeMillis();
        ae.c("MIAppsUtil", "APP: getting app list took " + (l2 - l1) + " ms");
        localByteArrayBuffer = new ByteArrayBuffer(1024);
        if (localObject1 == null)
        {
          ae.b("MIAppsUtil", "APP: Failed to get secureapplist:" + str);
          return localByteArrayBuffer;
        }
        try
        {
          r.a((InputStream)localObject1, localByteArrayBuffer);
          ad.a((Closeable)localObject1);
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            ae.b("MIAppsUtil", "APP: " + localIOException.toString());
            ad.a(localUnsupportedEncodingException);
          }
        }
        finally
        {
          ad.a(localUnsupportedEncodingException);
        }
        localObject1 = localByteArrayBuffer;
      } while (!ae.c());
      localObject1 = localByteArrayBuffer;
    } while (localByteArrayBuffer.length() <= 0);
    try
    {
      localObject1 = new String(localByteArrayBuffer.toByteArray(), "UTF-8");
      ae.c("MIAppsUtil", "APP: URL: " + str + " \n Response: " + (String)localObject1);
      return localByteArrayBuffer;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      ae.b("MIAppsUtil", "App exception: " + localUnsupportedEncodingException.toString());
      localUnsupportedEncodingException.printStackTrace();
      return localByteArrayBuffer;
    }
  }
  
  public static boolean b(Context paramContext)
  {
    File localFile = paramContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
    if ((localFile == null) || (!localFile.exists()))
    {
      if (localFile == null) {
        break label100;
      }
      paramContext = paramContext.getExternalFilesDir(null);
      if ((paramContext == null) || (!paramContext.exists())) {
        break label90;
      }
      if (localFile.mkdir()) {
        ae.d("MIAppsUtil", "Installation dir was successfully created");
      }
    }
    else
    {
      return true;
    }
    ae.a("MIAppsUtil", "Could not create installation folder '" + localFile.getAbsolutePath() + "'");
    return false;
    label90:
    ae.a("MIAppsUtil", "Failed to get external files root folder's file object");
    return false;
    label100:
    ae.a("MIAppsUtil", "Failed to get installation folder's file object");
    return false;
  }
  
  public static boolean b(String paramString, Context paramContext)
  {
    try
    {
      paramString = paramContext.getPackageManager().getPackageInfo(paramString, 1);
      if ((paramString != null) && (paramString.applicationInfo != null))
      {
        int i = paramString.applicationInfo.flags;
        return (i & 0x80) != 0;
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      ae.a("MIAppsUtil", "NameNotFoundException caught");
      paramString.printStackTrace();
    }
    return false;
  }
}
