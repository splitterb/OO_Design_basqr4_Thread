/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifcationexamples;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import taskers.*;

/**
 * FXML Controller class
 *
 * @author dalemusser
 */
public class NotificationsUIController implements Initializable, Notifiable {

    @FXML
    private TextArea textArea;
    
    private Task1 task1;
    private Task2 task2;
    private Task3 task3;
    
    @FXML Button button1;
    @FXML Button button2;
    @FXML Button button3;
    
    @FXML TextField textField1;
    @FXML TextField textField2;
    @FXML TextField textField3;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void start(Stage stage) {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if (task1 != null) task1.end();
                if (task2 != null) task2.end();
                if (task3 != null) task3.end();
            }
        });
    }
    
    @FXML
    public void startTask1(ActionEvent event) {
        if(task1 != null){
            if(task1.isAlive() == true){
                task1.end();
                button1.setText("Start Task 1");
                textField1.setText("STOPPED");
            } 
            else {
                task1 = new Task1(2147483647, 1000000);
                task1.setNotificationTarget(this);
                task1.start();
                button1.setText("End Task 1");
                textField1.setText("RUNNING");
            }
        }
        
        System.out.println("start task 1");
        if (task1 == null) {
            task1 = new Task1(2147483647, 1000000);
            task1.setNotificationTarget(this);
            task1.start();
            button1.setText("End Task 1");
            textField1.setText("RUNNING");
        }
    }
    
    @Override
    public void notify(String message) {
        if (message.equals("Task1 done.")) {
            task1 = null;
        }
        textArea.appendText(message + "\n");
    }
    
    @FXML
    public void startTask2(ActionEvent event) {
        if(task2 != null){
            if(task2.isAlive() == true){
                task2.end();
                button2.setText("Start Task 2");
                textField2.setText("STOPPED");
            } 
            else {
                task2 = new Task2(2147483647, 1000000);
                task2.setOnNotification((String message) -> {
                    textArea.appendText(message + "\n");
                });                
                task2.start();
                button2.setText("End Task 2");
                textField2.setText("RUNNING");
            }
        }
        
        System.out.println("start task 2");
        if (task2 == null) {
            task2 = new Task2(2147483647, 1000000);
            task2.setOnNotification((String message) -> {
                textArea.appendText(message + "\n");
            });
            
            task2.start();
            button2.setText("End Task 2");
            textField2.setText("RUNNING");
        }        
    }
    
    @FXML
    public void startTask3(ActionEvent event) {
         if(task3 != null){
            if(task3.isAlive() == true){
                task3.end();
                button3.setText("Start Task 3");
                textField3.setText("STOPPED");
            } 
            else {
                task3 = new Task3(2147483647, 1000000);
                task3.addPropertyChangeListener((PropertyChangeEvent evt) -> {
                    textArea.appendText((String)evt.getNewValue() + "\n");
                });                
                task3.start();
                button3.setText("End Task 3");
                textField3.setText("RUNNING");
            }
        }
        
        System.out.println("start task 3");
        if (task3 == null) {
            task3 = new Task3(2147483647, 1000000);
            // this uses a property change listener to get messages
            task3.addPropertyChangeListener((PropertyChangeEvent evt) -> {
                textArea.appendText((String)evt.getNewValue() + "\n");
            });
            
            task3.start();
            button3.setText("End Task 3");
            textField3.setText("RUNNING");
        }
    } 
}
