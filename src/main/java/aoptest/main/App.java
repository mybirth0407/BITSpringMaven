package aoptest.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext(
                "config/applicationContext3.xml");

        ProductService productService =
            (ProductService) applicationContext.getBean("productService");

        productService.findProduct("TV");
    }
}
