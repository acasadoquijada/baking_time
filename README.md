# Baking time!

This repo contains all the work done for the **Baking App** project of the Udacity's Android Developer Nanodegree.

## Application description

This app shows different cake recipes and the steps and ingredients needed for baking them.

The user is able to navigate the app selecting the desired recipe and its different steps to obtain the information needed in order to bake this delicious cakes!

### Application details


The first time the application is launched, it obtains the recipe information from the internet and store it in a database. See [Database schema section](https://github.com/acasadoquijada/baking_time#database-schema) for more info about this


The application has three Activities:

* MainActivity
* DetailRecipeActivity
* StepDetailAcivty

The flow between them is the following:

In MainActivity the recipe's name and serving is presented to the user using a Fragment (RecipeListFragment).

Once the user clicks in a recipe. a DetailRecipeActivity is opened. Here the recipe's ingredients and steps are shown using two fragments (IngredientListFragment and StepListFragments). The recipe information is obtained using a ViewModel object. It firs checks the database and if there is no information, it gets the recipe information from the internet. The use of a ViewModel for this purpose guarantees that the information is going to be recovered/stored correctly even if an event such as a screen rotation happens.

The user is able to click in one of the steps to launch a StepDetailActivity containing a video illustrating how to perform the step (VideoFramgent) and a more detail description about it (StepDetailFragment). The user is able to navigate between the steps with a previous and next button. In larger devices such as tablets, the step video and description will be shown as well.

### Database schema

For storing the recipe data in a database Room has been used. For more details about database structure please see the [recipe module](https://github.com/acasadoquijada/baking_time/tree/master/app/src/main/java/com/example/backing_app/recipe) and for more information about the database implementation see the [database module](https://github.com/acasadoquijada/baking_time/tree/master/app/src/main/java/com/example/backing_app/database) 


The database used contains three tables:

| Recipe | Ingredient | Step | Selected recipe |
|:---------------:|:---------------------------:|:---------------------------:|:-------------------:|
| int id //@key | int key //@key | int key //@key autogenerate | int key = 1; //@key |
| String name | float quantity | int id | int index |
| String servings | String measure | String shortDescription | - |
| String name | String ingredientName | String description | - |
| - | int recipeId //@Foreign key | String videoURL | - |
| - | - | int recipeId //@Foreign key | - |

The recipe table doesn't contain any information related to the ingredients or steps. Both, Ingredient and Step, contain a index of their recipe as foreign key.

The Selected Recipe represents the current recipe selected by the user. This is used in the application's widget to retrieve its ingredients

### Android testing

There are four test classes with Intrument tests within the com.example.backing_app(androidTest) package. Some of this tests are specific to small devices (smartphone) and another for large devices(tablets). 

* ***MainActivityTest***: Test in both
* ***MainActivityIntentTest***: Test in both
* ***RecipeDetailActivityTest***: Test in both
* ***RecipeDetailActivityIntentTest***: Test in smartphone (RecipeDetailActiviy doesn't launch StepDetailActiviy in large devices)
* ***StepDetailActivityTest***: Test in smartphone (StepDetailActivity is not accessible in large devices)
* ***StepDetailActivityIntentTest***:Test in smartphone (StepDetailActivity is not accessible in large devices)

### Screenshots

Please see [here](https://github.com/acasadoquijada/baking_time/tree/master/doc/images) the full screenshot set

![mainActivityPortrait](doc/images/mainActivityPortrait.jpg) ![recipeDetailPortrait](doc/images/recipeDetailPortrait.jpg) 

![recipeDetailActivityTabletPortrait](doc/images/recipeDetailTablet.png)

### Relevant libraries used

- [Moshi](https://github.com/square/moshi) for parsing the recipes from JSON

- [ExoPlayer](https://github.com/google/ExoPlayer) for reproducing the step videos

- [dataBinding](https://developer.android.com/topic/libraries/data-binding/start)

### External resources

The image shown where a step doesn't contain a video was obtained from [here](https://videomembertheme.szablonstrony.pl/wp-content/themes/videomembertheme/images/novideo.png) 

The logos shown in the RecipeDetailActivity are the following:

* recipe_book.png. Obtained from [flaticon](https://www.flaticon.com/)  and made by [smalllikeart](https://www.flaticon.com/authors/smalllikeart) 

* cake.png.  Obtained from [flaticon](https://www.flaticon.com/)  and made by [Creaticca Creative Agency](https://www.flaticon.com/authors/creaticca-creative-agency)
