package com.test.avro_cloudevents;

import java.util.function.UnaryOperator;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.Test;

@ApplicationScoped
public class TestFunction implements UnaryOperator<Test>
{
	private static final Logger LOG = LoggerFactory.getLogger(TestFunction.class);

	@Override
	public Test apply(Test translatableNotification)
	{
		LOG.info("t: {}", translatableNotification.getT());
		LOG.info("e: {}", translatableNotification.getE());
		Test hi = Test.newBuilder().setT("hi").setE(Integer.MAX_VALUE).build();
		LOG.info("new values: {} {}", hi.getT(), hi.getE());
		return hi;
	}
}