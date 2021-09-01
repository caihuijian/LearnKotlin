package com.example.lib.d03classobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class D0309GenericJava {

    static void copyAll2(Collection<? extends Object> collection) {
        // Collection<String> 是 Collection<? extends Object>的子类
    }

    static void copyAll3(Collection<Object> collection) {
        // Collection<String> 不是 Collection<Object>的子类
    }

    static void copyAll4(Collection<? super String> collection) {
        // Collection<Object> 是 Collection<? super String>的子类
    }

    static void copyAll(Collection<Object> to, Collection<String> from) {
        to.addAll(from);
        // addAll的实际定义 ：boolean addAll(Collection<? extends E> var1);
        // 而不是想象中的 boolean addAll(Collection<E> items);
        // 想象中的定义对于这种简单声明的 addAll 将不能编译：
        // 因为Collection<String> 不是 Collection<Object> 的子类型
        // 但实际上可以编译通过 是因为Collection<String> 是 Collection<? extends Object>的子类
    }

    public static void main(String[] args) {
        // 几种基本类型的子类父类关系
        List<String> strings = new ArrayList<>();
        List<Object> obj = new ArrayList<>();
        // obj = strings; List<Object> 不是 List<String> 的父类
        // strings = obj; List<String>不是 List<Object> 的子类
        copyAll(obj, strings);
        copyAll2(strings);
        //copyAll3(strings);// 编译报错
        copyAll4(obj);


        // Java协变的写法 父类对象指向子类引用
        // 协变: 父类可以使用的地方子类也可以使用
        // 父类引用指向子类对象
        Producer<? extends Food> foodStore = new FoodStore();
        foodStore.produce();

        Producer<? extends Food> fastFoodStore = new FastFoodStore();
        fastFoodStore.produce();
        Producer<? extends FastFood> fastFoodStore2 = new FastFoodStore();
        fastFoodStore2.produce();

        Producer<? extends Food> burgerStore1 = new BurgerStore();
        burgerStore1.produce();
        Producer<? extends FastFood> burgerStore2 = new BurgerStore();
        burgerStore2.produce();
        Producer<? extends Burger> burgerStore3 = new BurgerStore();
        burgerStore3.produce();

        // Java逆变的写法
        // 逆变: 子类可以使用的地方父类也可以使用
        // 子类引用指向父类对象
        Consumer<? super Food> foodConsumer = new People();
        foodConsumer.consume(new Food());
        foodConsumer.consume(new FastFood());
        foodConsumer.consume(new Burger());
        Consumer<? super FastFood> foodConsumer2 = new People();
        foodConsumer2.consume(new FastFood());
        foodConsumer2.consume(new Burger());
        Consumer<? super Burger> foodConsumer3 = new People();
        foodConsumer3.consume(new Burger());

        Consumer<? super FastFood> fastFoodConsumer = new ModernPeople();
        fastFoodConsumer.consume(new FastFood());
        fastFoodConsumer.consume(new Burger());
        Consumer<? super Burger> fastFoodConsumer2 = new ModernPeople();
        fastFoodConsumer2.consume(new Burger());

        Consumer<? super Burger> burgerConsumer = new American();
        burgerConsumer.consume(new Burger());
    }

    // 协变 这里用在返回值上
    interface Producer<T> {
        T produce();
    }

    // 逆变 这里用在输入参数上
    interface Consumer<T> {
        void consume(T food);
    }

    public static class Food {
    }

    public static class FastFood extends Food {
    }

    public static class Burger extends FastFood {
    }

    // 生产者 结合协变 out使用 协变 用在参数
    static class FoodStore implements Producer<Food> {
        @Override
        public Food produce() {
            System.out.println("produce food");
            return new Food();
        }
    }

    static class FastFoodStore implements Producer<FastFood> {
        @Override
        public FastFood produce() {
            System.out.println("produce fast food");
            return new FastFood();
        }
    }

    static class BurgerStore implements Producer<Burger> {
        @Override
        public Burger produce() {
            System.out.println("produce burger");
            return new Burger();
        }
    }

    static class People implements Consumer<Food> {
        @Override
        public void consume(Food food) {
            System.out.println("People eat Food");
        }
    }

    static class ModernPeople implements Consumer<FastFood> {
        @Override
        public void consume(FastFood food) {
            System.out.println("ModernPeople eat FastFood");
        }
    }

    static class American implements Consumer<Burger> {
        @Override
        public void consume(Burger food) {
            System.out.println("American eat Burger");
        }
    }
}
