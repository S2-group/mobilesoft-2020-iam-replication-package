package com.babybus.plugin.googlead.b;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.babybus.app.App;
import com.babybus.g.j;
import com.babybus.j.aq;
import com.babybus.j.av;
import com.babybus.j.aw;
import com.babybus.j.t;
import com.babybus.j.x;
import com.babybus.plugin.googlead.b.a.b;
import com.babybus.plugin.googlead.b.a.c;
import com.babybus.plugin.googlead.b.a.d;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  public static String jdField_for;
  public static String jdField_int;
  private static final a jdField_try = new a();
  private String jdField_byte;
  private String jdField_case;
  private int jdField_char;
  public String jdField_do = "0";
  private int jdField_else;
  private b jdField_goto;
  public List<String> jdField_if;
  private d jdField_long;
  public String jdField_new;
  private c jdField_this;
  
  public a() {}
  
  private String jdMethod_break()
  {
    jdMethod_case();
    if (com.babybus.j.a.jdMethod_goto()) {
      return this.jdField_long.jdMethod_case();
    }
    return "";
  }
  
  private void jdMethod_byte()
  {
    if (this.jdField_goto == null) {
      this.jdField_goto = new b();
    }
  }
  
  private void jdMethod_case()
  {
    if (this.jdField_long == null) {
      this.jdField_long = new d();
    }
  }
  
  private void jdMethod_char()
  {
    if (this.jdField_this == null) {
      this.jdField_this = new c();
    }
  }
  
  public static a jdMethod_do()
  {
    return try;
  }
  
  private String jdMethod_else()
  {
    Object localObject;
    if (App.jdMethod_do().jdField_do) {
      localObject = "http://beta-pic.baby-bus.com/";
    }
    String str;
    do
    {
      return localObject;
      str = aq.jdMethod_if("resource_url", "");
      localObject = str;
    } while (!TextUtils.isEmpty(str));
    return aw.jdMethod_goto();
  }
  
  private String jdMethod_goto()
  {
    return aw.jdMethod_char() + "api.php/v4/get_promote";
  }
  
  private String jdMethod_if(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("list", paramString);
      paramString = j.jdMethod_do().jdMethod_do(localJSONObject.toString());
      return paramString;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  private String jdMethod_int()
  {
    return aw.jdMethod_char() + "/api.php/v4/get_promote_list";
  }
  
  private List<String> jdMethod_long()
  {
    List localList = App.jdMethod_do().getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    int j = localList.size();
    int i = 0;
    while (i < j)
    {
      String str = ((PackageInfo)localList.get(i)).packageName;
      if (str.contains("com.sinyee.babybus")) {
        localArrayList.add(str);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  private String jdMethod_new()
  {
    Object localObject;
    if (App.jdMethod_do().jdField_do) {
      localObject = "http://beta.admin.baby-bus.com";
    }
    String str;
    do
    {
      return localObject;
      str = aq.jdMethod_if("zip_url", "");
      localObject = str;
    } while (!TextUtils.isEmpty(str));
    return aw.jdMethod_long();
  }
  
  private void jdMethod_new(int paramInt)
  {
    jdMethod_try(paramInt);
    switch (paramInt)
    {
    default: 
      return;
    case 1: 
      jdMethod_byte();
      this.jdField_goto.jdMethod_do();
      return;
    case 4: 
      jdMethod_case();
      this.jdField_long.jdMethod_do();
      return;
    }
    jdMethod_char();
    this.jdField_this.jdMethod_do();
  }
  
  private String jdMethod_this()
  {
    jdMethod_char();
    if (com.babybus.j.a.jdMethod_class()) {
      return this.jdField_this.jdMethod_case();
    }
    return "";
  }
  
  private int jdMethod_try()
  {
    int j = av.jdMethod_new();
    int i = j;
    if (j == 1) {
      i = 100;
    }
    return i;
  }
  
  private void jdMethod_try(int paramInt)
  {
    String str3 = "";
    String str1 = str3;
    switch (paramInt)
    {
    default: 
      str1 = str3;
    }
    for (;;)
    {
      try
      {
        if (TextUtils.isEmpty(str1)) {
          break label66;
        }
        com.babybus.i.a.jdMethod_do().jdMethod_do(str1, "");
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      str1 = "57EEB48C370010F7EDAADEDB526F61AB";
      continue;
      label66:
      return;
      String str2 = "E0908C9F1C9631DEE8A8176AEDF3E411";
    }
  }
  
  private String jdMethod_void()
  {
    jdMethod_byte();
    if (com.babybus.j.a.jdMethod_int()) {
      return this.jdField_goto.jdMethod_case();
    }
    return "";
  }
  
  public String jdMethod_do(int paramInt)
  {
    String str = this.jdField_byte + "|" + this.jdField_char + "|" + this.jdField_case + "|" + this.jdField_else + "|" + paramInt + "|" + this.jdField_do;
    x.jdMethod_for("广告：" + paramInt + "请求参数：" + str);
    return jdMethod_if(str);
  }
  
  public String jdMethod_do(Integer paramInteger)
  {
    switch (paramInteger.intValue())
    {
    default: 
      return "";
    case 1: 
      return jdMethod_void();
    case 4: 
      return jdMethod_break();
    }
    return jdMethod_this();
  }
  
  public void jdMethod_do(String paramString)
  {
    com.babybus.j.a.jdMethod_else(paramString);
  }
  
  public void jdMethod_do(String paramString1, String paramString2, String paramString3)
  {
    Object localObject = paramString1.split("_");
    if (localObject.length < 2) {}
    do
    {
      return;
      localObject = localObject[1];
      if (1 == t.jdMethod_do((String)localObject))
      {
        this.jdField_goto.jdMethod_do(paramString1, paramString2, paramString3);
        return;
      }
      if (19 == t.jdMethod_do((String)localObject))
      {
        this.jdField_this.jdMethod_do(paramString1, paramString2, paramString3);
        return;
      }
    } while (4 != t.jdMethod_do((String)localObject));
    this.jdField_long.jdMethod_do(paramString1, paramString2, paramString3);
  }
  
  public void jdMethod_for()
  {
    if (!j.jdMethod_do().jdMethod_try()) {
      j.jdMethod_do().jdMethod_for();
    }
  }
  
  public void jdMethod_for(int paramInt)
  {
    String str3 = "";
    String str1 = str3;
    switch (paramInt)
    {
    default: 
      str1 = str3;
    }
    for (;;)
    {
      try
      {
        if (TextUtils.isEmpty(str1)) {
          break label65;
        }
        com.babybus.i.a.jdMethod_do().jdMethod_do(str1);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      str1 = "123FA7E343350A100845DDDE40D51916";
      continue;
      label65:
      return;
      String str2 = "DBE736C7A414DC8C788D10A40D29C059";
    }
  }
  
  public String jdMethod_if(int paramInt)
  {
    return this.jdField_char + "_" + App.jdMethod_do().jdField_else + "_" + this.jdField_else + "_" + paramInt + "_" + this.jdField_do;
  }
  
  public void jdMethod_if()
  {
    this.jdField_byte = App.jdMethod_do().getPackageName();
    this.jdField_case = App.jdMethod_do().jdField_else;
    this.jdField_char = Integer.parseInt("2");
    this.jdField_else = jdMethod_try();
    this.jdField_if = jdMethod_long();
    this.jdField_new = jdMethod_new();
    for = jdMethod_else();
    int = jdMethod_goto();
    if (com.babybus.j.a.jdMethod_int()) {
      jdMethod_new(1);
    }
    if (com.babybus.j.a.jdMethod_goto()) {
      jdMethod_new(4);
    }
    if (com.babybus.j.a.jdMethod_class()) {
      jdMethod_new(19);
    }
  }
  
  public String jdMethod_int(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 1: 
      jdMethod_byte();
      return this.jdField_goto.jdMethod_long();
    case 4: 
      jdMethod_case();
      return this.jdField_long.jdMethod_long();
    }
    jdMethod_char();
    return this.jdField_this.jdMethod_long();
  }
  
  public class a
  {
    public static final String jdField_do = "1";
    public static final String jdField_for = "3";
    public static final String jdField_if = "2";
    
    public a() {}
  }
  
  public class b
  {
    public static final String jdField_do = "0";
    public static final String jdField_for = "2";
    public static final String jdField_if = "1";
    public static final String jdField_int = "3";
    public static final String jdField_new = "4";
    public static final String jdField_try = "5";
    
    public b() {}
  }
  
  public class c
  {
    public static final String jdField_do = "G_selfad";
    
    public c() {}
  }
}
