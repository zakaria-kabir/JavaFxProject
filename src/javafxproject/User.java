/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author zakar
 */
public class User implements Serializable{
    private String firstName,lastName,Address,Fname,Mname,Gender,userID,password;
    private LocalDate Dob;
    private long ContactNo,Nid;
    public User(){};
    
    User(String userID, String firstName, String lastName, String Address, String Fname, String Mname, LocalDate Dob, String Gender, long ContactNo, long Nid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.Address = Address;
        this.Fname = Fname;
        this.Mname = Mname;
        this.Gender = Gender;
        this.userID = userID;
        this.Dob = Dob;
        this.ContactNo = ContactNo;
        this.Nid = Nid;
    }

    public String getid(){
        return userID;
    }
    public String getpass(){
        return password;
    }
    public String getfname(){
        return firstName;
    }
    public String getlname(){
        return lastName;
    }
    public String getaddress(){
        return Address;
    }
    public String getFname(){
        return Fname;
    }
    public String getMname(){
        return Mname;
    }  
    public String getGender(){
        return Gender;
    }      
    public String getDate() {
        return Dob.toString();
    }

    public String getNid() {
        return String.valueOf(Nid);
    }

    String getContact() {
        return String.valueOf(ContactNo);
    }
    
    @Override
    public String toString(){
        return "UserID: "+userID+" , Name: "+firstName+" "+lastName+" , Father's name: "+Fname+
                " , Mother's name: "+Mname+" , Date of Birth: "+" , Gender: "+Gender+" , Contact number: "+ContactNo+" , NID: "+Nid+" , Address: "+Address+"\n";
    }


    void setInfoChange(String text, String text0, String text1, String text2, long parseLong) {
        String[] token = null;        token=text.split(" ");
        firstName=token[0];
        lastName=token[1];
        Fname=text0;
        Mname=text1;
       Address=text2;
        ContactNo=parseLong;
    }


}
