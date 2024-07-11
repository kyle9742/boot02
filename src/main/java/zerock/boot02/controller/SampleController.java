package zerock.boot02.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
public class SampleController {

	@GetMapping("/hello")
	public String hello(Model model){
		model.addAttribute("msg", "Spring Boot - Hello");
		return "hello";
	}

	@GetMapping("/ex/ex1")
	public void ex1(Model model){
		String str = "ABCDEFG";
		List<String> list = Arrays.asList("AAA","BBB","CCC","DDD");
		model.addAttribute("str", str);
		model.addAttribute("list", list);
	}
}
