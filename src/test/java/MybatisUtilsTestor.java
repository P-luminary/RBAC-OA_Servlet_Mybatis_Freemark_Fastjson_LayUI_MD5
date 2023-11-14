import org.junit.Test;
import com.imooc.oa.util.MybatisUtils;

public class MybatisUtilsTestor {
    @Test
    public void testcase1(){
        String result = (String)MybatisUtils.executeQuery(sqlSession -> {
            String out = (String)sqlSession.selectOne("test.sample");
            return out; //out会被retrun obj接收 返回Object
        });
        System.out.println(result);
    }
    @Test
    public void testcase2(){
        String result = (String) MybatisUtils.executeQuery(sqlSession -> sqlSession.selectOne("test.sample"));
        System.out.println(result);
    }
}
