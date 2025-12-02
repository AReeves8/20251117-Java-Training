package com.skillstorm.spring_aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;        // Simple Logging Facade for Java - abstraction for various logging frameworks
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.skillstorm.spring_aop.models.Director;
import com.skillstorm.spring_aop.models.Movie;


@Component      // no sterotype annotation for aspects in spring boot
@Aspect         // from AspectJ -> registers this class as an AOP aspect
public class LoggingAspect {



    /**
     * Spring Boot has many different loggers that it supports
     *      - Logback and Java Util Logging implemented OOTB in spring boot 
     *      - Log4J2 (don't use version one)
     * 
     *      - SLF4J can work with any that is used by spring
     * 
     * 
     *      Logging Levels
     *          - fatal -> error -> warn -> info -> debug -> trace
     * 
     *          - can be set for project in application.yml/properties
     *      
     */
    Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * ASPECT
     *      - define pointcuts, and advice
     *      - join points just exist throughout our application
     */


    /**
     * options for pointcuts:
     *      within() - specify a package or class
     *      execution() - specify a method
     *      bean() - specify a specific bean
     * 
     *      need entire path defined - basically same as an import statement
     */
    @Pointcut("within(com.skillstorm.spring_aop.controllers.MovieController)")  // all methods in the MovieController class
    public void checkMovie() {
        /* leave empty - this method is never actually executed */
    }

    // can use * as a wildcard
    @Pointcut("execution(public * saveMovie(com.skillstorm.spring_aop.models.Movie)) && args(movieToBeSaved)")
    public void checkCreateMovie(Movie movieToBeSaved) {

    }


    /**
     * TYPES OF ADVICE
     *      Before  - advice runs before the method executes
     *      After - advice runs after the method executes
     *      AfterReturning - advice runs after the method executes AND returns something
     *      AfterThrowing - advice runs after the method throws an excpetion
     *      Around - all of the above
     */

    @Before("checkMovie()")    // tells the advice which pointcut to run on - use method name of pointcut   
    public void movieRequest(JoinPoint joinPoint) {
        // JoinPoint gives information about the join point that is being invoked
        
        // since this runs before the method is invoked, log request info
        log.info("A request was made to [{}] with the argument(s) {}", 
            joinPoint.getSignature(),
            joinPoint.getArgs()
        );
    }

    // returning tells the aspect to put whatever was returned into the specified param name
    @AfterReturning(value = "checkCreateMovie(movieToBeSaved)", returning = "returnedData") 
    public void movieCreation(JoinPoint joinPoint, Object returnedData) {
        log.info("[{}] returned with the following data: {}", 
            joinPoint.getSignature(),
            returnedData.toString()
        );
    }


    @Around("checkCreateMovie(movieToBeSaved)")
    public Movie logDirectors(ProceedingJoinPoint proceedingJoinPoint, Movie movieToBeSaved) {

        Director director = movieToBeSaved.getDirector();

        // new movies have to use an EXISTING director (not null and id > 0)
        if(director != null && director.getId() > 0) {
            log.debug("DIRECTOR TO BE SAVED: {}", director.toString());

            /**
             * PROCEEDING JOIN POINT
             *      - join point with an extra method .proceed()
             *          - tells the code to continue with the normal method execution
             *              - original method WILL NOT EXECUTE until we call .proceed()
             * 
             *      - only available on Around advice
             *      - NEEDS to be used with Around advice
             */
            try{
                proceedingJoinPoint.proceed();
            }
            catch(Throwable t) {
                log.error("Method [{}] could not be executed. Error: [{}]", 
                    proceedingJoinPoint.getSignature(), 
                    t.getMessage()
                );
            }
        }
        else {
            log.error("{} could not be created. Method not invoked.", movieToBeSaved);
        }

        return movieToBeSaved;
    }

}
