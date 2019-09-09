package su.medsoft.mis.er.terminal.services.interfaces;

import su.medsoft.mis.er.terminal.database.QueryParam;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

public interface DatabaseService {
    <T> List<T> executeSelectQuery(String sql, Function<ResultSet, T> mapper, QueryParam...params);

    <T> T executeSelectQueryWithAllItems(String sql, Function<ResultSet, T> mapper, QueryParam...params);

    <T> T executeSelectQueryWithAllItemsWithoutParams(String sql, Function<ResultSet, T> mapper);
}
