package cc.southseast.view.ui.command.update;

import cc.southseast.controller.base.ToClose;
import cc.southseast.controller.function.update.ToUpdateDep;
import cc.southseast.model.dao.Department;
import cc.southseast.view.ui.base.BasePanel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 7:09 PM
 * @Version 1.0
 * 部门更新界面
 */
public class DepUpdatePanel extends GridPane {

    private Label depNoLabel = new Label(DEP_NO_NAME);
    private Label depNameLabel = new Label(DEP_NAME_NAME);
    private Label depAddressLabel = new Label(DEP_ADDRESS_NAME);
    private Label depPhoneLabel = new Label(DEP_POHONE_NAME);

    private Label depNoInput = new Label();
    private JFXTextField depNameInput = new JFXTextField();
    private JFXTextField depAddressInput = new JFXTextField();
    private JFXTextField depPhoneInput = new JFXTextField();

    private JFXButton saveButton = new JFXButton(SAVE_BUTTON_NAME);
    private JFXButton quitButton = new JFXButton(QUIT_BUTTON_NAME);

    private HBox button = new HBox();

    private Stage editStage = new Stage();

    public DepUpdatePanel(Department department, TableView tableView) {

        this.getStylesheets().add(EDIT_PANEL_CSS_ROUTE);
        this.setId(EDIT_PANEL_CSS_ID);
        this.setPadding(new Insets(0, 20, 20, 20));
        this.setHgap(10);
        this.setVgap(20);

        depNoLabel.setMinWidth(60);
        depNoInput.setText(String.valueOf(department.getDepNo()));
        depNameInput.setText(department.getDepName());
        depAddressInput.setText(department.getDepAddress());
        depPhoneInput.setText(department.getDepPhone());

        saveButton.setId(SAVE_BUTTON_CSS_ID);
        saveButton.setOnAction(new ToUpdateDep(tableView, depNoInput, depNameInput,
                depAddressInput, depPhoneInput, editStage));
        saveButton.setMinWidth(45);

        quitButton.setId(QUIT_BUTTON_CSS_ID);
        quitButton.setOnAction(new ToClose(editStage));

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

        BasePanel root = new BasePanel();

        Scene scene = new Scene(root);

        Stage stage = (Stage) tableView.getScene().getWindow();

        // 设置透明度
        scene.setFill(Color.TRANSPARENT);

        root.windowsInit(editStage);
        root.getSubject().getChildren().addAll(this);

        editStage.initModality(Modality.WINDOW_MODAL);
        editStage.initOwner(stage);
        editStage.setScene(scene);
        editStage.show();

    }

}