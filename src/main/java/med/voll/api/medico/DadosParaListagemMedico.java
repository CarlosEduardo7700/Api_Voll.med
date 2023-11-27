package med.voll.api.medico;

public record DadosParaListagemMedico(String nome, String email, String crm, Especialidade especialidade) {

    public DadosParaListagemMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
