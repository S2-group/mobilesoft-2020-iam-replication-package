package com.exlyo.mapmarker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

public class AndroidUtils
{
  private static final double EARTHRADIUS = 6366198.0D;
  
  public AndroidUtils() {}
  
  private static AlertDialog.Builder buildYesNoDialog(Activity paramActivity, int paramInt1, int paramInt2, String paramString, CharSequence paramCharSequence, Runnable paramRunnable1, Runnable paramRunnable2, Runnable paramRunnable3)
  {
    paramActivity = new AlertDialog.Builder(paramActivity);
    paramActivity.setPositiveButton(paramInt1, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (AndroidUtils.this != null) {
          AndroidUtils.this.run();
        }
      }
    });
    if (paramInt2 != -1)
    {
      paramActivity.setNegativeButton(paramInt2, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (AndroidUtils.this != null) {
            AndroidUtils.this.run();
          }
        }
      });
      paramActivity.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramAnonymousDialogInterface)
        {
          if (AndroidUtils.this != null) {
            AndroidUtils.this.run();
          }
        }
      });
    }
    if (paramString != null) {
      paramActivity.setTitle(paramString);
    }
    if (paramCharSequence != null) {
      paramActivity.setMessage(paramCharSequence);
    }
    return paramActivity;
  }
  
  public static float dipToPixels(Context paramContext, float paramFloat)
  {
    return TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics());
  }
  
  public static void fixMaxZoomLevelOnLatLngBounds(LatLngBounds.Builder paramBuilder)
  {
    try
    {
      LatLng localLatLng1 = paramBuilder.build().getCenter();
      LatLng localLatLng2 = move(localLatLng1, 709.0D, 709.0D);
      paramBuilder.include(move(localLatLng1, -709.0D, -709.0D));
      paramBuilder.include(localLatLng2);
      return;
    }
    catch (Throwable paramBuilder)
    {
      paramBuilder.printStackTrace();
    }
  }
  
  public static final String getMetaDataString(Context paramContext, String paramString)
  {
    Object localObject = null;
    try
    {
      ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      paramContext = localObject;
      if (localApplicationInfo.metaData != null) {
        paramContext = localApplicationInfo.metaData.getString(paramString);
      }
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static void hideSoftKeyboard(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static final boolean isGooglePlayInstalled(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return false;
      }
      localPackageInfo = (PackageInfo)paramContext.next();
    } while ((!localPackageInfo.packageName.equals("com.google.market")) && (!localPackageInfo.packageName.equals("com.android.vending")));
    return true;
  }
  
  private static double meterToLatitude(double paramDouble)
  {
    return Math.toDegrees(paramDouble / 6366198.0D);
  }
  
  private static double meterToLongitude(double paramDouble1, double paramDouble2)
  {
    return Math.toDegrees(paramDouble1 / (Math.cos(Math.toRadians(paramDouble2)) * 6366198.0D));
  }
  
  private static LatLng move(LatLng paramLatLng, double paramDouble1, double paramDouble2)
  {
    paramDouble2 = meterToLongitude(paramDouble2, paramLatLng.latitude);
    paramDouble1 = meterToLatitude(paramDouble1);
    return new LatLng(paramLatLng.latitude + paramDouble1, paramLatLng.longitude + paramDouble2);
  }
  
  public static String rawResourceToString(Context paramContext, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      try
      {
        localBufferedReader = new BufferedReader(new InputStreamReader(paramContext.getResources().openRawResource(paramInt)));
        paramContext = localBufferedReader.readLine();
        if (paramContext != null) {
          continue;
        }
      }
      catch (Throwable paramContext)
      {
        BufferedReader localBufferedReader;
        String str;
        paramContext.printStackTrace();
        continue;
      }
      return localStringBuilder.toString();
      localStringBuilder.append(paramContext);
      str = localBufferedReader.readLine();
      paramContext = str;
      if (str != null)
      {
        localStringBuilder.append("\n");
        paramContext = str;
      }
    }
  }
  
  public static void setEditTextTextWithCursorAtEnd(EditText paramEditText, String paramString)
  {
    paramEditText.setText(paramString);
    paramEditText.setSelection(paramEditText.getText().length());
  }
  
  public static void showLongToastMessage(Activity paramActivity, final int paramInt)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(AndroidUtils.this, paramInt, 1).show();
      }
    });
  }
  
  public static void showMessageDialog(Activity paramActivity, int paramInt1, int paramInt2)
  {
    showMessageDialog(paramActivity, paramInt1, paramInt2, null);
  }
  
  public static void showMessageDialog(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3)
  {
    showYesNoDialog(paramActivity, paramInt2, -1, paramInt1, paramInt3, null);
  }
  
  public static void showMessageDialog(Activity paramActivity, int paramInt1, int paramInt2, Runnable paramRunnable)
  {
    showYesNoDialog(paramActivity, paramInt1, -1, "", paramActivity.getText(paramInt2).toString(), paramRunnable, paramRunnable, paramRunnable);
  }
  
  public static void showMessageDialog(Activity paramActivity, int paramInt, String paramString)
  {
    showYesNoDialog(paramActivity, paramInt, -1, paramString, null, null);
  }
  
  public static void showMultiChoiceDialog(Activity paramActivity, Object[] paramArrayOfObject, int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramActivity = new AlertDialog.Builder(paramActivity);
    CharSequence[] arrayOfCharSequence = new CharSequence[paramArrayOfObject.length];
    int i = 0;
    for (;;)
    {
      if (i >= arrayOfCharSequence.length)
      {
        paramActivity.setTitle(paramInt);
        paramActivity.setItems(arrayOfCharSequence, paramOnClickListener);
        paramActivity.setNegativeButton(2131099698, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
        });
        paramActivity = paramActivity.create();
        paramActivity.setCanceledOnTouchOutside(true);
        paramActivity.show();
        return;
      }
      arrayOfCharSequence[i] = paramArrayOfObject[i].toString();
      i += 1;
    }
  }
  
  public static void showShortToastMessage(Activity paramActivity, final int paramInt)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(AndroidUtils.this, paramInt, 0).show();
      }
    });
  }
  
  public static void showShortToastMessage(Activity paramActivity, final String paramString)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(AndroidUtils.this, paramString, 0).show();
      }
    });
  }
  
  public static void showSoftKeyboardAtEndOfEditText(Context paramContext, EditText paramEditText)
  {
    paramEditText.setSelection(paramEditText.getText().length());
    ((InputMethodManager)paramContext.getSystemService("input_method")).showSoftInput(paramEditText, 0);
  }
  
  public static void showTextInputDialog(final Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, TextInputCallback paramTextInputCallback)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    View localView = paramActivity.getLayoutInflater().inflate(2130903069, null);
    final EditText localEditText = (EditText)localView.findViewById(2131230810);
    localBuilder.setView(localView);
    localBuilder.setPositiveButton(paramInt1, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        AndroidUtils.this.textChosen(localEditText.getText().toString());
        AndroidUtils.hideSoftKeyboard(paramActivity, localEditText);
      }
    });
    if (paramInt2 != -1)
    {
      localBuilder.setNegativeButton(paramInt2, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          AndroidUtils.this.textChosen(null);
          AndroidUtils.hideSoftKeyboard(paramActivity, localEditText);
        }
      });
      localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramAnonymousDialogInterface)
        {
          AndroidUtils.this.textChosen(null);
          AndroidUtils.hideSoftKeyboard(paramActivity, localEditText);
        }
      });
    }
    localBuilder.setMessage(paramActivity.getText(paramInt3));
    paramTextInputCallback = localBuilder.create();
    paramTextInputCallback.setOnShowListener(new DialogInterface.OnShowListener()
    {
      public void onShow(DialogInterface paramAnonymousDialogInterface)
      {
        AndroidUtils.showSoftKeyboardAtEndOfEditText(AndroidUtils.this, localEditText);
      }
    });
    paramTextInputCallback.show();
  }
  
  public static void showYesNoCancelDialog(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, Runnable paramRunnable1, Runnable paramRunnable2)
  {
    paramActivity = buildYesNoDialog(paramActivity, paramInt1, paramInt2, "", paramActivity.getText(paramInt3), paramRunnable1, paramRunnable2, null);
    paramActivity.setNeutralButton(2131099698, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    paramActivity.create().show();
  }
  
  public static void showYesNoDialog(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Runnable paramRunnable)
  {
    showYesNoDialog(paramActivity, paramInt1, paramInt2, paramInt3, paramInt4, paramRunnable, null);
  }
  
  public static void showYesNoDialog(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Runnable paramRunnable1, Runnable paramRunnable2)
  {
    String str;
    if (paramInt3 == -1)
    {
      str = null;
      if (paramInt4 != -1) {
        break label48;
      }
    }
    label48:
    for (Object localObject = null;; localObject = paramActivity.getText(paramInt4).toString())
    {
      showYesNoDialog(paramActivity, paramInt1, paramInt2, str, (CharSequence)localObject, paramRunnable1, paramRunnable2, null);
      return;
      str = paramActivity.getText(paramInt3).toString();
      break;
    }
  }
  
  public static void showYesNoDialog(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, Runnable paramRunnable)
  {
    showYesNoDialog(paramActivity, paramInt1, paramInt2, paramInt3, paramRunnable, null);
  }
  
  public static void showYesNoDialog(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, Runnable paramRunnable1, Runnable paramRunnable2)
  {
    showYesNoDialog(paramActivity, paramInt1, paramInt2, -1, paramInt3, paramRunnable1, paramRunnable2);
  }
  
  public static void showYesNoDialog(Activity paramActivity, int paramInt1, int paramInt2, String paramString, CharSequence paramCharSequence)
  {
    showYesNoDialog(paramActivity, paramInt1, paramInt2, paramString, paramCharSequence, null, null, null);
  }
  
  public static void showYesNoDialog(Activity paramActivity, int paramInt1, int paramInt2, String paramString, CharSequence paramCharSequence, Runnable paramRunnable1, Runnable paramRunnable2, Runnable paramRunnable3)
  {
    paramActivity = buildYesNoDialog(paramActivity, paramInt1, paramInt2, paramString, paramCharSequence, paramRunnable1, paramRunnable2, paramRunnable2).create();
    paramActivity.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (AndroidUtils.this != null) {
          AndroidUtils.this.run();
        }
      }
    });
    paramActivity.show();
  }
  
  public static void showYesNoDialog(Activity paramActivity, int paramInt1, int paramInt2, String paramString, Runnable paramRunnable1, Runnable paramRunnable2)
  {
    showYesNoDialog(paramActivity, paramInt1, paramInt2, "", paramString, paramRunnable1, paramRunnable2, null);
  }
  
  public static abstract class TextInputCallback
  {
    public TextInputCallback() {}
    
    protected abstract void textChosen(String paramString);
  }
}
