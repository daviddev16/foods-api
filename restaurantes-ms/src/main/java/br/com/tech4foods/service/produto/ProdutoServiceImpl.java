package br.com.tech4foods.service.produto;

import br.com.tech4foods.datatransfer.ProdutoDataTransfer;
import br.com.tech4foods.model.Produto;
import br.com.tech4foods.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public ProdutoDataTransfer adicionar(ProdutoDataTransfer produtoDataTransfer) {
        return salvar(produtoDataTransfer);
    }

    @Override
    public Optional<ProdutoDataTransfer> obterPorId(String id) {
        Optional<Produto> produto = repository.findById(id);
        if(produto.isPresent())
            return Optional.of(mapper.map(produto.get(), ProdutoDataTransfer.class));

        return Optional.empty();
    }

    @Override
    public List<ProdutoDataTransfer> obterTodos() {
        List<Produto> produtos = repository.findAll();

        return produtos.stream()
                .map(p -> mapper.map(p, ProdutoDataTransfer.class))
                .collect(Collectors.toList());
    }

    @Override
    public void remover(String id) {
        repository.deleteById(id);
    }

    @Override
    public ProdutoDataTransfer salvar(ProdutoDataTransfer produtoDataTransfer) {
        Produto produto = mapper.map(produtoDataTransfer, Produto.class);
        produto = repository.save(produto);

        return mapper.map(produto, ProdutoDataTransfer.class);
    }
}
