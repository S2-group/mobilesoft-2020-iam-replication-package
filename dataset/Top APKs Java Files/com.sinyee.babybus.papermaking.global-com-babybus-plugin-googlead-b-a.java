package com.babybus.plugin.googlead.b;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.babybus.app.App;
import com.babybus.e.k;
import com.babybus.h.at;
import com.babybus.h.ay;
import com.babybus.h.az;
import com.babybus.h.v;
import com.babybus.h.z;
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
  public static String jdField_if;
  private static final a jdField_new = new a();
  private String jdField_byte;
  private int jdField_case;
  private int jdField_char;
  public String jdField_do = "0";
  private b jdField_else;
  private d jdField_goto;
  public String jdField_int;
  private c jdField_long;
  private String jdField_try;
  
  public a() {}
  
  private String jdMethod_break()
  {
    jdMethod_case();
    if (com.babybus.h.a.jdMethod_this()) {
      return this.jdField_goto.jdMethod_case();
    }
    return "";
  }
  
  private void jdMethod_byte()
  {
    if (this.jdField_else == null) {
      this.jdField_else = new b();
    }
  }
  
  private void jdMethod_case()
  {
    if (this.jdField_goto == null) {
      this.jdField_goto = new d();
    }
  }
  
  private void jdMethod_char()
  {
    if (this.jdField_long == null) {
      this.jdField_long = new c();
    }
  }
  
  public static a jdMethod_do()
  {
    return new;
  }
  
  private String jdMethod_do(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("list", paramString);
      paramString = k.jdMethod_do().jdMethod_do(localJSONObject.toString());
      return paramString;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  private String jdMethod_else()
  {
    if (App.jdMethod_do().jdField_do) {
      return "http://beta-pic.baby-bus.com/";
    }
    String str2 = at.jdMethod_if("resource_url", "");
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = az.jdMethod_goto();
    }
    return str1;
  }
  
  private String jdMethod_goto()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(az.jdMethod_char());
    localStringBuilder.append("api.php/v4/get_promote");
    return localStringBuilder.toString();
  }
  
  private String jdMethod_int()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(az.jdMethod_char());
    localStringBuilder.append("/api.php/v4/get_promote_list");
    return localStringBuilder.toString();
  }
  
  private List<String> jdMethod_long()
  {
    Object localObject = App.jdMethod_do().getPackageManager();
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    int j = ((List)localObject).size();
    while (i < j)
    {
      String str = ((PackageInfo)((List)localObject).get(i)).packageName;
      if (str.contains("com.sinyee.babybus")) {
        localArrayList.add(str);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  private String jdMethod_new()
  {
    if (App.jdMethod_do().jdField_do) {
      return "http://beta.admin.baby-bus.com";
    }
    String str2 = at.jdMethod_if("zip_url", "");
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = az.jdMethod_long();
    }
    return str1;
  }
  
  private void jdMethod_new(int paramInt)
  {
    jdMethod_try(paramInt);
    if (paramInt != 1)
    {
      if (paramInt != 4)
      {
        if (paramInt != 19) {
          return;
        }
        jdMethod_char();
        this.jdField_long.jdMethod_do();
        return;
      }
      jdMethod_case();
      this.jdField_goto.jdMethod_do();
      return;
    }
    jdMethod_byte();
    this.jdField_else.jdMethod_do();
  }
  
  private String jdMethod_this()
  {
    jdMethod_char();
    if (com.babybus.h.a.jdMethod_final()) {
      return this.jdField_long.jdMethod_case();
    }
    return "";
  }
  
  private int jdMethod_try()
  {
    int j = ay.jdMethod_new();
    int i = j;
    if (j == 1) {
      i = 100;
    }
    return i;
  }
  
  private void jdMethod_try(int paramInt)
  {
    String str1 = "";
    if (paramInt != 1) {
      if (paramInt == 4) {
        str1 = "57EEB48C370010F7EDAADEDB526F61AB";
      }
    }
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty(str1))
        {
          com.babybus.g.a.jdMethod_do().jdMethod_do(str1, "");
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return;
      String str2 = "E0908C9F1C9631DEE8A8176AEDF3E411";
    }
  }
  
  private String jdMethod_void()
  {
    jdMethod_byte();
    if (com.babybus.h.a.jdMethod_int()) {
      return this.jdField_else.jdMethod_case();
    }
    return "";
  }
  
  public String jdMethod_do(int paramInt)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(this.jdField_try);
    ((StringBuilder)localObject).append("|");
    ((StringBuilder)localObject).append(this.jdField_case);
    ((StringBuilder)localObject).append("|");
    ((StringBuilder)localObject).append(this.jdField_byte);
    ((StringBuilder)localObject).append("|");
    ((StringBuilder)localObject).append(this.jdField_char);
    ((StringBuilder)localObject).append("|");
    ((StringBuilder)localObject).append(paramInt);
    ((StringBuilder)localObject).append("|");
    ((StringBuilder)localObject).append(this.jdField_do);
    localObject = ((StringBuilder)localObject).toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("广告：");
    localStringBuilder.append(paramInt);
    localStringBuilder.append("请求参数：");
    localStringBuilder.append((String)localObject);
    z.jdMethod_for(localStringBuilder.toString());
    return jdMethod_do((String)localObject);
  }
  
  public String jdMethod_do(Integer paramInteger)
  {
    int i = paramInteger.intValue();
    if (i != 1)
    {
      if (i != 4)
      {
        if (i != 19) {
          return "";
        }
        return jdMethod_this();
      }
      return jdMethod_break();
    }
    return jdMethod_void();
  }
  
  public void jdMethod_do(String paramString1, String paramString2, String paramString3)
  {
    Object localObject = paramString1.split("_");
    if (localObject.length < 2) {
      return;
    }
    localObject = localObject[1];
    if (1 == v.jdMethod_do((String)localObject))
    {
      this.jdField_else.jdMethod_do(paramString1, paramString2, paramString3);
      return;
    }
    if (19 == v.jdMethod_do((String)localObject))
    {
      this.jdField_long.jdMethod_do(paramString1, paramString2, paramString3);
      return;
    }
    if (4 == v.jdMethod_do((String)localObject)) {
      this.jdField_goto.jdMethod_do(paramString1, paramString2, paramString3);
    }
  }
  
  public void jdMethod_for()
  {
    if (!k.jdMethod_do().jdMethod_try()) {
      k.jdMethod_do().jdMethod_for();
    }
  }
  
  public void jdMethod_for(int paramInt)
  {
    String str1 = "";
    if (paramInt != 1) {
      if (paramInt == 4) {
        str1 = "123FA7E343350A100845DDDE40D51916";
      }
    }
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty(str1))
        {
          com.babybus.g.a.jdMethod_do().jdMethod_do(str1);
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return;
      String str2 = "DBE736C7A414DC8C788D10A40D29C059";
    }
  }
  
  public String jdMethod_if(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.jdField_case);
    localStringBuilder.append("_");
    localStringBuilder.append(App.jdMethod_do().jdField_else);
    localStringBuilder.append("_");
    localStringBuilder.append(this.jdField_char);
    localStringBuilder.append("_");
    localStringBuilder.append(paramInt);
    localStringBuilder.append("_");
    localStringBuilder.append(this.jdField_do);
    return localStringBuilder.toString();
  }
  
  public void jdMethod_if()
  {
    this.jdField_try = App.jdMethod_do().getPackageName();
    this.jdField_byte = App.jdMethod_do().jdField_else;
    this.jdField_case = Integer.parseInt("2");
    this.jdField_char = jdMethod_try();
    this.jdField_int = jdMethod_new();
    if = jdMethod_else();
    for = jdMethod_goto();
    if (com.babybus.h.a.jdMethod_int()) {
      jdMethod_new(1);
    }
    if (com.babybus.h.a.jdMethod_this()) {
      jdMethod_new(4);
    }
    if (com.babybus.h.a.jdMethod_final()) {
      jdMethod_new(19);
    }
  }
  
  public String jdMethod_int(int paramInt)
  {
    if (paramInt != 1)
    {
      if (paramInt != 4)
      {
        if (paramInt != 19) {
          return "";
        }
        jdMethod_char();
        return this.jdField_long.jdMethod_long();
      }
      jdMethod_case();
      return this.jdField_goto.jdMethod_long();
    }
    jdMethod_byte();
    return this.jdField_else.jdMethod_long();
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
