package springcontainer.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String args[]) {
//        beanFactoryTest();
        applicationContext();
    }

//    public static void beanFactoryTest() {
//        BeanFactory beanFactory = new XmlBeanFactory(
//            new ClassPathResource("config/applicationContext2.xml"));
//
//        User user1  = beanFactory.getBean(User.class);
//        user1.setName("a");
//        System.out.println(user1);
//        }

    public static void applicationContext() {
        ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext(
                "config/applicationContext.xml");
        User user1  = applicationContext.getBean(User.class);
        user1.setName("a");
        System.out.println(user1);

        User user2 = (User) applicationContext.getBean("user1");
        System.out.println(user2);
    }
}
