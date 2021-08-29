package global.genesis.usecase;

import java.util.List;

import global.genesis.cross.CSVUtils;
import global.genesis.domain.entity.Performance;
import global.genesis.domain.entity.Wallet;
import global.genesis.domain.mapper.HistoryMapper;
import global.genesis.service.CoinCapClient;

public class CalculateFinancialValueOfWalletUseCase {

	private static final Long START_TIME = 1617753600000L;
	private static final Long END_TIME = 1617753601000L;
	private static final String INTERVAL = "d1";
	private static final String FILENAME = "src/main/resources/wallet.csv";
	private final CoinCapClient coinCapClient;

	public CalculateFinancialValueOfWalletUseCase(CoinCapClient coinCapClient) {
		this.coinCapClient = coinCapClient;
	}

	public Performance execute() {
		var wallets = CSVUtils.load(FILENAME, Wallet.class);
		return calculatePerformanceFrom(wallets);
	}

	private Performance calculatePerformanceFrom(List<Wallet> wallets) {
		wallets.forEach(this::loadAssetHistory);
		return new Performance(wallets);
	}

	private void loadAssetHistory(Wallet wallet) {
		var asset = coinCapClient.getAsset(wallet.getSymbol());
		var historyResponse = coinCapClient.getHistory(asset.getId(), INTERVAL, START_TIME, END_TIME);
		wallet.setHistories(HistoryMapper.toHistories(historyResponse));
		wallet.calculateCurrentPosition();
	}
}
