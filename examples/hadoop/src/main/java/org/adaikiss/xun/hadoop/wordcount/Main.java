package org.adaikiss.xun.hadoop.wordcount;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hlw
 *
 */
public class Main {
    public static void main(String[] arguments) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
