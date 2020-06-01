"""platform_static URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from django.conf.urls import url
from django.views.static import serve

urlpatterns = [
    path('admin/', admin.site.urls),

    #根据路径直接访问文件
    url(r'^images/(?P<path>.*)$', serve, {'document_root': './static/images/activity'}),
    url(r'^music/(?P<path>.*)$', serve, {'document_root': './static/music'})
    
    #http://127.0.0.1:8000/images/productImages/12e24e90e6884fa29a0b7744481ea14e_01.png

]