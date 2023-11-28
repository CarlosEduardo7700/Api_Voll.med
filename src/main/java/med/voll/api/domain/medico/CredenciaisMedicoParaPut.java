package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record CredenciaisMedicoParaPut(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        DadosEndereco endereco) {
}
