# spring-demo: spring-boot + Redis + MyBatis + Elasticsearch + RocketMQ

# 项目结构
<img src="docs/project.png" height="400px;"/>

# 项目组件
* [MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html)
* [Redis-jetcache](https://github.com/alibaba/jetcache)
* [RocketMQ](https://github.com/apache/rocketmq-spring/blob/master/README_zh_CN.md)
* [ElasticSearch](https://spring.io/projects/spring-data-elasticsearch)
* [HuTool](https://hutool.cn/docs/#/?id=%e6%96%87%e6%a1%a3)

# 更新 2019-09-04
* Mybatis 升级为 [Mybatis plus](https://mp.baomidou.com/)。并完善多数据源混合使用，动态切换数据源demo。
减少xml配置，自动填充createTime、updateTime字段。
* 增加 Spring RestTemplate + HttpClient组合，时间连接池、超时时间配置。
* logback 多环境配置。

# 更新 2019-09-05
* 集成 Mybatis plus 分页插件，增加分页操作Demo。
* 修复 "Mybatis plus mapper-locations 配置错误，找不到 xml" BUG。
* 增加事务操作Demo。

# 通用响应
<img src="docs/result.png" height="400px;"/>