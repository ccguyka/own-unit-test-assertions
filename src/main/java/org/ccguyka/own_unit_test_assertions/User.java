package org.ccguyka.own_unit_test_assertions;

public class User {

	private final String name;
	private final Gender gender;

	public User(String name, Gender gender) {
		this.name = name;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}
}
