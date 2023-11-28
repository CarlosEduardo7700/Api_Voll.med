package med.voll.api.domain.medico;

public record DadosParaListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosParaListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
