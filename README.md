# Luandun

## 项目简介
### 功能简介
一个个人自用的手机应用。
包括：
* 网站管理（维护和查看）
* 手机管理（进程和缓存管理）
* 密码管理（一个口令管理其他密码，口令不进行任何形式的存储）
* 图灵机器人
* 主题设置（可以根据个人喜好，更换不同颜色的主题）

### 设计简介
[框架的uml类图](https://github.com/libo591/Luandun/blob/master/uml/uml.png)
> 总体上框架主要使用mvp模式，使用eventbus传递事件，解耦视图层和模型层，使用controller控制交互

## 如何使用
如果你想使用我的这套框架来进行快速开发，需要拷贝library下面的内容到你的项目中，作为一个module被你的项目引用即可

## 核心类
* BaseApplication 基础的Application，项目中请继承此类，新建Application
* BaseActivity 基础的Activity，项目中请继承此类，新建Activity
* BaseFragment 基础的Fragment，项目中请继承此类，新建Fragment
* BaseListFragment 基础的列表Fragment，继承自BaseFragment，如果你的页面是一个列表，并且想使用框架的列表机制，那么项目中请继承此类，新建Fragment
* BaseController 基础的Controller，项目中请继承此类，新建Controller
* BaseListController 基础的列表Controller，继承自BaseController，如果你的页面是一个列表，并且想使用框架的列表机制，那么项目中请继承此类，新建Controller
* BaseModel 基础的model，项目中请继承此类，新建model
* BaseModelService 基础的ModelService，model与controller的交互类，controller可以根据此类，得知如何获取数据
* BaseEvent 基础的Event，项目中请继承此类，新建Event

## 一个开发实例
下面将以图灵机器人模块，进行框架讲解及开发
项目中，以Tuling开头的类，属于图灵机器人模块
* 新建一个model类，[TulingModel](https://github.com/libo591/Luandun/blob/master/app/src/main/java/com/boboeye/luandun/model/impl/TulingModel.java)，继承自BaseModel,用于封装信息，如消息时间，消息发起人，消息的内容等
* 新建一个controller类，[Tuling123Controller](https://github.com/libo591/Luandun/blob/master/app/src/main/java/com/boboeye/luandun/controller/Tuling123Controller.java),用于提供获取数据的接口，如获取历史消息列表，查询答案等
* 数据的获取，我们新建[TulingModelService](https://github.com/libo591/Luandun/blob/master/app/src/main/java/com/boboeye/luandun/model/service/impl/TulingModelService.java)用于规范接口，新建[TulingLocalService](https://github.com/libo591/Luandun/blob/master/app/src/main/java/com/boboeye/luandun/model/service/impl/TulingLocalService.java)用于获取历史消息，新建[TulingNetService](https://github.com/libo591/Luandun/blob/master/app/src/main/java/com/boboeye/luandun/model/service/impl/TulingNetService.java)用语获取问题答案
* 上一步的方法，获取到数据后，需要想办法通知视图层进行更新，所以这里新建一个event类，用于封装进行通知的事件[TulingEvent](https://github.com/libo591/Luandun/blob/master/app/src/main/java/com/boboeye/luandun/event/TulingEvent.java)
* 经过1－4步，模型层和controller就已经完成了。这一步，需要根据情况建立视图层的类。如果你的activity需要回退功能，点击可以回退到上一个activity，则继承BaseBackActivity.否则，只需要简单继承BaseActivity,我们这里需要列表，所以新建[TulingActivity](https://github.com/libo591/Luandun/blob/master/app/src/main/java/com/boboeye/luandun/activitys/TulingActivity.java)
* 框架基本每个视图，都需要建立fragment。如果你的fragment需要列表功能，建议继承BaseListFragment。否则继承BaseFragment.我们这里新建[TulingFragment](https://github.com/libo591/Luandun/blob/master/app/src/main/java/com/boboeye/luandun/fragments/TulingFragment.java)
* listview需要的adapter，继承BaseListAdapter即可.我们这里新建[TulingListAdapter](https://github.com/libo591/Luandun/blob/master/app/src/main/java/com/boboeye/luandun/adapter/TulingListAdapter.java)
经过以上，一个模块的骨架就完成了。剩下的工作，包括视图的美化和交互，controller中接口方法的逻辑，model层的属性完善等

## 第三方资源
感谢
* butterknife 一个注入类库，可以方便的关联类属性和view组件，加快开发速度
* eventbus 一个事件类库，用于解耦模块
* android-async-http 一个网络请求框架，简化网络开发相关工作
* disklrucache 一个磁盘缓存类库
* floatingactionbutton 页面浮动按钮
* volley google的网络请求框架，包括了缓存功能，并且提供了图片请求的实现类
* leakcanary 内存泄漏检测工具，简单且好用
* robotium 一个测试框架，进行自动化测试
