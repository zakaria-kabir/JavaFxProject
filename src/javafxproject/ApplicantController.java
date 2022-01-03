/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author zakar
 */
public class ApplicantController implements Initializable {

    @FXML
    private Button openLc;
    @FXML
    private Pane paneform;
    @FXML
    private Button LcSubmit;
    @FXML
    private TextArea ScrollLabel;
    @FXML
    private Button submission;
    @FXML
    private TextField sellername;
    @FXML
    private TextField contactno;
    @FXML
    private TextField bussinessname;
    @FXML
    private TextField email;
    @FXML
    private Button edit;
    @FXML
    private Button ok;
    @FXML
    private Button logout;
    @FXML
    private Button download;
    @FXML
    private Button profile;
    private String s;
    //User u;
    public User u = null;
    public ArrayList<User> userArray=new ArrayList<>();
    @FXML
    private Button Doc;
    ArrayList<String> fileTypeList;  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         fileTypeList = new ArrayList<String>();
        fileTypeList.add("*.txt");
        fileTypeList.add("*.doc");
        fileTypeList.add("*.docx");
        fileTypeList.add("*.TXT");
        fileTypeList.add("*.DOC");
        fileTypeList.add("*.DOCX");
    }

    public boolean isFilledup() {
        return sellername.getText().length() == 0 || contactno.getText().length() == 0 || bussinessname.getText().length() == 0 || email.getText().length() == 0;
    }

    @FXML
    private void createLc(ActionEvent event) {
        ScrollLabel.setVisible(false);
        paneform.setVisible(true);
        edit.setVisible(false);
        ok.setVisible(false);
        download.setVisible(false);
    }

    public void autoHideDialogbox() {
        JOptionPane jop = new JOptionPane();
        jop.setMessage("All records are Submitted");
	JDialog dialog = jop.createDialog(null, "Message");
	new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            dialog.dispose();
        }).start();
	dialog.setVisible(true);
    }

    @FXML
    private void submitLC(ActionEvent event) {
        String st = s + ".txt";
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        if (isFilledup()) {
            JOptionPane.showMessageDialog(null, "All Fields Required");
        } else {
            String text = formatter.format(date) + "," + sellername.getText() + "," + contactno.getText() + "," + bussinessname.getText() + "," + email.getText() + "\n";
            File f = new File(st);
            appendUsingFileWriter(st, text, true);
            
            sellername.setText("");
            contactno.setText("");
            bussinessname.setText("");
            email.setText("");
            paneform.setVisible(false);
            autoHideDialogbox();
        }
    }

    @FXML
    private void showallLc(ActionEvent event) throws FileNotFoundException {
        paneform.setVisible(false);
        ScrollLabel.setVisible(true);
        
        String st = s + ".txt";
        File f = new File(st);
        Scanner sc;
        String str = null;
        try {
            sc = new Scanner(f);
            ScrollLabel.setText(null);
            while (sc.hasNextLine()) {
                str = sc.nextLine();
                ScrollLabel.appendText(str + "\n");
                //outputTxtArea.appendText("\n");               
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ApplicantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        edit.setVisible(true);
        ok.setVisible(true);
        download.setVisible(true);
    }

    public static void appendUsingFileWriter(String filePath, String text, boolean append) {
        File file = new File(filePath);
        FileWriter fr = null;
        try {
            // Below constructor argument decides whether to append or override
            fr = new FileWriter(file, append);
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
    private void editLc(ActionEvent event) {
        ScrollLabel.setEditable(true);

    }

    @FXML
    private void editok(ActionEvent event) {
        ScrollLabel.setEditable(false);
        String st = s + ".txt";
        File f = new File(st);
        String text = ScrollLabel.getText();
        appendUsingFileWriter(st, text, false);

    }

    @FXML
    private void loggedout(ActionEvent event) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene1 = new Scene(mainSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Log In");
            window.setScene(scene1);            
            window.show();
            
    }
    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ApplicantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void saveEtittedTextButtonOnClick(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
        fc.setTitle("Save");
        File f = fc.showSaveDialog(null);
        String text = ScrollLabel.getText();
        saveTextToFile(text, f);        
    }

    @FXML
    private void viewProfile(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("profileView.fxml"));
        Parent personView = loader.load();
        
        //Parent personViewParent = FXMLLoader.load(getClass().getResource("FXMLScene2.fxml"));
        //Scene personViewScene = new Scene(personViewParent);
        
        //access the controller
        ProfileViewController controller = loader.getController();
        controller.initData(s,u,userArray);      
        Stage window = new Stage();
        window.setTitle("Profile");
        window.setScene(new Scene(personView));
        window.show();
    }

    public void initData(String str) {
        s=str;
        readFromBinFileBtnOnClick(s);
    }
    public void readFromBinFileBtnOnClick(String str) {
         try {
             
             ObjectInputStream in = new ObjectInputStream(
                 new FileInputStream("user.bin")
             );
            //outputTxtArea.setText(null);
            while(true){
                
                User x=(User) in.readObject();
                
                if(x.getid().equals(str)){              
                    u=x;                
                }
                userArray.add(x);
            }
        } catch (IOException | ClassNotFoundException ex) {
           // Logger.getLogger(ProfileViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DocBtnOnCLick(ActionEvent event) {
        ScrollLabel.setVisible(true);
        FileChooser fc = new FileChooser();
        
        fc.getExtensionFilters().add(new ExtensionFilter("Text files", fileTypeList));
        //File f2 = fc.showSaveDialog(null);
        File f = fc.showOpenDialog(null);
        if(f != null){
            try {
                Scanner sc = new Scanner(f);
                String str="";
                while(sc.hasNextLine()){
                    str+=sc.nextLine()+"\n";
                }
                ScrollLabel.setText(str);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ApplicantController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
