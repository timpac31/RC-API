package com.github.timpac31.rcapi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class UrlReader {
	public static String readFromUrl(String path) throws MalformedURLException, IOException {
		URL url = new URL(path);
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		huc.setConnectTimeout(2000);
		huc.setReadTimeout(2500);
		
		BufferedReader br = null;
		InputStream is = null;
		try {
			is = huc.getInputStream();
			br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));		
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = br.read()) != -1) {
			  sb.append((char) cp);
			}
			return sb.toString();
		}finally {
			is.close();
		}
	}
}