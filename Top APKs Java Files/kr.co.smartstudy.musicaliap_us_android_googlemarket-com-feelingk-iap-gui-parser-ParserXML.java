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
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils.TruncateAt;
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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.feelingk.iap.b.ai;
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
  static View.OnClickListener P = new r();
  static final String a = "ParserXML";
  private static bf ac = null;
  private static Boolean bL;
  private static boolean bO;
  private static boolean bQ;
  private static String bR;
  private static StateListDrawable bl;
  private static StateListDrawable bm;
  private static LinearLayout bu = null;
  private static EditText bv = null;
  private static Button bw = null;
  private static Button bx = null;
  private static Boolean by;
  private static Boolean bz;
  public static String q = null;
  View.OnClickListener A = new b(this);
  aw B = new aw(this);
  CompoundButton.OnCheckedChangeListener C = new c(this);
  View.OnClickListener D = new d(this);
  View.OnClickListener E = new e(this);
  View.OnClickListener F = new f(this);
  View.OnClickListener G = new g(this);
  View.OnClickListener H = new h(this);
  View.OnClickListener I = new i(this);
  View.OnClickListener J = new k(this);
  View.OnClickListener K = new l(this);
  View.OnClickListener L = new n(this);
  View.OnClickListener M = new o(this);
  View.OnClickListener N = new p(this);
  View.OnClickListener O = new q(this);
  View.OnClickListener Q = new t(this);
  private Stack R = null;
  private Hashtable S = null;
  private int T;
  private Context U = null;
  private bj V = null;
  private ax W = null;
  private bk X = null;
  private bd Y = null;
  private ay Z = null;
  private boolean aA = false;
  private boolean aB = false;
  private boolean aC = false;
  private boolean aD = false;
  private boolean aE = false;
  private boolean[] aF = new boolean[3];
  private boolean[] aG = new boolean[1];
  private boolean[] aH = new boolean[1];
  private TextView aI = null;
  private TextView aJ = null;
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
  private EditText aV = null;
  private EditText aW = null;
  private boolean aX = true;
  private String aY = "";
  private StateListDrawable aZ;
  private be aa = null;
  private bh ab = null;
  private bc ad = null;
  private bg ae = null;
  private az af = null;
  private ba ag = null;
  private bb ah = null;
  private int ai = 0;
  private String aj = "/res/";
  private String ak = "/xml";
  private String al = "/xml_kt_lg";
  private String am = "purchase";
  private String an = null;
  private String ao = null;
  private View.OnClickListener ap = null;
  private com.feelingk.iap.gui.a.a aq = null;
  private ai ar = null;
  private boolean as = false;
  private boolean at = false;
  private boolean au = false;
  private boolean av = false;
  private boolean aw = false;
  private boolean ax = false;
  private boolean ay = false;
  private boolean az = false;
  boolean b = true;
  private Button bA;
  private Button bB;
  private Button bC;
  private Button bD;
  private Button bE = null;
  private Button bF;
  private Button bG;
  private TextView bH;
  private TextView bI;
  private TextView bJ;
  private TextView bK = null;
  private String bM;
  private boolean bN = false;
  private boolean bP = false;
  private int bS = 0;
  private Button bT;
  private Button bU;
  private as bV = new as(this, (byte)0);
  private at bW = new at(this, (byte)0);
  private int bX;
  private int bY;
  private int bZ;
  private StateListDrawable ba;
  private StateListDrawable bb;
  private StateListDrawable bc;
  private StateListDrawable bd;
  private StateListDrawable be;
  private StateListDrawable bf;
  private StateListDrawable bg;
  private StateListDrawable bh;
  private StateListDrawable bi;
  private StateListDrawable bj;
  private StateListDrawable bk;
  private StateListDrawable bn;
  private StateListDrawable bo;
  private StateListDrawable bp;
  private StateListDrawable bq;
  private Button br;
  private Button bs;
  private Button bt = null;
  boolean c = false;
  private int ca;
  InputStream d;
  InputStream e;
  InputStream f;
  InputStream g = null;
  Drawable h = null;
  Drawable i;
  Drawable j = null;
  Drawable k = null;
  Drawable l = null;
  Drawable m = null;
  Drawable n = null;
  Drawable o = null;
  boolean p = false;
  View.OnClickListener r = new a(this);
  View.OnClickListener s = new m(this);
  View.OnClickListener t = new y(this);
  View.OnClickListener u = new aj(this);
  View.OnClickListener v = new an(this);
  View.OnClickListener w = new ao(this);
  View.OnClickListener x = new ap(this);
  View.OnClickListener y = new aq(this);
  View.OnClickListener z = new ar(this);
  
  static
  {
    by = Boolean.valueOf(false);
    bz = Boolean.valueOf(false);
    bL = Boolean.valueOf(false);
    bO = false;
    bQ = false;
    bR = null;
  }
  
  public ParserXML(Context paramContext)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
  }
  
  public ParserXML(Context paramContext, ax paramAx, boolean paramBoolean)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
    this.W = paramAx;
    this.as = true;
  }
  
  public ParserXML(Context paramContext, ay paramAy, String paramString, boolean paramBoolean)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
    this.Z = paramAy;
    if ("AutoPurchaseForm".equals(paramString)) {
      this.av = true;
    }
  }
  
  public ParserXML(Context paramContext, az paramAz, boolean paramBoolean)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
    this.af = paramAz;
    this.aB = true;
  }
  
  public ParserXML(Context paramContext, ba paramBa, boolean paramBoolean)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
    this.ag = paramBa;
    this.aC = true;
  }
  
  public ParserXML(Context paramContext, bb paramBb)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
    this.ah = paramBb;
    this.aD = true;
  }
  
  public ParserXML(Context paramContext, bc paramBc, String paramString, boolean paramBoolean)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
    this.ad = paramBc;
    if ("IMEIAuthForm".equals(paramString)) {
      this.az = true;
    }
  }
  
  public ParserXML(Context paramContext, bd paramBd, String paramString, boolean paramBoolean)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
    this.Y = paramBd;
    if ("PermissionPopup".equals(paramString)) {
      this.au = true;
    }
  }
  
  public ParserXML(Context paramContext, be paramBe, String paramString, boolean paramBoolean)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
    this.aa = paramBe;
    if ("Join".equals(paramString)) {
      this.aw = true;
    }
  }
  
  public ParserXML(Context paramContext, bf paramBf, boolean paramBoolean)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
    ac = paramBf;
    this.ay = true;
  }
  
  public ParserXML(Context paramContext, bg paramBg, boolean paramBoolean)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
    this.ae = paramBg;
    this.aA = true;
  }
  
  public ParserXML(Context paramContext, bh paramBh, boolean paramBoolean)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
    this.ab = paramBh;
    this.ax = true;
  }
  
  public ParserXML(Context paramContext, bj paramBj)
  {
    this(paramContext);
    this.V = paramBj;
  }
  
  public ParserXML(Context paramContext, bj paramBj, byte paramByte)
  {
    this(paramContext);
    this.V = paramBj;
  }
  
  private ParserXML(Context paramContext, bj paramBj, boolean paramBoolean)
  {
    this(paramContext);
    this.V = paramBj;
    this.as = paramBoolean;
  }
  
  public ParserXML(Context paramContext, bk paramBk, String paramString, boolean paramBoolean)
  {
    this.U = paramContext;
    this.R = new Stack();
    this.S = new Hashtable();
    this.X = paramBk;
    if ("YesNo".equals(paramString)) {
      this.at = true;
    }
  }
  
  private int a(float paramFloat)
  {
    if (paramFloat == 0.0F) {
      return 0;
    }
    return (int)TypedValue.applyDimension(1, paramFloat, this.U.getResources().getDisplayMetrics());
  }
  
  private View a(XmlPullParser paramXmlPullParser)
  {
    this.R.clear();
    this.S.clear();
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
            this.R.push((ViewGroup)localView);
            localObject2 = localObject1;
            break;
            ((ViewGroup)this.R.peek()).addView(localView);
            continue;
            ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
            localObject2 = localObject1;
            break;
            localStack.pop();
            if (paramXmlPullParser.getName().endsWith("Layout")) {
              this.R.pop();
            }
            localObject2 = localObject1;
            if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
              break;
            }
            this.R.pop();
            localObject2 = localObject1;
            break;
          }
        }
        localObject2 = localObject1;
      }
    }
  }
  
  private View a(XmlPullParser paramXmlPullParser, String paramString)
  {
    this.R.clear();
    this.S.clear();
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
          this.R.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ((ViewGroup)this.R.peek()).addView((View)localObject2);
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.R.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.R.pop();
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
    if ((Build.MODEL.endsWith("LT15i")) && (this.c))
    {
      i2 = i(str3);
      i1 = i(str4);
      this.c = false;
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
          paramViewGroup.topMargin = k((String)localObject);
        }
        if (str2 != null) {
          paramViewGroup.leftMargin = k(str2);
        }
        if (paramAttributeSet != null) {
          paramViewGroup.bottomMargin = i(paramAttributeSet);
        }
        localObject = paramViewGroup;
        if (str1 != null)
        {
          paramViewGroup.rightMargin = k(str1);
          localObject = paramViewGroup;
        }
        return localObject;
        i2 = i(str1);
        i1 = i(str2);
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
    if ((paramString != null) && (bv != null))
    {
      q = paramString;
      bv.setTextColor(Color.parseColor("#FF6F00"));
      bv.setTextSize(1, 30.0F);
      bv.setText(paramString);
      bv.setPadding(0, 0, 0, 0);
      paramString = new InputFilter.LengthFilter(6);
      bv.setFilters(new InputFilter[] { paramString });
      bx.setBackgroundDrawable(bm);
      bx.setOnClickListener(P);
    }
  }
  
  private View b(XmlPullParser paramXmlPullParser)
  {
    Object localObject2 = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject2).equals("LinearLayout")) {
      paramXmlPullParser = new LinearLayout(this.U);
    }
    while (paramXmlPullParser == null)
    {
      localObject1 = null;
      return localObject1;
      if (((String)localObject2).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.U);
      }
      else if (((String)localObject2).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.U);
      }
      else if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.U);
      }
      else if (((String)localObject2).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.U);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
      }
      else if (((String)localObject2).equals("CheckBox"))
      {
        paramXmlPullParser = new CheckBox(this.U);
      }
      else
      {
        Assert.fail("# UnSupported tag:" + (String)localObject2);
        paramXmlPullParser = (XmlPullParser)localObject1;
      }
    }
    localObject2 = Boolean.valueOf(false);
    Object localObject4;
    if (this.aq != null)
    {
      com.feelingk.iap.gui.a.b.a().a(this.aq.c);
      com.feelingk.iap.gui.a.b.a().b(com.feelingk.iap.gui.a.b.a().c() - com.feelingk.iap.gui.a.b.a().e());
      localObject1 = com.feelingk.iap.an.r();
      if ((localObject1 != null) && (((com.feelingk.iap.b.aq)localObject1).e() != null) && (!((com.feelingk.iap.b.aq)localObject1).e().equals("null")))
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
          localObject9 = getClass().getResourceAsStream(this.aj + "line_dot_01.png");
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
            i1 = k((String)localObject7);
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
            i1 = i((String)localObject7);
          }
          if (localObject9 != null) {
            i2 = i((String)localObject9);
          }
          if (localObject10 != null) {
            i3 = i((String)localObject10);
          }
          if (localObject11 != null) {
            i4 = i((String)localObject11);
          }
          ((LinearLayout)localObject6).setPadding(i3, i1, i4, i2);
          localObject7 = a(localAttributeSet, "a:id");
          if ((localObject7 != null) && (this.aq != null))
          {
            if ((!this.aq.g.equals("Y")) || (!((String)localObject7).equals("unregistered"))) {
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
              localObject9 = getClass().getResourceAsStream(this.aj + (String)localObject7 + ".png");
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
              ((TextView)localObject6).setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.P));
            }
            if (str3 != null)
            {
              localObject12 = this.U.getPackageManager().getInstalledApplications(0);
              i2 = ((List)localObject12).size();
              i1 = 0;
              label970:
              if (i1 < i2) {}
            }
            else
            {
              if (localObject10 != null) {
                ((TextView)localObject6).setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.Y));
              }
              if (localObject11 != null)
              {
                ((String)localObject11).replace("\\n", "\n");
                ((TextView)localObject6).setText("<" + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.O) + ">\n월별 자동결제 상품이란,");
              }
              if (str1 != null) {
                ((TextView)localObject6).setTextSize(1, j(str1));
              }
              if (str2 != null) {
                ((TextView)localObject6).setTextColor(Color.parseColor(str2));
              }
              if (localObject9 != null)
              {
                if (!((String)localObject9).equals("ItemName")) {
                  break label2728;
                }
                ((TextView)localObject6).setText(this.aq.a);
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
              localObject2 = getClass().getResourceAsStream(this.aj + (String)localObject6 + ".png");
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
              this.br = ((Button)paramXmlPullParser);
              this.aZ = new StateListDrawable();
              this.bb = new StateListDrawable();
              this.bd = new StateListDrawable();
              this.bf = new StateListDrawable();
              this.bh = new StateListDrawable();
              this.bc = new StateListDrawable();
              this.be = new StateListDrawable();
              this.bg = new StateListDrawable();
              this.bi = new StateListDrawable();
              this.d = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
              this.j = Drawable.createFromStream(this.d, (String)localObject2);
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
              this.d.close();
              this.d = getClass().getResourceAsStream(this.aj + (String)localObject1 + ".png");
              this.k = Drawable.createFromStream(this.d, (String)localObject1);
              try
              {
                this.d.close();
                if (((String)localObject2).equals("bt_01_nor.9"))
                {
                  this.h = this.j;
                  this.i = this.k;
                }
                localObject4 = this.aZ;
                localObject7 = this.k;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.aZ;
                localObject7 = this.j;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.bb;
                localObject7 = this.h;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.bb;
                localObject7 = this.h;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.bd;
                localObject7 = this.h;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.bd;
                localObject7 = this.h;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.bf;
                localObject7 = this.h;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.bf;
                localObject7 = this.h;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.bh;
                localObject7 = this.h;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.bh;
                localObject7 = this.h;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.bc;
                localObject7 = this.i;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.bc;
                localObject7 = this.h;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.be;
                localObject7 = this.i;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.be;
                localObject7 = this.h;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.bg;
                localObject7 = this.i;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.bg;
                localObject7 = this.h;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                localObject4 = this.bi;
                localObject7 = this.i;
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                localObject4 = this.bi;
                localObject7 = this.h;
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                this.bn = new StateListDrawable();
                this.e = getClass().getResourceAsStream(this.aj + "bt_buy_dim" + ".png");
                this.l = Drawable.createFromStream(this.e, "bt_buy_dim");
                try
                {
                  this.e.close();
                  this.e = null;
                  this.e = getClass().getResourceAsStream(this.aj + "bt_buy_dim.png");
                  this.m = Drawable.createFromStream(this.e, "bt_buy_dim");
                  try
                  {
                    this.e.close();
                    this.e = null;
                    localObject4 = this.bn;
                    localObject7 = this.m;
                    ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject7);
                    localObject4 = this.bn;
                    localObject7 = this.l;
                    ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject7);
                    if ((((String)localObject2).equals("btn_buy_nor_h")) && (com.feelingk.iap.an.l()))
                    {
                      this.bs = this.br;
                      this.ba = this.aZ;
                      this.p = true;
                      label2200:
                      if ((!((String)localObject2).equals("btn_buy_nor_h")) || (!this.p)) {
                        break label3931;
                      }
                      this.br.setBackgroundDrawable(this.bn);
                      label2229:
                      if ((((String)localObject2).equals("bt_01_nor.9")) && (localObject6 != null))
                      {
                        this.br.setTextColor(Color.parseColor("#CFCFCF"));
                        if (!((String)localObject6).equals("OCBBtn")) {
                          break label4147;
                        }
                        this.bA = this.br;
                        this.bA.setPadding(0, 0, 0, 0);
                        if (!this.aq.g.equals("Y")) {
                          break label4077;
                        }
                        if (!com.feelingk.iap.an.m()) {
                          break label4035;
                        }
                        localObject2 = com.feelingk.iap.an.n();
                        if ((localObject2 != null) && (((String)localObject2).length() > 0) && (Integer.parseInt((String)localObject2) >= 10)) {
                          break label3961;
                        }
                        bL = Boolean.valueOf(true);
                        if (!com.feelingk.iap.gui.a.b.a().j()) {
                          break label3945;
                        }
                        this.bA.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.N));
                        label2367:
                        this.bA.setTextSize(1, 13.0F);
                        this.bA.setBackgroundDrawable(this.bb);
                        this.bA.setTextColor(Color.parseColor("#8B8B8B"));
                        this.bA.setClickable(false);
                        this.bA.setPadding(0, 0, 0, 0);
                        label2421:
                        if ((com.feelingk.iap.gui.a.b.a().d() == 0) && (com.feelingk.iap.gui.a.b.a().f() == 0))
                        {
                          this.bA.setBackgroundDrawable(this.bb);
                          this.bA.setTextColor(Color.parseColor("#8B8B8B"));
                          this.bA.setClickable(false);
                          this.bA.setPadding(0, 0, 0, 0);
                        }
                      }
                      label2482:
                      if ((!((String)localObject1).equals("btn_buy_sel_h")) || (com.feelingk.iap.an.l())) {
                        break label5240;
                      }
                      this.br.setOnClickListener(this.I);
                    }
                    for (;;)
                    {
                      label2510:
                      localObject1 = paramXmlPullParser;
                      if (this.R.size() <= 0) {
                        break;
                      }
                      paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.R.peek()));
                      return paramXmlPullParser;
                      label2545:
                      if (!((String)localObject7).equals("vertical")) {
                        break label353;
                      }
                      ((LinearLayout)localObject6).setOrientation(1);
                      break label353;
                      label2565:
                      localObject9 = getClass().getResourceAsStream(this.aj + (String)localObject7 + ".png");
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
                      if ((!this.aq.g.equals("N")) || (!localIOException13.equals("registered"))) {
                        break label720;
                      }
                      ((LinearLayout)localObject6).setVisibility(8);
                      break label720;
                      if (((ApplicationInfo)((List)localObject12).get(i1)).packageName.indexOf("com.skt.skaf.A000Z00040") == 0) {
                        this.aE = true;
                      }
                      i1 += 1;
                      break label970;
                      label2728:
                      if (((String)localObject9).equals("ItemUseDate"))
                      {
                        ((TextView)localObject6).setText(this.aq.b);
                        break label1114;
                      }
                      if (((String)localObject9).equals("ItemPrice"))
                      {
                        ((TextView)localObject6).setText(this.aq.c + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
                        break label1114;
                      }
                      if (((String)localObject9).equals("ItemOCB"))
                      {
                        this.bH = ((TextView)localObject6);
                        if (com.feelingk.iap.an.m())
                        {
                          this.bH.setText(Integer.parseInt(com.feelingk.iap.an.n()) - com.feelingk.iap.gui.a.b.a().f() + " P");
                          break label1114;
                        }
                        this.bH.setText("0 P");
                        break label1114;
                      }
                      if (((String)localObject9).equals("ItemDotori"))
                      {
                        this.bI = ((TextView)localObject6);
                        if (this.aq.j.equals("Y"))
                        {
                          ((TextView)localObject6).setText(this.aq.i / 100 - com.feelingk.iap.gui.a.b.a().g() / 100 + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.A));
                          break label1114;
                        }
                        ((TextView)localObject6).setText("0" + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.A));
                        break label1114;
                      }
                      if (((String)localObject9).equals("ItemCultureCash"))
                      {
                        this.bJ = ((TextView)localObject6);
                        if ((com.feelingk.iap.an.o()) && (com.feelingk.iap.an.p() != null))
                        {
                          ((TextView)localObject6).setText(Integer.parseInt(com.feelingk.iap.an.p()) - com.feelingk.iap.gui.a.b.a().h() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
                          break label1114;
                        }
                        ((TextView)localObject6).setText("0" + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
                        break label1114;
                      }
                      if (((String)localObject9).equals("ItemCash"))
                      {
                        this.bK = ((TextView)localObject6);
                        ((TextView)localObject6).setText(this.aq.d - com.feelingk.iap.gui.a.b.a().i() + " P");
                        break label1114;
                      }
                      if (((String)localObject9).equals("Discount"))
                      {
                        this.aJ = ((TextView)localObject6);
                        this.aJ.setText(com.feelingk.iap.gui.a.b.a().e() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
                        break label1114;
                      }
                      if (((String)localObject9).equals("Payments"))
                      {
                        this.aI = ((TextView)localObject6);
                        this.aI.setText(com.feelingk.iap.gui.a.b.a().c() - com.feelingk.iap.gui.a.b.a().e() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
                        break label1114;
                      }
                      if (((String)localObject9).equals("xperiaCash"))
                      {
                        if (((String)localObject4).endsWith("LT15i"))
                        {
                          ((TextView)localObject6).setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.M) + "  ");
                          break label1114;
                        }
                        ((TextView)localObject6).setText("");
                        break label1114;
                      }
                      if (((String)localObject9).equals("commonMessage"))
                      {
                        ((TextView)localObject6).setText(this.ao);
                        break label1114;
                      }
                      if (((String)localObject9).equals("Version"))
                      {
                        if ("iap.tstore.co.kr".equals("iapdev.tstore.co.kr"))
                        {
                          ((TextView)localObject6).setText("V 12.09.17(" + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.ab) + ")");
                          break label1114;
                        }
                        ((TextView)localObject6).setText("V 12.09.17");
                        break label1114;
                      }
                      if ((((String)localObject9).equals("HeaderMessage")) || (((String)localObject9).equals("FooterMessage")))
                      {
                        if (!this.aq.k) {
                          break label1114;
                        }
                        return null;
                      }
                      if (((String)localObject9).equals("OCBCardNumber"))
                      {
                        ((TextView)localObject6).setText(this.aq.h);
                        break label1114;
                      }
                      if (((String)localObject9).equals("infoText"))
                      {
                        if (com.feelingk.iap.c.a.a(this.U) == 1)
                        {
                          ((TextView)localObject6).setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.Q));
                          break label1114;
                        }
                        ((TextView)localObject6).setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.R));
                        break label1114;
                      }
                      if (((String)localObject9).equals("finalVersion_Item"))
                      {
                        if (this.aq.k)
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
                        localObject1 = ((com.feelingk.iap.b.aq)localObject2).e();
                        if (((String)localObject1).equals("null")) {
                          break label1114;
                        }
                        ((TextView)localObject6).setText(PhoneNumberUtils.formatNumber((String)localObject1));
                        break label1114;
                      }
                      if ((localObject2 != null) && (((String)localObject9).equals("seller_name_view")))
                      {
                        if ((((com.feelingk.iap.b.aq)localObject2).a().equals("null")) && (((com.feelingk.iap.b.aq)localObject2).d().equals("null"))) {
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
                          if (!((com.feelingk.iap.b.aq)localObject2).a().equals("null")) {
                            localObject1 = ((com.feelingk.iap.b.aq)localObject2).a();
                          } else {
                            localObject1 = ((com.feelingk.iap.b.aq)localObject2).d();
                          }
                        }
                      }
                      if ((localObject2 == null) || (!((String)localObject9).equals("seller_mail_view"))) {
                        break label1114;
                      }
                      if (((com.feelingk.iap.b.aq)localObject2).b().equals("null"))
                      {
                        ((TextView)localObject6).setText("");
                        break label1114;
                      }
                      ((TextView)localObject6).setSingleLine(true);
                      ((TextView)localObject6).setFocusable(true);
                      ((TextView)localObject6).setEllipsize(TextUtils.TruncateAt.MARQUEE);
                      ((TextView)localObject6).setFocusableInTouchMode(true);
                      ((TextView)localObject6).setSelected(true);
                      ((TextView)localObject6).setText(((com.feelingk.iap.b.aq)localObject2).b());
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
                      this.p = false;
                      break label2200;
                      label3931:
                      this.br.setBackgroundDrawable(this.aZ);
                      break label2229;
                      label3945:
                      this.bA.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.L));
                      break label2367;
                      label3961:
                      bL = Boolean.valueOf(true);
                      if (com.feelingk.iap.gui.a.b.a().j()) {
                        this.bA.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.N));
                      }
                      for (;;)
                      {
                        this.bA.setTextSize(1, 13.0F);
                        this.bA.setOnClickListener(new u(this));
                        break;
                        this.bA.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.L));
                      }
                      label4035:
                      this.bA.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.F));
                      this.bA.setTextSize(1, 13.0F);
                      this.bA.setOnClickListener(new v(this));
                      break label2421;
                      label4077:
                      this.bA.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.H));
                      this.bA.setTextSize(1, 13.0F);
                      this.bA.setTextSize(1, 13.0F);
                      if (this.bH != null) {
                        this.bH.setText("0 P");
                      }
                      this.bA.setOnClickListener(new w(this));
                      break label2421;
                      label4147:
                      if (((String)localObject6).equals("DotoriBtn"))
                      {
                        this.bB = this.br;
                        this.bB.setPadding(0, 0, 0, 0);
                        if (this.aq.j.equals("Y")) {
                          if (com.feelingk.iap.gui.a.b.a().k())
                          {
                            this.bB.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.N));
                            label4215:
                            this.bB.setTextSize(1, 13.0F);
                            if (!this.aq.n) {
                              break label4387;
                            }
                            if (this.aq.i == 0) {
                              break label4341;
                            }
                            this.bB.setOnClickListener(new x(this));
                          }
                        }
                        for (;;)
                        {
                          if ((com.feelingk.iap.gui.a.b.a().d() != 0) || (com.feelingk.iap.gui.a.b.a().g() != 0)) {
                            break label4525;
                          }
                          this.bB.setClickable(false);
                          this.bB.setBackgroundDrawable(this.bb);
                          this.bB.setPadding(0, 0, 0, 0);
                          this.bB.setTextColor(Color.parseColor("#8B8B8B"));
                          break;
                          this.bB.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.L));
                          break label4215;
                          label4341:
                          this.bB.setBackgroundDrawable(this.bb);
                          this.bB.setTextColor(Color.parseColor("#8B8B8B"));
                          this.bB.setClickable(false);
                          this.bB.setPadding(0, 0, 0, 0);
                          continue;
                          label4387:
                          this.bB.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.L));
                          this.bB.setTextSize(1, 13.0F);
                          if (this.aq.i != 0)
                          {
                            this.bB.setOnClickListener(new z(this));
                          }
                          else
                          {
                            this.bB.setBackgroundDrawable(this.bd);
                            this.bB.setTextColor(Color.parseColor("#8B8B8B"));
                            this.bB.setClickable(false);
                            this.bB.setPadding(0, 0, 0, 0);
                            continue;
                            this.bB.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.B));
                            this.bB.setTextSize(1, 13.0F);
                            this.bB.setOnClickListener(new aa(this));
                          }
                        }
                        label4525:
                        break label2482;
                      }
                      if (((String)localObject6).equals("CultureBtn"))
                      {
                        this.bC = this.br;
                        this.bC.setPadding(0, 0, 0, 0);
                        if (com.feelingk.iap.an.o()) {
                          if (com.feelingk.iap.gui.a.b.a().l())
                          {
                            this.bC.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.N));
                            label4585:
                            this.bC.setTextSize(1, 13.0F);
                            localObject2 = com.feelingk.iap.an.p();
                            if ((localObject2 != null) && (((String)localObject2).length() > 0) && (Integer.parseInt((String)localObject2) >= 10)) {
                              break label4747;
                            }
                            this.bC.setBackgroundDrawable(this.bb);
                            this.bC.setTextColor(Color.parseColor("#8B8B8B"));
                            this.bC.setClickable(false);
                            this.bC.setPadding(0, 0, 0, 0);
                          }
                        }
                        for (;;)
                        {
                          if ((com.feelingk.iap.gui.a.b.a().d() != 0) || (com.feelingk.iap.gui.a.b.a().h() != 0)) {
                            break label4816;
                          }
                          this.bC.setClickable(false);
                          this.bC.setBackgroundDrawable(this.bb);
                          this.bC.setPadding(0, 0, 0, 0);
                          this.bC.setTextColor(Color.parseColor("#8B8B8B"));
                          break;
                          this.bC.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.L));
                          break label4585;
                          label4747:
                          this.bC.setOnClickListener(new ab(this));
                          continue;
                          this.bC.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.D));
                          this.bC.setTextSize(1, 13.0F);
                          this.bC.setPadding(0, 0, 0, 0);
                          this.bC.setOnClickListener(new ac(this));
                        }
                        label4816:
                        break label2482;
                      }
                      if (((String)localObject6).equals("TcashBtn"))
                      {
                        this.bD = this.br;
                        this.bD.setPadding(0, 0, 0, 0);
                        if (com.feelingk.iap.gui.a.b.a().m())
                        {
                          this.bD.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.N));
                          label4870:
                          this.bD.setTextSize(1, 13.0F);
                          if (this.aq.d < 10) {
                            break label4988;
                          }
                          this.bD.setOnClickListener(new ad(this));
                        }
                        for (;;)
                        {
                          if ((com.feelingk.iap.gui.a.b.a().d() != 0) || (com.feelingk.iap.gui.a.b.a().i() != 0)) {
                            break label5032;
                          }
                          this.bD.setClickable(false);
                          this.bD.setBackgroundDrawable(this.bb);
                          this.bD.setPadding(0, 0, 0, 0);
                          this.bD.setTextColor(Color.parseColor("#8B8B8B"));
                          break;
                          this.bD.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.L));
                          break label4870;
                          label4988:
                          this.bD.setClickable(false);
                          this.bD.setBackgroundDrawable(this.bb);
                          this.bD.setPadding(0, 0, 0, 0);
                          this.bD.setTextColor(Color.parseColor("#8B8B8B"));
                        }
                        label5032:
                        break label2482;
                      }
                      if (((String)localObject6).equals("OCBRegister"))
                      {
                        localObject2 = this.br;
                        ((Button)localObject2).setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.G));
                        ((Button)localObject2).setTextSize(1, 13.0F);
                        ((Button)localObject2).setPadding(0, 0, 0, 0);
                        ((Button)localObject2).setOnClickListener(new ae(this));
                        break label2482;
                      }
                      if (((String)localObject6).equals("OCB_Card_Change"))
                      {
                        this.bU = this.br;
                        this.bU.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.I));
                        this.bU.setTextSize(1, 13.0F);
                        this.bU.setPadding(0, 0, 0, 0);
                        this.bU.setOnClickListener(new af(this));
                        break label2482;
                      }
                      if (!((String)localObject6).equals("OCB_Card_Del")) {
                        break label2482;
                      }
                      this.bT = this.br;
                      this.bT.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.J));
                      this.bT.setTextSize(1, 13.0F);
                      this.bT.setPadding(0, 0, 0, 0);
                      this.bT.setOnClickListener(new ag(this));
                      break label2482;
                      label5240:
                      if (((String)localObject1).equals("pop_btn_sel_ok"))
                      {
                        this.br.setOnClickListener(this.L);
                      }
                      else if (((String)localObject1).equals("btn_info01_sel"))
                      {
                        this.br.setOnClickListener(this.M);
                      }
                      else
                      {
                        if (((String)localObject1).equals("btn_locking_sel"))
                        {
                          localObject1 = this.U.getPackageManager().getInstalledApplications(0);
                          i2 = ((List)localObject1).size();
                          i1 = 0;
                          for (;;)
                          {
                            if (i1 >= i2)
                            {
                              this.br.setOnClickListener(this.J);
                              break;
                            }
                            if (((ApplicationInfo)((List)localObject1).get(i1)).packageName.indexOf("com.skt.skaf.A000Z00040") == 0) {
                              this.aE = true;
                            }
                            i1 += 1;
                          }
                        }
                        if (((String)localObject1).equals("btn_cancel_sel_h")) {
                          this.br.setOnClickListener(this.K);
                        }
                      }
                    }
                    label5403:
                    if (this.aq != null) {
                      if (!this.aq.l)
                      {
                        localObject2 = (CheckBox)paramXmlPullParser;
                        i1 = 0;
                        if (((String)localObject4).endsWith("LT15i"))
                        {
                          i1 = 1;
                          this.c = true;
                        }
                        if (i1 == 0)
                        {
                          localObject4 = getClass().getResourceAsStream(this.aj + (String)localObject1 + ".png");
                          localObject1 = Drawable.createFromStream((InputStream)localObject4, (String)localObject1);
                        }
                      }
                    }
                    try
                    {
                      ((InputStream)localObject4).close();
                      localObject6 = getClass().getResourceAsStream(this.aj + "btn_check_ok_nor" + ".png");
                      localObject4 = Drawable.createFromStream((InputStream)localObject6, "btn_check_ok_nor");
                    }
                    catch (IOException localIOException5)
                    {
                      try
                      {
                        ((InputStream)localObject6).close();
                        localObject8 = getClass().getResourceAsStream(this.aj + "btn_check_no_sel" + ".png");
                        localObject6 = Drawable.createFromStream((InputStream)localObject8, "btn_check_no_sel");
                      }
                      catch (IOException localIOException5)
                      {
                        try
                        {
                          ((InputStream)localObject8).close();
                          localObject9 = getClass().getResourceAsStream(this.aj + "btn_check_ok_sel" + ".png");
                          localObject8 = Drawable.createFromStream((InputStream)localObject9, "btn_check_ok_sel");
                        }
                        catch (IOException localIOException5)
                        {
                          try
                          {
                            ((InputStream)localObject9).close();
                            localObject10 = getClass().getResourceAsStream(this.aj + "btn_check_dim" + ".png");
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
                              if ((this.aq.d != 0) && (this.aq.d - this.aq.c >= 0)) {
                                ((CheckBox)localObject2).setEnabled(true);
                              }
                              for (;;)
                              {
                                ((CheckBox)localObject2).setOnCheckedChangeListener(new ah(this));
                                break;
                                ((CheckBox)localObject2).setEnabled(false);
                              }
                              if ((!this.aq.l) || (!(paramXmlPullParser instanceof CheckBox))) {
                                break label2510;
                              }
                              localObject2 = null;
                              localObject1 = a(localAttributeSet, "a:checkid");
                              localObject6 = (CheckBox)paramXmlPullParser;
                              ((CheckBox)localObject6).setTag(localObject1);
                              localObject1 = null;
                              if (this.aq.m)
                              {
                                localObject2 = "checkbox_y";
                                localObject1 = "checkbox_n";
                                this.aG[0] = true;
                                label6098:
                                ((CheckBox)localObject6).setChecked(false);
                                i1 = 0;
                                if (((String)localObject4).endsWith("LT15i"))
                                {
                                  i1 = 1;
                                  this.c = true;
                                }
                                if (i1 != 0) {
                                  break label6453;
                                }
                                localObject4 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
                                localObject2 = Drawable.createFromStream((InputStream)localObject4, (String)localObject2);
                              }
                            }
                            catch (IOException localIOException5)
                            {
                              try
                              {
                                ((InputStream)localObject4).close();
                                localObject4 = getClass().getResourceAsStream(this.aj + (String)localObject1 + ".png");
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
                                    ((CheckBox)localObject6).setOnCheckedChangeListener(this.C);
                                    break;
                                    if (this.aq.m) {
                                      break label6098;
                                    }
                                    localObject2 = "checkbox_n";
                                    localObject1 = "checkbox_y";
                                    this.aG[0] = false;
                                    break label6098;
                                    label6453:
                                    if ((i1 != 0) && (this.aq.m)) {
                                      ((CheckBox)localObject6).setChecked(true);
                                    }
                                  }
                                  localObject2 = (CheckBox)paramXmlPullParser;
                                  ((CheckBox)localObject2).setTag(a(localAttributeSet, "a:checkid"));
                                  i1 = 0;
                                  if (((String)localObject4).endsWith("LT15i"))
                                  {
                                    i1 = 1;
                                    this.c = true;
                                  }
                                  if (i1 == 0)
                                  {
                                    localObject4 = getClass().getResourceAsStream(this.aj + (String)localObject1 + ".png");
                                    localObject1 = Drawable.createFromStream((InputStream)localObject4, (String)localObject1);
                                  }
                                }
                                catch (IOException localIOException5)
                                {
                                  try
                                  {
                                    ((InputStream)localObject4).close();
                                    localObject6 = getClass().getResourceAsStream(this.aj + "checkbox_y" + ".png");
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
                                        ((CheckBox)localObject2).setOnCheckedChangeListener(this.C);
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
    this.R.clear();
    this.S.clear();
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
      localObject2 = d(paramXmlPullParser, paramString);
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
          this.R.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ((ViewGroup)this.R.peek()).addView((View)localObject2);
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("Layout")) {
            break;
          }
          this.R.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  private View c(XmlPullParser paramXmlPullParser)
  {
    this.R.clear();
    this.S.clear();
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
          this.R.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ((ViewGroup)this.R.peek()).addView((View)localObject2);
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.R.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.R.pop();
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
      paramXmlPullParser = new LinearLayout(this.U);
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
        paramXmlPullParser = new TextView(this.U);
        break;
      }
      if (((String)localObject1).equals("Button"))
      {
        paramXmlPullParser = new Button(this.U);
        break;
      }
      if (((String)localObject1).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.U);
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
          localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
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
            int i1 = k((String)localObject2);
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
              ((TextView)localObject1).setTextSize(1, j((String)localObject4));
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
            localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
            localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
          }
        }
      }
      catch (IOException localIOException2)
      {
        try
        {
          ((InputStream)localObject3).close();
          localObject4 = getClass().getResourceAsStream(this.aj + paramString + ".png");
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
                ((Button)localObject1).setOnClickListener(this.u);
              }
              for (;;)
              {
                paramString = paramXmlPullParser;
                if (this.R.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.R.peek()));
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
                ((Button)localObject1).setOnClickListener(this.v);
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
  
  private View d(XmlPullParser paramXmlPullParser)
  {
    this.R.clear();
    this.S.clear();
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
          this.R.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ((ViewGroup)this.R.peek()).addView((View)localObject2);
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.R.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.R.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  private View d(XmlPullParser paramXmlPullParser, String paramString)
  {
    Object localObject1 = paramXmlPullParser.getName();
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject1).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.U);
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
        paramXmlPullParser = new TextView(this.U);
        break;
      }
      if (((String)localObject1).equals("Button"))
      {
        paramXmlPullParser = new Button(this.U);
        break;
      }
      if (((String)localObject1).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.U);
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
          localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
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
            int i1 = k((String)localObject2);
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
              localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
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
              ((TextView)localObject1).setTextSize(1, j((String)localObject4));
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
            localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
            localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
          }
        }
        catch (IOException localIOException3)
        {
          try
          {
            ((InputStream)localObject3).close();
            localObject4 = getClass().getResourceAsStream(this.aj + paramString + ".png");
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
                  ((Button)localObject1).setOnClickListener(this.w);
                }
                paramString = paramXmlPullParser;
                if (this.R.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.R.peek()));
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
  
  private View e(String paramString)
  {
    for (;;)
    {
      try
      {
        localXmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
        localInputStream = getClass().getResourceAsStream(paramString);
        localXmlPullParser.setInput(localInputStream, "utf-8");
        if ((!this.as) && (!this.aA) && (!this.aB) && (!this.aC)) {
          continue;
        }
        paramString = c(localXmlPullParser);
      }
      catch (XmlPullParserException paramString)
      {
        InputStream localInputStream;
        String str2;
        paramString.printStackTrace();
        return null;
        localStack.push(new StringBuffer());
        localView = c(localXmlPullParser, str2);
        if (localView == null) {
          continue;
        }
        if (paramString != null) {
          continue;
        }
        paramString = localView;
        str1 = paramString;
        if (!(localView instanceof ViewGroup)) {
          continue;
        }
        this.R.push((ViewGroup)localView);
        str1 = paramString;
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
        ((ViewGroup)this.R.peek()).addView(localView);
        continue;
        ((StringBuffer)localStack.peek()).append(localXmlPullParser.getText());
        String str1 = paramString;
        continue;
        localStack.pop();
        if (!localXmlPullParser.getName().endsWith("Layout")) {
          continue;
        }
        this.R.pop();
        str1 = paramString;
        if (!localXmlPullParser.getName().endsWith("ScrollView")) {
          continue;
        }
        this.R.pop();
        str1 = paramString;
        continue;
        if (!this.au) {
          continue;
        }
        paramString = b(localXmlPullParser, this.ao);
        continue;
        if (!this.av) {
          continue;
        }
        paramString = d(localXmlPullParser);
        continue;
        if (!this.az) {
          continue;
        }
        paramString = e(localXmlPullParser);
        continue;
        if (!this.aw) {
          continue;
        }
        paramString = f(localXmlPullParser);
        continue;
        if (!this.ax) {
          continue;
        }
        paramString = g(localXmlPullParser);
        continue;
        if (!this.ay) {
          continue;
        }
        paramString = h(localXmlPullParser);
        continue;
        paramString = a(localXmlPullParser);
        continue;
        if (i1 == 1) {
          continue;
        }
        str1 = paramString;
        switch (i1)
        {
        }
        str1 = paramString;
        continue;
      }
      localInputStream.close();
      return paramString;
      if (!this.aD) {
        continue;
      }
      paramString = c(localXmlPullParser);
    }
    if (this.at)
    {
      str2 = this.ao;
      this.R.clear();
      this.S.clear();
      localStack = new Stack();
      i1 = localXmlPullParser.getEventType();
      paramString = null;
      break label458;
      for (;;)
      {
        i1 = localXmlPullParser.next();
        paramString = str1;
        break;
        localStack.clear();
        str1 = paramString;
      }
    }
  }
  
  private View e(XmlPullParser paramXmlPullParser)
  {
    this.R.clear();
    this.S.clear();
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
          this.R.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ((ViewGroup)this.R.peek()).addView((View)localObject2);
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.R.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.R.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  private View f(XmlPullParser paramXmlPullParser)
  {
    this.R.clear();
    this.S.clear();
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
          this.R.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ((ViewGroup)this.R.peek()).addView((View)localObject2);
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.R.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.R.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  private static boolean f(String paramString)
  {
    return paramString.endsWith("Layout");
  }
  
  private View g(XmlPullParser paramXmlPullParser)
  {
    this.R.clear();
    this.S.clear();
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
          this.R.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ((ViewGroup)this.R.peek()).addView((View)localObject2);
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.R.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.R.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  private void g()
  {
    if (this.R != null)
    {
      this.R.clear();
      this.R = null;
    }
    if (this.S != null)
    {
      this.S.clear();
      this.S = null;
    }
    this.U = null;
    this.V = null;
  }
  
  private static boolean g(String paramString)
  {
    return paramString.endsWith("ScrollView");
  }
  
  private int h(String paramString)
  {
    int i1 = paramString.indexOf("/");
    if (i1 != -1)
    {
      String str = paramString.substring(i1 + 1);
      Integer localInteger2 = (Integer)this.S.get(str);
      Integer localInteger1 = localInteger2;
      if (localInteger2 == null)
      {
        localInteger1 = localInteger2;
        if (paramString.startsWith("@+"))
        {
          i1 = this.T;
          this.T = (i1 + 1);
          localInteger1 = new Integer(i1);
          this.S.put(str, localInteger1);
        }
      }
      if (localInteger1 != null) {
        return localInteger1.intValue();
      }
    }
    return -1;
  }
  
  private View h(XmlPullParser paramXmlPullParser)
  {
    this.R.clear();
    this.S.clear();
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
          this.R.push((ViewGroup)localObject2);
          localObject2 = localObject1;
          break;
          ((ViewGroup)this.R.peek()).addView((View)localObject2);
          continue;
          ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
          localObject2 = localObject1;
          break;
          localStack.pop();
          if (paramXmlPullParser.getName().endsWith("Layout")) {
            this.R.pop();
          }
          localObject2 = localObject1;
          if (!paramXmlPullParser.getName().endsWith("ScrollView")) {
            break;
          }
          this.R.pop();
          localObject2 = localObject1;
          break;
        }
      }
      localObject2 = localObject1;
    }
  }
  
  private String h()
  {
    if (com.feelingk.iap.c.a.a(this.U) == 1) {
      return String.format("%s", new Object[] { this.ak });
    }
    return String.format("%s", new Object[] { this.al });
  }
  
  private int i(String paramString)
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
  
  /* Error */
  private View i(XmlPullParser paramXmlPullParser)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 680 1 0
    //   6: astore 6
    //   8: aconst_null
    //   9: astore 4
    //   11: aload_1
    //   12: invokestatic 870	android/util/Xml:asAttributeSet	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/util/AttributeSet;
    //   15: astore 5
    //   17: aload 6
    //   19: ldc_w 872
    //   22: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   25: ifeq +25 -> 50
    //   28: new 715	android/widget/LinearLayout
    //   31: dup
    //   32: aload_0
    //   33: getfield 244	com/feelingk/iap/gui/parser/ParserXML:U	Landroid/content/Context;
    //   36: invokespecial 873	android/widget/LinearLayout:<init>	(Landroid/content/Context;)V
    //   39: astore_1
    //   40: aload_1
    //   41: ifnonnull +233 -> 274
    //   44: aconst_null
    //   45: astore 4
    //   47: aload 4
    //   49: areturn
    //   50: aload 6
    //   52: ldc_w 875
    //   55: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   58: ifeq +18 -> 76
    //   61: new 877	android/widget/TextView
    //   64: dup
    //   65: aload_0
    //   66: getfield 244	com/feelingk/iap/gui/parser/ParserXML:U	Landroid/content/Context;
    //   69: invokespecial 878	android/widget/TextView:<init>	(Landroid/content/Context;)V
    //   72: astore_1
    //   73: goto -33 -> 40
    //   76: aload 6
    //   78: ldc_w 885
    //   81: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   84: ifeq +18 -> 102
    //   87: new 837	android/widget/Button
    //   90: dup
    //   91: aload_0
    //   92: getfield 244	com/feelingk/iap/gui/parser/ParserXML:U	Landroid/content/Context;
    //   95: invokespecial 886	android/widget/Button:<init>	(Landroid/content/Context;)V
    //   98: astore_1
    //   99: goto -59 -> 40
    //   102: aload 6
    //   104: ldc_w 1594
    //   107: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   110: ifeq +28 -> 138
    //   113: new 806	android/widget/EditText
    //   116: dup
    //   117: aload_0
    //   118: getfield 244	com/feelingk/iap/gui/parser/ParserXML:U	Landroid/content/Context;
    //   121: invokespecial 1595	android/widget/EditText:<init>	(Landroid/content/Context;)V
    //   124: astore_1
    //   125: aload_1
    //   126: checkcast 806	android/widget/EditText
    //   129: ldc_w 1596
    //   132: invokevirtual 1599	android/widget/EditText:setImeOptions	(I)V
    //   135: goto -95 -> 40
    //   138: aload 6
    //   140: ldc_w 688
    //   143: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   146: ifeq +23 -> 169
    //   149: new 722	android/widget/ScrollView
    //   152: dup
    //   153: aload_0
    //   154: getfield 244	com/feelingk/iap/gui/parser/ParserXML:U	Landroid/content/Context;
    //   157: invokespecial 887	android/widget/ScrollView:<init>	(Landroid/content/Context;)V
    //   160: astore_1
    //   161: aload_1
    //   162: iconst_0
    //   163: invokevirtual 892	android/view/View:setScrollbarFadingEnabled	(Z)V
    //   166: goto -126 -> 40
    //   169: aload 6
    //   171: ldc_w 894
    //   174: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   177: ifeq +18 -> 195
    //   180: new 896	android/widget/CheckBox
    //   183: dup
    //   184: aload_0
    //   185: getfield 244	com/feelingk/iap/gui/parser/ParserXML:U	Landroid/content/Context;
    //   188: invokespecial 897	android/widget/CheckBox:<init>	(Landroid/content/Context;)V
    //   191: astore_1
    //   192: goto -152 -> 40
    //   195: aload 6
    //   197: ldc_w 1601
    //   200: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   203: ifeq +18 -> 221
    //   206: new 1603	android/widget/RadioGroup
    //   209: dup
    //   210: aload_0
    //   211: getfield 244	com/feelingk/iap/gui/parser/ParserXML:U	Landroid/content/Context;
    //   214: invokespecial 1604	android/widget/RadioGroup:<init>	(Landroid/content/Context;)V
    //   217: astore_1
    //   218: goto -178 -> 40
    //   221: aload 6
    //   223: ldc_w 1606
    //   226: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   229: ifeq +18 -> 247
    //   232: new 1608	android/widget/RadioButton
    //   235: dup
    //   236: aload_0
    //   237: getfield 244	com/feelingk/iap/gui/parser/ParserXML:U	Landroid/content/Context;
    //   240: invokespecial 1609	android/widget/RadioButton:<init>	(Landroid/content/Context;)V
    //   243: astore_1
    //   244: goto -204 -> 40
    //   247: new 899	java/lang/StringBuilder
    //   250: dup
    //   251: ldc_w 901
    //   254: invokespecial 903	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   257: aload 6
    //   259: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   265: invokestatic 914	junit/framework/Assert:fail	(Ljava/lang/String;)V
    //   268: aload 4
    //   270: astore_1
    //   271: goto -231 -> 40
    //   274: aload_1
    //   275: instanceof 1603
    //   278: ifeq +1148 -> 1426
    //   281: aload_1
    //   282: checkcast 1603	android/widget/RadioGroup
    //   285: astore 4
    //   287: aload 5
    //   289: ldc_w 944
    //   292: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   295: astore 6
    //   297: aload 6
    //   299: ifnull +20 -> 319
    //   302: aload 6
    //   304: ldc_w 946
    //   307: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   310: ifeq +1087 -> 1397
    //   313: aload 4
    //   315: iconst_0
    //   316: invokevirtual 1610	android/widget/RadioGroup:setOrientation	(I)V
    //   319: aload 5
    //   321: ldc_w 1005
    //   324: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   327: astore 6
    //   329: aload 6
    //   331: ifnull +21 -> 352
    //   334: aload 6
    //   336: ldc_w 726
    //   339: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   342: ifeq +1075 -> 1417
    //   345: aload 4
    //   347: bipush 17
    //   349: invokevirtual 1611	android/widget/RadioGroup:setGravity	(I)V
    //   352: aload_1
    //   353: instanceof 877
    //   356: ifeq +157 -> 513
    //   359: aload_1
    //   360: checkcast 877	android/widget/TextView
    //   363: astore 4
    //   365: aload 5
    //   367: ldc_w 1026
    //   370: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   373: astore 6
    //   375: aload 5
    //   377: ldc_w 1043
    //   380: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   383: astore 7
    //   385: aload 5
    //   387: ldc_w 1053
    //   390: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   393: astore 8
    //   395: aload 5
    //   397: ldc_w 1055
    //   400: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   403: astore 9
    //   405: aload 5
    //   407: ldc_w 1005
    //   410: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   413: astore 10
    //   415: aload 7
    //   417: ifnull +19 -> 436
    //   420: aload 4
    //   422: aload 7
    //   424: ldc_w 1057
    //   427: ldc_w 1059
    //   430: invokevirtual 1063	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   433: invokevirtual 1064	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   436: aload 8
    //   438: ifnull +15 -> 453
    //   441: aload 4
    //   443: iconst_1
    //   444: aload 8
    //   446: invokestatic 1099	com/feelingk/iap/gui/parser/ParserXML:j	(Ljava/lang/String;)I
    //   449: i2f
    //   450: invokevirtual 1100	android/widget/TextView:setTextSize	(IF)V
    //   453: aload 9
    //   455: ifnull +13 -> 468
    //   458: aload 4
    //   460: aload 9
    //   462: invokestatic 804	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   465: invokevirtual 1101	android/widget/TextView:setTextColor	(I)V
    //   468: aload 6
    //   470: ifnull +22 -> 492
    //   473: aload 6
    //   475: ldc_w 1294
    //   478: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   481: ifeq +11 -> 492
    //   484: aload 4
    //   486: ldc_w 1306
    //   489: invokevirtual 1064	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   492: aload 10
    //   494: ifnull +1216 -> 1710
    //   497: aload 4
    //   499: bipush 17
    //   501: invokevirtual 1121	android/widget/TextView:setGravity	(I)V
    //   504: aload 4
    //   506: fconst_0
    //   507: ldc_w 1122
    //   510: invokevirtual 1126	android/widget/TextView:setLineSpacing	(FF)V
    //   513: aload_1
    //   514: instanceof 896
    //   517: ifeq +1203 -> 1720
    //   520: aload 5
    //   522: ldc_w 1131
    //   525: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   528: astore 9
    //   530: aload 5
    //   532: ldc_w 1476
    //   535: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   538: astore 4
    //   540: aload_1
    //   541: checkcast 896	android/widget/CheckBox
    //   544: astore 6
    //   546: aload 6
    //   548: aload 4
    //   550: invokevirtual 1480	android/widget/CheckBox:setTag	(Ljava/lang/Object;)V
    //   553: getstatic 708	android/os/Build:MODEL	Ljava/lang/String;
    //   556: astore 10
    //   558: iconst_0
    //   559: istore_2
    //   560: new 1135	android/graphics/drawable/StateListDrawable
    //   563: dup
    //   564: invokespecial 1136	android/graphics/drawable/StateListDrawable:<init>	()V
    //   567: astore 7
    //   569: new 1135	android/graphics/drawable/StateListDrawable
    //   572: dup
    //   573: invokespecial 1136	android/graphics/drawable/StateListDrawable:<init>	()V
    //   576: astore 8
    //   578: aload 10
    //   580: ldc_w 710
    //   583: invokevirtual 686	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   586: ifeq +10 -> 596
    //   589: iconst_1
    //   590: istore_2
    //   591: aload_0
    //   592: iconst_1
    //   593: putfield 326	com/feelingk/iap/gui/parser/ParserXML:c	Z
    //   596: iload_2
    //   597: ifne +270 -> 867
    //   600: aload_0
    //   601: invokevirtual 959	java/lang/Object:getClass	()Ljava/lang/Class;
    //   604: new 899	java/lang/StringBuilder
    //   607: dup
    //   608: aload_0
    //   609: getfield 274	com/feelingk/iap/gui/parser/ParserXML:aj	Ljava/lang/String;
    //   612: invokestatic 962	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   615: invokespecial 903	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   618: aload 9
    //   620: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   623: ldc_w 1038
    //   626: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   629: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   632: invokevirtual 970	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   635: astore 10
    //   637: aload 10
    //   639: aload 9
    //   641: invokestatic 976	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   644: astore 9
    //   646: aload 10
    //   648: invokevirtual 981	java/io/InputStream:close	()V
    //   651: aload_0
    //   652: invokevirtual 959	java/lang/Object:getClass	()Ljava/lang/Class;
    //   655: new 899	java/lang/StringBuilder
    //   658: dup
    //   659: aload_0
    //   660: getfield 274	com/feelingk/iap/gui/parser/ParserXML:aj	Ljava/lang/String;
    //   663: invokestatic 962	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   666: invokespecial 903	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   669: ldc_w 1484
    //   672: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   675: ldc_w 1038
    //   678: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   681: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   684: invokevirtual 970	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   687: astore 11
    //   689: aload 11
    //   691: ldc_w 1484
    //   694: invokestatic 976	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   697: astore 10
    //   699: aload 11
    //   701: invokevirtual 981	java/io/InputStream:close	()V
    //   704: aload 7
    //   706: iconst_2
    //   707: newarray int
    //   709: dup
    //   710: iconst_0
    //   711: ldc_w 1456
    //   714: iastore
    //   715: dup
    //   716: iconst_1
    //   717: ldc_w 1455
    //   720: iastore
    //   721: aload 9
    //   723: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   726: aload 7
    //   728: iconst_2
    //   729: newarray int
    //   731: dup
    //   732: iconst_0
    //   733: ldc_w 1457
    //   736: iastore
    //   737: dup
    //   738: iconst_1
    //   739: ldc_w 1455
    //   742: iastore
    //   743: aload 10
    //   745: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   748: aload 8
    //   750: iconst_2
    //   751: newarray int
    //   753: dup
    //   754: iconst_0
    //   755: ldc_w 1454
    //   758: iastore
    //   759: dup
    //   760: iconst_1
    //   761: ldc_w 1455
    //   764: iastore
    //   765: aconst_null
    //   766: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   769: aload 8
    //   771: iconst_2
    //   772: newarray int
    //   774: dup
    //   775: iconst_0
    //   776: ldc_w 1456
    //   779: iastore
    //   780: dup
    //   781: iconst_1
    //   782: ldc_w 1159
    //   785: iastore
    //   786: aconst_null
    //   787: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   790: aload 8
    //   792: iconst_2
    //   793: newarray int
    //   795: dup
    //   796: iconst_0
    //   797: ldc_w 1457
    //   800: iastore
    //   801: dup
    //   802: iconst_1
    //   803: ldc_w 1159
    //   806: iastore
    //   807: aconst_null
    //   808: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   811: aload 8
    //   813: iconst_2
    //   814: newarray int
    //   816: dup
    //   817: iconst_0
    //   818: ldc_w 1456
    //   821: iastore
    //   822: dup
    //   823: iconst_1
    //   824: ldc_w 1455
    //   827: iastore
    //   828: aconst_null
    //   829: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   832: aload 8
    //   834: iconst_2
    //   835: newarray int
    //   837: dup
    //   838: iconst_0
    //   839: ldc_w 1457
    //   842: iastore
    //   843: dup
    //   844: iconst_1
    //   845: ldc_w 1455
    //   848: iastore
    //   849: aconst_null
    //   850: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   853: aload 6
    //   855: aload 8
    //   857: invokevirtual 1460	android/widget/CheckBox:setButtonDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   860: aload 6
    //   862: aload 7
    //   864: invokevirtual 1461	android/widget/CheckBox:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   867: aload 6
    //   869: iconst_0
    //   870: invokevirtual 1464	android/widget/CheckBox:setChecked	(Z)V
    //   873: aload 6
    //   875: aload_0
    //   876: getfield 472	com/feelingk/iap/gui/parser/ParserXML:C	Landroid/widget/CompoundButton$OnCheckedChangeListener;
    //   879: invokevirtual 1474	android/widget/CheckBox:setOnCheckedChangeListener	(Landroid/widget/CompoundButton$OnCheckedChangeListener;)V
    //   882: aload 4
    //   884: ldc_w 1613
    //   887: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   890: ifeq +22 -> 912
    //   893: getstatic 226	com/feelingk/iap/gui/parser/ParserXML:bQ	Z
    //   896: ifeq +16 -> 912
    //   899: aload 6
    //   901: iconst_1
    //   902: invokevirtual 1464	android/widget/CheckBox:setChecked	(Z)V
    //   905: aload 6
    //   907: aload 7
    //   909: invokevirtual 1461	android/widget/CheckBox:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   912: aload_1
    //   913: instanceof 806
    //   916: ifeq +446 -> 1362
    //   919: aload_1
    //   920: checkcast 806	android/widget/EditText
    //   923: astore 4
    //   925: aload 4
    //   927: ldc_w 366
    //   930: invokevirtual 819	android/widget/EditText:setText	(Ljava/lang/CharSequence;)V
    //   933: aload_0
    //   934: getfield 248	com/feelingk/iap/gui/parser/ParserXML:W	Lcom/feelingk/iap/gui/parser/ax;
    //   937: ifnonnull +10 -> 947
    //   940: aload_0
    //   941: getfield 268	com/feelingk/iap/gui/parser/ParserXML:ah	Lcom/feelingk/iap/gui/parser/bb;
    //   944: ifnull +9 -> 953
    //   947: aload 4
    //   949: iconst_2
    //   950: invokevirtual 1616	android/widget/EditText:setInputType	(I)V
    //   953: new 1618	java/util/ArrayList
    //   956: dup
    //   957: invokespecial 1619	java/util/ArrayList:<init>	()V
    //   960: astore 6
    //   962: aload 5
    //   964: ldc_w 1621
    //   967: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   970: astore 7
    //   972: aload 7
    //   974: ifnull +35 -> 1009
    //   977: aload 7
    //   979: ldc_w 1623
    //   982: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   985: ifeq +1878 -> 2863
    //   988: aload 4
    //   990: bipush 17
    //   992: invokevirtual 1616	android/widget/EditText:setInputType	(I)V
    //   995: aload 6
    //   997: new 1625	com/feelingk/iap/gui/parser/bl
    //   1000: dup
    //   1001: aload_0
    //   1002: invokespecial 1626	com/feelingk/iap/gui/parser/bl:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;)V
    //   1005: invokevirtual 1629	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1008: pop
    //   1009: aload 5
    //   1011: ldc_w 1631
    //   1014: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1017: astore 7
    //   1019: aload 7
    //   1021: ifnull +26 -> 1047
    //   1024: aload 4
    //   1026: invokevirtual 1632	android/widget/EditText:setSingleLine	()V
    //   1029: aload 6
    //   1031: new 825	android/text/InputFilter$LengthFilter
    //   1034: dup
    //   1035: aload 7
    //   1037: invokestatic 1191	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1040: invokespecial 827	android/text/InputFilter$LengthFilter:<init>	(I)V
    //   1043: invokevirtual 1629	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1046: pop
    //   1047: aload 6
    //   1049: invokevirtual 1633	java/util/ArrayList:size	()I
    //   1052: ifle +20 -> 1072
    //   1055: aload 4
    //   1057: aload 6
    //   1059: iconst_0
    //   1060: anewarray 829	android/text/InputFilter
    //   1063: invokevirtual 1637	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   1066: checkcast 1639	[Landroid/text/InputFilter;
    //   1069: invokevirtual 833	android/widget/EditText:setFilters	([Landroid/text/InputFilter;)V
    //   1072: aload 5
    //   1074: ldc_w 1641
    //   1077: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1080: ifnull +15 -> 1095
    //   1083: aload 4
    //   1085: new 1643	android/text/method/PasswordTransformationMethod
    //   1088: dup
    //   1089: invokespecial 1644	android/text/method/PasswordTransformationMethod:<init>	()V
    //   1092: invokevirtual 1648	android/widget/EditText:setTransformationMethod	(Landroid/text/method/TransformationMethod;)V
    //   1095: aload 5
    //   1097: ldc_w 1650
    //   1100: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1103: astore 6
    //   1105: aload 6
    //   1107: ifnull +22 -> 1129
    //   1110: aload 6
    //   1112: ldc_w 1652
    //   1115: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1118: ifeq +11 -> 1129
    //   1121: aload 4
    //   1123: ldc_w 1654
    //   1126: invokevirtual 1657	android/widget/EditText:setPrivateImeOptions	(Ljava/lang/String;)V
    //   1129: aload 5
    //   1131: ldc_w 1026
    //   1134: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1137: astore 6
    //   1139: aload 6
    //   1141: ifnull +38 -> 1179
    //   1144: aload 6
    //   1146: ldc_w 1659
    //   1149: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1152: ifeq +1724 -> 2876
    //   1155: aload_0
    //   1156: aload 4
    //   1158: putfield 338	com/feelingk/iap/gui/parser/ParserXML:aK	Landroid/widget/EditText;
    //   1161: aload_0
    //   1162: getfield 268	com/feelingk/iap/gui/parser/ParserXML:ah	Lcom/feelingk/iap/gui/parser/bb;
    //   1165: ifnull +14 -> 1179
    //   1168: aload_0
    //   1169: getfield 338	com/feelingk/iap/gui/parser/ParserXML:aK	Landroid/widget/EditText;
    //   1172: aload_0
    //   1173: getfield 410	com/feelingk/iap/gui/parser/ParserXML:bW	Lcom/feelingk/iap/gui/parser/at;
    //   1176: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   1179: aload 5
    //   1181: ldc_w 951
    //   1184: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1187: astore 6
    //   1189: aload 6
    //   1191: ifnull +57 -> 1248
    //   1194: aload_0
    //   1195: invokevirtual 959	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1198: new 899	java/lang/StringBuilder
    //   1201: dup
    //   1202: aload_0
    //   1203: getfield 274	com/feelingk/iap/gui/parser/ParserXML:aj	Ljava/lang/String;
    //   1206: invokestatic 962	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1209: invokespecial 903	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1212: aload 6
    //   1214: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1217: ldc_w 1038
    //   1220: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1223: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1226: invokevirtual 970	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   1229: astore 7
    //   1231: aload 4
    //   1233: aload 7
    //   1235: aload 6
    //   1237: invokestatic 976	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1240: invokevirtual 1664	android/widget/EditText:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1243: aload 7
    //   1245: invokevirtual 981	java/io/InputStream:close	()V
    //   1248: aload 5
    //   1250: ldc_w 1666
    //   1253: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1256: ifnull +66 -> 1322
    //   1259: iconst_0
    //   1260: newarray int
    //   1262: astore 6
    //   1264: ldc_w 1668
    //   1267: invokestatic 804	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   1270: istore_2
    //   1271: ldc_w 1670
    //   1274: invokestatic 804	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   1277: istore_3
    //   1278: aload 4
    //   1280: new 1672	android/content/res/ColorStateList
    //   1283: dup
    //   1284: iconst_2
    //   1285: anewarray 1674	[I
    //   1288: dup
    //   1289: iconst_0
    //   1290: iconst_1
    //   1291: newarray int
    //   1293: dup
    //   1294: iconst_0
    //   1295: ldc_w 1159
    //   1298: iastore
    //   1299: aastore
    //   1300: dup
    //   1301: iconst_1
    //   1302: aload 6
    //   1304: aastore
    //   1305: iconst_2
    //   1306: newarray int
    //   1308: dup
    //   1309: iconst_0
    //   1310: iload_2
    //   1311: iastore
    //   1312: dup
    //   1313: iconst_1
    //   1314: iload_3
    //   1315: iastore
    //   1316: invokespecial 1677	android/content/res/ColorStateList:<init>	([[I[I)V
    //   1319: invokevirtual 1680	android/widget/EditText:setTextColor	(Landroid/content/res/ColorStateList;)V
    //   1322: aload_0
    //   1323: getfield 356	com/feelingk/iap/gui/parser/ParserXML:aT	Landroid/widget/EditText;
    //   1326: ifnull +18 -> 1344
    //   1329: aload_0
    //   1330: getfield 356	com/feelingk/iap/gui/parser/ParserXML:aT	Landroid/widget/EditText;
    //   1333: new 1682	com/feelingk/iap/gui/parser/ai
    //   1336: dup
    //   1337: aload_0
    //   1338: invokespecial 1683	com/feelingk/iap/gui/parser/ai:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;)V
    //   1341: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   1344: aload_0
    //   1345: getfield 362	com/feelingk/iap/gui/parser/ParserXML:aW	Landroid/widget/EditText;
    //   1348: ifnull +14 -> 1362
    //   1351: aload_0
    //   1352: getfield 362	com/feelingk/iap/gui/parser/ParserXML:aW	Landroid/widget/EditText;
    //   1355: aload_0
    //   1356: getfield 410	com/feelingk/iap/gui/parser/ParserXML:bW	Lcom/feelingk/iap/gui/parser/at;
    //   1359: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   1362: aload_1
    //   1363: astore 4
    //   1365: aload_0
    //   1366: getfield 240	com/feelingk/iap/gui/parser/ParserXML:R	Ljava/util/Stack;
    //   1369: invokevirtual 1210	java/util/Stack:size	()I
    //   1372: ifle -1325 -> 47
    //   1375: aload_1
    //   1376: aload_0
    //   1377: aload 5
    //   1379: aload_0
    //   1380: getfield 240	com/feelingk/iap/gui/parser/ParserXML:R	Ljava/util/Stack;
    //   1383: invokevirtual 662	java/util/Stack:peek	()Ljava/lang/Object;
    //   1386: checkcast 658	android/view/ViewGroup
    //   1389: invokespecial 1212	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Landroid/view/ViewGroup;)Landroid/view/ViewGroup$LayoutParams;
    //   1392: invokevirtual 1216	android/view/View:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   1395: aload_1
    //   1396: areturn
    //   1397: aload 6
    //   1399: ldc_w 1218
    //   1402: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1405: ifeq -1086 -> 319
    //   1408: aload 4
    //   1410: iconst_1
    //   1411: invokevirtual 1610	android/widget/RadioGroup:setOrientation	(I)V
    //   1414: goto -1095 -> 319
    //   1417: aload 4
    //   1419: iconst_5
    //   1420: invokevirtual 1611	android/widget/RadioGroup:setGravity	(I)V
    //   1423: goto -1071 -> 352
    //   1426: aload_1
    //   1427: instanceof 715
    //   1430: ifeq -1078 -> 352
    //   1433: aload_1
    //   1434: checkcast 715	android/widget/LinearLayout
    //   1437: astore 4
    //   1439: aload 5
    //   1441: ldc_w 1026
    //   1444: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1447: astore 6
    //   1449: aload 6
    //   1451: ifnull +26 -> 1477
    //   1454: aload 6
    //   1456: ldc_w 1685
    //   1459: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1462: ifeq +15 -> 1477
    //   1465: aload 4
    //   1467: putstatic 202	com/feelingk/iap/gui/parser/ParserXML:bu	Landroid/widget/LinearLayout;
    //   1470: aload 4
    //   1472: bipush 8
    //   1474: invokevirtual 1034	android/widget/LinearLayout:setVisibility	(I)V
    //   1477: aload 5
    //   1479: ldc_w 944
    //   1482: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1485: astore 6
    //   1487: aload 6
    //   1489: ifnull +20 -> 1509
    //   1492: aload 6
    //   1494: ldc_w 946
    //   1497: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1500: ifeq +181 -> 1681
    //   1503: aload 4
    //   1505: iconst_0
    //   1506: invokevirtual 949	android/widget/LinearLayout:setOrientation	(I)V
    //   1509: aload 5
    //   1511: ldc_w 951
    //   1514: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1517: astore 6
    //   1519: aload 6
    //   1521: ifnull +57 -> 1578
    //   1524: aload_0
    //   1525: invokevirtual 959	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1528: new 899	java/lang/StringBuilder
    //   1531: dup
    //   1532: aload_0
    //   1533: getfield 274	com/feelingk/iap/gui/parser/ParserXML:aj	Ljava/lang/String;
    //   1536: invokestatic 962	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1539: invokespecial 903	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1542: aload 6
    //   1544: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1547: ldc_w 1038
    //   1550: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1553: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1556: invokevirtual 970	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   1559: astore 7
    //   1561: aload 4
    //   1563: aload 7
    //   1565: aload 6
    //   1567: invokestatic 976	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1570: invokevirtual 997	android/widget/LinearLayout:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1573: aload 7
    //   1575: invokevirtual 981	java/io/InputStream:close	()V
    //   1578: aload 5
    //   1580: ldc_w 999
    //   1583: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1586: ifnull +11 -> 1597
    //   1589: aload 4
    //   1591: ldc_w 1000
    //   1594: invokevirtual 1003	android/widget/LinearLayout:setBackgroundColor	(I)V
    //   1597: aload 5
    //   1599: ldc_w 1005
    //   1602: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1605: astore 6
    //   1607: aload 6
    //   1609: ifnull +21 -> 1630
    //   1612: aload 6
    //   1614: ldc_w 726
    //   1617: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1620: ifeq +81 -> 1701
    //   1623: aload 4
    //   1625: bipush 17
    //   1627: invokevirtual 1008	android/widget/LinearLayout:setGravity	(I)V
    //   1630: aload 5
    //   1632: ldc_w 1010
    //   1635: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1638: astore 6
    //   1640: aload 6
    //   1642: ifnull +19 -> 1661
    //   1645: aload_0
    //   1646: aload 6
    //   1648: invokespecial 750	com/feelingk/iap/gui/parser/ParserXML:k	(Ljava/lang/String;)I
    //   1651: istore_2
    //   1652: aload 4
    //   1654: iload_2
    //   1655: iload_2
    //   1656: iload_2
    //   1657: iload_2
    //   1658: invokevirtual 1011	android/widget/LinearLayout:setPadding	(IIII)V
    //   1661: aload 5
    //   1663: ldc_w 1013
    //   1666: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1669: ifnull -1317 -> 352
    //   1672: aload 4
    //   1674: iconst_1
    //   1675: invokevirtual 1016	android/widget/LinearLayout:setFocusableInTouchMode	(Z)V
    //   1678: goto -1326 -> 352
    //   1681: aload 6
    //   1683: ldc_w 1218
    //   1686: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1689: ifeq -180 -> 1509
    //   1692: aload 4
    //   1694: iconst_1
    //   1695: invokevirtual 949	android/widget/LinearLayout:setOrientation	(I)V
    //   1698: goto -189 -> 1509
    //   1701: aload 4
    //   1703: iconst_5
    //   1704: invokevirtual 1008	android/widget/LinearLayout:setGravity	(I)V
    //   1707: goto -77 -> 1630
    //   1710: aload 4
    //   1712: bipush 19
    //   1714: invokevirtual 1121	android/widget/TextView:setGravity	(I)V
    //   1717: goto -1213 -> 504
    //   1720: aload_1
    //   1721: instanceof 1608
    //   1724: ifeq +434 -> 2158
    //   1727: aload_1
    //   1728: checkcast 1608	android/widget/RadioButton
    //   1731: astore 4
    //   1733: aload 5
    //   1735: ldc_w 1026
    //   1738: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1741: astore 6
    //   1743: aload 5
    //   1745: ldc_w 1131
    //   1748: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1751: astore 9
    //   1753: aload 5
    //   1755: ldc_w 1687
    //   1758: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1761: astore 8
    //   1763: aload 6
    //   1765: ifnull +51 -> 1816
    //   1768: aload 6
    //   1770: ldc_w 1689
    //   1773: invokevirtual 775	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   1776: iconst_m1
    //   1777: if_icmpeq +39 -> 1816
    //   1780: aload 6
    //   1782: ldc_w 1691
    //   1785: invokevirtual 1694	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1788: ifeq +324 -> 2112
    //   1791: aload_0
    //   1792: getfield 467	com/feelingk/iap/gui/parser/ParserXML:B	Lcom/feelingk/iap/gui/parser/aw;
    //   1795: aload 4
    //   1797: invokevirtual 1697	com/feelingk/iap/gui/parser/aw:a	(Landroid/widget/RadioButton;)V
    //   1800: aload 4
    //   1802: aload 6
    //   1804: invokevirtual 1698	android/widget/RadioButton:setTag	(Ljava/lang/Object;)V
    //   1807: aload 4
    //   1809: aload_0
    //   1810: getfield 467	com/feelingk/iap/gui/parser/ParserXML:B	Lcom/feelingk/iap/gui/parser/aw;
    //   1813: invokevirtual 1699	android/widget/RadioButton:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   1816: aload 9
    //   1818: ifnull -906 -> 912
    //   1821: aload 8
    //   1823: ifnull -911 -> 912
    //   1826: new 1135	android/graphics/drawable/StateListDrawable
    //   1829: dup
    //   1830: invokespecial 1136	android/graphics/drawable/StateListDrawable:<init>	()V
    //   1833: astore 6
    //   1835: new 1135	android/graphics/drawable/StateListDrawable
    //   1838: dup
    //   1839: invokespecial 1136	android/graphics/drawable/StateListDrawable:<init>	()V
    //   1842: astore 7
    //   1844: aload_0
    //   1845: invokevirtual 959	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1848: new 899	java/lang/StringBuilder
    //   1851: dup
    //   1852: aload_0
    //   1853: getfield 274	com/feelingk/iap/gui/parser/ParserXML:aj	Ljava/lang/String;
    //   1856: invokestatic 962	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1859: invokespecial 903	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1862: aload 9
    //   1864: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1867: ldc_w 1038
    //   1870: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1873: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1876: invokevirtual 970	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   1879: astore 10
    //   1881: aload 10
    //   1883: aload 9
    //   1885: invokestatic 976	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1888: astore 9
    //   1890: aload 10
    //   1892: invokevirtual 981	java/io/InputStream:close	()V
    //   1895: aload_0
    //   1896: invokevirtual 959	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1899: new 899	java/lang/StringBuilder
    //   1902: dup
    //   1903: aload_0
    //   1904: getfield 274	com/feelingk/iap/gui/parser/ParserXML:aj	Ljava/lang/String;
    //   1907: invokestatic 962	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1910: invokespecial 903	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1913: aload 8
    //   1915: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1918: ldc_w 1038
    //   1921: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1924: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1927: invokevirtual 970	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   1930: astore 10
    //   1932: aload 10
    //   1934: aload 8
    //   1936: invokestatic 976	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1939: astore 8
    //   1941: aload 10
    //   1943: invokevirtual 981	java/io/InputStream:close	()V
    //   1946: aload 6
    //   1948: iconst_2
    //   1949: newarray int
    //   1951: dup
    //   1952: iconst_0
    //   1953: ldc_w 1456
    //   1956: iastore
    //   1957: dup
    //   1958: iconst_1
    //   1959: ldc_w 1455
    //   1962: iastore
    //   1963: aload 9
    //   1965: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   1968: aload 6
    //   1970: iconst_2
    //   1971: newarray int
    //   1973: dup
    //   1974: iconst_0
    //   1975: ldc_w 1457
    //   1978: iastore
    //   1979: dup
    //   1980: iconst_1
    //   1981: ldc_w 1455
    //   1984: iastore
    //   1985: aload 8
    //   1987: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   1990: aload 7
    //   1992: iconst_2
    //   1993: newarray int
    //   1995: dup
    //   1996: iconst_0
    //   1997: ldc_w 1454
    //   2000: iastore
    //   2001: dup
    //   2002: iconst_1
    //   2003: ldc_w 1455
    //   2006: iastore
    //   2007: aconst_null
    //   2008: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2011: aload 7
    //   2013: iconst_2
    //   2014: newarray int
    //   2016: dup
    //   2017: iconst_0
    //   2018: ldc_w 1456
    //   2021: iastore
    //   2022: dup
    //   2023: iconst_1
    //   2024: ldc_w 1159
    //   2027: iastore
    //   2028: aconst_null
    //   2029: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2032: aload 7
    //   2034: iconst_2
    //   2035: newarray int
    //   2037: dup
    //   2038: iconst_0
    //   2039: ldc_w 1457
    //   2042: iastore
    //   2043: dup
    //   2044: iconst_1
    //   2045: ldc_w 1159
    //   2048: iastore
    //   2049: aconst_null
    //   2050: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2053: aload 7
    //   2055: iconst_2
    //   2056: newarray int
    //   2058: dup
    //   2059: iconst_0
    //   2060: ldc_w 1456
    //   2063: iastore
    //   2064: dup
    //   2065: iconst_1
    //   2066: ldc_w 1455
    //   2069: iastore
    //   2070: aconst_null
    //   2071: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2074: aload 7
    //   2076: iconst_2
    //   2077: newarray int
    //   2079: dup
    //   2080: iconst_0
    //   2081: ldc_w 1457
    //   2084: iastore
    //   2085: dup
    //   2086: iconst_1
    //   2087: ldc_w 1455
    //   2090: iastore
    //   2091: aconst_null
    //   2092: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2095: aload 4
    //   2097: aload 7
    //   2099: invokevirtual 1700	android/widget/RadioButton:setButtonDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2102: aload 4
    //   2104: aload 6
    //   2106: invokevirtual 1701	android/widget/RadioButton:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2109: goto -1197 -> 912
    //   2112: aload 6
    //   2114: ldc_w 1703
    //   2117: invokevirtual 1694	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2120: ifeq +15 -> 2135
    //   2123: aload_0
    //   2124: getfield 467	com/feelingk/iap/gui/parser/ParserXML:B	Lcom/feelingk/iap/gui/parser/aw;
    //   2127: aload 4
    //   2129: invokevirtual 1705	com/feelingk/iap/gui/parser/aw:b	(Landroid/widget/RadioButton;)V
    //   2132: goto -332 -> 1800
    //   2135: aload 6
    //   2137: ldc_w 1707
    //   2140: invokevirtual 1694	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2143: ifeq -343 -> 1800
    //   2146: aload_0
    //   2147: getfield 467	com/feelingk/iap/gui/parser/ParserXML:B	Lcom/feelingk/iap/gui/parser/aw;
    //   2150: aload 4
    //   2152: invokevirtual 1709	com/feelingk/iap/gui/parser/aw:c	(Landroid/widget/RadioButton;)V
    //   2155: goto -355 -> 1800
    //   2158: aload_1
    //   2159: instanceof 837
    //   2162: ifeq -1250 -> 912
    //   2165: aload 5
    //   2167: ldc_w 1129
    //   2170: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   2173: astore 8
    //   2175: aload 5
    //   2177: ldc_w 1131
    //   2180: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   2183: astore 4
    //   2185: aload 5
    //   2187: ldc_w 1026
    //   2190: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   2193: astore 6
    //   2195: aload_1
    //   2196: checkcast 837	android/widget/Button
    //   2199: astore 7
    //   2201: aload_0
    //   2202: invokevirtual 959	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2205: new 899	java/lang/StringBuilder
    //   2208: dup
    //   2209: aload_0
    //   2210: getfield 274	com/feelingk/iap/gui/parser/ParserXML:aj	Ljava/lang/String;
    //   2213: invokestatic 962	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   2216: invokespecial 903	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2219: aload 8
    //   2221: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2224: ldc_w 1038
    //   2227: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2230: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2233: invokevirtual 970	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   2236: astore 9
    //   2238: aload 9
    //   2240: aload 8
    //   2242: invokestatic 976	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   2245: astore 8
    //   2247: aload 9
    //   2249: invokevirtual 981	java/io/InputStream:close	()V
    //   2252: aload_0
    //   2253: invokevirtual 959	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2256: new 899	java/lang/StringBuilder
    //   2259: dup
    //   2260: aload_0
    //   2261: getfield 274	com/feelingk/iap/gui/parser/ParserXML:aj	Ljava/lang/String;
    //   2264: invokestatic 962	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   2267: invokespecial 903	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2270: ldc_w 1711
    //   2273: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2276: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2279: invokevirtual 970	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   2282: astore 10
    //   2284: aload 10
    //   2286: ldc_w 1713
    //   2289: invokestatic 976	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   2292: astore 9
    //   2294: aload 10
    //   2296: invokevirtual 981	java/io/InputStream:close	()V
    //   2299: aload_0
    //   2300: invokevirtual 959	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2303: new 899	java/lang/StringBuilder
    //   2306: dup
    //   2307: aload_0
    //   2308: getfield 274	com/feelingk/iap/gui/parser/ParserXML:aj	Ljava/lang/String;
    //   2311: invokestatic 962	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   2314: invokespecial 903	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2317: aload 4
    //   2319: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2322: ldc_w 1038
    //   2325: invokevirtual 906	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2328: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2331: invokevirtual 970	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   2334: astore 11
    //   2336: aload 11
    //   2338: aload 4
    //   2340: invokestatic 976	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   2343: astore 10
    //   2345: aload 11
    //   2347: invokevirtual 981	java/io/InputStream:close	()V
    //   2350: new 1135	android/graphics/drawable/StateListDrawable
    //   2353: dup
    //   2354: invokespecial 1136	android/graphics/drawable/StateListDrawable:<init>	()V
    //   2357: astore 11
    //   2359: aload_0
    //   2360: new 1135	android/graphics/drawable/StateListDrawable
    //   2363: dup
    //   2364: invokespecial 1136	android/graphics/drawable/StateListDrawable:<init>	()V
    //   2367: putfield 594	com/feelingk/iap/gui/parser/ParserXML:bo	Landroid/graphics/drawable/StateListDrawable;
    //   2370: aload_0
    //   2371: new 1135	android/graphics/drawable/StateListDrawable
    //   2374: dup
    //   2375: invokespecial 1136	android/graphics/drawable/StateListDrawable:<init>	()V
    //   2378: putfield 611	com/feelingk/iap/gui/parser/ParserXML:bj	Landroid/graphics/drawable/StateListDrawable;
    //   2381: aload_0
    //   2382: new 1135	android/graphics/drawable/StateListDrawable
    //   2385: dup
    //   2386: invokespecial 1136	android/graphics/drawable/StateListDrawable:<init>	()V
    //   2389: putfield 609	com/feelingk/iap/gui/parser/ParserXML:bk	Landroid/graphics/drawable/StateListDrawable;
    //   2392: aload 11
    //   2394: iconst_1
    //   2395: newarray int
    //   2397: dup
    //   2398: iconst_0
    //   2399: ldc_w 1159
    //   2402: iastore
    //   2403: aload 10
    //   2405: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2408: aload 11
    //   2410: iconst_0
    //   2411: newarray int
    //   2413: aload 8
    //   2415: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2418: aload_0
    //   2419: getfield 594	com/feelingk/iap/gui/parser/ParserXML:bo	Landroid/graphics/drawable/StateListDrawable;
    //   2422: iconst_1
    //   2423: newarray int
    //   2425: dup
    //   2426: iconst_0
    //   2427: ldc_w 1159
    //   2430: iastore
    //   2431: aload 9
    //   2433: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2436: aload_0
    //   2437: getfield 594	com/feelingk/iap/gui/parser/ParserXML:bo	Landroid/graphics/drawable/StateListDrawable;
    //   2440: iconst_0
    //   2441: newarray int
    //   2443: aload 9
    //   2445: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2448: aload 6
    //   2450: ifnull +53 -> 2503
    //   2453: aload 6
    //   2455: ldc_w 1715
    //   2458: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2461: ifne +36 -> 2497
    //   2464: aload 6
    //   2466: ldc_w 1717
    //   2469: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2472: ifne +25 -> 2497
    //   2475: aload 6
    //   2477: ldc_w 1719
    //   2480: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2483: ifne +14 -> 2497
    //   2486: aload 6
    //   2488: ldc_w 1721
    //   2491: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2494: ifeq +9 -> 2503
    //   2497: aload_0
    //   2498: aload 11
    //   2500: putfield 596	com/feelingk/iap/gui/parser/ParserXML:ba	Landroid/graphics/drawable/StateListDrawable;
    //   2503: aload 7
    //   2505: aload 11
    //   2507: invokevirtual 841	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2510: aload 5
    //   2512: ldc_w 1010
    //   2515: invokestatic 697	com/feelingk/iap/gui/parser/ParserXML:a	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   2518: astore 9
    //   2520: aload 9
    //   2522: ifnull +19 -> 2541
    //   2525: aload_0
    //   2526: aload 9
    //   2528: invokespecial 750	com/feelingk/iap/gui/parser/ParserXML:k	(Ljava/lang/String;)I
    //   2531: istore_2
    //   2532: aload 7
    //   2534: iload_2
    //   2535: iload_2
    //   2536: iload_2
    //   2537: iload_2
    //   2538: invokevirtual 1179	android/widget/Button:setPadding	(IIII)V
    //   2541: aload 4
    //   2543: ldc_w 1495
    //   2546: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2549: ifeq +178 -> 2727
    //   2552: aload 7
    //   2554: aload_0
    //   2555: getfield 417	com/feelingk/iap/gui/parser/ParserXML:r	Landroid/view/View$OnClickListener;
    //   2558: invokevirtual 845	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   2561: aload 6
    //   2563: ifnull -1651 -> 912
    //   2566: aload_0
    //   2567: aload 11
    //   2569: putfield 609	com/feelingk/iap/gui/parser/ParserXML:bk	Landroid/graphics/drawable/StateListDrawable;
    //   2572: aload_0
    //   2573: getfield 611	com/feelingk/iap/gui/parser/ParserXML:bj	Landroid/graphics/drawable/StateListDrawable;
    //   2576: iconst_1
    //   2577: newarray int
    //   2579: dup
    //   2580: iconst_0
    //   2581: ldc_w 1159
    //   2584: iastore
    //   2585: aload 8
    //   2587: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2590: aload_0
    //   2591: getfield 611	com/feelingk/iap/gui/parser/ParserXML:bj	Landroid/graphics/drawable/StateListDrawable;
    //   2594: iconst_0
    //   2595: newarray int
    //   2597: aload 8
    //   2599: invokevirtual 1163	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2602: aload 6
    //   2604: ldc_w 1723
    //   2607: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2610: ifeq +152 -> 2762
    //   2613: aload_0
    //   2614: aload 7
    //   2616: putfield 390	com/feelingk/iap/gui/parser/ParserXML:bE	Landroid/widget/Button;
    //   2619: aload_0
    //   2620: getfield 390	com/feelingk/iap/gui/parser/ParserXML:bE	Landroid/widget/Button;
    //   2623: aload_0
    //   2624: getfield 611	com/feelingk/iap/gui/parser/ParserXML:bj	Landroid/graphics/drawable/StateListDrawable;
    //   2627: invokevirtual 841	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2630: aload_0
    //   2631: getfield 390	com/feelingk/iap/gui/parser/ParserXML:bE	Landroid/widget/Button;
    //   2634: iconst_0
    //   2635: invokevirtual 1203	android/widget/Button:setClickable	(Z)V
    //   2638: aload_0
    //   2639: getfield 390	com/feelingk/iap/gui/parser/ParserXML:bE	Landroid/widget/Button;
    //   2642: ldc_w 1200
    //   2645: invokestatic 804	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   2648: invokevirtual 1176	android/widget/Button:setTextColor	(I)V
    //   2651: aload_0
    //   2652: getfield 390	com/feelingk/iap/gui/parser/ParserXML:bE	Landroid/widget/Button;
    //   2655: iconst_0
    //   2656: iconst_0
    //   2657: iconst_0
    //   2658: iconst_0
    //   2659: invokevirtual 1179	android/widget/Button:setPadding	(IIII)V
    //   2662: aload_0
    //   2663: getfield 390	com/feelingk/iap/gui/parser/ParserXML:bE	Landroid/widget/Button;
    //   2666: ifnull +11 -> 2677
    //   2669: aload_0
    //   2670: getfield 390	com/feelingk/iap/gui/parser/ParserXML:bE	Landroid/widget/Button;
    //   2673: iconst_0
    //   2674: invokevirtual 1203	android/widget/Button:setClickable	(Z)V
    //   2677: aload 6
    //   2679: ldc_w 1715
    //   2682: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2685: ifne +14 -> 2699
    //   2688: aload 6
    //   2690: ldc_w 1717
    //   2693: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2696: ifeq -1784 -> 912
    //   2699: aload_0
    //   2700: aload 7
    //   2702: putfield 600	com/feelingk/iap/gui/parser/ParserXML:bs	Landroid/widget/Button;
    //   2705: aload_0
    //   2706: getfield 600	com/feelingk/iap/gui/parser/ParserXML:bs	Landroid/widget/Button;
    //   2709: aload_0
    //   2710: getfield 594	com/feelingk/iap/gui/parser/ParserXML:bo	Landroid/graphics/drawable/StateListDrawable;
    //   2713: invokevirtual 841	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2716: aload_0
    //   2717: getfield 600	com/feelingk/iap/gui/parser/ParserXML:bs	Landroid/widget/Button;
    //   2720: iconst_0
    //   2721: invokevirtual 1203	android/widget/Button:setClickable	(Z)V
    //   2724: goto -1812 -> 912
    //   2727: aload 4
    //   2729: ldc_w 1725
    //   2732: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2735: ifeq +15 -> 2750
    //   2738: aload 7
    //   2740: aload_0
    //   2741: getfield 427	com/feelingk/iap/gui/parser/ParserXML:t	Landroid/view/View$OnClickListener;
    //   2744: invokevirtual 845	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   2747: goto -186 -> 2561
    //   2750: aload 7
    //   2752: aload_0
    //   2753: getfield 422	com/feelingk/iap/gui/parser/ParserXML:s	Landroid/view/View$OnClickListener;
    //   2756: invokevirtual 845	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   2759: goto -198 -> 2561
    //   2762: aload 6
    //   2764: ldc_w 1727
    //   2767: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2770: ifeq +15 -> 2785
    //   2773: aload 7
    //   2775: iconst_0
    //   2776: iconst_0
    //   2777: iconst_0
    //   2778: iconst_0
    //   2779: invokevirtual 1179	android/widget/Button:setPadding	(IIII)V
    //   2782: goto -120 -> 2662
    //   2785: aload 6
    //   2787: ldc_w 1719
    //   2790: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2793: ifeq +31 -> 2824
    //   2796: aload_0
    //   2797: aload 7
    //   2799: putfield 598	com/feelingk/iap/gui/parser/ParserXML:bF	Landroid/widget/Button;
    //   2802: aload_0
    //   2803: getfield 598	com/feelingk/iap/gui/parser/ParserXML:bF	Landroid/widget/Button;
    //   2806: aload_0
    //   2807: getfield 594	com/feelingk/iap/gui/parser/ParserXML:bo	Landroid/graphics/drawable/StateListDrawable;
    //   2810: invokevirtual 841	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2813: aload_0
    //   2814: getfield 598	com/feelingk/iap/gui/parser/ParserXML:bF	Landroid/widget/Button;
    //   2817: iconst_0
    //   2818: invokevirtual 1203	android/widget/Button:setClickable	(Z)V
    //   2821: goto -159 -> 2662
    //   2824: aload 6
    //   2826: ldc_w 1721
    //   2829: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2832: ifeq -170 -> 2662
    //   2835: aload_0
    //   2836: aload 7
    //   2838: putfield 592	com/feelingk/iap/gui/parser/ParserXML:bG	Landroid/widget/Button;
    //   2841: aload_0
    //   2842: getfield 592	com/feelingk/iap/gui/parser/ParserXML:bG	Landroid/widget/Button;
    //   2845: aload_0
    //   2846: getfield 594	com/feelingk/iap/gui/parser/ParserXML:bo	Landroid/graphics/drawable/StateListDrawable;
    //   2849: invokevirtual 841	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2852: aload_0
    //   2853: getfield 592	com/feelingk/iap/gui/parser/ParserXML:bG	Landroid/widget/Button;
    //   2856: iconst_0
    //   2857: invokevirtual 1203	android/widget/Button:setClickable	(Z)V
    //   2860: goto -198 -> 2662
    //   2863: aload 4
    //   2865: aload 7
    //   2867: invokestatic 1191	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   2870: invokevirtual 1616	android/widget/EditText:setInputType	(I)V
    //   2873: goto -1864 -> 1009
    //   2876: aload 6
    //   2878: ldc_w 1729
    //   2881: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2884: ifeq +30 -> 2914
    //   2887: aload_0
    //   2888: aload 4
    //   2890: putfield 340	com/feelingk/iap/gui/parser/ParserXML:aL	Landroid/widget/EditText;
    //   2893: aload_0
    //   2894: getfield 268	com/feelingk/iap/gui/parser/ParserXML:ah	Lcom/feelingk/iap/gui/parser/bb;
    //   2897: ifnull -1718 -> 1179
    //   2900: aload_0
    //   2901: getfield 340	com/feelingk/iap/gui/parser/ParserXML:aL	Landroid/widget/EditText;
    //   2904: aload_0
    //   2905: getfield 410	com/feelingk/iap/gui/parser/ParserXML:bW	Lcom/feelingk/iap/gui/parser/at;
    //   2908: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   2911: goto -1732 -> 1179
    //   2914: aload 6
    //   2916: ldc_w 1731
    //   2919: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2922: ifeq +30 -> 2952
    //   2925: aload_0
    //   2926: aload 4
    //   2928: putfield 342	com/feelingk/iap/gui/parser/ParserXML:aM	Landroid/widget/EditText;
    //   2931: aload_0
    //   2932: getfield 342	com/feelingk/iap/gui/parser/ParserXML:aM	Landroid/widget/EditText;
    //   2935: new 1733	com/feelingk/iap/gui/parser/au
    //   2938: dup
    //   2939: aload_0
    //   2940: aload 4
    //   2942: iconst_0
    //   2943: invokespecial 1736	com/feelingk/iap/gui/parser/au:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;B)V
    //   2946: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   2949: goto -1770 -> 1179
    //   2952: aload 6
    //   2954: ldc_w 1738
    //   2957: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2960: ifeq +30 -> 2990
    //   2963: aload_0
    //   2964: aload 4
    //   2966: putfield 344	com/feelingk/iap/gui/parser/ParserXML:aN	Landroid/widget/EditText;
    //   2969: aload_0
    //   2970: getfield 344	com/feelingk/iap/gui/parser/ParserXML:aN	Landroid/widget/EditText;
    //   2973: new 1733	com/feelingk/iap/gui/parser/au
    //   2976: dup
    //   2977: aload_0
    //   2978: aload 4
    //   2980: iconst_0
    //   2981: invokespecial 1736	com/feelingk/iap/gui/parser/au:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;B)V
    //   2984: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   2987: goto -1808 -> 1179
    //   2990: aload 6
    //   2992: ldc_w 1740
    //   2995: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2998: ifeq +30 -> 3028
    //   3001: aload_0
    //   3002: aload 4
    //   3004: putfield 346	com/feelingk/iap/gui/parser/ParserXML:aO	Landroid/widget/EditText;
    //   3007: aload_0
    //   3008: getfield 346	com/feelingk/iap/gui/parser/ParserXML:aO	Landroid/widget/EditText;
    //   3011: new 1733	com/feelingk/iap/gui/parser/au
    //   3014: dup
    //   3015: aload_0
    //   3016: aload 4
    //   3018: iconst_0
    //   3019: invokespecial 1736	com/feelingk/iap/gui/parser/au:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;B)V
    //   3022: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3025: goto -1846 -> 1179
    //   3028: aload 6
    //   3030: ldc_w 1742
    //   3033: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3036: ifeq +12 -> 3048
    //   3039: aload_0
    //   3040: aload 4
    //   3042: putfield 348	com/feelingk/iap/gui/parser/ParserXML:aP	Landroid/widget/EditText;
    //   3045: goto -1866 -> 1179
    //   3048: aload 6
    //   3050: ldc_w 1744
    //   3053: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3056: ifeq +28 -> 3084
    //   3059: aload_0
    //   3060: aload 4
    //   3062: putfield 350	com/feelingk/iap/gui/parser/ParserXML:aQ	Landroid/widget/EditText;
    //   3065: aload_0
    //   3066: getfield 350	com/feelingk/iap/gui/parser/ParserXML:aQ	Landroid/widget/EditText;
    //   3069: new 1746	com/feelingk/iap/gui/parser/av
    //   3072: dup
    //   3073: aload_0
    //   3074: iconst_0
    //   3075: invokespecial 1747	com/feelingk/iap/gui/parser/av:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;B)V
    //   3078: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3081: goto -1902 -> 1179
    //   3084: aload 6
    //   3086: ldc_w 1749
    //   3089: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3092: ifeq +39 -> 3131
    //   3095: aload_0
    //   3096: aload 4
    //   3098: putfield 352	com/feelingk/iap/gui/parser/ParserXML:aR	Landroid/widget/EditText;
    //   3101: getstatic 226	com/feelingk/iap/gui/parser/ParserXML:bQ	Z
    //   3104: ifeq +13 -> 3117
    //   3107: aload_0
    //   3108: getfield 352	com/feelingk/iap/gui/parser/ParserXML:aR	Landroid/widget/EditText;
    //   3111: getstatic 228	com/feelingk/iap/gui/parser/ParserXML:bR	Ljava/lang/String;
    //   3114: invokevirtual 819	android/widget/EditText:setText	(Ljava/lang/CharSequence;)V
    //   3117: aload_0
    //   3118: getfield 352	com/feelingk/iap/gui/parser/ParserXML:aR	Landroid/widget/EditText;
    //   3121: aload_0
    //   3122: getfield 405	com/feelingk/iap/gui/parser/ParserXML:bV	Lcom/feelingk/iap/gui/parser/as;
    //   3125: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3128: goto -1949 -> 1179
    //   3131: aload 6
    //   3133: ldc_w 1751
    //   3136: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3139: ifeq +23 -> 3162
    //   3142: aload_0
    //   3143: aload 4
    //   3145: putfield 354	com/feelingk/iap/gui/parser/ParserXML:aS	Landroid/widget/EditText;
    //   3148: aload_0
    //   3149: getfield 354	com/feelingk/iap/gui/parser/ParserXML:aS	Landroid/widget/EditText;
    //   3152: aload_0
    //   3153: getfield 405	com/feelingk/iap/gui/parser/ParserXML:bV	Lcom/feelingk/iap/gui/parser/as;
    //   3156: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3159: goto -1980 -> 1179
    //   3162: aload 6
    //   3164: ldc_w 1753
    //   3167: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3170: ifeq +12 -> 3182
    //   3173: aload_0
    //   3174: aload 4
    //   3176: putfield 356	com/feelingk/iap/gui/parser/ParserXML:aT	Landroid/widget/EditText;
    //   3179: goto -2000 -> 1179
    //   3182: aload 6
    //   3184: ldc_w 1755
    //   3187: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3190: ifeq +41 -> 3231
    //   3193: aload_0
    //   3194: aload 4
    //   3196: putfield 358	com/feelingk/iap/gui/parser/ParserXML:aU	Landroid/widget/EditText;
    //   3199: aload_0
    //   3200: getfield 358	com/feelingk/iap/gui/parser/ParserXML:aU	Landroid/widget/EditText;
    //   3203: new 1733	com/feelingk/iap/gui/parser/au
    //   3206: dup
    //   3207: aload_0
    //   3208: aload 4
    //   3210: iconst_0
    //   3211: invokespecial 1736	com/feelingk/iap/gui/parser/au:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;B)V
    //   3214: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3217: aload_0
    //   3218: getfield 358	com/feelingk/iap/gui/parser/ParserXML:aU	Landroid/widget/EditText;
    //   3221: aload_0
    //   3222: getfield 410	com/feelingk/iap/gui/parser/ParserXML:bW	Lcom/feelingk/iap/gui/parser/at;
    //   3225: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3228: goto -2049 -> 1179
    //   3231: aload 6
    //   3233: ldc_w 1757
    //   3236: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3239: ifeq +41 -> 3280
    //   3242: aload_0
    //   3243: aload 4
    //   3245: putfield 360	com/feelingk/iap/gui/parser/ParserXML:aV	Landroid/widget/EditText;
    //   3248: aload_0
    //   3249: getfield 360	com/feelingk/iap/gui/parser/ParserXML:aV	Landroid/widget/EditText;
    //   3252: new 1733	com/feelingk/iap/gui/parser/au
    //   3255: dup
    //   3256: aload_0
    //   3257: aload 4
    //   3259: iconst_0
    //   3260: invokespecial 1736	com/feelingk/iap/gui/parser/au:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;B)V
    //   3263: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3266: aload_0
    //   3267: getfield 360	com/feelingk/iap/gui/parser/ParserXML:aV	Landroid/widget/EditText;
    //   3270: aload_0
    //   3271: getfield 410	com/feelingk/iap/gui/parser/ParserXML:bW	Lcom/feelingk/iap/gui/parser/at;
    //   3274: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3277: goto -2098 -> 1179
    //   3280: aload 6
    //   3282: ldc_w 1759
    //   3285: invokevirtual 553	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3288: ifeq -2109 -> 1179
    //   3291: aload_0
    //   3292: aload 4
    //   3294: putfield 362	com/feelingk/iap/gui/parser/ParserXML:aW	Landroid/widget/EditText;
    //   3297: aload_0
    //   3298: getfield 362	com/feelingk/iap/gui/parser/ParserXML:aW	Landroid/widget/EditText;
    //   3301: aload_0
    //   3302: getfield 410	com/feelingk/iap/gui/parser/ParserXML:bW	Lcom/feelingk/iap/gui/parser/at;
    //   3305: invokevirtual 1663	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3308: goto -2129 -> 1179
    //   3311: astore 6
    //   3313: goto -1735 -> 1578
    //   3316: astore 10
    //   3318: goto -2667 -> 651
    //   3321: astore 11
    //   3323: goto -2619 -> 704
    //   3326: astore 10
    //   3328: goto -1433 -> 1895
    //   3331: astore 10
    //   3333: goto -1387 -> 1946
    //   3336: astore 9
    //   3338: goto -1086 -> 2252
    //   3341: astore 10
    //   3343: goto -1044 -> 2299
    //   3346: astore 11
    //   3348: goto -998 -> 2350
    //   3351: astore 6
    //   3353: goto -2105 -> 1248
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3356	0	this	ParserXML
    //   0	3356	1	paramXmlPullParser	XmlPullParser
    //   559	1979	2	i1	int
    //   1277	38	3	i2	int
    //   9	3284	4	localObject1	Object
    //   15	2496	5	localAttributeSet	AttributeSet
    //   6	3275	6	localObject2	Object
    //   3311	1	6	localIOException1	IOException
    //   3351	1	6	localIOException2	IOException
    //   383	2483	7	localObject3	Object
    //   393	2205	8	localObject4	Object
    //   403	2124	9	localObject5	Object
    //   3336	1	9	localIOException3	IOException
    //   413	1991	10	localObject6	Object
    //   3316	1	10	localIOException4	IOException
    //   3326	1	10	localIOException5	IOException
    //   3331	1	10	localIOException6	IOException
    //   3341	1	10	localIOException7	IOException
    //   687	1881	11	localObject7	Object
    //   3321	1	11	localIOException8	IOException
    //   3346	1	11	localIOException9	IOException
    // Exception table:
    //   from	to	target	type
    //   1573	1578	3311	java/io/IOException
    //   646	651	3316	java/io/IOException
    //   699	704	3321	java/io/IOException
    //   1890	1895	3326	java/io/IOException
    //   1941	1946	3331	java/io/IOException
    //   2247	2252	3336	java/io/IOException
    //   2294	2299	3341	java/io/IOException
    //   2345	2350	3346	java/io/IOException
    //   1243	1248	3351	java/io/IOException
  }
  
  private String i()
  {
    return this.aj;
  }
  
  private static int j(String paramString)
  {
    try
    {
      float f1 = Float.parseFloat(paramString.substring(0, paramString.length() - 2));
      return (int)(float)(f1 / 1.5D);
    }
    catch (NumberFormatException paramString) {}
    return -1;
  }
  
  private View j(XmlPullParser paramXmlPullParser)
  {
    Object localObject1 = paramXmlPullParser.getName();
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject1).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.U);
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
        paramXmlPullParser = new TextView(this.U);
        break;
      }
      if (((String)localObject1).equals("Button"))
      {
        paramXmlPullParser = new Button(this.U);
        break;
      }
      if (((String)localObject1).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.U);
        break;
      }
      if (((String)localObject1).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.U);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      if (((String)localObject1).equals("CheckBox"))
      {
        paramXmlPullParser = new CheckBox(this.U);
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
          localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
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
            i1 = k((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i1, i1, i1, i1);
          }
          localObject2 = a(localAttributeSet, "a:paddingleft");
          localObject3 = a(localAttributeSet, "a:paddingTop");
          localObject4 = a(localAttributeSet, "a:paddingRight");
          localObject5 = a(localAttributeSet, "a:paddingBottom");
          if (localObject2 == null) {
            break label1615;
          }
          i1 = k((String)localObject2);
          if (localObject3 == null) {
            break label1610;
          }
          i2 = k((String)localObject3);
          if (localObject4 == null) {
            break label1604;
          }
          i3 = k((String)localObject4);
          if (localObject5 == null) {
            break label1598;
          }
          i4 = k((String)localObject5);
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
              localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
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
              this.c = true;
              i1 = 1;
              if (i1 == 0)
              {
                localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
                localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
              }
            }
          }
          catch (IOException localIOException5)
          {
            try
            {
              ((InputStream)localObject3).close();
              localObject4 = getClass().getResourceAsStream(this.aj + "checkbox_y" + ".png");
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
                ((CheckBox)localObject1).setOnCheckedChangeListener(this.C);
                localObject1 = paramXmlPullParser;
                if (this.R.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.R.peek()));
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
                  localObject4 = getClass().getResourceAsStream(this.aj + (String)localObject3 + ".png");
                  localObject3 = Drawable.createFromStream((InputStream)localObject4, (String)localObject3);
                }
              }
              catch (IOException localIOException5)
              {
                try
                {
                  ((InputStream)localObject4).close();
                  localObject5 = getClass().getResourceAsStream(this.aj + (String)localObject1 + ".png");
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
                      ((Button)localObject2).setOnClickListener(this.x);
                      continue;
                    }
                    if (!((String)localObject1).equals("btn_buycancel_sel")) {
                      continue;
                    }
                    ((Button)localObject2).setOnClickListener(this.y);
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
                      ((TextView)localObject1).setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.S));
                    }
                    if (localObject4 != null)
                    {
                      ((String)localObject4).replace("\\n", "\n");
                      ((TextView)localObject1).setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.f));
                    }
                    if (localObject5 != null) {
                      ((TextView)localObject1).setText((CharSequence)localObject5);
                    }
                    if (str1 != null) {
                      ((TextView)localObject1).setTextSize(1, j(str1));
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
  
  private int k(String paramString)
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
  
  private View k(XmlPullParser paramXmlPullParser)
  {
    Object localObject1 = paramXmlPullParser.getName();
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject1).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.U);
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
        paramXmlPullParser = new TextView(this.U);
        break;
      }
      if (((String)localObject1).equals("Button"))
      {
        paramXmlPullParser = new Button(this.U);
        break;
      }
      if (((String)localObject1).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.U);
        break;
      }
      if (((String)localObject1).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.U);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      if (((String)localObject1).equals("CheckBox"))
      {
        paramXmlPullParser = new CheckBox(this.U);
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
          localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
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
            i1 = k((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i1, i1, i1, i1);
          }
          localObject2 = a(localAttributeSet, "a:paddingleft");
          localObject3 = a(localAttributeSet, "a:paddingTop");
          localObject4 = a(localAttributeSet, "a:paddingRight");
          localObject5 = a(localAttributeSet, "a:paddingBottom");
          if (localObject2 == null) {
            break label1817;
          }
          i1 = k((String)localObject2);
          if (localObject3 == null) {
            break label1812;
          }
          i2 = k((String)localObject3);
          if (localObject4 == null) {
            break label1806;
          }
          i3 = k((String)localObject4);
          if (localObject5 == null) {
            break label1800;
          }
          i4 = k((String)localObject5);
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
              localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
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
              this.c = true;
              i1 = 1;
              if (i1 == 0)
              {
                localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
                localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
              }
            }
          }
          catch (IOException localIOException7)
          {
            try
            {
              ((InputStream)localObject3).close();
              localObject4 = getClass().getResourceAsStream(this.aj + "checkbox_y" + ".png");
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
                ((CheckBox)localObject1).setOnCheckedChangeListener(this.C);
                localObject1 = paramXmlPullParser;
                if (this.R.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.R.peek()));
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
                    this.bt = ((Button)localObject2);
                  }
                  localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject4 + ".png");
                  localObject4 = Drawable.createFromStream((InputStream)localObject3, (String)localObject4);
                }
              }
              catch (IOException localIOException7)
              {
                try
                {
                  ((InputStream)localObject3).close();
                  localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject1 + ".png");
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
                      this.bq = ((StateListDrawable)localObject3);
                      this.bp = new StateListDrawable();
                      localObject5 = getClass().getResourceAsStream(this.aj + "btn_con_nor.png");
                      localObject4 = Drawable.createFromStream((InputStream)localObject5, "btn_con_nor");
                    }
                  }
                  catch (IOException localIOException7)
                  {
                    try
                    {
                      ((InputStream)localObject5).close();
                      localObject6 = getClass().getResourceAsStream(this.aj + "btn_con_sel.png");
                      localObject5 = Drawable.createFromStream((InputStream)localObject6, "btn_con_sel");
                    }
                    catch (IOException localIOException7)
                    {
                      try
                      {
                        ((InputStream)localObject6).close();
                        this.bp.addState(new int[] { 16842919 }, (Drawable)localObject5);
                        this.bp.addState(new int[0], (Drawable)localObject4);
                        if (this.aH[0] != 0)
                        {
                          this.bt.setBackgroundDrawable(this.bp);
                          this.bt.setOnClickListener(this.z);
                          if (!((String)localObject1).equals("btn_cancel_sel_h")) {
                            continue;
                          }
                          ((Button)localObject2).setOnClickListener(this.A);
                          continue;
                        }
                        this.bt.setBackgroundDrawable((Drawable)localObject3);
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
                          ((TextView)localObject1).setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.T));
                        }
                        if (localObject4 != null)
                        {
                          ((String)localObject4).replace("\\n", "\n");
                          ((TextView)localObject1).setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.g));
                        }
                        if (localObject5 != null) {
                          ((TextView)localObject1).setText((CharSequence)localObject5);
                        }
                        if (localObject6 != null) {
                          ((TextView)localObject1).setTextSize(1, j((String)localObject6));
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
      paramXmlPullParser = new LinearLayout(this.U);
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
        paramXmlPullParser = new TextView(this.U);
        break;
      }
      if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.U);
        break;
      }
      if (((String)localObject2).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.U);
        break;
      }
      if (((String)localObject2).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.U);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      if (((String)localObject2).equals("CheckBox"))
      {
        paramXmlPullParser = new CheckBox(this.U);
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
          localObject3 = getClass().getResourceAsStream(this.aj + "line_dot_01.png");
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
            i1 = k((String)localObject2);
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
            i1 = k((String)localObject2);
          }
          if (localObject3 != null) {
            i2 = k((String)localObject3);
          }
          if (localObject4 != null) {
            i3 = k((String)localObject4);
          }
          if (localObject5 != null) {
            i4 = k((String)localObject5);
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
              localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
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
              ((TextView)localObject3).setTextSize(1, j((String)localObject5));
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
              this.c = true;
            }
            if (i1 == 0)
            {
              localObject2 = a(localAttributeSet, "a:onImage");
              localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
              localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
            }
          }
        }
        catch (IOException localIOException6)
        {
          try
          {
            ((InputStream)localObject3).close();
            localObject4 = getClass().getResourceAsStream(this.aj + "checkbox_y" + ".png");
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
              ((CheckBox)localObject1).setOnCheckedChangeListener(this.C);
              label1207:
              label1227:
              label1328:
              do
              {
                do
                {
                  localObject1 = paramXmlPullParser;
                  if (this.R.size() <= 0) {
                    break;
                  }
                  paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.R.peek()));
                  return paramXmlPullParser;
                  if (!((String)localObject2).equals("vertical")) {
                    break label257;
                  }
                  ((LinearLayout)localObject1).setOrientation(1);
                  break label257;
                  localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
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
              localObject4 = getClass().getResourceAsStream(this.aj + (String)localObject3 + ".png");
              localObject3 = Drawable.createFromStream((InputStream)localObject4, (String)localObject3);
            }
            catch (IOException localIOException6)
            {
              try
              {
                ((InputStream)localObject4).close();
                localObject5 = getClass().getResourceAsStream(this.aj + (String)localObject1 + ".png");
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
                      localButton.setOnClickListener(this.G);
                    }
                    else if (((String)localObject1).equals("btn_cancel_sel_h"))
                    {
                      localButton.setOnClickListener(this.H);
                    }
                    else if (((String)localObject1).equals("btn_terms_sel_b"))
                    {
                      localButton.setOnClickListener(this.D);
                    }
                    else if (((String)localObject1).equals("btn_terms_sel_b2"))
                    {
                      localButton.setOnClickListener(this.E);
                    }
                    else if (((String)localObject1).equals("btn_policy_sel_b"))
                    {
                      localButton.setOnClickListener(this.F);
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
      paramXmlPullParser = new LinearLayout(this.U);
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
        paramXmlPullParser = new TextView(this.U);
        break;
      }
      if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.U);
        break;
      }
      if (((String)localObject2).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.U);
        break;
      }
      if (((String)localObject2).equals("EditText"))
      {
        paramXmlPullParser = new EditText(this.U);
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
            break label1075;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = a(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
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
            i1 = k((String)localObject2);
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
              localObject3 = getClass().getResourceAsStream(this.aj + (String)localObject2 + ".png");
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
                ((TextView)localObject1).setText(i2);
                this.aY = String.valueOf(i2);
              }
            }
            else
            {
              if (localObject4 != null) {
                ((TextView)localObject1).setText(((String)localObject4).replace("\\n", "\n"));
              }
              if (localObject5 != null) {
                ((TextView)localObject1).setTextSize(1, j((String)localObject5));
              }
              if (str1 != null) {
                ((TextView)localObject1).setTextColor(Color.parseColor(str1));
              }
              if (str2 == null) {
                break label1124;
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
            localObject4 = getClass().getResourceAsStream(this.aj + (String)localObject3 + ".png");
            localObject3 = Drawable.createFromStream((InputStream)localObject4, (String)localObject3);
          }
        }
        catch (IOException localIOException3)
        {
          try
          {
            ((InputStream)localObject4).close();
            localObject5 = getClass().getResourceAsStream(this.aj + (String)localObject1 + ".png");
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
                  ((Button)localObject2).setOnClickListener(this.N);
                }
                if ((paramXmlPullParser instanceof EditText))
                {
                  localObject1 = (EditText)paramXmlPullParser;
                  ((EditText)localObject1).setHint(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.X));
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
                  ((EditText)localObject1).addTextChangedListener(new ak(this, (EditText)localObject1));
                  ((EditText)localObject1).setOnClickListener(new al(this, (EditText)localObject1));
                }
                localObject1 = paramXmlPullParser;
                if (this.R.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.R.peek()));
                return paramXmlPullParser;
                label1075:
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
                label1124:
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
      paramXmlPullParser = new LinearLayout(this.U);
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
        paramXmlPullParser = new TextView(this.U);
        break;
      }
      if (str.equals("Button"))
      {
        paramXmlPullParser = new Button(this.U);
        break;
      }
      if (str.equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.U);
        break;
      }
      if (str.equals("EditText"))
      {
        paramXmlPullParser = new EditText(this.U);
        ((EditText)paramXmlPullParser).setImeOptions(268435456);
        break;
      }
      if (str.equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.U);
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
          localObject2 = getClass().getResourceAsStream(this.aj + str + ".png");
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
            i1 = k(str);
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
              localObject2 = getClass().getResourceAsStream(this.aj + str + ".png");
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
            bv = (EditText)localObject1;
            if (com.feelingk.iap.an.f() != null)
            {
              ((EditText)localObject1).setText(com.feelingk.iap.an.f());
              ((EditText)localObject1).setTextColor(Color.parseColor("#FF6F00"));
              ((EditText)localObject1).setTextSize(1, 30.0F);
              bw.setBackgroundDrawable(bl);
              label589:
              bv.addTextChangedListener(new am(this));
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
                ((TextView)localObject1).setTextSize(1, j((String)localObject2));
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
              if (com.feelingk.iap.an.f() != null)
              {
                bv.setText(com.feelingk.iap.an.f());
                bv.setTextColor(Color.parseColor("#FF6F00"));
                bv.setTextSize(1, 30.0F);
                bw.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.V));
                bw.setTextColor(Color.parseColor("#999999"));
              }
            }
            if ((paramXmlPullParser instanceof Button))
            {
              localObject1 = a(localAttributeSet, "a:offImage");
              str = a(localAttributeSet, "a:onImage");
              localObject2 = a(localAttributeSet, "a:id");
              this.br = ((Button)paramXmlPullParser);
              localObject3 = a(localAttributeSet, "a:padding");
              if (localObject3 != null)
              {
                i1 = k((String)localObject3);
                this.br.setPadding(i1, i1, i1, i1);
              }
              if (localObject2 != null)
              {
                if (((String)localObject2).equals("lgu_smsAuth_btn")) {
                  this.br.setPadding(0, 0, 0, 0);
                }
                this.br.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.W));
              }
              if (((String)localObject1).equals("bt_01_nor.9"))
              {
                localObject3 = this.br;
                bw = (Button)localObject3;
                ((Button)localObject3).setPadding(0, 0, 0, 0);
                bw.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.Z));
              }
              if (((String)localObject1).equals("bt_confirm_dim")) {
                bx = this.br;
              }
              this.d = getClass().getResourceAsStream(this.aj + (String)localObject1 + ".png");
              this.j = Drawable.createFromStream(this.d, (String)localObject1);
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
            this.d.close();
            this.d = getClass().getResourceAsStream(this.aj + str + ".png");
            this.k = Drawable.createFromStream(this.d, str);
            try
            {
              this.d.close();
              this.aZ = new StateListDrawable();
              localObject3 = this.aZ;
              localObject4 = this.k;
              ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject4);
              localObject3 = this.aZ;
              localObject4 = this.j;
              ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
              this.f = getClass().getResourceAsStream(this.aj + "bt_01_reclaim_nor" + ".png");
              this.l = Drawable.createFromStream(this.f, "bt_01_reclaim_nor");
              try
              {
                this.f.close();
                this.f = getClass().getResourceAsStream(this.aj + "bt_01_reclaim_sel" + ".png");
                this.m = Drawable.createFromStream(this.f, "bt_01_reclaim_sel");
                try
                {
                  this.f.close();
                  localObject3 = new StateListDrawable();
                  bl = (StateListDrawable)localObject3;
                  localObject4 = this.m;
                  ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject4);
                  localObject3 = bl;
                  localObject4 = this.l;
                  ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
                  this.g = getClass().getResourceAsStream(this.aj + "btn_con_nor" + ".png");
                  this.n = Drawable.createFromStream(this.g, "btn_con_nor");
                  try
                  {
                    this.g.close();
                    this.g = getClass().getResourceAsStream(this.aj + "btn_con_sel" + ".png");
                    this.o = Drawable.createFromStream(this.g, "btn_con_sel");
                    try
                    {
                      this.g.close();
                      localObject3 = new StateListDrawable();
                      bm = (StateListDrawable)localObject3;
                      localObject4 = this.o;
                      ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject4);
                      localObject3 = bm;
                      localObject4 = this.n;
                      ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
                      this.br.setBackgroundDrawable(this.aZ);
                      if ((by.booleanValue()) && (((String)localObject1).equals("bt_01_sel.9")))
                      {
                        this.br.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.V));
                        this.br.setTextColor(Color.parseColor("#999999"));
                      }
                      if (com.feelingk.iap.an.f() != null)
                      {
                        bw.setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.V));
                        bw.setTextColor(Color.parseColor("#999999"));
                        bx.setBackgroundDrawable(bm);
                        bx.setOnClickListener(P);
                      }
                      if ((localObject2 != null) && (((String)localObject2).equals("lgu_smsAuth_btn"))) {
                        this.br.setPadding(0, 0, 0, 0);
                      }
                      if (str.equals("bt_01_sel.9")) {
                        this.br.setOnClickListener(this.O);
                      }
                      label1714:
                      label1757:
                      label1816:
                      do
                      {
                        for (;;)
                        {
                          localObject1 = paramXmlPullParser;
                          if (this.R.size() <= 0) {
                            break;
                          }
                          paramXmlPullParser.setLayoutParams(a(localAttributeSet, (ViewGroup)this.R.peek()));
                          return paramXmlPullParser;
                          if (!str.equals("vertical")) {
                            break label267;
                          }
                          ((LinearLayout)localObject1).setOrientation(1);
                          break label267;
                          ((LinearLayout)localObject1).setGravity(5);
                          break label388;
                          ((EditText)localObject1).setText(com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.U));
                          break label589;
                          ((TextView)localObject1).setGravity(19);
                          break label739;
                          if (str.equals("btn_con_sel"))
                          {
                            this.br.setOnClickListener(P);
                          }
                          else
                          {
                            if (!str.equals("btn_cancel_sel_h")) {
                              break label1816;
                            }
                            this.br.setOnClickListener(this.Q);
                          }
                        }
                      } while (!str.equals("btn_locking_sel"));
                      localObject1 = this.U.getPackageManager().getInstalledApplications(0);
                      int i2 = ((List)localObject1).size();
                      i1 = 0;
                      for (;;)
                      {
                        if (i1 >= i2)
                        {
                          this.br.setOnClickListener(this.J);
                          break;
                        }
                        if (((ApplicationInfo)((List)localObject1).get(i1)).packageName.indexOf("com.skt.skaf.A000Z00040") == 0) {
                          this.aE = true;
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
  
  public final View a(int paramInt, Object paramObject)
  {
    this.ai = paramInt;
    Log.e("ParserXML", "# purchase Start !! GUI-rotate =" + paramInt);
    this.aq = ((com.feelingk.iap.gui.a.a)paramObject);
    if (this.aq.l) {
      if ((this.ai == 0) || (this.ai == 2)) {
        paramObject = h() + "/" + this.am + "W_Auto.xml";
      }
    }
    for (;;)
    {
      return e(paramObject);
      paramObject = h() + "/" + this.am + "H_Auto.xml";
      continue;
      if ((this.ai == 0) || (this.ai == 2)) {
        paramObject = h() + "/" + this.am + "_w.xml";
      } else {
        paramObject = h() + "/" + this.am + "_h.xml";
      }
    }
  }
  
  public final View a(String paramString, Object paramObject, int paramInt)
  {
    this.ap = ((View.OnClickListener)paramObject);
    this.ai = paramInt;
    Log.e("ParserXML", "# IMEIAuthDialog Start !! GUI-rotate =" + paramInt);
    if ((this.ai == 0) || (this.ai == 2)) {}
    for (paramString = paramString + "w.xml";; paramString = paramString + "h.xml") {
      return e(paramString);
    }
  }
  
  public final View a(String paramString1, String paramString2, Object paramObject)
  {
    this.ao = paramString2;
    this.ap = ((View.OnClickListener)paramObject);
    return e(paramString1);
  }
  
  public final View a(String paramString1, String paramString2, Object paramObject, int paramInt)
  {
    this.ao = paramString2;
    this.ap = ((View.OnClickListener)paramObject);
    this.ai = paramInt;
    Log.e("ParserXML", "# Dlg Start !! GUI-rotate =" + paramInt);
    if ((this.ai == 0) || (this.ai == 2)) {}
    for (paramString1 = paramString1 + "w.xml";; paramString1 = paramString1 + "h.xml") {
      return e(paramString1);
    }
  }
  
  public final View a(String paramString1, String paramString2, String paramString3, Object paramObject, int paramInt)
  {
    this.an = paramString2;
    this.ao = paramString3;
    this.ap = ((View.OnClickListener)paramObject);
    this.ai = paramInt;
    Log.e("ParserXML", "# AutoPurchaseForm용 Start !! GUI-rotate =" + paramInt);
    if ((this.ai == 0) || (this.ai == 2)) {}
    for (paramString1 = paramString1 + "W.xml";; paramString1 = paramString1 + "H.xml") {
      return e(paramString1);
    }
  }
  
  public final void a(Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue())
    {
      int i1 = this.aq.c - this.aq.d;
      if (i1 > 0)
      {
        paramBoolean = new DecimalFormat("###,###,###").format(i1);
        this.aI.setText(paramBoolean + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
        return;
      }
      paramBoolean = new DecimalFormat("###,###,###").format(this.aq.c);
      this.aI.setText(paramBoolean + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K) + "-" + paramBoolean + "P=0" + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
      return;
    }
    paramBoolean = new DecimalFormat("###,###,###").format(this.aq.c);
    this.aI.setText(paramBoolean + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
  }
  
  public final View b(String paramString, Object paramObject, int paramInt)
  {
    this.ap = ((View.OnClickListener)paramObject);
    this.ai = paramInt;
    Log.e("ParserXML", "# Dlg Start !! GUI-rotate =" + paramInt);
    if ((this.ai == 0) || (this.ai == 2)) {}
    for (paramString = paramString + "w.xml";; paramString = paramString + "h.xml") {
      return e(paramString);
    }
  }
  
  public final void b(String paramString)
  {
    Log.i("ParserXML", "add_payments_amount: " + paramString);
    int i2;
    int i1;
    if (paramString.equals("OCB"))
    {
      i2 = Integer.parseInt(com.feelingk.iap.an.n());
      i1 = com.feelingk.iap.gui.a.b.a().d() - (i2 - i2 % 10);
      if (i1 > 0)
      {
        com.feelingk.iap.gui.a.b.a().b(i1);
        com.feelingk.iap.gui.a.b.a().d(i2);
        com.feelingk.iap.gui.a.b.a().d(com.feelingk.iap.gui.a.b.a().f() - com.feelingk.iap.gui.a.b.a().f() % 10);
        paramString = com.feelingk.iap.gui.a.b.a();
        if (com.feelingk.iap.gui.a.b.a().f() <= 0)
        {
          i1 = 0;
          paramString.d(i1);
          this.bH.setText(i2 - com.feelingk.iap.gui.a.b.a().f() + " P");
          this.aI.setText(String.valueOf(com.feelingk.iap.gui.a.b.a().d()) + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
          label186:
          com.feelingk.iap.gui.a.b.a().c(com.feelingk.iap.gui.a.b.a().f() + com.feelingk.iap.gui.a.b.a().g() + com.feelingk.iap.gui.a.b.a().h() + com.feelingk.iap.gui.a.b.a().i());
          this.aJ.setText(com.feelingk.iap.gui.a.b.a().e() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
          if ((com.feelingk.iap.gui.a.b.a().e() != com.feelingk.iap.gui.a.b.a().c()) || (!com.feelingk.iap.an.l())) {
            break label2518;
          }
          this.bs.setBackgroundDrawable(this.ba);
          this.p = false;
          this.bs.setOnClickListener(this.I);
        }
      }
    }
    label741:
    label778:
    label936:
    label946:
    label2518:
    while ((com.feelingk.iap.gui.a.b.a().e() >= com.feelingk.iap.gui.a.b.a().c()) || (!com.feelingk.iap.an.l()))
    {
      return;
      i1 = com.feelingk.iap.gui.a.b.a().f();
      break;
      com.feelingk.iap.gui.a.b.a().d(com.feelingk.iap.gui.a.b.a().d());
      com.feelingk.iap.gui.a.b.a().d(com.feelingk.iap.gui.a.b.a().f() - com.feelingk.iap.gui.a.b.a().f() % 10);
      paramString = com.feelingk.iap.gui.a.b.a();
      if (com.feelingk.iap.gui.a.b.a().f() <= 0) {}
      for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().f())
      {
        paramString.d(i1);
        Log.i("ParserXML", "OCB 사용액: " + com.feelingk.iap.gui.a.b.a().f() + " P");
        com.feelingk.iap.gui.a.b.a().b(0);
        this.aI.setText(com.feelingk.iap.gui.a.b.a().f() % 10 + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
        this.bH.setText(i2 - com.feelingk.iap.gui.a.b.a().f() + " P");
        if (com.feelingk.iap.gui.a.b.a().g() == 0)
        {
          this.bB.setBackgroundDrawable(this.bd);
          this.bB.setTextColor(Color.parseColor("#8B8B8B"));
          this.bB.setClickable(false);
          this.bB.setPadding(0, 0, 0, 0);
        }
        if (com.feelingk.iap.gui.a.b.a().h() == 0)
        {
          this.bC.setBackgroundDrawable(this.bf);
          this.bC.setTextColor(Color.parseColor("#8B8B8B"));
          this.bC.setClickable(false);
          this.bC.setPadding(0, 0, 0, 0);
        }
        if (com.feelingk.iap.gui.a.b.a().i() != 0) {
          break;
        }
        this.bD.setBackgroundDrawable(this.bh);
        this.bD.setTextColor(Color.parseColor("#8B8B8B"));
        this.bD.setClickable(false);
        this.bD.setPadding(0, 0, 0, 0);
        break;
      }
      if (paramString.equals("DOTORI"))
      {
        i1 = com.feelingk.iap.gui.a.b.a().d() - (this.aq.i - this.aq.i % 100);
        if (i1 > 0)
        {
          if ((com.feelingk.iap.gui.a.b.a().d() > 100000) && (this.aq.i > 100000))
          {
            com.feelingk.iap.gui.a.b.a().b(com.feelingk.iap.gui.a.b.a().d() - 100000);
            com.feelingk.iap.gui.a.b.a().e(100000);
            i1 = 1;
            com.feelingk.iap.gui.a.b.a().e(com.feelingk.iap.gui.a.b.a().g() - com.feelingk.iap.gui.a.b.a().g() % 100);
            paramString = com.feelingk.iap.gui.a.b.a();
            if (com.feelingk.iap.gui.a.b.a().g() > 0) {
              break label936;
            }
            i2 = 0;
            paramString.e(i2);
            if (i1 == 0) {
              break label946;
            }
            this.bI.setText((this.aq.i - com.feelingk.iap.gui.a.b.a().g()) / 100 + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.A));
          }
          for (;;)
          {
            this.aI.setText(String.valueOf(com.feelingk.iap.gui.a.b.a().d()) + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
            Log.i("ParserXML", "도토리 사용액: " + com.feelingk.iap.gui.a.b.a().g() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
            break;
            com.feelingk.iap.gui.a.b.a().b(i1);
            com.feelingk.iap.gui.a.b.a().e(this.aq.i);
            i1 = 0;
            break label741;
            i2 = com.feelingk.iap.gui.a.b.a().g();
            break label778;
            this.bI.setText("0" + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.A));
          }
        }
        if ((com.feelingk.iap.gui.a.b.a().d() > 100000) && (this.aq.i > 100000))
        {
          com.feelingk.iap.gui.a.b.a().e(100000);
          com.feelingk.iap.gui.a.b.a().e(com.feelingk.iap.gui.a.b.a().g() - com.feelingk.iap.gui.a.b.a().g() % 100);
          paramString = com.feelingk.iap.gui.a.b.a();
          if (com.feelingk.iap.gui.a.b.a().g() <= 0) {}
          for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().g())
          {
            paramString.e(i1);
            Log.i("ParserXML", "도토리 사용액: " + com.feelingk.iap.gui.a.b.a().g() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
            com.feelingk.iap.gui.a.b.a().e(com.feelingk.iap.gui.a.b.a().g() / 100 * 100);
            this.aI.setText(com.feelingk.iap.gui.a.b.a().d() - com.feelingk.iap.gui.a.b.a().g() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
            com.feelingk.iap.gui.a.b.a().b(com.feelingk.iap.gui.a.b.a().d() - com.feelingk.iap.gui.a.b.a().g());
            i1 = (this.aq.i - com.feelingk.iap.gui.a.b.a().g()) / 100;
            this.bI.setText(i1 + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.A));
            if (com.feelingk.iap.gui.a.b.a().d() >= 10) {
              break;
            }
            if (com.feelingk.iap.gui.a.b.a().f() == 0)
            {
              this.bA.setBackgroundDrawable(this.bb);
              this.bA.setTextColor(Color.parseColor("#8B8B8B"));
              this.bA.setClickable(false);
              this.bA.setPadding(0, 0, 0, 0);
            }
            if (com.feelingk.iap.gui.a.b.a().h() == 0)
            {
              this.bC.setBackgroundDrawable(this.bf);
              this.bC.setTextColor(Color.parseColor("#8B8B8B"));
              this.bC.setClickable(false);
              this.bC.setPadding(0, 0, 0, 0);
            }
            if (com.feelingk.iap.gui.a.b.a().i() != 0) {
              break;
            }
            this.bD.setBackgroundDrawable(this.bh);
            this.bD.setTextColor(Color.parseColor("#8B8B8B"));
            this.bD.setClickable(false);
            this.bD.setPadding(0, 0, 0, 0);
            break;
          }
        }
        com.feelingk.iap.gui.a.b.a().e(com.feelingk.iap.gui.a.b.a().d());
        com.feelingk.iap.gui.a.b.a().e(com.feelingk.iap.gui.a.b.a().g() - com.feelingk.iap.gui.a.b.a().g() % 100);
        paramString = com.feelingk.iap.gui.a.b.a();
        if (com.feelingk.iap.gui.a.b.a().g() <= 0) {}
        for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().g())
        {
          paramString.e(i1);
          break;
        }
      }
      if (paramString.equals("CULTURE"))
      {
        i2 = Integer.parseInt(com.feelingk.iap.an.p());
        i1 = com.feelingk.iap.gui.a.b.a().d() - (i2 - i2 % 10);
        if (i1 > 0)
        {
          com.feelingk.iap.gui.a.b.a().b(i1);
          com.feelingk.iap.gui.a.b.a().f(i2);
          com.feelingk.iap.gui.a.b.a().f(com.feelingk.iap.gui.a.b.a().h() - com.feelingk.iap.gui.a.b.a().h() % 10);
          paramString = com.feelingk.iap.gui.a.b.a();
          if (com.feelingk.iap.gui.a.b.a().h() <= 0) {}
          for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().h())
          {
            paramString.f(i1);
            this.bJ.setText(i2 - com.feelingk.iap.gui.a.b.a().h() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
            this.aI.setText(String.valueOf(com.feelingk.iap.gui.a.b.a().d()) + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
            break;
          }
        }
        com.feelingk.iap.gui.a.b.a().f(com.feelingk.iap.gui.a.b.a().d());
        com.feelingk.iap.gui.a.b.a().f(com.feelingk.iap.gui.a.b.a().h() - com.feelingk.iap.gui.a.b.a().h() % 10);
        paramString = com.feelingk.iap.gui.a.b.a();
        if (com.feelingk.iap.gui.a.b.a().h() <= 0) {}
        for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().h())
        {
          paramString.f(i1);
          Log.i("ParserXML", "CULTURE 사용액: " + com.feelingk.iap.gui.a.b.a().h() + " P");
          com.feelingk.iap.gui.a.b.a().b(0);
          this.aI.setText(com.feelingk.iap.gui.a.b.a().h() % 10 + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
          this.bJ.setText(i2 - com.feelingk.iap.gui.a.b.a().h() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
          if (com.feelingk.iap.gui.a.b.a().f() == 0)
          {
            this.bA.setBackgroundDrawable(this.bb);
            this.bA.setTextColor(Color.parseColor("#8B8B8B"));
            this.bA.setClickable(false);
            this.bA.setPadding(0, 0, 0, 0);
          }
          if (com.feelingk.iap.gui.a.b.a().g() == 0)
          {
            this.bB.setBackgroundDrawable(this.bd);
            this.bB.setTextColor(Color.parseColor("#8B8B8B"));
            this.bB.setClickable(false);
            this.bB.setPadding(0, 0, 0, 0);
          }
          if (com.feelingk.iap.gui.a.b.a().i() != 0) {
            break;
          }
          this.bD.setBackgroundDrawable(this.bh);
          this.bD.setTextColor(Color.parseColor("#8B8B8B"));
          this.bD.setClickable(false);
          this.bD.setPadding(0, 0, 0, 0);
          break;
        }
      }
      if (!paramString.equals("TCASH")) {
        break label186;
      }
      i1 = com.feelingk.iap.gui.a.b.a().d() - (this.aq.d - this.aq.d % 10);
      if (i1 > 0)
      {
        com.feelingk.iap.gui.a.b.a().b(i1);
        com.feelingk.iap.gui.a.b.a().g(this.aq.d);
        com.feelingk.iap.gui.a.b.a().g(com.feelingk.iap.gui.a.b.a().i() - com.feelingk.iap.gui.a.b.a().i() % 10);
        paramString = com.feelingk.iap.gui.a.b.a();
        if (com.feelingk.iap.gui.a.b.a().i() <= 0) {}
        for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().i())
        {
          paramString.g(i1);
          this.bK.setText(this.aq.d - com.feelingk.iap.gui.a.b.a().i() + " P");
          this.aI.setText(String.valueOf(com.feelingk.iap.gui.a.b.a().d()) + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
          break;
        }
      }
      com.feelingk.iap.gui.a.b.a().g(com.feelingk.iap.gui.a.b.a().d());
      com.feelingk.iap.gui.a.b.a().g(com.feelingk.iap.gui.a.b.a().i() - com.feelingk.iap.gui.a.b.a().i() % 10);
      paramString = com.feelingk.iap.gui.a.b.a();
      if (com.feelingk.iap.gui.a.b.a().i() <= 0) {}
      for (i1 = 0;; i1 = com.feelingk.iap.gui.a.b.a().i())
      {
        paramString.g(i1);
        Log.i("ParserXML", "티캐쉬 사용액: " + com.feelingk.iap.gui.a.b.a().i() + "P");
        com.feelingk.iap.gui.a.b.a().b(0);
        this.aI.setText(com.feelingk.iap.gui.a.b.a().i() % 10 + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
        this.bK.setText(this.aq.d - com.feelingk.iap.gui.a.b.a().i() + " P");
        if (com.feelingk.iap.gui.a.b.a().f() == 0)
        {
          this.bA.setBackgroundDrawable(this.bb);
          this.bA.setTextColor(Color.parseColor("#8B8B8B"));
          this.bA.setClickable(false);
          this.bA.setPadding(0, 0, 0, 0);
        }
        if (com.feelingk.iap.gui.a.b.a().g() == 0)
        {
          this.bB.setBackgroundDrawable(this.bd);
          this.bB.setTextColor(Color.parseColor("#8B8B8B"));
          this.bB.setClickable(false);
          this.bB.setPadding(0, 0, 0, 0);
        }
        if (com.feelingk.iap.gui.a.b.a().h() != 0) {
          break;
        }
        this.bC.setBackgroundDrawable(this.bf);
        this.bC.setTextColor(Color.parseColor("#8B8B8B"));
        this.bC.setClickable(false);
        this.bC.setPadding(0, 0, 0, 0);
        break;
      }
    }
    this.bs.setBackgroundDrawable(this.bn);
    this.p = true;
    this.bs.setOnClickListener(null);
  }
  
  public final void c(String paramString)
  {
    Log.i("ParserXML", "del_payments_amount: " + paramString);
    if (paramString.equals("OCB"))
    {
      com.feelingk.iap.gui.a.b.a().b(com.feelingk.iap.gui.a.b.a().d() + com.feelingk.iap.gui.a.b.a().f());
      com.feelingk.iap.gui.a.b.a().d(0);
      this.aI.setText(com.feelingk.iap.gui.a.b.a().d() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
      this.bH.setText(com.feelingk.iap.an.n() + " P");
      if (this.aq.i != 0)
      {
        this.bB.setBackgroundDrawable(this.be);
        this.bB.setTextColor(Color.parseColor("#CFCFCF"));
        this.bB.setClickable(true);
        this.bB.setPadding(0, 0, 0, 0);
      }
      if (!com.feelingk.iap.an.p().equals("0"))
      {
        this.bC.setBackgroundDrawable(this.bg);
        this.bC.setTextColor(Color.parseColor("#CFCFCF"));
        this.bC.setClickable(true);
        this.bC.setPadding(0, 0, 0, 0);
      }
      if (this.aq.d > 0)
      {
        this.bD.setBackgroundDrawable(this.bi);
        this.bD.setTextColor(Color.parseColor("#CFCFCF"));
        this.bD.setClickable(true);
        this.bD.setPadding(0, 0, 0, 0);
      }
      com.feelingk.iap.gui.a.b.a().c(com.feelingk.iap.gui.a.b.a().f() + com.feelingk.iap.gui.a.b.a().g() + com.feelingk.iap.gui.a.b.a().h() + com.feelingk.iap.gui.a.b.a().i());
      this.aJ.setText(com.feelingk.iap.gui.a.b.a().e() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
      if ((com.feelingk.iap.gui.a.b.a().e() != com.feelingk.iap.gui.a.b.a().c()) || (!com.feelingk.iap.an.l())) {
        break label1180;
      }
      this.bs.setBackgroundDrawable(this.ba);
      this.p = false;
      this.bs.setOnClickListener(this.I);
    }
    label1180:
    while ((com.feelingk.iap.gui.a.b.a().e() >= com.feelingk.iap.gui.a.b.a().c()) || (!com.feelingk.iap.an.l()))
    {
      return;
      if (paramString.equals("DOTORI"))
      {
        com.feelingk.iap.gui.a.b.a().b(com.feelingk.iap.gui.a.b.a().d() + com.feelingk.iap.gui.a.b.a().g());
        com.feelingk.iap.gui.a.b.a().e(0);
        this.aI.setText(com.feelingk.iap.gui.a.b.a().d() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
        int i1 = this.aq.i / 100;
        this.bI.setText(i1 + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.A));
        this.bA.setBackgroundDrawable(this.bc);
        this.bA.setTextColor(Color.parseColor("#CFCFCF"));
        this.bA.setClickable(true);
        this.bA.setPadding(0, 0, 0, 0);
        if (!com.feelingk.iap.an.p().equals("0"))
        {
          this.bC.setBackgroundDrawable(this.bg);
          this.bC.setTextColor(Color.parseColor("#CFCFCF"));
          this.bC.setClickable(true);
          this.bC.setPadding(0, 0, 0, 0);
        }
        if (this.aq.d <= 0) {
          break;
        }
        this.bD.setBackgroundDrawable(this.bi);
        this.bD.setTextColor(Color.parseColor("#CFCFCF"));
        this.bD.setClickable(true);
        this.bD.setPadding(0, 0, 0, 0);
        break;
      }
      if (paramString.equals("CULTURE"))
      {
        com.feelingk.iap.gui.a.b.a().b(com.feelingk.iap.gui.a.b.a().d() + com.feelingk.iap.gui.a.b.a().h());
        com.feelingk.iap.gui.a.b.a().f(0);
        this.aI.setText(com.feelingk.iap.gui.a.b.a().d() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
        this.bJ.setText(com.feelingk.iap.an.p() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
        this.bA.setBackgroundDrawable(this.bc);
        this.bA.setTextColor(Color.parseColor("#CFCFCF"));
        this.bA.setClickable(true);
        this.bA.setPadding(0, 0, 0, 0);
        if (this.aq.i != 0)
        {
          this.bB.setBackgroundDrawable(this.be);
          this.bB.setTextColor(Color.parseColor("#CFCFCF"));
          this.bB.setClickable(true);
          this.bB.setPadding(0, 0, 0, 0);
        }
        if (this.aq.d <= 0) {
          break;
        }
        this.bD.setBackgroundDrawable(this.bi);
        this.bD.setTextColor(Color.parseColor("#CFCFCF"));
        this.bD.setClickable(true);
        this.bD.setPadding(0, 0, 0, 0);
        break;
      }
      if (!paramString.equals("TCASH")) {
        break;
      }
      com.feelingk.iap.gui.a.b.a().b(com.feelingk.iap.gui.a.b.a().d() + com.feelingk.iap.gui.a.b.a().i());
      com.feelingk.iap.gui.a.b.a().g(0);
      this.aI.setText(com.feelingk.iap.gui.a.b.a().d() + com.feelingk.iap.c.c.a(com.feelingk.iap.c.d.K));
      this.bK.setText(this.aq.d + " P");
      this.bA.setBackgroundDrawable(this.bc);
      this.bA.setTextColor(Color.parseColor("#CFCFCF"));
      this.bA.setClickable(true);
      this.bA.setPadding(0, 0, 0, 0);
      if (this.aq.i != 0)
      {
        this.bB.setBackgroundDrawable(this.be);
        this.bB.setTextColor(Color.parseColor("#CFCFCF"));
        this.bB.setClickable(true);
        this.bB.setPadding(0, 0, 0, 0);
      }
      if (com.feelingk.iap.an.p().equals("0")) {
        break;
      }
      this.bC.setBackgroundDrawable(this.bg);
      this.bC.setTextColor(Color.parseColor("#CFCFCF"));
      this.bC.setClickable(true);
      this.bC.setPadding(0, 0, 0, 0);
      break;
    }
    this.bs.setBackgroundDrawable(this.bn);
    this.p = true;
    this.bs.setOnClickListener(null);
  }
}
