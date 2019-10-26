Java多线程

# About Lock

## ReetrantLock

Let us begin at ReentrantLock. Java5 implemented ReentrantLock.

```java
myLock.lock(); // a ReentrantLock object
try{
	critical section
}finally{
  // make sure the lock is unlocked envn if an exception is thrown
	myLock.unLock();
}
```

### Condition Object

```java
myLock.lock();
try{
	while(!(OK to proceed)){
    condition.await();
  }
	...
	// whenever the state of object changes in a way that might be advantageout to wating threads.
	condition.sginalAll();
}finally{
  myLock.unlock();
}
```

## Synchronized Keyword

Every object in java has an intrinsic (built-in) lock. If a method is declared with the synchronized keyword, the object's lock protects the entire method.

Basically, Synchronized is the equivalent of Lock/Condition.

```java
public synchronized void method(){
	method body
}
// equals
public void method(){
	this.intrinsicLock.lock();
	try{
		method body
	}finally{
		this.intrinsicLock.unlock();
	}
}
```

The intrinsic object lock has a single associated condition.

Calling `wait` and `notifyAll` is the equivalent of

```
intrinsicConditon.await();
intrinsicCondition.singalAll()
```

Synchronized keyword makes code more concise .

Recommendation

1. Try to use neither `Lock/Conditon` nor `Synchronized` keyword. Try to use `java.until.concurrent package` that do all the locking for you.

2. If synchronized works for your situation, use it.
3. Use `Lock/Condtion` if you need the additional power these construts to give you.

## Atomicity

what is atomicity access?

In programming, an *atomic* action is one that effectively happens all at once. An atomic action cannot stop in the middle: it either happens completely, or it doesn't happen at all. No side effects of an atomic action are visible until the action is complete.

We have already seen that an increment expression, such as `c++`, does not describe an atomic action. Even very simple expressions can define complex actions that can decompose into other actions. However, there are actions you can specify that are atomic:

- Reads and writes are atomic for reference variables and for most primitive variables (all types except `long` and `double`).
- Reads and writes are atomic for *all* variables declared `volatile` (*including* `long` and `double` variables).

[javase-atomic](https://docs.oracle.com/javase/tutorial/essential/concurrency/atomic.html)

