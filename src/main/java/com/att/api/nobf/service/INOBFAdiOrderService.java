package com.att.api.nobf.service;

import java.util.List;

import com.att.api.nobf.dto.AdditionalInfoDTO;
import com.att.api.nobf.dto.FalloutCallbackInfoDTO;
import com.att.api.nobf.dto.OrderDTO;


public interface INOBFAdiOrderService {

	OrderDTO create(OrderDTO todo);

	OrderDTO delete(String id);

	List<OrderDTO> findAll();

	OrderDTO findById(String id);

	OrderDTO update(OrderDTO todo);

	AdditionalInfoDTO createAdditionalInfo(AdditionalInfoDTO additionalInfoDTO);
	
	FalloutCallbackInfoDTO createFalloutCallbackInfo(FalloutCallbackInfoDTO falloutCallbackInfoDTO);
}
