package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.DiagramSymbol;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Repository
public interface DiagramSymbolRepository extends CrudRepository<DiagramSymbol, Integer> {

}
