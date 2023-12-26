package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.DiagramComponentException;
import com.capeelectric.model.DiagramComponent;
import com.capeelectric.model.DiagramSymbol;

public interface DiagramService {

	public DiagramComponent addDiagram(DiagramComponent diagramComponent) throws DiagramComponentException;

	public DiagramComponent retrieveDiagramComponent(String userName, String fileName) throws DiagramComponentException;

	public String retrieveFileName(String userName, String fileName) throws DiagramComponentException;

	public List<DiagramComponent> retrieveAllDiagram(String userName) throws DiagramComponentException;

	public DiagramComponent updateDiagram(DiagramComponent diagramComponent) throws DiagramComponentException;

	public List<DiagramSymbol> retrieveAllSymbol();

	public void deleteDiagram(String nodeId) throws DiagramComponentException;

	public void deleteDiagram(Integer id) throws DiagramComponentException;
}
