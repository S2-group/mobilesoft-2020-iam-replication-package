package com.simpleapp.tinyscanfree;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.print.PrintManager;
import android.support.v4.print.PrintHelper;
import android.support.v4.util.LruCache;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appxy.tools.BitmapTools;
import com.appxy.tools.IAPBuy;
import com.appxy.tools.RecyclingBitmapDrawable;
import com.appxy.tools.Util;
import com.appxy.tools.Utils;
import com.flurry.android.FlurryAgent;
import com.itextpdf.text.pdf.PdfWriter;
import com.simpelapp.entity.MainListDao;
import com.simpelapp.entity.Photo_item;
import com.simpleapp.adpter.MoreListAdapter;
import com.simpleapp.adpter.MyPageAdapter;
import com.simpleapp.adpter.MyPrintDocumentAdapter;
import com.simpleapp.adpter.MyViewPager;
import com.simpleapp.adsUtils.AdsUtils;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase.DisplayType;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity_EditPhoto
  extends BaseActivity
{
  private static final int RC_REQUEST = 10001;
  private static Activity_EditPhoto activity_EditPhoto;
  private MyPageAdapter adapter;
  private ImageView back;
  private BitmapDrawable bit2 = null;
  private BitmapDrawable bitDrawable = null;
  private BitmapDrawable bitmap = null;
  private BitmapDrawable bitmap2 = null;
  Comparator<Photo_item> comparator = new Comparator()
  {
    public int compare(Photo_item paramAnonymousPhoto_item1, Photo_item paramAnonymousPhoto_item2)
    {
      return paramAnonymousPhoto_item1.getPath().substring(paramAnonymousPhoto_item1.getPath().length() - 7, paramAnonymousPhoto_item1.getPath().length() - 4).compareTo(paramAnonymousPhoto_item2.getPath().substring(paramAnonymousPhoto_item2.getPath().length() - 7, paramAnonymousPhoto_item2.getPath().length() - 4));
    }
  };
  Comparator<String> comparator3 = new Comparator()
  {
    public int compare(String paramAnonymousString1, String paramAnonymousString2)
    {
      return paramAnonymousString1.substring(paramAnonymousString1.length() - 7, paramAnonymousString1.length() - 4).compareTo(paramAnonymousString2.substring(paramAnonymousString2.length() - 7, paramAnonymousString2.length() - 4));
    }
  };
  private String compressJpeg_Path;
  private Context context;
  private TextView documentname;
  boolean edit = false;
  private LinearLayout edit_photo_bottomclick_layout;
  private ImageView edit_photo_draglist_cancel;
  private ImageView edit_photo_email_pad;
  private RelativeLayout edit_photo_layout1;
  private ProgressBar edit_photo_pb;
  private SharedPreferences.Editor editor;
  private ImageView editphto_morelist;
  private int export_size = 0;
  @SuppressLint({"HandlerLeak"})
  Handler handler = new Handler()
  {
    private PrintManager printManager;
    
    @SuppressLint({"DefaultLocale", "InflateParams", "InlinedApi", "NewApi"})
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      for (;;)
      {
        super.handleMessage(paramAnonymousMessage);
        return;
        int i = ((Integer)paramAnonymousMessage.obj).intValue();
        if (Activity_EditPhoto.this.mlist.get(i) != null)
        {
          Object localObject1 = Activity_EditPhoto.this.mPager.findViewWithTag(Activity_EditPhoto.this.mlist.get(i));
          if (localObject1 != null)
          {
            localObject1 = (ImageViewTouch)((View)localObject1).findViewById(2131624311);
            ((ImageViewTouch)localObject1).setImageDrawable(Activity_EditPhoto.this.bitmap, ((ImageViewTouch)localObject1).getDisplayMatrix(), -1.0F, -1.0F);
            continue;
            i = ((Integer)paramAnonymousMessage.obj).intValue();
            if ((i == Activity_EditPhoto.this.num - 1) && (!Activity_EditPhoto.this.isScroll) && (!Activity_EditPhoto.this.isListSelecting) && (Activity_EditPhoto.this.mlist.get(i) != null))
            {
              localObject1 = Activity_EditPhoto.this.mPager.findViewWithTag(Activity_EditPhoto.this.mlist.get(i));
              if (localObject1 != null)
              {
                localObject1 = (ImageViewTouch)((View)localObject1).findViewById(2131624311);
                ((ImageViewTouch)localObject1).setImageDrawable(Activity_EditPhoto.this.bitmap2, ((ImageViewTouch)localObject1).getDisplayMatrix(), -1.0F, -1.0F);
                continue;
                Activity_EditPhoto.this.edit_photo_pb.setVisibility(4);
                Activity_EditPhoto.access$302(Activity_EditPhoto.this, false);
                Activity_EditPhoto.access$402(Activity_EditPhoto.this, false);
                Activity_EditPhoto.this.mapp.setUpdate(true);
                Activity_EditPhoto.this.image.setImageDrawable(Activity_EditPhoto.this.bitmap2, Activity_EditPhoto.this.image.getDisplayMatrix(), -1.0F, -1.0F);
                continue;
                Activity_EditPhoto.this.showToast(Activity_EditPhoto.this.getResources().getString(2131165552));
                continue;
                if (Activity_EditPhoto.this.oldnum == Activity_EditPhoto.this.num)
                {
                  localObject1 = (ImageViewTouch)Activity_EditPhoto.this.mPager.findViewWithTag(Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.oldnum - 1)).findViewById(2131624311);
                  ((ImageViewTouch)localObject1).setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
                  ((ImageViewTouch)localObject1).setImageDrawable(Activity_EditPhoto.this.bitmap, ((ImageViewTouch)localObject1).getDisplayMatrix(), -1.0F, -1.0F);
                  continue;
                  Activity_EditPhoto.this.edit_photo_pb.setVisibility(8);
                  Activity_EditPhoto.access$2202(Activity_EditPhoto.this, null);
                  continue;
                  Activity_EditPhoto.this.edit_photo_pb.setVisibility(8);
                  Activity_EditPhoto.access$2202(Activity_EditPhoto.this, null);
                  Activity_EditPhoto.this.mapp.setUpdate(false);
                  Object localObject3 = new ArrayList();
                  Object localObject4;
                  if (Activity_EditPhoto.this.export_size == 0)
                  {
                    localObject1 = new File(Activity_EditPhoto.this.preferences.getString("folder_path", ""));
                    localObject1 = ((File)localObject1).listFiles(new Activity_EditPhoto.MyFilter(Activity_EditPhoto.this, ".pdf"));
                    if (localObject1.length > 0)
                    {
                      ((ArrayList)localObject3).add(Uri.fromFile(localObject1[0]));
                      Activity_EditPhoto.this.getPackageManager().getInstalledApplications(0).size();
                      localObject4 = new Intent("android.intent.action.SEND_MULTIPLE");
                      ((Intent)localObject4).setType("application/pdf");
                    }
                  }
                  else
                  {
                    Object localObject5;
                    label771:
                    Object localObject6;
                    Object localObject2;
                    switch (Activity_EditPhoto.this.select_pdf_posiotion)
                    {
                    case 4: 
                    default: 
                      break;
                    case 0: 
                      localObject1 = new Intent("android.intent.action.SEND");
                      ((Intent)localObject1).setType("application/pdf");
                      localObject4 = new ArrayList();
                      localObject1 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
                      if (!((List)localObject1).isEmpty())
                      {
                        localObject5 = ((List)localObject1).iterator();
                        if (((Iterator)localObject5).hasNext())
                        {
                          localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                          if (((ArrayList)localObject3).size() > 1) {}
                          for (localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");; localObject1 = new Intent("android.intent.action.SEND"))
                          {
                            ((Intent)localObject1).setType("application/pdf");
                            ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", Activity_EditPhoto.this.preferences.getString("folder_name", ""));
                            if (((ArrayList)localObject3).size() > 0) {
                              ((Intent)localObject1).putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject3).get(0));
                            }
                            ((Intent)localObject1).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                            ((Intent)localObject1).setClassName(((ResolveInfo)localObject6).activityInfo.packageName, ((ResolveInfo)localObject6).activityInfo.name);
                            ((List)localObject4).add(localObject1);
                            break label771;
                            localObject1 = new File(Activity_EditPhoto.this.root_Path2);
                            break;
                          }
                        }
                        if (((List)localObject4).size() > 0)
                        {
                          localObject1 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Share PDF file");
                          ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
                          Activity_EditPhoto.this.startActivity((Intent)localObject1);
                        }
                        else
                        {
                          Toast.makeText(Activity_EditPhoto.activity_EditPhoto, "Error: Cannot open or share created PDF report.", 0).show();
                        }
                      }
                      break;
                    case 1: 
                      Activity_EditPhoto.this.mapp.setPdf_path(((Uri)((ArrayList)localObject3).get(0)).getPath());
                      Activity_EditPhoto.this.mapp.setPdf_Name(localObject1[0].getName().replace(".pdf", ""));
                      Activity_EditPhoto.this.mapp.setPdf_pages(Util.getPdfPages(((Uri)((ArrayList)localObject3).get(0)).getPath()));
                      try
                      {
                        this.printManager = ((PrintManager)Activity_EditPhoto.activity_EditPhoto.getSystemService("print"));
                        this.printManager.print(Activity_EditPhoto.this.mapp.getPdf_Name(), new MyPrintDocumentAdapter(Activity_EditPhoto.this.mapp), null);
                      }
                      catch (Exception localException)
                      {
                        Toast.makeText(Activity_EditPhoto.activity_EditPhoto, "printing error.", 0).show();
                      }
                      break;
                    case 2: 
                      localObject2 = new ArrayList();
                      localObject4 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject4, 0);
                      if (!((List)localObject4).isEmpty())
                      {
                        localObject4 = ((List)localObject4).iterator();
                        if (((Iterator)localObject4).hasNext())
                        {
                          localObject5 = (ResolveInfo)((Iterator)localObject4).next();
                          localObject6 = new Intent("android.intent.action.SEND_MULTIPLE");
                          ((Intent)localObject6).setType("application/pdf");
                          if (!"".equals(Activity_EditPhoto.this.preferences.getString("emailsetting_to", ""))) {
                            ((Intent)localObject6).putExtra("android.intent.extra.EMAIL", new String[] { Activity_EditPhoto.this.preferences.getString("emailsetting_to", "") });
                          }
                          if (!"".equals(Activity_EditPhoto.this.preferences.getString("emailsetting_subject", ""))) {
                            ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", Activity_EditPhoto.this.preferences.getString("emailsetting_subject", ""));
                          }
                          for (;;)
                          {
                            if (!"".equals(Activity_EditPhoto.this.preferences.getString("emailsetting_body", ""))) {
                              ((Intent)localObject6).putExtra("android.intent.extra.TEXT", Activity_EditPhoto.this.preferences.getString("emailsetting_body", ""));
                            }
                            if ((!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("inbox")) && (!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) && (!((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("blue")) && (!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("outlook"))) {
                              break;
                            }
                            ((Intent)localObject6).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                            ((Intent)localObject6).setPackage(((ResolveInfo)localObject5).activityInfo.packageName);
                            ((List)localObject2).add(localObject6);
                            break;
                            ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", Activity_EditPhoto.this.preferences.getString("folder_name", ""));
                          }
                        }
                        if (((List)localObject2).size() > 0)
                        {
                          localObject3 = Intent.createChooser((Intent)((List)localObject2).remove(0), "Email");
                          ((Intent)localObject3).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject2).toArray(new Parcelable[0]));
                          Activity_EditPhoto.this.startActivityForResult((Intent)localObject3, 3);
                        }
                        else
                        {
                          Toast.makeText(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getResources().getString(2131165275), 0).show();
                        }
                      }
                      break;
                    case 3: 
                      localObject2 = new Intent("android.intent.action.VIEW");
                      ((Intent)localObject2).setDataAndType((Uri)((ArrayList)localObject3).get(0), "application/pdf");
                      ((Intent)localObject2).setFlags(67108864);
                      Activity_EditPhoto.this.startActivityForResult(Intent.createChooser((Intent)localObject2, "Export"), 3);
                      break;
                    case 5: 
                      if (!Utils.findAndGotoApp1(Activity_EditPhoto.activity_EditPhoto, "com.studio.topfax", (ArrayList)localObject3))
                      {
                        Utils.showGooglePlaysimplefax(Activity_EditPhoto.activity_EditPhoto);
                        continue;
                        Activity_EditPhoto.this.edit_photo_pb.setVisibility(8);
                        Activity_EditPhoto.access$2202(Activity_EditPhoto.this, null);
                        Activity_EditPhoto.this.mapp.setUpdate(false);
                        localObject3 = new ArrayList();
                        if (Activity_EditPhoto.this.export_size == 0) {}
                        for (localObject2 = new File(Activity_EditPhoto.this.preferences.getString("folder_path", ""));; localObject2 = new File(Activity_EditPhoto.this.compressJpeg_Path))
                        {
                          localObject2 = ((File)localObject2).listFiles(new Activity_EditPhoto.MyFilter(Activity_EditPhoto.this, ".jpg"));
                          if ((localObject2 == null) || (localObject2.length <= 0)) {
                            break;
                          }
                          i = 0;
                          while (i < localObject2.length)
                          {
                            ((ArrayList)localObject3).add(Uri.fromFile(localObject2[i]));
                            i += 1;
                          }
                        }
                        Activity_EditPhoto.this.getPackageManager().getInstalledApplications(0).size();
                        localObject4 = new Intent("android.intent.action.SEND_MULTIPLE");
                        ((Intent)localObject4).putExtra("android.intent.extra.SUBJECT", "TopScanner");
                        ((Intent)localObject4).setType("image/jpeg");
                        switch (Activity_EditPhoto.this.select_jpeg_posiotion)
                        {
                        case 1: 
                        default: 
                          break;
                        case 0: 
                          if (((ArrayList)localObject3).size() > 1)
                          {
                            localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                            ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", "TopScanner");
                            ((Intent)localObject2).setType("image/jpeg");
                            localObject4 = new ArrayList();
                            localObject2 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
                            if (((List)localObject2).isEmpty()) {
                              continue;
                            }
                            localObject5 = ((List)localObject2).iterator();
                            if (!((Iterator)localObject5).hasNext()) {
                              break label2253;
                            }
                            localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                            if (((ArrayList)localObject3).size() <= 1) {
                              break label2227;
                            }
                            localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                            ((Intent)localObject2).setType("image/jpeg");
                            ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", Activity_EditPhoto.this.preferences.getString("folder_name", ""));
                            if (((ArrayList)localObject3).size() != 1) {
                              break label2240;
                            }
                            ((Intent)localObject2).putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject3).get(0));
                          }
                          for (;;)
                          {
                            ((Intent)localObject2).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                            ((Intent)localObject2).setClassName(((ResolveInfo)localObject6).activityInfo.packageName, ((ResolveInfo)localObject6).activityInfo.name);
                            ((List)localObject4).add(localObject2);
                            break label2068;
                            localObject2 = new Intent("android.intent.action.SEND");
                            break;
                            localObject2 = new Intent("android.intent.action.SEND");
                            break label2109;
                            ((Intent)localObject2).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                          }
                          if (((List)localObject4).size() > 0)
                          {
                            localObject2 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Share JPEG file");
                            ((Intent)localObject2).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
                            Activity_EditPhoto.this.startActivity((Intent)localObject2);
                          }
                          else
                          {
                            Toast.makeText(Activity_EditPhoto.activity_EditPhoto, "Error: No resource images found.", 0).show();
                          }
                          break;
                        case 2: 
                          label2068:
                          label2109:
                          label2227:
                          label2240:
                          label2253:
                          localObject2 = new ArrayList();
                          localObject4 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject4, 0);
                          if (!((List)localObject4).isEmpty())
                          {
                            localObject4 = ((List)localObject4).iterator();
                            if (((Iterator)localObject4).hasNext())
                            {
                              localObject5 = (ResolveInfo)((Iterator)localObject4).next();
                              localObject6 = new Intent("android.intent.action.SEND_MULTIPLE");
                              ((Intent)localObject6).setType("image/jpeg");
                              if (!"".equals(Activity_EditPhoto.this.preferences.getString("emailsetting_to", ""))) {
                                ((Intent)localObject6).putExtra("android.intent.extra.EMAIL", new String[] { Activity_EditPhoto.this.preferences.getString("emailsetting_to", "") });
                              }
                              if (!"".equals(Activity_EditPhoto.this.preferences.getString("emailsetting_subject", ""))) {
                                ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", Activity_EditPhoto.this.preferences.getString("emailsetting_subject", ""));
                              }
                              for (;;)
                              {
                                if (!"".equals(Activity_EditPhoto.this.preferences.getString("emailsetting_body", ""))) {
                                  ((Intent)localObject6).putExtra("android.intent.extra.TEXT", Activity_EditPhoto.this.preferences.getString("emailsetting_body", ""));
                                }
                                if ((!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("mail")) && (!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("inbox")) && (!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) && (!((ResolveInfo)localObject5).activityInfo.name.toLowerCase().contains("blue")) && (!((ResolveInfo)localObject5).activityInfo.packageName.toLowerCase().contains("outlook"))) {
                                  break;
                                }
                                ((Intent)localObject6).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                                ((Intent)localObject6).setPackage(((ResolveInfo)localObject5).activityInfo.packageName);
                                ((List)localObject2).add(localObject6);
                                break;
                                ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", Activity_EditPhoto.this.preferences.getString("folder_name", ""));
                              }
                            }
                            if (((List)localObject2).size() > 0)
                            {
                              localObject3 = Intent.createChooser((Intent)((List)localObject2).remove(0), "Export");
                              ((Intent)localObject3).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject2).toArray(new Parcelable[0]));
                              Activity_EditPhoto.this.startActivityForResult((Intent)localObject3, 3);
                            }
                            else
                            {
                              Toast.makeText(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getResources().getString(2131165275), 0).show();
                              continue;
                              if ((((Integer)paramAnonymousMessage.obj).intValue() == Activity_EditPhoto.this.num) && (!Activity_EditPhoto.this.isScroll))
                              {
                                localObject2 = Activity_EditPhoto.this.mPager.findViewWithTag(Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1));
                                if (localObject2 != null)
                                {
                                  localObject2 = (ImageViewTouch)((View)localObject2).findViewById(2131624311);
                                  ((ImageViewTouch)localObject2).setImageDrawable(Activity_EditPhoto.this.bitmap2, ((ImageViewTouch)localObject2).getDisplayMatrix(), -1.0F, -1.0F);
                                  continue;
                                  Activity_EditPhoto.this.edit_photo_pb.setVisibility(8);
                                  Activity_EditPhoto.access$302(Activity_EditPhoto.this, false);
                                  Activity_EditPhoto.this.finish();
                                  continue;
                                  Activity_EditPhoto.this.edit_photo_pb.setVisibility(8);
                                  Activity_EditPhoto.access$302(Activity_EditPhoto.this, false);
                                  Activity_EditPhoto.this.finish();
                                  continue;
                                  if (!Activity_EditPhoto.this.is_viewgone) {
                                    Activity_EditPhoto.this.createTranslateAnimation_xia();
                                  } else {
                                    Activity_EditPhoto.this.createTranslateAnimation_top();
                                  }
                                }
                              }
                            }
                          }
                          break;
                        }
                      }
                      break;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  };
  private IAPBuy iapBuy;
  private ImageViewTouch image;
  public ImageViewTouch imgview;
  private LayoutInflater inflater;
  private boolean isListSelected = false;
  private boolean isListSelecting = false;
  private boolean isRotate = false;
  private boolean isRun = false;
  private boolean isScroll = false;
  private boolean is_viewgone = false;
  private boolean isonkeydown = false;
  public MyViewPager mPager;
  private Thread mThread = null;
  private MyApplication mapp;
  private Bitmap mbit = null;
  private DisplayMetrics metrics;
  private ArrayList<Photo_item> mlist = null;
  private ArrayList<Integer> mlist2 = null;
  View.OnClickListener mlistener2 = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      }
      do
      {
        do
        {
          return;
        } while (Activity_EditPhoto.this.isRun);
        Activity_EditPhoto.this.finish();
        return;
      } while (Activity_EditPhoto.this.isRun);
      Activity_EditPhoto.this.initPopuptWindow2();
      Activity_EditPhoto.this.popupWindow.showAtLocation(paramAnonymousView, 53, 0, 0);
    }
  };
  private ArrayList<MainListDao> moreList = new ArrayList();
  private int num = 1;
  private int oldnum = 0;
  private TextView other;
  private int pdf_OR_jpeg = 1;
  private PopupWindow popupWindow;
  private SharedPreferences preferences;
  private ProgressDialog progressDialog;
  private String root_Path2;
  private ImageView rotate;
  private int select_jpeg_posiotion = 0;
  private int select_pdf_posiotion = 0;
  private RelativeLayout share;
  private TextView size;
  private Thread thread = null;
  
  public Activity_EditPhoto() {}
  
  private void buy()
  {
    startActivity(new Intent(this.context, SupportUs_Activity.class));
  }
  
  private void createTranslateAnimation_top()
  {
    if (!this.mapp.isPad())
    {
      localTranslateAnimation = new TranslateAnimation(0, 0.0F, 0, 0.0F, 0, dip2px(56.0F), 0, 0.0F);
      localTranslateAnimation.setDuration(500L);
      localTranslateAnimation.setFillAfter(true);
      this.edit_photo_bottomclick_layout.startAnimation(localTranslateAnimation);
      this.other.startAnimation(localTranslateAnimation);
    }
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0, 0.0F, 0, 0.0F, 0, -dip2px(56.0F), 0, 0.0F);
    localTranslateAnimation.setDuration(500L);
    localTranslateAnimation.setFillAfter(true);
    this.edit_photo_layout1.startAnimation(localTranslateAnimation);
    this.is_viewgone = false;
  }
  
  private void createTranslateAnimation_xia()
  {
    if (!this.mapp.isPad())
    {
      localTranslateAnimation = new TranslateAnimation(0, 0.0F, 0, 0.0F, 0, 0.0F, 0, dip2px(56.0F));
      localTranslateAnimation.setDuration(500L);
      localTranslateAnimation.setFillAfter(true);
      this.edit_photo_bottomclick_layout.startAnimation(localTranslateAnimation);
      this.other.startAnimation(localTranslateAnimation);
    }
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0, 0.0F, 0, 0.0F, 0, 0.0F, 0, -dip2px(56.0F));
    localTranslateAnimation.setDuration(500L);
    localTranslateAnimation.setFillAfter(true);
    this.edit_photo_layout1.startAnimation(localTranslateAnimation);
    this.is_viewgone = true;
  }
  
  private void delete_single_image()
  {
    if (this.isRun) {
      return;
    }
    new AlertDialog.Builder(this.context).setMessage(getString(2131165241)).setPositiveButton(getString(2131165458), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = ((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath();
        new File(paramAnonymousDialogInterface).delete();
        Activity_EditPhoto.this.mapp.setUpdate(true);
        Activity_EditPhoto.this.deletePDF();
        Activity_EditPhoto.this.mlist.remove(Activity_EditPhoto.this.num - 1);
        File localFile = new File(Activity_EditPhoto.this.preferences.getString("folder_path", ""));
        String[] arrayOfString = localFile.list();
        if (Activity_EditPhoto.this.mlist.size() < 1)
        {
          if (arrayOfString.length < 1) {
            localFile.delete();
          }
          Activity_EditPhoto.this.finish();
          return;
        }
        Collections.sort(Activity_EditPhoto.this.mlist, Activity_EditPhoto.this.comparator);
        if ((Activity_EditPhoto.this.num == 1) && (Activity_EditPhoto.this.mapp.getBitmapFromMemCache(Activity_EditPhoto.this.preferences.getString("folder_path", "")) != null)) {
          Activity_EditPhoto.this.mapp.getmMemoryCache().remove(Activity_EditPhoto.this.preferences.getString("folder_path", ""));
        }
        if (Activity_EditPhoto.this.mapp.getBitmapFromMemCache(paramAnonymousDialogInterface) != null) {
          Activity_EditPhoto.this.mapp.getmMemoryCache().remove(paramAnonymousDialogInterface);
        }
        Activity_EditPhoto.access$3402(Activity_EditPhoto.this, new MyPageAdapter(Activity_EditPhoto.this.context, Activity_EditPhoto.this.mlist, Activity_EditPhoto.this.handler));
        Activity_EditPhoto.this.mPager.setAdapter(Activity_EditPhoto.this.adapter);
        Activity_EditPhoto.access$702(Activity_EditPhoto.this, Activity_EditPhoto.this.num - 1);
        if (Activity_EditPhoto.this.num == 0) {
          Activity_EditPhoto.access$702(Activity_EditPhoto.this, 1);
        }
        Activity_EditPhoto.this.mPager.setCurrentItem(Activity_EditPhoto.this.num - 1);
        Activity_EditPhoto.this.other.setText(Activity_EditPhoto.this.num + "/" + Activity_EditPhoto.this.mlist.size());
        Activity_EditPhoto.access$1602(Activity_EditPhoto.this, new Thread(new Runnable()
        {
          public void run()
          {
            int i;
            if (!Activity_EditPhoto.this.thread.isInterrupted()) {
              i = Activity_EditPhoto.this.num;
            }
            try
            {
              Activity_EditPhoto.access$1702(Activity_EditPhoto.this, BitmapTools.getSize(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath()));
              Activity_EditPhoto.access$902(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mapp.decodeSampledBitmapFromFile(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), ((Integer)Activity_EditPhoto.this.mlist2.get(0)).intValue(), ((Integer)Activity_EditPhoto.this.mlist2.get(1)).intValue())));
              if (Activity_EditPhoto.this.bitmap2 == null)
              {
                Activity_EditPhoto.access$902(Activity_EditPhoto.this, Activity_EditPhoto.this.getBitmapDrawable(BitmapFactory.decodeStream(new FileInputStream(new File(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath())))));
                Activity_EditPhoto.this.mapp.addResuableBit(Activity_EditPhoto.this.bitmap2);
              }
              Message localMessage = new Message();
              localMessage.what = 5;
              localMessage.obj = Integer.valueOf(i);
              Activity_EditPhoto.this.handler.sendMessage(localMessage);
              return;
            }
            catch (FileNotFoundException localFileNotFoundException)
            {
              for (;;)
              {
                localFileNotFoundException.printStackTrace();
              }
            }
          }
        }));
        Activity_EditPhoto.this.thread.start();
      }
    }).setNegativeButton(getString(2131165274), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).create().show();
  }
  
  private int dip2px(float paramFloat)
  {
    return (int)(paramFloat * getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static Activity_EditPhoto getActivity_EditPhoto()
  {
    return activity_EditPhoto;
  }
  
  private String getRealPathFromURI(Uri paramUri)
  {
    paramUri = new CursorLoader(this.context, paramUri, new String[] { "_data" }, null, null, null).loadInBackground();
    if (paramUri != null)
    {
      int i = paramUri.getColumnIndexOrThrow("_data");
      paramUri.moveToFirst();
      return paramUri.getString(i);
    }
    return "";
  }
  
  private long getfileSizeLength()
  {
    long l = 0L;
    int i = 0;
    while (i < this.mlist.size())
    {
      l += new File(((Photo_item)this.mlist.get(i)).getPath()).length();
      i += 1;
    }
    return l;
  }
  
  @SuppressLint({"InflateParams"})
  private void initPopuptWindow2()
  {
    Object localObject1 = new ArrayList();
    if (this.mapp.isPad())
    {
      ((ArrayList)localObject1).clear();
      ((ArrayList)localObject1).add(activity_EditPhoto.getResources().getString(2131165775));
      if (Build.VERSION.SDK_INT >= 19) {
        ((ArrayList)localObject1).add(activity_EditPhoto.getResources().getString(2131165520));
      }
      ((ArrayList)localObject1).add(activity_EditPhoto.getResources().getString(2131165382));
      ((ArrayList)localObject1).add(activity_EditPhoto.getResources().getString(2131165553));
    }
    for (;;)
    {
      this.moreList.clear();
      i = 0;
      while (i < ((ArrayList)localObject1).size())
      {
        localObject2 = new MainListDao();
        ((MainListDao)localObject2).setText((String)((ArrayList)localObject1).get(i));
        this.moreList.add(localObject2);
        i += 1;
      }
      ((ArrayList)localObject1).clear();
      ((ArrayList)localObject1).add(activity_EditPhoto.getResources().getString(2131165775));
      if (Build.VERSION.SDK_INT >= 19) {
        ((ArrayList)localObject1).add(activity_EditPhoto.getResources().getString(2131165520));
      }
      ((ArrayList)localObject1).add(activity_EditPhoto.getResources().getString(2131165382));
      ((ArrayList)localObject1).add(activity_EditPhoto.getResources().getString(2131165553));
    }
    if (this.popupWindow != null)
    {
      this.popupWindow.dismiss();
      this.popupWindow = null;
    }
    localObject1 = activity_EditPhoto.getLayoutInflater().inflate(2130903104, null, false);
    this.popupWindow = new PopupWindow(activity_EditPhoto);
    Object localObject2 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject2);
    int i = ((DisplayMetrics)localObject2).widthPixels;
    this.popupWindow.setHeight(-2);
    if (this.mapp.isPad()) {
      if (getResources().getConfiguration().orientation == 1) {
        this.popupWindow.setWidth(i * 2 / 5);
      }
    }
    for (;;)
    {
      this.popupWindow.setBackgroundDrawable(new BitmapDrawable());
      this.popupWindow.setOutsideTouchable(true);
      this.popupWindow.setFocusable(true);
      this.popupWindow.setContentView((View)localObject1);
      ((View)localObject1).setOnTouchListener(new View.OnTouchListener()
      {
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if ((Activity_EditPhoto.this.popupWindow != null) && (Activity_EditPhoto.this.popupWindow.isShowing())) {
            Activity_EditPhoto.this.popupWindow.dismiss();
          }
          Activity_EditPhoto.access$3302(Activity_EditPhoto.this, null);
          return false;
        }
      });
      localObject2 = (ListView)((View)localObject1).findViewById(2131624306);
      ((ListView)localObject2).setAdapter(new MoreListAdapter(activity_EditPhoto, this.moreList));
      ((ListView)localObject2).setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if ((Activity_EditPhoto.this.popupWindow != null) && (Activity_EditPhoto.this.popupWindow.isShowing())) {
            Activity_EditPhoto.this.popupWindow.dismiss();
          }
          Activity_EditPhoto.access$3302(Activity_EditPhoto.this, null);
          if (((MainListDao)Activity_EditPhoto.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_EditPhoto.activity_EditPhoto.getResources().getString(2131165520))) {}
          do
          {
            try
            {
              Activity_EditPhoto.this.print(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath());
              return;
            }
            catch (FileNotFoundException paramAnonymousAdapterView)
            {
              paramAnonymousAdapterView.printStackTrace();
              return;
            }
            if (((MainListDao)Activity_EditPhoto.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_EditPhoto.activity_EditPhoto.getResources().getString(2131165775)))
            {
              if (new File(Environment.getExternalStorageDirectory() + "/TopScanner/SignPng/topscanner_sign.png").exists())
              {
                Log.i("TAG", "22222222===================");
                paramAnonymousAdapterView = new Intent(Activity_EditPhoto.activity_EditPhoto, ImageSignActivity.class);
                paramAnonymousAdapterView.putExtra("image_path", "" + ((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath());
                Activity_EditPhoto.this.startActivity(paramAnonymousAdapterView);
                return;
              }
              Log.i("TAG", "1111111===================");
              paramAnonymousAdapterView = new Intent(Activity_EditPhoto.activity_EditPhoto, SettingSignActivity.class);
              Activity_EditPhoto.this.startActivityForResult(paramAnonymousAdapterView, 100);
              return;
            }
            if (((MainListDao)Activity_EditPhoto.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_EditPhoto.activity_EditPhoto.getResources().getString(2131165382)))
            {
              Activity_EditPhoto.this.showPgeselect();
              return;
            }
          } while (!((MainListDao)Activity_EditPhoto.this.moreList.get(paramAnonymousInt)).getText().toString().equals(Activity_EditPhoto.activity_EditPhoto.getResources().getString(2131165553)));
          new Thread(new Runnable()
          {
            public void run()
            {
              Object localObject = new File(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath());
              File localFile = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/TopScanner");
              if (!localFile.exists()) {
                localFile.mkdirs();
              }
              localFile = new File(localFile.getPath() + "/" + Activity_EditPhoto.this.preferences.getString("folder_name", "") + "_" + (Activity_EditPhoto.this.num - 1) + ".jpg");
              if ((localFile != null) && (localFile.exists())) {
                localFile.delete();
              }
              try
              {
                Activity_EditPhoto.this.copy((File)localObject, localFile);
                MediaScannerConnection.scanFile(Activity_EditPhoto.this.getApplicationContext(), new String[] { localFile.getAbsolutePath() }, null, null);
                localObject = new Message();
                ((Message)localObject).what = 10;
                Activity_EditPhoto.this.handler.sendMessage((Message)localObject);
                return;
              }
              catch (IOException localIOException)
              {
                for (;;)
                {
                  localIOException.printStackTrace();
                }
              }
            }
          }).start();
        }
      });
      ((LinearLayout)((View)localObject1).findViewById(2131624305)).setOnKeyListener(new View.OnKeyListener()
      {
        public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          if ((paramAnonymousKeyEvent.getAction() == 0) && (paramAnonymousInt == 4) && (Activity_EditPhoto.this.popupWindow != null) && (Activity_EditPhoto.this.popupWindow.isShowing())) {
            Activity_EditPhoto.this.popupWindow.dismiss();
          }
          Activity_EditPhoto.access$3302(Activity_EditPhoto.this, null);
          return false;
        }
      });
      return;
      this.popupWindow.setWidth(i * 2 / 7);
      continue;
      this.popupWindow.setWidth(i * 7 / 10);
    }
  }
  
  private void onAuthenticated(int paramInt, Intent paramIntent)
  {
    if (-1 != paramInt) {
      Toast.makeText(this, getResources().getString(2131165389), 1).show();
    }
  }
  
  private void onFolderSelected(int paramInt, Intent paramIntent)
  {
    if (-1 != paramInt) {
      Toast.makeText(this, getResources().getString(2131165315), 1).show();
    }
  }
  
  private void print(String paramString)
    throws FileNotFoundException
  {
    PrintHelper localPrintHelper = new PrintHelper(this);
    localPrintHelper.setScaleMode(1);
    paramString = BitmapFactory.decodeStream(new FileInputStream(new File(paramString)));
    localPrintHelper.printBitmap(this.preferences.getString("folder_name", ""), paramString);
  }
  
  @SuppressLint({"InflateParams"})
  private void showPgeselect()
  {
    Object localObject = this.inflater.inflate(2130903097, null);
    final NumberPicker localNumberPicker = (NumberPicker)((View)localObject).findViewById(2131624289);
    localNumberPicker.setDescendantFocusability(393216);
    localNumberPicker.setMaxValue(this.mlist.size());
    localNumberPicker.setMinValue(1);
    localNumberPicker.setValue(this.mPager.getCurrentItem() + 1);
    localNumberPicker.setWrapSelectorWheel(false);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(activity_EditPhoto);
    localBuilder.setTitle(activity_EditPhoto.getResources().getString(2131165382)).setView((View)localObject).setPositiveButton(activity_EditPhoto.getResources().getString(2131165458), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        Activity_EditPhoto.this.mPager.setCurrentItem(localNumberPicker.getValue() - 1, false);
      }
    }).setNegativeButton(activity_EditPhoto.getResources().getString(2131165274), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localObject = localBuilder.create();
    ((AlertDialog)localObject).setCanceledOnTouchOutside(false);
    if (!activity_EditPhoto.isFinishing()) {
      ((AlertDialog)localObject).show();
    }
  }
  
  private void thankBuy(String paramString)
  {
    new AlertDialog.Builder(this).setMessage(paramString).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    }).create().show();
  }
  
  public void CompressImage()
  {
    Object localObject = new File(this.compressJpeg_Path);
    if (((File)localObject).exists())
    {
      localObject = ((File)localObject).listFiles(new MyFilter(".jpg"));
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        clearFile(localObject[i]);
        i += 1;
      }
    }
    ((File)localObject).mkdir();
    if (this.isRun) {
      return;
    }
    this.edit_photo_pb.setVisibility(0);
    this.mThread = new Thread(new Runnable()
    {
      public void run()
      {
        Object localObject1 = new File(Activity_EditPhoto.this.preferences.getString("folder_path", ""));
        Object localObject2 = ((File)localObject1).list();
        ArrayList localArrayList = new ArrayList();
        if (localObject2 != null)
        {
          i = 0;
          while (i < localObject2.length)
          {
            if (localObject2[i].matches("[0-9]{18}.jpg")) {
              localArrayList.add(localObject2[i]);
            }
            i += 1;
          }
        }
        Collections.sort(localArrayList, Activity_EditPhoto.this.comparator3);
        int j = localArrayList.size();
        int i = 0;
        for (;;)
        {
          if (i < j)
          {
            BufferedOutputStream localBufferedOutputStream;
            try
            {
              localObject2 = BitmapFactory.decodeStream(new FileInputStream(new File(((File)localObject1).getPath() + "/" + (String)localArrayList.get(i))));
              localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(Activity_EditPhoto.this.compressJpeg_Path + (String)localArrayList.get(i)));
              if (Activity_EditPhoto.this.export_size == 0) {
                ((Bitmap)localObject2).compress(Bitmap.CompressFormat.JPEG, 100, localBufferedOutputStream);
              } else if (Activity_EditPhoto.this.export_size == 1) {
                ((Bitmap)localObject2).compress(Bitmap.CompressFormat.JPEG, 50, localBufferedOutputStream);
              }
            }
            catch (IOException localIOException)
            {
              System.err.println(localIOException.getMessage());
            }
            if (Activity_EditPhoto.this.export_size == 2) {
              localIOException.compress(Bitmap.CompressFormat.JPEG, 5, localBufferedOutputStream);
            }
          }
          else
          {
            localObject1 = new Message();
            ((Message)localObject1).what = 33;
            Activity_EditPhoto.this.handler.sendMessage((Message)localObject1);
            return;
          }
          i += 1;
        }
      }
    });
    this.mThread.start();
  }
  
  public void clearFile(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        clearFile(arrayOfFile[i]);
        i += 1;
      }
    }
    paramFile.delete();
  }
  
  public void copy(File paramFile1, File paramFile2)
    throws IOException
  {
    paramFile1 = new FileInputStream(paramFile1);
    paramFile2 = new FileOutputStream(paramFile2);
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramFile1.read(arrayOfByte);
      if (i <= 0) {
        break;
      }
      paramFile2.write(arrayOfByte, 0, i);
    }
    paramFile1.close();
    paramFile2.close();
  }
  
  public void createPDF()
  {
    Object localObject = new File(this.root_Path2);
    if (((File)localObject).exists())
    {
      localObject = ((File)localObject).listFiles(new MyFilter(".pdf"));
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        clearFile(localObject[i]);
        i += 1;
      }
    }
    ((File)localObject).mkdir();
    if (this.isRun) {
      return;
    }
    this.edit_photo_pb.setVisibility(0);
    this.mThread = new Thread(new Runnable()
    {
      private PdfWriter writer;
      
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: new 31	java/io/File
        //   3: dup
        //   4: aload_0
        //   5: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   8: invokestatic 35	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2400	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   11: ldc 37
        //   13: ldc 39
        //   15: invokeinterface 45 3 0
        //   20: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   23: astore 6
        //   25: aload_0
        //   26: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   29: invokestatic 52	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2300	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)I
        //   32: ifne +222 -> 254
        //   35: aload 6
        //   37: new 54	com/simpleapp/tinyscanfree/Activity_EditPhoto$MyFilter
        //   40: dup
        //   41: aload_0
        //   42: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   45: ldc 56
        //   47: invokespecial 59	com/simpleapp/tinyscanfree/Activity_EditPhoto$MyFilter:<init>	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;Ljava/lang/String;)V
        //   50: invokevirtual 63	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
        //   53: astore_3
        //   54: aload_3
        //   55: ifnull +15 -> 70
        //   58: aload_3
        //   59: arraylength
        //   60: ifle +10 -> 70
        //   63: aload_3
        //   64: iconst_0
        //   65: aaload
        //   66: invokevirtual 67	java/io/File:delete	()Z
        //   69: pop
        //   70: new 31	java/io/File
        //   73: dup
        //   74: new 69	java/lang/StringBuilder
        //   77: dup
        //   78: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   81: aload_0
        //   82: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   85: invokestatic 35	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2400	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   88: ldc 37
        //   90: ldc 39
        //   92: invokeinterface 45 3 0
        //   97: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: ldc 76
        //   102: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: aload_0
        //   106: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   109: invokestatic 35	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2400	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   112: ldc 37
        //   114: ldc 39
        //   116: invokeinterface 45 3 0
        //   121: aload_0
        //   122: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   125: invokestatic 35	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2400	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   128: ldc 37
        //   130: ldc 39
        //   132: invokeinterface 45 3 0
        //   137: ldc 76
        //   139: invokevirtual 82	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
        //   142: iconst_1
        //   143: iadd
        //   144: aload_0
        //   145: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   148: invokestatic 35	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2400	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   151: ldc 37
        //   153: ldc 39
        //   155: invokeinterface 45 3 0
        //   160: invokevirtual 86	java/lang/String:length	()I
        //   163: invokevirtual 90	java/lang/String:substring	(II)Ljava/lang/String;
        //   166: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: ldc 56
        //   171: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   174: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   177: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   180: astore_3
        //   181: aload_3
        //   182: invokevirtual 97	java/io/File:exists	()Z
        //   185: ifeq +8 -> 193
        //   188: aload_3
        //   189: invokevirtual 67	java/io/File:delete	()Z
        //   192: pop
        //   193: aload 6
        //   195: invokevirtual 101	java/io/File:list	()[Ljava/lang/String;
        //   198: astore 4
        //   200: new 103	java/util/ArrayList
        //   203: dup
        //   204: invokespecial 104	java/util/ArrayList:<init>	()V
        //   207: astore 7
        //   209: aload 4
        //   211: ifnull +143 -> 354
        //   214: iconst_0
        //   215: istore_1
        //   216: iload_1
        //   217: aload 4
        //   219: arraylength
        //   220: if_icmpge +134 -> 354
        //   223: aload 4
        //   225: iload_1
        //   226: aaload
        //   227: ldc 106
        //   229: invokevirtual 110	java/lang/String:matches	(Ljava/lang/String;)Z
        //   232: ifeq +15 -> 247
        //   235: aload 7
        //   237: aload 4
        //   239: iload_1
        //   240: aaload
        //   241: invokeinterface 116 2 0
        //   246: pop
        //   247: iload_1
        //   248: iconst_1
        //   249: iadd
        //   250: istore_1
        //   251: goto -35 -> 216
        //   254: new 31	java/io/File
        //   257: dup
        //   258: new 69	java/lang/StringBuilder
        //   261: dup
        //   262: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   265: aload_0
        //   266: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   269: invokestatic 120	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)Ljava/lang/String;
        //   272: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: aload_0
        //   276: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   279: invokestatic 35	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2400	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   282: ldc 37
        //   284: ldc 39
        //   286: invokeinterface 45 3 0
        //   291: aload_0
        //   292: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   295: invokestatic 35	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2400	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   298: ldc 37
        //   300: ldc 39
        //   302: invokeinterface 45 3 0
        //   307: ldc 76
        //   309: invokevirtual 82	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
        //   312: iconst_1
        //   313: iadd
        //   314: aload_0
        //   315: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   318: invokestatic 35	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2400	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   321: ldc 37
        //   323: ldc 39
        //   325: invokeinterface 45 3 0
        //   330: invokevirtual 86	java/lang/String:length	()I
        //   333: invokevirtual 90	java/lang/String:substring	(II)Ljava/lang/String;
        //   336: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   339: ldc 56
        //   341: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   344: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   347: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   350: astore_3
        //   351: goto -170 -> 181
        //   354: aload 7
        //   356: aload_0
        //   357: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   360: getfield 124	com/simpleapp/tinyscanfree/Activity_EditPhoto:comparator3	Ljava/util/Comparator;
        //   363: invokestatic 130	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
        //   366: getstatic 136	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   369: astore 4
        //   371: new 138	com/itextpdf/text/Document
        //   374: dup
        //   375: invokespecial 139	com/itextpdf/text/Document:<init>	()V
        //   378: astore 8
        //   380: aconst_null
        //   381: astore 5
        //   383: aconst_null
        //   384: astore 4
        //   386: new 141	com/itextpdf/text/pdf/PdfCopy
        //   389: dup
        //   390: aload 8
        //   392: new 143	java/io/FileOutputStream
        //   395: dup
        //   396: aload_3
        //   397: invokespecial 146	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   400: invokespecial 149	com/itextpdf/text/pdf/PdfCopy:<init>	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)V
        //   403: astore_3
        //   404: aload 8
        //   406: invokevirtual 152	com/itextpdf/text/Document:open	()V
        //   409: aload_3
        //   410: astore 4
        //   412: aload 7
        //   414: invokeinterface 155 1 0
        //   419: istore_2
        //   420: iconst_0
        //   421: istore_1
        //   422: iload_1
        //   423: iload_2
        //   424: if_icmpge +742 -> 1166
        //   427: aload 7
        //   429: iload_1
        //   430: invokeinterface 159 2 0
        //   435: checkcast 78	java/lang/String
        //   438: bipush 14
        //   440: bipush 15
        //   442: invokevirtual 90	java/lang/String:substring	(II)Ljava/lang/String;
        //   445: invokestatic 164	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   448: tableswitch	default:+40->488, 0:+473->921, 1:+480->928, 2:+487->935, 3:+494->942, 4:+501->949, 5:+508->956
        //   488: getstatic 136	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   491: astore_3
        //   492: new 138	com/itextpdf/text/Document
        //   495: dup
        //   496: aload_3
        //   497: invokespecial 167	com/itextpdf/text/Document:<init>	(Lcom/itextpdf/text/Rectangle;)V
        //   500: astore 5
        //   502: aload_0
        //   503: aload 5
        //   505: new 143	java/io/FileOutputStream
        //   508: dup
        //   509: new 69	java/lang/StringBuilder
        //   512: dup
        //   513: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   516: aload_0
        //   517: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   520: invokestatic 120	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)Ljava/lang/String;
        //   523: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   526: aload 7
        //   528: iload_1
        //   529: invokeinterface 159 2 0
        //   534: checkcast 78	java/lang/String
        //   537: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   540: ldc 56
        //   542: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   545: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   548: invokespecial 168	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
        //   551: invokestatic 174	com/itextpdf/text/pdf/PdfWriter:getInstance	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)Lcom/itextpdf/text/pdf/PdfWriter;
        //   554: putfield 176	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   557: aload 5
        //   559: invokevirtual 152	com/itextpdf/text/Document:open	()V
        //   562: aconst_null
        //   563: astore_3
        //   564: new 178	java/io/FileInputStream
        //   567: dup
        //   568: new 31	java/io/File
        //   571: dup
        //   572: new 69	java/lang/StringBuilder
        //   575: dup
        //   576: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   579: aload 6
        //   581: invokevirtual 181	java/io/File:getPath	()Ljava/lang/String;
        //   584: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   587: ldc 76
        //   589: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   592: aload 7
        //   594: iload_1
        //   595: invokeinterface 159 2 0
        //   600: checkcast 78	java/lang/String
        //   603: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   606: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   609: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   612: invokespecial 182	java/io/FileInputStream:<init>	(Ljava/io/File;)V
        //   615: invokestatic 188	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   618: astore 9
        //   620: new 190	java/io/ByteArrayOutputStream
        //   623: dup
        //   624: invokespecial 191	java/io/ByteArrayOutputStream:<init>	()V
        //   627: astore 10
        //   629: aload_0
        //   630: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   633: invokestatic 52	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2300	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)I
        //   636: ifne +337 -> 973
        //   639: new 69	java/lang/StringBuilder
        //   642: dup
        //   643: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   646: aload 6
        //   648: invokevirtual 181	java/io/File:getPath	()Ljava/lang/String;
        //   651: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   654: ldc 76
        //   656: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   659: aload 7
        //   661: iload_1
        //   662: invokeinterface 159 2 0
        //   667: checkcast 78	java/lang/String
        //   670: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   673: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   676: invokestatic 196	com/itextpdf/text/Image:getInstance	(Ljava/lang/String;)Lcom/itextpdf/text/Image;
        //   679: astore_3
        //   680: aload_3
        //   681: invokevirtual 200	com/itextpdf/text/Image:getWidth	()F
        //   684: aload 5
        //   686: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   689: invokevirtual 207	com/itextpdf/text/Rectangle:getWidth	()F
        //   692: fcmpl
        //   693: ifge +19 -> 712
        //   696: aload_3
        //   697: invokevirtual 210	com/itextpdf/text/Image:getHeight	()F
        //   700: aload 5
        //   702: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   705: invokevirtual 211	com/itextpdf/text/Rectangle:getHeight	()F
        //   708: fcmpl
        //   709: iflt +12 -> 721
        //   712: aload_3
        //   713: aload 5
        //   715: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   718: invokevirtual 214	com/itextpdf/text/Image:scaleToFit	(Lcom/itextpdf/text/Rectangle;)V
        //   721: aload_3
        //   722: aload 5
        //   724: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   727: invokevirtual 207	com/itextpdf/text/Rectangle:getWidth	()F
        //   730: aload_3
        //   731: invokevirtual 217	com/itextpdf/text/Image:getScaledWidth	()F
        //   734: fsub
        //   735: fconst_2
        //   736: fdiv
        //   737: aload 5
        //   739: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   742: invokevirtual 211	com/itextpdf/text/Rectangle:getHeight	()F
        //   745: aload_3
        //   746: invokevirtual 220	com/itextpdf/text/Image:getScaledHeight	()F
        //   749: fsub
        //   750: fconst_2
        //   751: fdiv
        //   752: invokevirtual 224	com/itextpdf/text/Image:setAbsolutePosition	(FF)V
        //   755: aload 5
        //   757: aload_3
        //   758: invokevirtual 227	com/itextpdf/text/Document:add	(Lcom/itextpdf/text/Element;)Z
        //   761: pop
        //   762: aload_0
        //   763: getfield 176	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   766: invokevirtual 230	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   769: ifeq +18 -> 787
        //   772: aload_0
        //   773: getfield 176	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   776: invokevirtual 233	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   779: ifne +8 -> 787
        //   782: aload 5
        //   784: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   787: new 238	com/itextpdf/text/pdf/PdfReader
        //   790: dup
        //   791: new 69	java/lang/StringBuilder
        //   794: dup
        //   795: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   798: aload_0
        //   799: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   802: invokestatic 120	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)Ljava/lang/String;
        //   805: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   808: aload 7
        //   810: iload_1
        //   811: invokeinterface 159 2 0
        //   816: checkcast 78	java/lang/String
        //   819: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   822: ldc 56
        //   824: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   827: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   830: invokespecial 239	com/itextpdf/text/pdf/PdfReader:<init>	(Ljava/lang/String;)V
        //   833: astore_3
        //   834: aload 4
        //   836: aload_3
        //   837: invokevirtual 243	com/itextpdf/text/pdf/PdfCopy:addDocument	(Lcom/itextpdf/text/pdf/PdfReader;)V
        //   840: aload_3
        //   841: invokevirtual 244	com/itextpdf/text/pdf/PdfReader:close	()V
        //   844: new 31	java/io/File
        //   847: dup
        //   848: new 69	java/lang/StringBuilder
        //   851: dup
        //   852: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   855: aload_0
        //   856: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   859: invokestatic 120	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)Ljava/lang/String;
        //   862: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   865: aload 7
        //   867: iload_1
        //   868: invokeinterface 159 2 0
        //   873: checkcast 78	java/lang/String
        //   876: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   879: ldc 56
        //   881: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   884: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   887: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   890: invokevirtual 67	java/io/File:delete	()Z
        //   893: pop
        //   894: iload_1
        //   895: iconst_1
        //   896: iadd
        //   897: istore_1
        //   898: goto -476 -> 422
        //   901: astore_3
        //   902: aload_3
        //   903: invokevirtual 247	java/io/FileNotFoundException:printStackTrace	()V
        //   906: goto -494 -> 412
        //   909: astore_3
        //   910: aload 5
        //   912: astore 4
        //   914: aload_3
        //   915: invokevirtual 248	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   918: goto -506 -> 412
        //   921: getstatic 251	com/itextpdf/text/PageSize:LETTER	Lcom/itextpdf/text/Rectangle;
        //   924: astore_3
        //   925: goto -433 -> 492
        //   928: getstatic 136	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   931: astore_3
        //   932: goto -440 -> 492
        //   935: getstatic 254	com/itextpdf/text/PageSize:LEGAL	Lcom/itextpdf/text/Rectangle;
        //   938: astore_3
        //   939: goto -447 -> 492
        //   942: getstatic 257	com/itextpdf/text/PageSize:A3	Lcom/itextpdf/text/Rectangle;
        //   945: astore_3
        //   946: goto -454 -> 492
        //   949: getstatic 260	com/itextpdf/text/PageSize:A5	Lcom/itextpdf/text/Rectangle;
        //   952: astore_3
        //   953: goto -461 -> 492
        //   956: new 206	com/itextpdf/text/Rectangle
        //   959: dup
        //   960: ldc_w 261
        //   963: ldc_w 262
        //   966: invokespecial 264	com/itextpdf/text/Rectangle:<init>	(FF)V
        //   969: astore_3
        //   970: goto -478 -> 492
        //   973: aload_0
        //   974: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   977: invokestatic 52	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2300	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)I
        //   980: iconst_1
        //   981: if_icmpne +28 -> 1009
        //   984: aload 9
        //   986: getstatic 270	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   989: bipush 50
        //   991: aload 10
        //   993: invokevirtual 276	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   996: pop
        //   997: aload 10
        //   999: invokevirtual 280	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   1002: invokestatic 283	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   1005: astore_3
        //   1006: goto -326 -> 680
        //   1009: aload_0
        //   1010: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   1013: invokestatic 52	com/simpleapp/tinyscanfree/Activity_EditPhoto:access$2300	(Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;)I
        //   1016: iconst_2
        //   1017: if_icmpne -337 -> 680
        //   1020: aload 9
        //   1022: getstatic 270	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   1025: iconst_5
        //   1026: aload 10
        //   1028: invokevirtual 276	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   1031: pop
        //   1032: aload 10
        //   1034: invokevirtual 280	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   1037: invokestatic 283	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   1040: astore_3
        //   1041: goto -361 -> 680
        //   1044: astore_3
        //   1045: getstatic 289	java/lang/System:err	Ljava/io/PrintStream;
        //   1048: aload_3
        //   1049: invokevirtual 292	com/itextpdf/text/DocumentException:getMessage	()Ljava/lang/String;
        //   1052: invokevirtual 297	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1055: aload_0
        //   1056: getfield 176	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1059: invokevirtual 230	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1062: ifeq -275 -> 787
        //   1065: aload_0
        //   1066: getfield 176	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1069: invokevirtual 233	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1072: ifne -285 -> 787
        //   1075: aload 5
        //   1077: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   1080: goto -293 -> 787
        //   1083: astore_3
        //   1084: getstatic 289	java/lang/System:err	Ljava/io/PrintStream;
        //   1087: aload_3
        //   1088: invokevirtual 298	java/io/IOException:getMessage	()Ljava/lang/String;
        //   1091: invokevirtual 297	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1094: aload_0
        //   1095: getfield 176	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1098: invokevirtual 230	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1101: ifeq -314 -> 787
        //   1104: aload_0
        //   1105: getfield 176	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1108: invokevirtual 233	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1111: ifne -324 -> 787
        //   1114: aload 5
        //   1116: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   1119: goto -332 -> 787
        //   1122: astore_3
        //   1123: aload_0
        //   1124: getfield 176	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1127: invokevirtual 230	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1130: ifeq +18 -> 1148
        //   1133: aload_0
        //   1134: getfield 176	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1137: invokevirtual 233	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1140: ifne +8 -> 1148
        //   1143: aload 5
        //   1145: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   1148: aload_3
        //   1149: athrow
        //   1150: astore_3
        //   1151: aload_3
        //   1152: invokevirtual 299	java/io/IOException:printStackTrace	()V
        //   1155: goto -261 -> 894
        //   1158: astore_3
        //   1159: aload_3
        //   1160: invokevirtual 248	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   1163: goto -269 -> 894
        //   1166: aload 8
        //   1168: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   1171: new 301	android/os/Message
        //   1174: dup
        //   1175: invokespecial 302	android/os/Message:<init>	()V
        //   1178: astore_3
        //   1179: aload_3
        //   1180: iconst_3
        //   1181: putfield 306	android/os/Message:what	I
        //   1184: aload_0
        //   1185: getfield 19	com/simpleapp/tinyscanfree/Activity_EditPhoto$24:this$0	Lcom/simpleapp/tinyscanfree/Activity_EditPhoto;
        //   1188: getfield 310	com/simpleapp/tinyscanfree/Activity_EditPhoto:handler	Landroid/os/Handler;
        //   1191: aload_3
        //   1192: invokevirtual 316	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   1195: pop
        //   1196: return
        //   1197: astore 5
        //   1199: aload_3
        //   1200: astore 4
        //   1202: aload 5
        //   1204: astore_3
        //   1205: goto -291 -> 914
        //   1208: astore 5
        //   1210: aload_3
        //   1211: astore 4
        //   1213: aload 5
        //   1215: astore_3
        //   1216: goto -314 -> 902
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1219	0	this	24
        //   215	683	1	i	int
        //   419	6	2	j	int
        //   53	788	3	localObject1	Object
        //   901	2	3	localFileNotFoundException1	FileNotFoundException
        //   909	6	3	localDocumentException1	com.itextpdf.text.DocumentException
        //   924	117	3	localObject2	Object
        //   1044	5	3	localDocumentException2	com.itextpdf.text.DocumentException
        //   1083	5	3	localIOException1	IOException
        //   1122	27	3	localObject3	Object
        //   1150	2	3	localIOException2	IOException
        //   1158	2	3	localDocumentException3	com.itextpdf.text.DocumentException
        //   1178	38	3	localObject4	Object
        //   198	1014	4	localObject5	Object
        //   381	763	5	localDocument1	com.itextpdf.text.Document
        //   1197	6	5	localDocumentException4	com.itextpdf.text.DocumentException
        //   1208	6	5	localFileNotFoundException2	FileNotFoundException
        //   23	624	6	localFile	File
        //   207	659	7	localArrayList	ArrayList
        //   378	789	8	localDocument2	com.itextpdf.text.Document
        //   618	403	9	localBitmap	Bitmap
        //   627	406	10	localByteArrayOutputStream	java.io.ByteArrayOutputStream
        // Exception table:
        //   from	to	target	type
        //   386	404	901	java/io/FileNotFoundException
        //   386	404	909	com/itextpdf/text/DocumentException
        //   502	562	1044	com/itextpdf/text/DocumentException
        //   564	680	1044	com/itextpdf/text/DocumentException
        //   680	712	1044	com/itextpdf/text/DocumentException
        //   712	721	1044	com/itextpdf/text/DocumentException
        //   721	762	1044	com/itextpdf/text/DocumentException
        //   973	1006	1044	com/itextpdf/text/DocumentException
        //   1009	1041	1044	com/itextpdf/text/DocumentException
        //   502	562	1083	java/io/IOException
        //   564	680	1083	java/io/IOException
        //   680	712	1083	java/io/IOException
        //   712	721	1083	java/io/IOException
        //   721	762	1083	java/io/IOException
        //   973	1006	1083	java/io/IOException
        //   1009	1041	1083	java/io/IOException
        //   502	562	1122	finally
        //   564	680	1122	finally
        //   680	712	1122	finally
        //   712	721	1122	finally
        //   721	762	1122	finally
        //   973	1006	1122	finally
        //   1009	1041	1122	finally
        //   1045	1055	1122	finally
        //   1084	1094	1122	finally
        //   787	894	1150	java/io/IOException
        //   787	894	1158	com/itextpdf/text/DocumentException
        //   404	409	1197	com/itextpdf/text/DocumentException
        //   404	409	1208	java/io/FileNotFoundException
      }
    });
    this.mThread.start();
  }
  
  public void deletePDF()
  {
    File[] arrayOfFile = new File(this.preferences.getString("folder_path", "")).listFiles(new MyFilter(".pdf"));
    if ((arrayOfFile != null) && (arrayOfFile.length > 0))
    {
      int i = 0;
      while (i < arrayOfFile.length)
      {
        arrayOfFile[i].delete();
        i += 1;
      }
    }
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((this.mlist == null) || (this.mlist.size() == 0))
    {
      Toast.makeText(this, "Please delete the erro picture!", 0).show();
      finish();
    }
    for (;;)
    {
      return super.dispatchTouchEvent(paramMotionEvent);
      Object localObject = this.mPager.findViewWithTag(this.mlist.get(this.num - 1));
      if (localObject != null)
      {
        localObject = (ImageViewTouch)((View)localObject).findViewById(2131624311);
        this.mPager.setImage((ImageViewTouch)localObject);
        this.mPager.setRotate(this.isRotate);
      }
    }
  }
  
  public void doUpdate(int paramInt)
  {
    final int i;
    if (this.mPager != null)
    {
      this.isListSelecting = true;
      this.isListSelected = true;
      i = this.mPager.getCurrentItem();
      if (i != paramInt) {}
    }
    else
    {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        if ((Activity_EditPhoto.this.mlist.get(i) != null) && (Activity_EditPhoto.this.mPager.findViewWithTag(Activity_EditPhoto.this.mlist.get(i)) != null))
        {
          Activity_EditPhoto.access$1902(Activity_EditPhoto.this, Activity_EditPhoto.this.mapp.getBitmapFromMemCache(((Photo_item)Activity_EditPhoto.this.mlist.get(i)).getPath()));
          if (Activity_EditPhoto.this.bitmap != null)
          {
            Message localMessage = new Message();
            localMessage.what = 42;
            localMessage.obj = Integer.valueOf(i);
            Activity_EditPhoto.this.handler.sendMessage(localMessage);
          }
        }
        else
        {
          return;
        }
        Activity_EditPhoto.access$1002(Activity_EditPhoto.this, BitmapTools.getPhoto2(((Photo_item)Activity_EditPhoto.this.mlist.get(i)).getPath(), 300, 400));
        if (Utils.hasHoneycomb()) {
          Activity_EditPhoto.access$1902(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mbit));
        }
        for (;;)
        {
          Activity_EditPhoto.this.mapp.addBitmapToMemoryCache(((Photo_item)Activity_EditPhoto.this.mlist.get(i)).getPath(), Activity_EditPhoto.this.bitmap);
          break;
          Activity_EditPhoto.access$1902(Activity_EditPhoto.this, new RecyclingBitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mbit));
        }
      }
    }).start();
    this.mPager.setCurrentItem(paramInt, false);
  }
  
  public BitmapDrawable getBitmapDrawable(Bitmap paramBitmap)
  {
    return new BitmapDrawable(this.context.getResources(), paramBitmap);
  }
  
  public String getCurentPath()
  {
    String str = "";
    int i = this.mPager.getCurrentItem();
    if (this.mlist.get(i) != null) {
      str = ((Photo_item)this.mlist.get(i)).getPath();
    }
    return str;
  }
  
  public String getReshowPath(int paramInt)
  {
    int i = this.mPager.getCurrentItem();
    if (i == paramInt)
    {
      if (i + 2 > this.mlist.size())
      {
        if (i == 0) {
          return ((Photo_item)this.mlist.get(i)).getPath();
        }
        return ((Photo_item)this.mlist.get(i - 1)).getPath();
      }
      return ((Photo_item)this.mlist.get(i + 1)).getPath();
    }
    return ((Photo_item)this.mlist.get(i)).getPath();
  }
  
  public void hideProgressDialog()
  {
    if ((this.progressDialog != null) && (this.progressDialog.isShowing())) {
      this.progressDialog.dismiss();
    }
  }
  
  public boolean isEmail(String paramString)
  {
    return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$").matcher(paramString).matches();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default: 
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
    label691:
    for (;;)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
      if (paramInt2 == 1)
      {
        relist();
      }
      else if (paramInt2 == 2)
      {
        finish();
        continue;
        onAuthenticated(paramInt2, paramIntent);
        continue;
        onFolderSelected(paramInt2, paramIntent);
        continue;
        if (paramInt2 == -1)
        {
          Object localObject = getRealPathFromURI(paramIntent.getData());
          this.mapp.setPhotoUri(paramIntent.getData());
          this.mapp.setPhotopath((String)localObject);
          this.mapp.setSavePath(this.preferences.getString("folder_path", "") + "/");
          this.mapp.setPhotofrom(false);
          this.editor.putBoolean("where", true);
          this.editor.commit();
          startActivity(new Intent(this.context, Activity_Detect.class));
          continue;
          if (paramInt2 == -1)
          {
            localObject = paramIntent.getData().getPath();
            this.mapp.setPhotoUri(paramIntent.getData());
            this.mapp.setPhotopath((String)localObject);
            this.mapp.setSavePath(this.preferences.getString("folder_path", "") + "/");
            this.mapp.setPhotofrom(false);
            this.editor.putBoolean("where", true);
            this.editor.commit();
            startActivity(new Intent(this.context, Activity_Detect.class));
            continue;
            if ((paramInt2 == -1) && (paramInt1 == 10001))
            {
              localObject = getSharedPreferences("TopScanner", 0).edit();
              ((SharedPreferences.Editor)localObject).putBoolean("GOOGLE_IAP", true);
              this.mapp.setIsBuyGoogleAds(true);
              this.mapp.setAdvOrChargeOrNormal(3);
              this.mapp.setIAPBuyVersion(1);
              ((SharedPreferences.Editor)localObject).commit();
              localObject = new Message();
              ((Message)localObject).what = 10020;
              this.handler.sendMessage((Message)localObject);
              thankBuy("Thank you for upgrading to pro! ");
              continue;
              if ((this.preferences.getInt("SDCARD_PATH", 0) > 0) && (this.mapp.getSdcard_list().size() > 1)) {}
              for (localObject = new File((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/SignPng/topscanner_sign.png");; localObject = new File(Environment.getExternalStorageDirectory() + "/TopScanner/SignPng/topscanner_sign.png"))
              {
                if (!((File)localObject).exists()) {
                  break label691;
                }
                localObject = new Intent(activity_EditPhoto, ImageSignActivity.class);
                ((Intent)localObject).putExtra("image_path", "" + ((Photo_item)this.mlist.get(this.num - 1)).getPath());
                startActivity((Intent)localObject);
                break;
              }
            }
          }
        }
      }
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    MyApplication.activityList.add(this);
    this.context = this;
    this.mapp = MyApplication.getApplication(this.context);
    label217:
    Object localObject1;
    Object localObject2;
    if (this.mapp.isPad())
    {
      requestWindowFeature(1);
      setContentView(2130903069);
      this.preferences = getSharedPreferences("TopScanner", 0);
      this.editor = this.preferences.edit();
      if ((this.preferences.getInt("SDCARD_PATH", 0) <= 0) || (this.mapp.getSdcard_list().size() <= 1)) {
        break label650;
      }
      this.root_Path2 = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/Documents/");
      this.compressJpeg_Path = ((String)this.mapp.getSdcard_list().get(this.preferences.getInt("SDCARD_PATH", 0)) + "/Android/data/com.studio.topscanner/files" + "/TopScanner/temporary/Jpeg/");
      activity_EditPhoto = this;
      this.inflater = LayoutInflater.from(this.context);
      this.metrics = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(this.metrics);
      this.mlist = new ArrayList();
      this.edit_photo_layout1 = ((RelativeLayout)findViewById(2131624070));
      this.mPager = ((MyViewPager)findViewById(2131624069));
      this.mPager.setOffscreenPageLimit(3);
      this.back = ((ImageView)findViewById(2131624071));
      this.back.setOnClickListener(this.mlistener2);
      this.editphto_morelist = ((ImageView)findViewById(2131624075));
      this.editphto_morelist.setOnClickListener(this.mlistener2);
      if (!this.mapp.isPad()) {
        break label711;
      }
      paramBundle = (ImageView)findViewById(2131624084);
      localObject1 = (ImageView)findViewById(2131624085);
      localObject2 = (ImageView)findViewById(2131624086);
      paramBundle.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_EditPhoto.this.showPdf_or_imagejpeg_SelectDailog(1);
        }
      });
      ((ImageView)localObject1).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_EditPhoto.this.delete_single_image();
        }
      });
      ((ImageView)localObject2).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          FlurryAgent.logEvent("6_simplefax");
          Activity_EditPhoto.access$102(Activity_EditPhoto.this, 1);
          Activity_EditPhoto.access$202(Activity_EditPhoto.this, 5);
          Activity_EditPhoto.this.shareDocment();
        }
      });
    }
    for (;;)
    {
      this.edit_photo_pb = ((ProgressBar)findViewById(2131624076));
      this.rotate = ((ImageView)findViewById(2131624072));
      this.rotate.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (Activity_EditPhoto.this.isRun) {}
          do
          {
            do
            {
              return;
              Activity_EditPhoto.access$402(Activity_EditPhoto.this, true);
              Activity_EditPhoto.this.edit_photo_pb.setVisibility(0);
              Activity_EditPhoto.this.deletePDF();
            } while (Activity_EditPhoto.this.mlist.size() <= 0);
            paramAnonymousView = Activity_EditPhoto.this.mPager.findViewWithTag(Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1));
          } while (paramAnonymousView == null);
          Activity_EditPhoto.access$802(Activity_EditPhoto.this, (ImageViewTouch)paramAnonymousView.findViewById(2131624311));
          new Thread(new Runnable()
          {
            public void run()
            {
              try
              {
                Activity_EditPhoto.access$302(Activity_EditPhoto.this, true);
                Object localObject3 = new Matrix();
                ((Matrix)localObject3).postRotate(90.0F);
                Object localObject2 = new File(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath());
                Object localObject1 = BitmapFactory.decodeStream(new FileInputStream((File)localObject2));
                localObject3 = Bitmap.createBitmap((Bitmap)localObject1, 0, 0, ((Bitmap)localObject1).getWidth(), ((Bitmap)localObject1).getHeight(), (Matrix)localObject3, true);
                localObject2 = new BufferedOutputStream(new FileOutputStream((File)localObject2));
                ((Bitmap)localObject3).compress(Bitmap.CompressFormat.JPEG, 85, (OutputStream)localObject2);
                ((OutputStream)localObject2).flush();
                ((OutputStream)localObject2).close();
                ((Bitmap)localObject1).recycle();
                ((Bitmap)localObject3).recycle();
                Activity_EditPhoto.access$902(Activity_EditPhoto.this, Activity_EditPhoto.this.getBitmapDrawable(BitmapFactory.decodeStream(new FileInputStream(new File(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath())))));
                Activity_EditPhoto.access$1002(Activity_EditPhoto.this, BitmapTools.getPhoto2(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), 300, 400));
                if (Utils.hasHoneycomb()) {
                  Activity_EditPhoto.access$1102(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mbit));
                }
                for (;;)
                {
                  Activity_EditPhoto.this.mapp.addBitmapToMemoryCache(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), Activity_EditPhoto.this.bitmap2);
                  Activity_EditPhoto.this.mapp.addBitmapToMemoryCache("photolist" + ((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), Activity_EditPhoto.this.bitDrawable);
                  localObject1 = new Message();
                  ((Message)localObject1).what = 1;
                  Activity_EditPhoto.this.handler.sendMessage((Message)localObject1);
                  return;
                  Activity_EditPhoto.access$1102(Activity_EditPhoto.this, new RecyclingBitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mbit));
                }
                return;
              }
              catch (FileNotFoundException localFileNotFoundException)
              {
                localFileNotFoundException.printStackTrace();
                return;
              }
              catch (IOException localIOException)
              {
                localIOException.printStackTrace();
              }
            }
          }).start();
        }
      });
      this.documentname = ((TextView)findViewById(2131624073));
      this.size = ((TextView)findViewById(2131624074));
      this.other = ((TextView)findViewById(2131624077));
      this.documentname.setText(this.preferences.getString("folder_name", ""));
      paramBundle = new File(this.preferences.getString("folder_path", "")).listFiles();
      if (paramBundle == null) {
        break label826;
      }
      i = 0;
      while (i < paramBundle.length)
      {
        if (paramBundle[i].getName().matches("[0-9]{18}.jpg"))
        {
          localObject1 = new Photo_item();
          ((Photo_item)localObject1).setPath(paramBundle[i].getPath());
          ((Photo_item)localObject1).setShow(false);
          this.mlist.add(localObject1);
        }
        i += 1;
      }
      setRequestedOrientation(1);
      break;
      label650:
      this.root_Path2 = (Environment.getExternalStorageDirectory().getPath() + "/TopScanner/temporary/Documents/");
      this.compressJpeg_Path = (Environment.getExternalStorageDirectory().getPath() + "/TopScanner/temporary/Jpeg/");
      break label217;
      label711:
      this.edit_photo_bottomclick_layout = ((LinearLayout)findViewById(2131624078));
      paramBundle = (LinearLayout)findViewById(2131624079);
      localObject1 = (LinearLayout)findViewById(2131624081);
      localObject2 = (LinearLayout)findViewById(2131624082);
      LinearLayout localLinearLayout = (LinearLayout)findViewById(2131624083);
      paramBundle.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_EditPhoto.this.showPdf_or_imagejpeg_SelectDailog(1);
        }
      });
      ((LinearLayout)localObject1).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_EditPhoto.this.showPdf_or_imagejpeg_SelectDailog(2);
        }
      });
      ((LinearLayout)localObject2).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Activity_EditPhoto.this.delete_single_image();
        }
      });
      localLinearLayout.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          FlurryAgent.logEvent("6_simplefax");
          Activity_EditPhoto.access$102(Activity_EditPhoto.this, 1);
          Activity_EditPhoto.access$202(Activity_EditPhoto.this, 5);
          Activity_EditPhoto.this.shareDocment();
        }
      });
    }
    label826:
    Collections.sort(this.mlist, this.comparator);
    this.other.setText(this.num + "/" + this.mlist.size());
    long l = 0L;
    int i = 0;
    while (i < this.mlist.size())
    {
      l += new File(((Photo_item)this.mlist.get(i)).getPath()).length();
      i += 1;
    }
    this.size.setText(Util.FormetFileSize1(l));
    this.adapter = new MyPageAdapter(this.context, this.mlist, this.handler);
    this.mPager.setAdapter(this.adapter);
    this.mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramAnonymousInt)
      {
        if (paramAnonymousInt == 0)
        {
          Activity_EditPhoto.access$1402(Activity_EditPhoto.this, false);
          Activity_EditPhoto.access$1502(Activity_EditPhoto.this, false);
          Activity_EditPhoto.access$1602(Activity_EditPhoto.this, new Thread(new Runnable()
          {
            public void run()
            {
              int i;
              if ((Activity_EditPhoto.this.thread != null) && (!Activity_EditPhoto.this.isScroll) && (!Activity_EditPhoto.this.thread.isInterrupted())) {
                i = Activity_EditPhoto.this.num;
              }
              try
              {
                Activity_EditPhoto.access$1702(Activity_EditPhoto.this, BitmapTools.getSize(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath()));
                Activity_EditPhoto.access$902(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mapp.decodeSampledBitmapFromFile(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), ((Integer)Activity_EditPhoto.this.mlist2.get(0)).intValue(), ((Integer)Activity_EditPhoto.this.mlist2.get(1)).intValue())));
                if (Activity_EditPhoto.this.bitmap2 == null)
                {
                  Activity_EditPhoto.access$902(Activity_EditPhoto.this, Activity_EditPhoto.this.getBitmapDrawable(BitmapFactory.decodeStream(new FileInputStream(new File(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath())))));
                  Activity_EditPhoto.this.mapp.addResuableBit(Activity_EditPhoto.this.bitmap2);
                }
                if ((Activity_EditPhoto.this.thread != null) && (!Activity_EditPhoto.this.isScroll) && (!Activity_EditPhoto.this.thread.isInterrupted()))
                {
                  Message localMessage = new Message();
                  localMessage.what = 5;
                  localMessage.obj = Integer.valueOf(i);
                  Activity_EditPhoto.this.handler.sendMessage(localMessage);
                }
                return;
              }
              catch (FileNotFoundException localFileNotFoundException)
              {
                for (;;)
                {
                  localFileNotFoundException.printStackTrace();
                }
              }
            }
          }));
          Activity_EditPhoto.this.thread.start();
        }
        do
        {
          return;
          if (paramAnonymousInt == 1)
          {
            Activity_EditPhoto.access$1402(Activity_EditPhoto.this, true);
            Activity_EditPhoto.access$1502(Activity_EditPhoto.this, false);
            new Thread(new Runnable()
            {
              public void run()
              {
                Activity_EditPhoto.access$1802(Activity_EditPhoto.this, Activity_EditPhoto.this.num);
                if (Activity_EditPhoto.this.mPager.findViewWithTag(Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.oldnum - 1)) != null)
                {
                  Activity_EditPhoto.access$1902(Activity_EditPhoto.this, Activity_EditPhoto.this.mapp.getBitmapFromMemCache(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.oldnum - 1)).getPath()));
                  if (Activity_EditPhoto.this.bitmap != null)
                  {
                    Message localMessage = new Message();
                    localMessage.what = 11;
                    Activity_EditPhoto.this.handler.sendMessage(localMessage);
                  }
                }
                else
                {
                  return;
                }
                Activity_EditPhoto.access$1002(Activity_EditPhoto.this, BitmapTools.getPhoto2(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.oldnum - 1)).getPath(), 300, 400));
                if (Utils.hasHoneycomb()) {
                  Activity_EditPhoto.access$1902(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mbit));
                }
                for (;;)
                {
                  Activity_EditPhoto.this.mapp.addBitmapToMemoryCache(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.oldnum - 1)).getPath(), Activity_EditPhoto.this.bitmap);
                  break;
                  Activity_EditPhoto.access$1902(Activity_EditPhoto.this, new RecyclingBitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mbit));
                }
              }
            }).start();
            return;
          }
        } while (paramAnonymousInt != 2);
        Activity_EditPhoto.access$1402(Activity_EditPhoto.this, true);
        Activity_EditPhoto.access$1502(Activity_EditPhoto.this, false);
      }
      
      public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2) {}
      
      public void onPageSelected(final int paramAnonymousInt)
      {
        Activity_EditPhoto.access$702(Activity_EditPhoto.this, paramAnonymousInt + 1);
        Activity_EditPhoto.this.other.setText(Activity_EditPhoto.this.num + "/" + Activity_EditPhoto.this.mlist.size());
        Activity_EditPhoto.access$2102(Activity_EditPhoto.this, false);
        int i = 0;
        if (i < Activity_EditPhoto.this.mlist.size())
        {
          if (i == paramAnonymousInt) {
            ((Photo_item)Activity_EditPhoto.this.mlist.get(i)).setSelected(true);
          }
          for (;;)
          {
            i += 1;
            break;
            ((Photo_item)Activity_EditPhoto.this.mlist.get(i)).setSelected(false);
          }
        }
        if ((Activity_EditPhoto.this.isListSelected) && (!Activity_EditPhoto.this.isListSelecting)) {
          new Thread(new Runnable()
          {
            public void run()
            {
              int i;
              if ((Activity_EditPhoto.this.thread != null) && (!Activity_EditPhoto.this.isScroll) && (!Activity_EditPhoto.this.thread.isInterrupted()) && (!Activity_EditPhoto.this.isListSelecting))
              {
                i = paramAnonymousInt;
                if ((Activity_EditPhoto.this.mlist.size() <= 0) || (Activity_EditPhoto.this.mlist.get(i) == null)) {}
              }
              try
              {
                Activity_EditPhoto.access$1702(Activity_EditPhoto.this, BitmapTools.getSize(((Photo_item)Activity_EditPhoto.this.mlist.get(i)).getPath()));
                Activity_EditPhoto.access$902(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mapp.decodeSampledBitmapFromFile(((Photo_item)Activity_EditPhoto.this.mlist.get(i)).getPath(), ((Integer)Activity_EditPhoto.this.mlist2.get(0)).intValue(), ((Integer)Activity_EditPhoto.this.mlist2.get(1)).intValue())));
                if (Activity_EditPhoto.this.bitmap2 == null)
                {
                  Activity_EditPhoto.access$902(Activity_EditPhoto.this, Activity_EditPhoto.this.getBitmapDrawable(BitmapFactory.decodeStream(new FileInputStream(new File(((Photo_item)Activity_EditPhoto.this.mlist.get(i)).getPath())))));
                  Activity_EditPhoto.this.mapp.addResuableBit(Activity_EditPhoto.this.bitmap2);
                }
                if ((Activity_EditPhoto.this.thread != null) && (!Activity_EditPhoto.this.isScroll) && (!Activity_EditPhoto.this.thread.isInterrupted()) && (!Activity_EditPhoto.this.isListSelecting))
                {
                  Message localMessage = new Message();
                  localMessage.what = 41;
                  localMessage.obj = Integer.valueOf(i);
                  Activity_EditPhoto.this.handler.sendMessage(localMessage);
                }
                return;
              }
              catch (FileNotFoundException localFileNotFoundException)
              {
                for (;;)
                {
                  localFileNotFoundException.printStackTrace();
                }
              }
            }
          }).start();
        }
      }
    });
    i = this.preferences.getInt("folder_id", 0);
    this.mPager.setCurrentItem(i, false);
    paramBundle = (RelativeLayout)findViewById(2131624067);
    if (this.preferences.getInt("newUser_1.3", -1) == 1) {
      AdsUtils.showAds(activity_EditPhoto, paramBundle, this.mapp, 3);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.edit_photo_pb.setVisibility(8);
    int i = 0;
    while (i < this.mlist.size())
    {
      if (this.mapp.getBitmapFromMemCache(((Photo_item)this.mlist.get(i)).getPath()) != null) {
        this.mapp.getmMemoryCache().remove(((Photo_item)this.mlist.get(i)).getPath());
      }
      i += 1;
    }
    i = 0;
    while (i < this.mlist.size())
    {
      if (this.mapp.getBitmapFromMemCache("photolist" + ((Photo_item)this.mlist.get(i)).getPath()) != null) {
        this.mapp.getmMemoryCache().remove("photolist" + ((Photo_item)this.mlist.get(i)).getPath());
      }
      i += 1;
    }
    this.mlist = null;
    if ((this.mbit != null) && (!this.mbit.isRecycled())) {
      this.mbit.recycle();
    }
    this.mbit = null;
    if ((this.bitDrawable != null) && (!this.bitDrawable.getBitmap().isRecycled())) {
      this.bitDrawable.getBitmap().recycle();
    }
    this.bitDrawable = null;
    if ((this.bit2 != null) && (!this.bit2.getBitmap().isRecycled())) {
      this.bit2.getBitmap().recycle();
    }
    this.bit2 = null;
    if ((this.bitmap != null) && (!this.bitmap.getBitmap().isRecycled())) {
      this.bitmap.getBitmap().recycle();
    }
    this.bitmap = null;
    if ((this.bitmap2 != null) && (this.bitmap2.getBitmap() != null) && (!this.bitmap2.getBitmap().isRecycled())) {
      this.bitmap2.getBitmap().recycle();
    }
    this.bitmap2 = null;
    if ((this.thread != null) && (this.thread.isAlive())) {
      this.thread.interrupt();
    }
    this.thread = null;
    if ((this.mThread != null) && (this.mThread.isAlive())) {
      this.mThread.interrupt();
    }
    this.mThread = null;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.isRun) {}
      for (;;)
      {
        return true;
        finish();
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    long l = 0L;
    int i = 0;
    while (i < this.mlist.size())
    {
      l += new File(((Photo_item)this.mlist.get(i)).getPath()).length();
      i += 1;
    }
    this.size.setText(Util.FormetFileSize1(l));
    this.mapp.clearData();
    if (this.mapp.isAdd())
    {
      this.adapter.notifyDataSetChanged();
      this.mPager.setCurrentItem(this.mlist.size() - 1);
      this.num = this.mlist.size();
      this.mapp.setUpdate(true);
      deletePDF();
      this.mapp.setAdd(false);
    }
    if (this.mapp.isIslist())
    {
      this.mapp.setIslist(false);
      this.mPager.setCurrentItem(this.mapp.getListitemid(), false);
    }
    this.thread = new Thread(new Runnable()
    {
      public void run()
      {
        int i;
        if ((Activity_EditPhoto.this.thread != null) && (!Activity_EditPhoto.this.isScroll) && (!Activity_EditPhoto.this.thread.isInterrupted()))
        {
          if (Activity_EditPhoto.this.num == 0) {
            Activity_EditPhoto.access$702(Activity_EditPhoto.this, 1);
          }
          i = Activity_EditPhoto.this.num;
          if ((Activity_EditPhoto.this.mlist == null) || (Activity_EditPhoto.this.mlist.size() <= 0) || (Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1) == null)) {}
        }
        try
        {
          Activity_EditPhoto.access$1702(Activity_EditPhoto.this, BitmapTools.getSize(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath()));
          Activity_EditPhoto.access$902(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mapp.decodeSampledBitmapFromFile(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), ((Integer)Activity_EditPhoto.this.mlist2.get(0)).intValue(), ((Integer)Activity_EditPhoto.this.mlist2.get(1)).intValue())));
          if (Activity_EditPhoto.this.bitmap2 == null)
          {
            Activity_EditPhoto.access$902(Activity_EditPhoto.this, Activity_EditPhoto.this.getBitmapDrawable(BitmapFactory.decodeStream(new FileInputStream(new File(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath())))));
            Activity_EditPhoto.this.mapp.addResuableBit(Activity_EditPhoto.this.bitmap2);
          }
          if ((Activity_EditPhoto.this.thread != null) && (!Activity_EditPhoto.this.isScroll) && (!Activity_EditPhoto.this.thread.isInterrupted()))
          {
            Message localMessage = new Message();
            localMessage.what = 5;
            localMessage.obj = Integer.valueOf(i);
            Activity_EditPhoto.this.handler.sendMessage(localMessage);
          }
          return;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          for (;;)
          {
            localFileNotFoundException.printStackTrace();
          }
        }
      }
    });
    this.thread.start();
  }
  
  public void relist()
  {
    for (;;)
    {
      int i;
      try
      {
        this.mlist.clear();
        this.documentname.setText(this.preferences.getString("folder_name", ""));
        localFile = new File(this.preferences.getString("folder_path", ""));
        arrayOfFile = localFile.listFiles();
        if (arrayOfFile.length >= 1) {
          break label251;
        }
        localFile.delete();
        finish();
        return;
      }
      catch (Exception localException)
      {
        File localFile;
        File[] arrayOfFile;
        Photo_item localPhoto_item;
        localException.printStackTrace();
        return;
      }
      if (i < arrayOfFile.length)
      {
        if (arrayOfFile[i].getName().matches("[0-9]{18}.jpg"))
        {
          localPhoto_item = new Photo_item();
          localPhoto_item.setPath(arrayOfFile[i].getPath());
          localPhoto_item.setShow(false);
          this.mlist.add(localPhoto_item);
        }
      }
      else
      {
        if (this.mlist.size() < 1)
        {
          localFile.delete();
          finish();
          return;
        }
        Collections.sort(this.mlist, this.comparator);
        this.adapter = new MyPageAdapter(this.context, this.mlist, this.handler);
        this.mPager.setAdapter(this.adapter);
        this.num = 1;
        this.other.setText(this.num + "/" + this.mlist.size());
        return;
        label251:
        i = 0;
        continue;
      }
      i += 1;
    }
  }
  
  protected void shareDocment()
  {
    if (this.pdf_OR_jpeg == 1) {
      if (this.export_size == 0) {
        if (this.mapp.isUpdate()) {
          createPDF();
        }
      }
    }
    Message localMessage;
    while (this.pdf_OR_jpeg != 2)
    {
      return;
      File localFile = new File(this.preferences.getString("folder_path", ""));
      Object localObject = localFile.listFiles(new MyFilter(".pdf"));
      if ((localObject != null) && (localObject.length > 0))
      {
        localObject = new File(localFile.getPath() + "/" + localFile.getName() + ".pdf");
        try
        {
          if ((Util.getFileSize((File)localObject) == 0L) || (!Util.isOpenPdf(localFile.getPath() + "/" + localFile.getName() + ".pdf")))
          {
            createPDF();
            return;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return;
        }
        localMessage = new Message();
        localMessage.what = 3;
        this.handler.sendMessage(localMessage);
        return;
      }
      createPDF();
      return;
      createPDF();
      return;
    }
    if (this.export_size == 0)
    {
      localMessage = new Message();
      localMessage.what = 33;
      this.handler.sendMessage(localMessage);
      return;
    }
    CompressImage();
  }
  
  @SuppressLint({"InflateParams"})
  protected void showPdfSzieSelectDailog(final int paramInt)
  {
    long l = getfileSizeLength();
    if (l > 1048576L)
    {
      Object localObject = this.inflater.inflate(2130903118, null);
      TextView localTextView1 = (TextView)((View)localObject).findViewById(2131624329);
      TextView localTextView2 = (TextView)((View)localObject).findViewById(2131624330);
      TextView localTextView3 = (TextView)((View)localObject).findViewById(2131624331);
      localTextView1.setText(getResources().getString(2131165465) + " (" + Util.FormetFileSize1(l) + ")");
      localTextView2.setText(getResources().getString(2131165395) + " (about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 1)) + ")");
      localTextView3.setText(getResources().getString(2131165573) + " (about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 2)) + ")");
      localObject = new AlertDialog.Builder(this.context).setView((View)localObject).create();
      localTextView1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (this.val$sizeDialog != null) {
            this.val$sizeDialog.cancel();
          }
          Activity_EditPhoto.access$2302(Activity_EditPhoto.this, 0);
          if (paramInt == 1)
          {
            Activity_EditPhoto.access$202(Activity_EditPhoto.this, 0);
            Activity_EditPhoto.access$2802(Activity_EditPhoto.this, 0);
          }
          for (;;)
          {
            Activity_EditPhoto.this.shareDocment();
            return;
            if (paramInt == 2)
            {
              Activity_EditPhoto.access$202(Activity_EditPhoto.this, 2);
              Activity_EditPhoto.access$2802(Activity_EditPhoto.this, 2);
            }
          }
        }
      });
      localTextView2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (this.val$sizeDialog != null) {
            this.val$sizeDialog.cancel();
          }
          Activity_EditPhoto.access$2302(Activity_EditPhoto.this, 1);
          if (paramInt == 1)
          {
            Activity_EditPhoto.access$202(Activity_EditPhoto.this, 0);
            Activity_EditPhoto.access$2802(Activity_EditPhoto.this, 0);
          }
          for (;;)
          {
            Activity_EditPhoto.this.shareDocment();
            return;
            if (paramInt == 2)
            {
              Activity_EditPhoto.access$202(Activity_EditPhoto.this, 2);
              Activity_EditPhoto.access$2802(Activity_EditPhoto.this, 2);
            }
          }
        }
      });
      localTextView3.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (this.val$sizeDialog != null) {
            this.val$sizeDialog.cancel();
          }
          Activity_EditPhoto.access$2302(Activity_EditPhoto.this, 2);
          if (paramInt == 1)
          {
            Activity_EditPhoto.access$202(Activity_EditPhoto.this, 0);
            Activity_EditPhoto.access$2802(Activity_EditPhoto.this, 0);
          }
          for (;;)
          {
            Activity_EditPhoto.this.shareDocment();
            return;
            if (paramInt == 2)
            {
              Activity_EditPhoto.access$202(Activity_EditPhoto.this, 2);
              Activity_EditPhoto.access$2802(Activity_EditPhoto.this, 2);
            }
          }
        }
      });
      ((AlertDialog)localObject).show();
      return;
    }
    if (paramInt == 1)
    {
      this.select_pdf_posiotion = 0;
      this.select_jpeg_posiotion = 0;
    }
    for (;;)
    {
      shareDocment();
      return;
      if (paramInt == 2)
      {
        this.select_pdf_posiotion = 2;
        this.select_jpeg_posiotion = 2;
      }
    }
  }
  
  @SuppressLint({"InflateParams"})
  protected void showPdf_or_imagejpeg_SelectDailog(final int paramInt)
  {
    long l = getfileSizeLength();
    Object localObject = this.inflater.inflate(2130903128, null);
    TextView localTextView1 = (TextView)((View)localObject).findViewById(2131624344);
    LinearLayout localLinearLayout = (LinearLayout)((View)localObject).findViewById(2131624345);
    TextView localTextView2 = (TextView)((View)localObject).findViewById(2131624346);
    localTextView1.setText(getResources().getString(2131165772) + " (" + Util.FormetFileSize1(l) + ")");
    localTextView2.setText(getResources().getString(2131165771) + " (" + Util.FormetFileSize1(l) + ")");
    localObject = new AlertDialog.Builder(this.context).setView((View)localObject).create();
    localTextView1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (this.val$sizeDialog != null) {
          this.val$sizeDialog.cancel();
        }
        Activity_EditPhoto.access$102(Activity_EditPhoto.this, 1);
        Activity_EditPhoto.this.showPdfSzieSelectDailog(paramInt);
      }
    });
    localLinearLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (this.val$sizeDialog != null) {
          this.val$sizeDialog.cancel();
        }
        Activity_EditPhoto.access$102(Activity_EditPhoto.this, 2);
        Activity_EditPhoto.this.showPdfSzieSelectDailog(paramInt);
      }
    });
    ((AlertDialog)localObject).show();
  }
  
  public void showProgressDialog(String paramString1, String paramString2, int paramInt)
  {
    if (this.progressDialog == null)
    {
      this.progressDialog = new ProgressDialog(activity_EditPhoto);
      this.progressDialog.setTitle(paramString1);
      this.progressDialog.setMessage(paramString2);
      this.progressDialog.setIndeterminate(false);
      this.progressDialog.setProgressStyle(1);
      this.progressDialog.setCancelable(false);
      this.progressDialog.show();
    }
    for (;;)
    {
      this.progressDialog.show();
      return;
      if (this.progressDialog.isShowing())
      {
        this.progressDialog.setTitle(paramString1);
        this.progressDialog.setMessage(paramString2);
        this.progressDialog.setMax(100);
        this.progressDialog.setProgress(paramInt);
      }
    }
  }
  
  public void showToast(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(Activity_EditPhoto.this.getApplicationContext(), paramString, 0).show();
      }
    });
  }
  
  public void updateDelete(String paramString)
  {
    if (this.mlist.size() == 0)
    {
      this.adapter.notifyDataSetChanged();
      deletePDF();
      new File(this.preferences.getString("folder_path", "")).delete();
      finish();
      return;
    }
    this.mapp.setUpdate(true);
    deletePDF();
    this.adapter = new MyPageAdapter(this.context, this.mlist, this.handler);
    this.mPager.setAdapter(this.adapter);
    int i = 0;
    Iterator localIterator = this.mlist.iterator();
    while (localIterator.hasNext())
    {
      Photo_item localPhoto_item = (Photo_item)localIterator.next();
      if (localPhoto_item.getPath().equals(paramString)) {
        i = this.mlist.indexOf(localPhoto_item);
      }
    }
    this.mPager.setCurrentItem(i);
    int j = 0;
    if (j < this.mlist.size())
    {
      if (j == i) {
        ((Photo_item)this.mlist.get(j)).setSelected(true);
      }
      for (;;)
      {
        j += 1;
        break;
        ((Photo_item)this.mlist.get(j)).setSelected(false);
      }
    }
    this.num = (i + 1);
    this.other.setText(this.num + "/" + this.mlist.size());
  }
  
  public void updateMove(int paramInt)
  {
    this.mapp.setUpdate(true);
    deletePDF();
    this.adapter = new MyPageAdapter(this.context, this.mlist, this.handler);
    this.mPager.setAdapter(this.adapter);
    this.mPager.setCurrentItem(paramInt);
    int i = 0;
    if (i < this.mlist.size())
    {
      if (i == paramInt) {
        ((Photo_item)this.mlist.get(i)).setSelected(true);
      }
      for (;;)
      {
        i += 1;
        break;
        ((Photo_item)this.mlist.get(i)).setSelected(false);
      }
    }
    this.num = (paramInt + 1);
    this.other.setText(this.num + "/" + this.mlist.size());
  }
  
  public class MyFilter
    implements FilenameFilter
  {
    String name;
    
    public MyFilter(String paramString)
    {
      this.name = paramString;
    }
    
    public boolean accept(File paramFile, String paramString)
    {
      return paramString.endsWith(this.name);
    }
  }
}
