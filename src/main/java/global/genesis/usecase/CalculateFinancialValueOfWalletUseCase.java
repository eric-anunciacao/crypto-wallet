package global.genesis.usecase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections4.ListUtils;

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
	private static final DateTimeFormatter HOUR_PATTERN = DateTimeFormatter.ofPattern("HH:mm:ss");
	private static final Logger LOGGER = Logger.getLogger(CalculateFinancialValueOfWalletUseCase.class.getName());

	private final CoinCapClient coinCapClient;

	public CalculateFinancialValueOfWalletUseCase(CoinCapClient coinCapClient) {
		this.coinCapClient = coinCapClient;
	}

	public Performance execute() {
		LOGGER.log(Level.INFO, "Now is {0}", HOUR_PATTERN.format(LocalDateTime.now()));
		var wallets = CSVUtils.load(FILENAME, Wallet.class);
		return calculatePerformanceFrom(wallets);
	}

	private Performance calculatePerformanceFrom(List<Wallet> wallets) {
		var partition = partition(wallets);
		execute(partition);
		return new Performance(wallets);
	}

	private List<List<Callable<Wallet>>> partition(List<Wallet> wallets) {
		var callables = new ArrayList<Callable<Wallet>>();
		wallets.parallelStream().forEach(w -> callables.add(() -> w));
		return ListUtils.partition(callables, 3);
	}

	private void execute(List<List<Callable<Wallet>>> partition) {
		var executor = Executors.newWorkStealingPool();
		partition.parallelStream().forEach(callable -> {
			try {
				executor.invokeAll(callable).parallelStream().map(future -> {
					try {
						var wallet = future.get();
						LOGGER.log(Level.INFO, "Submitted request ASSET_{0} at {1}",
								new Object[] { wallet.getSymbol(), HOUR_PATTERN.format(LocalDateTime.now()) });
						return wallet;
					} catch (Exception e) {
						throw new IllegalStateException(e);
					}
				}).forEach(this::loadAssetHistory);
				LOGGER.log(Level.INFO, "(program hangs, waiting for some of the previous requests to finish)");
			} catch (InterruptedException e) {
				LOGGER.log(Level.SEVERE, "Thread fail:", e);
				Thread.currentThread().interrupt();
			}
		});
	}

	private void loadAssetHistory(Wallet wallet) {
		var asset = coinCapClient.getAsset(wallet.getSymbol());
		var historyResponse = coinCapClient.getHistory(asset.getId(), INTERVAL, START_TIME, END_TIME);
		wallet.setHistories(HistoryMapper.toHistories(historyResponse));
		wallet.calculateCurrentPosition();
	}
}
