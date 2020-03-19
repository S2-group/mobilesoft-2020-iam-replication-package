package com.labs.merlinbirdid.orm.dao;

import com.labs.merlinbirdid.orm.model.PackModel;
import com.labs.merlinbirdid.orm.model.PackPartnerModel;
import com.labs.merlinbirdid.orm.model.PackTextModel;
import com.labs.merlinbirdid.orm.model.SpeciesPackModel;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.builder.SQLCondition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.Where;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PackDao
{
  public PackDao() {}
  
  public int countPackages(String paramString)
  {
    paramString = new Select().from(SpeciesPackModel.class).where(new SQLCondition[] { Condition.column("speciesCode").is(paramString) }).queryList();
    if (paramString != null) {
      return paramString.size();
    }
    return 0;
  }
  
  public PackModel findByPkgId(String paramString)
  {
    return (PackModel)new Select().from(PackModel.class).where(new SQLCondition[] { Condition.column("pkgId").is(paramString) }).querySingle();
  }
  
  public List<PackModel> getInstalledPackages()
  {
    return new Select().from(PackModel.class).where(new SQLCondition[] { Condition.column("dateInstalled").isNotNull() }).queryList();
  }
  
  public Set<String> getPackageSpecies(String paramString)
  {
    Object localObject = new Select().from(SpeciesPackModel.class).where(new SQLCondition[] { Condition.column("pkgId").is(paramString) }).queryList();
    if (localObject != null)
    {
      paramString = new HashSet(((List)localObject).size());
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramString.add(((SpeciesPackModel)((Iterator)localObject).next()).getSpeciesCode());
      }
      return paramString;
    }
    return null;
  }
  
  public List<PackTextModel> getPackageText(String paramString)
  {
    return new Select().from(PackTextModel.class).where(new SQLCondition[] { Condition.column("pkgId").is(paramString) }).queryList();
  }
  
  public List<PackPartnerModel> getPartners(String paramString)
  {
    return new Select().from(PackPartnerModel.class).where(new SQLCondition[] { Condition.column("pkgId").is(paramString) }).queryList();
  }
  
  public void removeAll()
  {
    new Delete().from(PackModel.class).query();
  }
  
  public void removeAllPartners(String paramString)
  {
    new Delete().from(PackPartnerModel.class).where(new SQLCondition[] { Condition.column("pkgId").is(paramString) }).query();
  }
  
  public void removeAllText()
  {
    new Delete().from(PackTextModel.class).query();
  }
  
  public void removeAllText(String paramString)
  {
    new Delete().from(PackTextModel.class).where(new SQLCondition[] { Condition.column("pkgId").is(paramString) }).query();
  }
  
  public void removeSpecies(String paramString)
  {
    new Delete().from(SpeciesPackModel.class).where(new SQLCondition[] { Condition.column("pkgId").is(paramString) }).query();
  }
  
  public void removeSpecies(String[] paramArrayOfString)
  {
    new Delete().from(SpeciesPackModel.class).where(new SQLCondition[] { Condition.column("speciesCode").in(paramArrayOfString, new Object[0]) }).query();
  }
}
