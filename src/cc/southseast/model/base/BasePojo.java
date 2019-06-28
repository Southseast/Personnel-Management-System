package cc.southseast.model.base;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Prev;

import java.util.Date;

/**
 * @Author: Southseast
 * @Date: 2019/1/5 2:41 PM
 * @Version 1.0
 * 基类信息，更新情况
 */
public abstract class BasePojo {

    // 创建时间
    @Column
    @Prev(els = @EL("$me.now()"))
    protected Date createTime;

    // 更新时间
    @Column
    @Prev(els = @EL("$me.now()"))
    protected Date updateTime;

    // 多选
    private boolean isCheck;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date now() {
        return new Date();
    }

    public boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

}
