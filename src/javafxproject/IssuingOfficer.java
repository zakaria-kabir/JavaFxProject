/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author zakar
 */
public class IssuingOfficer {
    


    public void ShowLC(ObservableList<Table> details) throws IOException{
        ArrayList<String> list=new ArrayList<>();
        readDataFromBin("idPass.bin",list);
        try {
            for(String x:list){               
        File f = new File(x+".txt");
        Scanner sc;
        String str = null;
        
            sc = new Scanner(f);           
            while (sc.hasNextLine()) {
                str = sc.nextLine(); 
                       
                details.add(new Table(x, str));
            }
        } 
        }catch (FileNotFoundException ex) {
            Logger.getLogger(ApplicantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AcceptLC(String FilePath,String newString, String oldString ) throws FileNotFoundException, IOException{
        File f = new File(FilePath);
        Scanner sc;
        String oldContent = "";
        
            sc = new Scanner(f);           
            while (sc.hasNextLine()) {
                oldContent = oldContent+sc.nextLine()+"\n"; 
             
            }
            sc.close();
            String newContent = oldContent.replaceAll(oldString, newString);
            
            FileWriter writer = new FileWriter(f);
             
            writer.write(newContent);
            writer.close();
    }
    public void readDataFromBin(String fileName,Collection<String> list) throws IOException {
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
                if(user.equals("100")){
                    continue;
                }
                else{
                list.add(user);
                }
                
                //pas = tokens[1];
                //userpaSS.add(pas);
            }

        } catch (FileNotFoundException e) {

        }
    }
    public void uploadToSwift(String text){
        String seller = "seller.txt";
        File ff = new File(seller);
        appendUsingFileWriter(seller, text, true);
    }
    public void appendUsingFileWriter(String filePath, String text, boolean append) {
        File file = new File(filePath);
        FileWriter fr = null;
        try {
            // Below constructor argument decides whether to append or override
            fr = new FileWriter(file, append);
            fr.write(text.replace("(Rejected)", "").replace("(Accepted)", ""));

        } catch (IOException e) {
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
            }
        }
    }

    void ShowFeedBack(ObservableList<Table> details) throws FileNotFoundException {
       
        try {
                   
        File f = new File("feedback.txt");
        Scanner sc;
        String str = null;
        
            sc = new Scanner(f);           
            while (sc.hasNextLine()) {
                str = sc.nextLine(); 
                String[] token;
                token=str.split(",");               
                details.add(new Table(token[0], str));
            }
       
        }catch (FileNotFoundException ex) {
            Logger.getLogger(ApplicantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
