/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

import javafx.scene.control.CheckBox;

/**
 *
 * @author zakar
 */
public class Table{
 private String userid,userlc;
private CheckBox select;
    public Table() {
    }
    public Table( String userid, String userlc) {
        this.userid = (userid);
        this.userlc =(userlc);
        this.select=new CheckBox();
    }

    public Table(CheckBox select) {
        this.select = select;
    }

    public CheckBox getSelect() {
        return select;
    }
    
    public void setUserid(String userid) {
        this.userid =(userid) ;
    }

    public void setUserlc(String userlc) {
        this.userlc = (userlc);
    }

    public String getUserid() {
        return userid;
    }

    public String getUserlc() {
        return userlc;
    }
   
  
}