import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import java.util.Iterator;
import java.util.List;

public class cnt
{
  public static final String a = cnt.class.getSimpleName();
  private static boolean b = false;
  private static boolean c = false;
  
  private cnt() {}
  
  private static void a(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setMessage(paramInt1).setTitle(bcx.er).setPositiveButton(paramInt2, paramOnClickListener).setNegativeButton(bcx.en, new cnw());
    paramContext.create().show();
  }
  
  public static boolean a()
  {
    return ("N".equals(Build.VERSION.CODENAME)) || (Build.VERSION.SDK_INT > 23);
  }
  
  public static boolean a(Activity paramActivity, boolean paramBoolean)
  {
    return a(paramActivity, paramBoolean, 1);
  }
  
  /* Error */
  private static boolean a(Activity paramActivity, boolean paramBoolean, int paramInt)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: invokestatic 99	cnt:a	()Z
    //   5: ifne +5 -> 10
    //   8: iconst_0
    //   9: ireturn
    //   10: ldc 101
    //   12: ldc 103
    //   14: iconst_2
    //   15: anewarray 13	java/lang/Class
    //   18: dup
    //   19: iconst_0
    //   20: getstatic 109	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   23: aastore
    //   24: dup
    //   25: iconst_1
    //   26: ldc 111
    //   28: aastore
    //   29: invokevirtual 115	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   32: astore 4
    //   34: aload 4
    //   36: aload_0
    //   37: iconst_2
    //   38: anewarray 4	java/lang/Object
    //   41: dup
    //   42: iconst_0
    //   43: iload_1
    //   44: invokestatic 119	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   47: aastore
    //   48: dup
    //   49: iconst_1
    //   50: new 111	android/content/ComponentName
    //   53: dup
    //   54: ldc 121
    //   56: ldc 123
    //   58: invokespecial 126	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   61: aastore
    //   62: invokevirtual 132	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   65: pop
    //   66: iconst_1
    //   67: ireturn
    //   68: astore_0
    //   69: getstatic 19	cnt:a	Ljava/lang/String;
    //   72: astore 4
    //   74: aload_0
    //   75: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   78: astore_0
    //   79: aload 4
    //   81: new 137	java/lang/StringBuilder
    //   84: dup
    //   85: aload_0
    //   86: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   89: invokevirtual 141	java/lang/String:length	()I
    //   92: bipush 49
    //   94: iadd
    //   95: invokespecial 144	java/lang/StringBuilder:<init>	(I)V
    //   98: ldc -110
    //   100: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: aload_0
    //   104: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: invokestatic 159	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   113: pop
    //   114: iconst_0
    //   115: ireturn
    //   116: astore 4
    //   118: aload 4
    //   120: invokevirtual 163	java/lang/reflect/InvocationTargetException:getCause	()Ljava/lang/Throwable;
    //   123: instanceof 165
    //   126: ifeq +129 -> 255
    //   129: getstatic 19	cnt:a	Ljava/lang/String;
    //   132: astore 5
    //   134: aload 4
    //   136: invokevirtual 163	java/lang/reflect/InvocationTargetException:getCause	()Ljava/lang/Throwable;
    //   139: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   142: astore 4
    //   144: aload 5
    //   146: new 137	java/lang/StringBuilder
    //   149: dup
    //   150: aload 4
    //   152: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   155: invokevirtual 141	java/lang/String:length	()I
    //   158: bipush 25
    //   160: iadd
    //   161: invokespecial 144	java/lang/StringBuilder:<init>	(I)V
    //   164: ldc -89
    //   166: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: aload 4
    //   171: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   177: invokestatic 170	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   180: pop
    //   181: aload_0
    //   182: invokestatic 173	cnt:b	(Landroid/content/Context;)I
    //   185: istore_3
    //   186: iload_3
    //   187: iconst_m1
    //   188: if_icmpne +38 -> 226
    //   191: aload_0
    //   192: getstatic 176	bcx:et	I
    //   195: getstatic 179	bcx:eu	I
    //   198: new 181	cnu
    //   201: dup
    //   202: aload_0
    //   203: invokespecial 182	cnu:<init>	(Landroid/content/Context;)V
    //   206: invokestatic 184	cnt:a	(Landroid/content/Context;IILandroid/content/DialogInterface$OnClickListener;)V
    //   209: iconst_0
    //   210: istore_2
    //   211: iload_2
    //   212: ifeq +12 -> 224
    //   215: getstatic 19	cnt:a	Ljava/lang/String;
    //   218: ldc -70
    //   220: invokestatic 170	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   223: pop
    //   224: iconst_0
    //   225: ireturn
    //   226: iload_3
    //   227: bipush -2
    //   229: if_icmpne -18 -> 211
    //   232: aload_0
    //   233: getstatic 189	bcx:es	I
    //   236: getstatic 192	bcx:ev	I
    //   239: new 194	cnv
    //   242: dup
    //   243: aload_0
    //   244: invokespecial 195	cnv:<init>	(Landroid/content/Context;)V
    //   247: invokestatic 184	cnt:a	(Landroid/content/Context;IILandroid/content/DialogInterface$OnClickListener;)V
    //   250: iconst_0
    //   251: istore_2
    //   252: goto -41 -> 211
    //   255: getstatic 19	cnt:a	Ljava/lang/String;
    //   258: astore 5
    //   260: aload 4
    //   262: astore_0
    //   263: aload 4
    //   265: invokevirtual 163	java/lang/reflect/InvocationTargetException:getCause	()Ljava/lang/Throwable;
    //   268: ifnull +9 -> 277
    //   271: aload 4
    //   273: invokevirtual 163	java/lang/reflect/InvocationTargetException:getCause	()Ljava/lang/Throwable;
    //   276: astore_0
    //   277: aload_0
    //   278: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   281: astore_0
    //   282: aload 5
    //   284: new 137	java/lang/StringBuilder
    //   287: dup
    //   288: aload_0
    //   289: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   292: invokevirtual 141	java/lang/String:length	()I
    //   295: bipush 23
    //   297: iadd
    //   298: invokespecial 144	java/lang/StringBuilder:<init>	(I)V
    //   301: ldc -59
    //   303: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: aload_0
    //   307: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   313: invokestatic 170	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   316: pop
    //   317: goto -93 -> 224
    //   320: astore_0
    //   321: getstatic 19	cnt:a	Ljava/lang/String;
    //   324: astore 4
    //   326: aload_0
    //   327: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   330: astore_0
    //   331: aload 4
    //   333: new 137	java/lang/StringBuilder
    //   336: dup
    //   337: aload_0
    //   338: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   341: invokevirtual 141	java/lang/String:length	()I
    //   344: bipush 23
    //   346: iadd
    //   347: invokespecial 144	java/lang/StringBuilder:<init>	(I)V
    //   350: ldc -59
    //   352: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   355: aload_0
    //   356: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   359: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   362: invokestatic 170	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   365: pop
    //   366: goto -142 -> 224
    //   369: astore_0
    //   370: goto -49 -> 321
    //   373: astore_0
    //   374: goto -305 -> 69
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	377	0	paramActivity	Activity
    //   0	377	1	paramBoolean	boolean
    //   0	377	2	paramInt	int
    //   185	45	3	i	int
    //   32	48	4	localObject	Object
    //   116	19	4	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   142	190	4	str1	String
    //   132	151	5	str2	String
    // Exception table:
    //   from	to	target	type
    //   10	34	68	java/lang/RuntimeException
    //   34	66	116	java/lang/reflect/InvocationTargetException
    //   34	66	320	java/lang/RuntimeException
    //   34	66	369	java/lang/IllegalAccessException
    //   10	34	373	java/lang/NoSuchMethodException
  }
  
  /* Error */
  public static boolean a(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 99	cnt:a	()Z
    //   3: ifne +5 -> 8
    //   6: iconst_0
    //   7: ireturn
    //   8: getstatic 211	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   11: ldc -43
    //   13: invokevirtual 82	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   16: ifeq +36 -> 52
    //   19: getstatic 216	android/os/Build:DEVICE	Ljava/lang/String;
    //   22: ldc -38
    //   24: invokevirtual 82	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   27: ifeq +25 -> 52
    //   30: getstatic 221	android/os/Build:MODEL	Ljava/lang/String;
    //   33: ldc -33
    //   35: invokevirtual 82	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   38: ifeq +14 -> 52
    //   41: getstatic 226	android/os/Build:HARDWARE	Ljava/lang/String;
    //   44: ldc -38
    //   46: invokevirtual 82	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   49: ifne +5 -> 54
    //   52: iconst_0
    //   53: ireturn
    //   54: ldc -28
    //   56: ldc -26
    //   58: invokevirtual 234	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   61: astore_2
    //   62: aload_2
    //   63: aconst_null
    //   64: invokevirtual 240	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   67: checkcast 78	java/lang/String
    //   70: astore_2
    //   71: aload_0
    //   72: invokevirtual 246	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   75: aload_2
    //   76: invokevirtual 250	android/content/pm/PackageManager:hasSystemFeature	(Ljava/lang/String;)Z
    //   79: istore_1
    //   80: iload_1
    //   81: ireturn
    //   82: astore_0
    //   83: getstatic 19	cnt:a	Ljava/lang/String;
    //   86: astore_2
    //   87: aload_0
    //   88: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   91: astore_0
    //   92: aload_2
    //   93: new 137	java/lang/StringBuilder
    //   96: dup
    //   97: aload_0
    //   98: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   101: invokevirtual 141	java/lang/String:length	()I
    //   104: bipush 55
    //   106: iadd
    //   107: invokespecial 144	java/lang/StringBuilder:<init>	(I)V
    //   110: ldc -4
    //   112: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: aload_0
    //   116: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   122: invokestatic 159	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   125: pop
    //   126: iconst_0
    //   127: ireturn
    //   128: astore_0
    //   129: getstatic 19	cnt:a	Ljava/lang/String;
    //   132: astore_2
    //   133: aload_0
    //   134: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   137: astore_0
    //   138: aload_2
    //   139: new 137	java/lang/StringBuilder
    //   142: dup
    //   143: aload_0
    //   144: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   147: invokevirtual 141	java/lang/String:length	()I
    //   150: bipush 32
    //   152: iadd
    //   153: invokespecial 144	java/lang/StringBuilder:<init>	(I)V
    //   156: ldc -2
    //   158: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: aload_0
    //   162: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   168: invokestatic 170	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   171: pop
    //   172: iconst_0
    //   173: ireturn
    //   174: astore_0
    //   175: goto -46 -> 129
    //   178: astore_0
    //   179: goto -50 -> 129
    //   182: astore_0
    //   183: goto -100 -> 83
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	186	0	paramContext	Context
    //   79	2	1	bool	boolean
    //   61	78	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   54	62	82	java/lang/NoSuchFieldException
    //   62	80	128	java/lang/IllegalAccessException
    //   62	80	174	java/lang/IllegalArgumentException
    //   62	80	178	java/lang/ExceptionInInitializerError
    //   54	62	182	java/lang/SecurityException
  }
  
  private static int b(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)((Iterator)localObject).next()).packageName.equals("com.google.vr.vrcore"));
    for (int i = 1;; i = 0)
    {
      if (i == 0) {
        return -1;
      }
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_vr_listeners");
      localObject = new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService");
      if ((paramContext == null) || (!paramContext.contains(((ComponentName)localObject).flattenToString()))) {
        return -2;
      }
      return 0;
    }
  }
}
