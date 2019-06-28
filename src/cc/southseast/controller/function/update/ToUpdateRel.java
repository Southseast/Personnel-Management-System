package cc.southseast.controller.function.update;

import cc.southseast.model.dao.Department;
import cc.southseast.model.dao.Employee;
import cc.southseast.model.dao.Post;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.getRelCacheData;
import static cc.southseast.controller.base.ToGetData.REL_CACHE_DATA;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 11:34 PM
 * @Version 1.0
 * 职工部门关系更新
 */
public class ToUpdateRel implements EventHandler<ActionEvent> {

    private TableView tableView;
    private Label empNoInput;
    private JFXComboBox<Department> depNameInput;
    private JFXComboBox<Post> depPostInput;
    private JFXTextField empLevelInput;
    private Stage editStage;


    public ToUpdateRel(TableView tableView, Label empNoInput, JFXComboBox<Department> depNameInput,
                       JFXComboBox<Post> depPostInput, JFXTextField empLevelInput, Stage editStage) {
        this.tableView = tableView;
        this.empNoInput = empNoInput;
        this.depNameInput = depNameInput;
        this.depPostInput = depPostInput;
        this.empLevelInput = empLevelInput;
        this.editStage = editStage;
    }

    @Override
    public void handle(ActionEvent event) {

        Employee employee = dao.fetch(Employee.class, Long.parseLong(empNoInput.getText()));
        if (depNameInput.getValue() != null) {

            employee.setDepNo(depNameInput.getValue().getDepNo());
        }

        if (depPostInput.getValue() != null){

            employee.setEmpPost(String.valueOf(depPostInput.getValue()));
        }

        if (!empLevelInput.getText().trim().equals("")){

            employee.setEmpLevel(Long.parseLong(empLevelInput.getText().trim()));
        }
        dao.update(employee);

        REL_CACHE_DATA = getRelCacheData();

        tableView.getItems().clear();
        tableView.setItems(REL_CACHE_DATA);
        tableView.refresh();
        editStage.close();

    }
}
