package model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data class Cryptocurrency {
	private String id;
	private double current_price;
	private String market_cap;
	private List<StatusUpdate> statusUpdates;
}
