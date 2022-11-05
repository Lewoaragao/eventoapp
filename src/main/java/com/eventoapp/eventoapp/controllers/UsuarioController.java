package com.eventoapp.eventoapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.eventoapp.models.Usuario;
import com.eventoapp.eventoapp.models.UsuariosRoles;
import com.eventoapp.eventoapp.repository.UsuarioRepository;
import com.eventoapp.eventoapp.repository.UsuariosRolesRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository ur;

	@Autowired
	private UsuariosRolesRepository urr;
	
	@RequestMapping(value = "/cadastroUsuario", method = RequestMethod.GET)
	public String form() {
		
		return "usuario/formCadastroUsuario";
	}
	
	@RequestMapping(value = "/cadastroUsuario", method = RequestMethod.POST)
	public String form(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos.");
			return "redirect:/cadastroUsuario";
		}
		
		Usuario usuarioVerifica = ur.findByLogin(usuario.getLogin());
		
		if(usuarioVerifica != null) {
			attributes.addFlashAttribute("mensagem", "Nome de usuário em uso.");
			return "redirect:/cadastroUsuario";
		}
		
		UsuariosRoles usuariosRoles = new UsuariosRoles();
		usuariosRoles.setUsuarioId(usuario.getLogin());
		usuariosRoles.setRoleId(usuario.getRole());
		
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		ur.save(usuario);
		urr.save(usuariosRoles);

		attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
		
		return "redirect:/cadastroUsuario";
	}
	
}
