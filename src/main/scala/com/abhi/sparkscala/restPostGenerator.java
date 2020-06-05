//package com.abhi.sparkscala;
//
//import java.security.Key;
//import java.util.Base64;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.apache.commons.httpclient.UsernamePasswordCredentials;
//import org.apache.commons.httpclient.auth.CredentialsProvider;
//import org.apache.hadoop.ipc.RefreshResponse;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.json.JSONArray;
//import org.JSON.JSONobject;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.apache.http.entity.ByteArrayEntity;
//import org.mortbay.jetty.security.Credential;
//
//import com.google.gson.JsonObject;
//
//public class restPostGenerator {
//
//	public String decryption(String file, String user, String s_pwd,String key_s) throws Exception {
//
//		String key = key_s;
//		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
//		Cipher cipher = Cipher.getInstance("AES");
//		cipher.init(Cipher.DECRYPT_MODE, aesKey);
//		byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(s_pwd));
//		String dePwd = new String(decrypted);
//		return dePwd;
//		
//	}
//	public String postRequest(String file,String user, String s_pwd, String pwd,String s_user, String key_s, String assignment_group,
//			String requestedfor, String httpost, String httpposturl) {
//		String pwd1 = decryption(s_pwd,s_user);
//
//		String body ="{\"u_assignmentgroup\":\""+ assignment_group +"\"," 
//				+ "\"u_requested_for\":\" " + requestedfor + "\"}";
//
//		String postData= body;
//		CredentialsProvider credsProvider = new BasicCredentialsProvider();
//		credsProvider.setCredentials(new AuthScope(new HttpHost(httphost)),
//		new UsernamePasswordCredentials(user,pwd1));
//        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
//
//        try {
//        	HttpPost httpPost = new HttpPost(httpposturl);
//        	httpPost.setHeader("Accept","application/json");
//        	httpPost.setHeader("Content-Type","application/json");
//        	HttpEntity entity = new ByteArrayEntity(postData.getBytes("utf-8"));
//        	httpPost.setEntity(entity);
//        	
//        	
//        	System.out.println("Executing request " + httpPost.getRequestLine());
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        }
//        
//        try {
//        	System.out.println("-------------");
//        	System.out.println(response.getStatusLine());
//        	String responseBody = EntityUtils.toString(response.getEntity());
//        	
//        	System.out.println(responseBody);
//        	
//        	JSONObject jsonObject = new JsonObject(responseBody);
//        	String incidentNumber = (String) ((JsonObject) ((JOSNArray) jsonObject.get("result")).get(0))
//        			.get("display_value");
//        	System.out.println(incidentNumber);
//        	return incidentNumber;
//        	
//        } finally {
//        	response.close();
//        	httpClient.close();
//        }
//	}
//}
