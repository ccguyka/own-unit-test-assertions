package org.ccguyka.own_unit_test_assertions.festassert;

import static org.ccguyka.own_unit_test_assertions.Gender.MALE;

import org.ccguyka.own_unit_test_assertions.User;
import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;

public class UserFestAssertion extends AbstractAssert<UserFestAssertion, User> {

	protected UserFestAssertion(User actual) {
		super(actual, UserFestAssertion.class);
	}

	public static UserFestAssertion assertThat(User actual) {
		return new UserFestAssertion(actual);
	}

	public UserFestAssertion isMale() {
		isNotNull();

		Assertions.assertThat(actual.getGender()).isEqualTo(MALE);

		return this;
	}

	public UserFestAssertion hasName(String name) {
		isNotNull();

		Assertions.assertThat(actual.getName()).isEqualTo(name);

		return this;
	}
}
