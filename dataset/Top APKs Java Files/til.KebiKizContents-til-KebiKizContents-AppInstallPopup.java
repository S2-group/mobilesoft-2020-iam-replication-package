package til.KebiKizContents;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.kebikids.common.G;
import com.kebikids.common.SoundManager;
import com.kebikids.moreapps.MoreAppsData;
import com.kebikids.moreapps.MoreAppsDataDownloader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"HandlerLeak", "HandlerLeak"})
public class AppInstallPopup
  extends Activity
{
  private RelativeLayout layout;
  private Handler loadErrorHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      G.dismissProgressDialog();
      AppInstallPopup.this.finish();
    }
  };
  private final Activity mActivity = this;
  private MoreAppsData moreAppsData = new MoreAppsData();
  private Handler moreAppsInfoLoadFinished = new Handler()
  {
    /* Error */
    public void handleMessage(Message paramAnonymousMessage)
    {
      // Byte code:
      //   0: invokestatic 29	com/kebikids/common/G:dismissProgressDialog	()V
      //   3: aconst_null
      //   4: astore 11
      //   6: aconst_null
      //   7: astore 5
      //   9: aconst_null
      //   10: astore 9
      //   12: aconst_null
      //   13: astore 10
      //   15: aload 5
      //   17: astore 8
      //   19: aload 11
      //   21: astore 6
      //   23: aload 9
      //   25: astore 7
      //   27: aload_0
      //   28: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   31: invokestatic 32	til/KebiKizContents/AppInstallPopup:access$0	(Ltil/KebiKizContents/AppInstallPopup;)Ljava/util/ArrayList;
      //   34: invokevirtual 37	java/util/ArrayList:clear	()V
      //   37: aload 5
      //   39: astore 8
      //   41: aload 11
      //   43: astore 6
      //   45: aload 9
      //   47: astore 7
      //   49: aload_0
      //   50: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   53: invokestatic 41	til/KebiKizContents/AppInstallPopup:access$1	(Ltil/KebiKizContents/AppInstallPopup;)Landroid/app/Activity;
      //   56: ldc 43
      //   58: invokevirtual 49	android/app/Activity:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
      //   61: astore 5
      //   63: aload 5
      //   65: astore 8
      //   67: aload 5
      //   69: astore 6
      //   71: aload 9
      //   73: astore 7
      //   75: new 51	java/io/ObjectInputStream
      //   78: dup
      //   79: aload 5
      //   81: invokespecial 54	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
      //   84: astore 9
      //   86: aload_0
      //   87: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   90: aload 9
      //   92: invokevirtual 58	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
      //   95: checkcast 60	com/kebikids/moreapps/MoreAppsData
      //   98: invokestatic 64	til/KebiKizContents/AppInstallPopup:access$2	(Ltil/KebiKizContents/AppInstallPopup;Lcom/kebikids/moreapps/MoreAppsData;)V
      //   101: aload_0
      //   102: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   105: invokestatic 68	til/KebiKizContents/AppInstallPopup:access$3	(Ltil/KebiKizContents/AppInstallPopup;)Lcom/kebikids/moreapps/MoreAppsData;
      //   108: new 34	java/util/ArrayList
      //   111: dup
      //   112: invokespecial 69	java/util/ArrayList:<init>	()V
      //   115: putfield 73	com/kebikids/moreapps/MoreAppsData:isSetup	Ljava/util/ArrayList;
      //   118: iconst_0
      //   119: istore_2
      //   120: iload_2
      //   121: aload_0
      //   122: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   125: invokestatic 68	til/KebiKizContents/AppInstallPopup:access$3	(Ltil/KebiKizContents/AppInstallPopup;)Lcom/kebikids/moreapps/MoreAppsData;
      //   128: getfield 76	com/kebikids/moreapps/MoreAppsData:appLinks	Ljava/util/ArrayList;
      //   131: invokevirtual 80	java/util/ArrayList:size	()I
      //   134: if_icmplt +57 -> 191
      //   137: iconst_0
      //   138: istore_2
      //   139: aload_0
      //   140: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   143: invokestatic 32	til/KebiKizContents/AppInstallPopup:access$0	(Ltil/KebiKizContents/AppInstallPopup;)Ljava/util/ArrayList;
      //   146: invokevirtual 80	java/util/ArrayList:size	()I
      //   149: ifne +87 -> 236
      //   152: iconst_0
      //   153: istore_2
      //   154: iload_2
      //   155: ifne +128 -> 283
      //   158: aload_0
      //   159: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   162: invokevirtual 83	til/KebiKizContents/AppInstallPopup:finish	()V
      //   165: aload 9
      //   167: ifnull +8 -> 175
      //   170: aload 9
      //   172: invokevirtual 86	java/io/ObjectInputStream:close	()V
      //   175: aload 5
      //   177: ifnull +450 -> 627
      //   180: aload 5
      //   182: invokevirtual 89	java/io/FileInputStream:close	()V
      //   185: aload_0
      //   186: aload_1
      //   187: invokespecial 91	android/os/Handler:handleMessage	(Landroid/os/Message;)V
      //   190: return
      //   191: aload_0
      //   192: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   195: aload_0
      //   196: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   199: invokestatic 68	til/KebiKizContents/AppInstallPopup:access$3	(Ltil/KebiKizContents/AppInstallPopup;)Lcom/kebikids/moreapps/MoreAppsData;
      //   202: getfield 76	com/kebikids/moreapps/MoreAppsData:appLinks	Ljava/util/ArrayList;
      //   205: iload_2
      //   206: invokevirtual 95	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   209: checkcast 97	java/lang/String
      //   212: invokestatic 101	til/KebiKizContents/AppInstallPopup:access$4	(Ltil/KebiKizContents/AppInstallPopup;Ljava/lang/String;)Z
      //   215: ifne +428 -> 643
      //   218: aload_0
      //   219: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   222: invokestatic 32	til/KebiKizContents/AppInstallPopup:access$0	(Ltil/KebiKizContents/AppInstallPopup;)Ljava/util/ArrayList;
      //   225: iload_2
      //   226: invokestatic 107	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   229: invokevirtual 111	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   232: pop
      //   233: goto +410 -> 643
      //   236: aload_0
      //   237: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   240: invokestatic 32	til/KebiKizContents/AppInstallPopup:access$0	(Ltil/KebiKizContents/AppInstallPopup;)Ljava/util/ArrayList;
      //   243: invokevirtual 80	java/util/ArrayList:size	()I
      //   246: iconst_3
      //   247: if_icmpge +17 -> 264
      //   250: aload_0
      //   251: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   254: invokestatic 32	til/KebiKizContents/AppInstallPopup:access$0	(Ltil/KebiKizContents/AppInstallPopup;)Ljava/util/ArrayList;
      //   257: invokevirtual 80	java/util/ArrayList:size	()I
      //   260: istore_2
      //   261: goto -107 -> 154
      //   264: aload_0
      //   265: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   268: invokestatic 32	til/KebiKizContents/AppInstallPopup:access$0	(Ltil/KebiKizContents/AppInstallPopup;)Ljava/util/ArrayList;
      //   271: invokevirtual 80	java/util/ArrayList:size	()I
      //   274: iconst_3
      //   275: if_icmplt -121 -> 154
      //   278: iconst_3
      //   279: istore_2
      //   280: goto -126 -> 154
      //   283: new 113	java/util/Random
      //   286: dup
      //   287: invokespecial 114	java/util/Random:<init>	()V
      //   290: astore 6
      //   292: iconst_0
      //   293: istore_3
      //   294: iload_3
      //   295: bipush 10
      //   297: if_icmplt +178 -> 475
      //   300: aload_0
      //   301: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   304: invokestatic 117	til/KebiKizContents/AppInstallPopup:access$5	(Ltil/KebiKizContents/AppInstallPopup;)V
      //   307: iconst_0
      //   308: istore_3
      //   309: iload_3
      //   310: iload_2
      //   311: if_icmpge -146 -> 165
      //   314: new 119	til/KebiKizContents/AppInstallPopup$AppThum
      //   317: dup
      //   318: aload_0
      //   319: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   322: aload_0
      //   323: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   326: invokestatic 41	til/KebiKizContents/AppInstallPopup:access$1	(Ltil/KebiKizContents/AppInstallPopup;)Landroid/app/Activity;
      //   329: invokespecial 122	til/KebiKizContents/AppInstallPopup$AppThum:<init>	(Ltil/KebiKizContents/AppInstallPopup;Landroid/content/Context;)V
      //   332: astore 6
      //   334: aload_0
      //   335: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   338: invokestatic 68	til/KebiKizContents/AppInstallPopup:access$3	(Ltil/KebiKizContents/AppInstallPopup;)Lcom/kebikids/moreapps/MoreAppsData;
      //   341: getfield 125	com/kebikids/moreapps/MoreAppsData:thumBytes	Ljava/util/ArrayList;
      //   344: aload_0
      //   345: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   348: invokestatic 32	til/KebiKizContents/AppInstallPopup:access$0	(Ltil/KebiKizContents/AppInstallPopup;)Ljava/util/ArrayList;
      //   351: iload_3
      //   352: invokevirtual 95	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   355: checkcast 103	java/lang/Integer
      //   358: invokevirtual 128	java/lang/Integer:intValue	()I
      //   361: invokevirtual 95	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   364: checkcast 130	[B
      //   367: astore 7
      //   369: aload 7
      //   371: ifnull +44 -> 415
      //   374: new 132	android/graphics/drawable/BitmapDrawable
      //   377: dup
      //   378: aload_0
      //   379: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   382: aload 7
      //   384: invokestatic 136	til/KebiKizContents/AppInstallPopup:access$6	(Ltil/KebiKizContents/AppInstallPopup;[B)Landroid/graphics/Bitmap;
      //   387: invokespecial 139	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
      //   390: astore 7
      //   392: aload 6
      //   394: getfield 143	til/KebiKizContents/AppInstallPopup$AppThum:thumImg	Landroid/widget/ImageView;
      //   397: aload 7
      //   399: invokevirtual 149	android/widget/ImageView:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
      //   402: aload 6
      //   404: new 8	til/KebiKizContents/AppInstallPopup$2$1
      //   407: dup
      //   408: aload_0
      //   409: invokespecial 152	til/KebiKizContents/AppInstallPopup$2$1:<init>	(Ltil/KebiKizContents/AppInstallPopup$2;)V
      //   412: invokevirtual 156	til/KebiKizContents/AppInstallPopup$AppThum:setOnClickListener	(Landroid/view/View$OnClickListener;)V
      //   415: aload 6
      //   417: getfield 160	til/KebiKizContents/AppInstallPopup$AppThum:titleTxt	Landroid/widget/TextView;
      //   420: aload_0
      //   421: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   424: invokestatic 68	til/KebiKizContents/AppInstallPopup:access$3	(Ltil/KebiKizContents/AppInstallPopup;)Lcom/kebikids/moreapps/MoreAppsData;
      //   427: getfield 163	com/kebikids/moreapps/MoreAppsData:titles	Ljava/util/ArrayList;
      //   430: aload_0
      //   431: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   434: invokestatic 32	til/KebiKizContents/AppInstallPopup:access$0	(Ltil/KebiKizContents/AppInstallPopup;)Ljava/util/ArrayList;
      //   437: iload_3
      //   438: invokevirtual 95	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   441: checkcast 103	java/lang/Integer
      //   444: invokevirtual 128	java/lang/Integer:intValue	()I
      //   447: invokevirtual 95	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   450: checkcast 165	java/lang/CharSequence
      //   453: invokevirtual 171	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
      //   456: aload_0
      //   457: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   460: invokestatic 175	til/KebiKizContents/AppInstallPopup:access$7	(Ltil/KebiKizContents/AppInstallPopup;)Landroid/widget/LinearLayout;
      //   463: aload 6
      //   465: invokevirtual 181	android/widget/LinearLayout:addView	(Landroid/view/View;)V
      //   468: iload_3
      //   469: iconst_1
      //   470: iadd
      //   471: istore_3
      //   472: goto -163 -> 309
      //   475: aload 6
      //   477: aload_0
      //   478: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   481: invokestatic 32	til/KebiKizContents/AppInstallPopup:access$0	(Ltil/KebiKizContents/AppInstallPopup;)Ljava/util/ArrayList;
      //   484: invokevirtual 80	java/util/ArrayList:size	()I
      //   487: invokevirtual 185	java/util/Random:nextInt	(I)I
      //   490: istore 4
      //   492: aload_0
      //   493: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   496: invokestatic 32	til/KebiKizContents/AppInstallPopup:access$0	(Ltil/KebiKizContents/AppInstallPopup;)Ljava/util/ArrayList;
      //   499: aload_0
      //   500: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   503: invokestatic 32	til/KebiKizContents/AppInstallPopup:access$0	(Ltil/KebiKizContents/AppInstallPopup;)Ljava/util/ArrayList;
      //   506: iload 4
      //   508: invokevirtual 188	java/util/ArrayList:remove	(I)Ljava/lang/Object;
      //   511: checkcast 103	java/lang/Integer
      //   514: invokevirtual 111	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   517: pop
      //   518: iload_3
      //   519: iconst_1
      //   520: iadd
      //   521: istore_3
      //   522: goto -228 -> 294
      //   525: astore 5
      //   527: aload 10
      //   529: astore 9
      //   531: aload 8
      //   533: astore 5
      //   535: aload 5
      //   537: astore 6
      //   539: aload 9
      //   541: astore 7
      //   543: aload_0
      //   544: getfield 14	til/KebiKizContents/AppInstallPopup$2:this$0	Ltil/KebiKizContents/AppInstallPopup;
      //   547: invokevirtual 83	til/KebiKizContents/AppInstallPopup:finish	()V
      //   550: aload 9
      //   552: ifnull +8 -> 560
      //   555: aload 9
      //   557: invokevirtual 86	java/io/ObjectInputStream:close	()V
      //   560: aload 5
      //   562: ifnull -377 -> 185
      //   565: aload 5
      //   567: invokevirtual 89	java/io/FileInputStream:close	()V
      //   570: goto -385 -> 185
      //   573: astore 5
      //   575: aload 5
      //   577: invokevirtual 191	java/lang/Exception:printStackTrace	()V
      //   580: goto -395 -> 185
      //   583: astore_1
      //   584: aload 6
      //   586: astore 5
      //   588: aload 7
      //   590: ifnull +8 -> 598
      //   593: aload 7
      //   595: invokevirtual 86	java/io/ObjectInputStream:close	()V
      //   598: aload 5
      //   600: ifnull +8 -> 608
      //   603: aload 5
      //   605: invokevirtual 89	java/io/FileInputStream:close	()V
      //   608: aload_1
      //   609: athrow
      //   610: astore 5
      //   612: aload 5
      //   614: invokevirtual 191	java/lang/Exception:printStackTrace	()V
      //   617: goto -9 -> 608
      //   620: astore 5
      //   622: aload 5
      //   624: invokevirtual 191	java/lang/Exception:printStackTrace	()V
      //   627: goto -442 -> 185
      //   630: astore_1
      //   631: aload 9
      //   633: astore 7
      //   635: goto -47 -> 588
      //   638: astore 6
      //   640: goto -105 -> 535
      //   643: iload_2
      //   644: iconst_1
      //   645: iadd
      //   646: istore_2
      //   647: goto -527 -> 120
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	650	0	this	2
      //   0	650	1	paramAnonymousMessage	Message
      //   119	528	2	i	int
      //   293	229	3	j	int
      //   490	17	4	k	int
      //   7	174	5	localFileInputStream1	java.io.FileInputStream
      //   525	1	5	localException1	Exception
      //   533	33	5	localObject1	Object
      //   573	3	5	localException2	Exception
      //   586	18	5	localObject2	Object
      //   610	3	5	localException3	Exception
      //   620	3	5	localException4	Exception
      //   21	564	6	localObject3	Object
      //   638	1	6	localException5	Exception
      //   25	609	7	localObject4	Object
      //   17	515	8	localFileInputStream2	java.io.FileInputStream
      //   10	622	9	localObject5	Object
      //   13	515	10	localObject6	Object
      //   4	38	11	localObject7	Object
      // Exception table:
      //   from	to	target	type
      //   27	37	525	java/lang/Exception
      //   49	63	525	java/lang/Exception
      //   75	86	525	java/lang/Exception
      //   555	560	573	java/lang/Exception
      //   565	570	573	java/lang/Exception
      //   27	37	583	finally
      //   49	63	583	finally
      //   75	86	583	finally
      //   543	550	583	finally
      //   593	598	610	java/lang/Exception
      //   603	608	610	java/lang/Exception
      //   170	175	620	java/lang/Exception
      //   180	185	620	java/lang/Exception
      //   86	118	630	finally
      //   120	137	630	finally
      //   139	152	630	finally
      //   158	165	630	finally
      //   191	233	630	finally
      //   236	261	630	finally
      //   264	278	630	finally
      //   283	292	630	finally
      //   300	307	630	finally
      //   314	369	630	finally
      //   374	415	630	finally
      //   415	468	630	finally
      //   475	518	630	finally
      //   86	118	638	java/lang/Exception
      //   120	137	638	java/lang/Exception
      //   139	152	638	java/lang/Exception
      //   158	165	638	java/lang/Exception
      //   191	233	638	java/lang/Exception
      //   236	261	638	java/lang/Exception
      //   264	278	638	java/lang/Exception
      //   283	292	638	java/lang/Exception
      //   300	307	638	java/lang/Exception
      //   314	369	638	java/lang/Exception
      //   374	415	638	java/lang/Exception
      //   415	468	638	java/lang/Exception
      //   475	518	638	java/lang/Exception
    }
  };
  private ArrayList<Integer> notInstalledAppIdxs = new ArrayList();
  private RelativeLayout.LayoutParams params;
  private LinearLayout thumLayout;
  
  public AppInstallPopup() {}
  
  private Bitmap byteToBitmap(byte[] paramArrayOfByte)
  {
    return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  private boolean checkAppIsInstalled(String paramString)
  {
    Iterator localIterator = this.mActivity.getPackageManager().getInstalledApplications(128).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!paramString.equals(((ApplicationInfo)localIterator.next()).packageName));
    return true;
  }
  
  private void uiSetting()
  {
    this.layout = new RelativeLayout(this.mActivity);
    this.params = new RelativeLayout.LayoutParams(-2, -2);
    this.layout.setLayoutParams(this.params);
    setContentView(this.layout);
    Object localObject = new ImageView(this.mActivity);
    ((ImageView)localObject).setBackgroundColor(Color.argb(150, 0, 0, 0));
    this.params = new RelativeLayout.LayoutParams(G.cWH(800, 1280), G.cWH(480, 720));
    this.params.setMargins(G.cX(0, 0), G.cY(0, 0), 0, 0);
    ((ImageView)localObject).setLayoutParams(this.params);
    this.layout.addView((View)localObject);
    ((ImageView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        G.dismissProgressDialog();
        SoundManager.soundPlay(1);
        AppInstallPopup.this.finish();
        AppInstallPopup.this.overridePendingTransition(0, 0);
      }
    });
    localObject = new ImageView(this.mActivity);
    ((ImageView)localObject).setBackgroundDrawable(G.getDrawableFromAssets("AppInstallPopup/Background.png", true));
    this.params = new RelativeLayout.LayoutParams(G.cWH(544, 604), G.cWH(288, 320));
    this.params.setMargins(G.cX(128, 338), G.cY(96, 200), 0, 0);
    ((ImageView)localObject).setLayoutParams(this.params);
    this.layout.addView((View)localObject);
    this.thumLayout = new LinearLayout(this.mActivity);
    this.thumLayout.setGravity(17);
    this.params = new RelativeLayout.LayoutParams(G.cWH(340, 386), G.cWH(140, 150));
    this.params.setMargins(G.cX(233, 450), G.cY(140, 262), 0, 0);
    this.thumLayout.setLayoutParams(this.params);
    this.layout.addView(this.thumLayout);
    localObject = new Button(this.mActivity);
    ((Button)localObject).setBackgroundDrawable(G.getDrawableFromAssets("AppInstallPopup/GoStoreBt_Normal.png", true));
    this.params = new RelativeLayout.LayoutParams(G.cWH(185, 206), G.cWH(41, 46));
    this.params.setMargins(G.cX(308, 548), G.cY(298, 428), 0, 0);
    ((Button)localObject).setLayoutParams(this.params);
    this.layout.addView((View)localObject);
    ((Button)localObject).setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if (paramAnonymousMotionEvent.getAction() == 0) {
          paramAnonymousView.setBackgroundDrawable(G.getDrawableFromAssets("AppInstallPopup/GoStoreBt_Down.png", true));
        }
        for (;;)
        {
          return false;
          if (paramAnonymousMotionEvent.getAction() == 1) {
            paramAnonymousView.setBackgroundDrawable(G.getDrawableFromAssets("AppInstallPopup/GoStoreBt_Normal.png", true));
          }
        }
      }
    });
    ((Button)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (G.doubleClickDefense(paramAnonymousView, 500))
        {
          SoundManager.soundPlay(2);
          paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse((String)AppInstallPopup.this.moreAppsData.links.get(((Integer)AppInstallPopup.this.notInstalledAppIdxs.get(0)).intValue())));
          AppInstallPopup.this.mActivity.startActivity(paramAnonymousView);
          AppInstallPopup.this.finish();
          AppInstallPopup.this.overridePendingTransition(0, 0);
        }
      }
    });
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().addFlags(128);
    G.showProgressDialog(this.mActivity, "잠시만 기다려 주십시오.");
    new MoreAppsDataDownloader(this, this.moreAppsInfoLoadFinished, this.loadErrorHandler).dataDownload(false, 0);
  }
  
  protected void onDestroy()
  {
    G.recursiveRecycle(getWindow().getDecorView());
    System.gc();
    super.onDestroy();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  class AppThum
    extends RelativeLayout
  {
    public ImageView thumImg;
    public TextView titleTxt;
    
    public AppThum(Context paramContext)
    {
      super();
      ImageView localImageView = new ImageView(paramContext);
      AppInstallPopup.this.params = new RelativeLayout.LayoutParams(G.cWH(112, 124), G.cWH(132, 150));
      localImageView.setLayoutParams(AppInstallPopup.this.params);
      addView(localImageView);
      localImageView = new ImageView(paramContext);
      localImageView.setBackgroundDrawable(G.getDrawableFromAssets("AppInstallPopup/ThumFrame.png", true));
      AppInstallPopup.this.params = new RelativeLayout.LayoutParams(G.cWH(112, 124), G.cWH(112, 124));
      localImageView.setLayoutParams(AppInstallPopup.this.params);
      addView(localImageView);
      this.thumImg = new ImageView(paramContext);
      AppInstallPopup.this.params = new RelativeLayout.LayoutParams(G.cWH(100, 112), G.cWH(100, 112));
      AppInstallPopup.this.params.setMargins(G.cWH(6, 6), G.cWH(6, 6), 0, 0);
      this.thumImg.setLayoutParams(AppInstallPopup.this.params);
      addView(this.thumImg);
      this.titleTxt = new TextView(paramContext);
      AppInstallPopup.this.params = new RelativeLayout.LayoutParams(G.cWH(112, 124), G.cWH(20, 26));
      AppInstallPopup.this.params.setMargins(0, G.cWH(112, 122), 0, 0);
      this.titleTxt.setLayoutParams(AppInstallPopup.this.params);
      this.titleTxt.setTextColor(-16777216);
      this.titleTxt.setGravity(17);
      this.titleTxt.setTextSize(0, G.cWH(14, 16));
      addView(this.titleTxt);
    }
  }
}
