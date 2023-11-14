package com.imooc.oa.dao;

import com.imooc.oa.entity.Notice;
import com.imooc.oa.util.MybatisUtils;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Date;

public class NoticeDaoTest extends TestCase {
    @Test
    public void testInsert(){
        MybatisUtils.executeQuery(sqlSession -> {
            NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
            Notice notice = new Notice();
            notice.setReceiverId(21L);
            notice.setContent("测试消息");
            notice.setCreateTime(new Date());
            dao.insert(notice);
            return null;
        });
    }

}