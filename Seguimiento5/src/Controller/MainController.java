package Controller;

import model.Transaction;
import model.TransactionData;

import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainController {
	
	@FXML
	private Stage stage;

    @FXML
    private TableView<Transaction> tableIngresos;

    @FXML
    private TableColumn<Transaction, LocalDate> dateColIngresos;

    @FXML
    private TableColumn<Transaction, String> infoColIngresos;

    @FXML
    private TableColumn<Transaction, Double> valueColIngresos;

    @FXML
    private TableView<Transaction> tableGastos;

    @FXML
    private TableColumn<Transaction, LocalDate> dateColGastos;

    @FXML
    private TableColumn<Transaction, String> infoColGastos;

    @FXML
    private TableColumn<Transaction, Double> valueColGastos;

    @FXML
    private DatePicker initialDate;

    @FXML
    private DatePicker finalDate;

    @FXML
    Label gastoTotalTextField;

    @FXML
     Label ingresoTotalTextField;

    @FXML
    Label balanceTextField;
    
    private ObservableList<Transaction> restaured=null;
    
    public void initialize()
    {
    	
    	dateColIngresos.setCellValueFactory(new PropertyValueFactory<>("date"));
    	infoColIngresos.setCellValueFactory(new PropertyValueFactory<>("text"));
    	valueColIngresos.setCellValueFactory(new PropertyValueFactory<>("value"));
    	
    	dateColGastos.setCellValueFactory(new PropertyValueFactory<>("date"));
    	infoColGastos.setCellValueFactory(new PropertyValueFactory<>("text"));
    	valueColGastos.setCellValueFactory(new PropertyValueFactory<>("value"));
    	
    	tableIngresos.setItems(TransactionData.ingresos);
    	tableGastos.setItems(TransactionData.gastos);
    	
    	setIngresoTotal(TransactionData.ingresos);
    	setGastoTotal(TransactionData.gastos);
    	setBalance();
    }
    
    public void addTransaction(ActionEvent event) throws IOException
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/AddScene.fxml"));
		Parent p = (Parent) loader.load();
		stage = new Stage();
		Scene scene = new Scene(p);
		stage.setScene(scene);
		stage.show();
    }
    @FXML
    public void filterDate(ActionEvent event2) throws Exception
    {
    	System.out.println("\n\ncogió\n");
    	LocalDate initialD, finalD;
	    initialD=initialDate.getValue();
	    finalD=finalDate.getValue();
    	ObservableList<Transaction> interIngresos=FXCollections.observableArrayList();
    	ObservableList<Transaction> interGastos=FXCollections.observableArrayList();
    	interIngresos.addAll(TransactionData.ingresos);
    	interGastos.addAll(TransactionData.gastos);
    	int a=interGastos.size();
    	int b=interIngresos.size();
    	for(int i=0; i<a; i++)
    	{
    		if(interGastos.get(i).getDate().isBefore(initialD))
    		{
    			interGastos.remove(i);
    			a-=1;
    		}
    	}
    	System.out.println("después del primer for");
    	for(int i=0; i<b; i++)
    	{
    		if(interIngresos.get(i).getDate().isBefore(initialD))
    		{
    			interIngresos.remove(i);
    			b-=1;
    		}
    	}
    	System.out.println("después del segundo for");
    	a=interGastos.size();
    	b=interIngresos.size();
    	for(int i=0; i<a; i++)
    	{
    		if(interGastos.get(i).getDate().isAfter(finalD))
    		{
    			interGastos.remove(i);
    			a-=1;
    			i-=1;
    		}
    	}
    	System.out.println("después del tercer for");
    	for(int i=0; i<b; i++)
    	{
    		if(interIngresos.get(i).getDate().isAfter(finalD))
    		{
    			interIngresos.remove(i);
    			b-=1;
    			i-=1;
    		}
    	}
    	System.out.println("después del tercer for");
    	tableIngresos.setItems(interIngresos);
    	tableGastos.setItems(interGastos);
    	setGastoTotal(interGastos);
    	setIngresoTotal(interIngresos);
    	setBalance();
    }
    
    public void restaurar(ActionEvent e)
    {
    	tableIngresos.setItems(TransactionData.ingresos);
    	tableGastos.setItems(TransactionData.gastos);
    	initialDate.setValue(null);;
    	finalDate.setValue(null);
    }
    
    public void setIngresosTable(ObservableList<Transaction> list) {
    	tableIngresos.setItems(list);
    }
    
    public void setGastosTable(ObservableList<Transaction> list) {
    	tableIngresos.setItems(list);
    }

    public void setIngresoTotal(ObservableList<Transaction> list)
    {
    	double result=0;
    	for(int i=0; i<list.size(); i++)
    	{
    		result+=list.get(i).getValue();
    	}
    	System.out.println(result);
    	ingresoTotalTextField.setText(String.valueOf(result));
    }	
    
    public void setGastoTotal(ObservableList<Transaction> list)
    {
    	double result=0;
    	for(int i=0; i<list.size(); i++)
    	{
    		result+=list.get(i).getValue();
    	}
    	gastoTotalTextField.setText(String.valueOf(result)); 
    }	

    public void setBalance()
    {
    	balanceTextField.setText(String.valueOf(Double.parseDouble(ingresoTotalTextField.getText())-Double.parseDouble(gastoTotalTextField.getText())));
    }
    
    public void update(ActionEvent e)
    {
    	setGastoTotal(TransactionData.gastos);
    	setIngresoTotal(TransactionData.ingresos);
    	setBalance();
    }
    
}
