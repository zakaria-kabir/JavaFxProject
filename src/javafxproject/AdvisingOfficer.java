/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author zakar
 */
public class AdvisingOfficer extends User{
 

    public void ShowLC(ObservableList<Table> details) {
        try {
        File f = new File("seller.txt");
        Scanner sc;
        String str = null;
        
            sc = new Scanner(f);           
            while (sc.hasNextLine()) {
                str = sc.nextLine(); 
                String[]t;
                t=str.split(",");
                
                details.add(new Table(t[2], str));
            }
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(ApplicantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}