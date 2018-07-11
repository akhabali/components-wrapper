package org.talend.components.source;

import java.io.Serializable;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;

import lombok.Data;

@Data
@GridLayout({@GridLayout.Row("option2")})
@Documentation("TODO fill the documentation for this configuration")
public class Configuration2 implements Serializable {

    @Option
    @Documentation("")
    private String option2;
}