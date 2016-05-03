package aoptest.main;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    @Before(
        "execution(* *..*.findProduct(..))")
    public void before() {
        System.out.println("call [before advice]");
    }


    @After(
        "execution( * *..aoptest.ProductService.*(..))")
    public void after() {
        // 메소드 종료 시점에 호출
        System.out.println("call [after advice]");
    }

    @Around(
        "execution(* *..aoptest.*.*(..))")
    public ProductVo around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("call [around device] : before");
        ProductVo productVo = (ProductVo) pjp.proceed();
        System.out.println("call [around device] : after");

        return productVo;
    }

    @AfterReturning(
        value = "execution(* *..aoptest.*.*(..))", returning = "vo")
    public void afterReturning(ProductVo vo) {
        System.out.println("call [returning device] + vo = " + vo);
    }

    @AfterThrowing(
        value = "execution(* *..aoptest.*.*(..))", throwing = "e")
    public void afterThrowing(Throwable e) {
        System.out.println("call [afterThrowing]: " + e);

    }
}
