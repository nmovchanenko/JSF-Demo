/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import java.io.Serializable;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author n.movchanenko
 */
@ManagedBean(name = "UserNumberBean")
@SessionScoped
public class UserNumberBean implements Serializable{

    Integer randomInteger;
    Integer userNumber;
    String response;
    
    /**
     * Creates a new instance of UserNumberBean
     */
    public UserNumberBean() {
        Random randomNumber = new Random();
        randomInteger = new Integer(randomNumber.nextInt(10));
        System.out.println("Duke's number is: " + randomInteger);
    }

    public String getResponse() {
        if(userNumber != null && userNumber.compareTo(randomInteger) == 0){
            // define user's session as invalid to encourage him to play again
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            session.invalidate();
            
            return "Yay! You got it!";
        } else {
            // if users didn't guess the number, the randomInteger will remain it's value
            return "<p>Sorry, " + userNumber + " isn't it.</p>"
                + "<p>Guess again...</p>";
        }
    }
    
    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }
}
