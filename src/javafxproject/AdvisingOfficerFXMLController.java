/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author zakar
 */
public class AdvisingOfficerFXMLController implements Initializable {

    @FXML
    private Button ViewLCBtn;
    @FXML
    private Button view;
    AdvisingOfficer a=new AdvisingOfficer();
    @FXML
    private TableView<Table> tableView;
    @FXML
    private TableColumn<Table, String> name;
    @FXML
    private TableColumn<Table, String> Lc;
    @FXML
    private TableColumn<Table, String> select;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ViewLCBtnOnClick(ActionEvent event) throws IOException {    
        ObservableList<Table> details = FXCollections.observableArrayList();
        a.ShowLC(details);  
      
        name.setCellValueFactory(new PropertyValueFactory<Table, String>("userid"));
        Lc.setCellValueFactory(new PropertyValueFactory<Table, String>("userlc"));
        select.setCellValueFactory(new PropertyValueFactory<Table, String>("select"));
        tableView.setItems(details);
    }

    @FXML
    private void viewBtnOnClick(ActionEvent event) {
        
    }
    
}
