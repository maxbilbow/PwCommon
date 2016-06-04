package com.maxbilbow.pwcommon.domain.service;

import com.maxbilbow.pwcommon.domain.AbstractDomain;
import com.maxbilbow.pwcommon.domain.dao.GenericPwRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Max on 04/06/2016.
 */
public abstract class AbstractPwService<Entity extends AbstractDomain<ID>,ID extends Serializable> implements GenericPwService<Entity,ID>
{

  @Autowired
  private GenericPwRepository<Entity, ID> mRepository;

  private Class<Entity> mDomainType;

  @PostConstruct
  private void init()
  {
    mDomainType = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  @Override
  public Entity findOne(ID aID)
  {
    return mRepository.findOne(aID);
  }

  @Override
  public List<Entity> findAll()
  {
    return mRepository.findAll();
  }


  @Override
  public void delete(Entity aEntity)
  {
    mRepository.delete(aEntity);
  }

  @Override
  public void delete(ID aID)
  {
    mRepository.delete(aID);
  }

  @Override
  public boolean exists(ID aID)
  {
    return mRepository.exists(aID);
  }

  @Override
  public boolean isEmpty()
  {
    return mRepository.count() == 0;
  }

  @Override
  public long count()
  {
    return mRepository.count();
  }

  protected String getClassName()
  {
    return mDomainType.getSimpleName();
  }


  @Override
  public Entity save(Entity aEntity)
  {
    return mRepository.save(aEntity);
  }

  @Override
  public List<Entity> save(Iterable<Entity> aIterator)
  {
    return mRepository.save(aIterator);
  }

  @Override
  public Entity findMostRecent()
  {
    return mRepository.findMostRecent();
  }

  public abstract Entity createNew();
}
