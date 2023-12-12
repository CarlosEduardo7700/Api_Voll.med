package med.voll.api.controller;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CredenciaisMedico> credenciaisMedicoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> detalhamentoMedicoJson;

    @MockBean
    private MedicoRepository repository;

    @Test
    @DisplayName("Deveria devolver o cod. 400 quando as informações estão inválidas.")
    @WithMockUser
    void cadastrar_Cenario1() throws Exception {
        MockHttpServletResponse response = mvc
                .perform(post("/medico"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver o cod. 200 quando as informações estão válidas.")
    @WithMockUser
    void cadastrar_Cenario2() throws Exception {

        CredenciaisMedico credenciais = new CredenciaisMedico(
                "Medico",
                "medico@voll.med",
                "11912345678",
                "12345",
                Especialidade.CARDIOLOGIA,
                dadosEndereco());

        when(repository.save(any())).thenReturn(new Medico(credenciais));

        MockHttpServletResponse response = mvc
                .perform(post("/medico")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(credenciaisMedicoJson.write(credenciais).getJson()))
                .andReturn().getResponse();

        DadosDetalhamentoMedico dadosDetalhamento = new DadosDetalhamentoMedico(
                null,
                credenciais.nome(),
                credenciais.email(),
                credenciais.crm(),
                credenciais.telefone(),
                credenciais.especialidade(),
                new Endereco(credenciais.endereco())
        );

        String jsonEsperado = detalhamentoMedicoJson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}