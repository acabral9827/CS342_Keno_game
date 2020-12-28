import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;
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


import javax.swing.*;

public class JavaFXTemplate extends Application {

    Vector<Button> buttonsVector = new Vector<Button>();
    Vector<Integer> buttonsValueVector = new Vector<Integer>();

    private Stage stage;
    TextField text;
    private TextField t1;
    private MenuBar menu;
    ListView<Integer> displayWinningNumbers;
    ListView<Integer> displayMatchingNumbers;
    ListView<Integer> displaySelectedNumbers;
    Integer drawMoneyResult = 0;
    Integer overallCash = 200;
    Integer currentDrawingsCount = 0;
    Integer selectedDrawCount = 0;
    Integer typeOfSpotGame = 0;
    Text currentDrawCount;
    public static int count = 0;
    public static int maximumPressesAllowed;


    public static void main(String[] args){
        launch(args);
    }

    int earningsMethod(Set<Integer> selectedSet, Set<Integer> winningSet,int spotsPlayingFor){
        int result = 0;
        Set<Integer> comparisonSet = new HashSet<Integer>(winningSet);
        comparisonSet.retainAll(selectedSet);
        if(spotsPlayingFor == 1){
            switch (comparisonSet.size()){
                case 0:
                    break;
                case 1:
                    result = result + 2;
                    break;
            }
        }
        else if(spotsPlayingFor == 4){
            switch (comparisonSet.size()){
                case 0:
                    break;
                case 1:
                    result = result + 1;
                    break;
                case 3:
                    result = result + 5;
                    break;
                case 4:
                    result = result + 75;
                    break;
            }

        }
        else if(spotsPlayingFor == 8){
            switch (comparisonSet.size()){
                case 4:
                    result = result + 2;
                    break;
                case 5:
                    result = result + 12;
                    break;
                case 6:
                    result = result + 50;
                    break;
                case 7:
                    result = result + 750;
                    break;
                case 8:
                    result = result + 10000;
                    break;
            }

        }
        else if(spotsPlayingFor == 10){
            switch (comparisonSet.size()){
                case 0:
                    result = result + 5;
                    break;
                case 5:
                    result = result + 2;
                    break;
                case 6:
                    result = result + 15;
                    break;
                case 7:
                    result = result + 40;
                    break;
                case 8:
                    result = result + 450;
                    break;
                case 9:
                    result = result + 4250;
                    break;
                case 10:
                    result = result + 100000;
                    break;
            }

        }
        overallCash = overallCash+result;
        return result;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        text = new TextField();
        displayWinningNumbers = new ListView<Integer>();
        displayMatchingNumbers = new ListView<Integer>();
        displaySelectedNumbers = new ListView<Integer>();

        stage = primaryStage;

        displayWinningNumbers.setStyle("-fx-font-size: 14;"+"-fx-border-size: 10;"+
                "-fx-border-color: purple;");
        displayMatchingNumbers.setStyle("-fx-font-size: 14;"+"-fx-border-size: 10;"+
                "-fx-border-color: purple;");
        displaySelectedNumbers.setStyle("-fx-font-size: 14;"+"-fx-border-size: 10;"+
                "-fx-border-color: purple;");

        displayWinningNumbers.setPrefSize(300,300);
        displayWinningNumbers.setMaxWidth(150);
        displayMatchingNumbers.setPrefSize(300,300);
        displayMatchingNumbers.setMaxWidth(150);
        displaySelectedNumbers.setPrefSize(300,300);
        displaySelectedNumbers.setMaxWidth(150);

        primaryStage.setScene(startupScene());

        primaryStage.show();
    }


    public Text defaultText(String input){ //method to set our text a certain style
        Text Input = new Text(input);

        Input.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Input.setFill(Color.WHITE);
        return Input;
    }
    public void undoMethod(Vector<Button> inputButtonsVector, Vector<Integer> inputButtonsValueVector, ListView<Integer> listInputted){

        inputButtonsVector.remove(inputButtonsVector.lastElement());
        listInputted.getItems().remove(inputButtonsValueVector.lastElement()); //removes from list
        inputButtonsValueVector.remove(inputButtonsValueVector.lastElement());

    }
    public void setMaxPresses(int val) {
        maximumPressesAllowed = val;
    }

    public void setDrawCount(int val) {
        selectedDrawCount = val;
    }

    public Scene gameScene() {
        stage.setTitle("Keno!");
        BorderPane gameScenePane = new BorderPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(20);
        grid.setVgap(10);
        Set<Integer> selectedNumbers = new HashSet();

        gameScenePane.setStyle("-fx-background-image: url('https://www.psdgraphics.com/wp-content/uploads/2015/07/casino-pattern.jpg')");


        //menu bar stuff i can probably write a method that can return menubar but I will do that later
        menu = new MenuBar();
        Menu mOne = new Menu("Menu"); //first dropMenu
        MenuItem iOne = new MenuItem("Rules"); //first Option of dropMenu
        MenuItem iTwo = new MenuItem("Odds of Winning");
        MenuItem iThree = new MenuItem("Quit");
        MenuItem iFour = new MenuItem("New Look");
        MenuItem iFive = new MenuItem("Default Look");
        MenuItem iSix = new MenuItem("Return to Main Menu");
        mOne.getItems().addAll(iOne,iTwo,iThree,iFour,iFive,iSix);
        iOne.setOnAction(e->stage.setScene(ruleScene("gameScene"))); //this is when someone hits the rules
        iTwo.setOnAction(e->stage.setScene(oddsScene("gameScene"))); //this is when someone hits the odds
        iThree.setOnAction(e->stage.close()); //this is when someone hits quit
        iFour.setOnAction(e->gameScenePane.setStyle("-fx-background-image: url('https://us.123rf.com/450wm/liliwhite/liliwhite1501/liliwhite150100019/35527581-stock-vector-luxury-black-poker-background-with-card-symbols.jpg?ver=6')"));
        iFive.setOnAction(e->gameScenePane.setStyle("-fx-background-image: url('https://www.psdgraphics.com/wp-content/uploads/2015/07/casino-pattern.jpg')"));
        iSix.setOnAction(e->stage.setScene(startupScene()));
        menu.getMenus().addAll(mOne);

        //this section will cover the buttons under our gridpane
        //-----------------------------------------------------------------
        Button drawButton = new Button("Draw");
        Button randomizeButton = new Button("Randomize");
        Button undoButton = new Button("Undo");
        Button continueButton  = new Button("Continue");
        Button playAgainButton  = new Button("Play Again");

        continueButton.setDisable(true);
        undoButton.setDisable(true);
        randomizeButton.setDisable(true);
        drawButton.setDisable(true);
        playAgainButton.setDisable(true);


        // Sets used to compare winning numbers & matching numbers
        Set<Integer> winningNumbers = new HashSet<>();
        Set<Integer> matchingNumbers = new HashSet<>();

        // random number generator
        Random ran = new Random();

        // When draw is clicked winning numbers should be selected randomly and compared to matching numbers to see winning

        HBox Buttons = new HBox(10,undoButton,randomizeButton,drawButton,continueButton,playAgainButton);
        //----------------------------------------------------------------
        //this section will be for our current drawings label and continue button
        //----------------------------------------------------------------
        Text currentDrawCountLabel = defaultText("Current Draw Count: " );

        currentDrawCount = defaultText(String.valueOf(currentDrawingsCount));

        Text selectedDrawsCountLabel = defaultText("Selected Amount of Draws: " );
        Text selectedDrawCounter;
        selectedDrawCounter = defaultText(String.valueOf(selectedDrawCount)+"x");
        HBox currentDrawHBox = new HBox(2, currentDrawCountLabel,currentDrawCount);
        HBox selectedDrawHBox = new HBox(2, selectedDrawsCountLabel,selectedDrawCounter);

        Text spotGameLabel = defaultText("Type of spot game Chosen: ");
        Text spotGameChosen;
        spotGameChosen = defaultText(String.valueOf(typeOfSpotGame));


        HBox firstCounterHBox = new HBox(50,currentDrawHBox,selectedDrawHBox);
        HBox secondCounterHBox = new HBox(2, spotGameLabel,spotGameChosen);



        VBox counterVBox = new VBox(2,firstCounterHBox,secondCounterHBox);
        //----------------------------------------------------------------
        //this section will be for our lists row of info righthand side
        //-----------------------------------------------------------------
        Label winningNumbersLabel = new Label("Winning Numbers");
        winningNumbersLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
        winningNumbersLabel.setTextFill(Color.WHITE);
        Label matchingNumbersLabel = new Label("Matching Numbers");
        matchingNumbersLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
        matchingNumbersLabel.setTextFill(Color.WHITE);
        Label selectedNumbersLabel = new Label("Selected Numbers");
        selectedNumbersLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
        selectedNumbersLabel.setTextFill(Color.WHITE);
        Label gridLabel = new Label ("Pick your numbers!");
        gridLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
        gridLabel.setTextFill(Color.WHITE);
        VBox selectedNumsInfo = new VBox(10,selectedNumbersLabel,displaySelectedNumbers);
        VBox winningNumsInfo = new VBox(10,winningNumbersLabel,displayWinningNumbers);
        VBox matchingNumsInfo = new VBox(10,matchingNumbersLabel,displayMatchingNumbers);
        //-----------------------------------------------------------------

        //this part will be for the info on how much player has won, total money, and maybe a play again somewhere
        //-----------------------------------------------------------------
        Text drawEarningsValueLabel = defaultText("Draw Earnings: " );
        Text drawEarningsValue;
        drawEarningsValue = defaultText(String.valueOf(drawMoneyResult));
        Text overallValueLabel = defaultText("Total Cash: " );
        Text overallValue;
        overallValue = defaultText(String.valueOf(overallCash));

        VBox drawEarningVBox = new VBox(10, drawEarningsValueLabel,drawEarningsValue) ;
        VBox overallCashVBox = new VBox(10, overallValueLabel,overallValue);

        HBox thirdRow = new HBox(10,drawEarningVBox,overallCashVBox);

        //-----------------------------------------------------------------

        //this part will be for the leftside buttons
        //-----------------------------------------------------------------

        Label typeSpotLabel = new Label("What type of Spot game would you like to play?");
        typeSpotLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
        typeSpotLabel.setTextFill(Color.WHITE);

        Button oneSpotButton = new Button("1 Spot Game");
        Button fourSpotButton = new Button("4 Spot Game");
        Button eightSpotButton = new Button("8 Spot Game");
        Button tenSpotButton = new Button("10 Spot Game");
        HBox spotHBox = new HBox(10,oneSpotButton,fourSpotButton,eightSpotButton,tenSpotButton);
        VBox spotsVBox = new VBox(10, typeSpotLabel,spotHBox);


        Label drawingsPerDrawLabel = new Label("How many times would you like to draw?");
        drawingsPerDrawLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
        drawingsPerDrawLabel.setTextFill(Color.WHITE);
        Button oneDrawButton = new Button("1x");
        Button twoDrawButton = new Button("2x");
        Button threeDrawButton = new Button("3x");
        Button fourDrawButton = new Button("4x");


        //event handlers for our button
        oneDrawButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent a){
                setDrawCount(1);
                selectedDrawCounter.setText(String.valueOf(selectedDrawCount)+"x");
                if (typeOfSpotGame>0){
                    undoButton.setDisable(false);
                    randomizeButton.setDisable(false);
                    drawButton.setDisable(false);
                    grid.setDisable(false);

                    switch (typeOfSpotGame){
                        case 1:
                            setMaxPresses(1);
                            break;
                        case 4:
                            setMaxPresses(4);
                            break;
                        case 8:
                            setMaxPresses(8);
                            break;
                        case 10:
                            setMaxPresses(10);
                            break;
                    }
                }
            }
        });

        twoDrawButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent a){
                setDrawCount(2);
                selectedDrawCounter.setText(String.valueOf(selectedDrawCount)+"x");
                if (typeOfSpotGame>0){
                    undoButton.setDisable(false);
                    randomizeButton.setDisable(false);
                    drawButton.setDisable(false);
                    grid.setDisable(false);
                    switch (typeOfSpotGame){
                        case 1:
                            setMaxPresses(1);
                            break;
                        case 4:
                            setMaxPresses(4);
                            break;
                        case 8:
                            setMaxPresses(8);
                            break;
                        case 10:
                            setMaxPresses(10);
                            break;
                    }
                }

            }
        });

        threeDrawButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent a){
                setDrawCount(3);
                selectedDrawCounter.setText(String.valueOf(selectedDrawCount)+"x");
                if (typeOfSpotGame>0){
                    undoButton.setDisable(false);
                    randomizeButton.setDisable(false);
                    drawButton.setDisable(false);
                    grid.setDisable(false);
                    switch (typeOfSpotGame){
                        case 1:
                            setMaxPresses(1);
                            break;
                        case 4:
                            setMaxPresses(4);
                            break;
                        case 8:
                            setMaxPresses(8);
                            break;
                        case 10:
                            setMaxPresses(10);
                            break;
                    }
                }

            }
        });

        fourDrawButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent a){
                setDrawCount(4);
                selectedDrawCounter.setText(String.valueOf(selectedDrawCount)+"x");
                if (typeOfSpotGame>0){
                    undoButton.setDisable(false);
                    randomizeButton.setDisable(false);
                    drawButton.setDisable(false);
                    grid.setDisable(false);
                    switch (typeOfSpotGame){
                        case 1:
                            setMaxPresses(1);
                            break;
                        case 4:
                            setMaxPresses(4);
                            break;
                        case 8:
                            setMaxPresses(8);
                            break;
                        case 10:
                            setMaxPresses(10);
                            break;
                    }
                }

            }
        });

        //spot buttons
        oneSpotButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent a){
                typeOfSpotGame = 1;
                setMaxPresses(1);
                spotGameChosen.setText(String.valueOf(typeOfSpotGame));
                undoButton.setDisable(false);
                randomizeButton.setDisable(false);
                grid.setDisable(false);
                if (selectedDrawCount>0){
                    drawButton.setDisable(false);
                }
            }
        });

        fourSpotButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent a){
                typeOfSpotGame = 4;
                setMaxPresses(4);

                spotGameChosen.setText(String.valueOf(typeOfSpotGame));
                undoButton.setDisable(false);
                randomizeButton.setDisable(false);
                grid.setDisable(false);
                if (selectedDrawCount>0){
                    drawButton.setDisable(false);
                }

            }
        });

        eightSpotButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent a){
                typeOfSpotGame = 8;
                setMaxPresses(8);

                spotGameChosen.setText(String.valueOf(typeOfSpotGame));
                undoButton.setDisable(false);
                randomizeButton.setDisable(false);
                grid.setDisable(false);
                if (selectedDrawCount>0){
                    drawButton.setDisable(false);
                }

            }
        });

        tenSpotButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent a){
                typeOfSpotGame = 10;
                setMaxPresses(10);

                spotGameChosen.setText(String.valueOf(typeOfSpotGame));
                undoButton.setDisable(false);
                randomizeButton.setDisable(false);
                grid.setDisable(false);
                if (selectedDrawCount>0){
                    drawButton.setDisable(false);
                }

            }
        });


        //DRAW BUTTON ACTION HANDLER IS HERE
        //------------------------------------------------------------------------------------
        drawButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if ( count == maximumPressesAllowed ) {
                    int i = 0;
                    continueButton.setDisable(false);
                    currentDrawingsCount++;
                    currentDrawCount.setText(String.valueOf(currentDrawingsCount));
                    undoButton.setDisable(true);
                    randomizeButton.setDisable(true);
                    drawButton.setDisable(true);
                    oneDrawButton.setDisable(true);
                    twoDrawButton.setDisable(true);
                    threeDrawButton.setDisable(true);
                    fourDrawButton.setDisable(true);
                    oneSpotButton.setDisable(true);
                    fourSpotButton.setDisable(true);
                    eightSpotButton.setDisable(true);
                    tenSpotButton.setDisable(true);
                    while ( i < 20 )
                    {
                        // random number from 1-80
                        int randomNum = ran.nextInt(80)+1;

                        // makes sure no dupes (not sure why but i need this, bc set should do automatically)
                        if ( !winningNumbers.contains(randomNum) )
                        {
                            winningNumbers.add(randomNum);
                            displayWinningNumbers.getItems().add(randomNum);
                            i++;
                        }
                    }
                    // matching numbers contains winning numbers
                    matchingNumbers.addAll(winningNumbers);
                    // get the intersection of selected numbers & winning numbers
                    matchingNumbers.retainAll(selectedNumbers);
                    displayMatchingNumbers.getItems().addAll(matchingNumbers);
                    drawMoneyResult = drawMoneyResult + earningsMethod(selectedNumbers,winningNumbers,typeOfSpotGame);
                    drawEarningsValue.setText(String.valueOf(drawMoneyResult));
                    overallValue.setText(String.valueOf(overallCash));



                }
            }
        });

        //-------------------------------------------------------------------------
        //CONTINUE BUTTON WILL BE HERE
        //----------------------------------------------------------------------------
        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (currentDrawingsCount < selectedDrawCount ){
                    currentDrawingsCount++;
                    currentDrawCount.setText(String.valueOf(currentDrawingsCount));
                    winningNumbers.clear();
                    displayWinningNumbers.getItems().clear();
                    //randomize again
                    int i = 0;
                    while ( i < 20 )
                    {
                        int randomNum = ran.nextInt(80)+1;
                        // random number from 1-80
                        randomNum = ran.nextInt(80)+1;

                        // makes sure no dupes (not sure why but i need this, bc set should do automatically)
                        if ( !winningNumbers.contains(randomNum) )
                        {
                            winningNumbers.add(randomNum);
                            displayWinningNumbers.getItems().add(randomNum);
                            i++;
                        }

                    }
                    matchingNumbers.clear();
                    displayMatchingNumbers.getItems().clear();
                    matchingNumbers.addAll(winningNumbers);
                    // get the intersection of selected numbers & winning numbers
                    matchingNumbers.retainAll(selectedNumbers);
                    displayMatchingNumbers.getItems().addAll(matchingNumbers);
                    drawMoneyResult = drawMoneyResult + earningsMethod(selectedNumbers,winningNumbers,typeOfSpotGame);
                    drawEarningsValue.setText(String.valueOf(drawMoneyResult));
                    overallValue.setText(String.valueOf(overallCash));


                }
                else{
                    continueButton.setDisable(true);
                    playAgainButton.setDisable(false);
                }

            }
        });


        //----------------------------------------------------------------
        randomizeButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                while ( count < maximumPressesAllowed )
                {
                    // random number from 1-80
                    int randomNum = ran.nextInt(80)+1;

                    // makes sure no dupes (not sure why but i need this, bc set should do automatically)
                    if ( !selectedNumbers.contains(randomNum) )
                    {
                        grid.getChildren().get(randomNum-1).setDisable(true);
                        displaySelectedNumbers.getItems().add(randomNum);
                        buttonsVector.add((Button) grid.getChildren().get(randomNum-1));
                        grid.getChildren().get(randomNum-1).setStyle("-fx-background-color: blue;-fx-text-fill: white;");
                        buttonsValueVector.add(randomNum);
                        selectedNumbers.add(randomNum);
                        oneSpotButton.setDisable(true);
                        fourSpotButton.setDisable(true);
                        eightSpotButton.setDisable(true);
                        tenSpotButton.setDisable(true);

                        count++;
                    }
                }
            }
        });


        undoButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent a) {

                if (count>0) {

                    Button temp = buttonsVector.lastElement();
                    int tempInt = buttonsValueVector.lastElement();
                    temp.setDisable(false); //reenables
                    temp.setStyle(undoButton.getStyle());
                    undoMethod(buttonsVector, buttonsValueVector, displaySelectedNumbers);

                    if (count==1){
                        oneSpotButton.setDisable(false);
                        fourSpotButton.setDisable(false);
                        eightSpotButton.setDisable(false);
                        tenSpotButton.setDisable(false);
                        oneDrawButton.setDisable(false);
                        twoDrawButton.setDisable(false);
                        threeDrawButton.setDisable(false);
                        fourDrawButton.setDisable(false);

                    }
                    count--;
                }
            }
        });

        //------------------------------------------------------------
        //getting our buttons in a row
        //------------------------------------------------------------
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 10; c++) {
                int number = 10 * r + c + 1;
                Button button = new Button(String.valueOf(number));

                grid.add(button, c, r);

                button.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent a){
                        if ( count < maximumPressesAllowed ) {
                            button.setStyle("-fx-background-color: blue;-fx-text-fill: white;");
                            button.setDisable(true);
                            displaySelectedNumbers.getItems().add(number);
                            buttonsVector.add(button);
                            buttonsValueVector.add(number);
                            selectedNumbers.add(number);
                            count++;
                            oneSpotButton.setDisable(true);
                            fourSpotButton.setDisable(true);
                            eightSpotButton.setDisable(true);
                            tenSpotButton.setDisable(true);
                        }
                    }
                });

            }
        }
        grid.setDisable(true);

        //--------------------------------------------------------------------

        HBox drawsHbox = new HBox(10, oneDrawButton,twoDrawButton,threeDrawButton,fourDrawButton);
        VBox drawsVBox = new VBox(10, drawingsPerDrawLabel,drawsHbox);

        //--------------------------------------------------------------------
        HBox secondSection = new HBox(10,selectedNumsInfo,winningNumsInfo,matchingNumsInfo);
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setStyle("-fx-box-border: transparent;");
        scrollPane.setStyle("-fx-focus-color: transparent;");
        VBox rightInfo = new VBox(10,gridLabel,scrollPane,counterVBox,Buttons,secondSection,thirdRow);
        VBox leftButtons = new VBox(50, spotsVBox,drawsVBox);
        leftButtons.setPadding(new Insets(20, 0, 0, 20)); //margins around the whole grid

        rightInfo.setPadding(new Insets(20, 100, 0, 0)); //margins around the whole grid
        gameScenePane.setRight(rightInfo);
        gameScenePane.setCenter(leftButtons);


        //starting to do the gameplay mechanics here
        //-----------------------------------------------------------------

        playAgainButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent a){
                // loop through and clear grid of buttons
                for ( int i = 0; i < 80; i++ ) {
                    grid.getChildren().get(i).setDisable(false);
                    grid.getChildren().get(i).setStyle(playAgainButton.getStyle());
                }
                displaySelectedNumbers.getItems().clear();
                displayMatchingNumbers.getItems().clear();
                displayWinningNumbers.getItems().clear();

                maximumPressesAllowed = 0;
                count = 0;
                typeOfSpotGame = 0;
                currentDrawingsCount = 0;
                selectedDrawCount = 0;
                drawMoneyResult = 0;
                currentDrawCount.setText(String.valueOf(currentDrawingsCount));
                selectedDrawCounter.setText(String.valueOf(selectedDrawCount)+"x");
                spotGameChosen.setText(String.valueOf(typeOfSpotGame));

                displaySelectedNumbers.getItems().clear();
                displayMatchingNumbers.getItems().clear();
                displayWinningNumbers.getItems().clear();
                buttonsVector.clear();
                buttonsValueVector.clear();
                winningNumbers.clear();
                matchingNumbers.clear();
                selectedNumbers.clear();

                grid.setDisable(true);

                drawEarningsValue.setText(String.valueOf(drawMoneyResult));

                continueButton.setDisable(true);
                undoButton.setDisable(true);
                randomizeButton.setDisable(true);
                drawButton.setDisable(true);
                playAgainButton.setDisable(true);
                oneSpotButton.setDisable(false);
                fourSpotButton.setDisable(false);
                eightSpotButton.setDisable(false);
                tenSpotButton.setDisable(false);
                oneDrawButton.setDisable(false);
                twoDrawButton.setDisable(false);
                threeDrawButton.setDisable(false);
                fourDrawButton.setDisable(false);
            }
        });

        //-----------------------------------------------------------------
        gameScenePane.setTop(menu);
        return new Scene(gameScenePane,1080,950);
    }


    public Scene startupScene(){
        stage.setTitle("Main Menu");
        Button playButton = new Button();
        menu = new MenuBar();
        BorderPane startupPane = new BorderPane();

        // set up background image
        Image background = new Image("https://www.psdgraphics.com/wp-content/uploads/2015/07/casino-pattern.jpg");
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


        return new Scene(startupPane, 610,458);
    }

    //
    public Scene ruleScene(String from){
        stage.setTitle("Rules Page");

        Button returnButton = new Button("return");

        Vector<Text> rulesVector = new Vector<Text>();
        Text rule1;
        Text rule2;
        Text rule3;
        Text rule4;
        Text rule5;
        Text rule6;


        BorderPane pane = new BorderPane();
        Image background = new Image("https://www.psdgraphics.com/wp-content/uploads/2015/07/casino-pattern.jpg");
        ImageView b = new ImageView(background);
        pane.getChildren().addAll(b);

        //if statements so that we can see where the user came from
        if(from.equals("mainMenu")){
            returnButton.setOnAction(e-> stage.setScene(startupScene()));
        }
        else{
            returnButton.setOnAction(e-> stage.setScene(gameScene()));
        }
        rule1 = defaultText("1) Decide on how many Spots you would like to play for");
        rule2 = defaultText("2) Select the numbers you think will be chosen");
        rule3 = defaultText("3) Choose the amount of drawings you would like");
        rule4 = defaultText("4) Hit Draw when you are ready to see the winning #'s");
        rule5 = defaultText("5) Hit Continue to continue through the draws if any left");
        rule6 = defaultText("6) Choose whether to play again or exit the program");
        rulesVector.add(rule1);
        rulesVector.add(rule2);
        rulesVector.add(rule3);
        rulesVector.add(rule4);
        rulesVector.add(rule5);
        rulesVector.add(rule6);


        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Rules of the Game");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        title.setFill(Color.WHITE);

        vbox.getChildren().add(title);
        for (int i = 0; i < rulesVector.size(); i++){
            VBox.setMargin(rulesVector.get(i), new Insets(0, 0, 0, 8));
            vbox.getChildren().add(rulesVector.get(i)); //this takes care of our rules and the in the vbox
        }


        vbox.getChildren().add(returnButton); //adding return button to the bottom of the vbox


        pane.setLeft(vbox);
        stage.setTitle("Rules Page");

        return new Scene(pane,520,450);

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
        Image background = new Image("https://www.psdgraphics.com/wp-content/uploads/2015/07/casino-pattern.jpg");
        ImageView b = new ImageView(background);
        pane.getChildren().addAll(b);

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
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
        title.setFill(Color.WHITE);

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
