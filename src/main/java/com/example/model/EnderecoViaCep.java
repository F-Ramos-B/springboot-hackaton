package com.example.model;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoViaCep {
	
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String unidade;
	private String ibge;
	private String gia;
	
	public static EnderecoViaCep criarEnderecoFromJSON(JSONObject json) throws JSONException {
		return new EnderecoViaCep(
				json.getString("cep"),
				json.getString("logradouro"),
				json.getString("complemento"),
				json.getString("bairro"),
				json.getString("localidade"),
				json.getString("uf"),
				json.getString("unidade"),
				json.getString("ibge"),
				json.getString("gia")
			);
	}

}
