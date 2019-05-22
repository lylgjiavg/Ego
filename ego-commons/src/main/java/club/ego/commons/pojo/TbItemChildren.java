package club.ego.commons.pojo;

import java.util.Arrays;

import club.ego.pojo.TbItem;

public class TbItemChildren extends TbItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean enough;
	
	private String images[];

	public Boolean getEnough() {
		return enough;
	}
	public void setEnough(Boolean enough) {
		this.enough = enough;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "TbItemChildren [images=" + Arrays.toString(images) + "]";
	}
	
}
