package com.adminwallet.Controllers;

import com.adminwallet.Entity.Currency;
import com.adminwallet.Services.CurrencyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@WebMvcTest(value=CurrencyControllers.class)
class CurrencyControllersTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CurrencyService currencyService;
    @Test
    void testGetAllCurrency() throws Exception{
        Currency currency1= new Currency(1L,"bitcoin",35000D,"0x64ff637fb478863b7468bc97d30a5bf3a428a1fd");
        Currency currency2= new Currency(2L,"Eth",3500D,"0x64ff637fb478863b7468bc97d30a5bf3a428a1ff");
        List<Currency> currencyList=new ArrayList<>();
        currencyList.add(currency1);
        currencyList.add(currency2);
        Mockito.when(currencyService.findAllCurrency()).thenReturn(currencyList);
        String URL="/currency/all";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = this.mapToJson(currencyList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(object);

    }



    @Test
    void testGetCurrencyById() throws  Exception {
        Currency currency=new Currency(1L,"bitcoin",35000D,"0x64ff637fb478863b7468bc97d30a5bf3a428a1fd");
        Mockito.when(currencyService.findCurrencyById(Mockito.anyLong())).thenReturn(Optional.of(currency));
        String URL="/currency/find/1";
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andReturn();
        String expectedJson=this.mapToJson(currency);
        String OutputInJson=mvcResult.getResponse().getContentAsString();
        assertThat(OutputInJson).isEqualTo(expectedJson);
    }

    @Test
    void testAddCurrency() throws  Exception {
        Currency currency=new Currency(1L,"bitcoin",35000D,"0x64ff637fb478863b7468bc97d30a5bf3a428a1fd");
        String inputInJson=this.mapToJson(currency);
        Mockito.when(currencyService.addCurrency(Mockito.any(Currency.class))).thenReturn(currency);
        String URL="/currency/add";
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post(URL).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response=mvcResult.getResponse();
        String outputInJson=response.getContentAsString();
        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Currency currency=new Currency(1L,"bitcoin",35000D,"0x64ff637fb478863b7468bc97d30a5bf3a428a1fd");
        String inputInJson=this.mapToJson(currency);
        Mockito.when(currencyService.updateCurrency(Mockito.any(Currency.class))).thenReturn(currency);
        String URL="/currency/update";
        RequestBuilder requestBuilder=MockMvcRequestBuilders.put(URL).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response=mvcResult.getResponse();
        String outputInJson=response.getContentAsString();
        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    @Test
    void testDeleteCurrency() throws Exception {
        Currency currency=new Currency(1L,"bitcoin",35000D,"0x64ff637fb478863b7468bc97d30a5bf3a428a1fd");
        Mockito.when(currencyService.findCurrencyById(currency.getId())).thenReturn(Optional.of(currency));
        String URL="/currency/delete/1";
        RequestBuilder requestBuilder=MockMvcRequestBuilders.delete(URL).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response=mvcResult.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
}