package com.clearlove;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author promise
 * @date 2024/12/1 - 22:43
 */
public class Log4j2Test01 {

  @Test
  public void test01() {

    /*
      入门案例
        单纯使用Log4j2的 门面 + 实现
        Log4j2和log4j提供了相同的日志级别输出
        默认为error级别信息的打印

        ERROR StatusLogger No Log4j 2 configuration file found. Using default configuration
        表示我们还没有建立自己的配置文件
        如果没有建立配置文件，则使用默认的配置
     */

    // Logger logger = LogManager.getLogger(Log4j2Test01.class);
    // logger.fatal("fatal信息");
    // logger.error("error信息");
    // logger.warn("warn信息");
    // logger.info("info信息");
    // logger.debug("debug信息");
    // logger.trace("trace信息");

  }

  @Test
  public void test02() {

    /*
      使用配置文件
        log4j2是参考logback创作出来的，所以配置文件也是使用xml
        log4j2同样是默认加载类路径(resources)下的log4j2.xml文件中的配置

        根标签，所有日志相关信息，都是在根标签中进行配置
        <Configuration status="debug" monitorInterval="数值"></Configuration>
        在根标签中，可以加属性
        status="debug" 日志框架本身的日志输出级别
        monitorInterval="5" 自动加载配置文件的间隔时间，不低于5s
     */
    // Logger logger = LogManager.getLogger(Log4j2Test01.class);
    // logger.fatal("fatal信息");
    // logger.error("error信息");
    // logger.warn("warn信息");
    // logger.info("info信息");
    // logger.debug("debug信息");
    // logger.trace("trace信息");

  }

  @Test
  public void test03() {
    /*
      虽然log4j2也是日志门面，但是现在市场的主流趋势仍然是slf4j
      所以我们仍然需要使用slf4j作为日志门面，搭配log4j2强大的日志实现功能，进行日志的相关操作

      接下来我们配置的就是当今市场上的最强大，最主流的日志使用搭配方式：
      slf4j+log4j2

      1.导入slf4j的日志门面
      2.导入log4j2的适配器
      3.导入log4j2的日志门面
      4.导入log4j2的日志实现

      执行原理：
        slf4j门面调用的是log4j2的门面，再由log4j2的门面调用log4j2的实现
     */

    Logger logger = LoggerFactory.getLogger(Log4j2Test01.class);

    logger.error("error信息");
    logger.warn("warn信息");
    logger.info("info信息");
    logger.debug("debug信息");
    logger.trace("trace信息");

  }


}
