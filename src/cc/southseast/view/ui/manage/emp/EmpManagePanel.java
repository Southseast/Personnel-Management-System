package cc.southseast.view.ui.manage.emp;

import cc.southseast.controller.change.quit.EmpToQuit;
import cc.southseast.controller.function.update.EmpToUpdate;
import cc.southseast.view.ui.clock.EmpClockPanel;
import cc.southseast.view.ui.title.TitlePanel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Date;

import static cc.southseast.controller.base.ToGetData.SEX_SORT;
import static cc.southseast.controller.base.ToGetData.USER;
import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/6 6:06 PM
 * @Version 1.0
 * 用户界面
 */
public class EmpManagePanel extends VBox {

    // 标题
    private TitlePanel titlePanel = new TitlePanel(EMP_LITTLE_TITLE);

    // 登录界面背景
    private HBox backgroundImage = new HBox();
    private HBox background = new HBox();

    // 不可更改
    private Label empNoLabel = new Label(EMP_NO_NAME);
    private Label empNameLabel = new Label(EMP_NAME_NAME);
    private Label entryTimeLabel = new Label(ENTRY_TIME_NAME);
    private Label empPostLabel = new Label(EMP_POST_NAME);
    private Label empLevelLabel = new Label(EMP_WAGES_NAME);
    private Label depNoLabel = new Label(DEP_NO_NAME);
    private Label depNameLabel = new Label(DEP_NAME_NAME);
    private Label depAddressLabel = new Label(DEP_ADDRESS_NAME);
    private Label depPhoneLabel = new Label(DEP_POHONE_NAME);

    // 可更改
    private Label empSexLabel = new Label(EMP_SEX_NAME);
    private Label empBirthdayLabel = new Label(EMP_BIRTHDAY_NAME);
    private Label empTelphoneLabel = new Label(EMP_TELPHONE_NAME);
    private Label empEmailLabel = new Label(EMP_EMAIL_NAME);
    private Label empPasswordLabel = new Label(EMP_PASSWORD_NAME);

    // 不可更改
    private Label empNoInput = new Label();
    private Label empNameInput = new Label();
    private Label entryTimeInput = new Label();
    private Label empPostInput = new Label();
    private Label empLevelInput = new Label();
    private Label depNoInput = new Label();
    private Label depNameInput = new Label();
    private Label depAddressInput = new Label();
    private Label depPhoneInput = new Label();

    // 可更改
    private JFXComboBox empSexInput = new JFXComboBox();
    private DatePicker empBirthdayInput = new DatePicker();
    private JFXTextField empTelphoneInput = new JFXTextField();
    private JFXTextField empEmailInput = new JFXTextField();
    private JFXTextField empPasswordInput = new JFXTextField();

    private JFXButton saveButton = new JFXButton(SAVE_BUTTON_NAME);
    private JFXButton quitButton = new JFXButton(QUIT_BUTTON_NAME);
    private HBox button = new HBox();
    private GridPane empUpdateMain = new GridPane();
    private HBox subject = new HBox();

    private EmpClockPanel empClockPanel = new EmpClockPanel();

    public EmpManagePanel() {

        this.getStylesheets().add(EDIT_PANEL_CSS_ROUTE);
        this.setId(EDIT_PANEL_CSS_ID);
        // 设置窗口大小
        this.setMinSize(1024, 768);
        this.setMaxSize(1024, 768);
        // 设置间隔
        this.setPadding(new Insets(50, 50, 0, 50));
        this.setSpacing(50);

        // 设置背景图片
        backgroundImage.setMinSize(300, 330);
        backgroundImage.setId(BACKGROUND_IMAGE_CSS_ID);

        background.getChildren().addAll(backgroundImage);
        background.setPadding(new Insets(60, 0, 0, 0));

        // 更新面板间隔
        empUpdateMain.setHgap(20);
        empUpdateMain.setVgap(20);
        // 长度
        empNoLabel.setMinWidth(60);
        // 设置值
        // 不可更改
        empNoInput.setText(String.valueOf(USER.getEmpNo()));
        empNameInput.setText(USER.getEmpName());
        entryTimeInput.setText(String.valueOf(((new java.sql.Date(new Date().getTime())).getTime()
                - (USER.getEntryTime().getTime())) / 1000 / 60 / 60 / 24 / 365));
        empPostInput.setText(USER.getEmpPost());
        empLevelInput.setText(String.valueOf(4000 + USER.getEmpLevel() * 500));
        depNoInput.setText(String.valueOf(USER.getDepNo()));
        depNameInput.setText(USER.getDepartment().getDepName());
        depAddressInput.setText(USER.getDepartment().getDepAddress());
        depPhoneInput.setText(USER.getDepartment().getDepPhone());
        // 可更改
        empPasswordInput.setText(USER.getEmpPassword());
        empSexInput.setValue(USER.getEmpSex());
        empSexInput.getItems().addAll(SEX_SORT);
        empSexInput.setMinWidth(200);
        empBirthdayInput.setValue(USER.getEmpBirthday().toLocalDate());
        empTelphoneInput.setText(USER.getEmpTelphone());
        empEmailInput.setText(USER.getEmpEmail());

        // 按钮样式
        saveButton.setId(SAVE_BUTTON_CSS_ID);
        saveButton.setOnAction(new EmpToUpdate(empSexInput, empBirthdayInput,
                empTelphoneInput, empEmailInput, empPasswordInput));
        quitButton.setId(QUIT_BUTTON_CSS_ID);
        quitButton.setOnMousePressed(new EmpToQuit(this));
        button.setSpacing(20);
        button.setAlignment(Pos.BOTTOM_RIGHT);
        button.getChildren().addAll(saveButton, quitButton);

        // 位置
        // 不可更改
        empUpdateMain.add(empNoLabel, 0, 0);
        empUpdateMain.add(empNoInput, 1, 0);
        empUpdateMain.add(empNameLabel, 0, 1);
        empUpdateMain.add(empNameInput, 1, 1);
        empUpdateMain.add(entryTimeLabel, 0, 2);
        empUpdateMain.add(entryTimeInput, 1, 2);
        empUpdateMain.add(empPostLabel, 0, 3);
        empUpdateMain.add(empPostInput, 1, 3);
        empUpdateMain.add(empLevelLabel, 0, 4);
        empUpdateMain.add(empLevelInput, 1, 4);
        empUpdateMain.add(depNoLabel, 0, 5);
        empUpdateMain.add(depNoInput, 1, 5);
        empUpdateMain.add(depNameLabel, 0, 6);
        empUpdateMain.add(depNameInput, 1, 6);
        empUpdateMain.add(depAddressLabel, 2, 0);
        empUpdateMain.add(depAddressInput, 3, 0);
        empUpdateMain.add(depPhoneLabel, 2, 1);
        empUpdateMain.add(depPhoneInput, 3, 1);
        // 可更改
        empUpdateMain.add(empSexLabel, 2, 2);
        empUpdateMain.add(empSexInput, 3, 2);
        empUpdateMain.add(empBirthdayLabel, 2, 3);
        empUpdateMain.add(empBirthdayInput, 3, 3);
        empUpdateMain.add(empTelphoneLabel, 2, 4);
        empUpdateMain.add(empTelphoneInput, 3, 4);
        empUpdateMain.add(empEmailLabel, 2, 5);
        empUpdateMain.add(empEmailInput, 3, 5);
        empUpdateMain.add(empPasswordLabel, 2, 6);
        empUpdateMain.add(empPasswordInput, 3, 6);
        empUpdateMain.add(button, 3, 7);
        empUpdateMain.setPadding(new Insets(50, 0, 0, 0));

        subject.getChildren().addAll(background, empUpdateMain);
        subject.setSpacing(180);
        this.getChildren().addAll(titlePanel, empClockPanel, subject);
    }
}
