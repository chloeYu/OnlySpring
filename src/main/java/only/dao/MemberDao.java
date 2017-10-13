package only.dao;

import java.util.List;

import only.model.Member;
import only.model.Post;

public interface MemberDao {
	Member getMemberById(String member_id);

	List<Member> searchMember(String searchTerm, String userid);

	int insert(Member member);

	int update(Member member);

	int updateThumbProfile(String userid, String url);

}
