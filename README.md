# cordova-qiyu


# js调用
 let params = {}; 
    params['UserId'] = "";
    //本地客户端显示用户头像
    params['Avatar'] = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1621341193,1402394044&fm=23&gp=0.jpg";
    params["Data"] = [];
    params["Data"].push({"key":"real_name", "value": "" });
    //七鱼客服端显示用户头像
    params["Data"].push({"key":"avatar", "value": "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1621341193,1402394044&fm=23&gp=0.jpg"});
    //自定义配置
    params["Data"].push({"index":0, "key":"ShowName", "label": "呢称", "value": "" });
          
    (<any>window).QiYu.openQiYu(params,()=>{
    },()=>{
    });



# IOS 

## 安装包支持 cocoapod

cordova plugin add cordova-plugin-cocoapod-support --save

## 切换到ios项目目录安装pod platforms/ios
运行pod install

编译ios项目
ionic build ios

## 修改rootView 将root添加在navigation上
CordovaLib.xcodeproj/Public/CDVAppDelegate.m

//self.window.rootViewController = self.viewController;
UINavigationController *nav = [[UINavigationController alloc]initWithRootViewController:viewController];
[nav setNavigationBarHidden:YES animated:YES];
nav.navigationBar.barTintColor = [UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0];
self.window.rootViewController = nav;

## 修改viewWillAppear 导航从七鱼界面退出pop时 隐藏导航
CordovaLib.xcodeproj/Public/CDVViewController.m
-(void)viewWillAppear:(BOOL)animated
[self.navigationController setNavigationBarHidden:YES];

## 初始化七鱼
Classes/AppDelegate.m
[[QYSDK sharedSDK] registerAppId:@"b7bca11accfe2de1d89f6fa6e4345da2" appName:@"bobol"];


# Android

## 引入第三方库

    compile 'com.qiyukf.unicorn:unicorn:3.6.0'
    compile 'com.facebook.fresco:fresco:1.3.0'
    compile 'com.nostra13.universalimageloader:universal-image-loader:1.9.5'
    compile 'com.squareup.picasso:picasso-parent:2.5.2'
    compile 'com.squareup.picasso:picasso:2.5.2'

## 增加Application 类

    <application android:name="com.bobol.tipster.BassApplication" 
        ...>

## 初始化Qiyu，BassApplication

    public YSFOptions myYSFOptions;

    @Override
    public void onCreate() {
        super.onCreate();

        //DemoPreferences.init(this);
        Log.w("QiYuModel", "init!");
        myYSFOptions = ysfOptions();
        // you can also use "new FrescoImageLoader()" or "new PicassoImageLoader()"
        if (!Unicorn.init(this, "b7bca11accfe2de1d89f6fa6e4345da2", myYSFOptions, new UILImageLoader())) {
            Log.w("QiYuModel", "init qiyu sdk error!");
        }

        if (Utils.inMainProcess(this)) {
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
            Fresco.initialize(this);
            Picasso.with(this);
        }
    }

    private YSFOptions ysfOptions() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        options.statusBarNotificationConfig.notificationSmallIconId = R.drawable.icon;
        options.savePowerConfig = new SavePowerConfig();
        options.uiCustomization = new UICustomization();
        return options;
    }


