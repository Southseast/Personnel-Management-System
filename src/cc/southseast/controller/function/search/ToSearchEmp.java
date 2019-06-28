package cc.southseast.controller.function.search;

import cc.southseast.model.dao.Employee;
import cc.southseast.view.ui.manage.admin.tableview.EmpTableView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import org.nutz.dao.Cnd;

import java.util.List;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.EMP_CACHE_DATA;
import static cc.southseast.controller.base.ToGetData.SEARCH_CONTENT;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 4:33 PM
 * @Version 1.0
 * 职工搜索
 */
public class ToSearchEmp implements EventHandler<ActionEvent> {

    private JFXTextField searchInput;
    private EmpTableView empTableView;
    private TableView tableView = new TableView();

    public ToSearchEmp(JFXTextField searchInput, EmpTableView empTableView) {
        this.searchInput = searchInput;
        this.empTableView = empTableView;
    }

    @Override
    public void handle(ActionEvent event) {

        SEARCH_CONTENT = searchInput.getText();
        tableView = (TableView) empTableView.getChildren().get(0);
        List<Employee> empList = dao.query(Employee.class, Cnd.where("empNo", "like", "%" + SEARCH_CONTENT + "%"));
        EMP_CACHE_DATA = FXCollections.observableArrayList(empList);
        tableView.getItems().clear();
        tableView.setItems(EMP_CACHE_DATA);
        tableView.refresh();
    }
}
