package cc.southseast.controller.change.quit;

import cc.southseast.view.ui.login.LoginMain;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

/**
 * @Author: Southseast
 * @Date: 2019/1/9 9:18 PM
 * @Version 1.0
 */
public class ResetToQuit implements EventHandler<ActionEvent> {

    private JFXTextField idInput;

    private HBox subject = new HBox();

    public ResetToQuit(JFXTextField idInput) {
        this.idInput = idInput;
    }

    @Override
    public void handle(ActionEvent event) {

        LoginMain loginMain = new LoginMain();

        subject = (HBox) idInput.getParent().getParent();
        subject.getChildren().remove(1);
        subject.getChildren().add(1, loginMain);

    }
}
