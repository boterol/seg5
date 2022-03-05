package model;

import java.time.LocalDate;

public class Transaction {
	private double value;
	private LocalDate date;
	private String text;
	private TypeOfTransaction typeOfTransaction;
	
	public Transaction(LocalDate date, String text, double value, TypeOfTransaction typeOfTransaction) {
		this.date=date;													
		this.text=text;
		this.setValue(value);
		this.setTypeOfTransaction(typeOfTransaction);
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public TypeOfTransaction getTypeOfTransaction() {
		return typeOfTransaction;
	}

	public void setTypeOfTransaction(TypeOfTransaction typeOfTransaction) {
		this.typeOfTransaction = typeOfTransaction;
	}

}

