package com.att.api.nobf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @EqualsAndHashCode(callSuper=false) @AllArgsConstructor
public class ADIQualifyAddressResponse {
	private boolean available;
}
