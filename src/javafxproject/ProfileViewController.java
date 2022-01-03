/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zakar
 */
public class ProfileViewController implements Initializable {

    private Label header;
    @FXML
    private TextField nameField;
    @FXML
    private TextField fNameField;
    @FXML
    private TextField mNameField;
    @FXML
    private TextField contactNoField;
    @FXML
    private TextArea addressField;
    @FXML
    private MenuItem closesystem;
    @FXML
    private Label HeaderLabel;
    @FXML
    private Label BalanceLabel;
    @FXML
    private Label Balance;
    @FXML
    private Label noOfLc;
    @FXML
    private Label ACcreatedDateLabel;
    private String username=null;
    @FXML
    private Label nidField;
    @FXML
    private Label userNameField;
    @FXML
    private Label dobField;
    @FXML
    private Label genderField;
    private User user;
    private ArrayList<User> arr=new ArrayList<>();    
    /**
     * Initializes the controller class.
     */
    public void initData(String str,User u,ArrayList<User> userArray) {
        user=u;       
        arr=userArray;
        username=str;
        HeaderLabel.setText(username);
        nameField.setText(u.getfname()+" "+u.getlname());
        fNameField.setText(u.getFname());
        mNameField.setText(u.getMname());
        contactNoField.setText(u.getContact());
        addressField.setText(u.getaddress());
        nidField.setText(u.getNid());
        dobField.setText(u.getDate());
        genderField.setText(u.getGender());
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    @FXML
    private void CloseSystem(ActionEvent event) {      
        System.exit(0);
    }

    @FXML
    private void BackToPreviousScene(ActionEvent event) throws IOException {  
       
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();             
        window.close();
    }
    public void writeToBinary(String filename, User obj, boolean append) {
       File file = new File(filename);
        ObjectOutputStream out = null;

        try {
            if (!file.exists() || !append) {
                out = new ObjectOutputStream(new FileOutputStream(filename));
            } else {
                out = new AppendableObjectOutputStream(new FileOutputStream(filename, append));
            }
            out.writeObject(obj);
            out.close();
        } catch (IOException e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
    }


    private static class AppendableObjectOutputStream extends ObjectOutputStream {

        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
        }
    }
    
}
