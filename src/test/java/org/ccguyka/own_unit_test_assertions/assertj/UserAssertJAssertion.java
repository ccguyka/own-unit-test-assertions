package org.ccguyka.own_unit_test_assertions.assertj;

import static org.ccguyka.own_unit_test_assertions.Gender.MALE;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.ccguyka.own_unit_test_assertions.User;


public class UserAssertJAssertion extends AbstractAssert<UserAssertJAssertion, User> {

	protected UserAssertJAssertion(User actual) {
		super(actual, UserAssertJAssertion.class);
	}

	public static UserAssertJAssertion assertThat(User actual) {
		return new UserAssertJAssertion(actual);
	}

	public UserAssertJAssertion isMale() {
		isNotNull();

		Assertions.assertThat(actual.getGender()).isEqualTo(MALE);

		return this;
	}

	public UserAssertJAssertion hasName(String name) {
		isNotNull();

		Assertions.assertThat(actual.getName()).isEqualTo(name);

		return this;
	}

}
