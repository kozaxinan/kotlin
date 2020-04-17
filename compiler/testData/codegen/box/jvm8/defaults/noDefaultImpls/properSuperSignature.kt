// !JVM_DEFAULT_MODE: all
// TARGET_BACKEND: JVM
// JVM_TARGET: 1.8
// WITH_RUNTIME

class Derived(val value: String)

interface Foo<T> {
    fun foo(a: T): T {
        return a
    }
}

interface FooChild : Foo<Derived>

class Test : FooChild {
    override fun foo(a: Derived): Derived {
        return super.foo(a)
    }
}

fun box(): String {
    return Test().foo(Derived("OK")).value
}