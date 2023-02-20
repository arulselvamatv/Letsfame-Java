package com.letsfame.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
@EnableWebMvc
public class AppConfig  {

	@Value("${razorpay.key.id}")
	private String keyId;
	@Value("${razorpay.key.secret}")
	private String keySecret;

	@Bean
	public RazorpayClient razorpayClient() throws RazorpayException {
		return new RazorpayClient(keyId, keySecret);

	}

//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		final ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//		//objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//		converter.setObjectMapper(objectMapper);
//		converters.add(converter);
//		WebMvcConfigurer.super.configureMessageConverters(converters);
//
//	}
//	@Bean
//	public ObjectMapper objectMapper() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
//		return objectMapper;
//	}
//	@Bean
//	@ConditionalOnMissingBean(AbstractJackson2HttpMessageConverter.class)
//	public ObjectMapper getMapper() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//		return objectMapper;
//	}
}