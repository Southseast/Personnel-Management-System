package cc.southseast.controller.function.batchdelete;

import cc.southseast.model.dao.Department;
import cc.southseast.view.ui.manage.admin.tableview.DepTableView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

import java.util.Iterator;

import static cc.southseast.controller.base.ToConnect.dao;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 7:09 PM
 * @Version 1.0
 * 批量删除部门信息
 */
public class ToBatchDeleteDep implements EventHandler<ActionEvent> {

    private TableView tableView = new TableView();

    private DepTableView depTableView;

    public ToBatchDeleteDep(DepTableView depTableView) {
        this.depTableView = depTableView;
    }

    @Override
    public void handle(ActionEvent event) {

        tableView = (TableView) depTableView.getChildren().get(0);
        Iterator<Department> iterator = tableView.getItems().iterator();

        while (iterator.hasNext()) {
            Department department = iterator.next();
            if (department.getCheck()) {
                iterator.remove();
                dao.delete(Department.class, department.getDepNo());
            }
        }
        tableView.refresh();
    }
}