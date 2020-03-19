package com.labs.merlinbirdid.packs;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.LatLng;
import com.labs.merlinbirdid.i18n.LanguageManager;
import com.labs.merlinbirdid.orm.dao.PackDao;
import com.labs.merlinbirdid.orm.model.PackModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Pack
  implements Parcelable
{
  public static final Parcelable.Creator<Pack> CREATOR = new Parcelable.Creator()
  {
    public Pack createFromParcel(Parcel paramAnonymousParcel)
    {
      return new Pack(paramAnonymousParcel);
    }
    
    public Pack[] newArray(int paramAnonymousInt)
    {
      return new Pack[paramAnonymousInt];
    }
  };
  private Date dateAvailable;
  private boolean deprecated;
  private Map<String, PackDescriptor> descriptor = new HashMap();
  private String id;
  private String imageUrl;
  private String md5;
  private boolean nearby;
  private Partner[] partners;
  private ArrayList<LatLng> regionPolygon;
  private long size;
  private long sizeUncompressed;
  private String[] species;
  private String[] speciesToAdd;
  private String[] speciesToRemove;
  private String taxonomyVersion;
  private String updateMd5;
  private long updateSize;
  private long updateSizeUncompressed;
  private int updateVersion;
  private String zipFilePath;
  
  public Pack() {}
  
  protected Pack(Parcel paramParcel)
  {
    this.id = paramParcel.readString();
    this.size = paramParcel.readLong();
    this.sizeUncompressed = paramParcel.readLong();
    this.species = paramParcel.createStringArray();
    this.speciesToAdd = paramParcel.createStringArray();
    this.speciesToRemove = paramParcel.createStringArray();
    this.zipFilePath = paramParcel.readString();
    this.imageUrl = paramParcel.readString();
    this.descriptor = getDescriptorFromParcel(paramParcel);
    this.partners = ((Partner[])paramParcel.createTypedArray(Partner.CREATOR));
    this.md5 = paramParcel.readString();
    this.regionPolygon = paramParcel.createTypedArrayList(LatLng.CREATOR);
    if (paramParcel.readByte() == 1)
    {
      bool1 = true;
      this.nearby = bool1;
      this.updateVersion = paramParcel.readInt();
      this.updateSize = paramParcel.readLong();
      this.updateSizeUncompressed = paramParcel.readLong();
      this.updateMd5 = paramParcel.readString();
      Date localDate = new Date();
      localDate.setTime(paramParcel.readLong());
      this.dateAvailable = localDate;
      if (paramParcel.readByte() != 1) {
        break label223;
      }
    }
    label223:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      this.deprecated = bool1;
      this.taxonomyVersion = paramParcel.readString();
      return;
      bool1 = false;
      break;
    }
  }
  
  private PackDescriptor getDescriptorForDeviceLocale()
  {
    Map localMap = getDescriptor();
    Object localObject = null;
    if (localMap != null)
    {
      PackDescriptor localPackDescriptor = (PackDescriptor)localMap.get(LanguageManager.getDeviceLocaleString());
      localObject = localPackDescriptor;
      if (localPackDescriptor == null) {
        localObject = (PackDescriptor)localMap.get("en_US");
      }
    }
    return localObject;
  }
  
  private Map<String, PackDescriptor> getDescriptorFromParcel(Parcel paramParcel)
  {
    HashMap localHashMap = new HashMap();
    int j = paramParcel.readInt();
    ClassLoader localClassLoader = PackDescriptor.class.getClassLoader();
    int i = 0;
    while (i < j)
    {
      localHashMap.put(paramParcel.readString(), (PackDescriptor)paramParcel.readParcelable(localClassLoader));
      i += 1;
    }
    return localHashMap;
  }
  
  private void writeDescriptorToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.descriptor.size());
    Object localObject = this.descriptor.entrySet();
    if (localObject != null)
    {
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
        paramParcel.writeString((String)localEntry.getKey());
        paramParcel.writeParcelable((Parcelable)localEntry.getValue(), paramInt);
      }
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getCreditText()
  {
    PackDescriptor localPackDescriptor = getDescriptorForDeviceLocale();
    if (localPackDescriptor != null) {
      return localPackDescriptor.getCreditText();
    }
    return null;
  }
  
  public Date getDateAvailable()
  {
    return this.dateAvailable;
  }
  
  public String getDescription()
  {
    PackDescriptor localPackDescriptor = getDescriptorForDeviceLocale();
    if (localPackDescriptor != null) {
      return localPackDescriptor.getDescription();
    }
    return null;
  }
  
  public Map<String, PackDescriptor> getDescriptor()
  {
    return this.descriptor;
  }
  
  public String getDownloadFileName(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getId()).append(".");
    if (paramBoolean) {
      localStringBuilder.append(getUpdateVersion()).append(".");
    }
    localStringBuilder.append("zip");
    return localStringBuilder.toString();
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public String getImageUrl()
  {
    return this.imageUrl;
  }
  
  public String getLongDescription()
  {
    PackDescriptor localPackDescriptor = getDescriptorForDeviceLocale();
    if (localPackDescriptor != null) {
      return localPackDescriptor.getLongDescription();
    }
    return null;
  }
  
  public String getMd5()
  {
    return this.md5;
  }
  
  public String getName()
  {
    PackDescriptor localPackDescriptor = getDescriptorForDeviceLocale();
    if (localPackDescriptor != null) {
      return localPackDescriptor.getName();
    }
    return null;
  }
  
  public String getPartnerText()
  {
    PackDescriptor localPackDescriptor = getDescriptorForDeviceLocale();
    if (localPackDescriptor != null) {
      return localPackDescriptor.getPartnerText();
    }
    return null;
  }
  
  public Partner[] getPartners()
  {
    return this.partners;
  }
  
  public ArrayList<LatLng> getRegionPolygon()
  {
    return this.regionPolygon;
  }
  
  public long getSize()
  {
    return this.size;
  }
  
  public long getSizeUncompressed()
  {
    return this.sizeUncompressed;
  }
  
  public String[] getSpecies()
  {
    return this.species;
  }
  
  public String[] getSpeciesToAdd()
  {
    return this.speciesToAdd;
  }
  
  public String[] getSpeciesToRemove()
  {
    return this.speciesToRemove;
  }
  
  public String getTaxonomyVersion()
  {
    return this.taxonomyVersion;
  }
  
  public String getUpdateMd5()
  {
    return this.updateMd5;
  }
  
  public long getUpdateSize()
  {
    return this.updateSize;
  }
  
  public long getUpdateSizeUncompressed()
  {
    return this.updateSizeUncompressed;
  }
  
  public int getUpdateVersion()
  {
    return this.updateVersion;
  }
  
  public String getWhatsNewText()
  {
    PackDescriptor localPackDescriptor = getDescriptorForDeviceLocale();
    if (localPackDescriptor != null) {
      return localPackDescriptor.getWhatsNewText();
    }
    return null;
  }
  
  public String getZipFilePath()
  {
    return this.zipFilePath;
  }
  
  public boolean isDeprecated()
  {
    return this.deprecated;
  }
  
  public boolean isInstalled()
  {
    return new PackDataProvider().getInstalledPackages().contains(getId());
  }
  
  public boolean isNearby()
  {
    return this.nearby;
  }
  
  public boolean isUpdate()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.dateAvailable != null)
    {
      bool1 = bool2;
      if (this.updateVersion > 0)
      {
        PackModel localPackModel = new PackDao().findByPkgId(getId());
        bool1 = bool2;
        if (localPackModel != null)
        {
          bool1 = bool2;
          if (localPackModel.getDateInstalled() != null)
          {
            bool1 = bool2;
            if (this.dateAvailable.compareTo(localPackModel.getDateInstalled()) > 0) {
              bool1 = true;
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public void setDateAvailable(Date paramDate)
  {
    this.dateAvailable = paramDate;
  }
  
  public void setDeprecated(boolean paramBoolean)
  {
    this.deprecated = paramBoolean;
  }
  
  public void setDescriptor(Map<String, PackDescriptor> paramMap)
  {
    this.descriptor = paramMap;
  }
  
  public void setId(String paramString)
  {
    this.id = paramString;
  }
  
  public void setImageUrl(String paramString)
  {
    this.imageUrl = paramString;
  }
  
  public void setMd5(String paramString)
  {
    this.md5 = paramString;
  }
  
  public void setNearby(boolean paramBoolean)
  {
    this.nearby = paramBoolean;
  }
  
  public void setPartners(Partner[] paramArrayOfPartner)
  {
    this.partners = paramArrayOfPartner;
  }
  
  public void setRegionPolygon(ArrayList<LatLng> paramArrayList)
  {
    this.regionPolygon = paramArrayList;
  }
  
  public void setSize(long paramLong)
  {
    this.size = paramLong;
  }
  
  public void setSizeUncompressed(long paramLong)
  {
    this.sizeUncompressed = paramLong;
  }
  
  public void setSpecies(String[] paramArrayOfString)
  {
    this.species = paramArrayOfString;
  }
  
  public void setSpeciesToAdd(String[] paramArrayOfString)
  {
    this.speciesToAdd = paramArrayOfString;
  }
  
  public void setSpeciesToRemove(String[] paramArrayOfString)
  {
    this.speciesToRemove = paramArrayOfString;
  }
  
  public void setTaxonomyVersion(String paramString)
  {
    this.taxonomyVersion = paramString;
  }
  
  public void setUpdateMd5(String paramString)
  {
    this.updateMd5 = paramString;
  }
  
  public void setUpdateSize(long paramLong)
  {
    this.updateSize = paramLong;
  }
  
  public void setUpdateSizeUncompressed(long paramLong)
  {
    this.updateSizeUncompressed = paramLong;
  }
  
  public void setUpdateVersion(int paramInt)
  {
    this.updateVersion = paramInt;
  }
  
  public void setZipFilePath(String paramString)
  {
    this.zipFilePath = paramString;
  }
  
  public String toString()
  {
    return "Pack{dateAvailable=" + this.dateAvailable + ", id='" + this.id + '\'' + ", size=" + this.size + ", sizeUncompressed=" + this.sizeUncompressed + ", species=" + Arrays.toString(this.species) + ", speciesToAdd=" + Arrays.toString(this.speciesToAdd) + ", speciesToRemove=" + Arrays.toString(this.speciesToRemove) + ", zipFilePath='" + this.zipFilePath + '\'' + ", descriptor=" + this.descriptor + ", md5=" + this.md5 + '}';
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    paramParcel.writeString(this.id);
    paramParcel.writeLong(this.size);
    paramParcel.writeLong(this.sizeUncompressed);
    paramParcel.writeStringArray(this.species);
    paramParcel.writeStringArray(this.speciesToAdd);
    paramParcel.writeStringArray(this.speciesToRemove);
    paramParcel.writeString(this.zipFilePath);
    paramParcel.writeString(this.imageUrl);
    writeDescriptorToParcel(paramParcel, paramInt);
    paramParcel.writeTypedArray(this.partners, paramInt);
    paramParcel.writeString(this.md5);
    paramParcel.writeTypedList(this.regionPolygon);
    long l;
    if (this.nearby)
    {
      paramInt = 1;
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeInt(this.updateVersion);
      paramParcel.writeLong(this.updateSize);
      paramParcel.writeLong(this.updateSizeUncompressed);
      paramParcel.writeString(this.updateMd5);
      if (this.dateAvailable == null) {
        break label195;
      }
      l = this.dateAvailable.getTime();
      label160:
      paramParcel.writeLong(l);
      if (!this.deprecated) {
        break label201;
      }
    }
    label195:
    label201:
    for (paramInt = i;; paramInt = 0)
    {
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeString(this.taxonomyVersion);
      return;
      paramInt = 0;
      break;
      l = 0L;
      break label160;
    }
  }
}
