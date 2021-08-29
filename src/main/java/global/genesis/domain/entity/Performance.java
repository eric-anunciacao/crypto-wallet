package global.genesis.domain.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.CollectionUtils;

public class Performance {

	private BigDecimal total;
	private String bestAsset;
	private BigDecimal bestPerformance;
	private String worstAsset;
	private BigDecimal worstPerformance;

	public Performance(List<Wallet> wallets) {
		generateFrom(wallets);
	}

	private void generateFrom(List<Wallet> wallets) {
		if (CollectionUtils.isNotEmpty(wallets)) {
			this.total = wallets.stream().map(Wallet::getCurrentPosition).reduce(BigDecimal.ZERO, BigDecimal::add)
					.setScale(2, RoundingMode.HALF_EVEN);

			var bestWallet = wallets.stream().max(Comparator.comparing(Wallet::getIncrease))
					.orElseThrow(NoSuchElementException::new);
			this.bestPerformance = bestWallet.getIncrease();
			this.bestAsset = bestWallet.getSymbol();

			var worstWallet = wallets.stream().min(Comparator.comparing(Wallet::getIncrease))
					.orElseThrow(NoSuchElementException::new);
			this.worstPerformance = worstWallet.getIncrease();
			this.worstAsset = worstWallet.getSymbol();
		}
	}

	@Override
	public String toString() {
		return "total=" + total + ", bestAsset=" + bestAsset + ", bestPerformance=" + bestPerformance + ", worstAsset="
				+ worstAsset + ", worstPerformance=" + worstPerformance;
	}

}
