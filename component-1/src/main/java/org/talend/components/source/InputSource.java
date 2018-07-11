package org.talend.components.source;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import org.talend.components.service.Company_componentService;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.input.Producer;
import org.talend.sdk.component.api.meta.Documentation;

@Documentation("TODO fill the documentation for this source")
public class InputSource implements Serializable {

    private final Configuration1 configuration;

    private final Company_componentService service;

    private final JsonBuilderFactory jsonBuilderFactory;

    private final Queue<String> data = new LinkedList<String>() {{
        add("source1 1");
        add("source1 2");
        add("source1 3");
    }};

    public InputSource(@Option("configuration") final Configuration1 configuration,
            final Company_componentService service,
            final JsonBuilderFactory jsonBuilderFactory) {
        this.configuration = configuration;
        this.service = service;
        this.jsonBuilderFactory = jsonBuilderFactory;
    }

    @Producer
    public JsonObject next() {
        return data.size() == 0 ? null : jsonBuilderFactory.createObjectBuilder().add("data", data.poll()).build();
    }

}