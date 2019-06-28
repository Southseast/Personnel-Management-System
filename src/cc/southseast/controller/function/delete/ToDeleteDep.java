package cc.southseast.controller.function.delete;

import cc.southseast.model.dao.Department;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import org.nutz.dao.Cnd;

import java.util.Iterator;
import java.util.List;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 7:09 PM
 * @Version 1.0
 * 删除部门信息
 */
public class ToDeleteDep {

    public ToDeleteDep(long depNo, TableView tableView) {

        Iterator<Department> iterator = tableView.getItems().iterator();

        while (iterator.hasNext()) {
            Department department = iterator.next();
            if (department.getDepNo() == depNo) {
                iterator.remove();
                dao.delete(Department.class, department.getDepNo());
            }
        }

        if (SEARCH_CONTENT.equals(null) || SEARCH_CONTENT.equals(""))
            DEP_CACHE_DATA = getDepCacheData();
        else {

            List<Department> depList = dao.query(Department.class, Cnd.where("depNo", "like", "%" + SEARCH_CONTENT + "%"));
            DEP_CACHE_DATA = FXCollections.observableArrayList(depList);
        }

        tableView.getItems().clear();
        tableView.setItems(DEP_CACHE_DATA);
        tableView.refresh();
    }

}