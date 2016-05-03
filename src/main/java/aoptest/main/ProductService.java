package aoptest.main;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public ProductVo findProduct(String name) {
        System.out.println("finding " + name + "...");


        return new ProductVo(name);
    }
}
