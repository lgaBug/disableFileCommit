package com.liuga;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ManageDisabledFilesAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project != null) {
            ManageDisabledFilesDialog dialog = new ManageDisabledFilesDialog(project);
            dialog.show();
        }
    }
}
