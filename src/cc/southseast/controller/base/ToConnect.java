package cc.southseast.controller.base;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import javax.sql.DataSource;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 8:45 AM
 * @Version 1.0
 * 数据库连接
 */
public class ToConnect {

    public static Ioc ioc;

    public static DataSource ds;

    public static Dao dao;

    static {
        ioc = new NutIoc(new JsonLoader("DatabaseSource.js"));
        ds = ioc.get(DataSource.class);
        // 如果已经定义了dao,那么改成dao = ioc.get(Dao.class);
        dao = new NutDao(ds);
    }
}
