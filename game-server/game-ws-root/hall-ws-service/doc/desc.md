
定义消息类型：

魔法头 short head;
长度 int length;
版本号 byte version;
命令 short cmd;
序列号 int serial;

魔法头 short head;
长度 int length;
版本号 byte version;
命令 short cmd;
序列号 int serial;
玩家id long playerId;
玩家会话凭据 int tocken;

登录成功 拿到用户 【异步】初始一些信息 关闭连接

客户端真实 拿到 返回的token信息 用token拿来用户做操作了

如何判断当前 用户是否注册过该游戏
找到 一个 对应的名字 判断所有服务器的所有名字不能重复

启动服务的时候 将数据库的所有用户的信息 加载出来 可以慢一点

客户端在 进去房间的时候已经 建立连接 
网关 和服务建立连接不冲突 客户端的连接