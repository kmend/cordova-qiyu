#import "QiYuPlugin.h"
#import "QYSDK.h"

@implementation QiYuPlugin

-(void)openQiYu:(CDVInvokedUrlCommand *)command{
    
    if (command.arguments.count>0) {
        
        
        NSDictionary* data =  command.arguments[0];

        QYUserInfo *userInfo = [[QYUserInfo alloc] init];
        userInfo.userId = [data objectForKey:@"UserId"];
        userInfo.data =[data objectForKey:@"Data"];
        [[QYSDK sharedSDK] setUserInfo:userInfo];
        
        [[QYSDK sharedSDK] customUIConfig].customerHeadImageUrl = [data objectForKey:@"Avatar"];
        
        QYSource *source = [[QYSource alloc] init];
        source.title =  [data objectForKey:@"UrlTitle"];
        source.urlString =  [data objectForKey:@"Url"];
        source.customInfo =  [data objectForKey:@"UrlDesc"];
        
        QYSessionViewController *vc = [[QYSDK sharedSDK] sessionViewController];
        vc.sessionTitle = [data objectForKey:@"Title"];
        vc.source = source;
        vc.hidesBottomBarWhenPushed = YES;

        [self.viewController.navigationController popViewControllerAnimated:YES];
        
        //[self.navigationController pushViewController:vc animated:YES];
        [self.viewController.navigationController setNavigationBarHidden:NO];
        [self.viewController.navigationController pushViewController:vc animated:YES];
        [[QYSDK sharedSDK] customUIConfig].bottomMargin = 0;
        
    }
    
}

@end
