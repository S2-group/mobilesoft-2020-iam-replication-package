package com.babybus.plugin.googlead.b;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.babybus.app.App;
import com.babybus.h.j;
import com.babybus.k.al;
import com.babybus.k.aq;
import com.babybus.k.ar;
import com.babybus.k.u;
import com.babybus.plugin.googlead.b.a.b;
import com.babybus.plugin.googlead.b.a.c;
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
  public String jdField_do;
  private int jdField_else;
  private String jdField_goto;
  public List<String> jdField_if;
  private b jdField_long;
  public String jdField_new;
  private c jdField_this;
  
  public a() {}
  
  private void jdMethod_byte()
  {
    if (this.jdField_long == null) {
      this.jdField_long = new b();
    }
  }
  
  private void jdMethod_case()
  {
    if (this.jdField_this == null) {
      this.jdField_this = new c();
    }
  }
  
  private String jdMethod_char()
  {
    Object localObject;
    if (App.jdMethod_do().jdField_do) {
      localObject = "http://beta-pic.baby-bus.com/";
    }
    String str;
    do
    {
      return localObject;
      str = al.jdMethod_if("resource_url", "");
      localObject = str;
    } while (!TextUtils.isEmpty(str));
    return ar.jdMethod_goto();
  }
  
  public static a jdMethod_do()
  {
    return try;
  }
  
  private String jdMethod_else()
  {
    return ar.jdMethod_char() + "api.php/v4/get_promote";
  }
  
  private List<String> jdMethod_goto()
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
    return ar.jdMethod_char() + "/api.php/v4/get_promote_list";
  }
  
  private void jdMethod_int(int paramInt)
  {
    jdMethod_new(paramInt);
    switch (paramInt)
    {
    case 2: 
    case 3: 
    default: 
      return;
    case 1: 
      jdMethod_byte();
      this.jdField_long.jdMethod_do();
      return;
    }
    jdMethod_case();
    this.jdField_this.jdMethod_do();
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
      str = al.jdMethod_if("zip_url", "");
      localObject = str;
    } while (!TextUtils.isEmpty(str));
    return ar.jdMethod_long();
  }
  
  private void jdMethod_new(int paramInt)
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
        com.babybus.j.a.jdMethod_do().jdMethod_do(str1, "");
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
  
  private int jdMethod_try()
  {
    int j = aq.jdMethod_new();
    int i = j;
    if (j == 1) {
      i = 100;
    }
    return i;
  }
  
  public String jdMethod_do(int paramInt)
  {
    String str = this.jdField_byte + "|" + this.jdField_char + "|" + this.jdField_case + "|" + this.jdField_else + "|" + paramInt + "|" + this.jdField_do;
    u.jdMethod_for("广告：" + paramInt + "请求参数：" + str);
    return jdMethod_if(str);
  }
  
  public String jdMethod_do(Integer paramInteger)
  {
    switch (paramInteger.intValue())
    {
    case 2: 
    case 3: 
    default: 
      return "";
    case 1: 
      jdMethod_byte();
      return this.jdField_long.jdMethod_new();
    }
    jdMethod_case();
    return this.jdField_this.jdMethod_new();
  }
  
  public void jdMethod_do(String paramString)
  {
    com.babybus.k.a.jdMethod_else(paramString);
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
          break label64;
        }
        com.babybus.j.a.jdMethod_do().jdMethod_do(str1);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      str1 = "123FA7E343350A100845DDDE40D51916";
      continue;
      label64:
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
    this.jdField_do = "0";
    this.jdField_char = Integer.parseInt("2");
    this.jdField_else = jdMethod_try();
    this.jdField_if = jdMethod_goto();
    this.jdField_new = jdMethod_new();
    for = jdMethod_char();
    int = jdMethod_else();
    this.jdField_goto = com.babybus.k.a.jdMethod_do();
    if ("1".equals(al.jdMethod_if("startup_state", this.jdField_goto))) {
      jdMethod_int(1);
    }
    if (!"0".equals(al.jdMethod_if("welcome_re_state", this.jdField_goto))) {
      jdMethod_int(4);
    }
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
