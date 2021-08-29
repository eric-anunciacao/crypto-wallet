package global.genesis.cross;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import global.genesis.infra.exception.WalletException;

class CSVUtilsTest {

	@Test
	void shouldThrowsWalletException() {
		var exception = assertThrows(WalletException.class, () -> CSVUtils.load("another/folder", Object.class));

		assertNotNull(exception);
		assertEquals("Wallet file not found!", exception.getMessage());
	}

}
