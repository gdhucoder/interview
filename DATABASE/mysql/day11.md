# 物化视图（Materialized View）

本文主要介绍什么是物化视图。

## 普通视图

我们都知道什么是视图。视图是根据基表查询结果建立一个虚拟表，有隐藏查询复杂性，保证数据安全等优点。但有时由于查询过于复杂，以及数据量等原因，使用视图查询会有效率的瓶颈出现。

这时如何进一步优化查询效率呢？

## 物化视图

物化视图是一个选择。

在计算机科学中，物化视图是一个数据库对象，它包含查询的结果。

例如它可以是远程数据的一份本地拷贝，也可以是表或者关联查询结果子集的部分行或者列，也可是聚合函数的查询结果。

创建物化视图的过程叫做物化（Materialzation），这是缓存查询结果的一种形式。，类似于缓存或者记忆化，或者提前计算。

在数据库中使用物化视图，通常是出于效率原因，是一种优化。

在关系型数据库中普通的视图是一种虚拟（virtual）表，而物化视图是将视图的查询结果缓存（cached）到了具体（concrete/materialized）表中。这种方式也是有代价的，因为物化视图的数据可能会过期。

物化视图常用于数据仓库（Data Warehose），在数仓中查询基表可能很耗时。

在物化视图中，可以在任意列上建立索引。相反，在普通的视图中是无法做到的，只能依赖于基表。

## 支持物化视图的数据库

支持物化视图的数据库：

- Oracle

```sql
 CREATE MATERIALIZED VIEW MV_MY_VIEW
REFRESH FAST START WITH SYSDATE
   NEXT SYSDATE + 1
     AS SELECT * FROM <table_name>;
```

- PostgreSQL
- SQL Server
- 流处理框架
 
 Kafka, Spark都支持流式数据的物化视图

 - MySQL
 并不支持物化视图，但实现物化视图可以基于存储过程和触发器或者[Flexviews](https://github.com/greenlion/swanhart-tools/tree/master/flexviews)

 ## 总结

 物化视图是在查询效率上以**空间换时间**思想。使用时要根据业务应用场景考虑到数据时效性，设置合理的更新频率，在时效性和查询效率达到平衡。