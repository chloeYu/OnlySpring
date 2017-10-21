package only.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import only.model.Member;
import only.model.Post;

public interface MemberDao {
	Member getMemberById(String member_id);

	List<Member> searchMember(String searchTerm, String userid);

	int insert(Member member);

	int update(Member member);

	int updateThumbProfile(String userid, String url);

	int updateCoverProfile(String userid, String url);

	UserDetails getUserDetails(String userid);

	List<Member> getLoggedInMembers(String userid);

}
