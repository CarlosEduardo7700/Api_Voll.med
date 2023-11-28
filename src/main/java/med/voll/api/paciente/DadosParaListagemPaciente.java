package med.voll.api.paciente;

public record DadosParaListagemPaciente(String nome, String email, String cpf) {

    public DadosParaListagemPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
