package com.actionlauncher.activitypicker;

import android.app.ExpandableListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import o.aM.ˏ;

public class ActivityPickerActivity
  extends ExpandableListActivity
{
  private static int ˋ = -1;
  private static int ˎ = -1;
  private static final Rect ˏ = new Rect();
  private static final Canvas ᐝ;
  private PackageManager ˊ;
  
  static
  {
    Canvas localCanvas = new Canvas();
    ᐝ = localCanvas;
    localCanvas.setDrawFilter(new PaintFlagsDrawFilter(4, 2));
  }
  
  public ActivityPickerActivity() {}
  
  static Drawable ˊ(Drawable paramDrawable, ActivityPickerActivity paramActivityPickerActivity)
  {
    if (ˋ == -1)
    {
      i = (int)paramActivityPickerActivity.getResources().getDimension(17104896);
      ˎ = i;
      ˋ = i;
    }
    int i = ˋ;
    int k = ˎ;
    if ((paramDrawable instanceof PaintDrawable))
    {
      localObject = (PaintDrawable)paramDrawable;
      ((PaintDrawable)localObject).setIntrinsicWidth(i);
      ((PaintDrawable)localObject).setIntrinsicHeight(k);
    }
    else if ((paramDrawable instanceof BitmapDrawable))
    {
      localObject = (BitmapDrawable)paramDrawable;
      if (((BitmapDrawable)localObject).getBitmap().getDensity() == 0) {
        ((BitmapDrawable)localObject).setTargetDensity(paramActivityPickerActivity.getResources().getDisplayMetrics());
      }
    }
    int m = paramDrawable.getIntrinsicWidth();
    int n = paramDrawable.getIntrinsicHeight();
    Object localObject = paramDrawable;
    if (i > 0)
    {
      localObject = paramDrawable;
      if (k > 0)
      {
        int j;
        Canvas localCanvas;
        if ((i < m) || (k < n))
        {
          float f = m / n;
          if (m > n)
          {
            j = (int)(i / f);
          }
          else
          {
            j = k;
            if (n > m)
            {
              i = (int)(k * f);
              j = k;
            }
          }
          if (paramDrawable.getOpacity() != -1) {
            localObject = Bitmap.Config.ARGB_8888;
          } else {
            localObject = Bitmap.Config.RGB_565;
          }
          localObject = Bitmap.createBitmap(ˋ, ˎ, (Bitmap.Config)localObject);
          localCanvas = ᐝ;
          localCanvas.setBitmap((Bitmap)localObject);
          ˏ.set(paramDrawable.getBounds());
          k = (ˋ - i) / 2;
          m = (ˎ - j) / 2;
          paramDrawable.setBounds(k, m, k + i, m + j);
          paramDrawable.draw(localCanvas);
          paramDrawable.setBounds(ˏ);
          return new BitmapDrawable(paramActivityPickerActivity.getResources(), (Bitmap)localObject);
        }
        localObject = paramDrawable;
        if (m < i)
        {
          localObject = paramDrawable;
          if (n < k)
          {
            localObject = Bitmap.Config.ARGB_8888;
            localObject = Bitmap.createBitmap(ˋ, ˎ, (Bitmap.Config)localObject);
            localCanvas = ᐝ;
            localCanvas.setBitmap((Bitmap)localObject);
            ˏ.set(paramDrawable.getBounds());
            i = (i - m) / 2;
            j = (k - n) / 2;
            paramDrawable.setBounds(i, j, i + m, j + n);
            paramDrawable.draw(localCanvas);
            paramDrawable.setBounds(ˏ);
            localObject = new BitmapDrawable(paramActivityPickerActivity.getResources(), (Bitmap)localObject);
          }
        }
      }
    }
    return localObject;
  }
  
  public boolean onChildClick(ExpandableListView paramExpandableListView, View paramView, int paramInt1, int paramInt2, long paramLong)
  {
    paramView = (ActivityInfo)getExpandableListAdapter().getChild(paramInt1, paramInt2);
    Object localObject = new Intent();
    ((Intent)localObject).setComponent(new ComponentName(paramView.applicationInfo.packageName, paramView.name));
    paramExpandableListView = new Intent();
    ((Intent)localObject).setAction("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
    paramExpandableListView.putExtra("android.intent.extra.shortcut.INTENT", (Parcelable)localObject);
    paramExpandableListView.putExtra("android.intent.extra.shortcut.NAME", paramView.loadLabel(this.ˊ));
    localObject = new Intent.ShortcutIconResource();
    ((Intent.ShortcutIconResource)localObject).packageName = paramView.packageName;
    try
    {
      ((Intent.ShortcutIconResource)localObject).resourceName = this.ˊ.getResourcesForApplication(((Intent.ShortcutIconResource)localObject).packageName).getResourceName(paramView.getIconResource());
      paramExpandableListView.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", (Parcelable)localObject);
    }
    catch (PackageManager.NameNotFoundException|Resources.NotFoundException paramView)
    {
      for (;;) {}
    }
    setResult(-1, paramExpandableListView);
    finish();
    return true;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(5);
    setContentView(aM.ˏ.activity_activity_picker);
    getExpandableListView().setTextFilterEnabled(true);
    this.ˊ = getPackageManager();
    new if((byte)0).execute(new Void[0]);
  }
  
  public final class If
    implements Comparator<PackageInfo>
  {
    public If() {}
  }
  
  final class if
    extends AsyncTask<Void, Void, Void>
  {
    private static int ˎ = 0;
    private static int ˏ = 1;
    private static final byte[] ᐝ = { 51, -119, 64, -86, 15, -8, 16, -1, -4, -3, -52, 55, 14, 1, 8, -13, 11, 8, -68, 68, -1, -61, 36, 19, 4, 10, -8, 8, 0, -22, 22, 15, -11, 8, 0, 15, 0, 17, -34, 19, 4, 10, -8, 8, 0, -26, 39, -6, 11, 15, -8, 16, -1, -4, -3, -52, 55, 14, 1, 8, -13, 11, 8, -68, 68, -1, -61, 36, 19, 4, 10, -8, 8, 0, -26, 39, -6, 11, -13, 4, 10, -8, 8, 0, -21, 21, 14, -6 };
    private List<PackageInfo> ˊ;
    
    private if() {}
    
    /* Error */
    private static String ˊ(short paramShort1, short paramShort2, int paramInt)
    {
      // Byte code:
      //   0: goto +107 -> 107
      //   3: iload 6
      //   5: iconst_1
      //   6: iadd
      //   7: istore_1
      //   8: iload 5
      //   10: iconst_1
      //   11: iadd
      //   12: istore_0
      //   13: aload 9
      //   15: iload_0
      //   16: iload_2
      //   17: i2b
      //   18: bastore
      //   19: iload_0
      //   20: iload 7
      //   22: if_icmpne +6 -> 28
      //   25: goto +28 -> 53
      //   28: goto +12 -> 40
      //   31: iload_3
      //   32: iload_2
      //   33: iadd
      //   34: iconst_2
      //   35: isub
      //   36: istore_3
      //   37: goto +27 -> 64
      //   40: aload 8
      //   42: iload_1
      //   43: baload
      //   44: istore_3
      //   45: goto -14 -> 31
      //   48: astore 8
      //   50: aload 8
      //   52: athrow
      //   53: new 76	java/lang/String
      //   56: dup
      //   57: aload 9
      //   59: iconst_0
      //   60: invokespecial 79	java/lang/String:<init>	([BI)V
      //   63: areturn
      //   64: getstatic 59	com/actionlauncher/activitypicker/ActivityPickerActivity$if:ˎ	I
      //   67: bipush 59
      //   69: iadd
      //   70: istore 4
      //   72: iload 4
      //   74: sipush 128
      //   77: irem
      //   78: putstatic 61	com/actionlauncher/activitypicker/ActivityPickerActivity$if:ˏ	I
      //   81: iload_0
      //   82: istore_2
      //   83: iload_1
      //   84: istore 5
      //   86: iload 4
      //   88: iconst_2
      //   89: irem
      //   90: ifne +6 -> 96
      //   93: goto +91 -> 184
      //   96: iconst_0
      //   97: istore 4
      //   99: iload_2
      //   100: istore_0
      //   101: iload 5
      //   103: istore_1
      //   104: goto +41 -> 145
      //   107: bipush 112
      //   109: iload_2
      //   110: iconst_3
      //   111: imul
      //   112: isub
      //   113: istore_2
      //   114: iload_0
      //   115: iconst_4
      //   116: iadd
      //   117: istore 6
      //   119: iload_1
      //   120: bipush 11
      //   122: iadd
      //   123: istore_0
      //   124: iconst_m1
      //   125: istore 5
      //   127: getstatic 57	com/actionlauncher/activitypicker/ActivityPickerActivity$if:ᐝ	[B
      //   130: astore 8
      //   132: iload_0
      //   133: newarray byte
      //   135: astore 9
      //   137: iload_0
      //   138: iconst_1
      //   139: isub
      //   140: istore 7
      //   142: goto -139 -> 3
      //   145: iload_0
      //   146: istore 5
      //   148: iload_1
      //   149: istore 6
      //   151: iload_3
      //   152: istore_2
      //   153: iload 4
      //   155: tableswitch	default:+21->176, 0:+-152->3, 1:+40->195
      //   176: iload_0
      //   177: istore_2
      //   178: iload_1
      //   179: istore 5
      //   181: goto -85 -> 96
      //   184: iconst_1
      //   185: istore 4
      //   187: goto -42 -> 145
      //   190: astore 8
      //   192: aload 8
      //   194: athrow
      //   195: iload_1
      //   196: iconst_1
      //   197: iadd
      //   198: istore_1
      //   199: iload_0
      //   200: iconst_1
      //   201: iadd
      //   202: istore_0
      //   203: aload 9
      //   205: iload_0
      //   206: iload_3
      //   207: i2b
      //   208: bastore
      //   209: iload_0
      //   210: iload 7
      //   212: if_icmpne +6 -> 218
      //   215: goto -162 -> 53
      //   218: iload_3
      //   219: istore_2
      //   220: goto -180 -> 40
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	223	0	paramShort1	short
      //   0	223	1	paramShort2	short
      //   0	223	2	paramInt	int
      //   31	188	3	i	int
      //   70	116	4	j	int
      //   8	172	5	s1	short
      //   3	147	6	k	int
      //   20	193	7	s2	short
      //   40	1	8	localObject	Object
      //   48	3	8	localException1	Exception
      //   130	1	8	arrayOfByte1	byte[]
      //   190	3	8	localException2	Exception
      //   13	191	9	arrayOfByte2	byte[]
      // Exception table:
      //   from	to	target	type
      //   64	72	48	java/lang/Exception
      //   72	81	48	java/lang/Exception
      //   72	81	190	java/lang/Exception
    }
    
    private Void ˊ()
    {
      break label735;
      Collections.sort(this.ˊ, new ActivityPickerActivity.If(ActivityPickerActivity.this));
      return null;
      label23:
      int j = 6;
      break label617;
      label30:
      int i;
      label51:
      int n;
      switch (i)
      {
      default: 
        break;
        i = 41;
        break label823;
        if (n > 0) {}
        break;
      }
      for (;;)
      {
        try
        {
          s1 = (byte)ᐝ[7];
          short s2 = (byte)ᐝ[30];
          localObject4 = Class.forName(ˊ(s1, s2, (byte)(s2 >>> 2)).intern());
          s1 = (byte)-ᐝ[9];
          localObject3 = (PackageInfo)((Class)localObject4).getMethod(ˊ((short)31, s1, (byte)s1).intern(), new Class[] { String.class, Integer.TYPE }).invoke(localObject3, new Object[] { localObject5, Integer.valueOf(1) });
        }
        finally
        {
          try
          {
            localObject4 = ((PackageInfo)localObject3).activities;
            if (localObject4 != null) {
              break label51;
            }
            continue;
            m = 0;
            localObject3 = ((PackageInfo)localObject3).activities;
            n = localObject3.length;
            k = 0;
            i = 0;
            break label879;
            localCollection = finally;
            throw localCollection.getCause();
            if (i < n) {
              continue;
            }
            continue;
            i = ((PackageInfo)localObject3).activities.length;
            if (i != 0)
            {
              continue;
              switch (i)
              {
              }
              continue;
              j = 18;
              continue;
              if (j != 0)
              {
                continue;
                j = 42;
                continue;
                i = 39;
                continue;
                continue;
                i = 85;
                continue;
                i = 1;
                switch (i)
                {
                default: 
                  continue;
                  j = m;
                  switch (i)
                  {
                  }
                  i = k;
                  continue;
                  i = 0;
                  continue;
                  j = 1;
                  continue;
                  i = 0;
                  continue;
                  boolean bool = localObject3[i].exported;
                  if (bool) {
                    continue;
                  }
                  break label23;
                  i = ˎ + 71;
                  ˏ = i % 128;
                  if (i % 2 == 0) {
                    continue;
                  }
                  continue;
                  j = 60;
                  switch (j)
                  {
                  }
                  break;
                }
              }
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            short s1;
            Object localObject4;
            Object localObject3;
            Object localObject5;
            int m;
            int k;
            Iterator localIterator;
            Object localObject2;
            label617:
            label735:
            label823:
            continue;
          }
          try
          {
            localObject2 = localIterator.next();
            localObject3 = ActivityPickerActivity.ˊ(ActivityPickerActivity.this);
            localObject4 = Class.forName(ˊ((short)44, (byte)ᐝ[23], (byte)(ᐝ[24] + 1)).intern());
            s1 = (byte)ᐝ[28];
            localObject5 = ((Class)localObject4).getField(ˊ((short)73, s1, (byte)s1).intern()).get(localObject2);
          }
          catch (Exception localException1)
          {
            throw localException1;
          }
          i = 1;
          continue;
          i = 1;
          break label30;
          switch (i)
          {
          case 85: 
          default: 
            break;
          }
          try
          {
            localObject2 = this.ˊ;
            ((List)localObject2).removeAll(localCollection);
          }
          catch (Exception localException2)
          {
            throw localException2;
          }
          i = localCollection.size();
          if (i > 0) {
            continue;
          }
          continue;
          localCollection.add(localObject2);
          continue;
          switch (j)
          {
          case 18: 
          default: 
            continue;
            localCollection.add(localObject2);
            continue;
            if (localIterator.hasNext()) {
              continue;
            }
            continue;
            i = 56;
            continue;
            return null;
            j = m;
            switch (k)
            {
            case 0: 
            default: 
              continue;
              i = 9;
              continue;
              continue;
              i = 3;
              continue;
              i = 99;
              break;
              Object localObject1 = ActivityPickerActivity.this;
              localObject1 = ActivityPickerActivity.ˊ((ActivityPickerActivity)localObject1);
              localObject1 = ((PackageManager)localObject1).getInstalledPackages(0);
              this.ˊ = ((List)localObject1);
              localObject1 = new ArrayList();
              localObject2 = this.ˊ;
              localIterator = ((List)localObject2).iterator();
              break;
            case 1: 
              j = ˎ;
              j += 7;
              ˏ = j % 128;
              if (j % 2 != 0)
              {
                continue;
                switch (i)
                {
                }
                continue;
                k = 0;
              }
              break;
            }
            break;
          case 6: 
            i += 1;
            continue;
            continue;
            k = 1;
            continue;
          }
        }
        label879:
        j = ˏ + 29;
        ˎ = j % 128;
        if (j % 2 != 0) {}
      }
    }
    
    public final void onPreExecute()
    {
      ActivityPickerActivity.this.setProgressBarIndeterminateVisibility(true);
    }
  }
  
  public final class ˊ
    extends BaseExpandableListAdapter
  {
    private static int ʻ = 0;
    private static int ʼ = 1;
    private static final byte[] ʽ = { 73, -97, 21, -119, -15, 8, -16, 1, 4, 3, 52, -55, -14, -1, -8, 13, -11, -8, 68, -68, 1, 61, -36, -19, -4, -10, 8, -8, 0, 22, -22, -15, 11, -8, 0, -15, 0, -17, 34, -19, -4, -10, 8, -8, 0, 26, -39, 6, -11, -15, 8, -16, 1, 4, 3, 52, -55, -14, -1, -8, 13, -11, -8, 68, -68, 1, 61, -36, -19, -4, -10, 8, -8, 0, 26, -39, 6, -11, 13, -4, -10, 8, -8, 0, 21, -21, -14, 6 };
    private static int ͺ = 221;
    private final List<PackageInfo> ˊ;
    private final AbsListView.LayoutParams ˋ;
    private final AbsListView.LayoutParams ˎ;
    private final int ˏ;
    
    public ˊ()
    {
      Object localObject;
      this.ˊ = localObject;
      this.ˏ = ActivityPickerActivity.this.getResources().getDimensionPixelSize(17104896);
      this.ˋ = new AbsListView.LayoutParams(-1, -2);
      this.ˎ = new AbsListView.LayoutParams(-1, this.ˏ);
    }
    
    private ActivityInfo ˊ(int paramInt1, int paramInt2)
    {
      break label41;
      paramInt1 = 38;
      break label11;
      return null;
      for (;;)
      {
        label11:
        switch (paramInt1)
        {
        }
        return (ActivityInfo)((ArrayList)localObject).get(paramInt2);
        label41:
        Object localObject = this.ˊ.get(paramInt1);
        localObject = ˊ((String)Class.forName(ˊ((byte)(ͺ & 0x7), (short)44, (byte)-ʽ[23]).intern()).getField(ˊ((byte)ʽ[28], (byte)ʽ[0], (byte)ʽ[28]).intern()).get(localObject));
        if (localObject != null) {
          break;
        }
        paramInt1 = 84;
      }
    }
    
    private TextView ˊ()
    {
      TextView localTextView = new TextView(ActivityPickerActivity.this);
      localTextView.setLayoutParams(this.ˋ);
      localTextView.setGravity(19);
      localTextView.setPadding(this.ˏ, 0, 0, 0);
      return localTextView;
    }
    
    /* Error */
    private static String ˊ(short paramShort1, short paramShort2, short paramShort3)
    {
      // Byte code:
      //   0: goto +88 -> 88
      //   3: getstatic 65	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʼ	I
      //   6: istore_0
      //   7: iload_0
      //   8: bipush 7
      //   10: iadd
      //   11: istore 7
      //   13: iload 7
      //   15: sipush 128
      //   18: irem
      //   19: putstatic 63	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʻ	I
      //   22: iload 4
      //   24: istore_0
      //   25: iload 5
      //   27: istore_1
      //   28: iload_3
      //   29: istore_2
      //   30: iload 7
      //   32: iconst_2
      //   33: irem
      //   34: ifeq +6 -> 40
      //   37: goto +100 -> 137
      //   40: iload_2
      //   41: iconst_1
      //   42: iadd
      //   43: istore_3
      //   44: aload 9
      //   46: iload_0
      //   47: iload_1
      //   48: i2b
      //   49: bastore
      //   50: iload_0
      //   51: iload 6
      //   53: iconst_1
      //   54: isub
      //   55: if_icmpne +6 -> 61
      //   58: goto +6 -> 64
      //   61: goto +14 -> 75
      //   64: new 115	java/lang/String
      //   67: dup
      //   68: aload 9
      //   70: iconst_0
      //   71: invokespecial 160	java/lang/String:<init>	([BI)V
      //   74: areturn
      //   75: iload_0
      //   76: iconst_1
      //   77: iadd
      //   78: istore 4
      //   80: aload 8
      //   82: iload_3
      //   83: baload
      //   84: istore_0
      //   85: goto +42 -> 127
      //   88: iconst_0
      //   89: istore_3
      //   90: getstatic 59	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʽ	[B
      //   93: astore 8
      //   95: bipush 112
      //   97: iload_0
      //   98: iconst_3
      //   99: imul
      //   100: isub
      //   101: istore 4
      //   103: iload_2
      //   104: bipush 11
      //   106: iadd
      //   107: istore 6
      //   109: iload_1
      //   110: iconst_4
      //   111: iadd
      //   112: istore_2
      //   113: iload 6
      //   115: newarray byte
      //   117: astore 9
      //   119: iload_3
      //   120: istore_0
      //   121: iload 4
      //   123: istore_1
      //   124: goto -84 -> 40
      //   127: iload_1
      //   128: iload_0
      //   129: isub
      //   130: iconst_2
      //   131: isub
      //   132: istore 5
      //   134: goto -131 -> 3
      //   137: iload 4
      //   139: istore_0
      //   140: iload 5
      //   142: istore_1
      //   143: iload_3
      //   144: istore_2
      //   145: goto -105 -> 40
      //   148: astore 8
      //   150: aload 8
      //   152: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	153	0	paramShort1	short
      //   0	153	1	paramShort2	short
      //   0	153	2	paramShort3	short
      //   28	116	3	s1	short
      //   22	116	4	s2	short
      //   25	116	5	s3	short
      //   51	63	6	i	int
      //   11	23	7	j	int
      //   80	14	8	arrayOfByte1	byte[]
      //   148	3	8	localException	Exception
      //   44	74	9	arrayOfByte2	byte[]
      // Exception table:
      //   from	to	target	type
      //   3	7	148	java/lang/Exception
      //   13	22	148	java/lang/Exception
    }
    
    /* Error */
    private ArrayList<ActivityInfo> ˊ(String paramString)
    {
      // Byte code:
      //   0: goto +478 -> 478
      //   3: iconst_1
      //   4: istore 4
      //   6: goto +357 -> 363
      //   9: iload_3
      //   10: iconst_1
      //   11: iadd
      //   12: istore_3
      //   13: goto +290 -> 303
      //   16: aconst_null
      //   17: areturn
      //   18: bipush 41
      //   20: istore_3
      //   21: goto +225 -> 246
      //   24: getstatic 65	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʼ	I
      //   27: bipush 43
      //   29: iadd
      //   30: istore_3
      //   31: iload_3
      //   32: sipush 128
      //   35: irem
      //   36: putstatic 63	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʻ	I
      //   39: iload_3
      //   40: iconst_2
      //   41: irem
      //   42: ifeq +6 -> 48
      //   45: goto +158 -> 203
      //   48: goto +29 -> 77
      //   51: getstatic 65	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʼ	I
      //   54: bipush 101
      //   56: iadd
      //   57: istore_3
      //   58: iload_3
      //   59: sipush 128
      //   62: irem
      //   63: putstatic 63	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʻ	I
      //   66: iload_3
      //   67: iconst_2
      //   68: irem
      //   69: ifeq +6 -> 75
      //   72: goto +165 -> 237
      //   75: aload_1
      //   76: areturn
      //   77: getstatic 61	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ͺ	I
      //   80: bipush 7
      //   82: iand
      //   83: i2b
      //   84: getstatic 59	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʽ	[B
      //   87: bipush 13
      //   89: baload
      //   90: i2b
      //   91: getstatic 59	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʽ	[B
      //   94: bipush 29
      //   96: baload
      //   97: i2b
      //   98: invokestatic 113	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ˊ	(SSS)Ljava/lang/String;
      //   101: invokevirtual 119	java/lang/String:intern	()Ljava/lang/String;
      //   104: invokestatic 125	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   107: astore 7
      //   109: getstatic 59	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʽ	[B
      //   112: bipush 9
      //   114: baload
      //   115: i2b
      //   116: istore_2
      //   117: aload 7
      //   119: iload_2
      //   120: iload_2
      //   121: bipush 28
      //   123: ior
      //   124: i2b
      //   125: getstatic 59	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʽ	[B
      //   128: bipush 9
      //   130: baload
      //   131: i2b
      //   132: invokestatic 113	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ˊ	(SSS)Ljava/lang/String;
      //   135: invokevirtual 119	java/lang/String:intern	()Ljava/lang/String;
      //   138: iconst_2
      //   139: anewarray 121	java/lang/Class
      //   142: dup
      //   143: iconst_0
      //   144: ldc 115
      //   146: aastore
      //   147: dup
      //   148: iconst_1
      //   149: getstatic 168	java/lang/Integer:TYPE	Ljava/lang/Class;
      //   152: aastore
      //   153: invokevirtual 172	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   156: aload 8
      //   158: iconst_2
      //   159: anewarray 174	java/lang/Object
      //   162: dup
      //   163: iconst_0
      //   164: aload_1
      //   165: aastore
      //   166: dup
      //   167: iconst_1
      //   168: iconst_1
      //   169: invokestatic 178	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   172: aastore
      //   173: invokevirtual 184	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   176: checkcast 186	android/content/pm/PackageInfo
      //   179: astore 7
      //   181: aload 7
      //   183: getfield 190	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
      //   186: astore_1
      //   187: aload_1
      //   188: ifnull +6 -> 194
      //   191: goto +200 -> 391
      //   194: goto -176 -> 18
      //   197: bipush 86
      //   199: istore_3
      //   200: goto +73 -> 273
      //   203: goto -126 -> 77
      //   206: new 101	java/util/ArrayList
      //   209: dup
      //   210: invokespecial 191	java/util/ArrayList:<init>	()V
      //   213: astore_1
      //   214: aload 7
      //   216: getfield 190	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
      //   219: astore 7
      //   221: aload 7
      //   223: arraylength
      //   224: istore 5
      //   226: iconst_0
      //   227: istore_3
      //   228: goto +87 -> 315
      //   231: goto +72 -> 303
      //   234: astore_1
      //   235: aload_1
      //   236: athrow
      //   237: aload_1
      //   238: areturn
      //   239: bipush 20
      //   241: istore 4
      //   243: goto +164 -> 407
      //   246: iload_3
      //   247: lookupswitch	default:+17->264, 41:+243->490
      //   264: goto -58 -> 206
      //   267: iconst_0
      //   268: istore 4
      //   270: goto +93 -> 363
      //   273: iload_3
      //   274: lookupswitch	default:+26->300, 86:+-223->51, 99:+-258->16
      //   300: goto -103 -> 197
      //   303: iload_3
      //   304: iload 5
      //   306: if_icmpge +6 -> 312
      //   309: goto -42 -> 267
      //   312: goto -309 -> 3
      //   315: getstatic 65	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʼ	I
      //   318: bipush 29
      //   320: iadd
      //   321: istore 4
      //   323: iload 4
      //   325: sipush 128
      //   328: irem
      //   329: putstatic 63	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ʻ	I
      //   332: iload 4
      //   334: iconst_2
      //   335: irem
      //   336: ifeq +6 -> 342
      //   339: goto -108 -> 231
      //   342: goto -39 -> 303
      //   345: aconst_null
      //   346: areturn
      //   347: aload_1
      //   348: aload 8
      //   350: invokevirtual 195	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   353: pop
      //   354: goto -345 -> 9
      //   357: astore_1
      //   358: aload_1
      //   359: invokevirtual 201	java/lang/Throwable:getCause	()Ljava/lang/Throwable;
      //   362: athrow
      //   363: iload 4
      //   365: tableswitch	default:+23->388, 0:+89->454, 1:+74->439
      //   388: goto -385 -> 3
      //   391: bipush 27
      //   393: istore_3
      //   394: goto -148 -> 246
      //   397: bipush 99
      //   399: istore_3
      //   400: goto -127 -> 273
      //   403: bipush 47
      //   405: istore 4
      //   407: iload 4
      //   409: lookupswitch	default:+27->436, 20:+-62->347, 47:+-400->9
      //   436: goto -197 -> 239
      //   439: aload_1
      //   440: invokevirtual 205	java/util/ArrayList:size	()I
      //   443: istore_3
      //   444: iload_3
      //   445: ifle +6 -> 451
      //   448: goto -251 -> 197
      //   451: goto -54 -> 397
      //   454: aload 7
      //   456: iload_3
      //   457: aaload
      //   458: astore 8
      //   460: aload 8
      //   462: getfield 209	android/content/pm/ActivityInfo:exported	Z
      //   465: istore 6
      //   467: iload 6
      //   469: ifeq +6 -> 475
      //   472: goto -233 -> 239
      //   475: goto -72 -> 403
      //   478: aload_0
      //   479: getfield 70	com/actionlauncher/activitypicker/ActivityPickerActivity$ˊ:ᐝ	Lcom/actionlauncher/activitypicker/ActivityPickerActivity;
      //   482: invokestatic 212	com/actionlauncher/activitypicker/ActivityPickerActivity:ˊ	(Lcom/actionlauncher/activitypicker/ActivityPickerActivity;)Landroid/content/pm/PackageManager;
      //   485: astore 8
      //   487: goto -463 -> 24
      //   490: aconst_null
      //   491: areturn
      //   492: astore_1
      //   493: goto -148 -> 345
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	496	0	this	ˊ
      //   0	496	1	paramString	String
      //   116	8	2	s	short
      //   9	448	3	i	int
      //   4	404	4	j	int
      //   224	83	5	k	int
      //   465	3	6	bool	boolean
      //   107	348	7	localObject1	Object
      //   156	330	8	localObject2	Object
      // Exception table:
      //   from	to	target	type
      //   24	31	234	java/lang/Exception
      //   31	39	234	java/lang/Exception
      //   77	181	357	finally
      //   181	187	492	android/content/pm/PackageManager$NameNotFoundException
      //   206	226	492	android/content/pm/PackageManager$NameNotFoundException
      //   347	354	492	android/content/pm/PackageManager$NameNotFoundException
      //   358	363	492	android/content/pm/PackageManager$NameNotFoundException
      //   439	444	492	android/content/pm/PackageManager$NameNotFoundException
      //   460	467	492	android/content/pm/PackageManager$NameNotFoundException
      //   478	487	492	android/content/pm/PackageManager$NameNotFoundException
    }
    
    public final long getChildId(int paramInt1, int paramInt2)
    {
      return paramInt2;
    }
    
    public final View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
    {
      paramView = ˊ();
      paramViewGroup = ˊ(paramInt1, paramInt2);
      if (paramViewGroup != null)
      {
        String str = paramViewGroup.name.replace(paramViewGroup.packageName, "");
        paramViewGroup = paramViewGroup.loadLabel(ActivityPickerActivity.ˊ(ActivityPickerActivity.this));
        str = paramViewGroup + "\n" + str;
        SpannableString localSpannableString = new SpannableString(str);
        localSpannableString.setSpan(new RelativeSizeSpan(0.7F), paramViewGroup.length() + 1, str.length(), 0);
        paramView.setText(localSpannableString);
        paramView.setLayoutParams(this.ˎ);
      }
      return paramView;
    }
    
    public final int getChildrenCount(int paramInt)
    {
      try
      {
        Object localObject1 = this.ˊ;
        try
        {
          localObject1 = ((List)localObject1).get(paramInt);
          paramInt = ͺ;
          short s1 = (byte)(paramInt & 0x7);
          Object localObject2 = ʽ;
          short s2 = (byte)-localObject2[23];
          localObject2 = ˊ(s1, (short)44, s2);
          localObject2 = ((String)localObject2).intern();
          localObject2 = Class.forName((String)localObject2);
          paramInt = ʽ[28];
          s1 = (byte)paramInt;
          Object localObject3 = ʽ;
          s2 = (byte)localObject3[0];
          localObject3 = ʽ;
          short s3 = (byte)localObject3[28];
          localObject3 = ˊ(s1, s2, s3);
          localObject3 = ((String)localObject3).intern();
          localObject2 = ((Class)localObject2).getField((String)localObject3);
          localObject1 = ((Field)localObject2).get(localObject1);
          localObject1 = (String)localObject1;
          localObject1 = ˊ((String)localObject1);
          if (localObject1 != null) {
            break label224;
          }
        }
        catch (Exception localException1)
        {
          throw localException1;
        }
        return 0;
      }
      catch (Exception localException2)
      {
        label224:
        label230:
        throw localException2;
      }
      paramInt = ʻ;
      paramInt += 53;
      ʼ = paramInt % 128;
      if (paramInt % 2 != 0) {
        break label230;
      }
      for (;;)
      {
        switch (paramInt)
        {
        }
        break;
        paramInt = 41;
        continue;
        break label230;
        paramInt = 42;
      }
      paramInt = localException1.size();
      return paramInt;
    }
    
    public final int getGroupCount()
    {
      return this.ˊ.size();
    }
    
    public final long getGroupId(int paramInt)
    {
      return paramInt;
    }
    
    public final View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
    {
      paramView = ˊ();
      paramViewGroup = (PackageInfo)this.ˊ.get(paramInt);
      paramView.setText(paramViewGroup.applicationInfo.loadLabel(ActivityPickerActivity.ˊ(ActivityPickerActivity.this)));
      paramView.setCompoundDrawablesWithIntrinsicBounds(ActivityPickerActivity.ˊ(paramViewGroup.applicationInfo.loadIcon(ActivityPickerActivity.ˊ(ActivityPickerActivity.this)), ActivityPickerActivity.this), null, null, null);
      return paramView;
    }
    
    public final boolean hasStableIds()
    {
      return true;
    }
    
    public final boolean isChildSelectable(int paramInt1, int paramInt2)
    {
      return true;
    }
  }
}
