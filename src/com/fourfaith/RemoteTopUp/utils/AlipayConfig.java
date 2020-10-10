package com.fourfaith.RemoteTopUp.utils;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2018080860987503";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDLanq73kke4Sj+AG/B1EFXDwV/Z3Acf07Y8+6+kVij3IZpYk0OXzY/My4+af2yE7l8FlKFAQTSjsOvEELLyvDg4kFcU2nD3yBTPfSVR31W8D7GgBrX4uYyOSJAYcw4rsUC33Y5e4lnIYuBMRK19yEOeuAEuHteHws3/iq8ekjpuR0bq9JmWRl4DOktxjBwP3TFj+XTDyn7Qat+ES/cnKNB2WgltpmbbermIu92oBfyH3X0fAfcpkD10IERhvuRED0vNKNmBpgbtkbXBb+k1Ru6wfYe75pRGBqI4GK32Me6ZffAoVOJOGa5u5kQQMxL4hkmDG3EGgy2cNFQvlOaFOQPAgMBAAECggEBALV4ygP+nBcrYmmwNAOk+6HSaeIugqs5vDr66/whKQYOv+8WXo46zOd3HoNaBZWv2SNlurqjnQzHhrYQeLbc1yaZUxwt4kZ3MZxxwljHDX/ZFvf543jEGP+nTvJHDW1oJaSZfRxtJLjYCV/5SqsBzQDhCCfN+WmPom+SlcfOBGxVPHuQOyaxzSMVXVPkk8cU+HSda9oQ+dhykJEPFrBMO6H6PCXYGUY5u9Z03GHXZS1Z5iafm8Vsl628G46sTkO7OuuO/CcCgEpHsXG0+sDgkSO7mjEu4Cn+EJdUAX+M3F5u6c92B1FWc2tWo0YJ3xKuONznxUtUMDfqt1w17fdCVEECgYEA8B6GWYdTD/Kqxp5FY8B+LLNJPkYgakphxGoFGAAiVJ+RVg6HZ9QDxhpBJK7LC3t18TxbDXBlQ8e1IYB05Af5IFR3a/uTUQA2DeGFW6AN6/tAg8chC6HL3K22hcOzUZsDnYsrg/RHVxiEW1qYknmccmwwLN1PlE0fFao+3fzdN6ECgYEA2N6HTHgATDPJfjPF7eqw9LtrYk5hrnJF2ju7P71BNziP8r1o/Htiuek8rrlmyundlffpe57I9I2OPRUP83uCoRZU2dQ3JV8quDguMLzjy7pRdmucVqB9FOgDDXBvF12ILFHymsQqSiQdxvTQAj8d6wR8Qw9JL+7Y8ycujnCKva8CgYEAvIKKYSEfccbGkdXRjiWiKP+8AbrWWfv5U1UUmKoKSbtYQ2J2pWnKaK1V9TGQ0HvltTT4Gs3hglv39Mw/eIJV8C6/7rN8DTyIM0+Y/bzjER+Yn5wmclZckOyD/fINBoUXSojm6dpOY68owfuXCm+VBjOvgplgj4IYZE7aujZ5ieECgYB5mLvv6lDwaREPC+Rss+yoK/XX1u76KuRzqo4g/PJ2DebYSc910ZDXgKkfpqC2IU626ZwmTi+Kv1wDb63T7Wqe+7BzuoN5FtUTGZz8gsyOzFckuM2d3LqFYFllzabcSPszLneCw36at4lipp2CxPT3czphN07n61oFa/C0uPFxzQKBgQDPVV4xh6w+GQL7rLp6/ZWreQYW21m7j6Q4kTZ5E/bFLfPYVGTis1faGM06ONCdbSA7+1Xj17nuYGrtOGw7ABv7ZHx4r9GxIjtdG2izKqORTcGRk5c6LN/P8O4e+ERsjLk1JBCOlGLrZ4+rlMLUA0Grrof3umOVB+oWVtzOI0ND4g==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0pWU3YXJL3z63kjACy8RMsSGX0tbRcdqoq5f0qm4v/c1GV00B/5x87GKctmathBGxDFpNVT+FfzTpKmTyUxkKyyHBnBs0cJt8OzXvSNncszLofyxdLtAMOD8t0ZLPkTIwrzuPuVzcUE0gsko6lRbWBo6q4BR+pCIBByF8HFSRclJxTShX4jbbRJnjgbZ1y0+TF9JS4S715FzwO+y39yshFMZdogVLKgEtXr+JdHpOvaX6DLBKY5gYbhvYMml2mNBFVwX0IwPWUROBFGbz9FLqU39ks/090DL8MOIjZbXX8CeqlTvNHI0t7ga7/1Dh31XNR9RD/g1J9gurFzl4ZeAcwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://192.168.1.99:9098/alipay/alipayNotifyNotice.action";
	
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://192.168.1.99:9098/alipay/alipayReturnNotice.action";
	// 签名方式
	public static String sign_type = "RSA2";
	// 字符编码格式
	public static String charset = "utf-8";
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	//日志路径
	public static String log_path = "C:\\";
	
	/** 
    * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
    * @param sWord 要写入日志里的文本内容
    */
   public static void logResult(String sWord) {
       FileWriter writer = null;
       try {
           writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
           writer.write(sWord);
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           if (writer != null) {
               try {
                   writer.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
   }
}
