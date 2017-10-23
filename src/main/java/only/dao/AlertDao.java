package only.dao;

import java.util.List;

import only.model.Alert;

public interface AlertDao {

	int insert(Alert alert);

	int uncheckedAlert(String userid);

	List<Alert> getAlertList(String userid);

	int checkNotification(int aid);

}
