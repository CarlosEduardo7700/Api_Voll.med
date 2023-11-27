package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.CredenciaisMedico;
import med.voll.api.medico.DadosParaListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void Cadastrar(@RequestBody @Valid CredenciaisMedico credenciais) {
        repository.save(new Medico(credenciais));
    }

    @GetMapping
    public List<DadosParaListagemMedico> listar() {
        return repository.findAll().stream().map(DadosParaListagemMedico::new).toList();
    }

}
