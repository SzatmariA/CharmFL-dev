package ui;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import modules.ProjectModule;

import com.intellij.ui.jcef.*;
import services.Resources;

public class SunBurstView extends DialogWrapper {

    public SunBurstView() {
        super(true);
        setTitle(Resources.get("titles", "sun_burst_title"));
        setModal(false);
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        String url = ProjectModule.getProjectPath() + "\\sunburst.html";
        return new JBCefBrowser(url).getComponent();

    }
}