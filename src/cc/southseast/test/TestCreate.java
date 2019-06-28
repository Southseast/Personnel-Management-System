package cc.southseast.test;

import cc.southseast.model.dao.Department;
import cc.southseast.model.dao.Employee;
import cc.southseast.model.dao.Post;

import static cc.southseast.controller.base.ToConnect.dao;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 11:30 AM
 * @Version 1.0
 * 数据库创建
 */

public class TestCreate {

    public static void main(String[] args) {

        dao.create(Department.class, false);
        dao.create(Employee.class, false);
        dao.create(Post.class, false);

    }

}