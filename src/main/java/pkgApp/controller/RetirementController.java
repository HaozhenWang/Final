package pkgApp.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import pkgApp.RetirementApp;
import pkgCore.Retirement;
 public class RetirementController implements Initializable {

		
	private RetirementApp mainApp = null;
	
	@FXML
	private TextField txtYearsToWork;
	@FXML
	private TextField txtAnnualReturn;
	@FXML
	private TextField txtYearsRetired;
	@FXML
	private TextField txtRetiredReturn;
	@FXML
	private TextField txtRequiredIncome;
	@FXML
	private TextField txtMonthlySSI;
	@FXML
	private Label txtYearsToWorkLabel;
	@FXML
	private Label amountToSaveByMonth;
	@FXML
	private Label totalAmountSaved;


	

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}
	
	   @FXML
	    public void btnClear(ActionEvent event) {
	        System.out.println("Clear pressed");
	        txtYearsToWork.setText("");
	        txtAnnualReturn.setText("");
	        txtYearsRetired.setText("");
	        txtRetiredReturn.setText("");
	        txtRequiredIncome.setText("");
	        txtMonthlySSI.setText("");
	        txtRequiredIncome.setStyle("");
	        txtMonthlySSI.setStyle("");
	        amountToSaveByMonth.setText("");
	        totalAmountSaved.setText("");
	        txtYearsToWorkLabel.setText("");
	        txtRetiredReturn.setText("");
	    }

	   @FXML
	    public void btnCalculate(ActionEvent event) {
	        if (IsInputValid()) {
	            int yearToWork = Integer.parseInt(txtYearsToWork.getText());
	            double annualReturn = Double.parseDouble(txtAnnualReturn.getText().substring(0, txtAnnualReturn.getText().length() - 1)) * 0.01;

	            int yearsRetired = Integer.parseInt(txtYearsRetired.getText());
	            double retiredAnnualReturn = Double.parseDouble(txtRetiredReturn.getText().substring(0, txtRetiredReturn.getText().length() - 1)) * 0.01;
	            double requiredIncome = Double.parseDouble(txtRequiredIncome.getText());
	            double monthlySSI = Double.parseDouble(txtMonthlySSI.getText());

	            Retirement retirement = new Retirement(yearToWork, annualReturn, yearsRetired, retiredAnnualReturn, requiredIncome, monthlySSI);

	            DecimalFormat df = new DecimalFormat("#,###.00");

	            double  doubleTotalAmountSaved = retirement.TotalAmountSaved();
	            double doubleAmountToSaveByMonth = retirement.AmountToSave(doubleTotalAmountSaved);

	            String strTotalAmountSaved= df.format(-doubleTotalAmountSaved);
	            String strAmountToSaveByMonth = df.format(doubleAmountToSaveByMonth);
	        }
	    }

	    private boolean IsInputValid() {
	    	String errorMessage = "";

	        try {
	            Integer.parseInt(txtYearsToWork.getText());
	        } catch (NumberFormatException e) {
	            errorMessage += "Years To Work must be an integer!\n"; 
	        }
	        
	        try {
	            Integer.parseInt(txtYearsRetired.getText());
	        } catch (NumberFormatException e) {
	            errorMessage += "Years Retired have to be an integer!\n"; 
	        }
	        
	        try {
	            if(Double.parseDouble(txtAnnualReturn.getText())<0 || Double.parseDouble(txtAnnualReturn.getText())>.2) {
	            	errorMessage += "Annual Return Working have to be between 0 and 0.20\n";
	            }
	            
	        } catch (NumberFormatException e) {
	            errorMessage += "Annual Return Working have to be a number!\n"; 
	        }
	        
	        try {
	        	if(Double.parseDouble(txtRetiredReturn.getText())<0 || Double.parseDouble(txtRetiredReturn.getText())>.03) {
	            	errorMessage += "Annual Return Working have to be between 0 and 0.03\n";
	            }
	        } catch (NumberFormatException e) {
	            errorMessage += "Annual Return Retired have to be a number!\n"; 
	        }
	        
	        try {
	            Double.parseDouble(txtRequiredIncome.getText());
	        } catch (NumberFormatException e) {
	            errorMessage += "Required Income have to be a number!\n"; 
	        }

	        try {
	            Double.parseDouble(txtMonthlySSI.getText());
	        } catch (NumberFormatException e) {
	            errorMessage += "Monthly SSI have to be a number!\n"; 
	        }
	        
	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Show the error message.
	            Alert alert = new Alert(AlertType.ERROR);
	           // alert.initOwner(dialogStage);
	            alert.setTitle("Invalid Fields");
	            alert.setHeaderText("Please correct invalid fields");
	            alert.setContentText(errorMessage);

	            alert.showAndWait();

	            return false;
	        }
	    }
	    
	}