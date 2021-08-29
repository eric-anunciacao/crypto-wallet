package global.genesis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ApplicationTest {

	@Test
	void shouldRunApplication() {
		try {
			Application.main(null);
		} catch (Exception e) {
			fail("Test fails");
		}
	}

}
