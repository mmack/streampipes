package de.fzi.cep.sepa.model.util;

import java.util.List;
import java.util.function.Supplier;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;





import de.fzi.cep.sepa.model.impl.TransportProtocol;
import de.fzi.cep.sepa.model.impl.eventproperty.EventProperty;
import de.fzi.cep.sepa.model.impl.output.OutputStrategy;
import de.fzi.cep.sepa.model.impl.quality.EventPropertyQualityDefinition;
import de.fzi.cep.sepa.model.impl.quality.MeasurementProperty;
import de.fzi.cep.sepa.model.impl.staticproperty.MappingProperty;
import de.fzi.cep.sepa.model.impl.staticproperty.StaticProperty;

public class GsonSerializer {

	public static Gson getGson()
	{
		GsonBuilder builder = new GsonBuilder();
//		builder.registerTypeAdapter(SepaDescription.class, new JsonLdSerializer());
		builder.registerTypeAdapter(EventProperty.class, new JsonLdSerializer<EventProperty>());
		builder.registerTypeAdapter(StaticProperty.class, new JsonLdSerializer<StaticProperty>());
		builder.registerTypeAdapter(OutputStrategy.class, new JsonLdSerializer<OutputStrategy>());
		builder.registerTypeAdapter(TransportProtocol.class, new JsonLdSerializer<TransportProtocol>());
		//builder.registerTypeAdapter(Operation.class, new JsonLdSerializer<Operation>());
		builder.setPrettyPrinting();
		return builder.create();
	}

	public static GsonBuilder getGsonBuilder()
	{
		GsonBuilder builder = new GsonBuilder();
//		builder.registerTypeAdapter(SepaDescription.class, new JsonLdSerializer());
		builder.registerTypeAdapter(EventProperty.class, new JsonLdSerializer<EventProperty>());
		builder.registerTypeAdapter(StaticProperty.class, new JsonLdSerializer<StaticProperty>());
		builder.registerTypeAdapter(OutputStrategy.class, new JsonLdSerializer<OutputStrategy>());
		builder.registerTypeAdapter(TransportProtocol.class, new JsonLdSerializer<TransportProtocol>());
		builder.registerTypeAdapter(EventPropertyQualityDefinition.class, new JsonLdSerializer<EventPropertyQualityDefinition>());
		builder.registerTypeAdapter(MeasurementProperty.class, new JsonLdSerializer<MeasurementProperty>());
		builder.registerTypeAdapter(MappingProperty.class, new JsonLdSerializer<MappingProperty>());
		
		builder.setPrettyPrinting();
		return builder;
	}
	
	public static Gson getGsonWithIds() {
		return getGsonBuilder().create();
	}
	
	public static Gson getGsonWithoutIds() {
		GsonBuilder builder = getGsonBuilder();
		
		builder.addSerializationExclusionStrategy(new ExclusionStrategy() {
			
			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				if (f.getName().equals("elementName")) return true;
				return false;
			}
			
			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		return builder.create();
	}
		
}
