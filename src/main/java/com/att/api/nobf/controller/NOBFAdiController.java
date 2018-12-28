package com.att.api.nobf.controller;

import com.att.api.nobf.dto.AdditionalInfoDTO;
import com.att.api.nobf.dto.FalloutCallbackInfoDTO;
import com.att.api.nobf.dto.OrderDTO;
import com.att.api.nobf.service.INOBFAdiOrderService;
import com.bcg.api.common.controller.Controller;
import com.bcg.api.common.exception.BadRequestException;
import com.bcg.api.common.exception.InternalServerErrorException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/nobf")
public class NOBFAdiController extends Controller {

    private final INOBFAdiOrderService service;

    @Autowired
    public NOBFAdiController(INOBFAdiOrderService service) {
        this.service = service;
    }

    @CrossOrigin(origins="*")
    @RequestMapping(value="/orders", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    String create(@Valid @RequestBody OrderDTO ordertDTO, final BindingResult bindingResult) {
        log.info("Creating a new order entry with information: {}", ordertDTO);

        if (!bindingResult.hasErrors()) {
        	try {
        		OrderDTO created = service.create(ordertDTO);
                log.info("Created a new order entry with information: {}", created);
                return created.getId();
        	}  catch (Exception e) {
        		throw new InternalServerErrorException(e);
        	}
        } else {
        	throw new BadRequestException(this.getErrors(bindingResult));
        }
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    OrderDTO delete(@PathVariable("id") String id) {
        log.info("Deleting order entry with id: {}", id);

        OrderDTO deleted = service.delete(id);
        log.info("Deleted order entry with information: {}", deleted);

        return deleted;
    }

    @CrossOrigin(origins="*")
    @RequestMapping(value="/orders", method = RequestMethod.GET)
    List<OrderDTO> findAll() {
        log.info("Finding all order entries");

        List<OrderDTO> todoEntries = service.findAll();
        log.info("Found {} order entries", todoEntries.size());

        return todoEntries;
    }

    @CrossOrigin(origins="*")
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    OrderDTO findById(@PathVariable("id") String id) {
        log.info("Finding order entry with id: {}", id);

        OrderDTO ordertDTO = service.findById(id);
        log.info("Found order entry with information: {}", ordertDTO);

        return ordertDTO;
    }

    @CrossOrigin(origins="*")
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
    OrderDTO update(@RequestBody @Valid OrderDTO ordertDTO) {
        log.info("Updating order entry with information: {}", ordertDTO);

        OrderDTO updated = service.update(ordertDTO);
        log.info("Updated order entry with information: {}", updated);

        return updated;
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping(value="/additionalInfo", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    AdditionalInfoDTO createAdditionalInfo(@RequestBody @Valid AdditionalInfoDTO additionalInfoDTO) {
        log.info("Creating a new order entry with additional information: {}", additionalInfoDTO);

        AdditionalInfoDTO createdAddinfo = service.createAdditionalInfo(additionalInfoDTO);
        log.info("Created a new order entry with addmitional information: {}", createdAddinfo);

        return createdAddinfo;
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping(value="/falloutCallbackInfo", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    FalloutCallbackInfoDTO createFalloutCallbackInfo(@RequestBody @Valid FalloutCallbackInfoDTO falloutCallbackInfoDTO) {
        log.info("Creating a new order entry with additional information: {}", falloutCallbackInfoDTO);

        FalloutCallbackInfoDTO falloutCallbackInfo = service.createFalloutCallbackInfo(falloutCallbackInfoDTO);
        log.info("Created a new order entry with addmitional information: {}", falloutCallbackInfo);

        return falloutCallbackInfo;
    }
}
