package anagog.pd.a;

import anagog.pd.service.MobilityService;
import anagog.pd.service.e.a;
import anagog.pd.service.e.a.a;
import anagog.pd.service.f.a;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class m
{
  private static char[] f = { 65, -26265, 12894, -13514, 25620, -742, -26887, 12245, -14121, 25021, -1377, -27693, 11638, -14782, 24364, 67, -26250, 12864, -13525, 25651, -761, -26966, 12237, -14121, 25006, -1406, -27683, 11637, -14757, 24370, -1995, -28391, 10980, -15361, 23745, -2651, -29044, 10132, -16030, 23048, -3241, -29633, 9502, -16656, 22516, -3904, -30288, 67, -26250, 12864, -13525, 25651, -761, -26966, 12242, -14119, 25017, -1405, -27683, 11637, -14757, 24370, -2006, -28336, 10991, -15369, 23750, -2632, -29044, 10120, -16024, 27692, -2788, 24120, -22700, 2136, -28294, -1405, 17313, -23367, 67, -26250, 12864, -13525, 25651, -761, -26966, 12226, -14134, 25002, -1401, -27767, 11633, -14837, 24328, -2038, -28353, 10953, -15426, 23770, -2642, -29047, 10143, -16014, 23132, -3297, -29633, 9475, -16649, 22511, -3885, -30299, 8844, -17518, 21866, -4603, -30883, 8227, -18150, 21234, -13626, 21492, -1831, 439, -20805, 14213, 23609, -6848, 606, -21710, 12315, 22791, -6167, 3295, -27151, 12971, 23443, -8092, 2430, 85, -26265, 12874, -13532, 25640, -746, -26966, 12232, -14122, 25020, -1390, -27748, 11640, -14777, 24359, -1987, -28336, 10982, -15378, 23749, -2625, 83, -26269, 12865, -13515, 25644, -746, -26898, 4276, -30327, 8890, -9258, 29918, -4615, 70, -26242, 12892, -13514, 25640, -710, -26908, 12242, -14132, 25006, -1398, -27759, 11584, -14782, 24367, -1988, -11926, 18550, -7342, 6764, 19730, -11224, 32515, -31121, 10583, -20387, -9296, 25246, -31342, 11508, -18452, -8502, 24615, -29936, 80, -26250, 12877, -13522, 25661, -748, -26897, 12271, -14119, 24994, -1405, 11810, -18662, 7215, -6836, 67, -26250, 12864, -13525, 25651, -761, -26966, 12226, -14134, 25002, -1401, -27767, 11633, -14837, 24328, -2038, -28353, 10953, -15426, 23770, -2642, -29047, 10143, -16014, 23132, -3297, -29648, 9474, -16650, 22459, -3885, -30297, 8896, -17506, 21856, -4522, -30904, 8242, -18170, 21229, -5123, -31541, 7654, -18756, 20356, -5765 };
  private static long g = -2896561883583244009L;
  private static int h = 0;
  private static int i = 1;
  public ArrayList<fx> a = new ArrayList();
  public a b = null;
  private Context c;
  private final PackageManager d;
  private final 1 e = new a.a()
  {
    public final void a(JSONArray paramAnonymousJSONArray)
    {
      Iterator localIterator = m.this.a.iterator();
      while (localIterator.hasNext())
      {
        fx localFx = (fx)localIterator.next();
        if (localFx.b() > 0) {
          localFx.a(paramAnonymousJSONArray);
        }
      }
    }
  };
  
  public m(MobilityService paramMobilityService, PackageManager paramPackageManager)
  {
    this.c = paramMobilityService.getApplicationContext();
    this.d = paramPackageManager;
    try
    {
      paramMobilityService = this.c.openFileInput(a(15, 0, '\000').intern());
      paramPackageManager = new GZIPInputStream(paramMobilityService);
      ObjectInputStream localObjectInputStream = new ObjectInputStream(paramPackageManager);
      this.b = ((a)localObjectInputStream.readObject());
      localObjectInputStream.close();
      paramPackageManager.close();
      paramMobilityService.close();
      if (this.b == null)
      {
        this.b = new a(d());
        this.b.b = this.e;
        return;
      }
    }
    catch (IOException paramMobilityService)
    {
      for (;;)
      {
        gr.a().a(f.a.c, new Object[] { a(32, 15, '\000').intern() });
        continue;
        this.b.b();
      }
    }
    catch (ClassNotFoundException paramMobilityService)
    {
      for (;;) {}
    }
  }
  
  public static String a(int paramInt1, int paramInt2, char paramChar)
  {
    int j = 0;
    int m = 0;
    char[] arrayOfChar = new char[paramInt1];
    int k = i + 19;
    h = k % 128;
    if (k % 2 != 0)
    {
      k = 1;
      switch (k)
      {
      default: 
        if (paramInt1 > 0)
        {
          j = 1;
          switch (j)
          {
          default: 
            k = m;
            arrayOfChar[k] = ((char)(int)(f[(paramInt2 + k)] ^ k * g ^ paramChar));
            k += 1;
            j = i + 55;
            h = j % 128;
            if (j % 2 != 0) {}
            for (m = 48;; m = 81)
            {
              j = k;
              switch (m)
              {
              }
            }
          }
        }
        break;
      case 0: 
        label62:
        label84:
        if (j < paramInt1)
        {
          for (;;)
          {
            m = 1;
            label187:
            k = j;
            switch (m)
            {
            }
          }
          return new String(arrayOfChar);
          if (k >= paramInt1) {
            break label281;
          }
        }
        break;
      }
    }
    label281:
    for (j = 86;; j = 30)
    {
      switch (j)
      {
      }
      break label84;
      m = 59;
      break label187;
      k = 0;
      break;
      j = 0;
      break label62;
    }
  }
  
  private JSONObject a(PackageInfo paramPackageInfo)
  {
    JSONObject localJSONObject = new JSONObject();
    int k = Build.VERSION.SDK_INT;
    int j;
    label22:
    label64:
    label102:
    boolean bool;
    if (k >= 12)
    {
      j = 67;
      switch (j)
      {
      default: 
        j = h + 33;
        i = j % 128;
        if (j % 2 == 0)
        {
          j = 69;
          switch (j)
          {
          }
          try
          {
            j = paramPackageInfo.applicationInfo.flags;
            if ((j & 0x200000) == 0) {
              break label659;
            }
            j = 1;
            switch (j)
            {
            default: 
              j = i + 97;
              h = j % 128;
              if (j % 2 == 0) {
                break label461;
              }
              bool = true;
              label144:
              localJSONObject.put(a(7, 160, '\000').intern(), bool);
              if ((paramPackageInfo.applicationInfo.flags & 0x1) == 0) {
                break label712;
              }
              j = 0;
            }
          }
          catch (JSONException paramPackageInfo)
          {
            gr.a().a(f.a.c, new Object[] { a(46, 222, '\000').intern() });
            return localJSONObject;
          }
          if ((paramPackageInfo.applicationInfo.flags & 0x200000) != 0) {
            j = 0;
          }
        }
        break;
      }
    }
    for (;;)
    {
      localJSONObject.put(a(6, 167, 'ყ').intern(), bool);
      if (k < 9) {
        break label776;
      }
      j = 0;
      break label751;
      localJSONObject.put(a(16, 173, '\000').intern(), String.format(Locale.US, a(4, 189, 53583).intern(), new Object[] { Double.valueOf(paramPackageInfo.firstInstallTime / 1000.0D) }));
      localJSONObject.put(a(14, 193, '䵞').intern(), String.format(Locale.US, a(4, 189, 53583).intern(), new Object[] { Double.valueOf(paramPackageInfo.lastUpdateTime / 1000.0D) }));
      j = i + 33;
      h = j % 128;
      if (j % 2 != 0) {}
      localJSONObject.put(a(11, 207, '\000').intern(), paramPackageInfo.packageName);
      localJSONObject.put(a(4, 218, '⹬').intern(), paramPackageInfo.applicationInfo.loadLabel(this.d).toString());
      return localJSONObject;
      label461:
      bool = true;
      break label144;
      j = h + 101;
      i = j % 128;
      if (j % 2 == 0)
      {
        for (;;)
        {
          j = 14;
          label491:
          switch (j)
          {
          }
        }
        j = i + 45;
        h = j % 128;
        if (j % 2 == 0) {
          break label670;
        }
      }
      label659:
      label670:
      for (j = 13;; j = 42) {
        switch (j)
        {
        default: 
          break;
        case 13: 
          localJSONObject.put(a(6, 167, 'ყ').intern(), true);
          if (k >= 9) {}
          for (j = 76;; j = 21) {
            switch (j)
            {
            }
          }
          j = 24;
          break label22;
          j = 1;
          break label717;
          j = 10;
          break label64;
          j = 0;
          break label102;
          j = 67;
          break label491;
        }
      }
      bool = true;
      continue;
      bool = false;
      continue;
      for (;;)
      {
        switch (j)
        {
        }
        label712:
        j = 1;
      }
      switch (j)
      {
      default: 
        break;
      case 1: 
        label717:
        bool = false;
        break label144;
        bool = true;
      }
    }
    for (;;)
    {
      label751:
      switch (j)
      {
      }
      label776:
      j = 1;
    }
  }
  
  private JSONObject d()
  {
    JSONObject localJSONObject = new JSONObject();
    JSONArray localJSONArray = new JSONArray();
    Object localObject = this.d.getInstalledPackages(0);
    int j;
    if (localObject == null)
    {
      j = 96;
      switch (j)
      {
      default: 
        j = i + 89;
        h = j % 128;
        if (j % 2 != 0) {}
        ArrayList localArrayList = new ArrayList();
        localObject = ((List)localObject).iterator();
        if (!((Iterator)localObject).hasNext())
        {
          j = 4;
          switch (j)
          {
          default: 
            for (;;)
            {
              label103:
              j = i + 37;
              h = j % 128;
              if (j % 2 != 0) {
                break label425;
              }
              j = 31;
              label148:
              PackageInfo localPackageInfo;
              switch (j)
              {
              default: 
                localPackageInfo = (PackageInfo)((Iterator)localObject).next();
                localJSONArray.put(a(localPackageInfo));
                localArrayList.add(localPackageInfo.packageName);
                break;
              case 31: 
                localPackageInfo = (PackageInfo)((Iterator)localObject).next();
                localJSONArray.put(a(localPackageInfo));
                localArrayList.add(localPackageInfo.packageName);
                j = i + 35;
                h = j % 128;
                if (j % 2 != 0) {
                  break label431;
                }
                j = 51;
                label263:
                switch (j)
                {
                }
                if (((Iterator)localObject).hasNext()) {
                  break label437;
                }
                j = 0;
                label296:
                switch (j)
                {
                }
                break;
              }
            }
          }
        }
        break;
      }
    }
    for (;;)
    {
      try
      {
        localJSONObject.put(a(9, 71, '汥').intern(), localJSONArray);
        j = h + 101;
        i = j % 128;
        if (j % 2 == 0) {
          break label442;
        }
        j = 1;
        switch (j)
        {
        default: 
          return localJSONObject;
        }
      }
      catch (JSONException localJSONException)
      {
        gr.a().a(f.a.c, new Object[] { a(40, 80, '\000').intern() });
        return localJSONObject;
      }
      j = 63;
      break;
      j = 26;
      break label103;
      label425:
      j = 54;
      break label148;
      label431:
      j = 39;
      break label263;
      label437:
      j = 1;
      break label296;
      label442:
      j = 80;
    }
  }
  
  public final void a()
  {
    gr.a().a(f.a.c, new Object[] { a(21, 139, '\000').intern() });
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap = new HashMap();
    Object localObject = this.d.getInstalledPackages(0);
    if (localObject == null)
    {
      j = 0;
      switch (j)
      {
      default: 
        label61:
        j = i + 75;
        h = j % 128;
        if (j % 2 != 0) {
          break;
        }
      }
    }
    for (int j = 1;; j = 0)
    {
      switch (j)
      {
      default: 
        localObject = ((List)localObject).iterator();
        if (((Iterator)localObject).hasNext()) {
          break;
        }
      }
      for (j = 1;; j = 0) {
        switch (j)
        {
        default: 
          break;
        case 0: 
          j = h + 59;
          i = j % 128;
          if (j % 2 == 0) {}
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
          JSONObject localJSONObject = a(localPackageInfo);
          localArrayList.add(localPackageInfo.packageName);
          localHashMap.put(localPackageInfo.packageName, localJSONObject);
          break;
          localObject = ((List)localObject).iterator();
          break;
        case 1: 
          this.b.a(localArrayList, localHashMap);
          a(this.b);
          return;
          j = 1;
          break label61;
        }
      }
    }
  }
  
  public final void a(a paramA)
  {
    try
    {
      FileOutputStream localFileOutputStream = this.c.openFileOutput(a(15, 0, '\000').intern(), 0);
      int j;
      try
      {
        GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localFileOutputStream);
        ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localGZIPOutputStream);
        localObjectOutputStream.writeObject(paramA);
        localObjectOutputStream.close();
        localGZIPOutputStream.close();
        paramA = localFileOutputStream;
        if (localFileOutputStream == null) {
          break label128;
        }
        j = 0;
        paramA = localFileOutputStream;
      }
      catch (IOException paramA)
      {
        gr.a().a(f.a.c, new Object[] { a(24, 47, '\000').intern() });
        return;
      }
      paramA.close();
      return;
      for (;;)
      {
        switch (j)
        {
        }
        label128:
        j = 1;
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      for (;;)
      {
        Object localObject = null;
      }
    }
  }
  
  public final JSONArray b()
  {
    Object localObject = this.b;
    JSONArray localJSONArray = new JSONArray();
    localObject = ((a)localObject).a.values().iterator();
    while (((Iterator)localObject).hasNext()) {
      localJSONArray.put((JSONObject)((Iterator)localObject).next());
    }
    return localJSONArray;
  }
  
  public final void c()
  {
    JSONObject localJSONObject = this.b.a();
    Iterator localIterator = this.a.iterator();
    while (localIterator.hasNext())
    {
      fx localFx = (fx)localIterator.next();
      if (localFx.b() == 0) {
        localFx.a(localJSONObject);
      }
    }
  }
}
