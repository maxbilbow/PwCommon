package com.maxbilbow.pwcommon.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * Created by Max on 21/05/2016.
 */
@MappedSuperclass
public abstract class AbstractDomain<ID extends Serializable>
{
  public static final Integer VERSION = 0;

  private static final ThreadLocal<Gson> GSON = new ThreadLocal()
  {
    @Override
    protected Object initialValue()
    {
      return new Gson();
    }
  };

  private ID mPk;
  private Timestamp mCreatedOn;
  private Timestamp mLastUpdated;
  private Integer mDbVersion;

  @Id
  @GeneratedValue
  public ID getPk()
  {
    return mPk;
  }

  public void setPk(ID aPk)
  {
    mPk = aPk;
  }

  @Column(nullable = false)
  public Timestamp getLastUpdated()
  {
    return mLastUpdated;
  }

  public void setLastUpdated(Timestamp aLastUpdated)
  {
    mLastUpdated = aLastUpdated;
  }

  @PrePersist
  protected void onCreate() {
    mCreatedOn = mLastUpdated = Timestamp.from(Instant.now());
    mDbVersion = VERSION;
  }

  @PreUpdate
  protected void onUpdate()
  {
    mLastUpdated = Timestamp.from(Instant.now());
  }

  @Column(nullable = false)
  public Timestamp getCreatedOn()
  {
    return mCreatedOn;
  }

  public void setCreatedOn(Timestamp aCreatedOn)
  {
    mCreatedOn = aCreatedOn;
  }

  @Column(nullable = false)
  public Integer getDbVersion()
  {
    return mDbVersion;
  }

  public void setDbVersion(Integer aDbVersion)
  {
    mDbVersion = aDbVersion;
  }

  @Transient
  public String toJSON()
  {
    return GSON.get().toJson(this,this.getClass());
  }
}
