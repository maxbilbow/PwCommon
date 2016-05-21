package com.maxbilbow.pw.domain;

import javax.persistence.MappedSuperclass;

/**
 * Created by Max on 21/05/2016.
 */
@MappedSuperclass
public abstract class AbstractDomain<ID extends Number>
{

  private Number mPk;

}
