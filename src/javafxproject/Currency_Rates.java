/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxproject;

/**
 *
 * @author zakar
 */
public class Currency_Rates{
    private String curr;
    private double buying,selling;
    public String getCurr() {
        return curr;
    }
    public double getBuying() {
        return buying;
    }

    public double getSelling() {
        return selling;
    }

    public Currency_Rates(String cur, double buying, double selling) {
        this.curr = cur;
        this.buying = buying;
        this.selling = selling;
    }

    
}
