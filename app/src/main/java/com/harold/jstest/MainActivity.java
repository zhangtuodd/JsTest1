package com.harold.jstest;

import com.harold.base.JSKit;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private WebView mWebView;

	@SuppressLint("JavascriptInterface")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.loadUrl("file:///android_asset/js_java_interaction.html");//加载本地asset下面的js_java_interaction.html文件
		//mWebView.loadUrl("https://www.baidu.com/");//加载本地assets下面的js_java_interaction.html文件

		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);//打开js支持
		/**
		 * 打开js接口給H5调用，参数1为本地类名，参数2为别名；h5用window.别名.类名里的方法名
		 * 才能调用方法里面的内容，例如：window.android.back();
		 * */
		mWebView.addJavascriptInterface(new JsInteration(), "android");
		mWebView.setWebViewClient(new WebViewClient());
		mWebView.setWebChromeClient(new WebChromeClient());
	}

	/**
	 * 自己写一个类，里面是提供给H5访问的方法
	 * */
	public class JsInteration {

		@JavascriptInterface//一定要写，不然H5调不到这个方法
		public void back() {
//			return "我是java里的方法返回值";
			Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
		}
	}

	//点击按钮，访问H5里带返回值的方法
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public void onClick(View v) {
		Log.e("TAG", "onClick: ");

//        mWebView.loadUrl("JavaScript:show()");//直接访问H5里不带返回值的方法，show()为H5里的方法


		//传固定字符串可以直接用单引号括起来
//		mWebView.loadUrl("javascript:alertMessage('哈哈')");//访问H5里带参数的方法，alertMessage(message)为H5里的方法

		//当出入变量名时，需要用转义符隔开
//		String content="9880";
//		mWebView.loadUrl("javascript:alertMessage(\""   +content+   "\")"   );


		//Android调用有返回值js方法，安卓4.4以上才能用这个方法
		//方法名不能写错，写错也不会崩溃但是返回为null
//		mWebView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
//			@Override
//			public void onReceiveValue(String value) {
//				Log.d(TAG, "js返回的结果为=" + value);
//				Toast.makeText(MainActivity.this,"js返回的结果为=" + value,Toast.LENGTH_LONG).show();
//			}
//		});
	}
}

//
//	private WebView mWebView;
//	private Button btnShowInfo;
//	private JSKit js;
//	private Handler mHandler = new Handler();
//
//	@Override
//	@SuppressLint("JavascriptInterface")
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.main);
//		//初始化控件
//		mWebView = (WebView) findViewById(R.id.wv_test);
//		btnShowInfo = (Button) findViewById(R.id.btn_showmsg);
//		//实例化js对象
////		js = new JSKit(this);
//		//设置参数
//		mWebView.getSettings().setBuiltInZoomControls(true);
//		//内容的渲染需要webviewChromClient去实现，设置webviewChromClient基类，解决js中alert不弹出的问题和其他内容渲染问题
//		mWebView.setWebChromeClient(new WebChromeClient());
//		mWebView.getSettings().setJavaScriptEnabled(true);
//		//把js绑定到全局的myjs上，myjs的作用域是全局的，初始化后可随处使用
////		mWebView.addJavascriptInterface(js, "myjs");
//
//		mWebView.loadUrl("file:///android_asset/jstest.html");
//
//		btnShowInfo.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				mHandler.post(new Runnable() {
//					@Override
//					public void run() {
//						//调用 HTML 中的javaScript 函数
//						mWebView.loadUrl("javascript:showMsg()");
//					}
//				});
//			}
//		});
//	}
//
//}
