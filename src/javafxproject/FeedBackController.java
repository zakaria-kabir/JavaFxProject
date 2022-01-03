/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author zakar
 */
public class FeedBackController implements Initializable {

    @FXML
    private TextField nameTxt;
    @FXML
    private TextField BranchTxt;
    @FXML
    private TextField idTxt;
    @FXML
    private TextField PhoneNoTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField addressTxt;
    @FXML
    private TextArea msgTxt;
    @FXML
    private Button SubmitBtn;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SubmitBtnOnClick(ActionEvent event) {
        String s=msgTxt.getText();
        String txt=nameTxt.getText()+","+BranchTxt.getText()+","+idTxt.getText()+","+PhoneNoTxt.getText()+","+emailTxt.getText()+","+addressTxt.getText()+","+"feedback:"+ s.replaceAll("\n", " ")+"\n";
        String file="feedback.txt";
        appendUsingFileWriter(file, txt, true);
        autoHideDialogbox();
        
        
    }
    public void appendUsingFileWriter(String filePath, String text, boolean append) {
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
    public void autoHideDialogbox() {
        JOptionPane jop = new JOptionPane();
        jop.setMessage("Thank you for your feedBack");
	JDialog dialog = jop.createDialog(null, "Message");
	new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            dialog.dispose();
        }).start();
	dialog.setVisible(true);
    }

    void initData(Table selectedItem) {
        nameTxt.setEditable(false);
        BranchTxt.setEditable(false);
        idTxt.setEditable(false);
        PhoneNoTxt.setEditable(false);
        emailTxt.setEditable(false);
        addressTxt.setEditable(false);
        msgTxt.setEditable(false);
        SubmitBtn.setVisible(false);
        back.setVisible(true);
        String s=selectedItem.getUserlc();
        String[] t;
        t=s.split(",");
        nameTxt.setText(t[0]);
        BranchTxt.setText(t[1]);
        idTxt.setText(t[2]);
        PhoneNoTxt.setText(t[3]);
        emailTxt.setText(t[4]);
        addressTxt.setText(t[5]);
        
            String str=s.substring(s.indexOf("feedback:"));
        msgTxt.setText(str);
        
    }

    @FXML
    private void backBtnOnclick(ActionEvent event) {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();             
        window.close();
    }
}
