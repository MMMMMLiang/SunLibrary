# SunLibrary

#### 介绍
RxJava2+Retrofit2+RxLifecycle2+OkHttp3 封装RHttp 使用MVP模式构建项目
基本的get、post、put、delete、4种请求
单/多文件上传
断点续传下载
基本回调包含 onSuccess、onError、onCancel、onProgress（上传/下载）
支持自定义Callback
支持https
支持tag取消，也可取消全部
支持链式调用
支持表单格式，String，json格式数据提交请求

基于主流网络框架 Retrofit2 + OkHttp3 作为底层框架
使用 RxJava2 处理异步事件，线程间切换逻辑
使用 RxLifecycle2 管理生命周期，再也不用担心 RxJava 使用不当造成内存泄漏
基础网络请求，数据转换为 String 并提供 onConvert 方法提供开发者自定义转化数据
上传带进度回调，多文件情况下，可以区分当前文件下标
断点续传大文件，多种下载状态满足不同下载需求

MVP 是 MVC 框架的升级版本，通过 Presenter 桥梁连接 View 和 Model，使得模块之间更好的解耦

#### 软件架构
软件架构说明


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request