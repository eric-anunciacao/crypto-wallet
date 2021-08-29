package global.genesis.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import global.genesis.cross.CryptoWalletHttpClient;

@ExtendWith(MockitoExtension.class)
class CoinCapClientTest {

	@InjectMocks
	private CoinCapClient client;

	@Mock
	private CryptoWalletHttpClient cryptoWalletHttpClient;

	@Test
	void shouldThrownExceptionWhenGetAssets() throws URISyntaxException, IOException, InterruptedException {
		mockException();
		var response = client.getAsset(null);
		assertNotNull(response);
		assertNull(response.getId());
	}

	@Test
	void shouldThrownExceptionWhenGetAssetHistory() throws URISyntaxException, IOException, InterruptedException {
		mockException();
		var response = client.getHistory(null, null, null, null);
		assertNotNull(response);
		assertTrue(CollectionUtils.isEmpty(response));
	}

	private void mockException() throws URISyntaxException, IOException, InterruptedException {
		BDDMockito.given(cryptoWalletHttpClient.call(Mockito.anyString(), Mockito.any()))
				.willThrow(new URISyntaxException("", ""));
	}

}
