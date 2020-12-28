import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Function;
import javafx.scene.layout.GridPane;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Vector;

public class JavaFXTemplate extends Application {

    private Vector<Button> buttonVector;
    private Stage stage;
    HashMap<String, Scene> sceneMap;

    private TextField t1;
    private MenuBar menu;


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


          stage = primaryStage;
          sceneMap = new HashMap<String,Scene>();

    primaryStage.setScene(startupScene());
    primaryStage.show();




    }
    public Text defaultText(String input){ //method to set our text a certain style
        Text Input = new Text(input);

        Input.setFont(Font.font("Times New Roman", 16));

        return Input;
    }


    public Scene gameScene() {
        BorderPane gameScenePane = new BorderPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        for (int r = 0; r <8; r++) {
            for (int c = 0; c < 10; c++) {
                int number = 10 * r + c + 1;
                Button button = new Button(String.valueOf(number));
                //maybe throw button into an array here
                grid.add(button, c, r);
            }
        }

        //menu bar stuff i can probably write a method that can return menubar but I will do that later
        menu = new MenuBar();
        Menu mOne = new Menu("Menu"); //first dropMenu
        MenuItem iOne = new MenuItem("Rules"); //first Option of dropMenu
        MenuItem iTwo = new MenuItem("Odds of Winning");
        MenuItem iThree = new MenuItem("Quit");
        MenuItem iFour = new MenuItem("New Look");
        MenuItem iFive = new MenuItem("Return to Main Menu");

        mOne.getItems().addAll(iFive,iOne,iTwo,iFour,iThree);
        iOne.setOnAction(e->stage.setScene(ruleScene("gameScene"))); //this is when someone hits the rules
        iTwo.setOnAction(e->stage.setScene(oddsScene("gameScene"))); //this is when someone hits the odds
        iFive.setOnAction(e->stage.setScene(startupScene()));
        iThree.setOnAction(e->stage.close()); //this is when someone hits quit
        menu.getMenus().addAll(mOne);


        ScrollPane scrollPane = new ScrollPane(grid);
        gameScenePane.setCenter(scrollPane);
        gameScenePane.setTop(menu);
        return new Scene(gameScenePane,1080,720);
    }
















    public Scene startupScene(){
        stage.setTitle("Main Menu");
        Button playButton = new Button();
        menu = new MenuBar();
        BorderPane startupPane = new BorderPane();

        // set up background image
        Image background = new Image("casino-background.jpg");
        ImageView b = new ImageView(background);
        startupPane.getChildren().addAll(b);

        Menu mOne = new Menu("Menu"); //first dropMenu
        MenuItem iOne = new MenuItem("Rules"); //first Option of dropMenu
        MenuItem iTwo = new MenuItem("Odds of Winning");
        MenuItem iThree = new MenuItem("Quit");
        mOne.getItems().addAll(iOne,iTwo,iThree);

        // Keno title
        Text kenoTitle = new Text("KENO");
        kenoTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 90));
        kenoTitle.setFill(Color.WHITE);

        //this should handle our button
        Image pic = new Image("playbutton.png");
        ImageView v = new ImageView(pic);

        playButton.setGraphic(v);


        v.setPreserveRatio(true);

        iOne.setOnAction(e->stage.setScene(ruleScene("mainMenu"))); //this is when someone hits the rules
        iTwo.setOnAction(e->stage.setScene(oddsScene("mainMenu"))); //this is when someone hits the odds
        iThree.setOnAction(e->stage.close()); //this is when someone hits quit
        menu.getMenus().addAll(mOne);

        playButton.setOnAction(e->stage.setScene(gameScene()));

        startupPane.setTop(menu);
        VBox vbox = new VBox(10, kenoTitle, playButton);
        HBox root = new HBox(5, vbox);
        vbox.setAlignment(Pos.CENTER);
        root.setAlignment(Pos.CENTER);
        startupPane.setCenter(root);



        return new Scene(startupPane, 500,500);
    }

//
    public Scene ruleScene(String from){
        stage.setTitle("Rules Page");

        Button returnButton = new Button("return");

        Vector<Text> rulesVector = new Vector<Text>();
        Text rule1;
        Text rule2;


        BorderPane pane = new BorderPane();
//        pane.setStyle("-fx-background-color: blue;");
        //if statements so that we can see where the user came from
        if(from.equals("mainMenu")){
            returnButton.setOnAction(e-> stage.setScene(startupScene()));
        }
        else{
            returnButton.setOnAction(e-> stage.setScene(gameScene()));
        }
        rule1 = defaultText("1) First Rule...");
        rule2 = defaultText("2) Second Rule...");

        rulesVector.add(rule1);
        rulesVector.add(rule2);


        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Rules of the Game");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        vbox.getChildren().add(title);
        for (int i = 0; i < rulesVector.size(); i++){
            VBox.setMargin(rulesVector.get(i), new Insets(0, 0, 0, 8));
            vbox.getChildren().add(rulesVector.get(i)); //this takes care of our rules and the in the vbox
        }


        vbox.getChildren().add(returnButton); //adding return button to the bottom of the vbox


        pane.setLeft(vbox);
        stage.setTitle("Rules Page");

        return new Scene(pane,350,500);

    }
    public Scene oddsScene(String from){
        stage.setTitle("Odds of Winning");
        Text oneSpot;
        Text fourSpot;
        Text eightSpot;
        Text tenSpot;
        Button returnButton = new Button("return");
        Vector<Text> oddsVector = new Vector<Text>();


        BorderPane pane = new BorderPane();

        //if statements so that we can see where the user came from

        if(from.equals("mainMenu")) {
            returnButton.setOnAction(e -> stage.setScene(startupScene()));
        }
        else{
            returnButton.setOnAction(e -> stage.setScene(gameScene()));
        }
        oneSpot = defaultText("1 Spot Game\nOdds of Winning = 1 in 4.00 ");
        fourSpot = defaultText("4 Spot Game\nOdds of Winning = 1 in 3.86 ");
        eightSpot = defaultText("8 Spot Game\nOdds of Winning = 1 in 9.77 ");
        tenSpot = defaultText("10 Spot Game\nOdds of Winning = 1 in 9.05 ");


        oddsVector.add(oneSpot);
        oddsVector.add(fourSpot);
        oddsVector.add(eightSpot);
        oddsVector.add(tenSpot);


        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Odds of winning");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);
        for (int i = 0; i < oddsVector.size(); i++){
            VBox.setMargin(oddsVector.get(i), new Insets(0, 0, 0, 8));
            vbox.getChildren().add(oddsVector.get(i)); //this takes care of our rules and the in the vbox
        }


        vbox.getChildren().add(returnButton); //adding return button to the bottom of the vbox


        pane.setLeft(vbox);

        return new Scene(pane,350,500);

    }

}
