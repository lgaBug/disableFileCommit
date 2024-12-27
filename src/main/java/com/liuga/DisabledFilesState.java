package com.liuga;

import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Service({Service.Level.APP})
@State(
        name = "DisabledFilesState",
        storages = @Storage("disabledFiles.xml")
)
public final class DisabledFilesState implements PersistentStateComponent<DisabledFilesState> {

    public List<String> disabledFiles = new ArrayList<>();

    public static DisabledFilesState getInstance() {
        return ServiceManager.getService(DisabledFilesState.class);
    }

    @Nullable
    @Override
    public DisabledFilesState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull DisabledFilesState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}