package cc.southseast.view.ui.command.insert;

import cc.southseast.controller.base.ToClose;
import cc.southseast.controller.function.insert.ToInsertEmp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static cc.southseast.controller.base.ToGetData.SEX_SORT;
import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 10:21 PM
 * @Version 1.0
 * 职工添加界面
 */
public class EmpInsertPanel extends GridPane {

    private Label empNoLabel = new Label(EMP_NO_NAME);
    private Label empNameLabel = new Label(EMP_NAME_NAME);
    private Label empPasswordLabel = new Label(EMP_PASSWORD_NAME);
    private Label empSexLabel = new Label(EMP_SEX_NAME);
    private Label empBirthdayLabel = new Label(EMP_BIRTHDAY_NAME);
    private Label entryTimeLabel = new Label(ENTRY_TIME_NAME);
    private Label empTelphoneLabel = new Label(EMP_TELPHONE_NAME);
    private Label empEmailLabel = new Label(EMP_EMAIL_NAME);

    private JFXTextField empNoInput = new JFXTextField();
    private JFXTextField empNameInput = new JFXTextField();
    private JFXTextField empPasswordInput = new JFXTextField();
    private JFXComboBox empSexInput = new JFXComboBox();
    private DatePicker empBirthdayInput = new DatePicker();
    private DatePicker entryTimeInput = new DatePicker();
    private JFXTextField empTelphoneInput = new JFXTextField();
    private JFXTextField empEmailInput = new JFXTextField();

    private JFXButton saveButton = new JFXButton(SAVE_BUTTON_NAME);
    private JFXButton quitButton = new JFXButton(QUIT_BUTTON_NAME);

    private HBox button = new HBox();

    public EmpInsertPanel(TableView tableView, Stage addStage) {

        // 引入样式文件
        this.getStylesheets().add(EDIT_PANEL_CSS_ROUTE);
        this.setId(EDIT_PANEL_CSS_ID);
        this.setPadding(new Insets(0, 20, 20, 20));
        this.setHgap(20);
        this.setVgap(20);

        empNoLabel.setMinWidth(60);
        empBirthdayLabel.setMinWidth(60);

        empSexInput.getItems().addAll(SEX_SORT);
        empSexInput.setMinWidth(150);

        saveButton.setId(SAVE_BUTTON_CSS_ID);
        saveButton.setOnAction(new ToInsertEmp(tableView, empNoInput, empNameInput,
                empPasswordInput, empSexInput, empBirthdayInput, entryTimeInput,
                empTelphoneInput, empEmailInput, addStage));
        saveButton.setMinWidth(45);

        quitButton.setId(QUIT_BUTTON_CSS_ID);
        quitButton.setOnAction(new ToClose(addStage));

        button.setSpacing(20);
        button.setAlignment(Pos.BOTTOM_RIGHT);
        button.getChildren().addAll(saveButton, quitButton);

        this.add(empNoLabel, 0, 0);
        this.add(empNoInput, 1, 0);
        this.add(empNameLabel, 0, 1);
        this.add(empNameInput, 1, 1);
        this.add(empPasswordLabel, 0, 2);
        this.add(empPasswordInput, 1, 2);
        this.add(empSexLabel, 0, 3);
        this.add(empSexInput, 1, 3);

        this.add(empBirthdayLabel, 2, 0);
        this.add(empBirthdayInput, 3, 0);
        this.add(entryTimeLabel, 2, 1);
        this.add(entryTimeInput, 3, 1);
        this.add(empTelphoneLabel, 2, 2);
        this.add(empTelphoneInput, 3, 2);
        this.add(empEmailLabel, 2, 3);
        this.add(empEmailInput, 3, 3);

        this.add(button, 3, 4);

    }
}
