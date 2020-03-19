package com.wavemarket.finder.core.v3.dto.dwd;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TDWDAssetFeatureInfo
  implements Serializable
{
  private boolean active;
  private TAutoReply autoReply;
  private boolean gpsOn;
  private boolean handsFreeOn;
  private List<TClientApplicationDescription> installedApplications;
  protected Date lastHeartbeat;
  private List<String> whitelistApplications;
  private List<TWhitelistContact> whitelistContacts;
  
  public TDWDAssetFeatureInfo() {}
  
  public TDWDAssetFeatureInfo(List<TWhitelistContact> paramList, List<TClientApplicationDescription> paramList1, List<String> paramList2, Date paramDate, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.whitelistContacts = paramList;
    this.installedApplications = paramList1;
    this.whitelistApplications = paramList2;
    this.lastHeartbeat = paramDate;
    this.active = paramBoolean1;
    this.handsFreeOn = paramBoolean2;
  }
  
  public TAutoReply getAutoReply()
  {
    return this.autoReply;
  }
  
  public List<TClientApplicationDescription> getInstalledApplications()
  {
    return this.installedApplications;
  }
  
  public Date getLastHeartbeat()
  {
    return this.lastHeartbeat;
  }
  
  public List<String> getWhitelistApplications()
  {
    return this.whitelistApplications;
  }
  
  public List<TWhitelistContact> getWhitelistContacts()
  {
    return this.whitelistContacts;
  }
  
  public boolean isActive()
  {
    return this.active;
  }
  
  public boolean isGpsOn()
  {
    return this.gpsOn;
  }
  
  public boolean isHandsFreeOn()
  {
    return this.handsFreeOn;
  }
  
  public void setActive(boolean paramBoolean)
  {
    this.active = paramBoolean;
  }
  
  public void setAutoReply(TAutoReply paramTAutoReply)
  {
    this.autoReply = paramTAutoReply;
  }
  
  public void setGpsOn(boolean paramBoolean)
  {
    this.gpsOn = paramBoolean;
  }
  
  public void setHandsFreeOn(boolean paramBoolean)
  {
    this.handsFreeOn = paramBoolean;
  }
  
  public void setInstalledApplications(List<TClientApplicationDescription> paramList)
  {
    this.installedApplications = paramList;
  }
  
  public void setLastHeartbeat(Date paramDate)
  {
    this.lastHeartbeat = paramDate;
  }
  
  public void setWhitelistApplications(List<String> paramList)
  {
    this.whitelistApplications = paramList;
  }
  
  public void setWhitelistContacts(List<TWhitelistContact> paramList)
  {
    this.whitelistContacts = paramList;
  }
  
  public String toString()
  {
    return "TDWDAssetFeatureInfo{active=" + this.active + ", autoReply=" + this.autoReply + ", whitelistContacts=" + this.whitelistContacts + ", installedApplications=" + this.installedApplications + ", whitelistApplications=" + this.whitelistApplications + ", lastHeartbeat=" + this.lastHeartbeat + ", gpsOn=" + this.gpsOn + ", handsFreeOn=" + this.handsFreeOn + '}';
  }
}
