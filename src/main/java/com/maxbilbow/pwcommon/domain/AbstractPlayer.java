package com.maxbilbow.pwcommon.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by Max on 04/06/2016.
 */
@MappedSuperclass
public class AbstractPlayer extends AbstractDomain<Long>
{
  protected String mName;

  protected String mBio;

  public void setName(String aName)
  {
    mName = aName;
  }

  /**
   * Whatever for now. This may link the user to their gameplay?
   * This could be a descriptive bio as seen on nationstates.
   * It should be progmatically determined using the campaign manager's reputation.
   */
  @Column(columnDefinition = "varchar(max)")
  public String getBio()
  {
    return mBio;
  }
}
