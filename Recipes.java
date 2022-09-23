import java.io.Serializable;

public class Recipes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String ingredientList;
	private String description;
	private String steps;
	private String type;
	private int prepTime;
	
	//Constructors
	//In each constructor you need to save the data to a file in the appropriate folder
	Recipes() {
		//make a generic entry
		
	}
	
	Recipes(String name, String list, String description, String steps, String type, int prepTime) {
		this.name = name;
		this.ingredientList = list;
		this.description = description;
		this.steps = steps;
		this.type = type;
		this.prepTime = prepTime;	
	}
	
	//Accessors
	public String getName() {
		return this.name;
	}
	
	public String getIngredientList() {
		return this.ingredientList;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getSteps() {
		return this.steps;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getPrepTime() {
		return this.prepTime;	
	}
	
	//Mutators
	public void setName(String name) {
		this.name = name;
	}
		
	public void setIngredientList(String newList) {
		this.ingredientList = newList;
	}	
		
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
		
	public void setSteps(String newSteps) {
		this.steps = newSteps;
	}
		
	public void setType(String newType) {
		this.type = newType;
	}
		
	public void setPrepTime(int newPrepTime) {
		this.prepTime = newPrepTime;	
	}
		
	public String toString() {
		return this.description;
	}

}
