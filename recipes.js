class Recipe {
    constructor(name, ingredientList, description, steps, prepTime, mealType) {
        this.name = name;
        //Ingredients have to be a list or array, need to loop to save their information as new entries to the database
        this.ingredientList = ingredientList;
        this.description = description;
        //will steps need to be converted from a list to a string first or do that within the constructor
        this.steps = steps;
        this.prepTime = prepTime;
        //
        this.mealType = mealType;

        this.saveRecipe = function() {
            //Save the recipe to the database
            //remove function and automatically do this?
        
        }
    };

    get name() {
        return this.name;
    }

    get ingredientList() {
        return this.ingredientList;
    }

    get description() {
        return this.description;
    }

    get steps() {
        return this.steps;
    }

    get prepTime() {
        return this.prepTime;
    }

    get mealType() {
        return this.mealType;
    }

    //all mutators need to also alter the database to update that data
    set name(name) {
        this.name = name;
    }

    set ingredientList(ingredientList) {
        this.ingredientList = ingredientList;
    }

    set description(description) {
        this.description = description;
    }

    set steps(steps) {
        this.steps = steps;
    }

    set prepTime(prepTime) {
        this.prepTime = prepTime;
    }

    set mealType(mealType) {
        this.mealType = mealType;
    }

    
}