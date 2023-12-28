package ru.itis.socketserver.repository;

import ru.itis.socketserver.model.Video;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoRepository {
    void save(Video video) throws SQLException;

    Optional<Video> findByName(String name) throws SQLException;

    Optional<Video> findByUri(String uri) throws SQLException;

    Optional<Video> findById(UUID id) throws SQLException;

    void update(Video video) throws SQLException;

    List<Video> getPage(int pageNumber, int pageSize) throws SQLException;
}
