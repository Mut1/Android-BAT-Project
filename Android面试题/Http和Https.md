#### HHttp到底是什么

HyperText Transfer Protocol 超文本传输协议

- 超文本：在电脑中显示的、含有可以指向其他文本的链接的文本----HTML

#### 报文格式：Request

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/image-20210718131959397.png" style="zoom:50%;" />

#### 报文格式：Response

<img src="https://mut-pic-1305269047.cos.ap-nanjing.myqcloud.com/image-20210718132111160.png" style="zoom:50%;" />

#### 请求方法

- Get
  - 获取资源；没有Body
  - 幂等

- Post
  - 增加或修改资源；有Body
  - 不具有幂等性

- Put
  - 修改资源；有Body
  - 幂等

- DELETE
  - 删除资源；没有Body
  - 幂等
- HEAD

#### 状态码

- 1xx：临时性消息
- 2xx：成功
- 3xx：重定向
- 4xx：客户端错误
- 5xx：服务器错误

#### Header

作用：HTTP消息的元数据（metadata）

- Host：服务器主机地址
- Content-Type/ Content-length:Body的类型和长度
  - Content-length:内容的长度（字节）
  - Content-Type：内容的类型
    - text/html: html文本,用于浏览器页面响应
    - application/x-www-form-urlencoded:普通表单, encoded URL格式(纯文字表单)
    - multipart/form-data:多部分形式,一般用于传输包含二进制内容的多项内容
    - application/json:json形式,用于 Web Api的响应或POST/PUT请求
    - image/jpeg/  application/zlip..:单文件,用于 Web Api响应或POST/PUT请求
- Location:重定向的目标URL
- User-Agent:用户代理
- Cookie/Set-Cookie:发送 Cookie/设置 ookie
- Authorization:授权信息
- Accept:客户端能接受的数据类型。如text/ html
- Accept- Charset:客户端接受的字符集。如utf-8
- Accept- Encoding:客户端接受的压缩编码类型。如gzip
- Content- Encoding:压缩类型。如gzip

#### HTTPS

- HTTP Secure/HTTP over SSL/HTTP over TLS
- SSL: Secure Socket Layer-> TLS Transport Layer Security

##### 定义：

在HTTP之下增加的一个**安全层**，用于保障HTTP的加密传输

##### 本质：

在客户端和服务器之间用非对称加密协商出一套对称密钥，每次发送信息之前将内容加密，收到之后解密，达到内容的加密传输

##### 为什么不直接用非对称加密？

⾮对称加密由于使⽤了复杂了数学原理，因此计算相当复杂，如果完全使⽤⾮对称 加密来加密通信内容，会严重影响⽹络通信的性能

##### HTTPS连接的建立

- 示例
  - 客户端请求建立TLS连接
  - 服务器发回证书
  - 客户端验证服务器证书
  - 客户端信任服务器后，和服务器协商对称密钥
  - 使用对称密钥开始通信

- 实际

  1. Client Hello（客户端->服务器）
     - 可选的TLS版本
     - 可选的加密套件
       - 可选的对称加密算法
       - 可选的非对称加密算法
       - 可选的Hash算法
     - 客户端随机数
  2. Server Hello（服务器->客户端）
     - TLS版本
     - 加密套件：
       - 对称加密算法
       - 非对称加密算法
       - hash算法
     - 客户端随机数
     - 服务器随机数
  3. 服务器证书（服务器->客户端）
     - 服务器公钥（其实是个数据）(证书)
     - **服务器主机名**
     - 服务器地区
     - 服务器公钥的签名
     - 证书签发机构的公钥（用于验证这个[公钥签名]的另一个公钥）(证书签发机构的证书)
     - 证书签发机构的公钥的签名
     - 证书签发机构的签发机构的公钥(根证书机构的证书)
  4. Pre-master secret （客户端->服务器）
     - Master secret
     - 客户端加密密钥 
     - 服务端加密密钥 
     - 客户端 MAC secret 
     - 服务端 MAC secret
  5. 客户端：将使用加密通信
  6. 客户端发送：Finished
  7. 服务器通知：将使⽤加密通信
  8. 服务器发送：Finished

#### Http和Https

- http和https使用连接方式不同，默认端口也不一样，http是80，https是443。

#### WebSocket 与 Socket 的区别

1. Socket是传输控制层的接口。用户可以通过socket来操作底层TCP/IP协议族通信
2. Websocket是一个完整应用层协议
3. Socket更灵活，Websocket更易用
4. 两者都能做即时通讯

