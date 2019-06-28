package cc.southseast.controller.function.batchdelete;

import cc.southseast.model.dao.Employee;
import cc.southseast.view.ui.manage.admin.tableview.RelTableView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

import java.util.Iterator;

import static cc.southseast.controller.base.ToConnect.dao;

/**
 * @Author: Southseast
 * @Date: 2019/1/4 7:09 PM
 * @Version 1.0
 * 批量删除职工信息
 */
public class ToBatchDeleteRel implements EventHandler<ActionEvent> {

    private TableView tableView = new TableView();

    private RelTableView relTableView;

    public ToBatchDeleteRel(RelTableView relTableView) {
        this.relTableView = relTableView;
    }

    @Override
    public void handle(ActionEvent event) {

        tableView = (TableView) relTableView.getChildren().get(0);
        Iterator<Employee> iterator = tableView.getItems().iterator();

        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getCheck()) {
                iterator.remove();
                dao.delete(Employee.class, employee.getDepNo());
            }
        }
        tableView.refresh();
    }
}