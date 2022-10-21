package actions;

import services.Resources;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.DumbAwareAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import modules.PluginModule;
import services.runnables.RunCallGraphRunnable;
import ui.*;

import java.lang.Thread;

public class PluginSunBurst extends DumbAwareAction {
    /**
     * When you click the Start Sun Burst menu item, then this method will generate a sunburst.
     * @param e An event
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        String mainFileName = JOptionPane.showInputDialog(Resources.get("errors", "enter_main_file_message"));
        ProgressManager.getInstance().run(new RunCallGraphRunnable(e.getProject(), PluginModule.getPluginName(),
                FileEditorManager.getInstance(e.getProject()).getSelectedTextEditor(), mainFileName));

        new SunBurstView().show();
    }
}