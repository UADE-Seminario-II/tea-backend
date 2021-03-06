package com.tea.springboot.USUARIO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		return usuarioDAO.findById(id);
	}

	@Override
	public Usuario save(Usuario usuario) {
		
		usuario.setEnable(true);
		return usuarioDAO.save(usuario);
	}
	
	@Override
	public Optional<Usuario> findByUsuarioAndPassword(String usuario, String password) {
		return usuarioDAO.findByUsuarioAndPassword(usuario, password);
	}
	
	@Override
	public Optional<Usuario> findByUsuario(String usuario) {
		return usuarioDAO.findByUsuario(usuario);
	}
	
	@Override
	public Usuario update(Usuario usuarioExistente, Usuario usuarioModificado) {
		
		usuarioExistente.setNombre(usuarioModificado.getNombre().trim());
		usuarioExistente.setApellido(usuarioModificado.getApellido().trim());
		usuarioExistente.setPassword(usuarioModificado.getPassword().trim());
		usuarioExistente.setMail(usuarioModificado.getMail().trim());
		usuarioExistente.setTelefono(usuarioModificado.getTelefono().trim());
		usuarioExistente.setNroDocumento(usuarioModificado.getNroDocumento().trim());
		usuarioExistente.setDireccion(usuarioModificado.getDireccion().trim());
		usuarioExistente.setLocalidad(usuarioModificado.getLocalidad().trim());
		usuarioExistente.setProvincia(usuarioModificado.getProvincia().trim());
		
		return this.save(usuarioExistente);
	}
	
	@Override
	public Usuario delete(Usuario usuario) {

		usuario.setEnable(false);
		
		return usuarioDAO.save(usuario);
	}
	
	@Override
	public Usuario activate(Usuario usuario) {

		usuario.setEnable(true);
			
		Usuario user = usuarioDAO.save(usuario);

		return user;
	}
}
