package com.liuga;

import com.intellij.openapi.vcs.changes.CommitExecutor;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
import com.intellij.util.PairConsumer;
import org.jetbrains.annotations.Nullable;

public class CustomVcsBeforeCheckinProvider extends CheckinHandler {

    @Override
    public @Nullable ReturnResult beforeCheckin(@Nullable CommitExecutor executor, PairConsumer<Object, Object> additionalDataConsumer) {
        return super.beforeCheckin(executor, additionalDataConsumer);

    }
}