package com.liuga.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.liuga.state.DisabledFilesState;

import java.util.ArrayList;
import java.util.List;

public class DisableFileAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project!= null) {
            List<VirtualFile> selectedFiles = getSelectedFiles(e);
            if (selectedFiles!= null) {
                for (VirtualFile file : selectedFiles) {
                    String filePath = file.getPath().replace(project.getBasePath() + "/", "");
                    DisabledFilesState state = DisabledFilesState.getInstance();
                    if (state.disabledFiles.contains(filePath)) {
                        state.disabledFiles.remove(filePath);
                    } else {
                        state.disabledFiles.add(filePath);
                    }
                }
            }
        }
        System.out.println("禁用文件："+DisabledFilesState.getInstance().disabledFiles);
    }

    private List<VirtualFile> getSelectedFiles(AnActionEvent e) {
        List<VirtualFile> selectedFiles = new ArrayList<>();
        VirtualFile[] virtualFiles = e.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY);
        if (virtualFiles!= null) {
            for (VirtualFile file : virtualFiles) {
                selectedFiles.add(file);
            }
        }
        return selectedFiles;
    }

    @Override
    public void update(AnActionEvent e) {
        List<VirtualFile> selectedFiles = getSelectedFiles(e);
        e.getPresentation().setEnabledAndVisible(selectedFiles!= null &&!selectedFiles.isEmpty());
    }
}