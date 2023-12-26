package com.capeelectric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "diagram_symbol")

public class DiagramSymbol {

	@Id
	private int Id;
	
	@Column(name = "image_source")
	private String imageSource;
	
	@Column(name = "image_name")
	private String imageName;
	
	

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

    public String getImageSource() {
		return imageSource;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
}

