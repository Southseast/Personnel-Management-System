package cc.southseast.controller.function.search;

import cc.southseast.model.dao.Employee;
import cc.southseast.view.ui.manage.admin.tableview.RelTableView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import org.nutz.dao.Cnd;

import java.util.List;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.REL_CACHE_DATA;
import static cc.southseast.controller.base.ToGetData.SEARCH_CONTENT;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 4:33 PM
 * @Version 1.0
 * 职工部门关系搜索
 */
public class ToSearchRel implements EventHandler<ActionEvent> {

    private JFXTextField searchInput;
    private RelTableView relTableView;
    private TableView tableView = new TableView();

    public ToSearchRel(JFXTextField searchInput, RelTableView relTableView) {
        this.searchInput = searchInput;
        this.relTableView = relTableView;
    }

    @Override
    public void handle(ActionEvent event) {

        SEARCH_CONTENT = searchInput.getText();
        tableView = (TableView) relTableView.getChildren().get(0);
        List<Employee> empList = dao.queryByJoin(Employee.class, null, Cnd.where("empNo", "like", "%" + SEARCH_CONTENT + "%"));
        REL_CACHE_DATA = FXCollections.observableArrayList(empList);
        tableView.getItems().clear();
        tableView.setItems(REL_CACHE_DATA);
        tableView.refresh();
    }
}
