package cc.southseast.view.ui.login;

import cc.southseast.controller.change.login.ToLogin;
import cc.southseast.controller.change.reset.ToResetPanel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 8:33 AM
 * @Version 1.0
 * 登陆界面
 */
public class LoginMain extends GridPane {

    // 账号输入
    private JFXTextField idInput = new JFXTextField();
    // 密码输入
    private JFXPasswordField passwordInput = new JFXPasswordField();
    // 登录按钮
    private JFXButton loginButton = new JFXButton(LOGIN_BUTTON_NAME);
    // 忘记密码
    private Button forgetPasswordButton = new Button(FORGET_PASSWORD_BUTTON_NAME);
    // 选择面板
    private HBox loginChoice = new HBox();

    public LoginMain() {

        // 引入样式文件
        this.getStylesheets().add(LOGIN_PANEL_CSS_ROUTE);

        // 设置登录组件间隔
        this.setHgap(10);
        this.setVgap(10);

        // 设置账号输入框的CSSID样式
        idInput.setId(ID_INPUT_CSS_ID);
        idInput.setPromptText(ID_INPUT_PROMPT_TEXT);
        idInput.setMaxWidth(150);
        idInput.setText(ID_INPUT_TEXT);

        // 设置密码输入框的CSSID样式
        passwordInput.setId(PASSWORD_INPUT_CSS_ID);
        passwordInput.setPromptText(PASSWORD_INPUT_PROMPT_TEXT);
        passwordInput.setMaxWidth(150);
        passwordInput.setText(PASSWORD_INPUT_TEXT);

        // 设置登录按钮的CSSID样式
        loginButton.setId(LOGIN_BUTTON_CSS_ID);
        // 设置登录按钮的大小
        loginButton.setMinWidth(150);
        // 设置登录按钮的触发功能
        loginButton.setOnAction(new ToLogin(idInput, passwordInput));

        forgetPasswordButton.setId(FORGET_PASSWORD_BUTTON_CSS_ID);
        forgetPasswordButton.setOnAction(new ToResetPanel(idInput));

        loginChoice.getChildren().addAll(forgetPasswordButton);
        loginChoice.setAlignment(Pos.CENTER);

        // 添加组件位置，添加为子节点
        this.add(idInput, 0, 0, 1, 2);
        this.add(passwordInput, 0, 2, 1, 2);
        this.add(loginButton, 0, 5, 1, 2);
        this.add(loginChoice, 0, 7);
        this.setVgap(10);
        this.setPadding(new Insets(100, 0, 0, 300));
    }
}
