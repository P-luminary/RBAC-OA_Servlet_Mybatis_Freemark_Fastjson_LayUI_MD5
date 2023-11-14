package com.imooc.oa.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DruidDataSourceFactory extends UnpooledDataSourceFactory {
    public DruidDataSourceFactory(){ //1.创造空的数据源对象
        // 2.调用setProperties读取xml对dataSource属性源进行设置
        this.dataSource = new DruidDataSource(); //表达数据源信息
    }
    //3.数据源需要额外设置要重写
    @Override
    public DataSource getDataSource() { //获取已经初始化的连接池进行返回
        try {
            ((DruidDataSource)this.dataSource).init(); //初始化Druid数据源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.dataSource;
    }
}
