package com.clearlove.slf4j.test01;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author promise
 * @date 2024/10/16 - 1:06
 */
public class SLF4JTest01 {

  // @Test
  // public void test01() {
  //   /*
  //     入门案例
  //       SLF4J对日志的级别划分
  //       trace、debug、info、warn、error五个级别
  //       trace:日志追踪信息
  //       debug:日志详细信息
  //       info: 日志的关键信息 默认打印级别
  //       warn: 日志警告信息
  //       error:日志错误信息
  //
  //       在没有任何其他日志实现框架集成的基础之上
  //       slf4j使用的就是自带的框架slf4j-simple
  //       slf4j-simple也必须以单独依赖的形式导入进来
  //   */
  //   Logger logger = LoggerFactory.getLogger(SLF4JTest01.class);
  //   logger.trace("trace信息");
  //   logger.debug("debug信息");
  //   logger.info("info信息");
  //   logger.warn("warn信息");
  //   logger.error("error信息");
  // }
  //
  // @Test
  // public void test02() {
  //   /*
  //     我们输出动态的信息时
  //     也可以使用占位符的形式来代替字符串的拼接
  //
  //     我们有些时候输出的日志信息，需要我们搭配动态的数据
  //     有可能是信息，有可能是数据库表中的数据
  //     总之我们这样做最大的好处就是能够让日志打印变得更加灵活
  //     如果是通过拼接字符串的形式，不仅麻烦，而且更重要的是可读性差
  //     我们的日志打印是支持以替代符的形式做日志信息拼接的
  //     一般情况下，几乎所有的日志实现产品，都会提供这种基础功能
  //   */
  //   Logger logger = LoggerFactory.getLogger(SLF4JTest01.class);
  //   String name = "zs";
  //   int age = 23;
  //   // logger.info("学生信息-姓名："+name+"；年龄："+age);
  //   // logger.info("学生信息-姓名：{}，年龄：{}"，new object[]{name,age});
  //   logger.info("学生信息-姓名：{}，年龄：{}", name, age);
  // }
  //
  // @Test
  // public void test03() {
  //   /*
  //     日志对于异常信息的处理
  //
  //       一般情况下，我们在开发中的异常信息，都是记录在控制台上（我们开发环境的一种日志打印方式）
  //       我们会根据异常信息提取出有用的线索，来调试bug
  //
  //       但是在真实生产环境中（项目上线），对于服务器或者是系统相关的问题
  //       在控制台上其实也会提供相应的异常或者错误信息的输出
  //       但是这种错误输出方式（输出的时间，位置，格式，，，）都是服务器系统默认的
  //
  //       我们可以通过日志技术，选择将异常以日志打印的方式，进行输出查看
  //       输出的时间，位置（控制台，文件），格式，完全由我们自己去进行定义
  //    */
  //   Logger logger = LoggerFactory.getLogger(SLF4JTest01.class);
  //   try {
  //     Class.forName("aaa");
  //   } catch (Exception e) {
  //     // 打印栈追踪信息
  //     // e.printStackTrace();
  //     logger.info("XXX类中的XXX方法出现了异常，请及时关注信息");
  //     // e是引用类型对象，不能跟前面的{}做有效的字符串拼接
  //     // logger.info("具体错误：{}", e);
  //     // 不用加{}，直接后面加上异常对象e即可
  //     logger.info("具体错误：", e);
  //   }
  // }
  //
  // @Test
  // public void test04() {
  //   /*
  //     SLF4]日志门面，共有3种情况对日志实现进行绑定
  //     1.在没有绑定任何日志实现的基础之上，日志是不能够绑定实现任何功能的
  //       值得大家注意的是，通过我们刚刚的演示，slf4j-simple是slf4j官方提供的
  //       使用的时候，也是需要导入依赖，自动绑定到slf4j门面上
  //       如果不导入，slf4j核心依赖是不提供任何实现的
  //     2.logback和simple(包括nop)
  //       都是slf4j门面时间线后面提供的日志实现，所以API完全遵循slf4j进行的设计
  //       那么我们只需要导入想要使用的日志实现依赖，即可与slf4j无缝衔接
  //       值得一提的是nop虽然也划分到实现中了，但是他是指不实现日志记录（后续课程）
  //     3.log4j和JUL
  //       都是slf4j门面时间线前面的日志实现，所以API不遵循slf4j进行设计
  //       通过适配桥接的技术，完成与日志门面的衔接
  //
  //     试着将logback日志框架集成进来
  //
  //     测试1：
  //       在原有slf4j-simple日志实现的基础上，又集成了logback
  //       通过测试，日志是打印出来了java.lang.ClassNotFoundException:aaa
  //       通过这一句可以发现SLF4:Actual binding is of type[org.slf4j.impl.simpleLoggerFactory]
  //       虽然集成了logback,但是我们现在使用的仍然是slf4j-simple
  //       事实上只要出现了这个提示
  //       SLF4J:Class path contains multiple SLF4J bindings.
  //       在slf4j环境下，证明同时出现了多个日志实现
  //       如果先导入logback依赖，后导入slf4j-simple依赖
  //       那么默认使用的就是logback依赖
  //       如果有多个日志实现的话，默认使用先导入的实现
  //
  //     测试2：
  //       将slf4j-simple注释掉
  //       只留下logback,那么slf4j门面使用的就是logback日志实现
  //       值得一提的是，这一次没有多余的提示信息
  //       所以在实际应用的时候，我们一般情况下，仅仅只是做一种日志实现的集成就可以了
  //
  //       通过这个集成测试，我们会发现虽然底层的日志实现变了，但是源代码完全没有改变
  //       这就是日志门面给我们带来最大的好处
  //       在底层真实记录日志的时候，不需要应用去做任何的了解
  //       应用只需要去记slf4j的API就可以了
  //    */
  //
  //   Logger logger = LoggerFactory.getLogger(SLF4JTest01.class);
  //   try {
  //     Class.forName("aaa");
  //   } catch (Exception e) {
  //     logger.info("具体错误：", e);
  //   }
  // }
  //
  // @Test
  // public void test05() {
  //   /*
  //
  //     使用slf4j-nop
  //       表示不记录日志
  //       在我们使用slf4j-nop的时候
  //       首先还是需要导入实现依赖
  //       这个实现依赖，根据我们之前所总结出来的日志日志实现种类的第二种
  //       与logback和simple是属于一类的
  //       通过集成依赖的顺序而定
  //       所以如果想要让nop发挥效果，禁止所有日志的打印
  //       那么就必须要将slf4j-nop的依赖放在所日志实现依赖的上方
  //
  //    */
  //   Logger logger = LoggerFactory.getLogger(SLF4JTest01.class);
  //   try {
  //     Class.forName("aaa");
  //   } catch (Exception e) {
  //     logger.info("具体错误：", e);
  //   }
  // }
  //
  // @Test
  // public void test06() {
  //   /*
  //
  //    接下来我们来绑定log4j
  //    由于log4j是在slf4j之前出品的日志框架实现
  //    所以并没有遵循slf4j的API规范
  //    (之前集成的logback,是slf4j之后出品的日志框架实现
  //    logback就是按照slf4j的标准指定的API,所以我们导入依赖就能用)
  //
  //    如果想要使用，需要绑定一个适配器
  //    叫做slf4j-log4j12
  //    再导入log4j的实现
  //
  //    log4j:WARN No appenders could be found for logger (com.clearlove.slf4j.test01.SLF4JTest01).
  //    log4j:WARN Please initialize the log4j system properly.
  //    log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
  //
  //    虽然日志信息没有打印出来，那么根据警告信息可以得出：
  //    使用了log4j日志实现框架
  //    提示appender没有加载，需要在执行日志之前做相应的加载工作（初始化）
  //    我们可以将log4j的配置文件导入使用
  //
  //    值得一提的是，我们虽然底层使用的是log4j做的打印，但是从当前代码使用来看
  //    我们其实使用的仍然是slf4j日志门面，至于日志是log4j打印的（或者是logback打印的）
  //    都是由slf4j进行操作的，我们不用操心
  //   */
  //
  //   Logger logger = LoggerFactory.getLogger(SLF4JTest01.class);
  //   logger.trace("trace信息");
  //   logger.debug("debug信息");
  //   logger.info("info信息");
  //   logger.warn("warn信息");
  //   logger.error("error信息");
  // }
  //
  // @Test
  // public void test07() {
  //
  //   /*
  //       接下来我们来适配JDKl4
  //         与上一个测试log4j导入适配器一样
  //         JUL也是slf4j之前出品的日志实现框架
  //         所以也需要相应的适配器
  //         适配器导入之后，JUL日志实现是不用导入依赖的
  //         因为JUL,是JDK内置的
  //
  //       从测试结果来看，是JUL的日志打印，默认是info级别日志的输出
  //
  //    */
  //   Logger logger = LoggerFactory.getLogger(SLF4JTest01.class);
  //   logger.trace("trace信息");
  //   logger.debug("debug信息");
  //   logger.info("info信息");
  //   logger.warn("warn信息");
  //   logger.error("error信息");
  //
  // }
  //
  // @Test
  // public void test08() {
  //
  //   /*
  //     绑定多个日志实现，会出现警告信息
  //     通过源码来看看其原理（看看slf4j的执行原理）
  //
  //     进入到getLogger
  //     Logger logger = getLogger(clazz.getName());
  //
  //     进入重载的getLogger
  //     ILoggerFactory iLoggerFactory=getILoggerFactory();用来取得Logger工厂实现的方法
  //
  //     进入getILoggerFactory()
  //     看到以双重检查锁的方式去做判断
  //     执行performInitialization();工厂的初始化方法
  //
  //     进入performInitialization()
  //     bind()就是用来绑定具体日志实现的方法
  //
  //     进入bind()
  //     看到Set集合Set<URL>staticLoggerBinderPathSet=null;
  //     因为当前有可能会有N多个日志框架的实现
  //     staticLoggerBinderPathSet findPossibleStaticLoggerBinderPathSet();
  //
  //     findPossibleStaticLoggerBinderPathSet()
  //     看到创建了一个有序不可重复的集合对象
  //     LinkedHashset staticLoggerBinderPathSet = new LinkedHashset();
  //     声明了枚举类的路径，经过if else判断，以获取系统中都有哪些日志实现
  //     看到Enumeration paths;
  //     if (loggerFactoryClassLoader =null){
  //     paths = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
  //     else
  //     paths = loggerFactoryClassLoader.getResources(STATIC_LOGGER_BINDER_PATH);
  //
  //     我们主要观察常量STATIC LOGGER BINDER PATH
  //     通过常量我们会找到类staticLoggerBinder
  //     这个类是以静态的方式绑定Logger实现的类
  //     来自slf4j-JDK14的适配器
  //     进入staticLoggerBinder
  //     看到new JDK14 LoggerFactory();
  //     进入JDK14LoggerFactory类的无参构造方法
  //     java.util.logging.Logger.getLogger("");
  //     使用的就是jul的Logger
  //
  //     接着观察findPossibleStaticLoggerBinderPathSet
  //     看到以下代码，表示如果还有其他的日志实现
  //     while(paths.hasMoreElements()){
  //         URL path = (URL)paths.nextElement();
  //         将路径添加进入
  //         staticLoggerBinderPathSet.add(path);
  //     }
  //
  //     回到bind方法
  //     表示对于绑定多实现的处理
  //     reportMultipleBindingAmbiguity(staticLoggerBinderPathSet);
  //     如果出现多日志实现的情况
  //     则会打
  //     Util.report("Class path contains multiple SLF4J bindings.");
  //
  //     总结：
  //     在真实生产环境中，slf4j只绑定一个日志实现框架就可以了
  //     绑定多个，默认使用导入依赖的第一个，而且会产生没有必要的警告信息
  //
  //    */
  //
  // }

  @Test
  public void test09(){
    /*
      需求：
        假设我们项目一直以来使用的是log4j日志框架
        但是随着技术和需求的更新换代
        log4j已然不能够满足我们系统的需求
        我们现在就需要将系统中的日志实现重构为slf4j+logback的组合
        在不触碰java源代码的情况下，将这个问题给解决掉

        首先将所有关于其他日志实现和门面依赖全部去除
        仅仅只留下log4j的依赖
        测试的过程中，只能使用log4j相关的组件

        此时需要将日志替换为slf4j+logback
        我们既然不用log4j了，就将log4j去除
        将slf4j日志门面和logback的日志实现依赖加入进来
        这样做，没有了log4j环境的支持，编译报错

        这个时候就需要使用桥接器来做这个需求了
        桥接器解决的是项目中日志的重构问题，当前系统中存在之前的日志API,可以通过桥接转换到slf4j的实现

        桥接器的使用步骤：
        1.去除之前旧的日志框架依赖
        2.添加slf4j提供的桥接组件
          log4j相关的桥接器
          桥接器加入后，代码编译就不报错了
          测试：
            日志信息输出
            输出格式为logback
            证明了现在使用的确实是slf4j门面+logback实现

            在重构之后，就会为我们造成这样一种假象
            使用的明明是log4j包下的日志组件资源
            但是真正日志的实现，却是使用slf4j门面+logback实现
            这就是桥接器给我们带来的效果

          注意：
            在桥接器加入之后，适配器就没有必要加入了
            桥接器和适配器不能同时导入依赖
            桥接器如果配置在适配器的上方，则运行报错，不能同时出现
            桥接器如果配置在适配器的下方，则不会执行桥接器，没有任何的意义
     */

    Logger logger = LogManager.getLogger(SLF4JTest01.class);
    logger.info("info信息");

  }

  @Test
  public void test10(){
    /*
      在配置了桥接器之后，底层就是使用slf4j实现的日志
      分析其中原理

      通过getLogger
      进入Log4jLoggerFactory
      Logger newInstance=new Logger(name);新建logger对象

      进入构造方法
      protected Logger(String name){
        super(name);
      }

      点击进入父类的构造方法
      Category(string name){
        this.name = name;
        this.slf4jLogger = LoggerFactory.getLogger(name);
        if (this.slf4jLogger instanceof LocationAwareLogger){
          this.locationAwareLogger = (LocationAwareLogger)this.slf4jLogger;
        }
      }

      在这个Category构造方法中，核心代码
        this.slf4jLogger = LoggerFactory.getLogger(name);

      LoggerFactory来自于org.slf4j
     */
    Logger logger = LogManager.getLogger(SLF4JTest01.class);
  }

}
