/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zakar
 */
public class GuestHomeController implements Initializable {

    @FXML
    private BorderPane borderid;
    @FXML
    private MenuItem importLCmenuitm;
    @FXML
    private MenuItem exportLcmenuItm;
    @FXML
    private MenuItem BtBmenuitm;
    @FXML
    private MenuItem docAgPaymenuItm;
    @FXML
    private MenuItem aboutMenuItm;
    @FXML
    private MenuItem BODMenuItm;
    @FXML
    private MenuItem CoCMenuItm;
    @FXML
    private MenuItem contactMenuitm;
    @FXML
    private Button servicecrgBtn;
    @FXML
    private Button ForeignCurrencyRatesBtn;
    @FXML
    private Button feedbackBtn;
    @FXML
    private Pane Pane;
    @FXML
    private ScrollPane showtxt;
    guest g=new guest();
    @FXML
    private ImageView imgView;
    @FXML
    private TableView<Currency_Rates> tableView;
    @FXML
    private TableColumn<Currency_Rates, String> currCol;
    @FXML
    private TableColumn<Currency_Rates, Double> buyCol;
    @FXML
    private TableColumn<Currency_Rates, Double> sellCol;
    @FXML
    private Button logOutBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void importLCmenuitmOnClick(ActionEvent event) {
        showtxt.setVisible(true);
                imgView.setVisible(false);
                tableView.setVisible(false);
        Text txt=new Text(g.showeimportLC());
        txt.wrappingWidthProperty().bind(Pane.widthProperty());
        showtxt.setFitToWidth(true);
        showtxt.setContent(txt);
    }

    @FXML
    private void exportLcmenuItmOnClick(ActionEvent event) {
        showtxt.setVisible(true);
                imgView.setVisible(false);
                tableView.setVisible(false);
        Text txt=new Text(g.showexportLC());
        txt.wrappingWidthProperty().bind(Pane.widthProperty());
        showtxt.setFitToWidth(true);
        showtxt.setContent(txt);
    }

    @FXML
    private void BtBmenuitmOnClick(ActionEvent event) {
        showtxt.setVisible(true);
        imgView.setVisible(false);
        tableView.setVisible(false);
        Text txt=new Text(g.showBacktoBackLC());
        txt.wrappingWidthProperty().bind(Pane.widthProperty());
        showtxt.setFitToWidth(true);
        showtxt.setContent(txt);
    }

    @FXML
    private void docAgPaymenuItmOnClick(ActionEvent event) {
        showtxt.setVisible(false);
        imgView.setVisible(true);
        tableView.setVisible(false);
        Image img=new Image("resource/DocForPay.jpg");
        imgView.setImage(img);
    }

    @FXML
    private void aboutMenuItmONClick(ActionEvent event) {
        showtxt.setVisible(true);
        imgView.setVisible(false);
        tableView.setVisible(false);
        Text txt=new Text("The Bank was initially emerged in the Banking scenario of the then East Pakistan as Eastern Mercantile Bank Limited at the initiative of some Bangalee entrepreneurs in the year 1959 under Bank Companies Act 1913 for providing credit to the Bangalee entrepreneurs who had limited access to the credit in those days from other financial institutions. After independence of Bangladesh in 1972 this Bank was nationalized as per policy of the Government and renamed as Pubali Bank. Subsequently due to changed circumstances this Bank was denationalized in the year 1983 as a private bank and renamed as Pubali Bank Limited. Since inception this Bank has been playing a vital role in socio-economic, industrial and agricultural development as well as in the overall economic development of the country through savings mobilization and investment of funds.\n" +
"At Present, Pubali Bank is the largest private commercial bank having 482 branches and it has the largest real time centralized online banking network.");
        txt.wrappingWidthProperty().bind(Pane.widthProperty());
        showtxt.setFitToWidth(true);
        showtxt.setContent(txt);
    }


    @FXML
    private void BODMenuItmOnLcik(ActionEvent event) {
        showtxt.setVisible(false);
        imgView.setVisible(true);
        tableView.setVisible(false);
        Image img=new Image("resource/BoD.jpg");
        imgView.setImage(img);
    }

    @FXML
    private void CoCMenuItmOnClick(ActionEvent event) {
        showtxt.setVisible(true);
        imgView.setVisible(false);
        tableView.setVisible(false);
        Text txt=new Text(g.showCoC());
        txt.wrappingWidthProperty().bind(Pane.widthProperty());
        showtxt.setFitToWidth(true);
        showtxt.setContent(txt);
    }
    @FXML
    private void contctusOnClick(ActionEvent event) {
        showtxt.setVisible(true);
        imgView.setVisible(false);
        tableView.setVisible(false);
        Text txt=new Text(g.contactUs());
        txt.wrappingWidthProperty().bind(Pane.widthProperty());
        showtxt.setFitToWidth(true);
        showtxt.setContent(txt);
    }

    @FXML
    private void servicecrgBtnOnClick(ActionEvent event) {
        showtxt.setVisible(true);
        imgView.setVisible(false);
        tableView.setVisible(false);
        Text txt=new Text(g.servicecrg());
        txt.wrappingWidthProperty().bind(Pane.widthProperty());
        showtxt.setFitToWidth(true);
        showtxt.setContent(txt);
    }

    @FXML
    private void ForeignCurrencyRatesBtnOnclick(ActionEvent event) {
        showtxt.setVisible(false);
        imgView.setVisible(false);
        tableView.setVisible(true);
        ObservableList<Currency_Rates> details = FXCollections.observableArrayList();
        File file = new File("Currency_Rates.txt");
        Scanner sc;
        String s = null;
        try {
            sc = new Scanner(file);
            
            while(sc.hasNextLine()){
                s=sc.nextLine();
                String[] token;
                token=s.split(",");
                Currency_Rates c=new Currency_Rates(token[0], Double.parseDouble(token[1]), Double.parseDouble(token[2]));
                details.add(c);
            }
           
        } catch (FileNotFoundException ex) {
            
        }   
        currCol.setCellValueFactory(new PropertyValueFactory<>("curr"));
        buyCol.setCellValueFactory(new PropertyValueFactory<>("buying"));
        sellCol.setCellValueFactory(new PropertyValueFactory<>("selling"));
        tableView.setItems(details);
    }

    @FXML
    private void feedbackBtnOnclick(ActionEvent event) throws IOException {
        showtxt.setVisible(false);
        imgView.setVisible(false);
        tableView.setVisible(false);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("feedBack.fxml"));
        Parent personView = loader.load();           
        Stage window = new Stage();
        window.setTitle("FeedBack");
        window.setScene(new Scene(personView));
        window.show();
    }

    @FXML
    private void logOutBtnOnclick(ActionEvent event) throws IOException {
          Parent mainSceneParent = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene1 = new Scene(mainSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Log In");
            window.setScene(scene1);            
            window.show();
    }

    
    
}
