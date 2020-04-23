/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : wechat_activity

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 22/04/2020 21:37:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` int(10) NOT NULL AUTO_INCREMENT,
  `cover_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `article_markdown` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `is_show` int(1) NULL DEFAULT NULL COMMENT '0隐藏，1显示',
  `activity_html` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `activity_text` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `create_type` int(1) NULL DEFAULT NULL COMMENT '类型，1 文章',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 'picgo/IMG_1122%2820200416-161555%29.PNG', '####  个人觉得写的很好的几位大佬的文章\r\n[Git常用命令及方法大全](https://blog.csdn.net/web_csdn_share/article/details/79243308)\r\n\r\n###### 第一次创建仓库提交\r\n```\r\n…or create a new repository on the command line\r\necho \"# ******\" >> README.md\r\ngit init\r\ngit add README.md\r\ngit commit -m \"first commit\"\r\ngit remote add origin https://github.com/******.git\r\ngit push -u origin master\r\n```\r\n\r\n###### 后续再次提交\r\n```\r\n…or push an existing repository from the command line\r\ngit remote add origin https://github.com/******.git\r\ngit push -u origin master\r\n```\r\n\r\n## 一个github账户下多个仓库管理\r\n\r\n###### 添加仓库\r\n```\r\n# 这里用不同的分支名代表不同的仓库，所以名字不要一样\r\ngit remote add 仓库名 https://github.com/******.git\r\n```\r\n\r\n###### 查看当前源与分支的名称与对应地址\r\n```\r\ngit remote -v    \r\n```\r\n\r\n###### 列出已经存在的远程分支\r\n```\r\ngit remote (不带参数)\r\n```\r\n\r\n###### 取消本地目录下关联的远程库：\r\n```\r\ngit remote remove 仓库名\r\n```\r\n\r\n###### 推送提交\r\n```\r\ngit push 仓库名 分支名(例如master)\r\n```\r\n## 缓存的管理\r\n###### 删除缓存文件\r\n```\r\ngit rm --cached  文件名    # 删除缓存内某个文件或文件夹\r\ngit rm --cached *    # 删除缓存内所有文件\r\ngit rm --f 文件名    # 删除缓存文件同时删除磁盘内文件\r\ngit rm -r --cached 文件名    # 删除缓存的某一个目录下所有文件\r\n``` \r\n其他的一些用到了再写吧', 1, NULL, NULL, NULL);
INSERT INTO `article` VALUES (2, 'picgo/IMG_1123(20200416-161613).PNG', '在腾讯云上买了一个学生服务器，外加一年的cn域名(已备案)。同时申请了一个免费一年的SSL证书。\r\n>服务器系统:  Ubuntu Server 18.04.1 LTS 64位\r\ntomcat: apache-tomcat-9.0.27  [官网下载地址](http://apache.01link.hk/tomcat/tomcat-9/v9.0.27/bin/apache-tomcat-9.0.27.tar.gz)\r\n\r\n### 1.在服务器上安装Java8\r\n参考大佬的[Ubuntu 18.04安装Java JDK8三种方式](https://blog.csdn.net/zbj18314469395/article/details/86064849)，我安装的open jdk8。\r\n要安装其他版本的请自行安装\r\n\r\n- 更新软件包列表:  ```sudo apt-get update```\r\n- 安装openjdk-8-jdk:  ```sudo apt-get install openjdk-8-jdk```\r\n- 查看java版本，看看是否安装成功:  ```java -version```\r\n\r\n### 2.通过win10的scp命令上传tomcat文件\r\n请更据自己的详细情况修改，scp命令详情自行百度或Google\r\n```scp 文件名及目录 服务器用户名@服务器ip地址:上传到服务器的路径```\r\n```\r\nscp C:\\Users\\***\\Downloads\\apache-tomcat-9.0.27.tar.gz ubuntu@ip地址:/home/ubuntu\r\n```\r\n![我这里用的默认的用户名，ip地址打马赛克了](http://pic.misslovecloud.cn//picgo/pwsh_abNBxjx439.png)\r\n\r\n\r\n### 3.通过win10的scp命令上传SSL证书\r\n从腾讯云控制台下载SSL证书，解压缩\r\n![从腾讯云控制台下载的SSL证书](http://pic.misslovecloud.cn//picgo/explorer_qETnY1l6ut.png)\r\n\r\n因为要配置tomcat的SSL，所以需要将tomcat文件夹内的文件上传到服务器。\r\n![tomcat文件夹内容](http://pic.misslovecloud.cn//picgo/explorer_DD25uYjTGW.png)\r\n\r\n里面只有一个.jks文件，通过SCP命令上传\r\n```\r\nscp C:\\Users\\***\\Downloads\\www.****.cn\\Tomcat\\www.****.cn.jks ubuntu@ip地址:/home/ubuntu/\r\n```\r\n\r\n### 4. 通过win10的ssh命令连接服务器，并进行相关配置\r\n在命令行里连接服务器：  ```ssh 用户名@ip地址 -p 端口号```ssh命令使用详情自行百度或Google\r\n```\r\nssh ubuntu@**** -p 22\r\n```\r\n使用```tar -zxvf apache-tomcat-9.0.27.tar.gz```命令解压缩，用cp或mv命令移动到相应的文件夹下。\r\n\r\n![解压缩并移动后tomcat文件夹内容](http://pic.misslovecloud.cn//picgo/pwsh_gSq0YL7jEJ.png)\r\n\r\n\r\n使用vim命令编辑conf目录下的server.xml文件\r\n命令：```vim conf/server.xml```\r\n将8443改为443\r\n\r\n![修改前]cccccccccc(http://pic.misslovecloud.cn//picgo/Code_MGIiT8arOC.png)\r\n![修改后](http://pic.misslovecloud.cn//picgo/Code_J5LdKF15i1.png)\r\n\r\n\r\n添加如下代码\r\n![](http://pic.misslovecloud.cn//picgo/Code_xUuLbTT89Z.png)\r\n\r\n```\r\n<Connector port=\"443\"   protocol=\"org.apache.coyote.http11.Http11NioProtocol\" maxThreads=\"150\" SSLEnabled=\"true\">\r\n        <SSLHostConfig>\r\n            <Certificate \r\n                certificateKeystoreFile=\"jks文件的绝对路径或相对路径\" \r\n                certificateKeyAlias=\"我这里填的域名(www.***.cn)\"\r\n                certificateKeystorePassword=\"私钥密码\"\r\n                         type=\"RSA\" />\r\n        </SSLHostConfig>\r\n    </Connector>\r\n```\r\ncertificateKeyAlias里填申请时在这里填的证书备注名\r\n![](http://pic.misslovecloud.cn//picgo/chrome_N4aABrgjDp.png)\r\n\r\n\r\n保存退出后，编辑conf目录下的web.xml\r\n命令：```vim conf/web.xml```\r\n添加如下代码，作用：将http转为https\r\n![](http://pic.misslovecloud.cn//picgo/Code_dcS5B1PU6j.png)\r\n\r\n```\r\n<!-- 强制 HTTPS 访问 -->\r\n    <login-config>    \r\n        <auth-method>CLIENT-CERT</auth-method>\r\n        <realm-name>Client Cert Users-only Area</realm-name>\r\n    </login-config> \r\n    <security-constraint>\r\n        <web-resource-collection>\r\n            <web-resource-name>SSL</web-resource-name>\r\n            <url-pattern>/*</url-pattern>\r\n        </web-resource-collection>\r\n        <user-data-constraint>\r\n            <transport-guarantee>CONFIDENTIAL</transport-guarantee>\r\n        </user-data-constraint>\r\n    </security-constraint>\r\n```\r\n添加完成后保存退出。\r\n运行bin目录下的startup.sh。(注：需要使用sudo提升权限，不然即使配置好了https也不能访问，有想法的可以自己尝试下)\r\n命令：```sudo ./bin/startup.sh```\r\n![](http://pic.misslovecloud.cn//picgo/pwsh_gJDkrDToTA.png)\r\n\r\n如此tomcat便已启动。\r\n通过https访问成功\r\n![](http://pic.misslovecloud.cn//picgo/chrome_pJ853nSNeq.png)\r\n\r\n\r\n\r\n\r\n\r\n\r\n在腾讯云上买了一个学生服务器，外加一年的cn域名(已备案)。同时申请了一个免费一年的SSL证书。\r\n>服务器系统:  Ubuntu Server 18.04.1 LTS 64位\r\ntomcat: apache-tomcat-9.0.27  [官网下载地址](http://apache.01link.hk/tomcat/tomcat-9/v9.0.27/bin/apache-tomcat-9.0.27.tar.gz)\r\n\r\n### 1.在服务器上安装Java8\r\n参考大佬的[Ubuntu 18.04安装Java JDK8三种方式](https://blog.csdn.net/zbj18314469395/article/details/86064849)，我安装的open jdk8。\r\n要安装其他版本的请自行安装\r\n\r\n- 更新软件包列表:  ```sudo apt-get update```\r\n- 安装openjdk-8-jdk:  ```sudo apt-get install openjdk-8-jdk```\r\n- 查看java版本，看看是否安装成功:  ```java -version```\r\n\r\n### 2.通过win10的scp命令上传tomcat文件\r\n请更据自己的详细情况修改，scp命令详情自行百度或Google\r\n```scp 文件名及目录 服务器用户名@服务器ip地址:上传到服务器的路径```\r\n```\r\nscp C:\\Users\\***\\Downloads\\apache-tomcat-9.0.27.tar.gz ubuntu@ip地址:/home/ubuntu\r\n```\r\n![我这里用的默认的用户名，ip地址打马赛克了](http://pic.misslovecloud.cn//picgo/pwsh_abNBxjx439.png)\r\n\r\n\r\n### 3.通过win10的scp命令上传SSL证书\r\n从腾讯云控制台下载SSL证书，解压缩\r\n![从腾讯云控制台下载的SSL证书](http://pic.misslovecloud.cn//picgo/explorer_qETnY1l6ut.png)\r\n\r\n因为要配置tomcat的SSL，所以需要将tomcat文件夹内的文件上传到服务器。\r\n![tomcat文件夹内容](http://pic.misslovecloud.cn//picgo/explorer_DD25uYjTGW.png)\r\n\r\n里面只有一个.jks文件，通过SCP命令上传\r\n```\r\nscp C:\\Users\\***\\Downloads\\www.****.cn\\Tomcat\\www.****.cn.jks ubuntu@ip地址:/home/ubuntu/\r\n```\r\n\r\n### 4. 通过win10的ssh命令连接服务器，并进行相关配置\r\n在命令行里连接服务器：  ```ssh 用户名@ip地址 -p 端口号```ssh命令使用详情自行百度或Google\r\n```\r\nssh ubuntu@**** -p 22\r\n```\r\n使用```tar -zxvf apache-tomcat-9.0.27.tar.gz```命令解压缩，用cp或mv命令移动到相应的文件夹下。\r\n\r\n![解压缩并移动后tomcat文件夹内容](http://pic.misslovecloud.cn//picgo/pwsh_gSq0YL7jEJ.png)\r\n\r\n\r\n使用vim命令编辑conf目录下的server.xml文件\r\n命令：```vim conf/server.xml```\r\n将8443改为443\r\n\r\n![修改前]cccccccccc(http://pic.misslovecloud.cn//picgo/Code_MGIiT8arOC.png)\r\n![修改后](http://pic.misslovecloud.cn//picgo/Code_J5LdKF15i1.png)\r\n\r\n\r\n添加如下代码\r\n![](http://pic.misslovecloud.cn//picgo/Code_xUuLbTT89Z.png)\r\n\r\n```\r\n<Connector port=\"443\"   protocol=\"org.apache.coyote.http11.Http11NioProtocol\" maxThreads=\"150\" SSLEnabled=\"true\">\r\n        <SSLHostConfig>\r\n            <Certificate \r\n                certificateKeystoreFile=\"jks文件的绝对路径或相对路径\" \r\n                certificateKeyAlias=\"我这里填的域名(www.***.cn)\"\r\n                certificateKeystorePassword=\"私钥密码\"\r\n                         type=\"RSA\" />\r\n        </SSLHostConfig>\r\n    </Connector>\r\n```\r\ncertificateKeyAlias里填申请时在这里填的证书备注名\r\n![](http://pic.misslovecloud.cn//picgo/chrome_N4aABrgjDp.png)\r\n\r\n\r\n保存退出后，编辑conf目录下的web.xml\r\n命令：```vim conf/web.xml```\r\n添加如下代码，作用：将http转为https\r\n![](http://pic.misslovecloud.cn//picgo/Code_dcS5B1PU6j.png)\r\n\r\n```\r\n<!-- 强制 HTTPS 访问 -->\r\n    <login-config>    \r\n        <auth-method>CLIENT-CERT</auth-method>\r\n        <realm-name>Client Cert Users-only Area</realm-name>\r\n    </login-config> \r\n    <security-constraint>\r\n        <web-resource-collection>\r\n            <web-resource-name>SSL</web-resource-name>\r\n            <url-pattern>/*</url-pattern>\r\n        </web-resource-collection>\r\n        <user-data-constraint>\r\n            <transport-guarantee>CONFIDENTIAL</transport-guarantee>\r\n        </user-data-constraint>\r\n    </security-constraint>\r\n```\r\n添加完成后保存退出。\r\n运行bin目录下的startup.sh。(注：需要使用sudo提升权限，不然即使配置好了https也不能访问，有想法的可以自己尝试下)\r\n命令：```sudo ./bin/startup.sh```\r\n![](http://pic.misslovecloud.cn//picgo/pwsh_gJDkrDToTA.png)\r\n\r\n如此tomcat便已启动。\r\n通过https访问成功\r\n![](http://pic.misslovecloud.cn//picgo/chrome_pJ853nSNeq.png)\r\n\r\n\r\n\r\n\r\n\r\n\r\n在腾讯云上买了一个学生服务器，外加一年的cn域名(已备案)。同时申请了一个免费一年的SSL证书。\r\n>服务器系统:  Ubuntu Server 18.04.1 LTS 64位\r\ntomcat: apache-tomcat-9.0.27  [官网下载地址](http://apache.01link.hk/tomcat/tomcat-9/v9.0.27/bin/apache-tomcat-9.0.27.tar.gz)\r\n\r\n### 1.在服务器上安装Java8\r\n参考大佬的[Ubuntu 18.04安装Java JDK8三种方式](https://blog.csdn.net/zbj18314469395/article/details/86064849)，我安装的open jdk8。\r\n要安装其他版本的请自行安装\r\n\r\n- 更新软件包列表:  ```sudo apt-get update```\r\n- 安装openjdk-8-jdk:  ```sudo apt-get install openjdk-8-jdk```\r\n- 查看java版本，看看是否安装成功:  ```java -version```\r\n\r\n### 2.通过win10的scp命令上传tomcat文件\r\n请更据自己的详细情况修改，scp命令详情自行百度或Google\r\n```scp 文件名及目录 服务器用户名@服务器ip地址:上传到服务器的路径```\r\n```\r\nscp C:\\Users\\***\\Downloads\\apache-tomcat-9.0.27.tar.gz ubuntu@ip地址:/home/ubuntu\r\n```\r\n![我这里用的默认的用户名，ip地址打马赛克了](http://pic.misslovecloud.cn//picgo/pwsh_abNBxjx439.png)\r\n\r\n\r\n### 3.通过win10的scp命令上传SSL证书\r\n从腾讯云控制台下载SSL证书，解压缩\r\n![从腾讯云控制台下载的SSL证书](http://pic.misslovecloud.cn//picgo/explorer_qETnY1l6ut.png)\r\n\r\n因为要配置tomcat的SSL，所以需要将tomcat文件夹内的文件上传到服务器。\r\n![tomcat文件夹内容](http://pic.misslovecloud.cn//picgo/explorer_DD25uYjTGW.png)\r\n\r\n里面只有一个.jks文件，通过SCP命令上传\r\n```\r\nscp C:\\Users\\***\\Downloads\\www.****.cn\\Tomcat\\www.****.cn.jks ubuntu@ip地址:/home/ubuntu/\r\n```\r\n\r\n### 4. 通过win10的ssh命令连接服务器，并进行相关配置\r\n在命令行里连接服务器：  ```ssh 用户名@ip地址 -p 端口号```ssh命令使用详情自行百度或Google\r\n```\r\nssh ubuntu@**** -p 22\r\n```\r\n使用```tar -zxvf apache-tomcat-9.0.27.tar.gz```命令解压缩，用cp或mv命令移动到相应的文件夹下。\r\n\r\n![解压缩并移动后tomcat文件夹内容](http://pic.misslovecloud.cn//picgo/pwsh_gSq0YL7jEJ.png)\r\n\r\n\r\n使用vim命令编辑conf目录下的server.xml文件\r\n命令：```vim conf/server.xml```\r\n将8443改为443\r\n\r\n![修改前]cccccccccc(http://pic.misslovecloud.cn//picgo/Code_MGIiT8arOC.png)\r\n![修改后](http://pic.misslovecloud.cn//picgo/Code_J5LdKF15i1.png)\r\n\r\n\r\n添加如下代码\r\n![](http://pic.misslovecloud.cn//picgo/Code_xUuLbTT89Z.png)\r\n\r\n```\r\n<Connector port=\"443\"   protocol=\"org.apache.coyote.http11.Http11NioProtocol\" maxThreads=\"150\" SSLEnabled=\"true\">\r\n        <SSLHostConfig>\r\n            <Certificate \r\n                certificateKeystoreFile=\"jks文件的绝对路径或相对路径\" \r\n                certificateKeyAlias=\"我这里填的域名(www.***.cn)\"\r\n                certificateKeystorePassword=\"私钥密码\"\r\n                         type=\"RSA\" />\r\n        </SSLHostConfig>\r\n    </Connector>\r\n```\r\ncertificateKeyAlias里填申请时在这里填的证书备注名\r\n![](http://pic.misslovecloud.cn//picgo/chrome_N4aABrgjDp.png)\r\n\r\n\r\n保存退出后，编辑conf目录下的web.xml\r\n命令：```vim conf/web.xml```\r\n添加如下代码，作用：将http转为https\r\n![](http://pic.misslovecloud.cn//picgo/Code_dcS5B1PU6j.png)\r\n\r\n```\r\n<!-- 强制 HTTPS 访问 -->\r\n    <login-config>    \r\n        <auth-method>CLIENT-CERT</auth-method>\r\n        <realm-name>Client Cert Users-only Area</realm-name>\r\n    </login-config> \r\n    <security-constraint>\r\n        <web-resource-collection>\r\n            <web-resource-name>SSL</web-resource-name>\r\n            <url-pattern>/*</url-pattern>\r\n        </web-resource-collection>\r\n        <user-data-constraint>\r\n            <transport-guarantee>CONFIDENTIAL</transport-guarantee>\r\n        </user-data-constraint>\r\n    </security-constraint>\r\n```\r\n添加完成后保存退出。\r\n运行bin目录下的startup.sh。(注：需要使用sudo提升权限，不然即使配置好了https也不能访问，有想法的可以自己尝试下)\r\n命令：```sudo ./bin/startup.sh```\r\n![](http://pic.misslovecloud.cn//picgo/pwsh_gJDkrDToTA.png)\r\n\r\n如此tomcat便已启动。\r\n通过https访问成功\r\n![](http://pic.misslovecloud.cn//picgo/chrome_pJ853nSNeq.png)\r\n\r\n\r\n\r\n\r\n\r\n\r\n', 1, NULL, NULL, NULL);
INSERT INTO `article` VALUES (3, 'picgo/IMG_1123(20200416-161613).PNG', '##首先说一下，mysqlclient现在支持python3，mysqlclient具体哪个版本支持的不太清楚。想的自己去看下[文档说明](https://pypi.org/project/mysqlclient/)\r\n```This project adds Python 3 support and bug fixes. I hope this fork is merged back to MySQLdb1 like distribute was merged back to setuptools.```\r\n\r\n\r\n原来Django一直使用的Mondb数据库，由于项目需要使用mysql，配置mysql时一直失败，报错提示\r\n```\r\ndjango.core.exceptions.ImproperlyConfigured: \r\nmysqlclient 1.3.13 or newer is required; you have 0.9.3\r\n```\r\n上网搜了半天，说是将报错的代码注释掉，注释掉后又会出现其他问题。\r\n在这里提供一个其他的解决方法\r\n大多数人使用Django配置mysql数据库使用的是pymysql库，django默认使用的是mysqlclient。那么为什么不用mysqlclient呢？\r\n在cmd命令行里更新mysqlclient库\r\n```\r\npip install --upgrade mysqlclient\r\n```\r\n在setting.py里配置mysql\r\n```\r\nDATABASES = {\r\n    \'default\': {\r\n        # \'ENGINE\': \'django.db.backends.sqlite3\',\r\n        # \'NAME\': os.path.join(BASE_DIR, \'db.sqlite3\'),\r\n        \'ENGINE\': \'django.db.backends.mysql\',\r\n        \'NAME\': \'数据库名称\'\r\n        \'USER\': \'用户名\',\r\n        \'PASSWORD\': \'用户密码\',\r\n        \'HOST\': \'localhost\',\r\n        \'PORT\': \'3306\',\r\n    }\r\n}\r\n```\r\n配置完成后执行迁移命令\r\n```\r\npython manage.py makemigrations\r\npython manage.py migrate\r\n```\r\n此时就可以正常执行，不会报错了\r\n\r\n#**更新一个，这段时间开发环境从win10换到了mac，再配置的时候发现在mac下会出一些其他的问题。下面是mac的解决方案**\r\n\r\n我的python版本和pip版本\r\n```\r\nmisslovedeMBP:lib misslove$ python3\r\nPython 3.7.4 (v3.7.4:e09359112e, Jul  8 2019, 14:54:52) \r\n[Clang 6.0 (clang-600.0.57)] on darwin\r\nType \"help\", \"copyright\", \"credits\" or \"license\" for more information.\r\n>>> \r\n\r\nmisslovedeMBP:lib misslove$ pip3 -V\r\npip 19.2.3 from /Library/Frameworks/Python.framework/Versions/3.7/lib/python3.7/site-packages/pip (python 3.7)\r\n```\r\n\r\n使用pip命令安装django和mysqlclient\r\n```\r\npip3 install -i https://pypi.douban.com/simple/ django\r\npip3 install -i https://pypi.douban.com/simple/ mysqlclient\r\n```\r\n\r\n安装完后使用python3 manage.py runserver会报错误\r\n```\r\ndjango.core.exceptions.ImproperlyConfigured: Error loading MySQLdb module.\r\nDid you install mysqlclient?\r\n```\r\n明明安装了mysqlclient却还是报错，尝试在python3里引用MySQLdb库发现报错\r\n```\r\n>>> import MySQLdb\r\nTraceback (most recent call last):\r\n  File \"<stdin>\", line 1, in <module>\r\n  File \"/Library/Frameworks/Python.framework/Versions/3.7/lib/python3.7/site-packages/MySQLdb/__init__.py\", line 18, in <module>\r\n    from . import _mysql\r\nImportError: dlopen(/Library/Frameworks/Python.framework/Versions/3.7/lib/python3.7/site-packages/MySQLdb/_mysql.cpython-37m-darwin.so, 2): Library not loaded: @rpath/libmysqlclient.21.dylib\r\n  Referenced from: /Library/Frameworks/Python.framework/Versions/3.7/lib/python3.7/site-packages/MySQLdb/_mysql.cpython-37m-darwin.so\r\n  Reason: image not found\r\n```\r\n主要看下面这一个报错，我这里因为已经改了一些东西，可能报错不一样，但都是lib***.dylib。意思是在/usr/lib目录下找不到这个文件。我们安装mysql时肯定是有的，所以建立几个软链接指向它就可以了。\r\n```\r\nLibrary not loaded: @rpath/libmysqlclient.21.dylib\r\n```\r\n\r\n我的mysql安装在 /usr/local/mysql-8.0.17-macos10.14-x86_64/，所以在这个目录下找lib目录，进去后会发现有刚才报错缺失的文件\r\n ```\r\nmisslovedeMBP:lib misslove$ cd /usr/local/mysql-8.0.17-macos10.14-x86_64/\r\nmisslovedeMBP:mysql-8.0.17-macos10.14-x86_64 misslove$ ls\r\nLICENSE		README.router	docs		lib		share\r\nLICENSE.router	bin		include		man		support-files\r\nREADME		data		keyring		run		var\r\nmisslovedeMBP:mysql-8.0.17-macos10.14-x86_64 misslove$ cd lib/\r\nmisslovedeMBP:lib misslove$ ls\r\nlibcrypto.1.0.0.dylib		libmysqlrouter_http.1.dylib\r\nlibcrypto.dylib			libmysqlrouter_http.dylib\r\nlibmysqlclient.21.dylib		libmysqlservices.a\r\nlibmysqlclient.a		libssl.1.0.0.dylib\r\nlibmysqlclient.dylib		libssl.dylib\r\nlibmysqlharness.1.dylib		mecab\r\nlibmysqlharness.a		mysqlrouter\r\nlibmysqlharness.dylib		pkgconfig\r\nlibmysqlrouter.1.dylib		plugin\r\nlibmysqlrouter.dylib\r\n```\r\n\r\n找到具体位置后就可以在/usr/lib目录下建立软链接。建立完成后可以到/usr/lib目录下使用ls -al命令查看建立的链接\r\n ```\r\nsudo ln -s /usr/local/mysql-8.0.17-macos10.14-x86_64/lib/libmysqlclient.21.dylib /usr/lib/libmysqlclient.21.dylib\r\n\r\nmisslovedeMBP:lib misslove$ ls -al\r\nlrwxr-xr-x    1 root  wheel        69  9  8 15:26 libmysqlclient.21.dylib -> /usr/local/mysql-8.0.17-macos10.14-x86_64/lib/libmysqlclient.21.dylib\r\n```\r\n\r\n建立完成后再去python里import MySQLdb可能会报其他的文件问题，复制文件名，建立软链接就可以了。\r\n除了这个问题还会有一个新的问题，报找不到MySQLdb库，说实话我看到了也一脸懵逼，发现退出重新再打开就好，或者重启终端。可能需要让系统底层代码跑一会儿？\r\n```\r\nmisslovedeMBP:lib misslove$ python3\r\nPython 3.7.4 (v3.7.4:e09359112e, Jul  8 2019, 14:54:52) \r\n[Clang 6.0 (clang-600.0.57)] on darwin\r\nType \"help\", \"copyright\", \"credits\" or \"license\" for more information.\r\n>>> import MqSQLdb\r\nTraceback (most recent call last):\r\n  File \"<stdin>\", line 1, in <module>\r\nModuleNotFoundError: No module named \'MqSQLdb\'\r\n>>> exit()\r\n\r\nmisslovedeMBP:platform_static misslove$ python3\r\nPython 3.7.4 (v3.7.4:e09359112e, Jul  8 2019, 14:54:52) \r\n[Clang 6.0 (clang-600.0.57)] on darwin\r\nType \"help\", \"copyright\", \"credits\" or \"license\" for more information.\r\n>>> import MySQLdb\r\n>>> \r\n```\r\n此时再python3 manage.py runserver就没问题了。\r\n\r\n下面这个问题是因为我把win10下的整个文件夹复制粘贴到mac下的，执行一下python manage.py migrate命令就可以了，具体命令时做什么的自行百度。我就不说了，因为我也只知道作用，讲不清概念\r\n```\r\nmisslovedeMBP:platform_static misslove$ python3 manage.py runserver\r\nWatching for file changes with StatReloader\r\nPerforming system checks...\r\n\r\nSystem check identified no issues (0 silenced).\r\n\r\nYou have 17 unapplied migration(s). Your project may not work properly until you apply the migrations for app(s): admin, auth, contenttypes, sessions.\r\nRun \'python manage.py migrate\' to apply them.\r\n\r\nSeptember 08, 2019 - 07:44:10\r\nDjango version 2.2.5, using settings \'platform_static.settings\'\r\nStarting development server at http://127.0.0.1:8000/\r\nQuit the server with CONTROL-C.\r\n```', 1, NULL, NULL, NULL);
INSERT INTO `article` VALUES (4, 'picgo/IMG_1123(20200416-161613).PNG', '# 今天在拉钩上找工作时，有一家公司职位详情写了“投递简历前，请base64decode以下内容”。于是就试了试。发现无法赋值便在cmd里纯手打deocde。。。\r\n\r\n~~~\r\n\'5aaC5p6c5L2g5ZKM5oiR5Lus5LiA5qC354Ot54ix57yW56CB77yM55e06L+35LqO5bel56iL5oqA5pyv77yM6K+35YaZ5LiA5bCB6YKu5Lu277yM566A5Y2V5LuL57uN5L2g6Ieq5bex77yM6Z2e5bi45pyf5b6F5pS25Yiw5L2g55qE5p2l5L+hOiBsaWFvaHVxaXVAZ21haWwuY29tCkhhcHB5IGNvZGluZyE=\'\r\n~~~\r\n不得不吐槽一下，拉钩I的大写字母和L的小写字母是一摸一样的，找了好长时间问题\r\n\r\n首先import一下python自带的库base64\r\n~~~\r\nimport base64\r\n~~~\r\n\r\n调用其b64decode方法，进行base64的编解码\r\n~~~\r\nbase64.b64decode(\'5aaC5p6c5L2g5ZKM5oiR5Lus5LiA5qC354Ot54ix57yW56CB77yM55e06L+35LqO5bel56iL5oqA5pyv77yM6K+35YaZ5LiA5bCB6YKu5Lu277yM566A5Y2V5LuL57uN5L2g6Ieq5bex77yM6Z2e5bi45pyf5b6F5pS25Yiw5L2g55qE5p2l5L+hOiBsaWFvaHVxaXVAZ21haWwuY29tCkhhcHB5IGNvZGluZyE=\')\r\n~~~\r\n\r\n解析后得到\r\n~~~\r\nb\'\\xe5\\xa6\\x82\\xe6\\x9e\\x9c\\xe4\\xbd\\xa0\\xe5\\x92\\x8c\\xe6\\x88\\x91\\xe4\\xbb\\xac\\xe4\\xb8\\x80\\xe6\\xa0\\xb7\\xe7\\x83\\xad\\xe7\\x88\\xb1\\xe7\\xbc\\x96\\xe7\\xa0\\x81\\xef\\xbc\\x8c\\xe7\\x97\\xb4\\xe8\\xbf\\xb7\\xe4\\xba\\x8e\\xe5\\xb7\\xa5\\xe7\\xa8\\x8b\\xe6\\x8a\\x80\\xe6\\x9c\\xaf\\xef\\xbc\\x8c\\xe8\\xaf\\xb7\\xe5\\x86\\x99\\xe4\\xb8\\x80\\xe5\\xb0\\x81\\xe9\\x82\\xae\\xe4\\xbb\\xb6\\xef\\xbc\\x8c\\xe7\\xae\\x80\\xe5\\x8d\\x95\\xe4\\xbb\\x8b\\xe7\\xbb\\x8d\\xe4\\xbd\\xa0\\xe8\\x87\\xaa\\xe5\\xb7\\xb1\\xef\\xbc\\x8c\\xe9\\x9d\\x9e\\xe5\\xb8\\xb8\\xe6\\x9c\\x9f\\xe5\\xbe\\x85\\xe6\\x94\\xb6\\xe5\\x88\\xb0\\xe4\\xbd\\xa0\\xe7\\x9a\\x84\\xe6\\x9d\\xa5\\xe4\\xbf\\xa1: liaohuqiu@gmail.com\\nHappy coding!\'\r\n~~~\r\n\r\n最后再转为utf-8，decode方法默认utf-8，所以可以不用指定，这里我指定了。\r\n~~~\r\nbase64.b64decode(\'5aaC5p6c5L2g5ZKM5oiR5Lus5LiA5qC354Ot54ix57yW56CB77yM55e06L+35LqO5bel56iL5oqA5pyv77yM6K+35YaZ5LiA5bCB6YKu5Lu277yM566A5Y2V5LuL57uN5L2g6Ieq5bex77yM6Z2e5bi45pyf5b6F5pS25Yiw5L2g55qE5p2l5L+hOiBsaWFvaHVxaXVAZ21haWwuY29tCkhhcHB5IGNvZGluZyE=\').decode(\'utf-8\')\r\n~~~\r\n转换后得到，\\n是换行\r\n~~~\r\n\'如果你和我们一样热爱编码，痴迷于工程技术，请写一封邮件，简单介绍你自己，非常期待收到你的来信: liaohuqiu@gmail.com\\nHappy coding!\'\r\n~~~\r\n\r\n下面我写一些由于敲错了字母找问题的经历吧。。\r\n\r\n下面是我一开始打的字符串，有三处错误：原“6K+3”我打成了小写k，原“6Ieq”和“lGNv”打成了小写l。\r\n~~~\r\ns=\"5aaC5p6c5L2g5ZKM5oiR5Lus5LiA5qC354Ot54ix57yW56CB77yM55e06L+35LqO5bel56iL5oqA5pyv77yM6k+35YaZ5LiA5bCB6YKu5Lu277yM566A5Y2V5LuL57uN5L2g6leq5bex77yM6Z2e5bi45pyf5b6F5pS25Yiw5L2g55qE5p2l5L+hOiBsaWFvaHVxaXVAZ21haWwuY29tCkhhcHB5lGNvZGluZyE=\"\r\n~~~\r\n进行base64编解码的时候是不会报错误的\r\n~~~\r\nb\'\\xe5\\xa6\\x82\\xe6\\x9e\\x9c\\xe4\\xbd\\xa0\\xe5\\x92\\x8c\\xe6\\x88\\x91\\xe4\\xbb\\xac\\xe4\\xb8\\x80\\xe6\\xa0\\xb7\\xe7\\x83\\xad\\xe7\\x88\\xb1\\xe7\\xbc\\x96\\xe7\\xa0\\x81\\xef\\xbc\\x8c\\xe7\\x97\\xb4\\xe8\\xbf\\xb7\\xe4\\xba\\x8e\\xe5\\xb7\\xa5\\xe7\\xa8\\x8b\\xe6\\x8a\\x80\\xe6\\x9c\\xaf\\xef\\xbc\\x8c\\xeaO\\xb7\\xe5\\x86\\x99\\xe4\\xb8\\x80\\xe5\\xb0\\x81\\xe9\\x82\\xae\\xe4\\xbb\\xb6\\xef\\xbc\\x8c\\xe7\\xae\\x80\\xe5\\x8d\\x95\\xe4\\xbb\\x8b\\xe7\\xbb\\x8d\\xe4\\xbd\\xa0\\xeaW\\xaa\\xe5\\xb7\\xb1\\xef\\xbc\\x8c\\xe9\\x9d\\x9e\\xe5\\xb8\\xb8\\xe6\\x9c\\x9f\\xe5\\xbe\\x85\\xe6\\x94\\xb6\\xe5\\x88\\xb0\\xe4\\xbd\\xa0\\xe7\\x9a\\x84\\xe6\\x9d\\xa5\\xe4\\xbf\\xa1: liaohuqiu@gmail.com\\nHappy\\x94coding!\'\r\n~~~\r\n在转utf-8时会报错误，分别报了三个错误。通过排查发现是因为\"6k+3\"转为了\\xeaO，“6Ieq”转为了\\xeaW，“lGNv”转为了\\x94。\r\n~~~\r\n\'utf-8\' codec can\'t decode byte 0xea in position 63: invalid continuation byte\r\n\'utf-8\' codec can\'t decode byte 0xea in position 99: invalid continuation byte\r\n\'utf-8\' codec can\'t decode byte 0x94 in position 165: invalid start byte\r\n~~~\r\n忽略掉错误后，大致是可以看懂说了什么的。\r\n~~~\r\n\'如果你和我们一样热爱编码，痴迷于工程技术，�O�写一封邮件，简单介绍你�W�己，非常期待收到你的来 信: liaohuqiu@gmail.com\\nHappy�coding!\'\r\n~~~\r\n猜测一下这三个空应该是“请”，“自”，空格。然后找到错误改了就成了\r\n~~~\r\n>>> base64.b64encode(\'请\'.encode())\r\nb\'6K+3\'\r\n>>> base64.b64encode(\'自\'.encode())\r\nb\'6Ieq\'\r\n>>> base64.b64encode(\' \'.encode())\r\nb\'IG==\'\r\n~~~', 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for header
-- ----------------------------
DROP TABLE IF EXISTS `header`;
CREATE TABLE `header`  (
  `header_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '团长id',
  `header_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `header_community` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社区',
  `header_phone_number` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `header_registered_time` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '注册时间',
  `header_longitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '经度',
  `header_latitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '纬度',
  PRIMARY KEY (`header_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of header
-- ----------------------------
INSERT INTO `header` VALUES (1, '晋城市', '兰亭书院', '11122223333', '1587308116233', '1', NULL);

-- ----------------------------
-- Table structure for header_commodity
-- ----------------------------
DROP TABLE IF EXISTS `header_commodity`;
CREATE TABLE `header_commodity`  (
  `header_commodity_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `header_id` int(20) NULL DEFAULT NULL COMMENT '团长id',
  `header_commodity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `header_commodity_imgurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `header_commodity_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `header_commodity_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详情',
  `header_commodity_specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `header_commodity_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `header_commodity_number` int(11) NULL DEFAULT NULL COMMENT '商品库存数量',
  `header_commodity_price` decimal(10, 8) NULL DEFAULT NULL COMMENT '商品价格',
  PRIMARY KEY (`header_commodity_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `user_avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户头像url',
  `user_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '城市',
  `user_phone_number` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `user_register_time` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (11, '一个懒得不想动脑子的昵称', 'https://wx.qlogo.cn/mmopen/vi_32/ajNVdqHZLLCMhMic7B34Hnt5l5EXjWfxGibbS92ZhCLz6gpWOmqPicPmiaSn2J5Ylvu9xtIFh2hUJ9IrvmOc0lac8w/132', 'Jincheng', 'q', '1571641644318');
INSERT INTO `user` VALUES (12, '辛福', 'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELo7p6RcD9wptfod6PFWobpvP6kNt8yXypryB1vQhOKcAqcRheEU8QTXicvVpeoER7bicTIdyRqgibeg/132', 'Xuancheng', '', '1573117151766');
INSERT INTO `user` VALUES (13, '辛福', 'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELo7p6RcD9wptfod6PFWobpvP6kNt8yXypryB1vQhOKcAqcRheEU8QTXicvVpeoER7bicTIdyRqgibeg/132', 'Xuancheng', '', '1573117151759');
INSERT INTO `user` VALUES (14, '辛福', 'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELo7p6RcD9wptfod6PFWobpvP6kNt8yXypryB1vQhOKcAqcRheEU8QTXicvVpeoER7bicTIdyRqgibeg/132', 'Xuancheng', '', '1573117151770');
INSERT INTO `user` VALUES (15, '辛福', 'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELo7p6RcD9wptfod6PFWobpvP6kNt8yXypryB1vQhOKcAqcRheEU8QTXicvVpeoER7bicTIdyRqgibeg/132', 'Xuancheng', '', '1573117151773');

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address`  (
  `user_address_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `user_id` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `user_phone_number` int(15) NULL DEFAULT NULL COMMENT '收货人手机号',
  `user_province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省',
  `user_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户所在城市',
  `user_region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区域',
  `user_street` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '街道',
  `user_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `user_default_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '否位默认地址，0否 1是',
  PRIMARY KEY (`user_address_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_cart
-- ----------------------------
DROP TABLE IF EXISTS `user_cart`;
CREATE TABLE `user_cart`  (
  `cart_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `user_id` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `header_id` int(20) NULL DEFAULT NULL COMMENT '团长id',
  `commodity_id` int(20) NULL DEFAULT NULL COMMENT '商品id',
  `commodity_number` int(20) NULL DEFAULT NULL COMMENT '购买数量',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order`  (
  `order_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `commodity_id` int(20) NULL DEFAULT NULL COMMENT '商品id',
  `means_of_transaction` int(20) NULL DEFAULT NULL COMMENT '交易方式',
  `total_price` decimal(10, 8) NULL DEFAULT NULL COMMENT '总价',
  `pay_price` decimal(10, 8) NULL DEFAULT NULL COMMENT '支付价格',
  `discounted_prices` decimal(10, 8) NULL DEFAULT NULL COMMENT '优惠价格',
  `wechat_transaction_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信交易号',
  `create_time` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建时间',
  `payment_time` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '付款时间',
  `transaction_time` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '成交时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_order_ommodity
-- ----------------------------
DROP TABLE IF EXISTS `user_order_ommodity`;
CREATE TABLE `user_order_ommodity`  (
  `order_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `commodity_id` int(20) NULL DEFAULT NULL COMMENT '商品id',
  `header_id` int(20) NULL DEFAULT NULL COMMENT '团长id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `imgurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品图片',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详情',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `commodity_number` int(5) NULL DEFAULT NULL COMMENT '购买数量',
  `commodity_price` decimal(10, 8) NULL DEFAULT NULL COMMENT '商品价格',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
