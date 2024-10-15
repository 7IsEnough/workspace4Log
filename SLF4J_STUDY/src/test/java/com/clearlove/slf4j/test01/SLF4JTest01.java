package com.clearlove.slf4j.test01;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author promise
 * @date 2024/10/16 - 1:06
 */
public class SLF4JTest01 {

  @Test
  public void test01() {
    /*
      入门案例
        SLF4J对日志的级别划分
        trace、debug、info、warn、error五个级别
        trace:日志追踪信息
        debug:日志详细信息
        info: 日志的关键信息 默认打印级别
        warn: 日志警告信息
        error:日志错误信息

        在没有任何其他日志实现框架集成的基础之上
        slf4j使用的就是自带的框架slf4j-simple
        slf4j-simple也必须以单独依赖的形式导入进来
    */
    Logger logger = LoggerFactory.getLogger(SLF4JTest01.class);
    logger.trace("trace信息");
    logger.debug("debug信息");
    logger.info("info信息");
    logger.warn("warn信息");
    logger.error("error信息");
  }

  @Test
  public void test02() {
    /*
      我们输出动态的信息时
      也可以使用占位符的形式来代替字符串的拼接

      我们有些时候输出的日志信息，需要我们搭配动态的数据
      有可能是信息，有可能是数据库表中的数据
      总之我们这样做最大的好处就是能够让日志打印变得更加灵活
      如果是通过拼接字符串的形式，不仅麻烦，而且更重要的是可读性差
      我们的日志打印是支持以替代符的形式做日志信息拼接的
      一般情况下，几乎所有的日志实现产品，都会提供这种基础功能
    */
    Logger logger = LoggerFactory.getLogger(SLF4JTest01.class);
    String name = "zs";
    int age = 23;
    // logger.info("学生信息-姓名："+name+"；年龄："+age);
    // logger.info("学生信息-姓名：{}，年龄：{}"，new object[]{name,age});
    logger.info("学生信息-姓名：{}，年龄：{}", name, age);
  }

  @Test
  public void test03() {
    /*
      日志对于异常信息的处理

        一般情况下，我们在开发中的异常信息，都是记录在控制台上（我们开发环境的一种日志打印方式）
        我们会根据异常信息提取出有用的线索，来调试bug

        但是在真实生产环境中（项目上线），对于服务器或者是系统相关的问题
        在控制台上其实也会提供相应的异常或者错误信息的输出
        但是这种错误输出方式（输出的时间，位置，格式，，，）都是服务器系统默认的

        我们可以通过日志技术，选择将异常以日志打印的方式，进行输出查看
        输出的时间，位置（控制台，文件），格式，完全由我们自己去进行定义
     */
    Logger logger = LoggerFactory.getLogger(SLF4JTest01.class);
    try {
      Class.forName("aaa");
    } catch (Exception e) {
      // 打印栈追踪信息
      // e.printStackTrace();
      logger.info("XXX类中的XXX方法出现了异常，请及时关注信息");
      // e是引用类型对象，不能跟前面的{}做有效的字符串拼接
      // logger.info("具体错误：{}", e);
      // 不用加{}，直接后面加上异常对象e即可
      logger.info("具体错误：", e);
    }
  }

}
