/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author zakar
 */
public class SignupController extends LoginController implements Initializable {

    @FXML
    private Button submit;
    @FXML
    private TextField fName;
    @FXML
    private TextField mName;
    @FXML
    private TextField address;
    @FXML
    private TextField nidNo;
    @FXML
    private TextField contactNo;
    @FXML
    private DatePicker dob;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private RadioButton transGenderRadioButton;

    private ToggleGroup tg, tg2;
    private User users;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField pass;
    private StringBuffer num = new StringBuffer();
    private int idnumer;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tg = new ToggleGroup();
        maleRadioButton.setToggleGroup(tg);
        femaleRadioButton.setToggleGroup(tg);
        transGenderRadioButton.setToggleGroup(tg);
        maleRadioButton.setSelected(true);
    }

    public User returnuser() {
        return users;
    }

    @FXML
    private void submitAndGologin(ActionEvent event) throws IOException {
        if (fname.getText().length() == 0 || lname.getText().length() == 0 || address.getText().length() == 0
                || fName.getText().length() == 0 || mName.getText().length() == 0 || dob.getValue().toString().length() == 0
                || radioButtonOnClick(event).length() == 0
                || contactNo.getText().length() == 0
                || nidNo.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Required All");
        } else {

            
                
            //pass&user id
            
            readDataFromBin("idPass.bin"); 
            
            idnumer = Integer.parseInt(num.toString());           
            idnumer += 1;
            String ID = fname.getText() + idnumer;
            String idpass=ID+","+pass.getText()+"\n";
            writeDataintoBin("idPass.bin",idpass);
            
            users = new User(ID, fname.getText(), lname.getText(), address.getText(), fName.getText(), mName.getText(), dob.getValue(), radioButtonOnClick(event), Long.parseLong(contactNo.getText()), Long.parseLong(nidNo.getText()));

            //users =new User(ID);
            //write to user.bin
            writeToBinary("user.bin", users, true);     
            
            Parent mainSceneParent = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene1 = new Scene(mainSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Log In");
            window.setScene(scene1);            
            window.show();
            
            static_label.setText("your ID is: "+ID);
        }
    }

    public static void appendUsingFileWriter(String filePath, String text) {
        File file = new File(filePath);
        FileWriter fr = null;
        try {
            // Below constructor argument decides whether to append or override
            fr = new FileWriter(file, true);
            fr.write(text);

        } catch (IOException e) {
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
            }
        }
    }

    @FXML
    private String radioButtonOnClick(ActionEvent event) {
        if (maleRadioButton.isSelected()) {
            return "Male";
        } else if (femaleRadioButton.isSelected()) {
            return "Female";
        } else if (transGenderRadioButton.isSelected()) {
            return "Transgender";
        }
        return null;
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

    public void writeDataintoBin(String fileName, String id) throws IOException {
        
        try (
                DataOutputStream dout
                = new DataOutputStream(new FileOutputStream(fileName, true))) {
            dout.writeUTF(id);
        } catch (FileNotFoundException ex) {
        }
    }

    @Override
    public void readDataFromBin(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.length() == 0) {
            DataOutputStream dout
                    = new DataOutputStream(new FileOutputStream(fileName));

            dout.writeUTF(100+","+"xxx" + "\n");
            num.append("100");

        }
        else{
            num = new StringBuffer();
        try (DataInputStream din
                = new DataInputStream(new FileInputStream(fileName))) {
            String user = null;
            String pas;
            while (din.available() > 0) {

                String Line = din.readUTF();
                String[] tokens;
                tokens = Line.split(",");
                 user = (tokens[0]);
                 pas=tokens[1];               
            }
            for(int i=0;i<user.length();i++){
            if (Character.isDigit(user.charAt(i))){
                num.append(user.charAt(i));
            }
        }
        } catch (FileNotFoundException e) {
        }
        }
    }
    public void readFromBinFileBtnOnClick(String str) {
         try {
             User s;
             ObjectInputStream in = new ObjectInputStream(
                 new FileInputStream("user.bin")
             );
            //outputTxtArea.setText(null);
            while(true){
                s = (User) in.readObject();
              
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProfileViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
