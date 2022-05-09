package com.adesso.deneysiz.integration.resource;


import com.adesso.deneysiz.admin.entity.UserDTO;
import com.adesso.deneysiz.admin.security.JWTProvider;
import com.adesso.deneysiz.integration.response.ResponseHandler;
import com.adesso.deneysiz.integration.service.BrandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.adesso.deneysiz.integration.constant.BrandUrlConstants.ADD;
import static com.adesso.deneysiz.integration.constant.BrandUrlConstants.BRANDS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BrandResource.class)
//TODO mkose use this test method first and cover all methods
public class BrandResourceTestMockMvc {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandResource brandResource;

    @MockBean
    private JWTProvider jwtProvider;

    @Mock
    private BrandService brandService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new BrandResource(brandService))
                .setControllerAdvice(new ResponseHandler())
                .build();
    }

    @Test
    public void addNewBrand() throws Exception {
        when(brandService.addNewBrand(any())).thenThrow(new RuntimeException());

        RequestBuilder requestBuilder = getRequestBuilder(BRANDS + ADD, new UserDTO());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @NotNull
    private MockHttpServletRequestBuilder getRequestBuilder(String url, Object content) {
        return MockMvcRequestBuilders
                .post(url)
                .content(asString(content))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    private String asString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}