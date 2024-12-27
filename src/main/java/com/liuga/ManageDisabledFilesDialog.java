package com.liuga;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManageDisabledFilesDialog extends DialogWrapper {
    private final JBList<String> fileList;
    private final DefaultListModel<String> listModel;
    private final DisabledFilesState state;

    public ManageDisabledFilesDialog(Project project) {
        super(project);
        state = DisabledFilesState.getInstance();
        listModel = new DefaultListModel<>();
        for (String file : state.disabledFiles) {
            listModel.addElement(file);
        }
        fileList = new JBList<>(listModel);
        fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        setTitle("Manage Disabled Files");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(500, 300));

        // Add list with scroll
        JBScrollPane scrollPane = new JBScrollPane(fileList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add remove button
        JButton removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(e -> removeSelectedFiles());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(removeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void removeSelectedFiles() {
        List<String> selectedFiles = fileList.getSelectedValuesList();
        List<String> filesToRemove = new ArrayList<>(selectedFiles);
        
        for (String file : filesToRemove) {
            listModel.removeElement(file);
            state.disabledFiles.remove(file);
        }
    }

    @Override
    protected void doOKAction() {
        state.loadState(state); // Save changes
        super.doOKAction();
    }
}
