package til.KebiKizContents;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.kebikids.common.G;
import com.kebikids.common.SoundManager;
import com.kebikids.moreapps.MoreAppsData;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.NodeList;

@SuppressLint({"HandlerLeak", "HandlerLeak", "HandlerLeak", "FloatMath"})
public class SubCatePage
  extends Activity
{
  private boolean allBtLock = false;
  private HashMap<String, String[]> appInfos = new HashMap();
  private String cat1Name;
  private ArrayList<String[]> cat2Names = new ArrayList();
  private Drawable cellBG = G.getDrawableFromAssets("SubCatePage/CellBG.png", true);
  private boolean isFirstRunning = true;
  private NodeList kebiNodes;
  private RelativeLayout layout;
  private ListAdapter listAdapter;
  private Activity mActivity;
  private MoreAppsData moreAppsData = null;
  private int needDownloadThumCnt;
  private HashMap<String, Boolean> newMark = new HashMap();
  private RelativeLayout.LayoutParams params;
  private ListView subCateList;
  Handler thumImageDownloadFinished = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      SubCatePage.this.listAdapter.notifyDataSetChanged();
      super.handleMessage(paramAnonymousMessage);
    }
  };
  private boolean thumLoadComplete = false;
  
  public SubCatePage() {}
  
  private void subCatBtClick(final View paramView)
  {
    SoundManager.soundPlay(6);
    this.allBtLock = true;
    Object localObject2;
    if (this.appInfos.get(((String[])this.cat2Names.get(((Integer)paramView.getTag(2130968577)).intValue()))[0]) == null)
    {
      localObject1 = new ScaleAnimation(1.1F, 1.0F, 1.1F, 1.0F, G.cWH(85.0F, 85.0F), G.cWH(85.0F, 85.0F));
      ((ScaleAnimation)localObject1).setFillAfter(true);
      ((ScaleAnimation)localObject1).setDuration(200L);
      ((ScaleAnimation)localObject1).setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          paramAnonymousAnimation = new Intent(SubCatePage.this.mActivity, ContentListPage.class);
          paramAnonymousAnimation.putExtra("Cat1Name", SubCatePage.this.cat1Name);
          paramAnonymousAnimation.putExtra("Cat2Name", ((String[])SubCatePage.this.cat2Names.get(((Integer)paramView.getTag(2130968577)).intValue()))[0]);
          SubCatePage.this.mActivity.startActivity(paramAnonymousAnimation);
          SubCatePage.this.overridePendingTransition(0, 0);
        }
        
        public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
        
        public void onAnimationStart(Animation paramAnonymousAnimation) {}
      });
      localObject2 = new ScaleAnimation(1.0F, 1.1F, 1.0F, 1.1F, G.cWH(85.0F, 85.0F), G.cWH(85.0F, 85.0F));
      ((ScaleAnimation)localObject2).setFillAfter(true);
      ((ScaleAnimation)localObject2).setDuration(200L);
      ((ScaleAnimation)localObject2).setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          paramView.startAnimation(this.val$toSmall);
        }
        
        public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
        
        public void onAnimationStart(Animation paramAnonymousAnimation) {}
      });
      paramView.startAnimation((Animation)localObject2);
      return;
    }
    Object localObject1 = (View)paramView.getTag(2130968578);
    if (((String[])this.appInfos.get(((String[])this.cat2Names.get(((Integer)paramView.getTag(2130968577)).intValue()))[0]))[0].equals("installed")) {}
    for (final boolean bool = true;; bool = false)
    {
      localObject2 = ((String[])this.appInfos.get(((String[])this.cat2Names.get(((Integer)paramView.getTag(2130968577)).intValue()))[0]))[1];
      paramView = new ScaleAnimation(1.1F, 1.0F, 1.1F, 1.0F, G.cWH(29.0F, 29.0F), G.cWH(55.0F, 55.0F));
      paramView.setFillAfter(true);
      paramView.setDuration(200L);
      paramView.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          if (bool) {}
          for (paramAnonymousAnimation = new Intent(SubCatePage.this.mActivity.getPackageManager().getLaunchIntentForPackage(this.val$appLink));; paramAnonymousAnimation = new Intent("android.intent.action.VIEW", Uri.parse(this.val$appLink)))
          {
            SubCatePage.this.startActivity(paramAnonymousAnimation);
            SubCatePage.this.overridePendingTransition(0, 0);
            return;
          }
        }
        
        public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
        
        public void onAnimationStart(Animation paramAnonymousAnimation) {}
      });
      localObject2 = new ScaleAnimation(1.0F, 1.1F, 1.0F, 1.1F, G.cWH(29.0F, 29.0F), G.cWH(55.0F, 55.0F));
      ((ScaleAnimation)localObject2).setFillAfter(true);
      ((ScaleAnimation)localObject2).setDuration(200L);
      ((ScaleAnimation)localObject2).setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          this.val$animationTarget.startAnimation(paramView);
        }
        
        public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
        
        public void onAnimationStart(Animation paramAnonymousAnimation) {}
      });
      ((View)localObject1).startAnimation((Animation)localObject2);
      return;
    }
  }
  
  private void thumDownloadCheck()
  {
    Object localObject1 = this.cat2Names;
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i >= ((ArrayList)localObject1).size())
    {
      if (localArrayList.size() > 0)
      {
        this.needDownloadThumCnt = localArrayList.size();
        localObject1 = new Handler()
        {
          public void handleMessage(Message paramAnonymousMessage)
          {
            SubCatePage localSubCatePage = SubCatePage.this;
            localSubCatePage.needDownloadThumCnt -= 1;
            if (SubCatePage.this.needDownloadThumCnt <= 0)
            {
              G.dismissProgressDialog();
              SubCatePage.this.thumLoadComplete = true;
              SubCatePage.this.listAdapter.notifyDataSetChanged();
            }
            super.handleMessage(paramAnonymousMessage);
          }
        };
        G.showProgressDialog(this, "잠시만 기다려 주세요.");
        i = 0;
      }
    }
    else {
      for (;;)
      {
        if (i >= localArrayList.size())
        {
          return;
          Object localObject3 = ((String[])localObject1.get(i))[0];
          localObject2 = G.getDrawableFromAssets("Thums/" + (String)localObject3 + ".png", false);
          localObject3 = new File(G.downloadDatasPath + (String)localObject3 + ".thm");
          if ((localObject2 == null) && (!((File)localObject3).exists()) && (!((String[])localObject1.get(i))[0].equals("all"))) {
            localArrayList.add((String[])((ArrayList)localObject1).get(i));
          }
          i += 1;
          break;
        }
        Object localObject2 = new FileDownloader();
        ((FileDownloader)localObject2).setServerPath("/kmobile_contents/kebikids/" + this.cat1Name + "/thumb/");
        ((FileDownloader)localObject2).setLocalPath(G.downloadDatasPath);
        ((FileDownloader)localObject2).setFileName(((String[])localArrayList.get(i))[0]);
        ((FileDownloader)localObject2).setFileType("png");
        ((FileDownloader)localObject2).setCellPosition(i);
        ((FileDownloader)localObject2).setHandler(null, (Handler)localObject1, (Handler)localObject1);
        ((FileDownloader)localObject2).startDownload();
        i += 1;
      }
    }
    this.thumLoadComplete = true;
    this.listAdapter.notifyDataSetChanged();
  }
  
  /* Error */
  protected void onCreate(android.os.Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 311	android/app/Activity:onCreate	(Landroid/os/Bundle;)V
    //   5: aload_0
    //   6: invokevirtual 315	til/KebiKizContents/SubCatePage:getWindow	()Landroid/view/Window;
    //   9: sipush 128
    //   12: invokevirtual 320	android/view/Window:addFlags	(I)V
    //   15: aload_0
    //   16: aload_0
    //   17: putfield 146	til/KebiKizContents/SubCatePage:mActivity	Landroid/app/Activity;
    //   20: aconst_null
    //   21: astore 6
    //   23: aconst_null
    //   24: astore 5
    //   26: aconst_null
    //   27: astore 8
    //   29: aconst_null
    //   30: astore 9
    //   32: aload_0
    //   33: getfield 146	til/KebiKizContents/SubCatePage:mActivity	Landroid/app/Activity;
    //   36: ldc_w 322
    //   39: invokevirtual 326	android/app/Activity:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   42: astore_1
    //   43: aload_1
    //   44: astore 5
    //   46: aload_1
    //   47: astore 6
    //   49: new 328	java/io/ObjectInputStream
    //   52: dup
    //   53: aload_1
    //   54: invokespecial 331	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   57: astore 7
    //   59: aload_0
    //   60: aload 7
    //   62: invokevirtual 335	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   65: checkcast 337	com/kebikids/moreapps/MoreAppsData
    //   68: putfield 101	til/KebiKizContents/SubCatePage:moreAppsData	Lcom/kebikids/moreapps/MoreAppsData;
    //   71: aload 7
    //   73: ifnull +8 -> 81
    //   76: aload 7
    //   78: invokevirtual 340	java/io/ObjectInputStream:close	()V
    //   81: aload_1
    //   82: ifnull +994 -> 1076
    //   85: aload_1
    //   86: invokevirtual 343	java/io/FileInputStream:close	()V
    //   89: aload_0
    //   90: getfield 146	til/KebiKizContents/SubCatePage:mActivity	Landroid/app/Activity;
    //   93: invokevirtual 347	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   96: sipush 128
    //   99: invokevirtual 353	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   102: astore 5
    //   104: aload_0
    //   105: aload_0
    //   106: invokevirtual 357	til/KebiKizContents/SubCatePage:getIntent	()Landroid/content/Intent;
    //   109: ldc_w 359
    //   112: invokevirtual 365	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   115: putfield 127	til/KebiKizContents/SubCatePage:cat1Name	Ljava/lang/String;
    //   118: aload_0
    //   119: getfield 84	til/KebiKizContents/SubCatePage:cat2Names	Ljava/util/ArrayList;
    //   122: iconst_2
    //   123: anewarray 212	java/lang/String
    //   126: dup
    //   127: iconst_0
    //   128: ldc_w 268
    //   131: aastore
    //   132: dup
    //   133: iconst_1
    //   134: ldc_w 367
    //   137: aastore
    //   138: invokevirtual 271	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   141: pop
    //   142: aload_0
    //   143: getstatic 370	com/kebikids/common/G:contentNodes	Ljava/util/HashMap;
    //   146: aload_0
    //   147: getfield 127	til/KebiKizContents/SubCatePage:cat1Name	Ljava/lang/String;
    //   150: invokevirtual 173	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   153: checkcast 372	org/w3c/dom/Node
    //   156: invokeinterface 376 1 0
    //   161: putfield 378	til/KebiKizContents/SubCatePage:kebiNodes	Lorg/w3c/dom/NodeList;
    //   164: iconst_0
    //   165: istore_2
    //   166: iload_2
    //   167: aload_0
    //   168: getfield 378	til/KebiKizContents/SubCatePage:kebiNodes	Lorg/w3c/dom/NodeList;
    //   171: invokeinterface 383 1 0
    //   176: if_icmplt +903 -> 1079
    //   179: aload_0
    //   180: new 385	android/widget/RelativeLayout
    //   183: dup
    //   184: aload_0
    //   185: getfield 146	til/KebiKizContents/SubCatePage:mActivity	Landroid/app/Activity;
    //   188: invokespecial 388	android/widget/RelativeLayout:<init>	(Landroid/content/Context;)V
    //   191: putfield 390	til/KebiKizContents/SubCatePage:layout	Landroid/widget/RelativeLayout;
    //   194: aload_0
    //   195: getfield 390	til/KebiKizContents/SubCatePage:layout	Landroid/widget/RelativeLayout;
    //   198: ldc_w 391
    //   201: invokevirtual 394	android/widget/RelativeLayout:setBackgroundColor	(I)V
    //   204: aload_0
    //   205: new 396	android/widget/RelativeLayout$LayoutParams
    //   208: dup
    //   209: bipush -2
    //   211: bipush -2
    //   213: invokespecial 399	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   216: putfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   219: aload_0
    //   220: getfield 390	til/KebiKizContents/SubCatePage:layout	Landroid/widget/RelativeLayout;
    //   223: aload_0
    //   224: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   227: invokevirtual 405	android/widget/RelativeLayout:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   230: aload_0
    //   231: aload_0
    //   232: getfield 390	til/KebiKizContents/SubCatePage:layout	Landroid/widget/RelativeLayout;
    //   235: invokevirtual 408	til/KebiKizContents/SubCatePage:setContentView	(Landroid/view/View;)V
    //   238: new 410	android/widget/ImageView
    //   241: dup
    //   242: aload_0
    //   243: getfield 146	til/KebiKizContents/SubCatePage:mActivity	Landroid/app/Activity;
    //   246: invokespecial 411	android/widget/ImageView:<init>	(Landroid/content/Context;)V
    //   249: astore_1
    //   250: aload_1
    //   251: getstatic 414	com/kebikids/common/G:backgroundImg	Landroid/graphics/drawable/Drawable;
    //   254: invokevirtual 418	android/widget/ImageView:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   257: aload_0
    //   258: new 396	android/widget/RelativeLayout$LayoutParams
    //   261: dup
    //   262: sipush 800
    //   265: sipush 1280
    //   268: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   271: sipush 480
    //   274: sipush 720
    //   277: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   280: invokespecial 399	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   283: putfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   286: aload_0
    //   287: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   290: iconst_0
    //   291: iconst_0
    //   292: invokestatic 424	com/kebikids/common/G:cX	(II)I
    //   295: iconst_0
    //   296: iconst_0
    //   297: invokestatic 427	com/kebikids/common/G:cY	(II)I
    //   300: iconst_0
    //   301: iconst_0
    //   302: invokevirtual 431	android/widget/RelativeLayout$LayoutParams:setMargins	(IIII)V
    //   305: aload_1
    //   306: aload_0
    //   307: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   310: invokevirtual 432	android/widget/ImageView:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   313: aload_0
    //   314: getfield 390	til/KebiKizContents/SubCatePage:layout	Landroid/widget/RelativeLayout;
    //   317: aload_1
    //   318: invokevirtual 435	android/widget/RelativeLayout:addView	(Landroid/view/View;)V
    //   321: new 410	android/widget/ImageView
    //   324: dup
    //   325: aload_0
    //   326: getfield 146	til/KebiKizContents/SubCatePage:mActivity	Landroid/app/Activity;
    //   329: invokespecial 411	android/widget/ImageView:<init>	(Landroid/content/Context;)V
    //   332: astore_1
    //   333: aload_1
    //   334: ldc_w 437
    //   337: iconst_1
    //   338: invokestatic 97	com/kebikids/common/G:getDrawableFromAssets	(Ljava/lang/String;Z)Landroid/graphics/drawable/Drawable;
    //   341: invokevirtual 418	android/widget/ImageView:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   344: aload_0
    //   345: new 396	android/widget/RelativeLayout$LayoutParams
    //   348: dup
    //   349: sipush 800
    //   352: sipush 1280
    //   355: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   358: sipush 410
    //   361: sipush 600
    //   364: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   367: invokespecial 399	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   370: putfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   373: aload_0
    //   374: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   377: iconst_0
    //   378: iconst_0
    //   379: invokestatic 424	com/kebikids/common/G:cX	(II)I
    //   382: bipush 70
    //   384: bipush 120
    //   386: invokestatic 427	com/kebikids/common/G:cY	(II)I
    //   389: iconst_0
    //   390: iconst_0
    //   391: invokevirtual 431	android/widget/RelativeLayout$LayoutParams:setMargins	(IIII)V
    //   394: aload_1
    //   395: aload_0
    //   396: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   399: invokevirtual 432	android/widget/ImageView:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   402: aload_0
    //   403: getfield 390	til/KebiKizContents/SubCatePage:layout	Landroid/widget/RelativeLayout;
    //   406: aload_1
    //   407: invokevirtual 435	android/widget/RelativeLayout:addView	(Landroid/view/View;)V
    //   410: new 439	android/widget/Button
    //   413: dup
    //   414: aload_0
    //   415: getfield 146	til/KebiKizContents/SubCatePage:mActivity	Landroid/app/Activity;
    //   418: invokespecial 440	android/widget/Button:<init>	(Landroid/content/Context;)V
    //   421: astore_1
    //   422: aload_1
    //   423: ldc_w 442
    //   426: iconst_1
    //   427: invokestatic 97	com/kebikids/common/G:getDrawableFromAssets	(Ljava/lang/String;Z)Landroid/graphics/drawable/Drawable;
    //   430: invokevirtual 443	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   433: aload_0
    //   434: new 396	android/widget/RelativeLayout$LayoutParams
    //   437: dup
    //   438: bipush 90
    //   440: sipush 140
    //   443: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   446: bipush 46
    //   448: bipush 72
    //   450: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   453: invokespecial 399	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   456: putfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   459: aload_0
    //   460: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   463: bipush 20
    //   465: bipush 40
    //   467: invokestatic 424	com/kebikids/common/G:cX	(II)I
    //   470: bipush 7
    //   472: bipush 12
    //   474: invokestatic 427	com/kebikids/common/G:cY	(II)I
    //   477: iconst_0
    //   478: iconst_0
    //   479: invokevirtual 431	android/widget/RelativeLayout$LayoutParams:setMargins	(IIII)V
    //   482: aload_1
    //   483: aload_0
    //   484: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   487: invokevirtual 444	android/widget/Button:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   490: aload_0
    //   491: getfield 390	til/KebiKizContents/SubCatePage:layout	Landroid/widget/RelativeLayout;
    //   494: aload_1
    //   495: invokevirtual 435	android/widget/RelativeLayout:addView	(Landroid/view/View;)V
    //   498: aload_1
    //   499: new 8	til/KebiKizContents/SubCatePage$2
    //   502: dup
    //   503: aload_0
    //   504: invokespecial 445	til/KebiKizContents/SubCatePage$2:<init>	(Ltil/KebiKizContents/SubCatePage;)V
    //   507: invokevirtual 449	android/widget/Button:setOnTouchListener	(Landroid/view/View$OnTouchListener;)V
    //   510: aload_1
    //   511: new 10	til/KebiKizContents/SubCatePage$3
    //   514: dup
    //   515: aload_0
    //   516: invokespecial 450	til/KebiKizContents/SubCatePage$3:<init>	(Ltil/KebiKizContents/SubCatePage;)V
    //   519: invokevirtual 454	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   522: aload_0
    //   523: new 25	til/KebiKizContents/SubCatePage$ListAdapter
    //   526: dup
    //   527: aload_0
    //   528: aload_0
    //   529: invokespecial 457	til/KebiKizContents/SubCatePage$ListAdapter:<init>	(Ltil/KebiKizContents/SubCatePage;Landroid/content/Context;)V
    //   532: putfield 113	til/KebiKizContents/SubCatePage:listAdapter	Ltil/KebiKizContents/SubCatePage$ListAdapter;
    //   535: aload_0
    //   536: new 459	android/widget/ListView
    //   539: dup
    //   540: aload_0
    //   541: getfield 146	til/KebiKizContents/SubCatePage:mActivity	Landroid/app/Activity;
    //   544: invokespecial 460	android/widget/ListView:<init>	(Landroid/content/Context;)V
    //   547: putfield 462	til/KebiKizContents/SubCatePage:subCateList	Landroid/widget/ListView;
    //   550: aload_0
    //   551: getfield 462	til/KebiKizContents/SubCatePage:subCateList	Landroid/widget/ListView;
    //   554: iconst_m1
    //   555: invokevirtual 465	android/widget/ListView:setDividerHeight	(I)V
    //   558: aload_0
    //   559: getfield 462	til/KebiKizContents/SubCatePage:subCateList	Landroid/widget/ListView;
    //   562: iconst_0
    //   563: invokevirtual 468	android/widget/ListView:setFocusableInTouchMode	(Z)V
    //   566: aload_0
    //   567: getfield 462	til/KebiKizContents/SubCatePage:subCateList	Landroid/widget/ListView;
    //   570: new 470	android/graphics/drawable/PaintDrawable
    //   573: dup
    //   574: iconst_0
    //   575: invokespecial 472	android/graphics/drawable/PaintDrawable:<init>	(I)V
    //   578: invokevirtual 475	android/widget/ListView:setSelector	(Landroid/graphics/drawable/Drawable;)V
    //   581: aload_0
    //   582: getfield 462	til/KebiKizContents/SubCatePage:subCateList	Landroid/widget/ListView;
    //   585: iconst_0
    //   586: invokevirtual 478	android/widget/ListView:setVerticalScrollBarEnabled	(Z)V
    //   589: aload_0
    //   590: getfield 462	til/KebiKizContents/SubCatePage:subCateList	Landroid/widget/ListView;
    //   593: iconst_0
    //   594: invokevirtual 481	android/widget/ListView:setCacheColorHint	(I)V
    //   597: aload_0
    //   598: getfield 462	til/KebiKizContents/SubCatePage:subCateList	Landroid/widget/ListView;
    //   601: iconst_0
    //   602: invokevirtual 468	android/widget/ListView:setFocusableInTouchMode	(Z)V
    //   605: aload_0
    //   606: new 396	android/widget/RelativeLayout$LayoutParams
    //   609: dup
    //   610: sipush 800
    //   613: sipush 1280
    //   616: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   619: sipush 404
    //   622: sipush 599
    //   625: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   628: invokespecial 399	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   631: putfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   634: aload_0
    //   635: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   638: iconst_0
    //   639: iconst_0
    //   640: invokestatic 424	com/kebikids/common/G:cX	(II)I
    //   643: bipush 76
    //   645: bipush 121
    //   647: invokestatic 427	com/kebikids/common/G:cY	(II)I
    //   650: iconst_0
    //   651: iconst_0
    //   652: invokevirtual 431	android/widget/RelativeLayout$LayoutParams:setMargins	(IIII)V
    //   655: aload_0
    //   656: getfield 462	til/KebiKizContents/SubCatePage:subCateList	Landroid/widget/ListView;
    //   659: aload_0
    //   660: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   663: invokevirtual 482	android/widget/ListView:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   666: aload_0
    //   667: getfield 390	til/KebiKizContents/SubCatePage:layout	Landroid/widget/RelativeLayout;
    //   670: aload_0
    //   671: getfield 462	til/KebiKizContents/SubCatePage:subCateList	Landroid/widget/ListView;
    //   674: invokevirtual 435	android/widget/RelativeLayout:addView	(Landroid/view/View;)V
    //   677: aload_0
    //   678: getfield 462	til/KebiKizContents/SubCatePage:subCateList	Landroid/widget/ListView;
    //   681: aload_0
    //   682: getfield 113	til/KebiKizContents/SubCatePage:listAdapter	Ltil/KebiKizContents/SubCatePage$ListAdapter;
    //   685: invokevirtual 486	android/widget/ListView:setAdapter	(Landroid/widget/ListAdapter;)V
    //   688: new 410	android/widget/ImageView
    //   691: dup
    //   692: aload_0
    //   693: getfield 146	til/KebiKizContents/SubCatePage:mActivity	Landroid/app/Activity;
    //   696: invokespecial 411	android/widget/ImageView:<init>	(Landroid/content/Context;)V
    //   699: astore_1
    //   700: aload_1
    //   701: ldc_w 488
    //   704: iconst_1
    //   705: invokestatic 97	com/kebikids/common/G:getDrawableFromAssets	(Ljava/lang/String;Z)Landroid/graphics/drawable/Drawable;
    //   708: invokevirtual 418	android/widget/ImageView:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   711: aload_0
    //   712: new 396	android/widget/RelativeLayout$LayoutParams
    //   715: dup
    //   716: sipush 800
    //   719: sipush 1280
    //   722: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   725: bipush 80
    //   727: sipush 130
    //   730: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   733: invokespecial 399	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   736: putfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   739: aload_0
    //   740: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   743: iconst_0
    //   744: iconst_0
    //   745: invokestatic 424	com/kebikids/common/G:cX	(II)I
    //   748: iconst_0
    //   749: iconst_0
    //   750: invokestatic 427	com/kebikids/common/G:cY	(II)I
    //   753: iconst_0
    //   754: iconst_0
    //   755: invokevirtual 431	android/widget/RelativeLayout$LayoutParams:setMargins	(IIII)V
    //   758: aload_1
    //   759: aload_0
    //   760: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   763: invokevirtual 432	android/widget/ImageView:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   766: aload_0
    //   767: getfield 390	til/KebiKizContents/SubCatePage:layout	Landroid/widget/RelativeLayout;
    //   770: aload_1
    //   771: invokevirtual 435	android/widget/RelativeLayout:addView	(Landroid/view/View;)V
    //   774: new 410	android/widget/ImageView
    //   777: dup
    //   778: aload_0
    //   779: getfield 146	til/KebiKizContents/SubCatePage:mActivity	Landroid/app/Activity;
    //   782: invokespecial 411	android/widget/ImageView:<init>	(Landroid/content/Context;)V
    //   785: astore_1
    //   786: aload_1
    //   787: new 235	java/lang/StringBuilder
    //   790: dup
    //   791: ldc_w 490
    //   794: invokespecial 240	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   797: aload_0
    //   798: getfield 127	til/KebiKizContents/SubCatePage:cat1Name	Ljava/lang/String;
    //   801: invokevirtual 244	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   804: ldc -10
    //   806: invokevirtual 244	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   809: invokevirtual 250	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   812: iconst_0
    //   813: invokestatic 97	com/kebikids/common/G:getDrawableFromAssets	(Ljava/lang/String;Z)Landroid/graphics/drawable/Drawable;
    //   816: invokevirtual 418	android/widget/ImageView:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   819: aload_0
    //   820: new 396	android/widget/RelativeLayout$LayoutParams
    //   823: dup
    //   824: bipush 50
    //   826: bipush 74
    //   828: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   831: bipush 50
    //   833: bipush 74
    //   835: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   838: invokespecial 399	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   841: putfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   844: aload_0
    //   845: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   848: sipush 334
    //   851: sipush 540
    //   854: invokestatic 424	com/kebikids/common/G:cX	(II)I
    //   857: bipush 12
    //   859: bipush 28
    //   861: invokestatic 427	com/kebikids/common/G:cY	(II)I
    //   864: iconst_0
    //   865: iconst_0
    //   866: invokevirtual 431	android/widget/RelativeLayout$LayoutParams:setMargins	(IIII)V
    //   869: aload_1
    //   870: aload_0
    //   871: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   874: invokevirtual 432	android/widget/ImageView:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   877: aload_0
    //   878: getfield 390	til/KebiKizContents/SubCatePage:layout	Landroid/widget/RelativeLayout;
    //   881: aload_1
    //   882: invokevirtual 435	android/widget/RelativeLayout:addView	(Landroid/view/View;)V
    //   885: new 410	android/widget/ImageView
    //   888: dup
    //   889: aload_0
    //   890: getfield 146	til/KebiKizContents/SubCatePage:mActivity	Landroid/app/Activity;
    //   893: invokespecial 411	android/widget/ImageView:<init>	(Landroid/content/Context;)V
    //   896: astore_1
    //   897: aload_1
    //   898: new 235	java/lang/StringBuilder
    //   901: dup
    //   902: ldc_w 492
    //   905: invokespecial 240	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   908: aload_0
    //   909: getfield 127	til/KebiKizContents/SubCatePage:cat1Name	Ljava/lang/String;
    //   912: invokevirtual 244	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   915: ldc -10
    //   917: invokevirtual 244	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   920: invokevirtual 250	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   923: iconst_1
    //   924: invokestatic 97	com/kebikids/common/G:getDrawableFromAssets	(Ljava/lang/String;Z)Landroid/graphics/drawable/Drawable;
    //   927: invokevirtual 418	android/widget/ImageView:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   930: aload_0
    //   931: new 396	android/widget/RelativeLayout$LayoutParams
    //   934: dup
    //   935: bipush 72
    //   937: bipush 115
    //   939: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   942: bipush 29
    //   944: bipush 45
    //   946: invokestatic 421	com/kebikids/common/G:cWH	(II)I
    //   949: invokespecial 399	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   952: putfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   955: aload_0
    //   956: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   959: sipush 388
    //   962: sipush 622
    //   965: invokestatic 424	com/kebikids/common/G:cX	(II)I
    //   968: bipush 27
    //   970: bipush 47
    //   972: invokestatic 427	com/kebikids/common/G:cY	(II)I
    //   975: iconst_0
    //   976: iconst_0
    //   977: invokevirtual 431	android/widget/RelativeLayout$LayoutParams:setMargins	(IIII)V
    //   980: aload_1
    //   981: aload_0
    //   982: getfield 401	til/KebiKizContents/SubCatePage:params	Landroid/widget/RelativeLayout$LayoutParams;
    //   985: invokevirtual 432	android/widget/ImageView:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   988: aload_0
    //   989: getfield 390	til/KebiKizContents/SubCatePage:layout	Landroid/widget/RelativeLayout;
    //   992: aload_1
    //   993: invokevirtual 435	android/widget/RelativeLayout:addView	(Landroid/view/View;)V
    //   996: return
    //   997: astore_1
    //   998: aload 9
    //   1000: astore 6
    //   1002: aload 5
    //   1004: astore_1
    //   1005: aload 6
    //   1007: ifnull +8 -> 1015
    //   1010: aload 6
    //   1012: invokevirtual 340	java/io/ObjectInputStream:close	()V
    //   1015: aload_1
    //   1016: ifnull -927 -> 89
    //   1019: aload_1
    //   1020: invokevirtual 343	java/io/FileInputStream:close	()V
    //   1023: goto -934 -> 89
    //   1026: astore_1
    //   1027: aload_1
    //   1028: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   1031: goto -942 -> 89
    //   1034: astore_1
    //   1035: aload 8
    //   1037: astore 5
    //   1039: aload 5
    //   1041: ifnull +8 -> 1049
    //   1044: aload 5
    //   1046: invokevirtual 340	java/io/ObjectInputStream:close	()V
    //   1049: aload 6
    //   1051: ifnull +8 -> 1059
    //   1054: aload 6
    //   1056: invokevirtual 343	java/io/FileInputStream:close	()V
    //   1059: aload_1
    //   1060: athrow
    //   1061: astore 5
    //   1063: aload 5
    //   1065: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   1068: goto -9 -> 1059
    //   1071: astore_1
    //   1072: aload_1
    //   1073: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   1076: goto -987 -> 89
    //   1079: aload_0
    //   1080: getfield 378	til/KebiKizContents/SubCatePage:kebiNodes	Lorg/w3c/dom/NodeList;
    //   1083: iload_2
    //   1084: invokeinterface 499 2 0
    //   1089: astore_1
    //   1090: aload_1
    //   1091: invokeinterface 502 1 0
    //   1096: ifeq +158 -> 1254
    //   1099: aload_1
    //   1100: invokeinterface 505 1 0
    //   1105: astore 6
    //   1107: aload_1
    //   1108: invokeinterface 509 1 0
    //   1113: ldc_w 511
    //   1116: invokeinterface 517 2 0
    //   1121: invokeinterface 520 1 0
    //   1126: astore 7
    //   1128: aload_1
    //   1129: invokeinterface 509 1 0
    //   1134: ldc_w 522
    //   1137: invokeinterface 517 2 0
    //   1142: ifnull +45 -> 1187
    //   1145: aload_1
    //   1146: invokeinterface 509 1 0
    //   1151: ldc_w 522
    //   1154: invokeinterface 517 2 0
    //   1159: invokeinterface 520 1 0
    //   1164: ldc_w 524
    //   1167: invokevirtual 216	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1170: ifeq +17 -> 1187
    //   1173: aload_0
    //   1174: getfield 89	til/KebiKizContents/SubCatePage:newMark	Ljava/util/HashMap;
    //   1177: aload 6
    //   1179: iconst_1
    //   1180: invokestatic 529	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1183: invokevirtual 533	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1186: pop
    //   1187: aload_0
    //   1188: getfield 84	til/KebiKizContents/SubCatePage:cat2Names	Ljava/util/ArrayList;
    //   1191: iconst_2
    //   1192: anewarray 212	java/lang/String
    //   1195: dup
    //   1196: iconst_0
    //   1197: aload 6
    //   1199: aastore
    //   1200: dup
    //   1201: iconst_1
    //   1202: aload 7
    //   1204: aastore
    //   1205: invokevirtual 271	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1208: pop
    //   1209: getstatic 536	com/kebikids/common/G:cat2Info	Ljava/util/HashMap;
    //   1212: aload 6
    //   1214: aload 7
    //   1216: invokevirtual 533	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1219: pop
    //   1220: aload_0
    //   1221: getfield 101	til/KebiKizContents/SubCatePage:moreAppsData	Lcom/kebikids/moreapps/MoreAppsData;
    //   1224: ifnull +30 -> 1254
    //   1227: aload 6
    //   1229: ldc_w 538
    //   1232: invokevirtual 542	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1235: ifeq +19 -> 1254
    //   1238: iconst_0
    //   1239: istore_3
    //   1240: iload_3
    //   1241: aload_0
    //   1242: getfield 101	til/KebiKizContents/SubCatePage:moreAppsData	Lcom/kebikids/moreapps/MoreAppsData;
    //   1245: getfield 545	com/kebikids/moreapps/MoreAppsData:titles	Ljava/util/ArrayList;
    //   1248: invokevirtual 226	java/util/ArrayList:size	()I
    //   1251: if_icmplt +10 -> 1261
    //   1254: iload_2
    //   1255: iconst_1
    //   1256: iadd
    //   1257: istore_2
    //   1258: goto -1092 -> 166
    //   1261: aload 7
    //   1263: aload_0
    //   1264: getfield 101	til/KebiKizContents/SubCatePage:moreAppsData	Lcom/kebikids/moreapps/MoreAppsData;
    //   1267: getfield 545	com/kebikids/moreapps/MoreAppsData:titles	Ljava/util/ArrayList;
    //   1270: iload_3
    //   1271: invokevirtual 168	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1274: invokevirtual 216	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1277: ifeq +66 -> 1343
    //   1280: iconst_0
    //   1281: istore 4
    //   1283: aload 5
    //   1285: invokeinterface 551 1 0
    //   1290: astore_1
    //   1291: aload_1
    //   1292: invokeinterface 556 1 0
    //   1297: ifne +53 -> 1350
    //   1300: iload 4
    //   1302: ifeq +90 -> 1392
    //   1305: iconst_2
    //   1306: anewarray 212	java/lang/String
    //   1309: astore_1
    //   1310: aload_1
    //   1311: iconst_0
    //   1312: ldc -46
    //   1314: aastore
    //   1315: aload_1
    //   1316: iconst_1
    //   1317: aload_0
    //   1318: getfield 101	til/KebiKizContents/SubCatePage:moreAppsData	Lcom/kebikids/moreapps/MoreAppsData;
    //   1321: getfield 559	com/kebikids/moreapps/MoreAppsData:appLinks	Ljava/util/ArrayList;
    //   1324: iload_3
    //   1325: invokevirtual 168	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1328: checkcast 212	java/lang/String
    //   1331: aastore
    //   1332: aload_0
    //   1333: getfield 103	til/KebiKizContents/SubCatePage:appInfos	Ljava/util/HashMap;
    //   1336: aload 6
    //   1338: aload_1
    //   1339: invokevirtual 533	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1342: pop
    //   1343: iload_3
    //   1344: iconst_1
    //   1345: iadd
    //   1346: istore_3
    //   1347: goto -107 -> 1240
    //   1350: aload_1
    //   1351: invokeinterface 562 1 0
    //   1356: checkcast 564	android/content/pm/ApplicationInfo
    //   1359: astore 8
    //   1361: aload_0
    //   1362: getfield 101	til/KebiKizContents/SubCatePage:moreAppsData	Lcom/kebikids/moreapps/MoreAppsData;
    //   1365: getfield 559	com/kebikids/moreapps/MoreAppsData:appLinks	Ljava/util/ArrayList;
    //   1368: iload_3
    //   1369: invokevirtual 168	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1372: checkcast 212	java/lang/String
    //   1375: aload 8
    //   1377: getfield 567	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   1380: invokevirtual 216	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1383: ifeq -92 -> 1291
    //   1386: iconst_1
    //   1387: istore 4
    //   1389: goto -89 -> 1300
    //   1392: iconst_2
    //   1393: anewarray 212	java/lang/String
    //   1396: astore_1
    //   1397: aload_1
    //   1398: iconst_0
    //   1399: ldc_w 569
    //   1402: aastore
    //   1403: aload_1
    //   1404: iconst_1
    //   1405: aload_0
    //   1406: getfield 101	til/KebiKizContents/SubCatePage:moreAppsData	Lcom/kebikids/moreapps/MoreAppsData;
    //   1409: getfield 572	com/kebikids/moreapps/MoreAppsData:links	Ljava/util/ArrayList;
    //   1412: iload_3
    //   1413: invokevirtual 168	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1416: checkcast 212	java/lang/String
    //   1419: aastore
    //   1420: goto -88 -> 1332
    //   1423: astore 8
    //   1425: aload 7
    //   1427: astore 5
    //   1429: aload_1
    //   1430: astore 6
    //   1432: aload 8
    //   1434: astore_1
    //   1435: goto -396 -> 1039
    //   1438: astore 5
    //   1440: aload 7
    //   1442: astore 6
    //   1444: goto -439 -> 1005
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1447	0	this	SubCatePage
    //   0	1447	1	paramBundle	android.os.Bundle
    //   165	1093	2	i	int
    //   1239	174	3	j	int
    //   1281	107	4	k	int
    //   24	1021	5	localObject1	Object
    //   1061	223	5	localException1	Exception
    //   1427	1	5	localObject2	Object
    //   1438	1	5	localException2	Exception
    //   21	1422	6	localObject3	Object
    //   57	1384	7	localObject4	Object
    //   27	1349	8	localApplicationInfo	android.content.pm.ApplicationInfo
    //   1423	10	8	localObject5	Object
    //   30	969	9	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   32	43	997	java/lang/Exception
    //   49	59	997	java/lang/Exception
    //   1010	1015	1026	java/lang/Exception
    //   1019	1023	1026	java/lang/Exception
    //   32	43	1034	finally
    //   49	59	1034	finally
    //   1044	1049	1061	java/lang/Exception
    //   1054	1059	1061	java/lang/Exception
    //   76	81	1071	java/lang/Exception
    //   85	89	1071	java/lang/Exception
    //   59	71	1423	finally
    //   59	71	1438	java/lang/Exception
  }
  
  protected void onDestroy()
  {
    G.recursiveRecycle(getWindow().getDecorView());
    if (this.listAdapter != null) {
      this.listAdapter.recycle();
    }
    System.gc();
    super.onDestroy();
  }
  
  protected void onResume()
  {
    this.allBtLock = false;
    if (!this.isFirstRunning) {
      this.listAdapter.notifyDataSetChanged();
    }
    super.onResume();
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if ((this.isFirstRunning) && (paramBoolean))
    {
      this.isFirstRunning = false;
      thumDownloadCheck();
    }
    super.onWindowFocusChanged(paramBoolean);
  }
  
  class GirdCellWrapper
  {
    private ImageView appTag1;
    private ImageView appTag2;
    private ImageView appTag3;
    private ImageView appTag4;
    private ImageView bgImg;
    private Button catBt1;
    private Button catBt2;
    private Button catBt3;
    private Button catBt4;
    private SubCatePage.SingleCell cell;
    private ImageView newIcon1;
    private ImageView newIcon2;
    private ImageView newIcon3;
    private ImageView newIcon4;
    
    public GirdCellWrapper(View paramView)
    {
      this.cell = ((SubCatePage.SingleCell)paramView);
    }
    
    public ImageView getAppTag1()
    {
      if (this.appTag1 == null) {
        this.appTag1 = this.cell.appTag1;
      }
      return this.appTag1;
    }
    
    public ImageView getAppTag2()
    {
      if (this.appTag2 == null) {
        this.appTag2 = this.cell.appTag2;
      }
      return this.appTag2;
    }
    
    public ImageView getAppTag3()
    {
      if (this.appTag3 == null) {
        this.appTag3 = this.cell.appTag3;
      }
      return this.appTag3;
    }
    
    public ImageView getAppTag4()
    {
      if (this.appTag4 == null) {
        this.appTag4 = this.cell.appTag4;
      }
      return this.appTag4;
    }
    
    public ImageView getBgImg()
    {
      if (this.bgImg == null) {
        this.bgImg = this.cell.bgImg;
      }
      return this.bgImg;
    }
    
    public Button getCatBt1()
    {
      if (this.catBt1 == null) {
        this.catBt1 = this.cell.catBt1;
      }
      return this.catBt1;
    }
    
    public Button getCatBt2()
    {
      if (this.catBt2 == null) {
        this.catBt2 = this.cell.catBt2;
      }
      return this.catBt2;
    }
    
    public Button getCatBt3()
    {
      if (this.catBt3 == null) {
        this.catBt3 = this.cell.catBt3;
      }
      return this.catBt3;
    }
    
    public Button getCatBt4()
    {
      if (this.catBt4 == null) {
        this.catBt4 = this.cell.catBt4;
      }
      return this.catBt4;
    }
    
    public ImageView getNewIcon1()
    {
      if (this.newIcon1 == null) {
        this.newIcon1 = this.cell.newIcon1;
      }
      return this.newIcon1;
    }
    
    public ImageView getNewIcon2()
    {
      if (this.newIcon2 == null) {
        this.newIcon2 = this.cell.newIcon2;
      }
      return this.newIcon2;
    }
    
    public ImageView getNewIcon3()
    {
      if (this.newIcon3 == null) {
        this.newIcon3 = this.cell.newIcon3;
      }
      return this.newIcon3;
    }
    
    public ImageView getNewIcon4()
    {
      if (this.newIcon4 == null) {
        this.newIcon4 = this.cell.newIcon4;
      }
      return this.newIcon4;
    }
  }
  
  class ListAdapter
    extends BaseAdapter
  {
    private Context mContext;
    private List<WeakReference<View>> mRecycleList = new ArrayList();
    
    public ListAdapter(Context paramContext)
    {
      this.mContext = paramContext;
    }
    
    public int getCount()
    {
      int j = (int)Math.ceil(SubCatePage.this.cat2Names.size() / 4.0F);
      int i = j;
      if (j < 3) {
        i = 3;
      }
      return i;
    }
    
    public Object getItem(int paramInt)
    {
      return null;
    }
    
    public long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramView = (SubCatePage.SingleCell)paramView;
      if (paramView == null)
      {
        paramView = new SubCatePage.SingleCell(SubCatePage.this, this.mContext);
        paramViewGroup = new SubCatePage.GirdCellWrapper(SubCatePage.this, paramView);
        paramView.setTag(paramViewGroup);
      }
      Button[] arrayOfButton;
      ImageView[] arrayOfImageView1;
      ImageView[] arrayOfImageView2;
      int i;
      for (;;)
      {
        paramViewGroup.getBgImg().setBackgroundDrawable(SubCatePage.this.cellBG);
        arrayOfButton = new Button[4];
        arrayOfButton[0] = paramViewGroup.getCatBt1();
        arrayOfButton[1] = paramViewGroup.getCatBt2();
        arrayOfButton[2] = paramViewGroup.getCatBt3();
        arrayOfButton[3] = paramViewGroup.getCatBt4();
        arrayOfImageView1 = new ImageView[4];
        arrayOfImageView1[0] = paramViewGroup.getAppTag1();
        arrayOfImageView1[1] = paramViewGroup.getAppTag2();
        arrayOfImageView1[2] = paramViewGroup.getAppTag3();
        arrayOfImageView1[3] = paramViewGroup.getAppTag4();
        arrayOfImageView2 = new ImageView[4];
        arrayOfImageView2[0] = paramViewGroup.getNewIcon1();
        arrayOfImageView2[1] = paramViewGroup.getNewIcon2();
        arrayOfImageView2[2] = paramViewGroup.getNewIcon3();
        arrayOfImageView2[3] = paramViewGroup.getNewIcon4();
        i = 0;
        if (i < 4) {
          break;
        }
        this.mRecycleList.add(new WeakReference(paramView));
        return paramView;
        paramViewGroup = (SubCatePage.GirdCellWrapper)paramView.getTag();
      }
      arrayOfButton[i].setEnabled(false);
      arrayOfButton[i].setBackgroundColor(0);
      arrayOfImageView1[i].setVisibility(8);
      arrayOfImageView1[i].setBackgroundColor(0);
      arrayOfImageView2[i].setBackgroundColor(0);
      Object localObject;
      if (SubCatePage.this.thumLoadComplete)
      {
        int j = paramInt * 4 + i;
        if (j < SubCatePage.this.cat2Names.size())
        {
          paramViewGroup = ((String[])SubCatePage.this.cat2Names.get(j))[0];
          arrayOfButton[i].setTag(2130968577, Integer.valueOf(j));
          arrayOfButton[i].setTag(2130968578, arrayOfImageView1[i]);
          if ((paramViewGroup.equals("all")) || (paramViewGroup.contains("app_"))) {
            break label574;
          }
          localObject = G.getDrawableFromAssets("Thums/" + paramViewGroup + ".png", false);
          if (localObject == null) {
            break label480;
          }
          arrayOfButton[i].setEnabled(true);
          arrayOfButton[i].setBackgroundDrawable((Drawable)localObject);
          label407:
          if ((SubCatePage.this.newMark.get(paramViewGroup) != null) && (((Boolean)SubCatePage.this.newMark.get(paramViewGroup)).booleanValue())) {
            arrayOfImageView2[i].setBackgroundDrawable(G.getDrawableFromAssets("Common/NewIcon_Cat2.png", false));
          }
        }
      }
      for (;;)
      {
        arrayOfButton[i].setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!SubCatePage.this.allBtLock)
            {
              SubCatePage.this.allBtLock = true;
              SubCatePage.this.subCatBtClick(paramAnonymousView);
            }
          }
        });
        i += 1;
        break;
        label480:
        if (!new File(G.downloadDatasPath + paramViewGroup + ".thm").exists()) {
          break label407;
        }
        arrayOfButton[i].setEnabled(true);
        localObject = new BitmapDrawable(G.downloadDatasPath + paramViewGroup + ".thm");
        arrayOfButton[i].setBackgroundDrawable((Drawable)localObject);
        break label407;
        label574:
        if (paramViewGroup.equals("all"))
        {
          arrayOfButton[i].setEnabled(true);
          arrayOfButton[i].setBackgroundDrawable(G.getDrawableFromAssets("SubCatePage/AllBt.png", false));
        }
        else if (SubCatePage.this.appInfos.get(paramViewGroup) != null)
        {
          arrayOfButton[i].setEnabled(true);
          localObject = G.getDrawableFromAssets("Thums/" + paramViewGroup + ".png", false);
          if (localObject != null) {
            arrayOfButton[i].setBackgroundDrawable((Drawable)localObject);
          }
          for (;;)
          {
            arrayOfImageView1[i].setVisibility(0);
            if (!((String[])SubCatePage.this.appInfos.get(paramViewGroup))[0].equals("installed")) {
              break label809;
            }
            arrayOfImageView1[i].setBackgroundDrawable(G.getDrawableFromAssets("ContentList/AppRunBt.png", false));
            break;
            if (new File(G.downloadDatasPath + paramViewGroup + ".thm").exists())
            {
              localObject = new BitmapDrawable(G.downloadDatasPath + paramViewGroup + ".thm");
              arrayOfButton[i].setBackgroundDrawable((Drawable)localObject);
            }
          }
          label809:
          arrayOfImageView1[i].setBackgroundDrawable(G.getDrawableFromAssets("ContentList/AppInstallBt.png", false));
        }
      }
    }
    
    public void recycle()
    {
      Iterator localIterator = this.mRecycleList.iterator();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return;
        }
        G.recursiveRecycle((View)((WeakReference)localIterator.next()).get());
      }
    }
  }
  
  class SingleCell
    extends RelativeLayout
  {
    public ImageView appTag1;
    public ImageView appTag2;
    public ImageView appTag3;
    public ImageView appTag4;
    public ImageView bgImg;
    public Button catBt1;
    public Button catBt2;
    public Button catBt3;
    public Button catBt4;
    public ImageView newIcon1;
    public ImageView newIcon2;
    public ImageView newIcon3;
    public ImageView newIcon4;
    private RelativeLayout.LayoutParams params;
    public int position;
    
    public SingleCell(Context paramContext)
    {
      super();
      this.bgImg = new ImageView(paramContext);
      this.params = new RelativeLayout.LayoutParams(G.cWH(800, 1280), G.cWH(190, 220));
      this.bgImg.setLayoutParams(this.params);
      addView(this.bgImg);
      this.catBt1 = new Button(paramContext);
      this.params = new RelativeLayout.LayoutParams(G.cWH(170, 170), G.cWH(170, 170));
      this.params.setMargins(G.cWH(28, 145), G.cWH(5, 21), 0, 0);
      this.catBt1.setLayoutParams(this.params);
      addView(this.catBt1);
      this.appTag1 = new ImageView(SubCatePage.this.mActivity);
      this.params = new RelativeLayout.LayoutParams(G.cWH(54, 54), G.cWH(154, 154));
      this.params.setMargins(G.cWH(26, 147), G.cWH(13, 20), 0, 0);
      this.appTag1.setLayoutParams(this.params);
      addView(this.appTag1);
      this.newIcon1 = new ImageView(SubCatePage.this.mActivity);
      this.params = new RelativeLayout.LayoutParams(G.cWH(60, 78), G.cWH(31, 40));
      this.params.setMargins(G.cWH(126, 227), G.cWH(146, 164), 0, 0);
      this.newIcon1.setLayoutParams(this.params);
      addView(this.newIcon1);
      this.catBt2 = new Button(paramContext);
      this.params = new RelativeLayout.LayoutParams(G.cWH(170, 170), G.cWH(170, 170));
      this.params.setMargins(G.cWH(218, 405), G.cWH(5, 21), 0, 0);
      this.catBt2.setLayoutParams(this.params);
      addView(this.catBt2);
      this.appTag2 = new ImageView(SubCatePage.this.mActivity);
      this.params = new RelativeLayout.LayoutParams(G.cWH(54, 54), G.cWH(154, 154));
      this.params.setMargins(G.cWH(216, 407), G.cWH(13, 20), 0, 0);
      this.appTag2.setLayoutParams(this.params);
      addView(this.appTag2);
      this.newIcon2 = new ImageView(SubCatePage.this.mActivity);
      this.params = new RelativeLayout.LayoutParams(G.cWH(60, 78), G.cWH(31, 40));
      this.params.setMargins(G.cWH(316, 487), G.cWH(146, 164), 0, 0);
      this.newIcon2.setLayoutParams(this.params);
      addView(this.newIcon2);
      this.catBt3 = new Button(paramContext);
      this.params = new RelativeLayout.LayoutParams(G.cWH(170, 170), G.cWH(170, 170));
      this.params.setMargins(G.cWH(408, 665), G.cWH(5, 21), 0, 0);
      this.catBt3.setLayoutParams(this.params);
      addView(this.catBt3);
      this.appTag3 = new ImageView(SubCatePage.this.mActivity);
      this.params = new RelativeLayout.LayoutParams(G.cWH(54, 54), G.cWH(154, 154));
      this.params.setMargins(G.cWH(406, 667), G.cWH(13, 20), 0, 0);
      this.appTag3.setLayoutParams(this.params);
      addView(this.appTag3);
      this.newIcon3 = new ImageView(SubCatePage.this.mActivity);
      this.params = new RelativeLayout.LayoutParams(G.cWH(60, 78), G.cWH(31, 40));
      this.params.setMargins(G.cWH(506, 747), G.cWH(146, 164), 0, 0);
      this.newIcon3.setLayoutParams(this.params);
      addView(this.newIcon3);
      this.catBt4 = new Button(paramContext);
      this.params = new RelativeLayout.LayoutParams(G.cWH(170, 170), G.cWH(170, 170));
      this.params.setMargins(G.cWH(598, 925), G.cWH(5, 21), 0, 0);
      this.catBt4.setLayoutParams(this.params);
      addView(this.catBt4);
      this.appTag4 = new ImageView(SubCatePage.this.mActivity);
      this.params = new RelativeLayout.LayoutParams(G.cWH(54, 54), G.cWH(154, 154));
      this.params.setMargins(G.cWH(596, 927), G.cWH(13, 20), 0, 0);
      this.appTag4.setLayoutParams(this.params);
      addView(this.appTag4);
      this.newIcon4 = new ImageView(SubCatePage.this.mActivity);
      this.params = new RelativeLayout.LayoutParams(G.cWH(60, 78), G.cWH(31, 40));
      this.params.setMargins(G.cWH(696, 1007), G.cWH(146, 164), 0, 0);
      this.newIcon4.setLayoutParams(this.params);
      addView(this.newIcon4);
    }
  }
}
