package only.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import only.dao.AlertDao;
import only.model.Alert;

@Service
public class AlertServiceImpl implements AlertService {
	@Autowired
	private AlertDao ad;
	@Override
	public int insert(Alert alert) {
		return ad.insert(alert);
	}
	@Override
	public int uncheckedAlert(String userid) {
		return ad.uncheckedAlert(userid);
	}
	@Override
	public List<Alert> getAlertList(String userid) {
		return ad.getAlertList(userid);
	}
	@Override
	public int checkNotification(int aid) {
		return ad.checkNotification(aid);
	}

}
