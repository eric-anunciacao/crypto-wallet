package global.genesis.cross;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

import global.genesis.service.response.CoinCapAssetDataResponse;

class CryptoWalletHttpClientTest {

	@Test
	void shouldCallAssetsUrl() throws URISyntaxException, IOException, InterruptedException {
		var response = new CryptoWalletHttpClient().call("https://api.coincap.io/v2/assets?search=BTC",
				CoinCapAssetDataResponse.class);
		assertNotNull(response);
		assertNotNull(response.getData());
	}

}
