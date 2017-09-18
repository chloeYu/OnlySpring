package only.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OnlyController {
	@RequestMapping("/chat")
	public String chat() {
		return "chat";
	}

}
