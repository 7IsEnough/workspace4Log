package com.clearlove.logback.test01;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author promise
 * @date 2024/11/6 - 1:27
 */
public class LOGBACKTest01 {

  @Test
  public void test01() {

    /*
      入门案例
        logback有5种级别的日志输出
        分别是
        trace < debug < info < warn < error

        通过信息打印，默认的日志级别是debug,trace信息没有打印出米
     */
    Logger logger = LoggerFactory.getLogger(LOGBACKTest01.class);
    logger.trace("trace信息");
    logger.debug("debug信息");
    logger.info("info信息");
    logger.warn("warn信息");
    logger.error("error信息");
  }

  @Test
  public void test02() {
    /*
      Logback配置文件的使用
        在resources下面，创建一份配置文件，命名为logback.xml

        一切配置都是在根标签中进行操作的
        <configuration>
        </configuration>

     */
    Logger logger = LoggerFactory.getLogger(LOGBACKTest01.class);
  }

}
