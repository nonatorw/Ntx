package org.example.repositories.load;

import java.util.List;

public interface LoadRepository<T> {
    List<T> load();
}
