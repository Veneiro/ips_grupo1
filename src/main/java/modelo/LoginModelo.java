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

    public void setPassword(String password) {
	String sql = "UPDATE TUSERS SET PASSWORD = ?";
	db.executeUpdate(sql, password);
    }

    public void setExpiration(String expiration) {
	String sql = "UPDATE TUSERS SET EXPIRATION = ?";
	db.executeUpdate(sql, expiration);
    }

    public void expire(int id) {
	String sql = "UPDATE TUSERS SET PASSWORD = NULL, EXPIRATION = NULL WHERE ID = ?";
	db.executeUpdate(sql, id);
    }
}
