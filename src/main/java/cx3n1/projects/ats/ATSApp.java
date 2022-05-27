package cx3n1.projects.ats;
//Auto Timed Shutdown System

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;


public class ATSApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ATSApp.class.getResource("Config-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 490, 302);

        stage.setTitle("ATS config");
        stage.setResizable(false);
        stage.requestFocus();
        stage.toFront();

        stage.setScene(scene);
        stage.show();
    }


    @SneakyThrows
    public static void main(String[] args) {
        ATSWatchman.initialize();
        ATSWatchman.LOGIC_THREAD = new Thread(() -> {
            try {
                ATSLogic.mainLogic();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ATSWatchman.LOGIC_THREAD.start();
        launch();
    }
}

