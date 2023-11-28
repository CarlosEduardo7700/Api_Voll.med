package med.voll.api.paciente;

public record DadosParaListagemPaciente(Long id, String nome, String email, String cpf) {

    public DadosParaListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
