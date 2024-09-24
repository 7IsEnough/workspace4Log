package com.clearlove;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 * @author promise
 * @date 2024/9/25 - 1:18
 */
public class JULTest {

  @Test
  public void test01() {

    // Logger对象的创建方式，不能直接new 对象
    // 取得对象的方法参数，需要引入当前类的全路径字符串
    Logger logger = Logger.getLogger("com.clearlove.JULTest");

    /**
     * 日志输出有2种方式
     * 1. 直接调用日志级别相关方法，方法中传递日志输出信息
     * 2. 调用通用的log方法，在里面通过Level类型定义日志的级别，以及搭配日志输出信息的参数
     */
    logger.info("输出info信息1");
    logger.log(Level.INFO, "输出info信息2");

  }
}
