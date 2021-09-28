package com.example.lib.d05others;

// 配套D0509JavaCallKotlin
public class CallKotlin {
    public static void main(String[] args) {
        Test test = new Test();
        // 1 Kotlin属性 set get方法命名的变化
        test.setStudent("AAA");
        System.out.println(test.isStudent());
        System.out.println("--------- 1 end ----------");

        // 2 调用Kotlin成员方法
        // 注意 调用成员方法时使用D0509JavaCallKotlin
        D0509JavaCallKotlin aa = new D0509JavaCallKotlin();
        // D0509JavaCallKotlinKt bb = new D0509JavaCallKotlinKt();// 运行时报错 cannot find symbol
        // 我们无法通过new关键字创建由Kotlin编译器生成的以Kt结尾的类的实例
        // 因为在生成的字节码中 没有为以Kt结尾的类生成任何构造方法
        // 反而是对应的没有以Kt结尾的类 是有构造方法的
        aa.nonStaticMethod();
        System.out.println("--------- 2 end ----------");

        // 3 顶层空间的属性和方法属于静态方法
        // 注意 调用时使用D0509JavaCallKotlinKt 而不是D0509JavaCallKotlin
        D0509JavaCallKotlinKt.test1();
        D0509JavaCallKotlinKt.setStr("set str");
        System.out.println(D0509JavaCallKotlinKt.getStr());
        System.out.println("--------- 3 end ----------");
        // 4 注意对比带Kt和不带Kt的类的使用
        // 即 D0509JavaCallKotlinKt 与 D0509JavaCallKotlin
    }
}
