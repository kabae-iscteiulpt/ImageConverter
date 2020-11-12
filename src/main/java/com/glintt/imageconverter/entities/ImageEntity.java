package com.glintt.imageconverter.entities;

public class ImageEntity {

	private String imageString;

	public ImageEntity(String imageString) {
		this.imageString = imageString;
	}

	public ImageEntity() {

	}

	public String getImageString() {
		return imageString;
	}

	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
}
