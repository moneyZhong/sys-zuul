请求格式 /{version}/{serviceName}/{resourcePath}
version: 必须是v1, v2, v3这种形式
serviceName: 注册到spring cloud的服务名称，并且小写(在上面的例子中是path_planning)
resourcePath: 要请求的服务的资源路径(在上面的例子里面是/truck/index)

网关必要的请求参数
accessid (required): ucenter的accessid
sign (required): 按照以下签名算法得到的结果。如果参数是使用url参数传递过来的，必须使用urlencode编码
token (not required): Ucenter获取的登录token，手机 使用 mobile_token
g7timestamp (required): 13位的签名时间戳，一个请求的签名必须在当前时间15分钟内进行


关于以上参数的位置说明：

如果是get请求，很明显上述参数一定在get里面
如果是post form请求，上述参数可以放到url或者post参数，两者都行
如果是post json请求，上述参数一定是放到URL参数
签名算法
Signature = Base64( HMAC_SHA1( YourSecretKey, UTF_8_Encoding_Of( StringToSign ) ) );

StringToSign = HTTP_Verb + "\n" + Timestamp + "\n" + CanonicalizedResource;
注意!"\n"表示换行符，不是字符串的\n

YourSecretKey: 分配的YourSecretKey(上面的请求计算用的是Wgyokg4EC2dJKwOYwBu3zrZHgfnOYHWi)
HTTP_Verb: http请求类型，比如GET, POST(上面的例子使用的POST方式)
CanonicalizedResource: 资源路径(/v1/path_planning/truck/index)
上面的请求签名结果是fSuKs38XvFEHH6iSwdmpuFKcxEg=, 经过urlencode之后是fSuKs38XvFEHH6iSwdmpuFKcxEg%3D