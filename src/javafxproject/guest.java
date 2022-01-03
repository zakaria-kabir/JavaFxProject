/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

/**
 *
 * @author zakar
 */
public class guest {
    public String showexportLC(){
        return readFromTextFile("ExportLC.txt");
        
    }
    public String showeimportLC() {
        return readFromTextFile("importLC.txt");
    }
    public String showBacktoBackLC() {
       return readFromTextFile("BacktoBackLC.txt");
    }
    public String showCoC() {
        return readFromTextFile("CoC.txt");
    }
    public String contactUs() {
         return readFromTextFile("ContactUs.txt");
    }
    public String servicecrg() {
         return readFromTextFile("ServiceCharge.txt");
    }
    
    private String readFromTextFile(String str) {
       
        File file = new File(str);
        Scanner sc;
        String s = "";
        try {
            sc = new Scanner(file);
            
            while(sc.hasNextLine()){
                s+=sc.nextLine()+"\n";
                    
            }
           
        } catch (FileNotFoundException ex) {
            
        }   
        return s;
    }
}
