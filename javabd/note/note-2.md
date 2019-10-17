# 关于 ==, equals(), hashCode()

== 比较基本类型，和引用类型的地址

equals() 比较两个对象是否相等。需要重写equals方法。

hashCode() 也需要重写。集合类例如hashmap需要用到。getKey的时候首先检查key的hash值。然后检查equals是否相等。若相等返回对象，不相等返回空。

equals相等 hashCode一定要相等。hashCode相等，equals不一定相等。

# Java StringTokenizer

已经过时了，用`split`。
[java7_tokenizer](https://docs.oracle.com/javase/7/docs/api/java/util/StringTokenizer.html)

# String, StringBuffer 和 StringBuilder有什么区别？

StringBuffer线程安全

StringBuilder线程不安全

效率方面
StringBuilder > StringBuffer > String

# 4.6 异常处理

## finally 块中的代码什么时候被执行？

无论异常是否被处理，finally块中的代码一定要执行。

