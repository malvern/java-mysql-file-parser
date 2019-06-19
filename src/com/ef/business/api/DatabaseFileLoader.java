package com.ef.business.api;

import java.nio.file.Path;
import java.util.Optional;

@FunctionalInterface
public interface DatabaseFileLoader {
    int loadFileToDatabase(Optional<String> filePath);
}
