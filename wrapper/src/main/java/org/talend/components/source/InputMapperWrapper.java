package org.talend.components.source;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.talend.sdk.component.api.component.Icon;
import org.talend.sdk.component.api.component.Version;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.input.Assessor;
import org.talend.sdk.component.api.input.Emitter;
import org.talend.sdk.component.api.input.PartitionMapper;
import org.talend.sdk.component.api.input.PartitionSize;
import org.talend.sdk.component.api.input.Split;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.junit.SimpleFactory;
import org.talend.sdk.component.runtime.input.Mapper;
import org.talend.sdk.component.runtime.manager.ComponentManager;

//
// this class role is to enable the work to be distributed in environments supporting it.
//
@Version(1) // default version is 1, if some configuration changes happen between 2 versions you can add a migrationHandler
@Icon(Icon.IconType.STAR) // you can use a custom one using @Icon(value=CUSTOM, custom="filename") and adding icons/filename_icon32.png in resources
@PartitionMapper(name = "InputWrapper")
@Documentation("TODO fill the documentation for this mapper")
public class InputMapperWrapper implements Serializable {

    private ConfigurationWrapper configuration;

    private ComponentManager manager;

    private Mapper mapper;

    public InputMapperWrapper(@Option("configuration") final ConfigurationWrapper configuration) {
        this.configuration = configuration;
        this.manager = ComponentManager.instance();
    }

    private InputMapperWrapper(final Mapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    public void post() {
        switch (configuration.getType()) {
        case Input1:
            mapper = manager.findMapper("family1", "Input1", 0, SimpleFactory.configurationByExample(configuration.getConfig1())).get();
            break;
        case Input2:
            mapper = manager.findMapper("family2", "Input2", 0, SimpleFactory.configurationByExample(configuration.getConfig2())).get();
            break;
        }
    }

    @Assessor
    public long estimateSize() {
        return mapper.assess();
    }

    @Split
    public List<InputMapperWrapper> split(@PartitionSize final long bundles) {
        return mapper.split(bundles).stream().map(InputMapperWrapper::new).collect(toList());
    }

    @Emitter
    public SourceWrapper createWorker() {
        return new SourceWrapper(mapper.create());
    }
}