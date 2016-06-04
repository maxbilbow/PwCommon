package com.maxbilbow.pwcommon.domain.service;

import com.maxbilbow.pwcommon.domain.AbstractDomain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Max on 04/06/2016.
 */
public interface GenericPwService<Entity extends AbstractDomain<ID>, ID extends Serializable>
{
  List<Entity> findAll();

  Entity findOne(ID aID);

  void delete(ID aID);

  void delete(Entity aEntity);

  boolean exists(ID aID);

  boolean isEmpty();

  long count();

  Entity createNew();

  Entity save(Entity aEntity);

  List<Entity> save(Iterable<Entity> aIterator);

  Entity findMostRecent();
}
