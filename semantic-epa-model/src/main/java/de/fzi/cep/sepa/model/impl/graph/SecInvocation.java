package de.fzi.cep.sepa.model.impl.graph;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfsClass;

import de.fzi.cep.sepa.model.InvocableSEPAElement;
import de.fzi.cep.sepa.model.impl.EventStream;
import de.fzi.cep.sepa.model.impl.staticproperty.StaticProperty;

@Namespaces({"sepa", "http://sepa.event-processing.org/sepa#",
	 "dc",   "http://purl.org/dc/terms/"})
@RdfsClass("sepa:SECInvocationGraph")
@Entity
public class SecInvocation extends InvocableSEPAElement{

	private static final long serialVersionUID = -2345635798917416757L;
		
	public SecInvocation(SecDescription sec)
	{
		super();
		this.setName(sec.getName());
		this.setDescription(sec.getDescription());
		this.setIconUrl(sec.getIconUrl());
		this.setInputStreams(sec.getEventStreams());
		this.setSupportedGrounding(sec.getSupportedGrounding());
		this.setStaticProperties(sec.getStaticProperties());
		this.setBelongsTo(sec.getRdfId().toString());
		this.setUri(belongsTo +"/" +getElementId());
	}
	
	public SecInvocation(SecDescription sec, String domId)
	{
		this(sec);
		this.setDomId(domId);
	}
	
	public SecInvocation()
	{
		super();
		inputStreams = new ArrayList<EventStream>();
	}
	
	public List<StaticProperty> getStaticProperties() {
		return staticProperties;
	}

	public void setStaticProperties(List<StaticProperty> staticProperties) {
		this.staticProperties = staticProperties;
	}
	
}
