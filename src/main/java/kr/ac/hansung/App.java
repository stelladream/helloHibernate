package kr.ac.hansung;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        /*Configuration conf= new Configuration();
        conf.configure();
        SessionFactory sessionFactory = conf.buildSessionFactory();*/

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Product product1 = new Product();
        product1.setName("노트북2");
        product1.setPrice(2000);
        product1.setDescription("Awesome Notebook");

        Product product2 = new Product();
        product2.setName("노트북1");
        product2.setPrice(1000);
        product2.setDescription("Powerful Notebook");

        session.save(product1);
        session.save(product2);

       /* Product savedProduct = session.get(Product.class, product1.getId());
        System.out.println("saved product: " + savedProduct);*/

        Query<Product> aQuery = session.createQuery("from Product order by name ", Product.class);  //HQL
        List<Product>  products = aQuery.getResultList();
        System.out.println(products);

        tx.commit();
        session.close();
        sessionFactory.close();


    }
}
