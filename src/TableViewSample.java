import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableCell;
import javafx.application.Platform;

 
public class TableViewSample extends Application {
 
    private final TableView<Person> table = new TableView<>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com"),
    new Person("Jacob1", "Smith", "jacob.smith@example.com"),
    new Person("Isabella1", "Johnson", "isabella.johnson@example.com"),
    new Person("Ethan1", "Williams", "ethan.williams@example.com"),
    new Person("Emma1", "Jones", "emma.jones@example.com"),
    new Person("Michael1", "Brown", "michael.brown@example.com"),
    new Person("Jacob2", "Smith", "jacob.smith@example.com"),
    new Person("Isabella2", "Johnson", "isabella.johnson@example.com"),
    new Person("Ethan2", "Williams", "ethan.williams@example.com"),
    new Person("Emma2", "Jones", "emma.jones@example.com"),
    new Person("Michael2", "Brown", "michael.brown@example.com"),
    new Person("Jacob3", "Smith", "jacob.smith@example.com"),
    new Person("Isabella3", "Johnson", "isabella.johnson@example.com"),
    new Person("Ethan3", "Williams", "ethan.williams@example.com"),
    new Person("Emma3", "Jones", "emma.jones@example.com"),
    new Person("Michael3", "Brown", "michael.brown@example.com"),
    new Person("Jacob4", "Smith", "jacob.smith@example.com"),
    new Person("Isabella4", "Johnson", "isabella.johnson@example.com"),
    new Person("Ethan4", "Williams", "ethan.williams@example.com"),
    new Person("Emma4", "Jones", "emma.jones@example.com"),
    new Person("Michael4", "Brown", "michael.brown@example.com"),
    new Person("Jacob5", "Smith", "jacob.smith@example.com"),
    new Person("Isabella5", "Johnson", "isabella.johnson@example.com"),
    new Person("Ethan5", "Williams", "ethan.williams@example.com"),
    new Person("Emma5", "Jones", "emma.jones@example.com"),
    new Person("Michael5", "Brown", "michael.brown@example.com"));
    
    final HBox hb = new HBox();
    
    final HBox hbmenu = new HBox();
    
    TableColumn<Person, Void> colBtn, colcb; // buttton and checkbox columns in tableview
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("MDA");
        stage.setWidth(630);
        stage.setHeight(550);
        
        // Setup menu at top of screen 
        
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        //root.setTop(menuBar);
        
        /*****************************************
         * File menu - new, save, exit
         * 
         ***************************************/
        
        Menu fileMenu = new Menu("Membership");
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        
        fileMenu.getItems().addAll(newMenuItem, saveMenuItem,
                new SeparatorMenuItem(), exitMenuItem);
        
        Menu webMenu = new Menu("Distribution");
        CheckMenuItem htmlMenuItem = new CheckMenuItem("HTML");
        htmlMenuItem.setSelected(true);
        webMenu.getItems().add(htmlMenuItem);

        CheckMenuItem cssMenuItem = new CheckMenuItem("CSS");
        cssMenuItem.setSelected(true);
        webMenu.getItems().add(cssMenuItem);

        Menu sqlMenu = new Menu("Help");
        ToggleGroup tGroup = new ToggleGroup();
        RadioMenuItem mysqlItem = new RadioMenuItem("MySQL");
        mysqlItem.setToggleGroup(tGroup);

        RadioMenuItem oracleItem = new RadioMenuItem("Oracle");
        oracleItem.setToggleGroup(tGroup);
        oracleItem.setSelected(true);

        sqlMenu.getItems().addAll(mysqlItem, oracleItem,
            new SeparatorMenuItem());

        Menu tutorialManeu = new Menu("Tutorial");
        tutorialManeu.getItems().addAll(
            new CheckMenuItem("Java"),
            new CheckMenuItem("JavaFX"),
            new CheckMenuItem("Swing"));
        
        menuBar.getMenus().addAll(fileMenu, webMenu, sqlMenu);
        
        hbmenu.getChildren().addAll(menuBar);
        hbmenu.setSpacing(3);
        
        // End of setup menu
        
        
        
        
        // setup title on screen using label
        
        final Label label = new Label("Members");
        label.setFont(new Font("Arial", 20));
 
        // setup tableview with static data 
        
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
 
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(70);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));
 
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        
    //    TableColumn selectButCol = new TableColumn("View");
       
     //   TableColumn checkboxCol = new TableColumn("Select");
        
        
 
        table.setItems(data);  // specify table view with static data
        
     //   table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");
 
        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            data.add(new Person(
                    addFirstName.getText(),
                    addLastName.getText(),
                    addEmail.getText()));
            addFirstName.clear();
            addLastName.clear();
            addEmail.clear();
        });
        
        final Button selectButtonBase = new Button("List All Checked");
        
        addButtonToTable();
        
        addCheckBoxToTable();
        
        
     // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
            	System.out.println("selectButtonbase pressed");
            	TableColumn<Person, Void> columncb = colcb ;
            	
            
            	List<String> columnData2 = new ArrayList<>();
                for (Person item : table.getItems()) {
                  //  columnData2.add(column2.getCellObservableValue(item).getValue());
                 //   System.out.println(columncb.getCellObservableValue(item).getValue());
                    System.out.println(columncb.getText()); // column title
                    System.out.println(columncb.hasProperties());
                    System.out.println(columncb.cellFactoryProperty());
                }
            }
        };
  
        // when button is pressed
        selectButtonBase.setOnAction(event);
        
        // list lastnames from display on screen
        
        TableColumn<Person, String> column = lastNameCol; // column you want
           
        List<String> columnData = new ArrayList<>();
        for (Person item : table.getItems()) {
            columnData.add(column.getCellObservableValue(item).getValue());
            System.out.println(column.getCellObservableValue(item).getValue());
        }
        
 
        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton, selectButtonBase);
        hb.setSpacing(3);
 
        // the vertical layout box vbox consists of elements from
        // top to bottom 
        
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(hbmenu,label, table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
    
     void addButtonToTable() {
         colBtn = new TableColumn("Correct Button");

        Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cellFactory = new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
            @Override
            public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
                final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

                    private final Button btn = new Button("Action");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Person data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                      //      Label secondLabel = new Label("I'm a Label on new Window");
                            
                            // setup new window to call 
                            
                            TextArea ta = new TextArea(data.toString());
                            
                            StackPane secondaryLayout = new StackPane();
                            secondaryLayout.getChildren().add(secondLabel);
                            secondaryLayout.getChildren().add(ta);
             
                            Scene secondScene = new Scene(secondaryLayout, 430, 100);
             
                            // New window (Stage)
                            Stage newWindow = new Stage();
                            newWindow.setTitle("Member Details");
                            newWindow.setScene(secondScene);
             
                            // Set position of second window, related to primary window.
          //                  newWindow.setX(stage.getX() + 200);
          //                  newWindow.setY(stage.getY() + 100);
                            
                            newWindow.initModality(Modality.APPLICATION_MODAL); // must finish new window , other windows unavailable
                     //       newWindow.initModality(Modality.NONE); // new window tottally independent of other windows
                      //      newWindow.initModality(Modality.WINDOW_MODAL);
             
                            newWindow.show();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        table.getColumns().add(colBtn);

    }
    
     void addCheckBoxToTable() {
        colcb = new TableColumn("Correct Checkbox");

        Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cellFactory = new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
            @Override
            public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
                final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

                    private final CheckBox cb = new CheckBox("");

                    {
                        cb.setOnAction((ActionEvent event) -> {
                            Person data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(cb);
                        }
                    }
                };
                return cell;
            }
        };

        colcb.setCellFactory(cellFactory);

        table.getColumns().add(colcb);

    }
    
    
 
    public static class Person {
 
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
       
 
        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
           
        }
 
        public String getFirstName() {
            return firstName.get();
        }
 
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
 
        public String getLastName() {
            return lastName.get();
        }
 
        public void setLastName(String fName) {
            lastName.set(fName);
        }
 
        public String getEmail() {
            return email.get();
        }
 
        public void setEmail(String fName) {
            email.set(fName);
        }
        
        @Override
        public String toString() {
            return "first Name: " + firstName.get() + "  " + "Last name: " + lastName.get() + "  Email :" + email.get();
        }
        
    }
} 