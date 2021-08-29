package global.genesis.domain.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import global.genesis.domain.entity.AssetHistory;
import global.genesis.service.response.CoinCapHistoryResponse;

public interface HistoryMapper {

	static List<AssetHistory> toHistories(List<CoinCapHistoryResponse> histories) {
		if (CollectionUtils.isEmpty(histories)) {
			return Collections.emptyList();
		}

		return histories.stream().map(HistoryMapper::toHistory).collect(Collectors.toList());
	}

	static AssetHistory toHistory(CoinCapHistoryResponse history) {
		var priceUsd = BigDecimal.ZERO;
		LocalDateTime date = null;

		if (StringUtils.isNotEmpty(history.getPriceUsd())) {
			priceUsd = new BigDecimal(history.getPriceUsd());
		}

		if (StringUtils.isNotEmpty(history.getDate())) {
			date = LocalDateTime.parse(history.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
		}

		return new AssetHistory(priceUsd, history.getTime(), date);
	}

}
