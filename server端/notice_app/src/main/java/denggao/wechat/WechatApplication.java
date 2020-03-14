package denggao.wechat;

import javafx.application.Application;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
//public class WechatApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(WechatApplication.class, args);
//    }
//
//}

public class WechatApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WechatApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }

}
