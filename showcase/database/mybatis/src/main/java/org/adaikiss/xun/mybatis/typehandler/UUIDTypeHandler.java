/**
 * 
 */
package org.adaikiss.xun.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * @author hlw
 *
 */
public class UUIDTypeHandler extends BaseTypeHandler<UUID> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			UUID parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.toString());
	}

	@Override
	public UUID getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return UUID.fromString(rs.getString(columnName));
	}

	@Override
	public UUID getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return UUID.fromString(rs.getString(columnIndex));
	}

	@Override
	public UUID getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return UUID.fromString(cs.getString(columnIndex));
	}

}
