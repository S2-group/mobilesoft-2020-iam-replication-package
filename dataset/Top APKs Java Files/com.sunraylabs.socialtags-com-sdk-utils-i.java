package com.sdk.utils;

import a.b.l;
import a.b.m;
import a.b.o;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class i
{
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0);
      a localA;
      Object localObject1;
      Object localObject2;
      do
      {
        Iterator localIterator = a().iterator();
        while (!((Iterator)localObject2).hasNext())
        {
          if (!localIterator.hasNext()) {
            break;
          }
          localA = (a)localIterator.next();
          localObject1 = new StringBuilder();
          localObject2 = localA.b();
          int j = localObject2.length;
          int i = 0;
          while (i < j)
          {
            ((StringBuilder)localObject1).append(localObject2[i]);
            i += 1;
          }
          localObject1 = ((StringBuilder)localObject1).toString();
          localObject2 = paramContext.iterator();
        }
      } while (!((ApplicationInfo)((Iterator)localObject2).next()).packageName.equalsIgnoreCase((String)localObject1));
      paramContext = localA.a();
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  private static ArrayList<a> a()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new a("Lucky Patcher", new String[] { "c", "o", "m", ".", "c", "h", "e", "l", "p", "u", "s", ".", "l", "a", "c", "k", "y", "p", "a", "t", "c", "h" }));
    localArrayList.add(new a("Lucky Patcher", new String[] { "c", "o", "m", ".", "d", "i", "m", "o", "n", "v", "i", "d", "e", "o", ".", "l", "u", "c", "k", "y", "p", "a", "t", "c", "h", "e", "r" }));
    localArrayList.add(new a("Lucky Patcher", new String[] { "c", "o", "m", ".", "f", "o", "r", "p", "d", "a", ".", "l", "p" }));
    localArrayList.add(new a("Lucky Patcher", new String[] { "c", "o", "m", ".", "a", "n", "d", "r", "o", "i", "d", ".", "v", "e", "n", "d", "i", "n", "g", ".", "b", "i", "l", "l", "i", "n", "g", ".", "I", "n", "A", "p", "p", "B", "i", "l", "l", "i", "n", "g", "S", "e", "r", "v", "i", "c", "e", ".", "L", "U", "C", "K" }));
    localArrayList.add(new a("Lucky Patcher", new String[] { "c", "o", "m", ".", "a", "n", "d", "r", "o", "i", "d", ".", "v", "e", "n", "d", "i", "n", "g", ".", "b", "i", "l", "l", "i", "n", "g", ".", "I", "n", "A", "p", "p", "B", "i", "l", "l", "i", "n", "g", "S", "e", "r", "v", "i", "c", "e", ".", "L", "O", "C", "K" }));
    localArrayList.add(new a("Lucky Patcher", new String[] { "c", "o", "m", ".", "a", "n", "d", "r", "o", "i", "d", ".", "v", "e", "n", "d", "i", "n", "g", ".", "b", "i", "l", "l", "i", "n", "g", ".", "I", "n", "A", "p", "p", "B", "i", "l", "l", "i", "n", "g", "S", "e", "r", "v", "i", "c", "e", ".", "L", "A", "C", "K" }));
    localArrayList.add(new a("Lucky Patcher", new String[] { "c", "o", "m", ".", "a", "n", "d", "r", "o", "i", "d", ".", "v", "e", "n", "d", "i", "n", "g", ".", "b", "i", "l", "l", "i", "n", "g", ".", "I", "n", "A", "p", "p", "B", "i", "l", "l", "i", "n", "g", "S", "e", "r", "v", "i", "c", "e", ".", "C", "L", "O", "N" }));
    localArrayList.add(new a("Lucky Patcher", new String[] { "c", "o", "m", ".", "a", "n", "d", "r", "o", "i", "d", ".", "v", "e", "n", "d", "i", "n", "g", ".", "b", "i", "l", "l", "i", "n", "g", ".", "I", "n", "A", "p", "p", "B", "i", "l", "l", "i", "n", "g", "S", "e", "r", "v", "i", "c", "e", ".", "C", "R", "A", "C" }));
    localArrayList.add(new a("Lucky Patcher", new String[] { "c", "o", "m", ".", "a", "n", "d", "r", "o", "i", "d", ".", "v", "e", "n", "d", "i", "n", "g", ".", "b", "i", "l", "l", "i", "n", "g", ".", "I", "n", "A", "p", "p", "B", "i", "l", "l", "i", "n", "g", "S", "e", "r", "v", "i", "c", "e", ".", "C", "O", "I", "N" }));
    localArrayList.add(new a("Uret Patcher", new String[] { "u", "r", "e", "t", ".", "j", "a", "s", "i", "2", "1", "6", "9", ".", "p", "a", "t", "c", "h", "e", "r" }));
    localArrayList.add(new a("Freedom", new String[] { "c", "c", ".", "m", "a", "d", "k", "i", "t", "e", ".", "f", "r", "e", "e", "d", "o", "m" }));
    localArrayList.add(new a("Freedom", new String[] { "c", "c", ".", "c", "z", ".", "m", "a", "d", "k", "i", "t", "e", ".", "f", "r", "e", "e", "d", "o", "m" }));
    localArrayList.add(new a("CreeHack", new String[] { "o", "r", "g", ".", "c", "r", "e", "e", "p", "l", "a", "y", "s", ".", "h", "a", "c", "k" }));
    localArrayList.add(new a("BlackMart", new String[] { "o", "r", "g", ".", "b", "l", "a", "c", "k", "m", "a", "r", "t", ".", "m", "a", "r", "k", "e", "t" }));
    return localArrayList;
  }
  
  public static l<String> b(Context paramContext)
  {
    l.a(new o()
    {
      public void a(m<String> paramAnonymousM)
      {
        try
        {
          if (!paramAnonymousM.a())
          {
            paramAnonymousM.a(i.a(this.a));
            return;
          }
        }
        catch (Throwable localThrowable)
        {
          paramAnonymousM.a(localThrowable);
        }
      }
    });
  }
  
  private static class a
  {
    private String a;
    private String[] b;
    
    public a(String paramString, String[] paramArrayOfString)
    {
      this.a = paramString;
      this.b = paramArrayOfString;
    }
    
    public String a()
    {
      return this.a;
    }
    
    public String[] b()
    {
      return this.b;
    }
  }
}
