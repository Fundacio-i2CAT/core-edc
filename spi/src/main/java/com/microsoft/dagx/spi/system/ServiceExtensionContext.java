package com.microsoft.dagx.spi.system;

import com.microsoft.dagx.spi.monitor.Monitor;
import com.microsoft.dagx.spi.types.TypeManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Context provided to extensions when they are initialized.
 */
public interface ServiceExtensionContext {

    /**
     * Returns the system monitor.
     */
    Monitor getMonitor();

    /**
     * Returns the type manager.
     */
    TypeManager getTypeManager();

    /**
     * Returns the configuration value, or the default value if not found.
     */
    String getSetting(String setting, String defaultValue);

    /**
     * Returns true if the service type is registered.
     */
    <T> boolean hasService(Class<T> type);

    /**
     * Returns a system service.
     */
    <T> T getService(Class<T> type);

    /**
     * Registers a service
     */
    default <T> void registerService(Class<T> type, T service) {
    }

    /**
     * Loads and orders the service extensions.
     */
    List<ServiceExtension> loadServiceExtensions();

    /**
     * Loads multiple extensions, raising an exception if at least one is not found.
     */
    <T> List<T> loadExtensions(Class<T> type, boolean required);

    /**
     * Loads a single extension, raising an exception if one is not found.
     */
    @Nullable()
    @Contract("_, true -> !null")
    <T> T loadSingletonExtension(Class<T> type, boolean required);

}
