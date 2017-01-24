package appBuyAndSale;

public class User
{
	String firstName, lastName;
	String username;
	String password;
	String email;
	int type;
	User (String fN, String lN , String u, String p, String e, int t)
	{
		this.firstName = fN;
		this.lastName = lN;
		this.username = u;
		this.password = p;
		this.email = e;
		this.type = t;
	}
	public void changePassword(String p)
	{
		this.password = p;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public String getEmail()
	{
		return email;
	}
	public int getType()
	{
		return type;
	}
	

}
