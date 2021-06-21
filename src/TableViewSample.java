/************************************************************************
 *   Title: Proof of concept for membership JavaFX
 *   Date : 17/062021 Originates
 *   
 *   Version : v1.5
 *   
 *   Comment: v1.3 - added Restapi testing 
 *                   and controller for crud
 *            v1.5 - added database to tablebview
 *   
 ************************************************************************/



import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
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

//import org.json.simple.JSONObject;

 
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
    
    private final ObservableList<Person> checkedData = FXCollections.observableArrayList(); // list of all persons checked box
    
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
 
        
        // added record to tableview
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
        
        final Button RestApiButton = new Button("RESTapi");
        
        RestApiButton.setOnAction((ActionEvent e) -> {
            ControllerRestApi capi = new ControllerRestApi();
            try {
                //ok  capi.getAll();
            
               //ok   capi.insert();
            
                  capi.update();
                  capi.getAll();
            
               //ok   capi.get();
            
               //ok   capi.delete();
            
                  capi.deleteAll();
            
              } catch (Exception ex) {
                   System.out.println("Something went wrong in calling api");
                   }
        });
        
        
        final Button selectButtonBase = new Button("List All Checked");
        
        final CheckBox callb = new CheckBox("Check All");
        
        // button to get data from mysql database 
        
        final Button FromDB = new Button("Get DB");
        
        FromDB.setOnAction((ActionEvent e) -> {
        	
        	TableView<Person> tabledb = new TableView<>();
            ObservableList<Tutorials> datadb = FXCollections.observableArrayList();
            
    //                FXCollections.observableArrayList(
    //                new Person("Jacob", "Smith", "jacob.smith@example.com"));
            
            ControllerRestApi cr2 = new ControllerRestApi();
   //           datadb = cr2.getAll();  // fill with data from database
            
            
            
            //
        //    ObjectMapper mapper = new ObjectMapper();
        //    String jsonInString = "{'name' : 'mkyong'}";

            //JSON from file to Object
        //    User user = mapper.readValue(new File("c:\\user.json"), User.class);

            //JSON from String to Object
         //   User user = mapper.readValue(jsonInString, User.class);
            
            //
              
            JSONObject obj = new JSONObject();
            
            StackPane secondaryLayout = new StackPane();
            
            tabledb.setEditable(true);
            
            TableColumn idCol = new TableColumn("Id");
            idCol.setMinWidth(100);
            idCol.setCellValueFactory(
                    new PropertyValueFactory<>("Id"));
            
            TableColumn titleCol = new TableColumn("title");
            titleCol.setMinWidth(100);
            titleCol.setCellValueFactory(
                    new PropertyValueFactory<>("title"));
            
            TableColumn descCol = new TableColumn("Desc");
            descCol.setMinWidth(100);
            descCol.setCellValueFactory(
                    new PropertyValueFactory<>("desc"));
            
            TableColumn pubCol = new TableColumn("Published");
            pubCol.setMinWidth(100);
            pubCol.setCellValueFactory(
                    new PropertyValueFactory<>("Published"));
            
            TableColumn caCol = new TableColumn("Created");
            caCol.setMinWidth(100);
            caCol.setCellValueFactory(
                    new PropertyValueFactory<>("Created"));
            
            TableColumn uaCol = new TableColumn("Updated");
            uaCol.setMinWidth(100);
            uaCol.setCellValueFactory(
                    new PropertyValueFactory<>("Updated"));
            

      //      tabledb.setItems(datadb);  // specify table view with static data
            
            //   table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
               tabledb.getColumns().addAll(idCol, titleCol, descCol, pubCol, caCol, uaCol);
               
             StackPane secondaryLayout2 = new StackPane();
               
             secondaryLayout2.getChildren().add(tabledb);
               

            Scene secondScene = new Scene(secondaryLayout, 430, 100);

            // New window (Stage)
            Stage newWindowdb = new Stage();
            newWindowdb.setTitle("Data from DB");
            newWindowdb.setScene(secondScene);

            
            newWindowdb.initModality(Modality.APPLICATION_MODAL); // must finish new window , other windows unavailable
     //       newWindow.initModality(Modality.NONE); // new window tottally independent of other windows
      //      newWindow.initModality(Modality.WINDOW_MODAL);

            newWindowdb.show();
            
        });
        
        
        callb.setOnAction((ActionEvent event) -> {
        	ObservableList<Person> items = table.getItems();
      //      System.out.println("items: " + items);
        });
        
        addButtonToTable();
        
        addCheckBoxToTable();
        
        
     // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
           public void handle(ActionEvent e)
           {
                for (Person item : checkedData) {
                    System.out.println(item.toString());
                }
            }
        };
       
  
        // when button is pressed
        selectButtonBase.setOnAction(event);
        
        // list lastnames from display on screen
        
        TableColumn<Person, String> column = lastNameCol; // column you want
           
 
        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton, selectButtonBase, callb, RestApiButton, FromDB);
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
                     
                            
                            // setup new window to call 
                            
                            TextArea ta = new TextArea(data.toString());
                            
                            StackPane secondaryLayout = new StackPane();
                
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
                           boolean personPresent = false;
                           int pos = 0;
                           for(Person p : checkedData)
                           {
                        	   if(p.toString().equals(data.toString())) 
                        	   { personPresent=true;
                        	     pos = checkedData.indexOf(data);
                        	     break;
                        	   }
                           }
                           if (!personPresent)
                             checkedData.add(data);
                           else
                        	   checkedData.remove(pos);
                            
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
        
        System.out.println("scolcb id: " + colcb.getId());

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
    
    public static class Tutorials {
    	 
        private  int  id;
        private String title;
        private  String description;
        private  int published;
        private  String createdAt;
        private  String updatedAt;
        
       
 
        private Tutorials(int i ,String t, String d, int p ,String ca, String ua) {
        	this.id=i;
            this.title=t;
            this.description=d;
            this.published=p;
            this.createdAt=ca;
            this.updatedAt=ua;
            
            
        }
        
        public int getId() {
            return this.id;
        }
        
        public void setId(int i) {
            this.id=i;
        }
 
        public String getTitle() {
            return this.title;
        }
        
        public void setTitle(String t) {
            this.title=t;
        }
 
        public int getPublished() {
            return this.published;
        }
        
        public void setPublished(int p) {
            this.published=p;
        }
 
        public void setDescription(String d) {
            this.description=d;
        }
 
        public String getDescription() {
            return this.description;
        }
 
        public void setCreatedAt(String ca){
            this.createdAt=ca;
        }
 
        public String getCreatedAt() {
            return this.createdAt;
        }
        
        public void setUpdatedAt(String ua){
            this.updatedAt=ua;
        }
 
        public String getUpdatedAt() {
            return this.updatedAt;
        }
 
        
        @Override
        public String toString() {
            return "Id: " + getId() + "  " + "Title: " + getTitle();
        }
        
    }
    
    public class ControllerRestApi {
    	
    	ControllerRestApi(){
    		
    	}
    	
    	void insert() throws Exception {
    		HttpClient client = HttpClient.newHttpClient();
    	      
    		var tutorial ="{\"id\":7,\"title\":\"shroud of turin 2\",\"description\":\"relgious relic 2\",\"published\":false,\"createdAt\":\"2021-06-18T08:12:52.000Z\",\"updatedAt\":\"2021-06-18T08:12:52.000Z\"}";
   // 		var tutorial ="{'id':7,'title':'shroud of turin 2','description':'relgious relic 2','published':false,'createdAt':'2021-06-18T08:12:52.000Z','updatedAt':'2021-06-18T08:12:52.000Z'}";
 //   	    var bodyJson = gson.toJson(person);
    	    var request = HttpRequest.newBuilder(URI.create("http://192.168.1.74:8080/api/tutorials") )
    	                              .header("Accept", "application/json")
    	                              .header("Content-Type", "application/json")
    	                              .POST(BodyPublishers.ofString(tutorial))
    	                              .build();
    //	    populateRequestFields(request, bodyJson);
    	    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    	    System.out.println("Insert response " + response.body());
    	//    populateResponseFields(response);
    	
    } 
    	void update() throws Exception{
    		HttpClient client = HttpClient.newHttpClient();
    		var tutorial ="{\"id\":7,\"title\":\"shroud of turin 2\",\"description\":\"relgious relic 2 updated\",\"published\":false,\"createdAt\":\"2021-06-18T08:12:52.000Z\",\"updatedAt\":\"2021-06-18T08:12:52.000Z\"}";
    	    var request = HttpRequest.newBuilder(URI.create("http://192.168.1.74:8080/api/tutorials/7"))
    	                            .header("Accept", "application/json")
    	                            .header("Content-Type", "application/json")
    	                            .PUT(BodyPublishers.ofString(tutorial))
    	                            .build();
    	    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    		
    	}
    	
    	void getAll() throws Exception{
      		HttpClient client = HttpClient.newHttpClient();
      	    HttpRequest request = HttpRequest.newBuilder(URI.create("http://192.168.1.74:8080/api/tutorials")).build();
      	    HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); // array json objects
      	    System.out.println("Get all response " + response.body());
    		
    	}
    	
    	void get() throws Exception {
    		HttpClient client = HttpClient.newHttpClient();
    		HttpRequest request = HttpRequest.newBuilder(URI.create("http://192.168.1.74:8080/api/tutorials/6")).build();
    		HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); // json object
    		System.out.println("Get id=6 response " + response.body());
    		
    	}
    	
    	void delete() throws Exception {
    		HttpClient client = HttpClient.newHttpClient();
    		var request = HttpRequest.newBuilder(URI.create("http://192.168.1.74:8080/api/tutorials/6"))
                    .header("Accept", "application/json")
                    .DELETE()
                    .build();
    		
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    		
    	}
    	
    	void deleteAll() throws Exception{
    		
    	}
    	
    }
    
    
} 