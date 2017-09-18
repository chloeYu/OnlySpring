package only.dao;

import java.util.List;

import only.model.Member;

public interface MemberDao {
	Member getMemberById(String member_id);

	List<Member> searchMember(String searchTerm);

}
