
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