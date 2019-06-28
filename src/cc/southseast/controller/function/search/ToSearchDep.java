package cc.southseast.controller.function.search;

import cc.southseast.model.dao.Department;
import cc.southseast.view.ui.manage.admin.tableview.DepTableView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import org.nutz.dao.Cnd;

import java.util.List;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.DEP_CACHE_DATA;
import static cc.southseast.controller.base.ToGetData.SEARCH_CONTENT;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 4:33 PM
 * @Version 1.0
 * 部门搜索
 */
public class ToSearchDep implements EventHandler<ActionEvent> {

    private JFXTextField searchInput;
    private DepTableView depTableView;
    private TableView tableView = new TableView();

    public ToSearchDep(JFXTextField searchInput, DepTableView depTableView) {
        this.searchInput = searchInput;
        this.depTableView = depTableView;
    }

    @Override
    public void handle(ActionEvent event) {

        SEARCH_CONTENT = searchInput.getText();
        tableView = (TableView) depTableView.getChildren().get(0);
        List<Department> depList = dao.query(Department.class, Cnd.where("depNo", "like", "%" + SEARCH_CONTENT + "%"));
        DEP_CACHE_DATA = FXCollections.observableArrayList(depList);
        tableView.getItems().clear();
        tableView.setItems(DEP_CACHE_DATA);
        tableView.refresh();


    }
}
