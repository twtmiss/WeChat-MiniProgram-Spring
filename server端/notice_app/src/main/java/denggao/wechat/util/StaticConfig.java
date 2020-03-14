package denggao.wechat.util;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class StaticConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有C:/Users/gzpost05/Desktop/springboot博客/ 访问都映射到/myTest/** 路径下
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:"+Config.UploadsPath);

        /*
        //addResourceHandler是指你想在url请求的路径
               //addResourceLocations是图片存放的真实路径

        registry.addResourceHandler("/**").addResourceLocations("file:C:/Users/tizzy/Desktop/img/").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
         */
    }

}
