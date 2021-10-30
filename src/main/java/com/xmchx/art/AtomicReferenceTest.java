package com.xmchx.art;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class AtomicReferenceTest {
	static AtomicReference<User> ar = new AtomicReference<>();

	public static void main(String[] args) {
		User kevin_sun = new User("Kevin Sun", 21);
		ar.set(kevin_sun);
		User huayang_sun = new User("Huayang Sun", 21);
		ar.compareAndSet(kevin_sun, huayang_sun);
		System.out.println(ar.get().name);
		System.out.println(ar.get().age);
	}

	static class User {
		private String name;
		private int age;

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
