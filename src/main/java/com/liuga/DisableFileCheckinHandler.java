package com.liuga;

import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.changes.Change;
import com.intellij.openapi.vcs.changes.ContentRevision;
import com.intellij.openapi.vcs.checkin.CheckinHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DisableFileCheckinHandler extends CheckinHandler {
    private final CheckinProjectPanel myPanel;

    public DisableFileCheckinHandler(CheckinProjectPanel panel) {
        myPanel = panel;
    }

    @Override
    public ReturnResult beforeCheckin() {
        Collection<Change> changes = myPanel.getSelectedChanges();
        List<Change> filteredChanges = new ArrayList<>();
        DisabledFilesState state = DisabledFilesState.getInstance();

        for (Change change : changes) {
            ContentRevision afterRevision = change.getAfterRevision();
            ContentRevision beforeRevision = change.getBeforeRevision();

            boolean isDisabled = false;
            if (afterRevision != null && state.disabledFiles.contains(afterRevision.getFile().getPath())) {
                isDisabled = true;
            }
            if (beforeRevision != null && state.disabledFiles.contains(beforeRevision.getFile().getPath())) {
                isDisabled = true;
            }

            if (!isDisabled) {
                filteredChanges.add(change);
            }
        }

        // If we found disabled files, update the selection
        if (filteredChanges.size() < changes.size()) {
            StringBuilder message = new StringBuilder("");
            message.append("\nDo you want to continue with the commit?");
            int result = Messages.showYesNoDialog(
                message.toString(),
                "Files Existed in Disabled List",
                "Continue",
                "Cancel",
                Messages.getWarningIcon()
            );

            return result == Messages.YES ? ReturnResult.COMMIT : ReturnResult.CANCEL;
        }
        return ReturnResult.COMMIT;
    }

}