import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;


public class Main extends Application {

    private Scene mainMenuScene;
    private Scene scoresScene;
    private Scene gameScene;
    private Game game;
    private AnimationTimer timer;
    private ArrayList<String> scores = new ArrayList<String>();
    private ListView<String> listScores = new ListView<>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        scores.add("#1-jean-12");
        scores.add("#2-jeanne-8");

        double WINDOW_WIDTH = 640.0;
        double WINDOW_HEIGHT = 480.0;

        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        //3 scenes

        //Main menu
        Group rootMainMenu = new Group();
        mainMenuScene = new Scene(rootMainMenu, WINDOW_WIDTH,WINDOW_HEIGHT);
        Image imageLogo = new Image("file:logo.png");
        ImagePattern logoPattern = new ImagePattern(imageLogo);

        mainMenuScene.setFill(logoPattern);

        Button newGame = new Button("Nouvelle Partie!");
        newGame.setMinWidth(60);
        newGame.setLayoutX(320-60);
        newGame.setLayoutY(335);
        newGame.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

                primaryStage.setScene(gameScene);
                game = new Game();
                timer.start();

            }
        });

        Button scoresBtn = new Button("Meilleurs Scores");
        scoresBtn.setMinWidth(60);
        scoresBtn.setLayoutX(320-60);
        scoresBtn.setLayoutY(385);
        scoresBtn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

                primaryStage.setScene(scoresScene);

            }
        });

        rootMainMenu.getChildren().add(newGame);
        rootMainMenu.getChildren().add(scoresBtn);

        //Scores scene
        Group rootScores = new Group();
        scoresScene = new Scene(rootScores,WINDOW_WIDTH, WINDOW_HEIGHT);

        Text scoreMenuText = new Text("Meilleurs scores");
        scoreMenuText.setFont(Font.font(30));

        scoreMenuText.setLayoutY(40);
        scoreMenuText.setLayoutX(215);
        rootScores.getChildren().add(scoreMenuText);

        Button mainMenu = new Button("Menu");
        mainMenu.setMinWidth(60);
        mainMenu.setLayoutX(287);
        mainMenu.setLayoutY(440);
        mainMenu.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

                primaryStage.setScene(mainMenuScene);

            }
        });

        listScores.setLayoutY(60);
        listScores.setLayoutX(30);
        listScores.setMaxHeight(310);
        listScores.setMinWidth(580);
        rootScores.getChildren().add(mainMenu);
        updateScoresTable();
        rootScores.getChildren().add(listScores);

        Text addScoreTextNom = new Text("Votre nom : ");
        addScoreTextNom.setFont(Font.font(20));
        addScoreTextNom.setLayoutY(410);
        addScoreTextNom.setLayoutX(120);
        rootScores.getChildren().add(addScoreTextNom);

        Text addScoreTextPoints = new Text("a fait 23 points!");
        addScoreTextPoints.setFont(Font.font(20));
        addScoreTextPoints.setLayoutY(410);
        addScoreTextPoints.setLayoutX(350);
        rootScores.getChildren().add(addScoreTextPoints);

        //Game scene
        Group rootGame = new Group();
        gameScene = new Scene(rootGame,WINDOW_WIDTH, WINDOW_HEIGHT);
        gameScene.setFill(Color.DARKBLUE);

        Image imageHp = new Image("file:00.png");

        ImageView hp1 = new ImageView(imageHp);
        hp1.setFitWidth(30);
        hp1.setFitHeight(30);
        hp1.setX(250);
        hp1.setY(100);
        ImageView hp2 = new ImageView(imageHp);
        hp2.setFitWidth(30);
        hp2.setFitHeight(30);
        hp2.setX(305);
        hp2.setY(100);
        ImageView hp3 = new ImageView(imageHp);
        hp3.setFitWidth(30);
        hp3.setFitHeight(30);
        hp3.setX(360);
        hp3.setY(100);

        rootGame.getChildren().add(hp1);
        rootGame.getChildren().add(hp2);
        rootGame.getChildren().add(hp3);

        String labelText = String.valueOf(0);
        Text scoreText = new Text(labelText);
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(Font.font(30));

        scoreText.setLayoutX(315);
        scoreText.setLayoutY(50);
        rootGame.getChildren().add(scoreText);

        Image cursor = new Image("file:cible.png");

        ImageCursor imageCursor = new ImageCursor(cursor);
        imageCursor.getBestSize(50, 50);
        gameScene.setCursor(imageCursor);


        timer = new AnimationTimer() {
            private long lastTime = 0;
            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }
                long deltaT = now - lastTime;

                try {
                    String labelText = String.valueOf(game.getScore());
                    scoreText.setText(labelText);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                if (game.getHp() <= 0){

                    if (scores.size() == 10){
                        if (game.getScore() >= Integer.parseInt(scores.get(9).split("-")[2])){

                        }
                    }

                    addScore("bob", 10);
                    updateScoresTable();
                    primaryStage.setScene(scoresScene);
                    timer.stop();
                }

                lastTime=now;
            }
        };

        primaryStage.setScene(mainMenuScene);
        primaryStage.setTitle("Fish Hunt");
        primaryStage.show();


    }


    public static void main(String[] args){
        launch(args);
    }

    private void addScore(String name, int score){

        if (scores.size() > 9) {
            scores.remove(9);
        }

        int count = 0;


        for (int i = 0; i < scores.size(); i++){

            String[] arr = scores.get(i).split("-");

            if (score < Integer.parseInt(arr[2])){
                count += 1;
            }
        }

        scores.add(count, "#" + String.valueOf(count+1) + "-" + name + "-" + String.valueOf(score));

        for (int i = count + 1; i < scores.size(); i++){
            String temp = scores.get(i);
            String[] arr = temp.split("-");
            scores.set(i, "#" + String.valueOf(i + 1) + "-" +  arr[1] + "-" + arr[2]);
        }

    }

    private void updateScoresTable(){
        listScores.getItems().setAll(scores);
    }
}
