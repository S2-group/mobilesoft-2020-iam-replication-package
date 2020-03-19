package com.monti.lib.cw.manager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.support.v4.f.j;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import com.monti.lib.cw.keyboard.CWKeyboardMatch;
import com.monti.lib.cw.utils.CWInstalledPackagesHolder;
import com.monti.lib.cw.utils.CWPackagesUtil;
import com.monti.lib.cw.utils.CWStateHolder;
import com.monti.lib.cw.utils.CWType;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CWImeStateManager
{
  List<CWKeyboardMatch> keyboardMatches = new LinkedList();
  private boolean mHasChange = true;
  private CWStateHolder mStateHolder;
  
  private CWImeStateManager() {}
  
  private void clearAllCandidates()
  {
    Iterator localIterator = this.keyboardMatches.iterator();
    while (localIterator.hasNext())
    {
      CWKeyboardMatch localCWKeyboardMatch = (CWKeyboardMatch)localIterator.next();
      if (localCWKeyboardMatch != null) {
        localCWKeyboardMatch.clearAllCandidates();
      }
    }
  }
  
  public static CWImeStateManager getInstance()
  {
    return Singleton.INSTANCE;
  }
  
  private j<CWKeyboardMatch, String> installedHighestPriorityKeyboard()
  {
    Iterator localIterator = this.keyboardMatches.iterator();
    while (localIterator.hasNext())
    {
      CWKeyboardMatch localCWKeyboardMatch = (CWKeyboardMatch)localIterator.next();
      if (localCWKeyboardMatch != null)
      {
        String str = localCWKeyboardMatch.installedHighestPriorityKeyboard();
        if (!TextUtils.isEmpty(str)) {
          return new j(localCWKeyboardMatch, str);
        }
      }
    }
    return null;
  }
  
  private void match(String paramString, PackageInfo paramPackageInfo)
  {
    Iterator localIterator = this.keyboardMatches.iterator();
    while (localIterator.hasNext())
    {
      CWKeyboardMatch localCWKeyboardMatch = (CWKeyboardMatch)localIterator.next();
      if (localCWKeyboardMatch != null) {
        localCWKeyboardMatch.match(paramString, paramPackageInfo);
      }
    }
  }
  
  private j<CWKeyboardMatch, String> notInstalledHighestPriorityKeyboard()
  {
    Iterator localIterator = this.keyboardMatches.iterator();
    while (localIterator.hasNext())
    {
      CWKeyboardMatch localCWKeyboardMatch = (CWKeyboardMatch)localIterator.next();
      if (localCWKeyboardMatch != null)
      {
        String str = localCWKeyboardMatch.notInstalledHighestPriorityKeyboard();
        if (!TextUtils.isEmpty(str)) {
          return new j(localCWKeyboardMatch, str);
        }
      }
    }
    return null;
  }
  
  public CWStateHolder getInstantStateHolder(Context paramContext)
  {
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      try
      {
        localObject1 = CWPackagesUtil.getActivatedIMEPackageName(paramContext);
        localObject2 = identify((String)localObject1);
        if (localObject2 == null) {
          break label442;
        }
        if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (localObject2 != null))
        {
          if (CWPackagesUtil.getVersionCodeByPkgName(paramContext, (String)localObject1) < ((CWKeyboardMatch)localObject2).supportVersion((String)localObject1))
          {
            paramContext = new CWStateHolder(2, (String)localObject1, ((CWKeyboardMatch)localObject2).type());
            return paramContext;
          }
          paramContext = new CWStateHolder(4, (String)localObject1, ((CWKeyboardMatch)localObject2).type());
          continue;
        }
        if (!TextUtils.isEmpty((CharSequence)localObject1)) {
          break label146;
        }
      }
      finally {}
      clearAllCandidates();
      paramContext = CWPackagesUtil.getInputMethodList(paramContext);
      if ((paramContext != null) && (!paramContext.isEmpty()))
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext()) {
          match(((InputMethodInfo)paramContext.next()).getPackageName(), null);
        }
      }
      label146:
      Object localObject3 = installedHighestPriorityKeyboard();
      paramContext = (Context)localObject2;
      if (localObject3 != null)
      {
        paramContext = (CWKeyboardMatch)((j)localObject3).a;
        localObject1 = (String)((j)localObject3).b;
      }
      if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (paramContext != null))
      {
        paramContext = new CWStateHolder(3, (String)localObject1, paramContext.type());
      }
      else
      {
        Object localObject4 = CWInstalledPackagesHolder.getInstance().getInstalledPackages();
        localObject3 = paramContext;
        localObject2 = localObject1;
        if (localObject4 != null)
        {
          localObject3 = paramContext;
          localObject2 = localObject1;
          if (!((List)localObject4).isEmpty())
          {
            clearAllCandidates();
            localObject2 = ((List)localObject4).iterator();
            while (((Iterator)localObject2).hasNext())
            {
              localObject3 = (PackageInfo)((Iterator)localObject2).next();
              match(((PackageInfo)localObject3).packageName, (PackageInfo)localObject3);
            }
            localObject4 = installedHighestPriorityKeyboard();
            localObject3 = paramContext;
            localObject2 = localObject1;
            if (localObject4 != null)
            {
              localObject3 = (CWKeyboardMatch)((j)localObject4).a;
              localObject2 = (String)((j)localObject4).b;
            }
          }
        }
        if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (localObject3 != null))
        {
          paramContext = new CWStateHolder(3, (String)localObject2, ((CWKeyboardMatch)localObject3).type());
        }
        else if ((this.keyboardMatches != null) && (!this.keyboardMatches.isEmpty()))
        {
          paramContext = notInstalledHighestPriorityKeyboard();
          if (paramContext != null)
          {
            localObject1 = (CWKeyboardMatch)paramContext.a;
            paramContext = new CWStateHolder(1, (String)paramContext.b, ((CWKeyboardMatch)localObject1).type());
          }
          else
          {
            paramContext = new CWStateHolder(0, "", CWType.MAIN_KEYBOARD);
          }
        }
        else
        {
          paramContext = new CWStateHolder(0, "", CWType.MAIN_KEYBOARD);
          continue;
          label442:
          localObject1 = "";
        }
      }
    }
  }
  
  public CWStateHolder getState(Context paramContext)
  {
    this.mStateHolder = getInstantStateHolder(paramContext);
    return this.mStateHolder;
  }
  
  public CWKeyboardMatch identify(String paramString)
  {
    Iterator localIterator = this.keyboardMatches.iterator();
    while (localIterator.hasNext())
    {
      CWKeyboardMatch localCWKeyboardMatch = (CWKeyboardMatch)localIterator.next();
      if ((localCWKeyboardMatch != null) && (localCWKeyboardMatch.identify(paramString, null))) {
        return localCWKeyboardMatch;
      }
    }
    return null;
  }
  
  public void setHasChange()
  {
    this.mHasChange = true;
  }
  
  private static class Singleton
  {
    static CWImeStateManager INSTANCE = new CWImeStateManager(null);
    
    private Singleton() {}
  }
}
