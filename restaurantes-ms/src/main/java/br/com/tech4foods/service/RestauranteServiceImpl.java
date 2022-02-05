package br.com.tech4foods.service;

import br.com.tech4foods.client.ProdutosFeignClient;
import br.com.tech4foods.datatransfer.RestauranteDataTransfer;
import br.com.tech4foods.model.Restaurante;
import br.com.tech4foods.repository.RestauranteRepository;
import br.com.tech4foods.shared.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private ProdutosFeignClient feignClient;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public RestauranteDataTransfer adicionar(RestauranteDataTransfer restauranteDataTransfer) {
        return salvar(restauranteDataTransfer);
    }

    @Override
    public Optional<RestauranteDataTransfer> obterPorId(String id) {
        Optional<Restaurante> restauranteOpt = repository.findById(id);
        if (restauranteOpt.isPresent()) {
            Restaurante restaurante = restauranteOpt.get();
            restaurante.setProdutos(feignClient.obterProdutos(id));
            return Optional.of(mapper.map(restaurante, RestauranteDataTransfer.class));
        }
        return Optional.empty();
    }

    @Override
    public List<RestauranteDataTransfer> obterTodos() {
        List<Restaurante> restaurantes = repository.findAll();

        //for(Produto produto : feignClient.obterProdutos())

        return restaurantes.stream()
                .map(restaurante ->
                {
                    restaurante.setProdutos(feignClient.obterProdutos(restaurante.getId()));
                    return mapper.map(restaurante, RestauranteDataTransfer.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void remover(String id) {
        repository.deleteById(id);
    }

    @Override
    public RestauranteDataTransfer salvar(RestauranteDataTransfer restauranteDataTransfer) {
        Restaurante restaurantes = mapper.map(restauranteDataTransfer, Restaurante.class);
        restaurantes = repository.save(restaurantes);

        return mapper.map(restaurantes, RestauranteDataTransfer.class);
    }
}
