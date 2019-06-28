package cc.southseast.controller.function.batchdelete;

import cc.southseast.model.dao.Employee;
import cc.southseast.view.ui.manage.admin.tableview.EmpTableView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

import java.util.Iterator;

import static cc.southseast.controller.base.ToConnect.dao;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 7:09 PM
 * @Version 1.0
 * 批量删除职工信息
 */
public class ToBatchDeleteEmp implements EventHandler<ActionEvent> {

    private TableView tableView = new TableView();

    private EmpTableView empTableView;

    public ToBatchDeleteEmp(EmpTableView empTableView) {
        this.empTableView = empTableView;
    }

    @Override
    public void handle(ActionEvent event) {

        tableView = (TableView) empTableView.getChildren().get(0);
        Iterator<Employee> iterator = tableView.getItems().iterator();

        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getCheck()) {
                iterator.remove();
                dao.delete(Employee.class, employee.getEmpNo());
            }
        }
        tableView.refresh();
    }
}