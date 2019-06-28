package cc.southseast.test;

import cc.southseast.model.dao.Department;

import java.util.Random;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.DEPARTMENT_SORT;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 11:30 AM
 * @Version 1.0
 * 部门数据插入
 */

public class DepartmentRandData {

    public static void main(String[] args) {

        Random rand = new Random();

        for (int i = 0; i < DEPARTMENT_SORT.length; i++) {

            dao.create(Department.class, false);

            Department department = new Department();

            // 创建部门编号
            department.setDepNo(i);

            // 创建部门名字
            department.setDepName(DEPARTMENT_SORT[i]);

            // 地址
            department.setDepAddress(rand.nextInt(10) + "楼" + rand.nextInt(10) + "0" + rand.nextInt(10));

            // 电话
            String depPhone = "1";
            for (int j = 0; j < 8; j++) {
                depPhone += rand.nextInt(10);
            }
            department.setDepPhone(depPhone);


            dao.insert(department);
        }

    }

}