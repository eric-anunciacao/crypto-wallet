package global.genesis.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import global.genesis.cross.CryptoWalletHttpClient;
import global.genesis.service.CoinCapClient;
import global.genesis.service.response.CoinCapAssetDataResponse;
import global.genesis.service.response.CoinCapHistoryDataResponse;
import global.genesis.utils.TestUtils;

@ExtendWith(MockitoExtension.class)
class CalculateFinancialValueOfWalletUseCaseTest {

	private static final String URL_GET_ASSETS = "https://api.coincap.io/v2/assets?search=%s";
	private static final String URL_GET_HISTORY = "https://api.coincap.io/v2/assets/%s/history?interval=d1&start=1617753600000&end=1617753601000";

	@Mock
	private CryptoWalletHttpClient cryptoWalletHttpClient;

	@Test
	void shouldCalculateFinancialValueOfWallet() throws URISyntaxException, IOException, InterruptedException {
		mockObjects();

		var coinCapClient = new CoinCapClient(cryptoWalletHttpClient);
		var performance = new CalculateFinancialValueOfWalletUseCase(coinCapClient).execute();

		assertEquals("total=16984.62, bestAsset=BTC, bestPerformance=1.51, worstAsset=ETH, worstPerformance=1.01",
				performance.toString());
	}

	private void mockObjects() throws URISyntaxException, IOException, InterruptedException {
		var urlGetBtcAssets = String.format(URL_GET_ASSETS, "BTC");
		BDDMockito.given(cryptoWalletHttpClient.call(urlGetBtcAssets, CoinCapAssetDataResponse.class))
				.willReturn(TestUtils.getBtcCoinCapAssetDataresponse());

		var urlGetBtcHistory = String.format(URL_GET_HISTORY, "bitcoin");
		BDDMockito.given(cryptoWalletHttpClient.call(urlGetBtcHistory, CoinCapHistoryDataResponse.class))
				.willReturn(TestUtils.getBtcCoinCapHistoryDataResponse());

		var urlGetEthAssets = String.format(URL_GET_ASSETS, "ETH");
		BDDMockito.given(cryptoWalletHttpClient.call(urlGetEthAssets, CoinCapAssetDataResponse.class))
				.willReturn(TestUtils.getEthCoinCapAssetDataresponse());

		var urlGetEthHistory = String.format(URL_GET_HISTORY, "ethereum");
		BDDMockito.given(cryptoWalletHttpClient.call(urlGetEthHistory, CoinCapHistoryDataResponse.class))
				.willReturn(TestUtils.getEthCoinCapHistoryDataResponse());
	}

}
