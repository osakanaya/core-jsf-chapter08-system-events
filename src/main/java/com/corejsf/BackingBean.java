package com.corejsf;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

@Named("bb")
@SessionScoped
public class BackingBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int day;
	private int month;
	private int year;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void validateDate(ComponentSystemEvent event) {
		UIComponent source = event.getComponent();
		
		UIInput dayInput = (UIInput)source.findComponent("day");
		UIInput monthInput = (UIInput)source.findComponent("month");
		UIInput yearInput = (UIInput)source.findComponent("year");
		
		int d = ((Integer)dayInput.getLocalValue()).intValue();
		int m = ((Integer)monthInput.getLocalValue()).intValue();
		int y = ((Integer)yearInput.getLocalValue()).intValue();
		
		if (!isValidDate(d, m, y)) {
			FacesMessage message 
			= com.corejsf.util.Messages.getMessage(
					"com.corejsf.messages", "invalidDate", null);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(source.getClientId(), message);
			
			context.renderResponse();
		}
	}

	private static boolean isValidDate(int d, int m, int y) {
		if (d < 1 || m < 1 || m > 12)
			return false;
		if (m == 2) {
			if (isLeapYear(y))
				return d <= 29;
			else
				return d <= 28;
		} else if (m == 4 || m == 6 || m == 9 || m == 11)
			return d <= 30;
		else
			return d <= 31;
	}

	private static boolean isLeapYear(int y) {
		return y % 4 == 0 && (y % 400 == 0 || y % 100 != 0);
	}

}
