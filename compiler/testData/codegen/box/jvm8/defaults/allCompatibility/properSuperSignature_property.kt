// !JVM_DEFAULT_MODE: all-compatibility
// TARGET_BACKEND: JVM
// JVM_TARGET: 1.8
// WITH_RUNTIME

class Derived(val value: String)

var result: Any = Derived("not inited")

interface Foo<T: Any> {
    var foo: T
        get() = result as T
        set(value) {
            result = value
        }
}

interface FooChild : Foo<Derived>

class Test : FooChild {
    override var foo: Derived
        get() = super.foo
        set(value) {
            super.foo = value
        }
}

fun box(): String {
    val test = Test()
    test.foo = Derived("OK")
    return test.foo.value
}