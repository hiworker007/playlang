package playlang.infrastructure;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import playlang.domain.Person;

import java.util.concurrent.atomic.AtomicBoolean;

public class HibernateSessionFactory {
    private final Configuration configuration = new Configuration();
    private final AtomicBoolean isInitial = new AtomicBoolean(false);

    public SessionFactory buildSessionFactory() {
        if (isInitial.compareAndSet(false, true))
            initConfiguration();

        return configuration.buildSessionFactory();
    }

    private void initConfiguration() {

        // 配置H2数据库连接
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:~/test");
        configuration.setProperty("hibernate.connection.username", "sa");
        configuration.setProperty("hibernate.connection.password", "");
        // 配置其他属性
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.show_sql", "false");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        // 添加实体类
        configuration.addAnnotatedClass(Person.class);
    }

}
