package gr.hua.group10.dao;

import java.util.List;

import gr.hua.group10.entities.Users;

public interface UsersDAO {

	public List<Users> getUsers();
	
	public Users getUser(String username);
	
	public int createUser(Users user);
	
	public List<Users> getUsersByUsername(String username);

	public Users getUserByID(int id);
	
	public Users updateUser(Users users, int id);
	
	public Users deleteUser(int id);
	
	public List<Users> getUsersByRole(String role);
}
