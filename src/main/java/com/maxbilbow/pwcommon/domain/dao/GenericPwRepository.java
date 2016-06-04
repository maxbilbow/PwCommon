package com.maxbilbow.pwcommon.domain.dao;

import com.maxbilbow.pwcommon.domain.AbstractDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

/**
 * Created by Max on 04/06/2016.
 */
public interface GenericPwRepository<E extends AbstractDomain<ID>, ID extends Serializable>
        extends JpaRepository<E,ID>
{

  @Query("SELECT MAX(e) FROM #{#entityName} e ORDER BY e.lastUpdated DESC")
  E findMostRecent();

//  @Query("SELECT MAX(e) FROM #{#entityName} e WHERE e.uniqueName = ?1")
//  E findByUniqueName(String aUniqueName);
}
