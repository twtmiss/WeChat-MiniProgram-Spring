package denggao.wechat.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
