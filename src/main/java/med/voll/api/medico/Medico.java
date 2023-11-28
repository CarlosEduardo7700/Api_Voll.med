package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(CredenciaisMedico credenciais) {
        this.ativo = true;
        this.nome = credenciais.nome();
        this.email = credenciais.email();
        this.telefone = credenciais.telefone();
        this.crm = credenciais.crm();
        this.especialidade = credenciais.especialidade();
        this.endereco = new Endereco(credenciais.endereco());
    }

    public void atualizarInformacoes(CredenciaisMedicoParaPut credenciais) {
        if (credenciais.nome() != null) {
            this.nome = credenciais.nome();
        }if (credenciais.telefone() != null) {
            this.telefone = credenciais.telefone();
        }if (credenciais.endereco() != null) {
            this.endereco.atualizarInformacoes(credenciais.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
