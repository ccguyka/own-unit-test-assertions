package org.ccguyka.own_unit_test_assertions.assertj;

import static org.ccguyka.own_unit_test_assertions.Gender.MALE;
import static org.hamcrest.CoreMatchers.equalTo;

import org.assertj.core.api.AbstractAssert;
import org.ccguyka.own_unit_test_assertions.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;


public class UserAssertJAssertion extends
		AbstractAssert<UserAssertJAssertion, User> {

	protected UserAssertJAssertion(User actual) {
		super(actual, UserAssertJAssertion.class);
	}

	public static UserAssertJAssertion assertThat(User actual) {
		return new UserAssertJAssertion(actual);
	}

	public UserAssertJAssertion isMale() {
		isNotNull();

		Assert.assertThat(actual.getGender(), CoreMatchers.is(equalTo(MALE)));

		return this;
	}

	public UserAssertJAssertion hasName(String name) {
		isNotNull();

		Assert.assertThat(actual.getName(), CoreMatchers.is(equalTo(name)));

		return this;
	}

}
