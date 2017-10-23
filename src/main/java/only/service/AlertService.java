package only.service;

import java.util.List;

import only.model.Alert;

public interface AlertService {

	int insert(Alert alert);

	int uncheckedAlert(String userid);

	List<Alert> getAlertList(String userid);

	int checkNotification(int aid);

}
