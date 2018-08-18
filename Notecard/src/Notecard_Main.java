import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Notecard_Main extends Application{
	
	private final TableView<Card> word_table = new TableView();
	private final ObservableList<Card> _stacks = FXCollections.observableArrayList();
	int index = 0;
	@Override
	public void start(Stage primaryStage) {
		
		// Main pane
		Random rand = new Random();
		BorderPane main_pane = new BorderPane();
		
		// Top box (HBox)
		HBox control_box = new HBox();
		control_box.setPadding(new Insets(15, 15, 15, 15));
		control_box.setStyle("-fx-background-color: #336699;");
		control_box.setSpacing(15);
		
		// Top box contents
		Button next_button = new Button("Next");
		next_button.setPrefSize(100,  20);
		Button next_rand = new Button("Next Random");
		next_rand.setPrefSize(100,  20);
		control_box.getChildren().addAll(next_button, next_rand);
		
		// Card pane
		StackPane card_pane = new StackPane();
		
		// Card pane contents
		Button word_button = new Button();
		word_button.setWrapText(true);
		word_button.setPrefSize(600, 400);
		word_button.setStyle("-fx-background-color: Yellow; -fx-border-color: Black; "
				+ "-fx-border-width: 1px; -fx-font: 50 Arial");
		word_button.setVisible(false);
		Button def_button = new Button();
		def_button.setWrapText(true);
		def_button.setPrefSize(600, 400);
		def_button.setStyle("-fx-background-color: Yellow; -fx-border-color: Black; "
				+ "-fx-border-width: 1px; -fx-alignment: TOP_LEFT; -fx-font: 30 Arial");
		def_button.setVisible(false);
		
		
		TableColumn word_col = new TableColumn("Words");
		word_col.setCellValueFactory(new PropertyValueFactory<Card, String>("Word"));
		TableColumn def_col = new TableColumn("Definitions");
		def_col.setCellValueFactory(new PropertyValueFactory<Card, String>("Def"));
		
		word_table.setVisible(true);
		word_table.setItems(_stacks);
		word_table.setEditable(true);
		word_table.getColumns().addAll(word_col, def_col);
		card_pane.getChildren().addAll(word_button, def_button, word_table);
		
		// Left Menu(VBOX)
		VBox menu_box = new VBox();
		menu_box.setPadding(new Insets(10));
		menu_box.setSpacing(15);
		
		// Left Menu contents
		Text menu_title = new Text("Stack Option");
		menu_title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		Hyperlink menu_options[] = new Hyperlink[] {
				new Hyperlink("Word/Definition"),
				new Hyperlink("Word List"),
				new Hyperlink("Add Card"),
				new Hyperlink("Import Samples"),
				new Hyperlink("Exit")
		};
		for (int i = 0; i < 5; ++i) {
			VBox.setMargin(menu_options[i], new Insets(0, 0, 0, 8));
			menu_box.getChildren().add(menu_options[i]);
		}
		
		// Show Words/Definitions
		menu_options[0].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent import_event) {
				word_button.setVisible(true);
				def_button.setVisible(false);
				word_table.setVisible(false);
			}
		});
		// List Words Action
		menu_options[1].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent list_event) {
				word_table.setVisible(true);
				word_button.setVisible(false);
				def_button.setVisible(false);
			}
		});
		// Add Card Action
		menu_options[2].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent add_event) {
				VBox add_pane = new VBox();
				
				TextField addWord = new TextField();
				addWord.setPromptText("Enter a word");
				addWord.setPrefWidth(def_col.getMaxWidth());
				TextField addDef = new TextField();
				addDef.setPromptText("Enter the definition");
				addDef.setPrefWidth(def_col.getMaxWidth());
				Button add_button = new Button("Add");
				
				add_pane.getChildren().addAll(addWord, addDef, add_button);
				
				Scene add_scene = new Scene(add_pane, 500, 250);
				Stage add_stage = new Stage();
				add_stage.setTitle("Add New Card");
				add_stage.setScene(add_scene);
				add_stage.show();
				
				add_button.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent add_word_event) {
						_stacks.add(new Card(addWord.getText(), addDef.getText()));
						Custom_dialog.message("Add Word", addWord.getText() + ": " + addDef.getText() + "\n"
								+ "has been succesfully added");
						add_stage.close();
					}
				});
				
			}
		});
		// Add Sample Action
		menu_options[3].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent import_event) {	
				_stacks.add(new Card("Namespace", "A named scope"));
				_stacks.add(new Card("Class Hierarchy", "Defines the inheritance relationship between a set of classes"));
				_stacks.add(new Card("Fork", "A second distinct and independent development path undertaken "
								+ "(often by a different organization) to create a unique product"));
				_stacks.add(new Card("Architect", "The engineer role that specifies a system's architecture"));
				_stacks.add(new Card("Derived Class", "The class inheriting members"));
				_stacks.add(new Card("Reference Counting", "A managed memory technique that tracks the number of references"
						+ "to allocated memory, so that the memory can be freed when the count reaches zero"));
				_stacks.add(new Card("Agile Process", "Prioritizing individuals and interactions, frequent delivery "
						+ "of working software, customer collaboration, and flexible response to change"));
				_stacks.add(new Card("Operator Overloading", "Defining the behavior of most C++ operators for new types"));
				_stacks.add(new Card("Second System Effect", "The common mistake of attempting to include far too "
								+ "many features in (typically) version 2.0, causing catastrophic schedule slips"));
				_stacks.add(new Card("Architecture", "The set of implementation elements and associated integration "
						+ "mechanisms necessary to meet the system requirements"));
				_stacks.add(new Card("Baseline", "A reference point in a version control system, usually indicating completion "
						+ "and approval of a product release and sometimes used to support a fork"));
				_stacks.add(new Card("Friend", "A class or function that is granted access to its friend class’ private members"));
				Custom_dialog.message("Import Samples", "Sample Data Imported");
			}
		});
		// Exit
		menu_options[4].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent import_event) {
				System.exit(0);
			}
		});
		// Flip to Word Action
		word_button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent flip_word) {
				word_button.setVisible(false);
				def_button.setVisible(true);
				word_table.setVisible(false);
			}
		});
		// Flip Definition Action
		def_button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent flip_def) {
				def_button.setVisible(false);
				word_button.setVisible(true);
				word_table.setVisible(false);
			}
		});
		// Next card in order
		next_button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent next_card) {
				if (_stacks.size() == 0) { return; }
				else if (index == _stacks.size() - 1) { index = 0; }
				else { index++; }
				word_button.setVisible(true);
				def_button.setVisible(false);
				word_button.setText(_stacks.get(index).getWord());
				def_button.setText(_stacks.get(index).getDef());
			}
		});
		// Next card at random
		next_rand.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent next_card) {
				if (_stacks.size() == 0) { return; }
				else {
					word_button.setVisible(true);
					def_button.setVisible(false);
					index = rand.nextInt(_stacks.size());
					word_button.setText(_stacks.get(index).getWord());
					def_button.setText(_stacks.get(index).getDef());				
				}
			}
		});
		
		main_pane.setTop(control_box);
		main_pane.setCenter(card_pane);
		main_pane.setLeft(menu_box);
		
		primaryStage.setTitle("Study Card");
		Scene main_scene = new Scene(main_pane);
		primaryStage.setScene(main_scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}