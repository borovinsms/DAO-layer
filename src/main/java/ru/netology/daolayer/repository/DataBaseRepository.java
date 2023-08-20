package ru.netology.daolayer.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class DataBaseRepository implements CommandLineRunner, ru.netology.daolayer.repository.Repository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String querySql;

    public DataBaseRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.querySql = read("query.sql");
    }

    @Override
    public void run(String... args) throws Exception {
    }

    @Override
    public List<String> getProductName(String name) {
//        final var products = new ArrayList<String>();
        final var products = namedParameterJdbcTemplate.query(querySql,
                Map.of("name", name),
                (rs, rowNum) -> rs.getString("product_name")
        );
        return products;
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
