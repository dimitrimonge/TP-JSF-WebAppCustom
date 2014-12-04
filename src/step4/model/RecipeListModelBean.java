package step4.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import step4.dao.fabric.DaoFabric;
import step4.dao.instance.RecipesDao;

@ManagedBean
@SessionScoped
public class RecipeListModelBean {
	
	private int dureeprepa;
	int star;
	int hmpeople;
	String type;
	
	private List<RecipeModel> recipeList;

	private RecipesDao recipesDao;
	
	public RecipeListModelBean() {
		recipeList=new ArrayList<RecipeModel>();
		this.recipesDao = DaoFabric.getInstance().createRecipesDao();
	}
	
	public void addRecipeList(RecipeModel recipe){
		this.recipeList.add(recipe);
	}
	
	public List<RecipeModel> getRecipeList() {
		List<RecipeModel> recipeList = recipesDao.getSpecRecipe(this.getDureeprepa(), this.getStar(), this.gethmpeople(), this.gettype());
		return recipeList;
	}
	
	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	
	public int gethmpeople() {
		return hmpeople;
	}

	public void sethmpeople(int hmpeople) {
		this.hmpeople = hmpeople;
	}
	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}
	
	public String Display_recipes(){
		recipeList = getRecipeList();
		return "Display_recipes";
			}

	public int getDureeprepa() {
		return dureeprepa;
	}

	public void setDureeprepa(int dureeprepa) {
		this.dureeprepa = dureeprepa;
	}

}

