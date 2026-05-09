package com.bridgelabz.controller;

import com.bridgelabz.model.QuantityMeasurementEntity;
import com.bridgelabz.service.IQuantityMeasurementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request
        .SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
public class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IQuantityMeasurementService service;

    @Test
    void givenValidQuantities_WhenCompare_ShouldReturnOk() throws Exception {

        QuantityMeasurementEntity response =
                new QuantityMeasurementEntity("COMPARE", null, null, true);

        when(service.compare(any(), any())).thenReturn(response);

        String requestBody = """
                {
                  "firstQuantity": {
                    "value": 1.0,
                    "unit": "FEET",
                    "measurementType": "LENGTH"
                  },
                  "secondQuantity": {
                    "value": 12.0,
                    "unit": "INCH",
                    "measurementType": "LENGTH"
                  }
                }
                """;

        mockMvc.perform(post("/api/v1/quantities/compare")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operationType").value("COMPARE"))
                .andExpect(jsonPath("$.result").value(true));
    }

    @Test
    void givenValidQuantities_WhenAdd_ShouldReturnOk() throws Exception {

        QuantityMeasurementEntity response =
                new QuantityMeasurementEntity("ADD", null, null, null);

        when(service.add(any(), any())).thenReturn(response);

        String requestBody = """
                {
                  "firstQuantity": {
                    "value": 10.0,
                    "unit": "FEET",
                    "measurementType": "LENGTH"
                  },
                  "secondQuantity": {
                    "value": 12.0,
                    "unit": "INCH",
                    "measurementType": "LENGTH"
                  }
                }
                """;

        mockMvc.perform(post("/api/v1/quantities/add")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operationType").value("ADD"));
    }
}