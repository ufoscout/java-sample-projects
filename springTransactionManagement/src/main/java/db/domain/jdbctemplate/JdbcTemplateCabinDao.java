package db.domain.jdbctemplate;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import db.domain.Cabin;
import db.domain.ICabinDao;

public class JdbcTemplateCabinDao extends SimpleJdbcDaoSupport implements ICabinDao {

	public void insert(Cabin cabin) {
		getSimpleJdbcTemplate().update("insert into cabin (BedCount, DeckLevel, id, name, shipId) values (?, ?, ?, ?, ?)",
                         new Object[] { cabin.getBedCount(), cabin.getDeckLevel(), cabin.getId(), cabin.getName(), cabin.getShipId() });
	}

	public void update(Cabin cabin) {
		getSimpleJdbcTemplate().update("update cabin set BedCount=?, DeckLevel=?, name=?, shipId=? where id=?)",
                new Object[] { cabin.getBedCount(), cabin.getDeckLevel(), cabin.getName(), cabin.getShipId() , cabin.getId()});
	}

	public void delete(int id) {
		getSimpleJdbcTemplate().update("delete from cabin where id=?", new Object[] { id });
	}

	public Cabin getById(int id) {
		return getSimpleJdbcTemplate().queryForObject("select * from cabin where id = ?", new CabinMapper(), new Object[] { id });
	}
	
	public boolean existCabin(int id) {
		return (getSimpleJdbcTemplate().queryForInt("select count(*) from cabin where id = ?", new Object[] { id })>0);
	}

	public List<Cabin> getAllCabin() {
		return getSimpleJdbcTemplate().query("select * from cabin", new CabinMapper());
	}

	public int getMaxId() {
		return getSimpleJdbcTemplate().queryForInt("select max(id) from cabin");
	}

}
