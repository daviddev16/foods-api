package br.com.tech4foods.controller;

import br.com.tech4foods.datatransfer.ProdutoDataTransfer;
import br.com.tech4foods.service.ProdutoService;
import br.com.tech4foods.view.request.ProdutoRequest;
import br.com.tech4foods.view.response.ProdutoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private ProdutoService service;

    @GetMapping(value = "/status")
    public String status(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoResponse>> obterProdutos() {
        List<ProdutoDataTransfer> produtos = service.obterTodos();
        return new ResponseEntity<>(produtos.stream()
                .map(dataTransfer -> mapper.map(dataTransfer, ProdutoResponse.class))
                .collect(Collectors.toList()), HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> obterProdutoPorId(@PathVariable String id) {
        Optional<ProdutoDataTransfer> produtoDtoOpt = service.obterPorId(id);
        ProdutoDataTransfer produtoDto = produtoDtoOpt.get();

        return produtoDtoOpt.isPresent() ? new ResponseEntity<>(mapper.map(produtoDto,
                ProdutoResponse.class), HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/restaurante/{id}")
    /* {id} = id do restaurante */
    public ResponseEntity<List<ProdutoResponse>> obterProdutoPorRestauranteId(@PathVariable String id) {
        List<ProdutoDataTransfer> produtos = service.obterPorRestaurante(id);
        List<ProdutoResponse> responses = produtos.stream()
                .map(p ->  mapper.map(p, ProdutoResponse.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionarProduto(@RequestBody @Valid ProdutoRequest produtoReq) {
        ProdutoDataTransfer produtoDto = mapper.map(produtoReq, ProdutoDataTransfer.class);
        produtoDto = service.adicionar(produtoDto);
        return new ResponseEntity<>(mapper.map(produtoDto,
                ProdutoResponse.class), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable String id,
                                                                     @RequestBody @Valid ProdutoRequest produtoReq) {

        ProdutoDataTransfer produtoDto = mapper.map(produtoReq, ProdutoDataTransfer.class);
        produtoDto = service.atualizar(id, produtoDto);
        return new ResponseEntity<ProdutoResponse>(mapper.map(produtoDto,
                ProdutoResponse.class), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable String id) {
        service.remover(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
