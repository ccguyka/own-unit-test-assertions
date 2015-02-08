package org.ccguyka.own_unit_test_assertions;

import static org.ccguyka.own_unit_test_assertions.Gender.FEMALE;
import static org.ccguyka.own_unit_test_assertions.Gender.MALE;
import static org.ccguyka.own_unit_test_assertions.User.UserBuilder.aUser;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.ccguyka.own_unit_test_assertions.assertj.UserAssertJAssertion;
import org.ccguyka.own_unit_test_assertions.assertj.UserAssertJSoftAssertion;
import org.ccguyka.own_unit_test_assertions.festassert.UserFestAssertion;
import org.junit.Test;


public class UserAssertionTest {

	private static final String NAME = "John Doe";
	private static final String FAILING_NAME = "Jane Roe";

	@Test
	public void goodJunitTest() {
		User user = goodUser();

		assertEquals(MALE, user.getGender());
		assertEquals(NAME, user.getName());
	}

	@Test
	public void failingJunitTest() {
		User user = failingUser();

		assertEquals(MALE, user.getGender());
		assertEquals(NAME, user.getName());
	}

	@Test
	public void goodHamcrestTest() {
		User user = goodUser();

		assertThat(user.getGender(), is(equalTo(MALE)));
		assertThat(user.getName(), is(equalTo(NAME)));
	}

	@Test
	public void failingHamcrestTest() {
		User user = failingUser();

		assertThat(user.getGender(), is(equalTo(MALE)));
		assertThat(user.getName(), is(equalTo(NAME)));
	}

	@Test
	public void failingJunitSoftAssertionsTest() {
		User user = failingUser();

		SoftAssertions soft = new SoftAssertions();
		soft.assertThat(user.getGender()).isEqualTo(MALE);
		soft.assertThat(user.getName()).isEqualTo(NAME);
		soft.assertAll();
	}

	@Test
	public void goodAssertJTest() {
		User user = goodUser();

		UserAssertJAssertion.assertThat(user).isMale().hasName(NAME);
	}

	@Test
	public void failingAssertJTest() {
		User user = failingUser();

		UserAssertJAssertion.assertThat(user).isMale().hasName(NAME);
	}

	@Test
	public void failingAssertJSoftAssertTest() {
		User user = failingUser();

		UserAssertJSoftAssertion.assertThat(user).isMale().hasName(NAME).assertAll();
	}

	@Test
	public void goodFestAssertionTest() {
		User user = goodUser();

		UserFestAssertion.assertThat(user).isMale().hasName(NAME);
	}

	@Test
	public void failingFestAssertionTest() {
		User user = failingUser();

		UserFestAssertion.assertThat(user).isMale().hasName(NAME);
	}

	private User goodUser() {
		return aUser()
					.withName(NAME)
					.withGender(MALE)
					.build();
	}

	private User failingUser() {
		return aUser()
				.withName(FAILING_NAME)
				.withGender(FEMALE)
				.build();
	}
}
