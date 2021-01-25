package com.training.monitor.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InfoServiceAspect {

    @Before("execution(* com.training.monitor.service.InfoService.create(..))")
    public void createInfoBefore() {
        System.out.println("Info.createInfoBeforeAdvice() :");
    }

    @Before("execution(* com.training.monitor.service.InfoService.viewInfo(..))")
    public void viewInfoBefore() {
        System.out.println("Info.viewInfoBeforeAdvice() :");
    }

    @Before("execution(* com.training.monitor.service.InfoService.searchInfo(..))")
    public void searchInfoBefore() {
        System.out.println("Info.searchInfoBeforeAdvice() :");
    }

    @Before("execution(* com.training.monitor.service.InfoService.giveReport(..))")
    public void giveReportBefore() {
        System.out.println("Info.giveReportBeforeAdvice() :");
    }

    @After("execution(* com.training.monitor.service.InfoService.create(..))")
    public void createInfoAfter(){
        System.out.println("Org.createInfoAfter: ");
    }

    @After("execution(* com.training.monitor.service.InfoService.viewInfo(..))")
    public void viewInfoAfter(){
        System.out.println("Org.viewInfoAfter: ");
    }

    @After("execution(* com.training.monitor.service.InfoService.searchInfo(..))")
    public void searchInfoAfter(){
        System.out.println("Org.searchInfoAfter: ");
    }

    @After("execution(* com.training.monitor.service.InfoService.giveReport(..))")
    public void giveReportAfter(){
        System.out.println("Org.giveReportAfter: ");
    }
}
