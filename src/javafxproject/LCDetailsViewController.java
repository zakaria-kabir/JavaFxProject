/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zakar
 */
public class LCDetailsViewController implements Initializable {

    @FXML
    private Label nameL;
    @FXML
    private Label ContactL;
    @FXML
    private Label BnameL;
    @FXML
    private Label EmailL;
    @FXML
    private Label dateL;
    @FXML
    private Label priceL;
    @FXML
    private ScrollPane itemsL;
    @FXML
    private Button Reject;
    @FXML
    private Button Accept;
    public Table t=new Table();
    @FXML
    private Label id;
    IssuingOfficer i=new IssuingOfficer();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void initData(Table selectedItem) {
        t=selectedItem;
        String s=t.getUserid();
        String st=t.getUserlc();
        id.setText(s);
        String[] token;
        token=st.split(",");
        dateL.setText(token[0]);
        nameL.setText(token[1]);
        ContactL.setText(token[2]);
        BnameL.setText(token[3]);
        EmailL.setText(token[4].replace("(Rejected)", "").replace("(Accepted)", ""));
    }

    @FXML
    private void rejectBtnOnClick(ActionEvent event) throws IOException {
        i.AcceptLC(id.getText()+".txt", t.getUserlc()+" (Rejected)", t.getUserlc());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();             
        window.close();
    }

    @FXML
    private void acceptBtnOnclick(ActionEvent event) throws IOException {
        i.AcceptLC(id.getText()+".txt", t.getUserlc()+" (Accepted)", t.getUserlc());
        i.uploadToSwift(id.getText()+","+t.getUserlc()+"\n");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();             
        window.close();
    }
    
}
