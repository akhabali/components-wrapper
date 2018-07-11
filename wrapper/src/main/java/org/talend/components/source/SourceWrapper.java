package org.talend.components.source;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.talend.sdk.component.api.input.Producer;
import org.talend.sdk.component.runtime.input.Input;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SourceWrapper implements Serializable {

    private final Input input;

    @PostConstruct
    public void init() {
        input.start();
    }

    @PreDestroy
    public void release() {
        input.stop();
    }

    @Producer
    public Object next() {
        return input.next();
    }

}
