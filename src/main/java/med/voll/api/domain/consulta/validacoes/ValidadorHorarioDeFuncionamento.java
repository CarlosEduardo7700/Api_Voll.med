package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioDeFuncionamento implements IValidacao {

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();

        boolean ehDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean ehAntesDaAbertura = dataConsulta.getHour() < 7;
        boolean ehAposOFechamento = dataConsulta.getHour() > 18;

        if (ehDomingo || ehAntesDaAbertura || ehAposOFechamento) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica!");
        }
    }
}
