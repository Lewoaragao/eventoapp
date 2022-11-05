package com.eventoapp.eventoapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios_roles")
public class UsuariosRoles {
	
	@Id
	@Column(name = "usuario_id", insertable = true, nullable = false)
	private String usuarioId;

	@Column(name = "role_id", insertable = true, nullable = false)
	private String roleId;

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
