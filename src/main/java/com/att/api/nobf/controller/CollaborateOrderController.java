package com.att.api.nobf.controller;

import com.att.api.nobf.model.CollaborateOrder;
import com.att.api.nobf.model.OrderNotify;
import com.att.api.nobf.service.INOBFCollaborateService;
import com.bcg.api.common.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collaborateorder")
public class CollaborateOrderController extends Controller
{
	private static final Logger logger = LoggerFactory.getLogger(CollaborateOrderController.class);

    private final INOBFCollaborateService service;
    
    @Autowired
    public CollaborateOrderController(INOBFCollaborateService service) {
        this.service = service;
    }

    // TODO: @Atif -- @CrossOrigin is handled by the supertype
    @CrossOrigin(origins="*")
    @PostMapping("/v1/order/{productType}")
    public ResponseEntity<?> generateOrder(@RequestBody CollaborateOrder order) {
    	//TODO future enrichment based on different product needs for RFI
    	return service.generateOrder(order);
    }
    
    @CrossOrigin(origins="*")
    @GetMapping(value = "/v1/order/orderList/{daysToInclude}/{product}")
    public @ResponseBody List<OrderNotify> retrieveOrders(@PathVariable(value="daysToInclude") int daysToInclude,@PathVariable(value="product") String product) {
    	return service.retrieveOrders(daysToInclude, product);
    }
   
	
}
