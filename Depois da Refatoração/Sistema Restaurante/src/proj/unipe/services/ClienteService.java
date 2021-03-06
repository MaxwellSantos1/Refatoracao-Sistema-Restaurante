package proj.unipe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proj.unipe.daos.ClienteDAO;
import proj.unipe.entities.Cliente;

@Service
@Transactional
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;

	public void inserir(Cliente cliente) throws Exception {
		clienteDAO.inserir(cliente);

	}

	public void atualizar(Cliente cliente) throws Exception {
		clienteDAO.atualizar(cliente);

	}

	public void excluir(Cliente cliente) throws Exception {

		clienteDAO.excluir(cliente);

	}

	public Cliente buscarPorId(Long id) {

		return (Cliente) clienteDAO.buscarPorID(id);

	}

	public List<Cliente> listar() {

		return clienteDAO.listar();
	}

	public List<Cliente> buscarPorNome(String filtro) {
		return clienteDAO.buscarPorNome(filtro);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/remove")
	public String remove(@PathVariable("id") Long id) {
		Cliente cliente = buscarPorId(id);
		if (cliente != null) {
			try {
				excluir(cliente);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/cliente/listar";
	}

}
