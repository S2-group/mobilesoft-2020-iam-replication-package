package com.monotype.android.font.free;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.c;
import android.support.v7.app.c.a;
import android.support.v7.app.d;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdView;
import com.monotype.android.font.free.fragments.ColorPickerView;
import com.monotype.android.font.free.fragments.ColorPickerView.a;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class e
  extends d
{
  private int A;
  private int B;
  protected TextView n;
  protected EditText o;
  protected Spinner p;
  protected Spinner q;
  protected Button r;
  protected ScrollView s;
  protected LinearLayout t;
  protected AdView u;
  int v = 0;
  private ProgressDialog w;
  private HashMap<String, String> x;
  private HashMap<String, String> y;
  private HashMap<String, String> z;
  
  public e() {}
  
  private int a(int paramInt)
  {
    switch (paramInt)
    {
    case 8388627: 
    default: 
      return 17;
    case 8388659: 
      return 49;
    case 49: 
      return 8388661;
    case 8388661: 
      return 8388627;
    case 17: 
      return 8388629;
    case 8388629: 
      return 8388691;
    case 8388691: 
      return 81;
    case 81: 
      return 8388693;
    }
    return 8388659;
  }
  
  private String a(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    try
    {
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      return localByteArrayOutputStream.toString();
    }
    catch (IOException paramInputStream) {}
    for (;;)
    {
      localByteArrayOutputStream.close();
      paramInputStream.close();
    }
  }
  
  private void a(ColorPickerView paramColorPickerView, SeekBar paramSeekBar, TextView paramTextView)
  {
    int i = paramColorPickerView.getColor();
    int j = paramSeekBar.getProgress();
    if (j < 50) {}
    for (this.v = ((Integer)new com.a.a.a().a(j / 50.0F, Integer.valueOf(-16777216), Integer.valueOf(i))).intValue();; this.v = ((Integer)new com.a.a.a().a((j - 50.0F) / 50.0F, Integer.valueOf(i), Integer.valueOf(-1))).intValue())
    {
      paramTextView.setBackgroundColor(this.v);
      return;
    }
  }
  
  public void a(int paramInt, final boolean paramBoolean)
  {
    this.v = paramInt;
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903076, null);
    Object localObject = (SeekBar)localView.findViewById(2131624060);
    final ColorPickerView localColorPickerView = (ColorPickerView)localView.findViewById(2131624058);
    final TextView localTextView = (TextView)localView.findViewById(2131624061);
    a(localColorPickerView, (SeekBar)localObject, localTextView);
    ((SeekBar)localObject).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        e.a(e.this, localColorPickerView, this.b, localTextView);
      }
      
      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar) {}
      
      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar) {}
    });
    localColorPickerView.setOnColorChangedListener(new ColorPickerView.a()
    {
      public void a(int paramAnonymousInt)
      {
        e.a(e.this, localColorPickerView, this.b, localTextView);
      }
    });
    localObject = new c.a(this);
    ((c.a)localObject).a(getString(2131165247, new Object[] { getString(2131165241) }));
    ((c.a)localObject).b(localView);
    ((c.a)localObject).b(17039360, null);
    ((c.a)localObject).a(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (paramBoolean)
        {
          e.this.t.setBackgroundColor(e.this.v);
          e.this.s.setBackgroundColor(e.this.v);
          return;
        }
        e.this.n.setTextColor(e.this.v);
      }
    });
    ((c.a)localObject).b().show();
  }
  
  protected void a(List<String> paramList)
  {
    this.w.dismiss();
    if (paramList != null)
    {
      ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367048, paramList);
      localArrayAdapter.setDropDownViewResource(17367049);
      this.p.setAdapter(localArrayAdapter);
    }
    try
    {
      paramList = Typeface.createFromAsset(createPackageContext((String)this.x.get(this.z.get(paramList.get(0))), 0).getAssets(), "fonts/" + (String)this.z.get(paramList.get(0)));
      this.n.setTypeface(paramList);
      return;
    }
    catch (Exception paramList) {}
  }
  
  protected void a(boolean paramBoolean, String paramString)
  {
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        paramString = Typeface.createFromAsset(createPackageContext((String)this.x.get(this.z.get(paramString)), 0).getAssets(), "fonts/" + (String)this.z.get(paramString));
        this.n.setTypeface(paramString);
        return;
      }
      catch (Exception paramString) {}
      try
      {
        paramString = Typeface.DEFAULT;
        this.n.setTypeface(paramString);
        return;
      }
      catch (Exception paramString) {}
    }
  }
  
  protected void b(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.n.setTextSize(Integer.valueOf(paramString).intValue());
      return;
    }
    this.n.setTextSize(Integer.valueOf(this.q.getItemAtPosition(2).toString()).intValue());
  }
  
  protected void k()
  {
    g().a(2131165283);
    if (this.A != 0)
    {
      this.t.setBackgroundColor(this.A);
      this.s.setBackgroundColor(this.A);
    }
    if (this.B != 0) {
      this.n.setTextColor(this.B);
    }
    this.x = new HashMap();
    this.y = new HashMap();
    this.z = new HashMap();
    this.w = ProgressDialog.show(this, null, getString(2131165331));
    l();
    this.q.setSelection(2);
    this.n.setTextSize(Integer.valueOf(this.q.getItemAtPosition(2).toString()).intValue());
  }
  
  /* Error */
  protected void l()
  {
    // Byte code:
    //   0: new 327	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 328	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: aload_0
    //   9: invokevirtual 332	com/monotype/android/font/free/e:m	()Ljava/util/List;
    //   12: invokeinterface 336 1 0
    //   17: astore_2
    //   18: aload_2
    //   19: invokeinterface 342 1 0
    //   24: ifeq +284 -> 308
    //   27: aload_2
    //   28: invokeinterface 346 1 0
    //   33: checkcast 233	java/lang/String
    //   36: astore 4
    //   38: aload_0
    //   39: aload 4
    //   41: iconst_0
    //   42: invokevirtual 237	com/monotype/android/font/free/e:createPackageContext	(Ljava/lang/String;I)Landroid/content/Context;
    //   45: astore 5
    //   47: aload 5
    //   49: invokevirtual 243	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   52: ldc_w 348
    //   55: invokevirtual 354	android/content/res/AssetManager:list	(Ljava/lang/String;)[Ljava/lang/String;
    //   58: invokestatic 360	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   61: invokeinterface 336 1 0
    //   66: astore 6
    //   68: aload 6
    //   70: invokeinterface 342 1 0
    //   75: ifeq -57 -> 18
    //   78: aload 6
    //   80: invokeinterface 346 1 0
    //   85: checkcast 233	java/lang/String
    //   88: astore 7
    //   90: aload 7
    //   92: ldc_w 362
    //   95: invokevirtual 366	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   98: istore_1
    //   99: iload_1
    //   100: ifne -32 -> 68
    //   103: aload_0
    //   104: aload 5
    //   106: invokevirtual 243	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   109: new 245	java/lang/StringBuilder
    //   112: dup
    //   113: invokespecial 246	java/lang/StringBuilder:<init>	()V
    //   116: ldc_w 368
    //   119: invokevirtual 252	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: aload 7
    //   124: ldc_w 370
    //   127: ldc_w 372
    //   130: invokevirtual 376	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   133: invokevirtual 252	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   139: invokevirtual 380	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   142: invokespecial 382	com/monotype/android/font/free/e:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   145: astore 8
    //   147: aload_0
    //   148: getfield 315	com/monotype/android/font/free/e:y	Ljava/util/HashMap;
    //   151: aload 7
    //   153: aload 8
    //   155: aload 8
    //   157: ldc_w 384
    //   160: invokevirtual 388	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   163: bipush 13
    //   165: iadd
    //   166: aload 8
    //   168: ldc_w 390
    //   171: aload 8
    //   173: ldc_w 384
    //   176: invokevirtual 388	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   179: bipush 13
    //   181: iadd
    //   182: invokevirtual 393	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   185: invokevirtual 397	java/lang/String:substring	(II)Ljava/lang/String;
    //   188: invokevirtual 401	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   191: pop
    //   192: aload_0
    //   193: getfield 220	com/monotype/android/font/free/e:z	Ljava/util/HashMap;
    //   196: aload 8
    //   198: aload 8
    //   200: ldc_w 384
    //   203: invokevirtual 388	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   206: bipush 13
    //   208: iadd
    //   209: aload 8
    //   211: ldc_w 390
    //   214: aload 8
    //   216: ldc_w 384
    //   219: invokevirtual 388	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   222: bipush 13
    //   224: iadd
    //   225: invokevirtual 393	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   228: invokevirtual 397	java/lang/String:substring	(II)Ljava/lang/String;
    //   231: aload 7
    //   233: invokevirtual 401	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   236: pop
    //   237: aload_0
    //   238: getfield 218	com/monotype/android/font/free/e:x	Ljava/util/HashMap;
    //   241: aload 7
    //   243: aload 4
    //   245: invokevirtual 401	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   248: pop
    //   249: aload_3
    //   250: aload 7
    //   252: invokeinterface 405 2 0
    //   257: pop
    //   258: goto -190 -> 68
    //   261: astore_2
    //   262: aload_2
    //   263: invokevirtual 408	java/lang/Exception:printStackTrace	()V
    //   266: aconst_null
    //   267: astore_2
    //   268: aload_0
    //   269: aload_2
    //   270: invokevirtual 410	com/monotype/android/font/free/e:a	(Ljava/util/List;)V
    //   273: return
    //   274: astore 8
    //   276: aload 8
    //   278: invokevirtual 408	java/lang/Exception:printStackTrace	()V
    //   281: aload_0
    //   282: getfield 315	com/monotype/android/font/free/e:y	Ljava/util/HashMap;
    //   285: aload 7
    //   287: aload 7
    //   289: invokevirtual 401	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   292: pop
    //   293: aload_0
    //   294: getfield 220	com/monotype/android/font/free/e:z	Ljava/util/HashMap;
    //   297: aload 7
    //   299: aload 7
    //   301: invokevirtual 401	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   304: pop
    //   305: goto -68 -> 237
    //   308: new 327	java/util/ArrayList
    //   311: dup
    //   312: invokespecial 328	java/util/ArrayList:<init>	()V
    //   315: astore_2
    //   316: aload_3
    //   317: invokeinterface 336 1 0
    //   322: astore_3
    //   323: aload_3
    //   324: invokeinterface 342 1 0
    //   329: ifeq +44 -> 373
    //   332: aload_3
    //   333: invokeinterface 346 1 0
    //   338: checkcast 233	java/lang/String
    //   341: astore 4
    //   343: aload 4
    //   345: ldc_w 412
    //   348: invokevirtual 366	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   351: ifne -28 -> 323
    //   354: aload_2
    //   355: aload_0
    //   356: getfield 315	com/monotype/android/font/free/e:y	Ljava/util/HashMap;
    //   359: aload 4
    //   361: invokevirtual 231	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   364: invokeinterface 405 2 0
    //   369: pop
    //   370: goto -47 -> 323
    //   373: goto -105 -> 268
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	376	0	this	e
    //   98	2	1	bool	boolean
    //   17	11	2	localIterator1	Iterator
    //   261	2	2	localException1	Exception
    //   267	88	2	localObject1	Object
    //   7	326	3	localObject2	Object
    //   36	324	4	str1	String
    //   45	60	5	localContext	Context
    //   66	13	6	localIterator2	Iterator
    //   88	212	7	str2	String
    //   145	70	8	str3	String
    //   274	3	8	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	18	261	java/lang/Exception
    //   18	68	261	java/lang/Exception
    //   68	99	261	java/lang/Exception
    //   237	258	261	java/lang/Exception
    //   276	305	261	java/lang/Exception
    //   308	323	261	java/lang/Exception
    //   323	370	261	java/lang/Exception
    //   103	237	274	java/lang/Exception
  }
  
  public List<String> m()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.contains("com.monotype.android.font.")) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  protected void n()
  {
    if (TextUtils.isEmpty(this.o.getText()))
    {
      this.n.setText(2131165274);
      return;
    }
    this.n.setText(this.o.getText().toString());
  }
  
  protected void o()
  {
    if (this.n.getLineCount() > 1)
    {
      this.r.setEnabled(true);
      return;
    }
    this.r.setEnabled(false);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (paramBundle != null)
    {
      if (paramBundle.containsKey("currentBackColor")) {}
      for (this.A = paramBundle.getInt("currentBackColor"); paramBundle.containsKey("currentTextColor"); this.A = -1)
      {
        this.B = paramBundle.getInt("currentTextColor");
        return;
      }
      this.B = -16777216;
      return;
    }
    this.A = -1;
    this.B = -16777216;
  }
  
  protected void onDestroy()
  {
    this.u.a();
    super.onDestroy();
  }
  
  protected void onPause()
  {
    this.u.b();
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.u.c();
    this.u.a(f.a());
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putBoolean("AdShown", true);
    if (this.A != 0) {
      paramBundle.putInt("currentBackColor", this.A);
    }
    if (this.B != 0) {
      paramBundle.putInt("currentTextColor", this.B);
    }
    super.onSaveInstanceState(paramBundle);
  }
  
  protected void p()
  {
    a(this.A, true);
  }
  
  protected void q()
  {
    a(this.B, false);
  }
  
  protected void r()
  {
    this.n.setGravity(a(this.n.getGravity()));
  }
  
  protected void s()
  {
    Object localObject2 = Bitmap.createBitmap(this.t.getWidth(), this.t.getHeight(), Bitmap.Config.ARGB_8888);
    this.t.draw(new Canvas((Bitmap)localObject2));
    try
    {
      Object localObject1 = new File(getFilesDir(), "message.jpg");
      boolean bool = ((File)localObject1).createNewFile();
      System.out.println("file created " + bool + " - " + ((File)localObject1).toString());
      Object localObject3 = new FileOutputStream((File)localObject1);
      ((Bitmap)localObject2).compress(Bitmap.CompressFormat.JPEG, 90, (OutputStream)localObject3);
      localObject2 = new Intent("android.intent.action.SEND");
      ((Intent)localObject2).setType("image/jpeg");
      localObject1 = FileProvider.a(this, getPackageName() + ".files", (File)localObject1);
      localObject3 = getPackageManager().queryIntentActivities((Intent)localObject2, 65536).iterator();
      while (((Iterator)localObject3).hasNext()) {
        grantUriPermission(((ResolveInfo)((Iterator)localObject3).next()).activityInfo.packageName, (Uri)localObject1, 3);
      }
      ((Intent)localObject2).putExtra("android.intent.extra.STREAM", localException);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Toast.makeText(this, 2131165280, 0).show();
      return;
    }
    startActivityForResult(Intent.createChooser((Intent)localObject2, "Share Image"), 0);
  }
}
