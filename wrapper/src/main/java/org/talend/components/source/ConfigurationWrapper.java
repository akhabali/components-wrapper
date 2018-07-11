package org.talend.components.source;

import java.io.Serializable;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.condition.ActiveIf;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;

import lombok.Data;

@Data
@GridLayout({
        @GridLayout.Row("type"),
        @GridLayout.Row("config1"),
        @GridLayout.Row("config2")
})
@Documentation("TODO fill the documentation for this configuration")
public class ConfigurationWrapper implements Serializable {

    @Option
    @Documentation("")
    private ComponentType type;

    @Option
    @ActiveIf(target = "type", value = "Input1")
    @Documentation("")
    private Configuration1 config1;

    @Option
    @ActiveIf(target = "type", value = "Input2")
    @Documentation("")
    private Configuration2 config2;

    public enum ComponentType {
        Input1,
        Input2;
    }
}