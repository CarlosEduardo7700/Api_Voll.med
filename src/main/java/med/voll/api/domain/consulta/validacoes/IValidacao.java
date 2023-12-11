package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public interface IValidacao {

    void validar(DadosAgendamentoConsulta dados);
}
