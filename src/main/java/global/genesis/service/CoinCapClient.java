package global.genesis.service;

import java.util.List;

import global.genesis.service.response.CoinCapAssetResponse;
import global.genesis.service.response.CoinCapHistoryResponse;

public interface CoinCapClient {

	CoinCapAssetResponse getAsset(String symbol);

	public List<CoinCapHistoryResponse> getHistory(String symbol, String interval, Long start, Long end);

}
