# Efficient Maps, Set, Queues

Concurrent hash map organizes buckets as trees, guaranteeing O(lgn) performance.

## Automic update for map entries

Use atomic Long, Integer, Boolean

Map has merge, compute, computeIfAbsent, computeIfPresent

## Bulkoperation on Concurrent Hashmaps

search, reduce, forEach

Eg:

searchValues(long threshold ...)

Threshold defines parallelism number, if map contains more elements than threshold, then bulk operation in different thread.

## CopyOnWriteArrays

We can use it if there are threads iterate over the collection greatly outnumber the threads that mutate it.

## Older Thread-Safe Collections

Do not use 

```java
List<E> list = Collections.synchronzedList<new ArrayList<E>());

Map<K,V> map = Collections.synchronzedMap<new HashMap<K,V>());
```

## Parallel Sort

Arrays.parallelSort use forkjoin mechnism.