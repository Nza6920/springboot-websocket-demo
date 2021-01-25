# 模块说明:
> ## websocket-base(springboot 集成 websocket 基础使用)
> > - websocket 集成
> > - 消息群发
> > - 点对点通信
> > - 客户端断线重连
> > - 心跳检测

> ## websocket-stomp(结合 stomp&socket.js 使用)
> > - 配置
> > - 点对点通信

> ## websocket-stomp-rabbitmq(整合 rabbitmq 发送消息)
 > > - 配置
 > > - 四种方式推送消息(Exchange, Queue, AmqQueue, Topic)
 > > - 支持 nginx 负载均衡 (配置见 resources/websocket.conf)

> ## websocket-stomp-cloud(websocket 微服务负载均衡)
 > > - cloud-client (客户端)
 > > - cloud-eureka (eureka 注册中心) 注:  **优先启动**
 > > - cloud-gateway (微服务网关)
 > > - cloud-stomp (消息推送服务)
 > >
 > > 架构图 resources/架构图.png  
 > > 端口信息 resources/端口.txt 

> ## websocket-stomp-reliability(消息可靠投递)
 > > - 手动ACK
 > > - 持久化队列

> ## websocket-client(Java 原生客户端实现消息推送)
 > > - 原生客户端实现消息推送
 > >
 > > 架构图 resources/架构图.png

> ## websocket-authentication(websocket 授权认证)
 > > - websocket 接口鉴权 (发送消息、订阅主题等操作)







