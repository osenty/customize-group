package org.example.customization.customizer.dao;

import lombok.NonNull;

import java.util.UUID;

public interface CustomizerDao<T> {
    void saveCustomization(@NonNull UUID uuid, @NonNull T value);
    T getCustomization(@NonNull UUID uuid);
    void deleteCustomization(@NonNull UUID uuid);


}
