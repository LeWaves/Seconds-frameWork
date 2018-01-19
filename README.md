
#  Seconds-framework【开发App框架】
[![](https://jitpack.io/v/LeWaves/Seconds-frameWork.svg)](https://jitpack.io/#LeWaves/Seconds-frameWork)

快速开发App框架，简单，便捷，支持（网络请求，图片加载，缓存，视频）。


## **描述**

支持http、https

支持文件上传和下载

支持图片加载

支持视频播放

支持轻量级缓存（处理复杂数据存储，这里推荐数据库 [Realm](https://realm.io/)）

框架里面封装了一个带加载进度条的webview控件支持https链接

项目中应用到的框架[okttp3](https://github.com/square/okhttp)、[volley](https://android.googlesource.com/platform/frameworks/volley)、[NiceVideoPlayer](https://github.com/jacky1234/NiceVideoPlayer)

 此框架是根据以上框架进行再次封装，OKhttp支持https请求，作为volley底层传输，实现高效传输数据；glide支持https加载
 
 特点：秒载（秒载数据列表,秒载webview数据），支持大文件下载
 
 

## **功能说明**

###      **集成使用**

  在app build.gradle 中添加：
  

   android{
   
           ...    
           
       allprojects {
       
        repositories {
        
            jcenter()
            
            maven { url 'https://jitpack.io' }
            
         }         
     }     
   }
   

   
   dependencies {   
   
           ...      
	   
           compile 'com.github.LeWaves:Seconds-frameWork:v-0.1.0'
              
       }
       
       
       
 


###    ** mainfast.xml添加权限**
    
    <uses-permission android:name="android.permission.INTERNET" />

###    **网络请求**

####     **HttpMethodState 提供GET、POST方式**

     new HttpJSONRequest(context, HttpMethodState.GET, url, new HttpJSONRequest.RequestNetWork() {
                 @Override
                 public void onSuccess(JSONObject response) {
                     
                 }

                 @Override
                 public void onFailure(VolleyError error) {
                    
                 }
             });
        
        第二种 添加tag

       new HttpJSONRequest(context, HttpMethodState.GET, url, new HttpJSONRequest.RequestNetWork() {
                 @Override
                 public void onSuccess(JSONObject response) {
                     
                 }

                 @Override
                 public void onFailure(VolleyError error) {
                    
                 }
             },tag);              
        
            
    
####    **文件上传**
     
      
    
    /**
     * 文件上传
     * @param  url 地址
     * @param  map 上传参数集 
     * @param  listener 监听
     *
     */
    
    HttpUploadFileRequest.uploadImgAndParameter(Map<String, Object> map,
                                                    String url, final RequestUploadListener listener) {
            @Override
            public void onSuccess(String result) {
                
            }

            @Override
            public void onFailure(VolleyError error) {

            }
        });
        
        
        
####    **支持大文件下载 **   
        封装OKhttp下载 
        功能已获取 Environment.getExternalStorageDirectory()目录
        
        自定义文件名和文件名称
        @ saveDir 自定义文件夹
        @ savePath 自定义文件名称
        
      HttpDownloadUtils.download(final String url, final String saveDir,final String savePath, final OnDownloadListener listener) { 
      
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress
         * 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
      
      }
        
      
      自定义文件名、url获取文件名称
      @ saveDir 自定义文件名       
        
      HttpDownloadUtils.download(final String url, final String saveDir, final OnDownloadListener listener) { 
      
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress
         * 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
      
      }
  
    提供以下几个方法
    
    /**
     * @return
     * 获取下载文件地址
     */
     
        getDownloadPath()
        
        
    /**
     * @return
     * 获取下载文件大小
     */
     
       getDownloadSize() 
  
    /**
     * @return
     * 获取下载文件目录
     */
     
      getDownloadDirPath()
      
      

####    **图片加载**

     此功能是引用了[gilde](https://github.com/bumptech/glide)框架

     功能使用：
     
     ImageLoader.display(mContext,imageView,url);
     
     第二种自定义loading和error加载View
     
     ImageLoader.display(mContext,imageView,url,loading,error);
     
        

####    **视频播放**


    此功能是引用了NiceVideoPlayer框架, 这里不再简述。可以详细查看[NiceVideoPlayer](https://github.com/jacky1234/NiceVideoPlayer)原文。    
  目前我已经将NiceVideoPlayer框架加入到当前lib里面
    
    
    


####    **轻量级缓存 IACache**

    
     IACache 轻量级缓存类，主要功能：缓存（图片，文件，JSON ,序列化数据,String数据，byte数据，Stream），移除某个key对应数据和清除所有数据。


####    ** 自定义webview支持https链接**
   
   
   ProgressBarWebView加载显示快，支持https URL，使用简洁，可以设置进度条颜色
   
   
    <com.lt.integrate.frame.view.ProgressBarWebView
    
        android:id="@+id/mWebView"
        
        android:layout_width="match_parent"
        
        android:layout_height="match_parent"
        
        android:background="@color/white"
        
        android:scrollbars="none"/>
        
        
        
        
  public class DetailsActivity extends AppCompatActivity {
  
    ProgressBarWebView mWebView;
    
    @Override
    
    public void onCreate( Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.xxx);
        
      
        mWebView = (ProgressBarWebView) findViewById(R.id.mWebView);

        //新增自定义ProgressBar颜色

        //mWebView.setProgressColors(Color.RED,Color.YELLOW);

       //mWebView.setProgressColors(Color.RED, Color.BLACK, Color.DKGRAY,Color.YELLOW);

        mWebView.loadUrl(getIntent().getStringExtra("url"));
        
     }   
     
   }
   
####    ** 自定义RoundImageView 默认为矩形，支持圆形or圆角**

         使用介绍

         private void initDisplayImg() {

                  roundImageView = (RoundImageView) findViewById(R.id.roundImageView);

                  roundImageView.setType(RoundImageView.TYPE_CIRCLE);//圆形图片
                  //roundImageView.setType(RoundImageView.TYPE_ROUND);//圆角图片
                  //roundImageView.setBorderRadius(20);//圆角图片,圆角大小 单位 dp
                  //roundImageView.setType(RoundImageView.TYPE_DEFAULT);//默认为矩形图片不用设置type

                  ImageLoader.display(this,roundImageView,url);

          }


          xml文件

           <com.lt.integrate.frame.view.RoundImageView

                  android:layout_width="120dp"

                  android:layout_height="120dp"

                  android:id="@+id/roundImageView"

                  />


####    ** API 24+ 动态申请权限封装**  
   
         项目使用[HiPermission](https://github.com/yewei02538/HiPermission)封装框架，本lib提供一个调用接口
	 
	 调用代码：
	 
	   多个权限申请
	   
	   List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();	
	   
           permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "Camera", R.drawable.permission_ic_camera));	
	   
           permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "Phone", R.drawable.permission_ic_phone));   
	   
	    /**
	    
              * @param  activity Context
	      
              *@param  permissions list 多个权限
	      
              *@param  title   弹窗title
	      
              *@param  msg    弹窗描述
	      
              *@param  color   颜色
	      
              *@param  animStyleId  动画风格
	      
              *@param  styleId  风格
	      
              *@param  mCallback 申请回调接口
	      
              */	      
	      
           支持多种接口调用可以省略（弹窗title、弹窗描述、颜色、动画风格、风格）
	   
	  IPermissionQueue.SendMutiPermissionQueue(this,permissionItems,"Permission","Please allow access to  Permission",R.color.colorPrimary,
                R.style.PermissionAnimFade, R.style.PermissionDefaultNormalStyle,new PermissionCallback() {
		
                    @Override
		    
                    public void onClose() {

                    }

                    @Override
		    
                    public void onFinish() {

                    }

                    @Override
		    
                    public void onDeny(String permission, int position) {

                    }

                    @Override
		    
                    public void onGuarantee(String permission, int position) {

                    }
		    
                });




         
	  单个权限申请
	 
	  String permission = Manifest.permission.READ_PHONE_STATE;
	 
	 IPermissionQueue.SendSingleMutiPermissionQueue(this,permission,new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onDeny(String permission, int position) {

                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });
           
	  
####    ** 滑动控件的封装**  
        此模块主要是针对scrollView 嵌套 recycleview\listview\viewpage,以及recycleview addHeaderView\addFooterView,
     功能的调用请看Demo里面的实现。此模块同时支持（京东和淘宝）商城 商品上拉查图文详情。lib中实现了很多控件，在这里就不列出来，大家可以查看demo中如何使用。



    效果图

        ![image](https://github.com/LeWaves/frame-lib/blob/master/screenshots/waves.gif)


    
#  总结
   
   在这里我非常感谢大神们的分享，在开发中给予了我们很大的帮助，此框架是根据以上介绍应用到的框架原基础上再次封装，在此大家有什么意见可以给我发邮件。

          联系人：Waves
          E-mail: LeWaves@yeah.net
