package step4.processing;

import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import step4.model.LoginBean;
import step4.model.UserModelBean;
import step4.dao.fabric.DaoFabric;
import step4.dao.instance.UserDao;
import step4.model.UserSubmissionModelBean;

@ManagedBean
@ApplicationScoped // Utilisation de application scope afin d'offrir un point d'entr� unique � l'ensemble des clients
public class UserControlerBean {
	private UserDao userDao;
	
	public UserControlerBean() {
		this.userDao=DaoFabric.getInstance().createUserDao();
	}
	
	public String checkUser(LoginBean loginBean){
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(), loginBean.getPwd());
		if( user!=null){
			
			//r�cup�re l'espace de m�moire de JSF
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			
			//place l'utilisateur dans l'espace de m�moire de JSF
			sessionMap.put("loggedUser", user);
			//redirect the current page
			return "action.xhtml";
		}else{
			
			//redirect the current page
			return "action.xhtml";
		}
	}
	
	public String checkAndAddUser(UserSubmissionModelBean userSubmitted){
		
		//V�rifier les propri�t�s de l'utilisateur
		UserModelBean user = this.userDao.checkUser(userSubmitted.getLogin(), userSubmitted.getPwd());
		if( user!=null){
			
			//redirect the current page
			return "formulaire.xhtml";
		}
		else{
			//ajout de l'utilisateur � la base de donn�es
			this.userDao.addUser(userSubmitted);
			//redirect the current page
			return "action.jsf";
		}
	}
	public String deconnexion()
	{
	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	Map<String, Object> sessionMap = externalContext.getSessionMap();
	sessionMap.remove("loggedUser");
	return "action.xhtml";
	}
		
	}
