package Controller;

import java.io.IOException;

import model.Transaction;
import model.TransactionData;
import model.TypeOfTransaction;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {
	
	
	@FXML 
	private Button addButton;
	@FXML
	private RadioButton ingresoRButton, gastoRButton;
	@FXML
	private TextField valueTextField;
	@FXML
	private TextArea infoTextArea;
	
	private String info;
	private double value=1;
	private LocalDate date;
	private TypeOfTransaction typeOfTransaction;
	
	@FXML
	public void goBack(ActionEvent e) throws IOException
	{
		if(ingresoRButton.isSelected())
		{
			typeOfTransaction=TypeOfTransaction.INGRESO;
		}
		else
		{
			typeOfTransaction=TypeOfTransaction.GASTO;
		}
		value=Double.parseDouble(valueTextField.getText());
		info=infoTextArea.getText();
		date=LocalDate.now();
		Transaction newTransaction=new Transaction(date, info, value, typeOfTransaction);
		if(value!=-1)
		{
			if(typeOfTransaction==TypeOfTransaction.INGRESO)
			{
				TransactionData.ingresos.add(newTransaction);
			}
			else if(typeOfTransaction==TypeOfTransaction.GASTO)
			{
				TransactionData.gastos.add(newTransaction);
			}
		}
		

		/*
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../ui/MainScene.fxml"));
		Parent p=loader.load();
		MainController mainController=loader.getController();
		mainController.setGastoTotal(TransactionData.gastos);
		System.out.println("después de asignar los gastos");
		mainController.setIngresoTotal(TransactionData.ingresos);
		System.out.println("después de asignar los ingresos");
		mainController.setBalance();
		System.out.println("después de asignar el balance");
		*/
		Stage stage=(Stage) addButton.getScene().getWindow();
		stage.close();
	}
}




