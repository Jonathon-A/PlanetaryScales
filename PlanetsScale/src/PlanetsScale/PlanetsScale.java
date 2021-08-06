//Page 391 in the JavaFX Textbook
package PlanetsScale;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlanetsScale extends Application {

    public Image planetImage = null;
    public float multiple;
    public long days;
    public boolean spin = false;
    public boolean size = true;

    @Override
    public void start(Stage primaryStage) {
        enterdetails();
        enterplanet();
        StackPane root = new StackPane();

        Scene scene = new Scene(root, 300, 300);

        PerspectiveCamera camera = new PerspectiveCamera(true);

        //Backs the camera away from the scene by 1000 units
        camera.setTranslateZ(-1000);

        //This is the range of which the camera will render objects
        camera.setNearClip(0.01);
        camera.setFarClip(20000.0);

        //The default field of view for the scene is 30 but change to suit
        camera.setFieldOfView(30);
        scene.setCamera(camera);

        //This sets up my sphere
        Sphere mysphere;
        if (size == true) {
            mysphere = new Sphere(200 * multiple);
        } else {
            mysphere = new Sphere(200);

        }

        mysphere.setTranslateX(-100);
        mysphere.setTranslateY(-100);
        mysphere.setTranslateZ(100);
        root.getChildren().add(mysphere);

        //This sets up the image of the earth to wrap around my sphere
        planetImage.isSmooth();
        PhongMaterial Phong = new PhongMaterial();
        Phong.setDiffuseMap(planetImage);
        mysphere.setMaterial(Phong);

        //This rotates my sphere
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(mysphere);
        if (spin == true) {
            rotate.setDuration(Duration.millis(TimeUnit.DAYS.toMillis(days)));
        } else {
            rotate.setDuration(Duration.millis(5000));
        }
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.setByAngle(-360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.play();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void enterdetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Accurate solar body Size(y/n)");
        String answer1 = null;
        if (input.hasNext()) {
            answer1 = input.next();
        } else {
            System.out.println("Invalid");
            enterplanet();
        }
        if (answer1.equalsIgnoreCase("y")) {
            size = true;
        } else {
            if (answer1.equalsIgnoreCase("n")) {
                size = false;
            } else {
                System.out.println("Invalid");
                enterplanet();
            }
        }

        System.out.println("Accurate solar body Spin(y/n)");
        String answer2 = null;
        if (input.hasNext()) {
            answer2 = input.next();
        } else {
            System.out.println("Invalid");
            enterplanet();
        }
        if (answer2.equalsIgnoreCase("y")) {
            spin = true;
        } else {
            if (answer2.equalsIgnoreCase("n")) {
                spin = false;
            } else {
                System.out.println("Invalid");
                enterplanet();
            }
        }
    }

    public void enterplanet() {
        Scanner input = new Scanner(System.in);

        System.out.println("");
        System.out.println("Pick a solar body to view in real time");
        System.out.println("1: Sun");
        System.out.println("2: Mercury");
        System.out.println("3: Venus");
        System.out.println("4: Earth");
        System.out.println("5: Mars");
        System.out.println("6: Jupiter");
        System.out.println("7: Saturn whithout rings");
        System.out.println("8: Uranus");
        System.out.println("9: Neptune");

        int choose;
        if (input.hasNextInt()) {
            choose = input.nextInt();
        } else {
            choose = 100;
        }

        System.out.println("");
        switch (choose) {
            case (1):
                planetImage = new Image("file:sun.jpg");
                multiple = 109;
                days = (long) 25.379995;
                break;
            case (2):
                planetImage = new Image("file:mercury.jpg");
                multiple = (float) 0.38;
                days = (long) 58.6462;
                break;
            case (3):
                planetImage = new Image("file:venus.jpg");
                multiple = (float) 0.95;
                days = (long) 243.0187;
                break;
            case (4):
                planetImage = new Image("file:earth.jpg");
                multiple = 1;
                days = (long) 0.99726968;
                break;
            case (5):
                planetImage = new Image("file:mars.jpg");
                multiple = (float) 0.53;
                days = (long) 1.02595675;
                break;
            case (6):
                planetImage = new Image("file:jupiter.jpg");
                multiple = (float) 10.8;
                days = (long) 0.41007;
                break;
            case (7):
                planetImage = new Image("file:saturn.jpg");
                multiple = 9;
                days = (long) 0.426;
                break;
            case (8):
                planetImage = new Image("file:uranus.jpg");
                multiple = (float) 3.93;
                days = (long) 0.71833;
                break;
            case (9):
                planetImage = new Image("file:neptune.jpg");
                multiple = (float) 3.87;
                days = (long) 0.67125;
                break;
            default:
                System.out.println("Invalid");
                enterplanet();
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
