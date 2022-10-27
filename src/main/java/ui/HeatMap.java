package ui;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import modules.ProjectModule;

import com.intellij.ui.jcef.*;
import services.Resources;

public class HeatMap extends DialogWrapper{
    public HeatMap() {
        super(true);
        setTitle(Resources.get("titles", "heatmap_title"));
        setModal(false);
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        System.out.println(ProjectModule.getProjectPath());
        String url = "C:\\Users\\ratlev\\Desktop\\CharmFL-dev\\test_project\\products\\heat.html";
        return new JBCefBrowser(url).getComponent();

    }
}
