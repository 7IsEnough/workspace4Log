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

    String name = "zs";
    int age = 23;
    // 较复杂的日志方式
    logger.log(Level.INFO,"学生的姓名为："+name+"；年龄为："+age);
    // 较简易的方式
    logger.log(Level.INFO,"学生的姓名：{0},年龄：{1}",new Object[]{name,age});
  }

  @Test
  public void test02(){
    /**
     * 日志的级别（通过源码查看，非常简单）
     * SEVERE:错误--最高级的日志级别
     * WARNING:警告
     * INFO:(默认级别)消息
     * CONFIG:配置
     * FINE:详细信息（少）
     * FINER:详细信息（中）
     * FINEST:详细信息（多）---最低级的日志级别
     *
     * 两个特殊的级别
     * OFF可用来关闭日志记录
     * ALL启用所有消息的日志记录
     *
     * 对于日志的级别，我们重点关注的是new对象的时候的第二个参数
     * 是一个数值
     * OFF Integer.MAX VALUE 整型最大值
     * SEVERE 1000
     * WARNING 900
     * ...
     * FINEST 300
     * ALL Integer,MIN VALUE 整型最小值
     *
     * 这个数值的意义在于，如果我们设置的日志的级别是INF0--800
     * 那么最终展现的日志信息，必须是数值大于等于800的所有的日志信息
     * 最终展现的就是
     * SEVERE
     * WARNING
     * INFO
     */


    /**
     通过打印结果，我们看到了仅仅只是输出了info级别以及比info级别高的日志信息
     比info级别低的甲志信息没有输出出来
     证明了info级别的日志信息，它是系统默认的日志级别
     在默认日志级别info的基础上，打印比它级别高的信息
     */
    Logger logger = Logger.getLogger("com.clearlove.JULTest");
    /**
     * 如果仅仅只是通过以下形式来设置日志级别
     * 那么不能够起到效果
     * 将来需要搭配处理器handler共同设置才会生刘
     */
    logger.setLevel(Level.ALL);

    logger.severe("severe信息");
    logger.warning("warning信息");
    logger.info("info信息");
    logger.config("config信息");
    logger.fine("fine信息");
    logger.finer("finer信息");
    logger.finest("finest信息");
  }
}
