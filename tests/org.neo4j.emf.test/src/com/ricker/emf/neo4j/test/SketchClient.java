package com.ricker.emf.neo4j.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class SketchClient {

	@Test
	public void getRoot() throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://localhost:7474/db/data/node/1");
		httpget.setHeader("Accept", "application/json");
		HttpResponse response = httpclient.execute(httpget);
		// HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		String output;
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}
		httpclient.getConnectionManager().shutdown();
	}

//	@Test
	public void getRootJson() throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://localhost:7474/db/data/");
		httpget.setHeader("Accept", "application/json");
		HttpResponse response = httpclient.execute(httpget);
		JsonFactory jsonFactory = new JsonFactory();
		JsonParser parser = jsonFactory.createJsonParser(response.getEntity().getContent());
		if (parser.nextToken() != JsonToken.START_OBJECT) {
			throw new IOException("Expected data to start with an Object");
		}
		// Iterate over object fields:
		while (parser.nextToken() != JsonToken.END_OBJECT) {
			System.out.println(parser.getCurrentToken().toString());
		}
		parser.close();
		httpclient.getConnectionManager().shutdown();
	}

}
