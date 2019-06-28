package cc.southseast.controller.function.update;

import cc.southseast.model.dao.Department;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.DEP_CACHE_DATA;
import static cc.southseast.controller.base.ToGetData.getDepCacheData;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 11:34 PM
 * @Version 1.0
 * 部门更新
 */
public class ToUpdateDep implements EventHandler<ActionEvent> {

    private TableView tableView;
    private Label depNoInput;
    private JFXTextField depNameInput;
    private JFXTextField depAddressInput;
    private JFXTextField depPhoneInput;
    private Stage editStage;

    public ToUpdateDep(TableView tableView, Label depNoInput, JFXTextField depNameInput,
                       JFXTextField depAddressInput, JFXTextField depPhoneInput, Stage editStage) {
        this.tableView = tableView;
        this.depNoInput = depNoInput;
        this.depNameInput = depNameInput;
        this.depAddressInput = depAddressInput;
        this.depPhoneInput = depPhoneInput;
        this.editStage = editStage;
    }

    @Override
    public void handle(ActionEvent event) {

        Department department = dao.fetch(Department.class, Long.parseLong(depNoInput.getText()));
        department.setDepName(depNameInput.getText().trim());
        department.setDepAddress(depAddressInput.getText().trim());
        department.setDepPhone(depPhoneInput.getText().trim());
        dao.update(department);

        DEP_CACHE_DATA = getDepCacheData();

        tableView.getItems().clear();
        tableView.setItems(DEP_CACHE_DATA);
        tableView.refresh();
        editStage.close();

    }
}
