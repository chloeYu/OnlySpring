package only.model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	private String name;
	private List<String> privileges;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}

	public List<String> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}

}
