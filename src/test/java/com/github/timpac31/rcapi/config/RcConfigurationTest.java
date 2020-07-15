package com.github.timpac31.rcapi.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RcConfigurationTest {
	@Test
	public void TestRcConfiguration() {
		RcConfiguration config = RcConfiguration.newInstance("rc-configuration-test.xml");
		ElementOption weatherOption = config.getElementOption("wheather-api-seoul");
		ElementOption instagramOption = config.getElementOption("test");
		
		assertEquals("wheather-api-seoul", weatherOption.getName());
		assertEquals("http://test.com", weatherOption.getUrl());
		assertEquals(600000, weatherOption.getInterval());
		assertEquals(DataType.XML, weatherOption.getDataType());
		
		assertEquals("test", instagramOption.getName());
		assertEquals("https://test.test.com/api/me&access_token=token1234", instagramOption.getUrl());
		assertEquals(60000, instagramOption.getInterval());
		assertEquals(DataType.JSON, instagramOption.getDataType());
	}

}
