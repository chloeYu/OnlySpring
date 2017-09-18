package only.service;

import java.util.List;

import only.model.Member;

public interface MemberService {
	Member getMemberById(String member_id);

	List<Member> searchMember(String searchTerm);
}
