package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.CredenciaisPaciente;
import med.voll.api.paciente.DadosParaListagemPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void Cadastrar(@RequestBody @Valid CredenciaisPaciente credenciais) {
        repository.save(new Paciente(credenciais));
    }

    @GetMapping
    public Page<DadosParaListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosParaListagemPaciente::new);
    }

}
