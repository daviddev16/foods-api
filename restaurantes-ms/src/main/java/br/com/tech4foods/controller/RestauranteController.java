package br.com.tech4foods.controller;

import br.com.tech4foods.datatransfer.RestauranteDataTransfer;
import br.com.tech4foods.service.restaurante.RestauranteServiceImpl;
import br.com.tech4foods.view.request.RestauranteRequest;
import br.com.tech4foods.view.response.RestauranteResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private RestauranteServiceImpl service;

    @GetMapping(value = "/status")
    public String status(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<RestauranteResponse>> obterRestaurantes() {
        List<RestauranteDataTransfer> Restaurantes = service.obterTodos();
        return new ResponseEntity<>(Restaurantes.stream()
                .map(dataTransfer -> mapper.map(dataTransfer, RestauranteResponse.class))
                .collect(Collectors.toList()), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestauranteResponse> obterRestaurantePorId(@PathVariable String id) {
        Optional<RestauranteDataTransfer> restauranteDtoOpt = service.obterPorId(id);
        RestauranteDataTransfer restauranteDto = restauranteDtoOpt.get();

        return restauranteDtoOpt.isPresent() ? new ResponseEntity<>(mapper.map(restauranteDto,
                RestauranteResponse.class), HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<RestauranteResponse> adicionarRestaurante(@RequestBody
                                                                        @Valid RestauranteRequest restauranteReq) {

        RestauranteDataTransfer restauranteDto = mapper.map(restauranteReq, RestauranteDataTransfer.class);
        restauranteDto = service.adicionar(restauranteDto);
        return new ResponseEntity<>(mapper.map(restauranteDto, RestauranteResponse.class), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RestauranteResponse> atualizarRestaurante(@PathVariable String id,
                                                            @RequestBody @Valid RestauranteRequest restauranteReq) {

        RestauranteDataTransfer restauranteDto = mapper.map(restauranteReq, RestauranteDataTransfer.class);
        restauranteDto = service.atualizar(id, restauranteDto);
        return new ResponseEntity<>(mapper.map(restauranteDto, RestauranteResponse.class), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removerRestaurante(@PathVariable String id) {
        service.remover(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
