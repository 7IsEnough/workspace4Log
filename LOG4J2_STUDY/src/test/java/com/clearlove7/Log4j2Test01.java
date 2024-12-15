package com.clearlove7;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author promise
 * @date 2024/12/15 - 19:42
 */
public class Log4j2Test01 {

  @Test
  public void test01() {

    /*
      不在自定义logger配置下的目录，异步打印不会生效
     */

    Logger logger = LoggerFactory.getLogger(Log4j2Test01.class);
    // 日志的记录
    for (int i = 0; i < 2000; i++) {
      logger.error("error信息");
      logger.warn("warn信息");
      logger.info("info信息");
      logger.debug("debug信息");
      logger.trace("trace信息");
    }

    // 系统业务逻辑
    for (int i = 0; i < 1000; i++) {
      System.out.println("-----------------");
    }
  }

}
