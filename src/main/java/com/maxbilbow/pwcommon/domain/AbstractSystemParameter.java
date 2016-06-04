package com.maxbilbow.pwcommon.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by Max on 04/06/2016.
 */
@MappedSuperclass
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public abstract class AbstractSystemParameter extends AbstractDomain<Long>
{

  private String mName;
  private String mValue;

  public String getName()
  {
    return mName;
  }

  public void setName(String aName)
  {
    mName = aName;
  }

  public String getValue()
  {
    return mValue;
  }

  public void setValue(String aValue)
  {
    mValue = aValue;
  }
}
