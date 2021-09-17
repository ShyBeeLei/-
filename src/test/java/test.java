import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName test
 * @Description
 * @Author Bruce Xu
 * @Date 2021/9/9 23:16
 * @Version 1.0
 */
@SpringBootTest
public class test {
    @Autowired
    ApplicationContext context;


    @Test
    public void test() throws SQLException {
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }
}
