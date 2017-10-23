package only.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import only.model.Alert;

@Repository
public class AlertDaoImpl implements AlertDao {
	@Autowired
	private SqlSessionTemplate sst;
	
	@Override
	public int insert(Alert alert) {
		return sst.insert("alertns.insert", alert);
	}

	@Override
	public int uncheckedAlert(String userid) {
		return sst.selectOne("alertns.uncheckedAlerts", userid);
	}

	@Override
	public List<Alert> getAlertList(String userid) {
		return sst.selectList("alertns.alertList", userid, new RowBounds(0, 10));
	}

	@Override
	public int checkNotification(int aid) {
		return sst.update("alertns.checkNotification", aid);
	}

}
