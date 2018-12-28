package com.att.api.nobf.controller;

import com.amind.att.controller.solution.avpn.SSDFQualifyAddressRequest;
import com.att.api.nobf.model.ADIQualifyAddressResponse;
import com.att.api.nobf.service.INewOnlineBuyFlowService;
import com.att.ssdf.cpq.services.soap.qualifyaddressesresponse.v1.QualifyAddressesResponseInfo;
import com.bcg.api.common.controller.Controller;
import com.bcg.api.common.exception.InternalServerErrorException;
import com.cingular.csi.csi.namespaces.container._public.validateaddressserviceavailabilityresponse.ValidateAddressServiceAvailabilityResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ApiController extends Controller {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    private INewOnlineBuyFlowService nobfService;

    @Autowired
    public ApiController(final INewOnlineBuyFlowService nobfService) {
        this.nobfService = nobfService;
    }

    /**
     * Returns information about the solutions for which the given address qualifies.
     *
     * @param request The request
     * @return A QualifyAddressesResponseInfo structure, representing the solutions available at the given address.
     */
    @CrossOrigin(origins="*")
    @PostMapping(path="/address/qualify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ADIQualifyAddressResponse qualifyAddress(@RequestBody final SSDFQualifyAddressRequest request) {
        try {
            return nobfService.processAddressQualificationRequest(request);
        } catch (final Exception e) {
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Returns information about the solutions for which the given address qualifies.
     *
     * @param line1 Address line 1
     * @param line2 Address line 2
     * @param city Address city
     * @param state Address state two char
     * @param zip Address zip code
     * @return A QualifyAddressesResponseInfo structure, representing the solutions available at the given address.
     */
    @CrossOrigin(origins="*")
    @GetMapping(path="/address/qualify", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ADIQualifyAddressResponse qualifyAddress(
            @RequestParam(value = "line1", defaultValue = "") String line1,
            @RequestParam(value = "line2", defaultValue = "", required = false) String line2,
            @RequestParam(value = "city", defaultValue = "") String city,
            @RequestParam(value = "state", defaultValue = "") String state,
            @RequestParam(value = "zip", defaultValue = "") String zip) {

        try {
            final SSDFQualifyAddressRequest request = new SSDFQualifyAddressRequest();
            request.setStreetName(line1 + " " + line2);
            request.setCity(city);
            request.setState(state);
            request.setZip(zip);

            return nobfService.processAddressQualificationRequest(request);
            	
        } catch (final Exception e) {
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Validates if the given address is valid.
     *
     * @param line1 Address line 1
     * @param line2 Address line 2
     * @param city Address city
     * @param state Address state two char
     * @param zip Address zip code
     *
     * @return A ValidateAddressServiceAvailabilityResponseInfo structure containing standardized address
     */
    @CrossOrigin(origins="*")
    @GetMapping(path="/address/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ValidateAddressServiceAvailabilityResponseInfo validatePostalAddress(
            @RequestParam(value = "line1", defaultValue = "") String line1,
            @RequestParam(value = "line2", defaultValue = "", required = false) String line2,
            @RequestParam(value = "city", defaultValue = "") String city,
            @RequestParam(value = "state", defaultValue = "") String state,
            @RequestParam(value = "zip", defaultValue = "") String zip) {

        try {
            return nobfService.validateAddressServiceAvail(line1, line2, city, state, zip);
        } catch (final Exception e) {
            throw new InternalServerErrorException(e);
        }
    }
}

