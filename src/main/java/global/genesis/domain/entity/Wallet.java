package global.genesis.domain.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.opencsv.bean.CsvBindByName;

public class Wallet {

	@CsvBindByName(column = "symbol")
	private String symbol;

	@CsvBindByName(column = "quantity")
	private BigDecimal quantity;

	@CsvBindByName(column = "price")
	private BigDecimal price;

	private BigDecimal currentPosition;

	private BigDecimal increase;

	private List<AssetHistory> histories;

	public Wallet() {
		currentPosition = BigDecimal.ZERO;
		increase = BigDecimal.ZERO;
		histories = new ArrayList<>();
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setHistories(List<AssetHistory> histories) {
		this.histories = histories;
	}

	public BigDecimal getCurrentPosition() {
		return currentPosition;
	}

	public BigDecimal getIncrease() {
		return increase;
	}

	public void calculateCurrentPosition() {
		if (CollectionUtils.isNotEmpty(this.histories)) {
			var priceUsd = histories.get(0).getPriceUsd();
			if (this.quantity != null && priceUsd != null) {
				this.currentPosition = this.quantity.multiply(priceUsd);
				this.increase = priceUsd.divide(this.price, 2, RoundingMode.HALF_EVEN);
			}
		}
	}

}
