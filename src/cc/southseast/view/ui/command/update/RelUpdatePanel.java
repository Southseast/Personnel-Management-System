package cc.southseast.view.ui.command.update;

import cc.southseast.controller.base.ToClose;
import cc.southseast.controller.function.update.ToUpdateRel;
import cc.southseast.model.dao.Department;
import cc.southseast.model.dao.Employee;
import cc.southseast.model.dao.Post;
import cc.southseast.view.ui.base.BasePanel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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

import static cc.southseast.controller.base.ToGetData.*;
import static cc.southseast.view.ui.base.StaticData.*;


/**
 * @Author: Southseast
 * @Date: 2019/1/3 7:09 PM
 * @Version 1.0
 * 职工部门关系添加界面
 */
public class RelUpdatePanel extends GridPane {

    private Label empNoLable = new Label(EMP_NO_NAME);
    private Label empNameLable = new Label(EMP_NAME_NAME);
    private Label depNameLabel = new Label(DEP_NAME_NAME);
    private Label depPostLabel = new Label(EMP_POST_NAME);
    private Label empLevelLabel = new Label(EMP_LEVEL_NAME);

    private Label empNoInput = new Label();
    private Label empNameInput = new Label();
    private JFXComboBox<Department> depNameInput = new JFXComboBox<Department>();
    private JFXComboBox<Post> depPostInput = new JFXComboBox<Post>();
    private JFXTextField empLevelInput = new JFXTextField();

    private JFXButton saveButton = new JFXButton(SAVE_BUTTON_NAME);
    private JFXButton quitButton = new JFXButton(QUIT_BUTTON_NAME);

    private HBox button = new HBox();

    private Stage editStage = new Stage();

    public RelUpdatePanel(Employee employee, TableView tableView) {

        this.getStylesheets().add(EDIT_PANEL_CSS_ROUTE);
        this.setId(EDIT_PANEL_CSS_ID);
        this.setPadding(new Insets(0, 20, 20, 20));
        this.setHgap(10);
        this.setVgap(20);

        empNoInput.setText(String.valueOf(employee.getEmpNo()));

        empNameInput.setText(employee.getEmpName());

        DEP_CACHE_DATA = getDepCacheData();
        depNameInput.setItems(DEP_CACHE_DATA);
        depNameInput.setMinWidth(150);

        POST_CACHE_DATA = getPostCacheData();
        depPostInput.setItems(POST_CACHE_DATA);
        depPostInput.setMinWidth(150);

        saveButton.setId(SAVE_BUTTON_CSS_ID);
        saveButton.setOnAction(new ToUpdateRel(tableView, empNoInput, depNameInput,
                depPostInput, empLevelInput, editStage));
        saveButton.setMinWidth(45);

        quitButton.setId(QUIT_BUTTON_CSS_ID);
        quitButton.setOnAction(new ToClose(editStage));

        button.setSpacing(20);
        button.setAlignment(Pos.BOTTOM_RIGHT);
        button.getChildren().addAll(saveButton, quitButton);

        this.add(empNoLable, 0, 0);
        this.add(empNoInput, 1, 0);
        this.add(empNameLable, 0, 1);
        this.add(empNameInput, 1, 1);
        this.add(depNameLabel, 0, 2);
        this.add(depNameInput, 1, 2);
        this.add(depPostLabel, 0, 3);
        this.add(depPostInput, 1, 3);
        this.add(empLevelLabel, 0, 4);
        this.add(empLevelInput, 1, 4);
        this.add(button, 1, 5);

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