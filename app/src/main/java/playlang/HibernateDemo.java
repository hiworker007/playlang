package playlang;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import playlang.infrastructure.HibernateSessionFactory;
import playlang.domain.Person;


public class HibernateDemo {
    private final HibernateSessionFactory hibernateSessionFactory = new HibernateSessionFactory();

    public void run() {
        // 创建 SessionFactory 对象
        SessionFactory sessionFactory = hibernateSessionFactory.buildSessionFactory();
        // 创建 Session 对象
        Session session = sessionFactory.openSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();

        // 创建实体对象
        Person person = new Person();
        person.setName("Tom");

        // 保存实体对象到数据库中
        session.persist(person);

        // 提交事务
        transaction.commit();

        var x = session.find(Person.class, 1);
        System.out.println(x);

        // 关闭 Session 和 SessionFactory
        session.close();
        sessionFactory.close();
    }

}
