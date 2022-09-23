import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Random;

public class Schedule implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Class to save generated meal plans
	private String fileName;
	private int numDays;
	private int numMeals;
	private boolean breakfast = true;
	private boolean lunch = true;
	private boolean dinner = true;
	private boolean snacks = false;
	private Recipes[] rList;
	
	//Constructors
	//In each constructor you need to save the data to a file in the appropriate folder
	Schedule() {
		//make a generic entry		
	}
	
	Schedule(String fileName, int numDays, int numMeals, boolean breakfast, boolean lunch, boolean dinner, boolean snacks) {
		this.fileName = fileName;
		this.numDays = numDays;
		this.numMeals = numMeals;
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
		this.snacks = snacks;
		this.rList = buildSchedule();
		
	}

	//Accessors
	public int getNumDays() {
		return this.numDays;
	}
	
	public int getNumMeals() {
		return this.numMeals;
	}
	
	public boolean getBreakfast() {
		return this.breakfast;
	}
	
	public boolean getLunch() {
		return this.lunch;
	}
	
	public boolean getDinner() {
		return this.dinner;
	}
	
	public boolean getSnacks() {
		return this.snacks;
	}
	
	public String getName() {
		return this.fileName;
	}
	public Recipes[] getlist() {
		return this.rList;
	}
	
	//This class should not need mutators as a new instance will be created if anything needs to be changed 
	public Recipes[] buildSchedule() {
		int total = this.numDays * this.numMeals;
		Random rand = new Random();

		File bDir = new File("Recipes\\Breakfast");
		File [] bRecipes = bDir.listFiles();
		File lDir = new File("Recipes\\Lunch");
		File [] lRecipes = lDir.listFiles();
		File dDir = new File("Recipes\\Dinner");
		File [] dRecipes = dDir.listFiles();
		File sDir = new File("Recipes\\Snacks");
		File [] sRecipes = sDir.listFiles();
		int bLen = bRecipes.length;
		int lLen = lRecipes.length;
		int dLen = dRecipes.length;
		int sLen = sRecipes.length;
		
		File[] allRecipes = new File[bLen + lLen + dLen+ sLen];
		System.arraycopy(bRecipes, 0, allRecipes, 0, bLen);
		System.arraycopy(lRecipes, 0, allRecipes, bLen, dLen);
		System.arraycopy(dRecipes, 0, allRecipes, bLen + lLen, dLen);
		System.arraycopy(sRecipes, 0, allRecipes, bLen + lLen + dLen, sLen);
		
		
		Recipes[] list = new Recipes[total];
		int entry = 0;
		for (int i = 0; i < this.numDays; i++) {
			for (int x = 0; x < this.numMeals; x++) {
				if (this.breakfast && x ==0) {
					File file = bRecipes[rand.nextInt(bRecipes.length)];
					Recipes add = (Recipes) ReadObjectFromFile(file); 
					list[entry] = add;	
					entry++;				
				}
				else if (this.lunch && x <2) {
					File file = lRecipes[rand.nextInt(lRecipes.length)];
					Recipes add = (Recipes) ReadObjectFromFile(file); 
					list[entry] = add;	
					entry++;				
				}
				else if (this.dinner && x < 3) {
					File file = dRecipes[rand.nextInt(dRecipes.length)];
					Recipes add = (Recipes) ReadObjectFromFile(file); 
					list[entry] = add;	
					entry++;	
				}
				else if (this.snacks) {
					File file = sRecipes[rand.nextInt(sRecipes.length)];
					Recipes add = (Recipes) ReadObjectFromFile(file); 
					list[entry] = add;	
					entry++;	
				}
				else {
					File file = allRecipes[rand.nextInt(allRecipes.length)];
					Recipes add = (Recipes) ReadObjectFromFile(file); 
					list[entry] = add;	
					entry++;					
				}			
			}
		}			
		return list;
		
	}
	
	public Object ReadObjectFromFile(File file) {
   	 
        try {
 
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            Recipes obj = (Recipes) objectIn.readObject();
 
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
	

}
