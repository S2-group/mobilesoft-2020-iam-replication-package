package imoblife.toolbox.full.command;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import base.util.PackageUtil;
import java.io.File;
import java.util.List;

public class TransferCommand
  extends ExaminableCommand
{
  public static final String TAG = TransferCommand.class.getSimpleName();
  private int move_amount;
  private long move_size;
  
  public TransferCommand(Context paramContext)
  {
    super(paramContext);
  }
  
  private void retrievePackage()
  {
    List localList = getContext().getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      String str = localPackageInfo.packageName;
      long l = new File(localPackageInfo.applicationInfo.sourceDir).length();
      if (((localPackageInfo.applicationInfo.flags & 0x1) != 1) && (PackageUtil.isMoveable(getContext(), str)) && (PackageUtil.isOnSdCard(getContext(), str)))
      {
        this.move_amount += 1;
        this.move_size += l;
      }
      i += 1;
    }
  }
  
  public void examine()
  {
    retrievePackage();
    onExamined(getContext(), this.move_amount, this.move_size);
  }
  
  public void execute(List... paramVarArgs)
  {
    if (paramVarArgs.length > 0) {}
  }
}
