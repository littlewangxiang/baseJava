# baseJava
该程序是以面相对象的方式计算合租人应当付的钱。
### 程序的业务如下
1.三个租户共同承担水电费，如果有租户在期间外出超过24，当天费用不计算。
2.3号租户默认不实用燃气做饭，所以只用支付燃气费的一半，剩余部分由另两户承担。
2.1 燃气费均分为两份，第一份由1号和2号均分 第二份由三户均分

### 程序启动
1.使用jdk自带的工具 javac 编译几个.java文件 
2.使用 java PersonPay 命令运行即可
3.程序参数 waterPrice electPrice firePrice user1Date user2Date user3Date
