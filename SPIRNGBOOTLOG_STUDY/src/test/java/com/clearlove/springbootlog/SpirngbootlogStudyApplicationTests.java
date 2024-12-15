package com.clearlove.springbootlog;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpirngbootlogStudyApplicationTests {

  @Test
  public void test01() {
    /*
      入门案例
          springboot日志具体实现
              级别测试
              默认是info级别
              logback的风格输出(默认使用的是logback的日志实现)
     */
    Logger logger = LoggerFactory.getLogger(SpirngbootlogStudyApplicationTests.class);
    logger.trace("trace信息");
    logger.debug("debug信息");
    logger.info("info信息");
    logger.warn("warn信息");
    logger.error("error信息");
  }

  @Test
  public void test02() {
    /*
      使用log4j2的日志实现
      观察桥接器是否起作用
      结果：仍然是slf4j + logback
      证明桥接器是起作用的
     */
    org.apache.logging.log4j.Logger logger = LogManager.getLogger(SpirngbootlogStudyApplicationTests.class);
    logger.info("info信息");
  }

  @Test
  public void test03() {
    /*
      application.properties(yml)是springboot的核心配置文件（用来简化开发使用）

      我们也可以通过该配置文件，修改日志相关的配置

     */
    Logger logger = LoggerFactory.getLogger(SpirngbootlogStudyApplicationTests.class);
    logger.trace("trace信息");
    logger.debug("debug信息");
    logger.info("info信息");
    logger.warn("warn信息");
    logger.error("error信息");

  }

  @Test
  public void test04() {
    /*

        将日志输出到文件中
            使用logging.file.path来配置文件路径下的文件夹(logging.file直接配置文件的形式己经过时，不使用)
            在配置的文件夹下，日志文件生成的名字为spring.log

     */

    Logger logger = LoggerFactory.getLogger(SpirngbootlogStudyApplicationTests.class);
    logger.trace("trace信息");
    logger.debug("debug信息");
    logger.info("info信息");
    logger.warn("warn信息");
    logger.error("error信息");


  }

  @Test
  public void test05() {

    /*
        如果是需要配置日志拆分等相对高级的功能
        那么application.properties就达不到需要了
        需要使用日志实现相应的配置文件

        例如我们现在使用的是logback日志实现
        那么就需要在类路径resources下，配置logback.xml

        由于log4j2性能的强大
        当今市场上越来越多的项目选择使用slf4j+log4j2的组合
        springboot默认使用的是slf4j+logback的组合
        我们可以将默认的logback替换成为log4j2

        1.启动器依赖，间接的依赖logback
          所以需要将之前的环境中，logback的依赖去除掉
        2.添加log4j2的依赖
        3.将log4j2的配置文件log4j2.xml导入到类路径resources下面
     */
    // Logger logger = LoggerFactory.getLogger(SpirngbootlogStudyApplicationTests.class);
    //
    // for (int i = 0; i < 2000; i++) {
    //   logger.trace("trace信息");
    //   logger.debug("debug信息");
    //   logger.info("info信息");
    //   logger.warn("warn信息");
    //   logger.error("error信息");
    // }

    Logger logger = LoggerFactory.getLogger(SpirngbootlogStudyApplicationTests.class);
    logger.trace("trace信息");
    logger.debug("debug信息");
    logger.info("info信息");
    logger.warn("warn信息");
    logger.error("error信息");
  }
}
