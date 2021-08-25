package com.example.lib.d03classobject

// 1 Kotlin中的可见性修饰符
// 在 Kotlin 中有这四个可见性修饰符：private 、protected 、internal 和 public
// 如果你不指定任何可见性修饰符，默认为 public ，这意味着你的声明将随处可见
// 如果你覆盖⼀个 protected 成员并且没有显式指定其可见性，该成员还会是 protected 可见性

// 2 Java 可见性修饰符 VS Kotlin
/**
修饰符          Kotlin                              Java
public    Kotlin默认修饰符 全局可见                    |完全相等
protected 类及其子类可见                              |类及其子类 包内可见
private   修饰的类 则只有类内可见 修饰其他东西 同文件可见   |类内可见
internal  模块内可见 类似Java的default                |无
default   无                                        |Java默认修饰符
 */


class D0305Access
