package src.Field;

import src.Enemy.Enemy;
import src.Enemy.SmallerEnemy;
import src.Enemy.TankerEnemy;
import src.GameObj;
import src.Main;
import src.Menu.Scenes;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import src.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameField extends GameStage implements Parameter{
    public static boolean startLevel = false;
    public static boolean startGame = false;
    public static List<GameObj> gameObjects = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static List<Point> PointsCanNotBuild= new ArrayList<>();

    public static int level ;


    public static void creatGameField(Stage stage) {
        Main.scene1 = Scenes.menuGame(stage);
        stage.setScene(Main.scene1);
        level = 1;
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(5000), event -> {
            enemies.addAll(SmallerEnemy.listSoldiers());
            enemies.addAll(TankerEnemy.listTanks());
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(2);
        timeline.play();
    }

    public static void setPointCanNotBuild() {
        List<String> pointCanNotBuild = new ArrayList<>(Arrays.asList("023", "025", "003", "047", "299", "048", "001", "027", "002", "004", "026", "046", "218", "244", "265", "240", "242"));
        for (int i = 0; i < MAP_SPRITES.length; i++) {
            for (int j = 0; j < MAP_SPRITES[i].length; j++) {
                if (pointCanNotBuild.contains(MAP_SPRITES[i][j])) {
                    PointsCanNotBuild.add(new Point(squareHeight * j, squareWidth * i));
                }
            }
        }
    }

    public static final String[][] MAP_SPRITES = new String[][] {
            { "024", "025", "023", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "242", "240", "114", "241" },
            { "024", "025", "023", "024", "024", "003", "047", "047", "047", "047", "004", "024", "024", "024", "242", "240", "241", "241" },
            { "041", "025", "023", "024", "024", "025", "299", "001", "001", "002", "023", "024", "024", "024", "242", "240", "241", "241" },
            { "024", "025", "023", "024", "024", "025", "023", "024", "024", "025", "023", "024", "024", "024", "242", "240", "241", "109" },
            { "024", "025", "023", "024", "024", "025", "023", "045", "024", "025", "023", "024", "024", "024", "242", "240", "241", "241" },
            { "024", "025", "023", "024", "024", "025", "023", "045", "024", "025", "023", "024", "024", "024", "242", "240", "241", "241" },
            { "024", "025", "046", "047", "047", "048", "023", "024", "024", "025", "023", "024", "024", "024", "242", "240", "241", "241" },
            { "024", "026", "001", "001", "001", "001", "027", "024", "024", "025", "046", "047", "047", "047", "265", "240", "241", "098" },
            { "024", "024", "024", "024", "024", "024", "024", "024", "024", "026", "001", "001", "001", "001", "218", "244", "098", "098" },
            { "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024" },
            { "024", "024", "024", "024", "039", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024", "024" }

    };


    //diem dinh huong
    public static final Point[] wayPoints = new Point[] {
            new Point(14 * squareWidth + 32, 7 * squareHeight + 32),
            new Point(9 * squareWidth + 32, 7 * squareHeight + 32),
            new Point(9 * squareWidth + 32, 1 * squareHeight + 32),
            new Point(5 * squareWidth + 32, 1 * squareHeight + 32),
            new Point(5 * squareWidth + 32, 6 * squareHeight + 32),
            new Point(1 * squareWidth + 32, 6 * squareHeight + 32),
            new Point(1 * squareWidth + 32,-1 * squareHeight + 00),
    };

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static void drawMap(GraphicsContext gc) {
        for (int i = 0; i < MAP_SPRITES.length; i++) {
            for (int j = 0; j < MAP_SPRITES[i].length; j++) {
                gc.drawImage(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile" + MAP_SPRITES[i][j] + ".png"), j * squareWidth, i * squareHeight);
            }
        }
        gc.drawImage(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile137.png") , 80 + 14 * squareWidth,  10 * squareHeight);
        gc.drawImage(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile136.png") , 128 + 14 *squareWidth, 30 + 9 * squareHeight );
        gc.drawImage(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile134.png") , 4 * squareWidth  + 25, 8 * squareHeight + 25);
        gc.drawImage(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile134.png") , 6 * squareWidth , 9 * squareHeight );
        gc.drawImage(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile131.png") , 11 * squareWidth , 0);
        gc.drawImage(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile130.png") , 12 * squareWidth , 32 );
    }

    //update vi tri enemy
    public static void render(GraphicsContext gc) {
        drawMap(gc);
        gameObjects.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
    }

    public static void update() {
        gameObjects.forEach(GameObj::update);
        enemies.forEach(GameObj::update);
    }

    public static void reset(){
        Main.scene1 = Scenes.menuGame(Main.stage);
        Main.stage.setScene(Main.scene1);
    }
}
