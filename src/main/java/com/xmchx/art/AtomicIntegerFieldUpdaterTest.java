package com.xmchx.art;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class AtomicIntegerFieldUpdaterTest {

	public static void main(String[] args) {
		AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
		User kevin_sun = new User("Kevin Sun", 21);
		System.out.println(a.getAndIncrement(kevin_sun));
		System.out.println(a.get(kevin_sun));
	}

	static class User {
		private String name;
		public volatile int age;

		public User(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}
	}
}
