package com.github.timpac31.rcapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.timpac31.rcapi.config.RcConfigurationException;

public class RcLoaderFactoryTest {
	//@Test
	//테스트 하려면 기본설정파일(rc-configuration-test.xml) 수정시 같이 수정해야함
	public void TestRcLoaderFactoryByTagName() {
		RcLoader xmlRcLoader = RcLoaderFactory.createRcLoader("wheather-api-seoul");
		RcLoader jsonRcLoader = RcLoaderFactory.createRcLoader("instagram");
		RcLoader invalidRcLoader = RcLoaderFactory.createRcLoader("invalid");
		
		assertEquals("XmlRcLoader", xmlRcLoader.getClass().getSimpleName());
		assertEquals("JsonRcLoader", jsonRcLoader.getClass().getSimpleName());
		assertNull(invalidRcLoader);
	}
	
	@Test
	public void TestRcLoaderFactoryByFileAndTagName() {
		RcLoader xmlRcLoader = RcLoaderFactory.createRcLoader("rc-configuration-test.xml", "wheather-api-seoul");
		RcLoader jsonRcLoader = RcLoaderFactory.createRcLoader("rc-configuration-test.xml", "test");
		
		assertEquals("XmlRcLoader", xmlRcLoader.getClass().getSimpleName());
		assertEquals("JsonRcLoader", jsonRcLoader.getClass().getSimpleName());
	}
	
	@Test(expected=RcConfigurationException.class) 
	public void testExceptionWhenNotFoundConfigFile() {
		RcLoader jsonRcLoader = RcLoaderFactory.createRcLoader("none.xml", "instagram");	
	}
}
