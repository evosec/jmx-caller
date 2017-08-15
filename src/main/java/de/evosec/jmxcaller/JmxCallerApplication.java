package de.evosec.jmxcaller;

import java.util.Set;

import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.j256.simplejmx.client.JmxClient;

@SpringBootApplication
@EnableConfigurationProperties(JmxCallerProperties.class)
public class JmxCallerApplication implements CommandLineRunner {

	private static final Logger LOG =
	        LoggerFactory.getLogger(JmxCallerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JmxCallerApplication.class, args);
	}

	@Autowired
	private JmxCallerProperties properties;

	@Override
	public void run(String... args) throws Exception {
		String jmxUrl = properties.getUrl();
		if (jmxUrl == null) {
			jmxUrl = JmxClient.generalJmxUrlForHostNamePort(
			    properties.getHostname(), properties.getPort());
		}

		try (JmxClient client = new JmxClient(jmxUrl, properties.getUsername(),
		    properties.getPassword())) {
			for (String beanDomain : client.getBeanDomains()) {
				if (!beanDomain.startsWith(properties.getBeanDomainPrefix())) {
					continue;
				}
				Set<ObjectName> beanNames = client.getBeanNames(beanDomain);
				ObjectName objectName = new ObjectName(
				    beanDomain + ":" + properties.getObjectNameSuffix());
				if (!beanNames.contains(objectName)) {
					continue;
				}
				if (client.getOperationInfo(objectName,
				    properties.getOperationName()) == null) {
					continue;
				}
				Object result = client.invokeOperation(objectName,
				    properties.getOperationName(),
				    properties.getOperationParameters().toArray(new Object[0]));
				LOG.info("result: {}", result);
			}
		}
	}

}
