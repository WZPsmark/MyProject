package com.voleypro;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by smark on 2016/12/14.
 */

@Aspect
public class TextAspect {

    static final  String TAG = "TextAspect";



    @Pointcut("execution(* com.voleypro.MainActivity.on*(..))")
    public void loginForActivity(){}



    @Before("loginForActivity()")
    public void log(JoinPoint joinPoint){
        Log.e(TAG,joinPoint.toShortString());

    }

    @Pointcut("execution(@SecurityCheckAnnotation public * *..*.*(..)) && @annotation(ann)")
    public void checkPermssion(SecurityCheckAnnotation ann){};

    @Before("checkPermssion(securityCheckAnnotation)")
    public void check(JoinPoint joinPoint,SecurityCheckAnnotation securityCheckAnnotation){
        String neededPermission = securityCheckAnnotation.declredPermission();
        Log.e(TAG, "\tneeded permission is " + neededPermission);
        SecurityCheckManager manager = SecurityCheckManager.getInstanc();
        if(manager.checkPermission(neededPermission) == false){
            throw new SecurityException("Need to declare permission:" + neededPermission);
        }
        return;
    }

}
