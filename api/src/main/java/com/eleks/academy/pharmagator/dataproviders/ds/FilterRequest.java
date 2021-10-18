package com.eleks.academy.pharmagator.dataproviders.ds;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class FilterRequest {
	private Long page;
	private Long per;
}
