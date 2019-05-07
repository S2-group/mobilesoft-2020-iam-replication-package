package com.feelingk.iap.gui.parser;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import com.feelingk.iap.net.k;
import com.feelingk.iap.net.s;
import com.feelingk.iap.util.CommonString;
import com.feelingk.iap.util.CommonString.Index;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;
import junit.framework.Assert;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ParserXML
  extends Activity
{
  static View.OnClickListener T = new ParserXML.17();
  private static ParserXML.n af = null;
  private static Boolean bJ;
  private static boolean bM;
  private static boolean bO;
  private static String bP;
  private static StateListDrawable bj;
  private static StateListDrawable bk;
  private static LinearLayout bs = null;
  private static EditText bt = null;
  private static Button bu = null;
  private static Button bv = null;
  private static Boolean bw;
  private static Boolean bx;
  public static String u = null;
  View.OnClickListener A = new View.OnClickListener()
  {
    public final void onClick(View paramAnonymousView)
    {
      ParserXML.z(ParserXML.this).a();
    }
  };
  View.OnClickListener B = new View.OnClickListener()
  {
    public final void onClick(View paramAnonymousView)
    {
      if (ParserXML.A(ParserXML.this)[0] != 0)
      {
        ParserXML.B(ParserXML.this).a(true);
        return;
      }
      ParserXML.B(ParserXML.this).a(false);
    }
  };
  View.OnClickListener C = new View.OnClickListener()
  {
    public final void onClick(View paramAnonymousView)
    {
      ParserXML.B(ParserXML.this).a();
    }
  };
  View.OnClickListener D = new View.OnClickListener()
  {
    public final void onClick(View paramAnonymousView)
    {
      ParserXML.C(ParserXML.this).a();
    }
  };
  View.OnClickListener E = new ParserXML.2(this);
  e F = new e();
  CompoundButton.OnCheckedChangeListener G = new ParserXML.3(this);
  View.OnClickListener H = new ParserXML.4(this);
  View.OnClickListener I = new ParserXML.5(this);
  View.OnClickListener J = new ParserXML.6(this);
  View.OnClickListener K = new ParserXML.7(this);
  View.OnClickListener L = new ParserXML.8(this);
  View.OnClickListener M = new ParserXML.9(this);
  View.OnClickListener N = new ParserXML.10(this);
  View.OnClickListener O = new ParserXML.11(this);
  View.OnClickListener P = new ParserXML.13(this);
  View.OnClickListener Q = new ParserXML.14(this);
  View.OnClickListener R = new ParserXML.15(this);
  View.OnClickListener S = new ParserXML.16(this);
  View.OnClickListener U = new ParserXML.18(this);
  private Stack<ViewGroup> V = null;
  private Hashtable<String, Integer> W = null;
  private Context X = null;
  private ParserXML.r Y = null;
  private f Z = null;
  public int a = 0;
  private boolean aA = false;
  private boolean aB = false;
  private boolean aC = false;
  private boolean aD = false;
  private boolean[] aE = new boolean[3];
  private boolean[] aF = new boolean[1];
  private boolean[] aG = new boolean[1];
  private TextView aH = null;
  private EditText aI = null;
  private EditText aJ = null;
  private EditText aK = null;
  private EditText aL = null;
  private EditText aM = null;
  private EditText aN = null;
  private EditText aO = null;
  private EditText aP = null;
  private EditText aQ = null;
  private EditText aR = null;
  private EditText aS = null;
  private EditText aT = null;
  private EditText aU = null;
  private boolean aV = true;
  private String aW = "";
  private StateListDrawable aX;
  private StateListDrawable aY;
  private StateListDrawable aZ;
  private ParserXML.s aa = null;
  private l ab = null;
  private g ac = null;
  private ParserXML.m ad = null;
  private ParserXML.p ae = null;
  private k ag = null;
  private ParserXML.o ah = null;
  private h ai = null;
  private i aj = null;
  private j ak = null;
  private String al = "/res/";
  private String am = "/xml";
  private String an = "/xml_kt_lg";
  private String ao = null;
  private String ap = null;
  private k aq = null;
  private boolean ar = false;
  private boolean as = false;
  private boolean at = false;
  private boolean au = false;
  private boolean av = false;
  private boolean aw = false;
  private boolean ax = false;
  private boolean ay = false;
  private boolean az = false;
  public String b = "purchase";
  private Button bA;
  private Button bB;
  private Button bC = null;
  private Button bD;
  private Button bE;
  private TextView bF;
  private TextView bG;
  private TextView bH;
  private TextView bI = null;
  private String bK;
  private boolean bL = false;
  private boolean bN = false;
  private int bQ = 0;
  private Button bR;
  private Button bS;
  private a bT = new a((byte)0);
  private b bU = new b((byte)0);
  private int bV;
  private int bW;
  private int bX;
  private StateListDrawable ba;
  private StateListDrawable bb;
  private StateListDrawable bc;
  private StateListDrawable bd;
  private StateListDrawable be;
  private StateListDrawable bf;
  private StateListDrawable bg;
  private StateListDrawable bh;
  private StateListDrawable bi;
  private StateListDrawable bl;
  private StateListDrawable bm;
  private StateListDrawable bn;
  private StateListDrawable bo;
  private Button bp;
  private Button bq;
  private Button br = null;
  private Button by;
  private Button bz;
  public View.OnClickListener c = null;
  public com.feelingk.iap.gui.a.a d = null;
  boolean e = true;
  boolean f = false;
  TextView g = null;
  InputStream h;
  InputStream i;
  InputStream j;
  InputStream k = null;
  Drawable l = null;
  Drawable m;
  Drawable n = null;
  Drawable o = null;
  Drawable p = null;
  Drawable q = null;
  Drawable r = null;
  Drawable s = null;
  boolean t = false;
  View.OnClickListener v = new ParserXML.1(this);
  View.OnClickListener w = new ParserXML.12(this);
  View.OnClickListener x = new View.OnClickListener()
  {
    public final void onClick(View paramAnonymousView)
    {
      if (ParserXML.e(ParserXML.this) != null)
      {
        ParserXML.a(ParserXML.this, ParserXML.r(ParserXML.this).getText().toString() + ParserXML.s(ParserXML.this).getText().toString() + ParserXML.t(ParserXML.this).getText().toString() + ParserXML.u(ParserXML.this).getText().toString());
        ParserXML.e(ParserXML.this).a(ParserXML.v(ParserXML.this));
      }
      while (ParserXML.k(ParserXML.this) == null) {
        return;
      }
      ParserXML.b(true);
      ParserXML.k(ParserXML.this).c();
    }
  };
  View.OnClickListener y = new View.OnClickListener()
  {
    public final void onClick(View paramAnonymousView)
    {
      if (ParserXML.w(ParserXML.this))
      {
        ParserXML.x(ParserXML.this).a("error");
        return;
      }
      ParserXML.y(ParserXML.this).a();
    }
  };
  View.OnClickListener z = new View.OnClickListener()
  {
    public final void onClick(View paramAnonymousView)
    {
      ParserXML.y(ParserXML.this).b();
    }
  };
  
  static
  {
    bw = Boolean.valueOf(false);
    bx = Boolean.valueOf(false);
    bJ = Boolean.valueOf(false);
    bM = false;
    bO = false;
    bP = null;
  }
  
  public ParserXML(Context paramContext)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
  }
  
  public ParserXML(Context paramContext, f paramF)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
    this.Z = paramF;
    this.ar = true;
  }
  
  public ParserXML(Context paramContext, g paramG, String paramString)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
    this.ac = paramG;
    if ("AutoPurchaseForm".equals(paramString)) {
      this.au = true;
    }
  }
  
  public ParserXML(Context paramContext, h paramH)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
    this.ai = paramH;
    this.aA = true;
  }
  
  public ParserXML(Context paramContext, i paramI)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
    this.aj = paramI;
    this.aB = true;
  }
  
  public ParserXML(Context paramContext, j paramJ)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
    this.ak = paramJ;
    this.aC = true;
  }
  
  public ParserXML(Context paramContext, k paramK, String paramString)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
    this.ag = paramK;
    if ("IMEIAuthForm".equals(paramString)) {
      this.ay = true;
    }
  }
  
  public ParserXML(Context paramContext, l paramL, String paramString)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
    this.ab = paramL;
    if ("PermissionPopup".equals(paramString)) {
      this.at = true;
    }
  }
  
  public ParserXML(Context paramContext, ParserXML.m paramM, String paramString)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
    this.ad = paramM;
    if ("Join".equals(paramString)) {
      this.av = true;
    }
  }
  
  public ParserXML(Context paramContext, ParserXML.n paramN)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
    af = paramN;
    this.ax = true;
  }
  
  public ParserXML(Context paramContext, ParserXML.o paramO)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
    this.ah = paramO;
    this.az = true;
  }
  
  public ParserXML(Context paramContext, ParserXML.p paramP)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
    this.ae = paramP;
    this.aw = true;
  }
  
  public ParserXML(Context paramContext, ParserXML.r paramR)
  {
    this(paramContext);
    this.Y = paramR;
  }
  
  public ParserXML(Context paramContext, ParserXML.s paramS, String paramString)
  {
    this.X = paramContext;
    this.V = new Stack();
    this.W = new Hashtable();
    this.aa = paramS;
    if ("YesNo".equals(paramString)) {
      this.as = true;
    }
  }
  
  private int a(float paramFloat)
  {
    if (paramFloat == 0.0F) {
      return 0;
    }
    return (int)TypedValue.applyDimension(1, paramFloat, this.X.getResources().getDisplayMetrics());
  }
  
  private View a(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.V.clear();
    this.W.clear();
    Stack localStack = new Stack();
    int i1 = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i1 == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i1)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i1 = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      View localView = b(paramXmlPullParser);
      localObject2 = localObject1;
      if (localView != null)
      {
        if (localObject1 == null) {
          localObject1 = localView;
        }
        for (;;)
        {
          if ((localView instanceof ViewGroup))
          {
            this.V.push((ViewGroup)localView);
            localObject2 = localObject1;
            break;
            localObject2 = (ViewGroup)this.V.peek();
            if (localView != null) {
              ((ViewGroup)localObject2).addView(localView);
            }
            continue;
            ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
            localObject2 = localObject1;
            break;
            localStack.pop();
            if (paramXmlPullParser.getName().endsWith("Layout")) {
              this.V.pop();
            }
            localObject2 = localObject1;
            if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
              break;
            }
            this.V.pop();
            localObject2 = localObject1;
            break;
          }
        }
        localObject2 = localObject1;
      }
    }
  }
  
  private View a(XmlPullParser paramXmlPullParser, String paramString)
    throws XmlPullParserException, IOException
  {
    this.V.clear();
    this.W.clear();
    Stack localStack = new Stack();
    int i1 = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i1 == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i1)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i1 = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      localObject2 = c(paramXmlPullParser, paramString);
      if (localObject2 == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localObject2;
      }
      for (;;)
      {
        if ((localObject2 instanceof ViewGroup))
        {
          this.V.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ViewGroup localViewGroup = (ViewGroup)this.V.peek();
          if (localObject2 != null) {
            localViewGroup.addView((View)localObject2);
          }
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("Layout")) {
            break;
          }
          this.V.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  private ViewGroup.LayoutParams a(AttributeSet paramAttributeSet, ViewGroup paramViewGroup)
  {
    Object localObject = null;
    String str1 = a(paramAttributeSet, "a:layout_width");
    String str2 = a(paramAttributeSet, "a:layout_height");
    String str3 = a(paramAttributeSet, "a:xlayout_width");
    String str4 = a(paramAttributeSet, "a:xlayout_height");
    int i2;
    int i1;
    if ((Build.MODEL.endsWith("LT15i")) && (this.f))
    {
      i2 = f(str3);
      i1 = f(str4);
      this.f = false;
      if ((paramViewGroup instanceof LinearLayout)) {
        localObject = new LinearLayout.LayoutParams(i2, i1);
      }
      if (!(paramViewGroup instanceof ScrollView)) {
        break label331;
      }
    }
    label304:
    label331:
    for (paramViewGroup = new LinearLayout.LayoutParams(i2, i1);; paramViewGroup = (ViewGroup)localObject)
    {
      localObject = paramViewGroup;
      if ((paramViewGroup instanceof LinearLayout.LayoutParams))
      {
        paramViewGroup = (LinearLayout.LayoutParams)paramViewGroup;
        localObject = a(paramAttributeSet, "a:layout_gravity");
        if (localObject != null)
        {
          if (!((String)localObject).equals("center")) {
            break label304;
          }
          paramViewGroup.gravity = 17;
        }
      }
      for (;;)
      {
        localObject = a(paramAttributeSet, "a:layout_weight");
        if (localObject != null) {
          paramViewGroup.weight = Float.parseFloat((String)localObject);
        }
        localObject = a(paramAttributeSet, "a:layout_marginTop");
        str2 = a(paramAttributeSet, "a:layout_marginLeft");
        str1 = a(paramAttributeSet, "a:layout_marginRight");
        paramAttributeSet = a(paramAttributeSet, "a:layout_marginBottom");
        if (localObject != null) {
          paramViewGroup.topMargin = h((String)localObject);
        }
        if (str2 != null) {
          paramViewGroup.leftMargin = h(str2);
        }
        if (paramAttributeSet != null) {
          paramViewGroup.bottomMargin = f(paramAttributeSet);
        }
        localObject = paramViewGroup;
        if (str1 != null)
        {
          paramViewGroup.rightMargin = h(str1);
          localObject = paramViewGroup;
        }
        return localObject;
        i2 = f(str1);
        i1 = f(str2);
        break;
        if (((String)localObject).equals("left")) {
          paramViewGroup.gravity = 3;
        } else {
          paramViewGroup.gravity = 5;
        }
      }
    }
  }
  
  private static String a(AttributeSet paramAttributeSet, String paramString)
  {
    int i1 = 0;
    for (;;)
    {
      if (i1 >= paramAttributeSet.getAttributeCount())
      {
        i1 = paramString.indexOf(":");
        if (i1 == -1) {
          break;
        }
        return paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", paramString.substring(i1 + 1));
      }
      if (paramAttributeSet.getAttributeName(i1).equals(paramString)) {
        return paramAttributeSet.getAttributeValue(i1);
      }
      i1 += 1;
    }
    return null;
  }
  
  public static void a(String paramString)
  {
    if ((paramString != null) && (bt != null))
    {
      u = paramString;
      bt.setTextColor(Color.parseColor("#FF6F00"));
      bt.setTextSize(1, 30.0F);
      bt.setText(paramString);
      bt.setPadding(0, 0, 0, 0);
      paramString = new InputFilter.LengthFilter(6);
      bt.setFilters(new InputFilter[] { paramString });
      bv.setBackgroundDrawable(bk);
      bv.setOnClickListener(T);
    }
  }
  
  private View b(XmlPullParser paramXmlPullParser)
  {
    Object localObject2 = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject2).equals("LinearLayout")) {
      paramXmlPullParser = new LinearLayout(this.X);
    }
    while (paramXmlPullParser == null)
    {
      localObject1 = null;
      return localObject1;
      if (((String)localObject2).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.X);
      }
      else if (((String)localObject2).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.X);
      }
      else if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.X);
      }
      else if (((String)localObject2).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.X);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
      }
      else if (((String)localObject2).equals("CheckBox"))
      {
        paramXmlPullParser = new CheckBox(this.X);
      }
      else
      {
        Assert.fail("# UnSupported tag:" + (String)localObject2);
        paramXmlPullParser = (XmlPullParser)localObject1;
      }
    }
    localObject2 = Boolean.valueOf(false);
    Object localObject4;
    if (this.d != null)
    {
      com.feelingk.iap.gui.a.b.a().a = this.d.c;
      com.feelingk.iap.gui.a.b.a().b = (com.feelingk.iap.gui.a.b.a().a - com.feelingk.iap.gui.a.b.a().c);
      localObject1 = com.feelingk.iap.b.t();
      if ((localObject1 != null) && (((s)localObject1).e != null) && (!((s)localObject1).e.equals("null")))
      {
        localObject4 = Boolean.valueOf(true);
        localObject2 = localObject1;
        localObject1 = localObject4;
      }
    }
    for (;;)
    {
      localObject4 = Build.MODEL;
      Object localObject6;
      Object localObject7;
      label353:
      Object localObject9;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject6 = (LinearLayout)paramXmlPullParser;
        localObject7 = a(localAttributeSet, "a:orientation");
        if (localObject7 != null)
        {
          if (!((String)localObject7).equals("horizontal")) {
            break label2545;
          }
          ((LinearLayout)localObject6).setOrientation(0);
        }
        localObject7 = a(localAttributeSet, "a:background");
        if (localObject7 != null)
        {
          if (!((String)localObject7).equals("dot_line")) {
            break label2565;
          }
          localObject9 = getClass().getResourceAsStream(this.al + "line_dot_01.png");
          localObject7 = Drawable.createFromStream((InputStream)localObject9, null);
        }
      }
      try
      {
        ((InputStream)localObject9).close();
        ((BitmapDrawable)localObject7).setTileModeX(Shader.TileMode.REPEAT);
        ((BitmapDrawable)localObject7).setTileModeY(Shader.TileMode.REPEAT);
        ((LinearLayout)localObject6).setBackgroundDrawable((Drawable)localObject7);
        label453:
        if (a(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject6).setBackgroundColor(-65536);
        }
        localObject7 = a(localAttributeSet, "a:gravity");
        if (localObject7 != null)
        {
          if (((String)localObject7).equals("center")) {
            ((LinearLayout)localObject6).setGravity(17);
          }
        }
        else
        {
          label505:
          localObject7 = a(localAttributeSet, "a:padding");
          if (localObject7 != null)
          {
            i1 = h((String)localObject7);
            ((LinearLayout)localObject6).setPadding(i1, i1, i1, i1);
          }
          if (a(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject6).setFocusableInTouchMode(true);
          }
          localObject7 = a(localAttributeSet, "a:paddingTop");
          localObject9 = a(localAttributeSet, "a:paddingBottom");
          localObject10 = a(localAttributeSet, "a:paddingLeft");
          localObject11 = a(localAttributeSet, "a:paddingRight");
          i1 = 0;
          i2 = 0;
          int i3 = 0;
          int i4 = 0;
          if (localObject7 != null) {
            i1 = f((String)localObject7);
          }
          if (localObject9 != null) {
            i2 = f((String)localObject9);
          }
          if (localObject10 != null) {
            i3 = f((String)localObject10);
          }
          if (localObject11 != null) {
            i4 = f((String)localObject11);
          }
          ((LinearLayout)localObject6).setPadding(i3, i1, i4, i2);
          localObject7 = a(localAttributeSet, "a:id");
          if ((localObject7 != null) && (this.d != null))
          {
            if ((!this.d.g.equals("Y")) || (!((String)localObject7).equals("unregistered"))) {
              break label2656;
            }
            ((LinearLayout)localObject6).setVisibility(8);
          }
          label720:
          if ((paramXmlPullParser instanceof ImageView))
          {
            localObject6 = (ImageView)paramXmlPullParser;
            localObject7 = a(localAttributeSet, "a:src");
            if (localObject7 != null)
            {
              localObject9 = getClass().getResourceAsStream(this.al + (String)localObject7 + ".png");
              ((ImageView)localObject6).setImageDrawable(Drawable.createFromStream((InputStream)localObject9, (String)localObject7));
            }
          }
        }
      }
      catch (IOException localIOException5)
      {
        try
        {
          ((InputStream)localObject9).close();
          label802:
          if ((paramXmlPullParser instanceof TextView))
          {
            localObject6 = (TextView)paramXmlPullParser;
            localObject9 = a(localAttributeSet, "a:id");
            localObject12 = a(localAttributeSet, "a:text");
            String str3 = a(localAttributeSet, "a:infotext");
            String str4 = a(localAttributeSet, "a:loctbtntext");
            localObject10 = a(localAttributeSet, "a:otptext");
            localObject11 = a(localAttributeSet, "a:textcontent");
            String str1 = a(localAttributeSet, "a:textSize");
            String str2 = a(localAttributeSet, "a:textColor");
            localObject7 = a(localAttributeSet, "a:gravity");
            if (localObject12 != null) {
              ((TextView)localObject6).setText(((String)localObject12).replace("\\n", "\n"));
            }
            if (str4 != null) {
              ((TextView)localObject6).setText(CommonString.a(CommonString.Index.P));
            }
            if (str3 != null)
            {
              localObject12 = this.X.getPackageManager().getInstalledApplications(0);
              i2 = ((List)localObject12).size();
              i1 = 0;
              label970:
              if (i1 < i2) {}
            }
            else
            {
              if (localObject10 != null) {
                ((TextView)localObject6).setText(CommonString.a(CommonString.Index.Y));
              }
              if (localObject11 != null)
              {
                ((String)localObject11).replace("\\n", "\n");
                ((TextView)localObject6).setText("<" + CommonString.a(CommonString.Index.O) + ">\n월별 자동결제 상품이란,");
              }
              if (str1 != null) {
                ((TextView)localObject6).setTextSize(1, g(str1));
              }
              if (str2 != null) {
                ((TextView)localObject6).setTextColor(Color.parseColor(str2));
              }
              if (localObject9 != null)
              {
                if (!((String)localObject9).equals("ItemName")) {
                  break label2728;
                }
                ((TextView)localObject6).setText(this.d.a);
                ((TextView)localObject6).setEllipsize(TextUtils.TruncateAt.END);
                ((TextView)localObject6).setSingleLine();
              }
              label1114:
              if (localObject7 != null)
              {
                if (!((String)localObject7).equals("right")) {
                  break label3872;
                }
                ((TextView)localObject6).setGravity(5);
              }
              label1136:
              ((TextView)localObject6).setLineSpacing(0.0F, 1.15F);
            }
          }
          else if ((paramXmlPullParser instanceof ImageView))
          {
            localObject1 = (ImageView)paramXmlPullParser;
            localObject6 = a(localAttributeSet, "a:background");
            if (localObject6 != null)
            {
              localObject2 = getClass().getResourceAsStream(this.al + (String)localObject6 + ".png");
              localObject6 = Drawable.createFromStream((InputStream)localObject2, (String)localObject6);
            }
          }
        }
        catch (IOException localIOException5)
        {
          try
          {
            ((InputStream)localObject2).close();
            label1224:
            ((ImageView)localObject1).setBackgroundDrawable((Drawable)localObject6);
            if ((paramXmlPullParser instanceof Button))
            {
              localObject2 = a(localAttributeSet, "a:offImage");
              localObject1 = a(localAttributeSet, "a:onImage");
              localObject6 = a(localAttributeSet, "a:id");
              if (localObject2 == null) {
                break label5403;
              }
              this.bp = ((Button)paramXmlPullParser);
              this.aX = new StateListDrawable();
              this.aZ = new StateListDrawable();
              this.bb = new StateListDrawable();
              this.bd = new StateListDrawable();
              this.bf = new StateListDrawable();
              this.ba = new StateListDrawable();
              this.bc = new StateListDrawable();
              this.be = new StateListDrawable();
              this.bg = new StateListDrawable();
              this.h = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
              this.n = Drawable.createFromStream(this.h, (String)localObject2);
            }
          }
          catch (IOException localIOException5)
          {
            try
            {
              int i1;
              Object localObject10;
              Object localObject11;
              int i2;
              Object localObject12;
              this.h.close();
              this.h = getClass().getResourceAsStream(this.al + (String)localObject1 + ".png");
              this.o = Drawable.createFromStream(this.h, (String)localObject1);
              try
              {
                this.h.close();
                if (((String)localObject2).equals("bt_01_nor.9"))
                {
                  this.l = this.n;
                  this.m = this.o;
                }
                localObject4 = this.aX;
                localObject7 = this.o;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.aX;
                localObject7 = this.n;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.aZ;
                localObject7 = this.l;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.aZ;
                localObject7 = this.l;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.bb;
                localObject7 = this.l;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.bb;
                localObject7 = this.l;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.bd;
                localObject7 = this.l;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.bd;
                localObject7 = this.l;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.bf;
                localObject7 = this.l;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.bf;
                localObject7 = this.l;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.ba;
                localObject7 = this.m;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.ba;
                localObject7 = this.l;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.bc;
                localObject7 = this.m;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.bc;
                localObject7 = this.l;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.be;
                localObject7 = this.m;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.be;
                localObject7 = this.l;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.bg;
                localObject7 = this.m;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.bg;
                localObject7 = this.l;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                this.bl = new StateListDrawable();
                this.i = getClass().getResourceAsStream(this.al + "bt_buy_dim" + ".png");
                this.p = Drawable.createFromStream(this.i, "bt_buy_dim");
                try
                {
                  this.i.close();
                  this.i = null;
                  this.i = getClass().getResourceAsStream(this.al + "bt_buy_dim.png");
                  this.q = Drawable.createFromStream(this.i, "bt_buy_dim");
                  try
                  {
                    this.i.close();
                    this.i = null;
                    localObject4 = this.bl;
                    localObject7 = this.q;
                    ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                    localObject4 = this.bl;
                    localObject7 = this.p;
                    ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                    if ((((String)localObject2).equals("btn_buy_nor_h")) && (com.feelingk.iap.b.n()))
                    {
                      this.bq = this.bp;
                      this.aY = this.aX;
                      this.t = true;
                      label2200:
                      if ((!((String)localObject2).equals("btn_buy_nor_h")) || (!this.t)) {
                        break label3931;
                      }
                      this.bp.setBackgroundDrawable(this.bl);
                      label2229:
                      if ((((String)localObject2).equals("bt_01_nor.9")) && (localObject6 != null))
                      {
                        this.bp.setTextColor(Color.parseColor("#CFCFCF"));
                        if (!((String)localObject6).equals("OCBBtn")) {
                          break label4147;
                        }
                        this.by = this.bp;
                        this.by.setPadding(0, 0, 0, 0);
                        if (!this.d.g.equals("Y")) {
                          break label4077;
                        }
                        if (!com.feelingk.iap.b.o()) {
                          break label4035;
                        }
                        localObject2 = com.feelingk.iap.b.p();
                        if ((localObject2 != null) && (((String)localObject2).length() > 0) && (Integer.parseInt((String)localObject2) >= 10)) {
                          break label3961;
                        }
                        bJ = Boolean.valueOf(true);
                        if (!com.feelingk.iap.gui.a.b.a().h) {
                          break label3945;
                        }
                        this.by.setText(CommonString.a(CommonString.Index.N));
                        label2367:
                        this.by.setTextSize(1, 13.0F);
                        this.by.setBackgroundDrawable(this.aZ);
                        this.by.setTextColor(Color.parseColor("#8B8B8B"));
                        this.by.setClickable(false);
                        this.by.setPadding(0, 0, 0, 0);
                        label2421:
                        if ((com.feelingk.iap.gui.a.b.a().b == 0) && (com.feelingk.iap.gui.a.b.a().d == 0))
                        {
                          this.by.setBackgroundDrawable(this.aZ);
                          this.by.setTextColor(Color.parseColor("#8B8B8B"));
                          this.by.setClickable(false);
                          this.by.setPadding(0, 0, 0, 0);
                        }
                      }
                      label2482:
                      if ((!((String)localObject1).equals("btn_buy_sel_h")) || (com.feelingk.iap.b.n())) {
                        break label5240;
                      }
                      this.bp.setOnClickListener(this.M);
                    }
                    for (;;)
                    {
                      label2510:
                      localObject1 = paramXmlPullParser;
                      if (this.V.size() <= 0) {
                        break;
                      }
                      paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.V.peek()));
                      return paramXmlPullParser;
                      label2545:
                      if (!((String)localObject7).equals("vertical")) {
                        break label353;
                      }
                      ((LinearLayout)localObject6).setOrientation(1);
                      break label353;
                      label2565:
                      localObject9 = getClass().getResourceAsStream(this.al + (String)localObject7 + ".png");
                      ((LinearLayout)localObject6).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject9, (String)localObject7));
                      try
                      {
                        ((InputStream)localObject9).close();
                      }
                      catch (IOException localIOException13) {}
                      break label453;
                      if (localIOException13.equals("left"))
                      {
                        ((LinearLayout)localObject6).setGravity(3);
                        break label505;
                      }
                      ((LinearLayout)localObject6).setGravity(5);
                      break label505;
                      label2656:
                      if ((!this.d.g.equals("N")) || (!localIOException13.equals("registered"))) {
                        break label720;
                      }
                      ((LinearLayout)localObject6).setVisibility(8);
                      break label720;
                      if (((ApplicationInfo)((List)localObject12).get(i1)).packageName.indexOf("com.skt.skaf.A000Z00040") == 0) {
                        this.aD = true;
                      }
                      i1 += 1;
                      break label970;
                      label2728:
                      if (((String)localObject9).equals("ItemUseDate"))
                      {
                        ((TextView)localObject6).setText(this.d.b);
                        break label1114;
                      }
                      if (((String)localObject9).equals("ItemPrice"))
                      {
                        ((TextView)localObject6).setText(this.d.c + CommonString.a(CommonString.Index.K));
                        break label1114;
                      }
                      if (((String)localObject9).equals("ItemOCB"))
                      {
                        this.bF = ((TextView)localObject6);
                        if (com.feelingk.iap.b.o())
                        {
                          this.bF.setText(Integer.parseInt(com.feelingk.iap.b.p()) - com.feelingk.iap.gui.a.b.a().d + " P");
                          break label1114;
                        }
                        this.bF.setText("0 P");
                        break label1114;
                      }
                      if (((String)localObject9).equals("ItemDotori"))
                      {
                        this.bG = ((TextView)localObject6);
                        if (this.d.j.equals("Y"))
                        {
                          ((TextView)localObject6).setText(this.d.i / 100 - com.feelingk.iap.gui.a.b.a().e / 100 + CommonString.a(CommonString.Index.A));
                          break label1114;
                        }
                        ((TextView)localObject6).setText("0" + CommonString.a(CommonString.Index.A));
                        break label1114;
                      }
                      if (((String)localObject9).equals("ItemCultureCash"))
                      {
                        this.bH = ((TextView)localObject6);
                        if ((com.feelingk.iap.b.q()) && (com.feelingk.iap.b.r() != null))
                        {
                          ((TextView)localObject6).setText(Integer.parseInt(com.feelingk.iap.b.r()) - com.feelingk.iap.gui.a.b.a().f + CommonString.a(CommonString.Index.K));
                          break label1114;
                        }
                        ((TextView)localObject6).setText("0" + CommonString.a(CommonString.Index.K));
                        break label1114;
                      }
                      if (((String)localObject9).equals("ItemCash"))
                      {
                        this.bI = ((TextView)localObject6);
                        ((TextView)localObject6).setText(this.d.d - com.feelingk.iap.gui.a.b.a().g + " P");
                        break label1114;
                      }
                      if (((String)localObject9).equals("Discount"))
                      {
                        this.aH = ((TextView)localObject6);
                        this.aH.setText(com.feelingk.iap.gui.a.b.a().c + CommonString.a(CommonString.Index.K));
                        break label1114;
                      }
                      if (((String)localObject9).equals("Payments"))
                      {
                        this.g = ((TextView)localObject6);
                        this.g.setText(com.feelingk.iap.gui.a.b.a().a - com.feelingk.iap.gui.a.b.a().c + CommonString.a(CommonString.Index.K));
                        break label1114;
                      }
                      if (((String)localObject9).equals("xperiaCash"))
                      {
                        if (((String)localObject4).endsWith("LT15i"))
                        {
                          ((TextView)localObject6).setText(CommonString.a(CommonString.Index.M) + "  ");
                          break label1114;
                        }
                        ((TextView)localObject6).setText("");
                        break label1114;
                      }
                      if (((String)localObject9).equals("commonMessage"))
                      {
                        ((TextView)localObject6).setText(this.ap);
                        break label1114;
                      }
                      if (((String)localObject9).equals("Version"))
                      {
                        if ("iap.tstore.co.kr".equals("iapdev.tstore.co.kr"))
                        {
                          ((TextView)localObject6).setText("V 12.09.17(" + CommonString.a(CommonString.Index.ab) + ")");
                          break label1114;
                        }
                        ((TextView)localObject6).setText("V 12.09.17");
                        break label1114;
                      }
                      if ((((String)localObject9).equals("HeaderMessage")) || (((String)localObject9).equals("FooterMessage")))
                      {
                        if (!this.d.k) {
                          break label1114;
                        }
                        return null;
                      }
                      if (((String)localObject9).equals("OCBCardNumber"))
                      {
                        ((TextView)localObject6).setText(this.d.h);
                        break label1114;
                      }
                      if (((String)localObject9).equals("infoText"))
                      {
                        if (com.feelingk.iap.util.a.a(this.X) == 1)
                        {
                          ((TextView)localObject6).setText(CommonString.a(CommonString.Index.Q));
                          break label1114;
                        }
                        ((TextView)localObject6).setText(CommonString.a(CommonString.Index.R));
                        break label1114;
                      }
                      if (((String)localObject9).equals("finalVersion_Item"))
                      {
                        if (this.d.k)
                        {
                          ((TextView)localObject6).setVisibility(8);
                          break label1114;
                        }
                        ((TextView)localObject6).setVisibility(0);
                        break label1114;
                      }
                      if ((((String)localObject9).equals("seller_tel_num")) || (((String)localObject9).equals("seller_tel_num_view")))
                      {
                        if (!((Boolean)localObject1).booleanValue())
                        {
                          ((TextView)localObject6).setVisibility(8);
                          break label1114;
                        }
                        if (!((String)localObject9).equals("seller_tel_num_view")) {
                          break label1114;
                        }
                        localObject1 = ((s)localObject2).e;
                        if (((String)localObject1).equals("null")) {
                          break label1114;
                        }
                        ((TextView)localObject6).setText(PhoneNumberUtils.formatNumber((String)localObject1));
                        break label1114;
                      }
                      if ((localObject2 != null) && (((String)localObject9).equals("seller_name_view")))
                      {
                        if ((((s)localObject2).a.equals("null")) && (((s)localObject2).d.equals("null"))) {
                          localObject1 = "";
                        }
                        for (;;)
                        {
                          ((TextView)localObject6).setSingleLine(true);
                          ((TextView)localObject6).setFocusable(true);
                          ((TextView)localObject6).setEllipsize(TextUtils.TruncateAt.MARQUEE);
                          ((TextView)localObject6).setFocusableInTouchMode(true);
                          ((TextView)localObject6).setSelected(true);
                          ((TextView)localObject6).setText((CharSequence)localObject1);
                          break;
                          if (!((s)localObject2).a.equals("null")) {
                            localObject1 = ((s)localObject2).a;
                          } else {
                            localObject1 = ((s)localObject2).d;
                          }
                        }
                      }
                      if ((localObject2 == null) || (!((String)localObject9).equals("seller_mail_view"))) {
                        break label1114;
                      }
                      if (((s)localObject2).b.equals("null"))
                      {
                        ((TextView)localObject6).setText("");
                        break label1114;
                      }
                      ((TextView)localObject6).setSingleLine(true);
                      ((TextView)localObject6).setFocusable(true);
                      ((TextView)localObject6).setEllipsize(TextUtils.TruncateAt.MARQUEE);
                      ((TextView)localObject6).setFocusableInTouchMode(true);
                      ((TextView)localObject6).setSelected(true);
                      ((TextView)localObject6).setText(((s)localObject2).b);
                      break label1114;
                      label3872:
                      if (localIOException13.equals("left"))
                      {
                        ((TextView)localObject6).setGravity(3);
                        break label1136;
                      }
                      if (localIOException13.equals("center"))
                      {
                        ((TextView)localObject6).setGravity(17);
                        break label1136;
                      }
                      ((TextView)localObject6).setGravity(19);
                      break label1136;
                      this.t = false;
                      break label2200;
                      label3931:
                      this.bp.setBackgroundDrawable(this.aX);
                      break label2229;
                      label3945:
                      this.by.setText(CommonString.a(CommonString.Index.L));
                      break label2367;
                      label3961:
                      bJ = Boolean.valueOf(true);
                      if (com.feelingk.iap.gui.a.b.a().h) {
                        this.by.setText(CommonString.a(CommonString.Index.N));
                      }
                      for (;;)
                      {
                        this.by.setTextSize(1, 13.0F);
                        this.by.setOnClickListener(new ParserXML.19(this));
                        break;
                        this.by.setText(CommonString.a(CommonString.Index.L));
                      }
                      label4035:
                      this.by.setText(CommonString.a(CommonString.Index.F));
                      this.by.setTextSize(1, 13.0F);
                      this.by.setOnClickListener(new View.OnClickListener()
                      {
                        public final void onClick(View paramAnonymousView)
                        {
                          ParserXML.G(ParserXML.this).f();
                        }
                      });
                      break label2421;
                      label4077:
                      this.by.setText(CommonString.a(CommonString.Index.H));
                      this.by.setTextSize(1, 13.0F);
                      this.by.setTextSize(1, 13.0F);
                      if (this.bF != null) {
                        this.bF.setText("0 P");
                      }
                      this.by.setOnClickListener(new View.OnClickListener()
                      {
                        public final void onClick(View paramAnonymousView)
                        {
                          ParserXML.G(ParserXML.this).c("C");
                        }
                      });
                      break label2421;
                      label4147:
                      if (((String)localObject6).equals("DotoriBtn"))
                      {
                        this.bz = this.bp;
                        this.bz.setPadding(0, 0, 0, 0);
                        if (this.d.j.equals("Y")) {
                          if (com.feelingk.iap.gui.a.b.a().i)
                          {
                            this.bz.setText(CommonString.a(CommonString.Index.N));
                            label4215:
                            this.bz.setTextSize(1, 13.0F);
                            if (!this.d.n) {
                              break label4387;
                            }
                            if (this.d.i == 0) {
                              break label4341;
                            }
                            this.bz.setOnClickListener(new View.OnClickListener()
                            {
                              public final void onClick(View paramAnonymousView)
                              {
                                if ((ParserXML.aa(ParserXML.this) != null) && (ParserXML.ab(ParserXML.this) != null))
                                {
                                  ParserXML.aa(ParserXML.this).setBackgroundDrawable(ParserXML.ab(ParserXML.this).getBackground());
                                  ParserXML.aa(ParserXML.this).setPadding(0, 0, 0, 0);
                                }
                                if (com.feelingk.iap.gui.a.b.a().i)
                                {
                                  ParserXML.ad(ParserXML.this).setText(CommonString.a(CommonString.Index.L));
                                  ParserXML.ad(ParserXML.this).setTextSize(1, 13.0F);
                                  ParserXML.ad(ParserXML.this).setPadding(0, 0, 0, 0);
                                  com.feelingk.iap.gui.a.b.a().i = false;
                                  ParserXML.this.d("DOTORI");
                                  return;
                                }
                                if ((com.feelingk.iap.gui.a.b.a().b < 100) && (com.feelingk.iap.gui.a.b.a().e == 0))
                                {
                                  ParserXML.ad(ParserXML.this).setBackgroundDrawable(ParserXML.ae(ParserXML.this));
                                  ParserXML.ad(ParserXML.this).setTextColor(Color.parseColor("#8B8B8B"));
                                  ParserXML.ad(ParserXML.this).setClickable(false);
                                  ParserXML.ad(ParserXML.this).setPadding(0, 0, 0, 0);
                                  return;
                                }
                                if ((com.feelingk.iap.gui.a.b.a().b > 100000) && (ParserXML.E(ParserXML.this).i > 100000))
                                {
                                  Log.i("ParserXML", "도토리 사용액, 후불결제금액 10만원 초과");
                                  Log.i("ParserXML", "후불 결제 금액: " + com.feelingk.iap.gui.a.b.a().b);
                                  ParserXML.G(ParserXML.this).h();
                                }
                                ParserXML.ad(ParserXML.this).setText(CommonString.a(CommonString.Index.N));
                                ParserXML.ad(ParserXML.this).setTextSize(1, 13.0F);
                                ParserXML.ad(ParserXML.this).setPadding(0, 0, 0, 0);
                                com.feelingk.iap.gui.a.b.a().i = true;
                                ParserXML.this.c("DOTORI");
                              }
                            });
                          }
                        }
                        for (;;)
                        {
                          if ((com.feelingk.iap.gui.a.b.a().b != 0) || (com.feelingk.iap.gui.a.b.a().e != 0)) {
                            break label4525;
                          }
                          this.bz.setClickable(false);
                          this.bz.setBackgroundDrawable(this.aZ);
                          this.bz.setPadding(0, 0, 0, 0);
                          this.bz.setTextColor(Color.parseColor("#8B8B8B"));
                          break;
                          this.bz.setText(CommonString.a(CommonString.Index.L));
                          break label4215;
                          label4341:
                          this.bz.setBackgroundDrawable(this.aZ);
                          this.bz.setTextColor(Color.parseColor("#8B8B8B"));
                          this.bz.setClickable(false);
                          this.bz.setPadding(0, 0, 0, 0);
                          continue;
                          label4387:
                          this.bz.setText(CommonString.a(CommonString.Index.L));
                          this.bz.setTextSize(1, 13.0F);
                          if (this.d.i != 0)
                          {
                            this.bz.setOnClickListener(new View.OnClickListener()
                            {
                              public final void onClick(View paramAnonymousView)
                              {
                                ParserXML.G(ParserXML.this).b(true);
                              }
                            });
                          }
                          else
                          {
                            this.bz.setBackgroundDrawable(this.bb);
                            this.bz.setTextColor(Color.parseColor("#8B8B8B"));
                            this.bz.setClickable(false);
                            this.bz.setPadding(0, 0, 0, 0);
                            continue;
                            this.bz.setText(CommonString.a(CommonString.Index.B));
                            this.bz.setTextSize(1, 13.0F);
                            this.bz.setOnClickListener(new View.OnClickListener()
                            {
                              public final void onClick(View paramAnonymousView)
                              {
                                ParserXML.G(ParserXML.this).b(false);
                              }
                            });
                          }
                        }
                        label4525:
                        break label2482;
                      }
                      if (((String)localObject6).equals("CultureBtn"))
                      {
                        this.bA = this.bp;
                        this.bA.setPadding(0, 0, 0, 0);
                        if (com.feelingk.iap.b.q()) {
                          if (com.feelingk.iap.gui.a.b.a().j)
                          {
                            this.bA.setText(CommonString.a(CommonString.Index.N));
                            label4585:
                            this.bA.setTextSize(1, 13.0F);
                            localObject2 = com.feelingk.iap.b.r();
                            if ((localObject2 != null) && (((String)localObject2).length() > 0) && (Integer.parseInt((String)localObject2) >= 10)) {
                              break label4747;
                            }
                            this.bA.setBackgroundDrawable(this.aZ);
                            this.bA.setTextColor(Color.parseColor("#8B8B8B"));
                            this.bA.setClickable(false);
                            this.bA.setPadding(0, 0, 0, 0);
                          }
                        }
                        for (;;)
                        {
                          if ((com.feelingk.iap.gui.a.b.a().b != 0) || (com.feelingk.iap.gui.a.b.a().f != 0)) {
                            break label4816;
                          }
                          this.bA.setClickable(false);
                          this.bA.setBackgroundDrawable(this.aZ);
                          this.bA.setPadding(0, 0, 0, 0);
                          this.bA.setTextColor(Color.parseColor("#8B8B8B"));
                          break;
                          this.bA.setText(CommonString.a(CommonString.Index.L));
                          break label4585;
                          label4747:
                          this.bA.setOnClickListener(new View.OnClickListener()
                          {
                            public final void onClick(View paramAnonymousView)
                            {
                              if ((ParserXML.aa(ParserXML.this) != null) && (ParserXML.ab(ParserXML.this) != null))
                              {
                                ParserXML.aa(ParserXML.this).setBackgroundDrawable(ParserXML.ab(ParserXML.this).getBackground());
                                ParserXML.aa(ParserXML.this).setPadding(0, 0, 0, 0);
                              }
                              if (com.feelingk.iap.gui.a.b.a().j)
                              {
                                ParserXML.af(ParserXML.this).setText(CommonString.a(CommonString.Index.L));
                                ParserXML.af(ParserXML.this).setTextSize(1, 13.0F);
                                ParserXML.af(ParserXML.this).setPadding(0, 0, 0, 0);
                                com.feelingk.iap.gui.a.b.a().j = false;
                                ParserXML.this.d("CULTURE");
                                return;
                              }
                              ParserXML.af(ParserXML.this).setText(CommonString.a(CommonString.Index.N));
                              ParserXML.af(ParserXML.this).setTextSize(1, 13.0F);
                              ParserXML.af(ParserXML.this).setPadding(0, 0, 0, 0);
                              com.feelingk.iap.gui.a.b.a().j = true;
                              ParserXML.this.c("CULTURE");
                            }
                          });
                          continue;
                          this.bA.setText(CommonString.a(CommonString.Index.D));
                          this.bA.setTextSize(1, 13.0F);
                          this.bA.setPadding(0, 0, 0, 0);
                          this.bA.setOnClickListener(new View.OnClickListener()
                          {
                            public final void onClick(View paramAnonymousView)
                            {
                              ParserXML.G(ParserXML.this).g();
                            }
                          });
                        }
                        label4816:
                        break label2482;
                      }
                      if (((String)localObject6).equals("TcashBtn"))
                      {
                        this.bB = this.bp;
                        this.bB.setPadding(0, 0, 0, 0);
                        if (com.feelingk.iap.gui.a.b.a().k)
                        {
                          this.bB.setText(CommonString.a(CommonString.Index.N));
                          label4870:
                          this.bB.setTextSize(1, 13.0F);
                          if (this.d.d < 10) {
                            break label4988;
                          }
                          this.bB.setOnClickListener(new View.OnClickListener()
                          {
                            public final void onClick(View paramAnonymousView)
                            {
                              if ((ParserXML.aa(ParserXML.this) != null) && (ParserXML.ab(ParserXML.this) != null))
                              {
                                ParserXML.aa(ParserXML.this).setBackgroundDrawable(ParserXML.ab(ParserXML.this).getBackground());
                                ParserXML.aa(ParserXML.this).setPadding(0, 0, 0, 0);
                              }
                              if (com.feelingk.iap.gui.a.b.a().k)
                              {
                                ParserXML.ag(ParserXML.this).setText(CommonString.a(CommonString.Index.L));
                                ParserXML.ag(ParserXML.this).setTextSize(1, 13.0F);
                                ParserXML.ag(ParserXML.this).setPadding(0, 0, 0, 0);
                                com.feelingk.iap.gui.a.b.a().k = false;
                                ParserXML.this.d("TCASH");
                                return;
                              }
                              ParserXML.ag(ParserXML.this).setText(CommonString.a(CommonString.Index.N));
                              ParserXML.ag(ParserXML.this).setTextSize(1, 13.0F);
                              ParserXML.ag(ParserXML.this).setPadding(0, 0, 0, 0);
                              com.feelingk.iap.gui.a.b.a().k = true;
                              ParserXML.this.c("TCASH");
                            }
                          });
                        }
                        for (;;)
                        {
                          if ((com.feelingk.iap.gui.a.b.a().b != 0) || (com.feelingk.iap.gui.a.b.a().g != 0)) {
                            break label5032;
                          }
                          this.bB.setClickable(false);
                          this.bB.setBackgroundDrawable(this.aZ);
                          this.bB.setPadding(0, 0, 0, 0);
                          this.bB.setTextColor(Color.parseColor("#8B8B8B"));
                          break;
                          this.bB.setText(CommonString.a(CommonString.Index.L));
                          break label4870;
                          label4988:
                          this.bB.setClickable(false);
                          this.bB.setBackgroundDrawable(this.aZ);
                          this.bB.setPadding(0, 0, 0, 0);
                          this.bB.setTextColor(Color.parseColor("#8B8B8B"));
                        }
                        label5032:
                        break label2482;
                      }
                      if (((String)localObject6).equals("OCBRegister"))
                      {
                        localObject2 = this.bp;
                        ((Button)localObject2).setText(CommonString.a(CommonString.Index.G));
                        ((Button)localObject2).setTextSize(1, 13.0F);
                        ((Button)localObject2).setPadding(0, 0, 0, 0);
                        ((Button)localObject2).setOnClickListener(new View.OnClickListener()
                        {
                          public final void onClick(View paramAnonymousView)
                          {
                            ParserXML.G(ParserXML.this).c("C");
                          }
                        });
                        break label2482;
                      }
                      if (((String)localObject6).equals("OCB_Card_Change"))
                      {
                        this.bS = this.bp;
                        this.bS.setText(CommonString.a(CommonString.Index.I));
                        this.bS.setTextSize(1, 13.0F);
                        this.bS.setPadding(0, 0, 0, 0);
                        this.bS.setOnClickListener(new View.OnClickListener()
                        {
                          public final void onClick(View paramAnonymousView)
                          {
                            ParserXML.G(ParserXML.this).c("U");
                          }
                        });
                        break label2482;
                      }
                      if (!((String)localObject6).equals("OCB_Card_Del")) {
                        break label2482;
                      }
                      this.bR = this.bp;
                      this.bR.setText(CommonString.a(CommonString.Index.J));
                      this.bR.setTextSize(1, 13.0F);
                      this.bR.setPadding(0, 0, 0, 0);
                      this.bR.setOnClickListener(new View.OnClickListener()
                      {
                        public final void onClick(View paramAnonymousView)
                        {
                          ParserXML.G(ParserXML.this).c("D");
                        }
                      });
                      break label2482;
                      label5240:
                      if (((String)localObject1).equals("pop_btn_sel_ok"))
                      {
                        this.bp.setOnClickListener(this.P);
                      }
                      else if (((String)localObject1).equals("btn_info01_sel"))
                      {
                        this.bp.setOnClickListener(this.Q);
                      }
                      else
                      {
                        if (((String)localObject1).equals("btn_locking_sel"))
                        {
                          localObject1 = this.X.getPackageManager().getInstalledApplications(0);
                          i2 = ((List)localObject1).size();
                          i1 = 0;
                          for (;;)
                          {
                            if (i1 >= i2)
                            {
                              this.bp.setOnClickListener(this.N);
                              break;
                            }
                            if (((ApplicationInfo)((List)localObject1).get(i1)).packageName.indexOf("com.skt.skaf.A000Z00040") == 0) {
                              this.aD = true;
                            }
                            i1 += 1;
                          }
                        }
                        if (((String)localObject1).equals("btn_cancel_sel_h")) {
                          this.bp.setOnClickListener(this.O);
                        }
                      }
                    }
                    label5403:
                    if (this.d != null) {
                      if (!this.d.l)
                      {
                        localObject2 = (CheckBox)paramXmlPullParser;
                        i1 = 0;
                        if (((String)localObject4).endsWith("LT15i"))
                        {
                          i1 = 1;
                          this.f = true;
                        }
                        if (i1 == 0)
                        {
                          localObject4 = getClass().getResourceAsStream(this.al + (String)localObject1 + ".png");
                          localObject1 = Drawable.createFromStream((InputStream)localObject4, (String)localObject1);
                        }
                      }
                    }
                    try
                    {
                      ((InputStream)localObject4).close();
                      localObject6 = getClass().getResourceAsStream(this.al + "btn_check_ok_nor" + ".png");
                      localObject4 = Drawable.createFromStream((InputStream)localObject6, "btn_check_ok_nor");
                    }
                    catch (IOException localIOException5)
                    {
                      try
                      {
                        ((InputStream)localObject6).close();
                        localObject8 = getClass().getResourceAsStream(this.al + "btn_check_no_sel" + ".png");
                        localObject6 = Drawable.createFromStream((InputStream)localObject8, "btn_check_no_sel");
                      }
                      catch (IOException localIOException5)
                      {
                        try
                        {
                          ((InputStream)localObject8).close();
                          localObject9 = getClass().getResourceAsStream(this.al + "btn_check_ok_sel" + ".png");
                          localObject8 = Drawable.createFromStream((InputStream)localObject9, "btn_check_ok_sel");
                        }
                        catch (IOException localIOException5)
                        {
                          try
                          {
                            ((InputStream)localObject9).close();
                            localObject10 = getClass().getResourceAsStream(this.al + "btn_check_dim" + ".png");
                            localObject9 = Drawable.createFromStream((InputStream)localObject10, "btn_check_dim");
                          }
                          catch (IOException localIOException5)
                          {
                            try
                            {
                              ((InputStream)localObject10).close();
                              localObject10 = new StateListDrawable();
                              localObject11 = new StateListDrawable();
                              ((StateListDrawable)localObject10).addState(new int[] { -16842910, -16842908 }, (Drawable)localObject9);
                              ((StateListDrawable)localObject10).addState(new int[] { -16842912, 16842919 }, (Drawable)localObject6);
                              ((StateListDrawable)localObject10).addState(new int[] { 16842912, 16842919 }, (Drawable)localObject8);
                              ((StateListDrawable)localObject10).addState(new int[] { -16842912, -16842908 }, (Drawable)localObject1);
                              ((StateListDrawable)localObject10).addState(new int[] { 16842912, -16842908 }, (Drawable)localObject4);
                              ((StateListDrawable)localObject11).addState(new int[] { -16842910, -16842908 }, null);
                              ((StateListDrawable)localObject11).addState(new int[] { -16842912, 16842919 }, null);
                              ((StateListDrawable)localObject11).addState(new int[] { 16842912, 16842919 }, null);
                              ((StateListDrawable)localObject11).addState(new int[] { -16842912, -16842908 }, null);
                              ((StateListDrawable)localObject11).addState(new int[] { 16842912, -16842908 }, null);
                              ((CheckBox)localObject2).setButtonDrawable((Drawable)localObject11);
                              ((CheckBox)localObject2).setBackgroundDrawable((Drawable)localObject10);
                              ((CheckBox)localObject2).setChecked(false);
                              if ((this.d.d != 0) && (this.d.d - this.d.c >= 0)) {
                                ((CheckBox)localObject2).setEnabled(true);
                              }
                              for (;;)
                              {
                                ((CheckBox)localObject2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                                {
                                  public final void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
                                  {
                                    paramAnonymousCompoundButton = ParserXML.this;
                                    String str;
                                    if (Boolean.valueOf(paramAnonymousBoolean).booleanValue())
                                    {
                                      int i = paramAnonymousCompoundButton.d.c - paramAnonymousCompoundButton.d.d;
                                      if (i > 0)
                                      {
                                        str = new DecimalFormat("###,###,###").format(i);
                                        paramAnonymousCompoundButton.g.setText(str + CommonString.a(CommonString.Index.K));
                                        ParserXML.G(ParserXML.this).a(paramAnonymousBoolean);
                                        if ((!paramAnonymousBoolean) || (!com.feelingk.iap.b.n())) {
                                          break label284;
                                        }
                                        ParserXML.S(ParserXML.this).setBackgroundDrawable(ParserXML.Q(ParserXML.this));
                                        ParserXML.this.t = false;
                                        ParserXML.S(ParserXML.this).setOnClickListener(ParserXML.this.M);
                                      }
                                    }
                                    label284:
                                    while ((paramAnonymousBoolean) || (!com.feelingk.iap.b.n()))
                                    {
                                      return;
                                      str = new DecimalFormat("###,###,###").format(paramAnonymousCompoundButton.d.c);
                                      paramAnonymousCompoundButton.g.setText(str + CommonString.a(CommonString.Index.K) + "-" + str + "P=0" + CommonString.a(CommonString.Index.K));
                                      break;
                                      str = new DecimalFormat("###,###,###").format(paramAnonymousCompoundButton.d.c);
                                      paramAnonymousCompoundButton.g.setText(str + CommonString.a(CommonString.Index.K));
                                      break;
                                    }
                                    ParserXML.S(ParserXML.this).setBackgroundDrawable(ParserXML.ah(ParserXML.this));
                                    ParserXML.this.t = true;
                                    ParserXML.S(ParserXML.this).setOnClickListener(null);
                                  }
                                });
                                break;
                                ((CheckBox)localObject2).setEnabled(false);
                              }
                              if ((!this.d.l) || (!(paramXmlPullParser instanceof CheckBox))) {
                                break label2510;
                              }
                              localObject2 = null;
                              localObject1 = a(localAttributeSet, "a:checkid");
                              localObject6 = (CheckBox)paramXmlPullParser;
                              ((CheckBox)localObject6).setTag(localObject1);
                              localObject1 = null;
                              if (this.d.m)
                              {
                                localObject2 = "checkbox_y";
                                localObject1 = "checkbox_n";
                                this.aF[0] = true;
                                label6098:
                                ((CheckBox)localObject6).setChecked(false);
                                i1 = 0;
                                if (((String)localObject4).endsWith("LT15i"))
                                {
                                  i1 = 1;
                                  this.f = true;
                                }
                                if (i1 != 0) {
                                  break label6453;
                                }
                                localObject4 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
                                localObject2 = Drawable.createFromStream((InputStream)localObject4, (String)localObject2);
                              }
                            }
                            catch (IOException localIOException5)
                            {
                              try
                              {
                                ((InputStream)localObject4).close();
                                localObject4 = getClass().getResourceAsStream(this.al + (String)localObject1 + ".png");
                                localObject1 = Drawable.createFromStream((InputStream)localObject4, (String)localObject1);
                              }
                              catch (IOException localIOException5)
                              {
                                try
                                {
                                  ((InputStream)localObject4).close();
                                  localObject4 = new StateListDrawable();
                                  localObject8 = new StateListDrawable();
                                  ((StateListDrawable)localObject4).addState(new int[] { -16842912, -16842908 }, (Drawable)localObject2);
                                  ((StateListDrawable)localObject4).addState(new int[] { 16842912, -16842908 }, (Drawable)localObject1);
                                  ((StateListDrawable)localObject8).addState(new int[] { -16842910, -16842908 }, null);
                                  ((StateListDrawable)localObject8).addState(new int[] { -16842912, 16842919 }, null);
                                  ((StateListDrawable)localObject8).addState(new int[] { 16842912, 16842919 }, null);
                                  ((StateListDrawable)localObject8).addState(new int[] { -16842912, -16842908 }, null);
                                  ((StateListDrawable)localObject8).addState(new int[] { 16842912, -16842908 }, null);
                                  ((CheckBox)localObject6).setButtonDrawable((Drawable)localObject8);
                                  ((CheckBox)localObject6).setBackgroundDrawable((Drawable)localObject4);
                                  for (;;)
                                  {
                                    ((CheckBox)localObject6).setOnCheckedChangeListener(this.G);
                                    break;
                                    if (this.d.m) {
                                      break label6098;
                                    }
                                    localObject2 = "checkbox_n";
                                    localObject1 = "checkbox_y";
                                    this.aF[0] = false;
                                    break label6098;
                                    label6453:
                                    if ((i1 != 0) && (this.d.m)) {
                                      ((CheckBox)localObject6).setChecked(true);
                                    }
                                  }
                                  localObject2 = (CheckBox)paramXmlPullParser;
                                  ((CheckBox)localObject2).setTag(a(localAttributeSet, "a:checkid"));
                                  i1 = 0;
                                  if (((String)localObject4).endsWith("LT15i"))
                                  {
                                    i1 = 1;
                                    this.f = true;
                                  }
                                  if (i1 == 0)
                                  {
                                    localObject4 = getClass().getResourceAsStream(this.al + (String)localObject1 + ".png");
                                    localObject1 = Drawable.createFromStream((InputStream)localObject4, (String)localObject1);
                                  }
                                }
                                catch (IOException localIOException5)
                                {
                                  try
                                  {
                                    ((InputStream)localObject4).close();
                                    localObject6 = getClass().getResourceAsStream(this.al + "checkbox_y" + ".png");
                                    localObject4 = Drawable.createFromStream((InputStream)localObject6, "checkbox_y");
                                  }
                                  catch (IOException localIOException5)
                                  {
                                    try
                                    {
                                      for (;;)
                                      {
                                        ((InputStream)localObject6).close();
                                        localObject6 = new StateListDrawable();
                                        Object localObject8 = new StateListDrawable();
                                        ((StateListDrawable)localObject6).addState(new int[] { -16842912, -16842908 }, (Drawable)localObject1);
                                        ((StateListDrawable)localObject6).addState(new int[] { 16842912, -16842908 }, (Drawable)localObject4);
                                        ((StateListDrawable)localObject8).addState(new int[] { -16842912, 16842919 }, null);
                                        ((StateListDrawable)localObject8).addState(new int[] { 16842912, 16842919 }, null);
                                        ((StateListDrawable)localObject8).addState(new int[] { -16842912, -16842908 }, null);
                                        ((StateListDrawable)localObject8).addState(new int[] { 16842912, -16842908 }, null);
                                        ((CheckBox)localObject2).setButtonDrawable((Drawable)localObject8);
                                        ((CheckBox)localObject2).setBackgroundDrawable((Drawable)localObject6);
                                        ((CheckBox)localObject2).setOnCheckedChangeListener(this.G);
                                        break label2510;
                                        localIOException15 = localIOException15;
                                        break;
                                        localIOException10 = localIOException10;
                                        break label802;
                                        localIOException1 = localIOException1;
                                        break label1224;
                                        localIOException2 = localIOException2;
                                        continue;
                                        localIOException11 = localIOException11;
                                        continue;
                                        localIOException14 = localIOException14;
                                        continue;
                                        localIOException16 = localIOException16;
                                        continue;
                                        localIOException17 = localIOException17;
                                        continue;
                                        localIOException3 = localIOException3;
                                        continue;
                                        localIOException4 = localIOException4;
                                      }
                                      localIOException5 = localIOException5;
                                    }
                                    catch (IOException localIOException12)
                                    {
                                      for (;;) {}
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
                  catch (IOException localIOException6)
                  {
                    for (;;) {}
                  }
                }
                catch (IOException localIOException7)
                {
                  for (;;) {}
                }
              }
              catch (IOException localIOException8)
              {
                for (;;) {}
              }
            }
            catch (IOException localIOException9)
            {
              for (;;) {}
            }
          }
        }
      }
      Object localObject5 = localObject1;
      localObject1 = localIOException1;
      Object localObject3 = localObject5;
      continue;
      localObject1 = localObject3;
      localObject3 = null;
    }
  }
  
  private View b(XmlPullParser paramXmlPullParser, String paramString)
  {
    Object localObject1 = paramXmlPullParser.getName();
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject1).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.X);
      if (paramXmlPullParser != null) {
        break label154;
      }
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      if (((String)localObject1).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.X);
        break;
      }
      if (((String)localObject1).equals("Button"))
      {
        paramXmlPullParser = new Button(this.X);
        break;
      }
      if (((String)localObject1).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.X);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject1);
      paramXmlPullParser = null;
      break;
      label154:
      Object localObject2;
      label199:
      Object localObject3;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        localObject2 = a(localAttributeSet, "a:orientation");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("horizontal")) {
            break label743;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = a(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
          ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
        }
      }
      try
      {
        ((InputStream)localObject3).close();
        if (a(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        localObject2 = a(localAttributeSet, "a:gravity");
        if (localObject2 != null)
        {
          if (((String)localObject2).equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          label320:
          localObject2 = a(localAttributeSet, "a:padding");
          if (localObject2 != null)
          {
            int i1 = h((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i1, i1, i1, i1);
          }
          if (a(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof TextView))
          {
            localObject1 = (TextView)paramXmlPullParser;
            localObject2 = a(localAttributeSet, "a:id");
            localObject3 = a(localAttributeSet, "a:text");
            localObject4 = a(localAttributeSet, "a:textSize");
            String str1 = a(localAttributeSet, "a:textColor");
            String str2 = a(localAttributeSet, "a:gravity");
            if (localObject3 != null) {
              ((TextView)localObject1).setText(((String)localObject3).replace("\\n", "\n"));
            }
            if (localObject4 != null) {
              ((TextView)localObject1).setTextSize(1, g((String)localObject4));
            }
            if (str1 != null) {
              ((TextView)localObject1).setTextColor(Color.parseColor(str1));
            }
            if (localObject2 != null) {
              ((TextView)localObject1).setText(paramString);
            }
            if (str2 == null) {
              break label772;
            }
            ((TextView)localObject1).setGravity(17);
            label507:
            ((TextView)localObject1).setLineSpacing(0.0F, 1.15F);
          }
          if ((paramXmlPullParser instanceof Button))
          {
            localObject2 = a(localAttributeSet, "a:offImage");
            paramString = a(localAttributeSet, "a:onImage");
            localObject1 = (Button)paramXmlPullParser;
            localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
            localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
          }
        }
      }
      catch (IOException localIOException2)
      {
        try
        {
          ((InputStream)localObject3).close();
          localObject4 = getClass().getResourceAsStream(this.al + paramString + ".png");
          localObject3 = Drawable.createFromStream((InputStream)localObject4, paramString);
        }
        catch (IOException localIOException2)
        {
          try
          {
            for (;;)
            {
              ((InputStream)localObject4).close();
              Object localObject4 = new StateListDrawable();
              ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject3);
              ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject2);
              ((Button)localObject1).setBackgroundDrawable((Drawable)localObject4);
              if (paramString.equals("btn_con_sel")) {
                ((Button)localObject1).setOnClickListener(this.y);
              }
              for (;;)
              {
                paramString = paramXmlPullParser;
                if (this.V.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.V.peek()));
                return paramXmlPullParser;
                label743:
                if (!((String)localObject2).equals("vertical")) {
                  break label199;
                }
                ((LinearLayout)localObject1).setOrientation(1);
                break label199;
                ((LinearLayout)localObject1).setGravity(5);
                break label320;
                label772:
                ((TextView)localObject1).setGravity(19);
                break label507;
                ((Button)localObject1).setOnClickListener(this.z);
              }
              localIOException1 = localIOException1;
            }
            localIOException2 = localIOException2;
          }
          catch (IOException localIOException3)
          {
            for (;;) {}
          }
        }
      }
    }
  }
  
  private View c(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.V.clear();
    this.W.clear();
    Stack localStack = new Stack();
    int i1 = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i1 == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i1)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i1 = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      localObject2 = i(paramXmlPullParser);
      if (localObject2 == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localObject2;
      }
      for (;;)
      {
        if ((localObject2 instanceof ViewGroup))
        {
          this.V.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ViewGroup localViewGroup = (ViewGroup)this.V.peek();
          if (localObject2 != null) {
            localViewGroup.addView((View)localObject2);
          }
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.V.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.V.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  private View c(XmlPullParser paramXmlPullParser, String paramString)
  {
    Object localObject1 = paramXmlPullParser.getName();
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject1).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.X);
      if (paramXmlPullParser != null) {
        break label149;
      }
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      if (((String)localObject1).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.X);
        break;
      }
      if (((String)localObject1).equals("Button"))
      {
        paramXmlPullParser = new Button(this.X);
        break;
      }
      if (((String)localObject1).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.X);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject1);
      paramXmlPullParser = null;
      break;
      label149:
      Object localObject2;
      label194:
      Object localObject3;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        localObject2 = a(localAttributeSet, "a:orientation");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("horizontal")) {
            break label820;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = a(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
          ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
        }
      }
      try
      {
        ((InputStream)localObject3).close();
        if (a(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        localObject2 = a(localAttributeSet, "a:gravity");
        if (localObject2 != null)
        {
          if (((String)localObject2).equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          localObject2 = a(localAttributeSet, "a:padding");
          if (localObject2 != null)
          {
            int i1 = h((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i1, i1, i1, i1);
          }
          if (a(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof ImageView))
          {
            localObject1 = (ImageView)paramXmlPullParser;
            localObject2 = a(localAttributeSet, "a:src");
            if (localObject2 != null)
            {
              localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
              ((ImageView)localObject1).setImageDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
            }
          }
        }
      }
      catch (IOException localIOException3)
      {
        try
        {
          ((InputStream)localObject3).close();
          if ((paramXmlPullParser instanceof TextView))
          {
            localObject1 = (TextView)paramXmlPullParser;
            localObject2 = a(localAttributeSet, "a:id");
            localObject3 = a(localAttributeSet, "a:text");
            localObject4 = a(localAttributeSet, "a:textSize");
            String str1 = a(localAttributeSet, "a:textColor");
            String str2 = a(localAttributeSet, "a:gravity");
            if (localObject3 != null) {
              ((TextView)localObject1).setText(((String)localObject3).replace("\\n", "\n"));
            }
            if (localObject4 != null) {
              ((TextView)localObject1).setTextSize(1, g((String)localObject4));
            }
            if (str1 != null) {
              ((TextView)localObject1).setTextColor(Color.parseColor(str1));
            }
            if (localObject2 != null) {
              ((TextView)localObject1).setText(paramString);
            }
            if (str2 != null)
            {
              ((TextView)localObject1).setGravity(17);
              ((TextView)localObject1).setLineSpacing(0.0F, 1.15F);
            }
          }
          else if ((paramXmlPullParser instanceof Button))
          {
            localObject2 = a(localAttributeSet, "a:offImage");
            paramString = a(localAttributeSet, "a:onImage");
            localObject1 = (Button)paramXmlPullParser;
            localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
            localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
          }
        }
        catch (IOException localIOException3)
        {
          try
          {
            ((InputStream)localObject3).close();
            localObject4 = getClass().getResourceAsStream(this.al + paramString + ".png");
            localObject3 = Drawable.createFromStream((InputStream)localObject4, paramString);
          }
          catch (IOException localIOException3)
          {
            try
            {
              for (;;)
              {
                ((InputStream)localObject4).close();
                Object localObject4 = new StateListDrawable();
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject3);
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject2);
                ((Button)localObject1).setBackgroundDrawable((Drawable)localObject4);
                if (paramString.equals("btn_con_sel")) {
                  ((Button)localObject1).setOnClickListener(this.A);
                }
                paramString = paramXmlPullParser;
                if (this.V.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.V.peek()));
                return paramXmlPullParser;
                label820:
                if (!((String)localObject2).equals("vertical")) {
                  break label194;
                }
                ((LinearLayout)localObject1).setOrientation(1);
                break label194;
                ((LinearLayout)localObject1).setGravity(5);
                continue;
                ((TextView)localObject1).setGravity(19);
                continue;
                localIOException2 = localIOException2;
                continue;
                localIOException1 = localIOException1;
              }
              localIOException3 = localIOException3;
            }
            catch (IOException localIOException4)
            {
              for (;;) {}
            }
          }
        }
      }
    }
  }
  
  private View d(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.V.clear();
    this.W.clear();
    Stack localStack = new Stack();
    int i1 = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i1 == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i1)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i1 = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      localObject2 = j(paramXmlPullParser);
      if (localObject2 == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localObject2;
      }
      for (;;)
      {
        if ((localObject2 instanceof ViewGroup))
        {
          this.V.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ViewGroup localViewGroup = (ViewGroup)this.V.peek();
          if (localObject2 != null) {
            localViewGroup.addView((View)localObject2);
          }
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.V.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.V.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  private View e(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.V.clear();
    this.W.clear();
    Stack localStack = new Stack();
    int i1 = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i1 == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i1)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i1 = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      localObject2 = k(paramXmlPullParser);
      if (localObject2 == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localObject2;
      }
      for (;;)
      {
        if ((localObject2 instanceof ViewGroup))
        {
          this.V.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ViewGroup localViewGroup = (ViewGroup)this.V.peek();
          if (localObject2 != null) {
            localViewGroup.addView((View)localObject2);
          }
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.V.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.V.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  private int f(String paramString)
  {
    if ("wrap_content".equals(paramString)) {}
    do
    {
      return -2;
      if ("fill_parent".equals(paramString)) {
        return -1;
      }
    } while (paramString == null);
    try
    {
      float f1 = Float.parseFloat(paramString.substring(0, paramString.length() - 2));
      if (paramString.endsWith("dp")) {
        return a(f1);
      }
      if (paramString.endsWith("pt")) {
        return (int)((float)(f1 / 1.5D) * 1.0F);
      }
      int i1 = Integer.parseInt(paramString);
      return i1;
    }
    catch (NumberFormatException paramString) {}
    return -1;
  }
  
  private View f(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.V.clear();
    this.W.clear();
    Stack localStack = new Stack();
    int i1 = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i1 == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i1)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i1 = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      localObject2 = l(paramXmlPullParser);
      if (localObject2 == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localObject2;
      }
      for (;;)
      {
        if ((localObject2 instanceof ViewGroup))
        {
          this.V.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ViewGroup localViewGroup = (ViewGroup)this.V.peek();
          if (localObject2 != null) {
            localViewGroup.addView((View)localObject2);
          }
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.V.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.V.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  private static int g(String paramString)
  {
    try
    {
      float f1 = Float.parseFloat(paramString.substring(0, paramString.length() - 2));
      return (int)(float)(f1 / 1.5D);
    }
    catch (NumberFormatException paramString) {}
    return -1;
  }
  
  private View g(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.V.clear();
    this.W.clear();
    Stack localStack = new Stack();
    int i1 = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i1 == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i1)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i1 = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      localObject2 = m(paramXmlPullParser);
      if (localObject2 == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localObject2;
      }
      for (;;)
      {
        if ((localObject2 instanceof ViewGroup))
        {
          this.V.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ViewGroup localViewGroup = (ViewGroup)this.V.peek();
          if (localObject2 != null) {
            localViewGroup.addView((View)localObject2);
          }
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.V.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.V.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  private int h(String paramString)
  {
    try
    {
      float f1 = Float.parseFloat(paramString.substring(0, paramString.length() - 2));
      if (paramString.endsWith("dp")) {
        return a(f1);
      }
      int i1 = Integer.parseInt(paramString);
      return i1;
    }
    catch (NumberFormatException paramString) {}
    return -1;
  }
  
  private View h(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.V.clear();
    this.W.clear();
    Stack localStack = new Stack();
    int i1 = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i1 == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i1)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i1 = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      localObject2 = n(paramXmlPullParser);
      if (localObject2 == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localObject2;
      }
      for (;;)
      {
        if ((localObject2 instanceof ViewGroup))
        {
          this.V.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ViewGroup localViewGroup = (ViewGroup)this.V.peek();
          if (localObject2 != null) {
            localViewGroup.addView((View)localObject2);
          }
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.V.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.V.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  /* Error */
  private View i(XmlPullParser paramXmlPullParser)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 743 1 0
    //   6: astore 6
    //   8: aconst_null
    //   9: astore 4
    //   11: aload_1
    //   12: invokestatic 934	android/util/Xml:asAttributeSet	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/util/AttributeSet;
    //   15: astore 5
    //   17: aload 6
    //   19: ldc_w 936
    //   22: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   25: ifeq +25 -> 50
    //   28: new 779	android/widget/LinearLayout
    //   31: dup
    //   32: aload_0
    //   33: getfield 327	com/feelingk/iap/gui/parser/ParserXML:X	Landroid/content/Context;
    //   36: invokespecial 937	android/widget/LinearLayout:<init>	(Landroid/content/Context;)V
    //   39: astore_1
    //   40: aload_1
    //   41: ifnonnull +233 -> 274
    //   44: aconst_null
    //   45: astore 4
    //   47: aload 4
    //   49: areturn
    //   50: aload 6
    //   52: ldc_w 939
    //   55: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   58: ifeq +18 -> 76
    //   61: new 941	android/widget/TextView
    //   64: dup
    //   65: aload_0
    //   66: getfield 327	com/feelingk/iap/gui/parser/ParserXML:X	Landroid/content/Context;
    //   69: invokespecial 942	android/widget/TextView:<init>	(Landroid/content/Context;)V
    //   72: astore_1
    //   73: goto -33 -> 40
    //   76: aload 6
    //   78: ldc_w 949
    //   81: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   84: ifeq +18 -> 102
    //   87: new 902	android/widget/Button
    //   90: dup
    //   91: aload_0
    //   92: getfield 327	com/feelingk/iap/gui/parser/ParserXML:X	Landroid/content/Context;
    //   95: invokespecial 950	android/widget/Button:<init>	(Landroid/content/Context;)V
    //   98: astore_1
    //   99: goto -59 -> 40
    //   102: aload 6
    //   104: ldc_w 1559
    //   107: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   110: ifeq +28 -> 138
    //   113: new 871	android/widget/EditText
    //   116: dup
    //   117: aload_0
    //   118: getfield 327	com/feelingk/iap/gui/parser/ParserXML:X	Landroid/content/Context;
    //   121: invokespecial 1560	android/widget/EditText:<init>	(Landroid/content/Context;)V
    //   124: astore_1
    //   125: aload_1
    //   126: checkcast 871	android/widget/EditText
    //   129: ldc_w 1561
    //   132: invokevirtual 1564	android/widget/EditText:setImeOptions	(I)V
    //   135: goto -95 -> 40
    //   138: aload 6
    //   140: ldc_w 751
    //   143: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   146: ifeq +23 -> 169
    //   149: new 786	android/widget/ScrollView
    //   152: dup
    //   153: aload_0
    //   154: getfield 327	com/feelingk/iap/gui/parser/ParserXML:X	Landroid/content/Context;
    //   157: invokespecial 951	android/widget/ScrollView:<init>	(Landroid/content/Context;)V
    //   160: astore_1
    //   161: aload_1
    //   162: iconst_0
    //   163: invokevirtual 956	android/view/View:setScrollbarFadingEnabled	(Z)V
    //   166: goto -126 -> 40
    //   169: aload 6
    //   171: ldc_w 958
    //   174: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   177: ifeq +18 -> 195
    //   180: new 960	android/widget/CheckBox
    //   183: dup
    //   184: aload_0
    //   185: getfield 327	com/feelingk/iap/gui/parser/ParserXML:X	Landroid/content/Context;
    //   188: invokespecial 961	android/widget/CheckBox:<init>	(Landroid/content/Context;)V
    //   191: astore_1
    //   192: goto -152 -> 40
    //   195: aload 6
    //   197: ldc_w 1566
    //   200: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   203: ifeq +18 -> 221
    //   206: new 1568	android/widget/RadioGroup
    //   209: dup
    //   210: aload_0
    //   211: getfield 327	com/feelingk/iap/gui/parser/ParserXML:X	Landroid/content/Context;
    //   214: invokespecial 1569	android/widget/RadioGroup:<init>	(Landroid/content/Context;)V
    //   217: astore_1
    //   218: goto -178 -> 40
    //   221: aload 6
    //   223: ldc_w 1571
    //   226: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   229: ifeq +18 -> 247
    //   232: new 1573	android/widget/RadioButton
    //   235: dup
    //   236: aload_0
    //   237: getfield 327	com/feelingk/iap/gui/parser/ParserXML:X	Landroid/content/Context;
    //   240: invokespecial 1574	android/widget/RadioButton:<init>	(Landroid/content/Context;)V
    //   243: astore_1
    //   244: goto -204 -> 40
    //   247: new 963	java/lang/StringBuilder
    //   250: dup
    //   251: ldc_w 965
    //   254: invokespecial 967	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   257: aload 6
    //   259: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: invokevirtual 973	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   265: invokestatic 978	junit/framework/Assert:fail	(Ljava/lang/String;)V
    //   268: aload 4
    //   270: astore_1
    //   271: goto -231 -> 40
    //   274: aload_1
    //   275: instanceof 1568
    //   278: ifeq +1152 -> 1430
    //   281: aload_1
    //   282: checkcast 1568	android/widget/RadioGroup
    //   285: astore 4
    //   287: aload 5
    //   289: ldc_w 1004
    //   292: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   295: astore 6
    //   297: aload 6
    //   299: ifnull +20 -> 319
    //   302: aload 6
    //   304: ldc_w 1006
    //   307: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   310: ifeq +1091 -> 1401
    //   313: aload 4
    //   315: iconst_0
    //   316: invokevirtual 1575	android/widget/RadioGroup:setOrientation	(I)V
    //   319: aload 5
    //   321: ldc_w 1065
    //   324: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   327: astore 6
    //   329: aload 6
    //   331: ifnull +21 -> 352
    //   334: aload 6
    //   336: ldc_w 790
    //   339: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   342: ifeq +1079 -> 1421
    //   345: aload 4
    //   347: bipush 17
    //   349: invokevirtual 1576	android/widget/RadioGroup:setGravity	(I)V
    //   352: aload_1
    //   353: instanceof 941
    //   356: ifeq +157 -> 513
    //   359: aload_1
    //   360: checkcast 941	android/widget/TextView
    //   363: astore 4
    //   365: aload 5
    //   367: ldc_w 1086
    //   370: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   373: astore 6
    //   375: aload 5
    //   377: ldc_w 1103
    //   380: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   383: astore 7
    //   385: aload 5
    //   387: ldc_w 1113
    //   390: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   393: astore 8
    //   395: aload 5
    //   397: ldc_w 1115
    //   400: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   403: astore 9
    //   405: aload 5
    //   407: ldc_w 1065
    //   410: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   413: astore 10
    //   415: aload 7
    //   417: ifnull +19 -> 436
    //   420: aload 4
    //   422: aload 7
    //   424: ldc_w 1117
    //   427: ldc_w 1119
    //   430: invokevirtual 1123	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   433: invokevirtual 1124	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   436: aload 8
    //   438: ifnull +15 -> 453
    //   441: aload 4
    //   443: iconst_1
    //   444: aload 8
    //   446: invokestatic 1159	com/feelingk/iap/gui/parser/ParserXML:g	(Ljava/lang/String;)I
    //   449: i2f
    //   450: invokevirtual 1160	android/widget/TextView:setTextSize	(IF)V
    //   453: aload 9
    //   455: ifnull +13 -> 468
    //   458: aload 4
    //   460: aload 9
    //   462: invokestatic 869	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   465: invokevirtual 1161	android/widget/TextView:setTextColor	(I)V
    //   468: aload 6
    //   470: ifnull +22 -> 492
    //   473: aload 6
    //   475: ldc_w 1351
    //   478: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   481: ifeq +11 -> 492
    //   484: aload 4
    //   486: ldc_w 1363
    //   489: invokevirtual 1124	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   492: aload 10
    //   494: ifnull +1220 -> 1714
    //   497: aload 4
    //   499: bipush 17
    //   501: invokevirtual 1181	android/widget/TextView:setGravity	(I)V
    //   504: aload 4
    //   506: fconst_0
    //   507: ldc_w 1182
    //   510: invokevirtual 1186	android/widget/TextView:setLineSpacing	(FF)V
    //   513: aload_1
    //   514: instanceof 960
    //   517: ifeq +1207 -> 1724
    //   520: aload 5
    //   522: ldc_w 1191
    //   525: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   528: astore 9
    //   530: aload 5
    //   532: ldc_w 1508
    //   535: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   538: astore 4
    //   540: aload_1
    //   541: checkcast 960	android/widget/CheckBox
    //   544: astore 6
    //   546: aload 6
    //   548: aload 4
    //   550: invokevirtual 1512	android/widget/CheckBox:setTag	(Ljava/lang/Object;)V
    //   553: getstatic 772	android/os/Build:MODEL	Ljava/lang/String;
    //   556: astore 10
    //   558: iconst_0
    //   559: istore_2
    //   560: new 1195	android/graphics/drawable/StateListDrawable
    //   563: dup
    //   564: invokespecial 1196	android/graphics/drawable/StateListDrawable:<init>	()V
    //   567: astore 7
    //   569: new 1195	android/graphics/drawable/StateListDrawable
    //   572: dup
    //   573: invokespecial 1196	android/graphics/drawable/StateListDrawable:<init>	()V
    //   576: astore 8
    //   578: aload 10
    //   580: ldc_w 774
    //   583: invokevirtual 749	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   586: ifeq +10 -> 596
    //   589: iconst_1
    //   590: istore_2
    //   591: aload_0
    //   592: iconst_1
    //   593: putfield 409	com/feelingk/iap/gui/parser/ParserXML:f	Z
    //   596: iload_2
    //   597: ifne +270 -> 867
    //   600: aload_0
    //   601: invokevirtual 1019	java/lang/Object:getClass	()Ljava/lang/Class;
    //   604: new 963	java/lang/StringBuilder
    //   607: dup
    //   608: aload_0
    //   609: getfield 357	com/feelingk/iap/gui/parser/ParserXML:al	Ljava/lang/String;
    //   612: invokestatic 1022	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   615: invokespecial 967	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   618: aload 9
    //   620: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   623: ldc_w 1098
    //   626: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   629: invokevirtual 973	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   632: invokevirtual 1030	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   635: astore 10
    //   637: aload 10
    //   639: aload 9
    //   641: invokestatic 1036	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   644: astore 9
    //   646: aload 10
    //   648: invokevirtual 1041	java/io/InputStream:close	()V
    //   651: aload_0
    //   652: invokevirtual 1019	java/lang/Object:getClass	()Ljava/lang/Class;
    //   655: new 963	java/lang/StringBuilder
    //   658: dup
    //   659: aload_0
    //   660: getfield 357	com/feelingk/iap/gui/parser/ParserXML:al	Ljava/lang/String;
    //   663: invokestatic 1022	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   666: invokespecial 967	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   669: ldc_w 1516
    //   672: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   675: ldc_w 1098
    //   678: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   681: invokevirtual 973	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   684: invokevirtual 1030	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   687: astore 11
    //   689: aload 11
    //   691: ldc_w 1516
    //   694: invokestatic 1036	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   697: astore 10
    //   699: aload 11
    //   701: invokevirtual 1041	java/io/InputStream:close	()V
    //   704: aload 7
    //   706: iconst_2
    //   707: newarray int
    //   709: dup
    //   710: iconst_0
    //   711: ldc_w 1490
    //   714: iastore
    //   715: dup
    //   716: iconst_1
    //   717: ldc_w 1489
    //   720: iastore
    //   721: aload 9
    //   723: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   726: aload 7
    //   728: iconst_2
    //   729: newarray int
    //   731: dup
    //   732: iconst_0
    //   733: ldc_w 1491
    //   736: iastore
    //   737: dup
    //   738: iconst_1
    //   739: ldc_w 1489
    //   742: iastore
    //   743: aload 10
    //   745: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   748: aload 8
    //   750: iconst_2
    //   751: newarray int
    //   753: dup
    //   754: iconst_0
    //   755: ldc_w 1488
    //   758: iastore
    //   759: dup
    //   760: iconst_1
    //   761: ldc_w 1489
    //   764: iastore
    //   765: aconst_null
    //   766: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   769: aload 8
    //   771: iconst_2
    //   772: newarray int
    //   774: dup
    //   775: iconst_0
    //   776: ldc_w 1490
    //   779: iastore
    //   780: dup
    //   781: iconst_1
    //   782: ldc_w 1219
    //   785: iastore
    //   786: aconst_null
    //   787: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   790: aload 8
    //   792: iconst_2
    //   793: newarray int
    //   795: dup
    //   796: iconst_0
    //   797: ldc_w 1491
    //   800: iastore
    //   801: dup
    //   802: iconst_1
    //   803: ldc_w 1219
    //   806: iastore
    //   807: aconst_null
    //   808: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   811: aload 8
    //   813: iconst_2
    //   814: newarray int
    //   816: dup
    //   817: iconst_0
    //   818: ldc_w 1490
    //   821: iastore
    //   822: dup
    //   823: iconst_1
    //   824: ldc_w 1489
    //   827: iastore
    //   828: aconst_null
    //   829: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   832: aload 8
    //   834: iconst_2
    //   835: newarray int
    //   837: dup
    //   838: iconst_0
    //   839: ldc_w 1491
    //   842: iastore
    //   843: dup
    //   844: iconst_1
    //   845: ldc_w 1489
    //   848: iastore
    //   849: aconst_null
    //   850: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   853: aload 6
    //   855: aload 8
    //   857: invokevirtual 1494	android/widget/CheckBox:setButtonDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   860: aload 6
    //   862: aload 7
    //   864: invokevirtual 1495	android/widget/CheckBox:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   867: aload 6
    //   869: iconst_0
    //   870: invokevirtual 1498	android/widget/CheckBox:setChecked	(Z)V
    //   873: aload 6
    //   875: aload_0
    //   876: getfield 535	com/feelingk/iap/gui/parser/ParserXML:G	Landroid/widget/CompoundButton$OnCheckedChangeListener;
    //   879: invokevirtual 1506	android/widget/CheckBox:setOnCheckedChangeListener	(Landroid/widget/CompoundButton$OnCheckedChangeListener;)V
    //   882: aload 4
    //   884: ldc_w 1578
    //   887: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   890: ifeq +22 -> 912
    //   893: getstatic 309	com/feelingk/iap/gui/parser/ParserXML:bO	Z
    //   896: ifeq +16 -> 912
    //   899: aload 6
    //   901: iconst_1
    //   902: invokevirtual 1498	android/widget/CheckBox:setChecked	(Z)V
    //   905: aload 6
    //   907: aload 7
    //   909: invokevirtual 1495	android/widget/CheckBox:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   912: aload_1
    //   913: instanceof 871
    //   916: ifeq +450 -> 1366
    //   919: aload_1
    //   920: checkcast 871	android/widget/EditText
    //   923: astore 4
    //   925: aload 4
    //   927: ldc_w 449
    //   930: invokevirtual 884	android/widget/EditText:setText	(Ljava/lang/CharSequence;)V
    //   933: aload_0
    //   934: getfield 331	com/feelingk/iap/gui/parser/ParserXML:Z	Lcom/feelingk/iap/gui/parser/ParserXML$f;
    //   937: ifnonnull +10 -> 947
    //   940: aload_0
    //   941: getfield 351	com/feelingk/iap/gui/parser/ParserXML:ak	Lcom/feelingk/iap/gui/parser/ParserXML$j;
    //   944: ifnull +9 -> 953
    //   947: aload 4
    //   949: iconst_2
    //   950: invokevirtual 1581	android/widget/EditText:setInputType	(I)V
    //   953: new 1583	java/util/ArrayList
    //   956: dup
    //   957: invokespecial 1584	java/util/ArrayList:<init>	()V
    //   960: astore 6
    //   962: aload 5
    //   964: ldc_w 1586
    //   967: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   970: astore 7
    //   972: aload 7
    //   974: ifnull +35 -> 1009
    //   977: aload 7
    //   979: ldc_w 1588
    //   982: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   985: ifeq +1904 -> 2889
    //   988: aload 4
    //   990: bipush 17
    //   992: invokevirtual 1581	android/widget/EditText:setInputType	(I)V
    //   995: aload 6
    //   997: new 102	com/feelingk/iap/gui/parser/ParserXML$t
    //   1000: dup
    //   1001: aload_0
    //   1002: invokespecial 1589	com/feelingk/iap/gui/parser/ParserXML$t:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;)V
    //   1005: invokevirtual 1592	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1008: pop
    //   1009: aload 5
    //   1011: ldc_w 1594
    //   1014: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1017: astore 7
    //   1019: aload 7
    //   1021: ifnull +26 -> 1047
    //   1024: aload 4
    //   1026: invokevirtual 1595	android/widget/EditText:setSingleLine	()V
    //   1029: aload 6
    //   1031: new 890	android/text/InputFilter$LengthFilter
    //   1034: dup
    //   1035: aload 7
    //   1037: invokestatic 1252	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1040: invokespecial 892	android/text/InputFilter$LengthFilter:<init>	(I)V
    //   1043: invokevirtual 1592	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1046: pop
    //   1047: aload 6
    //   1049: invokevirtual 1596	java/util/ArrayList:size	()I
    //   1052: ifle +20 -> 1072
    //   1055: aload 4
    //   1057: aload 6
    //   1059: iconst_0
    //   1060: anewarray 894	android/text/InputFilter
    //   1063: invokevirtual 1600	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   1066: checkcast 1602	[Landroid/text/InputFilter;
    //   1069: invokevirtual 898	android/widget/EditText:setFilters	([Landroid/text/InputFilter;)V
    //   1072: aload 5
    //   1074: ldc_w 1604
    //   1077: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1080: ifnull +15 -> 1095
    //   1083: aload 4
    //   1085: new 1606	android/text/method/PasswordTransformationMethod
    //   1088: dup
    //   1089: invokespecial 1607	android/text/method/PasswordTransformationMethod:<init>	()V
    //   1092: invokevirtual 1611	android/widget/EditText:setTransformationMethod	(Landroid/text/method/TransformationMethod;)V
    //   1095: aload 5
    //   1097: ldc_w 1613
    //   1100: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1103: astore 6
    //   1105: aload 6
    //   1107: ifnull +22 -> 1129
    //   1110: aload 6
    //   1112: ldc_w 1615
    //   1115: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1118: ifeq +11 -> 1129
    //   1121: aload 4
    //   1123: ldc_w 1617
    //   1126: invokevirtual 1620	android/widget/EditText:setPrivateImeOptions	(Ljava/lang/String;)V
    //   1129: aload 5
    //   1131: ldc_w 1086
    //   1134: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1137: astore 6
    //   1139: aload 6
    //   1141: ifnull +38 -> 1179
    //   1144: aload 6
    //   1146: ldc_w 1622
    //   1149: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1152: ifeq +1750 -> 2902
    //   1155: aload_0
    //   1156: aload 4
    //   1158: putfield 421	com/feelingk/iap/gui/parser/ParserXML:aI	Landroid/widget/EditText;
    //   1161: aload_0
    //   1162: getfield 351	com/feelingk/iap/gui/parser/ParserXML:ak	Lcom/feelingk/iap/gui/parser/ParserXML$j;
    //   1165: ifnull +14 -> 1179
    //   1168: aload_0
    //   1169: getfield 421	com/feelingk/iap/gui/parser/ParserXML:aI	Landroid/widget/EditText;
    //   1172: aload_0
    //   1173: getfield 489	com/feelingk/iap/gui/parser/ParserXML:bU	Lcom/feelingk/iap/gui/parser/ParserXML$b;
    //   1176: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   1179: aload 5
    //   1181: ldc_w 1011
    //   1184: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1187: astore 6
    //   1189: aload 6
    //   1191: ifnull +57 -> 1248
    //   1194: aload_0
    //   1195: invokevirtual 1019	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1198: new 963	java/lang/StringBuilder
    //   1201: dup
    //   1202: aload_0
    //   1203: getfield 357	com/feelingk/iap/gui/parser/ParserXML:al	Ljava/lang/String;
    //   1206: invokestatic 1022	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1209: invokespecial 967	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1212: aload 6
    //   1214: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1217: ldc_w 1098
    //   1220: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1223: invokevirtual 973	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1226: invokevirtual 1030	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   1229: astore 7
    //   1231: aload 4
    //   1233: aload 7
    //   1235: aload 6
    //   1237: invokestatic 1036	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1240: invokevirtual 1627	android/widget/EditText:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1243: aload 7
    //   1245: invokevirtual 1041	java/io/InputStream:close	()V
    //   1248: aload 5
    //   1250: ldc_w 1629
    //   1253: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1256: ifnull +70 -> 1326
    //   1259: iconst_1
    //   1260: newarray int
    //   1262: dup
    //   1263: iconst_0
    //   1264: ldc_w 1219
    //   1267: iastore
    //   1268: astore 6
    //   1270: iconst_0
    //   1271: newarray int
    //   1273: astore 7
    //   1275: ldc_w 1631
    //   1278: invokestatic 869	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   1281: istore_2
    //   1282: ldc_w 1633
    //   1285: invokestatic 869	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   1288: istore_3
    //   1289: aload 4
    //   1291: new 1635	android/content/res/ColorStateList
    //   1294: dup
    //   1295: iconst_2
    //   1296: anewarray 1637	[I
    //   1299: dup
    //   1300: iconst_0
    //   1301: aload 6
    //   1303: aastore
    //   1304: dup
    //   1305: iconst_1
    //   1306: aload 7
    //   1308: aastore
    //   1309: iconst_2
    //   1310: newarray int
    //   1312: dup
    //   1313: iconst_0
    //   1314: iload_2
    //   1315: iastore
    //   1316: dup
    //   1317: iconst_1
    //   1318: iload_3
    //   1319: iastore
    //   1320: invokespecial 1640	android/content/res/ColorStateList:<init>	([[I[I)V
    //   1323: invokevirtual 1643	android/widget/EditText:setTextColor	(Landroid/content/res/ColorStateList;)V
    //   1326: aload_0
    //   1327: getfield 439	com/feelingk/iap/gui/parser/ParserXML:aR	Landroid/widget/EditText;
    //   1330: ifnull +18 -> 1348
    //   1333: aload_0
    //   1334: getfield 439	com/feelingk/iap/gui/parser/ParserXML:aR	Landroid/widget/EditText;
    //   1337: new 32	com/feelingk/iap/gui/parser/ParserXML$33
    //   1340: dup
    //   1341: aload_0
    //   1342: invokespecial 1644	com/feelingk/iap/gui/parser/ParserXML$33:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;)V
    //   1345: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   1348: aload_0
    //   1349: getfield 445	com/feelingk/iap/gui/parser/ParserXML:aU	Landroid/widget/EditText;
    //   1352: ifnull +14 -> 1366
    //   1355: aload_0
    //   1356: getfield 445	com/feelingk/iap/gui/parser/ParserXML:aU	Landroid/widget/EditText;
    //   1359: aload_0
    //   1360: getfield 489	com/feelingk/iap/gui/parser/ParserXML:bU	Lcom/feelingk/iap/gui/parser/ParserXML$b;
    //   1363: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   1366: aload_1
    //   1367: astore 4
    //   1369: aload_0
    //   1370: getfield 323	com/feelingk/iap/gui/parser/ParserXML:V	Ljava/util/Stack;
    //   1373: invokevirtual 1269	java/util/Stack:size	()I
    //   1376: ifle -1329 -> 47
    //   1379: aload_1
    //   1380: aload_0
    //   1381: aload 5
    //   1383: aload_0
    //   1384: getfield 323	com/feelingk/iap/gui/parser/ParserXML:V	Ljava/util/Stack;
    //   1387: invokevirtual 725	java/util/Stack:peek	()Ljava/lang/Object;
    //   1390: checkcast 721	android/view/ViewGroup
    //   1393: invokespecial 1271	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Landroid/view/ViewGroup;)Landroid/view/ViewGroup$LayoutParams;
    //   1396: invokevirtual 1275	android/view/View:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   1399: aload_1
    //   1400: areturn
    //   1401: aload 6
    //   1403: ldc_w 1277
    //   1406: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1409: ifeq -1090 -> 319
    //   1412: aload 4
    //   1414: iconst_1
    //   1415: invokevirtual 1575	android/widget/RadioGroup:setOrientation	(I)V
    //   1418: goto -1099 -> 319
    //   1421: aload 4
    //   1423: iconst_5
    //   1424: invokevirtual 1576	android/widget/RadioGroup:setGravity	(I)V
    //   1427: goto -1075 -> 352
    //   1430: aload_1
    //   1431: instanceof 779
    //   1434: ifeq -1082 -> 352
    //   1437: aload_1
    //   1438: checkcast 779	android/widget/LinearLayout
    //   1441: astore 4
    //   1443: aload 5
    //   1445: ldc_w 1086
    //   1448: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1451: astore 6
    //   1453: aload 6
    //   1455: ifnull +26 -> 1481
    //   1458: aload 6
    //   1460: ldc_w 1646
    //   1463: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1466: ifeq +15 -> 1481
    //   1469: aload 4
    //   1471: putstatic 285	com/feelingk/iap/gui/parser/ParserXML:bs	Landroid/widget/LinearLayout;
    //   1474: aload 4
    //   1476: bipush 8
    //   1478: invokevirtual 1094	android/widget/LinearLayout:setVisibility	(I)V
    //   1481: aload 5
    //   1483: ldc_w 1004
    //   1486: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1489: astore 6
    //   1491: aload 6
    //   1493: ifnull +20 -> 1513
    //   1496: aload 6
    //   1498: ldc_w 1006
    //   1501: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1504: ifeq +181 -> 1685
    //   1507: aload 4
    //   1509: iconst_0
    //   1510: invokevirtual 1009	android/widget/LinearLayout:setOrientation	(I)V
    //   1513: aload 5
    //   1515: ldc_w 1011
    //   1518: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1521: astore 6
    //   1523: aload 6
    //   1525: ifnull +57 -> 1582
    //   1528: aload_0
    //   1529: invokevirtual 1019	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1532: new 963	java/lang/StringBuilder
    //   1535: dup
    //   1536: aload_0
    //   1537: getfield 357	com/feelingk/iap/gui/parser/ParserXML:al	Ljava/lang/String;
    //   1540: invokestatic 1022	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1543: invokespecial 967	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1546: aload 6
    //   1548: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1551: ldc_w 1098
    //   1554: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1557: invokevirtual 973	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1560: invokevirtual 1030	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   1563: astore 7
    //   1565: aload 4
    //   1567: aload 7
    //   1569: aload 6
    //   1571: invokestatic 1036	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1574: invokevirtual 1057	android/widget/LinearLayout:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1577: aload 7
    //   1579: invokevirtual 1041	java/io/InputStream:close	()V
    //   1582: aload 5
    //   1584: ldc_w 1059
    //   1587: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1590: ifnull +11 -> 1601
    //   1593: aload 4
    //   1595: ldc_w 1060
    //   1598: invokevirtual 1063	android/widget/LinearLayout:setBackgroundColor	(I)V
    //   1601: aload 5
    //   1603: ldc_w 1065
    //   1606: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1609: astore 6
    //   1611: aload 6
    //   1613: ifnull +21 -> 1634
    //   1616: aload 6
    //   1618: ldc_w 790
    //   1621: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1624: ifeq +81 -> 1705
    //   1627: aload 4
    //   1629: bipush 17
    //   1631: invokevirtual 1068	android/widget/LinearLayout:setGravity	(I)V
    //   1634: aload 5
    //   1636: ldc_w 1070
    //   1639: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1642: astore 6
    //   1644: aload 6
    //   1646: ifnull +19 -> 1665
    //   1649: aload_0
    //   1650: aload 6
    //   1652: invokespecial 814	com/feelingk/iap/gui/parser/ParserXML:h	(Ljava/lang/String;)I
    //   1655: istore_2
    //   1656: aload 4
    //   1658: iload_2
    //   1659: iload_2
    //   1660: iload_2
    //   1661: iload_2
    //   1662: invokevirtual 1071	android/widget/LinearLayout:setPadding	(IIII)V
    //   1665: aload 5
    //   1667: ldc_w 1073
    //   1670: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1673: ifnull -1321 -> 352
    //   1676: aload 4
    //   1678: iconst_1
    //   1679: invokevirtual 1076	android/widget/LinearLayout:setFocusableInTouchMode	(Z)V
    //   1682: goto -1330 -> 352
    //   1685: aload 6
    //   1687: ldc_w 1277
    //   1690: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1693: ifeq -180 -> 1513
    //   1696: aload 4
    //   1698: iconst_1
    //   1699: invokevirtual 1009	android/widget/LinearLayout:setOrientation	(I)V
    //   1702: goto -189 -> 1513
    //   1705: aload 4
    //   1707: iconst_5
    //   1708: invokevirtual 1068	android/widget/LinearLayout:setGravity	(I)V
    //   1711: goto -77 -> 1634
    //   1714: aload 4
    //   1716: bipush 19
    //   1718: invokevirtual 1181	android/widget/TextView:setGravity	(I)V
    //   1721: goto -1217 -> 504
    //   1724: aload_1
    //   1725: instanceof 1573
    //   1728: ifeq +456 -> 2184
    //   1731: aload_1
    //   1732: checkcast 1573	android/widget/RadioButton
    //   1735: astore 4
    //   1737: aload 5
    //   1739: ldc_w 1086
    //   1742: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1745: astore 7
    //   1747: aload 5
    //   1749: ldc_w 1191
    //   1752: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1755: astore 9
    //   1757: aload 5
    //   1759: ldc_w 1648
    //   1762: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1765: astore 6
    //   1767: aload 7
    //   1769: ifnull +73 -> 1842
    //   1772: aload 7
    //   1774: ldc_w 1650
    //   1777: invokevirtual 839	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   1780: iconst_m1
    //   1781: if_icmpeq +61 -> 1842
    //   1784: aload 7
    //   1786: ldc_w 1652
    //   1789: invokevirtual 1655	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1792: ifeq +346 -> 2138
    //   1795: aload_0
    //   1796: getfield 530	com/feelingk/iap/gui/parser/ParserXML:F	Lcom/feelingk/iap/gui/parser/ParserXML$e;
    //   1799: astore 8
    //   1801: aload 8
    //   1803: aload 4
    //   1805: putfield 1658	com/feelingk/iap/gui/parser/ParserXML$e:a	Landroid/widget/RadioButton;
    //   1808: aload 8
    //   1810: getfield 1658	com/feelingk/iap/gui/parser/ParserXML$e:a	Landroid/widget/RadioButton;
    //   1813: iconst_1
    //   1814: invokevirtual 1659	android/widget/RadioButton:setChecked	(Z)V
    //   1817: aload 8
    //   1819: getfield 1662	com/feelingk/iap/gui/parser/ParserXML$e:d	Lcom/feelingk/iap/gui/parser/ParserXML;
    //   1822: iconst_1
    //   1823: putfield 481	com/feelingk/iap/gui/parser/ParserXML:bQ	I
    //   1826: aload 4
    //   1828: aload 7
    //   1830: invokevirtual 1663	android/widget/RadioButton:setTag	(Ljava/lang/Object;)V
    //   1833: aload 4
    //   1835: aload_0
    //   1836: getfield 530	com/feelingk/iap/gui/parser/ParserXML:F	Lcom/feelingk/iap/gui/parser/ParserXML$e;
    //   1839: invokevirtual 1664	android/widget/RadioButton:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   1842: aload 9
    //   1844: ifnull -932 -> 912
    //   1847: aload 6
    //   1849: ifnull -937 -> 912
    //   1852: new 1195	android/graphics/drawable/StateListDrawable
    //   1855: dup
    //   1856: invokespecial 1196	android/graphics/drawable/StateListDrawable:<init>	()V
    //   1859: astore 7
    //   1861: new 1195	android/graphics/drawable/StateListDrawable
    //   1864: dup
    //   1865: invokespecial 1196	android/graphics/drawable/StateListDrawable:<init>	()V
    //   1868: astore 8
    //   1870: aload_0
    //   1871: invokevirtual 1019	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1874: new 963	java/lang/StringBuilder
    //   1877: dup
    //   1878: aload_0
    //   1879: getfield 357	com/feelingk/iap/gui/parser/ParserXML:al	Ljava/lang/String;
    //   1882: invokestatic 1022	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1885: invokespecial 967	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1888: aload 9
    //   1890: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1893: ldc_w 1098
    //   1896: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1899: invokevirtual 973	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1902: invokevirtual 1030	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   1905: astore 10
    //   1907: aload 10
    //   1909: aload 9
    //   1911: invokestatic 1036	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1914: astore 9
    //   1916: aload 10
    //   1918: invokevirtual 1041	java/io/InputStream:close	()V
    //   1921: aload_0
    //   1922: invokevirtual 1019	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1925: new 963	java/lang/StringBuilder
    //   1928: dup
    //   1929: aload_0
    //   1930: getfield 357	com/feelingk/iap/gui/parser/ParserXML:al	Ljava/lang/String;
    //   1933: invokestatic 1022	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1936: invokespecial 967	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1939: aload 6
    //   1941: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1944: ldc_w 1098
    //   1947: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1950: invokevirtual 973	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1953: invokevirtual 1030	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   1956: astore 10
    //   1958: aload 10
    //   1960: aload 6
    //   1962: invokestatic 1036	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1965: astore 6
    //   1967: aload 10
    //   1969: invokevirtual 1041	java/io/InputStream:close	()V
    //   1972: aload 7
    //   1974: iconst_2
    //   1975: newarray int
    //   1977: dup
    //   1978: iconst_0
    //   1979: ldc_w 1490
    //   1982: iastore
    //   1983: dup
    //   1984: iconst_1
    //   1985: ldc_w 1489
    //   1988: iastore
    //   1989: aload 9
    //   1991: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   1994: aload 7
    //   1996: iconst_2
    //   1997: newarray int
    //   1999: dup
    //   2000: iconst_0
    //   2001: ldc_w 1491
    //   2004: iastore
    //   2005: dup
    //   2006: iconst_1
    //   2007: ldc_w 1489
    //   2010: iastore
    //   2011: aload 6
    //   2013: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2016: aload 8
    //   2018: iconst_2
    //   2019: newarray int
    //   2021: dup
    //   2022: iconst_0
    //   2023: ldc_w 1488
    //   2026: iastore
    //   2027: dup
    //   2028: iconst_1
    //   2029: ldc_w 1489
    //   2032: iastore
    //   2033: aconst_null
    //   2034: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2037: aload 8
    //   2039: iconst_2
    //   2040: newarray int
    //   2042: dup
    //   2043: iconst_0
    //   2044: ldc_w 1490
    //   2047: iastore
    //   2048: dup
    //   2049: iconst_1
    //   2050: ldc_w 1219
    //   2053: iastore
    //   2054: aconst_null
    //   2055: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2058: aload 8
    //   2060: iconst_2
    //   2061: newarray int
    //   2063: dup
    //   2064: iconst_0
    //   2065: ldc_w 1491
    //   2068: iastore
    //   2069: dup
    //   2070: iconst_1
    //   2071: ldc_w 1219
    //   2074: iastore
    //   2075: aconst_null
    //   2076: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2079: aload 8
    //   2081: iconst_2
    //   2082: newarray int
    //   2084: dup
    //   2085: iconst_0
    //   2086: ldc_w 1490
    //   2089: iastore
    //   2090: dup
    //   2091: iconst_1
    //   2092: ldc_w 1489
    //   2095: iastore
    //   2096: aconst_null
    //   2097: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2100: aload 8
    //   2102: iconst_2
    //   2103: newarray int
    //   2105: dup
    //   2106: iconst_0
    //   2107: ldc_w 1491
    //   2110: iastore
    //   2111: dup
    //   2112: iconst_1
    //   2113: ldc_w 1489
    //   2116: iastore
    //   2117: aconst_null
    //   2118: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2121: aload 4
    //   2123: aload 8
    //   2125: invokevirtual 1665	android/widget/RadioButton:setButtonDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2128: aload 4
    //   2130: aload 7
    //   2132: invokevirtual 1666	android/widget/RadioButton:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2135: goto -1223 -> 912
    //   2138: aload 7
    //   2140: ldc_w 1668
    //   2143: invokevirtual 1655	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2146: ifeq +15 -> 2161
    //   2149: aload_0
    //   2150: getfield 530	com/feelingk/iap/gui/parser/ParserXML:F	Lcom/feelingk/iap/gui/parser/ParserXML$e;
    //   2153: aload 4
    //   2155: putfield 1670	com/feelingk/iap/gui/parser/ParserXML$e:b	Landroid/widget/RadioButton;
    //   2158: goto -332 -> 1826
    //   2161: aload 7
    //   2163: ldc_w 1672
    //   2166: invokevirtual 1655	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2169: ifeq -343 -> 1826
    //   2172: aload_0
    //   2173: getfield 530	com/feelingk/iap/gui/parser/ParserXML:F	Lcom/feelingk/iap/gui/parser/ParserXML$e;
    //   2176: aload 4
    //   2178: putfield 1674	com/feelingk/iap/gui/parser/ParserXML$e:c	Landroid/widget/RadioButton;
    //   2181: goto -355 -> 1826
    //   2184: aload_1
    //   2185: instanceof 902
    //   2188: ifeq -1276 -> 912
    //   2191: aload 5
    //   2193: ldc_w 1189
    //   2196: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   2199: astore 8
    //   2201: aload 5
    //   2203: ldc_w 1191
    //   2206: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   2209: astore 4
    //   2211: aload 5
    //   2213: ldc_w 1086
    //   2216: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   2219: astore 6
    //   2221: aload_1
    //   2222: checkcast 902	android/widget/Button
    //   2225: astore 7
    //   2227: aload_0
    //   2228: invokevirtual 1019	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2231: new 963	java/lang/StringBuilder
    //   2234: dup
    //   2235: aload_0
    //   2236: getfield 357	com/feelingk/iap/gui/parser/ParserXML:al	Ljava/lang/String;
    //   2239: invokestatic 1022	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   2242: invokespecial 967	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2245: aload 8
    //   2247: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2250: ldc_w 1098
    //   2253: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2256: invokevirtual 973	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2259: invokevirtual 1030	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   2262: astore 9
    //   2264: aload 9
    //   2266: aload 8
    //   2268: invokestatic 1036	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   2271: astore 8
    //   2273: aload 9
    //   2275: invokevirtual 1041	java/io/InputStream:close	()V
    //   2278: aload_0
    //   2279: invokevirtual 1019	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2282: new 963	java/lang/StringBuilder
    //   2285: dup
    //   2286: aload_0
    //   2287: getfield 357	com/feelingk/iap/gui/parser/ParserXML:al	Ljava/lang/String;
    //   2290: invokestatic 1022	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   2293: invokespecial 967	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2296: ldc_w 1676
    //   2299: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2302: invokevirtual 973	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2305: invokevirtual 1030	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   2308: astore 10
    //   2310: aload 10
    //   2312: ldc_w 1678
    //   2315: invokestatic 1036	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   2318: astore 9
    //   2320: aload 10
    //   2322: invokevirtual 1041	java/io/InputStream:close	()V
    //   2325: aload_0
    //   2326: invokevirtual 1019	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2329: new 963	java/lang/StringBuilder
    //   2332: dup
    //   2333: aload_0
    //   2334: getfield 357	com/feelingk/iap/gui/parser/ParserXML:al	Ljava/lang/String;
    //   2337: invokestatic 1022	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   2340: invokespecial 967	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2343: aload 4
    //   2345: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2348: ldc_w 1098
    //   2351: invokevirtual 970	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2354: invokevirtual 973	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2357: invokevirtual 1030	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   2360: astore 11
    //   2362: aload 11
    //   2364: aload 4
    //   2366: invokestatic 1036	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   2369: astore 10
    //   2371: aload 11
    //   2373: invokevirtual 1041	java/io/InputStream:close	()V
    //   2376: new 1195	android/graphics/drawable/StateListDrawable
    //   2379: dup
    //   2380: invokespecial 1196	android/graphics/drawable/StateListDrawable:<init>	()V
    //   2383: astore 11
    //   2385: aload_0
    //   2386: new 1195	android/graphics/drawable/StateListDrawable
    //   2389: dup
    //   2390: invokespecial 1196	android/graphics/drawable/StateListDrawable:<init>	()V
    //   2393: putfield 655	com/feelingk/iap/gui/parser/ParserXML:bm	Landroid/graphics/drawable/StateListDrawable;
    //   2396: aload_0
    //   2397: new 1195	android/graphics/drawable/StateListDrawable
    //   2400: dup
    //   2401: invokespecial 1196	android/graphics/drawable/StateListDrawable:<init>	()V
    //   2404: putfield 672	com/feelingk/iap/gui/parser/ParserXML:bh	Landroid/graphics/drawable/StateListDrawable;
    //   2407: aload_0
    //   2408: new 1195	android/graphics/drawable/StateListDrawable
    //   2411: dup
    //   2412: invokespecial 1196	android/graphics/drawable/StateListDrawable:<init>	()V
    //   2415: putfield 670	com/feelingk/iap/gui/parser/ParserXML:bi	Landroid/graphics/drawable/StateListDrawable;
    //   2418: aload 11
    //   2420: iconst_1
    //   2421: newarray int
    //   2423: dup
    //   2424: iconst_0
    //   2425: ldc_w 1219
    //   2428: iastore
    //   2429: aload 10
    //   2431: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2434: aload 11
    //   2436: iconst_0
    //   2437: newarray int
    //   2439: aload 8
    //   2441: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2444: aload_0
    //   2445: getfield 655	com/feelingk/iap/gui/parser/ParserXML:bm	Landroid/graphics/drawable/StateListDrawable;
    //   2448: iconst_1
    //   2449: newarray int
    //   2451: dup
    //   2452: iconst_0
    //   2453: ldc_w 1219
    //   2456: iastore
    //   2457: aload 9
    //   2459: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2462: aload_0
    //   2463: getfield 655	com/feelingk/iap/gui/parser/ParserXML:bm	Landroid/graphics/drawable/StateListDrawable;
    //   2466: iconst_0
    //   2467: newarray int
    //   2469: aload 9
    //   2471: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2474: aload 6
    //   2476: ifnull +53 -> 2529
    //   2479: aload 6
    //   2481: ldc_w 1680
    //   2484: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2487: ifne +36 -> 2523
    //   2490: aload 6
    //   2492: ldc_w 1682
    //   2495: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2498: ifne +25 -> 2523
    //   2501: aload 6
    //   2503: ldc_w 1684
    //   2506: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2509: ifne +14 -> 2523
    //   2512: aload 6
    //   2514: ldc_w 1686
    //   2517: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2520: ifeq +9 -> 2529
    //   2523: aload_0
    //   2524: aload 11
    //   2526: putfield 657	com/feelingk/iap/gui/parser/ParserXML:aY	Landroid/graphics/drawable/StateListDrawable;
    //   2529: aload 7
    //   2531: aload 11
    //   2533: invokevirtual 906	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2536: aload 5
    //   2538: ldc_w 1070
    //   2541: invokestatic 761	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   2544: astore 9
    //   2546: aload 9
    //   2548: ifnull +19 -> 2567
    //   2551: aload_0
    //   2552: aload 9
    //   2554: invokespecial 814	com/feelingk/iap/gui/parser/ParserXML:h	(Ljava/lang/String;)I
    //   2557: istore_2
    //   2558: aload 7
    //   2560: iload_2
    //   2561: iload_2
    //   2562: iload_2
    //   2563: iload_2
    //   2564: invokevirtual 1240	android/widget/Button:setPadding	(IIII)V
    //   2567: aload 4
    //   2569: ldc_w 1520
    //   2572: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2575: ifeq +178 -> 2753
    //   2578: aload 7
    //   2580: aload_0
    //   2581: getfield 496	com/feelingk/iap/gui/parser/ParserXML:v	Landroid/view/View$OnClickListener;
    //   2584: invokevirtual 910	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   2587: aload 6
    //   2589: ifnull -1677 -> 912
    //   2592: aload_0
    //   2593: aload 11
    //   2595: putfield 670	com/feelingk/iap/gui/parser/ParserXML:bi	Landroid/graphics/drawable/StateListDrawable;
    //   2598: aload_0
    //   2599: getfield 672	com/feelingk/iap/gui/parser/ParserXML:bh	Landroid/graphics/drawable/StateListDrawable;
    //   2602: iconst_1
    //   2603: newarray int
    //   2605: dup
    //   2606: iconst_0
    //   2607: ldc_w 1219
    //   2610: iastore
    //   2611: aload 8
    //   2613: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2616: aload_0
    //   2617: getfield 672	com/feelingk/iap/gui/parser/ParserXML:bh	Landroid/graphics/drawable/StateListDrawable;
    //   2620: iconst_0
    //   2621: newarray int
    //   2623: aload 8
    //   2625: invokevirtual 1223	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2628: aload 6
    //   2630: ldc_w 1688
    //   2633: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2636: ifeq +152 -> 2788
    //   2639: aload_0
    //   2640: aload 7
    //   2642: putfield 473	com/feelingk/iap/gui/parser/ParserXML:bC	Landroid/widget/Button;
    //   2645: aload_0
    //   2646: getfield 473	com/feelingk/iap/gui/parser/ParserXML:bC	Landroid/widget/Button;
    //   2649: aload_0
    //   2650: getfield 672	com/feelingk/iap/gui/parser/ParserXML:bh	Landroid/graphics/drawable/StateListDrawable;
    //   2653: invokevirtual 906	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2656: aload_0
    //   2657: getfield 473	com/feelingk/iap/gui/parser/ParserXML:bC	Landroid/widget/Button;
    //   2660: iconst_0
    //   2661: invokevirtual 1264	android/widget/Button:setClickable	(Z)V
    //   2664: aload_0
    //   2665: getfield 473	com/feelingk/iap/gui/parser/ParserXML:bC	Landroid/widget/Button;
    //   2668: ldc_w 1261
    //   2671: invokestatic 869	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   2674: invokevirtual 1237	android/widget/Button:setTextColor	(I)V
    //   2677: aload_0
    //   2678: getfield 473	com/feelingk/iap/gui/parser/ParserXML:bC	Landroid/widget/Button;
    //   2681: iconst_0
    //   2682: iconst_0
    //   2683: iconst_0
    //   2684: iconst_0
    //   2685: invokevirtual 1240	android/widget/Button:setPadding	(IIII)V
    //   2688: aload_0
    //   2689: getfield 473	com/feelingk/iap/gui/parser/ParserXML:bC	Landroid/widget/Button;
    //   2692: ifnull +11 -> 2703
    //   2695: aload_0
    //   2696: getfield 473	com/feelingk/iap/gui/parser/ParserXML:bC	Landroid/widget/Button;
    //   2699: iconst_0
    //   2700: invokevirtual 1264	android/widget/Button:setClickable	(Z)V
    //   2703: aload 6
    //   2705: ldc_w 1680
    //   2708: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2711: ifne +14 -> 2725
    //   2714: aload 6
    //   2716: ldc_w 1682
    //   2719: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2722: ifeq -1810 -> 912
    //   2725: aload_0
    //   2726: aload 7
    //   2728: putfield 661	com/feelingk/iap/gui/parser/ParserXML:bq	Landroid/widget/Button;
    //   2731: aload_0
    //   2732: getfield 661	com/feelingk/iap/gui/parser/ParserXML:bq	Landroid/widget/Button;
    //   2735: aload_0
    //   2736: getfield 655	com/feelingk/iap/gui/parser/ParserXML:bm	Landroid/graphics/drawable/StateListDrawable;
    //   2739: invokevirtual 906	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2742: aload_0
    //   2743: getfield 661	com/feelingk/iap/gui/parser/ParserXML:bq	Landroid/widget/Button;
    //   2746: iconst_0
    //   2747: invokevirtual 1264	android/widget/Button:setClickable	(Z)V
    //   2750: goto -1838 -> 912
    //   2753: aload 4
    //   2755: ldc_w 1690
    //   2758: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2761: ifeq +15 -> 2776
    //   2764: aload 7
    //   2766: aload_0
    //   2767: getfield 504	com/feelingk/iap/gui/parser/ParserXML:x	Landroid/view/View$OnClickListener;
    //   2770: invokevirtual 910	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   2773: goto -186 -> 2587
    //   2776: aload 7
    //   2778: aload_0
    //   2779: getfield 501	com/feelingk/iap/gui/parser/ParserXML:w	Landroid/view/View$OnClickListener;
    //   2782: invokevirtual 910	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   2785: goto -198 -> 2587
    //   2788: aload 6
    //   2790: ldc_w 1692
    //   2793: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2796: ifeq +15 -> 2811
    //   2799: aload 7
    //   2801: iconst_0
    //   2802: iconst_0
    //   2803: iconst_0
    //   2804: iconst_0
    //   2805: invokevirtual 1240	android/widget/Button:setPadding	(IIII)V
    //   2808: goto -120 -> 2688
    //   2811: aload 6
    //   2813: ldc_w 1684
    //   2816: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2819: ifeq +31 -> 2850
    //   2822: aload_0
    //   2823: aload 7
    //   2825: putfield 659	com/feelingk/iap/gui/parser/ParserXML:bD	Landroid/widget/Button;
    //   2828: aload_0
    //   2829: getfield 659	com/feelingk/iap/gui/parser/ParserXML:bD	Landroid/widget/Button;
    //   2832: aload_0
    //   2833: getfield 655	com/feelingk/iap/gui/parser/ParserXML:bm	Landroid/graphics/drawable/StateListDrawable;
    //   2836: invokevirtual 906	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2839: aload_0
    //   2840: getfield 659	com/feelingk/iap/gui/parser/ParserXML:bD	Landroid/widget/Button;
    //   2843: iconst_0
    //   2844: invokevirtual 1264	android/widget/Button:setClickable	(Z)V
    //   2847: goto -159 -> 2688
    //   2850: aload 6
    //   2852: ldc_w 1686
    //   2855: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2858: ifeq -170 -> 2688
    //   2861: aload_0
    //   2862: aload 7
    //   2864: putfield 653	com/feelingk/iap/gui/parser/ParserXML:bE	Landroid/widget/Button;
    //   2867: aload_0
    //   2868: getfield 653	com/feelingk/iap/gui/parser/ParserXML:bE	Landroid/widget/Button;
    //   2871: aload_0
    //   2872: getfield 655	com/feelingk/iap/gui/parser/ParserXML:bm	Landroid/graphics/drawable/StateListDrawable;
    //   2875: invokevirtual 906	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2878: aload_0
    //   2879: getfield 653	com/feelingk/iap/gui/parser/ParserXML:bE	Landroid/widget/Button;
    //   2882: iconst_0
    //   2883: invokevirtual 1264	android/widget/Button:setClickable	(Z)V
    //   2886: goto -198 -> 2688
    //   2889: aload 4
    //   2891: aload 7
    //   2893: invokestatic 1252	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   2896: invokevirtual 1581	android/widget/EditText:setInputType	(I)V
    //   2899: goto -1890 -> 1009
    //   2902: aload 6
    //   2904: ldc_w 1694
    //   2907: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2910: ifeq +30 -> 2940
    //   2913: aload_0
    //   2914: aload 4
    //   2916: putfield 423	com/feelingk/iap/gui/parser/ParserXML:aJ	Landroid/widget/EditText;
    //   2919: aload_0
    //   2920: getfield 351	com/feelingk/iap/gui/parser/ParserXML:ak	Lcom/feelingk/iap/gui/parser/ParserXML$j;
    //   2923: ifnull -1744 -> 1179
    //   2926: aload_0
    //   2927: getfield 423	com/feelingk/iap/gui/parser/ParserXML:aJ	Landroid/widget/EditText;
    //   2930: aload_0
    //   2931: getfield 489	com/feelingk/iap/gui/parser/ParserXML:bU	Lcom/feelingk/iap/gui/parser/ParserXML$b;
    //   2934: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   2937: goto -1758 -> 1179
    //   2940: aload 6
    //   2942: ldc_w 1696
    //   2945: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2948: ifeq +30 -> 2978
    //   2951: aload_0
    //   2952: aload 4
    //   2954: putfield 425	com/feelingk/iap/gui/parser/ParserXML:aK	Landroid/widget/EditText;
    //   2957: aload_0
    //   2958: getfield 425	com/feelingk/iap/gui/parser/ParserXML:aK	Landroid/widget/EditText;
    //   2961: new 58	com/feelingk/iap/gui/parser/ParserXML$c
    //   2964: dup
    //   2965: aload_0
    //   2966: aload 4
    //   2968: iconst_0
    //   2969: invokespecial 1699	com/feelingk/iap/gui/parser/ParserXML$c:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;B)V
    //   2972: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   2975: goto -1796 -> 1179
    //   2978: aload 6
    //   2980: ldc_w 1701
    //   2983: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2986: ifeq +30 -> 3016
    //   2989: aload_0
    //   2990: aload 4
    //   2992: putfield 427	com/feelingk/iap/gui/parser/ParserXML:aL	Landroid/widget/EditText;
    //   2995: aload_0
    //   2996: getfield 427	com/feelingk/iap/gui/parser/ParserXML:aL	Landroid/widget/EditText;
    //   2999: new 58	com/feelingk/iap/gui/parser/ParserXML$c
    //   3002: dup
    //   3003: aload_0
    //   3004: aload 4
    //   3006: iconst_0
    //   3007: invokespecial 1699	com/feelingk/iap/gui/parser/ParserXML$c:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;B)V
    //   3010: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3013: goto -1834 -> 1179
    //   3016: aload 6
    //   3018: ldc_w 1703
    //   3021: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3024: ifeq +30 -> 3054
    //   3027: aload_0
    //   3028: aload 4
    //   3030: putfield 429	com/feelingk/iap/gui/parser/ParserXML:aM	Landroid/widget/EditText;
    //   3033: aload_0
    //   3034: getfield 429	com/feelingk/iap/gui/parser/ParserXML:aM	Landroid/widget/EditText;
    //   3037: new 58	com/feelingk/iap/gui/parser/ParserXML$c
    //   3040: dup
    //   3041: aload_0
    //   3042: aload 4
    //   3044: iconst_0
    //   3045: invokespecial 1699	com/feelingk/iap/gui/parser/ParserXML$c:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;B)V
    //   3048: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3051: goto -1872 -> 1179
    //   3054: aload 6
    //   3056: ldc_w 1705
    //   3059: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3062: ifeq +12 -> 3074
    //   3065: aload_0
    //   3066: aload 4
    //   3068: putfield 431	com/feelingk/iap/gui/parser/ParserXML:aN	Landroid/widget/EditText;
    //   3071: goto -1892 -> 1179
    //   3074: aload 6
    //   3076: ldc_w 1707
    //   3079: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3082: ifeq +28 -> 3110
    //   3085: aload_0
    //   3086: aload 4
    //   3088: putfield 433	com/feelingk/iap/gui/parser/ParserXML:aO	Landroid/widget/EditText;
    //   3091: aload_0
    //   3092: getfield 433	com/feelingk/iap/gui/parser/ParserXML:aO	Landroid/widget/EditText;
    //   3095: new 61	com/feelingk/iap/gui/parser/ParserXML$d
    //   3098: dup
    //   3099: aload_0
    //   3100: iconst_0
    //   3101: invokespecial 1708	com/feelingk/iap/gui/parser/ParserXML$d:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;B)V
    //   3104: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3107: goto -1928 -> 1179
    //   3110: aload 6
    //   3112: ldc_w 1710
    //   3115: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3118: ifeq +39 -> 3157
    //   3121: aload_0
    //   3122: aload 4
    //   3124: putfield 435	com/feelingk/iap/gui/parser/ParserXML:aP	Landroid/widget/EditText;
    //   3127: getstatic 309	com/feelingk/iap/gui/parser/ParserXML:bO	Z
    //   3130: ifeq +13 -> 3143
    //   3133: aload_0
    //   3134: getfield 435	com/feelingk/iap/gui/parser/ParserXML:aP	Landroid/widget/EditText;
    //   3137: getstatic 311	com/feelingk/iap/gui/parser/ParserXML:bP	Ljava/lang/String;
    //   3140: invokevirtual 884	android/widget/EditText:setText	(Ljava/lang/CharSequence;)V
    //   3143: aload_0
    //   3144: getfield 435	com/feelingk/iap/gui/parser/ParserXML:aP	Landroid/widget/EditText;
    //   3147: aload_0
    //   3148: getfield 486	com/feelingk/iap/gui/parser/ParserXML:bT	Lcom/feelingk/iap/gui/parser/ParserXML$a;
    //   3151: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3154: goto -1975 -> 1179
    //   3157: aload 6
    //   3159: ldc_w 1712
    //   3162: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3165: ifeq +23 -> 3188
    //   3168: aload_0
    //   3169: aload 4
    //   3171: putfield 437	com/feelingk/iap/gui/parser/ParserXML:aQ	Landroid/widget/EditText;
    //   3174: aload_0
    //   3175: getfield 437	com/feelingk/iap/gui/parser/ParserXML:aQ	Landroid/widget/EditText;
    //   3178: aload_0
    //   3179: getfield 486	com/feelingk/iap/gui/parser/ParserXML:bT	Lcom/feelingk/iap/gui/parser/ParserXML$a;
    //   3182: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3185: goto -2006 -> 1179
    //   3188: aload 6
    //   3190: ldc_w 1714
    //   3193: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3196: ifeq +12 -> 3208
    //   3199: aload_0
    //   3200: aload 4
    //   3202: putfield 439	com/feelingk/iap/gui/parser/ParserXML:aR	Landroid/widget/EditText;
    //   3205: goto -2026 -> 1179
    //   3208: aload 6
    //   3210: ldc_w 1716
    //   3213: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3216: ifeq +41 -> 3257
    //   3219: aload_0
    //   3220: aload 4
    //   3222: putfield 441	com/feelingk/iap/gui/parser/ParserXML:aS	Landroid/widget/EditText;
    //   3225: aload_0
    //   3226: getfield 441	com/feelingk/iap/gui/parser/ParserXML:aS	Landroid/widget/EditText;
    //   3229: new 58	com/feelingk/iap/gui/parser/ParserXML$c
    //   3232: dup
    //   3233: aload_0
    //   3234: aload 4
    //   3236: iconst_0
    //   3237: invokespecial 1699	com/feelingk/iap/gui/parser/ParserXML$c:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;B)V
    //   3240: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3243: aload_0
    //   3244: getfield 441	com/feelingk/iap/gui/parser/ParserXML:aS	Landroid/widget/EditText;
    //   3247: aload_0
    //   3248: getfield 489	com/feelingk/iap/gui/parser/ParserXML:bU	Lcom/feelingk/iap/gui/parser/ParserXML$b;
    //   3251: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3254: goto -2075 -> 1179
    //   3257: aload 6
    //   3259: ldc_w 1718
    //   3262: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3265: ifeq +41 -> 3306
    //   3268: aload_0
    //   3269: aload 4
    //   3271: putfield 443	com/feelingk/iap/gui/parser/ParserXML:aT	Landroid/widget/EditText;
    //   3274: aload_0
    //   3275: getfield 443	com/feelingk/iap/gui/parser/ParserXML:aT	Landroid/widget/EditText;
    //   3278: new 58	com/feelingk/iap/gui/parser/ParserXML$c
    //   3281: dup
    //   3282: aload_0
    //   3283: aload 4
    //   3285: iconst_0
    //   3286: invokespecial 1699	com/feelingk/iap/gui/parser/ParserXML$c:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;B)V
    //   3289: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3292: aload_0
    //   3293: getfield 443	com/feelingk/iap/gui/parser/ParserXML:aT	Landroid/widget/EditText;
    //   3296: aload_0
    //   3297: getfield 489	com/feelingk/iap/gui/parser/ParserXML:bU	Lcom/feelingk/iap/gui/parser/ParserXML$b;
    //   3300: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3303: goto -2124 -> 1179
    //   3306: aload 6
    //   3308: ldc_w 1720
    //   3311: invokevirtual 616	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3314: ifeq -2135 -> 1179
    //   3317: aload_0
    //   3318: aload 4
    //   3320: putfield 445	com/feelingk/iap/gui/parser/ParserXML:aU	Landroid/widget/EditText;
    //   3323: aload_0
    //   3324: getfield 445	com/feelingk/iap/gui/parser/ParserXML:aU	Landroid/widget/EditText;
    //   3327: aload_0
    //   3328: getfield 489	com/feelingk/iap/gui/parser/ParserXML:bU	Lcom/feelingk/iap/gui/parser/ParserXML$b;
    //   3331: invokevirtual 1626	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3334: goto -2155 -> 1179
    //   3337: astore 6
    //   3339: goto -1757 -> 1582
    //   3342: astore 10
    //   3344: goto -2693 -> 651
    //   3347: astore 11
    //   3349: goto -2645 -> 704
    //   3352: astore 10
    //   3354: goto -1433 -> 1921
    //   3357: astore 10
    //   3359: goto -1387 -> 1972
    //   3362: astore 9
    //   3364: goto -1086 -> 2278
    //   3367: astore 10
    //   3369: goto -1044 -> 2325
    //   3372: astore 11
    //   3374: goto -998 -> 2376
    //   3377: astore 6
    //   3379: goto -2131 -> 1248
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3382	0	this	ParserXML
    //   0	3382	1	paramXmlPullParser	XmlPullParser
    //   559	2005	2	i1	int
    //   1288	31	3	i2	int
    //   9	3310	4	localObject1	Object
    //   15	2522	5	localAttributeSet	AttributeSet
    //   6	3301	6	localObject2	Object
    //   3337	1	6	localIOException1	IOException
    //   3377	1	6	localIOException2	IOException
    //   383	2509	7	localObject3	Object
    //   393	2231	8	localObject4	Object
    //   403	2150	9	localObject5	Object
    //   3362	1	9	localIOException3	IOException
    //   413	2017	10	localObject6	Object
    //   3342	1	10	localIOException4	IOException
    //   3352	1	10	localIOException5	IOException
    //   3357	1	10	localIOException6	IOException
    //   3367	1	10	localIOException7	IOException
    //   687	1907	11	localObject7	Object
    //   3347	1	11	localIOException8	IOException
    //   3372	1	11	localIOException9	IOException
    // Exception table:
    //   from	to	target	type
    //   1577	1582	3337	java/io/IOException
    //   646	651	3342	java/io/IOException
    //   699	704	3347	java/io/IOException
    //   1916	1921	3352	java/io/IOException
    //   1967	1972	3357	java/io/IOException
    //   2273	2278	3362	java/io/IOException
    //   2320	2325	3367	java/io/IOException
    //   2371	2376	3372	java/io/IOException
    //   1243	1248	3377	java/io/IOException
  }
  
  private View j(XmlPullParser paramXmlPullParser)
  {
    Object localObject1 = paramXmlPullParser.getName();
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject1).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.X);
      if (paramXmlPullParser != null) {
        break label208;
      }
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      if (((String)localObject1).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.X);
        break;
      }
      if (((String)localObject1).equals("Button"))
      {
        paramXmlPullParser = new Button(this.X);
        break;
      }
      if (((String)localObject1).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.X);
        break;
      }
      if (((String)localObject1).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.X);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      if (((String)localObject1).equals("CheckBox"))
      {
        paramXmlPullParser = new CheckBox(this.X);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject1);
      paramXmlPullParser = null;
      break;
      label208:
      Object localObject2;
      label253:
      Object localObject3;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        localObject2 = a(localAttributeSet, "a:orientation");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("horizontal")) {
            break label1003;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = a(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
          ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
        }
      }
      try
      {
        ((InputStream)localObject3).close();
        if (a(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        localObject2 = a(localAttributeSet, "a:gravity");
        if (localObject2 != null)
        {
          if (((String)localObject2).equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          localObject2 = a(localAttributeSet, "a:padding");
          if (localObject2 != null)
          {
            i1 = h((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i1, i1, i1, i1);
          }
          localObject2 = a(localAttributeSet, "a:paddingleft");
          localObject3 = a(localAttributeSet, "a:paddingTop");
          localObject4 = a(localAttributeSet, "a:paddingRight");
          localObject5 = a(localAttributeSet, "a:paddingBottom");
          if (localObject2 == null) {
            break label1615;
          }
          i1 = h((String)localObject2);
          if (localObject3 == null) {
            break label1610;
          }
          i2 = h((String)localObject3);
          if (localObject4 == null) {
            break label1604;
          }
          i3 = h((String)localObject4);
          if (localObject5 == null) {
            break label1598;
          }
          i4 = h((String)localObject5);
          ((LinearLayout)localObject1).setPadding(i1, i2, i3, i4);
          if (a(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof ImageView))
          {
            localObject1 = (ImageView)paramXmlPullParser;
            localObject2 = a(localAttributeSet, "a:src");
            if (localObject2 != null)
            {
              localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
              ((ImageView)localObject1).setImageDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
            }
          }
        }
      }
      catch (IOException localIOException5)
      {
        for (;;)
        {
          try
          {
            ((InputStream)localObject3).close();
            if ((paramXmlPullParser instanceof CheckBox))
            {
              localObject2 = a(localAttributeSet, "a:onImage");
              localObject3 = a(localAttributeSet, "a:checkid");
              localObject1 = (CheckBox)paramXmlPullParser;
              ((CheckBox)localObject1).setTag(localObject3);
              if (!Build.MODEL.endsWith("LT15i")) {
                continue;
              }
              this.f = true;
              i1 = 1;
              if (i1 == 0)
              {
                localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
                localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
              }
            }
          }
          catch (IOException localIOException5)
          {
            try
            {
              ((InputStream)localObject3).close();
              localObject4 = getClass().getResourceAsStream(this.al + "checkbox_y" + ".png");
              localObject3 = Drawable.createFromStream((InputStream)localObject4, "checkbox_y");
            }
            catch (IOException localIOException5)
            {
              try
              {
                ((InputStream)localObject4).close();
                localObject4 = new StateListDrawable();
                localObject5 = new StateListDrawable();
                ((StateListDrawable)localObject4).addState(new int[] { -16842912, -16842908 }, (Drawable)localObject2);
                ((StateListDrawable)localObject4).addState(new int[] { 16842912, -16842908 }, (Drawable)localObject3);
                ((StateListDrawable)localObject5).addState(new int[] { -16842910, -16842908 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { -16842912, 16842919 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { 16842912, 16842919 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { -16842912, -16842908 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { 16842912, -16842908 }, null);
                ((CheckBox)localObject1).setButtonDrawable((Drawable)localObject5);
                ((CheckBox)localObject1).setBackgroundDrawable((Drawable)localObject4);
                ((CheckBox)localObject1).setChecked(false);
                ((CheckBox)localObject1).setOnCheckedChangeListener(this.G);
                localObject1 = paramXmlPullParser;
                if (this.V.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.V.peek()));
                return paramXmlPullParser;
                label1003:
                if (!((String)localObject2).equals("vertical")) {
                  break label253;
                }
                ((LinearLayout)localObject1).setOrientation(1);
                break label253;
                if (((String)localObject2).equals("right"))
                {
                  ((LinearLayout)localObject1).setGravity(5);
                  continue;
                }
                if (!((String)localObject2).equals("left")) {
                  continue;
                }
                ((LinearLayout)localObject1).setGravity(3);
                continue;
                if ((paramXmlPullParser instanceof Button))
                {
                  localObject3 = a(localAttributeSet, "a:offImage");
                  localObject1 = a(localAttributeSet, "a:onImage");
                  localObject2 = (Button)paramXmlPullParser;
                  localObject4 = getClass().getResourceAsStream(this.al + (String)localObject3 + ".png");
                  localObject3 = Drawable.createFromStream((InputStream)localObject4, (String)localObject3);
                }
              }
              catch (IOException localIOException5)
              {
                try
                {
                  ((InputStream)localObject4).close();
                  localObject5 = getClass().getResourceAsStream(this.al + (String)localObject1 + ".png");
                  localObject4 = Drawable.createFromStream((InputStream)localObject5, (String)localObject1);
                }
                catch (IOException localIOException5)
                {
                  try
                  {
                    ((InputStream)localObject5).close();
                    Object localObject5 = new StateListDrawable();
                    ((StateListDrawable)localObject5).addState(new int[] { 16842919 }, (Drawable)localObject4);
                    ((StateListDrawable)localObject5).addState(new int[0], (Drawable)localObject3);
                    ((Button)localObject2).setBackgroundDrawable((Drawable)localObject5);
                    if (((String)localObject1).equals("btn_con_sel"))
                    {
                      ((Button)localObject2).setOnClickListener(this.B);
                      continue;
                    }
                    if (!((String)localObject1).equals("btn_buycancel_sel")) {
                      continue;
                    }
                    ((Button)localObject2).setOnClickListener(this.C);
                    continue;
                    if (!(paramXmlPullParser instanceof TextView)) {
                      continue;
                    }
                    localObject1 = (TextView)paramXmlPullParser;
                    localObject2 = a(localAttributeSet, "a:nortext");
                    localObject3 = a(localAttributeSet, "a:nametext");
                    Object localObject4 = a(localAttributeSet, "a:centertext");
                    localObject5 = a(localAttributeSet, "a:agreetext");
                    String str1 = a(localAttributeSet, "a:textSize");
                    String str2 = a(localAttributeSet, "a:textColor");
                    String str3 = a(localAttributeSet, "a:gravity");
                    a(localAttributeSet, "a:id");
                    if (localObject2 != null) {
                      ((TextView)localObject1).setText((CharSequence)localObject2);
                    }
                    if (localObject3 != null) {
                      ((TextView)localObject1).setText(CommonString.a(CommonString.Index.S));
                    }
                    if (localObject4 != null)
                    {
                      ((String)localObject4).replace("\\n", "\n");
                      ((TextView)localObject1).setText(CommonString.a(CommonString.Index.f));
                    }
                    if (localObject5 != null) {
                      ((TextView)localObject1).setText((CharSequence)localObject5);
                    }
                    if (str1 != null) {
                      ((TextView)localObject1).setTextSize(1, g(str1));
                    }
                    if (str2 != null) {
                      ((TextView)localObject1).setTextColor(Color.parseColor(str2));
                    }
                    if (str3 != null)
                    {
                      if (str3.equals("center")) {
                        ((TextView)localObject1).setGravity(17);
                      }
                    }
                    else
                    {
                      ((TextView)localObject1).setLineSpacing(0.0F, 1.15F);
                      continue;
                    }
                    if (str3.equals("right"))
                    {
                      ((TextView)localObject1).setGravity(5);
                      continue;
                    }
                    if (str3.equals("left"))
                    {
                      ((TextView)localObject1).setGravity(3);
                      continue;
                    }
                    ((TextView)localObject1).setGravity(19);
                    continue;
                    localIOException2 = localIOException2;
                    continue;
                    localIOException1 = localIOException1;
                    continue;
                    localIOException3 = localIOException3;
                    continue;
                    localIOException4 = localIOException4;
                    continue;
                    localIOException5 = localIOException5;
                  }
                  catch (IOException localIOException6)
                  {
                    continue;
                  }
                }
              }
            }
            i1 = 0;
            continue;
          }
          label1598:
          int i4 = 0;
          continue;
          label1604:
          int i3 = 0;
          continue;
          label1610:
          int i2 = 0;
          continue;
          label1615:
          int i1 = 0;
        }
      }
    }
  }
  
  private View k(XmlPullParser paramXmlPullParser)
  {
    Object localObject1 = paramXmlPullParser.getName();
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject1).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.X);
      if (paramXmlPullParser != null) {
        break label208;
      }
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      if (((String)localObject1).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.X);
        break;
      }
      if (((String)localObject1).equals("Button"))
      {
        paramXmlPullParser = new Button(this.X);
        break;
      }
      if (((String)localObject1).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.X);
        break;
      }
      if (((String)localObject1).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.X);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      if (((String)localObject1).equals("CheckBox"))
      {
        paramXmlPullParser = new CheckBox(this.X);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject1);
      paramXmlPullParser = null;
      break;
      label208:
      Object localObject2;
      label253:
      Object localObject3;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        localObject2 = a(localAttributeSet, "a:orientation");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("horizontal")) {
            break label1003;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = a(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
          ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
        }
      }
      try
      {
        ((InputStream)localObject3).close();
        if (a(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        localObject2 = a(localAttributeSet, "a:gravity");
        if (localObject2 != null)
        {
          if (((String)localObject2).equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          localObject2 = a(localAttributeSet, "a:padding");
          if (localObject2 != null)
          {
            i1 = h((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i1, i1, i1, i1);
          }
          localObject2 = a(localAttributeSet, "a:paddingleft");
          localObject3 = a(localAttributeSet, "a:paddingTop");
          localObject4 = a(localAttributeSet, "a:paddingRight");
          localObject5 = a(localAttributeSet, "a:paddingBottom");
          if (localObject2 == null) {
            break label1817;
          }
          i1 = h((String)localObject2);
          if (localObject3 == null) {
            break label1812;
          }
          i2 = h((String)localObject3);
          if (localObject4 == null) {
            break label1806;
          }
          i3 = h((String)localObject4);
          if (localObject5 == null) {
            break label1800;
          }
          i4 = h((String)localObject5);
          ((LinearLayout)localObject1).setPadding(i1, i2, i3, i4);
          if (a(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof ImageView))
          {
            localObject1 = (ImageView)paramXmlPullParser;
            localObject2 = a(localAttributeSet, "a:src");
            if (localObject2 != null)
            {
              localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
              ((ImageView)localObject1).setImageDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
            }
          }
        }
      }
      catch (IOException localIOException7)
      {
        for (;;)
        {
          try
          {
            ((InputStream)localObject3).close();
            if ((paramXmlPullParser instanceof CheckBox))
            {
              localObject2 = a(localAttributeSet, "a:onImage");
              localObject3 = a(localAttributeSet, "a:checkid");
              localObject1 = (CheckBox)paramXmlPullParser;
              ((CheckBox)localObject1).setTag(localObject3);
              if (!Build.MODEL.endsWith("LT15i")) {
                continue;
              }
              this.f = true;
              i1 = 1;
              if (i1 == 0)
              {
                localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
                localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
              }
            }
          }
          catch (IOException localIOException7)
          {
            try
            {
              ((InputStream)localObject3).close();
              localObject4 = getClass().getResourceAsStream(this.al + "checkbox_y" + ".png");
              localObject3 = Drawable.createFromStream((InputStream)localObject4, "checkbox_y");
            }
            catch (IOException localIOException7)
            {
              try
              {
                ((InputStream)localObject4).close();
                localObject4 = new StateListDrawable();
                localObject5 = new StateListDrawable();
                ((StateListDrawable)localObject4).addState(new int[] { -16842912, -16842908 }, (Drawable)localObject2);
                ((StateListDrawable)localObject4).addState(new int[] { 16842912, -16842908 }, (Drawable)localObject3);
                ((StateListDrawable)localObject5).addState(new int[] { -16842910, -16842908 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { -16842912, 16842919 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { 16842912, 16842919 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { -16842912, -16842908 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { 16842912, -16842908 }, null);
                ((CheckBox)localObject1).setButtonDrawable((Drawable)localObject5);
                ((CheckBox)localObject1).setBackgroundDrawable((Drawable)localObject4);
                ((CheckBox)localObject1).setChecked(false);
                ((CheckBox)localObject1).setOnCheckedChangeListener(this.G);
                localObject1 = paramXmlPullParser;
                if (this.V.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.V.peek()));
                return paramXmlPullParser;
                label1003:
                if (!((String)localObject2).equals("vertical")) {
                  break label253;
                }
                ((LinearLayout)localObject1).setOrientation(1);
                break label253;
                if (((String)localObject2).equals("right"))
                {
                  ((LinearLayout)localObject1).setGravity(5);
                  continue;
                }
                if (!((String)localObject2).equals("left")) {
                  continue;
                }
                ((LinearLayout)localObject1).setGravity(3);
                continue;
                if ((paramXmlPullParser instanceof Button))
                {
                  localObject4 = a(localAttributeSet, "a:offImage");
                  localObject1 = a(localAttributeSet, "a:onImage");
                  localObject2 = (Button)paramXmlPullParser;
                  if (((String)localObject1).equals("bt_confirm_dim")) {
                    this.br = ((Button)localObject2);
                  }
                  localObject3 = getClass().getResourceAsStream(this.al + (String)localObject4 + ".png");
                  localObject4 = Drawable.createFromStream((InputStream)localObject3, (String)localObject4);
                }
              }
              catch (IOException localIOException7)
              {
                try
                {
                  ((InputStream)localObject3).close();
                  localObject3 = getClass().getResourceAsStream(this.al + (String)localObject1 + ".png");
                  localObject5 = Drawable.createFromStream((InputStream)localObject3, (String)localObject1);
                }
                catch (IOException localIOException7)
                {
                  try
                  {
                    ((InputStream)localObject3).close();
                    localObject3 = new StateListDrawable();
                    ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject5);
                    ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
                    if (((String)localObject1).equals("bt_confirm_dim"))
                    {
                      this.bo = ((StateListDrawable)localObject3);
                      this.bn = new StateListDrawable();
                      localObject5 = getClass().getResourceAsStream(this.al + "btn_con_nor.png");
                      localObject4 = Drawable.createFromStream((InputStream)localObject5, "btn_con_nor");
                    }
                  }
                  catch (IOException localIOException7)
                  {
                    try
                    {
                      ((InputStream)localObject5).close();
                      localObject6 = getClass().getResourceAsStream(this.al + "btn_con_sel.png");
                      localObject5 = Drawable.createFromStream((InputStream)localObject6, "btn_con_sel");
                    }
                    catch (IOException localIOException7)
                    {
                      try
                      {
                        ((InputStream)localObject6).close();
                        this.bn.addState(new int[] { 16842919 }, (Drawable)localObject5);
                        this.bn.addState(new int[0], (Drawable)localObject4);
                        if (this.aG[0] != 0)
                        {
                          this.br.setBackgroundDrawable(this.bn);
                          this.br.setOnClickListener(this.D);
                          if (!((String)localObject1).equals("btn_cancel_sel_h")) {
                            continue;
                          }
                          ((Button)localObject2).setOnClickListener(this.E);
                          continue;
                        }
                        this.br.setBackgroundDrawable((Drawable)localObject3);
                        continue;
                        ((Button)localObject2).setBackgroundDrawable((Drawable)localObject3);
                        continue;
                        if (!(paramXmlPullParser instanceof TextView)) {
                          continue;
                        }
                        localObject1 = (TextView)paramXmlPullParser;
                        localObject2 = a(localAttributeSet, "a:nortext");
                        localObject3 = a(localAttributeSet, "a:nametext");
                        Object localObject4 = a(localAttributeSet, "a:centertext");
                        Object localObject5 = a(localAttributeSet, "a:agreetext");
                        Object localObject6 = a(localAttributeSet, "a:textSize");
                        String str1 = a(localAttributeSet, "a:textColor");
                        String str2 = a(localAttributeSet, "a:gravity");
                        a(localAttributeSet, "a:id");
                        if (localObject2 != null) {
                          ((TextView)localObject1).setText((CharSequence)localObject2);
                        }
                        if (localObject3 != null) {
                          ((TextView)localObject1).setText(CommonString.a(CommonString.Index.T));
                        }
                        if (localObject4 != null)
                        {
                          ((String)localObject4).replace("\\n", "\n");
                          ((TextView)localObject1).setText(CommonString.a(CommonString.Index.g));
                        }
                        if (localObject5 != null) {
                          ((TextView)localObject1).setText((CharSequence)localObject5);
                        }
                        if (localObject6 != null) {
                          ((TextView)localObject1).setTextSize(1, g((String)localObject6));
                        }
                        if (str1 != null) {
                          ((TextView)localObject1).setTextColor(Color.parseColor(str1));
                        }
                        if (str2 != null)
                        {
                          if (str2.equals("center")) {
                            ((TextView)localObject1).setGravity(17);
                          }
                        }
                        else
                        {
                          ((TextView)localObject1).setLineSpacing(0.0F, 1.15F);
                          continue;
                        }
                        if (str2.equals("right"))
                        {
                          ((TextView)localObject1).setGravity(5);
                          continue;
                        }
                        if (str2.equals("left"))
                        {
                          ((TextView)localObject1).setGravity(3);
                          continue;
                        }
                        ((TextView)localObject1).setGravity(19);
                        continue;
                        localIOException2 = localIOException2;
                        continue;
                        localIOException1 = localIOException1;
                        continue;
                        localIOException3 = localIOException3;
                        continue;
                        localIOException6 = localIOException6;
                        continue;
                        localIOException4 = localIOException4;
                        continue;
                        localIOException5 = localIOException5;
                        continue;
                        localIOException7 = localIOException7;
                      }
                      catch (IOException localIOException8)
                      {
                        continue;
                      }
                    }
                  }
                }
              }
            }
            i1 = 0;
            continue;
          }
          label1800:
          int i4 = 0;
          continue;
          label1806:
          int i3 = 0;
          continue;
          label1812:
          int i2 = 0;
          continue;
          label1817:
          int i1 = 0;
        }
      }
    }
  }
  
  private View l(XmlPullParser paramXmlPullParser)
  {
    Object localObject2 = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject2).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.X);
      if (paramXmlPullParser != null) {
        break label212;
      }
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      if (((String)localObject2).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.X);
        break;
      }
      if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.X);
        break;
      }
      if (((String)localObject2).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.X);
        break;
      }
      if (((String)localObject2).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.X);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      if (((String)localObject2).equals("CheckBox"))
      {
        paramXmlPullParser = new CheckBox(this.X);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject2);
      paramXmlPullParser = (XmlPullParser)localObject1;
      break;
      label212:
      label257:
      Object localObject3;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        localObject2 = a(localAttributeSet, "a:orientation");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("horizontal")) {
            break label1207;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = a(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("dot_line")) {
            break label1227;
          }
          localObject3 = getClass().getResourceAsStream(this.al + "line_dot_01.png");
          localObject2 = Drawable.createFromStream((InputStream)localObject3, null);
        }
      }
      try
      {
        ((InputStream)localObject3).close();
        ((BitmapDrawable)localObject2).setTileModeX(Shader.TileMode.REPEAT);
        ((BitmapDrawable)localObject2).setTileModeY(Shader.TileMode.REPEAT);
        ((LinearLayout)localObject1).setBackgroundDrawable((Drawable)localObject2);
        label357:
        if (a(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        localObject2 = a(localAttributeSet, "a:gravity");
        if (localObject2 != null)
        {
          if (((String)localObject2).equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          label409:
          localObject2 = a(localAttributeSet, "a:padding");
          if (localObject2 != null)
          {
            i1 = h((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i1, i1, i1, i1);
          }
          localObject2 = a(localAttributeSet, "a:paddingleft");
          localObject3 = a(localAttributeSet, "a:paddingTop");
          localObject4 = a(localAttributeSet, "a:paddingRight");
          localObject5 = a(localAttributeSet, "a:paddingBottom");
          i1 = 0;
          int i2 = 0;
          int i3 = 0;
          int i4 = 0;
          if (localObject2 != null) {
            i1 = h((String)localObject2);
          }
          if (localObject3 != null) {
            i2 = h((String)localObject3);
          }
          if (localObject4 != null) {
            i3 = h((String)localObject4);
          }
          if (localObject5 != null) {
            i4 = h((String)localObject5);
          }
          ((LinearLayout)localObject1).setPadding(i1, i2, i3, i4);
          if (a(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof ImageView))
          {
            localObject1 = (ImageView)paramXmlPullParser;
            localObject2 = a(localAttributeSet, "a:src");
            if (localObject2 != null)
            {
              localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
              ((ImageView)localObject1).setImageDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
            }
          }
        }
      }
      catch (IOException localIOException6)
      {
        try
        {
          int i1;
          ((InputStream)localObject3).close();
          if ((paramXmlPullParser instanceof TextView))
          {
            localObject3 = (TextView)paramXmlPullParser;
            localObject4 = a(localAttributeSet, "a:id");
            localObject2 = a(localAttributeSet, "a:text");
            localObject5 = a(localAttributeSet, "a:textSize");
            String str1 = a(localAttributeSet, "a:textColor");
            String str2 = a(localAttributeSet, "a:gravity");
            localObject1 = localObject2;
            if (localObject2 != null)
            {
              localObject1 = ((String)localObject2).replace("\\n", "\n");
              ((TextView)localObject3).setText((CharSequence)localObject1);
            }
            if (localObject5 != null) {
              ((TextView)localObject3).setTextSize(1, g((String)localObject5));
            }
            if (str1 != null) {
              ((TextView)localObject3).setTextColor(Color.parseColor(str1));
            }
            if (localObject4 != null) {
              ((TextView)localObject3).setText((CharSequence)localObject1);
            }
            if (str2 != null)
            {
              ((TextView)localObject3).setGravity(17);
              label798:
              ((TextView)localObject3).setLineSpacing(0.0F, 1.15F);
            }
          }
          else
          {
            if (!(paramXmlPullParser instanceof CheckBox)) {
              break label1328;
            }
            localObject1 = (CheckBox)paramXmlPullParser;
            ((CheckBox)localObject1).setTag(a(localAttributeSet, "a:checkid"));
            localObject2 = Build.MODEL;
            i1 = 0;
            if (((String)localObject2).endsWith("LT15i"))
            {
              i1 = 1;
              this.f = true;
            }
            if (i1 == 0)
            {
              localObject2 = a(localAttributeSet, "a:onImage");
              localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
              localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
            }
          }
        }
        catch (IOException localIOException6)
        {
          try
          {
            ((InputStream)localObject3).close();
            localObject4 = getClass().getResourceAsStream(this.al + "checkbox_y" + ".png");
            localObject3 = Drawable.createFromStream((InputStream)localObject4, "checkbox_y");
          }
          catch (IOException localIOException6)
          {
            try
            {
              ((InputStream)localObject4).close();
              localObject4 = new StateListDrawable();
              localObject5 = new StateListDrawable();
              ((StateListDrawable)localObject4).addState(new int[] { -16842912, -16842908 }, (Drawable)localObject2);
              ((StateListDrawable)localObject4).addState(new int[] { 16842912, -16842908 }, (Drawable)localObject3);
              ((StateListDrawable)localObject5).addState(new int[] { -16842910, -16842908 }, null);
              ((StateListDrawable)localObject5).addState(new int[] { -16842912, 16842919 }, null);
              ((StateListDrawable)localObject5).addState(new int[] { 16842912, 16842919 }, null);
              ((StateListDrawable)localObject5).addState(new int[] { -16842912, -16842908 }, null);
              ((StateListDrawable)localObject5).addState(new int[] { 16842912, -16842908 }, null);
              ((CheckBox)localObject1).setBackgroundDrawable((Drawable)localObject4);
              ((CheckBox)localObject1).setButtonDrawable((Drawable)localObject5);
              ((CheckBox)localObject1).setChecked(false);
              ((CheckBox)localObject1).setOnCheckedChangeListener(this.G);
              label1207:
              label1227:
              label1328:
              do
              {
                do
                {
                  localObject1 = paramXmlPullParser;
                  if (this.V.size() <= 0) {
                    break;
                  }
                  paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.V.peek()));
                  return paramXmlPullParser;
                  if (!((String)localObject2).equals("vertical")) {
                    break label257;
                  }
                  ((LinearLayout)localObject1).setOrientation(1);
                  break label257;
                  localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
                  ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
                  try
                  {
                    ((InputStream)localObject3).close();
                  }
                  catch (IOException localIOException2) {}
                  break label357;
                  if (localIOException2.equals("left"))
                  {
                    ((LinearLayout)localObject1).setGravity(3);
                    break label409;
                  }
                  ((LinearLayout)localObject1).setGravity(5);
                  break label409;
                  ((TextView)localObject3).setGravity(19);
                  break label798;
                } while (!(paramXmlPullParser instanceof Button));
                localObject3 = a(localAttributeSet, "a:offImage");
                localObject1 = a(localAttributeSet, "a:onImage");
                a(localAttributeSet, "a:gravity");
              } while (localObject3 == null);
              localButton = (Button)paramXmlPullParser;
              localObject4 = getClass().getResourceAsStream(this.al + (String)localObject3 + ".png");
              localObject3 = Drawable.createFromStream((InputStream)localObject4, (String)localObject3);
            }
            catch (IOException localIOException6)
            {
              try
              {
                ((InputStream)localObject4).close();
                localObject5 = getClass().getResourceAsStream(this.al + (String)localObject1 + ".png");
                localObject4 = Drawable.createFromStream((InputStream)localObject5, (String)localObject1);
              }
              catch (IOException localIOException6)
              {
                try
                {
                  for (;;)
                  {
                    Object localObject4;
                    Button localButton;
                    ((InputStream)localObject5).close();
                    Object localObject5 = new StateListDrawable();
                    ((StateListDrawable)localObject5).addState(new int[] { 16842919 }, (Drawable)localObject4);
                    ((StateListDrawable)localObject5).addState(new int[0], (Drawable)localObject3);
                    localButton.setBackgroundDrawable((Drawable)localObject5);
                    if (((String)localObject1).equals("btn_con_sel"))
                    {
                      localButton.setOnClickListener(this.K);
                    }
                    else if (((String)localObject1).equals("btn_cancel_sel_h"))
                    {
                      localButton.setOnClickListener(this.L);
                    }
                    else if (((String)localObject1).equals("btn_terms_sel_b"))
                    {
                      localButton.setOnClickListener(this.H);
                    }
                    else if (((String)localObject1).equals("btn_terms_sel_b2"))
                    {
                      localButton.setOnClickListener(this.I);
                    }
                    else if (((String)localObject1).equals("btn_policy_sel_b"))
                    {
                      localButton.setOnClickListener(this.J);
                      continue;
                      localIOException3 = localIOException3;
                      continue;
                      localIOException1 = localIOException1;
                      continue;
                      localIOException4 = localIOException4;
                      continue;
                      localIOException5 = localIOException5;
                    }
                  }
                  localIOException6 = localIOException6;
                }
                catch (IOException localIOException7)
                {
                  for (;;) {}
                }
              }
            }
          }
        }
      }
    }
  }
  
  private View m(XmlPullParser paramXmlPullParser)
  {
    Object localObject2 = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject2).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.X);
      if (paramXmlPullParser != null) {
        break label191;
      }
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      if (((String)localObject2).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.X);
        break;
      }
      if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.X);
        break;
      }
      if (((String)localObject2).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.X);
        break;
      }
      if (((String)localObject2).equals("EditText"))
      {
        paramXmlPullParser = new EditText(this.X);
        ((EditText)paramXmlPullParser).setImeOptions(268435456);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject2);
      paramXmlPullParser = (XmlPullParser)localObject1;
      break;
      label191:
      label236:
      Object localObject3;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        localObject2 = a(localAttributeSet, "a:orientation");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("horizontal")) {
            break label1065;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = a(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
          ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
        }
      }
      try
      {
        ((InputStream)localObject3).close();
        if (a(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        localObject2 = a(localAttributeSet, "a:gravity");
        if (localObject2 != null)
        {
          if (((String)localObject2).equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          localObject2 = a(localAttributeSet, "a:padding");
          if (localObject2 != null)
          {
            i1 = h((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i1, i1, i1, i1);
          }
          if (a(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof ImageView))
          {
            localObject1 = (ImageView)paramXmlPullParser;
            localObject2 = a(localAttributeSet, "a:src");
            if (localObject2 != null)
            {
              localObject3 = getClass().getResourceAsStream(this.al + (String)localObject2 + ".png");
              ((ImageView)localObject1).setImageDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
            }
          }
        }
      }
      catch (IOException localIOException3)
      {
        try
        {
          ((InputStream)localObject3).close();
          if ((paramXmlPullParser instanceof TextView))
          {
            localObject1 = (TextView)paramXmlPullParser;
            a(localAttributeSet, "a:id");
            localObject2 = a(localAttributeSet, "a:text");
            localObject3 = a(localAttributeSet, "a:otpnumbertext");
            localObject4 = a(localAttributeSet, "a:infotext");
            localObject5 = a(localAttributeSet, "a:textSize");
            String str1 = a(localAttributeSet, "a:textColor");
            String str2 = a(localAttributeSet, "a:gravity");
            if (localObject2 != null) {
              ((TextView)localObject1).setText(((String)localObject2).replace("\\n", "\n"));
            }
            if (localObject3 != null)
            {
              i2 = 0;
              i1 = 1;
              if (i1 > 20)
              {
                ((String)localObject3).replace("\\n", "\n");
                ((TextView)localObject1).setText(String.valueOf(i2));
                this.aW = String.valueOf(i2);
              }
            }
            else
            {
              if (localObject4 != null) {
                ((TextView)localObject1).setText(((String)localObject4).replace("\\n", "\n"));
              }
              if (localObject5 != null) {
                ((TextView)localObject1).setTextSize(1, g((String)localObject5));
              }
              if (str1 != null) {
                ((TextView)localObject1).setTextColor(Color.parseColor(str1));
              }
              if (str2 == null) {
                break label1114;
              }
              ((TextView)localObject1).setGravity(17);
              ((TextView)localObject1).setLineSpacing(0.0F, 1.15F);
            }
          }
          else if ((paramXmlPullParser instanceof Button))
          {
            localObject3 = a(localAttributeSet, "a:offImage");
            localObject1 = a(localAttributeSet, "a:onImage");
            localObject2 = (Button)paramXmlPullParser;
            localObject4 = getClass().getResourceAsStream(this.al + (String)localObject3 + ".png");
            localObject3 = Drawable.createFromStream((InputStream)localObject4, (String)localObject3);
          }
        }
        catch (IOException localIOException3)
        {
          try
          {
            ((InputStream)localObject4).close();
            localObject5 = getClass().getResourceAsStream(this.al + (String)localObject1 + ".png");
            localObject4 = Drawable.createFromStream((InputStream)localObject5, (String)localObject1);
          }
          catch (IOException localIOException3)
          {
            try
            {
              for (;;)
              {
                int i1;
                Object localObject4;
                ((InputStream)localObject5).close();
                Object localObject5 = new StateListDrawable();
                ((StateListDrawable)localObject5).addState(new int[] { 16842919 }, (Drawable)localObject4);
                ((StateListDrawable)localObject5).addState(new int[0], (Drawable)localObject3);
                ((Button)localObject2).setBackgroundDrawable((Drawable)localObject5);
                if (((String)localObject1).equals("btn_locking_sel")) {
                  ((Button)localObject2).setOnClickListener(this.R);
                }
                if ((paramXmlPullParser instanceof EditText))
                {
                  localObject1 = (EditText)paramXmlPullParser;
                  ((EditText)localObject1).setHint(CommonString.a(CommonString.Index.X));
                  ((EditText)localObject1).setFocusable(true);
                  ((EditText)localObject1).setBackgroundDrawable(null);
                  ((EditText)localObject1).setInputType(2);
                  localObject2 = a(localAttributeSet, "a:maxLength");
                  if (localObject2 != null)
                  {
                    ((EditText)localObject1).setSingleLine();
                    ((EditText)localObject1).setFilters(new InputFilter[] { new InputFilter.LengthFilter(Integer.parseInt((String)localObject2)) });
                  }
                  a(localAttributeSet, "a:id");
                  ((EditText)localObject1).addTextChangedListener(new TextWatcher()
                  {
                    String a;
                    
                    public final void afterTextChanged(Editable paramAnonymousEditable)
                    {
                      if (this.a.length() == 4)
                      {
                        if (ParserXML.ai(ParserXML.this).equals(this.a)) {
                          ParserXML.N(ParserXML.this).a();
                        }
                      }
                      else {
                        return;
                      }
                      this.a = "";
                      this.c.setText("");
                      this.c.setHint("잘못된 인증번호를 입력하셨습니다.");
                    }
                    
                    public final void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
                    
                    public final void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
                    {
                      this.a = paramAnonymousCharSequence.toString();
                    }
                  });
                  ((EditText)localObject1).setOnClickListener(new View.OnClickListener()
                  {
                    public final void onClick(View paramAnonymousView)
                    {
                      if (ParserXML.this.e) {
                        this.b.setHint("");
                      }
                      ParserXML.this.e = false;
                    }
                  });
                }
                localObject1 = paramXmlPullParser;
                if (this.V.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.V.peek()));
                return paramXmlPullParser;
                label1065:
                if (!((String)localObject2).equals("vertical")) {
                  break label236;
                }
                ((LinearLayout)localObject1).setOrientation(1);
                break label236;
                ((LinearLayout)localObject1).setGravity(5);
                continue;
                int i2 = (int)(Math.random() * 9000.0D + 1000.0D);
                i1 += 1;
                continue;
                label1114:
                ((TextView)localObject1).setGravity(19);
                continue;
                localIOException2 = localIOException2;
                continue;
                localIOException1 = localIOException1;
              }
              localIOException3 = localIOException3;
            }
            catch (IOException localIOException4)
            {
              for (;;) {}
            }
          }
        }
      }
    }
  }
  
  private View n(XmlPullParser paramXmlPullParser)
  {
    String str = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (str.equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.X);
      if (paramXmlPullParser != null) {
        break label222;
      }
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      if (str.equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.X);
        break;
      }
      if (str.equals("Button"))
      {
        paramXmlPullParser = new Button(this.X);
        break;
      }
      if (str.equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.X);
        break;
      }
      if (str.equals("EditText"))
      {
        paramXmlPullParser = new EditText(this.X);
        ((EditText)paramXmlPullParser).setImeOptions(268435456);
        break;
      }
      if (str.equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.X);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      Assert.fail("# UnSupported tag:" + str);
      paramXmlPullParser = (XmlPullParser)localObject1;
      break;
      label222:
      label267:
      Object localObject2;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        str = a(localAttributeSet, "a:orientation");
        if (str != null)
        {
          if (!str.equals("horizontal")) {
            break label1714;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        str = a(localAttributeSet, "a:background");
        if (str != null)
        {
          localObject2 = getClass().getResourceAsStream(this.al + str + ".png");
          ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject2, str));
        }
      }
      try
      {
        ((InputStream)localObject2).close();
        label336:
        if (a(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        str = a(localAttributeSet, "a:gravity");
        if (str != null)
        {
          if (str.equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          label388:
          str = a(localAttributeSet, "a:padding");
          if (str != null)
          {
            i1 = h(str);
            ((LinearLayout)localObject1).setPadding(i1, i1, i1, i1);
          }
          if (a(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof EditText))
          {
            localObject1 = (EditText)paramXmlPullParser;
            ((EditText)localObject1).setGravity(5);
            ((EditText)localObject1).setInputType(2);
            str = a(localAttributeSet, "a:background");
            if (str != null)
            {
              localObject2 = getClass().getResourceAsStream(this.al + str + ".png");
              ((EditText)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject2, str));
            }
          }
        }
      }
      catch (IOException localIOException2)
      {
        try
        {
          ((InputStream)localObject2).close();
          if (a(localAttributeSet, "a:lgu_auth_text") != null)
          {
            bt = (EditText)localObject1;
            if (com.feelingk.iap.b.f() != null)
            {
              ((EditText)localObject1).setText(com.feelingk.iap.b.f());
              ((EditText)localObject1).setTextColor(Color.parseColor("#FF6F00"));
              ((EditText)localObject1).setTextSize(1, 30.0F);
              bu.setBackgroundDrawable(bj);
              label589:
              bt.addTextChangedListener(new TextWatcher()
              {
                String a;
                
                public final void afterTextChanged(Editable paramAnonymousEditable)
                {
                  if (this.a.length() != 6) {
                    ParserXML.b(Boolean.valueOf(true));
                  }
                  if ((ParserXML.g().booleanValue()) && (this.a.length() == 6))
                  {
                    ParserXML.b(Boolean.valueOf(false));
                    com.feelingk.iap.b.k(this.a);
                  }
                }
                
                public final void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
                
                public final void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
                {
                  this.a = paramAnonymousCharSequence.toString();
                }
              });
            }
          }
          else
          {
            if ((paramXmlPullParser instanceof TextView))
            {
              localObject1 = (TextView)paramXmlPullParser;
              str = a(localAttributeSet, "a:text");
              a(localAttributeSet, "a:lgu_auth_text");
              a(localAttributeSet, "a:lgu_info_text");
              localObject2 = a(localAttributeSet, "a:textSize");
              localObject3 = a(localAttributeSet, "a:textColor");
              localObject4 = a(localAttributeSet, "a:gravity");
              if (str != null) {
                ((TextView)localObject1).setText(str.replace("\\n", "\n"));
              }
              if (localObject2 != null) {
                ((TextView)localObject1).setTextSize(1, g((String)localObject2));
              }
              if (localObject3 != null) {
                ((TextView)localObject1).setTextColor(Color.parseColor((String)localObject3));
              }
              if (localObject4 == null) {
                break label1757;
              }
              ((TextView)localObject1).setGravity(17);
              label739:
              ((TextView)localObject1).setLineSpacing(0.0F, 1.15F);
              if (com.feelingk.iap.b.f() != null)
              {
                bt.setText(com.feelingk.iap.b.f());
                bt.setTextColor(Color.parseColor("#FF6F00"));
                bt.setTextSize(1, 30.0F);
                bu.setText(CommonString.a(CommonString.Index.V));
                bu.setTextColor(Color.parseColor("#999999"));
              }
            }
            if ((paramXmlPullParser instanceof Button))
            {
              localObject1 = a(localAttributeSet, "a:offImage");
              str = a(localAttributeSet, "a:onImage");
              localObject2 = a(localAttributeSet, "a:id");
              this.bp = ((Button)paramXmlPullParser);
              localObject3 = a(localAttributeSet, "a:padding");
              if (localObject3 != null)
              {
                i1 = h((String)localObject3);
                this.bp.setPadding(i1, i1, i1, i1);
              }
              if (localObject2 != null)
              {
                if (((String)localObject2).equals("lgu_smsAuth_btn")) {
                  this.bp.setPadding(0, 0, 0, 0);
                }
                this.bp.setText(CommonString.a(CommonString.Index.W));
              }
              if (((String)localObject1).equals("bt_01_nor.9"))
              {
                localObject3 = this.bp;
                bu = (Button)localObject3;
                ((Button)localObject3).setPadding(0, 0, 0, 0);
                bu.setText(CommonString.a(CommonString.Index.Z));
              }
              if (((String)localObject1).equals("bt_confirm_dim")) {
                bv = this.bp;
              }
              this.h = getClass().getResourceAsStream(this.al + (String)localObject1 + ".png");
              this.n = Drawable.createFromStream(this.h, (String)localObject1);
            }
          }
        }
        catch (IOException localIOException2)
        {
          try
          {
            int i1;
            Object localObject3;
            Object localObject4;
            this.h.close();
            this.h = getClass().getResourceAsStream(this.al + str + ".png");
            this.o = Drawable.createFromStream(this.h, str);
            try
            {
              this.h.close();
              this.aX = new StateListDrawable();
              localObject3 = this.aX;
              localObject4 = this.o;
              ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject4);
              localObject3 = this.aX;
              localObject4 = this.n;
              ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
              this.j = getClass().getResourceAsStream(this.al + "bt_01_reclaim_nor" + ".png");
              this.p = Drawable.createFromStream(this.j, "bt_01_reclaim_nor");
              try
              {
                this.j.close();
                this.j = getClass().getResourceAsStream(this.al + "bt_01_reclaim_sel" + ".png");
                this.q = Drawable.createFromStream(this.j, "bt_01_reclaim_sel");
                try
                {
                  this.j.close();
                  localObject3 = new StateListDrawable();
                  bj = (StateListDrawable)localObject3;
                  localObject4 = this.q;
                  ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject4);
                  localObject3 = bj;
                  localObject4 = this.p;
                  ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
                  this.k = getClass().getResourceAsStream(this.al + "btn_con_nor" + ".png");
                  this.r = Drawable.createFromStream(this.k, "btn_con_nor");
                  try
                  {
                    this.k.close();
                    this.k = getClass().getResourceAsStream(this.al + "btn_con_sel" + ".png");
                    this.s = Drawable.createFromStream(this.k, "btn_con_sel");
                    try
                    {
                      this.k.close();
                      localObject3 = new StateListDrawable();
                      bk = (StateListDrawable)localObject3;
                      localObject4 = this.s;
                      ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject4);
                      localObject3 = bk;
                      localObject4 = this.r;
                      ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
                      this.bp.setBackgroundDrawable(this.aX);
                      if ((bw.booleanValue()) && (((String)localObject1).equals("bt_01_sel.9")))
                      {
                        this.bp.setText(CommonString.a(CommonString.Index.V));
                        this.bp.setTextColor(Color.parseColor("#999999"));
                      }
                      if (com.feelingk.iap.b.f() != null)
                      {
                        bu.setText(CommonString.a(CommonString.Index.V));
                        bu.setTextColor(Color.parseColor("#999999"));
                        bv.setBackgroundDrawable(bk);
                        bv.setOnClickListener(T);
                      }
                      if ((localObject2 != null) && (((String)localObject2).equals("lgu_smsAuth_btn"))) {
                        this.bp.setPadding(0, 0, 0, 0);
                      }
                      if (str.equals("bt_01_sel.9")) {
                        this.bp.setOnClickListener(this.S);
                      }
                      label1714:
                      label1757:
                      label1816:
                      do
                      {
                        for (;;)
                        {
                          localObject1 = paramXmlPullParser;
                          if (this.V.size() <= 0) {
                            break;
                          }
                          paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.V.peek()));
                          return paramXmlPullParser;
                          if (!str.equals("vertical")) {
                            break label267;
                          }
                          ((LinearLayout)localObject1).setOrientation(1);
                          break label267;
                          ((LinearLayout)localObject1).setGravity(5);
                          break label388;
                          ((EditText)localObject1).setText(CommonString.a(CommonString.Index.U));
                          break label589;
                          ((TextView)localObject1).setGravity(19);
                          break label739;
                          if (str.equals("btn_con_sel"))
                          {
                            this.bp.setOnClickListener(T);
                          }
                          else
                          {
                            if (!str.equals("btn_cancel_sel_h")) {
                              break label1816;
                            }
                            this.bp.setOnClickListener(this.U);
                          }
                        }
                      } while (!str.equals("btn_locking_sel"));
                      localObject1 = this.X.getPackageManager().getInstalledApplications(0);
                      int i2 = ((List)localObject1).size();
                      i1 = 0;
                      for (;;)
                      {
                        if (i1 >= i2)
                        {
                          this.bp.setOnClickListener(this.N);
                          break;
                        }
                        if (((ApplicationInfo)((List)localObject1).get(i1)).packageName.indexOf("com.skt.skaf.A000Z00040") == 0) {
                          this.aD = true;
                        }
                        i1 += 1;
                      }
                      localIOException1 = localIOException1;
                      break label336;
                      localIOException2 = localIOException2;
                    }
                    catch (IOException localIOException3)
                    {
                      for (;;) {}
                    }
                  }
                  catch (IOException localIOException4)
                  {
                    for (;;) {}
                  }
                }
                catch (IOException localIOException5)
                {
                  for (;;) {}
                }
              }
              catch (IOException localIOException6)
              {
                for (;;) {}
              }
            }
            catch (IOException localIOException7)
            {
              for (;;) {}
            }
          }
          catch (IOException localIOException8)
          {
            for (;;) {}
          }
        }
      }
    }
  }
  
  public final View a(String paramString1, String paramString2, Object paramObject)
  {
    this.ap = paramString2;
    this.c = ((View.OnClickListener)paramObject);
    return b(paramString1);
  }
  
  public final View a(String paramString1, String paramString2, Object paramObject, int paramInt)
  {
    this.ap = null;
    this.c = ((View.OnClickListener)paramObject);
    this.a = paramInt;
    Log.e("ParserXML", "# Dlg Start !! GUI-rotate =" + paramInt);
    if ((this.a == 0) || (this.a == 2)) {}
    for (paramString1 = paramString1 + "w.xml";; paramString1 = paramString1 + "h.xml") {
      return b(paramString1);
    }
  }
  
  public final View a(String paramString1, String paramString2, String paramString3, Object paramObject, int paramInt)
  {
    this.ao = paramString2;
    this.ap = paramString3;
    this.c = ((View.OnClickListener)paramObject);
    this.a = paramInt;
    Log.e("ParserXML", "# AutoPurchaseForm용 Start !! GUI-rotate =" + paramInt);
    if ((this.a == 0) || (this.a == 2)) {}
    for (paramString1 = paramString1 + "W.xml";; paramString1 = paramString1 + "H.xml") {
      return b(paramString1);
    }
  }
  
  public final String a()
  {
    if (com.feelingk.iap.util.a.a(this.X) == 1) {
      return String.format("%s", new Object[] { this.am });
    }
    return String.format("%s", new Object[] { this.an });
  }
  
  public final View b(String paramString)
  {
    for (;;)
    {
      try
      {
        localXmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
        localInputStream = getClass().getResourceAsStream(paramString);
        localXmlPullParser.setInput(localInputStream, "utf-8");
        if ((!this.ar) && (!this.az) && (!this.aA) && (!this.aB)) {
          continue;
        }
        paramString = c(localXmlPullParser);
      }
      catch (XmlPullParserException paramString)
      {
        InputStream localInputStream;
        String str;
        paramString.printStackTrace();
        return null;
        localStack.push(new StringBuffer());
        localView = b(localXmlPullParser, str);
        if (localView == null) {
          continue;
        }
        if (paramString != null) {
          continue;
        }
        paramString = localView;
        localObject = paramString;
        if (!(localView instanceof ViewGroup)) {
          continue;
        }
        this.V.push((ViewGroup)localView);
        localObject = paramString;
        continue;
      }
      catch (IOException paramString)
      {
        XmlPullParser localXmlPullParser;
        Stack localStack;
        int i1;
        View localView;
        paramString.printStackTrace();
        continue;
        Object localObject = (ViewGroup)this.V.peek();
        if (localView == null) {
          continue;
        }
        ((ViewGroup)localObject).addView(localView);
        continue;
        ((StringBuffer)localStack.peek()).append(localXmlPullParser.getText());
        localObject = paramString;
        continue;
        localStack.pop();
        if (!localXmlPullParser.getName().endsWith("Layout")) {
          continue;
        }
        this.V.pop();
        localObject = paramString;
        if (!localXmlPullParser.getName().endsWith("ScrollView")) {
          continue;
        }
        this.V.pop();
        localObject = paramString;
        continue;
        if (!this.at) {
          continue;
        }
        paramString = a(localXmlPullParser, this.ap);
        continue;
        if (!this.au) {
          continue;
        }
        paramString = d(localXmlPullParser);
        continue;
        if (!this.ay) {
          continue;
        }
        paramString = e(localXmlPullParser);
        continue;
        if (!this.av) {
          continue;
        }
        paramString = f(localXmlPullParser);
        continue;
        if (!this.aw) {
          continue;
        }
        paramString = g(localXmlPullParser);
        continue;
        if (!this.ax) {
          continue;
        }
        paramString = h(localXmlPullParser);
        continue;
        paramString = a(localXmlPullParser);
        continue;
        if (i1 == 1) {
          continue;
        }
        localObject = paramString;
        switch (i1)
        {
        }
        localObject = paramString;
        continue;
        continue;
      }
      localInputStream.close();
      return paramString;
      if (!this.aC) {
        continue;
      }
      paramString = c(localXmlPullParser);
    }
    if (this.as)
    {
      str = this.ap;
      this.V.clear();
      this.W.clear();
      localStack = new Stack();
      i1 = localXmlPullParser.getEventType();
      paramString = null;
      break label465;
      for (;;)
      {
        i1 = localXmlPullParser.next();
        paramString = (String)localObject;
        break;
        localStack.clear();
        localObject = paramString;
      }
    }
  }
  
  public final void c(String paramString)
  {
    Log.i("ParserXML", "add_payments_amount: " + paramString);
    int i2;
    int i1;
    if (paramString.equals("OCB"))
    {
      i2 = Integer.parseInt(com.feelingk.iap.b.p());
      i1 = com.feelingk.iap.gui.a.b.a().b - (i2 - i2 % 10);
      if (i1 > 0)
      {
        com.feelingk.iap.gui.a.b.a().b = i1;
        com.feelingk.iap.gui.a.b.a().d = i2;
        com.feelingk.iap.gui.a.b.a().d -= com.feelingk.iap.gui.a.b.a().d % 10;
        paramString = com.feelingk.iap.gui.a.b.a();
        if (com.feelingk.iap.gui.a.b.a().d <= 0)
        {
          i1 = 0;
          paramString.d = i1;
          this.bF.setText(i2 - com.feelingk.iap.gui.a.b.a().d + " P");
          this.g.setText(String.valueOf(com.feelingk.iap.gui.a.b.a().b) + CommonString.a(CommonString.Index.K));
          label187:
          com.feelingk.iap.gui.a.b.a().c = (com.feelingk.iap.gui.a.b.a().d + com.feelingk.iap.gui.a.b.a().e + com.feelingk.iap.gui.a.b.a().f + com.feelingk.iap.gui.a.b.a().g);
          this.aH.setText(com.feelingk.iap.gui.a.b.a().c + CommonString.a(CommonString.Index.K));
          if ((com.feelingk.iap.gui.a.b.a().c != com.feelingk.iap.gui.a.b.a().a) || (!com.feelingk.iap.b.n())) {
            break label2524;
          }
          this.bq.setBackgroundDrawable(this.aY);
          this.t = false;
          this.bq.setOnClickListener(this.M);
        }
      }
    }
    label743:
    label780:
    label939:
    label949:
    label2524:
    while ((com.feelingk.iap.gui.a.b.a().c >= com.feelingk.iap.gui.a.b.a().a) || (!com.feelingk.iap.b.n()))
    {
      return;
      i1 = com.feelingk.iap.gui.a.b.a().d;
      break;
      com.feelingk.iap.gui.a.b.a().d = com.feelingk.iap.gui.a.b.a().b;
      com.feelingk.iap.gui.a.b.a().d -= com.feelingk.iap.gui.a.b.a().d % 10;
      paramString = com.feelingk.iap.gui.a.b.a();
      if (com.feelingk.iap.gui.a.b.a().d <= 0) {}
      for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().d)
      {
        paramString.d = i1;
        Log.i("ParserXML", "OCB 사용액: " + com.feelingk.iap.gui.a.b.a().d + " P");
        com.feelingk.iap.gui.a.b.a().b = 0;
        this.g.setText(com.feelingk.iap.gui.a.b.a().d % 10 + CommonString.a(CommonString.Index.K));
        this.bF.setText(i2 - com.feelingk.iap.gui.a.b.a().d + " P");
        if (com.feelingk.iap.gui.a.b.a().e == 0)
        {
          this.bz.setBackgroundDrawable(this.bb);
          this.bz.setTextColor(Color.parseColor("#8B8B8B"));
          this.bz.setClickable(false);
          this.bz.setPadding(0, 0, 0, 0);
        }
        if (com.feelingk.iap.gui.a.b.a().f == 0)
        {
          this.bA.setBackgroundDrawable(this.bd);
          this.bA.setTextColor(Color.parseColor("#8B8B8B"));
          this.bA.setClickable(false);
          this.bA.setPadding(0, 0, 0, 0);
        }
        if (com.feelingk.iap.gui.a.b.a().g != 0) {
          break;
        }
        this.bB.setBackgroundDrawable(this.bf);
        this.bB.setTextColor(Color.parseColor("#8B8B8B"));
        this.bB.setClickable(false);
        this.bB.setPadding(0, 0, 0, 0);
        break;
      }
      if (paramString.equals("DOTORI"))
      {
        i1 = com.feelingk.iap.gui.a.b.a().b - (this.d.i - this.d.i % 100);
        if (i1 > 0)
        {
          if ((com.feelingk.iap.gui.a.b.a().b > 100000) && (this.d.i > 100000))
          {
            com.feelingk.iap.gui.a.b.a().b -= 100000;
            com.feelingk.iap.gui.a.b.a().e = 100000;
            i1 = 1;
            com.feelingk.iap.gui.a.b.a().e -= com.feelingk.iap.gui.a.b.a().e % 100;
            paramString = com.feelingk.iap.gui.a.b.a();
            if (com.feelingk.iap.gui.a.b.a().e > 0) {
              break label939;
            }
            i2 = 0;
            paramString.e = i2;
            if (i1 == 0) {
              break label949;
            }
            this.bG.setText((this.d.i - com.feelingk.iap.gui.a.b.a().e) / 100 + CommonString.a(CommonString.Index.A));
          }
          for (;;)
          {
            this.g.setText(String.valueOf(com.feelingk.iap.gui.a.b.a().b) + CommonString.a(CommonString.Index.K));
            Log.i("ParserXML", "도토리 사용액: " + com.feelingk.iap.gui.a.b.a().e + CommonString.a(CommonString.Index.K));
            break;
            com.feelingk.iap.gui.a.b.a().b = i1;
            com.feelingk.iap.gui.a.b.a().e = this.d.i;
            i1 = 0;
            break label743;
            i2 = com.feelingk.iap.gui.a.b.a().e;
            break label780;
            this.bG.setText("0" + CommonString.a(CommonString.Index.A));
          }
        }
        if ((com.feelingk.iap.gui.a.b.a().b > 100000) && (this.d.i > 100000))
        {
          com.feelingk.iap.gui.a.b.a().e = 100000;
          com.feelingk.iap.gui.a.b.a().e -= com.feelingk.iap.gui.a.b.a().e % 100;
          paramString = com.feelingk.iap.gui.a.b.a();
          if (com.feelingk.iap.gui.a.b.a().e <= 0) {}
          for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().e)
          {
            paramString.e = i1;
            Log.i("ParserXML", "도토리 사용액: " + com.feelingk.iap.gui.a.b.a().e + CommonString.a(CommonString.Index.K));
            com.feelingk.iap.gui.a.b.a().e = (com.feelingk.iap.gui.a.b.a().e / 100 * 100);
            this.g.setText(com.feelingk.iap.gui.a.b.a().b - com.feelingk.iap.gui.a.b.a().e + CommonString.a(CommonString.Index.K));
            com.feelingk.iap.gui.a.b.a().b -= com.feelingk.iap.gui.a.b.a().e;
            i1 = (this.d.i - com.feelingk.iap.gui.a.b.a().e) / 100;
            this.bG.setText(i1 + CommonString.a(CommonString.Index.A));
            if (com.feelingk.iap.gui.a.b.a().b >= 10) {
              break;
            }
            if (com.feelingk.iap.gui.a.b.a().d == 0)
            {
              this.by.setBackgroundDrawable(this.aZ);
              this.by.setTextColor(Color.parseColor("#8B8B8B"));
              this.by.setClickable(false);
              this.by.setPadding(0, 0, 0, 0);
            }
            if (com.feelingk.iap.gui.a.b.a().f == 0)
            {
              this.bA.setBackgroundDrawable(this.bd);
              this.bA.setTextColor(Color.parseColor("#8B8B8B"));
              this.bA.setClickable(false);
              this.bA.setPadding(0, 0, 0, 0);
            }
            if (com.feelingk.iap.gui.a.b.a().g != 0) {
              break;
            }
            this.bB.setBackgroundDrawable(this.bf);
            this.bB.setTextColor(Color.parseColor("#8B8B8B"));
            this.bB.setClickable(false);
            this.bB.setPadding(0, 0, 0, 0);
            break;
          }
        }
        com.feelingk.iap.gui.a.b.a().e = com.feelingk.iap.gui.a.b.a().b;
        com.feelingk.iap.gui.a.b.a().e -= com.feelingk.iap.gui.a.b.a().e % 100;
        paramString = com.feelingk.iap.gui.a.b.a();
        if (com.feelingk.iap.gui.a.b.a().e <= 0) {}
        for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().e)
        {
          paramString.e = i1;
          break;
        }
      }
      if (paramString.equals("CULTURE"))
      {
        i2 = Integer.parseInt(com.feelingk.iap.b.r());
        i1 = com.feelingk.iap.gui.a.b.a().b - (i2 - i2 % 10);
        if (i1 > 0)
        {
          com.feelingk.iap.gui.a.b.a().b = i1;
          com.feelingk.iap.gui.a.b.a().f = i2;
          com.feelingk.iap.gui.a.b.a().f -= com.feelingk.iap.gui.a.b.a().f % 10;
          paramString = com.feelingk.iap.gui.a.b.a();
          if (com.feelingk.iap.gui.a.b.a().f <= 0) {}
          for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().f)
          {
            paramString.f = i1;
            this.bH.setText(i2 - com.feelingk.iap.gui.a.b.a().f + CommonString.a(CommonString.Index.K));
            this.g.setText(String.valueOf(com.feelingk.iap.gui.a.b.a().b) + CommonString.a(CommonString.Index.K));
            break;
          }
        }
        com.feelingk.iap.gui.a.b.a().f = com.feelingk.iap.gui.a.b.a().b;
        com.feelingk.iap.gui.a.b.a().f -= com.feelingk.iap.gui.a.b.a().f % 10;
        paramString = com.feelingk.iap.gui.a.b.a();
        if (com.feelingk.iap.gui.a.b.a().f <= 0) {}
        for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().f)
        {
          paramString.f = i1;
          Log.i("ParserXML", "CULTURE 사용액: " + com.feelingk.iap.gui.a.b.a().f + " P");
          com.feelingk.iap.gui.a.b.a().b = 0;
          this.g.setText(com.feelingk.iap.gui.a.b.a().f % 10 + CommonString.a(CommonString.Index.K));
          this.bH.setText(i2 - com.feelingk.iap.gui.a.b.a().f + CommonString.a(CommonString.Index.K));
          if (com.feelingk.iap.gui.a.b.a().d == 0)
          {
            this.by.setBackgroundDrawable(this.aZ);
            this.by.setTextColor(Color.parseColor("#8B8B8B"));
            this.by.setClickable(false);
            this.by.setPadding(0, 0, 0, 0);
          }
          if (com.feelingk.iap.gui.a.b.a().e == 0)
          {
            this.bz.setBackgroundDrawable(this.bb);
            this.bz.setTextColor(Color.parseColor("#8B8B8B"));
            this.bz.setClickable(false);
            this.bz.setPadding(0, 0, 0, 0);
          }
          if (com.feelingk.iap.gui.a.b.a().g != 0) {
            break;
          }
          this.bB.setBackgroundDrawable(this.bf);
          this.bB.setTextColor(Color.parseColor("#8B8B8B"));
          this.bB.setClickable(false);
          this.bB.setPadding(0, 0, 0, 0);
          break;
        }
      }
      if (!paramString.equals("TCASH")) {
        break label187;
      }
      i1 = com.feelingk.iap.gui.a.b.a().b - (this.d.d - this.d.d % 10);
      if (i1 > 0)
      {
        com.feelingk.iap.gui.a.b.a().b = i1;
        com.feelingk.iap.gui.a.b.a().g = this.d.d;
        com.feelingk.iap.gui.a.b.a().g -= com.feelingk.iap.gui.a.b.a().g % 10;
        paramString = com.feelingk.iap.gui.a.b.a();
        if (com.feelingk.iap.gui.a.b.a().g <= 0) {}
        for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().g)
        {
          paramString.g = i1;
          this.bI.setText(this.d.d - com.feelingk.iap.gui.a.b.a().g + " P");
          this.g.setText(String.valueOf(com.feelingk.iap.gui.a.b.a().b) + CommonString.a(CommonString.Index.K));
          break;
        }
      }
      com.feelingk.iap.gui.a.b.a().g = com.feelingk.iap.gui.a.b.a().b;
      com.feelingk.iap.gui.a.b.a().g -= com.feelingk.iap.gui.a.b.a().g % 10;
      paramString = com.feelingk.iap.gui.a.b.a();
      if (com.feelingk.iap.gui.a.b.a().g <= 0) {}
      for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().g)
      {
        paramString.g = i1;
        Log.i("ParserXML", "티캐쉬 사용액: " + com.feelingk.iap.gui.a.b.a().g + "P");
        com.feelingk.iap.gui.a.b.a().b = 0;
        this.g.setText(com.feelingk.iap.gui.a.b.a().g % 10 + CommonString.a(CommonString.Index.K));
        this.bI.setText(this.d.d - com.feelingk.iap.gui.a.b.a().g + " P");
        if (com.feelingk.iap.gui.a.b.a().d == 0)
        {
          this.by.setBackgroundDrawable(this.aZ);
          this.by.setTextColor(Color.parseColor("#8B8B8B"));
          this.by.setClickable(false);
          this.by.setPadding(0, 0, 0, 0);
        }
        if (com.feelingk.iap.gui.a.b.a().e == 0)
        {
          this.bz.setBackgroundDrawable(this.bb);
          this.bz.setTextColor(Color.parseColor("#8B8B8B"));
          this.bz.setClickable(false);
          this.bz.setPadding(0, 0, 0, 0);
        }
        if (com.feelingk.iap.gui.a.b.a().f != 0) {
          break;
        }
        this.bA.setBackgroundDrawable(this.bd);
        this.bA.setTextColor(Color.parseColor("#8B8B8B"));
        this.bA.setClickable(false);
        this.bA.setPadding(0, 0, 0, 0);
        break;
      }
    }
    this.bq.setBackgroundDrawable(this.bl);
    this.t = true;
    this.bq.setOnClickListener(null);
  }
  
  public final void d(String paramString)
  {
    Log.i("ParserXML", "del_payments_amount: " + paramString);
    if (paramString.equals("OCB"))
    {
      com.feelingk.iap.gui.a.b.a().b += com.feelingk.iap.gui.a.b.a().d;
      com.feelingk.iap.gui.a.b.a().d = 0;
      this.g.setText(com.feelingk.iap.gui.a.b.a().b + CommonString.a(CommonString.Index.K));
      this.bF.setText(com.feelingk.iap.b.p() + " P");
      if (this.d.i != 0)
      {
        this.bz.setBackgroundDrawable(this.bc);
        this.bz.setTextColor(Color.parseColor("#CFCFCF"));
        this.bz.setClickable(true);
        this.bz.setPadding(0, 0, 0, 0);
      }
      if (!com.feelingk.iap.b.r().equals("0"))
      {
        this.bA.setBackgroundDrawable(this.be);
        this.bA.setTextColor(Color.parseColor("#CFCFCF"));
        this.bA.setClickable(true);
        this.bA.setPadding(0, 0, 0, 0);
      }
      if (this.d.d > 0)
      {
        this.bB.setBackgroundDrawable(this.bg);
        this.bB.setTextColor(Color.parseColor("#CFCFCF"));
        this.bB.setClickable(true);
        this.bB.setPadding(0, 0, 0, 0);
      }
      com.feelingk.iap.gui.a.b.a().c = (com.feelingk.iap.gui.a.b.a().d + com.feelingk.iap.gui.a.b.a().e + com.feelingk.iap.gui.a.b.a().f + com.feelingk.iap.gui.a.b.a().g);
      this.aH.setText(com.feelingk.iap.gui.a.b.a().c + CommonString.a(CommonString.Index.K));
      if ((com.feelingk.iap.gui.a.b.a().c != com.feelingk.iap.gui.a.b.a().a) || (!com.feelingk.iap.b.n())) {
        break label1181;
      }
      this.bq.setBackgroundDrawable(this.aY);
      this.t = false;
      this.bq.setOnClickListener(this.M);
    }
    label1181:
    while ((com.feelingk.iap.gui.a.b.a().c >= com.feelingk.iap.gui.a.b.a().a) || (!com.feelingk.iap.b.n()))
    {
      return;
      if (paramString.equals("DOTORI"))
      {
        com.feelingk.iap.gui.a.b.a().b += com.feelingk.iap.gui.a.b.a().e;
        com.feelingk.iap.gui.a.b.a().e = 0;
        this.g.setText(com.feelingk.iap.gui.a.b.a().b + CommonString.a(CommonString.Index.K));
        int i1 = this.d.i / 100;
        this.bG.setText(i1 + CommonString.a(CommonString.Index.A));
        this.by.setBackgroundDrawable(this.ba);
        this.by.setTextColor(Color.parseColor("#CFCFCF"));
        this.by.setClickable(true);
        this.by.setPadding(0, 0, 0, 0);
        if (!com.feelingk.iap.b.r().equals("0"))
        {
          this.bA.setBackgroundDrawable(this.be);
          this.bA.setTextColor(Color.parseColor("#CFCFCF"));
          this.bA.setClickable(true);
          this.bA.setPadding(0, 0, 0, 0);
        }
        if (this.d.d <= 0) {
          break;
        }
        this.bB.setBackgroundDrawable(this.bg);
        this.bB.setTextColor(Color.parseColor("#CFCFCF"));
        this.bB.setClickable(true);
        this.bB.setPadding(0, 0, 0, 0);
        break;
      }
      if (paramString.equals("CULTURE"))
      {
        com.feelingk.iap.gui.a.b.a().b += com.feelingk.iap.gui.a.b.a().f;
        com.feelingk.iap.gui.a.b.a().f = 0;
        this.g.setText(com.feelingk.iap.gui.a.b.a().b + CommonString.a(CommonString.Index.K));
        this.bH.setText(com.feelingk.iap.b.r() + CommonString.a(CommonString.Index.K));
        this.by.setBackgroundDrawable(this.ba);
        this.by.setTextColor(Color.parseColor("#CFCFCF"));
        this.by.setClickable(true);
        this.by.setPadding(0, 0, 0, 0);
        if (this.d.i != 0)
        {
          this.bz.setBackgroundDrawable(this.bc);
          this.bz.setTextColor(Color.parseColor("#CFCFCF"));
          this.bz.setClickable(true);
          this.bz.setPadding(0, 0, 0, 0);
        }
        if (this.d.d <= 0) {
          break;
        }
        this.bB.setBackgroundDrawable(this.bg);
        this.bB.setTextColor(Color.parseColor("#CFCFCF"));
        this.bB.setClickable(true);
        this.bB.setPadding(0, 0, 0, 0);
        break;
      }
      if (!paramString.equals("TCASH")) {
        break;
      }
      com.feelingk.iap.gui.a.b.a().b += com.feelingk.iap.gui.a.b.a().g;
      com.feelingk.iap.gui.a.b.a().g = 0;
      this.g.setText(com.feelingk.iap.gui.a.b.a().b + CommonString.a(CommonString.Index.K));
      this.bI.setText(this.d.d + " P");
      this.by.setBackgroundDrawable(this.ba);
      this.by.setTextColor(Color.parseColor("#CFCFCF"));
      this.by.setClickable(true);
      this.by.setPadding(0, 0, 0, 0);
      if (this.d.i != 0)
      {
        this.bz.setBackgroundDrawable(this.bc);
        this.bz.setTextColor(Color.parseColor("#CFCFCF"));
        this.bz.setClickable(true);
        this.bz.setPadding(0, 0, 0, 0);
      }
      if (com.feelingk.iap.b.r().equals("0")) {
        break;
      }
      this.bA.setBackgroundDrawable(this.be);
      this.bA.setTextColor(Color.parseColor("#CFCFCF"));
      this.bA.setClickable(true);
      this.bA.setPadding(0, 0, 0, 0);
      break;
    }
    this.bq.setBackgroundDrawable(this.bl);
    this.t = true;
    this.bq.setOnClickListener(null);
  }
  
  private final class a
    implements TextWatcher
  {
    private a() {}
    
    public final void afterTextChanged(Editable paramEditable)
    {
      if ((ParserXML.h(ParserXML.this).getText().length() <= 0) || (ParserXML.i(ParserXML.this).getText().length() <= 0))
      {
        ParserXML.R(ParserXML.this).setBackgroundDrawable(ParserXML.P(ParserXML.this));
        ParserXML.R(ParserXML.this).setClickable(false);
        return;
      }
      ParserXML.R(ParserXML.this).setBackgroundDrawable(ParserXML.Q(ParserXML.this));
      ParserXML.R(ParserXML.this).setClickable(true);
    }
    
    public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
  
  private final class b
    implements TextWatcher
  {
    private b() {}
    
    public final void afterTextChanged(Editable paramEditable)
    {
      if (ParserXML.this.F.a() == 1) {
        if (ParserXML.n(ParserXML.this).getText().length() + ParserXML.o(ParserXML.this).getText().length() + ParserXML.p(ParserXML.this).getText().length() >= 10)
        {
          ParserXML.S(ParserXML.this).setBackgroundDrawable(ParserXML.Q(ParserXML.this));
          ParserXML.S(ParserXML.this).setClickable(true);
        }
      }
      while ((ParserXML.this.F.a() != 2) && (ParserXML.this.F.a() != 3))
      {
        return;
        ParserXML.S(ParserXML.this).setBackgroundDrawable(ParserXML.P(ParserXML.this));
        ParserXML.S(ParserXML.this).setClickable(false);
        return;
      }
      if ((ParserXML.n(ParserXML.this).getText().length() + ParserXML.o(ParserXML.this).getText().length() + ParserXML.p(ParserXML.this).getText().length() >= 10) && (ParserXML.b(ParserXML.this).getText().length() + ParserXML.c(ParserXML.this).getText().length() == 13))
      {
        ParserXML.S(ParserXML.this).setBackgroundDrawable(ParserXML.Q(ParserXML.this));
        ParserXML.S(ParserXML.this).setClickable(true);
        return;
      }
      ParserXML.S(ParserXML.this).setBackgroundDrawable(ParserXML.P(ParserXML.this));
      ParserXML.S(ParserXML.this).setClickable(false);
    }
    
    public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
  
  private final class c
    implements TextWatcher
  {
    private EditText b;
    private int c;
    
    private c(EditText paramEditText)
    {
      this.b = paramEditText;
    }
    
    public final void afterTextChanged(Editable paramEditable)
    {
      if (ParserXML.T(ParserXML.this) != null)
      {
        ParserXML.T(ParserXML.this).setPadding(0, 0, 0, 0);
        if (paramEditable.length() < 4) {
          break label293;
        }
        if (this.b == ParserXML.r(ParserXML.this))
        {
          ParserXML.s(ParserXML.this).requestFocus();
          ParserXML.a(ParserXML.this, paramEditable.length());
          this.c = (ParserXML.U(ParserXML.this) + ParserXML.V(ParserXML.this) + ParserXML.W(ParserXML.this));
          if (this.c < 12) {
            break label383;
          }
          ParserXML.T(ParserXML.this).setBackgroundDrawable(ParserXML.X(ParserXML.this));
          ParserXML.T(ParserXML.this).setClickable(true);
          ParserXML.T(ParserXML.this).setTextColor(Color.parseColor("#DDDDDD"));
          ParserXML.T(ParserXML.this).setPadding(0, 0, 0, 0);
        }
      }
      else
      {
        label165:
        if (ParserXML.m(ParserXML.this) != null)
        {
          if ((this.b != ParserXML.n(ParserXML.this)) || (paramEditable.length() < 3)) {
            break label443;
          }
          ParserXML.o(ParserXML.this).requestFocus();
        }
      }
      label293:
      label383:
      label443:
      while ((this.b != ParserXML.o(ParserXML.this)) || (paramEditable.length() < 4))
      {
        return;
        if (this.b == ParserXML.s(ParserXML.this))
        {
          ParserXML.t(ParserXML.this).requestFocus();
          ParserXML.b(ParserXML.this, paramEditable.length());
          break;
        }
        if (this.b != ParserXML.t(ParserXML.this)) {
          break;
        }
        ParserXML.u(ParserXML.this).requestFocus();
        ParserXML.c(ParserXML.this, paramEditable.length());
        break;
        if (this.b == ParserXML.r(ParserXML.this))
        {
          ParserXML.a(ParserXML.this, paramEditable.length());
          break;
        }
        if (this.b == ParserXML.s(ParserXML.this))
        {
          ParserXML.b(ParserXML.this, paramEditable.length());
          break;
        }
        if (this.b != ParserXML.t(ParserXML.this)) {
          break;
        }
        ParserXML.c(ParserXML.this, paramEditable.length());
        break;
        ParserXML.T(ParserXML.this).setBackgroundDrawable(ParserXML.Y(ParserXML.this));
        ParserXML.T(ParserXML.this).setClickable(false);
        ParserXML.T(ParserXML.this).setTextColor(Color.parseColor("#8B8B8B"));
        ParserXML.T(ParserXML.this).setPadding(0, 0, 0, 0);
        break label165;
      }
      ParserXML.p(ParserXML.this).requestFocus();
    }
    
    public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
  
  private final class d
    implements TextWatcher
  {
    private d() {}
    
    public final void afterTextChanged(Editable paramEditable)
    {
      if (ParserXML.f(ParserXML.this).getText().length() < 6)
      {
        ParserXML.O(ParserXML.this).setBackgroundDrawable(ParserXML.P(ParserXML.this));
        ParserXML.O(ParserXML.this).setClickable(false);
        return;
      }
      ParserXML.O(ParserXML.this).setBackgroundDrawable(ParserXML.Q(ParserXML.this));
      ParserXML.O(ParserXML.this).setClickable(true);
    }
    
    public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
  
  public final class e
    implements View.OnClickListener
  {
    RadioButton a;
    RadioButton b;
    RadioButton c;
    
    public e() {}
    
    public final int a()
    {
      if (this.a.isChecked())
      {
        this.a.addTextChangedListener(ParserXML.Z(ParserXML.this));
        return 1;
      }
      if (this.b.isChecked())
      {
        this.b.addTextChangedListener(ParserXML.Z(ParserXML.this));
        return 2;
      }
      if (this.c.isChecked())
      {
        this.c.addTextChangedListener(ParserXML.Z(ParserXML.this));
        return 3;
      }
      return 0;
    }
    
    public final void onClick(View paramView)
    {
      if (paramView == this.a)
      {
        this.a.setChecked(true);
        this.b.setChecked(false);
        this.c.setChecked(false);
        ParserXML.e().setVisibility(8);
        ParserXML.d(ParserXML.this, 1);
        ParserXML.S(ParserXML.this).setBackgroundDrawable(ParserXML.P(ParserXML.this));
        ParserXML.S(ParserXML.this).setClickable(false);
        if (ParserXML.n(ParserXML.this).getText().length() + ParserXML.o(ParserXML.this).getText().length() + ParserXML.p(ParserXML.this).getText().length() >= 10)
        {
          ParserXML.S(ParserXML.this).setBackgroundDrawable(ParserXML.Q(ParserXML.this));
          ParserXML.S(ParserXML.this).setClickable(true);
        }
      }
      do
      {
        do
        {
          do
          {
            return;
            if (paramView != this.b) {
              break;
            }
            this.a.setChecked(false);
            this.b.setChecked(true);
            this.c.setChecked(false);
            ParserXML.e().setVisibility(0);
            ParserXML.d(ParserXML.this, 2);
            ParserXML.S(ParserXML.this).setBackgroundDrawable(ParserXML.P(ParserXML.this));
            ParserXML.S(ParserXML.this).setClickable(false);
          } while ((ParserXML.n(ParserXML.this).getText().length() + ParserXML.o(ParserXML.this).getText().length() + ParserXML.p(ParserXML.this).getText().length() < 10) || (ParserXML.b(ParserXML.this).getText().length() + ParserXML.c(ParserXML.this).getText().length() != 13));
          ParserXML.S(ParserXML.this).setBackgroundDrawable(ParserXML.Q(ParserXML.this));
          ParserXML.S(ParserXML.this).setClickable(true);
          return;
        } while (paramView != this.c);
        this.a.setChecked(false);
        this.b.setChecked(false);
        this.c.setChecked(true);
        ParserXML.e().setVisibility(0);
        ParserXML.d(ParserXML.this, 3);
        ParserXML.S(ParserXML.this).setBackgroundDrawable(ParserXML.P(ParserXML.this));
        ParserXML.S(ParserXML.this).setClickable(false);
      } while ((ParserXML.n(ParserXML.this).getText().length() + ParserXML.o(ParserXML.this).getText().length() + ParserXML.p(ParserXML.this).getText().length() < 10) || (ParserXML.b(ParserXML.this).getText().length() + ParserXML.c(ParserXML.this).getText().length() != 13));
      ParserXML.S(ParserXML.this).setBackgroundDrawable(ParserXML.Q(ParserXML.this));
      ParserXML.S(ParserXML.this).setClickable(true);
    }
  }
  
  public static abstract interface f
  {
    public abstract void a();
    
    public abstract void a(String paramString1, String paramString2);
  }
  
  public static abstract interface g
  {
    public abstract void a();
    
    public abstract void a(boolean paramBoolean);
  }
  
  public static abstract interface h
  {
    public abstract void a();
    
    public abstract void a(String paramString1, String paramString2);
  }
  
  public static abstract interface i
  {
    public abstract void a();
    
    public abstract void b();
    
    public abstract void c();
  }
  
  public static abstract interface j
  {
    public abstract void a(int paramInt, String paramString1, String paramString2);
  }
  
  public static abstract interface k
  {
    public abstract void a();
    
    public abstract void b();
  }
  
  public static abstract interface l
  {
    public abstract void a();
  }
}
