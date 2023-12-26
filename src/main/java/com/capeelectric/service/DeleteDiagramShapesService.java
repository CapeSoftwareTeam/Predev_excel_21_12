package com.capeelectric.service;

import com.capeelectric.exception.DeleteDiagramShapesException;

public interface DeleteDiagramShapesService {

	public void deleteDiagramShapes(String nodeId) throws DeleteDiagramShapesException;

}
