package cc.southseast.model.dao;

import cc.southseast.model.base.BasePojo;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

import java.sql.Date;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 8:40 PM
 * @Version 1.0
 * 职工
 */
@Table("t_employee")
public class Employee extends BasePojo {

    // 工号
    @Id(auto = false)
    private long empNo;

    // 姓名
    @Column
    private String empName;

    // 密码
    @Column
    private String empPassword;

    // 性别
    @Column
    private String empSex;

    // 生日，计算年龄
    @Column
    private Date empBirthday;

    // 入职时间，计算工龄
    @Column
    private Date entryTime;

    // 电话
    @Column
    private String empTelphone;

    // 邮箱
    @Column
    private String empEmail;

    // 管理员
    @Column
    private boolean isAdmin;

    // 部门编号
    @Column
    private long depNo;

    // 员工等级
    @Column
    private long empLevel;

    // 员工职位
    @Column
    private String empPost;

    private String depName;

    @One(field = "depNo")
    private Department department;

    public Employee() {
    }

    public long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(long empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }

    public Date getEmpBirthday() {
        return empBirthday;
    }

    public void setEmpBirthday(Date empBirthday) {
        this.empBirthday = empBirthday;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getEmpTelphone() {
        return empTelphone;
    }

    public void setEmpTelphone(String empTelphone) {
        this.empTelphone = empTelphone;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public long getDepNo() {
        return depNo;
    }

    public void setDepNo(long depNo) {
        this.depNo = depNo;
    }

    public long getEmpLevel() {
        return empLevel;
    }

    public void setEmpLevel(long empLevel) {
        this.empLevel = empLevel;
    }

    public String getEmpPost() {
        return empPost;
    }

    public void setEmpPost(String empPost) {
        this.empPost = empPost;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDepName() {
        return department.getDepName();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", empName='" + empName + '\'' +
                ", empPassword='" + empPassword + '\'' +
                ", empSex='" + empSex + '\'' +
                ", empBirthday=" + empBirthday +
                ", entryTime=" + entryTime +
                ", empTelphone='" + empTelphone + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", isAdmin=" + isAdmin +
                ", depNo=" + depNo +
                ", department=" + department +
                '}';
    }
}