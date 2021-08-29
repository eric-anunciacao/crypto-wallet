package global.genesis.service.response;

import java.util.List;

public class CoinCapHistoryDataResponse {

	private List<CoinCapHistoryResponse> data;

	public List<CoinCapHistoryResponse> getData() {
		return data;
	}

	public void setData(List<CoinCapHistoryResponse> data) {
		this.data = data;
	}

}
