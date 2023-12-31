package ru.itis.socketserver.repository;

import ru.itis.socketserver.model.Video;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class VideoRepositoryImpl implements VideoRepository {
    //language=sql
    private final static String SAVE_SQL = "INSERT INTO video " +
            "(uri, name, likes_amount, dislikes_amount) VALUES (%s, %s, %d, %d)";
    //language=sql
    private final static String FIND_BY_NAME_SQL = "SELECT * FROM video WHERE name = '%s'";
    //language=sql
    private final static String FIND_BY_URI_SQL = "SELECT * FROM video WHERE uri = '%s'";
    //language=sql
    private final static String FIND_BY_ID_SQL = "SELECT * FROM video WHERE id = '%s'";

    //language=sql
    private final static String UPDATE_SQL =
            "UPDATE video SET likes_amount = %d, dislikes_amount = %d WHERE uri = '%s'";
    //language=sql
    private final static String GET_PAGE = "SELECT * FROM video ORDER BY likes_amount LIMIT %d OFFSET %d";
    private final DataSource dataSource;

    public VideoRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Video video) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format(
                    SAVE_SQL, video.getUri(), video.getName(), video.getLikesAmount(), video.getDislikesAmount())
            );
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Optional<Video> findByName(String name) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(FIND_BY_NAME_SQL, name));

            if (resultSet.next()) {
                Video result = mapper(resultSet);
                resultSet.close();
                return Optional.of(result);
            } else {
                resultSet.close();
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Video> findByUri(String uri) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(FIND_BY_URI_SQL, uri));

            if (resultSet.next()) {
                Video result = mapper(resultSet);
                resultSet.close();
                return Optional.of(result);
            } else {
                resultSet.close();
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // useless
    @Override
    public Optional<Video> findById(UUID id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(FIND_BY_ID_SQL, id.toString()));

            if (resultSet.next()) {
                Video result = mapper(resultSet);
                resultSet.close();
                return Optional.of(result);
            } else {
                resultSet.close();
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Video video) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format(
                    UPDATE_SQL, video.getLikesAmount(), video.getDislikesAmount(), video.getUri())
            );
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Video> getPage(int pageNumber, int pageSize) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(GET_PAGE, pageSize, pageNumber*pageSize));
            List<Video> videos = new ArrayList<>();
            while (resultSet.next()) {
                videos.add(mapper(resultSet));
            }

            return videos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Video mapper(ResultSet resultSet) throws SQLException {
        return Video.builder()
                .uri(resultSet.getString("uri"))
                .name(resultSet.getString("name"))
                .likesAmount(resultSet.getInt("likes_amount"))
                .dislikesAmount(resultSet.getInt("dislikes_amount"))
                .build();
    }
}
