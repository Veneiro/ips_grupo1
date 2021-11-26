package modelo;

import java.util.List;

import dtos.UserDto;
import util.Database;

public class LoginModelo {

    private Database db = new Database();

    public List<UserDto> findAll() {
	String sql = "SELECT * FROM TUSERS";
	return db.executeQueryPojo(UserDto.class, sql);
    }

    public List<UserDto> findUser(String userName) {
	String sql = "SELECT * FROM TUSERS WHERE USER = ?";
	return db.executeQueryPojo(UserDto.class, sql, userName);
    }

    public List<UserDto> findUserByEmail(String email) {
	String sql = "SELECT * FROM TUSERS WHERE EMAIL = ?";
	return db.executeQueryPojo(UserDto.class, sql, email);
    }

    public void setPassword(int id, String password) {
	String sql = "UPDATE TUSERS SET PASSWORD = ? WHERE ID = ?";
	db.executeUpdate(sql, password, id);
    }

    public void setExpiration(int id, String expiration) {
	String sql = "UPDATE TUSERS SET EXPIRATION = ? WHERE ID = ?";
	db.executeUpdate(sql, expiration, id);
    }

    public void expire(int id) {
	String sql = "UPDATE TUSERS SET PASSWORD = NULL, EXPIRATION = NULL WHERE ID = ?";
	db.executeUpdate(sql, id);
    }
}
