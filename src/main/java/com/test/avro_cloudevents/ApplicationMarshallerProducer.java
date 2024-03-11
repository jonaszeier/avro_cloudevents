package com.test.avro_cloudevents;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.kie.kogito.addon.quarkus.messaging.common.ChannelFormat;
import org.kie.kogito.event.CloudEventMarshaller;
import org.kie.kogito.event.CloudEventUnmarshallerFactory;
import org.kie.kogito.event.avro.AvroCloudEventMarshaller;
import org.kie.kogito.event.avro.AvroCloudEventUnmarshallerFactory;
import org.kie.kogito.event.avro.AvroIO;

@ApplicationScoped
public class ApplicationMarshallerProducer
{
	private AvroIO avroIO;

	@PostConstruct
	void init() throws IOException
	{
		avroIO = new AvroIO();
	}

	@Produces
	@Named("incoming")
	@ChannelFormat
	public CloudEventUnmarshallerFactory<byte[]> getAvroCloudEventUnmarshallerFactory()
	{
		return new AvroCloudEventUnmarshallerFactory(avroIO);
	}

	@Produces
	@Named("outgoing")
	@ChannelFormat
	public CloudEventMarshaller<byte[]> getAvroCloudEventMarshaller()
	{
		return new AvroCloudEventMarshaller(avroIO);
	}
}