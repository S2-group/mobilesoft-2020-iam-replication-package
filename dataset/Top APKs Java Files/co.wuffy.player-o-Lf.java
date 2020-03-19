package o;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public final class Lf
  extends Lj
{
  private static long ʽᐝ = -5695932594322206424L;
  private static char[] ʾॱ = { -17206, -21011, -24929, -28767, -1948, -5878, -9665, -15203, 13704, 9900, 5973, 103, 28974, 25549, 19695, -16931, -20901, -24722, -30635, -1789, -5142, -11136, -14928, 13906, 9996, 4134, 754, 29693, 23725, 19788, 4970, 595, 12585, 8201, 22491, 18101, 30095, 27468, -26053, -30433, -18193 };
  private static String ˉॱ = "";
  private static int ˊʽ = 0;
  private static int ˋʻ = 1;
  static String ﾟ = "com.slideme.sam.manager";
  boolean ʹ = false;
  public iF ʻˊ;
  boolean ʻˋ = false;
  CheckBox ʻᐝ;
  View.OnClickListener ʼˊ = new Lk(this);
  private int ʼˋ;
  private TextView ʼᐝ;
  View.OnClickListener ʽˊ = new Lm(this);
  View.OnClickListener ʽˋ = new Ll(this);
  private ImageView ʿॱ;
  private TextView ˈॱ;
  public Context ꜞ;
  Button ꞌ;
  ImageView ﹳ;
  Button ﾞ;
  
  public Lf() {}
  
  /* Error */
  private static String ॱ(char paramChar, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: goto +174 -> 174
    //   3: astore 10
    //   5: aload 10
    //   7: athrow
    //   8: goto +29 -> 37
    //   11: getstatic 93	o/Lf:ˋʻ	I
    //   14: iconst_5
    //   15: iadd
    //   16: istore 4
    //   18: iload 4
    //   20: sipush 128
    //   23: irem
    //   24: putstatic 91	o/Lf:ˊʽ	I
    //   27: iload 4
    //   29: iconst_2
    //   30: irem
    //   31: ifeq +6 -> 37
    //   34: goto -26 -> 8
    //   37: getstatic 89	o/Lf:ʾॱ	[C
    //   40: astore 10
    //   42: aload 10
    //   44: iload_2
    //   45: iload_3
    //   46: iadd
    //   47: caload
    //   48: i2l
    //   49: lstore 6
    //   51: iload_3
    //   52: i2l
    //   53: lstore 8
    //   55: aload 11
    //   57: iload_3
    //   58: lload 6
    //   60: lload 8
    //   62: getstatic 46	o/Lf:ʽᐝ	J
    //   65: lmul
    //   66: lxor
    //   67: iload_0
    //   68: i2l
    //   69: lxor
    //   70: l2i
    //   71: i2c
    //   72: castore
    //   73: iload_3
    //   74: iconst_1
    //   75: iadd
    //   76: istore_3
    //   77: goto +39 -> 116
    //   80: iconst_1
    //   81: istore 4
    //   83: goto +54 -> 137
    //   86: getstatic 91	o/Lf:ˊʽ	I
    //   89: bipush 61
    //   91: iadd
    //   92: istore 5
    //   94: iload 5
    //   96: sipush 128
    //   99: irem
    //   100: putstatic 93	o/Lf:ˋʻ	I
    //   103: iload 4
    //   105: istore_3
    //   106: iload 5
    //   108: iconst_2
    //   109: irem
    //   110: ifne +6 -> 116
    //   113: goto +50 -> 163
    //   116: iload_3
    //   117: iload_1
    //   118: if_icmpge +6 -> 124
    //   121: goto +64 -> 185
    //   124: goto -44 -> 80
    //   127: new 132	java/lang/String
    //   130: dup
    //   131: aload 11
    //   133: invokespecial 135	java/lang/String:<init>	([C)V
    //   136: areturn
    //   137: iload 4
    //   139: tableswitch	default:+21->160, 0:+-128->11, 1:+-12->127
    //   160: goto +25 -> 185
    //   163: iload 4
    //   165: istore_3
    //   166: goto -50 -> 116
    //   169: astore 10
    //   171: aload 10
    //   173: athrow
    //   174: iload_1
    //   175: newarray char
    //   177: astore 11
    //   179: iconst_0
    //   180: istore 4
    //   182: goto -96 -> 86
    //   185: iconst_0
    //   186: istore 4
    //   188: goto -51 -> 137
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	191	0	paramChar	char
    //   0	191	1	paramInt1	int
    //   0	191	2	paramInt2	int
    //   45	121	3	i	int
    //   16	171	4	j	int
    //   92	18	5	k	int
    //   49	10	6	l1	long
    //   53	8	8	l2	long
    //   3	3	10	localException1	Exception
    //   40	3	10	arrayOfChar1	char[]
    //   169	3	10	localException2	Exception
    //   55	123	11	arrayOfChar2	char[]
    // Exception table:
    //   from	to	target	type
    //   37	42	3	java/lang/Exception
    //   55	73	3	java/lang/Exception
    //   11	18	169	java/lang/Exception
    //   18	27	169	java/lang/Exception
  }
  
  @SuppressLint({"NewApi"})
  public final void ˎ()
  {
    super.ˎ();
    if (this.ᐝ != null)
    {
      int i = this.ᐝ.getWindow().getAttributes().width;
      Object localObject;
      if (Build.VERSION.SDK_INT >= 13)
      {
        if (this.ˊᐝ == null) {
          localObject = null;
        } else {
          localObject = (ᵕ)this.ˊᐝ.ˎ;
        }
        localObject = ((ᵕ)localObject).getWindowManager().getDefaultDisplay();
        Point localPoint = new Point();
        ((Display)localObject).getSize(localPoint);
        i = localPoint.x;
      }
      else
      {
        if (this.ˊᐝ == null) {
          localObject = null;
        } else {
          localObject = (ᵕ)this.ˊᐝ.ˎ;
        }
        i = ((ᵕ)localObject).getWindowManager().getDefaultDisplay().getWidth();
      }
      int j = (int)TypedValue.applyDimension(1, 24.0F, ʼ().getDisplayMetrics());
      int k = this.ᐝ.getWindow().getAttributes().height;
      this.ᐝ.getWindow().setLayout(i - j, k);
    }
  }
  
  public final View ˏ(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(Ls.iF.quick_share_frangment, paramViewGroup, false);
    try
    {
      this.ʼˋ = ((Activity)this.ꜞ).getRequestedOrientation();
      ʼ().getConfiguration();
      i = ((Activity)this.ꜞ).getWindowManager().getDefaultDisplay().getRotation();
      paramViewGroup = new DisplayMetrics();
      ((Activity)this.ꜞ).getWindowManager().getDefaultDisplay().getMetrics(paramViewGroup);
      j = paramViewGroup.widthPixels;
      k = paramViewGroup.heightPixels;
      if (i == 0) {
        break label594;
      }
      if (i != 2) {
        break label601;
      }
    }
    catch (Exception paramViewGroup)
    {
      for (;;)
      {
        int i;
        int j;
        int k;
        continue;
        if ((k > j) || (((i == 1) || (i == 3)) && (j > k))) {}
        switch (i)
        {
        default: 
          break;
        case 0: 
          i = 1;
          break;
        case 1: 
          i = 0;
          break;
        case 2: 
          i = 9;
          break;
        case 3: 
          i = 8;
          continue;
          i = 1;
          continue;
          switch (i)
          {
          default: 
            break;
          case 0: 
            i = 0;
            break;
          case 1: 
            i = 9;
            break;
          case 2: 
            i = 8;
            break;
          case 3: 
            i = 1;
            continue;
            i = 0;
          }
          break;
        }
      }
    }
    ((Activity)this.ꜞ).setRequestedOrientation(i);
    if (this.ᐝ != null)
    {
      this.ᐝ.getWindow().requestFeature(1);
      this.ᐝ.getWindow().setBackgroundDrawableResource(17170445);
    }
    this.ʿॱ = ((ImageView)paramLayoutInflater.findViewById(Ls.ˋ.imggoogleplay));
    this.ꞌ = ((Button)paramLayoutInflater.findViewById(Ls.ˋ.buttonNoTahnks));
    this.ꞌ.setOnClickListener(this.ʽˋ);
    this.ﾞ = ((Button)paramLayoutInflater.findViewById(Ls.ˋ.buttonRemind));
    this.ﾞ.setOnClickListener(this.ʽˊ);
    this.ﹳ = ((ImageView)paramLayoutInflater.findViewById(Ls.ˋ.imggoogleplay));
    this.ﹳ.setOnClickListener(this.ʼˊ);
    this.ʻᐝ = ((CheckBox)paramLayoutInflater.findViewById(Ls.ˋ.checkBoxHide));
    this.ʻᐝ.setOnCheckedChangeListener(new Li(this));
    this.ʼᐝ = ((TextView)paramLayoutInflater.findViewById(Ls.ˋ.textAppTitle));
    this.ʼᐝ.setText(String.format(ʼ().getString(Ls.if.app_love), new Object[] { String.format(ʼ().getString(Ls.if.app_name), new Object[0]) }));
    this.ˈॱ = ((TextView)paramLayoutInflater.findViewById(Ls.ˋ.textRateApp));
    this.ˈॱ.setText(String.format(ʼ().getString(Ls.if.app_love_desc), new Object[] { String.format(ʼ().getString(Ls.if.app_name), new Object[0]) }));
    if ((!this.ʻˋ) && (!this.ʹ))
    {
      if (ˏ(ﾟ))
      {
        this.ˈॱ.setText(String.format(ʼ().getString(Ls.if.app_love_desc_slideme), new Object[] { String.format(ʼ().getString(Ls.if.app_name), new Object[0]) }));
        this.ʿॱ.setImageResource(Ls.If.slideme_logo);
        return paramLayoutInflater;
      }
    }
    else
    {
      if (this.ʻˋ)
      {
        this.ˈॱ.setText(String.format(ʼ().getString(Ls.if.app_love_desc_samsung), new Object[] { String.format(ʼ().getString(Ls.if.app_name), new Object[0]) }));
        this.ʿॱ.setImageResource(Ls.If.samsung_logo);
      }
      if (this.ʹ)
      {
        this.ˈॱ.setText(String.format(ʼ().getString(Ls.if.app_love_desc_amazon), new Object[] { String.format(ʼ().getString(Ls.if.app_name), new Object[0]) }));
        this.ʿॱ.setImageResource(Ls.If.amazon_apps_store);
      }
    }
    return paramLayoutInflater;
    ˎ(true);
    return paramLayoutInflater;
  }
  
  /* Error */
  public final boolean ˏ(String paramString)
  {
    // Byte code:
    //   0: goto +30 -> 30
    //   3: getstatic 93	o/Lf:ˋʻ	I
    //   6: bipush 43
    //   8: iadd
    //   9: istore_2
    //   10: iload_2
    //   11: sipush 128
    //   14: irem
    //   15: putstatic 91	o/Lf:ˊʽ	I
    //   18: iload_2
    //   19: iconst_2
    //   20: irem
    //   21: ifeq +6 -> 27
    //   24: goto +341 -> 365
    //   27: goto +150 -> 177
    //   30: aload_0
    //   31: getfield 248	o/Lf:ꜞ	Landroid/content/Context;
    //   34: invokevirtual 408	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   37: iconst_0
    //   38: invokevirtual 414	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   41: astore 4
    //   43: aload 4
    //   45: ifnull +6 -> 51
    //   48: goto +123 -> 171
    //   51: goto +320 -> 371
    //   54: iconst_0
    //   55: istore_2
    //   56: goto +6 -> 62
    //   59: astore_1
    //   60: aload_1
    //   61: athrow
    //   62: iload_2
    //   63: tableswitch	default:+17->80, 0:+44->107
    //   80: goto +55 -> 135
    //   83: iload_2
    //   84: lookupswitch	default:+20->104, 59:+23->107
    //   104: goto +161 -> 265
    //   107: iconst_0
    //   108: ireturn
    //   109: goto +218 -> 327
    //   112: iload_2
    //   113: tableswitch	default:+19->132, 1:+255->368
    //   132: goto +213 -> 345
    //   135: aload 4
    //   137: invokeinterface 420 1 0
    //   142: astore 4
    //   144: getstatic 91	o/Lf:ˊʽ	I
    //   147: bipush 101
    //   149: iadd
    //   150: istore_2
    //   151: iload_2
    //   152: sipush 128
    //   155: irem
    //   156: putstatic 93	o/Lf:ˋʻ	I
    //   159: iload_2
    //   160: iconst_2
    //   161: irem
    //   162: ifne +6 -> 168
    //   165: goto -56 -> 109
    //   168: goto +159 -> 327
    //   171: bipush 56
    //   173: istore_2
    //   174: goto -91 -> 83
    //   177: aload 4
    //   179: invokeinterface 426 1 0
    //   184: astore 5
    //   186: ldc_w 427
    //   189: bipush 30
    //   191: iconst_0
    //   192: invokestatic 429	o/Lf:ॱ	(CII)Ljava/lang/String;
    //   195: astore 6
    //   197: aload 6
    //   199: invokevirtual 433	java/lang/String:intern	()Ljava/lang/String;
    //   202: astore 6
    //   204: aload 6
    //   206: invokestatic 439	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   209: astore 6
    //   211: sipush 4890
    //   214: bipush 11
    //   216: bipush 30
    //   218: invokestatic 429	o/Lf:ॱ	(CII)Ljava/lang/String;
    //   221: astore 7
    //   223: aload 7
    //   225: invokevirtual 433	java/lang/String:intern	()Ljava/lang/String;
    //   228: astore 7
    //   230: aload 6
    //   232: aload 7
    //   234: invokevirtual 443	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   237: astore 6
    //   239: aload 6
    //   241: aload 5
    //   243: invokevirtual 449	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   246: astore 5
    //   248: aload_1
    //   249: aload 5
    //   251: invokevirtual 453	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   254: istore_3
    //   255: iload_3
    //   256: ifeq +6 -> 262
    //   259: goto +42 -> 301
    //   262: goto +29 -> 291
    //   265: getstatic 93	o/Lf:ˋʻ	I
    //   268: iconst_1
    //   269: iadd
    //   270: istore_2
    //   271: iload_2
    //   272: sipush 128
    //   275: irem
    //   276: putstatic 91	o/Lf:ˊʽ	I
    //   279: iload_2
    //   280: iconst_2
    //   281: irem
    //   282: ifeq +6 -> 288
    //   285: goto +97 -> 382
    //   288: goto +67 -> 355
    //   291: iconst_1
    //   292: istore_2
    //   293: goto -181 -> 112
    //   296: iconst_1
    //   297: istore_2
    //   298: goto +8 -> 306
    //   301: iconst_0
    //   302: istore_2
    //   303: goto -191 -> 112
    //   306: iload_2
    //   307: tableswitch	default:+17->324, 0:+-200->107
    //   324: goto -321 -> 3
    //   327: aload 4
    //   329: invokeinterface 457 1 0
    //   334: istore_3
    //   335: iload_3
    //   336: ifeq +6 -> 342
    //   339: goto -43 -> 296
    //   342: goto +8 -> 350
    //   345: iconst_1
    //   346: ireturn
    //   347: astore_1
    //   348: aload_1
    //   349: athrow
    //   350: iconst_0
    //   351: istore_2
    //   352: goto -46 -> 306
    //   355: aload_1
    //   356: ifnull +6 -> 362
    //   359: goto +18 -> 377
    //   362: goto -308 -> 54
    //   365: goto -188 -> 177
    //   368: goto -41 -> 327
    //   371: bipush 59
    //   373: istore_2
    //   374: goto -291 -> 83
    //   377: iconst_1
    //   378: istore_2
    //   379: goto -317 -> 62
    //   382: goto -27 -> 355
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	385	0	this	Lf
    //   0	385	1	paramString	String
    //   9	370	2	i	int
    //   254	82	3	bool	boolean
    //   41	287	4	localObject1	Object
    //   184	66	5	localObject2	Object
    //   195	45	6	localObject3	Object
    //   221	12	7	str	String
    // Exception table:
    //   from	to	target	type
    //   177	186	59	java/lang/Exception
    //   186	197	59	java/lang/Exception
    //   197	204	59	java/lang/Exception
    //   204	211	59	java/lang/Exception
    //   211	223	59	java/lang/Exception
    //   223	230	59	java/lang/Exception
    //   230	239	59	java/lang/Exception
    //   239	248	59	java/lang/Exception
    //   248	255	59	java/lang/Exception
    //   186	197	347	java/lang/Exception
    //   327	335	347	java/lang/Exception
  }
  
  public final void ͺ()
  {
    super.ͺ();
    if ((!this.ʻˋ) && (!this.ʹ)) {
      ˏ(ﾟ);
    }
  }
  
  public final void ᐝ()
  {
    ((Activity)this.ꜞ).setRequestedOrientation(this.ʼˋ);
    super.ᐝ();
  }
  
  public static abstract interface iF
  {
    public abstract void ˊ();
  }
}
