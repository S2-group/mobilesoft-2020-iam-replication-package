package com.magnifis.parking;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CrowdBrain
{
  public CrowdBrain() {}
  
  private static Context contextTheme(Context paramContext)
  {
    Object localObject = paramContext;
    if (Build.VERSION.SDK_INT > 10) {
      localObject = new ContextThemeWrapper(paramContext, 16973939);
    }
    return localObject;
  }
  
  public static void deleteIntent(Context paramContext, String paramString)
  {
    sp(paramContext).edit().remove(paramString).commit();
  }
  
  private static LinearLayout getAddSynonymRootLayout(Context paramContext, String paramString, ChangeListener paramChangeListener)
  {
    paramContext = contextTheme(paramContext);
    LinearLayout localLinearLayout = new LinearLayout(paramContext);
    EditText localEditText1 = new EditText(paramContext);
    EditText localEditText2 = new EditText(paramContext);
    Button localButton = new Button(paramContext);
    localLinearLayout.setOrientation(1);
    localEditText1.setHint("Synonym");
    localEditText2.setHint("Which command should this trigger");
    if (paramString != null) {
      localEditText1.setText(paramString);
    }
    localButton.setText("Save");
    localButton.setOnClickListener(new SaveIntentClickListener(paramContext, localEditText1, localEditText2, paramChangeListener));
    localLinearLayout.addView(localEditText1);
    localLinearLayout.addView(localEditText2);
    localLinearLayout.addView(localButton);
    localLinearLayout.setPadding(32, 32, 32, 32);
    return localLinearLayout;
  }
  
  public static Map<String, String> getAllIntents(Context paramContext)
  {
    return sp(paramContext).getAll();
  }
  
  private static LinearLayout getDialogRootLayout(Context paramContext, String paramString1, String paramString2, ChangeListener paramChangeListener)
  {
    paramContext = contextTheme(paramContext);
    LinearLayout localLinearLayout = new LinearLayout(paramContext);
    EditText localEditText1 = new EditText(paramContext);
    EditText localEditText2 = new EditText(paramContext);
    Button localButton = new Button(paramContext);
    TextView localTextView = new TextView(paramContext);
    localLinearLayout.setOrientation(1);
    localEditText1.setHint("text to trigger the intent");
    localEditText2.setHint("intent URI that performs the action");
    if (paramString1 != null) {
      localEditText1.setText(paramString1);
    }
    if (paramString2 != null) {
      localEditText2.setText(paramString2);
    }
    localButton.setText("Save");
    localButton.setOnClickListener(new SaveIntentClickListener(paramContext, localEditText1, localEditText2, paramChangeListener));
    localTextView.setText("  examples...");
    localTextView.setTextColor(Color.parseColor("#0099cc"));
    localTextView.setOnClickListener(new ExamplesDialog(paramContext, paramChangeListener));
    localLinearLayout.addView(localEditText1);
    localLinearLayout.addView(localEditText2);
    localLinearLayout.addView(localButton);
    localLinearLayout.addView(localTextView);
    localLinearLayout.setPadding(32, 32, 32, 32);
    return localLinearLayout;
  }
  
  private static LinearLayout getOpenAppIntentComposerRootLayout(final Context paramContext, String paramString, final ChangeListener paramChangeListener)
  {
    paramContext = contextTheme(paramContext);
    LinearLayout localLinearLayout = new LinearLayout(paramContext);
    final EditText localEditText = new EditText(paramContext);
    Spinner localSpinner = new Spinner(paramContext);
    Button localButton = new Button(paramContext);
    localSpinner.setAdapter(new InstalledAppsAdapter(paramContext, null));
    localLinearLayout.setOrientation(1);
    localEditText.setHint("Alias");
    if (paramString != null) {
      localEditText.setText(paramString);
    }
    localEditText.setTextSize(getTextSize());
    localButton.setText("Save");
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = ((ApplicationInfo)this.val$apps.getSelectedItem()).packageName;
        paramAnonymousView = paramContext.getPackageManager().getLaunchIntentForPackage(paramAnonymousView);
        paramAnonymousView.setFlags(268435456);
        CrowdBrain.saveIntent(paramContext, localEditText.getText().toString(), paramAnonymousView.toUri(0), paramChangeListener);
        Toast.makeText(paramContext, "Saved", 0).show();
      }
    });
    localLinearLayout.addView(localEditText);
    localLinearLayout.addView(localSpinner);
    localLinearLayout.addView(localButton);
    localLinearLayout.setPadding(32, 32, 32, 32);
    return localLinearLayout;
  }
  
  private static LinearLayout getSearchIntentComposerRootLayout(final Context paramContext, String paramString, final ChangeListener paramChangeListener)
  {
    paramContext = contextTheme(paramContext);
    LinearLayout localLinearLayout = new LinearLayout(paramContext);
    final EditText localEditText1 = new EditText(paramContext);
    Spinner localSpinner = new Spinner(paramContext);
    final EditText localEditText2 = new EditText(paramContext);
    Button localButton = new Button(paramContext);
    localSpinner.setAdapter(new InstalledAppsAdapter(paramContext, new Intent("android.intent.action.SEARCH")));
    localLinearLayout.setOrientation(1);
    localEditText1.setHint("Command");
    if (paramString != null) {
      localEditText1.setText(paramString);
    }
    localEditText1.setTextSize(getTextSize());
    localEditText2.setHint("Search term");
    localEditText2.setTextSize(getTextSize());
    localButton.setText("Save");
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent();
        paramAnonymousView.setAction("android.intent.action.SEARCH");
        paramAnonymousView.setPackage(((ApplicationInfo)this.val$apps.getSelectedItem()).packageName);
        paramAnonymousView.putExtra("query", localEditText2.getText().toString());
        paramAnonymousView.setFlags(268435456);
        CrowdBrain.saveIntent(paramContext, localEditText1.getText().toString(), paramAnonymousView.toUri(0), paramChangeListener);
        Toast.makeText(paramContext, "Saved", 0).show();
      }
    });
    localLinearLayout.addView(localEditText1);
    localLinearLayout.addView(localSpinner);
    localLinearLayout.addView(localEditText2);
    localLinearLayout.addView(localButton);
    localLinearLayout.setPadding(32, 32, 32, 32);
    return localLinearLayout;
  }
  
  private static LinearLayout getSendTextIntentComposerRootLayout(final Context paramContext, String paramString, final ChangeListener paramChangeListener)
  {
    paramContext = contextTheme(paramContext);
    LinearLayout localLinearLayout = new LinearLayout(paramContext);
    final EditText localEditText1 = new EditText(paramContext);
    final Spinner localSpinner = new Spinner(paramContext);
    EditText localEditText2 = new EditText(paramContext);
    Button localButton = new Button(paramContext);
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localSpinner.setAdapter(new InstalledAppsAdapter(paramContext, localIntent));
    localLinearLayout.setOrientation(1);
    localEditText1.setHint("Command");
    if (paramString != null) {
      localEditText1.setText(paramString);
    }
    localEditText1.setTextSize(getTextSize());
    localEditText2.setHint("Text to send");
    localEditText2.setTextSize(getTextSize());
    localButton.setText("Save");
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent();
        paramAnonymousView.setAction("android.intent.action.SEND");
        paramAnonymousView.putExtra("android.intent.extra.TEXT", this.val$query.getText().toString());
        paramAnonymousView.setType("text/plain");
        paramAnonymousView.setPackage(((ApplicationInfo)localSpinner.getSelectedItem()).packageName);
        paramAnonymousView.setFlags(268435456);
        CrowdBrain.saveIntent(paramContext, localEditText1.getText().toString(), paramAnonymousView.toUri(0), paramChangeListener);
        Toast.makeText(paramContext, "Saved", 0).show();
      }
    });
    localLinearLayout.addView(localEditText1);
    localLinearLayout.addView(localSpinner);
    localLinearLayout.addView(localEditText2);
    localLinearLayout.addView(localButton);
    localLinearLayout.setPadding(32, 32, 32, 32);
    return localLinearLayout;
  }
  
  private static float getTextSize()
  {
    return 20.0F;
  }
  
  private static void newFreeFormIntent(Context paramContext, String paramString1, String paramString2, ChangeListener paramChangeListener)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(contextTheme(paramContext));
    localBuilder.setView(getDialogRootLayout(paramContext, paramString1, paramString2, paramChangeListener));
    localBuilder.show();
  }
  
  private static void newIntentDialog(Context paramContext, ViewGroup paramViewGroup)
  {
    paramContext = new AlertDialog.Builder(contextTheme(paramContext));
    paramContext.setView(paramViewGroup);
    paramContext.show();
  }
  
  private static void oldShortcutMenu(Context paramContext, final String paramString, final ChangeListener paramChangeListener)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(contextTheme(paramContext));
    localBuilder.setTitle("What type of action would you like to teach me?");
    paramContext = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 0: 
          CrowdBrain.newIntentDialog(this.val$context, CrowdBrain.access$000(this.val$context, paramString, paramChangeListener));
          return;
        case 1: 
          CrowdBrain.newIntentDialog(this.val$context, CrowdBrain.access$200(this.val$context, paramString, paramChangeListener));
          return;
        case 2: 
          CrowdBrain.newIntentDialog(this.val$context, CrowdBrain.access$300(this.val$context, paramString, paramChangeListener));
          return;
        case 3: 
          CrowdBrain.newIntentDialog(this.val$context, CrowdBrain.access$400(this.val$context, paramString, paramChangeListener));
          return;
        }
        CrowdBrain.newFreeFormIntent(this.val$context, paramString, null, paramChangeListener);
      }
    };
    localBuilder.setItems(new CharSequence[] { "Add synonym", "Open an app", "Search inside an app", "Share text to an app", "Advanced" }, paramContext);
    localBuilder.create().show();
  }
  
  public static void saveIntent(Context paramContext, String paramString1, String paramString2, ChangeListener paramChangeListener)
  {
    sp(paramContext).edit().putString(paramString1, paramString2).commit();
    if (paramChangeListener != null) {
      paramChangeListener.onNewIntentAdded(new AbstractMap.SimpleEntry(paramString1, paramString2));
    }
  }
  
  static void shortcutMenu(Context paramContext, String paramString, final ChangeListener paramChangeListener)
  {
    Context localContext = contextTheme(paramContext);
    paramContext = new LinearLayout(localContext);
    paramContext.setOrientation(1);
    paramContext.setPadding(32, 32, 32, 32);
    Button localButton1 = new Button(localContext);
    Button localButton2 = new Button(localContext);
    Button localButton3 = new Button(localContext);
    Button localButton4 = new Button(localContext);
    Button localButton5 = new Button(localContext);
    final EditText localEditText = new EditText(localContext);
    if (paramString != null)
    {
      localEditText.setText(paramString);
      localEditText.setTextSize(getTextSize());
      localEditText.setHint("Command");
      paramContext.addView(localEditText);
    }
    paramString = new ArrayList(Arrays.asList(new Button[] { localButton1, localButton2, localButton3, localButton4, localButton5 }));
    localButton1.setText("Add synonym");
    localButton2.setText("Open an app");
    localButton3.setText("Search inside an app");
    localButton4.setText("Share text to an app");
    localButton5.setText("Advanced");
    localButton1.setCompoundDrawablesWithIntrinsicBounds(localContext.getResources().getDrawable(17301566), null, null, null);
    localButton2.setCompoundDrawablesWithIntrinsicBounds(localContext.getResources().getDrawable(17301585), null, null, null);
    localButton3.setCompoundDrawablesWithIntrinsicBounds(localContext.getResources().getDrawable(17301583), null, null, null);
    localButton4.setCompoundDrawablesWithIntrinsicBounds(localContext.getResources().getDrawable(17301586), null, null, null);
    localButton5.setCompoundDrawablesWithIntrinsicBounds(localContext.getResources().getDrawable(17301577), null, null, null);
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CrowdBrain.newIntentDialog(this.val$finalContext, CrowdBrain.access$000(this.val$finalContext, localEditText.getText().toString(), paramChangeListener));
      }
    });
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CrowdBrain.newIntentDialog(this.val$finalContext, CrowdBrain.access$200(this.val$finalContext, localEditText.getText().toString(), paramChangeListener));
      }
    });
    localButton3.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CrowdBrain.newIntentDialog(this.val$finalContext, CrowdBrain.access$300(this.val$finalContext, localEditText.getText().toString(), paramChangeListener));
      }
    });
    localButton4.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CrowdBrain.newIntentDialog(this.val$finalContext, CrowdBrain.access$400(this.val$finalContext, localEditText.getText().toString(), paramChangeListener));
      }
    });
    localButton5.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CrowdBrain.newFreeFormIntent(this.val$finalContext, localEditText.getText().toString(), null, paramChangeListener);
      }
    });
    paramString = paramString.iterator();
    while (paramString.hasNext())
    {
      paramChangeListener = (Button)paramString.next();
      paramChangeListener.setGravity(19);
      paramChangeListener.setCompoundDrawablePadding(8);
      paramContext.addView(paramChangeListener);
    }
    paramString = new AlertDialog.Builder(contextTheme(localContext));
    paramString.setView(paramContext);
    paramString.create().show();
  }
  
  private static SharedPreferences sp(Context paramContext)
  {
    return paramContext.getSharedPreferences("CROWD_BRAIN_SHARED_PREFS", 0);
  }
  
  public static abstract interface ChangeListener
  {
    public abstract void onNewIntentAdded(Map.Entry<String, String> paramEntry);
  }
  
  private static class ExamplesDialog
    implements View.OnClickListener
  {
    CrowdBrain.ChangeListener changeListener;
    Context context;
    
    public ExamplesDialog(Context paramContext, CrowdBrain.ChangeListener paramChangeListener)
    {
      this.context = paramContext;
      this.changeListener = paramChangeListener;
    }
    
    public void onClick(View paramView)
    {
      paramView = new AlertDialog.Builder(CrowdBrain.contextTheme(this.context));
      paramView.setTitle("Sample intents");
      final String[] arrayOfString = new String[3];
      arrayOfString[0] = "show calc";
      arrayOfString[1] = "Tell my girlfriend I love her";
      arrayOfString[2] = "play cat videos on youtube";
      paramView.setItems(arrayOfString, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface = new Intent();
          paramAnonymousDialogInterface.setAction("android.intent.action.MAIN");
          paramAnonymousDialogInterface.addCategory("android.intent.category.LAUNCHER");
          paramAnonymousDialogInterface.setPackage("com.android.calculator2");
          Intent localIntent1 = new Intent("android.intent.action.VIEW", Uri.parse("sms:1234567890"));
          localIntent1.putExtra("sms_body", "I LOVE YOU");
          Intent localIntent2 = new Intent("android.intent.action.SEARCH");
          localIntent2.setPackage("com.google.android.youtube");
          localIntent2.putExtra("query", "cats");
          switch (paramAnonymousInt)
          {
          default: 
            return;
          case 0: 
            CrowdBrain.newFreeFormIntent(CrowdBrain.ExamplesDialog.this.context, arrayOfString[paramAnonymousInt], paramAnonymousDialogInterface.toUri(0), CrowdBrain.ExamplesDialog.this.changeListener);
            return;
          case 1: 
            CrowdBrain.newFreeFormIntent(CrowdBrain.ExamplesDialog.this.context, arrayOfString[paramAnonymousInt], localIntent1.toUri(0), CrowdBrain.ExamplesDialog.this.changeListener);
            return;
          }
          CrowdBrain.newFreeFormIntent(CrowdBrain.ExamplesDialog.this.context, arrayOfString[paramAnonymousInt], localIntent2.toUri(0), CrowdBrain.ExamplesDialog.this.changeListener);
        }
      });
      paramView.create().show();
    }
  }
  
  private static class InstalledAppsAdapter
    extends BaseAdapter
    implements SpinnerAdapter
  {
    List<ApplicationInfo> applicationInfoList = new ArrayList();
    PackageManager pm;
    
    InstalledAppsAdapter(Context paramContext, Intent paramIntent)
    {
      this.pm = paramContext.getPackageManager();
      if (paramIntent != null)
      {
        paramContext = this.pm.queryIntentActivities(paramIntent, 65536).iterator();
        while (paramContext.hasNext())
        {
          paramIntent = ((ResolveInfo)paramContext.next()).activityInfo.applicationInfo;
          this.applicationInfoList.add(paramIntent);
        }
      }
      paramIntent = this.pm.getInstalledApplications(128).iterator();
      while (paramIntent.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramIntent.next();
        if (paramContext.getPackageManager().getLaunchIntentForPackage(localApplicationInfo.packageName) != null) {
          this.applicationInfoList.add(localApplicationInfo);
        }
      }
    }
    
    public int getCount()
    {
      return this.applicationInfoList.size();
    }
    
    public ApplicationInfo getItem(int paramInt)
    {
      return (ApplicationInfo)this.applicationInfoList.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Object localObject;
      if (paramView == null)
      {
        localObject = new CrowdBrain.ViewHolder();
        paramViewGroup = paramViewGroup.getContext();
        ((CrowdBrain.ViewHolder)localObject).linearLayout = new LinearLayout(paramViewGroup);
        ((CrowdBrain.ViewHolder)localObject).linearLayout.setOrientation(0);
        paramView = ((CrowdBrain.ViewHolder)localObject).linearLayout;
        ((CrowdBrain.ViewHolder)localObject).iconView = new ImageView(paramViewGroup);
        ((CrowdBrain.ViewHolder)localObject).nameView = new TextView(paramViewGroup);
        ((CrowdBrain.ViewHolder)localObject).linearLayout.addView(((CrowdBrain.ViewHolder)localObject).iconView);
        ((CrowdBrain.ViewHolder)localObject).linearLayout.addView(((CrowdBrain.ViewHolder)localObject).nameView);
        paramView.setTag(localObject);
      }
      for (paramViewGroup = (ViewGroup)localObject;; paramViewGroup = (CrowdBrain.ViewHolder)paramView.getTag())
      {
        localObject = (ApplicationInfo)this.applicationInfoList.get(paramInt);
        paramViewGroup.iconView.setImageDrawable(((ApplicationInfo)localObject).loadIcon(this.pm));
        paramViewGroup.nameView.setText(((ApplicationInfo)localObject).loadLabel(this.pm));
        paramViewGroup.nameView.setTextSize(CrowdBrain.access$700());
        return paramView;
      }
    }
  }
  
  private static class SaveIntentClickListener
    implements View.OnClickListener
  {
    CrowdBrain.ChangeListener changeListener;
    private final EditText command;
    Context ctx;
    private final EditText intent;
    
    public SaveIntentClickListener(Context paramContext, EditText paramEditText1, EditText paramEditText2, CrowdBrain.ChangeListener paramChangeListener)
    {
      this.command = paramEditText1;
      this.intent = paramEditText2;
      this.ctx = paramContext;
      this.changeListener = paramChangeListener;
    }
    
    public void onClick(View paramView)
    {
      CrowdBrain.saveIntent(this.ctx, this.command.getText().toString(), this.intent.getText().toString(), this.changeListener);
      Toast.makeText(this.ctx, "Saved", 0).show();
    }
  }
  
  static class ViewHolder
  {
    ImageView iconView;
    LinearLayout linearLayout;
    TextView nameView;
    
    ViewHolder() {}
  }
}
