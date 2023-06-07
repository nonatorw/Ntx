package org.example.repositories.write;

import java.util.List;

public interface WriteRepository<T> {
    void save(List<T> lines);
}
