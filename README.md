# rongPayDemo
pos调用demo
## 1.配置AndroidManifest.xml
配置应用亲和性，在AndroidManifest.xml 中添加
```
android:taskAffinity="com.rongcapital.pos"
```
## 2.调用收款功能
```
MainActivity => pay();
注意传入aplicationId：args.putString("packageName", applicationId);    // applicationId 应用标示
```

## 3.打印功能
```
MainActivity => print();
```