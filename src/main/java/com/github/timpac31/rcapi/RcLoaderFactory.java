package com.github.timpac31.rcapi;

import com.github.timpac31.rcapi.config.DataType;
import com.github.timpac31.rcapi.config.ElementOption;
import com.github.timpac31.rcapi.config.RcConfiguration;

public class RcLoaderFactory {
	private RcLoaderFactory() {}
	
	/**
	 * 기본설정파일 안에 tagName의 dataType을 확인하여 해당 타입의 RcLoader를 리턴한다.
	 * @param tagName 불러올 설정파일 elementOption의 name 태그 값
	 * @return 해당 타입의 RcLoader 리턴
	 * @throws InvalidTagNameException 설정파일에서 tagName을 찾을수 없을 때, 해당태그의 dataType 속성값을 지원하지 않을 때 
	 */
	public static RcLoader createRcLoader(String tagName) {
		return createRcLoader(RcConfiguration.CONFIG_FILE, tagName);
	}
	
	/**
	 * configFile설정파일 안에 tagName의 dataType을 확인하여 해당 타입의 RcLoader를 리턴한다.
	 * @param configFile 설정파일
	 * @param tagName 불러올 설정파일 elementOption의 name 태그 값
	 * @return 해당 타입의 RcLoader 리턴
	 * @throws InvalidTagNameException 설정파일에서 tagName을 찾을수 없을 때, 해당태그의 dataType 속성값을 지원하지 않을 때 
	 */
	public static RcLoader createRcLoader(String configFile, String tagName) {
		RcConfiguration config = RcConfiguration.newInstance(configFile);
		
		ElementOption option = config.getElementOption(tagName);
		if(option == null) 
			throw new InvalidTagNameException(String.format("tagName [%s] can not find. check configuration file", tagName));
		DataType dataType = option.getDataType();
		
		switch(dataType) {
			case JSON :
				return new JsonRcLoader(option);
			case XML :
				return new XmlRcLoader(option);
			default : 
				throw new InvalidTagNameException(String.format("dataType [%s] is not supported. check configuration file", dataType.getName()));
		}
	}

}
