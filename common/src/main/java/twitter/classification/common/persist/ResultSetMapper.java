package twitter.classification.common.persist;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generic mapper to map database result sets to a Java object
 *
 * Reference: https://oprsteny.com/?p=900
 *
 * @param <T>
 */
public class ResultSetMapper<T> {

  public static final Logger logger = LoggerFactory.getLogger(ResultSetMapper.class);

  public List<T> mapResultSetToClass(ResultSet resultSet, Class classToMap) {

    List<T> outputList = new ArrayList<>();

    try {

      if (resultSet != null) {

        // allows access to the database column names
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        while (resultSet.next()) {

          T mappedClass = (T) classToMap.newInstance();

          for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {

            String columnName = resultSetMetaData.getColumnName(i);
            
            Object columnValue = resultSet.getObject(i);

            for (Field field : classToMap.getDeclaredFields()) {

              // only want to map the fields which are related to database results
              if (field.isAnnotationPresent(Column.class)) {

                Column column = field.getAnnotation(Column.class);

                if (column.name().equalsIgnoreCase(columnName) && columnValue != null) {

                  setProperty(mappedClass, field.getName(), columnValue);
                  break;
                }
              }
            }
          }

          outputList.add(mappedClass);
        }

      } else {

        return null;
      }
    } catch (IllegalAccessException | InstantiationException | SQLException e) {

      logger.error("Issue mapping result set to object", e);
    }

    return outputList;
  }

  /**
   * Method to set the field properties of the class
   *
   * @param clazz
   * @param fieldName
   * @param columnValue
   */
  private void setProperty(Object clazz, String fieldName, Object columnValue) {
    try {

      Field field = clazz.getClass().getDeclaredField(fieldName);
      // as the fields are private need to alter it
      field.setAccessible(true);
      field.set(clazz, columnValue);
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {

      logger.error("Issue setting field properties", e);
    }
  }
}
