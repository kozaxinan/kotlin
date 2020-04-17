// TARGET_BACKEND: JVM
// FILE: JavaInterface.java

public interface JavaInterface<T> {
    default T foo(T param) {
        return param;
    }
}

// FILE: JavaDerived.java

public interface JavaDerived extends JavaInterface<Derived> {

}

// FILE: Kotlin.kt
// JVM_TARGET: 1.8

class Derived(val value: String)

class Test : JavaDerived {
    override fun foo(a: Derived?): Derived {
        return super.foo(a)
    }
}

fun box(): String {
    return Test().foo(Derived("OK")).value
}