package org.ccguyka.own_unit_test_assertions;


public class User {

	private final String name;
	private final Gender gender;

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	private User(UserBuilder builder) {
	  this.name = builder.name;
	  this.gender = builder.gender;
	}

	public static class UserBuilder {

		private String name;
		private Gender gender;

		public static UserBuilder aUser() {
			return new UserBuilder();
		}

		public UserBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public UserBuilder withGender(Gender gender) {
			this.gender = gender;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
}
