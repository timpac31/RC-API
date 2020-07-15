package com.github.timpac31.rcapi.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class RcConfiguration {
	private Map<String, ElementOption> options;
	public static final String CONFIG_FILE = "rc-configuration.xml";

	private RcConfiguration() {
		options = new HashMap<>();
	}
	
	/**
	 * 새로운 RcConfiguration 인스턴스를 리턴한다. 기본 설정파일로 클래스패스의 rc-configuration.xml 파일을 읽는다
	 * @throws RcConfigurationException 설정파일을 불러오지 못할 경우
	 */
	public static RcConfiguration newInstance() {
		return newInstance(CONFIG_FILE);
	}
	
	/**
	 * 새로운 RcConfiguration 인스턴스를 리턴한다
	 * @param configFile 설정파일, 클래스패스 하위 경로에있을 경우 경로까지 입력해야한다 
	 * @throws RcConfigurationException 설정파일을 불러오지 못할 경우
	 */
	public static RcConfiguration newInstance(String configFile) {
		RcConfiguration config = new RcConfiguration();
		config.setConfiguration(config, configFile);
		return config;
	}
	
	private void setConfiguration(RcConfiguration config, String configFile) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFile);
		if(is == null) {
			throw new RcConfigurationException(String.format("can not found file[%s]\n", configFile));
		}
		
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(is);
			NodeList elementOptionList = doc.getElementsByTagName("elementOption");
			
			for(int i=0; i<elementOptionList.getLength(); i++) {
				Element elementOption = Element.class.cast(elementOptionList.item(i));
				config.addElementOption(elementOption);
			}
		} catch (IOException e) {
			throw new RcConfigurationException(
					String.format("can not read file[%s] : %s\n", configFile, e.getMessage()));
		} catch (Exception e) {
			throw new RcConfigurationException(
					String.format("can not parse config file[%s] : %s\n", configFile, e.getMessage()));
		} finally {
			if(is != null) {
				try { is.close(); } catch (IOException e) { e.printStackTrace(); }
			}
		}
	}

	private void addElementOption(Element elementOption) {
		try {
			String name = elementOption.getElementsByTagName("name").item(0).getTextContent();
			String url = elementOption.getElementsByTagName("url").item(0).getTextContent();
			String interval = elementOption.getElementsByTagName("interval").item(0).getTextContent();
			String dataType = elementOption.getElementsByTagName("dataType").item(0).getTextContent();
			
			ElementOption option = this.options.get(name);
			if(option == null) {
				option = new ElementOption();
				option.setName(name);
				option.setUrl(url);
				option.setInterval(Long.parseLong(interval));
				option.setDataType(DataType.valueOf(dataType.toUpperCase()));
			}
			
			this.options.put(name, option);
			
		} catch(Exception e) {
			throw new RcConfigurationException(
					String.format("can not read attribute value in config file : %s\n", e.getMessage()));
		}
	}
	
	public ElementOption getElementOption(String optionName) {
		return this.options.get(optionName);
	}
	
}
