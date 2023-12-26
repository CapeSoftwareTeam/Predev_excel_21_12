/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.DiagramComponent;
import com.capeelectric.model.DiagramSymbol;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface DiagramRepository extends CrudRepository<DiagramComponent, Integer> {
	public Optional<DiagramComponent> findByUserNameAndFileName(String userName, String fileName);

	public DiagramComponent findByUserNameAndDiagramId(String userName, Integer diagramId);

	public List<DiagramComponent> findByUserName(String userName);

//	public void deleteByNodeId(String nodeId);

}
