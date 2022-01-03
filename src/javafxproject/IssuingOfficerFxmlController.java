/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zakar
 */
public class IssuingOfficerFxmlController implements Initializable {

    @FXML
    private Button viewLC;
    @FXML
    private TableView<Table> tableView;
    @FXML
    private TableColumn<Table, String> userId;
    @FXML
    private TableColumn<Table, String> Lc;
    @FXML
    private Button viewDetails;
    @FXML
    private Button doc;
    @FXML
    private Button feedbackBtn;
    @FXML
    private TableView<Table> tableView1;
    @FXML
    private TableColumn<Table, String> userId1;
    @FXML
    private TableColumn<Table, String> Lc1;
    @FXML
    private Button viewDetails1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewLCOnButtonClick(ActionEvent event) throws IOException {
        tableView.setVisible(true);
        tableView1.setVisible(false);
        viewDetails.setVisible(true);
        viewDetails1.setVisible(false);
        IssuingOfficer i=new IssuingOfficer();
       
               ObservableList<Table> details = FXCollections.observableArrayList();
        i.ShowLC(details);  
      
        userId.setCellValueFactory(new PropertyValueFactory<Table, String>("userid"));
        Lc.setCellValueFactory(new PropertyValueFactory<Table, String>("userlc"));
        tableView.setItems(details);
        
    }

    @FXML
    private void viewDetailsBtnOnClick(ActionEvent event) throws IOException {
       
        if(tableView.getSelectionModel().getSelectedItem()!=null){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LCDetailsView.fxml"));
        Parent personViewParent = loader.load();
        
       
        LCDetailsViewController controller = loader.getController();       
        controller.initData(tableView.getSelectionModel().getSelectedItem());
        Stage window = new Stage();
        window.setTitle("LCDetailsView");
        window.setScene(new Scene(personViewParent));
        window.show();
        }
         else{
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("No Lc Selected");
            a.show();
        }
    }


    @FXML
    private void feedbackBtnOnClick(ActionEvent event) throws FileNotFoundException {
        tableView.setVisible(false);
        tableView1.setVisible(true);
        viewDetails.setVisible(false);
        viewDetails1.setVisible(true);
        IssuingOfficer i=new IssuingOfficer();
       
               ObservableList<Table> details = FXCollections.observableArrayList();
        i.ShowFeedBack(details);  
      
        userId1.setCellValueFactory(new PropertyValueFactory<Table, String>("userid"));
        Lc1.setCellValueFactory(new PropertyValueFactory<Table, String>("userlc"));
        tableView1.setItems(details);
    }

    @FXML
    private void viewDetailsBtnFeedbackOnClick(ActionEvent event) throws IOException {
        if(tableView1.getSelectionModel().getSelectedItem()!=null){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("feedBack.fxml"));
        Parent personViewParent = loader.load();
        
        //Parent personViewParent = FXMLLoader.load(getClass().getResource("FXMLScene2.fxml"));
        //Scene personViewScene = new Scene(personViewParent);
        
        //access the controller
        FeedBackController controller = loader.getController();       
        controller.initData(tableView1.getSelectionModel().getSelectedItem());
        Stage window = new Stage();
        window.setTitle("view feedBack");
        window.setScene(new Scene(personViewParent));
        window.show();
        }
         else{
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("No Lc Selected");
            a.show();
        }
    }
    
}
