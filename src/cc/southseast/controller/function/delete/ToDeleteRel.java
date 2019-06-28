package cc.southseast.controller.function.delete;

import cc.southseast.model.dao.Employee;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import org.nutz.dao.Cnd;

import java.util.Iterator;
import java.util.List;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/4 7:09 PM
 * @Version 1.0
 * 删除职工信息
 */
public class ToDeleteRel {

    public ToDeleteRel(long empNo, TableView tableView) {

        Iterator<Employee> iterator = tableView.getItems().iterator();

        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getEmpNo() == empNo) {
                iterator.remove();
                dao.delete(Employee.class, employee.getEmpNo());
            }
        }

        if (SEARCH_CONTENT.equals(null) || SEARCH_CONTENT.equals(""))
            REL_CACHE_DATA = getRelCacheData();
        else {

            List<Employee> empList = dao.queryByJoin(Employee.class, null, Cnd.where("empNo", "like", "%" + SEARCH_CONTENT + "%"));
            REL_CACHE_DATA = FXCollections.observableArrayList(empList);
        }

        tableView.getItems().clear();
        tableView.setItems(REL_CACHE_DATA);
        tableView.refresh();
    }

}