package main;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	Button[] numberButtons;
	Button plusButton, minusButton, timesButton, divisionButton, equalButton, pointButton, zerozeroButton, negationButton, clearButton, delButton;
	TextField numberScreen;
	Scene scene;
	GridPane gPane;
	BorderPane bPane;
	FlowPane fPane;
	boolean kondisi = true;
	
	public void initialize() {
		bPane = new BorderPane();
		gPane = new GridPane();
		fPane = new FlowPane();
		
		numberScreen = new TextField();
		numberScreen.setEditable(false);
		
		numberButtons = new Button[10];
		for(int i = 0; i<10; i++) {
			int number = i;
			numberButtons[i] = new Button(String.valueOf(i));
			numberButtons[i].setOnAction(e->{
				addValueToDisplay(String.valueOf(number));
			});
		}
		
		plusButton = new Button("+");
		plusButton.setOnAction(e->{
			addOperatorToDisplay(" + ");
		});
		
		minusButton = new Button("-");
		minusButton.setOnAction(e->{
			addOperatorToDisplay(" - ");
		});
		
		timesButton = new Button("*");
		timesButton.setOnAction(e->{
			addOperatorToDisplay(" * ");
		});
		
		divisionButton = new Button("/");
		divisionButton.setOnAction(e->{
			addOperatorToDisplay(" / ");
		});
		
		pointButton = new Button(".");
		pointButton.setOnAction(e->{
			addPointToDisplay();
		});
		
		zerozeroButton = new Button("00");
		zerozeroButton.setOnAction(e->{
			addValueToDisplay("00");
		});
		
//		persenButton = new Button("%");
//		persenButton.setOnAction(e->{
//			addPersenButton();
//		});
		
		negationButton = new Button("±");
		negationButton.setOnAction(e->{
			negateValue();
		});
		
		clearButton = new Button("C");
		clearButton.setOnAction(e->{
			numberScreen.clear();
			kondisi = true;
		});
		
		delButton = new Button("⌫");
		delButton.setOnAction(e->{
			deleteValue();
		});
		
		equalButton = new Button("=");
		equalButton.setOnAction(e->{
			calculate();
		});
		
		scene = new Scene(bPane,220,320);
	}
	
	public void arrangeComponent() {
		fPane.getChildren().add(numberScreen);
		
		gPane.add(negationButton, 0, 0);
		gPane.add(clearButton, 1, 0);
		gPane.add(delButton, 2, 0);
		gPane.add(divisionButton, 3, 0);
		gPane.add(numberButtons[7], 0, 1);
		gPane.add(numberButtons[8], 1, 1);
		gPane.add(numberButtons[9], 2, 1);
		gPane.add(timesButton, 3, 1);
		gPane.add(numberButtons[4], 0, 2);
		gPane.add(numberButtons[5], 1, 2);
		gPane.add(numberButtons[6], 2, 2);
		gPane.add(minusButton, 3, 2);
		gPane.add(numberButtons[1], 0, 3);
		gPane.add(numberButtons[2], 1, 3);
		gPane.add(numberButtons[3], 2, 3);
		gPane.add(plusButton, 3, 3);
		gPane.add(zerozeroButton, 0, 4);
		gPane.add(numberButtons[0], 1, 4);
		gPane.add(pointButton, 2, 4);
		gPane.add(equalButton, 3, 4);
		
		negationButton.setMinSize(42, 42);
		clearButton.setMinSize(42, 42);
		delButton.setMinSize(42, 42);
		divisionButton.setMinSize(42, 42);
		timesButton.setMinSize(42, 42);
		minusButton.setMinSize(42, 42);
		plusButton.setMinSize(42, 42);
		zerozeroButton.setMinSize(42, 42);
		pointButton.setMinSize(42, 42);
		equalButton.setMinSize(42, 42);
		for (int i = 0; i<10; i++) {
			numberButtons[i].setMinSize(42, 42);
		}
		
		gPane.setHgap(4);
		gPane.setVgap(4);
		
		numberScreen.setMinWidth(180);
		numberScreen.setMinHeight(35);
		BorderPane.setAlignment(fPane, Pos.CENTER);
		fPane.setMinWidth(180);
		FlowPane.setMargin(numberScreen, new Insets(10,15,20,20));
		
		BorderPane.setMargin(gPane, new Insets(0,0,20,20));
		
		bPane.setTop(fPane);
		bPane.setCenter(gPane);
	}
	
	public void calculate() {
		String screenValue = numberScreen.getText();
		String values[] = screenValue.split(" ");
		if(values.length <= 1) {
			//kosong
		} else {
			double firstNumber = Double.parseDouble(values[0]);
			double secondNumber = Double.parseDouble(values[2]);
			String operator = values[1];
			double result = 0;
			
			switch(operator) {
				case "+":
					result = firstNumber + secondNumber;
					break;
				case "-":
					result = firstNumber - secondNumber;
					break;
				case "*":
					result = firstNumber * secondNumber;
					break;
				case "/":
					if(secondNumber != 0) {
						result = firstNumber / secondNumber;
					} else {
						//kosong
					}
					break;
			}
			if(result % 1 == 0) {
				numberScreen.setText(String.valueOf((int) result));								
			} else {
				numberScreen.setText(String.valueOf(result));				
			}
			kondisi = false;
		}
	}
	
	public void addValueToDisplay(String value) {
		if(kondisi) {
			numberScreen.setText(numberScreen.getText() + value);			
		}
	}
	
	public void addOperatorToDisplay(String value) {
		String screenValue = numberScreen.getText();
		String values[] = screenValue.split(" ");
		
		if (values.length == 2) {
			//kosong
		} else if(values.length == 3) {
			calculate();
			kondisi = true;
			addValueToDisplay(value);
		} else {
			kondisi = true;
			addValueToDisplay(value);
		}
	}
	
	public void addPointToDisplay() {
		String screenValue = numberScreen.getText();
		String values[] = screenValue.split(" ");
		
		if(values.length == 2) {
			//kosong
		} else {
			addValueToDisplay(".");
		}
	}
	
	public void negateValue() {
		String screenValue = numberScreen.getText();
		String values[] = screenValue.split(" ");
		
		if(screenValue.isEmpty() || values.length == 2) {
			//kosong
		} else if(values.length == 1) {
			double number = Double.parseDouble(values[0]);
			number = -number;
			
			if(number % 1 == 0) {
				numberScreen.setText(String.valueOf((int) number));								
			} else {
				numberScreen.setText(String.valueOf(number));				
			}
			
		} else if(values.length == 3) {
			double number = Double.parseDouble(values[2]);
			number = -number;
			numberScreen.setText(values[0] + " " + values[1] + " " + String.valueOf(number));
		}
	}
	
	public void addPersenButton() {
		String screenValue = numberScreen.getText();
		String values[] = screenValue.split(" ");
		
		if(values.length > 1) {
			//kosong
		} else {
			int numValue = Integer.parseInt(values[0]);
			numValue /= 100;
			numberScreen.setText(String.valueOf(numValue));
		}
	}
	
	public void deleteValue() {
		String screenValue = numberScreen.getText();
		if (screenValue.isEmpty()) {
			//kosong
		} else {
			numberScreen.setText(screenValue.substring(0, screenValue.length() - 1));
			String newValue = numberScreen.getText();
			if(newValue.isEmpty()) {
				kondisi = true;
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();
		arrangeComponent();
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Calculator Sederhana Ryan Febrian");
		primaryStage.show();
	}

}
