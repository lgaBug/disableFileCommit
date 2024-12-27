package com.liuga;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class DemoTool extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getProject();
        if (project != null) {
            Messages.showMessageDialog("hhh", project.getName(), Messages.getInformationIcon());
        }
    }
}
