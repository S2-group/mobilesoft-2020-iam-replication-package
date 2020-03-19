package com.appxy.tinyscanfree;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
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
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.print.PrintManager;
import android.support.annotation.IdRes;
import android.support.v4.content.FileProvider;
import android.support.v4.util.LruCache;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appxy.adpter.MyPageAdapter;
import com.appxy.adpter.MyPrintDocumentAdapter;
import com.appxy.adpter.MyViewPager;
import com.appxy.adpter.SharePopuList1Adapter;
import com.appxy.adpter.SharePopuList1_padAdapter;
import com.appxy.adpter.SharePopuList2Adapter;
import com.appxy.adpter.SharePopuList2_padAdapter;
import com.appxy.drawViews.DragListAdapter;
import com.appxy.drawViews.DragListView;
import com.appxy.entity.Photo_item;
import com.appxy.tools.BitmapTools;
import com.appxy.tools.IAPBuy;
import com.appxy.tools.RecyclingBitmapDrawable;
import com.appxy.tools.Util;
import com.appxy.tools.Utils;
import com.appxy.views.AnimatingRelativeLayout;
import com.appxy.views.HorizontalListView;
import com.flurry.android.FlurryAgent;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jp.co.cyberagent.android.gpuimage.extension.GPUImageWrapper;

public class Activity_EditPhoto
  extends BaseActivity
{
  private static final int RC_REQUEST = 10001;
  private static Activity_EditPhoto activity_EditPhoto;
  private MyPageAdapter adapter;
  private DragListAdapter adapter2;
  private ImageView addgallery;
  private ImageView addphoto;
  private BitmapDrawable bitmap = null;
  private BitmapDrawable bitmap2 = null;
  Comparator<Photo_item> comparator = new Comparator()
  {
    public int compare(Photo_item paramAnonymousPhoto_item1, Photo_item paramAnonymousPhoto_item2)
    {
      return paramAnonymousPhoto_item1.getPath().substring(paramAnonymousPhoto_item1.getPath().length() - 7, paramAnonymousPhoto_item1.getPath().length() - 4).compareTo(paramAnonymousPhoto_item2.getPath().substring(paramAnonymousPhoto_item2.getPath().length() - 7, paramAnonymousPhoto_item2.getPath().length() - 4));
    }
  };
  Comparator<String> comparator2 = new Comparator()
  {
    @SuppressLint({"DefaultLocale"})
    public int compare(String paramAnonymousString1, String paramAnonymousString2)
    {
      if ((paramAnonymousString1.matches("New Document\\(\\d{1,5}\\)")) && (paramAnonymousString2.matches("New Document\\(\\d{1,5}\\)"))) {
        return Integer.parseInt(paramAnonymousString1.substring(13, paramAnonymousString1.length() - 1)) - Integer.parseInt(paramAnonymousString2.substring(13, paramAnonymousString2.length() - 1));
      }
      return paramAnonymousString1.toLowerCase().compareTo(paramAnonymousString2.toLowerCase());
    }
  };
  Comparator<String> comparator3 = new Comparator()
  {
    public int compare(String paramAnonymousString1, String paramAnonymousString2)
    {
      return paramAnonymousString1.substring(paramAnonymousString1.length() - 7, paramAnonymousString1.length() - 4).compareTo(paramAnonymousString2.substring(paramAnonymousString2.length() - 7, paramAnonymousString2.length() - 4));
    }
  };
  private String compressJpeg_Path = Environment.getExternalStorageDirectory().getPath() + "/MyTinyScan/temporary/Jpeg/";
  private Context context;
  private String[] country;
  private int currentWidth = 0;
  private ImageView dragedit;
  private DragListView draglistview;
  boolean edit = false;
  private TextView edit_photo_name;
  private TextView edit_photo_pagenum;
  private AnimatingRelativeLayout edit_photo_pop;
  private SharedPreferences.Editor editor;
  private RelativeLayout editphoto_camera_relativelayout;
  private Toolbar editphoto_toolbar;
  private SharePopuList1_padAdapter exportAdapter1;
  private SharePopuList2_padAdapter exportAdapter2;
  private int export_select = 0;
  private int export_size = 0;
  private RelativeLayout gallery;
  @SuppressLint({"HandlerLeak"})
  Handler handler = new Handler()
  {
    private PrintManager printManager;
    
    @SuppressLint({"DefaultLocale", "InflateParams"})
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
            localObject1 = (ImageViewTouch)((View)localObject1).findViewById(2131755552);
            ((ImageViewTouch)localObject1).setImageDrawable(Activity_EditPhoto.this.bitmap, ((ImageViewTouch)localObject1).getDisplayMatrix(), -1.0F, -1.0F);
            continue;
            i = ((Integer)paramAnonymousMessage.obj).intValue();
            if ((i == Activity_EditPhoto.this.num - 1) && (!Activity_EditPhoto.this.isScroll) && (!Activity_EditPhoto.this.isListSelecting) && (Activity_EditPhoto.this.mlist.get(i) != null))
            {
              localObject1 = Activity_EditPhoto.this.mPager.findViewWithTag(Activity_EditPhoto.this.mlist.get(i));
              if (localObject1 != null)
              {
                localObject1 = (ImageViewTouch)((View)localObject1).findViewById(2131755552);
                ((ImageViewTouch)localObject1).setImageDrawable(Activity_EditPhoto.this.bitmap2, ((ImageViewTouch)localObject1).getDisplayMatrix(), -1.0F, -1.0F);
                continue;
                Activity_EditPhoto.this.pb.setVisibility(4);
                Activity_EditPhoto.access$1702(Activity_EditPhoto.this, false);
                Activity_EditPhoto.access$1902(Activity_EditPhoto.this, false);
                Activity_EditPhoto.this.mapp.setUpdate(true);
                Activity_EditPhoto.this.image.setImageDrawable(Activity_EditPhoto.this.bitmap2, Activity_EditPhoto.this.image.getDisplayMatrix(), -1.0F, -1.0F);
                if ((Activity_EditPhoto.this.mapp.isPad()) && (Activity_EditPhoto.this.draglistview != null))
                {
                  localObject1 = Activity_EditPhoto.this.draglistview.findViewWithTag(Integer.valueOf(Activity_EditPhoto.this.num - 1));
                  if (localObject1 != null)
                  {
                    Object localObject3 = (ImageView)((View)localObject1).findViewById(2131755558);
                    Object localObject4 = (ImageView)((View)localObject1).findViewById(2131755559);
                    localObject1 = Activity_EditPhoto.this.mapp.getBitmapFromMemCache("photolist" + ((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath());
                    if (localObject1 != null)
                    {
                      if (((BitmapDrawable)localObject1).getBitmap().getWidth() > ((BitmapDrawable)localObject1).getBitmap().getHeight())
                      {
                        ((ImageView)localObject3).setImageDrawable((Drawable)localObject1);
                        ((ImageView)localObject3).setVisibility(0);
                        ((ImageView)localObject4).setVisibility(8);
                      }
                    }
                    else
                    {
                      localObject1 = BitmapTools.getPhoto2(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), 100, 100);
                      if (Utils.hasHoneycomb()) {}
                      for (localObject1 = new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), (Bitmap)localObject1);; localObject1 = new RecyclingBitmapDrawable(Activity_EditPhoto.this.context.getResources(), (Bitmap)localObject1))
                      {
                        Activity_EditPhoto.this.mapp.addBitmapToMemoryCache("photolist" + ((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), (BitmapDrawable)localObject1);
                        break;
                      }
                    }
                    ((ImageView)localObject4).setImageDrawable((Drawable)localObject1);
                    ((ImageView)localObject4).setVisibility(0);
                    ((ImageView)localObject3).setVisibility(8);
                    continue;
                    Activity_EditPhoto.this.showToast(Activity_EditPhoto.this.getResources().getString(2131296488));
                    continue;
                    if (Activity_EditPhoto.this.oldnum == Activity_EditPhoto.this.num)
                    {
                      localObject1 = (ImageViewTouch)Activity_EditPhoto.this.mPager.findViewWithTag(Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.oldnum - 1)).findViewById(2131755552);
                      ((ImageViewTouch)localObject1).setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
                      ((ImageViewTouch)localObject1).setImageDrawable(Activity_EditPhoto.this.bitmap, ((ImageViewTouch)localObject1).getDisplayMatrix(), -1.0F, -1.0F);
                      continue;
                      if ((Activity_EditPhoto.this.progressDialog != null) && (Activity_EditPhoto.this.progressDialog.isShowing())) {
                        Activity_EditPhoto.this.progressDialog.dismiss();
                      }
                      Activity_EditPhoto.access$2202(Activity_EditPhoto.this, null);
                      Activity_EditPhoto.access$2302(Activity_EditPhoto.this, null);
                      continue;
                      if ((Activity_EditPhoto.this.progressDialog != null) && (Activity_EditPhoto.this.progressDialog.isShowing())) {
                        Activity_EditPhoto.this.progressDialog.dismiss();
                      }
                      Activity_EditPhoto.access$2202(Activity_EditPhoto.this, null);
                      Activity_EditPhoto.access$2302(Activity_EditPhoto.this, null);
                      Activity_EditPhoto.this.mapp.setUpdate(false);
                      localObject3 = new ArrayList();
                      if (Activity_EditPhoto.this.export_size == 0)
                      {
                        localObject1 = new File(Activity_EditPhoto.this.preferences.getString("folder_path", ""));
                        label1009:
                        localObject1 = ((File)localObject1).listFiles(new Activity_EditPhoto.MyFilter(Activity_EditPhoto.this, ".pdf"));
                        if (localObject1.length <= 0) {
                          continue;
                        }
                        if (Build.VERSION.SDK_INT < 24) {
                          break label1287;
                        }
                        ((ArrayList)localObject3).add(FileProvider.getUriForFile(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getPackageName() + ".fileprovider", localObject1[0]));
                      }
                      int j;
                      Object localObject5;
                      for (;;)
                      {
                        localObject4 = Activity_EditPhoto.this.getPackageManager().getInstalledApplications(0);
                        j = ((List)localObject4).size();
                        localObject5 = new Intent("android.intent.action.SEND_MULTIPLE");
                        ((Intent)localObject5).putExtra("android.intent.extra.SUBJECT", "TinyScanner");
                        ((Intent)localObject5).setType("application/pdf");
                        if (Activity_EditPhoto.this.isListView1OrListview2) {
                          break label1993;
                        }
                        switch (Activity_EditPhoto.this.export_select)
                        {
                        default: 
                          break;
                        case 0: 
                          if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() != 3) && (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() != 1)) {
                            break label1352;
                          }
                          if (!Activity_EditPhoto.this.findAndGotoApp("dropbox", (ArrayList)localObject3)) {
                            break label1303;
                          }
                          if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() != 1) {
                            break;
                          }
                          Activity_EditPhoto.access$2902(Activity_EditPhoto.this, true);
                          break;
                          localObject1 = new File(Activity_EditPhoto.this.root_Path2);
                          break label1009;
                          label1287:
                          ((ArrayList)localObject3).add(Uri.fromFile(localObject1[0]));
                        }
                      }
                      label1303:
                      new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle("Dropbox").setMessage("Please install Dropbox app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                      {
                        public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                        {
                          paramAnonymous2DialogInterface.dismiss();
                        }
                      }).create().show();
                      continue;
                      label1352:
                      Activity_EditPhoto.this.iapBuy.IAP_Buy();
                      continue;
                      if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                      {
                        if (Activity_EditPhoto.this.findAndGotoApp("evernote", (ArrayList)localObject3))
                        {
                          if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1) {
                            Activity_EditPhoto.access$2902(Activity_EditPhoto.this, true);
                          }
                        }
                        else {
                          new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle("Evernote").setMessage("Please install Evernote app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                          {
                            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                            {
                              paramAnonymous2DialogInterface.dismiss();
                            }
                          }).create().show();
                        }
                      }
                      else
                      {
                        Activity_EditPhoto.this.iapBuy.IAP_Buy();
                        continue;
                        if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                        {
                          if (Activity_EditPhoto.this.findAndGotoApp("com.google.android.apps.docs", (ArrayList)localObject3))
                          {
                            if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1) {
                              Activity_EditPhoto.access$2902(Activity_EditPhoto.this, true);
                            }
                          }
                          else {
                            new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle("Google Drive").setMessage("Please install Google Drive app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                            {
                              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                              {
                                paramAnonymous2DialogInterface.dismiss();
                              }
                            }).create().show();
                          }
                        }
                        else
                        {
                          Activity_EditPhoto.this.iapBuy.IAP_Buy();
                          continue;
                          if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                          {
                            localObject1 = null;
                            i = 0;
                            while (i < j)
                            {
                              if (((ApplicationInfo)((List)localObject4).get(i)).packageName.equals("com.box.android")) {
                                localObject1 = (ApplicationInfo)((List)localObject4).get(i);
                              }
                              i += 1;
                            }
                            if (localObject1 != null)
                            {
                              if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1) {
                                Activity_EditPhoto.access$2902(Activity_EditPhoto.this, true);
                              }
                              localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
                              ((Intent)localObject1).setComponent(new ComponentName("com.box.android", "com.box.android.activities.IntentProcessorSend"));
                              ((Intent)localObject1).setType("application/pdf");
                              ((Intent)localObject1).putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)localObject3);
                              Activity_EditPhoto.this.startActivityForResult((Intent)localObject1, 3);
                            }
                            else
                            {
                              new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle("Box").setMessage("Please install Box app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                              {
                                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                {
                                  paramAnonymous2DialogInterface.dismiss();
                                }
                              }).create().show();
                            }
                          }
                          else
                          {
                            Activity_EditPhoto.this.iapBuy.IAP_Buy();
                            continue;
                            if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                            {
                              if (Activity_EditPhoto.this.findAndGotoApp("skydrive", (ArrayList)localObject3))
                              {
                                if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1) {
                                  Activity_EditPhoto.access$2902(Activity_EditPhoto.this, true);
                                }
                              }
                              else {
                                new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle("Onedrive").setMessage("Please install Onedrive app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                                {
                                  public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                  {
                                    paramAnonymous2DialogInterface.dismiss();
                                  }
                                }).create().show();
                              }
                            }
                            else
                            {
                              Activity_EditPhoto.this.iapBuy.IAP_Buy();
                              continue;
                              label1993:
                              if (Activity_EditPhoto.this.isListView1OrListview2)
                              {
                                Object localObject6;
                                Object localObject7;
                                switch (Activity_EditPhoto.this.export_select)
                                {
                                default: 
                                  break;
                                case 0: 
                                  if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1)
                                  {
                                    Activity_EditPhoto.access$3102(Activity_EditPhoto.this, true);
                                    Activity_EditPhoto.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                                    Activity_EditPhoto.this.editor.commit();
                                  }
                                  localObject4 = new ArrayList();
                                  localObject5 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject5, 0);
                                  if (!((List)localObject5).isEmpty())
                                  {
                                    localObject5 = ((List)localObject5).iterator();
                                    while (((Iterator)localObject5).hasNext())
                                    {
                                      localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                                      localObject7 = new Intent("android.intent.action.SEND_MULTIPLE");
                                      ((Intent)localObject7).setType("application/pdf");
                                      ((Intent)localObject7).putExtra("android.intent.extra.SUBJECT", "" + localObject1[0].getName().substring(0, localObject1[0].getName().length() - 4));
                                      if ((((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject6).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject6).activityInfo.name.toLowerCase().contains("blue")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("outlook")))
                                      {
                                        ((Intent)localObject7).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                                        ((Intent)localObject7).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                                        ((List)localObject4).add(localObject7);
                                      }
                                    }
                                    if (((List)localObject4).size() > 0)
                                    {
                                      localObject1 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Export");
                                      ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
                                      Activity_EditPhoto.this.startActivityForResult((Intent)localObject1, 4);
                                    }
                                    else
                                    {
                                      Toast.makeText(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getResources().getString(2131296373), 0).show();
                                    }
                                  }
                                  break;
                                case 1: 
                                  if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                                  {
                                    if (Activity_EditPhoto.this.preferences.getString("email", "").equals(""))
                                    {
                                      localObject4 = LayoutInflater.from(Activity_EditPhoto.this.context).inflate(2130903161, null);
                                      localObject5 = (EditText)((View)localObject4).findViewById(2131755630);
                                      ((EditText)localObject5).setInputType(33);
                                      ((EditText)localObject5).setSelectAllOnFocus(true);
                                      ((EditText)localObject5).setText(Activity_EditPhoto.this.preferences.getString("email", ""));
                                      localObject1 = new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle(Activity_EditPhoto.this.getResources().getString(2131296457)).setView((View)localObject4).setPositiveButton(Activity_EditPhoto.this.getResources().getString(2131296486), new DialogInterface.OnClickListener()
                                      {
                                        public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                        {
                                          Object localObject1 = (EditText)this.val$mview.findViewById(2131755630);
                                          Object localObject2 = ((EditText)localObject1).getText().toString();
                                          if (Activity_EditPhoto.this.isEmail((String)localObject2))
                                          {
                                            Activity_EditPhoto.access$3202(Activity_EditPhoto.this, Activity_EditPhoto.this.preferences.edit());
                                            Activity_EditPhoto.this.editor.putString("email", ((EditText)localObject1).getText().toString());
                                            Activity_EditPhoto.this.editor.commit();
                                            paramAnonymous2DialogInterface.dismiss();
                                            paramAnonymous2DialogInterface = new ArrayList();
                                            localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
                                            ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", "" + this.val$file2[0].getName().substring(0, this.val$file2[0].getName().length() - 4));
                                            ((Intent)localObject1).setType("application/pdf");
                                            localObject1 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
                                            if (!((List)localObject1).isEmpty())
                                            {
                                              localObject1 = ((List)localObject1).iterator();
                                              while (((Iterator)localObject1).hasNext())
                                              {
                                                localObject2 = (ResolveInfo)((Iterator)localObject1).next();
                                                Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
                                                localIntent.setType("application/pdf");
                                                localIntent.putExtra("android.intent.extra.SUBJECT", "" + this.val$file2[0].getName().substring(0, this.val$file2[0].getName().length() - 4));
                                                if ((((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject2).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject2).activityInfo.name.toLowerCase().contains("blue")) || (((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("outlook")))
                                                {
                                                  localIntent.putExtra("android.intent.extra.STREAM", this.val$fileUris);
                                                  localIntent.putExtra("android.intent.extra.EMAIL", new String[] { Activity_EditPhoto.this.preferences.getString("email", "") });
                                                  localIntent.setPackage(((ResolveInfo)localObject2).activityInfo.packageName);
                                                  paramAnonymous2DialogInterface.add(localIntent);
                                                }
                                              }
                                              if (paramAnonymous2DialogInterface.size() > 0)
                                              {
                                                localObject1 = Intent.createChooser((Intent)paramAnonymous2DialogInterface.remove(0), "Export");
                                                ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymous2DialogInterface.toArray(new Parcelable[0]));
                                                Activity_EditPhoto.this.startActivityForResult((Intent)localObject1, 4);
                                                return;
                                              }
                                              Toast.makeText(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getResources().getString(2131296373), 0).show();
                                              return;
                                            }
                                            Toast.makeText(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getResources().getString(2131296373), 0).show();
                                            return;
                                          }
                                          paramAnonymous2DialogInterface.dismiss();
                                          new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle(Activity_EditPhoto.this.getResources().getString(2131296530)).setMessage(Activity_EditPhoto.this.getResources().getString(2131296442)).setPositiveButton(Activity_EditPhoto.this.getResources().getString(2131296464), null).create().show();
                                        }
                                      }).setNegativeButton(Activity_EditPhoto.this.getResources().getString(2131296372), new DialogInterface.OnClickListener()
                                      {
                                        public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                        {
                                          ((InputMethodManager)((Dialog)paramAnonymous2DialogInterface).getContext().getSystemService("input_method")).toggleSoftInput(0, 2);
                                          paramAnonymous2DialogInterface.dismiss();
                                        }
                                      }).create();
                                      ((AlertDialog)localObject1).show();
                                      if (!Activity_EditPhoto.this.mapp.isPad()) {
                                        new Timer().schedule(new TimerTask()
                                        {
                                          public void run()
                                          {
                                            ((InputMethodManager)this.val$mDialog.getContext().getSystemService("input_method")).toggleSoftInput(0, 2);
                                          }
                                        }, 100L);
                                      }
                                    }
                                    else
                                    {
                                      if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1)
                                      {
                                        Activity_EditPhoto.access$3102(Activity_EditPhoto.this, true);
                                        Activity_EditPhoto.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                                        Activity_EditPhoto.this.editor.commit();
                                      }
                                      localObject4 = new ArrayList();
                                      localObject5 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject5, 0);
                                      if (!((List)localObject5).isEmpty())
                                      {
                                        localObject5 = ((List)localObject5).iterator();
                                        while (((Iterator)localObject5).hasNext())
                                        {
                                          localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                                          localObject7 = new Intent("android.intent.action.SEND_MULTIPLE");
                                          ((Intent)localObject7).setType("application/pdf");
                                          ((Intent)localObject7).putExtra("android.intent.extra.SUBJECT", "" + localObject1[0].getName().substring(0, localObject1[0].getName().length() - 4));
                                          if ((((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject6).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject6).activityInfo.name.toLowerCase().contains("blue")))
                                          {
                                            ((Intent)localObject7).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                                            ((Intent)localObject7).putExtra("android.intent.extra.EMAIL", new String[] { Activity_EditPhoto.this.preferences.getString("email", "") });
                                            ((Intent)localObject7).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                                            ((List)localObject4).add(localObject7);
                                          }
                                        }
                                        if (((List)localObject4).size() > 0)
                                        {
                                          localObject1 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Export");
                                          ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
                                          Activity_EditPhoto.this.startActivityForResult((Intent)localObject1, 4);
                                        }
                                        else
                                        {
                                          Toast.makeText(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getResources().getString(2131296373), 0).show();
                                        }
                                      }
                                      else
                                      {
                                        Toast.makeText(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getResources().getString(2131296373), 0).show();
                                      }
                                    }
                                  }
                                  else {
                                    Activity_EditPhoto.this.iapBuy.IAP_Buy();
                                  }
                                  break;
                                case 2: 
                                  localObject4 = new String[1];
                                  localObject4[0] = "US";
                                  j = 0;
                                  i = 0;
                                  while (i < localObject4.length)
                                  {
                                    if (Locale.getDefault().getCountry().equals(localObject4[i])) {
                                      j = 1;
                                    }
                                    i += 1;
                                  }
                                  if (j != 0)
                                  {
                                    if ((Util.isPkgInstalled(Activity_EditPhoto.activity_EditPhoto, "com.appxy.tinyfax")) || (Util.isPkgInstalled(Activity_EditPhoto.activity_EditPhoto, "com.appxy.tinyfaxplus")))
                                    {
                                      if ((Util.isPkgInstalled(Activity_EditPhoto.activity_EditPhoto, "com.appxy.tinyfax")) && (Util.isPkgInstalled(Activity_EditPhoto.activity_EditPhoto, "com.appxy.tinyfaxplus")))
                                      {
                                        if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1)
                                        {
                                          Activity_EditPhoto.access$3102(Activity_EditPhoto.this, true);
                                          Activity_EditPhoto.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                                          Activity_EditPhoto.this.editor.commit();
                                        }
                                        localObject5 = new Intent("android.intent.action.SEND");
                                        ((Intent)localObject5).setType("application/pdf");
                                        localObject4 = new ArrayList();
                                        localObject5 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject5, 0);
                                        if (!((List)localObject5).isEmpty())
                                        {
                                          localObject5 = ((List)localObject5).iterator();
                                          while (((Iterator)localObject5).hasNext())
                                          {
                                            localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                                            localObject7 = new Intent("android.intent.action.SEND");
                                            ((Intent)localObject7).setType("application/pdf");
                                            ((Intent)localObject7).putExtra("android.intent.extra.SUBJECT", "TinyScanner_" + localObject1[0].getName().substring(0, localObject1[0].getName().length() - 4));
                                            if (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.appxy.tinyfax"))
                                            {
                                              if (((ArrayList)localObject3).size() == 1) {
                                                ((Intent)localObject7).putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject3).get(0));
                                              }
                                              for (;;)
                                              {
                                                ((Intent)localObject7).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                                                ((List)localObject4).add(localObject7);
                                                break;
                                                ((Intent)localObject7).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                                              }
                                            }
                                          }
                                          if (((List)localObject4).size() > 0)
                                          {
                                            localObject1 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Fax");
                                            ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
                                            Activity_EditPhoto.this.startActivity((Intent)localObject1);
                                          }
                                          else
                                          {
                                            Toast.makeText(Activity_EditPhoto.activity_EditPhoto, "Error: Cannot open or share created PDF report.", 0).show();
                                          }
                                        }
                                      }
                                      else if (Util.isPkgInstalled(Activity_EditPhoto.activity_EditPhoto, "com.appxy.tinyfax"))
                                      {
                                        if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1) {
                                          Activity_EditPhoto.access$2902(Activity_EditPhoto.this, true);
                                        }
                                        Util.findAndGotoApp1(Activity_EditPhoto.activity_EditPhoto, "com.appxy.tinyfax", (ArrayList)localObject3, Activity_EditPhoto.this.getfileSizeLength(), Activity_EditPhoto.this.export_size);
                                      }
                                      else
                                      {
                                        if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1) {
                                          Activity_EditPhoto.access$2902(Activity_EditPhoto.this, true);
                                        }
                                        Util.findAndGotoApp1(Activity_EditPhoto.activity_EditPhoto, "com.appxy.tinyfaxplus", (ArrayList)localObject3, Activity_EditPhoto.this.getfileSizeLength(), Activity_EditPhoto.this.export_size);
                                      }
                                    }
                                    else if (Activity_EditPhoto.this.preferences.getInt("newversion_1.2.5_faxpro", -1) == 0) {
                                      Util.showGooglePlayFax_pro(Activity_EditPhoto.activity_EditPhoto);
                                    } else {
                                      Util.showGooglePlayFax(Activity_EditPhoto.activity_EditPhoto);
                                    }
                                  }
                                  else if (Util.isPkgInstalled(Activity_EditPhoto.activity_EditPhoto, "com.appxy.tinyfaxintl"))
                                  {
                                    if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1)
                                    {
                                      Activity_EditPhoto.access$3102(Activity_EditPhoto.this, true);
                                      Activity_EditPhoto.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                                      Activity_EditPhoto.this.editor.commit();
                                    }
                                    localObject5 = new Intent("android.intent.action.SEND");
                                    ((Intent)localObject5).setType("application/pdf");
                                    localObject4 = new ArrayList();
                                    localObject5 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject5, 0);
                                    if (!((List)localObject5).isEmpty())
                                    {
                                      localObject5 = ((List)localObject5).iterator();
                                      while (((Iterator)localObject5).hasNext())
                                      {
                                        localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                                        localObject7 = new Intent("android.intent.action.SEND");
                                        ((Intent)localObject7).setType("application/pdf");
                                        ((Intent)localObject7).putExtra("android.intent.extra.SUBJECT", "TinyScanner_" + localObject1[0].getName().substring(0, localObject1[0].getName().length() - 4));
                                        if (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.appxy.tinyfaxintl"))
                                        {
                                          if (((ArrayList)localObject3).size() == 1) {
                                            ((Intent)localObject7).putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject3).get(0));
                                          }
                                          for (;;)
                                          {
                                            ((Intent)localObject7).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                                            ((List)localObject4).add(localObject7);
                                            break;
                                            ((Intent)localObject7).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                                          }
                                        }
                                      }
                                      if (((List)localObject4).size() > 0)
                                      {
                                        localObject1 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Fax");
                                        ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
                                        Activity_EditPhoto.this.startActivity((Intent)localObject1);
                                      }
                                      else
                                      {
                                        Toast.makeText(Activity_EditPhoto.activity_EditPhoto, "Error: Cannot open or share created PDF report.", 0).show();
                                      }
                                    }
                                  }
                                  else
                                  {
                                    Util.showGooglePlayFax11(Activity_EditPhoto.activity_EditPhoto);
                                  }
                                  break;
                                case 4: 
                                  if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1)
                                  {
                                    Activity_EditPhoto.access$3102(Activity_EditPhoto.this, true);
                                    Activity_EditPhoto.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                                    Activity_EditPhoto.this.editor.commit();
                                  }
                                  localObject1 = new Intent("android.intent.action.VIEW");
                                  ((Intent)localObject1).setDataAndType((Uri)((ArrayList)localObject3).get(0), "application/pdf");
                                  if (Build.VERSION.SDK_INT >= 24) {
                                    ((Intent)localObject1).setFlags(3);
                                  }
                                  for (;;)
                                  {
                                    Activity_EditPhoto.this.startActivityForResult(Intent.createChooser((Intent)localObject1, "Open in"), 4);
                                    break;
                                    ((Intent)localObject1).setFlags(67108864);
                                  }
                                case 3: 
                                  if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                                  {
                                    Activity_EditPhoto.this.editor.putInt("pdf_pages", Util.getPdfPages(localObject1[0].getPath()));
                                    Activity_EditPhoto.this.editor.putString("pdf_path", localObject1[0].getPath());
                                    Activity_EditPhoto.this.editor.putString("pdf_name", localObject1[0].getName().replace(".pdf", ""));
                                    Activity_EditPhoto.this.editor.commit();
                                    if (Build.VERSION.SDK_INT >= 19) {
                                      try
                                      {
                                        this.printManager = ((PrintManager)Activity_EditPhoto.activity_EditPhoto.getSystemService("print"));
                                        this.printManager.print(Activity_EditPhoto.this.preferences.getString("pdf_name", ""), new MyPrintDocumentAdapter(Activity_EditPhoto.this.preferences), null);
                                      }
                                      catch (Exception localException)
                                      {
                                        Toast.makeText(Activity_EditPhoto.activity_EditPhoto, "Print error!", 0).show();
                                      }
                                    }
                                  }
                                  else
                                  {
                                    Activity_EditPhoto.this.iapBuy.IAP_Buy();
                                  }
                                  break;
                                case 5: 
                                  Object localObject2;
                                  if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                                  {
                                    if (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1)
                                    {
                                      Activity_EditPhoto.access$3102(Activity_EditPhoto.this, true);
                                      Activity_EditPhoto.this.editor.putLong("click_systemdialog_time", System.currentTimeMillis());
                                      Activity_EditPhoto.this.editor.commit();
                                    }
                                    localObject5 = new Intent("android.intent.action.SEND");
                                    ((Intent)localObject5).setType("application/pdf");
                                    localObject4 = new ArrayList();
                                    localObject5 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject5, 0);
                                    if (!((List)localObject5).isEmpty())
                                    {
                                      localObject5 = ((List)localObject5).iterator();
                                      while (((Iterator)localObject5).hasNext())
                                      {
                                        localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                                        localObject7 = new Intent("android.intent.action.SEND");
                                        ((Intent)localObject7).setType("application/pdf");
                                        ((Intent)localObject7).putExtra("android.intent.extra.SUBJECT", localException[0].getName());
                                        if ((!((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("dropbox")) && (!((ResolveInfo)localObject6).activityInfo.name.toLowerCase().contains("evernote")) && (!((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.google.android.apps.docs")) && (!((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.box.android")) && (!((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("skydrive")))
                                        {
                                          if (((ArrayList)localObject3).size() > 0) {
                                            ((Intent)localObject7).putExtra("android.intent.extra.STREAM", (Parcelable)((ArrayList)localObject3).get(0));
                                          }
                                          ((Intent)localObject7).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                                          ((List)localObject4).add(localObject7);
                                        }
                                      }
                                      if (((List)localObject4).size() > 0)
                                      {
                                        localObject2 = Intent.createChooser((Intent)((List)localObject4).remove(0), "Share PDF file");
                                        ((Intent)localObject2).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject4).toArray(new Parcelable[0]));
                                        Activity_EditPhoto.this.startActivity((Intent)localObject2);
                                      }
                                      else
                                      {
                                        Toast.makeText(Activity_EditPhoto.activity_EditPhoto, "Error: Cannot open or share created PDF report.", 0).show();
                                      }
                                    }
                                  }
                                  else
                                  {
                                    Activity_EditPhoto.this.iapBuy.IAP_Buy();
                                    continue;
                                    if ((Activity_EditPhoto.this.progressDialog != null) && (Activity_EditPhoto.this.progressDialog.isShowing())) {
                                      Activity_EditPhoto.this.progressDialog.dismiss();
                                    }
                                    Activity_EditPhoto.access$2202(Activity_EditPhoto.this, null);
                                    Activity_EditPhoto.access$2302(Activity_EditPhoto.this, null);
                                    Activity_EditPhoto.this.mapp.setUpdate(false);
                                    localObject3 = new ArrayList();
                                    if (Activity_EditPhoto.this.export_size == 0)
                                    {
                                      localObject2 = new File(Activity_EditPhoto.this.preferences.getString("folder_path", ""));
                                      localObject4 = ((File)localObject2).listFiles(new Activity_EditPhoto.MyFilter(Activity_EditPhoto.this, ".jpg"));
                                      if ((localObject4 == null) || (localObject4.length <= 0)) {
                                        continue;
                                      }
                                      i = 0;
                                      label5242:
                                      if (i >= localObject4.length) {
                                        break label5345;
                                      }
                                      if (Build.VERSION.SDK_INT < 24) {
                                        break label5329;
                                      }
                                      ((ArrayList)localObject3).add(FileProvider.getUriForFile(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getPackageName() + ".fileprovider", localObject4[i]));
                                    }
                                    for (;;)
                                    {
                                      i += 1;
                                      break label5242;
                                      localObject2 = new File(Activity_EditPhoto.this.compressJpeg_Path);
                                      break;
                                      label5329:
                                      ((ArrayList)localObject3).add(Uri.fromFile(localObject4[i]));
                                    }
                                    label5345:
                                    localObject5 = Activity_EditPhoto.this.getPackageManager().getInstalledApplications(0);
                                    j = ((List)localObject5).size();
                                    localObject6 = new Intent("android.intent.action.SEND_MULTIPLE");
                                    ((Intent)localObject6).putExtra("android.intent.extra.SUBJECT", "TinyScanner");
                                    ((Intent)localObject6).setType("image/jpeg");
                                    if (!Activity_EditPhoto.this.isListView1OrListview2) {
                                      switch (Activity_EditPhoto.this.export_select)
                                      {
                                      default: 
                                        break;
                                      case 0: 
                                        if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                                        {
                                          if (Activity_EditPhoto.this.findAndGotoApp("dropbox", (ArrayList)localObject3)) {
                                            continue;
                                          }
                                          new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle("Dropbox").setMessage("Please install Dropbox app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                                          {
                                            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                            {
                                              paramAnonymous2DialogInterface.dismiss();
                                            }
                                          }).create().show();
                                          continue;
                                        }
                                        Activity_EditPhoto.this.iapBuy.IAP_Buy();
                                        break;
                                      case 1: 
                                        if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                                        {
                                          if (Activity_EditPhoto.this.findAndGotoApp("evernote", (ArrayList)localObject3)) {
                                            continue;
                                          }
                                          new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle("Evernote").setMessage("Please install Evernote app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                                          {
                                            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                            {
                                              paramAnonymous2DialogInterface.dismiss();
                                            }
                                          }).create().show();
                                          continue;
                                        }
                                        Activity_EditPhoto.this.iapBuy.IAP_Buy();
                                        break;
                                      case 2: 
                                        if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                                        {
                                          if (Activity_EditPhoto.this.findAndGotoApp("com.google.android.apps.docs", (ArrayList)localObject3)) {
                                            continue;
                                          }
                                          new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle("Google Drive").setMessage("Please install Google Drive app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                                          {
                                            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                            {
                                              paramAnonymous2DialogInterface.dismiss();
                                            }
                                          }).create().show();
                                          continue;
                                        }
                                        Activity_EditPhoto.this.iapBuy.IAP_Buy();
                                        break;
                                      case 3: 
                                        if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                                        {
                                          localObject2 = null;
                                          i = 0;
                                          while (i < j)
                                          {
                                            if (((ApplicationInfo)((List)localObject5).get(i)).packageName.equals("com.box.android")) {
                                              localObject2 = (ApplicationInfo)((List)localObject5).get(i);
                                            }
                                            i += 1;
                                          }
                                          if (localObject2 != null)
                                          {
                                            localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                                            ((Intent)localObject2).setComponent(new ComponentName("com.box.android", "com.box.android.activities.IntentProcessorSend"));
                                            ((Intent)localObject2).setType("application/pdf");
                                            ((Intent)localObject2).putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)localObject3);
                                            Activity_EditPhoto.this.startActivityForResult((Intent)localObject2, 3);
                                            continue;
                                          }
                                          new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle("Box").setMessage("Please install Box app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                                          {
                                            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                            {
                                              paramAnonymous2DialogInterface.dismiss();
                                            }
                                          }).create().show();
                                          continue;
                                        }
                                        Activity_EditPhoto.this.iapBuy.IAP_Buy();
                                        break;
                                      case 4: 
                                        if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                                        {
                                          if (Activity_EditPhoto.this.findAndGotoApp("skydrive", (ArrayList)localObject3)) {
                                            continue;
                                          }
                                          new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle("Onedrive").setMessage("Please install Onedrive app on your device first to upload the document(s).").setPositiveButton("OK", new DialogInterface.OnClickListener()
                                          {
                                            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                            {
                                              paramAnonymous2DialogInterface.dismiss();
                                            }
                                          }).create().show();
                                          continue;
                                        }
                                        Activity_EditPhoto.this.iapBuy.IAP_Buy();
                                        break;
                                      }
                                    } else if (Activity_EditPhoto.this.isListView1OrListview2) {
                                      switch (Activity_EditPhoto.this.export_select)
                                      {
                                      default: 
                                        break;
                                      case 0: 
                                        localObject2 = new ArrayList();
                                        localObject5 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject6, 0);
                                        if (!((List)localObject5).isEmpty())
                                        {
                                          localObject5 = ((List)localObject5).iterator();
                                          while (((Iterator)localObject5).hasNext())
                                          {
                                            localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                                            localObject7 = new Intent("android.intent.action.SEND_MULTIPLE");
                                            ((Intent)localObject7).setType("image/jpeg");
                                            ((Intent)localObject7).putExtra("android.intent.extra.SUBJECT", "" + localObject4[0].getName().substring(0, localObject4[0].getName().length() - 4));
                                            if ((((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject6).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject6).activityInfo.name.toLowerCase().contains("blue")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("outlook")))
                                            {
                                              ((Intent)localObject7).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                                              ((Intent)localObject7).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                                              ((List)localObject2).add(localObject7);
                                            }
                                          }
                                          if (((List)localObject2).size() > 0)
                                          {
                                            localObject3 = Intent.createChooser((Intent)((List)localObject2).remove(0), "Export");
                                            ((Intent)localObject3).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject2).toArray(new Parcelable[0]));
                                            Activity_EditPhoto.this.startActivityForResult((Intent)localObject3, 4);
                                          }
                                          else
                                          {
                                            Toast.makeText(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getResources().getString(2131296373), 0).show();
                                          }
                                        }
                                        break;
                                      case 1: 
                                        if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                                        {
                                          if (Activity_EditPhoto.this.preferences.getString("email", "").equals(""))
                                          {
                                            localObject2 = LayoutInflater.from(Activity_EditPhoto.this.context).inflate(2130903161, null);
                                            localObject5 = (EditText)((View)localObject2).findViewById(2131755630);
                                            ((EditText)localObject5).setInputType(33);
                                            ((EditText)localObject5).setSelectAllOnFocus(true);
                                            ((EditText)localObject5).setText(Activity_EditPhoto.this.preferences.getString("email", ""));
                                            localObject2 = new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle(Activity_EditPhoto.this.getResources().getString(2131296457)).setView((View)localObject2).setPositiveButton(Activity_EditPhoto.this.getResources().getString(2131296486), new DialogInterface.OnClickListener()
                                            {
                                              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                              {
                                                Object localObject1 = (EditText)this.val$mview.findViewById(2131755630);
                                                Object localObject2 = ((EditText)localObject1).getText().toString();
                                                if (Activity_EditPhoto.this.isEmail((String)localObject2))
                                                {
                                                  Activity_EditPhoto.access$3202(Activity_EditPhoto.this, Activity_EditPhoto.this.preferences.edit());
                                                  Activity_EditPhoto.this.editor.putString("email", ((EditText)localObject1).getText().toString());
                                                  Activity_EditPhoto.this.editor.commit();
                                                  paramAnonymous2DialogInterface.dismiss();
                                                  paramAnonymous2DialogInterface = new ArrayList();
                                                  localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
                                                  ((Intent)localObject1).setType("image/jpeg");
                                                  localObject1 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
                                                  if (!((List)localObject1).isEmpty())
                                                  {
                                                    localObject1 = ((List)localObject1).iterator();
                                                    while (((Iterator)localObject1).hasNext())
                                                    {
                                                      localObject2 = (ResolveInfo)((Iterator)localObject1).next();
                                                      Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
                                                      localIntent.setType("image/jpeg");
                                                      localIntent.putExtra("android.intent.extra.SUBJECT", "" + this.val$file2_jpg[0].getName().substring(0, this.val$file2_jpg[0].getName().length() - 4));
                                                      if ((((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject2).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject2).activityInfo.name.toLowerCase().contains("blue")) || (((ResolveInfo)localObject2).activityInfo.packageName.toLowerCase().contains("outlook")))
                                                      {
                                                        localIntent.putExtra("android.intent.extra.STREAM", this.val$file_imageUris);
                                                        localIntent.putExtra("android.intent.extra.EMAIL", new String[] { Activity_EditPhoto.this.preferences.getString("email", "") });
                                                        localIntent.setPackage(((ResolveInfo)localObject2).activityInfo.packageName);
                                                        paramAnonymous2DialogInterface.add(localIntent);
                                                      }
                                                    }
                                                    if (paramAnonymous2DialogInterface.size() > 0)
                                                    {
                                                      localObject1 = Intent.createChooser((Intent)paramAnonymous2DialogInterface.remove(0), "Export");
                                                      ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])paramAnonymous2DialogInterface.toArray(new Parcelable[0]));
                                                      Activity_EditPhoto.this.startActivityForResult((Intent)localObject1, 4);
                                                      return;
                                                    }
                                                    Toast.makeText(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getResources().getString(2131296373), 0).show();
                                                    return;
                                                  }
                                                  Toast.makeText(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getResources().getString(2131296373), 0).show();
                                                  return;
                                                }
                                                paramAnonymous2DialogInterface.dismiss();
                                                new AlertDialog.Builder(Activity_EditPhoto.this.context).setTitle(Activity_EditPhoto.this.getResources().getString(2131296530)).setMessage(Activity_EditPhoto.this.getResources().getString(2131296442)).setPositiveButton(Activity_EditPhoto.this.getResources().getString(2131296464), null).create().show();
                                              }
                                            }).setNegativeButton(Activity_EditPhoto.this.getResources().getString(2131296372), new DialogInterface.OnClickListener()
                                            {
                                              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                                              {
                                                ((InputMethodManager)((Dialog)paramAnonymous2DialogInterface).getContext().getSystemService("input_method")).toggleSoftInput(0, 2);
                                                paramAnonymous2DialogInterface.dismiss();
                                              }
                                            }).create();
                                            ((AlertDialog)localObject2).show();
                                            if (!Activity_EditPhoto.this.mapp.isPad()) {
                                              new Timer().schedule(new TimerTask()
                                              {
                                                public void run()
                                                {
                                                  ((InputMethodManager)this.val$mDialog.getContext().getSystemService("input_method")).toggleSoftInput(0, 2);
                                                }
                                              }, 100L);
                                            }
                                          }
                                          else
                                          {
                                            localObject2 = new ArrayList();
                                            localObject5 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject6, 0);
                                            if (!((List)localObject5).isEmpty())
                                            {
                                              localObject5 = ((List)localObject5).iterator();
                                              while (((Iterator)localObject5).hasNext())
                                              {
                                                localObject6 = (ResolveInfo)((Iterator)localObject5).next();
                                                localObject7 = new Intent("android.intent.action.SEND_MULTIPLE");
                                                ((Intent)localObject7).setType("image/jpeg");
                                                ((Intent)localObject7).putExtra("android.intent.extra.SUBJECT", "" + localObject4[0].getName().substring(0, localObject4[0].getName().length() - 4));
                                                if ((((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("mail")) || (((ResolveInfo)localObject6).activityInfo.name.toLowerCase().contains("mail")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("inbox")) || (((ResolveInfo)localObject6).activityInfo.packageName.toLowerCase().contains("com.fsck.k9")) || (((ResolveInfo)localObject6).activityInfo.name.toLowerCase().contains("blue")))
                                                {
                                                  ((Intent)localObject7).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                                                  ((Intent)localObject7).putExtra("android.intent.extra.EMAIL", new String[] { Activity_EditPhoto.this.preferences.getString("email", "") });
                                                  ((Intent)localObject7).setPackage(((ResolveInfo)localObject6).activityInfo.packageName);
                                                  ((List)localObject2).add(localObject7);
                                                }
                                              }
                                              if (((List)localObject2).size() > 0)
                                              {
                                                localObject3 = Intent.createChooser((Intent)((List)localObject2).remove(0), "Export");
                                                ((Intent)localObject3).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject2).toArray(new Parcelable[0]));
                                                Activity_EditPhoto.this.startActivityForResult((Intent)localObject3, 4);
                                              }
                                              else
                                              {
                                                Toast.makeText(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getResources().getString(2131296373), 0).show();
                                              }
                                            }
                                            else
                                            {
                                              Toast.makeText(Activity_EditPhoto.this.context, Activity_EditPhoto.this.getResources().getString(2131296373), 0).show();
                                            }
                                          }
                                        }
                                        else {
                                          Activity_EditPhoto.this.iapBuy.IAP_Buy();
                                        }
                                        break;
                                      case 2: 
                                        new Thread(new Runnable()
                                        {
                                          public void run()
                                          {
                                            Object localObject = new File(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath());
                                            File localFile = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/TinyScan");
                                            if (!localFile.exists()) {
                                              localFile.mkdirs();
                                            }
                                            localFile = new File(localFile.getPath() + "/" + ((File)localObject).getName());
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
                                        break;
                                      case 3: 
                                        if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
                                        {
                                          if (((ArrayList)localObject3).size() > 1)
                                          {
                                            localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
                                            ((Intent)localObject2).setType("image/jpeg");
                                            localObject5 = new ArrayList();
                                            localObject2 = Activity_EditPhoto.this.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
                                            if (((List)localObject2).isEmpty()) {
                                              continue;
                                            }
                                            localObject6 = ((List)localObject2).iterator();
                                            label7359:
                                            if (!((Iterator)localObject6).hasNext()) {
                                              break label7595;
                                            }
                                            localObject7 = (ResolveInfo)((Iterator)localObject6).next();
                                            if (((ArrayList)localObject3).size() <= 1) {
                                              break label7580;
                                            }
                                          }
                                          label7580:
                                          for (localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");; localObject2 = new Intent("android.intent.action.SEND"))
                                          {
                                            ((Intent)localObject2).setType("image/jpeg");
                                            ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", localObject4[0].getName());
                                            if ((((ResolveInfo)localObject7).activityInfo.packageName.toLowerCase().contains("dropbox")) || (((ResolveInfo)localObject7).activityInfo.name.toLowerCase().contains("evernote")) || (((ResolveInfo)localObject7).activityInfo.packageName.toLowerCase().contains("com.google.android.apps.docs")) || (((ResolveInfo)localObject7).activityInfo.packageName.toLowerCase().contains("com.box.android")) || (((ResolveInfo)localObject7).activityInfo.packageName.toLowerCase().contains("skydrive"))) {
                                              break label7359;
                                            }
                                            ((Intent)localObject2).putExtra("android.intent.extra.STREAM", (Serializable)localObject3);
                                            ((Intent)localObject2).setPackage(((ResolveInfo)localObject7).activityInfo.packageName);
                                            ((List)localObject5).add(localObject2);
                                            break label7359;
                                            localObject2 = new Intent("android.intent.action.SEND");
                                            break;
                                          }
                                          label7595:
                                          if (((List)localObject5).size() > 0)
                                          {
                                            localObject2 = Intent.createChooser((Intent)((List)localObject5).remove(0), "Share JPG file");
                                            ((Intent)localObject2).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject5).toArray(new Parcelable[0]));
                                            Activity_EditPhoto.this.startActivity((Intent)localObject2);
                                          }
                                          else
                                          {
                                            Toast.makeText(Activity_EditPhoto.activity_EditPhoto, "Error: Cannot open or share created JPG report.", 0).show();
                                          }
                                        }
                                        else
                                        {
                                          Activity_EditPhoto.this.iapBuy.IAP_Buy();
                                          continue;
                                          if ((((Integer)paramAnonymousMessage.obj).intValue() == Activity_EditPhoto.this.num) && (!Activity_EditPhoto.this.isScroll))
                                          {
                                            localObject2 = Activity_EditPhoto.this.mPager.findViewWithTag(Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1));
                                            if (localObject2 != null)
                                            {
                                              localObject2 = (ImageViewTouch)((View)localObject2).findViewById(2131755552);
                                              ((ImageViewTouch)localObject2).setImageDrawable(Activity_EditPhoto.this.bitmap2, ((ImageViewTouch)localObject2).getDisplayMatrix(), -1.0F, -1.0F);
                                              continue;
                                              if ((Activity_EditPhoto.this.progressDialog != null) && (Activity_EditPhoto.this.progressDialog.isShowing())) {
                                                Activity_EditPhoto.this.progressDialog.dismiss();
                                              }
                                              Activity_EditPhoto.access$2202(Activity_EditPhoto.this, null);
                                              Activity_EditPhoto.this.mapp.setUpdate(false);
                                              Activity_EditPhoto.access$1702(Activity_EditPhoto.this, false);
                                              Activity_EditPhoto.this.finish();
                                              continue;
                                              if ((Activity_EditPhoto.this.progressDialog != null) && (Activity_EditPhoto.this.progressDialog.isShowing())) {
                                                Activity_EditPhoto.this.progressDialog.dismiss();
                                              }
                                              Activity_EditPhoto.access$2202(Activity_EditPhoto.this, null);
                                              Activity_EditPhoto.access$1702(Activity_EditPhoto.this, false);
                                              Activity_EditPhoto.this.mapp.setUpdate(false);
                                              Activity_EditPhoto.this.finish();
                                            }
                                          }
                                        }
                                        break;
                                      }
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
  private boolean isDragEdit = false;
  private boolean isListSelected = false;
  private boolean isListSelecting = false;
  private boolean isListView1OrListview2 = false;
  private boolean isRotate = false;
  private boolean isRun = false;
  private boolean isScroll = false;
  private boolean isclick_cloud = false;
  private boolean isclick_systemdialog = false;
  private boolean isonkeydown = false;
  private View.OnClickListener itemsOnClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView) {}
  };
  public MyViewPager mPager;
  private Thread mThread = null;
  private MyApplication mapp;
  SelectPicPopupWindow menuWindow;
  private DisplayMetrics metrics;
  private ArrayList<Photo_item> mlist = null;
  private ArrayList<Integer> mlist2 = null;
  View.OnClickListener mlistener2 = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default: 
      case 2131755359: 
      case 2131755358: 
        do
        {
          return;
          Activity_EditPhoto.this.mapp.clearCheckeditems();
          Activity_EditPhoto.this.mapp.setSavePath(Activity_EditPhoto.this.preferences.getString("folder_path", ""));
          Activity_EditPhoto.this.mapp.setPhotofrom(false);
          Activity_EditPhoto.this.editor.putBoolean("where", true);
          Activity_EditPhoto.this.editor.commit();
          paramAnonymousView = new Intent(Activity_EditPhoto.this, LocalAlbum.class);
          Activity_EditPhoto.this.startActivity(paramAnonymousView);
          return;
        } while (Activity_EditPhoto.this.isRun);
        Activity_EditPhoto.this.mapp.setSavePath(Activity_EditPhoto.this.preferences.getString("folder_path", ""));
        Activity_EditPhoto.this.editor.putBoolean("where", true);
        Activity_EditPhoto.this.editor.commit();
        if (Activity_EditPhoto.this.mapp.isPad()) {}
        for (paramAnonymousView = new Intent(Activity_EditPhoto.this.context, Activity_PadCamera.class);; paramAnonymousView = new Intent(Activity_EditPhoto.this.context, Activity_CameraPreview.class))
        {
          Activity_EditPhoto.this.startActivity(paramAnonymousView);
          return;
        }
      }
      Activity_EditPhoto.this.shareMethod(paramAnonymousView);
    }
  };
  private int num = 1;
  private int oldnum = 0;
  private TextView other;
  private HorizontalListView pad_listview1;
  private HorizontalListView pad_listview2;
  private ArrayList<HashMap<String, Object>> padexportlist1;
  private ArrayList<HashMap<String, Object>> padexportlist2;
  private ProgressBar pb;
  private boolean pdf_or_jpg = true;
  private SharedPreferences preferences;
  private Dialog progressDialog;
  private String root_Path2 = Environment.getExternalStorageDirectory().getPath() + "/MyTinyScan_PDF/";
  private RelativeLayout rotate;
  private RelativeLayout share;
  private AlertDialog shareDialog;
  private RelativeLayout takephoto;
  private Thread thread = null;
  
  public Activity_EditPhoto() {}
  
  private void addPDF_listdata()
  {
    this.padexportlist1 = new ArrayList();
    this.padexportlist2 = new ArrayList();
    int i;
    if ((this.mapp.getAdvOrChargeOrNormal() == 3) || (this.mapp.getAdvOrChargeOrNormal() == 1))
    {
      i = 0;
      while (i < 5)
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(i));
        localHashMap.put("isPro", Boolean.valueOf(false));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        this.padexportlist1.add(localHashMap);
        i += 1;
      }
      i = 0;
      if (i < 5)
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(i));
        if (i == 3) {
          localHashMap.put("id", Integer.valueOf(6));
        }
        if (i == 4) {
          localHashMap.put("id", Integer.valueOf(3));
        }
        localHashMap.put("isPro", Boolean.valueOf(false));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        if (i == 3) {
          if (Build.VERSION.SDK_INT >= 19) {
            this.padexportlist2.add(localHashMap);
          }
        }
        for (;;)
        {
          i += 1;
          break;
          this.padexportlist2.add(localHashMap);
        }
      }
    }
    else
    {
      i = 0;
      while (i < 5)
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(i));
        localHashMap.put("isPro", Boolean.valueOf(true));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        this.padexportlist1.add(localHashMap);
        i += 1;
      }
      i = 0;
      if (i < 5)
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(i));
        if (i == 3) {
          localHashMap.put("id", Integer.valueOf(6));
        }
        if (i == 4) {
          localHashMap.put("id", Integer.valueOf(3));
        }
        if ((i == 0) || (i == 4) || (i == 2))
        {
          localHashMap.put("isPro", Boolean.valueOf(false));
          label393:
          localHashMap.put("isEnable", Boolean.valueOf(true));
          if (i != 3) {
            break label449;
          }
          if (Build.VERSION.SDK_INT >= 19) {
            this.padexportlist2.add(localHashMap);
          }
        }
        for (;;)
        {
          i += 1;
          break;
          localHashMap.put("isPro", Boolean.valueOf(true));
          break label393;
          label449:
          this.padexportlist2.add(localHashMap);
        }
      }
    }
    if (!this.mapp.getIsContainCountry()) {
      this.padexportlist2.remove(2);
    }
    if ((this.mapp.getAdvOrChargeOrNormal() == 3) || (this.mapp.getAdvOrChargeOrNormal() == 1))
    {
      localHashMap = new HashMap();
      localHashMap.put("id", Integer.valueOf(5));
      localHashMap.put("isPro", Boolean.valueOf(false));
      localHashMap.put("isEnable", Boolean.valueOf(true));
      this.padexportlist2.add(localHashMap);
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("id", Integer.valueOf(5));
    localHashMap.put("isPro", Boolean.valueOf(true));
    localHashMap.put("isEnable", Boolean.valueOf(true));
    this.padexportlist2.add(localHashMap);
  }
  
  private void addjpg_listdata()
  {
    this.padexportlist1 = new ArrayList();
    this.padexportlist2 = new ArrayList();
    int i;
    if ((this.mapp.getAdvOrChargeOrNormal() == 3) || (this.mapp.getAdvOrChargeOrNormal() == 1))
    {
      i = 0;
      while (i < 5)
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(i));
        localHashMap.put("isPro", Boolean.valueOf(false));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        this.padexportlist1.add(localHashMap);
        i += 1;
      }
      i = 0;
      if (i < 5)
      {
        if ((i == 2) || (i == 3)) {}
        for (;;)
        {
          i += 1;
          break;
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          localHashMap.put("isPro", Boolean.valueOf(false));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          this.padexportlist2.add(localHashMap);
        }
      }
    }
    else
    {
      i = 0;
      while (i < 5)
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(i));
        localHashMap.put("isPro", Boolean.valueOf(true));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        this.padexportlist1.add(localHashMap);
        i += 1;
      }
      i = 0;
      while (i < 5) {
        if ((i == 2) || (i == 3))
        {
          i += 1;
        }
        else
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          if ((i == 0) || (i == 3) || (i == 4) || (i == 2)) {
            localHashMap.put("isPro", Boolean.valueOf(false));
          }
          for (;;)
          {
            localHashMap.put("isEnable", Boolean.valueOf(true));
            this.padexportlist2.add(localHashMap);
            break;
            localHashMap.put("isPro", Boolean.valueOf(true));
          }
        }
      }
    }
    if ((this.mapp.getAdvOrChargeOrNormal() == 3) || (this.mapp.getAdvOrChargeOrNormal() == 1))
    {
      localHashMap = new HashMap();
      localHashMap.put("id", Integer.valueOf(5));
      localHashMap.put("isPro", Boolean.valueOf(false));
      localHashMap.put("isEnable", Boolean.valueOf(true));
      this.padexportlist2.add(localHashMap);
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("id", Integer.valueOf(5));
    localHashMap.put("isPro", Boolean.valueOf(true));
    localHashMap.put("isEnable", Boolean.valueOf(true));
    this.padexportlist2.add(localHashMap);
  }
  
  private void deletePageMethod()
  {
    if (this.isRun) {
      return;
    }
    new AlertDialog.Builder(this.context).setMessage(getString(2131296364)).setPositiveButton(getString(2131296464), new DialogInterface.OnClickListener()
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
        Activity_EditPhoto.access$3702(Activity_EditPhoto.this, new MyPageAdapter(Activity_EditPhoto.this.context, Activity_EditPhoto.this.mlist));
        Activity_EditPhoto.this.mPager.setAdapter(Activity_EditPhoto.this.adapter);
        Activity_EditPhoto.access$802(Activity_EditPhoto.this, Activity_EditPhoto.this.num - 1);
        if (Activity_EditPhoto.this.num == 0) {
          Activity_EditPhoto.access$802(Activity_EditPhoto.this, 1);
        }
        Activity_EditPhoto.this.mPager.setCurrentItem(Activity_EditPhoto.this.num - 1);
        Activity_EditPhoto.this.other.setText(Activity_EditPhoto.this.num + "/" + Activity_EditPhoto.this.mlist.size());
        Activity_EditPhoto.access$702(Activity_EditPhoto.this, new Thread(new Runnable()
        {
          public void run()
          {
            int i;
            if (!Activity_EditPhoto.this.thread.isInterrupted()) {
              i = Activity_EditPhoto.this.num;
            }
            try
            {
              Activity_EditPhoto.access$902(Activity_EditPhoto.this, BitmapTools.getSize(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath()));
              Activity_EditPhoto.access$1002(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mapp.decodeSampledBitmapFromFile(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), ((Integer)Activity_EditPhoto.this.mlist2.get(0)).intValue(), ((Integer)Activity_EditPhoto.this.mlist2.get(1)).intValue())));
              if (Activity_EditPhoto.this.bitmap2 == null)
              {
                Activity_EditPhoto.access$1002(Activity_EditPhoto.this, Activity_EditPhoto.this.getBitmapDrawable(BitmapFactory.decodeStream(new FileInputStream(new File(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath())))));
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
    }).setNegativeButton(getString(2131296372), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).create().show();
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
  
  private SharedPreferences getSharedPreferencesForCurrentUser()
  {
    return getSharedPreferences(this.mapp.getCurrentUser(), 0);
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
  
  private void onAuthenticated(int paramInt, Intent paramIntent)
  {
    if (-1 != paramInt) {
      Toast.makeText(this, getResources().getString(2131296445), 1).show();
    }
  }
  
  private void onFolderSelected(int paramInt, Intent paramIntent)
  {
    if (-1 != paramInt) {
      Toast.makeText(this, getResources().getString(2131296403), 1).show();
    }
  }
  
  private void rotateMethod()
  {
    if (this.isRun) {}
    View localView;
    do
    {
      do
      {
        return;
        this.isRotate = true;
        this.pb.setVisibility(0);
        deletePDF();
      } while (this.mlist.size() <= 0);
      localView = this.mPager.findViewWithTag(this.mlist.get(this.num - 1));
    } while (localView == null);
    this.image = ((ImageViewTouch)localView.findViewById(2131755552));
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Activity_EditPhoto.access$1702(Activity_EditPhoto.this, true);
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
          Activity_EditPhoto.access$1002(Activity_EditPhoto.this, Activity_EditPhoto.this.getBitmapDrawable(BitmapFactory.decodeStream(new FileInputStream(new File(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath())))));
          localObject1 = BitmapTools.getPhoto2(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), 300, 400);
          if (Utils.hasHoneycomb()) {}
          for (localObject1 = new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), (Bitmap)localObject1);; localObject1 = new RecyclingBitmapDrawable(Activity_EditPhoto.this.context.getResources(), (Bitmap)localObject1))
          {
            Activity_EditPhoto.this.mapp.addBitmapToMemoryCache(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), Activity_EditPhoto.this.bitmap2);
            Activity_EditPhoto.this.mapp.addBitmapToMemoryCache("photolist" + ((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), (BitmapDrawable)localObject1);
            localObject1 = new Message();
            ((Message)localObject1).what = 1;
            Activity_EditPhoto.this.handler.sendMessage((Message)localObject1);
            return;
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
  
  private void shareMethod(View paramView)
  {
    this.country = new String[] { "US", "CA", "GB", "JP", "FR", "DE", "AR", "BR", "IL", "IN", "PT", "IT", "HK", "AU", "AT", "BE", "DK", "GR", "IE", "LU", "NL", "NO", "RO", "ES", "ZA", "SE", "CH" };
    int i = 0;
    while (i < this.country.length)
    {
      if (Locale.getDefault().getCountry().equals(this.country[i])) {
        this.mapp.setIsContainCountry(true);
      }
      i += 1;
    }
    if (this.mapp.isPad())
    {
      sharepadAlterdialog();
      return;
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.menuWindow = new SelectPicPopupWindow(this, this.itemsOnClick, localDisplayMetrics.widthPixels);
    this.menuWindow.showAtLocation(paramView, 81, 0, 0);
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
    this.progressDialog = GPUImageWrapper.createLoadingDialog(this.context, "sf");
    this.progressDialog.show();
    this.progressDialog.setCancelable(true);
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
    this.progressDialog = GPUImageWrapper.createLoadingDialog(this.context, "sf");
    this.progressDialog.show();
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
        //   5: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   8: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   11: ldc 37
        //   13: ldc 39
        //   15: invokeinterface 45 3 0
        //   20: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   23: astore 6
        //   25: aload_0
        //   26: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   29: invokestatic 52	com/appxy/tinyscanfree/Activity_EditPhoto:access$2400	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)I
        //   32: ifne +218 -> 250
        //   35: aload 6
        //   37: new 54	com/appxy/tinyscanfree/Activity_EditPhoto$MyFilter
        //   40: dup
        //   41: aload_0
        //   42: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   45: ldc 56
        //   47: invokespecial 59	com/appxy/tinyscanfree/Activity_EditPhoto$MyFilter:<init>	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;Ljava/lang/String;)V
        //   50: invokevirtual 63	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
        //   53: astore_3
        //   54: aload_3
        //   55: arraylength
        //   56: ifle +10 -> 66
        //   59: aload_3
        //   60: iconst_0
        //   61: aaload
        //   62: invokevirtual 67	java/io/File:delete	()Z
        //   65: pop
        //   66: new 31	java/io/File
        //   69: dup
        //   70: new 69	java/lang/StringBuilder
        //   73: dup
        //   74: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   77: aload_0
        //   78: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   81: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   84: ldc 37
        //   86: ldc 39
        //   88: invokeinterface 45 3 0
        //   93: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   96: ldc 76
        //   98: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   101: aload_0
        //   102: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   105: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   108: ldc 37
        //   110: ldc 39
        //   112: invokeinterface 45 3 0
        //   117: aload_0
        //   118: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   121: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   124: ldc 37
        //   126: ldc 39
        //   128: invokeinterface 45 3 0
        //   133: ldc 76
        //   135: invokevirtual 82	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
        //   138: iconst_1
        //   139: iadd
        //   140: aload_0
        //   141: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   144: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   147: ldc 37
        //   149: ldc 39
        //   151: invokeinterface 45 3 0
        //   156: invokevirtual 86	java/lang/String:length	()I
        //   159: invokevirtual 90	java/lang/String:substring	(II)Ljava/lang/String;
        //   162: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   165: ldc 56
        //   167: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   173: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   176: astore_3
        //   177: aload_3
        //   178: invokevirtual 97	java/io/File:exists	()Z
        //   181: ifeq +8 -> 189
        //   184: aload_3
        //   185: invokevirtual 67	java/io/File:delete	()Z
        //   188: pop
        //   189: aload 6
        //   191: invokevirtual 101	java/io/File:list	()[Ljava/lang/String;
        //   194: astore 4
        //   196: new 103	java/util/ArrayList
        //   199: dup
        //   200: invokespecial 104	java/util/ArrayList:<init>	()V
        //   203: astore 7
        //   205: aload 4
        //   207: ifnull +143 -> 350
        //   210: iconst_0
        //   211: istore_1
        //   212: iload_1
        //   213: aload 4
        //   215: arraylength
        //   216: if_icmpge +134 -> 350
        //   219: aload 4
        //   221: iload_1
        //   222: aaload
        //   223: ldc 106
        //   225: invokevirtual 110	java/lang/String:matches	(Ljava/lang/String;)Z
        //   228: ifeq +15 -> 243
        //   231: aload 7
        //   233: aload 4
        //   235: iload_1
        //   236: aaload
        //   237: invokeinterface 116 2 0
        //   242: pop
        //   243: iload_1
        //   244: iconst_1
        //   245: iadd
        //   246: istore_1
        //   247: goto -35 -> 212
        //   250: new 31	java/io/File
        //   253: dup
        //   254: new 69	java/lang/StringBuilder
        //   257: dup
        //   258: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   261: aload_0
        //   262: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   265: invokestatic 120	com/appxy/tinyscanfree/Activity_EditPhoto:access$2600	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Ljava/lang/String;
        //   268: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   271: aload_0
        //   272: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   275: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   278: ldc 37
        //   280: ldc 39
        //   282: invokeinterface 45 3 0
        //   287: aload_0
        //   288: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   291: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   294: ldc 37
        //   296: ldc 39
        //   298: invokeinterface 45 3 0
        //   303: ldc 76
        //   305: invokevirtual 82	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
        //   308: iconst_1
        //   309: iadd
        //   310: aload_0
        //   311: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   314: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   317: ldc 37
        //   319: ldc 39
        //   321: invokeinterface 45 3 0
        //   326: invokevirtual 86	java/lang/String:length	()I
        //   329: invokevirtual 90	java/lang/String:substring	(II)Ljava/lang/String;
        //   332: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   335: ldc 56
        //   337: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   340: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   343: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   346: astore_3
        //   347: goto -170 -> 177
        //   350: aload 7
        //   352: aload_0
        //   353: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   356: getfield 124	com/appxy/tinyscanfree/Activity_EditPhoto:comparator3	Ljava/util/Comparator;
        //   359: invokestatic 130	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
        //   362: getstatic 136	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   365: astore 4
        //   367: new 138	com/itextpdf/text/Document
        //   370: dup
        //   371: invokespecial 139	com/itextpdf/text/Document:<init>	()V
        //   374: astore 8
        //   376: aconst_null
        //   377: astore 5
        //   379: aconst_null
        //   380: astore 4
        //   382: new 141	com/itextpdf/text/pdf/PdfCopy
        //   385: dup
        //   386: aload 8
        //   388: new 143	java/io/FileOutputStream
        //   391: dup
        //   392: aload_3
        //   393: invokespecial 146	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   396: invokespecial 149	com/itextpdf/text/pdf/PdfCopy:<init>	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)V
        //   399: astore_3
        //   400: aload 8
        //   402: invokevirtual 152	com/itextpdf/text/Document:open	()V
        //   405: aload_3
        //   406: astore 4
        //   408: aload 7
        //   410: invokeinterface 155 1 0
        //   415: istore_2
        //   416: iconst_0
        //   417: istore_1
        //   418: iload_1
        //   419: iload_2
        //   420: if_icmpge +742 -> 1162
        //   423: aload 7
        //   425: iload_1
        //   426: invokeinterface 159 2 0
        //   431: checkcast 78	java/lang/String
        //   434: bipush 14
        //   436: bipush 15
        //   438: invokevirtual 90	java/lang/String:substring	(II)Ljava/lang/String;
        //   441: invokestatic 164	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   444: tableswitch	default:+40->484, 0:+473->917, 1:+480->924, 2:+487->931, 3:+494->938, 4:+501->945, 5:+508->952
        //   484: getstatic 136	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   487: astore_3
        //   488: new 138	com/itextpdf/text/Document
        //   491: dup
        //   492: aload_3
        //   493: invokespecial 167	com/itextpdf/text/Document:<init>	(Lcom/itextpdf/text/Rectangle;)V
        //   496: astore 5
        //   498: aload_0
        //   499: aload 5
        //   501: new 143	java/io/FileOutputStream
        //   504: dup
        //   505: new 69	java/lang/StringBuilder
        //   508: dup
        //   509: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   512: aload_0
        //   513: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   516: invokestatic 120	com/appxy/tinyscanfree/Activity_EditPhoto:access$2600	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Ljava/lang/String;
        //   519: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   522: aload 7
        //   524: iload_1
        //   525: invokeinterface 159 2 0
        //   530: checkcast 78	java/lang/String
        //   533: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   536: ldc 56
        //   538: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   541: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   544: invokespecial 168	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
        //   547: invokestatic 174	com/itextpdf/text/pdf/PdfWriter:getInstance	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)Lcom/itextpdf/text/pdf/PdfWriter;
        //   550: putfield 176	com/appxy/tinyscanfree/Activity_EditPhoto$21:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   553: aload 5
        //   555: invokevirtual 152	com/itextpdf/text/Document:open	()V
        //   558: aconst_null
        //   559: astore_3
        //   560: new 178	java/io/FileInputStream
        //   563: dup
        //   564: new 31	java/io/File
        //   567: dup
        //   568: new 69	java/lang/StringBuilder
        //   571: dup
        //   572: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   575: aload 6
        //   577: invokevirtual 181	java/io/File:getPath	()Ljava/lang/String;
        //   580: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   583: ldc 76
        //   585: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   588: aload 7
        //   590: iload_1
        //   591: invokeinterface 159 2 0
        //   596: checkcast 78	java/lang/String
        //   599: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   602: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   605: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   608: invokespecial 182	java/io/FileInputStream:<init>	(Ljava/io/File;)V
        //   611: invokestatic 188	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   614: astore 9
        //   616: new 190	java/io/ByteArrayOutputStream
        //   619: dup
        //   620: invokespecial 191	java/io/ByteArrayOutputStream:<init>	()V
        //   623: astore 10
        //   625: aload_0
        //   626: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   629: invokestatic 52	com/appxy/tinyscanfree/Activity_EditPhoto:access$2400	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)I
        //   632: ifne +337 -> 969
        //   635: new 69	java/lang/StringBuilder
        //   638: dup
        //   639: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   642: aload 6
        //   644: invokevirtual 181	java/io/File:getPath	()Ljava/lang/String;
        //   647: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   650: ldc 76
        //   652: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   655: aload 7
        //   657: iload_1
        //   658: invokeinterface 159 2 0
        //   663: checkcast 78	java/lang/String
        //   666: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   669: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   672: invokestatic 196	com/itextpdf/text/Image:getInstance	(Ljava/lang/String;)Lcom/itextpdf/text/Image;
        //   675: astore_3
        //   676: aload_3
        //   677: invokevirtual 200	com/itextpdf/text/Image:getWidth	()F
        //   680: aload 5
        //   682: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   685: invokevirtual 207	com/itextpdf/text/Rectangle:getWidth	()F
        //   688: fcmpl
        //   689: ifge +19 -> 708
        //   692: aload_3
        //   693: invokevirtual 210	com/itextpdf/text/Image:getHeight	()F
        //   696: aload 5
        //   698: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   701: invokevirtual 211	com/itextpdf/text/Rectangle:getHeight	()F
        //   704: fcmpl
        //   705: iflt +12 -> 717
        //   708: aload_3
        //   709: aload 5
        //   711: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   714: invokevirtual 214	com/itextpdf/text/Image:scaleToFit	(Lcom/itextpdf/text/Rectangle;)V
        //   717: aload_3
        //   718: aload 5
        //   720: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   723: invokevirtual 207	com/itextpdf/text/Rectangle:getWidth	()F
        //   726: aload_3
        //   727: invokevirtual 217	com/itextpdf/text/Image:getScaledWidth	()F
        //   730: fsub
        //   731: fconst_2
        //   732: fdiv
        //   733: aload 5
        //   735: invokevirtual 204	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   738: invokevirtual 211	com/itextpdf/text/Rectangle:getHeight	()F
        //   741: aload_3
        //   742: invokevirtual 220	com/itextpdf/text/Image:getScaledHeight	()F
        //   745: fsub
        //   746: fconst_2
        //   747: fdiv
        //   748: invokevirtual 224	com/itextpdf/text/Image:setAbsolutePosition	(FF)V
        //   751: aload 5
        //   753: aload_3
        //   754: invokevirtual 227	com/itextpdf/text/Document:add	(Lcom/itextpdf/text/Element;)Z
        //   757: pop
        //   758: aload_0
        //   759: getfield 176	com/appxy/tinyscanfree/Activity_EditPhoto$21:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   762: invokevirtual 230	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   765: ifeq +18 -> 783
        //   768: aload_0
        //   769: getfield 176	com/appxy/tinyscanfree/Activity_EditPhoto$21:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   772: invokevirtual 233	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   775: ifne +8 -> 783
        //   778: aload 5
        //   780: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   783: new 238	com/itextpdf/text/pdf/PdfReader
        //   786: dup
        //   787: new 69	java/lang/StringBuilder
        //   790: dup
        //   791: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   794: aload_0
        //   795: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   798: invokestatic 120	com/appxy/tinyscanfree/Activity_EditPhoto:access$2600	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Ljava/lang/String;
        //   801: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   804: aload 7
        //   806: iload_1
        //   807: invokeinterface 159 2 0
        //   812: checkcast 78	java/lang/String
        //   815: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   818: ldc 56
        //   820: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   823: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   826: invokespecial 239	com/itextpdf/text/pdf/PdfReader:<init>	(Ljava/lang/String;)V
        //   829: astore_3
        //   830: aload 4
        //   832: aload_3
        //   833: invokevirtual 243	com/itextpdf/text/pdf/PdfCopy:addDocument	(Lcom/itextpdf/text/pdf/PdfReader;)V
        //   836: aload_3
        //   837: invokevirtual 244	com/itextpdf/text/pdf/PdfReader:close	()V
        //   840: new 31	java/io/File
        //   843: dup
        //   844: new 69	java/lang/StringBuilder
        //   847: dup
        //   848: invokespecial 70	java/lang/StringBuilder:<init>	()V
        //   851: aload_0
        //   852: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   855: invokestatic 120	com/appxy/tinyscanfree/Activity_EditPhoto:access$2600	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Ljava/lang/String;
        //   858: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   861: aload 7
        //   863: iload_1
        //   864: invokeinterface 159 2 0
        //   869: checkcast 78	java/lang/String
        //   872: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   875: ldc 56
        //   877: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   880: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   883: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   886: invokevirtual 67	java/io/File:delete	()Z
        //   889: pop
        //   890: iload_1
        //   891: iconst_1
        //   892: iadd
        //   893: istore_1
        //   894: goto -476 -> 418
        //   897: astore_3
        //   898: aload_3
        //   899: invokevirtual 247	java/io/FileNotFoundException:printStackTrace	()V
        //   902: goto -494 -> 408
        //   905: astore_3
        //   906: aload 5
        //   908: astore 4
        //   910: aload_3
        //   911: invokevirtual 248	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   914: goto -506 -> 408
        //   917: getstatic 251	com/itextpdf/text/PageSize:LETTER	Lcom/itextpdf/text/Rectangle;
        //   920: astore_3
        //   921: goto -433 -> 488
        //   924: getstatic 136	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   927: astore_3
        //   928: goto -440 -> 488
        //   931: getstatic 254	com/itextpdf/text/PageSize:LEGAL	Lcom/itextpdf/text/Rectangle;
        //   934: astore_3
        //   935: goto -447 -> 488
        //   938: getstatic 257	com/itextpdf/text/PageSize:A3	Lcom/itextpdf/text/Rectangle;
        //   941: astore_3
        //   942: goto -454 -> 488
        //   945: getstatic 260	com/itextpdf/text/PageSize:A5	Lcom/itextpdf/text/Rectangle;
        //   948: astore_3
        //   949: goto -461 -> 488
        //   952: new 206	com/itextpdf/text/Rectangle
        //   955: dup
        //   956: ldc_w 261
        //   959: ldc_w 262
        //   962: invokespecial 264	com/itextpdf/text/Rectangle:<init>	(FF)V
        //   965: astore_3
        //   966: goto -478 -> 488
        //   969: aload_0
        //   970: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   973: invokestatic 52	com/appxy/tinyscanfree/Activity_EditPhoto:access$2400	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)I
        //   976: iconst_1
        //   977: if_icmpne +28 -> 1005
        //   980: aload 9
        //   982: getstatic 270	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   985: bipush 50
        //   987: aload 10
        //   989: invokevirtual 276	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   992: pop
        //   993: aload 10
        //   995: invokevirtual 280	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   998: invokestatic 283	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   1001: astore_3
        //   1002: goto -326 -> 676
        //   1005: aload_0
        //   1006: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   1009: invokestatic 52	com/appxy/tinyscanfree/Activity_EditPhoto:access$2400	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)I
        //   1012: iconst_2
        //   1013: if_icmpne -337 -> 676
        //   1016: aload 9
        //   1018: getstatic 270	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
        //   1021: iconst_5
        //   1022: aload 10
        //   1024: invokevirtual 276	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   1027: pop
        //   1028: aload 10
        //   1030: invokevirtual 280	java/io/ByteArrayOutputStream:toByteArray	()[B
        //   1033: invokestatic 283	com/itextpdf/text/Image:getInstance	([B)Lcom/itextpdf/text/Image;
        //   1036: astore_3
        //   1037: goto -361 -> 676
        //   1040: astore_3
        //   1041: getstatic 289	java/lang/System:err	Ljava/io/PrintStream;
        //   1044: aload_3
        //   1045: invokevirtual 292	com/itextpdf/text/DocumentException:getMessage	()Ljava/lang/String;
        //   1048: invokevirtual 297	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1051: aload_0
        //   1052: getfield 176	com/appxy/tinyscanfree/Activity_EditPhoto$21:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1055: invokevirtual 230	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1058: ifeq -275 -> 783
        //   1061: aload_0
        //   1062: getfield 176	com/appxy/tinyscanfree/Activity_EditPhoto$21:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1065: invokevirtual 233	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1068: ifne -285 -> 783
        //   1071: aload 5
        //   1073: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   1076: goto -293 -> 783
        //   1079: astore_3
        //   1080: getstatic 289	java/lang/System:err	Ljava/io/PrintStream;
        //   1083: aload_3
        //   1084: invokevirtual 298	java/io/IOException:getMessage	()Ljava/lang/String;
        //   1087: invokevirtual 297	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   1090: aload_0
        //   1091: getfield 176	com/appxy/tinyscanfree/Activity_EditPhoto$21:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1094: invokevirtual 230	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1097: ifeq -314 -> 783
        //   1100: aload_0
        //   1101: getfield 176	com/appxy/tinyscanfree/Activity_EditPhoto$21:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1104: invokevirtual 233	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1107: ifne -324 -> 783
        //   1110: aload 5
        //   1112: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   1115: goto -332 -> 783
        //   1118: astore_3
        //   1119: aload_0
        //   1120: getfield 176	com/appxy/tinyscanfree/Activity_EditPhoto$21:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1123: invokevirtual 230	com/itextpdf/text/pdf/PdfWriter:getPageNumber	()I
        //   1126: ifeq +18 -> 1144
        //   1129: aload_0
        //   1130: getfield 176	com/appxy/tinyscanfree/Activity_EditPhoto$21:writer	Lcom/itextpdf/text/pdf/PdfWriter;
        //   1133: invokevirtual 233	com/itextpdf/text/pdf/PdfWriter:isPageEmpty	()Z
        //   1136: ifne +8 -> 1144
        //   1139: aload 5
        //   1141: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   1144: aload_3
        //   1145: athrow
        //   1146: astore_3
        //   1147: aload_3
        //   1148: invokevirtual 299	java/io/IOException:printStackTrace	()V
        //   1151: goto -261 -> 890
        //   1154: astore_3
        //   1155: aload_3
        //   1156: invokevirtual 248	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   1159: goto -269 -> 890
        //   1162: aload 8
        //   1164: invokevirtual 236	com/itextpdf/text/Document:close	()V
        //   1167: new 301	android/os/Message
        //   1170: dup
        //   1171: invokespecial 302	android/os/Message:<init>	()V
        //   1174: astore_3
        //   1175: aload_3
        //   1176: iconst_3
        //   1177: putfield 306	android/os/Message:what	I
        //   1180: aload_0
        //   1181: getfield 19	com/appxy/tinyscanfree/Activity_EditPhoto$21:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   1184: getfield 310	com/appxy/tinyscanfree/Activity_EditPhoto:handler	Landroid/os/Handler;
        //   1187: aload_3
        //   1188: invokevirtual 316	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   1191: pop
        //   1192: return
        //   1193: astore 5
        //   1195: aload_3
        //   1196: astore 4
        //   1198: aload 5
        //   1200: astore_3
        //   1201: goto -291 -> 910
        //   1204: astore 5
        //   1206: aload_3
        //   1207: astore 4
        //   1209: aload 5
        //   1211: astore_3
        //   1212: goto -314 -> 898
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1215	0	this	21
        //   211	683	1	i	int
        //   415	6	2	j	int
        //   53	784	3	localObject1	Object
        //   897	2	3	localFileNotFoundException1	FileNotFoundException
        //   905	6	3	localDocumentException1	com.itextpdf.text.DocumentException
        //   920	117	3	localObject2	Object
        //   1040	5	3	localDocumentException2	com.itextpdf.text.DocumentException
        //   1079	5	3	localIOException1	IOException
        //   1118	27	3	localObject3	Object
        //   1146	2	3	localIOException2	IOException
        //   1154	2	3	localDocumentException3	com.itextpdf.text.DocumentException
        //   1174	38	3	localObject4	Object
        //   194	1014	4	localObject5	Object
        //   377	763	5	localDocument1	com.itextpdf.text.Document
        //   1193	6	5	localDocumentException4	com.itextpdf.text.DocumentException
        //   1204	6	5	localFileNotFoundException2	FileNotFoundException
        //   23	620	6	localFile	File
        //   203	659	7	localArrayList	ArrayList
        //   374	789	8	localDocument2	com.itextpdf.text.Document
        //   614	403	9	localBitmap	Bitmap
        //   623	406	10	localByteArrayOutputStream	java.io.ByteArrayOutputStream
        // Exception table:
        //   from	to	target	type
        //   382	400	897	java/io/FileNotFoundException
        //   382	400	905	com/itextpdf/text/DocumentException
        //   498	558	1040	com/itextpdf/text/DocumentException
        //   560	676	1040	com/itextpdf/text/DocumentException
        //   676	708	1040	com/itextpdf/text/DocumentException
        //   708	717	1040	com/itextpdf/text/DocumentException
        //   717	758	1040	com/itextpdf/text/DocumentException
        //   969	1002	1040	com/itextpdf/text/DocumentException
        //   1005	1037	1040	com/itextpdf/text/DocumentException
        //   498	558	1079	java/io/IOException
        //   560	676	1079	java/io/IOException
        //   676	708	1079	java/io/IOException
        //   708	717	1079	java/io/IOException
        //   717	758	1079	java/io/IOException
        //   969	1002	1079	java/io/IOException
        //   1005	1037	1079	java/io/IOException
        //   498	558	1118	finally
        //   560	676	1118	finally
        //   676	708	1118	finally
        //   708	717	1118	finally
        //   717	758	1118	finally
        //   969	1002	1118	finally
        //   1005	1037	1118	finally
        //   1041	1051	1118	finally
        //   1080	1090	1118	finally
        //   783	890	1146	java/io/IOException
        //   783	890	1154	com/itextpdf/text/DocumentException
        //   400	405	1193	com/itextpdf/text/DocumentException
        //   400	405	1204	java/io/FileNotFoundException
      }
    });
    this.mThread.start();
  }
  
  public void createPDF2()
  {
    this.mapp.clearMemCache();
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
    if ((this.progressDialog != null) && (this.progressDialog.isShowing()))
    {
      this.progressDialog.dismiss();
      this.progressDialog = null;
    }
    this.progressDialog = GPUImageWrapper.createLoadingDialog(this.context, "sf");
    this.progressDialog.show();
    this.mThread = new Thread(new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: new 31	java/io/File
        //   3: dup
        //   4: aload_0
        //   5: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   8: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   11: ldc 37
        //   13: ldc 39
        //   15: invokeinterface 45 3 0
        //   20: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   23: astore 6
        //   25: aload 6
        //   27: new 50	com/appxy/tinyscanfree/Activity_EditPhoto$MyFilter
        //   30: dup
        //   31: aload_0
        //   32: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   35: ldc 52
        //   37: invokespecial 55	com/appxy/tinyscanfree/Activity_EditPhoto$MyFilter:<init>	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;Ljava/lang/String;)V
        //   40: invokevirtual 59	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
        //   43: astore_3
        //   44: aload_3
        //   45: ifnull +15 -> 60
        //   48: aload_3
        //   49: arraylength
        //   50: ifle +10 -> 60
        //   53: aload_3
        //   54: iconst_0
        //   55: aaload
        //   56: invokevirtual 63	java/io/File:delete	()Z
        //   59: pop
        //   60: new 31	java/io/File
        //   63: dup
        //   64: new 65	java/lang/StringBuilder
        //   67: dup
        //   68: invokespecial 66	java/lang/StringBuilder:<init>	()V
        //   71: aload_0
        //   72: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   75: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   78: ldc 37
        //   80: ldc 39
        //   82: invokeinterface 45 3 0
        //   87: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   90: ldc 72
        //   92: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   95: aload_0
        //   96: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   99: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   102: ldc 37
        //   104: ldc 39
        //   106: invokeinterface 45 3 0
        //   111: aload_0
        //   112: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   115: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   118: ldc 37
        //   120: ldc 39
        //   122: invokeinterface 45 3 0
        //   127: ldc 72
        //   129: invokevirtual 78	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
        //   132: iconst_1
        //   133: iadd
        //   134: aload_0
        //   135: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   138: invokestatic 35	com/appxy/tinyscanfree/Activity_EditPhoto:access$2500	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Landroid/content/SharedPreferences;
        //   141: ldc 37
        //   143: ldc 39
        //   145: invokeinterface 45 3 0
        //   150: invokevirtual 82	java/lang/String:length	()I
        //   153: invokevirtual 86	java/lang/String:substring	(II)Ljava/lang/String;
        //   156: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: ldc 52
        //   161: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   164: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   167: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   170: astore_3
        //   171: aload_3
        //   172: invokevirtual 93	java/io/File:exists	()Z
        //   175: ifeq +8 -> 183
        //   178: aload_3
        //   179: invokevirtual 63	java/io/File:delete	()Z
        //   182: pop
        //   183: aload 6
        //   185: invokevirtual 97	java/io/File:list	()[Ljava/lang/String;
        //   188: astore 4
        //   190: new 99	java/util/ArrayList
        //   193: dup
        //   194: invokespecial 100	java/util/ArrayList:<init>	()V
        //   197: astore 7
        //   199: aload 4
        //   201: ifnull +43 -> 244
        //   204: iconst_0
        //   205: istore_1
        //   206: iload_1
        //   207: aload 4
        //   209: arraylength
        //   210: if_icmpge +34 -> 244
        //   213: aload 4
        //   215: iload_1
        //   216: aaload
        //   217: ldc 102
        //   219: invokevirtual 106	java/lang/String:matches	(Ljava/lang/String;)Z
        //   222: ifeq +15 -> 237
        //   225: aload 7
        //   227: aload 4
        //   229: iload_1
        //   230: aaload
        //   231: invokeinterface 112 2 0
        //   236: pop
        //   237: iload_1
        //   238: iconst_1
        //   239: iadd
        //   240: istore_1
        //   241: goto -35 -> 206
        //   244: aload 7
        //   246: aload_0
        //   247: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   250: getfield 116	com/appxy/tinyscanfree/Activity_EditPhoto:comparator3	Ljava/util/Comparator;
        //   253: invokestatic 122	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
        //   256: getstatic 128	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   259: astore 4
        //   261: new 130	com/itextpdf/text/Document
        //   264: dup
        //   265: invokespecial 131	com/itextpdf/text/Document:<init>	()V
        //   268: astore 8
        //   270: aconst_null
        //   271: astore 5
        //   273: aconst_null
        //   274: astore 4
        //   276: new 133	com/itextpdf/text/pdf/PdfCopy
        //   279: dup
        //   280: aload 8
        //   282: new 135	java/io/FileOutputStream
        //   285: dup
        //   286: aload_3
        //   287: invokespecial 138	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   290: invokespecial 141	com/itextpdf/text/pdf/PdfCopy:<init>	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)V
        //   293: astore_3
        //   294: aload 8
        //   296: invokevirtual 144	com/itextpdf/text/Document:open	()V
        //   299: aload_3
        //   300: astore 4
        //   302: aload 7
        //   304: invokeinterface 147 1 0
        //   309: istore_2
        //   310: iconst_0
        //   311: istore_1
        //   312: iload_1
        //   313: iload_2
        //   314: if_icmpge +518 -> 832
        //   317: aload 7
        //   319: iload_1
        //   320: invokeinterface 151 2 0
        //   325: checkcast 74	java/lang/String
        //   328: bipush 14
        //   330: bipush 15
        //   332: invokevirtual 86	java/lang/String:substring	(II)Ljava/lang/String;
        //   335: invokestatic 156	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   338: tableswitch	default:+38->376, 0:+379->717, 1:+386->724, 2:+393->731, 3:+400->738, 4:+407->745, 5:+414->752
        //   376: getstatic 128	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   379: astore_3
        //   380: new 130	com/itextpdf/text/Document
        //   383: dup
        //   384: aload_3
        //   385: invokespecial 159	com/itextpdf/text/Document:<init>	(Lcom/itextpdf/text/Rectangle;)V
        //   388: astore_3
        //   389: aload_3
        //   390: new 135	java/io/FileOutputStream
        //   393: dup
        //   394: new 65	java/lang/StringBuilder
        //   397: dup
        //   398: invokespecial 66	java/lang/StringBuilder:<init>	()V
        //   401: aload_0
        //   402: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   405: invokestatic 163	com/appxy/tinyscanfree/Activity_EditPhoto:access$2600	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Ljava/lang/String;
        //   408: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   411: aload 7
        //   413: iload_1
        //   414: invokeinterface 151 2 0
        //   419: checkcast 74	java/lang/String
        //   422: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   425: ldc 52
        //   427: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   430: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   433: invokespecial 164	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
        //   436: invokestatic 170	com/itextpdf/text/pdf/PdfWriter:getInstance	(Lcom/itextpdf/text/Document;Ljava/io/OutputStream;)Lcom/itextpdf/text/pdf/PdfWriter;
        //   439: pop
        //   440: aload_3
        //   441: invokevirtual 144	com/itextpdf/text/Document:open	()V
        //   444: new 65	java/lang/StringBuilder
        //   447: dup
        //   448: invokespecial 66	java/lang/StringBuilder:<init>	()V
        //   451: aload 6
        //   453: invokevirtual 173	java/io/File:getPath	()Ljava/lang/String;
        //   456: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   459: ldc 72
        //   461: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   464: aload 7
        //   466: iload_1
        //   467: invokeinterface 151 2 0
        //   472: checkcast 74	java/lang/String
        //   475: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   478: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   481: invokestatic 178	com/itextpdf/text/Image:getInstance	(Ljava/lang/String;)Lcom/itextpdf/text/Image;
        //   484: astore 5
        //   486: aload 5
        //   488: invokevirtual 182	com/itextpdf/text/Image:getWidth	()F
        //   491: aload_3
        //   492: invokevirtual 186	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   495: invokevirtual 189	com/itextpdf/text/Rectangle:getWidth	()F
        //   498: fcmpl
        //   499: ifge +19 -> 518
        //   502: aload 5
        //   504: invokevirtual 192	com/itextpdf/text/Image:getHeight	()F
        //   507: aload_3
        //   508: invokevirtual 186	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   511: invokevirtual 193	com/itextpdf/text/Rectangle:getHeight	()F
        //   514: fcmpl
        //   515: iflt +12 -> 527
        //   518: aload 5
        //   520: aload_3
        //   521: invokevirtual 186	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   524: invokevirtual 196	com/itextpdf/text/Image:scaleToFit	(Lcom/itextpdf/text/Rectangle;)V
        //   527: aload 5
        //   529: aload_3
        //   530: invokevirtual 186	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   533: invokevirtual 189	com/itextpdf/text/Rectangle:getWidth	()F
        //   536: aload 5
        //   538: invokevirtual 199	com/itextpdf/text/Image:getScaledWidth	()F
        //   541: fsub
        //   542: fconst_2
        //   543: fdiv
        //   544: aload_3
        //   545: invokevirtual 186	com/itextpdf/text/Document:getPageSize	()Lcom/itextpdf/text/Rectangle;
        //   548: invokevirtual 193	com/itextpdf/text/Rectangle:getHeight	()F
        //   551: aload 5
        //   553: invokevirtual 202	com/itextpdf/text/Image:getScaledHeight	()F
        //   556: fsub
        //   557: fconst_2
        //   558: fdiv
        //   559: invokevirtual 206	com/itextpdf/text/Image:setAbsolutePosition	(FF)V
        //   562: aload_3
        //   563: aload 5
        //   565: invokevirtual 209	com/itextpdf/text/Document:add	(Lcom/itextpdf/text/Element;)Z
        //   568: pop
        //   569: aload_3
        //   570: invokevirtual 212	com/itextpdf/text/Document:close	()V
        //   573: new 214	com/itextpdf/text/pdf/PdfReader
        //   576: dup
        //   577: new 65	java/lang/StringBuilder
        //   580: dup
        //   581: invokespecial 66	java/lang/StringBuilder:<init>	()V
        //   584: aload_0
        //   585: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   588: invokestatic 163	com/appxy/tinyscanfree/Activity_EditPhoto:access$2600	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Ljava/lang/String;
        //   591: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   594: aload 7
        //   596: iload_1
        //   597: invokeinterface 151 2 0
        //   602: checkcast 74	java/lang/String
        //   605: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   608: ldc 52
        //   610: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   613: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   616: invokespecial 215	com/itextpdf/text/pdf/PdfReader:<init>	(Ljava/lang/String;)V
        //   619: astore_3
        //   620: aload 4
        //   622: aload_3
        //   623: invokevirtual 219	com/itextpdf/text/pdf/PdfCopy:addDocument	(Lcom/itextpdf/text/pdf/PdfReader;)V
        //   626: aload_3
        //   627: invokevirtual 220	com/itextpdf/text/pdf/PdfReader:close	()V
        //   630: new 31	java/io/File
        //   633: dup
        //   634: new 65	java/lang/StringBuilder
        //   637: dup
        //   638: invokespecial 66	java/lang/StringBuilder:<init>	()V
        //   641: aload_0
        //   642: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   645: invokestatic 163	com/appxy/tinyscanfree/Activity_EditPhoto:access$2600	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Ljava/lang/String;
        //   648: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   651: aload 7
        //   653: iload_1
        //   654: invokeinterface 151 2 0
        //   659: checkcast 74	java/lang/String
        //   662: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   665: ldc 52
        //   667: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   670: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   673: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
        //   676: invokevirtual 63	java/io/File:delete	()Z
        //   679: pop
        //   680: iload_1
        //   681: iconst_1
        //   682: iadd
        //   683: istore_1
        //   684: goto -372 -> 312
        //   687: astore 4
        //   689: aload 4
        //   691: invokevirtual 223	java/lang/Exception:printStackTrace	()V
        //   694: goto -438 -> 256
        //   697: astore_3
        //   698: aload_3
        //   699: invokevirtual 224	java/io/FileNotFoundException:printStackTrace	()V
        //   702: goto -400 -> 302
        //   705: astore_3
        //   706: aload 5
        //   708: astore 4
        //   710: aload_3
        //   711: invokevirtual 225	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   714: goto -412 -> 302
        //   717: getstatic 228	com/itextpdf/text/PageSize:LETTER	Lcom/itextpdf/text/Rectangle;
        //   720: astore_3
        //   721: goto -341 -> 380
        //   724: getstatic 128	com/itextpdf/text/PageSize:A4	Lcom/itextpdf/text/Rectangle;
        //   727: astore_3
        //   728: goto -348 -> 380
        //   731: getstatic 231	com/itextpdf/text/PageSize:LEGAL	Lcom/itextpdf/text/Rectangle;
        //   734: astore_3
        //   735: goto -355 -> 380
        //   738: getstatic 234	com/itextpdf/text/PageSize:A3	Lcom/itextpdf/text/Rectangle;
        //   741: astore_3
        //   742: goto -362 -> 380
        //   745: getstatic 237	com/itextpdf/text/PageSize:A5	Lcom/itextpdf/text/Rectangle;
        //   748: astore_3
        //   749: goto -369 -> 380
        //   752: new 188	com/itextpdf/text/Rectangle
        //   755: dup
        //   756: ldc -18
        //   758: ldc -17
        //   760: invokespecial 241	com/itextpdf/text/Rectangle:<init>	(FF)V
        //   763: astore_3
        //   764: goto -384 -> 380
        //   767: astore 5
        //   769: getstatic 247	java/lang/System:err	Ljava/io/PrintStream;
        //   772: aload 5
        //   774: invokevirtual 250	com/itextpdf/text/DocumentException:getMessage	()Ljava/lang/String;
        //   777: invokevirtual 255	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   780: aload_3
        //   781: invokevirtual 212	com/itextpdf/text/Document:close	()V
        //   784: goto -211 -> 573
        //   787: astore 5
        //   789: getstatic 247	java/lang/System:err	Ljava/io/PrintStream;
        //   792: aload 5
        //   794: invokevirtual 256	java/io/IOException:getMessage	()Ljava/lang/String;
        //   797: invokevirtual 255	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   800: aload_3
        //   801: invokevirtual 212	com/itextpdf/text/Document:close	()V
        //   804: goto -231 -> 573
        //   807: astore 4
        //   809: aload_3
        //   810: invokevirtual 212	com/itextpdf/text/Document:close	()V
        //   813: aload 4
        //   815: athrow
        //   816: astore_3
        //   817: aload_3
        //   818: invokevirtual 257	java/io/IOException:printStackTrace	()V
        //   821: goto -141 -> 680
        //   824: astore_3
        //   825: aload_3
        //   826: invokevirtual 225	com/itextpdf/text/DocumentException:printStackTrace	()V
        //   829: goto -149 -> 680
        //   832: aload 8
        //   834: invokevirtual 212	com/itextpdf/text/Document:close	()V
        //   837: aload_0
        //   838: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   841: invokestatic 261	com/appxy/tinyscanfree/Activity_EditPhoto:access$4800	(Lcom/appxy/tinyscanfree/Activity_EditPhoto;)Z
        //   844: ifeq +38 -> 882
        //   847: new 263	android/os/Message
        //   850: dup
        //   851: invokespecial 264	android/os/Message:<init>	()V
        //   854: astore_3
        //   855: aload_3
        //   856: bipush 6
        //   858: putfield 268	android/os/Message:what	I
        //   861: aload_0
        //   862: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   865: getfield 272	com/appxy/tinyscanfree/Activity_EditPhoto:handler	Landroid/os/Handler;
        //   868: aload_3
        //   869: invokevirtual 278	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   872: pop
        //   873: return
        //   874: astore_3
        //   875: aload_3
        //   876: invokevirtual 223	java/lang/Exception:printStackTrace	()V
        //   879: goto -42 -> 837
        //   882: new 263	android/os/Message
        //   885: dup
        //   886: invokespecial 264	android/os/Message:<init>	()V
        //   889: astore_3
        //   890: aload_3
        //   891: bipush 8
        //   893: putfield 268	android/os/Message:what	I
        //   896: aload_0
        //   897: getfield 17	com/appxy/tinyscanfree/Activity_EditPhoto$22:this$0	Lcom/appxy/tinyscanfree/Activity_EditPhoto;
        //   900: getfield 272	com/appxy/tinyscanfree/Activity_EditPhoto:handler	Landroid/os/Handler;
        //   903: aload_3
        //   904: invokevirtual 278	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
        //   907: pop
        //   908: return
        //   909: astore 5
        //   911: aload_3
        //   912: astore 4
        //   914: aload 5
        //   916: astore_3
        //   917: goto -207 -> 710
        //   920: astore 5
        //   922: aload_3
        //   923: astore 4
        //   925: aload 5
        //   927: astore_3
        //   928: goto -230 -> 698
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	931	0	this	22
        //   205	479	1	i	int
        //   309	6	2	j	int
        //   43	584	3	localObject1	Object
        //   697	2	3	localFileNotFoundException1	FileNotFoundException
        //   705	6	3	localDocumentException1	com.itextpdf.text.DocumentException
        //   720	90	3	localRectangle	com.itextpdf.text.Rectangle
        //   816	2	3	localIOException1	IOException
        //   824	2	3	localDocumentException2	com.itextpdf.text.DocumentException
        //   854	15	3	localMessage	Message
        //   874	2	3	localException1	Exception
        //   889	39	3	localObject2	Object
        //   188	433	4	localObject3	Object
        //   687	3	4	localException2	Exception
        //   708	1	4	localObject4	Object
        //   807	7	4	localObject5	Object
        //   912	12	4	localObject6	Object
        //   271	436	5	localImage	com.itextpdf.text.Image
        //   767	6	5	localDocumentException3	com.itextpdf.text.DocumentException
        //   787	6	5	localIOException2	IOException
        //   909	6	5	localDocumentException4	com.itextpdf.text.DocumentException
        //   920	6	5	localFileNotFoundException2	FileNotFoundException
        //   23	429	6	localFile	File
        //   197	455	7	localArrayList	ArrayList
        //   268	565	8	localDocument	com.itextpdf.text.Document
        // Exception table:
        //   from	to	target	type
        //   244	256	687	java/lang/Exception
        //   276	294	697	java/io/FileNotFoundException
        //   276	294	705	com/itextpdf/text/DocumentException
        //   389	518	767	com/itextpdf/text/DocumentException
        //   518	527	767	com/itextpdf/text/DocumentException
        //   527	569	767	com/itextpdf/text/DocumentException
        //   389	518	787	java/io/IOException
        //   518	527	787	java/io/IOException
        //   527	569	787	java/io/IOException
        //   389	518	807	finally
        //   518	527	807	finally
        //   527	569	807	finally
        //   769	780	807	finally
        //   789	800	807	finally
        //   573	680	816	java/io/IOException
        //   573	680	824	com/itextpdf/text/DocumentException
        //   832	837	874	java/lang/Exception
        //   294	299	909	com/itextpdf/text/DocumentException
        //   294	299	920	java/io/FileNotFoundException
      }
    });
    this.mThread.start();
  }
  
  protected void createPDFHorizontalListview(ArrayList<HashMap<String, Object>> paramArrayList, int paramInt)
  {
    boolean bool = ((Boolean)((HashMap)paramArrayList.get(paramInt)).get("isEnable")).booleanValue();
    int i = paramInt % 6;
    if (bool)
    {
      if ((this.shareDialog != null) && (this.shareDialog.isShowing())) {
        this.shareDialog.dismiss();
      }
      if ((this.menuWindow != null) && (this.menuWindow.isShowing())) {
        this.menuWindow.dismiss();
      }
      paramInt = i;
      if (this.isListView1OrListview2)
      {
        paramInt = i;
        if (!this.mapp.getIsContainCountry())
        {
          if (i != 2) {
            break label169;
          }
          paramInt = 3;
        }
      }
      i = paramInt;
      if (this.mapp.isPad())
      {
        i = paramInt;
        if (this.mapp.getDensityDpi() == 213)
        {
          if (paramInt != 2) {
            break label202;
          }
          i = 3;
        }
      }
    }
    for (;;)
    {
      this.export_select = i;
      if (this.export_size != 0) {
        break label422;
      }
      if (!this.mapp.isUpdate()) {
        break label235;
      }
      createPDF();
      return;
      label169:
      if (i == 3)
      {
        paramInt = 4;
        break;
      }
      if (i == 4)
      {
        paramInt = 5;
        break;
      }
      paramInt = i;
      if (i != 5) {
        break;
      }
      paramInt = 6;
      break;
      label202:
      if (paramInt == 3)
      {
        i = 4;
      }
      else if (paramInt == 4)
      {
        i = 5;
      }
      else
      {
        i = paramInt;
        if (paramInt == 5) {
          i = 6;
        }
      }
    }
    label235:
    paramArrayList = new File(this.preferences.getString("folder_path", ""));
    Object localObject = paramArrayList.listFiles(new MyFilter(".pdf"));
    if ((localObject != null) && (localObject.length > 0))
    {
      localObject = new File(paramArrayList.getPath() + "/" + paramArrayList.getName() + ".pdf");
      try
      {
        if ((Util.getFileSize((File)localObject) == 0L) || (!Util.isOpenPdf(paramArrayList.getPath() + "/" + paramArrayList.getName() + ".pdf")))
        {
          createPDF();
          return;
        }
      }
      catch (Exception paramArrayList)
      {
        paramArrayList.printStackTrace();
        return;
      }
      paramArrayList = new Message();
      paramArrayList.what = 3;
      this.handler.sendMessage(paramArrayList);
      return;
    }
    createPDF();
    return;
    label422:
    createPDF();
  }
  
  protected void createPDFHorizontalListview_jpg(ArrayList<HashMap<String, Object>> paramArrayList, int paramInt)
  {
    if (((Boolean)((HashMap)paramArrayList.get(paramInt)).get("isEnable")).booleanValue())
    {
      if ((this.shareDialog != null) && (this.shareDialog.isShowing())) {
        this.shareDialog.dismiss();
      }
      if ((this.menuWindow != null) && (this.menuWindow.isShowing())) {
        this.menuWindow.dismiss();
      }
      this.export_select = (paramInt % 6);
      if (this.export_size == 0)
      {
        paramArrayList = new Message();
        paramArrayList.what = 33;
        this.handler.sendMessage(paramArrayList);
      }
    }
    else
    {
      return;
    }
    CompressImage();
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
  
  public int dip2px(float paramFloat)
  {
    return (int)(paramFloat * getResources().getDisplayMetrics().density + 0.5F);
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
        localObject = (ImageViewTouch)((View)localObject).findViewById(2131755552);
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
          Activity_EditPhoto.access$1402(Activity_EditPhoto.this, Activity_EditPhoto.this.mapp.getBitmapFromMemCache(((Photo_item)Activity_EditPhoto.this.mlist.get(i)).getPath()));
          if (Activity_EditPhoto.this.bitmap != null)
          {
            localObject = new Message();
            ((Message)localObject).what = 42;
            ((Message)localObject).obj = Integer.valueOf(i);
            Activity_EditPhoto.this.handler.sendMessage((Message)localObject);
          }
        }
        else
        {
          return;
        }
        Object localObject = BitmapTools.getPhoto2(((Photo_item)Activity_EditPhoto.this.mlist.get(i)).getPath(), 300, 400);
        if (Utils.hasHoneycomb()) {
          Activity_EditPhoto.access$1402(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), (Bitmap)localObject));
        }
        for (;;)
        {
          Activity_EditPhoto.this.mapp.addBitmapToMemoryCache(((Photo_item)Activity_EditPhoto.this.mlist.get(i)).getPath(), Activity_EditPhoto.this.bitmap);
          break;
          Activity_EditPhoto.access$1402(Activity_EditPhoto.this, new RecyclingBitmapDrawable(Activity_EditPhoto.this.context.getResources(), (Bitmap)localObject));
        }
      }
    }).start();
    this.mPager.setCurrentItem(paramInt, false);
  }
  
  public void edit()
  {
    Intent localIntent = new Intent(this.context, Activity_ListPhotos.class);
    localIntent.putExtra("folder_name", this.preferences.getString("folder_name", ""));
    localIntent.putExtra("photo_path", this.preferences.getString("folder_path", ""));
    localIntent.putExtra("folder_root_path", this.preferences.getString("folder_root_path", ""));
    localIntent.putExtra("mlist", this.mlist);
    localIntent.putExtra("isBatch", true);
    startActivityForResult(localIntent, 0);
  }
  
  @SuppressLint({"DefaultLocale"})
  public boolean findAndGotoApp(String paramString, ArrayList<Uri> paramArrayList)
  {
    Object localObject = new Intent("android.intent.action.SEND_MULTIPLE");
    ((Intent)localObject).putExtra("android.intent.extra.SUBJECT", "TinyScan");
    ((Intent)localObject).setType("application/pdf");
    ArrayList localArrayList = new ArrayList();
    localObject = getPackageManager().queryIntentActivities((Intent)localObject, 0);
    if (!((List)localObject).isEmpty())
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
        Intent localIntent = new Intent("android.intent.action.SEND_MULTIPLE");
        localIntent.setType("application/pdf");
        localIntent.putExtra("android.intent.extra.SUBJECT", "TinyScan");
        if ((localResolveInfo.activityInfo.packageName.toLowerCase().contains(paramString)) || (localResolveInfo.activityInfo.name.toLowerCase().contains(paramString)))
        {
          localIntent.putExtra("android.intent.extra.STREAM", paramArrayList);
          localIntent.setPackage(localResolveInfo.activityInfo.packageName);
          localArrayList.add(localIntent);
        }
      }
      if (localArrayList.size() > 0)
      {
        paramString = Intent.createChooser((Intent)localArrayList.remove(0), "Export");
        paramString.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])localArrayList.toArray(new Parcelable[0]));
        startActivityForResult(paramString, 4);
        return true;
      }
      return false;
    }
    return false;
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
    do
    {
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
            localObject = getRealPathFromURI(paramIntent.getData());
            this.mapp.setPhotoUri(paramIntent.getData());
            this.mapp.setPhotopath((String)localObject);
            this.mapp.setSavePath(this.preferences.getString("folder_path", "") + "/");
            this.mapp.setPhotofrom(false);
            this.editor.putBoolean("where", true);
            this.editor.commit();
            startActivity(new Intent(this.context, Activity_Detect.class));
          }
        }
      }
    } while ((paramInt2 != -1) || (paramInt1 != 10001));
    Object localObject = activity_EditPhoto.getSharedPreferences("TinyScanPro", 0);
    if ((((SharedPreferences)localObject).getInt("newversion_1.2.7_first", -1) == 1) && (!"".equals(Util.FileName())) && (Util.FileName().length() > 11)) {
      FlurryAgent.logEvent("8_D" + ((new Date().getTime() - Util.strToDate(Util.FileName().substring(1, 11).toString()).getTime()) / 24L * 60L * 60L * 1000L + 1L));
    }
    int j;
    if (!((SharedPreferences)localObject).getString("newversion_firsttime", "").equals(""))
    {
      j = (int)((new Date().getTime() - Util.stringToDate1(((SharedPreferences)localObject).getString("newversion_firsttime", "")).getTime()) / 86400000L);
      if (j > 50) {
        FlurryAgent.logEvent("A_IAP_50");
      }
    }
    else
    {
      label441:
      if (this.mapp.getAdvOrChargeOrNormal() != 1) {
        break label589;
      }
      FlurryAgent.logEvent("7_UserAds_IAP");
    }
    for (;;)
    {
      localObject = getSharedPreferences("msp", 0).edit();
      ((SharedPreferences.Editor)localObject).putBoolean("GOOGLE_IAP", true);
      this.mapp.setIsBuyGoogleAds(true);
      this.mapp.setAdvOrChargeOrNormal(3);
      ((SharedPreferences.Editor)localObject).commit();
      localObject = new Message();
      ((Message)localObject).what = 10020;
      this.handler.sendMessage((Message)localObject);
      thankBuy("Thank you for upgrading to pro! ");
      break;
      int i = 0;
      while (i < j)
      {
        FlurryAgent.logEvent("A_IAP_" + i);
        i += 1;
      }
      break label441;
      label589:
      if (this.mapp.getAdvOrChargeOrNormal() == 2) {
        FlurryAgent.logEvent("7_UserDoc_IAP");
      }
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (this.mapp.isPad())
    {
      if (paramConfiguration.orientation != 1) {
        break label173;
      }
      paramConfiguration = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(paramConfiguration);
      if ((this.shareDialog != null) && (this.shareDialog.isShowing()))
      {
        localLayoutParams = this.shareDialog.getWindow().getAttributes();
        localLayoutParams.width = ((int)(paramConfiguration.widthPixels * 0.9D));
        i = localLayoutParams.width - dip2px(30.0F);
        this.exportAdapter1 = new SharePopuList1_padAdapter(this.context, this.padexportlist1, i);
        this.exportAdapter2 = new SharePopuList2_padAdapter(this.context, this.padexportlist2, i);
        this.pad_listview1.setAdapter(this.exportAdapter1);
        this.pad_listview2.setAdapter(this.exportAdapter2);
        this.shareDialog.getWindow().setAttributes(localLayoutParams);
      }
    }
    label173:
    do
    {
      do
      {
        return;
      } while (paramConfiguration.orientation != 2);
      paramConfiguration = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(paramConfiguration);
    } while ((this.shareDialog == null) || (!this.shareDialog.isShowing()));
    WindowManager.LayoutParams localLayoutParams = this.shareDialog.getWindow().getAttributes();
    localLayoutParams.width = ((int)(paramConfiguration.heightPixels * 0.9D));
    int i = localLayoutParams.width - dip2px(30.0F);
    this.exportAdapter1 = new SharePopuList1_padAdapter(this.context, this.padexportlist1, i);
    this.exportAdapter2 = new SharePopuList2_padAdapter(this.context, this.padexportlist2, i);
    this.pad_listview1.setAdapter(this.exportAdapter1);
    this.pad_listview2.setAdapter(this.exportAdapter2);
    this.shareDialog.getWindow().setAttributes(localLayoutParams);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = this;
    this.mapp = MyApplication.getApplication(this.context);
    if (this.mapp.isPad())
    {
      requestWindowFeature(1);
      setContentView(2130903094);
      activity_EditPhoto = this;
      this.edit = getIntent().getBooleanExtra("edit", false);
      this.inflater = LayoutInflater.from(this.context);
      this.metrics = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(this.metrics);
      this.mlist = new ArrayList();
      this.preferences = getSharedPreferences("TinyScanPro", 0);
      this.editor = this.preferences.edit();
      this.editphoto_toolbar = ((Toolbar)findViewById(2131755350));
      setSupportActionBar(this.editphoto_toolbar);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      this.mPager = ((MyViewPager)findViewById(2131755353));
      this.mPager.setOffscreenPageLimit(3);
      if (!this.mapp.isPad()) {
        break label581;
      }
      this.editphoto_camera_relativelayout = ((RelativeLayout)findViewById(2131755362));
      this.addphoto = ((ImageView)findViewById(2131755358));
      this.addphoto.setOnClickListener(this.mlistener2);
      this.addgallery = ((ImageView)findViewById(2131755359));
      this.addgallery.setOnClickListener(this.mlistener2);
      this.edit_photo_pop = ((AnimatingRelativeLayout)findViewById(2131755363));
      this.edit_photo_pop.hide();
      this.draglistview = ((DragListView)findViewById(2131755366));
      this.dragedit = ((ImageView)findViewById(2131755364));
      this.dragedit.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (Activity_EditPhoto.this.isDragEdit)
          {
            Activity_EditPhoto.access$002(Activity_EditPhoto.this, false);
            Activity_EditPhoto.this.dragedit.setImageResource(2130837732);
            paramAnonymousView = Activity_EditPhoto.this.mlist.iterator();
            while (paramAnonymousView.hasNext()) {
              ((Photo_item)paramAnonymousView.next()).setShow(false);
            }
            Activity_EditPhoto.this.adapter2.notifyDataSetChanged();
            return;
          }
          Activity_EditPhoto.access$002(Activity_EditPhoto.this, true);
          Activity_EditPhoto.this.dragedit.setImageResource(2130837709);
          paramAnonymousView = Activity_EditPhoto.this.mlist.iterator();
          while (paramAnonymousView.hasNext()) {
            ((Photo_item)paramAnonymousView.next()).setShow(true);
          }
          Activity_EditPhoto.this.adapter2.notifyDataSetChanged();
        }
      });
    }
    for (;;)
    {
      this.pb = ((ProgressBar)findViewById(2131755361));
      if (!this.mapp.isPad())
      {
        this.share = ((RelativeLayout)findViewById(2131755360));
        this.share.setOnClickListener(this.mlistener2);
        this.rotate = ((RelativeLayout)findViewById(2131755357));
        this.rotate.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Activity_EditPhoto.this.rotateMethod();
          }
        });
      }
      this.edit_photo_name = ((TextView)findViewById(2131755351));
      this.edit_photo_pagenum = ((TextView)findViewById(2131755352));
      this.other = ((TextView)findViewById(2131755355));
      this.edit_photo_name.setText(this.preferences.getString("folder_name", ""));
      paramBundle = new File(this.preferences.getString("folder_path", "")).listFiles();
      if (paramBundle == null) {
        break label634;
      }
      i = 0;
      while (i < paramBundle.length)
      {
        if (paramBundle[i].getName().matches("[0-9]{18}.jpg"))
        {
          Photo_item localPhoto_item = new Photo_item();
          localPhoto_item.setPath(paramBundle[i].getPath());
          localPhoto_item.setShow(false);
          this.mlist.add(localPhoto_item);
        }
        i += 1;
      }
      setRequestedOrientation(1);
      break;
      label581:
      this.takephoto = ((RelativeLayout)findViewById(2131755358));
      this.takephoto.setOnClickListener(this.mlistener2);
      this.gallery = ((RelativeLayout)findViewById(2131755359));
      this.gallery.setOnClickListener(this.mlistener2);
    }
    label634:
    Collections.sort(this.mlist, this.comparator);
    this.other.setText(this.num + "/" + this.mlist.size());
    long l = 0L;
    int i = 0;
    while (i < this.mlist.size())
    {
      l += new File(((Photo_item)this.mlist.get(i)).getPath()).length();
      i += 1;
    }
    this.edit_photo_pagenum.setText(Util.FormetFileSize1(l));
    this.adapter = new MyPageAdapter(this.context, this.mlist);
    this.mPager.setAdapter(this.adapter);
    this.mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramAnonymousInt)
      {
        if (paramAnonymousInt == 0)
        {
          Activity_EditPhoto.access$502(Activity_EditPhoto.this, false);
          Activity_EditPhoto.access$602(Activity_EditPhoto.this, false);
          Activity_EditPhoto.access$702(Activity_EditPhoto.this, new Thread(new Runnable()
          {
            public void run()
            {
              int i;
              if ((Activity_EditPhoto.this.thread != null) && (!Activity_EditPhoto.this.isScroll) && (!Activity_EditPhoto.this.thread.isInterrupted())) {
                i = Activity_EditPhoto.this.num;
              }
              try
              {
                Activity_EditPhoto.access$902(Activity_EditPhoto.this, BitmapTools.getSize(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath()));
                Activity_EditPhoto.access$1002(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mapp.decodeSampledBitmapFromFile(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), ((Integer)Activity_EditPhoto.this.mlist2.get(0)).intValue(), ((Integer)Activity_EditPhoto.this.mlist2.get(1)).intValue())));
                if (Activity_EditPhoto.this.bitmap2 == null)
                {
                  Activity_EditPhoto.access$1002(Activity_EditPhoto.this, Activity_EditPhoto.this.getBitmapDrawable(BitmapFactory.decodeStream(new FileInputStream(new File(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath())))));
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
            Activity_EditPhoto.access$502(Activity_EditPhoto.this, true);
            Activity_EditPhoto.access$602(Activity_EditPhoto.this, false);
            new Thread(new Runnable()
            {
              public void run()
              {
                Activity_EditPhoto.access$1302(Activity_EditPhoto.this, Activity_EditPhoto.this.num);
                if (Activity_EditPhoto.this.mPager.findViewWithTag(Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.oldnum - 1)) != null)
                {
                  Activity_EditPhoto.access$1402(Activity_EditPhoto.this, Activity_EditPhoto.this.mapp.getBitmapFromMemCache(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.oldnum - 1)).getPath()));
                  if (Activity_EditPhoto.this.bitmap != null)
                  {
                    localObject = new Message();
                    ((Message)localObject).what = 11;
                    Activity_EditPhoto.this.handler.sendMessage((Message)localObject);
                  }
                }
                else
                {
                  return;
                }
                Object localObject = BitmapTools.getPhoto2(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.oldnum - 1)).getPath(), 300, 400);
                if (Utils.hasHoneycomb()) {
                  Activity_EditPhoto.access$1402(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), (Bitmap)localObject));
                }
                for (;;)
                {
                  Activity_EditPhoto.this.mapp.addBitmapToMemoryCache(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.oldnum - 1)).getPath(), Activity_EditPhoto.this.bitmap);
                  break;
                  Activity_EditPhoto.access$1402(Activity_EditPhoto.this, new RecyclingBitmapDrawable(Activity_EditPhoto.this.context.getResources(), (Bitmap)localObject));
                }
              }
            }).start();
            return;
          }
        } while (paramAnonymousInt != 2);
        Activity_EditPhoto.access$502(Activity_EditPhoto.this, true);
        Activity_EditPhoto.access$602(Activity_EditPhoto.this, false);
      }
      
      public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2) {}
      
      public void onPageSelected(final int paramAnonymousInt)
      {
        Activity_EditPhoto.access$802(Activity_EditPhoto.this, paramAnonymousInt + 1);
        Activity_EditPhoto.this.other.setText(Activity_EditPhoto.this.num + "/" + Activity_EditPhoto.this.mlist.size());
        Activity_EditPhoto.access$1602(Activity_EditPhoto.this, false);
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
        if (Activity_EditPhoto.this.adapter2 != null) {
          Activity_EditPhoto.this.adapter2.notifyDataSetChanged();
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
                Activity_EditPhoto.access$902(Activity_EditPhoto.this, BitmapTools.getSize(((Photo_item)Activity_EditPhoto.this.mlist.get(i)).getPath()));
                Activity_EditPhoto.access$1002(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mapp.decodeSampledBitmapFromFile(((Photo_item)Activity_EditPhoto.this.mlist.get(i)).getPath(), ((Integer)Activity_EditPhoto.this.mlist2.get(0)).intValue(), ((Integer)Activity_EditPhoto.this.mlist2.get(1)).intValue())));
                if (Activity_EditPhoto.this.bitmap2 == null)
                {
                  Activity_EditPhoto.access$1002(Activity_EditPhoto.this, Activity_EditPhoto.this.getBitmapDrawable(BitmapFactory.decodeStream(new FileInputStream(new File(((Photo_item)Activity_EditPhoto.this.mlist.get(i)).getPath())))));
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
    if (this.edit)
    {
      this.edit = false;
      edit();
    }
    if (this.mapp.getAdvOrChargeOrNormal() != 3)
    {
      this.iapBuy = new IAPBuy(this);
      this.iapBuy.buyGoogleAdvPro();
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131820544, paramMenu);
    if (this.mapp.isPad()) {
      this.editphoto_toolbar.getMenu().findItem(2131755698).setVisible(false);
    }
    for (;;)
    {
      return super.onCreateOptionsMenu(paramMenu);
      this.editphoto_toolbar.getMenu().findItem(2131755699).setVisible(false);
      this.editphoto_toolbar.getMenu().findItem(2131755700).setVisible(false);
      this.editphoto_toolbar.getMenu().findItem(2131755701).setVisible(false);
      this.editphoto_toolbar.getMenu().findItem(2131755702).setVisible(false);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if ((this.progressDialog != null) && (this.progressDialog.isShowing())) {
      this.progressDialog.dismiss();
    }
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
      if (this.isRun) {
        return true;
      }
      if (this.mapp.isUpdate())
      {
        this.isonkeydown = true;
        createPDF2();
        this.mapp.setUpdate(false);
        return true;
      }
      finish();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131755698) {
      if (!this.isRun) {}
    }
    for (;;)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      Intent localIntent = new Intent(this.context, Activity_ListPhotos.class);
      localIntent.putExtra("folder_name", this.preferences.getString("folder_name", ""));
      localIntent.putExtra("photo_path", this.preferences.getString("folder_path", ""));
      localIntent.putExtra("folder_root_path", this.preferences.getString("folder_root_path", ""));
      localIntent.putExtra("mlist", this.mlist);
      startActivityForResult(localIntent, 0);
      continue;
      if (paramMenuItem.getItemId() == 16908332)
      {
        if (!this.isRun) {
          if (this.mapp.isUpdate())
          {
            this.isonkeydown = false;
            createPDF2();
          }
          else
          {
            finish();
          }
        }
      }
      else if (paramMenuItem.getItemId() == 2131755699)
      {
        rotateMethod();
      }
      else
      {
        int i;
        if (paramMenuItem.getItemId() == 2131755700)
        {
          this.country = new String[] { "US", "CA", "GB", "JP", "FR", "DE", "AR", "BR", "IL", "IN", "PT", "IT", "HK", "AU", "AT", "BE", "DK", "GR", "IE", "LU", "NL", "NO", "RO", "ES", "ZA", "SE", "CH" };
          i = 0;
          while (i < this.country.length)
          {
            if (Locale.getDefault().getCountry().equals(this.country[i])) {
              this.mapp.setIsContainCountry(true);
            }
            i += 1;
          }
          sharepadAlterdialog();
        }
        else if (paramMenuItem.getItemId() == 2131755701)
        {
          deletePageMethod();
        }
        else if (paramMenuItem.getItemId() == 2131755702)
        {
          if (this.edit_photo_pop.isShown())
          {
            this.editphoto_camera_relativelayout.setVisibility(0);
            this.edit_photo_pop.hide();
          }
          else
          {
            this.editphoto_camera_relativelayout.setVisibility(8);
            this.edit_photo_pop.show();
            int j = this.mPager.getCurrentItem();
            i = 0;
            if (i < this.mlist.size())
            {
              if (i == j) {
                ((Photo_item)this.mlist.get(i)).setSelected(true);
              }
              for (;;)
              {
                i += 1;
                break;
                ((Photo_item)this.mlist.get(i)).setSelected(false);
              }
            }
            this.adapter2 = new DragListAdapter(this, this.context, this.mlist, this.preferences.getString("folder_path", ""));
            this.draglistview.setAdapter(this.adapter2);
            this.isDragEdit = false;
          }
        }
      }
    }
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    if ((this.mapp.getAdvOrChargeOrNormal() == 1) && (this.preferences.getInt("file_count_total", 0) >= 2) && ((this.isclick_cloud) || (this.isclick_systemdialog)))
    {
      if (!this.isclick_cloud) {
        break label124;
      }
      FlurryAgent.logEvent("7_OPTIONDIR");
      Utils.showAd_chayeads(activity_EditPhoto);
    }
    long l;
    for (;;)
    {
      this.isclick_cloud = false;
      this.isclick_systemdialog = false;
      l = 0L;
      int i = 0;
      while (i < this.mlist.size())
      {
        l += new File(((Photo_item)this.mlist.get(i)).getPath()).length();
        i += 1;
      }
      label124:
      if ((this.isclick_systemdialog) && (System.currentTimeMillis() - this.preferences.getLong("click_systemdialog_time", 0L) >= 5000L))
      {
        FlurryAgent.logEvent("7_OPTION5S");
        Utils.showAd_chayeads(activity_EditPhoto);
      }
    }
    this.edit_photo_pagenum.setText(Util.FormetFileSize1(l));
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
            Activity_EditPhoto.access$802(Activity_EditPhoto.this, 1);
          }
          i = Activity_EditPhoto.this.num;
          if ((Activity_EditPhoto.this.mlist == null) || (Activity_EditPhoto.this.mlist.size() <= 0) || (Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1) == null)) {}
        }
        try
        {
          Activity_EditPhoto.access$902(Activity_EditPhoto.this, BitmapTools.getSize(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath()));
          Activity_EditPhoto.access$1002(Activity_EditPhoto.this, new BitmapDrawable(Activity_EditPhoto.this.context.getResources(), Activity_EditPhoto.this.mapp.decodeSampledBitmapFromFile(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath(), ((Integer)Activity_EditPhoto.this.mlist2.get(0)).intValue(), ((Integer)Activity_EditPhoto.this.mlist2.get(1)).intValue())));
          if (Activity_EditPhoto.this.bitmap2 == null)
          {
            Activity_EditPhoto.access$1002(Activity_EditPhoto.this, Activity_EditPhoto.this.getBitmapDrawable(BitmapFactory.decodeStream(new FileInputStream(new File(((Photo_item)Activity_EditPhoto.this.mlist.get(Activity_EditPhoto.this.num - 1)).getPath())))));
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
  
  public int px2dip(float paramFloat)
  {
    return (int)(paramFloat / getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public void relist()
  {
    for (;;)
    {
      int i;
      try
      {
        this.mlist.clear();
        this.edit_photo_name.setText(this.preferences.getString("folder_name", ""));
        localFile = new File(this.preferences.getString("folder_path", ""));
        arrayOfFile = localFile.listFiles();
        if (arrayOfFile.length >= 1) {
          break label247;
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
        this.adapter = new MyPageAdapter(this.context, this.mlist);
        this.mPager.setAdapter(this.adapter);
        this.num = 1;
        this.other.setText(this.num + "/" + this.mlist.size());
        return;
        label247:
        i = 0;
        continue;
      }
      i += 1;
    }
  }
  
  @SuppressLint({"InflateParams"})
  protected void sharepadAlterdialog()
  {
    Object localObject1 = this.inflater.inflate(2130903167, null);
    Object localObject2 = (RadioGroup)((View)localObject1).findViewById(2131755675);
    Object localObject3 = (RadioButton)((View)localObject1).findViewById(2131755676);
    ((RadioGroup)localObject2).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
      public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, @IdRes int paramAnonymousInt)
      {
        if (paramAnonymousInt == this.val$popu_radiobutton_pdf.getId())
        {
          Activity_EditPhoto.access$3802(Activity_EditPhoto.this, true);
          Activity_EditPhoto.this.addPDF_listdata();
          Activity_EditPhoto.access$4002(Activity_EditPhoto.this, new SharePopuList1_padAdapter(Activity_EditPhoto.this.context, Activity_EditPhoto.this.padexportlist1, Activity_EditPhoto.this.currentWidth));
          Activity_EditPhoto.access$4302(Activity_EditPhoto.this, new SharePopuList2_padAdapter(Activity_EditPhoto.this.context, Activity_EditPhoto.this.padexportlist2, Activity_EditPhoto.this.currentWidth));
          Activity_EditPhoto.this.pad_listview1.setAdapter(Activity_EditPhoto.this.exportAdapter1);
          Activity_EditPhoto.this.pad_listview2.setAdapter(Activity_EditPhoto.this.exportAdapter2);
        }
        while (paramAnonymousInt != this.val$popu_radiobutton_jpg.getId()) {
          return;
        }
        Activity_EditPhoto.access$3802(Activity_EditPhoto.this, false);
        Activity_EditPhoto.this.addjpg_listdata();
        Activity_EditPhoto.access$4002(Activity_EditPhoto.this, new SharePopuList1_padAdapter(Activity_EditPhoto.this.context, Activity_EditPhoto.this.padexportlist1, Activity_EditPhoto.this.currentWidth));
        Activity_EditPhoto.access$4302(Activity_EditPhoto.this, new SharePopuList2_padAdapter(Activity_EditPhoto.this.context, Activity_EditPhoto.this.padexportlist2, Activity_EditPhoto.this.currentWidth));
        Activity_EditPhoto.this.pad_listview1.setAdapter(Activity_EditPhoto.this.exportAdapter1);
        Activity_EditPhoto.this.pad_listview2.setAdapter(Activity_EditPhoto.this.exportAdapter2);
      }
    });
    final long l;
    WindowManager.LayoutParams localLayoutParams;
    if (((RadioButton)localObject3).isChecked())
    {
      this.pdf_or_jpg = true;
      addPDF_listdata();
      this.pad_listview1 = ((HorizontalListView)((View)localObject1).findViewById(2131755678));
      this.pad_listview2 = ((HorizontalListView)((View)localObject1).findViewById(2131755679));
      localObject2 = (TextView)((View)localObject1).findViewById(2131755680);
      localObject3 = (TextView)((View)localObject1).findViewById(2131755681);
      l = getfileSizeLength();
      ((TextView)localObject2).setText("File Size: " + Util.FormetFileSize1(l));
      ((TextView)localObject3).setText("Large");
      this.export_size = 0;
      this.shareDialog = new AlertDialog.Builder(activity_EditPhoto).setView((View)localObject1).create();
      this.shareDialog.show();
      this.shareDialog.setCanceledOnTouchOutside(true);
      this.shareDialog.setCancelable(true);
      localObject1 = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject1);
      localLayoutParams = this.shareDialog.getWindow().getAttributes();
      if (this.context.getResources().getConfiguration().orientation != 2) {
        break label451;
      }
      localLayoutParams.width = ((int)(((DisplayMetrics)localObject1).heightPixels * 0.9D));
    }
    for (this.currentWidth = localLayoutParams.width;; this.currentWidth = localLayoutParams.width)
    {
      this.currentWidth -= dip2px(30.0F);
      this.exportAdapter1 = new SharePopuList1_padAdapter(this.context, this.padexportlist1, this.currentWidth);
      this.exportAdapter2 = new SharePopuList2_padAdapter(this.context, this.padexportlist2, this.currentWidth);
      this.pad_listview1.setAdapter(this.exportAdapter1);
      this.pad_listview2.setAdapter(this.exportAdapter2);
      this.shareDialog.getWindow().setAttributes(localLayoutParams);
      ((TextView)localObject3).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (this.val$filesize_size.getText().toString().equals("Large"))
          {
            this.val$filesize_size.setText("Medium");
            this.val$filesize_tv.setText("File Size: about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 1)));
            Activity_EditPhoto.access$2402(Activity_EditPhoto.this, 1);
          }
          do
          {
            return;
            if (this.val$filesize_size.getText().toString().equals("Medium"))
            {
              this.val$filesize_size.setText("Small");
              this.val$filesize_tv.setText("File Size: about " + Util.FormetFileSize1(Util.getFilesizeAbout(l, 2)));
              Activity_EditPhoto.access$2402(Activity_EditPhoto.this, 2);
              return;
            }
          } while (!this.val$filesize_size.getText().toString().equals("Small"));
          this.val$filesize_size.setText("Large");
          this.val$filesize_tv.setText("File Size: " + Util.FormetFileSize1(l));
          Activity_EditPhoto.access$2402(Activity_EditPhoto.this, 0);
        }
      });
      this.pad_listview1.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (Activity_EditPhoto.this.pdf_or_jpg)
          {
            Activity_EditPhoto.access$2702(Activity_EditPhoto.this, false);
            Activity_EditPhoto.this.createPDFHorizontalListview(Activity_EditPhoto.this.padexportlist1, paramAnonymousInt);
            return;
          }
          Activity_EditPhoto.access$2702(Activity_EditPhoto.this, false);
          Activity_EditPhoto.this.createPDFHorizontalListview_jpg(Activity_EditPhoto.this.padexportlist1, paramAnonymousInt);
        }
      });
      this.pad_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (Activity_EditPhoto.this.pdf_or_jpg)
          {
            Activity_EditPhoto.access$2702(Activity_EditPhoto.this, true);
            Activity_EditPhoto.this.createPDFHorizontalListview(Activity_EditPhoto.this.padexportlist2, paramAnonymousInt);
            return;
          }
          Activity_EditPhoto.access$2702(Activity_EditPhoto.this, true);
          Activity_EditPhoto.this.createPDFHorizontalListview_jpg(Activity_EditPhoto.this.padexportlist2, paramAnonymousInt);
        }
      });
      return;
      this.pdf_or_jpg = false;
      addjpg_listdata();
      break;
      label451:
      localLayoutParams.width = ((int)(((DisplayMetrics)localObject1).widthPixels * 0.9D));
    }
  }
  
  public void showToast(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast localToast = Toast.makeText(Activity_EditPhoto.this.getApplicationContext(), paramString, 0);
        localToast.setGravity(17, 0, 0);
        localToast.show();
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
    this.adapter = new MyPageAdapter(this.context, this.mlist);
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
    this.adapter2.notifyDataSetChanged();
    this.num = (i + 1);
    this.other.setText(this.num + "/" + this.mlist.size());
  }
  
  public void updateMove(int paramInt)
  {
    this.mapp.setUpdate(true);
    deletePDF();
    this.adapter = new MyPageAdapter(this.context, this.mlist);
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
    this.adapter2.notifyDataSetChanged();
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
  
  class SelectPicPopupWindow
    extends PopupWindow
  {
    private SharePopuList1Adapter exportAdapter1;
    private SharePopuList2Adapter exportAdapter2;
    private ArrayList<HashMap<String, Object>> exportlist1;
    private ArrayList<HashMap<String, Object>> exportlist2;
    private TextView filesize_size;
    private TextView filesize_tv;
    private HorizontalListView listview1;
    private HorizontalListView listview2;
    private View mMenuView;
    private boolean pdf_or_jpg = true;
    long totalLength = 0L;
    
    @SuppressLint({"InflateParams"})
    public SelectPicPopupWindow(final Activity paramActivity, View.OnClickListener paramOnClickListener, final int paramInt)
    {
      super();
      this.mMenuView = ((LayoutInflater)paramActivity.getSystemService("layout_inflater")).inflate(2130903168, null);
      paramOnClickListener = (RadioGroup)this.mMenuView.findViewById(2131755675);
      final RadioButton localRadioButton = (RadioButton)this.mMenuView.findViewById(2131755676);
      paramOnClickListener.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
      {
        public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, @IdRes int paramAnonymousInt)
        {
          if (paramAnonymousInt == localRadioButton.getId())
          {
            Activity_EditPhoto.SelectPicPopupWindow.access$4902(Activity_EditPhoto.SelectPicPopupWindow.this, true);
            Activity_EditPhoto.SelectPicPopupWindow.this.addPDF_listdata();
            Activity_EditPhoto.SelectPicPopupWindow.access$5102(Activity_EditPhoto.SelectPicPopupWindow.this, new SharePopuList1Adapter(paramActivity, Activity_EditPhoto.SelectPicPopupWindow.this.exportlist1, paramInt));
            Activity_EditPhoto.SelectPicPopupWindow.access$5302(Activity_EditPhoto.SelectPicPopupWindow.this, new SharePopuList2Adapter(paramActivity, Activity_EditPhoto.SelectPicPopupWindow.this.exportlist2, paramInt));
            Activity_EditPhoto.SelectPicPopupWindow.this.listview1.setAdapter(Activity_EditPhoto.SelectPicPopupWindow.this.exportAdapter1);
            Activity_EditPhoto.SelectPicPopupWindow.this.listview2.setAdapter(Activity_EditPhoto.SelectPicPopupWindow.this.exportAdapter2);
          }
          while (paramAnonymousInt != this.val$popu_radiobutton_jpg.getId()) {
            return;
          }
          Activity_EditPhoto.SelectPicPopupWindow.access$4902(Activity_EditPhoto.SelectPicPopupWindow.this, false);
          Activity_EditPhoto.SelectPicPopupWindow.this.addjpg_listdata();
          Activity_EditPhoto.SelectPicPopupWindow.access$5102(Activity_EditPhoto.SelectPicPopupWindow.this, new SharePopuList1Adapter(paramActivity, Activity_EditPhoto.SelectPicPopupWindow.this.exportlist1, paramInt));
          Activity_EditPhoto.SelectPicPopupWindow.access$5302(Activity_EditPhoto.SelectPicPopupWindow.this, new SharePopuList2Adapter(paramActivity, Activity_EditPhoto.SelectPicPopupWindow.this.exportlist2, paramInt));
          Activity_EditPhoto.SelectPicPopupWindow.this.listview1.setAdapter(Activity_EditPhoto.SelectPicPopupWindow.this.exportAdapter1);
          Activity_EditPhoto.SelectPicPopupWindow.this.listview2.setAdapter(Activity_EditPhoto.SelectPicPopupWindow.this.exportAdapter2);
        }
      });
      if (localRadioButton.isChecked())
      {
        this.pdf_or_jpg = true;
        addPDF_listdata();
      }
      for (;;)
      {
        this.exportAdapter1 = new SharePopuList1Adapter(paramActivity, this.exportlist1, paramInt);
        this.exportAdapter2 = new SharePopuList2Adapter(paramActivity, this.exportlist2, paramInt);
        this.listview1 = ((HorizontalListView)this.mMenuView.findViewById(2131755683));
        this.listview2 = ((HorizontalListView)this.mMenuView.findViewById(2131755684));
        this.filesize_tv = ((TextView)this.mMenuView.findViewById(2131755680));
        this.filesize_size = ((TextView)this.mMenuView.findViewById(2131755681));
        paramInt = 0;
        while (paramInt < Activity_EditPhoto.this.mlist.size())
        {
          paramActivity = new File(((Photo_item)Activity_EditPhoto.this.mlist.get(paramInt)).getPath());
          this.totalLength += paramActivity.length();
          paramInt += 1;
        }
        this.pdf_or_jpg = false;
        addjpg_listdata();
      }
      this.filesize_tv.setText("File Size: " + Util.FormetFileSize1(this.totalLength));
      this.filesize_size.setText("Large");
      Activity_EditPhoto.access$2402(Activity_EditPhoto.this, 0);
      this.listview1.setAdapter(this.exportAdapter1);
      this.listview2.setAdapter(this.exportAdapter2);
      setContentView(this.mMenuView);
      setWidth(-1);
      setHeight(-1);
      setFocusable(true);
      setAnimationStyle(2131493040);
      setBackgroundDrawable(new ColorDrawable(1275068416));
      this.mMenuView.setOnTouchListener(new View.OnTouchListener()
      {
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          int i = Activity_EditPhoto.SelectPicPopupWindow.this.mMenuView.findViewById(2131755682).getTop();
          int j = (int)paramAnonymousMotionEvent.getY();
          if ((paramAnonymousMotionEvent.getAction() == 1) && (j < i)) {
            Activity_EditPhoto.SelectPicPopupWindow.this.dismiss();
          }
          return true;
        }
      });
      this.filesize_size.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (Activity_EditPhoto.SelectPicPopupWindow.this.filesize_size.getText().toString().equals("Large"))
          {
            Activity_EditPhoto.SelectPicPopupWindow.this.filesize_size.setText("Medium");
            Activity_EditPhoto.SelectPicPopupWindow.this.filesize_tv.setText("File Size: about " + Util.FormetFileSize1(Util.getFilesizeAbout(Activity_EditPhoto.SelectPicPopupWindow.this.totalLength, 1)));
            Activity_EditPhoto.access$2402(Activity_EditPhoto.this, 1);
          }
          do
          {
            return;
            if (Activity_EditPhoto.SelectPicPopupWindow.this.filesize_size.getText().toString().equals("Medium"))
            {
              Activity_EditPhoto.SelectPicPopupWindow.this.filesize_size.setText("Small");
              Activity_EditPhoto.SelectPicPopupWindow.this.filesize_tv.setText("File Size: about " + Util.FormetFileSize1(Util.getFilesizeAbout(Activity_EditPhoto.SelectPicPopupWindow.this.totalLength, 2)));
              Activity_EditPhoto.access$2402(Activity_EditPhoto.this, 2);
              return;
            }
          } while (!Activity_EditPhoto.SelectPicPopupWindow.this.filesize_size.getText().toString().equals("Small"));
          Activity_EditPhoto.SelectPicPopupWindow.this.filesize_size.setText("Large");
          Activity_EditPhoto.SelectPicPopupWindow.this.filesize_tv.setText("File Size: " + Util.FormetFileSize1(Activity_EditPhoto.SelectPicPopupWindow.this.totalLength));
          Activity_EditPhoto.access$2402(Activity_EditPhoto.this, 0);
        }
      });
      this.listview1.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (Activity_EditPhoto.SelectPicPopupWindow.this.pdf_or_jpg)
          {
            Activity_EditPhoto.access$2702(Activity_EditPhoto.this, false);
            Activity_EditPhoto.this.createPDFHorizontalListview(Activity_EditPhoto.SelectPicPopupWindow.this.exportlist1, paramAnonymousInt);
            return;
          }
          Activity_EditPhoto.access$2702(Activity_EditPhoto.this, false);
          Activity_EditPhoto.this.createPDFHorizontalListview_jpg(Activity_EditPhoto.SelectPicPopupWindow.this.exportlist1, paramAnonymousInt);
        }
      });
      this.listview2.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (Activity_EditPhoto.SelectPicPopupWindow.this.pdf_or_jpg)
          {
            Activity_EditPhoto.access$2702(Activity_EditPhoto.this, true);
            Activity_EditPhoto.this.createPDFHorizontalListview(Activity_EditPhoto.SelectPicPopupWindow.this.exportlist2, paramAnonymousInt);
            return;
          }
          Activity_EditPhoto.access$2702(Activity_EditPhoto.this, true);
          Activity_EditPhoto.this.createPDFHorizontalListview_jpg(Activity_EditPhoto.SelectPicPopupWindow.this.exportlist2, paramAnonymousInt);
        }
      });
    }
    
    private void addPDF_listdata()
    {
      this.exportlist1 = new ArrayList();
      this.exportlist2 = new ArrayList();
      int i;
      if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
      {
        i = 0;
        while (i < 5)
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          localHashMap.put("isPro", Boolean.valueOf(false));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          this.exportlist1.add(localHashMap);
          i += 1;
        }
        i = 0;
        if (i < 5)
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          if (i == 3) {
            localHashMap.put("id", Integer.valueOf(6));
          }
          if (i == 4) {
            localHashMap.put("id", Integer.valueOf(3));
          }
          localHashMap.put("isPro", Boolean.valueOf(false));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          if (i == 3) {
            if (Build.VERSION.SDK_INT >= 19) {
              this.exportlist2.add(localHashMap);
            }
          }
          for (;;)
          {
            i += 1;
            break;
            this.exportlist2.add(localHashMap);
          }
        }
      }
      else
      {
        i = 0;
        while (i < 5)
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          localHashMap.put("isPro", Boolean.valueOf(true));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          this.exportlist1.add(localHashMap);
          i += 1;
        }
        i = 0;
        if (i < 5)
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          if (i == 3) {
            localHashMap.put("id", Integer.valueOf(6));
          }
          if (i == 4) {
            localHashMap.put("id", Integer.valueOf(3));
          }
          if ((i == 0) || (i == 4) || (i == 2))
          {
            localHashMap.put("isPro", Boolean.valueOf(false));
            label399:
            localHashMap.put("isEnable", Boolean.valueOf(true));
            if (i != 3) {
              break label455;
            }
            if (Build.VERSION.SDK_INT >= 19) {
              this.exportlist2.add(localHashMap);
            }
          }
          for (;;)
          {
            i += 1;
            break;
            localHashMap.put("isPro", Boolean.valueOf(true));
            break label399;
            label455:
            this.exportlist2.add(localHashMap);
          }
        }
      }
      if (!Activity_EditPhoto.this.mapp.getIsContainCountry()) {
        this.exportlist2.remove(2);
      }
      if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(5));
        localHashMap.put("isPro", Boolean.valueOf(false));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        this.exportlist2.add(localHashMap);
        return;
      }
      HashMap localHashMap = new HashMap();
      localHashMap.put("id", Integer.valueOf(5));
      localHashMap.put("isPro", Boolean.valueOf(true));
      localHashMap.put("isEnable", Boolean.valueOf(true));
      this.exportlist2.add(localHashMap);
    }
    
    private void addjpg_listdata()
    {
      this.exportlist1 = new ArrayList();
      this.exportlist2 = new ArrayList();
      int i;
      if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
      {
        i = 0;
        while (i < 5)
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          localHashMap.put("isPro", Boolean.valueOf(false));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          this.exportlist1.add(localHashMap);
          i += 1;
        }
        i = 0;
        if (i < 5)
        {
          if ((i == 2) || (i == 3)) {}
          for (;;)
          {
            i += 1;
            break;
            localHashMap = new HashMap();
            localHashMap.put("id", Integer.valueOf(i));
            localHashMap.put("isPro", Boolean.valueOf(false));
            localHashMap.put("isEnable", Boolean.valueOf(true));
            this.exportlist2.add(localHashMap);
          }
        }
      }
      else
      {
        i = 0;
        while (i < 5)
        {
          localHashMap = new HashMap();
          localHashMap.put("id", Integer.valueOf(i));
          localHashMap.put("isPro", Boolean.valueOf(true));
          localHashMap.put("isEnable", Boolean.valueOf(true));
          this.exportlist1.add(localHashMap);
          i += 1;
        }
        i = 0;
        while (i < 5) {
          if ((i == 2) || (i == 3))
          {
            i += 1;
          }
          else
          {
            localHashMap = new HashMap();
            localHashMap.put("id", Integer.valueOf(i));
            if ((i == 0) || (i == 3) || (i == 4) || (i == 2)) {
              localHashMap.put("isPro", Boolean.valueOf(false));
            }
            for (;;)
            {
              localHashMap.put("isEnable", Boolean.valueOf(true));
              this.exportlist2.add(localHashMap);
              break;
              localHashMap.put("isPro", Boolean.valueOf(true));
            }
          }
        }
      }
      if ((Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_EditPhoto.this.mapp.getAdvOrChargeOrNormal() == 1))
      {
        localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(5));
        localHashMap.put("isPro", Boolean.valueOf(false));
        localHashMap.put("isEnable", Boolean.valueOf(true));
        this.exportlist2.add(localHashMap);
        return;
      }
      HashMap localHashMap = new HashMap();
      localHashMap.put("id", Integer.valueOf(5));
      localHashMap.put("isPro", Boolean.valueOf(true));
      localHashMap.put("isEnable", Boolean.valueOf(true));
      this.exportlist2.add(localHashMap);
    }
  }
}
