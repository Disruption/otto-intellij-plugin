package com.squareup.ideaplugin.otto;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectUtil;

import javax.swing.*;

public class BusClassConfigurable implements Configurable {
    public final static String STORED_CLASS_KEY = "SelectedClass";
    private JTextField selectedCustomClass;
    private JPanel componentPanel;

    public String getDisplayName() {
        return "Otto Bus Plugin";
    }

    public boolean isModified() {
        return true;
    }

    public JComponent createComponent() {
        String storedClass = PropertiesComponent.getInstance(ProjectUtil.guessCurrentProject(null)).getValue(STORED_CLASS_KEY);
        if (storedClass != null) {
            selectedCustomClass.setText(storedClass);
        }
        return componentPanel;
    }

    public void apply() {
        String customClass = selectedCustomClass.getText();
        PropertiesComponent.getInstance(ProjectUtil.guessCurrentProject(null)).setValue(STORED_CLASS_KEY, customClass);
        SubscriberMetadata.setCustomBusClass(customClass);
    }

    public void disposeUIResources() {
        // Do nothing
    }

    public String getHelpTopic() {
        return null;
    }

    public void reset() {

    }
}
