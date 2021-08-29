package global.genesis.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssetHistory {

	private BigDecimal priceUsd;
	private Long time;
	private LocalDateTime date;

	public AssetHistory(BigDecimal priceUsd, Long time, LocalDateTime date) {
		this.priceUsd = priceUsd;
		this.time = time;
		this.date = date;
	}

	public BigDecimal getPriceUsd() {
		return priceUsd;
	}

	public Long getTime() {
		return time;
	}

	public LocalDateTime getDate() {
		return date;
	}

}
