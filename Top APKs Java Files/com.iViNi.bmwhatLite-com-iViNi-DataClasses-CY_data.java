package com.iViNi.DataClasses;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.carly.lib_main_dataclasses_basic.ResultFromByteExtraction;
import com.iViNi.MainDataManager.UtilsBasic;
import com.iViNi.Utils.FileManager;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CY_data
{
  public static final int bitMaskAllFunc = 126;
  public ArrayList<ZipEntry> allFiles = new ArrayList();
  public int anzDatFS = 0;
  private Hashtable<String, Boolean> bForS;
  final int bit0apkV = 1;
  final int bit1Ecode = 2;
  final int bit2Fcode = 4;
  final int bit3ClearFault = 8;
  final int bit4params = 16;
  public long checkSum1 = 0L;
  public long checkSum2 = 0L;
  public long cs1FS = 0L;
  public long cs2FS = 0L;
  public CY_data cyData;
  public long fileCount = 0L;
  public long filesWithSizeCount = 0L;
  private Hashtable<String, Integer> iForS;
  public ResultFromByteExtraction status;
  
  public CY_data()
  {
    if (this.cyData == null)
    {
      this.cyData = this;
      initIforS();
      initBforS();
    }
  }
  
  private void getCYData_And_unZip(String paramString1, String paramString2, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(paramString1);
        ((StringBuilder)localObject1).append(paramString2);
        paramString1 = new ZipInputStream(new BufferedInputStream(new FileInputStream(((StringBuilder)localObject1).toString())));
        paramString2 = new byte['Ѐ'];
        Object localObject3 = paramString1.getNextEntry();
        if (localObject3 != null)
        {
          this.fileCount += 1L;
          Object localObject2 = ((ZipEntry)localObject3).getName();
          localObject1 = new ZipEntry(((ZipEntry)localObject3).getName());
          long l3 = ((ZipEntry)localObject3).getSize();
          this.checkSum1 += l3;
          ((ZipEntry)localObject1).setComment(((ZipEntry)localObject3).getComment());
          ((ZipEntry)localObject1).setTime(((ZipEntry)localObject3).getTime());
          this.allFiles.add(localObject1);
          int i;
          long l2;
          if (paramBoolean)
          {
            localObject3 = FileManager.getFilePathWithinDocumentsDirectoryUsingFileName("unzipped/").toString();
            if (handleFilenamesContainingDirs((String)localObject3, (String)localObject2)) {
              continue;
            }
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append((String)localObject3);
            localStringBuilder.append((String)localObject2);
            localObject2 = new FileOutputStream(localStringBuilder.toString());
            l1 = 0L;
            i = paramString1.read(paramString2);
            if (i != -1)
            {
              ((FileOutputStream)localObject2).write(paramString2, 0, i);
              l1 += i;
              continue;
            }
            ((FileOutputStream)localObject2).close();
            l2 = l1;
          }
          else
          {
            if (!((ZipEntry)localObject3).isDirectory()) {
              break label369;
            }
            continue;
            i = paramString1.read(paramString2);
            l2 = l1;
            if (i != -1)
            {
              l1 += i;
              continue;
            }
          }
          paramString1.closeEntry();
          if (l3 > 0L)
          {
            this.filesWithSizeCount += 1L;
            ((ZipEntry)localObject1).setSize(l3);
          }
          else
          {
            ((ZipEntry)localObject1).setSize(0L);
          }
          ((ZipEntry)localObject1).setCrc(l2);
          this.checkSum2 += l2;
        }
        else
        {
          paramString1.close();
          return;
        }
      }
      catch (IOException paramString1)
      {
        paramString1.printStackTrace();
        return;
      }
      label369:
      long l1 = 0L;
    }
  }
  
  private boolean handleFilenamesContainingDirs(String paramString1, String paramString2)
  {
    String[] arrayOfString = paramString2.split("/");
    int i = paramString2.length();
    boolean bool = true;
    i = paramString2.charAt(i - 1);
    int j = 0;
    if (i == 47) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      i = arrayOfString.length;
    }
    else
    {
      i = arrayOfString.length - 1;
      bool = false;
    }
    while (j < i)
    {
      paramString2 = arrayOfString[j];
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString1);
      localStringBuilder.append("/");
      localStringBuilder.append(paramString2);
      paramString1 = localStringBuilder.toString();
      paramString2 = new StringBuilder();
      paramString2.append(paramString1);
      paramString2.append("/");
      new File(paramString2.toString()).mkdirs();
      j += 1;
    }
    return bool;
  }
  
  private void initBforS()
  {
    this.bForS = new Hashtable();
    this.bForS.put("0", Boolean.valueOf(true));
    this.bForS.put("1", Boolean.valueOf(false));
    this.bForS.put("2", Boolean.valueOf(false));
    this.bForS.put("3", Boolean.valueOf(false));
    this.bForS.put("4", Boolean.valueOf(true));
    this.bForS.put("5", Boolean.valueOf(true));
    this.bForS.put("6", Boolean.valueOf(true));
    this.bForS.put("7", Boolean.valueOf(false));
    this.bForS.put("8", Boolean.valueOf(true));
    this.bForS.put("9", Boolean.valueOf(false));
    this.bForS.put("a", Boolean.valueOf(false));
    this.bForS.put("b", Boolean.valueOf(true));
    this.bForS.put("c", Boolean.valueOf(false));
    this.bForS.put("d", Boolean.valueOf(true));
    this.bForS.put("e", Boolean.valueOf(false));
    this.bForS.put("f", Boolean.valueOf(true));
    this.bForS.put("g", Boolean.valueOf(true));
    this.bForS.put("h", Boolean.valueOf(true));
    this.bForS.put("i", Boolean.valueOf(true));
    this.bForS.put("j", Boolean.valueOf(false));
    this.bForS.put("k", Boolean.valueOf(true));
    this.bForS.put("l", Boolean.valueOf(false));
    this.bForS.put("m", Boolean.valueOf(true));
    this.bForS.put("n", Boolean.valueOf(false));
    this.bForS.put("o", Boolean.valueOf(true));
    this.bForS.put("p", Boolean.valueOf(true));
    this.bForS.put("q", Boolean.valueOf(false));
    this.bForS.put("r", Boolean.valueOf(false));
    this.bForS.put("s", Boolean.valueOf(false));
    this.bForS.put("t", Boolean.valueOf(true));
    this.bForS.put("u", Boolean.valueOf(false));
    this.bForS.put("v", Boolean.valueOf(true));
    this.bForS.put("w", Boolean.valueOf(false));
    this.bForS.put("x", Boolean.valueOf(true));
    this.bForS.put("y", Boolean.valueOf(true));
    this.bForS.put("z", Boolean.valueOf(false));
  }
  
  private void initIforS()
  {
    this.iForS = new Hashtable();
    this.iForS.put("0", Integer.valueOf(9));
    this.iForS.put("1", Integer.valueOf(0));
    this.iForS.put("2", Integer.valueOf(5));
    this.iForS.put("3", Integer.valueOf(5));
    this.iForS.put("4", Integer.valueOf(8));
    this.iForS.put("5", Integer.valueOf(0));
    this.iForS.put("6", Integer.valueOf(10));
    this.iForS.put("7", Integer.valueOf(4));
    this.iForS.put("8", Integer.valueOf(3));
    this.iForS.put("9", Integer.valueOf(10));
    this.iForS.put("a", Integer.valueOf(2));
    this.iForS.put("b", Integer.valueOf(8));
    this.iForS.put("c", Integer.valueOf(5));
    this.iForS.put("d", Integer.valueOf(3));
    this.iForS.put("e", Integer.valueOf(2));
    this.iForS.put("f", Integer.valueOf(3));
    this.iForS.put("g", Integer.valueOf(2));
    this.iForS.put("h", Integer.valueOf(7));
    this.iForS.put("i", Integer.valueOf(4));
    this.iForS.put("j", Integer.valueOf(8));
    this.iForS.put("k", Integer.valueOf(3));
    this.iForS.put("l", Integer.valueOf(1));
    this.iForS.put("m", Integer.valueOf(8));
    this.iForS.put("n", Integer.valueOf(7));
    this.iForS.put("o", Integer.valueOf(8));
    this.iForS.put("p", Integer.valueOf(3));
    this.iForS.put("q", Integer.valueOf(10));
    this.iForS.put("r", Integer.valueOf(4));
    this.iForS.put("s", Integer.valueOf(6));
    this.iForS.put("t", Integer.valueOf(1));
    this.iForS.put("u", Integer.valueOf(10));
    this.iForS.put("v", Integer.valueOf(7));
    this.iForS.put("w", Integer.valueOf(5));
    this.iForS.put("x", Integer.valueOf(7));
    this.iForS.put("y", Integer.valueOf(8));
    this.iForS.put("z", Integer.valueOf(4));
  }
  
  public boolean apkIsV()
  {
    return true;
  }
  
  public boolean codE()
  {
    return true;
  }
  
  public boolean codF()
  {
    return true;
  }
  
  public CY_data fillWithData(String paramString, PackageManager paramPackageManager)
  {
    UtilsBasic.myLogI("CY_data: ", "fillWithData-> IN ");
    this.checkSum1 = 0L;
    this.checkSum2 = 0L;
    this.fileCount = 0L;
    this.filesWithSizeCount = 0L;
    String str2 = "";
    String str1 = "";
    try
    {
      Iterator localIterator = paramPackageManager.getInstalledApplications(0).iterator();
      paramPackageManager = str2;
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        str2 = localApplicationInfo.packageName;
        if (str2.contains(paramString))
        {
          paramPackageManager = new StringBuilder();
          paramPackageManager.append("package: ");
          paramPackageManager.append(str2);
          paramPackageManager.append(", sourceDir: ");
          paramPackageManager.append(localApplicationInfo.sourceDir);
          UtilsBasic.myLogI("CY_data: ", paramPackageManager.toString());
          str1 = localApplicationInfo.sourceDir;
          paramPackageManager = str2;
        }
      }
      if (paramPackageManager.length() > 0) {
        getCYData_And_unZip(str1, "", false);
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    UtilsBasic.myLogI("CY_data: ", "fillWithData<- OUT");
    return this.cyData;
  }
  
  public boolean getBforS(String paramString)
  {
    try
    {
      boolean bool = ((Boolean)this.bForS.get(paramString)).booleanValue();
      return bool;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public int getIforS(String paramString)
  {
    try
    {
      int i = ((Integer)this.iForS.get(paramString)).intValue();
      return i;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return -1;
  }
  
  public byte setStatus(int paramInt)
  {
    this.status = new ResultFromByteExtraction((byte)paramInt);
    return this.status.theValue;
  }
  
  public void showAll()
  {
    UtilsBasic.myLogI("CY_data: ", String.format(" CYSummary: fileCount=%d filesWithSizeCount=%d checkSum1=%d checkSum2=%d", new Object[] { Long.valueOf(this.fileCount), Long.valueOf(this.filesWithSizeCount), Long.valueOf(this.checkSum1), Long.valueOf(this.checkSum2) }));
    if (this.allFiles != null)
    {
      Iterator localIterator = this.allFiles.iterator();
      while (localIterator.hasNext())
      {
        ZipEntry localZipEntry = (ZipEntry)localIterator.next();
        UtilsBasic.myLogI("CY_data: ", String.format(" name=%s size=%d sizeCRC=%d time=%d", new Object[] { localZipEntry.getName(), Long.valueOf(localZipEntry.getSize()), Long.valueOf(localZipEntry.getCrc()), Long.valueOf(localZipEntry.getTime()) }));
      }
    }
    UtilsBasic.myLogI("CY_data: ", String.format(" allFiles=null", new Object[0]));
  }
  
  public void updateStatus()
  {
    int j;
    if ((this.fileCount == 0L) && (this.checkSum1 == 0L) && (this.checkSum2 == 0L))
    {
      j = this.status.theValue;
      this.status.theValue = ((byte)((0x1 | j) & 0xFF));
      UtilsBasic.myLogI("CY_data: ", String.format("updateStatus: validated, since no apk checksum can be calculated, all values == 0", new Object[0]));
      return;
    }
    if (this.anzDatFS + this.cs1FS + this.cs2FS > 0L) {
      j = 1;
    } else {
      j = 0;
    }
    boolean bool1;
    if (this.fileCount == this.anzDatFS) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    boolean bool2;
    if (this.checkSum1 == this.cs1FS) {
      bool2 = true;
    } else {
      bool2 = false;
    }
    boolean bool3;
    if (this.checkSum2 == this.cs2FS) {
      bool3 = true;
    } else {
      bool3 = false;
    }
    if (j != 0)
    {
      int k;
      if ((bool1) && (bool2) && (bool3)) {
        k = 1;
      } else {
        k = 0;
      }
      int i = this.status.theValue;
      String str1 = String.format("%8s", new Object[] { Integer.toBinaryString(i) }).replace(' ', '0');
      this.status.theValue = ((byte)((i | k) & 0xFF));
      String str2 = String.format("%8s", new Object[] { Integer.toBinaryString(this.status.theValue) }).replace(' ', '0');
      UtilsBasic.myLogI("CY_data: ", String.format("updateStatus: apkIsV=%b now:%d=%s - before=%d=%s", new Object[] { Boolean.valueOf(k), Byte.valueOf(this.status.theValue), str2, Byte.valueOf(i), str1 }));
      UtilsBasic.myLogI("CY_data: ", String.format("updateStatus: fileCount:%b %d anzDatFS=%d - checkSum1:%b %d cs1FS=%d - checkSum2:%b %d cs2FS=%d", new Object[] { Boolean.valueOf(bool1), Long.valueOf(this.fileCount), Integer.valueOf(this.anzDatFS), Boolean.valueOf(bool2), Long.valueOf(this.checkSum1), Long.valueOf(this.cs1FS), Boolean.valueOf(bool3), Long.valueOf(this.checkSum2), Long.valueOf(this.cs2FS) }));
      return;
    }
    UtilsBasic.myLogI("CY_data: ", String.format("updateStatus: NO status update performed since no apk data from the FS has been retrieved yet in this session", new Object[0]));
    UtilsBasic.myLogI("CY_data: ", String.format("updateStatus: fileCount:%b %d anzDatFS=%d - checkSum1:%b %d cs1FS=%d - checkSum2:%b %d cs2FS=%d", new Object[] { Boolean.valueOf(bool1), Long.valueOf(this.fileCount), Integer.valueOf(this.anzDatFS), Boolean.valueOf(bool2), Long.valueOf(this.checkSum1), Long.valueOf(this.cs1FS), Boolean.valueOf(bool3), Long.valueOf(this.checkSum2), Long.valueOf(this.cs2FS) }));
  }
}
