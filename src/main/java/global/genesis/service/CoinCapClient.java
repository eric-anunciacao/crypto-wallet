package global.genesis.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections4.CollectionUtils;

import global.genesis.cross.CryptoWalletHttpClient;
import global.genesis.service.response.CoinCapAssetDataResponse;
import global.genesis.service.response.CoinCapAssetResponse;
import global.genesis.service.response.CoinCapHistoryDataResponse;
import global.genesis.service.response.CoinCapHistoryResponse;

public class CoinCapClient {

	private static final Logger LOGGER = Logger.getLogger(CoinCapClient.class.getName());
	private static final String ERROR_MESSAGE = "Error calling Coincap API";
	private static final String URL_GET_HISTORY = "https://api.coincap.io/v2/assets/%s/history?interval=%s&start=%s&end=%s";
	private static final String URL_GET_ASSETS = "https://api.coincap.io/v2/assets?search=%s";

	private final CryptoWalletHttpClient cryptoWalletHttpClient;

	public CoinCapClient(CryptoWalletHttpClient cryptoWalletHttpClient) {
		this.cryptoWalletHttpClient = cryptoWalletHttpClient;
	}

	public CoinCapAssetResponse getAsset(String symbol) {
		try {
			var url = String.format(URL_GET_ASSETS, symbol);
			var response = cryptoWalletHttpClient.call(url, CoinCapAssetDataResponse.class);

			if (CollectionUtils.isNotEmpty(response.getData())) {
				return response.getData().stream().filter(asset -> symbol.equalsIgnoreCase(asset.getSymbol()))
						.findFirst().orElseThrow(NoSuchElementException::new);
			}
		} catch (URISyntaxException | IOException | InterruptedException e) {
			LOGGER.log(Level.SEVERE, ERROR_MESSAGE, e);
			Thread.currentThread().interrupt();
		}
		return new CoinCapAssetResponse();
	}

	public List<CoinCapHistoryResponse> getHistory(String symbol, String interval, Long start, Long end) {
		try {
			var url = String.format(URL_GET_HISTORY, symbol, interval, start, end);
			var response = cryptoWalletHttpClient.call(url, CoinCapHistoryDataResponse.class);

			if (CollectionUtils.isNotEmpty(response.getData())) {
				return response.getData();
			}
		} catch (URISyntaxException | IOException | InterruptedException e) {
			LOGGER.log(Level.SEVERE, ERROR_MESSAGE, e);
			Thread.currentThread().interrupt();
		}
		return Collections.emptyList();
	}

}
