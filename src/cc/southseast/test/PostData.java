package cc.southseast.test;

import cc.southseast.model.dao.Post;

import java.util.Random;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.POST_SORT;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 11:30 AM
 * @Version 1.0
 * 职工插入
 */

public class PostData {

    public static void main(String[] args) {

        Random rand = new Random();

        for (int i = 0; i < POST_SORT.length; i++) {

            dao.create(Post.class, false);

            Post post = new Post();

            post.setPostId(i);
            post.setPostName(POST_SORT[i]);


            dao.insert(post);
        }

    }

}