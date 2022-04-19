package br.com.lubank.banco.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	private String email;
	private String senha;

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Gera um token de usuario através do email e da senha.
	 * 
	 * @param email
	 * @param senha
	 * @return Retorna um token de usuario.
	 */
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}
