package View;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 * Created by Luxiam on 8/28/2017.
 */

@ManagedBean
@Named
@RequestScoped
public class MainPageView {


    UserController controller;

    public MainPageView(){
        controller = new UserController();
    }

    public void searchButtonAction(ActionEvent actionEvent) {
        System.err.print("No user found");
    }
}
