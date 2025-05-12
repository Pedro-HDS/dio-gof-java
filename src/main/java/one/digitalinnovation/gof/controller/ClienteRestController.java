package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;


@RestController
@RequestMapping("clientes")
@Tag(name = "Clientes", description = "Controller para gerenciamento de clientes")
@CrossOrigin("*")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	@Operation(summary = "Buscar todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso"),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	public ResponseEntity<Iterable<Cliente>> buscarTodos() {
		return ResponseEntity.ok(clienteService.buscarTodos());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar cliente por ID", description = "Retorna um cliente específico pelo seu ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	public ResponseEntity<Cliente> buscarPorId(@Parameter(description = "ID do cliente", required = true) @PathVariable Long id) {
		return ResponseEntity.ok(clienteService.buscarPorId(id));
	}

	@PostMapping
	@Operation(summary = "Inserir um novo cliente", description = "Cria um novo cliente no sistema")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos"),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	public ResponseEntity<Cliente> inserir(@Parameter(description = "Dados do cliente", required = true) @RequestBody Cliente cliente) {
		clienteService.inserir(cliente);
		return ResponseEntity.ok(cliente);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um cliente existente", description = "Atualiza os dados de um cliente existente pelo ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos"),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	public ResponseEntity<Cliente> atualizar(@Parameter(description = "ID do cliente", required = true) @PathVariable Long id, 
											 @Parameter(description = "Dados do cliente", required = true) @RequestBody Cliente cliente) {
		clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar um cliente", description = "Remove um cliente do sistema pelo ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	public ResponseEntity<Void> deletar(@Parameter(description = "ID do cliente", required = true) @PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}