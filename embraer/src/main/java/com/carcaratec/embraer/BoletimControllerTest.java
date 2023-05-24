//package com.carcaratec.embraer;
//
//import com.carcaratec.embraer.controller.BoletimController;
//import com.carcaratec.embraer.model.dto.BoletimServico;
//import com.carcaratec.embraer.repository.BoletimServicoRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class BoletimControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private BoletimServicoRepository boletimServicoRepository;
//
//    private BoletimController boletimController;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        boletimController = new BoletimController();
//        mockMvc = MockMvcBuilders.standaloneSetup(boletimController).build();
//        boletimController.setBoletimServicoRepository(boletimServicoRepository);
//    }
//
//    @Test
//    public void testFindAllBoletim() throws Exception {
//        BoletimServico boletim1 = new BoletimServico();
//        BoletimServico boletim2 = new BoletimServico();
//
//        List<BoletimServico> boletimList = Arrays.asList(boletim1, boletim2);
//
//        when(boletimServicoRepository.findAll()).thenReturn(boletimList);
//
//        MvcResult result = mockMvc.perform(get("/boletim")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String responseBody = result.getResponse().getContentAsString();
//
//        // Verifique se o corpo da resposta contém os boletins retornados
//        // Aqui você pode fazer asserções adicionais conforme necessário
//        // Exemplo: verificar se a quantidade de boletins está correta, etc.
//        System.out.println(responseBody);
//    }
//}
