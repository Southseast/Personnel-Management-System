package cc.southseast.controller.base;

import cc.southseast.model.dao.Department;
import cc.southseast.model.dao.Employee;
import cc.southseast.model.dao.Post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static cc.southseast.controller.base.ToConnect.dao;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 10:54 AM
 * @Version 1.0
 * 数据获取
 */
public class ToGetData {

    public static final String[] DEPARTMENT_SORT = new String[]{"财务部", "行政后勤部", "人事部", "企划部", "渠道部", "技术部", "销售部", "总经办", "项目工程部", "审计部", "质量管理部", "投资发展部", "生产研发部", "采购部", "客户服务部", "董事会"};
    public static final String[] SEX_SORT = new String[]{"男", "女"};
    public static final String[] POST_SORT = new String[]{"总裁", "副总裁", "总裁助理", "主任", "副主任", "总监", "副总监", "总经理", "副总经理", "组长", "副组长", "项目经理", "主管", "副主管", "工程师", "专员", "检验员", "普通员工", "食堂大妈", "保安"};
    public static ObservableList<Employee> EMP_CACHE_DATA;
    public static ObservableList<Department> DEP_CACHE_DATA;
    public static ObservableList<Employee> REL_CACHE_DATA;
    public static ObservableList<Post> POST_CACHE_DATA;
    public static String SEARCH_CONTENT = "";
    public static Employee USER;
    // 验证码
    public static String CODE = "";

    public static ObservableList<Employee> getEmpCacheData() {
        return FXCollections.observableArrayList(dao.query(Employee.class, null));
    }

    public static ObservableList<Department> getDepCacheData() {
        return FXCollections.observableArrayList(dao.query(Department.class, null));
    }

    public static ObservableList<Employee> getRelCacheData() {
        return FXCollections.observableArrayList(dao.queryByJoin(Employee.class, null, null));
    }

    public static ObservableList<Post> getPostCacheData() {
        return FXCollections.observableArrayList(dao.query(Post.class, null));
    }
}
