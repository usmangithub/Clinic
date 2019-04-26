package com.veterinaryclinic.dagger;

import com.veterinaryclinic.TestApplication;
import com.veterinaryclinic.dagger.application.ApplicationComponent;

public final class ComponentFactory {
    private ComponentFactory() {
    }

    public static ApplicationComponent createApplicationComponent(
            final TestApplication testApplication) {
        return ApplicationComponent.Initializer.init(testApplication);
    }
}
