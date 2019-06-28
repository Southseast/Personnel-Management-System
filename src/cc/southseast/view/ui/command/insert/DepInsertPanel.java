package cc.southseast.view.ui.command.insert;

import cc.southseast.controller.base.ToClose;
import cc.southseast.controller.function.insert.ToInsertDep;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 10:21 PM
 * @Version 1.0
 * 部门添加界面
 */
public class DepInsertPanel extends GridPane {

    private Label depNoLabel = new Label(DEP_NO_NAME);
    private Label depNameLabel = new Label(DEP_NAME_NAME);
    private Label depAddressLabel = new Label(DEP_ADDRESS_NAME);
    private Label depPhoneLabel = new Label(DEP_POHONE_NAME);

    private JFXTextField depNoInput = new JFXTextField();
    private JFXTextField depNameInput = new JFXTextField();
    private JFXTextField depAddressInput = new JFXTextField();
    private JFXTextField depPhoneInput = new JFXTextField();

    private JFXButton saveButton = new JFXButton(SAVE_BUTTON_NAME);
    private JFXButton quitButton = new JFXButton(QUIT_BUTTON_NAME);

    private HBox button = new HBox();

    public DepInsertPanel(TableView tableView, Stage addStage) {

        // 引入样式文件
        this.getStylesheets().add(EDIT_PANEL_CSS_ROUTE);
        this.setId(EDIT_PANEL_CSS_ID);
        this.setPadding(new Insets(0, 20, 20, 20));
        this.setHgap(10);
        this.setVgap(20);

        depNoLabel.setMinWidth(60);

        saveButton.setId(SAVE_BUTTON_CSS_ID);
        saveButton.setOnAction(new ToInsertDep(tableView, depNoInput, depNameInput,
                depAddressInput, depPhoneInput, addStage));
        saveButton.setAlignment(Pos.BOTTOM_RIGHT);
        saveButton.setMinWidth(45);

        quitButton.setId(QUIT_BUTTON_CSS_ID);
        quitButton.setOnAction(new ToClose(addStage));

        button.setSpacing(20);
        button.setAlignment(Pos.BOTTOM_RIGHT);
        button.getChildren().addAll(saveButton, quitButton);

        this.add(depNoLabel, 0, 0);
        this.add(depNoInput, 1, 0);
        this.add(depNameLabel, 0, 1);
        this.add(depNameInput, 1, 1);
        this.add(depAddressLabel, 0, 2);
        this.add(depAddressInput, 1, 2);
        this.add(depPhoneLabel, 0, 3);
        this.add(depPhoneInput, 1, 3);
        this.add(button, 1, 4);

    }
}
