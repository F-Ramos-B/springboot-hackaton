package com.example.service.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConUtil {
	
	public String requisicaoRest(String tipo, String url) throws IOException {
		URL req = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) req.openConnection();
		connection.setRequestMethod(tipo);
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();
		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();
		connection.disconnect();
		return response.toString();
	}

}
