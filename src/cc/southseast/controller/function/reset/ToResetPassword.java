package cc.southseast.controller.function.reset;

import cc.southseast.controller.verification.sm3.SM3Digest;
import cc.southseast.view.ui.hint.HintPanel;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.CODE;
import static cc.southseast.controller.base.ToGetData.USER;
import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019-01-09 23:51
 * @Version 1.0
 */
public class ToResetPassword implements EventHandler<ActionEvent> {

    private JFXTextField codeInput;
    private JFXPasswordField passwordInput;

    public ToResetPassword(JFXTextField codeInput, JFXPasswordField passwordInput) {
        this.codeInput = codeInput;
        this.passwordInput = passwordInput;
    }

    @Override
    public void handle(ActionEvent event) {

        if (!passwordInput.getText().trim().equals(null)
                && !passwordInput.getText().trim().equals("")) {

            if (codeInput.getText().trim().equals(CODE)) {
                USER.setEmpPassword(SM3Digest.encode(passwordInput.getText()));
                dao.update(USER);
                CODE = "";
                codeInput.setText("");
                passwordInput.setText("");

                // 提示
                new HintPanel(codeInput, RESET_PASSWORD_HINT);
            } else {

                // 错误提示
                new HintPanel(codeInput, CODE_ERROR_HINT);
            }
        } else {

            // 错误提示
            new HintPanel(codeInput, PASSWORD_ERROR_HINT);
        }

    }
}
