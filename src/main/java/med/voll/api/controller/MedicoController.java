package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.CredenciaisMedico;
import med.voll.api.medico.DadosParaListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<DadosParaListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosParaListagemMedico::new);
    }

}
