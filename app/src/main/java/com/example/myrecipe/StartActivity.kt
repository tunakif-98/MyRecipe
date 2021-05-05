package com.example.myrecipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myrecipe.database.RecipeDatabase
import com.example.myrecipe.entities.Categories
import com.example.myrecipe.entities.Recipes
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.coroutines.launch
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.io.IOException

class StartActivity : BaseActivity(), EasyPermissions.RationaleCallbacks, EasyPermissions.PermissionCallbacks {

    companion object{
        var recipeTypes =  ArrayList<String>()
    }

    private var READ_STORAGE_PERM = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        readStorage()

        btnStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getCategories(){
        launch {
            this.let {
                parseRecipeXML()
                val imageURLCategories = arrayListOf<String>()
                imageURLCategories.add("https://www.themealdb.com/images/ingredients/Beef.png")
                imageURLCategories.add("https://www.themealdb.com/images/ingredients/chicken.png")
                imageURLCategories.add("https://www.themealdb.com/ingredient/407-Banana")

                val imageURLRecipes = arrayListOf<String>()
                imageURLRecipes.add("https://www.themealdb.com/images/media/meals/vvpprx1487325699.jpg")
                imageURLRecipes.add("https://www.themealdb.com/images/media/meals/tyywsw1505930373.jpg")
                imageURLRecipes.add("https://www.themealdb.com/images/media/meals/sywswr1511383814.jpg")

                val recipeNames = arrayListOf<String>()
                recipeNames.add("Beef Wellington")
                recipeNames.add("Chicken Karaage")
                recipeNames.add("Banana Pancakes")

                val recipeIngredients = arrayListOf<String>()
                recipeIngredients.add("400g Mushrooms\n" +
                        "1-2 tbsp English mustard\n" +
                        "Dash Olive Oil\n" +
                        "750g Beef fillet\n" +
                        "6-8 slices Parma ham\n" +
                        "500g Puff pastry\n" +
                        "Dusting flour\n" +
                        "2 beaten Egg yolks\n")
                recipeIngredients.add("450g Boneless Chicken thigh\n" +
                        "1 tablespoon Ginger\n" +
                        "1 clove Garlic\n" +
                        "2 tsp Soy sauce\n" +
                        "1 tsp Sake\n" +
                        "2 tsp Granulated sugar\n" +
                        "1/3 cup Potato starch\n" +
                        "1/3 cup Vegetable oil\n" +
                        "1/3 cup Lemon\n")
                recipeIngredients.add("1 large Banana\n" +
                        "2 medium Eggs\n" +
                        "Pinch Baking powder\n" +
                        "Sprinkling Vanilla extract\n" +
                        "1 tsp Oil\n" +
                        "25g Pecan nuts\n" +
                        "125g Raspberries")

                val recipeInstructions = arrayListOf<String>()
                recipeInstructions.add("Put the mushrooms into a food processor with some seasoning and pulse to a rough paste.\n" +
                        "Scrape the paste into a pan and cook over a high heat for about 10 mins, tossing frequently, to cook out the moisture from the mushrooms.\n" +
                        "Spread out on a plate to cool.\n" +
                        "Heat in a frying pan and add a little olive oil.\n" +
                        "Season the beef and sear in the hot pan for 30 secs only on each side.\n" +
                        "(You don't want to cook it at this stage, just colour it).\n" +
                        "Remove the beef from the pan and leave to cool, then brush all over with the mustard.\n" +
                        "Lay a sheet of cling film on a work surface and arrange the Parma ham slices on it, in slightly overlapping rows.\n" +
                        "With a palette knife, spread the mushroom paste over the ham, then place the seared beef fillet in the middle.\n" +
                        "Keeping a tight hold of the cling film from the edge, neatly roll the Parma ham and mushrooms around the beef to form a tight barrel shape.\n" +
                        "Twist the ends of the cling film to secure.\n" +
                        "Chill for 15-20 mins to allow the beef to set and keep its shape.\n" +
                        "Roll out the puff pastry on a floured surface to a large rectangle, the thickness of a £1 coin.\n" +
                        "Remove the cling film from the beef, then lay in the centre.\n" +
                        "Brush the surrounding pastry with egg yolk.\n" +
                        "Fold the ends over, the wrap the pastry around the beef, cutting off any excess.\n" +
                        "Turn over, so the seam is underneath, and place on a baking sheet.\n" +
                        "Brush over all the pastry with egg and chill for about 15 mins to let the pastry rest.\n" +
                        "Heat the oven to 200C, 400F, gas 6.\n" +
                        "Lightly score the pastry at 1cm intervals and glaze again with beaten egg yolk.\n" +
                        "Bake for 20 minutes, then lower the oven setting to 180C, 350F, gas 4 and cook for another 15 mins.\n" +
                        "Allow to rest for 10-15 mins before slicing and serving with the side dishes of your choice.\n" +
                        "The beef should still be pink in the centre when you serve it.")
                recipeInstructions.add("Add the ginger, garlic, soy sauce, sake and sugar to a bowl and whisk to combine.\n" +
                        "Add the chicken, then stir to coat evenly.\n" +
                        "Cover and refrigerate for at least 1 hour.\n" +
                        "Add 1 inch of vegetable oil to a heavy bottomed pot and heat until the oil reaches 360 degrees F.\n" +
                        "Line a wire rack with 2 sheets of paper towels and get your tongs out.\n" +
                        "Put the potato starch in a bowl Add a handful of chicken to the potato starch and toss to coat each piece evenly.\n" +
                        "Fry the karaage in batches until the exterior is a medium brown and the chicken is cooked through.\n" +
                        "Transfer the fried chicken to the paper towel lined rack.\n" +
                        "If you want the karaage to stay crispy longer, you can fry the chicken a second time, until it's a darker color after it's cooled off once.\n" +
                        "Serve with lemon wedges.\n")
                recipeInstructions.add("In a bowl, mash the banana with a fork until it resembles a thick purée.\n" +
                        "Stir in the eggs, baking powder and vanilla.\n" +
                        "Heat a large non-stick frying pan or pancake pan over a medium heat and brush with half the oil.\n" +
                        "Using half the batter, spoon two pancakes into the pan, cook for 1-2 mins each side, then tip onto a plate.\n" +
                        "Repeat the process with the remaining oil and batter.\n" +
                        "Top the pancakes with the pecans and raspberries.")

                for (i in recipeTypes.indices){
                    val category = Categories(
                        idCategory = i,
                        categoryName = recipeTypes[i],
                        categoryImage = imageURLCategories[i]
                    )
                    RecipeDatabase.getDatabase(this@StartActivity).recipeDao().insertCategory(category)

                    val recipe = Recipes(
                        idRecipes = i+1,
                        categoryName = recipeTypes[i],
                        recipeImage = imageURLRecipes[i],
                        recipeName = recipeNames[i],
                        ingredients = recipeIngredients[i],
                        instructions = recipeInstructions[i]
                    )
                    RecipeDatabase.getDatabase(this@StartActivity).recipeDao().insertRecipe(recipe)
                }
            }
        }
    }

    private fun readStorage() {
        if (hasReadStoragePermission()){
            launch {
                let {
                    if(RecipeDatabase.getDatabase(this@StartActivity).recipeDao().getAllRecipe().isEmpty()){
                        clearDatabase()
                        getCategories()
                    }
                }
            }
        } else {
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your storage.",
                READ_STORAGE_PERM,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    private fun clearDatabase() {
        launch {
            this.let {
                RecipeDatabase.getDatabase(this@StartActivity).recipeDao().clearCategories()
                RecipeDatabase.getDatabase(this@StartActivity).recipeDao().clearRecipes()
            }
        }
    }

    private fun hasReadStoragePermission(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private fun parseRecipeXML(){
        recipeTypes = ArrayList<String>()
        val parser = resources.getXml(R.xml.recipetypes)
        try {
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.name == "recipetype") {
                        recipeTypes.add(parser.nextText())
                    }
                }
                eventType = parser.next()
            }
        } catch (e: XmlPullParserException) {
            Log.e("XmlPullParserException", e.toString())
        } catch (e: IOException) {
            Log.e("XmlPullParserException", e.toString())
        }
        parser.close()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onRationaleAccepted(requestCode: Int) {
        //TODO("Not yet implemented")
    }

    override fun onRationaleDenied(requestCode: Int) {
        //TODO("Not yet implemented")
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        readStorage()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }
}