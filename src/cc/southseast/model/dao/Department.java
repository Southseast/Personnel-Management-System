package cc.southseast.model.dao;

import cc.southseast.model.base.BasePojo;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 11:22 AM
 * @Version 1.0
 * 部门
 */
@Table("t_department")
public class Department extends BasePojo {

    // 部门编号
    @Id(auto = false)
    private long depNo;

    // 部门名称
    @Column
    private String depName;

    // 办公地址
    @Column
    private String depAddress;

    // 办公电话
    @Column
    private String depPhone;

    public Department() {
    }

    public long getDepNo() {
        return depNo;
    }

    public void setDepNo(long depNo) {
        this.depNo = depNo;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepAddress() {
        return depAddress;
    }

    public void setDepAddress(String depAddress) {
        this.depAddress = depAddress;
    }

    public String getDepPhone() {
        return depPhone;
    }

    public void setDepPhone(String depPhone) {
        this.depPhone = depPhone;
    }

    @Override
    public String toString() {
        return depName;
    }
}
