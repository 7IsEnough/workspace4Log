package com.clearlove;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
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
     比info级别低的日志信息没有输出出来
     证明了info级别的日志信息，它是系统默认的日志级别
     在默认日志级别info的基础上，打印比它级别高的信息
     */
    Logger logger = Logger.getLogger("com.clearlove.JULTest");
    /**
     * 如果仅仅只是通过以下形式来设置日志级别
     * 那么不能够起到效果
     * 将来需要搭配处理器handler共同设置才会生效
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

  /**
   * 自定义日志级别
   */
  @Test
  public void test03(){
    // 日志记录器
    Logger logger = Logger.getLogger("com.clearlove.JULTest");

    // 将默认的日志打印方式关闭
    // 参数设置为false，打印日志的方式就不会按照父logger默认的方式去进行操作
    logger.setUseParentHandlers(false);

    // 创建处理器对象Handler
    // 控制台处理器
    ConsoleHandler handler = new ConsoleHandler();
    // 创建日志格式化组件对象
    SimpleFormatter formatter = new SimpleFormatter();

    // 设置输出格式
    handler.setFormatter(formatter);

    // 设置日志打印级别
    // 必须将日志记录器和处理器的级别进行统一的设置，才会达到日志显示相应级别的效果
//    logger.setLevel(Level.CONFIG);
//    handler.setLevel(Level.CONFIG);

    logger.setLevel(Level.ALL);
    handler.setLevel(Level.ALL);

    // 在记录器中添加处理器
    logger.addHandler(handler);

    logger.severe("severe信息");
    logger.warning("warning信息");
    logger.info("info信息");
    logger.config("config信息");
    logger.fine("fine信息");
    logger.finer("finer信息");
    logger.finest("finest信息");
  }

  /**
   * 将日志输出到具体的磁盘文件中
   * 相当于是做了日志的持久化操作
   */
  @Test
  public void test04() throws IOException {
    Logger logger = Logger.getLogger("com.clearlove.JULTest");
    logger.setUseParentHandlers(false);

    // 文件日志处理器
    FileHandler handler = new FileHandler("logs/MyLogTest.log");
    SimpleFormatter formatter = new SimpleFormatter();
    handler.setFormatter(formatter);
    logger.addHandler(handler);

    // 也可以同时在控制台和文件上进行打印
    ConsoleHandler handler1 = new ConsoleHandler();
    handler1.setFormatter(formatter);
    // 可以在记录器中同时添加多个处理器
    logger.addHandler(handler1);

    logger.setLevel(Level.ALL);
    handler.setLevel(Level.ALL);
    handler1.setLevel(Level.ALL);

    logger.severe("severe信息");
    logger.warning("warning信息");
    logger.info("info信息");
    logger.config("config信息");
    logger.fine("fine信息");
    logger.finer("finer信息");
    logger.finest("finest信息");

    /*
      总结：
        用户使用Logger来进行日志的记录，Logger可以持有多个处理器Handler
        (日志的记录使用的是Logger,日志的输出使用的是Handler)
        添加了哪些handler对象，就相当于需要根据所添加的handler
        将日志输出到指定的位置上，例如控制台、文件
    */
  }

  @Test
  public void test05(){
    /*
      Logger之间的父子关系
        JUL中Logger.之间是存在"父子"关系的
        值得注意的是，这种父子关系不是我们普遍认为的类之间的继承关系
        关系是通过树状结构存储的

        JUL在初始化时会创建一个顶层RootLogger作为所有Logger的父Logger
        查看源码：
          owner.rootLogger owner.new RootLogger();
          RootLogger是LogManager的内部类
          java.util.logging.LogManager$RootLogger
          默认的名称为空串

        以上的RootLogger对象作为树状结构的根节点存在的
        将来自定义的父子关系通过路径来进行关联
        父子关系，同时也是节点之间的挂载关系
        owner.addLogger(owner.rootLogger);
        LoggerContext cx = getUserContext(); // LoggerContext一种用来保存节点的Map关系
        private LogNode node; // 节点
    */

    /*
       logger1是父logger，logger2是子logger
    */
    // 父亲为RootLogger，名称默认是一个空字符串
    // RootLogger可以被称之为所有logger对象的顶层logger
    Logger logger1 = Logger.getLogger("com.clearlove");
    Logger logger2 = Logger.getLogger("com.clearlove.JULTest");

    // System.out.println(logger2.getParent() == logger1); // true
    System.out.println("logger1的父Logger引用为：" + logger1.getParent() + "; 名称为：" + logger1.getName() + "; 父亲的名称为：" + logger1.getParent().getName());
    System.out.println("logger2的父Logger引用为：" + logger2.getParent() + "; 名称为：" + logger2.getName() + "; 父亲的名称为：" + logger2.getParent().getName());

    /*
      父亲所做的设置，也能够同时作用于儿子
      对logger1做日志打印相关的设置，然后使用logger2进行日志的打印
    */
    // 父亲做设置
    logger1.setUseParentHandlers(false);
    ConsoleHandler handler = new ConsoleHandler();
    SimpleFormatter formatter = new SimpleFormatter();
    handler.setFormatter(formatter);
    logger1.addHandler(handler);
    logger1.setLevel(Level.ALL);
    handler.setLevel(Level.ALL);

    // 儿子做打印
    logger2.severe("severe信息");
    logger2.warning("warning信息");
    logger2.info("info信息");
    logger2.config("config信息");
    logger2.fine("fine信息");
    logger2.finer("finer信息");
    logger2.finest("finest信息");
  }

  @Test
  public void test06() throws IOException {
    /*
      以上所有的配置相关的操作，都是以java硬编码的形式进行的
      我们可以使用更加清晰，更加专业的一种做法，就是使用配置文件
      如果我们没有自己添加配置文件，则会使用系统默认的配置文件
      这个配置文件：
      owner.readPrimordialConfiguration();
      readConfiguration();
      java.home->找到jre文件夹->lib->logging.properties
    */
    InputStream input = new FileInputStream("logging.properties");

    // 获取日志管理器对象
    LogManager manager = LogManager.getLogManager();

    // 读取自定义配置文件
    manager.readConfiguration(input);

    Logger logger = Logger.getLogger("com.clearlove.JULTest");

    logger.severe("severe信息");
    logger.warning("warning信息");
    logger.info("info信息");
    logger.config("config信息");
    logger.fine("fine信息");
    logger.finer("finer信息");
    logger.finest("finest信息");
  }
}
