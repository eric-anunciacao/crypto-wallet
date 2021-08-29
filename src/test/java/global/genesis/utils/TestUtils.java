package global.genesis.utils;

import java.util.Arrays;
import java.util.List;

import global.genesis.service.response.CoinCapAssetDataResponse;
import global.genesis.service.response.CoinCapAssetResponse;
import global.genesis.service.response.CoinCapHistoryDataResponse;
import global.genesis.service.response.CoinCapHistoryResponse;

public class TestUtils {

	public static CoinCapAssetDataResponse getBtcCoinCapAssetDataresponse() {
		var response = new CoinCapAssetDataResponse();
		response.setData(getBtcCoinCapAssetResponses());
		return response;
	}

	private static List<CoinCapAssetResponse> getBtcCoinCapAssetResponses() {
		return Arrays.asList(getBtcCoinCapAssetResponse());
	}

	private static CoinCapAssetResponse getBtcCoinCapAssetResponse() {
		var response = new CoinCapAssetResponse();
		response.setId("bitcoin");
		response.setSymbol("BTC");
		response.setName("Bitcoin");
		response.setRank("1");
		return response;
	}

	public static CoinCapAssetDataResponse getEthCoinCapAssetDataresponse() {
		var response = new CoinCapAssetDataResponse();
		response.setData(getEthCoinCapAssetResponses());
		return response;
	}

	private static List<CoinCapAssetResponse> getEthCoinCapAssetResponses() {
		return Arrays.asList(getEthCoinCapAssetResponse());
	}

	private static CoinCapAssetResponse getEthCoinCapAssetResponse() {
		var response = new CoinCapAssetResponse();
		response.setId("ethereum");
		response.setSymbol("ETH");
		response.setName("Ethereum");
		response.setRank("2");
		return response;
	}

	public static CoinCapHistoryDataResponse getBtcCoinCapHistoryDataResponse() {
		var response = new CoinCapHistoryDataResponse();
		response.setData(getBtcCoinCapHistoryResponses());
		return response;
	}

	private static List<CoinCapHistoryResponse> getBtcCoinCapHistoryResponses() {
		return Arrays.asList(getBtcCoinCapHistoryResponse());
	}

	private static CoinCapHistoryResponse getBtcCoinCapHistoryResponse() {
		var response = new CoinCapHistoryResponse();
		response.setPriceUsd("56999.9728252053067291");
		response.setTime(1617753600000L);
		response.setDate("2021-04-07T00:00:00.000Z");
		return response;
	}

	public static CoinCapHistoryDataResponse getEthCoinCapHistoryDataResponse() {
		var response = new CoinCapHistoryDataResponse();
		response.setData(getEthCoinCapHistoryResponses());
		return response;
	}

	private static List<CoinCapHistoryResponse> getEthCoinCapHistoryResponses() {
		return Arrays.asList(getEthCoinCapHistoryResponse());
	}

	private static CoinCapHistoryResponse getEthCoinCapHistoryResponse() {
		var response = new CoinCapHistoryResponse();
		response.setPriceUsd("2032.1394325557042107");
		response.setTime(1617753600000L);
		response.setDate("2021-04-07T00:00:00.000Z");
		return response;
	}

}
