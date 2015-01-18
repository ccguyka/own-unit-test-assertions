package org.ccguyka.own_unit_test_assertions.assertj;

import static org.ccguyka.own_unit_test_assertions.Gender.MALE;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.SoftAssertions;
import org.ccguyka.own_unit_test_assertions.User;

public class UserAssertJSoftAssertion extends
		AbstractAssert<UserAssertJSoftAssertion, User> {

	private SoftAssertions softAssert;

	protected UserAssertJSoftAssertion(User actual) {
		super(actual, UserAssertJSoftAssertion.class);

		softAssert = new SoftAssertions();
	}

	public static UserAssertJSoftAssertion assertThat(User actual) {
		return new UserAssertJSoftAssertion(actual);
	}

	public UserAssertJSoftAssertion isMale() {
		isNotNull();

		softAssert.assertThat(actual.getGender()).isEqualTo(MALE);

		return this;
	}

	public UserAssertJSoftAssertion hasName(String name) {
		isNotNull();

		softAssert.assertThat(actual.getName()).isEqualTo(name);

		return this;
	}

	public void assertAll() {
		softAssert.assertAll();
	}
}
