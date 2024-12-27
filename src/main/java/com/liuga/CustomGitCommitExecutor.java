package com.liuga;

import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.vcs.changes.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class CustomGitCommitExecutor implements CommitExecutor {

    @Override
    public String getActionText() {
        return "Custom Git Commit";
    }

    @Override
    public String getId() {
        return "CUSTOM_GIT_COMMIT_EXECUTOR";
    }


    @Override
    public @NotNull CommitSession createCommitSession(@NotNull CommitContext commitContext) {
        return new CustomerCommitSeesion();
    }


    private class CustomerCommitSeesion implements CommitSession {

        @Override
        public void execute(@NotNull Collection<? extends Change> changes, @Nullable @NlsSafe String commitMessage) {

        }
    }

    }