## RC-API (REDUCE CALL API)
요청 제한이있는 API를 사용할 경우 응답 데이터를 JVM 메모리에 일정시간 저장하여 요청수를 줄여주는 라이브러리 
만약 설정파일에 interval 시간을 1분으로 설정하면 마지막 API요청시간 1분전에는 기존 저장데이터를 불러오고 1분후에는 다시 API 요청합니다.

## Required
 1. JDK 1.8+
 2. Dependencies
  : junit 4.12, jackson-core 2.11.0, jackson-databind 2.11.0

## Getting Started
 1. git clone https://github.com/timpac31/RC-API.git
 2. 기본 설정파일 rc-configuration.xml 수정 [[참조]](#configuration) => classpath에 추가  
    만약 직접 파일 지정하고 싶으면 [[참조]](#customConfig)
 3. mvn package => 생성된 jar 파일 프로젝트에 추가, 의존 라이브러리 추가
 4. Usage Example [[참조]](#usage)
 
## <h2 id="configuration">Modify Configuration</h2>
- rc-configuration.xml 설정파일 예제
```
<elements>
  <elementOption>
    <name>api1</name>
    <url>http://test.com</url>
    <interval>300000</interval>
    <dataType>json</dataType>
  </elementOption>
		
  <elementOption>
    <name>api2</name>
    <url>https://test.test.com/api/me&amp;access_token=token1234</url>
    <interval>60000</interval>
    <dataType>xml</dataType>
  </elementOption>
</elements>
```
- 설정파일 ElementOption 속성

attribute name     | Description                          |
-------------------|--------------------------------------|
name               | API 이름                             | 
url                | API 요청 주소                        | 
interval           | 데이터 저장시간(millisecond)         | 
dataType           | 응답 데이터 타입 `json` or `xml`      | 

## <h2 id="usage">Usage Example</h2>
 1. JSON 타입
```
  RcLoader loader = RcLoaderFactory.createRcLoader("api1");
  JsonRcStorage storage = (JsonRcStorage) loader.getStorage();
  JsonNode root = storage.getData();  
```  
> RcLoaderFactory.createRcLoader(String name) : rc-configuration.xml 설정파일의 elementOption name 값으로 설정된 로더를 리턴  
storage.getData() 하면 [com.fasterxml.jackson.databind.JsonNode](https://javadoc.io/doc/com.fasterxml.jackson.core/jackson-databind/latest/com/fasterxml/jackson/databind/JsonNode.html) 타입을 리턴

 2. XML 타입
```  
  RcLoader loader = RcLoaderFactory.createRcLoader("api2");
  XmlRcStorage storage = (XmlRcStorage) loader.getStorage();		
  Document document = storage.getData();  
```
> storage.getData() 하면 [org.w3c.dom.Document](https://docs.oracle.com/javase/8/docs/api/org/w3c/dom/Document.html) 타입을 리턴

 3. <span id="customConfig">설정파일 Custom</span>
```
  RcLoader loader = RcLoaderFactory.createRcLoader("/config/myConfiguration.xml", "test");
``` 
> 직접 설정파일명과 경로를 지정하고 싶으면 RcLoader 생성시 인자로 경로와 파일명을 지정해야함. 

## Author
JYD(timpac61@gmail.com)
