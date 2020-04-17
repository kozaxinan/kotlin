// !JVM_DEFAULT_MODE: all-compatibility
// TARGET_BACKEND: JVM
// JVM_TARGET: 1.8
// WITH_RUNTIME

class Derived(val value: String)

interface Foo<T> {
    fun foo(a: T): T {
        return a
    }
}

interface Foo2 : Foo<Derived> {
    override fun foo(p : Derived): Derived = Derived("OK")
}

interface Foo3 : Foo<Derived>, Foo2


open class Test : Foo3 {
    override fun foo(p: Derived): Derived {
        return Derived("fail from Test.foo")
    }

    fun test(): Derived {
        return super.foo(Derived("fail from test"))
    }
}

fun box(): String {
    return Test().test().value
}