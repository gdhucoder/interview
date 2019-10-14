关于 ==, equals(), hashCode()

== 比较基本类型，和引用类型的地址

equals() 比较两个对象是否相等。需要重写equals方法。

hashCode() 也需要重写。集合类例如hashmap需要用到。getKey的时候首先检查key的hash值。然后检查equals是否相等。若相等返回对象，不相等返回空。

equals相等 hashCode一定要相等。hashCode相等，equals不一定相等。