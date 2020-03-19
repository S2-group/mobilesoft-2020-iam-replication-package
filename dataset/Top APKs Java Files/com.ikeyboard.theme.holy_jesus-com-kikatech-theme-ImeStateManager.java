package com.kikatech.theme;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import com.common.util.PackageUtils;
import com.kikatech.theme.core.Type;
import com.kikatech.theme.core.match.KeyboardMatch;
import com.kikatech.theme.core.match.MainKeyboardMatch;
import com.kikatech.theme.core.match.PreInstallKeyboardMatch;
import com.kikatech.theme.core.match.ThemeKeyboardMatch;
import com.kikatech.theme.util.PackageUtil;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ImeStateManager
{
  List<KeyboardMatch> keyboardMatches = new LinkedList();
  private boolean mHasChange = true;
  private StateHolder mStateHolder;
  
  private ImeStateManager() {}
  
  private void clearAllCandidates()
  {
    Iterator localIterator = this.keyboardMatches.iterator();
    while (localIterator.hasNext())
    {
      KeyboardMatch localKeyboardMatch = (KeyboardMatch)localIterator.next();
      if (localKeyboardMatch != null) {
        localKeyboardMatch.clearAllCandidates();
      }
    }
  }
  
  public static ImeStateManager getInstance()
  {
    return Singleton.INSTANCE;
  }
  
  private Pair<KeyboardMatch, String> installedHighestPriorityKeyboard()
  {
    Iterator localIterator = this.keyboardMatches.iterator();
    while (localIterator.hasNext())
    {
      KeyboardMatch localKeyboardMatch = (KeyboardMatch)localIterator.next();
      if (localKeyboardMatch != null)
      {
        String str = localKeyboardMatch.installedHighestPriorityKeyboard();
        if (!TextUtils.isEmpty(str)) {
          return new Pair(localKeyboardMatch, str);
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
      KeyboardMatch localKeyboardMatch = (KeyboardMatch)localIterator.next();
      if (localKeyboardMatch != null) {
        localKeyboardMatch.match(paramString, paramPackageInfo);
      }
    }
  }
  
  private Pair<KeyboardMatch, String> notInstalledHighestPriorityKeyboard()
  {
    Iterator localIterator = this.keyboardMatches.iterator();
    while (localIterator.hasNext())
    {
      KeyboardMatch localKeyboardMatch = (KeyboardMatch)localIterator.next();
      if (localKeyboardMatch != null)
      {
        String str = localKeyboardMatch.notInstalledHighestPriorityKeyboard();
        if (!TextUtils.isEmpty(str)) {
          return new Pair(localKeyboardMatch, str);
        }
      }
    }
    return null;
  }
  
  public String getCandidates(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    paramContext = PackageUtils.getIMEList(paramContext);
    if ((paramContext != null) && (!paramContext.isEmpty()))
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        InputMethodInfo localInputMethodInfo = (InputMethodInfo)paramContext.next();
        if (!TextUtils.isEmpty(localInputMethodInfo.getPackageName()))
        {
          Iterator localIterator = this.keyboardMatches.iterator();
          while (localIterator.hasNext()) {
            if (((KeyboardMatch)localIterator.next()).identify(localInputMethodInfo.getPackageName(), null))
            {
              localStringBuilder.append(localInputMethodInfo.getPackageName());
              localStringBuilder.append(",");
            }
          }
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public StateHolder getInstantStateHolder(Context paramContext)
  {
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      try
      {
        localObject1 = PackageUtil.getActivatedIMEPackageName(paramContext);
        localObject2 = identify((String)localObject1);
        if (localObject2 == null) {
          break label442;
        }
        if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (localObject2 != null))
        {
          if (PackageUtil.getVersionCodeByPkgName(paramContext, (String)localObject1) < ((KeyboardMatch)localObject2).supportVersion((String)localObject1))
          {
            paramContext = new StateHolder(2, (String)localObject1, ((KeyboardMatch)localObject2).type());
            return paramContext;
          }
          paramContext = new StateHolder(4, (String)localObject1, ((KeyboardMatch)localObject2).type());
          continue;
        }
        if (!TextUtils.isEmpty((CharSequence)localObject1)) {
          break label146;
        }
      }
      finally {}
      clearAllCandidates();
      paramContext = PackageUtil.getInputMethodList(paramContext);
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
        paramContext = (KeyboardMatch)((Pair)localObject3).first;
        localObject1 = (String)((Pair)localObject3).second;
      }
      if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (paramContext != null))
      {
        paramContext = new StateHolder(3, (String)localObject1, paramContext.type());
      }
      else
      {
        Object localObject4 = InstalledPackagesHolder.getInstance().getInstalledPackages();
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
              localObject3 = (KeyboardMatch)((Pair)localObject4).first;
              localObject2 = (String)((Pair)localObject4).second;
            }
          }
        }
        if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (localObject3 != null))
        {
          paramContext = new StateHolder(3, (String)localObject2, ((KeyboardMatch)localObject3).type());
        }
        else if ((this.keyboardMatches != null) && (!this.keyboardMatches.isEmpty()))
        {
          paramContext = notInstalledHighestPriorityKeyboard();
          if (paramContext != null)
          {
            localObject1 = (KeyboardMatch)paramContext.first;
            paramContext = new StateHolder(1, (String)paramContext.second, ((KeyboardMatch)localObject1).type());
          }
          else
          {
            paramContext = new StateHolder(0, "", Type.MAIN_KEYBOARD);
          }
        }
        else
        {
          paramContext = new StateHolder(0, "", Type.MAIN_KEYBOARD);
          continue;
          label442:
          localObject1 = "";
        }
      }
    }
  }
  
  public StateHolder getState(Context paramContext)
  {
    this.mStateHolder = getInstantStateHolder(paramContext);
    return this.mStateHolder;
  }
  
  public KeyboardMatch identify(String paramString)
  {
    Iterator localIterator = this.keyboardMatches.iterator();
    while (localIterator.hasNext())
    {
      KeyboardMatch localKeyboardMatch = (KeyboardMatch)localIterator.next();
      if ((localKeyboardMatch != null) && (localKeyboardMatch.identify(paramString, null))) {
        return localKeyboardMatch;
      }
    }
    return null;
  }
  
  public void init(Context paramContext)
  {
    InstalledPackagesHolder.getInstance().init(paramContext);
    this.keyboardMatches.clear();
    this.keyboardMatches.add(new MainKeyboardMatch(paramContext));
    this.keyboardMatches.add(new PreInstallKeyboardMatch(paramContext));
    this.keyboardMatches.add(new ThemeKeyboardMatch(paramContext));
  }
  
  public void setHasChange()
  {
    this.mHasChange = true;
  }
  
  private static class Singleton
  {
    static ImeStateManager INSTANCE = new ImeStateManager(null);
  }
}
