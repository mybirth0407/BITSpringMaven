package mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class MeasureDaoExecutionTimeAspect {
    public Object around(ProceedingJoinPoint pjp) {
        String taskName = pjp.getTarget().getClass() + "." +
            pjp.getSignature().getName();

        return null;
    }
}
