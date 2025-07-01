package com.example.medcentral.feature.sale.controller;

import com.example.medcentral.feature.sale.model.response.SaleAPIResponse;
import com.example.medcentral.feature.sale.model.response.SaleResponse;
import com.example.medcentral.feature.sale.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/sale")
public class SaleController {

    private SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/create-sale")
    public ResponseEntity<SaleAPIResponse<String>> createSale(@RequestParam UUID hospitalId,
                                                              @RequestParam UUID staffId,
                                                              @RequestParam UUID checkInId) {

        try {
            saleService.createSale(checkInId, staffId, hospitalId);

            SaleAPIResponse<String> response = SaleAPIResponse.<String>builder()
                    .saleAPIResponseCode("200")
                    .saleAPIResponseMessage("Sale created successfully.")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            SaleAPIResponse<String> response = SaleAPIResponse.<String>builder()
                    .saleAPIResponseCode("500")
                    .saleAPIResponseMessage("ERROR")
                    .saleAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get-sale-by-id")
    public ResponseEntity<SaleAPIResponse<Object>> getSaleById(@RequestParam UUID saleId,
                                                                @RequestParam UUID hospitalId) {

        try {
            SaleResponse sale = saleService.getSaleById(saleId, hospitalId);

            SaleAPIResponse<Object> response = SaleAPIResponse.<Object>builder()
                    .saleAPIResponseCode("200")
                    .saleAPIResponseMessage("SUCCESS.")
                    .saleAPIResponseData(sale)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            SaleAPIResponse<Object> response = SaleAPIResponse.<Object>builder()
                    .saleAPIResponseCode("500")
                    .saleAPIResponseMessage("ERROR.")
                    .saleAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/list-all-sales-by-checkin")
    public ResponseEntity<SaleAPIResponse<Object>> listAllSaleByCheckIn(@RequestParam UUID checkInId,
                                                                        @RequestParam UUID hospitalId) {

        try {
            List<SaleResponse> saleResponses = saleService.listAllSaleByCheckIn(checkInId, hospitalId);

            SaleAPIResponse<Object> response = SaleAPIResponse.<Object>builder()
                    .saleAPIResponseCode("200")
                    .saleAPIResponseMessage("SUCCESS.")
                    .saleAPIResponseData(saleResponses)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            SaleAPIResponse<Object> response = SaleAPIResponse.<Object>builder()
                    .saleAPIResponseCode("500")
                    .saleAPIResponseMessage("ERROR.")
                    .saleAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
