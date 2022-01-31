package br.com.tech4foods.service.restaurante;

import br.com.tech4foods.datatransfer.RestauranteDataTransfer;
import br.com.tech4foods.model.Restaurante;
import br.com.tech4foods.repository.RestauranteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestauranteServiceImpl implements  RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public RestauranteDataTransfer adicionar(RestauranteDataTransfer restauranteDataTransfer) {
        return salvar(restauranteDataTransfer);
    }

    @Override
    public Optional<RestauranteDataTransfer> obterPorId(String id) {
        Optional<Restaurante> restaurante = repository.findById(id);
        if(restaurante.isPresent())
            return Optional.of(mapper.map(restaurante.get(), RestauranteDataTransfer.class));

        return Optional.empty();
    }

    @Override
    public List<RestauranteDataTransfer> obterTodos() {
        List<Restaurante> restaurantes = repository.findAll();

        return restaurantes.stream()
                .map(restaurante -> mapper.map(restaurante, RestauranteDataTransfer.class))
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
