package cc.southseast.view.ui.reset;

import cc.southseast.controller.change.quit.ResetToQuit;
import cc.southseast.controller.function.reset.ToResetPassword;
import cc.southseast.controller.function.reset.ToSendEmail;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/9 9:05 PM
 * @Version 1.0
 */
public class ResetPasswordMain extends GridPane {

    // 账号输入
    private JFXTextField idInput = new JFXTextField();

    // 验证码输入
    private JFXTextField codeInput = new JFXTextField();

    // 密码输入
    private JFXPasswordField passwordInput = new JFXPasswordField();

    private JFXButton sendEmailButton = new JFXButton(SEND_EMAIL_BUTTON_NAME);

    private JFXButton resetPasswordButton = new JFXButton(RESET_PASSWORD_BUTTON_NAME);

    private JFXButton quitButton = new JFXButton(QUIT_BUTTON_NAME);

    public ResetPasswordMain() {

        this.getStylesheets().add(RESET_PASSWORD_MAIN_CSS_ROUTE);

        idInput.setPromptText(ID_INPUT_PROMPT_TEXT);
        idInput.setMaxWidth(150);

        codeInput.setPromptText(DODE_INPUT_PROMPT_TEXT);
        codeInput.setMaxWidth(150);

        passwordInput.setPromptText(PASSWORD_INPUT_PROMPT_TEXT);
        passwordInput.setMaxWidth(150);

        sendEmailButton.setId(SEND_EMAIL_BUTTON_CSS_ID);
        sendEmailButton.setMinWidth(150);
        sendEmailButton.setOnAction(new ToSendEmail(idInput));

        resetPasswordButton.setId(RESET_PASSWORD_BUTTON_CSS_ID);
        resetPasswordButton.setMinWidth(150);
        resetPasswordButton.setOnAction(new ToResetPassword(codeInput, passwordInput));

        quitButton.setId(QUIT_BUTTON_CSS_ID);
        quitButton.setMinWidth(150);
        quitButton.setOnAction(new ResetToQuit(idInput));


        this.add(idInput, 0, 0, 1, 2);
        this.add(codeInput, 0, 2, 1, 2);
        this.add(passwordInput, 0, 4, 1, 2);
        this.add(sendEmailButton, 0, 7, 1, 2);
        this.add(resetPasswordButton, 0, 9, 1, 2);
        this.add(quitButton, 0, 11, 1, 2);
        this.setVgap(10);
        this.setPadding(new Insets(45, 0, 0, 300));
    }
}
