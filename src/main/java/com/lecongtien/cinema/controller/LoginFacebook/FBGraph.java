package com.lecongtien.cinema.controller.LoginFacebook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FBGraph {
	private String accessToken;

	public FBGraph(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getFBGraph() {

		HttpURLConnection uc;
		try {

			String g = "https://graph.facebook.com/me?access_token=" + accessToken + "&&fields=id,name,email,gender,picture,first_name,birthday,hometown,middle_name,albums";
			URL u = new URL(g);
			uc = (HttpURLConnection) u.openConnection();

			uc.setRequestMethod("GET");
			uc.setUseCaches(false);
			uc.setDoOutput(true);
			uc.setRequestProperty("Content-Type", "text/javascript; charset=utf-8");

			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			for (int length; (length = uc.getInputStream().read(buffer)) != -1; ) {
				result.write(buffer, 0, length);
			}
			// StandardCharsets.UTF_8.name() > JDK 7
			return result.toString("UTF-8");

//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					c.getInputStream(), "UTF-8"));
//			String inputLine;
//			StringBuffer b = new StringBuffer();
//			while ((inputLine = in.readLine()) != null)
//				b.append(inputLine + "\n");
//			in.close();
//			graph = b.toString();
//			System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}

	}

	public Map<String, Object> getGraphData(String fbGraph) {
		try {
			Map<String, Object> profile = new ObjectMapper().readValue(fbGraph,Map.class);
			return profile;
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

	}
}
