import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;



public class Meal_Planner extends Application{
	//main fields
	private TextField tfMeals = new TextField();
	private TextField tfDays = new TextField();
	private CheckBox cbBreakfast = new CheckBox("Breakfast");
	private CheckBox cbLunch = new CheckBox("Lunch");
	private CheckBox cbDinner = new CheckBox("Dinner");
	private CheckBox cbSnacks = new CheckBox("Snacks");
	private Schedule lastSchedule = null;
	
	//recipe fields
	private TextField tfName = new TextField();
	private TextField tfPrepTime = new TextField();
	private TextArea taDescription = new TextArea();
	private TextArea taIngredients = new TextArea();
	private TextArea taSteps = new TextArea();
	private CheckBox cbBreakfast1 = new CheckBox("Breakfast");
	private CheckBox cbLunch1 = new CheckBox("Lunch");
	private CheckBox cbDinner1 = new CheckBox("Dinner");
	private CheckBox cbSnacks1 = new CheckBox("Snacks");
	
	//See recipe info fields
	private TextField tfRName = new TextField();
	private TextArea taRSteps = new TextArea();
	private TextArea taRIngredients = new TextArea();
	private CheckBox cbBreakfast2 = new CheckBox("Breakfast");
	private CheckBox cbLunch2 = new CheckBox("Lunch");
	private CheckBox cbDinner2 = new CheckBox("Dinner");
	private CheckBox cbSnacks2 = new CheckBox("Snacks");
	//Tabs
	private Tab tab1 = new Tab("Planning");
	private Tab tab2 = new Tab("Add Recipes");
	private Tab tab3 = new Tab("Meal Plan");
	private Tab tab4 = new Tab("See Recipes");
	private DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	
	
	
	
	
	public void start(Stage primaryStage) {
		TabPane pane = new TabPane();
		
		pane.setPadding(new Insets(10,10,10,10));
		pane.getTabs().add(tab1);
		pane.getTabs().add(tab3);
		pane.getTabs().add(tab4);
		pane.getTabs().add(tab2);
		
		tab1.setContent(mainPane());
		tab2.setContent(recipePane());
		tab4.setContent(seeRecipe());
		
		Scene scene = new Scene(pane, 800, 600);
		primaryStage.setTitle("Meal Planner");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

	private GridPane mainPane() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(2);
		pane.setVgap(2);
		
		pane.add(new Label("Number of Days: "), 0, 1);
		pane.add(tfDays, 1, 1);
		pane.add(new Label("Number of Meals per Day: "), 0, 2);
		pane.add(tfMeals, 1, 2);
		pane.add(new Label("Types of Meals:"), 0, 3);
		pane.add(cbBreakfast, 1, 3);
		pane.add(cbLunch, 2, 3);
		pane.add(cbDinner, 1, 4);
		pane.add(cbSnacks, 2, 4);
		
		Button btSchedule = new Button("Create Schedule");
		pane.add(btSchedule, 0, 6);
		GridPane.setHalignment(btSchedule, HPos.RIGHT);
		btSchedule.setOnAction(e -> createSchedule());
		
		return pane;
		
	}
	
	private GridPane recipePane() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(2);
		pane.setVgap(2);
		
		//labels and text boxes/areas
		pane.add(new Label("Enter information for a new recipe: "), 0, 0, 2, 1);
		pane.add(new Label("Name(no spaces): "), 0, 1);
		pane.add(tfName, 1, 1);
		pane.add(new Label("Ingredient List: "), 0, 6);
		pane.add(taIngredients, 1, 6);
		pane.add(new Label("Steps: "), 0, 7);
		pane.add(taSteps, 1, 7);
		pane.add(new Label("Description: "), 0, 5);
		pane.add(taDescription, 1, 5);
		pane.add(new Label("Type: "), 0, 2);
		pane.add(new Label("Prep Time (Minutes): "), 0, 4);
		pane.add(tfPrepTime, 1, 4);
		pane.add(cbBreakfast1, 1, 2);
		GridPane.setHalignment(cbBreakfast1, HPos.LEFT);
		pane.add(cbLunch1, 1, 2);
		GridPane.setHalignment(cbLunch1, HPos.CENTER);
		pane.add(cbDinner1, 1, 3);
		GridPane.setHalignment(cbDinner1, HPos.LEFT);
		pane.add(cbSnacks1, 1, 3);
		GridPane.setHalignment(cbSnacks1, HPos.CENTER);
		Button btRecipe = new Button("Add New Recipe");
		pane.add(btRecipe, 0, 8);
		btRecipe.setOnAction(e -> addRecipe());
		
		return pane;
	}
	
	private GridPane planPane(int numDays, int numMeals) {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(20);
		pane.setVgap(20);
		int entry = 0;
		Recipes[] list = this.lastSchedule.getlist();
		for (int a = 0; a < numDays; a++) {
			pane.add(new Label("Day " + (a+1)), a, 0);
			for (int b = 1; b <= numMeals; b++) {
				String meal = list[entry].getName();
				pane.add(new Label(meal), a, b);
				entry++;
			}
		}
		return pane;
	}
	
	private GridPane seeRecipe() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(2);
		pane.setVgap(2);
		Label intro = new Label("Enter recipe name to see steps and ingredients: ");
		intro.setWrapText(true);
		
		pane.add(intro, 0, 0);
		pane.add(tfRName, 1, 0);
		pane.add(new Label("Types of Meals:"), 0, 2);
		pane.add(cbBreakfast2, 1, 2);
		pane.add(cbLunch2, 1, 2);
		GridPane.setHalignment(cbLunch2, HPos.CENTER);
		pane.add(cbDinner2, 1, 3);
		pane.add(cbSnacks2, 1, 3);
		GridPane.setHalignment(cbSnacks2, HPos.CENTER);
		pane.add(new Label("Steps: "), 0, 5);
		pane.add(taRSteps, 1, 5);
		pane.add(new Label("Ingredients: "), 0, 6);
		pane.add(taRIngredients, 1, 6);
		
		Button btSee = new Button("Get Recipe");
		btSee.setOnAction(e -> seeRecipeInfo());
		pane.add(btSee, 1, 7);
		Button btClear = new Button("Clear");
		btClear.setOnAction(e -> clearSeeRecipe());
		pane.add(btClear, 1, 7);
		GridPane.setHalignment(btClear, HPos.CENTER);
		
		return pane;
	}
	
	private void clearSeeRecipe() {
		tfRName.setText("");
		cbBreakfast2.setSelected(false);
		cbLunch2.setSelected(false);
		cbDinner2.setSelected(false);
		cbSnacks2.setSelected(false);
		taRSteps.setText("");
		taRIngredients.setText("");

	}

	private void seeRecipeInfo() {
		String fileName;
		String name = tfRName.getText();
		Recipes recipe = null;
		if (cbBreakfast2.isSelected()) {
			fileName = "Recipes\\Breakfast\\" + name;
			recipe = (Recipes) ReadObjectFromFile(fileName);
			
		}
		else if (cbLunch2.isSelected()) {
			fileName = "Recipes\\Lunch\\" + name;
			recipe = (Recipes) ReadObjectFromFile(fileName);
			
		}
		else if (cbDinner2.isSelected()) {
			fileName = "Recipes\\Dinner\\" + name;
			recipe = (Recipes) ReadObjectFromFile(fileName);
			
		}
		else if (cbSnacks2.isSelected()) {
			fileName = "Recipes\\Snacks\\" + name;
			recipe = (Recipes) ReadObjectFromFile(fileName);
		}
		
		this.taRSteps.setText(recipe.getSteps());
		this.taRIngredients.setText(recipe.getIngredientList());
	}

	private void addRecipe() {
		String name = tfName.getText();
		String list = taIngredients.getText();
		String desc = taDescription.getText();
		String steps = taSteps.getText();
		String type = null;
		if(cbBreakfast1.isSelected()) {
			type = "Breakfast";
		}
		if(cbLunch1.isSelected()) {
			type ="Lunch";
		}
		if(cbDinner1.isSelected()) {
			type = "Dinner";
		}
		if(cbSnacks1.isSelected()) {
			type = "Snacks";
		}
		int prepTime = Integer.parseInt(tfPrepTime.getText());
		
		try (FileOutputStream fos = new FileOutputStream("Recipes\\" + type +"\\"+ name);
			     ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			    // create new recipe
			    Recipes newRecipe = new Recipes(name, list, desc, steps, type, prepTime);

			    // write object to file
			    oos.writeObject(newRecipe);

			} catch (IOException ex) {
			    ex.printStackTrace();
			}
		tfName.setText("");
		taIngredients.setText("");
		taDescription.setText("");
		taSteps.setText("");
		tfPrepTime.setText("");
		cbBreakfast1.setSelected(false);
		cbLunch1.setSelected(false);
		cbDinner1.setSelected(false);
		cbSnacks1.setSelected(false);

	}

	private void createSchedule() {
		int numMeals = Integer.parseInt(tfMeals.getText());
		int numDays = Integer.parseInt(tfDays.getText());
		boolean breakfast = cbBreakfast.isSelected();
		boolean lunch = cbBreakfast.isSelected();
		boolean dinner = cbBreakfast.isSelected();
		boolean snacks = cbBreakfast.isSelected();
		String fileName = "\\" + this.timeStampPattern.format(java.time.LocalDateTime.now());
		//create the schedule
		this.lastSchedule = new Schedule(fileName, numDays, numMeals, breakfast, lunch, dinner, snacks);	
		tab3.setContent(planPane(numDays,numMeals));
		saveSchedule();

	}
	
	private void saveSchedule() {
		try (FileOutputStream fos = new FileOutputStream("Schedules" +this.lastSchedule.getName());
			     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			    // write object to file
			    oos.writeObject(this.lastSchedule);

			} catch (IOException ex) {
			    ex.printStackTrace();
			}
	}
	
	public Object ReadObjectFromFile(String fileName) {
   	 
        try {
 
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            Recipes obj = (Recipes) objectIn.readObject();
 
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }



	public static void main(String[] args) {
		Application.launch(args);
	}

}
