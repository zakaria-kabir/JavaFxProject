/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javafxproject.SignupController.appendUsingFileWriter;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author zakar
 */
public class LoginController extends User implements Initializable {

    private Label label;
    @FXML
    private TextField userid;
    @FXML
    private PasswordField userpass;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private Button login;
    @FXML
    private Button loginguest;
    @FXML
    private Label showIDlabel;

    public String str = null;
    private ArrayList<String> userID = new ArrayList<>();
    private ArrayList<String> userpaSS = new ArrayList<>();

    public static Label static_label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        static_label = showIDlabel;
        combobox.getItems().addAll(
                "Applicant",
                "Benificiary",
                "Issuing Officer", "Advising Officer"
        );

    }

    boolean isValid(String id, String pass) {
        return userid.getText().equals(id) && userpass.getText().equals(pass);
    }

    @FXML
    private void userLogin(ActionEvent event) throws FileNotFoundException, IOException {
        String file = "idPass.bin";
        str = userid.getText();
        readDataFromBin(file);

        int index = 0;
        boolean login = false;
        for (String s : userID) {
            String s1 = userpaSS.get(index);
            index++;
            if (isValid(s, s1)) {
                login = true;
                String st = str + ".txt";
                File fname = new File(st);
                if(fname.length()==0){
                    fname.createNewFile();
                }
                break;
            }
        }
        boolean def=false;
        if(userid.getText().equals("100") && userpass.getText().equals("xxx")){
            def=true;
        }
        if (login && combobox.getValue().equals("Applicant")) {
            FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Applicant.fxml"));
        Parent personViewParent = loader.load();
        
        //Parent personViewParent = FXMLLoader.load(getClass().getResource("FXMLScene2.fxml"));
        Scene personViewScene = new Scene(personViewParent);
        
        //access the controller
        ApplicantController controller = loader.getController();
        controller.initData(str);        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Applicant");
        window.setScene(personViewScene);
        window.show();
        } 
        else if (def && combobox.getValue().equals("Issuing Officer")) {
            FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("IssuingOfficerFxml.fxml"));
        Parent personViewParent = loader.load();
        
        //Parent personViewParent = FXMLLoader.load(getClass().getResource("FXMLScene2.fxml"));
        Scene personViewScene = new Scene(personViewParent);
        
        //access the controller
        //ApplicantController controller = loader.getController();
        //controller.initData(str);        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Issuing Officer");
        window.setScene(personViewScene);
        window.show();
        }
         else if (def && combobox.getValue().equals("Advising Officer")) {
            FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdvisingOfficerFXML.fxml"));
        Parent personViewParent = loader.load();
        
        //Parent personViewParent = FXMLLoader.load(getClass().getResource("FXMLScene2.fxml"));
        Scene personViewScene = new Scene(personViewParent);
        
        //access the controller
        //ApplicantController controller = loader.getController();
        //controller.initData(str);        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Advising Officer");
        window.setScene(personViewScene);
        window.show();
        }
        else {
            JOptionPane.showMessageDialog(null, "Incorrect username or password");
            userid.setText("");
            userpass.setText("");
        }
    }

    @FXML
    private void guestLogin(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("guestHome.fxml"));
        Scene applicantPage = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(applicantPage);
        app_stage.setTitle("Guest Home");
        app_stage.show();
    }

    @FXML
    private void userSignup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene applicantPage = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(applicantPage);
        app_stage.setTitle("Sign Up");
        app_stage.show();
    }

    @FXML
    private String selectedUser(ActionEvent event) {
        return combobox.getValue();
    }

    @FXML
    private void idTxtOnMouseClick(MouseEvent event) {
        userid.setText(null);
    }

    @FXML
    private void PassOnMouseClick(MouseEvent event) {
        userpass.setText(null);
    }

    public void readDataFromBin(String fileName) throws IOException {
        try (DataInputStream din
                = new DataInputStream(new FileInputStream(fileName))) {
            String user = null;
            String pas;
            while (din.available() > 0) {

                String line = (din.readUTF());
                String Line = line.replaceAll("\\s+", "");
                String[] tokens;
                tokens = Line.split(",");
                user = (tokens[0]);
                userID.add(user);
                pas = tokens[1];
                userpaSS.add(pas);
            }

        } catch (FileNotFoundException e) {

        }
    }

}
