package db.domain.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import db.domain.Cabin;

public class CabinMapper implements RowMapper<Cabin> {

	public Cabin mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Cabin cabin = new Cabin();
		cabin.setBedCount(resultSet.getInt("bedCount"));
		cabin.setDeckLevel(resultSet.getInt("deckLevel"));
		cabin.setId(resultSet.getInt("id"));
		cabin.setName(resultSet.getString("name"));
		cabin.setShipId(resultSet.getInt("shipId"));
		return null;
	}

}
