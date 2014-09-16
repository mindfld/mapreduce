package ua.mind.mapreduce.client.jobs;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.UriBuilder;
import java.util.concurrent.Callable;

/**
 * Created by mind on 16.09.14.
 * Parent job(thread) for invoking rest requests
 */
public abstract class ServiceJob implements Callable<Double>{

    private float hostSum;
    private String host;
    protected WebResource service;

    protected final String REST_PATH="/rest";

    public ServiceJob(String host) {
        this.host = host;
        ClientConfig config = new DefaultClientConfig(GensonJsonConverter.class);
        Client client = Client.create(config);
        service = client.resource(UriBuilder.fromUri(host).build());
    }

}
