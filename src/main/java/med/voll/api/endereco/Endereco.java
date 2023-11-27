package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String numero;
    private String complemento;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco credenciais) {
        this.logradouro = credenciais.logradouro();
        this.bairro = credenciais.bairro();
        this.cep = credenciais.cep();
        this.uf = credenciais.uf();
        this.cidade = credenciais.cidade();
        this.numero = credenciais.numero();
        this.complemento = credenciais.complemento();
    }
}
