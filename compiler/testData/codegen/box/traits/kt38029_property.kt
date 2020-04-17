open class Base

class Derived(val value: String) : Base()

interface Foo<T> {
    val foo: T
}

interface Foo2 : Foo<Derived> {
    override val foo : Derived
        get() = Derived("OK")
}

abstract class A1<T> : Foo<T>

open class A2 : A1<Derived>(), Foo2

open class A3 : A2() {
    fun test(): Derived = super.foo
}

class A4 : A3() {
    override val foo: Derived
        get() = Derived("fail")
}

fun box(): String {
    return A4().test().value
}