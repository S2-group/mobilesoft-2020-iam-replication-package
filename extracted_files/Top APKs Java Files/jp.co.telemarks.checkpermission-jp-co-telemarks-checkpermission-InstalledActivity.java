package jp.co.telemarks.checkpermission;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.CheckBox;
import android.widget.ListView;
import java.util.ArrayList;

public class InstalledActivity
  extends Activity
{
  protected static final String a = InstalledActivity.class.getSimpleName();
  final Handler b = new b(this);
  private ArrayList c = new ArrayList();
  private g d = null;
  private CheckBox e;
  
  public InstalledActivity() {}
  
  /* Error */
  ArrayList a(android.content.Context paramContext)
  {
    // Byte code:
    //   0: new 30	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 31	java/util/ArrayList:<init>	()V
    //   7: astore 8
    //   9: aload_1
    //   10: invokevirtual 55	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: astore 9
    //   15: aload 9
    //   17: sipush 8192
    //   20: invokevirtual 61	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   23: invokeinterface 67 1 0
    //   28: astore 10
    //   30: aload 10
    //   32: invokeinterface 73 1 0
    //   37: ifne +33 -> 70
    //   40: getstatic 24	jp/co/telemarks/checkpermission/InstalledActivity:a	Ljava/lang/String;
    //   43: new 75	java/lang/StringBuilder
    //   46: dup
    //   47: ldc 77
    //   49: invokespecial 80	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   52: aload 8
    //   54: invokevirtual 84	java/util/ArrayList:size	()I
    //   57: invokevirtual 88	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   60: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokestatic 96	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   66: pop
    //   67: aload 8
    //   69: areturn
    //   70: aload 10
    //   72: invokeinterface 100 1 0
    //   77: checkcast 102	android/content/pm/ApplicationInfo
    //   80: astore 11
    //   82: aload_0
    //   83: getfield 104	jp/co/telemarks/checkpermission/InstalledActivity:e	Landroid/widget/CheckBox;
    //   86: invokevirtual 109	android/widget/CheckBox:isChecked	()Z
    //   89: ifne +13 -> 102
    //   92: aload 11
    //   94: getfield 113	android/content/pm/ApplicationInfo:flags	I
    //   97: iconst_1
    //   98: iand
    //   99: ifne -69 -> 30
    //   102: aload 9
    //   104: aload 11
    //   106: invokevirtual 117	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   109: checkcast 119	java/lang/String
    //   112: astore_1
    //   113: aload 9
    //   115: aload 11
    //   117: invokevirtual 123	android/content/pm/PackageManager:getApplicationIcon	(Landroid/content/pm/ApplicationInfo;)Landroid/graphics/drawable/Drawable;
    //   120: astore 7
    //   122: aload_1
    //   123: astore 6
    //   125: aload 7
    //   127: astore_1
    //   128: aload 11
    //   130: getfield 126	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   133: astore 11
    //   135: aload 9
    //   137: aload 11
    //   139: sipush 4096
    //   142: invokevirtual 130	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   145: astore 7
    //   147: iconst_0
    //   148: istore_3
    //   149: iconst_0
    //   150: istore 4
    //   152: iload_3
    //   153: istore_2
    //   154: aload 7
    //   156: ifnull +36 -> 192
    //   159: aload 7
    //   161: getfield 136	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   164: astore 7
    //   166: aload 7
    //   168: ifnull +129 -> 297
    //   171: aload 7
    //   173: invokestatic 142	java/util/Arrays:sort	([Ljava/lang/Object;)V
    //   176: aload 7
    //   178: arraylength
    //   179: istore 5
    //   181: iconst_0
    //   182: istore_3
    //   183: iload 4
    //   185: istore_2
    //   186: iload_3
    //   187: iload 5
    //   189: if_icmplt +59 -> 248
    //   192: iload_2
    //   193: ifeq -163 -> 30
    //   196: aload 8
    //   198: new 144	jp/co/telemarks/checkpermission/a
    //   201: dup
    //   202: aload 6
    //   204: aload 11
    //   206: aload_1
    //   207: invokespecial 147	jp/co/telemarks/checkpermission/a:<init>	(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)V
    //   210: invokevirtual 151	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   213: pop
    //   214: goto -184 -> 30
    //   217: astore 6
    //   219: ldc -103
    //   221: astore_1
    //   222: aload 6
    //   224: invokevirtual 156	java/lang/Exception:printStackTrace	()V
    //   227: aload_1
    //   228: astore 6
    //   230: aconst_null
    //   231: astore_1
    //   232: goto -104 -> 128
    //   235: astore 7
    //   237: aload 7
    //   239: invokevirtual 157	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   242: aconst_null
    //   243: astore 7
    //   245: goto -98 -> 147
    //   248: aload 7
    //   250: iload_3
    //   251: aaload
    //   252: astore 12
    //   254: getstatic 24	jp/co/telemarks/checkpermission/InstalledActivity:a	Ljava/lang/String;
    //   257: new 75	java/lang/StringBuilder
    //   260: dup
    //   261: ldc -97
    //   263: invokespecial 80	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   266: aload 12
    //   268: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   274: invokestatic 96	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   277: pop
    //   278: aload 12
    //   280: ldc -92
    //   282: invokevirtual 167	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   285: ifeq +5 -> 290
    //   288: iconst_1
    //   289: istore_2
    //   290: iload_3
    //   291: iconst_1
    //   292: iadd
    //   293: istore_3
    //   294: goto -108 -> 186
    //   297: getstatic 24	jp/co/telemarks/checkpermission/InstalledActivity:a	Ljava/lang/String;
    //   300: ldc -87
    //   302: invokestatic 96	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   305: pop
    //   306: iload_3
    //   307: istore_2
    //   308: goto -116 -> 192
    //   311: astore 6
    //   313: goto -91 -> 222
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	316	0	this	InstalledActivity
    //   0	316	1	paramContext	android.content.Context
    //   153	155	2	i	int
    //   148	159	3	j	int
    //   150	34	4	k	int
    //   179	11	5	m	int
    //   123	80	6	localContext1	android.content.Context
    //   217	6	6	localException1	Exception
    //   228	1	6	localContext2	android.content.Context
    //   311	1	6	localException2	Exception
    //   120	57	7	localObject1	Object
    //   235	3	7	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   243	6	7	localObject2	Object
    //   7	190	8	localArrayList	ArrayList
    //   13	123	9	localPackageManager	android.content.pm.PackageManager
    //   28	43	10	localIterator	java.util.Iterator
    //   80	125	11	localObject3	Object
    //   252	27	12	str	String
    // Exception table:
    //   from	to	target	type
    //   102	113	217	java/lang/Exception
    //   135	147	235	android/content/pm/PackageManager$NameNotFoundException
    //   113	122	311	java/lang/Exception
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903041);
    paramBundle = (ListView)findViewById(2131099651);
    this.c = new ArrayList();
    this.d = new g(getApplicationContext(), this.c);
    paramBundle.setAdapter(this.d);
    paramBundle.setOnItemClickListener(new c(this));
    this.e = ((CheckBox)findViewById(2131099650));
    this.e.setOnCheckedChangeListener(new d(this));
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  protected void onStart()
  {
    new f(this, null).execute(new Void[0]);
    super.onStart();
  }
}
