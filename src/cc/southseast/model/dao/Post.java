package cc.southseast.model.dao;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Author: Southseast
 * @Date: 2019/1/8 3:30 PM
 * @Version 1.0
 * 职位
 */
@Table("dic_post")
public class Post {

    @Id(auto = false)
    private long postId;

    @Column
    private String postName;

    public Post() {
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Override
    public String toString() {
        return postName;
    }
}
