package springcontainer.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
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
                "config/applicationContext1.xml");
//        User user1  = applicationContext.getBean(User.class);
//        user1.setName("끄억");
//        System.out.println(user1);

        User user1 = (User) applicationContext.getBean("user1");
        System.out.println(user1);

        User user2 = (User) applicationContext.getBean("user2");
        System.out.println(user2);
        
        User user3 = (User) applicationContext.getBean("user3");
        System.out.println(user3);

        User user4 = (User) applicationContext.getBean("user4");
        System.out.println(user4);

        Guest guest1 = (Guest) applicationContext.getBean("guest");
        System.out.println(guest1);

        Guest guest2 = (Guest) applicationContext.getBean("guest2");
        System.out.println(guest2);

//        Friend friend1 = (Friend) applicationContext.getBean("heedongyee");
//        System.out.println(friend1);

        ((ConfigurableApplicationContext) applicationContext).close();
    }
}
