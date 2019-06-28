package cc.southseast.controller.change.reset;

import cc.southseast.view.ui.reset.ResetPasswordMain;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

/**
 * @Author: Southseast
 * @Date: 2019/1/9 9:03 PM
 * @Version 1.0
 */
public class ToResetPanel implements EventHandler<ActionEvent> {

    private JFXTextField idInput;

    private HBox subject = new HBox();

    public ToResetPanel(JFXTextField idInput) {
        this.idInput = idInput;
    }

    @Override
    public void handle(ActionEvent event) {

        ResetPasswordMain resetPasswordMain = new ResetPasswordMain();

        subject = (HBox) idInput.getParent().getParent();
        subject.getChildren().remove(1);
        subject.getChildren().add(1, resetPasswordMain);
    }
}
