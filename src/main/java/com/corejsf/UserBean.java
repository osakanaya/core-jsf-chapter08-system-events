package com.corejsf;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

@Named("user")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name = "";
	private String password;
	private boolean loggedIn;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public String login() {
		loggedIn = true;
		
		return "index";
	}
	
	public String logout() {
		loggedIn = false;
		
		return "login";
	}
	
	
	public void checkLogin(ComponentSystemEvent e) {
		if (!loggedIn) {
			FacesContext context = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler handler = 
				(ConfigurableNavigationHandler)context.getApplication().getNavigationHandler();
			
			handler.performNavigation("login");
		}
	}
}
