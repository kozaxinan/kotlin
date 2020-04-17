// !JVM_DEFAULT_MODE: all-compatibility
// TARGET_BACKEND: JVM
// JVM_TARGET: 1.8
// WITH_RUNTIME

class Derived(val value: String)

var result: Any = Derived("fail not-inited")

interface Foo<T : Any> {
    var foo: T
        get() = result as T
        set(value) {
            result = value
        }
}

interface Foo2 : Foo<Derived> {
    override var foo: Derived
        get() = result as Derived
        set(value) {
            result = Derived("OK")
        }
}

interface Foo3 : Foo<Derived>, Foo2


open class Test : Foo3 {
    override var foo: Derived
        get() = Derived("fail from Test.get")
        set(value) {
            result = Derived("fail from Test.set")
        }


    fun test(): Derived {
        super.foo = Derived("fail from test")
        return super.foo
    }
}

fun box(): String {
    return Test().test().value
}
