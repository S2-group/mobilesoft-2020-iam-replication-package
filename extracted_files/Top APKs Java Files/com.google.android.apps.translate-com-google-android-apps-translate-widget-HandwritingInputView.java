package com.google.android.apps.translate.widget;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.r;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.google.android.apps.translate.inputtools.InputToolsInput;
import com.google.android.libraries.handwriting.base.HandwritingRecognizer;
import com.google.android.libraries.handwriting.base.RecognitionResult;
import com.google.android.libraries.handwriting.classifiers.b;
import com.google.android.libraries.handwriting.gui.HandwritingOverlayView;
import com.google.android.libraries.handwriting.gui.ScrollableCandidateView;
import com.google.android.libraries.handwriting.gui.UIHandler;
import com.google.android.libraries.handwriting.gui.UndoButtonInterface;
import com.google.android.libraries.handwriting.gui.aa;
import com.google.android.libraries.handwriting.gui.ab;
import com.google.android.libraries.handwriting.gui.af;
import com.google.android.libraries.handwriting.gui.al;
import com.google.android.libraries.handwriting.gui.i;
import com.google.android.libraries.handwriting.gui.s;
import com.google.android.libraries.handwriting.networkrecognizer.CloudRecognizer;
import com.google.android.libraries.handwriting.networkrecognizer.a;
import com.google.android.libraries.translate.languages.Language;
import com.google.i.a.a.a.e;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HandwritingInputView
  extends LinearLayout
  implements View.OnClickListener, View.OnTouchListener, al
{
  public final ab a;
  public final UIHandler b;
  i c;
  public HandwritingOverlayView d;
  public m e;
  public InputToolsInput f;
  public int g = -1;
  boolean h = true;
  boolean i = true;
  final Handler j = new Handler();
  Button k;
  ImageButton l;
  ImageButton m;
  private final Object n = new Object();
  private final a o = new a();
  private ScrollableCandidateView p;
  private String q;
  private Language r;
  private Language s;
  private TextView t;
  private final l u = new l(this);
  private HandwritingUndoButton v;
  private int w;
  private int x;
  private ToggleButton y;
  
  public HandwritingInputView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public HandwritingInputView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setWillNotDraw(false);
    this.a = new p(this);
    int i1 = ((Integer)com.google.android.libraries.translate.util.j.c().second).intValue();
    this.o.f = 0;
    this.o.b = "atrans";
    this.o.d = i1;
    this.o.e = Build.VERSION.SDK_INT;
    this.o.h = 2;
    this.o.i = 250;
    paramContext = this.o;
    paramAttributeSet = String.valueOf(Build.DEVICE);
    String str = String.valueOf(Build.VERSION.RELEASE);
    paramContext.c = (String.valueOf(paramAttributeSet).length() + 19 + String.valueOf(str).length() + paramAttributeSet + ":" + str + ":atrans" + i1);
    paramContext = new s();
    paramContext.a = false;
    paramContext.b = false;
    this.c = new i(this.a, paramContext);
    a();
    this.b = new q(this, this, this.c, this.n);
    this.b.d = this;
    this.a.b = this.b;
    this.a.d = 600;
    this.a.f = false;
    if (this.a.e) {
      findViewById(2131493310).setVisibility(8);
    }
  }
  
  private final HandwritingRecognizer g()
  {
    System.loadLibrary("gnustl_shared");
    System.loadLibrary("hwrword");
    Object localObject2 = this.o.a;
    com.google.i.a.a.a.d localD = com.google.android.libraries.handwriting.base.d.a(getResources().openRawResource(2131165187));
    if ((localD == null) || (localObject2 == null)) {
      localObject1 = null;
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (((com.google.i.a.a.a.c)localObject1).c.equals("com.google.android.apps.handwriting.ime.ImeRecognizer")) {
        localObject2 = ((com.google.i.a.a.a.c)localObject1).l.a;
      }
      if (!com.google.android.libraries.handwriting.base.d.a(getContext(), (com.google.i.a.a.a.c)localObject2)) {
        break;
      }
      label205:
      label208:
      try
      {
        localObject1 = new b((com.google.i.a.a.a.c)localObject2, getContext());
        return localObject1;
      }
      catch (IOException localIOException) {}
      localObject3 = com.google.android.libraries.handwriting.base.d.a(localD, (String)localObject2);
      localObject1 = localObject3;
      if (localObject3 == null)
      {
        localObject1 = String.valueOf(localObject2);
        if (((String)localObject1).length() != 0) {
          "No exact match for language ".concat((String)localObject1);
        }
        for (;;)
        {
          int i2 = ((String)localObject2).indexOf('_');
          int i1 = i2;
          if (i2 == -1) {
            i1 = ((String)localObject2).indexOf('-');
          }
          if (i1 == -1) {
            break label205;
          }
          localObject1 = ((String)localObject2).substring(0, i1);
          localObject3 = com.google.android.libraries.handwriting.base.d.a(localD, (String)localObject1);
          if (localObject3 == null) {
            break label208;
          }
          localObject1 = localObject3;
          break;
          new String("No exact match for language ");
        }
        localObject1 = localObject2;
        new StringBuilder(String.valueOf(localObject2).length() + 23 + String.valueOf(localObject1).length()).append("No match for language ").append((String)localObject2).append(" ").append((String)localObject1);
        if ((((String)localObject1).equals("no")) || (((String)localObject1).equals("nn")))
        {
          localObject1 = com.google.android.libraries.handwriting.base.d.a(localD, "nb");
        }
        else if (((String)localObject1).equals("id"))
        {
          localObject1 = com.google.android.libraries.handwriting.base.d.a(localD, "in");
        }
        else if (((String)localObject1).equals("tl"))
        {
          localObject1 = com.google.android.libraries.handwriting.base.d.a(localD, "fil");
        }
        else
        {
          new StringBuilder(String.valueOf(localObject2).length() + 29).append("Spec for language ").append((String)localObject2).append(" not found.");
          localObject1 = null;
        }
      }
    }
    Object localObject1 = com.google.android.libraries.handwriting.base.d.a((com.google.i.a.a.a.c)localObject2);
    localObject2 = getResources().getStringArray(2131230726);
    Object localObject3 = getResources().getIntArray(2131230725);
    localObject2 = String.format("%3.1f", new Object[] { Float.valueOf((float)com.google.android.libraries.handwriting.base.d.a(getContext(), (ArrayList)localObject1, (String[])localObject2, (int[])localObject3) / 1024.0F / 1024.0F) });
    localObject2 = new r(getContext()).b(String.format("The handwriting team requires to download 0.01%% of the internet (approx %sMB) for this to work", new Object[] { localObject2 }));
    localObject1 = new h(this, (ArrayList)localObject1);
    ((r)localObject2).a.i = "DOWNLOAD ALL THE DATA!";
    ((r)localObject2).a.j = ((DialogInterface.OnClickListener)localObject1);
    ((r)localObject2).a().show();
    return null;
    return null;
  }
  
  private final void h()
  {
    this.o.a = this.q;
    Object localObject;
    if ((this.c.a instanceof CloudRecognizer))
    {
      localObject = (CloudRecognizer)this.c.a;
      String str = this.o.a;
      ((CloudRecognizer)localObject).b.a = str;
    }
    do
    {
      return;
      localObject = g();
    } while (localObject == null);
    this.c.a((HandwritingRecognizer)localObject);
  }
  
  final void a()
  {
    if ((this.y == null) || (this.y.isChecked()))
    {
      localObject = this.c;
      com.google.android.libraries.handwriting.networkrecognizer.d localD = com.google.android.libraries.handwriting.networkrecognizer.d.a();
      com.google.android.libraries.translate.util.j.a(localD);
      ((i)localObject).a(new CloudRecognizer(localD, this.o));
    }
    for (;;)
    {
      h();
      return;
      localObject = g();
      if (localObject == null) {
        break;
      }
      this.c.a((HandwritingRecognizer)localObject);
    }
    Object localObject = this.y;
    if (!this.y.isChecked()) {}
    for (boolean bool = true;; bool = false)
    {
      ((ToggleButton)localObject).setChecked(bool);
      break;
    }
  }
  
  public final void a(char paramChar)
  {
    Editable localEditable = this.f.getEditableText();
    if (paramChar == this.b.l)
    {
      if (this.f.getSelectionStart() >= 0) {
        localEditable.insert(this.f.getSelectionStart(), " ");
      }
      return;
    }
  }
  
  final void a(String paramString)
  {
    int i2 = 0;
    int i1;
    TextView localTextView;
    if (!TextUtils.isEmpty(paramString))
    {
      i1 = 1;
      localTextView = this.t;
      if (i1 == 0) {
        break label49;
      }
      label21:
      localTextView.setVisibility(i2);
      localTextView = this.t;
      if (i1 == 0) {
        break label55;
      }
    }
    for (;;)
    {
      localTextView.setText(paramString);
      return;
      i1 = 0;
      break;
      label49:
      i2 = 8;
      break label21;
      label55:
      paramString = "";
    }
  }
  
  final void a(boolean paramBoolean)
  {
    if (this.f != null)
    {
      if (paramBoolean) {
        a(String.format(getContext().getString(2131624199), new Object[] { this.r.getLongName() }));
      }
    }
    else {
      return;
    }
    a("");
  }
  
  public final void b()
  {
    c();
    this.b.d();
    this.b.b();
    this.b.i.a.clear();
  }
  
  public final void c()
  {
    this.b.d();
    a(this.h);
    this.b.b();
  }
  
  public final void d()
  {
    Editable localEditable = this.f.getEditableText();
    if (this.f.getSelectionStart() < 0) {
      if (localEditable.length() > 0) {
        localEditable.delete(localEditable.length() - 1, localEditable.length());
      }
    }
    do
    {
      return;
      if (this.f.getSelectionStart() < this.f.getSelectionEnd())
      {
        localEditable.delete(this.f.getSelectionStart(), this.f.getSelectionEnd());
        return;
      }
    } while (this.f.getSelectionStart() <= 0);
    localEditable.delete(this.f.getSelectionStart() - 1, this.f.getSelectionStart());
  }
  
  final void e()
  {
    if (this.f != null) {
      this.f.clearComposingText();
    }
  }
  
  public final void f()
  {
    this.b.h();
    c();
  }
  
  public InputConnection getCurrentInputConnection()
  {
    return this.e;
  }
  
  public int getCursorSelectionStart()
  {
    return this.e.a.a;
  }
  
  public String getSourceTextToTranslate()
  {
    return this.f.getText().toString();
  }
  
  public void onClick(View paramView)
  {
    int i1;
    if ((paramView == this.l) || (paramView == this.k)) {
      i1 = this.w;
    }
    for (;;)
    {
      this.b.onKey(i1, new int[] { i1 });
      return;
      if (paramView == this.m) {
        i1 = this.x;
      } else {
        i1 = 0;
      }
    }
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.p = ((ScrollableCandidateView)findViewById(2131493306));
    this.p.setListener(this.b);
    this.b.j = this.p;
    this.d = ((HandwritingOverlayView)findViewById(2131493308));
    this.d.setHandwritingOverlayListener(this.b);
    this.d.setRecoQueueStrokeColor(getResources().getColor(2131361882));
    this.d.setPendingStrokeColor(getResources().getColor(2131361881));
    this.d.setRecognizedStrokeColor(getResources().getColor(2131361884));
    this.d.setStrokePreviouslyRecognizedColor(getResources().getColor(2131361883));
    this.a.c = this.d;
    this.a.g = findViewById(2131493310);
    this.b.c = this.d;
    this.v = ((HandwritingUndoButton)findViewById(2131493312));
    this.k = ((Button)findViewById(2131493315));
    this.l = ((ImageButton)findViewById(2131493314));
    this.m = ((ImageButton)findViewById(2131493316));
    Object localObject;
    if (((getContext().getResources().getBoolean(2131296267)) || (getContext().getResources().getBoolean(2131296268))) && (PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("key_allow_offline_handwriting", false)))
    {
      localObject = getContext().getPackageManager().getInstalledApplications(0).iterator();
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
      } while (!((ApplicationInfo)((Iterator)localObject).next()).packageName.equals("com.google.android.apps.handwriting.ime"));
    }
    for (int i1 = 1;; i1 = 0)
    {
      if (i1 != 0)
      {
        this.y = ((ToggleButton)findViewById(2131493313));
        this.y.setVisibility(0);
        this.y.setOnCheckedChangeListener(new j(this));
      }
      this.k.setOnClickListener(this);
      this.l.setOnClickListener(this);
      this.m.setOnClickListener(this);
      this.m.setOnTouchListener(this);
      localObject = this.b;
      HandwritingUndoButton localHandwritingUndoButton = this.v;
      ((UIHandler)localObject).k = localHandwritingUndoButton;
      if (localHandwritingUndoButton != null) {
        ((UIHandler)localObject).k.setOnClickListener(new af((UIHandler)localObject));
      }
      this.w = this.b.l;
      this.x = this.b.m;
      localObject = this.c;
      ((i)localObject).i.g();
      ((i)localObject).b();
      ((i)localObject).i.h();
      this.t = ((TextView)findViewById(2131493311));
      return;
    }
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (paramView == this.m) {}
    switch (paramMotionEvent.getAction())
    {
    case 2: 
    default: 
      return false;
    case 0: 
      this.m.setPressed(true);
      onClick(this.m);
      this.j.postDelayed(this.u, 300L);
      return true;
    }
    paramView.setPressed(false);
    this.j.removeCallbacks(this.u);
    return true;
  }
  
  protected void onWindowVisibilityChanged(int paramInt)
  {
    super.onWindowVisibilityChanged(paramInt);
    e();
    this.b.i.a.clear();
    this.b.d();
    this.b.a(RecognitionResult.i, false);
  }
  
  public void setSourceAndTargetLanguages(Language paramLanguage1, Language paramLanguage2)
  {
    boolean bool = true;
    int i1;
    int i2;
    if ((this.r != null) && (this.r != paramLanguage1))
    {
      i1 = 1;
      if ((this.s == null) || (this.s == paramLanguage2)) {
        break label282;
      }
      i2 = 1;
      label39:
      if ((i1 != 0) || (i2 != 0))
      {
        e();
        c();
      }
      this.r = paramLanguage1;
      this.s = paramLanguage2;
      paramLanguage1 = paramLanguage1.getShortName();
      this.q = com.google.android.libraries.translate.languages.c.b(paramLanguage1);
      a();
      h();
      com.google.android.libraries.translate.util.d.a(this.p, paramLanguage1);
      float f1 = getResources().getDimensionPixelSize(2131427519);
      float f2 = getResources().getDimensionPixelSize(2131427520);
      float f3 = getResources().getDimensionPixelSize(2131427516);
      if (this.d.getMaxStrokeWidth() > f1) {
        this.d.setMaxStrokeWidth(f1);
      }
      if (this.d.b.b < f2) {
        this.d.setMinStrokeWidth(f2);
      }
      if (this.d.getMaxStrokeWidth() + this.d.b.b < 2.0F * f3)
      {
        this.d.setMaxStrokeWidth(f3);
        this.d.setMinStrokeWidth(f3);
      }
      a(this.h);
      if (com.google.android.libraries.handwriting.gui.t.a(this.r.getShortName())) {
        break label288;
      }
    }
    for (;;)
    {
      this.i = bool;
      if (!com.google.android.libraries.translate.util.t.a(getContext())) {
        a(getContext().getString(2131624592));
      }
      return;
      i1 = 0;
      break;
      label282:
      i2 = 0;
      break label39;
      label288:
      bool = false;
    }
  }
}
