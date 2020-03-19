package com.obreey.bookviewer.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.abbyy.mobile.lingvo.api.TranslationContract.Intents;
import com.abbyy.mobile.lingvo.api.TranslationContract.Translations.UriBuilder;
import com.obreey.books.GA_TrackerCommands;
import com.obreey.books.GA_TrackerName;
import com.obreey.books.GlobalUtils;
import com.obreey.books.Log;
import com.obreey.bookviewer.R.drawable;
import com.obreey.bookviewer.R.id;
import com.obreey.bookviewer.R.layout;
import com.obreey.bookviewer.R.string;
import com.obreey.bookviewer.ReaderActivity;
import com.obreey.bookviewer.ReaderActivity.ReaderActivityHandler;
import com.obreey.bookviewer.ReaderContext;
import com.obreey.bookviewer.ReaderFrame;
import com.obreey.bookviewer.ReaderView;
import com.obreey.bookviewer.lib.DisplayParams;
import com.obreey.bookviewer.lib.ScrManager;
import com.obreey.bookviewer.lib.ScrSelection;
import com.obreey.bookviewer.lib.SelectionType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import net.apps.ui.theme.android.ProxyResources;
import net.apps.ui.theme.android.dialog.AndroidDialog;

public class TranslateDialog
  extends AndroidDialog
  implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener
{
  static final int CURSOR_FOR_TRANSLATIONS = 1;
  private static String filter_source_language;
  private static String filter_target_language;
  private View btn_cancel;
  private View btn_pin;
  private DirectionAdapter direction_adapter;
  private final int[] lingvo_fail_count = { 0 };
  private TranslationAdapter lst_adapter;
  private ListView lst_translations;
  private Spinner sp_translate_direction;
  private String temp_source_language;
  private String temp_target_language;
  private String text_to_translate = "";
  private TextView tv_empty_view;
  private TextView tv_text_to_translate;
  
  public TranslateDialog()
  {
    super(R.layout.translate_dialog);
  }
  
  private Uri createTranslationUri(String paramString)
  {
    return new TranslationContract.Translations.UriBuilder(paramString).setSourceLanguage("").setTargetLanguage("").setForceLemmatization(false).setEnablePrefixVariants(false).setEnableSuggestions(false).setEnableInverseLookup(false).setTranslateSuggestions(false).setTranslateVariants(false).build();
  }
  
  private void runExternalTranslation()
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.TEXT", this.text_to_translate);
      if (!TextUtils.isEmpty(filter_source_language)) {
        localIntent.putExtra("android.intent.extra.translate.FROM_LANGUAGE", filter_source_language);
      }
      if (!TextUtils.isEmpty(filter_target_language)) {
        localIntent.putExtra("android.intent.extra.translate.TO_LANGUAGE", filter_target_language);
      }
      startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      Log.e("translation", localException, "Cannot open translation activity", new Object[0]);
    }
  }
  
  private void setFilter(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (filter_source_language == null) && (paramString2 == null) && (filter_target_language == null)) {
      return;
    }
    if (this.lst_translations != null) {
      this.lst_adapter.setFilter(paramString1, paramString2);
    }
    if ((this.temp_source_language != null) && (this.temp_source_language.equals(paramString1)) && (this.temp_target_language != null) && (this.temp_target_language.equals(paramString2)))
    {
      this.temp_source_language = null;
      this.temp_target_language = null;
      return;
    }
    filter_source_language = paramString1;
    filter_target_language = paramString2;
  }
  
  private void setupView(boolean paramBoolean)
  {
    DialogManager localDialogManager = (DialogManager)getDlgMgr();
    if (localDialogManager != null) {
      localDialogManager.ract.isTranslationPinned();
    }
    if (this.btn_pin != null) {
      this.btn_pin.setVisibility(8);
    }
    if (paramBoolean)
    {
      localObject = getDialog().getWindow().getAttributes();
      ((WindowManager.LayoutParams)localObject).width = getDesiredWidth();
      ((WindowManager.LayoutParams)localObject).height = getDesiredHeight();
      int i = getGravity();
      ((WindowManager.LayoutParams)localObject).x = 0;
      ((WindowManager.LayoutParams)localObject).y = 0;
      ((WindowManager.LayoutParams)localObject).gravity = i;
      getDialog().getWindow().setAttributes((WindowManager.LayoutParams)localObject);
    }
    localDialogManager.ract.frame.setupScreenSize();
    Object localObject = new DisplayParams();
    ((DisplayParams)localObject).loadFromDocProps();
    ReaderView localReaderView = localDialogManager.ract.frame.getPrimaryReader();
    if (localReaderView != null) {
      ((DisplayParams)localObject).init_location = localReaderView.smgr.getCurrentLocation(false);
    }
    localDialogManager.ract.frame.createReaderView((DisplayParams)localObject);
  }
  
  public int getDesiredHeight()
  {
    DialogManager localDialogManager = (DialogManager)getDlgMgr();
    if ((localDialogManager != null) && (localDialogManager.ract.isTranslationPinned())) {
      return (int)(localDialogManager.DISPLAY_HEIGHT * 0.4F);
    }
    return -2;
  }
  
  public int getDesiredWidth()
  {
    DialogManager localDialogManager = (DialogManager)getDlgMgr();
    if ((localDialogManager != null) && (localDialogManager.ract.isTranslationPinned())) {
      return -1;
    }
    return localDialogManager.DISPLAY_WIDTH - localDialogManager.CELL_SIZE * 2;
  }
  
  public int getGravity()
  {
    DialogManager localDialogManager = (DialogManager)getDlgMgr();
    if ((localDialogManager != null) && (localDialogManager.ract.isTranslationPinned())) {
      return 87;
    }
    return 49;
  }
  
  public void hideDialog()
  {
    super.hideDialog();
    this.lst_translations.setVisibility(8);
  }
  
  public boolean isMovable()
  {
    DialogManager localDialogManager = (DialogManager)getDlgMgr();
    return (localDialogManager == null) || (!localDialogManager.ract.isTranslationPinned());
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    if ((paramBundle != null) && (TextUtils.isEmpty(this.text_to_translate))) {
      this.text_to_translate = paramBundle.getString("translate-text");
    }
    if (this.text_to_translate == null) {
      this.text_to_translate = "";
    }
    getActivity().getSupportLoaderManager().initLoader(1, null, this);
  }
  
  public void onClick(View paramView)
  {
    DialogManager localDialogManager = (DialogManager)getDlgMgr();
    if (localDialogManager == null) {
      return;
    }
    if (paramView.getId() == 16908292)
    {
      runExternalTranslation();
      return;
    }
    if (paramView.getId() == R.id.cmd_cancel)
    {
      if (localDialogManager.ract.isTranslationPinned())
      {
        localDialogManager.ract.setTranslationPinned(false);
        ReaderContext.cleanScrContext();
        setupView(false);
      }
      close();
      return;
    }
    if (paramView.getId() == R.id.cmd_pin)
    {
      localDialogManager.ract.setTranslationPinned(localDialogManager.ract.isTranslationPinned() ^ true);
      setupView(true);
      return;
    }
  }
  
  public Loader<Cursor> onCreateLoader(int paramInt, Bundle paramBundle)
  {
    if (paramInt == 1)
    {
      if (paramBundle != null) {
        paramBundle = paramBundle.getString("text_to_translate");
      } else {
        paramBundle = this.text_to_translate;
      }
      String[] arrayOfString = new String[9];
      arrayOfString[0] = "_id";
      arrayOfString[1] = "heading";
      arrayOfString[2] = "translation";
      arrayOfString[3] = "dictionary";
      arrayOfString[4] = "language_from";
      arrayOfString[5] = "language_to";
      arrayOfString[6] = "article_uri";
      arrayOfString[7] = "dictionary_article_uri";
      arrayOfString[8] = "sound_uri";
      if (this.lst_translations != null) {
        this.lst_adapter.clear();
      }
      if (this.sp_translate_direction != null)
      {
        this.sp_translate_direction.setOnItemSelectedListener(null);
        this.direction_adapter.clear();
      }
      if (this.tv_empty_view != null) {
        this.tv_empty_view.setText(getActivity().getString(R.string.msg_transl_searching, new Object[] { paramBundle }));
      }
      if ((!TextUtils.isEmpty(paramBundle)) && (TextUtils.isGraphic(paramBundle)))
      {
        paramBundle = createTranslationUri(paramBundle);
        return new TranslationsCursorLoader(this.lingvo_fail_count, getActivity(), paramBundle, arrayOfString);
      }
      return new TranslationsCursorLoader(this.lingvo_fail_count, getActivity(), null, arrayOfString);
    }
    throw new IllegalArgumentException();
  }
  
  public void onDestroyView()
  {
    this.sp_translate_direction = null;
    this.direction_adapter = null;
    this.tv_text_to_translate = null;
    this.lst_translations = null;
    this.lst_adapter = null;
    this.tv_empty_view = null;
    this.btn_cancel = null;
    this.btn_pin = null;
    super.onDestroyView();
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramAdapterView == this.lst_translations)
    {
      paramAdapterView = (TranslationInfo)this.lst_translations.getAdapter().getItem(paramInt);
      if ((paramAdapterView != null) && (!TextUtils.isEmpty(paramAdapterView.lingvo_uri)))
      {
        TranslationContract.Intents.openArticle(paramView.getContext(), paramAdapterView.lingvo_uri);
        return;
      }
      runExternalTranslation();
    }
  }
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramAdapterView == this.sp_translate_direction)
    {
      paramAdapterView = (TransDirInfo)paramAdapterView.getAdapter().getItem(paramInt);
      if (paramAdapterView != null) {
        setFilter(paramAdapterView.source_language, paramAdapterView.target_language);
      }
    }
  }
  
  public void onLoadFinished(final Loader<Cursor> paramLoader, final Cursor paramCursor)
  {
    paramCursor = (ReaderActivity)getActivity();
    if (paramLoader.getId() == 1)
    {
      if (this.lst_translations != null)
      {
        if (this.sp_translate_direction == null) {
          return;
        }
        this.sp_translate_direction.setOnItemSelectedListener(null);
        Object localObject5 = (TranslationsCursorLoader)paramLoader;
        paramLoader = this.lst_adapter;
        DirectionAdapter localDirectionAdapter = (DirectionAdapter)this.sp_translate_direction.getAdapter();
        paramLoader.clear();
        localDirectionAdapter.clear();
        Object localObject1 = ((TranslationsCursorLoader)localObject5).translations;
        int n = 0;
        int i1 = 0;
        int j;
        int i;
        Object localObject2;
        Object localObject3;
        if (localObject1 == null)
        {
          if (paramCursor != null)
          {
            localObject1 = paramCursor.getPackageManager().getInstalledApplications(0).iterator();
            j = 0;
            i = 0;
            for (;;)
            {
              m = j;
              k = i;
              if (!((Iterator)localObject1).hasNext()) {
                break;
              }
              localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
              if (((ApplicationInfo)localObject2).packageName.equals("com.abbyy.mobile.lingvo.market")) {
                j = 1;
              } else if (((ApplicationInfo)localObject2).packageName.equals("com.android.vending")) {
                i = 1;
              }
            }
          }
          int m = 0;
          int k = 0;
          if (m == 0)
          {
            setFilter(null, null);
            this.tv_text_to_translate.setText("");
            this.tv_empty_view.setLinksClickable(true);
            this.tv_empty_view.setMovementMethod(LinkMovementMethod.getInstance());
            if (k != 0) {
              paramLoader = "market://details?id=com.abbyy.mobile.lingvo.market&referrer=utm_source%3Dpocketbook_reader%26utm_medium%3Dapp";
            } else {
              paramLoader = "https://play.google.com/store/apps/details?id=com.abbyy.mobile.lingvo.market&referrer=utm_source%3Dpocketbook_reader%26utm_medium%3Dapp";
            }
            localObject1 = getString(R.string.msg_no_abbyy_lingvo);
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("<a href=\"");
            ((StringBuilder)localObject2).append(paramLoader);
            ((StringBuilder)localObject2).append("\">");
            ((StringBuilder)localObject2).append((String)localObject1);
            ((StringBuilder)localObject2).append("</a>");
            localObject1 = new SpannableStringBuilder(Html.fromHtml(((StringBuilder)localObject2).toString()));
            localObject2 = (UnderlineSpan[])((SpannableStringBuilder)localObject1).getSpans(0, 10, UnderlineSpan.class);
            j = localObject2.length;
            i = i1;
            while (i < j)
            {
              localObject3 = localObject2[i];
              k = ((SpannableStringBuilder)localObject1).getSpanStart(localObject3);
              m = ((SpannableStringBuilder)localObject1).getSpanEnd(localObject3);
              n = ((SpannableStringBuilder)localObject1).getSpanFlags(localObject3);
              ((SpannableStringBuilder)localObject1).setSpan(new ClickableSpan()
              {
                public void onClick(View paramAnonymousView)
                {
                  if (paramCursor != null)
                  {
                    paramAnonymousView = paramCursor.getPackageManager();
                    ComponentName localComponentName = new ComponentName(paramCursor.getPackageName(), "com.obreey.reader.InstallApplicationReceiver");
                    if (paramAnonymousView.getComponentEnabledSetting(localComponentName) == 2) {
                      paramAnonymousView.setComponentEnabledSetting(localComponentName, 1, 1);
                    }
                    paramAnonymousView = new Intent("android.intent.action.VIEW");
                    paramAnonymousView.addCategory("android.intent.category.BROWSABLE");
                    paramAnonymousView.addCategory("android.intent.category.DEFAULT");
                    paramAnonymousView.setData(Uri.parse(paramLoader));
                  }
                  try
                  {
                    GA_TrackerCommands.applicationEvent(GA_TrackerName.Reader_LingvoInstallation, "clicked");
                    paramCursor.startActivity(paramAnonymousView);
                    GlobalUtils.saveTimeAbbyyLingvoClick(System.currentTimeMillis());
                    return;
                  }
                  catch (ActivityNotFoundException paramAnonymousView) {}
                }
              }, k, m, n);
              i += 1;
            }
            this.tv_empty_view.setText((CharSequence)localObject1);
            return;
          }
        }
        if ((((TranslationsCursorLoader)localObject5).error == null) && (((TranslationsCursorLoader)localObject5).translations != null))
        {
          if (((TranslationsCursorLoader)localObject5).translations.isEmpty())
          {
            this.tv_empty_view.setLinksClickable(false);
            this.tv_empty_view.setText(getActivity().getString(R.string.msg_transl_nothing_found, new Object[] { this.text_to_translate }));
            update();
            return;
          }
          paramLoader.setTranslations(((TranslationsCursorLoader)localObject5).translations);
          localObject3 = filter_source_language;
          localObject2 = filter_target_language;
          TreeSet localTreeSet = new TreeSet();
          localObject1 = ((TranslationsCursorLoader)localObject5).translations.iterator();
          for (paramLoader = null; ((Iterator)localObject1).hasNext(); paramLoader = paramCursor)
          {
            label530:
            localObject4 = (TranslationInfo)((Iterator)localObject1).next();
            paramCursor = new TransDirInfo(((TranslationInfo)localObject4).source_language, ((TranslationInfo)localObject4).target_language);
            localTreeSet.add(paramCursor);
            if ((paramLoader != null) || (localObject3 == null) || (!((String)localObject3).equals(((TranslationInfo)localObject4).source_language)) || (localObject2 == null) || (!((String)localObject2).equals(((TranslationInfo)localObject4).target_language))) {
              break label530;
            }
          }
          if (paramLoader == null)
          {
            localObject3 = null;
            localObject2 = localObject3;
          }
          localObject1 = localObject2;
          Object localObject4 = paramLoader;
          paramCursor = (Cursor)localObject3;
          if (paramLoader == null)
          {
            String str = Locale.getDefault().getLanguage();
            localObject5 = ((TranslationsCursorLoader)localObject5).translations.iterator();
            do
            {
              localObject1 = localObject2;
              localObject4 = paramLoader;
              paramCursor = (Cursor)localObject3;
              if (!((Iterator)localObject5).hasNext()) {
                break;
              }
              localObject1 = (TranslationInfo)((Iterator)localObject5).next();
            } while (!((TranslationInfo)localObject1).target_language.equals(str));
            localObject4 = new TransDirInfo(((TranslationInfo)localObject1).source_language, ((TranslationInfo)localObject1).target_language);
            paramCursor = ((TranslationInfo)localObject1).source_language;
            localObject1 = ((TranslationInfo)localObject1).target_language;
          }
          paramLoader = localTreeSet.iterator();
          while (paramLoader.hasNext()) {
            localDirectionAdapter.add((TransDirInfo)paramLoader.next());
          }
          if (localObject4 != null)
          {
            i = 0;
            for (;;)
            {
              localObject2 = localObject1;
              j = n;
              paramLoader = paramCursor;
              if (i >= localDirectionAdapter.getCount()) {
                break;
              }
              if (((TransDirInfo)localDirectionAdapter.getItem(i)).equals(localObject4))
              {
                localObject2 = localObject1;
                j = i;
                paramLoader = paramCursor;
                break;
              }
              i += 1;
            }
          }
          paramLoader = ((TransDirInfo)localTreeSet.first()).source_language;
          localObject2 = ((TransDirInfo)localTreeSet.first()).target_language;
          j = n;
          if ((filter_source_language != null) && (filter_target_language != null))
          {
            if ((!filter_source_language.equals(paramLoader)) || (!filter_target_language.equals(localObject2)))
            {
              this.temp_source_language = paramLoader;
              this.temp_target_language = ((String)localObject2);
            }
          }
          else {
            setFilter(paramLoader, (String)localObject2);
          }
          this.sp_translate_direction.setSelection(j);
          this.sp_translate_direction.setOnItemSelectedListener(this);
          update();
          return;
        }
        setFilter(null, null);
        if (this.lingvo_fail_count[0] <= 3)
        {
          paramLoader = paramCursor.handler.obtainMessage(22, this.text_to_translate);
          paramCursor.handler.sendMessageDelayed(paramLoader, 1500L);
        }
        else
        {
          this.tv_empty_view.setLinksClickable(false);
          if (((TranslationsCursorLoader)localObject5).error == null) {
            this.tv_empty_view.setText(getActivity().getString(R.string.err_transl_cannot_connect, new Object[] { Integer.valueOf(this.lingvo_fail_count[0]) }));
          } else {
            this.tv_empty_view.setText(getActivity().getString(R.string.err_transl_exception, new Object[] { Integer.valueOf(this.lingvo_fail_count[0]), ((TranslationsCursorLoader)localObject5).error }));
          }
        }
        ((TranslationsCursorLoader)localObject5).error = null;
        update();
        return;
      }
      return;
    }
  }
  
  public void onLoaderReset(Loader<Cursor> paramLoader) {}
  
  public void onNothingSelected(AdapterView<?> paramAdapterView)
  {
    if (paramAdapterView == this.sp_translate_direction) {
      setFilter(null, null);
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.text_to_translate != null) {
      paramBundle.putString("translate-text", this.text_to_translate);
    }
  }
  
  public void onStart()
  {
    if (this.tv_text_to_translate != null) {
      this.tv_text_to_translate.setText(getActivity().getString(R.string.msg_transl_text, new Object[] { this.text_to_translate }));
    }
    super.onStart();
    runTranslation();
  }
  
  public void onStop()
  {
    super.onStop();
    if (this.lst_translations != null) {
      this.lst_adapter.clear();
    }
    if (this.keep_state_on_stop) {}
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    paramBundle = (DialogManager)getDlgMgr();
    this.sp_translate_direction = ((Spinner)paramView.findViewById(R.id.sp_translate_direction));
    Object localObject = this.sp_translate_direction;
    DirectionAdapter localDirectionAdapter = new DirectionAdapter(paramView.getContext());
    this.direction_adapter = localDirectionAdapter;
    ((Spinner)localObject).setAdapter(localDirectionAdapter);
    this.tv_text_to_translate = new TextView(getActivity());
    this.lst_translations = ((ListView)paramView.findViewById(R.id.lst_translations));
    this.tv_empty_view = ((TextView)paramView.findViewById(16908292));
    localObject = paramView.getContext().getResources();
    if ((localObject instanceof ProxyResources))
    {
      localObject = (ProxyResources)localObject;
      ((ProxyResources)localObject).styleTextView("textviewTranslationEmpty", null, this.tv_empty_view);
      ((ProxyResources)localObject).styleTextView("textviewTranslationQuery", null, this.tv_text_to_translate);
    }
    boolean bool = TextUtils.isEmpty(this.text_to_translate);
    int i = 0;
    if (!bool) {
      this.tv_text_to_translate.setText(getActivity().getString(R.string.msg_transl_text, new Object[] { this.text_to_translate }));
    }
    this.tv_empty_view.setOnClickListener(this);
    this.btn_cancel = paramView.findViewById(R.id.cmd_cancel);
    if (this.btn_cancel != null) {
      this.btn_cancel.setOnClickListener(this);
    }
    this.btn_pin = paramView.findViewById(R.id.cmd_pin);
    if (this.btn_pin != null)
    {
      this.btn_pin.setOnClickListener(this);
      paramView = this.btn_pin;
      if (paramBundle.ract.isTranslationPinned()) {
        i = 8;
      }
      paramView.setVisibility(i);
    }
    this.lst_translations.addHeaderView(this.tv_text_to_translate);
    this.lst_translations.setHeaderDividersEnabled(true);
    paramView = this.lst_translations;
    paramBundle = new TranslationAdapter(getActivity());
    this.lst_adapter = paramBundle;
    paramView.setAdapter(paramBundle);
    this.lst_translations.setEmptyView(this.tv_empty_view);
    this.lst_translations.setOnItemClickListener(this);
  }
  
  void runTranslation()
  {
    DialogManager localDialogManager = (DialogManager)getDlgMgr();
    if (getActivity() == null)
    {
      close();
      return;
    }
    if (TextUtils.isEmpty(this.text_to_translate)) {
      return;
    }
    Bundle localBundle = new Bundle();
    localBundle.putString("text_to_translate", this.text_to_translate);
    localDialogManager.ract.getSupportLoaderManager().restartLoader(1, localBundle, this);
  }
  
  public void setTranslationText(String paramString, boolean paramBoolean)
  {
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    this.text_to_translate = str;
    if (this.tv_text_to_translate != null) {
      this.tv_text_to_translate.setText(getActivity().getString(R.string.msg_transl_text, new Object[] { this.text_to_translate }));
    }
    if (this.tv_empty_view != null) {
      this.tv_empty_view.setText(getActivity().getString(R.string.msg_transl_searching, new Object[] { this.text_to_translate }));
    }
    if (paramBoolean) {
      runTranslation();
    }
    update();
  }
  
  public void update()
  {
    Object localObject = (DialogManager)getDlgMgr();
    if (((DialogManager)localObject).ract.isTranslationPinned())
    {
      if (((DialogManager)localObject).ract.isPinnedHidden() != isDialogHidden()) {
        if (isDialogHidden()) {
          showDialog();
        } else {
          hideDialog();
        }
      }
      this.lst_translations.setVisibility(0);
    }
    if ((ReaderContext.ctx_dlg) && (ReaderContext.ctx_dlg_ssel != null) && (ReaderContext.ctx_dlg_ssel.type == SelectionType.GENERIC))
    {
      localObject = ReaderContext.ctx_dlg_ssel.getText();
      if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!((String)localObject).equals(this.text_to_translate))) {
        setTranslationText((String)localObject, true);
      }
    }
  }
  
  private static final class DirectionAdapter
    extends ArrayAdapter<TranslateDialog.TransDirInfo>
  {
    public DirectionAdapter(Context paramContext)
    {
      super(17367048, 16908308);
      setDropDownViewResource(17367049);
      setNotifyOnChange(true);
    }
  }
  
  private static class TransDirInfo
    implements Comparable<TransDirInfo>
  {
    final String source_language;
    final String target_language;
    
    TransDirInfo(String paramString1, String paramString2)
    {
      this.source_language = paramString1;
      this.target_language = paramString2;
    }
    
    static String iso2str(String paramString)
    {
      if ((paramString != null) && (paramString.length() != 0) && (!paramString.equals("?")) && (!paramString.equals("*"))) {}
      try
      {
        String str = new Locale(paramString).getDisplayName();
        return str;
      }
      catch (Exception localException) {}
      return "Any";
      return paramString;
    }
    
    public int compareTo(TransDirInfo paramTransDirInfo)
    {
      if (this == paramTransDirInfo) {
        return 0;
      }
      int i = this.source_language.compareTo(paramTransDirInfo.source_language);
      if (i != 0) {
        return i;
      }
      return this.target_language.compareTo(paramTransDirInfo.target_language);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof TransDirInfo)) {
        return false;
      }
      paramObject = (TransDirInfo)paramObject;
      if (!this.source_language.equals(paramObject.source_language)) {
        return false;
      }
      return this.target_language.equals(paramObject.target_language);
    }
    
    public int hashCode()
    {
      return (this.source_language.hashCode() + 31) * 31 + this.target_language.hashCode();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(iso2str(this.source_language));
      localStringBuilder.append(" - ");
      localStringBuilder.append(iso2str(this.target_language));
      return localStringBuilder.toString();
    }
  }
  
  private static final class TranslationAdapter
    extends ArrayAdapter<TranslateDialog.TranslationInfo>
  {
    private ArrayList<TranslateDialog.TranslationInfo> allTranslations;
    private String dst_filter;
    private LayoutInflater mInflater;
    private String src_filter;
    
    public TranslationAdapter(Activity paramActivity)
    {
      super(R.layout.translate_row);
      setNotifyOnChange(true);
      this.mInflater = paramActivity.getLayoutInflater();
    }
    
    private void performFiltering()
    {
      clear();
      if (this.allTranslations == null) {
        return;
      }
      ArrayList localArrayList = this.allTranslations;
      int m = localArrayList.size();
      Object localObject = this.src_filter;
      int k = 0;
      int j = 0;
      int i = k;
      if (localObject == null)
      {
        i = k;
        if (this.dst_filter == null)
        {
          i = j;
          while (i < m)
          {
            add(localArrayList.get(i));
            i += 1;
          }
        }
      }
      while (i < m)
      {
        localObject = (TranslateDialog.TranslationInfo)localArrayList.get(i);
        if (((this.src_filter == null) || (((TranslateDialog.TranslationInfo)localObject).source_language.equals(this.src_filter))) && ((this.dst_filter == null) || (((TranslateDialog.TranslationInfo)localObject).target_language.equals(this.dst_filter)))) {
          add(localObject);
        }
        i += 1;
      }
    }
    
    public View getView(int paramInt, View paramView, final ViewGroup paramViewGroup)
    {
      Object localObject = paramView;
      if (paramView != null)
      {
        localObject = paramView;
        if (!(paramView.getTag() instanceof Helper)) {
          localObject = null;
        }
      }
      paramView = (View)localObject;
      if (localObject == null)
      {
        paramViewGroup = this.mInflater.inflate(R.layout.translate_row, paramViewGroup, false);
        localObject = new Helper(paramViewGroup);
        paramViewGroup.setTag(localObject);
        Resources localResources = getContext().getResources();
        paramView = paramViewGroup;
        if ((localResources instanceof ProxyResources))
        {
          paramView = (ProxyResources)localResources;
          paramView.styleTextView("textviewTranslationDictionary", null, ((Helper)localObject).tv_dictionary);
          paramView.styleTextView("textviewTranslationHeading", null, ((Helper)localObject).tv_heading);
          paramView.styleTextView("textviewTranslationText", null, ((Helper)localObject).tv_translation);
          paramView = paramViewGroup;
        }
      }
      paramViewGroup = (TranslateDialog.TranslationInfo)getItem(paramInt);
      localObject = (Helper)paramView.getTag();
      ((Helper)localObject).tv_dictionary.setText(paramViewGroup.dictionary);
      ((Helper)localObject).tv_heading.setText(paramViewGroup.heading);
      ((Helper)localObject).tv_translation.setText(paramViewGroup.translation);
      if (((Helper)localObject).sound_uri != paramViewGroup.sound_uri)
      {
        ((Helper)localObject).sound_uri = paramViewGroup.sound_uri;
        if (!TextUtils.isEmpty(((Helper)localObject).sound_uri))
        {
          ((Helper)localObject).tv_heading.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_sound_on, 0, 0, 0);
          ((Helper)localObject).tv_heading.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              try
              {
                TranslationContract.Intents.playSound(paramAnonymousView.getContext(), paramViewGroup.sound_uri);
                return;
              }
              catch (Exception paramAnonymousView)
              {
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("Failed to play sound: ");
                localStringBuilder.append(paramViewGroup.sound_uri);
                Log.e("translation", paramAnonymousView, localStringBuilder.toString(), new Object[0]);
              }
            }
          });
          return paramView;
        }
        ((Helper)localObject).tv_heading.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_sound_off, 0, 0, 0);
        ((Helper)localObject).tv_heading.setOnClickListener(null);
      }
      return paramView;
    }
    
    public void setFilter(String paramString1, String paramString2)
    {
      String str = paramString1;
      if (paramString1 != null)
      {
        str = paramString1;
        if (paramString1.length() == 0) {
          str = null;
        }
      }
      paramString1 = paramString2;
      if (paramString2 != null)
      {
        paramString1 = paramString2;
        if (paramString2.length() == 0) {
          paramString1 = null;
        }
      }
      this.src_filter = str;
      this.dst_filter = paramString1;
      performFiltering();
    }
    
    public void setTranslations(ArrayList<TranslateDialog.TranslationInfo> paramArrayList)
    {
      this.allTranslations = paramArrayList;
    }
    
    static final class Helper
    {
      String sound_uri;
      final TextView tv_dictionary;
      final TextView tv_heading;
      final TextView tv_translation;
      
      Helper(View paramView)
      {
        this.tv_dictionary = ((TextView)paramView.findViewById(R.id.tv_dictionary));
        this.tv_heading = ((TextView)paramView.findViewById(R.id.tv_heading));
        this.tv_translation = ((TextView)paramView.findViewById(R.id.tv_translation));
      }
    }
  }
  
  private static class TranslationInfo
  {
    String dictionary;
    String heading;
    String lingvo_uri;
    String sound_uri;
    String source_language;
    String target_language;
    String translation;
    
    private TranslationInfo() {}
  }
  
  private static final class TranslationsCursorLoader
    extends CursorLoader
  {
    Throwable error;
    final int[] lingvo_fail_count;
    ArrayList<TranslateDialog.TranslationInfo> translations;
    
    public TranslationsCursorLoader(int[] paramArrayOfInt, Context paramContext, Uri paramUri, String[] paramArrayOfString)
    {
      super(paramUri, paramArrayOfString, null, null, null);
      this.lingvo_fail_count = paramArrayOfInt;
    }
    
    private static String getString(Cursor paramCursor, String paramString)
    {
      if (paramCursor == null) {
        return "";
      }
      int i = paramCursor.getColumnIndex(paramString);
      if (i == -1) {
        return "";
      }
      return paramCursor.getString(i);
    }
    
    public Cursor loadInBackground()
    {
      this.translations = null;
      this.error = null;
      if (getUri() == null)
      {
        this.translations = new ArrayList();
        return null;
      }
      Cursor localCursor;
      try
      {
        localCursor = super.loadInBackground();
        if (localCursor != null) {}
      }
      catch (Throwable localThrowable1)
      {
        Object localObject;
        localCursor = null;
      }
      try
      {
        Log.e("translation", "No cursor returned for translations", new Object[0]);
        localObject = this.lingvo_fail_count;
        localObject[0] += 1;
        return null;
      }
      catch (Throwable localThrowable2)
      {
        int[] arrayOfInt;
        for (;;) {}
      }
      this.translations = new ArrayList();
      if (localCursor.moveToFirst()) {
        do
        {
          localObject = new TranslateDialog.TranslationInfo(null);
          ((TranslateDialog.TranslationInfo)localObject).source_language = getString(localCursor, "language_from");
          ((TranslateDialog.TranslationInfo)localObject).target_language = getString(localCursor, "language_to");
          ((TranslateDialog.TranslationInfo)localObject).dictionary = getString(localCursor, "dictionary");
          ((TranslateDialog.TranslationInfo)localObject).lingvo_uri = getString(localCursor, "dictionary_article_uri");
          if (TextUtils.isEmpty(((TranslateDialog.TranslationInfo)localObject).lingvo_uri)) {
            ((TranslateDialog.TranslationInfo)localObject).lingvo_uri = getString(localCursor, "article_uri");
          }
          ((TranslateDialog.TranslationInfo)localObject).sound_uri = getString(localCursor, "sound_uri");
          ((TranslateDialog.TranslationInfo)localObject).heading = getString(localCursor, "heading");
          ((TranslateDialog.TranslationInfo)localObject).translation = getString(localCursor, "translation");
          this.translations.add(localObject);
        } while (localCursor.moveToNext());
      }
      this.lingvo_fail_count[0] = 0;
      return localCursor;
      Log.e("translation", localThrowable1, "Failed to get translation", new Object[0]);
      this.error = localThrowable1;
      arrayOfInt = this.lingvo_fail_count;
      arrayOfInt[0] += 1;
      return localCursor;
    }
  }
}
