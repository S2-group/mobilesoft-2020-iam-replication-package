package com.apalon.adstore;

import android.util.Base64;
import android.util.Log;
import com.apalon.adstore.a.d;
import com.apalon.adstore.data.AdModel;
import com.apalon.adstore.data.AdStoreResponse;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public final class b
  implements com.apalon.adstore.a.e
{
  public c a;
  public d b;
  AdStoreResponse c;
  private Locale d = Locale.getDefault();
  private Map<String, String> e;
  private Set<String> f;
  
  public b() {}
  
  public final AdModel a(int paramInt)
  {
    if ((this.c != null) && (this.c.data != null) && (this.c.data.size() > paramInt)) {
      return (AdModel)this.c.data.get(paramInt);
    }
    return null;
  }
  
  /* Error */
  public final void a(android.content.Context paramContext)
  {
    // Byte code:
    //   0: ldc 71
    //   2: iconst_3
    //   3: invokestatic 77	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   6: ifeq +11 -> 17
    //   9: ldc 71
    //   11: ldc 79
    //   13: invokestatic 82	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   16: pop
    //   17: aload_1
    //   18: invokevirtual 88	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   21: iconst_0
    //   22: invokevirtual 94	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   25: astore_3
    //   26: aload_0
    //   27: new 96	java/util/HashSet
    //   30: dup
    //   31: aload_3
    //   32: invokeinterface 48 1 0
    //   37: invokespecial 99	java/util/HashSet:<init>	(I)V
    //   40: putfield 101	com/apalon/adstore/b:f	Ljava/util/Set;
    //   43: aload_3
    //   44: invokeinterface 105 1 0
    //   49: astore_3
    //   50: aload_3
    //   51: invokeinterface 111 1 0
    //   56: ifeq +32 -> 88
    //   59: aload_3
    //   60: invokeinterface 115 1 0
    //   65: checkcast 117	android/content/pm/ApplicationInfo
    //   68: astore 4
    //   70: aload_0
    //   71: getfield 101	com/apalon/adstore/b:f	Ljava/util/Set;
    //   74: aload 4
    //   76: getfield 121	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   79: invokeinterface 127 2 0
    //   84: pop
    //   85: goto -35 -> 50
    //   88: aload_0
    //   89: getfield 32	com/apalon/adstore/b:d	Ljava/util/Locale;
    //   92: ldc -127
    //   94: iconst_1
    //   95: anewarray 4	java/lang/Object
    //   98: dup
    //   99: iconst_0
    //   100: aload_0
    //   101: getfield 32	com/apalon/adstore/b:d	Ljava/util/Locale;
    //   104: aastore
    //   105: invokestatic 135	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   108: astore_3
    //   109: aload_3
    //   110: ldc -119
    //   112: invokestatic 142	com/apalon/adstore/a/a:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   115: astore 4
    //   117: ldc -112
    //   119: invokestatic 149	c/ac:e	(Ljava/lang/String;)Lc/ac;
    //   122: invokevirtual 153	c/ac:g	()Lc/ad;
    //   125: astore_3
    //   126: aload_3
    //   127: ldc -101
    //   129: iconst_0
    //   130: ldc -101
    //   132: invokevirtual 158	java/lang/String:length	()I
    //   135: iconst_0
    //   136: invokevirtual 163	c/ad:a	(Ljava/lang/String;IIZ)V
    //   139: aload_3
    //   140: ldc -92
    //   142: aload 4
    //   144: invokevirtual 167	c/ad:a	(Ljava/lang/String;Ljava/lang/String;)Lc/ad;
    //   147: pop
    //   148: aload_0
    //   149: getfield 169	com/apalon/adstore/b:e	Ljava/util/Map;
    //   152: ifnull +96 -> 248
    //   155: aload_0
    //   156: getfield 169	com/apalon/adstore/b:e	Ljava/util/Map;
    //   159: invokeinterface 175 1 0
    //   164: invokeinterface 176 1 0
    //   169: astore 4
    //   171: aload 4
    //   173: invokeinterface 111 1 0
    //   178: ifeq +70 -> 248
    //   181: aload 4
    //   183: invokeinterface 115 1 0
    //   188: checkcast 178	java/util/Map$Entry
    //   191: astore 5
    //   193: aload_3
    //   194: aload 5
    //   196: invokeinterface 181 1 0
    //   201: checkcast 131	java/lang/String
    //   204: aload 5
    //   206: invokeinterface 184 1 0
    //   211: checkcast 131	java/lang/String
    //   214: ldc -119
    //   216: invokestatic 142	com/apalon/adstore/a/a:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   219: invokevirtual 167	c/ad:a	(Ljava/lang/String;Ljava/lang/String;)Lc/ad;
    //   222: pop
    //   223: goto -52 -> 171
    //   226: astore_1
    //   227: ldc 71
    //   229: bipush 6
    //   231: invokestatic 77	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   234: ifeq +13 -> 247
    //   237: ldc 71
    //   239: aload_1
    //   240: invokevirtual 190	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   243: invokestatic 192	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   246: pop
    //   247: return
    //   248: aload_0
    //   249: aload_1
    //   250: invokestatic 197	com/apalon/adstore/a/d:a	(Landroid/content/Context;)Lcom/apalon/adstore/a/d;
    //   253: putfield 199	com/apalon/adstore/b:b	Lcom/apalon/adstore/a/d;
    //   256: aload_0
    //   257: getfield 199	com/apalon/adstore/b:b	Lcom/apalon/adstore/a/d;
    //   260: aload_0
    //   261: putfield 202	com/apalon/adstore/a/d:d	Lcom/apalon/adstore/a/e;
    //   264: aload_0
    //   265: getfield 199	com/apalon/adstore/b:b	Lcom/apalon/adstore/a/d;
    //   268: astore_1
    //   269: aload_3
    //   270: invokevirtual 205	c/ad:b	()Lc/ac;
    //   273: astore_3
    //   274: new 207	c/as
    //   277: dup
    //   278: invokespecial 208	c/as:<init>	()V
    //   281: aload_3
    //   282: invokevirtual 211	c/as:a	(Lc/ac;)Lc/as;
    //   285: astore_3
    //   286: aload_1
    //   287: getfield 214	com/apalon/adstore/a/d:a	Landroid/net/ConnectivityManager;
    //   290: invokevirtual 220	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   293: astore 4
    //   295: aload 4
    //   297: ifnull +116 -> 413
    //   300: aload 4
    //   302: invokevirtual 225	android/net/NetworkInfo:isConnected	()Z
    //   305: ifeq +108 -> 413
    //   308: iconst_1
    //   309: istore_2
    //   310: iload_2
    //   311: ifne +26 -> 337
    //   314: getstatic 230	c/g:b	Lc/g;
    //   317: invokevirtual 233	c/g:toString	()Ljava/lang/String;
    //   320: astore 4
    //   322: aload 4
    //   324: invokevirtual 236	java/lang/String:isEmpty	()Z
    //   327: ifeq +50 -> 377
    //   330: aload_3
    //   331: ldc -18
    //   333: invokevirtual 241	c/as:b	(Ljava/lang/String;)Lc/as;
    //   336: pop
    //   337: aload_3
    //   338: invokevirtual 244	c/as:a	()Lc/ar;
    //   341: astore_3
    //   342: aload_1
    //   343: getfield 247	com/apalon/adstore/a/d:b	Lc/al;
    //   346: aload_3
    //   347: invokevirtual 252	c/al:a	(Lc/ar;)Lc/i;
    //   350: astore_3
    //   351: aload_1
    //   352: getfield 254	com/apalon/adstore/a/d:c	Ljava/util/List;
    //   355: aload_3
    //   356: invokeinterface 255 2 0
    //   361: pop
    //   362: aload_3
    //   363: new 257	com/apalon/adstore/a/d$1
    //   366: dup
    //   367: aload_1
    //   368: invokespecial 260	com/apalon/adstore/a/d$1:<init>	(Lcom/apalon/adstore/a/d;)V
    //   371: invokeinterface 265 2 0
    //   376: return
    //   377: aload_3
    //   378: ldc -18
    //   380: aload 4
    //   382: invokevirtual 268	c/as:a	(Ljava/lang/String;Ljava/lang/String;)Lc/as;
    //   385: pop
    //   386: goto -49 -> 337
    //   389: astore_1
    //   390: goto -163 -> 227
    //   393: astore_1
    //   394: goto -167 -> 227
    //   397: astore_1
    //   398: goto -171 -> 227
    //   401: astore_1
    //   402: goto -175 -> 227
    //   405: astore_1
    //   406: goto -179 -> 227
    //   409: astore_1
    //   410: goto -183 -> 227
    //   413: iconst_0
    //   414: istore_2
    //   415: goto -105 -> 310
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	418	0	this	b
    //   0	418	1	paramContext	android.content.Context
    //   309	106	2	i	int
    //   25	353	3	localObject1	Object
    //   68	313	4	localObject2	Object
    //   191	14	5	localEntry	java.util.Map.Entry
    // Exception table:
    //   from	to	target	type
    //   109	171	226	java/io/UnsupportedEncodingException
    //   171	223	226	java/io/UnsupportedEncodingException
    //   248	295	226	java/io/UnsupportedEncodingException
    //   300	308	226	java/io/UnsupportedEncodingException
    //   314	337	226	java/io/UnsupportedEncodingException
    //   337	376	226	java/io/UnsupportedEncodingException
    //   377	386	226	java/io/UnsupportedEncodingException
    //   109	171	389	javax/crypto/IllegalBlockSizeException
    //   171	223	389	javax/crypto/IllegalBlockSizeException
    //   248	295	389	javax/crypto/IllegalBlockSizeException
    //   300	308	389	javax/crypto/IllegalBlockSizeException
    //   314	337	389	javax/crypto/IllegalBlockSizeException
    //   337	376	389	javax/crypto/IllegalBlockSizeException
    //   377	386	389	javax/crypto/IllegalBlockSizeException
    //   109	171	393	javax/crypto/NoSuchPaddingException
    //   171	223	393	javax/crypto/NoSuchPaddingException
    //   248	295	393	javax/crypto/NoSuchPaddingException
    //   300	308	393	javax/crypto/NoSuchPaddingException
    //   314	337	393	javax/crypto/NoSuchPaddingException
    //   337	376	393	javax/crypto/NoSuchPaddingException
    //   377	386	393	javax/crypto/NoSuchPaddingException
    //   109	171	397	java/security/NoSuchAlgorithmException
    //   171	223	397	java/security/NoSuchAlgorithmException
    //   248	295	397	java/security/NoSuchAlgorithmException
    //   300	308	397	java/security/NoSuchAlgorithmException
    //   314	337	397	java/security/NoSuchAlgorithmException
    //   337	376	397	java/security/NoSuchAlgorithmException
    //   377	386	397	java/security/NoSuchAlgorithmException
    //   109	171	401	java/security/InvalidAlgorithmParameterException
    //   171	223	401	java/security/InvalidAlgorithmParameterException
    //   248	295	401	java/security/InvalidAlgorithmParameterException
    //   300	308	401	java/security/InvalidAlgorithmParameterException
    //   314	337	401	java/security/InvalidAlgorithmParameterException
    //   337	376	401	java/security/InvalidAlgorithmParameterException
    //   377	386	401	java/security/InvalidAlgorithmParameterException
    //   109	171	405	java/security/InvalidKeyException
    //   171	223	405	java/security/InvalidKeyException
    //   248	295	405	java/security/InvalidKeyException
    //   300	308	405	java/security/InvalidKeyException
    //   314	337	405	java/security/InvalidKeyException
    //   337	376	405	java/security/InvalidKeyException
    //   377	386	405	java/security/InvalidKeyException
    //   109	171	409	javax/crypto/BadPaddingException
    //   171	223	409	javax/crypto/BadPaddingException
    //   248	295	409	javax/crypto/BadPaddingException
    //   300	308	409	javax/crypto/BadPaddingException
    //   314	337	409	javax/crypto/BadPaddingException
    //   337	376	409	javax/crypto/BadPaddingException
    //   377	386	409	javax/crypto/BadPaddingException
  }
  
  public final void a(Exception paramException)
  {
    if (Log.isLoggable("AdStore", 5)) {
      Log.w("AdStore", "onFailure");
    }
    if (this.a != null) {
      this.a.a(paramException);
    }
  }
  
  public final void a(String paramString)
  {
    if (Log.isLoggable("AdStore", 3)) {
      Log.d("AdStore", "onSuccess");
    }
    try
    {
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(Arrays.copyOf("2b9Y2ca38NrUwm6z".getBytes("UTF-8"), 16), "AES");
      Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      localCipher.init(2, localSecretKeySpec);
      paramString = new String(localCipher.doFinal(Base64.decode(paramString, 0)), "UTF-8");
      this.c = ((AdStoreResponse)new com.google.a.e().a(paramString, AdStoreResponse.class));
      this.c.filterResults(this.f);
      if (this.a != null) {
        this.a.a(this.c.data);
      }
      return;
    }
    catch (NoSuchPaddingException paramString)
    {
      do
      {
        if (Log.isLoggable("AdStore", 6)) {
          Log.e("AdStore", paramString.getMessage());
        }
      } while (this.a == null);
      this.a.a(paramString);
      return;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      for (;;) {}
    }
    catch (InvalidAlgorithmParameterException paramString)
    {
      for (;;) {}
    }
    catch (IllegalStateException paramString)
    {
      for (;;) {}
    }
    catch (IllegalBlockSizeException paramString)
    {
      for (;;) {}
    }
    catch (BadPaddingException paramString)
    {
      for (;;) {}
    }
    catch (UnsupportedEncodingException paramString)
    {
      for (;;) {}
    }
    catch (InvalidKeyException paramString)
    {
      for (;;) {}
    }
  }
}
