package de.evosec.jmxcaller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jmx")
public class JmxCallerProperties {

	private String hostname;
	private int port = 6969;
	private String url;
	private String username;
	private String password;
	private String beanDomainPrefix;
	private String objectNameSuffix;
	private String operationName;
	private List<String> operationParameters = new ArrayList<>();

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBeanDomainPrefix() {
		return beanDomainPrefix;
	}

	public void setBeanDomainPrefix(String beanDomainPrefix) {
		this.beanDomainPrefix = beanDomainPrefix;
	}

	public String getObjectNameSuffix() {
		return objectNameSuffix;
	}

	public void setObjectNameSuffix(String objectNameSuffix) {
		this.objectNameSuffix = objectNameSuffix;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public List<String> getOperationParameters() {
		return operationParameters;
	}

	public void setOperationParameters(List<String> operationParameters) {
		this.operationParameters = operationParameters;
	}

}
