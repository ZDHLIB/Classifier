package zdhlib.feature;

import java.util.HashMap;

public class Feature {

	private String category = "";
	private HashMap<String, Float> feature = new HashMap<String, Float>();
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public HashMap<String, Float> getFeature() {
		return feature;
	}
	public void setFeature(HashMap<String, Float> feature) {
		this.feature = feature;
	} 
	
	
		
}
