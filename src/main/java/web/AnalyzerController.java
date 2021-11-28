package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class AnalyzerController {

    @GetMapping("/")
    public String helloGradleGet(Model model) {
        return "analyzer";
    }

    @PostMapping
    public String helloGradlePost() {
        return "analyzer";
    }
}
