package com.labs.merlinbirdid.packs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import com.labs.merlinbirdid.app.PacksActivity;
import java.util.List;

public class PackUtil
{
  public PackUtil() {}
  
  public static boolean checkPacks(Context paramContext)
  {
    List localList = new PackDataProvider().getInstalledPackages();
    if ((localList != null) && (!localList.isEmpty())) {
      return true;
    }
    new AlertDialog.Builder(paramContext).setTitle(paramContext.getString(2131165503)).setMessage(paramContext.getString(2131165502)).setPositiveButton(paramContext.getString(2131165500), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        if (!(this.val$context instanceof PacksActivity))
        {
          paramAnonymousDialogInterface = new Intent(this.val$context, PacksActivity.class);
          this.val$context.startActivity(paramAnonymousDialogInterface);
        }
      }
    }).setNegativeButton(paramContext.getString(2131165501), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).create().show();
    return false;
  }
}
