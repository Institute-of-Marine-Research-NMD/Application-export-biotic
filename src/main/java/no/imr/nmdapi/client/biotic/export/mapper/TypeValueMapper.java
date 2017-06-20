package no.imr.nmdapi.client.biotic.export.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.biotic.export.pojo.TypeValue;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class TypeValueMapper implements RowMapper<TypeValue> {

    private String typeName;
    private String valueName;

    public TypeValueMapper(String typeName, String valueName) {
        this.typeName = typeName;
        this.valueName = valueName;
    }

    @Override
    public TypeValue mapRow(ResultSet rs, int rowNum) throws SQLException {
        TypeValue platform = new TypeValue();

        platform.setType(rs.getString(typeName));
        platform.setValue(rs.getString(valueName));

        return platform;
    }

}
